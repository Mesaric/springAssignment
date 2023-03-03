package com.ct.springassignmentproj.translation;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TranslationServiceExternal {

    @SneakyThrows
    public String getText(String languageCode) {
        OkHttpClient client = new OkHttpClient();
        FormBody body =
                new FormBody.Builder()
                        .add("q", "Hello, world!")
                        .add("target", "es")
                        .add("source", "en")
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
            throw new RuntimeException("External API failed to translate");
        }
        return translation(Objects.requireNonNull(response.body()).string());
    }
    public String translation(String result) {
        return result;
    }
}
