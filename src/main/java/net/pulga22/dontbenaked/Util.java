package net.pulga22.dontbenaked;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;

public class Util {

    public static PacketByteBuf newBuf(boolean active){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(active);
        return buf;
    }

}
