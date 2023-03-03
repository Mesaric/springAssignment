package com.ct.springassignmentproj.translation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class TranslationServiceExternal {
    private final static String TRANSLATION_FAILURE_MSG = "External API failed to translate message '%s' from language %s to %s.";

    @SneakyThrows
    public String getText(String targetLanguage) {
        String message = "Hello world!";
        String messageLanguage = "en".trim().toUpperCase(); //todo: centralise language code validation

        if (messageLanguage.equals(targetLanguage)) {
            return message;
        }

        OkHttpClient client = new OkHttpClient();
        FormBody body =
                new FormBody.Builder()
                        .add("q", message)
                        .add("target", targetLanguage)
                        .add("source", messageLanguage)
                        .add("format", "text")
                        .build();

        var request = new Request.Builder()
                .url("https://google-translate1.p.rapidapi.com/language/translate/v2")
                .post(body);
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        request.addHeader("Accept-Encoding", "application/gzip");
        request.addHeader("X-RapidAPI-Key", "d7cd865ad2mshbe749ea37dfca2bp111e3djsn7372d1fbf9be");
        request.addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com");

        var response = client.newCall(request.build()).execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException(String.format(TRANSLATION_FAILURE_MSG, message, messageLanguage, targetLanguage));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(Objects.requireNonNull(response.body()).string());
        String translatedText = jsonNode.get("data")
                .get("translations")
                .get(0)
                .get("translatedText")
                .asText();

        return translatedText;
    }
}
