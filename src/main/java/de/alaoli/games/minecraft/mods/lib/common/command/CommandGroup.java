/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************* */
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
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public abstract class CommandGroup extends Command implements Composite<Command>
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */
	
	private final Map<String, Command> commands = new HashMap<>();

	/* **************************************************************************************************************
	 * Method - Implement Command
	 ************************************************************************************************************** */

	@Override
	public void execute( Arguments args )
	{
		if( args.isEmpty() )
		{
			this.sendUsage( args.getSender() );
			return;
		}
		String arg = args.next(); 
		
		if( this.commands.containsKey( arg ) )
		{
			this.commands.get( arg ).execute( args );
		}
		else
		{
			this.sendUsage( args.getSender() );
		}
	}

	/* **************************************************************************************************************
	 * Methods - Implement ICommand
	 ************************************************************************************************************** */
	
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
				return command.getTabCompletions( server, sender, ArrayUtils.remove( args, 0 ), pos );
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
		
			this.commands.forEach( (key, value) -> options.add( key ) );
			usage += " " + options;
		}
		return usage;
	}
	
	@Override
	public List<String> getAliases()
	{
		List<String> list = new ArrayList<>();
		
		list.add( this.getName() );
		
		return list;
	}	
	
	@Override
	public void execute( MinecraftServer server, ICommandSender sender, String[] args )
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

	/* **************************************************************************************************************
	 * Methods - Implement Comparable<ICommand>
	 ************************************************************************************************************** */
	
	@Override
	public int compareTo( ICommand command ) 
	{
		return this.getName().compareTo( command.getName() );
	}

	/* **************************************************************************************************************
	 * Methods - Implement Composite
	 ************************************************************************************************************** */
	
	@Override
	public void addComponent( Command component )
	{
		component.getComponentName().ifPresent( name -> this.commands.put( name, component ) );

		component.setParent( this );
	}

	@Override
	public void addComponents( Collection<Command> components )
	{
		components.forEach(this::addComponent);
	}

	@Override
	public void removeComponent( Command component ) 
	{
		component.getComponentName()
			.filter( this.commands::containsKey )
			.ifPresent( name -> {
				component.setParent( null );
				this.commands.remove( name );
		});
	}

	@Override
	public void removeComponents( Collection<Command> components ) 
	{
		components.forEach(this::removeComponent);
	}
	
	@Override
	public boolean hasComponents() 
	{
		return !this.commands.isEmpty();
	}

	@Override
	public boolean existsComponent( Command component )
	{
		String key = component.getComponentName().orElse( null );

		return this.commands.containsKey( key );
	}

	@Override
	public Collection<Command> getComponents()
	{
		return this.commands.values();
	}

	@Override
	public void clearComponents()
	{
		this.commands.values().forEach( command -> command.setParent( null ) );
		this.commands.clear();
	}
}
