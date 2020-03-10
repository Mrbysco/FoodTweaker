package com.mrbysco.foodtweaker;

import com.mrbysco.foodtweaker.handler.AppleCoreHandler;
import com.mrbysco.foodtweaker.handler.FoodHandler;
import com.mrbysco.foodtweaker.handler.SanityHandler;
import com.mrbysco.foodtweaker.proxy.CommonProxy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
		if(Loader.isModLoaded("sanity")) {
			MinecraftForge.EVENT_BUS.register(new SanityHandler());
		}

		if(Loader.isModLoaded("applecore")) {
			MinecraftForge.EVENT_BUS.register(new AppleCoreHandler());
		} else {
			MinecraftForge.EVENT_BUS.register(new FoodHandler());
		}
	}


	@EventHandler
	public void Postinit(FMLPostInitializationEvent event)
	{

	}
}
