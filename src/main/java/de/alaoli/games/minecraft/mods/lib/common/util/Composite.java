package de.alaoli.games.minecraft.mods.lib.common.util;

import java.util.Arrays;
import java.util.Collection;

public interface Composite<T extends Component> extends Component
{
	void addComponent(T component);
	void addComponents(Collection<T> components);
	default void addComponents(T[] components)
	{
		this.addComponents( Arrays.asList( components ) );
	}
	
	void removeComponent(T component);
	void removeComponents(Collection<T> components);
	default void removeComponents(T[] components)
	{
		this.removeComponents( Arrays.asList( components ) );
	}
	
	boolean hasComponents();
	boolean existsComponent(T component);
	Collection<T> getComponents();
	
	void clearComponents();
}
