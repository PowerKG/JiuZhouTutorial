package inc.jiuzhou.tutorial.Gui.containers;

import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

/** ������������������ڸ���,������ĸ��ط��Ǹ����ĸ��ط���ɶ�� **/

public class ContainerGuiBlock extends Container {
	// ����һ��GuiBlockTileEntity��ʵ��,֮����������Ʒ�󶨵����������
	private GuiBlockTileEntity tile;
	private InventoryPlayer player;

	/* ��������Inventoryʵ��,���ڷֱ��GUI�ϵ���Ʒ����ͬ�������ﻥ������ */
	public ContainerGuiBlock(GuiBlockTileEntity GuiBlock��TileEntity����, InventoryPlayer ��ҵı�������) {
		tile = GuiBlock��TileEntity����;
		player = ��ҵı�������;
	}

	@Override
	/* �Ƿ����ڸ����ڵ���Ʒ����(����õ�������ɶ��) */
	public boolean canInteractWith(EntityPlayer �¼����) {
		return true;
	}

	@Override
	/* ��������� SHIFT + ����� ������,һ���Ǵ�����Ʒ,����Ϊ�˾���ֱ��д�ɲ��ᷢ���κ�����(return null) */
	public ItemStack transferStackInSlot(EntityPlayer �¼����, int �����ǲ�λ��) {
		return null;
	}

}
