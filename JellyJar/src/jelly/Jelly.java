package jelly;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32.MODULEENTRY32W;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import jelly.modules.Module;
import jelly.offsets.OffsetManager;
import jelly.offsets.Signatures;
import jelly.offsets.offsets.NetVarOffsets;

public class Jelly {

	public static Jelly jelly = new Jelly();
	boolean running = false;

	private ModuleManager moduleManager;

	public static HANDLE handle;
	public static long client;
	public static long engine;
	public static long localPlayer;
	public static int teamNum;

	public Jelly() {

		// Find csgo's process ID based on its window title
		IntByReference pid = new IntByReference();
		User32.INSTANCE.GetWindowThreadProcessId(User32.INSTANCE.FindWindow("Valve001", null), pid);

		System.out.println("Process ID: " + pid.getValue());

		handle = Kernel32.INSTANCE.CreateToolhelp32Snapshot(new DWORD(24), new DWORD(pid.getValue()));
		MODULEENTRY32W.ByReference e = new MODULEENTRY32W.ByReference();
		do {
			// Find the client module
			if (e.szModule[0] == 'c' && e.szModule[1] == 'l')
				client = Pointer.nativeValue(e.modBaseAddr);

			// Find the engine module
			if (e.szModule[0] == 'e' && e.szModule[1] == 'n')
				engine = Pointer.nativeValue(e.modBaseAddr);

		} while (Kernel32.INSTANCE.Module32NextW(handle, e));
		Kernel32.INSTANCE.CloseHandle(handle);

		System.out.println("Found client module: " + client);
		System.out.println("Found engine module: " + engine);

		// Open a handle to the csgo process
		handle = Kernel32.INSTANCE.OpenProcess(WinNT.PROCESS_ALL_ACCESS, false, pid.getValue());

		OffsetManager.initAll();
		
		// Save player address
		Memory m = new Memory(4);
		Pointer p = new Pointer(0);
		Pointer.nativeValue(p, client + Signatures.dwLocalPlayer);
		Kernel32.INSTANCE.ReadProcessMemory(handle, p, m, 4, null);
		localPlayer = Integer.toUnsignedLong(m.getInt(0));
		System.out.println("Found local player: " + localPlayer);

		// Save player team num
		System.out.println("TeamNum offset: " +  NetVarOffsets.DT_BasePlayer.m_iTeamNum);
		Pointer.nativeValue(p, localPlayer + NetVarOffsets.DT_BasePlayer.m_iTeamNum); // get team num so we can avoid teammates
		Kernel32.INSTANCE.ReadProcessMemory(handle, p, m, 4, null);
		teamNum = m.getInt(0);
		System.out.println("Team num: " + teamNum);

		moduleManager = new ModuleManager();
		
		// Dump offsets (netvars only for now)
		OffsetManager.dumpAll();
		
		running = true;
	}

	public void start() {

		Pointer p = new Pointer(0);
		Memory m = new Memory(4);
		
		// Main cheat loop
		while (running) {

			for (Module module : moduleManager.getModules()) {
//				if (module.isEnabled())
				module.onLoop();
			}
			
			// Loop through the entitylist
			for (int i = 1; i < 64; i++) {
				Pointer.nativeValue(p, client + Signatures.dwEntityList + i * 0x10);
				Kernel32.INSTANCE.ReadProcessMemory(handle, p, m, 4, null);
				long entity = Integer.toUnsignedLong(m.getInt(0));
				
				for (Module module : moduleManager.getModules()) {
//					if (module.isEnabled())
					module.onEntityLoop(entity);
				}
			}
			
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Kernel32.INSTANCE.CloseHandle(handle);
	}
}
