package de.alaoli.games.minecraft.mods.lib.common.ui.layout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.Element;

public class VBox extends AbstractPane<VBox> 
{
	/****************************************************************************************************
	 * Attribute
	 ****************************************************************************************************/

	private final List<Element> elements = new ArrayList<>();

	/****************************************************************************************************
	 * Method 
	 ****************************************************************************************************/
	
	public VBox addElement( Element element )
	{
		element.setElementParent( this );
		this.elements.add(element);
		
		return this;
	}
	
	/****************************************************************************************************
	 * Method - Implements AbstractPane
	 ****************************************************************************************************/
	
	@Override
	public Optional<Collection<Element>> getElements()
	{
		return Optional.of( this.elements );
	}

	/****************************************************************************************************
	 * Method - Implements Layout
	 ****************************************************************************************************/
		

	@Override
	public void doLayout() 
	{
		super.doLayout();

		int x = this.box.getX();
		int y = this.box.getY();
		int width = this.box.getWidth();
		int height = this.box.getHeight();

		for( Element element : this.elements )
		{
			element.box.setLocation( x, y );
			element.box.setWidth( width );
			
			y += element.box.getHeight();
		}
	}
	
}
