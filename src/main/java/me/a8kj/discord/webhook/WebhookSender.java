package me.a8kj.discord.webhook;

/**
 * Defines the contract for a component capable of sending data to a Discord Webhook.
 * * @author a8kj7sea
 */
public interface WebhookSender {

    /**
     * Dispatches a {@link DiscordPayload} to the target webhook destination.
     *
     * @param payload The structured message data, including content and embeds.
     * @return A {@link WebhookResult} containing the HTTP status and success state.
     */
    WebhookResult send(DiscordPayload payload);
}