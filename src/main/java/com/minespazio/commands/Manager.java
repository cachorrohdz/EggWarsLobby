package com.minespazio.commands;

import com.minespazio.Main;
import com.minespazio.managers.BungeeCord;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class Manager implements CommandExecutor{


    private Main Principal;

    public Manager(Main main){
        Principal = main;
    }

    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        Player p = null;
        if ((sender instanceof Player))
        {
            p = (Player)sender;
        }
        else
        {
            sender.sendMessage("§e§lRandomLobby§f§l§o: §e§l§oNot available in the Console.");
            sender.sendMessage("§e§lRandomLobby§f§l§o: §e§l§oPlayers Only Online Can Do This");
            return true;
        }
        if ((arg1.getName().equalsIgnoreCase("lobby")) || (arg1.getName().equalsIgnoreCase("salir")) || (arg1.getName().equalsIgnoreCase("hub"))  || (arg1.getName().equalsIgnoreCase("leave"))) {

            if (args.length < 1) {

                Principal.conectar(p, Principal.getRandomServer());
                return true;
            }
            ArrayList lobbylist = (ArrayList)Principal.getConfig().getStringList("Servers");
             if (args[0].equalsIgnoreCase("list")) {
                p.sendMessage(Principal.ConfigMsg("lobby-list").replaceAll("%name", p.getName()).replaceAll("%lobbylist", ""+lobbylist));
                 return true;
            }
           else if(args[0].equalsIgnoreCase(args[0])){
                List get = Principal.getConfig().getStringList("Servers");
                if(!get.contains(args[0])){
                    p.sendMessage(Principal.ConfigMsg("lobby-not-exits").replaceAll("%name", p.getName()).replaceAll("%args", args[0]));
                    p.sendMessage(Principal.ConfigMsg("lobby-list").replaceAll("%name", p.getName()).replaceAll("%lobbylist", ""+lobbylist));
                    return true;
                }
                 Principal.conectar(p, args[0]);
                 return true;
            }
        }


        return false;
    }
}
