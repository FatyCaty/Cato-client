package yusuf.cato.modules.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.BlockPos;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.ModeSetting;

public class Animations extends Module {
	
	public static ModeSetting animationsSetting = new ModeSetting("Animations", "1.7", "1.7", "Push", "Weird", "Down", "Meow<3", "Cool");
	
	public static ModeSetting getAnimationsSetting() {
		return animationsSetting;
	}

	public Animations(){
		super("Animations", Keyboard.KEY_I , Category.RENDER);
		this.addSettings(animationsSetting);
	}
	
	
}
 