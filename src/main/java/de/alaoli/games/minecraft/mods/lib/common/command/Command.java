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

public abstract class Command implements CommandNode
{
	/****************************************************************************************************
	 * Attribut 
	 ****************************************************************************************************/
	
	private CommandNode parent;
	
	/****************************************************************************************************
	 * Method 
	 ****************************************************************************************************/
	
	public Command( CommandNode parent )
	{
		this.parent = parent;
	}
	
	/****************************************************************************************************
	 * Method - Implement Node
	 ****************************************************************************************************/
	
	@Override
	public String getNodeName() 
	{
		return this.getName();
	}
	
	/****************************************************************************************************
	 * Method - Implement CommandNode
	 ****************************************************************************************************/

	@Override
	public boolean hasParent() 
	{
		return this.parent != null;
	}

	@Override
	public CommandNode getParent() 
	{
		return this.parent;
	}

	@Override
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
