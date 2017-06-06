package de.alaoli.games.minecraft.mods.lib.common.command;

import java.util.ArrayList;
import java.util.List;

import de.alaoli.games.minecraft.mods.lib.common.ModException;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public abstract class Command implements ICommand
{
	/********************************************************************************
	 * Attribute
	 ********************************************************************************/
	
	private Command parent;
	
	/********************************************************************************
	 * Methods
	 ********************************************************************************/
	
	public Command( Command parent )
	{
		this.parent = parent;
	}
	
	public boolean hasParent()
	{
		if( this.parent == null )
		{
			return false;
		}
		return true;
	}
	
	public Command getParent()
	{
		return this.parent;
	}

	public void sendUsage( ICommandSender sender )
	{
		sender.addChatMessage( new TextComponentString( this.getCommandUsage( sender ) ) );
	}
		
	public abstract void processCommand( Arguments args );
	
	/********************************************************************************
	 * Methods - Implement Comparable<ICommand>
	 ********************************************************************************/
	
	@Override
	public int compareTo( ICommand command ) 
	{
		return this.getCommandName().compareTo( command.getCommandName() );
	}	
	
	/********************************************************************************
	 * Methods - Implement ICommand
	 ********************************************************************************/

	@Override
	public boolean isUsernameIndex( String[] p_82358_1_, int p_82358_2_ )
	{
		return false;
	}
    
	@Override
	public String getCommandUsage( ICommandSender sender ) 
	{
		String usage = this.getCommandName();
		
		if( this.hasParent() )
		{
			usage = this.getParent().getCommandUsage( sender ) + " " + usage;
		}
		return usage;
	}
	
	@Override
	public List getCommandAliases() 
	{
		List<String> list = new ArrayList<>();
		
		list.add( this.getCommandName() );
		
		return list;
	}	

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
	{
		List<String> list = new ArrayList<>();
		list.add( this.getCommandName() );
		
		return list;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		try
		{
			this.processCommand( new Arguments( sender, args ) );
		}
		catch( ModException e )
		{
			sender.addChatMessage( new TextComponentString( e.getMessage() ) );
		}
	}
}
