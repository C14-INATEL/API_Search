package com.api_search.project.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("IsVerified")
    private boolean IsVerified;

    @JsonProperty("IsFabricated")
    private boolean IsFabricated;

    @JsonProperty("IsSensitive")
    private boolean IsSensitive;

    @JsonProperty("IsRetired")
    private boolean IsRetired;

    @JsonProperty("IsSpamList")
    private boolean IsSpamList;

    @JsonProperty("IsMalware")
    private boolean IsMalware;

    @JsonProperty("IsSubscriptionFree")
    private boolean IsSubscriptionFree;

    @JsonProperty("IsStealerLog")
    private boolean IsStealerLog;

    // getters and setters

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }

    public String getLogoPath() {
        return LogoPath;
    }

    public void setLogoPath(String logoPath) {
        LogoPath = logoPath;
    }

    public String getAttribution() {
        return Attribution;
    }

    public void setAttribution(String attribution) {
        Attribution = attribution;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getPwnCount() {
        return PwnCount;
    }

    public void setPwnCount(Integer pwnCount) {
        PwnCount = pwnCount;
    }

    public LocalDate getBreachDate() {
        return BreachDate;
    }

    public void setBreachDate(LocalDate breachDate) {
        BreachDate = breachDate;
    }

    public OffsetDateTime getAddedDate() {
        return AddedDate;
    }

    public void setAddedDate(OffsetDateTime addedDate) {
        AddedDate = addedDate;
    }

    public OffsetDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(OffsetDateTime modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public List<String> getDataClasses() {
        return DataClasses;
    }

    public void setDataClasses(List<String> dataClasses) {
        DataClasses = dataClasses;
    }

    public boolean isVerified() {
        return IsVerified;
    }

    public void setVerified(boolean verified) {
        IsVerified = verified;
    }

    public boolean isFabricated() {
        return IsFabricated;
    }

    public void setFabricated(boolean fabricated) {
        IsFabricated = fabricated;
    }

    public boolean isSensitive() {
        return IsSensitive;
    }

    public void setSensitive(boolean sensitive) {
        IsSensitive = sensitive;
    }

    public boolean isRetired() {
        return IsRetired;
    }

    public void setRetired(boolean retired) {
        IsRetired = retired;
    }

    public boolean isSpamList() {
        return IsSpamList;
    }

    public void setSpamList(boolean spamList) {
        IsSpamList = spamList;
    }

    public boolean isMalware() {
        return IsMalware;
    }

    public void setMalware(boolean malware) {
        IsMalware = malware;
    }

    public boolean isSubscriptionFree() {
        return IsSubscriptionFree;
    }

    public void setSubscriptionFree(boolean subscriptionFree) {
        IsSubscriptionFree = subscriptionFree;
    }

    public boolean isStealerLog() {
        return IsStealerLog;
    }

    public void setStealerLog(boolean stealerLog) {
        IsStealerLog = stealerLog;
    }
}