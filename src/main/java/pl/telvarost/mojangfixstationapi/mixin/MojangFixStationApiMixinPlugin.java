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

                Config.ConfigFields.enableAuthenticationChanges = configObject.getBoolean("enableAuthenticationChanges", true);
                Config.ConfigFields.enableControlsChanges = configObject.getBoolean("enableControlsChanges", true);
                Config.ConfigFields.enableBitDepthFix = configObject.getBoolean("enableBitDepthFix", true);
                Config.ConfigFields.enableDeathScreenScoreFix = configObject.getBoolean("enableDeathScreenScoreFix", true);
                Config.ConfigFields.enableDebugGraphChanges = configObject.getBoolean("enableDebugGraphChanges", true);
                Config.ConfigFields.enableDebugMenuWorldSeed = configObject.getBoolean("enableDebugMenuWorldSeed", true);
                Config.ConfigFields.enableQuitButton = configObject.getBoolean("enableQuitButton", true);
                Config.ConfigFields.useResourcesDownloadURL = configObject.getBoolean("useResourcesDownloadURL", true);
                Config.ConfigFields.prioritizeMojangProvider = configObject.getBoolean("prioritizeMojangProvider", false);
                Config.ConfigFields.enableMojangFixTextOnTitleScreen = configObject.getBoolean("enableMojangFixTextOnTitleScreen", true);
                Config.ConfigFields.enableInventoryChanges = configObject.getBoolean("enableInventoryChanges", true);
                Config.ConfigFields.enableMultiplayerServerChanges = configObject.getBoolean("enableMultiplayerServerChanges", true);
                Config.ConfigFields.enableSkinChanges = configObject.getBoolean("enableSkinChanges", true);
                Config.ConfigFields.enableChatChanges = configObject.getBoolean("enableChatChanges", true);
                Config.ConfigFields.enableWoodenSignChanges = configObject.getBoolean("enableWoodenSignChanges", true);
            } catch (IOException ex) {
                System.out.println("Couldn't read the config file" + ex.toString());
            } catch (SyntaxError error) {
                System.out.println("Couldn't read the config file" + error.getMessage());
                System.out.println(error.getLineMessage());
            }
        }

        if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.auth.ClientNetworkHandlerMixin")) {
            return Config.ConfigFields.enableAuthenticationChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.auth.SessionMixin")) {
            return Config.ConfigFields.enableAuthenticationChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.server.auth.ServerNetworkHandlerMixin")) {
            return Config.ConfigFields.enableAuthenticationChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.controls.ControlsOptionsScreenMixin")) {
            return Config.ConfigFields.enableControlsChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.controls.GameOptionsMixin")) {
            return Config.ConfigFields.enableControlsChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.controls.KeyBindingMixin")) {
            return Config.ConfigFields.enableControlsChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.inventory.ContainerScreenMixin")) {
            boolean isInventoryTweaksLoaded = FabricLoader.getInstance().isModLoaded("inventorytweaks");
            return (Config.ConfigFields.enableInventoryChanges && !isInventoryTweaksLoaded);
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.inventory.PlayerEntityMixin")) {
            boolean isInventoryTweaksLoaded = FabricLoader.getInstance().isModLoaded("inventorytweaks");
            return (Config.ConfigFields.enableInventoryChanges && !isInventoryTweaksLoaded);
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.BitDepthFixMixin")) {
            return Config.ConfigFields.enableBitDepthFix;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.DeathScreenMixin")) {
            return Config.ConfigFields.enableDeathScreenScoreFix;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.DebugGraphMixin")) {
            return Config.ConfigFields.enableDebugGraphChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.InGameHudMixin")) {
            return Config.ConfigFields.enableDebugMenuWorldSeed;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.MinecraftAppletMixin")) {
            return Config.ConfigFields.enableQuitButton;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.MinecraftMixin")) {
            return Config.ConfigFields.enableAuthenticationChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.ResourceDownloadThreadMixin")) {
            return Config.ConfigFields.useResourcesDownloadURL;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.ScreenMixin")) {
            return (Config.ConfigFields.enableControlsChanges || Config.ConfigFields.enableMultiplayerServerChanges);
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.misc.TitleScreenMixin")) {
            return Config.ConfigFields.enableMojangFixTextOnTitleScreen;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.multiplayer.ReturnToMainMenuMixin")) {
            return Config.ConfigFields.enableMultiplayerServerChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.multiplayer.TitleScreenMixin")) {
            return Config.ConfigFields.enableMultiplayerServerChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.BipedEntityModelMixin")) {
            return Config.ConfigFields.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.ClientPlayerEntityMixin")) {
            return Config.ConfigFields.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.EntityRenderDispatcherMixin")) {
            return Config.ConfigFields.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.ModelPartMixin")) {
            return Config.ConfigFields.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.OtherPlayerEntityMixin")) {
            return Config.ConfigFields.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.PlayerEntityMixin")) {
            return Config.ConfigFields.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.PlayerEntityRendererMixin")) {
            return Config.ConfigFields.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.SkinImageProcessorMixin")) {
            return Config.ConfigFields.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.skin.WorldRendererMixin")) {
            return Config.ConfigFields.enableSkinChanges;
        } else if (mixinClassName.equals("pl.telvarost.mojangfixstationapi.mixin.client.text.TextFieldWidgetMixin")) {
            return (Config.ConfigFields.enableMultiplayerServerChanges || Config.ConfigFields.enableChatChanges || Config.ConfigFields.enableWoodenSignChanges);
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
