package com.captain0potlid.captain0potlid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {

	public static HashMap<UUID, String> ready = new HashMap<UUID, String>(); 
	
	public static String hour;
	public static String TotalDate;
	public static String TotalTime;
	
	@Override
	public void onEnable() {
		ConsoleCommandSender consol = Bukkit.getConsoleSender();
		
		consol.sendMessage(ChatColor.AQUA + "mujupermission starts/");		
		
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	@EventHandler
	public void dew( PlayerCommandPreprocessEvent e) {
		
		if(e.getMessage().contains("/plugin") || e.getMessage().contains("/pl")) {
			banapi.sap = false;
			banapi.pcr(e.getPlayer(), "/PLUGINS");
		}
		if(e.getMessage().contains("/teammsg") || e.getMessage().contains("/tm") || e.getMessage().contains("/me") || e.getMessage().contains("/w")) {
			banapi.sap = false;
			banapi.pcr(e.getPlayer(), "/minecraft:#-muju");
		}
		
	}
	@EventHandler
	public void join(PlayerJoinEvent e) {
		
		try {
			String n = this.getConfig().getString("WARNCOUNTS." + e.getPlayer().getUniqueId());
			banapi.wcount.put(e.getPlayer().getUniqueId(), Integer.parseInt(n));
			
			if(Integer.parseInt(n) >= 1 | this.getConfig().getString("STATUS." + e.getPlayer().getUniqueId()) == "BAN") {
				this.getConfig().set("STATUS." + e.getPlayer().getUniqueId() , "BAN");
				
				String x = this.getConfig().getString(e.getPlayer().getUniqueId() + "." + n);
				
				//  '2': '2021鰍 06杉 26析 / 神板 1獣 17歳 28段 -   郊左 '
				//2021鰍 06杉 26析 / 神板 1獣 17歳 28段 -   郊左 
				//蒋拭辞 31鯵 肢薦
				
				String b = x.substring(0, 33);
				String ar = x.replace(b, "");
				banapi.sap = true;
				banapi.pcr(e.getPlayer(), ar);
			} else {
				this.getConfig().set("STATUS." + e.getPlayer().getUniqueId() , "GOOD");
			}
		}catch(Exception x){
			if(!banapi.wcount.containsKey(e.getPlayer().getUniqueId())) {
				banapi.wcount.put(e.getPlayer().getUniqueId(), 0);
				getConfig().options().copyDefaults(false);
				saveConfig();
				getdatetime();
				this.getConfig().set(e.getPlayer().getUniqueId() + ".a" , TotalDate + " / "+ TotalTime + " - " + "0NEW");
				this.getConfig().set("WARNCOUNTS" , e.getPlayer().getUniqueId() + "0");
				this.getConfig().set("STATUS." + e.getPlayer().getUniqueId() , "GOOD");
				this.saveConfig();
			}
		}
		
		try {//赤生檎
			String n = this.getConfig().getString("STATUS." + e.getPlayer().getUniqueId());
			if(n.contains("BAN")) {
				String a = this.getConfig().getString("WARNCOUNTS." + e.getPlayer().getUniqueId());
				
				if(a == "0") return;		
				
				if(this.getConfig().contains(e.getPlayer().getUniqueId() + ""))
				banapi.sap = true;
				banapi.pcr(e.getPlayer(), "");
				return;
			}
			if(n.contains("OP")) {
				e.getPlayer().setOp(true);
				e.getPlayer().sendMessage(ChatColor.RED + "<You have been OPed. / OP映廃 渋昔鞠醸柔艦陥.>");
			}
		}catch(Exception x){ //蒸生檎
			if(!banapi.wcount.containsKey(e.getPlayer().getUniqueId())) {
				banapi.wcount.put(e.getPlayer().getUniqueId(), 0);
				getConfig().options().copyDefaults(false);
				saveConfig();
				getdatetime();
				this.getConfig().set(e.getPlayer().getUniqueId() + ".a" , TotalDate + "/ "+ TotalTime + " - " + "0NEW");
				this.getConfig().set("WARNCOUNTS" , e.getPlayer().getUniqueId() + "0");
				this.getConfig().set("STATUS." + e.getPlayer().getUniqueId() , "GOOD");
				this.saveConfig();
			}
		}
		
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
	}
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {	
	}
	
	
	
	public static void getdatetime() {
		
		String thisTail;
	    LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH");
	    DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("mm");
	    DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("ss");
	    DateTimeFormatter myFormatObj3 = DateTimeFormatter.ofPattern("yyyy");
	    DateTimeFormatter myFormatObj4 = DateTimeFormatter.ofPattern("MM");
	    DateTimeFormatter myFormatObj5 = DateTimeFormatter.ofPattern("dd");
	    String formattedDate = myDateObj.format(myFormatObj);
	    String formattedDate1 = myDateObj.format(myFormatObj1);
	    String formattedDate2 = myDateObj.format(myFormatObj2);
	    String formattedDate3 = myDateObj.format(myFormatObj3); //鰍亀
	    String formattedDate4 = myDateObj.format(myFormatObj4); //杉
	    String formattedDate5 = myDateObj.format(myFormatObj5); //劾
	   
	    int yay = Integer.parseInt(formattedDate);
	     
	    if(yay >= 13) {
	    	//13獣 - 24獣析 獣 
	    	hour = Integer.toString(yay - 12);
	    	thisTail = "神板";
	    }else {
	    	hour = Integer.toString(yay);
	    	thisTail = "神穿";
	    }
	    
	    
	    
	    TotalTime = thisTail + " " + hour + "獣 " + formattedDate1 + "歳 " + formattedDate2 + "段";
	    TotalDate = formattedDate3 + "鰍 " + formattedDate4 + "杉 " + formattedDate5 + "析";
	    
	}
	
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		if (cmd.getName().equalsIgnoreCase("perminfo")) {
			sender.sendMessage(ChatColor.WHITE + "[DVLP] 背雁 柵球拭辞 鯵降切 柵球 乞球稽 穿発馬獣畏柔艦猿?");
			sender.sendMessage(ChatColor.GRAY + "[穿発 費諺 獣 /ahead 脊径]");
			
			ready.put(Bukkit.getPlayer(sender.getName()).getUniqueId(), "perminfo");
			
		}
		
		if(cmd.getName().equalsIgnoreCase("masterwarn")) {
			if(args.length < 1) {
				sender.sendMessage(ChatColor.RED + "[WR] ARGS 脊径 採店球験艦陥.");
				return true;
			}
			if(!sender.isOp()) {
				sender.sendMessage(ChatColor.RED + "[WR] 錘慎切亜 焼艦嬢辞 背雁 政煽研 蓄号/BAN 拝 呪 蒸柔艦陥!");
				return true;
			}
			if(!Bukkit.getPlayerExact(args[0]).isValid()) {
				sender.sendMessage(ChatColor.RED + "[WR] 蒸澗 巴傾戚嬢脊艦陥!");
				return true;
			}
			if(!(args[1].contains("true") || args[1].contains("false"))) {
				sender.sendMessage(ChatColor.RED + "[WR] ARGS 莫縦精 TRUE 暁澗 FALSE 稽 脊径馬偲醤 杯艦陥!");
				return true;
			}
			
			String local = "";
			for(int i = 0; i<args.length; i++) {
				local = local + args[i]+ " ";
			}
			String one = local.replace("masterwarn", "");
			String ww1 = one.replace(args[0], "");
			String ww = ww1.replace(args[1], ""); //       /masterwarn urabahn true 公持医陥 せせせせせせせせ
			banapi.uban(Bukkit.getPlayer(args[0]), Boolean.parseBoolean(args[1]), ww);
		}
		
		if(cmd.getName().equalsIgnoreCase("ahead")) {
			if(ready.containsKey(Bukkit.getPlayer(sender.getName()).getUniqueId())) {
				
				String xthis = ready.get(Bukkit.getPlayer(sender.getName()).getUniqueId());
				
				if(xthis == "perminfo" ) {
					sender.sendMessage(ChatColor.RED + "[DVLP] 背雁 巴傾戚嬢澗 muju.202105.pg.dvlp.ismaster 税 鋼発葵戚 false脊艦陥!(錘慎切亜 焼鑑艦陥)");
					sender.sendMessage(ChatColor.GRAY + "<TIP!> 析鋼 政煽澗 鯵降切 乞球稽 遭脊拝 呪 蒸柔艦陥. 馬走幻, #巷爽食重税 逐差# 焼戚奴聖 社走馬檎 遭脊拝 呪 赤柔艦陥.");
					ready.remove(xthis);
				}
				
				
			}
			else {
				Player p = (Player) sender;
				p.sendMessage(ChatColor.RED + "[DVLP] 焼送 焼巷 誤敬亀 走舛鞠走 省紹柔艦陥!");
				return true;
			}
			
		}
		return true;
	}
	
}

