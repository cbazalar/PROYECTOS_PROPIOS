package biz.belcorp.ssicc.web._ejemplos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.PieChartModel;

import biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF;
 
@ManagedBean
@SessionScoped
public class MEjemploPieChart extends MBaseSistemaAbstractJSF {
	
	private static final long serialVersionUID = -3646605566736855187L;
	private PieChartModel model; 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	public void setViewAtributes() {
		log.debug("ini post Construct MEjemploPieChart");
		model = new PieChartModel();
		model.set("Brand 1", 540);
		model.set("Brand 2", 325);
		model.set("Brand 3", 702);
		model.set("Brand 4", 421);
		log.debug("fin post Construct MEjemploPieChart");
	}
	
	private Integer[] data = new Integer[3];

	public Integer[] getData() {
		data[0] = 5;
		data[1] = 2;
		data[2] = -3;
		System.out.println ( data );
		return data;
	}
	
	public PieChartModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(PieChartModel model) {
		this.model = model;
	}
	
	
}
