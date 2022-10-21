package me.bryangaming.labority.loader;

import me.bryangaming.labority.api.Loader;
import me.bryangaming.labority.data.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataLoader implements Loader {

    private final Map<UUID, PlayerData> playerJobDataMap = new HashMap<>();


    @Override
    public void load() {

    }

    public void createPlayerJob(UUID uuid){
        playerJobDataMap.put(uuid, new PlayerData());
    }


    public PlayerData getPlayerJob(UUID uuid){
        return playerJobDataMap.get(uuid);
    }


}
