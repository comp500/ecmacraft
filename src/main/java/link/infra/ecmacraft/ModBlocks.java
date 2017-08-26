package link.infra.ecmacraft;

import link.infra.ecmacraft.blocks.TestBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	
	@GameRegistry.ObjectHolder("ecmacraft:testblock")
	public static TestBlock testblock;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		
	}

}
