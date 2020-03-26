package jelly;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;

public class Util {

	// Not even sure if this works
	public static int getClassID(int entity) {
		return ((((entity + 0x8) + 0x8) + 0x1) + 0x14);
//		int classID = ((((entity + 0x8) + 0x8) + 0x1) + 0x14);
		
//		int dwClientNetworkable = (entity + 0x8);
//		int dwGetClientClassFn = (dwClientNetworkable + 2 * 0x4);
//		int dwEntityClientClass = (dwGetClientClassFn + 1);
//		
//		Pointer p = new Pointer(0);
//		Pointer.nativeValue(p, classID);
//		Memory m = new Memory(4);
//		Kernel32.INSTANCE.ReadProcessMemory(Jelly.handle, p, m, 4, null);
//		int id = m.getInt(0);
//		m.clear();
//		
//		return id;
		
//		return dwEntityClientClass + 20;
	}
}
