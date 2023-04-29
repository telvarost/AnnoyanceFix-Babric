package net.fabricmc.annoyancefix.mixin;

import net.minecraft.block.Block;
import net.minecraft.class_420;
import net.minecraft.class_428;
import net.minecraft.class_632;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(class_420.class)
public abstract class ItemAxeMixin extends class_632
{
    @Shadow private static Block[] field_1681 = new Block[]
            { Block.PLANKS
            , Block.field_1888 // bookShelf
            , Block.LOG
            , Block.field_1895 // chest
            , Block.WORKBENCH
            , Block.NOTEBLOCK
            , Block.field_1853 // wooden door
            , Block.field_1854 // ladder
            , Block.STANDING_SIGN
            , Block.field_1857 // wall sign
            , Block.field_1861 // wooden pressure plate
            , Block.field_1873 // jukebox
            , Block.field_1894 // wooden stairs
            , Block.field_1902 // fence
            , Block.field_1903 // pumpkin
            , Block.field_1908 // lit pumpkin
            , Block.field_1913 // trapdoor
            };

    protected ItemAxeMixin(int i, class_428 arg) {
        super(i, 3, arg, field_1681);
    }
}