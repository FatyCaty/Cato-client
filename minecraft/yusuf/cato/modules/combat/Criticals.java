package yusuf.cato.modules.combat;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import yusuf.cato.modules.Module.Category;
import yusuf.cato.settings.ModeSetting;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class Criticals extends Module{
	
	ModeSetting rotationMode = new ModeSetting("Rotation Modes", "SilentRotation", "SilentRotation", "NonSilentRotation", "NoRotation");

	
	public Criticals() {
		super("Criticals", Keyboard.KEY_NONE,Category.COMBAT );
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(mc.objectMouseOver != null && mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
				double posX = mc.thePlayer.posX;
				double posZ = mc.thePlayer.posZ;
				double posY = mc.thePlayer.posY;
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX, posY + 0.0625D, posZ, true));
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX, posY, posZ, false));
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX, posY + 1.1E-5D, posZ, false));
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX, posY, posZ, false));
			}
		}
	}

}
