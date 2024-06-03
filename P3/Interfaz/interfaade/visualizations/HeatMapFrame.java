package interfaade.visualizations;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.chart.renderer.LookupPaintScale;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.util.Random;

public class HeatMapFrame extends ApplicationFrame {

    public HeatMapFrame(String title) {
        super(title);
        XYZDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private XYZDataset createDataset() {
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double[] xValues = new double[365]; // X values for months
        double[] yValues = new double[365]; // Y values for days of the week
        double[] zValues = new double[365]; // Z values for sales

        Random rand = new Random();

        for (int i = 0; i < 365; i++) {
            xValues[i] = (i % 12) + 1; // Months (1 to 12)
            yValues[i] = (i % 7) + 1;  // Days of the week (1 to 7)
            zValues[i] = rand.nextDouble() * 10; // Random sales value
        }

        double[][] data = new double[][]{xValues, yValues, zValues};
        dataset.addSeries("Series 1", data);
        return dataset;
    }

    private JFreeChart createChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis("Mes");
        NumberAxis yAxis = new NumberAxis("Día de la Semana");
        XYBlockRenderer renderer = new XYBlockRenderer();

        LookupPaintScale paintScale = new LookupPaintScale(0, 10, Color.WHITE);
        paintScale.add(0.0, new Color(255, 255, 255));
        paintScale.add(2.5, new Color(200, 255, 200));
        paintScale.add(5.0, new Color(150, 255, 150));
        paintScale.add(7.5, new Color(100, 255, 100));
        paintScale.add(10.0, new Color(50, 255, 50));
        renderer.setPaintScale(paintScale);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        JFreeChart chart = new JFreeChart("Mapa de Calor de Ventas", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        chart.addSubtitle(new TextTitle("Ventas por Día de la Semana y Mes", new Font("SansSerif", Font.PLAIN, 12)));
        return chart;
    }

    public static void main(String[] args) {
        HeatMapFrame demo = new HeatMapFrame("Heat Map de Ventas");
        demo.pack();
        demo.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        demo.setVisible(true);
    }
}
