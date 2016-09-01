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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Reloads the plugin.
 * 
 * @author Jakub Sapalski
 */
public class ReloadCommand implements CommandExecutor {
	
	private Hordes plugin;

	public ReloadCommand(Hordes plugin) {
		this.plugin = plugin;
		plugin.getCommand("hordesreload").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("hordesreload")) {
			plugin.load();
			sender.sendMessage("Reloaded!");
			return true;
		}
		return false;
	}

}
