package de.alaoli.games.minecraft.mods.lib.common.config;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.lib.common.util.CompositeGroup;

public abstract class SectionGroup extends CompositeGroup<Section> implements Section
{
	/****************************************************************************************************
	 * Method - Implement JsonSerializable
	 ****************************************************************************************************/

	@Override
	public JsonValue serialize() throws DataException 
	{
		JsonObject json = new JsonObject();
		
		this.getNodes().forEach( entry -> json.add( entry.getKey(), entry.getValue().serialize() ) );
		
		return json;
	}

	@Override
	public void deserialize( JsonValue json ) throws DataException 
	{
		if( !json.isObject() ) { throw new DataException( "SectionGroup isn't a JsonObject." ); }
		
		JsonObject obj = json.asObject();
		
		this.getNodes()
			.stream()
			.filter( entry -> { return obj.get( entry.getKey() ) != null; } )
			.forEach( entry -> entry.getValue().deserialize( obj.get( entry.getKey() ) ) );
	}
}
