package jelly.offsets.offsets;

public class ClientOffsets {
	/**
	 * Client.dll offsets
	 */
	public static long dwRadarBase = 0;
	public static long dwWeaponTable = 0;
	public static long dwWeaponTableIndex = 0;
	public static long dwInput = 0;
	public static long dwGlowObject = 0;
	public static long dwForceJump = 0;
	public static long dwForceAttack = 0;
	public static long dwViewMatrix = 0;
	public static long dwEntityList = 0;
	public static long dwLocalPlayer = 0;
	public static long bDormant = 0;
	public static long dwGameRulesProxy = 0;
	public static long dwMouseEnable = 0;
	public static long dwMouseEnablePtr = 0;

	public static void load() {
//		dwRadarBase = PatternScanner.byPattern(OffsetManager.clientModule(), 0x1, 0x0, PatternScanner.READ or PatternScanner.SUBTRACT, 0xA1, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x0C, 0xB0, 0x8B, 0x01, 0xFF, 0x50, 0x00, 0x46, 0x3B, 0x35, 0x00, 0x00, 0x00, 0x00, 0x7C, 0xEA, 0x8B, 0x0D, 0x00, 0x00, 0x00, 0x00);
//		dwWeaponTable = PatternScanner.byPattern(OffsetManager.clientModule(), 0x1, 0x0, PatternScanner.READ or PatternScanner.SUBTRACT, 0x39, 0x86, 0x00, 0x00, 0x00, 0x00, 0x74, 0x06, 0x89, 0x86, 0x0, 0x0, 0x0, 0x0, 0x8B, 0x86);
//		dwWeaponTableIndex = PatternScanner.byPattern(OffsetManager.clientModule(), 0x2, 0x0, PatternScanner.READ, 0x39, 0x86, 0x00, 0x00, 0x00, 0x00, 0x74, 0x06, 0x89, 0x86, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x86);
//		dwInput = PatternScanner.byPattern(OffsetManager.clientModule(), 0x1, 0x0, PatternScanner.READ or PatternScanner.SUBTRACT, 0xB9, 0x00, 0x00, 0x00, 0x00, 0xF3, 0x0F, 0x11, 0x04, 0x24, 0xFF, 0x50, 0x10);
//		dwGlowObject = PatternScanner.byPattern(OffsetManager.clientModule(), 0x1, 0x4, PatternScanner.READ or PatternScanner.SUBTRACT, 0xA1, 0x00, 0x00, 0x00, 0x00, 0xa8, 0x01, 0x75, 0x00, 0x0f, 0x57, 0xc0, 0xc7, 0x05);
//		dwForceJump = PatternScanner.byPattern(OffsetManager.clientModule(), 0x2, 0x0, PatternScanner.READ or PatternScanner.SUBTRACT, 0x89, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x8B, 0xF2, 0x8B, 0xC1, 0x83, 0xCE, 0x08);
//		dwForceAttack = PatternScanner.byPattern(OffsetManager.clientModule(), 0x2, 0xC, PatternScanner.READ or PatternScanner.SUBTRACT, 0x89, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x8B, 0xF2, 0x8B, 0xC1, 0x83, 0xCE, 0x04);
//		dwViewMatrix = PatternScanner.byPattern(OffsetManager.clientModule(), 0x3, 0xb0, PatternScanner.READ or PatternScanner.SUBTRACT, 0x0F, 0x10, 0x05, 0x00, 0x00, 0x00, 0x00, 0x8D, 0x85, 0x00, 0x00, 0x00, 0x00, 0xB9);
//		dwEntityList = PatternScanner.byPattern(OffsetManager.clientModule(), 0x1, 0x0, PatternScanner.READ or PatternScanner.SUBTRACT, 0xBB, 0x00, 0x00, 0x00, 0x00, 0x83, 0xFF, 0x01, 0x0F, 0x8C, 0x00, 0x00, 0x00, 0x00, 0x3B, 0xF8);
//		dwLocalPlayer = PatternScanner.byPattern(OffsetManager.clientModule(), 3, 4, PatternScanner.READ or PatternScanner.SUBTRACT, 0x8D, 0x34, 0x85, 0x0, 0x0, 0x0, 0x0, 0x89, 0x15, 0x0, 0x0, 0x0, 0x0, 0x8B, 0x41, 0x08, 0x8B, 0x48, 0x04, 0x83, 0xF9, 0xFF);
//		bDormant = PatternScanner.byPattern(OffsetManager.clientModule(), 0x2, 0x0, PatternScanner.READ, 0x88, 0x9E, 0x0, 0x0, 0x0, 0x0, 0xE8, 0x0, 0x0, 0x0, 0x0, 0x53, 0x8D, 0x8E, 0x0, 0x0, 0x0, 0x0, 0xE8, 0x0, 0x0, 0x0, 0x0, 0x8B, 0x06, 0x8B, 0xCE, 0x53, 0xFF, 0x90, 0x0, 0x0, 0x0, 0x0, 0x8B, 0x46, 0x64, 0x0F, 0xB6, 0xCB, 0x5E, 0x5B, 0x66, 0x89, 0x0C, 0xC5, 0x0, 0x0, 0x0, 0x0, 0x5D, 0xC2, 0x04, 0x00);
//		dwGameRulesProxy = PatternScanner.byPattern(OffsetManager.clientModule(), 1, 0, PatternScanner.READ or PatternScanner.SUBTRACT, 0xA1, 0x0, 0x0, 0x0, 0x0, 0x85, 0xC0, 0x0F, 0x84, 0x0, 0x0, 0x0, 0x0, 0x80, 0xB8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x74, 0x7A);
//		dwMouseEnable = PatternScanner.byPattern(OffsetManager.clientModule(), 1, 48, PatternScanner.READ or PatternScanner.SUBTRACT, 0xB9, 0x0, 0x0, 0x0, 0x0, 0xFF, 0x50, 0x34, 0x85, 0xC0, 0x75, 0x10);
//		dwMouseEnablePtr = PatternScanner.byPattern(OffsetManager.clientModule(), 1, 0, PatternScanner.READ or PatternScanner.SUBTRACT, 0xB9, 0x0, 0x0, 0x0, 0x0, 0xFF, 0x50, 0x34, 0x85, 0xC0, 0x75, 0x10);
		
		bDormant = 0xED;
	}
}
