package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


public class InterfazCCCEnviarSAPFICobranzasForm extends BaseInterfazForm implements Serializable {

    private String fechaProcesoHasta;
    private Date fechaProcesoHastaD;
    
    
    
    public Date getFechaProcesoHastaD() {
		return fechaProcesoHastaD;
	}

	public void setFechaProcesoHastaD(Date fechaProcesoHastaD) {
		this.fechaProcesoHastaD = fechaProcesoHastaD;
	}

	/**
	 * @return Returns the fechaProcesoHasta.
	 */
	public String getFechaProcesoHasta() {
		return fechaProcesoHasta;
	}
	
	public void setFechaProcesoHasta(String fechaProcesoHasta) {
		this.fechaProcesoHasta = fechaProcesoHasta;
	}

	
      
}
