package com.biclast.jasper.reports.service;

import dto.DocumentDTO;
import com.biclast.jasper.reports.entity.Document;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class JasperReportsService {


    public byte[] generatePDF(Document document) throws IOException, JRException {
        ClassLoader classLoader = getClass().getClassLoader();
        String resource = classLoader.getResource("master.jasper").getPath();
        String resourceSub = classLoader.getResource("subMaster.jasper").getPath();
        String resourceSub1 = classLoader.getResource("subMaster2.jasper").getPath();
        JasperReport jasperReport= (JasperReport) JRLoader.loadObjectFromFile(resource);
        JasperReport jasperReportSub= (JasperReport) JRLoader.loadObjectFromFile(resourceSub);
        JasperReport jasperReportSub1= (JasperReport) JRLoader.loadObjectFromFile(resourceSub1);
        DocumentDTO documentDTO=MapToDTO.map(document);
        Map<String, Object> map = new HashMap<>();
        map.put("subMaster",jasperReportSub);
        map.put("subMaster2",jasperReportSub1);
        List<DocumentDTO> documentDTOList=new ArrayList<>();
        documentDTOList.add(documentDTO);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(documentDTOList);
        JasperPrint report = JasperFillManager.fillReport(jasperReport, map, dataSource);
        String pdfName = documentDTO.getTitle() + "_" + "ANTONS" + ".pdf";
        byte[] pdf= JasperExportManager.exportReportToPdf(report);
        return pdf;
    }
}
