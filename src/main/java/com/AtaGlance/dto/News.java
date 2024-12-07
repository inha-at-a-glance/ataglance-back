package com.AtaGlance.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
