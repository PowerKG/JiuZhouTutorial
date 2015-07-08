package inc.jiuzhou.tutorial.Gui;

import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import inc.jiuzhou.tutorial.Gui.containers.ContainerGuiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

/** �������(GUI),�ӿ���IGuiHandler **/
public class GuiHandler implements IGuiHandler {
	// ����GUI��Ӧ��ID�Ա��Ժ����
	public static final int GuiBlockGui = 0;

	/**** ����ǳ��ǳ��ǳ��ǳ��ǳ���Ҫ!!����������Ϊûͬ���������Ϳͻ��˵����˺ܾ�һ��ʱ��!!!! ****/

	@Override
	/**�������õ��Ľ���Ԫ��**/
	public Object getServerGuiElement(int GUI��ID, EntityPlayer ���, World ����, int x, int y, int z) {
		switch (GUI��ID) {
		case GuiBlockGui:
			/* ���Ҫ��ȡ��ID��GuiBlock��ID��ͬ�Ļ��͸�����������һ���µ�����ʵ�� */
			/** ��������ȡ��Ԫ�������������������������������GUI! **/
			GuiBlockTileEntity tile = (GuiBlockTileEntity) ����.getTileEntity(x, y, z);
			return new ContainerGuiBlock(tile, ���.inventory);
		}
		return null;
	}

	@Override
	/**�ͻ��˵õ��Ľ���Ԫ��**/
	// ����һ��Ҫ������ͬ��!��Ȼ���ճ���Ʒ����������!
	public Object getClientGuiElement(int GUI��ID, EntityPlayer ���, World ����, int x, int y, int z) {
		switch (GUI��ID) {
		case GuiBlockGui:
			/* ���Ҫ��ȡ��ID��GuiBlock��ID��ͬ�Ļ��͸�����������һ���µ�GUIʵ�� */
			/** ����ҿ�����GUI��������!�𷢴���!!!һ���Ӳ���������!!! **/
			GuiBlockTileEntity tile = (GuiBlockTileEntity) ����.getTileEntity(x, y, z);
			return new GuiGuiBlock(tile, ���.inventory, 176, 141);
		}
		return null;
	}
}
