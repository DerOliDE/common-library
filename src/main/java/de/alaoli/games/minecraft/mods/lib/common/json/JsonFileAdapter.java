package de.alaoli.games.minecraft.mods.lib.common.json;

import java.io.IOException;

import de.alaoli.games.minecraft.mods.lib.common.data.DataException;

public interface JsonFileAdapter 
{
	void setSavePath(String savePath);
	String getSavePath();
	
	void setDirty(boolean flag);
	boolean isDirty();
	
	void save() throws IOException, DataException;
	void load() throws IOException, DataException;
	
	void cleanup();
}
