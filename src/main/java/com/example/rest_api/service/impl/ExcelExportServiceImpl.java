package com.example.rest_api.service.impl;

import com.example.rest_api.dto.TaskDTO;
import com.example.rest_api.service.ExcelExportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Override
    public byte[] exportTasksToExcel(List<TaskDTO> tasks) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tasks");
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Project", "Description", "Start Time", "End Time", "Priority", "Status", "Person"};

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            cell.setCellStyle(headerStyle);
        }

        // Ghi dữ liệu vào file Excel
        int rowNum = 1;
        for (TaskDTO task : tasks) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(task.getProject().getName());
            row.createCell(1).setCellValue(task.getDescription());
            row.createCell(2).setCellValue(task.getStartTime().toString());
            row.createCell(3).setCellValue(task.getEndTime().toString());
            row.createCell(4).setCellValue(task.getPriority().name());
            row.createCell(5).setCellValue(task.getStatus().name());
            row.createCell(6).setCellValue(task.getPerson().getFullName());
        }

        // Ghi dữ liệu ra byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();

    }
}
