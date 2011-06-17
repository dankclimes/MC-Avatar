package net.minecraft.src;

/**
 * Created by IntelliJ IDEA.
 * User: boosh
 * Date: 6/16/11
 * Time: 10:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityExample extends EntityAnimal {

    public EntityExample(World world){
        super(world);
        texture = "/mob/example.png";
        setSize(0.9F, 1.3F);

    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return "mob.example";
    }

    protected String getHurtSound()
    {
        return "mob.example";
    }

    protected String getDeathSound()
    {
        return "mob.example";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected int getDropItemId()
    {
        return 0;
    }

}
