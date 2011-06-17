package net.minecraft.src;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: ndelmas
 * Date: Jun 16, 2011
 * Time: 4:43:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class BlockRadiantDirt extends Block{

    public BlockRadiantDirt(int i, int j){
        super(i, j, Material.ground);
        slipperiness = 0.8F;
    }

    public int quantityDropped(Random random)
    {
        return 2 + random.nextInt(3);
    }
}
