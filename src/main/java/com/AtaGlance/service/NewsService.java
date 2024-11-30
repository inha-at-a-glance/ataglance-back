package com.AtaGlance.service;

import com.AtaGlance.dto.LambdaPayload;
import com.AtaGlance.dto.News;
import com.AtaGlance.repository.mybatis.NewsMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService implements NewsMapper{
    private final NewsMapper newsMapper;

    public NewsService(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }
    public void saveNews(News news) {
        try {
            // YouTube 제목 크롤링 (메타데이터 방식) 후 '/' 이후 제거
            String videoTitle = YoutubeCrawler.getVideoTitleFromMeta(news.getSourceUrl());
            news.setTitle(videoTitle);
        } catch (IOException | IllegalArgumentException e) {
            // 에러 발생 시 로그 출력 및 기본 제목 설정
            System.err.println("Error fetching video title: " + e.getMessage());
            news.setTitle("Untitled Video");
        }

        // 생성 및 수정 시간 설정
        news.setCreatedAt(LocalDateTime.now());
        news.setUpdatedAt(LocalDateTime.now());

        // 뉴스 저장
        newsMapper.saveNews(news);

//        LambdaInvoker lambdaInvoker = new LambdaInvoker("https://5mrsawztvppepgnmxblkcevixu0hivsi.lambda-url.us-east-1.on.aws/");
//        LambdaPayload payload = new LambdaPayload(
//                news.getNewsId(),
//                news.getTitle(),
//                news.getSourceUrl()
//        );
//
//        boolean success = lambdaInvoker.invoke(payload);
//        if (!success) {
//            System.err.println("Lambda 호출 실패!");
//        }
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
    @Override
    public void updateNews(int id, News updateParam) {

    }
    @Override
    public void deleteNews(int newsId) {

    }
}
