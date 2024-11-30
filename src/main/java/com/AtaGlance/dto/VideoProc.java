package com.AtaGlance.dto;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoProc {
    private Integer videoId;

    @NotNull
    private Integer newsId;

    @NotNull
    private Integer textId;

    private List<String> objectsPath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Constructor
    public VideoProc(Integer videoId, Integer newsId, Integer textId,
                     List<String> objectsPath, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.videoId = videoId;
        this.newsId = newsId;
        this.textId = textId;
        this.objectsPath = objectsPath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
}
