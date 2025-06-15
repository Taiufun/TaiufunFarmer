package ru.taiufun.taiufunfarmer.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.taiufun.taiufunfarmer.TaiufunFarmer;
import ru.taiufun.taiufunfarmer.config.FarmerConfig;
import ru.taiufun.taiufunfarmer.utils.HeadUtils;

public class FarmerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("taiufunfarmer.givehead")) return true;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Команда только для игроков!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("/taiufunfarmer <ник>");
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("Игрок не найден!");
            return true;
        }

        target.getInventory().addItem(HeadUtils.STACK);
        sender.sendMessage("ok");
        return true;
    }


}
