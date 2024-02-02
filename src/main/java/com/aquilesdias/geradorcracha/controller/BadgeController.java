package com.aquilesdias.geradorcracha.controller;

import com.aquilesdias.geradorcracha.domain.BadgeDataDTO;
import com.aquilesdias.geradorcracha.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("api/badge")
@RestController
public class BadgeController {

    @Autowired
    private BadgeService service;

    @PostMapping
    public ResponseEntity<byte[]> generateBadge(@RequestPart("name") String name,
                                                @RequestPart("jobTitle") String jobTitle,
                                                @RequestPart("nameCompany") String nameCompany,
                                                @RequestPart("photo") MultipartFile photo) throws IOException {

        BadgeDataDTO data = new BadgeDataDTO(name, jobTitle, nameCompany, photo.getBytes());

        byte[] dataJson = service.generateBadge(data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("cracha.pdf").build());
        return ResponseEntity.ok().headers(headers).body(dataJson);
    }
}
