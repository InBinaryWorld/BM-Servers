package dev.szafraniak.bmresource.services.action;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dev.szafraniak.bmresource.model.action.CreateInvoiceModel;
import dev.szafraniak.bmresource.model.action.InvoiceContactModel;
import dev.szafraniak.bmresource.model.action.InvoiceDetailsModel;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class InvoiceDocGeneratorIText implements InvoiceDocGenerator {

    Font font10;
    Font font12;
    Font font14;
    Font fontBold12;
    Font fontBold14;
    Font fontBold16;
    Font fontBold18;
    Font fontBold24;
    DateTimeFormatter fmt;

    public InvoiceDocGeneratorIText() {
        fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        font10 = FontFactory.getFont(FontFactory.TIMES, 10);
        font12 = FontFactory.getFont(FontFactory.TIMES, 12);
        font14 = FontFactory.getFont(FontFactory.TIMES, 14);
        fontBold12 = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
        fontBold14 = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);
        fontBold16 = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
        fontBold18 = FontFactory.getFont(FontFactory.TIMES_BOLD, 18);
        fontBold24 = FontFactory.getFont(FontFactory.TIMES_BOLD, 24);
    }

    public void createInvoice(CreateInvoiceModel model, InvoiceDetailsModel details, String outputFile) throws FileNotFoundException, DocumentException {
        FileOutputStream os = new FileOutputStream(outputFile);
        Document doc = new Document(PageSize.A4);
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, os);
        pdfWriter.setPdfVersion(PdfWriter.VERSION_1_7);
        doc.open();
        setBaseData(doc, model);
        doc.close();
    }

    private void setBaseData(Document doc, CreateInvoiceModel model) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{5f, 3f});

        PdfPCell logoCell = getLogoCell();
        PdfPCell BaseDataCell = getBaseDataCell(model);
        table.addCell(logoCell);
        table.addCell(BaseDataCell);

        addBlankRow(table, 12);

        PdfPCell sellerCell = getContactCell("Sprzedawca", model.getSeller());
        PdfPCell buyerCell = getContactCell("Nabywca", model.getBuyer());
        table.addCell(sellerCell);
        table.addCell(buyerCell);

        addBlankRow(table, 4);

        if (model.getReceiver() != null) {
            PdfPCell receiverCell = getContactCell("Odbiorca", model.getReceiver());
            table.addCell(getEmptyCell());
            table.addCell(receiverCell);
        }

        doc.add(table);
    }


    private PdfPCell getLogoCell() {
        PdfPCell logo = new PdfPCell();
        logo.setBorder(PdfPCell.NO_BORDER);
        logo.setHorizontalAlignment(Element.ALIGN_CENTER);
        logo.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Paragraph paragraph = new Paragraph("LOGO", fontBold24);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        logo.addElement(paragraph);
        return logo;
    }

    private PdfPCell getEmptyCell() {
        PdfPCell empty = new PdfPCell();
        empty.setBorder(PdfPCell.NO_BORDER);
        return empty;
    }

    private PdfPCell getBaseDataCell(CreateInvoiceModel model) {
        String creationDate = model.getCreationDate().format(fmt);
        String dueDate = model.getDueDate().format(fmt);
        String paymentMethod = model.getBankAccount() == null ? "Gotowka" : "Przelew";


        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell invName = new PdfPCell();
        invName.setColspan(2);
        invName.setBorder(PdfPCell.NO_BORDER);
        String invoiceNameString = "Faktura VAT nr " + model.getInvoiceName();
        invName.addElement(new Paragraph(invoiceNameString, fontBold14));

        Font descFont = font12;
        PdfPCell desc = new PdfPCell();
        desc.setBorder(PdfPCell.NO_BORDER);
        desc.addElement(new Paragraph("Data sprzedazy:", descFont));
        desc.addElement(new Paragraph("Termin zaplaty:", descFont));
        desc.addElement(new Paragraph("Metoda platnosci:", descFont));

        Font valFont = font12;
        PdfPCell val = new PdfPCell();
        val.setBorder(PdfPCell.NO_BORDER);
        val.addElement(new Paragraph(creationDate, valFont));
        val.addElement(new Paragraph(dueDate, valFont));
        val.addElement(new Paragraph(paymentMethod, valFont));

        table.addCell(invName);
        table.addCell(desc);
        table.addCell(val);
        PdfPCell cell = new PdfPCell(table);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell getContactCell(String title, InvoiceContactModel model) {
        Font font = font12;
        PdfPCell contact = new PdfPCell();
        contact.setBorder(PdfPCell.NO_BORDER);

        contact.addElement(new Paragraph(title, fontBold14));
        contact.addElement(new Paragraph(model.getName(), font));
        if (model.getTaxIdentityNumber() != null) {
            String tinString = "NIP: " + model.getTaxIdentityNumber();
            contact.addElement(new Paragraph(tinString, font));
        }
        for (String addressRow : model.getAddressRows()) {
            contact.addElement(new Paragraph(addressRow, font));
        }
        return contact;
    }

    private void addBlankRow(PdfPTable table, float height) {
        PdfPCell blankRow = new PdfPCell();
        blankRow.setMinimumHeight(height);
        blankRow.setColspan(table.getNumberOfColumns());
        blankRow.setBorder(Rectangle.NO_BORDER);
        table.addCell(blankRow);
    }

}
