package ru.solomka.blocker.events.damage;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.config.ConfigManager;

import java.util.List;

import static ru.solomka.blocker.config.ConfigManager.*;

public class DisableDamage implements Listener {

    @Getter private final Main plugin;

    private final List<String> list = getStringList("WorldsDamage");

    public DisableDamage (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void disableDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        if(!getBoolean("DisableDamage")) return;
        if(!list.contains(p.getLocation().getWorld().getName())) return;
        e.setCancelled(true);
    }
}
