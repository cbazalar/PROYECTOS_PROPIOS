package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarOlasPorFechaForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:nlopez@csigcomt.com">Nicol�s L�pez</a>
 * 
 * @struts.form name = "interfazAPEEnviarOlasPorFechaForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazAPEEnviarOlasPorFechaForm extends BaseInterfazForm implements Serializable {

    private String fechaFacturacion;
	
    private String codigoCD;
    
    private String codigoLineaArmado;
    
    private String numeroOla;

	/**
	 * @return numeroOla
	 */
	public String getNumeroOla() {
		return numeroOla;
	}

	/**
	 * @param numeroOla
	 */
	public void setNumeroOla(String numeroOla) {
		this.numeroOla = numeroOla;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 * @struts.validator type="required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
    
   /* (non-Javadoc)
   * @see biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
   */
   public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		this.fechaFacturacion=(sdf.format(new Date(System.currentTimeMillis())));
		sdf = new SimpleDateFormat("yyyyMM");
		
	}

	/**
	 * @return codigoCD
	 */
	public String getCodigoCD() {
		return codigoCD;
	}

	/**
	 * @param codigoCD the codigoCD to set
	 * @struts.validator type="required"
	 */
	public void setCodigoCD(String codigoCD) {
		this.codigoCD = codigoCD;
	}

	/**
	 * @return codigoLineaArmado
	 */
	public String getCodigoLineaArmado() {
		return codigoLineaArmado;
	}

	/**
	 * @param codigoLineaArmado the codigoLineaArmado to set
	 * @struts.validator type="required"
	 */
	public void setCodigoLineaArmado(String codigoLineaArmado) {
		this.codigoLineaArmado = codigoLineaArmado;
	}

}
