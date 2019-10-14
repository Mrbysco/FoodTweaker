package com.mrbysco.foodtweaker;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(Reference.MOD_ID)
public class FoodTweaker {
    public static final Logger LOGGER = LogManager.getLogger();

    public FoodTweaker() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent event) {
        if (event.getItemStack().isFood()) {
            List<ITextComponent> toolTip = event.getToolTip();
            Food food = event.getItemStack().getItem().getFood();
            toolTip.add(new StringTextComponent("Food stats:").applyTextStyle(TextFormatting.GREEN));
            toolTip.add(new StringTextComponent("Health: ").applyTextStyle(TextFormatting.GOLD)
                    .appendSibling(new StringTextComponent(String.valueOf(food.getHealing())).applyTextStyle(TextFormatting.YELLOW)));
            toolTip.add(new StringTextComponent("Saturation: ").applyTextStyle(TextFormatting.GOLD)
                    .appendSibling(new StringTextComponent(String.valueOf(food.getSaturation())).applyTextStyle(TextFormatting.YELLOW)));
            toolTip.add(new StringTextComponent("Always edible: ").applyTextStyle(TextFormatting.DARK_RED)
                    .appendSibling(new StringTextComponent(String.valueOf(food.canEatWhenFull())).applyTextStyle(TextFormatting.RED)));
            toolTip.add(new StringTextComponent("Is dog food: ").applyTextStyle(TextFormatting.DARK_AQUA)
                    .appendSibling(new StringTextComponent(String.valueOf(food.isMeat())).applyTextStyle(TextFormatting.AQUA)));

            List<Pair<EffectInstance, Float>> effects = food.getEffects();
            StringTextComponent effectNames = new StringTextComponent("");
            if (!food.getEffects().isEmpty()) {
                effectNames.applyTextStyle(TextFormatting.LIGHT_PURPLE);
                for (int i = 0; i < effects.size(); i++) {
                    EffectInstance effect = effects.get(i).getKey();
                    effectNames.appendSibling(new TranslationTextComponent(effect.getEffectName())
                            .appendSibling(new StringTextComponent(" " + RomanUtil.intToRoman(effect.getAmplifier() + 1)))
                            .appendSibling(new StringTextComponent(" with a " + (int) ((Math.round(effects.get(i).getValue() * 10) / 10.0) * 100) + "% chance for " + (effect.getDuration() / 20) + " second(s)")));
                    if (i < effects.size() - 1) {
                        effectNames.appendSibling(new StringTextComponent(", \n"));
                    }
                }
                toolTip.add(new StringTextComponent("Effects: \n").applyTextStyle(TextFormatting.DARK_PURPLE).appendSibling(effectNames));
            }
        }
    }

    public static void changeFood(Item item, Food food) {
        LOGGER.info("Changing food value of " + item.getName());
        ObfuscationReflectionHelper.setPrivateValue(Item.class, item, food, "field_219974_q"); //"food"
    }

    public static void removeFood(Item item) {
        LOGGER.info("Remove food values from " + item.getName());
        ObfuscationReflectionHelper.setPrivateValue(Item.class, item, null, "field_219974_q"); //"food"
    }
}
