package siara.lukasz.dependency_visualisation.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;
import siara.lukasz.dependency_visualisation.exception.PdfException;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {
    public byte[] convertDtoToPdf(List data, Class clazz) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            for (Object element : data) {
                document.add(new Paragraph(element.toString()));
            }
            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PdfException("Cannot create pdf file");
        }
    }
}
