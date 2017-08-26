package link.infra.ecmacraft.blocks;

import link.infra.ecmacraft.util.EcmaBlockOrientable;
import net.minecraft.block.material.Material;

public class TestBlock extends EcmaBlockOrientable {

	public TestBlock() {
		super("testblock", Material.CLAY, true);
	}

}
