package com.AtaGlance.service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

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
}


