package ru.solomka.blocker.events.chat;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.solomka.blocker.Main;

import java.util.List;

import static ru.solomka.blocker.config.ConfigManager.*;

public class DisabledChat implements Listener {

    @Getter private final Main plugin;

    private final List<String> list = getStringList("WorldsChat");
    private final String message = getString("MessageDisabledChat");

    public DisabledChat (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("UltimateBlocker.Bynpass") || p.isOp()) return;
        if(!getBoolean("DisabledChat")) return;
        if(!list.contains(p.getLocation().getWorld().getName())) return;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        e.setCancelled(true);
    }
}

