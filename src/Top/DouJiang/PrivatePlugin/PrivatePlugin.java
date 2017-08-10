package Top.DouJiang.PrivatePlugin;

import Top.DouJiang.Tool.SystemTools;
import Top.DouJiang.plugin.PluginMain;

/**
 * Created by NicoNicoNi on 2017/8/10 0010.
 */
public class PrivatePlugin implements PluginMain {
    @Override
    public void onEnable() {
        SystemTools.Print("[PrivatePlugin]加载成功!",1,0);
    }

    @Override
    public void onDisable() {
        SystemTools.Print("[PrivatePlugin]卸载成功!",1,0);
    }
}
