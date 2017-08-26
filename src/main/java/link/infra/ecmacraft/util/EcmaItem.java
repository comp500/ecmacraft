package link.infra.ecmacraft.util;

import java.util.List;

import link.infra.ecmacraft.EcmaCraft;
import link.infra.ecmacraft.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EcmaItem extends Item {
	
	private String extraTooltip;

	public EcmaItem(String itemID, String tooltip) {
		super();
		setRegistryName(itemID);
		setUnlocalizedName(EcmaCraft.MODID + "." + itemID);
		setCreativeTab(ModItems.tab);
		extraTooltip = tooltip;
	}
	
	public EcmaItem(String itemID) {
		super();
		setRegistryName(itemID);
		setUnlocalizedName(EcmaCraft.MODID + "." + itemID);
		setCreativeTab(ModItems.tab);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (extraTooltip != null) {
			tooltip.add(extraTooltip);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
}
