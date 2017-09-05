package de.alaoli.games.minecraft.mods.lib.common.ui.drawable;

import de.alaoli.games.minecraft.mods.lib.common.ui.util.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

public class Background implements Drawable 
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/

	private Color color;
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/

	public Background() 
	{
		this.color = new Color();
	}
	
	public Background( Color color )
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
			Gui.drawRect( x, y, x + width, y + height, this.color.value );
		}
		GlStateManager.popMatrix();
		
	}
}
