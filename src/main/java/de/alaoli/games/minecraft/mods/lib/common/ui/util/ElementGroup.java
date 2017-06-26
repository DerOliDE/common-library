package de.alaoli.games.minecraft.mods.lib.common.ui.util;

import de.alaoli.games.minecraft.mods.lib.common.util.Composite;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class ElementGroup extends Element implements Composite<ElementNode>
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/

	private ElementNode parent;
	private final Map<String, ElementNode> nodes = new HashMap<>();
	
	private Align align = Align.NONE;
	
	private float offsetX = 0;
	private float offsetY = 0;
	private float posX = 0;
	private float posY = 0;
	
	private float width = 0;
	private float height = 0;

	/******************************************************************************************
	 * Method - Implement CompositeNode<ElementNode>
	 ******************************************************************************************/

	@Override
	public void addNode( ElementNode node )
	{
		this.nodes.put( node.getNodeName(), node );
		
		node.setParent( this );
	}
	
	@Override
	public void removeNode( ElementNode node )
	{
		this.nodes.remove( node.getNodeName() );
		
		node.setParent( null );
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
	public boolean existsNode( ElementNode node )
	{
		return this.nodes.containsValue( node );
	}	
	
	@Override
	public ElementNode getNode( String nodeName )
	{
		return this.nodes.get( nodeName );
	}
	
	@Override
	public Set<Entry<String, ElementNode>> getNodes()
	{
		return this.nodes.entrySet();
	}
	
	@Override
	public void clearNodes()
	{
		this.getNodes().stream().forEach( node -> node.getValue().setParent( null ) );
		
		this.nodes.clear();
	}
	
	/******************************************************************************************
	 * Method - Implement ElementNode
	 ******************************************************************************************/
	
	@Override
	public void layout() 
	{
		super.layout();
		
		this.getNodes().stream().forEach( node -> node.getValue().layout() );
	}

	@Override
	public void draw( int mouseX, int mouseY, float partialTicks ) 
	{
		this.getNodes().stream().forEach( node -> node.getValue().draw( mouseX, mouseY, partialTicks )  );
	}	
}
