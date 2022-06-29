package yusuf.cato.modules.movement;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.jcraft.jorbis.Block;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventMotion;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.BooleanSetting;
import yusuf.cato.util.TimerUtil;

//Video I am following: https://youtu.be/WE82aSuIB3U?t=530

public class Scaffold extends Module {
	
	private boolean sprintedBeforeScaffoldToggle = mc.gameSettings.keyBindSprint.isPressed();
	public static BooleanSetting scaffoldNoSprintSetting = new BooleanSetting("noSprint", true);
	//public static BooleanSetting scaffoldRoationsSetting = new BooleanSetting("Rotations", true);
	public TimerUtil timer = new TimerUtil();

	public Scaffold(){
		super("Scaffold", Keyboard.KEY_Z , Category.MOVEMENT);
		this.addSettings(scaffoldNoSprintSetting/*, scaffoldRoationsSetting*/);
	}
	
	public void onEnable() {
		sprintedBeforeScaffoldToggle = mc.gameSettings.keyBindSprint.isPressed();
		
		if(scaffoldNoSprintSetting.isEnabled())
			KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
		else
			KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);

	}
	
	public void onDisable() {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), sprintedBeforeScaffoldToggle);

	}
	
	
	public void onEvent(Event e) {
		if(e.isPre() && mc.thePlayer != null) {
			if(scaffoldNoSprintSetting.isEnabled())
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
				mc.thePlayer.setSprinting(false);
			
			
			Entity p = mc.thePlayer;
			BlockPos bp = new BlockPos(p.posX, p.getEntityBoundingBox().minY, p.posZ);
			if(valid(bp.add(0, -2, 0)))
				
				place(bp.add(0, -1, 0), EnumFacing.UP);
			
			else if (valid(bp.add(-1, -1, 0)))
				
				place(bp.add(0, -1, 0), EnumFacing.EAST);
			
			else if (valid(bp.add(1, -1, 0)))
				
				place(bp.add(0, -1, -1), EnumFacing.WEST);
			
			else if (valid(bp.add(0, -1, -1)))
				
				place(bp.add(0, -1, 0), EnumFacing.SOUTH);
			
			else if (valid(bp.add(0, -1, 1)))
				
				place(bp.add(0, -1, 0), EnumFacing.NORTH);
			
			else if(valid(bp.add(1, -1, 1))) {
				
				if(valid(bp.add(0, -1, 1)))
					
					place(bp.add(0, -1, 1), EnumFacing.NORTH);
				place(bp.add(1, -1, 1), EnumFacing.EAST);
			}else if (valid(bp.add(-1, -1, 1))) {
				if(valid(bp.add(-1, -1, 0)))
					place(bp.add(0, -1, 1), EnumFacing.WEST);
				place(bp.add(-1, -1, 1),EnumFacing.SOUTH);
			}else if (valid(bp.add(-1, -1, -1))) {
				if(valid(bp.add(0, -1, -1)))
					place(bp.add(0, -1, 1), EnumFacing.SOUTH);
				place(bp.add(-1, -1, 1), EnumFacing.WEST);
			}else if (valid(bp.add(1, -1 , -1))) {
				if(valid(bp.add(1, -1, 0)))
					place(bp.add(1, -1, 0), EnumFacing.EAST);
				place(bp.add(1, -1, -1), EnumFacing.NORTH);
			}
	}
	}

	
	void place(BlockPos p , EnumFacing f) {
		if(f == EnumFacing.UP)
			p = p.add(0, -1 ,0);
		else if(f == EnumFacing.NORTH)
			p = p.add(0, 0, 1);
		else if(f == EnumFacing.EAST)
			p = p.add(-1, 0, 0);
		else if(f == EnumFacing.SOUTH)
			p = p.add(0, 0, -1);
		else if(f == EnumFacing.WEST)
			p = p.add(1.5, 0, 1);
		
		EntityPlayerSP _p = mc.thePlayer;
		
		
		if((timer.hasTimeElapsed(50, true)) &&(_p.getHeldItem() != null && _p.getHeldItem().getItem() instanceof ItemBlock)) {
			mc.playerController.onPlayerRightClick(_p, mc.theWorld, _p.getHeldItem(), p, f, new Vec3(0.5, 0.5, 0.5));
			_p.swingItem();
		}
		//Rotations
		double x = p.getX() + 0.25 - _p.posX;
		double z = p.getZ() + 0.25 - _p.posZ;
		double y = p.getY() + 0.25 - _p.posY;
		double distance = MathHelper.sqrt_double(x * x + z * z);
		float yaw = (float) (Math.atan2(z, x) * 180 / Math.PI - 90);
		float pitch = (float) - (Math.atan2(y, distance) * 180 /Math.PI);
		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(_p.posX, _p.posY, _p.posZ, yaw, pitch, _p.onGround));
		
	}
	
	boolean valid(BlockPos p) {
		net.minecraft.block.Block b = mc.theWorld.getBlock(p);
		return !(b instanceof BlockLiquid) && b.getMaterial() != Material.air;
		
	}
}
