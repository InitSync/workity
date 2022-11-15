package me.bryang.workity.commands.subcommands;

import me.bryang.workity.commands.JobsCommand;
import me.bryang.workity.data.PlayerData;
import me.bryang.workity.data.PlayerJobData;
import me.bryang.workity.loader.DataLoader;
import me.bryang.workity.manager.file.FileDataManager;
import me.bryang.workity.manager.file.FileManager;
import me.bryang.workity.utils.MathLevelsUtils;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AddLevelSubCommand implements CommandClass {

    private final FileManager configFile;
    private final FileManager messagesFile;
    private final FileDataManager playersFile;

    private final DataLoader dataLoader;

    public AddLevelSubCommand(JobsCommand jobsCommand) {
        this.messagesFile = jobsCommand.getPluginCore().getFilesLoader().getMessagesFile();
        this.configFile = jobsCommand.getPluginCore().getFilesLoader().getConfigFile();
        this.playersFile = jobsCommand.getPluginCore().getFilesLoader().getPlayersFile();

        this.dataLoader = jobsCommand.getPluginCore().getDataLoader();
    }


    @Command(
            names = "add-level",
            permission = "jobs.admin")

    public boolean onAddLevelSubCommand(

            @Sender Player sender,
            @OptArg("") String targetArgument,
            @OptArg("") String jobArgument,
            @OptArg("-1") int levelArgument) {

        if (targetArgument.isEmpty()) {

            sender.sendMessage(messagesFile.getString("error.no-argument")
                    .replace("%usage%", "/jobs add-level [player] [job] [level]"));
            return true;

        }

        Player target = Bukkit.getPlayer(targetArgument);

        if (target == null) {
            sender.sendMessage(messagesFile.getString("error.no-online"));
            return true;

        }

        if (jobArgument.isEmpty()) {

            sender.sendMessage(messagesFile.getString("error.no-argument")
                    .replace("%usage%", "/jobs add-level [player] [level]"));
            return true;

        }

        PlayerData playerData = dataLoader.getPlayerJob(target.getUniqueId());

        if (!playersFile.isConfigurationSection("jobs." + jobArgument)) {
            sender.sendMessage(messagesFile.getString("error.unknown-job")
                    .replace("%job%", jobArgument));
            return true;
        }

        if (levelArgument == -1) {

            sender.sendMessage(messagesFile.getString("error.no-argument")
                    .replace("%usage%", "/jobs add-level [player] [level]"));
            return true;

        }

        if (levelArgument < 0) {
            sender.sendMessage(messagesFile.getString("error.negative-number"));
            return true;
        }

        if (playerData.getJob(jobArgument) == null) {
            playerData.addJob(jobArgument);
        }

        PlayerJobData playerJobData = playerData.getJob(jobArgument);

        playerJobData.setLevel(playerJobData.getLevel() + levelArgument);
        playerJobData.setMaxXP(
                MathLevelsUtils.calculateNumber(configFile.getString("config.formula.max-xp"),
                        playerJobData.getLevel() + levelArgument));

        playersFile.setJobData(sender.getUniqueId(), "job-list." + jobArgument + ".level", playerJobData.getLevel());
        playersFile.setJobData(sender.getUniqueId(), "job-list." + jobArgument + ".xp", 0);
        playersFile.save();

        sender.sendMessage(messagesFile.getString("jobs.add-level.message")
                .replace("%level%", String.valueOf(playerJobData.getLevel() + levelArgument))
                .replace("%job%", jobArgument)
                .replace("%player%", target.getName()));
        return true;
    }
}
