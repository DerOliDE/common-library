package de.alaoli.games.minecraft.mods.lib.common.ui.util;

public class AlignHelper 
{
	public static void alignTop( ElementNode element, float y )
	{
		element.setPosY( y + element.getOffsetY() );
	}
	
	public static void alignLeft( ElementNode element, float x )
	{
		element.setPosX( x + element.getOffsetX() );
	}
	
	public static void alignMiddle( ElementNode element, float x, float width )
	{
		element.setPosX( x + width * 0.5f - element.getWidth() * element.getScaleX() * 0.5f + element.getOffsetX() );
	}
	
	public static void alignCenter( ElementNode element, float y, float height )
	{
		element.setPosY( y + height * 0.5f - element.getHeight() * element.getScaleY() * 0.5f + element.getOffsetY() );
	}
	
	public static void alignRight( ElementNode element, float x, float width )
	{
		element.setPosX( x + width - element.getWidth() * element.getScaleX() + element.getOffsetX() );
	}
	
	public static void alignBottom( ElementNode element, float y, float height )
	{
		element.setPosY( y + height - element.getHeight() * element.getScaleY() + element.getOffsetY() );
	}
}
