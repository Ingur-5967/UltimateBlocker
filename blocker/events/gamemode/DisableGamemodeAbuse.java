package ru.solomka.blocker.events.gamemode;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.config.ConfigManager;

import static org.bukkit.ChatColor.*;
import static ru.solomka.blocker.config.ConfigManager.*;

public class DisableGamemodeAbuse implements Listener {

    @Getter private final Main plugin;

    private final String messageDamager = getString("DisableDamageMessage");

    public DisableGamemodeAbuse (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void damagePlayer(EntityDamageByEntityEvent e){
        if(!(e.getDamager() instanceof Player)) return;
        Player p = (Player) e.getDamager();
        if(!getBoolean("DisableGamemodeAbuse")) return;
        if(!getBoolean("DisableDamageGM")) return;
        if(p.isOp() || p.hasPermission("UltimateBlocker.Bynpass")) return;
        if(p.getGameMode() != GameMode.CREATIVE) return;
        p.sendMessage(translateAlternateColorCodes('&', messageDamager));
        e.setCancelled(true);
    }
}
