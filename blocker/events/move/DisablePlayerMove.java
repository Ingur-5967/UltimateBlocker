package ru.solomka.blocker.events.move;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.config.ConfigManager;

import java.util.List;

public class DisablePlayerMove implements Listener {

    @Getter private final Main plugin;

    private final List<String> list = ConfigManager.getStringList("WorldMoveDisable");

    public DisablePlayerMove(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void movePlayer(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("UltimateBlocker.Bynpass") || p.isOp()) return;
        if (!list.contains(p.getLocation().getWorld().getName())) return;
        e.setCancelled(true);
    }
}
