package com.api_search.project.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface UserDashboardDTO {
    Integer getUserId();
    String getUsuario();
    String getEmailUsuario();

    Integer getAccountId();
    String getEmailMonitorado();
    String getContaMonitorada();
    String getRiskStatus();

    String getEmailVazado();

    Integer getLeakId();
    String getSiteVazado();
    LocalDate getDateOccurrence();
    Long getTotalRegistros();

    Integer getAlertId();
    String getRiskLevel();
    LocalDateTime getDateAlert();
}
