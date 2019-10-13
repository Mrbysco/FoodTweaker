package com.mrbysco.foodtweaker.ct;

import com.blamejared.crafttweaker.api.actions.IAction;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.mrbysco.foodtweaker.FoodTweaker;
import com.mrbysco.foodtweaker.ct.food.MCFood;

public class ActionChangeFood implements IAction {

    public final IItemStack stack;
    public final MCFood food;

    public ActionChangeFood(IItemStack stack, MCFood food) {
        this.stack = stack;
        this.food = food;
    }

    @Override
    public void apply() {
        FoodTweaker.changeFood(stack.getInternal().getItem(), food.getInternal());
    }

    @Override
    public String describe() {
        if(this.stack.getInternal().isFood()) {
            return String.format("Food values for " + stack.getDisplayName() + " have been changed");
        } else {
            return String.format(stack.getDisplayName() + " has been made edible");
        }
    }
}
