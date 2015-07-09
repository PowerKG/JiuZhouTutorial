package inc.jiuzhou.tutorial.Gui;

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

	/* 分别传入两个容器实例子,然后再用这两个容器创建一个新的的ContainerGuiBlock(GuiBlock的TileEntity容器)实例 */
	public GuiGuiBlock(GuiBlockTileEntity GuiBlock的TileEntity容器, InventoryPlayer 玩家的背包容器, int 宽, int 高) {
		super(new ContainerGuiBlock(GuiBlock的TileEntity容器, 玩家的背包容器));
		/* 获取到GuiBlock的TE容器，之后用来判断是不是有物品啥的来绘制GUI */
		tile = GuiBlock的TileEntity容器;
		// 设置贴图的宽和高以便于绘制
		this.xSize = 宽;
		this.ySize = 高;
	}
	/** 绘制前景,这里多会绘制文字*/
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){}
	
	/** 绘制背景贴图 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int var1, int var2) {
		this.mc.getTextureManager().bindTexture(backgroundimage);
		var1 = (this.width - xSize) / 2;
		var2 = (this.height - ySize) / 2;
		drawTexturedModalRect(var1, var2, 0, 0, xSize, ySize);
	}

}
