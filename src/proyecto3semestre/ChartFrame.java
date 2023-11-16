/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto3semestre;

import java.awt.event.WindowEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Sergio Diaz
 */
public class ChartFrame extends ApplicationFrame{
    
    public ChartFrame(String title, Cliente[] clientes, String[] ciudades, int contador) {
        super(title);
        JFreeChart barChart = ChartFactory.createBarChart(
         "Ciudades origen vs destino",           
         "Ciudad",            
         "Cantidad vuelos",            
         createDataset(clientes, ciudades, contador),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
    }
    
    private CategoryDataset createDataset(Cliente[] clientes, String[] ciudades, int contador){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i < ciudades.length ; i++) {
            dataset.addValue(countOrigen(clientes, ciudades[i], contador), ciudades[i], "Origen");
            dataset.addValue(countDestino(clientes, ciudades[i], contador), ciudades[i], "Destino");
        }
        return dataset;
    }
    
    public void init() {
        this.pack();
        RefineryUtilities.centerFrameOnScreen( this );        
      this.setVisible( true ); 
    }

    private Double countOrigen(Cliente[] clientes, String ciudad, int contador) {
        Double cantidad = 0.0;
        for (int i = 0; i < contador ; i++) {
            if(ciudad.equals(clientes[i].getCiudadDeOrigen())) {
                cantidad ++;
            }
        }
        
        return cantidad;
    }

    private Number countDestino(Cliente[] clientes, String ciudad, int contador) {
        Double cantidad = 0.0;
        for (int i = 0; i < contador ; i++) {
            if(ciudad.equals(clientes[i].getCiudadDeDestino())) {
                cantidad ++;
            }
        }
        
        return cantidad;
    }
 
    @Override
    public void windowClosing(WindowEvent event){
        this.hide();
    }
}
