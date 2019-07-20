package com.mrbysco.foodtweaker;

public class FoodInfo {

	private int healAmount;
	private float saturationAmount;
	private float sanityAmount;
	private boolean changeAlwaysEdible;
	private boolean alwaysEdible;

	public FoodInfo(int heal, float saturation, boolean changeAlwaysEdible, boolean alwaysEdible) {
		this.healAmount = heal;
		this.saturationAmount = saturation;
		this.sanityAmount = 0.0F;
		this.changeAlwaysEdible = changeAlwaysEdible;
		this.alwaysEdible = alwaysEdible;
	}

	public FoodInfo(int heal, float saturation, float sanity, boolean changeAlwaysEdible, boolean alwaysEdible) {
		this.healAmount = heal;
		this.saturationAmount = saturation;
		this.sanityAmount = sanity;
		this.changeAlwaysEdible = changeAlwaysEdible;
		this.alwaysEdible = alwaysEdible;
	}

	public int getHealAmount() { return healAmount; }

	public float getSaturationAmount() {
		return saturationAmount;
	}

	public float getSanityAmount() {
		return sanityAmount;
	}

	public boolean isAlwaysEdibleChanged() { return changeAlwaysEdible; }

	public boolean isAlwaysEdible() { return alwaysEdible; }
}
