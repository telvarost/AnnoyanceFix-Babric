package net.fabricmc.annoyancefix.mixin;


import java.util.ArrayList;
import java.util.Random;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Material;
import net.minecraft.class_416;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = class_416.class)
public abstract class BlockStairsMixin extends Block {
    @Shadow private Block field_1672;

    protected BlockStairsMixin(int i, Material arg) {
        super(i, arg);
    }

    protected BlockStairsMixin(int i, int j, Material arg) {
        super(i, j, arg);
    }

    @Inject(at = @At("HEAD"), method = "method_1612", cancellable = true)
    public void anf_method_1612(World arg, int i, int j, int k, int l, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "method_1601", cancellable = true)
    public void anf_method_1601(int i, Random random, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.id);
    }

    @Inject(at = @At("HEAD"), method = "method_1630", cancellable = true)
    public void anf_method_1630(World arg, int i, int j, int k, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "method_1625", cancellable = true)
    public void anf_method_1625(World arg, int i, int j, int k, int l, float f, CallbackInfo ci) {
        if (arg.isRemote) {
            ci.cancel();
        }
        int n = this.method_1603(arg.field_214);
        for (int i2 = 0; i2 < n; ++i2) {
            int n2;
            if (arg.field_214.nextFloat() > f || (n2 = this.method_1601(l, arg.field_214)) <= 0) continue;
            this.method_1581(arg, i, j, k, new ItemStack(n2, 1, this.method_1629(l)));
        }
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "method_1613", cancellable = true)
    public void anf_method_1613(World arg, int i, int j, int k, CallbackInfo ci) {
        ci.cancel();
    }
}

