package de.alaoli.games.minecraft.mods.lib.common.ui.util;

/*
 * @// TODO: color pool
 */
public class Colors
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    private Colors() {}

    public static Color factory()
    {
        return factory( Color.DEFAULT );
    }

    public static Color factory( int value )
    {
        return new Color( value );
    }

    public static Color factory( int r, int g, int b )
    {
        return factory( 255, r, g, b );
    }

    public static Color factory( float alpha, int r, int g, int b )
    {
        return factory( Math.round( alpha*255 ), r, g, b );
    }

    public static Color factory( int alpha, int r, int g, int b )
    {
        return new Color( alpha, r, g, b);
    }
}
