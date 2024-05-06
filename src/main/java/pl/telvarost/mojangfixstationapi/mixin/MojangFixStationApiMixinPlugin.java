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
import pl.telvarost.mojangfixstationapi.ModHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public final class MojangFixStationApiMixinPlugin implements IMixinConfigPlugin {

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (ModHelper.ModHelperFields.loadMixinConfigs) {
            ModHelper.ModHelperFields.loadMixinConfigs = false;

            try {
                JsonObject configObject = Jankson
                        .builder()
                        .build()
                        .load(new File("config/mojangfixstationapi", "config.json"));

                Config.config.enableAuthenticationChanges = configObject.getBoolean("enableAuthenticationChanges", true);
                Config.config.enableControlsChanges = configObject.getBoolean("enableControlsChanges", true);
                Config.config.enableBitDepthFix = configObject.getBoolean("enableBitDepthFix", true);
                Config.config.enableDeathScreenScoreFix = configObject.getBoolean("enableDeathScreenScoreFix", true);
                Config.config.enableDebugGraphChanges = configObject.getBoolean("enableDebugGraphChanges", true);
                Config.config.enableDebugMenuWorldSeed = configObject.getBoolean("enableDebugMenuWorldSeed", true);
                Config.config.enableQuitButton = configObject.getBoolean("enableQuitButton", true);
                Config.config.useResourcesDownloadURL = configObject.getBoolean("useResourcesDownloadURL", true);
                Config.config.prioritizeMojangProvider = configObject.getBoolean("prioritizeMojangProvider", false);
                Config.config.enableMojangFixTextOnTitleScreen = configObject.getBoolean("enableMojangFixTextOnTitleScreen", true);
                Config.config.enableInventoryChanges = configObject.getBoolean("enableInventoryChanges", true);
                Config.config.enableMultiplayerServerChanges = configObject.getBoolean("enableMultiplayerServerChanges", true);
                Config.config.enableSkinChanges = configObject.getBoolean("enableSkinChanges", true);
                Config.config.enableChatChanges = configObject.getBoolean("enableChatChanges", true);
                Config.config.enableWoodenSignChanges = configObject.getBoolean("enableWoodenSignChanges", true);
            } catch (IOException ex) {
                System.out.println("Couldn't read the config file" + ex.toString());
            } catch (SyntaxError error) {
                System.out.println("Couldn't read the config file" + error.getMessage());
                System.out.println(error.getLineMessage());
            }
        }

        if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.auth.ClientNetworkHandlerMixin")) {
            return Config.config.enableAuthenticationChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.auth.SessionMixin")) {
            return Config.config.enableAuthenticationChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.server.auth.ServerNetworkHandlerMixin")) {
            return Config.config.enableAuthenticationChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.controls.ControlsOptionsScreenMixin")) {
            return Config.config.enableControlsChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.controls.GameOptionsMixin")) {
            return Config.config.enableControlsChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.controls.KeyBindingMixin")) {
            return Config.config.enableControlsChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.inventory.ContainerScreenMixin")) {
            boolean isInventoryTweaksLoaded = FabricLoader.getInstance().isModLoaded("inventorytweaks");
            return (Config.config.enableInventoryChanges && !isInventoryTweaksLoaded);
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.inventory.PlayerEntityMixin")) {
            boolean isInventoryTweaksLoaded = FabricLoader.getInstance().isModLoaded("inventorytweaks");
            return (Config.config.enableInventoryChanges && !isInventoryTweaksLoaded);
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.BitDepthFixMixin")) {
            boolean isUniTweaksLoaded = FabricLoader.getInstance().isModLoaded("unitweaks");
            return (Config.config.enableBitDepthFix && !isUniTweaksLoaded);
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.DeathScreenMixin")) {
            return Config.config.enableDeathScreenScoreFix;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.DebugGraphMixin")) {
            return Config.config.enableDebugGraphChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.InGameHudMixin")) {
            return Config.config.enableDebugMenuWorldSeed;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.MinecraftAppletMixin")) {
            boolean isUniTweaksLoaded = FabricLoader.getInstance().isModLoaded("unitweaks");
            return (Config.config.enableQuitButton && !isUniTweaksLoaded);
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.MinecraftMixin")) {
            return Config.config.enableAuthenticationChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.ResourceDownloadThreadMixin")) {
            return Config.config.useResourcesDownloadURL;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.ScreenMixin")) {
            return (Config.config.enableControlsChanges || Config.config.enableMultiplayerServerChanges);
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.TitleScreenMixin")) {
            return Config.config.enableMojangFixTextOnTitleScreen;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.multiplayer.ReturnToMainMenuMixin")) {
            return Config.config.enableMultiplayerServerChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.multiplayer.TitleScreenMixin")) {
            return Config.config.enableMultiplayerServerChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.BipedEntityModelMixin")) {
            return Config.config.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.ClientPlayerEntityMixin")) {
            return Config.config.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.EntityRenderDispatcherMixin")) {
            return Config.config.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.ModelPartMixin")) {
            return Config.config.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.OtherPlayerEntityMixin")) {
            return Config.config.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.PlayerEntityMixin")) {
            return Config.config.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.PlayerEntityRendererMixin")) {
            return Config.config.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.SkinImageProcessorMixin")) {
            return Config.config.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.WorldRendererMixin")) {
            return Config.config.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.TextFieldWidgetMixin")) {
            return (Config.config.enableMultiplayerServerChanges || Config.config.enableChatChanges || Config.config.enableWoodenSignChanges);
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.chat.ChatScreenMixin")) {
            return Config.config.enableChatChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.chat.SleepingChatScreenMixin")) {
            return Config.config.enableChatChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.sign.ClientNetworkHandlerMixin")) {
            return Config.config.enableWoodenSignChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.sign.SignBlockEntityMixin")) {
            return Config.config.enableWoodenSignChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.sign.SignBlockEntityRendererMixin")) {
            return Config.config.enableWoodenSignChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.sign.SignEditScreenMixin")) {
            return Config.config.enableWoodenSignChanges;
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
