package link.infra.ecmacraft.proxy;

import link.infra.ecmacraft.ModBlocks;
import link.infra.ecmacraft.ModItems;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {

	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
    	ModItems.initModels();
    	ModBlocks.initModels();
    }
}
