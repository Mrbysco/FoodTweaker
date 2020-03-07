package com.mrbysco.foodtweaker.ct;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenSetter;

@ZenClass("foodtweaker.IFood")
@ZenRegister
public interface IFood {

	@ZenSetter("heal")
	void setHeal(int amount);

	@ZenSetter("saturation")
	void setSaturation(float amount);

	@ZenSetter("sanity")
	void setSanity(float sanity);

	@ZenSetter("alwaysEdible")
	void setAlwaysEdible(boolean alwaysEdible);

	@ZenSetter("meat")
	void setMeat(boolean isMeat);

	Object getInternal();
}
