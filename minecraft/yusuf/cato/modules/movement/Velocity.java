package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.server.S12PacketEntityVelocity;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventRecivePacket;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.BooleanSetting;
import yusuf.cato.settings.NumberSetting;



public class Velocity extends Module {
	
	BooleanSetting cancelPacket = new BooleanSetting("CancelPacket", true);
	NumberSetting hVelocitySetting = new NumberSetting("Horiziontal Velocity", 0, 0, 100, 1);
	NumberSetting vVelocitySetting = new NumberSetting("Vertical Velocity", 0, 0, 100, 1);
	
	
	public Velocity(){
		super("Velocity", Keyboard.KEY_NONE , Category.MOVEMENT);
		this.addSettings(cancelPacket, hVelocitySetting, vVelocitySetting);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	
	public void onEvent(Event e) {
		if (e instanceof EventRecivePacket && e.isPre()) {
            EventRecivePacket event = (EventRecivePacket) e;
            if (event.getPacket() instanceof S12PacketEntityVelocity) {
                if(cancelPacket.isEnabled()) { e.setCancelled(true);}
                else if(!(cancelPacket.isEnabled()) && (mc.thePlayer.hurtTime == mc.thePlayer.maxHurtTime && mc.thePlayer.maxHurtTime > 0)){
                	mc.thePlayer.motionX *=  hVelocitySetting.getValue() /100;
                	mc.thePlayer.motionY *= vVelocitySetting.getValue() /100;
                	mc.thePlayer.motionZ *=  hVelocitySetting.getValue()/100;
                }
                
            	
            }
        }
	}
	
	
}
