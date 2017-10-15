package de.alaoli.games.minecraft.mods.lib.common.json;

import com.eclipsesource.json.JsonValue;

import de.alaoli.games.minecraft.mods.lib.common.data.DataException;

public interface JsonSerializable
{
	JsonValue serialize() throws DataException;
	void deserialize(JsonValue json) throws DataException;
}
