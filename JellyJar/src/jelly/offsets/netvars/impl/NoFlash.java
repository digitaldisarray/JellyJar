package jelly.offsets.netvars.impl;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;

import jelly.Jelly;
import jelly.modules.Module;
import jelly.offsets.offsets.NetVarOffsets;

public class NoFlash extends Module {
	public void onLoop() {
		Pointer p = new Pointer(0);
		Memory m = new Memory(4);

		p.nativeValue(p, Jelly.localPlayer + NetVarOffsets.DT_CSPlayer.m_flFlashMaxAlpha);
		Kernel32.INSTANCE.ReadProcessMemory(Jelly.handle, p, m, 4, null);
		float max = m.getFloat(0);
		
		if(max > 0f) {
			m.setFloat(0, 85f);
			Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 4, null);
		} else if(max == 0f) {
			m.setFloat(0, 255f);
			Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 4, null);
		}
	}
}
