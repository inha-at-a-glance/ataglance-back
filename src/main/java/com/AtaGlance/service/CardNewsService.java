package com.AtaGlance.service;

import com.AtaGlance.dto.News;
import com.AtaGlance.dto.TextProc;
import com.AtaGlance.dto.VideoProc;
import com.AtaGlance.repository.mybatis.TextProcMapper;
import com.AtaGlance.repository.mybatis.VideoProcMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardNewsService {

    private final TextProcMapper textProcMapper;
    private final VideoProcMapper videoProcMapper;
    @Autowired
    private S3Service s3Service;

//    public CardNewsService(TextProcMapper textProcMapper) {
//        this.textProcMapper = textProcMapper;
//    }

    public List<String> getSummarySentences(int newsId) {
        // TextProc 객체 가져오기
        TextProc textProc = textProcMapper.getTextProcByNewsId(newsId);

        if (textProc == null || textProc.getSummaryTxt() == null) {
            // TextProc 객체가 없거나 요약문이 없는 경우 빈 리스트 반환
            return List.of("요약문 생성 예정");
        }

        // summary_txt에서 "text" 배열 추출
        try {
            JSONObject jsonObject = new JSONObject(textProc.getSummaryTxt());
            JSONArray textArray = jsonObject.getJSONArray("text");

            // 배열의 각 문장을 List로 변환
            List<String> sentences = new ArrayList<>();
            for (int i = 0; i < textArray.length(); i++) {
                sentences.add(textArray.getString(i));
            }
            return sentences;
        } catch (JSONException e) {
            // JSON 파싱 실패 시 빈 리스트 반환
            return List.of("요약문을 처리하는 중 오류가 발생했습니다.");
        }
    }

    public List<String> getObjectsPath(int newsId) {

        VideoProc videoProc = videoProcMapper.getVideoProcByNewsId(newsId);

        if (videoProc == null || videoProc.getObjectsPath() == null) {
            // TextProc 객체가 없거나 요약문이 없는 경우 빈 리스트 반환
            return List.of();
        }
        List<String> objectPaths = new ArrayList<>();

        try {
            // JSON 파싱
            JSONObject jsonObject = new JSONObject(videoProc.getObjectsPath());
            JSONArray imagePathArray = jsonObject.getJSONArray("image_path");

            //객체의 S3 URI 추출 및 URL 변환
            for (int i = 0; i < imagePathArray.length(); i++) {
                String s3Uri = imagePathArray.getString(i);
                objectPaths.add(s3Service.getURL(s3Uri));
            }

        } catch (JSONException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }

        return objectPaths;
    }
}


