package de.alaoli.games.minecraft.mods.lib.common.util;

import java.util.Optional;

public interface Component
{	
	default Optional<String> getComponentName()
	{
		return Optional.ofNullable( this.toString() );
	}
}
