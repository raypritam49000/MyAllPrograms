package com.hendisantika.springbootexportimportexcelpoi.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import jakarta.servlet.http.HttpServletResponse;

public class CsvExporter<T> {

	public void exportToCsv(HttpServletResponse response, String fileName, List<T> data, String[] csvHeader,
			String[] nameMapping) throws IOException {
		response.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + fileName + "_" + currentDateTime + ".csv";
		response.setHeader(headerKey, headerValue);

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		csvWriter.writeHeader(csvHeader);

		for (T entity : data) {
			csvWriter.write(entity, nameMapping);
		}

		csvWriter.close();
	}
}
