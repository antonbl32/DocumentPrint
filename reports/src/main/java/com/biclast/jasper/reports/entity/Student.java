package com.biclast.jasper.reports.entity;

import dto.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private String name;
    private String soname;
    private List<Address> address;
}

