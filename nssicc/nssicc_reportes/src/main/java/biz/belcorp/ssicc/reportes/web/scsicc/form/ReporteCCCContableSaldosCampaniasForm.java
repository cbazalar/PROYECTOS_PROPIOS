package biz.belcorp.ssicc.reportes.web.scsicc.form;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author Frank J. Gonzales Alcazar
 * 
 * @struts.form name = "reporteCCCContableSaldosCampaniasForm"
 */
public class ReporteCCCContableSaldosCampaniasForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String fechaCorte;
    
    private Date fechaCorteD;
    
    
	
	public Date getFechaCorteD() {
		return fechaCorteD;
	}


	public void setFechaCorteD(Date fechaCorteD) {
		this.fechaCorteD = fechaCorteD;
	}


	public void reset() {	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));		
		
		this.fechaCorte = fecha;
	}


	/**
	 * Gets the fecha corte.
	 *
	 * @return the fecha corte
	 */
	public String getFechaCorte() {
		return fechaCorte;
	}


	/**
	 * Sets the fecha corte.
	 *
	 * @param fechaCorte the new fecha corte
	 * @struts.validator type = "required"
	 */
	public void setFechaCorte(String fechaCorte) {
		this.fechaCorte = fechaCorte;
	}


}
