package inc.jiuzhou.tutorial.BlockTileEntiies;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/* ����ΪGuiBlock��TileEntity,�̳���TileEntity,��Ϊ���Դ洢���ö���,���Խӿ�ΪIInventory */
public class GuiBlockTileEntity extends TileEntity implements IInventory {
	/* ����������ڵ����е�������Ʒ,[����]����Ӧ����ƷΪ�Լ����õ� */
	private ItemStack[] inventory;

	/* �����ܹ���װ�¶�����Ʒ */
	private int INVENTORY_SIZE;

	/** ��¼�����޸���Ʒ������;ú�Ŀǰ�;�,����-1��˵��û�п����޸�����Ʒ ,������������������У���Ƿ��滻����Ʒ��ֹ���ˢ�޸� **/
	public int maxDamage = -1, nowDamage = -1, beforeDamage = -1;

	/* ��ʼ�������ItemStack��inventory��С */
	public GuiBlockTileEntity() {
		/* ������Ϊ�ҵ�Guiֻ�������Զ���ĸ���,���԰Ѵ�С����Ϊ2,Ҳ����ֱ�Ӹ�ֵ */
		INVENTORY_SIZE = 2;
		inventory = new ItemStack[INVENTORY_SIZE];
	}

	/** ÿ��ʵ����µ�ʱ�� (ÿtick����һ��,1tick = 1/20s) **/
	/**
	 * �޸�����: ÿtickʱ���һ���޸������Ƿ��ж���,�еĻ����ж��Ƿ��ܱ��޸�,���ܱ��޸��ͻ�ȡ����;�,�������;�
	 * Ȼ����GUI�����޸�����,ÿtick�޸�1���;�,���޸���ɺ�,���ұ߲۷����޸��õ�,�޸�������Ϊ��
	 * **/
	public void updateEntity() {
		// ���޸���Ϊ����ִ����Ӧ�ж�
		if (inventory[0] == null) {
			// ����޸���Ϊ���ұ���û�����õĻ����������ú��˳�
			if (maxDamage != -1 || nowDamage != -1) {
				maxDamage = -1;
				nowDamage = -1;
				beforeDamage = -1;
			}
			return;
		} else {

			// �жϳ�Ʒ���Ƿ�����Ʒ
			if (inventory[1] != null)
				return;

		}
		// ��û�����޸�����
		if (maxDamage == -1 || nowDamage == -1) {
			// ������Ʒ�����;���0�Ļ���˵���ⲻ�ǿ����޸��Ĺ���
			if (inventory[0].getMaxDamage() == 0)
				return;
			// �����Ʒ�ĵ�ǰ�;õ�������;þ�˵�������޸�
			if (inventory[0].getMaxDamage() == inventory[0].getMaxDamage() - inventory[0].getItemDamage())
				return;
			// ��ӦΪ��ֵ��ֵ
			maxDamage = inventory[0].getMaxDamage();
			nowDamage = maxDamage - inventory[0].getItemDamage();
			beforeDamage = inventory[0].getItemDamage();
			// ִ�б����������ж�
			updateEntity();
			return;
		} else {
			// У���Ƿ��������Ʒ
			if (inventory[0].getItemDamage() == beforeDamage ? inventory[0].getMaxDamage() == maxDamage : false) {
				// ��ͨ��У��������Ŀǰ���޸���
				++nowDamage;
				// �ж��Ƿ��޸����
				if (nowDamage == maxDamage) {
					// ���޸��������ٶ�Ӧ��Ʒ,Ȼ�����޸��õ�������Ӧ��Ʒ

					// ��ӡ�����Ʒ���޸��;�
					ItemStack item = inventory[0];
					// ת��λ���ж�
					if (inventory[0].stackSize - 1 == 0) {
						// ���޸�����Ʒ����ֻ��һ����Ѹ�λ������Ϊ��
						inventory[0] = null;
					} else {
						// ���޸�����Ʒ������ֹһ�����ȥ����һ������
						--inventory[0].stackSize;
					}

					// ������Ʒ�ж�
					if (inventory[1] == null) {
						// ��֮ǰ�Ѿ��޸��õĸ���Ʒ�Ž�ȥ
						item.setItemDamage(0);
						inventory[1] = item;
					} else {
						// �����Ǹ���Ʒ������
						++inventory[1].stackSize;
					}
					return;
				}
			} else {
				// ����Ҹ�������Ʒ���������б���
				maxDamage = -1;
				nowDamage = -1;
				beforeDamage = -1;
				// ִ�б��������¸�ֵ
				updateEntity();
			}
			return;
		}

	}

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

	public boolean isRepairing() {
		if (this.nowDamage != -1 && this.maxDamage != 0) {
			return true;
		} else {
			return false;
		}
	}
}
