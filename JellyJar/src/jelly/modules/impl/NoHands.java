package jelly.modules.impl;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;

import jelly.Jelly;
import jelly.modules.Module;
import jelly.offsets.offsets.NetVarOffsets;

public class NoHands extends Module {
	public void onLoop() {
		
		Pointer p = new Pointer(0);
		Pointer.nativeValue(p, Jelly.localPlayer + NetVarOffsets.DT_BasePlayer.m_nModelIndex);
		
		Memory m = new Memory(4);
		m.setInt(0, 0);
		
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 4, null);
		
//		Signatures.dwLocalPlayer + Netvars.CBaseEntity.m_nModelIndex, 20
	}
}
