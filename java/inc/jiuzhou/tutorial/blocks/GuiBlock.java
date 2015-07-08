package inc.jiuzhou.tutorial.blocks;

import inc.jiuzhou.tutorial.JiuzhouTutorial;
import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import inc.jiuzhou.tutorial.Gui.GuiHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/** 这是一个带有Gui的方块,因为可以放置东西合成什么的,所以这是一个继承于BlockContainer(方块容器)的类 **/
public class GuiBlock extends BlockContainer {

	public GuiBlock(Material 材质) {
		/* 设置这个方块的材质(可以自定义材质: 是否可以被覆盖,燃烧,透过光线啥的),为了方便阅读，这个材质将会在注册的类里统一赋上相应的材质 */
		super(材质);
		/** 设置这个方块的内部名字 **/
		this.setBlockName("GuiBlock");
		/* 设置所在的创造栏的位置,这里设置的是tabBlock(方块类) */
		this.setCreativeTab(CreativeTabs.tabBlock);
		/*
		 * this.setHardness(flot);
		 * 
		 * this.setLightLevel(flot);
		 * 
		 * 这些设置可以同样的自己进行扩展,具体功能可以把鼠标移到上面在右边的介绍里看
		 */
	}

	@Override
	/**绑定所对应的TileEntity**/
	public TileEntity createNewTileEntity(World 世界, int 变量) {
		/** 返回咱写好的TileEntity **/
		return new GuiBlockTileEntity();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par1, float par2, float par3, float par4) {
		// 这个isRemote我也不知道是什么鬼,不用管这个
		if (world.isRemote) {
			return true;
		}
		// 这个是判断如果玩家是蹲着就取消
		if (entityPlayer.isSneaking()) {
			return true;
		}
		// 这里就是打开GUI了quq
		entityPlayer.openGui(JiuzhouTutorial.实例, GuiHandler.GuiBlockGui, world, x, y, z);
		return true;
	}

}
