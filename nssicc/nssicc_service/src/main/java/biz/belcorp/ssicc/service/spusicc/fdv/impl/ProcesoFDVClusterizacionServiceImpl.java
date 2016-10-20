package biz.belcorp.ssicc.service.spusicc.fdv.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVClusterizacionDAO;
import biz.belcorp.ssicc.dao.spusicc.fdv.model.GenericBean;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVClusterizacionService;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidConsolidatedProcessException;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidTransactionProcessException;

/**
 * <p>
 * <a href="ProcesoFDVClusterizacionServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

@Service("spusicc.procesoFDVClusterizacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoFDVClusterizacionServiceImpl extends BaseService implements ProcesoFDVClusterizacionService{
					  
	@Resource(name = "spusicc.procesoFDVClusterizacionDAO")
    private ProcesoFDVClusterizacionDAO procesoFDVClusterizacionDAO;

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#executeProcedureClusterizacion(
     * biz.belcorp.ssicc.model.ProcesoFDVClusterizacion)
   */
    public void executeProcedureClusterizacion(
          ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception {
          
          String mensajeError = "";
          
          try{
                
                mensajeError = this.procesoFDVClusterizacionDAO.executeProcedureClusterizacion(
                procesoFDVClusterizacion);
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new Exception();
                }
                
          }catch(Exception e){
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, mensajeError, "actualizar");
                }else{
                      // Se lanzara una excepcion si existe algun fallo no controlado dentro del procedure
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, "", "actualizar");
                }           
          }
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#executeProcedureDistMetas()
   */
    public void executeProcedureDistMetas(String codProc, String codPais)
                throws Exception {
          
          String mensajeError = "";
          
          try{
                
                mensajeError = this.procesoFDVClusterizacionDAO.executeProcedureDistMetas(
                codProc, codPais);
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new Exception();
                }
                
          }catch(Exception e){
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, mensajeError, "procesar");
                }else{
                      // Se lanzara una excepcion si existe algun fallo no controlado dentro del procedure
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, "", "procesar");
                }           
          }
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#executeProcedureIniDistMetasSecciones()
   */
    public void executeProcedureIniDistMetasSecciones(String codProc)
                throws Exception {
          
          String mensajeError = "";
          
          try{
                
                mensajeError = this.procesoFDVClusterizacionDAO.executeProcedureIniDistMetasSecciones(codProc);
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new Exception();
                }
                
          }catch(Exception e){
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, mensajeError, "inicializar");
                }else{
                      // Se lanzara una excepcion si existe algun fallo no controlado dentro del procedure
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, "", "inicializar");
                }           
          }
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getCampaniaCambioList()
   */
    public List getCampaniaCambioList(
          ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception {

          List listCampa = new ArrayList();
          String camProc = procesoFDVClusterizacion.getCamProc();
          String anyProc = procesoFDVClusterizacion.getAnyProc();
          
          if(StringUtils.isNotBlank(camProc) && StringUtils.isNotBlank(anyProc)){
                
                GenericBean genericBean = null;
                Long numCamp = new Long(camProc.trim());
                Long numAnyo = new Long(anyProc.trim());
                
                if(numCamp.intValue() <= 6){
                      
                      for (int i = 7; i <= 12; i++) {                            
                           if(i < 10){
                                 genericBean = new GenericBean();
                                 genericBean.setDescripcion(numAnyo+"0"+i);
                                 listCampa.add(genericBean);
                           }else{
                                 genericBean = new GenericBean();
                                 genericBean.setDescripcion(""+numAnyo+i);
                                 listCampa.add(genericBean);
                           }                       
                      }
                      
                }else if(numCamp.intValue() <= 12){
                      
                      for (int i = 13; i <= 18; i++) {                           
                           genericBean = new GenericBean();
                           genericBean.setDescripcion(""+numAnyo+i);
                           listCampa.add(genericBean);
                      }
                      
                }else if(numCamp.intValue() <= 18){
                      
                      numAnyo = numAnyo + 1;
                      for (int i = 1; i <= 6; i++) {                             
                           if(i < 10){
                                 genericBean = new GenericBean();
                                 genericBean.setDescripcion(numAnyo+"0"+i);
                                 listCampa.add(genericBean);
                           }else{
                                 genericBean = new GenericBean();
                                 genericBean.setDescripcion(""+numAnyo+i);
                                 listCampa.add(genericBean);
                           }                       
                      }
                }
          }
          
          return listCampa;
    }
    
    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getCluAsigSistList()
   */
    public List getCluAsigSistList(
                ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception{
          return this.procesoFDVClusterizacionDAO.getCluAsigSistList(procesoFDVClusterizacion);
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getParamDistribucionList()
   */
    public List getParamDistribucionList(String codProc) throws Exception {
          return this.procesoFDVClusterizacionDAO.getParamDistribucionList(codProc);
    }
    
    /**
   * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getProcesoCluster(java.lang.String)
   */
    public ProcesoFDVClusterizacion getProcesoCluster(final String codigo) {
          return this.procesoFDVClusterizacionDAO.getProcesoCluster(codigo);
    }

    /**
     * @return Returns the procesoFDVClusterizacionDAO.
     */
    public ProcesoFDVClusterizacionDAO getProcesoFDVClusterizacionDAO() {
          return this.procesoFDVClusterizacionDAO;
    }

    /**
   * 
   * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getProcesosClusterByCriteria(java.util.Map)
   */
    public List getProcesosClusterByCriteria(Map criteria) {
          return this.procesoFDVClusterizacionDAO.getProcesosClusterByCriteria(criteria);
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZonaSeccionList()
   */
    public List getZonaSeccionList(String codProc) throws Exception {
          return this.procesoFDVClusterizacionDAO.getZonaSeccionList(codProc);
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZoneNoReliableDataList(java.lang.String)
   */
    public List getZoneNoReliableDataList(String codProc) {
          return this.procesoFDVClusterizacionDAO.getZoneNoReliableDataList(codProc);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZoneNoReliableDataVarVentaList(java.lang.String)
     */
    public List getZoneNoReliableDataVarVentaList(String codProc) {
          return this.procesoFDVClusterizacionDAO.getZoneNoReliableDataVarVentaList(codProc);
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZoneOfficeList(java.lang.String)
   */
    public List getZoneOfficeList(String codProc) {
          return this.procesoFDVClusterizacionDAO.getZoneOfficeList(codProc);
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZonesAsigPaisList()
   */
    public List getZonesAsigPaisList(
                ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception{
          return this.procesoFDVClusterizacionDAO.getZonesAsigPaisList(procesoFDVClusterizacion);
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZonesAsigSistList()
   */
    public List getZonesAsigSistList(
                ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception{
          
          List lisFinal = new ArrayList();
          
          GenericBean genericBean = null;
          ProcesoFDVClusterizacion bean = null;

          String ordVacl = procesoFDVClusterizacion.getOrdVacl();
          String[] arrayVacl = ordVacl.trim().split(",");
          String ordenVar = "";
          
          for (int i = 0; i < arrayVacl.length; i++) {
                if("VAR_GRPO".equalsIgnoreCase(arrayVacl[i].trim())){
                     ordenVar = ordenVar + "VAR_GRPO desc,";
                }else if("VAR_NSE".equalsIgnoreCase(arrayVacl[i].trim())){
                     ordenVar = ordenVar + "VAR_NSE asc,";
                }else if("VAR_RLUR".equalsIgnoreCase(arrayVacl[i].trim())){
                     ordenVar = ordenVar + "VAR_RLUR desc,";
                }else if("VAR_VAR1".equalsIgnoreCase(arrayVacl[i].trim())){
                     ordenVar = ordenVar + "VAR_VAR1 asc,";
                }else if("VAR_VAR2".equalsIgnoreCase(arrayVacl[i].trim())){
                     ordenVar = ordenVar + "VAR_VAR2 asc,";
                }
          }
          
          ordenVar = ordenVar + "TO_NUMBER(COD_ZONA)";
          
          procesoFDVClusterizacion.setOrdenVar(ordenVar);
          
          
          List list = this.procesoFDVClusterizacionDAO.getZonesAsigSistList(procesoFDVClusterizacion);
          
          for (int i = 0; i < list.size(); i++) {
                
                bean = (ProcesoFDVClusterizacion)list.get(i);
                genericBean = new GenericBean();
                
                for (int z = 0; z < arrayVacl.length; z++) {
                     if("VAR_GRPO".equalsIgnoreCase(arrayVacl[z].trim())){
                           
                           if(i == 0) {
                                 genericBean.setDeCabecera("mantenimientoFDVClusterizacionForm.tamañoPoblacion", z);
                           }
                                 genericBean.setDeVariable(bean.getDesGrpo(), z);
                           
                     }else if("VAR_NSE".equalsIgnoreCase(arrayVacl[z].trim())){
                           
                           if(i == 0) {
                                 genericBean.setDeCabecera("mantenimientoFDVClusterizacionForm.tipoNSE", z);
                           }
                                 genericBean.setDeVariable(bean.getVarNse(), z);
                                 
                     }else if("VAR_RLUR".equalsIgnoreCase(arrayVacl[z].trim())){
                           
                           if(i == 0) {
                                 genericBean.setDeCabecera("mantenimientoFDVClusterizacionForm.tipoRuralUrbano", z);
                           }
                                 genericBean.setDeVariable(bean.getVarRlur(), z);
                                 
                     }else if("VAR_VAR1".equalsIgnoreCase(arrayVacl[z].trim())){
                           
                           if(i == 0) {
                                 genericBean.setDeCabecera("mantenimientoFDVClusterizacionForm.variable1", z);
                           }
                                 genericBean.setDeVariable(bean.getVarVar1(), z);
                                 
                     }else if("VAR_VAR2".equalsIgnoreCase(arrayVacl[z].trim())){
                           
                           if(i == 0) {
                                 genericBean.setDeCabecera("mantenimientoFDVClusterizacionForm.variable2", z);
                           }
                                 genericBean.setDeVariable(bean.getVarVar2(), z);
                     }
                }
                
                genericBean.setCodZona(bean.getCodZona());
                genericBean.setCluAsigSist(bean.getCluAsigSist());
                genericBean.setCluAsigPais(bean.getCluAsigPais());
                lisFinal.add(genericBean);
          }
          
          return lisFinal;
          
    }

    /**
   * 
   * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#obtenerPathUpload(java.lang.String)
   */
    public String obtenerPathUpload(String codigoPais) {
          return this.procesoFDVClusterizacionDAO.obtenerPathUpload(codigoPais);
    }

    /**
   * 
   * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#removeProcesoCluster(java.lang.String,
   *      biz.belcorp.ssicc.model.Usuario)
   */
    public void removeProcesoCluster(final String codigo, Usuario usuario) {
                      
      try {
          // Actualizamos el estado del Proceso Cluster
          this.procesoFDVClusterizacionDAO.updateInactiveProcesoCluster(codigo, usuario);
      }
      catch (ObjectRetrievalFailureException orfe) {
          this.log.warn(orfe.getMessage());
      }         
    }

    /**
   * Setea el DAO para la comunicación con la capa de persistencia.
   * 
   * @param procesoFDVClusterizacionDAO
   */
    public void setProcesoFDVClusterizacionDAO(
                ProcesoFDVClusterizacionDAO procesoFDVClusterizacionDAO) {
          this.procesoFDVClusterizacionDAO = procesoFDVClusterizacionDAO;
    }
    
    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateClusterAsigPais()
   */
    public void updateClusterAsigPais(String[] arrayCountryAsigSist,
                String[] arrayCountryAsigPais, String[] arrayZoneAsigPais,
                String[] arrayZoneAsigSist, String codProc) throws Exception {
          this.procesoFDVClusterizacionDAO.updateClusterAsigPais(
          arrayCountryAsigSist, arrayCountryAsigPais, arrayZoneAsigPais,
          arrayZoneAsigSist, codProc);
    }

    public void updateFinalUpload(
                ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception {
          
          try{
                this.procesoFDVClusterizacionDAO.updateFinalUpload(procesoFDVClusterizacion, usuario);
          }catch(Exception e){
                if(e.getCause() instanceof UncategorizedSQLException){
                      throw new InvalidConsolidatedProcessException(
                      e.getCause().getCause().getCause().getMessage());
                }else{
                      throw e;
                }
          }
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateListaParamDist()
   */
    public void updateListaParamDist(String[] listCodParaDist,
                String[] listValParaDist, String codProc){
          
          
          String mensajeError = "";
          
          try{
                
                mensajeError = this.procesoFDVClusterizacionDAO.updateListaParamDist(
                listCodParaDist, listValParaDist, codProc);
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new Exception();
                }
                
          }catch(Exception e){
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, mensajeError, "actualizar");
                }else{
                      // Se lanzara una excepcion si existe algun fallo no controlado dentro del procedure
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, "", "actualizar");
                }           
          }
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateProcesoClusterizacion(
   *            biz.belcorp.ssicc.model.ProcesoFDVClusterizacion,
   *      biz.belcorp.ssicc.model.Usuario)
   */
    public void updateProcesoClusterizacion(
                ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception {

          //Verificamos que venga un valor en la descripcion
          if(StringUtils.isBlank(procesoFDVClusterizacion.getNomProc())){
                throw new InvalidDescriptionException(ProcesoFDVClusterizacion.class, procesoFDVClusterizacion.getNomProc());
          }
          
          // Verificamos que no exista un proceso con la misma descripción
      // Creamos el map que nos servirá como criterio de busqueda
          
          Map criteria = new HashMap();
          criteria.put("codProc", procesoFDVClusterizacion.getCodProc());
          criteria.put("nomProc", procesoFDVClusterizacion.getNomProc());
          criteria.put("codigoPais", procesoFDVClusterizacion.getCodigoPais());
          List listProcesos = this.procesoFDVClusterizacionDAO.getProcesosClusterByCriteria(criteria);
          if (listProcesos != null && listProcesos.size() > 0) {
          throw new InvalidDescriptionException(ProcesoFDVClusterizacion.class, procesoFDVClusterizacion.getNomProc());
      }
          
          // Realizamos el update a la tabla principal
      try{
          
         /* File zonaFile = procesoFDVClusterizacion.getZonaFile();
          File seccFile = procesoFDVClusterizacion.getSeccionFile();
          File variFile = procesoFDVClusterizacion.getVariablesExogFile();
          File noreFile = procesoFDVClusterizacion.getNoReconstruidaFile();
          
          if(!"".equals(zonaFile.getName())){
                procesoFDVClusterizacion.setArcZona(null);
          }
          
          if(!"".equals(seccFile.getName())){
                procesoFDVClusterizacion.setArcSecc(null);
          }
          
          if(!"".equals(variFile.getName())){
                procesoFDVClusterizacion.setArcVari(null);
          }
          
          if(!"".equals(noreFile.getName())){
                procesoFDVClusterizacion.setArcNore(null);
          }*/
          
            this.procesoFDVClusterizacionDAO.updateProcesoClusterizacion(procesoFDVClusterizacion, usuario);
      }catch(DataIntegrityViolationException e){
                // Se lanzara una excepcion si existe algun fallo en la integridad de los datos
                throw new InvalidTransactionProcessException(
                ProcesoFDVClusterizacion.class, e.getCause().getCause().getMessage(), "actualizar");               
          }
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updatePropuestaClusterizacion()
   */
    public void updatePropuestaClusterizacion(
                String[] listSelectedOffice, String[] listSelectedNoReliableData,
                String[] listCodZonaOffice, String[] listCodZonaNoReliableData,
                ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario)
                throws Exception {

          ProcesoFDVClusterizacion proceso = procesoFDVClusterizacionDAO.getProcesoCluster(procesoFDVClusterizacion.getCodProc());
          
          if(proceso.getVersionProceso() == null)
          {
                int versionProceso = procesoFDVClusterizacionDAO.getVersionProceso(procesoFDVClusterizacion.getCodProc(), procesoFDVClusterizacion.getAnyoPerProc(), procesoFDVClusterizacion.getPerProc());
                
                procesoFDVClusterizacion.setVersionProceso(versionProceso);
          }
          
          //Solo si no existe un proceso con el mismo año y periodo
          procesoFDVClusterizacion.setUpdatedByProcess(usuario.getLogin());
          procesoFDVClusterizacion.setLastUpdatedProcess(new Timestamp(System.currentTimeMillis()));
                      
          this.procesoFDVClusterizacionDAO.updatePropuestaClusterizacion(listSelectedOffice, 
          listSelectedNoReliableData,  listCodZonaOffice, listCodZonaNoReliableData, 
          procesoFDVClusterizacion, usuario);
                
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateStatusClusterizacion()
   */
    public void updateStatusClusterizacion(String codProc, Usuario usuario, String staProc) throws Exception {
          this.procesoFDVClusterizacionDAO.updateStatusClusterizacion(codProc, usuario, staProc);
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateValMevd()
   */
    public void updateValMevd(BigDecimal valMevd, String codProc, Usuario usuario){
          
          String mensajeError = "";
          
          try{
                
                mensajeError = this.procesoFDVClusterizacionDAO.updateValMevd(
                valMevd, codProc, usuario);
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new Exception();
                }
                
          }catch(Exception e){
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, mensajeError, "actualizar");
                }else{
                      // Se lanzara una excepcion si existe algun fallo no controlado dentro del procedure
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, "", "actualizar");
                }           
          }
    }

    /**
   * 
   * @throws Exception 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateZonaSeccion()
   */
    public void updateZonaSeccion(String[] listCodZonaDist,
          String[] listCamCaseDist, String[] listNroSecoDist, String codProc){
          
          String mensajeError = "";
          
          try{
                
                mensajeError = this.procesoFDVClusterizacionDAO.updateZonaSeccion(
                listCodZonaDist, listCamCaseDist, listNroSecoDist, codProc);
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new Exception();
                }
                
          }catch(Exception e){
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, mensajeError, "actualizar");
                }else{
                      // Se lanzara una excepcion si existe algun fallo no controlado dentro del procedure
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, "", "actualizar");
                }           
          }
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateZonasNoReliableDataVarVenta(java.lang.String[], java.lang.String)
     */
    public void updateZonasNoReliableDataVarVenta(
                String[] listSelectedNoReliableDataVarVenta, String codProc)
                throws Exception {
          this.procesoFDVClusterizacionDAO.updateZonasNoReliableDataVarVenta(listSelectedNoReliableDataVarVenta, codProc);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getAnyosProcesosDistribucionRealizadaByPais(java.lang.String)
     */
    public List getAnyosProcesosDistribucionRealizadaByPais(String codigoPais) {
          List anyos = procesoFDVClusterizacionDAO.getAnyosProcesosDistribucionRealizadaByPais(codigoPais);
          
          return anyos;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getValorParametroProceso(java.lang.String, java.lang.String)
     */
    public Double getValorParametroProceso(String codigoProceso, String codigoParametro) {
          return procesoFDVClusterizacionDAO.getValorParametroProceso(codigoProceso, codigoParametro);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getParametrosProceso(java.lang.String)
     */
    public List getParametrosProceso(String codigoProceso) {
          return procesoFDVClusterizacionDAO.getParametrosProceso(codigoProceso);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZonasAjusteDV(java.lang.String)
     */
    public List getZonasAjusteDV(String codigoProceso) {
          return procesoFDVClusterizacionDAO.getZonasAjusteDV(codigoProceso);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getTotalesZonasAjusteDV(java.util.List)
     */
    public Map getTotalesZonasAjusteDV(List lista) {
          Map totales = new HashMap();
          
          BigDecimal totalAjustePerido = new BigDecimal(0);
          BigDecimal totalAjusteDv = new BigDecimal(0);
          
          if(lista != null && lista.size() > 0)
          {
                for(int i=0; i<lista.size(); i++)
                {
                      Map registro = (HashMap)lista.get(i);
                      totalAjustePerido = totalAjustePerido.add((BigDecimal)MapUtils.getObject(registro, "metaAjustadaPeriodo"));
                      totalAjusteDv = totalAjusteDv.add((BigDecimal)MapUtils.getObject(registro, "metaAjustadaDv"));
                }
          }
          
          totales.put("totalAjustePerido", totalAjustePerido);
          totales.put("totalAjusteDv", totalAjusteDv);
                
          return totales;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#executeProcesoAjusteMetas(java.lang.String)
     */
    public void executeProcesoAjusteMetas(String codigoProceso) {
          String mensajeError = "";
          
          try{
                
                mensajeError = this.procesoFDVClusterizacionDAO.executeProcesoAjusteMetas(codigoProceso);
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new Exception();
                }
                
          }catch(Exception e){
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, mensajeError, "actualizar");
                }else{
                      // Se lanzara una excepcion si existe algun fallo no controlado dentro del procedure
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, "", "actualizar");
                }
          }
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateValoresAjustados(java.lang.String[], java.lang.String[], java.lang.String)
     */
    public void updateValoresAjustados(String[] listCodZonaAjus,
                String[] listValZonaAjus, String codigoProceso) {
          
          try{              
                procesoFDVClusterizacionDAO.updateValoresAjustados(listCodZonaAjus, listValZonaAjus, codigoProceso);
                
          }catch(Exception e){
                throw new InvalidTransactionProcessException(ProcesoFDVClusterizacion.class, e.getMessage(), "actualizar");
          }
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#executeProcedureClusterizacionDesdeArchivo(biz.belcorp.ssicc.model.ProcesoFDVClusterizacion)
     */
    public void executeProcedureClusterizacionDesdeArchivo(
                ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception {
          
          String mensajeError = "";
          
          try{
                
                mensajeError = this.procesoFDVClusterizacionDAO.executeProcedureClusterizacionDesdeArchivo(procesoFDVClusterizacion);
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new Exception();
                }
                
          }catch(Exception e){
                
                if(mensajeError != null && !"".equals(mensajeError)){
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, mensajeError, "actualizar");
                }else{
                      // Se lanzara una excepcion si existe algun fallo no controlado dentro del procedure
                      throw new InvalidTransactionProcessException(
                      ProcesoFDVClusterizacion.class, "", "actualizar");
                }           
          }
          
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateAjusteMeta(java.lang.String, java.lang.String)
	 */
	public void updateValorAjustadoArchivo(final List listValAjusteMeta,  final String codigoProceso) throws Exception{
		try {
			procesoFDVClusterizacionDAO.updateValorAjustadoArchivo(listValAjusteMeta, codigoProceso);
		} catch (Exception e) {
			throw new InvalidTransactionProcessException(ProcesoFDVClusterizacion.class, e.getMessage(), "actualizar");
		}
	}      
}
