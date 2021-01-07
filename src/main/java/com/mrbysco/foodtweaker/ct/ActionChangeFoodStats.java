package com.mrbysco.foodtweaker.ct;

import com.mrbysco.foodtweaker.FoodInfo;
import com.mrbysco.foodtweaker.FoodTweaker;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionChangeFoodStats implements IAction {
	private final List<ItemStack> stacks;
	private final FoodInfo foodInfo;

	public ActionChangeFoodStats(IItemStack[] inputs, MCFoodInfo food) {
		List<ItemStack> list = new ArrayList<>();
		for(IItemStack input : inputs) {
			list.add(CraftTweakerMC.getItemStack(input));
		}
		this.stacks = list;
		this.foodInfo = food.getInternal();
	}

	public ActionChangeFoodStats(IItemStack input, MCFoodInfo food) {
		this.stacks = Collections.singletonList(CraftTweakerMC.getItemStack(input));
		this.foodInfo = food.getInternal();
	}

	@Override
	public void apply() {
		if(!stacks.isEmpty()) {

		}
		for(ItemStack stack : stacks) {
			if(stack.getItem() instanceof ItemFood) {
				FoodTweaker.addFoodInfo(stack, foodInfo);
			}
		}
	}

	@Override
	public String describe() {
		if(!stacks.isEmpty()) {
			if(stacks.size() > 1) {
				for(ItemStack stack : stacks) {
					if(stack.getItem() instanceof ItemFood) {
						return "Changed food stats for " + stack.getDisplayName() + " has been changed";
					} else {
						return "Could not change stats for " + stack.getDisplayName() + " as it isn't an instance of ItemFood";
					}
				}
			} else {
				ItemStack stack = stacks.get(0);
				if(stack.getItem() instanceof ItemFood) {
					return "Changed food stats for " + stack.getDisplayName() + " has been changed";
				} else {
					return "Could not change stats for " + stack.getDisplayName() + " as it isn't an instance of ItemFood";
				}
			}
		}
		return "Stack provided is empty. Can't change food value";
	}
}
