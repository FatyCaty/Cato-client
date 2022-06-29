package yusuf.cato.modules.render;
import org.lwjgl.input.Keyboard;

import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class FullBright extends Module {
	private float originalBrightness = mc.gameSettings.gammaSetting;
	
	public FullBright(){
		super("FullBright", Keyboard.KEY_I , Category.RENDER);
	}
	
	public void onEnable() {
		mc.gameSettings.gammaSetting = 100f;
	}
	
	public void onDisable() {
		mc.gameSettings.gammaSetting = originalBrightness;
	}
	
}
