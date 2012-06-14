   /*
    Plugmania-intercept: stop your players doing things.
    Copyright (C) 2012 korikisulda

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package info.plugmania.intercept;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerListener implements Listener{
	intercept plugin;
	public PlayerListener(intercept instance) {
		plugin = instance;
		}
	
	@EventHandler()
	public void onGameModeChangeEvent(org.bukkit.event.player.PlayerGameModeChangeEvent event){
		if(plugin.util.shouldDeny(
				"player:'" +	event.getPlayer().getName() +
				"' gamemode:'" + event.getNewGameMode().getValue() + 
				"' world:'" + event.getPlayer().getWorld().getName() + 
				"' event:'" + "gamemode" + "'"
				,event.getPlayer())){
			event.setCancelled(true);
		}
		}
	@EventHandler()
	public void onPlayerBedEnter(org.bukkit.event.player.PlayerBedEnterEvent event){
		if(plugin.util.shouldDeny(
				"player:'" +	event.getPlayer().getName() +
				"' world:'" + event.getPlayer().getWorld().getName() + 
				"' event:'" + "bedenter" + "'"
				,event.getPlayer())){
			event.setCancelled(true);
		}		
	}
	@EventHandler()
	public void onPlayerBucketEmpty(org.bukkit.event.player.PlayerBucketEmptyEvent event){
		if(plugin.util.shouldDeny(
				"player:'" +	event.getPlayer().getName() +
				"' world:'" + event.getPlayer().getWorld().getName() + 
				"' id:'" + event.getBucket().getId() + 
				"' name:'" + event.getBucket().name() + 				
				"' event:'" + "bucketempty" + "'"
				,event.getPlayer())){
			event.setCancelled(true);
		}
	}
	@EventHandler()
	public void onPlayerBucketFill(org.bukkit.event.player.PlayerBucketFillEvent event){
		if(plugin.util.shouldDeny(
				"player:'" +	event.getPlayer().getName() +
				"' world:'" + event.getPlayer().getWorld().getName() + 
				"' id:'" + event.getBucket().getId() + 
				"' name:'" + event.getBucket().name() +
				"' event:'" + "bucketfill" + "'"
				,event.getPlayer())){
			event.setCancelled(true);
		}
	}
	
	
}