package de.alaoli.games.minecraft.mods.lib.common.ui.util;

import net.minecraft.client.gui.FontRenderer;

public abstract class Element implements ElementNode 
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/

	private ElementNode parent;
	
	private Align align = Align.NONE;
	
	private float offsetX = 0;
	private float offsetY = 0;
	private float posX = 0;
	private float posY = 0;
	
	private float scaleX = 1.0f;
	private float scaleY = 1.0f;
	
	private float width = 0;
	private float height = 0;

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	protected void alignTop( float y )
	{
		this.posY = y + this.offsetY;
	}
	
	protected void alignLeft( float x )
	{
		this.posX = x + this.offsetX;
	}
	
	protected void alignMiddle( float x, float width )
	{
		this.posX = x + width * 0.5f - this.width * this.scaleX * 0.5f + this.offsetX;
	}
	
	protected void alignCenter( float y, float height )
	{
		this.posY = y + height * 0.5f - this.height * this.scaleY * 0.5f + this.offsetY;
	}
	
	protected void alignRight( float x, float width )
	{
		this.posX = x + width - this.width * this.scaleX + this.offsetX;
	}
	
	protected void alignBottom( float y, float height )
	{
		this.posY = y + height - this.height * this.scaleY + this.offsetY;
	}
	
	/******************************************************************************************
	 * Method - Implement ElementNode
	 ******************************************************************************************/
	
	@Override
	public boolean hasParent() 
	{
		return this.parent != null;
	}

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
	public ElementNode setAlign( Align align ) 
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
	public ElementNode setOffsetX( float offsetX ) 
	{
		this.offsetX = offsetX;
		
		return this;
	}

	@Override
	public ElementNode setOffsetY( float offsetY )
	{
		this.offsetY = offsetY;
		
		return this;
	}

	@Override
	public float getPosX() 
	{
		return this.posX;
	}

	@Override
	public float getPosY() 
	{
		return this.posY;
	}

	@Override
	public ElementNode setPosX( float posX ) 
	{
		this.posX = posX;
		
		return this;
	}

	@Override
	public ElementNode setPosY( float posY )
	{
		this.posY = posY;
		
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
	public ElementNode setScale( float scale )
	{
		this.scaleX = scale;
		this.scaleY = scale;
		
		return this;
	}
	
	@Override
	public ElementNode setScaleX( float scaleX ) 
	{
		this.scaleX = scaleX;
		
		return this;
	}	

	@Override
	public ElementNode setScaleY( float scaleY )
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
	public ElementNode setWidth( float width )
	{
		this.width = width;
		
		return this;
	}

	@Override
	public ElementNode setHeight( float height )
	{
		this.height = height;
		
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
		return (this.hasParent()) ? this.parent.getFontRenderer() : null;
	}
	
	@Override
	public void layout() 
	{
		float parentX = (this.hasParent() ) ? this.parent.getPosX() : 0;
		float parentY = (this.hasParent() ) ? this.parent.getPosY() : 0;
		float parentWidth = (this.hasParent() ) ? this.parent.getWidth() : 0;
		float parentHeight = (this.hasParent() ) ? this.parent.getHeight() : 0;
		
		switch( this.align )
		{
			case TOPLEFT :
				this.alignTop( parentY );
				this.alignLeft( parentX );
				break;
			case TOP :
				this.alignTop( parentY );
				this.alignMiddle( parentX, parentWidth );
				break;
			case TOPRIGHT :
				this.alignTop( parentY );
				this.alignRight( parentX, parentWidth );
				break;
				
			case LEFT :
				this.alignCenter( parentY, parentHeight);
				this.alignLeft( parentX );
				break;
			case CENTER :
				this.alignCenter( parentY, parentHeight);
				this.alignMiddle( parentX, parentWidth );
				break;
			case RIGHT :
				this.alignCenter( parentY, parentHeight);
				this.alignRight( parentX, parentWidth );
				break;
				
			case BOTTOMLEFT :
				this.alignBottom( parentY, parentHeight );
				this.alignLeft( parentX );
				break;
			case BOTTOM :
				this.alignBottom( parentY, parentHeight );
				this.alignMiddle( parentX, parentWidth );
				break;
			case BOTTOMRIGHT :
				this.alignBottom( parentY, parentHeight );
				this.alignRight( parentX, parentWidth );
				break;
				
			case NONE :
			default :
				this.posX = parentX + this.offsetX;
				this.posY = parentY + this.offsetY;
				break;
		}
	}
}
