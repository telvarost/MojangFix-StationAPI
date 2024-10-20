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

package pl.telvarost.mojangfixstationapi.mixin.client.skin;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.telvarost.mojangfixstationapi.Config;
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

    @Inject(method = "renderHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPart;render(F)V"))
    private void fixFirstPerson$1(CallbackInfo ci) {
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Inject(method = "renderHand", at = @At("RETURN"))
    private void fixFirstPerson$2(CallbackInfo ci) {
        ((PlayerEntityModel) bipedModel).rightSleeve.render(0.0625F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    @Inject(method = "render(Lnet/minecraft/entity/player/PlayerEntity;DDDFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;render(Lnet/minecraft/entity/LivingEntity;DDDFF)V"))
    private void fixOuterLayer$1(CallbackInfo ci) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Inject(method = "render(Lnet/minecraft/entity/player/PlayerEntity;DDDFF)V", at = @At("RETURN"))
    private void fixOuterLayer$2(CallbackInfo ci) {
        GL11.glDisable(GL11.GL_BLEND);
    }


    @Redirect(method = "renderMore(Lnet/minecraft/entity/player/PlayerEntity;F)V",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;renderCape(F)V"
            )
    )
    protected void mojangFixStationApi_method_827(BipedEntityModel instance, float v) {
        if (Config.config.renderCape) {
            instance.renderCape(v);
        } else {
            return;
        }
    }
}
