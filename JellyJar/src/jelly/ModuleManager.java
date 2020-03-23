package jelly;

import java.util.ArrayList;

import jelly.modules.Module;
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
	}

	public ArrayList<Module> getModules() {
		return modules;
	}
}
