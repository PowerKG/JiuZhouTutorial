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

/** 这个就是容器，作用于格子,比如绑定哪个地方是格子哪个地方是啥的 **/

public class ContainerGuiBlock extends Container {
	// 创建一个GuiBlockTileEntity的实例,之后用来把物品绑定到这个容器中
	private GuiBlockTileEntity tile;
	private InventoryPlayer player;

	private int maxDamage, nowDamage, beforeDamage;

	/* 传入两个Inventory实例,用于分别绑定GUI上的物品到不同的容器里互不干扰 */
	public ContainerGuiBlock(GuiBlockTileEntity GuiBlock的TileEntity容器, InventoryPlayer 玩家的背包容器) {
		tile = GuiBlock的TileEntity容器;
		player = 玩家的背包容器;
		/* 绑定物品到GUI上 */
		// 绑定自定义的TileEntity容器
		bindTileEntitySlot();
		// 绑定玩家的背包
		bindPlayerInventory();
	}

	@Override
	/* 是否能于各种内的物品互动(点击得到，放下啥的) */
	public boolean canInteractWith(EntityPlayer 事件玩家) {
		return true;
	}

	/* 绑定TileEntity容器的槽绑定到GUI上 */

	private void bindTileEntitySlot() {
		// 参数(1)为绑定到的容器,参数(2)为槽的ID,参数(3)为X轴坐标,参数(4)为Y轴坐标
		addSlotToContainer(new Slot(tile, 0, 48, 21) {
			@Override
			/* 限制只有工具才能放进去 */
			public boolean isItemValid(ItemStack p_75214_1_) {
				return p_75214_1_.getMaxDamage() != 0;
			}
		});
		addSlotToContainer(new Slot(tile, 1, 112, 21) {
			@Override
			/* 设置该槽不能放任何东西 */
			public boolean isItemValid(ItemStack p_75214_1_) {
				return false;
			}
		});
	}

	/* 将玩家的背包的槽绑定到GUI上 */
	protected void bindPlayerInventory() {
		// 遍历并且绑定
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
	/* 这个功能是 SHIFT + 鼠标点击 后发生的,一般是传送物品,这里为了尽快直接写成不会发生任何事情(return null) */
	public ItemStack transferStackInSlot(EntityPlayer 事件玩家, int 可能是槽位置) {
		return null;
	}

	/** 服务器数据同步,照着做就好了,ID一定要相同的 **/
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
