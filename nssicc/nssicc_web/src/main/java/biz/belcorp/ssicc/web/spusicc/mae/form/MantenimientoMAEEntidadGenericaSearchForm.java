package biz.belcorp.ssicc.web.spusicc.mae.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoMAEEntidadGenericaSearchForm extends BaseSearchForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String codigoPais;
	private String codigoEntidad;
	
	private String codigoInterfaz;
	
	private String codigo;
	private String descripcion;
	
	private String codigoTipoCliente;
	private String descripcionTipoCliente;
	private String indicadorEvaluarEstatus;
	private String indicadorEmpleado;
	
	private String codigoTipoClienteST;
	private String codigoSubtipoCliente;
	private String descripcionSubtipoCliente;
	
	private String codigoTipoEstatus;
	private String codigoEstatusCliente;
	private String descripcionEstatusCliente;
	private String estatusPosteriorPosible;
	
	private String codigoSAP;
	private String indicadorRegistro;
	private String numDias;
	
	private String codigoTipoClienteCL;
	private String codigoTipoClasificacion;
	private String codigoTipoSubCliente;
	private String codigoClasificacion;
	private String descripcionClasificacion;
	private String codTipoClasificacion;
	private String descripcionTipoClasificacion;
	private String codigoTipoSubClienteTC;
	private String codigoTipoClienteTC;
	private String descripcionTipoEstatus;
	private String marcaTipoEstatus;
	
	private String codigoCivil;
	private String descripcionCivil;
	private String codigoTipoDirec;
	private String descripcionTipoDirec;
	private String codigoTipoComu;
	private String descripcionTipoComu;
	private String codigoTipoVinculo;
	private String descripcionVinculo;
	private String recomendado;
	
	
	private String codTipoDocumento;
	private String siglas;
	private String descripcionTipoDocumento;
	
	private String atributo1;
	private String atributo2;
	
	private String subTipoClasificacion;
	private String idcodigoTipoSubCliente;
	private String idcodigoTipoClasificacion;
	
	private String oidTipoBloqueo;
	private String oidProcesoBloqueo;
	private String oidAccionBloqueo;
	private String indicadorEstado;
	
	
	/**
	 * @return the idcodigoTipoSubCliente
	 */
	public String getIdcodigoTipoSubCliente() {
		return idcodigoTipoSubCliente;
	}
	/**
	 * @param idcodigoTipoSubCliente the idcodigoTipoSubCliente to set
	 */
	public void setIdcodigoTipoSubCliente(String idcodigoTipoSubCliente) {
		this.idcodigoTipoSubCliente = idcodigoTipoSubCliente;
	}
	/**
	 * @return the idcodigoTipoClasificacion
	 */
	public String getIdcodigoTipoClasificacion() {
		return idcodigoTipoClasificacion;
	}
	/**
	 * @param idcodigoTipoClasificacion the idcodigoTipoClasificacion to set
	 */
	public void setIdcodigoTipoClasificacion(String idcodigoTipoClasificacion) {
		this.idcodigoTipoClasificacion = idcodigoTipoClasificacion;
	}
	/**
	 * @return the subTipoClasificacion
	 */
	public String getSubTipoClasificacion() {
		return subTipoClasificacion;
	}
	/**
	 * @param subTipoClasificacion the subTipoClasificacion to set
	 */
	public void setSubTipoClasificacion(String subTipoClasificacion) {
		this.subTipoClasificacion = subTipoClasificacion;
	}
	/**
	 * @return the codTipoDocumento
	 */
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	/**
	 * @param codTipoDocumento the codTipoDocumento to set
	 */
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	/**
	 * @return the siglas
	 */
	public String getSiglas() {
		return siglas;
	}
	/**
	 * @param siglas the siglas to set
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	/**
	 * @return the descripcionTipoDocumento
	 */
	public String getDescripcionTipoDocumento() {
		return descripcionTipoDocumento;
	}
	/**
	 * @param descripcionTipoDocumento the descripcionTipoDocumento to set
	 */
	public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
		this.descripcionTipoDocumento = descripcionTipoDocumento;
	}
	/**
	 * @return the atributo1
	 */
	public String getAtributo1() {
		return atributo1;
	}
	/**
	 * @param atributo1 the atributo1 to set
	 */
	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}
	/**
	 * @return the atributo2
	 */
	public String getAtributo2() {
		return atributo2;
	}
	/**
	 * @param atributo2 the atributo2 to set
	 */
	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}
	/**
	 * @return the codigoCivil
	 */
	public String getCodigoCivil() {
		return codigoCivil;
	}
	/**
	 * @param codigoCivil the codigoCivil to set
	 */
	public void setCodigoCivil(String codigoCivil) {
		this.codigoCivil = codigoCivil;
	}
	/**
	 * @return the descripcionCivil
	 */
	public String getDescripcionCivil() {
		return descripcionCivil;
	}
	/**
	 * @param descripcionCivil the descripcionCivil to set
	 */
	public void setDescripcionCivil(String descripcionCivil) {
		this.descripcionCivil = descripcionCivil;
	}
	/**
	 * @return the codigoTipoDirec
	 */
	public String getCodigoTipoDirec() {
		return codigoTipoDirec;
	}
	/**
	 * @param codigoTipoDirec the codigoTipoDirec to set
	 */
	public void setCodigoTipoDirec(String codigoTipoDirec) {
		this.codigoTipoDirec = codigoTipoDirec;
	}
	/**
	 * @return the descripcionTipoDirec
	 */
	public String getDescripcionTipoDirec() {
		return descripcionTipoDirec;
	}
	/**
	 * @param descripcionTipoDirec the descripcionTipoDirec to set
	 */
	public void setDescripcionTipoDirec(String descripcionTipoDirec) {
		this.descripcionTipoDirec = descripcionTipoDirec;
	}
	/**
	 * @return the codigoTipoComu
	 */
	public String getCodigoTipoComu() {
		return codigoTipoComu;
	}
	/**
	 * @param codigoTipoComu the codigoTipoComu to set
	 */
	public void setCodigoTipoComu(String codigoTipoComu) {
		this.codigoTipoComu = codigoTipoComu;
	}
	/**
	 * @return the descripcionTipoComu
	 */
	public String getDescripcionTipoComu() {
		return descripcionTipoComu;
	}
	/**
	 * @param descripcionTipoComu the descripcionTipoComu to set
	 */
	public void setDescripcionTipoComu(String descripcionTipoComu) {
		this.descripcionTipoComu = descripcionTipoComu;
	}
	/**
	 * @return the codigoTipoVinculo
	 */
	public String getCodigoTipoVinculo() {
		return codigoTipoVinculo;
	}
	/**
	 * @param codigoTipoVinculo the codigoTipoVinculo to set
	 */
	public void setCodigoTipoVinculo(String codigoTipoVinculo) {
		this.codigoTipoVinculo = codigoTipoVinculo;
	}
	/**
	 * @return the descripcionVinculo
	 */
	public String getDescripcionVinculo() {
		return descripcionVinculo;
	}
	/**
	 * @param descripcionVinculo the descripcionVinculo to set
	 */
	public void setDescripcionVinculo(String descripcionVinculo) {
		this.descripcionVinculo = descripcionVinculo;
	}
	/**
	 * @return the recomendado
	 */
	public String getRecomendado() {
		return recomendado;
	}
	/**
	 * @param recomendado the recomendado to set
	 */
	public void setRecomendado(String recomendado) {
		this.recomendado = recomendado;
	}
	/**
	 * @return the descripcionTipoEstatus
	 */
	public String getDescripcionTipoEstatus() {
		return descripcionTipoEstatus;
	}
	/**
	 * @param descripcionTipoEstatus the descripcionTipoEstatus to set
	 */
	public void setDescripcionTipoEstatus(String descripcionTipoEstatus) {
		this.descripcionTipoEstatus = descripcionTipoEstatus;
	}
	/**
	 * @return the marcaTipoEstatus
	 */
	public String getMarcaTipoEstatus() {
		return marcaTipoEstatus;
	}
	/**
	 * @param marcaTipoEstatus the marcaTipoEstatus to set
	 */
	public void setMarcaTipoEstatus(String marcaTipoEstatus) {
		this.marcaTipoEstatus = marcaTipoEstatus;
	}
	/**
	 * @return the codigoTipoClienteTC
	 */
	public String getCodigoTipoClienteTC() {
		return codigoTipoClienteTC;
	}
	/**
	 * @param codigoTipoClienteTC the codigoTipoClienteTC to set
	 */
	public void setCodigoTipoClienteTC(String codigoTipoClienteTC) {
		this.codigoTipoClienteTC = codigoTipoClienteTC;
	}
	/**
	 * @return the codigoTipoSubClienteTC
	 */
	public String getCodigoTipoSubClienteTC() {
		return codigoTipoSubClienteTC;
	}
	/**
	 * @param codigoTipoSubClienteTC the codigoTipoSubClienteTC to set
	 */
	public void setCodigoTipoSubClienteTC(String codigoTipoSubClienteTC) {
		this.codigoTipoSubClienteTC = codigoTipoSubClienteTC;
	}
	/**
	 * @return the codTipoClasificacion
	 */
	public String getCodTipoClasificacion() {
		return codTipoClasificacion;
	}
	/**
	 * @param codTipoClasificacion the codTipoClasificacion to set
	 */
	public void setCodTipoClasificacion(String codTipoClasificacion) {
		this.codTipoClasificacion = codTipoClasificacion;
	}
	/**
	 * @return the descripcionTipoClasificacion
	 */
	public String getDescripcionTipoClasificacion() {
		return descripcionTipoClasificacion;
	}
	/**
	 * @param descripcionTipoClasificacion the descripcionTipoClasificacion to set
	 */
	public void setDescripcionTipoClasificacion(String descripcionTipoClasificacion) {
		this.descripcionTipoClasificacion = descripcionTipoClasificacion;
	}
	/**
	 * @return the codigoInterfaz
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}
	/**
	 * @param codigoInterfaz the codigoInterfaz to set
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}
	/**
	 * @return the codigoTipoClienteCL
	 */
	public String getCodigoTipoClienteCL() {
		return codigoTipoClienteCL;
	}
	/**
	 * @param codigoTipoClienteCL the codigoTipoClienteCL to set¿
	 */
	public void setCodigoTipoClienteCL(String codigoTipoClienteCL) {
		this.codigoTipoClienteCL = codigoTipoClienteCL;
	}
	/**
	 * @return the codigoTipoClasificacion
	 */
	public String getCodigoTipoClasificacion() {
		return codigoTipoClasificacion;
	}
	/**
	 * @param codigoTipoClasificacion the codigoTipoClasificacion to set
	 */
	public void setCodigoTipoClasificacion(String codigoTipoClasificacion) {
		this.codigoTipoClasificacion = codigoTipoClasificacion;
	}
	/**
	 * @return the codigoTipoSubCliente
	 */
	public String getCodigoTipoSubCliente() {
		return codigoTipoSubCliente;
	}
	/**
	 * @param codigoTipoSubCliente the codigoTipoSubCliente to set
	 */
	public void setCodigoTipoSubCliente(String codigoTipoSubCliente) {
		this.codigoTipoSubCliente = codigoTipoSubCliente;
	}
	/**
	 * @return the codigoClasificacion
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}
	/**
	 * @param codigoClasificacion the codigoClasificacion to set
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}
	/**
	 * @return the descripcionClasificacion
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}
	/**
	 * @param descripcionClasificacion the descripcionClasificacion to set
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
	}
	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}
	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	/**
	 * @return the indicadorRegistro
	 */
	public String getIndicadorRegistro() {
		return indicadorRegistro;
	}
	/**
	 * @param indicadorRegistro the indicadorRegistro to set
	 */
	public void setIndicadorRegistro(String indicadorRegistro) {
		this.indicadorRegistro = indicadorRegistro;
	}
	/**
	 * @return the numDias
	 */
	public String getNumDias() {
		return numDias;
	}
	/**
	 * @param numDias the numDias to set
	 */
	public void setNumDias(String numDias) {
		this.numDias = numDias;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoEntidad
	 */
	public String getCodigoEntidad() {
		return codigoEntidad;
	}
	/**
	 * @param codigoEntidad the codigoEntidad to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the codigoTipoCliente
	 */
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}
	/**
	 * @param codigoTipoCliente the codigoTipoCliente to set
	 */
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}
	/**
	 * @return the descripcionTipoCliente
	 */
	public String getDescripcionTipoCliente() {
		return descripcionTipoCliente;
	}
	/**
	 * @param descripcionTipoCliente the descripcionTipoCliente to set
	 */
	public void setDescripcionTipoCliente(String descripcionTipoCliente) {
		this.descripcionTipoCliente = descripcionTipoCliente;
	}
	/**
	 * @return the indicadorEvaluarEstatus
	 */
	public String getIndicadorEvaluarEstatus() {
		return indicadorEvaluarEstatus;
	}
	/**
	 * @param indicadorEvaluarEstatus the indicadorEvaluarEstatus to set
	 */
	public void setIndicadorEvaluarEstatus(String indicadorEvaluarEstatus) {
		this.indicadorEvaluarEstatus = indicadorEvaluarEstatus;
	}
	/**
	 * @return the indicadorEmpleado
	 */
	public String getIndicadorEmpleado() {
		return indicadorEmpleado;
	}
	/**
	 * @param indicadorEmpleado the indicadorEmpleado to set
	 */
	public void setIndicadorEmpleado(String indicadorEmpleado) {
		this.indicadorEmpleado = indicadorEmpleado;
	}
	
	
	
	/**
	 * @return the codigoTipoClienteST
	 */
	public String getCodigoTipoClienteST() {
		return codigoTipoClienteST;
	}
	/**
	 * @param codigoTipoClienteST the codigoTipoClienteST to set
	 */
	public void setCodigoTipoClienteST(String codigoTipoClienteST) {
		this.codigoTipoClienteST = codigoTipoClienteST;
	}
	/**
	 * @return the codigoSubtipoCliente
	 */
	public String getCodigoSubtipoCliente() {
		return codigoSubtipoCliente;
	}
	/**
	 * @param codigoSubtipoCliente the codigoSubtipoCliente to set
	 */
	public void setCodigoSubtipoCliente(String codigoSubtipoCliente) {
		this.codigoSubtipoCliente = codigoSubtipoCliente;
	}
	/**
	 * @return the descripcionSubtipoCliente
	 */
	public String getDescripcionSubtipoCliente() {
		return descripcionSubtipoCliente;
	}
	/**
	 * @param descripcionSubtipoCliente the descripcionSubtipoCliente to set
	 */
	public void setDescripcionSubtipoCliente(String descripcionSubtipoCliente) {
		this.descripcionSubtipoCliente = descripcionSubtipoCliente;
	}
	
	
	/**
	 * @return the codigoTipoEstatus
	 */
	public String getCodigoTipoEstatus() {
		return codigoTipoEstatus;
	}
	/**
	 * @param codigoTipoEstatus the codigoTipoEstatus to set
	 */
	public void setCodigoTipoEstatus(String codigoTipoEstatus) {
		this.codigoTipoEstatus = codigoTipoEstatus;
	}
	/**
	 * @return the codigoEstatusCliente
	 */
	public String getCodigoEstatusCliente() {
		return codigoEstatusCliente;
	}
	/**
	 * @param codigoEstatusCliente the codigoEstatusCliente to set
	 */
	public void setCodigoEstatusCliente(String codigoEstatusCliente) {
		this.codigoEstatusCliente = codigoEstatusCliente;
	}
	/**
	 * @return the descripcionEstatusCliente
	 */
	public String getDescripcionEstatusCliente() {
		return descripcionEstatusCliente;
	}
	/**
	 * @param descripcionEstatusCliente the descripcionEstatusCliente to set
	 */
	public void setDescripcionEstatusCliente(String descripcionEstatusCliente) {
		this.descripcionEstatusCliente = descripcionEstatusCliente;
	}
	/**
	 * @return the estatusPosteriorPosible
	 */
	public String getEstatusPosteriorPosible() {
		return estatusPosteriorPosible;
	}
	/**
	 * @param estatusPosteriorPosible the estatusPosteriorPosible to set
	 */
	public void setEstatusPosteriorPosible(String estatusPosteriorPosible) {
		this.estatusPosteriorPosible = estatusPosteriorPosible;
	}
	
	
	/**
	 * @return the oidTipoBloqueo
	 */
	public String getOidTipoBloqueo() {
		return oidTipoBloqueo;
	}
	
	/**
	 * @param oidTipoBloqueo the oidTipoBloqueo to set
	 */
	public void setOidTipoBloqueo(String oidTipoBloqueo) {
		this.oidTipoBloqueo = oidTipoBloqueo;
	}
	
	/**
	 * @return the oidProcesoBloqueo
	 */
	public String getOidProcesoBloqueo() {
		return oidProcesoBloqueo;
	}
	
	/**
	 * @param oidProcesoBloqueo the oidProcesoBloqueo to set
	 */
	public void setOidProcesoBloqueo(String oidProcesoBloqueo) {
		this.oidProcesoBloqueo = oidProcesoBloqueo;
	}
	
	/**
	 * @return the oidAccionBloqueo
	 */
	public String getOidAccionBloqueo() {
		return oidAccionBloqueo;
	}
	
	/**
	 * @param oidAccionBloqueo the oidAccionBloqueo to set
	 */
	public void setOidAccionBloqueo(String oidAccionBloqueo) {
		this.oidAccionBloqueo = oidAccionBloqueo;
	}
	
	
	/**
	 * @return the indicadorEstado
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}
	/**
	 * @param indicadorEstado the indicadorEstado to set
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}
}