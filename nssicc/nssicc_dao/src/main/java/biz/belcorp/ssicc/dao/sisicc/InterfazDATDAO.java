/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;


import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazDATDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */

/**
 * @author usuario
 *
 */
public interface InterfazDATDAO extends DAO {

	/**
	 * Carga la data en la tabla de datamart antes de ejecutar las interfaces
	 * @param params
	 */
	public void executeCargarTablaInterfaz(Map params);
	
	
	/**
	 * Interfaz que envia el maestro de tipo de clasificacion del Programa de Lideres
	 * 
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarTipoClasificacionPrograma(Map params);
	
	
	/**
	 * Interfaz que enva los responsables por seccin. Es el nuevo maestro de lderes 
	 * asociados con una clasificacin.
	 * 
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarResponsablesSeccion(Map params);

	/**
	 * Interfaz que envia ranking de las lderes
	 * 
	 * @param params
	 * @return
	 */
	public List getInterfazDATEnviarRankingLideres(Map params);

	
	/**
	 * Proceso de envio Tipo Clasificacion Programa
	 * @param map
	 */	
	public void executeInterfazDATEnviarTipoClasificacionPrograma(Map params);
	
	/**
	 * Proceso de envio Responsable Seccion
	 * @param map
	 */		
	public void executeInterfazDATEnviarResponsablesSeccion(Map params);
	
	/**
	 * Proceso de envio Ranking Lideres
	 * @param map
	 */	
	public void executeInterfazDATEnviarRankingLideres(Map params);

	/**
	 * Proceso de Interfaz que envia 
	 * maestro de tipos de clasificacin del Programa de Lderes
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroTipoClasificacionLideres(Map params);

	/**
	 * Proceso de Interfaz que envia a Datamart los puntajes de Concurso
	 * @param params
	 */
	public void executeInterfazDATEnviarPuntajeConcurso(Map params);

	/**
	 * Proceso de Interfaz que envia a Datamart los productos de apoyo
	 * @param params
	 */
	public void executeInterfazDATEnviarProductosApoyo(Map params);

	/**
	 * Proceso de Interfaz que envia a Datamart los productos reemplazados
	 * @param params
	 */
	public void executeInterfazDATEnviarProductosReemplazo(Map params);


	/**
	 * Proceso de Interfaz que envia a Datamart el cronograma planificado
	 * @param params
	 */
	public void executeInterfazDATEnviarCronogramaPlanificado(Map params);


	/**
	 * Proceso de Interfaz que envia a Datamart los motivos de transacciones post venta
	 * @param params
	 */
	public void executeInterfazDATEnviarMotivoCDR(Map params);


	/**
	 * Proceso de Interfaz que envia a Datamart los motivos de rechazo de pedido
	 * @param params
	 */
	public void executeInterfazDATEnviarMotivoRechazo(Map params);


	/**
	 * Proceso de Interfaz que envia a Datamart los motivos de rechazo de CDR
	 * @param params
	 */
	public void executeInterfazDATEnviarMotivoRechazoCDR(Map params);


	/**
	 * Proceso de Interfaz que envia a Datamart los productos recuperados
	 * @param params
	 */
	public void executeInterfazDATEnviarProductosRecuperacion(Map params);


	/**
	 *  Proceso de Interfaz que envia a Datamart las boletas de recojo
	 * @param params
	 */
	public void executeInterfazDATEnviarBoletasRecojo(Map params);


	/**
	 * Proceso de Interfaz que envia a Datamart los detalles de documentos post venta
	 * @param params
	 */
	public void executeInterfazDATEnviarDetalleDocuPostVenta(Map params);


	/**
	 * Proceso de Interfaz que envia a Datamart los documentos post venta
	 * @param params
	 */
	public void executeInterfazDATEnviarDocuPostVenta(Map params);


	/**
	 * Proceso de Interfaz que envia a Datamark los pedidos rechazados
	 * @param params
	 */
	public void executeInterfazDATEnviarPedidosRechazados(Map params);

	/**
	 * Proceso de Interfaz que envia a Datamark la fecha de Cierre del 
	 * periodo
	 * @param params
	 */
	public void executeInterfazDATEnviarFechaCierrePeriodo(Map params);
	
	/**
	 * Proceso de Interfaz que envia a Datamark los Productos de Reemplazo 
	 * @param params
	 */
	public void executeInterfazDATEnviarProductoReemplazo(Map params);
	/**
	 * Proceso de interfaz que envia el transaccional de logros love consultora 
	 * @param params
	 */
	public void executeInterfazDATEnviarLogrosLoveConsultora(Map params);


	/**
	 * Proceso de interfaz que envi el maestro de grupo de segmentos love
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroGrupoSegmentoLove(Map params);


	/**
	 * Proceso de interfaz que envia maestro de segmentos love
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroSegmentoLove(Map params);


	/**Proceso de interfaz enviar maestro tipo logro
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroTipoLogro(Map params);


	/**
	 * Proceso de interfaz que envia las fechas de los documetos
	 * @param params
	 */
	public void executeInterfazDATEnviarFechaDocumentos(Map params);


	/**Proceso q se encraga de enviar sociedad
	 * @param params
	 */
	public void executeInterfazDATEnviarEmpresaSiCC(Map params);


	/**
	 * Proceso que se encarga de enviar Acceso
	 * @param params
	 */
	public void executeInterfazDATEnviarAcceso(Map params);


	/**
	 * Proceso que encarga de enviar cieere de incentivos
	 * @param params
	 */
	public void executeInterfazDATEnviarFechaCierreIncentivos(Map params);


	/**Proceos que encarga de en enviar region
	 * @param params
	 */
	public void executeInterfazDATEnviarRegion(Map params);

	/**Proceos que encarga de en enviar hijas duplas
	 * @param params
	 */
	public void executeInterfazDATEnviarHijasDuplas(Map params);


	/**
	 * Proceso que encarga de enviar seccion de Asistencia compartamos
	 * @param params
	 */
	public void executeInterfazDATEnviarAsistenciaCompartamos(Map params);


	/**
	 * Proceso que se encarga de enviar la ganancia por campanha
	 * @param params
	 */
	public void executeInterfazDATEnviarGananciaCampanha(Map params);


	/**
	 * Proceso que se encarga de enviar el registro de los logros
	 * @param params
	 */
	public void executeInterfazDATEnviarRegistroLogros(Map params);


	/**
	 * Proceso que se encarga de enviar los tipo logros
	 * @param params
	 */
	public void executeInterfazDATEnviarTipoLogros(Map params);
	
	/**
	 * Proceso que encarga de enviar Maestro Programas Lideres
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroProgramaLideres(Map params);
	
	/**
	 * Proceso que encarga de enviar Nivel por Campana
	 * @param params
	 */
	public void executeInterfazDATEnviarNivelCampana(Map params);
	
	/**
	 * Proceso que encarga de enviar Nivel por Programa
	 * @param params
	 */
	public void executeInterfazDATEnviarNivelPrograma(Map params);
	
	/**
	 * Proceso que encarga de enviar Rango de Pedidos
	 * @param params
	 */
	public void executeInterfazDATEnviarRangoPedidos(Map params);
	
	/**
	 * Proceso que encarga de enviar Resultados por Campana
	 * @param params
	 */
	public void executeInterfazDATEnviarResultadoCampana(Map params);
	
	/**
	 * Proceso que encarga de enviar Resultados por Programa
	 * @param params
	 */
	public void executeInterfazDATEnviarResultadoPrograma(Map params);


	/**
	 * Proceso que encarga de enviar campanhas
	 * @param params
	 */
	public void executeInterfazDATEnviarCampanhas(Map params);
	

	/**
	 * Proceso que encarga de enviar Unidades Geograficas
	 * @param params
	 */
	public void executeInterfazDATEnviarUnidadesGeograficas(Map params);
	
		/**
	 * Proceso que encarga de enviar Unidades Geograficas en laCampanha
	 * @param params
	 */
	public void executeInterfazDATEnviarUnidadesGeograficasCampanha(Map params);
	
		/**
	 * Proceso que encarga de enviar Informacion Gerente Regionales
	 * @param params
	 */
	public void executeInterfazDATEnviarInformacionGerenteRegionales(Map params);
	
		/**
	 * Proceso que encarga de enviar Informacion Lideres
	 * @param params
	 */
	public void executeInterfazDATEnviarInformacionLideres(Map params);
	
		/**
	 * Proceso que encarga de enviar Matriz Campanha
	 * @param params
	 */
	public void executeInterfazDATEnviarMatrizCampanha(Map params);
	
	/**
	 * Proceso que encarga de enviar Secciones
	 * @param params
	 */
	public void executeInterfazDATEnviarSecciones(Map params);
	
		/**
	 * Proceso que encarga de enviar Homologacion Status Cliente
	 * @param params
	 */
	public void executeInterfazDATEnviarHomologacionStatusCliente(Map params);
	
		/**
	 * Proceso que encarga de enviar Tipo Ofertas
	 * @param params
	 */
	public void executeInterfazDATEnviarTipoOfertas(Map params);
	
		/**
	 * Proceso que encarga de enviar TablaZona
	 * @param params
	 */
	public void executeInterfazDATEnviarTablaZona(Map params);
	
		/**
	 * Proceso que encarga de enviar Status Zona
	 * @param params
	 */
	public void executeInterfazDATEnviarStatusZona(Map params);
	
		/**
	 * Proceso que encarga de enviar Zona Reales Region
	 * @param params
	 */
	public void executeInterfazDATEnviarZonaRealesRegion(Map params);


	/**
	 * Proceso que encarga de enviar Informacion gerente de zonas
	 * @param params
	 */
	public void executeInterfazDATEnviarInformacionGerenteZona(Map params);
	
	/**
	 * Proceso que encarga de enviar numero de pedidos por dia
	 * @param params
	 */
	public void executeInterfazDATEnviarNumeroPedidosDia(Map params);
	
	/**
	 * Proceso que encarga de enviar transacciones de zona
	 * @param params
	 */
	public void executeInterfazDATEnviarTransaccionesZona(Map params);


	/**
	 * Proceso que se encraga de enviar control cierre
	 * @param params
	 */
	public void executeInterfazDATEnviarControlCierre(Map params);
	
	/**
	 * Proceso que envia los Pedidos de una Campaa
	 * @param params
	 */
	public void executeInterfazDATEnviarNumeroPedidosCampana(Map params);
	
	/**
	 * Proceso que envia el status de las consultoras
	 * @param params
	 */
	public void executeInterfazDATEnviarStatusConsultora(Map params);
	
	/**
	 * Proceso que envia los tiempos de Campaa
	 * @param params
	 */
	public void executeInterfazDATEnviarTiempoCampana(Map params);

	/**
	 * Proceso que envia la fecha de Proceso
	 * @param params
	 */
	public void executeInterfazDATEnviarFechaProceso(Map params);

	/**
	 * Proceso que envia las Boletas de Despacho
	 * @param params
	 */
	public void executeInterfazDATEnviarBoletaDespacho(Map params);
	

	/**
	 * Proceso que envia las proyeccciones programadas
	 * @param params
	 */
	public void executeInterfazDATEnviarProyeProgra(Map params);
	
	
	/**
	 * Proceso que envia las Transacciones de Clientes
	 * @param params
	 */
	public void executeInterfazDATEnviarTransaccionesClientes(Map params);


	/**
	 * Proceso que envia los Canales de Ingreso de Pedido
	 * @param params
	 */
	public void executeInterfazDATEnviarCanalIngresoPedido(Map params);


	/**
	 * Proceso que envia los impuestos ICE
	 * @param params
	 */
	public void executeInterfazDATEnviarImpuestoIce(Map params);


/**
	 * Proceso que envia los Maestro De Programa De Reconocimiento
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroDeProgramaDeReconocimiento(Map params);

	
	/**
	 * Proceso que envia el nivel de programa reconocimiento 
	 * @param params
	 */
	public void executeInterfazDATEnviarNivelDeProgramaReconocimiento(Map params);


	/**
	 * Proceso que envia el nivel de programa reconocimiento puntaje
	 * @param params
	 */
	public void executeInterfazDATEnviarNivelDeProgramaReconocimientoPuntaje(Map params);
	
	
	/**
	 * Proceso que envia la consultora con reconocimiento
	 * @param params
	 */
	public void executeInterfazDATEnviarConsultoraConReconocimiento(Map params);

	/* INI JJ PER-SiCC-2012-0250 */
	/**
	 * Proceso que envia la clasificacion de las lideres
	 * @param params
	 */
	public void executeInterfazDATEnterfazDATEnviarClasificacionLider(Map params);
	/* FIN JJ PER-SiCC-2012-0250 */
	
	/**
	 * Proceso que envia los cargos de la consultora
	 * @param params
	 */
	public void executeInterfazDATEnviarCargosConsultora(Map params);
	
	/**
	 * Proceso que envia los abonos de las consultoras
	 * @param params
	 */
	public void executeInterfazDATEnviarAbonosConsultora(Map params);
	
	/**
	 * Proceso que envia las fechas de cierre
	 * @param params
	 */
	public void executeInterfazDATEnviarFechaCierreZonas(Map params);
	
	/**
	 * Proceso que envia los tipos de cargos
	 * @param params
	 */
	public void executeInterfazDATEnviarTipoCargos(Map params);
	
	/**
	 * Proceso que envia los tipos de abonos
	 * @param params
	 */
	public void executeInterfazDATEnviarTipoAbonos(Map params);
	
	/**
	 * Proceso que envia Estados Lider
	 * @param params
	 */
	public void executeInterfazDATEnviarEstadosLider(Map params);
	
	/**
	 * Proceso de Envio Maestro Tipos Baja Lider
	 * @param params
	 */
	public void executeInterfazDATEnviarTiposBajaLider(Map params);
	
	/**
	 * Proceso de Envio Maestro Clasificaciones Lider
	 * @param params
	 */
	public void executeInterfazDATEnviarClasificacionesLider(Map params);
	
	/**
	 * Proceso de Envio Maestro Programas Lderes
	 * @param params
	 */
	public void executeInterfazDATEnviarProgramasLider(Map params);
	
	/**
	 * Proceso de Envio Maestro Lderes
	 * @param params
	 */
	public void executeInterfazDATEnviarLideres(Map params);
	
	/**
	 * Proceso de Envio Responsable por Seccin
	 * @param params
	 */
	public void executeInterfazDATEnviarResponsableSeccion(Map params);
	
	/**
	 * Proceso de Envio Maestro Niveles de xito x Objetivo Pedidos
	 * @param params
	 */
	public void executeInterfazDATEnviarNivelExitoObjetivoPedido(Map params);
	
	/**
	 * Proceso de Envio Maestro Niveles de xito x Objetivo Ingresos
	 * @param params
	 */
	public void executeInterfazDATEnviarNivelExitoObjetivoIngreso(Map params);
	
	/**
	 * Proceso de Envio Resultado Concurso Lideres
	 * @param params
	 */
	public void executeInterfazDATEnviarResultadoConcursoLideres(Map params);
	
	/**
	 * Proceso de Envio Resultado Concurso por Cumplimiento Retenciones
	 * @param params
	 */
	public void executeInterfazDATEnviarResultadoCumplRetenciones(Map params);
	/**
	 * Proceso de Enviar Maestro Roles Directorio 
	 * @param params
	 */
	public void executeInterfazDATEnviarRolDirec(Map params);
	/**
	 * Proceso de Enviar Maestro Perfiles Directorio 
	 * @param params
	 */
	public void executeInterfazDATEnviarPerfilDirec(Map params);
	/**
	 * Proceso de Enviar Maestro Cargos Directorio 
	 * @param params
	 */
	public void executeInterfazDATEnviarTipoCargo(Map params);
	/**
	 * Proceso de Enviar Maestro Motivos de Licencia 
	 * @param params
	 */
	public void executeInterfazDATEnviarTipoLicen(Map params) ;
	/**
	 * Proceso de Enviar Maestro Tipos de Operacin  
	 * @param params
	 */
	public void executeInterfazDATEnviarTipoOpera(Map params);
	/**
	 * Proceso de Enviar Clientes Directorio 
	 * @param params
	 */
	public void executeInterfazDATEnviarClienDirec(Map params) ;
	/**
	 * Proceso de Enviar Operaciones Directorio 
	 * @param params
	 */
	public void executeInterfazDATEnviarOperaDirec(Map params) ;
	
	/**
	 * Proceso de Enviar Maestro Programa
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroPrograma(Map params);
	
	
	
	/**
	 * Proceso de Enviar Inicio Curso Bono Lets 
	 * @param params
	 */
	public void executeInterfazDATEnviarInicioCursoBonoLet(Map params);
	
	/**
	 * Proceso de Enviar Tipo Bono Lets 
	 * @param params
	 */
	public void executeInterfazDATEnviarTipoBonoLet(Map params);
	
	/**
	 * Proceso de Enviar Nivel Lets 
	 * @param params
	 */
	public void executeInterfazDATEnviarNivelLet(Map params);
	
	/**
	 * Proceso de Enviar Lanzamientos Estratgicos
	 * @param params
	 */
	public void executeInterfazDATEnviarLanzamientosEstrategicos(Map params);
	
	/**
	 * Proceso de Enviar Detalle Lanzamientos Estratgicos
	 * @param params
	 */
	public void executeInterfazDATEnviarDetalleLanzamientosEstrategicos(Map params);
	
	/**
	 * Proceso de Enviar Lanzamientos Estratgicos Nivel
	 * @param params
	 */
	public void executeInterfazDATEnviarLanzamientosEstrategicosNivel(Map params);
	
	/**
	 * Proceso de Enviar Resultado Lderes
	 * @param params
	 */
	public void executeInterfazDATEnviarResultadoLideres(Map params);
	
	
	/**
	 * Proceso de Enviar Tipo de Canasta
	 * @param params
	 */
	public void executeInterfazDATEnviarTipoCanasta(Map params);
	
	/**
	 * Proceso de Enviar Canasta por Nivel
	 * @param params
	 */
	public void executeInterfazDATEnviarCanastaPorNivel(Map params);
	

	/**
	 * Proceso de Enviar Detalle Canasta por Nivel
	 * @param params
	 */
	public void executeInterfazDATEnviarDetalleCanastaPorNivel(Map params);
	
	/**
	 * Proceso de Enviar Maestro Tramos
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroTramos(Map params);
	
	/**
	 * Proceso de Enviar Cobranza Tramos
	 * @param params
	 */
	public void executeInterfazDATEnviarCobranzaTramos(Map params);
	

	/**
	 * Proceso de Enviar Resultado Cobranza Tramos
	 * @param params
	 */
	public void executeInterfazDATEnviarResultadoCobranzaTramos(Map params);
	
	/**
	 * Proceso de Enviar Ciclo de Vida
	 * @param params
	 */
	public void executeInterfazDATEnviarCicloVida(Map params);
	/**
	 * Proceso de Enviar Ciclo de Vida por Nivel
	 * @param params
	 */
	public void executeInterfazDATEnviarCicloVidaPorNivel(Map params);
	
	
	/**
	 * Proceso de Enviar Resultado Pedido
	 * @param params
	 */
	public void executeInterfazDATEnviarResultadoPedido(Map params);
	
	
	/**
	 * Proceso de Enviar Maestro Lideres
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroLideres(Map params);
	
	
	/**
	 * Proceso de Enviar Maestro Clasificacion de lideres
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroClasificacionLideres(Map params);
	
	/**
	 * Proceso de Enviar Maestro seccion
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroSeccion(Map params);
	

	/**
	 * Proceso de Enviar Maestro Bajas
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroBajas(Map params);
	
	/**
	 * Proceso de Enviar Calendario Feriados
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroCalendarioFeriados(Map params);
	
	/**
	 * Proceso de Enviar Exigencia Web
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroExigenciaWeb(Map params);

	/**
	 * Proceso de Enviar Tipos de Reemplazo 
	 * @param params
	 */
	public void executeInterfazDATEnviarMaestroTipoReemplazo(Map params);
	
	/**
	 * Proceso de Enviar Archivo de Control
	 * @param params
	 */
	public void executeInterfazDATEnviarArchivoControl(Map params);
	
	/**
	 * Interfaz DAT de Ranking Nivel
	 * @param params
	 */
	public void executeInterfazDATnviarRankingNivel(Map params);
	
	/**
	 * Interfaz DAT de Descuentos Nuevas
	 * @param params
	 */
	public void executeInterfazDATEnviarDescuentosNuevas(Map params);

	/**
	 * Interfaz DAT de Consultoras Excluidas
	 * @param params
	 */
	public void executeInterfazDATEnviarConsultorasExcluidas(Map params);
	
	/**
	 * Interfaz DAT de Fechas de Entrega Exacta
	 * @param params
	 */
	public void executeInterfazDATEnviarFechasEntregaExacta(Map params);
	
	/**
	 * Interfaz DAT valida codigo de consultora
	 * @param params
	 */
	public boolean validarCodigoConsultora(Map params);
	
	/**
	 * Se recupera informacion los códigos y los ID’s del Tipo Cliente y SubTipo Cliente de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getTipoClienteSubTipoClientePorConsultora(Map criteria);
	
	
	/**
	 * Interfaz DAT valida codigo de campanha
	 * @param params
	 */
	public Integer validarCodigoCampanha(Map params);
	

	
	public int getCorrelativoDATClienClasi();
	
	public void insertDATClienClasi(Map criteria);
	
	public boolean validarExistenciaTipoClasificacion(Map params);
	
	public Map getOidsClasificacionTipoClasificacionPorParametrosInterfaz(Map params);
	
	public void deleteClasificacionClientePorOidClasificacion(Map params);
	
	public Integer getOidTipoSubTipoClientePorCodigoCliente(Map params);
	
	public Integer getOidTipoClasificacionPorParametriaInterfaz(Map params);
	
	public boolean validarExistenciaClienteClasificacion(Map params);
	
	/**
	 * 
	 * @param params
	 */
	public void executeInterfazDATEnviarEstadosProgramas(Map params);
	
	/**
	 * Proceso de Interfaz que envia a la recuperacion de cobranza
	 * @param params
	 */
	public void executeInterfazDATEnviarRecuperacionCobranza(Map params);
	
	/**
	 * Ejecuta validacion DATAMART
	 * @param params
	 */
	public String getValidacionInterfazDatamart(Map params);

	/**
	 * Validacion de cierre de campaña pendiente
	 * @param criteria
	 * @return
	 */
	public String getValidacionCierreCampanyaPendiente(Map criteria);
	
	
	/**
	 * Inerfaz DAT-177 Enviar Rango Comision
	 * @param params
	 */
	public void executeInterfazDATEnviarResultadoRangoComision(Map params) ;
	
}