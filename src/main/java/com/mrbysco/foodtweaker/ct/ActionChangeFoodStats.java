package com.mrbysco.foodtweaker.ct;

import com.mrbysco.foodtweaker.FoodTweaker;
import com.mrbysco.foodtweaker.FoodInfo;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ActionChangeFoodStats implements IAction {
	private final ItemStack stack;
	private final FoodInfo foodInfo;

	public ActionChangeFoodStats(IItemStack input, MCFoodInfo food) {
		this.stack = CraftTweakerMC.getItemStack(input);
		this.foodInfo = (FoodInfo)food.getInternal();
	}

	@Override
	public void apply() {
		if(stack.getItem() instanceof ItemFood) {
			FoodTweaker.addFoodInfo(stack, foodInfo);
		}
	}

	@Override
	public String describe() {
		if(stack.getItem() instanceof ItemFood) {
			return String.format("Changed food stats for " + stack.getDisplayName() + " has been changed");
		} else {
			return String.format("Could not change stats for " + stack.getDisplayName() + " as it isn't an instance of ItemFood");
		}
	}
}
