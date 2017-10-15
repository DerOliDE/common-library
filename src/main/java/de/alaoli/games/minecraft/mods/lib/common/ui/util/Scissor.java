package de.alaoli.games.minecraft.mods.lib.common.ui.util;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.Rectangle;

@SideOnly( Side.CLIENT )
public class Scissor 
{
	public static void begin( Rectangle box )
	{
		if( box.isEmpty() ) { throw new IllegalArgumentException(); }

		begin( box.getX(), box.getY(), box.getWidth(), box.getHeight() );
	}

	public static void begin( int x, int y, int width, int height )
	{
		if( ( x < 0 ) || ( y < 0 ) ) { throw new IllegalArgumentException(); }
		if( ( width <= 0 ) || ( height <= 0 ) ) { throw new IllegalArgumentException(); }
		
		int scale = ScaledResolution.getScaleFactor();
		int scaledHeight = ScaledResolution.getScaledHeight();
		
		//Left, bottom, width, height
		GL11.glScissor( x*scale, scaledHeight - (y+height)*scale, width*scale, height*scale );
		GL11.glEnable( GL11.GL_SCISSOR_TEST );
	}
	
	public static void end()
	{
		GL11.glDisable( GL11.GL_SCISSOR_TEST );
	}
}
