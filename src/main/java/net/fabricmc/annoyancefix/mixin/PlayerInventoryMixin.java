package net.fabricmc.annoyancefix.mixin;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Random;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @Shadow public ItemStack[] main = new ItemStack[36];
    @Shadow public int selectedSlot = 0;

    @Inject(at = @At("HEAD"), method = "method_691", cancellable = true)
    public void anf_setSelectedItem(int item_id, boolean var2, CallbackInfo ci) {
        // int currentSlot = this.getInventorySlotContainItem(item_id);
        int currentSlot = ((PlayerInventoryInvoker)this).invokeGetInventorySlotContainItem(item_id);
        if(currentSlot >= 0 && currentSlot < 9) {
            this.selectedSlot = currentSlot;
        }
//        int n = this.method_682(i);
//        if (n >= 0 && n < 9) {
//            this.selectedSlot = n;
//            return;
//        }
    }
}
