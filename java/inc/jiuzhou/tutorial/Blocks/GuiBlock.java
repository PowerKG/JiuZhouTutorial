package inc.jiuzhou.tutorial.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import inc.jiuzhou.tutorial.JiuzhouTutorial;
import inc.jiuzhou.tutorial.BlockTileEntiies.GuiBlockTileEntity;
import inc.jiuzhou.tutorial.Gui.Blocks.GuiHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/** 这是一个带有Gui的方块,因为可以放置东西合成什么的,所以这是一个继承于BlockContainer(方块容器)的类 **/
public class GuiBlock extends BlockContainer {
	// 材质的设定
	IIcon top = null, bottom = null, side = null;

	public GuiBlock(Material 材质) {
		/* 设置这个方块的材质(可以自定义材质: 是否可以被覆盖,燃烧,透过光线啥的),为了方便阅读，这个材质将会在注册的类里统一赋上相应的材质 */
		super(材质);
		/** 设置这个方块的内部名字 **/
		this.setBlockName("GuiBlock");

		/**
		 * 1.7版本中setUnlocalizedName被改为setBlockName了,非1.7版本请用这个
		 * this.setUnlocalizedName("GuiBlock");
		 */

		/**
		 * 简易设置材质方法(1.8版本不适用),且设置到的六面贴图都一样 this.setBlockTextureName("GuiBlock");
		 */

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
		// 明补充:world.isRemote 從字面看就是「是否远程」,MC的核心不竟还是服务器,所以这个Remote就是客戶端
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

	/**注册相应的贴图**/
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.top = reg.registerIcon("JiuZhouTutorial:guiblock_top");
		this.bottom = reg.registerIcon("JiuZhouTutorial:guiblock_bottom");
		this.side = reg.registerIcon("JiuZhouTutorial:guiblock_side");
	}

	/**
	 * 材质面获得方法(1.8同样不适用) 为什么这里要特別写呢,大槪因为本来的方法只返回1个值,所以我们要特別复盖方法
	 * 
	 * @param face
	 *            方块面
	 * @param meta
	 *            方块数据值
	 * @return Icon
	 */
	
	/** 当读取调用贴图的时候就会get这里，**/
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int face, int meta) {
		switch (face) {
		case 0:
			return bottom;
		case 1:
			return top;
		default:
			return side;
		}
	}

}
