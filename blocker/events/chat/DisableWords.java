package ru.solomka.blocker.events.chat;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.config.ConfigManager;

public class DisableWords implements Listener {

    @Getter private final Main plugin;

    private final String message = ConfigManager.getString("ForbiddenWord");
    private final String permBynpass = ConfigManager.getString("ForbiddenWord");

    public DisableWords (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void wordForbidden(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        if(p.hasPermission("UltimateBlocker.Bynpass") || p.isOp()) return;
        if(!ConfigManager.getBoolean("DisableMessage")) return;
        for(String s : ConfigManager.getStringList("Word")) {
            if(message.contains(s)) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                e.setCancelled(true);
            }
        }
    }
}
