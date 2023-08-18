package net.pulga22.dontbenaked.packets;

import net.minecraft.util.Identifier;
import net.pulga22.dontbenaked.DontBeNaked;

public class Packets {

    public static final Identifier CHANGE_SUIT_ACTIVE = registerPacket("change_suit_active");
    public static final Identifier REQUEST_SYNC = registerPacket("request_sync");

    protected static Identifier registerPacket(String id){
        return new Identifier(DontBeNaked.MOD_ID, id);
    }

}
