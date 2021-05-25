package com.ozgeburak.yazlab6;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafik extends ApplicationFrame {

    public Grafik(String grafikPencereBaslik, String grafikBaslik, String tip) {

        super(grafikPencereBaslik);

        if (tip == "steps") {
            JFreeChart grafik = ChartFactory.createXYLineChart(grafikBaslik,
                    "Episode", "Steps",
                    createDataset(tip),
                    PlotOrientation.VERTICAL,
                    true, true, false);
            ChartPanel chartPanel = new ChartPanel(grafik);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            setContentPane(chartPanel);
        } else if (tip == "costs") {
            JFreeChart grafik = ChartFactory.createXYLineChart(grafikBaslik,
                    "Episode", "Costs",
                    createDataset(tip),
                    PlotOrientation.VERTICAL,
                    true, true, false);
            ChartPanel chartPanel = new ChartPanel(grafik);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            setContentPane(chartPanel);
        }

    }

    private XYDataset createDataset(String tip) {

        XYSeriesCollection dataset = new XYSeriesCollection();
        if (tip == "steps") {
            XYSeries series1 = new XYSeries("Steps");
            for (int i = 0; i < QLearning.adimlar.size(); i++) {
                series1.add(i, (float) QLearning.adimlar.get(i));
            }
            dataset.addSeries(series1);
        } else if (tip == "costs") {
            XYSeries series1 = new XYSeries("costs");
            for (int i = 0; i < QLearning.maaliyetler.size(); i++) {
                series1.add(i, (float) QLearning.maaliyetler.get(i));
            }
            dataset.addSeries(series1);
        }

        return dataset;
    }

}
