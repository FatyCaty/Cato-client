package yusuf.cato;
import yusuf.cato.alt.AltManager;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.*;
import yusuf.cato.modules.Module;
import yusuf.cato.modules.Module.*;
import yusuf.cato.modules.combat.*;
import yusuf.cato.modules.movement.*;
import yusuf.cato.modules.render.*;
import yusuf.cato.modules.player.*;
import yusuf.cato.ui.HUD;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

//Nefs Video https://youtu.be/o8fuBYUB6cI

public class Cato {
	public static String name = "Cato", version =  "b1-Dev", author = "Yusuf -> FatyCaty";
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static HUD hud = new HUD();
	public static AltManager altManager;
	
	public static void startup() {
		System.out.println("Starting " + name + " " + version + " by " + author);
		Display.setTitle(name + " " + version + " by " + author);
		
		altManager = new AltManager();
		
		
		modules.add(new BlockAnimations());
		modules.add(new FullBright());
		modules.add(new Scaffold());
		modules.add(new NoFall());
		modules.add(new Amogus());
		modules.add(new Sprint());
		modules.add(new Speed());
		modules.add(new Aura());
		modules.add(new Step());
		modules.add(new Fly());
		
		modules.add(new noSlow());

		
	}
	
	public static void onEvent(Event e) {
		for(Module m : modules) {
			if(!m.toggled) {
				continue;
			}
			
			m.onEvent(e);
		}
	}
	
	public static void keyPress(int key) {
		Cato.onEvent(new EventKey(key));
		for(Module m : modules) {
			if(m.getKey() == key) {
				m.toggle();
			}
		}
	}
	
	public List<Module> getModulesByCategory(Category c){
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : this.modules) {
			if(m.category==c) {
				modules.add(m);
			}
		}
		
		return modules;
	}
}
