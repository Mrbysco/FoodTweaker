package com.mrbysco.foodtweaker.handler;

import com.mrbysco.foodtweaker.FoodInfo;
import com.mrbysco.foodtweaker.FoodTweaker;
import com.mrbysco.foodtweaker.event.FoodStatsEvent;
import com.mrbysco.foodtweaker.event.TweakedFoodStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class FoodHandler {

	@SubscribeEvent
	public void ItemUseEvent(LivingEntityUseItemEvent.Start event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
			if(!(player.getFoodStats() instanceof TweakedFoodStats)) {
				FoodStats oldStats = player.getFoodStats();
				FoodStats newStats = createFoodStats(oldStats);

				player.foodStats = newStats;
			}
		}
	}

	public TweakedFoodStats createFoodStats(FoodStats oldStats) {
		TweakedFoodStats newStats = new TweakedFoodStats();
		newStats.foodLevel = oldStats.foodLevel;
		newStats.foodSaturationLevel = oldStats.foodSaturationLevel;
		newStats.foodExhaustionLevel = oldStats.foodExhaustionLevel;
		newStats.foodTimer = oldStats.foodTimer;
		newStats.prevFoodLevel = oldStats.prevFoodLevel;

		return newStats;
	}

	@SubscribeEvent
	public void StatHandler(FoodStatsEvent event) {
		ItemStack stackCopy = event.getStack().copy();
		for (Map.Entry<ItemStack, FoodInfo> entry : FoodTweaker.instance.foodInfo.entrySet()) {
			ItemStack key = entry.getKey();
			FoodInfo info = entry.getValue();
			if(stackCopy.isItemEqualIgnoreDurability(key)) {
				if(event.getHealAmount() != info.getHealAmount())
					event.setHealAmount(info.getHealAmount());

				if(event.getSaturationModifier() != info.getSaturationAmount())
					event.setSaturation(info.getSaturationAmount());
			}
		}
	}

	@SubscribeEvent
	public void SanityEvent(LivingEntityUseItemEvent.Finish event)
	{
		if(event.getEntityLiving() instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
			for(HashMap.Entry<ItemStack, FoodInfo> entry : FoodTweaker.instance.foodInfo.entrySet()) {
				ItemStack compareStack = entry.getKey();
				FoodInfo info = entry.getValue();
				if(compareStack.isItemEqualIgnoreDurability(event.getItem())) {
					if(info.getSanityAmount() != 0.0F) {
						induceSanity(player, info.getSanityAmount());
					}
					break;
				} else {
					return;
				}
			}
		}
	}

	@Method(modid = "sanity")
	public void induceSanity(EntityPlayerMP player, float sanity) {
		net.tiffit.sanity.SanityCapability cap = player.getCapability(net.tiffit.sanity.SanityCapability.INSTANCE, null);
		cap.increaseSanity(sanity);
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

							if(food.healAmount != info.getHealAmount())
								food.healAmount = info.getHealAmount();

							if(food.saturationModifier != info.getSaturationAmount())
								food.saturationModifier = info.getSaturationAmount();

							if(food.isWolfsFavoriteMeat != info.isWolfsFavoriteMeat())
								food.isWolfsFavoriteMeat = info.isWolfsFavoriteMeat();

							if(food.isWolfsFavoriteMeat != info.isAlwaysEdible())
								food.isWolfsFavoriteMeat = info.isAlwaysEdible();
						}
					}
				}
			}
		}
	}
}
