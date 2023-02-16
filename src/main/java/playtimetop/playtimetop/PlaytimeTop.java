package playtimetop.playtimetop;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.plugin.java.JavaPlugin;
import playtimetop.playtimetop.PlayCommands.PlaytimeTopCommand;
import playtimetop.playtimetop.Playtimeevents.PlaytimeEvent;
import playtimetop.playtimetop.Playtimeevents.TimeInterval;
import playtimetop.playtimetop.utils.EnumUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

import static java.util.stream.Collectors.toMap;
import static playtimetop.playtimetop.PlaytimeTop.HashSmth.playtimemap;
import static playtimetop.playtimetop.PlaytimeTop.HashSmth.sortByValue;


public class PlaytimeTop extends JavaPlugin {
public static class HashSmth {
    public static HashMap<UUID, Long> playtimemap = new HashMap<>();
    public static final Statistic PLAY_ONE_TICK = EnumUtil.getStatistic("PLAY_ONE_MINUTE", "PLAY_ONE_TICK");
    public static HashMap<UUID, Long> sortByValue(HashMap<UUID, Long> playtimemap) {
        // Create a list from elements of HashMap
        List<Map.Entry<UUID, Long> > list =
                new LinkedList<Map.Entry<UUID, Long> >(playtimemap.entrySet());

        // Sort the list
        Map<UUID, Long> sorted = playtimemap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        // put data from sorted list to hashmap
        HashMap<UUID, Long> temp = new LinkedHashMap<UUID, Long>();
        for (Map.Entry<UUID, Long> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
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
}}
