package inc.jiuzhou.tutorial.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
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
		return null;
	}

}
