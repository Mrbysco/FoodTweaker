package com.mrbysco.foodtweaker.ct;

import com.mrbysco.foodtweaker.FoodTweaker;
import com.mrbysco.foodtweaker.FoodInfo;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;

public class ActionChangeFoodStats implements IAction {
	private final ItemStack stack;
	private final int healAmount;
	private final float saturationAmount;
	private final float sanityAmount;
	private final boolean changeAlwaysEdible;
	private final boolean alwaysEdible;

	public ActionChangeFoodStats(IItemStack input, int heal, float saturation, boolean changeAlwaysEdible, boolean alwaysEdible) {
		this.stack = CraftTweakerMC.getItemStack(input);
		this.healAmount = heal;
		this.saturationAmount = saturation;
		this.sanityAmount = 0.0F;
		this.changeAlwaysEdible = changeAlwaysEdible;
		this.alwaysEdible = alwaysEdible;
	}

	public ActionChangeFoodStats(IItemStack input, int heal, float saturation, float sanity, boolean changeAlwaysEdible, boolean alwaysEdible) {
		this.stack = CraftTweakerMC.getItemStack(input);
		this.healAmount = heal;
		this.saturationAmount = saturation;
		this.sanityAmount = sanity;
		this.changeAlwaysEdible = changeAlwaysEdible;
		this.alwaysEdible = alwaysEdible;
	}

	@Override
	public void apply() {
		FoodTweaker.addFoodInfo(stack, new FoodInfo(healAmount, saturationAmount, sanityAmount, changeAlwaysEdible, alwaysEdible));
	}

	@Override
	public String describe() {
		return String.format("Changed food stats for " + stack.getDisplayName() + " has been changed");
	}
}
