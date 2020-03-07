package com.mrbysco.foodtweaker;

public class FoodInfo {
	private int healAmount;
	private float saturationAmount;
	private float sanityAmount = 0.0F;
	private boolean isWolfsFavoriteMeat;
	private boolean alwaysEdible;

	public FoodInfo(int heal, float saturation) {
		this.healAmount = heal;
		this.saturationAmount = saturation;
	}

	public FoodInfo(int heal, float saturation, boolean isWolfsFavoriteMeat, boolean alwaysEdible) {
		this(heal, saturation);
		this.isWolfsFavoriteMeat = isWolfsFavoriteMeat;
		this.alwaysEdible = alwaysEdible;
	}

	public float getSanityAmount() {
		return sanityAmount;
	}

	public void setSanity(float amount) {
		this.sanityAmount = amount;
	}

	public int getHealAmount() { return healAmount; }

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	public float getSaturationAmount() {
		return saturationAmount;
	}

	public void setSaturationAmount(float saturationAmount) {
		this.saturationAmount = saturationAmount;
	}

	public boolean isWolfsFavoriteMeat() {
		return isWolfsFavoriteMeat;
	}

	public void setWolfsFavoriteMeat(boolean wolfsFavoriteMeat) {
		isWolfsFavoriteMeat = wolfsFavoriteMeat;
	}

	public boolean isAlwaysEdible() {
		return alwaysEdible;
	}

	public void setAlwaysEdible(boolean alwaysEdible) {
		this.alwaysEdible = alwaysEdible;
	}
}
