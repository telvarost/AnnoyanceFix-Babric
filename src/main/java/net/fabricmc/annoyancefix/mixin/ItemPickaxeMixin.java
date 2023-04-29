package net.fabricmc.annoyancefix.mixin;

import net.minecraft.block.Block;
import net.minecraft.class_116;
import net.minecraft.class_428;
import net.minecraft.class_632;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(class_116.class)
public abstract class ItemPickaxeMixin extends class_632
{
    @Shadow  static Block[] field_352 = new Block[]
            { Block.COBBLESTONE
            , Block.field_1884 // stair double
            , Block.field_1885 // stair single
            , Block.STONE
            , Block.SANDSTONE
            , Block.field_1889 // cobblestone mossy
            , Block.IRON_ORE
            , Block.field_1883 // block steel
            , Block.COAL_ORE
            , Block.field_1882 // block gold
            , Block.GOLD_ORE
            , Block.field_1897 // ore diamond
            , Block.field_1898 // block diamond
            , Block.field_1868 // ice
            , Block.field_1904 // netherrack
            , Block.LAPIS_ORE
            , Block.LAPIS_BLOCK
            , Block.DISPENSER
            , Block.RAIL
            , Block.POWERED_RAIL
            , Block.DETECTOR_RAIL
            , Block.field_1850 // stone oven idle
            , Block.field_1851 // stone oven active
            , Block.field_1856 // stone stairs
            , Block.field_1859 // stone pressure plate
            , Block.field_1860 // iron door
            , Block.field_1862 // redstone ore
            , Block.field_1863 // redstone ore lit
            , Block.field_1866 // stone button
            , Block.field_1886 // brick
            , Block.field_1893 // mob spawner
            };

    protected ItemPickaxeMixin(int i, class_428 arg) {
        super(i, 2, arg, field_352);
    }
}


