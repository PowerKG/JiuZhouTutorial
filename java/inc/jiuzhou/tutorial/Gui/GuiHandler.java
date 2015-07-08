package inc.jiuzhou.tutorial.Gui;

import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import inc.jiuzhou.tutorial.Gui.containers.ContainerGuiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

/** 处理界面(GUI),接口是IGuiHandler **/
public class GuiHandler implements IGuiHandler {
	// 绑定上GUI对应的ID以便以后记忆
	public static final int GuiBlockGui = 0;

	/**** 这里非常非常非常非常非常重要!!我在这里因为没同步服务器和客户端的死了很久一段时间!!!! ****/

	@Override
	/**服务器得到的界面元素**/
	public Object getServerGuiElement(int GUI的ID, EntityPlayer 玩家, World 世界, int x, int y, int z) {
		switch (GUI的ID) {
		case GuiBlockGui:
			/* 如果要获取的ID和GuiBlock的ID相同的话就给服务器发送一个新的容器实例 */
			/** 服务器获取的元素是用来处理的容器而不是用来看得GUI! **/
			GuiBlockTileEntity tile = (GuiBlockTileEntity) 世界.getTileEntity(x, y, z);
			return new ContainerGuiBlock(tile, 玩家.inventory);
		}
		return null;
	}

	@Override
	/**客户端得到的界面元素**/
	// 这里一定要和上面同步!不然会照成物品个各种问题!
	public Object getClientGuiElement(int GUI的ID, EntityPlayer 玩家, World 世界, int x, int y, int z) {
		switch (GUI的ID) {
		case GuiBlockGui:
			/* 如果要获取的ID和GuiBlock的ID相同的话就给服务器发送一个新的GUI实例 */
			/** 给玩家看的是GUI不是容器!别发错了!!!一个坑不能死两次!!! **/
			GuiBlockTileEntity tile = (GuiBlockTileEntity) 世界.getTileEntity(x, y, z);
			return new GuiGuiBlock(tile, 玩家.inventory, 176, 141);
		}
		return null;
	}
}
