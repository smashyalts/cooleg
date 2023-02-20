package playtimetop.playtimetop.playcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import playtimetop.playtimetop.PlaytimeTop;

import java.util.*;

public class PlaytimeTopCommand implements CommandExecutor {
    int count = 0;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for (Map.Entry<UUID, Long> en : PlaytimeTop.timesort.timesorted.entrySet()) {
            long time = (en.getValue()/20);
            long timeminutes = (time / 60);
            long timehours = (timeminutes / 60);
            long timedays = (timehours / 24);

            // This subtracts the time from earlier time measurement so that it doesn't show additional time.
            timeminutes-=timehours*60;
            timehours-=timedays*24;


            sender.sendMessage(Bukkit.getOfflinePlayer(en.getKey()).getName() + " = " + ChatColor.WHITE + timedays + ChatColor.RED + " Days " + ChatColor.WHITE + timehours + ChatColor.RED + " Hours "  + ChatColor.WHITE + timeminutes + ChatColor.RED + " Minutes ");
            count++;
            if (count <= 10) {
                return true;
            }
        }
        return true;
    }
}