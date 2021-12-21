package UI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Display;
import oshi.software.os.OperatingSystem;
import oshi.util.EdidUtil;
import oshi.util.FormatUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.List;

public class OsHwJPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;
    private static final String OPERATING_SYSTEM = "Operating System";
    private static final String HARDWARE_INFORMATION = "Hardware Information";
    private static final String PROCESSOR = "Processor";
    private static final String DISPLAYS = "Displays";
    public static final int REFRESH_FAST = 1000;
    private String osPrefix;
    public OsHwJPanel(SystemInfo si) {
        initComponents(si);
        setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(SystemInfo si) {
        osPrefix = getOsPrefix(si);
        jtaOS = new JTextArea();
        jtaProc = new JTextArea();
        jtaDisplay = new JTextArea();
        jtaHI = new JTextArea();
        jLblOS = new JLabel();
        jLblProc = new JLabel();
        jLblDisplay = new JLabel();
        jLblHI = new JLabel();

        setBackground(new Color(161, 201, 241));
        setPreferredSize(new Dimension(810, 430));

//        jtaOS.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                jtaOSActionPerformed(evt);
//            }
//        });
//
//        jtaProc.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                jtaProcActionPerformed(evt);
//            }
//        });
//
//        jtaDisplay.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                jtaDisplayActionPerformed(evt);
//            }
//        });
//
//        jtaHI.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                jtaHIActionPerformed(evt);
//            }
//        });

        jLblOS.setText(OPERATING_SYSTEM);

        jLblProc.setText(PROCESSOR);

        jLblDisplay.setText(DISPLAYS);

        jLblHI.setText(HARDWARE_INFORMATION);

        jtaOS.setText(updateOsData(si));
        jtaProc.setText(getProc(si));
        jtaDisplay.setText(getDisplay(si));
        jtaHI.setText(getHw(si));
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLblOS)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(jLblHI)
                .addGap(187, 187, 187))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jtaProc, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtaOS, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtaDisplay, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLblDisplay))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLblProc)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtaHI, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblOS)
                    .addComponent(jLblHI))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtaOS, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLblProc, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtaProc, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLblDisplay)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtaDisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtaHI, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        // Update up time every second
        Timer timer = new Timer(REFRESH_FAST, e -> jtaOS.setText(updateOsData(si)));
        timer.start();
    }// </editor-fold>//GEN-END:initComponents

    private void jtaOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtaOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtaOSActionPerformed

    private void jtaProcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtaProcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtaProcActionPerformed

    private void jtaDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtaDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtaDisplayActionPerformed

    private void jtaHIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtaHIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtaHIActionPerformed

    private static String getOsPrefix(SystemInfo si) {
        StringBuilder sb = new StringBuilder();

        OperatingSystem os = si.getOperatingSystem();
        sb.append(String.valueOf(os));
        sb.append("\n\n").append("Booted: ").append(Instant.ofEpochSecond(os.getSystemBootTime())).append('\n')
                .append("Uptime: ");
        return sb.toString();
    }

    private static String getHw(SystemInfo si) {
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        ComputerSystem computerSystem = si.getHardware().getComputerSystem();
        try {
            sb.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(computerSystem));
        } catch (JsonProcessingException e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    private static String getProc(SystemInfo si) {
        StringBuilder sb = new StringBuilder();
        CentralProcessor proc = si.getHardware().getProcessor();
        sb.append(proc.toString());

        return sb.toString();
    }

    private static String getDisplay(SystemInfo si) {
        StringBuilder sb = new StringBuilder();
        List<Display> displays = si.getHardware().getDisplays();
        if (displays.isEmpty()) {
            sb.append("None detected.");
        } else {
            int i = 0;
            for (Display display : displays) {
                byte[] edid = display.getEdid();
                byte[][] desc = EdidUtil.getDescriptors(edid);
                String name = "Display " + i;
                for (byte[] b : desc) {
                    if (EdidUtil.getDescriptorType(b) == 0xfc) {
                        name = EdidUtil.getDescriptorText(b);
                    }
                }
                if (i++ > 0) {
                    sb.append('\n');
                }
                sb.append(name).append(": ");
                int hSize = EdidUtil.getHcm(edid);
                int vSize = EdidUtil.getVcm(edid);
                sb.append(String.format("%d x %d cm (%.1f x %.1f in)", hSize, vSize, hSize / 2.54, vSize / 2.54));
            }
        }
        return sb.toString();
    }

    private String updateOsData(SystemInfo si) {
        return osPrefix + FormatUtil.formatElapsedSecs(si.getOperatingSystem().getSystemUptime());
    }

   private void setEditable(boolean flag) {
        jtaDisplay.setEditable(false);
        jtaHI.setEditable(false);
        jtaOS.setEditable(false);
        jtaProc.setEditable(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLblDisplay;
    private javax.swing.JLabel jLblHI;
    private javax.swing.JLabel jLblOS;
    private javax.swing.JLabel jLblProc;
    private javax.swing.JTextArea jtaDisplay;
    private javax.swing.JTextArea jtaHI;
    private javax.swing.JTextArea jtaOS;
    private javax.swing.JTextArea jtaProc;
    // End of variables declaration//GEN-END:variables
}
