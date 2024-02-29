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

package pl.telvarost.mojangfixstationapi.mixin;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.api.gcapi.api.GCAPI;
import net.modificationstation.stationapi.api.util.Identifier;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import pl.telvarost.mojangfixstationapi.Config;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public final class MojangFixStationApiMixinPlugin implements IMixinConfigPlugin {

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        try {
            JsonObject configObject = Jankson
                    .builder()
                    .build()
                    .load(new File("config/mojangfixstationapi", "config.json"));

            Config.ConfigFields.enableChatChanges = configObject.getBoolean("enableChatChanges", true);
            Config.ConfigFields.enableWoodenSignChanges = configObject.getBoolean("enableWoodenSignChanges", true);
        } catch (IOException ex) {
            System.out.println("Couldn't read the config file" + ex.toString());
        } catch (SyntaxError error) {
            System.out.println("Couldn't read the config file" + error.getMessage());
            System.out.println(error.getLineMessage());
        }

        if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.inventory.ContainerScreenMixin")) {
            return !FabricLoader.getInstance().isModLoaded("inventorytweaks");
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.inventory.PlayerEntityMixin")) {
            return !FabricLoader.getInstance().isModLoaded("inventorytweaks");
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.chat.ChatScreenMixin")) {
            return Config.ConfigFields.enableChatChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.chat.SleepingChatScreenMixin")) {
            return Config.ConfigFields.enableChatChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.sign.ClientNetworkHandlerMixin")) {
            return Config.ConfigFields.enableWoodenSignChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.sign.SignBlockEntityMixin")) {
            return Config.ConfigFields.enableWoodenSignChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.sign.SignBlockEntityRendererMixin")) {
            return Config.ConfigFields.enableWoodenSignChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.sign.SignEditScreenMixin")) {
            return Config.ConfigFields.enableWoodenSignChanges;
        } else {
            return true;
        }
    }

    // Boilerplate

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
