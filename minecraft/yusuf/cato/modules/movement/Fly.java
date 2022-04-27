package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.ModeSetting;

public class Fly extends Module {
	
	ModeSetting flymode = new ModeSetting("Fly modes", "VerusJumpFly", "CreativeCapabilities");
	private int flyTimer = 0;
	
	public Fly(){
		super("Fly", Keyboard.KEY_G , Category.MOVEMENT);
		this.settings.add(flymode);
	}
	
	public void onEnable() {
		
		
	}
	
	public void onDisable() {
		if(flymode.is("CreativeCapabilities")) {
			mc.thePlayer.capabilities.isFlying = false;
		}
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(flymode.is("VerusJumpFly")) {
				if(e.isPre()) {
					
					
					flyTimer+=1;
					mc.thePlayer.onGround = true;
					if(flyTimer == 11){
						mc.thePlayer.jump();
						flyTimer = 0;
						
					}
					
					
				}
			}
			
			if(flymode.is("CreativeCapabilities")) {
				mc.thePlayer.capabilities.isFlying = true;
				mc.thePlayer.setSprinting(true);
			}
		}
	}
	
	
}
