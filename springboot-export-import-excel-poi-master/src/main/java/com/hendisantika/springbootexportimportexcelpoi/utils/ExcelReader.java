package com.hendisantika.springbootexportimportexcelpoi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static List<Map<String, String>> readExcel(String filePath) throws IOException {
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        FileInputStream fis = new FileInputStream(filePath);

        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);
        int columns = headerRow.getLastCellNum();
        String[] headers = new String[columns];

        for (int i = 0; i < columns; i++) {
            Cell cell = headerRow.getCell(i);
            headers[i] = cell.getStringCellValue();
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> rowData = new HashMap<>();

            for (int j = 0; j < columns; j++) {
                Cell cell = row.getCell(j);
                String cellValue = "";

                if (cell != null) {
                    if (cell.getCellType() == CellType.STRING) {
                        cellValue = cell.getStringCellValue();
                    } else if (cell.getCellType() == CellType.NUMERIC) {

                        if (DateUtil.isCellDateFormatted(cell)) {
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            cellValue = df.format(cell.getDateCellValue());
                        }
                        else{
                            cellValue = String.format("%.0f", cell.getNumericCellValue());
                        }

                    } else if (cell.getCellType() == CellType.BOOLEAN) {
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                    } else {
                        cellValue = cell.getStringCellValue();
                    }
                }
                if (!headers[j].isEmpty()) {
                    rowData.put(headers[j], cellValue);
                }
            }

            dataList.add(rowData);
        }
        workbook.close();
        fis.close();
        return dataList;
    }

    public static void main(String[] args) {
        try {
            List<Map<String, String>> data = ExcelReader.readExcel("/Users/pritamray/MyAllProgram/eclipse-workspace/springboot-export-import-excel-poi-master/src/main/resources/users_2021-09-20_07_18_27.xlsx");
            System.out.println("==== Data ==== "+data);
            for (Map<String, String> row : data) {
                System.out.println(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}