package inc.jiuzhou.tutorial.Gui.Blocks;

import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import inc.jiuzhou.tutorial.Gui.containers.ContainerGuiBlock;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/** GUI绘制的地方 **/
public class GuiGuiBlock extends GuiContainer {
	/* 绑定需要绘制的贴图 */
	private static final ResourceLocation backgroundimage = new ResourceLocation("JiuZhouTutorial:" + "textures/gui/GuiBlock.png");
	private GuiBlockTileEntity tile;

	/* 绑定进度条的宽度和高度 */
	private final int barWidth = 24, barHeight = 18;

	/* 分别传入两个容器实例子,然后再用这两个容器创建一个新的的ContainerGuiBlock(GuiBlock的TileEntity容器)实例 */
	public GuiGuiBlock(GuiBlockTileEntity GuiBlock的TileEntity容器, InventoryPlayer 玩家的背包容器, int 宽, int 高) {
		super(new ContainerGuiBlock(GuiBlock的TileEntity容器, 玩家的背包容器));
		/* 获取到GuiBlock的TE容器，之后用来判断是不是有物品啥的来绘制GUI */
		tile = GuiBlock的TileEntity容器;
		// 设置贴图的宽和高以便于绘制
		this.xSize = 宽;
		this.ySize = 高;
	}

	/** 绘制前景,这里多会绘制文字 */
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// 绘制文字
		this.fontRendererObj.drawString("修复机", 7, 4, 4210752);

	}

	/** 绘制背景贴图 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int var1, int var2) {
		this.mc.getTextureManager().bindTexture(backgroundimage);
		var1 = (this.width - xSize) / 2;
		var2 = (this.height - ySize) / 2;
		drawTexturedModalRect(var1, var2, 0, 0, xSize, ySize);
		/* 绘制进度条 */
		if (tile.isRepairing()) {
			/** 绘制方法: 获得到总数和当前数,计算百分比,进度条X百分比得到需要绘制的长度,绘制到图中 **/
			/** 用类似以上方法绘制出进度条 **/
			// 总修复量: max-before 已经修复量: now - before
			float all = tile.maxDamage - tile.beforeDamage, now = tile.nowDamage - tile.beforeDamage;
			float percent = now / all;
			// 如果为百分比为负数就不画
			if (percent >= 0)
				drawTexturedModalRect(var1 + 76, var2 + 20, 176, 0, (int) ((float) this.barWidth * percent), this.barHeight);
		}
		return;
	}

}
