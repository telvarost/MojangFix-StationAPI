package pl.telvarost.mojangfixstationapi;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.option.KeyBinding;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import org.lwjgl.input.Keyboard;

public class KeyBindingListener {
    public static KeyBinding toggleDebugScreenPerformanceGraph;

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        if (Config.ConfigFields.enableDebugGraphChanges) {
            event.keyBindings.add(toggleDebugScreenPerformanceGraph = new KeyBinding("Toggle Debug Graph", Keyboard.KEY_P));
        }
    }
}
