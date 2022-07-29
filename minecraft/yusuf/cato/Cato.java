package yusuf.cato;
import yusuf.cato.alt.AltManager;
import yusuf.cato.events.Event;
import yusuf.cato.events.listeners.*;
import yusuf.cato.font.FontUtil;
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
	public static Cato cato;
	public static String name = "Cato", version =  "b1-rel", authors = "FatyCaty"; //FatyCaty aka Yusuf
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static HUD hud = new HUD();
	public static AltManager altManager;
	
	public static void startup() {
		cato = new Cato();
		System.out.println("Starting " + name + " " + version + " by " + authors);
		Display.setTitle(name + " " + version + " by " + authors);
				
		altManager = new AltManager();		
		
		// Modules
		modules.add(new ChestStealer());
		modules.add(new AutoClicker());
		modules.add(new Animations());
		modules.add(new FullBright());
		modules.add(new RenderTest());
		modules.add(new AutoBridge());
		modules.add(new Keystrokes());
		modules.add(new FastPlace());
		modules.add(new Scaffold());
		modules.add(new Velocity());
		modules.add(new InvMove());
		modules.add(new TabGui());
		modules.add(new NoFall());
		modules.add(new NoSlow());
		modules.add(new Sprint());
		modules.add(new Timer());
		modules.add(new Speed());
		modules.add(new Aura());
		modules.add(new Step());
		modules.add(new Derp());
		modules.add(new Fly());
		modules.add(new InfoBar());
		modules.add(new ClickGuiMod());
		modules.add(new Criticals());


		
		FontUtil.bootstrap();




		
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
	
	public static List<Module> getModulesByCategory(Category c){
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : Cato.modules) {
			if(m.category==c) {
				modules.add(m);
			}
		}
		
		return modules;
	}
	
	
	
	public static Module getModulesByName(String moduleName){
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : Cato.modules) {
			if(m.name == moduleName) {
				return m;
			}
		}
		
		return null;
	}
	
}
