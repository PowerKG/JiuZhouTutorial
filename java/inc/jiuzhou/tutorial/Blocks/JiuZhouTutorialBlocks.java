package inc.jiuzhou.tutorial.Blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

public class JiuZhouTutorialBlocks {
	// 创建方块实例并且给赋值
	public static final GuiBlock GuiBlock = new GuiBlock(Material.rock);

	public static void init() {
		/* 注册方块,到实战开发后我会提供好快捷注册库 */
		GameRegistry.registerBlock(GuiBlock, GuiBlock.getUnlocalizedName());
	}
}
