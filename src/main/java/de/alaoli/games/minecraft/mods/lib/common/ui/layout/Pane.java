package de.alaoli.games.minecraft.mods.lib.common.ui.layout;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.ElementGroup;

public class Pane<T extends Pane> extends ElementGroup<T> implements Layout
{
	/****************************************************************************************************
	 * Method - Implements Layout
	 ****************************************************************************************************/
	
	@Override
	public void layout()
	{
		this.getComponents()
			.stream()
			.filter( element -> { return element instanceof Layout; } )
			.forEach( element -> ((Layout)element).layout() );
	}
}
