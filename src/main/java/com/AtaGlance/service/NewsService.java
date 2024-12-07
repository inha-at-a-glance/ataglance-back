package com.AtaGlance.service;

import com.AtaGlance.dto.LambdaPayload;
import com.AtaGlance.dto.News;
import com.AtaGlance.repository.mybatis.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService{
    private final NewsMapper newsMapper;

    public NewsService(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }
    public News saveNews(News news) {
        try {
            // YouTube 제목 크롤링 (메타데이터 방식) 후 '/' 이후 제거
            String videoTitle = YoutubeCrawler.getVideoTitleFromMeta(news.getSourceUrl());
            LocalDateTime publishedDate = YoutubeCrawler.getVideoPublishedDate(news.getSourceUrl());
            // 제목과 게시 날짜 설정
            news.setTitle(videoTitle);
            news.setNewsAt(publishedDate);
        } catch (IOException | IllegalArgumentException e) {
            // 에러 발생 시 로그 출력 및 기본 제목 설정
            System.err.println("Error fetching video title: " + e.getMessage());
            news.setTitle("Untitled Video");
        }

        // 생성 및 수정 시간 설정
        news.setCreatedAt(LocalDateTime.now());
        news.setUpdatedAt(LocalDateTime.now());

        // 뉴스 저장
        if (newsMapper.existsBySourceUrl(news.getSourceUrl())) {
            throw new IllegalArgumentException("The source URL already exists: " + news.getSourceUrl());
        }
        int rowsAffected = newsMapper.saveNews(news);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to save news");
        }

        return news;
    }

    public List<News> getAllNews() {
        List<News> newsList = newsMapper.getAllNews();

        // Log the result from the mapper
        return newsList;
    }

    public List<News> getNewsByCategory(int category) {
        return newsMapper.getNewsByCategory(category);
    }

    public News getNewsById(int newsId) {
        return newsMapper.getNewsById(newsId);
    }
}
