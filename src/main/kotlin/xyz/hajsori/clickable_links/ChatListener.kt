package xyz.hajsori.clickable_links

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class ChatListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onChat(event: AsyncChatEvent) {
        val message = PlainTextComponentSerializer.plainText().serialize(event.message())
        var newMessage = Component.empty()

        if (!message.contains("https://") && !message.contains("http://")) {
            return
        }

        message.split(" ").forEach { word ->
            newMessage = if (word.startsWith("https://") || word.startsWith("http://")) {
                newMessage
                    .append(Component.text(word)
                        .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, ClickEvent.Payload.string(word)))
                        .decorate(TextDecoration.UNDERLINED)
                    )
                    .appendSpace()
            } else {
                newMessage.append(Component.text(word)).appendSpace()
            }
        }

        event.message(newMessage)
    }
}