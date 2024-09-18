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

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.lwjgl.input.Keyboard;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import pl.telvarost.mojangfixstationapi.ModHelper;
import pl.telvarost.mojangfixstationapi.mixinterface.ChatScreenAccessor;
import pl.telvarost.mojangfixstationapi.mixinterface.TextFieldWidgetAccessor;

import static pl.telvarost.mojangfixstationapi.client.text.chat.ChatScreenVariables.*;

@Mixin(ChatScreen.class)
public class ChatScreenMixin extends Screen implements ChatScreenAccessor {
    @Shadow protected String text;

    public ChatScreen setInitialMessage(String message) {
        initialMessage = message;
        return (ChatScreen) (Object) this;
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        textField = new TextFieldWidget(this, textRenderer, 2, height - 14, width - 2, height - 2, initialMessage);
        textField.setFocused(true);
        textField.setMaxLength(100);
        chatHistoryPosition = 0;
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(CallbackInfo ci) {
        if (null != textField) {
            textField.tick();
        }
        ci.cancel();
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ChatScreen;drawStringWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V"))
    private void redirectDrawString(ChatScreen chatScreen, TextRenderer textRenderer, String text, int x, int y, int color) {
        drawStringWithShadow(textRenderer, "> " + ((TextFieldWidgetAccessor) textField).getDisplayText(), x, y, color);
    }

    @Redirect(method = "*", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ChatScreen;text:Ljava/lang/String;", opcode = Opcodes.GETFIELD))
    private String getMessage(ChatScreen chatScreen) {
        return textField.getText();
    }

    @Inject(method = "keyPressed", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ClientPlayerEntity;sendChatMessage(Ljava/lang/String;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onSendChatMessage(char character, int keyCode, CallbackInfo ci, String var3, String message) {
        int size = CHAT_HISTORY.size();
        if (size > 0 && CHAT_HISTORY.get(size - 1).equals(message)) {
            return;
        }

        CHAT_HISTORY.add(message);
    }

    private void setTextFromHistory() {
        textField.setText(CHAT_HISTORY.get(CHAT_HISTORY.size() + chatHistoryPosition));
        text = textField.getText();
    }

    @Inject(method = "keyPressed", at = @At(value = "JUMP", opcode = Opcodes.IF_ICMPNE, ordinal = 2), cancellable = true)
    private void onKeyPressedEntry(char character, int keyCode, CallbackInfo ci) {
        if (keyCode == Keyboard.KEY_UP && chatHistoryPosition > -CHAT_HISTORY.size()) {
            --chatHistoryPosition;
            setTextFromHistory();
            ci.cancel();
        } else if (keyCode == Keyboard.KEY_DOWN && chatHistoryPosition < -1) {
            ++chatHistoryPosition;
            setTextFromHistory();
            ci.cancel();
        }
    }

    @Inject(method = "keyPressed", at = @At("HEAD"))
    private void onKeyPressedHead(char character, int keyCode, CallbackInfo ci) {
        if (ModHelper.ModHelperFields.setClipboardText) {
            ModHelper.ModHelperFields.setClipboardText = false;
            text = textField.getText();
        }
        textField.setText(text);
    }

    @Inject(method = "keyPressed", at = @At("TAIL"))
    private void onKeyPressedTail(char character, int keyCode, CallbackInfo ci) {
        textField.keyPressed(character, keyCode);
    }
}
