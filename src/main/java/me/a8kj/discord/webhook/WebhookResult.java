package me.a8kj.discord.webhook;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Represents the outcome of a Discord Webhook execution.
 * Encapsulates the success status, the HTTP response code, and any error messages.
 * * @author a8kj7sea
 */
@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class WebhookResult {
    private final boolean successful;
    private final int statusCode;
    private final String errorMessage;


    /**
     * Creates a successful result instance.
     * * @param code The HTTP status code (typically 200-204).
     *
     * @return A successful WebhookResult.
     */
    public static WebhookResult success(int code) {
        return new WebhookResult(true, code, null);
    }

    /**
     * Creates a failure result based on an API error response.
     * * @param code The HTTP status code returned by Discord.
     *
     * @param message The error details or response body.
     * @return A failed WebhookResult.
     */
    public static WebhookResult failure(int code, String message) {
        return new WebhookResult(false, code, message);
    }

    /**
     * Creates a result representing a network-level or internal exception.
     * * @param message The exception message or technical cause.
     *
     * @return A failed WebhookResult with a status code of -1.
     */
    public static WebhookResult error(String message) {
        return new WebhookResult(false, -1, message);
    }
}