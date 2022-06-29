package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import yusuf.cato.Cato;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class InvMove extends Module {
	public InvMove(){
		super("InvMove", Keyboard.KEY_NONE , Category.MOVEMENT);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate && !(mc.currentScreen == null)) {
			KeyBinding[] key = { this.mc.gameSettings.keyBindForward, this.mc.gameSettings.keyBindBack, this.mc.gameSettings.keyBindRight, /*this.mc.gameSettings.keyBindSneak,*/ this.mc.gameSettings.keyBindJump, this.mc.gameSettings.keyBindLeft, this.mc.gameSettings.keyBindSprint };
            KeyBinding[] array;
            for(int length = (array = key).length, i = 0; i < length; ++i) {
                KeyBinding b = array[i];
                KeyBinding.setKeyBindState(b.getKeyCode(),   
                Keyboard.isKeyDown(b.getKeyCode()));
            }
		}
	}
}
