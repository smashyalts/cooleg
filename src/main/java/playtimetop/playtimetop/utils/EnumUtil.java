package playtimetop.playtimetop.utils;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;

public final class EnumUtil {
    private EnumUtil() {
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumClass, String ... names) {
        for (String name : names) {
            try {
                Field enumField = enumClass.getDeclaredField(name);
                if (!enumField.isEnumConstant()) continue;
                return (T)((Enum)enumField.get(null));
            }
            catch (IllegalAccessException | NoSuchFieldException reflectiveOperationException) {
                // empty catch block
            }
        }
        return null;
    }

    public static <T extends Enum<T>> Set<T> getAllMatching(Class<T> enumClass, String ... names) {
        EnumSet<T> set = EnumSet.noneOf(enumClass);
        for (String name : names) {
            try {
                Field enumField = enumClass.getDeclaredField(name);
                if (!enumField.isEnumConstant()) continue;
                set.add((T)enumField.get(null));
            }
            catch (IllegalAccessException | NoSuchFieldException reflectiveOperationException) {
                // empty catch block
            }
        }
        return set;
    }

    public static Material getMaterial(String ... names) {
        return EnumUtil.valueOf(Material.class, names);
    }

    public static Statistic getStatistic(String ... names) {
        return EnumUtil.valueOf(Statistic.class, names);
    }

    public static EntityType getEntityType(String ... names) {
        return EnumUtil.valueOf(EntityType.class, names);
    }
}
