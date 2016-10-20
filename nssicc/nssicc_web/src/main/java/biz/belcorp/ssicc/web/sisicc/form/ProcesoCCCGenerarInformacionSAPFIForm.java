package biz.belcorp.ssicc.web.sisicc.form;


import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;


/**
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 * @struts.form name = "procesoCCCGenerarInformacionSAPFIForm"
 */
public class ProcesoCCCGenerarInformacionSAPFIForm extends BaseProcesoForm
		implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
			
	private String codigoModulo;
	
	private String codigoInterface;
				
	private String fechaProcesoHasta;
	
	/*						
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		this.fechaProcesoHasta = sdf.format(new Date(System.currentTimeMillis()));
		
	}
	*/

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	

	public String getCodigoModulo() {
		return codigoModulo;
	}

	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}

	/**
	 * @return the codigoInterface
	 */
	public String getCodigoInterface() {
		return codigoInterface;
	}

	/**
	 * @param codigoInterface the codigoInterface to set
	 */
	public void setCodigoInterface(String codigoInterface) {
		this.codigoInterface = codigoInterface;
	}
	

	/**
	 * @return Returns the fechaHasta.
	 */
	public String getfechaProcesoHasta() {
		return fechaProcesoHasta;
	}


	public void setfechaProcesoHasta(String fechaProcesoHasta) {
		this.fechaProcesoHasta = fechaProcesoHasta;
	}

	
}
