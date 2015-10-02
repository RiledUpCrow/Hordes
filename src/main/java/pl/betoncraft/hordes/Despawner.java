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

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Despawns mobs in unwanted places.
 * 
 * @author Jakub Sapalski
 */
public class Despawner extends BukkitRunnable {
	
	private Hordes plugin;

	/**
	 * Starts the despawner.
	 * 
	 * @param plugin
	 *            instance of the plugin
	 */
	public Despawner(Hordes plugin) {
		this.plugin = plugin;
		runTaskTimerAsynchronously(plugin, 10*20, 10*20);
	}

	@Override
	public void run() {
		for (World world : Bukkit.getWorlds()) {
			WorldSettings settings = plugin.getWorlds().get(world.getName());
			if (settings == null) continue;
			for (Entity entity : world.getEntities()) {
				if (!settings.shouldExist(entity)){
					entity.remove();
				}
			}
		}
	}

}
