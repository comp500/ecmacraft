package link.infra.ecmacraft.proxy;

import link.infra.ecmacraft.blocks.TestBlock;
import link.infra.ecmacraft.blocks.TestBlockGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	if (ID == TestBlock.GUI_ID) {
        	return new TestBlockGui();
        }
        return null;
    }
}
