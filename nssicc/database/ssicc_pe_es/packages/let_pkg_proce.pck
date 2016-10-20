CREATE OR REPLACE PACKAGE LET_PKG_PROCE IS

    /* Declaracion de Variables */
     ln_sqlcode NUMBER(10);
     ls_sqlerrm VARCHAR2(150);
     W_FILAS NUMBER := 5000;

     PROCESO_CIERRE_REGION CONSTANT VARCHAR2(3) := 'R';
     PROCESO_CIERRE_CAMPANA CONSTANT VARCHAR2(3) := 'C';

     CONCURSO_SIG_SUBSIG CONSTANT VARCHAR2(3) := 'SSB';
     CONCURSO_VIGENTE CONSTANT VARCHAR2(3) := 'V';
     
  

   /***********************************************************************************************
    Descripcion       : Realiza el resultado de lideres acumulado por concurso al cierre de campa�a
    Fecha Creacion    : 17/02/2011
    Autor             : Jesse Rios
    ***********************************************************************************************/
   PROCEDURE LET_PR_RESUL_LIDER_CONCU_REGIO(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoRegion VARCHAR2,
                                            psUsuario VARCHAR2);

   /***********************************************************************************************
    Descripcion       : Realiza el resultado de lideres acumulado por concurso al cierre de campa�a
    Fecha Creacion    : 15/02/2011
    Autor             : Jesse Rios
    ***********************************************************************************************/
   PROCEDURE LET_PR_RESUL_LIDER_CONCU_CAMPA(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psUsuario VARCHAR2);

   /**********************************************************************************
    Descripcion       : Obtiene el rango de premiacion de acuerdo al numero de pedido
    Fecha Creacion    : 16/02/2011
    Autor             : Jesse Rios
    **********************************************************************************/
   FUNCTION LET_FN_OBTIE_RANGO_PREMI(psCodigoPais VARCHAR2,
                                     pnNumeroPedidos NUMBER,
                                     psCodigoConcurso VARCHAR2)
   RETURN LET_PARAM_RANGO_PREMI.RANG_NUM_RANG%TYPE;

   /**********************************************************************************
    Descripcion       : Obtiene el nivel del concurso de acuerdo al numero de pedido
    Fecha Creacion    : 16/02/2011
    Autor             : Jesse Rios
    **********************************************************************************/
   FUNCTION LET_FN_OBTIE_NIVEL_CONCU(psCodigoPais VARCHAR2,
                                     pnNumeroPedidos NUMBER,
                                     psCodigoLider VARCHAR2,
                                     psCodigoConcurso VARCHAR2)
   RETURN LET_METAS_LIDER_CONCU.NICO_NIVE_CONC%TYPE;

   /**********************************************************************************
    Descripcion       : Genera las recomendaciones de lideres
    Fecha Creacion    : 18/02/2011
    Autor             : Carlos Diaz Valverde
    **********************************************************************************/
   PROCEDURE LET_PR_PROCE_GENER_RECOM_LIDER(psCodigoPais    VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psUsuario       VARCHAR2
                                           );

   /***********************************************************************************************
    Descripcion       : Realiza la enrega de premios por concurso al cierre de campa�a
    Fecha Creacion    : 21/02/2011
    Autor             : Jesse Rios
    ***********************************************************************************************/
   PROCEDURE LET_PR_ENTRE_PREMI_CONCU_CIERR(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoMarca   VARCHAR2,
                                            psCodigoCanal   VARCHAR2);

   /**********************************************************************************
    Descripcion       : Obtenemos la campa�a de asignacion de la unidad administrativa
                        y respecto a su fecha desde
    Fecha Creacion    : 01/03/2011
    Autor             : Carlos Diaz Valverde
   **********************************************************************************/
   FUNCTION LET_FN_OBTIE_CAMPA_ASIGN(psCodigoPais VARCHAR2,
                                     psCodigoMarca VARCHAR2,
                                     psCodigoCanal VARCHAR2,
                                     psUnidadAdministrativa VARCHAR2
                                    )
   RETURN VARCHAR2;

   /***************************************************************************
    Descripcion       : Obtiene el numero de Promedios finales por Zona
    Fecha Creacion    : 01/03/2011
    Autor             : Carlos Diaz Valverde
   ***************************************************************************/
   FUNCTION LET_FN_OBTIE_PROME_ZONA(pnOidZona NUMBER,
                                    pnOidPeriodo NUMBER
                                   )
   RETURN VARCHAR2;

   /***************************************************************************
    Descripcion       : Obtiene el numero de Promedios finales por Seccion
    Fecha Creacion    : 01/03/2011
    Autor             : Carlos Diaz Valverde
   ***************************************************************************/
   FUNCTION LET_FN_OBTIE_PROME_SECCI(pnOidZona NUMBER,
                                     pnOidSeccion NUMBER,
                                     pnOidUltimoPeriodo1 NUMBER
                                    )
   RETURN VARCHAR2;

   /**********************************************************************************
    Descripcion       : Realizamos las diferentes validaciones para ver si se puede
                        efectuar la asignacion de la lider a una respectiva seccion
    Fecha Creacion    : 24/02/2011
    Autor             : Carlos Diaz Valverde
    **********************************************************************************/
   FUNCTION LET_FN_VALID_ASIGN_LIDER_SECCI(psCodigoPais VARCHAR2,
                                           psCodigoMarca VARCHAR2,
                                           psCodigoCanal VARCHAR2,
                                           psCodigoCliente VARCHAR2,
                                           psIndicadorReingreso VARCHAR2,
                                           psIndicadorNoValidaUnicoLider VARCHAR2,
                                           lnNumeroActivasFinalesZona NUMBER,
                                           lnPromedioActFinalesSeccion NUMBER,
                                           psCodigoPeriodoActual VARCHAR2,
                                           psCodigoPeriodoValAsiLid VARCHAR2,
                                           pnOidSeccion NUMBER,
                                           psUnidadAdm VARCHAR2,
                                           pnOidPeriodoAnt NUMBER,
                                           psCodigoPeriodoAsigLiderSgte VARCHAR2,
                                           pscodsubgerencia varchar2,
                                           pscodregi varchar2,
                                           pscodzona varchar2,
                                           pscodsecc varchar2,
                                           psRealizarValidaciones varchar2
                                          )
   RETURN VARCHAR2;

   /***********************************************************************************************
    Descripcion       : Realiza la desvinculacion automatica de lideres
    Fecha Creacion    : 23/02/2011
    Autor             : Jesse Rios
    Facha Actualiza   : 26/05/2011
    Autor Actualiza   : Carlos Diaz Valverde
    ***********************************************************************************************/
   Procedure LET_PR_DESVI_AUTOM_LIDER(psCodigoPais Varchar2,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoRegion VARCHAR2,
                                      psCodigoUsuario VARCHAR2);

   /***********************************************************************************************
    Descripcion       : Realiza el c�lculo de pedidos objetivos por secci�n y campa�a
    Fecha Creacion    : 07/03/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_CALCU_PEDID_OBJEC_SECAM(psCodigoPais VARCHAR2,
                                            psCodigoMarca VARCHAR2,
                                            psCodigoCanal VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoRegion VARCHAR2,
                                            psCodigoUsuario VARCHAR2);

   /***********************************************************************************************
    Descripcion       : Realiza el c�lculo de metas de l�deres por campa�a
    Fecha Creacion    : 07/03/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_CALCU_METAS_LIDER_CAMPA(psCodigoPais VARCHAR2,
                                            psCodigoConcurso VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoRegion VARCHAR2,
                                            psCodigoZona VARCHAR2,
                                            psCodigoSeccion VARCHAR2,
                                            psObjetivoPedido VARCHAR2,
                                            psCodigoUsuario VARCHAR2);

   /***********************************************************************************************
    Descripcion       : Realiza el c�lculo de acumulado de metas de pedidos por concurso
    Fecha Creacion    : 20/10/2011
    Autor             : Jesse James Rios Franco
   ***********************************************************************************************/
   PROCEDURE LET_PR_CALCU_METAS_LIDER_CONCU(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoUsuario VARCHAR2);

   /***********************************************************************************************
    Descripcion       : Realiza el resultado de secciones por campa�a al cierre de campa�a
    Fecha Creacion    : 11/03/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_RESUL_SECCI_CAMPA_CIECA(psCodigoPais VARCHAR2,
                                            psCodigoMarca VARCHAR2,
                                            psCodigoCanal VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoUsuario VARCHAR2);

   /**************************************************************************
    Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
    Fecha Creacion     : 16/03/2011

    Autor              : Jose Cairampoma
                         Jesse Rios
   ***************************************************************************/
   FUNCTION LET_FN_DEVUE_SECUE_NSOLI(psCodigoPais      VARCHAR2,
                                     psCodigoOperacion VARCHAR2,
                                     psCodigoAcceso    VARCHAR2,
                                     psCodigoSubacceso VARCHAR2,
                                     psCodigoCanal     VARCHAR2)
                                     RETURN NUMBER;

   /**************************************************************************
   Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                         separndo n Numeros para inserciones.
   Fecha Creacion     : 16/03/2011

   Autor              : Jose Cairampoma
                        Jesse Rios
  ***************************************************************************/
  FUNCTION LET_FN_RESRV_SECUE_NSOLI(psCodigoPais      VARCHAR2,
                                    psCodigoOperacion VARCHAR2,
                                    psCodigoAcceso    VARCHAR2,
                                    psCodigoSubacceso VARCHAR2,
                                    psCodigoCanal     VARCHAR2,
                                    pnNumReservados   NUMBER)
                                    RETURN NUMBER;

  /***********************************************************************************************
   Descripcion       : Realiza la entrega de premios por campa�a al cierre de campa�a
   Fecha Creacion    : 17/03/2011
   Autor             : Jesse Rios
   ***********************************************************************************************/
   PROCEDURE LET_PR_ENTRE_PREMI_CAMPA_CIERR(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoMarca   VARCHAR2,
                                            psCodigoCanal   VARCHAR2);

   /*********************************************************
    Descripcion : Recupera la campa�as de ingreso de la lider
    Fecha Creacion : 17/03/2011
    Autor : Jesse Rios
    Parametros Entrada: psCodigoClie : Codigo de cliente
    *********************************************************/
    FUNCTION LET_FN_DEVUE_CAMPA_INGRE_LIDER(psCodigoClie VARCHAR2)
                                            RETURN VARCHAR2;

   /***********************************************************************************************
    Descripcion       : Realiza la Evaluaci�n de productividad al cierre de regi�n y campa�a
    Fecha Creacion    : 21/03/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_EVALU_PRODU_REGIO_CAMPA(psCodigoPais VARCHAR2,
                                            psCodigoMarca VARCHAR2,
                                            psCodigoCanal VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoUsuario VARCHAR2);

    /*********************************************************
    Descripcion : Recupera la campa�as de ingreso de la lider
    Fecha Creacion : 30/03/2011
    Autor : Jesse Rios
    *********************************************************/
    PROCEDURE LET_PR_GENER_REPOR_RESUL_LIDER(psCodigoPais VARCHAR2,
                                             psCodigoConcurso VARCHAR2,
                                             psCodigoMarca VARCHAR2,
                                             psCodigoCanal VARCHAR2);

   /***********************************************************************************************
    Descripcion       : Realiza la asignacion y desasignacion de lideres al inicio de campa�a
    Fecha Creacion    : 13/07/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_ASIGN_DESAS_LIDER(psCodigoPais VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      pscodigoUsuario VARCHAR2);

   /*************************************************************************
    Descripcion : Proceso de Caluclo de Pedidos Objetivos por Rezonificacion
    Fecha Creacion : 08/09/2011
    Autor : Jesse Rios
    *************************************************************************/
   PROCEDURE LET_PR_CALCU_PEDID_OBJET_REZON(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoUsuario VARCHAR2);

   /*************************************************************************
    Descripcion : Proceso de Desvinculacion por Lider
    Fecha Creacion : 27/10/2011
    Autor : Jesse James Rios Franco
    *************************************************************************/
   PROCEDURE LET_PR_PROCE_DESVI_LIDER(psCodigoPais VARCHAR2,
                                      pnOidSeccion NUMBER,
                                      psUA VARCHAR2,
                                      pnOidCliente NUMBER,
                                      pnOidPeriodo NUMBER,
                                      pdFechaProceso DATE,
                                      pnIndicadorDesvinculacion NUMBER,
                                      psCodigoUsuario VARCHAR2);

   /*************************************************************************
    Descripcion : Actualiza la Clasificacion Lider
    Fecha Creacion : 24/04/2012
    Autor : Jesse James Rios Franco
    *************************************************************************/
   PROCEDURE LET_PR_ACTUA_CLASI_LIDER(psCodigoPais VARCHAR2,
                                      psTransaccion NUMBER,
                                      psTipoOperacion NUMBER,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoCliente VARCHAR2,
                                      psCodigoUsuario VARCHAR2);

    /*************************************************************************
    Descripcion : Genera la data para el reporte LET de Lideres
    Fecha Creacion : 13/06/2012
    Autor : Jesse James Rios Franco
    *************************************************************************/
    PROCEDURE LET_PR_GENER_REPOR_LIDER(psCodigoPais VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2);

    /*************************************************************************
    Descripcion : Obtiene el tipo de clasifiacacion y clasificacion MAE
    Fecha Creacion : 25/06/2012
    Autor : Jesse James Rios Franco
    *************************************************************************/
    PROCEDURE LET_PR_OBTIE_CLASI_EQUIV(psCodigoClasificacion VARCHAR2,
                                       pnSubClasiLider NUMBER,
                                       psCodigoTipoClasificacionMAE OUT VARCHAR2,
                                       psCodigoClasificacionMAE OUT VARCHAR2);

    /*************************************************************************
    Descripcion : Devuelve la descripcion del tipo de clasificacion del cliente
    Fecha Creacion : 01/08/2012
    Autor : Jesse James Rios Franco
    *************************************************************************/
    FUNCTION LET_FN_DEVUE_DESCR_TIPO_CLASI(psCodigoTipoCliente VARCHAR2,
                                           psCodigoSubTipoCliente VARCHAR2,
                                           psCodigoTipoClasificacion VARCHAR2)
                                           RETURN VARCHAR2;

    /*************************************************************************
    Descripcion : Devuelve la descripcion de la clasificacion del cliente
    Fecha Creacion : 01/08/2012
    Autor : Jesse James Rios Franco
    *************************************************************************/
    FUNCTION LET_FN_DEVUE_DESCR_SUB_CLASI(psCodigoTipoCliente VARCHAR2,
                                          psCodigoSubTipoCliente VARCHAR2,
                                          psCodigoTipoClasificacion VARCHAR2,
                                          psCodigoClasificacion VARCHAR2)
                                          RETURN VARCHAR2;

    /*************************************************************************
    Descripcion : Validacion de desvinculacion
    Fecha Creacion : 21/02/2013
    Autor :
    *************************************************************************/

    FUNCTION LET_FN_VAL_DESVI(pscodregi varchar2,
                             pscodzona varchar2,
                             pscodsecc varchar2,
                             pstipo  varchar2,
                             pscodsubreg  varchar2,
                             vscodigoPeriodoValAsiLid varchar2,
                             psCodigoCliente VARCHAR2,
                             psCodigoPeriodoActual VARCHAR2)
                            RETURN VARCHAR2;

    /**********************************************************************************
    Descripcion       : Proceso de desvinculacion
    Fecha Creacion    : 21/02/2013, 03/04/2014
    Autor             : Anonimo
    Modificado por    : Juan Altamirano
    **********************************************************************************/
     procedure LET_PR_PROC_DESVI(psCodigoPais VARCHAR2,
                                psOidPeriodoValAsiLid varchar2,
                                pscodregi varchar2,
                                pscodzona varchar2,
                                pscodsecc varchar2,
                                pstipo  varchar2,
                                psFechaProceso  varchar2,
                                psCodigoCliente VARCHAR2,
                                psCodigoPeriodoActual VARCHAR2,
                                psusuario VARCHAR2,
                                psIndicadorOrigen VARCHAR2,
                                psIndDesvAuto VARCHAR2);

	  /**********************************************************************************
	  Descripcion       : Proceso de Calculo de pedidos Objetivos por seccion y campa�a
	  Fecha Creacion    : 10/06/2013
	  Autor             : Danny Amaro
	  **********************************************************************************/
	  PROCEDURE LET_PR_CALCU_OBJET_PEDID_LIDER(psCodigoPais VARCHAR2,
	                                           psCodigoMarca VARCHAR2,
	                                           psCodigoCanal VARCHAR2,
	                                           psCodigoPeriodo VARCHAR2,
	                                           psUsuario VARCHAR2);

	  /**********************************************************************************
	  Descripcion       : Proceso de Calcular Objetivo Ingresos Efectivos L�der
	  Fecha Creacion    : 12/06/2013
	  Autor             : Danny Amaro
	  **********************************************************************************/
	  procedure LET_PR_CALCU_OBJET_RET22_LIDER(psCodigoPais VARCHAR2,
	                                           psCodigoRegion VARCHAR2,
	                                           psCodigoMarca VARCHAR2,
	                                           psCodigoCanal VARCHAR2,
	                                           psCodigoPeriodo VARCHAR2,
                                             psTipoProceso VARCHAR2,
	                                           psUsuario VARCHAR2);

    /**********************************************************************************
    Descripcion       : Proceso de Calculo Objetivo Retenci�n 3/3 Secci�n
    Fecha Creacion    : 14/06/2013
    Autor             : Danny Amaro
    **********************************************************************************/
    PROCEDURE LET_PR_CALCU_OBJET_RET33_LIDER(psCodigoPais VARCHAR2,
                                             psCodigoRegion VARCHAR2,
                                             psCodigoMarca VARCHAR2,
                                             psCodigoCanal VARCHAR2,
                                             psCodigoPeriodo VARCHAR2,
                                             psTipoProceso VARCHAR2,
                                             psUsuario VARCHAR2);

    /**********************************************************************************
    Descripcion       : Proceso de Calculo Objetivo Retenci�n 4/4 Secci�n
    Fecha Creacion    : 17/06/2013
    Autor             : Danny Amaro
    **********************************************************************************/
    PROCEDURE LET_PR_CALCU_OBJET_RET44_LIDER(psCodigoPais VARCHAR2,
                                             psCodigoRegion VARCHAR2,
                                             psCodigoMarca VARCHAR2,
                                             psCodigoCanal VARCHAR2,
                                             psCodigoPeriodo VARCHAR2,
                                             psTipoProceso VARCHAR2,
                                             psUsuario VARCHAR2);

    /**********************************************************************************
    Descripcion       : Proceso de Calculo General de Retenciones
    Fecha Creacion    : 18/06/2013
    Autor             : Danny Amaro
    **********************************************************************************/
    PROCEDURE LET_PR_CALCU_OBJET_RETEN(psCodigoPais VARCHAR2,
                                       psCodigoRegion VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
                                       psUsuario VARCHAR2);

	/**********************************************************************************
    Descripcion       : Proceso de Calcular Resultados de Lider en base a sus
                        pedidos e ingresos
    Fecha Creacion    : 13/06/2013
    Autor             : Aurelio Oviedo
    **********************************************************************************/
    procedure LET_PR_CALCU_RESUL_LIDER(psCodigoPais VARCHAR2,
                                       psCodigoRegion VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
                                       psUsuario VARCHAR2);

    /**********************************************************************************
    Descripcion       : Proceso de Actualizaci�n de Clasificaci�n Lider
    Fecha Creacion    : 18/06/2013
    Autor             : Danny Amaro
    **********************************************************************************/
    PROCEDURE LET_PR_ACTUA_CLASI_CORPO(psCodigoPais VARCHAR2,
                                       psCodigoRegion VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psUsuario VARCHAR2);

    /**********************************************************************************
    Descripcion       : Proceso de Clasificaci�n Lider
    Fecha Creacion    : 19/06/2013
    Autor             : Danny Amaro
    **********************************************************************************/
    PROCEDURE LET_PR_PROCE_CLASI_LIDER(psCodigoPais VARCHAR2,
                                        psCodigoMarca VARCHAR2,
                                        psCodigoCanal VARCHAR2,
                                        psTipoEvaluacion VARCHAR2,
                                        psCodGerente VARCHAR2,
                                        psCodigoPeriodo VARCHAR2,
                                        psUsuario VARCHAR2);

    /**********************************************************************************
    Descripcion       : Proceso de Calculo de Nivel de Exito Lider
    Fecha Creacion    : 20/06/2013
    Autor             : Danny Amaro
    **********************************************************************************/
    PROCEDURE LET_PR_PROCE_NIVEL_LIDER(psCodigoPais VARCHAR2,
                                       psCodLider VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
                                       psEscenario VARCHAR2,
                                       psUsuario VARCHAR2);

    /**********************************************************************************
    Descripcion       : Proceso de Calculo de Nivel de Exito Masivo
    Fecha Creacion    : 25/06/2013
    Autor             : Aurelio Oviedo
    **********************************************************************************/
    PROCEDURE LET_PR_CALCU_NIVEL_EXITO(psCodigoPais VARCHAR2,
                                       psCodigoRegion VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
                                       psUsuario VARCHAR2);

    /**********************************************************************************
    Descripcion       : Proceso de Calculo de Ganancia Lider
    Fecha Creacion    : 27/06/2013
    Autor             : Aurelio Oviedo
    **********************************************************************************/
    PROCEDURE LET_PR_CALCU_GANAN_LIDER(psCodigoPais VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoLider VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
                                       psUsuario VARCHAR2);

    /**********************************************************************************
    Descripcion       : Proceso de Calculo de Ganancia Masivo
    Fecha Creacion    : 03/07/2013
    Autor             : Aurelio Oviedo
    **********************************************************************************/
    PROCEDURE LET_PR_CALCU_GANAN_CORPO(psCodigoPais VARCHAR2,
                                       psCodigoRegion VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
                                       psUsuario VARCHAR2);

	/**********************************************************************************
	    Descripcion       : Proceso de Calculo Ganancia Plus
	    Fecha Creacion    : 11/07/2013
	    Autor             : Juan Altamirano
	    **********************************************************************************/
	    PROCEDURE LET_PR_CALCU_GANAN_PLUS_LIDER(psCodigoPais VARCHAR2,
	                                       psCodigoMarca VARCHAR2,
	                                       psCodigoCanal VARCHAR2,
	                                       psCodLider VARCHAR2,
	                                       psCodigoPeriodo VARCHAR2,
	                                       psTipoProceso VARCHAR2,
	                                       psUsuario VARCHAR2);

	/**********************************************************************************
    Descripcion       : Proceso de Registrar Baja Automatica
    Fecha Creacion    : 18/07/2013
    Autor             : Juan Altamirano
    **********************************************************************************/
    PROCEDURE LET_PR_CALCU_BAJA_LIDER(psCodigoPais VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoRegion VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
	                                       psUsuario VARCHAR2);

    /***********************************************************************************************
    Descripcion       : Realiza el Despacho de premios
    Fecha Creacion    : 07/10/2013
    Autor             : Aurelio Oviedo
    ***********************************************************************************************/
    PROCEDURE LET_PR_DESPA_PREMI(psCodigoPais    VARCHAR2,
                                 psCodigoMarca   VARCHAR2,
                                 psCodigoCanal   VARCHAR2,
                                 psCodigoPeriodo VARCHAR2,
                                 psUsuario       VARCHAR2);

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte LET de Resultados
Fecha Creacion    : 13/01/2015
Autor             : Gonzalo Huertas
***************************************************************************/
PROCEDURE LET_PR_REPOR_RESU(
    psCodigoPais                     VARCHAR2,
    psCampanya                       VARCHAR2,
    psNombreArchivo                  VARCHAR2,
    psTitulo                         VARCHAR2,
    psTramo                          VARCHAR2,
    psDirectorio                     OUT  VARCHAR2
    ) ;



END LET_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY LET_PKG_PROCE IS

    /***********************************************************************************************
    Descripcion       : Realiza el resultado de lideres acumulado por concurso al cierre de campa�a
    Fecha Creacion    : 17/02/2011
    Autor             : Jesse Rios
    ***********************************************************************************************/
   PROCEDURE LET_PR_RESUL_LIDER_CONCU_REGIO(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoRegion VARCHAR2,
                                            psUsuario VARCHAR2)IS

    vsCodigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;

    CURSOR C_LIDERES_CAMPANA IS
    SELECT COD_LIDE,CAM_LIDE
    FROM LET_LIDER_CAMPA
    WHERE PAIS_COD_PAIS = psCodigoPais
    AND CAM_LIDE = psCodigoPeriodo
    AND COD_REGI = psCodigoRegion;

    TYPE lideresCampanaRecord IS RECORD(
         COD_LIDE LET_LIDER_CAMPA.COD_LIDE%TYPE,
         CAM_LIDE LET_LIDER_CAMPA.CAM_LIDE%TYPE
    );

    TYPE lideresCampanaTab IS TABLE OF lideresCampanaRecord;
    lideresCampana lideresCampanaTab;

    CURSOR C_RESULTADO_SECCIONES_CAMPANA(codigoLider LET_LIDER_CAMPA.COD_LIDE%TYPE,campanaInicioConcu LET_PARAM_CONCU_LIDER.CAM_INIC%TYPE,campanaFinConcu LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE) IS
    SELECT LIDE_COD_LIDE,CONC_COD_CONC,SUM(NUM_PEDI)AS SUM_NUM_PEDI,SUM(REC_EFEC)AS SUM_REC_EFEC,COUNT(LIDE_COD_LIDE)AS CONT_COD_LIDE
    FROM LET_RESUL_SECCI_CAMPA
    WHERE  PAIS_COD_PAIS = psCodigoPais
    AND  LIDE_COD_LIDE = codigoLider
    AND  LIDE_CAM_LIDE >= campanaInicioConcu
    AND  LIDE_CAM_LIDE <= campanaFinConcu
    GROUP BY LIDE_COD_LIDE,CONC_COD_CONC
    ORDER BY LIDE_COD_LIDE;

    TYPE resultadoSeccionCampanaRecord IS RECORD(

         LIDE_COD_LIDE LET_RESUL_SECCI_CAMPA.LIDE_COD_LIDE%TYPE,
         CONC_COD_CONC LET_RESUL_SECCI_CAMPA.CONC_COD_CONC%TYPE,
         SUM_NUM_PEDI  NUMBER,
         SUM_REC_EFEC  NUMBER,
         CONT_COD_LIDE NUMBER
    );

    TYPE resultadoSeccionCampanaTab IS TABLE OF resultadoSeccionCampanaRecord;
    resultadoSeccionCampana resultadoSeccionCampanaTab;

    vnEncontroReg NATURAL;

    vsCodigoRegion          LET_RESUL_SECCI_CAMPA.COD_REGI%TYPE;
    vsCodigoZona            LET_RESUL_SECCI_CAMPA.COD_ZONA%TYPE;
    vsCodigoSeccion         LET_RESUL_SECCI_CAMPA.COD_SECC%TYPE;
    vsCampanaFinConcu       LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE;
    vsCampanaInicioConcu    LET_PARAM_CONCU_LIDER.CAM_INIC%TYPE;

   BEGIN

        BEGIN
          SELECT COD_CONC,CAM_INIC,CAM_FINA
          INTO vsCodigoConcurso,vsCampanaInicioConcu,vsCampanaFinConcu
          FROM LET_PARAM_CONCU_LIDER
          WHERE PAIS_COD_PAIS = psCodigoPais
          AND psCodigoPeriodo >=  CAM_INIC
          AND psCodigoPeriodo <= CAM_FINA
          AND EST_REGI = 1;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
         vsCodigoConcurso := NULL;
        END;
        IF vsCodigoConcurso IS NOT NULL AND psCodigoPeriodo = vsCampanaFinConcu THEN
           OPEN C_LIDERES_CAMPANA;

           LOOP
               FETCH C_LIDERES_CAMPANA BULK COLLECT INTO lideresCampana LIMIT W_FILAS;

               IF lideresCampana.COUNT > 0 THEN
                  FOR i IN lideresCampana.FIRST .. lideresCampana.LAST LOOP
                      OPEN C_RESULTADO_SECCIONES_CAMPANA(lideresCampana(i).COD_LIDE,vsCampanaInicioConcu,vsCampanaFinConcu);

                      LOOP

                          FETCH C_RESULTADO_SECCIONES_CAMPANA BULK COLLECT INTO resultadoSeccionCampana LIMIT W_FILAS;

                          IF resultadoSeccionCampana.COUNT > 0 THEN

                             FOR j IN resultadoSeccionCampana.FIRST .. resultadoSeccionCampana.LAST LOOP
                                 SELECT COUNT(1)
                                 INTO vnEncontroReg
                                 FROM LET_RESUL_LIDER_CONCU
                                 WHERE PAIS_COD_PAIS = psCodigoPais
                                 AND CONC_COD_CONC = resultadoSeccionCampana(j).CONC_COD_CONC
                                 AND COD_LIDE = resultadoSeccionCampana(j).LIDE_COD_LIDE;

                                 IF vnEncontroReg = 1 THEN

                                    UPDATE LET_RESUL_LIDER_CONCU
                                    SET NUM_PEDI = resultadoSeccionCampana(j).SUM_NUM_PEDI,
                                        REC_EFEC = resultadoSeccionCampana(j).SUM_REC_EFEC,
                                        CAN_CAMP = resultadoSeccionCampana(j).CONT_COD_LIDE
                                    WHERE PAIS_COD_PAIS = psCodigoPais
                                    AND CONC_COD_CONC = resultadoSeccionCampana(j).CONC_COD_CONC
                                    AND COD_LIDE = resultadoSeccionCampana(j).LIDE_COD_LIDE;

                                 ELSE
                                    SELECT A.COD_REGI,A.COD_ZONA,A.COD_SECC
                                    INTO vsCodigoRegion,vsCodigoZona,vsCodigoSeccion
                                    FROM LET_RESUL_SECCI_CAMPA A
                                    WHERE A.PAIS_COD_PAIS = psCodigoPais
                                    AND A.LIDE_COD_LIDE = resultadoSeccionCampana(j).LIDE_COD_LIDE
                                    AND A.LIDE_CAM_LIDE = psCodigoPeriodo;

                                    INSERT INTO LET_RESUL_LIDER_CONCU(
                                     PAIS_COD_PAIS,
                                     CONC_COD_CONC,
                                     COD_LIDE,
                                     REC_EFEC,
                                     NUM_PEDI,
                                     CAN_CAMP,
                                     RAN_PREM,
                                     NIV_CONC,
                                     IND_PEDI,
                                     IND_CAMP,
                                     IND_EFEC,
                                     IND_PREM,
                                     IND_ACTI,
                                     COD_REGI,
                                     COD_ZONA,
                                     COD_SECC,
                                     USU_MODI,
                                     FEC_MODI,
                                     EST_REGI)
                                    VALUES(
                                     psCodigoPais,
                                     resultadoSeccionCampana(j).CONC_COD_CONC,
                                     resultadoSeccionCampana(j).LIDE_COD_LIDE,
                                     resultadoSeccionCampana(j).SUM_REC_EFEC,
                                     resultadoSeccionCampana(j).SUM_NUM_PEDI,
                                     resultadoSeccionCampana(j).CONT_COD_LIDE,
                                     0,
                                     0,
                                     0,
                                     0,
                                     0,
                                     0,
                                     0,
                                     vsCodigoRegion,
                                     vsCodigoZona,
                                     vsCodigoSeccion,
                                     psUsuario,
                                     SYSDATE,
                                     1
                                    );

                                 END IF;

                             END LOOP;

                          END IF;

                      EXIT WHEN C_RESULTADO_SECCIONES_CAMPANA%NOTFOUND;
                      END LOOP;

                      CLOSE C_RESULTADO_SECCIONES_CAMPANA;
                  END LOOP;
               END IF;
           EXIT WHEN C_LIDERES_CAMPANA%NOTFOUND;
           END LOOP;

           CLOSE C_LIDERES_CAMPANA;
        END IF;

   EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_RESUL_LIDER_CONCU_REGIO: ' || ls_sqlerrm);
   END LET_PR_RESUL_LIDER_CONCU_REGIO;

    /***********************************************************************************************
    Descripcion       : Realiza el resultado de lideres acumulado por concurso al cierre de campa�a
    Fecha Creacion    : 15/02/2011
    Autor             : Jesse Rios
    ***********************************************************************************************/
    PROCEDURE LET_PR_RESUL_LIDER_CONCU_CAMPA(psCodigoPais VARCHAR2,
                                             psCodigoPeriodo VARCHAR2,
                                             psUsuario VARCHAR2)IS

    vsCodigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;

    CURSOR C_RESULTADO_SECCIONES_CAMPANA(campanaInicioConcu LET_PARAM_CONCU_LIDER.CAM_INIC%TYPE,campanaFinConcu LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE) IS
    SELECT LIDE_COD_LIDE,CONC_COD_CONC,SUM(NUM_PEDI)AS SUM_NUM_PEDI,SUM(REC_EFEC)AS SUM_REC_EFEC,COUNT(LIDE_COD_LIDE)AS CONT_COD_LIDE
    FROM LET_RESUL_SECCI_CAMPA
    WHERE  PAIS_COD_PAIS = psCodigoPais
    AND  LIDE_CAM_LIDE >= campanaInicioConcu
    AND  LIDE_CAM_LIDE <= campanaFinConcu
    AND  LIDE_COD_LIDE IN (SELECT COD_LIDE
                           FROM LET_LIDER_CAMPA
                           WHERE PAIS_COD_PAIS = psCodigoPais
                           AND CAM_LIDE = psCodigoPeriodo)
    GROUP BY LIDE_COD_LIDE,CONC_COD_CONC
    ORDER BY LIDE_COD_LIDE;

    TYPE resultadoSeccionCampanaRecord IS RECORD(

         LIDE_COD_LIDE LET_RESUL_SECCI_CAMPA.LIDE_COD_LIDE%TYPE,
         CONC_COD_CONC LET_RESUL_SECCI_CAMPA.CONC_COD_CONC%TYPE,
         SUM_NUM_PEDI  NUMBER,
         SUM_REC_EFEC  NUMBER,
         CONT_COD_LIDE NUMBER
    );

    TYPE resultadoSeccionCampanaTab IS TABLE OF resultadoSeccionCampanaRecord;
    resultadoSeccionCampana resultadoSeccionCampanaTab;

    CURSOR C_RESULTADO_LIDERES_CONCURSO(codigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE) IS
    SELECT CONC_COD_CONC,COD_LIDE,CAN_CAMP,NUM_PEDI
    FROM LET_RESUL_LIDER_CONCU
    WHERE PAIS_COD_PAIS = psCodigoPais
    AND CONC_COD_CONC = codigoConcurso;

    TYPE resultadoLideresConcursoRecord IS RECORD(
         CONC_COD_CONC LET_RESUL_LIDER_CONCU.CONC_COD_CONC%TYPE,
         COD_LIDE LET_RESUL_LIDER_CONCU.COD_LIDE%TYPE,
         CAN_CAMP LET_RESUL_LIDER_CONCU.CAN_CAMP%TYPE,
         NUM_PEDI LET_RESUL_LIDER_CONCU.NUM_PEDI%TYPE
    );

    TYPE resultadoLideresConcursoTab IS TABLE OF resultadoLideresConcursoRecord;
    resultadoLideresConcurso resultadoLideresConcursoTab;

    vnEncontroReg NATURAL;

    vsCodigoRegion          LET_RESUL_SECCI_CAMPA.COD_REGI%TYPE;
    vsCodigoZona            LET_RESUL_SECCI_CAMPA.COD_ZONA%TYPE;
    vsCodigoSeccion         LET_RESUL_SECCI_CAMPA.COD_SECC%TYPE;
    vsMaxCampanaLider       LET_RESUL_SECCI_CAMPA.LIDE_CAM_LIDE%TYPE;
    vsCampanaFinConcu       LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE;

    vnIndicadorActiva       LET_RESUL_LIDER_CONCU.IND_ACTI%TYPE;

    vnNumeroPedidos         LET_RESUL_SECCI_CAMPA.NUM_PEDI%TYPE;

    vnRangoPremi            LET_PARAM_RANGO_PREMI.RANG_NUM_RANG%TYPE;
    vnNivelConcurso         LET_METAS_LIDER_CONCU.NICO_NIVE_CONC%TYPE;
    vnMinimoCampanaExigidas LET_PARAM_CONCU_LIDER.MIN_CAMP_EXIG%TYPE;

    vnIndicadorCampana      LET_RESUL_LIDER_CONCU.IND_CAMP%TYPE;
    vnIndicadorPedido       LET_RESUL_LIDER_CONCU.IND_PEDI%TYPE;
    vnIndicadorEfectividad  LET_RESUL_LIDER_CONCU.IND_EFEC%TYPE;
    vnIndicadorPremio       LET_RESUL_LIDER_CONCU.IND_PREM%TYPE;
    vnMontoMinimoPedido     LET_PARAM_CONCU_LIDER.MON_MINI_PEDI%TYPE;
    vnMontoPedidoLider      LET_RESUL_SECCI_CAMPA.MON_PEDI_LIDE%TYPE;
    vsCampanaInicioConcu    LET_PARAM_CONCU_LIDER.CAM_INIC%TYPE;

    vnSumNumPedidosAnte NUMBER;
    vnSumRecEfecAnte    NUMBER;
    vnSumCantCampaAnte NUMBER;

   BEGIN

    BEGIN
      SELECT COD_CONC,CAM_FINA,MIN_CAMP_EXIG,MON_MINI_PEDI,CAM_INIC
      INTO vsCodigoConcurso,vsCampanaFinConcu,vnMinimoCampanaExigidas,vnMontoMinimoPedido,vsCampanaInicioConcu
      FROM LET_PARAM_CONCU_LIDER
      WHERE PAIS_COD_PAIS = psCodigoPais
      AND psCodigoPeriodo >=  CAM_INIC
      AND psCodigoPeriodo <= CAM_FINA
      AND EST_REGI = 1;
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
     vsCodigoConcurso := NULL;
    END;

    IF vsCodigoConcurso IS NOT NULL AND psCodigoPeriodo = vsCampanaFinConcu THEN

      DELETE FROM LET_RESUL_LIDER_CONCU WHERE PAIS_COD_PAIS = psCodigoPais AND CONC_COD_CONC = vsCodigoConcurso;

      OPEN C_RESULTADO_SECCIONES_CAMPANA(vsCampanaInicioConcu,vsCampanaFinConcu);

      LOOP
          FETCH C_RESULTADO_SECCIONES_CAMPANA BULK COLLECT INTO resultadoSeccionCampana LIMIT W_FILAS;

          IF resultadoSeccionCampana.COUNT > 0 THEN

             FOR i IN resultadoSeccionCampana.FIRST .. resultadoSeccionCampana.LAST LOOP

                  SELECT MAX(B.LIDE_CAM_LIDE)
                  INTO vsMaxCampanaLider
                  FROM LET_RESUL_SECCI_CAMPA B
                  WHERE B.PAIS_COD_PAIS = psCodigoPais
                  AND   B.CONC_COD_CONC = vsCodigoConcurso
                  AND B.LIDE_COD_LIDE = resultadoSeccionCampana(i).LIDE_COD_LIDE;

                  SELECT A.COD_REGI,A.COD_ZONA,A.COD_SECC
                  INTO vsCodigoRegion,vsCodigoZona,vsCodigoSeccion
                  FROM LET_RESUL_SECCI_CAMPA A
                  WHERE A.PAIS_COD_PAIS = psCodigoPais
                  AND A.LIDE_COD_LIDE = resultadoSeccionCampana(i).LIDE_COD_LIDE
                  AND A.LIDE_CAM_LIDE = vsMaxCampanaLider;

                  IF vsMaxCampanaLider < vsCampanaFinConcu THEN
                     vnIndicadorActiva := 0;
                  ELSE
                     vnIndicadorActiva := 1;
                  END IF;

                  SELECT COUNT(1)
                  INTO vnEncontroReg
                  FROM LET_RESUL_LIDER_CONCU
                  WHERE PAIS_COD_PAIS = psCodigoPais
                  AND   CONC_COD_CONC = resultadoSeccionCampana(i).CONC_COD_CONC
                  AND   COD_LIDE = resultadoSeccionCampana(i).LIDE_COD_LIDE;

                  IF vnEncontroReg = 0 THEN
                    INSERT INTO LET_RESUL_LIDER_CONCU(
                     PAIS_COD_PAIS,
                     CONC_COD_CONC,
                     COD_LIDE,
                     REC_EFEC,
                     NUM_PEDI,
                     CAN_CAMP,
                     RAN_PREM,
                     NIV_CONC,
                     IND_PEDI,
                     IND_CAMP,
                     IND_EFEC,
                     IND_PREM,
                     IND_ACTI,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     USU_MODI,
                     FEC_MODI,
                     EST_REGI)
                    VALUES(
                     psCodigoPais,
                     resultadoSeccionCampana(i).CONC_COD_CONC,
                     resultadoSeccionCampana(i).LIDE_COD_LIDE,
                     resultadoSeccionCampana(i).SUM_REC_EFEC,
                     resultadoSeccionCampana(i).SUM_NUM_PEDI,
                     resultadoSeccionCampana(i).CONT_COD_LIDE,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     vnIndicadorActiva,
                     vsCodigoRegion,
                     vsCodigoZona,
                     vsCodigoSeccion,
                     psUsuario,
                     SYSDATE,
                     1
                    );
                  ELSE

                    SELECT NUM_PEDI,REC_EFEC,CAN_CAMP
                    INTO vnSumNumPedidosAnte,vnSumRecEfecAnte,vnSumCantCampaAnte
                    FROM LET_RESUL_LIDER_CONCU
                    WHERE PAIS_COD_PAIS = psCodigoPais
                    AND   CONC_COD_CONC = resultadoSeccionCampana(i).CONC_COD_CONC
                    AND   COD_LIDE = resultadoSeccionCampana(i).LIDE_COD_LIDE;

                    vnSumNumPedidosAnte := vnSumNumPedidosAnte + resultadoSeccionCampana(i).SUM_NUM_PEDI;
                    vnSumRecEfecAnte := vnSumRecEfecAnte + resultadoSeccionCampana(i).SUM_REC_EFEC;
                    vnSumCantCampaAnte := vnSumCantCampaAnte + resultadoSeccionCampana(i).CONT_COD_LIDE;

                    UPDATE LET_RESUL_LIDER_CONCU
                    SET    REC_EFEC = vnSumRecEfecAnte,
                           NUM_PEDI = vnSumNumPedidosAnte,
                           CAN_CAMP = vnSumCantCampaAnte,
                           COD_REGI = vsCodigoRegion,
                           COD_ZONA = vsCodigoZona,
                           COD_SECC = vsCodigoSeccion
                    WHERE  PAIS_COD_PAIS = psCodigoPais
                    AND    CONC_COD_CONC = resultadoSeccionCampana(i).CONC_COD_CONC
                    AND    COD_LIDE = resultadoSeccionCampana(i).LIDE_COD_LIDE;
                  END IF;
             END LOOP;
          END IF;
      EXIT WHEN  C_RESULTADO_SECCIONES_CAMPANA%NOTFOUND;
      END LOOP;

      CLOSE C_RESULTADO_SECCIONES_CAMPANA;

      OPEN C_RESULTADO_LIDERES_CONCURSO(vsCodigoConcurso);

        LOOP

        FETCH C_RESULTADO_LIDERES_CONCURSO BULK COLLECT INTO resultadoLideresConcurso LIMIT W_FILAS;

        IF resultadoLideresConcurso.COUNT > 0 THEN

           FOR j IN resultadoLideresConcurso.FIRST .. resultadoLideresConcurso.LAST LOOP
               BEGIN
                 SELECT NUM_PEDI,MON_PEDI_LIDE
                 INTO vnNumeroPedidos,vnMontoPedidoLider
                 FROM LET_RESUL_SECCI_CAMPA
                 WHERE PAIS_COD_PAIS = psCodigoPais
                 AND LIDE_COD_LIDE = resultadoLideresConcurso(j).COD_LIDE
                 AND LIDE_CAM_LIDE = psCodigoPeriodo;
               EXCEPTION
               WHEN NO_DATA_FOUND THEN
                    vnNumeroPedidos := 0;
                    vnMontoPedidoLider := 0;
               END;

               IF vnNumeroPedidos != 0 THEN
                  vnRangoPremi := LET_FN_OBTIE_RANGO_PREMI(psCodigoPais,vnNumeroPedidos,vsCodigoConcurso);
               ELSE
                  vnRangoPremi := 0;
               END IF;

               IF vnRangoPremi > 0 THEN
                  vnNivelConcurso := LET_FN_OBTIE_NIVEL_CONCU(psCodigoPais,resultadoLideresConcurso(j).NUM_PEDI,resultadoLideresConcurso(j).COD_LIDE,vsCodigoConcurso);
               ELSE
                  vnNivelConcurso := 0;
               END IF;

               IF vnNivelConcurso = 0 THEN
                  vnRangoPremi := 0;
               END IF;

               IF resultadoLideresConcurso(j).CAN_CAMP >= vnMinimoCampanaExigidas THEN
                  vnIndicadorCampana := 1;
               ELSE
                  vnIndicadorCampana := 0;
                  vnNivelConcurso := 0;
                  vnRangoPremi := 0;
               END IF;

               IF vnMontoPedidoLider >= vnMontoMinimoPedido THEN
                  vnIndicadorPedido := 1;
               ELSE
                  vnIndicadorPedido := 0;
               END IF;

               IF vnMontoPedidoLider = 0 THEN
                  vnIndicadorPedido := 0;
               END IF;

               IF (vnRangoPremi >= 1 AND vnNivelConcurso >= 1) AND (vnIndicadorCampana = 1 AND vnIndicadorPedido = 1) THEN
                  vnIndicadorEfectividad := 1;
               ELSE
                  vnIndicadorEfectividad := 2;
               END IF;

               vnIndicadorPremio := 0;

               UPDATE LET_RESUL_LIDER_CONCU
               SET    RAN_PREM = vnRangoPremi,
                      NIV_CONC = vnNivelConcurso,
                      IND_PEDI = vnIndicadorPedido,
                      IND_CAMP = vnIndicadorCampana,
                      IND_EFEC = vnIndicadorEfectividad,
                      IND_PREM = vnIndicadorPremio
               WHERE PAIS_COD_PAIS = psCodigoPais
               AND   CONC_COD_CONC = resultadoLideresConcurso(j).CONC_COD_CONC
               AND   COD_LIDE = resultadoLideresConcurso(j).COD_LIDE;

           END LOOP;

        END IF;

        EXIT WHEN C_RESULTADO_LIDERES_CONCURSO%NOTFOUND;
        END LOOP;
        CLOSE C_RESULTADO_LIDERES_CONCURSO;
    END IF;

   EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_RESUL_LIDER_CONCU_CAMPA: ' || ls_sqlerrm);
   END  LET_PR_RESUL_LIDER_CONCU_CAMPA;
   /**********************************************************************************
    Descripcion       : Obtiene el rango de premiacion de acuerdo al numero de pedido
    Fecha Creacion    : 16/02/2011
    Autor             : Jesse Rios
   **********************************************************************************/
   FUNCTION LET_FN_OBTIE_RANGO_PREMI(psCodigoPais VARCHAR2,
                                     pnNumeroPedidos NUMBER,
                                     psCodigoConcurso VARCHAR2)
                                     RETURN LET_PARAM_RANGO_PREMI.RANG_NUM_RANG%TYPE IS

     CURSOR C_RANGO_PREMI IS
     SELECT RANG_NUM_RANG,CAN_PEDI
     FROM LET_PARAM_RANGO_PREMI
     WHERE PAIS_COD_PAIS = psCodigoPais
     AND CONC_COD_CONC = psCodigoConcurso
     AND EST_REGI = 1;

     TYPE rangoPremiRecord IS RECORD(
          RANG_NUM_RANG LET_PARAM_RANGO_PREMI.RANG_NUM_RANG%TYPE,
          CAN_PEDI LET_PARAM_RANGO_PREMI.CAN_PEDI%TYPE
     );
     TYPE rangoPremiTab IS TABLE OF rangoPremiRecord;
     rangoPremi rangoPremiTab;

     vnRangoPremi LET_PARAM_RANGO_PREMI.RANG_NUM_RANG%TYPE := 0;

   BEGIN

       OPEN C_RANGO_PREMI;

       LOOP

           FETCH C_RANGO_PREMI BULK COLLECT INTO rangoPremi LIMIT W_FILAS;

           IF rangoPremi.COUNT > 0 THEN

              FOR i IN rangoPremi.FIRST .. rangoPremi.LAST LOOP

                 IF pnNumeroPedidos >= rangoPremi(i).CAN_PEDI THEN

                    vnRangoPremi := rangoPremi(i).RANG_NUM_RANG;

                 END IF;

              END LOOP;

           END IF;

       EXIT WHEN C_RANGO_PREMI%NOTFOUND;
       END LOOP;

       CLOSE C_RANGO_PREMI;

       RETURN vnRangoPremi;
   EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_FN_OBTIE_RANGO_PREMI: ' || ls_sqlerrm);
   END LET_FN_OBTIE_RANGO_PREMI;

   /**********************************************************************************
    Descripcion       : Obtiene el nivel del concurso de acuerdo al numero de pedido
    Fecha Creacion    : 16/02/2011
    Autor             : Jesse Rios
   **********************************************************************************/
   FUNCTION LET_FN_OBTIE_NIVEL_CONCU(psCodigoPais VARCHAR2,
                                     pnNumeroPedidos NUMBER,
                                     psCodigoLider VARCHAR2,
                                     psCodigoConcurso VARCHAR2)
                                     RETURN LET_METAS_LIDER_CONCU.NICO_NIVE_CONC%TYPE IS

     CURSOR C_NIVEL_CONCURSO IS
     SELECT NICO_NIVE_CONC,MET_PEDI
     FROM LET_METAS_LIDER_CONCU
     WHERE PAIS_COD_PAIS = psCodigoPais
     AND COD_LIDE = psCodigoLider
     AND CONC_COD_CONC = psCodigoConcurso
     AND EST_REGI = 1;

     TYPE nivelConcursoRecord IS RECORD(
          NICO_NIVE_CONC LET_METAS_LIDER_CONCU.NICO_NIVE_CONC%TYPE,
          MET_PEDI LET_METAS_LIDER_CONCU.MET_PEDI%TYPE
     );
     TYPE nivelConcursoTab IS TABLE OF nivelConcursoRecord;
     nivelConcurso nivelConcursoTab;

     vnNivelConcurso LET_METAS_LIDER_CONCU.NICO_NIVE_CONC%TYPE := 0;

   BEGIN

        OPEN C_NIVEL_CONCURSO;

        LOOP

            FETCH C_NIVEL_CONCURSO BULK COLLECT INTO nivelConcurso LIMIT W_FILAS;

            IF nivelConcurso.COUNT > 0 THEN

               FOR i IN nivelConcurso.FIRST .. nivelConcurso.LAST LOOP

                   IF pnNumeroPedidos >= nivelConcurso(i).MET_PEDI THEN
                      vnNivelConcurso := nivelConcurso(i).NICO_NIVE_CONC;
                   END IF;

               END LOOP;

            END IF;
        EXIT WHEN C_NIVEL_CONCURSO%NOTFOUND;
        END LOOP;

        CLOSE C_NIVEL_CONCURSO;

        RETURN vnNivelConcurso;
   EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_FN_OBTIE_NIVEL_CONCU: ' || ls_sqlerrm);
   END LET_FN_OBTIE_NIVEL_CONCU;

   /***********************************************************************************************
    Descripcion       : Genera las recomendaciones de lider
    Fecha Creacion    : 18/02/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_PROCE_GENER_RECOM_LIDER(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psUsuario VARCHAR2) IS

     -- Declarar cursor lideres campa�a
     CURSOR C_LIDERES_CAMPANA IS
       SELECT    COD_LIDE,
                 COD_ZONA
       FROM      LET_LIDER_CAMPA
       WHERE     PAIS_COD_PAIS = psCodigoPais
         AND     EST_REGI = 1
         AND     CAM_LIDE = psCodigoPeriodo;
     TYPE lideresCampanaRecord IS RECORD(
       COD_LIDE LET_LIDER_CAMPA.COD_LIDE%TYPE,
       COD_ZONA LET_LIDER_CAMPa.COD_ZONA%TYPE
     );
     TYPE lideresCampanaTab IS TABLE OF lideresCampanaRecord;
     lideresCampana lideresCampanaTab;

     -- Declarar cursor para capturar las Recomendadas
     CURSOR C_RESULTADO_LIDERES_CAMPANA(
       oidLider MAE_CLIEN.OID_CLIE%TYPE,
       oidPeriodo CRA_PERIO.OID_PERI%TYPE
     ) IS
       SELECT    MV.CLIE_OID_CLIE_VNDO
       FROM      MAE_CLIEN_VINCU MV,
                 MAE_CLIEN_PRIME_CONTA MC
       WHERE     MV.CLIE_OID_CLIE_VNTE = oidLider
         AND     MV.TIVC_OID_TIPO_VINC = 9
         AND     MV.CLIE_OID_CLIE_VNDO = MC.CLIE_OID_CLIE
         AND     MC.PERD_OID_PERI = oidPeriodo;
     TYPE resultadosCampanaRecord IS RECORD(
       CLIE_OID_VNDO MAE_CLIEN_VINCU.CLIE_OID_CLIE_VNDO%TYPE
     );
     TYPE resultadosCampanaTab IS TABLE OF resultadosCampanaRecord;
     resultadosCampana resultadosCampanaTab;

     -- Variables
     vnContar        NUMBER(1);
     vnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;
     vnOidLider      MAE_CLIEN.OID_CLIE%TYPE;
     vnFlagValida    NUMBER(3);
     vsCodReco       MAE_CLIEN.COD_CLIE%TYPE;
     vsValParametro  BAS_PARAM_PAIS.VAL_PARA%TYPE;
     vnContadorLideresCampa NUMBER;
     vsCodigoZonaVinculado ZON_ZONA.COD_ZONA%TYPE;

    BEGIN

       -- Validar que la recomendada pertenesca a la zona
       select VAL_PARA
       into  vsValParametro
       from BAS_PARAM_PAIS
       where COD_PAIS = psCodigoPais
       and   COD_SIST = 'LET'
       and   COD_PARA = '004';

       -- Eliminar valores si existen de la campa�a
       DELETE
       FROM      LET_RECOM_LIDER
       WHERE     PAIS_COD_PAIS = psCodigoPais
         AND     LIDE_CAM_LIDE = psCodigoPeriodo
         AND     CAM_EVAL IS NULL;

       -- oidPeriodo
       vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

       -- Si no se encuentra Lideres por campa�a se procesa de la tabla ZON_HISTO_GEREN
       SELECT COUNT(1)
       INTO vnContadorLideresCampa
       FROM LET_LIDER_CAMPA
       WHERE PAIS_COD_PAIS = psCodigoPais
       AND   EST_REGI = 1
       AND   CAM_LIDE = psCodigoPeriodo;

       IF vnContadorLideresCampa = 0 THEN
          INSERT INTO LET_LIDER_CAMPA(PAIS_COD_PAIS,CAM_LIDE,COD_LIDE,COD_REGI,COD_ZONA,COD_SECC,EST_REGI,USU_MODI,FEC_MODI)
          SELECT psCodigoPais as COD_PAIS,psCodigoPeriodo as CAM_LIDE,GERE as COD_LIDE,SUBSTR(UA,3,2) as COD_REGI,SUBSTR(UA,5,4) as COD_ZONA,SUBSTR(UA,9) as COD_SECCI,'1' as EST_REGI,psUsuario as USU_MODI,sysdate as FEC_MODI
          FROM ZON_HISTO_GEREN
          WHERE length(ua) = 9
          AND vnOidPeriodo >= PERD_OID_PERI_DESD
          AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);
       END IF;

       -- Recorrer Cursor de Lideres por Campa�a
       OPEN C_LIDERES_CAMPANA;
       LOOP

           FETCH C_LIDERES_CAMPANA BULK COLLECT INTO lideresCampana LIMIT W_FILAS;

           IF lideresCampana.COUNT > 0 THEN

               -- Recorrer Cursor para capturar el Recomendado por Lider
               FOR i IN lideresCampana.FIRST .. lideresCampana.LAST LOOP

                   -- oidLider
                   vnOidLider := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(lideresCampana(i).COD_LIDE);

                   OPEN C_RESULTADO_LIDERES_CAMPANA(
                     vnOidLider,
                     vnOidPeriodo
                   );
                   LOOP

                       FETCH C_RESULTADO_LIDERES_CAMPANA BULK COLLECT INTO resultadosCampana LIMIT W_FILAS;

                       IF resultadosCampana.COUNT > 0 THEN

                           FOR j IN resultadosCampana.FIRST .. resultadosCampana.LAST LOOP

                               -- Validar que las recomendaciones  hayan pasado pedido en la campa�a de cierre
                               SELECT    COUNT(1)
                                 INTO    vnFlagValida
                               FROM      PED_SOLIC_CABEC PS,
                                         PED_TIPO_SOLIC_PAIS TP,
                                         PED_TIPO_SOLIC TS
                               WHERE     PS.TSPA_OID_TIPO_SOLI_PAIS = TP.OID_TIPO_SOLI_PAIS
                                 AND     TP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                                 AND     PS.FEC_FACT IS NOT NULL
                                 AND     PS.IND_TS_NO_CONSO = 1
                                 AND     PS.IND_OC = 1
                                 AND     PS.IND_PEDI_PRUE = 0
                                 AND     TS.IND_DEVO = 0
                                 AND     TS.IND_ANUL = 0
                                 AND     PS.PERD_OID_PERI = vnOidPeriodo
                                 AND     PS.CLIE_OID_CLIE = resultadosCampana(j).CLIE_OID_VNDO;

                               IF vnFlagValida > 0 THEN

                                 vsCodReco := Gen_Pkg_Gener.GEN_FN_DEVUELVE_COD_CLIE(resultadosCampana(j).CLIE_OID_VNDO);

                                 -- Validar si existe Recomendado
                                 SELECT    COUNT(1)
                                   INTO    vnContar
                                 FROM      LET_RECOM_LIDER
                                 WHERE     PAIS_COD_PAIS = psCodigoPais
                                   AND     LIDE_CAM_LIDE = psCodigoPeriodo
                                   AND     LIDE_COD_LIDE = lideresCampana(i).COD_LIDE
                                   AND     COD_RECO = vsCodReco;

                                 -- Insertar Recomendados
                                 IF vnContar = 0 THEN

                                   IF vsValParametro = 1 THEN
                                      SELECT d.COD_ZONA
                                      INTO vsCodigoZonaVinculado
                                      FROM MAE_CLIEN_UNIDA_ADMIN a,
                                           ZON_TERRI_ADMIN b,
                                           ZON_SECCI c,
                                           ZON_ZONA d
                                      WHERE a.ZTAD_OID_TERR_ADMI = b.OID_TERR_ADMI
                                      AND b.ZSCC_OID_SECC = c.OID_SECC
                                      AND c.ZZON_OID_ZONA = d.OID_ZONA
                                      AND a.CLIE_OID_CLIE = resultadosCampana(j).CLIE_OID_VNDO
                                      AND vnOidPeriodo >= a.PERD_OID_PERI_INI
                                      AND (vnOidPeriodo <= a.PERD_OID_PERI_FIN OR a.PERD_OID_PERI_FIN IS NULL);

                                      IF vsCodigoZonaVinculado = lideresCampana(i).COD_ZONA THEN
                                         INSERT INTO LET_RECOM_LIDER(
                                           PAIS_COD_PAIS,
                                           LIDE_CAM_LIDE,
                                           LIDE_COD_LIDE,
                                           COD_RECO,
                                           IND_EFEC,
                                           IND_NCON,
                                           CAM_EVAL,
                                           USU_MODI,
                                           FEC_MODI,
                                           EST_REGI
                                         )
                                         VALUES(
                                           psCodigoPais,
                                           psCodigoPeriodo,
                                           lideresCampana(i).COD_LIDE,
                                           vsCodReco,
                                           0,
                                           0,
                                           NULL,
                                           psUsuario,
                                           SYSDATE,
                                           1
                                         );
                                      END IF;

                                   ELSE

                                      INSERT INTO LET_RECOM_LIDER(
                                         PAIS_COD_PAIS,
                                         LIDE_CAM_LIDE,
                                         LIDE_COD_LIDE,
                                         COD_RECO,
                                         IND_EFEC,
                                         IND_NCON,
                                         CAM_EVAL,
                                         USU_MODI,
                                         FEC_MODI,
                                         EST_REGI
                                       )
                                       VALUES(
                                         psCodigoPais,
                                         psCodigoPeriodo,
                                         lideresCampana(i).COD_LIDE,
                                         vsCodReco,
                                         0,
                                         0,
                                         NULL,
                                         psUsuario,
                                         SYSDATE,
                                         1
                                       );
                                   END IF;

                                 END IF;

                               END IF; -- Validar que las recomendaciones  hayan pasado pedido en la campa�a de cierre

                           END LOOP;

                       END IF;

                       EXIT WHEN C_RESULTADO_LIDERES_CAMPANA%NOTFOUND;
                   END LOOP;
                   CLOSE C_RESULTADO_LIDERES_CAMPANA;

               END LOOP;

           END IF;

           EXIT WHEN C_LIDERES_CAMPANA%NOTFOUND;
       END LOOP;
       CLOSE C_LIDERES_CAMPANA;

    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_PROCE_GENER_RECOM_LIDER: ' || ls_sqlerrm);
    END LET_PR_PROCE_GENER_RECOM_LIDER;

   /***********************************************************************************************
   Descripcion       : Realiza la enrega de premios por concurso al cierre de campa�a
   Fecha Creacion    : 21/02/2011
   Autor             : Jesse Rios
   ***********************************************************************************************/
   PROCEDURE LET_PR_ENTRE_PREMI_CONCU_CIERR(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoMarca   VARCHAR2,
                                            psCodigoCanal   VARCHAR2)IS

   vsCodigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;

   CURSOR C_RESULTADO_LIDERES_CONCURSO(codigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE) IS
   SELECT CONC_COD_CONC,COD_LIDE,RAN_PREM,NIV_CONC,COD_ZONA
   FROM LET_RESUL_LIDER_CONCU
   WHERE PAIS_COD_PAIS = psCodigoPais
   AND CONC_COD_CONC = codigoConcurso
   AND IND_EFEC = 1
   AND IND_PREM = 0;

   TYPE resultadoLideresConcursoRecord IS RECORD(
        CONC_COD_CONC LET_RESUL_LIDER_CONCU.CONC_COD_CONC%TYPE,
        COD_LIDE      LET_RESUL_LIDER_CONCU.COD_LIDE%TYPE,
        RAN_PREM      LET_RESUL_LIDER_CONCU.RAN_PREM%TYPE,
        NIV_CONC      LET_RESUL_LIDER_CONCU.NIV_CONC%TYPE,
        COD_ZONA      LET_RESUL_LIDER_CONCU.COD_ZONA%TYPE
   );

   TYPE resultadoLideresConcursoTab IS TABLE OF resultadoLideresConcursoRecord;
   resultadoLideresConcurso resultadoLideresConcursoTab;

   vnOidPais       SEG_PAIS.OID_PAIS%TYPE;
   vnOidMarca      SEG_MARCA.OID_MARC%TYPE;
   vnOidCanal      SEG_CANAL.OID_CANA%TYPE;
   vsCampanaSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE;
   vnOidPeriodoSiguiente SEG_PERIO_CORPO.OID_PERI%TYPE;

   vsCodigoVenta      LET_PREMI_CONCU.COD_VENT%TYPE;
   vnOidTipoSoliPais  LET_PARAM_GENER.OID_TIPO_SOLI%TYPE;
   vnFormaPagoEnv     PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO%TYPE;
   vnClaseSolicEnv    PED_CLASE_SOLIC.OID_CLAS_SOLI%TYPE;
   vnOidAlmacEnv      PED_TIPO_SOLIC_PAIS.ALMC_OID_ALMA%TYPE;
   vnTipoSoliCons     PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS%TYPE;
   vnTipoDocum2       PED_TIPO_SOLIC_PAIS.TIDO_OID_TIPO_DOCU%TYPE;
   vnSubac            PED_TIPO_SOLIC.SBAC_OID_SBAC%TYPE;
   vnSocie            PED_TIPO_SOLIC_PAIS.SOCI_OID_SOCI%TYPE;

   vnOidEjecutiva     MAE_CLIEN.OID_CLIE%TYPE;
   vnOidClieDire      MAE_CLIEN_DIREC.OID_CLIE_DIRE%TYPE;
   vnOidTipoDocu      MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU%TYPE;
   vnOidTerr          ZON_TERRI_ADMIN.TERR_OID_TERR%TYPE;
   vnOidZona          ZON_ZONA.OID_ZONA%TYPE;
   vnOidValorEstrGeop ZON_TERRI.VEPO_OID_VALO_ESTR_GEOP%TYPE;
   vnOidTerrAdmi      MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI%TYPE;
   vnOidFormPago      MAE_CLIEN.FOPA_OID_FORM_PAGO%TYPE;
   vnImpPrecPosi      PRE_OFERT_DETAL.IMP_PREC_POSI%TYPE;

   vnOidSolicCabec    NUMBER;

   vnOidProducto      PRE_OFERT_DETAL.PROD_OID_PROD%TYPE;

   vnOidTipoCliente    MAE_TIPO_CLIEN.OID_TIPO_CLIE%TYPE;
   vnOidSubTipoCliente MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;
   vnNumSoliInicio     NUMBER;
   vnNumSoliFormato    NUMBER;
   vnNumeroCabeceras   NUMBER;
   vsCampanaFinConcu   LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE;
   vnFecFinPerio       CRA_PERIO.Fec_Fina%TYPE;
   vnOidDetalleOferta  pre_ofert_detal.Oid_Deta_Ofer%TYPE;

   BEGIN

      BEGIN
        SELECT COD_CONC,CAM_FINA
        INTO vsCodigoConcurso,vsCampanaFinConcu
        FROM LET_PARAM_CONCU_LIDER
        WHERE PAIS_COD_PAIS = psCodigoPais
        AND psCodigoPeriodo >=  CAM_INIC
        AND psCodigoPeriodo <= CAM_FINA
        AND EST_REGI = 1;
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
       vsCodigoConcurso := NULL;
      END;

      IF vsCodigoConcurso IS NOT NULL AND psCodigoPeriodo = vsCampanaFinConcu THEN

       vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
       vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
       vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
       vsCampanaSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,1);
       vnOidPeriodoSiguiente := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(vsCampanaSiguiente,vnOidMarca,vnOidCanal);

       BEGIN
         SELECT OID_TIPO_SOLI
         INTO   vnOidTipoSoliPais
         FROM LET_PARAM_GENER
         WHERE PAIS_COD_PAIS = psCodigoPais;
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
           vnOidTipoSoliPais := 0;
       END;

       SELECT COUNT(1)
       INTO vnNumeroCabeceras
       FROM LET_RESUL_LIDER_CONCU
       WHERE PAIS_COD_PAIS = psCodigoPais
       AND CONC_COD_CONC = vsCodigoConcurso
       AND IND_EFEC = 1
       AND IND_PREM = 0;

       IF vnNumeroCabeceras > 0 THEN
          vnNumSoliInicio := LET_FN_RESRV_SECUE_NSOLI(psCodigoPais,'PED001','GZ','000',psCodigoCanal,vnNumeroCabeceras);
          vnNumSoliFormato :=  TO_CHAR(SYSDATE, 'yy') || LPAD(vnNumSoliInicio+1, 8, '0');

          SELECT TO_DATE((TO_CHAR(FEC_FINA,'dd/MM/yyyy')),'dd/MM/yyyy')AS FEC_FIN
          INTO vnFecFinPerio
          FROM CRA_PERIO
          WHERE OID_PERI = vnOidPeriodoSiguiente;
       END IF;

       OPEN C_RESULTADO_LIDERES_CONCURSO(vsCodigoConcurso);

       LOOP

           FETCH C_RESULTADO_LIDERES_CONCURSO BULK COLLECT INTO resultadoLideresConcurso LIMIT W_FILAS;

           IF resultadoLideresConcurso.COUNT > 0 THEN

              FOR i IN resultadoLideresConcurso.FIRST .. resultadoLideresConcurso.LAST LOOP

               BEGIN
                 SELECT COD_VENT
                 INTO vsCodigoVenta
                 FROM LET_PREMI_CONCU
                 WHERE PAIS_COD_PAIS = psCodigoPais
                 AND CONC_COD_CONC = vsCodigoConcurso
                 AND CAM_DESP = vsCampanaSiguiente
                 AND RANG_NUM_RANG = resultadoLideresConcurso(i).RAN_PREM
                 AND NICO_NIVE_CONC = resultadoLideresConcurso(i).NIV_CONC
                 AND EST_REGI = 1
                 AND COD_VENT != '0';-- PER-SiCC-2012-0519
               EXCEPTION
               WHEN NO_DATA_FOUND THEN
                    vsCodigoVenta := 0;
               END;

               IF vsCodigoVenta <> 0 THEN

                 SELECT ofedet.Prod_Oid_Prod,ofedet.IMP_PREC_POSI,ofedet.oid_deta_ofer
                 INTO   vnOidProducto,vnImpPrecPosi,vnOidDetalleOferta
              	 FROM  pre_ofert ofe,
              		     pre_ofert_detal ofedet,
              		     pre_matri_factu mf,
              		     pre_matri_factu_cabec mfc,
              		     mae_produ prod,
              		     (SELECT v.val_oid, v.val_i18n
              		      FROM v_gen_i18n_sicc v
              		      WHERE v.attr_enti = 'MAE_PRODU'
              		      AND v.idio_oid_idio = 1) i18prod
              		WHERE mfc.perd_oid_peri = vnOidPeriodoSiguiente
              		AND mf.mfca_oid_cabe = mfc.oid_cabe
              		AND ofe.mfca_oid_cabe = mfc.oid_cabe
              		AND ofe.oid_ofer = ofedet.ofer_oid_ofer
              		AND ofedet.oid_deta_ofer = mf.ofde_oid_deta_ofer
              		AND ofedet.val_codi_vent = vsCodigoVenta
              		AND ofedet.prod_oid_prod = prod.oid_prod
              		AND prod.oid_prod = i18prod.val_oid;

                 SELECT A.FOPA_OID_FORM_PAGO,
                  	    d.OID_CLAS_SOLI,
                	      A.ALMC_OID_ALMA,
                	      A.TSOL_OID_TIPO_CONS,
                	      A.TIDO_OID_TIPO_DOCU,
                	      c.SBAC_OID_SBAC,
                	      A.SOCI_OID_SOCI
                	 INTO vnFormaPagoEnv,
                  	    vnClaseSolicEnv,
                  	    vnOidAlmacEnv,
                  		  vnTipoSoliCons,
                  		  vnTipoDocum2,
                  		  vnSubac,
                		    vnSocie
                	 FROM ped_tipo_solic_pais A,
                	      ped_tipo_solic      c,
                	      ped_clase_solic     d
                	WHERE A.OID_TIPO_SOLI_PAIS = vnOidTipoSoliPais
                	  AND A.TSOL_OID_TIPO_SOLI = c.OID_TIPO_SOLI
                	  AND c.CLSO_OID_CLAS_SOLI = d.OID_CLAS_SOLI;

                  SELECT OID_CLIE,
                         FOPA_OID_FORM_PAGO
                    INTO vnOidEjecutiva,
                         vnOidFormPago
                    FROM MAE_CLIEN
                   WHERE PAIS_OID_PAIS = vnOidPais
                     AND COD_CLIE = resultadoLideresConcurso(i).COD_LIDE;

                  -------------------------------------------------
                  IF (vnFormaPagoEnv IS NULL) THEN
                    IF (vnOidFormPago IS NOT NULL) THEN
                       vnFormaPagoEnv := vnOidFormPago;
                    ELSE
                      SELECT fopa_oid_form_pago
                        INTO vnFormaPagoEnv
                        FROM seg_pais a
                       WHERE a.oid_pais = vnOidPais;
                    END IF;
                  END IF;

                  BEGIN
                    SELECT val_tipo_camb
                      INTO vnOidFormPago
                      FROM pre_matri_factu_cabec a
                     WHERE a.perd_oid_peri = vnOidPeriodoSiguiente;
                  EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    vnOidFormPago := '';
                  END;
                  --------------------------------------------------

                  SELECT OID_CLIE_DIRE
                  INTO vnOidClieDire
                  FROM MAE_CLIEN_DIREC
                  WHERE CLIE_OID_CLIE = vnOidEjecutiva
                  AND   IND_DIRE_PPAL = 1
                  AND   IND_ELIM = 0;

                  SELECT TDOC_OID_TIPO_DOCU
                  INTO vnOidTipoDocu
                  FROM MAE_CLIEN_IDENT
                  WHERE CLIE_OID_CLIE = vnOidEjecutiva
                  AND VAL_IDEN_DOCU_PRIN = 1;

                  SELECT ztad.TERR_OID_TERR,mcua.ZTAD_OID_TERR_ADMI
                  INTO vnOidTerr,vnOidTerrAdmi
                  FROM MAE_CLIEN_UNIDA_ADMIN mcua,
                       ZON_TERRI_ADMIN ztad
                  WHERE ztad.PAIS_OID_PAIS = vnOidPais
                  AND   mcua.Ztad_Oid_Terr_Admi = ztad.oid_terr_admi
                  AND   mcua.CLIE_OID_CLIE = vnOidEjecutiva
                  AND   mcua.IND_ACTI = 1;

                  SELECT OID_ZONA
                  INTO vnOidZona
                  FROM ZON_ZONA
                  WHERE PAIS_OID_PAIS = vnOidPais
                  AND COD_ZONA = resultadoLideresConcurso(i).COD_ZONA;

                  SELECT VEPO_OID_VALO_ESTR_GEOP
                  INTO vnOidValorEstrGeop
                  FROM ZON_TERRI
                  WHERE PAIS_OID_PAIS = vnOidPais
                  AND   OID_TERR = vnOidTerr;

                  SELECT TICL_OID_TIPO_CLIE,SBTI_OID_SUBT_CLIE
                  INTO vnOidTipoCliente,vnOidSubTipoCliente
                  FROM MAE_CLIEN_TIPO_SUBTI
                  WHERE CLIE_OID_CLIE = vnOidEjecutiva
                  AND IND_PPAL = 1;

                  SELECT PED_SOCA_SEQ.NEXTVAL
                  INTO vnOidSolicCabec
                  FROM dual;

                  INSERT INTO PED_SOLIC_CABEC(
                   OID_SOLI_CABE,
                   FEC_PROG_FACT,
                   TSPA_OID_TIPO_SOLI_PAIS,
                   TIDS_OID_TIPO_DESP,
                   ALMC_OID_ALMA,
                   MODU_OID_MODU,
                   TICL_OID_TIPO_CLIE,
                   PERD_OID_PERI,
                   CLIE_OID_CLIE,
                   CLIE_OID_CLIE_RECE_FACT,
                   CLIE_OID_CLIE_PAGA,
                   CLIE_OID_CLIE_DEST,
                   CLDI_OID_CLIE_DIRE,
                   TDOC_OID_TIPO_DOCU,
                   SOCI_OID_SOCI,
                   SBAC_OID_SBAC,
                   TERR_OID_TERR,
                   ZZON_OID_ZONA,
                   VAL_NUME_SOLI,
                   FEC_CRON,
                   IND_PERM_UNIO_SOL,
                   NUM_DOCU_ORIG,
                   IND_TS_NO_CONSO,
                   PAIS_OID_PAIS,
                   TIDO_OID_TIPO_DOCU,
                   VEPO_OID_VALO_ESTR_GEOP,
                   ESSO_OID_ESTA_SOLI,
                   COPA_OID_PARA_GENE,
                   GRPR_OID_GRUP_PROC,
                   SBTI_OID_SUBT_CLIE,
                   TSPA_OID_TIPO_SOLI_PAIS_CONS,
                   FOPA_OID_FORM_PAGO,
                   CLSO_OID_CLAS_SOLI,
                   ZTAD_OID_TERR_ADMI,
                   OPER_OID_OPER,
                   PROC_OID_PROC,
                   SOCA_OID_DOCU_REFE,
                   ICTP_OID_TIPO_PROG,
                   VAL_TIPO_CAMB,
                   IND_OC)
                  VALUES(
                   vnOidSolicCabec,
                   vnFecFinPerio,
                   vnOidTipoSoliPais,
                   1,
                   vnOidAlmacEnv,
                   1,
                   vnOidTipoCliente,
                   vnOidPeriodoSiguiente,
                   vnOidEjecutiva,
                   vnOidEjecutiva,
                   vnOidEjecutiva,
                   vnOidEjecutiva,
                   vnOidClieDire,
                   vnOidTipoDocu,
                   vnSocie,
                   vnSubac,
                   vnOidTerr,
                   vnOidZona,
                   vnNumSoliFormato,
                   TO_DATE((TO_CHAR(SYSDATE,'dd/MM/yyyy')),'dd/MM/yyyy'),
                   1,
                   NULL,
                   1,
                   vnOidPais,
                   30,
                   vnOidValorEstrGeop,
                   1,
                   NULL,
                   3,
                   vnOidSubTipoCliente,
                   vnTipoSoliCons,
                   vnFormaPagoEnv,
                   vnClaseSolicEnv,
                   vnOidTerrAdmi,
                   21,
                   1,
                   NULL,
                   NULL,
                   vnOidFormPago,
                   0);

                  INSERT INTO PED_SOLIC_POSIC(
                   OID_SOLI_POSI,
                   COD_POSI,
                   NUM_UNID_DEMA,
                   NUM_UNID_POR_ATEN,
                   VAL_TASA_IMPU,
                   SOCA_OID_SOLI_CABE,
                   TPOS_OID_TIPO_POSI,
                   PROD_OID_PROD,
                   VAL_PREC_CATA_UNIT_LOCA,
                   VAL_PREC_CONT_UNIT_LOCA,
                   VAL_PREC_CATA_UNIT_DOCU,
                   VAL_PREC_CONTA_UNIT_DOCU,
                   NUM_UNID_COMPR,
                   NUM_UNID_DEMA_REAL,
                   ESPO_OID_ESTA_POSI,
                   STPO_OID_SUBT_POSI,
                   VAL_CODI_VENT,
                   OFDE_OID_DETA_OFER)
                  VALUES(
                   PED_SOPO_SEQ.NEXTVAL,
                   1,
                   1,
                   1,
                   0,
                   vnOidSolicCabec,
                   9,
                   vnOidProducto,
                   0,
                   vnImpPrecPosi,
                   0,
                   vnImpPrecPosi,
                   1,
                   1,
                   4,
                   13,
                   vsCodigoVenta,
                   vnOidDetalleOferta);

                   UPDATE LET_RESUL_LIDER_CONCU
                   SET IND_PREM = 1
                   WHERE PAIS_COD_PAIS = psCodigoPais
                   AND CONC_COD_CONC = resultadoLideresConcurso(i).CONC_COD_CONC
                   AND COD_LIDE = resultadoLideresConcurso(i).COD_LIDE;

                   vnNumSoliFormato := vnNumSoliFormato + 1;

                 END IF;
              END LOOP;

           END IF;

       EXIT WHEN C_RESULTADO_LIDERES_CONCURSO%NOTFOUND;
       END LOOP;

       CLOSE C_RESULTADO_LIDERES_CONCURSO;
      END IF;

   EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_ENTRE_PREMI_CONCU_CIERR: ' || ls_sqlerrm);
   END LET_PR_ENTRE_PREMI_CONCU_CIERR;

   /**********************************************************************************
    Descripcion       : Obtenemos la campa�a de asignacion de la unidad administrativa
                        y respecto a su fecha desde
    Fecha Creacion    : 01/03/2011
    Autor             : Carlos Diaz Valverde
   **********************************************************************************/
   FUNCTION LET_FN_OBTIE_CAMPA_ASIGN(psCodigoPais VARCHAR2,
                                     psCodigoMarca VARCHAR2,
                                     psCodigoCanal VARCHAR2,
                                     psUnidadAdministrativa VARCHAR2
                                    )
   RETURN VARCHAR2 IS

     lnIdPais NUMBER;
     lnIdMarca NUMBER;
     lnIdCanal NUMBER;
     lsCodigoPeriodo SEG_PERIO_CORPO.COD_PERI%TYPE;

   BEGIN

     --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL
     lnIdPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
     lnIdMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
     lnIdCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
     /*
     --RECUPERAMOS LA FECHA DESDE DE LA UNIDAD ADMIISTRATIVA, DEL HISTORICO DE GERENTES
     SELECT    TRUNC(FEC_DESD)
       INTO    ldFechaDesde
     FROM      ZON_HISTO_GEREN
     WHERE     PAIS_OID_PAIS = lnIdPais
       AND     MARC_OID_MARC = lnIdMarca
       AND     CANA_OID_CANA = lnIdCanal
       AND     UA = psUnidadAdministrativa
       AND     FEC_HAST IS NULL;

     --OBTENEMOS EL CODIGO DE PERIODO QUE TENGA A LA FECHA DESDE DENTRO DE SU RANGO DE FECHAS
     SELECT    COD_PERI
       INTO    lsCodigoPeriodo
     FROM      (
                 SELECT    cor.COD_PERI,
                           cra.FEC_INIC
                 FROM      CRA_PERIO cra,
                           SEG_PERIO_CORPO cor
                 WHERE     cra.PAIS_OID_PAIS = lnIdPais
                   AND     cra.MARC_OID_MARC = lnIdMarca
                   AND     cra.CANA_OID_CANA = lnIdCanal
                   AND     cra.FEC_INIC <= ldFechaDesde
                   AND     cra.FEC_FINA >= ldFechaDesde
                   AND     cra.PERI_OID_PERI = cor.OID_PERI
                 ORDER BY  cra.FEC_INIC ASC
               )
     WHERE     ROWNUM = 1;
     */

     SELECT GEN_PKG_GENER.gen_fn_devuelve_des_perio(MAX(PERD_OID_PERI_DESD))
     INTO lsCodigoPeriodo
     FROM ZON_HISTO_GEREN
     WHERE PAIS_OID_PAIS = lnIdPais
     AND   MARC_OID_MARC = lnIdMarca
     AND   CANA_OID_CANA = lnIdCanal
     AND   UA = psUnidadAdministrativa
     AND   PERD_OID_PERI_HAST IS NULL
     AND   LENGTH(UA) = 9;

     RETURN lsCodigoPeriodo;

   EXCEPTION
     WHEN OTHERS THEN
       RETURN NULL;
   END LET_FN_OBTIE_CAMPA_ASIGN;

   /***************************************************************************
    Descripcion       : Obtiene el numero de Promedios finales por Zona
    Fecha Creacion    : 01/03/2011
    Autor             : Carlos Diaz Valverde
   ***************************************************************************/
   FUNCTION LET_FN_OBTIE_PROME_ZONA(pnOidZona NUMBER,
                                    pnOidPeriodo NUMBER
                                   )
   RETURN VARCHAR2 IS

     lnSumaActiFina1 NUMBER;
     lnPromedio NUMBER;
     lsResultado VARCHAR2(10);

   BEGIN

     --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL ULTIMO PERIODO
     SELECT    SUM(fue.num_acti_fina)
       INTO    lnSumaActiFina1
     FROM      int_fuent_ventas_real fue
     WHERE     fue.zzon_oid_zona = pnOidZona
       AND     fue.perd_oid_peri = pnOidPeriodo;

     --VERIFICAMOS SI ENCONTRO REGISTRO EN INF_FUENT_VENTAS_REAL
     IF(lnSumaActiFina1 IS NULL) THEN

       lsResultado := '0';

       SELECT    COUNT(1)
         INTO    lnPromedio
       FROM      mae_clien_unida_admin ua,
                 zon_terri_admin ta,
                 zon_terri t,
                 zon_secci s,
                 zon_zona z,
                 mae_clien c,
                 mae_clien_datos_adici da
       WHERE     ua.ind_acti = 1
         AND     ua.perd_oid_peri_fin IS NULL
         AND     ua.ztad_oid_terr_admi = ta.oid_terr_admi
         AND     ta.ind_borr = 0
         AND     ta.terr_oid_terr = t.oid_terr
         AND     ta.zscc_oid_secc = s.oid_secc
         AND     s.zzon_oid_zona = z.oid_zona
         AND     c.oid_clie = ua.clie_oid_clie
         AND     da.clie_oid_clie = ua.clie_oid_clie
         AND     da.ind_acti = 1
         AND     da.esta_oid_esta_clie IN (2,3,4,6,8)
         AND     z.oid_zona = pnOidZona;

     ELSE
       lsResultado := '1';
       lnPromedio := lnSumaActiFina1;
     END IF;

     lsResultado := lsResultado || '_' || TO_CHAR(lnPromedio);

     RETURN lsResultado;

   END LET_FN_OBTIE_PROME_ZONA;

   /***************************************************************************
    Descripcion       : Obtiene el numero de Promedios finales por Seccion
    Fecha Creacion    : 01/03/2011
    Autor             : Carlos Diaz Valverde
   ***************************************************************************/
   FUNCTION LET_FN_OBTIE_PROME_SECCI(pnOidZona NUMBER,
                                     pnOidSeccion NUMBER,
                                     pnOidUltimoPeriodo1 NUMBER
                                    )
   RETURN VARCHAR2 IS

     -- Variables
     lnSumaActiFina NUMBER;

     lnPromedio NUMBER;
     lsResultado VARCHAR2(10);

   BEGIN

     --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL ULTIMO PERIODO

     SELECT    SUM(fue.num_acti_fina)
       INTO    lnSumaActiFina
     FROM      int_fuent_ventas_real fue,
               zon_terri_admin adm
     WHERE     fue.zzon_oid_zona = pnOidZona
       AND     fue.terr_oid_terr = adm.terr_oid_terr
       AND     adm.zscc_oid_secc = pnOidSeccion
       AND     fue.perd_oid_peri = pnOidUltimoPeriodo1
       AND     (pnOidUltimoPeriodo1 >= adm.PERD_OID_PERI_INIC OR adm.PERD_OID_PERI_INIC IS NULL)
       AND     (pnOidUltimoPeriodo1 <= adm.PERD_OID_PERI_FINA OR adm.PERD_OID_PERI_FINA IS NULL);

     -- VERIFICAMOS NO SI ENCONTRO REGISTRO EN INF_FUENT_VENTAS_REAL
     IF(lnSumaActiFina IS NULL) THEN
         lsResultado := '0';

         SELECT    COUNT(1)
           INTO    lnPromedio
         FROM      mae_clien_unida_admin ua,
                   zon_terri_admin ta,
                   zon_terri t,
                   zon_secci s,
                   zon_zona z,
                   mae_clien c,
                   mae_clien_datos_adici da
         WHERE     ua.ind_acti = 1
           AND     ua.perd_oid_peri_fin IS NULL
           AND     ua.ztad_oid_terr_admi = ta.oid_terr_admi
           AND     ta.ind_borr = 0
           AND     ta.terr_oid_terr = t.oid_terr
           AND     ta.zscc_oid_secc = s.oid_secc
           AND     s.zzon_oid_zona = z.oid_zona
           AND     c.oid_clie = ua.clie_oid_clie
           AND     da.clie_oid_clie = ua.clie_oid_clie
           AND     da.ind_acti = 1
           AND     da.esta_oid_esta_clie IN (2,3,4,6,8)
           AND     z.oid_zona = pnOidZona
           AND     s.oid_secc = pnOidSeccion;
     ELSE
       lsResultado := '1';
       lnPromedio := lnSumaActiFina;
     END IF;

     -- Resultado
     lsResultado := lsResultado || '_' || TO_CHAR(lnPromedio);

     RETURN lsResultado;

   END LET_FN_OBTIE_PROME_SECCI;

   /**********************************************************************************
    Descripcion       : Realizamos las diferentes validaciones para ver si se puede
                        efectuar la asignacion de la lider a una respectiva seccion
    Fecha Creacion    : 24/02/2011
    Autor             : Carlos Diaz Valverde
    **********************************************************************************/
   FUNCTION LET_FN_VALID_ASIGN_LIDER_SECCI(psCodigoPais VARCHAR2,
                                           psCodigoMarca VARCHAR2,
                                           psCodigoCanal VARCHAR2,
                                           psCodigoCliente VARCHAR2,
                                           psIndicadorReingreso VARCHAR2,
                                           psIndicadorNoValidaUnicoLider VARCHAR2,
                                           lnNumeroActivasFinalesZona NUMBER,
                                           lnPromedioActFinalesSeccion NUMBER,
                                           psCodigoPeriodoActual VARCHAR2,
                                           psCodigoPeriodoValAsiLid VARCHAR2,
                                           pnOidSeccion NUMBER,
                                           psUnidadAdm VARCHAR2,
                                           pnOidPeriodoAnt NUMBER,
                                           psCodigoPeriodoAsigLiderSgte VARCHAR2,
                                           pscodsubgerencia varchar2,
                                           pscodregi varchar2,
                                           pscodzona varchar2,
                                           pscodsecc varchar2,
                                           psRealizarValidaciones varchar2
                                          )
   RETURN VARCHAR2 IS

     lnIdPais NUMBER;
     lnIdMarca NUMBER;
     lnIdCanal NUMBER;

     lnIdCliente MAE_CLIEN.OID_CLIE%TYPE;
     lnTotalTipos NUMBER;
     lsUnidadAdministrativa VARCHAR2(15);
     lnMinimoCampanas NUMBER;

     ldCodigoPeriodoHasta SEG_PERIO_CORPO.COD_PERI%TYPE;
     lnMinActivasFinalesZona NUMBER;
     lnMinActivasFinalesSeccion NUMBER;
     lnDiferenciaCampanas NUMBER;
     lsCodigoValidacion VARCHAR2(200);

     indicadorUnicoLiderSeccion LID_PARAM.IND_UNIC_LIDE_SECC%TYPE;

     psCodigoEstatus VARCHAR2(2);
     lnValidaCodigoEstatus NUMBER;
     lnOidCliente MAE_CLIEN.OID_CLIE%TYPE;
     vnIndProLid BAS_PAIS.IND_PROG_LIDE%TYPE;

     vnIndPerIni NUMBER(10);
     vnIndValSeccSinLid NUMBER(10);
     vsCodigoPeriodoValAsiLid VARCHAR2(6);
     vnIndValHisLid NUMBER(10);
     vnFlagValLid NUMBER(1) := 0;
     vsOidPeriodoValAsiLid ZON_HISTO_GEREN.PERD_OID_PERI_DESD%TYPE;
     vnIndValSeccLid NUMBER(10);
     vsUnidadAdm zon_histo_geren.ua%type;
     vnOidPeriodoAnt ZON_HISTO_GEREN.PERD_OID_PERI_DESD%TYPE;
     vnIndDesvAuto ZON_HISTO_GEREN.IND_DESV_AUTO%TYPE;
     vnIndRein     ZON_TIPO_DESVI.IND_REIN%TYPE;
     vsCodigoPeridoProceso SEG_PERIO_CORPO.COD_PERI%TYPE;
     vsCodigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;
     vnCantidadPedidos let_param_rango_premi.Can_Pedi%TYPE;
     vnFinalesExijidasSeccion NUMBER;
     vnPorcentajeActividadMeta LET_PARAM_CONCU_LIDER.POR_ACTI_META%TYPE;
     vnIndActiMiniSecc LET_PARAM_CONCU_LIDER.IND_ACTI_MINI_SECC%TYPE;
     vnOidPeriodoQuiebreAnio ZON_HISTO_GEREN.PERD_OID_PERI_DESD%TYPE;
     vnOidMaxPeriodoHasta ZON_HISTO_GEREN.PERD_OID_PERI_DESD%TYPE;
     vnDescrTipoDesvi ZON_TIPO_DESVI.DES_TIPO_DESV%TYPE;
     vnOidHistGeren zon_histo_geren.oid_hist_gere%type;

     vbRealizarValidaciones BOOLEAN:= TRUE;
   BEGIN
     IF psRealizarValidaciones IS NOT NULL AND psRealizarValidaciones = 'N' THEN
        vbRealizarValidaciones := FALSE;
     END IF;

     -- OBTENEMOS EL INDICADOR DE UNICO LIDER EN SECCION DE LID_PARAM
     indicadorUnicoLiderSeccion := 1;

     --OBTENEMOS EL ID PAIS, ID MARCA, ID CANAL
     lnIdPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
     lnIdMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
     lnIdCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
     lnIdCliente := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoCliente, TRUE);
     lsCodigoValidacion := '0__OK';


     --(1) Validamos si existe el cliente ---------------------------------------------------------
     IF(lnIdCliente = -1) THEN
       lsCodigoValidacion := '1__ER';
       RETURN lsCodigoValidacion;
     END IF;

     --(2) Validamos si existe el cliente tiene tipoCliente : Consultora --------------------------
     SELECT    COUNT(1)
       INTO    lnTotalTipos
     FROM      MAE_CLIEN_TIPO_SUBTI sub,
               MAE_TIPO_CLIEN tip
     WHERE     sub.CLIE_OID_CLIE = lnIdCliente
       AND     sub.TICL_OID_TIPO_CLIE = tip.OID_TIPO_CLIE
       AND     tip.COD_TIPO_CLIE = '02';

     IF(lnTotalTipos = 0) THEN
       lsCodigoValidacion := '2__ER';
       RETURN lsCodigoValidacion;
     END IF;

     -- (3B) Validamos si la consultora ya es lider de alguna seccion. ----------------------------
     --      y si lo es le permite varios lideres en seccion
     IF(indicadorUnicoLiderSeccion = 0 AND psIndicadorNoValidaUnicoLider='N') THEN

       BEGIN
         SELECT    uni_adm
           INTO    lsUnidadAdministrativa
         FROM      (
                     SELECT    (
                                 sub.COD_SUBG_VENT ||
                                 reg.COD_REGI ||
                                 zon.COD_ZONA ||
                                 sec.COD_SECC
                               ) uni_adm
                     FROM      ZON_SECCI sec,
                               ZON_ZONA zon,
                               ZON_REGIO reg,
                               ZON_SUB_GEREN_VENTA sub
                     WHERE     sec.ZZON_OID_ZONA = zon.oid_zona
                       AND     zon.ZORG_OID_REGI = reg.oid_regi
                       AND     reg.zsgv_oid_subg_vent = sub.oid_subg_vent
                       AND     zon.pais_oid_pais = lnIdPais
                       AND     zon.marc_oid_marc = lnIdMarca
                       AND     zon.cana_oid_cana = lnIdCanal
                       AND     sec.ind_acti = '1'
                       AND     sec.clie_oid_clie = lnIdCliente
                   )
         WHERE     ROWNUM = 1;
       EXCEPTION
         WHEN OTHERS THEN
           lsUnidadAdministrativa := NULL;
       END;

       IF(lsUnidadAdministrativa IS NOT NULL) THEN
         lsCodigoValidacion := '3B__' || lsUnidadAdministrativa;
         RETURN lsCodigoValidacion;
       END IF;

     END IF;

   --(3) Validamos si la consultora ya es lider de alguna seccion. ------------------------------

     IF vnFlagValLid = 0 THEN
       vsCodigoPeriodoValAsiLid := psCodigoPeriodoValAsiLid;
     END IF;

     -- Validar si el indicador de programa LET sea igual a 2
     SELECT    NVL(IND_PROG_LIDE, 0)
       INTO    vnIndProLid
     FROM      BAS_PAIS
     WHERE     COD_PAIS = psCodigoPais;

     --(4) Validamos si la Consultora no cumple minimo de campa�as para reingreso -----------------
     IF(psIndicadorReingreso = 'N') THEN

       -- capturar valor minimo de campa�a
       BEGIN
         SELECT    VAL_NUME_MINI_CAMP
           INTO    lnMinimoCampanas
         FROM      ZON_PARAM_REING_CONSU
         WHERE     PAIS_OID_PAIS = lnIdPais
           AND     TIPO_DE_UA = 'SECCION';
       EXCEPTION
         WHEN OTHERS THEN
           lnMinimoCampanas := NULL;
       END;

       IF(lnMinimoCampanas IS NOT NULL) THEN

         BEGIN
           /* INI JJ PER-SiCC-2012-0201*/
           --Obtenemos el mayor periodo del historico de responsable para la consultora
           SELECT GEN_PKG_GENER.gen_fn_devuelve_des_perio(MAX(PERD_OID_PERI_HAST))
           INTO ldCodigoPeriodoHasta
           FROM ZON_HISTO_GEREN
           WHERE  PAIS_OID_PAIS = lnIdPais
           AND  MARC_OID_MARC = lnIdMarca
           AND  CANA_OID_CANA = lnIdCanal
           AND  GERE = psCodigoCliente
           AND  LENGTH(UA) = 9;

         EXCEPTION
           WHEN OTHERS THEN
             ldCodigoPeriodoHasta := NULL;
         END;

         IF(ldCodigoPeriodoHasta IS NOT NULL) THEN

           SELECT NVL(IND_DESV_AUTO,0)
           INTO vnIndDesvAuto
           FROM ZON_HISTO_GEREN
           WHERE  PAIS_OID_PAIS = lnIdPais
           AND  MARC_OID_MARC = lnIdMarca
           AND  CANA_OID_CANA = lnIdCanal
           AND  GERE = psCodigoCliente
           AND  LENGTH(UA) = 9
           AND  PERD_OID_PERI_HAST = (SELECT MAX(PERD_OID_PERI_HAST)
                                      FROM ZON_HISTO_GEREN
                                      WHERE  PAIS_OID_PAIS = lnIdPais
                                      AND  MARC_OID_MARC = lnIdMarca
                                      AND  CANA_OID_CANA = lnIdCanal
                                      AND  GERE = psCodigoCliente
                                      AND  LENGTH(UA) = 9);

          /* FIN JJ PER-SiCC-2012-0201*/

           lnDiferenciaCampanas := ( (VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(ldCodigoPeriodoHasta,vsCodigoPeriodoValAsiLid,lnIdPais,lnIdMarca,lnIdCanal) - 1) ) - 1;

           IF vnIndProLid = 1 OR vnIndProLid = 0 OR vnIndProLid IS NULL THEN

             IF(lnDiferenciaCampanas < lnMinimoCampanas) THEN
               lsCodigoValidacion := '4__' || TO_CHAR(lnMinimoCampanas)||';'||'0';
               IF vbRealizarValidaciones THEN
               RETURN lsCodigoValidacion;
             END IF;
             END IF;

           END IF;

           IF vnIndProLid = 2 THEN

             IF(lnDiferenciaCampanas < lnMinimoCampanas) THEN

               select IND_REIN
               into vnIndRein
               from ZON_TIPO_DESVI --se cambio el nombre 12022013
               where cod_tipo_desv = vnIndDesvAuto
               and EST_REGI = 1;

               IF vnIndRein <> 1 THEN
                 lsCodigoValidacion := '4__' || TO_CHAR(lnMinimoCampanas)||';'||'1';
                 IF vbRealizarValidaciones THEN
                 RETURN lsCodigoValidacion;
               END IF;

             END IF;

           END IF;

           END IF;

         END IF; -- IF(ldCodigoPeriodoHasta IS NOT NULL) THEN

       END IF; -- IF(lnMinimoCampanas IS NOT NULL) THEN

     END IF; -- IF(psIndicadorReingreso = 'N') THEN


     -- RECUPERAMOS LOS MININOS ACTIVAS FINALES DE ZONA (5) Y SECCION (6) -------------------------

     BEGIN

       IF vnIndProLid = 2 THEN
         SELECT    MIN_ACTI_FINA_ZONA,
                   MIN_ACTI_FINA_SECC
           INTO    lnMinActivasFinalesZona,
                   lnMinActivasFinalesSeccion
         FROM      LET_PARAM_CONCU_LIDER
         WHERE     psCodigoPeriodoActual >= CAM_INIC
           AND     psCodigoPeriodoActual <= CAM_FINA
           AND     EST_REGI = '1';
       END IF;

       IF vnIndProLid = 1  THEN
          SELECT    MIN_ACTI_FINA_ZONA,
                     MIN_ACTI_FINA_SECC
             INTO    lnMinActivasFinalesZona,
                     lnMinActivasFinalesSeccion
           FROM      LID_PARAM
           WHERE     COD_PAIS = psCodigoPais;
       END IF;

       IF vnIndProLid = 0 THEN
           lnMinActivasFinalesZona := 0;
           vnFinalesExijidasSeccion := 0;
       END IF;
     EXCEPTION
       WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20123, 'NO SE ENCONTRO DATOS DE MINIMO DE ACTIVAS FINALES DE ZONA Y SECCION PARA EL PAIS');
     END;

     --(5) Validamos si la seccion cumple con el minimo de activas finales por Zona
     IF(lnNumeroActivasFinalesZona < lnMinActivasFinalesZona) THEN

       lsCodigoValidacion := '5__ER';
       IF vbRealizarValidaciones THEN
       RETURN lsCodigoValidacion;
     END IF;
     END IF;

     IF vnIndProLid = 2 THEN
         BEGIN
     SELECT IND_ACTI_MINI_SECC
       INTO vnIndActiMiniSecc
       FROM LET_PARAM_CONCU_LIDER
      WHERE psCodigoPeriodoActual >= CAM_INIC
        AND psCodigoPeriodoActual <= CAM_FINA;
         EXCEPTION
         WHEN NO_DATA_FOUND THEN
             vnIndActiMiniSecc := 0;
         END;
     END IF;

     --(6) Validamos si la seccion cumple con el minimo de activas finales por Seccion
     IF vnIndProLid = 2 and vnIndActiMiniSecc = 1 and psCodigoCliente IS NOT NULL THEN

        select COD_PERI
        into vsCodigoPeridoProceso
        from bas_ctrl_fact
        where sta_camp = 0
        and ind_camp_act = 1;

        SELECT COD_CONC,POR_ACTI_META
        INTO vsCodigoConcurso,vnPorcentajeActividadMeta
        FROM LET_PARAM_CONCU_LIDER
        WHERE vsCodigoPeridoProceso >= CAM_INIC
        AND vsCodigoPeridoProceso <= CAM_FINA
        AND EST_REGI = '1';

        select CAN_PEDI
        into vnCantidadPedidos
        from let_param_rango_premi
        where pais_cod_pais = psCodigoPais
        and conc_cod_conc = vsCodigoConcurso
        and rang_num_rang = 1;

        vnFinalesExijidasSeccion := FLOOR(vnCantidadPedidos / (vnPorcentajeActividadMeta / 100));

       IF lnPromedioActFinalesSeccion < vnFinalesExijidasSeccion THEN
         lsCodigoValidacion := '6__'||vnFinalesExijidasSeccion;
         IF vbRealizarValidaciones THEN
         RETURN lsCodigoValidacion;
       END IF;

       END IF;
     END IF;

     --(9.4) Decu v10.4
     IF vnIndProLid = 3 THEN
         BEGIN
             SELECT NUM_MINI_ACTI_SECC
               INTO vnFinalesExijidasSeccion
               FROM LET_CORPO_PARAM_PROGR
              WHERE psCodigoPeriodoActual >= CAM_INIC
                AND (psCodigoPeriodoActual <= CAM_FIN OR CAM_FIN IS NULL);
         EXCEPTION
         WHEN NO_DATA_FOUND THEN
             vnFinalesExijidasSeccion := 0;
         END;
     END IF;

     --(34) Decu v10.4
     IF vnIndProLid = 3 THEN
        IF lnPromedioActFinalesSeccion < vnFinalesExijidasSeccion THEN
         lsCodigoValidacion := '6B__'||vnFinalesExijidasSeccion;
         IF vbRealizarValidaciones THEN
         RETURN lsCodigoValidacion;
       END IF;

       END IF;
     END IF;

     --(7) Validamos si el status de la consultora se encuentra registrada en  --------------------

     IF vnIndProLid = 2 OR vnIndProLid = 3 THEN

       lnOidCliente :=  Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoCliente);

       SELECT    COD_ESTA_CLIE
         INTO    psCodigoEstatus
       FROM      MAE_CLIEN_DATOS_ADICI A,
                 MAE_ESTAT_CLIEN B
       WHERE     A.ESTA_OID_ESTA_CLIE = B.OID_ESTA_CLIE
         AND     CLIE_OID_CLIE = lnOidCliente;

       SELECT    COUNT(1)
         INTO    lnValidaCodigoEstatus
       FROM      LET_ESTAT_LIDER
       WHERE     COD_ESTA = psCodigoEstatus;

       IF lnValidaCodigoEstatus = 0 THEN
         lsCodigoValidacion := '7__ER';
         IF vbRealizarValidaciones THEN
         RETURN lsCodigoValidacion;
       END IF;

     END IF;

     END IF;

     --(0) Si paso toda validaci�n ----------------------------------------------------------------
     lsCodigoValidacion := '0__' || TO_CHAR(lnIdCliente);

   -- b) Validar lider si existe en otra seccion
     vsOidPeriodoValAsiLid := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodigoPeriodoValAsiLid);

     select    count(1)
       into    vnIndValSeccLid
     from      zon_histo_geren zh
     where     zh.gere = psCodigoCliente
       and     vsOidPeriodoValAsiLid >= zh.perd_oid_peri_desd
       and     (
                 vsOidPeriodoValAsiLid <= zh.perd_oid_peri_hast or
                 zh.perd_oid_peri_hast is null
               );


     IF vnIndValSeccLid > 0 THEN

       select    zh.ua
         into    vsUnidadAdm
       from      zon_histo_geren zh
       where     zh.gere = psCodigoCliente
         and     vsOidPeriodoValAsiLid >= zh.perd_oid_peri_desd
         and     (
                   vsOidPeriodoValAsiLid <= zh.perd_oid_peri_hast or
                   zh.perd_oid_peri_hast is null
                 );

       lsCodigoValidacion := '3__' || 'n;' || vsUnidadAdm || ';'|| vsCodigoPeriodoValAsiLid;
       RETURN lsCodigoValidacion;

     -- para recuperar la campa�a proceso final
     ELSE

       IF vsCodigoPeriodoValAsiLid = psCodigoPeriodoActual THEN

        vsOidPeriodoValAsiLid := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodoAsigLiderSgte);

        select count(1)
        into  vnIndValSeccLid
        from   zon_histo_geren zh
        where  zh.gere = psCodigoCliente
        and  vsOidPeriodoValAsiLid >= zh.perd_oid_peri_desd
        and  (vsOidPeriodoValAsiLid <= zh.perd_oid_peri_hast or zh.perd_oid_peri_hast is null);

        IF vnIndValSeccLid > 0 THEN
           select zh.oid_hist_gere
           into  vnOidHistGeren
         from      zon_histo_geren zh
         where     zh.gere = psCodigoCliente
           and     vsOidPeriodoValAsiLid >= zh.perd_oid_peri_desd
           and (vsOidPeriodoValAsiLid <= zh.perd_oid_peri_hast or zh.perd_oid_peri_hast is null);

         lsCodigoValidacion := '3__' || 'n2;' || vsUnidadAdm || ';'|| vnOidHistGeren;
         RETURN lsCodigoValidacion;

         END IF;

        select count(1)
            into  vnIndValSeccLid
           from zon_histo_geren zh
           where zh.ua = pscodsubgerencia || pscodregi || pscodzona || pscodsecc
        and  zh.perd_oid_peri_desd >= vsOidPeriodoValAsiLid;

        IF vnIndValSeccLid > 0 THEN
          select zh.oid_hist_gere
            into  vnOidHistGeren
           from zon_histo_geren zh
           where zh.ua = pscodsubgerencia || pscodregi || pscodzona || pscodsecc
           and  zh.perd_oid_peri_desd >= vsOidPeriodoValAsiLid;

         lsCodigoValidacion := '3__' || 'n2;' || vsUnidadAdm || ';'|| vnOidHistGeren;
           RETURN lsCodigoValidacion;

         END IF;

       END IF;

     END IF;

     -- Retornar valor ----------------------------------------------------------------------------
     RETURN lsCodigoValidacion;

   EXCEPTION

     -- (9) En caso de excepcion. Tambi�n es una validacion ---------------------------------------
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM,1,150);
       RETURN '9__' || ls_sqlerrm;

   END LET_FN_VALID_ASIGN_LIDER_SECCI;

   /***********************************************************************************************
    Descripcion       : Realiza la desvinculacion automatica de lideres
    Fecha Creacion    : 23/02/2011
    Autor             : Jesse Rios
    Facha Actualiza   : 26/05/2011
    Autor Actualiza   : Carlos Diaz Valverde
    ***********************************************************************************************/
   PROCEDURE LET_PR_DESVI_AUTOM_LIDER(psCodigoPais VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoRegion VARCHAR2,
                                      psCodigoUsuario VARCHAR2)IS

     vsCodigoConcurso      LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;
     vnNumCampSinPedi      LET_PARAM_CONCU_LIDER.NUM_CAMP_SIN_PEDI%TYPE;
     vnNumPeriCritDesv     LET_PARAM_CONCU_LIDER.NUM_PERI_CRIT_DESV%TYPE;
     vnNumPeriCritDesvTemp LET_PARAM_CONCU_LIDER.NUM_PERI_CRIT_DESV%TYPE;
      /* INI JJ PER-SiCC-2012-0201 */
     CURSOR C_LIDERES(oidPais SEG_PAIS.OID_PAIS%TYPE,oidPeriodo SEG_PERIO_CORPO.OID_PERI%TYPE) IS
     select zs.OID_SECC,
     (SELECT X.COD_CLIE
     FROM MAE_CLIEN X
              WHERE X.OID_CLIE = zs.CLIE_OID_CLIE ) COD_CLIE,
             zs.CLIE_OID_CLIE,
             sub.COD_SUBG_VENT || zr.COD_REGI || zz.COD_ZONA || zs.COD_SECC UA
      from ZON_SECCI zs,
          ZON_ZONA zz,
          ZON_REGIO zr,
          ZON_SUB_GEREN_VENTA sub,
          ZON_HISTO_GEREN zhg
      where zr.oid_regi = zz.zorg_oid_regi
      and zz.oid_zona = zs.zzon_oid_zona
      and zr.ZSGV_OID_SUBG_VENT = sub.OID_SUBG_VENT
      and zs.IND_ACTI = 1
      and zs.IND_BORR = 0
      and zs.CLIE_OID_CLIE IS NOT NULL
      and zr.oid_regi IN(SELECT DISTINCT(Zorg_Oid_Regi)
                         from FAC_CONTR_CIERR fcc
                         WHERE fcc.PAIS_OID_PAIS = oidPais
                         AND fcc.PERD_OID_PERI = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo)
                         AND fcc.TCIE_OID_TIPO_CIER = 1)
      and zr.cod_regi = DECODE(psCodigoRegion,'-1',zr.cod_regi,psCodigoRegion)
      and zhg.GERE = (SELECT X.COD_CLIE FROM MAE_CLIEN X WHERE X.OID_CLIE = zs.CLIE_OID_CLIE )
      and LENGTH(zhg.UA) = 9
      and oidPeriodo >= zhg.PERD_OID_PERI_DESD
      and (oidPeriodo <= zhg.PERD_OID_PERI_HAST OR zhg.PERD_OID_PERI_HAST IS NULL)
      and (zhg.IND_DESV_AUTO = 0 OR zhg.IND_DESV_AUTO IS NULL);

     /* FIN JJ PER-SiCC-2012-0201 */
     TYPE interfazLider IS RECORD(
       oidSeccion ZON_SECCI.OID_SECC%TYPE,
       codCliente MAE_CLIEN.COD_CLIE%TYPE,
       oidCliente MAE_CLIEN.OID_CLIE%TYPE,
       ua VARCHAR2(9)
     );

     TYPE interfazLiderTab IS TABLE OF interfazLider;
     interfazRecordN interfazLiderTab;

     CURSOR C_CONCURSOS(numPeriodoCriticos LET_PARAM_CONCU_LIDER.NUM_PERI_CRIT_DESV%TYPE) IS
     SELECT COD_CONC
     FROM (SELECT CAM_INIC,COD_CONC
           FROM LET_PARAM_CONCU_LIDER
           WHERE CAM_INIC < psCodigoPeriodo
           AND EST_REGI = 1
           ORDER BY CAM_INIC DESC)
     WHERE ROWNUM <= numPeriodoCriticos
     ORDER BY ROWNUM;

     TYPE concursosTab IS TABLE OF LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;
     concursos concursosTab;

     vsCodigoPeriodoInicio SEG_PERIO_CORPO.COD_PERI%TYPE;

    /* INI JJ PER-SiCC-2012-0201 */
    CURSOR C_SECCIONES_DESVINCULAR(oidPais SEG_PAIS.OID_PAIS%TYPE,oidPeriodo SEG_PERIO_CORPO.OID_PERI%TYPE) IS
    SELECT sec.OID_SECC,tab.GERE
    FROM(SELECT SUBSTR(UA,3,2) AS COD_REGI,
               SUBSTR(UA,5,4) AS COD_ZONA,
               SUBSTR(UA,9,1) AS COD_SECC,
               GERE
         FROM ZON_HISTO_GEREN
         WHERE PAIS_OID_PAIS = oidPais
         AND LENGTH(UA) = 9
         AND PERD_OID_PERI_HAST = oidPeriodo
         AND IND_DESV_AUTO IN (1,2,4,5)
        )tab,
        ZON_REGIO reg,
        ZON_ZONA zon,
        ZON_SECCI sec
     WHERE reg.oid_regi = zon.zorg_oid_regi
     AND   zon.oid_zona = sec.zzon_oid_zona
     AND   tab.COD_REGI = reg.cod_regi
     AND   tab.COD_ZONA = zon.cod_zona
     AND   tab.COD_SECC = sec.cod_secc;

     TYPE seccionesDesvincularRecord IS RECORD(
         OID_SECC ZON_SECCI.OID_SECC%TYPE,
         GERE ZON_HISTO_GEREN.GERE%TYPE
     );

     TYPE seccionesDesvincularTab IS TABLE OF seccionesDesvincularRecord;
     seccionesDesvincular seccionesDesvincularTab;

     /* FIN JJ PER-SiCC-2012-0201 */

     vnOidPais       SEG_PAIS.OID_PAIS%TYPE;
     vnOidMarca      SEG_MARCA.OID_MARC%TYPE;
     vnOidCanal      SEG_CANAL.OID_CANA%TYPE;
     vnOidPeriodo    SEG_PERIO_CORPO.OID_PERI%TYPE;

     vnMarcaDesvinviculacion NUMBER := 0;
     vsPeriodoIngreso SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnNumCampanha NUMBER;
     vnTotalPedidos NUMBER;
     vnContNumeroPeriodoCriticos NUMBER;
     vsPeriodoFinConcu LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE;
     vnContNumeroPeriodoCritTotal NUMBER;
     vsFechaProceso BAS_CTRL_FACT.FEC_PROC%TYPE;

     vnMinActiFinaSecc LET_PARAM_CONCU_LIDER.MIN_ACTI_FINA_SECC%TYPE;
     vnEncontroLiderActFinal NUMBER;
     /* INI JJ PER-SiCC-2012-0201 */
     vnNumCampSinActiMini  LET_PARAM_CONCU_LIDER.NUM_CAMP_SIN_ACTI_MINI%TYPE;
     vnNumCampSinNiveNuev  LET_PARAM_CONCU_LIDER.NUM_CAMP_SIN_NIVE_NUEV%TYPE;
     vnNumCampSinNieveEsta LET_PARAM_CONCU_LIDER.NUM_CAMP_SIN_NIVE_ESTA%TYPE;
     e_clasificacionLider  EXCEPTION;
     vsCodLider            LET_RESUL_SECCI_CAMPA.LIDE_COD_LIDE%TYPE;
     vnEncontroClasificacionLider NUMBER;
     vsCodigoClasificacion    LET_HISTO_CLASI_LIDER.COD_CLAS_LIDE%TYPE;
     vsCodigoSubClasificacion LET_HISTO_CLASI_LIDER.COD_SUB_CLAS_LIDE%TYPE;
     vnCantidadCampNivel0 NUMBER;
     /* FIN JJ PER-SiCC-2012-0201 */
   BEGIN

      --Recuperamos el oid Pais,Marca,Canal,Periodo
      vnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
      vnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
      vnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
      vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

      -- Recuperar Fecha Proceso Facturacion
      select    fec_proc
        into    vsFechaProceso
      from      BAS_CTRL_FACT
      where     cod_pais = psCodigoPais
        and     cod_peri = psCodigoPeriodo;
      /* INI JJ PER-SiCC-2012-0201 */
      BEGIN
        SELECT COD_CONC,
               NUM_CAMP_SIN_PEDI,
               NUM_PERI_CRIT_DESV,
               CAM_FINA,
               MIN_ACTI_FINA_SECC,
               NUM_CAMP_SIN_ACTI_MINI,
               NUM_CAMP_SIN_NIVE_NUEV,
               NUM_CAMP_SIN_NIVE_ESTA
        INTO vsCodigoConcurso,
             vnNumCampSinPedi,
             vnNumPeriCritDesv,
             vsPeriodoFinConcu,
             vnMinActiFinaSecc,
             vnNumCampSinActiMini,
             vnNumCampSinNiveNuev,
             vnNumCampSinNieveEsta
        FROM LET_PARAM_CONCU_LIDER
        WHERE PAIS_COD_PAIS = psCodigoPais
        AND psCodigoPeriodo >=  CAM_INIC
        AND psCodigoPeriodo <= CAM_FINA;
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
       vsCodigoConcurso := NULL;
      END;

      IF vsCodigoConcurso IS NOT NULL THEN

         IF( ((vnNumCampSinPedi IS NULL) OR (vnNumCampSinPedi=0))
             AND ((vnNumPeriCritDesv IS NULL) OR (vnNumPeriCritDesv=0))
             AND ((vnMinActiFinaSecc IS NULL) OR (vnMinActiFinaSecc=0))
             AND ((vnNumCampSinActiMini IS NULL) OR (vnNumCampSinActiMini=0))
             AND ((vnNumCampSinNiveNuev IS NULL) OR (vnNumCampSinNiveNuev=0))
             AND ((vnNumCampSinNieveEsta IS NULL) OR (vnNumCampSinNieveEsta=0))) THEN
      /* FIN JJ PER-SiCC-2012-0201 */
             RETURN;
         ELSE
            IF( vnNumCampSinPedi IS NOT NULL) THEN
                IF(vnNumCampSinPedi = 1) THEN
                   vsCodigoPeriodoInicio := psCodigoPeriodo;
                ELSE
                   vsCodigoPeriodoInicio := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais, vnOidMarca, vnOidCanal, (-1)*(vnNumCampSinPedi-1));
                END IF;
            END IF;
         END IF;

      END IF;

      vnNumPeriCritDesvTemp:= vnNumPeriCritDesv;

      OPEN C_LIDERES(vnOidPais,vnOidPeriodo);
        LOOP
             FETCH C_LIDERES BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
             IF interfazRecordN.COUNT > 0 THEN
                FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

                     /* INI JJ PER-SiCC-2012-0201 */
                     SELECT COUNT(1)
                     INTO vnEncontroClasificacionLider
                     FROM LET_HISTO_CLASI_LIDER
                     WHERE PAIS_COD_PAIS = psCodigoPais
                     AND CAM_CLAS = psCodigoPeriodo
                     AND COD_LIDE = interfazRecordN(x).codCliente;

                     IF vnEncontroClasificacionLider = 0 THEN
                       vsCodLider := interfazRecordN(x).codCliente;
                       RAISE e_clasificacionLider;
                     END IF;

                     SELECT COD_CLAS_LIDE,COD_SUB_CLAS_LIDE
                     INTO vsCodigoClasificacion,vsCodigoSubClasificacion
                     FROM LET_HISTO_CLASI_LIDER
                     WHERE PAIS_COD_PAIS = psCodigoPais
                     AND CAM_CLAS = psCodigoPeriodo
                     AND COD_LIDE = interfazRecordN(x).codCliente;
                    /* FIN JJ PER-SiCC-2012-0201 */

                    vnMarcaDesvinviculacion := 0;
                    vsPeriodoIngreso := LET_FN_DEVUE_CAMPA_INGRE_LIDER(interfazRecordN(x).codCliente);
                    vnNumCampanha := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(vsPeriodoIngreso,psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal);
                    -- Procesos de Desvinculacion por Numero de pedidos
                    IF(vnNumCampSinPedi IS NOT NULL AND vnNumCampSinPedi <> 0 AND vnNumCampanha >= vnNumCampSinPedi) THEN
                       --Para cada Lider se recupera el numero de Pedidos
                       SELECT COUNT(1)
                       INTO vnTotalPedidos
                       FROM PED_SOLIC_CABEC psc,
                       PED_TIPO_SOLIC_PAIS tsp,
                       (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC') ts,
                       CRA_PERIO cra,
                       SEG_PERIO_CORPO cor
                       WHERE psc.PAIS_OID_PAIS = vnOidPais
                       AND psc.PERD_OID_PERI = cra.oid_peri
                       AND cra.peri_oid_peri = cor.oid_peri
                       AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
                       AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
                       AND psc.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                       AND psc.GRPR_OID_GRUP_PROC = 5
                       AND psc.FEC_FACT IS NOT NULL
                       AND cor.cod_peri >= vsCodigoPeriodoInicio
                       AND cor.cod_peri <= psCodigoPeriodo;

                       IF vnTotalPedidos = 0 THEN --Desvinculamos a la Lider
                          --se procede a la desvinculacion
                          LET_PR_PROCE_DESVI_LIDER(psCodigoPais,
                                                   interfazRecordN(x).oidSeccion,
                                                   interfazRecordN(x).ua,
                                                   interfazRecordN(x).oidCliente,
                                                   vnOidPeriodo,
                                                   vsFechaProceso,
                                                   1,
                                                   psCodigoUsuario);

                          vnMarcaDesvinviculacion := 1;

                       END IF;
                    END IF;

                    /* INI JJ PER-SiCC-2012-0201 */
                    SELECT COUNT(1)
                    INTO vnCantidadCampNivel0
                    FROM LET_RESUL_SECCI_CAMPA
                    WHERE PAIS_COD_PAIS = psCodigoPais
                    AND CONC_COD_CONC = vsCodigoConcurso
                    AND LIDE_COD_LIDE = interfazRecordN(x).codCliente
                    AND NIV_CAMP = 0;

                    -- SE EJECUTA LA SGTE ACTIVIDAD
                    IF vnMarcaDesvinviculacion = 0 THEN
                    IF vsCodigoClasificacion = '01' THEN
                      IF vnCantidadCampNivel0 >= vnNumCampSinNiveNuev THEN
                          --se procede a la desvinculacion
                          LET_PR_PROCE_DESVI_LIDER(psCodigoPais,
                                                   interfazRecordN(x).oidSeccion,
                                                   interfazRecordN(x).ua,
                                                   interfazRecordN(x).oidCliente,
                                                   vnOidPeriodo,
                                                   vsFechaProceso,
                                                   5,
                                                   psCodigoUsuario);

                          vnMarcaDesvinviculacion := 1;
                      END IF;
                    ELSE
                      IF vnCantidadCampNivel0 >= vnNumCampSinNieveEsta THEN
                          --se procede a la desvinculacion
                          LET_PR_PROCE_DESVI_LIDER(psCodigoPais,
                                                   interfazRecordN(x).oidSeccion,
                                                   interfazRecordN(x).ua,
                                                   interfazRecordN(x).oidCliente,
                                                   vnOidPeriodo,
                                                   vsFechaProceso,
                                                   5,
                                                   psCodigoUsuario);

                          vnMarcaDesvinviculacion := 1;
                      END IF;
                    END IF;
                    END IF;

                    -- SE EJECUTA LA SGTE ACTIVIDAD
                    -- Procesos de Desvinculacion por Periodos Criticos
                    IF (vnMarcaDesvinviculacion = 0 AND psCodigoPeriodo = vsPeriodoFinConcu AND vnNumPeriCritDesv > 0 ) THEN --INGRESA SI NO HA SIDO MARCDA YA DESVINCULADA
                    /* FIN JJ PER-SiCC-2012-0201 */
                       /*Si la campa�a de proceso es igual a alguna de las campa�as de Fin de Per�odo */

                       vnContNumeroPeriodoCritTotal := 0;

                        OPEN C_CONCURSOS(vnNumPeriCritDesv);

                          LOOP
                            FETCH C_CONCURSOS BULK COLLECT INTO concursos LIMIT W_FILAS;

                            IF concursos.COUNT > 0 THEN
                              FOR j IN concursos.FIRST .. concursos.LAST LOOP

                                SELECT COUNT(1)
                                INTO vnContNumeroPeriodoCriticos
                                FROM LET_PRODU_LIDER Z
                                WHERE Z.COD_LID = interfazRecordN(x).codCliente
                                AND Z.VAL_ESTA = 'C'
                                AND Z.CONC_COD_CONC = concursos(j);

                                vnContNumeroPeriodoCritTotal := vnContNumeroPeriodoCritTotal + vnContNumeroPeriodoCriticos;

                              END LOOP;
                            END IF;
                          EXIT WHEN C_CONCURSOS%NOTFOUND;
                          END LOOP;
                        CLOSE C_CONCURSOS;

                        IF(vnContNumeroPeriodoCritTotal >= vnNumPeriCritDesvTemp) THEN

                         --se procede a la desvinculacion
                         LET_PR_PROCE_DESVI_LIDER(psCodigoPais,
                                                   interfazRecordN(x).oidSeccion,
                                                   interfazRecordN(x).ua,
                                                   interfazRecordN(x).oidCliente,
                                                   vnOidPeriodo,
                                                   vsFechaProceso,
                                                   /* INI JJ PER-SiCC-2012-0201 */
                                                   2,
                                                   /* FIN JJ PER-SiCC-2012-0201 */
                                                   psCodigoUsuario);

                         vnMarcaDesvinviculacion := 1;

                       END IF;
                    END IF;

                    -- SE EJECUTA LA SGTE ACTIVIDAD
                    -- Proceso de Desvinculacion por Activas Finales
                    /* INI JJ PER-SiCC-2012-0201 */
                    IF vnMarcaDesvinviculacion = 0 AND psCodigoPeriodo = vsPeriodoFinConcu AND vnNumCampSinActiMini > 0 THEN

                       SELECT COUNT(1)
                       INTO vnEncontroLiderActFinal
                       FROM LET_RESUL_SECCI_CAMPA
                       WHERE PAIS_COD_PAIS = psCodigoPais
                       AND LIDE_COD_LIDE = interfazRecordN(x).codCliente
                       AND LIDE_CAM_LIDE <= psCodigoPeriodo
                       AND CONC_COD_CONC = vsCodigoConcurso
                       AND ACT_FINA < NUM_FINA_EXIG;

                       IF vnEncontroLiderActFinal >= vnNumCampSinActiMini THEN
                            --se procede a la desvinculacion
                             LET_PR_PROCE_DESVI_LIDER(psCodigoPais,
                                                       interfazRecordN(x).oidSeccion,
                                                       interfazRecordN(x).ua,
                                                       interfazRecordN(x).oidCliente,
                                                       vnOidPeriodo,
                                                       vsFechaProceso,
                                                       4,
                                                       psCodigoUsuario);

                             vnMarcaDesvinviculacion := 1;
                         END IF;
                       END IF;
                    /* FIN JJ PER-SiCC-2012-0201 */
                END LOOP;
             END IF;
        EXIT WHEN C_LIDERES%NOTFOUND;
        END LOOP;
      CLOSE C_LIDERES;

      /* INI JJ PER-SiCC-2012-0201 */
      -- Resien al cierre de campa�a se procede a desvincular a las lideres de la ZON_SECCI
      -- que fueron desvinculadas previamente de la ZON_HISTO_GEREN
      IF psCodigoRegion = '-1' THEN
        OPEN C_SECCIONES_DESVINCULAR(vnOidPais,vnOidPeriodo);
          LOOP
             FETCH C_SECCIONES_DESVINCULAR BULK COLLECT INTO seccionesDesvincular LIMIT W_FILAS;

             IF seccionesDesvincular.COUNT > 0 THEN
                FOR c IN seccionesDesvincular.FIRST .. seccionesDesvincular.LAST LOOP
                    UPDATE ZON_SECCI
                    SET CLIE_OID_CLIE = NULL
                    WHERE OID_SECC = seccionesDesvincular(c).OID_SECC;

                    DELETE FROM MAE_CLIEN_CLASI
                    WHERE OID_CLIE_CLAS IN
                    (SELECT a.OID_CLIE_CLAS
                    FROM mae_clien_clasi a,
                    mae_clien_tipo_subti b ,
                    MAE_TIPO_CLASI_CLIEN x ,
                    MAE_CLASI y
                    WHERE a.ctsu_oid_clie_tipo_subt = b.Oid_Clie_Tipo_Subt
                    AND b.clie_oid_clie = Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(seccionesDesvincular(c).GERE)
                    AND b.Ind_Ppal = 1
                    AND a.tccl_oid_tipo_clasi = x.oid_tipo_clas
                    AND x.cod_tipo_clas = '01'
                    AND a.clas_oid_clas = y.oid_clas
                    AND y.cod_clas = '01'
                    AND b.sbti_oid_subt_clie = x.sbti_oid_subt_clie
                    AND x.oid_tipo_clas = y.tccl_oid_tipo_clas);
                END LOOP;
             END IF;

          EXIT WHEN C_SECCIONES_DESVINCULAR%NOTFOUND;
          END LOOP;
        CLOSE C_SECCIONES_DESVINCULAR;
      END IF;
      /* FIN JJ PER-SiCC-2012-0201 */

   EXCEPTION
   /* INI JJ PER-SiCC-2012-0201 */
   WHEN e_clasificacionLider THEN
       RAISE_APPLICATION_ERROR(-20001, ' Lider '||vsCodLider || ' no tiene Clasificaci�n para la campa�a de proceso ');
   /* FIN JJ PER-SiCC-2012-0201 */
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    raise_application_error(-20123, 'ERROR LET_PR_DESVI_AUTOM_LIDER:' ||ls_sqlerrm);
   END LET_PR_DESVI_AUTOM_LIDER;

   /***********************************************************************************************
    Descripcion       : Realiza el c�lculo de pedidos objetivos por secci�n y campa�a
    Fecha Creacion    : 07/03/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_CALCU_PEDID_OBJEC_SECAM(psCodigoPais VARCHAR2,
                                            psCodigoMarca VARCHAR2,
                                            psCodigoCanal VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoRegion VARCHAR2,
                                            psCodigoUsuario VARCHAR2) IS

     -- CURSORES ------------
     CURSOR C_VENTAS_REALES(
       codigoPeriodo ZON_TERRI_ADMIN.PERD_OID_PERI_INIC%TYPE
     ) IS
       SELECT    ZS.COD_SECC,
                 ZZ.COD_ZONA,
                 ZR.COD_REGI
       FROM      INT_FUENT_VENTAS_REAL FV,
                 ZON_TERRI_ADMIN ZT,
                 ZON_SECCI ZS,
                 ZON_ZONA ZZ,
                 ZON_REGIO ZR
       WHERE     FV.TERR_OID_TERR = ZT.TERR_OID_TERR
         AND     (
                   codigoPeriodo >= ZT.PERD_OID_PERI_INIC OR
                   ZT.PERD_OID_PERI_INIC IS NULL
                 )
         AND     (
                   codigoPeriodo <= ZT.PERD_OID_PERI_FINA OR
                   ZT.PERD_OID_PERI_FINA IS NULL
                 )
         AND     FV.PERD_OID_PERI = codigoPeriodo
         AND     ZT.ZSCC_OID_SECC = ZS.OID_SECC
         AND     ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
         AND     ZZ.ZORG_OID_REGI = ZR.OID_REGI
         AND     ZR.COD_REGI LIKE (CASE WHEN psCodigoRegion = '-1' THEN '%' ELSE psCodigoRegion END)
       GROUP BY  COD_SECC,
                 COD_ZONA,
                 COD_REGI
       ORDER BY  1,2,3;
     TYPE ventasRealesRecord IS RECORD (
       codigoSeccion ZON_SECCI.COD_SECC%TYPE,
       codigoZona ZON_ZONA.COD_ZONA%TYPE,
       codigoRegion ZON_REGIO.COD_REGI%TYPE
     );
     TYPE ventasRealesTab IS TABLE OF ventasRealesRecord;
     ventasReales ventasRealesTab;


     CURSOR C_VENTAS_ESTIMADAS(
       codigoPeriodoInicial INT_SAB_VENTA_PREVI_ZONA.COD_PERI%TYPE,
       codigoPeriodoFinal INT_SAB_VENTA_PREVI_ZONA.COD_PERI%TYPE,
       codigoRegion ZON_REGIO.COD_REGI%TYPE,
       codigoZona ZON_ZONA.COD_ZONA%TYPE
     ) IS
       SELECT    COD_PERI,
                 NUM_PEDI
       FROM      INT_SAB_VENTA_PREVI_ZONA
       WHERE     COD_PERI >= codigoPeriodoInicial
         AND     COD_PERI <= codigoPeriodoFinal
         AND     COD_REGI = codigoRegion
         AND     COD_ZONA = codigoZona
       ORDER BY  COD_ZONA,
                 COD_PERI;
     TYPE ventasEstimadasRecord IS RECORD(
       codigoPeriodo INT_SAB_VENTA_PREVI_ZONA.COD_PERI%TYPE,
       numeroPedido INT_SAB_VENTA_PREVI_ZONA.NUM_PEDI%TYPE
     );
     TYPE ventasEstimadasTab IS TABLE OF ventasEstimadasRecord;
     ventasEstimadas ventasEstimadasTab;


     -- VARIABLES -----------
     vnNumeroActiFinaSecc    INT_FUENT_VENTAS_REAL.NUM_ACTI_FINA%TYPE;
     vnNumeroActiFinaZona    INT_FUENT_VENTAS_REAL.NUM_ACTI_FINA%TYPE;
     vnPesoSeccion           NUMBER(10,4);
     vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
     vnOidMarca              SEG_MARCA.OID_MARC%TYPE;
     vnOidCanal              SEG_CANAL.OID_CANA%TYPE;
     vsCampSiguiente         SEG_PERIO_CORPO.COD_PERI%TYPE;
     vsCampSubSiguiente      SEG_PERIO_CORPO.COD_PERI%TYPE;
     vsCodigoConcurso        LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;
     vsCampInicial           LET_PARAM_CONCU_LIDER.CAM_INIC%TYPE;
     vsCampFinal             LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE;
     vnFlag                  NUMBER(1) := 0; -- 1 = Todos -  2 = Algunos - 3 = Ninguno
     vnCantidadFilas         NUMBER(2) := 0;
     vnObjetivoPedido        LET_OBJET_PEDID_SECCI.OBJ_PEDI%TYPE;
     vnFlagExiste            NUMBER(1) := 0;
     vnCantidadPeriodos      NUMBER(2);
     vnNumeroActiFina        NUMBER(13);
     vnPorcentajeActMeta     LET_PARAM_CONCU_LIDER.POR_ACTI_META%TYPE;
     vsCampProceso           SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnOidPeriodo            ZON_TERRI_ADMIN.PERD_OID_PERI_INIC%TYPE;
     vnExisteEjecucion       NUMBER(1);
     e_cierreRegion          EXCEPTION;
     vnCodPeriCalcular       SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnOidCampInicial        SEG_PERIO_CORPO.OID_PERI%TYPE;
     vnOidCampFinal          SEG_PERIO_CORPO.OID_PERI%TYPE;
     vsUltCamEstimada        SEG_PERIO_CORPO.COD_PERI%TYPE;
     vsCampFinalCon          SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnFlagExisteCam         NUMBER(1) := 0;
     vnFlagExisteCon         NUMBER(1) := 0;

   BEGIN

     -- Ejecucion doble a nivel region
     IF psCodigoRegion <> -1 THEN

       SELECT    COUNT(1)
         INTO    vnExisteEjecucion
       FROM      LET_ESTAT_PROCE
       WHERE     PAIS_COD_PAIS = psCodigoPais
         AND     CAM_PROC = psCodigoPeriodo
         AND     COD_REGI = psCodigoRegion;

       IF vnExisteEjecucion > 0 THEN
         RAISE e_cierreRegion;
       END IF;

     END IF;

     -- Obtener oid periodo
     vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
     vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
     vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
     vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
     vsCampSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,1);
     vsCampSubSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,2);

     -- Valiaciones de concurso tanto x nivel campa�a y concurso -------------------------

     -- Validar Si existe el Concurso x NIVEL CAMPA�A
     SELECT    COUNT(1)
       INTO    vnFlagExisteCam
     FROM      LET_PARAM_CONCU_LIDER
	   WHERE     (
	               CAM_INIC = vsCampSiguiente OR
	               CAM_INIC = vsCampSubSiguiente
	             )
	     AND     EST_REGI = '1';

	   IF vnFlagExisteCam > 0 THEN

       -- Encontrar Concurso Lider, campa�a proceso_+2 (campa�a inicial),
       -- campa�a fin del concurso encontrado
       SELECT    COD_CONC,
                 vsCampSubSiguiente,
                 CAM_FINA
         INTO    vsCodigoConcurso,
                 vsCampInicial,
                 vsCampFinal
       FROM      LET_PARAM_CONCU_LIDER
       WHERE     (
                   CAM_INIC = vsCampSiguiente OR
                   CAM_INIC = vsCampSubSiguiente
                 )
         AND     EST_REGI = '1';

     END IF;

     -- Validar Si existe el Concurso x NIVEL CONCURSO
     SELECT    COUNT(1)
       INTO    vnFlagExisteCon
     FROM      LET_PARAM_CONCU_LIDER
     WHERE     psCodigoPeriodo >= CAM_INIC
       AND     psCodigoPeriodo <= CAM_FINA
       AND     EST_REGI = '1';

	   IF vnFlagExisteCon > 0 THEN

       -- capturar campa�a fin del concurso (campa�a_final)
       SELECT    CAM_FINA
         INTO    vsCampFinalCon
       FROM      LET_PARAM_CONCU_LIDER
       WHERE     psCodigoPeriodo >= CAM_INIC
         AND     psCodigoPeriodo <= CAM_FINA
         AND     EST_REGI = '1';

       IF vsCampSubSiguiente <= vsCampFinalCon THEN

         -- Capturar Concurso Vigente
         SELECT    COD_CONC,
                   vsCampSubSiguiente,
                   CAM_FINA
           INTO    vsCodigoConcurso,
                   vsCampInicial,
                   vsCampFinal
         FROM      LET_PARAM_CONCU_LIDER
         WHERE     psCodigoPeriodo >= CAM_INIC
           AND     psCodigoPeriodo <= CAM_FINA
           AND     EST_REGI = '1';

       END IF;

	   END IF;

     -- Cursor de VENTAS REALES -------------------------------------------------------------
     OPEN C_VENTAS_REALES(
       vnOidPeriodo
     );
     LOOP

       FETCH C_VENTAS_REALES BULK COLLECT INTO ventasReales LIMIT W_FILAS;
       IF ventasReales.COUNT > 0 THEN

         -- Recorrer el Cursor Paginado de VENTAS REALES
         FOR i IN ventasReales.FIRST .. ventasReales.LAST LOOP

           -- Ejecucion doble a nivel region en cursor VENTAS REALES
           SELECT    COUNT(1)
             INTO    vnExisteEjecucion
           FROM      LET_ESTAT_PROCE
           WHERE     PAIS_COD_PAIS = psCodigoPais
             AND     CAM_PROC = psCodigoPeriodo
             AND     COD_REGI = ventasReales(i).codigoRegion;

           IF vnExisteEjecucion = 0 THEN

             -- ACUMULAR ACTIVAS FINALES X ZONA-SECCION
             SELECT    SUM(NUM_ACTI_FINA)
               INTO    vnNumeroActiFinaSecc
             FROM      INT_FUENT_VENTAS_REAL FV,
                       ZON_TERRI_ADMIN ZT,
                       ZON_SECCI ZS,
                       ZON_ZONA ZZ,
                       ZON_REGIO ZR
             WHERE     FV.TERR_OID_TERR = ZT.TERR_OID_TERR
               AND     (
                         vnOidPeriodo >= ZT.PERD_OID_PERI_INIC OR
                         ZT.PERD_OID_PERI_INIC IS NULL
                       )
               AND     (
                         vnOidPeriodo <= ZT.PERD_OID_PERI_FINA OR
                         ZT.PERD_OID_PERI_FINA IS NULL
                       )
               AND     FV.PERD_OID_PERI = vnOidPeriodo
               AND     ZT.ZSCC_OID_SECC = ZS.OID_SECC
               AND     ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
               AND     ZZ.ZORG_OID_REGI = ZR.OID_REGI
               AND     ZR.COD_REGI LIKE (CASE WHEN psCodigoRegion = '-1' THEN '%' ELSE psCodigoRegion END)
               AND     ZS.COD_SECC = ventasReales(i).codigoSeccion
               AND     COD_ZONA = ventasReales(i).codigoZona;

             -- ACUMULAR ACTIVAS FINALES X ZONA
             SELECT    SUM(NUM_ACTI_FINA)
               INTO    vnNumeroActiFinaZona
             FROM      INT_FUENT_VENTAS_REAL FV,
                       ZON_TERRI_ADMIN ZT,
                       ZON_SECCI ZS,
                       ZON_ZONA ZZ,
                       ZON_REGIO ZR
             WHERE     FV.TERR_OID_TERR = ZT.TERR_OID_TERR
               AND     (
                         vnOidPeriodo >= ZT.PERD_OID_PERI_INIC OR
                         ZT.PERD_OID_PERI_INIC IS NULL
                       )
               AND     (
                         vnOidPeriodo <= ZT.PERD_OID_PERI_FINA OR
                         ZT.PERD_OID_PERI_FINA IS NULL
                       )
               AND     FV.PERD_OID_PERI = vnOidPeriodo
               AND     ZT.ZSCC_OID_SECC = ZS.OID_SECC
               AND     ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
               AND     ZZ.ZORG_OID_REGI = ZR.OID_REGI
               AND     ZR.COD_REGI LIKE (CASE WHEN psCodigoRegion = '-1' THEN '%' ELSE psCodigoRegion END)
               AND     COD_ZONA = ventasReales(i).codigoZona;

             -- CALCULAR PESO SECCION
             BEGIN
               vnPesoSeccion := TO_NUMBER(vnNumeroActiFinaSecc) / TO_NUMBER(vnNumeroActiFinaZona);
             EXCEPTION
               WHEN OTHERS THEN
                 vnPesoSeccion := 0;
             END;

             -- OBJETIVO PEDIDO - A NIVEL CAMPA�A ---------------------------------------------------
             ----------------------------------------------------------------------------------------

             IF vnFlagExisteCam > 0 THEN

               -- 2.2 OBJETIVO PEDIDO X CAMPA�A
               vnCantidadFilas := 0;
               OPEN C_VENTAS_ESTIMADAS(
                 vsCampInicial,
                 vsCampFinal,
                 ventasReales(i).codigoRegion,
                 ventasReales(i).codigoZona
               );
               LOOP

                 FETCH C_VENTAS_ESTIMADAS BULK COLLECT INTO ventasEstimadas LIMIT W_FILAS;
                 IF ventasEstimadas.COUNT >0 THEN

                   -- Recorrer el Cursor Paginado de VENTAS ESTIMADAS
                   FOR j IN ventasEstimadas.FIRST .. ventasEstimadas.LAST LOOP

                     -- Calcular Objetivo Pedido
                     vnObjetivoPedido := CEIL(ventasEstimadas(j).numeroPedido * vnPesoSeccion);

                     -- Validar si existe Registro a INSERTAR
                     SELECT    COUNT(1)
                       INTO    vnFlagExiste
                     FROM      LET_OBJET_PEDID_SECCI
                     WHERE     PAIS_COD_PAIS = psCodigoPais
                       AND     CONC_COD_CONC = vsCodigoConcurso
                       AND     CAM_PROC = ventasEstimadas(j).codigoPeriodo
                       AND     COD_REGI = ventasReales(i).codigoRegion
                       AND     COD_ZONA = ventasReales(i).codigoZona
                       AND     COD_SECC = ventasReales(i).codigoSeccion;

                     -- INSERTAR Objetivo Pedido Seccion
                     IF vnFlagExiste = 0 THEN

                       INSERT INTO LET_OBJET_PEDID_SECCI (
                         PAIS_COD_PAIS,
                         CONC_COD_CONC,
                         CAM_PROC,
                         COD_REGI,
                         COD_ZONA,
                         COD_SECC,
                         OBJ_PEDI,
                         PES_SECC,
                         ACT_FINA,
                         USU_MODI,
                         FEC_MODI,
                         EST_REGI,
                         IND_ACTI
                       )VALUES(
                         psCodigoPais,
                         vsCodigoConcurso,
                         ventasEstimadas(j).codigoPeriodo,
                         ventasReales(i).codigoRegion,
                         ventasReales(i).codigoZona,
                         ventasReales(i).codigoSeccion,
                         vnObjetivoPedido,
                         vnPesoSeccion,
                         vnNumeroActiFinaSecc,
                         psCodigoUsuario,
                         SYSDATE,
                         '1',
                         'E'
                       );

                        -- INVOCAR a calculo de meta de lideres por campa�a
                         Let_Pkg_Proce.LET_PR_CALCU_METAS_LIDER_CAMPA(
                           psCodigoPais,
                           vsCodigoConcurso,
                           ventasEstimadas(j).codigoPeriodo,
                           ventasReales(i).codigoRegion,
                           ventasReales(i).codigoZona,
                           ventasReales(i).codigoSeccion,
                           vnObjetivoPedido,
                           psCodigoUsuario
                         );

                     END IF;

                   END LOOP; -- Fin del Cursor Paginado de VENTAS ESTIMADAS

                 END IF; -- ventasEstimadas.COUNT >0

                 -- Calcular la filas recorridas
                 vnCantidadFilas := ventasEstimadas.COUNT;

                 EXIT WHEN C_VENTAS_ESTIMADAS%NOTFOUND;
               END LOOP;
               CLOSE C_VENTAS_ESTIMADAS;

               -- 2.3 Calcular la cantidad de periodos del concurso
               vnOidCampInicial := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampInicial);
               vnOidCampFinal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampFinal);

               SELECT    COUNT(1)
                 INTO    vnCantidadPeriodos
               FROM      CRA_PERIO
               WHERE     OID_PERI >= vnOidCampInicial
                 AND     OID_PERI <= vnOidCampFinal;

               -- 2.4 Validar Si se encontro ESTIMADOS DE PEDIDOS
               IF vnCantidadFilas = 0 THEN
                 vnFlag := 3;
               ELSIF vnCantidadFilas < vnCantidadPeriodos THEN
                 vnFlag := 2;
               ELSIF vnCantidadFilas = vnCantidadPeriodos THEN
                 vnFlag := 1;
               ELSE
                 vnFlag := 0;
               END IF;

               -- 2.5 OBJETIVO PEDIDO X CAMPA�A - PARCIAL O NO SE ENCONTRO ESTIMADOS
               IF vnFlag = 2 OR vnFlag = 3 THEN

                 -- 2.5.1 OBJETIVO PEDIDO X CAMPA�A - PARCIAL
                 IF vnFlag = 2 THEN

                   -- Capturar Ultimo Periodo Recorrido en VENTAS ESTIMADAS + 1 (ultima_campa�a_estimadas)
                   SELECT    MAX(COD_PERI)
                     INTO    vsUltCamEstimada
                   FROM      INT_SAB_VENTA_PREVI_ZONA
                   WHERE     COD_PERI >= vsCampInicial
                     AND     COD_PERI <= vsCampFinal
                     AND     COD_REGI = ventasReales(i).codigoRegion
                     AND     COD_ZONA = ventasReales(i).codigoZona;

                   -- Campa�a Proceso
                   vsCampProceso := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(vsUltCamEstimada,vnOidPais,vnOidMarca,vnOidCanal,1);

                 END IF; -- 2.5.1 OBJETIVO PEDIDO X CAMPA�A - PARCIAL

                 -- 2.5.2 OBJETIVO PEDIDO X CAMPA�A - NO SE ENCONTRO ESTIMADOS
                 IF vnFlag = 3 THEN

                   -- Capturar Maximo Periodo en VENTAS ESTIMADAS (ultima_campa�a_estimadas)
                   SELECT    MAX(COD_PERI)
                     INTO    vsUltCamEstimada
                   FROM      INT_SAB_VENTA_PREVI_ZONA
                     WHERE   COD_REGI = ventasReales(i).codigoRegion
                     AND     COD_ZONA = ventasReales(i).codigoZona;

                   -- Campa�a Proceso
                   vsCampProceso := vsCampInicial;

                 END IF; -- 2.5.2 OBJETIVO PEDIDO X CAMPA�A - NO SE ENCONTRO ESTIMADOS

                 -- Capturar campa�a a calcular. En caso vsCampProceso ya halla sido ejecutada
                 SELECT    COUNT(1)
                   INTO    vnFlagExiste
                 FROM      LET_OBJET_PEDID_SECCI
                 WHERE     PAIS_COD_PAIS = psCodigoPais
                   AND     CONC_COD_CONC = vsCodigoConcurso
                   AND     CAM_PROC = vsCampProceso
                   AND     COD_REGI = ventasReales(i).codigoRegion
                   AND     COD_ZONA = ventasReales(i).codigoZona
                   AND     COD_SECC = ventasReales(i).codigoSeccion;

                 IF vnFlagExiste > 0 AND vsCampProceso < vsCampFinal THEN
                   LOOP
                     vnCodPeriCalcular := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsCampProceso,vnOidPais,vnOidMarca,vnOidCanal,1);

                     IF vnCodPeriCalcular > vsCampFinal THEN
                       vnFlagExiste := 0;
                       vsCampProceso := -1;
                     ELSE

                       SELECT    COUNT(1)
                         INTO    vnFlagExiste
                       FROM      LET_OBJET_PEDID_SECCI
                       WHERE     PAIS_COD_PAIS = psCodigoPais
                         AND     CONC_COD_CONC = vsCodigoConcurso
                         AND     CAM_PROC = vnCodPeriCalcular
                         AND     COD_REGI = ventasReales(i).codigoRegion
                         AND     COD_ZONA = ventasReales(i).codigoZona
                         AND     COD_SECC = ventasReales(i).codigoSeccion;

                       IF vnFlagExiste = 0 THEN
                         vsCampProceso := vnCodPeriCalcular;
                       ELSE
                         vsCampProceso := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsCampProceso,vnOidPais,vnOidMarca,vnOidCanal,1);
                       END IF;

                     END IF;

                     EXIT WHEN vnFlagExiste = 0;
                   END LOOP;
                 END IF;

                 IF vsCampProceso <> -1 THEN

                   -- 2.5.3 Calcular Activas Finales (ultima_campa�a_estimadas)
                   IF vsUltCamEstimada > psCodigoPeriodo THEN
                     SELECT    NUM_ACFI
                       INTO    vnNumeroActiFina
                     FROM      INT_SAB_VENTA_PREVI_ZONA
                     WHERE     COD_PERI = vsUltCamEstimada
                       AND     COD_REGI = ventasReales(i).codigoRegion
                       AND     COD_ZONA = ventasReales(i).codigoZona;
                   ELSE
                     vnNumeroActiFina := TO_NUMBER(vnNumeroActiFinaSecc);
                     vnPesoSeccion := 1;
                   END IF;

                   -- 2.5.4 Capturar Porcentaje Actividad Meta
                   SELECT    POR_ACTI_META
                     INTO    vnPorcentajeActMeta
                   FROM      LET_PARAM_CONCU_LIDER
                   WHERE     COD_CONC = vsCodigoConcurso
      							 AND     EST_REGI = '1';

                   -- 2.5.5 Calcular Objetivo Pedido
                   vnObjetivoPedido := CEIL(vnNumeroActiFina * vnPesoSeccion * (vnPorcentajeActMeta/100));

                   -- 2.5.6 Validar si existe Registro a INSERTAR
                   SELECT    COUNT(1)
                     INTO    vnFlagExiste
                   FROM      LET_OBJET_PEDID_SECCI
                   WHERE     PAIS_COD_PAIS = psCodigoPais
                     AND     CONC_COD_CONC = vsCodigoConcurso
                     AND     CAM_PROC = vsCampProceso
                     AND     COD_REGI = ventasReales(i).codigoRegion
                     AND     COD_ZONA = ventasReales(i).codigoZona
                     AND     COD_SECC = ventasReales(i).codigoSeccion;

                   -- 2.5.7 INSERTAR Objetivo Pedido Seccion
                   IF vnFlagExiste = 0 THEN
                     INSERT INTO LET_OBJET_PEDID_SECCI (
                       PAIS_COD_PAIS,
                       CONC_COD_CONC,
                       CAM_PROC,
                       COD_REGI,
                       COD_ZONA,
                       COD_SECC,
                       OBJ_PEDI,
                       PES_SECC,
                       ACT_FINA,
                       USU_MODI,
                       FEC_MODI,
                       EST_REGI,
                       IND_ACTI
                     )VALUES(
                       psCodigoPais,
                       vsCodigoConcurso,
                       vsCampProceso,
                       ventasReales(i).codigoRegion,
                       ventasReales(i).codigoZona,
                       ventasReales(i).codigoSeccion,
                       vnObjetivoPedido,
                       vnPesoSeccion,
                       vnNumeroActiFina,
                       psCodigoUsuario,
                       SYSDATE,
                       '1',
                       'R'
                     );

                    -- INVOCAR a calculo de meta de lideres por campa�a
                   Let_Pkg_Proce.LET_PR_CALCU_METAS_LIDER_CAMPA(
                     psCodigoPais,
                     vsCodigoConcurso,
                     vsCampProceso,
                     ventasReales(i).codigoRegion,
                     ventasReales(i).codigoZona,
                     ventasReales(i).codigoSeccion,
                     vnObjetivoPedido,
                     psCodigoUsuario
                   );

                   END IF; -- 2.5.7 INSERTAR Objetivo Pedido Seccion

                 END IF; -- Capturar campa�a a calcular. En caso vsCampProceso ya halla sido ejecutada

               END IF; -- 2.5 OBJETIVO PEDIDO X CAMPA�A - PARCIAL O NO SE ENCONTRO ESTIMADOS

             END IF; -- 2. Validar Si existe el Concurso

             -- OBJETIVO PEDIDO - A NIVEL CONCURSO --------------------------------------------------
             ----------------------------------------------------------------------------------------

             IF vnFlagExisteCon > 0 THEN

               -- 1.3 Validar que la campa�a subsiguiente sea menor igual
               --     a la campa�a fin del concurso
               IF vsCampSubSiguiente <= vsCampFinalCon THEN

                 -- 1.3.2 OBJETIVO PEDIDO X CONCURSO
                 vnCantidadFilas := 0;
                 OPEN C_VENTAS_ESTIMADAS(
                   vsCampInicial,
                   vsCampFinal,
                   ventasReales(i).codigoRegion,
                   ventasReales(i).codigoZona
                 );
                 LOOP

                   FETCH C_VENTAS_ESTIMADAS BULK COLLECT INTO ventasEstimadas LIMIT W_FILAS;
                   IF ventasEstimadas.COUNT >0 THEN

                     -- Recorrer el Cursor Paginado de VENTAS ESTIMADAS
                     FOR j IN ventasEstimadas.FIRST .. ventasEstimadas.LAST LOOP

                       -- Calcular Objetivo Pedido
                       vnObjetivoPedido := ventasEstimadas(j).numeroPedido * vnPesoSeccion;

                       -- Validar si existe Registro a INSERTAR
                       SELECT    COUNT(1)
                         INTO    vnFlagExiste
                       FROM      LET_OBJET_PEDID_SECCI
                       WHERE     PAIS_COD_PAIS = psCodigoPais
                         AND     CONC_COD_CONC = vsCodigoConcurso
                         AND     CAM_PROC = ventasEstimadas(j).codigoPeriodo
                         AND     COD_REGI = ventasReales(i).codigoRegion
                         AND     COD_ZONA = ventasReales(i).codigoZona
                         AND     COD_SECC = ventasReales(i).codigoSeccion;

                       -- INSERTAR Objetivo Pedido Seccion
                       IF vnFlagExiste = 0 THEN
                         INSERT INTO LET_OBJET_PEDID_SECCI (
                           PAIS_COD_PAIS,
                           CONC_COD_CONC,
                           CAM_PROC,
                           COD_REGI,
                           COD_ZONA,
                           COD_SECC,
                           OBJ_PEDI,
                           PES_SECC,
                           ACT_FINA,
                           USU_MODI,
                           FEC_MODI,
                           EST_REGI,
                           IND_ACTI
                         )VALUES(
                           psCodigoPais,
                           vsCodigoConcurso,
                           ventasEstimadas(j).codigoPeriodo,
                           ventasReales(i).codigoRegion,
                           ventasReales(i).codigoZona,
                           ventasReales(i).codigoSeccion,
                           vnObjetivoPedido,
                           vnPesoSeccion,
                           vnNumeroActiFinaSecc,
                           psCodigoUsuario,
                           SYSDATE,
                           '1',
                           'E'
                         );

                         -- INVOCAR a calculo de meta de lideres por campa�a
                         Let_Pkg_Proce.LET_PR_CALCU_METAS_LIDER_CAMPA(
                           psCodigoPais,
                           vsCodigoConcurso,
                           ventasEstimadas(j).codigoPeriodo,
                           ventasReales(i).codigoRegion,
                           ventasReales(i).codigoZona,
                           ventasReales(i).codigoSeccion,
                           vnObjetivoPedido,
                           psCodigoUsuario
                         );

                       END IF;

                     END LOOP; -- Fin del Cursor Paginado de VENTAS ESTIMADAS

                   END IF; -- ventasEstimadas.COUNT >0

                   -- Calcular la filas recorridas
                   vnCantidadFilas := ventasEstimadas.COUNT;

                   EXIT WHEN C_VENTAS_ESTIMADAS%NOTFOUND;
                 END LOOP;
                 CLOSE C_VENTAS_ESTIMADAS;

                 -- 1.3.3 Calcular la cantidad de periodos del concurso
                 vnOidCampInicial := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampInicial);
                 vnOidCampFinal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampFinal);

                 SELECT    COUNT(1)
                   INTO    vnCantidadPeriodos
                 FROM      CRA_PERIO
                 WHERE     OID_PERI >= vnOidCampInicial
                   AND     OID_PERI <= vnOidCampFinal;

                 -- 1.3.4 Validar Si se encontro ESTIMADOS DE PEDIDOS
                 IF vnCantidadFilas = 0 THEN
                   vnFlag := 3;
                 ELSIF vnCantidadFilas < vnCantidadPeriodos THEN
                   vnFlag := 2;
                 ELSIF vnCantidadFilas = vnCantidadPeriodos THEN
                   vnFlag := 1;
                 ELSE
                   vnFlag := 0;
                 END IF;

                 -- 1.3.5 OBJETIVO PEDIDO X CONCURSO - PARCIAL O NO SE ENCONTRO ESTIMADOS
                 IF vnFlag = 2 OR vnFlag = 3 THEN

                   -- 1.3.5.1 OBJETIVO PEDIDO X CAMPA�A - PARCIAL
                   IF vnFlag = 2 THEN

                     -- Capturar Ultimo Periodo Recorrido en VENTAS ESTIMADAS + 1 (ultima_campa�a_estimadas)
                     SELECT    MAX(COD_PERI)
                       INTO    vsUltCamEstimada
                     FROM      INT_SAB_VENTA_PREVI_ZONA
                     WHERE     COD_PERI >= vsCampInicial
                       AND     COD_PERI <= vsCampFinal
                       AND     COD_REGI = ventasReales(i).codigoRegion
                       AND     COD_ZONA = ventasReales(i).codigoZona;

                     -- Campa�a Proceso
                     vsCampProceso := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(vsUltCamEstimada,vnOidPais,vnOidMarca,vnOidCanal,1);

                   END IF; -- 1.3.5.1 OBJETIVO PEDIDO X CAMPA�A - PARCIAL

                   -- 1.3.5.2 OBJETIVO PEDIDO X CAMPA�A - NO SE ENCONTRO ESTIMADOS
                   IF vnFlag = 3 THEN

                     -- Capturar Maximo Periodo en VENTAS ESTIMADAS (ultima_campa�a_estimadas)
                     SELECT    MAX(COD_PERI)
                       INTO    vsUltCamEstimada
                     FROM      INT_SAB_VENTA_PREVI_ZONA
                       WHERE   COD_REGI = ventasReales(i).codigoRegion
                       AND     COD_ZONA = ventasReales(i).codigoZona;

                     -- Campa�a Proceso
                     vsCampProceso := vsCampInicial;

                   END IF; -- 1.3.5.2 OBJETIVO PEDIDO X CAMPA�A - NO SE ENCONTRO ESTIMADOS

                   -- Capturar campa�a a calcular. En caso vsCampProceso ya halla sido ejecutada
                   SELECT    COUNT(1)
                     INTO    vnFlagExiste
                   FROM      LET_OBJET_PEDID_SECCI
                   WHERE     PAIS_COD_PAIS = psCodigoPais
                     AND     CONC_COD_CONC = vsCodigoConcurso
                     AND     CAM_PROC = vsCampProceso
                     AND     COD_REGI = ventasReales(i).codigoRegion
                     AND     COD_ZONA = ventasReales(i).codigoZona
                     AND     COD_SECC = ventasReales(i).codigoSeccion;

                   IF vnFlagExiste > 0 AND vsCampProceso < vsCampFinal THEN
                     LOOP
                       vnCodPeriCalcular := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsCampProceso,vnOidPais,vnOidMarca,vnOidCanal,1);

                       IF vnCodPeriCalcular > vsCampFinal THEN
                         vnFlagExiste := 0;
                         vsCampProceso := -1;
                       ELSE

                         SELECT    COUNT(1)
                           INTO    vnFlagExiste
                         FROM      LET_OBJET_PEDID_SECCI
                         WHERE     PAIS_COD_PAIS = psCodigoPais
                           AND     CONC_COD_CONC = vsCodigoConcurso
                           AND     CAM_PROC = vnCodPeriCalcular
                           AND     COD_REGI = ventasReales(i).codigoRegion
                           AND     COD_ZONA = ventasReales(i).codigoZona
                           AND     COD_SECC = ventasReales(i).codigoSeccion;

                         IF vnFlagExiste = 0 THEN
                           vsCampProceso := vnCodPeriCalcular;
                         ELSE
                           vsCampProceso := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsCampProceso,vnOidPais,vnOidMarca,vnOidCanal,1);
                         END IF;

                       END IF;

                       EXIT WHEN vnFlagExiste = 0;
                     END LOOP;
                   END IF;

                   IF vsCampProceso <> -1 THEN

                     -- 1.3.5.3 Calcular Activas Finales (ultima_campa�a_estimadas)
                     IF vsUltCamEstimada > psCodigoPeriodo THEN
                       SELECT    NUM_ACFI
                         INTO    vnNumeroActiFina
                       FROM      INT_SAB_VENTA_PREVI_ZONA
                       WHERE     COD_PERI = vsUltCamEstimada
                         AND     COD_REGI = ventasReales(i).codigoRegion
                         AND     COD_ZONA = ventasReales(i).codigoZona;
                     ELSE
                       vnNumeroActiFina := TO_NUMBER(vnNumeroActiFinaSecc);
                       vnPesoSeccion := 1;
                     END IF;

                     -- 1.3.5.4 Capturar Porcentaje Actividad Meta
                     SELECT    POR_ACTI_META
                       INTO    vnPorcentajeActMeta
                     FROM      LET_PARAM_CONCU_LIDER
                     WHERE     COD_CONC = vsCodigoConcurso
     								   AND     EST_REGI = '1';

                     -- 1.3.5.5 Calcular Objetivo Pedido
                     vnObjetivoPedido := vnNumeroActiFina * vnPesoSeccion * (vnPorcentajeActMeta/100);

                     -- 1.3.5.6 Validar si existe Registro a INSERTAR
                     SELECT    COUNT(1)
                       INTO    vnFlagExiste
                     FROM      LET_OBJET_PEDID_SECCI
                     WHERE     PAIS_COD_PAIS = psCodigoPais
                       AND     CONC_COD_CONC = vsCodigoConcurso
                       AND     CAM_PROC = vsCampProceso
                       AND     COD_REGI = ventasReales(i).codigoRegion
                       AND     COD_ZONA = ventasReales(i).codigoZona
                       AND     COD_SECC = ventasReales(i).codigoSeccion;

                     -- 1.3.5.7 INSERTAR Objetivo Pedido Seccion
                     IF vnFlagExiste = 0 THEN
                       INSERT INTO LET_OBJET_PEDID_SECCI (
                         PAIS_COD_PAIS,
                         CONC_COD_CONC,
                         CAM_PROC,
                         COD_REGI,
                         COD_ZONA,
                         COD_SECC,
                         OBJ_PEDI,
                         PES_SECC,
                         ACT_FINA,
                         USU_MODI,
                         FEC_MODI,
                         EST_REGI,
                         IND_ACTI
                       )VALUES(
                         psCodigoPais,
                         vsCodigoConcurso,
                         vsCampProceso,
                         ventasReales(i).codigoRegion,
                         ventasReales(i).codigoZona,
                         ventasReales(i).codigoSeccion,
                         vnObjetivoPedido,
                         vnPesoSeccion,
                         vnNumeroActiFina,
                         psCodigoUsuario,
                         SYSDATE,
                         '1',
                         'R'
                       );

                        -- INVOCAR a calculo de meta de lideres por campa�a
                     Let_Pkg_Proce.LET_PR_CALCU_METAS_LIDER_CAMPA(
                       psCodigoPais,
                       vsCodigoConcurso,
                       vsCampProceso,
                       ventasReales(i).codigoRegion,
                       ventasReales(i).codigoZona,
                       ventasReales(i).codigoSeccion,
                       vnObjetivoPedido,
                       psCodigoUsuario
                     );

                     END IF; -- 1.3.5.7 INSERTAR Objetivo Pedido Seccion

                   END IF; -- Capturar campa�a a calcular. En caso vsCampProceso ya halla sido ejecutada

                 END IF; -- 1.3.5 OBJETIVO PEDIDO X CONCURSO - PARCIAL O NO SE ENCONTRO ESTIMADOS

               END IF; -- 1.3 Validar que la campa�a subsiguiente ....

             END IF; -- 1. Validar Si existe el Concurso

             ----------------------------------------------------------------------------------------
             ----------------------------------------------------------------------------------------

           END IF; -- Ejecucion doble a nivel region en cursor VENTAS REALES

         END LOOP; -- Fin del Cursor Paginado de VENTAS REALES

       END IF; -- ventasReales.COUNT > 0

       EXIT WHEN C_VENTAS_REALES%NOTFOUND;
     END LOOP;
     CLOSE C_VENTAS_REALES;


     -- Registrar periodo y regiones ejecutadas
     INSERT INTO LET_ESTAT_PROCE
     SELECT    *
     FROM      (
                 SELECT    psCodigoPais COD_PAIS,
                           psCodigoPeriodo COD_PERI,
                           ZR.COD_REGI
                 FROM      INT_FUENT_VENTAS_REAL FV,
                           ZON_TERRI_ADMIN ZT,
                           ZON_SECCI ZS,
                           ZON_ZONA ZZ,
                           ZON_REGIO ZR
                 WHERE     FV.TERR_OID_TERR = ZT.TERR_OID_TERR
                   AND     (
                             vnOidPeriodo >= ZT.PERD_OID_PERI_INIC OR
                             ZT.PERD_OID_PERI_INIC IS NULL
                           )
                   AND     (
                             vnOidPeriodo <= ZT.PERD_OID_PERI_FINA OR
                             ZT.PERD_OID_PERI_FINA IS NULL
                           )
                   AND     FV.PERD_OID_PERI = vnOidPeriodo
                   AND     ZT.ZSCC_OID_SECC = ZS.OID_SECC
                   AND     ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
                   AND     ZZ.ZORG_OID_REGI = ZR.OID_REGI
                   AND     ZR.COD_REGI LIKE (CASE WHEN psCodigoRegion = '-1' THEN '%' ELSE psCodigoRegion END)
                 GROUP BY  COD_REGI
               ) X
     WHERE     (COD_PAIS,COD_PERI,COD_REGI) NOT IN (
                 SELECT * FROM LET_ESTAT_PROCE
               );

   EXCEPTION
     WHEN e_cierreRegion THEN
       RAISE_APPLICATION_ERROR(-20001, 'PROCESO YA EJECUTADO PARA ESTA REGI�N');
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_PEDID_OBJEC_SECAM: ' || Ls_Sqlerrm);
   END LET_PR_CALCU_PEDID_OBJEC_SECAM;

   /***********************************************************************************************
    Descripcion       : Realiza el c�lculo de metas de l�deres por campa�a
    Fecha Creacion    : 07/03/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_CALCU_METAS_LIDER_CAMPA(psCodigoPais VARCHAR2,
                                            psCodigoConcurso VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoRegion VARCHAR2,
                                            psCodigoZona VARCHAR2,
                                            psCodigoSeccion VARCHAR2,
                                            psObjetivoPedido VARCHAR2,
                                            psCodigoUsuario VARCHAR2) IS

     -- CURSORES ------------
     CURSOR C_NIVEL_CAMPA IS
       SELECT    NICA_NIVE_CAMP,
                 INC_PEDI,
                 TIVA_COD_TIPO
       FROM      LET_PARAM_NIVEL_CAMPA
       WHERE     PAIS_COD_PAIS = psCodigoPais
         AND     CONC_COD_CONC = psCodigoConcurso
         AND     (NICA_NIVE_CAMP > 0 AND NICA_NIVE_CAMP < 99)
       ORDER BY  1;
     TYPE nivelCampaniaRecord IS RECORD(
       nivel LET_PARAM_NIVEL_CAMPA.NICA_NIVE_CAMP%TYPE,
       incrementoPedido LET_PARAM_NIVEL_CAMPA.INC_PEDI%TYPE,
       tipoVariable LET_PARAM_NIVEL_CAMPA.TIVA_COD_TIPO%TYPE
     );
     TYPE nivelCampaniaTab IS TABLE OF nivelCampaniaRecord;
     nivelCampania nivelCampaniaTab;


     -- VARIABLES -----------
     vnMetaPedido LET_METAS_LIDER_CAMPA.MET_PEDI%TYPE;


   BEGIN

     -- Eliminar datos de la campa�a a procesar
     DELETE
     FROM      LET_METAS_LIDER_CAMPA
     WHERE     PAIS_COD_PAIS = psCodigoPais
       AND     CONC_COD_CONC = psCodigoConcurso
       AND     CAM_EVAL = psCodigoPeriodo
       AND     COD_REGI = psCodigoRegion
       AND     COD_ZONA = psCodigoZona
       AND     COD_SECC = psCodigoSeccion;

     -- Cursos de NIVEL CAMPA�A
     OPEN C_NIVEL_CAMPA;
     LOOP

       FETCH C_NIVEL_CAMPA BULK COLLECT INTO nivelCampania LIMIT W_FILAS;
       IF nivelCampania.COUNT > 0 THEN

         -- Recorrer el Cursor Paginado de NIVEL CAMPA�A
         FOR i IN nivelCampania.FIRST .. nivelCampania.LAST LOOP

           -- Obtener meta_pedido
           IF nivelCampania(i).tipoVariable = 'P' OR nivelCampania(i).tipoVariable = 'A' THEN
              vnMetaPedido := psObjetivoPedido + nivelCampania(i).incrementoPedido;
           ELSE
               vnMetaPedido := 0;
           END IF;

           -- INSERTAR Metas de lideres por campa�a
           INSERT INTO LET_METAS_LIDER_CAMPA(
             PAIS_COD_PAIS,
             CONC_COD_CONC,
             CAM_EVAL,
             COD_REGI,
             COD_ZONA,
             COD_SECC,
             NICA_NIVE_CAMP,
             MET_PEDI,
             USU_MODI,
             FEC_MODI,
             EST_REGI
           ) VALUES (
             psCodigoPais,
             psCodigoConcurso,
             psCodigoPeriodo,
             psCodigoRegion,
             psCodigoZona,
             psCodigoSeccion,
             nivelCampania(i).nivel,
             vnMetaPedido,
             psCodigoUsuario,
             SYSDATE,
             '1'
           );

         END LOOP; -- Fin del Cursor Paginado de NIVEL CAMPA�A

       END IF; -- nivelCampania.COUNT > 0

       EXIT WHEN C_NIVEL_CAMPA%NOTFOUND;
     END LOOP;
     CLOSE C_NIVEL_CAMPA;

   EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_METAS_LIDER_CAMPA: ' || Ls_Sqlerrm);
   END LET_PR_CALCU_METAS_LIDER_CAMPA;

   /***********************************************************************************************
    Descripcion       : Realiza el c�lculo de acumulado de metas de pedidos por concurso
    Fecha Creacion    : 20/10/2011
    Autor             : Jesse James Rios Franco
   ***********************************************************************************************/
   PROCEDURE LET_PR_CALCU_METAS_LIDER_CONCU(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoUsuario VARCHAR2) IS

     -- CURSORES ------------
     CURSOR C_CONCURSOS(campanaSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE,campanaSubSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE) IS
     SELECT COD_CONC,CAM_INIC,CAM_FINA
     FROM LET_PARAM_CONCU_LIDER
     WHERE PAIS_COD_PAIS = psCodigoPais
     AND (cam_inic = campanaSiguiente or cam_inic = campanaSubSiguiente)
     UNION
     SELECT COD_CONC,CAM_INIC,CAM_FINA
     FROM LET_PARAM_CONCU_LIDER
     WHERE PAIS_COD_PAIS = psCodigoPais
     AND psCodigoPeriodo >= cam_inic
     AND psCodigoPeriodo <= cam_fina;

     TYPE concursoRecord IS RECORD(
       COD_CONC LET_PARAM_CONCU_LIDER.COD_CONC%TYPE,
       CAM_INIC LET_PARAM_CONCU_LIDER.CAM_INIC%TYPE,
       CAM_FINA LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE
     );

     TYPE concursoTab IS TABLE OF concursoRecord;
     concurso concursoTab;

     CURSOR C_NIVE_CONC (codigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE) IS
     SELECT NICO_NIVE_CONC,
            POR_INCR
     FROM  LET_PARAM_NIVEL_CONCU
     WHERE PAIS_COD_PAIS = psCodigoPais
     AND     CONC_COD_CONC = codigoConcurso
     AND     NICO_NIVE_CONC > 0
     ORDER BY  1;

     TYPE nivelConcursoRecord IS RECORD(
       codigoNivelConcurso LET_PARAM_NIVEL_CONCU.NICO_NIVE_CONC%TYPE,
       porcentajeInc LET_PARAM_NIVEL_CONCU.POR_INCR%TYPE
     );

     TYPE nivelConcursoTab IS TABLE OF nivelConcursoRecord;
     nivelConcurso nivelConcursoTab;

     CURSOR C_LIDERES_CAMPANA(oidPais SEG_PAIS.OID_PAIS%TYPE,oidPeriodoInicioConcurso SEG_PERIO_CORPO.OID_PERI%TYPE,oidPeriodoFinConcurso SEG_PERIO_CORPO.OID_PERI%TYPE) IS
     SELECT DISTINCT COD_LIDE,
            MIN(PERD_OID_PERI_DESD),
            MAX(PERD_OID_PERI_HAST)
      FROM(SELECT GERE as COD_LIDE,
                   CASE
                       WHEN PERD_OID_PERI_DESD < oidPeriodoInicioConcurso THEN
                            oidPeriodoInicioConcurso
                       ELSE
                            PERD_OID_PERI_DESD
                   END AS PERD_OID_PERI_DESD,
                   NVL2(PERD_OID_PERI_HAST,(CASE
                                            WHEN PERD_OID_PERI_HAST > oidPeriodoFinConcurso THEN
                                                 oidPeriodoFinConcurso
                                            ELSE
                                                PERD_OID_PERI_HAST
                                            END),oidPeriodoFinConcurso) AS PERD_OID_PERI_HAST
            FROM ZON_HISTO_GEREN
            WHERE PAIS_OID_PAIS = oidPais
            AND LENGTH(UA) = 9
            AND ( (PERD_OID_PERI_DESD >= oidPeriodoInicioConcurso AND PERD_OID_PERI_DESD <= oidPeriodoFinConcurso)
                   OR (PERD_OID_PERI_HAST >= oidPeriodoInicioConcurso AND PERD_OID_PERI_HAST <= oidPeriodoFinConcurso)
                   OR (PERD_OID_PERI_DESD <= oidPeriodoInicioConcurso AND PERD_OID_PERI_HAST IS NULL)
                   OR (PERD_OID_PERI_DESD <= oidPeriodoInicioConcurso AND PERD_OID_PERI_HAST >= oidPeriodoFinConcurso)
                )
            order by GERE)
      GROUP BY COD_LIDE
      ORDER BY COD_LIDE;

     TYPE lideresCampanaRecord IS RECORD (
       COD_LIDE ZON_HISTO_GEREN.GERE%TYPE,
       OID_PERI_MINI SEG_PERIO_CORPO.OID_PERI%TYPE,
       OID_PERI_MAXI SEG_PERIO_CORPO.OID_PERI%TYPE
     );

     TYPE lideresCampanaTab IS TABLE OF lideresCampanaRecord;
     lideresCampana lideresCampanaTab;

     CURSOR C_NIVELES_METAS_LIDERES(codigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE,codigoLider LET_METAS_LIDER_CONCU.COD_LIDE%TYPE) IS
     SELECT NICO_NIVE_CONC,OBJ_ACUM
     FROM LET_METAS_LIDER_CONCU
     WHERE PAIS_COD_PAIS = psCodigoPais
     AND CONC_COD_CONC = codigoConcurso
     AND COD_LIDE = codigoLider;

     TYPE nivelesMetasLideresRecord IS RECORD(
        NICO_NIVE_CONC LET_METAS_LIDER_CONCU.NICO_NIVE_CONC%TYPE,
        OBJ_ACUM       LET_METAS_LIDER_CONCU.OBJ_ACUM%TYPE
     );

     TYPE nivelesMetasLideresTab IS TABLE OF nivelesMetasLideresRecord;
     nivelesMetasLideres nivelesMetasLideresTab;
     -- VARIABLES -----------

     vnOidPais       SEG_PAIS.OID_PAIS%TYPE;
     vnOidMarca      SEG_MARCA.OID_MARC%TYPE;
     vnOidCanal      SEG_CANAL.OID_CANA%TYPE;
     vsCampanaSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE;
     vsCampanaSubSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnOidPeriodoInicioConcurso SEG_PERIO_CORPO.OID_PERI%TYPE;
     vnOidPeriodoFinConcurso SEG_PERIO_CORPO.OID_PERI%TYPE;
     vsCodigoPeriodoMinLider SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnCodigoPeriodoMaxLider SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnNumeroCampanas NUMBER;
     vsCampanaLider SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnOidCampanaLider SEG_PERIO_CORPO.OID_PERI%TYPE;
     vnExisteLider NUMBER;
     vsUA ZON_HISTO_GEREN.UA%TYPE;
     vsUAAnterior ZON_HISTO_GEREN.UA%TYPE;
     vbAcumularObjetivos BOOLEAN;
     vsCodigoRegion ZON_REGIO.COD_REGI%TYPE;
     vsCodigoZona ZON_ZONA.COD_ZONA%TYPE;
     vsCodigoSeccion ZON_SECCI.COD_SECC%TYPE;
     vnObjetivoPedido LET_OBJET_PEDID_SECCI.OBJ_PEDI%TYPE;
     vnExisteMeta NUMBER;
     vnObjetivoPedidoTotal LET_OBJET_PEDID_SECCI.OBJ_PEDI%TYPE;
     vnMetaPedido LET_METAS_LIDER_CONCU.MET_PEDI%TYPE;

   BEGIN

     vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
     vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA('T');
     vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
     vsCampanaSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,1);
     vsCampanaSubSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,2);

     OPEN C_CONCURSOS(vsCampanaSiguiente,vsCampanaSubSiguiente);
     LOOP
          FETCH C_CONCURSOS BULK COLLECT INTO concurso LIMIT W_FILAS;
          IF concurso.COUNT > 0 THEN
             FOR i IN concurso.FIRST .. concurso.LAST LOOP
                 DELETE FROM LET_METAS_LIDER_CONCU
                 WHERE PAIS_COD_PAIS = psCodigoPais
                 AND   CONC_COD_CONC = concurso(i).COD_CONC;

                 vnOidPeriodoInicioConcurso := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(concurso(i).CAM_INIC);
                 vnOidPeriodoFinConcurso := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(concurso(i).CAM_FINA);

                 OPEN C_LIDERES_CAMPANA(vnOidPais,vnOidPeriodoInicioConcurso,vnOidPeriodoFinConcurso);

                 LOOP
                   FETCH C_LIDERES_CAMPANA BULK COLLECT INTO lideresCampana LIMIT W_FILAS;
                   IF lideresCampana.COUNT > 0 THEN
                     FOR j IN lideresCampana.FIRST .. lideresCampana.LAST LOOP
                      vsCodigoPeriodoMinLider := GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(lideresCampana(j).OID_PERI_MINI);
                      vnCodigoPeriodoMaxLider := GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(lideresCampana(j).OID_PERI_MAXI);
                      vnNumeroCampanas := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(vsCodigoPeriodoMinLider,concurso(i).CAM_FINA,vnOidPais,vnOidMarca,vnOidCanal);

                      FOR c IN 0 .. (vnNumeroCampanas-1) LOOP

                          vsCampanaLider := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsCodigoPeriodoMinLider,vnOidPais,vnOidMarca,vnOidCanal,c);
                          vnOidCampanaLider := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampanaLider);

                          SELECT COUNT(1)
                            INTO vnExisteLider
                            FROM zon_histo_geren
                           WHERE pais_oid_pais = vnOidPais
                             AND gere = lideresCampana(j).cod_lide
                             AND vnOidCampanaLider >= perd_oid_peri_desd
                             AND (vnOidCampanaLider <= perd_oid_peri_hast OR perd_oid_peri_hast IS NULL)
                             AND LENGTH(ua) = 9;

                          IF vnExisteLider = 0 THEN
                             IF vnCodigoPeriodoMaxLider <= vsCampanaLider THEN
                               vsUA := vsUAAnterior;
                               vbAcumularObjetivos := TRUE;
                             ELSE
                               vbAcumularObjetivos := FALSE;
                             END IF;
                          ELSE
                             SELECT UA
                             INTO vsUA
                             FROM ZON_HISTO_GEREN
                             WHERE PAIS_OID_PAIS = vnOidPais
                             AND GERE = lideresCampana(j).COD_LIDE
                             AND vnOidCampanaLider >= PERD_OID_PERI_DESD
                             AND (vnOidCampanaLider <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
                             AND LENGTH (UA) = 9;

                             vsUAAnterior:= vsUA;

                             vbAcumularObjetivos := TRUE;
                          END IF;

                          IF vbAcumularObjetivos THEN

                             vsCodigoRegion := SUBSTR(vsUA,3,2);
                             vsCodigoZona := SUBSTR(vsUA,5,4);
                             vsCodigoSeccion := SUBSTR(vsUA,9);

                             BEGIN
                               SELECT OBJ_PEDI
                               INTO vnObjetivoPedido
                               FROM LET_OBJET_PEDID_SECCI
                               WHERE PAIS_COD_PAIS = psCodigoPais
                               AND CAM_PROC = vsCampanaLider
                               AND CONC_COD_CONC = concurso(i).COD_CONC
                               AND COD_REGI = vsCodigoRegion
                               AND COD_ZONA = vsCodigoZona
                               AND COD_SECC = vsCodigoSeccion;
                             EXCEPTION
                             WHEN NO_DATA_FOUND THEN
                               vnObjetivoPedido := 0;
                             END;

                             SELECT COUNT(1)
                             INTO vnExisteMeta
                             FROM LET_METAS_LIDER_CONCU
                             WHERE PAIS_COD_PAIS = psCodigoPais
                             AND CONC_COD_CONC = concurso(i).COD_CONC
                             AND COD_LIDE = lideresCampana(j).COD_LIDE;

                             IF vnExisteMeta = 0 THEN

                                OPEN C_NIVE_CONC(concurso(i).COD_CONC);
                                 LOOP
                                   FETCH C_NIVE_CONC BULK COLLECT INTO nivelConcurso LIMIT W_FILAS;
                                   IF nivelConcurso.COUNT > 0 THEN
                                       FOR x IN nivelConcurso.FIRST .. nivelConcurso.LAST LOOP
                                         IF vnObjetivoPedido > 0 THEN
                                             INSERT INTO LET_METAS_LIDER_CONCU (
                                               PAIS_COD_PAIS,
                                               CONC_COD_CONC,
                                               NICO_NIVE_CONC,
                                               COD_LIDE,
                                               CAM_INIC,
                                               USU_MODI,
                                               FEC_MODI,
                                               EST_REGI,
                                               OBJ_ACUM,
                                               CAM_FINA,
                                               MET_PEDI
                                             ) VALUES (
                                               psCodigoPais,
                                               concurso(i).COD_CONC,
                                               nivelConcurso(x).codigoNivelConcurso,
                                               lideresCampana(j).COD_LIDE,
                                               vsCampanaLider,
                                               psCodigoUsuario,
                                               SYSDATE,
                                               '1',
                                               vnObjetivoPedido,
                                               vsCampanaLider,
                                               0
                                             );

                                             vnObjetivoPedidoTotal := vnObjetivoPedido;
                                         END IF;
                                     END LOOP;
                                   END IF;
                                 EXIT WHEN C_NIVE_CONC%NOTFOUND;
                                 END LOOP;
                                 CLOSE C_NIVE_CONC;
                             ELSE
                                OPEN C_NIVELES_METAS_LIDERES(concurso(i).COD_CONC,lideresCampana(j).COD_LIDE);
                                LOOP
                                FETCH C_NIVELES_METAS_LIDERES BULK COLLECT INTO nivelesMetasLideres LIMIT W_FILAS;

                                  IF nivelesMetasLideres.COUNT > 0 THEN
                                     FOR v IN nivelesMetasLideres.FIRST .. nivelesMetasLideres.LAST LOOP

                                         vnObjetivoPedidoTotal := nivelesMetasLideres(v).OBJ_ACUM + vnObjetivoPedido;
                                         IF vnObjetivoPedido > 0 THEN
                                             UPDATE LET_METAS_LIDER_CONCU
                                             SET OBJ_ACUM = vnObjetivoPedidoTotal,
                                                 CAM_FINA = vsCampanaLider,
                                                 USU_MODI = psCodigoUsuario,
                                                 FEC_MODI = SYSDATE
                                             WHERE PAIS_COD_PAIS = psCodigoPais
                                             AND CONC_COD_CONC = concurso(i).COD_CONC
                                             AND NICO_NIVE_CONC = nivelesMetasLideres(v).NICO_NIVE_CONC
                                             AND COD_LIDE = lideresCampana(j).COD_LIDE;
                                         END IF;
                                     END LOOP;
                                  END IF;

                                EXIT WHEN C_NIVELES_METAS_LIDERES%NOTFOUND;
                                END LOOP;
                                CLOSE C_NIVELES_METAS_LIDERES;
                             END IF;

                          END IF;

                      END LOOP;

                      -------- Finalzo la carga de campa�as
                      OPEN C_NIVE_CONC(concurso(i).COD_CONC);
                      LOOP
                        FETCH C_NIVE_CONC BULK COLLECT INTO nivelConcurso LIMIT W_FILAS;
                          IF nivelConcurso.COUNT > 0 THEN
                             FOR k IN nivelConcurso.FIRST .. nivelConcurso.LAST LOOP
                               vnMetaPedido := CEIL(vnObjetivoPedidoTotal + ( (vnObjetivoPedidoTotal * nivelConcurso(k).porcentajeInc) / 100 ));

                               UPDATE LET_METAS_LIDER_CONCU
                               SET MET_PEDI = vnMetaPedido,
                                   USU_MODI = psCodigoUsuario,
                                   FEC_MODI = SYSDATE
                               WHERE PAIS_COD_PAIS = psCodigoPais
                               AND CONC_COD_CONC = concurso(i).COD_CONC
                               AND NICO_NIVE_CONC = nivelConcurso(k).codigoNivelConcurso
                               AND COD_LIDE = lideresCampana(j).COD_LIDE;
                             END LOOP;
                          END IF;
                        EXIT WHEN C_NIVE_CONC%NOTFOUND;
                      END LOOP;
                      CLOSE C_NIVE_CONC;
                     END LOOP;
                   END IF;
                   EXIT WHEN C_LIDERES_CAMPANA%NOTFOUND;
                 END LOOP;
                 CLOSE C_LIDERES_CAMPANA;
             END LOOP;
         END IF;
     EXIT WHEN C_CONCURSOS%NOTFOUND;
     END LOOP;
     CLOSE C_CONCURSOS;
   EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_METAS_LIDER_CONCU: ' || Ls_Sqlerrm);
   END LET_PR_CALCU_METAS_LIDER_CONCU;

   /***********************************************************************************************
    Descripcion       : Realiza el resultado de secciones por campa�a al cierre de campa�a
    Fecha Creacion    : 11/03/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_RESUL_SECCI_CAMPA_CIECA(psCodigoPais VARCHAR2,
                                            psCodigoMarca VARCHAR2,
                                            psCodigoCanal VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoUsuario VARCHAR2) IS

     -- CURSORES ------------
     CURSOR C_ZONAS (
       oidPeriodo CRA_PERIO.PERI_OID_PERI%TYPE
     ) IS
       SELECT    ZZ.COD_ZONA,
                 ZR.COD_REGI
       FROM      ZON_ZONA ZZ,
                 ZON_REGIO ZR
       WHERE     ZZ.ZORG_OID_REGI = ZR.OID_REGI
         AND     (
                   oidPeriodo >= ZZ.PERD_OID_PERI_INIC OR
                   ZZ.PERD_OID_PERI_INIC IS NULL
                 )
         AND     (
                   oidPeriodo <= ZZ.PERD_OID_PERI_FINA OR
                   ZZ.PERD_OID_PERI_FINA IS NULL
                 );
     TYPE zonasRecord IS RECORD (
       codigoZona ZON_ZONA.COD_ZONA%TYPE,
       codigoRegion ZON_REGIO.COD_REGI%TYPE
     );
     TYPE zonasTab IS TABLE OF zonasRecord;
     zonas zonasTab;

     CURSOR C_SECCIONES (
       oidPeriodo CRA_PERIO.PERI_OID_PERI%TYPE,
       codigoZona ZON_ZONA.COD_ZONA%TYPE
     ) IS
       SELECT    COD_SECC
       FROM      ZON_SECCI ZS,
                 ZON_ZONA ZZ
       WHERE     ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
         AND     (
                   oidPeriodo >= ZZ.PERD_OID_PERI_INIC OR
                   ZZ.PERD_OID_PERI_INIC IS NULL
                 )
         AND     (
                   oidPeriodo <= ZZ.PERD_OID_PERI_FINA OR
                   ZZ.PERD_OID_PERI_FINA IS NULL
                 )
         AND     (
                   oidPeriodo >= ZS.PERD_OID_PERI_INIC OR
                   ZS.PERD_OID_PERI_INIC IS NULL
                 )
         AND     (
                   oidPeriodo <= ZS.PERD_OID_PERI_FINA OR
                   ZS.PERD_OID_PERI_FINA IS NULL
                 )
         AND     ZZ.COD_ZONA = codigoZona;
     TYPE seccionesRecord IS RECORD (
       codigoSeccion ZON_SECCI.COD_SECC%TYPE
     );
     TYPE seccionesTab IS TABLE OF seccionesRecord;
     secciones seccionesTab;

     CURSOR C_RECOMENDADAS_PERI_ANTERIOR(oidLider MAE_CLIEN.OID_CLIE%TYPE,oidPeriodoAnterior CRA_PERIO.OID_PERI%TYPE
     ) IS
     SELECT    MV.CLIE_OID_CLIE_VNDO
     FROM      MAE_CLIEN_VINCU MV,
               MAE_CLIEN_PRIME_CONTA MC
     WHERE     MV.CLIE_OID_CLIE_VNTE = oidLider
       AND     MV.TIVC_OID_TIPO_VINC = 9
       AND     MV.CLIE_OID_CLIE_VNDO = MC.CLIE_OID_CLIE
       AND     MC.PERD_OID_PERI = oidPeriodoAnterior;

     TYPE recomendadasPeriAnteriorTab IS TABLE OF MAE_CLIEN_VINCU.CLIE_OID_CLIE_VNDO%TYPE;
     recomendadasPeriAnterior recomendadasPeriAnteriorTab;

     CURSOR C_SOLICITUDES (
       oidPeriodo CRA_PERIO.PERI_OID_PERI%TYPE,
       oidLider MAE_CLIEN.OID_CLIE%TYPE
     ) IS
       SELECT    OID_SOLI_CABE
       FROM      PED_SOLIC_CABEC PS,
                 PED_TIPO_SOLIC_PAIS TP,
                 PED_TIPO_SOLIC TS
       WHERE     PS.TSPA_OID_TIPO_SOLI_PAIS = TP.OID_TIPO_SOLI_PAIS
         AND     TP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
         AND     PS.FEC_FACT IS NOT NULL
         AND     PS.IND_TS_NO_CONSO = 1
         AND     PS.IND_OC = 1
         AND     PS.IND_PEDI_PRUE = 0
         AND     TS.IND_DEVO = 0
         AND     TS.IND_ANUL = 0
         AND     PS.PERD_OID_PERI = oidPeriodo
         AND     PS.CLIE_OID_CLIE = oidLider;
     TYPE listaSolicitudRecord IS RECORD (
       oidSoliCabe PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE
     );
     TYPE listaSolicitudTab IS TABLE OF listaSolicitudRecord;
     listaSolicitud listaSolicitudTab;

     -- VARIABLES -----------
     e_cierreRegion         EXCEPTION;
     vsFlagExiste           VARCHAR2(6) := 0;
     vsCodigoConcurso       LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;
     vsOidPeriodo           CRA_PERIO.PERI_OID_PERI%TYPE;
     vsCodLider             VARCHAR2(15);
     vnOidPais              SEG_PAIS.OID_PAIS%TYPE;
     vnNumPedido            INT_FUENT_VENTA_REAL_VACUM.NUM_PEDI%TYPE;
     vnNumActIni            INT_FUENT_VENTAS_REAL.NUM_ACTI_INIC%TYPE;
     vnNumActFin            INT_FUENT_VENTAS_REAL.NUM_ACTI_FINA%TYPE;
     vnNumIngresos          INT_FUENT_VENTAS_REAL.NUM_INGR%TYPE;
     vnNumReingresos        INT_FUENT_VENTAS_REAL.NUM_REIN%TYPE;
     vnNumEgresos           INT_FUENT_VENTAS_REAL.NUM_EGRE%TYPE;
     vnOidMarca             SEG_MARCA.OID_MARC%TYPE;
     vnOidCanal             SEG_CANAL.OID_CANA%TYPE;
     vsCampAnterior         SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnOidCampProceso       CRA_PERIO.PERI_OID_PERI%TYPE;
     vnRegistroCampAnterior NUMBER(6);
     vnRegistroCampProceso  NUMBER(6);
     vnRecoIndEfe           LET_RECOM_LIDER.IND_EFEC%TYPE;
     vnPedidoNoAnulado      NUMBER(6) := 0;
     vnOidLider             MAE_CLIEN.OID_CLIE%TYPE;
     vnMontoPedido          LET_RESUL_SECCI_CAMPA.MON_PEDI_LIDE%TYPE;
     vnMontoPedidoTemp      LET_RESUL_SECCI_CAMPA.MON_PEDI_LIDE%TYPE;
     vnParaMtoMin           LET_PARAM_CONCU_LIDER.MON_MINI_PEDI%TYPE;
     vnIndMtoPed            LET_RESUL_SECCI_CAMPA.IND_MONT_PEDI%TYPE;
     vnRangoPremiacion      LET_PARAM_RANGO_PREMI.RANG_NUM_RANG%TYPE;
     --vnNivelCampaA          LET_METAS_LIDER_CAMPA.NICA_NIVE_CAMP%TYPE;
     --vnNivelCampaB          LET_PARAM_NIVEL_CAMPA.NICA_NIVE_CAMP%TYPE;
     vnNivelCampaFinal      LET_PARAM_NIVEL_CAMPA.NICA_NIVE_CAMP%TYPE;
     vnIndEfectivo          LET_RESUL_SECCI_CAMPA.IND_EFEC%TYPE;
     vsStatus               LET_RESUL_SECCI_CAMPA.VAL_ESTA%TYPE;
     vnParaNivMin           LET_PARAM_CONCU_LIDER.NIV_MINI%TYPE;
     vnPuntajeRanking       LET_PARAM_NIVEL_CAMPA.PUN_RANK%TYPE;
     vnRecoEfectivas        LET_RESUL_SECCI_CAMPA.REC_EFEC%TYPE := 0;
     vnOidZona              ZON_ZONA.OID_ZONA%TYPE;
     vnCodSubgerencia       ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE;
     vsUA                   ZON_HISTO_GEREN.UA%TYPE;
     vnOidCampAnterior      CRA_PERIO.PERI_OID_PERI%TYPE;
     vsValParametro         BAS_PARAM_PAIS.VAL_PARA%TYPE;
     vsCodZonaLider         ZON_ZONA.COD_ZONA%TYPE;
     vsCodigoZonaVinculado  ZON_ZONA.COD_ZONA%TYPE;
     vsCodReco              MAE_CLIEN.COD_CLIE%TYPE;
     /* INI JJ PER-SiCC-2012-0201 */
     vnEncontroClasificacionLider NUMBER;
     e_clasificacionLider         EXCEPTION;
     vnPorcentajeActividadMeta LET_PARAM_CONCU_LIDER.POR_ACTI_META%TYPE;
     vnFinalesExijidasSeccion NUMBER;
     vnPremio NUMBER;
     vsCodigoClasificacion    LET_HISTO_CLASI_LIDER.COD_CLAS_LIDE%TYPE;
     vsCodigoSubClasificacion LET_HISTO_CLASI_LIDER.COD_SUB_CLAS_LIDE%TYPE;
     vnNivelMinimaEstablecida LET_PARAM_CONCU_LIDER.NIV_MINI_ESTA%TYPE;
     vnCantidadCampanias NUMBER;
     vnSubClasificacionMinimaNuevas LET_PARAM_CONCU_LIDER.SBC_MINI_NUEV%TYPE;
     vnNumeroIngresosNuevas LET_PARAM_CONCU_LIDER.NUM_INGR_NUEV%TYPE;
     vnCantidadPedidos LET_PARAM_RANGO_PREMI.CAN_PEDI%TYPE;
     vnNumeroCampMinEstablecidas LET_PARAM_CONCU_LIDER.NUM_CAMP_MINI_ESTA%TYPE;
     /* FIN JJ PER-SiCC-2012-0201 */

     vnPorcPediObli         LET_PARAM_CONCU_LIDER.POR_PEDI_OBLI%TYPE;
     vnObjPediObli          LET_METAS_LIDER_CAMPA.MET_PEDI%TYPE;
     vnMiniCampClasEsta     LET_PARAM_CONCU_LIDER.NIV_MINI_CAMP_CLAS_ESTA%TYPE;
     vnPedidosObli          NUMBER;
     vnNumCampMaxiEsta      LET_PARAM_CONCU_LIDER.NUM_CAMP_MAXI_ESTA%TYPE;
     vsCamInicEvalEsta      LET_PARAM_CONCU_LIDER.CAM_INIC_EVAL_ESTA%TYPE;
     vnDivisionStatusS      NUMBER;
   BEGIN

     -- 0 Variables globales
     vsOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
     vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
     vnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
     vnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
     vsCampAnterior := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-1);
     vnOidCampProceso := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, vnOidMarca, vnOidCanal);
     vnOidCampAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(vsCampAnterior, vnOidMarca, vnOidCanal);
     -- 1 Validar si existe el concurso
     SELECT    COUNT(1)
       INTO    vsFlagExiste
     FROM      LET_PARAM_CONCU_LIDER
     WHERE     PAIS_COD_PAIS = psCodigoPais
       AND     psCodigoPeriodo >= CAM_INIC
       AND     psCodigoPeriodo <= CAM_FINA
       AND     EST_REGI = 1;

     IF vsFlagExiste = 1 THEN

       -- 1.1 Capturar codigo concurso
       SELECT    COD_CONC,POR_ACTI_META,NIV_MINI_ESTA,SBC_MINI_NUEV,
                 NUM_INGR_NUEV,NUM_CAMP_MINI_ESTA,POR_PEDI_OBLI
         INTO    vsCodigoConcurso,vnPorcentajeActividadMeta,vnNivelMinimaEstablecida,vnSubClasificacionMinimaNuevas,
                 vnNumeroIngresosNuevas,vnNumeroCampMinEstablecidas,vnPorcPediObli
       FROM      LET_PARAM_CONCU_LIDER
       WHERE     PAIS_COD_PAIS = psCodigoPais
         AND     psCodigoPeriodo >= CAM_INIC
         AND     psCodigoPeriodo <= CAM_FINA
         AND     EST_REGI = 1;

       -- 1.2 Validar si ha ejecutado el proceso
       vsFlagExiste := 0;

       SELECT    COUNT(1)
         INTO    vsFlagExiste
       FROM      LET_RESUL_SECCI_CAMPA
       WHERE     PAIS_COD_PAIS = psCodigoPais
         AND     LIDE_CAM_LIDE = psCodigoPeriodo
         AND     CONC_COD_CONC = vsCodigoConcurso
         AND     IND_PREM = 1;

       IF vsFlagExiste > 0 THEN
         RAISE e_cierreRegion;
       ELSE

       -- Validar que la recomendada pertenesca a la zona
       select VAL_PARA
       into  vsValParametro
       from BAS_PARAM_PAIS
       where COD_PAIS = psCodigoPais
       and   COD_SIST = 'LET'
       and   COD_PARA = '004';

       DELETE FROM LET_RESUL_SECCI_CAMPA
       WHERE PAIS_COD_PAIS = psCodigoPais
       AND   CONC_COD_CONC = vsCodigoConcurso
       AND   LIDE_CAM_LIDE = psCodigoPeriodo;

       DELETE FROM LET_RECOM_LIDER
       WHERE PAIS_COD_PAIS = psCodigoPais
       AND LIDE_CAM_LIDE = psCodigoPeriodo;

       DELETE FROM LET_LIDER_CAMPA
       WHERE PAIS_COD_PAIS = psCodigoPais
       AND CAM_LIDE = psCodigoPeriodo;

        -- 1.2.1 y 1.2.2 Obtener zonas activas
         OPEN C_ZONAS(
           vsOidPeriodo
         );
         LOOP

           FETCH C_ZONAS BULK COLLECT INTO zonas LIMIT W_FILAS;
           IF zonas.COUNT > 0 THEN

             -- Recorrer el Cursor Paginado de ZONAS
             FOR i IN zonas.FIRST .. zonas.LAST LOOP

               -- 1 Obtener las secciones x cada zona obtenida
               OPEN C_SECCIONES (
                 vsOidPeriodo,
                 zonas(i).codigoZona
               );
               LOOP

                 FETCH C_SECCIONES BULK COLLECT INTO secciones LIMIT W_FILAS;
                 IF secciones.COUNT > 0 THEN

                   -- Recorrer el Cursor Paginado de SECCIONES
                   FOR j IN secciones.FIRST .. secciones.LAST LOOP

                     -- 1.1 Obtener codigoLider
                     SELECT    ZS.COD_SUBG_VENT
                       INTO    vnCodSubgerencia
                     FROM      ZON_REGIO ZR,
                               ZON_SUB_GEREN_VENTA ZS
                     WHERE     ZR.ZSGV_OID_SUBG_VENT = ZS.OID_SUBG_VENT
                       AND     ZR.COD_REGI = zonas(i).codigoRegion;

                     vsUA := ( vnCodSubgerencia || zonas(i).codigoRegion || zonas(i).codigoZona || secciones(j).codigoSeccion );

                     BEGIN
                       SELECT    GERE
                         INTO    vsCodLider
                       FROM      ZON_HISTO_GEREN
                       WHERE     UA = vsUA
                         AND     vsOidPeriodo >= PERD_OID_PERI_DESD
                         AND     (
                                   vsOidPeriodo <= PERD_OID_PERI_HAST OR
                                   PERD_OID_PERI_HAST IS NULL
                                 );
                     EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                         vsCodLider := NULL;
                     END;

                     IF vsCodLider IS NOT NULL THEN

                       -- 1.1.0 Variables
                       vnOidLider := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(vsCodLider);
                       vnOidZona := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_ZONA(psCodigoPais, psCodigoMarca, psCodigoCanal, zonas(i).codigoRegion, zonas(i).codigoZona);


                       -- 1.1.1 Obtener la suma numeroPedidos
                       SELECT    SUM(FV.NUM_PEDI)
                         INTO    vnNumPedido
                       FROM      ZON_TERRI_ADMIN ZT,
                                 ZON_SECCI ZS,
                                 INT_FUENT_VENTA_REAL_VACUM FV
                       WHERE     ZT.ZSCC_OID_SECC = ZS.OID_SECC
                         AND     FV.TERR_OID_TERR = ZT.TERR_OID_TERR
                         AND     (
                                   vsOidPeriodo >= ZT.PERD_OID_PERI_INIC OR
                                   ZT.PERD_OID_PERI_INIC IS NULL
                                 )
                         AND     (
                                   vsOidPeriodo <= ZT.PERD_OID_PERI_FINA OR
                                   ZT.PERD_OID_PERI_FINA IS NULL
                                 )
                         AND     (
                                   vsOidPeriodo >= ZS.PERD_OID_PERI_INIC OR
                                   ZS.PERD_OID_PERI_INIC IS NULL
                                 )
                         AND     (
                                   vsOidPeriodo <= ZS.PERD_OID_PERI_FINA OR
                                   ZS.PERD_OID_PERI_FINA IS NULL
                                 )
                         AND     FV.PERD_OID_PERI = vsOidPeriodo
                         AND     ZS.ZZON_OID_ZONA = vnOidZona
                         AND     ZS.COD_SECC = secciones(j).codigoSeccion;

                       IF vnNumPedido IS NULL THEN
                         vnNumPedido := 0;
                       END IF;

                       -- 1.1.2 obtener la suma activas_iniciales,activas_finales,
                       --       ingresos,reingresos,egresos
                       SELECT    SUM(FR.NUM_ACTI_INIC),
                                 SUM(FR.NUM_ACTI_FINA),
                                 SUM(FR.NUM_INGR),
                                 SUM(FR.NUM_REIN),
                                 SUM(FR.NUM_EGRE)
                         INTO    vnNumActIni,
                                 vnNumActFin,
                                 vnNumIngresos,
                                 vnNumReingresos,
                                 vnNumEgresos
                       FROM      ZON_TERRI_ADMIN ZT,
                                 ZON_SECCI ZS,
                                 INT_FUENT_VENTAS_REAL FR
                       WHERE     ZT.ZSCC_OID_SECC = ZS.OID_SECC
                         AND     FR.TERR_OID_TERR = ZT.TERR_OID_TERR
                         AND     (
                                   vsOidPeriodo >= ZT.PERD_OID_PERI_INIC OR
                                   ZT.PERD_OID_PERI_INIC IS NULL
                                 )
                         AND     (
                                   vsOidPeriodo <= ZT.PERD_OID_PERI_FINA OR
                                   ZT.PERD_OID_PERI_FINA IS NULL
                                 )
                         AND     (
                                   vsOidPeriodo >= ZS.PERD_OID_PERI_INIC OR
                                   ZS.PERD_OID_PERI_INIC IS NULL
                                 )
                         AND     (
                                   vsOidPeriodo <= ZS.PERD_OID_PERI_FINA OR
                                   ZS.PERD_OID_PERI_FINA IS NULL
                                 )
                         AND     FR.PERD_OID_PERI = vsOidPeriodo
                         AND     ZS.ZZON_OID_ZONA = vnOidZona
                         AND     ZS.COD_SECC = secciones(j).codigoSeccion;

                       IF vnNumActIni IS NULL THEN
                           vnNumActIni := 0;
                           vnNumActFin := 0;
                           vnNumIngresos := 0;
                           vnNumReingresos := 0;
                           vnNumEgresos := 0;
                       END IF;

                       INSERT INTO LET_LIDER_CAMPA (
                         PAIS_COD_PAIS,
                         CAM_LIDE,
                         COD_LIDE,
                         COD_REGI,
                         COD_ZONA,
                         COD_SECC,
                         USU_MODI,
                         FEC_MODI,
                         EST_REGI
                       ) VALUES (
                         psCodigoPais,
                         psCodigoPeriodo,
                         vsCodLider,
                         zonas(i).codigoRegion,
                         zonas(i).codigoZona,
                         secciones(j).codigoSeccion,
                         psCodigoUsuario,
                         SYSDATE,
                         '1'
                       );

                       /* INI JJ PER-SiCC-2012-0201 */
                       SELECT COUNT(1)
                       INTO vnEncontroClasificacionLider
                       FROM LET_HISTO_CLASI_LIDER
                       WHERE PAIS_COD_PAIS = psCodigoPais
                       AND CAM_CLAS = psCodigoPeriodo
                       AND COD_LIDE = vsCodLider;

                       IF vnEncontroClasificacionLider = 0 THEN
                         RAISE e_clasificacionLider;
                       END IF;

                       select CAN_PEDI
                       into vnCantidadPedidos
                       from let_param_rango_premi
                       where pais_cod_pais = psCodigoPais
                       and conc_cod_conc = vsCodigoConcurso
                       and rang_num_rang = 1;

                       vnFinalesExijidasSeccion := FLOOR(vnCantidadPedidos / (vnPorcentajeActividadMeta / 100));

                       /* FIN JJ PER-SiCC-2012-0201 */

                       -- 1.1.3 Obtener lista de recomendadas

                       vnRecoEfectivas := 0;

                       -- oidLider
                       vnOidLider := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(vsCodLider);

                       BEGIN
                         SELECT SUBSTR(UA,5,4) as COD_ZONA
                         INTO vsCodZonaLider
                         FROM ZON_HISTO_GEREN
                         WHERE PAIS_OID_PAIS = vnOidPais
                         AND GERE = vsCodLider
                         AND LENGTH(UA) = 9
                         AND vnOidCampAnterior >= PERD_OID_PERI_DESD
                         AND (vnOidCampAnterior <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST is null);
                       EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                         vsCodZonaLider := NULL;
                       END;

                       OPEN C_RECOMENDADAS_PERI_ANTERIOR(vnOidLider,vnOidCampAnterior);

                       LOOP
                           FETCH C_RECOMENDADAS_PERI_ANTERIOR BULK COLLECT INTO recomendadasPeriAnterior LIMIT W_FILAS;

                           IF recomendadasPeriAnterior.COUNT > 0 THEN
                              FOR k IN recomendadasPeriAnterior.FIRST .. recomendadasPeriAnterior.LAST LOOP

                                   vsCodReco := Gen_Pkg_Gener.GEN_FN_DEVUELVE_COD_CLIE(recomendadasPeriAnterior(k));

                                   -- Validar que las recomendaciones hayan pasado pedido en la campa�a anterior
                                   SELECT    COUNT(1)
                                     INTO    vnRegistroCampAnterior
                                   FROM      PED_SOLIC_CABEC PS,
                                             PED_TIPO_SOLIC_PAIS TP,
                                             PED_TIPO_SOLIC TS
                                   WHERE     PS.TSPA_OID_TIPO_SOLI_PAIS = TP.OID_TIPO_SOLI_PAIS
                                     AND     TP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                                     AND     PS.FEC_FACT IS NOT NULL
                                     AND     PS.IND_TS_NO_CONSO = 1
                                     AND     PS.IND_OC = 1
                                     AND     PS.IND_PEDI_PRUE = 0
                                     AND     TS.IND_DEVO = 0
                                     AND     TS.IND_ANUL = 0
                                     AND     PS.PERD_OID_PERI = vnOidCampAnterior
                                     AND     PS.CLIE_OID_CLIE = recomendadasPeriAnterior(k);

                                   -- Validar que las recomendaciones hayan pasado pedido en la campa�a de proceso
                                   SELECT    COUNT(1)
                                     INTO    vnRegistroCampProceso
                                   FROM      PED_SOLIC_CABEC PS,
                                             PED_TIPO_SOLIC_PAIS TP,
                                             PED_TIPO_SOLIC TS
                                   WHERE     PS.TSPA_OID_TIPO_SOLI_PAIS = TP.OID_TIPO_SOLI_PAIS
                                     AND     TP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                                     AND     PS.FEC_FACT IS NOT NULL
                                     AND     PS.IND_TS_NO_CONSO = 1
                                     AND     PS.IND_OC = 1
                                     AND     PS.IND_PEDI_PRUE = 0
                                     AND     TS.IND_DEVO = 0
                                     AND     TS.IND_ANUL = 0
                                     AND     PS.PERD_OID_PERI = vnOidCampProceso
                                     AND     PS.CLIE_OID_CLIE = recomendadasPeriAnterior(k);

                                     -- 5 Actualizar Indicador Efectiva en LET_RECOM_LIDER
                                     IF vnRegistroCampAnterior > 0 AND  vnRegistroCampProceso > 0 THEN
                                         vnRecoIndEfe := 1;
                                         vnRecoEfectivas := vnRecoEfectivas + 1;
                                     ELSE
                                         vnRecoIndEfe := 2;
                                     END IF;
                                     vsCodigoZonaVinculado := null;
                                     IF vsValParametro = 1 AND vsCodZonaLider IS NOT NULL THEN
                                           IF  vnRegistroCampAnterior > 0 THEN
                                             ------  DM agrega este IF para buscar s�lo si recomend pas� pedido en camp.anterior
                                        SELECT d.COD_ZONA
                                        INTO vsCodigoZonaVinculado
                                        FROM MAE_CLIEN_UNIDA_ADMIN a,
                                             ZON_TERRI_ADMIN b,
                                             ZON_SECCI c,
                                             ZON_ZONA d
                                        WHERE a.ZTAD_OID_TERR_ADMI = b.OID_TERR_ADMI
                                        AND b.ZSCC_OID_SECC = c.OID_SECC
                                        AND c.ZZON_OID_ZONA = d.OID_ZONA
                                        AND a.CLIE_OID_CLIE = recomendadasPeriAnterior(k)
                                        AND vnOidCampAnterior >= a.PERD_OID_PERI_INI
                                        AND (vnOidCampAnterior <= a.PERD_OID_PERI_FIN OR a.PERD_OID_PERI_FIN IS NULL);
                                           END IF;
                                        IF vsCodigoZonaVinculado = vsCodZonaLider THEN
                                           INSERT INTO LET_RECOM_LIDER(
                                             PAIS_COD_PAIS,
                                             LIDE_CAM_LIDE,
                                             LIDE_COD_LIDE,
                                             COD_RECO,
                                             IND_EFEC,
                                             IND_NCON,
                                             CAM_EVAL,
                                             USU_MODI,
                                             FEC_MODI,
                                             EST_REGI
                                           )
                                           VALUES(
                                             psCodigoPais,
                                             psCodigoPeriodo,
                                             vsCodLider,
                                             vsCodReco,
                                             vnRecoIndEfe,
                                             0,
                                             psCodigoPeriodo,
                                             psCodigoUsuario,
                                             SYSDATE,
                                             1
                                           );
                                        END IF;
                                     ELSE
                                        INSERT INTO LET_RECOM_LIDER(
                                             PAIS_COD_PAIS,
                                             LIDE_CAM_LIDE,
                                             LIDE_COD_LIDE,
                                             COD_RECO,
                                             IND_EFEC,
                                             IND_NCON,
                                             CAM_EVAL,
                                             USU_MODI,
                                             FEC_MODI,
                                             EST_REGI
                                           )
                                           VALUES(
                                             psCodigoPais,
                                             psCodigoPeriodo,
                                             vsCodLider,
                                             vsCodReco,
                                             vnRecoIndEfe,
                                             0,
                                             psCodigoPeriodo,
                                             psCodigoUsuario,
                                             SYSDATE,
                                             1
                                           );
                                     END IF;

                              END LOOP;
                           END IF;

                       EXIT WHEN C_RECOMENDADAS_PERI_ANTERIOR%NOTFOUND;
                       END LOOP;
                       CLOSE C_RECOMENDADAS_PERI_ANTERIOR;

                       -- 1.1.4 Obtener monto_pedido ----------------
                       vnMontoPedido := 0;
                       vnMontoPedidoTemp := 0;
                       OPEN C_SOLICITUDES(
                         vsOidPeriodo,
                         vnOidLider
                       );
                       LOOP

                         FETCH C_SOLICITUDES BULK COLLECT INTO listaSolicitud LIMIT W_FILAS;
                         IF listaSolicitud.COUNT > 0 THEN

                           -- Recorrer el Cursor Paginado de SOLICITUDES
                           FOR k IN listaSolicitud.FIRST .. listaSolicitud.LAST LOOP

                             -- 1 Hallar su consolidado validando SI NO es anulado
                             SELECT    COUNT(1)
                               INTO    vnPedidoNoAnulado
                             FROM      PED_SOLIC_CABEC PS,
                                       PED_TIPO_SOLIC_PAIS TP,
                                       PED_TIPO_SOLIC TS,
                             					 PED_SOLIC_CABEC CONS
                             WHERE     PS.TSPA_OID_TIPO_SOLI_PAIS = TP.OID_TIPO_SOLI_PAIS
                               AND     TP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                               AND 		 PS.SOCA_OID_SOLI_CABE = CONS.OID_SOLI_CABE
                               AND     PS.FEC_FACT IS NOT NULL
                               AND     PS.IND_TS_NO_CONSO = 1
                               AND     PS.IND_OC = 1
                               AND     PS.IND_PEDI_PRUE = 0
                               AND     TS.IND_DEVO = 0
                               AND     TS.IND_ANUL = 0
                               AND 		 CONS.ESSO_OID_ESTA_SOLI <> 4
                               AND     PS.PERD_OID_PERI = vsOidPeriodo
                               AND     PS.CLIE_OID_CLIE = vnOidLider;

                             -- 2 Si el pedido no es anulado. Sumar monto_pedido
                             IF vnPedidoNoAnulado > 0 THEN
                               SELECT    SUM( NVL(PP.NUM_UNID_POR_ATEN,0) * NVL(PP.VAL_PREC_CATA_UNIT_LOCA,0) )
                                 INTO    vnMontoPedidoTemp
                               FROM      PED_SOLIC_CABEC PS,
                                         PED_SOLIC_POSIC PP
                               WHERE     PS.OID_SOLI_CABE = PP.SOCA_OID_SOLI_CABE
                                 AND     PP.ESPO_OID_ESTA_POSI <> 2
                                 AND     PS.PERD_OID_PERI = vsOidPeriodo
                                 AND     PS.CLIE_OID_CLIE = vnOidLider
                                 AND     PS.OID_SOLI_CABE = listaSolicitud(k).oidSoliCabe;

                               vnMontoPedido := vnMontoPedido + vnMontoPedidoTemp;
                             END IF;

                           END LOOP; -- Fin del Cursor Paginado de SOLICITUDES

                         END IF; -- listaSolicitud.COUNT > 0

                         EXIT WHEN C_SOLICITUDES%NOTFOUND;
                       END LOOP;
                       CLOSE C_SOLICITUDES; -- 1.1.4 Obtener monto_pedido ----------------

                       -- 1.1.5 Obtener indicador_mto_pedido --------
                       SELECT    MON_MINI_PEDI
                         INTO    vnParaMtoMin
                       FROM      LET_PARAM_CONCU_LIDER
                       WHERE     PAIS_COD_PAIS = psCodigoPais
                         AND     COD_CONC = vsCodigoConcurso;

                       IF vnMontoPedido >= vnParaMtoMin THEN
                         vnIndMtoPed := 1;
                       ELSE
                         vnIndMtoPed := 2;
                       END IF;

                       -- 1.1.6 Obtener Rango Premiacion ------------
                       BEGIN
                         SELECT    NVL(MAX(RANG_NUM_RANG),0)
                           INTO    vnRangoPremiacion
                         FROM      LET_PARAM_RANGO_PREMI
                         WHERE     PAIS_COD_PAIS = psCodigoPais
                           AND     CONC_COD_CONC = vsCodigoConcurso
                         HAVING    MAX(CAN_PEDI) < vnNumPedido;
                       EXCEPTION
                         WHEN NO_DATA_FOUND THEN
                           vnRangoPremiacion := 0;
                       END;

                       IF vnRangoPremiacion = 0 THEN
                         SELECT    MAX(RANG_NUM_RANG)
                           INTO    vnRangoPremiacion
                         FROM      LET_PARAM_RANGO_PREMI
                         WHERE     PAIS_COD_PAIS = psCodigoPais
                           AND     CONC_COD_CONC = vsCodigoConcurso
                           AND     vnNumPedido >= CAN_PEDI;

                         IF vnRangoPremiacion IS NULL THEN
                           vnRangoPremiacion := 0;
                         END IF;

                       END IF;

                       -- 1.1.7 Obtener Nivel Campa�a ---------------
                       BEGIN
                        select NVL(max(a.nica_nive_camp),0)
                        into vnNivelCampaFinal
                        from LET_METAS_LIDER_CAMPA a,
                             LET_PARAM_NIVEL_CAMPA b
                        where a.pais_cod_pais = b.pais_cod_pais
                        and a.conc_cod_conc = b.conc_cod_conc
                        and a.nica_nive_camp = b.nica_nive_camp
                        and a.pais_cod_pais = psCodigoPais
                        and a.conc_cod_conc = vsCodigoConcurso
                        and a.cam_eval = psCodigoPeriodo
                        and a.cod_regi = zonas(i).codigoRegion
                        and a.cod_zona = zonas(i).codigoZona
                        and a.cod_secc = secciones(j).codigoSeccion
                        and a.met_pedi <= vnNumPedido
                        and b.met_segu_pedi <= vnRecoEfectivas;

                       EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                         vnNivelCampaFinal := 0;
                       END;

                       /* INI JJ PER-SiCC-2012-0201 */
                       IF vnRangoPremiacion > 0 and vnNivelCampaFinal > 0 THEN
                         vnPremio := 1;
                       END IF;

                       SELECT COD_CLAS_LIDE,COD_SUB_CLAS_LIDE
                       INTO vsCodigoClasificacion,vsCodigoSubClasificacion
                       FROM LET_HISTO_CLASI_LIDER
                       WHERE PAIS_COD_PAIS = psCodigoPais
                       AND CAM_CLAS = psCodigoPeriodo
                       AND COD_LIDE = vsCodLider;

                       IF vsCodigoClasificacion = '02' OR vsCodigoClasificacion = '03' THEN
                         IF vnNivelCampaFinal > 0 AND vnNivelCampaFinal <= vnNivelMinimaEstablecida THEN
                           SELECT COUNT(1)
                           INTO vnCantidadCampanias
                           FROM LET_RESUL_SECCI_CAMPA
                           WHERE PAIS_COD_PAIS = psCodigoPais
                           AND LIDE_COD_LIDE = vsCodLider
                           AND CONC_COD_CONC = vsCodigoConcurso
                           AND LIDE_CAM_LIDE < psCodigoPeriodo
                           AND (NIV_CAMP > 0 AND NIV_CAMP <= vnNivelMinimaEstablecida);

                           IF vnCantidadCampanias >= vnNumeroCampMinEstablecidas THEN
                              vnPremio := 0;
                           END IF;
                         END IF;
                       END IF;

                       IF vnNivelCampaFinal = 0 and vnRangoPremiacion > 0 THEN
                          IF vsCodigoClasificacion = '01' AND vsCodigoSubClasificacion <= vnSubClasificacionMinimaNuevas THEN
                            IF vnRecoEfectivas >= vnNumeroIngresosNuevas THEN
                              vnNivelCampaFinal := 99;
                              vnPremio := 1;
                            END IF;
                          END IF;
                       END IF;

                       --Obtenemos el objetivo de pedido de la campa�a
                       BEGIN
                         SELECT MET_PEDI
                           INTO vnObjPediObli
                           FROM LET_METAS_LIDER_CAMPA a
                          WHERE a.nica_nive_camp = vnNivelCampaFinal
                            AND a.pais_cod_pais = psCodigoPais
                            AND a.conc_cod_conc = vsCodigoConcurso
                            AND a.cam_eval = psCodigoPeriodo
                            AND a.cod_regi = zonas(i).codigoRegion
                            AND a.cod_zona = zonas(i).codigoZona
                            AND a.cod_secc = secciones(j).codigoSeccion;
                       EXCEPTION
                         WHEN NO_DATA_FOUND THEN
                           vnObjPediObli := 0;
                       END;

                       --Validacion si cumplio con el Porcentaje de Pedidos Obligatorio
                       vnPedidosObli := CEIL(vnObjPediObli * vnPorcPediObli);
                       IF(vnNumPedido < vnPedidosObli) THEN
                         vnRangoPremiacion := 0;
                         vnNivelCampaFinal := 0;
                       END IF;

                       IF vnRangoPremiacion = 0 OR vnNivelCampaFinal = 0 THEN
                          vnRangoPremiacion := 0;
                          vnNivelCampaFinal := 0;
                       END IF;

                        -- 1.1.8 Obtener indicador_efectivo ----------
                       IF vnIndMtoPed = 1 AND vnRangoPremiacion >= 1 AND vnNivelCampaFinal >= 1 AND vnPremio = 1 THEN
                         vnIndEfectivo := 1;
                       ELSE
                         vnIndEfectivo := 2;
                       END IF;

                       -- 1.1.9 Obtener Status ----------------------
                       SELECT    NIV_MINI, NIV_MINI_CAMP_CLAS_ESTA, CAM_INIC_EVAL_ESTA, NUM_CAMP_MAXI_ESTA
                         INTO    vnParaNivMin, vnMiniCampClasEsta, vsCamInicEvalEsta, vnNumCampMaxiEsta
                       FROM      LET_PARAM_CONCU_LIDER
                       WHERE     PAIS_COD_PAIS = psCodigoPais
                         AND     COD_CONC = vsCodigoConcurso;

                       --Obtener cantidad de campa�as con estatus de productividad igual a "S" (Estable)
                       SELECT COUNT(1)
                         INTO vnDivisionStatusS
                         FROM LET_RESUL_SECCI_CAMPA
                        WHERE PAIS_COD_PAIS = psCodigoPais
                          --AND CONC_COD_CONC = vsCodigoConcurso

                          AND LIDE_COD_LIDE = vsCodLider
                          AND LIDE_CAM_LIDE >= vsCamInicEvalEsta
                          AND LIDE_CAM_LIDE <= vsCampAnterior
                          AND VAL_ESTA = 'S';

                       -- Para que no tome los comodines
                       IF vnNivelCampaFinal >= 90 THEN
                          vsStatus := 'C'; --Critica
                       ELSE
                       IF vnNivelCampaFinal >= vnParaNivMin THEN
                         vsStatus := 'X'; --Exitosa
                       ELSE
                               IF ((vnMiniCampClasEsta IS NOT NULL) AND (vnDivisionStatusS < vnNumCampMaxiEsta)
                              AND (vnNivelCampaFinal >= vnMiniCampClasEsta)) THEN
                           vsStatus := 'S'; --Estable
                       ELSE
                           vsStatus := 'C'; --Critica
                       END IF;
                       END IF;
                       END IF;

                       /* FIN JJ PER-SiCC-2012-0201 */

                       -- 1.1.10 Obtener puntaje_ranking ------------
                       BEGIN
                         SELECT    PUN_RANK
                           INTO    vnPuntajeRanking
                         FROM      LET_PARAM_NIVEL_CAMPA
                         WHERE     PAIS_COD_PAIS = psCodigoPais
                           AND     CONC_COD_CONC = vsCodigoConcurso
                           AND     NICA_NIVE_CAMP = vnNivelCampaFinal;
                       EXCEPTION
                         WHEN NO_DATA_FOUND THEN
                           vnPuntajeRanking := 0;
                       END;

                       -- 1.1.12 Insertar en LET_RESUL_SECCI_CAMPA --
                       INSERT INTO LET_RESUL_SECCI_CAMPA (
                         PAIS_COD_PAIS,
                         CONC_COD_CONC,
                         LIDE_CAM_LIDE,
                         LIDE_COD_LIDE,
                         COD_REGI,
                         COD_ZONA,
                         COD_SECC,
                         ACT_INIC,
                         ACT_FINA,
                         NUM_INGR,
                         NUM_REIN,
                         NUM_EGRE,
                         REC_EFEC,
                         NUM_PEDI,
                         MON_PEDI_LIDE,
                         IND_MONT_PEDI,
                         RAN_PREM,
                         NIV_CAMP,
                         VAL_ESTA,
                         IND_PREM,
                         IND_EFEC,
                         PUN_RANK,
                         USU_MODI,
                         FEC_MODI,
                         EST_REGI,
                         NUM_FINA_EXIG
                       ) VALUES (
                         psCodigoPais,
                         vsCodigoConcurso,
                         psCodigoPeriodo,
                         vsCodLider,
                         zonas(i).codigoRegion,
                         zonas(i).codigoZona,
                         secciones(j).codigoSeccion,
                         vnNumActIni,
                         vnNumActFin,
                         vnNumIngresos,
                         vnNumReingresos,
                         vnNumEgresos,
                         vnRecoEfectivas,
                         vnNumPedido,
                         vnMontoPedido,
                         vnIndMtoPed,
                         vnRangoPremiacion,
                         vnNivelCampaFinal,
                         vsStatus,
                         0,
                         vnIndEfectivo,
                         vnPuntajeRanking,
                         psCodigoUsuario,
                         SYSDATE,
                         '1',
                         vnFinalesExijidasSeccion
                       );


                     END IF; -- 1.1 Obtener codigoLider

                   END LOOP; -- Fin del Cursor Paginado de SECCIONES

                 END IF; -- secciones.COUNT > 0

                 EXIT WHEN C_SECCIONES%NOTFOUND;
               END LOOP;
               CLOSE C_SECCIONES; -- 1 Obtener las secciones x cada zona obtenida

             END LOOP; -- Fin del Cursor Paginado de ZONAS

           END IF; -- zonas.COUNT > 0

           EXIT WHEN C_ZONAS%NOTFOUND;
         END LOOP;
         CLOSE C_ZONAS; -- 1.2.2 Obtener zonas activas

       END IF;  -- 1.2 Validar si ha ejecutado el proceso

     END IF; -- 1 Validar si existe el concurso

   EXCEPTION
     WHEN e_cierreRegion THEN
       RAISE_APPLICATION_ERROR(-20001, 'CAMPA�A NO VALIDA');
     /* INI JJ PER-SiCC-2012-0201 */
     WHEN e_clasificacionLider THEN
       RAISE_APPLICATION_ERROR(-20001, ' Lider '||vsCodLider || ' no tiene Clasificaci�n para la campa�a de proceso ');
     /* FIN JJ PER-SiCC-2012-0201 */
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_RESUL_SECCI_CAMPA_CIECA: ' || ls_sqlerrm);
   END LET_PR_RESUL_SECCI_CAMPA_CIECA;

   /**************************************************************************
    Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
    Fecha Creacion     : 16/03/2011

    Autor              : Jose Cairampoma
                         Jesse Rios
  ***************************************************************************/
  FUNCTION LET_FN_DEVUE_SECUE_NSOLI(psCodigoPais      VARCHAR2,
                                    psCodigoOperacion VARCHAR2,
                                    psCodigoAcceso    VARCHAR2,
                                    psCodigoSubacceso VARCHAR2,
                                    psCodigoCanal     VARCHAR2)
                                    RETURN NUMBER IS

    vnSolicitudInicio NUMBER;

  BEGIN

    SELECT ns.val_ulti_nume_soli
    INTO vnSolicitudInicio
    FROM ped_numer_solic ns
    WHERE ns.val_oper = psCodigoOperacion
    AND ns.val_anio = to_char(to_number(to_char(SYSDATE,'YY')))
    AND ns.cod_acce = psCodigoAcceso
    AND ns.cod_suba = psCodigoSubacceso
    AND ns.cod_pais = psCodigoPais
    AND ns.cod_cana = psCodigoCanal
    FOR UPDATE;

    RETURN vnSolicitudInicio;

  EXCEPTION
  WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,1,250);
      raise_application_error(-20123,'ERROR LET_FN_DEVUE_SECUE_NSOLI: ' || ls_sqlerrm);
  END LET_FN_DEVUE_SECUE_NSOLI;

  /**************************************************************************
   Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                         separndo n Numeros para inserciones.
   Fecha Creacion     : 16/03/2011

   Autor              : Jose Cairampoma
                        Jesse Rios
  ***************************************************************************/
  FUNCTION LET_FN_RESRV_SECUE_NSOLI(psCodigoPais      VARCHAR2,
                                    psCodigoOperacion VARCHAR2,
                                    psCodigoAcceso    VARCHAR2,
                                    psCodigoSubacceso VARCHAR2,
                                    psCodigoCanal     VARCHAR2,
                                    pnNumReservados   NUMBER)
                                    RETURN NUMBER IS

    PRAGMA AUTONOMOUS_TRANSACTION;

    vnNumSoliInicio      NUMBER;
    vnNumSoliFormatFinal NUMBER;

  BEGIN

    vnNumSoliInicio := LET_FN_DEVUE_SECUE_NSOLI(psCodigoPais,psCodigoOperacion,psCodigoAcceso,psCodigoSubacceso,psCodigoCanal);

    vnNumSoliFormatFinal := vnNumSoliInicio + pnNumReservados + 1;

    UPDATE ped_numer_solic ns
    SET ns.val_ulti_nume_soli = vnNumSoliFormatFinal
    WHERE ns.val_oper = psCodigoOperacion
    AND ns.val_anio = to_char(to_number(to_char(SYSDATE,'YY')))
    AND ns.cod_cana = psCodigoCanal
    AND ns.cod_acce = psCodigoAcceso
    AND ns.cod_suba = psCodigoSubacceso
    AND ns.cod_pais = psCodigoPais;

    COMMIT;

    RETURN vnNumSoliInicio;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,1,250);
      raise_application_error(-20123,'ERROR LET_FN_RESRV_SECUE_NSOLI: ' || ls_sqlerrm);

  END LET_FN_RESRV_SECUE_NSOLI;

  /***********************************************************************************************
   Descripcion       : Realiza la enrega de premios por campa�a al cierre de campa�a
   Fecha Creacion    : 17/03/2011
   Autor             : Jesse Rios
   ***********************************************************************************************/
   PROCEDURE LET_PR_ENTRE_PREMI_CAMPA_CIERR(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoMarca   VARCHAR2,
                                            psCodigoCanal   VARCHAR2)IS

   vsCodigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;

   CURSOR C_RESULTADO_SECCIONES_CAMPANA(codigoConcurso LET_PARAM_CONCU_LIDER.COD_CONC%TYPE) IS
   SELECT CONC_COD_CONC,LIDE_COD_LIDE,RAN_PREM,NIV_CAMP,COD_ZONA,LIDE_CAM_LIDE
   FROM LET_RESUL_SECCI_CAMPA
   WHERE PAIS_COD_PAIS = psCodigoPais
   AND CONC_COD_CONC = codigoConcurso
   AND LIDE_CAM_LIDE = psCodigoPeriodo
   AND IND_EFEC = 1
   AND IND_PREM = 0;

   TYPE resultadoSeccionesCampRecord IS RECORD(
        CONC_COD_CONC LET_RESUL_SECCI_CAMPA.CONC_COD_CONC%TYPE,
        COD_LIDE      LET_RESUL_SECCI_CAMPA.LIDE_COD_LIDE%TYPE,
        RAN_PREM      LET_RESUL_SECCI_CAMPA.RAN_PREM%TYPE,
        NIV_CAMP      LET_RESUL_SECCI_CAMPA.NIV_CAMP%TYPE,
        COD_ZONA      LET_RESUL_SECCI_CAMPA.COD_ZONA%TYPE,
        LIDE_CAM_LIDE LET_RESUL_SECCI_CAMPA.LIDE_CAM_LIDE%TYPE
   );

   TYPE resultadoSeccionesCampTab IS TABLE OF resultadoSeccionesCampRecord;
   resultadoSeccionesCamp resultadoSeccionesCampTab;

   vnOidPais       SEG_PAIS.OID_PAIS%TYPE;
   vnOidMarca      SEG_MARCA.OID_MARC%TYPE;
   vnOidCanal      SEG_CANAL.OID_CANA%TYPE;
   vsCampanaSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE;
   vnOidPeriodoSiguiente SEG_PERIO_CORPO.OID_PERI%TYPE;

   vsCodigoVenta      LET_PREMI_CONCU.COD_VENT%TYPE;
   vnOidTipoSoliPais  LET_PARAM_GENER.OID_TIPO_SOLI%TYPE;
   vnFormaPagoEnv     PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO%TYPE;
   vnClaseSolicEnv    PED_CLASE_SOLIC.OID_CLAS_SOLI%TYPE;
   vnOidAlmacEnv      PED_TIPO_SOLIC_PAIS.ALMC_OID_ALMA%TYPE;
   vnTipoSoliCons     PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS%TYPE;
   vnTipoDocum2       PED_TIPO_SOLIC_PAIS.TIDO_OID_TIPO_DOCU%TYPE;
   vnSubac            PED_TIPO_SOLIC.SBAC_OID_SBAC%TYPE;
   vnSocie            PED_TIPO_SOLIC_PAIS.SOCI_OID_SOCI%TYPE;

   vnOidEjecutiva     MAE_CLIEN.OID_CLIE%TYPE;
   vnOidClieDire      MAE_CLIEN_DIREC.OID_CLIE_DIRE%TYPE;
   vnOidTipoDocu      MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU%TYPE;
   vnOidTerr          ZON_TERRI_ADMIN.TERR_OID_TERR%TYPE;
   vnOidZona          ZON_ZONA.OID_ZONA%TYPE;
   vnOidValorEstrGeop ZON_TERRI.VEPO_OID_VALO_ESTR_GEOP%TYPE;
   vnOidTerrAdmi      MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI%TYPE;
   vnOidFormPago      MAE_CLIEN.FOPA_OID_FORM_PAGO%TYPE;
   vnImpPrecPosi      PRE_OFERT_DETAL.IMP_PREC_POSI%TYPE;

   vnOidSolicCabec    NUMBER;

   vnOidProducto      PRE_OFERT_DETAL.PROD_OID_PROD%TYPE;

   vnOidTipoCliente    MAE_TIPO_CLIEN.OID_TIPO_CLIE%TYPE;
   vnOidSubTipoCliente MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;
   vnNumSoliInicio     NUMBER;
   vnNumSoliFormato    NUMBER;
   vnNumeroCabeceras   NUMBER;
   vsCampanaFinConcu   LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE;
   vnFecFinPerio       CRA_PERIO.Fec_Fina%TYPE;
   vnOidDetalleOferta  pre_ofert_detal.Oid_Deta_Ofer%TYPE;

   BEGIN

      BEGIN
        SELECT COD_CONC,CAM_FINA
        INTO vsCodigoConcurso,vsCampanaFinConcu
        FROM LET_PARAM_CONCU_LIDER
        WHERE PAIS_COD_PAIS = psCodigoPais
        AND psCodigoPeriodo >=  CAM_INIC
        AND psCodigoPeriodo <= CAM_FINA
        AND EST_REGI = 1;
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
       vsCodigoConcurso := NULL;
      END;

      IF vsCodigoConcurso IS NOT NULL THEN

       vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
       vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
       vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
       vsCampanaSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,1);
       vnOidPeriodoSiguiente := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(vsCampanaSiguiente,vnOidMarca,vnOidCanal);

       BEGIN
         SELECT OID_TIPO_SOLI
         INTO   vnOidTipoSoliPais
         FROM LET_PARAM_GENER
         WHERE PAIS_COD_PAIS = psCodigoPais;
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
           vnOidTipoSoliPais := 0;
       END;

       SELECT COUNT(1)
       INTO vnNumeroCabeceras
       FROM LET_RESUL_SECCI_CAMPA
       WHERE PAIS_COD_PAIS = psCodigoPais
       AND CONC_COD_CONC = vsCodigoConcurso
       AND IND_EFEC = 1
       AND IND_PREM = 0;

       IF vnNumeroCabeceras > 0 THEN
          vnNumSoliInicio := LET_FN_RESRV_SECUE_NSOLI(psCodigoPais,'PED001','GZ','000',psCodigoCanal,vnNumeroCabeceras);
          vnNumSoliFormato :=  TO_CHAR(SYSDATE, 'yy') || LPAD(vnNumSoliInicio+1, 8, '0');

          SELECT TO_DATE((TO_CHAR(FEC_FINA,'dd/MM/yyyy')),'dd/MM/yyyy')AS FEC_FIN
          INTO vnFecFinPerio
          FROM CRA_PERIO
          WHERE OID_PERI = vnOidPeriodoSiguiente;
       END IF;

       OPEN C_RESULTADO_SECCIONES_CAMPANA(vsCodigoConcurso);

       LOOP

           FETCH C_RESULTADO_SECCIONES_CAMPANA BULK COLLECT INTO resultadoSeccionesCamp LIMIT W_FILAS;

           IF resultadoSeccionesCamp.COUNT > 0 THEN

              FOR i IN resultadoSeccionesCamp.FIRST .. resultadoSeccionesCamp.LAST LOOP

               BEGIN
                 SELECT COD_VENT
                 INTO vsCodigoVenta
                 FROM LET_PREMI_CAMPA
                 WHERE PAIS_COD_PAIS = psCodigoPais
                 AND CONC_COD_CONC = vsCodigoConcurso
                 AND CAM_DESP = vsCampanaSiguiente
                 AND RANG_NUM_RANG = resultadoSeccionesCamp(i).RAN_PREM
                 AND NICO_NIVE_CAMP = resultadoSeccionesCamp(i).NIV_CAMP
                 AND EST_REGI = 1
                 AND COD_VENT != 0;-- PER-SiCC-2012-0519
               EXCEPTION
               WHEN NO_DATA_FOUND THEN
                    vsCodigoVenta := 0;
               END;

               IF vsCodigoVenta <> 0 THEN

                 SELECT ofedet.Prod_Oid_Prod,ofedet.IMP_PREC_POSI,ofedet.oid_deta_ofer
                 INTO   vnOidProducto,vnImpPrecPosi,vnOidDetalleOferta
              	 FROM  pre_ofert ofe,
              		     pre_ofert_detal ofedet,
              		     pre_matri_factu mf,
              		     pre_matri_factu_cabec mfc,
              		     mae_produ prod,
              		     (SELECT v.val_oid, v.val_i18n
              		      FROM v_gen_i18n_sicc v
              		      WHERE v.attr_enti = 'MAE_PRODU'
              		      AND v.idio_oid_idio = 1) i18prod
              		WHERE mfc.perd_oid_peri = vnOidPeriodoSiguiente
              		AND mf.mfca_oid_cabe = mfc.oid_cabe
              		AND ofe.mfca_oid_cabe = mfc.oid_cabe
              		AND ofe.oid_ofer = ofedet.ofer_oid_ofer
              		AND ofedet.oid_deta_ofer = mf.ofde_oid_deta_ofer
              		AND ofedet.val_codi_vent = vsCodigoVenta
              		AND ofedet.prod_oid_prod = prod.oid_prod
              		AND prod.oid_prod = i18prod.val_oid;

                 SELECT A.FOPA_OID_FORM_PAGO,
                  	    d.OID_CLAS_SOLI,
                	      A.ALMC_OID_ALMA,
                	      A.TSOL_OID_TIPO_CONS,
                	      A.TIDO_OID_TIPO_DOCU,
                	      c.SBAC_OID_SBAC,
                	      A.SOCI_OID_SOCI
                 	 INTO vnFormaPagoEnv,
                   	    vnClaseSolicEnv,
                  	    vnOidAlmacEnv,
                  		  vnTipoSoliCons,
                  		  vnTipoDocum2,
                  		  vnSubac,
                		    vnSocie
                	 FROM ped_tipo_solic_pais A,
                	      ped_tipo_solic      c,
                	      ped_clase_solic     d
                	WHERE A.OID_TIPO_SOLI_PAIS = vnOidTipoSoliPais
                	  AND A.TSOL_OID_TIPO_SOLI = c.OID_TIPO_SOLI
                	  AND c.CLSO_OID_CLAS_SOLI = d.OID_CLAS_SOLI;

                  SELECT OID_CLIE,
                         FOPA_OID_FORM_PAGO
                    INTO vnOidEjecutiva,
                         vnOidFormPago
                    FROM MAE_CLIEN
                   WHERE PAIS_OID_PAIS = vnOidPais
                     AND COD_CLIE = resultadoSeccionesCamp(i).COD_LIDE;

                  -------------------------------------------------
                  IF (vnFormaPagoEnv IS NULL) THEN
                    IF (vnOidFormPago IS NOT NULL) THEN
                       vnFormaPagoEnv := vnOidFormPago;
                    ELSE
                      SELECT fopa_oid_form_pago
                        INTO vnFormaPagoEnv
                        FROM seg_pais a
                       WHERE a.oid_pais = vnOidPais;
                    END IF;
                  END IF;

                  BEGIN
                    SELECT val_tipo_camb
                      INTO vnOidFormPago
                      FROM pre_matri_factu_cabec a
                     WHERE a.perd_oid_peri = vnOidPeriodoSiguiente;
                  EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    vnOidFormPago := '';
                  END;
                  --------------------------------------------------

                  SELECT OID_CLIE_DIRE
                  INTO vnOidClieDire
                  FROM MAE_CLIEN_DIREC
                  WHERE CLIE_OID_CLIE = vnOidEjecutiva
                  AND   IND_DIRE_PPAL = 1
                  AND   IND_ELIM = 0;

                  SELECT TDOC_OID_TIPO_DOCU
                  INTO vnOidTipoDocu
                  FROM MAE_CLIEN_IDENT
                  WHERE CLIE_OID_CLIE = vnOidEjecutiva
                  AND VAL_IDEN_DOCU_PRIN = 1;

                  SELECT ztad.TERR_OID_TERR,mcua.ZTAD_OID_TERR_ADMI
                  INTO vnOidTerr,vnOidTerrAdmi
                  FROM MAE_CLIEN_UNIDA_ADMIN mcua,
                       ZON_TERRI_ADMIN ztad
                  WHERE ztad.PAIS_OID_PAIS = vnOidPais
                  AND   mcua.Ztad_Oid_Terr_Admi = ztad.oid_terr_admi
                  AND   mcua.CLIE_OID_CLIE = vnOidEjecutiva
                  AND   mcua.IND_ACTI = 1;

                  SELECT OID_ZONA
                  INTO vnOidZona
                  FROM ZON_ZONA
                  WHERE PAIS_OID_PAIS = vnOidPais
                  AND COD_ZONA = resultadoSeccionesCamp(i).COD_ZONA;

                  SELECT VEPO_OID_VALO_ESTR_GEOP
                  INTO vnOidValorEstrGeop
                  FROM ZON_TERRI
                  WHERE PAIS_OID_PAIS = vnOidPais
                  AND   OID_TERR = vnOidTerr;

                  SELECT TICL_OID_TIPO_CLIE,SBTI_OID_SUBT_CLIE
                  INTO vnOidTipoCliente,vnOidSubTipoCliente
                  FROM MAE_CLIEN_TIPO_SUBTI
                  WHERE CLIE_OID_CLIE = vnOidEjecutiva
                  AND IND_PPAL = 1;

                  SELECT PED_SOCA_SEQ.NEXTVAL
                  INTO vnOidSolicCabec
                  FROM dual;

                  INSERT INTO PED_SOLIC_CABEC(
                   OID_SOLI_CABE,
                   FEC_PROG_FACT,
                   TSPA_OID_TIPO_SOLI_PAIS,
                   TIDS_OID_TIPO_DESP,
                   ALMC_OID_ALMA,
                   MODU_OID_MODU,
                   TICL_OID_TIPO_CLIE,
                   PERD_OID_PERI,
                   CLIE_OID_CLIE,
                   CLIE_OID_CLIE_RECE_FACT,
                   CLIE_OID_CLIE_PAGA,
                   CLIE_OID_CLIE_DEST,
                   CLDI_OID_CLIE_DIRE,
                   TDOC_OID_TIPO_DOCU,
                   SOCI_OID_SOCI,
                   SBAC_OID_SBAC,
                   TERR_OID_TERR,
                   ZZON_OID_ZONA,
                   VAL_NUME_SOLI,
                   FEC_CRON,
                   IND_PERM_UNIO_SOL,
                   NUM_DOCU_ORIG,
                   IND_TS_NO_CONSO,
                   PAIS_OID_PAIS,
                   TIDO_OID_TIPO_DOCU,
                   VEPO_OID_VALO_ESTR_GEOP,
                   ESSO_OID_ESTA_SOLI,
                   COPA_OID_PARA_GENE,
                   GRPR_OID_GRUP_PROC,
                   SBTI_OID_SUBT_CLIE,
                   TSPA_OID_TIPO_SOLI_PAIS_CONS,
                   FOPA_OID_FORM_PAGO,
                   CLSO_OID_CLAS_SOLI,
                   ZTAD_OID_TERR_ADMI,
                   OPER_OID_OPER,
                   PROC_OID_PROC,
                   SOCA_OID_DOCU_REFE,
                   ICTP_OID_TIPO_PROG,
                   VAL_TIPO_CAMB,
                   IND_OC)
                  VALUES(
                   vnOidSolicCabec,
                   vnFecFinPerio,
                   vnOidTipoSoliPais,
                   1,
                   vnOidAlmacEnv,
                   1,
                   vnOidTipoCliente,
                   vnOidPeriodoSiguiente,
                   vnOidEjecutiva,
                   vnOidEjecutiva,
                   vnOidEjecutiva,
                   vnOidEjecutiva,
                   vnOidClieDire,
                   vnOidTipoDocu,
                   vnSocie,
                   vnSubac,
                   vnOidTerr,
                   vnOidZona,
                   vnNumSoliFormato,
                   TO_DATE((TO_CHAR(SYSDATE,'dd/MM/yyyy')),'dd/MM/yyyy'),
                   1,
                   NULL,
                   1,
                   vnOidPais,
                   30,
                   vnOidValorEstrGeop,
                   1,
                   NULL,
                   3,
                   vnOidSubTipoCliente,
                   vnTipoSoliCons,
                   vnFormaPagoEnv,
                   vnClaseSolicEnv,
                   vnOidTerrAdmi,
                   21,
                   1,
                   NULL,
                   NULL,
                   vnOidFormPago,
                   0);

                  INSERT INTO PED_SOLIC_POSIC(
                   OID_SOLI_POSI,
                   COD_POSI,
                   NUM_UNID_DEMA,
                   NUM_UNID_POR_ATEN,
                   VAL_TASA_IMPU,
                   SOCA_OID_SOLI_CABE,
                   TPOS_OID_TIPO_POSI,
                   PROD_OID_PROD,
                   VAL_PREC_CATA_UNIT_LOCA,
                   VAL_PREC_CONT_UNIT_LOCA,
                   VAL_PREC_CATA_UNIT_DOCU,
                   VAL_PREC_CONTA_UNIT_DOCU,
                   NUM_UNID_COMPR,
                   NUM_UNID_DEMA_REAL,
                   ESPO_OID_ESTA_POSI,
                   STPO_OID_SUBT_POSI,
                   VAL_CODI_VENT,
                   OFDE_OID_DETA_OFER)
                  VALUES(
                   PED_SOPO_SEQ.NEXTVAL,
                   1,
                   1,
                   1,
                   0,
                   vnOidSolicCabec,
                   9,
                   vnOidProducto,
                   0,
                   vnImpPrecPosi,
                   0,
                   vnImpPrecPosi,
                   1,
                   1,
                   4,
                   13,
                   vsCodigoVenta,
                   vnOidDetalleOferta);

                   UPDATE LET_RESUL_SECCI_CAMPA
                   SET IND_PREM = 1
                   WHERE PAIS_COD_PAIS = psCodigoPais
                   AND CONC_COD_CONC = resultadoSeccionesCamp(i).CONC_COD_CONC
                   AND LIDE_CAM_LIDE = resultadoSeccionesCamp(i).LIDE_CAM_LIDE
                   AND LIDE_COD_LIDE = resultadoSeccionesCamp(i).COD_LIDE;

                   vnNumSoliFormato := vnNumSoliFormato + 1;

                 END IF;
              END LOOP;

           END IF;

       EXIT WHEN C_RESULTADO_SECCIONES_CAMPANA%NOTFOUND;
       END LOOP;

       CLOSE C_RESULTADO_SECCIONES_CAMPANA;
      END IF;

   EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_ENTRE_PREMI_CAMPA_CIERR: ' || ls_sqlerrm);
   END LET_PR_ENTRE_PREMI_CAMPA_CIERR;

   /*********************************************************
    Descripcion : Recupera la campa�as de ingreso de la lider
    Fecha Creacion : 17/03/2011
    Autor : Jesse Rios
    Parametros Entrada: psCodigoClie : Codigo de cliente
    *********************************************************/
   FUNCTION LET_FN_DEVUE_CAMPA_INGRE_LIDER(psCodigoClie VARCHAR2)
    RETURN VARCHAR2 IS

     vnOidPeriodo ZON_HISTO_GEREN.PERD_OID_PERI_DESD%TYPE;
     vsCodigoPeriodo seg_perio_corpo.COD_PERI%TYPE;

    BEGIN

     SELECT MIN(Z.PERD_OID_PERI_DESD)
     INTO vnOidPeriodo
     FROM ZON_HISTO_GEREN Z
     WHERE Z.GERE = psCodigoClie;

     SELECT a.cod_peri
     INTO vsCodigoPeriodo
     FROM seg_perio_corpo a,
          cra_perio b,
          seg_canal c,
          seg_marca d
     WHERE a.oid_peri = b.peri_oid_peri
     and   b.CANA_OID_CANA = c.OID_CANA
     and   b.MARC_OID_MARC = d.OID_MARC
     and   c.COD_CANA = 'VD'
     and   d.COD_MARC = 'T'
     and   b.oid_peri = vnOidPeriodo;

     RETURN vsCodigoPeriodo;

     EXCEPTION
     WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_FN_DEVUE_CAMPA_INGRE_LIDER: '||ls_sqlerrm);
     RETURN '';
    END LET_FN_DEVUE_CAMPA_INGRE_LIDER;

   /***********************************************************************************************
    Descripcion       : Realiza la Evaluaci�n de productividad al cierre de regi�n y campa�a
    Fecha Creacion    : 21/03/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_EVALU_PRODU_REGIO_CAMPA(psCodigoPais VARCHAR2,
                                            psCodigoMarca VARCHAR2,
                                            psCodigoCanal VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoUsuario VARCHAR2) IS

     -- CURSORES ------------
     CURSOR C_RESUL_SECCI_CAMPA (
       codigoConcurso LET_RESUL_SECCI_CAMPA.CONC_COD_CONC%TYPE
     ) IS
       SELECT    DISTINCT(LIDE_COD_LIDE)
       FROM      LET_RESUL_SECCI_CAMPA
       WHERE     PAIS_COD_PAIS = psCodigoPais
         AND     CONC_COD_CONC = codigoConcurso;

     TYPE listaResulSecciCampaRecord IS RECORD(
       codigoLider LET_RESUL_SECCI_CAMPA.LIDE_COD_LIDE%TYPE
     );
     TYPE listaResulSecciCampaTab IS TABLE OF listaResulSecciCampaRecord;
     listaResulSecciCampa listaResulSecciCampaTab;

     -- VARIABLES -----------
     vsFlagExiste           VARCHAR2(6) := 0;
     vsCodigoConcurso       LET_PARAM_CONCU_LIDER.COD_CONC%TYPE;
     vsCampaniaInicial      LET_RESUL_SECCI_CAMPA.LIDE_CAM_LIDE%TYPE;
     vsCampaniaFinal        LET_RESUL_SECCI_CAMPA.LIDE_CAM_LIDE%TYPE;
     vnCampaniaEvaluada     LET_PRODU_LIDER.NUM_CAMP_EVAL%TYPE;
     vnDivisionStatusP      NUMBER(2);
     vnCalcularDivision     NUMBER(6,2);
     vnRatio                LET_PARAM_CONCU_LIDER.RAT_CRIT%TYPE;
     vsStatus               LET_PRODU_LIDER.VAL_ESTA%TYPE;
     vnOidPais              SEG_PAIS.OID_PAIS%TYPE;
     vnOidMarca             SEG_MARCA.OID_MARC%TYPE;
     vnOidCanal             SEG_CANAL.OID_CANA%TYPE;
     vnCanMiniCamp          LET_PARAM_CONCU_LIDER.CAN_MINI_CAMP%TYPE;
     /* INI JJ PER-SiCC-2012-0201 */
     vnNumCampComoEsta      LET_PARAM_CONCU_LIDER.NUM_CAMP_COMO_ESTA%TYPE;
     vsCodigoCampIni        SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnEncontroClasificacionLider NUMBER;
     e_clasificacionLider         EXCEPTION;
     vsCodLider             LET_RESUL_SECCI_CAMPA.LIDE_COD_LIDE%TYPE;
     vsCodigoClasificacion  LET_HISTO_CLASI_LIDER.COD_CLAS_LIDE%TYPE;
     vnContadorCamp         NUMBER := 0;
     vnNumeroCampanas       NUMBER := 0;
     vsCodigoPeriodoProcesar SEG_PERIO_CORPO.COD_PERI%TYPE;
     /* FIN JJ PER-SiCC-2012-0201 */

     vnNivelMinimo            LET_PARAM_CONCU_LIDER.NIV_MINI%TYPE;
     vnNivelMiniCampEsta      LET_PARAM_CONCU_LIDER.NIV_MINI_CAMP_CLAS_ESTA%TYPE;
     vnNumCampMaxiEsta        LET_PARAM_CONCU_LIDER.NUM_CAMP_MAXI_ESTA%TYPE;
     vsCamInicEvalEsta        LET_PARAM_CONCU_LIDER.CAM_INIC_EVAL_ESTA%TYPE;
     vnNumCampMiniPartConc    LET_PARAM_CONCU_LIDER.NUM_CAMP_MINI_PART_CONC%TYPE;
     vnRatioExito             LET_PARAM_CONCU_LIDER.RAT_EXIT%TYPE;


     vnDivisionStatusS        NUMBER;
     vnDivisionStatusX        NUMBER;
     vnDivisionStatusC        NUMBER;

     vnCalcularDivisionExito  NUMBER(6,2);
   BEGIN

     -- 1 Validar si existe el concurso
     SELECT    COUNT(1)
       INTO    vsFlagExiste
     FROM      LET_PARAM_CONCU_LIDER
     WHERE     PAIS_COD_PAIS = psCodigoPais
       AND     psCodigoPeriodo >= CAM_INIC
       AND     psCodigoPeriodo <= CAM_FINA
       AND     EST_REGI = 1;

     IF vsFlagExiste = 1 THEN

       -- 1.1 Capturar codigo concurso
       SELECT    COD_CONC,
                 NIV_MINI,
                 NIV_MINI_CAMP_CLAS_ESTA,
                 NUM_CAMP_MAXI_ESTA,
                 CAM_INIC_EVAL_ESTA,
                 NUM_CAMP_MINI_PART_CONC,
                 RAT_CRIT,
                 RAT_EXIT
         INTO    vsCodigoConcurso,
                 vnNivelMinimo,
                 vnNivelMiniCampEsta,
                 vnNumCampMaxiEsta,
                 vsCamInicEvalEsta,
                 vnNumCampMiniPartConc,
                 vnRatio,
                 vnRatioExito
       FROM      LET_PARAM_CONCU_LIDER
       WHERE     PAIS_COD_PAIS = psCodigoPais
         AND     psCodigoPeriodo >= CAM_INIC
         AND     psCodigoPeriodo <= CAM_FINA
         AND     EST_REGI = 1;

       /* INI JJ PER-SiCC-2012-0201 */
       DELETE FROM LET_PRODU_LIDER WHERE PAIS_COD_PAIS = psCodigoPais AND CONC_COD_CONC = vsCodigoConcurso;

       -- 1.2 Listar resultados_secciones_x_campa�a
       OPEN C_RESUL_SECCI_CAMPA(
         vsCodigoConcurso
       );
       LOOP

         FETCH C_RESUL_SECCI_CAMPA BULK COLLECT INTO listaResulSecciCampa LIMIT W_FILAS;
         IF listaResulSecciCampa.COUNT > 0 THEN

           -- Recorrer el Cursor Paginado de RESUL_SECCI_CAMPA
           FOR i IN listaResulSecciCampa.FIRST .. listaResulSecciCampa.LAST LOOP
              vnContadorCamp := 0;

             --Cantidad de campa�as participando en concurso
             SELECT COUNT(1)
               INTO vnCampaniaEvaluada
               FROM LET_RESUL_SECCI_CAMPA
             WHERE PAIS_COD_PAIS = psCodigoPais
                AND CONC_COD_CONC = vsCodigoConcurso
                AND LIDE_COD_LIDE = listaResulSecciCampa(i).codigoLider;

             --Obtener cantidad de campa�as con estatus de productividad igual a "S" (Estable)
             SELECT COUNT(1)
               INTO vnDivisionStatusS
               FROM LET_RESUL_SECCI_CAMPA
             WHERE PAIS_COD_PAIS = psCodigoPais
                AND CONC_COD_CONC = vsCodigoConcurso
                AND LIDE_COD_LIDE = listaResulSecciCampa(i).codigoLider
                AND VAL_ESTA = 'S';

             --Obtener cantidad de campa�as con estatus de productividad igual "X" (Exitosa)
             SELECT    COUNT(1)
               INTO vnDivisionStatusX
             FROM      LET_RESUL_SECCI_CAMPA
             WHERE     PAIS_COD_PAIS = psCodigoPais
               AND     CONC_COD_CONC = vsCodigoConcurso
                AND LIDE_COD_LIDE = listaResulSecciCampa(i).codigoLider
                AND VAL_ESTA = 'X';

             --Obtener cantidad de campa�as con estatus de productividad igual "C" (Critica)
               SELECT    COUNT(1)
               INTO vnDivisionStatusC
               FROM      LET_RESUL_SECCI_CAMPA
               WHERE     PAIS_COD_PAIS = psCodigoPais
                 AND     CONC_COD_CONC = vsCodigoConcurso
                 AND     LIDE_COD_LIDE = listaResulSecciCampa(i).codigoLider
                AND VAL_ESTA = 'C';

             --Se evalua participacion en concurso
             IF(vnCampaniaEvaluada >= vnNumCampMiniPartConc) THEN

               --Proyecci�n Estatus Exitosa
               --Calcular Ratio Exito
               vnCalcularDivisionExito := vnDivisionStatusX / vnCampaniaEvaluada;

               --Calcular Ratio Criticidad
               vnCalcularDivision := vnDivisionStatusC / vnCampaniaEvaluada;

               -- 1.2.6 Obtener status
               IF vnCalcularDivisionExito >= vnRatioExito THEN
                 vsStatus := 'X'; --'Exitosa
               ELSE
               IF vnCalcularDivision >= vnRatio THEN
                   vsStatus := 'C'; --Critica
               ELSE
                   vsStatus := 'S'; --Estable
                 END IF;

               END IF;

               -- INSERTAR LET_PRODU_LIDER
               SELECT    COUNT(1)
                 INTO    vsFlagExiste
               FROM      LET_PRODU_LIDER
               WHERE     PAIS_COD_PAIS = psCodigoPais
                 AND     CONC_COD_CONC = vsCodigoConcurso
                 AND     COD_LID = listaResulSecciCampa(i).codigoLider;

               IF vsFlagExiste > 0 THEN
                 UPDATE    LET_PRODU_LIDER
                   SET     NUM_CAMP_EVAL = vnCampaniaEvaluada,
                        NUM_CAMP_TRAN = vnDivisionStatusX + vnDivisionStatusS,
                           VAL_RATI = vnCalcularDivision,
                        VAL_RATI_EXIT = vnCalcularDivisionExito,
                        VAL_ESTA = vsStatus,
                        USU_MODI = psCodigoUsuario,
                        FEC_MODI = SYSDATE
                 WHERE     PAIS_COD_PAIS = psCodigoPais
                   AND     CONC_COD_CONC = vsCodigoConcurso
                   AND     COD_LID = listaResulSecciCampa(i).codigoLider;
               ELSE
                 INSERT INTO LET_PRODU_LIDER (
                   PAIS_COD_PAIS,
                   CONC_COD_CONC,
                   COD_LID,
                   NUM_CAMP_EVAL,
                   NUM_CAMP_TRAN,
                   VAL_RATI,
                   VAL_RATI_EXIT,
                   VAL_ESTA,
                   USU_MODI,
                   FEC_MODI,
                   EST_REGI
                 ) VALUES (
                   psCodigoPais,
                   vsCodigoConcurso,
                   listaResulSecciCampa(i).codigoLider,
                   vnCampaniaEvaluada,
                   vnDivisionStatusX + vnDivisionStatusS,
                   vnCalcularDivision,
                   vnCalcularDivisionExito,
                   vsStatus,
                   psCodigoUsuario,
                   SYSDATE,
                   1
                 );
             END IF;

             END IF;

           END LOOP; -- Fin del Cursor Paginado de RESUL_SECCI_CAMPA

         END IF; -- listaResulSecciCampa.COUNT > 0

         EXIT WHEN C_RESUL_SECCI_CAMPA%NOTFOUND;
       END LOOP;
       CLOSE C_RESUL_SECCI_CAMPA; -- 1.2 Listar resultados_secciones_x_campa�a

     END IF; -- 1 Validar si existe el concurso

   EXCEPTION
     /* INI JJ PER-SiCC-2012-0201 */
     WHEN e_clasificacionLider THEN
       RAISE_APPLICATION_ERROR(-20001, ' Lider '||vsCodLider || ' no tiene Clasificaci�n para la campa�a '||vsCampaniaFinal||' ');
       /* FIN JJ PER-SiCC-2012-0201 */
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_EVALU_PRODU_REGIO_CAMPA: ' || Ls_Sqlerrm);
   END LET_PR_EVALU_PRODU_REGIO_CAMPA;

   /*********************************************************
    Descripcion : Recupera la campa�as de ingreso de la lider
    Fecha Creacion : 30/03/2011
    Autor : Jesse Rios
    *********************************************************/
    PROCEDURE LET_PR_GENER_REPOR_RESUL_LIDER(psCodigoPais VARCHAR2,
                                             psCodigoConcurso VARCHAR2,
                                             psCodigoMarca VARCHAR2,
                                             psCodigoCanal VARCHAR2)
                                             IS
      CURSOR C_RESULTADO_LIDERES_CONCURSO(oidPais NUMBER,oidMarca NUMBER,oidCanal NUMBER) IS
      SELECT LC.CONC_COD_CONC,
             CO.DES_CONC,
             CO.CAM_INIC,
             CO.CAM_FINA,
             CL.COD_CLIE,
             (TRIM(NVL(CL.VAL_NOM1,' ')) ||'  '||TRIM(NVL(CL.VAL_APE1,' '))||'  '||TRIM(NVL2(CL.VAL_APE2,SUBSTR(CL.VAL_APE2,0,1) || '.',' ')))AS NOMBRE,
             LC.NIV_CONC,
             LC.RAN_PREM,
             RP.CAN_PEDI,
             NVL((SELECT MET_PEDI
                  FROM LET_METAS_LIDER_CONCU MLC
                  WHERE PAIS_COD_PAIS = psCodigoPais
                  AND   LC.CONC_COD_CONC = MLC.CONC_COD_CONC
                  AND   DECODE(LC.NIV_CONC,'0','1',LC.NIV_CONC) = MLC.NICO_NIVE_CONC
                  AND   LC.COD_LIDE = MLC.COD_LIDE),0) AS MET_PEDI,
             SC.ACT_FINA,
             PL.NUM_CAMP_EVAL,
             (CASE
              WHEN LC.IND_EFEC = 1 THEN
                (SELECT RC.COD_VENT
                FROM LET_PREMI_CONCU RC
                WHERE LC.CONC_COD_CONC = RC.CONC_COD_CONC
                AND   LC.RAN_PREM = RC.RANG_NUM_RANG
                AND   LC.NIV_CONC = RC.NICO_NIVE_CONC
                AND   RC.CAM_DESP = PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(CO.CAM_FINA,oidPais,oidMarca,oidCanal,1))
              ELSE
                ' '
              END) AS COD_PREM,
              LC.COD_REGI,
              LC.COD_ZONA,
              LC.COD_SECC,
              LC.IND_ACTI,
              PL.VAL_ESTA,
              LC.IND_EFEC,
              (CASE
              WHEN LC.IND_EFEC = 1 THEN
                (SELECT BO.VAL_MONT_BONO
                FROM LET_BONOS_CONCU BO
                WHERE LC.CONC_COD_CONC = BO.COD_CONC
                AND   LC.RAN_PREM = BO.RANG_NUM_RANG
                AND   LC.NIV_CONC = BO.NICO_NUM_NIVE
                AND   BO.CAM_DESP = PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(CO.CAM_FINA,oidPais,oidMarca,oidCanal,1))
              ELSE
                NULL
              END) AS VAL_BONO
       FROM LET_RESUL_LIDER_CONCU LC,
            LET_PARAM_CONCU_LIDER CO,
            MAE_CLIEN CL,
            LET_PARAM_RANGO_PREMI RP,
            LET_RESUL_SECCI_CAMPA SC,
            LET_PRODU_LIDER PL
        WHERE LC.PAIS_COD_PAIS = psCodigoPais--parametro requerido
        AND   LC.CONC_COD_CONC = CO.COD_CONC
        AND   LC.COD_LIDE = CL.COD_CLIE
        AND   LC.CONC_COD_CONC = psCodigoConcurso--parametro requerido
        AND   LC.CONC_COD_CONC = RP.CONC_COD_CONC
        AND   DECODE(LC.RAN_PREM,'0','1',LC.RAN_PREM) = RP.RANG_NUM_RANG
        AND   LC.CONC_COD_CONC = SC.CONC_COD_CONC
        AND   CO.CAM_FINA = SC.LIDE_CAM_LIDE
        AND   LC.COD_LIDE = SC.LIDE_COD_LIDE
        AND   LC.CONC_COD_CONC = PL.CONC_COD_CONC(+)
        AND   LC.COD_LIDE = PL.COD_LID(+)
        ORDER BY LC.COD_REGI,LC.COD_ZONA,LC.COD_SECC;

        TYPE resultadoLideresConcuRecord IS RECORD(
           CONC_COD_CONC LET_TEMPO_RESUL_LIDER_CONCU.CONC_COD_CONC%TYPE,
           DES_CONC      LET_TEMPO_RESUL_LIDER_CONCU.DES_CONC%TYPE,
           CAM_INIC      LET_TEMPO_RESUL_LIDER_CONCU.CAM_INIC%TYPE,
           CAM_FINA      LET_TEMPO_RESUL_LIDER_CONCU.CAM_FINA%TYPE,
           COD_CLIE      LET_TEMPO_RESUL_LIDER_CONCU.COD_CLIE%TYPE,
           NOM_CLIE      LET_TEMPO_RESUL_LIDER_CONCU.NOM_CLIE%TYPE,
           NIV_CONC      LET_TEMPO_RESUL_LIDER_CONCU.NIV_CONC%TYPE,
           RAN_PREM      LET_TEMPO_RESUL_LIDER_CONCU.RAN_PREM%TYPE,
           CAN_PEDI      LET_TEMPO_RESUL_LIDER_CONCU.CAN_PEDI%TYPE,
           MET_PEDI      LET_TEMPO_RESUL_LIDER_CONCU.MET_PEDI%TYPE,
           ACT_FINA      LET_TEMPO_RESUL_LIDER_CONCU.ACT_FINA%TYPE,
           NUM_CAMP_EVAL LET_TEMPO_RESUL_LIDER_CONCU.NUM_CAMP_EVAL%TYPE,
           COD_PREM      LET_TEMPO_RESUL_LIDER_CONCU.COD_PREM%TYPE,
           COD_REGI      LET_RESUL_LIDER_CONCU.COD_REGI%TYPE,
           COD_ZONA      LET_RESUL_LIDER_CONCU.COD_ZONA%TYPE,
           COD_SECC      LET_RESUL_LIDER_CONCU.COD_SECC%TYPE,
           IND_ACTI      LET_TEMPO_RESUL_LIDER_CONCU.IND_ACTI%TYPE,
           VAL_ESTA      LET_PRODU_LIDER.VAL_ESTA%TYPE,
           IND_EFEC      LET_RESUL_LIDER_CONCU.IND_EFEC%TYPE,
           VAL_BONO      LET_BONOS_CONCU.VAL_MONT_BONO%TYPE
        );

        TYPE resultadoLideresConcuTab IS TABLE OF resultadoLideresConcuRecord;
        resultadoLideresConcu resultadoLideresConcuTab;

        vnOidPais        SEG_PAIS.OID_PAIS%TYPE;
        vnOidMarca       SEG_MARCA.OID_MARC%TYPE;
        vnOidCanal       SEG_CANAL.OID_CANA%TYPE;
        vsCampanaInicial LET_PARAM_CONCU_LIDER.CAM_INIC%TYPE;
        vsCampanaFinal   LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE;
        vnNumeroCampanas NUMBER;
        vnPedidosLogrados LET_RESUL_SECCI_CAMPA.NUM_PEDI%TYPE;
        vsCampanaLider SEG_PERIO_CORPO.COD_PERI%TYPE;

    BEGIN

        EXECUTE IMMEDIATE 'TRUNCATE TABLE LET_TEMPO_RESUL_LIDER_CONCU';

        vnOidPais  := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
        vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
        vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

        SELECT CAM_INIC,CAM_FINA
        INTO vsCampanaInicial,vsCampanaFinal
        FROM LET_PARAM_CONCU_LIDER
        WHERE PAIS_COD_PAIS = psCodigoPais
        AND COD_CONC = psCodigoConcurso;

        vnNumeroCampanas := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(vsCampanaInicial,vsCampanaFinal,vnOidPais,vnOidMarca,vnOidCanal);

        OPEN C_RESULTADO_LIDERES_CONCURSO(vnOidPais,vnOidMarca,vnOidCanal);

        LOOP
         FETCH C_RESULTADO_LIDERES_CONCURSO BULK COLLECT INTO resultadoLideresConcu LIMIT W_FILAS;

         IF resultadoLideresConcu.COUNT > 1 THEN
            FOR i IN resultadoLideresConcu.FIRST .. resultadoLideresConcu.LAST LOOP
                vnPedidosLogrados := 0;
                FOR j IN 0 .. vnNumeroCampanas-1 LOOP

                 vsCampanaLider := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsCampanaInicial,vnOidPais,vnOidMarca,vnOidCanal,j);

                 BEGIN
                   SELECT NUM_PEDI
                   INTO vnPedidosLogrados
                   FROM LET_RESUL_SECCI_CAMPA
                   WHERE PAIS_COD_PAIS = psCodigoPais
                   AND LIDE_COD_LIDE = resultadoLideresConcu(i).COD_CLIE
                   AND LIDE_CAM_LIDE = vsCampanaLider;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                   vnPedidosLogrados:= 0;
                 END;

                 INSERT INTO LET_TEMPO_RESUL_LIDER_CONCU(
                   CONC_COD_CONC,
                   DES_CONC,
                   CAM_INIC,
                   CAM_FINA,
                   COD_CLIE,
                   NOM_CLIE,
                   NIV_CONC,
                   RAN_PREM,
                   CAN_PEDI,
                   MET_PEDI,
                   ACT_FINA,
                   NUM_CAMP_EVAL,
                   COD_PREM,
                   COD_PERI,
                   PED_LOGR,
                   COD_REGI,
                   COD_ZONA,
                   COD_SECC,
                   IND_ACTI,
                   VAL_ESTA,
                   IND_EFEC,
                   VAL_MONT_BONO)
                  VALUES(
                   resultadoLideresConcu(i).CONC_COD_CONC,
                   resultadoLideresConcu(i).DES_CONC,
                   resultadoLideresConcu(i).CAM_INIC,
                   resultadoLideresConcu(i).CAM_FINA,
                   resultadoLideresConcu(i).COD_CLIE,
                   resultadoLideresConcu(i).NOM_CLIE,
                   resultadoLideresConcu(i).NIV_CONC,
                   resultadoLideresConcu(i).RAN_PREM,
                   resultadoLideresConcu(i).CAN_PEDI,
                   resultadoLideresConcu(i).MET_PEDI,
                   resultadoLideresConcu(i).ACT_FINA,
                   resultadoLideresConcu(i).NUM_CAMP_EVAL,
                   resultadoLideresConcu(i).COD_PREM,
                   vsCampanaLider,
                   vnPedidosLogrados,
                   resultadoLideresConcu(i).COD_REGI,
                   resultadoLideresConcu(i).COD_ZONA,
                   resultadoLideresConcu(i).COD_SECC,
                   resultadoLideresConcu(i).IND_ACTI,
                   resultadoLideresConcu(i).VAL_ESTA,
                   resultadoLideresConcu(i).IND_EFEC,
                   resultadoLideresConcu(i).VAL_BONO);
                END LOOP;
            END LOOP;
         END IF;

         EXIT WHEN C_RESULTADO_LIDERES_CONCURSO%NOTFOUND;
        END LOOP;
        CLOSE C_RESULTADO_LIDERES_CONCURSO;
    EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_GENER_REPOR_RESUL_LIDER: ' || ls_sqlerrm);
    END LET_PR_GENER_REPOR_RESUL_LIDER;

   /***********************************************************************************************
    Descripcion       : Realiza la asignacion y desasignacion de lideres al inicio de campa�a
    Fecha Creacion    : 13/07/2011
    Autor             : Carlos Diaz Valverde
   ***********************************************************************************************/
   PROCEDURE LET_PR_ASIGN_DESAS_LIDER(psCodigoPais VARCHAR2,
                                      psCodigoPeriodo VARCHAR2,
                                      pscodigoUsuario VARCHAR2) IS

     -- CURSORES ------------
     CURSOR C_DESVI_LIDER(
       oidPais             zon_histo_geren.pais_oid_pais%type,
       oidPeriodoAnterior  zon_histo_geren.perd_oid_peri_desd%type
     ) IS
       select    zh.oid_hist_gere,
                 zh.perd_oid_peri_desd,
                 zh.perd_oid_peri_hast,
                 zh.gere,
                 substr(zh.ua,3,2) cod_regi,
                 substr(zh.ua,5,4) cod_zona,
                 substr(zh.ua,9) cod_secc
       from      zon_histo_geren zh,
                 zon_tipo_desvi td
       where     zh.pais_oid_pais = oidPais
         and     zh.perd_oid_peri_hast = oidPeriodoAnterior
         and     zh.IND_DESV_AUTO = td.COD_TIPO_DESV
         and     TD.IND_DESV = 0
        /* and     (
                   zh.ind_desv_auto = 0 or
                   zh.ind_desv_auto is null
                 )  */
         and     length(zh.ua) = 9
       order by  2;
     TYPE liderPendienteRecord IS RECORD(
       oidHisGer       zon_histo_geren.oid_hist_gere%type,
       oidPerDes       zon_histo_geren.perd_oid_peri_desd%type,
       oidPerHas       zon_histo_geren.perd_oid_peri_hast%type,
       codLidere       zon_histo_geren.gere%type,
       codRegion       zon_regio.cod_regi%type,
       codZonass       zon_zona.cod_zona%type,
       codSeccio       zon_secci.cod_secc%type
     );
     TYPE liderPendienteTab IS TABLE OF liderPendienteRecord;
     liderPendienteList liderPendienteTab;

     CURSOR C_ASIGN_LIDER(
       oidPais             zon_histo_geren.pais_oid_pais%type,
       oidPeriodoProceso   zon_histo_geren.perd_oid_peri_desd%type
     ) IS
       select    zh.oid_hist_gere,
                 zh.perd_oid_peri_desd,
                 zh.perd_oid_peri_hast,
                 zh.gere,
                 substr(zh.ua,3,2) cod_regi,
                 substr(zh.ua,5,4) cod_zona,
                 substr(zh.ua,9) cod_secc
       from      zon_histo_geren zh
       where     zh.pais_oid_pais = oidPais
         and     zh.perd_oid_peri_desd = oidPeriodoProceso
         and     length(zh.ua) = 9
       order by  2;
     TYPE asignarLiderRecord IS RECORD(
       oidHisGer       zon_histo_geren.oid_hist_gere%type,
       oidPerDes       zon_histo_geren.perd_oid_peri_desd%type,
       oidPerHas       zon_histo_geren.perd_oid_peri_hast%type,
       codLidere       zon_histo_geren.gere%type,
       codRegion       zon_regio.cod_regi%type,
       codZonass       zon_zona.cod_zona%type,
       codSeccio       zon_secci.cod_secc%type
     );
     TYPE asignarLiderTab IS TABLE OF asignarLiderRecord;
     asignarLiderList asignarLiderTab;

     CURSOR C_ZON_HISTO_GEREN(oidPais ZON_HISTO_GEREN.PAIS_OID_PAIS%TYPE,oidPeriodoAnt CRA_PERIO.OID_PERI%TYPE,fechaFinPeriodoAnt CRA_PERIO.FEC_FINA%TYPE) IS
     SELECT *
     FROM ZON_HISTO_GEREN
     WHERE PAIS_OID_PAIS = oidPais
     AND PERD_OID_PERI_HAST = oidPeriodoAnt
     AND FEC_HAST > fechaFinPeriodoAnt;

     TYPE zonHistoGerenTab IS TABLE OF ZON_HISTO_GEREN%ROWTYPE;
     zonHistoGeren zonHistoGerenTab;

     -- VARIABLES -----------
     vsValParametro     BAS_PARAM_PAIS.VAL_PARA%TYPE;
     vnOidPais          ZON_HISTO_GEREN.PAIS_OID_PAIS%TYPE;
     vnOidMarca         ZON_HISTO_GEREN.MARC_OID_MARC%TYPE;
     vnOidCanal         ZON_HISTO_GEREN.CANA_OID_CANA%TYPE;
     vnCodPeriodoAnt    SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnOidPeriodoAnt    CRA_PERIO.OID_PERI%TYPE;
     vnOidPeriodo       CRA_PERIO.OID_PERI%TYPE;
     vnOidCliente       MAE_CLIEN.OID_CLIE%TYPE;
     vnOidClienteSec    MAE_CLIEN.OID_CLIE%TYPE;
     vnExiste           NUMBER(3);
     vnOidSeccion       ZON_SECCI.OID_SECC%TYPE;
     e_insertClaLid     EXCEPTION;
     vdFechaFinPeriodoAnt CRA_PERIO.FEC_FINA%TYPE;

   BEGIN

     -- Inicializar variables ---------------------------------------
     vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
     vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA('T');
     vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
     vnCodPeriodoAnt := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-1);
     vnOidPeriodoAnt := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(vnCodPeriodoAnt,vnOidMarca,vnOidCanal);
     vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,vnOidMarca,vnOidCanal);

     SELECT FEC_FINA
     INTO vdFechaFinPeriodoAnt
     FROM CRA_PERIO
     WHERE PAIS_OID_PAIS = vnOidPais
     AND MARC_OID_MARC = vnOidMarca
     AND CANA_OID_CANA = vnOidCanal
     AND OID_PERI = vnOidPeriodoAnt;

     -- Validar si se debe ejecutar el proceso ----------------------
     select    VAL_PARA
       into    vsValParametro
     from      BAS_PARAM_PAIS
     where     COD_PAIS = psCodigoPais
       and     COD_SIST = 'LET'
       and     COD_PARA = '001';

     IF vsValParametro >= 1 THEN
        -- Cursor de DESVINCULAR LIDERES -------------------------------
       OPEN C_DESVI_LIDER(
         vnOidPais,
         vnOidPeriodoAnt
       );
       LOOP

         FETCH C_DESVI_LIDER BULK COLLECT INTO liderPendienteList LIMIT W_FILAS;
         IF liderPendienteList.COUNT > 0 THEN

           -- Recorrer el Cursor Paginado de LIDER PENDIENTE
           FOR i IN liderPendienteList.FIRST .. liderPendienteList.LAST LOOP

             -- 1 Capturar Variables requeridas ---------------------

             -- Obtener oidSeccion
             select    oid_secc
               into    vnOidSeccion
             from      zon_secci
             where     zzon_oid_zona = gen_pkg_gener.GEN_FN_DEVUELVE_ID_ZONA(
                         psCodigoPais,
                         'T',
                         'VD',
                         liderPendienteList(i).codRegion,
                         liderPendienteList(i).codZonass
                       )
             and       cod_secc = liderPendienteList(i).codSeccio
             and       ind_acti = 1;

             -- 2 Desvincular lideres -------------------------------

             -- Capturar consultora de la seccion
             select    zs.clie_oid_clie
               into    vnOidClienteSec
             from      zon_secci zs,
                       zon_zona zz
             where     zz.oid_zona = zs.zzon_oid_zona
               and     zz.cod_zona = liderPendienteList(i).codZonass
               and     zs.cod_secc = liderPendienteList(i).codSeccio
               and     zs.ind_acti = 1;

             -- Si existe consultora. se quita la clasificacion lider
             IF vnOidClienteSec IS NOT NULL THEN

               DELETE
               FROM      mae_clien_clasi
               WHERE     oid_clie_clas IN (
               	           SELECT    a.oid_clie_clas
               			       FROM      mae_clien_clasi a,
                             		     mae_clien_tipo_subti b,
                            		     MAE_TIPO_CLASI_CLIEN x,
                            		     MAE_CLASI y,
                            		     mae_clien m
                			     WHERE     a.ctsu_oid_clie_tipo_subt = b.Oid_Clie_Tipo_Subt
                      	     AND     b.clie_oid_clie = m.oid_clie
                      	     and     m.oid_clie = vnOidClienteSec
                       	     AND     b.Ind_Ppal = 1
                       	 	   AND     a.tccl_oid_tipo_clasi = x.oid_tipo_clas
                       	 	   AND     x.cod_tipo_clas = '01'
                       	 	   AND     a.clas_oid_clas = y.oid_clas
                       	 	   AND     y.cod_clas = '01'
                       	 	   AND     b.sbti_oid_subt_clie = x.sbti_oid_subt_clie
                       	 	   AND     x.oid_tipo_clas = y.tccl_oid_tipo_clas
                         );

             END IF;

             -- Desasignar lider
             UPDATE    ZON_SECCI
                SET    CLIE_OID_CLIE = null
             WHERE     OID_SECC = vnOidSeccion;

           END LOOP; -- Fin del Cursor Paginado de LIDER PENDIENTE

         END IF; -- liderPendienteList.COUNT > 0

         EXIT WHEN C_DESVI_LIDER%NOTFOUND;
       END LOOP;
       CLOSE C_DESVI_LIDER;

       -- Cursor de ASIGNAR LIDERES -----------------------------------
       OPEN C_ASIGN_LIDER(
         vnOidPais,
         vnOidPeriodo
       );
       LOOP

         FETCH C_ASIGN_LIDER BULK COLLECT INTO asignarLiderList LIMIT W_FILAS;
         IF asignarLiderList.COUNT > 0 THEN

           -- Recorrer el Cursor Paginado de ASIGNAR LIDER
           FOR i IN asignarLiderList.FIRST .. asignarLiderList.LAST LOOP

             -- 1 Capturar Variables requeridas ---------------------

             -- Obtener oidCliente
             BEGIN
               vnOidCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(asignarLiderList(i).codLidere);
             EXCEPTION
               WHEN OTHERS THEN
               vnOidCliente := NULL;
             END;

             -- Obtener oidSeccion
             select    oid_secc
               into    vnOidSeccion
             from      zon_secci
             where     zzon_oid_zona = gen_pkg_gener.GEN_FN_DEVUELVE_ID_ZONA(
                         psCodigoPais,
                         'T',
                         'VD',
                         asignarLiderList(i).codRegion,
                         asignarLiderList(i).codZonass
                       )
             and       cod_secc = asignarLiderList(i).codSeccio
             and       ind_acti = 1;

             -- 2 Si periodo desde del historio es igual ------------
             --   al periodo proceso
             IF  asignarLiderList(i).oidPerDes = vnOidPeriodo AND vnOidCliente IS NOT NULL THEN

               -- 2.1 Leer historico. Si existe datos eliminar historico
               --     sino realizar proceso de asignar lider
               select    count(1)
                 into    vnExiste
               from      zon_histo_geren zh
               where     zh.pais_oid_pais = vnOidPais
                 and     zh.gere = asignarLiderList(i).codLidere
                 and     zh.perd_oid_peri_hast = vnOidPeriodoAnt
                 and     zh.ind_desv_auto >= 1;

               IF vnExiste > 0 THEN

                 delete
                 from      zon_histo_geren zh
                 where     zh.oid_hist_gere = asignarLiderList(i).oidHisGer;

               ELSE

                 -- Capturar consultora de la seccion
                 select    zs.clie_oid_clie
                   into    vnOidClienteSec
                 from      zon_secci zs,
                           zon_zona zz
                 where     zz.oid_zona = zs.zzon_oid_zona
                   and     zz.cod_zona = asignarLiderList(i).codZonass
                   and     zs.cod_secc = asignarLiderList(i).codSeccio
                   and     zs.ind_acti = 1;


                 -- Si existe consultora. se valida si tiene
                 -- clasificacion lider y si lo tiene se elimina
                 IF vnOidClienteSec IS NOT NULL THEN

                   SELECT    COUNT(1)
                     INTO    vnExiste
                   FROM      mae_clien_clasi a,
                   	         mae_clien_tipo_subti b,
                   				   MAE_TIPO_CLASI_CLIEN x,
                   				   MAE_CLASI y
                   WHERE     a.ctsu_oid_clie_tipo_subt = b.Oid_Clie_Tipo_Subt
                     AND     b.clie_oid_clie = vnOidClienteSec
                     AND     b.Ind_Ppal = 1
                     AND     a.tccl_oid_tipo_clasi = x.oid_tipo_clas
                     AND     x.cod_tipo_clas = '01'
                     AND     a.clas_oid_clas = y.oid_clas
                     AND     y.cod_clas = '01'
                     AND     b.sbti_oid_subt_clie = x.sbti_oid_subt_clie
                     AND     x.oid_tipo_clas = y.tccl_oid_tipo_clas;

                   IF vnExiste > 0 THEN

                     DELETE
                     FROM      mae_clien_clasi
                     WHERE     oid_clie_clas IN (
                     	           SELECT    a.oid_clie_clas
                     			       FROM      mae_clien_clasi a,
                                   		     mae_clien_tipo_subti b,
                                  		     MAE_TIPO_CLASI_CLIEN x,
                                  		     MAE_CLASI y,
                                  		     mae_clien m
                      			     WHERE     a.ctsu_oid_clie_tipo_subt = b.Oid_Clie_Tipo_Subt
                            	     AND     b.clie_oid_clie = m.oid_clie
                            	     and     m.oid_clie = vnOidClienteSec
                             	     AND     b.Ind_Ppal = 1
                             	 	   AND     a.tccl_oid_tipo_clasi = x.oid_tipo_clas
                             	 	   AND     x.cod_tipo_clas = '01'
                             	 	   AND     a.clas_oid_clas = y.oid_clas
                             	 	   AND     y.cod_clas = '01'
                             	 	   AND     b.sbti_oid_subt_clie = x.sbti_oid_subt_clie
                             	 	   AND     x.oid_tipo_clas = y.tccl_oid_tipo_clas
                               );

                   END IF;

                 END IF; -- FIN - Si existe consultora. se valida

                 -- Asignar Lider
                 UPDATE    ZON_SECCI
                    SET    CLIE_OID_CLIE = vnOidCliente
                 WHERE     OID_SECC = vnOidSeccion;

                 -- Insertar clasificacion de lider a la consultora
                 -- que se esta asignando como lider. Se valida si ya
                 -- tiene clasificacion de lider

                 SELECT    COUNT(1)
                   INTO    vnExiste
                 FROM      mae_clien_clasi a,
                 	         mae_clien_tipo_subti b,
                  				 MAE_TIPO_CLASI_CLIEN x,
                   				 MAE_CLASI y
                 WHERE     a.ctsu_oid_clie_tipo_subt = b.Oid_Clie_Tipo_Subt
                   AND     b.clie_oid_clie = vnOidCliente
                   AND     b.Ind_Ppal = 1
                   AND     a.tccl_oid_tipo_clasi = x.oid_tipo_clas
                   AND     x.cod_tipo_clas = '01'
                   AND     a.clas_oid_clas = y.oid_clas
                   AND     y.cod_clas = '01'
                   AND     b.sbti_oid_subt_clie = x.sbti_oid_subt_clie
                   AND     x.oid_tipo_clas = y.tccl_oid_tipo_clas;

                 IF vnExiste = 0 THEN

                   BEGIN
                     INSERT INTO MAE_CLIEN_CLASI (
                       OID_CLIE_CLAS,
                       CTSU_OID_CLIE_TIPO_SUBT,
                       CLAS_OID_CLAS,
                       PERD_OID_PERI,
                       TCCL_OID_TIPO_CLASI,
                       FEC_CLAS,
                       IND_PPAL,
                       FEC_ULTI_ACTU
                     ) VALUES (
                       Mae_Clcl_Seq.NEXTVAL,
                       (
                         SELECT    x.oid_clie_tipo_subt
                         FROM      mae_clien_tipo_subti x
                         WHERE     x.ind_ppal = 1
                           AND     x.clie_oid_clie = vnOidCliente
                       ),
                     	 (
                     	   SELECT    y.oid_clas
                    		 FROM      mae_clien_tipo_subti b,
                    	 			       MAE_TIPO_CLASI_CLIEN x,
                    				       MAE_CLASI y
                    		 WHERE     b.clie_oid_clie = vnOidCliente
                    	     AND     b.Ind_Ppal = 1
                      	   AND     x.cod_tipo_clas = '01'
                      	   AND     y.cod_clas = '01'
                      	   AND     b.sbti_oid_subt_clie = x.sbti_oid_subt_clie
                      	   AND     x.oid_tipo_clas = y.tccl_oid_tipo_clas
                       ),
                    	 vnOidPeriodo,
                    	 (
                    	   SELECT    x.OID_TIPO_CLAS
                    	   FROM      mae_clien_tipo_subti b ,
                    	 			       MAE_TIPO_CLASI_CLIEN x ,
                     				       MAE_CLASI y
                         WHERE     b.clie_oid_clie = vnOidCliente
                           AND     b.Ind_Ppal = 1
                           AND     x.cod_tipo_clas = '01'
                           AND     y.cod_clas = '01'
                           AND     b.sbti_oid_subt_clie = x.sbti_oid_subt_clie
                           AND     x.oid_tipo_clas = y.tccl_oid_tipo_clas
                    	 ),
                       trunc(sysdate),
                       0,
                       null
                     );
                   EXCEPTION
                     WHEN OTHERS THEN
                       RAISE e_insertClaLid;
                   END;

                 END IF; -- FIN - Insertar clasificacion de lider

               END IF; -- FIN - 2.1 Leer historico

             END IF; -- FIN - 2 Si periodo desde del historio


           END LOOP; -- Fin del Cursor Paginado de ASIGNAR LIDER

         END IF; -- asignarLiderList.COUNT > 0

         EXIT WHEN C_ASIGN_LIDER%NOTFOUND;
       END LOOP;
       CLOSE C_ASIGN_LIDER;

       OPEN C_ZON_HISTO_GEREN(vnOidPais,vnOidPeriodoAnt,vdFechaFinPeriodoAnt);
       LOOP
            FETCH C_ZON_HISTO_GEREN BULK COLLECT INTO zonHistoGeren LIMIT W_FILAS;

            IF zonHistoGeren.COUNT > 0 THEN
               FORALL i IN zonHistoGeren.FIRST .. zonHistoGeren.LAST
               UPDATE ZON_HISTO_GEREN
               SET FEC_HAST = vdFechaFinPeriodoAnt,
                   USU_MODI = pscodigoUsuario,
                   FEC_MODI = SYSDATE
               WHERE PAIS_OID_PAIS = vnOidPais
               AND OID_HIST_GERE = zonHistoGeren(i).OID_HIST_GERE
               AND MARC_OID_MARC = zonHistoGeren(i).MARC_OID_MARC
               AND CANA_OID_CANA = zonHistoGeren(i).CANA_OID_CANA
               AND UA = zonHistoGeren(i).UA;
            END IF;
       EXIT WHEN C_ZON_HISTO_GEREN%NOTFOUND;
       END LOOP;
       CLOSE C_ZON_HISTO_GEREN;
     END IF; -- FIN - Validar si se debe ejecutar el proceso

     IF vsValParametro = 0 THEN
        OPEN C_ZON_HISTO_GEREN(vnOidPais,vnOidPeriodoAnt,vdFechaFinPeriodoAnt);
         LOOP
              FETCH C_ZON_HISTO_GEREN BULK COLLECT INTO zonHistoGeren LIMIT W_FILAS;

              IF zonHistoGeren.COUNT > 0 THEN
                 FORALL i IN zonHistoGeren.FIRST .. zonHistoGeren.LAST
                 UPDATE ZON_HISTO_GEREN
                 SET FEC_HAST = vdFechaFinPeriodoAnt,
                     USU_MODI = pscodigoUsuario,
                     FEC_MODI = SYSDATE
                 WHERE PAIS_OID_PAIS = vnOidPais
                 AND OID_HIST_GERE = zonHistoGeren(i).OID_HIST_GERE
                 AND MARC_OID_MARC = zonHistoGeren(i).MARC_OID_MARC
                 AND CANA_OID_CANA = zonHistoGeren(i).CANA_OID_CANA
                 AND UA = zonHistoGeren(i).UA;
              END IF;
         EXIT WHEN C_ZON_HISTO_GEREN%NOTFOUND;
        END LOOP;
        CLOSE C_ZON_HISTO_GEREN;
     END IF;

   EXCEPTION
     WHEN e_insertClaLid THEN
       RAISE_APPLICATION_ERROR(-20001, 'El tipo-subtipo de la consultora no es v�lido para la clasificaci�n de L�der');
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_ASIGN_DESAS_LIDER: ' || ls_sqlerrm);

   END LET_PR_ASIGN_DESAS_LIDER;

   /*************************************************************************
    Descripcion : Proceso de Caluclo de Pedidos Objetivos por Rezonificacion
    Fecha Creacion : 08/09/2011
    Autor : Jesse Rios
    *************************************************************************/
   PROCEDURE LET_PR_CALCU_PEDID_OBJET_REZON(psCodigoPais VARCHAR2,
                                            psCodigoPeriodo VARCHAR2,
                                            psCodigoUsuario VARCHAR2)IS

       CURSOR C_CONCURSOS(campanaSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE,campanaSubSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE) IS
       SELECT COD_CONC,CAM_INIC,CAM_FINA,'SSB' AS TIP_CONC
       FROM LET_PARAM_CONCU_LIDER
       WHERE PAIS_COD_PAIS = psCodigoPais
       AND (cam_inic = campanaSiguiente or cam_inic = campanaSubSiguiente)
       AND EST_REGI = 1
       UNION
       SELECT COD_CONC,CAM_INIC,CAM_FINA,'V' AS TIP_CONC
       FROM LET_PARAM_CONCU_LIDER
       WHERE PAIS_COD_PAIS = psCodigoPais
       AND psCodigoPeriodo >= cam_inic
       AND psCodigoPeriodo <= cam_fina
       AND EST_REGI = 1;

       TYPE concursoRecord IS RECORD(
        COD_CONC LET_PARAM_CONCU_LIDER.COD_CONC%TYPE,
        CAM_INIC LET_PARAM_CONCU_LIDER.CAM_INIC%TYPE,
        CAM_FINA LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE,
        TIP_CONC VARCHAR2(3)
       );

       TYPE concursoTab IS TABLE OF concursoRecord;
       concurso concursoTab;

       CURSOR C_ZONAS_ACTIVAS IS
       SELECT DISTINCT z.cod_zona
       FROM MAE_CLIEN_DATOS_ADICI AD,
            mae_clien_unida_admin UA,
            zon_terri_admin ZA,
            ZON_SECCI SC,
            ZON_ZONA Z
       WHERE ad.esta_oid_esta_clie IN (2,3,4,6,8)
       AND ad.clie_oid_clie = ua.clie_oid_clie AND ua.ind_acti = 1
       AND ua.ztad_oid_terr_admi = za.oid_terr_admi
       AND za.zscc_oid_secc = sc.oid_secc
       AND sc.zzon_oid_zona = z.oid_zona
       AND z.cod_zona IN (SELECT COD_ZONA FROM LET_GTT_ZONAS_REZON WHERE COD_PAIS = psCodigoPais)
       ORDER BY z.cod_zona;

       TYPE zonasActivasTab IS TABLE OF ZON_ZONA.COD_ZONA%TYPE;
       zonasActivas zonasActivasTab;

       CURSOR C_SECCIONES_ZONAS_ACTIVAS(codigoZona ZON_ZONA.COD_ZONA%TYPE) IS
       SELECT DISTINCT sc.cod_secc
       FROM MAE_CLIEN_DATOS_ADICI AD,
            mae_clien_unida_admin UA,
            zon_terri_admin ZA,
            ZON_SECCI SC,
            ZON_ZONA Z
       WHERE ad.esta_oid_esta_clie IN (2,3,4,6,8)
       AND ad.clie_oid_clie = ua.clie_oid_clie AND ua.ind_acti = 1
       AND ua.ztad_oid_terr_admi = za.oid_terr_admi
       AND za.zscc_oid_secc = sc.oid_secc
       AND sc.zzon_oid_zona = z.oid_zona
       AND z.cod_zona = codigoZona
       ORDER BY sc.cod_secc;

       TYPE seccionesZonasActivasTab IS TABLE OF ZON_SECCI.COD_SECC%TYPE;
       seccionesZonasActivas seccionesZonasActivasTab;

       CURSOR C_ZONAS_SELECCIONADAS IS
       SELECT COD_ZONA FROM LET_GTT_ZONAS_REZON WHERE COD_PAIS = psCodigoPais;

       TYPE zonasSeleccionadasTab IS TABLE OF LET_GTT_ZONAS_REZON.COD_ZONA%TYPE;
       zonasSeleccionadas zonasSeleccionadasTab;

       CURSOR C_PESOS_SECCIONES_ZONAS(codigoZona LET_GTT_SECCI_PESOS.COD_ZONA%TYPE) IS
       SELECT COD_PAIS,COD_ZONA,COD_SECC,VAL_PESO,ACT_INIC
       FROM LET_GTT_SECCI_PESOS
       WHERE COD_PAIS = psCodigoPais
       AND COD_ZONA = codigoZona;

       TYPE pesosSeccionesZonasTab IS TABLE OF LET_GTT_SECCI_PESOS%ROWTYPE;
       pesosSeccionesZonas pesosSeccionesZonasTab;

       vnOidPais       SEG_PAIS.OID_PAIS%TYPE;
       vnOidMarca      SEG_MARCA.OID_MARC%TYPE;
       vnOidCanal      SEG_CANAL.OID_CANA%TYPE;
       vsCampanaSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE;
       vsCampanaSubSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE;
       vnContZona    NATURAL;
       vnContSeccion NATURAL;
       vnPesoSeccion LET_GTT_SECCI_PESOS.VAL_PESO%TYPE;
       vsCodigoPeriodoInic LET_PARAM_CONCU_LIDER.CAM_INIC%TYPE;
       vsCodigoPeriodoFin  LET_PARAM_CONCU_LIDER.CAM_FINA%TYPE;
       vnContador INTEGER;
       vsCodigoPeriodoProceso SEG_PERIO_CORPO.COD_PERI%TYPE;
       vnNumCampa INTEGER;
       vnNumPedi  INT_SAB_VENTA_PREVI_ZONA.NUM_PEDI%TYPE;
       vnPedidoObjetivo LET_OBJET_PEDID_SECCI.OBJ_PEDI%TYPE;
       vsIndActi LET_OBJET_PEDID_SECCI.IND_ACTI%TYPE;
       vnActFina LET_OBJET_PEDID_SECCI.ACT_FINA%TYPE;
       vsCodigoRegion ZON_REGIO.COD_REGI%TYPE;
       vnExisteObjetivoPedido NATURAL;
       vsValorParametroPais BAS_PARAM_PAIS.VAL_PARA%TYPE;

   BEGIN
       vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
       vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA('T');
       vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
       vsCampanaSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,1);
       vsCampanaSubSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,2);

       SELECT VAL_PARA
       INTO vsValorParametroPais
		   FROM  BAS_PARAM_PAIS
		   WHERE COD_PAIS = psCodigoPais
		   AND   COD_SIST = 'LET'
		   AND   COD_PARA = '003';

       OPEN C_ZONAS_ACTIVAS;
          LOOP
          FETCH C_ZONAS_ACTIVAS BULK COLLECT INTO zonasActivas LIMIT W_FILAS;
            IF zonasActivas.COUNT > 0 THEN
               FOR z IN zonasActivas.FIRST .. zonasActivas.LAST LOOP
                 SELECT COUNT(1) AS CONT_ZONA
                 INTO vnContZona
                 FROM MAE_CLIEN_DATOS_ADICI AD,
                      mae_clien_unida_admin UA,
                      zon_terri_admin ZA,
                      ZON_SECCI SC,
                      ZON_ZONA Z
                 WHERE ad.esta_oid_esta_clie IN (2,3,4,6,8)
                 AND ad.clie_oid_clie = ua.clie_oid_clie AND ua.ind_acti = 1
                 AND ua.ztad_oid_terr_admi = za.oid_terr_admi
                 AND za.zscc_oid_secc = sc.oid_secc
                 AND sc.zzon_oid_zona = z.oid_zona
                 AND z.cod_zona = zonasActivas(z)
                 ORDER BY z.cod_zona;

                 OPEN C_SECCIONES_ZONAS_ACTIVAS(zonasActivas(z));
                 LOOP
                   FETCH C_SECCIONES_ZONAS_ACTIVAS BULK COLLECT INTO seccionesZonasActivas LIMIT W_FILAS;

                   IF seccionesZonasActivas.COUNT > 0 THEN
                      FOR s IN seccionesZonasActivas.FIRST .. seccionesZonasActivas.LAST LOOP
                          SELECT COUNT(1) AS CONT_SECC
                          INTO vnContSeccion
                          FROM MAE_CLIEN_DATOS_ADICI AD,
                               mae_clien_unida_admin UA,
                               zon_terri_admin ZA,
                               ZON_SECCI SC,
                               ZON_ZONA Z
                          WHERE ad.esta_oid_esta_clie IN (2,3,4,6,8)
                          AND ad.clie_oid_clie = ua.clie_oid_clie AND ua.ind_acti = 1
                          AND ua.ztad_oid_terr_admi = za.oid_terr_admi
                          AND za.zscc_oid_secc = sc.oid_secc
                          AND sc.zzon_oid_zona = z.oid_zona
                          AND z.cod_zona = zonasActivas(z)
                          AND sc.cod_secc = seccionesZonasActivas(s)
                          ORDER BY sc.cod_secc;

                          vnPesoSeccion := vnContSeccion / vnContZona;

                          INSERT INTO LET_GTT_SECCI_PESOS(COD_PAIS,COD_ZONA,COD_SECC,VAL_PESO,ACT_INIC)
                          VALUES(psCodigoPais,zonasActivas(z),seccionesZonasActivas(s),vnPesoSeccion,vnContSeccion);

                      END LOOP;
                   END IF;
                 EXIT WHEN C_SECCIONES_ZONAS_ACTIVAS%NOTFOUND;
                 END LOOP;
                 CLOSE C_SECCIONES_ZONAS_ACTIVAS;

               END LOOP;
            END IF;
          EXIT WHEN C_ZONAS_ACTIVAS%NOTFOUND;
          END LOOP;
       CLOSE C_ZONAS_ACTIVAS;

       OPEN C_CONCURSOS(vsCampanaSiguiente,vsCampanaSubSiguiente);
          LOOP
          FETCH C_CONCURSOS BULK COLLECT INTO concurso LIMIT W_FILAS;
            IF concurso.COUNT > 0 THEN
               FOR i IN concurso.FIRST .. concurso.LAST LOOP

                 IF concurso(i).TIP_CONC = CONCURSO_SIG_SUBSIG THEN
                    vsCodigoPeriodoInic := concurso(i).CAM_INIC;
                 END IF;

                 IF concurso(i).TIP_CONC = CONCURSO_VIGENTE THEN
                    vsCodigoPeriodoInic := psCodigoPeriodo;
                 END IF;

                 vsCodigoPeriodoFin := concurso(i).CAM_FINA;

                 OPEN C_ZONAS_SELECCIONADAS;
                 LOOP
                 FETCH C_ZONAS_SELECCIONADAS BULK COLLECT INTO zonasSeleccionadas LIMIT W_FILAS;
                   IF zonasSeleccionadas.COUNT > 0 THEN
                      FOR c IN zonasSeleccionadas.FIRST .. zonasSeleccionadas.LAST LOOP
                         DELETE FROM LET_METAS_LIDER_CAMPA
                         WHERE PAIS_COD_PAIS = psCodigoPais
                         AND CONC_COD_CONC = concurso(i).COD_CONC
                         AND COD_ZONA = zonasSeleccionadas(c)
                         AND CAM_EVAL >= psCodigoPeriodo;

                         DELETE FROM LET_OBJET_PEDID_SECCI
                         WHERE PAIS_COD_PAIS = psCodigoPais
                         AND CONC_COD_CONC = concurso(i).COD_CONC
                         AND COD_ZONA = zonasSeleccionadas(c)
                         AND CAM_PROC >= psCodigoPeriodo;

                         vnNumCampa := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(vsCodigoPeriodoInic,vsCodigoPeriodoFin,vnOidPais,vnOidMarca,vnOidCanal);

                         vnContador := 0;

                         LOOP
                            vsCodigoPeriodoProceso := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(vsCodigoPeriodoInic,vnOidPais,vnOidMarca,vnOidCanal,vnContador);
                            BEGIN
                              SELECT NUM_PEDI
                              INTO vnNumPedi
                              FROM INT_SAB_VENTA_PREVI_ZONA
                              WHERE PAIS_COD_PAIS = psCodigoPais
                              AND COD_ZONA = zonasSeleccionadas(c)
                              AND COD_PERI = vsCodigoPeriodoProceso
                              ORDER BY COD_ZONA,COD_PERI;
                            EXCEPTION
                            WHEN NO_DATA_FOUND THEN
                               EXIT;
                            END;

                            OPEN C_PESOS_SECCIONES_ZONAS(zonasSeleccionadas(c));
                            LOOP
                            FETCH C_PESOS_SECCIONES_ZONAS BULK COLLECT INTO pesosSeccionesZonas LIMIT W_FILAS;
                              IF pesosSeccionesZonas.COUNT > 0 THEN
                                 FOR x IN pesosSeccionesZonas.FIRST .. pesosSeccionesZonas.LAST LOOP
                                   vnPedidoObjetivo := CEIL(vnNumPedi * pesosSeccionesZonas(x).VAL_PESO);
                                   vsIndActi := 'Z';
                                   vnActFina := pesosSeccionesZonas(x).ACT_INIC;

                                   SELECT re.COD_REGI
                                   INTO vsCodigoRegion
                                   FROM ZON_REGIO re,
                                        ZON_ZONA zo
                                   WHERE zo.PAIS_OID_PAIS = vnOidPais
                                   AND re.oid_regi = zo.zorg_oid_regi
                                   AND zo.COD_ZONA = pesosSeccionesZonas(x).COD_ZONA;

                                   SELECT COUNT(1)
                                   INTO vnExisteObjetivoPedido
                                   FROM LET_OBJET_PEDID_SECCI
                                   WHERE PAIS_COD_PAIS = psCodigoPais
                                   AND CONC_COD_CONC = concurso(i).COD_CONC
                                   AND CAM_PROC = vsCodigoPeriodoProceso
                                   AND COD_REGI = vsCodigoRegion
                                   AND COD_ZONA = pesosSeccionesZonas(x).COD_ZONA
                                   AND COD_SECC = pesosSeccionesZonas(x).COD_SECC;

                                   IF vnExisteObjetivoPedido = 0 THEN
                                      INSERT INTO LET_OBJET_PEDID_SECCI(
                                       PAIS_COD_PAIS,
                                       CONC_COD_CONC,
                                       CAM_PROC,
                                       COD_REGI,
                                       COD_ZONA,
                                       COD_SECC,
                                       OBJ_PEDI,
                                       PES_SECC,
                                       ACT_FINA,
                                       USU_MODI,
                                       FEC_MODI,
                                       EST_REGI,
                                       IND_ACTI)
                                      VALUES(
                                       psCodigoPais,
                                       concurso(i).COD_CONC,
                                       vsCodigoPeriodoProceso,
                                       vsCodigoRegion,
                                       pesosSeccionesZonas(x).COD_ZONA,
                                       pesosSeccionesZonas(x).COD_SECC,
                                       vnPedidoObjetivo,
                                       pesosSeccionesZonas(x).VAL_PESO,
                                       pesosSeccionesZonas(x).ACT_INIC,
                                       psCodigoUsuario,
                                       SYSDATE,
                                       '1',
                                       vsIndActi
                                      );

                                      -- Invocacion al calculo de meta de lideres por campa�a
                                      LET_PR_CALCU_METAS_LIDER_CAMPA(
                                         psCodigoPais,
                                         concurso(i).COD_CONC,
                                         vsCodigoPeriodoProceso,
                                         vsCodigoRegion,
                                         pesosSeccionesZonas(x).COD_ZONA,
                                         pesosSeccionesZonas(x).COD_SECC,
                                         vnPedidoObjetivo,
                                         psCodigoUsuario
                                       );
                                   ELSE
                                      UPDATE LET_OBJET_PEDID_SECCI
                                      SET OBJ_PEDI = vnPedidoObjetivo,
                                          IND_ACTI = vsIndActi,
                                          ACT_FINA = pesosSeccionesZonas(x).ACT_INIC
                                      WHERE PAIS_COD_PAIS = psCodigoPais
                                      AND CONC_COD_CONC = concurso(i).COD_CONC
                                      AND CAM_PROC = vsCodigoPeriodoProceso
                                      AND COD_REGI = vsCodigoRegion
                                      AND COD_ZONA = pesosSeccionesZonas(x).COD_ZONA
                                      AND COD_SECC = pesosSeccionesZonas(x).COD_SECC;

                                      -- Invocacion al calculo de meta de lideres por campa�a
                                      LET_PR_CALCU_METAS_LIDER_CAMPA(
                                         psCodigoPais,
                                         concurso(i).COD_CONC,
                                         vsCodigoPeriodoProceso,
                                         vsCodigoRegion,
                                         pesosSeccionesZonas(x).COD_ZONA,
                                         pesosSeccionesZonas(x).COD_SECC,
                                         vnPedidoObjetivo,
                                         psCodigoUsuario
                                       );
                                   END IF;

                                 END LOOP;
                              END IF;
                            EXIT WHEN C_PESOS_SECCIONES_ZONAS%NOTFOUND;
                            END LOOP;
                            CLOSE C_PESOS_SECCIONES_ZONAS;
                            vnContador := vnContador + 1;

                         EXIT WHEN vnNumCampa = vnContador;
                         END LOOP;

                      END LOOP;
                   END IF;
                 EXIT WHEN C_ZONAS_SELECCIONADAS%NOTFOUND;
                 END LOOP;
                 CLOSE C_ZONAS_SELECCIONADAS;
               END LOOP;
            END IF;
          EXIT WHEN C_CONCURSOS%NOTFOUND;
          END LOOP;
       CLOSE C_CONCURSOS;

   EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_PEDID_OBJET_REZON: ' || ls_sqlerrm);
   END LET_PR_CALCU_PEDID_OBJET_REZON;

   /*************************************************************************
    Descripcion : Proceso de Desvinculacion por Lider
    Fecha Creacion : 27/10/2011
    Autor : Jesse James Rios Franco
    *************************************************************************/
   PROCEDURE LET_PR_PROCE_DESVI_LIDER(psCodigoPais VARCHAR2,
                                      pnOidSeccion NUMBER,
                                      psUA VARCHAR2,
                                      pnOidCliente NUMBER,
                                      pnOidPeriodo NUMBER,
                                      pdFechaProceso DATE,
                                      pnIndicadorDesvinculacion NUMBER,
                                      psCodigoUsuario VARCHAR2)IS

     vnOidHistoGeren ZON_HISTO_GEREN.OID_HIST_GERE%TYPE;
     vnContar NUMBER;
      /* INI JJ PER-SiCC-2012-0201 */
     vsCogidoLider ZON_HISTO_GEREN.GERE%TYPE;
      /* FIN JJ PER-SiCC-2012-0201 */
   BEGIN
    --se procede a la desvinculacion
      /* INI JJ PER-SiCC-2012-0201 */
      vsCogidoLider := GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(pnOidCliente);
      /* FIN JJ PER-SiCC-2012-0201 */

      BEGIN

       SELECT    count(1)
         INTO    vnContar
       FROM      ZON_HISTO_GEREN ger,
                 SEG_MARCA mar,
                 SEG_CANAL can,
                 SEG_PAIS pai
       WHERE     ger.PAIS_OID_PAIS = pai.OID_PAIS
         AND     ger.MARC_OID_MARC = mar.OID_MARC
         AND     ger.CANA_OID_CANA = can.OID_CANA
         AND     pai.COD_PAIS = psCodigoPais
         AND     mar.COD_MARC = 'T'
         AND     can.COD_CANA = 'VD'
         AND     UA = psUA
         /* INI JJ PER-SiCC-2012-0201 */
         AND     GERE = vsCogidoLider
         /* FIN JJ PER-SiCC-2012-0201 */
         AND     pnOidPeriodo >= ger.perd_oid_peri_desd
         AND     (
                   pnOidPeriodo <= ger.perd_oid_peri_hast OR
                   ger.perd_oid_peri_hast is null
                 );

       IF vnContar > 0 THEN

         SELECT    ger.Oid_Hist_Gere
           INTO    vnOidHistoGeren
         FROM      ZON_HISTO_GEREN ger,
                   SEG_MARCA mar,
                   SEG_CANAL can,
                   SEG_PAIS pai
         WHERE     ger.PAIS_OID_PAIS = pai.OID_PAIS
           AND     ger.MARC_OID_MARC = mar.OID_MARC
           AND     ger.CANA_OID_CANA = can.OID_CANA
           AND     pai.COD_PAIS = psCodigoPais
           AND     mar.COD_MARC = 'T'
           AND     can.COD_CANA = 'VD'
           AND     UA = psUA
           /* INI JJ PER-SiCC-2012-0201 */
           AND     GERE = vsCogidoLider
           /* FIN JJ PER-SiCC-2012-0201 */
           AND     pnOidPeriodo >= ger.perd_oid_peri_desd
           AND     (
                     pnOidPeriodo <= ger.perd_oid_peri_hast OR
                     ger.perd_oid_peri_hast is null
                   );

         UPDATE    ZON_HISTO_GEREN
            SET    FEC_HAST = pdFechaProceso,
                   PERD_OID_PERI_HAST = pnOidPeriodo,
                   IND_DESV_AUTO = pnIndicadorDesvinculacion,
                   USU_MODI = psCodigoUsuario,
                   FEC_MODI = SYSDATE
         WHERE     OID_HIST_GERE = vnOidHistoGeren;

       END IF;
      EXCEPTION
      WHEN OTHERS THEN
           NULL;
      END;

   EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_PROCE_DESVI_LIDER: ' || ls_sqlerrm);
   END LET_PR_PROCE_DESVI_LIDER;

   /*************************************************************************
    Descripcion : Actualiza la Clasificacion Lider
    Fecha Creacion : 24/04/2012
    Autor : Jesse James Rios Franco
    *************************************************************************/
   PROCEDURE LET_PR_ACTUA_CLASI_LIDER(psCodigoPais VARCHAR2,
                                      psTransaccion NUMBER,
                                      psTipoOperacion NUMBER,
                                      psCodigoPeriodo VARCHAR2,
                                      psCodigoCliente VARCHAR2,
                                      psCodigoUsuario VARCHAR2) IS

     CURSOR C_PARAM_CLASI_LIDER IS
     select PAIS_COD_PAIS,
            COD_CLAS_LIDE,
            NUM_SECU_CLAS,
            NUM_MAXI_CAMP_CLAS,
            NUM_CAMP_ANTE,
            NUM_TOTA_CAMP_EVAL,
            NUM_MAXI_CAMP_LIDE,
            NUM_MINI_CAMP_LIDE,
            IND_ACTI_CLAS,
            EST_REGI
     from LET_PARAM_CLASI_LIDER
     where PAIS_COD_PAIS = psCodigoPais
     and   EST_REGI = 1
     ORDER BY COD_CLAS_LIDE;

     TYPE paramClasiLiderTab IS TABLE OF LET_PARAM_CLASI_LIDER%ROWTYPE;
     paramClasiLider paramClasiLiderTab;

     CURSOR C_LISTA_CONSULTORAS(oidPais SEG_PAIS.OID_PAIS%TYPE,oidPeriodo CRA_PERIO.OID_PERI%TYPE) IS
     SELECT GERE
     FROM ZON_HISTO_GEREN
     WHERE PAIS_OID_PAIS = oidPais
     AND LENGTH(UA) = 9
     AND (oidPeriodo >= PERD_OID_PERI_DESD)
     AND (oidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

     TYPE listaConsultorasTab IS TABLE OF ZON_HISTO_GEREN.GERE%TYPE;
     listaConsultoras listaConsultorasTab;

     vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
     vnOidMarca              SEG_MARCA.OID_MARC%TYPE;
     vnOidCanal              SEG_CANAL.OID_CANA%TYPE;
     vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;
     vsCodigoPeriodoAnterior SEG_PERIO_CORPO.COD_PERI%TYPE;

     vnEncontro NATURAL;
     vnEncontroLider NATURAL;
     vnContadorLiderCamp NATURAL;

     vsNCampAnteriorInicial SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnNumTotalCampEval     LET_PARAM_CLASI_LIDER.NUM_TOTA_CAMP_EVAL%TYPE;
     vnNumCampAnte          LET_PARAM_CLASI_LIDER.NUM_CAMP_ANTE%TYPE;
     vnNumMaxCamp           LET_PARAM_CLASI_LIDER.NUM_MAXI_CAMP_LIDE%TYPE;
     vnNumMinCamp           LET_PARAM_CLASI_LIDER.NUM_MINI_CAMP_LIDE%TYPE;
     vnNumMaxCampClasi      LET_PARAM_CLASI_LIDER.NUM_MAXI_CAMP_CLAS%TYPE;
     vsCampProcesando       SEG_PERIO_CORPO.COD_PERI%TYPE;

     vbEvaluacionParamNumCampAnte     BOOLEAN;
     vbEvaluacionParamNumMaxCamp      BOOLEAN;
     vbEvaluacionParamNumMinCamp      BOOLEAN;

     vsCodigoClasificacion      LET_PARAM_CLASI_LIDER.COD_CLAS_LIDE%TYPE;
     vnSubClasiLider            LET_HISTO_CLASI_LIDER.COD_SUB_CLAS_LIDE%TYPE;
     vnNumSecuClasificacion     LET_PARAM_CLASI_LIDER.NUM_SECU_CLAS%TYPE;
      /* INI JJ PER-SiCC-2012-0540*/
     vsCodigoTipoClasificacionMAE LET_HISTO_CLASI_LIDER.COD_TIPO_CLAS%TYPE;
     vsCodigoClasificacionMAE LET_HISTO_CLASI_LIDER.COD_CLAS%TYPE;
      /* FIN JJ PER-SiCC-2012-0540*/
     vbRegistroClasificacion           BOOLEAN;
   BEGIN

        vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
        vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA('T');
        vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL('VD');
        vsCodigoPeriodoAnterior := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-1);

     /*Llamada en la Asignaci�n de L�deres */

        IF psTransaccion = 1 AND psTipoOperacion = 1 THEN
          /* JPCC - busca si tiene clasificacion en campa�a actual*/
          vbRegistroClasificacion := FALSE;

           SELECT COUNT(1)
           INTO vnEncontro
           FROM LET_HISTO_CLASI_LIDER
           WHERE PAIS_COD_PAIS = psCodigoPais
           AND COD_LIDE = psCodigoCliente
           AND CAM_CLAS = psCodigoPeriodo
           and EST_REGI = 1;

           IF vnEncontro = 0 THEN

           SELECT COUNT(1)
           INTO vnEncontro
           FROM LET_HISTO_CLASI_LIDER
           WHERE PAIS_COD_PAIS = psCodigoPais
           AND COD_LIDE = psCodigoCliente
           AND CAM_CLAS = vsCodigoPeriodoAnterior
           and EST_REGI = 1;

           IF vnEncontro = 0 THEN

              FOR paramClasiLider IN C_PARAM_CLASI_LIDER LOOP

                vbEvaluacionParamNumCampAnte := FALSE;
                vbEvaluacionParamNumMaxCamp := FALSE;
                vbEvaluacionParamNumMinCamp := FALSE;

                vnNumCampAnte := paramClasiLider.Num_Camp_Ante;

                IF vnNumCampAnte > 0 THEN

                  vsNCampAnteriorInicial := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-paramClasiLider.Num_Camp_Ante);

                  vnEncontroLider := 0;

                  FOR x IN 0 .. (paramClasiLider.Num_Camp_Ante-1) LOOP

                      vsCampProcesando := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsNCampAnteriorInicial,vnOidPais,vnOidMarca,vnOidCanal,x);
                      vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampProcesando);

                      SELECT COUNT(1)
                      INTO vnEncontroLider
                      FROM ZON_HISTO_GEREN
                      WHERE PAIS_OID_PAIS = vnOidPais
                      AND GERE = psCodigoCliente
                      AND LENGTH(UA) = 9
                      AND (vnOidPeriodo >= PERD_OID_PERI_DESD)
                      AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

                      IF vnEncontroLider > 0 THEN
                        EXIT;
                      END IF;

                  END LOOP;

                  IF vnEncontroLider = 0 THEN
                      vbEvaluacionParamNumCampAnte := TRUE;
                  ELSE
                      vbEvaluacionParamNumCampAnte := FALSE;
                  END IF;
                ELSE
                  vbEvaluacionParamNumCampAnte := TRUE;
                END IF;

                vnContadorLiderCamp := 0;

                vnNumTotalCampEval := paramClasiLider.Num_Tota_Camp_Eval;

                vsNCampAnteriorInicial := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-vnNumTotalCampEval);

                vnEncontroLider := 0;

                -- Contamos cuantas veces fue lider de seccion en el periodo de N Campa�as anteriores
                -- donde N es tomado del parametro Numero Total de Campa�as a Evaluar
                FOR x IN 0 .. (vnNumTotalCampEval-1) LOOP

                    vsCampProcesando := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsNCampAnteriorInicial,vnOidPais,vnOidMarca,vnOidCanal,x);
                    vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampProcesando);

                    SELECT COUNT(1)
                    INTO vnEncontroLider
                    FROM ZON_HISTO_GEREN
                    WHERE PAIS_OID_PAIS = vnOidPais
                    AND GERE = psCodigoCliente
                    AND LENGTH(UA) = 9
                    AND (vnOidPeriodo >= PERD_OID_PERI_DESD)
                    AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

                    -- vamos acumulando cuantas veces se le encontro como lider de seccion en el recorrido del periodo de campa�as

                    IF vnEncontroLider > 0 THEN
                      vnContadorLiderCamp := vnContadorLiderCamp + 1;
                    END IF;

                END LOOP;

                -- Evaluacion del siguiente parametro solo si el parametro de Numero de Campa�as Anteriores es 0,nulo o la evalucion del parametro
                -- Numero de Campa�as Anteriores fue valida y el Numero Maximo de Campa�as como Lider es mayor a 0,este paramtro es excluyente
                vnNumMaxCamp := paramClasiLider.Num_Maxi_Camp_Lide;

                IF vnNumMaxCamp > 0 THEN

                  IF vnContadorLiderCamp <= vnNumMaxCamp THEN
                     vbEvaluacionParamNumMaxCamp := TRUE;
                  ELSE
                     vbEvaluacionParamNumMaxCamp := FALSE;
                  END IF;

                END IF;

                -- Se evalua el parametro Numero Minimo de Campa�as como lider solo si el el paramtro Numero Maximo de Campa�as como Lider es igual a 0
                vnNumMinCamp := paramClasiLider.Num_Mini_Camp_Lide;
                          --05/09/2012 JPCC
                          IF vnNumMinCamp > 0 THEN

                  IF vnContadorLiderCamp >= vnNumMinCamp THEN
                     vbEvaluacionParamNumMinCamp := TRUE;
                  ELSE
                     vbEvaluacionParamNumMinCamp := FALSE;
                  END IF;

                END IF;

                -- Si todas las validaciones fueron correctas se procede a colocar a la consultora en la clasificacion evaluada
                IF vbEvaluacionParamNumCampAnte AND (vbEvaluacionParamNumMaxCamp OR vbEvaluacionParamNumMinCamp) THEN

                   vsCodigoClasificacion := paramClasiLider.Cod_Clas_Lide;
                   vnNumSecuClasificacion := paramClasiLider.Num_Secu_Clas;
                   /* INI JJ PER-SiCC-2012-0540*/

                   LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,1,vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

                   INSERT INTO LET_HISTO_CLASI_LIDER
                   VALUES(
                    psCodigoPais,
                    vsCodigoClasificacion,
                    vnNumSecuClasificacion,
                    vsCodigoTipoClasificacionMAE,-- Codigo Tipo Clasificacion - MAE
                    vsCodigoClasificacionMAE,-- C�digo Clasificaci�n - MAE
                    1,
                    psCodigoPeriodo,
                    psCodigoCliente,
                        2, --Tipo de carga = nombramiento
                    psCodigoUsuario,
                    SYSDATE,
                    NULL,
                    NULL,
                    1);

                    vbRegistroClasificacion := TRUE;

                    /* FIN JJ PER-SiCC-2012-0540*/
                END IF;
              END LOOP;

              IF vbRegistroClasificacion = FALSE THEN

                FOR paramClasiLider IN C_PARAM_CLASI_LIDER LOOP
                 vbEvaluacionParamNumMaxCamp := FALSE;
                  vbEvaluacionParamNumMinCamp := FALSE;
                 vnContadorLiderCamp:= 0;
                 vnNumTotalCampEval := paramClasiLider.Num_Tota_Camp_Eval;

                  vsNCampAnteriorInicial := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-vnNumTotalCampEval);

                  vnEncontroLider := 0;

                  -- Contamos cuantas veces fue lider de seccion en el periodo de N Campa�as anteriores
                  -- donde N es tomado del parametro Numero Total de Campa�as a Evaluar
                  FOR x IN 0 .. (vnNumTotalCampEval-1) LOOP

                      vsCampProcesando := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsNCampAnteriorInicial,vnOidPais,vnOidMarca,vnOidCanal,x);
                      vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampProcesando);

                      SELECT COUNT(1)
                      INTO vnEncontroLider
                      FROM ZON_HISTO_GEREN
                      WHERE PAIS_OID_PAIS = vnOidPais
                      AND GERE = psCodigoCliente
                      AND LENGTH(UA) = 9
                      AND (vnOidPeriodo >= PERD_OID_PERI_DESD)
                      AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

                      -- vamos acumulando cuantas veces se le encontro como lider de seccion en el recorrido del periodo de campa�as

                      IF vnEncontroLider > 0 THEN
                        vnContadorLiderCamp := vnContadorLiderCamp + 1;
                      END IF;

                  END LOOP;

                  -- Evaluacion del siguiente parametro solo si el parametro de Numero de Campa�as Anteriores es 0,nulo o la evalucion del parametro
                -- Numero de Campa�as Anteriores fue valida y el Numero Maximo de Campa�as como Lider es mayor a 0,este paramtro es excluyente
                vnNumMaxCamp := paramClasiLider.Num_Maxi_Camp_Lide;

                IF vnNumMaxCamp > 0 THEN

                  IF vnContadorLiderCamp <= vnNumMaxCamp THEN
                     vbEvaluacionParamNumMaxCamp := TRUE;
                  ELSE
                     vbEvaluacionParamNumMaxCamp := FALSE;
                  END IF;

                END IF;

                -- Se evalua el parametro Numero Minimo de Campa�as como lider solo si el el paramtro Numero Maximo de Campa�as como Lider es igual a 0
                vnNumMinCamp := paramClasiLider.Num_Mini_Camp_Lide;
                          --05/09/2012 JPCC
                          IF vnNumMinCamp > 0 THEN

                  IF vnContadorLiderCamp >= vnNumMinCamp THEN
                     vbEvaluacionParamNumMinCamp := TRUE;
                  ELSE
                     vbEvaluacionParamNumMinCamp := FALSE;
                  END IF;

                END IF;

                -- Si todas las validaciones fueron correctas se procede a colocar a la consultora en la clasificacion evaluada
                IF vbRegistroClasificacion= FALSE AND (vbEvaluacionParamNumMaxCamp OR vbEvaluacionParamNumMinCamp) THEN

                   vsCodigoClasificacion := paramClasiLider.Cod_Clas_Lide;
                   vnNumSecuClasificacion := paramClasiLider.Num_Secu_Clas;
                   /* INI JJ PER-SiCC-2012-0540*/

                   LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,1,vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

                   INSERT INTO LET_HISTO_CLASI_LIDER
                   VALUES(
                    psCodigoPais,
                    vsCodigoClasificacion,
                    vnNumSecuClasificacion,
                    vsCodigoTipoClasificacionMAE,-- Codigo Tipo Clasificacion - MAE
                    vsCodigoClasificacionMAE,-- C�digo Clasificaci�n - MAE
                    1,
                    psCodigoPeriodo,
                    psCodigoCliente,
                        2, --Tipo de carga = nombramiento
                    psCodigoUsuario,
                    SYSDATE,
                    NULL,
                    NULL,
                    1);

                    vbRegistroClasificacion := TRUE;

                    /* FIN JJ PER-SiCC-2012-0540*/
                END IF;

              END LOOP;
              END IF;

           ELSE
             -- Encontro consultora

             SELECT COD_CLAS_LIDE,COD_SUB_CLAS_LIDE,NUM_SECU_CLAS
             INTO vsCodigoClasificacion,vnSubClasiLider,vnNumSecuClasificacion
             FROM LET_HISTO_CLASI_LIDER
             WHERE PAIS_COD_PAIS = psCodigoPais
             AND COD_LIDE = psCodigoCliente
             AND CAM_CLAS = vsCodigoPeriodoAnterior
             AND EST_REGI = 1;

             select NUM_MAXI_CAMP_CLAS
             into vnNumMaxCampClasi
             from LET_PARAM_CLASI_LIDER
             where PAIS_COD_PAIS = psCodigoPais
             and   COD_CLAS_LIDE = vsCodigoClasificacion
             and   EST_REGI = 1;

             IF vnNumMaxCampClasi > 0 THEN

                IF vnSubClasiLider < vnNumMaxCampClasi THEN
                  /* INI JJ PER-SiCC-2012-0540*/
                  LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,(vnSubClasiLider+1),vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

                  INSERT INTO LET_HISTO_CLASI_LIDER
                   VALUES(
                    psCodigoPais,
                    vsCodigoClasificacion,
                    vnNumSecuClasificacion,
                    vsCodigoTipoClasificacionMAE,-- C�digo Tipo Clasificaci�n - MAE
                    vsCodigoClasificacionMAE,--C�digo Clasificaci�n - MAE
                    (vnSubClasiLider+1),
                    psCodigoPeriodo,
                    psCodigoCliente,
                    2,  --Tipo Carga = Nombramiento
                    psCodigoUsuario,
                    SYSDATE,
                    NULL,
                    NULL,
                    1);
                  /* FIN JJ PER-SiCC-2012-0540*/
                END IF;

                IF vnSubClasiLider = vnNumMaxCampClasi THEN

                   SELECT COD_CLAS_LIDE,NUM_TOTA_CAMP_EVAL,NUM_MAXI_CAMP_LIDE,NUM_MINI_CAMP_LIDE
                   INTO vsCodigoClasificacion,vnNumTotalCampEval,vnNumMaxCamp,vnNumMinCamp
                   FROM LET_PARAM_CLASI_LIDER
                   WHERE PAIS_COD_PAIS = psCodigoPais
                   AND NUM_TOTA_CAMP_EVAL = NUM_MINI_CAMP_LIDE;

                   /* INI JJ PER-SiCC-2012-0540*/
                   LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,1,vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

                     INSERT INTO LET_HISTO_CLASI_LIDER
                     VALUES(
                      psCodigoPais,
                      vsCodigoClasificacion,
                      vnNumSecuClasificacion,
                    vsCodigoTipoClasificacionMAE,-- C�digo Tipo Clasificaci�n - MAE
                    vsCodigoClasificacionMAE,-- C�digo Clasificaci�n - MAE
                    1,
                    psCodigoPeriodo,
                    psCodigoCliente,
                      1, --Tipo Carga = Nombramiento
                      psCodigoUsuario,
                      SYSDATE,
                      NULL,
                      NULL,
                      1);
                   /* FIN JJ PER-SiCC-2012-0540*/
                END IF;
             ELSE
                    /* INI JJ PER-SiCC-2012-0540*/
                  LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,(vnSubClasiLider+1),vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

                  INSERT INTO LET_HISTO_CLASI_LIDER
                   VALUES(
                    psCodigoPais,
                    vsCodigoClasificacion,
                    vnNumSecuClasificacion,
                    vsCodigoTipoClasificacionMAE,-- C�digo Tipo Clasificaci�n - MAE
                    vsCodigoClasificacionMAE,--C�digo Clasificaci�n - MAE
                    1,
                    psCodigoPeriodo,
                    psCodigoCliente,
                    2,  --Tipo Carga = Nombramiento
                    psCodigoUsuario,
                    SYSDATE,
                    NULL,
                    NULL,
                    1);
                  /* FIN JJ PER-SiCC-2012-0540*/

             END IF;

           END IF;
      /* fin jpcc */
      END IF;

     END IF;

     /*Llamada al Inicio de Campa�a */

        IF psTransaccion = 1 AND psTipoOperacion = 2 THEN

          vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

          /* Verifica si existe alguna clasificaci�n en la campa�a y la elimina*/
          --JPCC
          SELECT COUNT(1)
          INTO vnEncontro
          FROM LET_HISTO_CLASI_LIDER
          WHERE PAIS_COD_PAIS = psCodigoPais
          AND CAM_CLAS = psCodigoPeriodo
          AND EST_REGI = 1;

          IF vnEncontro > 0 THEN
            DELETE FROM LET_HISTO_CLASI_LIDER
            WHERE PAIS_COD_PAIS = psCodigoPais
            AND CAM_CLAS = psCodigoPeriodo
            AND EST_REGI = 1;
          END IF;
          --fin jPCC

          FOR listaConsultoras IN C_LISTA_CONSULTORAS(vnOidPais,vnOidPeriodo) LOOP

              vbRegistroClasificacion := FALSE;

              SELECT COUNT(1)
              INTO vnEncontro
              FROM LET_HISTO_CLASI_LIDER
              WHERE PAIS_COD_PAIS = psCodigoPais
              AND COD_LIDE = listaConsultoras.GERE
              AND CAM_CLAS = vsCodigoPeriodoAnterior
              AND EST_REGI = 1;

              IF vnEncontro > 0 THEN
              SELECT COD_CLAS_LIDE,COD_SUB_CLAS_LIDE,NUM_SECU_CLAS
              INTO vsCodigoClasificacion,vnSubClasiLider,vnNumSecuClasificacion
              FROM LET_HISTO_CLASI_LIDER
              WHERE PAIS_COD_PAIS = psCodigoPais
              AND COD_LIDE = listaConsultoras.GERE
              AND CAM_CLAS = vsCodigoPeriodoAnterior
              AND EST_REGI = 1;

              SELECT NUM_MAXI_CAMP_CLAS
              INTO vnNumMaxCampClasi
              FROM LET_PARAM_CLASI_LIDER
              WHERE PAIS_COD_PAIS = psCodigoPais
              AND COD_CLAS_LIDE = vsCodigoClasificacion
              AND EST_REGI = 1;

              IF vnNumMaxCampClasi > 0 THEN

                IF vnSubClasiLider < vnNumMaxCampClasi THEN
                  /* INI JJ PER-SiCC-2012-0540*/
                  LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,(vnSubClasiLider+1),vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

                  INSERT INTO LET_HISTO_CLASI_LIDER
                   VALUES(
                    psCodigoPais,
                    vsCodigoClasificacion,
                    vnNumSecuClasificacion,
                    vsCodigoTipoClasificacionMAE,-- C�digo Tipo Clasificaci�n - MAE
                    vsCodigoClasificacionMAE,-- C�digo Clasificaci�n - MAE
                    (vnSubClasiLider+1),
                    psCodigoPeriodo,
                    listaConsultoras.GERE,
                    3, --Tipo Carga = Inicio Campa�a
                    psCodigoUsuario,
                    SYSDATE,
                    NULL,
                    NULL,
                    1);
                   /* FIN JJ PER-SiCC-2012-0540*/
                END IF;

                IF vnSubClasiLider = vnNumMaxCampClasi THEN

                  SELECT COD_CLAS_LIDE
                  INTO vsCodigoClasificacion
                  FROM LET_PARAM_CLASI_LIDER
                  WHERE PAIS_COD_PAIS = psCodigoPais
                  AND NUM_MAXI_CAMP_CLAS = 0
                  AND EST_REGI = 1;
                  /* INI JJ PER-SiCC-2012-0540*/
                  LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,1,vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

                  INSERT INTO LET_HISTO_CLASI_LIDER
                   VALUES(
                    psCodigoPais,
                    vsCodigoClasificacion,
                    vnNumSecuClasificacion,
                    vsCodigoTipoClasificacionMAE,-- C�digo Tipo Clasificaci�n - MAE
                    vsCodigoClasificacionMAE,-- C�digo Clasificaci�n - MAE
                    1,
                    psCodigoPeriodo,
                    listaConsultoras.GERE,
                    3, --Tipo Carga = Inicio Campa�a
                    psCodigoUsuario,
                    SYSDATE,
                    NULL,
                    NULL,
                    1);
                   /* FIN JJ PER-SiCC-2012-0540*/
                END IF;

              ELSE
                  /* si vnNumMaxCampClasi = 0 entonces es Establecida Regular */
                 /* INI JJ PER-SiCC-2012-0540*/
                 LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,1,vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

                 INSERT INTO LET_HISTO_CLASI_LIDER
                  VALUES(
                   psCodigoPais,
                   vsCodigoClasificacion,
                   vnNumSecuClasificacion,
                   vsCodigoTipoClasificacionMAE,-- C�digo Tipo Clasificaci�n - MAE
                   vsCodigoClasificacionMAE,-- C�digo Clasificaci�n - MAE
                   1,
                   psCodigoPeriodo,
                   listaConsultoras.GERE,
                             3,  --Tipo Carga = Inicio Campa�a, Carga Masiva
                   psCodigoUsuario,
                   SYSDATE,
                   NULL,
                   NULL,
                   1);
                  /* FIN JJ PER-SiCC-2012-0540*/
              END IF;


              ELSE

                  /* si no la encontr� en la campa�a anterior */

                 FOR paramClasiLider IN C_PARAM_CLASI_LIDER LOOP

                    vbEvaluacionParamNumCampAnte := FALSE;
                    vbEvaluacionParamNumMaxCamp := FALSE;
                    vbEvaluacionParamNumMinCamp := FALSE;

                    vnNumCampAnte := paramClasiLider.Num_Camp_Ante;

                    IF vnNumCampAnte > 0 THEN

                      vsNCampAnteriorInicial := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-paramClasiLider.Num_Camp_Ante);

                      vnEncontroLider := 0;

                      FOR x IN 0 .. (paramClasiLider.Num_Camp_Ante-1) LOOP

                          vsCampProcesando := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsNCampAnteriorInicial,vnOidPais,vnOidMarca,vnOidCanal,x);
                          vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampProcesando);

                          SELECT COUNT(1)
                          INTO vnEncontroLider
                          FROM ZON_HISTO_GEREN
                          WHERE PAIS_OID_PAIS = vnOidPais
                          AND GERE = listaConsultoras.GERE
                          AND LENGTH(UA) = 9
                          AND (vnOidPeriodo >= PERD_OID_PERI_DESD)
                          AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

                          IF vnEncontroLider > 0 THEN
                            EXIT;
                          END IF;

                      END LOOP;

                      IF vnEncontroLider = 0 THEN
                          vbEvaluacionParamNumCampAnte := TRUE;
                      ELSE
                          vbEvaluacionParamNumCampAnte := FALSE;
                      END IF;
                    ELSE
                      vbEvaluacionParamNumCampAnte := TRUE;
                    END IF;

                    vnContadorLiderCamp := 0;

                    vnNumTotalCampEval := paramClasiLider.Num_Tota_Camp_Eval;

                    vsNCampAnteriorInicial := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-vnNumTotalCampEval);

                    vnEncontroLider := 0;

                    -- Contamos cuantas veces fue lider de seccion en el periodo de N Campa�as anteriores
                    -- donde N es tomado del parametro Numero Total de Campa�as a Evaluar
                    FOR x IN 0 .. (vnNumTotalCampEval-1) LOOP

                        vsCampProcesando := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsNCampAnteriorInicial,vnOidPais,vnOidMarca,vnOidCanal,x);
                        vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampProcesando);

                        SELECT COUNT(1)
                        INTO vnEncontroLider
                        FROM ZON_HISTO_GEREN
                        WHERE PAIS_OID_PAIS = vnOidPais
                        AND GERE = listaConsultoras.GERE
                        AND LENGTH(UA) = 9
                        AND (vnOidPeriodo >= PERD_OID_PERI_DESD)
                        AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

                        -- vamos acumulando cuantas veces se le encontro como lider de seccion en el recorrido del periodo de campa�as

                        IF vnEncontroLider > 0 THEN
                          vnContadorLiderCamp := vnContadorLiderCamp + 1;
                        END IF;

                    END LOOP;

                    -- Evaluacion del siguiente parametro solo si el parametro de Numero de Campa�as Anteriores es 0,nulo o la evalucion del parametro
                    -- Numero de Campa�as Anteriores fue valida y el Numero Maximo de Campa�as como Lider es mayor a 0,este paramtro es excluyente
                    vnNumMaxCamp := paramClasiLider.Num_Maxi_Camp_Lide;

                    IF vnNumMaxCamp > 0 THEN

                      IF vnContadorLiderCamp <= vnNumMaxCamp THEN
                         vbEvaluacionParamNumMaxCamp := TRUE;
                      ELSE
                         vbEvaluacionParamNumMaxCamp := FALSE;
                      END IF;

                    END IF;

                    -- Se evalua el parametro Numero Minimo de Campa�as como lider solo si el el paramtro Numero Maximo de Campa�as como Lider es igual a 0
                    vnNumMinCamp := paramClasiLider.Num_Mini_Camp_Lide;
					          --05/09/2012 JPCC
                    IF vnNumMinCamp > 0 THEN

                      IF vnContadorLiderCamp >= vnNumMinCamp THEN
                         vbEvaluacionParamNumMinCamp := TRUE;
                      ELSE
                         vbEvaluacionParamNumMinCamp := FALSE;
                      END IF;

                    END IF;

                    -- Si todas las validaciones fueron correctas se procede a colocar a la consultora en la clasificacion evaluada
                    IF vbEvaluacionParamNumCampAnte AND (vbEvaluacionParamNumMaxCamp OR vbEvaluacionParamNumMinCamp) THEN

                       vsCodigoClasificacion := paramClasiLider.Cod_Clas_Lide;
                       vnNumSecuClasificacion := paramClasiLider.Num_Secu_Clas;
                       /* INI JJ PER-SiCC-2012-0540*/

                       LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,1,vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

                       INSERT INTO LET_HISTO_CLASI_LIDER
                       VALUES(
                        psCodigoPais,
                        vsCodigoClasificacion,
                        vnNumSecuClasificacion,
                        vsCodigoTipoClasificacionMAE,-- Codigo Tipo Clasificacion - MAE
                        vsCodigoClasificacionMAE,-- C�digo Clasificaci�n - MAE
                        1,
                        psCodigoPeriodo,
                        listaConsultoras.GERE,
                                      3, --Tipo Carga = Inicio Campa�a Carga Masiva
                        psCodigoUsuario,
                        SYSDATE,
                        NULL,
                        NULL,
                        1);

                        vbRegistroClasificacion := TRUE;

                        /* FIN JJ PER-SiCC-2012-0540*/
                    END IF;

                  END LOOP;

                  IF vbRegistroClasificacion = FALSE THEN

                    FOR paramClasiLider IN C_PARAM_CLASI_LIDER LOOP
                     vbEvaluacionParamNumMaxCamp := FALSE;
                     vbEvaluacionParamNumMinCamp := FALSE;
                     vnContadorLiderCamp:= 0;
                     vnNumTotalCampEval := paramClasiLider.Num_Tota_Camp_Eval;

                      vsNCampAnteriorInicial := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-vnNumTotalCampEval);

                      vnEncontroLider := 0;

                      -- Contamos cuantas veces fue lider de seccion en el periodo de N Campa�as anteriores
                      -- donde N es tomado del parametro Numero Total de Campa�as a Evaluar
                      FOR x IN 0 .. (vnNumTotalCampEval-1) LOOP

                          vsCampProcesando := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsNCampAnteriorInicial,vnOidPais,vnOidMarca,vnOidCanal,x);
                          vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampProcesando);

                          SELECT COUNT(1)
                          INTO vnEncontroLider
                          FROM ZON_HISTO_GEREN
                          WHERE PAIS_OID_PAIS = vnOidPais
                          AND GERE = listaConsultoras.GERE
                          AND LENGTH(UA) = 9
                          AND (vnOidPeriodo >= PERD_OID_PERI_DESD)
                          AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

                          -- vamos acumulando cuantas veces se le encontro como lider de seccion en el recorrido del periodo de campa�as

                          IF vnEncontroLider > 0 THEN
                            vnContadorLiderCamp := vnContadorLiderCamp + 1;
                          END IF;

                      END LOOP;

                      -- Evaluacion del siguiente parametro solo si el parametro de Numero de Campa�as Anteriores es 0,nulo o la evalucion del parametro
                    -- Numero de Campa�as Anteriores fue valida y el Numero Maximo de Campa�as como Lider es mayor a 0,este paramtro es excluyente
                    vnNumMaxCamp := paramClasiLider.Num_Maxi_Camp_Lide;

                    IF vnNumMaxCamp > 0 THEN

                      IF vnContadorLiderCamp <= vnNumMaxCamp THEN
                         vbEvaluacionParamNumMaxCamp := TRUE;
                      ELSE
                         vbEvaluacionParamNumMaxCamp := FALSE;
                      END IF;

                    END IF;

                    -- Se evalua el parametro Numero Minimo de Campa�as como lider solo si el el paramtro Numero Maximo de Campa�as como Lider es igual a 0
                    vnNumMinCamp := paramClasiLider.Num_Mini_Camp_Lide;
                              --05/09/2012 JPCC
                    IF vnNumMinCamp > 0 THEN

                      IF vnContadorLiderCamp >= vnNumMinCamp THEN
                         vbEvaluacionParamNumMinCamp := TRUE;
                      ELSE
                         vbEvaluacionParamNumMinCamp := FALSE;
                      END IF;

                    END IF;

                    -- Si todas las validaciones fueron correctas se procede a colocar a la consultora en la clasificacion evaluada
                    IF vbRegistroClasificacion= FALSE AND (vbEvaluacionParamNumMaxCamp OR vbEvaluacionParamNumMinCamp) THEN

                       vsCodigoClasificacion := paramClasiLider.Cod_Clas_Lide;
                       vnNumSecuClasificacion := paramClasiLider.Num_Secu_Clas;
                       /* INI JJ PER-SiCC-2012-0540*/

                       LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,1,vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

                       INSERT INTO LET_HISTO_CLASI_LIDER
                       VALUES(
                        psCodigoPais,
                        vsCodigoClasificacion,
                        vnNumSecuClasificacion,
                        vsCodigoTipoClasificacionMAE,-- Codigo Tipo Clasificacion - MAE
                        vsCodigoClasificacionMAE,-- C�digo Clasificaci�n - MAE
                        1,
                        psCodigoPeriodo,
                        listaConsultoras.GERE,
                            2, --Tipo de carga = nombramiento
                        psCodigoUsuario,
                        SYSDATE,
                        NULL,
                        NULL,
                        1);

                        vbRegistroClasificacion := TRUE;

                        /* FIN JJ PER-SiCC-2012-0540*/
                    END IF;

                    END LOOP;
              END IF;

              END IF;
          END LOOP;

        END IF;
        /* INI JJ PER-SiCC-2012-0201 */
        IF psTransaccion = 2 AND (psTipoOperacion = 1 OR psTipoOperacion = 3 OR psTipoOperacion = 4) THEN
        /* FIN JJ PER-SiCC-2012-0201 */
           DELETE FROM LET_HISTO_CLASI_LIDER
           WHERE PAIS_COD_PAIS = psCodigoPais
           AND COD_LIDE = psCodigoCliente
           AND CAM_CLAS = psCodigoPeriodo;

        END IF;

    EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_ACTUA_CLASI_LIDER: ' || ls_sqlerrm);
    END LET_PR_ACTUA_CLASI_LIDER;

    /*************************************************************************
    Descripcion : Genera la data para el reporte LET de Lideres
    Fecha Creacion : 13/06/2012
    Autor : Jesse James Rios Franco
    *************************************************************************/
    PROCEDURE LET_PR_GENER_REPOR_LIDER(psCodigoPais VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2) IS

      CURSOR C_LIDERES_PERIODO_SIGUIENTE(oidPais SEG_PAIS.OID_PAIS%TYPE,periodoSiguiente CRA_PERIO.OID_PERI%TYPE) IS
      select GERE,
             UA
      from ZON_HISTO_GEREN
      where PAIS_OID_PAIS = oidPais
      and periodoSiguiente >= PERD_OID_PERI_DESD
      and (periodoSiguiente <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
      and LENGTH(UA) = 9;

      TYPE lideresPeriodoSiguienteRecord IS RECORD(
        GERE ZON_HISTO_GEREN.GERE%TYPE,
        UA   ZON_HISTO_GEREN.UA%TYPE
      );

      TYPE lideresPeriodoSiguienteTab IS TABLE OF lideresPeriodoSiguienteRecord;
      lideresPeriodoSiguiente lideresPeriodoSiguienteTab;
      /* INI JJ PER-SiCC-2012-0451*/
      CURSOR C_LIDERES_TEMP IS
      SELECT COD_LIDE FROM LET_TEMPO_LIDER;

      TYPE lideresTempTab IS TABLE OF LET_TEMPO_LIDER.COD_LIDE%TYPE;
      lideresTemp lideresTempTab;

      CURSOR C_PARAM_CLASI_LIDER IS
     select PAIS_COD_PAIS,
            COD_CLAS_LIDE,
            NUM_SECU_CLAS,
            NUM_MAXI_CAMP_CLAS,
            NUM_CAMP_ANTE,
            NUM_TOTA_CAMP_EVAL,
            NUM_MAXI_CAMP_LIDE,
            NUM_MINI_CAMP_LIDE,
            IND_ACTI_CLAS,
            EST_REGI
     from LET_PARAM_CLASI_LIDER
     where PAIS_COD_PAIS = psCodigoPais
     and   EST_REGI = 1;

     TYPE paramClasiLiderTab IS TABLE OF let_param_clasi_lider%ROWTYPE;
     paramClasiLider paramClasiLiderTab;

     vbEvaluacionParamNumCampAnte     BOOLEAN;
     vbEvaluacionParamNumMaxCamp      BOOLEAN;
     vbEvaluacionParamNumMinCamp      BOOLEAN;

     vsNCampAnteriorInicial SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnNumTotalCampEval     LET_PARAM_CLASI_LIDER.NUM_TOTA_CAMP_EVAL%TYPE;
     vnNumCampAnte          LET_PARAM_CLASI_LIDER.NUM_CAMP_ANTE%TYPE;
     vnNumMinCamp           LET_PARAM_CLASI_LIDER.NUM_MINI_CAMP_LIDE%TYPE;
     vsCampProcesando       SEG_PERIO_CORPO.COD_PERI%TYPE;
     vnNumMaxCamp           LET_PARAM_CLASI_LIDER.NUM_MAXI_CAMP_LIDE%TYPE;
     vnEncontroLider NATURAL;
     vnContadorLiderCamp NATURAL;
     vnNumSecuClasificacion     LET_PARAM_CLASI_LIDER.NUM_SECU_CLAS%TYPE;
     vsCodigoTipoClasificacionMAE LET_HISTO_CLASI_LIDER.COD_TIPO_CLAS%TYPE;
     vsCodigoClasificacionMAE LET_HISTO_CLASI_LIDER.COD_CLAS%TYPE;
     vsCodigoTipoClasificacionMAEP LET_HISTO_CLASI_LIDER.COD_TIPO_CLAS%TYPE;
     vsCodigoClasificacionMAEP LET_HISTO_CLASI_LIDER.COD_CLAS%TYPE;
      /* FIN JJ PER-SiCC-2012-0451*/
      vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
      vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;
      vsCodigoPeridoSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE;
      vnOidPeriodoSiguiente   CRA_PERIO.OID_PERI%TYPE;
      vnOidMarca              SEG_MARCA.OID_MARC%TYPE;
      vnOidCanal              SEG_CANAL.OID_CANA%TYPE;
      vnEncontroLiderTemp     NUMBER;
      vsCodigoClasificacion   LET_HISTO_CLASI_LIDER.COD_CLAS_LIDE%TYPE;
      vnCodigoSubClasificacion LET_HISTO_CLASI_LIDER.COD_SUB_CLAS_LIDE%TYPE;

    BEGIN

        vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
        vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
        vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
        vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
        vsCodigoPeridoSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,1);
        vnOidPeriodoSiguiente := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodigoPeridoSiguiente);

        EXECUTE IMMEDIATE 'TRUNCATE TABLE LET_TEMPO_LIDER';

        INSERT INTO LET_TEMPO_LIDER
        select GERE AS GERE_PROCE,
               (SELECT (NVL(CL.VAL_NOM1,' ')) ||'  '||TRIM(NVL(CL.VAL_APE1,' '))||'  '||TRIM(NVL2(CL.VAL_APE2,SUBSTR(CL.VAL_APE2,0,1) || '.',' '))
                FROM MAE_CLIEN CL
                WHERE PAIS_OID_PAIS = vnOidPais
                AND COD_CLIE = GERE)AS NOMBRE,
                UA AS UA_PROCE,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL
        from ZON_HISTO_GEREN
        where vnOidPeriodo >= PERD_OID_PERI_DESD
        and (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
        and LENGTH(UA) = 9;

        OPEN C_LIDERES_PERIODO_SIGUIENTE(vnOidPais,vnOidPeriodoSiguiente);

        LOOP

          FETCH C_LIDERES_PERIODO_SIGUIENTE BULK COLLECT INTO lideresPeriodoSiguiente LIMIT W_FILAS;

          IF lideresPeriodoSiguiente.COUNT > 0 THEN
            FOR i IN lideresPeriodoSiguiente.FIRST .. lideresPeriodoSiguiente.LAST LOOP

            SELECT COUNT(1)
            INTO vnEncontroLiderTemp
            FROM LET_TEMPO_LIDER
            WHERE COD_LIDE = lideresPeriodoSiguiente(i).GERE;

            IF vnEncontroLiderTemp > 0 THEN
              UPDATE LET_TEMPO_LIDER
              SET UA_PERI_SIGU = lideresPeriodoSiguiente(i).UA
              WHERE COD_LIDE = lideresPeriodoSiguiente(i).GERE;
            ELSE
              INSERT INTO LET_TEMPO_LIDER(COD_LIDE,NOM_LIDE,UA_PERI_SIGU)
              VALUES(
               lideresPeriodoSiguiente(i).GERE,
               (SELECT (NVL(CL.VAL_NOM1,' ')) ||'  '||TRIM(NVL(CL.VAL_APE1,' '))||'  '||TRIM(NVL2(CL.VAL_APE2,SUBSTR(CL.VAL_APE2,0,1) || '.',' '))
                FROM MAE_CLIEN CL
                WHERE PAIS_OID_PAIS = vnOidPais
                AND COD_CLIE = lideresPeriodoSiguiente(i).GERE),
                lideresPeriodoSiguiente(i).UA);
            END IF;

            END LOOP;
          END IF;

          EXIT WHEN C_LIDERES_PERIODO_SIGUIENTE%NOTFOUND;
        END LOOP;

        CLOSE C_LIDERES_PERIODO_SIGUIENTE;

        /* INI JJ PER-SiCC-2012-0451*/
        FOR lideresTemp IN C_LIDERES_TEMP LOOP
          BEGIN
          SELECT COD_CLAS_LIDE,COD_SUB_CLAS_LIDE
          INTO vsCodigoClasificacion,vnCodigoSubClasificacion
          FROM LET_HISTO_CLASI_LIDER
          WHERE PAIS_COD_PAIS = psCodigoPais
          AND COD_LIDE = lideresTemp.Cod_Lide
          AND CAM_CLAS = psCodigoPeriodo;

            LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,vnCodigoSubClasificacion,vsCodigoTipoClasificacionMAE,vsCodigoClasificacionMAE);

          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vsCodigoClasificacion := NULL;
              vnCodigoSubClasificacion := NULL;
          END;
          -- Proyecto de Clasificacion tipo inicio de campa�a
          IF vsCodigoClasificacion IS NOT NULL THEN
          UPDATE LET_TEMPO_LIDER
            SET COD_CLAS_LIDE = vsCodigoTipoClasificacionMAE,
                COD_SUB_CLAS_LIDE = vsCodigoClasificacionMAE
          WHERE COD_LIDE = lideresTemp.Cod_Lide;

          SELECT NUM_MAXI_CAMP_CLAS
          INTO   vnNumMaxCamp
          FROM LET_PARAM_CLASI_LIDER
          WHERE PAIS_COD_PAIS = psCodigoPais
          AND   COD_CLAS_LIDE = vsCodigoClasificacion;

          IF vnNumMaxCamp = vnCodigoSubClasificacion THEN
            SELECT COD_CLAS_LIDE
            INTO   vsCodigoClasificacion
            FROM LET_PARAM_CLASI_LIDER
            WHERE PAIS_COD_PAIS = psCodigoPais
            AND   NUM_TOTA_CAMP_EVAL = NUM_MINI_CAMP_LIDE;

              LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,1,vsCodigoTipoClasificacionMAEP,vsCodigoClasificacionMAEP);

            UPDATE LET_TEMPO_LIDER
              SET COD_CLAS_LIDE_PROY = vsCodigoTipoClasificacionMAEP,
                  COD_SUB_CLAS_LIDE_PROY = vsCodigoClasificacionMAEP
            WHERE COD_LIDE = lideresTemp.Cod_Lide;

          ELSIF vnNumMaxCamp > 0 THEN
            IF vnCodigoSubClasificacion < vnNumMaxCamp THEN
                LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,(vnCodigoSubClasificacion + 1),vsCodigoTipoClasificacionMAEP,vsCodigoClasificacionMAEP);

              UPDATE LET_TEMPO_LIDER
                SET COD_CLAS_LIDE_PROY = vsCodigoTipoClasificacionMAEP,
                    COD_SUB_CLAS_LIDE_PROY = vsCodigoClasificacionMAEP
                WHERE COD_LIDE = lideresTemp.Cod_Lide;
              END IF;
            ELSE
              LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,1,vsCodigoTipoClasificacionMAEP,vsCodigoClasificacionMAEP);
              UPDATE LET_TEMPO_LIDER
              SET COD_CLAS_LIDE_PROY = vsCodigoTipoClasificacionMAEP,
                  COD_SUB_CLAS_LIDE_PROY = vsCodigoClasificacionMAEP
              WHERE COD_LIDE = lideresTemp.Cod_Lide;
            END IF;
          ELSE
            -- Proyecto de Clasificacion tipo nombramiento de campa�a
            FOR paramClasiLider IN C_PARAM_CLASI_LIDER LOOP
                vsCodigoClasificacion := paramClasiLider.Cod_Clas_Lide;
                vbEvaluacionParamNumCampAnte := FALSE;
                vbEvaluacionParamNumMaxCamp := FALSE;
                vbEvaluacionParamNumMinCamp := FALSE;

                vnNumCampAnte := paramClasiLider.Num_Camp_Ante;

                IF vnNumCampAnte > 0 THEN

                  vsNCampAnteriorInicial := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(vsCodigoPeridoSiguiente,vnOidPais,vnOidMarca,vnOidCanal,-paramClasiLider.Num_Camp_Ante);

                  vnEncontroLider := 0;

                  FOR x IN 0 .. (paramClasiLider.Num_Camp_Ante-1) LOOP

                      vsCampProcesando := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsNCampAnteriorInicial,vnOidPais,vnOidMarca,vnOidCanal,x);
                      vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampProcesando);

                      SELECT COUNT(1)
                      INTO vnEncontroLider
                      FROM ZON_HISTO_GEREN
                      WHERE PAIS_OID_PAIS = vnOidPais
                      AND GERE = lideresTemp.Cod_Lide
                      AND LENGTH(UA) = 9
                      AND (vnOidPeriodo >= PERD_OID_PERI_DESD)
                      AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

                      IF vnEncontroLider > 0 THEN
                        EXIT;
                      END IF;

                  END LOOP;

                  IF vnEncontroLider = 0 THEN
                      vbEvaluacionParamNumCampAnte := TRUE;
                  ELSE
                      vbEvaluacionParamNumCampAnte := FALSE;
                  END IF;
                ELSE
                  vbEvaluacionParamNumCampAnte := TRUE;
                END IF;

                vnContadorLiderCamp := 0;

                vnNumTotalCampEval := paramClasiLider.Num_Tota_Camp_Eval;

                vsNCampAnteriorInicial := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(vsCodigoPeridoSiguiente,vnOidPais,vnOidMarca,vnOidCanal,-vnNumTotalCampEval);

                vnEncontroLider := 0;

                -- Contamos cuantas veces fue lider de seccion en el periodo de N Campa�as anteriores
                -- donde N es tomado del parametro Numero Total de Campa�as a Evaluar
                FOR x IN 0 .. (vnNumTotalCampEval-1) LOOP

                    vsCampProcesando := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(vsNCampAnteriorInicial,vnOidPais,vnOidMarca,vnOidCanal,x);
                    vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampProcesando);

                    SELECT COUNT(1)
                    INTO vnEncontroLider
                    FROM ZON_HISTO_GEREN
                    WHERE PAIS_OID_PAIS = vnOidPais
                    AND GERE = lideresTemp.Cod_Lide
                    AND LENGTH(UA) = 9
                    AND (vnOidPeriodo >= PERD_OID_PERI_DESD)
                    AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

                    -- vamos acumulando cuantas veces se le encontro como lider de seccion en el recorrido del periodo de campa�as

                    IF vnEncontroLider > 0 THEN
                      vnContadorLiderCamp := vnContadorLiderCamp + 1;
                    END IF;

                END LOOP;

                -- Evaluacion del siguiente parametro solo si el parametro de Numero de Campa�as Anteriores es 0,nulo o la evalucion del parametro
                -- Numero de Campa�as Anteriores fue valida y el Numero Maximo de Campa�as como Lider es mayor a 0,este paramtro es excluyente
                vnNumMaxCamp := paramClasiLider.Num_Maxi_Camp_Lide;
                IF vnNumMaxCamp > 0 THEN

                  IF vnContadorLiderCamp <= vnNumMaxCamp THEN
                     vbEvaluacionParamNumMaxCamp := TRUE;
                  ELSE
                     vbEvaluacionParamNumMaxCamp := FALSE;
                  END IF;

                END IF;

                -- Se evalua el parametro Numero Minimo de Campa�as como lider solo si el el paramtro Numero Maximo de Campa�as como Lider es igual a 0
                vnNumMinCamp := paramClasiLider.Num_Mini_Camp_Lide;
                IF vnNumMinCamp >= 0 THEN

                  IF vnContadorLiderCamp >= vnNumMinCamp THEN
                     vbEvaluacionParamNumMinCamp := TRUE;
                  ELSE
                     vbEvaluacionParamNumMinCamp := FALSE;
                  END IF;

                END IF;

                -- Si todas las validaciones fueron correctas se procede a colocar a la consultora en la clasificacion evaluada
                IF vbEvaluacionParamNumCampAnte AND (vbEvaluacionParamNumMaxCamp OR vbEvaluacionParamNumMinCamp) THEN
                    LET_PR_OBTIE_CLASI_EQUIV(vsCodigoClasificacion,1,vsCodigoTipoClasificacionMAEP,vsCodigoClasificacionMAEP);
            UPDATE LET_TEMPO_LIDER
                    SET COD_CLAS_LIDE_PROY = vsCodigoTipoClasificacionMAEP,
                        COD_SUB_CLAS_LIDE_PROY = vsCodigoClasificacionMAEP
            WHERE COD_LIDE = lideresTemp.Cod_Lide;
          END IF;
        END LOOP;
          END IF;
        END LOOP;
        /* FIN JJ PER-SiCC-2012-0451*/
    EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_GENER_REPOR_LIDER: ' || ls_sqlerrm);
    END LET_PR_GENER_REPOR_LIDER;

    /*************************************************************************
    Descripcion : Obtiene el tipo de clasifiacacion y clasificacion MAE
    Fecha Creacion : 25/06/2012
    Autor : Jesse James Rios Franco
    *************************************************************************/
    PROCEDURE LET_PR_OBTIE_CLASI_EQUIV(psCodigoClasificacion VARCHAR2,
                                       pnSubClasiLider NUMBER,
                                       psCodigoTipoClasificacionMAE OUT VARCHAR2,
                                       psCodigoClasificacionMAE OUT VARCHAR2)IS

    BEGIN

        IF psCodigoClasificacion = '01' THEN

          psCodigoTipoClasificacionMAE := '08';
          psCodigoClasificacionMAE := '0' || TO_CHAR(pnSubClasiLider);

        ELSIF psCodigoClasificacion = '02' THEN

              psCodigoTipoClasificacionMAE := '09';
              psCodigoClasificacionMAE := '01';

        ELSIF psCodigoClasificacion = '03' THEN

               psCodigoTipoClasificacionMAE := '09';
               psCodigoClasificacionMAE := '02';

        END IF;

    EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_OBTIE_CLASI_EQUIV: ' || ls_sqlerrm);
    END LET_PR_OBTIE_CLASI_EQUIV;

    /*************************************************************************
    Descripcion : Devuelve la descripcion del tipo de clasificacion del cliente
    Fecha Creacion : 01/08/2012
    Autor : Jesse James Rios Franco
    *************************************************************************/
    FUNCTION LET_FN_DEVUE_DESCR_TIPO_CLASI(psCodigoTipoCliente VARCHAR2,
                                           psCodigoSubTipoCliente VARCHAR2,
                                           psCodigoTipoClasificacion VARCHAR2)
                                           RETURN VARCHAR2 IS

    vsDescripcionTipoClasificacion VARCHAR2(100);

    BEGIN
      BEGIN
        select val_i18n
        into vsDescripcionTipoClasificacion
        from v_gen_i18n_sicc
        where attr_enti = 'MAE_TIPO_CLASI_CLIEN'
        and val_oid = (select OID_TIPO_CLAS
                       from mae_tipo_clasi_clien
                       where SBTI_OID_SUBT_CLIE = (select OID_SUBT_CLIE
                                                   from mae_tipo_clien a,
                                                        mae_subti_clien b
                                                   where a.oid_tipo_clie = b.ticl_oid_tipo_clie
                                                   and a.COD_TIPO_CLIE = psCodigoTipoCliente
                                                   and b.cod_subt_clie = psCodigoSubTipoCliente)
                       and COD_TIPO_CLAS = psCodigoTipoClasificacion
                      )
        and idio_oid_idio = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsDescripcionTipoClasificacion := '';
      END;

      RETURN vsDescripcionTipoClasificacion;

    EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_FN_DEVUE_DESCR_TIPO_CLASI: ' || ls_sqlerrm);
    END LET_FN_DEVUE_DESCR_TIPO_CLASI;

    /*************************************************************************
    Descripcion : Devuelve la descripcion de la clasificacion del cliente
    Fecha Creacion : 01/08/2012
    Autor : Jesse James Rios Franco
    *************************************************************************/
    FUNCTION LET_FN_DEVUE_DESCR_SUB_CLASI(psCodigoTipoCliente VARCHAR2,
                                          psCodigoSubTipoCliente VARCHAR2,
                                          psCodigoTipoClasificacion VARCHAR2,
                                          psCodigoClasificacion VARCHAR2)
                                          RETURN VARCHAR2 IS

      vsDescripcionSubClasificacion VARCHAR2(100);

    BEGIN

        BEGIN
          select val_i18n
          into vsDescripcionSubClasificacion
          from v_gen_i18n_sicc
          where attr_enti = 'MAE_CLASI'
          and val_oid = (select OID_CLAS
                         from mae_clasi
                         where TCCL_OID_TIPO_CLAS = (select OID_TIPO_CLAS
                                                     from mae_tipo_clasi_clien
                                                     where SBTI_OID_SUBT_CLIE = (select OID_SUBT_CLIE
                                                                                 from mae_tipo_clien a,
                                                                                      mae_subti_clien b
                                                                                 where a.oid_tipo_clie = b.ticl_oid_tipo_clie
                                                                                 and a.COD_TIPO_CLIE = psCodigoTipoCliente
                                                                                 and b.cod_subt_clie = psCodigoSubTipoCliente)
                                                     and COD_TIPO_CLAS = psCodigoTipoClasificacion
                                                    )
                           and COD_CLAS = psCodigoClasificacion
                           )
          and idio_oid_idio = 1;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsDescripcionSubClasificacion := '';
        END;

        RETURN vsDescripcionSubClasificacion;

     EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_FN_DEVUE_DESCR_SUB_CLASI: ' || ls_sqlerrm);
     END LET_FN_DEVUE_DESCR_SUB_CLASI;

    /**********************************************************************************
    Descripcion       : Valida la parte de desvinculacion
    Fecha Creacion    : 24/02/2011
    Autor             :
    **********************************************************************************/
   FUNCTION LET_FN_VAL_DESVI(pscodregi varchar2,
                             pscodzona varchar2,
                             pscodsecc varchar2,
                             pstipo  varchar2,
                             pscodsubreg  varchar2,
                             vscodigoPeriodoValAsiLid varchar2,
                             psCodigoCliente VARCHAR2,
                             psCodigoPeriodoActual VARCHAR2
                                  )
RETURN VARCHAR2 IS
  v_oid_clie VARCHAR2(50);
  V_OID_HIST_GERE VARCHAR2(50);
  v_cod_secc varchar2(12);
  --vsOidPeriodoValAsiLid :=
  --gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodigoPeriodoValAsiLid);
   BEGIN
     if pstipo = 'R' then
       select  r.clie_oid_clie
         into  v_oid_clie
         from zon_regio r
        where r.cod_regi = pscodregi;

        if v_oid_clie is not null then
          return 'Se desvincular� la responsable seleccionada, Desea continuar';
       end if;
     end if;

    if pstipo = 'Z' then
      select r.clie_oid_clie
        into v_oid_clie
        from zon_zona r
       where r.cod_zona = pscodzona;

       if v_oid_clie is not null then
          return 'Se desvincular� la responsable seleccionada, Desea continuar';
       end if;
    end if;



    if pstipo = 'S' then
       if vscodigoPeriodoValAsiLid = psCodigoPeriodoActual then
          /*select  distinct(r.clie_oid_clie), r.cod_secc
            into  v_oid_clie , v_cod_secc
            from zon_secci r
           where clie_oid_clie = GEN_PKG_GENER.gen_fn_devuelve_id_cliente(psCodigoCliente);

          if v_oid_clie is not null then
            return  'Se desvincular� la responsable seleccionada, Desea continuar';
          end if;
          */
          return  'Se desvincular� la responsable seleccionada, Desea continuar';

    else
         BEGIN
      select OID_HIST_GERE
        INTO  V_OID_HIST_GERE
        from ZON_HISTO_GEREN
           where ua = pscodsubreg || pscodregi || pscodzona || pscodsecc
             AND PERD_OID_PERI_HAST IS NULL;
         EXCEPTION
           WHEN NO_DATA_FOUND THEN
                V_OID_HIST_GERE := '' ;
         END;

       IF V_OID_HIST_GERE IS NOT NULL THEN
         return  'Se desvincular� la responsable seleccionada, Desea continuar';
         --proceso desvincular
       end if;
     --desvincular
    end if;
    end if;


   END;

    /**********************************************************************************
    Descripcion       : Proceso de desvinculacion
    Fecha Creacion    : 21/02/2013, 03/04/2014
    Autor             : Anonimo
    Modificado por    : Juan Altamirano
    **********************************************************************************/
   PROCEDURE LET_PR_PROC_DESVI(psCodigoPais VARCHAR2,
                                psOidPeriodoValAsiLid varchar2,
                                pscodregi varchar2,
                                pscodzona varchar2,
                                pscodsecc varchar2,
                                pstipo  varchar2,
                                psFechaProceso  varchar2,
                                psCodigoCliente VARCHAR2,
                                psCodigoPeriodoActual VARCHAR2,
                                psusuario VARCHAR2,
                                psIndicadorOrigen VARCHAR2,
                                psIndDesvAuto VARCHAR2) IS

   V_OID_HIST_GERE varchar2(50);
   V_PERD_OID_PERI_DESD varchar2(50);
   v_ua  varchar2(50);
   v_oid_clie varchar2(50);
   v_cod_secc varchar2(50);
   pscodsubreg varchar2(2) := '01';

   v_codigoLider    ZON_HISTO_GEREN.GERE%TYPE;
   v_indProgLider   BAS_PAIS.IND_PROG_LIDE%TYPE;

   lsCodPeriSiguiente      VARCHAR2(6);
   lnIdPais   NUMBER;
   lnIdCanal  NUMBER;
   lnIdMarca  NUMBER;
   lnIdPeriSiguiente  NUMBER;
   lnOidPeriodo NUMBER;
   BEGIN

     lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
     lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
     lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
     lnOidPeriodo   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psOidPeriodoValAsiLid, lnIdMarca, lnIdCanal);

     IF (pstipo = 'S') THEN
     /*begin
       select  distinct(r.clie_oid_clie), r.cod_secc
         into  v_oid_clie , v_cod_secc
         from zon_secci r
        where clie_oid_clie = GEN_PKG_GENER.gen_fn_devuelve_id_cliente(psCodigoCliente);
     end; */

       IF(psOidPeriodoValAsiLid <> psCodigoPeriodoActual) THEN
          BEGIN
            SELECT OID_HIST_GERE, PERD_OID_PERI_DESD, GERE
        INTO  V_OID_HIST_GERE, V_PERD_OID_PERI_DESD, v_codigoLider
              FROM ZON_HISTO_GEREN
             WHERE ua = pscodsubreg || pscodregi || pscodzona || pscodsecc
               AND lnOidPeriodo >= perd_oid_peri_desd
               AND (lnOidPeriodo <= perd_oid_peri_hast OR PERD_OID_PERI_HAST IS NULL);
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
            V_OID_HIST_GERE := '' ;
            V_PERD_OID_PERI_DESD  := '' ;
            v_codigoLider := '' ;
          END;

          IF(V_OID_HIST_GERE IS NOT NULL) THEN
            IF(V_PERD_OID_PERI_DESD = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psOidPeriodoValAsiLid)) THEN
              DELETE from ZON_HISTO_GEREN
               WHERE OID_HIST_GERE = V_OID_HIST_GERE;

          ELSE
                IF psIndicadorOrigen = 'W' THEN
                    UPDATE ZON_HISTO_GEREN
                    SET PERD_OID_PERI_HAST = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodoActual),
                        fec_hast = (select fec_fina-5 from cra_perio
                                    where oid_peri = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodoActual)),
                               IND_DESV_AUTO = 0,
                               usu_modi_web = PSUSUARIO,
                               fec_modi = SYSDATE
                    WHERE OID_HIST_GERE = V_OID_HIST_GERE;
                ELSE
                  UPDATE ZON_HISTO_GEREN
                  SET PERD_OID_PERI_HAST = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodoActual),
                        fec_hast = (select fec_fina-5 from cra_perio
                                           where oid_peri = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodoActual)),
                               IND_DESV_AUTO = psIndDesvAuto,
                             usu_modi = PSUSUARIO,
                             fec_modi = SYSDATE
                  WHERE OID_HIST_GERE = V_OID_HIST_GERE;
                END IF;

          END IF;
         END IF;

         ELSIF(psOidPeriodoValAsiLid = psCodigoPeriodoActual) THEN

          BEGIN
            SELECT oid_hist_gere, PERD_OID_PERI_DESD, GERE
              INTO v_oid_hist_gere, v_PERD_OID_PERI_DESD, v_codigoLider
              FROM ZON_HISTO_GEREN x
             WHERE lnOidPeriodo >= x.perd_oid_peri_desd
               AND (lnOidPeriodo <= x.perd_oid_peri_hast OR x.PERD_OID_PERI_HAST IS NULL)
               AND ua = pscodsubreg || pscodregi || pscodzona || pscodsecc;
            EXCEPTION
             WHEN NO_DATA_FOUND THEN
                  V_OID_HIST_GERE := '' ;
                  V_PERD_OID_PERI_DESD  := '' ;
                  v_codigoLider := '' ;
           END;

              IF(v_oid_hist_gere IS NOT NULL) THEN
                 IF(V_PERD_OID_PERI_DESD = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodoActual)) THEN
               DELETE from ZON_HISTO_GEREN
               WHERE OID_HIST_GERE = V_OID_HIST_GERE;

                   lsCodPeriSiguiente := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodoActual, lnIdPais, lnIdMarca, lnIdCanal, 1);
                   lnIdPeriSiguiente  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriSiguiente, lnIdMarca, lnIdCanal);

                   DELETE from ZON_HISTO_GEREN
                   WHERE OID_HIST_GERE = V_OID_HIST_GERE
                     AND PERD_OID_PERI_DESD = lnIdPeriSiguiente;

            ELSE
                    IF psIndicadorOrigen = 'W' THEN
                        UPDATE ZON_HISTO_GEREN
                          SET PERD_OID_PERI_HAST = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1)),
                              fec_hast = (select fec_fina-5 from cra_perio
                                          where oid_peri = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1))),
                                          IND_DESV_AUTO = 0,
                                          usu_modi_web = PSUSUARIO,
                                          fec_modi = SYSDATE
                        WHERE OID_HIST_GERE = V_OID_HIST_GERE;
                    ELSE
               UPDATE ZON_HISTO_GEREN
                      SET PERD_OID_PERI_HAST = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1)),
                          fec_hast = (select fec_fina-5 from cra_perio
                                   where oid_peri = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1))),
                                          IND_DESV_AUTO = psIndDesvAuto,
                      usu_modi = PSUSUARIO,
                          fec_modi = SYSDATE
                    WHERE OID_HIST_GERE = V_OID_HIST_GERE;
                END IF;
                  END IF;

                  /* Eliminando Clasificaci�n */
                  DELETE  FROM  mae_clien_clasi
                  WHERE   oid_clie_clas IN (
                        SELECT     a.oid_clie_clas
                        FROM       mae_clien_clasi a,
                                     mae_clien_tipo_subti b,
                                     MAE_TIPO_CLASI_CLIEN x,
                                     MAE_CLASI y,
                                     mae_clien m
                        WHERE      a.ctsu_oid_clie_tipo_subt = b.Oid_Clie_Tipo_Subt
                              AND      b.clie_oid_clie = m.oid_clie
                              AND      b.Ind_Ppal = 1
                              AND      a.tccl_oid_tipo_clasi = x.oid_tipo_clas
                              AND      x.cod_tipo_clas = '01'
                              AND      a.clas_oid_clas = y.oid_clas
                              AND      y.cod_clas = '01'
                              AND      b.sbti_oid_subt_clie = x.sbti_oid_subt_clie
                              AND      x.oid_tipo_clas = y.tccl_oid_tipo_clas
                              and      m.cod_clie IN (SELECT MA.COD_CLIE
                                                      FROM ZON_SECCI ZS,
                                                           MAE_CLIEN MA
                                                      WHERE ZZON_OID_ZONA = (select oid_zona
                                                                             from zon_zona where cod_zona = pscodzona)
                                                        AND COD_SECC = pscodsecc
                                                        AND ZS.CLIE_OID_CLIE = MA.OID_CLIE
                                                        AND ZS.CLIE_OID_CLIE IS NOT NULL
                                       )
                          );

                   UPDATE ZON_SECCI
                      SET CLIE_OID_CLIE = NULL
                    WHERE  ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona = pscodzona)
                   AND COD_SECC = pscodsecc ;
            END IF;

       END IF;

    BEGIN
          SELECT IND_PROG_LIDE INTO v_indProgLider
          FROM BAS_PAIS
         WHERE COD_PAIS = psCodigoPais;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            v_indProgLider := NULL;
    END;

     IF(v_indProgLider IS NOT NULL) THEN
          IF(psOidPeriodoValAsiLid = psCodigoPeriodoActual AND v_indProgLider = 3) THEN
                IF psIndicadorOrigen = 'W' THEN
                    LET_PKG_PROCE.LET_PR_PROCE_CLASI_LIDER(psCodigoPais,
                                                         'T',
                                                         'VD',
                                                         '2',
                                                         v_codigoLider,
                                                         GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1),
                                                         NULL);
                ELSE
            LET_PKG_PROCE.LET_PR_PROCE_CLASI_LIDER(psCodigoPais,
                                                   'T',
                                                   'VD',
                                                   '2',
                                                   v_codigoLider,
                                                   GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1),
                                                   psusuario);
               END IF;

          END IF;
          IF(psOidPeriodoValAsiLid = psCodigoPeriodoActual AND v_indProgLider = 4) THEN

                IF psIndicadorOrigen = 'W' THEN
                    LEC_PKG_PROCE.LEC_PR_ACTUA_CLASI_LIDER(psCodigoPais,
                                                     'T',
                                                     'VD',
                                                     '2',
                                                     v_codigoLider,
                                                     GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1),
                                                     '0',
                                                     NULL);
                ELSE
              LEC_PKG_PROCE.LEC_PR_ACTUA_CLASI_LIDER(psCodigoPais,
                                                     'T',
                                                     'VD',
                                                     '2',
                                                     v_codigoLider,
                                                     GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1),
                                                     '0',
                                                     psusuario);
               END IF;

          END IF;
     END IF;  

    ELSIF (pstipo = 'Z') THEN
  v_ua := pscodsubreg || pscodregi || pscodzona ;
          BEGIN
            SELECT oid_hist_gere, PERD_OID_PERI_DESD
              INTO v_oid_hist_gere, v_PERD_OID_PERI_DESD
              FROM ZON_HISTO_GEREN
             WHERE PERD_OID_PERI_HAST is null
               AND ua = v_ua;

     EXCEPTION
       WHEN NO_DATA_FOUND THEN
            V_OID_HIST_GERE := '' ;
            V_PERD_OID_PERI_DESD  := '' ;
          END;

      IF(v_oid_hist_gere IS NOT NULL) THEN
         IF v_PERD_OID_PERI_DESD = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodoActual) THEN
          delete zon_histo_geren where oid_hist_gere = v_oid_hist_gere;
         ELSE
            IF psIndicadorOrigen = 'W' THEN
                UPDATE ZON_HISTO_GEREN set
                PERD_OID_PERI_HAST = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1)),
                fec_hast = (select fec_fina-5
                             from cra_perio
                            where oid_peri = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1))), --campa�a anterior a la campa�a de proceso)
                usu_modi_web = psusuario,
                fec_modi = sysdate
                WHERE oid_hist_gere =  v_oid_hist_gere;
            ELSE
           UPDATE ZON_HISTO_GEREN set
         PERD_OID_PERI_HAST = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1)),
         fec_hast = (select fec_fina-5
                       from cra_perio
                      where oid_peri = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1))), --campa�a anterior a la campa�a de proceso)
         usu_modi = psusuario,
         fec_modi = sysdate
           WHERE oid_hist_gere =  v_oid_hist_gere;
            END IF;

         END IF;

         UPDATE zon_zona
            SET clie_oid_clie = null
          WHERE oid_zona = (select oid_zona from zon_zona where cod_zona = pscodzona);

      END IF;

    ELSIF (pstipo = 'R') THEN
        v_ua := pscodsubreg || pscodregi ;
       begin
      select oid_hist_gere, PERD_OID_PERI_DESD
        into v_oid_hist_gere, v_PERD_OID_PERI_DESD
        from ZON_HISTO_GEREN
      where PERD_OID_PERI_HAST is null
      and ua = v_ua;

       EXCEPTION
       WHEN NO_DATA_FOUND THEN
            V_OID_HIST_GERE := '' ;
            V_PERD_OID_PERI_DESD  := '' ;
       end;

	      IF (v_oid_hist_gere IS NOT NULL) THEN
	         IF v_PERD_OID_PERI_DESD = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodoActual) THEN
	            DELETE zon_histo_geren
	            WHERE oid_hist_gere = v_oid_hist_gere;

	         ELSE
            IF psIndicadorOrigen = 'W' THEN
               UPDATE ZON_HISTO_GEREN
                  SET PERD_OID_PERI_HAST = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1)),
                      fec_hast = (select fec_fina-5 from cra_perio WHERE oid_peri = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1))),
                      usu_modi_web = psusuario,
                      fec_modi = sysdate
                WHERE oid_hist_gere =  v_oid_hist_gere;
            ELSE
	           UPDATE ZON_HISTO_GEREN
	              SET PERD_OID_PERI_HAST = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1)),
	                  fec_hast = (select fec_fina-5 from cra_perio WHERE oid_peri = GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodoActual,-1))),
       usu_modi = psusuario,
       fec_modi = sysdate
	            WHERE oid_hist_gere =  v_oid_hist_gere;
	         END IF;
         END IF;

	         UPDATE zon_regio
	            SET clie_oid_clie = null
	          WHERE oid_regi = (select oid_regi from zon_regio where cod_regi = pscodregi);

	      END IF;

    END IF;

   EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_PROC_DESVI: ' || ls_sqlerrm);
  END LET_PR_PROC_DESVI;

  /**********************************************************************************
  Descripcion       : Proceso de Calculo de pedidos Objetivos por seccion y campa�a
  Fecha Creacion    : 10/06/2013
  Autor             : Danny Amaro
  **********************************************************************************/

  PROCEDURE LET_PR_CALCU_OBJET_PEDID_LIDER(psCodigoPais VARCHAR2,
                                           psCodigoMarca VARCHAR2,
                                           psCodigoCanal VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psUsuario VARCHAR2) IS

  vsCodProg               varchar2(3);
  vnValPorcAct            LET_CORPO_PARAM_PROGR.VAL_PORC_ACTI%TYPE;
  vsCodTram               LET_CORPO_PARAM_TRAMO.COD_TRAM%TYPE;
  vsCodPerFinTram         SEG_PERIO_CORPO.COD_PERI%TYPE;

  vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
  vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;
  vnOidPeriodoObj               CRA_PERIO.OID_PERI%TYPE;
  vsCodigoPeridoSiguiente SEG_PERIO_CORPO.COD_PERI%TYPE;
  vnOidPeriodoSiguiente   CRA_PERIO.OID_PERI%TYPE;
  vnOidMarca              SEG_MARCA.OID_MARC%TYPE;
  vnOidCanal              SEG_CANAL.OID_CANA%TYPE;
  vsCodigoPeridoSubSiguiente    SEG_PERIO_CORPO.COD_PERI%TYPE;
  vsCodigoPeridoFinalTramo      SEG_PERIO_CORPO.COD_PERI%TYPE;

  CURSOR cFuenteVentasReal(oidPeriodo NUMBER) IS
   SELECT DISTINCT
          zorg.cod_regi,
          zzon.cod_zona,
          zzon.oid_zona,
          zscc.cod_secc
     FROM zon_terri_admin       ztad,
          zon_secci             zscc,
          zon_zona              zzon,
          zon_regio             zorg,
          INT_FUENT_VENTAS_REAL FVR
    WHERE fvr.perd_oid_peri  = oidPeriodo
      AND ztad.terr_oid_terr = FVR.Terr_Oid_Terr
      AND ztad.perd_oid_peri_inic <= oidPeriodo
      AND (ztad.perd_oid_peri_fina is null OR ztad.perd_oid_peri_fina >= oidPeriodo)
      AND ztad.zscc_oid_secc = zscc.oid_secc
      AND zscc.zzon_oid_zona = zzon.oid_zona
      AND zzon.zorg_oid_regi = zorg.oid_regi;

  vnNumCampTram           NUMBER;
  vsCodPerAux             SEG_PERIO_CORPO.COD_PERI%TYPE;

  vnOidTerr               INT_FUENT_VENTAS_REAL.Terr_Oid_Terr%TYPE;
  vsCodSecc               ZON_SECCI.Cod_Secc%TYPE;
  vsCodZona               ZON_ZONA.Cod_Zona%TYPE;
  vnOidZona               ZON_ZONA.Oid_Zona%TYPE;
  vsCodRegi               ZON_REGIO.Cod_Regi%TYPE;

  vnSumActFinSecc         NUMBER;
  vnSumActFinZona         NUMBER;

  vnPesoSecc              NUMBER;
  vnNumObjExis            NUMBER;

  vnNumPedi               INT_SAB_VENTA_PREVI_ZONA.Num_Pedi%TYPE;

  vnPediObj               NUMBER;
  vsIndiMetCalc           VARCHAR(1);
    i                     NUMBER;

  BEGIN

    vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    vsCodigoPeridoSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,1);
    vnOidPeriodoSiguiente := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodigoPeridoSiguiente);
    vsCodigoPeridoSubSiguiente := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,2);
    vnOidPeriodoObj := vnOidPeriodo;

    --obtenemos parametros de programa
    BEGIN
        SELECT COD_PROG, VAL_PORC_ACTI, CAM_FIN
          INTO vsCodProg, vnValPorcAct, vsCodigoPeridoFinalTramo
    FROM LET_CORPO_PARAM_PROGR
         WHERE vsCodigoPeridoSubSiguiente >= CAM_INIC
          AND (CAM_FIN is null OR vsCodigoPeridoSubSiguiente <= CAM_FIN)
          AND IND_ACTI = 1;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            vsCodProg := NULL;
            vnValPorcAct := NULL;
    END;

    --obtenemos parametros del tramo a procesar
    IF vsCodProg IS NOT NULL THEN
        BEGIN
            SELECT COD_TRAM, CAM_FIN
              INTO vsCodTram, vsCodPerFinTram
    FROM LET_CORPO_PARAM_TRAMO
    WHERE COD_PROG = vsCodProg
               AND vsCodigoPeridoSubSiguiente >= CAM_INIC
               AND vsCodigoPeridoSubSiguiente <= CAM_FIN
               AND IND_ACTI = '1';
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                vsCodTram := NULL;
        END;

        IF vsCodTram IS NOT NULL THEN
           vnNumCampTram := ven_pkg_repor.ven_fn_devue_nume_campa(vsCodigoPeridoSubSiguiente,vsCodPerFinTram,vnOidPais,vnOidMarca,vnOidCanal);
           vsCodPerAux := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(vsCodigoPeridoSubSiguiente,vnOidPais,vnOidMarca,vnOidCanal,-1);
          --Lista de Secciones de Fuente Venta Real
             OPEN cFuenteVentasReal(vnOidPeriodoObj);
         LOOP FETCH cFuenteVentasReal INTO vsCodRegi,vsCodZona,vnOidZona,vsCodSecc;

         EXIT WHEN cFuenteVentasReal%NOTFOUND;
              --obtenemos la activas finales para la seccion
                        select zo.COD_ZONA,
                               SC.COD_SECC,
                               sum(f.NUM_ACTI_FINA)
                          into vsCodZona,
                               vsCodSecc,
                               vnSumActFinSecc
                          from INT_FUENT_VENTAS_REAL f,
                               ZON_TERRI_ADMIN       UA,
                               ZON_SECCI             SC,
                               zon_zona              zo,
                               zon_regio             rg
                         where f.PERD_OID_PERI = vnOidPeriodoObj
                           AND F.TERR_OID_TERR = UA.TERR_OID_TERR
                           and (vnOidPeriodoObj >= ua.PERD_OID_PERI_INIC or ua.PERD_OID_PERI_INIc is null)
                           and (vnOidPeriodoObj <= ua.PERD_OID_PERI_FINA or ua.PERD_OID_PERI_FINa is null)
                           AND UA.ZSCC_OID_SECC = SC.OID_SECC
                           and sc.ZZON_OID_ZONA = zo.OID_ZONA
                           and zo.ZORG_OID_REGI = rg.OID_REGI
                           and zo.COD_ZONA = vsCodZona
                           and sc.cod_secc = vsCodSecc
                         GROUP BY zo.COD_ZONA, SC.COD_SECC
                         ORDER BY zo.COD_ZONA, SC.COD_SECC;

              --obtenemos la activas finales para la zona
                        select zo.COD_ZONA, sum(f.NUM_ACTI_FINA)
                          into vsCodZona, vnSumActFinZona
                          from INT_FUENT_VENTAS_REAL f,
                               ZON_TERRI_ADMIN       UA,
                               ZON_SECCI             SC,
                               zon_zona              zo
                         where f.PERD_OID_PERI = vnOidPeriodoObj
                           AND F.TERR_OID_TERR = UA.TERR_OID_TERR
                           and (vnOidPeriodoObj >= ua.PERD_OID_PERI_INIC or
                               ua.PERD_OID_PERI_INIc is null)
                           and (vnOidPeriodoObj <= ua.PERD_OID_PERI_FINA or
                               ua.PERD_OID_PERI_FINa is null)
                           AND UA.ZSCC_OID_SECC = SC.OID_SECC
                           and sc.ZZON_OID_ZONA = zo.OID_ZONA
                           and zo.COD_ZONA = vsCodZona
                         group by zo.COD_ZONA;

              i := 1;
              vsCodPerAux := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(vsCodigoPeridoSubSiguiente,vnOidPais,vnOidMarca,vnOidCanal,-1);
              FOR i IN 1..vnNumCampTram LOOP
               vsCodPerAux := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(vsCodPerAux,vnOidPais,vnOidMarca,vnOidCanal,1);
               vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodPerAux);
               IF vsCodSecc is not null THEN
                  --obtener objetivo para la campa�a de calculo de objetivo
                  SELECT COUNT(1)
                    INTO vnNumObjExis
                    FROM LET_CORPO_SECCI_OBJET_PEDID
                   WHERE CAM_OBJE = vsCodPerAux
                     AND COD_ZONA = vsCodZona
                     AND COD_SECC = vsCodSecc
                     AND IND_ACTI = 1;


                  IF vnNumObjExis = 0 THEN
              IF vnSumActFinZona = 0 THEN
                vnPesoSecc := NULL;
              ELSE
                       vnPesoSecc := ROUND((vnSumActFinSecc / vnSumActFinZona), 4);
              END IF;

              BEGIN
                    SELECT NUM_PEDI
                      INTO vnNumPedi
                FROM INT_SAB_VENTA_PREVI_ZONA
                       WHERE COD_PERI = vsCodPerAux
                AND COD_ZONA = vsCodZona;

              EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    vnNumPedi := NULL;
              END;
              IF vnNumPedi IS NOT NULL THEN
                          vnPediObj := ROUND(vnNumPedi * vnPesoSecc);
                 vsIndiMetCalc := 'E';

                          INSERT INTO LET_CORPO_SECCI_OBJET_PEDID
                          (
                     	  COD_PAIS,
                          COD_PROG,
                          COD_REGI,
                          COD_ZONA,
                          COD_SECC,
                          CAM_OBJE,
                          NUM_PESO_SECC,
                          NUM_PEDI_OBJE,
                          NUM_ACTI_FINA_SECC,
                          NUM_ACTI_FINA_ZONA,
                          IND_METO_CALC_OBJE,
                          IND_ORIG_CALC,
                          IND_PROC,
                          COD_TRAM,
                          USU_CREA,
                          USU_MODI,
                          FEC_CREA,
                          FEC_MODI,
                          IND_ACTI
                          )
                          VALUES
                          (
                          psCodigoPais,
                          vsCodProg,
                          vsCodRegi,
                          vsCodZona,
                          vsCodSecc,
                            vsCodPerAux,
                          vnPesoSecc,
                          vnPediObj,
                          vnSumActFinSecc,
                          vnSumActFinZona,
                          vsIndiMetCalc,
                          'C',
                          'N',
                          vsCodTram,
                          psUsuario,
                          NULL,
                          SYSDATE,
                          NULL,
                          '1'
                          );

              ELSE
                         vnPediObj :=  ROUND(vnSumActFinSecc * (vnValPorcAct / 100));
                 vsIndiMetCalc := 'A';

                    BEGIN
                      SELECT COD_TRAM, COD_ZONA, COD_SECC
                        INTO vsCodTram, vsCodZona, vsCodSecc
                        FROM LET_CORPO_SECCI_OBJET_PEDID
                         WHERE COD_PAIS = psCodigoPais
                           AND COD_PROG = vsCodProg
                           AND COD_REGI = vsCodRegi
                         AND COD_ZONA = vsCodZona
                         AND COD_SECC = vsCodSecc
                           AND CAM_OBJE = vsCodPerAux
                         AND COD_TRAM = vsCodTram;
                     EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                         INSERT INTO LET_CORPO_SECCI_OBJET_PEDID
                               (
                                COD_PAIS,
                            COD_PROG,
                  COD_REGI,
                  COD_ZONA,
                  COD_SECC,
                  CAM_OBJE,
                  NUM_PESO_SECC,
                  NUM_PEDI_OBJE,
                  NUM_ACTI_FINA_SECC,
                  NUM_ACTI_FINA_ZONA,
                  IND_METO_CALC_OBJE,
                  IND_ORIG_CALC,
                  IND_PROC,
                  COD_TRAM,
                  USU_CREA,
                  USU_MODI,
                  FEC_CREA,
                  FEC_MODI,
                                IND_ACTI
                               )
                         VALUES
                               (
                                psCodigoPais,
                            vsCodProg,
                  vsCodRegi,
                  vsCodZona,
                  vsCodSecc,
                              vsCodPerAux,
                  vnPesoSecc,
                  vnPediObj,
                  vnSumActFinSecc,
                  vnSumActFinZona,
                  vsIndiMetCalc,
                  'C',
                  'N',
                  vsCodTram,
                  psUsuario,
                  NULL,
                  SYSDATE,
                  NULL,
                                '1'
                               );

                               EXIT;
                     END;

                  END IF;
            END IF;
          END IF;
       END LOOP;
         END LOOP;
       CLOSE cFuenteVentasReal;
      END IF;

    END IF;

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_OBJET_PEDID_LIDER: ' || ls_sqlerrm);
  END LET_PR_CALCU_OBJET_PEDID_LIDER;


  /**********************************************************************************
  Descripcion       : Proceso de Calcular Objetivo Ingresos Efectivos L�der
  Fecha Creacion    : 12/06/2013
  Autor             : Danny Amaro
  **********************************************************************************/
  procedure LET_PR_CALCU_OBJET_RET22_LIDER(psCodigoPais VARCHAR2,
                                           psCodigoRegion VARCHAR2,
                                           psCodigoMarca VARCHAR2,
                                           psCodigoCanal VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psTipoProceso VARCHAR2,
                                           psUsuario VARCHAR2)IS

  vsCodProg               LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
  vsIndRestReco           LET_CORPO_PARAM_PROGR.Ind_Rest_Alca_Ingr_Efec%TYPE;
  vnNumMinIngr            LET_CORPO_PARAM_PROGR.Num_Mini_Ingr%TYPE;
  vsNumMayIng             LET_CORPO_PARAM_PROGR.Num_Mini_Ingr%TYPE;
  vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
  vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;
  vsCodigoPeriodoSgte     SEG_PERIO_CORPO.COD_PERI%TYPE;
  vnOidPeriodoSgte        CRA_PERIO.OID_PERI%TYPE;
  vsCodigoPeriodoAnterior SEG_PERIO_CORPO.COD_PERI%TYPE;
  vnOidPeriodoAnterior    CRA_PERIO.OID_PERI%TYPE;
  vnOidMarca              SEG_MARCA.OID_MARC%TYPE;
  vnOidCanal              SEG_CANAL.OID_CANA%TYPE;

  --Cursor para universo de lideres
  CURSOR cLiderActivo(oidPeriodo NUMBER, codigoRegion ZON_REGIO.COD_REGI%TYPE) IS

  SELECT DISTINCT(GERE), COD_REGI, COD_ZONA
  FROM ZON_HISTO_GEREN
  WHERE oidPeriodo >= PERD_OID_PERI_DESD
    AND (oidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
    AND COD_SECC IS NOT NULL
    AND (codigoRegion is NULL OR COD_REGI = codigoRegion);

  vsCodGerente            ZON_HISTO_GEREN.Gere%TYPE;
  vsCodRegion             ZON_REGIO.COD_REGI%TYPE;
  vsCodZona               ZON_ZONA.Cod_Zona%TYPE;

  vnOidCliente            MAE_CLIEN.OID_CLIE%TYPE;
  vnCantRecomendadas      NUMBER;
  vnReten22               LET_CORPO_RANGO_RETEN.VAL_PORC_RETE_22%TYPE;
  vnNumIngrEfecObte       NUMBER;

  --Parametro para el Cursor
  vcCodigoReg             ZON_REGIO.COD_REGI%TYPE;

  vsCodPeriObjetivo       VARCHAR2(6);
  vsCodPeriRecom          VARCHAR2(6);
  vsOidPeriRecom          NUMBER(12);
  vnIndCalcOrig           VARCHAR2(1);
  vsCodTram               LET_CORPO_PARAM_TRAMO.COD_TRAM%TYPE;
  vsIndIngrEfec           LET_CORPO_PARAM_TRAMO.IND_ACTI_INGR_EFEC%TYPE;


  vsCodLA                 ZON_HISTO_GEREN.GERE%TYPE;
  vsCodRegLA              ZON_HISTO_GEREN.COD_REGI%TYPE;
  vsCodZonLA              ZON_HISTO_GEREN.COD_ZONA%TYPE;
  vsOidLA                 MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE%TYPE;

  BEGIN

    vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);


    --Variables Sgte
    vsCodigoPeriodoSgte := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,1);
    vnOidPeriodoSgte := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodigoPeriodoSgte);
    --Variables Actual
    vsCodigoPeriodoAnterior := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-1);
    vnOidPeriodoAnterior := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodigoPeriodoAnterior);


    vsCodPeriObjetivo := vsCodigoPeriodoSgte;

     IF psTipoProceso = 'R' THEN
         vcCodigoReg := psCodigoRegion;
     ELSE
         vcCodigoReg := NULL;
     END IF;

    --Calculo p' campa�a sgte.
    BEGIN
      SELECT COD_PROG, IND_REST_ALCA_INGR_EFEC, NUM_MINI_INGR
        INTO vsCodProg, vsIndRestReco, vnNumMinIngr
      FROM LET_CORPO_PARAM_PROGR
      WHERE vsCodPeriObjetivo >= CAM_INIC
      AND (CAM_FIN IS NULL OR vsCodPeriObjetivo <= CAM_FIN)
      AND IND_ACTI = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsCodProg := NULL;
    END;

    IF vsCodProg IS NOT NULL THEN
        BEGIN
          SELECT COD_TRAM, IND_ACTI_INGR_EFEC
            INTO vsCodTram, vsIndIngrEfec
            FROM LET_CORPO_PARAM_TRAMO
           WHERE COD_PROG = vsCodProg
             AND CAM_INIC <= vsCodPeriObjetivo
             AND CAM_FIN >= vsCodPeriObjetivo
             AND IND_ACTI = '1';
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCodTram := NULL;
            vsIndIngrEfec := NULL;
        END;

        --Si IND_ACTI_INGR_EFEC = 0 no calcular obj.
        IF (vsCodTram IS NOT NULL) AND (vsIndIngrEfec = '1') THEN
      OPEN cLiderActivo(vnOidPeriodo, vcCodigoReg);
      LOOP FETCH cLiderActivo INTO vsCodGerente, vsCodRegion, vsCodZona;
         EXIT WHEN cLiderActivo%NOTFOUND;
         vsCodPeriRecom := psCodigoPeriodo;
         vnOidCliente :=  Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(vsCodGerente);
                   vsOidPeriRecom := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodPeriRecom);
                   --(vsIndRestReco) Restriccion para contabilizar el numero de ingresos efectivos:
                   --[Z]:Zona [R]: Region [P]: Pais
         IF vsIndRestReco = 'P' THEN
                     SELECT COUNT(distinct(MCV.CLIE_OID_CLIE_VNDO)) INTO vnCantRecomendadas
             FROM MAE_CLIEN_VINCU MCV,
                  MAE_CLIEN_PRIME_CONTA MCP,
                            MAE_TIPO_VINCU MTP,
                            PED_SOLIC_CABEC PS,
                            PED_TIPO_SOLIC_PAIS TP,
                            PED_TIPO_SOLIC TS
            WHERE MCV.CLIE_OID_CLIE_VNDO = MCP.CLIE_OID_CLIE
              AND MCV.TIVC_OID_TIPO_VINC = MTP.OID_TIPO_VINC
              AND MTP.COD_TIPO_VINC = '03'
              AND MCV.CLIE_OID_CLIE_VNTE = vnOidCliente
                        AND MCP.PERD_OID_PERI = vsOidPeriRecom
                        AND  PS.TSPA_OID_TIPO_SOLI_PAIS =  TP.OID_TIPO_SOLI_PAIS
                         AND TP.TSOL_OID_TIPO_SOLI =  TS.OID_TIPO_SOLI
                         AND PS.FEC_FACT IS NOT NULL
                         AND PS.IND_TS_NO_CONSO = 1
                         AND PS.IND_OC = 1
                         AND PS.IND_PEDI_PRUE = 0
                         AND TS.IND_DEVO = 0
                         AND TS.IND_ANUL = 0
                         AND PS.PERD_OID_PERI =  vsOidPeriRecom
                         AND PS.CLIE_OID_CLIE = MCV.CLIE_OID_CLIE_VNDO;

         ELSIF vsIndRestReco = 'R' THEN
                     SELECT COUNT(distinct(MCV.CLIE_OID_CLIE_VNDO)) INTO vnCantRecomendadas
                 FROM MAE_CLIEN_VINCU MCV,
                      MAE_CLIEN_PRIME_CONTA MCP,
                      MAE_TIPO_VINCU MTP,
                      MAE_CLIEN_UNIDA_ADMIN MCUA,
                      ZON_TERRI_ADMIN ZTA,
                      ZON_SECCI ZC,
                      ZON_ZONA ZZ,
                            ZON_REGIO ZR,
                            PED_SOLIC_CABEC PS,
                            PED_TIPO_SOLIC_PAIS TP,
                            PED_TIPO_SOLIC TS
            WHERE MCV.CLIE_OID_CLIE_VNDO = MCP.CLIE_OID_CLIE
                     AND MCV.TIVC_OID_TIPO_VINC = MTP.OID_TIPO_VINC
                     AND MTP.COD_TIPO_VINC = '03'
                     AND MCP.CLIE_OID_CLIE = MCUA.CLIE_OID_CLIE
                     AND MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
                     AND ZTA.ZSCC_OID_SECC = ZC.OID_SECC
                     AND ZC.ZZON_OID_ZONA = ZZ.OID_ZONA
                     AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
                     AND ZR.COD_REGI = vsCodRegion
              AND MCV.CLIE_OID_CLIE_VNTE = vnOidCliente
                        AND MCP.PERD_OID_PERI = vsOidPeriRecom
                        AND MCUA.PERD_OID_PERI_INI <= vsOidPeriRecom
                        AND (MCUA.PERD_OID_PERI_FIN >= vsOidPeriRecom OR MCUA.PERD_OID_PERI_FIN IS NULL)
                        AND  PS.TSPA_OID_TIPO_SOLI_PAIS =  TP.OID_TIPO_SOLI_PAIS
                         AND TP.TSOL_OID_TIPO_SOLI =  TS.OID_TIPO_SOLI
                         AND PS.FEC_FACT IS NOT NULL
                         AND PS.IND_TS_NO_CONSO = 1
                         AND PS.IND_OC = 1
                         AND PS.IND_PEDI_PRUE = 0
                         AND TS.IND_DEVO = 0
                         AND TS.IND_ANUL = 0
                         AND PS.PERD_OID_PERI =  vsOidPeriRecom
                         AND PS.CLIE_OID_CLIE = MCV.CLIE_OID_CLIE_VNDO;

                        --IF vnCantRecomendadas = 0 THEN
                         -- SELECT *
                          --FROM MAE_CLIEN_UNIDA_ADMIN MCUA

                        --END ID;

         ELSIF vsIndRestReco = 'Z' THEN
                     SELECT COUNT(distinct(MCV.CLIE_OID_CLIE_VNDO)) INTO vnCantRecomendadas
             FROM MAE_CLIEN_VINCU MCV,
                  MAE_CLIEN_PRIME_CONTA MCP,
                  MAE_TIPO_VINCU MTP,
                  MAE_CLIEN_UNIDA_ADMIN MCUA,
                  ZON_TERRI_ADMIN ZTA,
                  ZON_SECCI ZC,
                  ZON_ZONA ZZ,
                            ZON_REGIO ZR,
                            PED_SOLIC_CABEC PS,
                            PED_TIPO_SOLIC_PAIS TP,
                            PED_TIPO_SOLIC TS
            WHERE MCV.CLIE_OID_CLIE_VNDO = MCP.CLIE_OID_CLIE
              AND MCV.TIVC_OID_TIPO_VINC = MTP.OID_TIPO_VINC
              AND MTP.COD_TIPO_VINC = '03'
              AND MCP.CLIE_OID_CLIE = MCUA.CLIE_OID_CLIE
              AND MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
              AND ZTA.ZSCC_OID_SECC = ZC.OID_SECC
              AND ZC.ZZON_OID_ZONA = ZZ.OID_ZONA
              AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
              AND ZR.COD_REGI = vsCodRegion
              AND ZZ.COD_ZONA = vsCodZona
              AND MCV.CLIE_OID_CLIE_VNTE = vnOidCliente
                        AND MCP.PERD_OID_PERI = vsOidPeriRecom
                        AND MCUA.PERD_OID_PERI_INI <= vsOidPeriRecom
                        AND (MCUA.PERD_OID_PERI_FIN >= vsOidPeriRecom OR MCUA.PERD_OID_PERI_FIN IS NULL)
                        AND  PS.TSPA_OID_TIPO_SOLI_PAIS =  TP.OID_TIPO_SOLI_PAIS
                         AND TP.TSOL_OID_TIPO_SOLI =  TS.OID_TIPO_SOLI
                         AND PS.FEC_FACT IS NOT NULL
                         AND PS.IND_TS_NO_CONSO = 1
                         AND PS.IND_OC = 1
                         AND PS.IND_PEDI_PRUE = 0
                         AND TS.IND_DEVO = 0
                         AND TS.IND_ANUL = 0
                         AND PS.PERD_OID_PERI =  vsOidPeriRecom
                         AND PS.CLIE_OID_CLIE = MCV.CLIE_OID_CLIE_VNDO;

         ELSE
           vnCantRecomendadas := 0;
         END IF;
                   --SE OBTIENE EL NUMERO MAYOR DE INGRESO
                   IF  vnCantRecomendadas > vnNumMinIngr THEN
                       vsNumMayIng := vnCantRecomendadas;
                   ELSE
                       vsNumMayIng := vnNumMinIngr;
                   END IF;
                   --SE OBTIENE EL %RETENCION22 PARA CON EL MAYOR NUMERO DE INGRESO
         BEGIN
                    SELECT VAL_PORC_RETE_22 INTO vnReten22
            FROM LET_CORPO_RANGO_RETEN
           WHERE COD_PROG = vsCodProg
                       AND NUM_INGR_INIC <= vsNumMayIng
                       AND NUM_INGR_FINA >= vsNumMayIng
                       AND IND_ACTI = 1;
         EXCEPTION
           WHEN NO_DATA_FOUND THEN
             vnReten22 := 0;
         END;

         IF vnCantRecomendadas > vnNumMinIngr THEN
                      vnNumIngrEfecObte := ROUND( ROUND(vnReten22/100,2) * vnCantRecomendadas);
         ELSE
                      vnNumIngrEfecObte := ROUND(ROUND(vnReten22/100,2) * vnNumMinIngr);
         END IF;

         --Se Verifica si existe el registro
         BEGIN
                    SELECT IND_ORIG_CALC
                           INTO vnIndCalcOrig
                           FROM LET_CORPO_LIDER_OBJET
          WHERE COD_CLIE_LIDE = vsCodGerente
                           AND CAM_OBJE = vsCodPeriObjetivo
                           AND IND_ACTI = 1;
         EXCEPTION
           WHEN NO_DATA_FOUND THEN
                     vnIndCalcOrig := NULL;
         END;

                   IF (vnIndCalcOrig IS NOT NULL) THEN
                      IF (vnIndCalcOrig <> 'X') THEN
           UPDATE LET_CORPO_LIDER_OBJET
               SET NUM_INGR_EFEC_OBJE = vnNumIngrEfecObte,
                   IND_PROC = 'N',
                                   --IND_ORIG_CALC = 'C',
                   USU_MODI = psUsuario,
                   FEC_MODI = SYSDATE
           WHERE COD_CLIE_LIDE = vsCodGerente
                      AND CAM_OBJE = vsCodPeriObjetivo;
                      --ELSE NO ACTUALIZAR
                      END IF;
         ELSE
            INSERT INTO LET_CORPO_LIDER_OBJET(
              COD_CLIE_LIDE, CAM_OBJE, NUM_INGR_EFEC_OBJE,
              IND_PROC, IND_ORIG_CALC,
              USU_CREA, USU_MODI, FEC_CREA, FEC_MODI,
              IND_ACTI)
            VALUES(
              vsCodGerente, vsCodPeriObjetivo, vnNumIngrEfecObte,
              'N', 'C',
              psUsuario, NULL, SYSDATE, NULL,
              '1');

         END IF;


      END LOOP;

      CLOSE cLiderActivo;
        END IF;

    END IF;

    --Calculo p' campa�a actual.
    vsCodPeriObjetivo := psCodigoPeriodo;

    BEGIN
      SELECT COD_PROG, IND_REST_ALCA_INGR_EFEC, NUM_MINI_INGR
        INTO vsCodProg, vsIndRestReco, vnNumMinIngr
      FROM LET_CORPO_PARAM_PROGR
      WHERE vsCodPeriObjetivo >= CAM_INIC
      AND (CAM_FIN IS NULL OR vsCodPeriObjetivo <= CAM_FIN)
      AND IND_ACTI = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsCodProg := NULL;
    END;

    IF vsCodProg IS NOT NULL THEN
       BEGIN
          SELECT COD_TRAM, IND_ACTI_INGR_EFEC
            INTO vsCodTram, vsIndIngrEfec
            FROM LET_CORPO_PARAM_TRAMO
           WHERE COD_PROG = vsCodProg
             AND CAM_INIC <= vsCodPeriObjetivo
             AND CAM_FIN >= vsCodPeriObjetivo
             AND IND_ACTI = '1';
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCodTram := NULL;
            vsIndIngrEfec := NULL;
        END;

      --Si IND_ACTI_INGR_EFEC = 0 no calcular obj.
      IF (vsCodTram IS NOT NULL) AND (vsIndIngrEfec = '1') THEN
      OPEN cLiderActivo(vnOidPeriodo, vcCodigoReg);
      LOOP FETCH cLiderActivo INTO vsCodGerente, vsCodRegion, vsCodZona;
         EXIT WHEN cLiderActivo%NOTFOUND;
         vsCodPeriRecom := vsCodigoPeriodoAnterior;
         vnOidCliente :=  Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(vsCodGerente);
               vsOidPeriRecom := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodPeriRecom);

         IF vsIndRestReco = 'P' THEN
                 SELECT COUNT(distinct(MCV.CLIE_OID_CLIE_VNDO)) INTO vnCantRecomendadas
             FROM MAE_CLIEN_VINCU MCV,
                  MAE_CLIEN_PRIME_CONTA MCP,
                        MAE_TIPO_VINCU MTP,
                        PED_SOLIC_CABEC PS,
                        PED_TIPO_SOLIC_PAIS TP,
                        PED_TIPO_SOLIC TS
            WHERE MCV.CLIE_OID_CLIE_VNDO = MCP.CLIE_OID_CLIE
              AND MCV.TIVC_OID_TIPO_VINC = MTP.OID_TIPO_VINC
              AND MTP.COD_TIPO_VINC = '03'
              AND MCV.CLIE_OID_CLIE_VNTE = vnOidCliente
                    AND MCP.PERD_OID_PERI = vsOidPeriRecom
                    AND  PS.TSPA_OID_TIPO_SOLI_PAIS =  TP.OID_TIPO_SOLI_PAIS
                     AND TP.TSOL_OID_TIPO_SOLI =  TS.OID_TIPO_SOLI
                     AND PS.FEC_FACT IS NOT NULL
                     AND PS.IND_TS_NO_CONSO = 1
                     AND PS.IND_OC = 1
                     AND PS.IND_PEDI_PRUE = 0
                     AND TS.IND_DEVO = 0
                     AND TS.IND_ANUL = 0
                     AND PS.PERD_OID_PERI =  vsOidPeriRecom
                     AND PS.CLIE_OID_CLIE = MCV.CLIE_OID_CLIE_VNDO;

               --REGION
         ELSIF vsIndRestReco = 'R' THEN
                     --SE BUSCA LA LIDER EN HISTO_GEREN CON CAMPA�A ANTERIOR
                      BEGIN
                      SELECT DISTINCT(GERE), COD_REGI, COD_ZONA
                      INTO vsCodLA,  vsCodRegLA, vsCodZonLA
                      FROM ZON_HISTO_GEREN
                      WHERE vsOidPeriRecom >= PERD_OID_PERI_DESD
                        AND (vsOidPeriRecom <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
                        AND COD_SECC IS NOT NULL
                        AND GERE = vsCodGerente;
                      EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            vsCodLA := NULL;
                            vsCodRegLA := null;
                            vscodZonLA:= null;
                      END;
                      --SI NOO SE ENCUENTRA EL LIDER SE BUSCA EN UNIDAD_ADMIN
                      IF vsCodLA IS NULL THEN
                            BEGIN
                             SELECT MCUA.CLIE_OID_CLIE, ZR.COD_REGI
                             INTO vsOidLA, vsCodRegLA
                             FROM MAE_CLIEN_UNIDA_ADMIN MCUA,
                                  ZON_TERRI_ADMIN ZTA,
                                  ZON_SECCI ZC,
                                  ZON_ZONA ZZ,
                                  ZON_REGIO ZR
                             WHERE MCUA.CLIE_OID_CLIE = vnOidCliente
                             AND vsOidPeriRecom >= PERD_OID_PERI_INI
                             AND (vsOidPeriRecom <= PERD_OID_PERI_FIN OR PERD_OID_PERI_FIN IS NULL)
                             AND MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
                             AND ZTA.ZSCC_OID_SECC = ZC.OID_SECC
                             AND ZC.ZZON_OID_ZONA = ZZ.OID_ZONA
                             AND ZZ.ZORG_OID_REGI = ZR.OID_REGI;
                            EXCEPTION
                               WHEN NO_DATA_FOUND THEN
                                 vsOidLA := NULL;
                                 vsCodRegLA := NULL;
                            END;

                           --CONTAMOS LAS RECOMENDADAS DE LA LIDER DEL PERIODO ANTERIOR
                           SELECT COUNT(distinct(MCV.CLIE_OID_CLIE_VNDO)) INTO vnCantRecomendadas
                 FROM MAE_CLIEN_VINCU MCV,
                      MAE_CLIEN_PRIME_CONTA MCP,
                      MAE_TIPO_VINCU MTP,
                      MAE_CLIEN_UNIDA_ADMIN MCUA,
                      ZON_TERRI_ADMIN ZTA,
                      ZON_SECCI ZC,
                      ZON_ZONA ZZ,
                                  ZON_REGIO ZR,
                                  PED_SOLIC_CABEC PS,
                                  PED_TIPO_SOLIC_PAIS TP,
                                  PED_TIPO_SOLIC TS
            WHERE MCV.CLIE_OID_CLIE_VNDO = MCP.CLIE_OID_CLIE
                     AND MCV.TIVC_OID_TIPO_VINC = MTP.OID_TIPO_VINC
                     AND MTP.COD_TIPO_VINC = '03'
                     AND MCP.CLIE_OID_CLIE = MCUA.CLIE_OID_CLIE
                     AND MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
                     AND ZTA.ZSCC_OID_SECC = ZC.OID_SECC
                     AND ZC.ZZON_OID_ZONA = ZZ.OID_ZONA
                     AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
                             AND ZR.COD_REGI = vsCodRegLA
                             AND MCV.CLIE_OID_CLIE_VNTE = vsOidLA
                             AND MCP.PERD_OID_PERI = vsOidPeriRecom
                             AND MCUA.PERD_OID_PERI_INI <= vsOidPeriRecom
                             AND (MCUA.PERD_OID_PERI_FIN >= vsOidPeriRecom OR MCUA.PERD_OID_PERI_FIN IS NULL)
                             AND  PS.TSPA_OID_TIPO_SOLI_PAIS =  TP.OID_TIPO_SOLI_PAIS
                             AND TP.TSOL_OID_TIPO_SOLI =  TS.OID_TIPO_SOLI
                             AND PS.FEC_FACT IS NOT NULL
                             AND PS.IND_TS_NO_CONSO = 1
                             AND PS.IND_OC = 1
                             AND PS.IND_PEDI_PRUE = 0
                             AND TS.IND_DEVO = 0
                             AND TS.IND_ANUL = 0
                             AND PS.PERD_OID_PERI =  vsOidPeriRecom
                             AND PS.CLIE_OID_CLIE = MCV.CLIE_OID_CLIE_VNDO;

                      ELSE --SI SE ENCUENTRA EL LIDER EN LA CAMPA�A ANTERIOR
                        vnOidCliente :=  Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(vsCodLA);
                        SELECT COUNT(distinct(MCV.CLIE_OID_CLIE_VNDO)) INTO vnCantRecomendadas
                         FROM MAE_CLIEN_VINCU MCV,
                              MAE_CLIEN_PRIME_CONTA MCP,
                              MAE_TIPO_VINCU MTP,
                              MAE_CLIEN_UNIDA_ADMIN MCUA,
                              ZON_TERRI_ADMIN ZTA,
                              ZON_SECCI ZC,
                              ZON_ZONA ZZ,
                              ZON_REGIO ZR,
                              PED_SOLIC_CABEC PS,
                              PED_TIPO_SOLIC_PAIS TP,
                              PED_TIPO_SOLIC TS
                       WHERE MCV.CLIE_OID_CLIE_VNDO = MCP.CLIE_OID_CLIE
                         AND MCV.TIVC_OID_TIPO_VINC = MTP.OID_TIPO_VINC
                         AND MTP.COD_TIPO_VINC = '03'
                         AND MCP.CLIE_OID_CLIE = MCUA.CLIE_OID_CLIE
                         AND MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
                         AND ZTA.ZSCC_OID_SECC = ZC.OID_SECC
                         AND ZC.ZZON_OID_ZONA = ZZ.OID_ZONA
                         AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
                         AND ZR.COD_REGI = vscodRegLA
              AND MCV.CLIE_OID_CLIE_VNTE = vnOidCliente
                    AND MCP.PERD_OID_PERI = vsOidPeriRecom
                    AND MCUA.PERD_OID_PERI_INI <= vsOidPeriRecom
                         AND (MCUA.PERD_OID_PERI_FIN >= vsOidPeriRecom OR MCUA.PERD_OID_PERI_FIN IS NULL)
                         AND  PS.TSPA_OID_TIPO_SOLI_PAIS =  TP.OID_TIPO_SOLI_PAIS
                         AND TP.TSOL_OID_TIPO_SOLI =  TS.OID_TIPO_SOLI
                         AND PS.FEC_FACT IS NOT NULL
                         AND PS.IND_TS_NO_CONSO = 1
                         AND PS.IND_OC = 1
                         AND PS.IND_PEDI_PRUE = 0
                         AND TS.IND_DEVO = 0
                         AND TS.IND_ANUL = 0
                         AND PS.PERD_OID_PERI =  vsOidPeriRecom
                         AND PS.CLIE_OID_CLIE = MCV.CLIE_OID_CLIE_VNDO;

                      END IF;
               --ZONA
         ELSIF vsIndRestReco = 'Z' THEN
                      --SE BUSCA LA LIDER EN HISTO_GEREN CON CAMPA�A ANTERIOR
                      BEGIN
                      SELECT DISTINCT(GERE), COD_REGI, COD_ZONA
                      INTO vsCodLA,  vsCodRegLA, vsCodZonLA
                      FROM ZON_HISTO_GEREN
                      WHERE vsOidPeriRecom >= PERD_OID_PERI_DESD
                        AND (vsOidPeriRecom <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
                        AND COD_SECC IS NOT NULL
                        AND GERE = vsCodGerente;
                      EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            vsCodLA := NULL;
                            vsCodRegLA := null;
                            vscodZonLA:= null;
                      END;

                      --SI NOO SE ENCUENTRA EL LIDER SE BUSCA EN UNIDAD_ADMIN
                      IF vsCodLA IS NULL THEN
                            BEGIN
                             SELECT MCUA.CLIE_OID_CLIE, ZR.COD_REGI, ZZ.COD_ZONA
                             INTO vsOidLA, vsCodRegLA, vsCodZonLA
                             FROM MAE_CLIEN_UNIDA_ADMIN MCUA,
                                  ZON_TERRI_ADMIN ZTA,
                                  ZON_SECCI ZC,
                                  ZON_ZONA ZZ,
                                  ZON_REGIO ZR
                             WHERE MCUA.CLIE_OID_CLIE = vnOidCliente
                             AND vsOidPeriRecom >= PERD_OID_PERI_INI
                             AND (vsOidPeriRecom <= PERD_OID_PERI_FIN OR PERD_OID_PERI_FIN IS NULL)
                             AND MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
                             AND ZTA.ZSCC_OID_SECC = ZC.OID_SECC
                             AND ZC.ZZON_OID_ZONA = ZZ.OID_ZONA
                             AND ZZ.ZORG_OID_REGI = ZR.OID_REGI;
                            EXCEPTION
                               WHEN NO_DATA_FOUND THEN
                                 vsOidLA := NULL;
                                 vsCodRegLA := NULL;
                            END;

                           --CONTAMOS LAS RECOMENDADAS DE LA LIDER DEL PERIODO ANTERIOR
                           SELECT COUNT(distinct(MCV.CLIE_OID_CLIE_VNDO)) INTO vnCantRecomendadas
             FROM MAE_CLIEN_VINCU MCV,
                  MAE_CLIEN_PRIME_CONTA MCP,
                  MAE_TIPO_VINCU MTP,
                  MAE_CLIEN_UNIDA_ADMIN MCUA,
                  ZON_TERRI_ADMIN ZTA,
                  ZON_SECCI ZC,
                  ZON_ZONA ZZ,
                                  ZON_REGIO ZR,
                                  PED_SOLIC_CABEC PS,
                                  PED_TIPO_SOLIC_PAIS TP,
                                  PED_TIPO_SOLIC TS
            WHERE MCV.CLIE_OID_CLIE_VNDO = MCP.CLIE_OID_CLIE
              AND MCV.TIVC_OID_TIPO_VINC = MTP.OID_TIPO_VINC
              AND MTP.COD_TIPO_VINC = '03'
              AND MCP.CLIE_OID_CLIE = MCUA.CLIE_OID_CLIE
              AND MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
              AND ZTA.ZSCC_OID_SECC = ZC.OID_SECC
              AND ZC.ZZON_OID_ZONA = ZZ.OID_ZONA
              AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
                             AND ZR.COD_REGI = vsCodRegLA
                             AND ZZ.COD_ZONA = vsCodZonLA
                             AND MCV.CLIE_OID_CLIE_VNTE = vsOidLA
                             AND MCP.PERD_OID_PERI = vsOidPeriRecom
                             AND MCUA.PERD_OID_PERI_INI <= vsOidPeriRecom
                             AND (MCUA.PERD_OID_PERI_FIN >= vsOidPeriRecom OR MCUA.PERD_OID_PERI_FIN IS NULL)
                             AND  PS.TSPA_OID_TIPO_SOLI_PAIS =  TP.OID_TIPO_SOLI_PAIS
                             AND TP.TSOL_OID_TIPO_SOLI =  TS.OID_TIPO_SOLI
                             AND PS.FEC_FACT IS NOT NULL
                             AND PS.IND_TS_NO_CONSO = 1
                             AND PS.IND_OC = 1
                             AND PS.IND_PEDI_PRUE = 0
                             AND TS.IND_DEVO = 0
                             AND TS.IND_ANUL = 0
                             AND PS.PERD_OID_PERI =  vsOidPeriRecom
                             AND PS.CLIE_OID_CLIE = MCV.CLIE_OID_CLIE_VNDO;

                      ELSE --SI SE ENCUENTRA EL LIDER EN LA CAMPA�A ANTERIOR
                          vnOidCliente :=  Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(vsCodLA);
                          SELECT COUNT(distinct(MCV.CLIE_OID_CLIE_VNDO)) INTO vnCantRecomendadas
                           FROM MAE_CLIEN_VINCU MCV,
                                MAE_CLIEN_PRIME_CONTA MCP,
                                MAE_TIPO_VINCU MTP,
                                MAE_CLIEN_UNIDA_ADMIN MCUA,
                                ZON_TERRI_ADMIN ZTA,
                                ZON_SECCI ZC,
                                ZON_ZONA ZZ,
                                ZON_REGIO ZR,
                                PED_SOLIC_CABEC PS,
                                PED_TIPO_SOLIC_PAIS TP,
                                PED_TIPO_SOLIC TS
                          WHERE MCV.CLIE_OID_CLIE_VNDO = MCP.CLIE_OID_CLIE
                            AND MCV.TIVC_OID_TIPO_VINC = MTP.OID_TIPO_VINC
                            AND MTP.COD_TIPO_VINC = '03'
                            AND MCP.CLIE_OID_CLIE = MCUA.CLIE_OID_CLIE
                            AND MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
                            AND ZTA.ZSCC_OID_SECC = ZC.OID_SECC
                            AND ZC.ZZON_OID_ZONA = ZZ.OID_ZONA
                            AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
                            AND ZR.COD_REGI = vsCodRegLA
                            AND ZZ.COD_ZONA = vsCodZonLA
              AND MCV.CLIE_OID_CLIE_VNTE = vnOidCliente
                    AND MCP.PERD_OID_PERI = vsOidPeriRecom
                    AND MCUA.PERD_OID_PERI_INI <= vsOidPeriRecom
                            AND (MCUA.PERD_OID_PERI_FIN >= vsOidPeriRecom OR MCUA.PERD_OID_PERI_FIN IS NULL)
                            AND  PS.TSPA_OID_TIPO_SOLI_PAIS =  TP.OID_TIPO_SOLI_PAIS
                             AND TP.TSOL_OID_TIPO_SOLI =  TS.OID_TIPO_SOLI
                             AND PS.FEC_FACT IS NOT NULL
                             AND PS.IND_TS_NO_CONSO = 1
                             AND PS.IND_OC = 1
                             AND PS.IND_PEDI_PRUE = 0
                             AND TS.IND_DEVO = 0
                             AND TS.IND_ANUL = 0
                             AND PS.PERD_OID_PERI =  vsOidPeriRecom
                             AND PS.CLIE_OID_CLIE = MCV.CLIE_OID_CLIE_VNDO;
                      END IF;

         ELSE
           vnCantRecomendadas := 0;
         END IF;

               --SE OBTIENE EL NUMERO MAYOR DE INGRESO
               IF  vnCantRecomendadas > vnNumMinIngr THEN
                   vsNumMayIng := vnCantRecomendadas;
               ELSE
                   vsNumMayIng := vnNumMinIngr;
               END IF;

               --SE OBTIENE EL %RETENCION22 PARA CON EL MAYOR NUMERO DE INGRESO
         BEGIN
          SELECT VAL_PORC_RETE_22 INTO vnReten22
            FROM LET_CORPO_RANGO_RETEN
           WHERE COD_PROG = vsCodProg
                   AND NUM_INGR_INIC <= vsNumMayIng
                   AND NUM_INGR_FINA >= vsNumMayIng
                   AND IND_ACTI = 1;
         EXCEPTION
           WHEN NO_DATA_FOUND THEN
             vnReten22 := 0;
         END;

         IF vnCantRecomendadas > vnNumMinIngr THEN
                  vnNumIngrEfecObte := ROUND(ROUND(vnReten22/100,2) * vnCantRecomendadas);
         ELSE
                  vnNumIngrEfecObte := ROUND(ROUND(vnReten22/100,2) * vnNumMinIngr);
         END IF;

         --Se Verifica si existe el registro
         BEGIN
                SELECT IND_ORIG_CALC
                  INTO vnIndCalcOrig
                FROM LET_CORPO_LIDER_OBJET
          WHERE COD_CLIE_LIDE = vsCodGerente
                      AND CAM_OBJE = vsCodPeriObjetivo
                      AND IND_ACTI = 1;
         EXCEPTION
           WHEN NO_DATA_FOUND THEN
                 vnIndCalcOrig := NULL;
         END;

               IF (vnIndCalcOrig IS NOT NULL) THEN
                  IF (vnIndCalcOrig <> 'X') THEN
           UPDATE LET_CORPO_LIDER_OBJET
               SET NUM_INGR_EFEC_OBJE = vnNumIngrEfecObte,
                   IND_PROC = 'N',
                             --IND_ORIG_CALC = 'C',
                   USU_MODI = psUsuario,
                   FEC_MODI = SYSDATE
           WHERE COD_CLIE_LIDE = vsCodGerente
                      AND CAM_OBJE = vsCodPeriObjetivo;
                  --ELSE NO ACTUALIZAR;

                  END IF;
         ELSE
         INSERT INTO LET_CORPO_LIDER_OBJET(
              COD_CLIE_LIDE, CAM_OBJE, NUM_INGR_EFEC_OBJE,
              IND_PROC, IND_ORIG_CALC,
              USU_CREA, USU_MODI, FEC_CREA, FEC_MODI,
              IND_ACTI)
         VALUES(
              vsCodGerente, vsCodPeriObjetivo, vnNumIngrEfecObte,
              'N', 'C',
              psUsuario, NULL, SYSDATE, NULL,
              '1');
         END IF;

      END LOOP;

      CLOSE cLiderActivo;
      END IF;

    END IF;

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_OBJET_RET22_LIDER: ' || ls_sqlerrm);
  END LET_PR_CALCU_OBJET_RET22_LIDER;

  /**********************************************************************************
  Descripcion       : Proceso de Calculo Objetivo Retencion 3/3 Seccion
  Fecha Creacion    : 14/06/2013
  Autor             : Danny Amaro
  **********************************************************************************/
  PROCEDURE LET_PR_CALCU_OBJET_RET33_LIDER(psCodigoPais VARCHAR2,
                                           psCodigoRegion VARCHAR2,
                                           psCodigoMarca VARCHAR2,
                                           psCodigoCanal VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psTipoProceso VARCHAR2,
                                           psUsuario VARCHAR2)IS

  vsCodProg               LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
  vsNumMinIng             LET_CORPO_PARAM_PROGR.NUM_MINI_INGR%TYPE;
  vsNumMayIng             LET_CORPO_PARAM_PROGR.NUM_MINI_INGR%TYPE;
  vsNumReg                NUMBER;
  vsIndOrigCalc           VARCHAR2(1);

  vsCodPeriFact           VARCHAR2(6);
  vsCodPeriIngrSgte       VARCHAR2(6);
  vsCodPeriIngrActual     VARCHAR2(6);
  vsCodPeriObjetivo       VARCHAR2(6);

  vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
  vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;
  vnOidPeriodoIngrSgte    CRA_PERIO.OID_PERI%TYPE;
  vnOidPeriodoIngrActual  CRA_PERIO.OID_PERI%TYPE;
  vnOidMarca              SEG_MARCA.OID_MARC%TYPE;
  vnOidCanal              SEG_CANAL.OID_CANA%TYPE;

  vsCodRegi               ZON_REGIO.COD_REGI%TYPE;
  vsCodZona               ZON_ZONA.COD_ZONA%TYPE;
  vsCodSecc               ZON_SECCI.COD_SECC%TYPE;
  vnNumIngrSecc           NUMBER;

  vnPorcReten33           LET_CORPO_RANGO_RETEN.VAL_PORC_RETE_33%TYPE;
  vnObje33                NUMBER;
  vsRet33                 NUMBER;

  --Parametro para el Cursor
  vcCodigoReg             ZON_REGIO.COD_REGI%TYPE;

  vsCodTram               LET_CORPO_PARAM_TRAMO.COD_TRAM%TYPE;
  vsIndIngrEfec33         LET_CORPO_PARAM_TRAMO.IND_ACTI_RETE_33%TYPE;

  --Cursor para universo de consultoras
  CURSOR cIngresoConsulSecc(oidPeriodo CRA_PERIO.OID_PERI%TYPE,
                                    oidPeriodoIngr CRA_PERIO.OID_PERI%TYPE,
                                    codigoRegion ZON_REGIO.COD_REGI%TYPE) IS

        SELECT ZR.COD_REGI, ZZ.COD_ZONA, ZS.COD_SECC, COUNT(1)
          FROM MAE_CLIEN_UNIDA_ADMIN MCUA,
               ZON_TERRI_ADMIN       ZTA,
               MAE_CLIEN_HISTO_ESTAT MCHE,
               MAE_ESTAT_CLIEN       MEC,
               ZON_SECCI             ZS,
               ZON_ZONA              ZZ,
               ZON_REGIO             ZR
         WHERE MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
           AND MCUA.CLIE_OID_CLIE = MCHE.CLIE_OID_CLIE
           AND ZTA.ZSCC_OID_SECC = ZS.OID_SECC
           AND ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
           AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
           AND (codigoRegion is NULL OR ZR.COD_REGI = codigoRegion)
           AND MCUA.PERD_OID_PERI_INI <= oidPeriodo
           AND (MCUA.PERD_OID_PERI_FIN >= oidPeriodo OR MCUA.PERD_OID_PERI_FIN IS NULL)
           AND MCHE.PERD_OID_PERI = oidPeriodoIngr
           AND MCHE.ESTA_OID_ESTA_CLIE = MEC.OID_ESTA_CLIE
           AND MEC.COD_ESTA_CLIE IN ('02', '08')
         GROUP BY ZR.COD_REGI, ZZ.COD_ZONA, ZS.COD_SECC;

  --Cursor para universo de secciones
  CURSOR cIngresoConsul2Secc(oidPeriodo CRA_PERIO.OID_PERI%TYPE,
                             codigoRegion ZON_REGIO.COD_REGI%TYPE) IS
         SELECT ZR.COD_REGI, ZZ.COD_ZONA, ZS.COD_SECC
         FROM  ZON_SECCI             ZS,
               ZON_ZONA              ZZ,
               ZON_REGIO             ZR
               WHERE ZR.OID_REGI = ZZ.ZORG_OID_REGI
               AND ZZ.OID_ZONA = ZS.ZZON_OID_ZONA
               AND (codigoRegion is NULL OR ZR.COD_REGI = codigoRegion)
               AND ZR.PERD_OID_PERI_INIC <= oidPeriodo
               AND (ZR.PERD_OID_PERI_FINA >= oidPeriodo OR ZR.PERD_OID_PERI_FINA IS NULL)
               AND ZZ.PERD_OID_PERI_INIC <= oidPeriodo
               AND (ZZ.PERD_OID_PERI_FINA >= oidPeriodo OR ZZ.PERD_OID_PERI_FINA IS NULL)
               AND ZS.PERD_OID_PERI_INIC <= oidPeriodo
               AND (ZS.PERD_OID_PERI_FINA >= oidPeriodo OR ZS.PERD_OID_PERI_FINA IS NULL);

  BEGIN

    vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    vsCodPeriObjetivo := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,1);
    --VARIABLES P' CAMPA�A SGTE
    vsCodPeriIngrSgte := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-1);
    vnOidPeriodoIngrSgte := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodPeriIngrSgte);
    --VARIABLES P' CAMPA�A ACTUAL
    vsCodPeriIngrActual := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-2);
    vnOidPeriodoIngrActual := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodPeriIngrActual);

    --BORRAMOS REGISTROS CON PERIODO OBJETIVO(psCodigoPeriodo y vsCodPeriObjetivo)
     IF psTipoProceso = 'R' THEN
         vcCodigoReg := psCodigoRegion;

          DELETE FROM
            LET_CORPO_SECCI_RESUL
            WHERE CAM_OBJE IN (psCodigoPeriodo ,vsCodPeriObjetivo)
            AND COD_REGI = vcCodigoReg;
          DELETE FROM
            LET_CORPO_SECCI_OBJET
            WHERE CAM_OBJE IN (psCodigoPeriodo ,vsCodPeriObjetivo)
            AND COD_REGI = vcCodigoReg
            AND IND_ORIG_CALC = 'C'
            AND IND_ORIG_CALC_44 = 'C';

     ELSE
         vcCodigoReg := NULL;
         DELETE FROM
            LET_CORPO_SECCI_RESUL
            WHERE CAM_OBJE IN (psCodigoPeriodo ,vsCodPeriObjetivo);
         DELETE FROM
            LET_CORPO_SECCI_OBJET
            WHERE CAM_OBJE IN (psCodigoPeriodo ,vsCodPeriObjetivo)
            AND IND_ORIG_CALC = 'C'
            AND IND_ORIG_CALC_44 = 'C';
     END IF;

    /*********** CALCULO OBJETIVOS P' CAMPA�A SGTE********/
    BEGIN
      SELECT COD_PROG, NUM_MINI_INGR
        INTO vsCodProg, vsNumMinIng
        FROM LET_CORPO_PARAM_PROGR
       WHERE vsCodPeriObjetivo >= CAM_INIC
         AND (CAM_FIN IS NULL OR vsCodPeriObjetivo <= CAM_FIN)
         AND IND_ACTI = 1;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vsCodProg := NULL;
    END;

     IF vsCodProg IS NOT NULL THEN
        BEGIN
          SELECT COD_TRAM, IND_ACTI_RETE_33
            INTO vsCodTram, vsIndIngrEfec33
            FROM LET_CORPO_PARAM_TRAMO
           WHERE COD_PROG = vsCodProg
             AND CAM_INIC <= vsCodPeriObjetivo
             AND CAM_FIN >= vsCodPeriObjetivo
             AND IND_ACTI = '1';
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCodTram := NULL;
            vsIndIngrEfec33 := NULL;
        END;

        --Si IND_ACTI_RETE_33 = 0 no calcular obj.
        IF (vsCodTram IS NOT NULL) AND (vsIndIngrEfec33 = '1') THEN
          OPEN cIngresoConsulSecc(vnOidPeriodo, vnOidPeriodoIngrSgte, vcCodigoReg);

          LOOP FETCH cIngresoConsulSecc INTO vsCodRegi, vsCodZona, vsCodSecc, vnNumIngrSecc;

               EXIT WHEN cIngresoConsulSecc%NOTFOUND;
                   --SE OBTIENE EL NUMERO MAYOR DE INGRESO
               IF  vnNumIngrSecc > vsNumMinIng THEN
                   vsNumMayIng := vnNumIngrSecc;
               ELSE
                   vsNumMayIng := vsNumMinIng;
               END IF;

                   --SE OBTIENE EL %RETENCION33 PARA CON EL MAYOR NUMERO DE INGRESO
               BEGIN
                         SELECT VAL_PORC_RETE_33  INTO vnPorcReten33
                         FROM LET_CORPO_RANGO_RETEN
                  WHERE NUM_INGR_INIC <= vsNumMayIng
                            AND NUM_INGR_FINA >= vsNumMayIng
                            AND COD_PROG = vsCodProg
                            AND IND_ACTI = 1;
                 EXCEPTION
                   WHEN NO_DATA_FOUND THEN
                     vnPorcReten33 := NULL;
               END;

               IF vnPorcReten33 IS NOT NULL THEN
                          vnObje33 := ROUND(ROUND(vnPorcReten33/100,2) * vsNumMayIng);

                          BEGIN
                            SELECT IND_ORIG_CALC
                               INTO vsIndOrigCalc
                             FROM LET_CORPO_SECCI_OBJET
                            WHERE COD_PROG = vsCodProg
                              AND COD_REGI = vsCodRegi
                              AND COD_ZONA = vsCodZona
                              AND COD_SECC = vsCodSecc
                              AND CAM_OBJE = vsCodPeriObjetivo
                              AND IND_ACTI = 1;
                          EXCEPTION
                            WHEN NO_DATA_FOUND THEN
                              vsIndOrigCalc := NULL;

                          END;

                          IF (vsIndOrigCalc IS NOT NULL) THEN
                             IF(vsIndOrigCalc <> 'X') THEN
                               UPDATE LET_CORPO_SECCI_OBJET
                                  SET NUM_INGR_RETE_33_OBJ = vnObje33,
                                      USU_MODI = psUsuario,
                                      FEC_MODI = SYSDATE
                  WHERE COD_PROG = vsCodProg
                  AND COD_REGI = vsCodRegi
                  AND COD_ZONA = vsCodZona
                  AND COD_SECC = vsCodSecc
                  AND CAM_OBJE = vsCodPeriObjetivo;
                             END IF;

                          ELSE
                    INSERT INTO LET_CORPO_SECCI_OBJET(
                          COD_PAIS, COD_PROG, COD_REGI,
                          COD_ZONA, COD_SECC, CAM_OBJE,
                          NUM_INGR_RETE_33_OBJ, NUM_INGR_RETE_44_OBJ,
                          IND_ORIG_CALC, IND_PROC,
                          USU_CREA, USU_MODI, FEC_CREA, FEC_MODI, IND_ACTI)
                     VALUES(
                          psCodigoPais, vsCodProg, vsCodRegi,
                          vsCodZona, vsCodSecc, vsCodPeriObjetivo,
                          vnObje33, 0,
                          'C', 'N',
                          psUsuario, NULL, SYSDATE, NULL, '1');



                   END IF;
               END IF;

          END LOOP;
          CLOSE cIngresoConsulSecc;

        END IF;

     END IF;--FIN CALCULO PARA CAMPA�A SGTE.

     --- Inserci�n de objetivos para secciones sin Ingresos
     --- Recorremos universo de SECCIONES(region,zona,seccion,)
        OPEN cIngresoConsul2Secc(vnOidPeriodo, vcCodigoReg);

          LOOP FETCH cIngresoConsul2Secc INTO vsCodRegi, vsCodZona, vsCodSecc;

               EXIT WHEN cIngresoConsul2Secc%NOTFOUND;

              --SE OBTIENE EL %RETENCION33 CON EL MINIMO NUMERO DE INGRESOS
              BEGIN
               SELECT VAL_PORC_RETE_33  INTO vnPorcReten33
               FROM LET_CORPO_RANGO_RETEN
                WHERE NUM_INGR_INIC <= vsNumMinIng
                  AND NUM_INGR_FINA >= vsNumMinIng
                  AND COD_PROG = vsCodProg
                  AND IND_ACTI = 1;
               EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                   vnPorcReten33 := NULL;
              END;

              IF vnPorcReten33 IS NOT NULL THEN
                vnObje33 := ROUND(ROUND(vnPorcReten33/100,2) * vsNumMinIng);

                BEGIN
                    SELECT NUM_INGR_RETE_33_OBJ
                    INTO vsRet33
                FROM LET_CORPO_SECCI_OBJET
                WHERE COD_PROG = vsCodProg
                  AND COD_REGI = vsCodRegi
                  AND COD_ZONA = vsCodZona
                  AND COD_SECC = vsCodSecc
                        AND CAM_OBJE = vsCodPeriObjetivo
                        AND IND_ACTI = 1;
                EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     vsRet33 := NULL;
                END;

                IF vsRet33 IS NULL THEN
                  INSERT INTO LET_CORPO_SECCI_OBJET(
                        COD_PAIS, COD_PROG, COD_REGI,
                        COD_ZONA, COD_SECC, CAM_OBJE,
                        NUM_INGR_RETE_33_OBJ, NUM_INGR_RETE_44_OBJ,
                        IND_ORIG_CALC, IND_PROC,
                        USU_CREA, USU_MODI, FEC_CREA, FEC_MODI, IND_ACTI)
                   VALUES(
                        psCodigoPais, vsCodProg, vsCodRegi,
                        vsCodZona, vsCodSecc, vsCodPeriObjetivo,
                        vnObje33, 0,
                        'C', 'N',
                        psUsuario, NULL, SYSDATE, NULL, '1');
                ELSE
                    IF vsRet33 = 0 THEN
                       UPDATE LET_CORPO_SECCI_OBJET
                          SET NUM_INGR_RETE_33_OBJ = vnObje33,
                              USU_MODI = psUsuario,
                              FEC_MODI = SYSDATE
                       WHERE COD_PROG = vsCodProg
                        AND COD_REGI = vsCodRegi
                        AND COD_ZONA = vsCodZona
                        AND COD_SECC = vsCodSecc
                        AND CAM_OBJE = vsCodPeriObjetivo;

                    END IF;
     END IF;
             END IF;
          END LOOP;
        CLOSE cIngresoConsul2Secc;


    /*********** CALCULO OBJETIVOS P' CAMPA�A ACTUAL********/
          vsCodPeriObjetivo := psCodigoPeriodo;

          BEGIN
            SELECT COD_PROG
              INTO vsCodProg
              FROM LET_CORPO_PARAM_PROGR
             WHERE vsCodPeriObjetivo >= CAM_INIC
               AND (CAM_FIN IS NULL OR vsCodPeriObjetivo <= CAM_FIN)
               AND IND_ACTI = 1;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vsCodProg := NULL;
          END;

    IF vsCodProg IS NOT NULL THEN
        BEGIN
          SELECT COD_TRAM, IND_ACTI_RETE_33
            INTO vsCodTram, vsIndIngrEfec33
            FROM LET_CORPO_PARAM_TRAMO
           WHERE COD_PROG = vsCodProg
             AND CAM_INIC <= vsCodPeriObjetivo
             AND CAM_FIN >= vsCodPeriObjetivo
             AND IND_ACTI = '1';
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCodTram := NULL;
            vsIndIngrEfec33 := NULL;
        END;

        --Si IND_ACTI_RETE_33 = 0 no calcular obj.
        IF (vsCodTram IS NOT NULL) AND (vsIndIngrEfec33 = '1') THEN
          OPEN cIngresoConsulSecc(vnOidPeriodo, vnOidPeriodoIngrActual, vcCodigoReg);

          LOOP FETCH cIngresoConsulSecc INTO vsCodRegi, vsCodZona, vsCodSecc, vnNumIngrSecc;

               EXIT WHEN cIngresoConsulSecc%NOTFOUND;
                 --SE OBTIENE EL NUMERO MAYOR DE INGRESO
               IF  vnNumIngrSecc > vsNumMinIng THEN
                   vsNumMayIng := vnNumIngrSecc;
               ELSE
                   vsNumMayIng := vsNumMinIng;
               END IF;

                 --SE OBTIENE EL %RETENCION33 PARA CON EL MAYOR NUMERO DE INGRESO
             BEGIN
                     SELECT VAL_PORC_RETE_33 INTO vnPorcReten33
                     FROM LET_CORPO_RANGO_RETEN
                  WHERE NUM_INGR_INIC <= vsNumMayIng
                        AND NUM_INGR_FINA >= vsNumMayIng
                        AND COD_PROG = vsCodProg
                        AND IND_ACTI = 1;
               EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                   vnPorcReten33 := NULL;
             END;

             IF vnPorcReten33 IS NOT NULL THEN
                        vnObje33 := ROUND(ROUND(vnPorcReten33/100,2) * vsNumMayIng);

                        BEGIN
                        SELECT IND_ORIG_CALC
                          INTO vsIndOrigCalc
                        FROM LET_CORPO_SECCI_OBJET
                        WHERE COD_PROG = vsCodProg
                          AND COD_REGI = vsCodRegi
                          AND COD_ZONA = vsCodZona
                          AND COD_SECC = vsCodSecc
                          AND CAM_OBJE = vsCodPeriObjetivo
                          AND IND_ACTI = 1;
                        EXCEPTION
                          WHEN NO_DATA_FOUND THEN
                            vsIndOrigCalc := NULL;
                        END;

                        IF (vsIndOrigCalc IS NOT NULL) THEN
                            IF(vsIndOrigCalc <> 'X') THEN
                              UPDATE LET_CORPO_SECCI_OBJET
                                SET NUM_INGR_RETE_33_OBJ = vnObje33,
                                    USU_MODI = psUsuario,
                                    FEC_MODI = SYSDATE
                  WHERE COD_PROG = vsCodProg
                  AND COD_REGI = vsCodRegi
                  AND COD_ZONA = vsCodZona
                  AND COD_SECC = vsCodSecc
                  AND CAM_OBJE = vsCodPeriObjetivo;
                            END IF;
                        ELSE
                INSERT INTO LET_CORPO_SECCI_OBJET(
                          COD_PAIS, COD_PROG, COD_REGI,
                          COD_ZONA, COD_SECC, CAM_OBJE,
                          NUM_INGR_RETE_33_OBJ, NUM_INGR_RETE_44_OBJ,
                          IND_ORIG_CALC, IND_PROC,
                          USU_CREA, USU_MODI, FEC_CREA, FEC_MODI, IND_ACTI)
                 VALUES(
                          psCodigoPais, vsCodProg, vsCodRegi,
                          vsCodZona, vsCodSecc, vsCodPeriObjetivo,
                                  vnObje33, 0,
                          'C', 'N',
                          psUsuario, NULL, SYSDATE, NULL, '1');


                   END IF;

               END IF;

        END LOOP;

          CLOSE cIngresoConsulSecc;

     END IF;

  END IF;--FIN CALCULO PARA CAMPA�A ACTUAL.

  --- Inserci�n de objetivos para secciones sin Ingresos
  --- Recorremos universo de SECCIONES(region,zona,seccion,)
    OPEN cIngresoConsul2Secc(vnOidPeriodo, vcCodigoReg);

      LOOP FETCH cIngresoConsul2Secc INTO vsCodRegi, vsCodZona, vsCodSecc;

           EXIT WHEN cIngresoConsul2Secc%NOTFOUND;

          --SE OBTIENE EL %RETENCION33 CON EL MINIMO NUMERO DE INGRESOS
          BEGIN
           SELECT VAL_PORC_RETE_33  INTO vnPorcReten33
           FROM LET_CORPO_RANGO_RETEN
            WHERE NUM_INGR_INIC <= vsNumMinIng
              AND NUM_INGR_FINA >= vsNumMinIng
              AND COD_PROG = vsCodProg
              AND IND_ACTI = 1;
           EXCEPTION
             WHEN NO_DATA_FOUND THEN
               vnPorcReten33 := NULL;
          END;

          IF vnPorcReten33 IS NOT NULL THEN
            vnObje33 := ROUND(ROUND(vnPorcReten33/100,2) * vsNumMinIng);

            BEGIN
              SELECT NUM_INGR_RETE_33_OBJ
              INTO vsRet33
            FROM LET_CORPO_SECCI_OBJET
            WHERE COD_PROG = vsCodProg
              AND COD_REGI = vsCodRegi
              AND COD_ZONA = vsCodZona
              AND COD_SECC = vsCodSecc
                  AND CAM_OBJE = vsCodPeriObjetivo
                  AND IND_ACTI = 1;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                 vsRet33 := NULL;
            END;


            IF vsRet33 IS NULL THEN
              INSERT INTO LET_CORPO_SECCI_OBJET(
                    COD_PAIS, COD_PROG, COD_REGI,
                    COD_ZONA, COD_SECC, CAM_OBJE,
                    NUM_INGR_RETE_33_OBJ, NUM_INGR_RETE_44_OBJ,
                    IND_ORIG_CALC, IND_PROC,
                    USU_CREA, USU_MODI, FEC_CREA, FEC_MODI, IND_ACTI)
               VALUES(
                    psCodigoPais, vsCodProg, vsCodRegi,
                    vsCodZona, vsCodSecc, vsCodPeriObjetivo,
                    vnObje33, 0,
                    'C', 'N',
                    psUsuario, NULL, SYSDATE, NULL, '1');
            ELSE
                IF vsRet33 = 0 THEN
                   UPDATE LET_CORPO_SECCI_OBJET
                      SET NUM_INGR_RETE_33_OBJ = vnObje33,
                          USU_MODI = psUsuario,
                          FEC_MODI = SYSDATE
                   WHERE COD_PROG = vsCodProg
                    AND COD_REGI = vsCodRegi
                    AND COD_ZONA = vsCodZona
                    AND COD_SECC = vsCodSecc
                    AND CAM_OBJE = vsCodPeriObjetivo;

  END IF;
            END IF;
         END IF;
      END LOOP;
    CLOSE cIngresoConsul2Secc;

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_OBJET_RET33_LIDER: ' || ls_sqlerrm);
  END LET_PR_CALCU_OBJET_RET33_LIDER;

  /**********************************************************************************
   Descripcion       : Proceso de Calculo Objetivo Retencion 4/4 Seccion
   Fecha Creacion    : 17/06/2013
   Autor             : Danny Amaro
  **********************************************************************************/
  PROCEDURE LET_PR_CALCU_OBJET_RET44_LIDER(psCodigoPais VARCHAR2,
                                           psCodigoRegion VARCHAR2,
                                           psCodigoMarca VARCHAR2,
                                           psCodigoCanal VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psTipoProceso VARCHAR2,
                                           psUsuario VARCHAR2)IS

  vsCodProg               LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
  vsNumMinIng             LET_CORPO_PARAM_PROGR.NUM_MINI_INGR%TYPE;
  vsNumMayIng             LET_CORPO_PARAM_PROGR.NUM_MINI_INGR%TYPE;
  vsNumReg                NUMBER;
  vsIndOrigCalc           VARCHAR2(1);
  vsCodPeriIngrSgte       VARCHAR2(6);
  vsCodPeriIngrActual     VARCHAR2(6);
  vsCodPeriObjetivo       VARCHAR2(6);

  vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
  vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;

  vnOidPeriodoIngrSgte    CRA_PERIO.OID_PERI%TYPE;
  vnOidPeriodoIngrActual  CRA_PERIO.OID_PERI%TYPE;


  vnOidMarca              SEG_MARCA.OID_MARC%TYPE;
  vnOidCanal              SEG_CANAL.OID_CANA%TYPE;

  vsCodRegi               ZON_REGIO.COD_REGI%TYPE;
  vsCodZona               ZON_ZONA.COD_ZONA%TYPE;
  vsCodSecc               ZON_SECCI.COD_SECC%TYPE;
  vnNumIngrSecc           NUMBER;

  vnPorcReten44           LET_CORPO_RANGO_RETEN.Val_Porc_Rete_44%TYPE;
  vnObje44                NUMBER;
  vsRet44                 NUMBER;

  --Parametro para el Cursor
  vcCodigoReg             ZON_REGIO.COD_REGI%TYPE;

  vsCodTram               LET_CORPO_PARAM_TRAMO.COD_TRAM%TYPE;
  vsIndIngrEfec44         LET_CORPO_PARAM_TRAMO.IND_ACTI_RETE_44%TYPE;

  --Cursor Universo de Consultoras
  CURSOR cIngresoSeccion(oidPeriodo CRA_PERIO.OID_PERI%TYPE,
                         oidPeriodoIngr CRA_PERIO.OID_PERI%TYPE,
                         codigoRegion ZON_REGIO.COD_REGI%TYPE) IS

        SELECT ZR.COD_REGI, ZZ.COD_ZONA, ZS.COD_SECC, COUNT(MCUA.CLIE_OID_CLIE)
          FROM MAE_CLIEN_UNIDA_ADMIN MCUA,
               ZON_TERRI_ADMIN       ZTA,
               MAE_CLIEN_HISTO_ESTAT MCHE,
               MAE_ESTAT_CLIEN       MEC,
               ZON_SECCI             ZS,
               ZON_ZONA              ZZ,
               ZON_REGIO             ZR
         WHERE MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
           AND MCUA.CLIE_OID_CLIE = MCHE.CLIE_OID_CLIE
           AND ZTA.ZSCC_OID_SECC = ZS.OID_SECC
           AND ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
           AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
           AND (codigoRegion IS NULL OR ZR.COD_REGI = codigoRegion)
           AND MCUA.PERD_OID_PERI_INI <= oidPeriodo
           AND (MCUA.PERD_OID_PERI_FIN >= oidPeriodo OR MCUA.PERD_OID_PERI_FIN IS NULL)
           AND MCHE.PERD_OID_PERI = oidPeriodoIngr
           AND MCHE.ESTA_OID_ESTA_CLIE = MEC.OID_ESTA_CLIE
           AND MEC.COD_ESTA_CLIE IN ('02', '08')
         GROUP BY ZR.COD_REGI, ZZ.COD_ZONA, ZS.COD_SECC;

  --Cursor para universo de secciones
  CURSOR cIngresoConsul2Secc(oidPeriodo CRA_PERIO.OID_PERI%TYPE,
                             codigoRegion ZON_REGIO.COD_REGI%TYPE) IS
         SELECT ZR.COD_REGI, ZZ.COD_ZONA, ZS.COD_SECC
         FROM  ZON_SECCI             ZS,
               ZON_ZONA              ZZ,
               ZON_REGIO             ZR
               WHERE ZR.OID_REGI = ZZ.ZORG_OID_REGI
               AND ZZ.OID_ZONA = ZS.ZZON_OID_ZONA
               AND (codigoRegion IS NULL OR ZR.COD_REGI = codigoRegion)
               AND ZR.PERD_OID_PERI_INIC <= oidPeriodo
               AND (ZR.PERD_OID_PERI_FINA >= oidPeriodo OR ZR.PERD_OID_PERI_FINA IS NULL)
               AND ZZ.PERD_OID_PERI_INIC <= oidPeriodo
               AND (ZZ.PERD_OID_PERI_FINA >= oidPeriodo OR ZZ.PERD_OID_PERI_FINA IS NULL)
               AND ZS.PERD_OID_PERI_INIC <= oidPeriodo
               AND (ZS.PERD_OID_PERI_FINA >= oidPeriodo OR ZS.PERD_OID_PERI_FINA IS NULL);

  BEGIN

    vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

    vsCodPeriObjetivo := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,1);

    --VARIABLES P' CAMPA�A SGTE
    vsCodPeriIngrSgte := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-2);
    vnOidPeriodoIngrSgte := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodPeriIngrSgte);
    --VARIABLES P' CAMPA�A ACTUAL
    vsCodPeriIngrActual := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-3);
    vnOidPeriodoIngrActual := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodPeriIngrActual);

    --BORRAMOS REGISTROS CON PERIODO OBJETIVO(psCodigoPeriodo y vsCodPeriObjetivo)
    IF psTipoProceso = 'R' THEN
         vcCodigoReg := psCodigoRegion;
    ELSE
         vcCodigoReg := NULL;
    END IF;


/*********** CALCULO OBJETIVOS P' CAMPA�A SGTE********/
    BEGIN
      SELECT COD_PROG, NUM_MINI_INGR
        INTO vsCodProg, vsNumMinIng
        FROM LET_CORPO_PARAM_PROGR
       WHERE vsCodPeriObjetivo >= CAM_INIC
         AND (CAM_FIN IS NULL OR vsCodPeriObjetivo <= CAM_FIN)
         AND IND_ACTI = 1;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vsCodProg := NULL;
    END;

    IF vsCodProg IS NOT NULL THEN
       BEGIN
          SELECT COD_TRAM, IND_ACTI_RETE_44
            INTO vsCodTram, vsIndIngrEfec44
            FROM LET_CORPO_PARAM_TRAMO
           WHERE COD_PROG = vsCodProg
             AND CAM_INIC <= vsCodPeriObjetivo
             AND CAM_FIN >= vsCodPeriObjetivo
             AND IND_ACTI = '1';
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCodTram := NULL;
            vsIndIngrEfec44 := NULL;
        END;

        --Si IND_ACTI_RETE_44 = 0 no calcular obj.
        IF (vsCodTram IS NOT NULL) AND (vsIndIngrEfec44 = '1') THEN
       OPEN cIngresoSeccion(vnOidPeriodo, vnOidPeriodoIngrSgte, vcCodigoReg);
       LOOP FETCH cIngresoSeccion INTO vsCodRegi, vsCodZona, vsCodSecc, vnNumIngrSecc;
            EXIT WHEN cIngresoSeccion%NOTFOUND;

				  --SE OBTIENE EL NUMERO MAYOR DE INGRESO
            IF  vnNumIngrSecc > vsNumMinIng THEN
                 vsNumMayIng := vnNumIngrSecc;
            ELSE
                 vsNumMayIng := vsNumMinIng;
            END IF;

				  --SE OBTIENE EL %RETENCION44 CON EL MAYOR NUMERO DE INGRESO
            BEGIN
               SELECT VAL_PORC_RETE_44 INTO vnPorcReten44
                 FROM LET_CORPO_RANGO_RETEN
                WHERE NUM_INGR_INIC <= vsNumMayIng
                        AND NUM_INGR_FINA >= vsNumMayIng
                        AND COD_PROG = vsCodProg
                        AND IND_ACTI = 1;
               EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                   vnPorcReten44 := NULL;
            END;

            IF vnPorcReten44 IS NOT NULL THEN
                     vnObje44 := ROUND(ROUND(vnPorcReten44/100,2) * vsNumMayIng);

                     SELECT COUNT (1)
                     INTO vsNumReg
                     FROM LET_CORPO_SECCI_OBJET
                     WHERE     COD_PROG = vsCodProg
                     AND COD_REGI = vsCodRegi
                     AND COD_ZONA = vsCodZona
                     AND COD_SECC = vsCodSecc
                     AND CAM_OBJE = vsCodPeriObjetivo
                     AND IND_ACTI = 1;

                     BEGIN
                       SELECT IND_ORIG_CALC_44
                          INTO vsIndOrigCalc
                       FROM LET_CORPO_SECCI_OBJET
                           WHERE COD_PROG = vsCodProg
                            AND COD_REGI = vsCodRegi
                            AND COD_ZONA = vsCodZona
                            AND COD_SECC = vsCodSecc
                            AND CAM_OBJE = vsCodPeriObjetivo
                            AND IND_ACTI = 1;
                     EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                              vsIndOrigCalc := NULL;
                     END;

                         IF vsNumReg > 0 THEN
                          IF(vsIndOrigCalc = 'C') OR vsIndOrigCalc is null THEN
                                UPDATE LET_CORPO_SECCI_OBJET
                                    SET NUM_INGR_RETE_44_OBJ = vnObje44,
                                        USU_MODI = psUsuario,
                                        FEC_MODI = SYSDATE,
                                        IND_ORIG_CALC_44 = 'C'
                 WHERE COD_PROG = vsCodProg
                  AND COD_REGI = vsCodRegi
                  AND COD_ZONA = vsCodZona
                  AND COD_SECC = vsCodSecc
                  AND CAM_OBJE = vsCodPeriObjetivo;
                          END IF;

                         ELSE
                    INSERT INTO LET_CORPO_SECCI_OBJET(
                          COD_PAIS, COD_PROG, COD_REGI,
                          COD_ZONA, COD_SECC, CAM_OBJE,
                          NUM_INGR_RETE_33_OBJ, NUM_INGR_RETE_44_OBJ,
                                IND_ORIG_CALC_44, IND_PROC,
                          USU_CREA, USU_MODI, FEC_CREA, FEC_MODI, IND_ACTI)
                     VALUES(
                          psCodigoPais, vsCodProg, vsCodRegi,
                          vsCodZona, vsCodSecc, vsCodPeriObjetivo,
                          0, vnObje44,
                          'C', 'N',
                          psUsuario, NULL, SYSDATE, NULL, '1');


                  END IF;
            END IF;
       END LOOP;
       CLOSE cIngresoSeccion;
        END IF;

    END IF;--FIN DE CALCULO P' CAMPA�A SGTE

    --- Inserci�n de objetivos para secciones sin Ingresos
    --- Recorremos universo de SECCIONES(region,zona,seccion,)
        OPEN cIngresoConsul2Secc(vnOidPeriodo, vcCodigoReg);

          LOOP FETCH cIngresoConsul2Secc INTO vsCodRegi, vsCodZona, vsCodSecc;

               EXIT WHEN cIngresoConsul2Secc%NOTFOUND;

              --SE OBTIENE EL %RETENCION33 CON EL MINIMO NUMERO DE INGRESOS
              BEGIN
               SELECT VAL_PORC_RETE_44  INTO vnPorcReten44
               FROM LET_CORPO_RANGO_RETEN
                WHERE NUM_INGR_INIC <= vsNumMinIng
                  AND NUM_INGR_FINA >= vsNumMinIng
                  AND COD_PROG = vsCodProg
                  AND IND_ACTI = 1;
               EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                   vnPorcReten44 := NULL;
              END;

              IF vnPorcReten44 IS NOT NULL THEN
                vnObje44 := ROUND(ROUND(vnPorcReten44/100,2) * vsNumMinIng);

                  BEGIN
                    SELECT NUM_INGR_RETE_44_OBJ
                    INTO vsRet44
                FROM LET_CORPO_SECCI_OBJET
                WHERE COD_PROG = vsCodProg
                  AND COD_REGI = vsCodRegi
                  AND COD_ZONA = vsCodZona
                  AND COD_SECC = vsCodSecc
                      AND CAM_OBJE = vsCodPeriObjetivo
                      AND IND_ACTI = 1;
                  EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                      vsRet44 := NULL;
                  END;


                  IF vsRet44 IS NULL THEN
                      INSERT INTO LET_CORPO_SECCI_OBJET(
                            COD_PAIS, COD_PROG, COD_REGI,
                            COD_ZONA, COD_SECC, CAM_OBJE,
                            NUM_INGR_RETE_33_OBJ, NUM_INGR_RETE_44_OBJ,
                            IND_ORIG_CALC_44, IND_PROC,
                            USU_CREA, USU_MODI, FEC_CREA, FEC_MODI, IND_ACTI)
                       VALUES(
                            psCodigoPais, vsCodProg, vsCodRegi,
                            vsCodZona, vsCodSecc, vsCodPeriObjetivo,
                            0, vnObje44,
                            'C', 'N',
                            psUsuario, NULL, SYSDATE, NULL, '1');

                   ELSE
                     IF vsRet44 = 0 THEN
                       UPDATE LET_CORPO_SECCI_OBJET
                          SET NUM_INGR_RETE_44_OBJ = vnObje44,
                              USU_MODI = psUsuario,
                              FEC_MODI = SYSDATE,
                              IND_ORIG_CALC_44 = 'C'
                       WHERE COD_PROG = vsCodProg
                        AND COD_REGI = vsCodRegi
                        AND COD_ZONA = vsCodZona
                        AND COD_SECC = vsCodSecc
                        AND CAM_OBJE = vsCodPeriObjetivo;
                     END IF;

                   END IF;

    END IF;

          END LOOP;
        CLOSE cIngresoConsul2Secc;

/*********** CALCULO OBJETIVOS P' CAMPA�A ACTUAL********/
    vsCodPeriObjetivo := psCodigoPeriodo;

    BEGIN
      SELECT COD_PROG, NUM_MINI_INGR
        INTO vsCodProg, vsNumMinIng
        FROM LET_CORPO_PARAM_PROGR
       WHERE vsCodPeriObjetivo >= CAM_INIC
         AND (CAM_FIN IS NULL OR vsCodPeriObjetivo <= CAM_FIN)
         AND IND_ACTI = 1;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vsCodProg := NULL;
    END;

    IF vsCodProg IS NOT NULL THEN
       BEGIN
          SELECT COD_TRAM, IND_ACTI_RETE_44
            INTO vsCodTram, vsIndIngrEfec44
            FROM LET_CORPO_PARAM_TRAMO
           WHERE COD_PROG = vsCodProg
             AND CAM_INIC <= vsCodPeriObjetivo
             AND CAM_FIN >= vsCodPeriObjetivo
             AND IND_ACTI = '1';
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCodTram := NULL;
            vsIndIngrEfec44 := NULL;
        END;

        --Si IND_ACTI_RETE_44 = 0 no calcular obj.
        IF (vsCodTram IS NOT NULL) AND (vsIndIngrEfec44 = '1') THEN
       OPEN cIngresoSeccion(vnOidPeriodo, vnOidPeriodoIngrActual, vcCodigoReg);

       LOOP FETCH cIngresoSeccion INTO vsCodRegi, vsCodZona, vsCodSecc, vnNumIngrSecc;

            EXIT WHEN cIngresoSeccion%NOTFOUND;

				  --SE OBTIENE EL NUMERO MAYOR DE INGRESO
            IF  vnNumIngrSecc > vsNumMinIng THEN
                 vsNumMayIng := vnNumIngrSecc;
            ELSE
                 vsNumMayIng := vsNumMinIng;
            END IF;

				  --SE OBTIENE EL %RETENCION44 CON EL MAYOR NUMERO DE INGRESO
            BEGIN
               SELECT VAL_PORC_RETE_44 INTO vnPorcReten44
                 FROM LET_CORPO_RANGO_RETEN
                WHERE NUM_INGR_INIC <= vsNumMayIng
                        AND NUM_INGR_FINA >= vsNumMayIng
                        AND COD_PROG = vsCodProg
                        AND IND_ACTI = 1;
               EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                   vnPorcReten44 := NULL;
            END;

            IF vnPorcReten44 IS NOT NULL THEN
                     vnObje44 := ROUND(ROUND(vnPorcReten44/100,2) * vsNumMayIng);

                     SELECT COUNT (1)
                     INTO vsNumReg
                     FROM LET_CORPO_SECCI_OBJET
                     WHERE     COD_PROG = vsCodProg
                     AND COD_REGI = vsCodRegi
                     AND COD_ZONA = vsCodZona
                     AND COD_SECC = vsCodSecc
                     AND CAM_OBJE = vsCodPeriObjetivo
                     AND IND_ACTI = 1;

                     BEGIN
                       SELECT IND_ORIG_CALC_44
                          INTO vsIndOrigCalc
                       FROM LET_CORPO_SECCI_OBJET
                           WHERE COD_PROG = vsCodProg
                            AND COD_REGI = vsCodRegi
                            AND COD_ZONA = vsCodZona
                            AND COD_SECC = vsCodSecc
                            AND CAM_OBJE = vsCodPeriObjetivo
                            AND IND_ACTI = 1;
                     EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                         vsIndOrigCalc := NULL;
                     END;


                        IF vsNumReg > 0 THEN
                          IF(vsIndOrigCalc = 'C')  OR vsIndOrigCalc is null THEN
                               UPDATE LET_CORPO_SECCI_OBJET
                                SET NUM_INGR_RETE_44_OBJ = vnObje44,
                                                USU_MODI = psUsuario,
                                                FEC_MODI = SYSDATE,
                                                IND_ORIG_CALC_44 = 'C'
                 WHERE COD_PROG = vsCodProg
                  AND COD_REGI = vsCodRegi
                  AND COD_ZONA = vsCodZona
                  AND COD_SECC = vsCodSecc
                  AND CAM_OBJE = vsCodPeriObjetivo;
                          END IF;
                        ELSE
                    INSERT INTO LET_CORPO_SECCI_OBJET(
                          COD_PAIS, COD_PROG, COD_REGI,
                          COD_ZONA, COD_SECC, CAM_OBJE,
                          NUM_INGR_RETE_33_OBJ, NUM_INGR_RETE_44_OBJ,
                                IND_ORIG_CALC_44, IND_PROC,
                          USU_CREA, USU_MODI, FEC_CREA, FEC_MODI, IND_ACTI)
                     VALUES(
                          psCodigoPais, vsCodProg, vsCodRegi,
                          vsCodZona, vsCodSecc, vsCodPeriObjetivo,
                          0, vnObje44,
                          'C', 'N',
                          psUsuario, NULL, SYSDATE, NULL, '1');

                  END IF;
            END IF;

       END LOOP;

       CLOSE cIngresoSeccion;
        END IF;

    END IF;--FIN CALCULO P' CAMPA�A ACTUAL

    --- Inserci�n de objetivos para secciones sin Ingresos
    --- Recorremos universo de SECCIONES(region,zona,seccion,)
        OPEN cIngresoConsul2Secc(vnOidPeriodo, vcCodigoReg);

          LOOP FETCH cIngresoConsul2Secc INTO vsCodRegi, vsCodZona, vsCodSecc;

              EXIT WHEN cIngresoConsul2Secc%NOTFOUND;

              --SE OBTIENE EL %RETENCION33 CON EL MINIMO NUMERO DE INGRESOS
              BEGIN
               SELECT VAL_PORC_RETE_44  INTO vnPorcReten44
               FROM LET_CORPO_RANGO_RETEN
                WHERE NUM_INGR_INIC <= vsNumMinIng
                  AND NUM_INGR_FINA >= vsNumMinIng
                  AND COD_PROG = vsCodProg
                  AND IND_ACTI = 1;
               EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                   vnPorcReten44 := NULL;
              END;

              IF vnPorcReten44 IS NOT NULL THEN
                vnObje44 := ROUND(ROUND(vnPorcReten44/100,2) * vsNumMinIng);

                  BEGIN
                    SELECT NUM_INGR_RETE_44_OBJ
                    INTO vsRet44
                  FROM LET_CORPO_SECCI_OBJET
                  WHERE COD_PROG = vsCodProg
                    AND COD_REGI = vsCodRegi
                    AND COD_ZONA = vsCodZona
                    AND COD_SECC = vsCodSecc
                      AND CAM_OBJE = vsCodPeriObjetivo
                      AND IND_ACTI = 1;
                  EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        vsRet44 := NULL;
                  END;

                  IF vsRet44 IS NULL THEN
                      INSERT INTO LET_CORPO_SECCI_OBJET(
                            COD_PAIS, COD_PROG, COD_REGI,
                            COD_ZONA, COD_SECC, CAM_OBJE,
                            NUM_INGR_RETE_33_OBJ, NUM_INGR_RETE_44_OBJ,
                            IND_ORIG_CALC_44, IND_PROC,
                            USU_CREA, USU_MODI, FEC_CREA, FEC_MODI, IND_ACTI)
                       VALUES(
                            psCodigoPais, vsCodProg, vsCodRegi,
                            vsCodZona, vsCodSecc, vsCodPeriObjetivo,
                            0, vnObje44,
                            'C', 'N',
                            psUsuario, NULL, SYSDATE, NULL, '1');
                   ELSE
                       IF vsRet44 = 0 THEN
                          UPDATE LET_CORPO_SECCI_OBJET
                            SET NUM_INGR_RETE_44_OBJ = vnObje44,
                                            USU_MODI = psUsuario,
                                            FEC_MODI = SYSDATE,
                                            IND_ORIG_CALC_44 = 'C'
                             WHERE COD_PROG = vsCodProg
                              AND COD_REGI = vsCodRegi
                              AND COD_ZONA = vsCodZona
                              AND COD_SECC = vsCodSecc
                              AND CAM_OBJE = vsCodPeriObjetivo;
                       END IF;

                   END IF;

    END IF;

          END LOOP;
        CLOSE cIngresoConsul2Secc;


  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_OBJET_RET44_LIDER: ' || ls_sqlerrm);
  END LET_PR_CALCU_OBJET_RET44_LIDER;

    /**********************************************************************************
    Descripcion       : Proceso de Calculo General de Retenciones
    Fecha Creacion    : 18/06/2013
    Autor             : Danny Amaro
    **********************************************************************************/
    PROCEDURE LET_PR_CALCU_OBJET_RETEN(psCodigoPais VARCHAR2,
                                       psCodigoRegion VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
                                       psUsuario VARCHAR2)IS

    vsCodProg               LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
    vsCodTram               LET_CORPO_PARAM_TRAMO.COD_TRAM%TYPE;

    vsIndIngrEfec           VARCHAR2(1);
    vsIndRete33             VARCHAR2(1);
    vsIndRete44             VARCHAR2(1);

    BEGIN

      BEGIN
        SELECT COD_PROG
          INTO vsCodProg
          FROM LET_CORPO_PARAM_PROGR
         WHERE psCodigoPeriodo >= CAM_INIC
           AND (CAM_FIN is null OR psCodigoPeriodo <= CAM_FIN)
           AND IND_ACTI = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsCodProg := NULL;
      END;

      IF vsCodProg IS NOT NULL THEN

        BEGIN
          SELECT COD_TRAM,
                 IND_ACTI_INGR_EFEC,
                 IND_ACTI_RETE_33,
                 IND_ACTI_RETE_44
            INTO vsCodTram, vsIndIngrEfec, vsIndRete33, vsIndRete44
            FROM LET_CORPO_PARAM_TRAMO
           WHERE COD_PROG = vsCodProg
             AND CAM_INIC <= psCodigoPeriodo
             AND CAM_FIN >= psCodigoPeriodo
             AND IND_ACTI = 1;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCodTram := NULL;
        END;

        IF vsCodTram IS NOT NULL THEN

           IF vsIndIngrEfec = '1' THEN
              LET_PR_CALCU_OBJET_RET22_LIDER(psCodigoPais,
                                             psCodigoRegion,
                                             psCodigoMarca,
                                             psCodigoCanal,
                                             psCodigoPeriodo,
                                             psTipoProceso,
                                             psUsuario);
           END IF;

           IF vsIndRete33 = '1' THEN
              LET_PR_CALCU_OBJET_RET33_LIDER(psCodigoPais,
                                             psCodigoRegion,
                                             psCodigoMarca,
                                             psCodigoCanal,
                                             psCodigoPeriodo,
                                             psTipoProceso,
                                             psUsuario);
           END IF;

           IF vsIndRete44 = '1' THEN
              LET_PR_CALCU_OBJET_RET44_LIDER(psCodigoPais,
                                             psCodigoRegion,
                                             psCodigoMarca,
                                             psCodigoCanal,
                                             psCodigoPeriodo,
                                             psTipoProceso,
                                             psUsuario);
           END IF;

        END IF;

      END IF;

    EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_OBJET_RETEN: ' || ls_sqlerrm);
    END LET_PR_CALCU_OBJET_RETEN;


  /**********************************************************************************
  Descripcion       : Proceso de Calcular Resultados de Lider en base a sus
                      pedidos e ingresos
  Fecha Creacion    : 13/06/2013
  Autor             : Aurelio Oviedo
  **********************************************************************************/
  PROCEDURE LET_PR_CALCU_RESUL_LIDER(psCodigoPais VARCHAR2,
                                     psCodigoRegion VARCHAR2,
                                     psCodigoMarca VARCHAR2,
                                     psCodigoCanal VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psTipoProceso VARCHAR2,
                                     psUsuario VARCHAR2)IS

    --Variables Globales
    vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
    vnOidMarca              SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal              SEG_CANAL.OID_CANA%TYPE;
    vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;
    vnOidPeriodoAnterior    CRA_PERIO.OID_PERI%TYPE;
    vnOidPeriodoAnterior2   CRA_PERIO.OID_PERI%TYPE;
    vnOidPeriodoAnterior3   CRA_PERIO.OID_PERI%TYPE;

    CURSOR c_liderActivoProcesoRegion(oidPeriodo NUMBER, codigoRegion VARCHAR2) IS
        SELECT GERE, COD_REGI, COD_ZONA, COD_SECC
          FROM ZON_HISTO_GEREN
         WHERE oidPeriodo >= PERD_OID_PERI_DESD
           AND (oidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
           AND COD_SECC IS NOT NULL
           AND (codigoRegion IS NULL OR COD_REGI = codigoRegion);
        ---   AND ROWNUM < 100;

    CURSOR c_territoriosSecciones(oidPeriodo NUMBER, oidSeccionLider NUMBER) IS
        SELECT OID_TERR_ADMI, TERR_OID_TERR
          FROM ZON_TERRI_ADMIN
         WHERE vnOidPeriodo >= PERD_OID_PERI_INIC
           AND (vnOidPeriodo <= PERD_OID_PERI_FINA OR PERD_OID_PERI_FINA IS NULL)
           AND ZSCC_OID_SECC = oidSeccionLider;

    CURSOR c_recomendacionesLider(codigoGerente VARCHAR2, codigoPais VARCHAR2, codigoPeriodo VARCHAR2) IS
        SELECT mcv.OID_CLIE_VINC, mcv.CLIE_OID_CLIE_VNTE, mcv.CLIE_OID_CLIE_VNDO
          FROM MAE_CLIEN_VINCU mcv, MAE_TIPO_VINCU mtv
         WHERE mcv.TIVC_OID_TIPO_VINC = mtv.OID_TIPO_VINC
           AND mcv.CLIE_OID_CLIE_VNTE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(codigoGerente)
           AND mtv.COD_TIPO_VINC = '03'
           AND mcv.CLIE_OID_CLIE_VNDO IN (SELECT mcpc.CLIE_OID_CLIE
                                            FROM MAE_CLIEN_PRIME_CONTA mcpc
                                           WHERE mcpc.PERD_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(codigoPais, codigoPeriodo, -1)))
         GROUP BY mcv.OID_CLIE_VINC, mcv.CLIE_OID_CLIE_VNTE, mcv.CLIE_OID_CLIE_VNDO;

    CURSOR c_ingresosHistorialEstatus33(codigoPais VARCHAR2, codigoPeriodo VARCHAR2) IS
        SELECT mchs.PERD_OID_PERI, mchs.CLIE_OID_CLIE, mchs.ESTA_OID_ESTA_CLIE
          FROM MAE_CLIEN_HISTO_ESTAT mchs
         WHERE (mchs.ESTA_OID_ESTA_CLIE = 2 OR mchs.ESTA_OID_ESTA_CLIE = 8)
           AND mchs.PERD_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(codigoPais, codigoPeriodo, -2));

    CURSOR c_ingresosHistorialEstatus44(codigoPais VARCHAR2, codigoPeriodo VARCHAR2) IS
        SELECT mchs.PERD_OID_PERI, mchs.CLIE_OID_CLIE, mchs.ESTA_OID_ESTA_CLIE
          FROM MAE_CLIEN_HISTO_ESTAT mchs
         WHERE (mchs.ESTA_OID_ESTA_CLIE = 2 OR mchs.ESTA_OID_ESTA_CLIE = 8)
           AND mchs.PERD_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(codigoPais, codigoPeriodo, -3));

    vsCodGerente    ZON_HISTO_GEREN.GERE%TYPE;
    vsCodRegion     ZON_HISTO_GEREN.COD_REGI%TYPE;
    vsCodZona       ZON_HISTO_GEREN.COD_ZONA%TYPE;
    vsCodSeccion    ZON_HISTO_GEREN.COD_SECC%TYPE;
    vsOidTerrAdmin  ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE;
    vsOidTerr       ZON_TERRI_ADMIN.TERR_OID_TERR%TYPE;
    vsOidClienVinc  MAE_CLIEN_VINCU.OID_CLIE_VINC%TYPE;
    vsOidVinculante MAE_CLIEN_VINCU.CLIE_OID_CLIE_VNTE%TYPE;
    vsOidVinculado  MAE_CLIEN_VINCU.CLIE_OID_CLIE_VNDO%TYPE;
    vsOidPeriodoIng MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI%TYPE;
    vsOidClienteIng MAE_CLIEN_HISTO_ESTAT.CLIE_OID_CLIE%TYPE;
    vsOidEstadoIng  MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;

    vnCodigoPrograma                LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
    vnIndRestIngresosEfectivos      LET_CORPO_PARAM_PROGR.IND_REST_ALCA_INGR_EFEC%TYPE;
    vnIndIngresosEfectivos          LET_CORPO_PARAM_TRAMO.IND_ACTI_INGR_EFEC%TYPE;
    vnIndRetencion33                LET_CORPO_PARAM_TRAMO.IND_ACTI_RETE_33%TYPE;
    vnIndRetencion44                LET_CORPO_PARAM_TRAMO.IND_ACTI_RETE_44%TYPE;
    vnNumPedidosObjetivos           LET_CORPO_SECCI_OBJET_PEDID.NUM_PEDI_OBJE%TYPE;
    vnOidZonaLider                  ZON_ZONA.OID_ZONA%TYPE;
    vnOidSeccionLider               ZON_SECCI.OID_SECC%TYPE;
    vnOidTerrAdmiLider              ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE;
    vnNumeroPedido                  INT_FUENT_VENTA_REAL_VACUM.NUM_PEDI%TYPE;
    vnNumPedidosReales              INT_FUENT_VENTA_REAL_VACUM.NUM_PEDI%TYPE;
    vnNumObjetivoIngresos           LET_CORPO_LIDER_OBJET.NUM_INGR_EFEC_OBJE%TYPE;

    vnNumIngER                      NUMBER := 0;
    vnNumIngERAnt                   NUMBER := 0;
    vnNumIngERSubTotal              NUMBER := 0;
    vnNumIngERTotal                 NUMBER := 0;
    vnPedTolerancia                 NUMBER := 0;

    vnOidClienUnidadAdmin           MAE_CLIEN_UNIDA_ADMIN.OID_CLIE_UNID_ADMI%TYPE;
    vnClieOidClieUnidadAdmin        MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE%TYPE;
    vnNumObjetivoIngreso33          LET_CORPO_SECCI_OBJET.NUM_INGR_RETE_33_OBJ%TYPE;

    vnNumRetencion33Actual          NUMBER := 0;
    vnNumRetencion33Ant1            NUMBER := 0;
    vnNumRetencion33Ant2            NUMBER := 0;
    vnNumRetencion33SubTotal        NUMBER := 0;
    vnNumRetencion33Total           NUMBER := 0;

    vnNumObjetivoIngreso44          LET_CORPO_SECCI_OBJET.NUM_INGR_RETE_44_OBJ%TYPE;

    vnNumRetencion44Actual          NUMBER := 0;
    vnNumRetencion44Ant1            NUMBER := 0;
    vnNumRetencion44Ant2            NUMBER := 0;
    vnNumRetencion44Ant3            NUMBER := 0;
    vnNumRetencion44SubTotal        NUMBER := 0;
    vnNumRetencion44Total           NUMBER := 0;
    vsRegSecciResul                 NUMBER;

    vnCodigoClasLider               LET_CORPO_LIDER_CLASI.COD_CLAS_LIDE%TYPE;
    vnCodigoNivelExito              LET_CORPO_LIDER_NIVEL.COD_NIVE%TYPE;
    vnNumPedidoTolerancia           LET_CORPO_PARAM_RANGO_NIVEL.NUM_PEDI_TOLE%TYPE;

    --Parametro para el Cursor
    pcCodigoRegion                  ZON_REGIO.COD_REGI%TYPE;

    --Parametros para el INSERT
    vnNumPedidosRealesSobr          LET_CORPO_SECCI_RESUL.NUM_PEDI_SOBR%TYPE;
    vnCodEstaObjetivoPedido         LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_PEDI%TYPE;
    vnCodEstaObjetivoIngreso        LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_INGR%TYPE;
    vnCodEstaObjetivo33             LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_R33%TYPE;
    vnCodEstaObjetivo44             LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_R44%TYPE;
    vnIndProductividad              LET_CORPO_SECCI_RESUL.IND_PROD%TYPE;
    vDiferenciaPedidos              NUMBER;
    vnPorcCriticidad                LET_CORPO_SECCI_RESUL.VAL_PORC_CRIT%TYPE;

    vsCodLA                 ZON_HISTO_GEREN.GERE%TYPE;
    vsCodRegLA              ZON_HISTO_GEREN.COD_REGI%TYPE;
    vsCodZonLA              ZON_HISTO_GEREN.COD_ZONA%TYPE;
    vsOidLA                 MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE%TYPE;

  BEGIN

    vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    vnOidPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais, psCodigoPeriodo, -1));
    vnOidPeriodoAnterior2 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais, psCodigoPeriodo, -2));
    vnOidPeriodoAnterior3 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais, psCodigoPeriodo, -3));
    vnNumPedidosReales := 0;

    IF (psTipoProceso = 'P') THEN
        DELETE LET_CORPO_SECCI_RESUL
         WHERE CAM_OBJE = psCodigoPeriodo;
    END IF;

    BEGIN
        SELECT COD_PROG, IND_REST_ALCA_INGR_EFEC
          INTO vnCodigoPrograma, vnIndRestIngresosEfectivos
          FROM LET_CORPO_PARAM_PROGR
         WHERE IND_ACTI = '1'
           AND psCodigoPeriodo >= CAM_INIC
           AND (psCodigoPeriodo <= CAM_FIN OR CAM_FIN IS NULL);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            vnCodigoPrograma := NULL;
            vnIndRestIngresosEfectivos := NULL;
    END;

    IF (vnCodigoPrograma IS NOT NULL) THEN
        BEGIN
            SELECT IND_ACTI_INGR_EFEC, IND_ACTI_RETE_33, IND_ACTI_RETE_44
              INTO vnIndIngresosEfectivos, vnIndRetencion33, vnIndRetencion44
              FROM LET_CORPO_PARAM_TRAMO
             WHERE psCodigoPeriodo >= CAM_INIC
               AND psCodigoPeriodo <= CAM_FIN
               AND IND_ACTI = 1;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                vnIndIngresosEfectivos := '';
                vnIndRetencion33 := '';
                vnIndRetencion44 := '';
        END;

        IF psTipoProceso = 'R' THEN
            pcCodigoRegion := psCodigoRegion;
        ELSE
            pcCodigoRegion := NULL;
        END IF;

        OPEN c_liderActivoProcesoRegion(vnOidPeriodo, pcCodigoRegion);
        LOOP FETCH c_liderActivoProcesoRegion INTO vsCodGerente, vsCodRegion, vsCodZona, vsCodSeccion;

            EXIT WHEN c_liderActivoProcesoRegion%NOTFOUND;

            --Obtener Pedidos Objetivos
            BEGIN
                SELECT op.NUM_PEDI_OBJE
                  INTO vnNumPedidosObjetivos
                  FROM LET_CORPO_SECCI_OBJET_PEDID op
                 WHERE op.COD_PROG = vnCodigoPrograma
                   AND op.COD_REGI = vsCodRegion
                   AND op.COD_ZONA = vsCodZona
                   AND op.COD_SECC = vsCodSeccion
                   AND op.CAM_OBJE = psCodigoPeriodo
                   AND op.IND_ACTI = '1';
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    vnNumPedidosObjetivos := NULL;
            END;

           IF(vnNumPedidosObjetivos IS NOT NULL) THEN
            --Obtener Pedidos Reales
            SELECT zz.OID_ZONA
              INTO vnOidZonaLider
              FROM ZON_ZONA zz
             WHERE zz.COD_ZONA = vsCodZona;

            SELECT zs.OID_SECC
              INTO vnOidSeccionLider
              FROM ZON_SECCI zs
             WHERE vnOidPeriodo >= zs.PERD_OID_PERI_INIC
               AND (vnOidPeriodo <= zs.PERD_OID_PERI_FINA OR zs.PERD_OID_PERI_FINA IS NULL)
               AND zs.COD_SECC = vsCodSeccion
               AND zs.ZZON_OID_ZONA = vnOidZonaLider;

                vnNumPedidosReales := 0;
                --Cursor para acumular los nros. de pedidos.
            OPEN c_territoriosSecciones(vnOidPeriodo, vnOidSeccionLider);
            LOOP FETCH c_territoriosSecciones INTO vsOidTerrAdmin, vsOidTerr;

                EXIT WHEN c_territoriosSecciones%NOTFOUND;

                BEGIN
                    SELECT rv.NUM_PEDI
                      INTO vnNumeroPedido
                      FROM INT_FUENT_VENTA_REAL_VACUM rv
                     WHERE rv.PERD_OID_PERI = vnOidPeriodo
                       AND rv.TERR_OID_TERR = vsOidTerr;
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        vnNumeroPedido := 0;
                END;

                vnNumPedidosReales := vnNumPedidosReales + vnNumeroPedido;

            END LOOP;
            CLOSE c_territoriosSecciones;


            IF vnIndIngresosEfectivos = '1' THEN
                --Obtener Objetivo Ingresos
                    BEGIN
                SELECT lo.NUM_INGR_EFEC_OBJE
                  INTO vnNumObjetivoIngresos
                  FROM LET_CORPO_LIDER_OBJET lo
                 WHERE lo.COD_CLIE_LIDE = vsCodGerente
                   AND lo.CAM_OBJE = psCodigoPeriodo
                   AND lo.IND_ACTI = '1';
                    EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                            vnNumObjetivoIngresos := NULL;
                    END;


                    --SE BUSCA LA LIDER EN HISTO_GEREN CON CAMPA�A ANTERIOR
                      BEGIN
                      SELECT DISTINCT(GERE), COD_REGI, COD_ZONA
                      INTO vsCodLA,  vsCodRegLA, vsCodZonLA
                      FROM ZON_HISTO_GEREN
                      WHERE vnOidPeriodoAnterior >= PERD_OID_PERI_DESD
                        AND (vnOidPeriodoAnterior <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
                        AND COD_SECC IS NOT NULL
                        AND GERE = vsCodGerente;
                      EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            vsCodLA := NULL;
                            vsCodRegLA := null;
                            vscodZonLA:= null;
                      END;



                --Obtener Ingresos Efectivos Reales
                     vnNumIngERTotal := 0 ;
                OPEN c_recomendacionesLider(vsCodGerente, psCodigoPais, psCodigoPeriodo);
                LOOP FETCH c_recomendacionesLider INTO vsOidClienVinc, vsOidVinculante, vsOidVinculado;

                    EXIT WHEN c_recomendacionesLider%NOTFOUND;

                        --SI LA CONSULTORA NO FUE LIDER EN LA CAMPA�A ANTERIOR
                        --OBTENGO COD_REGI, COD_ZONA
                         IF (vsCodLA IS NULL) THEN
                               BEGIN
                                   SELECT MCUA.CLIE_OID_CLIE, ZR.COD_REGI, ZZ.COD_ZONA
                                      INTO vsOidLA, vsCodRegLA, vscodZonLA
                                   FROM MAE_CLIEN_UNIDA_ADMIN MCUA,
                                      ZON_TERRI_ADMIN ZTA,
                                      ZON_SECCI ZC,
                                      ZON_ZONA ZZ,
                                      ZON_REGIO ZR
                                   WHERE MCUA.CLIE_OID_CLIE = vsOidVinculante
                                   AND vnOidPeriodoAnterior >= PERD_OID_PERI_INI
                                   AND (vnOidPeriodoAnterior <= PERD_OID_PERI_FIN OR PERD_OID_PERI_FIN IS NULL)
                                   AND MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
                                   AND ZTA.ZSCC_OID_SECC = ZC.OID_SECC
                                   AND ZC.ZZON_OID_ZONA = ZZ.OID_ZONA
                                   AND ZZ.ZORG_OID_REGI = ZR.OID_REGI;
                                  EXCEPTION
                                   WHEN NO_DATA_FOUND THEN
                                     vsOidLA := NULL;
                                     vsCodRegLA := NULL;
                                     vscodZonLA := NULL;
                                END;

                       END IF;


                        IF (vnIndRestIngresosEfectivos = 'P') THEN
                            vnClieOidClieUnidadAdmin :=  vsOidVinculado;
                        END IF;

                        IF (vnIndRestIngresosEfectivos = 'R') THEN
                              IF (vsCodLA IS NULL) THEN
                                  BEGIN
                                      SELECT mcua.CLIE_OID_CLIE
                                        INTO vnClieOidClieUnidadAdmin--obtiene codigoUnidadAdmin
                                        FROM MAE_CLIEN_UNIDA_ADMIN mcua,
                                             MAE_CLIEN_VINCU mcv,
                                             ZON_TERRI_ADMIN zta,
                                             ZON_SECCI zs,
                                             ZON_ZONA zz,
                                             ZON_REGIO zr
                                       WHERE mcua.CLIE_OID_CLIE = mcv.CLIE_OID_CLIE_VNDO
                                         AND mcua.CLIE_OID_CLIE = vsOidVinculado
                                         AND vnOidPeriodoAnterior >= mcua.PERD_OID_PERI_INI
                                         AND (vnOidPeriodoAnterior <= mcua.PERD_OID_PERI_FIN OR mcua.PERD_OID_PERI_FIN IS NULL)
                                         AND mcua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
                                         AND zta.ZSCC_OID_SECC = zs.OID_SECC
                                         AND zs.ZZON_OID_ZONA = zz.OID_ZONA
                                         AND zz.ZORG_OID_REGI = zr.OID_REGI
                                         AND zr.COD_REGI = vsCodRegLA --codigoRegion Lider Campa�a Anterior
                                       GROUP BY mcua.OID_CLIE_UNID_ADMI, mcua.CLIE_OID_CLIE;
                                  EXCEPTION
                                    WHEN NO_DATA_FOUND THEN
                                      vnClieOidClieUnidadAdmin := NULL;
                                  END;

                              ELSE
                          BEGIN
                                      SELECT mcua.CLIE_OID_CLIE
                                        INTO vnClieOidClieUnidadAdmin--obtiene codigoUnidadAdmin
                          FROM MAE_CLIEN_UNIDA_ADMIN mcua,
                               MAE_CLIEN_VINCU mcv,
                               ZON_TERRI_ADMIN zta,
                               ZON_SECCI zs,
                               ZON_ZONA zz,
                               ZON_REGIO zr
                         WHERE mcua.CLIE_OID_CLIE = mcv.CLIE_OID_CLIE_VNDO
                           AND mcua.CLIE_OID_CLIE = vsOidVinculado
                                         AND vnOidPeriodoAnterior >= mcua.PERD_OID_PERI_INI
                                         AND (vnOidPeriodoAnterior <= mcua.PERD_OID_PERI_FIN OR mcua.PERD_OID_PERI_FIN IS NULL)
                           AND mcua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
                           AND zta.ZSCC_OID_SECC = zs.OID_SECC
                           AND zs.ZZON_OID_ZONA = zz.OID_ZONA
                           AND zz.ZORG_OID_REGI = zr.OID_REGI
                                         AND zr.COD_REGI = vsCodRegion--codigoRegion Lider Campa�a Proceso
                         GROUP BY mcua.OID_CLIE_UNID_ADMI, mcua.CLIE_OID_CLIE;
                          EXCEPTION
                            WHEN NO_DATA_FOUND THEN
                              vnClieOidClieUnidadAdmin := NULL;
                          END;

                    END IF;

                        END IF;

                        IF (vnIndRestIngresosEfectivos = 'Z') THEN
                           IF (vsCodLA IS NULL) THEN
                          BEGIN
                                    SELECT mcua.CLIE_OID_CLIE
                                      INTO vnClieOidClieUnidadAdmin--obtiene codigoUnidadAdmin
                                      FROM MAE_CLIEN_UNIDA_ADMIN mcua,
                                           MAE_CLIEN_VINCU mcv,
                                           ZON_TERRI_ADMIN zta,
                                           ZON_SECCI zs,
                                           ZON_ZONA zz,
                                           ZON_REGIO zr
                                     WHERE mcua.CLIE_OID_CLIE = mcv.CLIE_OID_CLIE_VNDO
                                       AND mcua.CLIE_OID_CLIE = vsOidVinculado
                                       AND vnOidPeriodoAnterior >= mcua.PERD_OID_PERI_INI
                                       AND (vnOidPeriodoAnterior <= mcua.PERD_OID_PERI_FIN OR mcua.PERD_OID_PERI_FIN IS NULL)
                                       AND mcua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
                                       AND zta.ZSCC_OID_SECC = zs.OID_SECC
                                       AND zs.ZZON_OID_ZONA = zz.OID_ZONA
                                       AND zz.ZORG_OID_REGI = zr.OID_REGI
                                       AND zr.COD_REGI = vsCodRegLA--codigoRegion Lider Campa�a Anterior
                                       AND zz.COD_ZONA = vscodZonLA--codigoZona Lider Campa�a Anterior
                                     GROUP BY mcua.OID_CLIE_UNID_ADMI, mcua.CLIE_OID_CLIE;
                                EXCEPTION
                                  WHEN NO_DATA_FOUND THEN
                                    vnClieOidClieUnidadAdmin := NULL;
                                END;

                           ELSE
                                 BEGIN
                                    SELECT mcua.CLIE_OID_CLIE
                                      INTO vnClieOidClieUnidadAdmin--obtiene codigoUnidadAdmin
                          FROM MAE_CLIEN_UNIDA_ADMIN mcua,
                               MAE_CLIEN_VINCU mcv,
                               ZON_TERRI_ADMIN zta,
                               ZON_SECCI zs,
                               ZON_ZONA zz,
                               ZON_REGIO zr
                         WHERE mcua.CLIE_OID_CLIE = mcv.CLIE_OID_CLIE_VNDO
                           AND mcua.CLIE_OID_CLIE = vsOidVinculado
                                       AND vnOidPeriodoAnterior >= mcua.PERD_OID_PERI_INI
                                       AND (vnOidPeriodoAnterior <= mcua.PERD_OID_PERI_FIN OR mcua.PERD_OID_PERI_FIN IS NULL)
                           AND mcua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
                           AND zta.ZSCC_OID_SECC = zs.OID_SECC
                           AND zs.ZZON_OID_ZONA = zz.OID_ZONA
                           AND zz.ZORG_OID_REGI = zr.OID_REGI
                                       AND zr.COD_REGI = vsCodRegion--codigoRegion Lider Campa�a Proceso
                                       AND zz.COD_ZONA = vsCodZona--codigoRegion Lider Campa�a Proceso
                         GROUP BY mcua.OID_CLIE_UNID_ADMI, mcua.CLIE_OID_CLIE;
                          EXCEPTION
                            WHEN NO_DATA_FOUND THEN
                              vnClieOidClieUnidadAdmin := NULL;
                          END;

                    END IF;

                        END IF;


                        --CONTEO de los ingresos.
                        IF(vnClieOidClieUnidadAdmin IS NOT NULL) THEN
                    SELECT COUNT(1)
                              INTO vnNumIngER
                              FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC pts, PED_TIPO_SOLIC_PAIS ptsp
                             WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                               AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                               AND psc.PERD_OID_PERI = vnOidPeriodo --periodo del proceso
                               AND psc.CLIE_OID_CLIE = vnClieOidClieUnidadAdmin
                               AND psc.FEC_FACT IS NOT NULL
                               AND psc.IND_TS_NO_CONSO = 1
                               AND psc.IND_OC = 1
                               AND psc.IND_PEDI_PRUE = 0
                               AND pts.IND_DEVO = 0
                               AND pts.IND_ANUL = 0;

                            SELECT COUNT(1)
                              INTO vnNumIngERAnt
                      FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC pts, PED_TIPO_SOLIC_PAIS ptsp
                     WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                       AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                               AND psc.PERD_OID_PERI = vnOidPeriodoAnterior --periodo del procesoAnterior
                       AND psc.CLIE_OID_CLIE = vnClieOidClieUnidadAdmin
                       AND psc.FEC_FACT IS NOT NULL
                       AND psc.IND_TS_NO_CONSO = 1
                       AND psc.IND_OC = 1
                       AND psc.IND_PEDI_PRUE = 0
                       AND pts.IND_DEVO = 0
                       AND pts.IND_ANUL = 0;

                            IF vnNumIngER > 0 AND  vnNumIngERAnt > 0 THEN
                               vnNumIngERTotal := vnNumIngERTotal + 1;
                            END IF;

                    END IF;

                END LOOP;
                CLOSE c_recomendacionesLider;
            END IF;

            IF vnIndRetencion33 = '1' THEN
                --Obtener Objetivos Ingresos 33
                    BEGIN
                SELECT so.NUM_INGR_RETE_33_OBJ
                  INTO vnNumObjetivoIngreso33
                  FROM LET_CORPO_SECCI_OBJET so
                 WHERE so.COD_PROG = vnCodigoPrograma
                   AND so.COD_REGI = vsCodRegion
                   AND so.COD_ZONA = vsCodZona
                   AND so.COD_SECC = vsCodSeccion
                   AND so.CAM_OBJE = psCodigoPeriodo
                   AND so.IND_ACTI = '1';
                    EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                         vnNumObjetivoIngreso33 := NULL;
                    END;


                --Obtener Retencion 33
                    vnNumRetencion33Total := 0;   ----   Doris
                    vnNumRetencion33Actual := 0;
                    vnNumRetencion33Ant1 := 0;
                OPEN c_ingresosHistorialEstatus33(psCodigoPais, psCodigoPeriodo);
                LOOP FETCH c_ingresosHistorialEstatus33 INTO vsOidPeriodoIng, vsOidClienteIng, vsOidEstadoIng;

                    EXIT WHEN c_ingresosHistorialEstatus33%NOTFOUND;
                    BEGIN
                    SELECT mcua.OID_CLIE_UNID_ADMI, mcua.CLIE_OID_CLIE
                      INTO vnOidClienUnidadAdmin, vnClieOidClieUnidadAdmin
                      FROM MAE_CLIEN_UNIDA_ADMIN mcua,
                           ZON_TERRI_ADMIN zta,
                           ZON_SECCI zs,
                           ZON_ZONA zz,
                           ZON_REGIO zr
                     WHERE mcua.CLIE_OID_CLIE = vsOidClienteIng
                       AND vnOidPeriodo >= mcua.PERD_OID_PERI_INI
                       AND (vnOidPeriodo <= mcua.PERD_OID_PERI_FIN OR mcua.PERD_OID_PERI_FIN IS NULL)
                       AND mcua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
                       AND zta.ZSCC_OID_SECC = zs.OID_SECC
                       AND zs.ZZON_OID_ZONA = zz.OID_ZONA
                       AND zz.ZORG_OID_REGI = zr.OID_REGI
                       AND zr.COD_REGI = vsCodRegion
                       AND zz.COD_ZONA = vsCodZona
                       AND zs.COD_SECC = vsCodSeccion;
                                          EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                               vnClieOidClieUnidadAdmin := NULL;
                     END ;

                        IF(vnClieOidClieUnidadAdmin IS NOT NULL) THEN
                             SELECT COUNT(1)
                                INTO vnNumRetencion33Actual
                                FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC pts, PED_TIPO_SOLIC_PAIS ptsp
                               WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                                 AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                                 AND psc.PERD_OID_PERI = vnOidPeriodo--periodo del Proceso
                                 AND psc.CLIE_OID_CLIE = vnClieOidClieUnidadAdmin
                                 AND psc.FEC_FACT IS NOT NULL
                                 AND psc.IND_TS_NO_CONSO = 1
                                 AND psc.IND_OC = 1
                                 AND psc.IND_PEDI_PRUE = 0
                                 AND pts.IND_DEVO = 0
                                 AND pts.IND_ANUL = 0;

                    SELECT COUNT(1)
                                INTO vnNumRetencion33Ant1
                                FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC pts, PED_TIPO_SOLIC_PAIS ptsp
                               WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                                 AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                                 AND psc.PERD_OID_PERI = vnOidPeriodoAnterior --periodo del ProcesoAnt
                                 AND psc.CLIE_OID_CLIE = vnClieOidClieUnidadAdmin
                                 AND psc.FEC_FACT IS NOT NULL
                                 AND psc.IND_TS_NO_CONSO = 1
                                 AND psc.IND_OC = 1
                                 AND psc.IND_PEDI_PRUE = 0
                                 AND pts.IND_DEVO = 0
                                 AND pts.IND_ANUL = 0;

                             SELECT COUNT(1)
                                INTO vnNumRetencion33Ant2
                      FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC pts, PED_TIPO_SOLIC_PAIS ptsp
                     WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                       AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                                 AND psc.PERD_OID_PERI = vnOidPeriodoAnterior2 --periodo del ProcesoAnt2
                       AND psc.CLIE_OID_CLIE = vnClieOidClieUnidadAdmin
                       AND psc.FEC_FACT IS NOT NULL
                       AND psc.IND_TS_NO_CONSO = 1
                       AND psc.IND_OC = 1
                       AND psc.IND_PEDI_PRUE = 0
                       AND pts.IND_DEVO = 0
                       AND pts.IND_ANUL = 0;

                                 IF vnNumRetencion33Actual > 0 AND vnNumRetencion33Ant1 > 0 AND vnNumRetencion33Ant2 > 0 THEN
                                  vnNumRetencion33Total := vnNumRetencion33Total  + 1;
                                 END IF;

                    END IF;

                END LOOP;
                CLOSE c_ingresosHistorialEstatus33;
            END IF;


            IF vnIndRetencion44 = '1' THEN
                --Obtener Objetivos Ingresos 44
                    BEGIN
                SELECT so.NUM_INGR_RETE_44_OBJ
                  INTO vnNumObjetivoIngreso44
                  FROM LET_CORPO_SECCI_OBJET so
                 WHERE so.COD_PROG = vnCodigoPrograma
                   AND so.COD_REGI = vsCodRegion
                   AND so.COD_ZONA = vsCodZona
                   AND so.COD_SECC = vsCodSeccion
                   AND so.CAM_OBJE = psCodigoPeriodo
                   AND so.IND_ACTI = '1';
                    EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                         vnNumObjetivoIngreso44 := NULL;
                    END;

                --Obtener Retencion 44
                    vnNumRetencion44Actual:= 0;
                    vnNumRetencion44Ant1 := 0;
                    vnNumRetencion44Ant2 := 0;
                    vnNumRetencion44Ant3 := 0;
                    vnNumRetencion44Total := 0;

                OPEN c_ingresosHistorialEstatus44(psCodigoPais, psCodigoPeriodo);
                LOOP FETCH c_ingresosHistorialEstatus44 INTO vsOidPeriodoIng, vsOidClienteIng, vsOidEstadoIng;

                    EXIT WHEN c_ingresosHistorialEstatus44%NOTFOUND;

                    BEGIN
                    SELECT mcua.OID_CLIE_UNID_ADMI, mcua.CLIE_OID_CLIE
                      INTO vnOidClienUnidadAdmin, vnClieOidClieUnidadAdmin
                      FROM MAE_CLIEN_UNIDA_ADMIN mcua,
                           ZON_TERRI_ADMIN zta,
                           ZON_SECCI zs,
                           ZON_ZONA zz,
                           ZON_REGIO zr
                     WHERE mcua.CLIE_OID_CLIE = vsOidClienteIng
                       AND vnOidPeriodo >= mcua.PERD_OID_PERI_INI
                       AND (vnOidPeriodo <= mcua.PERD_OID_PERI_FIN OR mcua.PERD_OID_PERI_FIN IS NULL)
                       AND mcua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
                       AND zta.ZSCC_OID_SECC = zs.OID_SECC
                       AND zs.ZZON_OID_ZONA = zz.OID_ZONA
                       AND zz.ZORG_OID_REGI = zr.OID_REGI
                       AND zr.COD_REGI = vsCodRegion
                       AND zz.COD_ZONA = vsCodZona
                       AND zs.COD_SECC = vsCodSeccion;
                       EXCEPTION
                         WHEN NO_DATA_FOUND THEN
                               vnClieOidClieUnidadAdmin := NULL;
                     END ;

                         IF(vnClieOidClieUnidadAdmin IS NOT NULL) THEN
                    SELECT COUNT(1)
                              INTO vnNumRetencion44Actual
                      FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC pts, PED_TIPO_SOLIC_PAIS ptsp
                     WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                       AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                               AND psc.PERD_OID_PERI = vnOidPeriodo --periodo del Proceso
                       AND psc.CLIE_OID_CLIE = vnClieOidClieUnidadAdmin
                       AND psc.FEC_FACT IS NOT NULL
                       AND psc.IND_TS_NO_CONSO = 1
                       AND psc.IND_OC = 1
                       AND psc.IND_PEDI_PRUE = 0
                       AND pts.IND_DEVO = 0
                       AND pts.IND_ANUL = 0;

                            SELECT COUNT(1)
                              INTO vnNumRetencion44Ant1
                              FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC pts, PED_TIPO_SOLIC_PAIS ptsp
                             WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                               AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                               AND psc.PERD_OID_PERI = vnOidPeriodoAnterior --periodo del ProcesoAnt1
                               AND psc.CLIE_OID_CLIE = vnClieOidClieUnidadAdmin
                               AND psc.FEC_FACT IS NOT NULL
                               AND psc.IND_TS_NO_CONSO = 1
                               AND psc.IND_OC = 1
                               AND psc.IND_PEDI_PRUE = 0
                               AND pts.IND_DEVO = 0
                               AND pts.IND_ANUL = 0;

                            SELECT COUNT(1)
                              INTO vnNumRetencion44Ant2
                              FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC pts, PED_TIPO_SOLIC_PAIS ptsp
                             WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                               AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                               AND psc.PERD_OID_PERI = vnOidPeriodoAnterior2 --periodo del ProcesoAnt2
                               AND psc.CLIE_OID_CLIE = vnClieOidClieUnidadAdmin
                               AND psc.FEC_FACT IS NOT NULL
                               AND psc.IND_TS_NO_CONSO = 1
                               AND psc.IND_OC = 1
                               AND psc.IND_PEDI_PRUE = 0
                               AND pts.IND_DEVO = 0
                               AND pts.IND_ANUL = 0;

                            SELECT COUNT(1)
                              INTO vnNumRetencion44Ant3
                              FROM PED_SOLIC_CABEC psc, PED_TIPO_SOLIC pts, PED_TIPO_SOLIC_PAIS ptsp
                             WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                               AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                               AND psc.PERD_OID_PERI = vnOidPeriodoAnterior3 --periodo del ProcesoAnt3
                               AND psc.CLIE_OID_CLIE = vnClieOidClieUnidadAdmin
                               AND psc.FEC_FACT IS NOT NULL
                               AND psc.IND_TS_NO_CONSO = 1
                               AND psc.IND_OC = 1
                               AND psc.IND_PEDI_PRUE = 0
                               AND pts.IND_DEVO = 0
                               AND pts.IND_ANUL = 0;

                            IF vnNumRetencion44Actual > 0 AND vnNumRetencion44Ant1 > 0 AND vnNumRetencion44Ant2 > 0
                                AND vnNumRetencion44Ant3 > 0 THEN
                                 vnNumRetencion44Total := vnNumRetencion44Total + 1;
                            END IF;
                    END IF;
                END LOOP;
                CLOSE c_ingresosHistorialEstatus44;

            END IF;

            --Obtener Clasificacion Lider
            BEGIN
                SELECT lclc.COD_CLAS_LIDE
                  INTO vnCodigoClasLider
                  FROM LET_CORPO_LIDER_CLASI lclc
                 WHERE lclc.COD_CLIE_LIDE = vsCodGerente
                   AND psCodigoPeriodo >= lclc.CAM_INIC
                   AND (psCodigoPeriodo <= lclc.CAM_FIN OR lclc.CAM_FIN IS NULL)
                   AND lclc.IND_ACTI = '1';
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    vnCodigoClasLider := NULL;
            END;

            --Obtener Nivel Exito Lider
            BEGIN
                SELECT lcln.COD_NIVE
                  INTO vnCodigoNivelExito
                  FROM LET_CORPO_LIDER_NIVEL lcln
                 WHERE lcln.COD_CLIE_LIDE = vsCodGerente
                   AND psCodigoPeriodo >= lcln.CAM_INIC
                   AND (psCodigoPeriodo <= lcln.CAM_FIN OR lcln.CAM_FIN IS NULL)
                       AND IND_TIPO_NIVE = 'R'
                   AND lcln.IND_ACTI = '1';
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    vnCodigoNivelExito := NULL;
            END;

            --Obtener Tolerancia
            BEGIN
                SELECT lcprn.NUM_PEDI_TOLE
                  INTO vnNumPedidoTolerancia
                  FROM LET_CORPO_PARAM_RANGO_NIVEL lcprn
                 WHERE lcprn.COD_PROG = vnCodigoPrograma
                   AND lcprn.COD_NIVE = vnCodigoNivelExito;
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    vnNumPedidoTolerancia := NULL;
            END;

            --Calculos finales de los valores a insertar
                --Numero de Pedidos Reales, Objetivos, Sobrantes, Tolerados
                 vDiferenciaPedidos := 0;
                 vnNumPedidosRealesSobr := 0;
                 vnPedTolerancia := 0;
                  IF(vnNumPedidosReales > vnNumPedidosObjetivos) THEN --vreal > vobje
                vnNumPedidosRealesSobr := vnNumPedidosReales - vnNumPedidosObjetivos;
                     vnCodEstaObjetivoPedido := '02';
                     vnIndProductividad := 'E';
                  ELSIF(vnNumPedidosReales < vnNumPedidosObjetivos) THEN--vreal < vobje
                     vnIndProductividad := 'N';
                vnNumPedidosRealesSobr := 0;
                    vDiferenciaPedidos := vnNumPedidosObjetivos - vnNumPedidosReales;
                      IF(vDiferenciaPedidos <= vnNumPedidoTolerancia) THEN
                        vnCodEstaObjetivoPedido := '03';
                         vnPedTolerancia := vDiferenciaPedidos;
                    ELSE
                        vnCodEstaObjetivoPedido := '04';
                    END IF;
                  ELSE -- vreal = vobje
                    vnNumPedidosRealesSobr := 0;
                    vnCodEstaObjetivoPedido := '01';
                    vnIndProductividad := 'E';
            END IF;

                  --calculo del % criticidad
                  vnPorcCriticidad := CEIL(ROUND((vnNumPedidosReales * 100)/vnNumPedidosObjetivos,2));

                --objetivoIngreso
                IF(vnNumObjetivoIngresos IS NOT NULL) THEN
                   IF vnNumIngERTotal >= vnNumObjetivoIngresos THEN
                vnCodEstaObjetivoIngreso := '05';
            ELSE
                vnCodEstaObjetivoIngreso := '06';
            END IF;
                END IF;

                --objetivoIngreso33
                IF(vnNumObjetivoIngreso33 IS NOT NULL) THEN
                    IF(vnNumRetencion33Total >= vnNumObjetivoIngreso33) THEN
                vnCodEstaObjetivo33 := '07';
            ELSE
                vnCodEstaObjetivo33 := '08';
            END IF;
                END IF;
                --objetivoIngreso44
                IF(vnNumObjetivoIngreso44 IS NOT NULL) THEN
                   IF(vnNumRetencion44Total >= vnNumObjetivoIngreso44) THEN
                vnCodEstaObjetivo44 := '09';
            ELSE
                vnCodEstaObjetivo44 := '10';
            END IF;
                END IF;

                --IF vnNumPedidosReales >= vnNumPedidosObjetivos THEN
                    --vnIndProductividad := 'E';
                --ELSE
                    --vnIndProductividad := 'N';
                --END IF;

            --Calculo de %Criticidad
                --vnPorcCriticidad := (vnNumPedidosReales  - vnNumPedidosObjetivos)/vnNumPedidosObjetivos;

                BEGIN
                  SELECT COUNT(1) INTO vsRegSecciResul
                  FROM LET_CORPO_SECCI_RESUL
                      WHERE COD_PROG = vnCodigoPrograma
                      AND COD_PAIS = psCodigoPais
                      AND COD_CLIE_LIDE = vsCodGerente
                      AND COD_REGI = vsCodRegion
                      AND COD_ZONA = vsCodZona
                      AND COD_SECC = vsCodSeccion
                      AND CAM_OBJE = psCodigoPeriodo;
                END;

                IF (vsRegSecciResul > 0) THEN
                   UPDATE LET_CORPO_SECCI_RESUL
                    SET
                       NUM_PEDI_REAL = vnNumPedidosReales,
                       NUM_PEDI_SOBR = vnNumPedidosRealesSobr,
                       NUM_INGR_REAL = vnNumIngERTotal,
                       NUM_PEDI_TOLE =  vnPedTolerancia,
                       NUM_INGR_33_REAL = vnNumRetencion33Total,
                       NUM_INGR_44_REAL = vnNumRetencion44Total,
                       COD_ESTA_OBJE_PEDI = vnCodEstaObjetivoPedido,
                       COD_ESTA_OBJE_INGR = vnCodEstaObjetivoIngreso,
                       COD_ESTA_OBJE_R33 = vnCodEstaObjetivo33,
                       COD_ESTA_OBJE_R44 = vnCodEstaObjetivo44,
                       IND_PROD = vnIndProductividad,
                       VAL_PORC_CRIT = vnPorcCriticidad,
                       IND_ENVI_BAJA = '0',
                       USU_MODI = psUsuario,
                       FEC_MODI = SYSDATE,
                       IND_ACTI = '1'
                      WHERE COD_PROG = vnCodigoPrograma
                      AND COD_PAIS = psCodigoPais
                      AND COD_CLIE_LIDE = vsCodGerente
                      AND COD_REGI = vsCodRegion
                      AND COD_ZONA = vsCodZona
                      AND COD_SECC = vsCodSeccion
                      AND CAM_OBJE = psCodigoPeriodo;

                      COMMIT;
                 ELSE
            INSERT INTO LET_CORPO_SECCI_RESUL
                    (COD_PAIS, COD_CLIE_LIDE, COD_PROG, COD_REGI, COD_ZONA, COD_SECC, CAM_OBJE,
                         NUM_PEDI_REAL, NUM_PEDI_SOBR,  NUM_PEDI_TOLE,NUM_INGR_REAL, NUM_INGR_33_REAL, NUM_INGR_44_REAL,
                     COD_ESTA_OBJE_PEDI, COD_ESTA_OBJE_INGR, COD_ESTA_OBJE_R33, COD_ESTA_OBJE_R44,
                     IND_PROD,
                     VAL_PORC_CRIT,
                     IND_ENVI_BAJA,
                     USU_CREA,
                     FEC_CREA,
                     IND_ACTI
                    )
            VALUES
                    (psCodigoPais, vsCodGerente, vnCodigoPrograma, vsCodRegion, vsCodZona, vsCodSeccion, psCodigoPeriodo,
                         vnNumPedidosReales, vnNumPedidosRealesSobr, vnPedTolerancia, vnNumIngERTotal, vnNumRetencion33Total, vnNumRetencion44Total,
                     vnCodEstaObjetivoPedido, vnCodEstaObjetivoIngreso, vnCodEstaObjetivo33, vnCodEstaObjetivo44,
                     vnIndProductividad,
                     vnPorcCriticidad,
                     '0',
                     psUsuario,
                     SYSDATE,
                         '1');

                         COMMIT;
                 END IF;

             END IF;


        END LOOP;
        CLOSE c_liderActivoProcesoRegion;
    END IF;

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_RESUL_LIDER: ' || ls_sqlerrm);
  END LET_PR_CALCU_RESUL_LIDER;


    /**********************************************************************************
    Descripcion       : Proceso de Actualizaci�n de Clasificaci�n Lider
    Fecha Creacion    : 18/06/2013
    Autor             : Danny Amaro
    **********************************************************************************/
    PROCEDURE LET_PR_ACTUA_CLASI_CORPO(psCodigoPais VARCHAR2,
                                       psCodigoRegion VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psUsuario VARCHAR2)IS

    vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
    vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;
    vnOidMarca              SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal              SEG_CANAL.OID_CANA%TYPE;

    CURSOR cLideresActivos(oidPeriodo CRA_PERIO.OID_PERI%TYPE) IS

    SELECT ZHG.GERE
      FROM ZON_HISTO_GEREN ZHG
     WHERE ZHG.PERD_OID_PERI_DESD <= oidPeriodo
       AND nvl(ZHG.PERD_OID_PERI_HAST,oidPeriodo) >= oidPeriodo
       AND ZHG.COD_SECC IS NOT NULL;

    CURSOR cLideresPeriodoAnterior(oidPeriodo CRA_PERIO.OID_PERI%TYPE, oidPeriodoAnt CRA_PERIO.OID_PERI%TYPE) IS

    SELECT ZHG.GERE
      FROM ZON_HISTO_GEREN ZHG
     WHERE ZHG.PERD_OID_PERI_DESD <= oidPeriodoAnt
       AND ZHG.PERD_OID_PERI_HAST >= oidPeriodoAnt
       AND ZHG.COD_SECC IS NOT NULL
       AND ZHG.GERE
     NOT IN (SELECT AUX.GERE
                            FROM ZON_HISTO_GEREN AUX
                           WHERE AUX.PERD_OID_PERI_DESD <= oidPeriodo
                             AND AUX.PERD_OID_PERI_HAST >= oidPeriodo
                             AND AUX.COD_SECC IS NOT NULL);

    vsCodGerente            ZON_HISTO_GEREN.GERE%TYPE;
    vsCodGerenteAnt         ZON_HISTO_GEREN.GERE%TYPE;

    vsCodigoPeridoAnterior  SEG_PERIO_CORPO.COD_PERI%TYPE;
    vnOidPeriodoanterior    CRA_PERIO.OID_PERI%TYPE;

    BEGIN

      vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
      vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
      vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
      vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

      OPEN cLideresActivos(vnOidPeriodo);

      LOOP FETCH cLideresActivos INTO vsCodGerente;

         EXIT WHEN cLideresActivos%NOTFOUND;

         LET_PR_PROCE_CLASI_LIDER(psCodigoPais, psCodigoMarca, psCodigoCanal, '1', vsCodGerente, psCodigoPeriodo, psUsuario);

      END LOOP;

      CLOSE cLideresActivos;

/*      vsCodigoPeridoAnterior := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-1);

      vnOidPeriodoanterior := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodigoPeridoAnterior);

      OPEN cLideresPeriodoAnterior(vnOidPeriodo, vnOidPeriodoanterior);

      LOOP FETCH cLideresPeriodoAnterior INTO vsCodGerenteAnt;

         EXIT WHEN cLideresPeriodoAnterior%NOTFOUND;

         LET_PR_PROCE_CLASI_LIDER(psCodigoPais, psCodigoMarca, psCodigoCanal, '2', vsCodGerenteAnt, vsCodigoPeridoAnterior, psUsuario);

      END LOOP;

      CLOSE cLideresPeriodoAnterior;
*/
    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR LET_PR_ACTUA_CLASI_CORPO: ' ||
                                ls_sqlerrm);
    END LET_PR_ACTUA_CLASI_CORPO;


    /**********************************************************************************
    Descripcion       : Proceso de Clasificaci�n Lider
    Fecha Creacion    : 19/06/2013
    Autor             : Danny Amaro
    **********************************************************************************/
    PROCEDURE LET_PR_PROCE_CLASI_LIDER(psCodigoPais VARCHAR2,
                                        psCodigoMarca VARCHAR2,
                                        psCodigoCanal VARCHAR2,
                                        psTipoEvaluacion VARCHAR2,
                                        psCodGerente VARCHAR2,
                                        psCodigoPeriodo VARCHAR2,
                                        psUsuario VARCHAR2)IS


    vnOidUltimoPeriodo           CRA_PERIO.OID_PERI%TYPE;
    vsCodUltimoPeriodo           SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCodPeriodoAux              SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCodPeriodoAux2             SEG_PERIO_CORPO.COD_PERI%TYPE;

    vnOidPais                    SEG_PAIS.OID_PAIS%TYPE;
    vnOidMarca                   SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal                   SEG_CANAL.OID_CANA%TYPE;

    vsCodClasifLider             LET_CORPO_LIDER_CLASI.COD_CLAS_LIDE%TYPE;
    vsCodCampInicLider           LET_CORPO_LIDER_CLASI.CAM_INIC%TYPE;
    vsCodCampFinaLider           LET_CORPO_LIDER_CLASI.cam_fin%TYPE;

    lsIndLiderActual             VARCHAR2(1);
    lsIndLiderAnterior           VARCHAR2(1);
    lsEvaluarCampanaAnterior     BOOLEAN;
    lnContadorLiderSI            INTEGER;
    lnContadorLiderNO            INTEGER;
    lnContadorIteracion          INTEGER;
    vsCodCampEvalAux             SEG_PERIO_CORPO.COD_PERI%TYPE;

    lnCampNueva                  INTEGER;
    lnCampReingreso              INTEGER;
    lnCampEstablecida            INTEGER;
    ln1eraVez                    INTEGER;

    BEGIN
      vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
      vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
      vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

      BEGIN
        SELECT cl.val_nume_camp_requ
          INTO lnCampNueva
          FROM LET_corpo_clasi_lider cl
         WHERE cl.cod_clas_lide = '01';
       EXCEPTION
         WHEN OTHERS THEN
            ls_sqlerrm := 'NO ESTA CONFIGURADO CLASIFICACION NUEVA 01';
            RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_PROCE_CLASI_LIDER: ' || ls_sqlerrm);
      END;


      BEGIN
        SELECT cl.val_nume_camp_requ
          INTO lnCampReingreso
          FROM LET_corpo_clasi_lider cl
         WHERE cl.cod_clas_lide = '02';
       EXCEPTION
         WHEN OTHERS THEN
            ls_sqlerrm := 'NO ESTA CONFIGURADO CLASIFICACION REINGRESO 02';
            RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_PROCE_CLASI_LIDER: ' || ls_sqlerrm);
      END;


      BEGIN
        SELECT cl.val_nume_camp_requ
          INTO lnCampEstablecida
          FROM LET_corpo_clasi_lider cl
         WHERE cl.cod_clas_lide = '03';
       EXCEPTION
         WHEN OTHERS THEN
            ls_sqlerrm := 'NO ESTA CONFIGURADO CLASIFICACION ESTABLECIDA 03';
            RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_PROCE_CLASI_LIDER: ' || ls_sqlerrm);
      END;


      IF psTipoEvaluacion = '1' THEN -- (1:Tipo Evaluacion)

          SELECT MAX(nvl(PERD_OID_PERI_HAST,gen_pkg_gener.gen_fn_devuelve_id_cra_perio(psCodigoPeriodo,vnOidMarca,vnOidCanal)))
            INTO vnOidUltimoPeriodo
            FROM ZON_HISTO_GEREN
           WHERE GERE = psCodGerente
             AND COD_SECC IS NOT NULL;

         IF vnOidUltimoPeriodo IS NOT NULL THEN --(2)
            SELECT A.cod_peri
            INTO vsCodUltimoPeriodo
              FROM seg_perio_corpo A, cra_perio b, seg_canal c, seg_marca d
               WHERE b.oid_peri = vnOidUltimoPeriodo
               AND A.oid_peri = b.peri_oid_peri
             AND b.cana_oid_cana = c.oid_cana
             AND b.marc_oid_marc = d.oid_marc
             AND c.cod_cana = psCodigoCanal
             AND d.cod_marc = psCodigoMarca;

             IF vsCodUltimoPeriodo > psCodigoPeriodo THEN --(3)
               vsCodUltimoPeriodo:=psCodigoPeriodo;
               vnOidUltimoPeriodo:=gen_pkg_gener.gen_fn_devuelve_id_cra_perio(psCodigoPeriodo,vnOidMarca,vnOidCanal);
             END IF; --(3)

          vsCodPeriodoAux := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(vsCodUltimoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,18);

         END IF; --(2)


         IF vnOidUltimoPeriodo IS NULL OR (vsCodPeriodoAux < psCodigoPeriodo) THEN --(4)
            --NUEVA 01
            INSERT INTO LET_CORPO_LIDER_CLASI(
                   COD_CLIE_LIDE,
                   COD_CLAS_LIDE,
                   CAM_INIC,
                   CAM_FIN,
                   IND_CLAS_ACTI,
                   USU_CREA,
                   USU_MODI,
                   FEC_CREA,
                   FEC_MODI,
                   IND_ACTI
            )VALUES(
                   psCodGerente,
                   '01',
                   psCodigoPeriodo,
                   NULL,
                   '1',
                   psUsuario,
                   NULL,
                   SYSDATE,
                   NULL,
                   '1'
            );

          ELSE

            IF vsCodUltimoPeriodo = psCodigoPeriodo THEN
             BEGIN
                        SELECT COUNT(*)
                           INTO ln1eraVez
                          FROM LET_CORPO_LIDER_CLASI lc
                        WHERE COD_CLIE_LIDE = psCodGerente;

                        IF  ln1eraVez = 0 THEN
              BEGIN
                         --Verifica si es lider
                       SELECT COUNT(1) INTO lsIndLiderActual
                                   FROM zon_histo_geren G
                                 WHERE G.cod_secc IS NOT NULL
                                      AND G.gere = psCodGerente
                                      AND vnOidUltimoPeriodo BETWEEN G.perd_oid_peri_desd AND nvl(G.perd_oid_peri_hast,vnOidUltimoPeriodo);

                       lsIndLiderAnterior        := lsIndLiderActual;
                       lsEvaluarCampanaAnterior  := TRUE;
                       vsCodCampEvalAux := psCodigoPeriodo;
                       lnContadorLiderSI := 0;
                       lnContadorLiderNO := 0;
                       lnContadorIteracion :=0;

                       IF lsIndLiderActual = 1 THEN
                         lnContadorLiderSI := 1;
                         lnContadorLiderNO := 0;
                       ELSE
                         lnContadorLiderSI := 0;
                         lnContadorLiderNO := 1;
                       END IF;

                        WHILE lsEvaluarCampanaAnterior LOOP
                            IF lnContadorLiderSI > lnCampNueva THEN

                              lsIndLiderActual   := lsIndLiderAnterior;
                              vsCodCampEvalAux   := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,vsCodCampEvalAux,-1);
                              vnOidUltimoPeriodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(vsCodCampEvalAux,vnOidMarca,vnOidCanal);

                              SELECT COUNT(1) INTO lsIndLiderAnterior
                                    FROM zon_histo_geren G
                                   WHERE G.cod_secc IS NOT NULL
                                     AND G.gere = psCodGerente
                                     AND vnOidUltimoPeriodo BETWEEN G.perd_oid_peri_desd AND nvl(G.perd_oid_peri_hast,vnOidUltimoPeriodo);

                                 lnContadorIteracion := lnContadorIteracion +1;

                                 IF lsIndLiderAnterior = 1 THEN
                                    lnContadorLiderSI := lnContadorLiderSI + 1;
                                 ELSE
                               lsEvaluarCampanaAnterior := FALSE;
                               vsCodClasifLider         := '03'; --establecida
                                    IF lnContadorLiderSI = lnCampEstablecida THEN
                                      lnContadorLiderSI:=1;
                                    END IF;
                                 END IF;
                            ELSE
                               IF lnContadorLiderSI >= 0 AND lnContadorLiderSI <=lnCampNueva THEN
                                  IF lnContadorLiderNO < 18 THEN
                                     IF lnContadorIteracion > (18+lnCampReingreso) OR (lsIndLiderActual = 0 AND lsIndLiderAnterior = 1 ) THEN
                                        lsEvaluarCampanaAnterior := FALSE;
                                        vsCodClasifLider         := '02'; --Reingreso
                                     ELSE
                                        lsIndLiderActual   := lsIndLiderAnterior;
                                        vsCodCampEvalAux   := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,vsCodCampEvalAux,-1);
                                        vnOidUltimoPeriodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(vsCodCampEvalAux,vnOidMarca,vnOidCanal);

                                        SELECT COUNT(1) INTO lsIndLiderAnterior
                                              FROM zon_histo_geren G
                                             WHERE G.cod_secc IS NOT NULL
                                               AND G.gere = psCodGerente
                                               AND vnOidUltimoPeriodo BETWEEN G.perd_oid_peri_desd AND nvl(G.perd_oid_peri_hast,vnOidUltimoPeriodo);

                                         lnContadorIteracion := lnContadorIteracion +1;

                                        IF lsIndLiderActual = 0 AND lsIndLiderAnterior = 1 THEN
                                           CONTINUE;
                                        ELSE
                                           IF lsIndLiderAnterior = 1 THEN
                                              lnContadorLiderSI := lnContadorLiderSI + 1;
                                           ELSE
                                              lnContadorLiderNO := lnContadorLiderNO + 1;
                                           END IF;
                                        END IF;
                                     END IF;
                                  ELSE
                                     lsEvaluarCampanaAnterior := FALSE;
                                     vsCodClasifLider := '01'; --NUEVA

                              END IF;
                           END IF;
                        END IF;

                       END LOOP;


                       IF lnContadorLiderSI >= lnCampEstablecida THEN
                                     lnContadorLiderSI := lnContadorLiderSI - lnCampNueva;
                       END IF;

                       --insertar 1era vez clasificaci�n
                           INSERT INTO LET_CORPO_LIDER_CLASI(
                                   COD_CLIE_LIDE,
                                   COD_CLAS_LIDE,
                                   CAM_INIC,
                                   CAM_FIN,
                                   IND_CLAS_ACTI,
                                   USU_CREA,
                                   USU_MODI,
                                   FEC_CREA,
                                   FEC_MODI,
                                   IND_ACTI
                            )VALUES(
                                   psCodGerente,
                                   vsCodClasifLider,
                                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,psCodigoPeriodo,-lnContadorLiderSI+1)
                                   ,
                                   NULL,
                                   '1',
                                   psUsuario,
                                   NULL,
                                   SYSDATE,
                                   NULL,
                                   '1'
                            );
              END;

                        ELSE
                                BEGIN

                                     BEGIN
                                             SELECT COD_CLAS_LIDE,
                                                         CAM_INIC
                                                INTO vsCodClasifLider,
                                                        vsCodCampInicLider
                                               FROM LET_CORPO_LIDER_CLASI  lc
                                              WHERE COD_CLIE_LIDE = psCodGerente
                                                AND lc.cam_inic <= psCodigoPeriodo
                                                AND nvl(CAM_FIN,psCodigoPeriodo)>=psCodigoPeriodo;
                                            EXCEPTION
                                               WHEN NO_DATA_FOUND THEN
                                                 vsCodClasifLider := NULL;
                                                 vsCodCampInicLider := NULL;
                                   END;


                                            CASE vsCodClasifLider
                                              WHEN '01' THEN --si la clasifiacion actual es Nueva 01
                                                vsCodPeriodoAux2 := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,vsCodCampInicLider,lnCampNueva-1);
                                                IF vsCodPeriodoAux2 < psCodigoPeriodo THEN
                                                    UPDATE LET_CORPO_LIDER_CLASI
                                                         SET CAM_FIN       = gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,psCodigoPeriodo,-1),
                                                                IND_CLAS_ACTI = '0',
                                                                USU_MODI      = psUsuario,
                                                                FEC_MODI      = SYSDATE
                                                    WHERE COD_CLIE_LIDE = psCodGerente
                                                         AND CAM_FIN IS NULL;
                                                      --Cambia a Establecida 03
                                                     INSERT INTO LET_CORPO_LIDER_CLASI(
                                                             COD_CLIE_LIDE,
                                                             COD_CLAS_LIDE,
                                                             CAM_INIC,
                                                             CAM_FIN,
                                                             IND_CLAS_ACTI,
                                                             USU_CREA,
                                                             USU_MODI,
                                                             FEC_CREA,
                                                             FEC_MODI,
                                                             IND_ACTI
                                                      )VALUES(
                                                             psCodGerente,
                                                             '03',
                                                             psCodigoPeriodo,
                                                             NULL,
                                                             '1',
                                                             psUsuario,
                                                             NULL,
                                                             SYSDATE,
                                                             NULL,
                                                             '1'
                                                      );

                                                END IF;

                                             WHEN '02' THEN--si la clasifiacion actual es Reingreso 01
                                                vsCodPeriodoAux2 := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,vsCodCampInicLider,lnCampNueva-1);
                                                 IF vsCodPeriodoAux2 < psCodigoPeriodo THEN
                    UPDATE LET_CORPO_LIDER_CLASI
                       SET CAM_FIN       = gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,psCodigoPeriodo,-1),
                           IND_CLAS_ACTI = '0',
                           USU_MODI      = psUsuario,
                           FEC_MODI      = SYSDATE
                     WHERE COD_CLIE_LIDE = psCodGerente
                       AND CAM_FIN IS NULL;
                                                            --Cambia a Establecida 03
                                                           INSERT INTO LET_CORPO_LIDER_CLASI(
                                                                   COD_CLIE_LIDE,
                                                                   COD_CLAS_LIDE,
                                                                   CAM_INIC,
                                                                   CAM_FIN,
                                                                   IND_CLAS_ACTI,
                                                                   USU_CREA,
                                                                   USU_MODI,
                                                                   FEC_CREA,
                                                                   FEC_MODI,
                                                                   IND_ACTI
                                                            )VALUES(
                                                                   psCodGerente,
                                                                   '03',
                                                                   psCodigoPeriodo,
                                                                   NULL,
                                                                   '1',
                                                                   psUsuario,
                                                                   NULL,
                                                                   SYSDATE,
                                                                   NULL,
                                                                   '1'
                                                            );

                                                    END IF;

                                            WHEN '03' THEN--si la clasifiacion actual es Establecida 03
                                            BEGIN
                                                GOTO fin_case;
                                            END;

                                            ELSE
                                                --Verifica si tiene clasificacion en campana anterior
                                                vsCodCampEvalAux := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,psCodigoPeriodo,-1);
                                                  BEGIN
                                                SELECT COD_CLAS_LIDE,
                                                            CAM_INIC,
                                                            lc.cam_fin
                                                    INTO vsCodClasifLider,
                                                            vsCodCampInicLider,
                                                            vsCodCampFinaLider
                                                  FROM LET_CORPO_LIDER_CLASI lc
                                                WHERE COD_CLIE_LIDE = psCodGerente
                                                     AND lc.cam_inic <= vsCodCampEvalAux
                                                     AND nvl(lc.cam_fin,psCodigoPeriodo) >= vsCodCampEvalAux;
                                                     EXCEPTION
                                                     WHEN NO_DATA_FOUND THEN
                                                       vsCodClasifLider := NULL;
                                                       vsCodCampInicLider := NULL;
                                                  END;

                                                 CASE vsCodClasifLider
                                                   WHEN '01' THEN
                                                     IF vsCodCampFinaLider = vsCodCampEvalAux THEN --si fue desvinculada en campana anterior
                                                       vsCodPeriodoAux2 := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,vsCodCampInicLider,lnCampNueva-1);
                                                      IF vsCodPeriodoAux2 >= psCodigoPeriodo THEN -- Se mantiene con la clasificacion
                                                          UPDATE LET_CORPO_LIDER_CLASI lc
                                                             SET CAM_FIN       = NULL,
                                                                 IND_CLAS_ACTI = '1',
                                                                 USU_MODI      = psUsuario,
                                                                 FEC_MODI      = SYSDATE
                                                           WHERE COD_CLIE_LIDE = psCodGerente
                                                             AND lc.cam_inic <= vsCodCampEvalAux
                                                             AND nvl(lc.cam_fin,psCodigoPeriodo) >= vsCodCampEvalAux;
                                                      ELSE
                                                            --Cambia a Establecida 03
                     INSERT INTO LET_CORPO_LIDER_CLASI(
                             COD_CLIE_LIDE,
                             COD_CLAS_LIDE,
                             CAM_INIC,
                             CAM_FIN,
                             IND_CLAS_ACTI,
                             USU_CREA,
                             USU_MODI,
                             FEC_CREA,
                             FEC_MODI,
                             IND_ACTI
                      )VALUES(
                             psCodGerente,
                             '03',
                             psCodigoPeriodo,
                             NULL,
                             '1',
                             psUsuario,
                             NULL,
                             SYSDATE,
                             NULL,
                             '1'
                      );
                                                      END IF;
                                                    END IF;

                                                   WHEN '02' THEN
                                                     IF vsCodCampFinaLider = vsCodCampEvalAux THEN --si fue desvinculada en campana anterior
                                                       vsCodPeriodoAux2 := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,vsCodCampInicLider,lnCampNueva-1);
                                                       IF vsCodPeriodoAux2 >= psCodigoPeriodo THEN -- Se mantiene con la clasificacion
                                                          UPDATE LET_CORPO_LIDER_CLASI lc
                                                             SET CAM_FIN       = NULL,
                                                                 IND_CLAS_ACTI = '1',
                                                                 USU_MODI      = psUsuario,
                                                                 FEC_MODI      = SYSDATE
                                                           WHERE COD_CLIE_LIDE = psCodGerente
                                                             AND lc.cam_inic <= vsCodCampEvalAux
                                                             AND nvl(lc.cam_fin,psCodigoPeriodo) >= vsCodCampEvalAux;
                 ELSE
                                                            --Cambia a Establecida 03
                      INSERT INTO LET_CORPO_LIDER_CLASI(
                             COD_CLIE_LIDE,
                             COD_CLAS_LIDE,
                             CAM_INIC,
                             CAM_FIN,
                             IND_CLAS_ACTI,
                             USU_CREA,
                             USU_MODI,
                             FEC_CREA,
                             FEC_MODI,
                             IND_ACTI
                      )VALUES(
                             psCodGerente,
                                                                   '03',
                             psCodigoPeriodo,
                             NULL,
                             '1',
                             psUsuario,
                             NULL,
                             SYSDATE,
                             NULL,
                             '1'
                      );
                                                      END IF;
                                                    END IF;
                                                   WHEN '03' THEN
                                                     IF vsCodCampFinaLider = vsCodCampEvalAux THEN --si fue desvinculada en campana anterior
                                                          UPDATE LET_CORPO_LIDER_CLASI lc
                                                             SET CAM_FIN       = NULL,
                                                                 IND_CLAS_ACTI = '1',
                                                                 USU_MODI      = psUsuario,
                                                                 FEC_MODI      = SYSDATE
                                                           WHERE COD_CLIE_LIDE = psCodGerente
                                                             AND lc.cam_inic <= vsCodCampEvalAux
                                                             AND nvl(lc.cam_fin,psCodigoPeriodo) >= vsCodCampEvalAux;
                                                      END IF;
                                                   ELSE
                                                     lnContadorIteracion := 1;
                                                     lsEvaluarCampanaAnterior := TRUE;

                                                     WHILE lsEvaluarCampanaAnterior LOOP
                                                          vsCodCampEvalAux  := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,vsCodCampEvalAux,-1);
                                                          vnOidUltimoPeriodo  := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(vsCodCampEvalAux,vnOidMarca,vnOidCanal);

                                                          SELECT COUNT(1) INTO lsIndLiderAnterior
                                                            FROM zon_histo_geren G
                                                           WHERE G.cod_secc IS NOT NULL
                                                             AND G.gere = psCodGerente
                                                             AND vnOidUltimoPeriodo BETWEEN G.perd_oid_peri_desd AND nvl(G.perd_oid_peri_hast,vnOidUltimoPeriodo);

                                                             IF lsIndLiderAnterior = 1 THEN
                                                                lsEvaluarCampanaAnterior:= FALSE;
                                                             ELSE
                                                                lnContadorIteracion := lnContadorIteracion +1;
                 END IF;
                                                        END LOOP;

                                                     IF lnContadorIteracion < 18 THEN
                                                           --Registra Reingreso 02
                                                           INSERT INTO LET_CORPO_LIDER_CLASI(
                                                                   COD_CLIE_LIDE,
                                                                   COD_CLAS_LIDE,
                                                                   CAM_INIC,
                                                                   CAM_FIN,
                                                                   IND_CLAS_ACTI,
                                                                   USU_CREA,
                                                                   USU_MODI,
                                                                   FEC_CREA,
                                                                   FEC_MODI,
                                                                   IND_ACTI
                                                            )VALUES(
                                                                   psCodGerente,
                                                                   '02',
                                                                   psCodigoPeriodo,
                                                                   NULL,
                                                                   '1',
                                                                   psUsuario,
                                                                   NULL,
                                                                   SYSDATE,
                                                                   NULL,
                                                                   '1'
                                                            );
                                                     ELSE
                                                           --Registra Nueva 01
                                                           INSERT INTO LET_CORPO_LIDER_CLASI(
                                                                   COD_CLIE_LIDE,
                                                                   COD_CLAS_LIDE,
                                                                   CAM_INIC,
                                                                   CAM_FIN,
                                                                   IND_CLAS_ACTI,
                                                                   USU_CREA,
                                                                   USU_MODI,
                                                                   FEC_CREA,
                                                                   FEC_MODI,
                                                                   IND_ACTI
                                                            )VALUES(
                                                                   psCodGerente,
                                                                   '01',
                                                                   psCodigoPeriodo,
                                                                   NULL,
                                                                   '1',
                                                                   psUsuario,
                                                                   NULL,
                                                                   SYSDATE,
                                                                   NULL,
                                                                   '1'
                                                            );
              END IF;
                                                   END CASE;


                                             END CASE;

                                END;
                        END IF;
             END;
            END IF;


         END IF;

      END IF;

      <<fin_case>>

      IF psTipoEvaluacion = '2' THEN

         vsCodPeriodoAux := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,psCodigoPeriodo,1);

         BEGIN
             SELECT COD_CLAS_LIDE, CAM_INIC
              INTO vsCodClasifLider, vsCodCampInicLider
              FROM LET_CORPO_LIDER_CLASI
              WHERE COD_CLIE_LIDE = psCodGerente
              AND  psCodigoPeriodo  >= CAM_INIC
              AND ( psCodigoPeriodo <= CAM_FIN  OR  CAM_FIN IS NULL) ;
         EXCEPTION
         WHEN NO_DATA_FOUND THEN
            vsCodCampInicLider := NULL;
         END;

         IF vsCodCampInicLider = vsCodPeriodoAux THEN
           DELETE FROM LET_CORPO_LIDER_CLASI cc
                 WHERE COD_CLIE_LIDE = psCodGerente
                 AND  psCodigoPeriodo  >= CAM_INIC
                 AND ( psCodigoPeriodo <= CAM_FIN  OR  CAM_FIN IS NULL) ;
         END IF;

         BEGIN
         SELECT COD_CLAS_LIDE, CAM_INIC
              INTO vsCodClasifLider, vsCodCampInicLider
              FROM LET_CORPO_LIDER_CLASI
              WHERE COD_CLIE_LIDE = psCodGerente
              AND  psCodigoPeriodo  >= CAM_INIC
              AND ( psCodigoPeriodo <= CAM_FIN  OR  CAM_FIN IS NULL) ;
         EXCEPTION
         WHEN NO_DATA_FOUND THEN
            vsCodClasifLider := NULL;
         END;
         IF vsCodClasifLider IS not NULL THEN
         UPDATE LET_CORPO_LIDER_CLASI
            SET CAM_FIN       = psCodigoPeriodo,
                IND_CLAS_ACTI = '0',
                USU_MODI      = psUsuario,
                FEC_MODI      = SYSDATE
          WHERE COD_CLIE_LIDE = psCodGerente
              AND psCodigoPeriodo >= CAM_INIC
              AND ( psCodigoPeriodo <= CAM_FIN  OR  CAM_FIN IS NULL) ;
         END IF;

      END IF;


    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_PROCE_CLASI_LIDER: ' || ls_sqlerrm);
    END LET_PR_PROCE_CLASI_LIDER;
  /**********************************************************************************
    Descripcion       : Proceso de Calculo de Nivel de Exito Lider
    Fecha Creacion    : 20/06/2013
    Autor             : Danny Amaro
    **********************************************************************************/
    PROCEDURE LET_PR_PROCE_NIVEL_LIDER(psCodigoPais VARCHAR2,
                                       psCodLider VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
                                       psEscenario VARCHAR2,
                                       psUsuario VARCHAR2)IS

    vnOidPais                    SEG_PAIS.OID_PAIS%TYPE;
    vnOidMarca                   SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal                   SEG_CANAL.OID_CANA%TYPE;
    vnOidPeriodo                 CRA_PERIO.OID_PERI%TYPE;
    vnOidCampAux              CRA_PERIO.OID_PERI%TYPE;

    vsCodRegion                 ZON_HISTO_GEREN.Cod_Regi%TYPE;
    vsCodZona                   ZON_HISTO_GEREN.Cod_Zona%TYPE;
    vsCodSeccion                ZON_HISTO_GEREN.Cod_Secc%TYPE;
    vnNumPedObjet               LET_CORPO_SECCI_OBJET_PEDID.Num_Pedi_Obje%TYPE;
    vsCodProg                   LET_CORPO_SECCI_OBJET_PEDID.COD_PROG%TYPE;

    vsCodNivelExiAnterior       LET_CORPO_LIDER_NIVEL.COD_NIVE%TYPE;
    vsCodNivelExitoNuevo      LET_CORPO_PARAM_RANGO_NIVEL.COD_NIVE%TYPE;
    vsNumCampNivel              LET_CORPO_PARAM_PROGR.NUM_CAMP_MANT_NIVE_EXIT%TYPE;
    vsCodClasifLider            LET_CORPO_LIDER_CLASI.COD_CLAS_LIDE%TYPE;
    vsCodNivelExitoActual       LET_CORPO_LIDER_NIVEL.COD_NIVE%TYPE;
    vsCodNivelObjet           LET_CORPO_LIDER_NIVEL.COD_NIVE%TYPE;
    vsNivelLid                LET_CORPO_LIDER_NIVEL.COD_NIVE%TYPE;
    vnNumPedSecc                NUMBER;
    vnTotPedi                 NUMBER;
    vsRegCLN                    NUMBER;
    vsCodNivelExitPedido        LET_CORPO_PARAM_RANGO_NIVEL.COD_NIVE%TYPE;
    vsCodPeriodoAnterior        LET_CORPO_LIDER_NIVEL.CAM_INIC%TYPE;
    vnDifCampAux                NUMBER;
    vnNumCampLiderCons          NUMBER;
    vnNumCampMismoNivel         NUMBER;
    vnNumCampTrans            NUMBER;
    vsMaxCamp                 VARCHAR2 (6);
    vsCampInicPromPed         VARCHAR2 (6);
    vsCampAux                 VARCHAR2 (6);
    vsCampFIn                 VARCHAR2 (6);
    vsCampInicNueva           VARCHAR2 (6);
    nPesoLid                  NUMBER;
    vsInicTramoAnter          VARCHAR2 (6);
    vnTipoEvalAnter           NUMBER;
    vsCampInicioEsc2          VARCHAR2 (6);
    vsOidCampSecci            NUMBER;
    vsCampaSecci              VARCHAR2 (6);
    vnNumCampSecci            NUMBER;
    vnCampPromedio            NUMBER;
    vnPromPedSecc             NUMBER;
    n                         NUMBER;
    nHayNiv                   NUMBER;
    nVecesMayor               NUMBER;
    nVecesMenor               NUMBER;
    nPesoNivAct               NUMBER;
    nPesoNuevoNiv             NUMBER;
    nCampNueva                NUMBER;


    BEGIN
       vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
       vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
       vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
       vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
      vsCodPeriodoAnterior :=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO (psCodigoPeriodo,
                                                 vnOidPais,
                                                 vnOidMarca,
                                                 vnOidCanal,
                                                -1);
      BEGIN
        SELECT VAL_PARA     ---- Nro campa�as para Promedio
        INTO   vnCampPromedio
        FROM   BAS_PARAM_PAIS
        WHERE   COD_SIST = 'LET'
        AND    COD_PARA = '008';
      EXCEPTION
        WHEN NO_DATA_FOUND
        THEN
           vnCampPromedio := 1;
      END;

      IF  vnCampPromedio >= 1 AND psEscenario = 2  THEN
             vsCampInicPromPed  :=  GEN_FN_PERIO_atras(psCodigoPeriodo,(vnCampPromedio-1));  -----   Campa�a Inicio para evaluar Promedio Pedidos, desde que campa�a
      END IF;

      SELECT COUNT (1)
          INTO vsRegCLN
         FROM LET_CORPO_LIDER_NIVEL
        WHERE COD_CLIE_LIDE = psCodLider AND CAM_INIC = psCodigoPeriodo;

      IF (vsRegCLN > 0)
      THEN
         DELETE FROM LET_CORPO_LIDER_NIVEL
              WHERE COD_CLIE_LIDE = psCodLider
              AND CAM_INIC = psCodigoPeriodo;
           UPDATE LET_CORPO_LIDER_NIVEL
                  SET CAM_FIN = NULL
              WHERE COD_CLIE_LIDE = psCodLider
             AND CAM_FIN = vsCodPeriodoAnterior
             AND  IND_TIPO_NIVE ='R';
         END IF;


      IF psEscenario = '1'
      THEN

         -- Obtener Secci�n Lider
         BEGIN
           SELECT COD_REGI, COD_ZONA, COD_SECC
             INTO vsCodRegion, vsCodZona, vsCodSeccion
             FROM ZON_HISTO_GEREN
            WHERE GERE = psCodLider
              AND COD_SECC IS NOT NULL
              AND PERD_OID_PERI_DESD <= vnOidPeriodo
                   AND (PERD_OID_PERI_HAST IS NULL
                        OR vnOidPeriodo <= PERD_OID_PERI_HAST);
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
             vsCodSeccion := NULL;
         END;

         IF vsCodSeccion IS NOT NULL
         THEN
            -- Obtener Pedido Objetivo
            BEGIN
              SELECT NUM_PEDI_OBJE, COD_PROG
                INTO vnNumPedObjet, vsCodProg
                FROM LET_CORPO_SECCI_OBJET_PEDID
               WHERE COD_REGI = vsCodRegion
                 AND COD_ZONA = vsCodZona
                 AND COD_SECC = vsCodSeccion
                 AND CAM_OBJE = psCodigoPeriodo
                 AND IND_ACTI = 1;
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                vnNumPedObjet := 0;
            END;

            --***Obtener Nivel de Exito ANTERIOR(modificacion)

            BEGIN
              SELECT COD_NIVE
                 INTO vsCodNivelExiAnterior
                FROM LET_CORPO_LIDER_NIVEL
               WHERE COD_CLIE_LIDE = psCodLider
                   AND CAM_INIC <= vsCodPeriodoAnterior
                   AND (CAM_FIN IS NULL OR CAM_FIN >= vsCodPeriodoAnterior)
                   AND IND_TIPO_NIVE = 'R';
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                vsCodNivelExiAnterior := NULL;
            END;

            IF vnNumPedObjet = 0
            THEN                             -- Si el Pedido Objetivo es vacio
               IF (vsCodNivelExiAnterior IS NOT NULL)
               THEN
                  vsCodNivelExitoNuevo := vsCodNivelExiAnterior;
            ELSE
              BEGIN
                    SELECT COD_NIVE
                       INTO vsCodNivelExitoNuevo
                    FROM LET_CORPO_NIVEL_EXITO
                       WHERE VAL_PESO_NIVE = 1;
                  EXCEPTION
                     WHEN NO_DATA_FOUND
                     THEN
                        vsCodNivelExitoNuevo := NULL;
                  END;
               END IF;
            ELSE-- Si tiene Pedido Objetivo
              BEGIN -- Obtener Nivel de Exito Nuevo
                SELECT COD_NIVE
                    INTO vsCodNivelExitoNuevo
                FROM LET_CORPO_PARAM_RANGO_NIVEL
               WHERE COD_PROG = vsCodProg
                 AND NUM_PEDI_INIC <= vnNumPedObjet
                     AND NUM_PEDI_FINA >= vnNumPedObjet
                     AND IND_ACTI = 1;
              EXCEPTION
                  WHEN NO_DATA_FOUND
                  THEN
                     vsCodNivelExitoNuevo := NULL;
              END;

                --De no encontrarse registro.
               IF (vsCodNivelExitoNuevo IS NULL)
               THEN
                     BEGIN -- Obtener Nivel de Exito Nuevo
                     SELECT COD_NIVE
                       INTO vsCodNivelExitoNuevo
                       FROM LET_CORPO_NIVEL_EXITO
                            WHERE VAL_PESO_NIVE = 0;
                     EXCEPTION
                     WHEN NO_DATA_FOUND
                     THEN
                        vsCodNivelExitoNuevo := NULL;
                     END;
                END IF;
            END IF;

            --VALIDAMOS SI LOS NIVELES SON DIFERENTES
            IF (vsCodNivelExitoNuevo IS NOT NULL)
            THEN
               IF (vsCodNivelExiAnterior IS NOT NULL)
               THEN
                  IF ( (vsCodNivelExitoNuevo <> vsCodNivelExiAnterior))
                  THEN           -- SI SON DIFERENTES Y EL ANTERIOR NO ES NULO
                          --Cerrar el nivel de exito anterior
                     UPDATE LET_CORPO_LIDER_NIVEL
                           SET CAM_FIN =  vsCodPeriodoAnterior,
                            USU_MODI = psUsuario,
                            FEC_MODI = SYSDATE
                      WHERE COD_CLIE_LIDE = psCodLider
                               AND COD_NIVE = vsCodNivelExiAnterior
                               AND CAM_FIN IS NULL;

                         -- Registro Nivel Exito Real
                          INSERT INTO LET_CORPO_LIDER_NIVEL (COD_CLIE_LIDE,
                                                             COD_NIVE,
                                                             CAM_INIC,
                                                             IND_TIPO_NIVE,
                                                             CAM_FIN,
                                                             IND_ACTI,
                                                             USU_CREA,
                                                             USU_MODI,
                                                             FEC_CREA,
                                                             FEC_MODI)
                               VALUES (psCodLider,
                                  vsCodNivelExitoNuevo,
                                       psCodigoPeriodo,
                                       'R',
                                       NULL,
                                       '1',
                                       psUsuario,
                                       NULL,
                                       SYSDATE,
                                       NULL);
                   END IF;
               ELSE
                    -- Registro Nivel Exito Real
                  INSERT INTO LET_CORPO_LIDER_NIVEL (COD_CLIE_LIDE,
                                                     COD_NIVE,
                                                     CAM_INIC,
                                                     IND_TIPO_NIVE,
                                                     CAM_FIN,
                                                     IND_ACTI,
                                                     USU_CREA,
                                                     USU_MODI,
                                                     FEC_CREA,
                                                     FEC_MODI)
                       VALUES (psCodLider,
                               vsCodNivelExitoNuevo,
                               psCodigoPeriodo,
                               'R',
                               NULL,
                               '1',
                               psUsuario,
                               NULL,
                               SYSDATE,
                               NULL);
                  END IF;

                    -- Registro de Nivel de Exito Proyectado
               INSERT INTO LET_CORPO_LIDER_NIVEL (COD_CLIE_LIDE,
                                                  COD_NIVE,
                                                  CAM_INIC,
                                                  IND_TIPO_NIVE,
                                                  CAM_FIN,
                                                  IND_ACTI,
                                                  USU_CREA,
                                                  USU_MODI,
                                                  FEC_CREA,
                                                  FEC_MODI)
                    VALUES (psCodLider,
                            vsCodNivelExitoNuevo,
                            psCodigoPeriodo,
                            'P',
                            psCodigoPeriodo,
                            '1',
                            psUsuario,
                            NULL,
                            SYSDATE,
                            NULL);
            END IF;
            ---*----**------------****--------------

         END IF;
      END IF;

      -- Escenario: 2
      IF psEscenario = '2'
      THEN
         -- Parametro Numero Campa�a Cambia Nivel
         BEGIN
         SELECT COD_PROG, NUM_CAMP_MANT_NIVE_EXIT
           INTO vsCodProg, vsNumCampNivel
               FROM LET_CORPO_PARAM_TRAMO
          WHERE CAM_INIC <= psCodigoPeriodo
                    AND ( CAM_FIN >= psCodigoPeriodo);
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               vsNumCampNivel := 0;
         END;

         vsCampAux  :=  GEN_FN_PERIO_atras(psCodigoPeriodo,(vsNumCampNivel-1));  -----   Campa�a Inicio para evaluar cambio de nivel

         ------  Si la campa�a obtenida es Escenario 2, el cambio de nivel , se evalua desde campa�a obtenida, si es Escenario 1, no se eval�ua cambio Nivel
        BEGIN
             SELECT tip_eval_nive_exit , CAM_INIC
             INTO vnTipoEvalAnter, vsInicTramoAnter
             FROM  LET_CORPO_PARAM_TRAMO
             WHERE CAM_INIC <= vsCampAux
             AND  CAM_FIN >= vsCampAux;
        EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               vnTipoEvalAnter := 1;
        END;

        IF  vnTipoEvalAnter = 1 THEN
               vsCampInicioEsc2 := psCodigoPeriodo;
        ELSE
               vsCampInicioEsc2 :=   vsCampAux ;
        END IF;

              -- Obtengo campa�as transcurridas  con Escenario 2
         vnNumCampTrans := gen_pkg_gener.GEN_FN_DEVUE_DIFER_PERIO(vsCampInicioEsc2,psCodigoPeriodo) + 1;

         ----   Obtengo nivel en campa�a de Proceso
         BEGIN
            SELECT NL.COD_NIVE, NE.VAL_PESO_NIVE
           INTO vsCodNivelExitoActual, nPesoNivAct
           FROM LET_CORPO_LIDER_NIVEL NL, SSICC_COMUN.LET_CORPO_NIVEL_EXITO ne
           WHERE nl.COD_CLIE_LIDE = psCodLider AND psCodigoPeriodo >= nl.CAM_INIC
           AND ( psCodigoPeriodo <= nl.CAM_FIN or nl.CAM_FIN is NULL )
           AND nl.IND_TIPO_NIVE = 'R'
           AND NL.COD_NIVE = NE.COD_NIVE ;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               vsCodNivelExitoActual := NULL;
         END;

         IF  vsNumCampNivel > 0 THEN
         -- Clasificacion Lider
             BEGIN
                 SELECT COD_CLAS_LIDE, CAM_INIC
                   INTO vsCodClasifLider, vsCampInicNueva
           FROM LET_CORPO_LIDER_CLASI
          WHERE COD_CLIE_LIDE = psCodLider
            AND CAM_INIC <= psCodigoPeriodo
                        AND ( CAM_FIN >= psCodigoPeriodo OR  CAM_FIN is null);
         EXCEPTION
                WHEN NO_DATA_FOUND
                THEN
                   vsCodClasifLider := NULL;
                   vsCampInicNueva := NULL;
         END;

         -- Seccion Lider
         BEGIN
           SELECT COD_REGI, COD_ZONA, COD_SECC
             INTO vsCodRegion, vsCodZona, vsCodSeccion
             FROM ZON_HISTO_GEREN
            WHERE GERE = psCodLider
              AND COD_SECC IS NOT NULL
              AND PERD_OID_PERI_DESD <= vnOidPeriodo
                       AND (PERD_OID_PERI_HAST IS NULL
                            OR vnOidPeriodo <= PERD_OID_PERI_HAST);
         EXCEPTION
                WHEN NO_DATA_FOUND
                THEN
             vsCodSeccion := NULL;
         END;

             IF vsCodSeccion IS NOT NULL AND vsCodClasifLider IS NOT NULL
             THEN
                   -- Contabiliza Pedidos Seccion
                   SELECT SUM(IFVRV.NUM_PEDI)
                     INTO vnNumPedSecc
                     FROM INT_FUENT_VENTA_REAL_VACUM IFVRV,
                          ZON_TERRI_ADMIN            ZTA,
                          ZON_SECCI                  ZS,
                          ZON_ZONA                   ZZ,
                          ZON_REGIO                  ZR
                 WHERE     IFVRV.TERR_OID_TERR = ZTA.TERR_OID_TERR
                 AND (vnOidPeriodo >= ZTA.PERD_OID_PERI_INIC )
                 AND (vnOidPeriodo <= ZTA.PERD_OID_PERI_FINA or ZTA.PERD_OID_PERI_FINa  is null)
                      AND ZTA.ZSCC_OID_SECC = ZS.OID_SECC
                      AND ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
                      AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
                      AND IFVRV.PERD_OID_PERI = vnOidPeriodo
                      AND ZR.COD_REGI = vsCodRegion
                      AND ZZ.COD_ZONA = vsCodZona
                      AND ZS.COD_SECC = vsCodSeccion;
                  -- Obtengo Nivel Exito seg�n cantidad de  Pedidos
                  BEGIN
                   SELECT COD_NIVE
                     INTO vsCodNivelExitPedido
                     FROM LET_CORPO_PARAM_RANGO_NIVEL
                    WHERE COD_PROG = vsCodProg
                      AND NUM_PEDI_INIC <= vnNumPedSecc
                             AND NUM_PEDI_FINA >= vnNumPedSecc
                             AND IND_ACTI = 1;
                  EXCEPTION
                         WHEN NO_DATA_FOUND
                         THEN
                         vsCodNivelExitPedido := '00';
                   END;

                   -- Registro de Nivel de Exito Proyectado
                  INSERT INTO LET_CORPO_LIDER_NIVEL (COD_CLIE_LIDE,
                        COD_NIVE,
                        CAM_INIC,
                        IND_TIPO_NIVE,
                        CAM_FIN,
                        IND_ACTI,
                        USU_CREA,
                        USU_MODI,
                        FEC_CREA,
                        FEC_MODI)
                       VALUES (psCodLider,
                        vsCodNivelExitPedido,
                        psCodigoPeriodo,
                        'P',
                        psCodigoPeriodo,
                        '1',
                        psUsuario,
                        NULL,
                        SYSDATE,
                        NULL);

                 vsCodNivelObjet := NULL;
                  BEGIN
                         SELECT NUM_PEDI_OBJE, COD_PROG
                         INTO vnNumPedObjet, vsCodProg
                         FROM LET_CORPO_SECCI_OBJET_PEDID
                         WHERE     COD_REGI = vsCodRegion
                         AND COD_ZONA = vsCodZona
                         AND COD_SECC = vsCodSeccion
                        AND CAM_OBJE = psCodigoPeriodo
                         AND IND_ACTI = 1;
                  EXCEPTION
                         WHEN NO_DATA_FOUND
                         THEN
                            vnNumPedObjet := 0;
                  END;
                     ----   Si no hay Objetivos
                 IF  vnNumPedObjet  > 0
                 THEN
                       BEGIN                           -- Obtener Nivel de Exito Nuevo  de acuerdo a Objetivo
                  SELECT COD_NIVE
                               INTO    vsCodNivelObjet
                     FROM LET_CORPO_PARAM_RANGO_NIVEL
                    WHERE COD_PROG = vsCodProg
                               AND    NUM_PEDI_INIC <= vnNumPedObjet
                               AND    NUM_PEDI_FINA >= vnNumPedObjet
                               AND    IND_ACTI = 1;
                       EXCEPTION
                                  WHEN NO_DATA_FOUND
                                  THEN
                                     vsCodNivelObjet := NULL;
                       END;
                 END IF;

                 IF   vsCodNivelObjet is NULL THEN
                     BEGIN
                         SELECT COD_NIVE
                         INTO vsCodNivelObjet
                         FROM LET_CORPO_NIVEL_EXITO
                         WHERE VAL_PESO_NIVE = 1;
                     EXCEPTION
                          WHEN NO_DATA_FOUND
                          THEN
                                vsCodNivelObjet := NULL;
                     END;
                 END IF;

                IF   vsCodNivelExitoActual is NULL THEN -- Si no tiene Nivel Real para campa�a, se le crea en base a Objetivos
                  INSERT INTO LET_CORPO_LIDER_NIVEL (COD_CLIE_LIDE,
                        COD_NIVE,
                        CAM_INIC,
                        IND_TIPO_NIVE,
                        CAM_FIN,
                        IND_ACTI,
                        USU_CREA,
                        USU_MODI,
                        FEC_CREA,
                        FEC_MODI)
                       VALUES (psCodLider,
                               vsCodNivelObjet,
                        psCodigoPeriodo,
                               'R',
                               NULL,
                        '1',
                        psUsuario,
                        NULL,
                        SYSDATE,
                        NULL);
                END IF;
                     ------    Si no es Cierre campa�a �  ( Es cierre Cmpa�a y ( estamos en escenario 2 hace menos de las campa�as para cambiar nivel � la secci�n se cre� hace menos  de las campa�as para cambiar nivel )
                IF  psTipoProceso in ( 'C' , 'R')  THEN
                        vsCodNivelExitoNuevo := NULL;
                        -----   Si es Nueva o reingresada  --
                         IF (vsCodClasifLider = '01' OR vsCodClasifLider = '02')  THEN
                               nCampNueva:= gen_pkg_gener.GEN_FN_DEVUE_DIFER_PERIO(vsCampInicNueva,psCodigoPeriodo) + 1;
                               vnNumCampTrans := gen_pkg_gener.GEN_FN_DEVUE_DIFER_PERIO(vsCampInicNueva,psCodigoPeriodo) + 1;
             END IF;

                         IF   nCampNueva = 1 THEN  --- Solo secciones nUevas o reingresada ( por campa�a
                         ----  Obtengo campa�a Creaci�n secci�n
                            SELECT ZS.PERD_OID_PERI_INIC
                            INTO vsOidCampSecci
                            FROM
                          ZON_SECCI                  ZS,
                          ZON_ZONA                   ZZ,
                          ZON_REGIO                  ZR
                            WHERE   ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
                      AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
                            AND ZS.IND_ACTI = 1
                      AND ZR.COD_REGI = vsCodRegion
                      AND ZZ.COD_ZONA = vsCodZona
                      AND ZS.COD_SECC = vsCodSeccion;

                            vsCampaSecci := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio3(vsOidCampSecci);
                           ----  Obtengo hace cuantas campa�as se cre� la secci�n
                           vnNumCampSecci  := gen_pkg_gener.GEN_FN_DEVUE_DIFER_PERIO(vsCampaSecci,psCodigoPeriodo) + 1;

                             IF   vnNumCampSecci < vnCampPromedio THEN   --- Si la seccion es Nueva, el nivel es en base a Objetivos , de lo contraio en base a Promedio de Pedidos  ----
                                   vsCodNivelExitoNuevo := vsCodNivelObjet;
                ELSE
                                  ----  Promedio de Pedido   ---
                               -----
                               vsCampAux := vsCampInicPromPed;
                               vnTotPedi := 0;
                               n := 0;
                                 BEGIN
                                     LOOP
                                           vnOidCampAux := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2 (vsCampAux);
                                           SELECT SUM (nvl(IFVRV.NUM_PEDI,0))
                     INTO vnNumPedSecc
                     FROM INT_FUENT_VENTA_REAL_VACUM IFVRV,
                          ZON_TERRI_ADMIN            ZTA,
                          ZON_SECCI                  ZS,
                          ZON_ZONA                   ZZ,
                          ZON_REGIO                  ZR
                                            WHERE     IFVRV.TERR_OID_TERR = ZTA.TERR_OID_TERR
                                              AND (vnOidCampAux >= ZTA.PERD_OID_PERI_INIC )
                                              AND (vnOidCampAux <= ZTA.PERD_OID_PERI_FINA or ZTA.PERD_OID_PERI_FINa  is null)
                      AND ZTA.ZSCC_OID_SECC = ZS.OID_SECC
                      AND ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
                      AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
                                              AND IFVRV.PERD_OID_PERI = vnOidCampAux
                      AND ZR.COD_REGI = vsCodRegion
                      AND ZZ.COD_ZONA = vsCodZona
                      AND ZS.COD_SECC = vsCodSeccion;
                                       vnTotPedi := vnTotPedi + vnNumPedSecc;
                                      exit when vsCampAux = psCodigoPeriodo;
                                       n := n + 1;
                                       vsCampAux :=  gen_pkg_gener.gen_fn_perio_nsigu (psCodigoPais,vsCampInicPromPed,n);
                                      end loop;
                                 END;
                                 vnPromPedSecc := ROUND( vnTotPedi / vnCampPromedio,0);

                                  BEGIN                           -- Obtener Nivel de Exito Nuevo  de acuerdo a Promedio Pedidos
                   SELECT COD_NIVE
                                     INTO    vsCodNivelExitoNuevo
                     FROM LET_CORPO_PARAM_RANGO_NIVEL
                    WHERE COD_PROG = vsCodProg
                                     AND    NUM_PEDI_INIC <= vnPromPedSecc
                                     AND    NUM_PEDI_FINA >= vnPromPedSecc
                                     AND    IND_ACTI = 1;
                                 EXCEPTION
                                      WHEN NO_DATA_FOUND
                                      THEN
                                         vsCodNivelExitoNuevo := NULL;
                                 END;
                             END IF;
                         ELSE        --------   No son Nuevas ni Reingresos
                             IF    vnNumCampTrans  <  vsNumCampNivel    THEN      -------  Si aun no transcurren las campa�as para cambiar de Nivel
                                    -----  Si no tiene Nivel Actual , su nivel es en base a Objetivos
                                    IF   vsCodNivelExitoActual  is  NULL  THEN
                                          vsCodNivelExitoNuevo := vsCodNivelObjet;
                                    END IF;
                             ELSE   -------  Se evalua si cambia de nivel
                                vsCampAux := vsCampInicioEsc2;    --- x evaluar
                                nVecesMayor := 0;
                                nVecesMenor := 0;
                                n := 0;
                                 BEGIN
                                     LOOP
                                         BEGIN
                                               SELECT   ln.COD_NIVE,  VAL_PESO_NIVE
                                               INTO  vsNivelLid, nPesoLid
                                               FROM  LET_CORPO_LIDER_NIVEL  LN, ssicc_comun.LET_CORPO_NIVEL_EXITO NE
                                               WHERE LN.COD_CLIE_LIDE = psCodLider
                                               AND IND_TIPO_NIVE = 'P'
                                               AND  LN.COD_NIVE = NE.COD_NIVE
                                               AND  vsCampAux  >= CAM_INIC
                                               AND  vsCampAux <=  CAM_FIN  ;
                                           EXCEPTION
                                               WHEN NO_DATA_FOUND
                                              THEN
                                              vsNivelLid  := 1;
                                              nPesoLid := 1;
                                           END;
                                           IF    nPesoLid  >  nPesoNivAct THEN
                                                 nVecesMayor := nVecesMayor + 1;
                                                 IF   nVecesMayor = 1 THEN
                                                       nPesoNuevoNiv := nPesoLid;
                                                 END IF;
                                                 IF   nPesoLid < nPesoNuevoNiv THEN
                                                       nPesoNuevoNiv := nPesoLid;
                                                 END IF;
                                           ELSE
                                                IF   nPesoLid  <  nPesoNivAct THEN
                                                     nVecesMenor := nVecesMenor + 1;
                                                     IF   nVecesMenor = 1 THEN
                                                        nPesoNuevoNiv := nPesoLid;
                                                     END IF;
                                                     IF   nPesoLid  >  nPesoNuevoNiv THEN
                                                           nPesoNuevoNiv := nPesoLid;
                                                     END IF;
                                               END IF;
                                           END IF;
                                      exit when vsCampAux = psCodigoPeriodo;
                                       n := n + 1;
                                       vsCampAux :=  gen_pkg_gener.gen_fn_perio_nsigu (psCodigoPais,vsCampInicioEsc2,n);
                                      end loop;
                                 END;
                                 IF nVecesMayor >= vsNumCampNivel THEN
                                    SELECT NE.COD_NIVE
                                    INTO  vsCodNivelExitoNuevo
                                    FROM  LET_CORPO_NIVEL_EXITO NE
                                    WHERE NE.VAL_PESO_NIVE = nPesoNuevoNiv;
                                 END IF;
                                 IF nVecesMenor >= vsNumCampNivel THEN
                                    SELECT NE.COD_NIVE
                                    INTO  vsCodNivelExitoNuevo
                                    FROM  LET_CORPO_NIVEL_EXITO NE
                                    WHERE NE.VAL_PESO_NIVE = nPesoNuevoNiv;
                                 END IF;
                             END IF;

                         END IF;
                        ---------------------------
                         IF (vsCodNivelExitoNuevo IS NOT NULL)
                            THEN
                               IF (vsCodNivelExitoActual IS NOT NULL)
                               THEN
                                  IF ( (vsCodNivelExitoNuevo <> vsCodNivelExitoActual))
                                  THEN           -- SI SON DIFERENTES Y EL ANTERIOR NO ES NULO
                                     --Cerrar el nivel de exito anterior
                                     UPDATE LET_CORPO_LIDER_NIVEL
                                        SET CAM_FIN = vsCodPeriodoAnterior,
                                            USU_MODI = psUsuario,
                                            FEC_MODI = SYSDATE
                   WHERE COD_CLIE_LIDE = psCodLider
                                      AND   CAM_FIN IS NULL;

                                     -- Registro Nivel Exito Real
                                     INSERT INTO LET_CORPO_LIDER_NIVEL (COD_CLIE_LIDE,
                          COD_NIVE,
                          CAM_INIC,
                          IND_TIPO_NIVE,
                          CAM_FIN,
                          IND_ACTI,
                          USU_CREA,
                          USU_MODI,
                          FEC_CREA,
                          FEC_MODI)
                                          VALUES (psCodLider,
                                                  vsCodNivelExitoNuevo,
                          psCodigoPeriodo,
                                                  'R',
                                                  NULL,
                          '1',
                          psUsuario,
                          NULL,
                          SYSDATE,
                          NULL);
                                  END IF;
                   ELSE
                                  -- Registro Nivel Exito Real
                     DELETE FROM LET_CORPO_LIDER_NIVEL
                      WHERE COD_CLIE_LIDE = psCodLider
                        AND CAM_INIC = psCodigoPeriodo
                                AND IND_TIPO_NIVE = 'R';
                                  INSERT INTO LET_CORPO_LIDER_NIVEL (COD_CLIE_LIDE,
                                COD_NIVE,
                                CAM_INIC,
                                IND_TIPO_NIVE,
                                CAM_FIN,
                                IND_ACTI,
                                USU_CREA,
                                USU_MODI,
                                FEC_CREA,
                                FEC_MODI)
                                       VALUES (psCodLider,
                                               vsCodNivelExitoNuevo,
                                psCodigoPeriodo,
                                'R',
                                NULL,
                                '1',
                                psUsuario,
                                NULL,
                                SYSDATE,
                                NULL);
                         END IF;

                        END IF;   --------vsCodNivelExitoNuevo   not null
                 END IF; ---   Tipo Proceso  C,R
                      END IF;
                   END IF;
      END IF;

    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR LET_PR_PROCE_NIVEL_LIDER: ' ||
                                ls_sqlerrm);
    END LET_PR_PROCE_NIVEL_LIDER;

  /**********************************************************************************
  Descripcion       : Proceso de Calculo de Nivel de Exito Masivo
  Fecha Creacion    : 25/06/2013
  Autor             : Aurelio Oviedo
  **********************************************************************************/
  PROCEDURE LET_PR_CALCU_NIVEL_EXITO(psCodigoPais VARCHAR2,
                                     psCodigoRegion VARCHAR2,
                                     psCodigoMarca VARCHAR2,
                                     psCodigoCanal VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psTipoProceso VARCHAR2,
                                     psUsuario VARCHAR2)IS

    --Variables Globales
    vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;

    CURSOR c_liderActivoProcesoRegion(oidPeriodo NUMBER, codigoRegion VARCHAR2) IS
        SELECT GERE, COD_REGI
          FROM ZON_HISTO_GEREN
         WHERE oidPeriodo >= PERD_OID_PERI_DESD
           AND (oidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
           AND COD_SECC IS NOT NULL
           AND (codigoRegion IS NULL OR COD_REGI = codigoRegion);

    vsCodGerente    ZON_HISTO_GEREN.GERE%TYPE;
    vsCodRegion     ZON_HISTO_GEREN.COD_REGI%TYPE;

    vnCodigoPrograma                LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
    vnParametroEscenario            LET_CORPO_PARAM_PROGR.TIP_EVAL_NIVE_EXIT%TYPE;
    vnPeriodoInicioEvaluacion       LET_CORPO_PARAM_TRAMO.CAM_INIC%TYPE;

    --Parametro para el Cursor
    pcCodigoRegion                  ZON_REGIO.COD_REGI%TYPE;

  BEGIN

    vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

    --Obtener Codigo Programa y Parametro Escenario
    BEGIN
        SELECT PP.COD_PROG
          INTO vnCodigoPrograma
          FROM LET_CORPO_PARAM_PROGR PP
          WHERE psCodigoPeriodo >= PP.CAM_INIC
          AND (psCodigoPeriodo <= PP.CAM_FIN OR PP.CAM_FIN IS NULL)
          AND PP.IND_ACTI = 1;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            vnCodigoPrograma := NULL;
            vnParametroEscenario := NULL;
    END;

    IF vnCodigoPrograma IS NOT NULL THEN
        --Obtener Campa�a Inicio Evaluacion
        SELECT CAM_INIC, TIP_EVAL_NIVE_EXIT
          INTO vnPeriodoInicioEvaluacion, vnParametroEscenario
                  FROM LET_CORPO_PARAM_TRAMO
                 WHERE COD_PROG = vnCodigoPrograma
           AND CAM_INIC <= psCodigoPeriodo
           AND ( CAM_FIN >= psCodigoPeriodo);

        IF psTipoProceso = 'R' THEN
            pcCodigoRegion := psCodigoRegion;
        ELSE
            pcCodigoRegion := NULL;
        END IF;

        OPEN c_liderActivoProcesoRegion(vnOidPeriodo, pcCodigoRegion);
        LOOP FETCH c_liderActivoProcesoRegion INTO vsCodGerente, vsCodRegion;

            EXIT WHEN c_liderActivoProcesoRegion%NOTFOUND;

            IF vnParametroEscenario = 1 THEN
                LET_PKG_PROCE.LET_PR_PROCE_NIVEL_LIDER(psCodigoPais,
                                                       vsCodGerente,
                                                       psCodigoMarca,
                                                       psCodigoCanal,
                                                       psCodigoPeriodo,
                                                       psTipoProceso,
                                                       '1',
                                                       psUsuario);
            ELSE
                LET_PKG_PROCE.LET_PR_PROCE_NIVEL_LIDER(psCodigoPais,
                                                       vsCodGerente,
                                                       psCodigoMarca,
                                                       psCodigoCanal,
                                                       psCodigoPeriodo,
                                                       psTipoProceso,
                                                       vnParametroEscenario,
                                                       psUsuario);
            END IF;

        END LOOP;
        CLOSE c_liderActivoProcesoRegion;
    END IF;

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_NIVEL_EXITO: ' || ls_sqlerrm);
  END LET_PR_CALCU_NIVEL_EXITO;

  /**********************************************************************************
  Descripcion       : Proceso de Calculo de Ganancia Lider
  Fecha Creacion    : 27/06/2013
  Autor             : Aurelio Oviedo
  **********************************************************************************/
  PROCEDURE LET_PR_CALCU_GANAN_LIDER(psCodigoPais VARCHAR2,
                                     psCodigoMarca VARCHAR2,
                                     psCodigoCanal VARCHAR2,
                                     psCodigoLider VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psTipoProceso VARCHAR2,
                                     psUsuario VARCHAR2)IS

    --Variables Globales
    vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;

    vnGananciaPedidoLider           NUMBER;
    vnGananciaNuevaPedidoLider      NUMBER;
    vnCodRegionLider                ZON_HISTO_GEREN.COD_REGI%TYPE;
    vnCodZonaLider                  ZON_HISTO_GEREN.COD_ZONA%TYPE;
    vnCodSeccionLider               ZON_HISTO_GEREN.COD_SECC%TYPE;
    vnNivelExitoLider               LET_CORPO_LIDER_NIVEL.COD_NIVE%TYPE;
    vnCodigoPrograma                LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
    vnMontoDsctoTolerancia          LET_CORPO_PARAM_PROGR.VAL_MONT_GANA_TOLE%TYPE;
    vnIndActGananciaLiderNueva      LET_CORPO_PARAM_PROGR.IND_ACTI_GANA_LIDE_NUEV%TYPE;
    vnNumCampGraciaLiderNueva       LET_CORPO_PARAM_PROGR.NUM_CAMP_GRAC_LIDE_NUEV%TYPE;
    vnCodEstadoObjPedido            LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_PEDI%TYPE;
    vnNumPedidoSobrecump            LET_CORPO_SECCI_RESUL.NUM_PEDI_SOBR%TYPE;
    vnNumPedidoTolerancia           LET_CORPO_SECCI_RESUL.NUM_PEDI_TOLE%TYPE;
    vnCodEstadoObjIngresoEfec       LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_INGR%TYPE;
    vnCodEstadoObjRetencion33       LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_R33%TYPE;
    vnCodEstadoObjRetencion44       LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_R44%TYPE;
    vnNumIngresoReal                LET_CORPO_SECCI_RESUL.NUM_INGR_REAL%TYPE;
    vnNumIngresoReal33              LET_CORPO_SECCI_RESUL.NUM_INGR_33_REAL%TYPE;
    vnNumIngresoReal44              LET_CORPO_SECCI_RESUL.NUM_INGR_44_REAL%TYPE;
    vnMontoGananciaNivelExito       LET_CORPO_PARAM_RANGO_NIVEL.VAL_MONT_GANA%TYPE;
    vnNumPedidoToleranciaNivel      LET_CORPO_PARAM_RANGO_NIVEL.NUM_PEDI_TOLE%TYPE;
    vnMontoGananciaSobrecump        LET_CORPO_PARAM_RANGO_NIVEL.VAL_MONT_GANA_SOBR%TYPE;
    vnMontoGananciaSobrecumpLider   LET_CORPO_PARAM_RANGO_NIVEL.VAL_MONT_GANA_SOBR%TYPE;
    vnMontoDsctoToleranciaLider     LET_CORPO_PARAM_PROGR.VAL_MONT_GANA_TOLE%TYPE;

    vnValorMontoGananciaLider       LET_CORPO_LIDER_GANAN.VAL_MONT_GANA%TYPE;
    vnValorMontoGananciaLider2      LET_CORPO_LIDER_GANAN.VAL_MONT_GANA%TYPE;
    vnValorMontoGananciaLider3      LET_CORPO_LIDER_GANAN.VAL_MONT_GANA%TYPE;
    vnValorMontoGananciaLider6      LET_CORPO_LIDER_GANAN.VAL_MONT_GANA%TYPE;
    vnValorMontoGananciaLider8      LET_CORPO_LIDER_GANAN.VAL_MONT_GANA%TYPE;
    vnValorMontoGananciaLider9      LET_CORPO_LIDER_GANAN.VAL_MONT_GANA%TYPE;
    vnValorMontoGananciaLider10     LET_CORPO_LIDER_GANAN.VAL_MONT_GANA%TYPE;
    vnValorMontoGananciaLider11     LET_CORPO_LIDER_GANAN.VAL_MONT_GANA%TYPE;

    vnCodClasiLider                 LET_CORPO_LIDER_CLASI.COD_CLAS_LIDE%TYPE;
    vnCamInicioClasiLider           LET_CORPO_LIDER_CLASI.CAM_INIC%TYPE;
    vnNumCampComoNueva              SEG_PERIO_CORPO.COD_PERI%TYPE;

    vnValorMontoGanancia            LET_CORPO_RANGO_RETEN.VAL_MONT_GANA%TYPE;
    vnValorMontoGanancia33          LET_CORPO_RANGO_RETEN.VAL_MONT_GANA_33%TYPE;
    vnValorMontoGanancia44          LET_CORPO_RANGO_RETEN.VAL_MONT_GANA_44%TYPE;
    vnGananciaIngrEfec              NUMBER(12,2);
    vnGananciaRetencion33           NUMBER(12,2);
    vnGananciaRetencion44           NUMBER(12,2);

  BEGIN

    vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

    --Obtener Ganancia Pedido Lider y Ganancia Nueva Pedido Lider, Default 0
    vnGananciaPedidoLider := 0;
    vnGananciaNuevaPedidoLider := 0;

    --Obtener Seccion Lider
    BEGIN
        SELECT hg.COD_REGI,
               hg.COD_ZONA,
               hg.COD_SECC
          INTO vnCodRegionLider,
               vnCodZonaLider,
               vnCodSeccionLider
          FROM ZON_HISTO_GEREN hg
         WHERE hg.GERE = psCodigoLider
           AND hg.COD_SECC IS NOT NULL
           AND vnOidPeriodo >= hg.PERD_OID_PERI_DESD
           AND (vnOidPeriodo <= hg.PERD_OID_PERI_HAST OR hg.PERD_OID_PERI_HAST IS NULL);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            vnCodRegionLider := NULL;
            vnCodZonaLider := NULL;
            vnCodSeccionLider := NULL;
    END;


    --Obtener Nivel Exito Lider
    BEGIN
        SELECT cln.COD_NIVE
          INTO vnNivelExitoLider
          FROM LET_CORPO_LIDER_NIVEL cln
         WHERE cln.COD_CLIE_LIDE = psCodigoLider
           AND psCodigoPeriodo >= cln.CAM_INIC
           AND (psCodigoPeriodo <= cln.CAM_FIN OR cln.CAM_FIN IS NULL)
           AND cln.IND_TIPO_NIVE = 'R';
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            vnNivelExitoLider := NULL;
    END;

    --Obtener Codigo Programa
    BEGIN
        SELECT pp.COD_PROG,
               pp.VAL_MONT_GANA_TOLE,
               pp.IND_ACTI_GANA_LIDE_NUEV,
               pp.NUM_CAMP_GRAC_LIDE_NUEV
          INTO vnCodigoPrograma,
               vnMontoDsctoTolerancia,
               vnIndActGananciaLiderNueva,
               vnNumCampGraciaLiderNueva
          FROM LET_CORPO_PARAM_PROGR pp
         WHERE psCodigoPeriodo >= pp.CAM_INIC
           AND (psCodigoPeriodo <= pp.CAM_FIN OR pp.CAM_FIN IS NULL)
           AND pp.IND_ACTI = 1;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            vnCodigoPrograma := NULL;
            vnMontoDsctoTolerancia := NULL;
            vnIndActGananciaLiderNueva := NULL;
            vnNumCampGraciaLiderNueva := NULL;
    END;

    IF vnCodigoPrograma IS NOT NULL AND vnNivelExitoLider IS NOT NULL THEN
        --Obtener Resultado Objetivo Lider
        BEGIN
            SELECT sr.COD_ESTA_OBJE_PEDI,
                   sr.NUM_PEDI_SOBR,
                   sr.NUM_PEDI_TOLE,
                   sr.COD_ESTA_OBJE_INGR,
                   sr.COD_ESTA_OBJE_R33,
                   sr.COD_ESTA_OBJE_R44,
                   sr.NUM_INGR_REAL,
                   sr.NUM_INGR_33_REAL,
                   sr.NUM_INGR_44_REAL
              INTO vnCodEstadoObjPedido,
                   vnNumPedidoSobrecump,
                   vnNumPedidoTolerancia,
                   vnCodEstadoObjIngresoEfec,
                   vnCodEstadoObjRetencion33,
                   vnCodEstadoObjRetencion44,
                   vnNumIngresoReal,
                   vnNumIngresoReal33,
                   vnNumIngresoReal44
              FROM LET_CORPO_SECCI_RESUL sr
             WHERE sr.COD_PAIS = psCodigoPais
               AND sr.COD_PROG = vnCodigoPrograma
               AND sr.COD_REGI = vnCodRegionLider
               AND sr.COD_ZONA = vnCodZonaLider
               AND sr.COD_SECC = vnCodSeccionLider
               AND sr.CAM_OBJE = psCodigoPeriodo;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                vnCodEstadoObjPedido := NULL;
                vnNumPedidoSobrecump := NULL;
                vnNumPedidoTolerancia := NULL;
                vnCodEstadoObjIngresoEfec := NULL;
                vnCodEstadoObjRetencion33 := NULL;
                vnCodEstadoObjRetencion44 := NULL;
                vnNumIngresoReal := NULL;
                vnNumIngresoReal33 := NULL;
                vnNumIngresoReal44 := NULL;
        END;

        --Obtener Atributos del Nivel Exito
        BEGIN
            SELECT rn.VAL_MONT_GANA,
                   rn.NUM_PEDI_TOLE,
                   rn.VAL_MONT_GANA_SOBR
          INTO vnMontoGananciaNivelExito,
               vnNumPedidoToleranciaNivel,
               vnMontoGananciaSobrecump
          FROM LET_CORPO_PARAM_RANGO_NIVEL rn
         WHERE rn.COD_PAIS = psCodigoPais
           AND rn.COD_PROG = vnCodigoPrograma
           AND rn.COD_NIVE = vnNivelExitoLider;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
            vnMontoGananciaNivelExito :=0;
            vnNumPedidoToleranciaNivel :=0;
            vnMontoGananciaSobrecump :=0;
        END;

         vnGananciaPedidoLider := 0;
        IF vnCodEstadoObjPedido IN ('01', '02', '03') THEN
            vnGananciaPedidoLider := vnGananciaPedidoLider + vnMontoGananciaNivelExito;


                BEGIN
                    SELECT lg.VAL_MONT_GANA
                      INTO vnValorMontoGananciaLider
                      FROM LET_CORPO_LIDER_GANAN lg
                     WHERE lg.COD_CLIE_LIDE = psCodigoLider
                       AND lg.CAM_GANA = psCodigoPeriodo
                       AND lg.COD_TIPO_GANA = '01';
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        vnValorMontoGananciaLider := NULL;
                END;

                IF vnValorMontoGananciaLider IS NOT NULL THEN
                    UPDATE LET_CORPO_LIDER_GANAN lg
                       SET lg.VAL_MONT_GANA = vnMontoGananciaNivelExito,
                           lg.USU_MODI = psUsuario,
                           lg.FEC_MODI = SYSDATE
                     WHERE lg.COD_CLIE_LIDE = psCodigoLider
                       AND lg.CAM_GANA = psCodigoPeriodo
                       AND lg.COD_TIPO_GANA = '01';
                ELSE
                    INSERT INTO LET_CORPO_LIDER_GANAN
                            (COD_CLIE_LIDE,
                             CAM_GANA,
                             COD_TIPO_GANA,
                             VAL_MONT_GANA,
                             USU_CREA,
                             FEC_CREA,
                             IND_ACTI)
                    VALUES  (psCodigoLider,
                             psCodigoPeriodo,
                             '01',
                             vnMontoGananciaNivelExito,
                             psUsuario,
                             SYSDATE,
                             '1');
                END IF;


            IF vnCodEstadoObjPedido = '02' THEN
                vnMontoGananciaSobrecumpLider := vnMontoGananciaSobrecump * vnNumPedidoSobrecump;

                vnGananciaPedidoLider := vnGananciaPedidoLider + vnMontoGananciaSobrecumpLider;


                    BEGIN
                        SELECT lg.VAL_MONT_GANA
                          INTO vnValorMontoGananciaLider2
                          FROM LET_CORPO_LIDER_GANAN lg
                         WHERE lg.COD_CLIE_LIDE = psCodigoLider
                           AND lg.CAM_GANA = psCodigoPeriodo
                           AND lg.COD_TIPO_GANA = '02';
                    EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            vnValorMontoGananciaLider2 := NULL;
                    END;

                    IF vnValorMontoGananciaLider2 IS NOT NULL THEN
                        UPDATE LET_CORPO_LIDER_GANAN lg
                           SET lg.VAL_MONT_GANA = vnMontoGananciaSobrecumpLider,
                               lg.USU_MODI = psUsuario,
                               lg.FEC_MODI = SYSDATE
                         WHERE lg.COD_CLIE_LIDE = psCodigoLider
                           AND lg.CAM_GANA = psCodigoPeriodo
                           AND lg.COD_TIPO_GANA = '02';
                    ELSE
                        INSERT INTO LET_CORPO_LIDER_GANAN
                                (COD_CLIE_LIDE,
                                 CAM_GANA,
                                 COD_TIPO_GANA,
                                 VAL_MONT_GANA,
                                 USU_CREA,
                                 FEC_CREA,
                                 IND_ACTI)
                        VALUES  (psCodigoLider,
                                 psCodigoPeriodo,
                                 '02',
                                 vnMontoGananciaSobrecumpLider,
                                 psUsuario,
                                 SYSDATE,
                                 '1');
                    END IF;

            END IF;

            IF vnCodEstadoObjPedido = '03' THEN
                vnMontoDsctoToleranciaLider := vnMontoDsctoTolerancia * vnNumPedidoTolerancia;

                vnGananciaPedidoLider := vnGananciaPedidoLider - vnMontoDsctoToleranciaLider;


                    BEGIN
                        SELECT lg.VAL_MONT_GANA
                          INTO vnValorMontoGananciaLider3
                          FROM LET_CORPO_LIDER_GANAN lg
                         WHERE lg.COD_CLIE_LIDE = psCodigoLider
                           AND lg.CAM_GANA = psCodigoPeriodo
                           AND lg.COD_TIPO_GANA = '03';
                    EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            vnValorMontoGananciaLider3 := NULL;
                    END;

                    IF vnValorMontoGananciaLider3 IS NOT NULL THEN
                        UPDATE LET_CORPO_LIDER_GANAN lg
                           SET lg.VAL_MONT_GANA = vnMontoDsctoToleranciaLider,
                               lg.USU_MODI = psUsuario,
                               lg.FEC_MODI = SYSDATE
                         WHERE lg.COD_CLIE_LIDE = psCodigoLider
                           AND lg.CAM_GANA = psCodigoPeriodo
                           AND lg.COD_TIPO_GANA = '03';
                    ELSE
                        INSERT INTO LET_CORPO_LIDER_GANAN
                                (COD_CLIE_LIDE,
                                 CAM_GANA,
                                 COD_TIPO_GANA,
                                 VAL_MONT_GANA,
                                 USU_CREA,
                                 FEC_CREA,
                                 IND_ACTI)
                        VALUES  (psCodigoLider,
                                 psCodigoPeriodo,
                                 '03',
                                 vnMontoDsctoToleranciaLider,
                                 psUsuario,
                                 SYSDATE,
                                 '1');
                    END IF;

            END IF;


                BEGIN
                    SELECT lg.VAL_MONT_GANA
                      INTO vnValorMontoGananciaLider8
                      FROM LET_CORPO_LIDER_GANAN lg
                     WHERE lg.COD_CLIE_LIDE = psCodigoLider
                       AND lg.CAM_GANA = psCodigoPeriodo
                       AND lg.COD_TIPO_GANA = '08';
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        vnValorMontoGananciaLider8 := NULL;
                END;

                IF vnValorMontoGananciaLider8 IS NOT NULL THEN
                    UPDATE LET_CORPO_LIDER_GANAN lg
                       SET lg.VAL_MONT_GANA = vnGananciaPedidoLider,
                           lg.USU_MODI = psUsuario,
                           lg.FEC_MODI = SYSDATE
                     WHERE lg.COD_CLIE_LIDE = psCodigoLider
                       AND lg.CAM_GANA = psCodigoPeriodo
                       AND lg.COD_TIPO_GANA = '08';
                ELSE
                    INSERT INTO LET_CORPO_LIDER_GANAN
                            (COD_CLIE_LIDE,
                             CAM_GANA,
                             COD_TIPO_GANA,
                             VAL_MONT_GANA,
                             USU_CREA,
                             FEC_CREA,
                             IND_ACTI)
                    VALUES  (psCodigoLider,
                             psCodigoPeriodo,
                             '08',
                             vnGananciaPedidoLider,
                             psUsuario,
                             SYSDATE,
                             '1');
                END IF;

        ELSE
            IF vnIndActGananciaLiderNueva = '1' THEN
                BEGIN
                    SELECT lc.COD_CLAS_LIDE, lc.CAM_INIC
                      INTO vnCodClasiLider, vnCamInicioClasiLider
                      FROM LET_CORPO_LIDER_CLASI lc
                     WHERE lc.COD_CLIE_LIDE = psCodigoLider
                       AND psCodigoPeriodo >= lc.CAM_INIC
                       AND (psCodigoPeriodo <= lc.CAM_FIN OR lc.CAM_FIN IS NULL);
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        vnCodClasiLider := NULL;
                        vnCamInicioClasiLider := NULL;
                END;

                IF vnCodClasiLider = '01' THEN
                    vnNumCampComoNueva := GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO(vnCamInicioClasiLider, psCodigoPeriodo) + 1;

                    IF vnNumCampComoNueva <= vnNumCampGraciaLiderNueva THEN
                        vnGananciaNuevaPedidoLider := vnMontoGananciaNivelExito - (vnNumPedidoToleranciaNivel * vnMontoDsctoTolerancia);

                        vnGananciaPedidoLider := vnGananciaNuevaPedidoLider;


                            BEGIN
                                SELECT lg.VAL_MONT_GANA
                                  INTO vnValorMontoGananciaLider6
                                  FROM LET_CORPO_LIDER_GANAN lg
                                 WHERE lg.COD_CLIE_LIDE = psCodigoLider
                                   AND lg.CAM_GANA = psCodigoPeriodo
                                   AND lg.COD_TIPO_GANA = '06';
                            EXCEPTION
                                WHEN NO_DATA_FOUND THEN
                                    vnValorMontoGananciaLider6 := NULL;
                            END;

                            IF vnValorMontoGananciaLider6 IS NOT NULL THEN
                                UPDATE LET_CORPO_LIDER_GANAN lg
                                   SET lg.VAL_MONT_GANA = vnGananciaNuevaPedidoLider,
                                       lg.USU_MODI = psUsuario,
                                       lg.FEC_MODI = SYSDATE
                                 WHERE lg.COD_CLIE_LIDE = psCodigoLider
                                   AND lg.CAM_GANA = psCodigoPeriodo
                                   AND lg.COD_TIPO_GANA = '06';
                            ELSE
                                INSERT INTO LET_CORPO_LIDER_GANAN
                                        (COD_CLIE_LIDE,
                                         CAM_GANA,
                                         COD_TIPO_GANA,
                                         VAL_MONT_GANA,
                                         USU_CREA,
                                         FEC_CREA,
                                         IND_ACTI)
                                VALUES  (psCodigoLider,
                                         psCodigoPeriodo,
                                         '06',
                                         vnGananciaNuevaPedidoLider,
                                         psUsuario,
                                         SYSDATE,
                                         '1');
                            END IF;

                            BEGIN
                                SELECT lg.VAL_MONT_GANA
                                  INTO vnValorMontoGananciaLider8
                                  FROM LET_CORPO_LIDER_GANAN lg
                                 WHERE lg.COD_CLIE_LIDE = psCodigoLider
                                   AND lg.CAM_GANA = psCodigoPeriodo
                                   AND lg.COD_TIPO_GANA = '08';
                            EXCEPTION
                                WHEN NO_DATA_FOUND THEN
                                    vnValorMontoGananciaLider8 := NULL;
                            END;

                            IF vnValorMontoGananciaLider8 IS NOT NULL THEN
                                UPDATE LET_CORPO_LIDER_GANAN lg
                                   SET lg.VAL_MONT_GANA = vnGananciaPedidoLider,
                                       lg.USU_MODI = psUsuario,
                                       lg.FEC_MODI = SYSDATE
                                 WHERE lg.COD_CLIE_LIDE = psCodigoLider
                                   AND lg.CAM_GANA = psCodigoPeriodo
                                   AND lg.COD_TIPO_GANA = '08';
                            ELSE
                                INSERT INTO LET_CORPO_LIDER_GANAN
                                        (COD_CLIE_LIDE,
                                         CAM_GANA,
                                         COD_TIPO_GANA,
                                         VAL_MONT_GANA,
                                         USU_CREA,
                                         FEC_CREA,
                                         IND_ACTI)
                                VALUES  (psCodigoLider,
                                         psCodigoPeriodo,
                                         '08',
                                         vnGananciaPedidoLider,
                                         psUsuario,
                                         SYSDATE,
                                         '1');
                            END IF;

                    ELSE
                        BEGIN
                            SELECT lg.VAL_MONT_GANA
                              INTO vnValorMontoGananciaLider8
                              FROM LET_CORPO_LIDER_GANAN lg
                             WHERE lg.COD_CLIE_LIDE = psCodigoLider
                               AND lg.CAM_GANA = psCodigoPeriodo
                               AND lg.COD_TIPO_GANA = '08';
                        EXCEPTION
                            WHEN NO_DATA_FOUND THEN
                                vnValorMontoGananciaLider8 := NULL;
                        END;

                        IF vnValorMontoGananciaLider8 IS NOT NULL THEN
                            UPDATE LET_CORPO_LIDER_GANAN lg
                               SET lg.VAL_MONT_GANA = vnGananciaPedidoLider,
                                   lg.USU_MODI = psUsuario,
                                   lg.FEC_MODI = SYSDATE
                             WHERE lg.COD_CLIE_LIDE = psCodigoLider
                               AND lg.CAM_GANA = psCodigoPeriodo
                               AND lg.COD_TIPO_GANA = '08';
                        ELSE
                            INSERT INTO LET_CORPO_LIDER_GANAN
                                    (COD_CLIE_LIDE,
                                     CAM_GANA,
                                     COD_TIPO_GANA,
                                     VAL_MONT_GANA,
                                     USU_CREA,
                                     FEC_CREA,
                                     IND_ACTI)
                            VALUES  (psCodigoLider,
                                     psCodigoPeriodo,
                                     '08',
                                     vnGananciaPedidoLider,
                                     psUsuario,
                                     SYSDATE,
                                     '1');
                        END IF;
                    END IF;
                ELSE
                    BEGIN
                        SELECT lg.VAL_MONT_GANA
                          INTO vnValorMontoGananciaLider8
                          FROM LET_CORPO_LIDER_GANAN lg
                         WHERE lg.COD_CLIE_LIDE = psCodigoLider
                           AND lg.CAM_GANA = psCodigoPeriodo
                           AND lg.COD_TIPO_GANA = '08';
                    EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            vnValorMontoGananciaLider8 := NULL;
                    END;

                    IF vnValorMontoGananciaLider8 IS NOT NULL THEN
                        UPDATE LET_CORPO_LIDER_GANAN lg
                           SET lg.VAL_MONT_GANA = vnGananciaPedidoLider,
                               lg.USU_MODI = psUsuario,
                               lg.FEC_MODI = SYSDATE
                         WHERE lg.COD_CLIE_LIDE = psCodigoLider
                           AND lg.CAM_GANA = psCodigoPeriodo
                           AND lg.COD_TIPO_GANA = '08';
                    ELSE
                        INSERT INTO LET_CORPO_LIDER_GANAN
                                (COD_CLIE_LIDE,
                                 CAM_GANA,
                                 COD_TIPO_GANA,
                                 VAL_MONT_GANA,
                                 USU_CREA,
                                 FEC_CREA,
                                 IND_ACTI)
                        VALUES  (psCodigoLider,
                                 psCodigoPeriodo,
                                 '08',
                                 vnGananciaPedidoLider,
                                 psUsuario,
                                 SYSDATE,
                                 '1');
                    END IF;
                    END IF;
            ELSE
                BEGIN
                    SELECT lg.VAL_MONT_GANA
                      INTO vnValorMontoGananciaLider8
                      FROM LET_CORPO_LIDER_GANAN lg
                     WHERE lg.COD_CLIE_LIDE = psCodigoLider
                       AND lg.CAM_GANA = psCodigoPeriodo
                       AND lg.COD_TIPO_GANA = '08';
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        vnValorMontoGananciaLider8 := NULL;
                END;

                IF vnValorMontoGananciaLider8 IS NOT NULL THEN
                    UPDATE LET_CORPO_LIDER_GANAN lg
                       SET lg.VAL_MONT_GANA = vnGananciaPedidoLider,
                           lg.USU_MODI = psUsuario,
                           lg.FEC_MODI = SYSDATE
                     WHERE lg.COD_CLIE_LIDE = psCodigoLider
                       AND lg.CAM_GANA = psCodigoPeriodo
                       AND lg.COD_TIPO_GANA = '08';
                ELSE
                    INSERT INTO LET_CORPO_LIDER_GANAN
                            (COD_CLIE_LIDE,
                             CAM_GANA,
                             COD_TIPO_GANA,
                             VAL_MONT_GANA,
                             USU_CREA,
                             FEC_CREA,
                             IND_ACTI)
                    VALUES  (psCodigoLider,
                             psCodigoPeriodo,
                             '08',
                             vnGananciaPedidoLider,
                             psUsuario,
                             SYSDATE,
                             '1');
                END IF;
            END IF;
        END IF;

        --Calculo de Ganancia por Retenciones
         vnGananciaIngrEfec:= 0;
         IF vnCodEstadoObjIngresoEfec = '05'
         THEN
            BEGIN
            SELECT rr.VAL_MONT_GANA
              INTO vnValorMontoGanancia
              FROM LET_CORPO_RANGO_RETEN rr
             WHERE rr.COD_PROG = vnCodigoPrograma
               AND vnNumIngresoReal >= rr.NUM_INGR_INIC
               AND vnNumIngresoReal <= rr.NUM_INGR_FINA;
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    vnValorMontoGanancia := 0;
            END;
            vnGananciaIngrEfec := vnNumIngresoReal * vnValorMontoGanancia;

            BEGIN
                SELECT lg.VAL_MONT_GANA
                  INTO vnValorMontoGananciaLider9
                  FROM LET_CORPO_LIDER_GANAN lg
                 WHERE lg.COD_CLIE_LIDE = psCodigoLider
                   AND lg.CAM_GANA = psCodigoPeriodo
                   AND lg.COD_TIPO_GANA = '09';
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    vnValorMontoGananciaLider9 := NULL;
            END;

            IF vnValorMontoGananciaLider9 IS NOT NULL THEN
                UPDATE LET_CORPO_LIDER_GANAN lg
                   SET lg.VAL_MONT_GANA = vnGananciaIngrEfec,
                       lg.USU_MODI = psUsuario,
                       lg.FEC_MODI = SYSDATE
                 WHERE lg.COD_CLIE_LIDE = psCodigoLider
                   AND lg.CAM_GANA = psCodigoPeriodo
                   AND lg.COD_TIPO_GANA = '09';
            ELSE
                INSERT INTO LET_CORPO_LIDER_GANAN
                        (COD_CLIE_LIDE,
                         CAM_GANA,
                         COD_TIPO_GANA,
                         VAL_MONT_GANA,
                         USU_CREA,
                         FEC_CREA,
                         IND_ACTI)
                VALUES  (psCodigoLider,
                         psCodigoPeriodo,
                         '09',
                         vnGananciaIngrEfec,
                         psUsuario,
                         SYSDATE,
                         '1');
            END IF;
        END IF;

        --Calculo de Ganancia por Retenciones 3/3
        IF vnCodEstadoObjRetencion33 = '07' THEN
            BEGIN
            SELECT rr.VAL_MONT_GANA_33
              INTO vnValorMontoGanancia33
              FROM LET_CORPO_RANGO_RETEN rr
             WHERE rr.COD_PROG = vnCodigoPrograma
               AND vnNumIngresoReal33 >= rr.NUM_INGR_INIC
               AND vnNumIngresoReal33 <= rr.NUM_INGR_FINA;
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    vnValorMontoGanancia33 := 0;
            END;
            vnGananciaRetencion33 := vnNumIngresoReal33 * vnValorMontoGanancia33;

            BEGIN
                SELECT lg.VAL_MONT_GANA
                  INTO vnValorMontoGananciaLider10
                  FROM LET_CORPO_LIDER_GANAN lg
                 WHERE lg.COD_CLIE_LIDE = psCodigoLider
                   AND lg.CAM_GANA = psCodigoPeriodo
                   AND lg.COD_TIPO_GANA = '10';
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    vnValorMontoGananciaLider10 := NULL;
            END;

            IF vnValorMontoGananciaLider10 IS NOT NULL THEN
                UPDATE LET_CORPO_LIDER_GANAN lg
                   SET lg.VAL_MONT_GANA = vnGananciaRetencion33,
                       lg.USU_MODI = psUsuario,
                       lg.FEC_MODI = SYSDATE
                 WHERE lg.COD_CLIE_LIDE = psCodigoLider
                   AND lg.CAM_GANA = psCodigoPeriodo
                   AND lg.COD_TIPO_GANA = '10';
            ELSE
                INSERT INTO LET_CORPO_LIDER_GANAN
                        (COD_CLIE_LIDE,
                         CAM_GANA,
                         COD_TIPO_GANA,
                         VAL_MONT_GANA,
                         USU_CREA,
                         FEC_CREA,
                         IND_ACTI)
                VALUES  (psCodigoLider,
                         psCodigoPeriodo,
                         '10',
                         vnGananciaRetencion33,
                         psUsuario,
                         SYSDATE,
                         '1');
            END IF;
        END IF;

        --Calculo de Ganancia por Retenciones 4/4
        IF vnCodEstadoObjRetencion44 = '09' THEN
            BEGIN
            SELECT rr.VAL_MONT_GANA_44
              INTO vnValorMontoGanancia44
              FROM LET_CORPO_RANGO_RETEN rr
             WHERE rr.COD_PROG = vnCodigoPrograma
               AND vnNumIngresoReal44 >= rr.NUM_INGR_INIC
               AND vnNumIngresoReal44 <= rr.NUM_INGR_FINA;
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    vnValorMontoGanancia44 := 0;
            END;
            vnGananciaRetencion44 := vnNumIngresoReal44 * vnValorMontoGanancia44;

            BEGIN
                SELECT lg.VAL_MONT_GANA
                  INTO vnValorMontoGananciaLider11
                  FROM LET_CORPO_LIDER_GANAN lg
                 WHERE lg.COD_CLIE_LIDE = psCodigoLider
                   AND lg.CAM_GANA = psCodigoPeriodo
                   AND lg.COD_TIPO_GANA = '11';
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    vnValorMontoGananciaLider11 := NULL;
            END;

            IF vnValorMontoGananciaLider11 IS NOT NULL THEN
                UPDATE LET_CORPO_LIDER_GANAN lg
                   SET lg.VAL_MONT_GANA = vnGananciaRetencion44,
                       lg.USU_MODI = psUsuario,
                       lg.FEC_MODI = SYSDATE
                 WHERE lg.COD_CLIE_LIDE = psCodigoLider
                   AND lg.CAM_GANA = psCodigoPeriodo
                   AND lg.COD_TIPO_GANA = '11';
            ELSE
                INSERT INTO LET_CORPO_LIDER_GANAN
                        (COD_CLIE_LIDE,
                         CAM_GANA,
                         COD_TIPO_GANA,
                         VAL_MONT_GANA,
                         USU_CREA,
                         FEC_CREA,
                         IND_ACTI)
                VALUES  (psCodigoLider,
                         psCodigoPeriodo,
                         '11',
                         vnGananciaRetencion44,
                         psUsuario,
                         SYSDATE,
                         '1');
            END IF;
        END IF;
    END IF;

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_GANAN_LIDER: ' || ls_sqlerrm);
  END LET_PR_CALCU_GANAN_LIDER;

  /**********************************************************************************
  Descripcion       : Proceso de Calculo de Ganancia Masivo
  Fecha Creacion    : 03/07/2013
  Autor             : Aurelio Oviedo
  **********************************************************************************/
  PROCEDURE LET_PR_CALCU_GANAN_CORPO(psCodigoPais VARCHAR2,
                                     psCodigoRegion VARCHAR2,
                                     psCodigoMarca VARCHAR2,
                                     psCodigoCanal VARCHAR2,
                                     psCodigoPeriodo VARCHAR2,
                                     psTipoProceso VARCHAR2,
                                     psUsuario VARCHAR2)IS

    --Variables Globales
    vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;

    --Cursor universo de lideres
    CURSOR c_liderActivoProcesoRegion(oidPeriodo NUMBER, codigoRegion VARCHAR2) IS
        SELECT GERE, COD_REGI
          FROM ZON_HISTO_GEREN
         WHERE oidPeriodo >= PERD_OID_PERI_DESD
           AND ( oidPeriodo <= PERD_OID_PERI_HAST
           OR  PERD_OID_PERI_HAST IS NULL )
           AND COD_SECC IS NOT NULL
           AND (codigoRegion IS NULL OR COD_REGI = codigoRegion);

    vsCodGerente    ZON_HISTO_GEREN.GERE%TYPE;
    vsCodRegion     ZON_HISTO_GEREN.COD_REGI%TYPE;

    vnCodigoPrograma                LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
    vnTipoEvalNivelExit             LET_CORPO_PARAM_PROGR.TIP_EVAL_NIVE_EXIT%TYPE;
    --Parametro para el Cursor
    pcCodigoRegion                  ZON_REGIO.COD_REGI%TYPE;

  BEGIN

    vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

    --Obtener Codigo Programa y Parametro Escenario
    BEGIN
        SELECT pp.COD_PROG
          INTO vnCodigoPrograma
          FROM LET_CORPO_PARAM_PROGR pp
         WHERE psCodigoPeriodo >= pp.CAM_INIC
           AND ( psCodigoPeriodo <= pp.CAM_FIN
           OR  pp.CAM_FIN IS NULL )
           AND IND_ACTI = '1';
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            vnCodigoPrograma := NULL;
    END;

    IF vnCodigoPrograma IS NOT NULL THEN

           DELETE LET_CORPO_LIDER_GANAN
           WHERE CAM_GANA = psCodigoPeriodo;

        SELECT  TIP_EVAL_NIVE_EXIT
           INTO  vnTipoEvalNivelExit
           FROM LET_CORPO_PARAM_TRAMO
        WHERE     COD_PROG = vnCodigoPrograma
           AND CAM_INIC <= psCodigoPeriodo
           AND (CAM_FIN >= psCodigoPeriodo);

        IF psTipoProceso = 'R' THEN
            pcCodigoRegion := psCodigoRegion;
        ELSE
            pcCodigoRegion := NULL;
        END IF;

        OPEN c_liderActivoProcesoRegion(vnOidPeriodo, pcCodigoRegion);
        LOOP FETCH c_liderActivoProcesoRegion INTO vsCodGerente, vsCodRegion;

            EXIT WHEN c_liderActivoProcesoRegion%NOTFOUND;

            LET_PKG_PROCE.LET_PR_CALCU_GANAN_LIDER(psCodigoPais,
                                                   psCodigoMarca,
                                                   psCodigoCanal,
                                                   vsCodGerente,
                                                   psCodigoPeriodo,
                                                   psTipoProceso,
                                                   psUsuario);

		/*	IF vnTipoEvalNivelExit = '2' THEN
              LET_PKG_PROCE.LET_PR_CALCU_GANAN_PLUS_LIDER(psCodigoPais,
                                                          psCodigoMarca,
                                                          psCodigoCanal,
                                                          vsCodGerente,
                                                          psCodigoPeriodo,
                                                          psTipoProceso,
                                                          psUsuario);
            END IF;  */

        END LOOP;
        CLOSE c_liderActivoProcesoRegion;
    END IF;

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_GANAN_CORPO: ' || ls_sqlerrm);
  END LET_PR_CALCU_GANAN_CORPO;

  /**********************************************************************************
    Descripcion       : Proceso de Calculo Ganancia Plus
    Fecha Creacion    : 11/07/2013
    Autor             : Juan Altamirano
    **********************************************************************************/
    PROCEDURE LET_PR_CALCU_GANAN_PLUS_LIDER(psCodigoPais VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodLider VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
                                       psUsuario VARCHAR2)IS


    vsCodNivelExiActual          LET_CORPO_LIDER_NIVEL.COD_NIVE%TYPE;
    vsNumRegGanaPlus             NUMBER;
    vsCodProg                    LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
    vsTipoEval                   VARCHAR2(1);
    vsCamIniEsc2                 SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsNumCampGP                  LET_CORPO_PARAM_PROGR.NUM_CAMP_GANA_PLUS%TYPE;
    vsNumCamMNP                  LET_CORPO_PARAM_PROGR.NUM_CAMP_MANT_NIVE_PLUS%TYPE;

    vsMontoGana                  LET_CORPO_PARAM_RANGO_NIVEL.VAL_MONT_GANA%TYPE;
    vsNumPediTole                LET_CORPO_PARAM_RANGO_NIVEL.NUM_PEDI_TOLE%TYPE;
    vsMontGanaPlus               LET_CORPO_PARAM_RANGO_NIVEL.VAL_MONT_GANA_PLUS%TYPE;
    vsMontGanaSobr               LET_CORPO_PARAM_RANGO_NIVEL.VAL_MONT_GANA_SOBR%TYPE;

    vnOidPais                    SEG_PAIS.OID_PAIS%TYPE;
    vnOidMarca                   SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal                   SEG_CANAL.OID_CANA%TYPE;
    vnOidPeriodo                 CRA_PERIO.OID_PERI%TYPE;

    vsCodClieLider               LET_CORPO_SECCI_RESUL.COD_CLIE_LIDE%TYPE;
    vsCodClieLiderResul          LET_CORPO_SECCI_RESUL.COD_CLIE_LIDE%TYPE;
    vsCodClieLiderGanan          LET_CORPO_LIDER_GANAN.COD_CLIE_LIDE%TYPE;
    vsMontGan                    LET_CORPO_LIDER_GANAN.VAL_MONT_GANA%TYPE;

    vsOidPeriIniNom              CRA_PERIO.OID_PERI%TYPE;
    vsCodPeriIniNom              SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCodPeriAnterior            SEG_PERIO_CORPO.COD_PERI%TYPE;
    vnOidPeriAnterior            CRA_PERIO.OID_PERI%TYPE;

    vsCodPeriPrimNivel           SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCodPeriUltiNivel           SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCodPeriXNivel              SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCodPeriAux                 SEG_PERIO_CORPO.COD_PERI%TYPE;

    vsCamProcPrimNivel           SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCamProcUltiNivel           SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCamProcXNivel              SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsUltiNGP                    SEG_PERIO_CORPO.COD_PERI%TYPE;

    vsIndPrimNivel               VARCHAR2(1);
    vsIndUltiNivel               VARCHAR2(1);
    vsIndXNivel                  VARCHAR2(15);

    vsPesoNivelActual            LET_CORPO_NIVEL_EXITO.VAL_PESO_NIVE%TYPE;
    vsPesoPrimNivel              LET_CORPO_NIVEL_EXITO.VAL_PESO_NIVE%TYPE;
    vsPesoUltiNivel              LET_CORPO_NIVEL_EXITO.VAL_PESO_NIVE%TYPE;
    vsPesoXNivel                 LET_CORPO_NIVEL_EXITO.VAL_PESO_NIVE%TYPE;

    vsUltiIndFinEval             LET_CORPO_LIDER_GANAN_PLUS.IND_FIN_EVAL%TYPE;
    vsUltiMantNivel              LET_CORPO_LIDER_GANAN_PLUS.CON_MANT_NIVE%TYPE;

    vsValMontoGana               LET_CORPO_LIDER_GANAN.VAL_MONT_GANA%TYPE;


    BEGIN
       vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
       vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
       vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
       vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

       vsCodPeriAnterior := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psCodigoPeriodo,vnOidPais,vnOidMarca,vnOidCanal,-1);
       vnOidPeriAnterior := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCodPeriAnterior);

       --Verificar si se tiene resultados en la campa�a anterior para la L�der
         BEGIN
           SELECT COD_CLIE_LIDE
             INTO vsCodClieLiderResul
             FROM LET_CORPO_SECCI_RESUL
            WHERE CAM_OBJE = psCodigoPeriodo
              AND COD_CLIE_LIDE = psCodLider;
         EXCEPTION
           WHEN NO_DATA_FOUND THEN
             vsCodClieLider := NULL;
         END;

         --OBTENER DATOS DEL PROGRAMA
         BEGIN
          SELECT COD_PROG, TIP_EVAL_NIVE_EXIT, CAM_INIC_ESC2, NUM_CAMP_GANA_PLUS, NUM_CAMP_MANT_NIVE_PLUS
            INTO vsCodProg, vsTipoEval, vsCamIniEsc2, vsNumCampGP, vsNumCamMNP
            FROM LET_CORPO_PARAM_PROGR
          WHERE
            CAM_INIC >= psCodigoPeriodo
            AND CAM_FIN <= psCodigoPeriodo
            AND IND_ACTI = 1;
         EXCEPTION
           WHEN NO_DATA_FOUND THEN
             vsCodProg := NULL;
         END;

         --OBTENER CAMPA�A INICIO NOMBRAMIENTO
         BEGIN
         SELECT PERD_OID_PERI_DESD INTO vsOidPeriIniNom
          FROM ZON_HISTO_GEREN
          WHERE GERE = psCodLider
            AND COD_SECC IS NOT NULL
            AND vnOidPeriAnterior >= PERD_OID_PERI_DESD
            AND (vnOidPeriAnterior <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vsOidPeriIniNom := NULL;
          END;
          --OBTIENE EL CODIGO PERIODO DEL OID(vsOidPeriIniNom)
          BEGIN
             SELECT C.COD_PERI INTO vsCodPeriIniNom
                FROM SEG_PERIO_CORPO C, CRA_PERIO P
                  WHERE C.OID_PERI = P.PERI_OID_PERI AND
                        C.OID_PERI = vsOidPeriIniNom;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCodPeriIniNom := NULL;
          END;


          -- Obtener Nivel de Exito Actual
          BEGIN
           SELECT LIN.COD_NIVE, NIE.VAL_PESO_NIVE
              INTO vsCodNivelExiActual, vsPesoNivelActual
              FROM LET_CORPO_LIDER_NIVEL LIN,
                   LET_CORPO_NIVEL_EXITO NIE
             WHERE LIN.COD_NIVE = NIE.COD_NIVE
               AND LIN.IND_ACTI = '1'
               AND LIN.COD_CLIE_LIDE = psCodLider
               AND psCodigoPeriodo >= LIN.CAM_INIC
               AND (psCodigoPeriodo <= LIN.CAM_FIN OR LIN.CAM_FIN IS NULL)
               AND LIN.IND_TIPO_NIVE = 'R';
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vsCodNivelExiActual := NULL;
              vsPesoNivelActual := NULL;
          END;

          --Obtener Atributos del Nivel
         BEGIN
          SELECT RN.VAL_MONT_GANA, RN.NUM_PEDI_TOLE, RN.VAL_MONT_GANA_PLUS, RN.VAL_MONT_GANA_SOBR
          INTO vsMontoGana, vsNumPediTole, vsMontGanaPlus, vsMontGanaSobr
          FROM LET_CORPO_PARAM_RANGO_NIVEL RN
          WHERE RN.COD_PAIS = psCodigoPais
            AND RN.COD_PROG = vsCodProg
            AND RN.COD_NIVE = vsCodNivelExiActual;
         EXCEPTION
           WHEN NO_DATA_FOUND THEN
             vsMontoGana := NULL;
         END;

            --5. VALIDACIONES INICIALES

            IF (vsCodClieLider IS NULL) OR (vsCamIniEsc2 = psCodigoPeriodo) OR (vsCodPeriIniNom = psCodigoPeriodo) THEN
               --Actualizar 1er Nivel Ganancia Plus (UPDATE/INSERT)
                 SELECT COUNT(1) INTO vsNumRegGanaPlus
                  FROM LET_CORPO_LIDER_GANAN_PLUS
                    WHERE COD_CLIE_LIDE = psCodLider
                    AND CAM_PROC = psCodigoPeriodo;

                    IF vsNumRegGanaPlus = 0 THEN
                      UPDATE LET_CORPO_LIDER_GANAN_PLUS
                       SET COD_NIVE = vsCodNivelExiActual,
                          IND_PRIM_NIVE = '1',
                          IND_ULTM_NIVE = '0',
                          CON_MANT_NIVE = '0',
                          IND_GANA = '0',
                          IND_FIN_EVAL = '0',
                          USU_MODI = psUsuario,
                          FEC_MODI = SYSDATE
                       WHERE COD_CLIE_LIDE = psCodLider
                       AND CAM_PROC = psCodigoPeriodo;
                    ELSE
                      INSERT INTO LET_CORPO_LIDER_GANAN_PLUS
                        (COD_CLIE_LIDE, CAM_PROC,
                         COD_NIVE, IND_PRIM_NIVE,
                         IND_ULTM_NIVE, CON_MANT_NIVE,
                         IND_GANA, IND_FIN_EVAL,
                         USU_CREA, USU_MODI,
                         FEC_CREA, FEC_MODI,
                         IND_ACTI)
                      VALUES  (psCodLider, psCodigoPeriodo,
                               vsCodNivelExiActual,'1',
                               '0', '0',
                               '0', '0',
                               psUsuario, NULL,
                               SYSDATE, NULL,
                               '1'
                              );
                    END IF;
            ELSE
                 --OBTENER 1er NIVEL
                 BEGIN
                   SELECT MAX(LGP.CAM_PROC)
                          INTO vsCamProcPrimNivel
                     FROM LET_CORPO_LIDER_GANAN_PLUS LGP,
                          LET_CORPO_NIVEL_EXITO NE
                    WHERE LGP.COD_NIVE = NE.COD_NIVE
                      AND LGP.IND_ACTI = '1'
                      AND LGP.IND_PRIM_NIVE = '1'
                      AND LGP.IND_FIN_EVAL = '0'
                      AND LGP.CAM_PROC <= psCodigoPeriodo
                      AND LGP.Cod_Clie_Lide = psCodLider;
                 EXCEPTION
                   WHEN NO_DATA_FOUND THEN
                   vsCamProcPrimNivel := NULL;
                 END;

                 BEGIN
                   SELECT LGP.IND_PRIM_NIVE, LGP.CAM_PROC ,NE.VAL_PESO_NIVE
                     INTO vsIndPrimNivel, vsCodPeriPrimNivel, vsPesoPrimNivel
                     FROM LET_CORPO_LIDER_GANAN_PLUS LGP,
                          LET_CORPO_NIVEL_EXITO NE
                    WHERE LGP.COD_NIVE = NE.COD_NIVE
                      AND LGP.IND_ACTI = '1'
                      AND LGP.IND_PRIM_NIVE = '1'
                      AND LGP.IND_FIN_EVAL = '0'
                      AND LGP.CAM_PROC = vsCamProcPrimNivel
                      AND LGP.Cod_Clie_Lide = psCodLider;
                 EXCEPTION
                   WHEN NO_DATA_FOUND THEN
                   vsIndPrimNivel := NULL;
                   vsCodPeriPrimNivel := NULL;
                   vsPesoPrimNivel := NULL;
                 END;

                 --OBTENER ultimo NIVEL
                  BEGIN
                      SELECT MAX(LGP.CAM_PROC)
                         INTO vsCamProcUltiNivel
                         FROM LET_CORPO_LIDER_GANAN_PLUS LGP,
                              LET_CORPO_NIVEL_EXITO NE
                        WHERE LGP.COD_NIVE = NE.COD_NIVE
                          AND LGP.IND_ACTI = '1'
                          AND LGP.IND_ULTM_NIVE = '1'
                          AND LGP.IND_FIN_EVAL = '0'
                          AND LGP.CAM_PROC <= psCodigoPeriodo
                          AND LGP.COD_CLIE_LIDE = psCodLider;
                   EXCEPTION
                   WHEN NO_DATA_FOUND THEN
                        vsCamProcUltiNivel := NULL;
                   END;

                   BEGIN
                     SELECT LGP.IND_ULTM_NIVE, LGP.CAM_PROC ,NE.VAL_PESO_NIVE
                       INTO vsIndUltiNivel, vsCodPeriUltiNivel, vsPesoUltiNivel
                       FROM LET_CORPO_LIDER_GANAN_PLUS LGP,
                            LET_CORPO_NIVEL_EXITO NE
                      WHERE LGP.COD_NIVE = NE.COD_NIVE
                        AND LGP.IND_ACTI = '1'
                        AND LGP.IND_ULTM_NIVE = '1'
                        AND LGP.IND_FIN_EVAL = '0'
                        AND LGP.CAM_PROC = vsCamProcUltiNivel
                        AND LGP.COD_CLIE_LIDE = psCodLider;
                   EXCEPTION
                   WHEN NO_DATA_FOUND THEN
                        vsIndUltiNivel := NULL;
                        vsCodPeriUltiNivel := NULL;
                        vsPesoUltiNivel := NULL;
                   END;

                 --NIVEL X
                  BEGIN
                   SELECT MAX(LGP.CAM_PROC)
                     INTO vsCamProcXNivel
                     FROM LET_CORPO_LIDER_GANAN_PLUS LGP,
                          LET_CORPO_NIVEL_EXITO NIE
                    WHERE LGP.COD_NIVE = NIE.COD_NIVE
                      AND LGP.IND_ACTI = '1'
                      AND LGP.CON_MANT_NIVE > 0
                      AND LGP.CAM_PROC <= psCodigoPeriodo
                      AND LGP.Cod_Clie_Lide = psCodLider;
                   EXCEPTION
                   WHEN NO_DATA_FOUND THEN
                        vsCamProcXNivel := NULL;
                   END;

                   BEGIN
                     SELECT LGP.CON_MANT_NIVE, LGP.CAM_PROC ,NE.VAL_PESO_NIVE
                       INTO vsIndXNivel, vsCodPeriXNivel, vsPesoXNivel
                       FROM LET_CORPO_LIDER_GANAN_PLUS LGP,
                            LET_CORPO_NIVEL_EXITO NE
                      WHERE LGP.COD_NIVE = NE.COD_NIVE
                        AND LGP.IND_ACTI = '1'
                        AND LGP.CON_MANT_NIVE > 0
                        AND LGP.CAM_PROC = vsCamProcXNivel
                        AND LGP.Cod_Clie_Lide = psCodLider;
                   EXCEPTION
                   WHEN NO_DATA_FOUND THEN
                        vsIndXNivel := NULL;
                        vsCodPeriXNivel := NULL;
                        vsPesoXNivel := NULL;
                   END;


                 --INICIO VALIDACIONES MULTI NIVEL
                 IF vsIndUltiNivel IS NOT NULL THEN
                    IF (vsIndPrimNivel <> vsCodNivelExiActual) THEN
                      --COMPARAMOS PESOS DE NIVELES
                      IF (vsPesoPrimNivel < vsPesoNivelActual) THEN
                        --Obtenemos periodo del primerNivel y le sumamos Nros.Camp.MantenesPlus
                        vsCodPeriAux := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(vsCodPeriPrimNivel,vnOidPais,vnOidMarca,vnOidCanal,vsNumCampGP);

                        IF  (vsCodPeriAux > psCodigoPeriodo) THEN
                            UPDATE LET_CORPO_LIDER_GANAN_PLUS
                              SET COD_NIVE = vsCodNivelExiActual,
                                 IND_PRIM_NIVE = '0',
                                 IND_ULTM_NIVE = '1',
                                 CON_MANT_NIVE = '0',
                                 IND_GANA = '0',
                                 IND_FIN_EVAL = '0',
                                 USU_MODI = psUsuario,
                                 FEC_MODI = SYSDATE;
                        ELSE
                            UPDATE LET_CORPO_LIDER_GANAN_PLUS
                                SET COD_NIVE = vsCodNivelExiActual,
                                   IND_PRIM_NIVE = '1',
                                   IND_ULTM_NIVE = '0',
                                   CON_MANT_NIVE = '0',
                                   IND_GANA = '0',
                                   IND_FIN_EVAL = '0',
                                   USU_MODI = psUsuario,
                                   FEC_MODI = SYSDATE;

                        END IF;

                      ELSE
                        UPDATE LET_CORPO_LIDER_GANAN_PLUS
                              SET COD_NIVE = vsCodNivelExiActual,
                                 IND_PRIM_NIVE = '1',
                                 IND_ULTM_NIVE = '0',
                                 CON_MANT_NIVE = '0',
                                 IND_GANA = '0',
                                 IND_FIN_EVAL = '0',
                                 USU_MODI = psUsuario,
                                 FEC_MODI = SYSDATE;
                      END IF;

                    END IF;

                ELSE
                  --pesoUltimoNivel <= pesoNivelActual
                  IF (vsPesoUltiNivel <= vsPesoNivelActual) THEN
                    IF (vsIndXNivel IS NULL) THEN--SI NIVEL X ES VACIO
                       UPDATE LET_CORPO_LIDER_GANAN_PLUS
                              SET COD_NIVE = vsCodNivelExiActual,
                                 IND_PRIM_NIVE = '0',
                                 IND_ULTM_NIVE = '1',
                                 CON_MANT_NIVE = '1',
                                 IND_GANA = '0',
                                 IND_FIN_EVAL = '0',
                                 USU_MODI = psUsuario,
                                 FEC_MODI = SYSDATE;

                    ELSE--SI NIVEL X NO ES VACIO
                      --(CON_MANT_NIVE) Contador N
                      IF (vsIndXNivel < vsNumCamMNP) THEN  --SI Contador N < X
                        vsIndXNivel := vsIndXNivel + 1;  --Contador N = Contador N +1
                        UPDATE LET_CORPO_LIDER_GANAN_PLUS
                          SET COD_NIVE = vsCodNivelExiActual,
                              IND_PRIM_NIVE = '0',
                              IND_ULTM_NIVE = '1',
                              CON_MANT_NIVE = vsIndXNivel,
                              IND_GANA = '0',
                              IND_FIN_EVAL = '0',
                              USU_MODI = psUsuario,
                              FEC_MODI = SYSDATE;

                      ELSIF (vsIndXNivel = vsNumCamMNP) THEN
                         UPDATE LET_CORPO_LIDER_GANAN_PLUS
                          SET COD_NIVE = vsCodNivelExiActual,
                              IND_PRIM_NIVE = '0',
                              IND_ULTM_NIVE = '1',
                              CON_MANT_NIVE = vsIndXNivel,
                              IND_GANA = '0',
                              IND_FIN_EVAL = '1',
                              USU_MODI = psUsuario,
                              FEC_MODI = SYSDATE;

                      END IF;

                    END IF; --FIN NIVEL X ES VACIO


                  ELSE
                     IF (vsIndXNivel IS NULL) THEN
                       UPDATE LET_CORPO_LIDER_GANAN_PLUS
                          SET COD_NIVE = vsCodNivelExiActual,
                              IND_PRIM_NIVE = '0',
                              IND_ULTM_NIVE = '1',
                              CON_MANT_NIVE = '1',
                              IND_GANA = '0',
                              IND_FIN_EVAL = '1',
                              USU_MODI = psUsuario,
                              FEC_MODI = SYSDATE;
                     ELSE
                         --(CON_MANT_NIVE) Contador N
                         IF (vsIndXNivel <= vsNumCamMNP) THEN  --SI Contador N < X
                          UPDATE LET_CORPO_LIDER_GANAN_PLUS
                            SET COD_NIVE = vsCodNivelExiActual,
                                IND_PRIM_NIVE = '0',
                                IND_ULTM_NIVE = '1',
                                CON_MANT_NIVE = vsIndXNivel,
                                IND_GANA = '0',
                                IND_FIN_EVAL = '1',
                                USU_MODI = psUsuario,
                                FEC_MODI = SYSDATE;
                         END IF;
                     END IF;


                  END IF; --FIN pesoUltimoNivel <= pesoNivelActual


                END IF;--FIN VALIDACIONES MULTI NIVEL

            END IF; --FIN 5. VALIDACIONES INICIALES

          --OBTENER ULTIMA LGP ACTIVA
           BEGIN
               SELECT MAX(LGP.CAM_PROC)
                      INTO vsUltiNGP
                 FROM LET_CORPO_LIDER_GANAN_PLUS LGP
                WHERE LGP.IND_ACTI = '1'
                  AND LGP.IND_FIN_EVAL = '0'
                  AND LGP.CAM_PROC <= psCodigoPeriodo
                  AND LGP.Cod_Clie_Lide = psCodLider;
           EXCEPTION
               WHEN NO_DATA_FOUND THEN
               vsUltiNGP := NULL;
           END;

           --OBTENER PARAMETROS DE LA ULTIMA LGP
            BEGIN
               SELECT IND_FIN_EVAL, CON_MANT_NIVE
                      INTO vsUltiIndFinEval, vsUltiMantNivel
                 FROM LET_CORPO_LIDER_GANAN_PLUS
                WHERE IND_ACTI = '1'
                  AND IND_FIN_EVAL = '0'
                  AND CAM_PROC <= psCodigoPeriodo
                  AND COD_CLIE_LIDE = psCodLider;
           EXCEPTION
               WHEN NO_DATA_FOUND THEN
               vsUltiIndFinEval := NULL;
               vsUltiMantNivel := NULL;
           END;

           --VALIDACIONES PARA LA ULTIMA LGP
           IF (vsUltiIndFinEval = 1) THEN
                IF (vsUltiMantNivel = vsNumCamMNP) THEN --SI (MttoNivel = X)
                  UPDATE LET_CORPO_LIDER_GANAN_PLUS
                            SET COD_NIVE = vsCodNivelExiActual,
                                IND_PRIM_NIVE = '0',
                                IND_ULTM_NIVE = '1',
                                CON_MANT_NIVE = vsUltiMantNivel,
                                IND_GANA = '1',
                                IND_FIN_EVAL = '1',
                                USU_MODI = psUsuario,
                                FEC_MODI = SYSDATE;

                  --Parametro GananciaPlus Obtenido: vsMontGanaPlus

                   BEGIN--Obtenemos el MontoGanancia de la entidad LIGER_GANANCIA
                        SELECT VAL_MONT_GANA
                         INTO vsValMontoGana
                        FROM LET_CORPO_LIDER_GANAN
                           WHERE COD_CLIE_LIDE = psCodLider
                             AND CAM_GANA = psCodigoPeriodo
                             AND COD_TIPO_GANA = '05';
                   EXCEPTION
                     WHEN NO_DATA_FOUND THEN
                       vsValMontoGana := NULL;
                   END;

                   IF (vsValMontoGana IS NOT NULL) THEN
                     UPDATE LET_CORPO_LIDER_GANAN
                            SET VAL_MONT_GANA = vsMontGanaPlus,
                                USU_MODI = psUsuario,
                                FEC_MODI = SYSDATE
                            WHERE COD_CLIE_LIDE = psCodLider
                                   AND CAM_GANA = psCodigoPeriodo
                                   AND COD_TIPO_GANA = '05';
                   ELSE
                     INSERT INTO LET_CORPO_LIDER_GANAN
                        (COD_CLIE_LIDE, CAM_GANA,
                         COD_TIPO_GANA, VAL_MONT_GANA,
                         CAM_PAGO, USU_CREA, USU_MODI,
                         FEC_CREA, FEC_MODI, IND_ACTI)
                      VALUES  (psCodLider, psCodigoPeriodo,
                               '05',vsMontGanaPlus,
                               NULL, psUsuario, NULL,
                               SYSDATE, NULL, '1'
                              );
                   END IF;

                END IF; --FIN (Si MttoNivel = X)


           END IF;


    EXCEPTION
      WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_GANAN_PLUS_LIDER: ' || ls_sqlerrm);
    END LET_PR_CALCU_GANAN_PLUS_LIDER;


    /**********************************************************************************
    Descripcion       : Proceso de Registrar Baja Automatica
    Fecha Creacion    : 18/07/2013
  Autor             : CSVD - FFVV
    **********************************************************************************/
    PROCEDURE LET_PR_CALCU_BAJA_LIDER(psCodigoPais VARCHAR2,
                                       psCodigoMarca VARCHAR2,
                                       psCodigoCanal VARCHAR2,
                                       psCodigoRegion VARCHAR2,
                                       psCodigoPeriodo VARCHAR2,
                                       psTipoProceso VARCHAR2,
                                       psUsuario VARCHAR2)IS

  vnOidPais                            SEG_PAIS.OID_PAIS%TYPE;
  vnOidMarca                           SEG_MARCA.OID_MARC%TYPE;
  vnOidCanal                           SEG_CANAL.OID_CANA%TYPE;
  vnOidPeriodo                         CRA_PERIO.OID_PERI%TYPE;

  vsCodigoPeriodoSgte                  SEG_PERIO_CORPO.COD_PERI%TYPE;
  vnOidPeriodoSgte                     CRA_PERIO.OID_PERI%TYPE;


  --Cursor para universo de lideres
  CURSOR cLiderActivo(oidPeriodo NUMBER, codigoRegion ZON_REGIO.COD_REGI%TYPE) IS

  SELECT DISTINCT(GERE), COD_REGI, COD_ZONA, cod_secc
  FROM ZON_HISTO_GEREN
  WHERE oidPeriodo >= PERD_OID_PERI_DESD
    AND (oidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
    AND COD_SECC IS NOT NULL
    AND (codigoRegion is NULL OR COD_REGI = codigoRegion);

  --cursor para universo de zonas con lideres que no cumplen objetivo pedido
  CURSOR cZonaBaja( codPais VARCHAR2,
                    codPeriodo VARCHAR2,
                    lnNumCampBajaAuto LET_CORPO_PARAM_PROGR.NUM_CAMP_BAJA_AUTO%TYPE
                  ) IS
       SELECT sr.cod_zona,COUNT(*)
         FROM let_corpo_secci_resul sr,
              (
                 SELECT sr.cod_clie_lide,COUNT(*)
                   FROM let_corpo_secci_resul sr
                  WHERE sr.cod_esta_obje_pedi = '04' AND
                        sr.ind_envi_baja = 0 AND
                        sr.cam_obje BETWEEN gen_pkg_gener.gen_fn_perio_nsigu(codPais,codPeriodo,-(lnNumCampBajaAuto-1)) AND codPeriodo
               GROUP BY sr.cod_clie_lide
                 HAVING COUNT(*) = lnNumCampBajaAuto
              ) pb

        WHERE sr.cam_obje = codPeriodo AND
              sr.cod_clie_lide = pb.cod_clie_lide
     GROUP BY sr.cod_zona;


  --cursor lider baja por zona
    CURSOR cZonaLiderBaja( codPais VARCHAR2,
                           codPeriodo VARCHAR2,
                           lnNumCampBajaAuto LET_CORPO_PARAM_PROGR.NUM_CAMP_BAJA_AUTO%TYPE,
                           codZona VARCHAR2
                         ) IS
       SELECT sr.cod_clie_lide,sr.cod_secc
         FROM let_corpo_secci_resul sr,
              (
                 SELECT sr.cod_clie_lide,COUNT(*)
                   FROM let_corpo_secci_resul sr
                  WHERE sr.cod_esta_obje_pedi = '04' AND
                        sr.ind_envi_baja = 0 AND
                        sr.cam_obje BETWEEN gen_pkg_gener.gen_fn_perio_nsigu(codPais,codPeriodo,-(lnNumCampBajaAuto-1)) AND codPeriodo
               GROUP BY sr.cod_clie_lide
                 HAVING COUNT(*) = lnNumCampBajaAuto
              ) pb

        WHERE sr.cam_obje      = codPeriodo AND
              sr.cod_clie_lide = pb.cod_clie_lide AND
              sr.cod_zona      = codZona;
  --cursor para universo lider baja por ranking
    CURSOR cZonaLiderBajaRanking( codPais VARCHAR2,
                                  codPeriodo VARCHAR2,
                                  lnNumCampBajaAuto LET_CORPO_PARAM_PROGR.NUM_CAMP_BAJA_AUTO%TYPE,
                                  codZona VARCHAR2
                                 ) IS
        SELECT sr.cod_clie_lide,
               (SELECT COUNT(*)
                 FROM let_corpo_lider_baja lb
                WHERE lb.cod_clie_lide = sr.cod_clie_lide
               ) cantidad,
               sr.val_porc_crit brecha,
               sr.cod_secc
         FROM let_corpo_secci_resul sr,
              (
                 SELECT sr.cod_clie_lide,COUNT(*)
                   FROM let_corpo_secci_resul sr
                  WHERE sr.cod_esta_obje_pedi = '04' AND
                        sr.ind_envi_baja = 0 AND
                        sr.cam_obje BETWEEN gen_pkg_gener.gen_fn_perio_nsigu(codPais,codPeriodo,-(lnNumCampBajaAuto-1)) AND codPeriodo
               GROUP BY sr.cod_clie_lide
                 HAVING COUNT(*) = lnNumCampBajaAuto
              ) pb
        WHERE sr.cam_obje      = codPeriodo AND
              sr.cod_clie_lide = pb.cod_clie_lide AND
              sr.cod_zona      = codZona
     ORDER BY cantidad DESC, brecha asc;
  --Cursor para universo d lideres que no cumplen objetivo pedido
  CURSOR cLideresBaja(codPeriodo VARCHAR2,
                      codProg VARCHAR2,
                      codPais VARCHAR2,
                      codZona VARCHAR2) IS
  SELECT COD_CLIE_LIDE, COD_SECC, VAL_PORC_CRIT, IND_ENVI_BAJA
  FROM LET_CORPO_SECCI_RESUL
  WHERE COD_PAIS = codPais
    AND COD_PROG = codProg
    AND CAM_OBJE = codPeriodo
    AND VAL_PORC_CRIT < 0
    ORDER BY VAL_PORC_CRIT, IND_ENVI_BAJA ASC;

    vsCodGerente                 ZON_HISTO_GEREN.GERE%TYPE;
    vsCodRegion                  ZON_REGIO.COD_REGI%TYPE;
    vsCodZona                    ZON_ZONA.Cod_Zona%TYPE;
    vsCodSecc                    Zon_Secci.Cod_Secc%TYPE;
    vsCodProg                    LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
    vsNumCampBajaAuto            LET_CORPO_PARAM_PROGR.NUM_CAMP_BAJA_AUTO%TYPE;
    vsNumLiderBajaAutoZona       LET_CORPO_PARAM_PROGR.NUM_LIDE_BAJA_AUTO_ZONA%TYPE;
    --Parametro para el Cursor
    vcCodigoReg                  ZON_REGIO.COD_REGI%TYPE;

    vnOidCliente                 MAE_CLIEN_HISTO_ESTAT.CLIE_OID_CLIE%TYPE;
    vsEstaOidClie                MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;
    vsRegiBajas                  NUMBER;
    vsRegiBajasZona              NUMBER;
    vsCamFinTramo                VARCHAR2(6);
    vsContDifPeriod              NUMBER;
    vsCodLiderBaja               LET_CORPO_SECCI_RESUL.COD_CLIE_LIDE%TYPE;
    vsCodSeccBaja                Zon_Secci.Cod_Secc%TYPE;
    vsPorcCriti                  NUMBER(10,2);
    vsIndEnviBaja                VARCHAR2(1);
    vnCantibajas                 NUMBER;
    lsActivaDesbPedi             bas_param_pais.val_para%TYPE;

    BEGIN

    vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

      IF psTipoProceso = 'R' THEN
          vcCodigoReg := psCodigoRegion;
      ELSE
          vcCodigoReg := NULL;
      END IF;

      BEGIN
          SELECT COD_PROG,
                 NUM_CAMP_BAJA_AUTO,
                 NUM_LIDE_BAJA_AUTO_ZONA--N
            INTO vsCodProg,
                 vsNumCampBajaAuto,
                 vsNumLiderBajaAutoZona
          FROM LET_CORPO_PARAM_PROGR
           WHERE psCodigoPeriodo >= CAM_INIC AND
                 (psCodigoPeriodo <= CAM_FIN OR CAM_FIN IS NULL) AND
                 IND_ACTI = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsCodProg := NULL;
      END;

     IF vsCodProg IS NOT NULL THEN
      OPEN cLiderActivo(vnOidPeriodo, vcCodigoReg);
         LOOP FETCH cLiderActivo INTO vsCodGerente, vsCodRegion, vsCodZona, vsCodSecc;
              EXIT WHEN cLiderActivo%NOTFOUND;
              vnOidCliente :=  Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(vsCodGerente);
              BEGIN
               SELECT ESTA_OID_ESTA_CLIE
                INTO vsEstaOidClie
                FROM MAE_CLIEN_HISTO_ESTAT
                WHERE CLIE_OID_CLIE = vnOidCliente
                      AND vnOidPeriodo >= PERD_OID_PERI
                      AND (vnOidPeriodo <=  PERD_OID_PERI_PERI_FIN OR PERD_OID_PERI_PERI_FIN IS NULL);
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   vsEstaOidClie := NULL;
              END;

              IF (vsEstaOidClie = 5) THEN
                 --Obtener Baja Lider
                 BEGIN
                 SELECT COUNT(1)
                  INTO vsRegiBajas
                 FROM LET_CORPO_LIDER_BAJA
                   WHERE COD_CLIE_LIDE = vsCodGerente
                          AND CAM_BAJA = psCodigoPeriodo;
                   EXCEPTION
                     WHEN NO_DATA_FOUND THEN
                          vsRegiBajas := 0;
                 END;

                 IF (vsRegiBajas = 0) THEN
                     INSERT INTO LET_CORPO_LIDER_BAJA
                        (COD_CLIE_LIDE, CAM_BAJA,
                         LTDE_COD_TIPO_DESV, IND_TIPO_PROC,
                         USU_CREA, FEC_CREA,
                         IND_ACTI
                         )
                      VALUES  (vsCodGerente, psCodigoPeriodo,
                               1, psTipoProceso,
                               psUsuario, SYSDATE,
                               '1'
                              );
                 ELSE
                    UPDATE LET_CORPO_LIDER_BAJA
                       SET LTDE_COD_TIPO_DESV = 1,
                          IND_TIPO_PROC = psTipoProceso,
                          USU_MODI = psUsuario,
                          FEC_MODI = SYSDATE,
                          IND_ACTI = '1'
                        WHERE COD_CLIE_LIDE = vsCodGerente
                        AND CAM_BAJA = psCodigoPeriodo;

                 END IF;

                 IF psTipoProceso = 'P' THEN

                   --Cerramos la clasificaci�n let vigente
                   LET_PKG_PROCE.LET_PR_PROCE_CLASI_LIDER(psCodigoPais,
                                                                      psCodigoMarca,
                                                                      psCodigoCanal,
                                                                      '2',
                                                                      vsCodGerente,
                                                                      psCodigoPeriodo,
                                                                      psUsuario
                                                                     );
                   --eliminamos la clasificaci�n mae asignada
                   DELETE FROM mae_clien_clasi cc
                         WHERE cc.oid_clie_clas IN ( SELECT mcc.oid_clie_clas
                                                       FROM mae_clien c,
                                                            mae_clien_tipo_subti mcts,
                                                            mae_clien_clasi mcc,
                                                            mae_tipo_clasi_clien mtcc,
                                                            mae_clasi mc
                                                      WHERE c.oid_clie              = mcts.clie_oid_clie AND
                                                            mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt AND
                                                            mcc.tccl_oid_tipo_clasi = mtcc.oid_tipo_clas AND
                                                            mcc.clas_oid_clas       = mc.oid_clas AND
                                                            mtcc.sbti_oid_subt_clie = mcts.sbti_oid_subt_clie AND
                                                            mtcc.cod_tipo_clas      = '01' AND -- Tipo Clasificacion Lider
                                                            mc.tccl_oid_tipo_clas   = mtcc.oid_tipo_clas AND
                                                            mc.cod_clas             = '01' AND -- Clasificacion Lider
                                                            c.cod_clie              = vsCodGerente
                                                    );
                  --Eliminamos Lider de la Secci�n
                  UPDATE zon_secci zs
                     SET zs.clie_oid_clie = NULL
                   WHERE zs.zzon_oid_zona = (SELECT zz.oid_zona
                                               FROM zon_zona zz
                                              WHERE zz.cod_zona = vsCodZona
                                             ) AND
                         zs.cod_secc      = vsCodSecc AND
                         zs.clie_oid_clie = (SELECT c.oid_clie
                                               FROM mae_clien c
                                              WHERE c.cod_clie = vsCodGerente
                                            );
                  --Cerramos la Historia
                  UPDATE zon_histo_geren zhg
                     SET zhg.fec_hast           = (SELECT bcf.fec_proc
                                                     FROM bas_ctrl_fact bcf
                                                    WHERE bcf.cod_peri = psCodigoPeriodo
                                                  ),
                         zhg.perd_oid_peri_hast = vnOidPeriodo,
                         zhg.ind_desv_auto      = 1,
                         zhg.fec_modi           = SYSDATE,
                         zhg.usu_modi           = psUsuario
                   WHERE zhg.gere = vsCodGerente AND
                         zhg.cod_secc IS NOT NULL AND
                         vnOidPeriodo BETWEEN zhg.perd_oid_peri_desd AND nvl(zhg.perd_oid_peri_hast,vnOidPeriodo);
                 END IF;

              END IF;


         END LOOP;
      CLOSE cLiderActivo;
     END IF;

--inicio
--obtenemos indicador de bloqueo de reingreso por pais
  BEGIN
       SELECT bpp.val_para
         INTO lsActivaDesbPedi
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'LET'
          AND bpp.nom_para = 'ActivaDesbPedi'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lsActivaDesbPedi := '0';
  END;
  IF lsActivaDesbPedi = '1' THEN
   BEGIN
/*     BEGIN
     SELECT CAM_FIN, Gen_Pkg_Gener.GEN_FN_DEVUE_DIFER_PERIO(CAM_INIC,CAM_FIN)
     INTO vsCamFinTramo, vsContDifPeriod
     FROM LET_CORPO_PARAM_TRAMO
          WHERE COD_PROG = vsCodProg;
     EXCEPTION
       WHEN NO_DATA_FOUND THEN
         vsCamFinTramo:=NULL;
         vsContDifPeriod := NULL;
     END;
*/
--     IF(vsCamFinTramo = psCodigoPeriodo) THEN

      --abrir cursor cZonaBaja
      OPEN cZonaBaja(psCodigoPais,psCodigoPeriodo,vsNumCampBajaAuto);
           LOOP FETCH cZonaBaja INTO vsCodZona,vsRegiBajasZona;
                EXIT WHEN cZonaBaja%NOTFOUND;
                     IF vsRegiBajasZona<=vsNumLiderBajaAutoZona THEN
                       --Abrir cursor cZonaLiderBaja
                       OPEN cZonaLiderBaja(psCodigoPais,psCodigoPeriodo,vsNumCampBajaAuto,vsCodZona);
                         LOOP FETCH cZonaLiderBaja INTO vsCodLiderBaja,vsCodSecc;
                           EXIT WHEN cZonaLiderBaja%NOTFOUND;
                             BEGIN
                              SELECT COUNT(1)
                                INTO vsRegiBajas
                                FROM LET_CORPO_LIDER_BAJA
                               WHERE COD_CLIE_LIDE = vsCodLiderBaja AND
                                     CAM_BAJA = psCodigoPeriodo;
                              EXCEPTION
                                WHEN NO_DATA_FOUND THEN
                                     vsRegiBajas := 0;
                             END;

                             IF (vsRegiBajas = 0) THEN
                                 INSERT INTO LET_CORPO_LIDER_BAJA
                                    (COD_CLIE_LIDE, CAM_BAJA,
                                     LTDE_COD_TIPO_DESV, IND_TIPO_PROC,
                                     USU_CREA, FEC_CREA,
                                     IND_ACTI
                                     )
                                  VALUES  (vsCodLiderBaja, psCodigoPeriodo,
                                           6, psTipoProceso,
                                           psUsuario, SYSDATE,
                                           '1'
                                          );
                             ELSE
                                UPDATE LET_CORPO_LIDER_BAJA
                                   SET LTDE_COD_TIPO_DESV = 6,
                                      IND_TIPO_PROC = psTipoProceso,
                                      USU_MODI = psUsuario,
                                      FEC_MODI = SYSDATE,
                                      IND_ACTI = '1'
                                    WHERE COD_CLIE_LIDE = vsCodLiderBaja
                                    AND CAM_BAJA = psCodigoPeriodo;

                             END IF;

                             IF psTipoProceso = 'P' THEN
                               --Cerramos la clasificaci�n let vigente
                               LET_PKG_PROCE.LET_PR_PROCE_CLASI_LIDER(psCodigoPais,
                                                                                  psCodigoMarca,
                                                                                  psCodigoCanal,
                                                                                  '2',
                                                                                  vsCodLiderBaja,
                                                                                  psCodigoPeriodo,
                                                                                  psUsuario
                                                                                 );
                               --eliminamos la clasificaci�n mae asignada
                               DELETE FROM mae_clien_clasi cc
                                     WHERE cc.oid_clie_clas IN ( SELECT mcc.oid_clie_clas
                                                                   FROM mae_clien c,
                                                                        mae_clien_tipo_subti mcts,
                                                                        mae_clien_clasi mcc,
                                                                        mae_tipo_clasi_clien mtcc,
                                                                        mae_clasi mc
                                                                  WHERE c.oid_clie              = mcts.clie_oid_clie AND
                                                                        mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt AND
                                                                        mcc.tccl_oid_tipo_clasi = mtcc.oid_tipo_clas AND
                                                                        mcc.clas_oid_clas       = mc.oid_clas AND
                                                                        mtcc.sbti_oid_subt_clie = mcts.sbti_oid_subt_clie AND
                                                                        mtcc.cod_tipo_clas      = '01' AND -- Tipo Clasificacion Lider
                                                                        mc.tccl_oid_tipo_clas   = mtcc.oid_tipo_clas AND
                                                                        mc.cod_clas             = '01' AND -- Clasificacion Lider
                                                                        c.cod_clie              = vsCodLiderBaja
                                                                );
                              --Eliminamos Lider de la Secci�n
                              UPDATE zon_secci zs
                                 SET zs.clie_oid_clie = NULL
                               WHERE zs.zzon_oid_zona = (SELECT zz.oid_zona
                                                           FROM zon_zona zz
                                                          WHERE zz.cod_zona = vsCodZona
                                                         ) AND
                                     zs.cod_secc      = vsCodSecc AND
                                     zs.clie_oid_clie = (SELECT c.oid_clie
                                                           FROM mae_clien c
                                                          WHERE c.cod_clie = vsCodLiderBaja
                                                        );
                              --Cerramos la Historia
                              UPDATE zon_histo_geren zhg
                                 SET zhg.fec_hast           = (SELECT bcf.fec_proc
                                                                 FROM bas_ctrl_fact bcf
                                                                WHERE bcf.cod_peri = psCodigoPeriodo
                                                              ),
                                     zhg.perd_oid_peri_hast = vnOidPeriodo,
                                     zhg.ind_desv_auto      = 1,
                                     zhg.fec_modi           = SYSDATE,
                                     zhg.usu_modi           = psUsuario
                               WHERE zhg.gere = vsCodLiderBaja AND
                                     zhg.cod_secc IS NOT NULL AND
                                     vnOidPeriodo BETWEEN zhg.perd_oid_peri_desd AND nvl(zhg.perd_oid_peri_hast,vnOidPeriodo);

                             END IF;
                       END LOOP;
                      CLOSE cZonaLiderBaja;

                     ELSE
                       OPEN cZonaLiderBajaRanking(psCodigoPais,psCodigoPeriodo,vsNumCampBajaAuto,vsCodZona);
                         LOOP FETCH cZonaLiderBajaRanking INTO vsCodLiderBaja,vnCantibajas,vsPorcCriti,vsCodSecc;
                           EXIT WHEN cZonaLiderBajaRanking%ROWCOUNT=vsNumLiderBajaAutoZona OR cZonaLiderBajaRanking%NOTFOUND;
                                                        BEGIN
                              SELECT COUNT(1)
                                INTO vsRegiBajas
                                FROM LET_CORPO_LIDER_BAJA
                               WHERE COD_CLIE_LIDE = vsCodLiderBaja AND
                                     CAM_BAJA = psCodigoPeriodo;
                              EXCEPTION
                                WHEN NO_DATA_FOUND THEN
                                     vsRegiBajas := 0;
                             END;

                             IF (vsRegiBajas = 0) THEN
                                 INSERT INTO LET_CORPO_LIDER_BAJA
                                    (COD_CLIE_LIDE, CAM_BAJA,
                                     LTDE_COD_TIPO_DESV, IND_TIPO_PROC,
                                     USU_CREA, FEC_CREA,
                                     IND_ACTI
                                     )
                                  VALUES  (vsCodLiderBaja, psCodigoPeriodo,
                                           6, psTipoProceso,
                                           psUsuario, SYSDATE,
                                           '1'
                                          );
                             ELSE
                                UPDATE LET_CORPO_LIDER_BAJA
                                   SET LTDE_COD_TIPO_DESV = 6,
                                      IND_TIPO_PROC = psTipoProceso,
                                      USU_MODI = psUsuario,
                                      FEC_MODI = SYSDATE,
                                      IND_ACTI = '1'
                                    WHERE COD_CLIE_LIDE = vsCodLiderBaja
                                    AND CAM_BAJA = psCodigoPeriodo;

                             END IF;

                             IF psTipoProceso = 'P' THEN
                               --Cerramos la clasificaci�n let vigente
                               LET_PKG_PROCE.LET_PR_PROCE_CLASI_LIDER(psCodigoPais,
                                                                                  psCodigoMarca,
                                                                                  psCodigoCanal,
                                                                                  '2',
                                                                                  vsCodLiderBaja,
                                                                                  psCodigoPeriodo,
                                                                                  psUsuario
                                                                                 );
                               --eliminamos la clasificaci�n mae asignada
                               DELETE FROM mae_clien_clasi cc
                                     WHERE cc.oid_clie_clas IN ( SELECT mcc.oid_clie_clas
                                                                   FROM mae_clien c,
                                                                        mae_clien_tipo_subti mcts,
                                                                        mae_clien_clasi mcc,
                                                                        mae_tipo_clasi_clien mtcc,
                                                                        mae_clasi mc
                                                                  WHERE c.oid_clie              = mcts.clie_oid_clie AND
                                                                        mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt AND
                                                                        mcc.tccl_oid_tipo_clasi = mtcc.oid_tipo_clas AND
                                                                        mcc.clas_oid_clas       = mc.oid_clas AND
                                                                        mtcc.sbti_oid_subt_clie = mcts.sbti_oid_subt_clie AND
                                                                        mtcc.cod_tipo_clas      = '01' AND -- Tipo Clasificacion Lider
                                                                        mc.tccl_oid_tipo_clas   = mtcc.oid_tipo_clas AND
                                                                        mc.cod_clas             = '01' AND -- Clasificacion Lider
                                                                        c.cod_clie              = vsCodLiderBaja
                                                                );
                              --Eliminamos Lider de la Secci�n
                              UPDATE zon_secci zs
                                 SET zs.clie_oid_clie = NULL
                               WHERE zs.zzon_oid_zona = (SELECT zz.oid_zona
                                                           FROM zon_zona zz
                                                          WHERE zz.cod_zona = vsCodZona
                                                         ) AND
                                     zs.cod_secc      = vsCodSecc AND
                                     zs.clie_oid_clie = (SELECT c.oid_clie
                                                           FROM mae_clien c
                                                          WHERE c.cod_clie = vsCodLiderBaja
                                                        );
                              --Cerramos la Historia
                              UPDATE zon_histo_geren zhg
                                 SET zhg.fec_hast           = (SELECT bcf.fec_proc
                                                                 FROM bas_ctrl_fact bcf
                                                                WHERE bcf.cod_peri = psCodigoPeriodo
                                                              ),
                                     zhg.perd_oid_peri_hast = vnOidPeriodo,
                                     zhg.ind_desv_auto      = 1,
                                     zhg.fec_modi           = SYSDATE,
                                     zhg.usu_modi           = psUsuario
                               WHERE zhg.gere = vsCodLiderBaja AND
                                     zhg.cod_secc IS NOT NULL AND
                                     vnOidPeriodo BETWEEN zhg.perd_oid_peri_desd AND nvl(zhg.perd_oid_peri_hast,vnOidPeriodo);

                             END IF;

                         END LOOP;

                       CLOSE cZonaLiderBajaRanking;

                    END IF;

         END LOOP;
      CLOSE cZonaBaja;
     /*
      --open Cursor LiderBaja
      OPEN cLideresBaja(psCodigoPeriodo, vsCodProg, psCodigoPais);
         LOOP FETCH cLideresBaja INTO vsCodLiderBaja, vsCodSeccBaja, vsPorcCriti, vsIndEnviBaja;
              EXIT WHEN cLiderActivo%NOTFOUND;

                  IF psTipoProceso <> 'C' THEN
                     IF vsNumLiderBajaAutoZona < 2 THEN
           UPDATE LET_CORPO_LIDER_BAJA
                           SET LTDE_COD_TIPO_DESV = 6, --Codigo Tipo Desvinculaci�n para LET_CORPO_LIDER_BAJA
                     IND_TIPO_PROC = psTipoProceso,
                     USU_MODI = psUsuario,
                     FEC_MODI = SYSDATE,
                               IND_ACTI = '1'
                            WHERE COD_CLIE_LIDE = vsCodLiderBaja
                            AND CAM_BAJA = psCodigoPeriodo;
                     ELSE
                       vsPorcCriti := vsPorcCriti + 1;

                      UPDATE LET_CORPO_SECCI_RESUL
                           SET VAL_PORC_CRIT = vsPorcCriti,
                               USU_MODI = psUsuario,
                               FEC_MODI = SYSDATE
                           WHERE CAM_OBJE = psCodigoPeriodo
                              AND COD_CLIE_LIDE = vsCodLiderBaja
                              AND COD_SECC = vsCodSeccBaja;
                     END IF;

                  ELSE

                     UPDATE ZON_HISTO_GEREN
                           SET PERD_OID_PERI_HAST = vnOidPeriodo,
                               USU_MODI = psUsuario,
                               FEC_MODI = SYSDATE
                            WHERE vnOidPeriodo >= PERD_OID_PERI_DESD
                            AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
                            AND COD_SECC IS NOT NULL
                            AND GERE = vsCodLiderBaja
                            AND IND_DESV_AUTO = 6; --Indicador de desvinculado automticamente para ZON_HISTO_GEREN
                  END IF;
         END LOOP;
      CLOSE cLideresBaja;
*/
--     END IF;

   END;
  END IF;

    EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_CALCU_BAJA_LIDER: ' || ls_sqlerrm);
    END LET_PR_CALCU_BAJA_LIDER;

/***********************************************************************************************
Descripcion       : Realiza el Despacho de premios
Fecha Creacion    : 07/10/2013
Autor             : Aurelio Oviedo
***********************************************************************************************/
PROCEDURE LET_PR_DESPA_PREMI(psCodigoPais    VARCHAR2,
                             psCodigoMarca   VARCHAR2,
                             psCodigoCanal   VARCHAR2,
                             psCodigoPeriodo VARCHAR2,
                             psUsuario       VARCHAR2) IS

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  W_FILAS NUMBER := 5000;
  vsCampanaSiguiente      SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCodigoConcurso        LET_CORPO_PARAM_PROGR.COD_PROG%TYPE;
    vsCampanaFinConcu       LET_CORPO_PARAM_PROGR.CAM_FIN%TYPE;
    vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
    vnOidMarca              SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal              SEG_CANAL.OID_CANA%TYPE;
    vnOidPeriodoSiguiente   SEG_PERIO_CORPO.OID_PERI%TYPE;
    vsResultado             NUMBER;
  vsPremPed               NUMBER;
  vshayPremio             NUMBER;
    vnOidTipoSoliPais       LET_PARAM_GENER.OID_TIPO_SOLI%TYPE;
  vsIndPrem                  LET_CORPO_RANGO_RETEN.IND_PREM%TYPE;
    vnFormaPagoEnv          PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO%TYPE;
    vnClaseSolicEnv         PED_CLASE_SOLIC.OID_CLAS_SOLI%TYPE;
    vnOidAlmacEnv           PED_TIPO_SOLIC_PAIS.ALMC_OID_ALMA%TYPE;
    vnTipoSoliCons          PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS%TYPE;
    vnTipoDocum2            PED_TIPO_SOLIC_PAIS.TIDO_OID_TIPO_DOCU%TYPE;
    vnSubac                 PED_TIPO_SOLIC.SBAC_OID_SBAC%TYPE;
    vnSocie                 PED_TIPO_SOLIC_PAIS.SOCI_OID_SOCI%TYPE;
    vnCodNivelExitoLider    LET_CORPO_LIDER_NIVEL.COD_NIVE%TYPE;
    vnCodigoPremio          LET_CORPO_PARAM_PREMI_PROGR.VAL_CODI_VENT_INCE%TYPE;
    vnOidProducto           PRE_OFERT_DETAL.PROD_OID_PROD%TYPE;
    vnImpPrecPosi           PRE_OFERT_DETAL.IMP_PREC_POSI%TYPE;
    vnOidDetalleOferta      PRE_OFERT_DETAL.OID_DETA_OFER%TYPE;
    vnNumeSoli              PED_NUMER_SOLIC.VAL_ULTI_NUME_SOLI%TYPE;
    vnFecFinPerio           CRA_PERIO.FEC_FINA%TYPE;
    vnOidEjecutiva          MAE_CLIEN.OID_CLIE%TYPE;
    vnOidFormPago           MAE_CLIEN.FOPA_OID_FORM_PAGO%TYPE;
    vnOidTipoDocu           MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU%TYPE;
    vnOidClieDire           MAE_CLIEN_DIREC.OID_CLIE_DIRE%TYPE;
    vnOidTerr               ZON_TERRI_ADMIN.TERR_OID_TERR%TYPE;
    vnOidTerrAdmi           MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI%TYPE;
    vnOidValorEstrGeop      ZON_TERRI.VEPO_OID_VALO_ESTR_GEOP%TYPE;
    vnOidZona               ZON_ZONA.OID_ZONA%TYPE;
    vnOidTipoCliente        MAE_TIPO_CLIEN.OID_TIPO_CLIE%TYPE;
    vnOidSubTipoCliente     MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;
    vnOidSolicCabec         NUMBER;
  vnNumeroCabeceras       NUMBER;
  vnNumSoliInicio         NUMBER;
  vnNumSoliFormato        NUMBER;
  vnPosic                 NUMBER;

  CURSOR c_SeccionResultado (
     codigoConcurso  LET_CORPO_PARAM_PROGR.COD_PROG%TYPE)
  IS
     SELECT COD_PROG,
            COD_CLIE_LIDE,
            COD_REGI,
            COD_ZONA,
            COD_SECC,
            COD_ESTA_OBJE_PEDI,
            COD_ESTA_OBJE_INGR,
            COD_ESTA_OBJE_R33,
            COD_ESTA_OBJE_R44,
            NUM_INGR_REAL,
            NUM_INGR_33_REAL,
            NUM_INGR_44_REAL
       FROM  LET_CORPO_SECCI_RESUL
      WHERE     COD_PROG = codigoConcurso
            AND CAM_OBJE = psCodigoPeriodo
            AND  NVL(IND_ENTR_PREM,0) = 0
            AND (   COD_ESTA_OBJE_PEDI IN ('01', '02', '03')
                 OR COD_ESTA_OBJE_INGR = '05'
                 OR COD_ESTA_OBJE_R33 = '07'
                 OR COD_ESTA_OBJE_R44 = '09');

  TYPE seccionResultadoRecord IS RECORD
  (
     COD_PROG              LET_CORPO_SECCI_RESUL.COD_PROG%TYPE,
     COD_CLIE_LIDE         LET_CORPO_SECCI_RESUL.COD_CLIE_LIDE%TYPE,
     COD_REGI              LET_CORPO_SECCI_RESUL.COD_REGI%TYPE,
     COD_ZONA              LET_CORPO_SECCI_RESUL.COD_ZONA%TYPE,
     COD_SECC              LET_CORPO_SECCI_RESUL.COD_SECC%TYPE,
     COD_ESTA_OBJE_PEDI    LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_PEDI%TYPE,
     COD_ESTA_OBJE_INGR    LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_INGR%TYPE,
     COD_ESTA_OBJE_R33     LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_R33%TYPE,
     COD_ESTA_OBJE_R44    LET_CORPO_SECCI_RESUL.COD_ESTA_OBJE_R44%TYPE,
     NUM_INGR_REAL   LET_CORPO_SECCI_RESUL.NUM_INGR_REAL%TYPE,     -----yo
     NUM_INGR_33_REAL  LET_CORPO_SECCI_RESUL.NUM_INGR_33_REAL%TYPE,  -----yo
     NUM_INGR_44_REAL  LET_CORPO_SECCI_RESUL.NUM_INGR_44_REAL%TYPE
  );

  TYPE seccionResultadoTab IS TABLE OF seccionResultadoRecord;

  seccionResultado        seccionResultadoTab;

   CURSOR c_PremiosCampana  (
       vnCodNivelExitoLider  LET_CORPO_PARAM_PREMI_PROGR.NIVE_COD_NIVE%TYPE,
       vnTipoPremio  LET_CORPO_PARAM_PREMI_PROGR.TIP_PREM%TYPE)
   IS
            SELECT VAL_CODI_VENT_INCE
             FROM  LET_CORPO_PARAM_PREMI_PROGR
             WHERE     COD_PROG = vsCodigoConcurso
             AND   CAM_DESP = vsCampanaSiguiente
             AND   NIVE_COD_NIVE = vnCodNivelExitoLider
             AND   TIP_PREM = vnTipoPremio;

  TYPE PremiosCampanaRecord IS RECORD
  (
     codigoPremio    LET_CORPO_PARAM_PREMI_PROGR.NIVE_COD_NIVE%TYPE
  );

  TYPE PremiosCampanaTab IS TABLE OF PremiosCampanaRecord;

  PremiosCampana        PremiosCampanaTab;
BEGIN
    vnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    vnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
      vsCampanaSiguiente :=
         PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodigoPeriodo,
                                                 vnOidPais,
                                                 vnOidMarca,
                                                 vnOidCanal,
                                                 1);
      vnOidPeriodoSiguiente :=
         GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO (vsCampanaSiguiente,
                                                     vnOidMarca,
                                                     vnOidCanal);

    BEGIN
        SELECT COD_PROG, CAM_FIN
          INTO vsCodigoConcurso, vsCampanaFinConcu
          FROM LET_CORPO_PARAM_PROGR
      WHERE psCodigoPeriodo >= CAM_INIC
            AND (psCodigoPeriodo <= CAM_FIN OR CAM_FIN IS NULL);
    EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            vsCodigoConcurso := NULL;
            vsCampanaFinConcu := NULL;
    END;

      IF vsCodigoConcurso IS NOT NULL
      THEN
        SELECT COUNT(1)
          INTO vsResultado
          FROM LET_CORPO_PARAM_PREMI_PROGR
          WHERE COD_PROG = vsCodigoConcurso AND CAM_DESP = vsCampanaSiguiente;

         IF vsResultado > 0
         THEN
            BEGIN
                 SELECT OID_TIPO_SOLI
                   INTO vnOidTipoSoliPais
                   FROM LET_PARAM_GENER
                  WHERE PAIS_COD_PAIS = psCodigoPais;
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                    vnOidTipoSoliPais := 0;
            END;

            SELECT  COUNT (1)
            INTO   vnNumeroCabeceras
            FROM    LET_CORPO_SECCI_RESUL
            WHERE  COD_PROG = vscodigoConcurso
            AND    CAM_OBJE = psCodigoPeriodo
            AND     NVL(IND_ENTR_PREM,0) = 0
            AND (   COD_ESTA_OBJE_PEDI IN ('01', '02', '03')
                 OR COD_ESTA_OBJE_INGR = '05'
                 OR COD_ESTA_OBJE_R33 = '07'
                 OR COD_ESTA_OBJE_R44 = '09');

            BEGIN
                SELECT a.FOPA_OID_FORM_PAGO,
                       d.OID_CLAS_SOLI,
                       a.ALMC_OID_ALMA,
                       a.TSOL_OID_TIPO_CONS,
                       a.TIDO_OID_TIPO_DOCU,
                       c.SBAC_OID_SBAC,
                       a.SOCI_OID_SOCI
                  INTO vnFormaPagoEnv,
                       vnClaseSolicEnv,
                       vnOidAlmacEnv,
                       vnTipoSoliCons,
                       vnTipoDocum2,
                       vnSubac,
                       vnSocie
                  FROM PED_TIPO_SOLIC_PAIS a,
                       PED_TIPO_SOLIC      c,
                       PED_CLASE_SOLIC     d
                 WHERE a.OID_TIPO_SOLI_PAIS = vnOidTipoSoliPais
                   AND a.TSOL_OID_TIPO_SOLI = c.OID_TIPO_SOLI
                   AND c.CLSO_OID_CLAS_SOLI = d.OID_CLAS_SOLI;
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                    vnFormaPagoEnv := NULL;
                    vnClaseSolicEnv := NULL;
                    vnOidAlmacEnv := NULL;
                    vnTipoSoliCons := NULL;
                    vnTipoDocum2 := NULL;
                    vnSubac := NULL;
                    vnSocie := NULL;
            END;

            vnNumSoliInicio :=
               LeT_PKG_PROCE.LET_FN_RESRV_SECUE_NSOLI (psCodigoPais,
                                         'PED001',
                                         'GZ',
                                         '000',
                                         psCodigoCanal,
                                         vnNumeroCabeceras);
            vnNumSoliFormato :=
               TO_CHAR (SYSDATE, 'yy') || LPAD (vnNumSoliInicio + 1, 8, '0');

            SELECT TO_DATE ( (TO_CHAR (FEC_FINA, 'DD/MM/YYYY')),
                            'DD/MM/YYYY')
              INTO vnFecFinPerio
              FROM CRA_PERIO
              WHERE OID_PERI = vnOidPeriodoSiguiente;

            OPEN c_SeccionResultado(vsCodigoConcurso);

                LOOP
               FETCH c_SeccionResultado
               BULK COLLECT INTO seccionResultado
               LIMIT W_FILAS;

               IF seccionResultado.COUNT > 0
               THEN
                  FOR i IN seccionResultado.FIRST .. seccionResultado.LAST
                  LOOP
                        BEGIN
                            SELECT COD_NIVE
                              INTO vnCodNivelExitoLider
                              FROM LET_CORPO_LIDER_NIVEL
                         WHERE COD_CLIE_LIDE =
                                  seccionResultado (i).COD_CLIE_LIDE
                               AND psCodigoPeriodo >= CAM_INIC
                               AND (psCodigoPeriodo <= CAM_FIN
                                    OR CAM_FIN IS NULL)
                               AND IND_ACTI = 1
                               AND IND_TIPO_NIVE = 'R';
                        EXCEPTION
                        WHEN NO_DATA_FOUND
                        THEN
                                vnCodNivelExitoLider := NULL;
                        END;

                     SELECT OID_CLIE, FOPA_OID_FORM_PAGO
                       INTO vnOidEjecutiva, vnOidFormPago
                          FROM MAE_CLIEN
                         WHERE PAIS_OID_PAIS = vnOidPais
                           AND COD_CLIE = seccionResultado(i).COD_CLIE_LIDE;

                     IF vnFormaPagoEnv IS NULL
                     THEN
                        IF vnOidFormPago IS NOT NULL
                        THEN
                                vnFormaPagoEnv := vnOidFormPago;
                            ELSE
                                SELECT fopa_oid_form_pago
                                  INTO vnFormaPagoEnv
                                  FROM seg_pais a
                                 WHERE a.oid_pais = vnOidPais;
                            END IF;
                        END IF;

                        BEGIN
                            SELECT val_tipo_camb
                              INTO vnOidFormPago
                              FROM pre_matri_factu_cabec a
                             WHERE a.perd_oid_peri = vnOidPeriodoSiguiente;
                        EXCEPTION
                        WHEN NO_DATA_FOUND
                        THEN
                                vnOidFormPago := '';
                        END;

                        SELECT OID_CLIE_DIRE
                          INTO vnOidClieDire
                          FROM MAE_CLIEN_DIREC
                         WHERE CLIE_OID_CLIE = vnOidEjecutiva
                           AND IND_DIRE_PPAL = 1
                           AND IND_ELIM = 0;

                        SELECT TDOC_OID_TIPO_DOCU
                          INTO vnOidTipoDocu
                          FROM MAE_CLIEN_IDENT
                         WHERE CLIE_OID_CLIE = vnOidEjecutiva
                           AND VAL_IDEN_DOCU_PRIN = 1;

                 SELECT ztad.TERR_OID_TERR,
                        mcua.ZTAD_OID_TERR_ADMI,
                        scc.zzon_oid_zona,
                        trr.vepo_oid_valo_estr_geop
                   INTO vnOidTerr,
                        vnOidTerrAdmi,
                        vnOidZona,
                        vnOidValorEstrGeop
                   FROM MAE_CLIEN_UNIDA_ADMIN mcua,
                        ZON_TERRI_ADMIN ztad,
                        ZON_SECCI scc,
                        ZON_TERRI trr
                         WHERE ztad.PAIS_OID_PAIS = vnOidPais
                           AND mcua.Ztad_Oid_Terr_Admi = ztad.oid_terr_admi
                           AND mcua.CLIE_OID_CLIE = vnOidEjecutiva
                            AND mcua.IND_ACTI = 1
                            AND ztad.zscc_oid_secc = scc.oid_secc
                            AND ztad.terr_oid_terr = trr.oid_terr;

                     SELECT TICL_OID_TIPO_CLIE, SBTI_OID_SUBT_CLIE
                       INTO vnOidTipoCliente, vnOidSubTipoCliente
                          FROM MAE_CLIEN_TIPO_SUBTI
                      WHERE CLIE_OID_CLIE = vnOidEjecutiva AND IND_PPAL = 1;

                        SELECT PED_SOCA_SEQ.NEXTVAL
                          INTO vnOidSolicCabec
                          FROM DUAL;

                 IF seccionResultado (i).COD_ESTA_OBJE_PEDI IN
                       ('01', '02', '03')
                 THEN
                     INSERT
                       INTO PED_SOLIC_CABEC (OID_SOLI_CABE,
                            FEC_PROG_FACT,
                            TSPA_OID_TIPO_SOLI_PAIS,
                            TIDS_OID_TIPO_DESP,
                            ALMC_OID_ALMA,
                            MODU_OID_MODU,
                            TICL_OID_TIPO_CLIE,
                            PERD_OID_PERI,
                            CLIE_OID_CLIE,
                            CLIE_OID_CLIE_RECE_FACT,
                            CLIE_OID_CLIE_PAGA,
                            CLIE_OID_CLIE_DEST,
                            CLDI_OID_CLIE_DIRE,
                            TDOC_OID_TIPO_DOCU,
                            SOCI_OID_SOCI,
                            SBAC_OID_SBAC,
                            TERR_OID_TERR,
                            ZZON_OID_ZONA,
                            VAL_NUME_SOLI,
                            FEC_CRON,
                            IND_PERM_UNIO_SOL,
                            NUM_DOCU_ORIG,
                            IND_TS_NO_CONSO,
                            PAIS_OID_PAIS,
                            TIDO_OID_TIPO_DOCU,
                            VEPO_OID_VALO_ESTR_GEOP,
                            ESSO_OID_ESTA_SOLI,
                            COPA_OID_PARA_GENE,
                            GRPR_OID_GRUP_PROC,
                            SBTI_OID_SUBT_CLIE,
                            TSPA_OID_TIPO_SOLI_PAIS_CONS,
                            FOPA_OID_FORM_PAGO,
                            CLSO_OID_CLAS_SOLI,
                            ZTAD_OID_TERR_ADMI,
                            OPER_OID_OPER,
                            PROC_OID_PROC,
                            SOCA_OID_DOCU_REFE,
                            ICTP_OID_TIPO_PROG,
                            VAL_TIPO_CAMB,
                            IND_OC)
                        VALUES(
                            vnOidSolicCabec,
                            vnFecFinPerio,
                            vnOidTipoSoliPais,
                            1,
                            vnOidAlmacEnv,
                            1,
                            vnOidTipoCliente,
                            vnOidPeriodoSiguiente,
                            vnOidEjecutiva,
                            vnOidEjecutiva,
                            vnOidEjecutiva,
                            vnOidEjecutiva,
                            vnOidClieDire,
                            vnOidTipoDocu,
                            vnSocie,
                            vnSubac,
                            vnOidTerr,
                            vnOidZona,
                               vnNumSoliFormato,
                               TO_DATE ( (TO_CHAR (SYSDATE, 'dd/MM/yyyy')),
                                        'dd/MM/yyyy'),
                            1,
                            NULL,
                            1,
                            vnOidPais,
                            30,
                            vnOidValorEstrGeop,
                            1,
                            NULL,
                            3,
                            vnOidSubTipoCliente,
                            vnTipoSoliCons,
                            vnFormaPagoEnv,
                            vnClaseSolicEnv,
                            vnOidTerrAdmi,
                            21,
                            1,
                            NULL,
                            NULL,
                            vnOidFormPago,
                            0);

                          vnPosic := 1;

                          OPEN c_PremiosCampana (vnCodNivelExitoLider,'PE');

                          LOOP
                             FETCH c_PremiosCampana INTO vnCodigoPremio;

                             EXIT WHEN c_PremiosCampana%NOTFOUND;

                                    SELECT ofedet.PROD_OID_PROD,
                                           ofedet.IMP_PREC_POSI,
                                           ofedet.OID_DETA_OFER
                                      INTO vnOidProducto,
                                           vnImpPrecPosi,
                                           vnOidDetalleOferta
                                      FROM PRE_OFERT ofe,
                                           PRE_OFERT_DETAL ofedet,
                                           PRE_MATRI_FACTU mf,
                                           PRE_MATRI_FACTU_CABEC mfc,
                                           MAE_PRODU prod,
                                           (SELECT V.VAL_OID, V.VAL_I18N
                                              FROM V_GEN_I18N_SICC V
                                             WHERE V.ATTR_ENTI = 'MAE_PRODU'
                                               AND V.IDIO_OID_IDIO = 1) i18prod
                        WHERE mfc.PERD_OID_PERI = vnOidPeriodoSiguiente
                                       AND mf.MFCA_OID_CABE = mfc.OID_CABE
                                       AND ofe.MFCA_OID_CABE = mfc.OID_CABE
                                       AND ofe.OID_OFER = ofedet.OFER_OID_OFER
                                             AND ofedet.OID_DETA_OFER =
                                                    mf.OFDE_OID_DETA_OFER
                              AND ofedet.VAL_CODI_VENT = vnCodigoPremio
                                       AND ofedet.PROD_OID_PROD = prod.OID_PROD
                                       AND prod.OID_PROD = i18prod.VAL_OID;

                                      INSERT
                                        INTO PED_SOLIC_POSIC (OID_SOLI_POSI,
                                        COD_POSI,
                                        NUM_UNID_DEMA,
                                        NUM_UNID_POR_ATEN,
                                        VAL_TASA_IMPU,
                                        SOCA_OID_SOLI_CABE,
                                        TPOS_OID_TIPO_POSI,
                                        PROD_OID_PROD,
                                        VAL_PREC_CATA_UNIT_LOCA,
                                        VAL_PREC_CONT_UNIT_LOCA,
                                        VAL_PREC_CATA_UNIT_DOCU,
                                        VAL_PREC_CONTA_UNIT_DOCU,
                                        NUM_UNID_COMPR,
                                        NUM_UNID_DEMA_REAL,
                                        ESPO_OID_ESTA_POSI,
                                        STPO_OID_SUBT_POSI,
                                        VAL_CODI_VENT,
                                        OFDE_OID_DETA_OFER)
                                      VALUES (PED_SOPO_SEQ.NEXTVAL,
                                              vnPosic,
                                        1,
                                        1,
                                        0,
                                        vnOidSolicCabec,
                                        9,
                                        vnOidProducto,
                                        0,
                                        vnImpPrecPosi,
                                        0,
                                        vnImpPrecPosi,
                                        1,
                                        1,
                                        4,
                                        13,
                                        vnCodigoPremio,
                                        vnOidDetalleOferta);

                                        vnPosic := vnPosic + 1;
                                  END LOOP;

                          CLOSE c_PremiosCampana;
                                END IF;

                     IF seccionResultado (i).COD_ESTA_OBJE_INGR = '05'
                     THEN
                    SELECT nvl(rr.IND_PREM,0)
                      INTO vsIndPrem
                      FROM LET_CORPO_RANGO_RETEN rr
                     WHERE     rr.COD_PROG = vsCodigoConcurso
                           AND seccionResultado (i).NUM_INGR_REAL >= rr.NUM_INGR_INIC
                           AND seccionResultado (i).NUM_INGR_REAL <= rr.NUM_INGR_FINA;
                    IF  vsIndPrem = 1
                    THEN
                       SELECT COUNT(1)
                       INTO      vsPremPed   FROM PED_SOLIC_CABEC
                       WHERE  TSPA_OID_TIPO_SOLI_PAIS = vnOidTipoSoliPais
                       AND       PERD_OID_PERI = vnOidPeriodoSiguiente
                       AND       CLIE_OID_CLIE =  vnOidEjecutiva;
                       SELECT COUNT(1)
                       INTO      vshayPremio
                       FROM    LET_CORPO_PARAM_PREMI_PROGR
                       WHERE   COD_PROG = vsCodigoConcurso
                       AND       CAM_DESP = vsCampanaSiguiente
                       AND       TIP_PREM = 'R2';
                       IF   vsPremPed = 0 AND vshayPremio >= 1
                       THEN
                           INSERT
                           INTO PED_SOLIC_CABEC (OID_SOLI_CABE,
                                                 FEC_PROG_FACT,
                                                 TSPA_OID_TIPO_SOLI_PAIS,
                                                 TIDS_OID_TIPO_DESP,
                                                 ALMC_OID_ALMA,
                                                 MODU_OID_MODU,
                                                 TICL_OID_TIPO_CLIE,
                                                 PERD_OID_PERI,
                                                 CLIE_OID_CLIE,
                                                 CLIE_OID_CLIE_RECE_FACT,
                                                 CLIE_OID_CLIE_PAGA,
                                                 CLIE_OID_CLIE_DEST,
                                                 CLDI_OID_CLIE_DIRE,
                                                 TDOC_OID_TIPO_DOCU,
                                                 SOCI_OID_SOCI,
                                                 SBAC_OID_SBAC,
                                                 TERR_OID_TERR,
                                                 ZZON_OID_ZONA,
                                                 VAL_NUME_SOLI,
                                                 FEC_CRON,
                                                 IND_PERM_UNIO_SOL,
                                                 NUM_DOCU_ORIG,
                                                 IND_TS_NO_CONSO,
                                                 PAIS_OID_PAIS,
                                                 TIDO_OID_TIPO_DOCU,
                                                 VEPO_OID_VALO_ESTR_GEOP,
                                                 ESSO_OID_ESTA_SOLI,
                                                 COPA_OID_PARA_GENE,
                                                 GRPR_OID_GRUP_PROC,
                                                 SBTI_OID_SUBT_CLIE,
                                                 TSPA_OID_TIPO_SOLI_PAIS_CONS,
                                                 FOPA_OID_FORM_PAGO,
                                                 CLSO_OID_CLAS_SOLI,
                                                 ZTAD_OID_TERR_ADMI,
                                                 OPER_OID_OPER,
                                                 PROC_OID_PROC,
                                                 SOCA_OID_DOCU_REFE,
                                                 ICTP_OID_TIPO_PROG,
                                                 VAL_TIPO_CAMB,
                                                 IND_OC)
                         VALUES (
                                   vnOidSolicCabec,
                                   vnFecFinPerio,
                                   vnOidTipoSoliPais,
                                   1,
                                   vnOidAlmacEnv,
                                   1,
                                   vnOidTipoCliente,
                                   vnOidPeriodoSiguiente,
                                   vnOidEjecutiva,
                                   vnOidEjecutiva,
                                   vnOidEjecutiva,
                                   vnOidEjecutiva,
                                   vnOidClieDire,
                                   vnOidTipoDocu,
                                   vnSocie,
                                   vnSubac,
                                   vnOidTerr,
                                   vnOidZona,
                                   vnNumSoliFormato,
                                   TO_DATE ( (TO_CHAR (SYSDATE, 'dd/MM/yyyy')),
                                            'dd/MM/yyyy'),
                                   1,
                                   NULL,
                                   1,
                                   vnOidPais,
                                   30,
                                   vnOidValorEstrGeop,
                                   1,
                                   NULL,
                                   3,
                                   vnOidSubTipoCliente,
                                   vnTipoSoliCons,
                                   vnFormaPagoEnv,
                                   vnClaseSolicEnv,
                                   vnOidTerrAdmi,
                                   21,
                                   1,
                                   NULL,
                                   NULL,
                                   vnOidFormPago,
                                   0);
                                   vnPosic := 1;
                       END IF;
                       OPEN c_PremiosCampana (vnCodNivelExitoLider,'R2');

                          LOOP
                             FETCH c_PremiosCampana INTO vnCodigoPremio;

                             EXIT WHEN c_PremiosCampana%NOTFOUND;

                                    SELECT ofedet.PROD_OID_PROD,
                                           ofedet.IMP_PREC_POSI,
                                           ofedet.OID_DETA_OFER
                                      INTO vnOidProducto,
                                           vnImpPrecPosi,
                                           vnOidDetalleOferta
                                      FROM PRE_OFERT ofe,
                                           PRE_OFERT_DETAL ofedet,
                                           PRE_MATRI_FACTU mf,
                                           PRE_MATRI_FACTU_CABEC mfc,
                                           MAE_PRODU prod,
                                           (SELECT V.VAL_OID, V.VAL_I18N
                                              FROM V_GEN_I18N_SICC V
                                             WHERE V.ATTR_ENTI = 'MAE_PRODU'
                                               AND V.IDIO_OID_IDIO = 1) i18prod
                            WHERE mfc.PERD_OID_PERI = vnOidPeriodoSiguiente
                                       AND mf.MFCA_OID_CABE = mfc.OID_CABE
                                       AND ofe.MFCA_OID_CABE = mfc.OID_CABE
                                       AND ofe.OID_OFER = ofedet.OFER_OID_OFER
                                             AND ofedet.OID_DETA_OFER =
                                                    mf.OFDE_OID_DETA_OFER
                                  AND ofedet.VAL_CODI_VENT = vnCodigoPremio
                                       AND ofedet.PROD_OID_PROD = prod.OID_PROD
                                       AND prod.OID_PROD = i18prod.VAL_OID;

                                      INSERT
                                        INTO PED_SOLIC_POSIC (OID_SOLI_POSI,
                                        COD_POSI,
                                        NUM_UNID_DEMA,
                                        NUM_UNID_POR_ATEN,
                                        VAL_TASA_IMPU,
                                        SOCA_OID_SOLI_CABE,
                                        TPOS_OID_TIPO_POSI,
                                        PROD_OID_PROD,
                                        VAL_PREC_CATA_UNIT_LOCA,
                                        VAL_PREC_CONT_UNIT_LOCA,
                                        VAL_PREC_CATA_UNIT_DOCU,
                                        VAL_PREC_CONTA_UNIT_DOCU,
                                        NUM_UNID_COMPR,
                                        NUM_UNID_DEMA_REAL,
                                        ESPO_OID_ESTA_POSI,
                                        STPO_OID_SUBT_POSI,
                                        VAL_CODI_VENT,
                                        OFDE_OID_DETA_OFER)
                                      VALUES (PED_SOPO_SEQ.NEXTVAL,
                                              vnPosic,
                                        1,
                                        1,
                                        0,
                                        vnOidSolicCabec,
                                        9,
                                        vnOidProducto,
                                        0,
                                        vnImpPrecPosi,
                                        0,
                                        vnImpPrecPosi,
                                        1,
                                        1,
                                        4,
                                        13,
                                        vnCodigoPremio,
                                        vnOidDetalleOferta);

                                        vnPosic := vnPosic + 1;
                                  END LOOP;

                          CLOSE c_PremiosCampana;
                        END IF;
                 END IF;

                     IF seccionResultado (i).COD_ESTA_OBJE_R33 = '07'
                     THEN
                     SELECT nvl(rr.IND_PREM,0)
                     INTO vsIndPrem
                      FROM LET_CORPO_RANGO_RETEN rr
                      WHERE     rr.COD_PROG = vsCodigoConcurso
                      AND seccionResultado (i).NUM_INGR_33_REAL >= rr.NUM_INGR_INIC
                      AND seccionResultado (i).NUM_INGR_33_REAL <= rr.NUM_INGR_FINA;
                      IF  vsIndPrem = 1
                      THEN
                          SELECT COUNT(1)
                           INTO      vsPremPed   FROM PED_SOLIC_CABEC
                           WHERE  TSPA_OID_TIPO_SOLI_PAIS = vnOidTipoSoliPais
                           AND       PERD_OID_PERI = vnOidPeriodoSiguiente
                           AND       CLIE_OID_CLIE =  vnOidEjecutiva;
                           SELECT COUNT(1)
                           INTO      vshayPremio
                           FROM    LET_CORPO_PARAM_PREMI_PROGR
                           WHERE   COD_PROG = vsCodigoConcurso
                           AND       CAM_DESP = vsCampanaSiguiente
                           AND       TIP_PREM = 'R3';
                           IF   vsPremPed = 0 AND vshayPremio >= 1
                           THEN
                               INSERT
                               INTO PED_SOLIC_CABEC (OID_SOLI_CABE,
                                                     FEC_PROG_FACT,
                                                     TSPA_OID_TIPO_SOLI_PAIS,
                                                     TIDS_OID_TIPO_DESP,
                                                     ALMC_OID_ALMA,
                                                     MODU_OID_MODU,
                                                     TICL_OID_TIPO_CLIE,
                                                     PERD_OID_PERI,
                                                     CLIE_OID_CLIE,
                                                     CLIE_OID_CLIE_RECE_FACT,
                                                     CLIE_OID_CLIE_PAGA,
                                                     CLIE_OID_CLIE_DEST,
                                                     CLDI_OID_CLIE_DIRE,
                                                     TDOC_OID_TIPO_DOCU,
                                                     SOCI_OID_SOCI,
                                                     SBAC_OID_SBAC,
                                                     TERR_OID_TERR,
                                                     ZZON_OID_ZONA,
                                                     VAL_NUME_SOLI,
                                                     FEC_CRON,
                                                     IND_PERM_UNIO_SOL,
                                                     NUM_DOCU_ORIG,
                                                     IND_TS_NO_CONSO,
                                                     PAIS_OID_PAIS,
                                                     TIDO_OID_TIPO_DOCU,
                                                     VEPO_OID_VALO_ESTR_GEOP,
                                                     ESSO_OID_ESTA_SOLI,
                                                     COPA_OID_PARA_GENE,
                                                     GRPR_OID_GRUP_PROC,
                                                     SBTI_OID_SUBT_CLIE,
                                                     TSPA_OID_TIPO_SOLI_PAIS_CONS,
                                                     FOPA_OID_FORM_PAGO,
                                                     CLSO_OID_CLAS_SOLI,
                                                     ZTAD_OID_TERR_ADMI,
                                                     OPER_OID_OPER,
                                                     PROC_OID_PROC,
                                                     SOCA_OID_DOCU_REFE,
                                                     ICTP_OID_TIPO_PROG,
                                                     VAL_TIPO_CAMB,
                                                     IND_OC)
                             VALUES (
                                       vnOidSolicCabec,
                                       vnFecFinPerio,
                                       vnOidTipoSoliPais,
                                       1,
                                       vnOidAlmacEnv,
                                       1,
                                       vnOidTipoCliente,
                                       vnOidPeriodoSiguiente,
                                       vnOidEjecutiva,
                                       vnOidEjecutiva,
                                       vnOidEjecutiva,
                                       vnOidEjecutiva,
                                       vnOidClieDire,
                                       vnOidTipoDocu,
                                       vnSocie,
                                       vnSubac,
                                       vnOidTerr,
                                       vnOidZona,
                                       vnNumSoliFormato,
                                       TO_DATE ( (TO_CHAR (SYSDATE, 'dd/MM/yyyy')),
                                                'dd/MM/yyyy'),
                                       1,
                                       NULL,
                                       1,
                                       vnOidPais,
                                       30,
                                       vnOidValorEstrGeop,
                                       1,
                                       NULL,
                                       3,
                                       vnOidSubTipoCliente,
                                       vnTipoSoliCons,
                                       vnFormaPagoEnv,
                                       vnClaseSolicEnv,
                                       vnOidTerrAdmi,
                                       21,
                                       1,
                                       NULL,
                                       NULL,
                                       vnOidFormPago,
                                       0);
                                       vnPosic := 1;
                           END IF;
                       OPEN c_PremiosCampana (vnCodNivelExitoLider,'R3');

                          LOOP
                             FETCH c_PremiosCampana INTO vnCodigoPremio;

                             EXIT WHEN c_PremiosCampana%NOTFOUND;

                                    SELECT ofedet.PROD_OID_PROD,
                                           ofedet.IMP_PREC_POSI,
                                           ofedet.OID_DETA_OFER
                                      INTO vnOidProducto,
                                           vnImpPrecPosi,
                                           vnOidDetalleOferta
                                      FROM PRE_OFERT ofe,
                                           PRE_OFERT_DETAL ofedet,
                                           PRE_MATRI_FACTU mf,
                                           PRE_MATRI_FACTU_CABEC mfc,
                                           MAE_PRODU prod,
                                           (SELECT V.VAL_OID, V.VAL_I18N
                                              FROM V_GEN_I18N_SICC V
                                             WHERE V.ATTR_ENTI = 'MAE_PRODU'
                                               AND V.IDIO_OID_IDIO = 1) i18prod
                            WHERE mfc.PERD_OID_PERI = vnOidPeriodoSiguiente
                                       AND mf.MFCA_OID_CABE = mfc.OID_CABE
                                       AND ofe.MFCA_OID_CABE = mfc.OID_CABE
                                       AND ofe.OID_OFER = ofedet.OFER_OID_OFER
                                             AND ofedet.OID_DETA_OFER =
                                                    mf.OFDE_OID_DETA_OFER
                                  AND ofedet.VAL_CODI_VENT = vnCodigoPremio
                                       AND ofedet.PROD_OID_PROD = prod.OID_PROD
                                       AND prod.OID_PROD = i18prod.VAL_OID;

                                      INSERT
                                        INTO PED_SOLIC_POSIC (OID_SOLI_POSI,
                                        COD_POSI,
                                        NUM_UNID_DEMA,
                                        NUM_UNID_POR_ATEN,
                                        VAL_TASA_IMPU,
                                        SOCA_OID_SOLI_CABE,
                                        TPOS_OID_TIPO_POSI,
                                        PROD_OID_PROD,
                                        VAL_PREC_CATA_UNIT_LOCA,
                                        VAL_PREC_CONT_UNIT_LOCA,
                                        VAL_PREC_CATA_UNIT_DOCU,
                                        VAL_PREC_CONTA_UNIT_DOCU,
                                        NUM_UNID_COMPR,
                                        NUM_UNID_DEMA_REAL,
                                        ESPO_OID_ESTA_POSI,
                                        STPO_OID_SUBT_POSI,
                                        VAL_CODI_VENT,
                                        OFDE_OID_DETA_OFER)
                                      VALUES (PED_SOPO_SEQ.NEXTVAL,
                                              vnPosic,
                                        1,
                                        1,
                                        0,
                                        vnOidSolicCabec,
                                        9,
                                        vnOidProducto,
                                        0,
                                        vnImpPrecPosi,
                                        0,
                                        vnImpPrecPosi,
                                        1,
                                        1,
                                        4,
                                        13,
                                        vnCodigoPremio,
                                        vnOidDetalleOferta);

                                        vnPosic := vnPosic + 1;
                                  END LOOP;

                          CLOSE c_PremiosCampana;
                        END IF;
                 END IF;

                     IF seccionResultado (i).COD_ESTA_OBJE_R44 = '09'
                     THEN
                         SELECT nvl(rr.IND_PREM,0)
                         INTO vsIndPrem
                         FROM LET_CORPO_RANGO_RETEN rr
                         WHERE     rr.COD_PROG = vsCodigoConcurso
                               AND seccionResultado (i).NUM_INGR_44_REAL >= rr.NUM_INGR_INIC
                               AND seccionResultado (i).NUM_INGR_44_REAL <= rr.NUM_INGR_FINA;
                        IF  vsIndPrem = 1
                        THEN
                            SELECT COUNT(1)
                           INTO      vsPremPed   FROM PED_SOLIC_CABEC
                           WHERE  TSPA_OID_TIPO_SOLI_PAIS = vnOidTipoSoliPais
                           AND       PERD_OID_PERI = vnOidPeriodoSiguiente
                           AND       CLIE_OID_CLIE =  vnOidEjecutiva;
                           SELECT COUNT(1)
                           INTO      vshayPremio
                           FROM    LET_CORPO_PARAM_PREMI_PROGR
                           WHERE   COD_PROG = vsCodigoConcurso
                           AND       CAM_DESP = vsCampanaSiguiente
                           AND       TIP_PREM = 'R4';
                           IF   vsPremPed = 0 AND vshayPremio >= 1
                           THEN
                               INSERT
                               INTO PED_SOLIC_CABEC (OID_SOLI_CABE,
                                                     FEC_PROG_FACT,
                                                     TSPA_OID_TIPO_SOLI_PAIS,
                                                     TIDS_OID_TIPO_DESP,
                                                     ALMC_OID_ALMA,
                                                     MODU_OID_MODU,
                                                     TICL_OID_TIPO_CLIE,
                                                     PERD_OID_PERI,
                                                     CLIE_OID_CLIE,
                                                     CLIE_OID_CLIE_RECE_FACT,
                                                     CLIE_OID_CLIE_PAGA,
                                                     CLIE_OID_CLIE_DEST,
                                                     CLDI_OID_CLIE_DIRE,
                                                     TDOC_OID_TIPO_DOCU,
                                                     SOCI_OID_SOCI,
                                                     SBAC_OID_SBAC,
                                                     TERR_OID_TERR,
                                                     ZZON_OID_ZONA,
                                                     VAL_NUME_SOLI,
                                                     FEC_CRON,
                                                     IND_PERM_UNIO_SOL,
                                                     NUM_DOCU_ORIG,
                                                     IND_TS_NO_CONSO,
                                                     PAIS_OID_PAIS,
                                                     TIDO_OID_TIPO_DOCU,
                                                     VEPO_OID_VALO_ESTR_GEOP,
                                                     ESSO_OID_ESTA_SOLI,
                                                     COPA_OID_PARA_GENE,
                                                     GRPR_OID_GRUP_PROC,
                                                     SBTI_OID_SUBT_CLIE,
                                                     TSPA_OID_TIPO_SOLI_PAIS_CONS,
                                                     FOPA_OID_FORM_PAGO,
                                                     CLSO_OID_CLAS_SOLI,
                                                     ZTAD_OID_TERR_ADMI,
                                                     OPER_OID_OPER,
                                                     PROC_OID_PROC,
                                                     SOCA_OID_DOCU_REFE,
                                                     ICTP_OID_TIPO_PROG,
                                                     VAL_TIPO_CAMB,
                                                     IND_OC)
                             VALUES (
                                       vnOidSolicCabec,
                                       vnFecFinPerio,
                                       vnOidTipoSoliPais,
                                       1,
                                       vnOidAlmacEnv,
                                       1,
                                       vnOidTipoCliente,
                                       vnOidPeriodoSiguiente,
                                       vnOidEjecutiva,
                                       vnOidEjecutiva,
                                       vnOidEjecutiva,
                                       vnOidEjecutiva,
                                       vnOidClieDire,
                                       vnOidTipoDocu,
                                       vnSocie,
                                       vnSubac,
                                       vnOidTerr,
                                       vnOidZona,
                                       vnNumSoliFormato,
                                       TO_DATE ( (TO_CHAR (SYSDATE, 'dd/MM/yyyy')),
                                                'dd/MM/yyyy'),
                                       1,
                                       NULL,
                                       1,
                                       vnOidPais,
                                       30,
                                       vnOidValorEstrGeop,
                                       1,
                                       NULL,
                                       3,
                                       vnOidSubTipoCliente,
                                       vnTipoSoliCons,
                                       vnFormaPagoEnv,
                                       vnClaseSolicEnv,
                                       vnOidTerrAdmi,
                                       21,
                                       1,
                                       NULL,
                                       NULL,
                                       vnOidFormPago,
                                       0);
                                       vnPosic := 1;

                           END IF;
                        OPEN c_PremiosCampana (vnCodNivelExitoLider,'R4');

                          LOOP
                             FETCH c_PremiosCampana INTO vnCodigoPremio;

                             EXIT WHEN c_PremiosCampana%NOTFOUND;

                                    SELECT ofedet.PROD_OID_PROD,
                                           ofedet.IMP_PREC_POSI,
                                           ofedet.OID_DETA_OFER
                                      INTO vnOidProducto,
                                           vnImpPrecPosi,
                                           vnOidDetalleOferta
                                      FROM PRE_OFERT ofe,
                                           PRE_OFERT_DETAL ofedet,
                                           PRE_MATRI_FACTU mf,
                                           PRE_MATRI_FACTU_CABEC mfc,
                                           MAE_PRODU prod,
                                           (SELECT V.VAL_OID, V.VAL_I18N
                                              FROM V_GEN_I18N_SICC V
                                             WHERE V.ATTR_ENTI = 'MAE_PRODU'
                                               AND V.IDIO_OID_IDIO = 1) i18prod
                            WHERE mfc.PERD_OID_PERI = vnOidPeriodoSiguiente
                                       AND mf.MFCA_OID_CABE = mfc.OID_CABE
                                       AND ofe.MFCA_OID_CABE = mfc.OID_CABE
                                       AND ofe.OID_OFER = ofedet.OFER_OID_OFER
                                             AND ofedet.OID_DETA_OFER =
                                                    mf.OFDE_OID_DETA_OFER
                                  AND ofedet.VAL_CODI_VENT = vnCodigoPremio
                                       AND ofedet.PROD_OID_PROD = prod.OID_PROD
                                       AND prod.OID_PROD = i18prod.VAL_OID;

                                      INSERT
                                        INTO PED_SOLIC_POSIC (OID_SOLI_POSI,
                                        COD_POSI,
                                        NUM_UNID_DEMA,
                                        NUM_UNID_POR_ATEN,
                                        VAL_TASA_IMPU,
                                        SOCA_OID_SOLI_CABE,
                                        TPOS_OID_TIPO_POSI,
                                        PROD_OID_PROD,
                                        VAL_PREC_CATA_UNIT_LOCA,
                                        VAL_PREC_CONT_UNIT_LOCA,
                                        VAL_PREC_CATA_UNIT_DOCU,
                                        VAL_PREC_CONTA_UNIT_DOCU,
                                        NUM_UNID_COMPR,
                                        NUM_UNID_DEMA_REAL,
                                        ESPO_OID_ESTA_POSI,
                                        STPO_OID_SUBT_POSI,
                                        VAL_CODI_VENT,
                                        OFDE_OID_DETA_OFER)
                                      VALUES (PED_SOPO_SEQ.NEXTVAL,
                                              vnPosic,
                                        1,
                                        1,
                                        0,
                                        vnOidSolicCabec,
                                        9,
                                        vnOidProducto,
                                        0,
                                        vnImpPrecPosi,
                                        0,
                                        vnImpPrecPosi,
                                        1,
                                        1,
                                        4,
                                        13,
                                        vnCodigoPremio,
                                        vnOidDetalleOferta);

                                        vnPosic := vnPosic + 1;
                                  END LOOP;

                          CLOSE c_PremiosCampana;
                        END IF;
                 END IF;

                        UPDATE LET_CORPO_SECCI_RESUL
                           SET IND_ENTR_PREM = 1
                         WHERE COD_PAIS = psCodigoPais
                           AND COD_PROG = seccionResultado(i).COD_PROG
                            AND COD_CLIE_LIDE =
                                   seccionResultado (i).COD_CLIE_LIDE;

                     vnNumSoliFormato := vnNumSoliFormato + 1;
                    END LOOP;
                END IF;

            EXIT WHEN c_SeccionResultado%NOTFOUND;
            END LOOP;

            CLOSE c_SeccionResultado;
        END IF;
    END IF;
EXCEPTION
      WHEN OTHERS
      THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
         RAISE_APPLICATION_ERROR (-20123,
                                  'ERROR LET_PR_DESPA_PREMI: ' || ls_sqlerrm);
END LET_PR_DESPA_PREMI;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte LET de Resultados
Fecha Creacion    : 13/01/2015
Autor             : Gonzalo Huertas
***************************************************************************/
PROCEDURE LET_PR_REPOR_RESU(
    psCodigoPais                     VARCHAR2,
    psCampanya                       VARCHAR2,
    psNombreArchivo                  VARCHAR2,
    psTitulo                         VARCHAR2,
    psTramo                          VARCHAR2,
    psDirectorio                     OUT  VARCHAR2
    )
IS
  lsDirTempo                        BAS_INTER.DIR_TEMP%TYPE;
  w_filas                           NUMBER := 5000 ;
  v_handle                          UTL_FILE.FILE_TYPE;
  lsLinea                           VARCHAR2(4000);
  lsFlagRegiones                    VARCHAR2(1);

  CURSOR c_interfaz IS
SELECT --informacion lider
 r.cam_resu campa�a_resultado,
       r.cod_regi codigo_region,
       r.cod_zona codigo_zona,
       r.cod_secc codigo_seccion,
       r.cod_lide codigo_lider,
       c.val_ape1 ||' '||c.val_ape2||' '||c.val_nom1||' '||c.val_nom2 nombre,
       cc.val_text_comu correo,
       r.ind_pedi_pers_lide forma_pedido,
       case when nvl(r.ind_pedi_web,0) = 1 then 'SI' else 'NO' end val_indi_pedi_web,
 (gen_pkg_gener.GEN_FN_DEVUE_DIFER_PERIO(spc.cod_peri, psCampanya) + 1) nro_campa�a_consultora,
       gen_pkg_gener.gen_fn_devuelve_des_perio((SELECT MIN(g.perd_oid_peri_desd)
         FROM zon_histo_geren g
        WHERE g.gere = r.cod_lide)) campa_inicio_lider,
       gen_pkg_gener.gen_fn_devuelve_des_perio(
        (SELECT MAX(g.perd_oid_peri_desd)
         FROM zon_histo_geren g
        WHERE g.gere = r.cod_lide)) campa_ultimo_ingreso,
       cl.des_clas clasificacion,
       scl.des_subc subclasificacion,
       'SI' coberturada,
       r.ind_secc_apta seccion_apta,
       (
        SELECT lniv.des_nive
          FROM lec_lider_nivel llni,
               lec_nivel lniv
         WHERE llni.lniv_cod_nive = lniv.cod_nive
           AND llni.pais_cod_pais = r.pais_cod_pais
         --  AND llni.lpro_cod_prog = r.lpro_cod_prog
           AND llni.cod_lide = r.cod_lide
           AND GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,psCampanya,-1) BETWEEN llni.cam_inic AND NVL(llni.cam_fin,GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,psCampanya,-1))
           AND llni.ind_tipo_nive = 'R'
       ) cod_nive_camp_ante, -- Nuevo
       n.des_nive Nivel,
       (gen_pkg_gener.GEN_FN_DEVUE_DIFER_PERIO(lni.cam_inic,psCampanya)+ 1) Nro_Campanias_Nivel,
       --resultados
       ( SELECT tdc.des_tipo_dese
           FROM Lec_Lider_Desem dc,
                lec_tipo_desem tdc
          WHERE dc.ltde_cod_tipo_dese   = tdc.cod_tipo_dese
            AND dc.cod_lide             = r.cod_lide
            AND dc.lpec_cam_eval        = r.cam_resu
            AND dc.ind_tipo_dese        = 'C'
       ) Traccion,
       (SELECT tde.des_tipo_dese
          FROM lec_lider_desem de,
               lec_tipo_desem tde
         WHERE de.ltde_cod_tipo_dese = tde.cod_tipo_dese
           AND de.lpec_cam_eval      = r.cam_resu
           AND de.cod_lide           = r.cod_lide
           AND de.ind_tipo_dese      = 'E'
       ) Productividad,
       r.num_pedi nro_pediddos_reales,
       r.num_pedi_cons nro_pedido_constante,
       r.num_pedi_ncon nro_pedido_no_constante,
       op.num_pedi_obje_fina nro_pedidos_objetivo,
       r.num_pedi - op.num_pedi_obje_fina Diferencia_Pedidos,
       nvl(lnipr.mon_vent_cata,0) + nvl(lnipr.mont_vent_rtal,0) Venta_Real,
       op.val_obje_vent Venta_Objetivo,
       ( nvl(lnipr.mon_vent_cata,0) + nvl(lnipr.mont_vent_rtal,0)  )  -  nvl(op.val_obje_vent,0) Diferencia_Venta,
       r.ind_pedi_pers_lide lider_paso_pedido,
       ( SELECT COUNT(*)
     FROM mae_clien c,
          mae_clien_vincu vin,
          mae_clien_prime_conta pc,
          cra_perio cp,
          seg_perio_corpo spc
    WHERE c.oid_clie             = vin.clie_oid_clie_vnte
      AND vin.tivc_oid_tipo_vinc = 9
      AND pc.clie_oid_clie       = vin.clie_oid_clie_vndo
      AND EXISTS (SELECT NULL
                    FROM ped_solic_cabec sc,
                         ped_tipo_solic_pais tsp,
                         ped_tipo_solic ts
                   WHERE sc.fec_fact                IS NOT NULL
                     AND sc.clie_oid_clie           = vin.clie_oid_clie_vndo
                     AND sc.esso_oid_esta_soli      <> 4
                     AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                     AND tsp.tsol_oid_tipo_soli     = ts.oid_tipo_soli
                     AND ts.cod_tipo_soli           = 'SOC'
                     AND sc.perd_oid_peri           = pc.perd_oid_peri
                 )
      AND pc.perd_oid_peri       = cp.oid_peri
      AND cp.peri_oid_peri       = spc.oid_peri
      AND spc.cod_peri           = r.cam_resu
      AND c.cod_clie             = r.cod_lide
      ) ing_lider_camp,
       ( SELECT COUNT(*)
     FROM mae_clien c,
          mae_clien_vincu vin,
          mae_clien_prime_conta pc,
          cra_perio cp,
          seg_perio_corpo spc
    WHERE c.oid_clie             = vin.clie_oid_clie_vnte
      AND vin.tivc_oid_tipo_vinc = 9
      AND pc.clie_oid_clie       = vin.clie_oid_clie_vndo
      AND EXISTS (SELECT NULL
                    FROM ped_solic_cabec sc,
                         ped_tipo_solic_pais tsp,
                         ped_tipo_solic ts
                   WHERE sc.fec_fact                IS NOT NULL
                     AND sc.clie_oid_clie           = vin.clie_oid_clie_vndo
                     AND sc.esso_oid_esta_soli      <> 4
                     AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                     AND tsp.tsol_oid_tipo_soli     = ts.oid_tipo_soli
                     AND ts.cod_tipo_soli           = 'SOC'
                     AND sc.perd_oid_peri           = pc.perd_oid_peri
                 )
      AND pc.perd_oid_peri       = cp.oid_peri
      AND cp.peri_oid_peri       = spc.oid_peri
      AND spc.cod_peri           = GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,psCampanya,-1)
      AND c.cod_clie             = r.cod_lide
      ) ing_Lider_Camp_Anterior,

        nvl(r.num_ingr_secc,0)  Ing_Seccion,
         PN.NUM_META_INGR  Met_Ingresos,
        nvl(r.num_ingr_secc,0)  -  nvl(PN.NUM_META_INGR ,0)    Difer_Ingres,
        nvl(r.num_ingr_secc,0) + nvl(r.num_rein_secc,0) - nvl(r.num_egre_secc,0)    Capi_Seccion,                 ----   Nuevo  RCR  PER_SICC_2015-0548
         PN.NUM_META_CAPI  Meta_Capitaliz,                                               ----   Nuevo  RCR  PER_SICC_2015-0548
        ( nvl(r.num_ingr_secc,0)  + nvl(r.num_rein_secc,0) - nvl(r.num_egre_secc,0)  )   -  nvl(PN.NUM_META_CAPI ,0)    Difer_Capitaliz,     ----   Nuevo  RCR  PER_SICC_2015-0548

       --'0' retencion_22_Lider,
       r22.num_ingr_secc ing_secc_Cx_1,
       r22.val_rete_secc retencion_22,
       (SELECT ob.val_obje_bono
          FROM lec_lider_secci_objet_bono ob
         WHERE ob.ltbo_cod_tipo_bono = '05' AND
               r22.cod_regi           = ob.cod_regi AND
               r22.cod_zona           = ob.cod_zona AND
               r22.cod_secc           = ob.cod_secc AND
               r22.cam_resu           = ob.cam_obje AND
               ob.lniv_cod_nive       = lni.lniv_cod_nive AND
               OB.LPRO_COD_PROG = r22.lpro_cod_prog
        ) obj_retencion_22,
       r33.num_ingr_secc Ingr_Secc_Cx_2,
       r33.val_rete_secc Retencion_33,
       (SELECT ob.val_obje_bono
          FROM lec_lider_secci_objet_bono ob
         WHERE ob.ltbo_cod_tipo_bono = '06' AND
               r33.cod_regi           = ob.cod_regi AND
               r33.cod_zona           = ob.cod_zona AND
               r33.cod_secc           = ob.cod_secc AND
               r33.cam_resu           = ob.cam_obje AND
               ob.lniv_cod_nive       = lni.lniv_cod_nive AND
               OB.LPRO_COD_PROG = r33.lpro_cod_prog
        ) obj_retencion_33,
       r44.num_ingr_secc Ingr_Secc_Cx_3,
       r44.val_rete_secc Retencion_44,
       (SELECT ob.val_obje_bono
          FROM lec_lider_secci_objet_bono ob
         WHERE ob.ltbo_cod_tipo_bono = '07' AND
               r44.cod_regi           = ob.cod_regi AND
               r44.cod_zona           = ob.cod_zona AND
               r44.cod_secc           = ob.cod_secc AND
               r44.cam_resu           = ob.cam_obje AND
               ob.lniv_cod_nive       = lni.lniv_cod_nive AND
               OB.LPRO_COD_PROG = r44.lpro_cod_prog
        ) obj_retencion_44,
        (
         SELECT lrbo.num_pedi_secc_lanz
           FROM lec_lider_secci_resul_bono lrbo
          WHERE lrbo.pais_cod_pais = r.pais_cod_pais
            AND lrbo.lpro_cod_prog = r.lpro_cod_prog
            AND lrbo.cod_regi = r.cod_regi
            AND lrbo.cod_zona = r.cod_zona
            AND lrbo.cod_secc = r.cod_secc
            AND lrbo.cam_resu = r.cam_resu
            AND lrbo.ltbo_cod_tipo_bono = '03'
        ) val_unid_lanz_estr, -- Nuevo
        (
         SELECT lsob.val_obje_bono
           FROM lec_lider_secci_objet_bono lsob
          WHERE lsob.pais_cod_pais = r.pais_cod_pais
            AND lsob.lpro_cod_prog = r.lpro_cod_prog
            AND lsob.cod_regi = r.cod_regi
            AND lsob.cod_zona = r.cod_zona
            AND lsob.cod_secc = r.cod_secc
            AND lsob.ltbo_cod_tipo_bono = '03'
            AND lsob.lniv_cod_nive = n.cod_nive
            AND lsob.cam_obje = r.cam_resu
        ) val_obje_unid_lanz_estr, -- Nuevo
        (
         SELECT lrbo.num_pedi_secc_lanz
           FROM lec_lider_secci_resul_bono lrbo
          WHERE lrbo.pais_cod_pais = r.pais_cod_pais
            AND lrbo.lpro_cod_prog = r.lpro_cod_prog
            AND lrbo.cod_regi = r.cod_regi
            AND lrbo.cod_zona = r.cod_zona
            AND lrbo.cod_secc = r.cod_secc
            AND lrbo.cam_resu = r.cam_resu
            AND lrbo.ltbo_cod_tipo_bono = '04'
        ) val_unid_lanz_estr_sobr, -- Nuevo
        (
         SELECT lsob.val_obje_bono
           FROM lec_lider_secci_objet_bono lsob
          WHERE lsob.pais_cod_pais = r.pais_cod_pais
            AND lsob.lpro_cod_prog = r.lpro_cod_prog
            AND lsob.cod_regi = r.cod_regi
            AND lsob.cod_zona = r.cod_zona
            AND lsob.cod_secc = r.cod_secc
            AND lsob.ltbo_cod_tipo_bono = '04'
            AND lsob.lniv_cod_nive = n.cod_nive
            AND lsob.cam_obje = r.cam_resu
        ) val_obje_unid_lanz_estr_sobr, -- Nuevo
        (
         SELECT lrbo.num_pedi_secc_lanz
           FROM lec_lider_secci_resul_bono lrbo
          WHERE lrbo.pais_cod_pais = r.pais_cod_pais
            AND lrbo.lpro_cod_prog = r.lpro_cod_prog
            AND lrbo.cod_regi = r.cod_regi
            AND lrbo.cod_zona = r.cod_zona
            AND lrbo.cod_secc = r.cod_secc
            AND lrbo.cam_resu = r.cam_resu
            AND lrbo.ltbo_cod_tipo_bono = '01'
        ) val_pedi_lanz_estr, -- Nuevo
        (
         SELECT lsob.val_obje_bono
           FROM lec_lider_secci_objet_bono lsob
          WHERE lsob.pais_cod_pais = r.pais_cod_pais
            AND lsob.lpro_cod_prog = r.lpro_cod_prog
            AND lsob.cod_regi = r.cod_regi
            AND lsob.cod_zona = r.cod_zona
            AND lsob.cod_secc = r.cod_secc
            AND lsob.ltbo_cod_tipo_bono = '01'
            AND lsob.lniv_cod_nive = n.cod_nive
            AND lsob.cam_obje = r.cam_resu
        ) val_obje_pedi_lanz_estr, -- Nuevo
        (
         SELECT lrbo.num_pedi_secc_lanz
           FROM lec_lider_secci_resul_bono lrbo
          WHERE lrbo.pais_cod_pais = r.pais_cod_pais
            AND lrbo.lpro_cod_prog = r.lpro_cod_prog
            AND lrbo.cod_regi = r.cod_regi
            AND lrbo.cod_zona = r.cod_zona
            AND lrbo.cod_secc = r.cod_secc
            AND lrbo.cam_resu = r.cam_resu
            AND lrbo.ltbo_cod_tipo_bono = '02'
        ) val_pedi_lanz_estr_sobr, -- Nuevo
        (
         SELECT lsob.val_obje_bono
           FROM lec_lider_secci_objet_bono lsob
          WHERE lsob.pais_cod_pais = r.pais_cod_pais
            AND lsob.lpro_cod_prog = r.lpro_cod_prog
            AND lsob.cod_regi = r.cod_regi
            AND lsob.cod_zona = r.cod_zona
            AND lsob.cod_secc = r.cod_secc
            AND lsob.ltbo_cod_tipo_bono = '02'
            AND lsob.lniv_cod_nive = n.cod_nive
            AND lsob.cam_obje = r.cam_resu
        ) val_obje_pedi_lanz_estr_sobr, -- Nuevo
     --ganancia
      CASE WHEN (SELECT count(lg.mon_gana)
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana = '13' AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        r.cam_resu            = lg.cam_gana AND
                        lg.lcpt_cod_tram      = psTramo
                 ) > 0 THEN 'Nueva'
            WHEN (SELECT count(lg.mon_gana)
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana = '04' AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        r.cam_resu            = lg.cam_gana AND
                        lg.lcpt_cod_tram      = psTramo
                 ) > 0 THEN 'Tolerancia'
            WHEN (SELECT count(lg.mon_gana)
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana = '03' AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        r.cam_resu            = lg.cam_gana AND
                        lg.lcpt_cod_tram      = psTramo
                 ) > 0 THEN 'Cumplimiento'
           ELSE NULL END forma,
      CASE WHEN (SELECT count(lg.mon_gana)
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana = '13' AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        r.cam_resu            = lg.cam_gana AND
                        lg.lcpt_cod_tram      = psTramo
                 ) > 0 THEN (SELECT lg.mon_gana
                               FROM lec_lider_ganan lg
                              WHERE lg.ltga_cod_tipo_gana = '13' AND
                                    lg.cam_gana           = psCampanya AND
                                    r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                                    r.cod_regi            = lg.cod_regi AND
                                    r.cod_zona            = lg.cod_zona AND
                                    r.cod_secc            = lg.cod_secc AND
                                    r.cod_lide            = lg.cod_lide AND
                                    r.cam_resu            = lg.cam_gana AND
                                    lg.lcpt_cod_tram      = psTramo)
            WHEN (SELECT count(lg.mon_gana)
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana = '04' AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        r.cam_resu            = lg.cam_gana AND
                        lg.lcpt_cod_tram      = psTramo
                 ) > 0 THEN (SELECT lg.mon_gana
                               FROM lec_lider_ganan lg
                              WHERE lg.ltga_cod_tipo_gana = '04' AND
                                    lg.cam_gana           = psCampanya AND
                                    r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                                    r.cod_regi            = lg.cod_regi AND
                                    r.cod_zona            = lg.cod_zona AND
                                    r.cod_secc            = lg.cod_secc AND
                                    r.cod_lide            = lg.cod_lide AND
                                    r.cam_resu            = lg.cam_gana AND
                                    lg.lcpt_cod_tram      = psTramo)
            WHEN (SELECT count(lg.mon_gana)
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana = '03' AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        r.cam_resu            = lg.cam_gana AND
                        lg.lcpt_cod_tram      = psTramo
                 ) > 0 THEN (SELECT lg.mon_gana
                               FROM lec_lider_ganan lg
                              WHERE lg.ltga_cod_tipo_gana = '03' AND
                                    lg.cam_gana           = psCampanya AND
                                    r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                                    r.cod_regi            = lg.cod_regi AND
                                    r.cod_zona            = lg.cod_zona AND
                                    r.cod_secc            = lg.cod_secc AND
                                    r.cod_lide            = lg.cod_lide AND
                                    r.cam_resu            = lg.cam_gana AND 
                                    lg.lcpt_cod_tram      = psTramo)
           ELSE NULL END  ganancia,
                (SELECT lg.mon_gana
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana = '09' AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        lg.lcpt_cod_tram      = psTramo AND
                        r.cam_resu            = lg.cam_gana) ganancia_22,
                (SELECT lg.mon_gana
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana = '10' AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        lg.lcpt_cod_tram      = psTramo AND
                        r.cam_resu            = lg.cam_gana) ganancia_33,
                (SELECT lg.mon_gana
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana = '11' AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND 
                        lg.lcpt_cod_tram      = psTramo AND
                        r.cam_resu            = lg.cam_gana) ganancia_44,
                (SELECT SUM(lg.mon_gana)
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana IN ('05','06','07','08') AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        lg.lcpt_cod_tram      = psTramo AND
                        r.cam_resu            = lg.cam_gana) ganancia_Lanz,
                (SELECT lg.mon_gana
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana = '12' AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        lg.lcpt_cod_tram      = psTramo AND
                        r.cam_resu            = lg.cam_gana) ganancia_Nivel_Acel,
                (SELECT sum(lg.mon_gana)
                   FROM lec_lider_ganan lg
                  WHERE lg.ltga_cod_tipo_gana IN ('03','05','06','07','08','09','10','11','12') AND
                        lg.cam_gana           = psCampanya AND
                        r.Lpro_Cod_Prog       = lg.lpro_cod_prog AND
                        r.cod_regi            = lg.cod_regi AND
                        r.cod_zona            = lg.cod_zona AND
                        r.cod_secc            = lg.cod_secc AND
                        r.cod_lide            = lg.cod_lide AND
                        lg.lcpt_cod_tram      = psTramo AND
                        r.cam_resu            = lg.cam_gana) ganancia_Total,
                        (select GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio3(max(perd_oid_peri_desd))
                        from zon_histo_geren where  cod_secc is not null and gere=c.cod_clie) as campa_ultimo_nombra
  FROM (
        SELECT gere.gere cod_lide, gere.cod_regi, gere.cod_zona, gere.cod_secc, peri.cod_peri cod_peri_inic, peri2.cod_peri cod_peri_fina
          FROM zon_histo_geren gere,
               cra_perio perd,
               seg_perio_corpo peri,
               cra_perio perd2,
               seg_perio_corpo peri2
         WHERE gere.perd_oid_peri_desd = perd.oid_peri
           AND perd.peri_oid_peri = peri.oid_peri
           AND NVL(gere.perd_oid_peri_hast, Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanya)) = perd2.oid_peri
           AND perd2.peri_oid_peri = peri2.oid_peri
           --
           AND gere.cod_regi IS NOT NULL
           AND gere.cod_zona IS NOT NULL
           AND gere.cod_secc IS NOT NULL
           AND psCampanya BETWEEN peri.cod_peri AND peri2.cod_peri
       ) lide,
       lec_lider_secci_resul r,
       lec_lider_secci_objet_pedid op,
       lec_estad_objet eo,
       lec_lider_clasi lc,
       Lec_Clasi cl,
       lec_subcl scl,
       lec_lider_nivel lni,
       lec_lider_nivel lnipr,
       LEC_PROGR_NIVEL pn,
       lec_nivel n,
       mae_clien c,
       mae_clien_prime_conta pc,
       cra_perio p,
       seg_perio_corpo spc,
       mae_clien_comun cc,
       (SELECT rb.lpro_cod_prog,rb.cod_regi, rb.cod_zona,rb.cod_secc,rb.cod_lide,rb.cam_resu,rb.num_ingr_secc,rb.val_rete_secc
           FROM LEC_LIDER_SECCI_RESUL_BONO rb
           WHERE rb.ltbo_cod_tipo_bono = '05' AND
                 rb.cam_resu = psCampanya
        ) r22,
        (SELECT rb.lpro_cod_prog,rb.cod_regi, rb.cod_zona,rb.cod_secc,rb.cod_lide,rb.cam_resu,rb.num_ingr_secc,rb.val_rete_secc
           FROM LEC_LIDER_SECCI_RESUL_BONO rb
           WHERE rb.ltbo_cod_tipo_bono = '06' AND
                 rb.cam_resu = psCampanya
        ) r33,
        (SELECT rb.lpro_cod_prog,rb.cod_regi, rb.cod_zona,rb.cod_secc,rb.cod_lide,rb.cam_resu,rb.num_ingr_secc,rb.val_rete_secc
           FROM LEC_LIDER_SECCI_RESUL_BONO rb
           WHERE rb.ltbo_cod_tipo_bono = '07' AND
                 rb.cam_resu = psCampanya
        ) r44
 WHERE lide.cod_lide = r.cod_lide(+)
   AND r.leob_cod_esta_obje     = eo.cod_esta_obje(+)
   AND r.cod_regi               = op.cod_regi
   AND r.cod_zona               = op.cod_zona
   AND r.cod_secc               = op.cod_secc
   AND r.cam_resu               = op.cam_obje
AND  r.lpro_cod_prog = op.lpro_cod_prog
   AND r.cam_resu               = psCampanya
   AND r.cod_lide               = lc.cod_lide
   AND lc.cam_inic             <= psCampanya
   AND (lc.cam_fin             >= psCampanya OR lc.cam_fin IS NULL)
   AND lc.lccl_cod_clas         = scl.lccl_cod_clas
   AND lc.lscl_cod_subc         = scl.cod_subc
   AND scl.lccl_cod_clas        = cl.cod_clas
   AND r.cod_lide               = lni.cod_lide
   AND lni.cam_inic            <= psCampanya
   AND (lni.cam_fin            >= psCampanya OR lni.cam_fin IS NULL)
   AND lni.ind_tipo_nive       = 'R'
   AND  lni.lpro_cod_prog = r.lpro_cod_prog
   AND lni.lniv_cod_nive       = n.cod_nive
    AND r.cod_lide               = lnipr.cod_lide
   AND lnipr.cam_inic            = psCampanya
   AND lnipr.ind_tipo_nive       = 'P'
   AND  lnipr.lpro_cod_prog = r.lpro_cod_prog
   AND r.cod_lide              = c.cod_clie
   AND c.oid_clie              = pc.clie_oid_clie
   AND pc.perd_oid_peri        = p.oid_peri
   AND p.peri_oid_peri         = spc.oid_peri
   AND pc.perd_oid_peri        = p.oid_peri
   AND p.peri_oid_peri         = spc.oid_peri
   AND c.oid_clie              = cc.clie_oid_clie(+)
   AND cc.ticm_oid_tipo_comu(+)= 3
       --retencion 22
   AND r.Lpro_Cod_Prog = r22.lpro_cod_prog(+)
   AND r.cod_regi      = r22.cod_regi(+)
   AND r.cod_zona      = r22.cod_zona(+)
   AND r.cod_secc      = r22.cod_secc(+)
   AND r.cod_lide      = r22.cod_lide(+)
   AND r.cam_resu      = r22.cam_resu(+)
       --retencion 33
   AND r.Lpro_Cod_Prog = r33.lpro_cod_prog(+)
   AND r.cod_regi      = r33.cod_regi(+)
   AND r.cod_zona      = r33.cod_zona(+)
   AND r.cod_secc      = r33.cod_secc(+)
   AND r.cod_lide      = r33.cod_lide(+)
   AND r.cam_resu      = r33.cam_resu(+)
       --retencion 44
   AND r.Lpro_Cod_Prog = r44.lpro_cod_prog(+)
   AND r.cod_regi      = r44.cod_regi(+)
   AND r.cod_zona      = r44.cod_zona(+)
   AND r.cod_secc      = r44.cod_secc(+)
   AND r.cod_lide      = r44.cod_lide(+)
   AND r.cam_resu      = r44.cam_resu(+)
   AND lni.Lpro_Cod_Prog = PN.LPRO_COD_PROG (+)
   AND  lni.lniv_cod_nive = PN.LNIV_COD_NIVE (+)
   AND ((lsFlagRegiones = '1' AND r.cod_regi IN (SELECT COD_REGI FROM LET_TEMPO_REPOR_RESUL_REGIO)) OR lsFlagRegiones='0')

   ORDER BY r.cod_regi, r.cod_zona, r.cod_secc;

    TYPE interfazTipo IS RECORD (
        v_CAMPANA_RESULTADO      VARCHAR2(6),
        v_CODIGO_REGION          VARCHAR2(2),
        v_CODIGO_ZONA            VARCHAR2(4),
        v_CODIGO_SECCION         VARCHAR2(1),
        v_CODIGO_LIDER           VARCHAR2(15),
        v_NOMBRE                 VARCHAR2(100),
        v_CORREO                 VARCHAR2(100),
        v_FORMA_PEDIDO           VARCHAR2(1),
        v_VAL_INDI_PEDI_WEB      VARCHAR2(2),
        v_NRO_CAMPANA_CONSULTORA VARCHAR2(3),
        v_CAMPA_INICIO_LIDER     VARCHAR2(6),
        v_CAMPA_ULTIMO_INGRESO   VARCHAR2(6),
        v_CLASIFICACION          VARCHAR2(120),
        v_SUBCLASIFICACION       VARCHAR2(200),
        v_COBERTURADA            VARCHAR2(2),
        v_SECCION_APTA           VARCHAR2(1),
        v_COD_NIVE_CAMP_ANTE     VARCHAR2(200),
        v_NIVEL                  VARCHAR2(200),
        v_NRO_CAMPANIAS_NIVEL    VARCHAR2(3),
        v_TRACCION               VARCHAR2(200),
        v_PRODUCTIVIDAD          VARCHAR2(200),
        v_NRO_PEDIDDOS_REALES    NUMBER(12),
        v_NRO_PEDIDO_CONSTANTE   NUMBER(12),
        v_NRO_PEDIDO_NO_CONSTANTE NUMBER(12),
        v_NRO_PEDIDOS_OBJETIVO   NUMBER(12),
        v_DIFERENCIA_PEDIDOS     NUMBER(12),
        v_VENTA_REAL               NUMBER(12, 2),
        v_VENTA_OBJETIVO           NUMBER(12, 2),
        v_DIFERENCIA_VENTA         NUMBER(12, 2),
        v_LIDER_PASO_PEDIDO      VARCHAR2(1),
        v_ING_LIDER_CAMP         NUMBER(12),
        v_ING_LIDER_CAMP_ANTERIOR NUMBER(12),
        V_ING_SECCION           NUMBER(12),
        V_MET_INGRESOS          NUMBER(12),
        V_DIFER_INGRES          NUMBER(12),
        -- RCR  PER_SICC_2015-0548
        V_CAPI_SECCION          NUMBER(12),
        V_META_CAPITALIZ        NUMBER(8),
        V_DIFER_CAPITALIZ       NUMBER(12),
        --FIN
        v_ING_SECC_CX_1          NUMBER(12),
        v_RETENCION_22           NUMBER(12),
        v_OBJ_RETENCION_22       NUMBER(12),
        v_INGR_SECC_CX_2         NUMBER(12),
        v_RETENCION_33           NUMBER(12),
        v_OBJ_RETENCION_33       NUMBER(12),
        v_INGR_SECC_CX_3         NUMBER(12),
        v_RETENCION_44           NUMBER(12),
        v_OBJ_RETENCION_44       NUMBER(12),
        v_VAL_UNID_LANZ_ESTR     NUMBER(12),
        v_VAL_OBJE_UNID_LANZ_ESTR NUMBER(12),
        v_VAL_UNID_LANZ_ESTR_SOBR NUMBER(12),
        v_VAL_OBJE_UNID_LANZ_ESTR_SOBR NUMBER(12),
        v_VAL_PEDI_LANZ_ESTR     NUMBER(12),
        v_VAL_OBJE_PEDI_LANZ_ESTR NUMBER(12),
        v_VAL_PEDI_LANZ_ESTR_SOBR NUMBER(12),
        v_VAL_OBJE_PEDI_LANZ_ESTR_SOBR NUMBER(12),
        v_FORMA                  VARCHAR2(15),
        v_GANANCIA               NUMBER(12,2),
        v_GANANCIA_22            NUMBER(12,2),
        v_GANANCIA_33            NUMBER(12,2),
        v_GANANCIA_44            NUMBER(12,2),
        v_GANANCIA_LANZ          NUMBER(12,2),
        v_GANANCIA_NIVEL_ACEL    NUMBER(12,2),
        v_GANANCIA_TOTAL         NUMBER(12,2),
        v_CAMPA_ULTIMO_NOMBRA      VARCHAR2(6)
    );

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;

    lbAbrirUtlFile  BOOLEAN;

BEGIN

    SELECT DECODE(COUNT(*), 0, '0', '1')
       INTO lsFlagRegiones
     FROM LET_TEMPO_REPOR_RESUL_REGIO;

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, v_handle);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;
   END IF ;

   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := '"'||interfazRecord(x).v_CAMPANA_RESULTADO||'"' ||','||
       '=T("'||interfazRecord(x).v_CODIGO_REGION||'")' ||','||
       '=T("'||interfazRecord(x).v_CODIGO_ZONA||'")' ||','||
       '=T("'||interfazRecord(x).v_CODIGO_SECCION||'")' ||','||
       '=T("'||interfazRecord(x).v_CODIGO_LIDER||'")' ||','||
       '"'||interfazRecord(x).v_NOMBRE||'"' ||','||
       '"'||interfazRecord(x).v_CORREO||'"' ||','||
       '"'||interfazRecord(x).v_FORMA_PEDIDO||'"' ||','||
       '"'||interfazRecord(x).v_VAL_INDI_PEDI_WEB||'"' ||','||
       '"'||interfazRecord(x).v_NRO_CAMPANA_CONSULTORA||'"' ||','||
       '"'||interfazRecord(x).v_CAMPA_INICIO_LIDER||'"' ||','||
       '"'||interfazRecord(x).v_CAMPA_ULTIMO_INGRESO||'"' ||','||
       '"'||interfazRecord(x).v_CLASIFICACION||'"' ||','||
       '"'||interfazRecord(x).v_SUBCLASIFICACION||'"' ||','||
       '"'||interfazRecord(x).v_COBERTURADA||'"' ||','||
       '"'||interfazRecord(x).v_SECCION_APTA||'"' ||','||
       '"'||interfazRecord(x).v_COD_NIVE_CAMP_ANTE||'"' ||','||
       '"'||interfazRecord(x).v_NIVEL||'"' ||','||
       '"'||interfazRecord(x).v_NRO_CAMPANIAS_NIVEL||'"' ||','||
       '"'||interfazRecord(x).v_TRACCION||'"' ||','||
       '"'||interfazRecord(x).v_PRODUCTIVIDAD||'"' ||','||
        '"'||interfazRecord(x).v_NRO_PEDIDDOS_REALES||'"' ||','||
        '"'||interfazRecord(x).v_NRO_PEDIDO_CONSTANTE||'"' ||','||
        '"'||interfazRecord(x).v_NRO_PEDIDO_NO_CONSTANTE||'"' ||','||
        '"'||interfazRecord(x).v_NRO_PEDIDOS_OBJETIVO||'"' ||','||
        '"'||interfazRecord(x).v_DIFERENCIA_PEDIDOS||'"' ||','||
        '"'||interfazRecord(x).v_VENTA_REAL||'"' ||','||
        '"'||interfazRecord(x).v_VENTA_OBJETIVO||'"' ||','||
        '"'||interfazRecord(x).v_DIFERENCIA_VENTA||'"' ||','||
        '"'||interfazRecord(x).v_LIDER_PASO_PEDIDO||'"' ||','||
        '"'||interfazRecord(x).v_ING_LIDER_CAMP||'"' ||','||
        '"'||interfazRecord(x).v_ING_LIDER_CAMP_ANTERIOR||'"' ||','||
        '"'||interfazRecord(x).V_ING_SECCION||'"' ||','||
        '"'||interfazRecord(x).V_MET_INGRESOS||'"' ||','||
        '"'||interfazRecord(x).V_DIFER_INGRES||'"' ||','||
        -- RCR  PER_SICC_2015-0548
        '"'||interfazRecord(x).V_CAPI_SECCION||'"' ||','||
        '"'||interfazRecord(x).V_META_CAPITALIZ||'"' ||','||
        '"'||interfazRecord(x).V_DIFER_CAPITALIZ||'"' ||','||
        -- FIN
        '"'||interfazRecord(x).v_ING_SECC_CX_1||'"' ||','||
        '"'||interfazRecord(x).v_RETENCION_22||'"' ||','||
        '"'||interfazRecord(x).v_OBJ_RETENCION_22||'"' ||','||
        '"'||interfazRecord(x).v_INGR_SECC_CX_2||'"' ||','||
        '"'||interfazRecord(x).v_RETENCION_33||'"' ||','||
        '"'||interfazRecord(x).v_OBJ_RETENCION_33||'"' ||','||
        '"'||interfazRecord(x).v_INGR_SECC_CX_3||'"' ||','||
        '"'||interfazRecord(x).v_RETENCION_44||'"' ||','||
        '"'||interfazRecord(x).v_OBJ_RETENCION_44||'"' ||','||
        '"'||interfazRecord(x).v_VAL_UNID_LANZ_ESTR||'"' ||','||
        '"'||interfazRecord(x).v_VAL_OBJE_UNID_LANZ_ESTR||'"' ||','||
        '"'||interfazRecord(x).v_VAL_UNID_LANZ_ESTR_SOBR||'"' ||','||
        '"'||interfazRecord(x).v_VAL_OBJE_UNID_LANZ_ESTR_SOBR||'"' ||','||
        '"'||interfazRecord(x).v_VAL_PEDI_LANZ_ESTR||'"' ||','||
        '"'||interfazRecord(x).v_VAL_OBJE_PEDI_LANZ_ESTR||'"' ||','||
        '"'||interfazRecord(x).v_VAL_PEDI_LANZ_ESTR_SOBR||'"' ||','||
        '"'||interfazRecord(x).v_VAL_OBJE_PEDI_LANZ_ESTR_SOBR||'"' ||','||
       '"'||interfazRecord(x).v_FORMA||'"' ||','||
        '"'||interfazRecord(x).v_GANANCIA||'"' ||','||
        '"'||interfazRecord(x).v_GANANCIA_22||'"' ||','||
        '"'||interfazRecord(x).v_GANANCIA_33||'"' ||','||
        '"'||interfazRecord(x).v_GANANCIA_44||'"' ||','||
        '"'||interfazRecord(x).v_GANANCIA_LANZ||'"' ||','||
        '"'||interfazRecord(x).v_GANANCIA_NIVEL_ACEL||'"' ||','||
        '"'||interfazRecord(x).v_GANANCIA_TOTAL||'"' ||','||
        '"'||interfazRecord(x).v_CAMPA_ULTIMO_NOMBRA||'"';

            UTL_FILE.PUT_LINE (v_handle, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LET_PR_REPOR_RESU: '||ls_sqlerrm);
END LET_PR_REPOR_RESU;

END LET_PKG_PROCE;
/
