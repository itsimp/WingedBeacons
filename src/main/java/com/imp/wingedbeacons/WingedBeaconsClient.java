package com.imp.wingedbeacons;

import com.imp.wingedbeacons.event.AdvancementEventHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class WingedBeaconsClient implements ClientModInitializer {

    public static final AdvancementEventHandler ADVANCEMENT_EVENT_HANDLER = new AdvancementEventHandler();

    @Override
    public void onInitializeClient() {
        /* Networking */
        ClientPlayNetworking.registerGlobalReceiver(WingedBeacons.ADVANCEMENT_S2C_PACKET, ((client, handler, buf, responseSender) -> {
            if (buf.readBoolean()) {
                UUID uuid = null;
                if (client.player != null) {
                    uuid = client.player.getUuid();
                    ADVANCEMENT_EVENT_HANDLER.playerList.add(uuid);
                }

                UUID finalUuid = uuid;
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (finalUuid != null)
                            ADVANCEMENT_EVENT_HANDLER.playerList.remove(finalUuid);
                    }
                };

                Timer timer = new Timer("PlayerAdvancementTimer");
                timer.schedule(task, 3500L);

                if (ADVANCEMENT_EVENT_HANDLER.timerMap.containsKey(uuid)) {
                    ADVANCEMENT_EVENT_HANDLER.timerMap.get(uuid).cancel();
                }

                ADVANCEMENT_EVENT_HANDLER.timerMap.put(uuid, timer);
            }
        }));
    }
}
