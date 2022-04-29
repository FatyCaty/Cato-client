package yusuf.cato.modules.combat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventMotion;
import yusuf.cato.events.listeners.EventRenderGUI;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;
import yusuf.cato.settings.BooleanSetting;
import yusuf.cato.settings.ModeSetting;
import yusuf.cato.util.Timer;

public class Aura extends Module {
	
	public double auraReach = 3.3;
	public int cps = 10;
	ModeSetting rotationMode = new ModeSetting("Rotation Modes", "SilentRotation", "NoRotation", "NonSilentRotation");
	BooleanSetting onlyAttackPlayers = new BooleanSetting("Only Attack Players", false);
	public boolean printEntityToConsole = true;


	public static String currentTargetName;
	public Timer timer = new Timer();
	
	
	public Aura(){
		super("Aura", Keyboard.KEY_R , Category.COMBAT);
		this.settings.add(rotationMode);
		this.settings.add(onlyAttackPlayers);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.isKeyDown());
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
			if(e.isPre()) {
				
				EventMotion event = (EventMotion)e;
				
				List<Entity> targets = mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
				
				targets = targets.stream().filter(entity -> entity.getDistanceToEntity(mc.thePlayer)< auraReach && entity != mc.thePlayer && !(entity.isDead)).collect(Collectors.toList());
				
				if(onlyAttackPlayers.isEnabled()) {
					targets = targets.stream().filter(EntityPlayer.class::isInstance).collect(Collectors.toList());
				}
				
				targets.sort(Comparator.comparingDouble(entity -> (entity).getDistanceToEntity(mc.thePlayer)));
				
				if(!targets.isEmpty()) {
					Entity target = targets.get(0);
						if(timer.hasTimeElapsed(1000/ cps, true)) {
						mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, Action.ATTACK));
						mc.thePlayer.swingItem();
						
						if(rotationMode.is("SilentRotation")) {
							event.setYaw(getRotations(target)[0]);
							event.setPitch(getRotations(target)[1]);
						}
						else if (rotationMode.is("NonSilentRotation")) {
							mc.thePlayer.rotationYaw = (getRotations(target)[0]);
							mc.thePlayer.rotationPitch = (getRotations(target)[1]);
						}

						
						//TargetHUD
						if(printEntityToConsole) {
							currentTargetName = targets.get(0).getName();
							System.out.println("Aura Attacking Entity: " + currentTargetName);
							
							
						}
						
						
					}
				}
			
				
				/*
				 * 
				 * Old non working code
				List<EntityLivingBase> targets = mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
				
				targets = targets.stream().filter(entity -> entity.getDistanceToEntity(mc.thePlayer)< auraReach && entity != mc.thePlayer).collect(Collectors.toList());
				
				targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase)entity).getDistanceToEntity(mc.thePlayer)));
				if(!targets.isEmpty()) {
					EntityLivingBase target = targets.get(0);
						if(timer.hasTimeElapsed(1000/ cps, true)) {
						mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, Action.ATTACK));
					}
				}
				 */
				
			}
		}
	}
	
	public float[] getRotations(Entity e) {
		double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX,
		deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
		deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
		distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
		float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
				pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
		if(deltaX < 0 && deltaY < 0) {
			yaw = (float) Math.toDegrees( 90 +Math.atan(deltaZ / deltaX));
		} else if (deltaX > 0 && deltaZ < 0) {
			yaw = (float) Math.toDegrees(-90 + Math.atan(deltaZ / deltaX));
		}
		return new float [] {yaw, pitch};
	}
	
	/*
	 * 
	 * Old Non working Aura Code
	 * 
	 * 
	public Aura(){
		super("Aura", Keyboard.KEY_R , Category.COMBAT);
	}
	
	private EntityLivingBase target;
	private long current, last;
	private int delay = 8;
	private float yaw, pitch;
	private boolean others;
	
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.isKeyDown());
	}
	
	
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
			if(e.isPre()) {
				target = getClosest((mc.playerController.getBlockReachDistance()));
				if(target == null) {
					return;
				}
				updateTime();
				
				yaw = mc.thePlayer.rotationYaw;
				pitch = mc.thePlayer.rotationPitch;
				if(current - last > 1000 / delay) {
					attack(target);
					resetTime();
				}
			}
			
			
		}
		if(e instanceof EventMotion) {
			if(e.isPost()) {
				if(target == null) {
					return;
				}
				mc.thePlayer.rotationYaw = yaw;
				mc.thePlayer.rotationPitch = pitch;
			}
			
		}
	}
	
	
	
	private void attack(Entity entity) {
		mc.thePlayer.swingItem();
		mc.playerController.attackEntity(mc.thePlayer, entity);
		
	}

	private void updateTime() {
		current = (System.nanoTime() / 1000000L);
		
	}
	
	private void resetTime() {
		last = (System.nanoTime() / 1000000L);
	}
	
	

	private EntityLivingBase getClosest(double range) {
		double dist = range;
		EntityLivingBase target = null;
		for(Object object : mc.theWorld.loadedEntityList) {
			Entity entity = (Entity)object;
			if(entity instanceof EntityLivingBase) {
				EntityLivingBase player = (EntityLivingBase)entity;
				if(canAttack(player)) {
					if(!(player instanceof EntityMob) && (!((player instanceof EntityAnimal) || others))) {
						double currentDist = mc.thePlayer.getDistanceToEntity(player);
						if(currentDist <= dist) {
							dist = currentDist;
							target = player;
						}
					}
				}
			}
		}
		
		return target;
	}

	private boolean canAttack(EntityLivingBase player) {
		
		
		return player != mc.thePlayer && player.isEntityAlive() && mc.thePlayer.getDistanceToEntity(player) <= mc.playerController.getBlockReachDistance() && player.ticksExisted > 30;
	}
	*/
	
}
