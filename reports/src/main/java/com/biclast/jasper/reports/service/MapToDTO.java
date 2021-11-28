package com.biclast.jasper.reports.service;

import com.biclast.jasper.reports.entity.Document;
import dto.Address;
import dto.DocumentDTO;
import dto.StudentDTO;

import java.util.stream.Collectors;

public class MapToDTO {
    public static DocumentDTO map(Document document) {
        DocumentDTO documentDTO = DocumentDTO.builder().title(document.getTitle()).type(document.getType())
                .allStudents(document.getStudentList().stream().map(a -> StudentDTO.builder().name(a.getName())
                                .soname(a.getSoname()).address(a.getAddress().stream().map(b ->
                                        Address.builder().city(b.getCity()).country(b.getCountry()).phone(b.getPhone()).
                                                type(b.getType()).build()).collect(Collectors.toList())).build())
                        .collect(Collectors.toList())).build();
        return documentDTO;
    }
}
