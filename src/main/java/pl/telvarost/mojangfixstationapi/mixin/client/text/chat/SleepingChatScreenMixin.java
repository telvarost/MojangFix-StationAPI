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

package pl.telvarost.mojangfixstationapi.mixin.client.text.chat;

import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.SleepingChatScreen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SleepingChatScreen.class)
public class SleepingChatScreenMixin extends ChatScreen {
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;enableRepeatEvents(Z)V", remap = false))
    private void redirectEnableInput(boolean enable) {
        super.init();
    }

    @Redirect(method = "removed", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;enableRepeatEvents(Z)V", remap = false))
    private void redirectDisableInput(boolean enable) {
        super.removed();
    }

    @ModifyConstant(method = "keyPressed", constant = @Constant(intValue = Keyboard.KEY_RETURN))
    private int ignoreEnter(int def) {
        return -1;
    }
}
