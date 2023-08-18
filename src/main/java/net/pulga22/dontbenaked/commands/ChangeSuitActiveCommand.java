package net.pulga22.dontbenaked.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.pulga22.dontbenaked.ISuitAccessor;
import net.pulga22.dontbenaked.Util;
import net.pulga22.dontbenaked.packets.Packets;

import java.util.Collection;

public class ChangeSuitActiveCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(
                CommandManager.literal("overlay").requires(source -> source.hasPermissionLevel(2))
                        .then( CommandManager.argument("targets", EntityArgumentType.players())
                                .then(
                                        CommandManager.literal("set").executes(ctx ->
                                            changeActive(ctx.getSource(), EntityArgumentType.getPlayers(ctx, "targets"), true)
                                        )
                                )
                                .then(
                                        CommandManager.literal("unset").executes(ctx ->
                                                changeActive(ctx.getSource(), EntityArgumentType.getPlayers(ctx, "targets"), false)
                                        )
                                )
                        )
        );
    }

    private static int changeActive(ServerCommandSource source, Collection<ServerPlayerEntity> targets, boolean active){
        PacketByteBuf buf = Util.newBuf(active);
        targets.forEach(target -> {
            ((ISuitAccessor) target).dontbenaked$setSuitActive(active);
            ServerPlayNetworking.send(target, Packets.CHANGE_SUIT_ACTIVE, buf);
        });
        source.sendFeedback(() -> Text.of("Se ha actualizado a " + targets.size() + " jugadores."), true);
        return 1;
    }


}
