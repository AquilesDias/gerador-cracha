package com.aquilesdias.geradorcracha.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgeData {

    private String name;
    private String jogTitle;
    private String nameCompany;
    private byte[] photo;
}
