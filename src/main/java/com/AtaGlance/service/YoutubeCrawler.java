package com.AtaGlance.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class YoutubeCrawler {

    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36";

    public static String getVideoTitleFromMeta(String videoUrl) throws IOException {
        try {
            // Jsoup으로 YouTube 페이지 로드 (User-Agent 추가)
            Document document = Jsoup.connect(videoUrl)
                    .userAgent(USER_AGENT)
                    .timeout(10000) // 타임아웃 설정 (10초)
                    .get();

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

            // '/' 또는 '(' 이후를 제거하여 반환
            int cutIndex1 = title.indexOf('/');
            int cutIndex2 = title.indexOf('(');
            int res = -1;
            if(cutIndex1 == -1 && cutIndex2 == -1){
                return title;
            }
            else if(cutIndex1 == -1) res = cutIndex2;
            else if(cutIndex2 == -1) res = cutIndex1;
            else res = Math.min(cutIndex1, cutIndex2);
            title = title.substring(0, res).trim();
            return title;
        } catch (IOException e) {
            throw new IOException("Failed to fetch video metadata: " + e.getMessage());
        }
    }

    public static LocalDateTime getVideoPublishedDate(String videoUrl) throws IOException {
        try {
            // Jsoup으로 YouTube 페이지 로드 (User-Agent 추가)
            Document document = Jsoup.connect(videoUrl)
                    .userAgent(USER_AGENT)
                    .timeout(10000) // 타임아웃 설정 (10초)
                    .get();

            // <meta itemprop="datePublished"> 태그에서 게시 날짜 추출
            Element dateElement = document.selectFirst("meta[itemprop=datePublished]");
            String publishedDate = null;
            if (dateElement != null) {
                publishedDate = dateElement.attr("content").trim(); // ISO-8601 형식
            }

            // 날짜를 찾지 못한 경우 예외 처리
            if (publishedDate == null || publishedDate.isEmpty()) {
                throw new IllegalArgumentException("No published date found.");
            }

            // ISO-8601 문자열을 OffsetDateTime으로 파싱
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(publishedDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

            // LocalDateTime으로 변환 후 반환 (타임존 정보를 제거)
            return offsetDateTime.toLocalDateTime();
        } catch (IOException e) {
            throw new IOException("Failed to fetch video metadata: " + e.getMessage());
        }
    }
}
