package biz.belcorp.ssicc.dao.spncd.ibatis;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
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

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoCUPProgDsctoDAOiBatis.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno</a>
 * 
 */
@Repository("spncd.mantenimientoCUPProgDsctoDAO")
public class MantenimientoCUPProgDsctoDAOiBatis extends BaseDAOiBatis implements
		MantenimientoCUPProgDsctoDAO {
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getProgramasDsctoByCriteria(java.util.Map)
	 */
	public List getProgramasDsctoByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getCUPProgDesctosbyCriteria", criteria);
	}

	public List getDespachoProductosByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getDespachoProductosByCriteria", criteria);
	}

	public void executeProcesoCUPDespachoProductos(Map queryParams) {
		getSqlMapClientTemplate().update(
				"spncd.CuponesSQL.executeProcesoCUPDespachoProductos",
				queryParams);
	}

	public void executeProcesoCUPCierreFacturacion(Map queryParams) {
		getSqlMapClientTemplate().update(
				"spncd.CuponesSQL.executeProcesoCUPCierreFacturacion", queryParams);
	}

	/* INI SA PER-SiCC--2012-0467 */
	public DespachoProducto getDespachoProductosById(Map criteria) {
		// TODO Auto-generated method stub
		return (DespachoProducto) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getDespachoProductosById", criteria);
	}
	/* FIN SA PER-SiCC--2012-0467 */

	public DespachoProducto getOfertaDetalleById(Map criteria) {
		// TODO Auto-generated method stub
		return (DespachoProducto) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getOfertaDetalleById", criteria);
	}

	public void insertDespachoProductos(DespachoProducto despachoProducto,
			Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert(
				"spncd.CuponesSQL.insertDespachoProductos", despachoProducto);
	}

	public List getProgramasDescuentosbyPais(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getProgramasDescuentosbyPais", criteria);
	}

	public List getNivelbyPais(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getNivelbyPais", criteria);
	}
	
	public void desactivarDespachoProducto(DespachoProducto despachoProducto) {
		getSqlMapClientTemplate()
		.update("spncd.CuponesSQL.desactivarDespachoProducto",
			despachoProducto);
	}

	public BigDecimal getCodVentaDespachoProductoById(Map params) {
		if (log.isDebugEnabled())
			log.debug("Entering method 'getCodVentaDespachoProductoById'");
		log.debug("params=" + params);

		BigDecimal decimal = new BigDecimal(0);
		// Obtenemos los clientes nuevos a actualizar
		List list = getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getCodVentaDespachoProductoById", params);
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			decimal = (BigDecimal) iter.next();
		}

		return decimal;
	}

	public BigDecimal getDetOfertaEstrategiaCompuestaFijaById(Map params) {
		if (log.isDebugEnabled())
			log
					.debug("Entering method 'getDetOfertaEstrategiaCompuestaFijaById'");
		log.debug("params=" + params);

		BigDecimal decimal = new BigDecimal(0);
		// Obtenemos los clientes nuevos a actualizar
		List list = getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getDetOfertaEstrategiaCompuestaFijaById",
				params);
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			decimal = (BigDecimal) iter.next();
		}

		return decimal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getProgramaDsctoById(java.util.Map)
	 */
	public ProgramaCupon getProgramaDsctoById(Map criteria) {
		return (ProgramaCupon) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getCUPProgDesctosbyCriteria", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getTablaEquivalByCriteria(java.util.Map)
	 */
	public List getTablaEquivalByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getTablaEquivalByCriteria", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#insertProgramaDscto(biz.belcorp.ssicc.spncd.dao.ProgCupon,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertProgramaDscto(ProgramaCupon cupon, Usuario usuario) {
		cupon.setEstadoProg(Constants.YES);
		getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertProgramaDscto", cupon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#updateProgramaDscto(biz.belcorp.ssicc.spncd.dao.ProgCupon,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateProgramaDscto(ProgramaCupon cupon, Usuario usuario) {
		getSqlMapClientTemplate().update("spncd.CuponesSQL.updateProgramaDscto", cupon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#deleteProgramaDscto(biz.belcorp.ssicc.spncd.dao.ProgCupon)
	 */
	public void deleteProgramaDscto(ProgramaCupon cupon) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update(
				"spncd.CuponesSQL.updateIncactivoPrograma", cupon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#actualizarCuponNivel(java.util.Map)
	 */
	public void actualizarCuponNivel(Map criteria) {
		String[] cuponNoAsignados = (String[]) criteria.get("cuponesNoAsignados");
		
		if (cuponNoAsignados == null || cuponNoAsignados.length == 0)
			criteria.put("cuponesNoAsignados", new ArrayList());
		else{
			getSqlMapClientTemplate().delete("spncd.CuponesSQL.deleteCupCuponNivel", criteria);
		}
		
		List listaCuponExistentes = (getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getListaCuponNivelInsertar", criteria));
		int nivel = Integer.parseInt((String) criteria.get("nivel"));
		String nivelString = String.valueOf(nivel - 1);
		criteria.put("codPrecon", nivelString);
		
		String[] cuponAsignados = (String[]) criteria.get("cuponesAsignados");
		
		if(cuponAsignados != null)
		{
		for (int i = 0; i < cuponAsignados.length; i++) {
			
			criteria.put("codigoCupon", cuponAsignados[i]);
			String codigoCupon = cuponAsignados[i];
			boolean nuevo = true;
			for (int j = 0; j < listaCuponExistentes.size(); j++) {
				
					String codigoCuponExistente = (String) listaCuponExistentes.get(j);
				if (StringUtils.equals(codigoCupon, codigoCuponExistente))
					nuevo = false;
			}
			if (nuevo)
					getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertCupCuponNivel", criteria);
			}
		}
		
		//Actualizamos las vigencias por nivel
		String indicadorVigencia = MapUtils.getString(criteria, "indicadorVigencia", "");
		
		if(StringUtils.equals(indicadorVigencia, Constants.CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES))
		{
			Map pn = (Map)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getProgramaNivel", criteria);
			
			if(pn == null)
			{
				getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertProgramaNivel", criteria);
			}
			else
			{
				getSqlMapClientTemplate().update("spncd.CuponesSQL.updateProgramaNivel", criteria);
			}
		}
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getProgramasPeriodoByCriteria(java.util.Map)
	 */
	public List getProgramasPeriodoByCriteria(Map criteria) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getProgramasPeriodoByCriteria", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getProgramaPeriodoById(java.util.Map)
	 */
	public ProgramaPeriodo getProgramaPeriodoById(Map criteria) {
		// TODO Auto-generated method stub
		return (ProgramaPeriodo) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getProgramaPeriodoById", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getNextPeriodoByCupProgPerio(java.util.Map)
	 */
	public String getNextPeriodoByCupProgPerio(Map criteria) {
		// TODO Auto-generated method stub
		String siguientePeriodo = (String) getSqlMapClientTemplate()
				.queryForObject(
						"spncd.CuponesSQL.getNextPeriodoByCupProgPerio",
						criteria);
		String fechaFinalPeriodo = (String) getSqlMapClientTemplate()
				.queryForObject("spncd.CuponesSQL.getPeriodoFinByCupon",
						criteria);
		if (StringUtils.isEmpty(siguientePeriodo))
			siguientePeriodo = (String) getSqlMapClientTemplate()
					.queryForObject("spncd.CuponesSQL.getPeriodoInicioByCupon",
							criteria);
		/*else if (fechaFinalPeriodo.compareTo(siguientePeriodo) < 0)
			return "";*/
		return siguientePeriodo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#insertProgramaPeriodo(biz.belcorp.ssicc.spncd.model.ProgramaPeriodo,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertProgramaPeriodo(ProgramaPeriodo cupon, Usuario usuario) {
		// TODO Auto-generated method stub
		cupon.setEstadoProgramaPeriodo(Constants.ESTADO_ACTIVO);
		try {
			getSqlMapClientTemplate().insert(
					"spncd.CuponesSQL.insertProgramaPeriodo", cupon);
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		try {
			getSqlMapClientTemplate().insert(
					"spncd.CuponesSQL.insertPeriodoNivel", cupon);
		} catch (Exception e) {
			// TODO: handle exception
		}
				

		Map criteria = new HashMap();
		try {
			criteria = BeanUtils.describe(cupon);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		criteria.put("cuponesAsignados", cupon.getCuponesAsignados());
		criteria.put("cuponesNoAsignados", cupon.getCuponesNoAsignados());
		getSqlMapClientTemplate().delete(
				"spncd.CuponesSQL.deleteEquivalenciaMatrizByProgramaPeriodo",
				cupon);
		List listaCuponExistentes = (getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getListaCuponInsertar", criteria));
		String[] cuponAsignados = cupon.getCuponesAsignados();
		for (int i = 0; i < cuponAsignados.length; i++) {
			criteria.put("codigoCupon", cuponAsignados[i]);
			String codigoCupon = cuponAsignados[i];
			boolean nuevo = true;
			for (int j = 0; j < listaCuponExistentes.size(); j++) {
				String codigoCuponExistente = (String) listaCuponExistentes
						.get(j);
				if (StringUtils.equals(codigoCupon, codigoCuponExistente))
					nuevo = false;
			}
			if (nuevo){
				try {
					getSqlMapClientTemplate().insert(
							"spncd.CuponesSQL.insertCuponMatrizEquivalencia",
							criteria);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}				
		}
		updateProgramaPeriodo(cupon, usuario);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#deletePeriodoPrograma(biz.belcorp.ssicc.spncd.model.ProgramaPeriodo)
	 */
	public void deletePeriodoPrograma(ProgramaPeriodo cuponBean) {
		// TODO Auto-generated method stub
		cuponBean.setEstadoProgramaPeriodo(Constants.OK);
		getSqlMapClientTemplate().update(
				"spncd.CuponesSQL.updateProgramaPeriodo", cuponBean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#updateProgramaPeriodo(biz.belcorp.ssicc.spncd.model.ProgramaPeriodo,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateProgramaPeriodo(ProgramaPeriodo cupon, Usuario usuario) {
		Map criteria = new HashMap();
		try {
			criteria = BeanUtils.describe(cupon);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String valUnid = (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getValorCodigoNivel", criteria);
		log.debug("valUnid"+valUnid);
		if(valUnid == null)
			getSqlMapClientTemplate().insert(
					"spncd.CuponesSQL.insertPeriodoNivel", cupon);
		else{
			/*
			getSqlMapClientTemplate().update(
					"spncd.CuponesSQL.updateProgramaPeriodo", cupon);
			*/
			getSqlMapClientTemplate().update(
					"spncd.CuponesSQL.updatePeriodoNivel", cupon);
		}
			
		
		criteria.put("cuponesAsignados", cupon.getCuponesAsignados());
		criteria.put("cuponesNoAsignados", cupon.getCuponesNoAsignados());
		if (cupon != null && cupon.getCuponesNoAsignados() != null
				 && cupon.getCuponesNoAsignados().length != 0){
			System.out.println("Entro al eliminar cupones no asignados");
			getSqlMapClientTemplate()
					.delete(
							"spncd.CuponesSQL.deleteEquivalenciaMatrizByProgramaPeriodo",
							cupon);
			
		}
		List listaCuponExistentes = (getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getListaCuponInsertar", criteria));
		String[] cuponAsignados = cupon.getCuponesAsignados();
		for (int i = 0; i < cuponAsignados.length; i++) {
			criteria.put("codigoCupon", cuponAsignados[i]);
			String codigoCupon = cuponAsignados[i];
			boolean nuevo = true;
			for (int j = 0; j < listaCuponExistentes.size(); j++) {
				String codigoCuponExistente = (String) listaCuponExistentes
						.get(j);
				if (StringUtils.equals(codigoCupon, codigoCuponExistente))
					nuevo = false;
			}
			if (nuevo){
				System.out.println("Entro al eliminar cupones no asignados "+codigoCupon);
				getSqlMapClientTemplate().insert(
						"spncd.CuponesSQL.insertCuponMatrizEquivalencia",
						criteria);
				
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getEquivalenciaMatrizByCriteria(java.util.Map)
	 */
	public List getEquivalenciaMatrizByCriteria(Map criteria) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getEquivalenciaMatrizByCriteria", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getEquivalenciaMatrizById(java.util.Map)
	 */
	public EquivalenciaMatriz getEquivalenciaMatrizById(Map criteria) {
		// TODO Auto-generated method stub
		return (EquivalenciaMatriz) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getEquivalenciaMatrizById", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#updateEquivalenciaMatriz(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEquivalenciaMatriz(EquivalenciaMatriz cupon,
			Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update(
				"spncd.CuponesSQL.updateEquivalenciaMatriz", cupon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#insertEquivalenciaMatriz(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEquivalenciaMatriz(EquivalenciaMatriz cupon,
			Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert(
				"spncd.CuponesSQL.insertEquivalenciaMatriz", cupon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#deleteEquivalenciaMatriz(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public void deleteEquivalenciaMatriz(EquivalenciaMatriz cuponBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete(
				"spncd.CuponesSQL.deleteEquivalenciaMatriz", cuponBean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getNextCodigoPrograma(java.lang.String)
	 */
	public String getNextCodigoPrograma(String pais) {
		// TODO Auto-generated method stub
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais);
		return (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getNextCodigoPrograma", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getProgramaActivo(java.lang.String)
	 */
	public String getProgramaActivo(String codigo) {
		// TODO Auto-generated method stub
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigo);
		return (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getProgramaActivo", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCodigoCuponInicial(java.util.Map)
	 */
	public String getCodigoCuponInicial(Map criteria) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getCodigoCuponInicial", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCodigoCuponFinal(java.util.Map)
	 */
	public String getCodigoCuponFinal(Map criteria) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getCodigoCuponFinal", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCodigoPeriodoInicial(java.util.Map)
	 */
	public String getCodigoPeriodoInicial(Map criteria) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getCodigoPeriodoInicial", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCodigoPeriodoFinal(java.util.Map)
	 */
	public String getCodigoPeriodoFinal(Map criteria) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getCodigoPeriodoFinal", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getPeriodoDefaultByPrograma(java.util.Map)
	 */
	public String getPeriodoDefaultByPrograma(Map criteria) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getPeriodoDefaultByPrograma", criteria);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getValidaPrioridad(java.util.Map)
	 */
	public String validaPrioridad(EquivalenciaMatriz cupon) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.validaPrioridad", cupon);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#updateValorUnidadNivel(java.util.Map)
	 */
	public void updateValorUnidadNivel(Map criteria){
		getSqlMapClientTemplate().update(
				"spncd.CuponesSQL.updateValorUnidadNivel", criteria);
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getValorNivel(java.lang.String)
	 */
	public String getValorNivel(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getValorNivel", criteria);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCupNoAsignadosUtilizados(java.util.Map)
	 */
	public List getCupNoAsignadosUtilizados(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getCupNoAsignadosUtilizados", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getProductosMatrizByCriteria(java.util.Map)
	 */
	public List getProductosMatrizByCriteria(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getProductosMatrizByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCabecConsultorasSuscripcion(java.util.Map)
	 */
	public SuscripcionCabeceraConsultora getCabecConsultorasSuscripcion(Map criteria){
		return (SuscripcionCabeceraConsultora) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getCabecConsultorasSuscripcion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getNivelesSuscripcion(java.util.Map)
	 */
	public List getNivelesSuscripcion(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getNivelesSuscripcion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getNivelesSuscripcionEdit(java.util.Map)
	 */
	public List getNivelesSuscripcionEdit(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getNivelesSuscripcionEdit", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#saveSuscripcionConsultoras(biz.belcorp.ssicc.spncd.model.SuscripcionCabeceraConsultora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void saveSuscripcionConsultoras(
			SuscripcionCabeceraConsultora consultora, Usuario usuario){
		 getSqlMapClientTemplate().insert(
				"spncd.CuponesSQL.insertSuscripcionConsultoras", consultora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#deleteSuscripcionConsultorasDetalle(biz.belcorp.ssicc.spncd.model.SuscripcionCabeceraConsultora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteSuscripcionConsultorasDetalle(
			SuscripcionCabeceraConsultora consultora){
		getSqlMapClientTemplate().delete(
				"spncd.CuponesSQL.deleteSuscripcionConsultorasDetalle", consultora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#saveSuscripcionConsultorasNivel(biz.belcorp.ssicc.spncd.model.SuscripcionNivelConsultora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void saveSuscripcionConsultorasNivel(
			SuscripcionNivelConsultora nivel, Usuario usuario){
		 getSqlMapClientTemplate().insert(
					"spncd.CuponesSQL.insertSuscripcionConsultorasNivel", nivel);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#saveSuscripcionConsultorasDetalle(biz.belcorp.ssicc.spncd.model.SuscripcionDetalleConsultora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void saveSuscripcionConsultorasDetalle(
			SuscripcionDetalleConsultora detalle, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spncd.CuponesSQL.insertSuscripcionConsultorasDetalle", detalle);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#updateSuscripcionConsultorasDetalle(biz.belcorp.ssicc.spncd.model.SuscripcionDetalleConsultora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateSuscripcionConsultorasDetalle(
			SuscripcionDetalleConsultora detalle, Usuario usuario){
		 getSqlMapClientTemplate().update(
					"spncd.CuponesSQL.updateSuscripcionConsultorasDetalle", detalle);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#deleteSuscripcionConsultorasDetalle(biz.belcorp.ssicc.spncd.model.SuscripcionDetalleConsultora)
	 */
	public void deleteSuscripcionDetalle(
			SuscripcionDetalleConsultora detalle){
			getSqlMapClientTemplate().update(
					"spncd.CuponesSQL.deleteSuscripcionDetalle", detalle);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getDetalleSuscripcionConsultora(biz.belcorp.ssicc.spncd.model.SuscripcionDetalleConsultora)
	 */
	public SuscripcionDetalleConsultora getDetalleSuscripcionConsultora(SuscripcionDetalleConsultora detalle){
		return (SuscripcionDetalleConsultora) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getDetalleSuscripcionConsultora", detalle);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCuponesNoHomologados(java.util.Map)
	 */
	public List getCuponesNoHomologados(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getCuponesNoHomologados", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#cierreCampanha(java.util.Map)
	 */
	public void executeCierreCampanha(Map map){
		getSqlMapClientTemplate().update("spncd.CuponesSQL.executeCierreCampanha",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#guardarCuponLoveDefault(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public void guardarCuponLoveDefault(EquivalenciaMatriz cupon){
		try {
			getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertLoveCuponDefault", cupon);
		} catch (Exception e) {
			getSqlMapClientTemplate().update("spncd.CuponesSQL.updateLoveCuponDefault", cupon);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#guardarEquivalenciaCuponLove(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public void guardarEquivalenciaCuponLove(EquivalenciaMatriz cupon){
		try {
			getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertLoveEquivalenciaMatriz", cupon);
		} catch (Exception e) {
			getSqlMapClientTemplate().update("spncd.CuponesSQL.updateLoveEquivalenciaMatriz", cupon);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#deleteCuponLoveDefault(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public void deleteCuponLoveDefault(EquivalenciaMatriz cupon){
		getSqlMapClientTemplate().delete("spncd.CuponesSQL.deleteLoveCuponDefault", cupon);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#deleteLoveEquivalenciaMatriz(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public void deleteLoveEquivalenciaMatriz(EquivalenciaMatriz cupon){
		getSqlMapClientTemplate().delete("spncd.CuponesSQL.deleteLoveEquivalenciaMatriz", cupon);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getExcepciones(java.util.Map)
	 */
	public List getExcepciones(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getExcepciones", criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#insertExcepciones(java.util.Map)
	 */
	public void insertExcepciones(Map criteria){
		getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertExcepciones", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#deleteExcepciones(java.util.Map)
	 */
	public void deleteExcepciones(Map criteria){
		getSqlMapClientTemplate().delete("spncd.CuponesSQL.deleteExcepciones", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCuponesPeriodo(java.util.Map)
	 */
	public String getCuponesPeriodo(Map criteria){
		List l = new ArrayList(); 
		l = getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getCuponesPeriodo", criteria);
		if (l.size()!=0){
			String strCupones = "";
			for (int i = 0; i < l.size(); i++) {
				strCupones += (l.get(i)+" ");
			}
			return strCupones;
		}
		else
			return  "";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCodigoVentaPeriodo(java.util.Map)
	 */
	public String getCodigoVentaPeriodo(Map criteria){		 
		return getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getCodigoVentaPeriodo", criteria).toString();		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getEquivalenciaMatrizByIdLove(java.util.Map)
	 */
	public EquivalenciaMatriz getEquivalenciaMatrizByIdLove(Map criteria) {
		return (EquivalenciaMatriz) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getEquivalenciaMatrizByIdLove", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getProdutoPrimerPedidoList(java.util.Map)
	 */
	public List getProdutoPrimerPedidoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getProdutoPrimerPedidoList", criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#updateProdutoPrimerPedido(java.util.Map)
	 */
	public void updateProdutoPrimerPedido(Map criteria) {
		getSqlMapClientTemplate().update("spncd.CuponesSQL.updateProdutoPrimerPedido", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getIndicadorDigitable(java.util.Map)
	 */
	public List getIndicadorDigitable(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getIndicadorDigitable", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#insertProductoPrimerPedido(java.util.Map)
	 */
	public void insertProductoPrimerPedido(Map criteria){
		getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertProductoPrimerPedido", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getValorNivelPremiosElectivos(java.util.Map)
	 */
	public String getValorNivelPremiosElectivos(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getValorNivelPremiosElectivos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#verificaCruce(java.util.Map)
	 */
	public String verificaCruce(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.verificaCruce", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getPeriodoInicioByCupon(java.util.Map)
	 */
	public String getPeriodoInicioByCupon(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getPeriodoInicioByCupon", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getPeriodoFinByCupon(java.util.Map)
	 */
	public String getPeriodoFinByCupon(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getPeriodoFinByCupon", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getloadRegionesDisponibles(java.util.Map)
	 */
	public List getloadRegionesDisponibles(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getloadRegionesDisponibles", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getZonasDisponiblesRegion(java.util.Map)
	 */
	public List getZonasDisponiblesRegion(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getZonasDisponiblesRegion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#devuelveDescripcionRegion(java.util.Map)
	 */
	public String devuelveDescripcionRegion(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.devuelveDescripcionRegion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#devuelveDescripcionZona(java.util.Map)
	 */
	public String devuelveDescripcionZona(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.devuelveDescripcionZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#insertUnidadAdministrativa(biz.belcorp.ssicc.spncd.model.UnidadAdministrativaCupon)
	 */
	public void insertUnidadAdministrativa(UnidadAdministrativaCupon unidadAdministrativa) {
		getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertUnidadAdministrativa", unidadAdministrativa);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#insertDescuentos(biz.belcorp.ssicc.spncd.model.DescuentoCupon)
	 */
	public void insertDescuentos(DescuentoCupon descuentoCupon) {
		getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertDescuentos", descuentoCupon);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getNextOidUAS()
	 */
	public String getNextOidUAS(){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getNextOidUAS");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getListUnidadesAdministrativas(java.util.Map)
	 */
	public List getListUnidadesAdministrativas(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getListUnidadesAdministrativas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getListDescuentos(java.util.Map)
	 */
	public List getListDescuentos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getListDescuentos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#verificaConsultoraPrograma(java.util.Map)
	 */
	public String verificaConsultoraPrograma(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.verificaConsultoraPrograma", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#updateProgramaDscto2(java.util.Map)
	 */
	public void updateProgramaDscto2(Map criteria) {
		getSqlMapClientTemplate().update("spncd.CuponesSQL.updateProgramaDscto2", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#updateUnidadAdministrativa(java.util.Map)
	 */
	public void updateUnidadAdministrativa(Map criteria) {
		getSqlMapClientTemplate().update("spncd.CuponesSQL.updateUnidadAdministrativa", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getPeriodosPrograma(java.util.Map)
	 */
	public String getPeriodosPrograma(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getPeriodosPrograma", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getPeriodoSiguiente(java.util.Map)
	 */
	public String getPeriodoSiguiente(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getPeriodoSiguiente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#validarMatrizFacturacion(java.util.Map)
	 */
	public String validarMatrizFacturacion(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.validarMatrizFacturacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getDetalleProductoByIdOferta(java.util.Map)
	 */
	public List getDetalleProductoByIdOferta(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getDetalleProductoByIdOferta", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#insertaRegaloxPedido(java.util.Map)
	 */
	public void insertaRegaloxPedido(Map criteria){
		getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertaRegaloxPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getNumeroNiveles(java.util.Map)
	 */
	public String getNumeroNiveles(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getNumeroNiveles", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getNivelesPermitidos(java.util.Map)
	 */
	public List getNivelesPermitidos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getNivelesPermitidos", criteria);
	}
	
	/* INI JR PER-SiCC-2012-0362 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#executeEntregaRxP(java.util.Map)
	 */
	public void executeEntregaRxP(Map criteria) {
        getSqlMapClientTemplate().update("spncd.CuponesSQL.executeEntregaRxP", criteria);
	}
	/* FIN JR PER-SiCC-2012-0362 */
	
	/* INI SA PER-SiCC--2012-0467 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#verificaProgramaPrimerPedido(java.util.Map)
	 */
	public boolean verificaProgramaPrimerPedido(Map criteria) {
		String contador = (String) getSqlMapClientTemplate().queryForObject(
											"spncd.CuponesSQL.getProgramaPrimerPedido", criteria);
		
		if(contador.equals("0"))
			return false;
		else
			return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#existenCuponesConIndicadorKit(java.util.Map)
	 */
	public boolean existenCuponesConIndicadorKit(Map criteria) {
		String contador = (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getCuponConIndicadorKit", criteria);
		
		if(contador.equals("0"))
			return false;
		else
			return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#existenDespachosConIndicadorKit(java.util.Map)
	 */
	public boolean existenDespachosConIndicadorKit(Map criteria) {
		String contador = (String) getSqlMapClientTemplate().queryForObject(
				"spncd.CuponesSQL.getDespachoConIndicadorKit", criteria);
		
		if(contador.equals("0"))
			return false;
		else
			return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#verificaCruceProgramaUA(java.util.Map)
	 */
	public void verificaCruceProgramaUA(Map criteria){
		getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.verificaCruceProgramaUA", criteria);
	}
	/* FIN SA PER-SiCC--2012-0467 */
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getPeriodo(java.util.Map)
	 */
	public String getPeriodo(Map criteria) {
		String periodo =(String)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getPeriodo", criteria);
		return periodo;
	}

	public void insertPeriodo(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spncd.CuponesSQL.insertPeriodo", criteria);
	}

	public String getParametroPrograma(String parametro) {
		String programa =(String)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getParametroPrograma", parametro);
		return programa;
	}

	public int validarPeriodoCampahnaFin(Map criteria) {
		Integer periodo =(Integer)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.validarPeriodoCampahnaFin", criteria);
		return periodo.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getRegaloPorPedido(java.util.Map)
	 */
	public String getRegaloPorPedido(Map criteria) {
		String RegaloPorPedido=(String)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getRegaloPorPedido", criteria);
		return RegaloPorPedido;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#eliminarDespachoProducto(biz.belcorp.ssicc.spncd.model.DespachoProducto)
	 */
	public void eliminarDespachoProducto(DespachoProducto despachoProducto) {
		getSqlMapClientTemplate()
			.delete("spncd.CuponesSQL.eliminarDespachoProducto",
				despachoProducto);
	}

	public int validaTipoOferta(Map criteria) {
		Integer tipoOferta=(Integer)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.validaTipoOferta", criteria);
		return tipoOferta.intValue();
	}
	
/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#validaTipoOfertaDuplicado(java.util.Map)
	 */
	public int validaTipoOfertaDuplicado(Map criteria) {
		Integer tipoOfertaDup =(Integer)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.validaTipoOfertaDuplicado", criteria);
		return tipoOfertaDup.intValue();
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCantZonasxRegion(java.util.Map)
	 */
	public int getCantZonasxRegion(Map criteria) {
		Integer tipoOferta=(Integer)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getCantZonasxRegion", criteria);;
		return tipoOferta.intValue();
		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getConsultaSOAProgramaNuevas(java.util.Map)
	 */
	public List getConsultaSOAProgramaNuevas(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getConsultaSOAProgramaNuevas", criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getConsultaSOAIncentivos(java.util.Map)
	 */
	public List getConsultaSOAIncentivos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getConsultaSOAIncentivos", criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getDevuelveOidConcurso(java.util.Map)
	 */
	public Long getDevuelveOidConcurso(Map criteria){
		return (Long)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getDevuelveOidConcurso", criteria); 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#existenDespachoConIndicadorKitSegundoPedido(java.util.Map)
	 */
	public boolean existenDespachoConIndicadorKitSegundoPedido(Map criteria) {
		String resultado = (String)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.existenDespachoConIndicadorKitSegundoPedido", criteria);
		
		if (StringUtils.equals(resultado, "0")) {
			return false;
		}else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#validaProductosIndicadorKit2(java.util.Map)
	 */
	public Integer validaProductosKitSegundoPedido(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.validaProductosKitSegundoPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getTipoPedidoCUP()
	 */
	public List getTipoPedidoCUP() {
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getTipoPedidoCUP", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getVigenciaCuponPorNivel(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getVigenciaCuponPorNivel(String codigoPais, String codigoPrograma, String codigoNivel) {
		String result = "";
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPrograma", codigoPrograma);
		criteria.put("nivel", codigoNivel);
		
		Map pn = (Map)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getProgramaNivel", criteria);
		
		result = MapUtils.getString(pn, "vigencia", "");
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getExisteDescuento(java.util.Map)
	 */
	public String getExisteDescuento(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getExisteDescuento", criteria);
	}
	
	public String getExisteDescuentoTotal(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getExisteDescuentoTotal", criteria);
	}
	
	public void updateDescuentos(Map criteria) {
		getSqlMapClientTemplate().update("spncd.CuponesSQL.updateDescuentos", criteria);
	}
	
	public void deleteDescuentos(Map criteria) {
		getSqlMapClientTemplate().update("spncd.CuponesSQL.deleteDescuentos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getCuponLoveDefault(biz.belcorp.ssicc.spncd.model.EquivalenciaMatriz)
	 */
	public Map getCuponLoveDefault(EquivalenciaMatriz cupon) {
		return (Map)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getCuponLoveDefault", cupon);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPProgDsctoDAO#getIndicadorCuponReutilizable(java.util.Map)
	 */
	public String getIndicadorCuponReutilizable(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getIndicadorCuponReutilizable", criteria);
	}
}