package de.alaoli.games.minecraft.mods.lib.common.util;

import java.util.Collection;

public interface Composite<T extends Component> extends Component
{
	public void addComponent( T component );
	public void removeComponent( T component );
	
	public boolean hasComponents();
	public boolean existsComponent( T component );
	public Collection<T> getComponents();
	
	public void clearComponents();
}
