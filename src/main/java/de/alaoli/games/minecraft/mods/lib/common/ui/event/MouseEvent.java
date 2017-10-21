package de.alaoli.games.minecraft.mods.lib.common.ui.event;

import org.lwjgl.input.Mouse;

public class MouseEvent
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/
	
	public final int x;
	public final int y;
	public final int button;
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/	
	
	public MouseEvent( int x, int y, int button )
	{
		this.x = x;
		this.y = y;
		this.button = button;
	}
	
	public static boolean isButtonDown( int button )
	{
		return Mouse.isButtonDown( button );
	}	
}
