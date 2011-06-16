// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Slot, AchievementList, Item, EntityPlayer, 
//            ItemStack, IInventory, Block

public class SlotCrafting extends Slot
{

    public SlotCrafting(EntityPlayer entityplayer, IInventory iinventory, IInventory iinventory1, int i, int j, int k)
    {
        super(iinventory1, i, j, k);
        field_25015_e = entityplayer;
        craftMatrix = iinventory;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }

    public void onPickupFromSlot(ItemStack itemstack)
    {
        itemstack.func_28155_b(field_25015_e.worldObj, field_25015_e);
        if(itemstack.itemID == Block.workbench.blockID)
        {
            field_25015_e.addStat(AchievementList.buildWorkBench, 1);
        } else
        if(itemstack.itemID == Item.pickaxeWood.shiftedIndex)
        {
            field_25015_e.addStat(AchievementList.buildPickaxe, 1);
        } else
        if(itemstack.itemID == Block.stoneOvenIdle.blockID)
        {
            field_25015_e.addStat(AchievementList.buildFurnace, 1);
        } else
        if(itemstack.itemID == Item.hoeWood.shiftedIndex)
        {
            field_25015_e.addStat(AchievementList.buildHoe, 1);
        } else
        if(itemstack.itemID == Item.bread.shiftedIndex)
        {
            field_25015_e.addStat(AchievementList.makeBread, 1);
        } else
        if(itemstack.itemID == Item.cake.shiftedIndex)
        {
            field_25015_e.addStat(AchievementList.bakeCake, 1);
        } else
        if(itemstack.itemID == Item.pickaxeStone.shiftedIndex)
        {
            field_25015_e.addStat(AchievementList.buildBetterPickaxe, 1);
        } else
        if(itemstack.itemID == Item.swordWood.shiftedIndex)
        {
            field_25015_e.addStat(AchievementList.buildSword, 1);
        }
        for(int i = 0; i < craftMatrix.getSizeInventory(); i++)
        {
            ItemStack itemstack1 = craftMatrix.getStackInSlot(i);
            if(itemstack1 == null)
            {
                continue;
            }
            craftMatrix.decrStackSize(i, 1);
            if(itemstack1.getItem().hasContainerItem())
            {
                craftMatrix.setInventorySlotContents(i, new ItemStack(itemstack1.getItem().getContainerItem()));
            }
        }

    }

    private final IInventory craftMatrix;
    private EntityPlayer field_25015_e;
}
