package de.alaoli.games.minecraft.mods.lib.common.comparator;

import net.minecraft.block.Block;

public class BlockComparator 
{
	/********************************************************************************
	 * Attributes
	 ********************************************************************************/
	
	protected Block block;
	protected int blockMetaId = 0;
	protected boolean ignoreMeta = false;
	
	/********************************************************************************
	 * Methods
	 ********************************************************************************/

	public BlockComparator() {}
	
	public BlockComparator( Block block )
	{
		this.block = block;
	}
	
	public BlockComparator( Block block, int metaId )
	{
		this.block = block;
		this.blockMetaId = metaId;
	}

	
	public BlockComparator( Block block, boolean ignoreMeta )
	{
		this.block = block;
		this.ignoreMeta = ignoreMeta;
	}

	/**
	 * 
	 * @TODO GameRegistry
	 *
	public BlockComparator( String name )
	{
		String splitted[] = name.split( ":" );
		
		if( splitted.length < 2 ) { throw new DataException("Minecraft unique identifier requires <modid>:<itemid>[:<metaid>]."); }
		
		this.block = GameRegistry.findBlock( splitted[0], splitted[1] );
		
		GameData.g
		
		if( block == null ) { throw new DataException( "Block not found." ); }
			
		if( splitted.length > 2 )
		{
			if( splitted[2].contains( "*" ) )
			{
				this.ignoreMeta = true;
			}
			else
			{
				this.blockMetaId = Integer.valueOf( splitted[2] );
			}
		}
	}*/
	
	public Block getBlock()
	{
		return this.block;
	}
	
	public int getBlockMetaId()
	{
		return this.blockMetaId;
	}
	
	public boolean ignoreMeta()
	{
		return this.ignoreMeta;
	}
	
	@Override
	public int hashCode() 
	{
		if( this.ignoreMeta )
		{
			return this.block.hashCode();
		}
		else
		{
			if( this.blockMetaId == 0 )
			{
				return this.block.hashCode();
			}
			else
			{
				return this.block.hashCode() + this.blockMetaId;
			}
		}
	}

	@Override
	public boolean equals( Object obj ) 
	{
		if( !(obj instanceof BlockComparator) ) { return false;}
		
		if( this.ignoreMeta )
		{
			return this.block.equals( ((BlockComparator)obj).block );
		}
		else
		{
			if( this.blockMetaId == 0 )
			{
				return this.block.equals( ((BlockComparator)obj).block );
			}
			else
			{
				return this.block.equals( ((BlockComparator)obj).block ) &&
						( this.blockMetaId == ((BlockComparator)obj).blockMetaId );
			}
		}
	}

	/**
	 * 
	 * @TODO GameRegistry
	 *
	@Override
	public String toString() 
	{
		StringBuilder result = new StringBuilder()
			.append( GameRegistry.findUniqueIdentifierFor( this.block ) );
			
		if( this.ignoreMeta )
		{
			result.append( ":*" );
		}
		else
		{
			if( this.blockMetaId == 0 )
			{
				result.append( ":" );
				result.append( this.blockMetaId );
			}
		}		
		return result.toString();
	}*/
}
