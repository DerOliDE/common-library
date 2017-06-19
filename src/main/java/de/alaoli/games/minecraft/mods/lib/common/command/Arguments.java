package de.alaoli.games.minecraft.mods.lib.common.command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class Arguments 
{
	private Queue<String> args;
	
	public final MinecraftServer server;
	public final ICommandSender sender; 
	public final boolean senderIsEntityPlayer; 
	public final boolean senderIsOP;
	
	public Arguments( MinecraftServer server, ICommandSender sender, String[] args )
	{
		this.server = server;
		this.sender = sender;
		this.senderIsEntityPlayer = sender instanceof EntityPlayer;
		this.senderIsOP = sender.canCommandSenderUseCommand( 2, "yadm" );
		
		this.args = new LinkedList<>( Arrays.asList( args ) );
	}

	/**********************************************************************
	 * Method - Arguments 
	 **********************************************************************/
	
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
		String next = this.args.remove();

		return next;
	}
	
	/********************************************************************************
	 * Common
	 ********************************************************************************/
	
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
