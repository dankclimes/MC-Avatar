// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Slot, AchievementList, Item, EntityPlayer, 
//            ItemStack, IInventory

public class SlotFurnace extends Slot
{

    public SlotFurnace(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k)
    {
        super(iinventory, i, j, k);
        field_27011_d = entityplayer;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }

    public void onPickupFromSlot(ItemStack itemstack)
    {
        itemstack.func_28155_b(field_27011_d.worldObj, field_27011_d);
        if(itemstack.itemID == Item.ingotIron.shiftedIndex)
        {
            field_27011_d.addStat(AchievementList.acquireIron, 1);
        }
        if(itemstack.itemID == Item.fishCooked.shiftedIndex)
        {
            field_27011_d.addStat(AchievementList.cookFish, 1);
        }
        super.onPickupFromSlot(itemstack);
    }

    private EntityPlayer field_27011_d;
}
