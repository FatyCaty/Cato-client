package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class NoSlow extends Module {
	public NoSlow(){
		super("NoSlow", Keyboard.KEY_NONE , Category.MOVEMENT);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.isKeyDown());
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if (mc.thePlayer.onGround && mc.thePlayer.moveForward != 0 && (mc.thePlayer.isBlocking() || mc.thePlayer.isUsingItem())) {
                mc.thePlayer.motionX *= 1D;
                mc.thePlayer.motionY *= 0.2D;
                mc.thePlayer.motionZ *= 1D;
            }
            
            //if (mc.thePlayer.onGround && mc.thePlayer.moveForward != 0 && (mc.thePlayer.isBlocking() || mc.thePlayer.isUsingItem())) {
            //    mc.thePlayer.motionY = -1f;
            //}
		}
	}
	
	
}
