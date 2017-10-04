package de.alaoli.games.minecraft.mods.lib.common.ui.drawable;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.Element;

public interface Drawable
{
	default public void drawOn( Element element )
	{
		this.drawAt( element.getElementPosX(), element.getElementPosY(), element.getElementWidth(), element.getElementHeight() );
	}
	
	public void drawAt( int x, int y, int width, int height );
}
