package org.example.studymaterial;

import java.util.Map;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

     // Add methods for common operations
     public Reference withTitle(String title) {
        this.title = title;
        return this;
    }

    public Reference withDescription(String description) {
        this.description = description;
        return this;
    }

    public Reference withLink(String link) {
        this.link = link;
        return this;
    }

    public Reference withAccessRights(String accessRights) {
        this.accessRights = accessRights;
        return this;
    }

    public Reference withLicense(String license) {
        this.license = license;
        return this;
    }

    public Reference withIsDownloadable(boolean isDownloadable) {
        this.isDownloadable = isDownloadable;
        return this;
    }

    public Reference withRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Reference withLanguage(String language) {
        this.language = language;
        return this;
    }

    public Reference withViewCount(int viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public Reference withDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
        return this;
    }

    public Reference withShareCount(int shareCount) {
        this.shareCount = shareCount;
        return this;
    }

    // Add methods for common actions
    public void incrementViewCount() {
        this.viewCount++;
    }

    public void incrementDownloadCount() {
        this.downloadCount++;
    }

    public void incrementShareCount() {
        this.shareCount++;
    }

    public abstract void incrementCount(Map<String, Integer> referenceCount);
}
