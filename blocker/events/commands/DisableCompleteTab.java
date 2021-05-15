package ru.solomka.blocker.events.commands;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.config.ConfigManager;

public class DisableCompleteTab implements Listener {

    @Getter private final Main plugin;

    private final String message = ConfigManager.getString("MessageTabComplete");

    public DisableCompleteTab (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void tab(TabCompleteEvent e){
        if(!(e.getSender() instanceof Player)) return;
        Player p = ( Player ) e.getSender();
        if(p.hasPermission("UltimateBlocker.Bynpass") || p.isOp()) return;
        if(!ConfigManager.getBoolean("DisableTabComplete") ) return;
        for(String s : ConfigManager.getStringList("CompletedTabCommands")) {
            if (e.getBuffer().contains(s) ) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                e.setCancelled(true);
            }
        }
    }
}
