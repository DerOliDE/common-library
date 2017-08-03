package de.alaoli.games.minecraft.mods.lib.common.ui.util;

import java.util.Objects;

import de.alaoli.games.minecraft.mods.lib.common.util.Node;
import net.minecraft.client.gui.FontRenderer;

public interface ElementNode<T> extends Node
{
	default public boolean hasParent()
	{
		return this.getParent() != null;
	}
	public ElementNode setParent( ElementNode parent );
	public ElementNode getParent();
	
	public T setAlign( Align align );
	public Align getAlign();
	
	default public T setOffset( float offsetX, float offsetY )
	{
		this.setOffsetX( offsetX );
		this.setOffsetY( offsetY );
		
		return (T)this;
	}
	public T setOffsetX( float offsetX );
	public T setOffsetY( float offsetY );
	public float getOffsetX();
	public float getOffsetY();

	default T setPos( float posX, float posY )
	{
		this.setPosX( posX );
		this.setPosY( posY );
		
		return (T)this;
	}
	public T setPosX( float posX );
	public T setPosY( float posY );
	public float getPosX();
	public float getPosY();
	
	default public T setScale( float scale )
	{
		this.setScaleX( scale );
		this.setScaleY( scale );
		
		return (T)this;
	}
	public T setScaleX( float scaleX );
	public T setScaleY( float scaleY );
	public float getScaleX();
	public float getScaleY();
	
	default public T setDimension( float width, float height )
	{
		this.setWidth(width);
		this.setHeight(height);
		
		return (T)this;
	}
	public T setWidth( float width );
	public T setHeight( float height );
	public float getWidth();
	public float getHeight();

	default public FontRenderer getFontRenderer()
	{
		return (this.hasParent()) ? this.getParent().getFontRenderer() : null;
	}
	
	default public void layout()
	{
		float parentX = (this.hasParent() ) ? this.getParent().getPosX() : 0;
		float parentY = (this.hasParent() ) ? this.getParent().getPosY() : 0;
		float parentWidth = (this.hasParent() ) ? this.getParent().getWidth() : 0;
		float parentHeight = (this.hasParent() ) ? this.getParent().getHeight() : 0;
		
		switch( this.getAlign() )
		{
			case TOPLEFT :	
				AlignHelper.alignTop( this, parentY );
				AlignHelper.alignLeft( this, parentX );
				break;
			case TOP :
				AlignHelper.alignTop( this, parentY );
				AlignHelper.alignMiddle( this, parentX, parentWidth );
				break;
			case TOPRIGHT :
				AlignHelper.alignTop( this, parentY );
				AlignHelper.alignRight( this, parentX, parentWidth );
				break;
				
			case LEFT :
				AlignHelper.alignCenter( this, parentY, parentHeight );
				AlignHelper.alignLeft( this, parentX );
				break;
			case CENTER :
				AlignHelper.alignCenter( this, parentY, parentHeight );
				AlignHelper.alignMiddle( this, parentX, parentWidth );
				break;
			case RIGHT :
				AlignHelper.alignCenter( this, parentY, parentHeight );
				AlignHelper.alignRight( this, parentX, parentWidth );
				break;
				
			case BOTTOMLEFT :
				AlignHelper.alignBottom( this, parentY, parentHeight );
				AlignHelper.alignLeft( this, parentX );
				break;
			case BOTTOM :
				AlignHelper.alignBottom( this, parentY, parentHeight );
				AlignHelper.alignMiddle( this, parentX, parentWidth );
				break;
			case BOTTOMRIGHT :
				AlignHelper.alignBottom( this, parentY, parentHeight );
				AlignHelper.alignRight( this, parentX, parentWidth );
				break;
				
			case NONE :
			default :
				this.setPosX( parentX + this.getOffsetX() );
				this.setPosY( parentY + this.getOffsetY() );
				break;
		}		
	}
	public void act( int mouseX, int mouseY, float partialTicks );
	public void draw( int mouseX, int mouseY, float partialTicks );
}
