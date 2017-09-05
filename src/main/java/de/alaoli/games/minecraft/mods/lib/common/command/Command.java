package de.alaoli.games.minecraft.mods.lib.common.command;

import java.util.ArrayList;
import java.util.List;

import de.alaoli.games.minecraft.mods.lib.common.ModException;
import de.alaoli.games.minecraft.mods.lib.common.util.Component;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public abstract class Command implements Component, ICommand 
{
	/****************************************************************************************************
	 * Attribute
	 ****************************************************************************************************/
	
	private Command parent;

	/****************************************************************************************************
	 * Method
	 ****************************************************************************************************/
	
	public abstract void execute( Arguments argument );
	
	/****************************************************************************************************
	 * Method - Implement Component
	 ****************************************************************************************************/
	
	@Override
	public String getComponentName() 
	{
		return this.getName();
	}
	
	/****************************************************************************************************
	 * Method - Implement CommandNode
	 ****************************************************************************************************/

	public boolean hasParent() 
	{
		return this.parent != null;
	}

	public Command getParent() 
	{
		return this.parent;
	}

	public void setParent( Command parent )
	{
		this.parent = parent;
	}
	
	public void sendUsage( ICommandSender sender ) 
	{
		sender.sendMessage( new TextComponentString( this.getUsage( sender ) ) );
	}
	
	/****************************************************************************************************
	 * Methods - Implement Comparable<ICommand>
	 ****************************************************************************************************/
	
	@Override
	public int compareTo( ICommand command ) 
	{
		return this.getName().compareTo( command.getName() );
	}	
	
	/****************************************************************************************************
	 * Methods - Implement ICommand
	 ****************************************************************************************************/

	@Override
	public boolean isUsernameIndex( String[] p_82358_1_, int p_82358_2_ )
	{
		return false;
	}
    
	@Override
	public String getUsage( ICommandSender sender ) 
	{
		String usage = this.getName();
		
		if( this.hasParent() )
		{
			usage = this.getParent().getUsage( sender ) + " " + usage;
		}
		return usage;
	}
	
	@Override
	public List getAliases() 
	{
		List<String> list = new ArrayList<>();
		
		list.add( this.getName() );
		
		return list;
	}	

	@Override
	public List<String> getTabCompletions( MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos )
	{
		List<String> list = new ArrayList<>();
		list.add( this.getName() );
		
		return list;
	}
	
	@Override
	public void execute( MinecraftServer server, ICommandSender sender, String[] args ) throws CommandException 
	{
		try
		{
			this.execute( new Arguments( server, sender, this, args ) );
		}
		catch( ModException e )
		{
			sender.sendMessage( new TextComponentString( e.getMessage() ) );
		}
	}
}
