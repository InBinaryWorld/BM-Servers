package dev.szafraniak.bmresource.services.action;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dev.szafraniak.bmresource.config.BaseEnvironment;
import dev.szafraniak.bmresource.model.action.contact.InvoiceCompanyContactModel;
import dev.szafraniak.bmresource.model.action.contact.InvoiceContactModel;
import dev.szafraniak.bmresource.model.action.invoice.*;
import dev.szafraniak.bmresource.model.entity.payment.PaymentMethod;
import dev.szafraniak.bmresource.model.entity.payment.PaymentMethodTransfer;
import dev.szafraniak.bmresource.utils.Formatters;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Service
public class InvoiceDocGeneratorIText implements InvoiceDocGenerator {

    private final Font logoFont;
    private final Font defaultFont;
    private final Font defaultBoldFont;
    private final Font smallFont;
    private final Font invoiceNameFont;

    private final String fontDir = "fonts/times_new_roman.ttf";

    public InvoiceDocGeneratorIText() throws IOException, DocumentException {
        BaseFont baseFont = BaseFont.createFont(fontDir, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        smallFont = new Font(baseFont, 8);
        defaultFont = new Font(baseFont, 9);
        defaultBoldFont = new Font(baseFont, 9, Font.BOLD);
        invoiceNameFont = new Font(baseFont, 10, Font.BOLD);
        logoFont = new Font(baseFont, 24, Font.BOLD);
    }

    public void createInvoice(BaseInvoiceDataModel model, FinancesInvoiceSectionModel finances, String outputFile) throws FileNotFoundException, DocumentException {
        FileOutputStream os = new FileOutputStream(outputFile);
        Document document = new Document(PageSize.A4);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, os);
        pdfWriter.setPdfVersion(PdfWriter.VERSION_1_7);

        document.open();
        setBaseData(document, model);
        setItems(document, finances);
        setSummary(document, finances);
        setSignFields(document);
        document.close();
    }

    private void setBaseData(Document doc, BaseInvoiceDataModel model) throws DocumentException {
        PdfPTable invoiceInfoTable = new PdfPTable(3);
        invoiceInfoTable.setWidthPercentage(100);
        invoiceInfoTable.setWidths(new float[]{5f, 1f, 5f});

        PdfPCell logoCell = getLogoCell(model.getInvoiceLogo());
        PdfPCell BaseDataCell = getBaseDataCell(model);
        invoiceInfoTable.addCell(logoCell);
        invoiceInfoTable.addCell(getEmptyCell());
        invoiceInfoTable.addCell(BaseDataCell);
        doc.add(invoiceInfoTable);

        PdfPTable contactTable = new PdfPTable(2);
        contactTable.setWidthPercentage(100);
        contactTable.setSpacingBefore(15);
        contactTable.setWidths(new float[]{6f, 5f});

        PdfPCell sellerCell = getSellerCell(model.getSeller(), model.getPaymentMethod());
        PdfPCell buyerCell = getContactCell("Nabywca", model.getBuyer());
        contactTable.addCell(sellerCell);
        contactTable.addCell(buyerCell);

        if (model.getReceiver() != null) {
            PdfPCell receiverCell = getContactCell("Odbiorca", model.getReceiver());
            receiverCell.setPaddingTop(4);
            contactTable.addCell(getEmptyCell());
            contactTable.addCell(receiverCell);
        }
        doc.add(contactTable);
    }

    private PdfPCell getLogoCell(String logo) {
        PdfPCell cell = createParCell(logo == null ? "" : logo, logoFont);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPaddingBottom(0);
        return cell;
    }

    private PdfPCell getBaseDataCell(BaseInvoiceDataModel model) {
        String creationDate = Formatters.formatDate(model.getCreationDate());
        String dueDate = Formatters.formatDate(model.getDueDate());
        String paymentMethod = model.getPaymentMethod() instanceof PaymentMethodTransfer ? "Przelew" : "Gotowka";

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        String invoiceNameText = "Faktura VAT nr " + model.getInvoiceNumber();
        PdfPCell invName = createParCell(invoiceNameText, Element.ALIGN_LEFT, invoiceNameFont);
        invName.setColspan(2);

        PdfPCell desc = noBorderDecortor(new PdfPCell());
        desc.addElement(createParagraph("Data wystawienia:"));
        desc.addElement(createParagraph("Termin zapłaty:"));
        desc.addElement(createParagraph("Metoda płatności:"));

        PdfPCell val = noBorderDecortor(new PdfPCell());
        val.addElement(createParagraph(creationDate));
        val.addElement(createParagraph(dueDate));
        val.addElement(createParagraph(paymentMethod));

        table.addCell(invName);
        table.addCell(desc);
        table.addCell(val);

        PdfPCell cell = new PdfPCell(table);
        cell.setPaddingRight(30);
        return noBorderDecortor(cell);
    }


    private PdfPCell getContactCell(String title, InvoiceContactModel model) {
        PdfPCell contact = new PdfPCell();
        contact.addElement(createParagraph(title, defaultBoldFont));
        contact.addElement(createParagraph(model.getName()));
        for (String addressRow : model.getAddressRows()) {
            contact.addElement(createParagraph(addressRow));
        }
        if (model instanceof InvoiceCompanyContactModel) {
            String taxId = ((InvoiceCompanyContactModel) model).getTaxIdentityNumber();
            String tinString = String.format("NIP: %s", taxId);
            contact.addElement(createParagraph(tinString));
        }
        return noBorderDecortor(contact);
    }


    private PdfPCell getSellerCell(InvoiceContactModel model, PaymentMethod paymentMethod) {
        PdfPCell contact = getContactCell("Sprzedawca", model);
        if (paymentMethod instanceof PaymentMethodTransfer) {
            String bankAccount = ((PaymentMethodTransfer) paymentMethod).getBankAccount();
            contact.addElement(createParagraph("Konto " + bankAccount));
        }
        return contact;
    }

    private void setItems(Document doc, FinancesInvoiceSectionModel details) throws DocumentException {
        float[] widths = new float[]{0.7f, 10f, 1.1f, 1.5f, 2.5f, 1.5f, 2.7f, 2.7f, 2.9f};
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setWidths(widths);
        setItemTableHeader(table);

        List<InvoiceOrderItemModel> items = details.getItems();
        for (int i = 0; i < items.size(); i++) {
            addItemRow(table, i + 1, items.get(i));
        }
        table.setSpacingBefore(30);
        doc.add(table);
    }

    private void setItemTableHeader(PdfPTable table) {
        table.addCell(createParBorderedCell("Lp"));
        table.addCell(createParBorderedCell("Nazwa"));
        table.addCell(createParBorderedCell("Jm"));
        table.addCell(createParBorderedCell("Ilość"));
        table.addCell(createParBorderedCell("Cena netto"));
        table.addCell(createParBorderedCell("Stawka"));
        table.addCell(createParBorderedCell("Wartość netto"));
        table.addCell(createParBorderedCell("Wartość VAT"));
        table.addCell(createParBorderedCell("Wartość brutto"));
        table.setHeaderRows(1);
    }

    private void addItemRow(PdfPTable table, int lp, InvoiceOrderItemModel item) {
        Font font = smallFont;
        String lpText = Integer.toString(lp);
        String nameText = item.getName();
        String quantityUnitText = item.getQuantityUnit();
        String quantityText = item.getQuantity().toPlainString();
        String taxRateText = Formatters.formatTaxRate(item.getTaxRate());
        String priceNetText = Formatters.formatPrice(item.getPriceNet());
        String netText = Formatters.formatPrice(item.getNet());
        String taxText = Formatters.formatPrice(item.getTax());
        String grossText = Formatters.formatPrice(item.getGross());

        table.addCell(createParBorderedCell(lpText, Element.ALIGN_RIGHT, font));
        table.addCell(createParBorderedCell(nameText, Element.ALIGN_LEFT, font));
        table.addCell(createParBorderedCell(quantityUnitText, font));
        table.addCell(createParBorderedCell(quantityText, Element.ALIGN_RIGHT, font));
        table.addCell(createParBorderedCell(priceNetText, Element.ALIGN_RIGHT, font));
        table.addCell(createParBorderedCell(taxRateText, Element.ALIGN_RIGHT, font));
        table.addCell(createParBorderedCell(netText, Element.ALIGN_RIGHT, font));
        table.addCell(createParBorderedCell(taxText, Element.ALIGN_RIGHT, font));
        table.addCell(createParBorderedCell(grossText, Element.ALIGN_RIGHT, font));
    }

    private void setSummary(Document doc, FinancesInvoiceSectionModel details) throws DocumentException {
        float[] widths = new float[]{2, 1, 0.3f, 0.7f};
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);
        table.setWidths(widths);

        String totalGross = Formatters.formatPrice(details.getTotalAmount().getGross(), BaseEnvironment.CURRENCY);
        table.addCell(createSummaryTable(details));
        table.addCell(getEmptyCell());
        table.addCell(createParCell("Razem", Element.ALIGN_LEFT));
        table.addCell(createParCell(totalGross, Element.ALIGN_RIGHT, defaultBoldFont));
        doc.add(table);
    }

    private void setTotalOfGroups(PdfPTable table, FinancesInvoiceSectionModel finances) {
        AmountModel total = finances.getTotalAmount();
        String netText = Formatters.formatPrice(total.getNet());
        String taxText = Formatters.formatPrice(total.getTax());
        String grossText = Formatters.formatPrice(total.getGross());

        table.addCell(createParBorderedCell("Razem"));
        table.addCell(createParBorderedCell(netText, Element.ALIGN_RIGHT));
        table.addCell(createParBorderedCell(taxText, Element.ALIGN_RIGHT));
        table.addCell(createParBorderedCell(grossText, Element.ALIGN_RIGHT));
    }

    private PdfPCell createSummaryTable(FinancesInvoiceSectionModel details) {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        setSummaryTableHeader(table);
        List<TaxGroupAmountModel> groups = details.getTaxGroups();
        groups.sort(Comparator.comparing(TaxGroupAmountModel::getTaxRate));
        groups.forEach(group -> addGroup(table, group));
        setTotalOfGroups(table, details);
        return new PdfPCell(table);
    }


    private void addGroup(PdfPTable table, TaxGroupAmountModel group) {
        Font font = smallFont;
        String taxRateText = Formatters.formatTaxRate(group.getTaxRate());
        String netText = Formatters.formatPrice(group.getNet());
        String taxText = Formatters.formatPrice(group.getTax());
        String grossText = Formatters.formatPrice(group.getGross());

        table.addCell(createParBorderedCell(taxRateText, font));
        table.addCell(createParBorderedCell(netText, Element.ALIGN_RIGHT, font));
        table.addCell(createParBorderedCell(taxText, Element.ALIGN_RIGHT, font));
        table.addCell(createParBorderedCell(grossText, Element.ALIGN_RIGHT, font));
    }

    private void setSummaryTableHeader(PdfPTable table) {
        table.addCell(createParBorderedCell("Stawka VAT"));
        table.addCell(createParBorderedCell("Wartość netto"));
        table.addCell(createParBorderedCell("Wartość VAT"));
        table.addCell(createParBorderedCell("Wartość brutto"));
        table.setHeaderRows(1);
    }

    private void setSignFields(Document doc) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(70);

        table.addCell(createParCell("Sprzedawca"));
        table.addCell(createParCell("Nabywca"));
        PdfPCell dots = createParCell(".".repeat(60));
        dots.setPaddingTop(25);
        table.addCell(dots);
        table.addCell(dots);
        doc.add(table);
    }


    private PdfPCell createParCell(String text) {
        return createParCell(text, Element.ALIGN_CENTER);
    }

    private PdfPCell createParCell(String text, Font font) {
        return createParCell(text, Element.ALIGN_CENTER, font);
    }

    private PdfPCell createParCell(String text, int textAlignment) {
        return createParCell(text, textAlignment, defaultFont);
    }

    private PdfPCell createParCell(String text, int textAlignment, Font font) {
        return createParCell(text, textAlignment, font, true);
    }

    private PdfPCell createParBorderedCell(String text) {
        return createParBorderedCell(text, Element.ALIGN_CENTER);
    }

    private PdfPCell createParBorderedCell(String text, Font font) {
        return createParBorderedCell(text, Element.ALIGN_CENTER, font);
    }

    private PdfPCell createParBorderedCell(String text, int textAlignment) {
        return createParBorderedCell(text, textAlignment, defaultFont);
    }

    private PdfPCell createParBorderedCell(String text, int textAlignment, Font font) {
        return createParCell(text, textAlignment, font, false);
    }

    private PdfPCell createParCell(String text, int textAlignment, Font font, boolean noBorders) {
        Paragraph paragraph = createParagraph(text, textAlignment, font);
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setPaddingBottom(4);
        cell.setHorizontalAlignment(textAlignment);
        return noBorders ? noBorderDecortor(cell) : cell;
    }

    private Paragraph createParagraph(String text) {
        return createParagraph(text, Element.ALIGN_LEFT);
    }

    private Paragraph createParagraph(String text, Font font) {
        return createParagraph(text, Element.ALIGN_LEFT, font);
    }

    private Paragraph createParagraph(String text, int textAlignment) {
        return createParagraph(text, textAlignment, defaultFont);
    }

    private Paragraph createParagraph(String text, int textAlignment, Font font) {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setAlignment(textAlignment);
        return paragraph;
    }

    private PdfPCell getEmptyCell() {
        PdfPCell empty = new PdfPCell();
        return noBorderDecortor(empty);
    }

    private <T extends Rectangle> T noBorderDecortor(T rectangle) {
        rectangle.setBorder(Rectangle.NO_BORDER);
        return rectangle;
    }
}
