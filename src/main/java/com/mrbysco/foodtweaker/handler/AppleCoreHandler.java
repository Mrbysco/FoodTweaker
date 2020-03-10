package com.mrbysco.foodtweaker.handler;

import com.mrbysco.foodtweaker.FoodInfo;
import com.mrbysco.foodtweaker.FoodTweaker;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

public class AppleCoreHandler {

	@SubscribeEvent
	public void FoodStatsEvent(squeek.applecore.api.food.FoodEvent.GetFoodValues event) {
		ItemStack stackCopy = event.food.copy();
		stackCopy.setCount(1);
		for (Map.Entry<ItemStack, FoodInfo> entry : FoodTweaker.instance.foodInfo.entrySet()) {
			ItemStack key = entry.getKey();
			FoodInfo info = entry.getValue();
			if(stackCopy.isItemEqualIgnoreDurability(key)) {
				event.foodValues = new squeek.applecore.api.food.FoodValues(info.getHealAmount(), info.getSaturationAmount());
				break;
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void changeValues(AttachCapabilitiesEvent<ItemStack> event) {
		if (!(event.getObject() instanceof ItemStack)) return;

		if(event.getObject() != null && event.getObject().getItem() instanceof ItemFood) {
			ItemStack stack = event.getObject();
			if (stack != null && !stack.isEmpty()) {
				if (!FoodTweaker.instance.foodInfo.isEmpty()) {
					for (Map.Entry<ItemStack, FoodInfo> entry : FoodTweaker.instance.foodInfo.entrySet()) {
						ItemStack key = entry.getKey();
						FoodInfo info = entry.getValue();
						if (stack.isItemEqualIgnoreDurability(key)) {
							ItemFood food = (ItemFood) stack.getItem();

							if(food.isWolfsFavoriteMeat != info.isWolfsFavoriteMeat())
								food.isWolfsFavoriteMeat = info.isWolfsFavoriteMeat();

							if(food.alwaysEdible != info.isAlwaysEdible())
								food.alwaysEdible = info.isAlwaysEdible();

							break;
						}
					}
				}
			}
		}
	}
}
