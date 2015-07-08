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

/** ����һ������Gui�ķ���,��Ϊ���Է��ö����ϳ�ʲô��,��������һ���̳���BlockContainer(��������)���� **/
public class GuiBlock extends BlockContainer {

	public GuiBlock(Material ����) {
		/* �����������Ĳ���(�����Զ������: �Ƿ���Ա�����,ȼ��,͸������ɶ��),Ϊ�˷����Ķ���������ʽ�����ע�������ͳһ������Ӧ�Ĳ��� */
		super(����);
		/** �������������ڲ����� **/
		this.setBlockName("GuiBlock");
		/* �������ڵĴ�������λ��,�������õ���tabBlock(������) */
		this.setCreativeTab(CreativeTabs.tabBlock);
		/*
		 * this.setHardness(flot);
		 * 
		 * this.setLightLevel(flot);
		 * 
		 * ��Щ���ÿ���ͬ�����Լ�������չ,���幦�ܿ��԰�����Ƶ��������ұߵĽ����￴
		 */
	}

	@Override
	/**������Ӧ��TileEntity**/
	public TileEntity createNewTileEntity(World ����, int ����) {
		/** ������д�õ�TileEntity **/
		return new GuiBlockTileEntity();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par1, float par2, float par3, float par4) {
		// ���isRemote��Ҳ��֪����ʲô��,���ù����
		if (world.isRemote) {
			return true;
		}
		// ������ж��������Ƕ��ž�ȡ��
		if (entityPlayer.isSneaking()) {
			return true;
		}
		// ������Ǵ�GUI��quq
		entityPlayer.openGui(JiuzhouTutorial.ʵ��, GuiHandler.GuiBlockGui, world, x, y, z);
		return true;
	}

}
