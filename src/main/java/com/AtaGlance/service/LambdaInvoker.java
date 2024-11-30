package com.AtaGlance.service;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaInvoker {

    private final String lambdaUrl; // Lambda Function URL
    private final ObjectMapper objectMapper; // JSON 직렬화/역직렬화 도구

    // 생성자에서 Lambda Function URL 초기화
    public LambdaInvoker(String lambdaUrl) {
        this.lambdaUrl = lambdaUrl;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Lambda 호출 메서드
     @param payload Lambda로 보낼 데이터 객체
     @return 호출 성공 여부 (true: 성공, false: 실패)
     */
    public boolean invoke(Object payload) {
        try {
            // JSON으로 변환
            String jsonPayload = objectMapper.writeValueAsString(payload);

            // HTTP 연결 설정
            URL url = new URL(lambdaUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // 요청 본문 전송
            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            // 응답 상태 코드 확인
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Lambda 호출 성공!");
                return true;
            } else {
                System.err.println("Lambda 호출 실패: 응답 코드 " + responseCode);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Lambda 호출 중 오류 발생: " + e.getMessage());
            return false;
        }
    }
}

