package de.alaoli.games.minecraft.mods.lib.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.WriterConfig;

import de.alaoli.games.minecraft.mods.lib.common.config.Section;
import de.alaoli.games.minecraft.mods.lib.common.config.SectionGroup;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.lib.common.json.JsonFileAdapter;

public class Config extends SectionGroup implements JsonFileAdapter
{
	/****************************************************************************************************
	 * Attribute
	 ****************************************************************************************************/
	
	private String savePath;
	private boolean isDirty = false;

	/****************************************************************************************************
	 * Method
	 ****************************************************************************************************/

	public static Config factory( String savePath, Section[] sections ) throws DataException, IOException
	{
		Config config = new Config();
		
		config.addComponents( sections );
		config.setSavePath( savePath );
		config.load();
		
		return config;
	}
	
	/****************************************************************************************************
	 * Method - Implement JsonFileAdapter
	 ****************************************************************************************************/
	
	@Override
	public void setSavePath( String savePath ) 
	{
		this.savePath = savePath;
	}

	@Override
	public String getSavePath() 
	{
		return this.savePath;
	}

	@Override
	public void setDirty( boolean flag ) 
	{
		this.isDirty = flag;
	}

	@Override
	public boolean isDirty() 
	{ 
		return this.isDirty; 
	}

	@Override
	public void save() throws IOException, DataException 
	{
		if( !this.isDirty ) { return; }
		
		OutputStreamWriter writer = new OutputStreamWriter( new FileOutputStream( new File( this.savePath ) ), "UTF-8" );
		
		this.serialize().writeTo( writer, WriterConfig.PRETTY_PRINT );
		this.setDirty( false );
		
		writer.close();
		
	}

	@Override
	public void load() throws IOException, DataException 
	{	
		File config = new File( this.savePath );
		
		if( !config.exists() ) { return; }
		if( !config.isFile() ) { return; }
		
		InputStreamReader reader = new InputStreamReader( new FileInputStream( config ), "UTF-8" );
		
		this.deserialize( Json.parse( reader ) );
		
		reader.close();
	}

	@Override
	public void cleanup() 
	{
		this.clearComponents();
	}
}
