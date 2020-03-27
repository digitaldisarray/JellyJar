package jelly.modules.impl;

import java.awt.Color;

import com.github.jonatino.misc.Utils;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;

import jelly.Jelly;
import jelly.Util;
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
	
	/*
	 * Thinking of alternate methods: 
	 * 
	 * https://www.unknowncheats.me/forum/counterstrike-global-offensive/283060-chamming-xqz-externally-modifying-modelrenderinfo_t-2.html
	 * Using ModelRenderInfo_t (ModelRenderInfo_t struct https://github.com/iWanheda/wanhedacsgo/blob/5c611a390cebe4f2fd21ad92ee3b131693b950e4/SDK/ModelRender.h) 
	 * Write our own vmt to the entity and replace $phong with $ignorez
	 * list of pointers to materials at around 0x04000000
	 * 
	 * https://www.youtube.com/watch?v=tVltnnh2Dso
	 * Color Modulate the player entity material
	 * Set $ignorez flag in shader parameters
	 * 
	 * Maybe something to do with MaterialVarFlags_t
	 * https://github.com/spirthack/CSGOSimple/blob/1cfdc3817790337c1110df6ad924ae0c15177660/CSGOSimple/valve_sdk/interfaces/IVModelRender.hpp
	 * 
	 */

	// Entity brightness and viewmodel fix
	public void onLoop() {
		Pointer p = new Pointer(0);
		Pointer.nativeValue(p, Jelly.engine + Signatures.model_ambient_min);
		Memory m = new Memory(4);
		int addr = (int) (Jelly.engine + Signatures.model_ambient_min - 0x2c);

		/*
		 * Java removes information about brightness being a float when we cast it to an
		 * int so we must convert it to an int with its raw data
		 */
		int bright = Float.floatToIntBits(brightness);

		m.setInt(0, bright ^ addr);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 4, null);
		m.clear();

		m = new Memory(1);

		// Attempting to fix the viewmodles brightness
		
//		int viewmodelHandler = (int) (Jelly.localPlayer + NetVarOffsets.DT_BasePlayer.m_hViewModel);
//		int currentViewmodelEntity = (int) (Jelly.client + Signatures.dwEntityList
//				+ (((viewmodelHandler & 0xFFF) - 1) * 16));
//		Pointer.nativeValue(p, currentViewmodelEntity + 0x70);
//		Kernel32.INSTANCE.ReadProcessMemory(Jelly.handle, p, m, 1, null);
		// RBGA values
		
		
		int currentViewmodelEntity = (int) (Jelly.localPlayer + NetVarOffsets.DT_BasePlayer.m_hViewModel);

//		m.setByte(0, (byte) ((brightness == 0) ? 255 : (int) (255.f / (brightness / 10.f))));
		m.setByte(0, (byte) 0);

		Pointer.nativeValue(p, currentViewmodelEntity + 0x70);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 4, null);
		
		Pointer.nativeValue(p, currentViewmodelEntity + 0x71);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 4, null);
		
		Pointer.nativeValue(p, currentViewmodelEntity + 0x72);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 4, null);
	}

	// Entity colors
	@Override
	public void onEntityLoop(int entity) {
		Pointer p = new Pointer(0);
		Memory m = new Memory(4);

		
		System.out.println(Util.getClassID(entity));
		// Check if the entity is on our team
		Pointer.nativeValue(p, NetVarOffsets.DT_BaseEntity.m_iTeamNum);
		Kernel32.INSTANCE.ReadProcessMemory(Jelly.handle, p, m, 4, null);
		
		m.clear();
		m = new Memory(1);

		// Red
		Pointer.nativeValue(p, entity + 0x70);
		m.setByte(0, rEnemy);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);

		// Green
		Pointer.nativeValue(p, entity + 0x71);
		m.setByte(0, gEnemy);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);

		// Blue
		Pointer.nativeValue(p, entity + 0x72);
		m.setByte(0, bEnemy);
		Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);

		m.clear();

//			m = new Memory(1);
//			m.setByte(0, (byte) ((brightness == 0) ? 255 : (int) (255.f / (brightness / 10.f))));
//
//			Pointer.nativeValue(p, entity + 0x70);
//			Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);
//			Pointer.nativeValue(p, entity + 0x71);
//			Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);
//			Pointer.nativeValue(p, entity + 0x72);
//			Kernel32.INSTANCE.WriteProcessMemory(Jelly.handle, p, m, 1, null);

	}

	private void temp(int entity) {
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
