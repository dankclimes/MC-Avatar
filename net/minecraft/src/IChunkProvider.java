// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Chunk, IProgressUpdate

public interface IChunkProvider
{

    public abstract boolean chunkExists(int i, int j);

    public abstract Chunk provideChunk(int i, int j);

    public abstract Chunk func_538_d(int i, int j);

    public abstract void populate(IChunkProvider ichunkprovider, int i, int j);

    public abstract boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate);

    public abstract boolean func_532_a();

    public abstract boolean func_536_b();

    public abstract String makeString();
}
