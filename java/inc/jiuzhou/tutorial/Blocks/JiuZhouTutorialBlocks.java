package inc.jiuzhou.tutorial.Blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

public class JiuZhouTutorialBlocks {
	// ��������ʵ�����Ҹ���ֵ
	public static final GuiBlock GuiBlock = new GuiBlock(Material.rock);

	public static void init() {
		/* ע�᷽��,��ʵս�������һ��ṩ�ÿ��ע��� */
		GameRegistry.registerBlock(GuiBlock, GuiBlock.getUnlocalizedName());
	}
}
