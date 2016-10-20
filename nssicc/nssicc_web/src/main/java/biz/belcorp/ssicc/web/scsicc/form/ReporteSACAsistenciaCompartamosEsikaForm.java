package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACAsistenciaCompartamosEsikaForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 16/09/2014
 */
public class ReporteSACAsistenciaCompartamosEsikaForm extends BaseReporteForm  implements Serializable {

	/** The codigo pais. */
	private String codigoPais;
	
	/** The name sub reporte. */
	private String nameSubReporte;
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getNameSubReporte() {
		return nameSubReporte;
	}

	public void setNameSubReporte(String nameSubReporte) {
		this.nameSubReporte = nameSubReporte;
	}
			
}
