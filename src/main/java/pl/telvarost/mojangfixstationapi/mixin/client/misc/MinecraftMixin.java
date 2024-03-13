/*
 * Copyright (C) 2022-2024 js6pak
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

package pl.telvarost.mojangfixstationapi.mixin.client.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.telvarost.mojangfixstationapi.client.MojangFixStationApiClientMod;
import pl.telvarost.mojangfixstationapi.mixinterface.ChatScreenAccessor;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    public abstract void setScreen(Screen screen);

    @Shadow
    public abstract boolean isWorldRemote();

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isWorldRemote()Z", ordinal = 0))
    private void onKey(CallbackInfo ci) {
        if (this.isWorldRemote() && Keyboard.getEventKey() == MojangFixStationApiClientMod.COMMAND_KEYBIND.code) {
            this.setScreen(((ChatScreenAccessor) new ChatScreen()).setInitialMessage("/"));
        }
    }

    @Inject(method = "startSessionCheck", at = @At("HEAD"), cancellable = true)
    private void disableSessionCheck(CallbackInfo ci) {
        ci.cancel();
    }
}
