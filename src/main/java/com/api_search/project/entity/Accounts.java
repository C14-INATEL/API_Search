package com.api_search.project.entity;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "accounts_monitored")
@Audited
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email_monitored")
    private String emailMonitored;

    @Column(name = "name_breaches")
    private String nameBreaches;

    @Column(name = "title")
    private String title;

    @Column(name = "domain")
    private String domain;

    @Column(name = "logo_path")
    private String logoPath;

    @Column(name = "attribution", columnDefinition = "TEXT")
    private String attribution;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "pwn_count")
    private Integer pwnCount;

    @Column(name = "breach_date")
    private LocalDate breachDate;

    @Column(name = "added_date")
    private OffsetDateTime addedDate;

    @Column(name = "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name = "data_classes")
    private List<String> dataClasses;

    @Column(name = "is_verified")
    private boolean isVerified;

    @Column(name = "is_fabricated")
    private boolean isFabricated;

    @Column(name = "is_sensitive")
    private boolean isSsensitive;

    @Column(name = "is_retired")
    private boolean isRetired;

    @Column(name = "is_spam_list")
    private boolean isSpamList;

    @Column(name = "is_malware")
    private boolean isMalware;

    @Column(name = "is_subscription_free")
    private boolean isSubscriptionFree;

    @Column(name = "is_stealer_log")
    private boolean isStealerLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmailMonitored() {
        return emailMonitored;
    }

    public void setEmailMonitored(String emailMonitored) {
        this.emailMonitored = emailMonitored;
    }

    public String getNameBreaches() {
        return nameBreaches;
    }

    public void setNameBreaches(String nameBreaches) {
        this.nameBreaches = nameBreaches;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPwnCount() {
        return pwnCount;
    }

    public void setPwnCount(Integer pwnCount) {
        this.pwnCount = pwnCount;
    }

    public LocalDate getBreachDate() {
        return breachDate;
    }

    public void setBreachDate(LocalDate breachDate) {
        this.breachDate = breachDate;
    }

    public OffsetDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(OffsetDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public OffsetDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(OffsetDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<String> getDataClasses() {
        return dataClasses;
    }

    public void setDataClasses(List<String> dataClasses) {
        this.dataClasses = dataClasses;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isFabricated() {
        return isFabricated;
    }

    public void setFabricated(boolean fabricated) {
        isFabricated = fabricated;
    }

    public boolean isSsensitive() {
        return isSsensitive;
    }

    public void setSsensitive(boolean ssensitive) {
        isSsensitive = ssensitive;
    }

    public boolean isRetired() {
        return isRetired;
    }

    public void setRetired(boolean retired) {
        isRetired = retired;
    }

    public boolean isSpamList() {
        return isSpamList;
    }

    public void setSpamList(boolean spamList) {
        isSpamList = spamList;
    }

    public boolean isMalware() {
        return isMalware;
    }

    public void setMalware(boolean malware) {
        isMalware = malware;
    }

    public boolean isSubscriptionFree() {
        return isSubscriptionFree;
    }

    public void setSubscriptionFree(boolean subscriptionFree) {
        isSubscriptionFree = subscriptionFree;
    }

    public boolean isStealerLog() {
        return isStealerLog;
    }

    public void setStealerLog(boolean stealerLog) {
        isStealerLog = stealerLog;
    }
}
