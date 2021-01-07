package com.mrbysco.foodtweaker.event;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.Nonnull;

public class FoodStatsEvent extends Event {
	private ItemStack foodStack;

	private int healAmount;
	private float saturationModifier;

	public FoodStatsEvent(ItemStack stack, int healAmount, float saturation) {
		super();
		this.foodStack = stack;
		this.healAmount = healAmount;
		this.saturationModifier = saturation;
	}

	@Nonnull
	public ItemStack getStack() {
		return foodStack;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public float getSaturationModifier() {
		return saturationModifier;
	}

	public void setHealAmount(int amount) {
		this.healAmount = amount;
	}

	public void setSaturation(float amount) {
		this.saturationModifier = amount;
	}
}
