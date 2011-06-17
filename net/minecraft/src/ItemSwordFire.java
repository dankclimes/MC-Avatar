package net.minecraft.src;

/**
 * Created by IntelliJ IDEA.
 * User: boosh
 * Date: 6/15/11
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemSwordFire extends ItemSword{

    public ItemSwordFire(int i, EnumToolMaterial enumtoolmaterial){
        super(i, enumtoolmaterial);

    }

    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }


    public boolean hitEntity(ItemStack itemstack, EntityLiving entityHit, EntityLiving player)
    {
        super.hitEntity(itemstack,entityHit,player);

        entityHit.dealFireDamage(1);

        return true;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        if(l == 0)
        {
            j--;
        }
        if(l == 1)
        {
            j++;
        }
        if(l == 2)
        {
            k--;
        }
        if(l == 3)
        {
            k++;
        }
        if(l == 4)
        {
            i--;
        }
        if(l == 5)
        {
            i++;
        }
        int i1 = world.getBlockId(i, j, k);
        if(i1 == 0)
        {
            //world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            world.setBlockWithNotify(i, j, k, Block.fire.blockID);
        }
        //itemstack.damageItem(1, entityplayer);
        return true;
    }
}
