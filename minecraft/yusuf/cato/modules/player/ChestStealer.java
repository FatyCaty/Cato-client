package yusuf.cato.modules.player;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.BlockPos;
import yusuf.cato.modules.Module.Category;
import yusuf.cato.settings.BooleanSetting;
import yusuf.cato.settings.ModeSetting;
import yusuf.cato.settings.NumberSetting;
import yusuf.cato.util.TimerUtil;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.EventUpdate;
import yusuf.cato.modules.Module;

public class ChestStealer extends Module{
	private TimerUtil timer = new TimerUtil();
	public BooleanSetting close = new BooleanSetting("AutoClose", true);
	public NumberSetting delay = new NumberSetting("StealDelay", 10, 0, 200, 5);

	
	public ChestStealer() {
		super("ChestStealer", Keyboard.KEY_C, Category.PLAYER);
	}
	
	
	
	
	public void onEvent(Event e) {
		if (e instanceof EventUpdate && mc.thePlayer.openContainer != null && mc.thePlayer.openContainer instanceof ContainerChest) { 
            if (isGui())
                return;
            
            ContainerChest chest = (ContainerChest) mc.thePlayer.openContainer;
            
            for (int i = 0; i < chest.getLowerChestInventory().getSizeInventory(); i++) {
                if (chest.getLowerChestInventory().getStackInSlot(i) != null && timer.hasTimeElapsedNoReset((long) delay.getValue())) {
                    mc.playerController.windowClick(chest.windowId, i, 0, 1, mc.thePlayer);
                    timer.reset();
                }
            }
            
            if (isEmpty((Container) chest) && close.isEnabled() && timer.hasTimeElapsedNoReset((long) delay.getValue())) {
                mc.thePlayer.closeScreen();
                timer.reset();
            }
            
        }
    }
    
    public boolean isEmpty(Container b) {
        int j = b.inventorySlots.size() == 90 ? 54 : 27;
        for (int i = 0; i < j; i++) {
            if (b.getSlot(i).getHasStack())
                return false;
        }
        return true;
    }
    
    public boolean isGui() {
        int range = 5;
        for (int x = -range; x < range; x++) {
            for (int y = range; y > -range; y--) {
                for (int z = -range; z < range; z++) {
                    int posX = (int)this.mc.thePlayer.posX + x;
                    int posY = (int)this.mc.thePlayer.posY + y;
                    int posZ = (int)this.mc.thePlayer.posZ + z;
                    BlockPos blockPos = new BlockPos(posX, posY, posZ);
                    Block block = this.mc.theWorld.getBlockState(blockPos).getBlock();
                    if (block instanceof BlockChest)
                        return false; 
                } 
            } 
        } 
        return true;
    }

}
