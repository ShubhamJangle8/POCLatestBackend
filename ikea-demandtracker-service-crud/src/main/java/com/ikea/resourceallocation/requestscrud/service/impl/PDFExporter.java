package com.ikea.resourceallocation.requestscrud.service.impl;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import com.ikea.resourceallocation.requestscrud.dto.RequestCreationDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
 
 
public class PDFExporter {
    private List<RequestCreationDto> requests;
     
    public PDFExporter(List<RequestCreationDto> requests) {
        this.requests = requests;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(8);
        cell.setMinimumHeight(10f);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Requestor ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Tech Stack", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Grade Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Last Modified Date", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Cluster Name", font));
        table.addCell(cell);     
        
        cell.setPhrase(new Phrase("Sub Cluster Name", font));
        table.addCell(cell);     
        
        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);     
        
        cell.setPhrase(new Phrase("Owner Name", font));
        table.addCell(cell);     
    }
     
    private void writeTableData(PdfPTable table) {
        for (RequestCreationDto request : requests) {
            table.addCell(String.valueOf(request.getReqId()));
            table.addCell(request.getTechStack());
            table.addCell(request.getGradeId());
            table.addCell(request.getLastModifiedDate());
            table.addCell(request.getClusterName());
            table.addCell(request.getSubClusterName());
            table.addCell(request.getStatus());
            table.addCell(request.getOwnerName());
        
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A3);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(24);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Requests", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.5f, 2f, 1f, 3.0f, 3f , 3f ,1f , 2.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}