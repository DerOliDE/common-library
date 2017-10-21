package de.alaoli.games.minecraft.mods.lib.common.ui.util;

public interface Hoverable<T extends Hoverable<T>>
{
    T setHover( boolean hover );
    boolean isHovered();
}
