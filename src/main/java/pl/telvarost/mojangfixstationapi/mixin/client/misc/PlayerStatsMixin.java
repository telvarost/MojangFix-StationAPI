/*
 * Copyright (C) 2025 js6pak
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

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.stat.PlayerStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import pl.telvarost.mojangfixstationapi.Config;

@Mixin(PlayerStats.class)
public class PlayerStatsMixin {

    @WrapOperation(
            method = "deserialize",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"
            )
    )
    private static boolean deserialize(String instance, Object string, Operation<Boolean> original) {
        boolean checksumMismatch = original.call(instance, string);

        checksumMismatch = checksumMismatch || Config.config.disableStatsChecksumVerification;

        return checksumMismatch;
    }
}
