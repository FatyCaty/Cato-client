package yusuf.cato.modules.render;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventMotion;
import yusuf.cato.events.listeners.EventRenderGUI;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class Derp extends Module {
	
	
	
	public Derp(){
		super("Derp", Keyboard.KEY_NONE , Category.RENDER);
	}
	
	public void onEnable() {
		
	}
	
	
	public void onDisable() {
		
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
			int count = 0;
		
			((EventMotion)e).yaw+= 5;

		}
	}
	
	
}
