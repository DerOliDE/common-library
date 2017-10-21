package de.alaoli.games.minecraft.mods.lib.common.ui.layout;

import java.util.Collection;
import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.BoxStyle;

public abstract class AbstractPane<T extends AbstractPane<T>> extends Element<T> implements Layout
{
	/****************************************************************************************************
	 * Attribute
	 ****************************************************************************************************/

	private BoxStyle boxStyle;

	/****************************************************************************************************
	 * Method
	 ****************************************************************************************************/

	public T setBoxStyle( BoxStyle style )
	{
		this.boxStyle = style;
		
		return (T)this;
	}
	
	public Optional<BoxStyle> getBoxStyle()
	{
		return Optional.ofNullable( this.boxStyle );
	}
	
	public abstract Optional<Collection<Element>> getElements();
	
	/****************************************************************************************************
	 * Method - Implements Element
	 ****************************************************************************************************/
	
	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		if( this.boxStyle != null ) { this.boxStyle.drawOn( this ); }
		this.getElements().ifPresent( elements -> elements.forEach(element -> element.drawElement( mouseX, mouseY, partialTicks )  ) );
	}	

	/****************************************************************************************************
	 * Method - Implements Layout
	 ****************************************************************************************************/

	@Override
	public void layout()
	{
		this.doLayout();

		this.getElements().ifPresent( elements -> elements
			.stream()
			.filter( element -> element instanceof Layout )
			.forEach( element -> ((Layout)element).layout() ) );
	}
	
	@Override
	public void doLayout() 
	{
		if( this.boxStyle == null ) { return; }

		this.box.translate( this.boxStyle.paddingLeft, this.boxStyle.paddingTop );
		this.box.setSize(
            box.getWidth() - this.boxStyle.paddingLeft - this.boxStyle.paddingRight,
            box.getHeight() - this.boxStyle.paddingTop - this.boxStyle.paddingBottom
        );
	}
}
