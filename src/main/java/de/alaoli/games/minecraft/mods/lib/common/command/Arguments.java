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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import lombok.Getter;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public final class Arguments
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	private Queue<String> args;

	@Getter private final MinecraftServer server;
	@Getter private final ICommandSender sender;
	@Getter private final boolean isSenderEntityPlayer;
	@Getter private final boolean isSenderOP;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Arguments( MinecraftServer server, ICommandSender sender, Command node, String[] args )
	{
		this.server = server;
		this.sender = sender;
		this.isSenderEntityPlayer = sender instanceof EntityPlayer;
		this.isSenderOP = sender.canUseCommand( 2, node.getName() );
		
		this.args = new LinkedList<>( Arrays.asList( args ) );
	}

	public void add( String arg )
	{
		this.args.add( arg );
	}
	
	public boolean isEmpty()
	{
		return args.isEmpty();
	}
	
	public int size()
	{
		return this.args.size();
	}
	
	public String next()
	{
		return this.args.remove();
	}

	public static boolean isInt( String str )
	{
	    if( str == null ) { return false; }
	    int length = str.length();
	    if( length == 0 ){ return false; }
	    int i = 0;
	    
	    if( str.charAt(0) == '-' )
	    {
            if( length == 1 ) { return false; }
            i = 1;
	    }
	    for( ; i < length; i++ )
	    {
	        if( !Character.isDigit(str.charAt( i ) ) ) { return false; }
	    }
	    return true;
	}
}