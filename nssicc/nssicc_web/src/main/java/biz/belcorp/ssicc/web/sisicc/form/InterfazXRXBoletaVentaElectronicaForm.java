package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * @author <a href="jvelasquez@sigcomt.com">Jorge Velasquez</a>
 * 
 * @struts.form name = "interfazXRXBoletaVentaElectronicaForm" extends =
 *              "baseInterfazPaqueteForm"
 */
public class InterfazXRXBoletaVentaElectronicaForm extends BaseInterfazForm
		implements Serializable {

	private static final long serialVersionUID = 4983812500690273393L;

	private String tipoRecepcion;

	private String codigoPeriodo;

	private String numLoteSTO;

	private String codigoBatch;

	private String fechaProceso;
	private Date fechaProcesoDate;

	private String listaArchivos;

	public String getCodigoBatch() {
		return codigoBatch;
	}

	public void setCodigoBatch(String codigoBatch) {
		this.codigoBatch = codigoBatch;
	}

	public String getNumLoteSTO() {
		return numLoteSTO;
	}

	public void setNumLoteSTO(String numLoteSTO) {
		this.numLoteSTO = numLoteSTO;
	}

	public String getTipoRecepcion() {
		return tipoRecepcion;
	}

	public void setTipoRecepcion(String tipoRecepcion) {
		this.tipoRecepcion = tipoRecepcion;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getListaArchivos() {
		return listaArchivos;
	}

	public void setListaArchivos(String listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}

}
