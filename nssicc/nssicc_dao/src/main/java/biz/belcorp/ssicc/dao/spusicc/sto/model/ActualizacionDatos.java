package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/* @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */

public class ActualizacionDatos implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String fechaProceso;
	private String unidadAdministrativa;
	private String codPeriodo;
	private String valApellido1;
	private String valApellido2;
	private String valNombre1;
	private String valNombre2;
	private String tipoDocumento;
	private String numDocuIdentidad;
	private String numRUC;
	private String valDirecCliente;
	private String valBarrCliente;
	private String valCiudCliente;
	private String valDepaCliente;
	private String valTelfCliente;
	private String valCeluCliente;
	private String valTelfTrabajo;
	private String valMailCliente;
	private String valRegionArribo;
	private String valZonaArribo;
	private String indEstaProceso;
	private String indMotivoRechazo;
	private String tipoViaCliente;
	private String valNombreVia;
	private String numDireCliente;
	private String codDepaCliente;
	private String codProvCliente;
	private String codDistCliente;
	private String codSectCliente;
	private String codTipoDocumento;
	private String numSecuencia;
	private String numLote;
	private String detalle;
	private String direccionEntrega;
	private String telefonoEntrega;
	private String celularEntrega;
	private String indicadorValidacionOK;
	//sb PER-SiCC-2012-0460 ini
	private String codigoCiudadDomicilio;
	private String codigoCiudadDomicilioUbigeo;
	private String villaPoblacionDomicilio;
	private String codigoCiudadEntrega;
	private String codigoCiudadEntregaUbigeo;
	private String villaPoblacionEntrega;	
	//sb PER-SiCC-2012-0460 fin
	
	private String nacionalidad;
	private String territorioCorresponde;
	private String direccionDomicilioIgualDireccionEntrega;
	private String dirDomManzana;
	private String dirDomEtapa;
	private String dirDomCallePrincipal;
	private String dirDomCalleSecundaria;
	private String dirDomNumero;
	private String dirDomReferencia;
	private String dirEntBarrio;
	private String dirEntManzana;
	private String dirEntEtapa;
	private String dirEntCallePrincipal;
	private String dirEntCalleSecundaria;
	private String dirEntNumero;
	private String dirEntReferencia;
	
	/**
	 * @return Returns the codCliente.
	 */
	public String getCodCliente() {
		return codCliente;
	}
	/**
	 * @param codCliente The codCliente to set.
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
	/**
	 * @return Returns the codCompania.
	 */
	public String getCodCompania() {
		return codCompania;
	}
	/**
	 * @param codCompania The codCompania to set.
	 */
	public void setCodCompania(String codCompania) {
		this.codCompania = codCompania;
	}
	/**
	 * @return Returns the codDepaCliente.
	 */
	public String getCodDepaCliente() {
		return codDepaCliente;
	}
	/**
	 * @param codDepaCliente The codDepaCliente to set.
	 */
	public void setCodDepaCliente(String codDepaCliente) {
		this.codDepaCliente = codDepaCliente;
	}
	
	/**
	 * @return Returns the codDistCliente.
	 */
	public String getCodDistCliente() {
		return codDistCliente;
	}
	/**
	 * @param codDistCliente The codDistCliente to set.
	 */
	public void setCodDistCliente(String codDistCliente) {
		this.codDistCliente = codDistCliente;
	}
	
	/**
	 * @return Returns the codPais.
	 */
	public String getCodPais() {
		return codPais;
	}
	/**
	 * @param codPais The codPais to set.
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	/**
	 * @return Returns the codPeriodo.
	 */
	public String getCodPeriodo() {
		return codPeriodo;
	}
	/**
	 * @param codPeriodo The codPeriodo to set.
	 */
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}
	
	/**
	 * @return Returns the codProvCliente.
	 */
	public String getCodProvCliente() {
		return codProvCliente;
	}
	/**
	 * @param codProvCliente The codProvCliente to set.
	 */
	public void setCodProvCliente(String codProvCliente) {
		this.codProvCliente = codProvCliente;
	}
	
	/**
	 * @return Returns the codSectCliente.
	 */
	public String getCodSectCliente() {
		return codSectCliente;
	}
	/**
	 * @param codSectCliente The codSectCliente to set.
	 */
	public void setCodSectCliente(String codSectCliente) {
		this.codSectCliente = codSectCliente;
	}
	
	/**
	 * @return Returns the codTipoDocumento.
	 */
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	/**
	 * @param codTipoDocumento The codTipoDocumento to set.
	 */
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	
	
	/**
	 * @return Returns the indEstaProceso.
	 */
	public String getIndEstaProceso() {
		return indEstaProceso;
	}
	/**
	 * @param indEstaProceso The indEstaProceso to set.
	 */
	public void setIndEstaProceso(String indEstaProceso) {
		this.indEstaProceso = indEstaProceso;
	}
	/**
	 * @return Returns the indMotivoRechazo.
	 */
	public String getIndMotivoRechazo() {
		return indMotivoRechazo;
	}
	/**
	 * @param indMotivoRechazo The indMotivoRechazo to set.
	 */
	public void setIndMotivoRechazo(String indMotivoRechazo) {
		this.indMotivoRechazo = indMotivoRechazo;
	}
	
	/**
	 * @return Returns the numDireCliente.
	 */
	public String getNumDireCliente() {
		return numDireCliente;
	}
	/**
	 * @param numDireCliente The numDireCliente to set.
	 */
	public void setNumDireCliente(String numDireCliente) {
		this.numDireCliente = numDireCliente;
	}
		/**
	 * @return Returns the numDocuIdentidad.
	 */
	public String getNumDocuIdentidad() {
		return numDocuIdentidad;
	}
	/**
	 * @param numDocuIdentidad The numDocuIdentidad to set.
	 */
	public void setNumDocuIdentidad(String numDocuIdentidad) {
		this.numDocuIdentidad = numDocuIdentidad;
	}
	/**
	 * @return Returns the numDocumento.
	 */
	public String getNumDocumento() {
		return numDocumento;
	}
	/**
	 * @param numDocumento The numDocumento to set.
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	/**
	 * @return Returns the numRUC.
	 */
	public String getNumRUC() {
		return numRUC;
	}
	/**
	 * @param numRUC The numRUC to set.
	 */
	public void setNumRUC(String numRUC) {
		this.numRUC = numRUC;
	}
	
	/**
	 * @return Returns the tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento The tipoDocumento to set.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * @return Returns the tipoViaCliente.
	 */
	public String getTipoViaCliente() {
		return tipoViaCliente;
	}
	/**
	 * @param tipoViaCliente The tipoViaCliente to set.
	 */
	public void setTipoViaCliente(String tipoViaCliente) {
		this.tipoViaCliente = tipoViaCliente;
	}
	
	/**
	 * @return Returns the unidadAdministrativa.
	 */
	public String getUnidadAdministrativa() {
		return unidadAdministrativa;
	}
	/**
	 * @param unidadAdministrativa The unidadAdministrativa to set.
	 */
	public void setUnidadAdministrativa(String unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}
	/**
	 * @return Returns the valApellido1.
	 */
	public String getValApellido1() {
		return valApellido1;
	}
	/**
	 * @param valApellido1 The valApellido1 to set.
	 */
	public void setValApellido1(String valApellido1) {
		this.valApellido1 = valApellido1;
	}
	
	/**
	 * @return Returns the valApellido2.
	 */
	public String getValApellido2() {
		return valApellido2;
	}
	/**
	 * @param valApellido2 The valApellido2 to set.
	 */
	public void setValApellido2(String valApellido2) {
		this.valApellido2 = valApellido2;
	}
	
	/**
	 * @return Returns the valBarrCliente.
	 */
	public String getValBarrCliente() {
		return valBarrCliente;
	}
	/**
	 * @param valBarrCliente The valBarrCliente to set.
	 */
	public void setValBarrCliente(String valBarrCliente) {
		this.valBarrCliente = valBarrCliente;
	}
	
	/**
	 * @return Returns the valCeluCliente.
	 */
	public String getValCeluCliente() {
		return valCeluCliente;
	}
	/**
	 * @param valCeluCliente The valCeluCliente to set.
	 */
	public void setValCeluCliente(String valCeluCliente) {
		this.valCeluCliente = valCeluCliente;
	}
	
	/**
	 * @return Returns the valCiudCliente.
	 */
	public String getValCiudCliente() {
		return valCiudCliente;
	}
	/**
	 * @param valCiudCliente The valCiudCliente to set.
	 */
	public void setValCiudCliente(String valCiudCliente) {
		this.valCiudCliente = valCiudCliente;
	}
	
	/**
	 * @return Returns the valDepaCliente.
	 */
	public String getValDepaCliente() {
		return valDepaCliente;
	}
	/**
	 * @param valDepaCliente The valDepaCliente to set.
	 */
	public void setValDepaCliente(String valDepaCliente) {
		this.valDepaCliente = valDepaCliente;
	}
	
	/**
	 * @return Returns the valDirecCliente.
	 */
	public String getValDirecCliente() {
		return valDirecCliente;
	}
	/**
	 * @param valDirecCliente The valDirecCliente to set.
	 */
	public void setValDirecCliente(String valDirecCliente) {
		this.valDirecCliente = valDirecCliente;
	}
	
	/**
	 * @return Returns the valMailCliente.
	 */
	public String getValMailCliente() {
		return valMailCliente;
	}
	/**
	 * @param valMailCliente The valMailCliente to set.
	 */
	public void setValMailCliente(String valMailCliente) {
		this.valMailCliente = valMailCliente;
	}
	/**
	 * @return Returns the valNombre1.
	 */
	public String getValNombre1() {
		return valNombre1;
	}
	/**
	 * @param valNombre1 The valNombre1 to set.
	 */
	public void setValNombre1(String valNombre1) {
		this.valNombre1 = valNombre1;
	}
	
	/**
	 * @return Returns the valNombre2.
	 */
	public String getValNombre2() {
		return valNombre2;
	}
	/**
	 * @param valNombre2 The valNombre2 to set.
	 */
	public void setValNombre2(String valNombre2) {
		this.valNombre2 = valNombre2;
	}
	
	/**
	 * @return Returns the valNombreVia.
	 */
	public String getValNombreVia() {
		return valNombreVia;
	}
	/**
	 * @param valNombreVia The valNombreVia to set.
	 */
	public void setValNombreVia(String valNombreVia) {
		this.valNombreVia = valNombreVia;
	}
	
	/**
	 * @return Returns the valRegionArribo.
	 */
	public String getValRegionArribo() {
		return valRegionArribo;
	}
	/**
	 * @param valRegionArribo The valRegionArribo to set.
	 */
	public void setValRegionArribo(String valRegionArribo) {
		this.valRegionArribo = valRegionArribo;
	}
	/**
	 * @return Returns the valTelfCliente.
	 */
	public String getValTelfCliente() {
		return valTelfCliente;
	}
	/**
	 * @param valTelfCliente The valTelfCliente to set.
	 */
	public void setValTelfCliente(String valTelfCliente) {
		this.valTelfCliente = valTelfCliente;
	}
	
	/**
	 * @return Returns the valTelfTrabajo.
	 */
	public String getValTelfTrabajo() {
		return valTelfTrabajo;
	}
	/**
	 * @param valTelfTrabajo The valTelfTrabajo to set.
	 */
	public void setValTelfTrabajo(String valTelfTrabajo) {
		this.valTelfTrabajo = valTelfTrabajo;
	}
	/**
	 * @return Returns the valZonaArribo.
	 */
	public String getValZonaArribo() {
		return valZonaArribo;
	}
	/**
	 * @param valZonaArribo The valZonaArribo to set.
	 */
	public void setValZonaArribo(String valZonaArribo) {
		this.valZonaArribo = valZonaArribo;
	}
	/**
	 * @return Returns the numSecuencia.
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}
	/**
	 * @param numSecuencia The numSecuencia to set.
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	/**
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLote The numLote to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	
	/**
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso The fechaProceso to set.
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	/**
	 * @return Returns the detalle.
	 */
	public String getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle The detalle to set.
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getDireccionEntrega() {
		return direccionEntrega;
	}
	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}
	public String getTelefonoEntrega() {
		return telefonoEntrega;
	}
	public void setTelefonoEntrega(String telefonoEntrega) {
		this.telefonoEntrega = telefonoEntrega;
	}
	public String getCelularEntrega() {
		return celularEntrega;
	}
	public void setCelularEntrega(String celularEntrega) {
		this.celularEntrega = celularEntrega;
	}
	/**
	 * @return the indicadorValidacionOK
	 */
	public String getIndicadorValidacionOK() {
		return indicadorValidacionOK;
	}
	/**
	 * @param indicadorValidacionOK the indicadorValidacionOK to set
	 */
	public void setIndicadorValidacionOK(String indicadorValidacionOK) {
		this.indicadorValidacionOK = indicadorValidacionOK;
	}
	/**
	 * @return the codigoCiudadDomicilio
	 */
	public String getCodigoCiudadDomicilio() {
		return codigoCiudadDomicilio;
	}
	/**
	 * @param codigoCiudadDomicilio the codigoCiudadDomicilio to set
	 */
	public void setCodigoCiudadDomicilio(String codigoCiudadDomicilio) {
		this.codigoCiudadDomicilio = codigoCiudadDomicilio;
	}
	/**
	 * @return the codigoCiudadDomicilioUbigeo
	 */
	public String getCodigoCiudadDomicilioUbigeo() {
		return codigoCiudadDomicilioUbigeo;
	}
	/**
	 * @param codigoCiudadDomicilioUbigeo the codigoCiudadDomicilioUbigeo to set
	 */
	public void setCodigoCiudadDomicilioUbigeo(String codigoCiudadDomicilioUbigeo) {
		this.codigoCiudadDomicilioUbigeo = codigoCiudadDomicilioUbigeo;
	}
	/**
	 * @return the villaPoblacionDomicilio
	 */
	public String getVillaPoblacionDomicilio() {
		return villaPoblacionDomicilio;
	}
	/**
	 * @param villaPoblacionDomicilio the villaPoblacionDomicilio to set
	 */
	public void setVillaPoblacionDomicilio(String villaPoblacionDomicilio) {
		this.villaPoblacionDomicilio = villaPoblacionDomicilio;
	}
	/**
	 * @return the codigoCiudadEntrega
	 */
	public String getCodigoCiudadEntrega() {
		return codigoCiudadEntrega;
	}
	/**
	 * @param codigoCiudadEntrega the codigoCiudadEntrega to set
	 */
	public void setCodigoCiudadEntrega(String codigoCiudadEntrega) {
		this.codigoCiudadEntrega = codigoCiudadEntrega;
	}
	/**
	 * @return the codigoCiudadEntregaUbigeo
	 */
	public String getCodigoCiudadEntregaUbigeo() {
		return codigoCiudadEntregaUbigeo;
	}
	/**
	 * @param codigoCiudadEntregaUbigeo the codigoCiudadEntregaUbigeo to set
	 */
	public void setCodigoCiudadEntregaUbigeo(String codigoCiudadEntregaUbigeo) {
		this.codigoCiudadEntregaUbigeo = codigoCiudadEntregaUbigeo;
	}
	/**
	 * @return the villaPoblacionEntrega
	 */
	public String getVillaPoblacionEntrega() {
		return villaPoblacionEntrega;
	}
	/**
	 * @param villaPoblacionEntrega the villaPoblacionEntrega to set
	 */
	public void setVillaPoblacionEntrega(String villaPoblacionEntrega) {
		this.villaPoblacionEntrega = villaPoblacionEntrega;
	}
	/**
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}
	/**
	 * @param nacionalidad the nacionalidad to set
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 * @return the territorioCorresponde
	 */
	public String getTerritorioCorresponde() {
		return territorioCorresponde;
	}
	/**
	 * @param territorioCorresponde the territorioCorresponde to set
	 */
	public void setTerritorioCorresponde(String territorioCorresponde) {
		this.territorioCorresponde = territorioCorresponde;
	}
	/**
	 * @return the direccionDomicilioIgualDireccionEntrega
	 */
	public String getDireccionDomicilioIgualDireccionEntrega() {
		return direccionDomicilioIgualDireccionEntrega;
	}
	/**
	 * @param direccionDomicilioIgualDireccionEntrega the direccionDomicilioIgualDireccionEntrega to set
	 */
	public void setDireccionDomicilioIgualDireccionEntrega(
			String direccionDomicilioIgualDireccionEntrega) {
		this.direccionDomicilioIgualDireccionEntrega = direccionDomicilioIgualDireccionEntrega;
	}
	/**
	 * @return the dirDomManzana
	 */
	public String getDirDomManzana() {
		return dirDomManzana;
	}
	/**
	 * @param dirDomManzana the dirDomManzana to set
	 */
	public void setDirDomManzana(String dirDomManzana) {
		this.dirDomManzana = dirDomManzana;
	}
	/**
	 * @return the dirDomEtapa
	 */
	public String getDirDomEtapa() {
		return dirDomEtapa;
	}
	/**
	 * @param dirDomEtapa the dirDomEtapa to set
	 */
	public void setDirDomEtapa(String dirDomEtapa) {
		this.dirDomEtapa = dirDomEtapa;
	}
	/**
	 * @return the dirDomCallePrincipal
	 */
	public String getDirDomCallePrincipal() {
		return dirDomCallePrincipal;
	}
	/**
	 * @param dirDomCallePrincipal the dirDomCallePrincipal to set
	 */
	public void setDirDomCallePrincipal(String dirDomCallePrincipal) {
		this.dirDomCallePrincipal = dirDomCallePrincipal;
	}
	/**
	 * @return the dirDomCalleSecundaria
	 */
	public String getDirDomCalleSecundaria() {
		return dirDomCalleSecundaria;
	}
	/**
	 * @param dirDomCalleSecundaria the dirDomCalleSecundaria to set
	 */
	public void setDirDomCalleSecundaria(String dirDomCalleSecundaria) {
		this.dirDomCalleSecundaria = dirDomCalleSecundaria;
	}
	/**
	 * @return the dirDomNumero
	 */
	public String getDirDomNumero() {
		return dirDomNumero;
	}
	/**
	 * @param dirDomNumero the dirDomNumero to set
	 */
	public void setDirDomNumero(String dirDomNumero) {
		this.dirDomNumero = dirDomNumero;
	}
	/**
	 * @return the dirDomReferencia
	 */
	public String getDirDomReferencia() {
		return dirDomReferencia;
	}
	/**
	 * @param dirDomReferencia the dirDomReferencia to set
	 */
	public void setDirDomReferencia(String dirDomReferencia) {
		this.dirDomReferencia = dirDomReferencia;
	}
	/**
	 * @return the dirEntBarrio
	 */
	public String getDirEntBarrio() {
		return dirEntBarrio;
	}
	/**
	 * @param dirEntBarrio the dirEntBarrio to set
	 */
	public void setDirEntBarrio(String dirEntBarrio) {
		this.dirEntBarrio = dirEntBarrio;
	}
	/**
	 * @return the dirEntManzana
	 */
	public String getDirEntManzana() {
		return dirEntManzana;
	}
	/**
	 * @param dirEntManzana the dirEntManzana to set
	 */
	public void setDirEntManzana(String dirEntManzana) {
		this.dirEntManzana = dirEntManzana;
	}
	/**
	 * @return the dirEntEtapa
	 */
	public String getDirEntEtapa() {
		return dirEntEtapa;
	}
	/**
	 * @param dirEntEtapa the dirEntEtapa to set
	 */
	public void setDirEntEtapa(String dirEntEtapa) {
		this.dirEntEtapa = dirEntEtapa;
	}
	/**
	 * @return the dirEntCallePrincipal
	 */
	public String getDirEntCallePrincipal() {
		return dirEntCallePrincipal;
	}
	/**
	 * @param dirEntCallePrincipal the dirEntCallePrincipal to set
	 */
	public void setDirEntCallePrincipal(String dirEntCallePrincipal) {
		this.dirEntCallePrincipal = dirEntCallePrincipal;
	}
	/**
	 * @return the dirEntCalleSecundaria
	 */
	public String getDirEntCalleSecundaria() {
		return dirEntCalleSecundaria;
	}
	/**
	 * @param dirEntCalleSecundaria the dirEntCalleSecundaria to set
	 */
	public void setDirEntCalleSecundaria(String dirEntCalleSecundaria) {
		this.dirEntCalleSecundaria = dirEntCalleSecundaria;
	}
	/**
	 * @return the dirEntNumero
	 */
	public String getDirEntNumero() {
		return dirEntNumero;
	}
	/**
	 * @param dirEntNumero the dirEntNumero to set
	 */
	public void setDirEntNumero(String dirEntNumero) {
		this.dirEntNumero = dirEntNumero;
	}
	/**
	 * @return the dirEntReferencia
	 */
	public String getDirEntReferencia() {
		return dirEntReferencia;
	}
	/**
	 * @param dirEntReferencia the dirEntReferencia to set
	 */
	public void setDirEntReferencia(String dirEntReferencia) {
		this.dirEntReferencia = dirEntReferencia;
	}
}
