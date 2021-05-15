package ru.solomka.blocker.events.op;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ru.solomka.blocker.Main;
import ru.solomka.blocker.OptionalCommand.message.EText;
import ru.solomka.blocker.config.ConfigManager;

import static ru.solomka.blocker.OptionalCommand.message.EText.*;


public class DisableOp implements Listener {

    @Getter private final Main plugin;

    public DisableOp(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void joinPlayer(PlayerJoinEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!ConfigManager.getBoolean("ProtectOp")) return;
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    boolean unlisted = ConfigManager.getStringList("AllowPlayers").contains(pl.getName());
                    if (!unlisted && pl.isOp()) {
                        pl.setOp(false);
                        send(pl, ChatColor.translateAlternateColorCodes('&', "&6&l[WARNING] &fВас нет в списке разрешенных к опке! Забираем права..."));
                    }
                }
            }
        }.runTaskLater(Main.getInstance(), 20L);
    }
}
