package de.alaoli.games.minecraft.mods.lib.common.network;

import net.minecraft.nbt.NBTTagCompound;

public interface Packageable 
{
	void writeToNBT(NBTTagCompound tagCompound);
	void readFromNBT(NBTTagCompound tagCompound);
}
