package biz.belcorp.ssicc.dao.model;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * <p>
 * <a href="ProcesoFDVClusterizacion.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public class ProcesoFDVClusterizacion extends AuditableBaseObject implements Serializable{

	private static final long serialVersionUID = 4887222891637826132L;
	
	/**
     * @uml.property name="codProc" multiplicity="(0 1)"
     */
	private String codProc;
	
	/**
     * @uml.property name="nomProc" multiplicity="(0 1)"
     */
	private String nomProc;
	
	/**
     * @uml.property name="estProc" multiplicity="(0 1)"
     */
	private String estProc;
	
	private String arcZona;
	private String arcSecc;
	private String arcVari;
	private String arcNore;
	private String staProc;	
	private String flaNse;
	private String flaRlur;
	private String flaPobl;
	private String flaVar1;
	private String flaVar2;
    private String nroGrpo;
    private String camProc;
    private String anyProc;
    private String perProc;
    private String ordVacl;
    private BigDecimal valMevd;
    private String codigoPais;
    private String descPais;
    private String directorioTemporal;
    
    private String stFlaNse;
	private String stFlaRlur;
	private String stFlaPobl;
	private String stFlaVar1;
	private String stFlaVar2;
    
	
	
    private File zonaFile;
    private File seccionFile;
    private File variablesExogFile;
    private File noReconstruidaFile;
    
    private String updatedByProcess;
    private Timestamp lastUpdatedProcess;
    
    private String flaZonc;
    private String flaZofi;
    private String codZona;
    
    private String cluAsigSist;
    private String cluAsigPais;
    private String varRlur;
    private String varNse;
    private String varGrpo;
    private String varVar1; 
    private String varVar2;
    private String desGrpo;
    
    private int contador;
    private String ordenVar;
	
    private String anyoPerProc;
    
    private Integer versionProceso;
    
    private String flaClus;
    private String stFlaClus;
   
    
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		ProcesoFDVClusterizacion other = (ProcesoFDVClusterizacion) obj;
		if (anyProc == null) {
			if (other.anyProc != null)
				return false;
		} else if (!anyProc.equals(other.anyProc))
			return false;
		if (anyoPerProc == null) {
			if (other.anyoPerProc != null)
				return false;
		} else if (!anyoPerProc.equals(other.anyoPerProc))
			return false;
		if (arcNore == null) {
			if (other.arcNore != null)
				return false;
		} else if (!arcNore.equals(other.arcNore))
			return false;
		if (arcSecc == null) {
			if (other.arcSecc != null)
				return false;
		} else if (!arcSecc.equals(other.arcSecc))
			return false;
		if (arcVari == null) {
			if (other.arcVari != null)
				return false;
		} else if (!arcVari.equals(other.arcVari))
			return false;
		if (arcZona == null) {
			if (other.arcZona != null)
				return false;
		} else if (!arcZona.equals(other.arcZona))
			return false;
		if (camProc == null) {
			if (other.camProc != null)
				return false;
		} else if (!camProc.equals(other.camProc))
			return false;
		if (cluAsigPais == null) {
			if (other.cluAsigPais != null)
				return false;
		} else if (!cluAsigPais.equals(other.cluAsigPais))
			return false;
		if (cluAsigSist == null) {
			if (other.cluAsigSist != null)
				return false;
		} else if (!cluAsigSist.equals(other.cluAsigSist))
			return false;
		if (codProc == null) {
			if (other.codProc != null)
				return false;
		} else if (!codProc.equals(other.codProc))
			return false;
		if (codZona == null) {
			if (other.codZona != null)
				return false;
		} else if (!codZona.equals(other.codZona))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (contador != other.contador)
			return false;
		if (desGrpo == null) {
			if (other.desGrpo != null)
				return false;
		} else if (!desGrpo.equals(other.desGrpo))
			return false;
		if (descPais == null) {
			if (other.descPais != null)
				return false;
		} else if (!descPais.equals(other.descPais))
			return false;
		if (directorioTemporal == null) {
			if (other.directorioTemporal != null)
				return false;
		} else if (!directorioTemporal.equals(other.directorioTemporal))
			return false;
		if (estProc == null) {
			if (other.estProc != null)
				return false;
		} else if (!estProc.equals(other.estProc))
			return false;
		if (flaNse == null) {
			if (other.flaNse != null)
				return false;
		} else if (!flaNse.equals(other.flaNse))
			return false;
		if (flaPobl == null) {
			if (other.flaPobl != null)
				return false;
		} else if (!flaPobl.equals(other.flaPobl))
			return false;
		if (flaRlur == null) {
			if (other.flaRlur != null)
				return false;
		} else if (!flaRlur.equals(other.flaRlur))
			return false;
		if (flaVar1 == null) {
			if (other.flaVar1 != null)
				return false;
		} else if (!flaVar1.equals(other.flaVar1))
			return false;
		if (flaVar2 == null) {
			if (other.flaVar2 != null)
				return false;
		} else if (!flaVar2.equals(other.flaVar2))
			return false;
		if (flaZofi == null) {
			if (other.flaZofi != null)
				return false;
		} else if (!flaZofi.equals(other.flaZofi))
			return false;
		if (flaZonc == null) {
			if (other.flaZonc != null)
				return false;
		} else if (!flaZonc.equals(other.flaZonc))
			return false;
		if (lastUpdatedProcess == null) {
			if (other.lastUpdatedProcess != null)
				return false;
		} else if (!lastUpdatedProcess.equals(other.lastUpdatedProcess))
			return false;
		
		if (nomProc == null) {
			if (other.nomProc != null)
				return false;
		} else if (!nomProc.equals(other.nomProc))
			return false;
		if (nroGrpo == null) {
			if (other.nroGrpo != null)
				return false;
		} else if (!nroGrpo.equals(other.nroGrpo))
			return false;
		if (ordVacl == null) {
			if (other.ordVacl != null)
				return false;
		} else if (!ordVacl.equals(other.ordVacl))
			return false;
		if (ordenVar == null) {
			if (other.ordenVar != null)
				return false;
		} else if (!ordenVar.equals(other.ordenVar))
			return false;
		if (perProc == null) {
			if (other.perProc != null)
				return false;
		} else if (!perProc.equals(other.perProc))
			return false;
		
		if (stFlaNse == null) {
			if (other.stFlaNse != null)
				return false;
		} else if (!stFlaNse.equals(other.stFlaNse))
			return false;
		if (stFlaPobl == null) {
			if (other.stFlaPobl != null)
				return false;
		} else if (!stFlaPobl.equals(other.stFlaPobl))
			return false;
		if (stFlaRlur == null) {
			if (other.stFlaRlur != null)
				return false;
		} else if (!stFlaRlur.equals(other.stFlaRlur))
			return false;
		if (stFlaVar1 == null) {
			if (other.stFlaVar1 != null)
				return false;
		} else if (!stFlaVar1.equals(other.stFlaVar1))
			return false;
		if (stFlaVar2 == null) {
			if (other.stFlaVar2 != null)
				return false;
		} else if (!stFlaVar2.equals(other.stFlaVar2))
			return false;
		if (staProc == null) {
			if (other.staProc != null)
				return false;
		} else if (!staProc.equals(other.staProc))
			return false;
		if (updatedByProcess == null) {
			if (other.updatedByProcess != null)
				return false;
		} else if (!updatedByProcess.equals(other.updatedByProcess))
			return false;
		if (valMevd == null) {
			if (other.valMevd != null)
				return false;
		} else if (!valMevd.equals(other.valMevd))
			return false;
		if (varGrpo == null) {
			if (other.varGrpo != null)
				return false;
		} else if (!varGrpo.equals(other.varGrpo))
			return false;
		if (varNse == null) {
			if (other.varNse != null)
				return false;
		} else if (!varNse.equals(other.varNse))
			return false;
		if (varRlur == null) {
			if (other.varRlur != null)
				return false;
		} else if (!varRlur.equals(other.varRlur))
			return false;
		if (varVar1 == null) {
			if (other.varVar1 != null)
				return false;
		} else if (!varVar1.equals(other.varVar1))
			return false;
		if (varVar2 == null) {
			if (other.varVar2 != null)
				return false;
		} else if (!varVar2.equals(other.varVar2))
			return false;
		
		if (versionProceso == null) {
			if (other.versionProceso != null)
				return false;
		} else if (!versionProceso.equals(other.versionProceso))
			return false;
		
		if (stFlaClus == null) {
			if (other.stFlaClus != null)
				return false;
		} else if (!stFlaClus.equals(other.stFlaClus))
			return false;
		return true;
	}

	public String getAnyoPerProc() {
		return this.anyoPerProc;
	}

	public String getAnyProc() {
		return this.anyProc;
	}
	
	public String getArcNore() {
		return this.arcNore;
	}

	public String getArcSecc() {
		return this.arcSecc;
	}

	public String getArcVari() {
		return this.arcVari;
	}

	public String getArcZona() {
		return this.arcZona;
	}

	public String getCamProc() {
		return this.camProc;
	}

	public String getCluAsigPais() {
		return this.cluAsigPais;
	}

	public String getCluAsigSist() {
		return this.cluAsigSist;
	}

	public String getCodigoPais() {
		return this.codigoPais;
	}
	
	/**
     * @uml.property name="codProc"
     * @struts.form-field
     */
	public String getCodProc() {
		return this.codProc;
	}

	public String getCodZona() {
		return this.codZona;
	}

	public int getContador() {
		return this.contador;
	}

	public String getDescPais() {
		return this.descPais;
	}

	public String getDesGrpo() {
		return this.desGrpo;
	}

	public String getDirectorioTemporal() {
		return this.directorioTemporal;
	}

	/**
     * @uml.property name="estProc"
     * @struts.form-field
     */
	public String getEstProc() {
		return this.estProc;
	}

	public String getFlaNse() {
		return this.flaNse;
	}

	public String getFlaPobl() {
		return this.flaPobl;
	}

	public String getFlaRlur() {
		return this.flaRlur;
	}

	public String getFlaVar1() {
		return this.flaVar1;
	}

	public String getFlaVar2() {
		return this.flaVar2;
	}

	public String getFlaZofi() {
		return this.flaZofi;
	}

	public String getFlaZonc() {
		return this.flaZonc;
	}

	public Timestamp getLastUpdatedProcess() {
		return this.lastUpdatedProcess;
	}

	/**
     * @uml.property name="nomProc"
     * @struts.form-field
     * @struts.validator type="required"
     */
	public String getNomProc() {
		return this.nomProc;
	}

	

	public String getNroGrpo() {
		return this.nroGrpo;
	}

	public String getOrdenVar() {
		return this.ordenVar;
	}

	public String getOrdVacl() {
		return this.ordVacl;
	}

	public String getPerProc() {
		return this.perProc;
	}

	

	public String getStaProc() {
		return this.staProc;
	}

	public String getStFlaNse() {
		return this.stFlaNse;
	}

	public String getStFlaPobl() {
		return this.stFlaPobl;
	}

	public String getStFlaRlur() {
		return this.stFlaRlur;
	}

	public String getStFlaVar1() {
		return this.stFlaVar1;
	}

	public String getStFlaVar2() {
		return this.stFlaVar2;
	}
	
	public String getUpdatedByProcess() {
		return this.updatedByProcess;
	}

	public BigDecimal getValMevd() {
		return this.valMevd;
	}

	public String getVarGrpo() {
		return this.varGrpo;
	}

	

	public String getVarNse() {
		return this.varNse;
	}

	public String getVarRlur() {
		return this.varRlur;
	}

	public String getVarVar1() {
		return this.varVar1;
	}

	public String getVarVar2() {
		return this.varVar2;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anyProc == null) ? 0 : anyProc.hashCode());
		result = prime * result
				+ ((anyoPerProc == null) ? 0 : anyoPerProc.hashCode());
		result = prime * result + ((arcNore == null) ? 0 : arcNore.hashCode());
		result = prime * result + ((arcSecc == null) ? 0 : arcSecc.hashCode());
		result = prime * result + ((arcVari == null) ? 0 : arcVari.hashCode());
		result = prime * result + ((arcZona == null) ? 0 : arcZona.hashCode());
		result = prime * result + ((camProc == null) ? 0 : camProc.hashCode());
		result = prime * result
				+ ((cluAsigPais == null) ? 0 : cluAsigPais.hashCode());
		result = prime * result
				+ ((cluAsigSist == null) ? 0 : cluAsigSist.hashCode());
		result = prime * result + ((codProc == null) ? 0 : codProc.hashCode());
		result = prime * result + ((codZona == null) ? 0 : codZona.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result + contador;
		result = prime * result + ((desGrpo == null) ? 0 : desGrpo.hashCode());
		result = prime * result
				+ ((descPais == null) ? 0 : descPais.hashCode());
		result = prime
				* result
				+ ((directorioTemporal == null) ? 0 : directorioTemporal
						.hashCode());
		result = prime * result + ((estProc == null) ? 0 : estProc.hashCode());
		result = prime * result + ((flaNse == null) ? 0 : flaNse.hashCode());
		result = prime * result + ((flaPobl == null) ? 0 : flaPobl.hashCode());
		result = prime * result + ((flaRlur == null) ? 0 : flaRlur.hashCode());
		result = prime * result + ((flaVar1 == null) ? 0 : flaVar1.hashCode());
		result = prime * result + ((flaVar2 == null) ? 0 : flaVar2.hashCode());
		result = prime * result + ((flaZofi == null) ? 0 : flaZofi.hashCode());
		result = prime * result + ((flaZonc == null) ? 0 : flaZonc.hashCode());
		result = prime
				* result
				+ ((lastUpdatedProcess == null) ? 0 : lastUpdatedProcess
						.hashCode());
		
		result = prime * result + ((nomProc == null) ? 0 : nomProc.hashCode());
		result = prime * result + ((nroGrpo == null) ? 0 : nroGrpo.hashCode());
		result = prime * result + ((ordVacl == null) ? 0 : ordVacl.hashCode());
		result = prime * result
				+ ((ordenVar == null) ? 0 : ordenVar.hashCode());
		result = prime * result + ((perProc == null) ? 0 : perProc.hashCode());
		
		result = prime * result
				+ ((stFlaNse == null) ? 0 : stFlaNse.hashCode());
		result = prime * result
				+ ((stFlaPobl == null) ? 0 : stFlaPobl.hashCode());
		result = prime * result
				+ ((stFlaRlur == null) ? 0 : stFlaRlur.hashCode());
		result = prime * result
				+ ((stFlaVar1 == null) ? 0 : stFlaVar1.hashCode());
		result = prime * result
				+ ((stFlaVar2 == null) ? 0 : stFlaVar2.hashCode());
		result = prime * result + ((staProc == null) ? 0 : staProc.hashCode());
		result = prime
				* result
				+ ((updatedByProcess == null) ? 0 : updatedByProcess.hashCode());
		result = prime * result + ((valMevd == null) ? 0 : valMevd.hashCode());
		result = prime * result + ((varGrpo == null) ? 0 : varGrpo.hashCode());
		result = prime * result + ((varNse == null) ? 0 : varNse.hashCode());
		result = prime * result + ((varRlur == null) ? 0 : varRlur.hashCode());
		result = prime * result + ((varVar1 == null) ? 0 : varVar1.hashCode());
		result = prime * result + ((varVar2 == null) ? 0 : varVar2.hashCode());
		
		result = prime * result
				+ ((versionProceso == null) ? 0 : versionProceso.hashCode());
		
		return result;
	}

	public void setAnyoPerProc(String anyoPerProc) {
		this.anyoPerProc = anyoPerProc;
	}

	public void setAnyProc(String anyProc) {
		this.anyProc = anyProc;
	}

	public void setArcNore(String arcNore) {
		this.arcNore = arcNore;
	}
	
	public void setArcSecc(String arcSecc) {
		this.arcSecc = arcSecc;
	}

	public void setArcVari(String arcVari) {
		this.arcVari = arcVari;
	}

	public void setArcZona(String arcZona) {
		this.arcZona = arcZona;
	}

	public void setCamProc(String camProc) {
		this.camProc = camProc;
	}

	public void setCluAsigPais(String cluAsigPais) {
		this.cluAsigPais = cluAsigPais;
	}

	public void setCluAsigSist(String cluAsigSist) {
		this.cluAsigSist = cluAsigSist;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
     * @uml.property name="codProc"
     */
	public void setCodProc(String codProc) {
		this.codProc = codProc;
	}

	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}

	public void setDesGrpo(String desGrpo) {
		this.desGrpo = desGrpo;
	}

	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	/**
     * @uml.property name="estProc"
     */
	public void setEstProc(String estProc) {
		this.estProc = estProc;
	}

	public void setFlaNse(String flaNse) {
		
		if("S".equalsIgnoreCase(flaNse)){
			this.stFlaNse = "true";
		}else if("N".equalsIgnoreCase(flaNse)){
			this.stFlaNse = "false";
		}		
		
		this.flaNse = flaNse;
	}

	public void setFlaPobl(String flaPobl) {
		
		if("S".equalsIgnoreCase(flaPobl)){
			this.stFlaPobl = "true";
		}else if("N".equalsIgnoreCase(flaPobl)){
			this.stFlaPobl = "false";
		}
		
		this.flaPobl = flaPobl;
	}

	public void setFlaRlur(String flaRlur) {
		
		if("S".equalsIgnoreCase(flaRlur)){
			this.stFlaRlur = "true";
		}else if("N".equalsIgnoreCase(flaRlur)){
			this.stFlaRlur = "false";
		}
		
		this.flaRlur = flaRlur;
	}

	public void setFlaVar1(String flaVar1) {
		
		if("S".equalsIgnoreCase(flaVar1)){
			this.stFlaVar1 = "true";
		}else if("N".equalsIgnoreCase(flaVar1)){
			this.stFlaVar1 = "false";
		}
		
		this.flaVar1 = flaVar1;
	}

	public void setFlaVar2(String flaVar2) {
		
		if("S".equalsIgnoreCase(flaVar2)){
			this.stFlaVar2 = "true";
		}else if("N".equalsIgnoreCase(flaVar2)){
			this.stFlaVar2 = "false";
		}
		
		this.flaVar2 = flaVar2;
	}

	public void setFlaZofi(String flaZofi) {
		this.flaZofi = flaZofi;
	}

	public void setFlaZonc(String flaZonc) {
		this.flaZonc = flaZonc;
	}

	public void setLastUpdatedProcess(Timestamp lastUpdatedProcess) {
		this.lastUpdatedProcess = lastUpdatedProcess;
	}

	/**
     * @uml.property name="nomProc"
     */
	public void setNomProc(String nomProc) {
		this.nomProc = nomProc;
	}

	

	public void setNroGrpo(String nroGrpo) {
		this.nroGrpo = nroGrpo;
	}

	public void setOrdenVar(String ordenVar) {
		this.ordenVar = ordenVar;
	}

	public void setOrdVacl(String ordVacl) {
		this.ordVacl = ordVacl;
	}

	public void setPerProc(String perProc) {
		this.perProc = perProc;
	}

	

	public void setStaProc(String staProc) {
		this.staProc = staProc;
	}

	public void setStFlaNse(String stFlaNse) {
		
		if("true".equalsIgnoreCase(stFlaNse)){
			this.flaNse = "S";
		}else{
			this.flaNse = "N";
		}
		
		this.stFlaNse = stFlaNse;
	}

	public void setStFlaPobl(String stFlaPobl) {
		
		if("true".equalsIgnoreCase(stFlaPobl)){
			this.flaPobl = "S";
		}else{
			this.flaPobl = "N";
		}
		
		this.stFlaPobl = stFlaPobl;
	}

	public void setStFlaRlur(String stFlaRlur) {
		
		if("true".equalsIgnoreCase(stFlaRlur)){
			this.flaRlur = "S";
		}else{
			this.flaRlur = "N";
		}
		
		this.stFlaRlur = stFlaRlur;
	}

	public void setStFlaVar1(String stFlaVar1) {
		
		if("true".equalsIgnoreCase(stFlaVar1)){
			this.flaVar1 = "S";
		}else{
			this.flaVar1 = "N";
		}
		
		this.stFlaVar1 = stFlaVar1;
	}
	
	public void setStFlaVar2(String stFlaVar2) {
		
		if("true".equalsIgnoreCase(stFlaVar2)){
			this.flaVar2 = "S";
		}else{
			this.flaVar2 = "N";
		}
		
		this.stFlaVar2 = stFlaVar2;
	}

	public void setUpdatedByProcess(String updatedByProcess) {
		this.updatedByProcess = updatedByProcess;
	}

	public void setValMevd(BigDecimal valMevd) {
		this.valMevd = valMevd;
	}

	public void setVarGrpo(String varGrpo) {
		this.varGrpo = varGrpo;
	}

	

	public void setVarNse(String varNse) {
		this.varNse = varNse;
	}

	public void setVarRlur(String varRlur) {
		this.varRlur = varRlur;
	}
	
    public void setVarVar1(String varVar1) {
		this.varVar1 = varVar1;
	}

	public void setVarVar2(String varVar2) {
		this.varVar2 = varVar2;
	}

	
    
	/**
	 * @return the versionProceso
	 */
	public Integer getVersionProceso() {
		return versionProceso;
	}

	/**
	 * @param versionProceso the versionProceso to set
	 */
	public void setVersionProceso(Integer versionProceso) {
		this.versionProceso = versionProceso;
	}

	
	/**
	 * @return the flaClus
	 */
	public String getFlaClus() {
		return flaClus;
	}

	/**
	 * @param flaClus the flaClus to set
	 */
	public void setFlaClus(String flaClus) {
		if("S".equalsIgnoreCase(flaClus)){
			this.stFlaClus = "true";
		}else if("N".equalsIgnoreCase(flaClus)){
			this.stFlaClus = "false";
		}
		this.flaClus = flaClus;
	}

	/**
	 * @return the stFlaClus
	 */
	public String getStFlaClus() {
		return stFlaClus;
	}

	/**
	 * @param stFlaClus the stFlaClus to set
	 */
	public void setStFlaClus(String stFlaClus) {
		if("true".equalsIgnoreCase(stFlaClus)){
			this.flaClus = "S";
		}else{
			this.flaClus = "N";
		}
		this.stFlaClus = stFlaClus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProcesoFDVClusterizacion [anyProc=" + anyProc
				+ ", anyoPerProc=" + anyoPerProc + ", arcNore=" + arcNore
				+ ", arcSecc=" + arcSecc + ", arcVari=" + arcVari
				+ ", arcZona=" + arcZona + ", camProc=" + camProc
				+ ", cluAsigPais=" + cluAsigPais + ", cluAsigSist="
				+ cluAsigSist + ", codProc=" + codProc + ", codZona=" + codZona
				+ ", codigoPais=" + codigoPais + ", contador=" + contador
				+ ", desGrpo=" + desGrpo + ", descPais=" + descPais
				+ ", directorioTemporal=" + directorioTemporal + ", estProc="
				+ estProc + ", flaNse=" + flaNse + ", flaPobl=" + flaPobl
				+ ", flaRlur=" + flaRlur + ", flaVar1=" + flaVar1
				+ ", flaVar2=" + flaVar2 + ", flaZofi=" + flaZofi
				+ ", flaZonc=" + flaZonc + ", lastUpdatedProcess="
				+ lastUpdatedProcess +  ", nomProc=" + nomProc + ", nroGrpo="
				+ nroGrpo + ", ordVacl=" + ordVacl + ", ordenVar=" + ordenVar
				+ ", perProc=" + perProc 
				+ ", stFlaNse=" + stFlaNse + ", stFlaPobl=" + stFlaPobl
				+ ", stFlaRlur=" + stFlaRlur + ", stFlaVar1=" + stFlaVar1
				+ ", stFlaVar2=" + stFlaVar2 + ", staProc=" + staProc
				+ ", updatedByProcess=" + updatedByProcess + ", valMevd="
				+ valMevd + ", varGrpo=" + varGrpo + ", varNse=" + varNse
				+ ", varRlur=" + varRlur + ", varVar1=" + varVar1
				+ ", varVar2=" + varVar2 +  ", versionProceso=" + versionProceso
				 + ", stFlaClus="
				+ stFlaClus + "]";
	}

	/**
	 * @return the zonaFile
	 */
	public File getZonaFile() {
		return zonaFile;
	}

	/**
	 * @param zonaFile the zonaFile to set
	 */
	public void setZonaFile(File zonaFile) {
		this.zonaFile = zonaFile;
	}

	/**
	 * @return the seccionFile
	 */
	public File getSeccionFile() {
		return seccionFile;
	}

	/**
	 * @param seccionFile the seccionFile to set
	 */
	public void setSeccionFile(File seccionFile) {
		this.seccionFile = seccionFile;
	}

	/**
	 * @return the variablesExogFile
	 */
	public File getVariablesExogFile() {
		return variablesExogFile;
	}

	/**
	 * @param variablesExogFile the variablesExogFile to set
	 */
	public void setVariablesExogFile(File variablesExogFile) {
		this.variablesExogFile = variablesExogFile;
	}

	/**
	 * @return the noReconstruidaFile
	 */
	public File getNoReconstruidaFile() {
		return noReconstruidaFile;
	}

	/**
	 * @param noReconstruidaFile the noReconstruidaFile to set
	 */
	public void setNoReconstruidaFile(File noReconstruidaFile) {
		this.noReconstruidaFile = noReconstruidaFile;
	}	
	
	
	
}
