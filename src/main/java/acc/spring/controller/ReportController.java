package acc.spring.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.core.io.FileSystemResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acc.spring.DTO.MovementDto;
import acc.spring.services.IMovementsService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/reporte")
@AllArgsConstructor
public class ReportController {
    IMovementsService movementsService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getMovementsByAccount(
            @RequestParam Long accountId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date fechaFin)
            throws Exception {
        MovementDto movementDto = new MovementDto();
        if (fechaInicio != null)
            movementDto.fechaInicio = new Timestamp(fechaInicio.getTime());
        if (fechaFin != null)
            movementDto.fechaFin = new Timestamp(fechaFin.getTime());

        FileSystemResource pdfReport = movementsService.createMovementPDFReport(accountId, movementDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(pdfReport);
    }
}
