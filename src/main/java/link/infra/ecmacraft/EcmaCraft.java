package link.infra.ecmacraft;

import org.apache.logging.log4j.Logger;

import link.infra.ecmacraft.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EcmaCraft.MODID, name = EcmaCraft.MODNAME, version = EcmaCraft.VERSION, useMetadata = true)
public class EcmaCraft {

	public static final String MODID = "ecmacraft";
    public static final String MODNAME = "ECMAcraft";
    public static final String VERSION = "1.0.0.0";
    
    @SidedProxy(clientSide = "link.infra.ecmacraft.proxy.ClientProxy", serverSide = "link.infra.ecmacraft.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static EcmaCraft instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
