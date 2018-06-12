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
import java.util.List;
import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.common.ModException;
import de.alaoli.games.minecraft.mods.lib.common.util.Component;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public abstract class Command implements Component, ICommand 
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */
	
	@Getter
	@Setter
	private Command parent;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */
	
	public abstract void execute( Arguments argument );

	boolean hasParent()
	{
		return this.parent != null;
	}

	void sendUsage( ICommandSender sender )
	{
		sender.sendMessage( new TextComponentString( this.getUsage( sender ) ) );
	}

	/* **************************************************************************************************************
	 * Method - Implement Component
	 ************************************************************************************************************** */

	@Override
	public Optional<String> getComponentName()
	{
		return Optional.of( this.getName() );
	}

	/* **************************************************************************************************************
	 * Method - Implement Comparable<ICommand>
	 ************************************************************************************************************** */

	@Override
	public int compareTo( ICommand command ) 
	{
		return this.getName().compareTo( command.getName() );
	}

	/* **************************************************************************************************************
	 * Method - Implement ICommand
	 ************************************************************************************************************** */

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
	public List<String> getAliases() 
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