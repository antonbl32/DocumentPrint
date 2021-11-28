package com.biclast.jasper.reports.controller;

import com.biclast.jasper.reports.entity.Document;
import com.biclast.jasper.reports.service.JasperReportsService;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import dto.DocumentDTO;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ExportController {

    @Autowired
    private ServletContext context;

    @Autowired
    private JasperReportsService jasperReportsService;

    @RequestMapping(value = "/report", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> download(@RequestBody Document document) throws IOException, JRException {
        byte[] fileName = jasperReportsService.generatePDF(document);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename.length" + fileName.length);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        //headers.setContentLength(pdfFile.contentLength());
        ResponseEntity<InputStreamResource> response = new ResponseEntity<>(
                new InputStreamResource(new ByteArrayInputStream(fileName)), headers, HttpStatus.OK);
        return response;
    }
}