package dev.szafraniak.bmresource.services.action;

import dev.szafraniak.bmresource.converters.action.CreateInvoiceConverter;
import dev.szafraniak.bmresource.dto.action.createInvoice.CreateInvoiceDTO;
import dev.szafraniak.bmresource.dto.action.createInvoice.InvoiceOrderItemDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePostDTO;
import dev.szafraniak.bmresource.model.action.CreateInvoiceModel;
import dev.szafraniak.bmresource.model.action.InvoiceDetailsModel;
import dev.szafraniak.bmresource.model.action.InvoiceOrderItemModel;
import dev.szafraniak.bmresource.model.action.TaxGroupAmountModel;
import dev.szafraniak.bmresource.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InvoiceActionService {

    private FileService fileService;
    private InvoiceDocGenerator docGenerator;
    private CreateInvoiceConverter converter;


    public InvoicePostDTO generateInvoice(CreateInvoiceDTO dto, Long companyId) throws Exception {
        CreateInvoiceModel baseInfo = converter.convertToModel(dto, companyId);
        InvoiceDetailsModel details = calculateInvoiceDetails(dto.getItems());
        String filePath = fileService.getInvoicePath("invoice.pdf");
        docGenerator.createInvoice(baseInfo, details, filePath);
        return converter.convertToPostDTO(baseInfo, details, filePath);
    }

    private InvoiceDetailsModel calculateInvoiceDetails(List<InvoiceOrderItemDTO> itemsDTO) {
        List<InvoiceOrderItemModel> items = generateInvoiceItems(itemsDTO);
        List<TaxGroupAmountModel> taxGroups = groupByTax(items);
        BigDecimal totalNet = sumBy(taxGroups, TaxGroupAmountModel::getNet);
        BigDecimal totalTax = sumBy(taxGroups, TaxGroupAmountModel::getTax);
        BigDecimal totalGross = sumBy(taxGroups, TaxGroupAmountModel::getGross);
        return new InvoiceDetailsModel(totalGross, totalTax, totalNet, items, taxGroups);
    }

    private List<InvoiceOrderItemModel> generateInvoiceItems(List<InvoiceOrderItemDTO> itemsDTO) {
        return itemsDTO.stream()
                .map(itemDTO -> {
                    BigDecimal net = itemDTO.getNetPrice().setScale(2, RoundingMode.HALF_UP)
                            .multiply(itemDTO.getQuantity()).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal tax = itemDTO.getTaxRate().movePointLeft(2).multiply(net)
                            .setScale(2, RoundingMode.HALF_UP);
                    BigDecimal gross = net.add(tax);
                    return converter.convertToModel(itemDTO, net, tax, gross);
                }).collect(Collectors.toList());
    }

    private List<TaxGroupAmountModel> groupByTax(List<InvoiceOrderItemModel> items) {
        return items.stream().collect(Collectors.groupingBy(InvoiceOrderItemModel::getTaxRate))
                .entrySet().stream().map(entry -> {
                    BigDecimal net = sumBy(entry.getValue(), InvoiceOrderItemModel::getNet);
                    BigDecimal tax = entry.getKey().movePointLeft(2).multiply(net)
                            .setScale(2, RoundingMode.HALF_UP);
                    BigDecimal gross = net.add(tax);
                    return new TaxGroupAmountModel(net, tax, gross, entry.getKey());
                }).collect(Collectors.toList());
    }

    public <T> BigDecimal sumBy(List<T> list, Function<T, BigDecimal> fieldFunc) {
        return list.stream().reduce(new BigDecimal("0"), (acc, next) -> acc.add(fieldFunc.apply(next)), BigDecimal::add);
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
