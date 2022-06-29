package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class Sprint extends Module {
	public Sprint(){
		super("Sprint", Keyboard.KEY_M , Category.MOVEMENT);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.isKeyDown());
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				if (mc.thePlayer.moveForward > 0 && !mc.thePlayer.isUsingItem() && !mc.thePlayer.isSneaking() && !mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isBlocking()){
					mc.thePlayer.setSprinting(true);

				}
			}
		}
	}
	
	
}
