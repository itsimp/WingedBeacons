package com.imp.wingedbeacons.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.sql.Time;
import java.util.*;

@Environment(EnvType.CLIENT)
public class AdvancementEventHandler {

    public final Set<UUID> playerList = new HashSet<>();
    public final HashMap<UUID, Timer> timerMap = new HashMap<>();

}
