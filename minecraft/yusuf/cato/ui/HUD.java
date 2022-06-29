package yusuf.cato.ui;

import java.awt.Color;
import java.util.Comparator;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import yusuf.cato.Cato;
import yusuf.cato.events.listeners.EventRenderGUI;
import yusuf.cato.modules.render.ClickGuiMod;
import yusuf.cato.util.ColourUtil;



public class HUD {
	
	public Minecraft mc = Minecraft.getMinecraft();

	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		
		Cato.modules.sort(Comparator.comparingInt(m -> -(mc.fontRendererObj.getStringWidth((m).name))));
		GlStateManager.pushMatrix();
		GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(1, 1, 1);
		Gui.drawRect(2, 1,68, 2, new ColourUtil().getRainbow(3, 3, 3));
		Gui.drawRect(2, 2,68, 15, 0x90000000);
		fr.drawString("C", 4, 4, new ColourUtil().getRainbow(3, 3, 3));
		fr.drawString("ato " + Cato.version, 10, 4, -1);
		GlStateManager.popMatrix();
		
		//this.mc.getTextureManager().bindTexture(new ResourceLocation("catoassets//CatoLogo.png"));
		//Gui.drawModalRectWithCustomSizedTexture(1 - 1, (int) (2 - 2.5), 0, 0, 86, 49, 86, 49);
		
		/*
		GlStateManager.pushMatrix();
		GlStateManager.translate(30, 10, 0);
		GlStateManager.scale(0.8, 0.8, 1);
		GlStateManager.popMatrix();
		*/
		
		//fr.drawString(m.name, sr.getScaledWidth() - fr.getStringWidth(m.name) - 10 , 8 + count*(fr.FONT_HEIGHT + 4), new ColourUtil().getRainbow(5, 3, 3));

		int count = 0;
		int offset = count*(fr.FONT_HEIGHT + 6);
		for(yusuf.cato.modules.Module m : Cato.modules) {
			if(!m.toggled) {
				continue;
			}
			fr.drawString(m.name, sr.getScaledWidth() - fr.getStringWidth(m.name) - 4, 4 + count*(fr.FONT_HEIGHT + 4), new ColourUtil().getRainbow(5, 2, 5));
			count++;
		}
		Cato.onEvent(new EventRenderGUI());
		
	}
	
}