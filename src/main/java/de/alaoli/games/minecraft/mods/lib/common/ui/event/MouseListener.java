package de.alaoli.games.minecraft.mods.lib.common.ui.event;

public interface MouseListener extends InputListener 
{
	void mouseEntered( MouseEvent event );
	void mouseExited( MouseEvent event );
	void mouseClicked(MouseEvent event);
}
