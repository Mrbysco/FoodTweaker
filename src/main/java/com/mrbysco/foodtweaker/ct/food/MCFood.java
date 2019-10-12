package com.mrbysco.foodtweaker.ct.food;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.impl.potion.MCEffectInstance;
import com.google.common.collect.Lists;
import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.openzen.zencode.java.ZenCodeType.Constructor;
import org.openzen.zencode.java.ZenCodeType.Getter;
import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Name;

import java.util.List;

@ZenRegister
@Name("mods.foodtweaker.MCFood")
public class MCFood {
    private final Food internal;

    public MCFood(Food food) {
        this.internal = food;
    }

    @Constructor
    public MCFood(int healing, float saturation) {
        this(new Food.Builder().hunger(healing).saturation(saturation).build());
    }

    @Method
    public MCFood meat() {
        Food newInternal = this.getInternal();
        changeValue(newInternal, "food", true);
        return new MCFood(newInternal);
    }

    @Method
    public MCFood setAlwaysEdible() {
        Food newInternal = this.getInternal();
        changeValue(newInternal, "canEatWhenFull", true);
        return new MCFood(newInternal);
    }

    @Method
    public MCFood fastToEat() {
        Food newInternal = this.getInternal();
        changeValue(newInternal, "fastToEat", true);
        return new MCFood(newInternal);
    }

    @Method
    public MCFood setEffect(MCEffectInstance effect, float probability) {
        Food newInternal = this.getInternal();
        List<Pair<EffectInstance, Float>> effectList = Lists.newArrayList();
        effectList.add(Pair.of(effect.getInternal(), probability));
        changeValue(newInternal, "effects", effectList);
        return new MCFood(newInternal);
    }

    @Method
    public MCFood setEffects(MCEffectInstance[] effects, float[] probability) {
        Food newInternal = this.getInternal();

        List<Pair<EffectInstance, Float>> effectList = Lists.newArrayList();
        if(effects.length == probability.length) {
            for(int i = 0; i < effects.length; i++) {
                effectList.add(Pair.of(effects[i].getInternal(), probability[i]));
            }
        }
        changeValue(newInternal, "effects", effectList);
        return new MCFood(newInternal);
    }

    @Getter("saturation")
    public Float getSaturation() {
        return this.internal.getSaturation();
    }

    @Getter("healing")
    public Float getHealing() {
        return this.internal.getSaturation();
    }

    @Getter("alwaysEdible")
    public boolean isAlwaysEdible() {
        return this.internal.canEatWhenFull();
    }

    @Getter("meat")
    public boolean isMeat() {
        return this.internal.isMeat();
    }

    @Getter("fastEating")
    public boolean isFastEating() {
        return this.internal.canEatWhenFull();
    }

    @Getter("effects")
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
