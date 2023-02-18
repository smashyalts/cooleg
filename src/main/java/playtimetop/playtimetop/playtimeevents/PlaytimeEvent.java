package playtimetop.playtimetop.playtimeevents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

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
