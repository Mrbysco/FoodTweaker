package com.mrbysco.foodtweaker.ct;

import com.blamejared.crafttweaker.api.actions.IUndoableAction;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.mrbysco.foodtweaker.FoodTweaker;
import com.mrbysco.foodtweaker.ct.food.MCFood;

public class ActionChangeFood implements IUndoableAction {

    public final IItemStack stack;
    public final MCFood food;
    public final MCFood oldFood;

    public ActionChangeFood(IItemStack stack, MCFood food) {
        this.stack = stack;
        this.oldFood = new MCFood(stack.getInternal().getItem().getFood());
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

    @Override
    public void undo() {
        FoodTweaker.changeFood(stack.getInternal().getItem(), oldFood.getInternal());
    }

    @Override
    public String describeUndo() {
        return String.format("Undid food changes for " + stack.getDisplayName());
    }
}
