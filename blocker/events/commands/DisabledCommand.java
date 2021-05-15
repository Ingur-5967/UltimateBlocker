package ru.solomka.blocker.events.commands;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.config.ConfigManager;

import java.util.List;

public class DisabledCommand implements Listener {

    @Getter private final Main plugin;

    private final List<String> list = ConfigManager.getStringList("Commands");
    private final String message = ConfigManager.getString("MessageCommands");

    public DisabledCommand (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand (PlayerCommandPreprocessEvent e) {

        Player p = e.getPlayer();
        String cmd = e.getMessage().split(" ")[0].toLowerCase();

        if(p.hasPermission("UltimateBlocker.Bynpass") || p.isOp()) return;

        if(!ConfigManager.getBoolean("DisabledCommands")) return;

        for(int i = 0; i == cmd.length(); i++ ){
            if(!list.contains(cmd)) return;
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            e.setCancelled(true);
        }
    }
}
