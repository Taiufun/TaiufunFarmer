package ru.taiufun.taiufunfarmer.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.taiufun.taiufunfarmer.TaiufunFarmer;
import ru.taiufun.taiufunfarmer.config.FarmerConfig;
import ru.taiufun.taiufunfarmer.utils.HeadUtils;

public class FarmerCommand implements CommandExecutor {

    private final TaiufunFarmer plugin;


    public FarmerCommand(TaiufunFarmer plugin, FarmerConfig farmerConfig) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && (args.length == 0)) {

            sender.sendMessage("Команда только для игроков или с указанием ника");
            return true;
        }

        Player targetPlayer;

        if (args.length > 0) {
            targetPlayer = plugin.getServer().getPlayerExact(args[0]);
            if (targetPlayer == null) {
                sender.sendMessage(ChatColor.RED + "Игрок с ником '" + args[0] + "' не найден.");
                return true;
            }
        } else {
            targetPlayer = (Player) sender;
        }

        if (!sender.hasPermission("taiufunfarmer.givehead")) {
            return true;
        }



        FarmerConfig farmerConfig = new FarmerConfig(plugin);
        String skin = farmerConfig.getFarmerHeadSkin();
        targetPlayer.getInventory().addItem(HeadUtils.create(plugin, skin, farmerConfig.getFarmerHeadDisplayName(), farmerConfig.getFarmerHeadLore()));

        return true;
    }


}
