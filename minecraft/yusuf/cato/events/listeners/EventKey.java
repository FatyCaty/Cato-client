package yusuf.cato.events.listeners;

import yusuf.cato.events.Event;

public class EventKey extends Event<EventKey> {

	
	public int code;
	
	public EventKey(int code) {
		this.code = code;
	}
	
}
