/*
 *    Copyright 2016 Jonathan Beaudoin
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package jelly.offsets;

import com.github.jonatino.process.Module;
import com.github.jonatino.process.Process;
import com.github.jonatino.process.Processes;

import jelly.offsets.netvars.NetVars;
import jelly.offsets.offsets.NetVarOffsets;

/**
 * Created by Jonathan on 12/22/2015.
 */
public final class OffsetManager {
	
	private static Process process;
	private static Module clientModule, engineModule;
	
	static {
		waitUntilFound("process", () -> (process = Processes.byName("csgo.exe")) != null);
		waitUntilFound("client module", () -> (clientModule = process.findModule("client_panorama.dll")) != null);
		waitUntilFound("engine module", () -> (engineModule = process.findModule("engine.dll")) != null);
	}
	
	public static void initAll() {
		NetVars.load();
		loadOffsets();
	}
	
	public static void dumpAll() {
//		ClientOffsets.dump();
//		EngineOffsets.dump();
//		NetVarOffsets.dump();
		NetVars.dump();
	}
	
	public static void loadOffsets() {
//		ClientOffsets.load();
//		EngineOffsets.load();
		NetVarOffsets.load();
	}
	
	public static Process process() {
		return process;
	}
	
	public static Module clientModule() {
		return clientModule;
	}
	
	public static Module engineModule() {
		return engineModule;
	}
	
	private static void waitUntilFound(String message, Clause clause) {
		System.out.print("Looking for " + message + ". Please wait.");
		while (!clause.get()) try {
			Thread.sleep(3000);
			System.out.print(".");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\nFound " + message + "!");
	}
	
	@FunctionalInterface
	private interface Clause {
		boolean get();
	}
	
}
