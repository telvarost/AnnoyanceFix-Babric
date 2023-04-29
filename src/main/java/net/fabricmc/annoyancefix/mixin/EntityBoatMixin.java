package net.fabricmc.annoyancefix.mixin;

import java.util.List;
import net.minecraft.class_113;
import net.minecraft.Item;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = class_113.class)
public abstract class EntityBoatMixin extends Entity {
    @Shadow public int field_338 = 0;
    @Shadow public int field_339 = 0;
    @Shadow public int field_340 = 1;
    @Shadow private int field_341;
    @Shadow private double field_342;
    @Shadow private double field_343;
    @Shadow private double field_344;
    @Shadow private double field_345;
    @Shadow private double field_346;

    public EntityBoatMixin(World world) {
        super(world);
    }

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void anf_damage(Entity arg, int i, CallbackInfoReturnable<Boolean> cir) {
        if (this.world.isRemote || this.dead) {
            cir.setReturnValue(true);
        }
        else
        {
            this.field_340 = -this.field_340;
            this.field_339 = 10;
            this.field_338 += i * 10;
            this.method_1336();
            if (this.field_338 > 40) {
                int n;
                if (this.field_1594 != null) {
                    this.field_1594.method_1376(this);
                }
                this.method_1325(Item.field_405.id, 1, 0.0f);
                this.markDead();
            }
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    public void anf_tick(CallbackInfo ci) {
        double d;
        double d2;
        double d3;
        super.tick();
        if (this.field_339 > 0) {
            --this.field_339;
        }
        if (this.field_338 > 0) {
            --this.field_338;
        }
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        int n = 5;
        double d4 = 0.0;
        for (int i = 0; i < n; ++i) {
            double d5 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 0) / (double)n - 0.125;
            double d6 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 1) / (double)n - 0.125;
            Box box = Box.getOrCreate(this.boundingBox.minX, d5, this.boundingBox.minZ, this.boundingBox.maxX, d6, this.boundingBox.maxZ);
            if (!this.world.method_207(box, Material.WATER)) continue;
            d4 += 1.0 / (double)n;
        }
        if (this.world.isRemote) {
            if (this.field_341 > 0) {
                double d7;
                double d8 = this.x + (this.field_342 - this.x) / (double)this.field_341;
                double d9 = this.y + (this.field_343 - this.y) / (double)this.field_341;
                double d10 = this.z + (this.field_344 - this.z) / (double)this.field_341;
                for (d7 = this.field_345 - (double)this.yaw; d7 < -180.0; d7 += 360.0) {
                }
                while (d7 >= 180.0) {
                    d7 -= 360.0;
                }
                this.yaw = (float)((double)this.yaw + d7 / (double)this.field_341);
                this.pitch = (float)((double)this.pitch + (this.field_346 - (double)this.pitch) / (double)this.field_341);
                --this.field_341;
                this.method_1340(d8, d9, d10);
                this.method_1342(this.yaw, this.pitch);
            } else {
                double d11 = this.x + this.velocityX;
                double d12 = this.y + this.velocityY;
                double d13 = this.z + this.velocityZ;
                this.method_1340(d11, d12, d13);
                if (this.field_1623) {
                    this.velocityX *= 0.5;
                    this.velocityY *= 0.5;
                    this.velocityZ *= 0.5;
                }
                this.velocityX *= (double)0.99f;
                this.velocityY *= (double)0.95f;
                this.velocityZ *= (double)0.99f;
            }
            return;
        }
        if (d4 < 1.0) {
            double d14 = d4 * 2.0 - 1.0;
            this.velocityY += (double)0.04f * d14;
        } else {
            if (this.velocityY < 0.0) {
                this.velocityY /= 2.0;
            }
            this.velocityY += (double)0.007f;
        }
        if (this.field_1594 != null) {
            this.velocityX += this.field_1594.velocityX * 0.2;
            this.velocityZ += this.field_1594.velocityZ * 0.2;
        }
        if (this.velocityX < -(d3 = 0.4)) {
            this.velocityX = -d3;
        }
        if (this.velocityX > d3) {
            this.velocityX = d3;
        }
        if (this.velocityZ < -d3) {
            this.velocityZ = -d3;
        }
        if (this.velocityZ > d3) {
            this.velocityZ = d3;
        }
        if (this.field_1623) {
            this.velocityX *= 0.5;
            this.velocityY *= 0.5;
            this.velocityZ *= 0.5;
        }
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        double d15 = Math.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
        if (d15 > 0.15) {
            double d16 = Math.cos((double)this.yaw * Math.PI / 180.0);
            d2 = Math.sin((double)this.yaw * Math.PI / 180.0);
            int n2 = 0;
            while ((double)n2 < 1.0 + d15 * 60.0) {
                double d17;
                double d18;
                double d19 = this.random.nextFloat() * 2.0f - 1.0f;
                double d20 = (double)(this.random.nextInt(2) * 2 - 1) * 0.7;
                if (this.random.nextBoolean()) {
                    d18 = this.x - d16 * d19 * 0.8 + d2 * d20;
                    d17 = this.z - d2 * d19 * 0.8 - d16 * d20;
                    this.world.addParticle("splash", d18, this.y - 0.125, d17, this.velocityX, this.velocityY, this.velocityZ);
                } else {
                    d18 = this.x + d16 + d2 * d19 * 0.7;
                    d17 = this.z + d2 - d16 * d19 * 0.7;
                    this.world.addParticle("splash", d18, this.y - 0.125, d17, this.velocityX, this.velocityY, this.velocityZ);
                }
                ++n2;
            }
        }
        // if (this.field_1624 && d15 > 0.15) {
        //     if (!this.world.isRemote) {
        //         int n3;
        //         this.markDead();
        //         for (n3 = 0; n3 < 3; ++n3) {
        //             this.method_1325(Block.PLANKS.id, 1, 0.0f);
        //         }
        //         for (n3 = 0; n3 < 2; ++n3) {
        //             this.method_1325(Item.field_377.id, 1, 0.0f);
        //         }
        //     }
        // } else {
            this.velocityX *= (double)0.99f;
            this.velocityY *= (double)0.95f;
            this.velocityZ *= (double)0.99f;
        // }
        this.pitch = 0.0f;
        double d21 = this.yaw;
        d2 = this.prevX - this.x;
        double d22 = this.prevZ - this.z;
        if (d2 * d2 + d22 * d22 > 0.001) {
            d21 = (float)(Math.atan2(d22, d2) * 180.0 / Math.PI);
        }
        for (d = d21 - (double)this.yaw; d >= 180.0; d -= 360.0) {
        }
        while (d < -180.0) {
            d += 360.0;
        }
        if (d > 20.0) {
            d = 20.0;
        }
        if (d < -20.0) {
            d = -20.0;
        }
        this.yaw = (float)((double)this.yaw + d);
        this.method_1342(this.yaw, this.pitch);
        List list = this.world.getEntities(this, this.boundingBox.expand(0.2f, 0.0, 0.2f));
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity)list.get(i);
                if (entity == this.field_1594 || !entity.method_1380() || !(entity instanceof class_113)) continue;
                entity.method_1353(this);
            }
        }
        for (int i = 0; i < 4; ++i) {
            int n4;
            int n5;
            int n6 = MathHelper.floot(this.x + ((double)(i % 2) - 0.5) * 0.8);
            if (this.world.getBlockId(n6, n5 = MathHelper.floot(this.y), n4 = MathHelper.floot(this.z + ((double)(i / 2) - 0.5) * 0.8)) != Block.field_1867.id) continue;
            this.world.method_229(n6, n5, n4, 0);
        }
        if (this.field_1594 != null && this.field_1594.dead) {
            this.field_1594 = null;
        }
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "method_1323", cancellable = true)
    public void anf_method_1323(PlayerEntity arg, CallbackInfoReturnable<Boolean> cir) {
        if (this.field_1594 != null && this.field_1594 instanceof PlayerEntity && this.field_1594 != arg) {
            cir.setReturnValue(true);
        }
        else
        {
            if (!this.world.isRemote) {
                arg.method_1376(this);
                // If player is not riding anything after interacting with the boat, it must have unmounted
                if(arg.field_1595 == null) {
                    // Compensate for floating point errors
                    arg.method_1340(arg.x, arg.y+0.01f, arg.z);
                }
            }
            cir.setReturnValue(true);
        }
    }
}

