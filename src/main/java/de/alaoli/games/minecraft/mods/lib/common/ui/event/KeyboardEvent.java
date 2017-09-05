package de.alaoli.games.minecraft.mods.lib.common.ui.event;

public interface KeyboardEvent extends InputEvent 
{
	public void keyTyped( char typedChar, int keyCode );
}
