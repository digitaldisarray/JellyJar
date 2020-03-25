package jelly;

import java.util.ArrayList;

import jelly.modules.Module;
import jelly.modules.impl.Chams;
import jelly.modules.impl.Glow;
import jelly.modules.impl.NoHands;
import jelly.modules.impl.Triggerbot;

public class ModuleManager {
	ArrayList<Module> modules = new ArrayList<>();

	public ModuleManager() {
		// Init modules
		modules.add(new Triggerbot());
		modules.add(new Glow());
		modules.add(new NoHands());
		
		// RGB is 0-128
		modules.add(new Chams((byte) 100, (byte) 0, (byte) 0, (byte) 0, (byte) 100, (byte) 0, 100f));
	}

	public ArrayList<Module> getModules() {
		return modules;
	}
}
