package com.AtaGlance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class News {
    private Integer newsId;
    @NotNull
    private Integer sourceBc;
    @NotNull
    private String sourceUrl;
    private String videoFilePath;
    private String title;
    private Integer category;
    private String cardsPath;
    private String thumbnail;
    private LocalDateTime newsAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor
    public News() {}
    public News(Integer sourceBc, String sourceUrl, String title,
                Integer category, LocalDateTime newsAt) {
        this.sourceBc = sourceBc;
        this.sourceUrl = sourceUrl;
        this.title = title;
        this.category = category;
        this.newsAt = newsAt;
    }

    public News(Integer sourceBc, String sourceUrl,
                Integer category) {
        this.sourceBc = sourceBc;
        this.sourceUrl = sourceUrl;
        this.category = category;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", sourceBc=" + sourceBc +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category +
                '}';
    }
}
