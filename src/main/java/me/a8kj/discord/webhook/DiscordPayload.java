package me.a8kj.discord.webhook;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

/**
 * Represents the top-level JSON payload sent to a Discord Webhook.
 * Contains the message content, identity overrides, and rich embeds.
 *
 * @author a8kj7sea
 */
@Getter
@Builder
public class DiscordPayload {

    /**
     * The main text content of the message (up to 2000 characters).
     */
    private String content;

    /**
     * Overrides the default username of the webhook.
     */
    private String username;

    /**
     * Overrides the default avatar of the webhook with a specific image URL.
     */
    private String avatarUrl;

    /**
     * Whether or not this message should be read aloud using Text-To-Speech.
     */
    private boolean tts;

    /**
     * A list of rich {@link DiscordEmbed} objects.
     * Discord currently allows up to 10 embeds per message.
     */
    @Singular
    private List<DiscordEmbed> embeds;
}