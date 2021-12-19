package UI;

import java.awt.*;
import java.io.Serial;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.*;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class CPU_Usage extends OshiJPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    public static final int REFRESH_RATE = 1000;

    private long[] oldTicks;
    private long[][] oldProcTicks;

    public CPU_Usage(SystemInfo si) {
        super();
        CentralProcessor cpu = si.getHardware().getProcessor();
        oldTicks = new long[CentralProcessor.TickType.values().length];
        oldProcTicks = new long[cpu.getLogicalProcessorCount()][CentralProcessor.TickType.values().length];
    }

    public void init(CentralProcessor processor, double[] Usage) {

        GridBagConstraints sysConstraints = new GridBagConstraints();
        sysConstraints.weightx = 1d;
        sysConstraints.weighty = 1d;
        sysConstraints.fill = GridBagConstraints.BOTH;

        GridBagConstraints procConstraints = (GridBagConstraints) sysConstraints.clone();
        procConstraints.gridx = 1;

        Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        DynamicTimeSeriesCollection sysData = new DynamicTimeSeriesCollection(1, 60, new Second());
        sysData.setTimeBase(new Second(date));
        sysData.addSeries(FloatArrayPercent(cpuData(processor)), 0, "All cpus");
        JFreeChart systemCpu = ChartFactory.createTimeSeriesChart("System CPU Usage", "Time", "% CPU", sysData, true,
                true, false);
        
        DynamicTimeSeriesCollection procData = new DynamicTimeSeriesCollection(Usage.length, 60, new Second());
        procData.setTimeBase(new Second(date));
        for (int i = 0; i < Usage.length; i++) {
            procData.addSeries(FloatArrayPercent(Usage[i]), i, "cpu" + i);
        }
        JFreeChart procCpu = ChartFactory.createTimeSeriesChart("Processor CPU Usage", "Time", "% CPU", procData, true,
                true, false);

        JPanel cpuPanel = new JPanel();
        cpuPanel.setLayout(new GridBagLayout());
        cpuPanel.add(new ChartPanel(systemCpu), sysConstraints);
        cpuPanel.add(new ChartPanel(procCpu), procConstraints);

        add(cpuPanel, BorderLayout.CENTER);

        Timer timer = new Timer(REFRESH_RATE, e -> {
            sysData.advanceTime();
            sysData.appendData(FloatArrayPercent(cpuData(processor)));
            procData.advanceTime();
            int newest = procData.getNewestIndex();
            double[] procUsageData = procData(processor);
            for (int i = 0; i < procUsageData.length; i++) {
                procData.addValue(i, newest, (float) (100 * procUsageData[i]));
            }
        });
        timer.start();
    }

    public static float[] FloatArrayPercent(double d) {
        float[] f = new float[1];
        f[0] = (float) (100d * d);
        return f;
    }

    public double cpuData(CentralProcessor proc) {
        double d = proc.getSystemCpuLoadBetweenTicks(oldTicks);
        oldTicks = proc.getSystemCpuLoadTicks();
        return d;
    }

    public double[] procData(CentralProcessor proc) {
        double[] p = proc.getProcessorCpuLoadBetweenTicks(oldProcTicks);
        oldProcTicks = proc.getProcessorCpuLoadTicks();
        return p;
    }

    public static  void main(String[] args)
    {
        /*
        JFrame frame = new JFrame();
        SystemInfo si = new SystemInfo();
        frame.add(new CPU_Usage(si));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        */
    }

}
