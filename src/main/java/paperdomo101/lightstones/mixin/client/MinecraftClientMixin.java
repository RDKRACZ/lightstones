package paperdomo101.lightstones.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import paperdomo101.lightstones.entity.vehicle.LavaSurfboardEntity;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow
    public HitResult crosshairTarget;

    @Shadow
    public ClientPlayerEntity player;

    @Shadow
    public ClientPlayerInteractionManager interactionManager;

    @Inject(at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/util/hit/EntityHitResult;getEntity()Lnet/minecraft/entity/Entity;", ordinal = 0), method = "doItemPick", cancellable = true)
    private void pickLavaSurfboardItem(CallbackInfo ci) {
        Entity entity = ((EntityHitResult) this.crosshairTarget).getEntity();

        if (entity instanceof LavaSurfboardEntity) {
            ItemStack pickedLavaSurfboard = new ItemStack(((LavaSurfboardEntity) entity).asItem());
            PlayerInventory playerInventory = this.player.inventory;

            int i = playerInventory.getSlotWithStack(pickedLavaSurfboard);
            if (this.player.abilities.creativeMode) {
                playerInventory.addPickBlock(pickedLavaSurfboard);
                this.interactionManager.clickCreativeStack(this.player.getStackInHand(Hand.MAIN_HAND), 36 + playerInventory.selectedSlot);
            } else if (i != -1) {
                if (PlayerInventory.isValidHotbarIndex(i)) {
                    playerInventory.selectedSlot = i;
                } else {
                    this.interactionManager.pickFromInventory(i);
                }
            }
            ci.cancel();
        }
    }
}    
