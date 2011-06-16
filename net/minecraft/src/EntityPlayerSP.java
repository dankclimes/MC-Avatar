// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            EntityPlayer, MouseFilter, EffectRenderer, EntityPickupFX, 
//            AchievementList, AxisAlignedBB, World, GuiDispenser, 
//            Session, GuiChest, MathHelper, InventoryPlayer, 
//            NBTTagCompound, Achievement, GuiCrafting, GuiFurnace, 
//            MovementInput, GuiIngame, StatBase, GuiAchievement, 
//            StatFileWriter, GuiEditSign, SoundManager, TileEntitySign, 
//            IInventory, TileEntityFurnace, TileEntityDispenser, Entity

public class EntityPlayerSP extends EntityPlayer
{

    public EntityPlayerSP(Minecraft minecraft, World world, Session session, int i)
    {
        super(world);
        field_21903_bJ = new MouseFilter();
        field_21904_bK = new MouseFilter();
        field_21902_bL = new MouseFilter();
        mc = minecraft;
        dimension = i;
        if(session != null && session.username != null && session.username.length() > 0)
        {
            skinUrl = (new StringBuilder()).append("http://s3.amazonaws.com/MinecraftSkins/").append(session.username).append(".png").toString();
        }
        username = session.username;
    }

    public void moveEntity(double d, double d1, double d2)
    {
        super.moveEntity(d, d1, d2);
    }

    public void updatePlayerActionState()
    {
        super.updatePlayerActionState();
        moveStrafing = movementInput.moveStrafe;
        moveForward = movementInput.moveForward;
        isJumping = movementInput.jump;
    }

    public void onLivingUpdate()
    {
        if(!mc.statFileWriter.func_27183_a(AchievementList.openInventory))
        {
            mc.guiAchievement.queueAchievementInformation(AchievementList.openInventory);
        }
        prevTimeInPortal = timeInPortal;
        if(inPortal)
        {
            if(!worldObj.multiplayerWorld && ridingEntity != null)
            {
                mountEntity(null);
            }
            if(mc.currentScreen != null)
            {
                mc.displayGuiScreen(null);
            }
            if(timeInPortal == 0.0F)
            {
                mc.sndManager.playSoundFX("portal.trigger", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
            }
            timeInPortal += 0.0125F;
            if(timeInPortal >= 1.0F)
            {
                timeInPortal = 1.0F;
                if(!worldObj.multiplayerWorld)
                {
                    field_28024_y = 10;
                    mc.sndManager.playSoundFX("portal.travel", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
                    mc.usePortal();
                }
            }
            inPortal = false;
        } else
        {
            if(timeInPortal > 0.0F)
            {
                timeInPortal -= 0.05F;
            }
            if(timeInPortal < 0.0F)
            {
                timeInPortal = 0.0F;
            }
        }
        if(field_28024_y > 0)
        {
            field_28024_y--;
        }
        movementInput.updatePlayerMoveState(this);
        if(movementInput.sneak && ySize < 0.2F)
        {
            ySize = 0.2F;
        }
        func_28014_c(posX - (double)width * 0.34999999999999998D, boundingBox.minY + 0.5D, posZ + (double)width * 0.34999999999999998D);
        func_28014_c(posX - (double)width * 0.34999999999999998D, boundingBox.minY + 0.5D, posZ - (double)width * 0.34999999999999998D);
        func_28014_c(posX + (double)width * 0.34999999999999998D, boundingBox.minY + 0.5D, posZ - (double)width * 0.34999999999999998D);
        func_28014_c(posX + (double)width * 0.34999999999999998D, boundingBox.minY + 0.5D, posZ + (double)width * 0.34999999999999998D);
        super.onLivingUpdate();
    }

    public void resetPlayerKeyState()
    {
        movementInput.resetKeyState();
    }

    public void handleKeyPress(int i, boolean flag)
    {
        movementInput.checkKeyForMovementInput(i, flag);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("Score", score);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        score = nbttagcompound.getInteger("Score");
    }

    public void func_20059_m()
    {
        super.func_20059_m();
        mc.displayGuiScreen(null);
    }

    public void displayGUIEditSign(TileEntitySign tileentitysign)
    {
        mc.displayGuiScreen(new GuiEditSign(tileentitysign));
    }

    public void displayGUIChest(IInventory iinventory)
    {
        mc.displayGuiScreen(new GuiChest(inventory, iinventory));
    }

    public void displayWorkbenchGUI(int i, int j, int k)
    {
        mc.displayGuiScreen(new GuiCrafting(inventory, worldObj, i, j, k));
    }

    public void displayGUIFurnace(TileEntityFurnace tileentityfurnace)
    {
        mc.displayGuiScreen(new GuiFurnace(inventory, tileentityfurnace));
    }

    public void displayGUIDispenser(TileEntityDispenser tileentitydispenser)
    {
        mc.displayGuiScreen(new GuiDispenser(inventory, tileentitydispenser));
    }

    public void onItemPickup(Entity entity, int i)
    {
        mc.effectRenderer.addEffect(new EntityPickupFX(mc.theWorld, entity, this, -0.5F));
    }

    public int getPlayerArmorValue()
    {
        return inventory.getTotalArmorValue();
    }

    public void sendChatMessage(String s)
    {
    }

    public boolean isSneaking()
    {
        return movementInput.sneak && !sleeping;
    }

    public void setHealth(int i)
    {
        int j = health - i;
        if(j <= 0)
        {
            health = i;
            if(j < 0)
            {
                field_9306_bj = field_9366_o / 2;
            }
        } else
        {
            field_9346_af = j;
            prevHealth = health;
            field_9306_bj = field_9366_o;
            damageEntity(j);
            hurtTime = maxHurtTime = 10;
        }
    }

    public void respawnPlayer()
    {
        mc.respawn(false, 0);
    }

    public void func_6420_o()
    {
    }

    public void addChatMessage(String s)
    {
        mc.ingameGUI.func_22064_c(s);
    }

    public void addStat(StatBase statbase, int i)
    {
        if(statbase == null)
        {
            return;
        }
        if(statbase.func_25067_a())
        {
            Achievement achievement = (Achievement)statbase;
            if(achievement.parentAchievement == null || mc.statFileWriter.func_27183_a(achievement.parentAchievement))
            {
                if(!mc.statFileWriter.func_27183_a(achievement))
                {
                    mc.guiAchievement.queueTakenAchievement(achievement);
                }
                mc.statFileWriter.func_25100_a(statbase, i);
            }
        } else
        {
            mc.statFileWriter.func_25100_a(statbase, i);
        }
    }

    private boolean func_28027_d(int i, int j, int k)
    {
        return worldObj.func_28100_h(i, j, k);
    }

    protected boolean func_28014_c(double d, double d1, double d2)
    {
        int i = MathHelper.floor_double(d);
        int j = MathHelper.floor_double(d1);
        int k = MathHelper.floor_double(d2);
        double d3 = d - (double)i;
        double d4 = d2 - (double)k;
        if(func_28027_d(i, j, k) || func_28027_d(i, j + 1, k))
        {
            boolean flag = !func_28027_d(i - 1, j, k) && !func_28027_d(i - 1, j + 1, k);
            boolean flag1 = !func_28027_d(i + 1, j, k) && !func_28027_d(i + 1, j + 1, k);
            boolean flag2 = !func_28027_d(i, j, k - 1) && !func_28027_d(i, j + 1, k - 1);
            boolean flag3 = !func_28027_d(i, j, k + 1) && !func_28027_d(i, j + 1, k + 1);
            byte byte0 = -1;
            double d5 = 9999D;
            if(flag && d3 < d5)
            {
                d5 = d3;
                byte0 = 0;
            }
            if(flag1 && 1.0D - d3 < d5)
            {
                d5 = 1.0D - d3;
                byte0 = 1;
            }
            if(flag2 && d4 < d5)
            {
                d5 = d4;
                byte0 = 4;
            }
            if(flag3 && 1.0D - d4 < d5)
            {
                double d6 = 1.0D - d4;
                byte0 = 5;
            }
            float f = 0.1F;
            if(byte0 == 0)
            {
                motionX = -f;
            }
            if(byte0 == 1)
            {
                motionX = f;
            }
            if(byte0 == 4)
            {
                motionZ = -f;
            }
            if(byte0 == 5)
            {
                motionZ = f;
            }
        }
        return false;
    }

    public MovementInput movementInput;
    protected Minecraft mc;
    private MouseFilter field_21903_bJ;
    private MouseFilter field_21904_bK;
    private MouseFilter field_21902_bL;
}
