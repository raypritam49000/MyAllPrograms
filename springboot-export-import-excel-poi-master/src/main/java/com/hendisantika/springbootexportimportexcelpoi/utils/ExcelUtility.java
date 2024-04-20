package com.hendisantika.springbootexportimportexcelpoi.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelUtility<T> {

	@SuppressWarnings("unused")
	public ByteArrayInputStream exportExcel(List<T> entities, String[] columns) throws Exception {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Data");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (T entity : entities) {
				Row row = sheet.createRow(rowIdx);
				populateRow(row, entity);
				rowIdx++;
			}

			workbook.write(out);
			workbook.close();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void populateRow(Row row, T entity) {
		Class<?> entityClass = entity.getClass();
		Field[] fields = entityClass.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			Cell cell = row.createCell(i);
			try {
				Object value = fields[i].get(entity);
				setCellValue(cell, value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private void setCellValue(Cell cell, Object value) {
		if (value == null) {
			return;
		}

		if (value instanceof String) {
			cell.setCellValue((String) value);
		} else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof Date) {
			cell.setCellValue((Date) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue(value.toString());
		}
	}
}
