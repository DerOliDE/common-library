package de.alaoli.games.minecraft.mods.lib.common.ui.layout;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.alaoli.games.minecraft.mods.lib.common.ui.drawable.Drawable;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Align;

public class BorderPane extends Pane<BorderPane>
{
	/****************************************************************************************************
	 * Attribute
	 ****************************************************************************************************/
	
	public final static String PROPERTY_ALIGN = "align";
	public final static List<Align> allowedAligns = Arrays.asList( Align.TOP, Align.LEFT, Align.CENTER, Align.RIGHT, Align.BOTTOM );
	
	private BorderPaneStyle style = new BorderPaneStyle();
	
	/****************************************************************************************************
	 * Method
	 ****************************************************************************************************/
	
	public BorderPane setBorder( Align align, Element element )
	{
		if( !BorderPane.allowedAligns.contains( align ) ) { throw new IllegalArgumentException( "Alignment '" + align + "' is not allowed." ); }
		
		if( this.hasBorder( align ) )
		{
			this.removeBorder( align );
		}
		element.properties.setProperty( PROPERTY_ALIGN, align.toString() );
		this.addComponent( element );
		
		return this;
	}
	
	public Element getBorder( Align align )
	{
		if( !BorderPane.allowedAligns.contains( align ) ) { throw new IllegalArgumentException( "Alignment '" + align + "' is not allowed." ); }
		
		return this.getComponents()
			.stream()
			.filter( element -> { return element.properties.getProperty( PROPERTY_ALIGN ) == align.toString(); } )
			.findFirst()
			.get();
	}
	
	public Map<Align, Element> getBorders()
	{
		return this.getComponents()
			.stream()
			.collect( Collectors.toMap( 
				element -> Align.get( element.getElementProperty( PROPERTY_ALIGN ) ),
				element -> element
			));
	}
	
	public void removeBorder( Align align )
	{
		this.getComponents()
			.stream()
			.filter( element -> { return element.properties.getProperty( PROPERTY_ALIGN ) == align.toString(); } )
			.forEach( element -> this.removeComponent( element ) );
	}
	
	public boolean hasBorder( Align align )
	{
		return this.getComponents()
			.stream()
			.filter( element -> { return element.properties.getProperty( PROPERTY_ALIGN ) == align.toString(); } )
			.count() > 0;
	}
	
	public BorderPane setStyleBackground( Drawable background )
	{
		this.style.background = background;
		
		return this;
	}
	
	public BorderPane setStyleBorder( Drawable border )
	{
		this.style.border = border;
		
		return this;
	}
	
	/****************************************************************************************************
	 * Method - Implements ElementGroup
	 ****************************************************************************************************/
	
	@Override
	public void removeComponent( Element component )
	{
		component.properties.remove( PROPERTY_ALIGN );
		
		super.removeComponent( component );
	}

	/****************************************************************************************************
	 * Method - Implements Layout
	 ****************************************************************************************************/

	@Override
	public void layout() 
	{
		Element border;
		Map<Align, Element> borders = this.getBorders();
		
		
		int top = this.getElementPosY();
		int left = this.getElementPosX();
		int right = left + this.getElementWidth();
		int bottom = top + this.getElementHeight();
		
		if( borders.containsKey( Align.TOP ) )
		{
			border = borders.get( Align.TOP );
			
			border.setElementPos( left, top );
			border.setElementWidth( this.getElementWidth() );
			
			top += border.getElementHeight();
		}
		
		if( borders.containsKey( Align.BOTTOM ) )
		{
			border = borders.get( Align.BOTTOM );
			bottom -= border.getElementHeight();
			
			border.setElementPos( left, bottom) ; 
			border.setElementWidth( this.getElementWidth() );
		}
		
		if( borders.containsKey( Align.LEFT ) )
		{
			border = borders.get( Align.LEFT );
			
			border.setElementPos( left, top );
			border.setElementHeight( this.getElementHeight() - top - bottom );
			
			left += border.getElementWidth();
		}
		
		if( borders.containsKey( Align.RIGHT ) )
		{
			border = borders.get( Align.RIGHT );
			right -= border.getElementWidth();
			
			border.setElementPos( top, right );
			border.setElementHeight( this.getElementHeight() - top - bottom );
		}
		super.layout();
	}
	
	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		if( this.style.background != null )
    	{
    		this.style.background.draw( this );
    	}
    	
    	if( this.style.border != null )
    	{
    		this.style.border.draw( this );
    	}
    	super.drawElement( mouseX, mouseY, partialTicks );
	}

	public static class BorderPaneStyle
	{
		public Drawable background;
		public Drawable border;		
	}
}
