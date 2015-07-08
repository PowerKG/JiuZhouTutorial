package inc.jiuzhou.tutorial.Gui;

import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import inc.jiuzhou.tutorial.Gui.containers.ContainerGuiBlock;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/** GUI���Ƶĵط� **/
public class GuiGuiBlock extends GuiContainer {
	/* ����Ҫ���Ƶ���ͼ */
	private static final ResourceLocation backgroundimage = new ResourceLocation("JiuZhouTutorial:" + "textures/gui/GuiBlock.png");
	private GuiBlockTileEntity tile;

	/* �ֱ�����������ʵ����,Ȼ��������������������һ���µĵ�ContainerGuiBlock(GuiBlock��TileEntity����)ʵ�� */
	public GuiGuiBlock(GuiBlockTileEntity GuiBlock��TileEntity����, InventoryPlayer ��ҵı�������, int ��, int ��) {
		super(new ContainerGuiBlock(GuiBlock��TileEntity����, ��ҵı�������));
		/* ��ȡ��GuiBlock��TE������֮�������ж��ǲ�������Ʒɶ��������GUI */
		tile = GuiBlock��TileEntity����;
		// ������ͼ�Ŀ�͸��Ա��ڻ���
		this.xSize = ��;
		this.ySize = ��;
	}

	/* ���Ʊ�����ͼ */
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int var1, int var2) {
		this.mc.getTextureManager().bindTexture(backgroundimage);
		var1 = (this.width - xSize) / 2;
		var2 = (this.height - ySize) / 2;
		drawTexturedModalRect(var1, var2, 0, 0, xSize, ySize);
	}

}
