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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class util {
	intercept plugin;
	public util(intercept instance) {
		plugin = instance;
		}
	
	public boolean doesntHavePermission(Player p,String permission){
		p.sendMessage(ChatColor.RED + "You don't have permission for this.");
		return !(p.hasPermission(plugin.getName() + "." + permission)||p.hasPermission(plugin.getName() + ".*"));
	}
	
	public boolean doesntHavePermission(CommandSender p,String permission){
		p.sendMessage(ChatColor.RED + "You don't have permission for this.");
		return !(p.hasPermission(plugin.getName() + "." + permission)||p.hasPermission(plugin.getName() + ".*"));
	}
	
	
	public HashMap<String,String> getFlags(String string){
		HashMap<String,String> ret=new HashMap<String,String>();
		boolean inQuotes=false;
		boolean inFlag=true;
		String currentflag="";
		String flagvalue="";
		for(char c:string.toCharArray()){
			if(c=='\''){
				inQuotes=!inQuotes;
			}else if(c==' '&&!inQuotes){
				ret.put(currentflag, flagvalue);
                currentflag = "";
                flagvalue = "";
				inFlag=true;
			}else if(c==':'&&!inQuotes){
				inFlag=false;
			}else if(inFlag){
				currentflag+=c;
			}else if(inQuotes){
				flagvalue+=c;
			}else if(!inFlag){
				flagvalue+=c;
			}
			
		}
		ret.put(currentflag, flagvalue);
		return ret;
		}
	
	
	public boolean shouldDeny(String relevantflags,Player p){
		relevantflags=relevantflags.toLowerCase();
		HashMap<String,String> flags=getFlags(relevantflags);
		for(String s:plugin.denylist.keySet()){
			if(compare(flags,getFlags(plugin.denylist.get(s)))){
				notifyDeny(p);
				return true;
				}
		}
		return false;
	}
	
	public boolean compare(HashMap<String,String> event,HashMap<String,String> entry){
		for(String s:entry.keySet()){
			if(!event.containsKey(s)) return false;
			if((event.get(s)!=entry.get(s))&&(entry.get(s)!="*")) return false;
		}
		return true;
	}
	
	public String join(String[] a, String delimiter,Integer startIndex) { 
		try{
		Collection<String> s=Arrays.asList(a);
		StringBuffer buffer = new StringBuffer();
		Iterator<String> iter = s.iterator();
		
		while (iter.hasNext()) {
			if(startIndex==0){
			buffer.append(iter.next());
			if (iter.hasNext()) {
				buffer.append(delimiter);
			}
				}else{startIndex--; iter.next();}
			}
		
		return buffer.toString();
	}catch(Exception e)
	{
		e.printStackTrace();
		return "";
	}
		} 
	
	public void notifyDeny(Player p){
		p.sendMessage(ChatColor.DARK_GRAY + "[Intercept] " + ChatColor.RED +  "Your action matches a filter and has been denied.");		
	}
}
