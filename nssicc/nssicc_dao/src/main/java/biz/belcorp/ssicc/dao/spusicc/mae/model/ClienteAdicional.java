package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteAdicional extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oidCliente;
	private Long oidNivelSocioEconomico;
	private Long oid;
	private String codigoEmpleado;
	private Date fechaNacimiento;
	private Integer edad;
	private String ocupacion;
	private String profesion;

	private String centroTrabajo;
	private String cargo;
	private String centroEstudios;
	private String nivelSocioEconomico3;
	private Integer numeroHijos;
	private Integer numeroDependientes;
	private Integer numeroCampanasSinPedido;
	private Double ingresoFamiliar;
	private Double montoLineaCredito;
	
	private Long oidNivelRiesgo;
	private Long oidNivelEstudios;
	private Long oidNacionalidad;
	private Long oidEstadoCivil;
	private Long oidPeriodoNivelRiesgo;
	private Long oidPeriodoLineaCredito;
	private Integer indicadorCorrespondencia;
	private Integer indicadorActivo;
	private Long oidEstatusCliente;
	private Long oidCicloVida;
	private String email;
	private String codigoStatus;
	private String descripcionStatus;
	
	private Long oidTipoCutis;
	
	private Long oidOtrasMarcas;
	
	/* INI SA COS-SiCC-2013-0031 */
	private String indicadorImpresionPaqDoc;
	/* FIN SA COS-SiCC-2013-0031 */
	
	/* INI JJ PER-SiCC-2012-0329 */
	private String codigoCUB;
	
	private String indicadorCompromiso;
	private String motivo;
	
	/* INI JP PER-SiCC-2013-0480 */
	private String indicadorImpresionDocumentos;
	/* FIN JP PER-SiCC-2013-0480 */
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */
	
	private String codGrupoFuncional;
 	private String desGrupoFuncional;
 	private String usuRed;
 	private String codJefeCUB;
 	private String valRelContr;
 	
 	private String nomJefeDir;
 	private String valPueOrg;
 	
 	/* INI PER-SiCC-2014-0162 */
 	private String usuCarg; 
 	private Date fecCarg; 
 	private String tipCarg; 
 	private String indImprDocu; 
 	/* FIN PER-SiCC-2014-0162 */
 	
 	private String usuCargAnt;
 	private String indImprDocuAnt;
 	
 	/* INI PER-SiCC-2015-0007 */
 	private String indDocFiscal;
 	/* FIN PER-SiCC-2015-0007 */
 	
 	/* INI ECU-SiCC-2015-0036*/
 	private String valTipoPersona;
 	private String valOrigenIngreso;
 	/* FIN ECU-SiCC-2015-0036*/
 	
 	private Long codigoBanco;
 	private String cuentaBancaria;
 	
 	/* INI PER-SiCC-2015-0592*/
 	private String indicadorCalularPercepcion;
 	/* FIN PER-SiCC-2015-0592*/
 	
 	private String valBanco;
	private String valTipoCuenta;
	private String valCuentaCorriente;
 	
	/**
	 * @return
	 */
	public String getCodigoCUB() {
		return codigoCUB;
	}
	
	

	public String getValTipoPersona() {
		return valTipoPersona;
	}



	public void setValTipoPersona(String valTipoPersona) {
		this.valTipoPersona = valTipoPersona;
	}



	public String getValOrigenIngreso() {
		return valOrigenIngreso;
	}

	public void setValOrigenIngreso(String valOrigenIngreso) {
		this.valOrigenIngreso = valOrigenIngreso;
	}

	public String getUsuCarg() {
		return usuCarg;
	}

	public Date getFecCarg() {
		return fecCarg;
	}

	public String getTipCarg() {
		return tipCarg;
	}

	public String getIndImprDocu() {
		return indImprDocu;
	}

	public void setUsuCarg(String usuCarg) {
		this.usuCarg = usuCarg;
	}

	public void setFecCarg(Date fecCarg) {
		this.fecCarg = fecCarg;
	}

	public void setTipCarg(String tipCarg) {
		this.tipCarg = tipCarg;
	}

	public void setIndImprDocu(String indImprDocu) {
		this.indImprDocu = indImprDocu;
	}

	/**
	 * @param codigoCUB
	 */
	public void setCodigoCUB(String codigoCUB) {
		this.codigoCUB = codigoCUB;
	}
	/* FIN JJ PER-SiCC-2012-0329 */
	/**
	 * @return the codigoStatus
	 */
	public String getCodigoStatus() {
		return codigoStatus;
	}

	/**
	 * @param codigoStatus the codigoStatus to set
	 */
	public void setCodigoStatus(String codigoStatus) {
		this.codigoStatus = codigoStatus;
	}

	/**
	 * @return the descripcionStatus
	 */
	public String getDescripcionStatus() {
		return descripcionStatus;
	}

	/**
	 * @param descripcionStatus the descripcionStatus to set
	 */
	public void setDescripcionStatus(String descripcionStatus) {
		this.descripcionStatus = descripcionStatus;
	}

	public ClienteAdicional() {
		
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
	 * @return Returns the cargo.
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo The cargo to set.
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return Returns the centroEstudios.
	 */
	public String getCentroEstudios() {
		return centroEstudios;
	}

	/**
	 * @param centroEstudios The centroEstudios to set.
	 */
	public void setCentroEstudios(String centroEstudios) {
		this.centroEstudios = centroEstudios;
	}

	/**
	 * @return Returns the centroTrabajo.
	 */
	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	/**
	 * @param centroTrabajo The centroTrabajo to set.
	 */
	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	/**
	 * @return Returns the codigoEmpleado.
	 */
	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	/**
	 * @param codigoEmpleado The codigoEmpleado to set.
	 */
	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	/**
	 * @return Returns the edad.
	 */
	public Integer getEdad() {
		return edad;
	}

	/**
	 * @param edad The edad to set.
	 */
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the fechaNacimiento.
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento The fechaNacimiento to set.
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return Returns the indicadorActivo.
	 */
	public Integer getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo The indicadorActivo to set.
	 */
	public void setIndicadorActivo(Integer indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return Returns the indicadorCorrespondencia.
	 */
	public Integer getIndicadorCorrespondencia() {
		return indicadorCorrespondencia;
	}

	/**
	 * @param indicadorCorrespondencia The indicadorCorrespondencia to set.
	 */
	public void setIndicadorCorrespondencia(Integer indicadorCorrespondencia) {
		this.indicadorCorrespondencia = indicadorCorrespondencia;
	}

	/**
	 * @return Returns the ingresoFamiliar.
	 */
	public Double getIngresoFamiliar() {
		return ingresoFamiliar;
	}

	/**
	 * @param ingresoFamiliar The ingresoFamiliar to set.
	 */
	public void setIngresoFamiliar(Double ingresoFamiliar) {
		this.ingresoFamiliar = ingresoFamiliar;
	}

	/**
	 * @return Returns the montoLineaCredito.
	 */
	public Double getMontoLineaCredito() {
		return montoLineaCredito;
	}

	/**
	 * @param montoLineaCredito The montoLineaCredito to set.
	 */
	public void setMontoLineaCredito(Double montoLineaCredito) {
		this.montoLineaCredito = montoLineaCredito;
	}

	/**
	 * @return Returns the nivelSocioEconomico3.
	 */
	public String getNivelSocioEconomico3() {
		return nivelSocioEconomico3;
	}

	/**
	 * @param nivelSocioEconomico3 The nivelSocioEconomico3 to set.
	 */
	public void setNivelSocioEconomico3(String nivelSocioEconomico3) {
		this.nivelSocioEconomico3 = nivelSocioEconomico3;
	}

	/**
	 * @return Returns the numeroCampanasSinPedido.
	 */
	public Integer getNumeroCampanasSinPedido() {
		return numeroCampanasSinPedido;
	}

	/**
	 * @param numeroCampanasSinPedido The numeroCampanasSinPedido to set.
	 */
	public void setNumeroCampanasSinPedido(Integer numeroCampanasSinPedido) {
		this.numeroCampanasSinPedido = numeroCampanasSinPedido;
	}

	/**
	 * @return Returns the numeroDependientes.
	 */
	public Integer getNumeroDependientes() {
		return numeroDependientes;
	}

	/**
	 * @param numeroDependientes The numeroDependientes to set.
	 */
	public void setNumeroDependientes(Integer numeroDependientes) {
		this.numeroDependientes = numeroDependientes;
	}

	/**
	 * @return Returns the numeroHijos.
	 */
	public Integer getNumeroHijos() {
		return numeroHijos;
	}

	/**
	 * @param numeroHijos The numeroHijos to set.
	 */
	public void setNumeroHijos(Integer numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	/**
	 * @return Returns the ocupacion.
	 */
	public String getOcupacion() {
		return ocupacion;
	}

	/**
	 * @param ocupacion The ocupacion to set.
	 */
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
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
	 * @return Returns the oidCicloVida.
	 */
	public Long getOidCicloVida() {
		return oidCicloVida;
	}

	/**
	 * @param oidCicloVida The oidCicloVida to set.
	 */
	public void setOidCicloVida(Long oidCicloVida) {
		this.oidCicloVida = oidCicloVida;
	}

	/**
	 * @return Returns the oidCliente.
	 */
	public Long getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente The oidCliente to set.
	 */
	public void setOidCliente(Long oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return Returns the oidEstadoCivil.
	 */
	public Long getOidEstadoCivil() {
		return oidEstadoCivil;
	}

	/**
	 * @param oidEstadoCivil The oidEstadoCivil to set.
	 */
	public void setOidEstadoCivil(Long oidEstadoCivil) {
		this.oidEstadoCivil = oidEstadoCivil;
	}

	/**
	 * @return Returns the oidEstatusCliente.
	 */
	public Long getOidEstatusCliente() {
		return oidEstatusCliente;
	}

	/**
	 * @param oidEstatusCliente The oidEstatusCliente to set.
	 */
	public void setOidEstatusCliente(Long oidEstatusCliente) {
		this.oidEstatusCliente = oidEstatusCliente;
	}

	/**
	 * @return Returns the oidNacionalidad.
	 */
	public Long getOidNacionalidad() {
		return oidNacionalidad;
	}

	/**
	 * @param oidNacionalidad The oidNacionalidad to set.
	 */
	public void setOidNacionalidad(Long oidNacionalidad) {
		this.oidNacionalidad = oidNacionalidad;
	}

	/**
	 * @return Returns the oidNivelEstudios.
	 */
	public Long getOidNivelEstudios() {
		return oidNivelEstudios;
	}

	/**
	 * @param oidNivelEstudios The oidNivelEstudios to set.
	 */
	public void setOidNivelEstudios(Long oidNivelEstudios) {
		this.oidNivelEstudios = oidNivelEstudios;
	}

	/**
	 * @return Returns the oidNivelRiesgo.
	 */
	public Long getOidNivelRiesgo() {
		return oidNivelRiesgo;
	}

	/**
	 * @param oidNivelRiesgo The oidNivelRiesgo to set.
	 */
	public void setOidNivelRiesgo(Long oidNivelRiesgo) {
		this.oidNivelRiesgo = oidNivelRiesgo;
	}

	/**
	 * @return Returns the oidNivelSocioEconomico.
	 */
	public Long getOidNivelSocioEconomico() {
		return oidNivelSocioEconomico;
	}

	/**
	 * @param oidNivelSocioEconomico The oidNivelSocioEconomico to set.
	 */
	public void setOidNivelSocioEconomico(Long oidNivelSocioEconomico) {
		this.oidNivelSocioEconomico = oidNivelSocioEconomico;
	}

	/**
	 * @return Returns the oidPeriodoLineaCredito.
	 */
	public Long getOidPeriodoLineaCredito() {
		return oidPeriodoLineaCredito;
	}

	/**
	 * @param oidPeriodoLineaCredito The oidPeriodoLineaCredito to set.
	 */
	public void setOidPeriodoLineaCredito(Long oidPeriodoLineaCredito) {
		this.oidPeriodoLineaCredito = oidPeriodoLineaCredito;
	}

	/**
	 * @return Returns the oidPeriodoNivelRiesgo.
	 */
	public Long getOidPeriodoNivelRiesgo() {
		return oidPeriodoNivelRiesgo;
	}

	/**
	 * @param oidPeriodoNivelRiesgo The oidPeriodoNivelRiesgo to set.
	 */
	public void setOidPeriodoNivelRiesgo(Long oidPeriodoNivelRiesgo) {
		this.oidPeriodoNivelRiesgo = oidPeriodoNivelRiesgo;
	}

	/**
	 * @return Returns the profesion.
	 */
	public String getProfesion() {
		return profesion;
	}

	/**
	 * @param profesion The profesion to set.
	 */
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	/**
	 * @return the oidTipoCutis
	 */
	public Long getOidTipoCutis() {
		return oidTipoCutis;
	}

	/**
	 * @param oidTipoCutis the oidTipoCutis to set
	 */
	public void setOidTipoCutis(Long oidTipoCutis) {
		this.oidTipoCutis = oidTipoCutis;
	}

	/**
	 * @return the oidOtrasMarcas
	 */
	public Long getOidOtrasMarcas() {
		return oidOtrasMarcas;
	}

	/**
	 * @param oidOtrasMarcas the oidOtrasMarcas to set
	 */
	public void setOidOtrasMarcas(Long oidOtrasMarcas) {
		this.oidOtrasMarcas = oidOtrasMarcas;
	}

	/**
	 * @return the indicadorCompromiso
	 */
	public String getIndicadorCompromiso() {
		return indicadorCompromiso;
	}

	/**
	 * @param indicadorCompromiso the indicadorCompromiso to set
	 */
	public void setIndicadorCompromiso(String indicadorCompromiso) {
		this.indicadorCompromiso = indicadorCompromiso;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the indicadorImpresionPaqDoc
	 */
	public String getIndicadorImpresionPaqDoc() {
		return indicadorImpresionPaqDoc;
	}

	/**
	 * @param indicadorImpresionPaqDoc the indicadorImpresionPaqDoc to set
	 */
	public void setIndicadorImpresionPaqDoc(String indicadorImpresionPaqDoc) {
		this.indicadorImpresionPaqDoc = indicadorImpresionPaqDoc;
	}

	/**
	 * @return the indicadorImpresionDocumentos
	 */
	public String getIndicadorImpresionDocumentos() {
		return indicadorImpresionDocumentos;
	}

	/**
	 * @param indicadorImpresionDocumentos the indicadorImpresionDocumentos to set
	 */
	public void setIndicadorImpresionDocumentos(String indicadorImpresionDocumentos) {
		this.indicadorImpresionDocumentos = indicadorImpresionDocumentos;
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
	 * @return the codGrupoFuncional
	 */
	public String getCodGrupoFuncional() {
		return codGrupoFuncional;
	}

	/**
	 * @param codGrupoFuncional the codGrupoFuncional to set
	 */
	public void setCodGrupoFuncional(String codGrupoFuncional) {
		this.codGrupoFuncional = codGrupoFuncional;
	}

	/**
	 * @return the desGrupoFuncional
	 */
	public String getDesGrupoFuncional() {
		return desGrupoFuncional;
	}

	/**
	 * @param desGrupoFuncional the desGrupoFuncional to set
	 */
	public void setDesGrupoFuncional(String desGrupoFuncional) {
		this.desGrupoFuncional = desGrupoFuncional;
	}

	/**
	 * @return the usuRed
	 */
	public String getUsuRed() {
		return usuRed;
	}

	/**
	 * @param usuRed the usuRed to set
	 */
	public void setUsuRed(String usuRed) {
		this.usuRed = usuRed;
	}

	/**
	 * @return the codJefeCUB
	 */
	public String getCodJefeCUB() {
		return codJefeCUB;
	}

	/**
	 * @param codJefeCUB the codJefeCUB to set
	 */
	public void setCodJefeCUB(String codJefeCUB) {
		this.codJefeCUB = codJefeCUB;
	}

	/**
	 * @return the valRelContr
	 */
	public String getValRelContr() {
		return valRelContr;
	}

	/**
	 * @param valRelContr the valRelContr to set
	 */
	public void setValRelContr(String valRelContr) {
		this.valRelContr = valRelContr;
	}

	/**
	 * @return the nomJefeDir
	 */
	public String getNomJefeDir() {
		return nomJefeDir;
	}

	/**
	 * @param nomJefeDir the nomJefeDir to set
	 */
	public void setNomJefeDir(String nomJefeDir) {
		this.nomJefeDir = nomJefeDir;
	}

	/**
	 * @return the valPueOrg
	 */
	public String getValPueOrg() {
		return valPueOrg;
	}

	/**
	 * @param valPueOrg the valPueOrg to set
	 */
	public void setValPueOrg(String valPueOrg) {
		this.valPueOrg = valPueOrg;
	}

	/**
	 * @return the indImprDocuAnt
	 */
	public String getIndImprDocuAnt() {
		return indImprDocuAnt;
	}

	/**
	 * @param indImprDocuAnt the indImprDocuAnt to set
	 */
	public void setIndImprDocuAnt(String indImprDocuAnt) {
		this.indImprDocuAnt = indImprDocuAnt;
	}

	/**
	 * @return the usuCargAnt
	 */
	public String getUsuCargAnt() {
		return usuCargAnt;
	}

	/**
	 * @param usuCargAnt the usuCargAnt to set
	 */
	public void setUsuCargAnt(String usuCargAnt) {
		this.usuCargAnt = usuCargAnt;
	}

	public String getCambioImprDocu() {
		String cambio = "0";
		
		if(!StringUtils.equals(this.indImprDocu, this.indImprDocuAnt)) {
			cambio = "1";
		} else {
			if((this.usuCarg == null) && !StringUtils.equals(this.usuCarg, this.usuCargAnt)) {
				cambio = "1";
			}	
		}	
			
		return cambio;
	}

	public String getIndDocFiscal() {
		return indDocFiscal;
	}

	public void setIndDocFiscal(String indDocFiscal) {
		this.indDocFiscal = indDocFiscal;
	}



	/**
	 * @return the codigoBanco
	 */
	public Long getCodigoBanco() {
		return codigoBanco;
	}



	/**
	 * @param codigoBanco the codigoBanco to set
	 */
	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return the cuentaBancaria
	 */
	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	/**
	 * @param cuentaBancaria the cuentaBancaria to set
	 */
	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}



	/**
	 * @return the indicadorCalularPercepcion
	 */
	public String getIndicadorCalularPercepcion() {
		return indicadorCalularPercepcion;
	}



	/**
	 * @param indicadorCalularPercepcion the indicadorCalularPercepcion to set
	 */
	public void setIndicadorCalularPercepcion(String indicadorCalularPercepcion) {
		this.indicadorCalularPercepcion = indicadorCalularPercepcion;
	}

	/**
	 * @return the valBanco
	 */
	public String getValBanco() {
		return valBanco;
	}

	/**
	 * @param valBanco the valBanco to set
	 */
	public void setValBanco(String valBanco) {
		this.valBanco = valBanco;
	}

	/**
	 * @return the valTipoCuenta
	 */
	public String getValTipoCuenta() {
		return valTipoCuenta;
	}

	/**
	 * @param valTipoCuenta the valTipoCuenta to set
	 */
	public void setValTipoCuenta(String valTipoCuenta) {
		this.valTipoCuenta = valTipoCuenta;
	}

	/**
	 * @return the valCuentaCorriente
	 */
	public String getValCuentaCorriente() {
		return valCuentaCorriente;
	}

	/**
	 * @param valCuentaCorriente the valCuentaCorriente to set
	 */
	public void setValCuentaCorriente(String valCuentaCorriente) {
		this.valCuentaCorriente = valCuentaCorriente;
	}
}
