// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            PlayerController, Packet7UseEntity, StepSound, EntityPlayerSP, 
//            Container, World, EntityPlayer, Packet15Place, 
//            Packet16BlockItemSwitch, InventoryPlayer, ItemStack, Packet14BlockDig, 
//            RenderGlobal, NetClientHandler, Packet102WindowClick, Entity, 
//            EntityClientPlayerMP, GuiIngame, Block, SoundManager

public class PlayerControllerMP extends PlayerController
{

    public PlayerControllerMP(Minecraft minecraft, NetClientHandler netclienthandler)
    {
        super(minecraft);
        field_9445_c = -1;
        field_9444_d = -1;
        field_9443_e = -1;
        curBlockDamageMP = 0.0F;
        prevBlockDamageMP = 0.0F;
        field_9441_h = 0.0F;
        field_9440_i = 0;
        field_9439_j = false;
        field_1075_l = 0;
        netClientHandler = netclienthandler;
    }

    public void flipPlayer(EntityPlayer entityplayer)
    {
        entityplayer.rotationYaw = -180F;
    }

    public boolean sendBlockRemoved(int i, int j, int k, int l)
    {
        int i1 = mc.theWorld.getBlockId(i, j, k);
        boolean flag = super.sendBlockRemoved(i, j, k, l);
        ItemStack itemstack = mc.thePlayer.getCurrentEquippedItem();
        if(itemstack != null)
        {
            itemstack.func_25191_a(i1, i, j, k, mc.thePlayer);
            if(itemstack.stackSize == 0)
            {
                itemstack.func_1097_a(mc.thePlayer);
                mc.thePlayer.destroyCurrentEquippedItem();
            }
        }
        return flag;
    }

    public void clickBlock(int i, int j, int k, int l)
    {
        if(!field_9439_j || i != field_9445_c || j != field_9444_d || k != field_9443_e)
        {
            netClientHandler.addToSendQueue(new Packet14BlockDig(0, i, j, k, l));
            int i1 = mc.theWorld.getBlockId(i, j, k);
            if(i1 > 0 && curBlockDamageMP == 0.0F)
            {
                Block.blocksList[i1].onBlockClicked(mc.theWorld, i, j, k, mc.thePlayer);
            }
            if(i1 > 0 && Block.blocksList[i1].blockStrength(mc.thePlayer) >= 1.0F)
            {
                sendBlockRemoved(i, j, k, l);
            } else
            {
                field_9439_j = true;
                field_9445_c = i;
                field_9444_d = j;
                field_9443_e = k;
                curBlockDamageMP = 0.0F;
                prevBlockDamageMP = 0.0F;
                field_9441_h = 0.0F;
            }
        }
    }

    public void func_6468_a()
    {
        curBlockDamageMP = 0.0F;
        field_9439_j = false;
    }

    public void sendBlockRemoving(int i, int j, int k, int l)
    {
        if(!field_9439_j)
        {
            return;
        }
        func_730_e();
        if(field_9440_i > 0)
        {
            field_9440_i--;
            return;
        }
        if(i == field_9445_c && j == field_9444_d && k == field_9443_e)
        {
            int i1 = mc.theWorld.getBlockId(i, j, k);
            if(i1 == 0)
            {
                field_9439_j = false;
                return;
            }
            Block block = Block.blocksList[i1];
            curBlockDamageMP += block.blockStrength(mc.thePlayer);
            if(field_9441_h % 4F == 0.0F && block != null)
            {
                mc.sndManager.playSound(block.stepSound.func_1145_d(), (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, (block.stepSound.getVolume() + 1.0F) / 8F, block.stepSound.getPitch() * 0.5F);
            }
            field_9441_h++;
            if(curBlockDamageMP >= 1.0F)
            {
                field_9439_j = false;
                netClientHandler.addToSendQueue(new Packet14BlockDig(2, i, j, k, l));
                sendBlockRemoved(i, j, k, l);
                curBlockDamageMP = 0.0F;
                prevBlockDamageMP = 0.0F;
                field_9441_h = 0.0F;
                field_9440_i = 5;
            }
        } else
        {
            clickBlock(i, j, k, l);
        }
    }

    public void setPartialTime(float f)
    {
        if(curBlockDamageMP <= 0.0F)
        {
            mc.ingameGUI.damageGuiPartialTime = 0.0F;
            mc.renderGlobal.damagePartialTime = 0.0F;
        } else
        {
            float f1 = prevBlockDamageMP + (curBlockDamageMP - prevBlockDamageMP) * f;
            mc.ingameGUI.damageGuiPartialTime = f1;
            mc.renderGlobal.damagePartialTime = f1;
        }
    }

    public float getBlockReachDistance()
    {
        return 4F;
    }

    public void func_717_a(World world)
    {
        super.func_717_a(world);
    }

    public void updateController()
    {
        func_730_e();
        prevBlockDamageMP = curBlockDamageMP;
        mc.sndManager.playRandomMusicIfReady();
    }

    private void func_730_e()
    {
        int i = mc.thePlayer.inventory.currentItem;
        if(i != field_1075_l)
        {
            field_1075_l = i;
            netClientHandler.addToSendQueue(new Packet16BlockItemSwitch(field_1075_l));
        }
    }

    public boolean sendPlaceBlock(EntityPlayer entityplayer, World world, ItemStack itemstack, int i, int j, int k, int l)
    {
        func_730_e();
        netClientHandler.addToSendQueue(new Packet15Place(i, j, k, l, entityplayer.inventory.getCurrentItem()));
        boolean flag = super.sendPlaceBlock(entityplayer, world, itemstack, i, j, k, l);
        return flag;
    }

    public boolean sendUseItem(EntityPlayer entityplayer, World world, ItemStack itemstack)
    {
        func_730_e();
        netClientHandler.addToSendQueue(new Packet15Place(-1, -1, -1, 255, entityplayer.inventory.getCurrentItem()));
        boolean flag = super.sendUseItem(entityplayer, world, itemstack);
        return flag;
    }

    public EntityPlayer createPlayer(World world)
    {
        return new EntityClientPlayerMP(mc, world, mc.session, netClientHandler);
    }

    public void func_6472_b(EntityPlayer entityplayer, Entity entity)
    {
        func_730_e();
        netClientHandler.addToSendQueue(new Packet7UseEntity(entityplayer.entityId, entity.entityId, 1));
        entityplayer.attackTargetEntityWithCurrentItem(entity);
    }

    public void func_6475_a(EntityPlayer entityplayer, Entity entity)
    {
        func_730_e();
        netClientHandler.addToSendQueue(new Packet7UseEntity(entityplayer.entityId, entity.entityId, 0));
        entityplayer.useCurrentItemOnEntity(entity);
    }

    public ItemStack func_27174_a(int i, int j, int k, boolean flag, EntityPlayer entityplayer)
    {
        short word0 = entityplayer.craftingInventory.func_20111_a(entityplayer.inventory);
        ItemStack itemstack = super.func_27174_a(i, j, k, flag, entityplayer);
        netClientHandler.addToSendQueue(new Packet102WindowClick(i, j, k, flag, itemstack, word0));
        return itemstack;
    }

    public void func_20086_a(int i, EntityPlayer entityplayer)
    {
        if(i == -9999)
        {
            return;
        } else
        {
            return;
        }
    }

    private int field_9445_c;
    private int field_9444_d;
    private int field_9443_e;
    private float curBlockDamageMP;
    private float prevBlockDamageMP;
    private float field_9441_h;
    private int field_9440_i;
    private boolean field_9439_j;
    private NetClientHandler netClientHandler;
    private int field_1075_l;
}
