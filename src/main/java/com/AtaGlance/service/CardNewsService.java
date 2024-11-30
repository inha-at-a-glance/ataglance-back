package com.AtaGlance.service;

import com.AtaGlance.dto.News;
import com.AtaGlance.dto.TextProc;
import com.AtaGlance.repository.mybatis.TextProcMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardNewsService {

    private final TextProcMapper textProcMapper;

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
}


