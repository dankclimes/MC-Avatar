// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            BlockPressurePlate

public enum EnumMobType
{
    everything,
    mobs,
    players;
/*
    public static EnumMobType[] values()
    {
        return (EnumMobType[])d.clone();
    }

    public static EnumMobType valueOf(String s)
    {
        return (EnumMobType)Enum.valueOf(net.minecraft.src.EnumMobType.class, s);
    }

    private EnumMobType(String s, int i)
    {
        super(s, i);
    }

    public static final EnumMobType everything;
    public static final EnumMobType mobs;
    public static final EnumMobType players;
    private static final EnumMobType d[]; /* synthetic field */
/*
    static 
    {
        everything = new EnumMobType("everything", 0);
        mobs = new EnumMobType("mobs", 1);
        players = new EnumMobType("players", 2);
        d = (new EnumMobType[] {
            everything, mobs, players
        });
    }
*/
}
