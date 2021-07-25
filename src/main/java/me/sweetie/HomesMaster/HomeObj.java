package me.sweetie.HomesMaster;

import org.bukkit.Location;

import java.util.Objects;

public class HomeObj {

    public String w;
    public int x, y, z;
    public String name;

    public void createHomeByLocation(Location location, String home_name) {
        this.w = Objects.requireNonNull(location.getWorld()).getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.name = home_name;
    }

    public void createHomeByXYZ(int x, int y, int z, String w, String home_name) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = home_name;
    }


}
