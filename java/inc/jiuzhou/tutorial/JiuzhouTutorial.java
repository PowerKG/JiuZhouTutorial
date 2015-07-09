package inc.jiuzhou.tutorial;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.network.ForgeNetworkHandler;
import inc.jiuzhou.tutorial.Blocks.JiuZhouTutorialBlocks;
import inc.jiuzhou.tutorial.Events.EventHandler;
import inc.jiuzhou.tutorial.Gui.GuiHandler;
import inc.jiuzhou.tutorial.Gui.MyMenuGui;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
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
		/* ע�������(�Ȱ�����MainMenuGui) */
		MinecraftForge.EVENT_BUS.register(EventHandler.ins);
		/* ���з���ĳ�ʼ�� */
		JiuZhouTutorialBlocks.init();
	}

	/* ֮��ִ�еĵط� */
	@Mod.EventHandler
	public void load(FMLInitializationEvent �¼�) {
		/* ����ע��GUI,һ����Ҫ��������ִ�еĵط�,��Ϊ�ȼ����˶������ܼ�����ͼ�ǰ�! */
		NetworkRegistry.INSTANCE.registerGuiHandler(ʵ��, new GuiHandler());
	}

}
