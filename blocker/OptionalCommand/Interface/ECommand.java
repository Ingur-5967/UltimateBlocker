package ru.solomka.blocker.OptionalCommand.Interface;

import org.bukkit.command.CommandSender;
import ru.solomka.blocker.OptionalCommand.enums.Type;

import java.io.IOException;

public interface ECommand extends Perms {

        Type getSenderType();

        String getDescription();

        String getName();

        String getUsage();

        void use(CommandSender sender, String[] args) throws IOException;
    }
