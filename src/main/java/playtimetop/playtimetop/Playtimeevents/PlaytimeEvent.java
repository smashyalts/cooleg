package playtimetop.playtimetop.Playtimeevents;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.yaml.snakeyaml.util.EnumUtils;
import playtimetop.playtimetop.utils.EnumUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static playtimetop.playtimetop.PlaytimeTop.HashSmth.PLAY_ONE_TICK;
import static playtimetop.playtimetop.PlaytimeTop.HashSmth.playtimemap;

public class PlaytimeEvent implements Listener {

    @EventHandler
    public void onLeave (PlayerQuitEvent e) {
        long playtime;
        playtime = e.getPlayer().getStatistic(PLAY_ONE_TICK);
        playtimemap.put(e.getPlayer().getUniqueId(), playtime);
    }
}
