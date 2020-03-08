package com.mrbysco.foodtweaker.ct;

import com.mrbysco.foodtweaker.FoodInfo;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenConstructor;
import stanhebben.zenscript.annotations.ZenSetter;

@ZenClass("foodtweaker.MCFoodInfo")
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

	@ZenSetter("heal")
	public void setHeal(int amount) {
		internal.setHealAmount(amount);
	}

	@ZenSetter("saturation")
	public void setSaturation(float amount) {
		internal.setSaturationAmount(amount);
	}

	@ZenSetter("sanity")
	public void setSanity(float sanity) {
		internal.setSanity(sanity);
	}

	@ZenSetter("alwaysEdible")
	public void setAlwaysEdible(boolean alwaysEdible) {
		internal.setAlwaysEdible(alwaysEdible);
	}

	@ZenSetter("meat")
	public void setMeat(boolean isMeat) {
		internal.setWolfsFavoriteMeat(isMeat);
	}

	public FoodInfo getInternal() {
		return internal;
	}
}
