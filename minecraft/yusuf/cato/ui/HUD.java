package yusuf.cato.ui;

import java.awt.Color;
import java.util.Comparator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import yusuf.cato.Cato;
import yusuf.cato.events.listeners.EventRenderGUI;



public class HUD {
	
	public Minecraft mc = Minecraft.getMinecraft();

	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		
		/*
		 * Nefarious intents method of sorting which I dont use lol
		 */
		//Cato.modules.sort(Comparator.comparingInt(m -> mc.fontRendererObj.getStringWidth(((Module)m).name)));
		
		
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(1, 1, 1);
		
		fr.drawString(Cato.name, 4, 4, -1);
		GlStateManager.popMatrix();
		
		//this.mc.getTextureManager().bindTexture(new ResourceLocation("catoassets//CatoLogo.png"));
		//Gui.drawModalRectWithCustomSizedTexture(1 - 1, (int) (2 - 2.5), 0, 0, 86, 49, 86, 49);
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(30, 10, 0);
		GlStateManager.scale(0.5, 0.5, 1);
		fr.drawString("CatLover Edition <3", 4, 4, -2);
		GlStateManager.popMatrix();
		
		
		
		
		
		fr.drawString(Cato.version, 2, 230, -1);
		
		int count = 0;
		for(yusuf.cato.modules.Module m : Cato.modules) {
			if(!m.toggled) {
				continue;
			}
			
			//int var storing offset
			int offset = count*(fr.FONT_HEIGHT + 6);

			//ArrayList background rendering
			//Gui.drawRect((int)sr.getScaledWidth() - fr.getStringWidth(m.name) - 12, 5  + count*(fr.FONT_HEIGHT + 3),sr.getScaledWidth() - 4, 8 + fr.FONT_HEIGHT + offset,0x30888888);
			//ArrayList text rendering
			fr.drawString(m.name, sr.getScaledWidth() - fr.getStringWidth(m.name) - 8 , 8 + count*(fr.FONT_HEIGHT + 4), -1);
			
			count++;
		}
		
		Cato.onEvent(new EventRenderGUI());
	}
	
}