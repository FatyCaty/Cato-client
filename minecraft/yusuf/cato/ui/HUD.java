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
import yusuf.cato.font.FontUtil;
import yusuf.cato.font.MinecraftFontRenderer;
import yusuf.cato.modules.render.ClickGuiMod;
import yusuf.cato.util.ColourUtil;



public class HUD {
	
	public Minecraft mc = Minecraft.getMinecraft();

	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		MinecraftFontRenderer ffr = FontUtil.normal;

		
		Cato.modules.sort(Comparator.comparingInt(m -> (int)-(ffr.getStringWidth((m).name))));
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(1, 1, 1);
		ffr.drawString("Catoware", 9, 14, -1);
		GlStateManager.popMatrix();
		
		
		/*this.mc.getTextureManager().bindTexture(new ResourceLocation("Cato/watermark/watermark.png"));
		 		Gui.drawScaledCustomSizeModalRect(5, -6, 0, 1, 50, 50, 50, 50, 50, 50);
		 */
		
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
			ffr.drawString(m.name, sr.getScaledWidth() - ffr.getStringWidth(m.name) - 4, 4 + count*(ffr.getHeight() + 4), new ColourUtil().getRainbowWave(5, 1, 1, count *125));
			count++;
		}
		Cato.onEvent(new EventRenderGUI());
		
	}
	
}