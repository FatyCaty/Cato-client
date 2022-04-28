package yusuf.cato.modules.movement;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.BlockPos;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class noSlow extends Module {
	public noSlow(){
		super("noSlow", Keyboard.KEY_M , Category.MOVEMENT);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			
		}
	}
	
	
}
