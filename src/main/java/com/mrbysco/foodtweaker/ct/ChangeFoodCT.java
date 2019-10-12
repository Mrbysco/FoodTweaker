package com.mrbysco.foodtweaker.ct;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.mrbysco.foodtweaker.ct.food.MCFood;
import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Name;

@ZenRegister
@Name("foodtweaker")
public class ChangeFoodCT {
    @Method
    public void changeFood(IItemStack stack, MCFood food) {
        CraftTweakerAPI.apply(new ActionChangeFood(stack, food));
    }

    @Method
    public void removeFood(IItemStack stack) {
        CraftTweakerAPI.apply(new ActionRemoveFood(stack));
    }
}
