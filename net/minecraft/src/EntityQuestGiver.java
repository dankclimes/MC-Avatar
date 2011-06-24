package net.minecraft.src;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: boosh
 * Date: 6/22/11
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityQuestGiver extends EntityZombie{


    public EntityQuestGiver(World world)
    {
        super(world);
        angerLevel = 0;
        randomSoundDelay = 0;
        texture = "/mob/wood_elf.png";
        moveSpeed = 0.1F;
        attackStrength = 5;
        isImmuneToFire = true;
    }

    public void onUpdate()
    {
        moveSpeed = playerToAttack == null ? 0.1F : 0.95F;
        /*if(randomSoundDelay > 0 && --randomSoundDelay == 0)
        {
            worldObj.playSoundAtEntity(this, "mob.zombiepig.zpigangry", getSoundVolume() * 2.0F, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }*/
        super.onUpdate();
    }

    public boolean getCanSpawnHere()
    {
        //return worldObj.difficultySetting > 0 && worldObj.checkIfAABBIsClear(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.getIsAnyLiquid(boundingBox);
        return  worldObj.checkIfAABBIsClear(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.getIsAnyLiquid(boundingBox);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Anger", (short)angerLevel);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        angerLevel = nbttagcompound.getShort("Anger");
    }

    protected Entity findPlayerToAttack()
    {
        return null;
        /*if(angerLevel == 0)
        {
            return null;
        } else
        {
            return super.findPlayerToAttack();
        }*/
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    public boolean attackEntityFrom(Entity entity, int i)
    {
        if(entity instanceof EntityPlayer)
        {
            //TODO give quest


            //becomeAngryAt(entity);
        }
        return super.attackEntityFrom(entity, i);
    }

    private void becomeAngryAt(Entity entity)
    {
        playerToAttack = entity;
        angerLevel = 400 + rand.nextInt(400);
        randomSoundDelay = rand.nextInt(40);
    }

    protected String getLivingSound()
    {
        return "mob.zombiepig.zpig";
    }

    protected String getHurtSound()
    {
        return "mob.zombiepig.zpighurt";
    }

    protected String getDeathSound()
    {
        return "mob.zombiepig.zpigdeath";
    }

    protected int getDropItemId()
    {
        return Item.porkCooked.shiftedIndex;
    }

    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    private int angerLevel;
    private int randomSoundDelay;
    private static final ItemStack defaultHeldItem;

    static
    {
        defaultHeldItem = new ItemStack(Item.swordGold, 1);
    }
}
