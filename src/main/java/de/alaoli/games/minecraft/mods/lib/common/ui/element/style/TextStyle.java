package de.alaoli.games.minecraft.mods.lib.common.ui.element.style;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.common.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Color;

public class TextStyle implements Style
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/
	
	public static final int DEFAULT_LINEHEIGHT = 10;
	public static final List<Align> ALLOWED_ALIGNS = Collections.unmodifiableList( Arrays.asList( Align.LEFT, Align.CENTER, Align.RIGHT ) );
	
	private Color color;
	private Align align;
	private int lineHeight = DEFAULT_LINEHEIGHT;
	private boolean hasShadow = false;
	
	/******************************************************************************************
	 * Method 
	 ******************************************************************************************/	
	
	public Optional<Color> getColor()
	{
		return Optional.ofNullable( this.color );
	}
	
	public TextStyle setColor( Color color )
	{
		this.color = color;
		
		return this;
	}
	
	public Optional<Align> getAlign()
	{
		return Optional.ofNullable( this.align );
	}
	
	public TextStyle setAlign( Align align )
	{
		if( !ALLOWED_ALIGNS.contains( align ) ) { throw new IllegalArgumentException( "Alignment '" + align + "' is not allowed." ); }
		
		this.align = align;
		
		return this;
	}
	
	public TextStyle setLineHeight( int lineHeight )
	{
		this.lineHeight = lineHeight;
				
		return this;
	}
	
	public int getLineHeight()
	{
		return this.lineHeight;
	}
	
	public TextStyle setShadow( boolean shadow )
	{
		this.hasShadow = shadow;
		
		return this;
	}
	
	public boolean hasShadow()
	{
		return this.hasShadow;
	}
}
