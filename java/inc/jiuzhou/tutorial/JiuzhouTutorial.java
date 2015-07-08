package inc.jiuzhou.tutorial;

import inc.jiuzhou.tutorial.Gui.GuiHandler;
import inc.jiuzhou.tutorial.blocks.JiuZhouTutorialBlocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

/** 此处为主要入口的地方 **/

/* 使用@Mod声明这是一个萌萌的MOD */
@Mod(modid = "JiuZhouTutorial", name = "JiuZhou Tutorial", version = "quq")
public class JiuzhouTutorial {
	// 这个我也不知道有啥用,应该是代理服务器之间传输数据啥的,挺重要,但是不需要详细了解
	@SidedProxy(clientSide = "inc.jiuzhou.tutorial.ClientProxy", serverSide = "inc.jiuzhou.tutorial.CommonProxy")
	public static CommonProxy 代理;
	@Mod.Instance
	// 创建一个MOD实例
	public static JiuzhouTutorial 实例;

	/* 最先执行的地方 */
	@Mod.EventHandler
	public void preLoad(FMLPreInitializationEvent 事件) {
		/* 进行方块的初始化 */
		JiuZhouTutorialBlocks.init();
	}

	/* 之后执行的地方 */
	@Mod.EventHandler
	public void load(FMLInitializationEvent 事件) {
		/* 这里注册GUI,一定不要放在最先执行的地方,因为先加载了东西才能加载视图是吧! */
		NetworkRegistry.INSTANCE.registerGuiHandler(实例, new GuiHandler());
	}

	/* 最后执行的地方 */
	@Mod.EventHandler
	public void PostLoad(FMLPostInitializationEvent 事件) {

	}

}
