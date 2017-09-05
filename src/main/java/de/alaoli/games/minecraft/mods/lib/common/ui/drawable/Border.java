package de.alaoli.games.minecraft.mods.lib.common.ui.drawable;

import de.alaoli.games.minecraft.mods.lib.common.ui.util.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

public class Border implements Drawable 
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/

	private Color color;

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	public Border() 
	{
		this.color = new Color();
	}
	
	public Border( Color color )
	{
		this.color = color;
	}
	
	/******************************************************************************************
	 * Method - Implement Drawable
	 ******************************************************************************************/
	
	@Override
	public void draw( int x, int y, int width, int height ) 
	{
		GlStateManager.pushMatrix();
		{
			Gui.drawRect( x, y, x+width, y+1 , this.color.value );
			Gui.drawRect( x, y+height, x+width, y+height-1 , this.color.value );
			Gui.drawRect( x, y, x+1, y+height, this.color.value );
			Gui.drawRect( x+width, y, x+width-1, y+height, this.color.value );
		}
		GlStateManager.popMatrix();
	}
}
