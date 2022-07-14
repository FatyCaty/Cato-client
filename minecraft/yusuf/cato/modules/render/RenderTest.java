package yusuf.cato.modules.render;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventRenderGUI;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class RenderTest extends Module {
	
	
	
	public RenderTest(){
		super("RenderTest", Keyboard.KEY_NONE , Category.RENDER);
	}
	
	public void onEnable() {
		System.out.println("Amogus");
	}
	
	
	public void onDisable() {
		
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {
			FontRenderer fr = mc.fontRendererObj;
			
			fr.drawString("Amogus", 200, 8, -1);
			

		}
	}
	
	
}
