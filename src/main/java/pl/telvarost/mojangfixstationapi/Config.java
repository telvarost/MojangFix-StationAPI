package pl.telvarost.mojangfixstationapi;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;

public class Config {

    @GConfig(value = "config", visibleName = "MiscTweaks Config")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigName("Minecraft Resources URL")
        @MaxLength(4096)
        @Comment("Restart required for changes to take effect")
        public static String RESOURCES_URL = "http://mcresources.modification-station.net/MinecraftResources/";

        @ConfigName("Enable Chat Changes")
        public static Boolean enableChatChanges = true;

//        @ConfigName("Enable Inventory Changes")
//        public static Boolean enableInventoryChanges = true;

//        @ConfigName("Enable Skin Changes")
//        public static Boolean enableSkinChanges = true;

        @ConfigName("Enable Wooden Sign Changes")
        public static Boolean enableWoodenSignChanges = true;
    }
}
