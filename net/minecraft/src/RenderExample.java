package net.minecraft.src;

/**
 * Created by IntelliJ IDEA.
 * User: boosh
 * Date: 6/16/11
 * Time: 10:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class RenderExample extends RenderLiving
{
    public RenderExample(ModelBase modelbase, float f)
    {
        super(modelbase, f);
    }

    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2,
            float f, float f1)
    {
        super.doRenderLiving((EntityExample)entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2,
            float f, float f1)
    {
        doRenderLiving((EntityExample)entity, d, d1, d2, f, f1);
    }

}
