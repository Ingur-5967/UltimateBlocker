package ru.solomka.blocker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.solomka.blocker.events.move.DisablePlayerMove;
import ru.solomka.blocker.events.blocks.DisableBreakBlock;
import ru.solomka.blocker.events.blocks.DisablePlaceBlock;
import ru.solomka.blocker.events.chat.DisableWords;
import ru.solomka.blocker.events.chat.DisabledChat;
import ru.solomka.blocker.events.commands.DisableCompleteTab;
import ru.solomka.blocker.events.commands.DisabledCommand;
import ru.solomka.blocker.events.damage.DisableDamage;
import ru.solomka.blocker.events.damage.DisableFallingVoid;
import ru.solomka.blocker.events.gamemode.DisableGamemodeAbuse;
import ru.solomka.blocker.events.items.DisabledInteractItem;
import ru.solomka.blocker.events.op.DisableOp;

import static ru.solomka.blocker.config.ConfigManager.*;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        eventsBukkit();
        createConfig();
        getLogger().info("UltimateBlocker activated!");
        getLogger().info("Version: v3.0!");
        getLogger().info("");
        getLogger().info("[!] Please, check updates [!]");
    }

    public void onDisable() {
        getLogger().info("UltimateBlocker deactivated!");
        getLogger().info("Version: v3.0!");
        getLogger().info("");
        getLogger().info("[!] Please, stay tuned for plugin updates [!]");
    }

    public void eventsBukkit() {
        Bukkit.getPluginManager().registerEvents(new DisableDamage(this), this);
        Bukkit.getPluginManager().registerEvents(new DisableBreakBlock(this), this);
        Bukkit.getPluginManager().registerEvents(new DisabledCommand(this), this);
        Bukkit.getPluginManager().registerEvents(new DisabledChat(this), this);
        Bukkit.getPluginManager().registerEvents(new DisabledInteractItem(this), this);
        Bukkit.getPluginManager().registerEvents(new DisablePlaceBlock(this), this);
        Bukkit.getPluginManager().registerEvents(new DisableWords(this), this);
        Bukkit.getPluginManager().registerEvents(new DisableFallingVoid(this), this);
        Bukkit.getPluginManager().registerEvents(new DisableCompleteTab(this), this);
        Bukkit.getPluginManager().registerEvents(new DisableGamemodeAbuse(this), this);
        Bukkit.getPluginManager().registerEvents(new DisableOp(this), this);
        Bukkit.getPluginManager().registerEvents(new DisablePlayerMove(this), this);
    }
    public static Main getInstance() {
        return instance;
    }
}