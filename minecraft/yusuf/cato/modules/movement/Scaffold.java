package yusuf.cato.modules.movement;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.jcraft.jorbis.Block;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
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
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.BooleanSetting;

//Video I am following: https://youtu.be/WE82aSuIB3U?t=530

public class Scaffold extends Module {
	
	BooleanSetting noSwing = new BooleanSetting("noSwing", true);
	
	public Scaffold(){
		super("Scaffold", Keyboard.KEY_Z , Category.MOVEMENT);
		this.addSettings(noSwing);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			Entity p = mc.thePlayer;
			BlockPos bp = new BlockPos(p.posX, p.getEntityBoundingBox().minY, p.posZ);
			
			if(valid(bp.add(0, -2, 0))) {
				place(bp.add(0, -1, 0), EnumFacing.UP);
			} else if (valid(bp.add(-1, -1, 0))) {
				place(bp.add(0, -1, 0), EnumFacing.EAST);
			} else if (valid(bp.add(1, -1, 0))) {
				place(bp.add(0, -1, -1), EnumFacing.WEST);
			} else if (valid(bp.add(0, -1, -1))) {
				place(bp.add(0, -1, 0), EnumFacing.SOUTH);
			} else if (valid(bp.add(0, -1, 1))) {
				place(bp.add(0, -1, 0), EnumFacing.NORTH);
			} else if (valid(bp.add(1, -1, -1))) {
				if(valid(bp.add(0, -1, 1))) {
					place(bp.add(0, -1, 1), EnumFacing.NORTH);
				}
				place(bp.add(1, -1, 1), EnumFacing.EAST);
			} else if(valid(bp.add(-1, -1, 1))) {
				if(valid(bp.add(-1, -1, 0))) {
					place(bp.add(0, -1, 1), EnumFacing.WEST);
				}
				place(bp.add(-1, -1, 1), EnumFacing.SOUTH);
			} else if (valid(bp.add(-1, -1 ,-1))) {
				if(valid(bp.add(0, -1, -1))) {
					place(bp.add(0, -1 ,1), EnumFacing.SOUTH);
				}
				place(bp.add(-1, -1, 1), EnumFacing.WEST);
			} else if(valid(bp.add(bp.add(1, -1, -1)))) {
				if(valid(bp.add(1, -1, 0))) {
					place(bp.add(1, -1 ,0), EnumFacing.EAST);
				}
				place(bp.add(1, -1 , -1), EnumFacing.NORTH);
			}
			
			
			
		}
	}
	
	void place(BlockPos p , EnumFacing f) {
		if(f == EnumFacing.UP) {
			p = p.add(0, -1, 0);
		}
		else if(f == EnumFacing.NORTH) {
			p = p.add(0, 0, 1);
		}
		else if(f == EnumFacing.EAST) {
			p = p.add(-1, 0, 0);
		}
		else if(f == EnumFacing.EAST) {
			p = p.add(0, 0, -1);
		}
		else if(f == EnumFacing.WEST) {
			p = p.add(1, 0, 0);
		}
		
		EntityPlayerSP _p = mc.thePlayer;
		if(_p.getHeldItem() != null && _p.getHeldItem().getItem() instanceof ItemBlock) {
			
			mc.playerController.onPlayerRightClick(_p, mc.theWorld, _p.getHeldItem(), p, f, new Vec3(0.5, 0.5, 0.5));
			
			double x = p.getX() + 0.25 - _p.posX;
			double z = p.getX() + 0.25 - _p.posZ;
			double y = p.getX() + 0.25 - _p.posY;

			double distance = MathHelper.sqrt_double(x * x + z * z);
			float yaw = (float) (Math.atan2(z, x) * 100 / Math.PI - 90);
			float pitch = (float) -(Math.atan2(y, distance) * 100 / Math.PI);
			if(!(noSwing.isEnabled()))
				_p.swingItem();
			
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(_p.posX, _p.posY, _p.posZ, yaw, pitch, _p.onGround));

			
		}
		
	}
	
	boolean valid(BlockPos p) {
		net.minecraft.block.Block b = mc.theWorld.getBlock(p);
		return !(b instanceof BlockLiquid) && b.getMaterial() != Material.air;
		
	}
}
