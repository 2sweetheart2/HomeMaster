package me.sweetie.HomesMaster;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public File json;
    Logger log = Bukkit.getLogger();
    public int homes_count;
    public HashMap<String, List<HomeObj>> usersAndHomes = new HashMap<>();
    public JsonStuff jsonStuff;

    @Override
    public void onEnable() {
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            config.mkdir();
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        json = new File(getDataFolder() + File.separator + "homes.json");
        if (!json.exists()) {
            try {
                json.createNewFile();
            } catch (IOException e) {
                log.log(Level.WARNING, e.getMessage());
            }
        }
        jsonStuff = new JsonStuff(this);
        try {
            usersAndHomes = jsonStuff.gethomes();
        } catch (FileNotFoundException e) {
            jsonStuff.SaveHomes(usersAndHomes);
            try {
                usersAndHomes = jsonStuff.gethomes();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        homes_count = getConfig().getInt("homes_cout");
        Objects.requireNonNull(getCommand("sethome")).setExecutor(new CreateHome(this));
        Objects.requireNonNull(getCommand("home")).setExecutor(new Home(this));
        Objects.requireNonNull(getCommand("delhome")).setExecutor(new DelHome(this));

    }

    @Override
    public void onDisable() {
        jsonStuff.SaveHomes(usersAndHomes);
    }
}
