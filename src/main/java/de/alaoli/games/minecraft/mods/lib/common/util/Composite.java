package de.alaoli.games.minecraft.mods.lib.common.util;

import java.util.Arrays;
import java.util.Collection;

public interface Composite<T extends Component> extends Component
{
	public void addComponent( T component );
	public void addComponents( Collection<T> components );
	default public void addComponents( T[] components )
	{
		this.addComponents( Arrays.asList( components ) );
	}
	
	public void removeComponent( T component );
	public void removeComponents( Collection<T> components );
	default public void removeComponents( T[] components )
	{
		this.removeComponents( Arrays.asList( components ) );
	}
	
	public boolean hasComponents();
	public boolean existsComponent( T component );
	public Collection<T> getComponents();
	
	public void clearComponents();
}
