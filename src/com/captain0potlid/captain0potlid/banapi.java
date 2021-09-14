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
//      /masterwarn urabahn true 못생겼다 ㅋㅋㅋㅋㅋㅋㅋㅋ
		sap = w;
		spacecon(t);
		if(w == false) {
			pcr(input, t);
		}
		if(w == true) {
			main.getdatetime();
			int d = wcount.get(input.getUniqueId());
			int a = d+1;
			wcount.put(input.getUniqueId(),a); //횟수 지정 완료
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
			input.kickPlayer(ChatColor.GRAY + " |#| 무주서버 경고 |#| " + "\n" + "\n" 
					+ ChatColor.RED + "해당 서버로부터 " + input.getDisplayName() 
					+ "님께서는 아래와 같은 내용으로" + "\n" + "잠시 접속이 불가되었음을 알립니다." + "\n" + "\n" + ChatColor.DARK_AQUA 
					+ "기록유무(경고횟수 포함유무): " + ChatColor.YELLOW + "X" + "\n" + ChatColor.DARK_AQUA + "주의사항: "
					+ ChatColor.YELLOW + t + ChatColor.DARK_AQUA + "\n" + "차단기간: " + ChatColor.YELLOW
					+ "차단없음" + "\n"+ "\n" + "\n" + ChatColor.GREEN
					+ "(무주서버 공식 디스코드 : https://discord.gg/mQUmY4V4CZ)");
		return;
		}
		if(sap == true) {
			input.kickPlayer(ChatColor.GRAY + " |#| 무주서버 접속 금지 안내 |#| " + "\n" + "\n" 
					+ ChatColor.RED + "해당 서버로부터 " + input.getDisplayName() 
					+ "님께서는 아래와 같은 내용으로" + "\n" + " 접속이 차단되었음을 알립니다." + "\n" + "\n" + ChatColor.DARK_AQUA 
					+ "기록유무(경고횟수 포함유무): " + ChatColor.YELLOW + "O" + "\n" + ChatColor.DARK_AQUA + "차단사유:"
					+ ChatColor.YELLOW + t + ChatColor.DARK_AQUA + "\n" + "차단기간: " + ChatColor.YELLOW
					+ "<디스코드 별도문의>" + "\n"+ "\n" + "\n" + ChatColor.GREEN 
					+ "(무주서버 공식 디스코드 : https://discord.gg/mQUmY4V4CZ)");
		return;
		}
	}

}
