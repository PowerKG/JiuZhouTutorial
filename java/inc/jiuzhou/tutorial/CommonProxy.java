package inc.jiuzhou.tutorial;

import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	/** �ڷ�����ע��TileEntity,��ע��ᵼ��NBT�޷�������ȡ **/
	public void RegisterTileEntities() {
		GameRegistry.registerTileEntity(GuiBlockTileEntity.class, GuiBlockTileEntity.class.getName().replace("TileEntity", "").replace(".class", ""));
	}
}
