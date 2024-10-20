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
import net.glasslauncher.mods.gcapi3.api.*;

public class Config {

    @ConfigRoot(value = "config", visibleName = "MojangFixStationAPI")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigEntry(
                name = "Disable Server List IP Addresses",
                description = "Hides IP addresses for the server list only"
        )
        public Boolean disableServerListIpAddresses = false;

        @ConfigEntry(
                name = "Enable Authentication Changes",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableAuthenticationChanges = true;

        @ConfigEntry(
                name = "Enable Bit Depth Fix",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableBitDepthFix = true;

        @ConfigEntry(
                name = "Enable Controls Changes",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableControlsChanges = true;

        @ConfigEntry(
                name = "Enable Chat Changes",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableChatChanges = true;

        @ConfigEntry(
                name = "Enable Death Screen Score Fix",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableDeathScreenScoreFix = true;

        @ConfigEntry(
                name = "Enable Debug Graph Changes",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableDebugGraphChanges = true;

        @ConfigEntry(
                name = "Enable Debug Graph Toggle As KEYBIND+F3",
                description = "False=Toggle debug graph with just KEYBIND"
        )
        public Boolean enableDebugGraphModernToggle = true;

        @ConfigEntry(
                name = "Enable Debug Menu World Seed",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableDebugMenuWorldSeed = true;

        @ConfigEntry(
                name = "Enable Inventory Changes",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableInventoryChanges = true;

        @ConfigEntry(
                name = "Enable MojangFix Text On Title Screen"
        )
        public Boolean enableMojangFixTextOnTitleScreen = true;

        @ConfigEntry(
                name = "Enable Multiplayer Server Changes",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableMultiplayerServerChanges = true;

        @ConfigEntry(
                name = "Enable Skin Changes",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableSkinChanges = true;

        @ConfigEntry(
                name = "Enable Wooden Sign Changes",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableWoodenSignChanges = true;

        @ConfigEntry(
                name = "Enable Quit Button",
                description = "Restart required for changes to take effect"
        )
        public Boolean enableQuitButton = true;

        @ConfigEntry(
                name = "Minecraft Resources Download URL",
                description = "Restart required for changes to take effect",
                maxLength = 4096
        )
        public String RESOURCES_DOWNLOAD_URL = "http://mcresources.modification-station.net/MinecraftResources/";

        @ConfigEntry(
                name = "Render Player Capes"
        )
        public Boolean renderCape = true;

        @ConfigEntry(
                name = "Raise Slim Skin Shoulders",
                description = "Restart required for changes to take effect"
        )
        public Boolean raiseSlimSkinShoulders = false;

        @ConfigEntry(
                name = "Use Resources Download URL",
                description = "Restart required for changes to take effect"
        )
        public Boolean useResourcesDownloadURL = true;
    }
}
