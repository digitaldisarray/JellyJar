package jelly.modules.impl;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;

import jelly.Jelly;
import jelly.modules.Module;
import jelly.offsets.Netvars;
import jelly.offsets.Signatures;

public class Triggerbot extends Module {

	public void onLoop() {
		Memory m = new Memory(4);
		Pointer p = new Pointer(0);
		
		// Get crosshair id
		Pointer.nativeValue(p, Jelly.localPlayer + Netvars.m_iCrosshairId);
		Kernel32.INSTANCE.ReadProcessMemory(Jelly.handle, p, m, 4, null);
		int id = m.getInt(0);
		
		// Get who we are aimed at
		Pointer.nativeValue(p, Jelly.client + Signatures.dwEntityList + (id - 1) * 0x10);
		Kernel32.INSTANCE.ReadProcessMemory(Jelly.handle, p, m, 4, null);
		long aimedAt = Integer.toUnsignedLong(m.getInt(0));
		
		// Get their team
		Pointer.nativeValue(p, aimedAt + Netvars.m_iTeamNum);
		Kernel32.INSTANCE.ReadProcessMemory(Jelly.handle, p, m, 4, null);
		
		// TODO: Add settings for triggerbot keycode
		if (m.getInt(0) != Jelly.teamNum && (User32.INSTANCE.GetAsyncKeyState(0x45) & 0x8000) != 0 && id != 0) { // 0x45 = VK_E
			m.setInt(0, 6);
			// 6 = for one tick
			Pointer.nativeValue(p, Jelly.client + Signatures.dwForceAttack);
			Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 4, null);
		}
		
		m.clear();
	}

}
