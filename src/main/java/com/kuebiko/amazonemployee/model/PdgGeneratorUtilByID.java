package com.kuebiko.amazonemployee.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.kuebiko.amazonemployee.dto_entity.EmployeeDTO;

import java.io.ByteArrayOutputStream;

public class PdgGeneratorUtilByID {

    //Getmapping---get employee details in pdf files using ID
    //http://localhost:8080/employee/action/convert-pdf-file/7
    public static byte[] generateEmployeeDetailsPDFByID(EmployeeDTO employee1) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // No need to iterate, as you're working with a single EmployeeDTO
            // Iterate over a list of EmployeeDTO if you're expecting multiple employees
            //for (EmployeeDTO employee : employee1) {
                //System.out.println("Employee_Details");
                document.add(new Paragraph("   Amazon Morning Shift Employees Details"));
                document.add(new Paragraph("              Baltimore MD USA"));
                document.add(new Paragraph("----------------------------------------------------------"));
                document.add(new Paragraph("\n")); //Break the line............
                document.add(new Paragraph("Employee Batch ID: " + employee1.getEmpBatchID()));
                document.add(new Paragraph("First Name: " + employee1.getFirstName()));
                document.add(new Paragraph("Last Name: " + employee1.getLastName()));
                document.add(new Paragraph("Date of Birth: " + employee1.getDob()));
                document.add(new Paragraph("Age: " + employee1.getAge()));
                document.add(new Paragraph("Position: " + employee1.getPosition()));
                document.add(new Paragraph("Phone Number: " + employee1.getPhoneNumber()));
                document.add(new Paragraph("Email: " + employee1.getEmail()));
                document.add(new Paragraph("Address: " + employee1.getAddress()));
                document.add(new Paragraph("Gender: " + employee1.getGender()));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("----------------------------------------------------------"));
                document.add(new Paragraph("   More Info@www.amazonemp.com"));
                document.add(new Paragraph("          Thank you!!!"));
                document.add(new Paragraph("\n"));
            //}

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
