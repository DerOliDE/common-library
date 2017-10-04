package de.alaoli.games.minecraft.mods.lib.common.util;

import java.util.Optional;

public interface Component
{	
	default public Optional<String> getComponentName()
	{
		return Optional.ofNullable( this.toString() );
	}
}
