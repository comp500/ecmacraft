package link.infra.ecmacraft.util;

import java.util.List;

import link.infra.ecmacraft.EcmaCraft;
import link.infra.ecmacraft.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EcmaItem extends Item {
	
	private boolean extraTooltip;
	private String itemId;
	private int stackLimit;
	private boolean customStackLimit = false;
	
	public EcmaItem(String itemID, boolean tooltip, int stackAmount) {
		super();
		setRegistryName(itemID);
		setUnlocalizedName(EcmaCraft.MODID + "." + itemID);
		setCreativeTab(ModItems.tab);
		extraTooltip = tooltip;
		itemId = itemID;
		stackLimit = stackAmount;
		customStackLimit = true;
	}
	
	public EcmaItem(String itemID, int stackAmount) {
		super();
		setRegistryName(itemID);
		setUnlocalizedName(EcmaCraft.MODID + "." + itemID);
		setCreativeTab(ModItems.tab);
		itemId = itemID;
		stackLimit = stackAmount;
		customStackLimit = true;
	}

	public EcmaItem(String itemID, boolean tooltip) {
		super();
		setRegistryName(itemID);
		setUnlocalizedName(EcmaCraft.MODID + "." + itemID);
		setCreativeTab(ModItems.tab);
		extraTooltip = tooltip;
		itemId = itemID;
	}
	
	public EcmaItem(String itemID) {
		super();
		setRegistryName(itemID);
		setUnlocalizedName(EcmaCraft.MODID + "." + itemID);
		setCreativeTab(ModItems.tab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (extraTooltip) {
			tooltip.add(I18n.format("tooltip." + EcmaCraft.MODID + ".item." + itemId + ".name"));
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		if (customStackLimit) {
			return stackLimit;
		} else {
			return super.getItemStackLimit(stack);
		}
	}
	
}
