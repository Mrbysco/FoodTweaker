package com.mrbysco.foodtweaker.handler;

import com.mrbysco.foodtweaker.FoodInfo;
import com.mrbysco.foodtweaker.FoodTweaker;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;

public class SanityHandler {
	@SubscribeEvent
	public void SanityEvent(LivingEntityUseItemEvent.Finish event) {
		if(event.getEntityLiving() instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
			for(HashMap.Entry<ItemStack, FoodInfo> entry : FoodTweaker.instance.foodInfo.entrySet()) {
				ItemStack compareStack = entry.getKey();
				FoodInfo info = entry.getValue();
				if(compareStack.isItemEqualIgnoreDurability(event.getItem())) {
					if(info.getSanityAmount() != 0.0F)
						induceSanity(player, info.getSanityAmount());

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
}
