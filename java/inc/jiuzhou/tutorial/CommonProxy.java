package inc.jiuzhou.tutorial;

import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	/** 在服务器注册TileEntity,不注册会导致NBT无法正常读取 **/
	public void RegisterTileEntities() {
		GameRegistry.registerTileEntity(GuiBlockTileEntity.class, GuiBlockTileEntity.class.getName().replace("TileEntity", "").replace(".class", ""));
	}
}
