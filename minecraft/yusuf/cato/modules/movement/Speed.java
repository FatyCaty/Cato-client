package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.ModeSetting;

public class Speed extends Module {
	
	ModeSetting speedMode = new ModeSetting("Speed Modes", "LowHop", "Bhop", "LowHop");
	
	public Speed(){
		super("Speed", Keyboard.KEY_N , Category.MOVEMENT);
		this.addSettings(speedMode);
	}
	
	public void onEnable() {
		
		
	}
	
	
	public void onDisable() {
		
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				if(speedMode.is("LowHop")) {
					if(mc.thePlayer.onGround && mc.thePlayer.moveForward > 0) {
						mc.thePlayer.jump();
						
						mc.thePlayer.moveStrafing = 2f;
						mc.thePlayer.motionY = 0.2;
						
						if (mc.thePlayer.isUsingItem() && mc.thePlayer.isSneaking() && mc.thePlayer.isCollidedHorizontally){
							mc.thePlayer.setSprinting(true);
	
						}
					}
				}
				
				if(speedMode.is("Bhop")) {
					mc.thePlayer.moveStrafing = 1f;
					if(mc.thePlayer.onGround && mc.thePlayer.moveForward > 0) {
						mc.thePlayer.motionY += 0.51;
						mc.thePlayer.moveStrafing += 2f;
						
					}
				}
			}
		}
	}
	
	
}
