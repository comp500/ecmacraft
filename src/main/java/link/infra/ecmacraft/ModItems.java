package link.infra.ecmacraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	public static final CreativeTabs tab = new CreativeTabs(EcmaCraft.MODID) {
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(Blocks.BRICK_BLOCK); // TODO change to computer
	    }
	};
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		
	}

}
