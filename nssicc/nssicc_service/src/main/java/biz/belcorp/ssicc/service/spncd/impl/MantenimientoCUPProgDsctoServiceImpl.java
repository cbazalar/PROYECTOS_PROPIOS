package biz.belcorp.ssicc.service.spncd.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spncd.MantenimientoCUPProgDsctoDAO;
import biz.belcorp.ssicc.dao.spncd.model.DescuentoCupon;
import biz.belcorp.ssicc.dao.spncd.model.DespachoProducto;
import biz.belcorp.ssicc.dao.spncd.model.EquivalenciaMatriz;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaCupon;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaPeriodo;
import biz.belcorp.ssicc.dao.spncd.model.SuscripcionCabeceraConsultora;
import biz.belcorp.ssicc.dao.spncd.model.SuscripcionDetalleConsultora;
import biz.belcorp.ssicc.dao.spncd.model.SuscripcionNivelConsultora;
import biz.belcorp.ssicc.dao.spncd.model.UnidadAdministrativaCupon;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;

@Service("spncd.mantenimientoCUPProgDsctoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCUPProgDsctoServiceImpl extends BaseService implements MantenimientoCUPProgDsctoService {
    
	@Resource(name="spncd.mantenimientoCUPProgDsctoDAO")
	MantenimientoCUPProgDsctoDAO mantenimientoDAO;

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getProgramasDsctoByCriteria(java.util.Map)
     */
    public List getProgramasDsctoByCriteria(Map criteria) {
        return this.mantenimientoDAO.getProgramasDsctoByCriteria(criteria);
    }
    
    public void executeProcesoCUPDespachoProductos(Map params){
    	mantenimientoDAO.executeProcesoCUPDespachoProductos(params);    	
    	
    }    

    public void executeProcesoCUPCierreFacturacion(Map params){
    	mantenimientoDAO.executeProcesoCUPCierreFacturacion(params);    	
    	
    }    
    
    public List getDespachoProductosByCriteria(Map criteria) {
        return this.mantenimientoDAO.getDespachoProductosByCriteria(criteria);
    }

    public DespachoProducto getDespachoProductosById(Map criteria) {
        return this.mantenimientoDAO.getDespachoProductosById(criteria);
    }
    
	public void insertDespachoProductos(DespachoProducto despachoProducto, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoDAO.insertDespachoProductos(despachoProducto, usuario);
	}
	
	public void desactivarDespachoProducto(DespachoProducto despachoProducto) {
		// TODO Auto-generated method stub
		mantenimientoDAO.desactivarDespachoProducto(despachoProducto);
	}

	public BigDecimal getCodVentaDespachoProductoById(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoDAO.getCodVentaDespachoProductoById(criteria);		
	}

	public BigDecimal getDetOfertaEstrategiaCompuestaFijaById(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoDAO.getDetOfertaEstrategiaCompuestaFijaById(criteria);		
	}
	

    public DespachoProducto getOfertaDetalleById(Map criteria) {
        return this.mantenimientoDAO.getOfertaDetalleById(criteria);
    }
    
    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getProgramaDsctoById(java.util.Map)
     */
    public ProgramaCupon getProgramaDsctoById(Map criteria) {
    	ProgramaCupon cupon = this.mantenimientoDAO.getProgramaDsctoById(criteria);

    	List listaUAS = this.mantenimientoDAO.getListUnidadesAdministrativas(criteria);
    	List listaDES = this.mantenimientoDAO.getListDescuentos(criteria);
    	cupon.setListUnidadesAdministrativas(listaUAS);
    	cupon.setListDescuentos(listaDES);
    	

        return cupon;
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getTablaEquivalByCriteria(java.util.Map)
     */
    public List getTablaEquivalByCriteria(Map criteria) {
        return this.mantenimientoDAO.getTablaEquivalByCriteria(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#insertProgramaDsctoById(biz.belcorp.ssicc.spncd.dao.ProgCupon, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertProgramaDscto(ProgramaCupon cupon, Usuario usuario) {

        mantenimientoDAO.insertProgramaDscto(cupon, usuario);

        insertUnidadAdministrativa(cupon);
        insertDescuentos(cupon);

    }
    
    public List getProgramasDescuentosbyPais(Map criteria) {
        return mantenimientoDAO.getProgramasDescuentosbyPais(criteria);
    }

    public List getNivelbyPais(Map criteria) {
        return mantenimientoDAO.getNivelbyPais(criteria);
    }
    

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#updateProgramaDsctoById(biz.belcorp.ssicc.spncd.dao.ProgCupon, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateProgramaDscto(ProgramaCupon cupon, Usuario usuario) {
        mantenimientoDAO.updateProgramaDscto(cupon, usuario);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#deleteProgramaDscto(biz.belcorp.ssicc.spncd.dao.ProgCupon)
     */
    public void deleteProgramaDscto(ProgramaCupon cupon) {
        // TODO Auto-generated method stub
        mantenimientoDAO.deleteProgramaDscto(cupon);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#actualizarCuponNivel(java.util.Map)
	 */
	public void actualizarCuponNivel(Map criteria) {
		// TODO Auto-generated method stub
		mantenimientoDAO.actualizarCuponNivel(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getProgramasPeriodoByCriteria(java.util.Map)
	 */
	public List getProgramasPeriodoByCriteria(Map criteria) {
		// TODO Auto-generated method stub
		
		return mantenimientoDAO.getProgramasPeriodoByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getProgramaPeriodoById(java.util.Map)
	 */
	public ProgramaPeriodo getProgramaPeriodoById(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoDAO.getProgramaPeriodoById(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#insertProgramaPeriodo(biz.belcorp.ssicc.spncd.model.ProgramaPeriodo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertProgramaPeriodo(ProgramaPeriodo cupon, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoDAO.insertProgramaPeriodo(cupon, usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#deletePeriodoPrograma(biz.belcorp.ssicc.spncd.model.ProgramaPeriodo)
	 */
	public void deletePeriodoPrograma(ProgramaPeriodo cuponBean) {
		// TODO Auto-generated method stub
		mantenimientoDAO.deletePeriodoPrograma(cuponBean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#updateProgramaPeriodo(biz.belcorp.ssicc.spncd.model.ProgramaPeriodo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateProgramaPeriodo(ProgramaPeriodo cupon, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoDAO.updateProgramaPeriodo( cupon, usuario);	  
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getEquivalenciaMatrizByCriteria(java.util.Map)
	 */
	public List getEquivalenciaMatrizByCriteria(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoDAO.getEquivalenciaMatrizByCriteria( criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getEquivalenciaMatrizById(java.util.Map)
	 */
	public EquivalenciaMatriz getEquivalenciaMatrizById(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoDAO.getEquivalenciaMatrizById( criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#updateEquivalenciaMatriz(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEquivalenciaMatriz(EquivalenciaMatriz cupon, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoDAO.updateEquivalenciaMatriz( cupon,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#insertEquivalenciaMatriz(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEquivalenciaMatriz(EquivalenciaMatriz cupon, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoDAO.insertEquivalenciaMatriz( cupon,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#deleteEquivalenciaMatriz(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public void deleteEquivalenciaMatriz(EquivalenciaMatriz cuponBean) {
		// TODO Auto-generated method stub
		mantenimientoDAO.deleteEquivalenciaMatriz(cuponBean);
		mantenimientoDAO.deleteLoveEquivalenciaMatriz(cuponBean);
		mantenimientoDAO.deleteCuponLoveDefault(cuponBean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getNextCodigoPrograma()
	 */
	public String getNextCodigoPrograma(String pais) {
		// TODO Auto-generated method stub
		return mantenimientoDAO.getNextCodigoPrograma( pais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getProgramaActivo(java.lang.String)
	 */
	public String getProgramaActivo(String codigo) {
		// TODO Auto-generated method stub
		return mantenimientoDAO.getProgramaActivo( codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getCodigoCuponInicial(java.util.Map)
	 */
	public String getCodigoCuponInicial(Map criteria) {
		// TODO Auto-generated method stub
		return  mantenimientoDAO.getCodigoCuponInicial( criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getCodigoCuponFinal(java.util.Map)
	 */
	public String getCodigoCuponFinal(Map criteria) {
		// TODO Auto-generated method stub
		return  mantenimientoDAO.getCodigoCuponFinal( criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getCodigoPeriodoInicial(java.util.Map)
	 */
	public String getCodigoPeriodoInicial(Map criteria) {
		// TODO Auto-generated method stub
		return  mantenimientoDAO.getCodigoPeriodoInicial( criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getCodigoPeriodoFinal(java.util.Map)
	 */
	public String getCodigoPeriodoFinal(Map criteria) {
		// TODO Auto-generated method stub
		return  mantenimientoDAO.getCodigoPeriodoFinal( criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getPeriodoDefaultByPrograma(java.util.Map)
	 */
	public String getPeriodoDefaultByPrograma(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoDAO.getPeriodoDefaultByPrograma( criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#validaPrioridad(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public String validaPrioridad(EquivalenciaMatriz cupon){
		return mantenimientoDAO.validaPrioridad(cupon);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#updateValorUnidadNivel(java.util.Map)
	 */
	public void updateValorUnidadNivel(Map criteria){
		mantenimientoDAO.updateValorUnidadNivel(criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getCupNoAsignadosUtilizados(java.util.Map)
	 */
	public List getCupNoAsignadosUtilizados(Map criteria){
		return mantenimientoDAO.getCupNoAsignadosUtilizados(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getProductosMatrizByCriteria(java.util.Map)
	 */
	public List getProductosMatrizByCriteria(Map criteria){
		return mantenimientoDAO.getProductosMatrizByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getCabecConsultorasSuscripcion(java.util.Map)
	 */
	public SuscripcionCabeceraConsultora getCabecConsultorasSuscripcion(Map criteria){
		return mantenimientoDAO.getCabecConsultorasSuscripcion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getNivelesSuscripcion(java.util.Map)
	 */
	public List getNivelesSuscripcion(Map criteria){
		return mantenimientoDAO.getNivelesSuscripcion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getNivelesSuscripcionEdit(java.util.Map)
	 */
	public List getNivelesSuscripcionEdit(Map criteria){
		return mantenimientoDAO.getNivelesSuscripcionEdit(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#saveSuscripcionConsultoras(java.util.Map)
	 */
	public void saveSuscripcionConsultoras(Map criteria){
		
		List listaNiveles = (List) criteria.get("listaNiveles");
		String [] listaCantidadPedida = (String[]) criteria.get("listaCantidadPedida");
		Usuario usuario =  (Usuario) criteria.get("usuario");				
		String codigoConsultora=(String) criteria.get("codigoConsultora");
		String codigoPeriodo=(String) criteria.get("codigoPeriodo");
		String codigoPais = ((Map) listaNiveles.get(0)).get("codigoPais").toString();
		String codigoPrograma = ((Map) listaNiveles.get(0)).get("codigoPrograma").toString();
		String indicadoExiste="";
		
		Map criteriaConsultora = new HashMap();
		
		criteriaConsultora.put("codigoPais", codigoPais);
		criteriaConsultora.put("codigoPrograma",codigoPrograma);
		criteriaConsultora.put("codigoConsultora",codigoConsultora);
		criteriaConsultora.put("codigoMarca",Constants.CODIGO_MARCA_DEFAULT);	
											
		if (getCabecConsultorasSuscripcion(criteriaConsultora) ==  null){	// consultora no registrada
			
			indicadoExiste=Constants.ESTADO_ACTIVO;
			SuscripcionCabeceraConsultora consultora = new SuscripcionCabeceraConsultora();
			consultora.setCodigoPais(codigoPais);
			consultora.setCodigoPrograma(codigoPrograma);
			consultora.setCodigoConsultora(codigoConsultora);
			consultora.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			consultora.setCodigoPeriodo(codigoPeriodo);
			consultora.setEstadoRegistro(Constants.ESTADO_ACTIVO);
			consultora.setCodigoUsuario(usuario.getLogin());
			
			mantenimientoDAO.saveSuscripcionConsultoras( consultora,usuario);
						
		}
		else{ //Consultora Registrada
			indicadoExiste=Constants.ESTADO_INACTIVO;					
		}
			
		
		String nivelAnt="";
		
		SuscripcionNivelConsultora nivel = new SuscripcionNivelConsultora();
		SuscripcionDetalleConsultora detalle = new SuscripcionDetalleConsultora();			

		for (int i=0; i<listaNiveles.size();i++){					
			
			Map fila = new HashMap();
			fila = (Map) listaNiveles.get(i);
			
			log.debug("fila"+i+":"+fila.toString()+"Cant:"+listaCantidadPedida[i]);			
			
			detalle.setCodigoPais(fila.get("codigoPais").toString());
			detalle.setCodigoPrograma(fila.get("codigoPrograma").toString());
			detalle.setCodigoConsultora(codigoConsultora);
			detalle.setCodigoNivel(fila.get("codigoNivel").toString());
			detalle.setCodigoCupon(fila.get("codigoCupon").toString());
			if (fila.get("codigoVenta") ==null)				
				detalle.setCodigoVenta("");
			else 
				detalle.setCodigoVenta(fila.get("codigoVenta").toString());
			detalle.setCodigoVenta(fila.get("codigoVenta").toString());
			if (fila.get("descripcionProducto") == null)
				detalle.setDescripcionProducto("");
			else 
				detalle.setDescripcionProducto(fila.get("descripcionProducto").toString());
			detalle.setValorDemandada(listaCantidadPedida[i]);
			detalle.setIndicadorUtilidad(Constants.OK);
			detalle.setCampanhaUtilidad("");			
			detalle.setCodigoUsuario(usuario.getLogin());
			
			if (indicadoExiste.equals(Constants.ESTADO_ACTIVO)){ //Consultora no registrada
				if ( !fila.get("codigoNivel").toString().equals(nivelAnt) ){
					nivel.setCodigoPais(fila.get("codigoPais").toString());
					nivel.setCodigoPrograma(fila.get("codigoPrograma").toString());
					nivel.setCodigoConsultora(codigoConsultora);
					nivel.setCodigoNivel(fila.get("codigoNivel").toString());
					nivel.setCampanhaInicio(fila.get("campanaInicio").toString());
					nivel.setCampanhaFin(fila.get("campanaFin").toString());
					nivel.setCodigoUsuario(usuario.getLogin());
					mantenimientoDAO.saveSuscripcionConsultorasNivel( nivel,usuario);				
				}
				log.debug("----------------"+detalle.getValorDemandada());
				if(!detalle.getValorDemandada().equals(""))
					mantenimientoDAO.saveSuscripcionConsultorasDetalle( detalle,usuario);
			}
			else	//Consultora Registrada	
				if(!detalle.getValorDemandada().equals(""))
					if (mantenimientoDAO.getDetalleSuscripcionConsultora(detalle) == null ) // si existe el detalle
						mantenimientoDAO.saveSuscripcionConsultorasDetalle( detalle,usuario);						
					else
						mantenimientoDAO.updateSuscripcionConsultorasDetalle( detalle,usuario);
				else
					mantenimientoDAO.deleteSuscripcionDetalle(detalle);
						
			nivelAnt = fila.get("codigoNivel").toString();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#cierreCampanha(java.util.Map)
	 */
	public void executeCierreCampanha(Map map){
		mantenimientoDAO.executeCierreCampanha(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#guardarCuponLoveDefault(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public void guardarCuponLoveDefault(EquivalenciaMatriz cupon){
		mantenimientoDAO.guardarCuponLoveDefault(cupon);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#guardarEquivalenciaCuponLove(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public void guardarEquivalenciaCuponLove(EquivalenciaMatriz cupon){
		mantenimientoDAO.guardarEquivalenciaCuponLove(cupon);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#eliminarEquivalenciaLove(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public void eliminarEquivalenciaLove(EquivalenciaMatriz cupon){
		mantenimientoDAO.deleteLoveEquivalenciaMatriz(cupon);
		mantenimientoDAO.deleteCuponLoveDefault(cupon);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#eliminarDefaultLove(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public void eliminarDefaultLove(EquivalenciaMatriz cupon){
		mantenimientoDAO.deleteCuponLoveDefault(cupon);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#almacenarEquivalencia(biz.belcorp.ssicc.model.Usuario, biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz, boolean)
	 */
	public void almacenarEquivalencia(Usuario usuario, EquivalenciaMatriz cupon, boolean updateFlag){		
		//Esto es como funciona actualmente
		log.debug("insert bean " + cupon);
		if (updateFlag) {
			updateEquivalenciaMatriz(cupon, usuario);
		} 
		else{
			insertEquivalenciaMatriz(cupon, usuario);
		}	
		//---------------------------------------
		//Si esta marcado el check de cupon LOVE
		//---------------------------------------
		if (cupon.getIndicadorLove()!=null){
			log.debug("Almaceno cupon love");
			guardarEquivalenciaCuponLove(cupon);
		}
		else{
			eliminarEquivalenciaLove(cupon);
		}
		//----------------------------------------
		//Si esta marcado el check de LOVE default
		//----------------------------------------
		if (cupon.getIndicadorLoveDefault() != null){
			log.debug("Almaceno default love");
			
			// Verificamos si ya existe			
			Map cuponLoveDefault = mantenimientoDAO.getCuponLoveDefault(cupon);
			
			// Si no existe lo insertamamos
			if(cuponLoveDefault == null)			
				guardarCuponLoveDefault(cupon);
		}
		else{
			eliminarDefaultLove(cupon);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getExcepciones(java.util.Map)
	 */
	public List getExcepciones(Map criteria){
		return mantenimientoDAO.getExcepciones(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#insertExcepciones(java.util.Map)
	 */
	public void insertExcepciones(Map criteria){
		mantenimientoDAO.insertExcepciones(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#deleteExcepciones(java.lang.String[])
	 */
	public void deleteExcepciones(String[] listaEliminar){
		Map criteria = new HashMap();
		for (int i = 0; i < listaEliminar.length; i++) {
			criteria.put("codigoPais"    , StringUtils.split(listaEliminar[i], "|")[0]);
			criteria.put("codigoPeriodo" , StringUtils.split(listaEliminar[i], "|")[1]);
			criteria.put("codigoPrograma", StringUtils.split(listaEliminar[i], "|")[2]);
			criteria.put("codigoRegion"  , StringUtils.split(listaEliminar[i], "|")[3]);
			criteria.put("codigoZona"    , StringUtils.split(listaEliminar[i], "|")[4]);
			mantenimientoDAO.deleteExcepciones(criteria);
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getCuponesPeriodo(java.util.Map)
	 */
	public String getCuponesPeriodo(Map criteria){
		return mantenimientoDAO.getCuponesPeriodo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getCodigoVentaPeriodo(java.util.Map)
	 */
	public String getCodigoVentaPeriodo(Map criteria){
		return mantenimientoDAO.getCodigoVentaPeriodo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getEquivalenciaMatrizByIdLove(java.util.Map)
	 */
	public EquivalenciaMatriz getEquivalenciaMatrizByIdLove(Map criteria) {
		return mantenimientoDAO.getEquivalenciaMatrizByIdLove( criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getProdutoPrimerPedidoList(java.util.Map)
	 */
	public List getProdutoPrimerPedidoList(Map criteria){
		return mantenimientoDAO.getProdutoPrimerPedidoList(criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#updateProdutoPrimerPedido(java.util.Map)
	 */
	public void updateProdutoPrimerPedido(Map criteria){
		mantenimientoDAO.updateProdutoPrimerPedido(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#deleteProdutosPrimerPedido(java.lang.String[], java.lang.String)
	 */
	public void deleteProdutosPrimerPedido(String[] items, String usuario){
		Map criteria = new HashMap();
		for (int i = 0; i < items.length; i++) {						
			criteria.put("codigoPais"    ,StringUtils.split(items[i], "|")[0]);
			criteria.put("codigoPrograma",StringUtils.split(items[i], "|")[1]);
			criteria.put("codigoPeriodo" ,StringUtils.split(items[i], "|")[2]);
			criteria.put("codigoVenta"   ,StringUtils.split(items[i], "|")[3]);
			criteria.put("codigoUsuario" ,usuario);
			criteria.put("estado"        ,Constants.NUMERO_CERO);
			updateProdutoPrimerPedido(criteria);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getIndicadorDigitable(java.util.Map)
	 */
	public List getIndicadorDigitable(Map criteria){
		return mantenimientoDAO.getIndicadorDigitable(criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#insertProductoPrimerPedido(java.util.Map)
	 */
	public void insertProductoPrimerPedido(Map criteria){
		try {
			mantenimientoDAO.insertProductoPrimerPedido(criteria);
		} catch (Exception e) {
			criteria.put("estado", Constants.NUMERO_UNO);
			mantenimientoDAO.updateProdutoPrimerPedido(criteria);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#verificaCruce(java.util.Map)
	 */
	public String verificaCruce(Map criteria){
		return mantenimientoDAO.verificaCruce(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getPeriodoInicioByCupon(java.util.Map)
	 */
	public String getPeriodoInicioByCupon(Map criteria){
		return mantenimientoDAO.getPeriodoInicioByCupon(criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getPeriodoFinByCupon(java.util.Map)
	 */
	public String getPeriodoFinByCupon(Map criteria){
		return mantenimientoDAO.getPeriodoFinByCupon(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#devuelveDescripcionRegion(java.util.Map)
	 */
	public String devuelveDescripcionRegion(Map criteria){
		return mantenimientoDAO.devuelveDescripcionRegion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#devuelveDescripcionZona(java.util.Map)
	 */
	public String devuelveDescripcionZona(Map criteria){
		return mantenimientoDAO.devuelveDescripcionZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#verificaConsultoraPrograma(java.util.Map)
	 */
	public String verificaConsultoraPrograma(Map criteria){
		return mantenimientoDAO.verificaConsultoraPrograma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#updateProgramaDscto2(java.util.Map)
	 */
	public void updateProgramaDscto2(Map criteria) {
        mantenimientoDAO.updateProgramaDscto2(criteria);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#updateUnidadAdministrativa(java.util.Map)
	 */
	public void updateUnidadAdministrativa(Map criteria) {
        mantenimientoDAO.updateUnidadAdministrativa(criteria);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#insertUnidadAdministrativa(biz.belcorp.ssicc.spncd.model.ProgramaCupon)
	 */
	public void insertUnidadAdministrativa(ProgramaCupon cupon) {
		 if (Constants.NUMERO_CERO.equals(cupon.getIndicadorTodasUA())){
	        	//Insertamos las unidades administrativas
	            if(cupon.getListUnidadesAdministrativas() != null) {
	            	List listConcursoAmbitoGeografico = cupon.getListUnidadesAdministrativas();

	    	    	for(int i=0; i<listConcursoAmbitoGeografico.size(); i++) {
	    	    		UnidadAdministrativaCupon unidadAdministrativa = (UnidadAdministrativaCupon)listConcursoAmbitoGeografico.get(i);
	    	    		unidadAdministrativa.setOidPeriodo(null);
	    	    		unidadAdministrativa.setCodigoPeriodo(null);
	    	    		unidadAdministrativa.setUsuario(cupon.getCodigoUsuario());

	    	    		String oidUAS = mantenimientoDAO.getNextOidUAS();
	    	    		unidadAdministrativa.setOid(new Long(oidUAS));
	    	    		mantenimientoDAO.insertUnidadAdministrativa(unidadAdministrativa);
	    	    	}
	            }
	        }
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getPeriodosPrograma(java.util.Map)
	 */
	public String getPeriodosPrograma(Map criteria){
		return mantenimientoDAO.getPeriodosPrograma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getPeriodoSiguiente(java.util.Map)
	 */
	public String getPeriodoSiguiente(Map criteria){
		return mantenimientoDAO.getPeriodoSiguiente(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#validarMatrizFacturacion(java.util.Map)
	 */
	public String validarMatrizFacturacion(Map criteria){
		return mantenimientoDAO.validarMatrizFacturacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getDetalleProductoByIdOferta(java.util.Map)
	 */
	public List getDetalleProductoByIdOferta(Map criteria){
		return mantenimientoDAO.getDetalleProductoByIdOferta(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#insertaRegaloxPedido(java.util.Map)
	 */
	public void insertaRegaloxPedido(Map criteria){
		mantenimientoDAO.insertaRegaloxPedido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getNumeroNiveles(java.util.Map)
	 */
	public String getNumeroNiveles(Map criteria){
		return mantenimientoDAO.getNumeroNiveles(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getNivelesPermitidos(java.util.Map)
	 */
	public List getNivelesPermitidos(Map criteria){
		return mantenimientoDAO.getNivelesPermitidos(criteria);
	}
	
	/* INI SA PER-SiCC--2012-0467 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#verificaProgramaPrimerPedido(java.util.Map)
	 */
	public boolean verificaProgramaPrimerPedido(Map criteria) {
		return mantenimientoDAO.verificaProgramaPrimerPedido(criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#existenCuponesConIndicadorKit(java.util.Map)
	 */
	public boolean existenCuponesConIndicadorKit(Map criteria) {
		return mantenimientoDAO.existenCuponesConIndicadorKit(criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#existenDespachosConIndicadorKit(java.util.Map)
	 */
	public boolean existenDespachosConIndicadorKit(Map criteria) {
		return mantenimientoDAO.existenDespachosConIndicadorKit(criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#verificaCruceProgramaUA(java.util.Map)
	 */
	public void verificaCruceProgramaUA(Map criteria){
		mantenimientoDAO.verificaCruceProgramaUA(criteria);
	}
	/* FIN SA PER-SiCC--2012-0467 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getPeriodo(java.util.Map)
	 */
	public String getPeriodo(Map criteria) {
		return mantenimientoDAO.getPeriodo(criteria);
	}
	
	public void insertPeriodo(Map criteria){
		mantenimientoDAO.insertPeriodo(criteria);
	}
	
	public String getParametroPrograma(String parametro){
		return mantenimientoDAO.getParametroPrograma(parametro);
	}

	public int validarPeriodoCampahnaFin(Map criteria) {
		return mantenimientoDAO.validarPeriodoCampahnaFin(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getRegaloPorPedido(java.util.Map)
	 */
	public String getRegaloPorPedido(Map criteria) {
		return mantenimientoDAO.getRegaloPorPedido(criteria);
	}

	public void eliminarDespachoProducto(DespachoProducto despachoProducto) {
		mantenimientoDAO.eliminarDespachoProducto(despachoProducto);	
	}

	public int validaTipoOferta(Map criteria) {
		return mantenimientoDAO.validaTipoOferta(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#validaTipoOfertaDuplicado(java.util.Map)
	 */
	public int validaTipoOfertaDuplicado(Map criteria) {
		return mantenimientoDAO.validaTipoOfertaDuplicado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getCantZonasxRegion(java.util.Map)
	 */
	public int getCantZonasxRegion(Map criteria) {
		return mantenimientoDAO.getCantZonasxRegion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getZonasDisponiblesRegion(java.util.Map)
	 */
	public List getZonasDisponiblesRegion(Map criteria) {
		return mantenimientoDAO.getZonasDisponiblesRegion(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getConsultaSOAProgramaNuevas(java.util.Map)
	 */
	public List getConsultaSOAProgramaNuevas(Map criteria){
		return mantenimientoDAO.getConsultaSOAProgramaNuevas(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getConsultaSOAIncentivos(java.util.Map)
	 */
	public List getConsultaSOAIncentivos(Map criteria){
		return mantenimientoDAO.getConsultaSOAIncentivos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getDevuelveOidConcurso(java.util.Map)
	 */
	public Long getDevuelveOidConcurso(Map criteria){
		return mantenimientoDAO.getDevuelveOidConcurso(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#existenDespachoConIndicadorKitSegundoPedido(java.util.Map)
	 */
	public boolean existenDespachoConIndicadorKitSegundoPedido(Map criteria) {
		return mantenimientoDAO.existenDespachoConIndicadorKitSegundoPedido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getTipoPedidoCUP()
	 */
	public List getTipoPedidoCUP() {
		return mantenimientoDAO.getTipoPedidoCUP();
	}
	
	public String getExisteDescuento(Map criteria) {
		return mantenimientoDAO.getExisteDescuento( criteria);
	}
	
	public void insertDescuentos(DescuentoCupon descuento) {
		mantenimientoDAO.insertDescuentos(descuento);
	}
	
	public void insertDescuentos(ProgramaCupon cupon) {
		
		
		if(cupon.getListDescuentos()!=null)
		{	
			
				List listDescuentos = cupon.getListDescuentos();
				for (int i=0;i<listDescuentos.size();i++){
					DescuentoCupon p1 = (DescuentoCupon)listDescuentos.get(i);
					
					Map criteriaDescuento = new HashMap();
					
					criteriaDescuento.put("codigoPais", cupon.getCodigoPais());
					criteriaDescuento.put("codigoPrograma", cupon.getCodigoPrograma());
					criteriaDescuento.put("campanhaInicio", p1.getCampanhaInicio());
					criteriaDescuento.put("codigoNivel", p1.getNivel());
					criteriaDescuento.put("usuario", cupon.getCodigoUsuario());
					p1.setUsuario(cupon.getCodigoUsuario());
					
					String indeExisteDescuento= mantenimientoDAO.getExisteDescuentoTotal(criteriaDescuento);
					if(StringUtils.equals(indeExisteDescuento, Constants.NUMERO_CERO))
					{
						mantenimientoDAO.insertDescuentos(p1);
					}else{
						
						
						criteriaDescuento.put("usuario", cupon.getCodigoUsuario());
						criteriaDescuento.put("montoDescuento", p1.getMontoDescuento());
						criteriaDescuento.put("montoVentaExigido", p1.getMontoVentaExigencia());
						criteriaDescuento.put("campanhaFin", p1.getCampanhaFin());
						criteriaDescuento.put("codigoVenta", p1.getCodigoVenta());
						criteriaDescuento.put("estadoRegistro", Constants.ESTADO_ACTIVO);
						
						mantenimientoDAO.updateDescuentos(criteriaDescuento);
					}
				}
			
				Map criteriaDescuento = new HashMap();
				criteriaDescuento.put("codigoPais", cupon.getCodigoPais());
				criteriaDescuento.put("codigoPrograma", cupon.getCodigoPrograma());
				criteriaDescuento.put("usuario", cupon.getCodigoUsuario());
				
				List listaBD = mantenimientoDAO.getListDescuentos(criteriaDescuento);
				
				if(listaBD!=null && listaBD.size()>0){
					
					for(int i=0;i<listaBD.size();i++){
						boolean indExist=false;
						String nivelBD=((DescuentoCupon)listaBD.get(i)).getNivel();
						String programaBD=((DescuentoCupon)listaBD.get(i)).getCodigoPrograma();
						String campanyaInicioBD=((DescuentoCupon)listaBD.get(i)).getCampanhaInicio();
						
						for(int j=0;j<listDescuentos.size();j++){
							
							if(StringUtils.equals(nivelBD, ((DescuentoCupon)listDescuentos.get(j)).getNivel()) 
									&& StringUtils.equals(programaBD, ((DescuentoCupon)listDescuentos.get(j)).getCodigoPrograma())
									&& StringUtils.equals(campanyaInicioBD, ((DescuentoCupon)listDescuentos.get(j)).getCampanhaInicio())){
								indExist=true;
							}
						}
						if(!indExist){
							criteriaDescuento.put("codigoNivel", ((DescuentoCupon)listaBD.get(i)).getNivel());
							criteriaDescuento.put("campanhaInicio", ((DescuentoCupon)listaBD.get(i)).getCampanhaInicio());
							criteriaDescuento.put("estadoEliminado", Constants.ESTADO_INACTIVO);
							
							mantenimientoDAO.deleteDescuentos(criteriaDescuento);
						}
					}
				}
		}
	}
	
	
	public void updateDescuentos(Map criteria) {
        mantenimientoDAO.updateDescuentos(criteria);
    }
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPProgDsctoService#getIndicadorCuponReutilizable(java.util.Map)
	 */
	public String getIndicadorCuponReutilizable(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoDAO.getIndicadorCuponReutilizable(criteria);
	}

}