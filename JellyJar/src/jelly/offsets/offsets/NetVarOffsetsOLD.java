package jelly.offsets.offsets;

import jelly.offsets.netvars.NetVars;

public class NetVarOffsetsOLD {

	/**
	 * Netvar offsets
	 */
	public static long nFallbackPaintKit;
	public static long nFallbackSeed;
	public static long nFallbackStatTrak;
	public static long iEntityQuality;
	public static long flFallbackWear;
	public static long iItemDefinitionIndex;
	public static long OriginalOwnerXuidLow;
	public static long iItemIDHigh;
	public static long iAccountID;
	public static long iViewModelIndex;
	public static long iWorldModelIndex;
	public static long iWorldDroppedModelIndex;
	public static long hViewModel;
	public static long nModelIndex;
	public static long bIsScoped;
	public static long flNextPrimaryAttack;
	public static long iWeaponID;
	public static long flFlashMaxAlpha;
	public static long iShotsFired;
	public static long hMyWeapons;
	public static long iClip1;
	public static long iClip2;
	public static long dwBoneMatrix;
	public static long iCrossHairID;
	public static long nSurvivalTeam;
	public static long dwModel;
	public static long dwIndex;
	public static long bMoveType;
	public static long flSurvivalStartTime;
	public static long m_SurvivalGameRuleDecisionTypes;
	
	public static class BasePlayer {
		public static long fFlags;
		public static long iHealth;
		public static long iTeamNum;
		public static long vecViewOffset;
		public static long hActiveWeapon;
		public static long vecVelocity;
		public static long nTickBase;
		public static long lifeState;
		public static long vecPunch;
		public static long nModelIndex;
	}
	
	public static class BaseEntity {
		public static long nModelIndex;
		public static long bSpotted;
		public static long bSpottedByMask;
		public static long vecOrigin;
		public static long iTeamNum;
	}

	public static void load() {
		BasePlayer.fFlags = NetVars.byName("DT_BasePlayer", "m_fFlags");
		BasePlayer.iHealth = NetVars.byName("DT_BasePlayer", "m_iHealth");
		BasePlayer.vecViewOffset = NetVars.byName("DT_BasePlayer", "m_vecViewOffset[0]");
		BasePlayer.hActiveWeapon = NetVars.byName("DT_BasePlayer", "m_hActiveWeapon");
		BasePlayer.nTickBase = NetVars.byName("DT_BasePlayer", "m_nTickBase");
		BasePlayer.vecVelocity = NetVars.byName("DT_BasePlayer", "m_vecVelocity[0]");
		BasePlayer.lifeState = NetVars.byName("DT_BasePlayer", "m_lifeState");
		BasePlayer.vecPunch = NetVars.byName("DT_BasePlayer", "m_aimPunchAngle");
		BasePlayer.nModelIndex = NetVars.byName("DT_BasePlayer", "m_nModelIndex");
		BasePlayer.iTeamNum = NetVars.byName("DT_BasePlayer", "m_iTeamNum");
		

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

		BaseEntity.bSpotted = NetVars.byName("DT_BaseEntity", "m_bSpotted");
		BaseEntity.bSpottedByMask = NetVars.byName("DT_BaseEntity", "m_bSpottedByMask");
		BaseEntity.vecOrigin = NetVars.byName("DT_BaseEntity", "m_vecOrigin");
		BaseEntity.iTeamNum = NetVars.byName("DT_BaseEntity", "m_iTeamNum");
		BaseEntity.nModelIndex = NetVars.byName("DT_BaseEntity", "m_nModelIndex");

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
