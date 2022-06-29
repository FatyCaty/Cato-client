package yusuf.cato.modules.player;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.client.C03PacketPlayer;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.NumberSetting;

public class AutoClicker extends Module {
	
	//Original Video: https://youtu.be/45894INyJXU
	//Thank you so much!
	
	public static NumberSetting minCPSSetting = new NumberSetting("Minimum CPS", 6, 1, 20, 1);
	public static NumberSetting maxCPSSetting = new NumberSetting("Maximum CPS", 12, 1, 20, 1);

	private long lastClick, hold;
	private double speed, holdLen;
	
	public AutoClicker(){
		super("AutoClicker", Keyboard.KEY_NONE , Category.PLAYER);
		this.addSettings(minCPSSetting, maxCPSSetting);
	}
	
	public void onEnable() {
		this.updateVals();
	}
	
	public void onDisable() {
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(Mouse.isButtonDown(0)) {
				if(System.currentTimeMillis() - lastClick > speed * 1000) {
					lastClick = System.currentTimeMillis();
					if(hold < lastClick) {
						hold = lastClick;
					}
					int key = mc.gameSettings.keyBindAttack.getKeyCode();
					KeyBinding.setKeyBindState(key, true);
					KeyBinding.onTick(key);
					
				} else if(System.currentTimeMillis() - hold > holdLen * 1000) {
					KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), false);
					this.updateVals();
				}
			}
		}
	}
	
	private void updateVals() {
		double miC = minCPSSetting.value;
		double mxC = maxCPSSetting.value;

		if(miC == mxC) {
			mxC = miC + 1;
		}
		speed = 1.0 / ThreadLocalRandom.current().nextDouble(miC - 0.2, mxC);
		holdLen = speed / ThreadLocalRandom.current().nextDouble(miC, mxC);
	}
	
	
}
