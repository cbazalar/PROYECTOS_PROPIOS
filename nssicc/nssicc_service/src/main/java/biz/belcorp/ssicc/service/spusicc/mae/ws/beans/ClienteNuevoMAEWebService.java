package biz.belcorp.ssicc.service.spusicc.mae.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ClienteNuevoMAEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 * 
 */
public class ClienteNuevoMAEWebService implements Serializable {

 	private static final long serialVersionUID = 1L;

 	private String codConsultora;
 	private String tipoCliente;
 	private String subCliente;
 	private String codZona;
 	private String codTerri;
 	private String fecIng;
 	private String paqDoc;
 	private String apePat;
 	private String apeMat;
 	private String priNom;
 	private String segNom;
 	private String nacCliente;
 	private String tipoDoc;
 	private String nroDoc;
 	private String fecNac;
 	private String sexo;
 	private String estCivil;
 	private String gradIns;
 	private String nomAbrev;
 	private String CUB;
 	private String codPais;
 	private String indActiva;
 	private String indOrigen;
 	private String codGrupoFuncional;
 	private String desGrupoFuncional;
 	private String usuRed;
 	private String codJefeCUB;
 	private String valRelContr;
 	private String mailBelcorp;
 	private String nomJefeDir;
 	private String valPueOrg;
 	private String codEmpleado;
 	
 	private DireccionMAEWebService[] lsDireccion;

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return the subCliente
	 */
	public String getSubCliente() {
		return subCliente;
	}

	/**
	 * @param subCliente the subCliente to set
	 */
	public void setSubCliente(String subCliente) {
		this.subCliente = subCliente;
	}

	/**
	 * @return the codZona
	 */
	public String getCodZona() {
		return codZona;
	}

	/**
	 * @param codZona the codZona to set
	 */
	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}

	/**
	 * @return the codTerri
	 */
	public String getCodTerri() {
		return codTerri;
	}

	/**
	 * @param codTerri the codTerri to set
	 */
	public void setCodTerri(String codTerri) {
		this.codTerri = codTerri;
	}

	/**
	 * @return the fecIng
	 */
	public String getFecIng() {
		return fecIng;
	}

	/**
	 * @param fecIng the fecIng to set
	 */
	public void setFecIng(String fecIng) {
		this.fecIng = fecIng;
	}

	/**
	 * @return the paqDoc
	 */
	public String getPaqDoc() {
		return paqDoc;
	}

	/**
	 * @param paqDoc the paqDoc to set
	 */
	public void setPaqDoc(String paqDoc) {
		this.paqDoc = paqDoc;
	}

	/**
	 * @return the apePat
	 */
	public String getApePat() {
		return apePat;
	}

	/**
	 * @param apePat the apePat to set
	 */
	public void setApePat(String apePat) {
		this.apePat = apePat;
	}

	/**
	 * @return the apeMat
	 */
	public String getApeMat() {
		return apeMat;
	}

	/**
	 * @param apeMat the apeMat to set
	 */
	public void setApeMat(String apeMat) {
		this.apeMat = apeMat;
	}

	/**
	 * @return the priNom
	 */
	public String getPriNom() {
		return priNom;
	}

	/**
	 * @param priNom the priNom to set
	 */
	public void setPriNom(String priNom) {
		this.priNom = priNom;
	}

	/**
	 * @return the segNom
	 */
	public String getSegNom() {
		return segNom;
	}

	/**
	 * @param segNom the segNom to set
	 */
	public void setSegNom(String segNom) {
		this.segNom = segNom;
	}

	/**
	 * @return the nacCliente
	 */
	public String getNacCliente() {
		return nacCliente;
	}

	/**
	 * @param nacCliente the nacCliente to set
	 */
	public void setNacCliente(String nacCliente) {
		this.nacCliente = nacCliente;
	}

	/**
	 * @return the tipoDoc
	 */
	public String getTipoDoc() {
		return tipoDoc;
	}

	/**
	 * @param tipoDoc the tipoDoc to set
	 */
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	/**
	 * @return the nroDoc
	 */
	public String getNroDoc() {
		return nroDoc;
	}

	/**
	 * @param nroDoc the nroDoc to set
	 */
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	/**
	 * @return the fecNac
	 */
	public String getFecNac() {
		return fecNac;
	}

	/**
	 * @param fecNac the fecNac to set
	 */
	public void setFecNac(String fecNac) {
		this.fecNac = fecNac;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the estCivil
	 */
	public String getEstCivil() {
		return estCivil;
	}

	/**
	 * @param estCivil the estCivil to set
	 */
	public void setEstCivil(String estCivil) {
		this.estCivil = estCivil;
	}

	/**
	 * @return the gradIns
	 */
	public String getGradIns() {
		return gradIns;
	}

	/**
	 * @param gradIns the gradIns to set
	 */
	public void setGradIns(String gradIns) {
		this.gradIns = gradIns;
	}

	/**
	 * @return the nomAbrev
	 */
	public String getNomAbrev() {
		return nomAbrev;
	}

	/**
	 * @param nomAbrev the nomAbrev to set
	 */
	public void setNomAbrev(String nomAbrev) {
		this.nomAbrev = nomAbrev;
	}

	/**
	 * @return the cUB
	 */
	public String getCUB() {
		return CUB;
	}

	/**
	 * @param cUB the cUB to set
	 */
	public void setCUB(String cUB) {
		CUB = cUB;
	}

	/**
	 * @return the codPais
	 */
	public String getCodPais() {
		return codPais;
	}

	/**
	 * @param codPais the codPais to set
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	/**
	 * @return the indActiva
	 */
	public String getIndActiva() {
		return indActiva;
	}

	/**
	 * @param indActiva the indActiva to set
	 */
	public void setIndActiva(String indActiva) {
		this.indActiva = indActiva;
	}

	/**
	 * @return the lsDireccion
	 */
	public DireccionMAEWebService[] getLsDireccion() {
		return lsDireccion;
	}

	/**
	 * @param lsDireccion the lsDireccion to set
	 */
	public void setLsDireccion(DireccionMAEWebService[] lsDireccion) {
		this.lsDireccion = lsDireccion;
	}

	/**
	 * @return the indOrigen
	 */
	public String getIndOrigen() {
		return indOrigen;
	}

	/**
	 * @param indOrigen the indOrigen to set
	 */
	public void setIndOrigen(String indOrigen) {
		this.indOrigen = indOrigen;
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
	 * @return the mailBelcorp
	 */
	public String getMailBelcorp() {
		return mailBelcorp;
	}

	/**
	 * @param mailBelcorp the mailBelcorp to set
	 */
	public void setMailBelcorp(String mailBelcorp) {
		this.mailBelcorp = mailBelcorp;
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
	 * @return the codConsultora
	 */
	public String getCodConsultora() {
		return codConsultora;
	}

	/**
	 * @param codConsultora the codConsultora to set
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}

	/**
	 * @return the codEmpleado
	 */
	public String getCodEmpleado() {
		return codEmpleado;
	}

	/**
	 * @param codEmpleado the codEmpleado to set
	 */
	public void setCodEmpleado(String codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

}
