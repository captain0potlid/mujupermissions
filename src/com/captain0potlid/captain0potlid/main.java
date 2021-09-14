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
				
				//  '2': '2021년 06월 26일 / 오후 1시 17분 28초 -   바보 '
				//2021년 06월 26일 / 오후 1시 17분 28초 -   바보 
				//앞에서 31개 삭제
				
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
		
		try {//있으면
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
				e.getPlayer().sendMessage(ChatColor.RED + "<You have been OPed. / OP권한 승인되었습니다.>");
			}
		}catch(Exception x){ //없으면
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
	    String formattedDate3 = myDateObj.format(myFormatObj3); //년도
	    String formattedDate4 = myDateObj.format(myFormatObj4); //월
	    String formattedDate5 = myDateObj.format(myFormatObj5); //날
	   
	    int yay = Integer.parseInt(formattedDate);
	     
	    if(yay >= 13) {
	    	//13시 - 24시일 시 
	    	hour = Integer.toString(yay - 12);
	    	thisTail = "오후";
	    }else {
	    	hour = Integer.toString(yay);
	    	thisTail = "오전";
	    }
	    
	    
	    
	    TotalTime = thisTail + " " + hour + "시 " + formattedDate1 + "분 " + formattedDate2 + "초";
	    TotalDate = formattedDate3 + "년 " + formattedDate4 + "월 " + formattedDate5 + "일";
	    
	}
	
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		if (cmd.getName().equalsIgnoreCase("perminfo")) {
			sender.sendMessage(ChatColor.WHITE + "[DVLP] 해당 빌드에서 개발자 빌드 모드로 전환하시겠습니까?");
			sender.sendMessage(ChatColor.GRAY + "[전환 희망 시 /ahead 입력]");
			
			ready.put(Bukkit.getPlayer(sender.getName()).getUniqueId(), "perminfo");
			
		}
		
		if(cmd.getName().equalsIgnoreCase("masterwarn")) {
			if(args.length < 1) {
				sender.sendMessage(ChatColor.RED + "[WR] ARGS 입력 부탁드립니다.");
				return true;
			}
			if(!sender.isOp()) {
				sender.sendMessage(ChatColor.RED + "[WR] 운영자가 아니어서 해당 유저를 추방/BAN 할 수 없습니다!");
				return true;
			}
			if(!Bukkit.getPlayerExact(args[0]).isValid()) {
				sender.sendMessage(ChatColor.RED + "[WR] 없는 플레이어입니다!");
				return true;
			}
			if(!(args[1].contains("true") || args[1].contains("false"))) {
				sender.sendMessage(ChatColor.RED + "[WR] ARGS 형식은 TRUE 또는 FALSE 로 입력하셔야 합니다!");
				return true;
			}
			
			String local = "";
			for(int i = 0; i<args.length; i++) {
				local = local + args[i]+ " ";
			}
			String one = local.replace("masterwarn", "");
			String ww1 = one.replace(args[0], "");
			String ww = ww1.replace(args[1], ""); //       /masterwarn urabahn true 못생겼다 ㅋㅋㅋㅋㅋㅋㅋㅋ
			banapi.uban(Bukkit.getPlayer(args[0]), Boolean.parseBoolean(args[1]), ww);
		}
		
		if(cmd.getName().equalsIgnoreCase("ahead")) {
			if(ready.containsKey(Bukkit.getPlayer(sender.getName()).getUniqueId())) {
				
				String xthis = ready.get(Bukkit.getPlayer(sender.getName()).getUniqueId());
				
				if(xthis == "perminfo" ) {
					sender.sendMessage(ChatColor.RED + "[DVLP] 해당 플레이어는 muju.202105.pg.dvlp.ismaster 의 반환값이 false입니다!(운영자가 아닙니다)");
					sender.sendMessage(ChatColor.GRAY + "<TIP!> 일반 유저는 개발자 모드로 진입할 수 없습니다. 하지만, #무주여신의 축복# 아이템을 소지하면 진입할 수 있습니다.");
					ready.remove(xthis);
				}
				
				
			}
			else {
				Player p = (Player) sender;
				p.sendMessage(ChatColor.RED + "[DVLP] 아직 아무 명령도 지정되지 않았습니다!");
				return true;
			}
			
		}
		return true;
	}
	
}

