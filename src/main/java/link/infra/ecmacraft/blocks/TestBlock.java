package link.infra.ecmacraft.blocks;

import link.infra.ecmacraft.EcmaCraft;
import link.infra.ecmacraft.ModItems;
import link.infra.ecmacraft.proxy.GuiProxy.GuiIds;
import link.infra.ecmacraft.util.EcmaBlockOrientable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestBlock extends EcmaBlockOrientable implements ITileEntityProvider {

	public TestBlock() {
		super("testblock", Material.CLAY, true);
	}
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TestBlockTile();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
    	// Only execute on the client
        if (!world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof TestBlockTile)) {
            return false;
        }
        if (player.getHeldItemMainhand().isItemEqual(new ItemStack(ModItems.solderingiron))) { // this doesn't work for some reason
        	player.openGui(EcmaCraft.instance, GuiIds.SETTINGS.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
        } else {
        	player.openGui(EcmaCraft.instance, GuiIds.TERMINAL.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
        }
        
        return true;
    }
    

}
