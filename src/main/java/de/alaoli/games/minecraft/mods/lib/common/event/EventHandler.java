package de.alaoli.games.minecraft.mods.lib.common.event;

import net.minecraftforge.common.MinecraftForge;

public class EventHandler
{
	/********************************************************************************
	 * Attributes
	 ********************************************************************************/
	
	private static class LazyHolder
	{
		public static final EventHandler INSTANCE = new EventHandler();
	}
	
	/********************************************************************************
	 * Methods
	 ********************************************************************************/
	
	protected EventHandler() {}
	
	public static EventHandler getInstance()
	{
		return LazyHolder.INSTANCE;
	}
	
	public static void register()
	{
		MinecraftForge.EVENT_BUS.register( LazyHolder.INSTANCE );
	}
}
