/*
 * Copyright (C) 2022 js6pak
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

package pl.telvarost.mojangfixstationapi.mixin.client.skin;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.telvarost.mojangfixstationapi.mixinterface.PlayerEntityRendererAccessor;
import pl.telvarost.mojangfixstationapi.client.skinfix.PlayerEntityModel;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer implements PlayerEntityRendererAccessor {
    @Shadow
    private BipedEntityModel bipedModel;

    public PlayerEntityRendererMixin(EntityModel arg, float f) {
        super(arg, f);
    }

    public void setThinArms(boolean thinArms) {
        this.model = this.bipedModel = new PlayerEntityModel(0.0F, thinArms);
    }

    @Inject(method = "renderHand", at = @At("RETURN"))
    private void fixFirstPerson(CallbackInfo ci) {
        ((PlayerEntityModel) bipedModel).rightSleeve.render(0.0625F);
    }
}
