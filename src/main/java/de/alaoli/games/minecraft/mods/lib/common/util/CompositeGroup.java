package de.alaoli.games.minecraft.mods.lib.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class CompositeGroup<T extends Node> implements Composite<T> 
{
	/****************************************************************************************************
	 * Attribute 
	 ****************************************************************************************************/
	
	private final Map<String, T> nodes = new HashMap<>();
	
	/****************************************************************************************************
	 * Method 
	 ****************************************************************************************************/
	
	@Override
	public void addNode( T node )
	{
		this.nodes.put( node.getNodeName(), node );
	}
	
	@Override
	public void removeNode( T node )
	{
		this.nodes.remove( node.getNodeName() );
	}
	
	@Override
	public boolean hasNodes()
	{
		return this.nodes.isEmpty() == false;
	}
	
	@Override
	public boolean existsNode( String nodeName )
	{
		return this.nodes.containsKey( nodeName );
	}
	
	@Override
	public boolean existsNode( T node )
	{
		return this.nodes.containsValue( node );
	}	
	
	@Override
	public T getNode( String nodeName )
	{
		return this.nodes.get( nodeName );
	}
	
	@Override
	public Set<Entry<String, T>> getNodes()
	{
		return this.nodes.entrySet();
	}
	
	@Override
	public void clearNodes()
	{
		this.nodes.clear();
	}
}
