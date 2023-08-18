package net.pulga22.dontbenaked.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.pulga22.dontbenaked.ISuitAccessor;
import net.pulga22.dontbenaked.Util;

public class RequestSyncC2SPacket {
    public static void onServer(MinecraftServer server, ServerPlayerEntity serverPlayer,
                                ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf buf, PacketSender packetSender) {

        boolean active = ((ISuitAccessor) serverPlayer).dontbenaked$isSuitActive();
        server.execute(() -> {
            ServerPlayNetworking.send(serverPlayer, Packets.CHANGE_SUIT_ACTIVE, Util.newBuf(active));
        });

    }
}
