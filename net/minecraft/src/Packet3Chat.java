// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet3Chat extends Packet
{

    public Packet3Chat()
    {
    }

    public Packet3Chat(String s)
    {
        if(s.length() > 119)
        {
            s = s.substring(0, 119);
        }
        message = s;
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException
    {
        message = func_27048_a(datainputstream, 119);
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException
    {
        func_27049_a(message, dataoutputstream);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleChat(this);
    }

    public int getPacketSize()
    {
        return message.length();
    }

    public String message;
}
