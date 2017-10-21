package de.alaoli.games.minecraft.mods.lib.common.ui.drawable;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.Element;

public interface Drawable<T extends Element>
{
	void drawOn( T element );
}
