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

package pl.telvarost.mojangfixstationapi.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;

public class MojangFixStationApiClientMod implements ClientModInitializer {
    public final static KeyBinding COMMAND_KEYBIND = new KeyBinding("Command", Keyboard.KEY_SLASH);

    @Override
    public void onInitializeClient() {
    }
}
