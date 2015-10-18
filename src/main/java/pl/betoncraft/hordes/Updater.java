/**
 * Bukkit plugin which moves the mobs closer to the players.
 * Copyright (C) 2015 Jakub "Co0sh" Sapalski
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.betoncraft.hordes;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Updates the configuration to newest version.
 * 
 * @author Jakub Sapalski
 */
public class Updater {
	
	public final int VERSION = 1;

	/**
	 * Updates the configuration to newest version.
	 * 
	 * @param plugin
	 *            the Hordes instance
	 */
	public Updater(Hordes plugin) {
		File configFile = new File(plugin.getDataFolder(), "config.yml");
		if (!configFile.exists()) return; // first installation
		int current = plugin.getConfig().getInt("global-settings.version", 0);
		FileConfiguration config = plugin.getConfig();
		while (current < VERSION) {
			switch (current) {
			case 0:
				for (String key : config.getKeys(false)) {
					if (key.equals("global-settings")) continue;
					config.set("worlds." + key + ".height", config.getInt(
							key + ".height"));
					config.set("worlds." + key + ".multi", config.getInt(
							key + ".multi"));
					config.set("worlds." + key + ".mobs", config.getStringList(
							key + ".mobs"));
					config.set(key, null);
				}
				config.set("global-settings.async-despawn", true);
				config.set("global-settings.despawn-interval", 10);
				config.set("global-settings.version", 1);
				plugin.getLogger().info("Added global settings to the "
						+ "configuration");
				current = 1;
				break;
			default:
				plugin.getLogger().info("Invalid configuration version!");
				break;
			}
		}
		plugin.saveConfig();
	}

}
