package ru.solomka.blocker.OptionalCommand.Interface;

import org.bukkit.command.CommandExecutor;

public interface Handler extends CommandExecutor{

        ECommand getDefaultCommand();

        void registerCommand(ECommand command);
    }
