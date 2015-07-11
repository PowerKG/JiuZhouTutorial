package inc.jiuzhou.tutorial.Gui.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/** ������������������ڸ���,������ĸ��ط��Ǹ����ĸ��ط���ɶ�� **/

public class ContainerGuiBlock extends Container {
	// ����һ��GuiBlockTileEntity��ʵ��,֮����������Ʒ�󶨵����������
	private GuiBlockTileEntity tile;
	private InventoryPlayer player;

	private int maxDamage, nowDamage, beforeDamage;

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
		addSlotToContainer(new Slot(tile, 0, 48, 21) {
			@Override
			/* ����ֻ�й��߲��ܷŽ�ȥ */
			public boolean isItemValid(ItemStack p_75214_1_) {
				return p_75214_1_.getMaxDamage() != 0;
			}
		});
		addSlotToContainer(new Slot(tile, 1, 112, 21) {
			@Override
			/* ���øò۲��ܷ��κζ��� */
			public boolean isItemValid(ItemStack p_75214_1_) {
				return false;
			}
		});
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

	/** ����������ͬ��,�������ͺ���,IDһ��Ҫ��ͬ�� **/
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {

		if (par1 == 0) {
			this.tile.maxDamage = par2;
		}
		if (par1 == 1) {
			this.tile.nowDamage = par2;
		}
		if (par1 == 2) {
			this.tile.beforeDamage = par2;
		}
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			if (this.maxDamage != this.tile.maxDamage) {
				icrafting.sendProgressBarUpdate(this, 0, this.tile.maxDamage);
			}
			if (this.nowDamage != this.tile.nowDamage) {
				icrafting.sendProgressBarUpdate(this, 1, this.tile.nowDamage);
			}
			if (this.beforeDamage != this.tile.beforeDamage) {
				icrafting.sendProgressBarUpdate(this, 2, this.tile.beforeDamage);
			}
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.tile.maxDamage);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.tile.nowDamage);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.tile.beforeDamage);
	}
}
