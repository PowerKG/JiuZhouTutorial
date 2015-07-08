package inc.jiuzhou.tutorial.BlockTileEntiies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/* ����ΪGuiBlock��TileEntity,�̳���TileEntity,��Ϊ���Դ洢���ö���,���Խӿ�ΪIInventory */
public class GuiBlockTileEntity extends TileEntity implements IInventory {
	/* ����������ڵ����е�������Ʒ,[����]����Ӧ����ƷΪ�Լ����õ� */
	private ItemStack[] inventory;
	/*�����ܹ���װ�¶�����Ʒ*/
	private int INVENTORY_SIZE;

	// ���涼�����߰�������Լ�����ķ���,���ڿ����ȸ��������Ľ���
	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		ItemStack itemStack = getStackInSlot(slotIndex);
		if (itemStack != null) {
			setInventorySlotContents(slotIndex, null);
		}
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
		inventory[slotIndex] = itemStack;
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "GuiBlock";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if (inventory[slot] == null) {
			return null;
		} else {
			return inventory[slot];
		}
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
		ItemStack itemStack = getStackInSlot(slotIndex);
		if (itemStack != null) {
			if (itemStack.stackSize <= decrementAmount) {
				setInventorySlotContents(slotIndex, null);
			} else {
				itemStack = itemStack.splitStack(decrementAmount);
				if (itemStack.stackSize == 0)
					setInventorySlotContents(slotIndex, null);
			}
		}
		return itemStack;
	}

	@Override
	public int getSizeInventory() {
		return INVENTORY_SIZE;
	}

}
