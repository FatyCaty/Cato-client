package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.server.S12PacketEntityVelocity;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;



public class Velocity extends Module {
	
	float hVelocity = 0f;
	float vVelocity = 0f;
	//float zVelocity = hVelocity;

	
	public Velocity(){
		super("Velocity", Keyboard.KEY_M , Category.MOVEMENT);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(mc.thePlayer.hurtTime == mc.thePlayer.maxHurtTime && mc.thePlayer.maxHurtTime > 0) {
				mc.thePlayer.motionX *= hVelocity / 100f;
				mc.thePlayer.motionX *= vVelocity / 100f;
				mc.thePlayer.motionZ *= hVelocity / 100f;
			}
			
		}
	}
	
	
}
