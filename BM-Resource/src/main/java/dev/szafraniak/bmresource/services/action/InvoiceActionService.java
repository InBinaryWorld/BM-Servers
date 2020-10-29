package dev.szafraniak.bmresource.services.action;

import dev.szafraniak.bmresource.converters.action.CreateInvoiceConverter;
import dev.szafraniak.bmresource.dto.action.createInvoice.CreateInvoiceDTO;
import dev.szafraniak.bmresource.dto.action.createInvoice.InvoiceOrderItemDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePostDTO;
import dev.szafraniak.bmresource.model.action.*;
import dev.szafraniak.bmresource.services.FileService;
import dev.szafraniak.bmresource.utils.FinancialUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceActionService {

    private FileService fileService;
    private InvoiceDocGenerator docGenerator;
    private CreateInvoiceConverter converter;


    public InvoicePostDTO generateInvoice(CreateInvoiceDTO dto, Long companyId, String fileReference) throws Exception {
        CreateInvoiceModel baseInfo = converter.convertToModel(dto, companyId);
        InvoiceDetailsModel details = calculateInvoiceDetails(dto.getItems());
        InvoicePostDTO postModel = converter.convertToPostDTO(baseInfo, details, fileReference);

        String filePath = fileService.getInvoicePath(fileReference);
        docGenerator.createInvoice(baseInfo, details, filePath);
        return postModel;
    }

    private InvoiceDetailsModel calculateInvoiceDetails(List<InvoiceOrderItemDTO> itemsDTO) {
        List<InvoiceOrderItemModel> items = generateInvoiceItems(itemsDTO);
        List<TaxGroupAmountModel> taxGroups = groupByTax(items);
        BigDecimal totalNet = FinancialUtils.sumBy(taxGroups, TaxGroupAmountModel::getNet);
        BigDecimal totalTax = FinancialUtils.sumBy(taxGroups, TaxGroupAmountModel::getTax);
        BigDecimal totalGross = FinancialUtils.sumBy(taxGroups, TaxGroupAmountModel::getGross);
        return new InvoiceDetailsModel(totalGross, totalTax, totalNet, items, taxGroups);
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
        return items.stream().collect(Collectors.groupingBy(InvoiceOrderItemModel::getTaxRate))
                .entrySet().stream().map(entry -> {
                    BigDecimal taxRate = entry.getKey();
                    List<BigDecimal> netAmounts = entry.getValue().stream()
                            .map(InvoiceOrderItemModel::getNet).collect(Collectors.toList());
                    AmountModel amount = FinancialUtils.countTaxGroupAmount(taxRate, netAmounts);
                    return new TaxGroupAmountModel(amount, taxRate);
                }).collect(Collectors.toList());
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
