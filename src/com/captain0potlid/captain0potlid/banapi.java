package com.captain0potlid.captain0potlid;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.BanList.Type;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class banapi {
	public static HashMap<UUID, Integer> wcount = new HashMap<UUID, Integer>(); 
	public static boolean sap = false;
	
	public static void uban(Player input, Boolean w, String t) {
//      /masterwarn urabahn true 公持医陥 せせせせせせせせ
		sap = w;
		spacecon(t);
		if(w == false) {
			pcr(input, t);
		}
		if(w == true) {
			main.getdatetime();
			int d = wcount.get(input.getUniqueId());
			int a = d+1;
			wcount.put(input.getUniqueId(),a); //判呪 走舛 刃戟
			main.getPlugin(main.class).getConfig().set(input.getUniqueId() + "." + a , main.TotalDate + " / "+ main.TotalTime + " - " + t);
			main.getPlugin(main.class).getConfig().set("WARNCOUNTS." + input.getUniqueId() , Integer.toString(a));
			main.getPlugin(main.class).saveConfig();
			pcr(input, t);
		}
	}
	public static void spacecon(String a) {
		String b = a.substring(0, 1);
		if(b.isBlank() || b.isEmpty()) {
			String local = a.substring(2, a.length());
			spacecon(local);
		} else {
			return;
		}
	}
	
	public static void pcr(Player input, String t) {
		spacecon(t);
		main.getPlugin(main.class).getConfig().set("STATUS." + input.getUniqueId() , "BAN");
		if(sap == false ) {
			input.kickPlayer(ChatColor.GRAY + " |#| 巷爽辞獄 井壱 |#| " + "\n" + "\n" 
					+ ChatColor.RED + "背雁 辞獄稽採斗 " + input.getDisplayName() 
					+ "還臆辞澗 焼掘人 旭精 鎧遂生稽" + "\n" + "節獣 羨紗戚 災亜鞠醸製聖 硝験艦陥." + "\n" + "\n" + ChatColor.DARK_AQUA 
					+ "奄系政巷(井壱判呪 匂敗政巷): " + ChatColor.YELLOW + "X" + "\n" + ChatColor.DARK_AQUA + "爽税紫牌: "
					+ ChatColor.YELLOW + t + ChatColor.DARK_AQUA + "\n" + "託舘奄娃: " + ChatColor.YELLOW
					+ "託舘蒸製" + "\n"+ "\n" + "\n" + ChatColor.GREEN
					+ "(巷爽辞獄 因縦 巨什坪球 : https://discord.gg/mQUmY4V4CZ)");
		return;
		}
		if(sap == true) {
			input.kickPlayer(ChatColor.GRAY + " |#| 巷爽辞獄 羨紗 榎走 照鎧 |#| " + "\n" + "\n" 
					+ ChatColor.RED + "背雁 辞獄稽採斗 " + input.getDisplayName() 
					+ "還臆辞澗 焼掘人 旭精 鎧遂生稽" + "\n" + " 羨紗戚 託舘鞠醸製聖 硝験艦陥." + "\n" + "\n" + ChatColor.DARK_AQUA 
					+ "奄系政巷(井壱判呪 匂敗政巷): " + ChatColor.YELLOW + "O" + "\n" + ChatColor.DARK_AQUA + "託舘紫政:"
					+ ChatColor.YELLOW + t + ChatColor.DARK_AQUA + "\n" + "託舘奄娃: " + ChatColor.YELLOW
					+ "<巨什坪球 紺亀庚税>" + "\n"+ "\n" + "\n" + ChatColor.GREEN 
					+ "(巷爽辞獄 因縦 巨什坪球 : https://discord.gg/mQUmY4V4CZ)");
		return;
		}
	}

}
