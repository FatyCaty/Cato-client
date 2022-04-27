package yusuf.cato.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import yusuf.cato.events.Event;
import yusuf.cato.settings.KeybindSetting;
import yusuf.cato.settings.Setting;

public class Module {

	public String name;
	public boolean toggled;
	public KeybindSetting keyCode = new KeybindSetting(0);
	public Category category;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public List<Setting> settings = new ArrayList<Setting>();
	
	public Module(String name, int key, Category c) {
		this.name = name;
		keyCode.code = key;
		this.category = c;
		this.addSettings(keyCode);
	}
	
	public void addSettings(Setting...settings) {
		this.settings.addAll(Arrays.asList(settings));
		this.settings.sort(Comparator.comparingInt(s -> s != keyCode ? 1 : 0));
	}
	
	public boolean isEnabled() {
		return toggled;
	}
	
	public int getKey() {
		return keyCode.code;
	}
	
	public void onEvent(Event e) {
		
	}
	
	public void toggle() {
		toggled = !toggled;
		if(toggled) {
			onEnable();
		} else {
			onDisable();
		}
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public enum Category{
		COMBAT("Combat"), MOVEMENT("Movement"), PLAYER("Player"), RENDER("Render");
		
		public String name;
		
		Category(String name){
			this.name = name;
		}
	}
}
