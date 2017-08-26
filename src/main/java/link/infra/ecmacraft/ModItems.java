package link.infra.ecmacraft;

import link.infra.ecmacraft.items.Screwdriver;
import link.infra.ecmacraft.util.EcmaItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	public static final CreativeTabs tab = new CreativeTabs(EcmaCraft.MODID) {
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(ModItems.solderingiron); // TODO change to computer
	    }
	};
	
	@GameRegistry.ObjectHolder("lightcraft:solderingiron")
    public static EcmaItem solderingiron;
	
	@GameRegistry.ObjectHolder("lightcraft:screwdriver")
    public static Screwdriver screwdriver;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		solderingiron.initModel();
		screwdriver.initModel();
	}

}
