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
