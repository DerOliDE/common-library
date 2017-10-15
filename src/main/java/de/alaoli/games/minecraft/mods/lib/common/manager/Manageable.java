package de.alaoli.games.minecraft.mods.lib.common.manager;

public interface Manageable
{
	void setManageableGroupName(String name);
	String getManageableGroupName();
	boolean hasManageableGroupName();
	
	void setManageableName(String name);
	String getManageableName();
	boolean hasManageableName();
}
