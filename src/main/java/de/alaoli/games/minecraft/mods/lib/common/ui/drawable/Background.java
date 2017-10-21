package de.alaoli.games.minecraft.mods.lib.common.ui.drawable;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Color;
import net.minecraft.client.gui.Gui;

public class Background extends Gui implements Drawable<Element>
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
	public void drawOn( Element element )
	{
		int color = this.getColor().map( Color::getValue ).orElse( Color.DEFAULT ),
			x = element.box.getX(),
			y = element.box.getY();

		Gui.drawRect( x, y, x + element.box.getWidth(), y + element.box.getHeight(), color );
	}
}
