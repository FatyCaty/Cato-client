package yusuf.cato.modules.render;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import yusuf.cato.Cato;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventKey;
import yusuf.cato.events.listeners.EventRenderGUI;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.font.FontUtil;
import yusuf.cato.font.MinecraftFontRenderer;
import yusuf.cato.modules.Module;
import yusuf.cato.util.ColourUtil;

public class TabGui extends Module {
	
	public int currentTab;
	public boolean expanded;
	
	public TabGui(){
		super("TabGui", Keyboard.KEY_NONE , Category.RENDER);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {
			FontRenderer fr = mc.fontRendererObj;
			MinecraftFontRenderer ffr = FontUtil.normal;

			Gui.drawRect(5, 30, 70, 30 + Module.Category.values().length * 16 + 2, 0x30000000);
			Gui.drawRect(5, 30 + currentTab * 16, 9 + 61, 36 + currentTab * 16 + 12, 0x90000000);
			
			int count = 0;
			for(Category c : Module.Category.values()) {
				ffr.drawString(c.name, 11, 35 + count*16, -1);
				count++;

			}
			if(expanded) {
				Category category = Module.Category.values()[currentTab];
				List<Module> modules = Cato.getModulesByCategory(category);
				
				if(modules.size() == 0) {
					return;
				}
				
				Gui.drawRect(70, 30, 70 + 68, 30 + modules.size() * 16 + 2, 0x30000000);
				Gui.drawRect(70, 33 + category.moduleIndex * 16, 7 + 61 + 68, 33 + category.moduleIndex * 16 + 12, 0x70000000);
				
				count = 0;
				for(Module m : modules) {
					fr.drawString(m.name, 73, 35 + count*16, -1);
					count++;

				}
				
			}
			
		}
		if(e instanceof EventKey) {
			int code = ((EventKey)e).code;
			Category category = Module.Category.values()[currentTab];
			List<Module> modules = Cato.getModulesByCategory(Module.Category.values()[currentTab]);

			
			if(code == Keyboard.KEY_UP) {
				if(expanded) {
					if(category.moduleIndex <= 0 || category.moduleIndex > modules.size() -2 ) {
						category.moduleIndex = 0;
					}
					else {
						category.moduleIndex++;
					}
				}
				else {
					if(currentTab <= 0) {
						currentTab = modules.size() - 1;
					}
					else {
						currentTab--;
					}
				}
			}
			
			if(code == Keyboard.KEY_DOWN) {
				if(expanded) {
					if(category.moduleIndex >= modules.size() - 1) {
						category.moduleIndex = 0;
					}
					else {
						category.moduleIndex++;
					}
				}
				else {
					if(currentTab >= Module.Category.values().length - 1) {
						currentTab = 0;
					}
					else {
						currentTab++;
					}
				}
			}
			
			if(code == Keyboard.KEY_RIGHT) {
				
				if(expanded) {
					
					//Stops user from disabling TabGui from TabGui
					
					if(!(modules.get(category.moduleIndex).name == "TabGui")) {
						modules.get(category.moduleIndex).toggle();
					}
				}
					
				else {
					expanded = true;

				}

			}
			
			if(code == Keyboard.KEY_LEFT) {
				expanded = false;
			}
			
			
			
		}
	}
	
}
