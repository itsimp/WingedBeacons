package com.imp.wingedbeacons;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.util.internal.SuppressJava6Requirement;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WingedBeaconsConfig {

    public static WingedBeaconsConfig INSTANCE = new WingedBeaconsConfig();

    private static final File configDir = new File("config");
    private static final File configFile = new File("config/" + WingedBeacons.MODID + ".json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().serializeNulls().create();

    public int xpLevelRequired = 30;
    public int slowFallingTime = 10;
    public String advancementRequired = "minecraft:end/elytra";

    public static void loadConfig() {
        try {
            configDir.mkdirs();

            if (configFile.createNewFile()) {
                FileWriter fw = new FileWriter(configFile);
                fw.append(gson.toJson(INSTANCE));
                fw.close();
                System.out.println("Default config generated.");
            } else {
                FileReader fr = new FileReader(configFile);
                INSTANCE = gson.fromJson(fr, WingedBeaconsConfig.class);
                fr.close();
                System.out.println("Config loaded.");

                if (INSTANCE.advancementRequired.isEmpty()) {
                    INSTANCE.advancementRequired = "minecraft:end/elytra";
                    saveConfig();
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading config!");
        }
    }

    public static void saveConfig() {
        try {
            configDir.mkdirs();

            FileWriter fw = new FileWriter(configFile);
            fw.append(gson.toJson(INSTANCE));
            fw.close();
            System.out.println("Saved config.");
        } catch (Exception e) {
            System.out.println("Failed to save config.");
        }
    }

}
