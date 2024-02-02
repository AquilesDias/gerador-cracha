package com.aquilesdias.geradorcracha.service;

import com.aquilesdias.geradorcracha.domain.BadgeDataDTO;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class BadgeService {

    public byte[] generateBadge(BadgeDataDTO dto){

        InputStream inputStream = this.getClass().getResourceAsStream("/reports/cracha.jasper");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("NAME_COMPANY", dto.nameCompany());
        parameters.put("PHOTO", dto.photo());
        parameters.put("NAME", dto.name());
        parameters.put("JOB_TITLE", dto.jobTitle());

        try {
            // Preencher o relatório e exportar para PDF
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            // Lidar com exceções, se necessário
            e.printStackTrace();
            return null;
        }

    }
}
