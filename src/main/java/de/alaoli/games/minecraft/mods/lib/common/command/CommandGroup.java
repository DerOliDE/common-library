package de.alaoli.games.minecraft.mods.lib.common.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.commons.lang3.ArrayUtils;

import de.alaoli.games.minecraft.mods.lib.common.ModException;
import de.alaoli.games.minecraft.mods.lib.common.util.Composite;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public abstract class CommandGroup extends Command implements Composite<Command> 
{
	/****************************************************************************************************
	 * Attributes
	 ****************************************************************************************************/
	
	private Map<String, Command> commands = new HashMap<>();
	
	/****************************************************************************************************
	 * Method - Implement Command
	 ****************************************************************************************************/

	@Override
	public void execute( Arguments args )
	{
		if( args.isEmpty() )
		{
			this.sendUsage( args.sender );
			return;
		}
		String arg = args.next(); 
		
		if( this.commands.containsKey( arg ) )
		{
			this.commands.get( arg ).execute( args );
		}
		else
		{
			this.sendUsage( args.sender );
		}
	}

	/****************************************************************************************************
	 * Methods - Implement ICommand
	 ****************************************************************************************************/
	
	@Override
	public List<String> getTabCompletions( MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos )
	{
		List<String> list = new ArrayList<>();
		
		if( ( args.length > 1 ) && 
			( this.commands.containsKey( args[ 0 ] ) ) )
		{
			Command command = this.commands.get( args[ 0 ] );
			
			if( command instanceof CommandGroup )
			{
				return ((CommandGroup)command).getTabCompletions( server, sender, ArrayUtils.remove( args, 0 ), pos );
			}
			return list;
		}
		
		this.commands.entrySet()
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
	public String getUsage( ICommandSender sender ) 
	{
		String usage = "";
		
		if( this.hasParent() )
		{
			usage += this.getParent().getName() + " ";
		}
		usage += this.getName();
		
		if( this.hasComponents() )
		{
			StringJoiner options = new StringJoiner( " | " );
		
			this.commands.entrySet()
				.stream()
				.forEach( entry -> options.add( entry.getKey() ) );
			usage += " " + options;
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
	
	
	/****************************************************************************************************
	 * Methods - Implement Comparable<ICommand>
	 ****************************************************************************************************/
	
	@Override
	public int compareTo( ICommand command ) 
	{
		return this.getName().compareTo( command.getName() );
	}

	/****************************************************************************************************
	 * Methods - Implement Composite
	 ****************************************************************************************************/
	
	@Override
	public void addComponent( Command component ) 
	{
		this.commands.put( component.getComponentName(), component );
		
		component.setParent( this );
	}

	@Override
	public void addComponents( Collection<Command> components )
	{
		components
			.stream()
			.forEach( component -> this.addComponent( component ) );
	}

	@Override
	public void removeComponent( Command component ) 
	{
		if( this.commands.containsKey( component.getComponentName() ) )
		{
			component.setParent( null );
		}
		this.commands.remove( component.getComponentName() );
	}

	@Override
	public void removeComponents( Collection<Command> components ) 
	{
		components
			.stream()
			.forEach( component -> this.removeComponent( component ) );		
	}
	
	@Override
	public boolean hasComponents() 
	{
		return !this.commands.isEmpty();
	}

	@Override
	public boolean existsComponent( Command component )
	{
		return this.commands.containsKey( component.getComponentName() );
	}

	@Override
	public void clearComponents()
	{
		this.commands.values()
			.stream()
			.forEach( command -> command.setParent( null ) );
		this.commands.clear();
	}
}
