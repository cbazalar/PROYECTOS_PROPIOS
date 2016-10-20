package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;



/**
 * @author peextsapaza
 *
 */
public class Cliente extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long oid;
	private String codigo;
	private Integer indicadorFichaInscripcion;
	private Long oidPais;
	private String digitoControl;
	private String apellido1;
	private String apellido2;
	private String nombre1;
	private String nombre2;
	private String tratamiento;
	private String criterioBusqueda1;
	private String criterioBusqueda2;
	private String sexo;
	private Date fechaIngreso;
	private Long oidFormaPago;
	private String apellidoCasada;
	private Long deudaAnterior;

	private String codigoPais;
	
	//clases relacionados con Cliente
	private ClienteAdicional clienteAdicional;
	private ClienteHistoricoEstatus clienteHistoricoEstatus; 
	private ClientePrimerContacto clientePrimerContacto;
	private List listClienteSubTipo;
	private ClienteIdentificacion clienteIdentificacion;
	private List listClienteDireccion;
	private ClienteUnidadAdministrativa clienteUnidadAdministrativa;
	private List listClienteComunicacion;
	private List listClienteVinculo;
	private ClienteMarca clienteMarca;
	private List listClienteObservacion;
		
	//parte web, caso duplacyzone
	private Long oidPeriodoIngreso;
	
	private List listClienteIdentificacion;
	private List listClienteConcursoPremio;
	private ClienteUnidadAdministrativa clienteUnidadAdministrativaNew;
	
	//referencia del cliente
	private ClienteReferencias clienteReferencias;
	
	private Long oidSubTipoAval;
	
	private List listClienteAval;
	private String codigoUsuario;
	private String codigoAnterior;
	
	private String descripcionCargo;
	private String unidadAdministrativa;

	//campo de auditoria
	private String usuarioModifica;
	
	//historico de cambios
	private ClienteHistoricoDatos clienteHistoricoDatos;
	
	//indicador Origen
	private String indicadorOrigen;
	
	//Ejecutiva
	private boolean esEjecutiva;
	private boolean indicadorCamposAdicionales;
	
	/**
	 * @return the clienteReferencias
	 */
	public ClienteReferencias getClienteReferencias() {
		return clienteReferencias;
	}

	/**
	 * @param clienteReferencias the clienteReferencias to set
	 */
	public void setClienteReferencias(ClienteReferencias clienteReferencias) {
		this.clienteReferencias = clienteReferencias;
	}

	public Cliente() {
		
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the apellido1.
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @param apellido1 The apellido1 to set.
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @return Returns the apellido2.
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @param apellido2 The apellido2 to set.
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * @return Returns the apellidoCasada.
	 */
	public String getApellidoCasada() {
		return apellidoCasada;
	}

	/**
	 * @param apellidoCasada The apellidoCasada to set.
	 */
	public void setApellidoCasada(String apellidoCasada) {
		this.apellidoCasada = apellidoCasada;
	}

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the criterioBusqueda1.
	 */
	public String getCriterioBusqueda1() {
		return criterioBusqueda1;
	}

	/**
	 * @param criterioBusqueda1 The criterioBusqueda1 to set.
	 */
	public void setCriterioBusqueda1(String criterioBusqueda1) {
		this.criterioBusqueda1 = criterioBusqueda1;
	}

	/**
	 * @return Returns the criterioBusqueda2.
	 */
	public String getCriterioBusqueda2() {
		return criterioBusqueda2;
	}

	/**
	 * @param criterioBusqueda2 The criterioBusqueda2 to set.
	 */
	public void setCriterioBusqueda2(String criterioBusqueda2) {
		this.criterioBusqueda2 = criterioBusqueda2;
	}

	/**
	 * @return Returns the deudaAnterior.
	 */
	public Long getDeudaAnterior() {
		return deudaAnterior;
	}

	/**
	 * @param deudaAnterior The deudaAnterior to set.
	 */
	public void setDeudaAnterior(Long deudaAnterior) {
		this.deudaAnterior = deudaAnterior;
	}

	/**
	 * @return Returns the digitoControl.
	 */
	public String getDigitoControl() {
		return digitoControl;
	}

	/**
	 * @param digitoControl The digitoControl to set.
	 */
	public void setDigitoControl(String digitoControl) {
		this.digitoControl = digitoControl;
	}

	/**
	 * @return Returns the fechaIngreso.
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso The fechaIngreso to set.
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return Returns the indicadorFichaInscripcion.
	 */
	public Integer getIndicadorFichaInscripcion() {
		return indicadorFichaInscripcion;
	}

	/**
	 * @param indicadorFichaInscripcion The indicadorFichaInscripcion to set.
	 */
	public void setIndicadorFichaInscripcion(Integer indicadorFichaInscripcion) {
		this.indicadorFichaInscripcion = indicadorFichaInscripcion;
	}

	/**
	 * @return Returns the nombre1.
	 */
	public String getNombre1() {
		return nombre1;
	}

	/**
	 * @param nombre1 The nombre1 to set.
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	/**
	 * @return Returns the nombre2.
	 */
	public String getNombre2() {
		return nombre2;
	}

	/**
	 * @param nombre2 The nombre2 to set.
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	/**
	 * @return Returns the oid.
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid The oid to set.
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return Returns the oidFormaPago.
	 */
	public Long getOidFormaPago() {
		return oidFormaPago;
	}

	/**
	 * @param oidFormaPago The oidFormaPago to set.
	 */
	public void setOidFormaPago(Long oidFormaPago) {
		this.oidFormaPago = oidFormaPago;
	}

	/**
	 * @return Returns the oidPais.
	 */
	public Long getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais The oidPais to set.
	 */
	public void setOidPais(Long oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return Returns the sexo.
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo The sexo to set.
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return Returns the tratamiento.
	 */
	public String getTratamiento() {
		return tratamiento;
	}

	/**
	 * @param tratamiento The tratamiento to set.
	 */
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	/**
	 * @return Returns the clienteAdicional.
	 */
	public ClienteAdicional getClienteAdicional() {
		return clienteAdicional;
	}

	/**
	 * @param clienteAdicional The clienteAdicional to set.
	 */
	public void setClienteAdicional(ClienteAdicional clienteAdicional) {
		this.clienteAdicional = clienteAdicional;
	}

	/**
	 * @return Returns the clienteIdentificacion.
	 */
	public ClienteIdentificacion getClienteIdentificacion() {
		return clienteIdentificacion;
	}

	/**
	 * @param clienteIdentificacion The clienteIdentificacion to set.
	 */
	public void setClienteIdentificacion(ClienteIdentificacion clienteIdentificacion) {
		this.clienteIdentificacion = clienteIdentificacion;
	}

	/**
	 * @return Returns the clienteMarca.
	 */
	public ClienteMarca getClienteMarca() {
		return clienteMarca;
	}

	/**
	 * @param clienteMarca The clienteMarca to set.
	 */
	public void setClienteMarca(ClienteMarca clienteMarca) {
		this.clienteMarca = clienteMarca;
	}

	/**
	 * @return Returns the clientePrimerContacto.
	 */
	public ClientePrimerContacto getClientePrimerContacto() {
		return clientePrimerContacto;
	}

	/**
	 * @param clientePrimerContacto The clientePrimerContacto to set.
	 */
	public void setClientePrimerContacto(ClientePrimerContacto clientePrimerContacto) {
		this.clientePrimerContacto = clientePrimerContacto;
	}

	/**
	 * @return Returns the clienteUnidadAdministrativa.
	 */
	public ClienteUnidadAdministrativa getClienteUnidadAdministrativa() {
		return clienteUnidadAdministrativa;
	}

	/**
	 * @param clienteUnidadAdministrativa The clienteUnidadAdministrativa to set.
	 */
	public void setClienteUnidadAdministrativa(
			ClienteUnidadAdministrativa clienteUnidadAdministrativa) {
		this.clienteUnidadAdministrativa = clienteUnidadAdministrativa;
	}

	/**
	 * @return Returns the listClienteSubTipo.
	 */
	public List getListClienteSubTipo() {
		return listClienteSubTipo;
	}

	/**
	 * @param listClienteSubTipo The listClienteSubTipo to set.
	 */
	public void setListClienteSubTipo(List listClienteSubTipo) {
		this.listClienteSubTipo = listClienteSubTipo;
	}

	/**
	 * @return Returns the listClienteVinculo.
	 */
	public List getListClienteVinculo() {
		return listClienteVinculo;
	}

	/**
	 * @param listClienteVinculo The listClienteVinculo to set.
	 */
	public void setListClienteVinculo(List listClienteVinculo) {
		this.listClienteVinculo = listClienteVinculo;
	}

	/**
	 * @return Returns the clienteHistoricoEstatus.
	 */
	public ClienteHistoricoEstatus getClienteHistoricoEstatus() {
		return clienteHistoricoEstatus;
	}

	/**
	 * @param clienteHistoricoEstatus The clienteHistoricoEstatus to set.
	 */
	public void setClienteHistoricoEstatus(
			ClienteHistoricoEstatus clienteHistoricoEstatus) {
		this.clienteHistoricoEstatus = clienteHistoricoEstatus;
	}

	/**
	 * @return Returns the listClienteDireccion.
	 */
	public List getListClienteDireccion() {
		return listClienteDireccion;
	}

	/**
	 * @param listClienteDireccion The listClienteDireccion to set.
	 */
	public void setListClienteDireccion(List listClienteDireccion) {
		this.listClienteDireccion = listClienteDireccion;
	}

	/**
	 * @return Returns the listClienteComunicacion.
	 */
	public List getListClienteComunicacion() {
		return listClienteComunicacion;
	}

	/**
	 * @param listClienteComunicacion The listClienteComunicacion to set.
	 */
	public void setListClienteComunicacion(List listClienteComunicacion) {
		this.listClienteComunicacion = listClienteComunicacion;
	}

	/**
	 * @return Returns the oidPeriodoIngreso.
	 */
	public Long getOidPeriodoIngreso() {
		return oidPeriodoIngreso;
	}

	/**
	 * @param oidPeriodoIngreso The oidPeriodoIngreso to set.
	 */
	public void setOidPeriodoIngreso(Long oidPeriodoIngreso) {
		this.oidPeriodoIngreso = oidPeriodoIngreso;
	}

	/**
	 * @return Returns the listClienteObservacion.
	 */
	public List getListClienteObservacion() {
		return listClienteObservacion;
	}

	/**
	 * @param listClienteObservacion The listClienteObservacion to set.
	 */
	public void setListClienteObservacion(List listClienteObservacion) {
		this.listClienteObservacion = listClienteObservacion;
	}

	/**
	 * @return the listClienteIdentificacion
	 */
	public List getListClienteIdentificacion() {
		return listClienteIdentificacion;
	}

	/**
	 * @param listClienteIdentificacion the listClienteIdentificacion to set
	 */
	public void setListClienteIdentificacion(List listClienteIdentificacion) {
		this.listClienteIdentificacion = listClienteIdentificacion;
	}

	/**
	 * @return the listClienteConcursoPremio
	 */
	public List getListClienteConcursoPremio() {
		return listClienteConcursoPremio;
	}

	/**
	 * @param listClienteConcursoPremio the listClienteConcursoPremio to set
	 */
	public void setListClienteConcursoPremio(List listClienteConcursoPremio) {
		this.listClienteConcursoPremio = listClienteConcursoPremio;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the clienteUnidadAdministrativaNew
	 */
	public ClienteUnidadAdministrativa getClienteUnidadAdministrativaNew() {
		return clienteUnidadAdministrativaNew;
	}

	/**
	 * @param clienteUnidadAdministrativaNew the clienteUnidadAdministrativaNew to set
	 */
	public void setClienteUnidadAdministrativaNew(
			ClienteUnidadAdministrativa clienteUnidadAdministrativaNew) {
		this.clienteUnidadAdministrativaNew = clienteUnidadAdministrativaNew;
	}

	/**
	 * @return the oidSubTipoAval
	 */
	public Long getOidSubTipoAval() {
		return oidSubTipoAval;
	}

	/**
	 * @param oidSubTipoAval the oidSubTipoAval to set
	 */
	public void setOidSubTipoAval(Long oidSubTipoAval) {
		this.oidSubTipoAval = oidSubTipoAval;
	}

	/**
	 * @return the listClienteAval
	 */
	public List getListClienteAval() {
		return listClienteAval;
	}

	/**
	 * @param listClienteAval the listClienteAval to set
	 */
	public void setListClienteAval(List listClienteAval) {
		this.listClienteAval = listClienteAval;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the codigoAnterior
	 */
	public String getCodigoAnterior() {
		return codigoAnterior;
	}

	/**
	 * @param codigoAnterior the codigoAnterior to set
	 */
	public void setCodigoAnterior(String codigoAnterior) {
		this.codigoAnterior = codigoAnterior;
	}

	/**
	 * @return the descripcionCargo
	 */
	public String getDescripcionCargo() {
		return descripcionCargo;
	}

	/**
	 * @param descripcionCargo the descripcionCargo to set
	 */
	public void setDescripcionCargo(String descripcionCargo) {
		this.descripcionCargo = descripcionCargo;
	}

	/**
	 * @return the unidadAdministrativa
	 */
	public String getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	/**
	 * @param unidadAdministrativa the unidadAdministrativa to set
	 */
	public void setUnidadAdministrativa(String unidadAdministrativa) {
		this.unidadAdministrativa = unidadAdministrativa;
	}

	/**
	 * @return the usuarioModifica
	 */
	public String getUsuarioModifica() {
		return usuarioModifica;
	}

	/**
	 * @param usuarioModifica the usuarioModifica to set
	 */
	public void setUsuarioModifica(String usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	/**
	 * @return the clienteHistoricoDatos
	 */
	public ClienteHistoricoDatos getClienteHistoricoDatos() {
		return clienteHistoricoDatos;
	}

	/**
	 * @param clienteHistoricoDatos the clienteHistoricoDatos to set
	 */
	public void setClienteHistoricoDatos(ClienteHistoricoDatos clienteHistoricoDatos) {
		this.clienteHistoricoDatos = clienteHistoricoDatos;
	}

	/**
	 * @return the indicadorOrigen
	 */
	public String getIndicadorOrigen() {
		return indicadorOrigen;
	}

	/**
	 * @param indicadorOrigen the indicadorOrigen to set
	 */
	public void setIndicadorOrigen(String indicadorOrigen) {
		this.indicadorOrigen = indicadorOrigen;
	}

	/**
	 * @return the esEjecutiva
	 */
	public boolean isEsEjecutiva() {
		return esEjecutiva;
	}

	/**
	 * @param esEjecutiva the esEjecutiva to set
	 */
	public void setEsEjecutiva(boolean esEjecutiva) {
		this.esEjecutiva = esEjecutiva;
	}

	/**
	 * @return the indicadorCamposAdicionales
	 */
	public boolean isIndicadorCamposAdicionales() {
		return indicadorCamposAdicionales;
	}

	/**
	 * @param indicadorCamposAdicionales the indicadorCamposAdicionales to set
	 */
	public void setIndicadorCamposAdicionales(boolean indicadorCamposAdicionales) {
		this.indicadorCamposAdicionales = indicadorCamposAdicionales;
	}
	
	

}
