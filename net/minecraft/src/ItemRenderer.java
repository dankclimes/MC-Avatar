// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            RenderBlocks, EntityPlayerSP, RenderPlayer, World, 
//            Item, EntityPlayer, MathHelper, InventoryPlayer, 
//            MapItemRenderer, ItemStack, RenderEngine, Material, 
//            EntityLiving, Tessellator, RenderHelper, RenderManager, 
//            Block, ItemMap, BlockFire

public class ItemRenderer
{

    public ItemRenderer(Minecraft minecraft)
    {
        itemToRender = null;
        equippedProgress = 0.0F;
        prevEquippedProgress = 0.0F;
        renderBlocksInstance = new RenderBlocks();
        field_20099_f = -1;
        mc = minecraft;
        field_28131_f = new MapItemRenderer(minecraft.fontRenderer, minecraft.gameSettings, minecraft.renderEngine);
    }

    public void renderItem(EntityLiving entityliving, ItemStack itemstack)
    {
        GL11.glPushMatrix();
        if(itemstack.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType()))
        {
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/terrain.png"));
            renderBlocksInstance.renderBlockOnInventory(Block.blocksList[itemstack.itemID], itemstack.getItemDamage());
        } else
        {
            if(itemstack.itemID < 256)
            {
                GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/terrain.png"));
            } else
            {
                GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/gui/items.png"));
            }
            Tessellator tessellator = Tessellator.instance;
            int i = entityliving.getItemIcon(itemstack);
            float f = ((float)((i % 16) * 16) + 0.0F) / 256F;
            float f1 = ((float)((i % 16) * 16) + 15.99F) / 256F;
            float f2 = ((float)((i / 16) * 16) + 0.0F) / 256F;
            float f3 = ((float)((i / 16) * 16) + 15.99F) / 256F;
            float f4 = 1.0F;
            float f5 = 0.0F;
            float f6 = 0.3F;
            GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
            GL11.glTranslatef(-f5, -f6, 0.0F);
            float f7 = 1.5F;
            GL11.glScalef(f7, f7, f7);
            GL11.glRotatef(50F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(335F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
            float f8 = 0.0625F;
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, f1, f3);
            tessellator.addVertexWithUV(f4, 0.0D, 0.0D, f, f3);
            tessellator.addVertexWithUV(f4, 1.0D, 0.0D, f, f2);
            tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, f1, f2);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1F);
            tessellator.addVertexWithUV(0.0D, 1.0D, 0.0F - f8, f1, f2);
            tessellator.addVertexWithUV(f4, 1.0D, 0.0F - f8, f, f2);
            tessellator.addVertexWithUV(f4, 0.0D, 0.0F - f8, f, f3);
            tessellator.addVertexWithUV(0.0D, 0.0D, 0.0F - f8, f1, f3);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1F, 0.0F, 0.0F);
            for(int j = 0; j < 16; j++)
            {
                float f9 = (float)j / 16F;
                float f13 = (f1 + (f - f1) * f9) - 0.001953125F;
                float f17 = f4 * f9;
                tessellator.addVertexWithUV(f17, 0.0D, 0.0F - f8, f13, f3);
                tessellator.addVertexWithUV(f17, 0.0D, 0.0D, f13, f3);
                tessellator.addVertexWithUV(f17, 1.0D, 0.0D, f13, f2);
                tessellator.addVertexWithUV(f17, 1.0D, 0.0F - f8, f13, f2);
            }

            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            for(int k = 0; k < 16; k++)
            {
                float f10 = (float)k / 16F;
                float f14 = (f1 + (f - f1) * f10) - 0.001953125F;
                float f18 = f4 * f10 + 0.0625F;
                tessellator.addVertexWithUV(f18, 1.0D, 0.0F - f8, f14, f2);
                tessellator.addVertexWithUV(f18, 1.0D, 0.0D, f14, f2);
                tessellator.addVertexWithUV(f18, 0.0D, 0.0D, f14, f3);
                tessellator.addVertexWithUV(f18, 0.0D, 0.0F - f8, f14, f3);
            }

            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            for(int l = 0; l < 16; l++)
            {
                float f11 = (float)l / 16F;
                float f15 = (f3 + (f2 - f3) * f11) - 0.001953125F;
                float f19 = f4 * f11 + 0.0625F;
                tessellator.addVertexWithUV(0.0D, f19, 0.0D, f1, f15);
                tessellator.addVertexWithUV(f4, f19, 0.0D, f, f15);
                tessellator.addVertexWithUV(f4, f19, 0.0F - f8, f, f15);
                tessellator.addVertexWithUV(0.0D, f19, 0.0F - f8, f1, f15);
            }

            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1F, 0.0F);
            for(int i1 = 0; i1 < 16; i1++)
            {
                float f12 = (float)i1 / 16F;
                float f16 = (f3 + (f2 - f3) * f12) - 0.001953125F;
                float f20 = f4 * f12;
                tessellator.addVertexWithUV(f4, f20, 0.0D, f, f16);
                tessellator.addVertexWithUV(0.0D, f20, 0.0D, f1, f16);
                tessellator.addVertexWithUV(0.0D, f20, 0.0F - f8, f1, f16);
                tessellator.addVertexWithUV(f4, f20, 0.0F - f8, f, f16);
            }

            tessellator.draw();
            GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        }
        GL11.glPopMatrix();
    }

    private void addItemEffects(ItemStack itemStack, float x, float y, float z, float rot){
        if(itemStack.getItem().equals(Item.swordFire)){
            addFireEffect(x,y,z, rot);
        }
    }

    class Coords {
        public float x;
        public float y;
        public float z;
    }

    protected Coords getRotationOffset(float x, float y, float z, float xoff, float zoff){
        Coords coords = new Coords();

        float zofffinal;
        float xofffinal;

        float yoff = 0.07f;//vertical


        yoff = zoff * -MathHelper.sin((mc.thePlayer.rotationPitch/180) * 3.141593F);//*MathHelper.sin((rotationPitch/180) * 3.141593F);

        coords.y = yoff + y;

        float hyp = MathHelper.sqrt_float(xoff*xoff + zoff*zoff - yoff*yoff);

        float hyp1 = MathHelper.sqrt_double(zoff*zoff - yoff*yoff);

        float angle = (mc.thePlayer.rotationYaw/180)*3.141593F - MathHelper.acos(hyp1/hyp);

        xofffinal = MathHelper.sin(angle) * -hyp;
        if( xoff > 0){
            coords.x =  x + xofffinal;
        }else{
            coords.x = x - xofffinal;
        }

        zofffinal = MathHelper.cos(angle) * hyp;
        if(zoff > 0){
            coords.z = z + zofffinal;// + (float)motionZ;
        }else{
            coords.z = z - zofffinal;// + (float)motionZ;
        }
        return coords;
    }

    private Coords addViewBob(float x, float y, float z, float f){
        EntityPlayer entityplayer = (EntityPlayer)mc.renderViewEntity;

        float playerrotRads = f;

        float deltaDistWalked = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;// is 0 at full velocity?
        float deltaRotWalked = -(entityplayer.distanceWalkedModified + deltaDistWalked * playerrotRads);//

        float deltaYaw = entityplayer.cameraYaw + (entityplayer.nextCameraYaw - entityplayer.cameraYaw) * playerrotRads;
        float deltapitch = entityplayer.cameraPitch + (entityplayer.nextCameraPitch - entityplayer.cameraPitch) * playerrotRads;

        //translate
        //GL11.glTranslatef(MathHelper.sin(deltaRotWalked * 3.141593F) * deltaYaw * 0.5F, -Math.abs(MathHelper.cos(deltaRotWalked * 3.141593F) * deltaYaw), 0.0F);
        float bobxtrans = MathHelper.sin(deltaRotWalked * 3.141593F) * deltaYaw * 0.5F;
        float bobytrans = -Math.abs(MathHelper.cos(deltaRotWalked * 3.141593F) * deltaYaw);

        x -= bobxtrans;
        y -= bobytrans;

        float x0 = x;
        float y0 = y;
        float z0 = z;

        float rotDeg;
        float rotRad;

        //xz rots
        //rot1 z axis
        //GL11.glRotatef(MathHelper.sin(deltaRotWalked * 3.141593F) * deltaYaw * 3F, 0.0F, 0.0F, 1.0F);
        rotDeg = MathHelper.sin(deltaRotWalked * 3.141593F) * deltaYaw * 3F;
        rotRad = (rotDeg /180) * 3.14593F;

        x = x0 * MathHelper.cos(rotRad) - y0 * MathHelper.sin(rotRad);
        y = x0 * MathHelper.sin(rotRad) + y0 * MathHelper.cos(rotRad);

        //rot2 x axis
        //GL11.glRotatef(Math.abs(MathHelper.cos(deltaRotWalked * 3.141593F - 0.2F) * deltaYaw) * 5F, 1.0F, 0.0F, 0.0F);

        rotDeg = Math.abs(MathHelper.cos(deltaRotWalked * 3.141593F - 0.2F) * deltaYaw) * 5F;
        rotRad = (rotDeg /180) * 3.14593F;

        z = y0 * MathHelper.sin(rotRad) + z0 * MathHelper.cos(rotRad);
        y = y0 * MathHelper.cos(rotRad) - z0 * MathHelper.sin(rotRad);

        //y rot x axis
        //GL11.glRotatef(deltapitch, 1.0F, 0.0F, 0.0F);

        rotDeg = deltapitch;
        rotRad = (rotDeg /180) * 3.14593F;

        z = y0 * MathHelper.sin(rotRad) + z0 * MathHelper.cos(rotRad);
        y = y0 * MathHelper.cos(rotRad) - z0 * MathHelper.sin(rotRad);

        Coords coords = new Coords();

        coords.x = x;
        coords.y = y;
        coords.z = z;

        return  coords;
    }

    private void addFireEffect(float x, float y, float z,float rot){

        float xoff = -0.04f;
        float zoff = -0.04F;

        Coords coords = getRotationOffset(x,y,z,xoff,zoff);
        coords = addViewBob(coords.x, coords.y, coords.z, rot);
        mc.theWorld.spawnParticle("smallFlame", coords.x, coords.y, coords.z, 0.0D, 0.0D, 0.0D);
    }

    public void renderItemInFirstPerson(float f)
    {
        float f1 = prevEquippedProgress + (equippedProgress - prevEquippedProgress) * f;
        EntityPlayerSP entityplayersp = mc.thePlayer;
        float f2 = ((EntityPlayer) (entityplayersp)).prevRotationPitch + (((EntityPlayer) (entityplayersp)).rotationPitch - ((EntityPlayer) (entityplayersp)).prevRotationPitch) * f;
        GL11.glPushMatrix();
        GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(((EntityPlayer) (entityplayersp)).prevRotationYaw + (((EntityPlayer) (entityplayersp)).rotationYaw - ((EntityPlayer) (entityplayersp)).prevRotationYaw) * f, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
        float f3 = mc.theWorld.getLightBrightness(MathHelper.floor_double(((EntityPlayer) (entityplayersp)).posX), MathHelper.floor_double(((EntityPlayer) (entityplayersp)).posY), MathHelper.floor_double(((EntityPlayer) (entityplayersp)).posZ));
        GL11.glColor4f(f3, f3, f3, 1.0F);
        ItemStack itemstack = itemToRender;
        if(itemstack != null && itemstack.itemID == Item.mapItem.shiftedIndex)
        {
            GL11.glPushMatrix();
            float f4 = 0.8F;
            float f7 = entityplayersp.getSwingProgress(f);
            float f10 = MathHelper.sin(f7 * 3.141593F);
            float f13 = MathHelper.sin(MathHelper.sqrt_float(f7) * 3.141593F);
            GL11.glTranslatef(-f13 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(f7) * 3.141593F * 2.0F) * 0.2F, -f10 * 0.2F);
            f7 = (1.0F - f2 / 45F) + 0.1F;
            if(f7 < 0.0F)
            {
                f7 = 0.0F;
            }
            if(f7 > 1.0F)
            {
                f7 = 1.0F;
            }
            f7 = -MathHelper.cos(f7 * 3.141593F) * 0.5F + 0.5F;
            GL11.glTranslatef(0.0F, (0.0F * f4 - (1.0F - f1) * 1.2F - f7 * 0.5F) + 0.04F, -0.9F * f4);
            GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(f7 * -85F, 0.0F, 0.0F, 1.0F);
            GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTextureForDownloadableImage(mc.thePlayer.skinUrl, mc.thePlayer.getEntityTexture()));
            for(f10 = 0; f10 < 2; f10++)
            {
                f13 = f10 * 2 - 1;
                GL11.glPushMatrix();
                GL11.glTranslatef(-0F, -0.6F, 1.1F * (float)f13);
                GL11.glRotatef(-45 * f13, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-90F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(59F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-65 * f13, 0.0F, 1.0F, 0.0F);
                Render render1 = RenderManager.instance.getEntityRenderObject(mc.thePlayer);
                RenderPlayer renderplayer1 = (RenderPlayer)render1;
                float f17 = 1.0F;
                GL11.glScalef(f17, f17, f17);
                renderplayer1.drawFirstPersonHand();
                GL11.glPopMatrix();
            }

            f10 = entityplayersp.getSwingProgress(f);
            f13 = MathHelper.sin(f10 * f10 * 3.141593F);
            float f16 = MathHelper.sin(MathHelper.sqrt_float(f10) * 3.141593F);
            GL11.glRotatef(-f13 * 20F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-f16 * 20F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-f16 * 80F, 1.0F, 0.0F, 0.0F);
            f10 = 0.38F;
            GL11.glScalef(f10, f10, f10);
            GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-1F, -1F, 0.0F);
            f13 = 0.015625F;
            GL11.glScalef(f13, f13, f13);
            mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/misc/mapbg.png"));
            Tessellator tessellator = Tessellator.instance;
            GL11.glNormal3f(0.0F, 0.0F, -1F);
            tessellator.startDrawingQuads();
            byte byte0 = 7;
            tessellator.addVertexWithUV(0 - byte0, 128 + byte0, 0.0D, 0.0D, 1.0D);
            tessellator.addVertexWithUV(128 + byte0, 128 + byte0, 0.0D, 1.0D, 1.0D);
            tessellator.addVertexWithUV(128 + byte0, 0 - byte0, 0.0D, 1.0D, 0.0D);
            tessellator.addVertexWithUV(0 - byte0, 0 - byte0, 0.0D, 0.0D, 0.0D);
            tessellator.draw();
            MapData mapdata = Item.mapItem.func_28012_a(itemstack, mc.theWorld);
            field_28131_f.func_28157_a(mc.thePlayer, mc.renderEngine, mapdata);
            GL11.glPopMatrix();
        } else
        if(itemstack != null)
        {
            GL11.glPushMatrix();
            float f5 = 0.8F;
            float f8 = entityplayersp.getSwingProgress(f);
            float f11 = MathHelper.sin(f8 * 3.141593F);
            float f14 = MathHelper.sin(MathHelper.sqrt_float(f8) * 3.141593F);
            GL11.glTranslatef(-f14 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(f8) * 3.141593F * 2.0F) * 0.2F, -f11 * 0.2F);
            GL11.glTranslatef(0.7F * f5, -0.65F * f5 - (1.0F - f1) * 0.6F, -0.9F * f5);
            GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
            GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
            f8 = entityplayersp.getSwingProgress(f);
            f11 = MathHelper.sin(f8 * f8 * 3.141593F);
            f14 = MathHelper.sin(MathHelper.sqrt_float(f8) * 3.141593F);
            GL11.glRotatef(-f11 * 20F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-f14 * 20F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-f14 * 80F, 1.0F, 0.0F, 0.0F);
            f8 = 0.4F;
            GL11.glScalef(f8, f8, f8);
            if(itemstack.getItem().shouldRotateAroundWhenRendering())
            {
                GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
            }
            renderItem(entityplayersp, itemstack);
            GL11.glPopMatrix();


            //********** my stuff *****************//
            EntityPlayerSP player = mc.thePlayer;
            /*
            float playerrotRads = f;
            EntityPlayer entityplayer = (EntityPlayer)mc.renderViewEntity;

            float deltaDistWalked = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;// is 0 at full velocity?
            float deltaRotWalked = -(entityplayer.distanceWalkedModified + deltaDistWalked * playerrotRads);//

            float deltaYaw = entityplayer.cameraYaw + (entityplayer.nextCameraYaw - entityplayer.cameraYaw) * playerrotRads;
            // float deltapitch = entityplayer.cameraPitch + (entityplayer.nextCameraPitch - entityplayer.cameraPitch) * playerrotRads;

            float bobxoff;
            float bobyoff;

            //***************************  need to figure out these const
            float bobxconst = -0.025F;

            //.65 works in water
            float bobyconst = 0.09F;


            bobyoff = MathHelper.abs(MathHelper.cos(deltaRotWalked * 3.141593F)) * bobyconst;


            if(MathHelper.sin(deltaRotWalked* 3.141593F) > 0){
                bobxoff = Math.abs(MathHelper.cos(deltaRotWalked* 3.141593F)) * bobxconst;
            }else{
                bobxoff = bobxconst - MathHelper.sin(deltaRotWalked* 3.141593F) * bobxconst;
            }

            if(deltaDistWalked == 0){
                bobxoff = 0;
                bobyoff = 0;
            }
             */
            //**************************************************************

            float posx = (float)player.posX;// + (float)player.motionX/2;//- (float) player.motionX * MathHelper.sin((player.prevRotationYaw/180) * 3.141593F);
            float posy = (float)player.posY;
            float posz = (float)player.posZ;// + (float)player.motionZ/2;// + (float)player.motionZ * MathHelper.cos((player.prevRotationYaw/180) * 3.141593F);

            //player.worldObj.spawnParticle("smallFlame",player.posX,player.posY,player.posZ,0.0D,0D,0D);
            //addItemEffects(itemstack,posx,posy,posz,f);

            //**** Need to figure this out
            //float fullv = .07777777777F;

            //float scalev = deltaDistWalked/fullv;

            //posx += bobxoff;// * scalev;
            //posy += bobyoff;
            //posz += bobxoff;// * scalev;


            //posx = bobx;
            //posy = boby;
            //posz = bobz;

            addItemEffects(itemstack,posx,posy,posz,f);
        } else
        {
            GL11.glPushMatrix();
            float f6 = 0.8F;
            float f9 = entityplayersp.getSwingProgress(f);
            float f12 = MathHelper.sin(f9 * 3.141593F);
            float f15 = MathHelper.sin(MathHelper.sqrt_float(f9) * 3.141593F);
            GL11.glTranslatef(-f15 * 0.3F, MathHelper.sin(MathHelper.sqrt_float(f9) * 3.141593F * 2.0F) * 0.4F, -f12 * 0.4F);
            GL11.glTranslatef(0.8F * f6, -0.75F * f6 - (1.0F - f1) * 0.6F, -0.9F * f6);
            GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
            GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
            f9 = entityplayersp.getSwingProgress(f);
            f12 = MathHelper.sin(f9 * f9 * 3.141593F);
            f15 = MathHelper.sin(MathHelper.sqrt_float(f9) * 3.141593F);
            GL11.glRotatef(f15 * 70F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-f12 * 20F, 0.0F, 0.0F, 1.0F);
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTextureForDownloadableImage(mc.thePlayer.skinUrl, mc.thePlayer.getEntityTexture()));
            GL11.glTranslatef(-1F, 3.6F, 3.5F);
            GL11.glRotatef(120F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(200F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-135F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef(5.6F, 0.0F, 0.0F);
            Render render = RenderManager.instance.getEntityRenderObject(mc.thePlayer);
            RenderPlayer renderplayer = (RenderPlayer)render;
            f15 = 1.0F;
            GL11.glScalef(f15, f15, f15);
            renderplayer.drawFirstPersonHand();
            GL11.glPopMatrix();
        }
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        RenderHelper.disableStandardItemLighting();
    }

    public void renderOverlays(float f)
    {
        GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
        if(mc.thePlayer.isBurning())
        {
            int i = mc.renderEngine.getTexture("/terrain.png");
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, i);
            renderFireInFirstPerson(f);
        }
        if(mc.thePlayer.isEntityInsideOpaqueBlock())
        {
            int j = MathHelper.floor_double(mc.thePlayer.posX);
            int l = MathHelper.floor_double(mc.thePlayer.posY);
            int i1 = MathHelper.floor_double(mc.thePlayer.posZ);
            int j1 = mc.renderEngine.getTexture("/terrain.png");
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, j1);
            int k1 = mc.theWorld.getBlockId(j, l, i1);
            if(mc.theWorld.func_28100_h(j, l, i1))
            {
                renderInsideOfBlock(f, Block.blocksList[k1].getBlockTextureFromSide(2));
            } else
            {
                for(int l1 = 0; l1 < 8; l1++)
                {
                    float f1 = ((float)((l1 >> 0) % 2) - 0.5F) * mc.thePlayer.width * 0.9F;
                    float f2 = ((float)((l1 >> 1) % 2) - 0.5F) * mc.thePlayer.height * 0.2F;
                    float f3 = ((float)((l1 >> 2) % 2) - 0.5F) * mc.thePlayer.width * 0.9F;
                    int i2 = MathHelper.floor_float((float)j + f1);
                    int j2 = MathHelper.floor_float((float)l + f2);
                    int k2 = MathHelper.floor_float((float)i1 + f3);
                    if(mc.theWorld.func_28100_h(i2, j2, k2))
                    {
                        k1 = mc.theWorld.getBlockId(i2, j2, k2);
                    }
                }

            }
            if(Block.blocksList[k1] != null)
            {
                renderInsideOfBlock(f, Block.blocksList[k1].getBlockTextureFromSide(2));
            }
        }
        if(mc.thePlayer.isInsideOfMaterial(Material.water))
        {
            int k = mc.renderEngine.getTexture("/misc/water.png");
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, k);
            renderWarpedTextureOverlay(f);
        }
        GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
    }

    private void renderInsideOfBlock(float f, int i)
    {
        Tessellator tessellator = Tessellator.instance;
        float f1 = mc.thePlayer.getEntityBrightness(f);
        f1 = 0.1F;
        GL11.glColor4f(f1, f1, f1, 0.5F);
        GL11.glPushMatrix();
        float f2 = -1F;
        float f3 = 1.0F;
        float f4 = -1F;
        float f5 = 1.0F;
        float f6 = -0.5F;
        float f7 = 0.0078125F;
        float f8 = (float)(i % 16) / 256F - f7;
        float f9 = ((float)(i % 16) + 15.99F) / 256F + f7;
        float f10 = (float)(i / 16) / 256F - f7;
        float f11 = ((float)(i / 16) + 15.99F) / 256F + f7;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(f2, f4, f6, f9, f11);
        tessellator.addVertexWithUV(f3, f4, f6, f8, f11);
        tessellator.addVertexWithUV(f3, f5, f6, f8, f10);
        tessellator.addVertexWithUV(f2, f5, f6, f9, f10);
        tessellator.draw();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void renderWarpedTextureOverlay(float f)
    {
        Tessellator tessellator = Tessellator.instance;
        float f1 = mc.thePlayer.getEntityBrightness(f);
        GL11.glColor4f(f1, f1, f1, 0.5F);
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glBlendFunc(770, 771);
        GL11.glPushMatrix();
        float f2 = 4F;
        float f3 = -1F;
        float f4 = 1.0F;
        float f5 = -1F;
        float f6 = 1.0F;
        float f7 = -0.5F;
        float f8 = -mc.thePlayer.rotationYaw / 64F;
        float f9 = mc.thePlayer.rotationPitch / 64F;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(f3, f5, f7, f2 + f8, f2 + f9);
        tessellator.addVertexWithUV(f4, f5, f7, 0.0F + f8, f2 + f9);
        tessellator.addVertexWithUV(f4, f6, f7, 0.0F + f8, 0.0F + f9);
        tessellator.addVertexWithUV(f3, f6, f7, f2 + f8, 0.0F + f9);
        tessellator.draw();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3042 /*GL_BLEND*/);
    }

    private void renderFireInFirstPerson(float f)
    {
        Tessellator tessellator = Tessellator.instance;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glBlendFunc(770, 771);
        float f1 = 1.0F;
        for(int i = 0; i < 2; i++)
        {
            GL11.glPushMatrix();
            int j = Block.fire.blockIndexInTexture + i * 16;
            int k = (j & 0xf) << 4;
            int l = j & 0xf0;
            float f2 = (float)k / 256F;
            float f3 = ((float)k + 15.99F) / 256F;
            float f4 = (float)l / 256F;
            float f5 = ((float)l + 15.99F) / 256F;
            float f6 = (0.0F - f1) / 2.0F;
            float f7 = f6 + f1;
            float f8 = 0.0F - f1 / 2.0F;
            float f9 = f8 + f1;
            float f10 = -0.5F;
            GL11.glTranslatef((float)(-(i * 2 - 1)) * 0.24F, -0.3F, 0.0F);
            GL11.glRotatef((float)(i * 2 - 1) * 10F, 0.0F, 1.0F, 0.0F);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(f6, f8, f10, f3, f5);
            tessellator.addVertexWithUV(f7, f8, f10, f2, f5);
            tessellator.addVertexWithUV(f7, f9, f10, f2, f4);
            tessellator.addVertexWithUV(f6, f9, f10, f3, f4);
            tessellator.draw();
            GL11.glPopMatrix();
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3042 /*GL_BLEND*/);
    }

    public void updateEquippedItem()
    {
        prevEquippedProgress = equippedProgress;
        EntityPlayerSP entityplayersp = mc.thePlayer;
        ItemStack itemstack = ((EntityPlayer) (entityplayersp)).inventory.getCurrentItem();
        ItemStack itemstack1 = itemstack;
        boolean flag = field_20099_f == ((EntityPlayer) (entityplayersp)).inventory.currentItem && itemstack1 == itemToRender;
        if(itemToRender == null && itemstack1 == null)
        {
            flag = true;
        }
        if(itemstack1 != null && itemToRender != null && itemstack1 != itemToRender && itemstack1.itemID == itemToRender.itemID && itemstack1.getItemDamage() == itemToRender.getItemDamage())
        {
            itemToRender = itemstack1;
            flag = true;
        }
        float f = 0.4F;
        float f1 = flag ? 1.0F : 0.0F;
        float f2 = f1 - equippedProgress;
        if(f2 < -f)
        {
            f2 = -f;
        }
        if(f2 > f)
        {
            f2 = f;
        }
        equippedProgress += f2;
        if(equippedProgress < 0.1F)
        {
            itemToRender = itemstack1;
            field_20099_f = ((EntityPlayer) (entityplayersp)).inventory.currentItem;
        }
    }

    public void func_9449_b()
    {
        equippedProgress = 0.0F;
    }

    public void func_9450_c()
    {
        equippedProgress = 0.0F;
    }

    private Minecraft mc;
    private ItemStack itemToRender;
    private float equippedProgress;
    private float prevEquippedProgress;
    private RenderBlocks renderBlocksInstance;
    private MapItemRenderer field_28131_f;
    private int field_20099_f;
}
