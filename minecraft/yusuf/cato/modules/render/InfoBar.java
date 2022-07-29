package yusuf.cato.modules.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.BlockPos;
import yusuf.cato.Cato;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventRenderGUI;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.font.FontUtil;
import yusuf.cato.font.MinecraftFontRenderer;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.ModeSetting;
import yusuf.cato.util.ColourUtil;

public class InfoBar extends Module {
	
	FontRenderer fr = mc.fontRendererObj;

	public InfoBar(){
		super("InfoBar", Keyboard.KEY_I , Category.RENDER);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {
			fr.drawString("Version: " + Cato.version, 8, 35 /*+ 10*/, -1);
			fr.drawString("FPS: " + mc.getDebugFPS(), 8, 35 + 10, -1);

			Gui.drawRect(5, 30, 90, 32, new ColourUtil().getRainbowWave(3, 1, 1, 30));
			Gui.drawRect(5, 30, 90, 75, 0x40000000);
			
		}
	}
}
 