package me.a8kj.discord.webhook;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import okhttp3.*;

import java.io.IOException;

/**
 * Implementation of {@link WebhookSender} using OkHttp3 for network communication.
 * Handles the serialization of {@link DiscordPayload} to JSON and manages the HTTP POST request.
 *
 * @author a8kj7sea
 */
@RequiredArgsConstructor
public class DiscordWebhookClient implements WebhookSender {

    private final String webhookUrl;
    private final OkHttpClient httpClient = new OkHttpClient();
    private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");

    /**
     * Configured GSON instance to handle Discord's snake_case naming requirement.
     */
    private final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    /**
     * Sends the provided payload to the configured Discord Webhook URL.
     *
     * @param payload The message and embed data to send.
     * @throws IOException If the request fails or the Discord API returns an error code.
     */
    @Override
    public void send(DiscordPayload payload) throws IOException {
        String jsonString = gson.toJson(payload);
        RequestBody body = RequestBody.create(jsonString, JSON_MEDIA_TYPE);

        Request request = new Request.Builder()
                .url(webhookUrl)
                .post(body)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorBody = response.body() != null ? response.body().string() : "No error body";
                throw new IOException("Discord API Error: " + response.code() + " - " + response.message() + " | Details: " + errorBody);
            }
        }
    }
}