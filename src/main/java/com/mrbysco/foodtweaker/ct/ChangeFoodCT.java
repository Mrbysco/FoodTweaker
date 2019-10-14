package com.mrbysco.foodtweaker.ct;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.mrbysco.foodtweaker.ct.food.MCFood;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.foodtweaker.Tweaker")
public class ChangeFoodCT {

    @ZenCodeType.Method
    public static void changeFood(IItemStack stack, MCFood food) {
        CraftTweakerAPI.apply(new ActionChangeFood(stack, food));
    }

    @ZenCodeType.Method
    public static void removeFood(IItemStack stack) {
        CraftTweakerAPI.apply(new ActionRemoveFood(stack));
    }

    @ZenCodeType.Method
    public static void removeFood(IItemStack[] stack) {
        CraftTweakerAPI.apply(new ActionRemoveFood(stack));
    }
}
