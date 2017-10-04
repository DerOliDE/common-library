package de.alaoli.games.minecraft.mods.lib.common.ui.drawable;

import java.util.Optional;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Color;
import net.minecraft.client.gui.Gui;

public class Border extends Gui implements Drawable 
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/
	
	private Color color;
	
	private boolean hideTop = false;
	private boolean hideLeft = false;
	private boolean hideRight = false;
	private boolean hideBottom = false;
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	public Border() {}
	
	public Border( Color color ) 
	{
		this.color = color;
	}
		
	public Optional<Color> getColor()
	{
		return Optional.ofNullable( this.color );
	}
	
	public Border setColor( Color color )
	{
		this.color = color;
		
		return this;
	}
	
	public Border hide( boolean hide )
	{
		this.hideTop = hide;
		this.hideLeft = hide;
		this.hideRight = hide;
		this.hideBottom = hide;
		
		return this;
	}
	
	public Border hideTop( boolean hide )
	{
		this.hideTop = hide;
		
		return this;
	}
	
	public Border hideLeft( boolean hide )
	{
		this.hideLeft = hide;
		
		return this;
	}
	
	public Border hideRight( boolean hide )
	{
		this.hideRight = hide;
		
		return this;
	}
	
	public Border hideBottom( boolean hide )
	{
		this.hideBottom = hide;
		
		return this;
	}
	
	/******************************************************************************************
	 * Method - Implement Drawable
	 ******************************************************************************************/
	
	@Override
	public void drawAt( int x, int y, int width, int height ) 
	{
		int color = this.getColor().map( Color::getValue ).orElse( Color.DEFAULT );				
				
		if( !this.hideTop ) { this.drawHorizontalLine( x, x+width-1, y, color ); }
		if( !this.hideLeft ) { this.drawVerticalLine( x, y, y+height, color ); }
		if( !this.hideRight ) { this.drawVerticalLine( x+width-1, y, y+height, color ); }
		if( !this.hideBottom ) { this.drawHorizontalLine( x, x+width-1, y+height-1, color ); }
	}
}
