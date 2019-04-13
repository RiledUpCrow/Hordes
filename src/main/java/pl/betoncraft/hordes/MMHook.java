package pl.betoncraft.hordes;

import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class MMHook {
    private static io.lumine.xikage.mythicmobs.MythicMobs mythicMobs;

    private MMHook() {
    }

    public static boolean isMythicMob(LivingEntity entity) {
        return mythicMobs != null && mythicMobs.getAPIHelper().isMythicMob(entity);
    }

    static void setInstance(Plugin plugin) {
        mythicMobs = (plugin instanceof io.lumine.xikage.mythicmobs.MythicMobs)
                ? (io.lumine.xikage.mythicmobs.MythicMobs) plugin : null;
    }
}
