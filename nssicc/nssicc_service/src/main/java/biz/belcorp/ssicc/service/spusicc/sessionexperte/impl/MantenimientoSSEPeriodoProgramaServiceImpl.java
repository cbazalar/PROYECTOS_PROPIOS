package biz.belcorp.ssicc.service.spusicc.sessionexperte.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEPeriodoProgramaDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEProductoDeProgramaSSEDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEProductoPeriodoDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.PeriodoPrograma;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoDeProgramaSessionExperte;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoPeriodo;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProgramaSessionExperte;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sessionexperte.MantenimientoSSEPeriodoProgramaService;

/**
 * <p>
 * <a href="MantenimientoSSEPeriodoProgramaServiceImpl.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 */
@Service("spusicc.mantenimientoSSEPeriodoProgramaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSSEPeriodoProgramaServiceImpl extends BaseService implements MantenimientoSSEPeriodoProgramaService {

	@Resource(name="spusicc.sessionexperte.mantenimientoSSEPeriodoProgramaDAO")
	MantenimientoSSEPeriodoProgramaDAO mantenimientoDAO;
	
	@Resource(name="spusicc.sessionexperte.mantenimientoSSEProductoPeriodoDAO")
	MantenimientoSSEProductoPeriodoDAO mantenimientoProductoPeriodoDAO;
	
	@Resource(name="spusicc.sessionexperte.mantenimientoSSEProductoDeProgramaSSEDAO")
	MantenimientoSSEProductoDeProgramaSSEDAO mantenimientoSSEProductoDeProgramaSSEDAO;
	/**
	 * Obtiene un listado de todos los periodos de los programas session experte con sus respectivos productos segun criteria.
	 * @param criteria Objeto PeriodoPrograma cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos PeriodoPrograma, poblados.
	 */
    public List getPeriodosProgramaByCriteria(PeriodoPrograma periodo) {
        return mantenimientoDAO.getPeriodosProgramaByCriteria(periodo);
    }

	/**
	 * Obtiene el primer objeto del listado de todos los programas session experte.
	 * @param periodo PeriodoPrograma cuyos atributos son usados como criterios de bsqueda.
	 * @return Un objeto PeriodoPrograma.
	 */
    public PeriodoPrograma getFirstFromPeriodosProgramaByCriteria(PeriodoPrograma periodo) {
        return (PeriodoPrograma)(mantenimientoDAO.getPeriodosProgramaByCriteria(periodo)).get(0);
    }

	/**
	 * Obtiene el MAXIMO codigo de periodo existente para un programa
	 * @param periodo Periodo que contiene el codigo de pais y programa
	 * @return Maximo Cdigo de Periodo para un programa 
	 */
	public String getPeriodoDefaultByPrograma(PeriodoPrograma periodo){
        return mantenimientoDAO.getPeriodoDefaultByPrograma(periodo);
    }

	/**
	 * Obtiene el SIGUIENTE codigo de periodo para un programa
	 * @param periodo Periodo que contiene el codigo de pais y programa
	 * @return SIGUIENTE Cdigo de Periodo para un programa 
	 */
	public String getNextPeriodoBySSEProgPerio(PeriodoPrograma periodo, ProgramaSessionExperte programa){
		String siguientePeriodo = mantenimientoDAO.getNextPeriodoBySSEProgPerio(periodo);

		String fechaFinalPeriodo = programa.getCampanhaFinal();
		
		if (StringUtils.isEmpty(siguientePeriodo))
			siguientePeriodo = programa.getCampanhaInicial();
		else 
		if (fechaFinalPeriodo.compareTo(siguientePeriodo) < 0)
			return "";
		return siguientePeriodo;
    }
	
	/**
	 * Inserta un Periodo de Programa y sus productos.
	 * @param periodo PeriodoPrograma, coge los datos de Pais, Program y periodo para crear un nuevo periodo.
	 * @param usuario Usuario que inserta.
	 */
	public void insertPeriodoPrograma(PeriodoPrograma periodo, Usuario usuario) {
		periodo.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		//1. Insertar periodo en SSE_PROGR_PERIO, pues aun no existia
		mantenimientoDAO.insertPeriodoProgramaSSE(periodo, usuario);
		
		//2. Borrar aquellos productos no asignados al periodo
		if (periodo.getProductosNoAsignados()!=null)
			mantenimientoProductoPeriodoDAO.deleteListaProductosNoAsignadosPeriodo(periodo);

		//3. Obtener listado de Codigos de Productos asignados al periodo (ya se borraron los que se desasignaron)
		List listaProductosExistentes = mantenimientoProductoPeriodoDAO.getCodigoProductosPeriodosByCriteria(periodo);
		//List listaCuponExistentes = (getSqlMapClientTemplate().queryForList("spusicc.sessionexperte.PeriodoProgramaSQL.getCodigoProductosPeriodosByCriteria", criteria));

		//4. Comparar contra la lista de codigos existentes, la lista de asignados por si hay un producto nuevo que se asigno		
		insertNuevosProductosAPeriodo(periodo, listaProductosExistentes, usuario);
	}

	/**
	 * Actualiza un Periodo de Programa y sus productos.
	 * @param periodo PeriodoPrograma, coge los datos de Pais, Program y periodo para crear un nuevo periodo.
	 * @param usuario Usuario que inserta.
	 */
	public void updatePeriodoPrograma(PeriodoPrograma periodo, Usuario usuario) {
		//1. Borrar aquellos productos no asignados al periodo
		if (	periodo != null 
				&& periodo.getProductosNoAsignados() != null
				&& periodo.getProductosNoAsignados().length > 0
			){
			for (int i=0; i<periodo.getProductosNoAsignados().length; i++){
				log.debug("DELETE Producto No Asignado["+periodo.getProductosNoAsignados()[i]+"]");
			}
			mantenimientoProductoPeriodoDAO.deleteListaProductosNoAsignadosPeriodo(periodo);
		}

		//2. Obtener listado de Codigos de Productos asignados al periodo (ya se borraron los que se desasignaron)
		List listaProductosExistentes = mantenimientoProductoPeriodoDAO.getCodigoProductosPeriodosByCriteria(periodo);
			
		//3. Comparar contra la lista de codigos existentes, la lista de asignados por si hay un producto nuevo que se asigno		
		insertNuevosProductosAPeriodo(periodo, listaProductosExistentes, usuario);
	}
	
	/**
	 * Comparar contra la lista de codigos existentes, la lista de asignados por si hay un producto nuevo que se asigno
	 * @param periodo Periodo al que pertenecen los productos.
	 * @param listaProductosExistentes Lista de codigos de producto existentes.
	 * @param usuario Usuario que inserta/modifica.
	 */
	private void insertNuevosProductosAPeriodo(PeriodoPrograma periodo, List listaProductosExistentes, Usuario usuario){
		//4. Comparar contra la lista de codigos existentes, la lista de asignados por si hay un producto nuevo que se asigno
		ProductoPeriodo productoPeriodo = new ProductoPeriodo();
		productoPeriodo.setCodigoPais(periodo.getCodigoPais());
		productoPeriodo.setCodigoPrograma(periodo.getCodigoPrograma());
		productoPeriodo.setCodigoPeriodo(periodo.getCodigoPeriodo());
		productoPeriodo.setCodigoAnho(periodo.getCodigoAnho());
		
		if (periodo.getProductosAsignados() != null){
		String[] productosAsignados = periodo.getProductosAsignados();
		for (int i = 0; i < productosAsignados.length; i++) {
			String codigoProducto = productosAsignados[i];
			log.debug("L productosAsignados i="+i+", Codigo Producto ["+codigoProducto+"]");
			productoPeriodo.setCodigoProducto(codigoProducto);

			boolean nuevo = true;
			for (int j = 0; j < listaProductosExistentes.size(); j++) {
				String codigoProductoExistente = (String) listaProductosExistentes.get(j);
				
				if (StringUtils.equals(codigoProducto, codigoProductoExistente))
					nuevo = false;
			}//end for j
			
			if (nuevo){
				// prepare for search
				ProductoDeProgramaSessionExperte productoPrograma = new ProductoDeProgramaSessionExperte();
				productoPrograma.setCodigoPais(productoPeriodo.getCodigoPais());
				productoPrograma.setCodigoPrograma(productoPeriodo.getCodigoPrograma());
				productoPrograma.setCodigoAnho(productoPeriodo.getCodigoAnho());
				productoPrograma.setCodigoProducto(codigoProducto);
				
				log.debug("NUEVO Codigo Producto ["+codigoProducto+"]");
				List resultadoQuery = mantenimientoSSEProductoDeProgramaSSEDAO.getProductosDeProgramaSessionExperteByCriteria(productoPrograma);
				
				if (resultadoQuery!=null && resultadoQuery.size()>0){
					productoPrograma = (ProductoDeProgramaSessionExperte)(resultadoQuery.get(0));
					productoPeriodo.setDescripcionProducto(productoPrograma.getDescripcionProducto());
					log.debug("--> Descripcion encontrada = ["+productoPrograma.getDescripcionProducto()+"]");
				}else{
					log.debug("--> Descripcion NO encontrada");
					productoPeriodo.setDescripcionProducto(productoPeriodo.getCodigoProducto()+": Sin Descripcion.");
				}
				/*
				LabelDatosProductoValue labelDatosProductoValue = mantenimientoSSEProductoDeProgramaSSEDAO.getDatosProductoByCriteria(productoPrograma);
				if (labelDatosProductoValue!=null){
					log.debug("--> Descripcion encontrada = ["+labelDatosProductoValue.getDescripcionProducto()+"]");
					productoPeriodo.setDescripcionProducto(labelDatosProductoValue.getDescripcionProducto());
				}else{
					log.debug("--> Descripcion NO encontrada");
					productoPeriodo.setDescripcionProducto("Descripcion No encontrada para ["+productoPeriodo.getCodigoProducto()+"]");
				}
				*/
				log.debug("INSERT productoPeriodo="+productoPeriodo);
				mantenimientoProductoPeriodoDAO.insertProductoPeriodo(productoPeriodo, usuario);
			}
		}// end for i
		}
	}

	/**
	 * Cambia el estado a INACTIVO del Periodo de Programa (No lo elimina).
	 * @param periodo PeriodoPrograma, coge los datos de Pais, Program y periodo para crear un nuevo periodo.
	 * @param usuario Usuario que elimina.
	 */
	public void deletePeriodoPrograma(PeriodoPrograma periodo, Usuario usuario) {
		periodo.setEstadoRegistro(Constants.ESTADO_INACTIVO);
		mantenimientoDAO.updateInactivarPeriodoProgramaSSE(periodo, usuario);
	}
}
