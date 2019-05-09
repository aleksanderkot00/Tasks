package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;

    @Value("${company.name}")
    private String companyName;

    @Value("${company.phone}")
    private String companyPhone;

    @Value("${company.address.city}")
    private String companyCity;

    @Value("${company.address.street}")
    private String companyStreet;

    @Value("${company.address.country}")
    private String companyCountry;

    public String getCompanyDetails() {
        return companyName + ", " +
                companyStreet + ", " +
                companyCity + ", " +
                companyCountry + ", " +
                "tel: " + companyPhone;
    }
}