package de.alaoli.games.minecraft.mods.lib.common.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.lib.common.util.Composite;

public abstract class SectionGroup implements Section, Composite<Section>
{
	/****************************************************************************************************
	 * Attribute
	 ****************************************************************************************************/
	
	private List<Section> sections = new ArrayList<>();
	
	/****************************************************************************************************
	 * Method - Implement Composite
	 ****************************************************************************************************/

	@Override
	public void addComponent( Section component )
	{
		this.sections.add( component );
	}

	@Override
	public void addComponents( Collection<Section> components )
	{
		this.sections.addAll( components );
	}
	
	@Override
	public void removeComponent( Section component ) 
	{
		this.sections.remove( component );
	}

	@Override
	public void removeComponents( Collection<Section> components )
	{
		this.sections.removeAll( components );
	}
	
	@Override
	public boolean hasComponents() 
	{
		return !this.sections.isEmpty();
	}

	@Override
	public boolean existsComponent( Section component) 
	{
		return this.sections.contains( component );
	}
	
	@Override
	public Collection<Section> getComponents()
	{
		return this.sections;
	}
	
	@Override
	public void clearComponents() 
	{
		this.sections.clear();
	}

	/****************************************************************************************************
	 * Method - Implement JsonSerializable
	 ****************************************************************************************************/

	@Override
	public JsonValue serialize() throws DataException 
	{
		JsonObject json = new JsonObject();
		
		this.sections
			.stream()
			.forEach( section -> json.add( section.getComponentName(), section.serialize() ) );
		
		return json;
	}

	@Override
	public void deserialize( JsonValue json ) throws DataException 
	{
		if( !json.isObject() ) { throw new DataException( "SectionGroup isn't a JsonObject." ); }
		
		JsonObject obj = json.asObject();
		
		this.sections
			.stream()
			.filter( section -> { return obj.get( section.getComponentName() ) != null; } )
			.forEach( section -> section.deserialize( obj.get( section.getComponentName() ) ) );
	}
}
