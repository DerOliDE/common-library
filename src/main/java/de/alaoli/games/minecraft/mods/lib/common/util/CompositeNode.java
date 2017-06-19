package de.alaoli.games.minecraft.mods.lib.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class CompositeNode<T extends Node> implements Node 
{
	/****************************************************************************************************
	 * Attribute 
	 ****************************************************************************************************/
	
	private final Map<String, T> nodes = new HashMap<>();
	
	/****************************************************************************************************
	 * Method 
	 ****************************************************************************************************/
	
	public void addNode( T node )
	{
		this.nodes.put( node.getNodeName(), node );
	}
	
	public void removeNode( T node )
	{
		this.nodes.remove( node.getNodeName() );
	}
	
	public boolean hasNodes()
	{
		return this.nodes.isEmpty() == false;
	}
	
	public boolean existsNode( String nodeName )
	{
		return this.nodes.containsKey( nodeName );
	}
	
	public boolean existsNode( T node )
	{
		return this.nodes.containsValue( node );
	}	
	
	public T getNode( String nodeName )
	{
		return this.nodes.get( nodeName );
	}
	
	public Set<Entry<String, T>> getNodes()
	{
		return this.nodes.entrySet();
	}
	
	public void clearNodes()
	{
		this.nodes.clear();
	}
}
