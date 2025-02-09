package com.example.rest_api.service;

import com.example.rest_api.dto.TaskDTO;

import java.io.IOException;
import java.util.List;

public interface ExcelExportService {
    byte[] exportTasksToExcel(List<TaskDTO> tasks) throws IOException;
}
