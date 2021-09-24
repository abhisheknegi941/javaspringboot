package com.nagarro.hrmanagerapi.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nagarro.hrmanagerapi.model.Employee;

public class EmployeePDFExporter {
	private List<Employee> empList;

	public EmployeePDFExporter(List<Employee> empList) {
		this.empList = empList;
	}
    
	/*Method to set pdf column names and other properties*/
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.RED);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.TIMES);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Employee Code", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Employee Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Location", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Date of Birth", font));
		table.addCell(cell);
	}
   
	/*Method to write data to pdf table*/
	private void writeTableData(PdfPTable table) {

		empList.forEach(e -> {
			table.addCell(String.valueOf(e.getEmpCode()));
			table.addCell(e.getEmpName());
			table.addCell(e.getEmpEmail());
			table.addCell(e.getEmpLocation());
			table.addCell(String.valueOf(e.getEmpDob()));
		});

	}

	/*Method to export*/
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
		font.setSize(4);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("List of Employees", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.7f, 3.5f, 3.0f, 3.0f, 1.5f });
		table.setSpacingBefore(7);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);
		document.close();
	}
}
