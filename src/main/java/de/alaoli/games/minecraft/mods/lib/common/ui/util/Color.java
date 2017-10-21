package de.alaoli.games.minecraft.mods.lib.common.ui.util;

public class Color 
{	
	/********************************************************************************
	 * Attribute
	 ********************************************************************************/

	public static final int DEFAULT = 0;

	public final int value;
	
	/********************************************************************************
	 * Method
	 ********************************************************************************/

	public Color()
	{
		this.value = DEFAULT;
	}
		
	public Color( int value )
	{
		if( value < 0 ) { throw new IllegalArgumentException( "'color' value must be greater than 0." ); }
		
		this.value = value;
	}
	
	public Color( int r, int g, int b )
	{
		this( 255, r, g, b );
	}
	
	public Color( float alpha, int r, int g, int b )
	{
		this( Math.round( alpha*255 ), r, g, b );
	}
	
	public Color( int alpha, int r, int g, int b )
	{
		this.value = argbToIntValue( alpha, r, g, b );
	}

	@Override
	public boolean equals( Object obj )
	{
		return obj instanceof Color && ((Color) obj).value == this.value;
	}

	@Override
	public int hashCode() 
	{
		return this.value;
	}
	
	public final int getValue()
	{
		return this.value;
	}
	
	public static int argbToIntValue( int alpha, int r, int g, int b )
	{
		if( alpha > 255 ) { throw new IllegalArgumentException( "'alpha' value must be between 0 and 255." ); }
		if( r > 255 ) { throw new IllegalArgumentException( "'r' value must be between 0 and 255." ); }
		if( g > 255 ) { throw new IllegalArgumentException( "'g' value must be between 0 and 255." ); }
		if( b > 255 ) { throw new IllegalArgumentException( "'b' value must be between 0 and 255." ); }
		
		return ((alpha & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF));		
	}
}
