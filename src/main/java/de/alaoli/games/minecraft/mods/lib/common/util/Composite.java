package de.alaoli.games.minecraft.mods.lib.common.util;

import java.util.Set;
import java.util.Map.Entry;

public interface Composite<T extends Node> extends Node 
{
	/****************************************************************************************************
	 * Method 
	 ****************************************************************************************************/
	
	public void addNode( T node );
	public void removeNode( T node );
	
	public boolean hasNodes();
	public boolean existsNode( String nodeName );
	public boolean existsNode( T node );
	
	public T getNode( String nodeName );
	public Set<Entry<String, T>> getNodes();
	
	public void clearNodes();
}
