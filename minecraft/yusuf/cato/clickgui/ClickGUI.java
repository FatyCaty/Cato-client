package yusuf.cato.clickgui;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import yusuf.cato.Cato;
import yusuf.cato.modules.Module.Category;
import yusuf.cato.settings.BooleanSetting;
import yusuf.cato.settings.KeybindSetting;
import yusuf.cato.settings.ModeSetting;
import yusuf.cato.settings.NumberSetting;
import yusuf.cato.settings.Setting;
import yusuf.cato.util.ColourUtil;

public class ClickGUI extends GuiScreen{
	public Minecraft mc = Minecraft.getMinecraft();

	private int oldX, oldY;
	private KeybindSetting listening;
	private NumberSetting dragging;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		//drawDefaultBackground();
		FontRenderer fr = mc.fontRendererObj;
		for (Category c : Category.values()) {
			boolean hovered = isInside(mouseX, mouseY, c.posX, c.posY, fr.getStringWidth(c.name), fr.FONT_HEIGHT);
			if (c.dragging) {
				c.posX += mouseX - oldX;
				c.posY += mouseY - oldY;
			}
			
			Gui.drawRect(c.posX, c.posY, c.posX + 70, c.posY + 20, -1);
			fr.drawString(c.name, c.posX + 35, c.posY - (fr.FONT_HEIGHT / 2) + 10, new ColourUtil().getRainbow(3, 3, 3));
			
			int x = c.posX + 4;
			int y = c.posY + 24;
			if (c.show) {
				int bgY = y;
				for (yusuf.cato.modules.Module m : Cato.getModulesByCategory(c))
					bgY += 12;
				Gui.drawRect(x - 4, c.posY + 20, x + 66, bgY - 1, 0x80000000);
				for (yusuf.cato.modules.Module m : Cato.getModulesByCategory(c)) {
					fr.drawStringWithShadow(m.name, x, y, m.toggled ? new ColourUtil().getRainbow(3, 3, 3) : -1);
					int settingY = y;
					int settingX = x + 74; 
					if (!m.settings.isEmpty() && m.showSettings) {
						for (Setting setting : m.settings) {
							
							if (setting instanceof BooleanSetting)
								fr.drawStringWithShadow(setting.name, settingX, settingY,((BooleanSetting) setting).enabled ? new ColourUtil().getRainbow(3, 3, 3) : -1);
							
							if (setting instanceof ModeSetting)
								fr.drawStringWithShadow(setting.name + ": " + ((ModeSetting) setting).getMode(), settingX, settingY, -1);
							
							if (setting instanceof NumberSetting) {
								NumberSetting s = (NumberSetting) setting;
								
								double min = s.getMinimum();
								double max = s.getmaximum();
								double length = 90;
								
								double renderX = length * (s.getValue() - min) / (max - min);
								double renderWidth = length * (s.getmaximum() - min) / (max - min);
								double diff = Math.min(length, Math.max(0, mouseX - settingX));
								
								if (s.dragging) {
									if (diff == 0) {
										s.setValueWithoutIncrement(s.getMinimum());
										System.out.println(s.getMinimum());
									}
									
									else {
										double value = roundToPlace(((diff / length) * (max - min) + min), BigDecimal.valueOf(s.getIncrement()).scale());
										s.setValueWithoutIncrement(value);
										//System.out.println(value);
									}
								}
								
								Gui.drawRectFloat(settingX, settingY, (float) (settingX + renderX), settingY + fr.FONT_HEIGHT,(new ColourUtil().getRainbow(3, 3, 3)));
								
								fr.drawStringWithShadow(s.name + ": " + s.getValue(), settingX, settingY, -1);
							}
								
							if (setting instanceof KeybindSetting)
								fr.drawStringWithShadow("Bind: " + (listening == setting ? "LISTENING" : Keyboard.getKeyName(((KeybindSetting) setting).getKeyCode())), settingX, settingY, (listening == setting ? new ColourUtil().getRainbow(3, 3, 3) : -1));
							
							
							settingY += 12;
						}
					} 
					y += 12;
				}
			}
			
		}
		oldX = mouseX;
		oldY = mouseY;
	}

	
	
	private double roundToPlace(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}



	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		FontRenderer fr = mc.fontRendererObj;
		for (Category c : Category.values()) {
			int x = c.posX;
			int y = c.posY + 25;
			if (c.show) {
				for (yusuf.cato.modules.Module m : Cato.getModulesByCategory(c)) {

					int settingY = y;
					int settingX = x + 74; 
					if (!m.settings.isEmpty() && m.showSettings) {
						for (Setting s : m.settings) {
							if (isInside(mouseX, mouseY, settingX, settingY, 90, fr.FONT_HEIGHT + 1)) {
							
								if (s instanceof BooleanSetting)
									((BooleanSetting) s).toggle();
								
								if (s instanceof ModeSetting)
									((ModeSetting) s).multiDirectionalCycle(mouseButton == 0);
							
								if (s instanceof KeybindSetting)
									listening = (KeybindSetting) s;
								
								if (s instanceof NumberSetting && mouseButton == 0)
									((NumberSetting)s).dragging = true;
								
								return;
								
							}
								
							settingY += 12;
						}
					}
					
					if (isInside(mouseX, mouseY, x, y, 70, fr.FONT_HEIGHT + 1)) {
						
						if (mouseButton == 0)
							m.toggle();
						
						else if (mouseButton == 1) {
							if (!m.showSettings) {
								for (yusuf.cato.modules.Module mod : Cato.modules) {
									if (mod.name.equalsIgnoreCase(m.name)) continue;
									mod.showSettings = false;
								}
								m.showSettings = true;
							} else
								m.showSettings = false;
						}
						return;
					}
					y += 12;
				}
			}
			if (isInside(mouseX, mouseY, c.posX, c.posY, 70, 20)) {
				
				if (mouseButton == 0)
					c.dragging = true;
				
				else if (mouseButton == 1)
					c.show = !c.show;
				
				return;
			}
		}
	}



	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		for (Category c : Category.values()) {
			c.dragging = false;
		}
		
		for (yusuf.cato.modules.Module m : yusuf.cato.Cato.modules)
			for (Setting s : m.settings)
				if (s instanceof NumberSetting)
					((NumberSetting)s).dragging = false;
		
	}
	
	
	
	@Override
	public void initGui() {
		
	}



	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (listening != null) {
			if (keyCode == 1)
				listening.setKeyCode(Keyboard.KEY_NONE);
			else
			 listening.setKeyCode(keyCode);
			listening = null;
		} else if (keyCode == 1) {
			Cato.getModulesByName("ClickGuiMod").toggled = false;
			for (Category c : Category.values()) {
				c.dragging = false;
			}
			this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
		}
			
	}



	public boolean doesGuiPauseGame() {return false;}
	
	public boolean isInside(int mouseX, int mouseY, double x, double y, double width, double height) {
		return (mouseX >= x && mouseX <= x + width) && (mouseY >= y && mouseY <= y + height);
	}
}
