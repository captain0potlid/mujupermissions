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
//      /masterwarn urabahn true ������� ����������������
		sap = w;
		spacecon(t);
		if(w == false) {
			pcr(input, t);
		}
		if(w == true) {
			main.getdatetime();
			int d = wcount.get(input.getUniqueId());
			int a = d+1;
			wcount.put(input.getUniqueId(),a); //Ƚ�� ���� �Ϸ�
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
			input.kickPlayer(ChatColor.GRAY + " |#| ���ּ��� ��� |#| " + "\n" + "\n" 
					+ ChatColor.RED + "�ش� �����κ��� " + input.getDisplayName() 
					+ "�Բ����� �Ʒ��� ���� ��������" + "\n" + "��� ������ �Ұ��Ǿ����� �˸��ϴ�." + "\n" + "\n" + ChatColor.DARK_AQUA 
					+ "�������(���Ƚ�� ��������): " + ChatColor.YELLOW + "X" + "\n" + ChatColor.DARK_AQUA + "���ǻ���: "
					+ ChatColor.YELLOW + t + ChatColor.DARK_AQUA + "\n" + "���ܱⰣ: " + ChatColor.YELLOW
					+ "���ܾ���" + "\n"+ "\n" + "\n" + ChatColor.GREEN
					+ "(���ּ��� ���� ���ڵ� : https://discord.gg/mQUmY4V4CZ)");
		return;
		}
		if(sap == true) {
			input.kickPlayer(ChatColor.GRAY + " |#| ���ּ��� ���� ���� �ȳ� |#| " + "\n" + "\n" 
					+ ChatColor.RED + "�ش� �����κ��� " + input.getDisplayName() 
					+ "�Բ����� �Ʒ��� ���� ��������" + "\n" + " ������ ���ܵǾ����� �˸��ϴ�." + "\n" + "\n" + ChatColor.DARK_AQUA 
					+ "�������(���Ƚ�� ��������): " + ChatColor.YELLOW + "O" + "\n" + ChatColor.DARK_AQUA + "���ܻ���:"
					+ ChatColor.YELLOW + t + ChatColor.DARK_AQUA + "\n" + "���ܱⰣ: " + ChatColor.YELLOW
					+ "<���ڵ� ��������>" + "\n"+ "\n" + "\n" + ChatColor.GREEN 
					+ "(���ּ��� ���� ���ڵ� : https://discord.gg/mQUmY4V4CZ)");
		return;
		}
	}

}
