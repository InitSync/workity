package me.bryang.workity.manager.jobs;

import me.bryang.workity.data.PlayerJobData;
import org.bukkit.entity.Player;

public interface WorkAction {

    void doWorkAction(Player player, String jobName, String itemName, PlayerJobData playerJobData);
}
