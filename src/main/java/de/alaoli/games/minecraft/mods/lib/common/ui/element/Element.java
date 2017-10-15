package de.alaoli.games.minecraft.mods.lib.common.ui.element;

import java.util.Optional;

import org.lwjgl.util.Rectangle;

import de.alaoli.games.minecraft.mods.lib.common.util.Component;
import net.minecraft.client.gui.Gui;

public abstract class Element<T extends Element> extends Gui implements Component
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/

	private Element parent;

	public final Rectangle box = new Rectangle();

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

	public T setElementBounds( int posX, int posY, int width, int height )
	{
		this.box.setBounds( posX, posY, width, height );

		return (T)this;
	}

	public T setElementLocation( int posX, int posY )
	{
		this.box.setLocation( posX, posY );

		return (T)this;
	}

	public T setElementPosX( int posX )
	{
		this.box.setX( posX );

		return (T)this;
	}

	public T setElementPosY( int posY )
	{
		this.box.setY( posY );

		return (T)this;
	}

	public T setElementSize( int width, int height )
	{
		this.box.setSize( width, height );

		return (T)this;
	}

	public T setElementWidth( int width )
	{
		this.box.setWidth( width );

		return (T)this;
	}

	public T setElementHeight( int height )
	{
		this.box.setHeight( height );

		return (T)this;
	}

	public abstract void drawElement( float partialTicks  );
}
