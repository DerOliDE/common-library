package de.alaoli.games.minecraft.mods.lib.common;

import java.io.IOException;

import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface Initialize 
{
	void preInit(FMLPreInitializationEvent event) throws IOException, DataException;
	void init(FMLInitializationEvent event) throws IOException, DataException;
	void postInit(FMLPostInitializationEvent event) throws IOException, DataException;
}
