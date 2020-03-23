package jelly.offsets.offsets;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.jonatino.misc.Strings;

import jelly.offsets.netvars.NetVars;

public class NetVarOffsets {

	/**
	 * Netvar offsets
	 */
	public static long nFallbackPaintKit = 0;
	public static long nFallbackSeed = 0;
	public static long nFallbackStatTrak = 0;
	public static long iEntityQuality = 0;
	public static long flFallbackWear = 0;
	public static long iItemDefinitionIndex = 0;
	public static long OriginalOwnerXuidLow = 0;
	public static long iItemIDHigh = 0;
	public static long iAccountID = 0;
	public static long iViewModelIndex = 0;
	public static long iWorldModelIndex = 0;
	public static long iWorldDroppedModelIndex = 0;
	public static long hViewModel = 0;
	public static long nModelIndex = 0;
	public static long fFlags = 0;
	public static long iHealth = 0;
	public static long nTickBase = 0;
	public static long bIsScoped = 0;
	public static long flNextPrimaryAttack = 0;
	public static long iWeaponID = 0;
	public static long vecViewOffset = 0;
	public static long hActiveWeapon = 0;
	public static long vecVelocity = 0;
	public static long lifeState = 0;
	public static long vecPunch = 0;
	public static long flFlashMaxAlpha = 0;
	public static long iShotsFired = 0;
	public static long hMyWeapons = 0;
	public static long iClip1 = 0;
	public static long iClip2 = 0;
	public static long bSpotted = 0;
	public static long bSpottedByMask = 0;
	public static long vecOrigin = 0;
	public static long iTeamNum = 0;
	public static long dwBoneMatrix = 0;
	public static long iCrossHairID = 0;
	public static long nSurvivalTeam = 0;
	public static long dwModel = 0;
	public static long dwIndex = 0;
	public static long bMoveType = 0;
	public static long flSurvivalStartTime = 0;
	public static long m_SurvivalGameRuleDecisionTypes = 0;

	public static void load() {
		fFlags = NetVars.byName("DT_BasePlayer", "m_fFlags");
		iHealth = NetVars.byName("DT_BasePlayer", "m_iHealth");
		vecViewOffset = NetVars.byName("DT_BasePlayer", "m_vecViewOffset[0]");
		hActiveWeapon = NetVars.byName("DT_BasePlayer", "m_hActiveWeapon");
		nTickBase = NetVars.byName("DT_BasePlayer", "m_nTickBase");
		vecVelocity = NetVars.byName("DT_BasePlayer", "m_vecVelocity[0]");
		lifeState = NetVars.byName("DT_BasePlayer", "m_lifeState");
		vecPunch = NetVars.byName("DT_BasePlayer", "m_aimPunchAngle");

		flFlashMaxAlpha = NetVars.byName("DT_CSPlayer", "m_flFlashMaxAlpha");
		iShotsFired = NetVars.byName("DT_CSPlayer", "m_iShotsFired");
		bIsScoped = NetVars.byName("DT_CSPlayer", "m_bIsScoped");
		hMyWeapons = NetVars.byName("DT_CSPlayer", "m_hMyWeapons");
		hViewModel = NetVars.byName("DT_CSPlayer", "m_hViewModel[0]");
		iCrossHairID = NetVars.byName("DT_CSPlayer", "m_bHasDefuser") + 0x5C;
		nSurvivalTeam = NetVars.byName("DT_CSPlayer", "m_nSurvivalTeam");

		flNextPrimaryAttack = NetVars.byName("DT_BaseCombatWeapon", "m_flNextPrimaryAttack");
		iClip1 = NetVars.byName("DT_BaseCombatWeapon", "m_iClip1");
		iClip2 = NetVars.byName("DT_BaseCombatWeapon", "m_iClip2");

		bSpotted = NetVars.byName("DT_BaseEntity", "m_bSpotted");
		bSpottedByMask = NetVars.byName("DT_BaseEntity", "m_bSpottedByMask");
		vecOrigin = NetVars.byName("DT_BaseEntity", "m_vecOrigin");
		iTeamNum = NetVars.byName("DT_BaseEntity", "m_iTeamNum");

		iWeaponID = NetVars.byName("DT_WeaponCSBase", "m_fAccuracyPenalty") + 0x2C;
		iAccountID = NetVars.byName("DT_WeaponCSBase", "m_iAccountID");
		nFallbackPaintKit = NetVars.byName("DT_WeaponCSBase", "m_nFallbackPaintKit");
		nFallbackSeed = NetVars.byName("DT_WeaponCSBase", "m_nFallbackSeed");
		nFallbackStatTrak = NetVars.byName("DT_WeaponCSBase", "m_nFallbackStatTrak");
		iEntityQuality = NetVars.byName("DT_WeaponCSBase", "m_iEntityQuality");
		flFallbackWear = NetVars.byName("DT_WeaponCSBase", "m_flFallbackWear");
		iItemDefinitionIndex = NetVars.byName("DT_WeaponCSBase", "m_iItemDefinitionIndex");
		OriginalOwnerXuidLow = NetVars.byName("DT_WeaponCSBase", "m_OriginalOwnerXuidLow");
		iItemIDHigh = NetVars.byName("DT_WeaponCSBase", "m_iItemIDHigh");
		iViewModelIndex = NetVars.byName("DT_WeaponCSBase", "m_iViewModelIndex");
		iWorldModelIndex = NetVars.byName("DT_WeaponCSBase", "m_iWorldModelIndex");
		iWorldDroppedModelIndex = NetVars.byName("DT_WeaponCSBase", "m_iWorldDroppedModelIndex");

		dwBoneMatrix = NetVars.byName("DT_BaseAnimating", "m_nForceBone") + 0x1c;

		nModelIndex = NetVars.byName("DT_BaseViewModel", "m_nModelIndex");

		flSurvivalStartTime = NetVars.byName("DT_CSGameRulesProxy", "m_flSurvivalStartTime");
		m_SurvivalGameRuleDecisionTypes = NetVars.byName("DT_CSGameRulesProxy", "m_SurvivalGameRuleDecisionTypes");

		dwModel = 0x6C;
		dwIndex = 0x64;
		bMoveType = 0x258;
	}
}
