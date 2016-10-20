create or replace package INC_PKG_PROCE_PREMI is

/* Declaracion de variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=3000;
  
  REGLA_VALI_CANAL_PREMIACION CONSTANT NUMBER := 1;
  REGLA_VALI_MARCA_PREMIACION CONSTANT NUMBER := 2;
  REGLA_VALI_PAIS_PREMIACION CONSTANT NUMBER := 3;
  REGLA_EXIG_CURSOS CONSTANT NUMBER := 4;    
  REGLA_EXIG_CUOTA_MINIMA CONSTANT NUMBER := 5;   
  REGLA_PAGO_TIEMPO CONSTANT NUMBER := 6;    
  REGLA_EXIG_PERIODO_DESPA CONSTANT NUMBER := 7;
  REGLA_EXIG_DEUDA_PERIO_DESPA CONSTANT NUMBER := 8;    
  REGLA_VALI_DEUDA_SIN_PERI_DESP CONSTANT NUMBER := 9;    
  REGLA_VALI_DEUDA_CIERRE CONSTANT NUMBER := 10;    
  REGLA_VALI_DEUDA_CIERRE_ACTUAL CONSTANT NUMBER := 11;
  REGLA_VALI_CANDIDATA_GANADORA CONSTANT NUMBER := 12;
  REGLA_EXIG_PRODUCTOS CONSTANT NUMBER := 13;
  REGLA_EXIG_NUMERO_PERIODOS CONSTANT NUMBER := 14;
  REGLA_EXIG_NUMERO_PEDIDOS CONSTANT NUMBER := 15;
  REGLA_EXIG_MONTO_MIN_PREMI CONSTANT NUMBER := 16;
  REGLA_EXIG_MONTO_MIN_CONCU CONSTANT NUMBER := 17;
  REGLA_VALI_ACTIVIDAD CONSTANT NUMBER := 18;
  REGLA_VALI_CONSTANCIA CONSTANT NUMBER := 19;
  REGLA_VALI_CLIENTE_DESCA CONSTANT NUMBER := 20;
  
  BASE_INCUM_ASISTENCIA_A_CURSOS CONSTANT NUMBER := 1;
  BASE_INCUM_EXIG_CONCURSO CONSTANT NUMBER := 2;
  BASE_INCUM_EXIG_PREMIACION CONSTANT NUMBER := 3;
  BASE_INCUM_PAGO_A_TIEMPO CONSTANT NUMBER := 4;
  BASE_INCUM_CLIENTE_BLOQUEADO CONSTANT NUMBER := 5;
  
  TIPO_CIERRE_ZONA CONSTANT VARCHAR2(1) := 'Z'; 
  TIPO_CIERRE_REGION CONSTANT VARCHAR2(1) := 'R';
  TIPO_CIERRE_PERIODO CONSTANT VARCHAR2(1) := 'P';
  
  CAUSA_DESCALIF_ACTIVIDAD CONSTANT NUMBER := 1;
  CAUSA_DESCALIF_CONSTANCIA CONSTANT NUMBER := 2;
  
  TYPE tRegNivel IS RECORD
  (
    OID_PARA_NIVE_PREM              INC_PARAM_NIVEL_PREMI.OID_PARA_NIVE_PREM%TYPE,
    VAL_PUNT_SERV                   INC_PARAM_NIVEL_PREMI.VAL_PUNT_SERV%TYPE,
    NUM_CANT_FIJA_PUNT              INC_PARAM_NIVEL_PREMI.NUM_CANT_FIJA_PUNT%TYPE,
    NUM_CANT_INIC_PUNT              INC_PARAM_NIVEL_PREMI.NUM_CANT_INIC_PUNT%TYPE,
    NUM_CANT_FINA_PUNT              INC_PARAM_NIVEL_PREMI.NUM_CANT_FINA_PUNT%TYPE,
    NUM_PUNT_PROD_EXIG              INC_PARAM_NIVEL_PREMI.NUM_PUNT_PROD_EXIG%TYPE,
    PUNTOS                          NUMBER(12),
    TPRE_OID_TIPO_PREM              INC_PARAM_NIVEL_PREMI.TPRE_OID_TIPO_PREM%TYPE,
    NUM_NIVE                        INC_PARAM_NIVEL_PREMI.NUM_NIVE%TYPE,
    VAL_NIVE_SELE                   INC_PARAM_NIVEL_PREMI.VAL_NIVE_SELE%TYPE
  );
  
  TYPE tRegArticulo IS RECORD
  (
    IMP_PREC_PUBL                   INC_ARTIC_LOTE.IMP_PREC_PUBL%TYPE,
    NUM_UNID                        INC_ARTIC_LOTE.NUM_UNID%TYPE,
    COD_VENT_FICT                   INC_ARTIC_LOTE.COD_VENT_FICT%TYPE,
    PROD_OID_PROD                   INC_ARTIC_LOTE.PROD_OID_PROD%TYPE,
    COD_SAP                         MAE_PRODU.COD_SAP%TYPE,
    OID_ARTI_LOTE                   INC_ARTIC_LOTE.OID_ARTI_LOTE%TYPE
  );
  
  TYPE tablaRegArticulo IS TABLE OF tRegArticulo;
    
  TYPE tRegPremio IS RECORD
  (
    NUM_NIVE                        INC_PARAM_NIVEL_PREMI.NUM_NIVE%TYPE,
    NUM_CANT_FIJA_PUNT              INC_PARAM_NIVEL_PREMI.NUM_CANT_FIJA_PUNT%TYPE,
    NUM_CANT_INIC_PUNT              INC_PARAM_NIVEL_PREMI.NUM_CANT_INIC_PUNT%TYPE,
    NUM_CANT_FINA_PUNT              INC_PARAM_NIVEL_PREMI.NUM_CANT_FINA_PUNT%TYPE,
    OID_PARA_NIVE_PREM              INC_PARAM_NIVEL_PREMI.OID_PARA_NIVE_PREM%TYPE,
    NUM_PUNT_PROD_EXIG              INC_PARAM_NIVEL_PREMI.NUM_PUNT_PROD_EXIG%TYPE,
    NUM_PREM                        INC_LOTE_PREMI_ARTIC.NUM_PREM%TYPE,
    OID_PERI                        INC_PARAM_GENER_PREMI.PERD_OID_PERI%TYPE,
    OID_PREM_ELEG                   INC_PREMI_ELEGI.OID_PREM_ELEG%TYPE,
    OID_PREM                        INC_LOTE_PREMI_ARTIC.OID_LOTE_PREM_ARTI%TYPE,
    IND_PEND                        NUMBER(1) := 1,
    COD_MOTI_INVA                   NUMBER(2),
    NUM_UNID                        INC_PREMI_ARTIC.NUM_UNID%TYPE,
    articulos                       tablaRegArticulo := tablaRegArticulo(),
    IND_ATEN_PREM                   NUMBER(1) := 1,
    IND_RECH                        NUMBER(1) := 0,
    IND_ACTU_GANA                   NUMBER(1) := 0,
    OID_GANA                        NUMBER,
    IND_ELIM                        NUMBER(1) := 0 --ESTO SERVIRA PARA REALIZAR LA AGRUPACION DE PREMIOS
  );
                            

  
  TYPE tablaRegNivel IS TABLE OF tRegNivel;
  TYPE tablaRegPremio IS TABLE OF tRegPremio;
  TYPE tablaOidNivel IS TABLE OF INC_PARAM_NIVEL_PREMI.OID_PARA_NIVE_PREM%TYPE;

/**************************************************************************
Descripcion       : Este proceso replica al proceso P450 de GP4 de SICC, que
                    es el proceso Aplicar Requisito Premiacion solo para
                    Concursos Tipo Niveles y Premios Articulos

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais     :  Codigo Pais
  pnOidCliente     :  Oid Cliente
  pnOidSolicitud     :  Oid Solicitud
  psTipoCierre       : Tipo Cierre : NULL->Facturacion Diaria, Z->Zona,  P->Periodo 
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_APLIC_REQUI_PREMI(psCodigoPais               VARCHAR2,
                                   pnOidCliente               NUMBER,
                                   pnOidSolicitud             NUMBER,
                                   psTipoCierre               VARCHAR2,
                                   psCodigoPeriodo            VARCHAR2,
                                   psFechaFacturacion         VARCHAR2,
                                   psCodigoUsuario            VARCHAR2);
      
/**************************************************************************
Descripcion       : Valida si el Cliente cumple con los cursos exigidos por
                    el Concurso

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  oid Concurso
  pnOidCliente    :  oid Cliente

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION INC_FN_VALID_CURSO_EXIGI(pnOidConcurso      NUMBER,
                                  pnOidCliente       NUMBER)
RETURN VARCHAR2;   

/**************************************************************************
Descripcion       : Valida si el Cliente cumple con el pago a Tiempo exigido
                    por el Concurso

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  oid Concurso
  pnOidCliente    :  oid Cliente

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION INC_FN_VALID_PAGO_TIEMP(psCodigoPais       VARCHAR2,
                                 pnOidConcurso      NUMBER,
                                 pnOidCliente       NUMBER)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Valida si el Cliente cumple con la exigencia de 
                    productos por el Concurso

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  oid Concurso
  pnOidCliente    :  oid Cliente
  pnOidPeriodo    :  oid Periodo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION INC_FN_VALID_EXIGI_PRODU(pnOidConcurso      NUMBER,
                                  pnOidCliente       NUMBER,
                                  pnOidPeriodo       NUMBER)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Valida si el Cliente cumple con ser Activa en el rango 
                    de periodos del Concurso

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  oid Concurso
  pnOidCliente    :  oid Cliente
  pnOidPeriodo    :  oid Periodo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION INC_FN_VALID_ACTIV_CONCU(pnOidConcurso      NUMBER,
                                  pnOidCliente       NUMBER,
                                  pnOidPeriodo       NUMBER)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Valida si el Cliente cumple con ser Constante en el rango 
                    de periodos del Concurso

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  oid Concurso
  pnOidCliente    :  oid Cliente
  pnOidPeriodo    :  oid Periodo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION INC_FN_VALID_CONST_CONCU(pnOidConcurso      NUMBER,
                                  pnOidCliente       NUMBER,
                                  pnOidPeriodo       NUMBER)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Este proceso ejecuta logica relacionado a Programas 
                    Nuevas de Regalo

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais     :  Codigo Pais
  pnOidPeriodo     :  Oid Periodo
  pnOidConcurso  :  Oid Concurso
  pnOidCliente  :  Oid Cliente
  psFechaFacturacion : Fecha de Facturacion  
  psResultado  :  Resultado del proceso

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_PROGR_NUEVA_REGAL(psCodigoPais            VARCHAR2,
                                   pnOidPeriodo            NUMBER,
                                   pnOidConcurso           NUMBER,
                                   pnOidCliente            NUMBER,
                                   psFechaFacturacion      VARCHAR2,
                                   psResultado             OUT VARCHAR2);
   
/**************************************************************************
Descripcion       : Este proceso consiste transformar los puntos a Premios

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais    : Codigo de Pais
  pnOidSolicitud   :  Oid Solicitud
  pnOidConcurso     :  Oid Concurso
  pnOidCliente     :  Oid Cliente
  pnOidPeriodo     :  OidPeriodo
  pnSaldoPuntos  :  Saldo Puntos
  pnSaldoPuntosExig  :  Saldo Puntos Exigidos
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_TRANS_PUNTO_PREMI(psCodigoPais               VARCHAR2,
                                   pnOidSolicitud             NUMBER,
                                   pnOidConcurso              NUMBER,
                                   pnOidCliente               NUMBER,
                                   pnOidPeriodo               NUMBER,
                                   pnSaldoPuntos              NUMBER,
                                   pnSaldoPuntosExig          NUMBER,
                                   psCodigoUsuario            VARCHAR2,
                                   ptRegPremiosEscogidos      OUT tablaRegPremio);   
   
/**************************************************************************
Descripcion       : Recupera los niveles a entregar de acuerdo al puntaje
                    acumulado

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  Oid Concurso
  ptRegNivel    :  Lista de Niveles del Concurso
  ptOidNivel    :  Vector de Niveles a Premiar
  pnInicio      :  Nivel a Entregar
  pnPrimerNivel :  Primer Nivel Concurso
  
Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_TRATA_NIVEL_OBTEN(pnOidConcurso               NUMBER,
                                   ptRegNivel                  tablaRegNivel,
                                   ptOidNivel                  IN OUT tablaOidNivel,
                                   pnInicio                    NUMBER,
                                   pnPrimerNivel               NUMBER);   
   
/**************************************************************************
Descripcion       : Este proceso consiste transformar los puntos a Premios

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais    : Codigo de Pais
  pnOidSolicitud   :  Oid Solicitud
  pnOidConcurso     :  Oid Concurso
  pnOidCliente     :  Oid Cliente
  pnOidPeriodo     :  OidPeriodo
  pnSaldoPuntos  :  Saldo Puntos
  pnSaldoPuntosExig  :  Saldo Puntos Exigidos
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_DESPA_PREMI(psCodigoPais               VARCHAR2,
                             pnOidConcurso              NUMBER,
                             pnOidCliente               NUMBER,
                             pnOidPeriodo               NUMBER,
                             pnIndClieBloq              NUMBER,
                             psNumeroConcurso           VARCHAR2,
                             psCodigoRegion             VARCHAR2,
                             psCodigoUsuario            VARCHAR2,
                             psTipoCierre               VARCHAR2,
                             ptRegPremiosGanados        tablaRegPremio);

/**************************************************************************
Descripcion       : Realiza el calculo de puntajes de los pedidos O/C que se
                    encuentran en gp4.
Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion : Fecha de Facturacion  
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_APLIC_PREMI_FACTU(psCodigoPais               VARCHAR2,
                                   psCodigoMarca              VARCHAR2,
                                   psCodigoCanal              VARCHAR2,
                                   psCodigoPeriodo            VARCHAR2,
                                   psFechaFacturacion         VARCHAR2,
                                   psCodigoUsuario            VARCHAR2);
                                   
/**************************************************************************
Descripcion       : Realiza la premiacion de las consultoras que no han pasado
                    pedido y al cierre de zona seran premiadas si cumple las 
                    condiciones de premiacion de los concursos
Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion : Fecha de Facturacion  
  psCodigoZona     :  Codigo de zona
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_APLIC_PREMI_CZONA(psCodigoPais          VARCHAR2,
                                   psCodigoMarca         VARCHAR2,
                                   psCodigoCanal         VARCHAR2,
                                   psCodigoPeriodo       VARCHAR2,
                                   psFechaFacturacion    VARCHAR2, 
                                   psCodigoZona          VARCHAR2,
                                   psCodigoUsuario       VARCHAR2);
                                                                      
end INC_PKG_PROCE_PREMI;
/
create or replace package body INC_PKG_PROCE_PREMI is

/**************************************************************************
Descripcion       : Este proceso replica al proceso P450 de GP4 de SICC, que
                    es el proceso Aplicar Requisito Premiacion solo para
                    Concursos Tipo Niveles y Premios Articulos

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais     :  Codigo Pais
  pnOidCliente     :  Oid Cliente
  pnOidSolicitud     :  Oid Solicitud
  psTipoCierre       : Tipo Cierre : NULL->Facturacion Diaria, Z->Zona,  P->Periodo 
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_APLIC_REQUI_PREMI(psCodigoPais               VARCHAR2,
                                   pnOidCliente               NUMBER,
                                   pnOidSolicitud             NUMBER,
                                   psTipoCierre               VARCHAR2,
                                   psCodigoPeriodo            VARCHAR2,
                                   psFechaFacturacion         VARCHAR2,
                                   psCodigoUsuario            VARCHAR2)
IS
  lnOidPais             SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca            SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal            SEG_CANAL.OID_CANA%TYPE;

  lnOidPeriodo          PED_SOLIC_CABEC.PERD_OID_PERI%TYPE;
  lsCodPeriodoAnt       SEG_PERIO_CORPO.COD_PERI%TYPE;

  lnOidBaseCalc         INC_CONCU_PARAM_GENER.BCAL_OID_BASE_CALC%TYPE;
  lsNombreConcurso      INC_CONCU_PARAM_GENER.VAL_NOMB%TYPE;
  lsNumeroConcurso      INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
  lnOidPeriodoDesde     INC_CONCU_PARAM_GENER.PERD_OID_PERI_DESD%TYPE;
  lnOidPeriodoHasta     INC_CONCU_PARAM_GENER.PERD_OID_PERI_HAST%TYPE;
  lsCodPeriodoDesde     SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoHasta     SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnIndDuplaCyzone      INC_CONCU_PARAM_GENER.IND_DUPL_CYZO%TYPE;

  lnPuntajeTotal        NUMBER;
  lnOidConcurso         INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;

  lnOidSecuencia        NUMBER;
  lnOcurrencias         NUMBER;
  lsResult              VARCHAR2(1);
  lsResultNuevas        VARCHAR2(1);
 
--  lnOidPeriodoPriCon    MAE_CLIEN_PRIME_CONTA.PERD_OID_PERI%TYPE;
--  lnMontoPedPremi       NUMBER;
--  lsCodEmpleado         MAE_CLIEN_DATOS_ADICI.COD_EMPL%TYPE;
  lsCodigoZona          ZON_ZONA.COD_ZONA%TYPE;
  lsCodigoTerritorio    ZON_TERRI.COD_TERR%TYPE;
  lsCodigoRegion        ZON_REGIO.COD_REGI%TYPE;
  lnIndClieBloq         NUMBER;

  lnIndAsistCursos      INC_REQUI_PREMI.IND_ASIS_CURS%TYPE;
  lnIndPagoTiempo       INC_REQUI_PREMI.IND_PAGO_TIEM%TYPE;
  lnCuotaIngreso        INC_REQUI_PREMI.VAL_CUOT_INGR%TYPE;
  lnIndPeriDespExig     INC_PARAM_GENER_PREMI.IND_PERI_DESP_EXIG%TYPE;
  lnOidPeriDespacho     INC_PARAM_GENER_PREMI.PERD_OID_PERI%TYPE;
  lnOidPeriDespInic     INC_PARAM_GENER_PREMI.PERD_OID_PERI_INIC%TYPE;
  lnNumeroPeriodos      INC_PARAM_GENER_PREMI.NUM_PERI%TYPE;
  lnIndPedidoPeriodo    INC_REQUI_PREMI.IND_PEDI_PERI%TYPE;
  lnNumeroPedidos       INC_REQUI_PREMI.NUM_PEDI%TYPE;
  lnMontoMinPed         INC_REQUI_PREMI.VAL_MONT_MINI_PEDI%TYPE;
  lnMontoMinCon         INC_REQUI_PREMI.VAL_MONT_MINI_CONC%TYPE;
  lnIndDespCampPunt     INC_REQUI_PREMI.IND_DESP_CAMP_PUNT%TYPE;
          
  lnOidBaseIncumplida   INC_CANDI_GANAD.BINC_OID_BASE_INCU%TYPE;
  lnRequiPremSupe       INC_CANDI_GANAD.VAL_REQU_PREM_SUPE%TYPE;
  lnNumPeriEval         INC_CANDI_GANAD.NUM_PERI_EVAL%TYPE;
  lnOidPeriEval         INC_CANDI_GANAD.PERD_OID_PERI_EVAL%TYPE;
  
  lnIndActividad        INC_OBTEN_PUNTO.IND_ACTI%TYPE;
  lnIndConstancia       INC_OBTEN_PUNTO.IND_CONS%TYPE;
  lnIndPuntAcum         INC_OBTEN_PUNTO.VAL_PUNT_ACUM%TYPE;
  
  lsCodPeriEval         SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnCodPeriDespObte     SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidPeriDespObte     NUMBER;
             
  lnSaldoPuntosConcurso NUMBER;
  lnCantPedidos         NUMBER;
  lnMontoConcurso       NUMBER;
  lnMontoPedido         NUMBER;

  lbRechazado           BOOLEAN;
  lnRegla               NUMBER;
  lnUltPeriEval         NUMBER;
  lnBaseIncumplida      NUMBER;
  lbActCandiGanad       BOOLEAN;
  lsDescSolicitud       NUMBER;
  lbReqPremiSupe        BOOLEAN;
  lnReqPremiSupe        NUMBER;
  
  lnSumaPuntos          NUMBER;
  lnSumaPuntosBoni      NUMBER;
  lnSumaPuntosExig      NUMBER;
  lnTotalRegPed         NUMBER;
  
  lnSaldoPuntos         NUMBER;
  lnSaldoPuntosExig     NUMBER;

  CURSOR c_ConcursosOrdenCompra(oidPais NUMBER, oidMarca NUMBER, oidCanal NUMBER, 
                                oidCliente NUMBER, oidPeriodo NUMBER) IS
    SELECT T1.COPA_OID_PARA_GRAL
      FROM INC_CANDI_GANAD       T1,
           INC_CONCU_PARAM_GENER T2,
           INC_PARAM_GENER_PREMI PRE,
           INC_REQUI_PREMI       REQ
     WHERE PRE.COPA_OID_PARA_GRAL = T2.OID_PARA_GRAL
       AND (((oidPeriodo BETWEEN PRE.PERD_OID_PERI_INIC AND PRE.PERD_OID_PERI) AND
           PRE.PERD_OID_PERI_INIC IS NOT NULL) OR (PRE.PERD_OID_PERI IS NULL) OR
           ((PRE.PERD_OID_PERI_INIC IS NULL) AND PRE.PERD_OID_PERI = oidPeriodo))
       AND PRE.TPRM_OID_TIPO_PION <> 1
       AND T1.COPA_OID_PARA_GRAL = T2.OID_PARA_GRAL
       AND T2.PAIS_OID_PAIS = oidPais
       AND T2.CANA_OID_CANA = oidCanal
       AND T2.MARC_OID_MARC = oidMarca
       AND (T1.VAL_REQU_PREM_SUPE = 0 OR T2.BCAL_OID_BASE_CALC = 4)
       AND T1.BINC_OID_BASE_INCU IS NULL
       AND T2.DIRI_OID_DIRI = 1
       AND T1.CLIE_OID_CLIE = oidCliente
       AND T2.IND_ACTI = 1
       AND REQ.COPA_OID_PARA_GRAL(+) = T2.OID_PARA_GRAL
       AND NOT EXISTS
                     (SELECT *
                              FROM INC_DESCA DES
                             WHERE T1.CLIE_OID_CLIE = DES.CLIE_OID_CLIE
                               AND T1.COPA_OID_PARA_GRAL = DES.COPA_OID_PARA_GRAL)
     GROUP BY T1.COPA_OID_PARA_GRAL
     ORDER BY T1.COPA_OID_PARA_GRAL;

  TYPE t_oidConcurso         IS TABLE OF INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;

  v_oidConcurso              t_oidConcurso  := t_oidConcurso();
  ltRegPremiosEscogidos      tablaRegPremio;
  
BEGIN

  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

  --1) Obtenemos el oid Periodo Proceso
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);

/*   Variables eliminadas
  --2) Recuperar periodo primerContacto
  SELECT PERD_OID_PERI
    INTO lnOidPeriodoPriCon
    FROM MAE_CLIEN_PRIME_CONTA
   WHERE CLIE_OID_CLIE = pnOidCliente;
   
  --3) Recuperar Monto Pedido Premiacion
  SELECT DISTINCT SUM(VAL_MONT_TOTA) 
    INTO lnMontoPedPremi
    FROM PED_SOLIC_CABEC_ACUM2 A, CRA_PERIO B
   WHERE B.OID_PERI = lnOidPeriodo
     AND A.CLIE_OID_CLIE = pnOidCliente
     AND A.PERD_OID_PERI = B.OID_PERI;
  
  --4) Obtener codigo Empleado   
  SELECT COD_EMPL  
    INTO lsCodEmpleado
    FROM MAE_CLIEN_DATOS_ADICI  
   WHERE CLIE_OID_CLIE = pnOidCliente;
  */ 
  --5) Obtener Datos Personales
  -- Codigo Zona, Codigo Region y Codigo Territorio de la Consultora
  SELECT COD_ZONA, COD_TERR, COD_REGI
    INTO lsCodigoZona, 
         lsCodigoTerritorio, 
         lsCodigoRegion
  FROM (
  SELECT ZON.COD_ZONA, 
         TERR.COD_TERR, 
         REG.COD_REGI
    FROM MAE_CLIEN_UNIDA_ADMIN UNA,
         ZON_TERRI_ADMIN       TERRA,
         ZON_SECCI             SEC,
         ZON_ZONA              ZON,
         ZON_TERRI             TERR,
         ZON_REGIO             REG
   WHERE UNA.ZTAD_OID_TERR_ADMI = TERRA.OID_TERR_ADMI
     AND TERRA.TERR_OID_TERR = TERR.OID_TERR
     AND TERRA.ZSCC_OID_SECC = SEC.OID_SECC
     AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA
     AND ZON.ZORG_OID_REGI = REG.OID_REGI
     AND (UNA.PERD_OID_PERI_FIN IS NULL OR UNA.IND_ACTI=1)
     AND UNA.CLIE_OID_CLIE = pnOidCliente
     ORDER BY UNA.IND_ACTI DESC)
   WHERE ROWNUM = 1; 

  --6) Verifica si el cliente esta Bloqueado en MAE, para CO=CONCURSOS
  SELECT COUNT(1)
    INTO lnIndClieBloq
    FROM MAE_CLIEN_BLOQU CB, 
         MAE_ACCIO_PROCE_BLOQU APB, 
         MAE_ACCIO_BLOQU AB
   WHERE CB.FEC_DESB IS NULL
     AND APB.TIBQ_OID_TIPO_BLOQ = CB.TIBQ_OID_TIPO_BLOQ
     AND APB.PAIS_OID_PAIS = lnOidPais
     AND AB.OID_ACCI_BLOQ = APB.MABL_OID_ACCI_BLOQ
     AND AB.COD_ACCI_BLOQ = 'EN'
     AND APB.MAPB_OID_PROC_BLOQ =
         (SELECT PB.OID_PROC_BLOQ
            FROM MAE_PROCE_BLOQU PB
           WHERE PB.COD_PROC_BLOQ = 'CO')
     AND CB.CLIE_OID_CLIE = pnOidCliente;
     
  IF(lnIndClieBloq > 0) THEN
    lnIndClieBloq := 1;
  END IF;   
  
  --7) Verifica si el cliente esta Bloqueado en MAE, para CO=CONCURSOS   
  SELECT COUNT(1)
    INTO lnTotalRegPed
    FROM NVS_CONSU_REGAL_PEDID reg,
         MAE_CLIEN             cli,
         CUP_PROG_NUEVA_CUPON  cup,
         CRA_PERIO             per,
         SEG_PERIO_CORPO       cor,
         SEG_PAIS              pai
   WHERE reg.CONS_COD_CONS = cli.COD_CLIE
     AND reg.CONS_COD_PROG = cup.COD_PROG
     AND reg.COD_PERI_DESP = cor.COD_PERI
     AND reg.CONS_COD_PAIS = pai.COD_PAIS
     AND cor.OID_PERI = per.PERI_OID_PERI
     AND pai.OID_PAIS = lnOidPais
     AND per.OID_PERI = lnOidPeriodo
     AND cli.OID_CLIE = pnOidCliente
     AND reg.EST_REGA = '01'
     AND cup.IND_PREM_INCE = '1';

  --Recuperamos los Concursos de PREMIACION
  OPEN c_ConcursosOrdenCompra(lnOidPais, lnOidMarca, lnOidCanal, pnOidCliente, lnOidPeriodo);
  LOOP
    FETCH c_ConcursosOrdenCompra INTO lnOidConcurso;
    EXIT WHEN c_ConcursosOrdenCompra%NOTFOUND;

    v_oidConcurso.EXTEND(1);
    v_oidConcurso(v_oidConcurso.COUNT) := lnOidConcurso;

  END LOOP;
  CLOSE c_ConcursosOrdenCompra;


  --RECORREMOS LA LISTA DE CONCURSOS de PREMIACION
  FOR i IN 1..v_oidConcurso.COUNT LOOP
    lnOidConcurso := v_oidConcurso(i);
    lsResult := '1';
    lbRechazado := FALSE;
    lnRegla := NULL;
    lnUltPeriEval := NULL;
    lnBaseIncumplida := NULL;
    lbActCandiGanad := FALSE;
    lsDescSolicitud := NULL;
    lbReqPremiSupe := FALSE;
    
    --DBMS_OUTPUT.put_line('----------------------------------');
    --DBMS_OUTPUT.put_line('lnOidConcurso : ' || lnOidConcurso);

    --RECUPERAMOS DATOS DEL CONCURSO
    SELECT gen.BCAL_OID_BASE_CALC,
           gen.VAL_NOMB,
           gen.NUM_CONC,
           gen.PERD_OID_PERI_DESD,
           gen.PERD_OID_PERI_HAST,
           FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(gen.PERD_OID_PERI_DESD),
           FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(gen.PERD_OID_PERI_HAST),
           gen.IND_DUPL_CYZO,
           NVL(req.IND_ASIS_CURS, 0),
           NVL(req.IND_PAGO_TIEM, 0),
           NVL(req.VAL_CUOT_INGR, 0),
           NVL(pgp.IND_PERI_DESP_EXIG, 0),
           pgp.PERD_OID_PERI,
           pgp.PERD_OID_PERI_INIC,
           pgp.NUM_PERI,
           NVL(req.IND_PEDI_PERI, 0),
           NVL(req.NUM_PEDI, 0),
           NVL(req.VAL_MONT_MINI_PEDI, 0),
           NVL(req.VAL_MONT_MINI_CONC, 0),
           NVL(obt.IND_ACTI, 0),
           NVL(obt.IND_CONS, 0),
           req.IND_DESP_CAMP_PUNT,
           NVL(obt.VAL_PUNT_ACUM, 0)
      INTO lnOidBaseCalc,
           lsNombreConcurso,
           lsNumeroConcurso,
           lnOidPeriodoDesde,
           lnOidPeriodoHasta,
           lsCodPeriodoDesde,
           lsCodPeriodoHasta,
           lnIndDuplaCyzone,
           lnIndAsistCursos,
           lnIndPagoTiempo,
           lnCuotaIngreso,
           lnIndPeriDespExig,
           lnOidPeriDespacho,
           lnOidPeriDespInic,
           lnNumeroPeriodos,
           lnIndPedidoPeriodo,
           lnNumeroPedidos,
           lnMontoMinPed,
           lnMontoMinCon,
           lnIndActividad,
           lnIndConstancia,
           lnIndDespCampPunt,
           lnIndPuntAcum
      FROM INC_CONCU_PARAM_GENER gen,
           INC_OBTEN_PUNTO obt,
           INC_PRODU pro,
           INC_CLASI_CONCU cla,
           INC_REQUI_PREMI req,
           INC_PARAM_GENER_PREMI pgp
     WHERE gen.OID_PARA_GRAL = obt.COPA_OID_PARA_GRAL
       AND gen.OID_PARA_GRAL = lnOidConcurso
       AND gen.OID_PARA_GRAL = pro.COPA_OID_PARA_GRAL(+)
       AND gen.CCON_OID_CLAS_CONC = cla.OID_CLAS_CONC(+)
       AND gen.OID_PARA_GRAL = req.COPA_OID_PARA_GRAL(+)
       AND gen.OID_PARA_GRAL = pgp.COPA_OID_PARA_GRAL(+);
       
    --VALIDAR PREMIO ENTREGADO
    IF( (lnIndDespCampPunt IS NOT NULL) AND (lnIndDespCampPunt = 2)) THEN 
      lsResult := '0';
      lbRechazado := TRUE;
      --DBMS_OUTPUT.put_line('RECHAZADADO POR lnIndDespCampPunt = 2');
    END IF;    
    IF( ((lnIndDespCampPunt IS NULL) OR (lnIndDespCampPunt = 0)) AND
        ((lnOidBaseCalc = 1) OR (lnOidBaseCalc = 2))  ) THEN 
      
      SELECT COUNT(1)
        INTO lnOcurrencias
        FROM INC_GANAD GAN,
             INC_PARAM_NIVEL_PREMI NIVEL,
             INC_PARAM_GENER_PREMI GENP,
             INC_CONCU_PARAM_GENER PARAM
       WHERE GAN.PANP_OID_PARA_NIVE_PREM = NIVEL.OID_PARA_NIVE_PREM
         AND NIVEL.PAGP_OID_PARA_GENE_PREM = GENP.OID_PARA_GENE_PREM
         AND GENP.COPA_OID_PARA_GRAL = PARAM.OID_PARA_GRAL
         AND PARAM.OID_PARA_GRAL = lnOidConcurso
         AND GAN.CLIE_OID_CLIE = pnOidCliente;

      IF(lnOcurrencias > 0) THEN
        lsResult := '0';
        lbRechazado := TRUE;
        --DBMS_OUTPUT.put_line('RECHAZADADO POR lnIndDespCampPunt = 0 y Premio Entregado');
      END IF;
    END IF; 

    --VALIDAR EXIGENCIAS CURSOS
    IF((lsResult = '1') AND (lnIndAsistCursos = 1)) THEN
      lsResult := INC_FN_VALID_CURSO_EXIGI(lnOidConcurso, pnOidCliente);
      
      IF(lsResult = '0') THEN 
        lbRechazado := TRUE;
        
        lnRegla := REGLA_EXIG_CURSOS;
        lnUltPeriEval := lnOidPeriodo;
        lnBaseIncumplida := BASE_INCUM_ASISTENCIA_A_CURSOS;
        lbActCandiGanad := TRUE;
      END IF;   
    END IF;
    
    --VALIDAR PAGO A TIEMPO
    IF((lsResult = '1') AND (lnIndPagoTiempo = 1)) THEN
      lsResult := INC_FN_VALID_PAGO_TIEMP(psCodigoPais, lnOidConcurso, pnOidCliente);
      
      IF(lsResult = '0') THEN 
        lbRechazado := TRUE;
        
        lnRegla := REGLA_PAGO_TIEMPO;
        lnUltPeriEval := lnOidPeriodo;
        lnBaseIncumplida := BASE_INCUM_PAGO_A_TIEMPO;
        lbActCandiGanad := TRUE;
      END IF;  
    END IF;
  
    --VALIDAR EXIGENCIAS CUOTA MINIMA
    IF((lsResult = '1') AND (lnCuotaIngreso > 0)) THEN
      SELECT NVL(SUM(NUM_PUNT),0) 
        INTO lnSaldoPuntosConcurso
        FROM INC_CUENT_CORRI_PUNTO
       WHERE CLIE_OID_CLIE = pnOidCliente
         AND COPA_OID_PARA_GRAL = lnOidConcurso;
         
      IF(lnSaldoPuntosConcurso < lnCuotaIngreso) THEN
        lsResult:='0';
        lbRechazado := TRUE;
      
        lnRegla := REGLA_EXIG_CUOTA_MINIMA;
        lnUltPeriEval := lnOidPeriodo;
        lnBaseIncumplida := BASE_INCUM_EXIG_CONCURSO;
        lbActCandiGanad := TRUE;
      END IF;   
    END IF;
  
    --VALIDAR EXIGENCIAS PERIODO DESPACHO
    IF((lsResult = '1') AND ((lnIndPeriDespExig = 1) AND (lnOidPeriDespacho IS NOT NULL) AND
                     (lnNumeroPeriodos IS NULL) )) THEN

      IF(NOT ( ((lnOidPeriDespInic IS NULL) AND (lnOidPeriDespacho = lnOidPeriodo)) OR
          ((lnOidPeriDespInic IS NOT NULL) AND (lnOidPeriDespInic <= lnOidPeriodo) AND 
            (lnOidPeriodo<=lnOidPeriDespacho)))) THEN    
  
        lsResult := '0';
        lbRechazado := TRUE;
      
        lnRegla := REGLA_EXIG_PERIODO_DESPA;
        lnUltPeriEval := lnOidPeriodo;
        lnBaseIncumplida := NULL;
        lbActCandiGanad := TRUE;
      END IF;
    END IF;
    
    --VALIDAR EXIGENCIA PRODUCTOS
    IF((lsResult = '1') AND (lnIndPedidoPeriodo = 1)) THEN
      lsResult := INC_FN_VALID_EXIGI_PRODU(lnOidConcurso, pnOidCliente, lnOidPeriodo);
   
      IF(lsResult = '0') THEN
        lbRechazado := TRUE;
        
        lnRegla := REGLA_EXIG_PRODUCTOS;
        lnUltPeriEval := lnOidPeriodo;
        lnBaseIncumplida := BASE_INCUM_EXIG_PREMIACION;
        lbActCandiGanad := TRUE;
      END IF;  
    END IF;
    
    --VALIDAR EXIGENCIA NUMERO PEDIDOS CONCURSOS
    IF((lsResult = '1') AND (lnNumeroPedidos > 0)) THEN
      SELECT NVL(SUM(VAL_CANT_PEDI), 0) 
        INTO lnCantPedidos     
        FROM PED_SOLIC_CABEC_ACUM2 A,
             CRA_PERIO             B,
             SEG_PERIO_CORPO       C,
             CRA_PERIO             D,
             SEG_PERIO_CORPO       E,
             CRA_PERIO             F,
             SEG_PERIO_CORPO       G
       WHERE A.CLIE_OID_CLIE = pnOidCliente
         AND A.PERD_OID_PERI = B.OID_PERI
         AND B.PERI_OID_PERI = C.OID_PERI
         AND D.OID_PERI = lnOidPeriodoDesde
         AND D.PERI_OID_PERI = E.OID_PERI
         AND F.OID_PERI = lnOidPeriodoHasta
         AND F.PERI_OID_PERI = G.OID_PERI
         AND C.COD_PERI BETWEEN E.COD_PERI AND G.COD_PERI;
         
      IF(lnCantPedidos < lnNumeroPedidos) THEN
        lsResult:='0';
        lbRechazado := TRUE;
      
        lnRegla := REGLA_EXIG_NUMERO_PEDIDOS;
        lnUltPeriEval := lnOidPeriodo;
        lnBaseIncumplida := BASE_INCUM_EXIG_CONCURSO;
        lbActCandiGanad := TRUE;
      END IF;   
    END IF;

    --VALIDAR EXIGENCIAS MONTO MINIMO PREMIACION
    IF((lsResult = '1') AND (lnIndPedidoPeriodo = 1) AND (lnMontoMinPed > 0)) THEN
      SELECT DISTINCT NVL(SUM(VAL_MONT_TOTA),0)
        INTO lnMontoPedido
        FROM PED_SOLIC_CABEC_ACUM2 A, 
             CRA_PERIO B
       WHERE B.OID_PERI = lnOidPeriodo
         AND A.CLIE_OID_CLIE = pnOidCliente
         AND A.PERD_OID_PERI = B.OID_PERI;

      IF(lnMontoPedido < lnMontoMinPed) THEN
        lsResult:='0';
        lbRechazado := TRUE;
      
        lnRegla := REGLA_EXIG_MONTO_MIN_PREMI;
        lnUltPeriEval := lnOidPeriodo;
        lnBaseIncumplida := BASE_INCUM_EXIG_PREMIACION;
        lbActCandiGanad := TRUE;
      END IF;   
    END IF;

    --VALIDAR EXIGENCIA MONTO MINIMO CONCURSOS
    IF((lsResult = '1') AND (lnMontoMinCon > 0)) THEN
      SELECT NVL(SUM(VAL_MONT_TOTA), 0)
        INTO lnMontoConcurso  
        FROM PED_SOLIC_CABEC_ACUM2 A,
             CRA_PERIO             B,
             SEG_PERIO_CORPO       C,
             CRA_PERIO             D,
             SEG_PERIO_CORPO       E,
             CRA_PERIO             F,
             SEG_PERIO_CORPO       G
       WHERE A.CLIE_OID_CLIE = pnOidCliente
         AND A.PERD_OID_PERI = B.OID_PERI
         AND B.PERI_OID_PERI = C.OID_PERI
         AND D.OID_PERI = lnOidPeriodoDesde
         AND D.PERI_OID_PERI = E.OID_PERI
         AND F.OID_PERI = lnOidPeriodoHasta
         AND F.PERI_OID_PERI = G.OID_PERI
         AND C.COD_PERI BETWEEN E.COD_PERI AND G.COD_PERI;
         
      IF(lnMontoConcurso < lnMontoMinCon) THEN
        lsResult:='0';
        lbRechazado := TRUE;
      
        lnRegla := REGLA_EXIG_MONTO_MIN_CONCU;
        lnUltPeriEval := lnOidPeriodo;
        lnBaseIncumplida := BASE_INCUM_EXIG_CONCURSO;
        lbActCandiGanad := TRUE;
      END IF;   
    END IF;    
    
    --RECUPERA INFORMACION de INC_CANDI_GANAD
    SELECT BINC_OID_BASE_INCU,
           NVL(VAL_REQU_PREM_SUPE, 0),
           NVL(NUM_PERI_EVAL, 0),
           PERD_OID_PERI_EVAL
      INTO lnOidBaseIncumplida,
           lnRequiPremSupe,
           lnNumPeriEval,
           lnOidPeriEval     
      FROM (SELECT BINC_OID_BASE_INCU,
                   VAL_REQU_PREM_SUPE,
                   COD_PERI,
                   FEC_INIC,
                   FEC_FINA,
                   CANA_OID_CANA,
                   MARC_OID_MARC,
                   PAIS_OID_PAIS,
                   NUM_PERI_EVAL,
                   PERD_OID_PERI,
                   PERD_OID_PERI_EVAL
              FROM INC_CANDI_GANAD GAN, CRA_PERIO CRA, SEG_PERIO_CORPO SEG
             WHERE COPA_OID_PARA_GRAL = lnOidConcurso
               AND CLIE_OID_CLIE = pnOidCliente
               AND GAN.PERD_OID_PERI = CRA.OID_PERI
               AND CRA.PERI_OID_PERI = SEG.OID_PERI
               AND PAIS_OID_PAIS = lnOidPais
               AND MARC_OID_MARC = lnOidMarca
               AND CANA_OID_CANA = lnOidCanal
             ORDER BY num_peri_eval desc, VAL_REQU_PREM_SUPE)
    WHERE ROWNUM = 1;         

    IF (lnNumeroPeriodos IS NOT NULL) THEN
      lsCodPeriEval := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(lnOidPeriEval);

      lnCodPeriDespObte := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodPeriEval,
                                                                 lnOidPais,
                                                                 lnOidMarca,
                                                                 lnOidCanal,
                                                                 lnNumPeriEval);
                                                                 
      lnOidPeriDespObte := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lnCodPeriDespObte,
                                                             lnOidMarca,
                                                             lnOidCanal);
    END IF;
    
    --VALIDAR CANDIDATA GANADORA
    IF (lsResult = '1') THEN
      IF(NOT ((lnRequiPremSupe = 0) AND (lnOidBaseIncumplida IS NULL) AND
         (((lnOidPeriEval IS NOT NULL) AND (lnOidPeriEval <= lnOidPeriodo)) OR
           (lnOidPeriEval IS NULL))) ) THEN 
        lsResult:='0'; 
        lbRechazado := TRUE;
      
        lnRegla := REGLA_VALI_CANDIDATA_GANADORA;
        --lnUltPeriEval := NULL;
        --lnBaseIncumplida := NULL;
        --lbActCandiGanad := TRUE;    
      END IF;  
    END IF;   

    --VALIDAR EXIGENCIAS PERIODO con NUMERO PERIODOS
    IF((lsResult = '1') AND ((lnIndPeriDespExig = 1) AND (lnNumeroPeriodos IS NOT NULL) AND
                     (lnOidPeriDespObte <> lnOidPeriodo)) ) THEN
                     
      lsResult := '0';
      lbRechazado := TRUE;
      
      lnRegla := REGLA_EXIG_NUMERO_PERIODOS;
      lnUltPeriEval := lnOidPeriodo;
      lnBaseIncumplida := NULL;
      lbActCandiGanad := TRUE;
    END IF;
    
    --VALIDAR ACTIVIDAD
    IF((lsResult = '1') AND (lnIndActividad = 1)) THEN
      lsResult := INC_FN_VALID_ACTIV_CONCU(lnOidConcurso, pnOidCliente, lnOidPeriodo);
      
      IF(lsResult = '0') THEN
        lbRechazado := TRUE;
        
        lnRegla := REGLA_VALI_ACTIVIDAD;
        lsDescSolicitud := CAUSA_DESCALIF_ACTIVIDAD;
        --lnUltPeriEval := lnOidPeriodo;
        --lnBaseIncumplida := NULL;
        --lbActCandiGanad := TRUE;
      END IF;  
    END IF;
    
    --VALIDAR CONSTANCIA
    IF((lsResult = '1') AND (lnIndConstancia = 1)) THEN
      lsResult := INC_FN_VALID_CONST_CONCU(lnOidConcurso, pnOidCliente, lnOidPeriodo);
      
      IF(lsResult = '0') THEN
        lbRechazado := TRUE;
        
        lnRegla := REGLA_VALI_CONSTANCIA;
        lsDescSolicitud := CAUSA_DESCALIF_CONSTANCIA;
        --lnUltPeriEval := lnOidPeriodo;
        --lnBaseIncumplida := NULL;
        --lbActCandiGanad := TRUE;
      END IF;  
    END IF;
    
    /*IF(lbRechazado) THEN
      DBMS_OUTPUT.put_line('lbRechazado : TRUE');
    ELSE
      DBMS_OUTPUT.put_line('lbRechazado : FALSE'); 
    END IF;*/
    
    --DBMS_OUTPUT.put_line('lnRegla : ' || lnRegla);
    --DBMS_OUTPUT.put_line('lsDescSolicitud : ' || lsDescSolicitud);
    --DBMS_OUTPUT.put_line('lnUltPeriEval : ' || lnUltPeriEval);
    --DBMS_OUTPUT.put_line('lnBaseIncumplida : ' || lnBaseIncumplida);
    
    /*IF(lbActCandiGanad) THEN
      DBMS_OUTPUT.put_line('lbActCandiGanad : TRUE');
    ELSE
      DBMS_OUTPUT.put_line('lbActCandiGanad : FALSE'); 
    END IF; */   
    
    --VALIDAMOS SI HA SIDO DESCALIFICADA x ACTIVIDAD o x CONSTANCIA
    IF(((lsDescSolicitud = CAUSA_DESCALIF_ACTIVIDAD) OR
        (lsDescSolicitud = CAUSA_DESCALIF_CONSTANCIA)) AND
        (((psTipoCierre IS NOT NULL) AND NOT(psTipoCierre = TIPO_CIERRE_ZONA)) OR
         (psTipoCierre IS NULL))) THEN
         
      INSERT INTO INC_DESCA
        (OID_DESC,
         FEC_DESC,
         CLIE_OID_CLIE,
         COPA_OID_PARA_GRAL,
         PERD_OID_PERI,
         CADE_OID_CAUS_DESC)
      VALUES
        (INC_CESC_SEQ.NEXTVAL,
         TRUNC(SYSDATE),
         pnOidCliente,
         lnOidConcurso,
         lnOidPeriodo,
         lsDescSolicitud);
           
      SELECT NVL(SUM(NUM_PUNT),0), 
             NVL(SUM(NUM_PUNT_BONI),0),         
             NVL(SUM(NUM_PUNT_EXIG),0)
        INTO lnSumaPuntos,
             lnSumaPuntosBoni,
             lnSumaPuntosExig     
        FROM INC_CUENT_CORRI_PUNTO 
       WHERE COPA_OID_PARA_GRAL = lnOidConcurso
         AND CLIE_OID_CLIE = pnOidCliente;
         
      IF((lnSumaPuntos > 0) OR (lnSumaPuntosBoni > 0) OR (lnSumaPuntosExig > 0)) THEN
        IF(lnSumaPuntos > 0) THEN
          lnSumaPuntos := (-1) * lnSumaPuntos;
        ELSE
          lnSumaPuntos := 0;
        END IF;
        
        IF(lnSumaPuntosBoni > 0) THEN
          lnSumaPuntosBoni := (-1) * lnSumaPuntosBoni;
        ELSE
          lnSumaPuntosBoni := 0;
        END IF;
        
        IF(lnSumaPuntosExig > 0) THEN
          lnSumaPuntosExig := (-1) * lnSumaPuntosExig;
        ELSE
          lnSumaPuntosExig := 0;
        END IF;
        
        lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
        
        INSERT INTO INC_CUENT_CORRI_PUNTO
          (OID_CUEN_CORR_PUNT,
           NUM_MOVI,
           NUM_PUNT,
           NUM_PUNT_EXIG,
           FEC_MOVI,
           COPA_OID_PARA_GRAL,
           CLIE_OID_CLIE,
           PERD_OID_PERI,
           TMOV_OID_TIPO_MOVI,
           FEC_ULTI_ACTU,
           VAL_DESC,
           USU_MODI,
           NUM_PUNT_BONI)
        VALUES
          (lnOidSecuencia,
           lnOidSecuencia,
           lnSumaPuntos,
           lnSumaPuntosExig,
           TRUNC(SYSDATE),
           lnOidConcurso,
           pnOidCliente,
           lnOidPeriodo,
           2,
           SYSDATE,
           DECODE(lsDescSolicitud, CAUSA_DESCALIF_ACTIVIDAD, 'Por falta de Actividad',
                  'Por falta de Constancia'),
           psCodigoUsuario,
           lnSumaPuntosBoni);
        
      END IF;   
               
    END IF;
    
    IF((NOT lbRechazado) AND
       (((psTipoCierre IS NOT NULL) AND (psTipoCierre = TIPO_CIERRE_ZONA)) OR
         (psTipoCierre IS NULL))) THEN
         
      lbReqPremiSupe := TRUE;   
      lbActCandiGanad := TRUE;
      
      IF(lnOidPeriDespacho IS NOT NULL) THEN
        lnUltPeriEval := lnOidPeriDespacho;
      ELSE
        lnUltPeriEval := lnOidPeriodo;
      END IF;
      
    END IF;     

    /*IF(lbReqPremiSupe) THEN
      DBMS_OUTPUT.put_line('lbReqPremiSupe : TRUE');
    ELSE
      DBMS_OUTPUT.put_line('lbReqPremiSupe : FALSE'); 
    END IF;*/
    
    /*IF(lbActCandiGanad) THEN
      DBMS_OUTPUT.put_line('lbActCandiGanad (2) : TRUE');
    ELSE
      DBMS_OUTPUT.put_line('lbActCandiGanad (2) : FALSE'); 
    END IF;*/
    
    --ACTUALIZAR CANDIDATA GANADORA
    IF((lbActCandiGanad) OR
       ( (psTipoCierre IS NULL) OR
         (((psTipoCierre IS NOT NULL) AND (psTipoCierre = TIPO_CIERRE_PERIODO)) OR
          (lbReqPremiSupe AND (psTipoCierre IS NOT NULL) AND (psTipoCierre = TIPO_CIERRE_ZONA)) 
         ) 
       )) THEN
    
      IF(NOT lbReqPremiSupe) THEN
        IF(lbReqPremiSupe) THEN
          lnReqPremiSupe := 1;
        ELSE
          lnReqPremiSupe := 0;
        END IF;
        
        UPDATE INC_CANDI_GANAD CG
           SET CG.BINC_OID_BASE_INCU = (CASE WHEN ((psTipoCierre IS NULL) OR ((psTipoCierre IS NOT NULL)
                                                                AND (psTipoCierre = TIPO_CIERRE_PERIODO) ) )  
                                             THEN lnBaseIncumplida
                                             ELSE CG.BINC_OID_BASE_INCU
                                        END),
               CG.VAL_REQU_PREM_SUPE = (CASE WHEN (lnReqPremiSupe=1) AND
                                                  ((psTipoCierre IS NULL) OR ((psTipoCierre IS NOT NULL)
                                                                AND (psTipoCierre = TIPO_CIERRE_ZONA) ) )  
                                             THEN 1
                                             ELSE 0
                                        END),
               CG.PERD_OID_PERI_EVAL = NVL(lnUltPeriEval, CG.PERD_OID_PERI_EVAL),
               CG.REGL_OID_REGL      = (CASE WHEN ((psTipoCierre IS NULL) AND (lnRegla IS NOT NULL))
                                             THEN lnRegla
                                             ELSE CG.REGL_OID_REGL
                                        END)       
         WHERE CG.OID_CAND_GANA IN
               (SELECT OID_CAND_GANA
                  FROM INC_CANDI_GANAD CG, CRA_PERIO T5
                 WHERE CG.COPA_OID_PARA_GRAL = lnOidConcurso
                   AND CG.CLIE_OID_CLIE = pnOidCliente
                   AND CG.VAL_REQU_PREM_SUPE = 0
                   AND CG.BINC_OID_BASE_INCU IS NULL
                   AND CG.PERD_OID_PERI_EVAL = T5.OID_PERI(+)
                   AND ((SELECT T4.FEC_INIC
                           FROM CRA_PERIO T4
                          WHERE T4.OID_PERI = lnOidPeriodo) >= T5.FEC_INIC OR
                       CG.PERD_OID_PERI_EVAL is null));
                       
        IF((psTipoCierre IS NULL) AND (lnRegla IS NOT NULL)) THEN               
          UPDATE INC_PREMI_ELEGI
             SET ind_pend      = 4,
                 COD_MOTI_INVA = 10,
                 usu_modi      = psCodigoUsuario,
                 fec_modi      = SYSDATE
           WHERE copa_oid_para_gral = lnOidConcurso
             AND clie_oid_clie = pnOidCliente
             AND ind_pend = 1;
        END IF;     
           
      END IF;
    
    ELSE
      IF((psTipoCierre IS NULL) AND (lnRegla IS NOT NULL)) THEN
        UPDATE INC_CANDI_GANAD CG
           SET CG.REGL_OID_REGL = (CASE WHEN ((psTipoCierre IS NULL) AND (lnRegla IS NOT NULL))
                                             THEN lnRegla
                                             ELSE CG.REGL_OID_REGL
                                        END)       
         WHERE CG.OID_CAND_GANA IN
               (SELECT OID_CAND_GANA
                  FROM INC_CANDI_GANAD CG, CRA_PERIO T5
                 WHERE CG.COPA_OID_PARA_GRAL = lnOidConcurso
                   AND CG.CLIE_OID_CLIE = pnOidCliente
                   AND CG.VAL_REQU_PREM_SUPE = 0
                   AND CG.BINC_OID_BASE_INCU IS NULL
                   AND CG.PERD_OID_PERI_EVAL = T5.OID_PERI(+)
                   AND ((SELECT T4.FEC_INIC
                           FROM CRA_PERIO T4
                          WHERE T4.OID_PERI = lnOidPeriodo) >= T5.FEC_INIC OR
                       CG.PERD_OID_PERI_EVAL is null));
                       
        UPDATE INC_PREMI_ELEGI
             SET ind_pend      = 4,
                 COD_MOTI_INVA = 10,
                 usu_modi      = psCodigoUsuario,
                 fec_modi      = SYSDATE
           WHERE copa_oid_para_gral = lnOidConcurso
             AND clie_oid_clie = pnOidCliente
             AND ind_pend = 1;
                            
      END IF;
        
    END IF;
    
    IF(lbReqPremiSupe) THEN
      --TEMA DE PROGRAMAS NUEVAS REGALO
      SELECT COUNT(1)
        INTO lnTotalRegPed
        FROM NVS_CONSU_REGAL_PEDID reg,
             MAE_CLIEN             cli,
             CUP_PROG_NUEVA_CUPON  cup,
             CRA_PERIO             per,
             SEG_PERIO_CORPO       cor,
             SEG_PAIS              pai
       WHERE reg.CONS_COD_CONS = cli.COD_CLIE
         AND reg.CONS_COD_PROG = cup.COD_PROG
         AND reg.COD_PERI_DESP = cor.COD_PERI
         AND reg.CONS_COD_PAIS = pai.COD_PAIS
         AND cor.OID_PERI = per.PERI_OID_PERI
         AND pai.OID_PAIS = lnOidPais
         AND per.OID_PERI = lnOidPeriodo
         AND cli.OID_CLIE = pnOidCliente
         AND reg.EST_REGA = '01'
         AND cup.IND_PREM_INCE = '1';

      lsResultNuevas := '0';
        
      IF(lnTotalRegPed > 0) THEN 
        INC_PR_PROGR_NUEVA_REGAL(psCodigoPais, 
                                 lnOidPeriodo, 
                                 lnOidConcurso, 
                                 pnOidCliente,
                                 psFechaFacturacion,
                                 lsResultNuevas);
   
        NULL;
      END IF;
      
      IF(lsResultNuevas = '0') THEN
        --RECUPERAMOS EL VALOR de Saldo Puntos y Saldo Puntos Exigidos
        IF(lnIndPuntAcum = 0) THEN
          SELECT SUM (NUM_PUNT),
                 SUM (NUM_PUNT_EXIG)
            INTO lnSaldoPuntos,
                 lnSaldoPuntosExig
            FROM INC_CUENT_CORRI_PUNTO 
           WHERE CLIE_OID_CLIE = pnOidCliente
             AND COPA_OID_PARA_GRAL = lnOidConcurso
             AND PERD_OID_PERI = lnOidPeriodo;
        ELSE
          SELECT SUM (NUM_PUNT),
                 SUM (NUM_PUNT_EXIG)
            INTO lnSaldoPuntos,
                 lnSaldoPuntosExig
            FROM INC_CUENT_CORRI_PUNTO 
           WHERE CLIE_OID_CLIE = pnOidCliente
             AND COPA_OID_PARA_GRAL = lnOidConcurso;
        END IF;   

        --CASO DE USO: TRANSFORMAR PUNTOS A PREMIOS
        INC_PR_TRANS_PUNTO_PREMI(psCodigoPais,
                                 pnOidSolicitud,
                                 lnOidConcurso,
                                 pnOidCliente,
                                 lnOidPeriodo,
                                 lnSaldoPuntos,
                                 lnSaldoPuntosExig,
                                 psCodigoUsuario,
                                 ltRegPremiosEscogidos);
       
        --CASO DE USO: DESPACHAR PREMIOS
        IF(lbReqPremiSupe) THEN
          INC_PR_DESPA_PREMI(psCodigoPais,
                             lnOidConcurso,
                             pnOidCliente,
                             lnOidPeriodo,
                             lnIndClieBloq,
                             lsNumeroConcurso,
                             lsCodigoRegion,
                             psCodigoUsuario,
                             psTipoCierre,
                             ltRegPremiosEscogidos);
        END IF;                         
 
      END IF;
      
    END IF;
    

  END LOOP;

/*EXCEPTION
  WHEN OTHERS THEN
    NULL;*/

END INC_PR_APLIC_REQUI_PREMI;

/**************************************************************************
Descripcion       : Valida si el Cliente cumple con los cursos exigidos por
                    el Concurso

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  oid Concurso
  pnOidCliente    :  oid Cliente

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION INC_FN_VALID_CURSO_EXIGI(pnOidConcurso      NUMBER,
                                  pnOidCliente       NUMBER)
RETURN VARCHAR2 IS

  lnTotalOcurrencias     NUMBER;
BEGIN

  FOR x IN (SELECT T2.TICU_OID_TIPO_CURS
              FROM INC_REQUI_PREMI T1, INC_CURSO_EXIGI_PREMI T2
             WHERE T1.OID_REQU_PREM = T2.REPR_OID_REQU_PREM
               AND T1.COPA_OID_PARA_GRAL = pnOidConcurso) LOOP

    SELECT COUNT(1)
      INTO lnTotalOcurrencias
      FROM EDU_HISTO_CURSO his,
           EDU_MATRI_CURSO mat
     WHERE his.MCUR_OID_CURS = mat.OID_CURS
       AND his.CLIE_OID_CLIE = pnOidCliente    
       AND mat.TICU_OID_TIPO_CURS = x.TICU_OID_TIPO_CURS; 

    IF(lnTotalOcurrencias = 0) THEN
      RETURN '0';
    END IF;

  END LOOP;

  RETURN '1';
/*
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_VALID_CURSO_EXIGI: ' || ls_sqlerrm);
*/
END INC_FN_VALID_CURSO_EXIGI;

/**************************************************************************
Descripcion       : Valida si el Cliente cumple con el pago a Tiempo exigido
                    por el Concurso

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  oid Concurso
  pnOidCliente    :  oid Cliente

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION INC_FN_VALID_PAGO_TIEMP(psCodigoPais       VARCHAR2,
                                 pnOidConcurso      NUMBER,
                                 pnOidCliente       NUMBER)
RETURN VARCHAR2 IS

  lnOidPais              NUMBER;
  lnTotalOcurrencias     NUMBER;
  lnDiasGracia           NUMBER;
  ldFechaInicio          DATE;
  ldFechaFin             DATE;
BEGIN
  
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  
  SELECT NVL(NUM_DIAS_GRAC,0), 
         B.FEC_INIC, 
         C.FEC_FINA
    INTO lnDiasGracia, 
         ldFechaInicio, 
         ldFechaFin
    FROM INC_REQUI_PREMI RP, 
         CRA_PERIO B, 
         CRA_PERIO C
   WHERE RP.PERD_OID_PERI_DESD = B.OID_PERI
     AND RP.PERD_OID_PERI = C.OID_PERI
     AND COPA_OID_PARA_GRAL = pnOidConcurso;
       
     
  SELECT COUNT(1)
    INTO lnTotalOcurrencias
    FROM CCC_MOVIM_CUENT_CORRI A, CRA_PERIO B
   WHERE B.PAIS_OID_PAIS = lnOidPais
     AND A.CLIE_OID_CLIE = pnOidCliente
     AND B.FEC_INIC >= ldFechaInicio
     AND B.FEC_FINA <= ldFechaFin
     AND A.IMP_PEND = 0
     AND A.PERD_OID_PERI = B.OID_PERI
     AND A.FEC_VENC < TRUNC(SYSDATE) - lnDiasGracia
     AND A.FEC_ULTI_MOVI > (A.FEC_VENC + lnDiasGracia);

  IF(lnTotalOcurrencias = 0) THEN
    RETURN '1';
  ELSE
    RETURN '0';
  END IF;    

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_VALID_PAGO_TIEMP: ' || ls_sqlerrm);

END INC_FN_VALID_PAGO_TIEMP;

/**************************************************************************
Descripcion       : Valida si el Cliente cumple con la exigencia de 
                    productos por el Concurso

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  oid Concurso
  pnOidCliente    :  oid Cliente
  pnOidPeriodo    :  oid Periodo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION INC_FN_VALID_EXIGI_PRODU(pnOidConcurso      NUMBER,
                                  pnOidCliente       NUMBER,
                                  pnOidPeriodo       NUMBER)
RETURN VARCHAR2 IS

  lnTotalOcurrencias     NUMBER;
BEGIN

  FOR x IN (SELECT DISTINCT P.OID_PROD
              FROM INC_PRODU_EXIGI_PREMI T1, INC_REQUI_PREMI T2, MAE_PRODU P
             WHERE T1.REPR_OID_REQU_PREM = T2.OID_REQU_PREM
               AND T2.COPA_OID_PARA_GRAL = pnOidConcurso
               AND T1.MAPR_OID_MARC_PROD = P.MAPR_OID_MARC_PROD
               AND (T1.PROD_OID_PROD = P.OID_PROD OR T1.PROD_OID_PROD IS NULL)
               AND (T1.NEGO_OID_NEGO = P.NEGO_OID_NEGO OR T1.NEGO_OID_NEGO IS NULL)
               AND (T1.SGEN_OID_SUPE_GENE = P.SGEN_OID_SUPE_GENE OR
                   T1.SGEN_OID_SUPE_GENE IS NULL)
               AND (T1.UNEG_OID_UNID_NEGO = P.UNEG_OID_UNID_NEGO OR
                   T1.UNEG_OID_UNID_NEGO IS NULL)
               AND (T1.GENE_OID_GENE = P.GENE_OID_GENE OR T1.GENE_OID_GENE IS NULL)) LOOP
  
  
   SELECT COUNT(1)
     INTO lnTotalOcurrencias
     FROM PED_SOLIC_POSIC A,  PED_SOLIC_CABEC B 
    WHERE A.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE  
      AND B.CLIE_OID_CLIE = pnOidCliente 
      AND B.PERD_OID_PERI = pnOidPeriodo  
      AND A.PROD_OID_PROD = x.OID_PROD;
      
    IF(lnTotalOcurrencias = 0) THEN
      RETURN '0';
    END IF;
            
  END LOOP;             

  RETURN '1';
/*
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_VALID_EXIGI_PRODU: ' || ls_sqlerrm);
*/
END INC_FN_VALID_EXIGI_PRODU;

/**************************************************************************
Descripcion       : Valida si el Cliente cumple con ser Activa en el rango 
                    de periodos del Concurso

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  oid Concurso
  pnOidCliente    :  oid Cliente
  pnOidPeriodo    :  oid Periodo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION INC_FN_VALID_ACTIV_CONCU(pnOidConcurso      NUMBER,
                                  pnOidCliente       NUMBER,
                                  pnOidPeriodo       NUMBER)
RETURN VARCHAR2 IS

  lnTotalOcurrencias     NUMBER;
  lnOidPeriodoDesde      NUMBER;
  lnOidPeriodoHasta      NUMBER;
  lnOidPeriodoIngre      NUMBER;
  lnOidPeriodoIni        NUMBER;
  lnOidPeriodoFin        NUMBER;
  
  lnNoPasoPedidos        NUMBER;
BEGIN

  --Obtenemos el Periodo Desde y Periodo Hasta del Concurso
  SELECT PERD_OID_PERI_DESD,
         PERD_OID_PERI_HAST
    INTO lnOidPeriodoDesde,
         lnOidPeriodoHasta
    FROM INC_CONCU_PARAM_GENER
   WHERE OID_PARA_GRAL = pnOidConcurso;
  
  --Obtenemos el Periodo de Ingreso del Cliente
  SELECT PERD_OID_PERI
    INTO lnOidPeriodoIngre
    FROM MAE_CLIEN_PRIME_CONTA
   WHERE CLIE_OID_CLIE = pnOidCliente;
   
  --Determinamos el Periodo Inicio de Validacion 
  IF(lnOidPeriodoIngre < lnOidPeriodoDesde) THEN
    lnOidPeriodoIni := lnOidPeriodoDesde;
  ELSE
    lnOidPeriodoIni := lnOidPeriodoIngre;
  END IF;
  
  --Determinamos el Periodo Fin de Validacion
  IF(pnOidPeriodo <= lnOidPeriodoHasta) THEN
    lnOidPeriodoFin := pnOidPeriodo;
  ELSE
    lnOidPeriodoFin := lnOidPeriodoHasta;
  END IF;
   
  lnNoPasoPedidos := 0;
  
  FOR x IN (SELECT CPC.OID_PERI
              FROM CRA_PERIO CPA,
                   SEG_PERIO_CORPO SPA,
                   CRA_PERIO CPB,
                   SEG_PERIO_CORPO SPB,
                   CRA_PERIO CPC,
                   SEG_PERIO_CORPO SPC
             WHERE CPA.PERI_OID_PERI = SPA.OID_PERI
               AND CPB.PERI_OID_PERI = SPB.OID_PERI 
               AND CPC.PERI_OID_PERI = SPC.OID_PERI
               AND SPA.OID_PERI = lnOidPeriodoIni
               AND SPB.OID_PERI = lnOidPeriodoFin
               AND SPC.COD_PERI >= SPA.COD_PERI
               AND SPC.COD_PERI <= SPB.COD_PERI
             ORDER BY SPC.COD_PERI) LOOP
    
    SELECT COUNT(1)
      INTO lnTotalOcurrencias
      FROM PED_SOLIC_CABEC_ACUM2 A
     WHERE A.PERD_OID_PERI = x.OID_PERI
       AND A.CLIE_OID_CLIE = pnOidCliente
       AND A.val_cant_pedi > 0;

    --Validamos si no Paso pedido en un determinado Periodo
    IF(lnTotalOcurrencias = 0) THEN 
      lnNoPasoPedidos := lnNoPasoPedidos + 1;
      
      IF(lnNoPasoPedidos > 1) THEN
        RETURN '0';
      END IF;
    ELSE
      lnNoPasoPedidos := 0;    
    END IF;       
  END LOOP;             
  
  RETURN '1';
/*
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_VALID_ACTIV_CONCU: ' || ls_sqlerrm);
*/
END INC_FN_VALID_ACTIV_CONCU;

/**************************************************************************
Descripcion       : Valida si el Cliente cumple con ser Constante en el rango 
                    de periodos del Concurso

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  oid Concurso
  pnOidCliente    :  oid Cliente
  pnOidPeriodo    :  oid Periodo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION INC_FN_VALID_CONST_CONCU(pnOidConcurso      NUMBER,
                                  pnOidCliente       NUMBER,
                                  pnOidPeriodo       NUMBER)
RETURN VARCHAR2 IS

  lnTotalOcurrencias     NUMBER;
  lnOidPeriodoDesde      NUMBER;
  lnOidPeriodoHasta      NUMBER;
  lnOidPeriodoIngre      NUMBER;
  lnOidPeriodoIni        NUMBER;
  lnOidPeriodoFin        NUMBER;
  
BEGIN

  --Obtenemos el Periodo Desde y Periodo Hasta del Concurso
  SELECT PERD_OID_PERI_DESD,
         PERD_OID_PERI_HAST
    INTO lnOidPeriodoDesde,
         lnOidPeriodoHasta
    FROM INC_CONCU_PARAM_GENER
   WHERE OID_PARA_GRAL = pnOidConcurso;
  
  --Obtenemos el Periodo de Ingreso del Cliente
  SELECT PERD_OID_PERI
    INTO lnOidPeriodoIngre
    FROM MAE_CLIEN_PRIME_CONTA
   WHERE CLIE_OID_CLIE = pnOidCliente;
   
  --Determinamos el Periodo Inicio de Validacion 
  IF(lnOidPeriodoIngre < lnOidPeriodoDesde) THEN
    lnOidPeriodoIni := lnOidPeriodoDesde;
  ELSE
    lnOidPeriodoIni := lnOidPeriodoIngre;
  END IF;
  
  --Determinamos el Periodo Fin de Validacion
  IF(pnOidPeriodo <= lnOidPeriodoHasta) THEN
    lnOidPeriodoFin := pnOidPeriodo;
  ELSE
    lnOidPeriodoFin := lnOidPeriodoHasta;
  END IF;
   
  FOR x IN (SELECT CPC.OID_PERI
              FROM CRA_PERIO CPA,
                   SEG_PERIO_CORPO SPA,
                   CRA_PERIO CPB,
                   SEG_PERIO_CORPO SPB,
                   CRA_PERIO CPC,
                   SEG_PERIO_CORPO SPC
             WHERE CPA.PERI_OID_PERI = SPA.OID_PERI
               AND CPB.PERI_OID_PERI = SPB.OID_PERI 
               AND CPC.PERI_OID_PERI = SPC.OID_PERI
               AND SPA.OID_PERI = lnOidPeriodoIni
               AND SPB.OID_PERI = lnOidPeriodoFin
               AND SPC.COD_PERI >= SPA.COD_PERI
               AND SPC.COD_PERI <= SPB.COD_PERI
             ORDER BY SPC.COD_PERI) LOOP
    
    SELECT COUNT(1)
      INTO lnTotalOcurrencias
      FROM PED_SOLIC_CABEC_ACUM2 A
     WHERE A.PERD_OID_PERI = x.OID_PERI
       AND A.CLIE_OID_CLIE = pnOidCliente
       AND A.VAL_CANT_PEDI > 0;

    --Validamos si no Paso pedido en un determinado Periodo
    IF(lnTotalOcurrencias = 0) THEN 
      RETURN '0';
    END IF;
           
  END LOOP;             
  
  RETURN '1';
/*
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_VALID_CONST_CONCU: ' || ls_sqlerrm);
*/
END INC_FN_VALID_CONST_CONCU;

/**************************************************************************
Descripcion       : Este proceso ejecuta logica relacionado a Programas 
                    Nuevas de Regalo

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais     :  Codigo Pais
  pnOidPeriodo     :  Oid Periodo
  pnOidConcurso  :  Oid Concurso
  pnOidCliente  :  Oid Cliente
  psFechaFacturacion : Fecha de Facturacion
  psResultado  :  Resultado del proceso

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_PROGR_NUEVA_REGAL(psCodigoPais            VARCHAR2,
                                   pnOidPeriodo            NUMBER,
                                   pnOidConcurso           NUMBER,
                                   pnOidCliente            NUMBER,
                                   psFechaFacturacion      VARCHAR2,
                                   psResultado             OUT VARCHAR2)
IS
  lsResultado    VARCHAR2(1);
  lnpuntajetotal NUMBER(7);

  lnoidcausa         inc_causa_desca.oid_caus_desc%TYPE;
  lscodclasificacion inc_clasi_concu.cod_clas_conc%TYPE;
  lnoidsecuencia     inc_cuent_corri_punto.oid_cuen_corr_punt%TYPE;

BEGIN

  lsResultado := '0';

  BEGIN
    SELECT cla.cod_clas_conc
      INTO lscodclasificacion
      FROM inc_concu_param_gener gen,
           inc_clasi_concu       cla
     WHERE gen.ccon_oid_clas_conc = cla.oid_clas_conc
       AND gen.oid_para_gral = pnOidConcurso;
  EXCEPTION
    WHEN OTHERS THEN
      lscodclasificacion := NULL;
  END;

  IF ((lscodclasificacion = 'X') OR (lscodclasificacion = 'K')) THEN

    SELECT oid_caus_desc INTO lnoidcausa FROM inc_causa_desca WHERE cod_caus = 'PN';

    --Sumariza todos los puntajes del concurso
    SELECT nvl(SUM(num_punt),
               0)
      INTO lnpuntajetotal
      FROM inc_cuent_corri_punto
     WHERE clie_oid_clie = pnOidCliente
       AND copa_oid_para_gral = pnOidConcurso;

    INSERT INTO inc_desca
      (oid_desc,
       fec_desc,
       clie_oid_clie,
       copa_oid_para_gral,
       perd_oid_peri,
       cade_oid_caus_desc)
    VALUES
      (inc_cesc_seq.nextval,
       TO_DATE(psFechaFacturacion, 'dd/MM/yyyy'),
       pnOidCliente,
       pnOidConcurso,
       pnOidPeriodo,
       lnoidcausa);

    --Crear un nuevo registro en la tabla de Cuenta corriente:
    lnoidsecuencia := inc_cucp_seq.nextval;

    --Insertamos el puntaje en la Entidad Cuenta Corriente Puntos
    INSERT INTO inc_cuent_corri_punto
      (oid_cuen_corr_punt,
       num_movi,
       num_punt,
       num_punt_exig,
       fec_movi,
       copa_oid_para_gral,
       clie_oid_clie,
       perd_oid_peri,
       tmov_oid_tipo_movi,
       fec_ulti_actu,
       val_desc)
    VALUES
      (lnoidsecuencia,
       lnoidsecuencia,
       (-1) * lnpuntajetotal,
       0,
       TO_DATE(psFechaFacturacion, 'dd/MM/yyyy'),
       pnOidConcurso,
       pnOidCliente,
       pnOidPeriodo,
       2,
       SYSDATE,
       'Cargo Puntaje por atención Programa Nuevas');

    lsResultado := '1';

  END IF;

  psResultado := lsResultado;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    raise_application_error(-20123,
                            'ERROR INC_PR_PROGR_NUEVA_REGAL: ' || ls_sqlerrm);

END INC_PR_PROGR_NUEVA_REGAL;


/**************************************************************************
Descripcion       : Este proceso consiste transformar los puntos a Premios

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais    : Codigo de Pais
  pnOidSolicitud   :  Oid Solicitud
  pnOidConcurso     :  Oid Concurso
  pnOidCliente     :  Oid Cliente
  pnOidPeriodo     :  OidPeriodo
  pnSaldoPuntos  :  Saldo Puntos
  pnSaldoPuntosExig  :  Saldo Puntos Exigidos
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_TRANS_PUNTO_PREMI(psCodigoPais               VARCHAR2,
                                   pnOidSolicitud             NUMBER,
                                   pnOidConcurso              NUMBER,
                                   pnOidCliente               NUMBER,
                                   pnOidPeriodo               NUMBER,
                                   pnSaldoPuntos              NUMBER,
                                   pnSaldoPuntosExig          NUMBER,
                                   psCodigoUsuario            VARCHAR2,
                                   ptRegPremiosEscogidos      OUT tablaRegPremio)
IS
  lnOidPais                SEG_PAIS.OID_PAIS%TYPE;
  lnIndNivelesRotatorios   INC_PARAM_GENER_PREMI.IND_NIVE_ROTA%TYPE;
  lnNumeroRotaciones       INC_PARAM_GENER_PREMI.NUM_ROTA%TYPE;
  lnFactorConversion       INC_OBTEN_PUNTO.VAL_FACT_CONV%TYPE;
  lnPuntosAsignar          INC_OBTEN_PUNTO.NUM_PUNT_ASIG%TYPE;
  lnIndDuplaCyzone         INC_CONCU_PARAM_GENER.IND_DUPL_CYZO%TYPE;
  lnIndPremAcumNive        INC_PARAM_GENER_PREMI.IND_PREM_ACUM_NIVE%TYPE;
  lnOidPeriodoDespacho     INC_PARAM_GENER_PREMI.PERD_OID_PERI%TYPE;
  lnOidPeriodoGanadora     INC_GANAD.PERD_OID_PERI%TYPE;
  lnIndNoPremDefe          INC_PARAM_GENER_PREMI.IND_NOPR_DEFE%TYPE;
  
  lnRotaciones             NUMBER;
  lnSaldoCanjear           NUMBER;
  lnPuntosServicio         NUMBER;
  lnCantidadFija           NUMBER;
  lnPuntosInicial          NUMBER;
  lnPuntosFinal            NUMBER;
  lnPuntos                 NUMBER;
  
  lnMeta                   INC_METAS_TIPO_VENTA.VAL_META%TYPE;   
  lnPuntosMeta             NUMBER;
  lnIncrementoSobreMeta    NUMBER;
  
  lbIndPuntosExigidos      BOOLEAN;
  lnNivelObtenidoExig      NUMBER;

  lnNivelObtenido          NUMBER;
  lbSeguir                 BOOLEAN;
  lbCanjeoPuntos           BOOLEAN;
  lnPrimerNivel            NUMBER;
  lnUltimoSaldoCanjeado    NUMBER;
  lnSaldoADescontar        NUMBER;
  lnNivel                  NUMBER;
  lnOidNivel               NUMBER;
  lnPosicion               NUMBER;
  lnPosicionAux            NUMBER;
  lnPosicionEleg           NUMBER;
  lnTotalElecSinPremio     NUMBER;
  
  lnUltimaRotacion         INC_ULTIM_ASIGN_PREMI.VAL_ULTI_ROTA%TYPE;
  lnUltimoNivel            INC_ULTIM_ASIGN_PREMI.VAL_ULTI_NIVE%TYPE;
  lnUltimoSaldo            INC_ULTIM_ASIGN_PREMI.VAL_ULTI_SALD%TYPE;
  
  lnNumeroUnidades         INC_PREMI_ARTIC.NUM_UNID%TYPE;
  lnNumeroPremio           INC_LOTE_PREMI_ARTIC.NUM_PREM%TYPE;
  lnOidLotePrem            INC_LOTE_PREMI_ARTIC.OID_LOTE_PREM_ARTI%TYPE;
  lsValiUpdaPend           BAS_PARAM_PAIS.VAL_PARA%TYPE;
                  
  ltRegNivel               tablaRegNivel := tablaRegNivel();
  ltRegPremio              tablaRegPremio := tablaRegPremio();
  lvNivelEntregar          tablaOidNivel  := tablaOidNivel();
  
  ltRegPremiosEscogidos    tablaRegPremio := tablaRegPremio();
  ltRegPremioDefecto       tRegPremio;      
  
  lnIndClieBloq            NUMBER;
  lnTotalPuntos            NUMBER := 0;
  lnAuxTotalPuntos         NUMBER := 0;
  lnAuxAnterior            NUMBER := -1;
  lnAuxActual              NUMBER := -1;     
  
  lnOidCuenta              NUMBER; 
     
BEGIN
  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  
  --RECUPERAMOS DATOS DEL CONCURSO PARA SER USADOS EN LA OBTENCION DE PREMIOS A ENTREGAR
  SELECT NVL(pgp.Ind_Nive_Rota, 0),
         NVL(pgp.num_rota, 0),
         obt.val_fact_conv,
         obt.num_punt_asig,
         NVL(gen.ind_dupl_cyzo,0),
         NVL(pgp.ind_prem_acum_nive,0),
         pgp.perd_oid_peri,
         NVL(pgp.IND_NOPR_DEFE, 0)
    INTO lnIndNivelesRotatorios,
         lnNumeroRotaciones,
         lnFactorConversion,
         lnPuntosAsignar,
         lnIndDuplaCyzone,
         lnIndPremAcumNive,
         lnOidPeriodoDespacho,
         lnIndNoPremDefe
    FROM INC_CONCU_PARAM_GENER gen,
         INC_OBTEN_PUNTO obt,
         INC_PARAM_GENER_PREMI pgp
   WHERE gen.OID_PARA_GRAL = obt.COPA_OID_PARA_GRAL
     AND gen.OID_PARA_GRAL = pnOidConcurso
     AND gen.OID_PARA_GRAL = pgp.COPA_OID_PARA_GRAL(+);
         
  --RECUPERAMOS LA ULTIMA ROTACION, ULTIMO NIVEL y ULTIMO SALDO     
  BEGIN
    SELECT VAL_ULTI_ROTA, 
           VAL_ULTI_NIVE, 
           VAL_ULTI_SALD
      INTO lnUltimaRotacion,
           lnUltimoNivel,
           lnUltimoSaldo
      FROM INC_ULTIM_ASIGN_PREMI
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND COPA_OID_PARA_GRAL = pnOidConcurso;
  EXCEPTION
    WHEN OTHERS THEN
      lnUltimaRotacion := 0;  
      lnUltimoNivel := 0;
      lnUltimoSaldo := 0;
  END;     

  lnRotaciones := 1;
  
  IF((lnIndNivelesRotatorios = 1) AND (lnNumeroRotaciones > 0)) THEN
    lnRotaciones := lnNumeroRotaciones;
  END IF;

  lnSaldoCanjear := pnSaldoPuntos; 
  lnSaldoCanjear := lnSaldoCanjear + lnUltimoSaldo; 
  lnRotaciones := lnRotaciones - lnUltimaRotacion; 
  
  --Recuperamos la Meta
  BEGIN
    SELECT VAL_META
      INTO lnMeta
      FROM INC_METAS_TIPO_VENTA V, 
           INC_CONCU_PARAM_GENER C
     WHERE V.COPA_OID_PARA_GRAL = pnOidConcurso
       AND V.CLIE_OID_CLIE = pnOidCliente
       AND C.OID_PARA_GRAL = V.COPA_OID_PARA_GRAL
       AND C.PAIS_OID_PAIS = lnOidPais;
  EXCEPTION
    WHEN OTHERS THEN
      lnMeta := 0;     
  END;
  
  IF(lnMeta = 0) THEN
    BEGIN
      SELECT M.IMP_MONT_MINI 
        INTO lnMeta
        FROM INC_MONTO_MINIM_RANGO_CONSU M
       WHERE M.ESTV_OID_ESTA_VENT IN
             (SELECT OID_ESTA_VENT
                FROM INC_ESTAT_VENTA
               WHERE COPA_OID_PARA_GRAL = pnOidConcurso
                 AND ESTA_OID_ESTA_CLIE = 2);                
    EXCEPTION
      WHEN OTHERS THEN
        lnMeta := 0; 
    END;    
  END IF;
  
  --(1) PuntosMeta = ( Meta / FactorConversion ) * Puntos_a_Asignar
  lnPuntosMeta := ROUND(lnMeta / lnFactorConversion) * lnPuntosAsignar;
  
  --(2) IncrementoSobreMeta = SaldoPunto - PuntosMeta
  lnIncrementoSobreMeta := lnSaldoCanjear - lnPuntosMeta;
  
  --(3) SaldoCanjear = IncrementoSobreMeta 
  lnSaldoCanjear := lnIncrementoSobreMeta;
  
  lbIndPuntosExigidos := FALSE;
  lnNivelObtenidoExig := 0;
  FOR x IN (SELECT PNP.OID_PARA_NIVE_PREM,
                   NVL(PNP.VAL_PUNT_SERV, 0) VAL_PUNT_SERV,
                   NVL(PNP.NUM_CANT_FIJA_PUNT, 0) NUM_CANT_FIJA_PUNT,
                   NVL(PNP.NUM_CANT_INIC_PUNT, 0) NUM_CANT_INIC_PUNT,
                   NVL(PNP.NUM_CANT_FINA_PUNT, 0) NUM_CANT_FINA_PUNT,
                   NVL(NUM_PUNT_PROD_EXIG, 0) NUM_PUNT_PROD_EXIG,
                   NVL(PNP.NUM_CANT_FIJA_PUNT, PNP.NUM_CANT_INIC_PUNT) AS PUNTOS,
                   PNP.TPRE_OID_TIPO_PREM,
                   PNP.NUM_NIVE,
                   NVL(PNP.VAL_NIVE_SELE, 0) VAL_NIVE_SELE,
                   V.VAL_I18N descTipoPremio
              FROM INC_PARAM_NIVEL_PREMI PNP,
                   INC_PARAM_GENER_PREMI PGP,
                   INC_CONCU_PARAM_GENER conc,
                   V_GEN_I18N_SICC       V
             WHERE PNP.PAGP_OID_PARA_GENE_PREM = PGP.OID_PARA_GENE_PREM
               AND PGP.COPA_OID_PARA_GRAL = pnOidConcurso
               AND conc.OID_PARA_GRAL = PGP.COPA_OID_PARA_GRAL
               AND V.VAL_OID = TPRE_OID_TIPO_PREM
               AND V.ATTR_NUM_ATRI = 1
               AND V.ATTR_ENTI = 'INC_TIPO_PREMI'
               AND V.IDIO_OID_IDIO = 1
             ORDER BY PNP.NUM_NIVE) LOOP       
             
    ltRegNivel.EXTEND(1);
    ltRegNivel(ltRegNivel.LAST).OID_PARA_NIVE_PREM := x.OID_PARA_NIVE_PREM;
    ltRegNivel(ltRegNivel.LAST).VAL_PUNT_SERV := x.VAL_PUNT_SERV;
    ltRegNivel(ltRegNivel.LAST).NUM_CANT_FIJA_PUNT := x.NUM_CANT_FIJA_PUNT;
    ltRegNivel(ltRegNivel.LAST).NUM_CANT_INIC_PUNT := x.NUM_CANT_INIC_PUNT;
    ltRegNivel(ltRegNivel.LAST).NUM_CANT_FINA_PUNT := x.NUM_CANT_FINA_PUNT;
    ltRegNivel(ltRegNivel.LAST).NUM_PUNT_PROD_EXIG := x.NUM_PUNT_PROD_EXIG;
    ltRegNivel(ltRegNivel.LAST).PUNTOS := x.PUNTOS;
    ltRegNivel(ltRegNivel.LAST).TPRE_OID_TIPO_PREM := x.TPRE_OID_TIPO_PREM;
    ltRegNivel(ltRegNivel.LAST).NUM_NIVE := x.NUM_NIVE;
    ltRegNivel(ltRegNivel.LAST).VAL_NIVE_SELE := x.VAL_NIVE_SELE;         
             
    IF(x.NUM_PUNT_PROD_EXIG > 0) THEN
      lbIndPuntosExigidos := TRUE;
    END IF;
    
    IF(pnSaldoPuntosExig >= x.NUM_PUNT_PROD_EXIG) THEN
      lnNivelObtenidoExig := x.NUM_NIVE;
    END IF;
         
  END LOOP;             

  --DBMS_OUTPUT.put_line('pnSaldoPuntosExig : ' || pnSaldoPuntosExig);
  /*IF(lbIndPuntosExigidos) THEN DBMS_OUTPUT.put_line('lbIndPuntosExigidos : TRUE '); END IF;*/
  --DBMS_OUTPUT.put_line('lnNivelObtenidoExig : ' || lnNivelObtenidoExig);

  IF(lbIndPuntosExigidos AND (lnNivelObtenidoExig = 0)) THEN
    --DBMS_OUTPUT.put_line('NO CUMPLIO CON PRODUCTOS EXIGIDOS');
    lnNivelObtenido := 0;
    
  ELSE
    lbSeguir := TRUE;
    lbCanjeoPuntos := FALSE;
    lnPrimerNivel := lnUltimoNivel; --ESTO HAY QUE VER DE DONDE SE RECUPERARARA
    lnUltimoSaldoCanjeado := 0;
    lnNivelObtenido := 0;
    lnUltimoNivel := ltRegNivel(ltRegNivel.LAST).NUM_NIVE;
    lnSaldoADescontar := 0;
    lnSaldoADescontar := lnSaldoADescontar - lnUltimoSaldo; --ESTO HAY QUE VER DE DONDE SE RECUPERARARA
    lnNivel := 0;
    lnUltimaRotacion := lnUltimaRotacion; --ESTO HAY QUE VER DE DONDE SE RECUPERARARA
  
    --DBMS_OUTPUT.put_line('lnUltimoNivel : ' || lnUltimoNivel);
    --DBMS_OUTPUT.put_line('lnPrimerNivel : ' || lnPrimerNivel);
    --DBMS_OUTPUT.put_line('lnSaldoCanjear : ' || lnSaldoCanjear);
    --DBMS_OUTPUT.put_line('lnInicial : ' || ltRegNivel(lnPrimerNivel + 1).NUM_NIVE);
    
    WHILE((lnRotaciones != 0) AND (lbSeguir) AND (
           ((ltRegNivel(lnPrimerNivel + 1).NUM_CANT_FIJA_PUNT > 0) AND
           ((lnSaldoCanjear + ltRegNivel(lnPrimerNivel + 1).VAL_PUNT_SERV) >= ltRegNivel(lnPrimerNivel + 1).NUM_CANT_FIJA_PUNT)) OR
           (lnSaldoCanjear >= ltRegNivel(lnPrimerNivel + 1).NUM_CANT_INIC_PUNT)) ) LOOP
    
      lnNivel := lnPrimerNivel;
      
      WHILE((lnUltimoNivel != lnNivel) AND (lnNivelObtenido = 0)) LOOP
        lnPuntosServicio := ltRegNivel(lnNivel+1).VAL_PUNT_SERV;
        lnCantidadFija := ltRegNivel(lnNivel+1).NUM_CANT_FIJA_PUNT;
        lnPuntosInicial := ltRegNivel(lnNivel+1).NUM_CANT_INIC_PUNT;
        lnPuntosFinal := ltRegNivel(lnNivel+1).NUM_CANT_FINA_PUNT;
        lnPuntos := ltRegNivel(lnNivel+1).PUNTOS;
        lnNivel := lnNivel + 1; 
        
        --DBMS_OUTPUT.put_line('lnNivel : ' || lnNivel);
        --DBMS_OUTPUT.put_line('lnPuntosServicio : ' || lnPuntosServicio);
        --DBMS_OUTPUT.put_line('lnCantidadFija : ' || lnCantidadFija);
        --DBMS_OUTPUT.put_line('lnPuntosInicial : ' || lnPuntosInicial);
        --DBMS_OUTPUT.put_line('lnPuntosFinal : ' || lnPuntosFinal);
        --DBMS_OUTPUT.put_line('lnPuntos : ' || lnPuntos);
        --DBMS_OUTPUT.put_line('lnSaldoCanjear : ' || lnSaldoCanjear);
        --DBMS_OUTPUT.put_line('lnRotaciones : ' || lnRotaciones);
        
        IF((lnUltimoNivel != lnNivel) AND (((lnCantidadFija > 0) AND (lnSaldoCanjear + lnPuntosServicio) >= lnCantidadFija) OR
              ((lnSaldoCanjear >= lnPuntosInicial) AND (lnSaldoCanjear <= lnPuntosFinal)) OR
              (lbIndPuntosExigidos AND (lnNivelObtenidoExig = lnNivel))
              )) THEN      
              
          --DBMS_OUTPUT.put_line('*** Entro 1: ' || lnNivel);                                     

          lnNivelObtenido := lnNivel;
          lbCanjeoPuntos := TRUE;
          lnSaldoCanjear := lnSaldoCanjear - lnPuntos;
          lnUltimoSaldoCanjeado := lnPuntos;
          lnSaldoADescontar := lnSaldoADescontar + lnPuntos;
          
          INC_PR_TRATA_NIVEL_OBTEN(pnOidConcurso, ltRegNivel, lvNivelEntregar, lnNivel, lnPrimerNivel + 1);
        END IF;
        
        --DBMS_OUTPUT.put_line('*** lnNivelObtenido: ' || lnNivelObtenido);
        
        IF((lnUltimoNivel <> lnNivelObtenido) AND (lnNivelObtenido > 0) AND (lnIndDuplaCyzone = 0)) THEN
          --DBMS_OUTPUT.put_line('*** Entro 2: ' || lnNivel); 
          lbSeguir := FALSE;
        END IF;
    
        IF ((lnUltimoNivel = lnNivel) AND (((lnCantidadFija > 0) AND (lnSaldoCanjear >= lnCantidadFija)) OR
            (lnSaldoCanjear >= lnPuntosInicial))) THEN
        
          --DBMS_OUTPUT.put_line('*** Entro 3: ' || lnNivel);
                                             
          lnNivelObtenido := lnNivel;
          lbCanjeoPuntos := TRUE;
          lnSaldoCanjear := lnSaldoCanjear - lnPuntos;
          lnUltimoSaldoCanjeado := lnPuntos;

          lnSaldoADescontar := lnSaldoADescontar + lnPuntos;
          INC_PR_TRATA_NIVEL_OBTEN(pnOidConcurso, ltRegNivel, lvNivelEntregar, lnNivel, lnPrimerNivel + 1);            
        END IF;
                                
      END LOOP;
   
      lnPrimerNivel := 0;
                
      IF (lnIndDuplaCyzone = 1) THEN
        --DBMS_OUTPUT.put_line('lbIndDuplaCyzone ');
        lnNivelObtenido := 0;
        lnRotaciones := lnRotaciones - 1;
      END IF;
                
      IF (lnNivelObtenido = lnUltimoNivel) THEN
          --DBMS_OUTPUT.put_line('lnNivelObtenido == lnUltimoNivel');
          lbSeguir := TRUE;
          lnRotaciones := lnRotaciones - 1;
          lnUltimaRotacion := lnUltimaRotacion + 1;
          lnUltimoSaldoCanjeado := 0;
          lnNivelObtenido := 0;
      END IF;
          
    END LOOP;
 
    --OBTENEMOS INFORMACION DE LOS PREMIOS ELEGIDOS
    FOR p IN (SELECT TPRE_OID_TIPO_PREM,
                   pnp.NUM_NIVE,
                   NUM_CANT_FIJA_PUNT,
                   NUM_CANT_INIC_PUNT,
                   NUM_CANT_FINA_PUNT,
                   OID_PARA_NIVE_PREM,
                   NUM_PUNT_PROD_EXIG,
                   pgp.PERD_OID_PERI,
                   NUM_PREM,
                   pe.OID_PREM_ELEG,
                   pgp.COPA_OID_PARA_GRAL,
                   NVL(pnp.VAL_NIVE_SELE, 0) VAL_NIVE_SELE
              FROM INC_PREMI_ELEGI       pe,
                   INC_PARAM_NIVEL_PREMI pnp,
                   INC_PARAM_GENER_PREMI pgp,
                   INC_CONCU_PARAM_GENER conc,
                   CRA_PERIO             cp,
                   V_GEN_I18N_SICC       V
             WHERE PANP_OID_PARA_NIVE_PREM = OID_PARA_NIVE_PREM
               AND CLIE_OID_CLIE = pnOidCliente
               AND pe.COPA_OID_PARA_GRAL = pnOidConcurso
               AND pnp.PAGP_OID_PARA_GENE_PREM = pgp.OID_PARA_GENE_PREM
               AND conc.OID_PARA_GRAL = pgp.COPA_OID_PARA_GRAL
               AND conc.PERD_OID_PERI_DESD = OID_PERI
               AND V.VAL_OID = TPRE_OID_TIPO_PREM
               AND V.ATTR_NUM_ATRI = 1
               AND V.ATTR_ENTI = 'INC_TIPO_PREMI'
               AND V.IDIO_OID_IDIO = 1
               AND IND_PEND = 1
               AND (pe.PERD_OID_PERI_IATN IS NULL OR pe.PERD_OID_PERI_IATN <= pnOidPeriodo)) LOOP
               
      ltRegPremio.EXTEND(1);
      ltRegPremio(ltRegPremio.LAST).NUM_NIVE := p.NUM_NIVE;

      --INICIALIZANDO INDICADORES DEL PREMIO
      ltRegPremio(ltRegPremio.LAST).IND_PEND := 1;    
      ltRegPremio(ltRegPremio.LAST).IND_ATEN_PREM := 1;           
      ltRegPremio(ltRegPremio.LAST).IND_ELIM := 0;               
      ltRegPremio(ltRegPremio.LAST).IND_RECH := 0;               
      ltRegPremio(ltRegPremio.LAST).IND_ACTU_GANA := 0;                             
      
      IF(p.COPA_OID_PARA_GRAL <> pnOidConcurso) THEN
        ltRegPremio(ltRegPremio.LAST).COD_MOTI_INVA := 1;
        
      ELSE
        ltRegPremio(ltRegPremio.LAST).NUM_CANT_FIJA_PUNT := p.NUM_CANT_FIJA_PUNT;
        ltRegPremio(ltRegPremio.LAST).NUM_CANT_INIC_PUNT := p.NUM_CANT_INIC_PUNT;
        ltRegPremio(ltRegPremio.LAST).NUM_CANT_FINA_PUNT := p.NUM_CANT_FINA_PUNT;
        ltRegPremio(ltRegPremio.LAST).OID_PARA_NIVE_PREM := p.OID_PARA_NIVE_PREM;
        ltRegPremio(ltRegPremio.LAST).NUM_PUNT_PROD_EXIG := p.NUM_PUNT_PROD_EXIG;
        ltRegPremio(ltRegPremio.LAST).NUM_PREM := p.NUM_PREM;  
      
        ltRegPremio(ltRegPremio.LAST).OID_PERI := p.PERD_OID_PERI; 
        ltRegPremio(ltRegPremio.LAST).OID_PREM_ELEG := p.OID_PREM_ELEG; 
        
      END IF;  
      
      IF(p.VAL_NIVE_SELE = 0) THEN
        ltRegPremio(ltRegPremio.LAST).COD_MOTI_INVA := 4;
      ELSE
        BEGIN
          SELECT NUM_UNID,
                 NUM_PREM,
                 OID_LOTE_PREM_ARTI
            INTO lnNumeroUnidades,
                 lnNumeroPremio,
                 lnOidLotePrem   
            FROM (SELECT NUM_UNID,
                         NUM_PREM,
                         OID_LOTE_PREM_ARTI
                    FROM INC_PREMI_ARTIC, INC_LOTE_PREMI_ARTIC
                   WHERE PRAR_OID_PREM_ARTI = OID_PREM_ARTI
                     AND PANP_OID_PARA_NIVE_PREM = p.OID_PARA_NIVE_PREM
                     AND NUM_PREM = p.NUM_PREM
                   ORDER BY NUM_LOTE)
           WHERE ROWNUM = 1;   
        EXCEPTION
          WHEN OTHERS THEN
            lnNumeroUnidades := NULL;
            lnNumeroPremio := NULL;
            lnOidLotePrem := NULL;
        END;        
        
        IF(lnNumeroPremio IS NULL) THEN
          ltRegPremio(ltRegPremio.LAST).COD_MOTI_INVA := 1;
        ELSE
          ltRegPremio(ltRegPremio.LAST).NUM_PREM := lnNumeroPremio; 
          ltRegPremio(ltRegPremio.LAST).NUM_UNID := lnNumeroUnidades; 
          ltRegPremio(ltRegPremio.LAST).articulos := tablaRegArticulo();
          
          FOR pa IN (SELECT IMP_PREC_PUBL, 
                            NUM_UNID, 
                            COD_VENT_FICT, 
                            PROD_OID_PROD, 
                            PRODU.COD_SAP, 
                            OID_ARTI_LOTE      
                       FROM INC_ARTIC_LOTE, MAE_PRODU PRODU, INC_CENTR_SERVI CS 
                      WHERE LOPA_OID_LOTE_PREM_ARTI = lnOidLotePrem
                        AND INC_ARTIC_LOTE.PROD_OID_PROD = PRODU.OID_PROD 
                        AND CESE_OID_CESE_ENTR = CS.OID_CENT_SERV(+) 
                   ORDER BY PROD_OID_PROD ) LOOP
                        
            ltRegPremio(ltRegPremio.LAST).articulos.EXTEND(1);
            lnPosicion := ltRegPremio(ltRegPremio.LAST).articulos.LAST;
            ltRegPremio(ltRegPremio.LAST).articulos(lnPosicion).IMP_PREC_PUBL := pa.IMP_PREC_PUBL; 
            ltRegPremio(ltRegPremio.LAST).articulos(lnPosicion).NUM_UNID := pa.NUM_UNID; 
            ltRegPremio(ltRegPremio.LAST).articulos(lnPosicion).COD_VENT_FICT := pa.COD_VENT_FICT; 
            ltRegPremio(ltRegPremio.LAST).articulos(lnPosicion).PROD_OID_PROD := pa.PROD_OID_PROD; 
            ltRegPremio(ltRegPremio.LAST).articulos(lnPosicion).COD_SAP := pa.COD_SAP;  
            ltRegPremio(ltRegPremio.LAST).articulos(lnPosicion).OID_ARTI_LOTE := pa.OID_ARTI_LOTE;     
          END LOOP;
          
        END IF;
        
      END IF;
               
    END LOOP;          
        
    IF((lbCanjeoPuntos) AND (lnIndDuplaCyzone=0) AND (lnIndPremAcumNive=1)) THEN
       
      DELETE FROM INC_ULTIM_ASIGN_PREMI 
       WHERE COPA_OID_PARA_GRAL = pnOidConcurso
         AND CLIE_OID_CLIE = pnOidCliente;
      
      INSERT INTO INC_ULTIM_ASIGN_PREMI 
        (OID_ULTI_ASIG_PREM, 
         PAIS_OID_PAIS, 
         COPA_OID_PARA_GRAL, 
         CLIE_OID_CLIE, 
         VAL_ULTI_ROTA, 
         VAL_ULTI_NIVE, 
         VAL_ULTI_SALD, 
         FEC_CREA ) 
      VALUES 
        (INC_IUAP_SEQ.nextval, 
         lnOidPais,
         pnOidConcurso,
         pnOidCliente,
         lnUltimaRotacion,
         lnNivelObtenido,
         lnUltimoSaldoCanjeado,
         SYSDATE);
         
    END IF;     

    lnTotalElecSinPremio := 0;
    --VALIDAMOS LOS PREMIOS A ENTREGAR, VERIFICANDO SI EXISTE PREMIO ELEGIDO y
    --SINO SE EL ENTREGA EL PREMIO x DEFECTO
    FOR k IN 1..lvNivelEntregar.COUNT LOOP
      lnOidNivel := lvNivelEntregar(k);
     
      FOR k1 IN 1..ltRegNivel.LAST LOOP
        IF(ltRegNivel(k1).OID_PARA_NIVE_PREM = lnOidNivel) THEN
          lnPosicion := k1;
        END IF;
        
      END LOOP;
      
      lnPosicionEleg := 0;
   
      FOR k2 IN 1..ltRegPremio.COUNT LOOP
       IF(ltRegPremio(k2).OID_PARA_NIVE_PREM = lnOidNivel) THEN     
          IF((ltRegPremio(k2).IND_PEND = 1) AND (ltRegPremio(k2).COD_MOTI_INVA IS NULL)) THEN        
            ltRegPremio(k2).IND_PEND := 0;
          
            lnPosicionEleg := k2;
          END IF;
        END IF;
        
        EXIT WHEN lnPosicionEleg > 0;
                    
      END LOOP;
  
      IF(lnPosicionEleg > 0) THEN --ENCONTRO PREMIO ELEGIDO     
        ltRegPremiosEscogidos.EXTEND(1);
        ltRegPremiosEscogidos(ltRegPremiosEscogidos.LAST) := ltRegPremio(lnPosicionEleg);      
        ltRegPremiosEscogidos(ltRegPremiosEscogidos.LAST).IND_PEND := 0;

      ELSE
        ltRegPremioDefecto.NUM_PREM := NULL;
                  
        FOR pd IN (SELECT IMP_PREC_PUBL,
                         COD_VENT_FICT,
                         Z.PROD_OID_PROD,
                         B.NUM_UNID AS UNIDADES_PREMIO,
                         Z.NUM_UNID AS UNIDADES_ARTICULO,
                         A.NUM_LOTE,
                         A.NUM_PREM,
                         A.OID_LOTE_PREM_ARTI,
                         C.NUM_NIVE,
                         C.OID_PARA_NIVE_PREM,
                         C.NUM_CANT_FIJA_PUNT,
                         C.NUM_CANT_INIC_PUNT,
                         C.NUM_CANT_FINA_PUNT,
                         D.PERD_OID_PERI,
                         C.VAL_PUNT_SERV,
                         C.NUM_PUNT_PROD_EXIG,
                         C.TPRE_OID_TIPO_PREM,
                         PRODU.COD_SAP,
                         Z.OID_ARTI_LOTE
                    FROM INC_ARTIC_LOTE        Z,
                         INC_LOTE_PREMI_ARTIC  A,
                         INC_PREMI_ARTIC       B,
                         INC_PARAM_NIVEL_PREMI C,
                         INC_PARAM_GENER_PREMI D,
                         INC_CONCU_PARAM_GENER CONC,
                         CRA_PERIO             CP,
                         MAE_PRODU             PRODU,
                         INC_CENTR_SERVI       CS
                   WHERE PRAR_OID_PREM_ARTI = OID_PREM_ARTI
                     AND Z.PROD_OID_PROD = PRODU.OID_PROD
                     AND PANP_OID_PARA_NIVE_PREM = OID_PARA_NIVE_PREM
                     AND PAGP_OID_PARA_GENE_PREM = OID_PARA_GENE_PREM
                     AND CONC.PERD_OID_PERI_DESD = OID_PERI
                     AND PANP_OID_PARA_NIVE_PREM = lnOidNivel
                     AND COPA_OID_PARA_GRAL = pnOidConcurso
                     AND TPRE_OID_TIPO_PREM = 2
                     AND LOPA_OID_LOTE_PREM_ARTI = OID_LOTE_PREM_ARTI
                     AND CONC.OID_PARA_GRAL = D.COPA_OID_PARA_GRAL
                     AND A.NUM_PREM =
                         (SELECT MIN(A.NUM_PREM)
                            FROM INC_LOTE_PREMI_ARTIC  A,
                                 INC_PREMI_ARTIC       B,
                                 INC_PARAM_NIVEL_PREMI C
                           WHERE PRAR_OID_PREM_ARTI = OID_PREM_ARTI
                             AND PANP_OID_PARA_NIVE_PREM = lnOidNivel
                             AND TPRE_OID_TIPO_PREM = 2
                             AND PANP_OID_PARA_NIVE_PREM = OID_PARA_NIVE_PREM)
                     AND CESE_OID_CESE_ENTR = CS.OID_CENT_SERV(+)
                   ORDER BY NUM_PREM, PROD_OID_PROD) LOOP
        
          IF(ltRegPremioDefecto.NUM_PREM IS NULL) THEN

            ltRegPremioDefecto.NUM_NIVE := pd.NUM_NIVE;
            ltRegPremioDefecto.NUM_CANT_FIJA_PUNT := pd.NUM_CANT_FIJA_PUNT;
            ltRegPremioDefecto.NUM_CANT_INIC_PUNT := pd.NUM_CANT_INIC_PUNT;
            ltRegPremioDefecto.NUM_CANT_FINA_PUNT := pd.NUM_CANT_FINA_PUNT;
            ltRegPremioDefecto.OID_PARA_NIVE_PREM := pd.OID_PARA_NIVE_PREM;
            ltRegPremioDefecto.NUM_PUNT_PROD_EXIG := pd.NUM_PUNT_PROD_EXIG;
            ltRegPremioDefecto.NUM_PREM := pd.NUM_PREM;  
            ltRegPremioDefecto.OID_PERI := pd.PERD_OID_PERI;  
            ltRegPremioDefecto.NUM_UNID := pd.UNIDADES_PREMIO;
            ltRegPremioDefecto.OID_PREM := pd.OID_LOTE_PREM_ARTI;
            
            ltRegPremioDefecto.articulos.DELETE;
          END IF;  
                        
          ltRegPremioDefecto.articulos.EXTEND(1);
          lnPosicionAux := ltRegPremioDefecto.articulos.LAST;
          ltRegPremioDefecto.articulos(lnPosicionAux).IMP_PREC_PUBL := pd.IMP_PREC_PUBL; 
          ltRegPremioDefecto.articulos(lnPosicionAux).NUM_UNID := pd.UNIDADES_ARTICULO; 
          ltRegPremioDefecto.articulos(lnPosicionAux).COD_VENT_FICT := pd.COD_VENT_FICT; 
          ltRegPremioDefecto.articulos(lnPosicionAux).PROD_OID_PROD := pd.PROD_OID_PROD; 
          ltRegPremioDefecto.articulos(lnPosicionAux).COD_SAP := pd.COD_SAP;  
          ltRegPremioDefecto.articulos(lnPosicionAux).OID_ARTI_LOTE := pd.OID_ARTI_LOTE;  
                   
        END LOOP;           

        IF(ltRegPremioDefecto.NUM_PREM IS NOT NULL) THEN --ENCONTRAMOS PREMIOS x DEFECTO
          ltRegPremiosEscogidos.EXTEND(1);
          ltRegPremiosEscogidos(ltRegPremiosEscogidos.LAST) := ltRegPremioDefecto;
          ltRegPremiosEscogidos(ltRegPremiosEscogidos.LAST).IND_PEND := 0;
          
          IF((ltRegNivel(lnPosicion).VAL_NIVE_SELE = 1) AND (lnIndNoPremDefe = 1)) THEN
            ltRegPremiosEscogidos(ltRegPremiosEscogidos.LAST).IND_ATEN_PREM := 0;
            lnTotalElecSinPremio := lnTotalElecSinPremio + 1;
          END IF;  
        END IF;
       
      END IF;
      
    END LOOP;
    
    --SI TODOS LOS PREMIOS A ENTREGAR CORRESPONDE A NIVELES ELECTIVOS SIN PREMIO x DEFECTO
    --ENTONCES NO SE ATIENDE NINGUN PREMIO
    IF(lvNivelEntregar.COUNT = lnTotalElecSinPremio) THEN
      ltRegPremiosEscogidos.DELETE;
    END IF;
 
    IF(ltRegPremio.COUNT > 0) THEN
      SELECT NVL(GEN_PKG_GENER.GEN_FN_PARAM_PAIS(sp.COD_PAIS,'INC','007'),'0')
        INTO lsValiUpdaPend
        FROM seg_pais sp  
       WHERE sp.COD_PAIS = psCodigoPais;
    END IF;   
           
    --SE ACTUALIZA LOS DATOS DE LOS PREMIOS ELEGIDOS
    FOR kk IN 1..ltRegPremio.COUNT LOOP
      IF(ltRegPremio(kk).IND_PEND = 0) THEN
        UPDATE INC_PREMI_ELEGI 
           SET IND_PEND = 0, 
               COD_MOTI_INVA = NULL, 
               PERD_OID_PERI_IATN = pnOidPeriodo,
               USU_MODI = psCodigoUsuario, 
               FEC_MODI = SYSDATE 
           WHERE OID_PREM_ELEG = ltRegPremio(kk).OID_PREM_ELEG;
      END IF;
      
      IF((ltRegPremio(kk).COD_MOTI_INVA = 1) OR (ltRegPremio(kk).COD_MOTI_INVA = 4)) THEN
        UPDATE INC_PREMI_ELEGI 
           SET IND_PEND = 4, 
               COD_MOTI_INVA = ltRegPremio(kk).COD_MOTI_INVA, 
               USU_MODI = psCodigoUsuario, 
               FEC_MODI = SYSDATE 
         WHERE OID_PREM_ELEG = ltRegPremio(kk).OID_PREM_ELEG;
      
      END IF;
      
      IF((ltRegPremio(kk).COD_MOTI_INVA IS NULL) AND (ltRegPremio(kk).IND_PEND = 1)) THEN
        IF(lsValiUpdaPend = '1') THEN
          UPDATE INC_PREMI_ELEGI 
             SET COD_MOTI_INVA = 8, 
                 IND_PEND = 4, 
                 USU_MODI = psCodigoUsuario, 
                 FEC_MODI = SYSDATE 
             WHERE OID_PREM_ELEG = ltRegPremio(kk).OID_PREM_ELEG;
        ELSE
          UPDATE INC_PREMI_ELEGI 
             SET COD_MOTI_INVA = 8, 
                 USU_MODI = psCodigoUsuario, 
                 FEC_MODI = SYSDATE 
             WHERE OID_PREM_ELEG = ltRegPremio(kk).OID_PREM_ELEG;          
        END IF;
        
      END IF;
        
    END LOOP;
          
  END IF;

  --Validamos si el Cliente se encuentra Bloqueado
  SELECT COUNT(1)
    INTO lnIndClieBloq
    FROM MAE_CLIEN_BLOQU       CB,
         MAE_ACCIO_PROCE_BLOQU APB,
         MAE_ACCIO_BLOQU       AB
   WHERE CB.FEC_DESB IS NULL
     AND APB.TIBQ_OID_TIPO_BLOQ = CB.TIBQ_OID_TIPO_BLOQ
     AND APB.PAIS_OID_PAIS = lnOidPais
     AND AB.OID_ACCI_BLOQ = APB.MABL_OID_ACCI_BLOQ
     AND AB.COD_ACCI_BLOQ = 'EN'
     AND APB.MAPB_OID_PROC_BLOQ =
         (SELECT PB.OID_PROC_BLOQ
            FROM MAE_PROCE_BLOQU PB
           WHERE PB.COD_PROC_BLOQ = 'CO')
     AND CB.CLIE_OID_CLIE = pnOidCliente;
          
  IF(lnIndClieBloq > 0) THEN
    UPDATE INC_CANDI_GANAD CG 
       SET CG.BINC_OID_BASE_INCU = BASE_INCUM_CLIENTE_BLOQUEADO,
           CG.PERD_OID_PERI_EVAL = pnOidPeriodo
     WHERE CG.OID_CAND_GANA IN ( 
                               SELECT OID_CAND_GANA 
                                 FROM INC_CANDI_GANAD CG, INC_CONCU_PARAM_GENER CPG 
                                WHERE CG.CLIE_OID_CLIE = pnOidCliente
                                  AND CG.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL 
                                  AND CPG.IND_ACTI = 1);
    lnIndClieBloq := 1;                              
  END IF;
    
  --VALIDAMOS SI EL PREMIO CUMPLE CON LA EXIGENCIA DE PUNTOS EXIGIDOS
  FOR k IN 1..ltRegPremiosEscogidos.COUNT LOOP
 
    IF(((ltRegPremiosEscogidos(k).NUM_PUNT_PROD_EXIG IS NOT NULL) AND
         (pnSaldoPuntosExig >= ltRegPremiosEscogidos(k).NUM_PUNT_PROD_EXIG)) OR
       (ltRegPremiosEscogidos(k).NUM_PUNT_PROD_EXIG IS NULL))  THEN
      
      ltRegPremiosEscogidos(k).IND_ACTU_GANA := 1;
    END IF;   
  END LOOP;
  
  --AGRUPAMOS PREMIOS
  FOR k IN 1..ltRegPremiosEscogidos.COUNT LOOP
    IF(ltRegPremiosEscogidos(k).IND_ELIM = 0) THEN
      
      FOR kk IN (k+1)..ltRegPremiosEscogidos.COUNT LOOP
        IF((ltRegPremiosEscogidos(k).OID_PARA_NIVE_PREM = ltRegPremiosEscogidos(kk).OID_PARA_NIVE_PREM) AND 
           (ltRegPremiosEscogidos(k).NUM_PREM = ltRegPremiosEscogidos(kk).NUM_PREM)) THEN
           
           ltRegPremiosEscogidos(k).NUM_UNID := ltRegPremiosEscogidos(k).NUM_UNID +
                                                ltRegPremiosEscogidos(kk).NUM_UNID;
           ltRegPremiosEscogidos(k).NUM_CANT_INIC_PUNT := ltRegPremiosEscogidos(k).NUM_CANT_INIC_PUNT +
                                                ltRegPremiosEscogidos(kk).NUM_CANT_INIC_PUNT;  
           ltRegPremiosEscogidos(k).NUM_CANT_FINA_PUNT := ltRegPremiosEscogidos(k).NUM_CANT_FINA_PUNT +
                                                ltRegPremiosEscogidos(kk).NUM_CANT_FINA_PUNT;                                                                         
           
           ltRegPremiosEscogidos(kk).IND_ELIM := 1;
        END IF;   
      END LOOP;
    END IF;   
  END LOOP;
  
  IF(lnOidPeriodoDespacho IS NULL) THEN
    lnOidPeriodoGanadora := pnOidPeriodo;
  ELSE
    lnOidPeriodoGanadora := lnOidPeriodoDespacho;
  END IF;  
  
  --ACTUALIZAMOS LOS PREMIOS marcados como GANADORAS
  FOR k IN 1..ltRegPremiosEscogidos.COUNT LOOP

    IF((ltRegPremiosEscogidos(k).IND_ELIM = 0) AND (ltRegPremiosEscogidos(k).IND_ACTU_GANA = 1)) THEN
        
      IF((ltRegPremiosEscogidos(k).NUM_CANT_FIJA_PUNT IS NOT NULL) AND (ltRegPremiosEscogidos(k).NUM_CANT_FIJA_PUNT <> 0)) THEN
        IF(lnIndPremAcumNive = 1) THEN
          lnTotalPuntos := ltRegPremiosEscogidos(k).NUM_CANT_FIJA_PUNT;
        ELSE
          lnTotalPuntos := lnTotalPuntos + ltRegPremiosEscogidos(k).NUM_CANT_FIJA_PUNT;
        END IF;
        
      ELSE
        
        IF(lnIndPremAcumNive = 1) THEN
          lnTotalPuntos := ltRegPremiosEscogidos(k).NUM_CANT_INIC_PUNT;
        ELSE
          lnTotalPuntos := lnTotalPuntos + ltRegPremiosEscogidos(k).NUM_CANT_INIC_PUNT;
        END IF;
      END IF;
      
      ltRegPremiosEscogidos(k).OID_GANA := INC_GANA_SEQ.NEXTVAL;
      
      --Se registra el cliente como Ganadora
       INSERT INTO INC_GANAD
        (OID_GANA,
         FEC_OBTE,
         NUM_UNID,
         IND_LIST_GANA,
         IND_DESC,
         IND_DESP,
         CLIE_OID_CLIE,
         PERD_OID_PERI,
         PANP_OID_PARA_NIVE_PREM,
         SOCA_OID_SOLI_CABE,
         IND_CLIE_BLOQ)
       VALUES
        (ltRegPremiosEscogidos(k).OID_GANA,
         TRUNC(SYSDATE),
         ltRegPremiosEscogidos(k).NUM_UNID,
         0,
         0,
         0,
         pnOidCliente,
         lnOidPeriodoGanadora,
         ltRegPremiosEscogidos(k).OID_PARA_NIVE_PREM,
         pnOidSolicitud,
         lnIndClieBloq);

      --JVM         
      IF(lnAuxActual = -1) THEN
        lnAuxActual := lnTotalPuntos;
      ELSE
        lnAuxAnterior := lnAuxActual;
        lnAuxActual := lnTotalPuntos;
                                
        IF(lnAuxActual < lnAuxAnterior) THEN
          lnAuxTotalPuntos := lnAuxTotalPuntos + lnAuxAnterior;
        END IF;
      END IF;  
          
    END IF;
    
  END LOOP;
 
  lnAuxTotalPuntos := lnAuxTotalPuntos + lnAuxActual;
  
  IF(lnTotalPuntos > 0) THEN
    IF(lnIndNivelesRotatorios = 1) THEN
      lnTotalPuntos := lnAuxTotalPuntos + lnPuntosMeta;
    ELSE
      IF(lnIndDuplaCyzone = 1) THEN
        lnTotalPuntos := lnTotalPuntos + lnPuntosMeta;
      END IF;  
    END IF;
    
    IF(lnSaldoADescontar > 0) THEN
      lnTotalPuntos := lnSaldoADescontar;
    END IF;
    
    
    --DBMS_OUTPUT.put_line('totalPuntos: ' || lnTotalPuntos);
    --DBMS_OUTPUT.put_line('PuntosExigidos: ' || pnSaldoPuntosExig);
    
    SELECT INC_CUCP_SEQ.NEXTVAL INTO lnOidCuenta FROM DUAL;
    
    --Insertar cargo puntaje en  Cuenta Corriente
     INSERT INTO INC_CUENT_CORRI_PUNTO
      (OID_CUEN_CORR_PUNT,
       NUM_MOVI,
       NUM_PUNT,
       NUM_PUNT_EXIG,
       FEC_MOVI,
       COPA_OID_PARA_GRAL,
       CLIE_OID_CLIE,
       PERD_OID_PERI,
       TMOV_OID_TIPO_MOVI,
       FEC_ULTI_ACTU,
       VAL_DESC)
     VALUES
        (lnOidCuenta,
         lnOidCuenta,
         lnTotalPuntos*(-1),
         pnSaldoPuntosExig,
         TRUNC(SYSDATE),
         pnOidConcurso,
         pnOidCliente,
         pnOidPeriodo,
         2,
         SYSDATE,
         'Entrega de Premio');
         
    --ACTUALIZAMOS INC_CANDI_GANAD que se ENTREGO EL PREMIO                      
    UPDATE INC_CANDI_GANAD
       SET VAL_REQU_PREM_SUPE = 1
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND COPA_OID_PARA_GRAL = pnOidConcurso;
    
    --ACTUALIZAMOS LOS PREMIOS ELEGIDOS PENDIENTES como CERRADO
    UPDATE INC_PREMI_ELEGI
       SET ind_pend      = 4,
           COD_MOTI_INVA = 14,
           usu_modi      = psCodigoUsuario,
           fec_modi      = SYSDATE
     WHERE copa_oid_para_gral = pnOidConcurso
       AND clie_oid_clie = pnOidCliente
       AND ind_pend = 1;         
                         
  END IF;
       
  ptRegPremiosEscogidos := ltRegPremiosEscogidos;
  
END;

/**************************************************************************
Descripcion       : Recupera los niveles a entregar de acuerdo al puntaje
                    acumulado

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  pnOidConcurso     :  Oid Concurso
  ptRegNivel    :  Lista de Niveles del Concurso
  ptOidNivel    :  Vector de Niveles a Premiar
  pnInicio      :  Nivel a Entregar
  pnPrimerNivel :  Primer Nivel Concurso
  
Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_TRATA_NIVEL_OBTEN(pnOidConcurso               NUMBER,
                                   ptRegNivel                  tablaRegNivel,
                                   ptOidNivel                  IN OUT tablaOidNivel,
                                   pnInicio                    NUMBER,
                                   pnPrimerNivel               NUMBER)
IS
  lnIndPremiosAcumulativos        INC_PARAM_GENER_PREMI.IND_PREM_ACUM_NIVE%TYPE;
  lnHastaNivelPremiacion          INC_PARAM_GENER_PREMI.VAL_HAST_NIVE%TYPE;
BEGIN

  SELECT IND_PREM_ACUM_NIVE,
         VAL_HAST_NIVE
    INTO lnIndPremiosAcumulativos,
         lnHastaNivelPremiacion
    FROM INC_PARAM_GENER_PREMI
   WHERE COPA_OID_PARA_GRAL = pnOidConcurso;        
  
  IF(lnIndPremiosAcumulativos = 1) THEN
    IF(lnHastaNivelPremiacion > 0) THEN

      IF(ptRegNivel(pnInicio).NUM_NIVE > lnHastaNivelPremiacion) THEN
        ptOidNivel.EXTEND(1);
        ptOidNivel(ptOidNivel.LAST):= ptRegNivel(pnInicio).OID_PARA_NIVE_PREM;
        
      ELSE

        FOR x IN pnPrimerNivel..pnInicio LOOP
          ptOidNivel.EXTEND(1);
          ptOidNivel(ptOidNivel.LAST):= ptRegNivel(x).OID_PARA_NIVE_PREM; 
        END LOOP;
        
      END IF;
    
    ELSE
      FOR x IN pnPrimerNivel..pnInicio LOOP
        ptOidNivel.EXTEND(1);
        ptOidNivel(ptOidNivel.LAST):= ptRegNivel(x).OID_PARA_NIVE_PREM; 
      END LOOP; 
    END IF;
  ELSE
    ptOidNivel.EXTEND(1);
    ptOidNivel(ptOidNivel.LAST):= ptRegNivel(pnInicio).OID_PARA_NIVE_PREM;
  
  END IF;

END INC_PR_TRATA_NIVEL_OBTEN;

/**************************************************************************
Descripcion       : Este proceso consiste transformar los puntos a Premios

Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais    : Codigo de Pais
  pnOidSolicitud   :  Oid Solicitud
  pnOidConcurso     :  Oid Concurso
  pnOidCliente     :  Oid Cliente
  pnOidPeriodo     :  OidPeriodo
  pnSaldoPuntos  :  Saldo Puntos
  pnSaldoPuntosExig  :  Saldo Puntos Exigidos
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_DESPA_PREMI(psCodigoPais               VARCHAR2,
                             pnOidConcurso              NUMBER,
                             pnOidCliente               NUMBER,
                             pnOidPeriodo               NUMBER,
                             pnIndClieBloq              NUMBER,
                             psNumeroConcurso           VARCHAR2,
                             psCodigoRegion             VARCHAR2,
                             psCodigoUsuario            VARCHAR2,
                             psTipoCierre               VARCHAR2,
                             ptRegPremiosGanados        tablaRegPremio)
IS
  lsCodigoPeriodo     SEG_PERIO_CORPO.COD_PERI%TYPE;     
  lnOcurrencias       NUMBER;  
  lbPremioPendiente   BOOLEAN;
  lbReemplazo         BOOLEAN;
  lnUltimaPosicion    NUMBER;
  lsIndReemPendiente  INC_PREMI_BLOQU.IND_REEM%TYPE;
  lnPosicionCompuesta NUMBER;
  lnNumUnidadesPosi   NUMBER;
 
  ltRegSolicitud      INC_PKG_PROCE_INCEN.tRegSolicitud;
  ltRegPosicion       INC_PKG_PROCE_INCEN.tablaRegPosicion := INC_PKG_PROCE_INCEN.tablaRegPosicion();
BEGIN
  
  --SI EL CLIENTE ESTA BLOQUEADO, NO SE DESPACHA PREMIOS
  IF(pnIndClieBloq = 1) THEN
    RETURN;
  END IF;
  
  lsCodigoPeriodo := INC_PKG_PROCE_INCEN.INC_FN_OBTIE_CODIG_PERIO(pnOidPeriodo);

  FOR k IN 1..ptRegPremiosGanados.COUNT LOOP
    IF((ptRegPremiosGanados(k).IND_ELIM = 0) AND (ptRegPremiosGanados(k).IND_ATEN_PREM = 1)) THEN
      
      --CREAMOS LOS DATOS DE LA SOLICITUD DE PEDIDO Y SU DETALLE
      ltRegSolicitud.COD_PAIS      := psCodigoPais;
      ltRegSolicitud.COD_MARC      := 'T';
      ltRegSolicitud.COD_CANA      := 'VD';
      ltRegSolicitud.OID_CLIE      := pnOidCliente;
      ltRegSolicitud.COD_PERI      := lsCodigoPeriodo;
      ltRegSolicitud.FEC_PROG_FACT := NULL; --Se calculara en la Creacion de la Solicitud
      ltRegSolicitud.COD_OPER      := 'INC020';
      ltRegSolicitud.COD_CLAS_SOLI := 'I1';
      ltRegSolicitud.COD_TIPO_SOLI := NULL;
      ltRegSolicitud.COD_TIPO_CLIE := '02'; --Consultoras
      ltRegSolicitud.OID_PARA_GRAL := pnOidConcurso;
      ltRegSolicitud.NUM_PREM      := ptRegPremiosGanados(k).NUM_PREM;
      ltRegSolicitud.COD_USUA      := psCodigoUsuario;

      ltRegSolicitud.OID_ACCE_FISI := 1;
      ltRegSolicitud.OID_GRUP_PROC := 3; --(GP3)
      ltRegSolicitud.OID_GRUP_PROC_SECU := 3; --(GP3)
      ltRegSolicitud.NUM_CLIEN     := 1;
      ltRegSolicitud.OID_MODU      := 13; --Incentivos
      
      IF(psTipoCierre = TIPO_CIERRE_ZONA) THEN
        ltRegSolicitud.VAL_GLOS_OBSE := 'PREMIACION CIERRE DE ZONA';
      ELSE
        ltRegSolicitud.VAL_GLOS_OBSE := 'TRANSFERENCIA GRATUITA';
      END IF;
                   
      ltRegSolicitud.VAL_BASE_FLET_LOCA := 0;
      ltRegSolicitud.VAL_TOTA_PAGA_LOCA := 0;
      ltRegSolicitud.VAL_PREC_CATA_TOTA_LOCA := 0;
      ltRegSolicitud.VAL_BASE_FLET_DOCU := 0;
      ltRegSolicitud.VAL_PREC_CATA_TOTA_LOC_UNI_DEM := 0;
      --ltRegSolicitud.VAL_UNID_DEMA_REAL_TOTA := interfazRecordN(x).numUnidFaltantes;
      --ltRegSolicitud.NUM_UNID_POR_ATEN_TOTA := interfazRecordN(x).numUnidFaltantes;
      ltRegSolicitud.VAL_PREC_CONT_TOTA_LOCA := 0;
      ltRegPosicion.DELETE;
 
      FOR kk IN 1..ptRegPremiosGanados(k).articulos.COUNT LOOP
        
        --CREAMOS LOS DATOS DEL DETALLE DE LA SOLICITUD DE PEDIDO
        ltRegPosicion.EXTEND(1);
        lnUltimaPosicion := ltRegPosicion.LAST;
        ltRegPosicion(ltRegPosicion.LAST).OID_PROD := ptRegPremiosGanados(k).articulos(kk).PROD_OID_PROD;
        ltRegPosicion(ltRegPosicion.LAST).NUM_UNID := ptRegPremiosGanados(k).articulos(kk).NUM_UNID *
                                                            ptRegPremiosGanados(k).NUM_UNID;
        ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT := NULL;
        ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT_FICT := ptRegPremiosGanados(k).articulos(kk).COD_VENT_FICT;
        ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR := ptRegPremiosGanados(k).articulos(kk).NUM_UNID *
                                                            ptRegPremiosGanados(k).NUM_UNID;
        ltRegPosicion(ltRegPosicion.LAST).OID_DETA_OFER := NULL;
        ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_LOCA := 0;
        ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CONT_UNIT_LOCA := ptRegPremiosGanados(k).articulos(kk).IMP_PREC_PUBL;
        ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_DOCU := 0;
        
        --VALIDAMOS SI EL PREMIO A ENTREGAR QUEDA COMO PENDIENTE
        BEGIN
          SELECT blo.IND_REEM
            INTO lsIndReemPendiente
            FROM INC_PREMI_BLOQU blo, MAE_PRODU pro
           WHERE pro.OID_PROD = ptRegPremiosGanados(k).articulos(kk).PROD_OID_PROD
             AND blo.COD_REGI = psCodigoRegion
             AND blo.COD_CONC = psNumeroConcurso
             AND blo.CAM_BLOQ = lsCodigoPeriodo
             AND blo.COD_SAP = pro.COD_SAP
             AND blo.COD_VENT_FICT = ptRegPremiosGanados(k).articulos(kk).COD_VENT_FICT;
        EXCEPTION
          WHEN OTHERS THEN
            lsIndReemPendiente := NULL;
        END;         
           
        IF(lsIndReemPendiente IS NOT NULL) THEN
          lbPremioPendiente := TRUE;   
        ELSE
          lbPremioPendiente := FALSE; 
        END IF;
        
        lbReemplazo := FALSE;
        --VERIFICAMOS SI EL PRODUCTO TIENE REEMPLAZO
        FOR y IN ((SELECT reemL.OID_REEM_ARTI_LOTE,
                         reemL.CTRE_OID_CRIT_REEM,
                         reemL.VAL_CRIT_REEM,
                         reemL.PROD_OID_PROD,
                         reemL.COD_VENT_FICT,
                         reemL.NUM_UNID,
                         reemL.IMP_PREC_PUBL,
                         reemL.IND_COMU,
                         prod.COD_SAP COD_SAP_REEM,
                         i18n.VAL_I18N DESC_PROD_REEM,
                         prod2.COD_SAP COD_SAP_ORIG,
                         con.NUM_CONC,
                         desp.MENS_OID_MENS_AUTO,
                         reeml.num_orde,
                         nvl(reemL.COD_TIPO_AGRU, 'I') COD_TIPO_AGRU
                    FROM INC_REEMP_ARTIC_LOTE  reemL,
                         INC_REEMP_REGIO_ZONA  reemRZ,
                         MAE_PRODU             prod,
                         v_gen_i18n_sicc       i18n,
                         MAE_PRODU             prod2,
                         INC_ARTIC_LOTE        artLote,
                         INC_CONCU_PARAM_GENER con,
                         INC_DESPA_PREMI       desp
                   WHERE reemL.ARLO_OID_ARTI_LOTE = ptRegPremiosGanados(k).articulos(kk).OID_ARTI_LOTE
                     AND reemL.IND_ACTI = 1
                     AND reemL.OID_REEM_ARTI_LOTE = reemRZ.RARL_OID_REEM_ARTI_LOTE
                     AND (SELECT COUNT(1)
                            FROM MAE_CLIEN_UNIDA_ADMIN unidAdm,
                                 ZON_TERRI_ADMIN       terrAdm,
                                 ZON_SECCI             secc,
                                 ZON_ZONA              zon,
                                 ZON_REGIO             reg
                           WHERE unidAdm.CLIE_OID_CLIE = pnOidCliente
                             AND unidAdm.IND_ACTI = 1
                             AND unidAdm.ZTAD_OID_TERR_ADMI = terrAdm.OID_TERR_ADMI
                             AND terrAdm.ZSCC_OID_SECC = secc.OID_SECC
                             AND secc.ZZON_OID_ZONA = zon.OID_ZONA
                             AND zon.ZORG_OID_REGI = reg.OID_REGI
                             AND zon.zorg_oid_regi =
                                 NVL(reemrz.zorg_oid_regi, zon.zorg_oid_regi)
                             AND zon.oid_zona =
                                 NVL(reemrz.zzon_oid_zona, zon.oid_zona)) >= 1
                     AND reemL.PROD_OID_PROD = prod.OID_PROD
                     AND i18n.ATTR_ENTI = 'MAE_PRODU'
                     AND i18n.ATTR_NUM_ATRI = 1
                     AND i18n.IDIO_OID_IDIO = 1
                     AND i18n.VAL_OID = prod.OID_PROD
                     AND reemL.ARLO_OID_ARTI_LOTE = artLote.OID_ARTI_LOTE
                     AND artLote.PROD_OID_PROD = prod2.OID_PROD
                     AND con.OID_PARA_GRAL = pnOidConcurso
                     AND desp.COPA_OID_PARA_GRAL = con.OID_PARA_GRAL
                     AND reemL.COMP_OID_REEM_ARTI_LOTE IS NULL
                  UNION
                  SELECT reemL.OID_REEM_ARTI_LOTE,
                         reemL.CTRE_OID_CRIT_REEM,
                         reemL.VAL_CRIT_REEM,
                         reemL.PROD_OID_PROD,
                         reemL.COD_VENT_FICT,
                         reemL.NUM_UNID,
                         reemL.IMP_PREC_PUBL,
                         reemL.IND_COMU,
                         prod.COD_SAP COD_SAP_REEM,
                         i18n.VAL_I18N DESC_PROD_REEM,
                         prod2.COD_SAP COD_SAP_ORIG,
                         con.NUM_CONC,
                         desp.MENS_OID_MENS_AUTO,
                         reeml.num_orde,
                         nvl(reemL.COD_TIPO_AGRU, 'I') COD_TIPO_AGRU
                    FROM INC_REEMP_ARTIC_LOTE  reemL,
                         MAE_PRODU             prod,
                         v_gen_i18n_sicc       i18n,
                         MAE_PRODU             prod2,
                         INC_ARTIC_LOTE        artLote,
                         INC_CONCU_PARAM_GENER con,
                         INC_DESPA_PREMI       desp
                   WHERE reemL.ARLO_OID_ARTI_LOTE = ptRegPremiosGanados(k).articulos(kk).OID_ARTI_LOTE
                     AND reemL.IND_ACTI = 1
                     AND NOT EXISTS
                   (SELECT 1
                            FROM inc_reemp_regio_zona reemrz
                           where reemrz.RARL_OID_REEM_ARTI_LOTE =
                                 reeml.OID_REEM_ARTI_LOTE)
                     AND reemL.PROD_OID_PROD = prod.OID_PROD
                     AND i18n.ATTR_ENTI = 'MAE_PRODU'
                     AND i18n.ATTR_NUM_ATRI = 1
                     AND i18n.IDIO_OID_IDIO = 1
                     AND i18n.VAL_OID = prod.OID_PROD
                     AND reemL.ARLO_OID_ARTI_LOTE = artLote.OID_ARTI_LOTE
                     AND artLote.PROD_OID_PROD = prod2.OID_PROD
                     AND con.OID_PARA_GRAL = pnOidConcurso
                     AND reemL.COMP_OID_REEM_ARTI_LOTE IS NULL
                     AND desp.COPA_OID_PARA_GRAL = con.OID_PARA_GRAL) ORDER BY NUM_ORDE ) LOOP

          IF((NOT lbReemplazo) AND (y.CTRE_OID_CRIT_REEM = 3)) THEN
            lbReemplazo := TRUE;
          
            IF((y.IND_COMU = 1) AND (y.MENS_OID_MENS_AUTO IS NOT NULL)) THEN
              UPDATE MSG_BUZON_MENSA 
                 SET DATO_VARI_07 = 'Premio Reemplazo', 
                     DATO_VARI_11 = y.DESC_PROD_REEM, 
                     DATO_VARI_18 = y.COD_SAP_REEM, 
                     DATO_VARI_19 = y.COD_VENT_FICT
               WHERE CLIE_OID_CLIE = pnOidCliente
                 AND MENS_OID_MENS = y.MENS_OID_MENS_AUTO
                 AND DATO_VARI_18 = y.COD_SAP_ORIG
                 AND DATO_VARI_17 = psNumeroConcurso; 
            END IF;
            
            lnNumUnidadesPosi := ltRegPosicion(ltRegPosicion.LAST).NUM_UNID;
            ltRegPosicion(ltRegPosicion.LAST).OID_PROD := y.PROD_OID_PROD;
            ltRegPosicion(ltRegPosicion.LAST).NUM_UNID := y.NUM_UNID * ltRegPosicion(ltRegPosicion.LAST).NUM_UNID;
            ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR := y.NUM_UNID * ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR;
            ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT_FICT := y.COD_VENT_FICT;
            ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CONT_UNIT_LOCA := y.IMP_PREC_PUBL;
            
            lnPosicionCompuesta := ltRegPosicion.LAST;
            
            IF(lsIndReemPendiente = '1') THEN
              lbPremioPendiente := TRUE;   
            ELSE
              lbPremioPendiente := FALSE; 
            END IF;
        
            --Verificamos si se trata de un Reemplazo de tipo Compuesta
            IF(y.COD_TIPO_AGRU = 'C') THEN
              FOR z IN (SELECT PROD_OID_PROD,         
                               COD_VENT_FICT,         
                               NUM_UNID,         
                               IMP_PREC_PUBL  
                          FROM INC_REEMP_ARTIC_LOTE  
                         WHERE COMP_OID_REEM_ARTI_LOTE = y.OID_REEM_ARTI_LOTE    
                           AND IND_ACTI = 1 ) LOOP
                           
                --AGREGAMOS LOS DETALLES DE LOS PRODUCTOS RELACIONADOS AL REEMPLAZO COMPUESTA
                ltRegPosicion.EXTEND(1);
                ltRegPosicion(ltRegPosicion.LAST).OID_PROD := z.PROD_OID_PROD;
                ltRegPosicion(ltRegPosicion.LAST).NUM_UNID := z.NUM_UNID * lnNumUnidadesPosi;
                ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT := NULL;
                ltRegPosicion(ltRegPosicion.LAST).VAL_CODI_VENT_FICT := z.COD_VENT_FICT;
                ltRegPosicion(ltRegPosicion.LAST).NUM_UNID_COMPR := z.NUM_UNID * lnNumUnidadesPosi;
                ltRegPosicion(ltRegPosicion.LAST).OID_DETA_OFER := NULL;
                ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_LOCA := 0;
                ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CONT_UNIT_LOCA := z.IMP_PREC_PUBL;
                ltRegPosicion(ltRegPosicion.LAST).VAL_PREC_CATA_UNIT_DOCU := 0;
              END LOOP;
              
            END IF;
   
          END IF;
        
        END LOOP;
    
        --VALIDAMOS SI SE ENTREGA EL PREMIO O SE MARCA COMO PENDIENTE
        IF(lbPremioPendiente) THEN
        
          INSERT INTO INC_PREMI_PENDI_SOLIC ( 
            COR_PREM_PEND, 
            COD_PAIS, 
            CAM_BLOQ, 
            COD_CONC, 
            COD_CLIE,
            COD_REGI, 
            COD_SAP, 
            COD_VENT_FICT, 
            PROD_OID_PROD, 
            COPA_OID_PARA_GRAL,  
            PANP_OID_PARA_NIVE_PREM, 
            LOPA_OID_LOTE_PREM_ARTI, 
            ICTP_OID_CONC_TIPO_PROG, 
            NUM_UNID_DEMA, 
            USU_CREA, 
            FEC_CREA,
            USU_MODI,
            FEC_MODI) 
          VALUES 
            (INC_PPEN_SEQ.nextval,
             psCodigoPais,
             lsCodigoPeriodo,
             psNumeroConcurso,
             (SELECT COD_CLIE FROM MAE_CLIEN WHERE OID_CLIE = pnOidCliente),
             psCodigoRegion,
             (SELECT COD_SAP FROM MAE_PRODU WHERE OID_PROD = ltRegPosicion(lnUltimaPosicion).OID_PROD),
             ltRegPosicion(lnUltimaPosicion).VAL_CODI_VENT_FICT,
             ltRegPosicion(lnUltimaPosicion).OID_PROD,
             pnOidConcurso,
             ptRegPremiosGanados(k).OID_PARA_NIVE_PREM,
             ptRegPremiosGanados(k).OID_PREM,
             (SELECT ICTP_OID_CONC_TIPO_PROG FROM INC_CONCU_PARAM_GENER WHERE OID_PARA_GRAL = pnOidConcurso),
             ltRegPosicion(lnUltimaPosicion).NUM_UNID,
             psCodigoUsuario,
             SYSDATE,
             psCodigoUsuario,
             SYSDATE);
             
          ltRegPosicion.DELETE(lnUltimaPosicion, ltRegPosicion.LAST);
             
        END IF;
        
      END LOOP;
     
      --SI NO HAY POSICIONES, PORQUE FUERON BLOQUEADOS, NO SE REALIZA LA GENERACION 
      --DE LA SOLICITUD DE PREMIOS
      IF(ltRegPosicion.COUNT > 0) THEN
        --CREAMOS LA SOLICITUD DE PEDIDO  
        --DBMS_OUTPUT.put_line('GENERACION SOLICITUD');   
        INC_PKG_PROCE_INCEN.INC_PR_GENER_SOLIC_PEDID(ltRegSolicitud, 'Z', ltRegPosicion);
    
        --ACTUALIZAMOS EN LA TABLA DE GANADORAS, que sea ha DESPACHADO y la Solicitud GENERADA
        UPDATE INC_GANAD
           SET IND_DESP = 1,
               SOCA_OID_SOLI_CABE = ltRegSolicitud.OID_SOLI_CABE_GENE
         WHERE OID_GANA = ptRegPremiosGanados(k).OID_GANA;

      END IF;  
      
    END IF;                                   

  END LOOP;  
  
END INC_PR_DESPA_PREMI;

/**************************************************************************
Descripcion       : Realiza la premiacion de las consultoras que han pasado
                    pedido y se encuentran en gp4.
Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion : Fecha de Facturacion
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_APLIC_PREMI_FACTU(psCodigoPais               VARCHAR2,
                                   psCodigoMarca              VARCHAR2,
                                   psCodigoCanal              VARCHAR2,
                                   psCodigoPeriodo            VARCHAR2,
                                   psFechaFacturacion         VARCHAR2,
                                   psCodigoUsuario            VARCHAR2)
IS
  CURSOR c_pedidos(pnOidPeriodo cra_perio.oid_peri%TYPE) IS
    SELECT cab.CLIE_OID_CLIE,
           cab.OID_SOLI_CABE
      FROM PED_SOLIC_CABEC cab,
           PED_TIPO_SOLIC_PAIS tsp,
           PED_TIPO_SOLIC sol
     WHERE cab.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
       AND tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
       AND sol.COD_TIPO_SOLI = 'SOC'
       AND cab.GRPR_OID_GRUP_PROC = 4
       AND PERD_OID_PERI = pnOidPeriodo;

  --se define un tipo de dato tipo Tabla de Registros de los pedidos
  TYPE RegTab IS TABLE OF c_pedidos%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  pedidoReg RegTab;

  lnOidMarca     SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal     SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo   CRA_PERIO.OID_PERI%TYPE;
BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);
  OPEN c_pedidos(lnOidPeriodo);
      LOOP
      FETCH c_pedidos BULK COLLECT INTO pedidoReg LIMIT W_FILAS;

        IF pedidoReg.COUNT > 0 THEN
          FOR x IN pedidoReg.FIRST .. pedidoReg.LAST LOOP
              --Proceso de Premiacion de las Ordenes de Compra
						  INC_PR_APLIC_REQUI_PREMI(psCodigoPais,
                                       pedidoReg(x).CLIE_OID_CLIE,
                                       pedidoReg(x).OID_SOLI_CABE,
                                       NULL,
                                       psCodigoPeriodo,
                                       psFechaFacturacion,
                                       psCodigoUsuario);
          END LOOP;
        END IF;

      EXIT WHEN c_pedidos%NOTFOUND;
      END LOOP;
    CLOSE c_pedidos;
/*
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
     RAISE_APPLICATION_ERROR(-20123,'ERROR INC_PR_APLIC_PREMI_FACTU: (' ||ln_sqlcode || ')' || ls_sqlerrm);
*/     
END INC_PR_APLIC_PREMI_FACTU;

/**************************************************************************
Descripcion       : Realiza la premiacion de las consultoras que no han pasado
                    pedido y al cierre de zona seran premiadas si cumple las 
                    condiciones de premiacion de los concursos
Fecha Creacion    : 20/02/2015
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion : Fecha de Facturacion
  psCodigoZona     :  Codigo de zona
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_APLIC_PREMI_CZONA(psCodigoPais          VARCHAR2,
                                   psCodigoMarca         VARCHAR2,
                                   psCodigoCanal         VARCHAR2,
                                   psCodigoPeriodo       VARCHAR2,
                                   psFechaFacturacion    VARCHAR2,
                                   psCodigoZona          VARCHAR2,
                                   psCodigoUsuario       VARCHAR2) IS
  lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca      SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;
  lnOidZona       ZON_ZONA.OID_ZONA%TYPE;

  CURSOR c_Pedidos(oidPais NUMBER, oidZona NUMBER, oidPeriodo NUMBER) IS
    SELECT C.CLIE_OID_CLIE
  FROM MAE_CLIEN_UNIDA_ADMIN C,
       CRA_PERIO CP1,
       (SELECT FEC_INIC, FEC_FINA FROM CRA_PERIO WHERE OID_PERI = oidPeriodo) PER_REF,
       ZON_TERRI_ADMIN TA,
       ZON_SECCI SEXY,
       ZON_ZONA ZON
 WHERE C.PERD_OID_PERI_INI = CP1.OID_PERI
   AND C.IND_ACTI = 1
   AND C.CLIE_OID_CLIE IN
       (SELECT CLIE_OID_CLIE
          FROM INC_CANDI_GANAD GAN,
               INC_CONCU_PARAM_GENER C,
               INC_PARAM_GENER_PREMI PRE,
               INC_REQUI_PREMI REQ,
               (SELECT FEC_INIC, FEC_FINA
                  FROM CRA_PERIO
                 WHERE OID_PERI = oidPeriodo) PER_REF
         WHERE GAN.COPA_OID_PARA_GRAL = C.OID_PARA_GRAL
           AND PRE.COPA_OID_PARA_GRAL = C.OID_PARA_GRAL
           AND (((oidPeriodo BETWEEN PRE.PERD_OID_PERI_INIC AND PRE.PERD_OID_PERI) AND
               PRE.PERD_OID_PERI_INIC IS NOT NULL) OR
               (PRE.PERD_OID_PERI IS NULL) OR ((PRE.PERD_OID_PERI_INIC IS NULL) AND
               PRE.PERD_OID_PERI = oidPeriodo))
           AND REQ.COPA_OID_PARA_GRAL(+) = C.OID_PARA_GRAL
           AND PRE.TPRM_OID_TIPO_PION <> 1
           AND GAN.VAL_REQU_PREM_SUPE <> 1
           AND GAN.BINC_OID_BASE_INCU IS NULL
           AND CLIE_OID_CLIE NOT IN
               (SELECT DSC.CLIE_OID_CLIE
                  FROM INC_DESCA DSC
                 WHERE DSC.COPA_OID_PARA_GRAL = C.OID_PARA_GRAL)
           AND C.PAIS_OID_PAIS = oidPais
           AND C.DIRI_OID_DIRI = 1
           AND C.IND_ACTI = 1
         GROUP BY CLIE_OID_CLIE)
   AND ZON.OID_ZONA = oidZona
   AND SEXY.ZZON_OID_ZONA = ZON.OID_ZONA
   AND TA.ZSCC_OID_SECC = SEXY.OID_SECC
   AND TA.OID_TERR_ADMI = C.ZTAD_OID_TERR_ADMI
   AND PER_REF.FEC_INIC >= CP1.FEC_INIC
   AND ((C.PERD_OID_PERI_FIN IS NULL) OR
       (SELECT FEC_FINA FROM CRA_PERIO WHERE OID_PERI = C.PERD_OID_PERI_FIN) <=
       PER_REF.FEC_FINA);

  TYPE interfazPedidos IS RECORD(
    oidCliente         MAE_CLIEN.OID_CLIE%TYPE
  );

  TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
  interfazRecordN interfazPedidosTab;

BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);

  --Recorremos las zonas seleccionadas
  FOR y IN (SELECT OID_ZONA
              FROM ZON_ZONA
             WHERE INSTR(psCodigoZona, COD_ZONA) > 0
               AND IND_ACTI = 1
               AND IND_BORR = 0) LOOP

    lnOidZona := y.OID_ZONA;

    --PROCESAMOS LAS CONSULTORAS
    OPEN c_Pedidos(lnOidPais, lnOidZona, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN

        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

          --Proceso de Premiacion de las Ordenes de Compra
          INC_PR_APLIC_REQUI_PREMI(psCodigoPais,
                                   interfazRecordN(x).oidCliente,
                                   NULL,
                                   TIPO_CIERRE_ZONA,
                                   psCodigoPeriodo,
                                   psFechaFacturacion,
                                   psCodigoUsuario);

        END LOOP;

      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;

  END LOOP;
/*
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_APLIC_PREMI_CZONA: (' || ln_sqlcode || ')' || ls_sqlerrm);
*/
END INC_PR_APLIC_PREMI_CZONA;

end INC_PKG_PROCE_PREMI;
/
