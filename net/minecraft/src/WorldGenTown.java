package net.minecraft.src;

import javax.security.auth.kerberos.KerberosKey;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: boosh
 * Date: 6/21/11
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorldGenTown extends WorldGenerator{

    private static final int BASE_HEIGHT = 64;
    private static final int CHUNK_HEIGHT = 128;
    private static final int CHUNK_WIDTH = 16;

    private static final int AIR = 0;

    public WorldGenTown(){
    }

    private void genMarker(World world, int i, int j, int k){
        for(int jj = 64; jj < CHUNK_HEIGHT - 11; jj++){

            world.setBlock(i,jj,k,Block.stone.blockID);
        }
        int i0;
        int k0;
        int radius = 5;
        for(int jj = CHUNK_HEIGHT - 10; jj < CHUNK_HEIGHT; jj++){
            for(int ii = -radius; ii < radius; ii++){
                i0 = i + ii;
                for(int kk = -radius; kk < radius; kk++){
                    k0 = k + kk;
                    world.setBlock(i0,jj,k0,Block.glowStone.blockID);
                }
            }
        }
    }

    public boolean generate(World world, Random random, int i, int j, int k){
        boolean doGen = true;

        int j0 = world.findTopSolidBlock(i,k);
        int blockID = world.getBlockId(i,j0,k);
        if(blockID == Block.waterStill.blockID || blockID == Block.waterMoving.blockID || blockID == Block.lavaStill.blockID || blockID == Block.lavaMoving.blockID){
            doGen = false;
        }

        if(doGen) doGenerate(world,random,i,j,k);


        return true;
    }

    public void doGenerate(World world, Random random, int i, int j, int k){

        //create clearing
        int radius = 25;

        int i0;
        int j0;
        int k0;

        for(int ii = -radius; ii < radius; ii++){
            i0 = i + ii;
            for(int kk = -radius; kk < radius; kk++){
                k0 = k + kk;

                //set base

                world.setBlock(i0,BASE_HEIGHT-1,k0, Block.dirt.blockID);

                for(int jj = BASE_HEIGHT; jj < CHUNK_HEIGHT; jj++){
                    j0 = jj;

                    world.setBlock(i0,j0,k0,AIR);
                }
            }
        }

        //make buildings
        genHouse(world,i + 10,BASE_HEIGHT,k + 10);

        //add residents

        //resident will despawn because of distance from player... needs to be done when nearing the town in populate
        //genQuestGiver(world,i + 10,BASE_HEIGHT,k + 12,0,0);

        //marker
        genMarker(world,i,BASE_HEIGHT,k);
    }

    private void genHouse(World world, int i, int j, int k){

                int edgeMat = Block.stairSingle.blockID;

        int width = 6;
        int length = 8;
        int height = 6;

        int i0 = i;
        int j0 = j;
        int k0 = k;


        //walls
        i0++;
        i0++;

        while(i0 < i + length/2 + 1){

            genVert(world,i0,j0,k0,height,Block.planks.blockID);

            //roof
            world.setBlock(i0,j0 + height,k0,edgeMat);

            i0++;
        }

        while(k0 < k + width + 2){
            genVert(world,i0,j0,k0,height,Block.planks.blockID);
            world.setBlock(i0,j0 + height,k0,edgeMat);

            k0++;
        }

        while(i0 > i - length/2 - 1){
            genVert(world,i0,j0,k0,height,Block.planks.blockID);
            world.setBlock(i0,j0 + height,k0,edgeMat);

            i0--;
        }

        while(k0 > k){
            genVert(world,i0,j0,k0,height,Block.planks.blockID);
            world.setBlock(i0,j0 + height,k0,edgeMat);

            k0--;
        }

        while(i0 < i-2){
            genVert(world,i0,j0,k0,height,Block.planks.blockID);
            world.setBlock(i0,j0 + height,k0,edgeMat);

            i0++;
        }

        //roof


        i0 = i;
        j0 = j + height;
        k0 = k;

        genVert(world,i0,j + 3,k0,height-3,Block.planks.blockID);

        world.setBlock(i0,j0,k0,edgeMat);

        genVert(world,i0 + 1,j + 3,k0,height-3,Block.planks.blockID);

        world.setBlock(i0 + 1,j0,k0,edgeMat);

        genVert(world,i0-1,j + 3,k0,height-3,Block.planks.blockID);

        world.setBlock(i0 - 1,j0,k0,edgeMat);

        genVert(world,i0-2,j + 3,k0,height-3,Block.planks.blockID);

        world.setBlock(i0 - 2,j0,k0,edgeMat);


        k0++;
        i0 += length/2;

        while(i0 > i - length/2 - 1){
            while(k0 < k + width + 2){
                world.setBlock(i0,j0,k0,Block.brick.blockID);
                k0++;
            }
            k0 = k + 1;
            i0--;
        }

        //door
        world.setBlock(i + 2,j + 2,k-1,Block.torchWood.blockID);
        world.setBlock(i - 3,j + 2,k-1,Block.torchWood.blockID);

        //window

        i0 = i + length/2 - 1;
        j0 = j + 1;
        k0 = k + width + 2;

        while(i0 > i - length/2 + 1){
            genVert(world,i0,j0,k0,3,AIR);
            i0--;
        }

    }

    private void genVert(World world, int i,int j,int k, int h, int block){
        for(int jj = j; jj < j + h; jj++){
            world.setBlock(i,jj,k,block);
        }
    }

    private void genQuestGiver(World world, int i, int j, int k, int rotYaw, int rotPitch){
            EntityQuestGiver entityQuestGiver = new EntityQuestGiver(world);
            entityQuestGiver.setLocationAndAngles(i, j, k, rotYaw, rotPitch);
            world.entityJoinedWorld(entityQuestGiver);
    }
}
