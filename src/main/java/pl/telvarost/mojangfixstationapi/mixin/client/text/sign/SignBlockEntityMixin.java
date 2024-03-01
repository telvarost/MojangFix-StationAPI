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

package pl.telvarost.mojangfixstationapi.mixin.client.text.sign;

import lombok.Getter;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.telvarost.mojangfixstationapi.ModHelper;
import pl.telvarost.mojangfixstationapi.mixin.client.MinecraftAccessor;
import pl.telvarost.mojangfixstationapi.mixinterface.SignBlockEntityAccessor;

@Mixin(SignBlockEntity.class)
public class SignBlockEntityMixin extends BlockEntity implements SignBlockEntityAccessor {
    @Shadow
    public String[] texts;

    @Unique
    @Getter
    private final TextFieldWidget[] textFields = new TextFieldWidget[4];

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        for (int i = 0; i < texts.length; i++) {
            TextFieldWidget textField = textFields[i] = new TextFieldWidget(null, MinecraftAccessor.getInstance().textRenderer, -1, -1, -1, -1, texts[i]);
            textField.setMaxLength(15);
        }
    }

    @Inject(method = "readNbt", at = @At("RETURN"))
    private void onReadNbt(CallbackInfo ci) {
        for (int i = 0; i < texts.length; i++) {
            textFields[i].setText(texts[i]);
        }
    }

    @Redirect(method = "writeNbt", at = @At(value = "FIELD", target = "Lnet/minecraft/block/entity/SignBlockEntity;texts:[Ljava/lang/String;", args = "array=get"))
    private String getSignText(String[] signText, int i) {
        return textFields[i].getText();
    }
}
