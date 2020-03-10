package com.mrbysco.foodtweaker.ct;

import com.mrbysco.foodtweaker.FoodInfo;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenConstructor;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("foodtweaker.FoodInfo")
@ZenRegister
public class MCFoodInfo {
	private final FoodInfo internal;

	private MCFoodInfo(FoodInfo internal) {
		this.internal = internal;
	}

	@ZenConstructor
	public MCFoodInfo(IItemStack iItemStack) {
		ItemStack stack = CraftTweakerMC.getItemStack(iItemStack);
		if(!(stack.getItem() instanceof ItemFood))
			throw new IllegalArgumentException("stack does not extend ItemFood");

		ItemFood food = (ItemFood)stack.getItem();
		internal = new FoodInfo(food.healAmount, food.saturationModifier, food.isWolfsFavoriteMeat, food.alwaysEdible);
	}

	@ZenConstructor
	public MCFoodInfo(int healAmount, float saturation, boolean isMeat, boolean alwaysEdible) {
		internal = new FoodInfo(healAmount, saturation, isMeat, alwaysEdible);
	}

	@ZenMethod
	public MCFoodInfo setHeal(int amount) {
		FoodInfo copy = internal;
		copy.setHealAmount(amount);
		return new MCFoodInfo(copy);
	}

	@ZenMethod
	public MCFoodInfo setSaturation(float amount) {
		FoodInfo copy = internal;
		copy.setSaturationAmount(amount);
		return new MCFoodInfo(copy);
	}

	@ZenMethod
	public MCFoodInfo setSanity(float sanity) {
		FoodInfo copy = internal;
		copy.setSanity(sanity);
		return new MCFoodInfo(copy);
	}

	@ZenMethod
	public MCFoodInfo setAlwaysEdible(boolean alwaysEdible) {
		FoodInfo copy = internal;
		copy.setAlwaysEdible(alwaysEdible);
		return new MCFoodInfo(copy);
	}

	@ZenMethod
	public MCFoodInfo setMeat(boolean isMeat) {
		FoodInfo copy = internal;
		copy.setWolfsFavoriteMeat(isMeat);
		return new MCFoodInfo(copy);
	}

	public FoodInfo getInternal() {
		return internal;
	}
}
