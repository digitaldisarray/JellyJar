package jelly.modules.impl;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;

import jelly.Jelly;
import jelly.modules.Module;
import jelly.offsets.Signatures;

public class Chams extends Module {
	
	private byte r, g, b;
	private int brightness;
	
	public Chams(byte r, byte g, byte b, int brightness) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.brightness = brightness;
	}
	
	public void onLoop() {
		// Entity brightness
		Pointer p = new Pointer(0);
		Pointer.nativeValue(p, Jelly.engine + Signatures.model_ambient_min); // - 0x2c
		Memory m = new Memory(4);
		m.setInt(0, brightness);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 4, null);
	}
	
	public void onEntityLoop(long entity) {
		// Entity colors
		Pointer p = new Pointer(0);
		Memory m = new Memory(1);
		
		Pointer.nativeValue(p, entity + 0x70);
		m.setByte(0, r);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);
		
		Pointer.nativeValue(p, entity + 0x71);
		m.setByte(0, g);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);
		
		Pointer.nativeValue(p, entity + 0x72);
		m.setByte(0, b);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);
		
	}
	
}
