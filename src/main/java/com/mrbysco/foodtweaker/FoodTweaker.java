package com.mrbysco.foodtweaker;

import com.mrbysco.foodtweaker.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class FoodTweaker {
	@Instance(Reference.MOD_ID)
	public static FoodTweaker instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

	public static HashMap<ItemStack, FoodInfo> foodInfo = new HashMap<ItemStack, FoodInfo>();

	public static void addFoodInfo(ItemStack food, FoodInfo info)
	{
		// Check if the info doesn't already exist
		if(foodInfo.containsKey(food) ) {
			return;
		} else {
			foodInfo.put(food, info);
		}
	}

	@EventHandler
	public void Preinit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(this);
	}


	@EventHandler
	public void Postinit(FMLPostInitializationEvent event)
	{
		replaceFoodValues();
	}

	@SubscribeEvent
	public void SanityEven(LivingEntityUseItemEvent.Finish event)
	{
		if(event.getEntityLiving() instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
			for(HashMap.Entry<ItemStack, FoodInfo> entry : foodInfo.entrySet()) {
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

	public void replaceFoodValues() {
		for(HashMap.Entry<ItemStack, FoodInfo> entry : foodInfo.entrySet()) {
			if(entry.getKey().getItem() instanceof ItemFood) {
				FoodInfo info = entry.getValue();
				ItemFood item = (ItemFood)entry.getKey().getItem();

				if(item.getHealAmount(entry.getKey()) != info.getHealAmount()) {
					ReflectionHelper.setPrivateValue(ItemFood.class, item, info.getHealAmount(), "healAmount", "field_77853_b");
				}
				if(item.getSaturationModifier(entry.getKey()) != info.getSaturationAmount()) {
					ReflectionHelper.setPrivateValue(ItemFood.class, item, info.getSaturationAmount(), "saturationModifier", "field_77854_c");
				}

				if(info.isAlwaysEdibleChanged()) {
					ReflectionHelper.setPrivateValue(ItemFood.class, item, info.isAlwaysEdible(), "alwaysEdible", "field_77852_bZ");
				}
			}
		}
	}
}
