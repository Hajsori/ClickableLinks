package xyz.hajsori.clickable_links

import org.bukkit.plugin.java.JavaPlugin

class ClickableLinks : JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(ChatListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
