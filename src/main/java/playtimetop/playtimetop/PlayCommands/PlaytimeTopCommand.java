package playtimetop.playtimetop.PlayCommands;

import co.aikar.util.Counter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import playtimetop.playtimetop.PlaytimeTop;

import java.util.*;

import static playtimetop.playtimetop.PlaytimeTop.*;

public class PlaytimeTopCommand extends PlaytimeTop implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        int count = 0;
        for (Map.Entry<UUID, Long> en : timesorted.entrySet()) {
            long time = (en.getValue() / 20);
            long timeminutes = (time / 60);
            long timehours = (timeminutes / 60);
            long timedays = (timehours / 24);
            sender.sendMessage(Bukkit.getOfflinePlayer(en.getKey().toString()) + " = " + ChatColor.RED + "Days " + ChatColor.WHITE + timedays + " " + ChatColor.RED + "Hours " + ChatColor.WHITE + timehours + " " + ChatColor.RED + "Minutes " + ChatColor.WHITE + timeminutes);
            count++;
            if (count >= 10) {
                return true;
            }
        }
        return true;
    }
}