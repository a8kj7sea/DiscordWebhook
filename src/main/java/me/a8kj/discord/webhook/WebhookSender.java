package me.a8kj.discord.webhook;

import java.io.IOException;

/**
 * Defines the contract for a component capable of sending data to a Discord Webhook.
 * This abstraction allows for different HTTP implementations (e.g., OkHttp, Java HttpClient)
 * or mock implementations for unit testing.
 *
 * @author a8kj7sea
 */
public interface WebhookSender {

    /**
     * Dispatches a {@link DiscordPayload} to the target webhook destination.
     *
     * @param payload The structured message data, including content and embeds.
     * @throws IOException If a network error occurs or the server returns an unsuccessful response.
     */
    void send(DiscordPayload payload) throws IOException;
}