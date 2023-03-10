package playtimetop.playtimetop;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import playtimetop.playtimetop.playcommands.PlaytimeTopCommand;
import playtimetop.playtimetop.playtimeevents.PlaytimeEvent;
import playtimetop.playtimetop.playtimeevents.TimeInterval;
import playtimetop.playtimetop.utils.EnumUtil;

import java.util.*;

import static java.util.stream.Collectors.toMap;
import static playtimetop.playtimetop.PlaytimeTop.HashSmth.playtimemap;
import static playtimetop.playtimetop.PlaytimeTop.HashSmth.sortByValue;
import static playtimetop.playtimetop.PlaytimeTop.timesort.timesorted;


public final class PlaytimeTop extends JavaPlugin {
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
    public static class timesort {
        public static HashMap<UUID, Long> timesorted = sortByValue(playtimemap);
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(new PlaytimeEvent(), this);
        Bukkit.getScheduler().runTaskTimer(this, new TimeInterval(), 200L, 6000L);
        getCommand("playtimetop").setExecutor(new PlaytimeTopCommand());
        saveDefaultConfig();
        loadTopPlayers();
        Bukkit.getLogger().info(playtimemap.size() + " test");
    }
    int count = 0;

    @Override
    public void onDisable() {
       if (count <= 10) {
           for (Map.Entry<UUID, Long> en : timesorted.entrySet()) {
            getConfig().set(en.getKey().toString(), en.getValue().toString());
            count++;
            saveConfig();
        // Plugin shutdown logic
        }
    }
    }

    private void loadTopPlayers() {
        ConfigurationSection section = getConfig().getConfigurationSection("playtimetop");
        if (section == null) {return;}
        for (String s : section.getKeys(false)) {
            playtimemap.put(UUID.fromString(s), section.getLong(s));
        }
        playtimemap = sortByValue(playtimemap);
    }
}
