package me.a8kj.discord.webhook;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

/**
 * Represents a Discord Embed object used within a Webhook payload.
 * Provides a structured way to send rich media content.
 *
 * @author a8kj7sea
 */
@Getter
@Builder
public class DiscordEmbed {

    /**
     * The title of the embed.
     */
    private String title;

    /**
     * The description text shown in the embed.
     */
    private String description;

    /**
     * The URL that the title will link to.
     */
    private String url;

    /**
     * The color code of the embed (decimal value).
     */
    private Integer color;

    /**
     * The footer component of the embed.
     */
    private Footer footer;

    /**
     * The main image component of the embed.
     */
    private Image image;

    /**
     * The thumbnail component (top right) of the embed.
     */
    private Thumbnail thumbnail;

    /**
     * The author information component of the embed.
     */
    private Author author;

    /**
     * A list of fields to be displayed in the embed.
     */
    @Singular
    private List<Field> fields;

    /**
     * Represents the footer section of a Discord embed.
     */
    @Getter
    @Builder
    public static class Footer {
        /**
         * The text to display in the footer.
         */
        private String text;

        /**
         * The URL of the icon to display in the footer.
         */
        private String iconUrl;
    }

    /**
     * Represents the main image section of a Discord embed.
     */
    @Getter
    @Builder
    public static class Image {
        /**
         * The source URL of the image.
         */
        private String url;
    }

    /**
     * Represents the thumbnail section of a Discord embed.
     */
    @Getter
    @Builder
    public static class Thumbnail {
        /**
         * The source URL of the thumbnail image.
         */
        private String url;
    }

    /**
     * Represents the author section of a Discord embed.
     */
    @Getter
    @Builder
    public static class Author {
        /**
         * The name of the author.
         */
        private String name;

        /**
         * The URL that the author's name will link to.
         */
        private String url;

        /**
         * The URL of the icon to display next to the author's name.
         */
        private String iconUrl;
    }

    /**
     * Represents a single field within a Discord embed.
     */
    @Getter
    @Builder
    public static class Field {
        /**
         * The name (header) of the field.
         */
        private String name;

        /**
         * The value (content) of the field.
         */
        private String value;

        /**
         * Whether the field should display inline with others.
         */
        boolean inline;
    }
}