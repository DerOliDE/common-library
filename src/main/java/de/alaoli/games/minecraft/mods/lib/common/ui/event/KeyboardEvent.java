package de.alaoli.games.minecraft.mods.lib.common.ui.event;

import org.lwjgl.input.Keyboard;

public class KeyboardEvent
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/
	
	public final char eventChar;
	public final int eventKey;
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/	

	public KeyboardEvent( char eventChar, int eventKey )
	{
		this.eventChar = eventChar;
		this.eventKey = eventKey;
	}
	
	public static boolean isKeyDown( int key )
	{
		return Keyboard.isKeyDown( key );
	}	
}
