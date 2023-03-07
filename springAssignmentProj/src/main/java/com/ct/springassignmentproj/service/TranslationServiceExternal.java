package com.ct.springassignmentproj.service;

import com.ct.springassignmentproj.aop.Log;
import com.ct.springassignmentproj.util.IsoUtil;
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
    private final IsoUtil isoUtil;

    @Log
    @SneakyThrows
    public String getText(String targetLanguage) {
        String message = "Hello world!";
        String messageLanguage = isoUtil.sanitizeISOCode("en");
        String sanatizedTargetLanguage = isoUtil.sanitizeISOCode(targetLanguage);

        if (!(IsoUtil.isValidISOLanguage(messageLanguage))) {
            throw new RuntimeException(String.format(IsoUtil.NOT_ISO_LANGUAGE_CODE, messageLanguage));
        }
        if (!(IsoUtil.isValidISOLanguage(sanatizedTargetLanguage))) {
            throw new RuntimeException(String.format(IsoUtil.NOT_ISO_LANGUAGE_CODE, sanatizedTargetLanguage));
        }
        if (messageLanguage.equals(sanatizedTargetLanguage)) {
            return message;
        }

        OkHttpClient client = new OkHttpClient();
        FormBody body =
                new FormBody.Builder()
                        .add("q", message)
                        .add("target", sanatizedTargetLanguage)
                        .add("source", messageLanguage)
                        .add("format", "text")
                        .build();

        var request = new Request.Builder()
                .url("https://google-translate1.p.rapidapi.com/language/translate/v2")
                .post(body);
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        request.addHeader("Accept-Encoding", "application/gzip");
        request.addHeader("X-RapidAPI-Key", "9ae50b97b4mshdaed7d5b11470c3p1410d0jsnf9bce326bd6e");
        request.addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com");

        var response = client.newCall(request.build()).execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException(String.format(TRANSLATION_FAILURE_MSG, message, messageLanguage, sanatizedTargetLanguage));
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
