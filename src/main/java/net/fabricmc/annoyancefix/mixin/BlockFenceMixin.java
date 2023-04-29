package net.fabricmc.annoyancefix.mixin;

import net.fabricmc.annoyancefix.AnnoyanceFixMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_229;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = class_229.class)
public abstract class BlockFenceMixin extends Block {

    protected BlockFenceMixin(int i, Material arg) {
        super(i, arg);
    }

    protected BlockFenceMixin(int i, int j, Material arg) {
        super(i, j, arg);
    }

    @Inject(at = @At("HEAD"), method = "method_1567", cancellable = true)
    public void anf_method_1567(World arg, int i, int j, int k, CallbackInfoReturnable<Boolean> cir) {
        int n = arg.getBlockId(i, j, k);
        cir.setReturnValue(n == 0 || Block.BLOCKS[n].field_1900.method_896());
    }
}

