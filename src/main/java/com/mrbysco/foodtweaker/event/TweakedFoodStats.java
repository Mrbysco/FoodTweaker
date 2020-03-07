package com.mrbysco.foodtweaker.event;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraftforge.common.MinecraftForge;

public class TweakedFoodStats extends FoodStats {
	public TweakedFoodStats() {
		super();
	}

	public void addStats(ItemFood foodItem, ItemStack stack)
	{
		FoodStatsEvent event = new FoodStatsEvent(stack, foodItem.getHealAmount(stack), foodItem.getSaturationModifier(stack));
		if(MinecraftForge.EVENT_BUS.post(event))
			this.addStats(event.getHealAmount(), event.getSaturationModifier());
		 else
			this.addStats(foodItem.getHealAmount(stack), foodItem.getSaturationModifier(stack));

	}

}