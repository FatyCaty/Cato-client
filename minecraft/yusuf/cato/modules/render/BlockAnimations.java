package yusuf.cato.modules.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.BlockPos;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.ModeSetting;

public class BlockAnimations extends Module {
	
	public ModeSetting animation = new ModeSetting("Block Animations", "Normal", "Normal", "Amongus");
	public static String currentAnimation;
	
	public BlockAnimations(){
		super("BlockAnimations", Keyboard.KEY_I , Category.RENDER);
		this.addSettings(animation);
	}
	
	public void onEnable() {
		if(animation.is("Normal")) {
			currentAnimation = "Normal";
		}
		if(animation.is("Amongus")) {
			currentAnimation = "Amongus";
		}
		
	}
	
	public void onDisable() {
		currentAnimation = "Normal";
	}
	
	/*
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				
			}
		}
	}
	*/
	
}
