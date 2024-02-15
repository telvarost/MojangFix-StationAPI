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

package pl.telvarost.mojangfixstationapi.client.gui;

import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;

import java.util.function.Consumer;

public class CallbackConfirmScreen extends ConfirmScreen {
    private final Consumer<Boolean> callback;

    public CallbackConfirmScreen(Screen screen, String title, String message, String yes, String no, Consumer<Boolean> callback) {
        super(screen, title, message, yes, no, -1);
        this.callback = callback;
    }

    @Override
    protected void buttonClicked(ButtonWidget button) {
        this.callback.accept(button.id == 0);
    }
}
