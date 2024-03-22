package com.kuebiko.amazonemployee.model;

import com.kuebiko.amazonemployee.dto_entity.EmployeeDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfGeneratorUtil {

    //@Getmapping--http://localhost:8080/employee/action/convert-pdf-file
    //If you put this url in Browser you will get pdf file-----employee_details.pdf
    //Connect ot the service and controller-------------------------
    public static byte[] generateEmployeeDetailsPDF(List<EmployeeDTO> employees) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            for (EmployeeDTO employee : employees) {
                document.add(new Paragraph("Employee Batch ID: " + employee.getEmpBatchID()));
                document.add(new Paragraph("First Name: " + employee.getFirstName()));
                document.add(new Paragraph("Last Name: " + employee.getLastName()));
                document.add(new Paragraph("Date of Birth: " + employee.getDob()));
                document.add(new Paragraph("Age: " + employee.getAge()));
                document.add(new Paragraph("Position: " + employee.getPosition()));
                document.add(new Paragraph("Phone Number: " + employee.getPhoneNumber()));
                document.add(new Paragraph("Email: " + employee.getEmail()));
                document.add(new Paragraph("Address: " + employee.getAddress()));
                document.add(new Paragraph("Gender: " + employee.getGender()));
                document.add(new Paragraph("\n"));
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
