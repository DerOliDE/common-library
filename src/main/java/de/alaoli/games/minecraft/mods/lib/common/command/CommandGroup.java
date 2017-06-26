package de.alaoli.games.minecraft.mods.lib.common.command;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.lang3.ArrayUtils;

import de.alaoli.games.minecraft.mods.lib.common.ModException;
import de.alaoli.games.minecraft.mods.lib.common.util.CompositeGroup;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public abstract class CommandGroup extends CompositeGroup<CommandNode> implements CommandNode 
{
	/****************************************************************************************************
	 * Attributes
	 ****************************************************************************************************/
	
	private CommandNode parent;
	
	/****************************************************************************************************
	 * Methods
	 ****************************************************************************************************/
	
	public CommandGroup( CommandNode parent ) 
	{
		this.parent = parent;
	}

	/****************************************************************************************************
	 * Method - Implement Node
	 ****************************************************************************************************/
	
	@Override
	public String getNodeName() 
	{
		return this.getCommandName();
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
		sender.addChatMessage( new TextComponentString( this.getCommandUsage( sender ) ) );
	}
	
	@Override
	public void execute( Arguments args )
	{
		if( args.isEmpty() )
		{
			this.sendUsage( args.sender );
			return;
		}
		String arg = args.next(); 
		
		if( this.existsNode( arg ) )
		{
			this.getNode( arg ).execute( args );
		}
		else
		{
			this.sendUsage( args.sender );
		}
	}
	
	/****************************************************************************************************
	 * Methods - Implement Comparable<ICommand>
	 ****************************************************************************************************/
	
	@Override
	public int compareTo( ICommand command ) 
	{
		return this.getCommandName().compareTo( command.getCommandName() );
	}
	
	/****************************************************************************************************
	 * Methods - Implement ICommand
	 ****************************************************************************************************/
	
	@Override
	public List<String> getTabCompletionOptions( MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos )
	{
		List<String> list = new ArrayList<>();
		
		if( ( args.length > 1 ) && 
			( this.existsNode( args[ 0 ] ) ) )
		{
			CommandNode command = this.getNode( args[ 0 ] );
			
			if( command instanceof CommandGroup )
			{
				return ((CommandGroup)command).getTabCompletionOptions( server, sender, ArrayUtils.remove( args, 0 ), pos );
			}
			return list;
		}
		
		this.getNodes()
			.stream()
			.filter( entry -> entry.getKey().regionMatches( 0, args[ 0 ], 0, args[ 0 ].length() ) )
			.forEach( entry -> list.add( entry.getKey() ) );
		return list;
	}
	
	@Override
	public boolean isUsernameIndex( String[] p_82358_1_, int p_82358_2_ )
	{
		return false;
	}
    
	@Override
	public String getCommandUsage( ICommandSender sender ) 
	{
		String usage = "";
		
		if( this.hasParent() )
		{
			usage += this.parent.getCommandName() + " ";
		}
		usage += this.getCommandName();
		
		if( this.hasNodes() )
		{
			StringJoiner options = new StringJoiner( " | " );
		
			this.getNodes()
				.stream()
				.forEach( entry -> options.add( entry.getKey() ) );
			usage += " " + options;
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
	public void execute( MinecraftServer server, ICommandSender sender, String[] args ) throws CommandException 
	{
		try
		{
			this.execute( new Arguments( server, sender, args ) );
		}
		catch( ModException e )
		{
			sender.addChatMessage( new TextComponentString( e.getMessage() ) );
		}
	}
}
