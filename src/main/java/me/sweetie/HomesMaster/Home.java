package me.sweetie.HomesMaster;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.StringJoiner;

public class Home implements CommandExecutor {

    Main main;

    public Home(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can use only players");
            return true;
        }
        Player player = (Player) sender;
        if (!main.usersAndHomes.containsKey(player.getUniqueId().toString())) {
            player.sendMessage("You don't have a home");
            return true;
        }
        if (args.length == 0) GetHomes(player);
        if (args.length >= 1) return TpToHome(player, args[0]);
        return true;
    }

    private boolean TpToHome(Player player, String name) {
        List<HomeObj> homes = main.usersAndHomes.get(player.getUniqueId().toString());
        for (HomeObj h : homes) {
            if (h.name.equals(name)) {
                player.teleport(new Location(Bukkit.getWorld(h.w), h.x, h.y, h.z));
                player.sendMessage(ChatColor.GREEN+"You teleported to home '"+name+"'");
                return true;
            }
        }
        player.sendMessage(ChatColor.RED+"you don't have homes with this name!");
        return true;
    }

    private void GetHomes(Player player) {
        List<HomeObj> homes = main.usersAndHomes.get(player.getUniqueId().toString());
        StringJoiner text = new StringJoiner("\n");
        text.add("Your homes name:");
        for (HomeObj h : homes) {
            text.add(h.name);
        }
        player.sendMessage(text.toString());
    }
}
