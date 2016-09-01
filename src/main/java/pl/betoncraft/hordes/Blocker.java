/**
 * Bukkit plugin which moves the mobs closer to the players.
 * Copyright (C) 2016 Jakub "Co0sh" Sapalski
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

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Blocks the mobs from spawning in unwanted places.
 * 
 * @author Jakub Sapalski
 */
public class Blocker implements Listener {
	
	private Hordes plugin;
	
	private Random rand = new Random();
	
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
		LivingEntity e = event.getEntity();
		WorldSettings set = plugin.getWorlds().get(event.getEntity().getWorld().getName());
		if (set == null) {
			return;
		}
		if (!set.getEntities().contains(e.getType())) {
			return;
		}
		if (!set.shouldExist(e)) {
			event.setCancelled(true);
		} else if (rand.nextDouble() > set.getRatio(e.getType())) {
			event.setCancelled(true);
		} else {
			AttributeInstance maxHealth = e.getAttribute(Attribute.GENERIC_MAX_HEALTH);
			maxHealth.setBaseValue(maxHealth.getBaseValue() * set.getHealth(e.getType()));
			e.setHealth(e.getMaxHealth());
		}
	}
	
}
