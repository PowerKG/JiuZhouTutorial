package inc.jiuzhou.tutorial;

import inc.jiuzhou.tutorial.Gui.GuiHandler;
import inc.jiuzhou.tutorial.blocks.JiuZhouTutorialBlocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

/** �˴�Ϊ��Ҫ��ڵĵط� **/

/* ʹ��@Mod��������һ�����ȵ�MOD */
@Mod(modid = "JiuZhouTutorial", name = "JiuZhou Tutorial", version = "quq")
public class JiuzhouTutorial {
	// �����Ҳ��֪����ɶ��,Ӧ���Ǵ��������֮�䴫������ɶ��,ͦ��Ҫ,���ǲ���Ҫ��ϸ�˽�
	@SidedProxy(clientSide = "inc.jiuzhou.tutorial.ClientProxy", serverSide = "inc.jiuzhou.tutorial.CommonProxy")
	public static CommonProxy ����;
	@Mod.Instance
	// ����һ��MODʵ��
	public static JiuzhouTutorial ʵ��;

	/* ����ִ�еĵط� */
	@Mod.EventHandler
	public void preLoad(FMLPreInitializationEvent �¼�) {
		/* ���з���ĳ�ʼ�� */
		JiuZhouTutorialBlocks.init();
	}

	/* ֮��ִ�еĵط� */
	@Mod.EventHandler
	public void load(FMLInitializationEvent �¼�) {
		/* ����ע��GUI,һ����Ҫ��������ִ�еĵط�,��Ϊ�ȼ����˶������ܼ�����ͼ�ǰ�! */
		NetworkRegistry.INSTANCE.registerGuiHandler(ʵ��, new GuiHandler());
	}

	/* ���ִ�еĵط� */
	@Mod.EventHandler
	public void PostLoad(FMLPostInitializationEvent �¼�) {

	}

}
