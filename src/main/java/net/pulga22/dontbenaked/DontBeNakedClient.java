package net.pulga22.dontbenaked;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.pulga22.dontbenaked.packets.ChangeSuitActiveS2CPacket;
import net.pulga22.dontbenaked.packets.Packets;

public class DontBeNakedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(Packets.CHANGE_SUIT_ACTIVE, ChangeSuitActiveS2CPacket::onClient);
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client)->{
            if (client.player != null){
                ClientPlayNetworking.send(Packets.REQUEST_SYNC, PacketByteBufs.empty());
            }
        });
    }

}
