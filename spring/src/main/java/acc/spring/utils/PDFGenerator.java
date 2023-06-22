package acc.spring.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import acc.spring.DTO.ResListMovement;

import static acc.spring.constants.AppConstants.*;
@Component
public class PDFGenerator {
    public void createReport(ResListMovement movementsReport) throws FileNotFoundException, DocumentException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(REPORT_URL_NAME));

        document.open();
        addHeader(document, movementsReport);

        PdfPTable table = new PdfPTable(7);
        addTableHeader(table);
        addRows(table, movementsReport);

        document.add(table);

        document.close();
    }

    private void addHeader(Document document, ResListMovement movementsReport) throws FileNotFoundException, DocumentException {
        Font fontText = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Reporte de movimientos", fontText);
        document.add(chunk);
        document.add(new Paragraph("\n"));
        chunk = new Chunk("Cliente: " + movementsReport.cliente.nombre, fontText);
        document.add(chunk);
        document.add(new Paragraph("\n"));
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Fecha", "Num. Cuenta", "Tipo", "Saldo Inicial", "Estado", "Movimiento",
                "Disponible")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, ResListMovement report) {
        report.movimientos.forEach(movement -> {
            table.addCell(movement.fecha.toString());
            table.addCell(movement.cuentaOrigen.toString());
            table.addCell(movement.tipoMovimiento);
            table.addCell(movement.saldoInicial.toString());
            table.addCell(movement.estado.toString());
            table.addCell(movement.valor.toString());
            table.addCell(movement.saldoDisponible.toString());
        });

    }

}
