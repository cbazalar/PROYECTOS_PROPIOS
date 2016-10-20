CREATE OR REPLACE PACKAGE LID_PKG_PROCE_LIDER is

 /* Declaracion de Variables */
 ln_sqlcode NUMBER(10);
 ls_sqlerrm VARCHAR2(150);
 W_FILAS NUMBER := 1000;
 ----------------
 TYPE t_codigo IS TABLE OF lid_tipo_asign_punta.cod_tipo_asign_punta%TYPE;
 TYPE t_descripcion IS TABLE OF lid_tipo_asign_punta.des_tipo_asign_punta%TYPE;

 /* Declaracion de constantes */
 TIPO_PROCESO_RECOMENDADA VARCHAR2(2) := 'RD';
 TIPO_PROCESO_AMBOS VARCHAR2(2) := 'A';

 /* Procesos de Programas Incentivos */
 PROCESO_GP4 CONSTANT VARCHAR2(3) := 'G';
 PROCESO_GP3 CONSTANT VARCHAR2(3) := 'GP3';
 PROCESO_CIERRE_ZONA CONSTANT VARCHAR2(3) := 'Z';
 PROCESO_CIERRE_REGION CONSTANT VARCHAR2(3) := 'R';
 PROCESO_CIERRE_CAMPANA CONSTANT VARCHAR2(3) := 'P';

/***************************************************************************
Descripcion : Procedimiemto que genera final de zonas por periido

Fecha Creacion : 16/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_GENE_ACTI_FINA_ZONA_PER(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodPeriodo VARCHAR2,
 psCodVarVenta VARCHAR2

);
/***************************************************************************
Descripcion : Recupera el numero actividad final zona

Fecha Creacion : 16/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_NUM_ACT_FIN_ZONA(psCodPais VARCHAR2, psCodMarca VARCHAR2, psCodZona VARCHAR2, psCodPeriodo VARCHAR2)
RETURN NUMBER;

/***************************************************************************
Descripcion : Procedimiemto que genera informe de Lideres al Cierre del
 periodo

Fecha Creacion : 22/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_GENE_INFO_LIDE_CIER_PER(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodPeriodo VARCHAR2
);

/***************************************************************************
Descripcion : Recupera el ODI_PARA_GRAL, si no existe devuelve 0

Fecha Creacion : 22/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_CONCU_PARAM_GENER(psCodPais VARCHAR2, psCodMarca VARCHAR2, psPeriodo VARCHAR2 )
RETURN NUMBER;

/***************************************************************************
Descripcion : Recupera el NUM_CONC, si no existe devuelve 0

Fecha Creacion : 22/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_NUM_CONC_PARA_GEN(psCodPais VARCHAR2, psCodMarca VARCHAR2, psPeriodo VARCHAR2 )
RETURN VARCHAR2;


/***************************************************************************
Descripcion : Recupera 1 si existen registro de solicitud de concurso
 0 si no existe
Fecha Creacion : 22/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_SOLC_CONCU_PUNTA(psClieOidClie VARCHAR2, psOidParaGral VARCHAR2)
RETURN NUMBER;


/***************************************************************************
Descripcion : Recupera el SOCA_OID_SOLI_CABE si no existe devuelve 0
Fecha Creacion : 23/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_OID_PED_SOLIC_CAB(psClieOidClie VARCHAR2, psCodMarca VARCHAR2, psPeriodo VARCHAR2)
RETURN NUMBER;

/***************************************************************************
Descripcion : Recupera el FEC_FACT si no existe devuelve 0
Fecha Creacion : 23/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_FEC_PED_SOLIC_CAB(psClieOidClie VARCHAR2, psCodMarca VARCHAR2, psPeriodo VARCHAR2)
RETURN DATE;

/***************************************************************************
Descripcion : Recupera 1 si existen registro en INC_CANDI_GANAD del concurso
 0 si no existe
Fecha Creacion : 22/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_INC_CANDI_GANAD(psClieOidClie VARCHAR2, psOidParaGral VARCHAR2)
RETURN NUMBER;

/***************************************************************************
Descripcion : Procedimiemto que Evalua Recomendaciones de 2 pedidos en
 Facturación
Fecha Creacion : 22/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_EVAL_RECO_2PED_FACT(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2
);

/***************************************************************************
Descripcion : Recupera 1 si existen si su orden de compra se estan
 facturando a la fecha 0 si no existe

Fecha Creacion : 24/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_ORDE_COMPRA(psCodCliente VARCHAR2, psCodPais VARCHAR2, psCodPeriodo VARCHAR2)
RETURN NUMBER;

/***************************************************************************
Descripcion : Recupera 1 si existen si su orden de compra se estan
 facturando a la fecha. 0 si no existe

Fecha Creacion : 24/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_ORDE_COMPRA_FACT(psCodCliente VARCHAR2, psCodPais VARCHAR2, psCodPeriodo VARCHAR2, psFechaProceso VARCHAR2)
RETURN NUMBER;

/***************************************************************************
Descripcion : Recupera 1 si existen si su orden de compra se estan
 facturando en toda la campaña. 0 si no existe

Fecha Creacion : 24/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_ORDE_COMPRA_FACT1(psCodCliente VARCHAR2, psCodPais VARCHAR2, psCodPeriodo VARCHAR2)
RETURN NUMBER;



/***********************************************************************************
Descripcion : Recupera el factor multiplicador de LID_FACTO_PUNTA_PROGR_LIDER
 2 PEDIDOS
 0 si no existe

Fecha Creacion : 25/01/2008
Autor : Leonardo Lizana
************************************************************************************/
FUNCTION LID_FN_OBTIE_FAC_MULTI_2PED(psCodPeriodo VARCHAR2, psCodConcurso VARCHAR2)
RETURN NUMBER;

/***********************************************************************************
Descripcion : Recupera el factor multiplicador de LID_FACTO_PUNTA_PROGR_LIDER
 3 PEDIDOS
 0 si no existe

Fecha Creacion : 25/01/2008
Autor : Leonardo Lizana
************************************************************************************/
FUNCTION LID_FN_OBTIE_FAC_MULTI_3PED(psCodPeriodo VARCHAR2, psCodConcurso VARCHAR2)
RETURN NUMBER;


/***************************************************************************
Descripcion : Procedimiemto Evaluar Recomendaciones de 2 pedidos al
 Cierre del periodo

Fecha Creacion : 24/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_EVAL_RECO_2PED_CIER_PER(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiemto Evaluar Recomendaciones de 3 pedidos al
 Cierre del periodo

Fecha Creacion : 24/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_EVAL_RECO_3PED_CIER_PER(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiemto Evaluar Recomendaciones de 3 pedidos en
 Facturación

Fecha Creacion : 28/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_EVAL_RECO_3PED_FACT(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiemto Generar Número de Pedidos por Periodo (Old)

Fecha Creacion : 29/01/2008 2:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_GENE_NUM_PEDI_SECC_PEDI(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodRegion VARCHAR2
);


/**************************************************************************
Descripcion : Devuelve 1 si existe un SECCION NUMERO DE PEDIDO
 si no existe 0

Fecha Creacion : 29/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_SECC_NUME_PEDI(psCodPais VARCHAR2, psCodMarca VARCHAR2,codZona VARCHAR2, codSeccion VARCHAR2, psPeriodo VARCHAR2 )
RETURN NUMBER;

/***************************************************************************
Descripcion : Procedimiemto Evaluar Número de Pedidos de la Sección
 al Cierre de Región (Old)

Fecha Creacion : 30/01/2008 6:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_EVA_NUM_PED_SEC_CIE_REG(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodRegion VARCHAR2,
 psFechaProceso VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiemto Generar Mensaje de Puntaje Obtenido

Fecha Creacion : 29/01/2008 2:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_GENE_MENSA_PUNTA_OBTEN(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2
);

/***************************************************************************
Descripcion : Devuelve Id de Cliente con atributo :
 FALSE - Retorna NO DATA FOUND
 TRUE - Retorna -1
Fecha Creacion : 07/03/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION GEN_FN_DEVUELVE_ID_CLIENTE(psCodCliente VARCHAR2)
RETURN NUMBER;

/***************************************************************************
Descripcion : Procedimiemto Generar Número de Pedidos de Sección
 por Periodo (II)

Fecha Creacion : 13/03/2009
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_GENER_NUMER_PEDID_SECCI(
 psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodigoRegion VARCHAR2);

/***************************************************************************
Descripcion : Procedimiemto Evaluar Número de Pedidos de la Sección
 al Cierre de Región (II)

Fecha Creacion : 17/03/2009
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_EVALU_NUMER_PEDID_CIERE(
 psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psFechaProceso VARCHAR2);

/***************************************************************************
Descripcion : Procedimiemto Evaluar Número de Pedidos de la Sección
 al Cierre de Periodo

Fecha Creacion : 23/03/20089
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_EVALU_NUMER_PEDID_CIEPE(
 psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2);

/***************************************************************************
Descripcion : Procedimiemto Evaluar Ingresos Efectivos de la Sección
 al Cierre de Región

Fecha Creacion : 27/03/20089
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_EVALU_INGRE_EFECT_CIERE(
 psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psFechaProceso VARCHAR2,
 psTipoEvaluacion VARCHAR2);

/***************************************************************************
Descripcion : Procedimiemto Evaluar Ingresos Efectivos de la Sección
 al Cierre de Periodo

Fecha Creacion : 01/04/20089
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_EVALU_INGRE_EFECT_CIEPE(
 psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2,
 psTipoEvaluacion VARCHAR2);

/***************************************************************************
Descripcion : Recupera el Tipo de Proceso de Recomendacion activa

Fecha Creacion : 22/04/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_TIPO_PROCE_ACTIV(psCodigoPais VARCHAR2) RETURN VARCHAR2;

/***************************************************************************
Descripcion : Obtenemos la campaña de asignacion de la unidad administrativa
 y respecto a su fecha desde

Fecha Creacion : 14/07/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_CAMPA_ASIGN(psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psUnidadAdministrativa VARCHAR2)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Realizamos las diferentes validaciones para si se puede
 efectuar la asignacion de la lider a una respectiva seccion

Fecha Creacion : 15/07/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_VALID_ASIGN_LIDER_SECCI(psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoCliente VARCHAR2,
 psIndicadorReingreso VARCHAR2,
 psIndicadorNoValidaUnicoLider VARCHAR2,
 lnNumeroActivasFinalesZona NUMBER,
 lnPromedioActFinalesSeccion NUMBER)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Recupera el Indicador de constancia

Fecha Creacion : 23/07/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_INDIC_CONST(psCodigoPais VARCHAR2)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Recupera la informacion del mensaje a enviar a la Lider

Fecha Creacion : 17/09/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_INFOR_MENSA(psCodigoCliente VARCHAR2)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Recupera el Oid Concurso

Fecha Creacion : 24/08/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_OID_CONCU(psCodigoPais VARCHAR2,
 psNumeroConcurso VARCHAR2)
RETURN NUMBER;

/***************************************************************************
Descripcion : Obtiene el numero de Promedios finales por Seccion

Fecha Creacion : 26/08/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_PROME_SECCI(pnOidZona NUMBER,
 pnOidSeccion NUMBER,
 pnOidUltimoPeriodo1 NUMBER,
 pnOidUltimoPeriodo2 NUMBER,
 pnOidUltimoPeriodo3 NUMBER)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Obtiene el numero de Promedios finales por Zona

Fecha Creacion : 26/08/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_PROME_ZONA(pnOidZona NUMBER,
 pnOidPeriodo NUMBER)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Recupera 1 si existen si su orden de compra se ha
 facturado para un determinado peridoo
 Recupera 0 si no existe

Fecha Creacion : 14/10/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_PASO_PEDID(psCodigoCliente VARCHAR2,
 psCodigoPais VARCHAR2,
 psCodigoPeriodo VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion : Genera las solicitudes de premiación del Programa Love
Fecha Creacion : 23/10/2009
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Apaza

***************************************************************************/
PROCEDURE LID_PR_PREMI_CONCU_BOLSA_PREMI
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2);

/**************************************************************************
Descripcion : Desvinculacion Automatica de Lideres
Fecha Creacion : 22/12/2009
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Apaza

***************************************************************************/
PROCEDURE LID_PR_DESVI_AUTOM_LIDER
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2);

/**************************************************************************
Descripcion : Registra una incidencia producida de alguna validacion que no
 paso una determinada solicitud para cualquier de los procesos de Incentivos
Fecha Creacion : 19/12/2009
Parametros Entrada:
 psNumeroConcurso : Numero de Concurso
 psCodigoCliente : Codigo de Cliente
 psCodigoPeriodo : Codigo de periodo
 psCodigoProceso : Codigo de Proceso
 psMotivoIndicencia : Motivo de la Incidencia
 pnOidSolicitud : Oid Solicitud
 pnNumeroPremio : Numero de Premio
 psCodigoVenta : Codigo de Venta
 psDescripcionPremio : Descripcion Premio
 pnNumeroUnidades : Numero de Unidades del Premio

Autor : Sergio Apaza
Fecha Actualiza: 27/05/2011
Autor Actualiza: Carlos Diaz Valverde
***************************************************************************/
PROCEDURE LID_PR_REGIS_INCID_INDEP(psNumeroConcurso VARCHAR2,
 psCodigoCliente VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psCodigoProceso VARCHAR2,
 psMotivoIndicencia VARCHAR2,
 pnOidSolicitud NUMBER,
 pnOidConcurso NUMBER);

/**************************************************************************
Descripcion : exisitiendo la matriz de facturacion inserta los productos en la canasta
 si exsiten aquellos en pre ofert , si no crea registros en pre_ofer cabcera y detall
 para ñluego insertrlos en la entidad canasa de productos
Fecha Creacion : 22/01/2010
Parametros Entrada:
 psCodigoPais : Código Pais
 psCodigoPeriodo : Codigo de periodo
 psCodigoSap : Codigo de Producto
 psCodigoOferta : Código Oferta
 psNumUnidades : Numero Unidades
 psPrecioContable : Precio Contable
 psIndicadorActivo : Indicador Activo
 psUsuario : Usuario
 psMensajeError : Mensaje Error

Autor : Sergio Buchelli

***************************************************************************/
PROCEDURE LID_PR_GENER_PRODU_CANAS(psCodigoPais VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psCodigoSap VARCHAR2,
 psCodigoOferta VARCHAR2,
 psCodigoVenta VARCHAR2,
 psNumUnidades VARCHAR2,
 psPrecioContable VARCHAR2,
 psIndicadorActivo VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2);

/**************************************************************************
Descripcion : Determina Ganadoras de Canasta de Líderes
Fecha Creacion : 25/01/2010
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Apaza

***************************************************************************/
PROCEDURE LID_PR_DETER_GANAD_CANAS_LIDER
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2);

/**************************************************************************
Descripcion : Atencion de la Canasta de Líderes
Fecha Creacion : 27/01/2010
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4, GP3: GP3
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Apaza

***************************************************************************/
PROCEDURE LID_PR_ATENC_CANAS_LIDER
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2);

/**************************************************************************
Descripcion : Evalua Ranking de lideres en la Campanha
Fecha Creacion : 24/03/2010
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4, GP3: GP3
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Buchelli

***************************************************************************/
PROCEDURE LID_PR_EVALU_RANKI_LIDER_CAMPA
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2);

/**************************************************************************
Descripcion : Evalua Ranking de lideren en el Periodo
Fecha Creacion : 24/03/2010
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4, GP3: GP3
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Buchelli

***************************************************************************/
PROCEDURE LID_PR_EVALU_RANKI_LIDER_PERIO
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2);

/***************************************************************************
Descripcion : Recupera la campnha de ingreso de la lider a una UA
Fecha Creacion : 25/03/2010
Autor : Sergio Buchelli
Parametros Entrada:
 psCodigoClie : Codigo de cliente
 psUnidadAdministrativa : unidad administrativa

***************************************************************************/
FUNCTION LID_FN_DEVUE_CAMPA_INGRE_LIDER(psCodigoClie VARCHAR2, psUnidadAdministrativa VARCHAR2)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Procedimiemto Evaluar Recomendaciones de 2 pedidos al
              Cierre de la region
Fecha Creacion : 09/09/2011
Autor : Jose Luis Rodriguez
***************************************************************************/

PROCEDURE LID_PR_EVALU_RECOM_2PEDI_REGIO(
  psCodPais         VARCHAR2,
  psCodMarca        VARCHAR2,
  psPeriodoProceso  VARCHAR2,
  psFechaProceso    VARCHAR2,
  psCodRegion       VARCHAR2
);

/***************************************************************************
Descripcion: Valida los pedidos estimados en las zonas
Fecha Creacion : 12/04/2011
Autor : Jesse James Rios Franco
Parametros Entrada:
 psCodigoPais: Codigo de Pais
 psCodigoPeriodo: Codigo de Periodo
 psCodigoMarca: Codigo de Marca
 psCodigoRetorno: Codigo de Retorno que sera evaluado en java
***************************************************************************/
/*PROCEDURE LID_PR_VALID_PEDID_ESTIM(psCodigoPais    IN  VARCHAR2,
                                   psCodigoPeriodo IN  VARCHAR2,
                                   psCodigoMarca   IN  VARCHAR2,
                                   psCodigoRetorno OUT VARCHAR2,
                                   psCodigoZona    OUT VARCHAR2);*/

end LID_PKG_PROCE_LIDER;
/
CREATE OR REPLACE PACKAGE BODY LID_PKG_PROCE_LIDER is

/***************************************************************************
Descripcion : Procedimiemto que genera Actividad Final de Zonas por Periodo

Fecha Creacion : 16/01/2008
Autor : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE LID_PR_GENE_ACTI_FINA_ZONA_PER(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodPeriodo VARCHAR2,
 psCodVarVenta VARCHAR2


)IS

CURSOR c_cursor (
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodPeriodo VARCHAR2
 ) IS
 SELECT T.PAIS_COD_PAIS
 , T.COD_MARC
 , T.COD_PERI
 , T.COD_ZONA
 , T.NUM_PEDI
 , T.NUM_ACFI
 FROM INT_SAB_VENTA_PREVI_ZONA T
 WHERE T.pais_cod_pais=psCodPais
 AND T.cod_marc = psCodMarca
 AND T.cod_peri= psCodPeriodo
 AND T.Num_Pedi<>0;

lbValorIncremento number(15,2);
lbPorcentajeActividad number(15,2);
lbPorcentajeActividadFinal number(15,2);
lbDataFound number;
lsNumPedidoEstimado number;
lsNumActivasFinalesEsti number;
lsValIncre number;

BEGIN

SELECT T.VAL_INCRE INTO lbValorIncremento
FROM LID_INCRE_VARIA_VENTA T
WHERE T.COD_PAIS = psCodPais
 AND T.COD_MARC=psCodMarca
 AND T.COD_PERI =psCodPeriodo
 AND T.COD_VARI_VENT=psCodVarVenta
 AND T.TIIN_COD_TIPO_INCR = 'P';--define que la variable venta es de tipo porcentaje

FOR fila IN c_cursor(psCodPais,psCodMarca,psCodPeriodo) LOOP
 lbPorcentajeActividad:=(fila.num_pedi*100)/fila.num_acfi;
 lbPorcentajeActividadFinal:=(lbPorcentajeActividad+lbValorIncremento);

 lbDataFound:=LID_FN_OBTIE_NUM_ACT_FIN_ZONA(psCodPais , psCodMarca, fila.cod_zona, psCodPeriodo);
 IF(lbDataFound<>0) THEN
 SELECT T.NUM_PEDI_ESTI
 , T.NUM_ACTI_FINA_ESTI
 , T.VAL_INCR

 INTO lsNumPedidoEstimado, lsNumActivasFinalesEsti, lsValIncre
 FROM LID_ACTIV_FINAL_ZONA T
 WHERE T.COD_PAIS=psCodPais
 AND T.COD_MARC=psCodMarca
 AND T.COD_ZONA=fila.cod_zona
 AND T.COD_PERI=psCodPeriodo;

 IF(lsNumPedidoEstimado<>fila.num_pedi OR lsNumActivasFinalesEsti<>fila.num_acfi OR lsValIncre<>lbValorIncremento)THEN

 UPDATE LID_ACTIV_FINAL_ZONA T
 SET T.NUM_PEDI_ESTI = fila.num_pedi
 ,T.NUM_ACTI_FINA_ESTI = fila.num_acfi
 ,T.VAL_PORC_ACTI = lbPorcentajeActividad
 ,T.VAL_INCR = lbValorIncremento
 ,T.VAL_PORC_ACTI_FINA = lbPorcentajeActividadFinal
 ,T.FEC_ULTI_ACTU = SYSDATE
 WHERE T.COD_PAIS = psCodPais
 AND T.COD_MARC = psCodMarca
 AND T.COD_PERI = psCodPeriodo
 AND T.COD_ZONA = fila.cod_zona;
 END IF;

 ELSE

 INSERT INTO LID_ACTIV_FINAL_ZONA(
 COD_PAIS
 ,COD_MARC
 ,COD_PERI
 ,COD_ZONA
 ,NUM_PEDI_ESTI
 ,NUM_ACTI_FINA_ESTI
 ,VAL_PORC_ACTI
 ,VAL_INCR
 ,VAL_PORC_ACTI_FINA
 ,FEC_ULTI_ACTU)
 VALUES(
 psCodPais
 ,psCodMarca
 ,psCodPeriodo
 ,fila.cod_zona
 ,fila.num_pedi
 ,fila.num_acfi
 ,lbPorcentajeActividad
 ,lbValorIncremento
 ,lbPorcentajeActividadFinal
 ,sysdate
 ) ;
 END IF;

END LOOP;



EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_GENE_ACTI_FINA_ZONA_PER: '||ls_sqlerrm);
END LID_PR_GENE_ACTI_FINA_ZONA_PER;


/***************************************************************************
Descripcion : Recupera 1 si existen registro de Actividad final Zona
 0 si no existe
Fecha Creacion : 16/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_NUM_ACT_FIN_ZONA(psCodPais VARCHAR2, psCodMarca VARCHAR2, psCodZona VARCHAR2, psCodPeriodo VARCHAR2)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN
 ls_cantidad :=0;

 SELECT MIN(1) INTO ls_cantidad
 FROM LID_ACTIV_FINAL_ZONA T
 WHERE T.COD_PAIS=psCodPais
 AND T.COD_MARC=psCodMarca
 AND T.COD_ZONA=psCodZona
 AND T.COD_PERI=psCodPeriodo;
 IF ls_cantidad = 1 THEN
 RETURN ls_cantidad;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_NUM_ACT_FIN_ZONA: '||ls_sqlerrm);
 RETURN '';
END LID_FN_OBTIE_NUM_ACT_FIN_ZONA;



/***************************************************************************
Descripcion : Procedimiemto que genera informe de Lideres al Cierre del
 periodo

Fecha Creacion : 22/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_GENE_INFO_LIDE_CIER_PER(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodPeriodo VARCHAR2
)IS
 CURSOR c_ZON_SECCI IS
 SELECT ZS.*,ZZ.COD_ZONA, ZR.COD_REGI, SRV.COD_SUBG_VENT
 FROM ZON_SECCI ZS
 , ZON_ZONA ZZ
 , ZON_REGIO ZR
 , ZON_SUB_GEREN_VENTA SRV
 WHERE ZS.IND_ACTI ='1'
 AND ZS.IND_BORR = '0'
 AND ZS.CLIE_OID_CLIE IS NOT NULL
 AND ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
 AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
 AND ZR.ZSGV_OID_SUBG_VENT = SRV.OID_SUBG_VENT;

 /*CURSOR c_INC_CLIEN_RECDO (psCodPeriodo VARCHAR2, psCodMarca VARCHAR2, psfilaOidCliente VARCHAR2) IS
 SELECT CT.CLIE_OID_CLIE,
 CD.CLIE_OID_CLIE,
 CD.PERD_OID_PERI,
 MIN(CD.OID_CLIE_REDO)
 FROM INC_CLIEN_RECDO CD
 , INC_CLIEN_RECTE CT
 WHERE CD.PERD_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'))
 AND CD.CLR3_OID_CLIE_RETE=CT.OID_CLIE_RETE
 AND CT.CLIE_OID_CLIE = psfilaOidCliente
 GROUP BY CT.CLIE_OID_CLIE, CD.CLIE_OID_CLIE, CD.PERD_OID_PERI;*/

 CURSOR c_INC_CLIEN_RECDO (pnOidPeriodo NUMBER, pnfilaOidCliente NUMBER) IS
 SELECT vin.CLIE_OID_CLIE_VNTE,
       vin.CLIE_OID_CLIE_VNDO,
       pri.PERD_OID_PERI,
       NULL
  FROM MAE_CLIEN_VINCU vin, MAE_TIPO_VINCU tip, MAE_CLIEN_PRIME_CONTA pri
 WHERE vin.CLIE_OID_CLIE_VNTE = pnfilaOidCliente
   AND vin.TIVC_OID_TIPO_VINC = tip.OID_TIPO_VINC
   AND tip.COD_TIPO_VINC = '03'
   AND vin.CLIE_OID_CLIE_VNDO = pri.CLIE_OID_CLIE
   AND pri.Perd_Oid_Peri = pnOidPeriodo;

 TYPE recomendacionesLideresRec IS RECORD(
 oidClienteRecomendante NUMBER(12)
 , oidClienteRecomendada NUMBER(12)
 , oidPeriodoRecomendacion NUMBER(12)
 , oidClienteRecomendacion NUMBER(12));

 TYPE recomendacionesLideresRecTab IS TABLE OF recomendacionesLideresRec;
 recomendacionesLideresRecord recomendacionesLideresRecTab;
 ls_oid_para_gral NUMBER;
 ls_num_conc VARCHAR2(6);
 W_FILAS NUMBER := 1000 ;
 lnIdPais NUMBER;
 lnIdMarca NUMBER;
 lnIdCanal NUMBER;
 lnIdPeriodo NUMBER;
 lsCodCliente  MAE_CLIEN.COD_CLIE%TYPE;
BEGIN
 ls_oid_para_gral := LID_FN_OBTIE_CONCU_PARAM_GENER(psCodPais,psCodMarca,psCodPeriodo);
 ls_num_conc := LID_FN_OBTIE_NUM_CONC_PARA_GEN(psCodPais,psCodMarca,psCodPeriodo);
 lnIdMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
 lnIdPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo,lnIdMarca,lnIdCanal);

 dbms_output.put_line('Parametros iniciales: '||ls_oid_para_gral||' - '||ls_num_conc);
 IF(ls_oid_para_gral<>0) THEN
 FOR fila IN c_ZON_SECCI LOOP
 INSERT INTO LID_LIDER_PERIO
 VALUES(
 psCodPais
 ,psCodMarca
 ,psCodPeriodo
 ,GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(fila.clie_oid_clie)
 ,fila.cod_secc
 ,fila.cod_zona
 ,fila.cod_regi
 ,fila.cod_subg_vent
 );
 END LOOP;

 FOR fila IN c_ZON_SECCI LOOP

 -- Verificamos si si existe al menos una solicitud de la lider grabada
 dbms_output.put_line('Verificamos si si existe al menos una solicitud de la lider grabada .'||fila.clie_oid_clie||' - '||ls_oid_para_gral);
 IF(LID_FN_OBTIE_SOLC_CONCU_PUNTA(fila.clie_oid_clie,ls_oid_para_gral)=0)THEN
 IF(LID_FN_OBTIE_OID_PED_SOLIC_CAB(fila.clie_oid_clie,psCodMarca,psCodPeriodo)<>0) THEN
 dbms_output.put_line('INSERTANDO...');
 INSERT INTO INC_SOLIC_CONCU_PUNTA(
 OID_SOLI_CONC_PUNT
 ,NUM_PUNT
 ,VAL_PUNT_BONI
 ,VAL_PUNT_FALT_NANU
 ,IND_ANUL
 ,COPA_OID_PARA_GRAL
 ,SOCA_OID_SOLI_CABE
 ,FEC_DOCU
 ,PERD_OID_PERI
 ,CLIE_OID_CLIE
 ,IMP_MONT
 ,CLIE_OID_CLIE_GERE
 ,NUM_UNID
 )
 VALUES(
 INC_SOCP_SEQ.NEXTVAL
 ,'0'
 ,'0'
 ,'0'
 ,'0'
 ,ls_oid_para_gral
 ,LID_FN_OBTIE_OID_PED_SOLIC_CAB(fila.clie_oid_clie,psCodMarca,psCodPeriodo)
 ,LID_FN_OBTIE_FEC_PED_SOLIC_CAB(fila.clie_oid_clie,psCodMarca,psCodPeriodo)
 ,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo,lnIdMarca,lnIdCanal)
 ,fila.clie_oid_clie
 ,'0'
 ,null
 ,'0'
 );
 END IF;
 END IF;
 -- Verificamos si la lider ya se encuentra registrada en INC_CANDI_GANAD
 dbms_output.put_line(' Verificamos si la lider ya se encuentra registrada en INC_CANDI_GANAD...'||fila.clie_oid_clie||' - '||ls_oid_para_gral);
 IF(LID_FN_OBTIE_INC_CANDI_GANAD(fila.clie_oid_clie,ls_oid_para_gral)=0) THEN
 INSERT INTO INC_CANDI_GANAD(
 OID_CAND_GANA
 ,IND_META_SUPE
 ,VAL_REQU_PREM_SUPE
 ,PERD_OID_PERI
 ,COPA_OID_PARA_GRAL
 ,BINC_OID_BASE_INCU
 ,PERD_OID_PERI_EVAL
 ,CLIE_OID_CLIE
 ,FEC_ULTI_ACTU
 ,NUM_PERI_EVAL
 )
 VALUES(
 INC_CAGA_SEQ.NEXTVAL
 ,'0'
 ,'0'
 ,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo,lnIdMarca,lnIdCanal)
 ,ls_oid_para_gral
 ,NULL
 ,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo,lnIdMarca,lnIdCanal)
 ,fila.clie_oid_clie
 ,SYSDATE
 ,'1'
 );
 END IF;

 OPEN c_INC_CLIEN_RECDO (lnIdPeriodo,fila.clie_oid_clie);
 LOOP
 FETCH c_INC_CLIEN_RECDO BULK COLLECT INTO recomendacionesLideresRecord LIMIT W_FILAS;


 dbms_output.put_line('Insertar las recomendaciones de cada lider vigentes '||recomendacionesLideresRecord.COUNT ||' ---------- '||fila.clie_oid_clie);
 IF recomendacionesLideresRecord.COUNT > 0 THEN
 FOR i IN recomendacionesLideresRecord.FIRST .. recomendacionesLideresRecord.LAST LOOP
   lsCodCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(recomendacionesLideresRecord(i).oidClienteRecomendada);

   IF(LID_FN_PASO_PEDID(lsCodCliente, psCodPais, psCodPeriodo)=1) THEN
     INSERT INTO LID_RECOM_LIDER
     VALUES (
     GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(fila.clie_oid_clie)
     ,ls_num_conc
     ,'01'
     ,recomendacionesLideresRecord(i).oidClienteRecomendacion
     ,GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(recomendacionesLideresRecord(i).oidClienteRecomendante)
     ,lsCodCliente
     ,GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(recomendacionesLideresRecord(i).oidPeriodoRecomendacion)
     ,PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, 1)
     ,null
     ,null
     ,null
     ,psCodPais
     ,psCodMarca
     ,'0'
     );

     INSERT INTO LID_RECOM_LIDER
     VALUES (
     GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(fila.clie_oid_clie)
     ,ls_num_conc
     ,'02'
     ,recomendacionesLideresRecord(i).oidClienteRecomendacion
     ,GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(recomendacionesLideresRecord(i).oidClienteRecomendante)
     ,lsCodCliente
     ,GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(recomendacionesLideresRecord(i).oidPeriodoRecomendacion)
     ,PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, 2)
     ,null
     ,null
     ,null
     ,psCodPais
     ,psCodMarca
     ,'0'
     );
   END IF;

 END LOOP;
 END IF;
 EXIT WHEN c_INC_CLIEN_RECDO%NOTFOUND;
 END LOOP;
 CLOSE c_INC_CLIEN_RECDO;

 END LOOP;
 END IF;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_GENE_INFO_LIDE_CIER_PER: '||ls_sqlerrm);
END LID_PR_GENE_INFO_LIDE_CIER_PER;



/***************************************************************************
Descripcion : Recupera el ODI_PARA_GRAL, si no existe devuelve 0

Fecha Creacion : 22/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_CONCU_PARAM_GENER(psCodPais VARCHAR2, psCodMarca VARCHAR2, psPeriodo VARCHAR2 )
RETURN NUMBER IS
ls_oid_para_gral NUMBER;
BEGIN

SELECT CPG.OID_PARA_GRAL INTO ls_oid_para_gral
FROM INC_CONCU_PARAM_GENER CPG
WHERE CPG.PAIS_OID_PAIS = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodPais)
 AND CPG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca)
 AND CPG.VAL_OBSE = 'PROGRAMA-LIDERES'
 AND CPG.IND_ACTI ='1'
 AND CPG.PERD_OID_PERI_HAST>= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodo,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'))
 AND CPG.PERD_OID_PERI_DESD<= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodo,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));
 RETURN ls_oid_para_gral;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_CONCU_PARAM_GENER: '||ls_sqlerrm);
 RETURN '';
END LID_FN_OBTIE_CONCU_PARAM_GENER;

/***************************************************************************
Descripcion : Recupera el NUM_CONC, si no existe devuelve 0

Fecha Creacion : 22/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_NUM_CONC_PARA_GEN(psCodPais VARCHAR2, psCodMarca VARCHAR2, psPeriodo VARCHAR2 )
RETURN VARCHAR2 IS
ls_num_conc VARCHAR2(6);
BEGIN

SELECT CPG.NUM_CONC INTO ls_num_conc
FROM INC_CONCU_PARAM_GENER CPG
WHERE CPG.PAIS_OID_PAIS = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodPais)
 AND CPG.MARC_OID_MARC = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca)
 AND CPG.VAL_OBSE = 'PROGRAMA-LIDERES'
 AND CPG.IND_ACTI ='1'
 AND CPG.PERD_OID_PERI_HAST>= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodo,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'))
 AND CPG.PERD_OID_PERI_DESD<= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodo,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));
 RETURN ls_num_conc;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN '';
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_NUM_CONC_PARA_GEN: '||ls_sqlerrm);
 RETURN '';
END LID_FN_OBTIE_NUM_CONC_PARA_GEN;

/***************************************************************************
Descripcion : Recupera 1 si existen registro de solicitud de concurso
 0 si no existe
Fecha Creacion : 22/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_SOLC_CONCU_PUNTA(psClieOidClie VARCHAR2, psOidParaGral VARCHAR2)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN
 ls_cantidad :=0;
 SELECT MIN(1) INTO ls_cantidad
 FROM INC_SOLIC_CONCU_PUNTA SCP
 WHERE SCP.CLIE_OID_CLIE = psClieOidClie
 AND SCP.COPA_OID_PARA_GRAL = psOidParaGral;
 IF ls_cantidad = 1 THEN
 RETURN ls_cantidad;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_SOLC_CONCU_PUNTA: '||ls_sqlerrm);
 RETURN '';
 END LID_FN_OBTIE_SOLC_CONCU_PUNTA;

/***************************************************************************
Descripcion : Recupera el SOCA_OID_SOLI_CABE si no existe devuelve 0
Fecha Creacion : 23/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_OID_PED_SOLIC_CAB(psClieOidClie VARCHAR2, psCodMarca VARCHAR2, psPeriodo VARCHAR2)

RETURN NUMBER IS

lb_odi_soli_cabe NUMBER;
BEGIN
 lb_odi_soli_cabe:=0;
 SELECT MAX(A.OID_SOLI_CABE) INTO lb_odi_soli_cabe
 FROM PED_SOLIC_CABEC A
 , PED_TIPO_SOLIC_PAIS B
 , PED_TIPO_SOLIC C
 , PED_SOLIC_CABEC D
 WHERE A.IND_OC = 1
 AND A.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
 AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
 AND C.COD_TIPO_SOLI = 'SOC' -- Tipo de solicitud Orden de Compra
 AND A.SOCA_OID_SOLI_CABE = D.OID_SOLI_CABE
 AND D.ESSO_OID_ESTA_SOLI != 4 -- No esta anulado
 AND A.CLIE_OID_CLIE = psClieOidClie
 AND A.PERD_OID_PERI=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodo,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));

 IF lb_odi_soli_cabe <>0 THEN
 RETURN lb_odi_soli_cabe;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_OID_PED_SOLIC_CAB: '||ls_sqlerrm);
 RETURN '';

END LID_FN_OBTIE_OID_PED_SOLIC_CAB;

/***************************************************************************
Descripcion : Recupera el FEC_FACT si no existe devuelve 0
Fecha Creacion : 23/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_FEC_PED_SOLIC_CAB(psClieOidClie VARCHAR2, psCodMarca VARCHAR2, psPeriodo VARCHAR2)

RETURN DATE IS

lb_fec_fact DATE;
lb_locate NUMBER;
BEGIN
 SELECT MIN(1) INTO lb_locate
 FROM PED_SOLIC_CABEC A
 , PED_TIPO_SOLIC_PAIS B
 , PED_TIPO_SOLIC C
 , PED_SOLIC_CABEC D
 WHERE A.IND_OC = 1
 AND A.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
 AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
 AND C.COD_TIPO_SOLI = 'SOC' -- Tipo de solicitud Orden de Compra
 AND A.SOCA_OID_SOLI_CABE = D.OID_SOLI_CABE
 AND D.ESSO_OID_ESTA_SOLI != 4 -- No esta anulado
 AND A.CLIE_OID_CLIE = psClieOidClie
 AND A.PERD_OID_PERI=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodo,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));

 IF lb_locate=1 THEN
 SELECT MAX(A.FEC_FACT) INTO lb_fec_fact
 FROM PED_SOLIC_CABEC A
 , PED_TIPO_SOLIC_PAIS B
 , PED_TIPO_SOLIC C
 , PED_SOLIC_CABEC D
 WHERE A.IND_OC = 1
 AND A.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
 AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
 AND C.COD_TIPO_SOLI = 'SOC' -- Tipo de solicitud Orden de Compra
 AND A.SOCA_OID_SOLI_CABE = D.OID_SOLI_CABE
 AND D.ESSO_OID_ESTA_SOLI != 4 -- No esta anulado
 AND A.CLIE_OID_CLIE = psClieOidClie
 AND A.PERD_OID_PERI=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodo,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));

 RETURN lb_fec_fact;
 ELSE
 RETURN '';
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN '';
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_FEC_PED_SOLIC_CAB: '||ls_sqlerrm);
 RETURN '';

END LID_FN_OBTIE_FEC_PED_SOLIC_CAB;


/***************************************************************************
Descripcion : Recupera 1 si existen registro en INC_CANDI_GANAD del concurso
 0 si no existe
Fecha Creacion : 22/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_INC_CANDI_GANAD(psClieOidClie VARCHAR2, psOidParaGral VARCHAR2)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN
 ls_cantidad :=0;
 SELECT min(1) INTO ls_cantidad
 FROM INC_CANDI_GANAD CG
 WHERE CG.CLIE_OID_CLIE = psClieOidClie
 AND CG.COPA_OID_PARA_GRAL = psOidParaGral;

 IF ls_cantidad = 1 THEN
 RETURN ls_cantidad;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_INC_CANDI_GANAD: '||ls_sqlerrm);
 RETURN '';
END LID_FN_OBTIE_INC_CANDI_GANAD;

/***************************************************************************
Descripcion : Procedimiemto evaluar Recomendaciones de 2 pedidos en
 Facturación

Fecha Creacion : 24/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_EVAL_RECO_2PED_FACT(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2
)IS

 CURSOR c_RECO_LIDE (ps_CodPais VARCHAR2, ps_CodMarca VARCHAR2, ps_PeriodoProceso VARCHAR2)
 IS
 SELECT RL.COD_CONC, RL.COD_CLIE_LIDE, RL.COD_CLIE_RETE, RL.COD_CLIE_REDA, RL.COD_PERI_EVAL, RL.COD_TIPO, RL.COD_PERI_REON
 FROM LID_RECOM_LIDER RL
 WHERE RL.COD_PAIS = psCodPais
 AND RL.COD_MARC = psCodMarca
 AND RL.COD_TIPO ='01'
 AND (RL.INT_EFEC IS NULL OR RL.INT_EFEC=0)
 AND RL.COD_PERI_EVAL = psPeriodoProceso;

 CURSOR c_RECO_LIDE_EFEC
 IS
 SELECT lid_pkg_proce_lider.gen_fn_devuelve_id_cliente(t.cod_clie_lide) AS oid_cliente,
 cpg.oid_para_gral,
 SUM(t.val_punt) sum_puntaje
 FROM lid_recom_lider_tempo t,
 inc_concu_param_gener cpg
 WHERE cpg.num_conc = t.cod_conc
 GROUP BY t.cod_clie_lide,
 cpg.oid_para_gral;


lb_codPeriodoAnterior VARCHAR2(12);
ln_facMult NUMBER;
lb_efectiva BOOLEAN;
--ls_oid_para_gral NUMBER;
ls_oid_periodo NUMBER;
lnIdMarca NUMBER;
lnIdCanal NUMBER;
lnIdPais NUMBER;

lsTipoProcesoRecomendacion LID_PARAM_PROCE_RECOM.TIP_PROC_RECO%TYPE;

BEGIN

 lsTipoProcesoRecomendacion := LID_FN_OBTIE_TIPO_PROCE_ACTIV(psCodPais);

 FOR fila IN c_RECO_LIDE(psCodPais,psCodMarca,psPeriodoProceso) LOOP

 lb_efectiva:=false;

 -- Si la orden de compra de la lider esta en facturacion
 IF(LID_FN_OBTIE_ORDE_COMPRA_FACT(fila.cod_clie_lide,psCodPais,psPeriodoProceso,psFechaProceso)=1) THEN

 --Se Evalua a la Recomendante y a la Recomendada
 IF( lsTipoProcesoRecomendacion = TIPO_PROCESO_AMBOS) THEN

 -- si la recomndada y recomendante pasaron pedido
 IF(LID_FN_OBTIE_ORDE_COMPRA_FACT1(fila.Cod_Clie_Reda,psCodPais,psPeriodoProceso)=1) AND (LID_FN_OBTIE_ORDE_COMPRA_FACT1(fila.Cod_Clie_Rete,psCodPais,psPeriodoProceso)=1) THEN


 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -1);

 -- si la recomndada y recomendante pasaron pedido en el periodo anterior
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,lb_codPeriodoAnterior)=1) AND (LID_FN_PASO_PEDID(fila.Cod_Clie_Rete,psCodPais,lb_codPeriodoAnterior)=1) THEN
 ln_facMult := LID_FN_OBTIE_FAC_MULTI_2PED(fila.COD_PERI_EVAL,fila.cod_conc);
 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='1'
 ,A.VAL_PUNT = ln_facMult
 ,A.FEC_EVAL=SYSDATE
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;
 lb_efectiva:=true;

 INSERT INTO LID_RECOM_LIDER_TEMPO
 VALUES(
 fila.cod_clie_lide
 ,fila.cod_conc
 ,fila.cod_tipo
 ,null
 ,fila.cod_clie_rete
 ,fila.cod_clie_reda
 ,fila.cod_peri_reon
 ,fila.cod_peri_eval
 ,'1'
 ,ln_facMult
 ,SYSDATE
 ,psCodPais
 ,psCodMarca
 );


 END IF;
 END IF;

 ELSIF ( lsTipoProcesoRecomendacion = TIPO_PROCESO_RECOMENDADA) THEN

 -- si la recomendada paso pedido
 IF(LID_FN_OBTIE_ORDE_COMPRA_FACT1(fila.Cod_Clie_Reda,psCodPais,psPeriodoProceso)=1) THEN

 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -1);

 -- si la recomndada paso pedido en el periodo anterior
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,lb_codPeriodoAnterior)=1) THEN
 ln_facMult := LID_FN_OBTIE_FAC_MULTI_2PED(fila.COD_PERI_EVAL,fila.cod_conc);

 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='1'
 ,A.VAL_PUNT = ln_facMult
 ,A.FEC_EVAL=SYSDATE
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;

 lb_efectiva:=true;

 INSERT INTO LID_RECOM_LIDER_TEMPO
 VALUES(
 fila.cod_clie_lide
 ,fila.cod_conc
 ,fila.cod_tipo
 ,null
 ,fila.cod_clie_rete
 ,fila.cod_clie_reda
 ,fila.cod_peri_reon
 ,fila.cod_peri_eval
 ,'1'
 ,ln_facMult
 ,SYSDATE
 ,psCodPais
 ,psCodMarca);

 END IF;
 END IF;

 END IF;

 IF(NOT lb_efectiva)THEN
 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='0'
 ,A.FEC_EVAL=SYSDATE
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;
 END IF;

 END IF;

 END LOOP;
 -- ls_oid_para_gral := LID_FN_OBTIE_CONCU_PARAM_GENER(psCodPais,psCodMarca,psPeriodoProceso);
 ls_oid_periodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));

 FOR fila IN c_RECO_LIDE_EFEC LOOP
 IF(fila.sum_puntaje) > 0 THEN
 INSERT INTO INC_CUENT_CORRI_PUNTO
 VALUES(
 INC_CUCP_SEQ.NEXTVAL
 ,INC_CUCP_SEQ.NEXTVAL
 ,fila.sum_puntaje
 ,'0'
 ,TO_DATE(psFechaProceso,'DD/MM/YYYY')
 ,fila.OID_PARA_GRAL
 ,fila.oid_cliente
 ,ls_oid_periodo
 ,'1'
 ,SYSDATE
 ,'Abono por Recomendación Efectiva(2P)',
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL
 );
 END IF;
 END LOOP;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVAL_RECO_2PED_FACT: '||ls_sqlerrm);

END LID_PR_EVAL_RECO_2PED_FACT;



/***************************************************************************
Descripcion : Recupera 1 si existen si su orden de compra se estan
 facturando a la fecha 0 si no existe

Fecha Creacion : 24/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_ORDE_COMPRA(psCodCliente VARCHAR2, psCodPais VARCHAR2, psCodPeriodo VARCHAR2)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN

 SELECT 1
 INTO ls_cantidad
 FROM ped_histo_solic_conso_cabec a
 WHERE a.sta_proc = 'A'
 AND a.ind_anul = '0'
 AND a.ind_proc_gp2 = '1'
 AND a.ind_ocs_proc = '1'
 AND a.cod_pais = pscodpais
 AND a.cod_peri = pscodperiodo
 AND a.cod_clie = pscodcliente
 AND rownum = 1;

 IF ls_cantidad =1 THEN
 RETURN 1;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_ORDE_COMPRA: '||ls_sqlerrm);
 RETURN '';

END LID_FN_OBTIE_ORDE_COMPRA;

/***************************************************************************
Descripcion : Recupera 1 si existen si su orden de compra se estan
 facturando a la fecha. 0 si no existe

Fecha Creacion : 24/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_ORDE_COMPRA_FACT(psCodCliente VARCHAR2, psCodPais VARCHAR2, psCodPeriodo VARCHAR2, psFechaProceso VARCHAR2)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN


SELECT 1
 INTO ls_cantidad
 FROM int_solic_conso_cabec a
 WHERE a.sta_proc = 'A'
 AND a.ind_anul = '0'
 AND a.ind_proc_gp2 = '1'
 AND a.ind_ocs_proc = '1'
 AND a.cod_pais = pscodpais
 AND a.cod_peri = pscodperiodo
 AND a.cod_clie = pscodcliente
 AND rownum = 1
 AND a.fec_prog_fact = to_date(psfechaproceso, 'DD/MM/YYYY');

 IF ls_cantidad =1 THEN
 RETURN 1;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN

 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_ORDE_COMPRA_FACT: '||ls_sqlerrm);
 RETURN '';

END LID_FN_OBTIE_ORDE_COMPRA_FACT;

/***************************************************************************
Descripcion : Recupera 1 si existen si su orden de compra se estan
 facturando en toda la campaña. 0 si no existe

Fecha Creacion : 24/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_ORDE_COMPRA_FACT1(psCodCliente VARCHAR2, psCodPais VARCHAR2, psCodPeriodo VARCHAR2)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN


 SELECT 1
 INTO ls_cantidad
 FROM int_solic_conso_cabec a
 WHERE a.sta_proc = 'A'
 AND a.ind_anul = '0'
 AND a.ind_proc_gp2 = '1'
 AND a.ind_ocs_proc = '1'
 AND a.cod_pais = pscodpais
 AND a.cod_peri = pscodperiodo
 AND a.cod_clie = pscodcliente
 AND rownum = 1;


 IF ls_cantidad =1 THEN
 RETURN 1;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_ORDE_COMPRA_FACT1: '||ls_sqlerrm);
 RETURN '';

END LID_FN_OBTIE_ORDE_COMPRA_FACT1;


/***********************************************************************************
Descripcion : Recupera el factor multiplicador de LID_FACTO_PUNTA_PROGR_LIDER
 0 si no existe

Fecha Creacion : 24/01/2008
Autor : Leonardo Lizana
************************************************************************************/
FUNCTION LID_FN_OBTIE_FAC_MULTI_2PED(psCodPeriodo VARCHAR2, psCodConcurso VARCHAR2)
RETURN NUMBER IS
ln_factorMultiplicador NUMBER;
lnOidConcursoHasta INC_CONCU_PARAM_GENER.PERD_OID_PERI_HAST%TYPE;
lsPeriodoHastaConcurso SEG_PERIO_CORPO.COD_PERI%TYPE;
lsCodPeriodo SEG_PERIO_CORPO.COD_PERI%TYPE;
BEGIN
 --OBTENIENDO EL OID HASTA DEL CONCURSO
	 SELECT A.PERD_OID_PERI_HAST INTO lnOidConcursoHasta
	 FROM INC_CONCU_PARAM_GENER A
	 WHERE A.NUM_CONC = psCodConcurso;

	 --OBTENIENDO EL PERIODO HASTA DEL CONCURSO
	 SELECT A.COD_PERI INTO lsPeriodoHastaConcurso
		 FROM SEG_PERIO_CORPO A,
		 CRA_PERIO B,
		 SEG_CANAL C,
		 SEG_MARCA D
		 WHERE A.OID_PERI = B.PERI_OID_PERI
		 AND B.OID_PERI=lnOidConcursoHasta
		 AND B.CANA_OID_CANA = C.OID_CANA
		 AND B.MARC_OID_MARC = D.OID_MARC
		 AND C.COD_CANA = 'VD'
		 AND D.COD_MARC = 'T';

		 IF(psCodPeriodo > lsPeriodoHastaConcurso)THEN
		 lsCodPeriodo:= lsPeriodoHastaConcurso;
		 ELSE
		 lsCodPeriodo := psCodPeriodo;
		 END IF;

 ln_factorMultiplicador:=0;
 SELECT T.VAL_FAC_MULT INTO ln_factorMultiplicador
 FROM LID_FACTO_PUNTA_PROGR_LIDER T
 WHERE T.COD_CONC =psCodConcurso
 AND T.TIAP_COD_TIPO_ASIGN_PUNTA ='01'
 AND T.COD_PERI=lsCodPeriodo;

 IF ln_factorMultiplicador<>0 THEN
 RETURN ln_factorMultiplicador;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_FAC_MULTI_2PED: '||ls_sqlerrm);
 RETURN '';


END LID_FN_OBTIE_FAC_MULTI_2PED;


/***********************************************************************************
Descripcion : Recupera el factor multiplicador de LID_FACTO_PUNTA_PROGR_LIDER
 para 3 pedidos
 0 si no existe

Fecha Creacion : 24/01/2008
Autor : Leonardo Lizana
************************************************************************************/
FUNCTION LID_FN_OBTIE_FAC_MULTI_3PED(psCodPeriodo VARCHAR2, psCodConcurso VARCHAR2)
RETURN NUMBER IS
ln_factorMultiplicador NUMBER;
lnOidConcursoHasta INC_CONCU_PARAM_GENER.PERD_OID_PERI_HAST%TYPE;
lsPeriodoHastaConcurso SEG_PERIO_CORPO.COD_PERI%TYPE;
lsCodPeriodo SEG_PERIO_CORPO.COD_PERI%TYPE;
BEGIN

 --OBTENIENDO EL OID HASTA DEL CONCURSO
	 SELECT A.PERD_OID_PERI_HAST INTO lnOidConcursoHasta
	 FROM INC_CONCU_PARAM_GENER A
	 WHERE A.NUM_CONC = psCodConcurso;

	 --OBTENIENDO EL PERIODO HASTA DEL CONCURSO
	 SELECT A.COD_PERI INTO lsPeriodoHastaConcurso
		 FROM SEG_PERIO_CORPO A,
		 CRA_PERIO B,
		 SEG_CANAL C,
		 SEG_MARCA D
		 WHERE A.OID_PERI = B.PERI_OID_PERI
		 AND B.OID_PERI=lnOidConcursoHasta
		 AND B.CANA_OID_CANA = C.OID_CANA
		 AND B.MARC_OID_MARC = D.OID_MARC
		 AND C.COD_CANA = 'VD'
		 AND D.COD_MARC = 'T';

		 IF(psCodPeriodo > lsPeriodoHastaConcurso)THEN
		 lsCodPeriodo:= lsPeriodoHastaConcurso;
		 ELSE
		 lsCodPeriodo := psCodPeriodo;
		 END IF;


 ln_factorMultiplicador:=0;
 SELECT T.VAL_FAC_MULT INTO ln_factorMultiplicador
 FROM LID_FACTO_PUNTA_PROGR_LIDER T
 WHERE T.COD_CONC =psCodConcurso
 AND T.TIAP_COD_TIPO_ASIGN_PUNTA ='02'
 AND T.COD_PERI=lsCodPeriodo;

 IF ln_factorMultiplicador<>0 THEN
 RETURN ln_factorMultiplicador;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_FAC_MULTI_3PED: '||ls_sqlerrm);
 RETURN '';


END LID_FN_OBTIE_FAC_MULTI_3PED;


/***************************************************************************
Descripcion : Procedimiemto Evaluar Recomendaciones de 2 pedidos al
 Cierre del periodod

Fecha Creacion : 24/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_EVAL_RECO_2PED_CIER_PER(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2
)IS

 CURSOR c_RECO_LIDE (ps_CodPais VARCHAR2, ps_CodMarca VARCHAR2, ps_PeriodoProceso VARCHAR2)
 IS
 SELECT RL.COD_CONC, RL.COD_CLIE_LIDE, RL.COD_CLIE_RETE, RL.COD_CLIE_REDA, RL.COD_PERI_EVAL, RL.COD_TIPO, RL.COD_PERI_REON
 FROM LID_RECOM_LIDER RL
 WHERE RL.COD_PAIS = psCodPais
 AND RL.COD_MARC = psCodMarca
 AND RL.COD_TIPO ='01'
 AND (RL.INT_EFEC IS NULL OR RL.INT_EFEC=0)
 AND RL.COD_PERI_EVAL = psPeriodoProceso;
 CURSOR c_RECO_LIDE_EFEC
 IS
 SELECT lid_pkg_proce_lider.gen_fn_devuelve_id_cliente(t.cod_clie_lide) AS oid_cliente,
 cpg.oid_para_gral,
 SUM(t.val_punt) sum_puntaje
 FROM lid_recom_lider_tempo t,
 inc_concu_param_gener cpg
 WHERE cpg.num_conc = t.cod_conc
 GROUP BY t.cod_clie_lide,
 cpg.oid_para_gral;


lb_codPeriodoAnterior VARCHAR2(12);
ln_facMult NUMBER;
lb_efectiva BOOLEAN;
--ls_oid_para_gral NUMBER;
ls_oid_periodo NUMBER;
lnIdMarca NUMBER;
lnIdCanal NUMBER;
lnIdPais NUMBER;

lsTipoProcesoRecomendacion LID_PARAM_PROCE_RECOM.TIP_PROC_RECO%TYPE;
lsIndicadorConstancia LID_PARAM.IND_CONS%TYPE;

BEGIN

 lsTipoProcesoRecomendacion := LID_FN_OBTIE_TIPO_PROCE_ACTIV(psCodPais);
 lsIndicadorConstancia := LID_FN_OBTIE_INDIC_CONST(psCodPais);

 FOR fila IN c_RECO_LIDE(psCodPais,psCodMarca,psPeriodoProceso) LOOP

 lb_efectiva:=false;

 --Se Evalua a la Recomendante y a la Recomendada
 IF( lsTipoProcesoRecomendacion = TIPO_PROCESO_AMBOS) THEN

 -- si la recomndada y recomendante pasaron pedido
 IF(LID_FN_PASO_PEDID(fila.cod_clie_rete,psCodPais,psPeriodoProceso)=1) AND (LID_FN_PASO_PEDID(fila.cod_clie_reda,psCodPais,psPeriodoProceso)=1) THEN

 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -1);
 -- si la recomndada y recomendante pasaron pedido en el periodo anterior
 IF(LID_FN_PASO_PEDID(fila.cod_clie_rete,psCodPais,lb_codPeriodoAnterior)=1) AND (LID_FN_PASO_PEDID(fila.cod_clie_reda,psCodPais,lb_codPeriodoAnterior)=1) THEN
 ln_facMult := LID_FN_OBTIE_FAC_MULTI_2PED(fila.COD_PERI_EVAL,fila.cod_conc);

 IF(((lsIndicadorConstancia = '1') AND
 ((fila.COD_CLIE_LIDE IS NOT NULL) AND
 LID_FN_PASO_PEDID(fila.COD_CLIE_LIDE, psCodPais, psPeriodoProceso) = 1))
 OR (lsIndicadorConstancia = '0')) THEN

 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='1'
 ,A.VAL_PUNT = ln_facMult
 ,A.FEC_EVAL=SYSDATE
 ,A.IND_NO_CONS = '0'
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.Cod_Clie_Reda
 AND A.COD_PERI_EVAL = psPeriodoProceso
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;

 lb_efectiva:=true;

 INSERT INTO LID_RECOM_LIDER_TEMPO
 VALUES(
 fila.cod_clie_lide
 ,fila.cod_conc
 ,fila.cod_tipo
 ,null
 ,fila.cod_clie_rete
 ,fila.cod_clie_reda
 ,fila.cod_peri_reon
 ,fila.cod_peri_eval
 ,'1'
 ,ln_facMult
 ,SYSDATE
 ,psCodPais
 ,psCodMarca
 );

 ELSE

 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='2'
 ,A.FEC_EVAL=SYSDATE
 ,A.IND_NO_CONS = '1'
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.Cod_Clie_Reda
 AND A.COD_PERI_EVAL = psPeriodoProceso
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;

 lb_efectiva:=true;
 END IF;

 END IF;
 END IF;

 ELSIF ( lsTipoProcesoRecomendacion = TIPO_PROCESO_RECOMENDADA) THEN

 -- si la recomendada paso pedido
 IF(LID_FN_PASO_PEDID(fila.cod_clie_reda,psCodPais,psPeriodoProceso)=1) THEN

 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -1);
 -- si la recomendada paso pedido en el periodo anterior
 IF(LID_FN_PASO_PEDID(fila.cod_clie_reda,psCodPais,lb_codPeriodoAnterior)=1) THEN
 ln_facMult := LID_FN_OBTIE_FAC_MULTI_2PED(fila.COD_PERI_EVAL,fila.cod_conc);

 IF(((lsIndicadorConstancia = '1') AND
 ((fila.COD_CLIE_LIDE IS NOT NULL) AND
 LID_FN_PASO_PEDID(fila.COD_CLIE_LIDE, psCodPais, psPeriodoProceso) = 1))
 OR (lsIndicadorConstancia = '0')) THEN

 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='1'
 ,A.VAL_PUNT = ln_facMult
 ,A.FEC_EVAL=SYSDATE
 ,A.IND_NO_CONS = '0'
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.Cod_Clie_Reda
 AND A.COD_PERI_EVAL = psPeriodoProceso
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;

 lb_efectiva:=true;

 INSERT INTO LID_RECOM_LIDER_TEMPO
 VALUES(
 fila.cod_clie_lide
 ,fila.cod_conc
 ,fila.cod_tipo
 ,null
 ,fila.cod_clie_rete
 ,fila.cod_clie_reda
 ,fila.cod_peri_reon
 ,fila.cod_peri_eval
 ,'1'
 ,ln_facMult
 ,SYSDATE
 ,psCodPais
 ,psCodMarca
 );

 ELSE

 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='2'
 ,A.FEC_EVAL=SYSDATE
 ,A.IND_NO_CONS = '1'
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.Cod_Clie_Reda
 AND A.COD_PERI_EVAL = psPeriodoProceso
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;

 lb_efectiva:=true;
 END IF;

 END IF;
 END IF;

 END IF;



 IF(NOT lb_efectiva)THEN
 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='2'
 ,A.FEC_EVAL=SYSDATE
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.Cod_Clie_Reda
 AND A.COD_PERI_EVAL = psPeriodoProceso
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;
 END IF;

 END LOOP;
 --ls_oid_para_gral := LID_FN_OBTIE_CONCU_PARAM_GENER(psCodPais,psCodMarca,psPeriodoProceso);
 ls_oid_periodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));

 FOR fila IN c_RECO_LIDE_EFEC LOOP
 IF(fila.sum_puntaje) > 0 THEN
 INSERT INTO INC_CUENT_CORRI_PUNTO
 VALUES(
 INC_CUCP_SEQ.NEXTVAL
 ,INC_CUCP_SEQ.NEXTVAL
 ,fila.sum_puntaje
 ,'0'
 ,TO_DATE(psFechaProceso,'DD/MM/YYYY')
 ,fila.oid_para_gral
 ,fila.oid_cliente
 ,ls_oid_periodo
 ,'1'
 ,SYSDATE
 ,'Abono por Recomendación Efectiva(2P) al cierre',
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL
 );
 END IF;

 END LOOP;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVAL_RECO_2PED_CIER_PER: '||ls_sqlerrm);

END LID_PR_EVAL_RECO_2PED_CIER_PER;

/***************************************************************************
Descripcion : Procedimiemto Evaluar Recomendaciones de 3 pedidos al
 Cierre del periodo

Fecha Creacion : 28/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_EVAL_RECO_3PED_CIER_PER(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2
)IS

 CURSOR c_RECO_LIDE (ps_CodPais VARCHAR2, ps_CodMarca VARCHAR2, ps_PeriodoProceso VARCHAR2)
 IS
 SELECT RL.COD_CONC, RL.COD_CLIE_LIDE, RL.COD_CLIE_RETE, RL.COD_CLIE_REDA, RL.COD_PERI_EVAL, RL.COD_TIPO, RL.COD_PERI_REON
 FROM LID_RECOM_LIDER RL
 WHERE RL.COD_PAIS = psCodPais
 AND RL.COD_MARC = psCodMarca
 AND RL.COD_TIPO ='02'
 AND (RL.INT_EFEC IS NULL OR RL.INT_EFEC=0)
 AND RL.COD_PERI_EVAL = psPeriodoProceso;

 CURSOR c_RECO_LIDE_EFEC
 IS
 SELECT lid_pkg_proce_lider.gen_fn_devuelve_id_cliente(t.cod_clie_lide) AS oid_cliente,
 cpg.oid_para_gral,
 SUM(t.val_punt) sum_puntaje
 FROM lid_recom_lider_tempo t,
 inc_concu_param_gener cpg
 WHERE cpg.num_conc = t.cod_conc
 GROUP BY t.cod_clie_lide,
 cpg.oid_para_gral;

lb_codPeriodoAnterior VARCHAR2(12);
lb_codPeriodoPrevioAnterior VARCHAR2(12);
ln_facMult NUMBER;
lb_efectiva BOOLEAN;
--ls_oid_para_gral NUMBER;
ls_oid_periodo NUMBER;
lnIdMarca NUMBER;
lnIdCanal NUMBER;
lnIdPais NUMBER;

lsTipoProcesoRecomendacion LID_PARAM_PROCE_RECOM.TIP_PROC_RECO%TYPE;
lsIndicadorConstancia LID_PARAM.IND_CONS%TYPE;

BEGIN

 lsTipoProcesoRecomendacion := LID_FN_OBTIE_TIPO_PROCE_ACTIV(psCodPais);
 lsIndicadorConstancia := LID_FN_OBTIE_INDIC_CONST(psCodPais);

 FOR fila IN c_RECO_LIDE(psCodPais,psCodMarca,psPeriodoProceso) LOOP

 lb_efectiva:=false;

 --Se Evalua a la Recomendante y a la Recomendada
 IF( lsTipoProcesoRecomendacion = TIPO_PROCESO_AMBOS) THEN

 -- si la recomndada y recomendante pasaron pedido
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,psPeriodoProceso)=1) AND (LID_FN_PASO_PEDID(fila.Cod_Clie_Rete,psCodPais,psPeriodoProceso)=1) THEN
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -1);
 -- si la recomndada y recomendante pasaron pedido en el periodo anterior
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,lb_codPeriodoAnterior)=1) AND (LID_FN_PASO_PEDID(fila.Cod_Clie_Rete,psCodPais,lb_codPeriodoAnterior)=1) THEN
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoPrevioAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -2);
 -- si la recomndada y recomendante pasaron pedido en el periodo previo al anterior
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,lb_codPeriodoPrevioAnterior)=1) AND (LID_FN_PASO_PEDID(fila.Cod_Clie_Rete,psCodPais,lb_codPeriodoPrevioAnterior)=1) THEN
 ln_facMult := LID_FN_OBTIE_FAC_MULTI_3PED(fila.COD_PERI_EVAL,fila.cod_conc);

 IF(((lsIndicadorConstancia = '1') AND
 ((fila.COD_CLIE_LIDE IS NOT NULL) AND
 LID_FN_PASO_PEDID(fila.COD_CLIE_LIDE, psCodPais, psPeriodoProceso) = 1))
 OR (lsIndicadorConstancia = '0')) THEN

 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='1'
 ,A.VAL_PUNT = ln_facMult
 ,A.FEC_EVAL=SYSDATE
 ,A.IND_NO_CONS = '0'
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;
 lb_efectiva:=true;

 INSERT INTO LID_RECOM_LIDER_TEMPO
 VALUES(
 fila.cod_clie_lide
 ,fila.cod_conc
 ,fila.cod_tipo
 ,null
 ,fila.cod_clie_rete
 ,fila.cod_clie_reda
 ,fila.cod_peri_reon
 ,fila.cod_peri_eval
 ,'1'
 ,ln_facMult
 ,SYSDATE
 ,psCodPais
 ,psCodMarca
 );

 ELSE

 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='2'
 ,A.FEC_EVAL=SYSDATE
 ,A.IND_NO_CONS = '1'
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;

 lb_efectiva:=true;
 END IF;


 END IF;
 END IF;
 END IF;

 ELSIF ( lsTipoProcesoRecomendacion = TIPO_PROCESO_RECOMENDADA) THEN

 -- si la recomendada paso pedido
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,psPeriodoProceso)=1) THEN
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -1);
 -- si la recomendada paso pedido en el periodo anterior
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,lb_codPeriodoAnterior)=1) THEN
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoPrevioAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -2);
 -- si la recomendada paso pedido en el periodo previo al anterior
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,lb_codPeriodoPrevioAnterior)=1) THEN
 ln_facMult := LID_FN_OBTIE_FAC_MULTI_3PED(fila.COD_PERI_EVAL,fila.cod_conc);

 IF(((lsIndicadorConstancia = '1') AND
 ((fila.COD_CLIE_LIDE IS NOT NULL) AND
 LID_FN_PASO_PEDID(fila.COD_CLIE_LIDE, psCodPais, psPeriodoProceso) = 1))
 OR (lsIndicadorConstancia = '0')) THEN

 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='1'
 ,A.VAL_PUNT = ln_facMult
 ,A.FEC_EVAL=SYSDATE
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;
 lb_efectiva:=true;

 INSERT INTO LID_RECOM_LIDER_TEMPO
 VALUES(
 fila.cod_clie_lide
 ,fila.cod_conc
 ,fila.cod_tipo
 ,null
 ,fila.cod_clie_rete
 ,fila.cod_clie_reda
 ,fila.cod_peri_reon
 ,fila.cod_peri_eval
 ,'1'
 ,ln_facMult
 ,SYSDATE
 ,psCodPais
 ,psCodMarca
 );

 ELSE

 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='2'
 ,A.FEC_EVAL=SYSDATE
 ,A.IND_NO_CONS = '1'
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;

 lb_efectiva:=true;
 END IF;


 END IF;
 END IF;
 END IF;

 END IF;


 IF(NOT lb_efectiva)THEN
 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='2'
 ,A.FEC_EVAL=SYSDATE
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;
 END IF;
 END LOOP;
 --ls_oid_para_gral := LID_FN_OBTIE_CONCU_PARAM_GENER(psCodPais,psCodMarca,psPeriodoProceso);
 ls_oid_periodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));

 FOR fila IN c_RECO_LIDE_EFEC LOOP
 IF(fila.sum_puntaje) > 0 THEN
 INSERT INTO INC_CUENT_CORRI_PUNTO
 VALUES(
 INC_CUCP_SEQ.NEXTVAL
 ,INC_CUCP_SEQ.NEXTVAL
 ,fila.sum_puntaje
 ,'0'
 ,TO_DATE(psFechaProceso,'DD/MM/YYYY')
 ,fila.oid_para_gral
 ,fila.oid_cliente
 ,ls_oid_periodo
 ,'1'
 ,SYSDATE
 ,'Abono por Recomendación Efectiva(3P) al cierre',
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL
 );
 END IF;

 END LOOP;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVAL_RECO_3PED_CIER_PER: '||ls_sqlerrm);

END LID_PR_EVAL_RECO_3PED_CIER_PER;

/***************************************************************************
Descripcion : Procedimiemto Evaluar Recomendaciones de 3 pedidos en
 Facturación

Fecha Creacion : 28/01/2008 11:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_EVAL_RECO_3PED_FACT(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2
)IS

 CURSOR c_RECO_LIDE (ps_CodPais VARCHAR2, ps_CodMarca VARCHAR2, ps_PeriodoProceso VARCHAR2)
 IS
 SELECT RL.COD_CONC, RL.COD_CLIE_LIDE, RL.COD_CLIE_RETE, RL.COD_CLIE_REDA, RL.COD_PERI_EVAL, RL.COD_TIPO, RL.COD_PERI_REON
 FROM LID_RECOM_LIDER RL
 WHERE RL.COD_PAIS = psCodPais
 AND RL.COD_MARC = psCodMarca
 AND RL.COD_TIPO ='02'
 AND (RL.INT_EFEC IS NULL OR RL.INT_EFEC=0)
 AND RL.COD_PERI_EVAL = psPeriodoProceso;

 CURSOR c_RECO_LIDE_EFEC
 IS
 SELECT lid_pkg_proce_lider.gen_fn_devuelve_id_cliente(t.cod_clie_lide) AS oid_cliente,
 cpg.oid_para_gral,
 SUM(t.val_punt) sum_puntaje
 FROM lid_recom_lider_tempo t,
 inc_concu_param_gener cpg
 WHERE cpg.num_conc = t.cod_conc
 GROUP BY t.cod_clie_lide,
 cpg.oid_para_gral;

lb_codPeriodoAnterior VARCHAR2(12);
lb_codPeriodoPrevioAnterior VARCHAR2(12);
ln_facMult NUMBER;
lb_efectiva BOOLEAN;
--ls_oid_para_gral NUMBER;
ls_oid_periodo NUMBER;
lnIdMarca NUMBER;
lnIdCanal NUMBER;
lnIdPais NUMBER;

lsTipoProcesoRecomendacion LID_PARAM_PROCE_RECOM.TIP_PROC_RECO%TYPE;

BEGIN

 lsTipoProcesoRecomendacion := LID_FN_OBTIE_TIPO_PROCE_ACTIV(psCodPais);

 FOR fila IN c_RECO_LIDE(psCodPais,psCodMarca,psPeriodoProceso) LOOP

 lb_efectiva:=false;
 -- Si la orden de compra de la lider esta en facturacion
 IF(LID_FN_OBTIE_ORDE_COMPRA_FACT(fila.cod_clie_lide,psCodPais,psPeriodoProceso,psFechaProceso)=1) THEN

 --Se Evalua a la Recomendante y a la Recomendada
 IF( lsTipoProcesoRecomendacion = TIPO_PROCESO_AMBOS) THEN

 -- si la recomndada y recomendante pasaron pedido
 IF(LID_FN_OBTIE_ORDE_COMPRA_FACT1(fila.Cod_Clie_Reda,psCodPais,psPeriodoProceso)=1) AND (LID_FN_OBTIE_ORDE_COMPRA_FACT1(fila.Cod_Clie_Rete,psCodPais,psPeriodoProceso)=1) THEN
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -1);

 -- si la recomndada y recomendante pasaron pedido en el periodo anterior
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,lb_codPeriodoAnterior)=1) AND (LID_FN_PASO_PEDID(fila.Cod_Clie_Rete,psCodPais,lb_codPeriodoAnterior)=1) THEN
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoPrevioAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -2);

 -- si la recomndada y recomendante pasaron pedido en el periodo previo al anterior
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,lb_codPeriodoPrevioAnterior)=1) AND (LID_FN_PASO_PEDID(fila.Cod_Clie_Rete,psCodPais,lb_codPeriodoPrevioAnterior)=1) THEN

 ln_facMult := LID_FN_OBTIE_FAC_MULTI_3PED(fila.COD_PERI_EVAL,fila.cod_conc);
 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='1'
 ,A.VAL_PUNT = ln_facMult
 ,A.FEC_EVAL=SYSDATE
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;
 lb_efectiva:=true;

 INSERT INTO LID_RECOM_LIDER_TEMPO
 VALUES(
 fila.cod_clie_lide
 ,fila.cod_conc
 ,fila.cod_tipo
 ,null
 ,fila.cod_clie_rete
 ,fila.cod_clie_reda
 ,fila.cod_peri_reon
 ,fila.cod_peri_eval
 ,'1'
 ,ln_facMult
 ,SYSDATE
 ,psCodPais
 ,psCodMarca
 );

 END IF;
 END IF;
 END IF;

 ELSIF ( lsTipoProcesoRecomendacion = TIPO_PROCESO_RECOMENDADA) THEN

 -- si la recomndada paso pedido
 IF(LID_FN_OBTIE_ORDE_COMPRA_FACT1(fila.Cod_Clie_Reda,psCodPais,psPeriodoProceso)=1) THEN
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -1);

 -- si la recomndada paso pedido en el periodo anterior
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,lb_codPeriodoAnterior)=1) THEN
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 lb_codPeriodoPrevioAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 -2);

 -- si la recomndada paso pedido en el periodo previo al anterior
 IF(LID_FN_PASO_PEDID(fila.Cod_Clie_Reda,psCodPais,lb_codPeriodoPrevioAnterior)=1) THEN

 ln_facMult := LID_FN_OBTIE_FAC_MULTI_3PED(fila.COD_PERI_EVAL,fila.cod_conc);
 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='1'
 ,A.VAL_PUNT = ln_facMult
 ,A.FEC_EVAL=SYSDATE
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;
 lb_efectiva:=true;

 INSERT INTO LID_RECOM_LIDER_TEMPO
 VALUES(
 fila.cod_clie_lide
 ,fila.cod_conc
 ,fila.cod_tipo
 ,null
 ,fila.cod_clie_rete
 ,fila.cod_clie_reda
 ,fila.cod_peri_reon
 ,fila.cod_peri_eval
 ,'1'
 ,ln_facMult
 ,SYSDATE
 ,psCodPais
 ,psCodMarca
 );

 END IF;
 END IF;
 END IF;

 END IF;


 IF(NOT lb_efectiva)THEN
 UPDATE LID_RECOM_LIDER A
 SET A.INT_EFEC ='0'
 ,A.FEC_EVAL=SYSDATE
 WHERE A.COD_CLIE_LIDE=fila.cod_clie_lide
 AND A.COD_CONC=fila.cod_conc
 AND A.COD_TIPO=fila.cod_tipo
 AND A.COD_CLIE_REDA = fila.cod_clie_reda
 AND A.COD_CLIE_RETE = fila.cod_clie_rete;
 END IF;
 END IF;
 END LOOP;
-- ls_oid_para_gral := LID_FN_OBTIE_CONCU_PARAM_GENER(psCodPais,psCodMarca,psPeriodoProceso);
 ls_oid_periodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));

 FOR fila IN c_RECO_LIDE_EFEC LOOP
 IF(fila.sum_puntaje) > 0 THEN
 INSERT INTO INC_CUENT_CORRI_PUNTO(
 OID_CUEN_CORR_PUNT,
 NUM_MOVI,
 NUM_PUNT,
 NUM_PUNT_EXIG,
 FEC_MOVI,
 COPA_OID_PARA_GRAL,
 CLIE_OID_CLIE,
 PERD_OID_PERI,
 TMOV_OID_TIPO_MOVI,
 FEC_ULTI_ACTU,
 VAL_DESC
 )
 VALUES(
 INC_CUCP_SEQ.NEXTVAL
 ,INC_CUCP_SEQ.NEXTVAL
 ,fila.sum_puntaje
 ,'0'
 ,TO_DATE(psFechaProceso,'DD/MM/YYYY')
 ,fila.OID_PARA_GRAL
 ,fila.oid_cliente
 ,ls_oid_periodo
 ,'1'
 ,SYSDATE
 ,'Abono por Recomendación Efectiva(3P)'
 );
 END IF;
 END LOOP;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVAL_RECO_3PED_FACT: '||ls_sqlerrm);

END LID_PR_EVAL_RECO_3PED_FACT;


/***************************************************************************
Descripcion : Procedimiemto Generar Número de Pedidos de Sección
 por Periodo (Old)

Fecha Creacion : 29/01/2008 2:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_GENE_NUM_PEDI_SECC_PEDI(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodRegion VARCHAR2
)IS

CURSOR C_NUME_PEDI_SECCI_PERI(vnIdPais NUMBER, vnIdMarca NUMBER, vnIdPeriodo NUMBER,vnIdRegion NUMBER )
IS
 SELECT A.OID_ZONA
 ,A.COD_ZONA
 ,B.COD_SECC
 ,B.OID_SECC
 ,B.CLIE_OID_CLIE
 ,SUM(D.NUM_ACTI_FINA) AS numeroActividadFinales
 FROM ZON_ZONA A
 ,ZON_SECCI B
 ,ZON_TERRI_ADMIN C
 ,INT_FUENT_VENTAS_REAL D
 WHERE A.PAIS_OID_PAIS = vnIdPais
 AND A.MARC_OID_MARC = vnIdMarca
 AND A.IND_ACTI = 1
 AND A.IND_BORR = 0
 AND A.ZORG_OID_REGI = vnIdRegion
 --SECCIONES
 AND A.OID_ZONA= B.ZZON_OID_ZONA
 AND B.IND_ACTI = 1
 AND B.IND_BORR = 0
 AND B.CLIE_OID_CLIE IS NOT NULL
 -- ZON_TERRI_ADMIN
 AND B.OID_SECC = C.ZSCC_OID_SECC
 AND C.IND_BORR = 0
 -- VENTA REAL
 AND C.TERR_OID_TERR = D.TERR_OID_TERR
 AND D.PERD_OID_PERI = vnIdPeriodo

 GROUP BY A.OID_ZONA,A.COD_ZONA, B.OID_SECC, B.COD_SECC,B.CLIE_OID_CLIE;

ln_actividadFinalZonaSeccion NUMBER(5,2);
ln_numeroPedidosCalculado NUMBER;
ln_numeroPedidosObjetivo NUMBER;

ln_codClienteLider NUMBER;
ln_valNumPediCalc NUMBER;
ln_valNumPediObjt NUMBER;
ln_codPeriodoSiguiente NUMBER;
lnIdMarca NUMBER;
lnIdCanal NUMBER;
lnIdPais NUMBER;
lnIdRegion NUMBER;
lnIdPeriodo NUMBER;
lsCodClienteFila VARCHAR2(20);
BEGIN
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
 lnIdRegion := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION(psCodPais,psCodMarca,'VD', psCodRegion);
 lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso, lnIdMarca, lnIdCanal);

 FOR fila IN C_NUME_PEDI_SECCI_PERI(lnIdPais , lnIdMarca, lnIdPeriodo, lnIdRegion) LOOP


 ln_codPeriodoSiguiente:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais,
 lnIdMarca,
 lnIdCanal,
 1);
 lsCodClienteFila := GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(fila.clie_oid_clie);

 SELECT Z.VAL_PORC_ACTI_FINA INTO ln_actividadFinalZonaSeccion
 FROM LID_ACTIV_FINAL_ZONA Z
 WHERE Z.COD_PAIS = psCodPais
 AND Z.COD_MARC = psCodMarca
 AND Z.COD_PERI = ln_codPeriodoSiguiente
 AND Z.COD_ZONA = (select zz.cod_zona from zon_zona zz where zz.oid_zona=fila.oid_zona);

 ln_numeroPedidosCalculado := fila.numeroactividadfinales * ln_actividadFinalZonaSeccion/100;
 ln_numeroPedidosObjetivo := fila.numeroactividadfinales * ln_actividadFinalZonaSeccion/100;
 IF(LID_FN_OBTIE_SECC_NUME_PEDI(psCodPais,psCodMarca,fila.cod_zona,fila.cod_secc,ln_codPeriodoSiguiente)=1) THEN

 SELECT A.COD_CLIE_LIDE
 ,A.VAL_NUM_PEDI_CALC
 ,A.VAL_NUM_PEDI_OBJT
 INTO ln_codClienteLider
 ,ln_valNumPediCalc
 ,ln_valNumPediObjt
 FROM LID_SECCI_NUMER_PEDID A
 WHERE A.COD_PAIS = psCodPais
 AND A.COD_MARC = psCodMarca
 AND A.COD_ZONA = fila.cod_zona
 AND A.COD_SECC = fila.cod_secc
 AND A.COD_PERI = ln_codPeriodoSiguiente;

 IF((lsCodClienteFila <> ln_codClienteLider)
 OR (ln_numeroPedidosCalculado<>ln_valNumPediCalc) OR (ln_valNumPediObjt<>ln_numeroPedidosObjetivo) )THEN

 UPDATE LID_SECCI_NUMER_PEDID B
 SET B.COD_CLIE_LIDE = lsCodClienteFila
 ,B.VAL_NUM_PEDI_CALC = round(ln_numeroPedidosCalculado)
 ,B.VAL_NUM_PEDI_OBJT = round(ln_numeroPedidosObjetivo)
 WHERE B.COD_PAIS = psCodPais
 AND B.COD_MARC = psCodMarca
 AND B.COD_ZONA = fila.cod_zona
 AND B.COD_SECC = fila.cod_secc
 AND B.COD_PERI = ln_codPeriodoSiguiente;
 END IF;

 ELSE
 INSERT INTO LID_SECCI_NUMER_PEDID
 VALUES(
 psCodPais
 ,ln_codPeriodoSiguiente
 ,psCodMarca
 ,fila.cod_zona
 ,fila.cod_secc
 ,lsCodClienteFila
 ,round(ln_numeroPedidosCalculado)
 ,round(ln_numeroPedidosObjetivo)
 ,NULL
 ,NULL
 ,NULL
 ,NULL
 ,psCodRegion
 ,NULL
 ,NULL
 ,'0'
 );
 END IF;
 END LOOP;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_GENE_NUM_PEDI_SECC_PEDI: '||ls_sqlerrm);


END LID_PR_GENE_NUM_PEDI_SECC_PEDI;

/**************************************************************************
Descripcion : Devuelve 1 si existe un SECCION NUMERO DE PEDIDO
 si no existe 0

Fecha Creacion : 29/01/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION LID_FN_OBTIE_SECC_NUME_PEDI(psCodPais VARCHAR2, psCodMarca VARCHAR2,codZona VARCHAR2, codSeccion VARCHAR2, psPeriodo VARCHAR2 )
RETURN NUMBER IS
ln_cantidad NUMBER;
BEGIN
 SELECT MIN(1) INTO ln_cantidad
 FROM LID_SECCI_NUMER_PEDID A
 WHERE A.COD_PAIS = psCodPais
 AND A.COD_MARC = psCodMarca
 AND A.COD_ZONA = codZona
 AND A.COD_SECC = codSeccion
 AND A.COD_PERI = psPeriodo;
 IF ln_cantidad=1 THEN
 RETURN 1;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_OBTIE_SECC_NUME_PEDI: '||ls_sqlerrm);
 RETURN '';

END LID_FN_OBTIE_SECC_NUME_PEDI;


/***************************************************************************
Descripcion : Procedimiemto Evaluar Número de Pedidos de la Sección
 al Cierre de Región (Old)

Fecha Creacion : 30/01/2008 6:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_EVA_NUM_PED_SEC_CIE_REG(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodRegion VARCHAR2,
 psFechaProceso VARCHAR2
)IS

CURSOR c_LID_SECCI_NUME_PEDI( psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodRegion VARCHAR2) IS

 SELECT
 A.COD_CLIE_LIDE
 , A.COD_ZONA
 , A.COD_SECC
 , SUM(V.NUM_PEDI) AS SUM_NUM_PEDI
 FROM
 LID_SECCI_NUMER_PEDID A
 ,ZON_ZONA Z
 ,ZON_SECCI S
 ,ZON_TERRI_ADMIN T
 ,INT_FUENT_VENTA_REAL_VACUM V
 WHERE A.COD_PAIS = psCodPais
 AND A.COD_MARC = psCodMarca
 AND A.COD_PERI = psPeriodoProceso
 AND A.COD_REGI = psCodRegion
 AND A.COD_ZONA = Z.COD_ZONA
 AND Z.OID_ZONA = S.ZZON_OID_ZONA
 AND A.COD_SECC = S.COD_SECC
 AND S.OID_SECC = T.ZSCC_OID_SECC
 AND T.TERR_OID_TERR = V.TERR_OID_TERR
 AND (A.IND_EFEC IS NULL OR A.IND_EFEC = '0')
 AND V.PERD_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'))

 GROUP BY A.COD_ZONA, A.COD_SECC,A.COD_CLIE_LIDE;

 ls_oid_para_gral NUMBER;
 ls_num_conc VARCHAR2(6);
 ln_val_num_pedi_objt NUMBER;
 ln_factorMultiplicador NUMBER;
 ls_oid_periodo NUMBER;

BEGIN
 ls_oid_para_gral := LID_FN_OBTIE_CONCU_PARAM_GENER(psCodPais,psCodMarca,psPeriodoProceso);
 ls_num_conc := LID_FN_OBTIE_NUM_CONC_PARA_GEN(psCodPais,psCodMarca,psPeriodoProceso);
 ls_oid_periodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso,GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));
 FOR fila IN c_LID_SECCI_NUME_PEDI(psCodPais , psCodMarca, psPeriodoProceso,psCodRegion) LOOP

 SELECT P.VAL_NUM_PEDI_OBJT INTO ln_val_num_pedi_objt
 FROM LID_SECCI_NUMER_PEDID P
 WHERE P.COD_PAIS = psCodPais
 AND P.COD_MARC = psCodMarca
 AND P.COD_PERI = psPeriodoProceso
 AND P.COD_REGI = psCodRegion
 AND P.COD_ZONA = fila.cod_zona
 AND P.COD_SECC = fila.cod_secc;

 IF fila.sum_num_pedi>=ln_val_num_pedi_objt THEN
 SELECT A.VAL_FAC_MULT INTO ln_factorMultiplicador
 FROM LID_FACTO_PUNTA_PROGR_LIDER A
 WHERE A.COD_CONC = ls_num_conc
 AND A.COD_PERI = psPeriodoProceso
 AND A.TIAP_COD_TIPO_ASIGN_PUNTA = '03';

 UPDATE LID_SECCI_NUMER_PEDID L
 SET L.VAL_NUM_PEDI_REAL = fila.sum_num_pedi
 ,L.IND_EFEC = '1'
 ,L.VAL_PUNT = ln_factorMultiplicador
 ,L.FEC_EVAL = SYSDATE
 WHERE
 L.COD_PAIS=psCodPais
 AND L.COD_MARC=psCodMarca
 AND L.COD_PERI=psPeriodoProceso
 AND L.COD_ZONA=fila.cod_zona
 AND L.COD_SECC=fila.cod_secc;

 -- ABONAR
 INSERT INTO inc_cuent_corri_punto P
 VALUES (
 INC_CUCP_SEQ.NEXTVAL
 ,INC_CUCP_SEQ.NEXTVAL
 ,ln_factorMultiplicador
 ,'0'
 ,TO_DATE(psFechaProceso,'DD/MM/YYYY')
 ,ls_oid_para_gral
 ,LID_PKG_PROCE_LIDER.GEN_FN_DEVUELVE_ID_CLIENTE(fila.cod_clie_lide)
 ,ls_oid_periodo
 ,'1'
 ,SYSDATE
 ,'Abono por Número de Pedidos Sección '||fila.cod_secc,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL
 );

 ELSE
 UPDATE LID_SECCI_NUMER_PEDID L
 SET L.VAL_NUM_PEDI_REAL = fila.sum_num_pedi
 ,L.IND_EFEC = '2'
 ,L.FEC_EVAL = SYSDATE
 WHERE
 L.COD_PAIS=psCodPais
 AND L.COD_MARC=psCodMarca
 AND L.COD_PERI=psPeriodoProceso
 AND L.COD_ZONA=fila.cod_zona
 AND L.COD_SECC=fila.cod_secc;

 END IF;


 END LOOP;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVA_NUM_PED_SEC_CIE_REG: '||ls_sqlerrm);
END LID_PR_EVA_NUM_PED_SEC_CIE_REG;

/***************************************************************************
Descripcion : Procedimiemto Generar Mensaje de Puntaje Obtenido

Fecha Creacion : 29/01/2008 2:56:20 a.m.
Autor : Leonardo Lizana
***************************************************************************/

PROCEDURE LID_PR_GENE_MENSA_PUNTA_OBTEN(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2
)IS

CURSOR c_LID_CONSULTORAS_FACTURADO(
 psCodPais VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2,
 pnOidPais NUMBER,
 pnOidMarca NUMBER) IS

 SELECT DISTINCT
 C.OID_CLIE,
 A.NOM_CLIE,
 A.COD_CLIE,
 CPG.OID_PARA_GRAL,
 CPG.NUM_CONC,
 CPG.VAL_NOMB,
 GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(CPG.PERD_OID_PERI_DESD) PERI_DESD,
 GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(CPG.PERD_OID_PERI_HAST) PERI_HAST
 FROM INT_SOLIC_CONSO_CABEC A,
 		MAE_CLIEN C,
 INC_CONCU_PARAM_GENER CPG,
 INC_CUENT_CORRI_PUNTO CCP
 WHERE A.STA_PROC = 'A'
 AND A.IND_ANUL = '0'
 AND A.IND_PROC_GP2 = '1'
 AND A.IND_OCS_PROC = '1'
 AND A.FEC_PROG_FACT = TO_DATE(psFechaProceso,'DD/MM/YYYY')
 AND A.COD_CLIE=C.COD_CLIE
 AND A.COD_PAIS = psCodPais
 AND A.COD_PERI = psPeriodoProceso
 AND CPG.PAIS_OID_PAIS = pnOidPais
 AND CPG.MARC_OID_MARC = pnOidMarca
 AND CPG.VAL_OBSE = 'PROGRAMA-LIDERES'
 AND CPG.IND_ACTI ='1'
 AND CPG.OID_PARA_GRAL = CCP.COPA_OID_PARA_GRAL
 AND C.OID_CLIE = CCP.CLIE_OID_CLIE
 AND CCP.NUM_PUNT <> 0
 ORDER BY A.COD_CLIE, CPG.NUM_CONC;

 lv_num_puntaje_acumulado NUMBER(12);
 lv_num_puntaje_canjeado NUMBER(12);
 lv_num_puntaje_disponible NUMBER(12);

 lnIdMarca NUMBER;
 lnIdPais NUMBER;
BEGIN

 --OBTENEMOS EL ID PAIS, ID MARCA
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);

 --LIMPIAMOS LA TABLA DE MENSAJES
 EXECUTE IMMEDIATE('TRUNCATE TABLE LID_MENSA_PUNTA_OBTEN');

 --OBTENEMOS LAS LIDERES QUE TIENEN CUENTA CORRIENTE DE PUNTOS PARA UN CONCURSO DEL PROGRAMA DE LIDERES
 FOR fila IN c_LID_CONSULTORAS_FACTURADO(psCodPais, psPeriodoProceso,psFechaProceso,lnIdPais,lnIdMarca) LOOP
 --Recuperamos el puntaje Acumulado
 SELECT sum(P.NUM_PUNT) AS PUNTAJE_ACUMULADO
 INTO lv_num_puntaje_acumulado
 FROM INC_CUENT_CORRI_PUNTO P
 WHERE P.COPA_OID_PARA_GRAL = fila.OID_PARA_GRAL
 AND P.VAL_DESC NOT LIKE 'Entrega de Premio%'
 AND P.CLIE_OID_CLIE = fila.oid_clie;

 --Recuperamos el puntaje Canjeado
 SELECT sum(P.NUM_PUNT)AS PUNTAJE_CANJEADO
 INTO lv_num_puntaje_canjeado
 FROM INC_CUENT_CORRI_PUNTO P
 WHERE P.COPA_OID_PARA_GRAL = fila.OID_PARA_GRAL
 AND P.VAL_DESC LIKE 'Entrega de Premio%'
 AND P.CLIE_OID_CLIE = fila.oid_clie;

 --Recuperamos el puntaje Disponible
 SELECT sum(P.NUM_PUNT)AS PUNTAJE_DISPONIBLE
 INTO lv_num_puntaje_disponible
 FROM INC_CUENT_CORRI_PUNTO P
 WHERE P.COPA_OID_PARA_GRAL = fila.OID_PARA_GRAL
 AND P.CLIE_OID_CLIE = fila.oid_clie;

 INSERT INTO LID_MENSA_PUNTA_OBTEN
 (NOMB_CONC,
 VAL_PUNT_DISP,
 CLIE_OID_CLIE,
 MEN_OID_MENS,
 NOM_CLIE,
 NUM_CONC,
 VAL_PUNT_CANJ,
 COD_PERI_DESD,
 COD_PERI_HAST,
 VAL_PUNT_ACUM,
 FEC_GRAB,
 COD_CLIE)
 VALUES
 (fila.VAL_NOMB,
 NVL(lv_num_puntaje_disponible,0),
 fila.oid_clie,
 null,
 fila.nom_clie,
 fila.NUM_CONC,
 NVL(lv_num_puntaje_canjeado,0),
 fila.PERI_DESD,
 fila.PERI_HAST,
 NVL(lv_num_puntaje_acumulado,0),
 SYSDATE,
 fila.cod_clie
 );

 END LOOP;

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_GENE_MENSA_PUNTA_OBTEN: '||ls_sqlerrm);

END LID_PR_GENE_MENSA_PUNTA_OBTEN;

/***************************************************************************
Descripcion : Devuelve Id de Cliente con atributo :
 FALSE - Retorna NO DATA FOUND
 TRUE - Retorna -1
Fecha Creacion : 07/03/2008
Autor : Leonardo Lizana
***************************************************************************/
FUNCTION GEN_FN_DEVUELVE_ID_CLIENTE(psCodCliente VARCHAR2)
RETURN NUMBER IS
BEGIN
 return GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(psCodCliente,true);
END GEN_FN_DEVUELVE_ID_CLIENTE;

/***************************************************************************
Descripcion : Procedimiemto Generar Número de Pedidos de Sección
 por Periodo (II)

Fecha Creacion : 13/03/2009
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_GENER_NUMER_PEDID_SECCI(
 psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodigoRegion VARCHAR2)
IS
CURSOR C_NUME_PEDI_SECCI_PERI(vnIdMarca NUMBER, vnIdPeriodo NUMBER, vnIdRegion NUMBER ) IS
 SELECT reg.COD_REGI,
 zon.COD_ZONA,
 secc.COD_SECC,
 secc.CLIE_OID_CLIE,
 SUM(fue.NUM_ACTI_FINA) AS ACTIVASFINALES
 FROM ZON_TERRI_ADMIN terri,
 ZON_SECCI secc,
 ZON_ZONA zon,
 ZON_REGIO reg,
 ZON_TERRI ter,
 INT_FUENT_VENTAS_REAL fue
 WHERE fue.TERR_OID_TERR = ter.OID_TERR
 AND fue.PERD_OID_PERI = vnIdPeriodo
 AND ter.oid_terr = terri.TERR_OID_TERR
 AND terri.ZSCC_OID_SECC = secc.OID_SECC
 AND secc.ZZON_OID_ZONA = zon.OID_ZONA
 AND zon.ZORG_OID_REGI = reg.OID_REGI
 AND terri.IND_BORR = 0
 AND secc.IND_BORR = 0
 AND secc.IND_ACTI = 1
 AND zon.IND_BORR = 0
 AND zon.IND_ACTI = 1
 AND (zon.IND_OFIC = 0 OR zon.IND_OFIC IS NULL)
 AND reg.OID_REGI = vnIdRegion
 AND reg.MARC_OID_MARC = vnIdMarca
 GROUP BY
 reg.COD_REGI, zon.COD_ZONA, secc.COD_SECC, secc.CLIE_OID_CLIE;

 lnActividadFinalZona LID_ACTIV_FINAL_ZONA.VAL_PORC_ACTI_FINA%TYPE;
 lnNumeroPedidosCalculado LID_SECCI_NUMER_PEDID.VAL_NUM_PEDI_CALC%TYPE;
 lnNumeroPedidosObjetivo LID_SECCI_NUMER_PEDID.VAL_NUM_PEDI_OBJT%TYPE;
 lnMinimoPedidosObjetivo LID_PARAM.MIN_PEDI_OBJT%TYPE;

 lnValNumPediCalc LID_SECCI_NUMER_PEDID.VAL_NUM_PEDI_CALC%TYPE;
 lnValNumPediObjt LID_SECCI_NUMER_PEDID.VAL_NUM_PEDI_OBJT%TYPE;
 lnValNumActiFina LID_SECCI_NUMER_PEDID.VAL_NUM_ACTI_FINA%TYPE;

 lsCodPeriodoSiguiente VARCHAR2(6);
 lnIdMarca NUMBER;
 lnIdCanal NUMBER;
 lnIdPais NUMBER;
 lnIdRegion NUMBER;
 lnIdPeriodo NUMBER;
 lsCodigoCliente MAE_CLIEN.COD_CLIE%TYPE;
BEGIN
 --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL, ID REGION, ID PERIODO
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
 lnIdRegion := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION(psCodigoPais, psCodigoMarca, 'VD', psCodigoRegion);
 lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso, lnIdMarca, lnIdCanal);

 --RECUPERAMOS EL PERIODO SIGUIENTE DEL PERIODO A PROCESAR
 lsCodPeriodoSiguiente := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psPeriodoProceso,
 lnIdPais, lnIdMarca, lnIdCanal,1);

 --RECUPERAMOS EL NUMERO DE PEDIDOS OBJETIVO MINIMO DEL PAIS
 BEGIN
 SELECT MIN_PEDI_OBJT
 INTO lnMinimoPedidosObjetivo
 FROM LID_PARAM
 WHERE COD_PAIS = psCodigoPais;
 END;

 --RECORREMOS LAS SECCIONES DE LA REGION SELECCIONADA
 FOR fila IN C_NUME_PEDI_SECCI_PERI(lnIdMarca, lnIdPeriodo, lnIdRegion) LOOP
   --RECUPERAMOS EL CODIGO CLIENTE
   IF(fila.CLIE_OID_CLIE IS NOT NULL) THEN
   lsCodigoCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(fila.CLIE_OID_CLIE);
   ELSE
   lsCodigoCliente := '';
   END IF;

   --RECUPERAMOS LA ACTIVIDAD FINAL DE LA ZONA
   BEGIN
   SELECT Z.VAL_PORC_ACTI_FINA
   INTO lnActividadFinalZona
   FROM LID_ACTIV_FINAL_ZONA Z
   WHERE Z.COD_PAIS = psCodigoPais
   AND Z.COD_MARC = psCodigoMarca
   AND Z.COD_PERI = lsCodPeriodoSiguiente
   AND Z.COD_ZONA = fila.COD_ZONA;
   EXCEPTION
   WHEN OTHERS THEN
   lnActividadFinalZona := NULL;
   END;

   IF ((lnActividadFinalZona IS NOT NULL) OR
   ((lnActividadFinalZona IS NULL) AND (lnMinimoPedidosObjetivo IS NOT NULL)) ) THEN

       --CALCULAMOS LAS VARIABLES PEDIDOS CALCULADO
       lnNumeroPedidosCalculado := ROUND(fila.ACTIVASFINALES * lnActividadFinalZona/100);

       --CALCULAMOS LAS VARIABLES PEDIDOS OBJETIVO
       IF(lnActividadFinalZona IS NOT NULL) THEN

         IF(lnNumeroPedidosCalculado < lnMinimoPedidosObjetivo) THEN
            lnNumeroPedidosObjetivo := lnMinimoPedidosObjetivo;
         ELSE
            lnNumeroPedidosObjetivo := lnNumeroPedidosCalculado;
         END IF;

       ELSE
         lnNumeroPedidosObjetivo := lnMinimoPedidosObjetivo;
       END IF;

       --VALIDAMOS SI YA SE HA REGISTRADO ANTERIORMENTE LA SECCION
       IF(LID_FN_OBTIE_SECC_NUME_PEDI(psCodigoPais, psCodigoMarca,
       fila.COD_ZONA, fila.COD_SECC,
       lsCodPeriodoSiguiente) = 1) THEN
         SELECT A.VAL_NUM_PEDI_CALC,
         A.VAL_NUM_PEDI_OBJT,
         A.VAL_NUM_ACTI_FINA
         INTO lnValNumPediCalc,
         lnValNumPediObjt,
         lnValNumActiFina
         FROM LID_SECCI_NUMER_PEDID A
         WHERE A.COD_PAIS = psCodigoPais
         AND A.COD_MARC = psCodigoMarca
         AND A.COD_ZONA = fila.COD_ZONA
         AND A.COD_SECC = fila.COD_SECC
         AND A.COD_PERI = lsCodPeriodoSiguiente;

         IF((lnNumeroPedidosCalculado <> lnValNumPediCalc)
         OR (lnNumeroPedidosObjetivo <> lnValNumPediObjt)
         OR (fila.ACTIVASFINALES <> lnValNumActiFina) )THEN

           UPDATE LID_SECCI_NUMER_PEDID B
           SET B.VAL_NUM_PEDI_CALC = lnNumeroPedidosCalculado,
           B.VAL_NUM_PEDI_OBJT = lnNumeroPedidosObjetivo,
           B.VAL_NUM_ACTI_FINA = lnValNumActiFina
           WHERE B.COD_PAIS = psCodigoPais
           AND B.COD_MARC = psCodigoMarca
           AND B.COD_ZONA = fila.COD_ZONA
           AND B.COD_SECC = fila.COD_SECC
           AND B.COD_PERI = lsCodPeriodoSiguiente;
         END IF;
       ELSE
         INSERT INTO LID_SECCI_NUMER_PEDID
         (COD_PAIS, COD_PERI, COD_MARC,
         COD_REGI, COD_ZONA, COD_SECC, COD_CLIE_LIDE,
         VAL_NUM_PEDI_CALC, VAL_NUM_PEDI_OBJT, VAL_NUM_ACTI_FINA,
         COD_CONC, IND_NO_CONS)
         VALUES
         (psCodigoPais ,lsCodPeriodoSiguiente ,psCodigoMarca,
         psCodigoRegion, fila.COD_ZONA, fila.COD_SECC, NULL,
         lnNumeroPedidosCalculado, lnNumeroPedidosObjetivo, fila.ACTIVASFINALES,
         NULL, '0');
       END IF;

   ELSE

     lnNumeroPedidosObjetivo := 0;

     INSERT INTO LID_SECCI_NUMER_PEDID
     (COD_PAIS, COD_PERI, COD_MARC,
     COD_REGI, COD_ZONA, COD_SECC, COD_CLIE_LIDE,
     VAL_NUM_PEDI_CALC, VAL_NUM_PEDI_OBJT, VAL_NUM_ACTI_FINA,
     COD_CONC, IND_NO_CONS)
     VALUES
     (psCodigoPais ,lsCodPeriodoSiguiente ,psCodigoMarca,
     psCodigoRegion, fila.COD_ZONA, fila.COD_SECC, NULL,
     lnNumeroPedidosCalculado, lnNumeroPedidosObjetivo, fila.ACTIVASFINALES,
     NULL, '0');

     --EN CASO NO SE RECUPERAR ACTIVIDAD FINAL DE LA ZONA
     INSERT INTO LID_SECCI_PENDI_GENER
     (COD_PAIS, COD_PERI, COD_MARC,
     COD_REGI, COD_ZONA, COD_SECC, COD_CLIE_LIDE, FEC_ULTI_ACTU)
     VALUES
     (psCodigoPais ,lsCodPeriodoSiguiente ,psCodigoMarca,
     psCodigoRegion, fila.COD_ZONA, fila.COD_SECC, lsCodigoCliente, SYSDATE);

   END IF;
 END LOOP;

 --INSERTAMOS EN ESTA TABLA PARA SABER SI YA SE HA PROCESADO PARA UNA DETERMINADA REGION
 INSERT INTO LID_REGIO_GENER
 VALUES(psCodigoPais, psPeriodoProceso, psCodigoRegion, '1', SYSDATE);

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_GENER_NUMER_PEDID_SECCI: '||ls_sqlerrm);
END LID_PR_GENER_NUMER_PEDID_SECCI;

/***************************************************************************
Descripcion : Procedimiemto Evaluar Número de Pedidos de la Sección
 al Cierre de Región (II)

Fecha Creacion : 17/03/2009
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_EVALU_NUMER_PEDID_CIERE(
 psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psFechaProceso VARCHAR2)
IS
 CURSOR c_LID_SECCI_NUME_PEDI(vnIdPais NUMBER, vnIdRegion NUMBER, vInPeriodo NUMBER) IS
 SELECT A.COD_ZONA,
 A.COD_SECC,
 S.CLIE_OID_CLIE,
 (select count(sc.oid_soli_cabe)
 from ped_solic_cabec sc
 ,ped_tipo_solic ts
 ,ped_tipo_solic_pais tsp
 ,zon_terri_admin ta
 where sc.perd_oid_peri = vInPeriodo
 and sc.pais_oid_pais = vnIdPais
 and sc.fec_fact is not null
 and sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
 and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
 and ts.cod_tipo_soli = 'SOC'
 --and sc.terr_oid_terr = ta.terr_oid_terr
 and sc.ZTAD_OID_TERR_ADMI = ta.OID_TERR_ADMI
 and ta.zscc_oid_secc = S.OID_SECC) TOTAL_PEDIDOS,
 A.VAL_NUM_PEDI_OBJT
 FROM LID_SECCI_NUMER_PEDID A,
 ZON_SECCI S,
 ZON_ZONA Z,
 ZON_REGIO R
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_MARC = psCodigoMarca
 AND A.COD_PERI = psPeriodoProceso
 AND A.COD_REGI = psCodigoRegion
 AND A.COD_ZONA = Z.COD_ZONA
 AND A.COD_SECC = S.COD_SECC
 AND A.COD_REGI = R.COD_REGI
 AND Z.OID_ZONA = S.ZZON_OID_ZONA
 AND R.OID_REGI = Z.ZORG_OID_REGI
 AND R.OID_REGI = vnIdRegion
 AND R.PAIS_OID_PAIS = vnIdPais
 AND (A.IND_EFEC IS NULL OR A.IND_EFEC = '0')
 AND S.IND_ACTI = '1';

 lnNumeroPedidosObjetivos LID_SECCI_NUMER_PEDID.VAL_NUM_PEDI_OBJT%TYPE;
 lnFactorMultiplicador LID_FACTO_PUNTA_PROGR_LIDER.VAL_FAC_MULT%TYPE;
 lsCodigoClienteLider LID_SECCI_NUMER_PEDID.COD_CLIE_LIDE%TYPE;

 lnIdMarca NUMBER;
 lnIdCanal NUMBER;
 lnIdPais NUMBER;
 lnIdRegion NUMBER;
 lnIdPeriodo NUMBER;

 lnOidConcurso NUMBER;
 lsNumeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
 lsIndicadorConstancia LID_PARAM.IND_CONS%TYPE;
BEGIN
 --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL, ID REGION, ID PERIODO
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
 lnIdRegion := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION(psCodigoPais, psCodigoMarca, 'VD', psCodigoRegion);
 lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso, lnIdMarca, lnIdCanal);

 --OBTENEMOS EL CONCURSO VIGENTE PARA EL PERIODO SELECCIONADO
 lnOidConcurso := LID_FN_OBTIE_CONCU_PARAM_GENER(psCodigoPais, psCodigoMarca, psPeriodoProceso);
 lsNumeroConcurso := LID_FN_OBTIE_NUM_CONC_PARA_GEN(psCodigoPais, psCodigoMarca, psPeriodoProceso);

   --RECUPERAMOS EL INDICADOR DE CONSTANCIA DEL PAIS
   lsIndicadorConstancia := LID_FN_OBTIE_INDIC_CONST(psCodigoPais);

   --RECORREMOS LAS SECCIONES DE LA REGION SELECCIONADA
   FOR fila IN c_LID_SECCI_NUME_PEDI(lnIdPais, lnIdRegion , lnIdPeriodo) LOOP

     --RECUPERAMOS LA LIDER DE LA SECCION
     IF(fila.CLIE_OID_CLIE IS NOT NULL) THEN
        lsCodigoClienteLider := GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(fila.CLIE_OID_CLIE);
     ELSE
        lsCodigoClienteLider := '';
     END IF;

     --RECUPERAMOS EL NUMERO DE PEDIDOS OBJETIVO POR CADA SECCION
     lnNumeroPedidosObjetivos := fila.VAL_NUM_PEDI_OBJT;

     --CUMPLIO CON EL OBJETIVO
       IF (fila.TOTAL_PEDIDOS >= lnNumeroPedidosObjetivos) THEN
         --RECUPERMAMOS EL FACTOR MULTIPLICADOR
         SELECT A.VAL_FAC_MULT
         INTO lnFactorMultiplicador
         FROM LID_FACTO_PUNTA_PROGR_LIDER A
         WHERE A.COD_PAIS = psCodigoPais
         AND A.COD_CONC = lsNumeroConcurso
         AND A.COD_PERI = psPeriodoProceso
         AND A.TIAP_COD_TIPO_ASIGN_PUNTA = '03';

         IF(((lsIndicadorConstancia = '1') AND
         ((lsCodigoClienteLider IS NOT NULL) AND
 LID_FN_OBTIE_ORDE_COMPRA_FACT1(lsCodigoClienteLider, psCodigoPais, psPeriodoProceso) = 1))
         OR (lsIndicadorConstancia = '0')) THEN

           UPDATE LID_SECCI_NUMER_PEDID L
           SET L.COD_CLIE_LIDE = lsCodigoClienteLider,
           L.COD_CONC = lsNumeroConcurso,
           L.VAL_NUM_PEDI_REAL = fila.TOTAL_PEDIDOS,
           L.IND_EFEC = '1',
           L.VAL_PUNT = lnFactorMultiplicador,
           L.FEC_EVAL = SYSDATE
           WHERE L.COD_PAIS = psCodigoPais
           AND L.COD_MARC = psCodigoMarca
           AND L.COD_PERI = psPeriodoProceso
           AND L.COD_ZONA = fila.COD_ZONA
           AND L.COD_SECC = fila.COD_SECC;

           --SI CUMPLIO CON EL OBJETIVO, SE LE ABONA PUNTOS, SI LA SECCION TIENE LIDER
           IF (fila.CLIE_OID_CLIE IS NOT NULL) THEN
             INSERT INTO INC_CUENT_CORRI_PUNTO P
             (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
             NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
             CLIE_OID_CLIE, PERD_OID_PERI, TMOV_OID_TIPO_MOVI,
             FEC_ULTI_ACTU, VAL_DESC)
             VALUES
             (INC_CUCP_SEQ.NEXTVAL, INC_CUCP_SEQ.NEXTVAL, lnFactorMultiplicador,
             0, TO_DATE(psFechaProceso,'DD/MM/YYYY'), lnOidConcurso,
             fila.CLIE_OID_CLIE, lnIdPeriodo, '1',
             SYSDATE, 'Abono por Número de Pedidos Sección ' || fila.COD_SECC || ' al cierre de región');
           END IF;

         ELSE
           UPDATE LID_SECCI_NUMER_PEDID L
           SET L.COD_CLIE_LIDE = lsCodigoClienteLider,
           L.COD_CONC = lsNumeroConcurso,
           L.VAL_NUM_PEDI_REAL = fila.TOTAL_PEDIDOS,
           L.IND_EFEC = '0',
           L.FEC_EVAL = SYSDATE,
 L.IND_NO_CONS = '1'
           WHERE L.COD_PAIS = psCodigoPais
           AND L.COD_MARC = psCodigoMarca
           AND L.COD_PERI = psPeriodoProceso
           AND L.COD_ZONA = fila.COD_ZONA
           AND L.COD_SECC = fila.COD_SECC;
         END IF;

       ELSE
         --LA SECCION NO CUMPLIO CON SUS NUMEROS DE PEDIDOS OBJETIVO
         UPDATE LID_SECCI_NUMER_PEDID L
         SET L.COD_CLIE_LIDE = lsCodigoClienteLider,
         L.COD_CONC = lsNumeroConcurso,
         L.VAL_NUM_PEDI_REAL = fila.TOTAL_PEDIDOS,
         L.IND_EFEC = '0',
         L.FEC_EVAL = SYSDATE
         WHERE L.COD_PAIS = psCodigoPais
         AND L.COD_MARC = psCodigoMarca
         AND L.COD_PERI = psPeriodoProceso
         AND L.COD_ZONA = fila.COD_ZONA
         AND L.COD_SECC = fila.COD_SECC;
       END IF;

   END LOOP;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVALU_NUMER_PEDID_CIERE: ' || ls_sqlerrm);
END LID_PR_EVALU_NUMER_PEDID_CIERE;

/***************************************************************************
Descripcion : Procedimiemto Evaluar Número de Pedidos de la Sección
 al Cierre de Periodo

Fecha Creacion : 23/03/20089
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_EVALU_NUMER_PEDID_CIEPE(
 psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2)
IS
CURSOR c_LID_SECCI_NUME_PEDI(vnIdPais NUMBER, vInPeriodo NUMBER) IS
 SELECT A.COD_REGI,
 A.COD_ZONA,
 A.COD_SECC,
 S.CLIE_OID_CLIE,
 (select count(sc.oid_soli_cabe)
 from ped_solic_cabec sc
 ,ped_tipo_solic ts
 ,ped_tipo_solic_pais tsp
 ,zon_terri_admin ta
 where sc.perd_oid_peri = vInPeriodo
 and sc.pais_oid_pais = vnIdPais
 and sc.fec_fact is not null
 and sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
 and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
 and ts.cod_tipo_soli = 'SOC'
 --and sc.terr_oid_terr = ta.terr_oid_terr
 and sc.ZTAD_OID_TERR_ADMI = ta.OID_TERR_ADMI
 and ta.zscc_oid_secc = S.OID_SECC) TOTAL_PEDIDOS,
 A.VAL_NUM_PEDI_OBJT
 FROM LID_SECCI_NUMER_PEDID A,
 ZON_SECCI S,
 ZON_ZONA Z,
 ZON_REGIO R
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_MARC = psCodigoMarca
 AND A.COD_PERI = psPeriodoProceso
 AND A.COD_ZONA = Z.COD_ZONA
 AND A.COD_SECC = S.COD_SECC
 AND A.COD_REGI = R.COD_REGI
 AND Z.OID_ZONA = S.ZZON_OID_ZONA
 AND R.OID_REGI = Z.ZORG_OID_REGI
 AND R.PAIS_OID_PAIS = vnIdPais
 AND (A.IND_EFEC IS NULL OR A.IND_EFEC = '0')
 AND s.Ind_Acti = '1';

 lnNumeroPedidosObjetivos LID_SECCI_NUMER_PEDID.VAL_NUM_PEDI_OBJT%TYPE;
 lnFactorMultiplicador LID_FACTO_PUNTA_PROGR_LIDER.VAL_FAC_MULT%TYPE;
 lsCodigoClienteLider LID_SECCI_NUMER_PEDID.COD_CLIE_LIDE%TYPE;

 lnIdMarca NUMBER;
 lnIdCanal NUMBER;
 lnIdPais NUMBER;
 lnIdPeriodo NUMBER;

 lnOidConcurso NUMBER;
 lsNumeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
 lsIndicadorConstancia LID_PARAM.IND_CONS%TYPE;
BEGIN
 --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL, ID REGION, ID PERIODO
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
 lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso, lnIdMarca, lnIdCanal);

 --OBTENEMOS EL CONCURSO VIGENTE PARA EL PERIODO SELECCIONADO
 lnOidConcurso := LID_FN_OBTIE_CONCU_PARAM_GENER(psCodigoPais, psCodigoMarca, psPeriodoProceso);
 lsNumeroConcurso := LID_FN_OBTIE_NUM_CONC_PARA_GEN(psCodigoPais, psCodigoMarca, psPeriodoProceso);

 --RECUPERAMOS EL INDICADOR DE CONSTANCIA DEL PAIS
 lsIndicadorConstancia := LID_FN_OBTIE_INDIC_CONST(psCodigoPais);

 --RECORREMOS LAS SECCIONES DE LA REGION SELECCIONADA
 FOR fila IN c_LID_SECCI_NUME_PEDI(lnIdPais, lnIdPeriodo) LOOP

 --RECUPERAMOS LA LIDER DE LA SECCION
 IF(fila.CLIE_OID_CLIE IS NOT NULL) THEN
 lsCodigoClienteLider := GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(fila.CLIE_OID_CLIE);
 ELSE
 lsCodigoClienteLider := '';
 END IF;

 --RECUPERAMOS EL NUMERO DE PEDIDOS OBJETIVO POR CADA SECCION
 lnNumeroPedidosObjetivos := fila.VAL_NUM_PEDI_OBJT;

 --CUMPLIO CON EL OBJETIVO
 IF (fila.TOTAL_PEDIDOS >= lnNumeroPedidosObjetivos) THEN
 --RECUPERMAMOS EL FACTOR MULTIPLICADOR
 SELECT A.VAL_FAC_MULT
 INTO lnFactorMultiplicador
 FROM LID_FACTO_PUNTA_PROGR_LIDER A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_CONC = lsNumeroConcurso
 AND A.COD_PERI = psPeriodoProceso
 AND A.TIAP_COD_TIPO_ASIGN_PUNTA = '03';

 IF(((lsIndicadorConstancia = '1') AND
 ((lsCodigoClienteLider IS NOT NULL) AND
 LID_FN_PASO_PEDID(lsCodigoClienteLider, psCodigoPais, psPeriodoProceso) = 1))
 OR (lsIndicadorConstancia = '0')) THEN

 UPDATE LID_SECCI_NUMER_PEDID L
 SET L.COD_CLIE_LIDE = lsCodigoClienteLider,
 L.COD_CONC = lsNumeroConcurso,
 L.VAL_NUM_PEDI_REAL = fila.TOTAL_PEDIDOS,
 L.IND_EFEC = '1',
 L.VAL_PUNT = lnFactorMultiplicador,
 L.FEC_EVAL = SYSDATE,
 L.IND_NO_CONS = '0'
 WHERE L.COD_PAIS = psCodigoPais
 AND L.COD_MARC = psCodigoMarca
 AND L.COD_PERI = psPeriodoProceso
 AND L.COD_REGI = fila.COD_REGI
 AND L.COD_ZONA = fila.COD_ZONA
 AND L.COD_SECC = fila.COD_SECC;

 --SI CUMPLIO CON EL OBJETIVO, SE LE ABONA PUNTOS, SI LA SECCION TIENE LIDER
 IF (fila.CLIE_OID_CLIE IS NOT NULL) THEN
 INSERT INTO INC_CUENT_CORRI_PUNTO P
 (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
 NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
 CLIE_OID_CLIE, PERD_OID_PERI, TMOV_OID_TIPO_MOVI,
 FEC_ULTI_ACTU, VAL_DESC)
 VALUES
 (INC_CUCP_SEQ.NEXTVAL, INC_CUCP_SEQ.NEXTVAL, lnFactorMultiplicador,
 0, TO_DATE(psFechaProceso,'DD/MM/YYYY'), lnOidConcurso,
 fila.CLIE_OID_CLIE, lnIdPeriodo, '1',
 SYSDATE, 'Abono por Número de Pedidos Sección ' || fila.COD_SECC || ' al cierre de campaña');
 END IF;

 ELSE
 UPDATE LID_SECCI_NUMER_PEDID L
 SET L.COD_CLIE_LIDE = lsCodigoClienteLider,
 L.COD_CONC = lsNumeroConcurso,
 L.VAL_NUM_PEDI_REAL = fila.TOTAL_PEDIDOS,
 L.IND_EFEC = '2',
 L.FEC_EVAL = SYSDATE,
 L.IND_NO_CONS = '1'
 WHERE L.COD_PAIS = psCodigoPais
 AND L.COD_MARC = psCodigoMarca
 AND L.COD_PERI = psPeriodoProceso
 AND L.COD_REGI = fila.COD_REGI
 AND L.COD_ZONA = fila.COD_ZONA
 AND L.COD_SECC = fila.COD_SECC;
 END IF;

 ELSE
 --LA SECCION NO CUMPLIO CON SUS NUMEROS DE PEDIDOS OBJETIVO
 UPDATE LID_SECCI_NUMER_PEDID L
 SET L.COD_CLIE_LIDE = lsCodigoClienteLider,
 L.COD_CONC = lsNumeroConcurso,
 L.VAL_NUM_PEDI_REAL = fila.TOTAL_PEDIDOS,
 L.IND_EFEC = '2',
 L.FEC_EVAL = SYSDATE
 WHERE L.COD_PAIS = psCodigoPais
 AND L.COD_MARC = psCodigoMarca
 AND L.COD_PERI = psPeriodoProceso
 AND L.COD_REGI = fila.COD_REGI
 AND L.COD_ZONA = fila.COD_ZONA
 AND L.COD_SECC = fila.COD_SECC;
 END IF;

 END LOOP;

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVALU_NUMER_PEDID_CIEPE: ' || ls_sqlerrm);
END LID_PR_EVALU_NUMER_PEDID_CIEPE;

/***************************************************************************
Descripcion : Procedimiemto Evaluar Ingresos Efectivos de la Sección
 al Cierre de Región

Fecha Creacion : 27/03/20089
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_EVALU_INGRE_EFECT_CIERE(
 psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psFechaProceso VARCHAR2,
 psTipoEvaluacion VARCHAR2)
IS
CURSOR c_LID_SECCI(vnIdRegion NUMBER) IS
 SELECT reg.COD_REGI,
 zon.COD_ZONA,
 secc.COD_SECC,
 cli.COD_CLIE
 FROM ZON_SECCI secc,
 ZON_ZONA zon,
 ZON_REGIO reg,
 MAE_CLIEN cli
 WHERE secc.ZZON_OID_ZONA = zon.OID_ZONA
 AND zon.ZORG_OID_REGI = reg.OID_REGI
 AND secc.IND_BORR = 0
 AND secc.IND_ACTI = 1
 AND zon.IND_BORR = 0
 AND zon.IND_ACTI = 1
 AND reg.OID_REGI = vnIdRegion
 AND cli.OID_CLIE = secc.CLIE_OID_CLIE;

CURSOR c_LID_SECCI_INGRE_EFECT IS
 SELECT A.COD_REGI,
 A.COD_ZONA,
 A.COD_SECC,
 A.COD_CLIE_LIDE,
 A.IND_EFEC_CUMP,
 A.IND_EFEC_SUPE,
 A.VAL_INGR_EFEC_OBJT,
 A.VAL_INGR_EFEC_REAL,
 A.COD_CONC
 FROM LID_SECCI_INGRE_EFECT A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_MARC = psCodigoMarca
 AND A.COD_PERI = psPeriodoProceso
 AND A.COD_REGI = psCodigoRegion
 AND ((A.IND_EFEC_CUMP IS NULL OR A.IND_EFEC_CUMP = '0')
 OR (A.IND_EFEC_SUPE IS NULL OR A.IND_EFEC_SUPE = '0'));

 lnNumeroIngresosEfectivos LID_OBJET_ASIGN_PUNTA.VAL_OBJT%TYPE;
 lnNumeroIngresosReales LID_RECOM_LIDER.VAL_PUNT%TYPE;
 lnFactorMultiplicador04 LID_FACTO_PUNTA_PROGR_LIDER.VAL_FAC_MULT%TYPE;
 lnFactorMultiplicador05 LID_FACTO_PUNTA_PROGR_LIDER.VAL_FAC_MULT%TYPE;

 lnIdCliente MAE_CLIEN.OID_CLIE%TYPE;
 lnIdPais NUMBER;
 lnIdMarca NUMBER;
 lnIdCanal NUMBER;
 lnIdRegion NUMBER;
 lnIdPeriodo NUMBER;

 lnOidConcurso NUMBER;
 lsNumeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
 lnTotal NUMBER;
 lnTotalCumplimiento NUMBER;
 lnTotalSuperacion NUMBER;
 lsIndicadorConstancia LID_PARAM.IND_CONS%TYPE;
 lsCodigoPeriodoAnterior SEG_PERIO_CORPO.COD_PERI%TYPE;
BEGIN
 --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL, ID REGION, ID PERIODO
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
 lnIdRegion := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION(psCodigoPais, psCodigoMarca, 'VD', psCodigoRegion);
 lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso, lnIdMarca, lnIdCanal);

 --VERIFICAMOS SI EXISTE TIPOS DE PUNTAJE PARA EL TIPO DE EVALUACION SELECCIONADO
 SELECT NVL(SUM(CASE WHEN COD_TIPO_ASIG_PUNT = '04' THEN 1 ELSE 0 END),0),
 NVL(SUM(CASE WHEN COD_TIPO_ASIG_PUNT = '05' THEN 1 ELSE 0 END),0)
 INTO lnTotalCumplimiento,
 lnTotalSuperacion
 FROM LID_TIPO_EVALU_ASIGN_PUNTA
 WHERE COD_TIPO_EVAL = psTipoEvaluacion;

 IF((lnTotalCumplimiento + lnTotalSuperacion) = 0) THEN
 RETURN;
 END IF;

 --RECUPERAMOS LA META PARA EL CUMPLIMIENTO DE INGRESOS EFECTIVOS
 BEGIN
 SELECT VAL_OBJT
 INTO lnNumeroIngresosEfectivos
 FROM LID_OBJET_ASIGN_PUNTA
 WHERE COD_PAIS = psCodigoPais
 AND COD_TIPO_ASIG_PUNT = '04'
 AND COD_PERI_INIC <= psPeriodoProceso
 AND COD_PERI_FINA >= psPeriodoProceso;
 EXCEPTION
 WHEN OTHERS THEN
 RETURN;
 END;

 FOR fila IN c_LID_SECCI(lnIdRegion) LOOP

 --SE RECUPERA EL NUMERO DE RECOMENDADAS EFECTIVAS
 BEGIN
 SELECT COD_CONC, TOTAL
 INTO lsNumeroConcurso, lnNumeroIngresosReales
 FROM (
 SELECT COD_CONC, COUNT(*) TOTAL
 FROM LID_RECOM_LIDER A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_MARC = psCodigoMarca
 AND A.COD_TIPO = '01'
 AND A.COD_CLIE_LIDE = fila.COD_CLIE
 AND A.COD_PERI_EVAL = psPeriodoProceso
 AND A.INT_EFEC = 1
 GROUP BY A.COD_CONC)
 WHERE ROWNUM = 1
 ORDER BY TOTAL DESC;
 EXCEPTION
 WHEN OTHERS THEN
 lnNumeroIngresosReales := 0;

 --OBTENEMOS EL CODIGO DE PERIODO ANTERIOR AL PERIODO DE PROCESO
 lsCodigoPeriodoAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais, lnIdMarca, lnIdCanal, -1);

 --OBTENEMOS EL CONCURSO ACTIVO DEL PERIODO ANTERIOR
 lsNumeroConcurso := LID_FN_OBTIE_NUM_CONC_PARA_GEN(psCodigoPais, psCodigoMarca, lsCodigoPeriodoAnterior);
 END;

 SELECT COUNT(*)
 INTO lnTotal
 FROM LID_SECCI_INGRE_EFECT A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_MARC = psCodigoMarca
 AND A.COD_PERI = psPeriodoProceso
 AND A.COD_REGI = psCodigoRegion
 AND A.COD_ZONA = fila.COD_ZONA
 AND A.COD_SECC = fila.COD_SECC;

 IF(lnTotal > 0) THEN
 UPDATE LID_SECCI_INGRE_EFECT L
 SET L.VAL_INGR_EFEC_OBJT = lnNumeroIngresosEfectivos,
 L.VAL_INGR_EFEC_REAL = lnNumeroIngresosReales
 WHERE L.COD_PAIS = psCodigoPais
 AND L.COD_MARC = psCodigoMarca
 AND L.COD_PERI = psPeriodoProceso
 AND L.COD_REGI = psCodigoRegion
 AND L.COD_ZONA = fila.COD_ZONA
 AND L.COD_SECC = fila.COD_SECC;
 ELSE

 INSERT INTO LID_SECCI_INGRE_EFECT
 (COD_PAIS, COD_PERI, COD_MARC,
 COD_REGI, COD_ZONA, COD_SECC,
 COD_CLIE_LIDE, VAL_INGR_EFEC_OBJT, VAL_INGR_EFEC_REAL,
 COD_CONC, IND_NO_CONS)
 VALUES
 (psCodigoPais, psPeriodoProceso, psCodigoMarca,
 psCodigoRegion, fila.COD_ZONA, fila.COD_SECC,
 fila.COD_CLIE, lnNumeroIngresosEfectivos, lnNumeroIngresosReales,
 lsNumeroConcurso, '0');

 END IF;

 END LOOP;

 --RECUPERAMOS EL INDICADOR DE CONSTANCIA DEL PAIS
 lsIndicadorConstancia := LID_FN_OBTIE_INDIC_CONST(psCodigoPais);


 --SE RECUPERAN LAS SECCIONES CUYO INDICADOR DE EFECTIVIDAD DE CUMPLIMIENTO o
 --INDICADORE DE EFECTIVIDAD DE SUPERAR SEA IGUAL A 0 NULO
 FOR filaIngr IN c_LID_SECCI_INGRE_EFECT LOOP

 --TIPO ASIGNACION=04, Cumplimiento de Meta de Ingresos Efectivos
 IF(lnTotalCumplimiento > 0) THEN
 IF (filaIngr.IND_EFEC_CUMP IS NULL OR filaIngr.IND_EFEC_CUMP = '0') THEN
 IF (filaIngr.VAL_INGR_EFEC_REAL>= filaIngr.VAL_INGR_EFEC_OBJT) THEN

 IF(((lsIndicadorConstancia = '1') AND
 ((filaIngr.COD_CLIE_LIDE IS NOT NULL) AND
 LID_FN_OBTIE_ORDE_COMPRA_FACT1(filaIngr.COD_CLIE_LIDE, psCodigoPais, psPeriodoProceso) = 1))
 OR (lsIndicadorConstancia = '0')) THEN

 --RECUPERMAMOS EL FACTOR MULTIPLICADOR, PARA EL CASO DE META A CUMPLIR
 BEGIN
 SELECT A.VAL_FAC_MULT
 INTO lnFactorMultiplicador04
 FROM LID_FACTO_PUNTA_PROGR_LIDER A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_CONC = filaIngr.COD_CONC
 AND A.COD_PERI = psPeriodoProceso
 AND A.TIAP_COD_TIPO_ASIGN_PUNTA = '04';
 EXCEPTION
 WHEN OTHERS THEN
 RETURN;
 END;

 --CUMPLIO CON LA META
 UPDATE LID_SECCI_INGRE_EFECT L
 SET L.IND_EFEC_CUMP = '1',
 L.VAL_PUNT_CUMP = lnFactorMultiplicador04,
 L.FEC_EVAL = SYSDATE
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = psCodigoRegion
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;

 --SI CUMPLIO CON EL OBJETIVO, SE LE ABONA PUNTOS, A LA LIDER DE LA SECCION
 lnIdCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(filaIngr.COD_CLIE_LIDE);

 --Obtenemos el Oid Concurso
 lnOidConcurso := LID_FN_OBTIE_OID_CONCU(psCodigoPais, filaIngr.COD_CONC);

 INSERT INTO INC_CUENT_CORRI_PUNTO P
 (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
 NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
 CLIE_OID_CLIE, PERD_OID_PERI, TMOV_OID_TIPO_MOVI,
 FEC_ULTI_ACTU, VAL_DESC)
 VALUES
 (INC_CUCP_SEQ.NEXTVAL, INC_CUCP_SEQ.NEXTVAL, lnFactorMultiplicador04,
 0, TO_DATE(psFechaProceso,'DD/MM/YYYY'), lnOidConcurso,
 lnIdCliente, lnIdPeriodo, '1',
 SYSDATE, 'Abono por Cumplir Meta de Ingresos Efectivos Sección ' || filaIngr.COD_SECC || ' al cierre de región');

 ELSE

 UPDATE LID_SECCI_INGRE_EFECT
 SET IND_EFEC_CUMP = '0',
 IND_NO_CONS = '1',
 FEC_EVAL = SYSDATE
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = psCodigoRegion
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;
 END IF;

 ELSE
 --NO CUMPLIO CON LA META
 UPDATE LID_SECCI_INGRE_EFECT
 SET IND_EFEC_CUMP = '0',
 FEC_EVAL = SYSDATE
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = psCodigoRegion
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;
 END IF;
 END IF;
 END IF;

 --TIPO ASIGNACION=05, Superacion de Meta de Ingresos Efectivos
 IF(lnTotalSuperacion > 0) THEN
 IF (filaIngr.IND_EFEC_SUPE IS NULL OR filaIngr.IND_EFEC_SUPE = '0') THEN
 IF (filaIngr.VAL_INGR_EFEC_REAL > filaIngr.VAL_INGR_EFEC_OBJT) THEN

 IF(((lsIndicadorConstancia = '1') AND
 ((filaIngr.COD_CLIE_LIDE IS NOT NULL) AND
 LID_FN_OBTIE_ORDE_COMPRA_FACT1(filaIngr.COD_CLIE_LIDE, psCodigoPais, psPeriodoProceso) = 1))
 OR (lsIndicadorConstancia = '0')) THEN

 --RECUPERMAMOS EL FACTOR MULTIPLICADOR, PARA EL CASO DE SI SE SUPERO LA META
 BEGIN
 SELECT A.VAL_FAC_MULT
 INTO lnFactorMultiplicador05
 FROM LID_FACTO_PUNTA_PROGR_LIDER A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_CONC = filaIngr.COD_CONC
 AND A.COD_PERI = psPeriodoProceso
 AND A.TIAP_COD_TIPO_ASIGN_PUNTA = '05';
 EXCEPTION
 WHEN OTHERS THEN
 RETURN;
 END;

 --CUMPLIO CON LA META
 UPDATE LID_SECCI_INGRE_EFECT
 SET IND_EFEC_SUPE = '1',
 VAL_PUNT_SUPE = lnFactorMultiplicador05 * (filaIngr.VAL_INGR_EFEC_REAL - filaIngr.VAL_INGR_EFEC_OBJT),
 FEC_EVAL = SYSDATE
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = psCodigoRegion
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;

 --SI CUMPLIO CON EL OBJETIVO, SE LE ABONA PUNTOS, A LA LIDER DE LA SECCION
 lnIdCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(filaIngr.COD_CLIE_LIDE);

 --Obtenemos el Oid Concurso
 lnOidConcurso := LID_FN_OBTIE_OID_CONCU(psCodigoPais, filaIngr.COD_CONC);

 INSERT INTO INC_CUENT_CORRI_PUNTO P
 (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
 NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
 CLIE_OID_CLIE, PERD_OID_PERI, TMOV_OID_TIPO_MOVI,
 FEC_ULTI_ACTU, VAL_DESC)
 VALUES
 (INC_CUCP_SEQ.NEXTVAL, INC_CUCP_SEQ.NEXTVAL, lnFactorMultiplicador05 * (filaIngr.VAL_INGR_EFEC_REAL - filaIngr.VAL_INGR_EFEC_OBJT),
 0, TO_DATE(psFechaProceso,'DD/MM/YYYY'), lnOidConcurso,
 lnIdCliente, lnIdPeriodo, '1',
 SYSDATE, 'Abono por Adicionales a la Meta de Ingresos Efectivos Sección ' || filaIngr.COD_SECC || ' al cierre de región');

 ELSE

 UPDATE LID_SECCI_INGRE_EFECT
 SET IND_EFEC_SUPE = '0',
 IND_NO_CONS = '1',
 FEC_EVAL = SYSDATE
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = psCodigoRegion
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;
 END IF;

 ELSE
 --NO CUMPLIO CON LA META
 UPDATE LID_SECCI_INGRE_EFECT
 SET IND_EFEC_SUPE = '0',
 FEC_EVAL = SYSDATE
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = psCodigoRegion
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;
 END IF;
 END IF;
 END IF;

 END LOOP;

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVALU_INGRE_EFECT_CIERE: ' || ls_sqlerrm);
END LID_PR_EVALU_INGRE_EFECT_CIERE;

/***************************************************************************
Descripcion : Procedimiemto Evaluar Ingresos Efectivos de la Sección
 al Cierre de Periodo

Fecha Creacion : 01/04/20089
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_EVALU_INGRE_EFECT_CIEPE(
 psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psPeriodoProceso VARCHAR2,
 psFechaProceso VARCHAR2,
 psTipoEvaluacion VARCHAR2)
IS
CURSOR c_LID_SECCI(vnIdPais NUMBER) IS
 SELECT reg.COD_REGI,
 zon.COD_ZONA,
 secc.COD_SECC,
 cli.COD_CLIE
 FROM ZON_SECCI secc,
 ZON_ZONA zon,
 ZON_REGIO reg,
 MAE_CLIEN cli
 WHERE secc.ZZON_OID_ZONA = zon.OID_ZONA
 AND zon.ZORG_OID_REGI = reg.OID_REGI
 AND secc.IND_BORR = 0
 AND secc.IND_ACTI = 1
 AND zon.IND_BORR = 0
 AND zon.IND_ACTI = 1
 AND reg.PAIS_OID_PAIS = vnIdPais
 AND cli.OID_CLIE = secc.CLIE_OID_CLIE;

CURSOR c_LID_SECCI_INGRE_EFECT IS
 SELECT A.COD_REGI,
 A.COD_ZONA,
 A.COD_SECC,
 A.COD_CLIE_LIDE,
 A.IND_EFEC_CUMP,
 A.IND_EFEC_SUPE,
 A.VAL_INGR_EFEC_OBJT,
 A.VAL_INGR_EFEC_REAL,
 A.COD_CONC
 FROM LID_SECCI_INGRE_EFECT A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_MARC = psCodigoMarca
 AND A.COD_PERI = psPeriodoProceso
 AND ((A.IND_EFEC_CUMP IS NULL OR A.IND_EFEC_CUMP = '0')
 OR (A.IND_EFEC_SUPE IS NULL OR A.IND_EFEC_SUPE = '0'));

 lnNumeroIngresosEfectivos LID_OBJET_ASIGN_PUNTA.VAL_OBJT%TYPE;
 lnNumeroIngresosReales LID_RECOM_LIDER.VAL_PUNT%TYPE;
 lnNumeroIngresosRealesAct LID_SECCI_INGRE_EFECT.VAL_INGR_EFEC_REAL%TYPE;
 lnFactorMultiplicador04 LID_FACTO_PUNTA_PROGR_LIDER.VAL_FAC_MULT%TYPE;
 lnFactorMultiplicador05 LID_FACTO_PUNTA_PROGR_LIDER.VAL_FAC_MULT%TYPE;

 lnIdCliente MAE_CLIEN.OID_CLIE%TYPE;
 lnIdPais NUMBER;
 lnIdMarca NUMBER;
 lnIdCanal NUMBER;
 lnIdPeriodo NUMBER;

 lnOidConcurso NUMBER;
 lsNumeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
 lnTotal NUMBER;
 lnTotalCumplimiento NUMBER;
 lnTotalSuperacion NUMBER;

 lnPuntajeSuperacion NUMBER;
 lnPuntajeAbonado NUMBER;
 lsIndicadorConstancia LID_PARAM.IND_CONS%TYPE;
 lsCodigoPeriodoAnterior SEG_PERIO_CORPO.COD_PERI%TYPE;
BEGIN
 --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL, ID REGION, ID PERIODO
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
 lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso, lnIdMarca, lnIdCanal);

 --VERIFICAMOS SI EXISTE TIPOS DE PUNTAJE PARA EL TIPO DE EVALUACION SELECCIONADO
 SELECT NVL(SUM(CASE WHEN COD_TIPO_ASIG_PUNT = '04' THEN 1 ELSE 0 END),0),
 NVL(SUM(CASE WHEN COD_TIPO_ASIG_PUNT = '05' THEN 1 ELSE 0 END),0)
 INTO lnTotalCumplimiento,
 lnTotalSuperacion
 FROM LID_TIPO_EVALU_ASIGN_PUNTA
 WHERE COD_TIPO_EVAL = psTipoEvaluacion;

 IF((lnTotalCumplimiento + lnTotalSuperacion) = 0) THEN
 RETURN;
 END IF;

 --RECUPERAMOS LA META PARA EL CUMPLIMIENTO DE INGRESOS EFECTIVOS
 BEGIN
 SELECT VAL_OBJT
 INTO lnNumeroIngresosEfectivos
 FROM LID_OBJET_ASIGN_PUNTA
 WHERE COD_PAIS = psCodigoPais
 AND COD_TIPO_ASIG_PUNT = '04'
 AND COD_PERI_INIC <= psPeriodoProceso
 AND COD_PERI_FINA >= psPeriodoProceso;
 EXCEPTION
 WHEN OTHERS THEN
 RETURN;
 END;

 FOR fila IN c_LID_SECCI(lnIdPais) LOOP

 --SE RECUPERA EL NUMERO DE RECOMENDADAS EFECTIVAS
 BEGIN
 SELECT COD_CONC, TOTAL
 INTO lsNumeroConcurso, lnNumeroIngresosReales
 FROM (
 SELECT COD_CONC, COUNT(*) TOTAL
 FROM LID_RECOM_LIDER A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_MARC = psCodigoMarca
 AND A.COD_TIPO = '01'
 AND A.COD_CLIE_LIDE = fila.COD_CLIE
 AND A.COD_PERI_EVAL = psPeriodoProceso
 AND A.INT_EFEC = 1
 GROUP BY A.COD_CONC)
 WHERE ROWNUM = 1
 ORDER BY TOTAL DESC;
 EXCEPTION
 WHEN OTHERS THEN
 lnNumeroIngresosReales := 0;

 --OBTENEMOS EL CODIGO DE PERIODO ANTERIOR AL PERIODO DE PROCESO
 lsCodigoPeriodoAnterior:= per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
 lnIdPais, lnIdMarca, lnIdCanal, -1);

 --OBTENEMOS EL CONCURSO ACTIVO DEL PERIODO ANTERIOR
 lsNumeroConcurso := LID_FN_OBTIE_NUM_CONC_PARA_GEN(psCodigoPais, psCodigoMarca, lsCodigoPeriodoAnterior);
 END;

 BEGIN
 SELECT VAL_INGR_EFEC_REAL
 INTO lnNumeroIngresosRealesAct
 FROM LID_SECCI_INGRE_EFECT A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_MARC = psCodigoMarca
 AND A.COD_PERI = psPeriodoProceso
 AND A.COD_REGI = fila.COD_REGI
 AND A.COD_ZONA = fila.COD_ZONA
 AND A.COD_SECC = fila.COD_SECC;

 lnTotal := 1;
 EXCEPTION
 WHEN OTHERS THEN
 lnTotal := 0;
 END;

 IF(lnTotal > 0) THEN
 IF (lnNumeroIngresosReales <> lnNumeroIngresosRealesAct) THEN
 UPDATE LID_SECCI_INGRE_EFECT L
 SET L.VAL_INGR_EFEC_REAL = lnNumeroIngresosReales,
 L.IND_EFEC_SUPE = NULL
 WHERE L.COD_PAIS = psCodigoPais
 AND L.COD_MARC = psCodigoMarca
 AND L.COD_PERI = psPeriodoProceso
 AND L.COD_REGI = fila.COD_REGI
 AND L.COD_ZONA = fila.COD_ZONA
 AND L.COD_SECC = fila.COD_SECC;
 END IF;
 ELSE

 INSERT INTO LID_SECCI_INGRE_EFECT
 (COD_PAIS, COD_PERI, COD_MARC,
 COD_REGI, COD_ZONA, COD_SECC,
 COD_CLIE_LIDE, VAL_INGR_EFEC_OBJT, VAL_INGR_EFEC_REAL,
 COD_CONC, IND_NO_CONS)
 VALUES
 (psCodigoPais, psPeriodoProceso, psCodigoMarca,
 fila.COD_REGI, fila.COD_ZONA, fila.COD_SECC,
 fila.COD_CLIE, lnNumeroIngresosEfectivos, lnNumeroIngresosReales,
 lsNumeroConcurso, '0');

 END IF;

 END LOOP;

 --RECUPERAMOS EL INDICADOR DE CONSTANCIA DEL PAIS
 lsIndicadorConstancia := LID_FN_OBTIE_INDIC_CONST(psCodigoPais);


 --SE RECUPERAN LAS SECCIONES CUYO INDICADOR DE EFECTIVIDAD DE CUMPLIMIENTO o
 --INDICADORE DE EFECTIVIDAD DE SUPERAR SEA IGUAL A 0 NULO
 FOR filaIngr IN c_LID_SECCI_INGRE_EFECT LOOP

 --TIPO ASIGNACION=04, Cumplimiento de Meta de Ingresos Efectivos
 IF(lnTotalCumplimiento > 0) THEN
 IF (filaIngr.IND_EFEC_CUMP IS NULL OR filaIngr.IND_EFEC_CUMP = '0') THEN
 IF (filaIngr.VAL_INGR_EFEC_REAL>= filaIngr.VAL_INGR_EFEC_OBJT) THEN

 IF(((lsIndicadorConstancia = '1') AND
 ((filaIngr.COD_CLIE_LIDE IS NOT NULL) AND
 LID_FN_PASO_PEDID(filaIngr.COD_CLIE_LIDE, psCodigoPais, psPeriodoProceso) = 1))
 OR (lsIndicadorConstancia = '0')) THEN

 --RECUPERMAMOS EL FACTOR MULTIPLICADOR, PARA EL CASO DE META A CUMPLIR
 BEGIN
 SELECT A.VAL_FAC_MULT
 INTO lnFactorMultiplicador04
 FROM LID_FACTO_PUNTA_PROGR_LIDER A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_CONC = filaIngr.COD_CONC
 AND A.COD_PERI = psPeriodoProceso
 AND A.TIAP_COD_TIPO_ASIGN_PUNTA = '04';
 EXCEPTION
 WHEN OTHERS THEN
 RETURN;
 END;

 --CUMPLIO CON LA META
 UPDATE LID_SECCI_INGRE_EFECT L
 SET L.IND_EFEC_CUMP = '1',
 L.VAL_PUNT_CUMP = lnFactorMultiplicador04,
 L.FEC_EVAL = SYSDATE,
 L.IND_NO_CONS = '0'
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = filaIngr.COD_REGI
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;

 --SI CUMPLIO CON EL OBJETIVO, SE LE ABONA PUNTOS, A LA LIDER DE LA SECCION
 lnIdCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(filaIngr.COD_CLIE_LIDE);

 --Obtenemos el Oid Concurso
 lnOidConcurso := LID_FN_OBTIE_OID_CONCU(psCodigoPais, filaIngr.COD_CONC);

 INSERT INTO INC_CUENT_CORRI_PUNTO P
 (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
 NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
 CLIE_OID_CLIE, PERD_OID_PERI, TMOV_OID_TIPO_MOVI,
 FEC_ULTI_ACTU, VAL_DESC)
 VALUES
 (INC_CUCP_SEQ.NEXTVAL, INC_CUCP_SEQ.NEXTVAL, lnFactorMultiplicador04,
 0, TO_DATE(psFechaProceso,'DD/MM/YYYY'), lnOidConcurso,
 lnIdCliente, lnIdPeriodo, '1',
 SYSDATE, 'Abono por Cumplir Meta de Ingresos Efectivos Sección ' || filaIngr.COD_SECC || ' al cierre de campaña');

 ELSE

 UPDATE LID_SECCI_INGRE_EFECT
 SET IND_EFEC_CUMP = '2',
 IND_NO_CONS = '1',
 FEC_EVAL = SYSDATE
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = filaIngr.COD_REGI
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;
 END IF;

 ELSE
 --NO CUMPLIO CON LA META
 UPDATE LID_SECCI_INGRE_EFECT
 SET IND_EFEC_CUMP = '2',
 FEC_EVAL = SYSDATE
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = filaIngr.COD_REGI
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;
 END IF;
 END IF;
 END IF;

 --TIPO ASIGNACION=05, Superacion de Meta de Ingresos Efectivos
 IF(lnTotalSuperacion > 0) THEN
 IF (filaIngr.IND_EFEC_SUPE IS NULL OR filaIngr.IND_EFEC_SUPE = '0') THEN
 IF (filaIngr.VAL_INGR_EFEC_REAL > filaIngr.VAL_INGR_EFEC_OBJT) THEN

 IF(((lsIndicadorConstancia = '1') AND
 ((filaIngr.COD_CLIE_LIDE IS NOT NULL) AND
 LID_FN_PASO_PEDID(filaIngr.COD_CLIE_LIDE, psCodigoPais, psPeriodoProceso) = 1))
 OR (lsIndicadorConstancia = '0')) THEN

 --RECUPERMAMOS EL FACTOR MULTIPLICADOR, PARA EL CASO DE SI SE SUPERO LA META
 BEGIN
 SELECT A.VAL_FAC_MULT
 INTO lnFactorMultiplicador05
 FROM LID_FACTO_PUNTA_PROGR_LIDER A
 WHERE A.COD_PAIS = psCodigoPais
 AND A.COD_CONC = filaIngr.COD_CONC
 AND A.COD_PERI = psPeriodoProceso
 AND A.TIAP_COD_TIPO_ASIGN_PUNTA = '05';
 EXCEPTION
 WHEN OTHERS THEN
 RETURN;
 END;

 --SI CUMPLIO CON EL OBJETIVO, SE LE ABONA PUNTOS, A LA LIDER DE LA SECCION
 lnIdCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(filaIngr.COD_CLIE_LIDE);

 --RECUPERAMOS PUNTAJE ABONADO ANTERIORMENTE
 SELECT NVL(SUM(NUM_PUNT),0)
 INTO lnPuntajeAbonado
 FROM INC_CUENT_CORRI_PUNTO
 WHERE CLIE_OID_CLIE = lnIdCliente
 AND COPA_OID_PARA_GRAL = lnOidConcurso
 AND PERD_OID_PERI = lnIdPeriodo
 AND VAL_DESC LIKE 'Abono por Adicionales a la Meta%';

 --CALCULAMOS EL PUNTAJE POR SUPERAR LA META
 lnPuntajeSuperacion := lnFactorMultiplicador05 * (filaIngr.VAL_INGR_EFEC_REAL - filaIngr.VAL_INGR_EFEC_OBJT);

 --CUMPLIO CON LA META
 UPDATE LID_SECCI_INGRE_EFECT
 SET IND_EFEC_SUPE = '1',
 VAL_PUNT_SUPE = lnPuntajeSuperacion,
 FEC_EVAL = SYSDATE,
 IND_NO_CONS = '0'
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = filaIngr.COD_REGI
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;

 --Obtenemos el Oid Concurso
 lnOidConcurso := LID_FN_OBTIE_OID_CONCU(psCodigoPais, filaIngr.COD_CONC);

 INSERT INTO INC_CUENT_CORRI_PUNTO P
 (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
 NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
 CLIE_OID_CLIE, PERD_OID_PERI, TMOV_OID_TIPO_MOVI,
 FEC_ULTI_ACTU, VAL_DESC)
 VALUES
 (INC_CUCP_SEQ.NEXTVAL, INC_CUCP_SEQ.NEXTVAL, lnPuntajeSuperacion - lnPuntajeAbonado,
 0, TO_DATE(psFechaProceso,'DD/MM/YYYY'), lnOidConcurso,
 lnIdCliente, lnIdPeriodo, '1',
 SYSDATE, 'Abono por Adicionales a la Meta de Ingresos Efectivos Sección ' || filaIngr.COD_SECC || ' al cierre de campaña');

 ELSE

 UPDATE LID_SECCI_INGRE_EFECT
 SET IND_EFEC_SUPE = '2',
 IND_NO_CONS = '1',
 FEC_EVAL = SYSDATE
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = filaIngr.COD_REGI
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;
 END IF;

 ELSE
 --NO CUMPLIO CON LA META
 UPDATE LID_SECCI_INGRE_EFECT
 SET IND_EFEC_SUPE = '2',
 FEC_EVAL = SYSDATE
 WHERE COD_PAIS = psCodigoPais
 AND COD_MARC = psCodigoMarca
 AND COD_PERI = psPeriodoProceso
 AND COD_REGI = filaIngr.COD_REGI
 AND COD_ZONA = filaIngr.COD_ZONA
 AND COD_SECC = filaIngr.COD_SECC;
 END IF;
 END IF;
 END IF;

 END LOOP;

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVALU_INGRE_EFECT_CIEPE: ' || ls_sqlerrm);
END LID_PR_EVALU_INGRE_EFECT_CIEPE;

/***************************************************************************
Descripcion : Recupera el Tipo de Proceso de Recomendacion activa

Fecha Creacion : 22/04/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_TIPO_PROCE_ACTIV(psCodigoPais VARCHAR2) RETURN VARCHAR2 IS
 lsTipoProceso LID_PARAM_PROCE_RECOM.TIP_PROC_RECO%TYPE;
BEGIN

 SELECT LID.TIP_PROC_RECO
 INTO lsTipoProceso
 FROM LID_PARAM_PROCE_RECOM LID
 WHERE LID.COD_PAIS = psCodigoPais
 AND LID.IND_ACTI = '1';

 RETURN lsTipoProceso;

EXCEPTION
 WHEN OTHERS THEN
 RETURN TIPO_PROCESO_RECOMENDADA;
END LID_FN_OBTIE_TIPO_PROCE_ACTIV;

/***************************************************************************
Descripcion : Obtenemos la campaña de asignacion de la unidad administrativa
 y respecto a su fecha desde

Fecha Creacion : 14/07/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_CAMPA_ASIGN(psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psUnidadAdministrativa VARCHAR2)
RETURN VARCHAR2 IS
 lnIdPais NUMBER;
 lnIdMarca NUMBER;
 lnIdCanal NUMBER;
 ldFechaDesde DATE;
 lsCodigoPeriodo SEG_PERIO_CORPO.COD_PERI%TYPE;
BEGIN
 --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

 --RECUPERAMOS LA FECHA DESDE DE LA UNIDAD ADMIISTRATIVA, DEL HISTORICO DE GERENTES
 SELECT TRUNC(FEC_DESD)
 INTO ldFechaDesde
 FROM ZON_HISTO_GEREN
 WHERE PAIS_OID_PAIS = lnIdPais
 AND MARC_OID_MARC = lnIdMarca
 AND CANA_OID_CANA = lnIdCanal
 AND UA = psUnidadAdministrativa
 AND FEC_HAST IS NULL;

 --OBTENEMOS EL CODIGO DE PERIODO QUE TENGA A LA FECHA DESDE DENTRO DE SU RANGO DE FECHAS
 SELECT COD_PERI
 INTO lsCodigoPeriodo
 FROM (
 SELECT cor.COD_PERI, cra.FEC_INIC
 FROM CRA_PERIO cra, SEG_PERIO_CORPO cor
 WHERE cra.PAIS_OID_PAIS = lnIdPais
 AND cra.MARC_OID_MARC = lnIdMarca
 AND cra.CANA_OID_CANA = lnIdCanal
 AND cra.FEC_INIC <= ldFechaDesde
 AND cra.FEC_FINA >= ldFechaDesde
 AND cra.PERI_OID_PERI = cor.OID_PERI
 ORDER BY cra.FEC_INIC ASC)
 WHERE ROWNUM = 1;

 RETURN lsCodigoPeriodo;

EXCEPTION
 WHEN OTHERS THEN
 RETURN NULL;
END LID_FN_OBTIE_CAMPA_ASIGN;

/***************************************************************************
Descripcion : Realizamos las diferentes validaciones para si se puede
 efectuar la asignacion de la lider a una respectiva seccion

Fecha Creacion : 15/07/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_VALID_ASIGN_LIDER_SECCI(psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoCliente VARCHAR2,
 psIndicadorReingreso VARCHAR2,
 psIndicadorNoValidaUnicoLider VARCHAR2,
 lnNumeroActivasFinalesZona NUMBER,
 lnPromedioActFinalesSeccion NUMBER)
RETURN VARCHAR2 IS
 lnIdPais NUMBER;
 lnIdMarca NUMBER;
 lnIdCanal NUMBER;

 lnIdCliente MAE_CLIEN.OID_CLIE%TYPE;
 lnTotalTipos NUMBER;
 lsUnidadAdministrativa VARCHAR2(15);
 lnMinimoCampanas NUMBER;
 ldFechaHasta DATE;

 lsCodigoPeriodoActual SEG_PERIO_CORPO.COD_PERI%TYPE;
 ldCodigoPeriodoHasta SEG_PERIO_CORPO.COD_PERI%TYPE;
 lnMinActivasFinalesZona NUMBER;
 lnMinActivasFinalesSeccion NUMBER;
 lnDiferenciaCampanas NUMBER;
 lsCodigoValidacion VARCHAR2(200);

 indicadorUnicoLiderSeccion LID_PARAM.IND_UNIC_LIDE_SECC%TYPE;

BEGIN
 -- OBTENEMOS EL INDICADOR DE UNICO LIDER EN SECCION DE LID_PARAM

 SELECT X.IND_UNIC_LIDE_SECC INTO indicadorUnicoLiderSeccion
 FROM LID_PARAM X
 WHERE X.COD_PAIS = psCodigoPais;



 --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
 lsCodigoValidacion := '0__OK';

 --(1) Validamos si existe el cliente
 lnIdCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoCliente, true);
 IF(lnIdCliente = -1) THEN
 lsCodigoValidacion := '1__ER';
 RETURN lsCodigoValidacion;
 END IF;

 --(2) Validamos si existe el cliente tiene tipoCliente : Consultora
 SELECT COUNT(*)
 INTO lnTotalTipos
 FROM MAE_CLIEN_TIPO_SUBTI sub, MAE_TIPO_CLIEN tip
 WHERE sub.CLIE_OID_CLIE = lnIdCliente
 AND sub.TICL_OID_TIPO_CLIE = tip.OID_TIPO_CLIE
 AND tip.COD_TIPO_CLIE = '02';

 IF(lnTotalTipos = 0) THEN
 lsCodigoValidacion := '2__ER';
 RETURN lsCodigoValidacion;
 END IF;

 --(3) Validamos si la consultora ya es lider de alguna seccion.
 IF(indicadorUnicoLiderSeccion = 1) THEN
 BEGIN
 SELECT uni_adm
 INTO lsUnidadAdministrativa
 FROM (
 SELECT sub.COD_SUBG_VENT || reg.COD_REGI || zon.COD_ZONA || sec.COD_SECC uni_adm
 FROM ZON_SECCI sec,
 ZON_ZONA zon,
 ZON_REGIO reg,
 ZON_SUB_GEREN_VENTA sub
 WHERE sec.ZZON_OID_ZONA = zon.oid_zona
 AND zon.ZORG_OID_REGI = reg.oid_regi
 AND reg.zsgv_oid_subg_vent = sub.oid_subg_vent
 AND zon.pais_oid_pais = lnIdPais
 AND zon.marc_oid_marc = lnIdMarca
 AND zon.cana_oid_cana = lnIdCanal
 AND sec.ind_acti = '1'
 AND sec.clie_oid_clie = lnIdCliente)
 WHERE ROWNUM = 1;
 EXCEPTION
 WHEN OTHERS THEN
 lsUnidadAdministrativa := NULL;
 END;

 IF(lsUnidadAdministrativa IS NOT NULL) THEN
 lsCodigoValidacion := '3__' || lsUnidadAdministrativa;
 RETURN lsCodigoValidacion;
 END IF;


 END IF;



 --(3) Validamos si la consultora ya es lider de alguna seccion. y si lo es le permite varios lideres en seccion
 IF(indicadorUnicoLiderSeccion = 0 AND psIndicadorNoValidaUnicoLider='N') THEN
 BEGIN
 SELECT uni_adm
 INTO lsUnidadAdministrativa
 FROM (
 SELECT sub.COD_SUBG_VENT || reg.COD_REGI || zon.COD_ZONA || sec.COD_SECC uni_adm
 FROM ZON_SECCI sec,
 ZON_ZONA zon,
 ZON_REGIO reg,
 ZON_SUB_GEREN_VENTA sub
 WHERE sec.ZZON_OID_ZONA = zon.oid_zona
 AND zon.ZORG_OID_REGI = reg.oid_regi
 AND reg.zsgv_oid_subg_vent = sub.oid_subg_vent
 AND zon.pais_oid_pais = lnIdPais
 AND zon.marc_oid_marc = lnIdMarca
 AND zon.cana_oid_cana = lnIdCanal
 AND sec.ind_acti = '1'
 AND sec.clie_oid_clie = lnIdCliente)
 WHERE ROWNUM = 1;
 EXCEPTION
 WHEN OTHERS THEN
 lsUnidadAdministrativa := NULL;
 END;

 IF(lsUnidadAdministrativa IS NOT NULL) THEN
 lsCodigoValidacion := '3B__' || lsUnidadAdministrativa;
 RETURN lsCodigoValidacion;
 END IF;

 END IF;




 --(4) Validamos si la Consultora no cumple minimo de campañas para reingreso
 IF(psIndicadorReingreso = 'N') THEN
 BEGIN
 SELECT VAL_NUME_MINI_CAMP
 INTO lnMinimoCampanas
 FROM ZON_PARAM_REING_CONSU
 WHERE PAIS_OID_PAIS = lnIdPais
 AND TIPO_DE_UA = 'SECCION';
 EXCEPTION
 WHEN OTHERS THEN
 lnMinimoCampanas := NULL;
 END;


 IF(lnMinimoCampanas IS NOT NULL) THEN
 --Obtenemos el periodo actual Maximo
 SELECT COD_PERI
 INTO lsCodigoPeriodoActual
 FROM (
 SELECT cor.COD_PERI
 FROM CRA_PERIO cra,
 SEG_PERIO_CORPO cor
 WHERE cra.PAIS_OID_PAIS = lnIdPais
 AND cra.MARC_OID_MARC = lnIdMarca
 AND cra.CANA_OID_CANA = lnIdCanal
 AND (cor.OID_PERI = cra.PERI_OID_PERI)
 AND cra.FEC_INIC <= TRUNC(SYSDATE)
 AND cra.FEC_FINA >= TRUNC(SYSDATE)
 ORDER BY cra.FEC_INIC DESC)
 WHERE ROWNUM = 1;

 --Obtenemos la mayor fecha hasta del historico de responsables para la consultora
 BEGIN
 SELECT MAX(ger.FEC_HAST)
 INTO ldFechaHasta
 FROM ZON_HISTO_GEREN ger
 WHERE ger.PAIS_OID_PAIS = lnIdPais
 AND ger.MARC_OID_MARC = lnIdMarca
 AND ger.CANA_OID_CANA = lnIdCanal
 AND ger.GERE = psCodigoCliente
 AND ger.FEC_HAST IS NOT NULL
 AND length(ger.UA) = 9; --Para secciones, la longitud del UA es 9

 --Obtenemos el mayor periodo del historico de responsable para la consultora
 SELECT COD_PERI
 INTO ldCodigoPeriodoHasta
 FROM (
 SELECT cor.COD_PERI, cra.FEC_INIC
 FROM CRA_PERIO cra, SEG_PERIO_CORPO cor
 WHERE cra.PAIS_OID_PAIS = lnIdPais
 AND cra.MARC_OID_MARC = lnIdMarca
 AND cra.CANA_OID_CANA = lnIdCanal
 AND cra.FEC_INIC <= TRUNC(ldFechaHasta)
 AND cra.FEC_FINA >= TRUNC(ldFechaHasta)
 AND cra.PERI_OID_PERI = cor.OID_PERI
 ORDER BY cra.FEC_INIC ASC)
 WHERE ROWNUM = 1;

 --Recuperamos la anterior campaña del periodo Hasta
 	SELECT COD_PERI
 INTO ldCodigoPeriodoHasta
 		FROM (
 		 SELECT B.OID_PERI, C.COD_PERI
 		 FROM CRA_PERIO A, cra_perio B, SEG_PERIO_CORPO C, SEG_MARCA M,
 SEG_CANAL S, SEG_PAIS P, SEG_PERIO_CORPO D
 		 WHERE B.PAIS_OID_PAIS = A.PAIS_OID_PAIS
 		 AND B.MARC_OID_MARC = A.MARC_OID_MARC
 		 AND B.CANA_OID_CANA = A.CANA_OID_CANA
 	 AND B.FEC_INIC < A.FEC_INIC
 		 AND C.OID_PERI = B.PERI_OID_PERI
 		 AND D.OID_PERI = A.PERI_OID_PERI
 		 AND A.MARC_OID_MARC = lnIdMarca
 		 AND A.CANA_OID_CANA = lnIdCanal
 		 AND A.PAIS_OID_PAIS = lnIdPais
 	 AND D.COD_PERI = ldCodigoPeriodoHasta
 		 ORDER BY B.FEC_INIC DESC
 		)
 		WHERE ROWNUM = 1;

 EXCEPTION
 WHEN OTHERS THEN
 ldCodigoPeriodoHasta := NULL;
 END;

 IF(ldCodigoPeriodoHasta IS NOT NULL) THEN
 SELECT COUNT(*) - 1
 INTO lnDiferenciaCampanas
 FROM SEG_PERIO_CORPO cor, CRA_PERIO cra, CRA_PERIO cra_inic,
 SEG_PERIO_CORPO cor_inic, CRA_PERIO cra_fina, SEG_PERIO_CORPO cor_fina
 WHERE cra.PERI_OID_PERI = cor.OID_PERI
 AND cra.PAIS_OID_PAIS = lnIdPais
 AND cra.MARC_OID_MARC = lnIdMarca
 AND cra.CANA_OID_CANA = lnIdCanal
 AND cra.fec_inic >= cra_inic.fec_inic
 AND cra_inic.pais_oid_pais = cra.pais_oid_pais
 AND cra_inic.marc_oid_marc = cra.marc_oid_marc
 AND cra_inic.cana_oid_cana = cra.cana_oid_cana
 AND cra_inic.peri_oid_peri = cor_inic.oid_peri
 AND cor_inic.cod_peri = ldCodigoPeriodoHasta
 AND cra.fec_inic <= cra_fina.fec_inic
 AND cra_fina.pais_oid_pais = cra.pais_oid_pais
 AND cra_fina.marc_oid_marc = cra.marc_oid_marc
 AND cra_fina.cana_oid_cana = cra.cana_oid_cana
 AND cra_fina.peri_oid_peri = cor_fina.oid_peri
 AND cor_fina.cod_peri = lsCodigoPeriodoActual;

 IF(lnDiferenciaCampanas < lnMinimoCampanas) THEN
 lsCodigoValidacion := '4__' || TO_CHAR(lnMinimoCampanas);
 RETURN lsCodigoValidacion;
 END IF;
 END IF;
 END IF;
 END IF;


 --RECUPERAMOS LOS MININOS ACTIVAS FINALES DE ZONA Y SECCION
 BEGIN
 SELECT MIN_ACTI_FINA_ZONA, MIN_ACTI_FINA_SECC
 INTO lnMinActivasFinalesZona, lnMinActivasFinalesSeccion
 FROM LID_PARAM
 WHERE COD_PAIS = psCodigoPais;
 EXCEPTION
 WHEN OTHERS THEN
 RAISE_APPLICATION_ERROR(-20123, 'NO SE ENCONTRO DATOS DE MINIMO DE ACTIVAS FINALES DE ZONA Y SECCION PARA EL PAIS');
 END;

 --(5) Validamos si la seccion cumple con el minimo de activas finales por Zona
 IF(lnNumeroActivasFinalesZona < lnMinActivasFinalesZona) THEN
 lsCodigoValidacion := '5__ER';
 RETURN lsCodigoValidacion;
 END IF;

 --(6) Validamos si la seccion cumple con el minimo de activas finales por Seccion
 IF(lnPromedioActFinalesSeccion < lnMinActivasFinalesSeccion) THEN
 lsCodigoValidacion := '6__ER';
 RETURN lsCodigoValidacion;
 END IF;

 lsCodigoValidacion := '0__' || TO_CHAR(lnIdCliente);
 RETURN lsCodigoValidacion;

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,150);
 RETURN '9__' || ls_sqlerrm;
END LID_FN_VALID_ASIGN_LIDER_SECCI;

/***************************************************************************
Descripcion : Recupera el Indicador de constancia

Fecha Creacion : 23/07/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_INDIC_CONST(psCodigoPais VARCHAR2) RETURN VARCHAR2 IS
 lsIndicadorConstancia LID_PARAM.IND_CONS%TYPE;
BEGIN

 SELECT IND_CONS
 INTO lsIndicadorConstancia
 FROM LID_PARAM
 WHERE COD_PAIS = psCodigoPais;

 RETURN lsIndicadorConstancia;
END LID_FN_OBTIE_INDIC_CONST;

/***************************************************************************
Descripcion : Recupera la informacion del mensaje a enviar a la Lider

Fecha Creacion : 17/09/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_INFOR_MENSA(psCodigoCliente VARCHAR2) RETURN VARCHAR2 IS
 lsInformacionMensaje VARCHAR2(1000);
 lnTotal NUMBER;

 CURSOR c_MENSAJES(lnCodigoCliente VARCHAR2) IS
 SELECT pun.NUM_CONC || '__' || pun.NOMB_CONC || '__' || TO_CHAR(pun.VAL_PUNT_ACUM)
 || '__' || TO_CHAR(pun.VAL_PUNT_CANJ) || '__' || TO_CHAR(pun.VAL_PUNT_DISP) MENSAJE
 FROM LID_MENSA_PUNTA_OBTEN pun
 WHERE COD_CLIE = lnCodigoCliente;

BEGIN
 SELECT COUNT(*)
 INTO lnTotal
 FROM LID_MENSA_PUNTA_OBTEN
 WHERE COD_CLIE = psCodigoCliente;

 IF(lnTotal = 1) THEN
 SELECT pun.NUM_CONC || '__' || pun.NOMB_CONC || '__' || TO_CHAR(pun.VAL_PUNT_ACUM)
 || '__' || TO_CHAR(pun.VAL_PUNT_CANJ) || '__' || TO_CHAR(pun.VAL_PUNT_DISP)
 INTO lsInformacionMensaje
 FROM LID_MENSA_PUNTA_OBTEN pun
 WHERE COD_CLIE = psCodigoCliente;
 ELSE
 lsInformacionMensaje := NULL;

 FOR fila IN c_MENSAJES(psCodigoCliente) LOOP
 IF (lsInformacionMensaje IS NULL) THEN
 lsInformacionMensaje := fila.MENSAJE;
 ELSE
 lsInformacionMensaje := lsInformacionMensaje || '||' || fila.MENSAJE;
 END IF;
 END LOOP;

 END IF;

 RETURN lsInformacionMensaje;
END LID_FN_OBTIE_INFOR_MENSA;

/***************************************************************************
Descripcion : Recupera el Oid Concurso

Fecha Creacion : 24/08/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_OID_CONCU(psCodigoPais VARCHAR2,
 psNumeroConcurso VARCHAR2) RETURN NUMBER IS
 lnOidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
 lnIdPais NUMBER;
BEGIN
 --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

 SELECT OID_PARA_GRAL
 INTO lnOidConcurso
 FROM INC_CONCU_PARAM_GENER
 WHERE PAIS_OID_PAIS = lnIdPais
 AND NUM_CONC = psNumeroConcurso;

 RETURN lnOidConcurso;
END LID_FN_OBTIE_OID_CONCU;

/***************************************************************************
Descripcion : Obtiene el numero de Promedios finales por Seccion

Fecha Creacion : 26/08/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_PROME_SECCI(pnOidZona NUMBER,
 pnOidSeccion NUMBER,
 pnOidUltimoPeriodo1 NUMBER,
 pnOidUltimoPeriodo2 NUMBER,
 pnOidUltimoPeriodo3 NUMBER) RETURN VARCHAR2 IS
 lnSumaActiFina11 NUMBER;
 lnSumaActiFina12 NUMBER;
 lnSumaActiFina13 NUMBER;

 lnTotal NUMBER;
 lnContador NUMBER;
 lnPromedio NUMBER;
 lsResultado VARCHAR2(10);
BEGIN
 --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL ULTIMO PERIODO
 SELECT sum(fue.num_acti_fina)
 INTO lnSumaActiFina11
 FROM int_fuent_ventas_real fue, zon_terri_admin adm
 WHERE fue.zzon_oid_zona = pnOidZona
 AND fue.terr_oid_terr = adm.terr_oid_terr
 AND adm.zscc_oid_secc = pnOidSeccion
 AND fue.perd_oid_peri = pnOidUltimoPeriodo1
 AND (pnOidUltimoPeriodo1 >= adm.PERD_OID_PERI_INIC or adm.PERD_OID_PERI_INIC is null)
 AND (pnOidUltimoPeriodo1 <= adm.PERD_OID_PERI_FINA or adm.PERD_OID_PERI_FINA is null);

 --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL PENULTIMO PERIODO
 SELECT sum(fue.num_acti_fina)
 INTO lnSumaActiFina12
 FROM int_fuent_ventas_real fue, zon_terri_admin adm
 WHERE fue.zzon_oid_zona = pnOidZona
 AND fue.terr_oid_terr = adm.terr_oid_terr
 AND adm.zscc_oid_secc = pnOidSeccion
 AND fue.perd_oid_peri = pnOidUltimoPeriodo2
 AND (pnOidUltimoPeriodo2 >= adm.PERD_OID_PERI_INIC or adm.PERD_OID_PERI_INIC is null)
 AND (pnOidUltimoPeriodo2 <= adm.PERD_OID_PERI_FINA or adm.PERD_OID_PERI_FINA is null);

 --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL ANTEPENULTIMO PERIODO
 SELECT sum(fue.num_acti_fina)
 INTO lnSumaActiFina13
 FROM int_fuent_ventas_real fue, zon_terri_admin adm
 WHERE fue.zzon_oid_zona = pnOidZona
 AND fue.terr_oid_terr = adm.terr_oid_terr
 AND adm.zscc_oid_secc = pnOidSeccion
 AND fue.perd_oid_peri = pnOidUltimoPeriodo3
 AND (pnOidUltimoPeriodo3 >= adm.PERD_OID_PERI_INIC or adm.PERD_OID_PERI_INIC is null)
 AND (pnOidUltimoPeriodo3 <= adm.PERD_OID_PERI_FINA or adm.PERD_OID_PERI_FINA is null);

 lnTotal := 0;
 lnContador := 0;
 IF(lnSumaActiFina11 IS NOT NULL) THEN
 lnContador := lnContador + 1;
 lnTotal := lnTotal + NVL(lnSumaActiFina11,0);
 END IF;
 IF(lnSumaActiFina12 IS NOT NULL) THEN
 lnContador := lnContador + 1;
 lnTotal := lnTotal + NVL(lnSumaActiFina12,0);
 END IF;
 IF(lnSumaActiFina13 IS NOT NULL) THEN
 lnContador := lnContador + 1;
 lnTotal := lnTotal + NVL(lnSumaActiFina13,0);
 END IF;

 --VERIFICAMOS SI ENCONTRO REGISTRO EN INF_FUENT_VENTAS_REAL
 IF(lnContador = 0) THEN
 lsResultado := '0';

 SELECT count(*)
 INTO lnPromedio
 FROM mae_clien_unida_admin ua,
 zon_terri_admin ta,
 zon_terri t,
 zon_secci s,
 zon_zona z,
 mae_clien c,
 mae_clien_datos_adici da
 WHERE ua.ind_acti = 1
 AND ua.perd_oid_peri_fin IS NULL
 AND ua.ztad_oid_terr_admi = ta.oid_terr_admi
 AND ta.ind_borr = 0
 AND ta.terr_oid_terr = t.oid_terr
 AND ta.zscc_oid_secc = s.oid_secc
 AND s.zzon_oid_zona = z.oid_zona
 AND c.oid_clie = ua.clie_oid_clie
 AND da.clie_oid_clie = ua.clie_oid_clie
 AND da.ind_acti = 1
 AND da.esta_oid_esta_clie IN (2,3,4,6,8)
 AND z.oid_zona = pnOidZona
 AND s.oid_secc = pnOidSeccion;
 ELSE
 lnPromedio := round(lnTotal / lnContador);
 lsResultado := '1';
 END IF;

 lsResultado := lsResultado || '_' || TO_CHAR(lnPromedio);

 RETURN lsResultado;

END LID_FN_OBTIE_PROME_SECCI;

/***************************************************************************
Descripcion : Obtiene el numero de Promedios finales por Zona

Fecha Creacion : 26/08/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_OBTIE_PROME_ZONA(pnOidZona NUMBER,
 pnOidPeriodo NUMBER) RETURN VARCHAR2 IS
 lnSumaActiFina1 NUMBER;
 lnPromedio NUMBER;
 lsResultado VARCHAR2(10);
BEGIN
 --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL ULTIMO PERIODO
 SELECT sum(fue.num_acti_fina)
 INTO lnSumaActiFina1
 FROM int_fuent_ventas_real fue
 WHERE fue.zzon_oid_zona = pnOidZona
 AND fue.perd_oid_peri = pnOidPeriodo;

 --VERIFICAMOS SI ENCONTRO REGISTRO EN INF_FUENT_VENTAS_REAL
 IF(lnSumaActiFina1 IS NULL) THEN
 lsResultado := '0';

 SELECT count(*)
 INTO lnPromedio
 FROM mae_clien_unida_admin ua,
 zon_terri_admin ta,
 zon_terri t,
 zon_secci s,
 zon_zona z,
 mae_clien c,
 mae_clien_datos_adici da
 WHERE ua.ind_acti = 1
 AND ua.perd_oid_peri_fin IS NULL
 AND ua.ztad_oid_terr_admi = ta.oid_terr_admi
 AND ta.ind_borr = 0
 AND ta.terr_oid_terr = t.oid_terr
 AND ta.zscc_oid_secc = s.oid_secc
 AND s.zzon_oid_zona = z.oid_zona
 AND c.oid_clie = ua.clie_oid_clie
 AND da.clie_oid_clie = ua.clie_oid_clie
 AND da.ind_acti = 1
 AND da.esta_oid_esta_clie IN (2,3,4,6,8)
 AND z.oid_zona = pnOidZona;
 ELSE
 lsResultado := '1';
 lnPromedio := lnSumaActiFina1;
 END IF;

 lsResultado := lsResultado || '_' || TO_CHAR(lnPromedio);

 RETURN lsResultado;

END LID_FN_OBTIE_PROME_ZONA;

/***************************************************************************
Descripcion : Recupera 1 si existen si su orden de compra se ha
 facturado para un determinado peridoo
 Recupera 0 si no existe

Fecha Creacion : 14/10/2009
Autor : Sergio Apaza
***************************************************************************/
FUNCTION LID_FN_PASO_PEDID(psCodigoCliente VARCHAR2,
 psCodigoPais VARCHAR2,
 psCodigoPeriodo VARCHAR2)
RETURN NUMBER IS
 lnCantidad NUMBER;

 lnIdPais NUMBER;
 lnIdMarca NUMBER;
 lnIdCanal NUMBER;
 lnIdPeriodo NUMBER;
BEGIN

 --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL, ID REGION, ID PERIODO
 lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA('T');
 lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
 lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnIdMarca, lnIdCanal);

 SELECT COUNT(1)
 INTO lnCantidad
 FROM PED_SOLIC_CABEC sc,
 PED_TIPO_SOLIC_PAIS tsp,
 PED_TIPO_SOLIC ts,
 MAE_CLIEN cli
 WHERE sc.pais_oid_pais = lnIdPais
 AND sc.perd_oid_peri = lnIdPeriodo
 AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
 AND ts.cod_tipo_soli = 'SOC'
 AND sc.grpr_oid_grup_proc = 5
 AND sc.fec_fact IS NOT NULL
 AND sc.clie_oid_clie = cli.oid_clie
 AND cli.cod_clie = psCodigoCliente;

 IF (lnCantidad >= 1) THEN
 RETURN 1;
 ELSE
 RETURN 0;
 END IF;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_PASO_PEDID: '||ls_sqlerrm);
 RETURN '';
END LID_FN_PASO_PEDID;


/**************************************************************************
Descripcion : Genera las solicitudes de premiación de los concursos
 con tipo de premiacion Bolsa de Premios.
Fecha Creacion : 19/12/2009
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Apaza

***************************************************************************/
PROCEDURE LID_PR_PREMI_CONCU_BOLSA_PREMI
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2)
IS
 lnOidPais SEG_PAIS.OID_PAIS%TYPE;
 lnOidMarca SEG_MARCA.OID_MARC%TYPE;
 lnOidCanal SEG_CANAL.OID_CANA%TYPE;
 lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;

 lnOidConcurso INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
 lsNumeroConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
 lsCodigoPeriodoIni SEG_PERIO_CORPO.COD_PERI%TYPE;
 lsCodigoPeriodoFin SEG_PERIO_CORPO.COD_PERI%TYPE;
 lnOidDirigido INC_CONCU_PARAM_GENER.DIRI_OID_DIRI%TYPE;
 lnOidTipoPrograma INC_CONCU_PARAM_GENER.ICTP_OID_TIPO_PROG%TYPE;
 lnOidPeriodoPremio INC_PARAM_GENER_PREMI.PERD_OID_PERI%TYPE;
 lnValPorcMaxDscto  INC_PARAM_GENER_PREMI.VAL_PORC_MAXI_DSTO%TYPE; 

 lnOidCuenta INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;
 lnOidOperacion BEL_OPERA.OID_OPER%TYPE;
 lnOidClaseSolicitud PED_CLASE_SOLIC.OID_CLAS_SOLI%TYPE;
 lnIndicadorOC PED_CLASE_SOLIC.IND_ORDE_COMP%TYPE;

 lnOidTipoSoliPais PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE;
 lnOidTipoPosi PED_TIPO_SOLIC_PROCE.TPOS_OID_TIPO_POSI%TYPE;
 lnOidSubTipoPosi PED_TIPO_SOLIC_PROCE.STPO_OID_SUBT_POSI%TYPE;
 lnOidAcceso PED_TIPO_SOLIC.ACCE_OID_ACCE%TYPE;
 lnOidSubAcceso PED_TIPO_SOLIC.SBAC_OID_SBAC%TYPE;
 lnOidTipoCliente PED_TIPO_SOLIC.TICL_OID_TIPO_CLIE%TYPE;
 lnOidActividad PED_TIPO_SOLIC_PAIS.CACT_OID_ACTI%TYPE;
 lnOidFormaPago PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO%TYPE;
 lnIndPedidoPrueba PED_TIPO_SOLIC_PAIS.IND_PEDI_PRUE%TYPE;
 lnIndPermitirUnion PED_TIPO_SOLIC_PAIS.IND_PERM_UNIO%TYPE;
 lnOidTipoCons PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS%TYPE;
 lnOidTipoDocumentoLegal PED_TIPO_SOLIC_PAIS.TIDO_OID_TIPO_DOCU%TYPE;
 lnOidSociedad PED_TIPO_SOLIC_PAIS.SOCI_OID_SOCI%TYPE;
 lnOidMoneda PED_TIPO_SOLIC_PAIS.MONE_OID_MONE%TYPE;

 lnOidEstadoSolicitud PED_ESTAD_SOLIC.OID_ESTA_SOLI%TYPE;
 lnOidEstadoPosicion PED_ESTAD_POSIC.OID_ESTA_POSI%TYPE;
 lnOidProceso PED_SECUE_PROCE.PROC_OID_PROC%TYPE;
 lnTipoCambio PRE_MATRI_FACTU_CABEC.VAL_TIPO_CAMB%TYPE;
 lnOidTipoDespacho PED_TIPO_DESPA.OID_TIPO_DESP%TYPE;
 lnIndCronograma PED_TIPO_DESPA.IND_CRON%TYPE;
 lnOidAlmacen BEL_ALMAC.OID_ALMA%TYPE;
 lsCodigoPeriodoSig SEG_PERIO_CORPO.COD_PERI%TYPE;
 lnOidPeriodoDespacho CRA_PERIO.OID_PERI%TYPE;

 lnOidSubTipoCliente MAE_CLIEN_TIPO_SUBTI.SBTI_OID_SUBT_CLIE%TYPE;
 lnOidClienteDir MAE_CLIEN_DIREC.OID_CLIE_DIRE%TYPE;
 lnOidValEstruGeopo ZON_VALOR_ESTRU_GEOPO.OID_VALO_ESTR_GEOP%TYPE;

 lnOidTipoDocumento MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU%TYPE;
 lnOidTerriAdmin ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE;
 lnOidTerritorio ZON_TERRI.OID_TERR%TYPE;
 lnOidSeccion ZON_SECCI.OID_SECC%TYPE;
 lnOidZona ZON_ZONA.OID_ZONA%TYPE;
 lnOidRegion ZON_REGIO.OID_REGI%TYPE;
 lnOidSolicitud PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;

 lbPrimeraVez BOOLEAN;
 lnContadorPosiciones NUMBER;
 lsConsulta VARCHAR2(100);
 ldFechaProgFacturacion DATE;
 lnPuntaje INC_CUENT_CORRI_PUNTO.NUM_PUNT%TYPE;
 lnMontoMaxDscto NUMBER;
 lnDsctoAplicar    NUMBER;
 lnDsctoTotAplicar NUMBER;

 lsCodigoSubAcceso SEG_SUBAC.COD_SBAC%TYPE;
 lnNumeroSolicitud PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
 lnSolicitudFormato PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;

 lnMontoMinimoConcurso INC_REQUI_PREMI.VAL_MONT_MINI_CONC%TYPE;
 lnNumeroPedidos INC_REQUI_PREMI.NUM_PEDI%TYPE;
 lnCuotaIngreso INC_REQUI_PREMI.VAL_CUOT_INGR%TYPE;
 lnIndicadorPedido INC_REQUI_PREMI.IND_PEDI_PERI%TYPE;
 lnMontoMinimoPedido INC_REQUI_PREMI.VAL_MONT_MINI_PEDI%TYPE;
 lnIndpremioWEB      INC_REQUI_PREMI.IND_PRIO_EWEB%TYPE;

 lnValPart1 NUMBER;
 lnValPart2 NUMBER;
 lnValPart3 NUMBER;

 lbValMontoMinimoConcurso BOOLEAN;
 lbValNumeroPedidos BOOLEAN;
 lbValCuotaIngreso BOOLEAN;
 lbValIndicadorPedido BOOLEAN;
 lbValIndicadorPedidoAux BOOLEAN;
 lbValParticipante BOOLEAN;

 lnMontoPedido NUMBER;
 lnTotalPedidos NUMBER;
 lnTotalPuntaje NUMBER;
 lnTotalErrores NUMBER;

 lnExistenConcurso NUMBER;
 lnCodigoConcurso INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;

 lsCodigoClienteSeg MAE_CLIEN.COD_CLIE%TYPE;
 lnOidSolicitudSeg PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;

 lnSaldoActual NUMBER;
 lsIndRechazo  BAS_PARAM_PAIS.VAL_PARA%TYPE;
 lspremiopend  NUMBER;

CURSOR c_Concursos IS
SELECT con.OID_PARA_GRAL, con.NUM_CONC
 FROM INC_CONCU_PARAM_GENER con, INC_PARAM_GENER_PREMI pre, INC_REQUI_PREMI req,
 CRA_PERIO crai, SEG_PERIO_CORPO cori, CRA_PERIO craf, SEG_PERIO_CORPO corf
 WHERE con.ind_acti = '1'
 AND con.oid_para_gral = pre.copa_oid_para_gral
 AND con.oid_para_gral = req.copa_oid_para_gral
 AND pre.tprm_oid_tipo_pion = 1
 AND pre.perd_oid_peri_inic = crai.oid_peri
 AND crai.peri_oid_peri = cori.oid_peri
 AND pre.perd_oid_peri = craf.oid_peri
 AND craf.peri_oid_peri = corf.oid_peri
 AND psCodigoPeriodo >= cori.cod_peri
 AND psCodigoPeriodo <= corf.cod_peri
 AND ((select count(1) from INC_CONCU_CAMPA_DESPA a
           where a.num_conc=con.num_conc)=0
            or
            psCodigoPeriodo in
               (select b.cod_peri from INC_CONCU_CAMPA_DESPA b
                where b.num_conc=con.num_conc));


CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER, oidConcurso NUMBER) IS
 SELECT psc.OID_SOLI_CABE,
 psc.CLIE_OID_CLIE,
 cli.COD_CLIE,
 psc.PERD_OID_PERI,
 psc.VAL_PREC_CATA_TOTA_LOCA,
 psc.VAL_BASE_FLET_LOCA
 FROM PED_SOLIC_CABEC psc,
 PED_TIPO_SOLIC_PAIS tsp,
        PED_TIPO_SOLIC ts,
 MAE_CLIEN cli
  WHERE psIndicadorProceso = PROCESO_GP4
    AND psc.PAIS_OID_PAIS = oidPais
 AND psc.PERD_OID_PERI = oidPeriodo
 AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
 AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
    AND ts.COD_TIPO_SOLI = 'SOC'
 AND psc.CLIE_OID_CLIE = cli.OID_CLIE
    AND psc.FEC_FACT IS NULL
    AND psc.GRPR_OID_GRUP_PROC = 4
    AND EXISTS (SELECT 1
                  FROM INC_PREMI_ELEGI pre
                 WHERE pre.clie_oid_clie = psc.clie_oid_clie
                   AND pre.copa_oid_para_gral = oidConcurso
                   AND (pre.perd_oid_peri_iatn is null or psCodigoPeriodo>=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn))
                   AND pre.ind_pend = 1 )
 UNION
 SELECT NULL OID_SOLI_CABE,
        pre.CLIE_OID_CLIE,
        cli.COD_CLIE,
        oidPeriodo PERD_OID_PERI,
        0 VAL_PREC_CATA_TOTA_LOCA,
        0 VAL_BASE_FLET_LOCA
   FROM INC_PREMI_ELEGI pre,
        MAE_CLIEN cli,
        MAE_CLIEN_UNIDA_ADMIN adm,
        ZON_TERRI_ADMIN tad,
        ZON_SECCI sec,
        ZON_ZONA zon
  WHERE psIndicadorProceso = PROCESO_CIERRE_ZONA
    AND pre.CLIE_OID_CLIE = cli.OID_CLIE
    AND pre.COPA_OID_PARA_GRAL = oidConcurso
    AND (pre.perd_oid_peri_iatn is null or psCodigoPeriodo>=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn))
    AND pre.IND_PEND = 1
    AND adm.CLIE_OID_CLIE = cli.OID_CLIE
    AND adm.IND_ACTI = 1
    AND tad.OID_TERR_ADMI = adm.ZTAD_OID_TERR_ADMI
    AND sec.OID_SECC = tad.ZSCC_OID_SECC
    AND zon.OID_ZONA = sec.ZZON_OID_ZONA
    AND zon.COD_ZONA IN (SELECT DISTINCT Z.COD_ZONA
                                                                                FROM ZON_ZONA Z,
                                                                                     FAC_PROGR_CIERR PRO,
                                                                                     CRA_PERIO CRA,
                                                                                     SEG_PERIO_CORPO cor
                                                                               WHERE z.ind_acti = 1
                                                                                 AND z.ind_borr = 0
                                                                                 AND z.Cod_Zona = pro.Cod_Zona
                                                                                 AND pro.Fec_Cier = TO_DATE(psFechaFacturacion ,'dd/MM/yyyy')
                                                                                 AND pro.tip_cier = 'Z'
                           AND pro.est_cier IN ('A','P')
                                                                                 AND cra.peri_oid_peri = cor.oid_peri
                                                                                 AND pro.CAM_PROC = cor.cod_peri
                           AND cor.COD_PERI = psCodigoPeriodo)
  GROUP BY pre.CLIE_OID_CLIE, cli.COD_CLIE;

 TYPE interfazPedidos IS RECORD
 (
 oidSolicitud PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
 oidCliente MAE_CLIEN.OID_CLIE%TYPE,
 codCliente MAE_CLIEN.COD_CLIE%TYPE,
 oidPeriodo PED_SOLIC_CABEC.PERD_OID_PERI%TYPE,
 montoPedido PED_SOLIC_CABEC.VAL_PREC_CATA_TOTA_LOCA%TYPE,
 montobasef  PED_SOLIC_CABEC.VAL_BASE_FLET_LOCA%TYPE
 );

 TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
 interfazRecordN interfazPedidosTab;

 lnCantidadPedidos       PED_SOLIC_CABEC_ACUM2.VAL_CANT_PEDI%TYPE;
 lnMontoTotal            PED_SOLIC_CABEC_ACUM2.VAL_MONT_TOTA%TYPE;

BEGIN
 --Recuperamos el oid Pais,Marca,Canal,Periodo
 lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
 lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

 lbPrimeraVez := TRUE;
 lnTotalErrores := 0;

 IF(psIndicadorProceso = PROCESO_CIERRE_REGION) THEN
   RETURN;
 END IF;
 IF(psIndicadorProceso = PROCESO_CIERRE_CAMPANA) THEN
   RETURN;
 END IF;

 --Recorremos los Concursos pa Bolsa de Premios
 FOR filaConcurso IN c_Concursos LOOP

 lnOidConcurso    := filaConcurso.Oid_Para_Gral;
 lnCodigoConcurso := filaConcurso.Num_Conc;

 BEGIN
 SELECT COUNT(1) INTO lnExistenConcurso FROM INC_CONCU_CAMPA_DESPA
        WHERE  COD_PAIS = psCodigoPais AND NUM_CONC = lnCodigoConcurso;
 EXCEPTION
    WHEN NO_DATA_FOUND THEN
        lnExistenConcurso := 0;
 END;

 IF lnExistenConcurso = 0 THEN
    lnExistenConcurso := 1;
 ELSE
 BEGIN
 SELECT COUNT(1) INTO lnExistenConcurso FROM INC_CONCU_CAMPA_DESPA
        WHERE  COD_PAIS = psCodigoPais AND NUM_CONC = lnCodigoConcurso AND COD_PERI = psCodigoPeriodo;
 EXCEPTION
    WHEN NO_DATA_FOUND THEN
        lnExistenConcurso := 0;
 END;

 END IF;

 IF lnExistenConcurso <> 0 THEN
 --Obtenemos el numero de Concurso
 SELECT gen.Num_Conc, gen.diri_oid_diri, cord.cod_peri,
 corh.cod_peri, gen.ICTP_OID_TIPO_PROG, pre.perd_oid_peri,
 req.Val_Mont_Mini_Conc, req.Num_Pedi, req.Val_Cuot_Ingr,
 req.Ind_Pedi_Peri, req.Val_Mont_Mini_Pedi,
 pre.val_porc_maxi_dsto, req.ind_prio_eweb
 INTO lsNumeroConcurso, lnOidDirigido, lsCodigoPeriodoIni,
 lsCodigoPeriodoFin, lnOidTipoPrograma, lnOidPeriodoPremio,
 lnMontoMinimoConcurso, lnNumeroPedidos, lnCuotaIngreso,
 lnIndicadorPedido, lnMontoMinimoPedido,
 lnValPorcMaxDscto, lnIndpremioWEB
 FROM INC_CONCU_PARAM_GENER gen,
 CRA_PERIO crad, SEG_PERIO_CORPO cord,
 CRA_PERIO crah, SEG_PERIO_CORPO corh,
 INC_PARAM_GENER_PREMI pre, INC_REQUI_PREMI req
 WHERE oid_para_gral = lnOidConcurso
 AND gen.perd_oid_peri_desd = crad.oid_peri
 AND crad.peri_oid_peri = cord.oid_peri
 AND gen.perd_oid_peri_hast = crah.oid_peri
 AND crah.peri_oid_peri = corh.oid_peri
 AND gen.oid_para_gral = pre.copa_oid_para_gral
 AND gen.oid_para_gral = req.copa_oid_para_gral;


 BEGIN

 --(1) PROCESAMOS A LOS CLIENTES
 OPEN c_Pedidos(lnOidPais, lnOidPeriodo, lnOidConcurso);
 LOOP

 FETCH c_Pedidos BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;

 IF interfazRecordN.COUNT > 0 THEN

 FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

 lsCodigoClienteSeg := interfazRecordN(x).codCliente;
 lnOidSolicitudSeg := nvl(interfazRecordN(x).oidSolicitud,0);

 lbValMontoMinimoConcurso := TRUE;
 lbValNumeroPedidos := TRUE;
 lbValCuotaIngreso := TRUE;
 lbValIndicadorPedido := TRUE;
 lbValIndicadorPedidoAux := TRUE;
 lnValPart1 := 0;
 lnValPart2 := 0;
 lnValPart3 := 0;
 lbValParticipante := TRUE;
 lsConsulta := 'Realizando Validaciones';

 --SE VALIDA SI EL CLIENTE CUMPLE CON LOS REQUISITOS DE PREMIACION
 --(a0) Valida si es participante

 SELECT COUNT(1)
 INTO lnValPart1
 FROM INC_CANDI_GANAD
 WHERE COPA_OID_PARA_GRAL = lnOidConcurso
 AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente;

 SELECT COUNT(1)
 INTO lnValPart2
 FROM inc_clien_recte
 WHERE COPA_OID_PARA_GRAL = lnOidConcurso
 AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
 AND nvl(ind_part,0)=1;

 SELECT COUNT(1)
 INTO lnValPart3
 from inc_pedid_concu_recom pedid, inc_clien_recte recte
 WHERE recte.oid_clie_rete=pedid.clr3_oid_clie_rete
       AND RECTE.COPA_OID_PARA_GRAL = lnOidConcurso
       AND RECTE.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
       AND pedid.clre_oid_clie_redo IS NULL;

 IF lnValPart1+lnValPart2+lnValPart3>0 THEN
   lbValParticipante := TRUE;
 ELSE
   lbValParticipante := FALSE;
 END IF;
 --(a) Valida Monto Minimo Concurso
 IF((lnMontoMinimoConcurso IS NOT NULL) AND (lnMontoMinimoConcurso <> 0)) THEN

 SELECT NVL(SUM(IMP_MONT),0)
 INTO lnMontoPedido
 FROM INC_SOLIC_CONCU_PUNTA
 WHERE COPA_OID_PARA_GRAL = lnOidConcurso
 AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente;

 IF(lnMontoPedido < lnMontoMinimoConcurso) THEN
 lbValMontoMinimoConcurso := FALSE;
 END IF;
 END IF;
 --(b) Valida Numero Pedidos
 IF((lnNumeroPedidos IS NOT NULL) AND (lnNumeroPedidos <> 0)) THEN

 SELECT COUNT(1)
 INTO lnTotalPedidos
 FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC_PAIS tsp, PED_TIPO_SOLIC sol,
 CRA_PERIO cra, SEG_PERIO_CORPO cor
 WHERE psc.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
 AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
 AND tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
 AND sol.COD_TIPO_SOLI = 'SOC'
 AND psc.GRPR_OID_GRUP_PROC = 5
 AND psc.FEC_FACT IS NOT NULL
 AND psc.PERD_OID_PERI = cra.OID_PERI
 AND cra.PERI_OID_PERI = cor.OID_PERI
 AND cor.COD_PERI >= lsCodigoPeriodoIni
 AND cor.COD_PERI <= lsCodigoPeriodoFin;

 IF(lnTotalPedidos < lnNumeroPedidos) THEN
 lbValNumeroPedidos := FALSE;
 END IF;
 END IF;
 --(c) Valida Cuota Ingreso
 IF((lnCuotaIngreso IS NOT NULL) AND (lnCuotaIngreso <> 0)) THEN

 SELECT NVL(SUM(NUM_PUNT),0)
 INTO lnTotalPuntaje
 FROM INC_CUENT_CORRI_PUNTO
 WHERE COPA_OID_PARA_GRAL = lnOidConcurso
 AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
 AND (VAL_DESC NOT LIKE '%Entrega de Premio%')
 AND NOT (PERD_OID_PERI=interfazRecordN(x).oidPeriodo AND TMOV_OID_TIPO_MOVI=1)
 ;
-- AND PERD_OID_PERI;

 IF(lnTotalPuntaje < lnCuotaIngreso) THEN
 lbValCuotaIngreso := FALSE;
 END IF;
 END IF;
 --(d) Valida Indicador Pedido Campaña Premiacion
 IF((lnIndicadorPedido IS NOT NULL) AND (lnIndicadorPedido <> 0)) THEN
   BEGIN
     SELECT VAL_CANT_PEDI, VAL_MONT_TOTA
       INTO lnCantidadPedidos, lnMontoTotal
       FROM PED_SOLIC_CABEC_ACUM2
      WHERE PERD_OID_PERI = lnOidPeriodo
        AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente;

     IF(lnCantidadPedidos < 1) THEN
       lbValIndicadorPedido := FALSE;
     END IF;
   EXCEPTION
     WHEN OTHERS THEN
       lbValIndicadorPedido := FALSE;
   END;


   IF(lbValIndicadorPedido) THEN
     IF (lnMontoTotal < lnMontoMinimoPedido) THEN
 lbValIndicadorPedido := FALSE;
 END IF;
   END IF;

   IF((NOT lbValIndicadorPedido) AND (psIndicadorProceso = PROCESO_CIERRE_ZONA)) THEN
 LID_PR_REGIS_INCID_INDEP(lsNumeroConcurso, interfazRecordN(x).codCliente,
 psCodigoPeriodo,'INC-3', SUBSTR('Descalificación: Requiere pedido'
         || ' en Campaña de Premiación. No se puede premiar al Cierre de Zona', 1, 100),
 interfazRecordN(x).oidSolicitud, lnOidConcurso);

 END IF;

 END IF;

 --Registramos en la tabla de Incidencias, las validaciones que no paso la solicitud del Cliente

 IF(NOT lbValParticipante) THEN
 LID_PR_REGIS_INCID_INDEP(lsNumeroConcurso, interfazRecordN(x).codCliente,
 psCodigoPeriodo,'INC-3', SUBSTR('Descalificación: No está participando', 1, 100),
 interfazRecordN(x).oidSolicitud, lnOidConcurso);
 END IF;

 IF(NOT lbValMontoMinimoConcurso) THEN
 LID_PR_REGIS_INCID_INDEP(lsNumeroConcurso, interfazRecordN(x).codCliente,
 psCodigoPeriodo,'INC-3', SUBSTR('Descalificación: No Paso Validación Monto Mínimo Concurso', 1, 100),
 interfazRecordN(x).oidSolicitud, lnOidConcurso);
 END IF;
 IF(NOT lbValNumeroPedidos) THEN
 LID_PR_REGIS_INCID_INDEP(lsNumeroConcurso, interfazRecordN(x).codCliente,
 psCodigoPeriodo,'INC-3', SUBSTR('Descalificación: No Paso Validación Número Pedidos', 1, 100),
 interfazRecordN(x).oidSolicitud, lnOidConcurso);
 END IF;
 IF(NOT lbValCuotaIngreso) THEN
 LID_PR_REGIS_INCID_INDEP(lsNumeroConcurso, interfazRecordN(x).codCliente,
 psCodigoPeriodo,'INC-3', SUBSTR('Descalificación: No Paso Validación Cuota Ingreso', 1, 100),
 interfazRecordN(x).oidSolicitud, lnOidConcurso);
 END IF;
 IF((NOT lbValIndicadorPedido) AND (psIndicadorProceso = PROCESO_GP4)) THEN
 LID_PR_REGIS_INCID_INDEP(lsNumeroConcurso, interfazRecordN(x).codCliente,
 psCodigoPeriodo,'INC-3', SUBSTR('Descalificación: No Paso Validación Indicador Pedido Campaña Premiación', 1, 100),
 interfazRecordN(x).oidSolicitud, lnOidConcurso);
 END IF;

 --
 lsIndRechazo := NVL(GEN_PKG_GENER.GEN_FN_PARAM_PAIS(psCodigoPais, 'INC', '007'),'0');

 IF lsIndRechazo = '1' THEN
   lspremiopend := 4;
 ELSE
   lspremiopend := 1;
 END IF  ;


 CASE
  WHEN (NOT lbValParticipante) THEN
   UPDATE INC_PREMI_ELEGI SET cod_moti_inva=9, fec_modi=sysdate, usu_modi=psCodigoUsuario, ind_pend = lspremiopend
          where oid_prem_eleg in (SELECT oid_prem_eleg
                  FROM INC_PREMI_ELEGI pre
                  WHERE pre.clie_oid_clie = interfazRecordN(x).oidCliente
                   AND pre.copa_oid_para_gral = lnOidConcurso
                   AND (pre.perd_oid_peri_iatn is null or psCodigoPeriodo>=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn))
                   AND pre.ind_pend = 1 );

  WHEN (NOT lbValNumeroPedidos) THEN
   UPDATE INC_PREMI_ELEGI SET cod_moti_inva=10, fec_modi=sysdate, usu_modi=psCodigoUsuario, ind_pend = lspremiopend
          where oid_prem_eleg in (SELECT oid_prem_eleg
                  FROM INC_PREMI_ELEGI pre
                  WHERE pre.clie_oid_clie = interfazRecordN(x).oidCliente
                   AND pre.copa_oid_para_gral = lnOidConcurso
                   AND (pre.perd_oid_peri_iatn is null or psCodigoPeriodo>=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn))
                   AND pre.ind_pend = 1 );

  WHEN (NOT lbValIndicadorPedido) THEN
   UPDATE INC_PREMI_ELEGI SET cod_moti_inva=11, fec_modi=sysdate, usu_modi=psCodigoUsuario, ind_pend = lspremiopend
          where oid_prem_eleg in (SELECT oid_prem_eleg
                  FROM INC_PREMI_ELEGI pre
                  WHERE pre.clie_oid_clie = interfazRecordN(x).oidCliente
                   AND pre.copa_oid_para_gral = lnOidConcurso
                   AND (pre.perd_oid_peri_iatn is null or psCodigoPeriodo>=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn))
                   AND pre.ind_pend = 1 );

   WHEN (NOT lbValMontoMinimoConcurso) THEN
   UPDATE INC_PREMI_ELEGI SET cod_moti_inva=12, fec_modi=sysdate, usu_modi=psCodigoUsuario, ind_pend = lspremiopend
          where oid_prem_eleg in (SELECT oid_prem_eleg
                  FROM INC_PREMI_ELEGI pre
                  WHERE pre.clie_oid_clie = interfazRecordN(x).oidCliente
                   AND pre.copa_oid_para_gral = lnOidConcurso
                   AND (pre.perd_oid_peri_iatn is null or psCodigoPeriodo>=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn))
                   AND pre.ind_pend = 1 );

  WHEN (NOT lbValCuotaIngreso) THEN
   UPDATE INC_PREMI_ELEGI SET cod_moti_inva=13, fec_modi=sysdate, usu_modi=psCodigoUsuario, ind_pend = lspremiopend
          where oid_prem_eleg in (SELECT oid_prem_eleg
                  FROM INC_PREMI_ELEGI pre
                  WHERE pre.clie_oid_clie = interfazRecordN(x).oidCliente
                   AND pre.copa_oid_para_gral = lnOidConcurso
                   AND (pre.perd_oid_peri_iatn is null or psCodigoPeriodo>=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn))
                   AND pre.ind_pend = 1 );
   ELSE NULL;
END CASE;


 IF(lbValMontoMinimoConcurso AND lbValNumeroPedidos AND lbValCuotaIngreso
 AND lbValIndicadorPedido AND lbValParticipante) THEN

 --SI ES PRIMERA VEZ, RECUPERAMOS INFORMACION DE LAS TABLAS NECESARIAS
 --PARA LA GENERACION DE LAS SOLICITUDES DE INCENTIVOS
 lsConsulta := 'Inicio';
 IF(lbPrimeraVez) THEN
 SELECT OID_OPER
 INTO lnOidOperacion
 FROM BEL_OPERA
 WHERE COD_OPER = 'INC030';

 SELECT OID_CLAS_SOLI, IND_ORDE_COMP
 INTO lnOidClaseSolicitud, lnIndicadorOC
 FROM PED_CLASE_SOLIC
 WHERE COD_CLAS_SOLI = 'I1';

 --Recuperamos datos relacionado al tipo de Solicitud de Incentivos
 SELECT tsp.OID_TIPO_SOLI_PAIS, pro.TPOS_OID_TIPO_POSI, pro.STPO_OID_SUBT_POSI,
 sol.ACCE_OID_ACCE, sol.SBAC_OID_SBAC, sol.TICL_OID_TIPO_CLIE,
 tsp.CACT_OID_ACTI, tsp.FOPA_OID_FORM_PAGO, tsp.IND_PEDI_PRUE,
 tsp.IND_PERM_UNIO, tsp.TSOL_OID_TIPO_CONS, tsp.TIDO_OID_TIPO_DOCU,
 tsp.SOCI_OID_SOCI, tsp.MONE_OID_MONE
 INTO lnOidTipoSoliPais, lnOidTipoPosi, lnOidSubTipoPosi,
 lnOidAcceso, lnOidSubAcceso, lnOidTipoCliente,
 lnOidActividad, lnOidFormaPago, lnIndPedidoPrueba,
 lnIndPermitirUnion, lnOidTipoCons, lnOidTipoDocumentoLegal,
 lnOidSociedad, lnOidMoneda
 FROM PED_TIPO_SOLIC_PROCE pro, PED_TIPO_SOLIC_PAIS tsp, PED_TIPO_SOLIC sol, MAE_TIPO_CLIEN tip
 WHERE tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
 AND pro.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
 AND sol.TICL_OID_TIPO_CLIE = tip.OID_TIPO_CLIE
 AND tip.Cod_Tipo_Clie = '02' --Consultora
 AND tsp.PAIS_OID_PAIS = lnOidPais
 AND sol.MARC_OID_MARC = lnOidMarca
 AND sol.CLSO_OID_CLAS_SOLI = lnOidClaseSolicitud
 AND pro.OPER_OID_OPER = lnOidOperacion;

 --Recupermos el Oid Estado de la Solicitud
 SELECT OID_ESTA_SOLI
 INTO lnOidEstadoSolicitud
 FROM PED_ESTAD_SOLIC
 WHERE COD_ESTA_SOLI = 'VA';

 --Recupermos el Oid Estado de la Posicion
 SELECT OID_ESTA_POSI
 INTO lnOidEstadoPosicion
 FROM PED_ESTAD_POSIC
 WHERE COD_ESTA_POSI = 'CO';

 --Recuperamos el Oid Secuencia de Procesos
 BEGIN
     SELECT PROC_OID_PROC
     INTO lnOidProceso
     FROM PED_SECUE_PROCE
     WHERE TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSoliPais
     AND GRPR_OID_GRUP_PROC = 3;
 EXCEPTION
   WHEN OTHERS THEN
       lnOidProceso:= 1;
 END;      

 --Recuperamos el Tipo de Cambio
 SELECT VAL_TIPO_CAMB
 INTO lnTipoCambio
 FROM PRE_MATRI_FACTU_CABEC
 WHERE PERD_OID_PERI = lnOidPeriodo;

 --Recuperamos el Oid Tipo Despacho y el indicador de Cronograma
 SELECT OID_TIPO_DESP, IND_CRON
 INTO lnOidTipoDespacho, lnIndCronograma
 FROM PED_TIPO_DESPA
 WHERE PAIS_OID_PAIS = lnOidPais
 AND IND_CRON = 1;

 --Recuperamos el Oid Asignacion Almacen
 SELECT ALMC_OID_ALMA
 INTO lnOidAlmacen
 FROM PED_ASIGN_ALMAC
 WHERE PAIS_OID_PAIS = lnOidPais
 AND MARC_OID_MARC = lnOidMarca
 AND SBAC_OID_SBAC = lnOidSubAcceso;

 --Obtenemos el Oid del Periodo de Despacho
 IF(psIndicadorProceso = 'P') THEN
 lsCodigoPeriodoSig := per_pkg_repor_perce.per_fn_obtie_perio(psCodigoPeriodo,
 lnOidPais, lnOidMarca, lnOidCanal, 1);

 lnOidPeriodoDespacho := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoSig, lnOidMarca, lnOidCanal);
 ELSE
 lnOidPeriodoDespacho := lnOidPeriodo;
 END IF;

 lbPrimeraVez := FALSE;
 END IF;

 --RECUPERAMOS LOS DATOS DEL CLIENTE
 --Recuperamos el OidSubTipo Cliente
 lsConsulta := 'SubTipoCliente';
 SELECT SBTI_OID_SUBT_CLIE
 INTO lnOidSubTipoCliente
 FROM MAE_CLIEN_TIPO_SUBTI
 WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
 AND TICL_OID_TIPO_CLIE = lnOidTipoCliente;

 --Recuperamos datos de la direccion del Cliente
 lsConsulta := 'DireccionCliente';
 SELECT OID_CLIE_DIRE,
 (CASE
 WHEN (SUBSTR(COD_UNID_GEOG,19,6) IS NULL) THEN
 (SELECT VEG.OID_VALO_ESTR_GEOP
 FROM ZON_VALOR_ESTRU_GEOPO VEG
 WHERE VEG.ORDE_1 = SUBSTR(MCD.COD_UNID_GEOG,0,6)
 AND VEG.ORDE_2 = SUBSTR(MCD.COD_UNID_GEOG,7,6)
 AND VEG.ORDE_3 = SUBSTR(MCD.COD_UNID_GEOG,13,6)
 AND VEG.ORDE_4 IS NULL
 )
 ELSE
 (
 SELECT VEG.OID_VALO_ESTR_GEOP
 FROM ZON_VALOR_ESTRU_GEOPO VEG
 WHERE VEG.ORDE_1 = SUBSTR(MCD.COD_UNID_GEOG,0,6)
 AND VEG.ORDE_2 = SUBSTR(MCD.COD_UNID_GEOG,7,6)
 AND VEG.ORDE_3 = SUBSTR(MCD.COD_UNID_GEOG,13,6)
 AND VEG.ORDE_4 = SUBSTR(MCD.COD_UNID_GEOG,19,6)
 )
 END) VEPO_OID_VALO_ESTR_GEOP
 INTO lnOidClienteDir, lnOidValEstruGeopo
 FROM MAE_CLIEN_DIREC MCD
 WHERE MCD.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
 AND MCD.IND_DIRE_PPAL = 1
 AND MCD.IND_ELIM = 0;

 --Recuperamos los datos del documento de identidad del Cliente
 lsConsulta := 'DocumentoCliente';
 SELECT TDOC_OID_TIPO_DOCU
 INTO lnOidTipoDocumento
 FROM
 (SELECT TDOC_OID_TIPO_DOCU
 FROM MAE_CLIEN_IDENT
 WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
 ORDER BY VAL_IDEN_DOCU_PRIN DESC)
 WHERE ROWNUM = 1;

 --Recuperamos los datos de la unidad administrativa del Cliente
 lsConsulta := 'TerritorioCliente';
 SELECT OID_TERR_ADMI, TERR_OID_TERR, OID_SECC,
 OID_ZONA, ZORG_OID_REGI
 INTO lnOidTerriAdmin, lnOidTerritorio, lnOidSeccion,
 lnOidZona, lnOidRegion
 FROM 
 (SELECT ter.OID_TERR_ADMI, ter.TERR_OID_TERR, sec.OID_SECC,
 zon.OID_ZONA, zon.ZORG_OID_REGI
 FROM MAE_CLIEN_UNIDA_ADMIN adm, ZON_TERRI_ADMIN ter, ZON_SECCI sec, ZON_ZONA zon
 WHERE adm.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
 AND (adm.PERD_OID_PERI_FIN IS NULL OR adm.IND_ACTI=1)
 AND adm.ZTAD_OID_TERR_ADMI = ter.OID_TERR_ADMI
 AND ter.ZSCC_OID_SECC = sec.OID_SECC
 AND sec.ZZON_OID_ZONA = zon.OID_ZONA
 ORDER BY adm.ind_acti DESC)
 WHERE ROWNUM = 1;

---------- Para los premios descuento se calcula el monto máximo que puede ser descontado ---------------
   lnMontoMaxDscto:=round(interfazRecordN(x).montobasef*lnValPorcMaxDscto/100,0);
   lnDsctoTotAplicar:=0;
   lnDsctoAplicar:=0;

 --Por cada cliente, se recupera los premios elegidos
  FOR y IN
 (SELECT PREM.OID_PREM_ELEG ,PREM.PANP_OID_PARA_NIVE_PREM, PREM.NUM_PREM, PREM.TOT_ATEN, PREM.PUNT_NIVE,
         PRIORWEB,
   --
   NVL((SELECT MAX(A.ind_tipo_prem) FROM INC_ARTIC_LOTE A, INC_LOTE_PREMI_ARTIC B, INC_PREMI_ARTIC C
    WHERE
        A.LOPA_OID_LOTE_PREM_ARTI=B.OID_LOTE_PREM_ARTI AND
        B.PRAR_OID_PREM_ARTI = C.OID_PREM_ARTI
        AND C.PANP_OID_PARA_NIVE_PREM = PREM.PANP_OID_PARA_NIVE_PREM
        AND A.IND_TIPO_PREM='D'),'A') TIPO_PREM,
   NVL((SELECT MAX(A.val_prem_desc) FROM INC_ARTIC_LOTE A, INC_LOTE_PREMI_ARTIC B, INC_PREMI_ARTIC C
    WHERE
        A.LOPA_OID_LOTE_PREM_ARTI=B.OID_LOTE_PREM_ARTI AND
        B.PRAR_OID_PREM_ARTI = C.OID_PREM_ARTI
        AND C.PANP_OID_PARA_NIVE_PREM = PREM.PANP_OID_PARA_NIVE_PREM
        AND A.IND_TIPO_PREM='D'),0) MONTO_DSCTO        
   --
  FROM
    (SELECT MIN(OID_PREM_ELEG) OID_PREM_ELEG ,PANP_OID_PARA_NIVE_PREM, NUM_PREM, COUNT(1) TOT_ATEN,
      DECODE(pnp.NUM_CANT_FIJA_PUNT, null, pnp.NUM_CANT_INIC_PUNT, pnp.NUM_CANT_FIJA_PUNT) PUNT_NIVE,
      (CASE WHEN PRE.CANA_ORIG='SOMOS B.' AND lnIndpremioWEB=1 THEN 1 ELSE 0 END) PRIORWEB
    FROM INC_PREMI_ELEGI PRE, INC_PARAM_NIVEL_PREMI pnp
    WHERE COPA_OID_PARA_GRAL = lnOidConcurso
       AND PRE.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
       AND PRE.IND_PEND = 1
       AND pnp.OID_PARA_NIVE_PREM = PRE.PANP_OID_PARA_NIVE_PREM
       AND (pre.perd_oid_peri_iatn is null or psCodigoPeriodo>=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn))
    GROUP BY PANP_OID_PARA_NIVE_PREM, NUM_PREM,
             DECODE(pnp.NUM_CANT_FIJA_PUNT, null, pnp.NUM_CANT_INIC_PUNT, pnp.NUM_CANT_FIJA_PUNT),
             (CASE WHEN PRE.CANA_ORIG='SOMOS B.' AND lnIndpremioWEB=1 THEN 1 ELSE 0 END)
    ) PREM
  ORDER BY PRIORWEB DESC, PREM.PUNT_NIVE DESC, PREM.TOT_ATEN*PREM.PUNT_NIVE DESC) LOOP

  BEGIN

  SELECT SUM(NUM_PUNT) -  (Y.TOT_ATEN*Y.PUNT_NIVE)
  INTO lnSaldoActual
  FROM INC_CUENT_CORRI_PUNTO
  WHERE COPA_OID_PARA_GRAL=lnOidConcurso AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
        AND NOT (PERD_OID_PERI=interfazRecordN(x).oidPeriodo AND TMOV_OID_TIPO_MOVI=1);
  EXCEPTION WHEN OTHERS THEN
    lnSaldoActual := -1;
  END;

  --  EXIT WHEN lnSaldoActual < 0;
  IF lnSaldoActual >= 0 THEN

   IF y.TIPO_PREM = 'A' THEN


       SELECT PED_SOCA_SEQ.NEXTVAL INTO lnOidSolicitud FROM DUAL;

        --Calculamos la Fecha Programada de Facturacion
       lsConsulta := 'Obteniendo FechaProgFacturacion';

       SELECT FEC_FINA
       INTO ldFechaProgFacturacion
       FROM CRA_PERIO
       WHERE OID_PERI = lnOidPeriodo;

        --Obtenemos el codigo de SubAcceso
       lsConsulta := 'Obteniendo CodigoSubAcceso';
       SELECT COD_SBAC
       INTO lsCodigoSubAcceso
       FROM SEG_SUBAC
       WHERE OID_SBAC = lnOidSubAcceso;

       --Obtenemos el Numero de Solicitud
       lsConsulta := 'Obteniendo NumeroSolicitud';
       lnNumeroSolicitud := STO_PKG_GENER.STO_FN_RESRV_SECUE_NSOLI(psCodigoPais,
                         'PED001',lsCodigoSubAcceso, 0);

       lnSolicitudFormato := to_char(SYSDATE, 'YY') || lpad(lnNumeroSolicitud, 8,
                              '0') + 1;

       --INSERTAMOS UN REGISTRO EN PED_SOLIC_CABEC
       lsConsulta := 'Insertando PedSolicCabec';
       INSERT INTO PED_SOLIC_CABEC
              (OID_SOLI_CABE, ACFI_OID_ACCE_FISI, ALMC_OID_ALMA,
               CLDI_OID_CLIE_DIRE, CLIE_OID_CLIE, CLIE_OID_CLIE_DEST,
               CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_RECE_FACT, CLIE_OID_CONS_ASOC,
               CLSO_OID_CLAS_SOLI, COPA_OID_PARA_GENE, ESSO_OID_ESTA_SOLI,
               FEC_CRON, FEC_PROG_FACT, FOPA_OID_FORM_PAGO,
               GRPR_OID_GRUP_PROC, IND_OC, IND_PEDI_PRUE,
               IND_PERM_UNIO_SOL, IND_TS_NO_CONSO, MODU_OID_MODU,
               MONE_OID_MONE, NUM_CLIEN, NUM_DOCU_ORIG,
               NUM_PREM, OPER_OID_OPER, PAIS_OID_PAIS,
               PERD_OID_PERI, PROC_OID_PROC, SBAC_OID_SBAC,
               SBTI_OID_SUBT_CLIE, SOCA_OID_DOCU_REFE, SOCI_OID_SOCI,
               TDOC_OID_TIPO_DOCU, TERR_OID_TERR, TICL_OID_TIPO_CLIE,
               TIDO_OID_TIPO_DOCU, TIDS_OID_TIPO_DESP, TSPA_OID_TIPO_SOLI_PAIS,
               TSPA_OID_TIPO_SOLI_PAIS_CONS, VAL_GLOS_OBSE, VAL_NUME_SOLI,
               VAL_USUA, VEPO_OID_VALO_ESTR_GEOP, VAL_TIPO_CAMB,
               ZZON_OID_ZONA, ZTAD_OID_TERR_ADMI, ICTP_OID_TIPO_PROG)
       VALUES
               (lnOidSolicitud, NULL, lnOidAlmacen,
               lnOidClienteDir, interfazRecordN(x).oidCliente, interfazRecordN(x).oidCliente,
               interfazRecordN(x).oidCliente, interfazRecordN(x).oidCliente, NULL,
               lnOidClaseSolicitud, lnOidConcurso, lnOidEstadoSolicitud,
               TRUNC(SYSDATE), ldFechaProgFacturacion, lnOidFormaPago,
               3, lnIndicadorOC, lnIndPedidoPrueba,
               lnIndPermitirUnion, 1, 13,
               lnOidMoneda, NULL, NULL,
               y.NUM_PREM, lnOidOperacion, lnOidPais,
               lnOidPeriodoDespacho, lnOidProceso, lnOidSubAcceso,
               lnOidSubTipoCliente, NULL, lnOidSociedad,
               lnOidTipoDocumento, lnOidTerritorio, lnOidTipoCliente,
               lnOidTipoDocumentoLegal, lnOidTipoDespacho, lnOidTipoSoliPais,
               lnOidTipoCons, decode(psIndicadorProceso,PROCESO_GP4,'PREMIO ELECTIVO CON PEDIDO','PREMIO ELECTIVO AL CIERRE DE ZONA'),
               lnSolicitudFormato,
               psCodigoUsuario, lnOidValEstruGeopo, DECODE(lnOidMoneda, NULL, 1, lnTipoCambio),
               lnOidZona, lnOidTerriAdmin, lnOidTipoPrograma);

       --Inicializamos el contador de Posiciones
       lnContadorPosiciones := 0;

       -- Versión nueva con condiciones de reemplazo 29/10/2013 --
       FOR z IN (
                 SELECT
                 CASE WHEN ral.oid_reem_arti_lote IS NULL THEN
                      al.PROD_OID_PROD ELSE ral.PROD_OID_PROD END PROD_OID_PROD,
                 CASE WHEN ral.oid_reem_arti_lote IS NULL THEN
                      al.IMP_PREC_PUBL else ral.IMP_PREC_PUBL end IMP_PREC_PUBL,
                 CASE WHEN ral.oid_reem_arti_lote IS NULL THEN
                      al.NUM_UNID ELSE al.num_unid*ral.num_unid END NUM_UNID,
                 CASE WHEN ral.oid_reem_arti_lote IS NULL THEN
                      al.COD_VENT_FICT ELSE ral.cod_vent_fict END COD_VENT_FICT,
                      DECODE(pnp.NUM_CANT_FIJA_PUNT, null, pnp.NUM_CANT_INIC_PUNT, pnp.NUM_CANT_FIJA_PUNT) PUNT_NIVE,
                      lpa.VAL_DESC_LOTE_PREM_ARTI DES_LOTE
                 FROM INC_PARAM_NIVEL_PREMI pnp,
                      INC_PREMI_ARTIC pa,
                      INC_LOTE_PREMI_ARTIC lpa,
                      INC_ARTIC_LOTE al,
                      (
                       select * from inc_reemp_artic_lote a where a.ind_acti=1 and a.ctre_oid_crit_reem = 3 and
                              inc_pkg_proce_incen.INC_FN_VALID_AMBIT_GEOGR_REEMP(nvl(a.comp_oid_reem_arti_lote,a.oid_reem_arti_lote),
                              interfazRecordN(x).oidCliente)='1' and
                              a.num_orde  = (select min(b.num_orde) from inc_reemp_artic_lote b where b.ind_acti=1
                              and b.ctre_oid_crit_reem = 3
                              and a.ARLO_OID_ARTI_LOTE = b.ARLO_OID_ARTI_LOTE)
                       ) ral
                 WHERE
                       pnp.OID_PARA_NIVE_PREM = y.PANP_OID_PARA_NIVE_PREM
                       AND lpa.NUM_PREM = y.NUM_PREM
                       AND pnp.OID_PARA_NIVE_PREM = pa.PANP_OID_PARA_NIVE_PREM
                       AND pa.OID_PREM_ARTI = lpa.PRAR_OID_PREM_ARTI
                       AND lpa.OID_LOTE_PREM_ARTI = al.LOPA_OID_LOTE_PREM_ARTI
                       AND al.oid_arti_lote = ral.arlo_oid_arti_lote(+)
                 ) LOOP
                 --  Fin de versión nueva

                 lsConsulta := 'Insertando PedSolicPosic';
                 INSERT INTO PED_SOLIC_POSIC
 	                 (OID_SOLI_POSI, SOCA_OID_SOLI_CABE, COD_POSI,
 	                 NUM_UNID_DEMA, NUM_UNID_POR_ATEN, TPOS_OID_TIPO_POSI,
 	                 PROD_OID_PROD, FOPA_OID_FORM_PAGO, VAL_CODI_VENT,
 	                 ESPO_OID_ESTA_POSI, STPO_OID_SUBT_POSI, VAL_CODI_VENT_FICT,
 	                 NUM_UNID_DEMA_REAL, VAL_PREC_CATA_UNIT_LOCA, VAL_PREC_CONT_UNIT_LOCA,
 	                 VAL_PREC_CATA_UNIT_DOCU, VAL_PREC_CONTA_UNIT_DOCU, VAL_PORC_DESC,
 	                 VAL_IMPO_DESC_UNIT_DOCU, OFDE_OID_DETA_OFER, SOPO_OID_SOLI_POSI,
 	                 NUM_UNID_COMPR, VAL_IMPO_DESC_UNIT_LOCA, NUM_PAGI_CATA, VAL_CATA)
                 VALUES
                   (PED_SOPO_SEQ.NEXTVAL, lnOidSolicitud, lnContadorPosiciones,
                   y.TOT_ATEN*z.NUM_UNID, y.TOT_ATEN*z.NUM_UNID, lnOidTipoPosi,
                   z.PROD_OID_PROD, NULL, NULL,
                   lnOidEstadoPosicion, lnOidSubTipoPosi, z.COD_VENT_FICT,
                   y.TOT_ATEN*z.NUM_UNID, 0, z.IMP_PREC_PUBL,
                   0, 0, NULL,
                   NULL, NULL, NULL,
                   y.TOT_ATEN*z.NUM_UNID, NULL, NULL, NULL);

                   lnContadorPosiciones := lnContadorPosiciones + 1;
                   lnPuntaje := z.PUNT_NIVE;

       END LOOP;
  
       --Calculamos el puntaje, que es el puntos en premio * numero de unidades atendidas
       lnPuntaje := lnPuntaje * y.TOT_ATEN;
       
    ELSE
       ------- Bloque nuevo para premio descuento ------  
       
       IF lnMontoMaxDscto < y.MONTO_DSCTO*y.TOT_ATEN THEN
         lnDsctoAplicar:= lnMontoMaxDscto ;
       ELSE
         lnDsctoAplicar:=   y.MONTO_DSCTO*y.TOT_ATEN;
       END IF;
       lnMontoMaxDscto := lnMontoMaxDscto - lnDsctoAplicar;
       lnDsctoTotAplicar:= lnDsctoTotAplicar + lnDsctoAplicar;
       --
       IF y.MONTO_DSCTO*y.TOT_ATEN >0 THEN
              lnPuntaje:=ROUND((y.PUNT_NIVE*y.TOT_ATEN)*(lnDsctoAplicar/(y.MONTO_DSCTO*y.TOT_ATEN)),2);
       ELSE
              lnPuntaje:=0;
       END IF;       
       
       ---- Fin de bloque nuevo premio descuento
    END IF;

    IF lnPuntaje=0 AND y.TIPO_PREM = 'D' THEN
    --
       UPDATE INC_PREMI_ELEGI
       SET cod_moti_inva=15, fec_modi=sysdate, usu_modi=psCodigoUsuario, ind_pend = lspremiopend
       WHERE COPA_OID_PARA_GRAL = lnOidConcurso
             AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
             AND PANP_OID_PARA_NIVE_PREM = y.PANP_OID_PARA_NIVE_PREM
             AND NUM_PREM = y.NUM_PREM
             AND IND_PEND = 1
             AND (perd_oid_peri_iatn is null or psCodigoPeriodo
            >=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn));
        
    ELSE  
        --Se actualiza los premios elegidos como Atendidos
        lsConsulta := 'Actualizando PremioElegido';
        UPDATE INC_PREMI_ELEGI
        SET IND_PEND = 0, COD_MOTI_INVA = NULL,
             fec_modi=sysdate, usu_modi=psCodigoUsuario,
             perd_oid_peri_iatn = lnOidPeriodo,
             cod_tipo_prem = y.TIPO_PREM,
             val_dsto_real = lnDsctoAplicar,
             num_punt_real = lnPuntaje,
             soca_oid_soli_cabe = lnOidSolicitudSeg,
             cana_orig = CASE WHEN lnOidSolicitudSeg>0 AND cana_orig IS NOT NULL
                         THEN HIP_PKG_CONSU.HIP_FN_OBTIE_ORIGE_PEDID_CONSO(lnOidSolicitudSeg,
                                                  lsCodigoClienteSeg)
                         END   -- Actualiza canal de ingreso sólo si no lo tiene.
        WHERE COPA_OID_PARA_GRAL = lnOidConcurso
              AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
              AND PANP_OID_PARA_NIVE_PREM = y.PANP_OID_PARA_NIVE_PREM
              AND NUM_PREM = y.NUM_PREM
              AND IND_PEND = 1
              AND (perd_oid_peri_iatn is null or 
                   psCodigoPeriodo>=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn));
     END IF;         

     IF lnPuntaje > 0 OR y.TIPO_PREM <> 'D' THEN
          --Se actualiza la cuenta corriente de puntos con el cargo por el despacho de premios
          SELECT INC_CUCP_SEQ.NEXTVAL INTO lnOidCuenta FROM DUAL;

          lsConsulta := 'Insertando CuentaCorriente';
          INSERT INTO INC_CUENT_CORRI_PUNTO
            (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
            NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
            CLIE_OID_CLIE, PERD_OID_PERI,
            TMOV_OID_TIPO_MOVI,
            FEC_ULTI_ACTU, VAL_DESC, DES_MOTI)
          VALUES
            (lnOidCuenta, lnOidCuenta, lnPuntaje*(-1),
             0, TRUNC(SYSDATE), lnOidConcurso,
             interfazRecordN(x).oidCliente, interfazRecordN(x).oidPeriodo,
             2, SYSDATE, 
             'Entrega de Premio',
             Decode(y.TIPO_PREM,'D','Premiación por Descuentos','Premiación'));

          --Se registra el cliente como Ganadora
          lsConsulta := 'Insertando IncGanadora';
          INSERT INTO INC_GANAD
                 (OID_GANA, FEC_OBTE, NUM_UNID,
                 IND_LIST_GANA, IND_DESC, IND_DESP,
                 CLIE_OID_CLIE, PERD_OID_PERI,
                 PANP_OID_PARA_NIVE_PREM, SOCA_OID_SOLI_CABE, IND_CLIE_BLOQ)
          VALUES
                 (INC_GANA_SEQ.NEXTVAL, TRUNC(SYSDATE), y.TOT_ATEN,
                 0, 0, 1,
                 interfazRecordN(x).oidCliente, interfazRecordN(x).oidPeriodo,
                 y.PANP_OID_PARA_NIVE_PREM, lnOidSolicitud, 0);

          --Si el periodo Proceso es igual al ultimo periodo de despacho (Periodo de INC_PARAM_GENER_PREMI)
          IF(lnOidPeriodo = lnOidPeriodoPremio) THEN
                lsConsulta := 'Actualizando IncGanadora';
                UPDATE INC_CANDI_GANAD
                SET VAL_REQU_PREM_SUPE = 1,
                    PERD_OID_PERI_EVAL = lnOidPeriodo
                WHERE COPA_OID_PARA_GRAL = lnOidConcurso
                    AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                    AND VAL_REQU_PREM_SUPE = 0;
          END IF;
     END IF;     
 ELSE  -- Nuevo
 --
    UPDATE INC_PREMI_ELEGI
     SET cod_moti_inva=8, fec_modi=sysdate, usu_modi=psCodigoUsuario, ind_pend = lspremiopend
      WHERE COPA_OID_PARA_GRAL = lnOidConcurso
        AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
        AND PANP_OID_PARA_NIVE_PREM = y.PANP_OID_PARA_NIVE_PREM
        AND NUM_PREM = y.NUM_PREM
        AND IND_PEND = 1
        AND (perd_oid_peri_iatn is null or psCodigoPeriodo
            >=fin_pkg_gener.fin_fn_obtie_codig_perio(perd_oid_peri_iatn));

 END IF;   -- Nuevo

 END LOOP;
--  Sección que graba el descuento
 IF lnDsctoTotAplicar>0 THEN
   BEGIN
     INSERT INTO inc_premi_descu_pedid
            (PAIS_COD_PAIS,
             NUM_CONC,
             CAM_PROC,
             COD_CLIE,
             MON_DESC,
             EST_DESC,
             SOCA_OID_SOLI_CABE,
             USU_CREA,
             FEC_CREA,
             USU_MODI,
             FEC_MODI, 	 
             EST_REG)
             VALUES 
            (psCodigoPais,
             lnCodigoConcurso,
             psCodigoPeriodo,
             lsCodigoClienteSeg,
             lnDsctoTotAplicar,
             'P',
             lnOidSolicitudSeg,
             psCodigoUsuario,
             SYSDATE,
             NULL,
             NULL,
             0);
        EXCEPTION WHEN dup_val_on_index THEN
             NULL;
   END;         
 END IF;      
 
-----------------------------------------

 END IF;

 END LOOP;
 END IF;
 EXIT WHEN c_Pedidos%NOTFOUND;
 END LOOP;
 CLOSE c_Pedidos;

 --Si Se proceso correctamente las solicitudes de los concursos, hacemos COMMIT
 COMMIT;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(SQLERRM,1,250);

 ROLLBACK;
 lnTotalErrores := lnTotalErrores + 1;

 LID_PR_REGIS_INCID_INDEP(lsNumeroConcurso, lsCodigoClienteSeg,
 psCodigoPeriodo,'INC-3', SUBSTR(lsConsulta || ' ,' || ls_sqlerrm, 1, 100),
 lnOidSolicitudSeg, lnOidConcurso);

 END;
 END IF;

 END LOOP;

 --Si Hubo Errores al procesar las solicitudes de los concursos, hacemos ROLLBACK
 IF(lnTotalErrores > 0) THEN
 RAISE_APPLICATION_ERROR(-20123, 'Hubo Problemas al Procesar las solicitudes de los Concursos de Bolsa de Premios');
 END IF;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 IF(lnTotalErrores > 0) THEN
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_PREMI_CONCU_BOLSA_PREMI: ' ||
 'Hubo Problemas al Procesar las solicitudes de los Concursos de Bolsa de Premios');
 ELSE
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_PREMI_CONCU_BOLSA_PREMI: ('|| ln_sqlcode || ')' || ls_sqlerrm);
 END IF;
END LID_PR_PREMI_CONCU_BOLSA_PREMI;


/**************************************************************************
Descripcion : Desvinculacion Automatica de Lideres
Fecha Creacion : 22/12/2009
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Apaza
Fecha Actualiza: 27/05/2011
Autor Actualiza: Carlos Diaz Valverde
***************************************************************************/
PROCEDURE LID_PR_DESVI_AUTOM_LIDER
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2)
IS
 lnOidPais SEG_PAIS.OID_PAIS%TYPE;
 lnOidMarca SEG_MARCA.OID_MARC%TYPE;
 lnOidCanal SEG_CANAL.OID_CANA%TYPE;
 lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;

 lnNumeroCampanas LID_PARAM.NUM_CAMP_SIN_PEDI%TYPE;
 lsCodigoPeriodoIni SEG_PERIO_CORPO.COD_PERI%TYPE;
 lnTotalPedidos NUMBER;
 lnOidHistoGeren ZON_HISTO_GEREN.OID_HIST_GERE%TYPE;

 lsPeriodoIngreso SEG_PERIO_CORPO.COD_PERI%TYPE;

CURSOR c_Lideres IS
 SELECT sec.OID_SECC,
 (SELECT X.COD_CLIE FROM MAE_CLIEN X WHERE X.OID_CLIE= sec.CLIE_OID_CLIE ) COD_CLIE,
 sec.CLIE_OID_CLIE,
 sub.COD_SUBG_VENT || reg.COD_REGI || zon.COD_ZONA || sec.COD_SECC UA
 FROM ZON_SECCI sec, ZON_ZONA zon, ZON_REGIO reg, ZON_SUB_GEREN_VENTA sub
 WHERE sec.ZZON_OID_ZONA = zon.OID_ZONA
 AND zon.ZORG_OID_REGI = reg.OID_REGI
 AND reg.ZSGV_OID_SUBG_VENT = sub.OID_SUBG_VENT
 AND sec.IND_ACTI = 1
 AND sec.IND_BORR = 0
 AND sec.CLIE_OID_CLIE IS NOT NULL
 --AND sec.CLIE_OID_CLIE in (8504,17085)--IS NOT NULL
 AND (((psIndicadorProceso = PROCESO_CIERRE_ZONA) AND
 (
 (zon.cod_zona = psCodigoZona) AND (zon.ind_acti = 1)
 )
 ) OR
 ((psIndicadorProceso = PROCESO_CIERRE_REGION) AND
 (
 (reg.cod_regi = psCodigoRegion) AND (reg.ind_acti = 1)
 )
 ) OR
 (psIndicadorProceso = PROCESO_CIERRE_CAMPANA)
 );

 TYPE interfazLider IS RECORD
 (
 oidSeccion ZON_SECCI.OID_SECC%TYPE,
 codCliente MAE_CLIEN.COD_CLIE%TYPE,
 oidCliente MAE_CLIEN.OID_CLIE%TYPE,
 ua VARCHAR2(9)
 );

 TYPE interfazLiderTab IS TABLE OF interfazLider;
 interfazRecordN interfazLiderTab;

 lnNumeroPeriodoCritico LID_PARAM.NUM_PERI_CRIT_DESV%TYPE;
 lnContNumeroPeriodoCriticos NUMBER;
 lnNumCampanha NUMBER;
 lnHayProceso NUMBER;

 lsCampanhaHasta SEG_PERIO_CORPO.COD_PERI%TYPE;
 lsCampanhaDesde SEG_PERIO_CORPO.COD_PERI%TYPE;

 lnNumPeriodo NUMBER;
 lnNumRestar NUMBER;

 lnDifeRango NUMBER;
 lnmaxNumPeriodo NUMBER;
 lnNumAnhioRetroc NUMBER;
 lnResul NUMBER;
 lnNumPeridoDesde NUMBER;

 lsAnioDesde VARCHAR2(4);

 lnMarcaDesvinviculacion NUMBER:=0;
 lnNumeroPeriodoCriticoTemp NUMBER;

 vsFechaProceso BAS_CTRL_FACT.FEC_PROC%TYPE;

BEGIN

 --Recuperamos el oid Pais,Marca,Canal,Periodo
 lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
 lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

 -- Recuperar Fecha Proceso Facturacion
 select    fec_proc
   into    vsFechaProceso
 from      BAS_CTRL_FACT
 where     cod_pais = psCodigoPais
   and     cod_peri = psCodigoPeriodo;


 --Se recupera el valor del parametro de Numero de Maximo de Campañas sin Pedidos
 --para las lideres
 BEGIN
   SELECT NUM_CAMP_SIN_PEDI,
   NUM_PERI_CRIT_DESV
   INTO lnNumeroCampanas,
   lnNumeroPeriodoCritico
   FROM LID_PARAM
   WHERE COD_PAIS = psCodigoPais;
   EXCEPTION
   WHEN OTHERS THEN
   lnNumeroCampanas := NULL;
   lnNumeroPeriodoCritico:= NULL;
 END;

 IF (((lnNumeroCampanas IS NULL) OR (lnNumeroCampanas=0) )AND
 ((lnNumeroPeriodoCritico IS NULL) OR (lnNumeroPeriodoCritico=0) )
 ) THEN
   RETURN;
 ELSE
   IF( lnNumeroCampanas IS NOT NULL) THEN
     IF(lnNumeroCampanas = 1) THEN
       lsCodigoPeriodoIni := psCodigoPeriodo;
     ELSE
       lsCodigoPeriodoIni := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
       lnOidPais, lnOidMarca, lnOidCanal, (-1)*(lnNumeroCampanas-1));
     END IF;
   END IF;

 END IF;


 lnNumeroPeriodoCriticoTemp:= lnNumeroPeriodoCritico;

 --(1) PROCESAMOS A LOS PREMIOS
 OPEN c_Lideres;
 LOOP

   FETCH c_Lideres BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;

   IF interfazRecordN.COUNT > 0 THEN

     FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

       lnMarcaDesvinviculacion:=0;
       --se excluye aquelas consultoras cuyo numero comprendido entre su campanha d eingreso
       -- y campanha actual es menor al num campa consecutivas sin peido
       -- ci + num > cp , es decir solo ingresan las q se encuntran en el rango

       lsPeriodoIngreso :=LID_FN_DEVUE_CAMPA_INGRE_LIDER(interfazRecordN(x).codCliente,interfazRecordN(x).ua);
       lnNumCampanha :=VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsPeriodoIngreso,psCodigoPeriodo,lnOidPais,lnOidMarca,lnOidCanal);

       IF(lnNumeroCampanas IS NOT NULL AND lnNumeroCampanas<> 0 AND lnNumCampanha>=lnNumeroCampanas) THEN

         --Para cada Lider se recupera el numero de Pedidos
         SELECT COUNT(1)
         INTO lnTotalPedidos
         FROM PED_SOLIC_CABEC psc,
         PED_TIPO_SOLIC_PAIS tsp,
         (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC') ts,
         CRA_PERIO cra,
         SEG_PERIO_CORPO cor
         WHERE psc.PAIS_OID_PAIS = lnOidPais
         AND psc.PERD_OID_PERI = cra.oid_peri
         AND cra.peri_oid_peri = cor.oid_peri
         AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
         AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
         AND psc.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
         AND psc.GRPR_OID_GRUP_PROC = 5
         AND psc.FEC_FACT IS NOT NULL
         AND cor.cod_peri >= lsCodigoPeriodoIni
         AND cor.cod_peri <= psCodigoPeriodo;


         IF(lnTotalPedidos = 0) THEN --Desvinculamos a la Lider

           --se procede a la desvinculacion
           UPDATE ZON_SECCI
           SET CLIE_OID_CLIE = NULL
           WHERE OID_SECC = interfazRecordN(x).oidSeccion;

           BEGIN

             SELECT ger.Oid_Hist_Gere
             INTO lnOidHistoGeren
             FROM ZON_HISTO_GEREN ger, SEG_MARCA mar,
             SEG_CANAL can, SEG_PAIS pai
             WHERE ger.PAIS_OID_PAIS = pai.OID_PAIS
             AND ger.MARC_OID_MARC = mar.OID_MARC
             AND ger.CANA_OID_CANA = can.OID_CANA
             AND pai.COD_PAIS = psCodigoPais
             AND mar.COD_MARC = psCodigoMarca
             AND can.COD_CANA = psCodigoCanal
             AND UA = interfazRecordN(x).ua
             AND ger.FEC_HAST IS NULL;

             UPDATE    ZON_HISTO_GEREN
                SET    FEC_HAST = vsFechaProceso,
                       PERD_OID_PERI_HAST = lnOidPeriodo,
                       IND_DESV_AUTO = 1,
                       USU_MODI = psCodigoUsuario,
                       FEC_MODI = SYSDATE
             WHERE     OID_HIST_GERE = lnOidHistoGeren;

             DELETE FROM MAE_CLIEN_CLASI
             WHERE OID_CLIE_CLAS IN
             (SELECT a.OID_CLIE_CLAS
             FROM mae_clien_clasi a,
             mae_clien_tipo_subti b ,
             MAE_TIPO_CLASI_CLIEN x ,
             MAE_CLASI y
             WHERE a.ctsu_oid_clie_tipo_subt = b.Oid_Clie_Tipo_Subt
             AND b.clie_oid_clie = interfazRecordN(x).oidCliente
             AND b.Ind_Ppal = 1
             AND a.tccl_oid_tipo_clasi = x.oid_tipo_clas
             AND x.cod_tipo_clas = '01'
             AND a.clas_oid_clas = y.oid_clas
             AND y.cod_clas = '01'
             AND b.sbti_oid_subt_clie = x.sbti_oid_subt_clie
             AND x.oid_tipo_clas = y.tccl_oid_tipo_clas);

             lnMarcaDesvinviculacion:=1;

           EXCEPTION
             WHEN OTHERS THEN
               NULL;
           END;

         END IF;

       END IF;

       -- SE EJECUTA LA SGTE ACTIVIDAD
       IF (lnMarcaDesvinviculacion =0) THEN --INGRESA SI NO HA SIDO MARCDA YA DESVINCULADA +

         --SE OBTINE EL MAX NUM PERIDO
         SELECT MAX(NUM_PERI) INTO lnmaxNumPeriodo
         FROM LID_PERIO_CORPO X;


         /*Si la campaña de proceso es igual a alguna de las campañas de Fin de Período */
         SELECT count(1) into lnHayProceso
         FROM LID_PERIO_CORPO X
         WHERE X.FIN_PERI = substr(psCodigoPeriodo,5,2)
         AND lnNumeroPeriodoCriticoTemp IS NOT NULL
         AND lnNumeroPeriodoCriticoTemp >0 ;

         IF(lnHayProceso > 0) THEN

           --se recupera el rango de numero de periodos de proceos
           --campanha Hasta
           SELECT substr(psCodigoPeriodo,1,4)|| X.FIN_PERI , X.NUM_PERI INTO lsCampanhaHasta, lnNumPeriodo
           FROM LID_PERIO_CORPO X
           WHERE X.FIN_PERI= substr(psCodigoPeriodo,5,2);

           --
           lnNumeroPeriodoCritico:=lnNumeroPeriodoCriticoTemp - 1;
           -- calculo de num peri desde
           --SELECT MOD(lnNumeroPeriodoCritico ,lnmaxNumPeriodo ) INTO FORM DUAL;
           lnNumRestar := MOD(lnNumeroPeriodoCritico ,lnmaxNumPeriodo );

           lnResul := lnNumPeriodo - lnNumRestar;

           IF( lnResul = 0 ) THEN
             lnNumPeridoDesde := lnmaxNumPeriodo;
           ELSE
             IF( lnResul < 0 ) THEN
               lnNumPeridoDesde := lnResul + lnmaxNumPeriodo;
             ELSE
               lnNumPeridoDesde := lnResul;
             END IF;
           END IF;

           --calculo del anho desde
           lnDifeRango := lnNumeroPeriodoCritico + lnmaxNumPeriodo - lnNumPeriodo;
           lnNumAnhioRetroc:=TRUNC(lnDifeRango/lnmaxNumPeriodo);

           if(lnNumAnhioRetroc <> 0) then
             lsAnioDesde:= TO_CHAR(TO_NUMBER(substr(psCodigoPeriodo,1,4)) - 1);
           else
             lsAnioDesde:=SUBSTR(psCodigoPeriodo,1,4);
           END IF;

           --Obteniedo campanha desde con los valores lsAnidoDesde y perioodoDesde
           SELECT lsAnioDesde || X.INI_PERI INTO lsCampanhaDesde
           FROM LID_PERIO_CORPO X
           WHERE X.NUM_PERI = lnNumPeridoDesde;

           --
           SELECT COUNT(1) INTO lnContNumeroPeriodoCriticos
           FROM LID_CRITI_PERIO Z
           WHERE Z.COD_CLIE_LIDE = interfazRecordN(x).codCliente
           AND Z.IND_PROD = 'C'
           AND Z.COD_PERI >= lsCampanhaDesde
           AND Z.COD_PERI <= lsCampanhaHasta;
           /*AND Z.VAL_ANIO >= lsAnioInicio
           AND Z.VAL_ANIO <= lsAnioFin
           AND Z.NUM_PERI >= lnNumPeriIni
           AND Z.NUM_PERI <= lnNumPeriFin ;*/


           IF(lnContNumeroPeriodoCriticos >= lnNumeroPeriodoCriticoTemp) THEN

             --se procede a la desvinculacion
             UPDATE ZON_SECCI
             SET CLIE_OID_CLIE = NULL
             WHERE OID_SECC = interfazRecordN(x).oidSeccion;

             BEGIN

               SELECT ger.Oid_Hist_Gere
               INTO lnOidHistoGeren
               FROM ZON_HISTO_GEREN ger, SEG_MARCA mar,
               SEG_CANAL can, SEG_PAIS pai
               WHERE ger.PAIS_OID_PAIS = pai.OID_PAIS
               AND ger.MARC_OID_MARC = mar.OID_MARC
               AND ger.CANA_OID_CANA = can.OID_CANA
               AND pai.COD_PAIS = psCodigoPais
               AND mar.COD_MARC = psCodigoMarca
               AND can.COD_CANA = psCodigoCanal
               AND UA = interfazRecordN(x).ua
               AND ger.FEC_HAST IS NULL;

               UPDATE    ZON_HISTO_GEREN
                  SET    FEC_HAST = vsFechaProceso,
                         PERD_OID_PERI_HAST = lnOidPeriodo,
                         IND_DESV_AUTO = 2,
                         USU_MODI = psCodigoUsuario,
                         FEC_MODI = SYSDATE
               WHERE     OID_HIST_GERE = lnOidHistoGeren;

             EXCEPTION
               WHEN OTHERS THEN
                 NULL;
             END;


             DELETE FROM MAE_CLIEN_CLASI
             WHERE OID_CLIE_CLAS IN
             (SELECT a.OID_CLIE_CLAS
             FROM mae_clien_clasi a,
             mae_clien_tipo_subti b ,
             MAE_TIPO_CLASI_CLIEN x ,
             MAE_CLASI y
             WHERE a.ctsu_oid_clie_tipo_subt = b.Oid_Clie_Tipo_Subt
             AND b.clie_oid_clie = interfazRecordN(x).oidCliente
             AND b.Ind_Ppal = 1
             AND a.tccl_oid_tipo_clasi = x.oid_tipo_clas
             AND x.cod_tipo_clas = '01'
             AND a.clas_oid_clas = y.oid_clas
             AND y.cod_clas = '01'
             AND b.sbti_oid_subt_clie = x.sbti_oid_subt_clie
             AND x.oid_tipo_clas = y.tccl_oid_tipo_clas);

           END IF; --FIN PERIODO CRITICOS

         END IF;--FIN PROCESP

       END IF;

     END LOOP;

   END IF;

   EXIT WHEN c_Lideres%NOTFOUND;
 END LOOP;
 CLOSE c_Lideres;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_DESVI_AUTOM_LIDER: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END LID_PR_DESVI_AUTOM_LIDER;

/**************************************************************************
Descripcion : Registra una incidencia producida de alguna validacion que no
 paso una determinada solicitud para cualquier de los procesos de Incentivos
Fecha Creacion : 19/12/2009
Parametros Entrada:
 psNumeroConcurso : Numero de Concurso
 psCodigoCliente : Codigo de Cliente
 psCodigoPeriodo : Codigo de periodo
 psCodigoProceso : Codigo de Proceso
 psMotivoIndicencia : Motivo de la Incidencia
 pnOidSolicitud : Oid Solicitud
 pnNumeroPremio : Numero de Premio
 psCodigoVenta : Codigo de Venta
 psDescripcionPremio : Descripcion Premio
 pnNumeroUnidades : Numero de Unidades del Premio

Autor : Sergio Apaza
***************************************************************************/
PROCEDURE LID_PR_REGIS_INCID_INDEP(psNumeroConcurso VARCHAR2,
 psCodigoCliente VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psCodigoProceso VARCHAR2,
 psMotivoIndicencia VARCHAR2,
 pnOidSolicitud NUMBER,
 pnOidConcurso NUMBER)
IS PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN

 INSERT INTO INC_INCID
 (NUM_INCI, COD_CONC, COD_CLIE,
 COD_PERI, COD_PROC, DES_MOTI,
 SOCA_OID_SOLI_CABE, COPA_OID_PARA_GRAL, FEC_ULTI_ACTU)
 VALUES
 (INC_SEQ_INCID.NEXTVAL, psNumeroConcurso, psCodigoCliente,
 psCodigoPeriodo, psCodigoProceso, psMotivoIndicencia,
 pnOidSolicitud, pnOidConcurso, SYSDATE);

 COMMIT;

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(SQLERRM, 1, 250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_REGIS_INCID_INDEP: ' || ls_sqlerrm);

END LID_PR_REGIS_INCID_INDEP;



/**************************************************************************
Descripcion : exisitiendo la matriz de facturacion inserta los productos en la canasta
 si exsiten aquellos en pre ofert , si no crea registros en pre_ofer cabcera y detall
 para ñluego insertrlos en la entidad canasa de productos
Fecha Creacion : 22/01/2010
Parametros Entrada:
 psCodigoPais : Código Pais
 psCodigoPeriodo : Codigo de periodo
 psCodigoSap : Codigo de Producto
 psCodigoOferta : Código Oferta
 psNumUnidades : Numero Unidades
 psPrecioContable : Precio Contable
 psIndicadorActivo : Indicador Activo
 psUsuario : Usuario
 psMensajeError : Mensaje Error

Autor : Sergio Buchelli

***************************************************************************/
PROCEDURE LID_PR_GENER_PRODU_CANAS(psCodigoPais VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psCodigoSap VARCHAR2,
 psCodigoOferta VARCHAR2,
 psCodigoVenta VARCHAR2,
 psNumUnidades VARCHAR2,
 psPrecioContable VARCHAR2,
 psIndicadorActivo VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2)
IS
 lnOidMatrizFacturacion PRE_MATRI_FACTU_CABEC.OID_CABE%TYPE;
 lnOidProducto MAE_PRODU.OID_PROD%TYPE;
 lnOidTipoOferta PRE_TIPO_OFERT.OID_TIPO_OFER%TYPE;
 lsCodigoVenta PRE_OFERT_DETAL.VAL_CODI_VENT%TYPE;
 lnOidFormaPago PRE_OFERT_DETAL.FOPA_OID_FORM_PAGO%TYPE;
 lnPrecioCatalogo PRE_OFERT_DETAL.IMP_PREC_CATA%TYPE;
 lnPrecioContable PRE_OFERT_DETAL.IMP_PREC_POSI%TYPE;

 lnOidEstrategia PRE_ESTRA.OID_ESTR%TYPE;
 lnOidTipoEstrategia PRE_ESTRA.TIES_OID_TIPO_ESTR%TYPE;
 lnCuvMenor LID_PARAM.CUV_MENO%TYPE;
 lnCuvMayor LID_PARAM.CUV_MAYO%TYPE;

 lnOidPais SEG_PAIS.OID_PAIS%TYPE;
 lnOidCanal SEG_ACCES.CANA_OID_CANA%TYPE;
 lnOidAcceso SEG_ACCES.OID_ACCE%TYPE;
 lnOidOfer PRE_OFERT.OID_OFER%TYPE;

BEGIN
psMensajeError:='';

 lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 --lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
 lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
 --OBTENEMOS EL EL OID DE LA MATRIZ DE FACTURACION
 				 SELECT X.OID_CABE
 INTO lnOidMatrizFacturacion
				 FROM SEG_PERIO_CORPO A,
				 	 CRA_PERIO B,
				 	 SEG_CANAL C,
				 	 SEG_MARCA D,
				 	 PRE_MATRI_FACTU_CABEC X
				 WHERE A.COD_PERI = psCodigoPeriodo AND
				 A.OID_PERI = B.PERI_OID_PERI AND
				 B.CANA_OID_CANA = C.OID_CANA AND
				 B.MARC_OID_MARC = D.OID_MARC
				 AND C.COD_CANA = 'VD'
				 AND D.COD_MARC = 'T'
				 AND X.PERD_OID_PERI = B.OID_PERI;

 --Obtenemos el oid producto
 SELECT X.OID_PROD
 INTO lnOidProducto
 FROM MAE_PRODU X
 WHERE X.COD_SAP = psCodigoSap;

 --Obtenemos el oid TipoOferta

 SELECT b.OID_TIPO_OFER
 INTO lnOidTipoOferta
 		FROM LID_TIPO_OFERT L,PRE_TIPO_OFERT B
 		WHERE L.COD_TIPO_OFER = B.COD_TIPO_OFER
			 AND L.COD_TIPO_OFER = psCodigoOferta;

 IF(psCodigoVenta IS NULL OR psCodigoVenta='')THEN --SE TRATA DE REGISTRO NUEVO

 BEGIN
 SELECT B.VAL_CODI_VENT,
 B.FOPA_OID_FORM_PAGO,
 B.IMP_PREC_CATA,
 B.IMP_PREC_POSI
 INTO lsCodigoVenta,lnOidFormaPago,lnPrecioCatalogo,lnPrecioContable
 FROM PRE_OFERT A ,PRE_OFERT_DETAL B
 WHERE A.MFCA_OID_CABE = lnOidMatrizFacturacion
 AND A.OID_OFER = B.OFER_OID_OFER
 AND B.IND_MATR_FACT_GENE =1
 AND B.PROD_OID_PROD = lnOidProducto
 AND B.TOFE_OID_TIPO_OFER = lnOidTipoOferta;
 EXCEPTION
 WHEN OTHERS THEN
 --Si no existe Registro entonces se cra la combinacion cosigo sap y tipo de oferta
 --Se recipera oidEstrategia y OidTipoEstaegia
 SELECT A.OID_ESTR, A.TIES_OID_TIPO_ESTR, X.CUV_MENO,X.CUV_MAYO
 INTO lnOidEstrategia, lnOidTipoEstrategia, lnCuvMenor, lnCuvMayor
 FROM PRE_ESTRA A , LID_PARAM X
 WHERE A.COD_ESTR = X.COD_ESTR_CANA;

 SELECT NVL(MAX(B.VAL_CODI_VENT),0)
 INTO lsCodigoVenta
 FROM PRE_OFERT A ,PRE_OFERT_DETAL B
 WHERE A.MFCA_OID_CABE = lnOidMatrizFacturacion
 AND A.OID_OFER = B.OFER_OID_OFER
 AND B.IND_CODI_VENT_GENE=1;

 lsCodigoVenta:=lsCodigoVenta+1;

 IF( lsCodigoVenta < lnCuvMenor ) THEN
 lsCodigoVenta:= lnCuvMenor;
 END IF;

 IF (lsCodigoVenta > lnCuvMayor) THEN
 psmensajeError:='El código de venta que se esta generando es incorrecto';
 RETURN ;
 END IF;

 --recuperamos el oid acceso
 SELECT Z.OID_ACCE into lnOidAcceso FROM SEG_ACCES Z WHERE Z.CANA_OID_CANA =lnOidCanal AND COD_ACCE='GZ';
 --obtenmo el oid_ofer a insertat
 SELECT PRE_OFER_SEQ.NEXTVAL INTO lnOidOfer FROM DUAL;
 --se crea un registro para PRE_OFERT
 INSERT INTO PRE_OFERT (
 OID_OFER,
 COES_OID_ESTR,
 NUM_OFER,
 NUM_ORDE,
 NUM_GRUP,
 NUM_GRUP_CNDT,
 NUM_GRUP_COND,
 VAL_COND_G1_CNDT,
 VAL_COND_G2_CNDO,
 NUM_PAQU,
 NUM_PRIM_POSI_RANK,
 NUM_ULTI_POSI_RANK,
 FOPA_OID_FORM_PAGO,
 ACCE_OID_ACCE,
 SBAC_OID_SBAC,
 ARGV_OID_ARGU_VENT,
 MFCA_OID_CABE,
 IND_CODI_VENT_GENE,
 IND_DESP_COMPL,
 IND_DESP_AUTO,
 IND_MATR_FACT_GENE,
 IND_RECU_OBLI,
 IND_REGI_ESTA_GENE,
 OCAT_OID_CATA)
 VALUES ( lnOidOfer,
 lnOidEstrategia,
 (SELECT NVL(MAX(NUM_OFER),0)+1 FROM PRE_OFERT Z WHERE Z.MFCA_OID_CABE =lnOidMatrizFacturacion AND COES_OID_ESTR=lnOidEstrategia ) ,
 null,
 0,
 0,
 0,
 null,
 null,
 0,
 null,
 null,
 (SELECT Z.OID_FORM_PAGO FROM BEL_FORMA_PAGO Z WHERE Z.PAIS_OID_PAIS =lnOidPais AND Z.COD_FORM_PAGO='GRT'),--FORMA DE PAGO
 lnOidAcceso,
 (SELECT Z.OID_SBAC FROM SEG_SUBAC Z WHERE Z.ACCE_OID_ACCE=lnOidAcceso AND COD_SBAC='000'),
 (SELECT Z.OID_ARGU_VENT FROM PRE_ARGUM_VENTA Z WHERE Z.TIES_OID_TIPO_ESTR=lnOidTipoEstrategia),
 lnOidMatrizFacturacion,
 1,
 1,
 1,
 1,
 null,
 null,
 null );


 --SE CREA UN REGISTRO PARA PRE_OFERT_DETAL

 INSERT INTO PRE_OFERT_DETAL (
 OID_DETA_OFER,
 OFER_OID_OFER,
 PROD_OID_PROD,
 NUM_LINE_OFER,
 VAL_TEXT_BREV,
 NUM_UNID_ESTI,
 COD_ORIG,
 VAL_FACT_REPE,
 NUM_POSI_RANK,
 IND_PROD_PRIN,
 IMP_PREC_CATA,
 IMP_PREC_POSI,
 IMP_COST_ESTA,
 IMP_VENT_NETA_ESTI,
 NUM_PAGI_CATA,
 OCAT_OID_CATAL,
 TOFE_OID_TIPO_OFER,
 CIVI_OID_CICLO_VIDA,
 CNDP_OID_COND_PROM,
 FOPA_OID_FORM_PAGO,
 GOFE_OID_GRUP_OFER,
 IND_DIGI,
 IND_IMPR_GP,
 IND_CODI_VENT_GENE,
 IND_MATR_FACT_GENE,
 VAL_POSI_PAGI,
 VAL_CODI_VENT,
 VAL_CENT,
 FEC_ULTI_ACTU,
 PRECIO_UNITARIO,
 NUM_PUNT_FIJO,
 VARI_OID_VARI,
 PRFI_OID_PROG_FIDE,
 NUM_ORDE_DETA)
 VALUES (PRE_OFDE_SEQ.NEXTVAL ,
 lnOidOfer ,
 lnOidProducto ,
 1,
 pq_apl_aux.Valor_Gen_I18n_Sicc(1,lnOidProducto,'MAE_PRODU') ,
 null,
 'MAV',
 1 ,
 null,
 null,
 0,
 TO_NUMBER(psPrecioContable,'999999.99') ,
 null,
 null,
 null,
 null,
 lnOidTipoOferta,
 (SELECT Z.OID_CICL_VIDA FROM PRE_CICLO_VIDA Z WHERE Z.COD_CICL_VIDA='00'),--CICLO VIDA,
 null,
 (SELECT Z.OID_FORM_PAGO FROM BEL_FORMA_PAGO Z WHERE Z.PAIS_OID_PAIS =lnOidPais AND Z.COD_FORM_PAGO='GRT'),--FORMA DE PAGO
 null,
 0,
 0,
 1,
 1,
 null,
 lsCodigoVenta ,
 null,
 sysdate,
 0,
 null,
 null ,
 null ,
 null);

 END;

 --SE GRABA EN LID_PRODU_CANSTA
 INSERT INTO LID_PRODU_CANAS (
 COD_PERI, COD_SAP, COD_TIPO_OFER,
 NUM_UNID, IMP_PREC_CONT, COD_VENT,
 IND_ACTI, USU_DIGI, FEC_DIGI)
 VALUES ( psCodigoPeriodo,psCodigoSap , psCodigoOferta,
 psNumUnidades,TO_NUMBER(psPrecioContable,'999999.99') ,lsCodigoVenta ,
 1, psUsuario, SYSDATE);


 ELSE
 --SE TRATA DE ACTUALIZACION
 UPDATE LID_PRODU_CANAS
 SET NUM_UNID = psNumUnidades,
 IMP_PREC_CONT = TO_NUMBER(psPrecioContable,'999999.99'),
 IND_ACTI = psIndicadorActivo,
 USU_MODI = psUsuario,
 FEC_MODI = SYSDATE
 WHERE COD_PERI = psCodigoPeriodo
 AND COD_SAP = psCodigoSap
 AND COD_TIPO_OFER = psCodigoOferta;


 END IF;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(SQLERRM, 1, 250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_GENER_PRODU_CANAS: ' || ls_sqlerrm);
END LID_PR_GENER_PRODU_CANAS;


/**************************************************************************
Descripcion : Determina Ganadoras de Canasta de Líderes
Fecha Creacion : 25/01/2010
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Apaza

***************************************************************************/
PROCEDURE LID_PR_DETER_GANAD_CANAS_LIDER
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2)
IS
 lnOidPais SEG_PAIS.OID_PAIS%TYPE;
 lnOidMarca SEG_MARCA.OID_MARC%TYPE;
 lnOidCanal SEG_CANAL.OID_CANA%TYPE;
 lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;

 lsCodPeriodoSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE;
 lnTotalRegistros NUMBER;
 lnPuntajeCliente NUMBER;
 lsCodigoCondicion LID_CONDI_CANAS.COD_COND%TYPE;
 lnValorMeta LID_CONDI_CANAS.VAL_META%TYPE;
 lnValorRequLider LID_CONDI_CANAS.VAL_REQU_LIDE%TYPE;

 lsCodPeriInicRecu LID_PARAM.COD_PERI_INIC_RECU%TYPE;
 lsCodPeriFinaRecu LID_PARAM.COD_PERI_FINA_RECU%TYPE;
 lnPuntajeMinimoRecu LID_PARAM.PUNT_MINI_RECU%TYPE;
 lnNumeroComodinesConsultora LID_PARAM.NUM_COMO_CANA%TYPE;

CURSOR c_Lideres(codPeriInicRecu VARCHAR2, codPeriFinaRecu VARCHAR2) IS
 SELECT COD_CLIE_LIDE, COUNT(1)
 FROM LID_GANAD_CANAS
 WHERE COD_PERI_DESP >= codPeriInicRecu
 AND COD_PERI_DESP <= codPeriFinaRecu
 AND IND_ATEN = 0
 AND IND_GANA = 0
 GROUP BY COD_CLIE_LIDE;

 TYPE interfazLider IS RECORD
 (
 codigoCliente LID_GANAD_CANAS.COD_CLIE_LIDE%TYPE,
 numeroRegistros NUMBER
 );

 TYPE interfazLiderTab IS TABLE OF interfazLider;
 interfazRecordN interfazLiderTab;


 --COMIDN
 CURSOR c_LideresComodin(codPeriIDet VARCHAR2) IS
 SELECT COD_CLIE_LIDE, 0
 FROM LID_GANAD_CANAS
 WHERE COD_PERI_DETE = codPeriIDet
 AND IND_ATEN = 0
 AND IND_GANA = 0;

 regParamLider LID_PARAM%ROWTYPE;

BEGIN

     --Recuperamos el oid Pais,Marca,Canal,Periodo
     lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
     lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
     lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
     lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

     --RECUPERAMOS EL PERIODO SIGUIENTE DEL PERIODO A PROCESAR
     lsCodPeriodoSiguiente := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
     lnOidPais, lnOidMarca, lnOidCanal,1);

     --Se verifica si hay registro en la tabla LID_CONDI_CANAS
     SELECT COUNT(1)
     INTO lnTotalRegistros
     FROM LID_CONDI_CANAS
     WHERE COD_PERI = lsCodPeriodoSiguiente;

     IF(lnTotalRegistros > 0) THEN

         --Recuperamos el valor Meta y Valor Requiere para el Lider
         SELECT COD_COND, VAL_META, VAL_REQU_LIDE
         INTO lsCodigoCondicion, lnValorMeta, lnValorRequLider
         FROM LID_CONDI_CANAS
         WHERE COD_PERI = lsCodPeriodoSiguiente;

         IF(lsCodigoCondicion = '01') THEN --Por se lider al pasar Pedido
            NULL; --Ho se hace Nada

          ELSIF (lsCodigoCondicion = '02') THEN --Por Cumplir con el pedido objetivo de la Campaña Anterior

             INSERT INTO LID_GANAD_CANAS
             (COD_CLIE_LIDE, COD_COND, COD_PERI_DETE,
             COD_PERI_DESP, NUM_PEDI_REAL, NUM_INGR_EFEC,
             IND_ATEN, IND_GANA, IND_REQU_LIDE, FEC_EVAL_DESP)
             SELECT COD_CLIE_LIDE, lsCodigoCondicion, psCodigoPeriodo ,
                        lsCodPeriodoSiguiente, VAL_NUM_PEDI_REAL, NULL,
                        0, IND_EFEC_RESU, lnValorRequLider, NULL
                 FROM (SELECT SEC.COD_CLIE_LIDE,
                       SEC.VAL_NUM_PEDI_REAL,
                       DECODE(SEC.IND_EFEC, 1, 1, 0) IND_EFEC_RESU,
                       (SELECT COUNT(1) FROM LID_GANAD_CANAS
                        WHERE COD_CLIE_LIDE = SEC.COD_CLIE_LIDE
                        AND COD_COND = lsCodigoCondicion
                        AND COD_PERI_DETE = psCodigoPeriodo) TOT_GANA
                 FROM LID_SECCI_NUMER_PEDID SEC
                 WHERE SEC.COD_PERI = psCodigoPeriodo
                 AND SEC.COD_CLIE_LIDE IS NOT NULL)
             WHERE TOT_GANA = 0;

          ELSIF (lsCodigoCondicion = '03') THEN --Por Cumplir con un numero de ingresos efectivos en la Campaña Anterior

             INSERT INTO LID_GANAD_CANAS
             (COD_CLIE_LIDE, COD_COND, COD_PERI_DETE,
             COD_PERI_DESP, NUM_PEDI_REAL, NUM_INGR_EFEC,
             IND_ATEN, IND_GANA, IND_REQU_LIDE, FEC_EVAL_DESP)
             SELECT COD_CLIE_LIDE, lsCodigoCondicion, psCodigoPeriodo ,
                    lsCodPeriodoSiguiente, NULL,TOT_RECO,
                     0, IND_EFEC_RESU, lnValorRequLider, NULL
             FROM (SELECT COD_CLIE_LIDE,
                         TOT_RECO,
                         IND_EFEC_RESU,
                         (SELECT COUNT(1) FROM LID_GANAD_CANAS
                         WHERE COD_CLIE_LIDE = SEC.COD_CLIE_LIDE
                         AND COD_COND = lsCodigoCondicion
                         AND COD_PERI_DETE = psCodigoPeriodo) TOT_GANA
                   FROM (SELECT COD_CLIE_LIDE,
                     COUNT(1) TOT_RECO,
                     (CASE WHEN (COUNT(1) >= lnValorMeta) THEN 1
                     ELSE 0
                     END) IND_EFEC_RESU
                     FROM LID_RECOM_LIDER
                     WHERE COD_PERI_EVAL = psCodigoPeriodo
                     AND COD_TIPO = '01'
                     AND INT_EFEC = 1
                     GROUP BY COD_CLIE_LIDE) SEC )
             WHERE TOT_GANA = 0;

         END IF;

     END IF;

     -- INICIO COMODIN
     --RECUPERAMOS LOS PARAMETROS DE LIDERES
      SELECT *
      INTO regParamLider
      FROM LID_PARAM A
      WHERE A.COD_PAIS = psCodigoPais;

    IF(regParamLider.NUM_COMO_CANA IS NOT NULL AND regParamLider.NUM_COMO_CANA >0 ) THEN
         OPEN c_LideresComodin(psCodigoPeriodo);
          LOOP
          FETCH c_LideresComodin BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
            IF interfazRecordN.COUNT > 0 THEN
              FOR t IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

                --numero comodines utilizados por consultora
                SELECT COUNT(1) INTO lnNumeroComodinesConsultora
                FROM  LID_LIDER_COMOD
                WHERE  VAL_ANIO= SUBSTR(psCodigoPeriodo,0,4)
                  AND NUM_PERI =(SELECT NUM_PERI
                                 FROM LID_PERIO_CORPO A
                                 WHERE INI_PERI <=SUBSTR(psCodigoPeriodo,5) AND
                                       SUBSTR(psCodigoPeriodo,5) <= FIN_PERI)
                  AND COD_CLIE_LIDE = interfazRecordN(t).codigoCliente;

                IF(lnNumeroComodinesConsultora < regParamLider.NUM_COMO_CANA)THEN

                    UPDATE LID_GANAD_CANAS
                    SET IND_GANA ='1'
                    WHERE COD_CLIE_LIDE= interfazRecordN(t).codigoCliente
                     AND COD_PERI_DETE = psCodigoPeriodo;
                  BEGIN
                    INSERT INTO LID_LIDER_COMOD (
                        VAL_ANIO,
                        NUM_PERI,
                        COD_PERI,
                        COD_CLIE_LIDE)
                    VALUES (SUBSTR(psCodigoPeriodo,0,4),
                            (SELECT NUM_PERI
                                 FROM LID_PERIO_CORPO A
                                 WHERE INI_PERI <=SUBSTR(psCodigoPeriodo,5) AND
                                       SUBSTR(psCodigoPeriodo,5) <= FIN_PERI),
                            psCodigoPeriodo,
                            interfazRecordN(t).codigoCliente);
                  EXCEPTION
                   WHEN OTHERS THEN
                     NULL;
                  END;

                END IF;

              END LOOP;
            END IF;
            EXIT WHEN c_LideresComodin%NOTFOUND;
          END LOOP;
         CLOSE c_LideresComodin;
    END IF;
      /*FIN COMODIN*/


     --Recuperamos Datos de LID_PARAM
     SELECT COD_PERI_INIC_RECU,
     COD_PERI_FINA_RECU,
     PUNT_MINI_RECU
     INTO lsCodPeriInicRecu,
     lsCodPeriFinaRecu,
     lnPuntajeMinimoRecu
     FROM LID_PARAM
     WHERE COD_PAIS = psCodigoPais;


     IF((lsCodPeriFinaRecu IS NOT NULL) AND (lsCodPeriFinaRecu = psCodigoPeriodo)) THEN

     --(1) PROCESAMOS A LAS LIDERES
      OPEN c_Lideres(lsCodPeriInicRecu, lsCodPeriFinaRecu);
      LOOP
      FETCH c_Lideres BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
       IF interfazRecordN.COUNT > 0 THEN

         FOR t IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

                 --Para cada Lider se calcula el puntaje Acumulado entre las campañas de recuperacion
                 SELECT NVL(SUM(NUM_PUNT),0)
                 INTO lnPuntajeCliente
                 FROM INC_CUENT_CORRI_PUNTO x,
                 MAE_CLIEN y,
                 (SELECT gen.Oid_Para_Gral
                 FROM INC_CONCU_PARAM_GENER gen,
                 CRA_PERIO crad, SEG_PERIO_CORPO cord,
                 CRA_PERIO crah, SEG_PERIO_CORPO corh
                 WHERE gen.PAIS_OID_PAIS = lnOidPais
                 AND gen.PERD_OID_PERI_DESD = crad.OID_PERI
                 AND crad.PERI_OID_PERI = cord.OID_PERI
                 AND gen.PERD_OID_PERI_HAST = crah.OID_PERI
                 AND crah.PERI_OID_PERI = corh.OID_PERI
                 AND cord.COD_PERI <= lsCodPeriInicRecu
                 AND corh.COD_PERI >= lsCodPeriInicRecu
                 AND cord.COD_PERI <= lsCodPeriFinaRecu
                 AND corh.COD_PERI >= lsCodPeriFinaRecu
                 AND gen.VAL_OBSE LIKE '%PROGRAMA-LIDERES%') z,
                 CRA_PERIO cp, SEG_PERIO_CORPO sp
                 WHERE x.CLIE_OID_CLIE = y.OID_CLIE
                 and x.COPA_OID_PARA_GRAL = z.OID_PARA_GRAL
                 and y.COD_CLIE = interfazRecordN(t).codigoCliente
                 and x.PERD_OID_PERI = cp.OID_PERI
                 and cp.PERI_OID_PERI = sp.OID_PERI
                 and sp.COD_PERI >= lsCodPeriInicRecu
                 and sp.COD_PERI <= lsCodPeriFinaRecu;

              IF (lnPuntajeCliente >= lnPuntajeMinimoRecu) THEN
                 INSERT INTO LID_RECUP_LIDER
                 (COD_CLIE_LIDE, COD_PERI_DESP, NUM_RECU,
                 IND_SUPE_MINI, IND_ATEN)
                 VALUES
                 (interfazRecordN(t).codigoCliente, lsCodPeriodoSiguiente, interfazRecordN(t).numeroRegistros,
                 1, 0);
              ELSE
                 INSERT INTO LID_RECUP_LIDER
                 (COD_CLIE_LIDE, COD_PERI_DESP, NUM_RECU,
                 IND_SUPE_MINI, IND_ATEN)
                 VALUES
                 (interfazRecordN(t).codigoCliente, lsCodPeriodoSiguiente, interfazRecordN(t).numeroRegistros,
                 0, 0);
              END IF;

             END LOOP;
           END IF;
          EXIT WHEN c_Lideres%NOTFOUND;
        END LOOP;
       CLOSE c_Lideres;

      END IF;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_DETER_GANAD_CANAS_LIDER: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END LID_PR_DETER_GANAD_CANAS_LIDER;


/**************************************************************************
Descripcion : Atencion de la Canasta de Líderes
Fecha Creacion : 27/01/2010
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4, GP3: GP3
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Apaza

***************************************************************************/
PROCEDURE LID_PR_ATENC_CANAS_LIDER
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2)
IS
 lnOidPais SEG_PAIS.OID_PAIS%TYPE;
 lnOidMarca SEG_MARCA.OID_MARC%TYPE;
 lnOidCanal SEG_CANAL.OID_CANA%TYPE;
 lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;

 lsCodPeriodoSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE;
 lnTotalRegistros NUMBER;
 lnTotalRegistrosAux NUMBER;
 lnPuntajeCliente NUMBER;
 lsCodigoCondicion LID_CONDI_CANAS.COD_COND%TYPE;
 lnValorRequLider LID_CONDI_CANAS.VAL_REQU_LIDE%TYPE;
 lnIndRequLide LID_GANAD_CANAS.IND_REQU_LIDE%TYPE;

 lnOidTipoPosicion PED_TIPO_POSIC.OID_TIPO_POSI%TYPE;
 lnOidSubTipoPosicion PED_SUBTI_POSIC.OID_SUBT_POSI%TYPE;

 lnCodigoPosicion PED_SOLIC_POSIC.COD_POSI%TYPE;
 lnUnidadesAtender PED_SOLIC_POSIC.NUM_UNID_POR_ATEN%TYPE;
 lnTasaImpuesto PED_SOLIC_POSIC.VAL_TASA_IMPU%TYPE;
 lnOidTasaImpuesto PED_SOLIC_POSIC.TAIM_OID_TASA_IMPU%TYPE;
 lnOidEstadoPosicion PED_ESTAD_POSIC.OID_ESTA_POSI%TYPE;
 lnOidFormaPago PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO%TYPE;
 lnOidSolicitud PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;

CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER) IS
 SELECT psc.CLIE_OID_CLIE,
 cli.COD_CLIE,
 psc.OID_SOLI_CABE,
 psc.VAL_TIPO_CAMB
 FROM PED_SOLIC_CABEC psc,
 PED_TIPO_SOLIC_PAIS tsp,
 (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC') ts,
 MAE_CLIEN cli
 WHERE psc.PAIS_OID_PAIS = oidPais
 AND psc.PERD_OID_PERI = oidPeriodo
 AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
 AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
 AND psc.FEC_FACT IS NULL
 AND psc.CLIE_OID_CLIE = cli.OID_CLIE
 AND ((psIndicadorProceso = PROCESO_GP3) AND (psc.GRPR_OID_GRUP_PROC = 3));

 TYPE interfazPedidos IS RECORD
 (
 oidCliente PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE,
 codCliente MAE_CLIEN.COD_CLIE%TYPE,
 oidSolicitud PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
 valorTipoCambio PED_SOLIC_CABEC.VAL_TIPO_CAMB%TYPE
 );

 TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
 interfazRecordN interfazPedidosTab;
 interfazPedidosReg interfazPedidos;

CURSOR c_RecupExiste IS
 SELECT rec.COD_CLIE_LIDE, rec.NUM_RECU
 FROM LID_RECUP_LIDER rec, LID_TMP_ATENC_LIDER lid
 WHERE rec.COD_PERI_DESP = psCodigoPeriodo
 AND rec.IND_ATEN = 0
 AND rec.IND_SUPE_MINI = 1
 AND rec.COD_CLIE_LIDE = lid.COD_CLIE;

CURSOR c_RecupNoExiste IS
 SELECT rec.COD_CLIE_LIDE, rec.NUM_RECU
 FROM LID_RECUP_LIDER rec
 WHERE rec.COD_PERI_DESP = psCodigoPeriodo
 AND rec.IND_ATEN = 0
 AND rec.IND_SUPE_MINI = 1
 AND rec.COD_CLIE_LIDE NOT IN (SELECT COD_CLIE FROM LID_TMP_ATENC_LIDER);

 TYPE interfazRecup IS RECORD
 (
 codCliente MAE_CLIEN.COD_CLIE%TYPE,
 numeroRecuperaciones LID_RECUP_LIDER.NUM_RECU%TYPE
 );

 TYPE interfazRecupTab IS TABLE OF interfazRecup;
 interfazRecordR interfazRecupTab;

CURSOR c_AtencionLideres IS
 SELECT OID_CLIE, COD_CLIE, OID_SOLI_CABE, VAL_TIPO_CAMB, FAC_REPE
 FROM LID_TMP_ATENC_LIDER;

 TYPE interfazAtencionTab IS TABLE OF LID_TMP_ATENC_LIDER%ROWTYPE;
 interfazRecordA interfazAtencionTab;

 TYPE interfazProducto IS RECORD
 (
 codigoSAP LID_PRODU_CANAS.COD_SAP%TYPE,
 codigoTipoOferta LID_PRODU_CANAS.COD_TIPO_OFER%TYPE,
 numeroUnidades LID_PRODU_CANAS.NUM_UNID%TYPE,
 precioContable LID_PRODU_CANAS.IMP_PREC_CONT%TYPE,
 codigoVenta LID_PRODU_CANAS.COD_VENT%TYPE,
 oidProducto MAE_PRODU.OID_PROD%TYPE,
 oidDetalleOferta PRE_OFERT_DETAL.OID_DETA_OFER%TYPE
 );

 TYPE interfazProductoTab IS TABLE OF interfazProducto;
 interfazRecordP interfazProductoTab := interfazProductoTab();
 interfazProductoReg interfazProducto;

BEGIN
 --LIMPIAMOS LA TABLA TEMPORAL
 EXECUTE IMMEDIATE('TRUNCATE TABLE LID_TMP_ATENC_LIDER');

 --Recuperamos el oid Pais,Marca,Canal,Periodo
 lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
 lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

 --RECUPERAMOS EL PERIODO SIGUIENTE DEL PERIODO A PROCESAR
 lsCodPeriodoSiguiente := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
 lnOidPais, lnOidMarca, lnOidCanal,1);

 --Se verifica si hay registro en la tabla LID_PRODU_CANAS
 SELECT COUNT(1)
 INTO lnTotalRegistros
 FROM LID_PRODU_CANAS
 WHERE COD_PERI = psCodigoPeriodo
 AND IND_ACTI = 1;

 IF(lnTotalRegistros = 0) THEN
 RETURN;
 ELSE

 --Recuperamos los productos de Canaste de Lideres
 FOR t IN (SELECT COD_SAP, COD_TIPO_OFER,
 NUM_UNID, IMP_PREC_CONT,
 COD_VENT, (SELECT OID_PROD FROM MAE_PRODU
 WHERE COD_SAP = LID_PRODU_CANAS.COD_SAP) OID_PROD
 FROM LID_PRODU_CANAS
 WHERE COD_PERI = psCodigoPeriodo
 AND IND_ACTI = 1) LOOP

 interfazProductoReg.codigoSAP := t.COD_SAP;
 interfazProductoReg.codigoTipoOferta := t.COD_TIPO_OFER;
 interfazProductoReg.numeroUnidades := t.NUM_UNID;
 interfazProductoReg.precioContable := t.IMP_PREC_CONT;
 interfazProductoReg.codigoVenta := t.COD_VENT;
 interfazProductoReg.oidProducto := t.OID_PROD;
 interfazProductoReg.oidDetalleOferta := NULL;

 SELECT det.OID_DETA_OFER
 INTO interfazProductoReg.oidDetalleOferta
 FROM PRE_MATRI_FACTU_CABEC cab, PRE_OFERT ofe,
 PRE_OFERT_DETAL det, PRE_TIPO_OFERT tip
 WHERE cab.PERD_OID_PERI = lnOidPeriodo
 AND ofe.MFCA_OID_CABE = cab.OID_CABE
 AND det.OFER_OID_OFER = ofe.OID_OFER
 AND det.TOFE_OID_TIPO_OFER = tip.OID_TIPO_OFER
 AND det.PROD_OID_PROD = t.OID_PROD
 AND det.VAL_CODI_VENT = t.COD_VENT
 AND tip.cod_tipo_ofer = t.COD_TIPO_OFER;

 interfazRecordP.EXTEND(1);
 interfazRecordP(interfazRecordP.COUNT) := interfazProductoReg;
 END LOOP;

 END IF;

 --Recuperamos el codigo Condicion para la campaña de Proceso
 SELECT COD_COND, VAL_REQU_LIDE
 INTO lsCodigoCondicion, lnValorRequLider
 FROM LID_CONDI_CANAS
 WHERE COD_PERI = psCodigoPeriodo;


 --(1) PROCESAMOS A LOS PEDIDOS
 OPEN c_Pedidos(lnOidPais, lnOidPeriodo);
 LOOP
 FETCH c_Pedidos BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
 IF interfazRecordN.COUNT > 0 THEN

 FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

 IF(lsCodigoCondicion = '01') THEN
 --Validamos que sea Lider de Seccion
 SELECT COUNT(1)
 INTO lnTotalRegistros
 FROM ZON_SECCI
 WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
 AND IND_ACTI = 1
 AND IND_BORR = 0;

 IF(lnTotalRegistros > 0) THEN

 INSERT INTO LID_GANAD_CANAS
 (COD_CLIE_LIDE, COD_COND, COD_PERI_DETE,
 COD_PERI_DESP, NUM_PEDI_REAL, NUM_INGR_EFEC,
 IND_ATEN, IND_GANA, IND_REQU_LIDE, FEC_EVAL_DESP)
 VALUES
 (interfazRecordN(x).codCliente, '01', psCodigoPeriodo,
 psCodigoPeriodo, NULL, NULL,
 0, 1, lnValorRequLider, NULL);


 INSERT INTO LID_TMP_ATENC_LIDER
 (OID_CLIE, COD_CLIE,
 OID_SOLI_CABE, VAL_TIPO_CAMB, FAC_REPE)
 VALUES
 (interfazRecordN(x).oidCliente, interfazRecordN(x).codCliente,
 interfazRecordN(x).oidSolicitud, interfazRecordN(x).valorTipoCambio, 1);

 END IF;

 ELSE

 BEGIN
 SELECT IND_REQU_LIDE
 INTO lnIndRequLide
 FROM LID_GANAD_CANAS
 WHERE COD_COND = lsCodigoCondicion
 AND IND_GANA = 1
 AND COD_PERI_DESP = psCodigoPeriodo
 AND IND_ATEN = 0
 AND COD_CLIE_LIDE = interfazRecordN(x).codCliente;
 EXCEPTION
 WHEN OTHERS THEN
 lnIndRequLide := NULL;
 END;

 IF(lnIndRequLide IS NOT NULL) THEN

 IF(lnIndRequLide = 1) THEN
 --Validamos que sea Lider de Seccion
 SELECT COUNT(1)
 INTO lnTotalRegistros
 FROM ZON_SECCI
 WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
 AND IND_ACTI = 1
 AND IND_BORR = 0;

 IF(lnTotalRegistros = 0) THEN
 UPDATE LID_GANAD_CANAS
 SET IND_ATEN = 0,
 IND_GANA = 3,
 FEC_EVAL_DESP = SYSDATE
 WHERE COD_CLIE_LIDE = interfazRecordN(x).codCliente
 AND COD_PERI_DESP = psCodigoPeriodo
 AND COD_COND = lsCodigoCondicion;

 ELSE
 INSERT INTO LID_TMP_ATENC_LIDER
 (OID_CLIE, COD_CLIE,
 OID_SOLI_CABE, VAL_TIPO_CAMB, FAC_REPE)
 VALUES
 (interfazRecordN(x).oidCliente, interfazRecordN(x).codCliente,
 interfazRecordN(x).oidSolicitud, interfazRecordN(x).valorTipoCambio, 1);

 END IF;

 ELSE
 INSERT INTO LID_TMP_ATENC_LIDER
 (OID_CLIE, COD_CLIE,
 OID_SOLI_CABE, VAL_TIPO_CAMB, FAC_REPE)
 VALUES
 (interfazRecordN(x).oidCliente, interfazRecordN(x).codCliente,
 interfazRecordN(x).oidSolicitud, interfazRecordN(x).valorTipoCambio, 1);

 END IF;
 END IF;
 END IF;

 END LOOP;

 END IF;
 EXIT WHEN c_Pedidos%NOTFOUND;
 END LOOP;
 CLOSE c_Pedidos;


 --(2) PROCESAMOS A LAS RECUPERACIONES
 OPEN c_RecupExiste;
 LOOP
 FETCH c_RecupExiste BULK COLLECT INTO interfazRecordR LIMIT W_FILAS;
 IF interfazRecordR.COUNT > 0 THEN

 FOR x IN interfazRecordR.FIRST .. interfazRecordR.LAST LOOP
 UPDATE LID_TMP_ATENC_LIDER
 SET FAC_REPE = 1 + interfazRecordR(x).numeroRecuperaciones
 WHERE COD_CLIE = interfazRecordR(x).codCliente;

 END LOOP;

 END IF;
 EXIT WHEN c_RecupExiste%NOTFOUND;
 END LOOP;
 CLOSE c_RecupExiste;


 --(3) PROCESAMOS A LAS RECUPERACIONES
 OPEN c_RecupNoExiste;
 LOOP
 FETCH c_RecupNoExiste BULK COLLECT INTO interfazRecordR LIMIT W_FILAS;
 IF interfazRecordR.COUNT > 0 THEN

 FOR x IN interfazRecordR.FIRST .. interfazRecordR.LAST LOOP
 BEGIN
 --Validamos si el cliente esta pasando Pedido
 SELECT psc.CLIE_OID_CLIE,
 cli.COD_CLIE,
 psc.OID_SOLI_CABE,
 psc.VAL_TIPO_CAMB
 INTO interfazPedidosReg
 FROM PED_SOLIC_CABEC psc,
 PED_TIPO_SOLIC_PAIS tsp,
 (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC') ts,
 MAE_CLIEN cli
 WHERE psc.PAIS_OID_PAIS = lnOidPais
 AND psc.PERD_OID_PERI = lnOidPeriodo
 AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
 AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
 AND psc.FEC_FACT IS NULL
 AND psc.CLIE_OID_CLIE = cli.OID_CLIE
 AND cli.COD_CLIE = interfazRecordR(x).codCliente
 AND psc.GRPR_OID_GRUP_PROC = 3;

 lnOidSolicitud := interfazPedidosReg.oidSolicitud;
 EXCEPTION
 WHEN OTHERS THEN
 lnOidSolicitud := NULL;
 END;

 IF (lnOidSolicitud IS NOT NULL) THEN
 INSERT INTO LID_TMP_ATENC_LIDER
 (OID_CLIE, COD_CLIE,
 OID_SOLI_CABE, VAL_TIPO_CAMB, FAC_REPE)
 VALUES
 (interfazPedidosReg.oidCliente, interfazRecordR(x).codCliente,
 lnOidSolicitud, interfazPedidosReg.valorTipoCambio, interfazRecordR(x).numeroRecuperaciones);
 END IF;

 END LOOP;

 END IF;
 EXIT WHEN c_RecupNoExiste%NOTFOUND;
 END LOOP;
 CLOSE c_RecupNoExiste;

 --Si no hay registros en Ganadoras Lideres, se finaliza
 SELECT COUNT(1)
 INTO lnTotalRegistros
 FROM LID_TMP_ATENC_LIDER;

 IF(lnTotalRegistros = 0) THEN
 RETURN;
 END IF;

 --Se recupera los tipos de Solicitud y Posicion
 SELECT OID_TIPO_POSI
 INTO lnOidTipoPosicion
 FROM PED_TIPO_POSIC
 WHERE COD_TIPO_POSI = 'AG';

 SELECT OID_SUBT_POSI
 INTO lnOidSubTipoPosicion
 FROM PED_SUBTI_POSIC
 WHERE COD_SUBT_POSI = 'AG';

 --Recupermos el Oid Estado de la Posicion
 SELECT OID_ESTA_POSI
 INTO lnOidEstadoPosicion
 FROM PED_ESTAD_POSIC
 WHERE COD_ESTA_POSI = 'CO';

 --Obtenemos el Oid Forma Pago
 SELECT OID_FORM_PAGO
 INTO lnOidFormaPago
 FROM BEL_FORMA_PAGO
 WHERE PAIS_OID_PAIS = lnOidPais
 AND COD_FORM_PAGO = 'GRT';


 -- AÑADIMOS A LAS SOLICITUDES LOS PRODUCTOS DE LA CANASTA
 OPEN c_AtencionLideres;
 LOOP
 FETCH c_AtencionLideres BULK COLLECT INTO interfazRecordA LIMIT W_FILAS;
 IF interfazRecordA.COUNT > 0 THEN

 FOR x IN interfazRecordA.FIRST .. interfazRecordA.LAST LOOP

 --Recuperamos datos de la ultima posicion del detalle de la Solicitud
 SELECT COD_POSI, VAL_TASA_IMPU, TAIM_OID_TASA_IMPU
 INTO lnCodigoPosicion, lnTasaImpuesto, lnOidTasaImpuesto
 FROM (
 SELECT COD_POSI, VAL_TASA_IMPU, TAIM_OID_TASA_IMPU
 FROM PED_SOLIC_POSIC
 WHERE SOCA_OID_SOLI_CABE = interfazRecordA(x).OID_SOLI_CABE
 ORDER BY COD_POSI DESC)
 WHERE ROWNUM = 1;

 FOR i IN 1..interfazRecordP.COUNT LOOP
 lnCodigoPosicion := lnCodigoPosicion + 1;
 lnUnidadesAtender := interfazRecordA(x).FAC_REPE * interfazRecordP(i).numeroUnidades;

 INSERT INTO PED_SOLIC_POSIC
 	(OID_SOLI_POSI, SOCA_OID_SOLI_CABE, COD_POSI,
 NUM_UNID_DEMA, NUM_UNID_POR_ATEN, NUM_UNID_DEMA_REAL,
 NUM_UNID_COMPR, TPOS_OID_TIPO_POSI, STPO_OID_SUBT_POSI,
 FOPA_OID_FORM_PAGO, PROD_OID_PROD, VAL_CODI_VENT,
 OFDE_OID_DETA_OFER, VAL_CODI_VENT_FICT, VAL_PREC_CATA_UNIT_LOCA,
 VAL_PREC_CONT_UNIT_LOCA, VAL_PREC_CATA_UNIT_DOCU, VAL_PREC_CONTA_UNIT_DOCU,
 SOPO_OID_SOLI_POSI, VAL_PORC_DESC, VAL_IMPO_DESC_UNIT_LOCA,
 VAL_IMPO_DESC_UNIT_DOCU, NUM_PAGI_CATA, VAL_CATA,
 VAL_TASA_IMPU, TAIM_OID_TASA_IMPU, ESPO_OID_ESTA_POSI)
 VALUES
 (PED_SOPO_SEQ.NEXTVAL, interfazRecordA(x).OID_SOLI_CABE, lnCodigoPosicion,
 0, lnUnidadesAtender, lnUnidadesAtender,
 lnUnidadesAtender, lnOidTipoPosicion, lnOidSubTipoPosicion,
 lnOidFormaPago, interfazRecordP(i).oidProducto, interfazRecordP(i).codigoVenta,
 interfazRecordP(i).oidDetalleOferta, NULL, 0,
 interfazRecordP(i).precioContable, 0, ROUND(interfazRecordP(i).precioContable/interfazRecordA(x).VAL_TIPO_CAMB,2),
 NULL, NULL, NULL,
 NULL, NULL, NULL,
 lnTasaImpuesto, lnOidTasaImpuesto, lnOidEstadoPosicion);

 END LOOP;

 --Se actualiza como Ganadora, a la consultora que se ha agregado los productos a su pedido
 UPDATE LID_GANAD_CANAS
 SET IND_ATEN = 1,
 IND_GANA = 2,
 FEC_EVAL_DESP = SYSDATE,
 SOCA_OID_SOLI_CABE = interfazRecordA(x).OID_SOLI_CABE
 WHERE COD_CLIE_LIDE = interfazRecordA(x).COD_CLIE
 AND COD_PERI_DESP = psCodigoPeriodo;

 --Si ha Tenido Recuperaciones, se actualiza como Atendida a la Consultora
 SELECT COUNT(1)
 INTO lnTotalRegistros
 FROM LID_RECUP_LIDER
 WHERE COD_CLIE_LIDE = interfazRecordA(x).COD_CLIE
 AND COD_PERI_DESP = psCodigoPeriodo;

 UPDATE LID_RECUP_LIDER
 SET IND_ATEN = 1
 WHERE COD_CLIE_LIDE = interfazRecordA(x).COD_CLIE
 AND COD_PERI_DESP = psCodigoPeriodo;

 IF(lnTotalRegistros > 0) THEN
 SELECT COUNT(1)
 INTO lnTotalRegistrosAux
 FROM LID_GANAD_CANAS
 WHERE COD_CLIE_LIDE = interfazRecordA(x).COD_CLIE
 AND COD_PERI_DESP = psCodigoPeriodo;

 IF(lnTotalRegistrosAux = 0) THEN

 INSERT INTO LID_GANAD_CANAS
 (COD_CLIE_LIDE, COD_COND, COD_PERI_DETE,
 COD_PERI_DESP, NUM_PEDI_REAL, NUM_INGR_EFEC,
 IND_ATEN, IND_GANA, IND_REQU_LIDE, FEC_EVAL_DESP, SOCA_OID_SOLI_CABE)
 VALUES
 (interfazRecordA(x).COD_CLIE, lsCodigoCondicion, psCodigoPeriodo,
 psCodigoPeriodo, NULL, NULL,
 1, 4, lnValorRequLider, SYSDATE, interfazRecordA(x).OID_SOLI_CABE);

 ELSE

 UPDATE LID_GANAD_CANAS
 SET IND_GANA = 4
 WHERE COD_CLIE_LIDE = interfazRecordA(x).COD_CLIE
 AND COD_PERI_DESP = psCodigoPeriodo;
 END IF;

 END IF;

 END LOOP;

 END IF;
 EXIT WHEN c_AtencionLideres%NOTFOUND;
 END LOOP;
 CLOSE c_AtencionLideres;


EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_ATENC_CANAS_LIDER: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END LID_PR_ATENC_CANAS_LIDER;

/**************************************************************************
Descripcion : Evalua Ranking de lideres en la Campanha
Fecha Creacion : 24/03/2010
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4, GP3: GP3
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Buchelli

***************************************************************************/
PROCEDURE LID_PR_EVALU_RANKI_LIDER_CAMPA
 (psCodigoPais VARCHAR2,
 psCodigoMarca VARCHAR2,
 psCodigoCanal VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psIndicadorProceso VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodigoUsuario VARCHAR2)
IS
 lnOidPais SEG_PAIS.OID_PAIS%TYPE;
 lnOidMarca SEG_MARCA.OID_MARC%TYPE;
 lnOidCanal SEG_CANAL.OID_CANA%TYPE;
 lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;

CURSOR c_Lideres IS
 SELECT sec.OID_SECC,
 (SELECT R.COD_CLIE FROM MAE_CLIEN R WHERE R.OID_CLIE =sec.CLIE_OID_CLIE) COD_CLIE,
 sec.CLIE_OID_CLIE,
 sub.COD_SUBG_VENT || reg.COD_REGI || zon.COD_ZONA || sec.COD_SECC
 FROM ZON_SECCI sec, ZON_ZONA zon, ZON_REGIO reg, ZON_SUB_GEREN_VENTA sub
 WHERE sec.ZZON_OID_ZONA = zon.OID_ZONA
 AND zon.ZORG_OID_REGI = reg.OID_REGI
 AND reg.ZSGV_OID_SUBG_VENT = sub.OID_SUBG_VENT
 AND sec.IND_ACTI = 1
 AND sec.IND_BORR = 0
 AND sec.CLIE_OID_CLIE IS NOT NULL
 AND (((psIndicadorProceso = PROCESO_CIERRE_ZONA) AND
 (
 (zon.cod_zona = psCodigoZona) AND (zon.ind_acti = 1)
 )
 ) OR
 ((psIndicadorProceso = PROCESO_CIERRE_REGION) AND
 (
 (reg.cod_regi = psCodigoRegion) AND (reg.ind_acti = 1)
 )
 ) OR
 (psIndicadorProceso = PROCESO_CIERRE_CAMPANA)
 );

 TYPE interfazLider IS RECORD
 (
 oidSeccion ZON_SECCI.OID_SECC%TYPE,
 codCliente MAE_CLIEN.COD_CLIE%TYPE,
 oidCliente MAE_CLIEN.OID_CLIE%TYPE,
 ua VARCHAR2(9)
 );

 TYPE interfazLiderTab IS TABLE OF interfazLider;
 interfazRecordN interfazLiderTab;

 lnContadorConcursoActivos NUMBER;
 lsFlagCierrePeriodo VARCHAR2(1);
 lnValorPuntajeMinProd NUMBER;
 lnContCliente NUMBER;
 lnPuntajeObtenido INC_CUENT_CORRI_PUNTO.NUM_PUNT%TYPE;
 lsIndProductividad VARCHAR2(1);
BEGIN

 --Recuperamos el oid Pais,Marca,Canal,Periodo
 lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
 lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
 lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
 lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

 /* Si se ejecuta del cierre de periodo se activa el flag de Ciere de periodo en 1*/
 IF(psIndicadorProceso = PROCESO_CIERRE_CAMPANA) THEN
 lsFlagCierrePeriodo :='1';
 ELSE
 lsFlagCierrePeriodo :='0';
 END IF;

 /* Se recupera los concursos de líderes activos para la campaña*/
 SELECT COUNT(1) INTO lnContadorConcursoActivos
 FROM INC_CONCU_PARAM_GENER X
 WHERE X.IND_ACTI=1
 AND UPPER(X.VAL_OBSE) LIKE '%PROGRAMA-LIDERES%';

 IF (lnContadorConcursoActivos =0 ) THEN
 RETURN;
 END IF ;

 /*Se recupera el Puntaje Mínimo de Productividad*/
 BEGIN
 SELECT X.VAL_PUNT_MINI_PROD INTO lnValorPuntajeMinProd
 FROM LID_PARAM X
 where X.COD_PAIS = psCodigoPais;
 EXCEPTION
 WHEN OTHERS THEN
 lnValorPuntajeMinProd:=NULL;
 END;

 IF(lnValorPuntajeMinProd IS NULL) THEN
 RETURN;
 END IF;

 OPEN c_Lideres;
 LOOP
 FETCH c_Lideres BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
 IF interfazRecordN.COUNT > 0 THEN

 FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

 SELECT COUNT(1) INTO lnContCliente
 FROM LID_CRITI_CAMPA A
 WHERE A.COD_PERI = psCodigoPeriodo
 AND interfazRecordN(x).codCliente = A.COD_CLIE_LIDE;

 /* OBTENEMOS PUNTAJE OBTENIDO POR LA LIDER EN LA CAMPANHA*/
 SELECT NVL(SUM(X.NUM_PUNT),0)
 INTO lnPuntajeObtenido
 FROM INC_CUENT_CORRI_PUNTO X
 WHERE X.COPA_OID_PARA_GRAL IN (
 SELECT Z.OID_PARA_GRAL
 FROM INC_CONCU_PARAM_GENER Z
 WHERE /*Z.IND_ACTI=1
 AND */Z.OID_PARA_GRAL = X.COPA_OID_PARA_GRAL
 AND UPPER(Z.VAL_OBSE) LIKE '%PROGRAMA-LIDERES%')
 AND X.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
 AND X.PERD_OID_PERI = lnOidPeriodo
 AND UPPER(TRIM(X.VAL_DESC)) <> UPPER(TRIM('Entrega de Premio'));

 /*
 se eevalúa si supera el mínimo de puntos de productividad.
 Para esto se valida si el valor de NUM_PUNT_CAMP es mayor igual NUM_PUNT_MINI entonces se actualiza:
 -	IND_PROD = 'P'
 En caso contrario, se actualiza con:
 -	IND_PROD = 'C'
 */

 IF(lnPuntajeObtenido >= lnValorPuntajeMinProd) THEN
 lsIndProductividad := 'P';
 ELSE
 lsIndProductividad := 'C';
 END IF;


 IF(lnContCliente=0) THEN--NO EXISTE
 INSERT INTO LID_CRITI_CAMPA (
 COD_PERI,
 COD_CLIE_LIDE,
 NUM_PUNT_CAMP,
 NUM_PUNT_MINI,
 IND_PROD,
 IND_CIER_PERI,
 USU_DIGI,
 FEC_DIGI)
 VALUES (psCodigoPeriodo ,
 interfazRecordN(x).codCliente,
 lnPuntajeObtenido,
 lnValorPuntajeMinProd,
 lsIndProductividad,
 lsFlagCierrePeriodo,
 psCodigoUsuario,
 SYSDATE
 );


 ELSE -- EXISTE
 UPDATE LID_CRITI_CAMPA
 SET NUM_PUNT_CAMP = lnPuntajeObtenido,
 NUM_PUNT_MINI = lnValorPuntajeMinProd,
 IND_CIER_PERI = lsFlagCierrePeriodo,
 IND_PROD = lsIndProductividad,
 USU_MODI = psCodigoUsuario,
 FEC_MODI = SYSDATE
 WHERE COD_PERI = psCodigoPeriodo
 AND COD_CLIE_LIDE = interfazRecordN(x).codCliente;

 END IF;

 END LOOP;

 END IF;
 EXIT WHEN c_Lideres%NOTFOUND;
 END LOOP;
 CLOSE c_Lideres;


EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVALU_RANKI_LIDER_CAMPA: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END LID_PR_EVALU_RANKI_LIDER_CAMPA;

/**************************************************************************
Descripcion : Evalua Ranking de lideren en el Periodo
Fecha Creacion : 25/03/2010
Parametros Entrada:
 psCodigoPais : Codigo de pais
 psCodigoMarca : Codigo de Marca
 psCodigoCanal : Codigo de Canal
 psCodigoPeriodo : Codigo de periodo
 psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
 GP4: GP4, GP3: GP3
 psCodigoRegion : Codigo de Region
 psCodigoZona : Codigo de Zona
 psFechaFacturacion : Fecha de Facturacion
 psCodigoUsuario : Usuario que ejecuta el Proceso

Autor : Sergio Buchelli

***************************************************************************/
PROCEDURE LID_PR_EVALU_RANKI_LIDER_PERIO(
  psCodigoPais        VARCHAR2,
  psCodigoMarca       VARCHAR2,
  psCodigoCanal       VARCHAR2,
  psCodigoPeriodo     VARCHAR2,
  psIndicadorProceso  VARCHAR2,
  psCodigoRegion      VARCHAR2,
  psCodigoZona        VARCHAR2,
  psFechaFacturacion  VARCHAR2,
  psCodigoUsuario     VARCHAR2)
IS

  lnOidPais     SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca    SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal    SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo  CRA_PERIO.OID_PERI%TYPE;

  CURSOR c_Lideres IS
    SELECT sec.oid_secc,
           (SELECT r.cod_clie
              FROM mae_clien r
             WHERE r.oid_clie = sec.clie_oid_clie) COD_CLIE,
           sec.clie_oid_clie,
           sub.cod_subg_vent || reg.cod_regi || zon.cod_zona || sec.cod_secc
      FROM zon_secci sec,
           zon_zona zon,
           zon_regio reg,
           zon_sub_geren_venta sub
     WHERE sec.zzon_oid_zona = zon.oid_zona
       AND zon.zorg_oid_regi = reg.oid_regi
       AND reg.zsgv_oid_subg_vent = sub.oid_subg_vent
       AND sec.ind_acti = 1
       AND sec.ind_borr = 0
       AND sec.clie_oid_clie IS NOT NULL
       AND (( (psIndicadorProceso = PROCESO_CIERRE_ZONA) AND
              ( (zon.cod_zona = psCodigoZona) AND (zon.ind_acti = 1)))
        OR ((psIndicadorProceso = PROCESO_CIERRE_REGION) AND
           ((reg.cod_regi = psCodigoRegion) AND (reg.ind_acti = 1)))
        OR (psIndicadorProceso = PROCESO_CIERRE_CAMPANA));

  TYPE interfazLider IS RECORD(
    oidSeccion  ZON_SECCI.OID_SECC%TYPE,
    codCliente  MAE_CLIEN.COD_CLIE%TYPE,
    oidCliente  MAE_CLIEN.OID_CLIE%TYPE,
    ua          VARCHAR2(9)
  );

  TYPE interfazLiderTab IS TABLE OF interfazLider;
  interfazRecordN interfazLiderTab;

  lnContCliente       NUMBER;
  lsIndProductividad  VARCHAR2(1);
  lsAnho              VARCHAR2(4);
  lsCampa             VARCHAR2(2);
  lnRatioCriti        LID_PARAM.VAL_RATI_CRIT%TYPE;
  lnNumPeri           LID_PERIO_CORPO.NUM_PERI%type;
  lnNumCamProd        NUMBER;
  lnNumCamEvaluando   NUMBER;
  lnRatio             NUMBER;

  lsIniPeri               INC_CARGA_PUNTA_CONSU.COD_PERI%TYPE;
  lsFinPeri               INC_CARGA_PUNTA_CONSU.COD_PERI%TYPE;
  lsCampanhaIngresoLider  INC_CARGA_PUNTA_CONSU.COD_PERI%TYPE;
  lsCampanhaDesde         INC_CARGA_PUNTA_CONSU.COD_PERI%TYPE;
  lsCampanhaHasta         INC_CARGA_PUNTA_CONSU.COD_PERI%TYPE;

BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  lsAnho  := SUBSTR(psCodigoPeriodo,1,4);
  lsCampa := SUBSTR(psCodigoPeriodo,5);

  SELECT num_peri,
         lsAnho || INI_PERI,
         lsAnho || FIN_PERI
    INTO lnNumPeri,
         lsIniPeri,
         lsFinPeri
    FROM lid_perio_corpo
   WHERE ini_peri <= lsCampa
     AND fin_peri >= lsCampa;

  /*Se recupera el Puntaje Mínimo de Productividad*/
  BEGIN
    SELECT x.val_rati_crit
      INTO lnRatioCriti
      FROM lid_param x
     WHERE x.cod_pais = psCodigoPais;
  EXCEPTION
    WHEN OTHERS THEN
      lnRatioCriti := NULL;
  END;

  IF (lnRatioCriti IS NULL) THEN
    RETURN;
  END IF;

  OPEN c_Lideres;
    LOOP
      FETCH c_Lideres BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;

        IF interfazRecordN.COUNT > 0 THEN

          FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

            SELECT COUNT(1)
              INTO lnContCliente
              FROM lid_criti_perio a
             WHERE a.val_anio = lsAnho
               AND a.num_peri = lnNumPeri
               --AND A.COD_PERI = psCodigoPeriodo
               AND interfazRecordN(x).codCliente = a.cod_clie_lide;

            -- DBMS_OUTPUT.PUT_LINE('CLIENTE '|| interfazRecordN(x).codCliente || ' UA ' || interfazRecordN(x).ua);
            -- CAMPANHA DE INGRESO LIDER
            /*lsCampanhaIngresoLider := LID_FN_DEVUE_CAMPA_INGRE_LIDER(interfazRecordN(x).codCliente,interfazRecordN(x).ua);

            IF (lsIniPeri >= lsCampanhaIngresoLider) THEN
              lsCampanhaDesde := lsIniPeri;
            ELSE
              lsCampanhaDesde := lsCampanhaIngresoLider;
            END IF;

            IF (lsFinPeri <= psCodigoPeriodo) THEN
              lsCampanhaHasta := lsFinPeri;
            ELSE
              lsCampanhaHasta := psCodigoPeriodo;
            END IF;*/

            /* Obtenemos numero de campanhas productivas*/
            SELECT COUNT(1)
              INTO lnNumCamProd
              FROM lid_criti_campa x
             WHERE x.cod_clie_lide = interfazRecordN(x).codCliente
               AND x.cod_peri >= lsIniPeri--lsCampanhaDesde
               AND x.cod_peri <= lsFinPeri--lsCampanhaHasta
               AND x.ind_prod = 'P';

            /* Obtenemos numero de campanhas que se estan evaluando*/
            --lnNumCamEvaluando := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsCampanhaDesde,lsCampanhaHasta,lnOidPais,lnOidMarca,lnOidCanal);
            SELECT COUNT(1)
              INTO lnNumCamEvaluando
              FROM lid_criti_campa x
             WHERE x.cod_clie_lide = interfazRecordN(x).codCliente
               AND x.cod_peri >= lsIniPeri--lsCampanhaDesde
               AND x.cod_peri <= lsFinPeri--lsCampanhaHasta
               ;

            IF (lnNumCamEvaluando > 0) THEN
              lnRatio := lnNumCamProd/lnNumCamEvaluando;
            ELSE
              lnRatio := 0;
            END IF;

            /* se evalúa si supera el ratio de criticidad.
               Para esto se valida si el valor de NUM_ CAMP_PROD/NUM_CAMP_EVAL es mayor igual VAL_RATIO
               entonces se actualiza:
               -	IND_PROD = 'P'
               En caso contrario, se actualiza con:
               -	IND_PROD = 'C'
            */
            IF (lnRatio >= lnRatioCriti) THEN
              lsIndProductividad := 'P';
            ELSE
              lsIndProductividad := 'C';
            END IF;

            IF (lnContCliente = 0) THEN--NO EXISTE

              INSERT INTO lid_criti_perio (
                val_anio,
                num_peri,
                cod_clie_lide,
                cod_peri,
                num_camp_prod,
                num_camp_eval,
                val_ratio,
                ind_prod,
                usu_digi,
                fec_digi
              )
              VALUES (
                lsAnho,
                lnNumPeri,
                interfazRecordN(x).codCliente,
                psCodigoPeriodo,
                lnNumCamProd,
                lnNumCamEvaluando,
                lnRatioCriti,
                lsIndProductividad,
                psCodigoUsuario,
                SYSDATE
              );

            ELSE -- EXISTE

              UPDATE lid_criti_perio
                 SET cod_peri = psCodigoPeriodo,
                     num_camp_prod = lnNumCamProd,
                     num_camp_eval = lnNumCamEvaluando,
                     val_ratio = lnRatioCriti,
                     ind_prod = lsIndProductividad,
                     usu_modi = psCodigoUsuario,
                     fec_modi = SYSDATE
               WHERE val_anio = lsAnho
                 AND num_peri = lnNumPeri
                 AND cod_clie_lide = interfazRecordN(x).codCliente;

            END IF;

          END LOOP;

        END IF;

      EXIT WHEN c_Lideres%NOTFOUND;
    END LOOP;
  CLOSE c_Lideres;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVALU_RANKI_LIDER_PERIO: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END LID_PR_EVALU_RANKI_LIDER_PERIO;


/***************************************************************************
Descripcion : Recupera la campnha de ingreso de la lider a una UA
Fecha Creacion : 25/03/2010
Autor : Sergio Buchelli
Parametros Entrada:
 psCodigoClie : Codigo de cliente
 psUnidadAdministrativa : unidad administrativa

***************************************************************************/
FUNCTION LID_FN_DEVUE_CAMPA_INGRE_LIDER(psCodigoClie VARCHAR2, psUnidadAdministrativa VARCHAR2)
RETURN VARCHAR2 IS
 ldFecha DATE;
 lsFecha VARCHAR2(10);
 lsPeriodo INC_CARGA_PUNTA_CONSU.COD_PERI%TYPE;
BEGIN

 SELECT MIN(Z. FEC_DESD) INTO ldFecha
 FROM ZON_HISTO_GEREN Z
 WHERE Z.UA = psUnidadAdministrativa
 AND Z.GERE = psCodigoClie;


 /* SELECT MAX(OID_PERI) INTO lnOidPeri
 FROM CRA_PERIO A
 WHERE ldFecha >=A.FEC_INIC
 AND ldFecha <= A.FEC_FINA;
 */

 SELECT a.cod_peri
 INTO lsPeriodo
 FROM seg_perio_corpo a,
 cra_perio b,
 seg_canal c,
 seg_marca d
 WHERE
 a.oid_peri = b.peri_oid_peri and
 b.CANA_OID_CANA = c.OID_CANA and
 b.MARC_OID_MARC = d.OID_MARC
 and c.COD_CANA = 'VD'
 and d.COD_MARC = 'T'
 AND TRUNC(ldFecha) >=b.FEC_INIC
 AND TRUNC(ldFecha) <= b.FEC_FINA
 and rownum= 1;

 RETURN lsPeriodo;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_FN_DEVUE_CAMPA_INGRE_LIDER: '||ls_sqlerrm);
 RETURN '';
END LID_FN_DEVUE_CAMPA_INGRE_LIDER;

/***************************************************************************
Descripcion : Procedimiemto Evaluar Recomendaciones de 2 pedidos al
              Cierre de la region
Fecha Creacion : 09/09/2011
Autor : Jose Luis Rodriguez
***************************************************************************/

PROCEDURE LID_PR_EVALU_RECOM_2PEDI_REGIO(
  psCodPais         VARCHAR2,
  psCodMarca        VARCHAR2,
  psPeriodoProceso  VARCHAR2,
  psFechaProceso    VARCHAR2,
  psCodRegion       VARCHAR2)
IS

  CURSOR c_RECO_LIDE IS
    SELECT t.cod_conc,
           t.cod_clie_lide,
           t.cod_clie_rete,
           t.cod_clie_reda,
           t.cod_peri_eval,
           t.cod_tipo,
           t.cod_peri_reon
      FROM lid_recom_lider t
     WHERE t.cod_pais = psCodPais
       AND t.cod_marc = psCodMarca
       AND t.cod_tipo = '01'
       AND(t.int_efec IS NULL OR t.int_efec = 0)
       AND t.cod_peri_eval = psPeriodoProceso
       AND t.cod_clie_lide IN (SELECT mc.cod_clie
                                 FROM zon_regio zr,
                                      zon_zona zz,
                                      zon_secci zs,
                                      mae_clien mc
                                WHERE zr.cod_regi = psCodRegion
                                  AND zr.ind_acti = 1
                                  AND zr.oid_regi =zorg_oid_regi
                                  AND zz.ind_acti = 1
                                  AND zz.oid_zona = zs.zzon_oid_zona
                                  AND zs.ind_acti = 1
                                  AND zs.clie_oid_clie IS NOT NULL
                                  AND mc.oid_clie = zs.clie_oid_clie);

  TYPE interfazLider IS RECORD(
    codConc     LID_RECOM_LIDER.COD_CONC%TYPE,
    codCliLide  MAE_CLIEN.COD_CLIE%TYPE,
    codCliRete  MAE_CLIEN.COD_CLIE%TYPE,
    codCliReda  MAE_CLIEN.COD_CLIE%TYPE,
    codPeriEva  LID_RECOM_LIDER.COD_PERI_EVAL%TYPE,
    codTipo     LID_RECOM_LIDER.COD_TIPO%TYPE,
    codPeriReo  LID_RECOM_LIDER.COD_CLIE_REON%TYPE
  );

  TYPE interfazLiderTab IS TABLE OF interfazLider;
  interfazRecordN interfazLiderTab;

  CURSOR c_RECO_LIDE_EFEC IS
    SELECT lid_pkg_proce_lider.gen_fn_devuelve_id_cliente(t.cod_clie_lide)  oidCliente,
           cpg.oid_para_gral  oidParaGral,
           SUM(t.val_punt)    sumPuntaje
      FROM lid_recom_lider_tempo t,
           inc_concu_param_gener cpg
     WHERE cpg.num_conc = t.cod_conc
  GROUP BY t.cod_clie_lide,
           cpg.oid_para_gral;

  TYPE interfazLiderEfec IS RECORD(
    oidCliente   MAE_CLIEN.OID_CLIE%TYPE,
    oidParaGral  INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
    sumPuntaje   NUMBER
  );

  TYPE interfazLiderEfecTab IS TABLE OF interfazLiderEfec;
  interfazRecordE interfazLiderEfecTab;

  lsTipoProcesoRecomendacion LID_PARAM_PROCE_RECOM.TIP_PROC_RECO%TYPE;
  lsIndicadorConstancia LID_PARAM.IND_CONS%TYPE;

  lb_efectiva  BOOLEAN;
  lnIdMarca NUMBER;
  lnIdCanal NUMBER;
  lnIdPais NUMBER;
  lb_codPeriodoAnterior VARCHAR2(12);
  ln_facMult NUMBER;
  ls_oid_periodo NUMBER;

BEGIN

  lsTipoProcesoRecomendacion := LID_FN_OBTIE_TIPO_PROCE_ACTIV(psCodPais);
  lsIndicadorConstancia := LID_FN_OBTIE_INDIC_CONST(psCodPais);

   OPEN c_RECO_LIDE;
     LOOP
        FETCH c_RECO_LIDE BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;

          IF interfazRecordN.COUNT > 0 THEN

            FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

              lb_efectiva := FALSE;

              --Se Evalua a la Recomendante y a la Recomendada
              IF( lsTipoProcesoRecomendacion = TIPO_PROCESO_AMBOS) THEN
                 -- si la recomndada y recomendante pasaron pedido
                  IF( LID_FN_PASO_PEDID(interfazRecordN(x).codCliRete, psCodPais, psPeriodoProceso) = 1) AND
                     (LID_FN_PASO_PEDID(interfazRecordN(x).codCliReda, psCodPais, psPeriodoProceso) = 1 ) THEN

                     lnIdPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
                     lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
                     lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

                     lb_codPeriodoAnterior := per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
                                                lnIdPais, lnIdMarca, lnIdCanal, -1);

                     -- si la recomndada y recomendante pasaron pedido en el periodo anterior
                     IF( LID_FN_PASO_PEDID(interfazRecordN(x).codCliRete, psCodPais, lb_codPeriodoAnterior) = 1) AND
                        (LID_FN_PASO_PEDID(interfazRecordN(x).codCliReda, psCodPais, lb_codPeriodoAnterior) = 1 ) THEN

                       ln_facMult := LID_FN_OBTIE_FAC_MULTI_2PED(interfazRecordN(x).codPeriEva,
                                                                 interfazRecordN(x).codConc);

                       IF(((lsIndicadorConstancia = '1') AND ((interfazRecordN(x).codCliLide IS NOT NULL) AND
                           LID_FN_PASO_PEDID(interfazRecordN(x).codCliLide, psCodPais, psPeriodoProceso) = 1))
                          OR (lsIndicadorConstancia = '0')) THEN

                          UPDATE lid_recom_lider a
                             SET a.int_efec = '1',
                                 a.val_punt = ln_facMult,
                                 a.fec_eval = SYSDATE,
                                 a.ind_no_cons = '0'
                           WHERE a.cod_clie_lide = interfazRecordN(x).codCliLide
                             AND a.cod_conc = interfazRecordN(x).codConc
                             AND a.cod_tipo = interfazRecordN(x).codTipo
                             AND a.cod_clie_reda = interfazRecordN(x).codCliReda
                             AND a.cod_peri_eval = psPeriodoProceso
                             AND a.cod_clie_rete = interfazRecordN(x).codCliRete;

                          lb_efectiva := true;

                          INSERT INTO LID_RECOM_LIDER_TEMPO
                          VALUES(
                            interfazRecordN(x).codCliLide,
                            interfazRecordN(x).codConc,
                            interfazRecordN(x).codTipo,
                            NULL,
                            interfazRecordN(x).codCliRete,
                            interfazRecordN(x).codCliReda,
                            interfazRecordN(x).codPeriReo,
                            interfazRecordN(x).codPeriEva,
                            '1',
                            ln_facMult,
                            SYSDATE,
                            psCodPais,
                            psCodMarca
                          );

                       ELSE

                         UPDATE lid_recom_lider a
                            SET a.int_efec = '0',
                                a.fec_eval = SYSDATE,
                                a.ind_no_cons = '1'
                          WHERE a.cod_clie_lide = interfazRecordN(x).codCliLide
                            AND a.cod_conc = interfazRecordN(x).codConc
                            AND a.cod_tipo = interfazRecordN(x).codTipo
                            AND a.cod_clie_reda = interfazRecordN(x).codCliReda
                            AND a.cod_peri_eval = psPeriodoProceso
                            AND a.cod_clie_rete = interfazRecordN(x).codCliRete;

                         lb_efectiva := TRUE;

                       END IF;

                     END IF;
                  END IF;

              ELSIF ( lsTipoProcesoRecomendacion = TIPO_PROCESO_RECOMENDADA) THEN
                -- si la recomendada paso pedido
                IF(LID_FN_PASO_PEDID(interfazRecordN(x).codCliReda, psCodPais, psPeriodoProceso) = 1) THEN

                   lnIdPais  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
                   lnIdMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
                   lnIdCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

                   lb_codPeriodoAnterior := per_pkg_repor_perce.per_fn_obtie_perio(psPeriodoProceso,
                                              lnIdPais, lnIdMarca, lnIdCanal, -1);

                   -- si la recomendada paso pedido en el periodo anterior
                   IF(LID_FN_PASO_PEDID(interfazRecordN(x).codCliReda, psCodPais, lb_codPeriodoAnterior) = 1) THEN

                     ln_facMult := LID_FN_OBTIE_FAC_MULTI_2PED(interfazRecordN(x).codPeriEva, interfazRecordN(x).codConc);

                     IF(((lsIndicadorConstancia = '1') AND ((interfazRecordN(x).codCliLide IS NOT NULL) AND
                         LID_FN_PASO_PEDID(interfazRecordN(x).codCliLide, psCodPais, psPeriodoProceso) = 1))
                       OR (lsIndicadorConstancia = '0')) THEN

                       UPDATE lid_recom_lider a
                          SET a.int_efec = '1',
                              a.val_punt = ln_facMult,
                              a.fec_eval = SYSDATE,
                              a.ind_no_cons = '0'
                        WHERE a.cod_clie_lide = interfazRecordN(x).codCliLide
                          AND a.cod_conc = interfazRecordN(x).codConc
                          AND a.cod_tipo = interfazRecordN(x).codTipo
                          AND a.cod_clie_reda = interfazRecordN(x).codCliReda
                          AND a.cod_peri_eval = psPeriodoProceso
                          AND a.cod_clie_rete = interfazRecordN(x).codCliRete;

                       lb_efectiva := TRUE;

                       INSERT INTO lid_recom_lider_tempo
                       VALUES(
                         interfazRecordN(x).codCliLide,
                         interfazRecordN(x).codConc,
                         interfazRecordN(x).codTipo,
                         NULL,
                         interfazRecordN(x).codCliRete,
                         interfazRecordN(x).codCliReda,
                         interfazRecordN(x).codPeriReo,
                         interfazRecordN(x).codPeriEva,
                         '1',
                         ln_facMult,
                         SYSDATE,
                         psCodPais,
                         psCodMarca
                       );

                     ELSE

                       UPDATE lid_recom_lider a
                          SET a.int_efec ='0',
                              a.fec_eval = SYSDATE,
                              a.ind_no_cons = '1'
                        WHERE a.cod_clie_lide = interfazRecordN(x).codCliLide
                          AND a.cod_conc = interfazRecordN(x).codConc
                          AND a.cod_tipo = interfazRecordN(x).codTipo
                          AND a.cod_clie_reda = interfazRecordN(x).codCliReda
                          AND a.cod_peri_eval = psPeriodoProceso
                          AND a.cod_clie_rete = interfazRecordN(x).codCliRete;

                       lb_efectiva := TRUE;

                     END IF;

                   END IF;

                END IF;
              END IF;

              IF(NOT lb_efectiva)THEN
                UPDATE lid_recom_lider a
                   SET a.int_efec = '0',
                       a.fec_eval = SYSDATE
                 WHERE a.cod_clie_lide = interfazRecordN(x).codCliLide
                   AND a.cod_conc = interfazRecordN(x).codConc
                   AND a.cod_tipo = interfazRecordN(x).codTipo
                   AND a.cod_clie_reda = interfazRecordN(x).codCliReda
                   AND a.cod_peri_eval = psPeriodoProceso
                   AND a.cod_clie_rete = interfazRecordN(x).codCliRete;
              END IF;

            END LOOP;

          END IF;

        EXIT WHEN c_RECO_LIDE%NOTFOUND;
     END LOOP;
   CLOSE c_RECO_LIDE;

   ls_oid_periodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psPeriodoProceso,
                                                                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca),
                                                                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD'));

   OPEN c_RECO_LIDE_EFEC;
     LOOP
       FETCH c_RECO_LIDE_EFEC BULK COLLECT INTO interfazRecordE LIMIT W_FILAS;

         IF interfazRecordE.COUNT > 0 THEN

           FOR x IN interfazRecordE.FIRST .. interfazRecordE.LAST LOOP

             IF(interfazRecordE(x).sumPuntaje) > 0 THEN
               INSERT INTO inc_cuent_corri_punto
               VALUES(
                 INC_CUCP_SEQ.NEXTVAL,
                 INC_CUCP_SEQ.NEXTVAL,
                 interfazRecordE(x).sumPuntaje,
                 '0',
                 TO_DATE(psFechaProceso,'DD/MM/YYYY'),
                 interfazRecordE(x).oidParaGral,
                 interfazRecordE(x).oidCliente,
                 ls_oid_periodo,
                 '1',
                 SYSDATE,
                 'Abono por Recomendación Efectiva(2P) al cierre',
                 NULL,
                 NULL,
                 NULL,
                 NULL,
                 NULL,
                 NULL,
                 NULL
               );
             END IF;

           END LOOP;

         END IF;

       EXIT WHEN c_RECO_LIDE_EFEC%NOTFOUND;
     END LOOP;
   CLOSE c_RECO_LIDE_EFEC;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_EVALU_RECOM_2PEDI_REGIO: '||ls_sqlerrm);

END LID_PR_EVALU_RECOM_2PEDI_REGIO;

/***************************************************************************
Descripcion: Valida los pedidos estimados en las zonas
Fecha Creacion : 12/04/2011
Autor : Jesse James Rios Franco
Parametros Entrada:
 psCodigoPais: Codigo de Pais
 psCodigoPeriodo: Codigo de Periodo
 psCodigoMarca: Codigo de Marca
 psCodigoRetorno: Codigo de Retorno que sera evaluado en java
***************************************************************************/
/*PROCEDURE LID_PR_VALID_PEDID_ESTIM(psCodigoPais    IN  VARCHAR2,
                                   psCodigoPeriodo IN  VARCHAR2,
                                   psCodigoMarca   IN  VARCHAR2,
                                   psCodigoRetorno OUT VARCHAR2,
                                   psCodigoZona    OUT VARCHAR2)IS

   CURSOR C_TEMPORAL_PEDIDOS_OBJETIVOS IS
   SELECT COD_PAIS,COD_PERI,COD_MARC,COD_REGI,COD_ZONA,NVL(SUM(VAL_NUM_PEDI_OBJT),0) AS SUM_PEDI_OBJT
   FROM LID_TEMPO_PEDID_OBJET
   WHERE COD_PAIS = psCodigoPais
   GROUP BY COD_PAIS,COD_PERI,COD_MARC,COD_REGI,COD_ZONA
   ORDER BY COD_PAIS,COD_PERI,COD_MARC,COD_REGI,COD_ZONA;

   TYPE temporalPedidosObjetivosRecord IS RECORD(
      COD_PAIS      LID_TEMPO_PEDID_OBJET.COD_PAIS%TYPE,
      COD_PERI      LID_TEMPO_PEDID_OBJET.COD_PERI%TYPE,
      COD_MARC      LID_TEMPO_PEDID_OBJET.COD_MARC%TYPE,
      COD_REGI      LID_TEMPO_PEDID_OBJET.COD_REGI%TYPE,
      COD_ZONA      LID_TEMPO_PEDID_OBJET.COD_ZONA%TYPE,
      SUM_PEDI_OBJT NUMBER(6,2)
   );

   TYPE temporalPedidosObjetivosTab IS TABLE OF temporalPedidosObjetivosRecord;
   temporalPedidosObjetivos temporalPedidosObjetivosTab;

   vnNumPedi       INT_SAB_VENTA_PREVI_ZONA.NUM_PEDI%TYPE;
   vnSumPedidoObjt LID_SECCI_NUMER_PEDID.VAL_NUM_PEDI_OBJT%TYPE;
   vnSumTotal      LID_SECCI_NUMER_PEDID.VAL_NUM_PEDI_OBJT%TYPE;

BEGIN

    OPEN C_TEMPORAL_PEDIDOS_OBJETIVOS;

    LOOP
    FETCH C_TEMPORAL_PEDIDOS_OBJETIVOS BULK COLLECT INTO temporalPedidosObjetivos LIMIT W_FILAS;

    IF temporalPedidosObjetivos.COUNT > 0 THEN

       FOR i IN temporalPedidosObjetivos.FIRST .. temporalPedidosObjetivos.LAST LOOP
           BEGIN
             SELECT NUM_PEDI
             INTO vnNumPedi
             FROM INT_SAB_VENTA_PREVI_ZONA
             WHERE PAIS_COD_PAIS = temporalPedidosObjetivos(i).COD_PAIS
             AND COD_PERI = temporalPedidosObjetivos(i).COD_PERI
             AND COD_ZONA = temporalPedidosObjetivos(i).COD_ZONA;
           EXCEPTION
           WHEN NO_DATA_FOUND THEN
             vnNumPedi := 0;
           END;

           SELECT NVL(SUM(VAL_NUM_PEDI_OBJT),0) AS SUM_PEDI_OBJT
           INTO vnSumPedidoObjt
           FROM LID_SECCI_NUMER_PEDID
           WHERE COD_PAIS = temporalPedidosObjetivos(i).COD_PAIS
           AND COD_ZONA =  temporalPedidosObjetivos(i).COD_ZONA
           AND COD_SECC NOT IN (SELECT COD_SECC
                                FROM LID_TEMPO_PEDID_OBJET
                                WHERE COD_PAIS = temporalPedidosObjetivos(i).COD_PAIS
                                AND COD_ZONA =  temporalPedidosObjetivos(i).COD_ZONA);

           vnSumTotal := temporalPedidosObjetivos(i).SUM_PEDI_OBJT + vnSumPedidoObjt;

           IF vnSumTotal != vnNumPedi THEN
              psCodigoRetorno := 1;
              psCodigoZona := temporalPedidosObjetivos(i).COD_ZONA;
              RETURN;
           END IF;

       END LOOP;
    END IF;

    EXIT WHEN C_TEMPORAL_PEDIDOS_OBJETIVOS%NOTFOUND;
    END LOOP;

    CLOSE C_TEMPORAL_PEDIDOS_OBJETIVOS;

    psCodigoRetorno := 0;

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR LID_PR_VALID_PEDID_ESTIM: '||ls_sqlerrm);
END LID_PR_VALID_PEDID_ESTIM;*/
end LID_PKG_PROCE_LIDER;
/
