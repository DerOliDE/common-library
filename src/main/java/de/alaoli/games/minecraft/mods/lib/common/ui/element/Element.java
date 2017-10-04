package de.alaoli.games.minecraft.mods.lib.common.ui.element;

import java.util.Optional;
import de.alaoli.games.minecraft.mods.lib.common.util.Component;
import net.minecraft.client.gui.Gui;

public abstract class Element<T extends Element> extends Gui implements Component
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/

	private Element parent;
	
	private int posX = 0;
	private int	posY = 0;
	private int width = 0;
	private int height = 0;

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	public T setElementParent( Element parent ) 
	{
		this.parent = parent;
		
		return (T)this;
	}
	
	public Optional<Element> getElementParent() 
	{
		return Optional.ofNullable( this.parent );
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
