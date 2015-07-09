package inc.jiuzhou.tutorial.Events;

import inc.jiuzhou.tutorial.Gui.MyMenuGui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventHandler {
	public static final EventHandler ins = new EventHandler();

	/** 监听GUIOPEN事件,将自己的主界面HOOK上去 **/
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void openMainMenu(GuiOpenEvent e) {
		if (e.gui instanceof GuiMainMenu) {
			// HOOK
			e.gui = new MyMenuGui();
		}
	}

}
