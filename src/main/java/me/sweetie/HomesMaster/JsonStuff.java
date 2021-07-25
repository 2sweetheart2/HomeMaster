package me.sweetie.HomesMaster;

import com.google.gson.*;

import java.io.*;
import java.util.*;

public class JsonStuff {

    Main main;
    File json;

    public JsonStuff(Main main) {
        this.main = main;
        this.json = main.json;
    }

    public void SaveHomes(HashMap<String, List<HomeObj>> h) {
        Gson gson = new Gson();
        String json = gson.toJson(h);
        try {
            OutputStream os = new FileOutputStream(this.json);
            os.flush();
            os.write(json.getBytes());
        } catch (Exception ignored) {

        }
    }

    public HashMap<String, List<HomeObj>> gethomes() throws FileNotFoundException {
        HashMap<String, List<HomeObj>> homesAndUsers = new HashMap<>();
        JsonParser jsonParser = new JsonParser();
        Object parsed = jsonParser.parse(new FileReader(json.getPath()));
        JsonObject jsonObject = (JsonObject) parsed;
        Set<Map.Entry<String, JsonElement>> set = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> e : set) {
            JsonArray jsonArray = jsonObject.getAsJsonArray(e.getKey());
            List<HomeObj> homes = new ArrayList<>();
            for (JsonElement jsonElement : jsonArray) {
                JsonObject json = jsonElement.getAsJsonObject();
                String name = json.get("name").getAsString();
                String worldName = json.get("w").getAsString();
                int x = json.get("x").getAsInt();
                int y = json.get("y").getAsInt();
                int z = json.get("z").getAsInt();
                HomeObj newhome = new HomeObj();
                newhome.createHomeByXYZ(x, y, z, worldName, name);
                homes.add(newhome);
            }
            homesAndUsers.put(e.getKey(), homes);
        }
        return homesAndUsers;
    }
}
