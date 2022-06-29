package yusuf.cato.modules.render;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import yusuf.cato.clickgui.ClickGUI;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventRenderGUI;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.util.ColourUtil;

public class ClickGuiMod extends Module {
	
	private ClickGUI clickgui = new ClickGUI();
	
	public ClickGuiMod(){
		super("ClickGuiMod", Keyboard.KEY_RSHIFT , Category.RENDER);
		
	}
	
	public void onEnable() {
		mc.displayGuiScreen(clickgui);
	}
	
	
	public void onDisable() {
		
	}
	
	
	
}
