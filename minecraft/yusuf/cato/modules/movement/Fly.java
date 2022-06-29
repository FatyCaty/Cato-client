package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.ModeSetting;

public class Fly extends Module {
	
	public static ModeSetting flyModeSetting = new ModeSetting("Fly modes", "NoRules", "NoRules", "OldVerusJumpFly", "CreativeCapabilities");
	private int flyTimer = 0;
	
	public Fly(){
		super("Fly", Keyboard.KEY_NONE , Category.MOVEMENT);
		this.addSettings(flyModeSetting);
	}
	
	public void onEnable() {
		
		
	}
	
	public void onDisable() {
		if(flyModeSetting.is("CreativeCapabilities")) {
			mc.thePlayer.capabilities.isFlying = false;
		}
		if(flyModeSetting.is("NoRules")) {
			mc.thePlayer.capabilities.isFlying = false;
		}
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(flyModeSetting.is("OldVerusJumpFly")) {
				if(e.isPre()) {
					flyTimer+=1;
					mc.thePlayer.onGround = true;
					if(flyTimer == 11){
						mc.thePlayer.jump();
						flyTimer = 0;
						
					}
				}
			}
			
			if(flyModeSetting.is("CreativeCapabilities")) {
				mc.thePlayer.capabilities.isFlying = true;
				mc.thePlayer.setSprinting(true);
			}
			if(flyModeSetting.is("NoRules")) {
				mc.thePlayer.capabilities.isFlying = true;
				mc.thePlayer.setSprinting(true);
				mc.thePlayer.onGround = true;
			
				//mc.thePlayer.moveFlying(flyTimer, flyTimer, flyTimer);
			}
		}
	}
	
	
}
