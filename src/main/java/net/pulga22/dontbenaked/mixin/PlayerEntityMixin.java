package net.pulga22.dontbenaked.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.pulga22.dontbenaked.ISuitAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements ISuitAccessor {

    @Unique private boolean suitActive = false;

    @Inject(at = @At("TAIL"), method = "writeCustomDataToNbt")
    public void writeNbt(NbtCompound nbt, CallbackInfo ci){
        nbt.putBoolean("suitActive", this.suitActive);
    }

    @Inject(at = @At("TAIL"), method = "readCustomDataFromNbt")
    public void readNbt(NbtCompound nbt, CallbackInfo ci){
        this.suitActive = nbt.getBoolean("suitActive");
    }

    @Override
    public void dontbenaked$setSuitActive(boolean active){
        this.suitActive = active;
    }

    @Override
    public boolean dontbenaked$isSuitActive(){
        return this.suitActive;
    }

}
