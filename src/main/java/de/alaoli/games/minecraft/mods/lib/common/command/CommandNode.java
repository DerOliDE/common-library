package de.alaoli.games.minecraft.mods.lib.common.command;

import de.alaoli.games.minecraft.mods.lib.common.util.Node;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

public interface CommandNode extends Node, ICommand 
{
	/****************************************************************************************************
	 * Method 
	 ****************************************************************************************************/
	
	public boolean hasParent();
	public CommandNode getParent();

	public void sendUsage( ICommandSender sender );
	public void execute( Arguments args );	
}
