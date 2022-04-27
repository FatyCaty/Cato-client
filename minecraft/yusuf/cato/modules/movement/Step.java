package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.BooleanSetting;
import yusuf.cato.settings.NumberSetting;

public class Step extends Module {
	
	public Step(){
		super("Step", Keyboard.KEY_U , Category.MOVEMENT);
		
	}
	
	public void onEnable() {
		
		mc.thePlayer.stepHeight = 1.5f;
		
			
		
	}
	
	public void onDisable() {
		mc.thePlayer.stepHeight = 1f;
	}
}
