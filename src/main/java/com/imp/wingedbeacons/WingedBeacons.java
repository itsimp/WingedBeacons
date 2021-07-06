package com.imp.wingedbeacons;

import com.imp.wingedbeacons.effect.FlightEffect;
import com.imp.wingedbeacons.event.AdvancementEventHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WingedBeacons implements ModInitializer {

	public static final String MODID = "wingedbeacons";

	public static final FlightEffect FLIGHT_EFFECT = new FlightEffect();

	public static final Identifier ADVANCEMENT_C2S_PACKET = new Identifier(MODID, "advancement_c2s_packet");
	public static final Identifier ADVANCEMENT_S2C_PACKET = new Identifier(MODID, "advancement_s2c_packet");

	@Override
	public void onInitialize() {
		/* Load Config */
		WingedBeaconsConfig.loadConfig();

		/* Register Status Effects */
		Registry.register(Registry.STATUS_EFFECT, new Identifier(MODID, "flight"), FLIGHT_EFFECT);

		/* Networking */
		ServerPlayNetworking.registerGlobalReceiver(ADVANCEMENT_C2S_PACKET, ((server, player, handler, buf, responseSender) -> {
			AdvancementProgress prog = player.getAdvancementTracker().getProgress(server.getAdvancementLoader().get(new Identifier(WingedBeaconsConfig.INSTANCE.advancementRequired)));
			PacketByteBuf buff = PacketByteBufs.create();
			if (prog.isDone()) {
				buff.writeBoolean(true);
			} else {
				buff.writeBoolean(false);
			}
			ServerPlayNetworking.send(player, ADVANCEMENT_S2C_PACKET, buff);
		}));
	}
}
