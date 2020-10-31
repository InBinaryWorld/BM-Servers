package dev.szafraniak.bmresource.services.action;

import dev.szafraniak.bmresource.converters.action.CreateInvoiceConverter;
import dev.szafraniak.bmresource.dto.action.createInvoice.CreateInvoiceDTO;
import dev.szafraniak.bmresource.dto.action.createInvoice.InvoiceOrderItemDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePostDTO;
import dev.szafraniak.bmresource.model.action.invoice.*;
import dev.szafraniak.bmresource.services.FileService;
import dev.szafraniak.bmresource.utils.FinancialUtils;
import dev.szafraniak.bmresource.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceActionService {

    private FileService fileService;
    private InvoiceDocGenerator docGenerator;
    private CreateInvoiceConverter converter;

    public InvoicePostDTO generateInvoice(CreateInvoiceDTO dto, Long companyId) throws Exception {
        BaseInvoiceDataModel baseInfo = converter.convertToModel(dto, companyId);
        FinancesInvoiceSectionModel finances = calculateInvoiceFinances(dto.getItems());

        String fileReference = generateInvoiceFileReference(dto, companyId);
        String filePath = fileService.getInvoicePath(fileReference);
        docGenerator.createInvoice(baseInfo, finances, filePath);
        return converter.convertToPostDTO(baseInfo, finances, fileReference);
    }

    private FinancesInvoiceSectionModel calculateInvoiceFinances(List<InvoiceOrderItemDTO> itemsDTO) {
        List<InvoiceOrderItemModel> items = generateInvoiceItems(itemsDTO);
        List<TaxGroupAmountModel> taxGroups = groupByTax(items);
        AmountModel totalAmount = countTotalAmount(taxGroups);
        return new FinancesInvoiceSectionModel(totalAmount, items, taxGroups);
    }

    private AmountModel countTotalAmount(List<TaxGroupAmountModel> taxGroups) {
        AmountModel amount = new AmountModel();
        amount.setNet(FinancialUtils.sumBy(taxGroups, TaxGroupAmountModel::getNet));
        amount.setTax(FinancialUtils.sumBy(taxGroups, TaxGroupAmountModel::getTax));
        amount.setGross(FinancialUtils.sumBy(taxGroups, TaxGroupAmountModel::getGross));
        return amount;
    }

    private List<InvoiceOrderItemModel> generateInvoiceItems(List<InvoiceOrderItemDTO> itemsDTO) {
        return itemsDTO.stream().map(itemDTO -> {
            BigDecimal netPrice = itemDTO.getNetPrice();
            BigDecimal taxRate = itemDTO.getTaxRate().stripTrailingZeros();
            BigDecimal quantity = itemDTO.getQuantity().stripTrailingZeros();
            AmountModel amountModel = FinancialUtils.countAmount(netPrice, taxRate, quantity);
            return converter.convertToModel(itemDTO, amountModel);
        }).collect(Collectors.toList());
    }

    private List<TaxGroupAmountModel> groupByTax(List<InvoiceOrderItemModel> items) {
        Set<Map.Entry<BigDecimal, List<InvoiceOrderItemModel>>> groups = items.stream()
                .collect(Collectors.groupingBy(InvoiceOrderItemModel::getTaxRate)).entrySet();
        return groups.stream().map(entry -> {
            BigDecimal taxRate = entry.getKey();
            List<BigDecimal> netAmounts = ListUtils.map(entry.getValue(), InvoiceOrderItemModel::getNet);
            AmountModel amount = FinancialUtils.countTaxGroupAmount(taxRate, netAmounts);
            return new TaxGroupAmountModel(amount, taxRate);
        }).collect(Collectors.toList());
    }


    private String generateInvoiceFileReference(CreateInvoiceDTO dto, Long companyId) {
        String uuid = UUID.randomUUID().toString();
        long secondsPart = dto.getCreationDate().toEpochSecond();
        return String.format("%d#%d#%s", companyId, secondsPart, uuid);
    }

    @Autowired
    public void setConverter(CreateInvoiceConverter converter) {
        this.converter = converter;
    }

    @Autowired
    public void setDocGenerator(InvoiceDocGenerator docGenerator) {
        this.docGenerator = docGenerator;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
