package de.alaoli.games.minecraft.mods.lib.common.ui.drawable;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.Element;

public interface Drawable
{
	default public void draw( Element parent )
	{
		this.draw( parent.getElementPosX(), parent.getElementPosY(), parent.getElementWidth(), parent.getElementHeight() );
	}
	
	public void draw( int x, int y, int width, int height );
}
