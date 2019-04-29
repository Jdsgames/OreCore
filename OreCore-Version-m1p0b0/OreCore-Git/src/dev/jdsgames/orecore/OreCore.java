package dev.jdsgames.orecore;

import org.bukkit.plugin.java.JavaPlugin;

import dev.jdsgames.orecore.events.CobbleCreated;

public class OreCore extends JavaPlugin
{
	OreCore plugin;
	
	// Enables the Plugin
	public void onEnable() 
	{
		this.plugin = this;
		registerEvents();
	}
	
	// Disables the Plugin
	public void onDisable() 
	{
		
	}
	
	// Register the Events
	private void registerEvents() 
	{
		this.getServer().getPluginManager().registerEvents(new CobbleCreated(plugin), plugin);
	}
}