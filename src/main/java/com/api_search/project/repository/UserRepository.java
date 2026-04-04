package com.api_search.project.repository;

import com.api_search.project.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = """
        SELECT 
            u.id AS userId,
            u.name AS usuario,
            u.email_to_in AS emailUsuario,

            am.id AS accountId,
            am.email_monitored AS emailMonitorado,
            am.account_monitored AS contaMonitorada,
            am.risk_status AS riskStatus,

            le.email_id AS emailVazado,

            l.id AS leakId,
            l.account_monitored AS siteVazado,
            l.date_occurrence AS dateOccurrence,
            l.register AS totalRegistros,

            a.id AS alertId,
            a.risk_level AS riskLevel,
            a.date_alert AS dateAlert

        FROM user u
        LEFT JOIN accounts_monitored am 
            ON am.user_id = u.id
        LEFT JOIN leakeds_email le 
            ON LOWER(le.email_id) = LOWER(am.email_monitored)
        LEFT JOIN leak l 
            ON l.id = le.id_leak
        LEFT JOIN alert a 
            ON a.user_id = u.id 
            AND LOWER(a.email) = LOWER(am.email_monitored)

        WHERE u.id = :userId
    """, nativeQuery = true)
    List<UserDashboardDTO> buscarDashboard(@Param("userId") Integer userId);

}
