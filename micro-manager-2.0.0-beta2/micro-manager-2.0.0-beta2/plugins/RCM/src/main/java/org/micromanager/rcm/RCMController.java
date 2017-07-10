/**
 * ExampleFrame.java
 *
 * This module shows an example of creating a GUI (Graphical User Interface).
 * There are many ways to do this in Java; this particular example uses the
 * MigLayout layout manager, which has extensive documentation online.
 *
 *
 * Nico Stuurman, copyright UCSF, 2012, 2015
 *
 * LICENSE: This file is distributed under the BSD license. License text is
 * included with the source distribution.
 *
 * This file is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.
 *
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES.
 */
package main.java.org.micromanager.rcm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

import org.micromanager.Studio;
import org.micromanager.internal.utils.MMFrame;

import mmcorej.CMMCore;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class RCMController extends MMFrame {

   private Studio studio_;
   private final CMMCore core_;
   
   public RCMController(Studio studio) {
      super("RCM Control Panel");
      setResizable(false);
      setTitle("RCM control");
      studio_ = studio;
      core_ = studio_.getCMMCore();
      
      String output = "";
      for(String s : core_.getLoadedDevices()){
    	  output += s;
      }
      
      studio_.alerts().postAlert("Devicelist",
              RCMController.class, output);
        
      
      getContentPane().setLayout(new MigLayout("fill, insets 2, gap 2, flowx", "[100px:100.00:100px][100px:100.00:100px][100px:100.00:100px,grow]", "[][][][][][][][][][][][][][][][][11.00][50px:50.00]"));

      // Snap an image, show the image in the Snap/Live view
      JButton btnSnap = new JButton("Snap");
      btnSnap.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            studio_.live().snap(true);
         }
      });
      
      JLabel lblAmplitudeX = new JLabel("Amplitude X:");
      getContentPane().add(lblAmplitudeX, "cell 0 0");
      
      JSpinner spAmplitudeX = new JSpinner();
      spAmplitudeX.setModel(new SpinnerNumberModel(350.0, 0.0, 1000.0, 1.0));
      getContentPane().add(spAmplitudeX, "cell 2 0,grow");
      
      JLabel lblAmplitudey = new JLabel("Amplitude Y:");
      getContentPane().add(lblAmplitudey, "cell 0 1");
      
      JSpinner spAmplitudeY = new JSpinner();
      spAmplitudeY.setModel(new SpinnerNumberModel(1.0, 0.25, 500.0, 0.25));
      getContentPane().add(spAmplitudeY, "cell 2 1,grow");
      
      JLabel lblFrequencyX = new JLabel("Frequency X:");
      getContentPane().add(lblFrequencyX, "cell 0 2");
      
      JSpinner spFrequencyX = new JSpinner();
      spFrequencyX.setModel(new SpinnerNumberModel(400.0, 0.0, 1000.0, 1.0));
      getContentPane().add(spFrequencyX, "cell 2 2,grow");
      
      JLabel lblFrequencyY = new JLabel("Frequency Y:");
      getContentPane().add(lblFrequencyY, "cell 0 3");
      
      JSpinner spFrequencyY = new JSpinner();
      spFrequencyY.setModel(new SpinnerNumberModel(400.0, 0.0, 1000.0, 1.0));
      getContentPane().add(spFrequencyY, "cell 2 3,growx");
      
      JLabel lblPhaseX = new JLabel("Phase X:");
      getContentPane().add(lblPhaseX, "cell 0 4");
      
      JSpinner spPhaseX = new JSpinner();
      spPhaseX.setModel(new SpinnerNumberModel(0.0, 0.0, 360.0, 1.0));
      getContentPane().add(spPhaseX, "cell 2 4,growx");
      
      JLabel lblPhaseY = new JLabel("Phase Y:");
      getContentPane().add(lblPhaseY, "cell 0 5");
      
      JSpinner spPhaseY = new JSpinner();
      spPhaseY.setModel(new SpinnerNumberModel(0.0, 0.0, 360.0, 1.0));
      getContentPane().add(spPhaseY, "cell 2 5,growx");
      
      JLabel lblOffsetScannerX = new JLabel("Offset Scanner X:");
      getContentPane().add(lblOffsetScannerX, "cell 0 6");
      
      JSpinner spOffsetScannerX = new JSpinner();
      spOffsetScannerX.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.1));
      getContentPane().add(spOffsetScannerX, "cell 2 6,growx");
      
      JLabel lblOffsetScannerY = new JLabel("Offset Scanner Y:");
      getContentPane().add(lblOffsetScannerY, "cell 0 7");
      
      JSpinner spOffsetScannerY = new JSpinner();
      spOffsetScannerY.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.1));
      getContentPane().add(spOffsetScannerY, "cell 2 7,growx");
      
      JLabel lblOffsetRescannerX = new JLabel("Offset ReScanner X:");
      getContentPane().add(lblOffsetRescannerX, "cell 0 8");
      
      JSpinner spOffsetRescannerX = new JSpinner();
      spOffsetRescannerX.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.1));
      getContentPane().add(spOffsetRescannerX, "cell 2 8,growx");
      
      JLabel lblOffsetRescannerY = new JLabel("Offset ReScanner Y:");
      getContentPane().add(lblOffsetRescannerY, "cell 0 9");
      
      JSpinner spOffsetRescannerY = new JSpinner();
      spOffsetRescannerY.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.1));
      getContentPane().add(spOffsetRescannerY, "cell 2 9,growx");
      
      JLabel lblLaserPower = new JLabel("Laser Power:");
      getContentPane().add(lblLaserPower, "cell 0 10");
      
      JSpinner spLaserPower = new JSpinner();
      spLaserPower.setModel(new SpinnerNumberModel(30.0, 0.0, 100.0, 1.0));
      getContentPane().add(spLaserPower, "cell 2 10,growx");
      
      JLabel lblLaserOn = new JLabel("Laser On:");
      getContentPane().add(lblLaserOn, "cell 0 11");
      
      JSpinner spLaserOn = new JSpinner();
      spLaserOn.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0));
      getContentPane().add(spLaserOn, "cell 2 11,growx");
      
      JLabel lblLaserOff = new JLabel("Laser Off:");
      getContentPane().add(lblLaserOff, "cell 0 12");
      
      JSpinner spLaserOff = new JSpinner();
      spLaserOff.setModel(new SpinnerNumberModel(0.0, 0.0, 360.0, 1.0));
      getContentPane().add(spLaserOff, "cell 2 12,growx");
      
      JLabel lblSweepFactorX = new JLabel("Sweep factor X:");
      getContentPane().add(lblSweepFactorX, "cell 0 13");
      
      JSpinner spSweepFactorX = new JSpinner();
      spSweepFactorX.setModel(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1));
      getContentPane().add(spSweepFactorX, "cell 2 13,growx");
      
      JLabel lblSweepFactorY = new JLabel("Sweep factor Y:");
      getContentPane().add(lblSweepFactorY, "cell 0 14");
      
      JSpinner spSweepFactorY = new JSpinner();
      spSweepFactorY.setModel(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1));
      getContentPane().add(spSweepFactorY, "cell 2 14,growx");
      
      JLabel lblSweepRatio = new JLabel("Sweep ratio:");
      getContentPane().add(lblSweepRatio, "cell 0 15");
      
      JComboBox<String> comboBox = new JComboBox<String>();
      comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2"}));
      getContentPane().add(comboBox, "cell 2 15,growx");
      getContentPane().add(btnSnap, "cell 0 17,grow");
      
            // Run an acquisition using the current MDA parameters.
            JButton btnLive = new JButton("Live");
            btnLive.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {        	
                   studio_.live().setLiveMode(true);
               }
            });
            getContentPane().add(btnLive, "cell 1 17,alignx center,grow");
            
            JButton btnAlbum = new JButton("Album");
            btnAlbum.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
                    studio_.live().snap(true);
            	}
            });
            getContentPane().add(btnAlbum, "cell 2 17,grow");

      pack();

      // Registering this class for events means that its event handlers
      // (that is, methods with the @Subscribe annotation) will be invoked when
      // an event occurs. You need to call the right registerForEvents() method
      // to get events; this one is for the application-wide event bus, but
      // there's also Datastore.registerForEvents() for events specific to one
      // Datastore, and DisplayWindow.registerForEvents() for events specific
      // to one image display window.
      studio_.events().registerForEvents(this);
   }
}