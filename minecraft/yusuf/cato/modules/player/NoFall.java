package yusuf.cato.modules.player;
import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C03PacketPlayer;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class NoFall extends Module {
	public NoFall(){
		super("NoFall", Keyboard.KEY_NONE , Category.PLAYER);
	}
	//flyModeSetting
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate && e.isPre() && mc.thePlayer.fallDistance > 2) {
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
		}
	}
	
	
}
