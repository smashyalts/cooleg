package playtimetop.playtimetop;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import playtimetop.playtimetop.PlayCommands.PlaytimeTopCommand;
import playtimetop.playtimetop.Playtimeevents.PlaytimeEvent;
import playtimetop.playtimetop.Playtimeevents.TimeInterval;
import playtimetop.playtimetop.utils.EnumUtil;

import java.util.*;

import static java.util.stream.Collectors.toMap;
import static playtimetop.playtimetop.PlaytimeTop.HashSmth.playtimemap;
import static playtimetop.playtimetop.PlaytimeTop.HashSmth.sortByValue;


public class PlaytimeTop extends JavaPlugin {
public static class HashSmth {
    public static LinkedHashMap<UUID, Long> playtimemap = new LinkedHashMap<>();
    public static final Statistic PLAY_ONE_TICK = EnumUtil.getStatistic("PLAY_ONE_MINUTE", "PLAY_ONE_TICK");
    public static LinkedHashMap<UUID, Long> sortByValue(HashMap<UUID, Long> playtimemap) {
        // Sort the list
        LinkedHashMap<UUID, Long> sorted = playtimemap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        return sorted;
    }
}
    public HashMap<UUID, Long> timesorted = sortByValue(playtimemap);
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(new PlaytimeEvent(), this);
        Bukkit.getScheduler().runTaskTimer(this, new TimeInterval(), 200L, 6000L);
        getCommand("playtimetop").setExecutor(new PlaytimeTopCommand());
        saveDefaultConfig();
        loadTopPlayers();
    }

    @Override
    public void onDisable() {
        int count = 0;
        for (Map.Entry<UUID, Long> en : timesorted.entrySet()) {
            getConfig().set(String.valueOf(en.getKey()), en.getValue().toString());
            count++;
            if (count >= 10) return;
        // Plugin shutdown logic
        }
    }

    private void loadTopPlayers() {
        ConfigurationSection section = getConfig().getConfigurationSection("playtimetop");
        for (String s : section.getKeys(false)) {
            playtimemap.put(UUID.fromString(s), section.getLong(s));
        }
        playtimemap = sortByValue(playtimemap);
    }
}
