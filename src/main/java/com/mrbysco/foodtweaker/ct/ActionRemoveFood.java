package com.mrbysco.foodtweaker.ct;

import com.blamejared.crafttweaker.api.actions.IAction;
import com.blamejared.crafttweaker.api.actions.IRuntimeAction;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.mrbysco.foodtweaker.FoodTweaker;

public class ActionRemoveFood implements IRuntimeAction {

    public final IItemStack[] stacks;

    public ActionRemoveFood(IItemStack stack) {
        this.stacks = new IItemStack[]{stack};;
    }

    public ActionRemoveFood(IItemStack[] stacks) {
        this.stacks = stacks;
    }

    @Override
    public void apply() {
        for(int i = 0; i < stacks.length; i++) {
            IItemStack stack = this.stacks[i];
            if(stack.getInternal().isFood()) {
                FoodTweaker.removeFood(stack.getInternal().getItem());
            }
        }
    }

    @Override
    public String describe() {
        String description = "";
        for(int i = 0; i < stacks.length; i++) {
            IItemStack stack = this.stacks[i];
            if(!stack.getInternal().isFood()) {
                description = description + String.format("Tried to make " + stack.getDisplayName() + "inedible but it was already inedible");
            } else {
                description = description + String.format(stack.getDisplayName() + " has been made inedible");
            }
        }

        return description;
    }
}
