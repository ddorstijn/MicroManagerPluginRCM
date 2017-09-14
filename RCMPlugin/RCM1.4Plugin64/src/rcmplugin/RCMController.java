package rcmplugin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

import org.micromanager.Studio;
import org.micromanager.internal.utils.MMFrame;
import org.micromanager.events.AcquisitionEndedEvent;
import org.micromanager.events.AcquisitionStartedEvent;
import org.micromanager.events.LiveModeEvent;

import com.google.common.eventbus.Subscribe;
import java.util.logging.Level;
import java.util.logging.Logger;

import mmcorej.CMMCore;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import org.micromanager.data.Datastore;
import org.micromanager.data.DatastoreFrozenException;
import org.micromanager.data.DatastoreRewriteException;
import org.micromanager.data.Image;

@SuppressWarnings("serial")
public class RCMController extends MMFrame {

    private final Studio studio_;
    private final CMMCore core_;

    public RCMController(Studio studio) {
            super("RCM Control Panel");

            studio_ = studio;
            core_ = studio_.getCMMCore();

            initComponents();
    }

    private void initComponents() {
        setResizable(false);
        setTitle("RCM control");

        getContentPane().setLayout(new MigLayout("fill, insets 2, gap 2, flowx", "[100px:100.00:100px][100px:100.00:100px][100px:100.00:100px,grow]", "[][][][][][][][][][][][][][][][][][][11.00][50px:50.00]"));

        // Amplitude X
        JLabel lblAmplitudeX = new JLabel("Amplitude X:");
        getContentPane().add(lblAmplitudeX, "cell 0 0");

        final JSpinner spAmplitudeX = new JSpinner();
        spAmplitudeX.setModel(new SpinnerNumberModel(2.0, 0.0, 10.0, 0.1));
        spAmplitudeX.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ev) {
                try {
                    core_.setProperty("RCM", "Amplitude x", (Double) spAmplitudeX.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spAmplitudeX, "cell 2 0,grow");

        JLabel lblAmplitudey = new JLabel("Amplitude Y:");
        getContentPane().add(lblAmplitudey, "cell 0 1");

        // Amplitude Y
        final JSpinner spAmplitudeY = new JSpinner();
        spAmplitudeY.setModel(new SpinnerNumberModel(2.0, 0.0, 10.0, 0.1));
        spAmplitudeY.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent ev) {
                        try {
                            core_.setProperty("RCM", "Amplitude y", (Double) spAmplitudeY.getValue());
                        } catch (Exception e) {
                            System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                        }
                }
        });
        getContentPane().add(spAmplitudeY, "cell 2 1,grow");

        // Frequency X
        JLabel lblFrequencyX = new JLabel("Frequency X:");
        getContentPane().add(lblFrequencyX, "cell 0 2");

        final JSpinner spFrequencyX = new JSpinner();
        spFrequencyX.setModel(new SpinnerNumberModel(350.0, 0.0, 1000.0, 1.0));
        spFrequencyX.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Frequency x", (Double) spFrequencyX.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spFrequencyX, "cell 2 2,grow");

        // Frequency Y
        JLabel lblFrequencyY = new JLabel("Frequency Y:");
        getContentPane().add(lblFrequencyY, "cell 0 3");

        final JSpinner spFrequencyY = new JSpinner();
        spFrequencyY.setModel(new SpinnerNumberModel(1.0, 0.0, 1000.0, 1.0));
        spFrequencyY.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Frequency y", (Double) spFrequencyY.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spFrequencyY, "cell 2 3,growx");

        // Phase X
        JLabel lblPhaseX = new JLabel("Phase X:");
        getContentPane().add(lblPhaseX, "cell 0 4");

        final JSpinner spPhaseX = new JSpinner();
        spPhaseX.setModel(new SpinnerNumberModel(0.0, 0.0, 360.0, 1.0));
        spPhaseX.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Phase rotation x", (Integer) spPhaseX.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spPhaseX, "cell 2 4,growx");

        // Phase Y
        JLabel lblPhaseY = new JLabel("Phase Y:");
        getContentPane().add(lblPhaseY, "cell 0 5");

        final JSpinner spPhaseY = new JSpinner();
        spPhaseY.setModel(new SpinnerNumberModel(0.0, 0.0, 360.0, 1.0));
        spPhaseY.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Phase rotation y", (Integer) spPhaseY.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spPhaseY, "cell 2 5,growx");

        // Offset Scanner X
        JLabel lblOffsetScannerX = new JLabel("Offset Scanner X:");
        getContentPane().add(lblOffsetScannerX, "cell 0 6");

        final JSpinner spOffsetScannerX = new JSpinner();
        spOffsetScannerX.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1));
        spOffsetScannerX.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Offset Scan x", (Double) spOffsetScannerX.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spOffsetScannerX, "cell 2 6,growx");

        // Offset Scanner Y
        JLabel lblOffsetScannerY = new JLabel("Offset Scanner Y:");
        getContentPane().add(lblOffsetScannerY, "cell 0 7");

        final JSpinner spOffsetScannerY = new JSpinner();
        spOffsetScannerY.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1));
        spOffsetScannerY.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Offset Scan y", (Double) spOffsetScannerY.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spOffsetScannerY, "cell 2 7,growx");

        // Offset Rescanner X
        JLabel lblOffsetRescannerX = new JLabel("Offset ReScanner X:");
        getContentPane().add(lblOffsetRescannerX, "cell 0 8");

        final JSpinner spOffsetRescannerX = new JSpinner();
        spOffsetRescannerX.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1));
        spOffsetRescannerX.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Offset Re-scan x", (Double) spOffsetRescannerX.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spOffsetRescannerX, "cell 2 8,growx");

        // Offset Rescanner Y
        JLabel lblOffsetRescannerY = new JLabel("Offset ReScanner Y:");
        getContentPane().add(lblOffsetRescannerY, "cell 0 9");

        final JSpinner spOffsetRescannerY = new JSpinner();
        spOffsetRescannerY.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1));
        spOffsetRescannerY.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Offset Re-scan y", (Double) spOffsetRescannerY.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spOffsetRescannerY, "cell 2 9,growx");
        
        JLabel lblLaserSelected = new JLabel("Laser Selected:");
        getContentPane().add(lblLaserSelected, "cell 0 10");
        
        // Laser selected
        final JComboBox cmbx_laser_selected = new JComboBox();
        cmbx_laser_selected.addActionListener(new ActionListener() {
                
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    //System.out.println(cmbx_laser_selected.getSelectedIndex()+1);
                    core_.setProperty("RCM", "Laser selected", cmbx_laser_selected.getSelectedIndex()+1);
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        cmbx_laser_selected.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
        getContentPane().add(cmbx_laser_selected, "cell 2 10,growx");

        // Laser Power
        JLabel lblLaserPower = new JLabel("Laser Power:");
        getContentPane().add(lblLaserPower, "cell 0 11");

        final JSpinner spLaserPower = new JSpinner();
        spLaserPower.setModel(new SpinnerNumberModel(30.0, 0.0, 100.0, 1.0));
        spLaserPower.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Laser power", (Double) spLaserPower.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spLaserPower, "cell 2 11,growx");

        // Laser On
        JLabel lblLaserOn = new JLabel("Laser On:");
        getContentPane().add(lblLaserOn, "cell 0 12");

        final JSpinner spLaserOn = new JSpinner();
        spLaserOn.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0));
        spLaserOn.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Laser on", (Double) spLaserOn.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spLaserOn, "cell 2 12,growx");

        // Laser Off
        JLabel lblLaserOff = new JLabel("Laser Off:");
        getContentPane().add(lblLaserOff, "cell 0 13");

        final JSpinner spLaserOff = new JSpinner();
        spLaserOff.setModel(new SpinnerNumberModel(0.0, 0.0, 360.0, 1.0));
        spLaserOff.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Laser off", (Double) spLaserOff.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spLaserOff, "cell 2 13,growx");
                
        // Sweep Factor
        JLabel lblSweepFactor = new JLabel("Sweep factor:");
        getContentPane().add(lblSweepFactor, "cell 0 14");

        final JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
        comboBox.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    //System.out.println(comboBox.getSelectedIndex()+1);
                    core_.setProperty("RCM", "Sweep factor", (int) comboBox.getSelectedIndex() + 1);
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(comboBox, "cell 2 14,growx");

        // Sweep ratio X
        final JLabel lblSweepRatioX = new JLabel("Sweep ratio X:");
        getContentPane().add(lblSweepRatioX, "cell 0 16");

        final JSpinner spSweepRatioX = new JSpinner();
        spSweepRatioX.setModel(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1));
        spSweepRatioX.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Sweep ratio x (M=1)", (Double) spSweepRatioX.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spSweepRatioX, "cell 2 16,growx");

        // Sweep ratio Y
        final JLabel lblSweepRatioY = new JLabel("Sweep ratio Y:");
        getContentPane().add(lblSweepRatioY, "cell 0 17");

        final JSpinner spSweepRatioY = new JSpinner();
        spSweepRatioY.setModel(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1));
        spSweepRatioY.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent event) {
                try {
                    core_.setProperty("RCM", "Sweep ratio y (M=1)", (Double) spSweepRatioY.getValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
                }
            }
        });
        getContentPane().add(spSweepRatioY, "cell 2 17,growx");

        lblSweepRatioX.setVisible(false);
        spSweepRatioX.setVisible(false);

        lblSweepRatioY.setVisible(false);
        spSweepRatioY.setVisible(false);

        // Snap
        JButton btnSnap = new JButton("Snap");
        btnSnap.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent event) {
                Datastore store = studio_.data().createRAMDatastore();
                studio_.displays().createDisplay(store);
                studio_.displays().manage(store);
                try {
                    store.putImage(getSingleImage());
                } catch (DatastoreFrozenException ex) {
                    Logger.getLogger(RCMController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DatastoreRewriteException ex) {
                    Logger.getLogger(RCMController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(RCMController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        getContentPane().add(btnSnap, "cell 0 19,grow");

        // Live
        JButton btnLive = new JButton("Live");
        btnLive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studio_.live().setLiveMode(true);
            }
        });
        getContentPane().add(btnLive, "cell 1 19,alignx center,grow");

        // Album
        JButton btnAlbum = new JButton("Album");
        btnAlbum.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    studio_.album().addImage(getSingleImage());
                }
        });
        getContentPane().add(btnAlbum, "cell 2 19,grow");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnAdvanced = new JMenu("Advanced");
        menuBar.add(mnAdvanced);

        final JCheckBoxMenuItem chckbxmntmEnableAdvancedSettings = new JCheckBoxMenuItem("Enable advanced settings");
        chckbxmntmEnableAdvancedSettings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (chckbxmntmEnableAdvancedSettings.isSelected()) {
                    lblSweepRatioX.setVisible(true);
                    spSweepRatioX.setVisible(true);

                    lblSweepRatioY.setVisible(true);
                    spSweepRatioY.setVisible(true);
                } else {
                    lblSweepRatioX.setVisible(false);
                    spSweepRatioX.setVisible(false);

                    lblSweepRatioY.setVisible(false);
                    spSweepRatioY.setVisible(false);
                }

            }
        });
        mnAdvanced.add(chckbxmntmEnableAdvancedSettings);

        pack();

        studio_.events().registerForEvents(this);
    }
    
    private Image getSingleImage() {
        Image image = null;
                
        try {
            core_.setProperty("RCM", "Start Sequence", "1");
            
            image = studio_.live().snap(false).get(0);
            
            core_.setProperty("RCM", "Start Sequence", "0");
        } catch (Exception ex) {
            Logger.getLogger(RCMController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return image;
    }

    @Subscribe
    public void onLiveMode(LiveModeEvent event) {
        System.out.println("Fired livemode");
        if (event.getIsOn()) {
            try {
                core_.setProperty("RCM", "Start Sequence", "1");
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
            }
        } else {
            try {
                core_.setProperty("RCM", "Start Sequence", "0");
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
            }
        }
    }

    @Subscribe
    public void onAcquisitionStarted(AcquisitionStartedEvent event) {
        System.out.println("Fired sequence");
        try {
            core_.setProperty("RCM", "Start Sequence", "0");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
        }
    }

    @Subscribe
    public void onAcquisitionEnded(AcquisitionEndedEvent event) {
        try {
            core_.setProperty("RCM", "Start Sequence", "0");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage() + "\nExiting now.");
        }
    }
}