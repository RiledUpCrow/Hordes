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
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

/**
 * Blocks the mobs from spawning in unwanted places.
 * 
 * @author Jakub Sapalski
 */
public class Blocker implements Listener {
	
	private Hordes plugin;
	
	/**
	 * Starts the blocker.
	 * 
	 * @param plugin
	 *            instance of the plugin
	 */
	public Blocker(Hordes plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onSpawn(CreatureSpawnEvent event) {
		if (event.getSpawnReason() != SpawnReason.NATURAL) return;
		WorldSettings set = plugin.getWorlds().get(event.getEntity().getWorld()
				.getName());
		LivingEntity e = event.getEntity();
		if (!set.shouldExist(e)) {
			event.setCancelled(true);
		} else {
			if (set.getEntities().contains(e.getType())) {
				e.setMaxHealth(e.getMaxHealth() * set.getMultiplier());
				e.setHealth(e.getMaxHealth());
			}
		}
	}
	
}
