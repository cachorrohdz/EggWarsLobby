package com.minespazio;

import com.minespazio.commands.Manager;
import com.minespazio.managers.BungeeCord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Main extends JavaPlugin {

    public Plugin pl;

    @Override
    public void onLoad() {
        MensajeConsola(Prefix() + "&e&lPlugin load...");
    }

    @Override
    public void onEnable() {
        pl = this;
        saveDefaultConfig();
        BungeeCord cone = new BungeeCord(this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        MensajeConsola(Prefix() + "&aPlugin enable!");
        getCommand("lobby").setExecutor(new Manager(this));
    }


    @Override
    public void onDisable() {
        pl = null;
        MensajeConsola(Prefix() + "&ePlugin disable!");
    }

    private void MensajeConsola(String msg) {
        System.out.println(ChatColor.translateAlternateColorCodes('&', msg));
    }


    private String Prefix(){
    return ChatColor.GRAY+"["+ChatColor.GREEN+"RandomLobby"+ChatColor.GRAY+"] ";
    }

    public Plugin get()
    {
        return pl;
    }

    public String ConfigMsg(String args){
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString(args));
    }


    public String getRandomServer() {

        ArrayList servers = (ArrayList)getConfig().getStringList("Servers");
        Random random = new Random();
        int index = random.nextInt(servers.size());
        return (String)servers.get(index);
    }

    public void conectar(Player player, String args) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(this.pl, "BungeeCord", b.toByteArray());
    }

}
