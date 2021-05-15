package ru.solomka.blocker.events.blocks;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.config.ConfigManager;

import java.util.List;

public class DisableBreakBlock implements Listener {

    @Getter private final Main plugin;

    private final String messageBreak = ConfigManager.getString("MessageBlockBreak");
    private final List<String> list = ConfigManager.getStringList("WorldsBlock");

    public DisableBreakBlock (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("UltimateBlocker.Bynpass") || p.isOp()) return;
        if(!ConfigManager.getBoolean("DisabledBlockBreak")) return;
        if(!list.contains(p.getLocation().getWorld().getName())) return;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messageBreak));
        e.setCancelled(true);
    }
}


