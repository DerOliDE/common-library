package de.alaoli.games.minecraft.mods.lib.common.ui.element;

import java.util.Properties;

import de.alaoli.games.minecraft.mods.lib.common.util.Component;

public abstract class Element<T extends Element> implements Component
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/

	public final Properties properties = new Properties();
	
	private Element parent;
	
	private int posX = 0;
	private int	posY = 0;
	private int width = 0;
	private int height = 0;

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	public T setElementProperty( String key, String value )
	{
		this.properties.setProperty( key, value );
		
		return (T)this;
	}
	
	public String getElementProperty( String key )
	{
		return this.properties.getProperty( key );
	}
	
	public boolean hasElementProperty( String key )
	{
		return this.properties.containsKey( key );
	}
	
	public T setElementParent( Element parent ) 
	{
		this.parent = parent;
		
		return (T)this;
	}

	public Element getElementParent() 
	{
		return this.parent;
	}

	public boolean hasElementParent()
	{
		return this.parent != null;
	}
	
	public int getElementPosX() 
	{
		return this.posX;
	}

	public int getElementPosY() 
	{
		return this.posY;
	}

	
	public T setElementPos( int posX, int posY )
	{
		this.posX = posX;
		this.posY = posY;
		
		return (T)this;
	}
	
	public T setElementPosX( int posX ) 
	{
		this.posX = posX;
		
		return (T)this;
	}

	public T setElementPosY( int posY )
	{
		this.posY = posY;
		
		return (T)this;
	}
		
	public T setElementDimension( int width, int height )
	{
		this.width = width;
		this.height = height;
		
		return (T)this;
	}
	
	public T setElementWidth( int width )
	{
		this.width = width;
		
		return (T)this;
	}

	public T setElementHeight( int height )
	{
		this.height = height;
		
		return (T)this;
	}

	public int getElementWidth() 
	{
		return this.width;
	}

	public int getElementHeight() 
	{
		return this.height;
	}
	
	public abstract void drawElement( int mouseX, int mouseY, float partialTicks  );
}
