package net.minecraft.network;

import net.minecraft.util.IThreadListener;
import yusuf.cato.Cato;
import yusuf.cato.events.EventType;
import yusuf.cato.events.listeners.EventRecivePacket;

public class PacketThreadUtil
{
    public static <T extends INetHandler> void checkThreadAndEnqueue(final Packet<T> p_180031_0_, final T p_180031_1_, IThreadListener p_180031_2_) throws ThreadQuickExitException
    {
        if (!p_180031_2_.isCallingFromMinecraftThread())
        {
            p_180031_2_.addScheduledTask(new Runnable()
            {
                public void run()
                {
                	EventRecivePacket e = new EventRecivePacket(p_180031_0_);
                	e.setType(EventType.PRE);
                	Cato.onEvent(e);
                	if(e.isCancelled()) {
                		return;
                	}
                    p_180031_0_.processPacket(p_180031_1_);
                }
            });
            throw ThreadQuickExitException.field_179886_a;
        }
    }
}
