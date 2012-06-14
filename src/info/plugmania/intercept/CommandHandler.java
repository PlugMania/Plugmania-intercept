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


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CommandHandler implements CommandExecutor {
	intercept plugin;
	public CommandHandler(intercept instance) {
		plugin = instance;
		}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args[0].equalsIgnoreCase("help")){
sender.sendMessage("/intercept deny label denyflags");
sender.sendMessage("/intercept help");

}else if(args[0].equalsIgnoreCase("deny")){
	if(plugin.util.doesntHavePermission(sender, "deny")) return true;
	plugin.denylist.put(args[1], plugin.util.join(args, " ", 2).toLowerCase());
	sender.sendMessage(args[1] + " set to " + plugin.util.join(args, " ", 2));
}else if(args[0].equalsIgnoreCase("version")){
sender.sendMessage(ChatColor.DARK_GRAY + "Intercept version is " + plugin.getDescription().getVersion());
sender.sendMessage(ChatColor.DARK_GRAY + "http://dev.bukkit.org/server-mods/plugmania-intercept/");
}else{
	return false;
}


		return true;
			}
}
