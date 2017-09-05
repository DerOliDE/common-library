package de.alaoli.games.minecraft.mods.lib.common.ui.element;

import de.alaoli.games.minecraft.mods.lib.common.util.Composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ElementGroup<T extends ElementGroup> extends Element<T> implements Composite<Element>
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/

	private List<Element> elements = new ArrayList<>();

	/******************************************************************************************
	 * Method - Implement CompositeNode<Element>
	 ******************************************************************************************/

	@Override
	public void addComponent( Element component )
	{
		this.elements.add( component );
		
		component.setElementParent( this );
	}
	
	@Override
	public void addComponents( Collection<Element> components )
	{
		components
			.stream()
			.forEach( component -> this.addComponent( component ) );
	}

	@Override
	public void removeComponent( Element component )
	{
		if( this.elements.remove( component ) )
		{
			component.setElementParent( null );
		}
	}

	@Override
	public void removeComponents( Collection<Element> components ) 
	{
		components
			.stream()
			.forEach( component -> this.removeComponent( component ) );		
	}
	
	@Override
	public boolean hasComponents()
	{
		return !this.elements.isEmpty();
	}
	
	@Override
	public boolean existsComponent( Element component )
	{
		return this.elements.contains( component );
	}	

	@Override
	public Collection<Element> getComponents() 
	{
		return this.elements;
	}
	
	@Override
	public void clearComponents()
	{
		this.elements
			.stream()
			.forEach( element -> {
				if( element instanceof ElementGroup )
				{
					((ElementGroup)element).clearComponents();
				}
				element.setElementParent( null );
			});
		this.elements.clear();
	}
	
	/******************************************************************************************
	 * Method - Implement Element
	 ******************************************************************************************/
	
	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks ) 
	{
		this.elements.stream().forEach( element -> element.drawElement( mouseX, mouseY, partialTicks )  );
	}	
}
