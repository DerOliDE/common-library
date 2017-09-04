package de.alaoli.games.minecraft.mods.lib.common.util;

public interface Component
{	
	default public String getComponentName()
	{
		return this.toString();
	}
}
