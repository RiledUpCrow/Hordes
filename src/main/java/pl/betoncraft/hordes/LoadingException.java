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

/**
 * Thrown when the configuration contains invalid data.
 * 
 * @author Jakub Sapalski
 */
public class LoadingException extends Exception {
	
	private static final long serialVersionUID = 5197545070386342207L;
	private String message; 
	
	public LoadingException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
