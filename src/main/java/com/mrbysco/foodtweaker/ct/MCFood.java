package com.mrbysco.foodtweaker.ct;

import com.mrbysco.foodtweaker.FoodInfo;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class MCFood implements IFood{
	private final FoodInfo internal;

	public MCFood(FoodInfo internal) {
		this.internal = internal;
	}

	public MCFood(IItemStack iItemStack) {
		ItemStack stack = CraftTweakerMC.getItemStack(iItemStack);
		if(!(stack.getItem() instanceof ItemFood))
			throw new IllegalArgumentException("stack does not extend ItemFood");

		ItemFood food = (ItemFood)stack.getItem();
		internal = new FoodInfo(food.healAmount, food.saturationModifier, food.isWolfsFavoriteMeat, food.alwaysEdible);
	}

	@Override
	public void setHeal(int amount) {
		internal.setHealAmount(amount);
	}

	@Override
	public void setSaturation(float amount) {
		internal.setSaturationAmount(amount);
	}

	@Override
	public void setSanity(float sanity) {
		internal.setSanity(sanity);
	}

	@Override
	public void setAlwaysEdible(boolean alwaysEdible) {
		internal.setAlwaysEdible(alwaysEdible);
	}

	@Override
	public void setMeat(boolean isMeat) {
		internal.setWolfsFavoriteMeat(isMeat);
	}

	@Override
	public Object getInternal() {
		return internal;
	}
}
