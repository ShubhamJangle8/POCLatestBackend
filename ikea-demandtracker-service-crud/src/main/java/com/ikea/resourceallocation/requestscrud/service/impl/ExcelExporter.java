package com.ikea.resourceallocation.requestscrud.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ikea.resourceallocation.requestscrud.dto.RequestCreationDto;
 
public class ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<RequestCreationDto> requestDataList;
     
    public ExcelExporter(List<RequestCreationDto> requestData) {
        this.requestDataList = requestData;
        System.out.println(requestData + "**********************");
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Requests");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "ID", style);      
        createCell(row, 1, "Technology", style);       
        createCell(row, 2, "Grade", style);    
        createCell(row, 3, "Updated", style);
        createCell(row, 4, "Cluster", style);
        createCell(row, 5, "Sub Cluster", style);
        createCell(row, 6, "Status", style);
        createCell(row, 7, "Owner", style);
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (RequestCreationDto request : requestDataList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
//             System.out.println(request.getClusterId() + request.getGradeId());
            createCell(row, columnCount++, request.getReqId(), style);
            createCell(row, columnCount++, request.getTechStack(), style);
            createCell(row, columnCount++, request.getGradeId(), style);
            createCell(row, columnCount++, request.getLastModifiedDate(), style);
            createCell(row, columnCount++, request.getClusterName(), style);
            createCell(row, columnCount++, request.getSubClusterName(), style);
            createCell(row, columnCount++, request.getStatus(), style);
            createCell(row, columnCount++, request.getOwnerName(), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}