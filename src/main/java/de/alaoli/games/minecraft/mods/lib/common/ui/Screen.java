package de.alaoli.games.minecraft.mods.lib.common.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import de.alaoli.games.minecraft.mods.lib.common.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.ElementNode;
import de.alaoli.games.minecraft.mods.lib.common.util.Composite;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public abstract class Screen extends GuiScreen implements Composite<ElementNode>, ElementNode<Screen>
{
	private final Map<String, ElementNode> nodes = new HashMap<>();

	/******************************************************************************************
	 * Method - Implement GuiScreen
	 ******************************************************************************************/
	
	@Override
	public void initGui() 
	{
		super.initGui();
		
		this.layout();
	}

	@Override
	public void drawScreen( int mouseX, int mouseY, float partialTicks )
	{	
		this.act( mouseX, mouseY, partialTicks );
		this.draw( mouseX, mouseY, partialTicks );
	}
	
	@Override
    public void onGuiClosed()
    {
		super.onGuiClosed();
		
		this.clearNodes();
    }
    
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
	public boolean hasParent() { return false; }

	@Override
	public ElementNode setParent( ElementNode parent ) { return this; }

	@Override
	public ElementNode getParent() { return null; }

	@Override
	public Screen setAlign( Align align ) { return this; }

	@Override
	public Align getAlign() { return Align.NONE; }

	@Override
	public Screen setOffsetX( float offsetX ) { return this; }

	@Override
	public Screen setOffsetY( float offsetY ) { return this; }
	
	@Override
	public float getOffsetX() { return 0; }

	@Override
	public float getOffsetY() { return 0; }

	@Override
	public Screen setPosX( float posX ) { return this; }

	@Override
	public Screen setPosY( float posY ) { return this; }

	@Override
	public float getPosX() { return 0; }

	@Override
	public float getPosY() { return 0; }

	@Override
	public Screen setScale( float scale ) { return this; }
	
	@Override
	public Screen setScaleX( float scaleX ) { return this; }
	
	@Override
	public Screen setScaleY( float scaleY ) { return this; }
	
	@Override
	public float getScaleX() { return 1.0f; }
	
	@Override
	public float getScaleY() { return 1.0f; }
	
	@Override
	public Screen setWidth( float width )
	{
		this.width = (int) width;
		
		 return this; 
	}

	@Override
	public Screen setHeight( float height )
	{
		this.height = (int) height;
		
		 return this; 
	}

	@Override
	public float getWidth() 
	{
		return this.width;
	}

	@Override
	public float getHeight() 
	{
		return this.width;
	}
	
	@Override
    public FontRenderer getFontRenderer()
    {
        return this.fontRendererObj;
    }	
	
	@Override
	public void act( int mouseX, int mouseY, float partialTicks )
	{
		this.getNodes().stream().forEach( node -> node.getValue().act( mouseX, mouseY, partialTicks )  );
	}
	
	@Override
	public void layout() 
	{
		this.getNodes().stream().forEach( node -> node.getValue().layout() );
	}

	@Override
	public void draw( int mouseX, int mouseY, float partialTicks ) 
	{
		this.getNodes().stream().forEach( node -> node.getValue().draw( mouseX, mouseY, partialTicks ) );
	}		
}
