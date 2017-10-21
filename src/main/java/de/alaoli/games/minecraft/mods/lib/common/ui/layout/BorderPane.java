package de.alaoli.games.minecraft.mods.lib.common.ui.layout;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Align;

public class BorderPane extends AbstractPane<BorderPane>
{
	/****************************************************************************************************
	 * Attribute
	 ****************************************************************************************************/
	
	public final static List<Align> ALLOWED_ALIGNS = Collections.unmodifiableList( Arrays.asList( Align.TOP, Align.LEFT, Align.CENTER, Align.RIGHT, Align.BOTTOM ) );
		
	private final Map<Align, Element> borders = new HashMap<>();
	
	/****************************************************************************************************
	 * Method
	 ****************************************************************************************************/
	
	public BorderPane setBorder( Align align, Element element )
	{
		if( !BorderPane.ALLOWED_ALIGNS.contains( align ) ) { throw new IllegalArgumentException( "Alignment '" + align + "' is not allowed." ); }
		
		element.setElementParent( this );
		this.borders.put( align, element );
		
		return this;
	}
	
	public Optional<Element> getBorder( Align align )
	{
		if( !BorderPane.ALLOWED_ALIGNS.contains( align ) ) { throw new IllegalArgumentException( "Alignment '" + align + "' is not allowed." ); }
		
		return Optional.ofNullable( this.borders.get(align) );	
	}
	
	public void removeBorder( Align align )
	{
		this.getBorder( align ).ifPresent( border -> border.setElementParent( null ) );
		this.borders.remove( align );
		
	}
	
	public boolean hasBorder( Align align )
	{
		return this.borders.containsKey( align );
	}

	public void doLayoutTop()
	{

	}

	public void doLayoutBottom()
	{

	}

	public void doLayoutLeft()
	{

	}
	public void doLayoutRight()
	{

	}
	public void doLayoutCenter()
	{

	}

	/****************************************************************************************************
	 * Method - Implements AbstractPane
	 ****************************************************************************************************/

	@Override
	public Optional<Collection<Element>> getElements()
	{
		return Optional.of( this.borders.values() );
	}
	
	/****************************************************************************************************
	 * Method - Implements Layout
	 ****************************************************************************************************/

	@Override
	public void doLayout() 
	{
		super.doLayout();
		
		if( this.borders.isEmpty() ) { return; }

		Element border;

		int x = this.box.getX();
		int y = this.box.getY();
		int width = this.box.getWidth();
		int height = this.box.getHeight();
		int top = this.box.getX();
		int left = this.box.getY();
		int right = 0;
		int bottom = 0;


		if( this.borders.containsKey( Align.TOP ) )
		{
			border = this.borders.get( Align.TOP );

			border.box.setLocation( left, top );
			border.box.setWidth( width );

			top += border.box.getHeight();
		}
		
		if( this.borders.containsKey( Align.BOTTOM ) )
		{
			border = this.borders.get( Align.BOTTOM );
			bottom = border.box.getHeight();

			border.box.setLocation( left, x + height - bottom );
			border.box.setWidth( width );
		}
		
		if( this.borders.containsKey( Align.LEFT ) )
		{
			border = this.borders.get( Align.LEFT );

			border.box.setLocation( left, top);
			border.box.setHeight( height - top - bottom );

			left += border.box.getWidth();
		}
		
		if( this.borders.containsKey( Align.RIGHT ) )
		{
			border = this.borders.get( Align.RIGHT );

			right += x + width - border.box.getWidth();

			border.box.setLocation( top, right );
			border.box.setHeight( height - top - bottom );
		}
		
		if( this.borders.containsKey( Align.CENTER ) )
		{
			border = this.borders.get( Align.CENTER );

			border.box.setLocation( left, top );
			border.box.setSize(
				x + width - left - right,
				y + height - top - bottom
			);
		}
	}
}
