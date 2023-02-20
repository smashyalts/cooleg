package playtimetop.playtimetop.playtimeevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import playtimetop.playtimetop.PlaytimeTop;

import java.util.Map;
import java.util.UUID;

import static playtimetop.playtimetop.PlaytimeTop.HashSmth.*;
import static playtimetop.playtimetop.PlaytimeTop.timesort.timesorted;

public class TimeInterval implements Runnable {
    int count = 0;
    long playtime;
    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            playtime = player.getStatistic(PLAY_ONE_TICK);
            playtimemap.put(player.getUniqueId(), playtime);
        }
        if (count <= 10) {for (Map.Entry<UUID, Long> en : timesorted.entrySet()) {
            Bukkit.getServer().getPluginManager().getPlugin("PlaytimeTop").getConfig().set(String.valueOf(en.getKey()), en.getValue().toString());
            count++;
            Bukkit.getServer().getPluginManager().getPlugin("PlaytimeTop").saveConfig();
        }
    }
}
}
