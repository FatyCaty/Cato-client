package yusuf.cato.modules.player;
import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C03PacketPlayer;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class FastPlace extends Module {
	private int originalDelay = mc.rightClickDelayTimer;
	
	
	public FastPlace(){
		super("FastPlace", Keyboard.KEY_NONE , Category.PLAYER);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		originalDelay = mc.rightClickDelayTimer;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			mc.rightClickDelayTimer = 0;
		}
	}

	
	
}
