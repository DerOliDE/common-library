package de.alaoli.games.minecraft.mods.lib.common.ui;

import de.alaoli.games.minecraft.mods.lib.common.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.ElementNode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class Button extends GuiButton implements ElementNode<Button>
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/
	
	private ElementNode parent;
	
	private Align align = Align.NONE;
	
	private float offsetX = 0;
	private float offsetY = 0;
	
	private float scaleX = 1.0f;
	private float scaleY = 1.0f;
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	public Button( int id, ElementNode parent )
	{
		super( id, 0, 0, 0, 0, "" );
	}

	public Button( int id, ElementNode parent, String text )
	{
		super( id, 0, 0, 0, 0, text );
	}
	
	/******************************************************************************************
	 * Method - Implement Node
	 ******************************************************************************************/
	
	@Override
	public String getNodeName() 
	{
		return "button_" + this.id;
	}
	
	/******************************************************************************************
	 * Method - Implement ElementNode
	 ******************************************************************************************/

	@Override
	public ElementNode setParent( ElementNode parent ) 
	{
		this.parent = parent;
		
		return this;
	}

	@Override
	public ElementNode getParent() 
	{
		return this.parent;
	}

	@Override
	public Button setAlign( Align align ) 
	{
		this.align = align;
		
		return this;
	}

	@Override
	public Align getAlign() 
	{
		return this.align;
	}

	@Override
	public Button setOffsetX( float offsetX ) 
	{
		this.offsetX = offsetX;
		
		return this;
	}

	@Override
	public Button setOffsetY( float offsetY )
	{
		this.offsetY = offsetY;
		
		return this;
	}

	@Override
	public float getPosX() 
	{
		return this.xPosition;
	}

	@Override
	public float getPosY() 
	{
		return this.yPosition;
	}

	@Override
	public Button setPosX( float posX ) 
	{
		this.xPosition = (int)posX;
		
		return this;
	}

	@Override
	public Button setPosY( float posY )
	{
		this.yPosition = (int)posY;
		
		return this;
	}

	@Override
	public float getOffsetX() 
	{
		return this.offsetX;
	}

	@Override
	public float getOffsetY() 
	{
		return this.offsetY;
	}
	
	@Override
	public Button setScale( float scale )
	{
		this.scaleX = scale;
		this.scaleY = scale;
		
		return this;
	}
	
	@Override
	public Button setScaleX( float scaleX ) 
	{
		this.scaleX = scaleX;
		
		return this;
	}	

	@Override
	public Button setScaleY( float scaleY )
	{
		this.scaleY = scaleY;
		
		return this;
	}	
	
	@Override
	public float getScaleX() 
	{
		return this.scaleX;
	}

	@Override
	public float getScaleY() 
	{
		return this.scaleY;
	}
	
	@Override
	public Button setWidth( float width )
	{
		this.width = (int)width;
		
		return this;
	}

	@Override
	public Button setHeight( float height )
	{
		this.height = (int)height;
		
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
	public void act( int mouseX, int mouseY, float partialTicks ) {}

	@Override
	public void draw( int mouseX, int mouseY, float partialTicks ) 
	{
		this.drawButton( Minecraft.getMinecraft(), mouseX, mouseY );
	}
}
