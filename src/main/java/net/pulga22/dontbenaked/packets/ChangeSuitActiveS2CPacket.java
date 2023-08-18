package net.pulga22.dontbenaked.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.pulga22.dontbenaked.ISuitAccessor;

public class ChangeSuitActiveS2CPacket {
    public static void onClient(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler,
                                PacketByteBuf buf, PacketSender packetSender) {

        boolean active = buf.readBoolean();
        minecraftClient.execute(() -> {
            ISuitAccessor player = (ISuitAccessor) minecraftClient.player;
            if (player != null){
                player.dontbenaked$setSuitActive(active);
            }
        });

    }
}
