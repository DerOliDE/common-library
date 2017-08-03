package de.alaoli.games.minecraft.mods.lib.common.ui.util;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import net.minecraft.client.gui.FontRenderer;

public abstract class Element<T> implements ElementNode
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
	public T setAlign( Align align ) 
	{
		this.align = align;
		
		return (T)this;
	}

	@Override
	public Align getAlign() 
	{
		return this.align;
	}

	@Override
	public T setOffsetX( float offsetX ) 
	{
		this.offsetX = offsetX;
		
		return (T)this;
	}

	@Override
	public T setOffsetY( float offsetY )
	{
		this.offsetY = offsetY;
		
		return (T)this;
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
	public T setPosX( float posX ) 
	{
		this.posX = posX;
		
		return (T)this;
	}

	@Override
	public T setPosY( float posY )
	{
		this.posY = posY;
		
		return (T)this;
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
	public T setScale( float scale )
	{
		this.scaleX = scale;
		this.scaleY = scale;
		
		return (T)this;
	}
	
	@Override
	public T setScaleX( float scaleX ) 
	{
		this.scaleX = scaleX;
		
		return (T)this;
	}	

	@Override
	public T setScaleY( float scaleY )
	{
		this.scaleY = scaleY;
		
		return (T)this;
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
	public T setWidth( float width )
	{
		this.width = width;
		
		return (T)this;
	}

	@Override
	public T setHeight( float height )
	{
		this.height = height;
		
		return (T)this;
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
}
