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

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class.
 * 
 * @author Jakub Sapalski
 */
public class Hordes extends JavaPlugin {
	
	private HashMap<String, WorldSettings> worlds = new HashMap<>();
	
	private Blocker blocker;
	private Despawner despawner;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		load();
		new ReloadCommand(this);
	}
	
	/**
	 * Loads the data from the configuration and starts new blocker
	 * and despawner.
	 */
	public void load() {
		if (blocker != null) HandlerList.unregisterAll(blocker);
		if (despawner != null) despawner.cancel();
		worlds.clear();
		for (World world : Bukkit.getWorlds()) {
			String name = world.getName();
			if (getConfig().contains(name)) {
				try {
					worlds.put(name, new WorldSettings(this, name));
				} catch (LoadingException e) {
					getLogger().warning("Error while loading configuration for"
							+ name + " world: " + e.getMessage());
				}
			}
		}
		blocker = new Blocker(this);
		despawner = new Despawner(this);
	}
	
	/**
	 * @return the map of world settings
	 */
	public HashMap<String, WorldSettings> getWorlds() {
		return worlds;
	}

}
