package inc.jiuzhou.tutorial.BlockTileEntiies;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/* 这里为GuiBlock的TileEntity,继承于TileEntity,因为可以存储放置东西,所以接口为IInventory */
public class GuiBlockTileEntity extends TileEntity implements IInventory {
	/* 这个是容器内的现有的所有物品,[数字]所对应的物品为自己设置的 */
	private ItemStack[] inventory;

	/* 设置总共能装下多少物品 */
	private int INVENTORY_SIZE;

	/** 记录可以修复物品的最大耐久和目前耐久,若是-1则说明没有可以修复的物品 ,第三个变量则是用来校对是否替换了物品防止玩家刷修复 **/
	public int maxDamage = -1, nowDamage = -1, beforeDamage = -1;

	/* 初始化储存的ItemStack的inventory大小 */
	public GuiBlockTileEntity() {
		/* 这里因为我的Gui只有两个自定义的格子,所以把大小设置为2,也可以直接赋值 */
		INVENTORY_SIZE = 2;
		inventory = new ItemStack[INVENTORY_SIZE];
	}

	/** 每当实体更新的时候 (每tick更新一次,1tick = 1/20s) **/
	/**
	 * 修复方法: 每tick时检测一下修复槽里是否有东西,有的话就判断是否能被修复,若能被修复就获取最大耐久,和现在耐久
	 * 然后在GUI绘制修复进度,每tick修复1的耐久,当修复完成后,在右边槽放置修复好的,修复槽则设为空
	 * **/
	public void updateEntity() {
		// 若修复槽为空则执行相应判断
		if (inventory[0] == null) {
			// 如果修复槽为空且变量没被重置的话将变量重置后退出
			if (maxDamage != -1 || nowDamage != -1) {
				maxDamage = -1;
				nowDamage = -1;
				beforeDamage = -1;
			}
			return;
		} else {

			// 判断成品槽是否有物品
			if (inventory[1] != null)
				return;

		}
		// 若没有在修复东西
		if (maxDamage == -1 || nowDamage == -1) {
			// 若该物品最大的耐久是0的话就说明这不是可以修复的工具
			if (inventory[0].getMaxDamage() == 0)
				return;
			// 如果物品的当前耐久等于最大耐久就说明不用修复
			if (inventory[0].getMaxDamage() == inventory[0].getMaxDamage() - inventory[0].getItemDamage())
				return;
			// 相应为数值赋值
			maxDamage = inventory[0].getMaxDamage();
			nowDamage = maxDamage - inventory[0].getItemDamage();
			beforeDamage = inventory[0].getItemDamage();
			// 执行本方法进行判断
			updateEntity();
			return;
		} else {
			// 校对是否更换了物品
			if (inventory[0].getItemDamage() == beforeDamage ? inventory[0].getMaxDamage() == maxDamage : false) {
				// 若通过校验则增加目前的修复量
				++nowDamage;
				// 判断是否修复完成
				if (nowDamage == maxDamage) {
					// 若修复完成则减少对应物品,然后在修复好的增加相应物品

					// 复印这个物品且修复耐久
					ItemStack item = inventory[0];
					// 转移位置判断
					if (inventory[0].stackSize - 1 == 0) {
						// 若修复的物品数量只有一个则把该位置设置为空
						inventory[0] = null;
					} else {
						// 若修复的物品数量不止一个则减去他的一个数量
						--inventory[0].stackSize;
					}

					// 放置物品判断
					if (inventory[1] == null) {
						// 将之前已经修复好的复制品放进去
						item.setItemDamage(0);
						inventory[1] = item;
					} else {
						// 增加那个物品的数量
						++inventory[1].stackSize;
					}
					return;
				}
			} else {
				// 若玩家更换了物品则重置所有变量
				maxDamage = -1;
				nowDamage = -1;
				beforeDamage = -1;
				// 执行本方法重新赋值
				updateEntity();
			}
			return;
		}

	}

	/**
	 * 以下为NBT的读写
	 * 
	 * 需要注意的是,在此之前一定要注册好这个TileEntity,否则会导致读写失败!
	 * 同时数值什么的也要进行在服务器的同步,否则会导致数值读取不正确,出现各种问题
	 * */
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		/** 存储物品槽 **/

		// 遍历inventory[]里的所有物品并且写到NBTTAG里
		for (int a = 0; a < this.INVENTORY_SIZE; a++) {
			NBTTagCompound tag = new NBTTagCompound();
			// 判断是否有物品
			if (inventory[a] != null) {
				// 有的话写进去1
				tag = inventory[a].writeToNBT(tag);
			}
			// 没有的话同样也写个null进去以防读取报错
			nbt.setTag("Inventory." + a, tag);
		}

		/** 存储进程变量 **/
		// 分别存储所有变量
		nbt.setInteger("TileVar.ItemMaxDamage", this.maxDamage);
		nbt.setInteger("TileVar.ItemBeforeDamage", this.beforeDamage);
		nbt.setInteger("TileVar.ItemNowDamage", this.nowDamage);
		return;
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		/** 读取物品槽 **/

		// 遍历物品位置
		for (int a = 0; a < INVENTORY_SIZE; a++) {
			// 获取那个槽的物品
			NBTBase tagbase = nbt.getTag("Inventory." + a);
			// 如果获取到的是NBT标签说明这是个物品
			if (tagbase instanceof NBTTagCompound) {
				// 将物品写入指定槽
				inventory[a] = ItemStack.loadItemStackFromNBT((NBTTagCompound) tagbase);
			}
			continue;
		}
		/** 读取储存的参数 **/

		this.maxDamage = nbt.getInteger("TileVar.ItemMaxDamage");
		this.beforeDamage = nbt.getInteger("TileVar.ItemBeforeDamage");
		this.nowDamage = nbt.getInteger("TileVar.ItemNowDamage");
		return;
	}

	/* 用于判断是不是正在修复武器中 */
	public boolean isRepairing() {
		if (this.nowDamage != -1 && this.maxDamage != 0) {
			return true;
		} else {
			return false;
		}
	}

	// 下面都是乱七八糟的它自己补充的方法,现在可以先复制其他的进来
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
