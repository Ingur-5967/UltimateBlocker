package ru.solomka.blocker.events.damage;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.config.ConfigManager;

import java.util.List;
import java.util.Random;

import static org.bukkit.ChatColor.*;
import static ru.solomka.blocker.config.ConfigManager.*;


public class DisableFallingVoid implements Listener {

    @Getter private final Main plugin;

    private int x;
    private int y;
    private int z;

    private final World world = Bukkit.getWorld(getString("DefaultWorld"));

    private final List list = getStringList("WorldsFalling");
    private final String message = getString("MessageFallingInVoid");

    public DisableFallingVoid (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void fall(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player) ) return;

        Player p = (Player) e.getEntity();

        if (!getBoolean("DisableFallingInVoid") ) return;
        if (!list.contains(p.getLocation().getWorld().getName())) return;
        if (e.getCause() != DamageCause.VOID) return;
        if ( world != null ) {
            p.teleport(world.getSpawnLocation());
            p.sendMessage(translateAlternateColorCodes('&', message));
            e.setCancelled(true);
        }
    }
}

