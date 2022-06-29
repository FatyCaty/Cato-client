package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.ModeSetting;

public class Speed extends Module {
	
	public static ModeSetting speedModeSetting = new ModeSetting("Speed Modes", "Bhop", "Bhop", "LowHopTimer");
	
	public Speed(){
		super("Speed", Keyboard.KEY_N , Category.MOVEMENT);
		this.addSettings(speedModeSetting);
	}
	
	public void onEnable() {
		mc.timer.timerSpeed = mc.timer.timerSpeed + 0.5f;
	}
	
	
	public void onDisable() {
		mc.timer.timerSpeed = 1f;

	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				if(speedModeSetting.is("LowHopTimer")) {
					if(mc.thePlayer.onGround && mc.thePlayer.moveForward > 0) {
						mc.thePlayer.jump();
						
						mc.thePlayer.moveStrafing = 2f;
						mc.thePlayer.motionY = 0.2;
						System.out.println("Speed timer: " + mc.timer.timerSpeed);
						
						if (mc.thePlayer.isUsingItem() && mc.thePlayer.isSneaking() && mc.thePlayer.isCollidedHorizontally){
							mc.thePlayer.setSprinting(true);
	
						}
					}
				}
				
				if(speedModeSetting.is("Bhop")) {
					if(mc.thePlayer.onGround && mc.thePlayer.moveForward > 0) {
						
						mc.thePlayer.setSprinting(true);
						mc.thePlayer.jump();
						
						
					}
				}
			}
		}
	}
	
	
}
