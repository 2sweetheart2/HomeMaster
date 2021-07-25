package me.sweetie.HomesMaster;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class DelHome implements CommandExecutor {

    Main main;

    public DelHome(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can use only players");
            return true;
        }
        if (args.length < 1) return false;
        Player player = (Player) sender;
        List<HomeObj> homes = main.usersAndHomes.get(player.getUniqueId().toString());
        for (HomeObj h : homes) {
            if (h.name.equals(args[0])) {
                homes.remove(h);
                main.usersAndHomes.put(player.getUniqueId().toString(), homes);
                player.sendMessage(ChatColor.GREEN+"you remove your home with name of '" + args[0] + "'");
                return true;
            }
        }
        player.sendMessage(ChatColor.RED+"You don't have homes with this name");
        return true;
    }
}
