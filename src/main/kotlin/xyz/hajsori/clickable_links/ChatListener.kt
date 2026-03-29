package xyz.hajsori.clickable_links

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class ChatListener : Listener {
    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val message = PlainTextComponentSerializer.plainText().serialize(event.message())
        var newMessage = Component.empty()

        message.split(" ").forEach { word ->
            if (word.startsWith("https://") || word.startsWith("http://")) {
                newMessage = newMessage.append(Component.text(word).clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL,
                    ClickEvent.Payload.string(word)))).appendSpace()
            } else {
                newMessage = newMessage.append(Component.text(word)).appendSpace()
            }
        }

        event.message(newMessage)
    }
}