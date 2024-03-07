/*
 * Copyright (C) 2024 js6pak
 *
 * This file is part of MojangFixStationAPI.
 *
 * MojangFixStationAPI is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, version 3.
 *
 * MojangFixStationAPI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with MojangFixStationAPI. If not, see <https://www.gnu.org/licenses/>.
 */

package pl.telvarost.mojangfixstationapi;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;

public class Config {

    @GConfig(value = "config", visibleName = "MojangFixStationAPI Config")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigName("Enable Authentication Changes")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableAuthenticationChanges = true;

        @ConfigName("Enable Bit Depth Fix")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableBitDepthFix = true;

        @ConfigName("Enable Controls Changes")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableControlsChanges = true;

        @ConfigName("Enable Chat Changes")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableChatChanges = true;

        @ConfigName("Enable Death Screen Score Fix")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableDeathScreenScoreFix = true;

        @ConfigName("Enable Debug Graph Changes")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableDebugGraphChanges = true;

        @ConfigName("Enable Debug Menu World Seed")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableDebugMenuWorldSeed = true;

        @ConfigName("Enable Inventory Changes")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableInventoryChanges = true;

        @ConfigName("Enable MojangFix Text On Title Screen")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableMojangFixTextOnTitleScreen = true;

        @ConfigName("Enable Multiplayer Server Changes")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableMultiplayerServerChanges = true;

        @ConfigName("Enable Skin Changes")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableSkinChanges = true;

        @ConfigName("Prioritize Mojang as Skin Provider")
        @Comment("Restart required for changes to take effect")
        public static Boolean prioMojangProvider = true;

        @ConfigName("Enable Wooden Sign Changes")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableWoodenSignChanges = true;

        @ConfigName("Enable Quit Button")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableQuitButton = true;

        @ConfigName("Minecraft Resources Download URL")
        @MaxLength(4096)
        @Comment("Restart required for changes to take effect")
        public static String RESOURCES_DOWNLOAD_URL = "http://mcresources.modification-station.net/MinecraftResources/";

        @ConfigName("Use Resources Download URL")
        @Comment("Restart required for changes to take effect")
        public static Boolean useResourcesDownloadURL = true;
    }
}
