/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcmplugin;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mmcorej.DeviceDetectionStatus;
import org.micromanager.MenuPlugin;
import org.micromanager.Studio;
import org.scijava.plugin.Plugin;


@Plugin(type = MenuPlugin.class)
public class RCMPlugin implements MenuPlugin{
    private Studio studio_;
    private RCMController frame_;
   
    @Override
    public String getSubMenu() {
        return "";
    }

    @Override
    public void onPluginSelected() {
        try {
            studio_.getCMMCore().getDeviceName("RCM");
            
            if (frame_ == null) {
                // We have never before shown our GUI, so now we need to create it.
                frame_ = new RCMController(studio_);
            }
            frame_.setVisible(true);
        } catch (Exception ex) {
            String error_msg = "Couldn't find RCM driver available. "
                        + "Please make sure you have the driver installed "
                        + "and it's in your current configuration.";
                String error_title = this.getName();
                JOptionPane.showMessageDialog(null, error_msg, error_title, JOptionPane.ERROR_MESSAGE);
        }
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
        return "Copyright (c) 2017 Confocal.nl\n" +
"\n" +
" Permission is hereby granted, free of charge, to any person\n" +
" obtaining a copy of this software and associated documentation\n" +
" files (the \"Software\"), to deal in the Software without\n" +
" restriction, including without limitation the rights to use,\n" +
" copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
" copies of the Software, and to permit persons to whom the\n" +
" Software is furnished to do so, subject to the following\n" +
" conditions:\n" +
"\n" +
" The above copyright notice and this permission notice shall be\n" +
" included in all copies or substantial portions of the Software.\n" +
"\n" +
" THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND,\n" +
" EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES\n" +
" OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND\n" +
" NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT\n" +
" HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,\n" +
" WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING\n" +
" FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR\n" +
" OTHER DEALINGS IN THE SOFTWARE.";
    }
    
}
