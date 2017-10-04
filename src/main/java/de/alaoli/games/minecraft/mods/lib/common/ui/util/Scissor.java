package de.alaoli.games.minecraft.mods.lib.common.ui.util;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly( Side.CLIENT )
public class Scissor 
{
	/*
	 Minecraft mc = Minecraft.getMinecraft();
			ScaledResolution sr = new ScaledResolution(mc);
			int scale = sr.getScaleFactor();
			int x = this.getElementPosX();
			int y = this.getElementPosY();
			int width = this.getElementWidth();
			int height = this.getElementHeight();
			
			
	 		GL11.glScissor( mc.displayWidth - (x + width ) * scale, mc.displayHeight - (y + height) * scale, (width + x) * scale, (height+y)* scale);
			GL11.glEnable( GL11.GL_SCISSOR_TEST );

	 */

	public static void begin( int x, int y, int width, int height )
	{
		if( x < 0 ) { throw new IllegalArgumentException(); }
		if( y < 0 ) { throw new IllegalArgumentException(); }
		if( width < 0 ) { throw new IllegalArgumentException(); }
		if( height < 0 ) { throw new IllegalArgumentException(); }
		
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
