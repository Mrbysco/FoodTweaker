package com.mrbysco.foodtweaker.ct;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.foodtweaker.Tweaker")
@ZenRegister
public class ChangeFoodStatsCT {
	@ZenMethod
	public static void changeFoodStats(IItemStack input, MCFoodInfo info) {
		CraftTweakerAPI.apply(new ActionChangeFoodStats(input, info));
	}
}
