package jelly.modules.impl;

import java.nio.ByteBuffer;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;

import jelly.Jelly;
import jelly.modules.Module;
import jelly.offsets.Signatures;
import jelly.offsets.offsets.NetVarOffsets;

public class Chams extends Module {

	private byte rEnemy, gEnemy, bEnemy;
	private byte rTeam, gTeam, bTeam;
	private float brightness;

	// TODO: Defeat entire purpose of args and fields for storing settings with
	// settings manager
	public Chams(byte rEnemy, byte gEnemy, byte bEnemy, byte rTeam, byte gTeam, byte bTeam, float brightness) {
		super();
		this.rEnemy = rEnemy;
		this.gEnemy = gEnemy;
		this.bEnemy = bEnemy;
		this.rTeam = rTeam;
		this.gTeam = gTeam;
		this.bTeam = bTeam;
		this.brightness = brightness;
	}

	// Entity brightness
	public void onLoop() {
		Pointer p = new Pointer(0);
		Pointer.nativeValue(p, Jelly.engine + Signatures.model_ambient_min);
		Memory m = new Memory(4);
		int addr = (int) (Jelly.engine + Signatures.model_ambient_min - 0x2c);
		int bright = Float.floatToIntBits(brightness);
		m.setInt(0, bright ^ addr);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 4, null);
		m.clear();
	}

	public static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	// Entity colors
	public void onEntityLoop(long entity) {

		Pointer p = new Pointer(0);
		Memory m = new Memory(4);

		// Check if the entity is on our team
		Pointer.nativeValue(p, NetVarOffsets.DT_BaseEntity.m_iTeamNum);
		Kernel32.INSTANCE.ReadProcessMemory(Jelly.handle, p, m, 4, null);
		boolean enemy = false;
		if (m.getInt(0) != Jelly.teamNum)
			enemy = true;
//		boolean enemy = (m.getInt(0) != Jelly.teamNum);

		m.clear();
		m = new Memory(1);

		// Red
		Pointer.nativeValue(p, entity + 0x70);
		if (enemy)
			m.setByte(0, rEnemy);
		else
			m.setByte(0, rTeam);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);

		// Green
		Pointer.nativeValue(p, entity + 0x71);
		if (enemy)
			m.setByte(0, gEnemy);
		else
			m.setByte(0, gTeam);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);

		// Blue
		Pointer.nativeValue(p, entity + 0x72);
		if (enemy)
			m.setByte(0, bEnemy);
		else
			m.setByte(0, bTeam);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);

		m.clear();
	}

}
