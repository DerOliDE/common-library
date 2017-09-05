package de.alaoli.games.minecraft.mods.lib.common.ui.util;

public enum Align 
{
	TOPLEFT( 7, "topleft" ),
	TOP( 8, "top" ),
	TOPRIGHT( 9, "topright" ),
	LEFT( 4, "left" ),
	CENTER( 5, "center" ),
	RIGHT( 6, "right" ),
	BOTTOMLEFT( 1, "bottomleft" ),
	BOTTOM( 2, "bottom" ),
	BOTTOMRIGHT( 3, "bottomright" );
	
	public final int id;
	public final String name;
	
	private Align( int id, String name ) 
	{
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() 
	{
		return this.name;
	}
	
	public static Align get( String name )
	{
		for( Align align : Align.values() )
		{
			if( align.toString() == name )
			{
				return align;
			}
		}
		throw new IllegalArgumentException( "Unknown alignment." );
	}
}
