package yusuf.cato.modules.render;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventRenderGUI;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.util.ColourUtil;

public class Keystrokes extends Module {
	
	public Keystrokes(){
		super("Keystrokes", Keyboard.KEY_NONE , Category.RENDER);
	}
	
	public void onEnable() {
		
	}
	
	
	public void onDisable() {
		
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {
			FontRenderer fr = mc.fontRendererObj;
			
			if(mc.gameSettings.keyBindForward.isKeyDown()) {
				Gui.drawRect(27, 100, 50, 120, 0x90000000);

			} else {
				Gui.drawRect(27, 100, 50, 120, 0x50000000);
			}
			
			
			if(mc.gameSettings.keyBindBack.isKeyDown()) {
				Gui.drawRect(27, 125, 50, 145, 0x90000000);
			} else {
				Gui.drawRect(27, 125, 50, 145, 0x50000000);
			}
			
			if(mc.gameSettings.keyBindRight.isKeyDown()) {
				Gui.drawRect(56, 125, 78, 145, 0x90000000);
			} else {
				Gui.drawRect(56, 125, 78, 145, 0x50000000);
			}
			
			if(mc.gameSettings.keyBindLeft.isKeyDown()) {
				Gui.drawRect(22, 125, 1, 145, 0x90000000);
			} else {
				Gui.drawRect(22, 125, 1, 145, 0x50000000);
			}
			
			if(mc.gameSettings.keyBindJump.isKeyDown()) {
				Gui.drawRect(78, 165, 1, 148, 0x90000000);
			} else {
				Gui.drawRect(78, 165, 1, 148, 0x50000000);
			}

			
			
			
		}
	}
	
	
}
