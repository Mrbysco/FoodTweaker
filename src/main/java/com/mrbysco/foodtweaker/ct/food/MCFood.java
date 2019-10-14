package com.mrbysco.foodtweaker.ct.food;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.impl.potion.MCEffectInstance;
import com.google.common.collect.Lists;
import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.foodtweaker.MCFood")
public class MCFood {
    private final Food internal;

    public MCFood(Food food) {
        this.internal = food;
    }

    @ZenCodeType.Constructor
    public MCFood(int healing, float saturation) {
        this(new Food.Builder().hunger(healing).saturation(saturation).build());
    }

    @ZenCodeType.Method
    public MCFood meat() {
        Food newInternal = this.getInternal();
        changeValue(newInternal, "field_221472_c", true); //"meat"
        return new MCFood(newInternal);
    }

    @ZenCodeType.Method
    public MCFood setAlwaysEdible() {
        Food newInternal = this.getInternal();
        changeValue(newInternal, "field_221473_d", true); //"canEatWhenFull"
        return new MCFood(newInternal);
    }

    @ZenCodeType.Method
    public MCFood fastToEat() {
        Food newInternal = this.getInternal();
        changeValue(newInternal, "field_221474_e", true); //"fastToEat"
        return new MCFood(newInternal);
    }

    @ZenCodeType.Method
    public MCFood setEffect(MCEffectInstance effect, float probability) {
        Food newInternal = this.getInternal();
        List<Pair<EffectInstance, Float>> effectList = Lists.newArrayList();
        effectList.add(Pair.of(effect.getInternal(), probability));
        changeValue(newInternal, "field_221475_f", effectList); //"effects"
        return new MCFood(newInternal);
    }

    @ZenCodeType.Method
    public MCFood setEffects(MCEffectInstance[] effects, float[] probability) {
        Food newInternal = this.getInternal();

        List<Pair<EffectInstance, Float>> effectList = Lists.newArrayList();
        if(effects.length == probability.length) {
            for(int i = 0; i < effects.length; i++) {
                effectList.add(Pair.of(effects[i].getInternal(), probability[i]));
            }
        }
        changeValue(newInternal, "field_221475_f", effectList); //"effects"
        return new MCFood(newInternal);
    }

    @ZenCodeType.Getter("saturation")
    public Float getSaturation() {
        return this.internal.getSaturation();
    }

    @ZenCodeType.Getter("healing")
    public Float getHealing() {
        return this.internal.getSaturation();
    }

    @ZenCodeType.Getter("alwaysEdible")
    public boolean isAlwaysEdible() {
        return this.internal.canEatWhenFull();
    }

    @ZenCodeType.Getter("meat")
    public boolean isMeat() {
        return this.internal.isMeat();
    }

    @ZenCodeType.Getter("fastEating")
    public boolean isFastEating() {
        return this.internal.canEatWhenFull();
    }

    @ZenCodeType.Getter("effects")
    public List<Pair<EffectInstance, Float>> getEffects() {
        return this.internal.getEffects();
    }

    private static <T> void changeValue(Food food, String name, T value) {
        ObfuscationReflectionHelper.setPrivateValue(Food.class, food, value, name);
    }

    public Food getInternal() {
        return this.internal;
    }
}
