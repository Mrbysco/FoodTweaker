package com.mrbysco.foodtweaker.ct;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.foodtweaker")
public class ChangeFoodStatsCT {

	@ZenMethod
	public static void changeFoodStats(IItemStack input, int heal, float saturation) {
		CraftTweakerAPI.apply(new ActionChangeFoodStats(input, heal, saturation, false, false));
	}

	@ZenMethod
	public static void changeFoodStatsAndSanity(IItemStack input, int heal, float saturation, float sanity) {
		CraftTweakerAPI.apply(new ActionChangeFoodStats(input, heal, saturation, sanity, false, false));
	}

	@ZenMethod
	public static void changeFoodStatsAndEdibility(IItemStack input, int heal, float saturation, boolean alwaysEdible) {
		CraftTweakerAPI.apply(new ActionChangeFoodStats(input, heal, saturation, true, alwaysEdible));
	}

	@ZenMethod
	public static void changeAlwaysEdibleFoodStatsSanityAndEdibility(IItemStack input, int heal, float saturation, float sanity, boolean alwaysEdible) {
		CraftTweakerAPI.apply(new ActionChangeFoodStats(input, heal, saturation, sanity, true, alwaysEdible));
	}
}
