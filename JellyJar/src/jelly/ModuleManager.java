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
		modules.add(new Chams((byte) 128, (byte) 128, (byte) 128, 100));
	}

	public ArrayList<Module> getModules() {
		return modules;
	}
}
