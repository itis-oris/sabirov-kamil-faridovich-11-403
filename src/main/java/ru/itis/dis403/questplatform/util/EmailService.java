package ru.itis.dis403.questplatform.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class EmailService {

    @Value("${app.mailgun.api-url}")
    private String apiUrl;

    @Value("${app.mailgun.api-key}")
    private String apiKey;

    @Value("${app.mailgun.domain}")
    private String domain;

    @Value("${app.mailgun.from-email}")
    private String fromEmail;

    private final OkHttpClient httpClient = new OkHttpClient();

    public void sendCompletionEmail(String toEmail, String username, String questTitle) {
        String url = apiUrl + "/" + domain + "/messages";

        RequestBody formBody = new FormBody.Builder()
                .add("from", "Quest Platform <" + fromEmail + ">")
                .add("to", toEmail)
                .add("subject", "Вы завершили квест: " + questTitle)
                .add("text", "Привет, " + username + "!\n\nПоздравляем! Вы успешно прошли квест \"" + questTitle + "\".\n\nСпасибо за участие!")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .addHeader("Authorization", Credentials.basic("api", apiKey))
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.error("Failed to send email to {}: {} - {}", toEmail, response.code(), response.message());
            } else {
                log.info("Email sent successfully to {}", toEmail);
            }
        } catch (IOException e) {
            log.error("IO Exception while sending email to {}", toEmail, e);
        }
    }
}