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

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.telvarost.mojangfixstationapi.Config;
import pl.telvarost.mojangfixstationapi.MojangFixStationApiMod;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Inject(method = "render", at = @At("RETURN"))
    private void onDrawScreen(CallbackInfo ci) {
        if (Config.config.enableMojangFixTextOnTitleScreen) {
            this.drawTextWithShadow(this.textRenderer, MojangFixStationApiMod.getMetadata().getName() + " " + MojangFixStationApiMod.getVersion(), 2, 2 + 10, 0x505050);
        }
    }
}
