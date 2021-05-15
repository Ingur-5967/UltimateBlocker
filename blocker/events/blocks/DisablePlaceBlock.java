package ru.solomka.blocker.events.blocks;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.config.ConfigManager;

import java.util.List;

public class DisablePlaceBlock implements Listener {

    @Getter private final Main plugin;

    private final String messagePlace = ConfigManager.getString("MessagePlaceBlock");
    private final List<String> blocks = ConfigManager.getStringList("Blocks");

    public DisablePlaceBlock (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void placeBlockList(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(!ConfigManager.getBoolean("DisabledBlockPlace")) return;
        if (!blocks.contains(e.getItemInHand().getType().name())) return;
        if(p.isOp() || p.hasPermission("UltimateBlocker.Bynpass")) return;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePlace));
        e.setCancelled(true);
    }
}
