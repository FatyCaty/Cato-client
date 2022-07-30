package yusuf.cato.modules.movement;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.ChatComponentText;
import yusuf.cato.Cato;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventRenderGUI;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.font.FontUtil;
import yusuf.cato.font.MinecraftFontRenderer;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.BooleanSetting;
import yusuf.cato.settings.ModeSetting;
import yusuf.cato.util.ColourUtil;

public class Fly extends Module {
	
	public static ModeSetting flyModeSetting = new ModeSetting("Fly modes", "NoRules", "NoRules", "OldVerusJumpFly", "CreativeCapabilities", "dev.bukkit.org/projects/anticheat");
	private int flyTimer = 0;
	private boolean noFallWasEnabledBeforeToggle;
	public BooleanSetting showText = new BooleanSetting("Show Text", true);
	

	public Fly(){
		super("Fly", Keyboard.KEY_NONE , Category.MOVEMENT);
		this.addSettings(flyModeSetting);
	}
	
	public void onEnable() {
		if(flyModeSetting.is("dev.bukkit.org/projects/anticheat")) {
			mc.timer.timerSpeed = mc.timer.timerSpeed + 5f;
			mc.thePlayer.capabilities.isFlying = true;
			mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3.01f, mc.thePlayer.posZ, false));
			mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
			mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
			
			if(Cato.getModulesByName("NoFall").isEnabled()) {
				noFallWasEnabledBeforeToggle = true;
				Cato.getModulesByName("NoFall").toggled = false;
				Cato.addChatMessage("NoFall has been disabled!");
			}
			else {
				noFallWasEnabledBeforeToggle = false;
			}
		}
		
	}
	
	public void onDisable() {
		if(flyModeSetting.is("CreativeCapabilities")) {
			mc.thePlayer.capabilities.isFlying = false;
		}
		if(flyModeSetting.is("NoRules")) {
			mc.thePlayer.capabilities.isFlying = false;
		}
		if(flyModeSetting.is("dev.bukkit.org/projects/anticheat")) {
			mc.timer.timerSpeed = mc.timer.timerSpeed - 5f;
			mc.thePlayer.capabilities.isFlying = false;
			if(noFallWasEnabledBeforeToggle) {
				noFallWasEnabledBeforeToggle = false;
				Cato.getModulesByName("NoFall").toggled = true;
				Cato.addChatMessage("NoFall has been re-enabled!");
				
			}
		}
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(flyModeSetting.is("OldVerusJumpFly")) {
				if(e.isPre()) {
					flyTimer+=1;
					mc.thePlayer.onGround = true;
					if(flyTimer == 11){
						mc.thePlayer.jump();
						flyTimer = 0;
						
					}
				}
			}
			
			if(flyModeSetting.is("CreativeCapabilities")) {
				mc.thePlayer.capabilities.isFlying = true;
				mc.thePlayer.setSprinting(true);
			}
			if(flyModeSetting.is("NoRules")) {
				mc.thePlayer.capabilities.isFlying = true;
				mc.thePlayer.setSprinting(true);
				mc.thePlayer.onGround = true;
			
				//mc.thePlayer.moveFlying(flyTimer, flyTimer, flyTimer);
			}
			if(flyModeSetting.is("dev.bukkit.org/projects/anticheat")) {
				mc.thePlayer.capabilities.isFlying = true;
				mc.thePlayer.setSprinting(true);
			}
			
		}
		
		if(e instanceof EventRenderGUI && showText.isEnabled()) {
			ScaledResolution sr = new ScaledResolution(mc);
			MinecraftFontRenderer ffr = FontUtil.normal;
			String text = "Enjoy your Flight at Cato Airlines!";
			ffr.drawString("Cato Airlines", sr.getScaledWidth()/2 - ffr.getStringWidth("Cato Airlines") + 30, 10, new ColourUtil().getRainbowWave(3, 1, 1, 100));
		}
	}
	
	
}
