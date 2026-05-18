package com.api_search.project.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FindByEmailResponse {
    private String Name;
    private String Title;
    private String Domain;
    private String LogoPath;
    private String Attribution;
    private String Description;
    private Integer PwnCount;
    private LocalDate BreachDate;
    private OffsetDateTime AddedDate;
    private OffsetDateTime ModifiedDate;
    private List<String> DataClasses;
    private boolean IsVerified;
    private boolean IsFabricated;
    private boolean IsSensitive;
    private boolean IsRetired;
    private boolean IsSpamList;
    private boolean IsMalware;
    private boolean IsSubscriptionFree;
    private boolean IsStealerLog;
}
