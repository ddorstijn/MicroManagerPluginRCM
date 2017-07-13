/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcmplugin;

import org.micromanager.MenuPlugin;
import org.micromanager.Studio;
import org.scijava.plugin.Plugin;

/**
 *
 * @author Familie Dorstijn
 */
@Plugin(type = MenuPlugin.class)
public class RCMPlugin implements MenuPlugin{
    private Studio studio_;
    private RCMFrame frame_;
   
    @Override
    public String getSubMenu() {
        return "";
    }

    @Override
    public void onPluginSelected() {
        if (frame_ == null) {
         // We have never before shown our GUI, so now we need to create it.
         frame_ = new RCMFrame(studio_);
      }
      frame_.setVisible(true);
    }

    @Override
    public void setContext(Studio studio) {
        studio_ = studio;
    }

    @Override
    public String getName() {
        return "RCM control";
    }

    @Override
    public String getHelpText() {
        return "Plugin for the RCM unit";
    }

    @Override
    public String getVersion() {
        return "0.03";
    }

    @Override
    public String getCopyright() {
        return "MIT";
    }
    
}
