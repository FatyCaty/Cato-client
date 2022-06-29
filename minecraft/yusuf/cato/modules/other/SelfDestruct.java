package yusuf.cato.modules.other;

import org.lwjgl.input.Keyboard;

import yusuf.cato.Cato;
import yusuf.cato.modules.Module;
import yusuf.cato.modules.Module.Category;
import yusuf.cato.settings.KeybindSetting;

public class SelfDestruct extends Module{
	public SelfDestruct() {
		super("SelfDestruct", Keyboard.KEY_NONE, Category.OTHER);
	}
	
	public void onEnable() {
		for(Module m : Cato.modules) {
			m.keyCode.code = Keyboard.KEY_NONE;
			m.toggled = false;
		}
	}
}
