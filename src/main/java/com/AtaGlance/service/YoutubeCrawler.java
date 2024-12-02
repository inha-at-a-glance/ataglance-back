package com.AtaGlance.service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.time.LocalDateTime;
import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class YoutubeCrawler {

    public static String getVideoTitleFromMeta(String videoUrl) throws IOException {
        try {
            // Jsoup으로 YouTube 페이지 로드
            Document document = Jsoup.connect(videoUrl).get();

            // <meta property="og:title"> 태그에서 제목 추출
            Element metaTitleElement = document.selectFirst("meta[property=og:title]");
            String title = null;
            if (metaTitleElement != null) {
                title = metaTitleElement.attr("content").trim();
            }

            // <meta name="title"> 태그에서 제목 추출 (백업 방법)
            if (title == null || title.isEmpty()) {
                metaTitleElement = document.selectFirst("meta[name=title]");
                if (metaTitleElement != null) {
                    title = metaTitleElement.attr("content").trim();
                }
            }

            // 제목을 찾지 못한 경우 예외 처리
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("No meta title found.");
            }

            // '/' 이후를 제거하여 반환
            int slashIndex = title.indexOf('/');
            if (slashIndex != -1) {
                title = title.substring(0, slashIndex).trim();
            }

            return title;
        } catch (IOException e) {
            throw new IOException("Failed to fetch video metadata: " + e.getMessage());
        }
    }

    public static LocalDateTime getVideoPublishedDate(String videoUrl) throws IOException {
        try {
            // Jsoup으로 YouTube 페이지 로드
            Document document = Jsoup.connect(videoUrl).get();

            // <meta itemprop="datePublished"> 태그에서 게시 날짜 추출
            Element dateElement = document.selectFirst("meta[itemprop=datePublished]");
            String publishedDate = null;
            if (dateElement != null) {
                publishedDate = dateElement.attr("content").trim(); // YYYY-MM-DD 형식
            }

            // 날짜를 찾지 못한 경우 예외 처리
            if (publishedDate == null || publishedDate.isEmpty()) {
                throw new IllegalArgumentException("No published date found.");
            }

            // 문자열 날짜를 LocalDateTime으로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDateTime.parse(publishedDate + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (IOException e) {
            throw new IOException("Failed to fetch video metadata: " + e.getMessage());
        }
    }

}


