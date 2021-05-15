package ru.solomka.blocker.OptionalCommand.basic;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.solomka.blocker.OptionalCommand.Interface.CommandHandler;
import ru.solomka.blocker.OptionalCommand.Interface.ECommand;
import ru.solomka.blocker.OptionalCommand.message.EText;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public abstract class BasicCommandHandler implements CommandHandler, TabCompleter {

        protected Map<String, ECommand> commands = new HashMap<>();

        public Collection<ECommand> getRegisteredCommands() { return commands.values(); }

        private final ECommand defCommand;

        public BasicCommandHandler(ECommand def) { defCommand = def; }

        @Override
        public ECommand getDefaultCommand() { return defCommand; }

        @Override
        public void registerCommand(ECommand command) { commands.put(command.getName(), command); }

        public void registerHandler(JavaPlugin plugin, String key) {
            plugin.getCommand(key).setExecutor(this);
        }

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            ECommand cmd;

            if(!(sender instanceof Player)) return true;

            Player p = (Player) sender;

            if (args.length == 0 || commands.get(args[0]) == null) cmd = defCommand;
            else {
                cmd = commands.get(args[0]);
                args = EText.trimArgs(args);
            }

            if (cmd.getPermission() == null || sender.hasPermission(cmd.getPermission())) {
                try {
                    cmd.use(sender, args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
                p.sendTitle(translateAlternateColorCodes('&', "&4Нет доступа"), translateAlternateColorCodes('&', "&fУ вас недосточно прав для этого!"), 40, 40, 40);
            return true;
        }
}
