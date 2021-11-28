package com.biclast.jasper.reports.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NonNull
    private String city;
    private String country;
    private String phone;
    @NonNull
    private String type;
}
