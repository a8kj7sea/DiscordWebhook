package me.a8kj.discord.webhook;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.Map;
import java.util.Objects;

/**
 * Utility class providing pre-defined templates for common Discord Webhook payloads.
 * Simplifies the creation of status messages, alerts, and rich media embeds.
 *
 * @author a8kj7sea
 */
@UtilityClass
public final class WebhookTemplates {

    private static final int COLOR_SUCCESS = 0x2ECC71;
    private static final int COLOR_ERROR = 0xE74C3C;
    private static final int COLOR_INFO = 0x3498DB;
    private static final int COLOR_WARNING = 0xF1C40F;

    /**
     * Creates a basic text-only payload.
     */
    public DiscordPayload simple(String content) {
        return DiscordPayload.builder()
                .content(content)
                .build();
    }

    /**
     * Creates a message that mentions a specific user or role by ID.
     */
    public DiscordPayload mention(String id, String content, boolean isRole) {
        String mention = isRole ? "<@&" + id + ">" : "<@" + id + ">";
        return DiscordPayload.builder()
                .content(mention + " " + content)
                .build();
    }

    /**
     * Creates a success notification with a green sidebar.
     */
    public DiscordPayload success(String title, String message) {
        return embedOnly(DiscordEmbed.builder()
                .title("✅ " + title)
                .description(message)
                .color(COLOR_SUCCESS)
                .build());
    }

    /**
     * Creates an error notification with a red sidebar.
     */
    public DiscordPayload error(String title, String message) {
        return embedOnly(DiscordEmbed.builder()
                .title("❌ " + title)
                .description(message)
                .color(COLOR_ERROR)
                .build());
    }

    /**
     * Creates an info notification with a blue sidebar.
     */
    public DiscordPayload info(String title, String message) {
        return embedOnly(DiscordEmbed.builder()
                .title("ℹ️ " + title)
                .description(message)
                .color(COLOR_INFO)
                .build());
    }

    /**
     * Creates a warning notification with a yellow sidebar.
     */
    public DiscordPayload warning(String title, String message) {
        return embedOnly(DiscordEmbed.builder()
                .title("⚠️ " + title)
                .description(message)
                .color(COLOR_WARNING)
                .build());
    }

    /**
     * Creates an embed with a list of fields (e.g., for system stats or logs).
     */
    public DiscordPayload fields(String title, Map<String, String> data, boolean inline) {
        DiscordEmbed.DiscordEmbedBuilder builder = DiscordEmbed.builder()
                .title(title)
                .color(COLOR_INFO);

        data.forEach((name, value) -> builder.field(DiscordEmbed.Field.builder()
                .name(name)
                .value(value)
                .inline(inline)
                .build()));

        return embedOnly(builder.build());
    }

    /**
     * Creates a payload with a large remote image.
     */
    public DiscordPayload remoteImage(String title, String imageUrl, String description) {
        return embedOnly(DiscordEmbed.builder()
                .title(title)
                .description(description)
                .image(DiscordEmbed.Image.builder().url(imageUrl).build())
                .build());
    }

    /**
     * Creates a payload with a thumbnail (top right small image).
     */
    public DiscordPayload thumbnail(String title, String description, String thumbnailUrl) {
        return embedOnly(DiscordEmbed.builder()
                .title(title)
                .description(description)
                .thumbnail(DiscordEmbed.Thumbnail.builder().url(thumbnailUrl).build())
                .color(COLOR_INFO)
                .build());
    }

    /**
     * Prepares a payload for a local file attachment.
     * * @param file The local file to be referenced.
     */
    public DiscordPayload localImage(String title, File file, String description) {
        Objects.requireNonNull(file, "File cannot be null");
        String attachmentUrl = "attachment://" + file.getName();

        return embedOnly(DiscordEmbed.builder()
                .title(title)
                .description(description)
                .image(DiscordEmbed.Image.builder().url(attachmentUrl).build())
                .build());
    }

    /**
     * Creates a payload representing a user profile or author action.
     */
    public DiscordPayload author(String authorName, String authorIcon, String title, String description) {
        return embedOnly(DiscordEmbed.builder()
                .author(DiscordEmbed.Author.builder()
                        .name(authorName)
                        .iconUrl(authorIcon)
                        .build())
                .title(title)
                .description(description)
                .color(COLOR_INFO)
                .build());
    }

    /**
     * Creates a payload using a link/URI as the main focus.
     */
    public DiscordPayload link(String title, String url, String description) {
        return embedOnly(DiscordEmbed.builder()
                .title(title)
                .url(url)
                .description(description + "\n\n" + url)
                .color(COLOR_INFO)
                .build());
    }

    /**
     * Creates a payload with a small footer text and optional icon.
     */
    public DiscordPayload withFooter(String title, String description, String footerText, String footerIcon) {
        return embedOnly(DiscordEmbed.builder()
                .title(title)
                .description(description)
                .footer(DiscordEmbed.Footer.builder()
                        .text(footerText)
                        .iconUrl(footerIcon)
                        .build())
                .color(COLOR_INFO)
                .build());
    }

    /**
     * Helper to wrap a single embed into a payload.
     */
    public DiscordPayload embedOnly(DiscordEmbed embed) {
        return DiscordPayload.builder()
                .embed(embed)
                .build();
    }
}