package yusuf.cato.modules.player;
import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C03PacketPlayer;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.NumberSetting;

public class Timer extends Module {
	private float originalTimerSpeed = mc.timer.timerSpeed;
	
	public static NumberSetting timerSpeedSetting = new NumberSetting("Timer Speed", 1.5, 1, 10, 1);
	
	
	public Timer(){
		super("Timer", Keyboard.KEY_NONE , Category.PLAYER);
	}
	
	public void onEnable() {
		mc.timer.timerSpeed = (float) timerSpeedSetting.value;
	}
	
	public void onDisable() {
		mc.timer.timerSpeed = originalTimerSpeed;
	}
	
}
