package me.a8kj.discord.webhook;

/**
 * Modern entry point demonstrating the fluent builder API for Discord webhooks.
 *
 * @author a8kj7sea
 */
public class Main {
    public static void main(String[] args) {
        // Your Webhook URL
        final String WEBHOOK_URL = "YOUR_WEBHOOK_URL_HERE";

        // Initialize the client using the WebhookSender abstraction
        WebhookSender sender = new DiscordWebhookClient(WEBHOOK_URL);

        // Build a rich payload with an embed
        DiscordPayload payload = DiscordPayload.builder()
                .username("System Monitor")
                .avatarUrl("https://i.imgur.com/NS45qcE.jpeg")
                .content("Greetings! This is a test message from the modernized Discord Webhook client.")
                .embed(DiscordEmbed.builder()
                        .title("🚀 Server Deployment Success")
                        .description("The production environment has been successfully updated to the latest build.")
                        .url("https://github.com/a8kj7sea/DiscordWebhook")
                        .color(0x32CD32) // Lime Green
                        .author(DiscordEmbed.Author.builder()
                                .name("a8kj7sea")
                                .iconUrl("https://i.imgur.com/NS45qcE.jpeg")
                                .build())
                        .field(DiscordEmbed.Field.builder()
                                .name("Version")
                                .value("v1.0.0")
                                .inline(true)
                                .build())
                        .field(DiscordEmbed.Field.builder()
                                .name("Status")
                                .value("Stable")
                                .inline(true)
                                .build())
                        .footer(DiscordEmbed.Footer.builder()
                                .text("Sent via Modern Discord Webhook Client")
                                .build())
                        .build())
                .build();

        // Dispatch the request
        try {
            sender.send(payload);
            System.out.println("Payload dispatched successfully.");
        } catch (Exception e) {
            System.err.println("Failed to send webhook: " + e.getMessage());
        }
    }
}