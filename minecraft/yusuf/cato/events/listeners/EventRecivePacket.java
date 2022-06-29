package yusuf.cato.events.listeners;

import net.minecraft.network.Packet;
import yusuf.cato.events.Event;

public class EventRecivePacket extends Event<EventRecivePacket> {

	public Packet packet;
	
	public EventRecivePacket(Packet packet) {
		this.packet = packet;
	}

	public Packet getPacket() {
		return packet;
	}
	
}
