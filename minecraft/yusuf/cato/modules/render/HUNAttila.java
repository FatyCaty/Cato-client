package yusuf.cato.modules.render;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventRenderGUI;
import yusuf.cato.modules.Module;
public class HUNAttila extends Module{
	public HUNAttila() {
		super("HUNAttila", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {
			this.mc.getTextureManager().bindTexture(new ResourceLocation("Cato/images/hunattila.png"));
	 		Gui.drawScaledCustomSizeModalRect(mc.displayWidth/4 -90, 5, 0, 10, 400, 91, 200, 100, 500, 100);
	 		//System.out.println(mc.displayWidth);
		}
		
	}
}
