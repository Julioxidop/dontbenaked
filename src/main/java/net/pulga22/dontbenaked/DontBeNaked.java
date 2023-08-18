package net.pulga22.dontbenaked;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.pulga22.dontbenaked.commands.ChangeSuitActiveCommand;
import net.pulga22.dontbenaked.packets.Packets;
import net.pulga22.dontbenaked.packets.RequestSyncC2SPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DontBeNaked implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("dontbenaked");
	public static final String MOD_ID = "dontbenaked";

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register(ChangeSuitActiveCommand::register);
		ServerPlayNetworking.registerGlobalReceiver(Packets.REQUEST_SYNC, RequestSyncC2SPacket::onServer);
		ServerPlayerEvents.AFTER_RESPAWN.register(((oldPlayer, newPlayer, alive) -> {
			boolean active = ((ISuitAccessor) oldPlayer).dontbenaked$isSuitActive();
			((ISuitAccessor) newPlayer).dontbenaked$setSuitActive(active);
			ServerPlayNetworking.send(newPlayer, Packets.CHANGE_SUIT_ACTIVE, Util.newBuf(active));
		}));
		LOGGER.info("Hello Fabric world!");
	}
}