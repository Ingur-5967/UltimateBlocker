package ru.solomka.blocker.events.items;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.config.ConfigManager;

import java.util.List;

public class DisabledInteractItem implements Listener {

    @Getter private final Main plugin;

    private final List list = ConfigManager.getStringList("Items");
    private final String message = ConfigManager.getString("MessageInteractItem");

    public DisabledInteractItem (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void itemBlocked(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("UltimateBlocker.Bynpass") || p.isOp()) return;
        if(!ConfigManager.getBoolean("DisabledInteractItem")) return;
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR) return;
        if (!list.contains(p.getInventory().getItemInHand().getType().name())) return;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        e.setCancelled(true);
    }
}
