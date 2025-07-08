package pl.telvarost.mojangfixstationapi.mixin.client.multiplayer;

import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.Properties;

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {
    @Shadow private Properties translations;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        try {
            this.translations.load(TranslationStorage.class.getResourceAsStream("/assets/mojangfixstationapi/stationapi/lang/en_US.lang"));
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }
}
