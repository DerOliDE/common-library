package de.alaoli.games.minecraft.mods.lib.common.ui.drawable;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.common.ui.util.Color;
import net.minecraft.client.gui.Gui;

public class Background extends Gui implements Drawable 
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/

	private Color color;
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	public Background() {}
	
	public Background( Color color ) 
	{
		this.color = color;
	}
	
	public Optional<Color> getColor() 
	{
		return Optional.ofNullable( this.color );
	}
	
	public Background setColor( Color color )
	{
		this.color = color;
		
		return this;
	}
	
	/******************************************************************************************
	 * Method - Implement Drawable
	 ******************************************************************************************/
	
	@Override
	public void drawAt( int x, int y, int width, int height ) 
	{
		int color = this.getColor().map( Color::getValue ).orElse( Color.DEFAULT );			
		
		this.drawRect( x, y, x + width, y + height, color );
	}
}
