package playtimetop.playtimetop.Playtimeevents;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import playtimetop.playtimetop.PlaytimeTop;

import static playtimetop.playtimetop.PlaytimeTop.*;
import java.util.*;

import static playtimetop.playtimetop.PlaytimeTop.HashSmth.*;

public class TimeInterval extends PlaytimeTop implements Runnable {
    int count = 0;
    long playtime;
    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            playtime = player.getStatistic(PLAY_ONE_TICK);
            playtimemap.put(player.getUniqueId(), playtime);
            }
        }
    }
