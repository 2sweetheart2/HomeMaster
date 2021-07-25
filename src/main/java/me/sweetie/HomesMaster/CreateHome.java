package me.sweetie.HomesMaster;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CreateHome implements CommandExecutor {

    Main main;

    public CreateHome(Main main) {
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
        if (main.usersAndHomes.containsKey(player.getUniqueId().toString())) {
            for (HomeObj h : main.usersAndHomes.get(player.getUniqueId().toString())) {
                if (h.name.equals(args[0])) {
                    player.sendMessage("you have home this name of " + args[0]);
                    return true;
                }
            }
        }
        HomeObj newHome = new HomeObj();
        newHome.createHomeByLocation(player.getLocation(), args[0]);
        List<HomeObj> h = new ArrayList<>();
        if (main.usersAndHomes.containsKey(player.getUniqueId().toString()))
            h = main.usersAndHomes.get(player.getUniqueId().toString());
        if (h.size() >= main.homes_count) {
            player.sendMessage(ChatColor.RED+"you can't create " + (h.size()+1) + " homes\nLimit - " + main.homes_count);
            return true;
        }
        h.add(newHome);
        player.sendMessage(ChatColor.GREEN+"You create home with name '" +args[0]+ "'");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,5,1);
        main.usersAndHomes.put(player.getUniqueId().toString(), h);
        return true;
    }
}
