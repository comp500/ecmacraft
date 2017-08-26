package link.infra.ecmacraft.proxy;

import link.infra.ecmacraft.gui.SettingsGui;
import link.infra.ecmacraft.gui.TerminalGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {
	
	public static enum GuiIds { TERMINAL, SETTINGS, ADDON };

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	if (ID == GuiIds.TERMINAL.ordinal()) {
        	return new TerminalGui();
        }
    	if (ID == GuiIds.SETTINGS.ordinal()) {
        	return new SettingsGui();
        }
    	/*if (ID == GuiIds.ADDON.ordinal()) {
        	return new AddonGui();
        }*/
        return null;
    }
}
