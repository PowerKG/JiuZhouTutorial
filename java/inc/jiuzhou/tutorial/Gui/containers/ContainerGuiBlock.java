package inc.jiuzhou.tutorial.Gui.containers;

import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
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
		/* ����Ʒ��GUI�� */
		// ���Զ����TileEntity����
		bindTileEntitySlot();
		// ����ҵı���
		bindPlayerInventory();
	}

	@Override
	/* �Ƿ����ڸ����ڵ���Ʒ����(����õ�������ɶ��) */
	public boolean canInteractWith(EntityPlayer �¼����) {
		return true;
	}

	/* ��TileEntity�����Ĳ۰󶨵�GUI�� */

	private void bindTileEntitySlot() {
		// ����(1)Ϊ�󶨵�������,����(2)Ϊ�۵�ID,����(3)ΪX������,����(4)ΪY������
		addSlotToContainer(new Slot(tile, 0, 48, 21));
		addSlotToContainer(new Slot(tile, 1, 112, 21));
	}

	/* ����ҵı����Ĳ۰󶨵�GUI�� */
	protected void bindPlayerInventory() {
		// �������Ұ�
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 59 + i * 18));
			}
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(player, i, 8 + i * 18, 117));
		}
	}

	@Override
	/* ��������� SHIFT + ����� ������,һ���Ǵ�����Ʒ,����Ϊ�˾���ֱ��д�ɲ��ᷢ���κ�����(return null) */
	public ItemStack transferStackInSlot(EntityPlayer �¼����, int �����ǲ�λ��) {
		return null;
	}

}
