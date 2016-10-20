package biz.belcorp.ssicc.service.scsicc.framework.beans;

import org.jfree.chart.JFreeChart;

/**
 * Clase que devuelve el resultado de la ejecucin del grafico
 * @author peextcbazalar
 *
 */
public class GraphResult {
	
	private JFreeChart pieChart;  


	/**
	 * @return Returns the pieChart.
	 */
	public JFreeChart getPieChart() {
		return pieChart;
	}

	/**
	 * @param pieChart The pieChart to set.
	 */
	public void setPieChart(JFreeChart pieChart) {
		this.pieChart = pieChart;
	}
	
	
	
		
}
