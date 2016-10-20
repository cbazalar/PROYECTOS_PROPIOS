CREATE OR REPLACE PACKAGE "INC_PKG_REPOR_INCEN" IS

   /* Declaracion de Tipos */
   TYPE TIPOCURSOR IS  REF CURSOR;

   /* Declaracion de Variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(150);
   W_FILAS      NUMBER:=10000;

   /* Declaracion de constantes */
   CAMPO_FILTRO_CONCURSO  VARCHAR2(1) := 'C';
   CAMPO_FILTRO_PROGRAMA  VARCHAR2(1) := 'P';
   CAMPO_FILTRO_CODIGO_SAP  VARCHAR2(1) := 'S';

   TYPE tIncentivos IS RECORD (
     PERI_DESD          SEG_PERIO_CORPO.COD_PERI%TYPE,
     PERI_HAST          SEG_PERIO_CORPO.COD_PERI%TYPE,
     PERD_OID_PERI_DESD CRA_PERIO.OID_PERI%TYPE,
     PERD_OID_PERI_HAST CRA_PERIO.OID_PERI%TYPE,
     VAL_NOMB           INC_CONCU_PARAM_GENER.VAL_NOMB%TYPE,
     IND_PREM_ACUM_NIVE INC_PARAM_GENER_PREMI.IND_PREM_ACUM_NIVE%TYPE,
     VAL_HAST_NIVE      INC_PARAM_GENER_PREMI.VAL_HAST_NIVE%TYPE,
     NUM_NIVE           INC_PARAM_GENER_PREMI.NUM_NIVE%TYPE,
     PUNT_NIVE          INC_PARAM_NIVEL_PREMI.NUM_CANT_FIJA_PUNT%TYPE,
     VAL_NIVE_SELE      INC_PARAM_NIVEL_PREMI.VAL_NIVE_SELE%TYPE,
     OID_PARA_NIVE_PREM INC_PARAM_NIVEL_PREMI.OID_PARA_NIVE_PREM%TYPE,
     NUM_CONC           INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
     OID_PARA_GRAL      INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
     IND_NIVE_ROTA      INC_PARAM_GENER_PREMI.IND_NIVE_ROTA%TYPE,
     NUM_CANT_INIC_PUNT INC_REPOR_PROY_CONC.NUM_CANT_INIC_PUNT%TYPE,
     NUM_CANT_FINA_PUNT INC_REPOR_PROY_CONC.NUM_CANT_FINA_PUNT%TYPE,
     NUM_ROTA           INC_PARAM_GENER_PREMI.NUM_ROTA%TYPE
   );
   TYPE tablaIncentivos IS TABLE OF tIncentivos;

   TYPE tClientes IS RECORD (
      CLIE_OID_CLIE       number(12),
    COPA_OID_PARA_GRAL  number(12),
    COD_SUB_GEREN_VENT  varchar2(2),
    DES_SUB_GEREN_VENT  varchar2(40),
    COD_REGI            varchar2(4),
    DES_REGI            varchar2(40),
    COD_ZONA            varchar2(4),
    DES_ZONA            varchar2(40),
    PUNT_CLIE           number(9),
    VAL_PUNT_CCPU            INC_REPOR_PROY_CLIEN.VAL_PUNT_CCPU%TYPE
   );
   TYPE tablaClientes IS TABLE OF tClientes;

   TYPE tReporteProyeccion IS RECORD (
     PERI_DESD          SEG_PERIO_CORPO.COD_PERI%TYPE,
     PERI_HAST          SEG_PERIO_CORPO.COD_PERI%TYPE,
     PERD_OID_PERI_DESD CRA_PERIO.OID_PERI%TYPE,
     PERD_OID_PERI_HAST CRA_PERIO.OID_PERI%TYPE,
     IND_PREM_ACUM_NIVE INC_PARAM_GENER_PREMI.IND_PREM_ACUM_NIVE%TYPE,
     VAL_HAST_NIVE      INC_PARAM_GENER_PREMI.VAL_HAST_NIVE%TYPE,
     NUM_NIVE           INC_PARAM_GENER_PREMI.NUM_NIVE%TYPE,
     PUNT_NIVE          INC_PARAM_NIVEL_PREMI.NUM_CANT_FIJA_PUNT%TYPE,
     VAL_NIVE_SELE      INC_PARAM_NIVEL_PREMI.VAL_NIVE_SELE%TYPE,
     OID_PARA_GRAL      INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
     COD_SAP            MAE_PRODU.COD_SAP%TYPE,
     DES_PROD           MAE_PRODU.DES_CORT%TYPE,
     NUM_CONC           INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
     NUM_PREM           INC_LOTE_PREMI_ARTIC.NUM_PREM%TYPE,
     NUM_UNID           INC_ARTIC_LOTE.NUM_UNID%TYPE,
     COD_SUB_GEREN_VENT INC_REPOR_PROY_CLIEN.COD_SUB_GEREN_VENT%TYPE,
     DES_SUB_GEREN_VENT INC_REPOR_PROY_CLIEN.DES_SUB_GEREN_VENT%TYPE,
     COD_REGI           INC_REPOR_PROY_CLIEN.COD_REGI%TYPE,
     DES_REGI           INC_REPOR_PROY_CLIEN.DES_REGI%TYPE,
     COD_ZONA           INC_REPOR_PROY_CLIEN.COD_ZONA%TYPE,
     DES_ZONA           INC_REPOR_PROY_CLIEN.DES_ZONA%TYPE,
     PUNT_CLIE          INC_REPOR_PROY_CLIEN.PUNT_CLIE%TYPE,
     IND_NIVE_ROTA      INC_PARAM_GENER_PREMI.IND_NIVE_ROTA%TYPE,
     NUM_ROTA           INC_PARAM_GENER_PREMI.NUM_ROTA%TYPE
   );
   TYPE tablaReporteProyeccion IS TABLE OF tReporteProyeccion;

    TYPE tClien IS RECORD (
     CLIE_OID_CLIE              MAE_CLIEN.OID_CLIE%TYPE,
     OID_CLIE_DATO_ADIC          MAE_CLIEN_DATOS_ADICI.OID_CLIE_DATO_ADIC%TYPE,
     FEC_NACI                   MAE_CLIEN_DATOS_ADICI.FEC_NACI%TYPE,
     IND_CORR                    MAE_CLIEN_DATOS_ADICI.IND_CORR%TYPE,
     IND_ACTI                    MAE_CLIEN_DATOS_ADICI.IND_ACTI%TYPE,
     ESTA_OID_ESTA_CLIE         MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE,
     FEC_ULTI_ACTU              MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU%TYPE,
     OID_CLIE_UNID_ADMI         MAE_CLIEN_UNIDA_ADMIN.OID_CLIE_UNID_ADMI%TYPE,
     ZTAD_OID_TERR_ADMI         MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI%TYPE,
     Oid_Terr_Admi              ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE,
     OID_SECC                   ZON_SECCI.OID_SECC%TYPE,
     OID_ZONA                   ZON_ZONA.OID_ZONA%TYPE,
     ZORG_OID_REGI              ZON_ZONA.ZORG_OID_REGI%TYPE
   );
   TYPE tablaClien IS TABLE OF tClien;



  TYPE tCabecera IS RECORD (
    OID_SOLI_CABE              PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
    TSPA_OID_TIPO_SOLI_PAIS    PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE,
    PERD_OID_PERI              CRA_PERIO.OID_PERI%TYPE,
    SOCA_OID_SOLI_CABE        PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE%TYPE,
    CLIE_OID_CLIE              MAE_CLIEN.OID_CLIE%TYPE,
    PAIS_OID_PAIS              SEG_PAIS.OID_PAIS%TYPE,
    ESSO_OID_ESTA_SOLI        PED_ESTAD_SOLIC.OID_ESTA_SOLI%TYPE
    );
  TYPE tablaPedidosCabecera IS TABLE OF tCabecera;


  TYPE tDetalle IS RECORD (
    OID_SOLI_POSI                    PED_SOLIC_POSIC.OID_SOLI_POSI%TYPE,
    COD_POSI                        PED_SOLIC_POSIC.Cod_Posi%TYPE,
    SOCA_OID_SOLI_CABE              PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE%TYPE,
    tpos_oid_tipo_posi              ped_solic_posic.tpos_oid_tipo_posi%type,
    VAL_PREC_CATA_UNIT_LOCA         PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA%TYPE,
    NUM_UNID_DEMA_REAL              PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL%TYPE,
    NUM_UNID_ATEN                    PED_SOLIC_POSIC.Num_Unid_Aten%TYPE
    );
  TYPE tablaPedidosDetalle IS TABLE OF tDetalle;


  

  /***************************************************************************
  Descripcion       : Genera data para Reporte de Proyeccion de Premios
                      de concursos
  Fecha Creacion    : 15/02/2007
  Autor             : Marco Antonio Agurto Jimenez
  Autor Modificación: Carlos Diaz Valverde
  Fecha Modificación: 29/11/2010
  ***************************************************************************/

  PROCEDURE INC_PR_GENER_PROYE_PREMI_CONCU(codigoPais VARCHAR2) ;

  /***************************************************************************
  Descripcion       : Realiza la validacion de los Tipos de Cursos Exigidos
                      por concursos
  Fecha Creacion    : 15/02/2007
     pdOidCliente        : Oid del Cliente
     psOidParamGeneral   : Oid del Concurso
  Autor             : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION  INC_FN_ASIS_TIPOS_CURS_EXI (pdOidCliente NUMBER,
                                        psOidParamGeneral NUMBER)
  RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Realiza la validacion del indicador de Actividad
                      de concursos
  Fecha Creacion    : 15/02/2007
     pdOidCliente        : Oid del Cliente
     psOidParamGeneral   : Oid del Concurso
     psPeriodoInicio     : Periodo de Inicio de Evaluacion
     psPeriodoFin        : Periodo de Final de Evaluacion

  Autor             : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION  INC_FN_ACTIV_CONSE (pdOidCliente NUMBER,
                                        psOidParamGeneral NUMBER,
                                        psPeriodoInicio VARCHAR2,
                                        psPeriodoFin VARCHAR2)
RETURN BOOLEAN;

/***************************************************************************
Descripcion       : Realiza la validacion del indicador de Constancia
                    de concursos
Fecha Creacion    : 26/06/2007
   pdOidCliente        : Oid del Cliente
   psCodPeriodoInicio   : codigo de Periodo Inicial
   psCodPeriodoFina     : Codigo de Periodo Final
   psOidPais        :  Oid de Pais
   psOidMarca        : Oid de Marca
   psOidCanal        : Oid de Canal

Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION  INC_FN_CONS_CONSEC (pdOidCliente NUMBER,
                              psCodPeriodoInicio VARCHAR2,
                              psCodPeriodoFina VARCHAR2,
                              psOidPais NUMBER,
                              psOidMarca NUMBER,
                              psOidCanal NUMBER)
RETURN BOOLEAN;
/***************************************************************************
Descripcion       : Funcion que la utilizaremos para asignar un nivel determinado
                    de un cliente para la evaluacion
Fecha Creacion    : 17/05/2007
   pdOidCliente        : Oid del Cliente
   psOidParamGeneral   : Oid del Concurso

Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION  INC_FN_OBTIE_NIVEL_CLIEN (pdOidCliente NUMBER,
                                      psOidParamGeneral NUMBER)
RETURN NUMBER ;
/***************************************************************************
Descripcion       : Funcion que la utilizaremos para determinar que cliente
                    tiene un puntaje minimo al menor puntaje de los niveles
                    ya ingresados en INC_REPOR_PROY_CONC
Fecha Creacion    : 17/05/2007
   pdOidCliente        : Oid del Cliente
   psOidParamGeneral   : Oid del Concurso

Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION  INC_FN_OBTIE_PUNMI_CLIEN (pdOidCliente NUMBER,
                                      psOidParamGeneral NUMBER)
RETURN NUMBER;
/***************************************************************************
Descripcion       : Funcion que la utilizaremos para obtener el puntaje del clientee
                    de la tabla INC_CUENT_CORRI_PUNTO
Fecha Creacion    : 17/05/2007
   pdOidCliente        : Oid del Cliente
   psOidParamGeneral   : Oid del Concurso

Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION  INC_FN_OBTIE_PUNTA_CLIEN (pdOidCliente NUMBER,
                                      psOidParamGeneral NUMBER)
RETURN NUMBER;


/***************************************************************************
Descripcion       : Funcion que la utilizaremos para asignar la meta minima
Fecha Creacion    : 19/01/2010
   psOidClasClienParti     : Oid de la Clasificacion de Clientes Participantes

Autor             : Alexander Villavicencio Adán
***************************************************************************/
FUNCTION  INC_FN_META_MINIM (psOidClasClienParti NUMBER)
RETURN NUMBER;


/***************************************************************************
Descripcion       : Obtiene las cantidades de premios entregados, asi como
                    el monto base imponible, igv y monto total
Fecha Creacion    : 12/12/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_OBTIE_PREMI_ENTRE
  (psCodPais                   VARCHAR2,
   psCodMarca                  VARCHAR2,
   psCodCanal                  VARCHAR2,
   psFechaInicioFacturacion    VARCHAR2,
   psFechaFinFacturacion       VARCHAR2,
   psCampoFiltro               VARCHAR2,
   psCodConcursos              VARCHAR2,
   psCodProgramas              VARCHAR2,
   psCodSAP                    VARCHAR2);

/***************************************************************************
Descripcion       : Obtiene las cantidades de devoluciones, anulaciones,
                    trueques, canjes, que se han generados por los premios
                    entregados a las consultoras
Fecha Creacion    : 05/12/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_OBTIE_INDIC_GESTI_INCEN
  (psCodPais             VARCHAR2,
   psCodMarca            VARCHAR2,
   psCodCanal            VARCHAR2,
   psCampoFiltro         VARCHAR2,
   psCodConcursos        VARCHAR2,
   psCodPeriodoInicio    VARCHAR2,
   psCodPeriodoFin       VARCHAR2);


/***************************************************************************
Descripcion       :  Genera la data delos premios despachadaoos para luego ser mostrada
             por el reporte
Fecha Creacion    :  23/06/2009
Parametros      :
   psCodPais             Codigo Pais
   psCodMarca            Codigo Marca
   psCodCanal            Codigo Canal
   psTipoCierre          Tipo Cierre  (D:diario,R:region Z:zona, P:campanha)
   psCodPeriodo           Codigo Periodo
   psFechaProceso         Fecha Proceso
   psUsuario            Usuario
   psNumLote       Numero de Lote que se genera
Autor             : Sergio Buchelli
***************************************************************************/
PROCEDURE INC_PR_GENER_REPOR_INCEN
  (psCodPais             VARCHAR2,
   psCodMarca            VARCHAR2,
   psCodCanal            VARCHAR2,
   psTipoCierre          VARCHAR2,
   psCodPeriodo           VARCHAR2,
   psFechaProceso         VARCHAR2,
   psUsuario            VARCHAR2,
   psNumLote       OUT VARCHAR2);


/***************************************************************************
Descripcion       :  Genera el calculo de metas(venta base), clasificacion y creacion de mensajes
                     de concursos Dupl Cyzone
Fecha Creacion    :  19/01/2010
Parametros        :
   p_cod_pais             Codigo Pais
   p_cod_concurso         Numero Concurso
   p_cod_peri             Periodo
Autor             : Alexander Villavicencio
***************************************************************************/

PROCEDURE INC_PR_CALCU_VENTA(
        p_cod_pais     VARCHAR2,
        p_cod_concurso VARCHAR2,
        p_cod_peri     VARCHAR2,
        p_ind_Eliminar VARCHAR2);

/**************************************************************************
Descripcion        : Recupera la Cantidad de Campañas sobre un rango de Periodos
Fecha Creacion     :  01/03/2011
Parametros Entrada :
           pnOidPeriodoDesde : oid Periodo Desde
           pnOidPeriodoHasta : oid Periodo Hasta

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INC_FN_CALCU_CANTI_PERIO(pnOidPeriodoDesde    NUMBER,
                                  pnOidPeriodoHasta    NUMBER)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Funcion que la utilizaremos para obtenes la campaña
                    siguiente
Fecha Creacion    : 13/09/2013
   pdOidPeriodo    : Oid del Periodo
   psOidPais       : Oid del Pais
   psNumCampana    : Numero Campaña Siguiente

Autor              : Aurelio Oviedo
***************************************************************************/
FUNCTION  INC_FN_OBTIE_CAMPA_SIGUI (pdOidPeriodo NUMBER,
                                    psOidPais NUMBER,
                                    psNumCampanas NUMBER)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Genera la data para el Reporte del Programa de
                    Reconocimiento
Fecha Creacion    : 02/10/2013
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE  INC_PR_CARGA_HISTO_PROGR_RECON(
    psNumConcurso   VARCHAR2,
    psOidPeriodo    VARCHAR2,
    psPuntMinimo    NUMBER,
    psPuntMaximo    NUMBER
);

/***************************************************************************
Descripcion       : Genera la data para el Reporte del Programa de
                    Reconocimiento COLOMBIA
Fecha Creacion    : 03/10/2013
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE  INC_PR_HISTO_PROGR_RECON_COLOM(
    psNumConcurso   VARCHAR2,
    psOidPeriodo    VARCHAR2,
    psPuntMinimo    NUMBER,
    psPuntMaximo    NUMBER
);

/***************************************************************************
Descripcion       : Genera la data para el Reporte de cupones electronicos

Fecha Creacion    : 06/11/2013
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE INC_PR_REPOR_CUPON_ELECT(
    psNumeroConcurso VARCHAR2,
    psUsuario VARCHAR2
);

/***************************************************************************
Descripcion       : Genera el Reporte de cupones electronicos

Fecha Creacion    : 06/11/2013
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE INC_PR_GENER_REPOR_CUPON_ELECT(
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psUsuario VARCHAR2,
    psdirectorio             OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Genera el Reporte Puntos Obtenidos de Bolsa Faltantes
Fecha Creacion    : 28/01/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_REPOR_PUNTO_BOLSA_FALTA(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Genera el Reporte Puntaje Obtenidos y Puntahe Faltantes
Fecha Creacion    : 10/04/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INC_PR_REPOR_POBTE_PFALT_CSV(
    pscodigopais       VARCHAR2,
    pstipoReporte      VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Genera el Reporte Puntos Consultora
Fecha Creacion    : 28/05/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_REPOR_PUNTO_CONSU_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Genera el Reporte Puntos Campania
Fecha Creacion    : 28/05/2014
Autor             : Sebastian Guerra
***************************************************************************/
PROCEDURE INC_PR_REPOR_PUNTO_CAMPA_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Genera el Reporte Consultoras con Puntajes
Fecha Creacion    : 28/09/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INC_PR_REPOR_CONSU_PUNTA(
    pnOidConcurso      NUMBER
);

/***************************************************************************
Descripcion       : Genera el Reporte Programa Puntos Nacional
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROGR_PUNTO_NACI_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    pscondicionPeriodo VARCHAR2,
    psdirectorio       OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Genera el Reporte Programa Puntos por Region
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROGR_PUNTO_REGI_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    pscondicionPeriodo VARCHAR2,
    pscondicionRegion  VARCHAR2,
    psdirectorio       OUT VARCHAR2
);


/***************************************************************************
Descripcion       : Genera el Reporte Programa Puntos por Zona
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROGR_PUNTO_ZONA_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    pscondicionPeriodo VARCHAR2,
    pscondicionRegion  VARCHAR2,
    pscondicionZona    VARCHAR2,
    psdirectorio       OUT VARCHAR2
);


/***************************************************************************
Descripcion       : Genera el Reporte Programa Puntos por Consultora
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROGR_PUNTO_CONS_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    pscondicionPeriodo VARCHAR2,
    pscondicionRegion  VARCHAR2,
    pscondicionZona    VARCHAR2,
    psdirectorio       OUT VARCHAR2
);


/***************************************************************************
Descripcion       : Genera el Reporte Programa Puntos por Campania
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROGR_PUNTO_CAMP_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    pscondicionPeriodo VARCHAR2,
    pscondicionRegion  VARCHAR2,
    pscondicionZona    VARCHAR2,
    psdirectorio       OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Genera el Reporte para Provisión Contable Ingresos
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROVI_CONTA_INGRE_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    psfechaInicio      VARCHAR2,
    psfechaFin         VARCHAR2,
    psdirectorio       OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Genera el Reporte para Provisión Contable Gastos
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROVI_CONTA_GASTO_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    psfechaInicio      VARCHAR2,
    psfechaFin         VARCHAR2,
    psdirectorio       OUT VARCHAR2
);


END INC_PKG_REPOR_INCEN;
/
CREATE OR REPLACE PACKAGE BODY "INC_PKG_REPOR_INCEN" IS

/***************************************************************************
Descripcion : Genera data para Reporte de Proyeccion de Premios
 de concursos
 Fecha Creacion : 15/02/2007
 psPeriodoInicio : Periodo de Inicio para la simulacion
 psPeriodoFinal : Periodo Final para la simulacion
 Autor : Marco Antonio Agurto Jimenez
 Autor Modificación: Carlos Diaz Valverde
 Fecha Modificación: 29/11/2010
***************************************************************************/

PROCEDURE INC_PR_GENER_PROYE_PREMI_CONCU (codigoPais VARCHAR2)

IS
 tablaRegistro tablaIncentivos;
 tablaRegistroClientes tablaClientes;
 r_ReporteProyeccion tReporteProyeccion;
CURSOR incentivos IS
 SELECT *
 FROM INC_REPOR_PROY_CONC
 ORDER BY INC_REPOR_PROY_CONC.Oid_Para_Gral, INC_REPOR_PROY_CONC.Num_Nive;

 CURSOR clientes (oidparametroGeneral NUMBER, numNivel NUMBER) IS
 SELECT CLIE_OID_CLIE,
 COPA_OID_PARA_GRAL ,
 COD_SUB_GEREN_VENT ,
 DES_SUB_GEREN_VENT ,
 COD_REGI ,
 DES_REGI ,
 COD_ZONA ,
 DES_ZONA ,
 PUNT_CLIE,
 VAL_PUNT_CCPU
 FROM INC_REPOR_PROY_CLIEN
 WHERE COPA_OID_PARA_GRAL = oidparametroGeneral
 AND NUM_NIVE = numNivel
 ORDER BY Clie_Oid_Clie;

 lsConcursoConMetas NUMBER;
 clientesValidos BOOLEAN;
-- puntajeMeta NUMBER;
 montoMinimoIngreso NUMBER;
 cuotaIngreso NUMBER;
 --existeCliente NUMBER;
 montoFacturadoCliente NUMBER;
 clienteCalificado NUMBER;
 numeroPedidosCliente NUMBER;
 numeroPedidosConcurso NUMBER;
 periodoHastaConsurso seg_perio_corpo.cod_peri%TYPE;
 periodoActual seg_perio_corpo.cod_peri%TYPE;
 periodoDesdeConsurso seg_perio_corpo.cod_peri%TYPE;
-- fechaActual VARCHAR2(8);
 periodoInicioCliente VARCHAR2(6);
 periodoFinCliente VARCHAR2(6);
 psOidPais seg_pais.oid_pais%TYPE;
 psOidMarca seg_marca.oid_marc%TYPE;
 psOidCanal seg_canal.oid_cana%TYPE;
 --oidPeriodoInicioCliente NUMBER;
 --oidPeriodoFinCliente NUMBER;
 indicadorActividad INC_OBTEN_PUNTO.IND_ACTI%TYPE;
 indicadorConstancia INC_OBTEN_PUNTO.IND_CONS%TYPE;
 periodoFechaInicio SEG_PERIO_CORPO.COD_PERI%TYPE;
 periodoFechaInicio2 SEG_PERIO_CORPO.COD_PERI%TYPE;
 periodoFechaFin SEG_PERIO_CORPO.COD_PERI%TYPE;
 regRequisitosPremiacion INC_REQUI_PREMI%ROWTYPE;
-- numeroPedidosClienteIntervalo NUMBER;
-- nivelCorrecto NUMBER;
 pagina NUMBER:=0;
 registro NUMBER:=0;
 parametroGeneral NUMBER(12);
 contador NUMBER;
 nivelMax NUMBER;

 CURSOR nivelesAcum(vnNivel NUMBER)IS
 SELECT *
 FROM INC_REPOR_PROY_CONC
 WHERE NUM_NIVE = vnNivel
 ORDER BY INC_REPOR_PROY_CONC.Oid_Para_Gral, INC_REPOR_PROY_CONC.Num_Nive;

 tablaRegistroInf tablaIncentivos;

 numRota NUMBER;
 puntajeConsultora NUMBER;

 v_num_nive NUMBER;
 v_punt_nive NUMBER;
 v_oid_para_nive_prem NUMBER;
 v_val_nive_sele NUMBER;
 v_num_cant_inic_punt NUMBER;

 /* variables para nueva validacion */
 v_canped NUMBER;
 v_ppedid NUMBER;

 lnCantidadPeriodos       NUMBER;
 lnCantidadPeriodosAux    NUMBER;

BEGIN

  /* Inicializando variables */
  lsConcursoConMetas := 0;
  periodoHastaConsurso := '';
  psOidPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(codigoPais);
  psOidMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA('T');
  psOidCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

  SELECT    DISTINCT INC_REPOR_PROY_CONC.Oid_Para_Gral,
            INC_REPOR_PROY_CONC.Perd_Oid_Peri_Hast,
            INC_REPOR_PROY_CONC.PERD_OID_PERI_DESD
    INTO    parametroGeneral,
            periodoHastaConsurso,
            periodoDesdeConsurso
  FROM      INC_REPOR_PROY_CONC;

  /* Se determina si el concurso tiene metas */
  SELECT    COUNT(INC_PARAM_CALIF.Oid_Para_Cali)
    INTO    lsConcursoConMetas
  FROM      INC_PARAM_CALIF
  WHERE     INC_PARAM_CALIF.COPA_OID_PARA_GRAL = parametroGeneral;

  /* Obtenemos los Datos de Requisitos de Premiacion */
  SELECT    *
    INTO    regRequisitosPremiacion
  FROM      INC_REQUI_PREMI
  WHERE     INC_REQUI_PREMI.COPA_OID_PARA_GRAL = parametroGeneral;

  /* Obtenemos los indicadores de Actividad y Constancia */
  indicadorActividad := 0;
  indicadorConstancia:= 0;

  SELECT    INC_OBTEN_PUNTO.IND_ACTI,
            INC_OBTEN_PUNTO.IND_CONS
    INTO    indicadorActividad,
            indicadorConstancia
  FROM      INC_OBTEN_PUNTO
  WHERE     INC_OBTEN_PUNTO.COPA_OID_PARA_GRAL = parametroGeneral;

  /* Obtenemos el periodo FIN para poder evaluar la constancia o actividad */
  periodoFechaFin := gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO( periodoHastaConsurso);

  SELECT    min(SEG_PERIO_CORPO.COD_PERI)
    INTO    periodoActual
  FROM      CRA_PERIO,
            SEG_PERIO_CORPO
  WHERE     SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI
  AND       CRA_PERIO.FEC_INIC <= SYSDATE
  AND       CRA_PERIO.FEC_FINA >= SYSDATE;

  /* Obteniendo el periodo de Fin */
  periodoFinCliente := '';

  IF periodoFechaFin >= periodoActual THEN
    periodoFinCliente := periodoActual;
  ELSE
    periodoFinCliente := periodoFechaFin;
  END IF;

  /* Obteniendo el Periodo de la Inicial del Concurso */
  periodoFechaInicio := gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(periodoDesdeConsurso);

  /* LOGICA GENERAL ---------------------------------------------------------------------------- */
  OPEN incentivos;
  LOOP
      FETCH incentivos BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;

      IF tablaRegistro.COUNT > 0 THEN -- IF LOGICA GENERAL

          /* LOGICA GENERAL PAGINADA ----------------------------------------------------------- */
          FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
          BEGIN

              /* variables de pagina y contador de filas x pagina */
              pagina:=x;
              contador:=0;

              lnCantidadPeriodos := INC_FN_CALCU_CANTI_PERIO(tablaRegistro(x).PERD_OID_PERI_DESD,
                                    tablaRegistro(x).PERD_OID_PERI_HAST);

              /* cursor clientes - LOGICA GENERAL PAGINADA ------------------------------------- */
              OPEN clientes(tablaRegistro(x).OID_PARA_GRAL, tablaRegistro(x).NUM_NIVE);
              LOOP
                  FETCH clientes BULK COLLECT INTO tablaRegistroClientes LIMIT W_FILAS;

                  /* if cursor clientes - LOGICA GENERAL PAGINADA ------------------------------ */
                  IF tablaRegistroClientes.COUNT > 0 THEN

                      /* cursor clientes paginado - LOGICA GENERAL PAGINADA -------------------- */
                      FOR y IN tablaRegistroClientes.FIRST .. tablaRegistroClientes.LAST LOOP
                      BEGIN

                          /* cursor clientes paginado IF - LOGICA GENERAL PAGINADA ------------- */
                          IF tablaRegistro(x).IND_NIVE_ROTA = 1 THEN


                              clientesValidos:= TRUE;
                              numRota := tablaRegistro(x).NUM_ROTA;
                              puntajeConsultora := tablaRegistroClientes(y).PUNT_CLIE;

                              LOOP -- del IF - tablaRegistro(x).IND_NIVE_ROTA = 1

                                  select    num_nive,
                                            punt_nive,
                                            oid_para_nive_prem,
                                            VAL_NIVE_SELE,
                                            num_cant_inic_punt
                                    into    v_num_nive,
                                            v_punt_nive,
                                            v_oid_para_nive_prem,
                                            v_val_nive_sele,
                                            v_num_cant_inic_punt
                                  from      INC_REPOR_PROY_CONC
                                  where     num_cant_inic_punt <= puntajeConsultora
                                    and     num_cant_fina_punt >= puntajeConsultora;
                                  puntajeConsultora := puntajeConsultora - v_punt_nive;


                                  /* 1 - Validamos que el cliente no este descalificado */
                                  IF (clientesValidos) THEN

                                      clienteCalificado := 0;

                                      SELECT    COUNT(1)
                                        INTO    clienteCalificado
                                      FROM      INC_DESCA
                                      WHERE     INC_DESCA.COPA_OID_PARA_GRAL = tablaRegistro(x).OID_PARA_GRAL
                                        AND     INC_DESCA.CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;

                                      IF (clienteCalificado > 0) THEN
                                        clientesValidos:= FALSE;
                                        UPDATE    INC_REPOR_PROY_CLIEN
                                           SET    MOTI_CLIE_INVA = 'Inválido por Descalificación'
                                        WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                      END IF;

                                  END IF; -- end if _ 1 - Validamos que el cliente no e


                                 /* 2 - El puntaje del cliente debe cumplir la cuota minima de ingreso */
                                  IF (clientesValidos) and regRequisitosPremiacion.VAL_CUOT_INGR>1 THEN

                                      cuotaIngreso := regRequisitosPremiacion.VAL_CUOT_INGR;

                                      IF (cuotaIngreso > tablaRegistroClientes(y).VAL_PUNT_CCPU) THEN
                                          clientesValidos:= FALSE;
                                          UPDATE    INC_REPOR_PROY_CLIEN
                                             SET    MOTI_CLIE_INVA = 'Inválido por Cuota mínima no alcanzada'
                                          WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                      END IF;

                                  END IF; -- end if _ 2 - El puntaje del cliente debe ...


                                  /* 3 - El monto de venta del cliente debe cumplir con el monto
                                         minimo deconcurso */
                                  IF (clientesValidos) and regRequisitosPremiacion.VAL_MONT_MINI_CONC>1 THEN

                                      montoMinimoIngreso := regRequisitosPremiacion.VAL_MONT_MINI_CONC;
                                      montoFacturadoCliente:=0;

                                      SELECT    SUM(sc.VAL_PREC_CATA_TOTA_LOCA)
                                        INTO    montoFacturadoCliente
                                       FROM     PED_SOLIC_CABEC sc,
                                                ped_tipo_solic_pais tsp,
                                                ped_tipo_solic ts,
                                                PED_SOLIC_CABEC sc2
                                      WHERE    sc.CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE
                                        AND    sc.PERD_OID_PERI > =tablaRegistro(x).PERD_OID_PERI_DESD
                                        AND    sc.PERD_OID_PERI < =tablaRegistro(x).PERD_OID_PERI_HAST
                                        AND    sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                                        AND    tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                        AND    ts.cod_tipo_soli = 'SOC'
                                        AND    sc.soca_oid_soli_cabe = sc2.oid_soli_cabe
                                        AND    sc2.esso_oid_esta_soli <> 4; -- ANULADO --

                                      IF (montoMinimoIngreso > 0 AND montoMinimoIngreso > montoFacturadoCliente ) THEN
                                          clientesValidos:= FALSE;
                                          UPDATE    INC_REPOR_PROY_CLIEN
                                             SET    MOTI_CLIE_INVA = 'Inválido por monto de venta mínima no alcanzada'
                                          WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                      END IF;

                                  END IF; -- end if _ 3 - El monto de venta del cliente


                                  /* 4 - El cliente debe de tener asistencia en todos los tipos de cursos
                                         exigidos por el concurso */
/*
                                  IF (clientesValidos) THEN
                                      clientesValidos := INC_FN_ASIS_TIPOS_CURS_EXI(
                                                           tablaRegistroClientes(y).CLIE_OID_CLIE,
                                                           tablaRegistro(x).OID_PARA_GRAL
                                                         );
                                      IF clientesValidos = FALSE THEN
                                          UPDATE    INC_REPOR_PROY_CLIEN
                                             SET    MOTI_CLIE_INVA = 'Inválido por inasistencias en los cursos exigidos por el concurso'
                                          WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                      END IF;

                                  END IF; -- end if _ 4 - El cliente debe de tener asistencia

*/

                                  /* 5 - Calculamos la cantidad de Pedidos del Cliente, esto solo se debera
                                         calcular siempre y cuando en el concurso se halla especificado
                                         evaluar por constancia o actividad */
                                  IF (clientesValidos) and regRequisitosPremiacion.NUM_PEDI>1 THEN

                                      numeroPedidosCliente:=0;

/*
                                      SELECT    COUNT(1)
                                        INTO    numeroPedidosCliente
                                      FROM      INC_SOLIC_CONCU_PUNTA
                                      WHERE     INC_SOLIC_CONCU_PUNTA.CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE
                                        AND     INC_SOLIC_CONCU_PUNTA.COPA_OID_PARA_GRAL = tablaRegistro(x).OID_PARA_GRAL
                                        AND     INC_SOLIC_CONCU_PUNTA.IND_ANUL = 0
                                        AND     INC_SOLIC_CONCU_PUNTA.IMP_MONT > 0;
*/
                                       SELECT COUNT(DISTINCT D.PERD_OID_PERI)
                                          INTO    numeroPedidosCliente
                                          FROM PED_SOLIC_CABEC     Z,
                                               PED_TIPO_SOLIC_PAIS B,
                                               PED_TIPO_SOLIC      C,
                                               PED_SOLIC_CABEC     D,
                                               INC_CONCU_PARAM_GENER E
                                       WHERE Z.IND_OC = 1
                                             AND Z.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
                                             AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
                                             AND C.COD_TIPO_SOLI = 'SOC' -- Tipo de solicitud Orden de Compra
                                             AND Z.SOCA_OID_SOLI_CABE = D.OID_SOLI_CABE
                                             AND D.ESSO_OID_ESTA_SOLI != 4 -- No esta anulado
                                             AND Z.CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE
                                             AND E.OID_PARA_GRAL = tablaRegistro(x).OID_PARA_GRAL
                                             AND Z.PERD_OID_PERI = D.PERD_OID_PERI
                                             AND gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(D.PERD_OID_PERI) BETWEEN
                                                 gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(E.PERD_OID_PERI_DESD) AND
                                                 gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(E.PERD_OID_PERI_HAST);

                                      numeroPedidosConcurso := regRequisitosPremiacion.NUM_PEDI;

                                      IF ( numeroPedidosCliente < numeroPedidosConcurso ) THEN
                                          clientesValidos:= FALSE;
                                          UPDATE    INC_REPOR_PROY_CLIEN
                                             SET    MOTI_CLIE_INVA = 'Inválido por Número de Pedidos no alcanzado'
                                          WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                      END IF;

                                  END IF; -- end if _ 5 - Calculamos la cantidad de Pedidos del ...


                                 /* 6 - Si el concurso se ha especificado evaluar actividad o constancia
                                        se calcula el periodo inicio y fin para validar al cliente */
                                  IF (clientesValidos AND ((indicadorActividad = 1) OR (indicadorConstancia = 1) )) THEN

                                      /* -- */
                                      BEGIN

                                        SELECT    gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(MAE_CLIEN_PRIME_CONTA.Perd_Oid_Peri)
                                          INTO    periodoFechaInicio2
                                        FROM      MAE_CLIEN_PRIME_CONTA
                                        WHERE     MAE_CLIEN_PRIME_CONTA.CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE
                                          AND     MAE_CLIEN_PRIME_CONTA.Perd_Oid_Peri IS NOT NULL;

                                      EXCEPTION
                                        WHEN NO_DATA_FOUND THEN
                                          periodoFechaInicio2 := '';--Para que tome la condicion primera
                                      END;

                                      /*Obteniendo el periodo de Inicio*/
                                      IF ( periodoFechaInicio2 >= periodoFechaInicio ) THEN
                                        periodoInicioCliente := periodoFechaInicio2;
                                      ELSE
                                        periodoInicioCliente := periodoFechaInicio;
                                      END IF;

                                      /* -- */
                                      IF (indicadorConstancia = 1) THEN

                                        IF (periodoInicioCliente > periodoFinCliente) THEN
                                          clientesValidos := FALSE;
                                          UPDATE    INC_REPOR_PROY_CLIEN
                                             SET    MOTI_CLIE_INVA = 'Inválido por período constancia'
                                          WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                        END IF;

                                        IF clientesValidos = TRUE THEN

                                           clientesValidos := INC_FN_CONS_CONSEC(
                                                                tablaRegistroClientes(y).CLIE_OID_CLIE,
                                                                periodoInicioCliente,
                                                                periodoFinCliente,
                                                                psOidPais,
                                                                psOidMarca,
                                                                psOidCanal
                                                              );
                                           IF clientesValidos = FALSE THEN
                                             UPDATE    INC_REPOR_PROY_CLIEN
                                                SET    MOTI_CLIE_INVA = 'Inválido por Constancia'
                                             WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                           END IF;

                                        END IF;

                                      END IF;

                                      /* -- */
                                      IF (indicadorActividad = 1) THEN

                                        IF (periodoInicioCliente > periodoFinCliente) THEN
                                          clientesValidos := FALSE;
                                          UPDATE    INC_REPOR_PROY_CLIEN
                                             SET    MOTI_CLIE_INVA = 'Inválido por período actividad'
                                          WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                        END IF;

                                        IF clientesValidos = TRUE THEN
                                          clientesValidos :=INC_FN_ACTIV_CONSE( tablaRegistro(x).OID_PARA_GRAL, tablaRegistroClientes(y).CLIE_OID_CLIE,periodoInicioCliente,periodoFinCliente );
                                          IF clientesValidos = FALSE THEN
                                            UPDATE    INC_REPOR_PROY_CLIEN
                                               SET    MOTI_CLIE_INVA = 'Inválido por Actividad'
                                            WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                          END IF;
                                        END IF;

                                      END IF;

                                  END IF; -- end if _ 6 - Si el concurso se ha especificad ....


                                  /* 7 - Validar consultoras descalificadas por no cumplir la condición
                                         de constancia */
                                  IF (clientesValidos) THEN

                                      IF (indicadorConstancia = 1) THEN

                                          -- Cantidad de pedidos, las consultoras constantes deben tener 3,
                                          -- solo las consultoras nuevas durante el concurso pueden tener menos.
                                          BEGIN
                                            select    count(distinct perd_oid_peri)
                                              into    v_canped
                                            from      ped_solic_cabec_acum2
                                            where     clie_oid_clie = tablaRegistroClientes(y).CLIE_OID_CLIE
                                              and     perd_oid_peri >= tablaRegistro(x).PERD_OID_PERI_DESD
                                              and     perd_oid_peri <= tablaRegistro(x).PERD_OID_PERI_HAST;
                                          EXCEPTION
                                            WHEN NO_DATA_FOUND THEN
                                              v_canped := 0;
                                          END;

                                          -- Periodo de primer pedido, se genera para saber
                                          -- cuales son nuevas durante el concurso
                                          BEGIN
                                            select    min(perd_oid_peri)
                                              into    v_ppedid
                                            from      ped_solic_cabec_acum2
                                            where     clie_oid_clie = tablaRegistroClientes(y).CLIE_OID_CLIE
                                            and ((clie_oid_clie in (select  estat.clie_oid_clie  from mae_clien_histo_estat estat
                                                                            where estat.perd_oid_peri  <=  periodoFechaFin
                                                                                    and estat.esta_oid_esta_clie in (1,2, 7,8)
                                                                                    and (estat.perd_oid_peri_peri_fin is null or estat.perd_oid_peri_peri_fin>= periodoFechaInicio ))
                                                                                        and perd_oid_peri>=periodoFechaInicio)
                                                    or ( (clie_oid_clie not in (select  estat.clie_oid_clie  from mae_clien_histo_estat estat
                                                                            where estat.perd_oid_peri  <=periodoFechaFin
                                                                                    and estat.esta_oid_esta_clie in ( 1,2,7,8)
                                                                                    and (estat.perd_oid_peri_peri_fin is null or estat.perd_oid_peri_peri_fin>=periodoFechaInicio )))));

                                          EXCEPTION
                                            WHEN NO_DATA_FOUND THEN
                                              v_ppedid := 0;
                                          END;


                                          -- Validación para actualización
                                          IF(v_canped < lnCantidadPeriodos AND v_ppedid < tablaRegistro(x).PERD_OID_PERI_DESD) THEN
                                            clientesValidos:= FALSE;
                                            UPDATE    INC_REPOR_PROY_CLIEN
                                               SET    MOTI_CLIE_INVA = 'Inválido por Constancia'
                                            WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                          ELSE
                                            IF (v_ppedid >= tablaRegistro(x).PERD_OID_PERI_DESD) THEN
                                              lnCantidadPeriodosAux := INC_FN_CALCU_CANTI_PERIO(v_ppedid,
                                                                                   tablaRegistro(x).PERD_OID_PERI_HAST);

                                              IF(v_canped < lnCantidadPeriodosAux) THEN
                                                clientesValidos:= FALSE;
                                                UPDATE    INC_REPOR_PROY_CLIEN
                                                   SET    MOTI_CLIE_INVA = 'Inválido por Constancia'
                                                WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;

                                              END IF;

                                            END IF;
                                          END IF;

                                      END IF;

                                  END IF; -- end if _ 7 - Validar consultoras descalificadas ....


                                  /* 8 - Formateo de columnas para mostrar en el reporte */
                                  IF (clientesValidos) THEN

                                     r_ReporteProyeccion.PERI_DESD := tablaRegistro(x).PERI_DESD;
                                     r_ReporteProyeccion.PERI_HAST := tablaRegistro(x).PERI_HAST;
                                     r_ReporteProyeccion.PERD_OID_PERI_DESD := tablaRegistro(x).PERD_OID_PERI_DESD;
                                     r_ReporteProyeccion.PERD_OID_PERI_HAST := tablaRegistro(x).PERD_OID_PERI_HAST;
                                     r_ReporteProyeccion.IND_PREM_ACUM_NIVE := tablaRegistro(x).IND_PREM_ACUM_NIVE;
                                     r_ReporteProyeccion.VAL_HAST_NIVE := tablaRegistro(x).VAL_HAST_NIVE;
                                     r_ReporteProyeccion.NUM_NIVE := v_num_nive;-- por el que hemos recuperado arriba
                                     r_ReporteProyeccion.PUNT_NIVE := v_punt_nive;-- por el que hemos recuperado arriba
                                     r_ReporteProyeccion.VAL_NIVE_SELE := v_val_nive_sele; -- por el que hemos recuperado arriba
                                     r_ReporteProyeccion.OID_PARA_GRAL := tablaRegistro(x).OID_PARA_GRAL;
                                     r_ReporteProyeccion.NUM_CONC := tablaRegistro(x).NUM_CONC;
                                     r_ReporteProyeccion.IND_NIVE_ROTA := tablaRegistro(x).IND_NIVE_ROTA;
                                     r_ReporteProyeccion.NUM_ROTA := tablaRegistro(x).NUM_ROTA;

                                     /*Datos del Cliente*/
                                     r_ReporteProyeccion.COD_SUB_GEREN_VENT := tablaRegistroClientes(y).COD_SUB_GEREN_VENT;
                                     r_ReporteProyeccion.DES_SUB_GEREN_VENT := tablaRegistroClientes(y).DES_SUB_GEREN_VENT;
                                     r_ReporteProyeccion.COD_REGI := tablaRegistroClientes(y).COD_REGI;
                                     r_ReporteProyeccion.DES_REGI := tablaRegistroClientes(y).DES_REGI;
                                     r_ReporteProyeccion.COD_ZONA := tablaRegistroClientes(y).COD_ZONA;
                                     r_ReporteProyeccion.DES_ZONA := tablaRegistroClientes(y).DES_ZONA;
                                     r_ReporteProyeccion.PUNT_CLIE := tablaRegistroClientes(y).PUNT_CLIE;

                                     /*Insertamos a las consultoras que se van a mostrar en los reportes*/
                                     INSERT INTO INC_REPOR_PROY_PREM (
                                       PERI_DESD ,
                                       PERI_HAST ,
                                       PERD_OID_PERI_DESD ,
                                       PERD_OID_PERI_HAST ,
                                       IND_PREM_ACUM_NIVE ,
                                       VAL_HAST_NIVE ,
                                       NUM_NIVE ,
                                       PUNT_NIVE ,
                                       VAL_NIVE_SELE ,
                                       OID_PARA_GRAL ,
                                       COD_SAP ,
                                       DES_PROD ,
                                       NUM_CONC ,
                                       CLIE_OID_CLIE ,
                                       NUM_PREM ,
                                       NUM_UNID ,
                                       COD_SUB_GEREN_VENT ,
                                       DES_SUB_GEREN_VENT ,
                                       COD_REGI ,
                                       DES_REGI ,
                                       COD_ZONA ,
                                       DES_ZONA ,
                                       PUNT_CLIE
                                     )
                                     (
                                        SELECT    r_ReporteProyeccion.PERI_DESD ,
                                                  r_ReporteProyeccion.PERI_HAST ,
                                                  r_ReporteProyeccion.PERD_OID_PERI_DESD,
                                                  r_ReporteProyeccion.PERD_OID_PERI_HAST,
                                                  r_ReporteProyeccion.IND_PREM_ACUM_NIVE,
                                                  r_ReporteProyeccion.VAL_HAST_NIVE ,
                                                  r_ReporteProyeccion.NUM_NIVE ,
                                                  r_ReporteProyeccion.PUNT_NIVE ,
                                                  r_ReporteProyeccion.VAL_NIVE_SELE ,
                                                  r_ReporteProyeccion.OID_PARA_GRAL ,
                                                  OCR_SOLIC_PEDIDOS.GEN_FN_CODSAP_PROD(INC_ARTIC_LOTE.PROD_OID_PROD),
                                                  OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(INC_ARTIC_LOTE.PROD_OID_PROD),
                                                  r_ReporteProyeccion.NUM_CONC ,
                                                  tablaRegistroClientes(y).CLIE_OID_CLIE,
                                                  INC_LOTE_PREMI_ARTIC.NUM_PREM,
                                                  INC_ARTIC_LOTE.NUM_UNID ,
                                                  r_ReporteProyeccion.COD_SUB_GEREN_VENT,
                                                  r_ReporteProyeccion.DES_SUB_GEREN_VENT,
                                                  r_ReporteProyeccion.COD_REGI ,
                                                  r_ReporteProyeccion.DES_REGI ,
                                                  r_ReporteProyeccion.COD_ZONA ,
                                                  r_ReporteProyeccion.DES_ZONA ,
                                                  r_ReporteProyeccion.PUNT_CLIE
                                        FROM      INC_PREMI_ARTIC,
                                                  INC_LOTE_PREMI_ARTIC,
                                                  INC_ARTIC_LOTE
                                        WHERE     INC_PREMI_ARTIC.PANP_OID_PARA_NIVE_PREM = v_oid_para_nive_prem --por el hemos recuperado arriba
                                          AND     INC_LOTE_PREMI_ARTIC.PRAR_OID_PREM_ARTI = INC_PREMI_ARTIC.OID_PREM_ARTI
                                          AND     INC_LOTE_PREMI_ARTIC.OID_LOTE_PREM_ARTI = INC_ARTIC_LOTE.LOPA_OID_LOTE_PREM_ARTI
                                     );

                                     /* si el concurso es de niveles acumulativos, se procede a insertar
                                        a la consultora los premios de
                                        los niveles inferiores */
                                     IF(r_ReporteProyeccion.IND_PREM_ACUM_NIVE = 1) THEN

                                         nivelMax := r_ReporteProyeccion.NUM_NIVE - 1;

                                         FOR v IN 1 .. nivelMax LOOP

                                             OPEN nivelesAcum(v);
                                             LOOP

                                                 FETCH nivelesAcum BULK COLLECT INTO tablaRegistroInf LIMIT W_FILAS;

                                                 IF tablaRegistroInf.COUNT > 0 THEN

                                                       FOR h IN tablaRegistroInf.FIRST .. tablaRegistroInf.LAST LOOP

                                                           INSERT INTO INC_REPOR_PROY_PREM (
                                                             PERI_DESD ,
                                                             PERI_HAST ,
                                                             PERD_OID_PERI_DESD ,
                                                             PERD_OID_PERI_HAST ,
                                                              IND_PREM_ACUM_NIVE ,
                                                             VAL_HAST_NIVE ,
                                                             NUM_NIVE ,
                                                             PUNT_NIVE ,
                                                              VAL_NIVE_SELE ,
                                                             OID_PARA_GRAL ,
                                                             COD_SAP ,
                                                             DES_PROD ,
                                                             NUM_CONC ,
                                                             CLIE_OID_CLIE ,
                                                             NUM_PREM ,
                                                             NUM_UNID ,
                                                             COD_SUB_GEREN_VENT ,
                                                             DES_SUB_GEREN_VENT ,
                                                             COD_REGI ,
                                                             DES_REGI ,
                                                             COD_ZONA ,
                                                             DES_ZONA ,
                                                             PUNT_CLIE
                                                           )
                                                           (
                                                             SELECT    tablaRegistroInf(h).PERI_DESD ,
                                                                       tablaRegistroInf(h).PERI_HAST ,
                                                                       tablaRegistroInf(h).PERD_OID_PERI_DESD,
                                                                       tablaRegistroInf(h).PERD_OID_PERI_HAST,
                                                                       tablaRegistroInf(h).IND_PREM_ACUM_NIVE,
                                                                       tablaRegistroInf(h).VAL_HAST_NIVE ,
                                                                       tablaRegistroInf(h).NUM_NIVE ,
                                                                       tablaRegistroInf(h).PUNT_NIVE ,
                                                                       tablaRegistroInf(h).VAL_NIVE_SELE ,
                                                                       tablaRegistroInf(h).OID_PARA_GRAL ,
                                                                       OCR_SOLIC_PEDIDOS.GEN_FN_CODSAP_PROD(INC_ARTIC_LOTE.PROD_OID_PROD),
                                                                       OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(INC_ARTIC_LOTE.PROD_OID_PROD),
                                                                       tablaRegistroInf(h).NUM_CONC ,
                                                                       tablaRegistroClientes(y).CLIE_OID_CLIE,
                                                                       INC_LOTE_PREMI_ARTIC.NUM_PREM,
                                                                       INC_ARTIC_LOTE.NUM_UNID ,
                                                                       r_ReporteProyeccion.COD_SUB_GEREN_VENT,
                                                                       r_ReporteProyeccion.DES_SUB_GEREN_VENT,
                                                                       r_ReporteProyeccion.COD_REGI ,
                                                                       r_ReporteProyeccion.DES_REGI ,
                                                                       r_ReporteProyeccion.COD_ZONA ,
                                                                       r_ReporteProyeccion.DES_ZONA ,
                                                                       r_ReporteProyeccion.PUNT_CLIE
                                                             FROM      INC_PREMI_ARTIC,
                                                                       INC_LOTE_PREMI_ARTIC,
                                                                       INC_ARTIC_LOTE
                                                             WHERE     INC_PREMI_ARTIC.PANP_OID_PARA_NIVE_PREM = tablaRegistroInf(h).OID_PARA_NIVE_PREM
                                                               AND     INC_LOTE_PREMI_ARTIC.PRAR_OID_PREM_ARTI = INC_PREMI_ARTIC.OID_PREM_ARTI
                                                               AND     INC_LOTE_PREMI_ARTIC.OID_LOTE_PREM_ARTI = INC_ARTIC_LOTE.LOPA_OID_LOTE_PREM_ARTI
                                                           );

                                                       END LOOP;

                                                 END IF; -- end if - tablaRegistroInf.COUNT

                                                 EXIT WHEN nivelesAcum%NOTFOUND;
                                             END LOOP; -- end loop - cursor nivelesAcum
                                             CLOSE nivelesAcum; -- cursor nivelesAcum

                                         END LOOP; -- end loop - nivelMax

                                     END IF; -- end if - r_ReporteProyeccion.IND_PREM_ACUM_NIVE

                                  END IF; -- end if _ 8 - Formateo de columnas


                                  EXIT WHEN (numRota = 0 or puntajeConsultora < v_num_cant_inic_punt or clientesValidos = FALSE); -- or clientesValido = FALSE
                              END LOOP; -- end loop _ del IF - tablaRegistro(x).IND_NIVE_ROTA = 1


                          /* cursor clientes paginado ELSE - LOGICA GENERAL PAGINADA ----------- */
                          ELSE


                              registro :=y ;
                              contador:= contador +1;

                              /* Inicializamos la variable que nos indicara si adicionamos al cliente
                                 como posible ganador de premios */
                              clientesValidos:= TRUE;

                              /**********************************************************************/
                              /* VALIDACIONES DEL PROCESO */
                              /**********************************************************************/

                              /* 1 - Validamos que el cliente no este descalificado */
                              IF (clientesValidos) THEN

                                clienteCalificado := 0;

                                SELECT    COUNT(1)
                                  INTO    clienteCalificado
                                FROM      INC_DESCA
                                WHERE     INC_DESCA.COPA_OID_PARA_GRAL = tablaRegistro(x).OID_PARA_GRAL
                                  AND     INC_DESCA.CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;

                                IF (clienteCalificado > 0) THEN
                                 clientesValidos:= FALSE;
                                 UPDATE    INC_REPOR_PROY_CLIEN
                                    SET    MOTI_CLIE_INVA = 'Inválido por Descalificación'
                                 WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                END IF;

                              END IF; -- end if _ 1 - Validamos que el cliente ...


                              /* 2 - El puntaje del cliente debe cumplir la cuota minima de ingreso */
                              IF (clientesValidos) and regRequisitosPremiacion.VAL_CUOT_INGR>1 THEN

                                cuotaIngreso := regRequisitosPremiacion.VAL_CUOT_INGR;
                                IF (cuotaIngreso > tablaRegistroClientes(y).VAL_PUNT_CCPU) THEN
                                  clientesValidos:= FALSE;
                                  UPDATE    INC_REPOR_PROY_CLIEN
                                     SET    MOTI_CLIE_INVA = 'Inválido por Cuota mínima no alcanzada'
                                  WHERE CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                END IF;

                              END IF; -- end if _ 2 - El puntaje del cliente debe ...


                              /* 3 - El monto de venta del cliente debe cumplir con el monto minimo
                                     de concurso */
                              IF (clientesValidos) and regRequisitosPremiacion.VAL_MONT_MINI_CONC>1 THEN

                                montoMinimoIngreso := regRequisitosPremiacion.VAL_MONT_MINI_CONC;
                                montoFacturadoCliente:=0;

                                SELECT    SUM(sc.VAL_PREC_CATA_TOTA_LOCA)
                                  INTO    montoFacturadoCliente
                                FROM      PED_SOLIC_CABEC sc,
                                          ped_tipo_solic_pais tsp,
                                          ped_tipo_solic ts,
                                          PED_SOLIC_CABEC sc2
                                WHERE     sc.CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE
                                  AND     sc.PERD_OID_PERI > =tablaRegistro(x).PERD_OID_PERI_DESD
                                  AND     sc.PERD_OID_PERI < =tablaRegistro(x).PERD_OID_PERI_HAST
                                  AND     sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                                  AND     tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                  AND     ts.cod_tipo_soli = 'SOC'
                                  AND     sc.soca_oid_soli_cabe = sc2.oid_soli_cabe
                                  AND     sc2.esso_oid_esta_soli <> 4; -- ANULADO --

                                IF (montoMinimoIngreso > 0 AND montoMinimoIngreso > montoFacturadoCliente ) THEN
                                  clientesValidos:= FALSE;
                                  UPDATE    INC_REPOR_PROY_CLIEN
                                     SET    MOTI_CLIE_INVA = 'Inválido por monto de venta mínima no alcanzada'
                                  WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                END IF;

                              END IF; -- end if _ 3 - El monto de venta del cliente ...


                              /* 4 - El cliente debe de tener asistencia en todos los tipos de cursos
                                     exigidos por el concurso */
/*
                              IF (clientesValidos) THEN

                                clientesValidos := INC_FN_ASIS_TIPOS_CURS_EXI(tablaRegistroClientes(y).CLIE_OID_CLIE,tablaRegistro(x).OID_PARA_GRAL);
                                IF clientesValidos = FALSE THEN
                                  UPDATE    INC_REPOR_PROY_CLIEN
                                     SET    MOTI_CLIE_INVA = 'Inválido por inasistencias en los cursos exigidos por el concurso'
                                  WHERE CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                END IF;

                              END IF; -- end if _ 4 - El cliente debe de tener asistencia ...
*/

                              /* 5 - Calculamos la cantidad de Pedidos del Cliente, esto solo se debera
                                     calcular siempre y cuando en el concurso se halla especificado evaluar
                                     por constancia o actividad */
                              IF (clientesValidos) and regRequisitosPremiacion.NUM_PEDI>1 THEN

                                 numeroPedidosCliente:=0;
/*
                                 SELECT    COUNT(1)
                                   INTO    numeroPedidosCliente
                                 FROM      INC_SOLIC_CONCU_PUNTA
                                 WHERE     INC_SOLIC_CONCU_PUNTA.CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE
                                   AND     INC_SOLIC_CONCU_PUNTA.COPA_OID_PARA_GRAL = tablaRegistro(x).OID_PARA_GRAL
                                   AND     INC_SOLIC_CONCU_PUNTA.IND_ANUL = 0
                                   AND     INC_SOLIC_CONCU_PUNTA.IMP_MONT > 0;
*/
                                 SELECT COUNT(DISTINCT D.PERD_OID_PERI)
                                        INTO    numeroPedidosCliente
                                 FROM PED_SOLIC_CABEC     Z,
                                      PED_TIPO_SOLIC_PAIS B,
                                      PED_TIPO_SOLIC      C,
                                      PED_SOLIC_CABEC     D,
                                      INC_CONCU_PARAM_GENER E
                                 WHERE Z.IND_OC = 1
                                       AND Z.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
                                       AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
                                       AND C.COD_TIPO_SOLI = 'SOC' -- Tipo de solicitud Orden de Compra
                                       AND Z.SOCA_OID_SOLI_CABE = D.OID_SOLI_CABE
                                       AND D.ESSO_OID_ESTA_SOLI != 4 -- No esta anulado
                                       AND Z.CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE
                                       AND E.OID_PARA_GRAL = tablaRegistro(x).OID_PARA_GRAL
                                       AND Z.PERD_OID_PERI = D.PERD_OID_PERI
                                       AND gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(D.PERD_OID_PERI) BETWEEN
                                           gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(E.PERD_OID_PERI_DESD) AND
                                           gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(E.PERD_OID_PERI_HAST);

                                 /* Validamos que la cantidad de pedidos del cliente se mayor al
                                    numero de pedidos exigidos del concurso */
                                 numeroPedidosConcurso := regRequisitosPremiacion.NUM_PEDI;
                                 IF ( numeroPedidosCliente < numeroPedidosConcurso ) THEN
                                   clientesValidos:= FALSE;
                                   UPDATE    INC_REPOR_PROY_CLIEN
                                      SET    MOTI_CLIE_INVA = 'Inválido por Número de Pedidos no alcanzado'
                                   WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                 END IF;

                              END IF; -- end if _ 5 - Calculamos la cantidad de Pedidos ...


                              /* 6 - Si el concurso se ha especificado evaluar actividad o constancia
                                     se calcula el periodo inicio y fin para validar al cliente */
                              IF (clientesValidos AND ((indicadorActividad = 1) OR (indicadorConstancia = 1) )) THEN

                                 /* INI A */
                                 BEGIN
                                   SELECT    gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(MAE_CLIEN_PRIME_CONTA.Perd_Oid_Peri)
                                     INTO    periodoFechaInicio2
                                   FROM      MAE_CLIEN_PRIME_CONTA
                                   WHERE     MAE_CLIEN_PRIME_CONTA.CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE
                                     AND     MAE_CLIEN_PRIME_CONTA.Perd_Oid_Peri IS NOT NULL;
                                 EXCEPTION
                                   WHEN NO_DATA_FOUND THEN
                                     periodoFechaInicio2:='';--Para que tome la condicion primera
                                 END; /* FIN A */

                                 /* B- Obteniendo el periodo de Inicio */
                                 IF ( periodoFechaInicio2 >= periodoFechaInicio ) THEN
                                   periodoInicioCliente:= periodoFechaInicio2;
                                 ELSE
                                   periodoInicioCliente:= periodoFechaInicio;
                                 END IF /* FIN B */;

                                 /* INI  C */
                                 IF (indicadorConstancia = 1) THEN

                                   IF (periodoInicioCliente > periodoFinCliente) THEN
                                     clientesValidos := FALSE;
                                     UPDATE    INC_REPOR_PROY_CLIEN
                                        SET    MOTI_CLIE_INVA = 'Inválido por período constancia'
                                     WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                   END IF;

                                   IF clientesValidos = TRUE THEN
                                     clientesValidos := INC_FN_CONS_CONSEC(
                                                          tablaRegistroClientes(y).CLIE_OID_CLIE,
                                                          periodoInicioCliente,
                                                          periodoFinCliente,
                                                          psOidPais,
                                                          psOidMarca,
                                                          psOidCanal);
                                     IF clientesValidos = FALSE THEN
                                       UPDATE    INC_REPOR_PROY_CLIEN
                                          SET    MOTI_CLIE_INVA = 'Inválido por Constancia'
                                       WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                     END IF;
                                   END IF;

                                 END IF; /* FIN C */

                                 /* INI D */
                                 IF (indicadorActividad = 1) THEN

                                   IF (periodoInicioCliente > periodoFinCliente) THEN
                                     clientesValidos := FALSE;
                                     UPDATE    INC_REPOR_PROY_CLIEN
                                        SET    MOTI_CLIE_INVA = 'Inválido por período actividad'
                                     WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                   END IF;

                                   IF clientesValidos = TRUE THEN
                                     clientesValidos := INC_FN_ACTIV_CONSE(
                                                          tablaRegistro(x).OID_PARA_GRAL,
                                                          tablaRegistroClientes(y).CLIE_OID_CLIE,
                                                          periodoInicioCliente,
                                                          periodoFinCliente
                                                        );
                                     IF clientesValidos = FALSE THEN
                                       UPDATE    INC_REPOR_PROY_CLIEN
                                          SET    MOTI_CLIE_INVA = 'Inválido por Actividad'
                                       WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                     END IF;
                                   END IF;

                                 END IF; /* FIN D */

                              END IF; -- end if _ 6 - Si el concurso se ha especificado ...


                              /* 7 - Validar consultoras descalificadas por no cumplir la condición
                                     de constancia */
                              IF (clientesValidos) THEN

                                      IF (indicadorConstancia = 1) THEN

                                          -- Cantidad de pedidos, las consultoras constantes deben tener 3,
                                          -- solo las consultoras nuevas durante el concurso pueden tener menos.
                                          BEGIN
                                            select    count(distinct perd_oid_peri)
                                              into    v_canped
                                            from      ped_solic_cabec_acum2
                                            where     clie_oid_clie = tablaRegistroClientes(y).CLIE_OID_CLIE
                                              and     perd_oid_peri >= tablaRegistro(x).PERD_OID_PERI_DESD
                                              and     perd_oid_peri <= tablaRegistro(x).PERD_OID_PERI_HAST;
                                          EXCEPTION
                                            WHEN NO_DATA_FOUND THEN
                                              v_canped := 0;
                                          END;

                                          -- Periodo de primer pedido, se genera para saber
                                          -- cuales son nuevas durante el concurso
                                          BEGIN
                                            select    min(perd_oid_peri)
                                              into    v_ppedid
                                            from      ped_solic_cabec_acum2
                                            where     clie_oid_clie = tablaRegistroClientes(y).CLIE_OID_CLIE
                                            and ((clie_oid_clie in (select  estat.clie_oid_clie  from mae_clien_histo_estat estat
                                                                            where estat.perd_oid_peri  <=  periodoFechaFin
                                                                                    and estat.esta_oid_esta_clie in (1,2, 7,8)
                                                                                    and (estat.perd_oid_peri_peri_fin is null or estat.perd_oid_peri_peri_fin>= periodoFechaInicio ))
                                                                                        and perd_oid_peri>=periodoFechaInicio)
                                                    or ( (clie_oid_clie not in (select  estat.clie_oid_clie  from mae_clien_histo_estat estat
                                                                            where estat.perd_oid_peri  <=periodoFechaFin
                                                                                    and estat.esta_oid_esta_clie in ( 1,2,7,8)
                                                                                    and (estat.perd_oid_peri_peri_fin is null or estat.perd_oid_peri_peri_fin>=periodoFechaInicio )))));

                                          EXCEPTION
                                            WHEN NO_DATA_FOUND THEN
                                              v_ppedid := 0;
                                          END;

                                          -- Validación para actualización
                                          IF(v_canped < lnCantidadPeriodos AND v_ppedid < tablaRegistro(x).PERD_OID_PERI_DESD) THEN
                                            clientesValidos:= FALSE;
                                            UPDATE    INC_REPOR_PROY_CLIEN
                                               SET    MOTI_CLIE_INVA = 'Inválido por Constancia'
                                            WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;
                                          ELSE
                                            IF (v_ppedid >= tablaRegistro(x).PERD_OID_PERI_DESD) THEN
                                              lnCantidadPeriodosAux := INC_FN_CALCU_CANTI_PERIO(v_ppedid,
                                                                                   tablaRegistro(x).PERD_OID_PERI_HAST);

                                              IF(v_canped < lnCantidadPeriodosAux) THEN
                                                clientesValidos:= FALSE;
                                                UPDATE    INC_REPOR_PROY_CLIEN
                                                   SET    MOTI_CLIE_INVA = 'Inválido por Constancia'
                                                WHERE     CLIE_OID_CLIE = tablaRegistroClientes(y).CLIE_OID_CLIE;

                                              END IF;

                                            END IF;
                                          END IF;

                                      END IF;

                              END IF; -- end if _ 7 - Validar consultoras descalificadas ....


                              /* 8 - Formateo de columnas para mostrar en el reporte */
                              IF (clientesValidos) THEN

                                 r_ReporteProyeccion.PERI_DESD :=tablaRegistro(x).PERI_DESD;
                                 r_ReporteProyeccion.PERI_HAST :=tablaRegistro(x).PERI_HAST;
                                 r_ReporteProyeccion.PERD_OID_PERI_DESD :=tablaRegistro(x).PERD_OID_PERI_DESD;
                                 r_ReporteProyeccion.PERD_OID_PERI_HAST :=tablaRegistro(x).PERD_OID_PERI_HAST;
                                 r_ReporteProyeccion.IND_PREM_ACUM_NIVE :=tablaRegistro(x).IND_PREM_ACUM_NIVE;
                                 r_ReporteProyeccion.VAL_HAST_NIVE :=tablaRegistro(x).VAL_HAST_NIVE;
                                 r_ReporteProyeccion.NUM_NIVE :=tablaRegistro(x).NUM_NIVE;
                                 r_ReporteProyeccion.PUNT_NIVE :=tablaRegistro(x).PUNT_NIVE;
                                 r_ReporteProyeccion.VAL_NIVE_SELE :=tablaRegistro(x).VAL_NIVE_SELE;
                                 r_ReporteProyeccion.OID_PARA_GRAL :=tablaRegistro(x).OID_PARA_GRAL;
                                 r_ReporteProyeccion.NUM_CONC :=tablaRegistro(x).NUM_CONC;
                                 r_ReporteProyeccion.IND_NIVE_ROTA :=tablaRegistro(x).IND_NIVE_ROTA;
                                 r_ReporteProyeccion.NUM_ROTA :=tablaRegistro(x).NUM_ROTA;

                                 /*Datos del Cliente*/
                                 r_ReporteProyeccion.COD_SUB_GEREN_VENT := tablaRegistroClientes(y).COD_SUB_GEREN_VENT;
                                 r_ReporteProyeccion.DES_SUB_GEREN_VENT := tablaRegistroClientes(y).DES_SUB_GEREN_VENT;
                                 r_ReporteProyeccion.COD_REGI := tablaRegistroClientes(y).COD_REGI;
                                 r_ReporteProyeccion.DES_REGI := tablaRegistroClientes(y).DES_REGI;
                                 r_ReporteProyeccion.COD_ZONA := tablaRegistroClientes(y).COD_ZONA;
                                 r_ReporteProyeccion.DES_ZONA := tablaRegistroClientes(y).DES_ZONA;
                                 r_ReporteProyeccion.PUNT_CLIE := tablaRegistroClientes(y).PUNT_CLIE;

                                 /*Insertamos a las consultoras que se van a mostrar en los reportes*/
                                 INSERT INTO INC_REPOR_PROY_PREM (
                                   PERI_DESD ,
                                   PERI_HAST ,
                                   PERD_OID_PERI_DESD ,
                                   PERD_OID_PERI_HAST ,
                                   IND_PREM_ACUM_NIVE ,
                                   VAL_HAST_NIVE ,
                                   NUM_NIVE ,
                                   PUNT_NIVE ,
                                   VAL_NIVE_SELE ,
                                   OID_PARA_GRAL ,
                                   COD_SAP ,
                                   DES_PROD ,
                                   NUM_CONC ,
                                   CLIE_OID_CLIE ,
                                   NUM_PREM ,
                                   NUM_UNID ,
                                   COD_SUB_GEREN_VENT ,
                                   DES_SUB_GEREN_VENT ,
                                   COD_REGI ,
                                   DES_REGI ,
                                   COD_ZONA ,
                                   DES_ZONA ,
                                   PUNT_CLIE
                                 )
                                 (
                                   SELECT    r_ReporteProyeccion.PERI_DESD ,
                                             r_ReporteProyeccion.PERI_HAST ,
                                             r_ReporteProyeccion.PERD_OID_PERI_DESD,
                                             r_ReporteProyeccion.PERD_OID_PERI_HAST,
                                             r_ReporteProyeccion.IND_PREM_ACUM_NIVE,
                                             r_ReporteProyeccion.VAL_HAST_NIVE ,
                                             r_ReporteProyeccion.NUM_NIVE ,
                                             r_ReporteProyeccion.PUNT_NIVE ,
                                             r_ReporteProyeccion.VAL_NIVE_SELE ,
                                             r_ReporteProyeccion.OID_PARA_GRAL ,
                                             OCR_SOLIC_PEDIDOS.GEN_FN_CODSAP_PROD(INC_ARTIC_LOTE.PROD_OID_PROD),
                                             OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(INC_ARTIC_LOTE.PROD_OID_PROD),
                                             r_ReporteProyeccion.NUM_CONC ,
                                             tablaRegistroClientes(y).CLIE_OID_CLIE,
                                             INC_LOTE_PREMI_ARTIC.NUM_PREM,
                                             INC_ARTIC_LOTE.NUM_UNID ,
                                             r_ReporteProyeccion.COD_SUB_GEREN_VENT,
                                             r_ReporteProyeccion.DES_SUB_GEREN_VENT,
                                             r_ReporteProyeccion.COD_REGI ,
                                             r_ReporteProyeccion.DES_REGI ,
                                             r_ReporteProyeccion.COD_ZONA ,
                                             r_ReporteProyeccion.DES_ZONA ,
                                             r_ReporteProyeccion.PUNT_CLIE
                                   FROM      INC_PREMI_ARTIC,
                                             INC_LOTE_PREMI_ARTIC,
                                             INC_ARTIC_LOTE
                                   WHERE     INC_PREMI_ARTIC.PANP_OID_PARA_NIVE_PREM = tablaRegistro(x).OID_PARA_NIVE_PREM
                                     AND     INC_LOTE_PREMI_ARTIC.PRAR_OID_PREM_ARTI = INC_PREMI_ARTIC.OID_PREM_ARTI
                                     AND     INC_LOTE_PREMI_ARTIC.OID_LOTE_PREM_ARTI = INC_ARTIC_LOTE.LOPA_OID_LOTE_PREM_ARTI
                                 );

                                 /* si el concurso es de niveles acumulativos, se procede a insertar
                                    a la consultora los premios de los niveles inferiores */
                                 IF (r_ReporteProyeccion.IND_PREM_ACUM_NIVE = 1) THEN

                                     nivelMax := r_ReporteProyeccion.NUM_NIVE - 1;
                                     FOR v IN 1 .. nivelMax LOOP

                                         OPEN nivelesAcum(v);
                                         LOOP
                                             FETCH nivelesAcum BULK COLLECT INTO tablaRegistroInf LIMIT W_FILAS;

                                             IF tablaRegistroInf.COUNT > 0 THEN

                                                 FOR h IN tablaRegistroInf.FIRST .. tablaRegistroInf.LAST LOOP

                                                     INSERT INTO INC_REPOR_PROY_PREM (
                                                       PERI_DESD ,
                                                       PERI_HAST ,
                                                       PERD_OID_PERI_DESD ,
                                                       PERD_OID_PERI_HAST ,
                                                       IND_PREM_ACUM_NIVE ,
                                                       VAL_HAST_NIVE ,
                                                       NUM_NIVE ,
                                                       PUNT_NIVE ,
                                                       VAL_NIVE_SELE ,
                                                       OID_PARA_GRAL ,
                                                       COD_SAP ,
                                                       DES_PROD ,
                                                       NUM_CONC ,
                                                       CLIE_OID_CLIE ,
                                                       NUM_PREM ,
                                                       NUM_UNID ,
                                                       COD_SUB_GEREN_VENT ,
                                                       DES_SUB_GEREN_VENT ,
                                                       COD_REGI ,
                                                       DES_REGI ,
                                                       COD_ZONA ,
                                                       DES_ZONA ,
                                                       PUNT_CLIE
                                                     )
                                                     (
                                                       SELECT    tablaRegistroInf(h).PERI_DESD ,
                                                                 tablaRegistroInf(h).PERI_HAST ,
                                                                 tablaRegistroInf(h).PERD_OID_PERI_DESD,
                                                                 tablaRegistroInf(h).PERD_OID_PERI_HAST,
                                                                 tablaRegistroInf(h).IND_PREM_ACUM_NIVE,
                                                                 tablaRegistroInf(h).VAL_HAST_NIVE ,
                                                                 tablaRegistroInf(h).NUM_NIVE ,
                                                                 tablaRegistroInf(h).PUNT_NIVE ,
                                                                 tablaRegistroInf(h).VAL_NIVE_SELE ,
                                                                 tablaRegistroInf(h).OID_PARA_GRAL ,
                                                                 OCR_SOLIC_PEDIDOS.GEN_FN_CODSAP_PROD(INC_ARTIC_LOTE.PROD_OID_PROD),
                                                                 OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(INC_ARTIC_LOTE.PROD_OID_PROD),
                                                                 tablaRegistroInf(h).NUM_CONC ,
                                                                 tablaRegistroClientes(y).CLIE_OID_CLIE,
                                                                 INC_LOTE_PREMI_ARTIC.NUM_PREM,
                                                                 INC_ARTIC_LOTE.NUM_UNID ,
                                                                 r_ReporteProyeccion.COD_SUB_GEREN_VENT,
                                                                 r_ReporteProyeccion.DES_SUB_GEREN_VENT,
                                                                 r_ReporteProyeccion.COD_REGI ,
                                                                 r_ReporteProyeccion.DES_REGI ,
                                                                 r_ReporteProyeccion.COD_ZONA ,
                                                                 r_ReporteProyeccion.DES_ZONA ,
                                                                 r_ReporteProyeccion.PUNT_CLIE
                                                       FROM      INC_PREMI_ARTIC,
                                                                 INC_LOTE_PREMI_ARTIC,
                                                                 INC_ARTIC_LOTE
                                                       WHERE     INC_PREMI_ARTIC.PANP_OID_PARA_NIVE_PREM = tablaRegistroInf(h).OID_PARA_NIVE_PREM
                                                         AND     INC_LOTE_PREMI_ARTIC.PRAR_OID_PREM_ARTI = INC_PREMI_ARTIC.OID_PREM_ARTI
                                                         AND     INC_LOTE_PREMI_ARTIC.OID_LOTE_PREM_ARTI = INC_ARTIC_LOTE.LOPA_OID_LOTE_PREM_ARTI
                                                     );

                                                 END LOOP; -- end loop - tablaRegistroInf

                                             END IF; -- end if - tablaRegistroInf.COUNT

                                             EXIT WHEN nivelesAcum%NOTFOUND;
                                         END LOOP; -- cursor nivelesAcum
                                         CLOSE nivelesAcum; -- cursor nivelesAcum

                                     END LOOP; -- end loop - nivelMax

                                 END IF; -- end if - (r_ReporteProyeccion.IND_PREM_ACUM_NIVE = 1)

                              END IF;  -- end if _ 8 - Formateo de columnas para ...


                          END IF;/* cursor clientes paginado END IF - LOGICA GENERAL PAGINADA -- */

                      END; -- cursor clientes paginada - LOGICA GENERAL PAGINADA --------------- */
                      END LOOP; -- cursor clientes paginada - LOGICA GENERAL PAGINADA ---------- */

                  END IF; -- end if cursor clientes - LOGICA GENERAL PAGINADA ------------------ */

                  EXIT WHEN clientes%NOTFOUND;
              END LOOP; --  cursor clientes - LOGICA GENERAL PAGINADA
              CLOSE clientes; --  cursor clientes - LOGICA GENERAL PAGINADA

          END;  -- tablaRegistro - LOGICA GENERAL PAGINADA
          END LOOP; -- tablaRegistro - LOGICA GENERAL PAGINADA

      END IF; -- end if - LOGICA GENERAL

      EXIT WHEN incentivos%NOTFOUND;
  END LOOP ; -- incentivos - LOGICA GENERAL
  CLOSE incentivos; -- incentivos - LOGICA GENERAL

  RETURN;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_GENER_PROYE_PREMI_CONCU: '||ls_sqlerrm||' pagina '|| pagina||' registro '||registro);
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_GENER_PROYE_PREMI_CONCU: '||ls_sqlerrm||' pagina '|| pagina||' registro '||registro);

END INC_PR_GENER_PROYE_PREMI_CONCU;


/***************************************************************************
Descripcion : Realiza la validacion de los Tipos de Cursos Exigidos
 por un concurso
Fecha Creacion : 15/02/2007
 pdOidCliente : Oid del Cliente
 psOidParamGeneral : Oid del Concurso
Autor : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION INC_FN_ASIS_TIPOS_CURS_EXI (pdOidCliente NUMBER,
 psOidParamGeneral NUMBER)
RETURN BOOLEAN IS
CURSOR cursosExigidos IS
 SELECT CEP.*
 FROM INC_CURSO_EXIGI_PREMI CEP,
 INC_REQUI_PREMI RP
 WHERE CEP.REPR_OID_REQU_PREM = RP.OID_REQU_PREM
 AND RP.COPA_OID_PARA_GRAL= psOidParamGeneral;

r_cursosExigidos cursosExigidos%ROWTYPE;
inscritoCurso NUMBER;
inscritoAptaCurso NUMBER;
retorno BOOLEAN;
BEGIN

retorno := TRUE;

 OPEN cursosExigidos;
 LOOP
 FETCH cursosExigidos INTO r_cursosExigidos;
 EXIT WHEN cursosExigidos%NOTFOUND;
 BEGIN
 inscritoCurso:=0;
 SELECT COUNT(1)
 INTO inscritoCurso
 FROM EDU_MATRI_CURSO EMC,
 EDU_HISTO_CURSO EHC
 WHERE EMC.OID_CURS = EHC.MCUR_OID_CURS
 AND EHC.CLIE_OID_CLIE = pdOidCliente
 AND EMC.TICU_OID_TIPO_CURS = r_cursosExigidos.Ticu_Oid_Tipo_Curs
 AND EHC.FEC_ASIS IS NOT NULL;

 inscritoAptaCurso:=0;
 SELECT COUNT(1)
 INTO inscritoAptaCurso
 FROM EDU_MATRI_CURSO EMC,
 EDU_APTAS_CURSO EAC
 WHERE EMC.OID_CURS = EAC.MCUR_OID_CURS
 AND EAC.CLIE_OID_CLIE = pdOidCliente
 AND EMC.TICU_OID_TIPO_CURS = r_cursosExigidos.Ticu_Oid_Tipo_Curs
 AND EAC.FEC_ASIS IS NOT NULL;
 IF (inscritoCurso=0 AND inscritoAptaCurso=0) THEN
 BEGIN
 retorno := FALSE;
 EXIT;
 END;
 END IF;
 END;
 END LOOP;
 CLOSE cursosExigidos;
 RETURN retorno;
 EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := SUBSTR(SQLERRM,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_ASIS_TIPOS_CURS_EXI: '||ls_sqlerrm);

END INC_FN_ASIS_TIPOS_CURS_EXI;

/***************************************************************************
Descripcion : Realiza la validacion del indicador de Actividad
 de concursos
Fecha Creacion : 15/02/2007
 pdOidCliente : Oid del Cliente
 psOidParamGeneral : Oid del Concurso
 psPeriodoInicio : Periodo de Inicio de Evaluacion
 psPeriodoFin : Periodo de Final de Evaluacion

Autor : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION INC_FN_ACTIV_CONSE (pdOidCliente NUMBER,
 psOidParamGeneral NUMBER,
 psPeriodoInicio VARCHAR2,
 psPeriodoFin VARCHAR2)
RETURN BOOLEAN IS
CURSOR cursosPedidosCliente IS
 SELECT INC_SOLIC_CONCU_PUNTA.*
 FROM INC_SOLIC_CONCU_PUNTA
 WHERE INC_SOLIC_CONCU_PUNTA.CLIE_OID_CLIE = pdOidCliente
 AND INC_SOLIC_CONCU_PUNTA.COPA_OID_PARA_GRAL = psOidParamGeneral
 AND psPeriodoInicio <= Gen_Pkg_Gener.GEN_FN_DEVUELVE_DES_PERIO(INC_SOLIC_CONCU_PUNTA.PERD_OID_PERI)
 AND psPeriodoFin >= Gen_Pkg_Gener.GEN_FN_DEVUELVE_DES_PERIO(INC_SOLIC_CONCU_PUNTA.PERD_OID_PERI)
 AND INC_SOLIC_CONCU_PUNTA.IND_ANUL = 0
 AND INC_SOLIC_CONCU_PUNTA.IMP_MONT > 0;

r_pedidoCliente cursosPedidosCliente%ROWTYPE;

minimoAnterior VARCHAR2(6);
retorno BOOLEAN;
BEGIN

retorno := TRUE;

 OPEN cursosPedidosCliente;
 LOOP
 FETCH cursosPedidosCliente INTO r_pedidoCliente;
 EXIT WHEN cursosPedidosCliente%NOTFOUND;
 BEGIN
 minimoAnterior:='';
 SELECT MAX(Gen_Pkg_Gener.GEN_FN_DEVUELVE_DES_PERIO(INC_SOLIC_CONCU_PUNTA.PERD_OID_PERI))
 INTO minimoAnterior
 FROM INC_SOLIC_CONCU_PUNTA
 WHERE INC_SOLIC_CONCU_PUNTA.COPA_OID_PARA_GRAL = psOidParamGeneral
 AND INC_SOLIC_CONCU_PUNTA.CLIE_OID_CLIE = pdOidCliente
 AND psPeriodoInicio <= Gen_Pkg_Gener.GEN_FN_DEVUELVE_DES_PERIO(INC_SOLIC_CONCU_PUNTA.PERD_OID_PERI)
 AND psPeriodoFin >= Gen_Pkg_Gener.GEN_FN_DEVUELVE_DES_PERIO(INC_SOLIC_CONCU_PUNTA.PERD_OID_PERI)
 AND Gen_Pkg_Gener.GEN_FN_DEVUELVE_DES_PERIO(r_pedidoCliente.PERD_OID_PERI) > Gen_Pkg_Gener.GEN_FN_DEVUELVE_DES_PERIO(INC_SOLIC_CONCU_PUNTA.PERD_OID_PERI)
 AND INC_SOLIC_CONCU_PUNTA.IND_ANUL = 0
 AND INC_SOLIC_CONCU_PUNTA.IMP_MONT > 0;

 IF ((minimoAnterior IS NULL) OR (minimoAnterior ='')) THEN
 retorno := TRUE;
 ELSE
 IF (2 > TO_NUMBER(Gen_Pkg_Gener.GEN_FN_DEVUELVE_DES_PERIO(r_pedidoCliente.PERD_OID_PERI)) - TO_NUMBER(minimoAnterior) ) THEN
 BEGIN
 retorno := FALSE;
 EXIT;
 END;
 END IF;
 END IF;
 END;
 END LOOP;
 CLOSE cursosPedidosCliente;
 RETURN retorno;
 EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := SUBSTR(SQLERRM,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_ACTIV_CONSE: '||ls_sqlerrm);

END INC_FN_ACTIV_CONSE;


/***************************************************************************
Descripcion : Realiza la validacion del indicador de Constancia
 de concursos
Fecha Creacion : 26/06/2007
 pdOidCliente : Oid del Cliente
 psCodPeriodoInicio : codigo de Periodo Inicial
 psCodPeriodoFina : Codigo de Periodo Final
 psOidPais : Oid de Pais
 psOidMarca : Oid de Marca
 psOidCanal : Oid de Canal

Autor : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION INC_FN_CONS_CONSEC (pdOidCliente NUMBER,
 psCodPeriodoInicio VARCHAR2,
 psCodPeriodoFina VARCHAR2,
 psOidPais NUMBER,
 psOidMarca NUMBER,
 psOidCanal NUMBER)
RETURN BOOLEAN IS
/*
CURSOR cursosPeriodos IS
SELECT oid_peri
FROM TABLE(Ven_Pkg_Repor.VEN_FN_OBTIE_LISTA_OID_PERI(psCodPeriodoInicio ,psCodPeriodoFina ,psOidPais ,psOidMarca , psOidCanal) );

r_periodo cursosPeriodos%ROWTYPE;
*/
existePeriodo NUMBER;
retorno BOOLEAN;
BEGIN
retorno := TRUE;
/*
 OPEN cursosPeriodos;
 LOOP
 FETCH cursosPeriodos INTO r_periodo;
 EXIT WHEN cursosPeriodos%NOTFOUND;
 BEGIN
 existePeriodo:=0;
 BEGIN
 SELECT COUNT(1)
 INTO existePeriodo
 FROM PED_SOLIC_CABEC_ACUM2
 WHERE PED_SOLIC_CABEC_ACUM2.CLIE_OID_CLIE = pdOidCliente
 AND PED_SOLIC_CABEC_ACUM2.PERD_OID_PERI= r_periodo.OID_peri
 AND PED_SOLIC_CABEC_ACUM2.VAL_CANT_PEDI > 0;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 existePeriodo:=0;
 END;
 IF ((existePeriodo <= 0)) THEN
 retorno := FALSE;
 END IF;
 END;
 END LOOP;
 CLOSE cursosPeriodos;
 */
 RETURN retorno;
 EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := SUBSTR(SQLERRM,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_CONS_CONSEC: '||ls_sqlerrm);

END INC_FN_CONS_CONSEC;
/***************************************************************************
Descripcion : Funcion que la utilizaremos para asignar un nivel determinado
 de un cliente para la evaluacion
Fecha Creacion : 17/05/2007
 pdOidCliente : Oid del Cliente
 psOidParamGeneral : Oid del Concurso

Autor : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION INC_FN_OBTIE_NIVEL_CLIEN (pdOidCliente NUMBER,
 psOidParamGeneral NUMBER)
RETURN NUMBER IS
lsValorRetorno NUMBER;
BEGIN
 lsValorRetorno:=0;
 SELECT MAX(NUM_NIVE)
 INTO lsValorRetorno
 FROM INC_REPOR_PROY_CONC C
 WHERE C.PUNT_NIVE <= (SELECT SUM(T.NUM_PUNT)
 FROM INC_CUENT_CORRI_PUNTO T
 WHERE T.COPA_OID_PARA_GRAL = psOidParamGeneral
 AND T.CLIE_OID_CLIE = pdOidCliente
 AND T.VAL_DESC <> 'Entrega de Premio') ;
 RETURN lsValorRetorno;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := SUBSTR(SQLERRM,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_OBTIE_NIVEL_CLIEN: '||ls_sqlerrm);
END INC_FN_OBTIE_NIVEL_CLIEN;
/***************************************************************************
Descripcion : Funcion que la utilizaremos para determinar que cliente
 tiene un puntaje minimo al menor puntaje de los niveles
 ya ingresados en INC_REPOR_PROY_CONC
Fecha Creacion : 17/05/2007
 pdOidCliente : Oid del Cliente
 psOidParamGeneral : Oid del Concurso

Autor : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION INC_FN_OBTIE_PUNMI_CLIEN (pdOidCliente NUMBER,
 psOidParamGeneral NUMBER)
RETURN NUMBER IS
lsValorRetorno NUMBER;
lsMinPuntajeNivel NUMBER;
BEGIN
 lsValorRetorno:=0;
 SELECT SUM(T.NUM_PUNT)
 INTO lsValorRetorno
 FROM INC_CUENT_CORRI_PUNTO T
 WHERE T.COPA_OID_PARA_GRAL = psOidParamGeneral
 AND T.CLIE_OID_CLIE = pdOidCliente
 AND T.VAL_DESC <> 'Entrega de Premio' ;

 SELECT MIN(PUNT_NIVE)
 INTO lsMinPuntajeNivel
 FROM INC_REPOR_PROY_CONC C
 WHERE C.OID_PARA_GRAL = psOidParamGeneral;

 IF (lsMinPuntajeNivel> lsValorRetorno) THEN
 RETURN 0;
 ELSE
 RETURN 1;
 END IF;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := SUBSTR(SQLERRM,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_OBTIE_PUNMI_CLIEN: '||ls_sqlerrm);
END INC_FN_OBTIE_PUNMI_CLIEN;
/***************************************************************************
Descripcion : Funcion que la utilizaremos para obtener el puntaje del clientee
 de la tabla INC_CUENT_CORRI_PUNTO
Fecha Creacion : 17/05/2007
 pdOidCliente : Oid del Cliente
 psOidParamGeneral : Oid del Concurso

Autor : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION INC_FN_OBTIE_PUNTA_CLIEN (pdOidCliente NUMBER,
 psOidParamGeneral NUMBER)
RETURN NUMBER IS
lsValorRetorno NUMBER;
BEGIN
 lsValorRetorno:=0;
 SELECT SUM(T.NUM_PUNT)
 INTO lsValorRetorno
 FROM INC_CUENT_CORRI_PUNTO T
 WHERE T.COPA_OID_PARA_GRAL = psOidParamGeneral
 AND T.CLIE_OID_CLIE = pdOidCliente
 AND T.VAL_DESC <> 'Entrega de Premio' ;
 RETURN lsValorRetorno;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := SUBSTR(SQLERRM,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_OBTIE_PUNTA_CLIEN: '||ls_sqlerrm);
END INC_FN_OBTIE_PUNTA_CLIEN;

/***************************************************************************
Descripcion : Funcion que la utilizaremos para asignar la meta minima
Fecha Creacion : 19/01/2010
 psOidClasClienParti : Oid de la Clasificacion de Clientes Participantes

Autor : Alexander Villavicencio Adán
***************************************************************************/
FUNCTION INC_FN_META_MINIM (psOidClasClienParti NUMBER) RETURN NUMBER IS
       lsValorRetorno NUMBER:=0;
BEGIN
     SELECT NVL(max(imrc.imp_mont_mini),0) INTO lsValorRetorno
      FROM INC_CLASI_PARTI_CALIF icpc, INC_MONTO_MINIM_RANGO_CONSU imrc
     WHERE icpc.oid_clas_part_cali = psOidClasClienParti          AND
           icpc.oid_clas_part_cali = imrc.clpc_oid_clas_part_cali ;
 RETURN lsValorRetorno;
/*EXCEPTION
 WHEN NO_DATA_FOUND THEN
      RETURN 0;
 WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_META_MINIMA: '||ls_sqlerrm);
     */
END INC_FN_META_MINIM;


/***************************************************************************
Descripcion : Obtiene las cantidades de premios entregados, asi como
 el monto base imponible, igv y monto total
Fecha Creacion : 12/12/2008
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_OBTIE_PREMI_ENTRE
 (psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodCanal VARCHAR2,
 psFechaInicioFacturacion VARCHAR2,
 psFechaFinFacturacion VARCHAR2,
 psCampoFiltro VARCHAR2,
 psCodConcursos VARCHAR2,
 psCodProgramas VARCHAR2,
 psCodSAP VARCHAR2)
IS
 lnIdPais SEG_PAIS.OID_PAIS%TYPE;
 lnIdMarca SEG_MARCA.OID_MARC%TYPE;
 lnIdCanal SEG_CANAL.OID_CANA%TYPE;
 lnTasaImpuesto PED_TASA_IMPUE.VAL_TASA_IMPU%TYPE;

 lsCodigoSAP VARCHAR2(15);
 lnProgPrivilege NUMBER;
 lsSentenciaSQL VARCHAR2(2000);
BEGIN

 --RECUPERAMOS EL OID DEL PAIS, MARCA Y CANAL
 lnIdPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);

 --BORRAMOS LAS TABLAS TEMPORALES
 EXECUTE IMMEDIATE 'TRUNCATE TABLE INC_TMP_PREMI_ENTRE';
 EXECUTE IMMEDIATE 'TRUNCATE TABLE INC_TMP_PREMI_ENTRE_CONCU';
 EXECUTE IMMEDIATE 'TRUNCATE TABLE INC_TMP_PREMI_ENTRE_PROGR';

 --OBTENEMOS LOS PROGRAMAS A MOSTRARSE
 IF((psCampoFiltro IS NULL) OR (psCampoFiltro = CAMPO_FILTRO_PROGRAMA) OR (psCampoFiltro = CAMPO_FILTRO_CODIGO_SAP)) THEN
 lsSentenciaSQL := 'INSERT INTO INC_TMP_PREMI_ENTRE_PROGR ';
 lsSentenciaSQL := lsSentenciaSQL || 'SELECT OID_TIPO_PROG ';
 lsSentenciaSQL := lsSentenciaSQL || 'FROM (SELECT ctp.oid_tipo_prog ';
 lsSentenciaSQL := lsSentenciaSQL || ' FROM inc_concu_tipo_prog ctp ';
 lsSentenciaSQL := lsSentenciaSQL || ' UNION ';
 lsSentenciaSQL := lsSentenciaSQL || ' SELECT 9999 AS oid_tipo_prog ';
 lsSentenciaSQL := lsSentenciaSQL || ' FROM DUAL) ';

 IF ((psCampoFiltro = CAMPO_FILTRO_PROGRAMA) AND (psCodProgramas IS NOT NULL)) THEN
 lsSentenciaSQL := lsSentenciaSQL || 'WHERE OID_TIPO_PROG ' || psCodProgramas;
 END IF;
 ELSE
 IF(psCampoFiltro = CAMPO_FILTRO_CONCURSO) THEN
 lsSentenciaSQL := 'INSERT INTO INC_TMP_PREMI_ENTRE_PROGR ';
 lsSentenciaSQL := lsSentenciaSQL || 'SELECT ctp.oid_tipo_prog ';
 lsSentenciaSQL := lsSentenciaSQL || ' FROM inc_concu_tipo_prog ctp ';
 END IF;
 END IF;
 --OBTENEMOS LOS PROGRAMAS A CONSULTAR
 EXECUTE IMMEDIATE lsSentenciaSQL;


 --OBTENEMOS LOS CONCURSOS A MOSTRARSE
 lsSentenciaSQL := 'INSERT INTO INC_TMP_PREMI_ENTRE_CONCU ';
 lsSentenciaSQL := lsSentenciaSQL || 'SELECT cpg.oid_para_gral ';
 lsSentenciaSQL := lsSentenciaSQL || 'FROM INC_CONCU_PARAM_GENER cpg, ';
 lsSentenciaSQL := lsSentenciaSQL || ' INC_VERSI_CONCU VIG, ';
 lsSentenciaSQL := lsSentenciaSQL || ' INC_CONCU_TIPO_PROG ctp, ';
 lsSentenciaSQL := lsSentenciaSQL || ' INC_TMP_PREMI_ENTRE_PROGR pro ';
 lsSentenciaSQL := lsSentenciaSQL || 'WHERE cpg.pais_oid_pais = ' || TO_CHAR(lnIdPais);
 lsSentenciaSQL := lsSentenciaSQL || ' AND cpg.marc_oid_marc = ' || TO_CHAR(lnIdMarca);
 lsSentenciaSQL := lsSentenciaSQL || ' AND cpg.cana_oid_cana = ' || TO_CHAR(lnIdCanal);
 lsSentenciaSQL := lsSentenciaSQL || ' AND cpg.oid_para_gral = vig.copa_oid_para_gral ';
 lsSentenciaSQL := lsSentenciaSQL || ' AND cpg.ictp_oid_tipo_prog = ctp.oid_tipo_prog ';
 lsSentenciaSQL := lsSentenciaSQL || ' AND pro.oid_tipo_prog = ctp.oid_tipo_prog ';

 IF(psCampoFiltro = CAMPO_FILTRO_CONCURSO) THEN
 IF (psCodConcursos IS NOT NULL) THEN
 lsSentenciaSQL := lsSentenciaSQL || ' AND cpg.oid_para_gral ' || psCodConcursos;
 END IF;
 END IF;

 --OBTENEMOS LOS CONCURSOS A CONSULTAR
 EXECUTE IMMEDIATE lsSentenciaSQL;

 lsCodigoSAP := NULL;
 IF(psCampoFiltro = CAMPO_FILTRO_CODIGO_SAP) THEN
 lsCodigoSAP := psCodSAP;
 END IF;

 --RECUPERAMOS EL PORCENTAJE DEL IMPUESTO IGV o IVA
 SELECT tas.VAL_TASA_IMPU
 INTO lnTasaImpuesto
 FROM PED_IMPUE_GENER imp, SEG_SUBAC acc, PED_TASA_IMPUE tas
 WHERE imp.PAIS_OID_PAIS = lnIdPais
 AND imp.TAIM_OID_TASA_IMPU = tas.OID_TASA_IMPU
 AND imp.SBAC_OID_SBAC = acc.OID_SBAC
 AND acc.COD_SBAC = '000';

 --RECUPERAMOS LOS CONCURSOS, CON LA CANTIDAD DE PREMIOS, BASE IMPONIBLE, IGV y MONTO TOTAL
 INSERT INTO INC_TMP_PREMI_ENTRE
 (COD_PAIS, COD_CONC, NOM_CONC, NOM_PROG,
 COD_SAP, NOM_PREM, COD_ANIO, COD_PERI_INIC,
 COD_PERI_FINA, COD_PERI_DESP, FEC_FACT, NUM_PREM,
 VAL_BASE_IMPO, VAL_IGV, VAL_IMPO_TOTA)
 SELECT psCodPais,
 con.num_conc,
 con.val_nomb,
 con.des_tipo_prog,
 con.cod_sap,
 con.des_producto,
 SUBSTR(Gen_Pkg_Gener.gen_fn_devuelve_des_perio(prem.perd_oid_peri),0,4) Anio ,
 con.perDesde,
 con.perHasta,
 Gen_Pkg_Gener.gen_fn_devuelve_des_perio(prem.perd_oid_peri) perDespacho,
 prem.fec_fact,
 prem.premios_entregados,
 prem.base_imponible,
 prem.impuesto,
 prem.importe_total
 FROM
 ( -- DATOS DE LOS CONCURSOS --
 SELECT tabla.oid_para_gral,
 tabla.num_conc,
 tabla.val_nomb,
 tabla.oid_tipo_prog,
 tabla.des_tipo_prog,
 Gen_Pkg_Gener.gen_fn_devuelve_des_perio(tabla.perDesde) perDesde,
 Gen_Pkg_Gener.gen_fn_devuelve_des_perio(tabla.perHasta) perHasta,
 tabla.oid_prod,
 tabla.cod_sap,
 tabla.des_producto
 FROM
 ( -- Todos los concursos --
 SELECT cpg.oid_para_gral,
 cpg.num_conc,
 cpg.val_nomb,
 ctp.oid_tipo_prog,
 ctp.des_tipo_prog,
 cpg.perd_oid_peri_desd perDesde,
 cpg.perd_oid_peri_hast perHasta,
 pgp.perd_oid_peri perDespacho,
 pnp.num_nive,
 lpa.num_prem,
 p.oid_prod,
 p.cod_sap,
 pq_apl_aux.Valor_Gen_I18n_Sicc( 1, p.oid_prod, 'MAE_PRODU') AS des_producto
 FROM
 INC_CONCU_PARAM_GENER cpg,
 INC_TMP_PREMI_ENTRE_CONCU vig,
 INC_CONCU_TIPO_PROG ctp,
 INC_PARAM_GENER_PREMI pgp,
 INC_PARAM_NIVEL_PREMI pnp,
 INC_PREMI_ARTIC pa,
 INC_LOTE_PREMI_ARTIC lpa,
 INC_ARTIC_LOTE al,
 MAE_PRODU p
 WHERE
 cpg.oid_para_gral = vig.oid_para_gral
 AND cpg.ictp_oid_tipo_prog = ctp.oid_tipo_prog
 AND cpg.oid_para_gral = pgp.copa_oid_para_gral
 AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
 AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
 AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
 AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
 AND al.prod_oid_prod = p.oid_prod
 AND (lsCodigoSAP IS NULL OR p.cod_sap = lsCodigoSAP)
 ) tabla
 GROUP BY
 tabla.oid_para_gral,
 tabla.num_conc,
 tabla.val_nomb,
 tabla.oid_tipo_prog,
 tabla.des_tipo_prog,
 tabla.perDesde,
 tabla.perHasta,
 tabla.oid_prod,
 tabla.cod_sap,
 tabla.des_producto
 ) con,
 ( -- DATOS DE LOS PREMIOS DE INCENTIVOS --
 SELECT tabla.perd_oid_peri,
 tabla.copa_oid_para_gene,
 tabla.prod_oid_prod,
 tabla.fec_fact,
 SUM(tabla.premios_entregados) AS premios_entregados,
 ROUND(SUM(tabla.importe_total - tabla.impuesto),2) AS base_imponible,
 ROUND(SUM(tabla.impuesto),2) AS impuesto,
 SUM(tabla.importe_total) AS importe_total
 FROM
 ( -- Totales sumarizados por Solicitudes de Incentivos (no considera reclamos) --
 SELECT
 sc.perd_oid_peri,
 TO_CHAR(sc.fec_fact,'DD/MM/YYYY') AS fec_fact,
 sc.copa_oid_para_gene,
 sc.num_prem,
 sp.prod_oid_prod,
 SUM(sp.num_unid_aten) AS premios_entregados,
 SUM(sp.val_prec_cont_unit_loca*sp.num_unid_aten) AS importe_total,
 (lnTasaImpuesto/(100+lnTasaImpuesto)) * SUM(sp.val_prec_cont_unit_loca*sp.num_unid_aten) AS impuesto
 FROM
 (SELECT pp.* FROM PED_SOLIC_CABEC pp, INC_CONCU_PARAM_GENER cpg, INC_TMP_PREMI_ENTRE_CONCU vig
 WHERE cpg.oid_para_gral = vig.oid_para_gral
 AND cpg.oid_para_gral = pp.copa_oid_para_gene
 AND pp.fec_fact IS NOT NULL
 AND pp.grpr_oid_grup_proc = 5
 AND pp.fec_fact >= TO_DATE(psFechaInicioFacturacion, 'DD/MM/YYYY')
 AND pp.fec_fact < TO_DATE(psFechaFinFacturacion, 'DD/MM/YYYY') + 1
 AND pp.pais_oid_pais = lnIdPais) sc,
 PED_SOLIC_POSIC sp,
 MAE_PRODU p,
 PED_SOLIC_CABEC sc2
 WHERE
 sc.oid_soli_cabe = sp.soca_oid_soli_cabe
 AND sp.prod_oid_prod = p.oid_prod
 AND sp.num_unid_aten <> 0 -- mayor o menor a 0 -
 AND sc.soca_oid_soli_cabe = sc2.oid_soli_cabe
 AND sc2.esso_oid_esta_soli <> 4
 AND (lsCodigoSAP IS NULL OR p.cod_sap = lsCodigoSAP)
 GROUP BY
 sc.perd_oid_peri,
 sc.fec_fact,
 sc.copa_oid_para_gene,
 sc.num_prem,
 sp.prod_oid_prod
 ) tabla
 GROUP BY
 tabla.perd_oid_peri,
 tabla.copa_oid_para_gene,
 tabla.prod_oid_prod,
 tabla.fec_fact
 ) prem
 WHERE con.oid_para_gral = prem.copa_oid_para_gene
 AND con.oid_prod = prem.prod_oid_prod;


 --VERIFICAMOS SI SE VA A CONSULTAR EL PROGRAMA DE PRIVILEGE
 SELECT COUNT(1)
 INTO lnProgPrivilege
 FROM INC_TMP_PREMI_ENTRE_PROGR
 WHERE oid_tipo_prog = 9999;

 IF(lnProgPrivilege > 0) THEN

 -- DATOS DE LOS PREMIOS PRIVILEGE --
 INSERT INTO INC_TMP_PREMI_ENTRE
 (COD_PAIS, COD_CONC, NOM_CONC, NOM_PROG,
 COD_SAP, NOM_PREM, COD_ANIO, COD_PERI_INIC,
 COD_PERI_FINA, COD_PERI_DESP, FEC_FACT, NUM_PREM,
 VAL_BASE_IMPO, VAL_IGV, VAL_IMPO_TOTA)
 SELECT psCodPais,
 '001' num_conc,
 'Programa Club Privilege' val_nomb,
 'PRIVILEGE' des_tipo_prog,
 tabla.cod_sap,
 tabla.des_producto,
 SUBSTR(Gen_Pkg_Gener.gen_fn_devuelve_des_perio(tabla.perd_oid_peri),0,4) Anio,
 Gen_Pkg_Gener.gen_fn_devuelve_des_perio(tabla.perd_oid_peri) perDesde,
 Gen_Pkg_Gener.gen_fn_devuelve_des_perio(tabla.perd_oid_peri) perHasta,
 Gen_Pkg_Gener.gen_fn_devuelve_des_perio(tabla.perd_oid_peri) perDespacho,
 tabla.fec_fact,
 SUM(tabla.premios_entregados) AS premios_entregados,
 ROUND(SUM(tabla.importe_total - tabla.impuesto),2) AS base_imponible,
 ROUND(SUM(tabla.impuesto),2) AS impuesto,
 SUM(tabla.importe_total) AS importe_total

 FROM
 ( -- Totales sumarizados por Solicitudes de Privilege --
 SELECT sc.perd_oid_peri,
 TO_CHAR(sc.fec_fact,'DD/MM/YYYY') AS fec_fact,
 sp.prod_oid_prod,
 p.cod_sap,
 pq_apl_aux.Valor_Gen_I18n_Sicc( 1, sp.prod_oid_prod, 'MAE_PRODU') AS des_producto,
 SUM(sp.num_unid_aten) AS premios_entregados,
 SUM(sp.val_prec_cont_unit_loca*sp.num_unid_aten) AS importe_total,
 (lnTasaImpuesto/(100+lnTasaImpuesto)) * SUM(sp.val_prec_cont_unit_loca*sp.num_unid_aten) AS impuesto
 FROM
 PED_SOLIC_CABEC sc,
 PED_SOLIC_POSIC sp,
 MAE_PRODU p,
 PED_SOLIC_CABEC sc2
 WHERE
 sc.pais_oid_pais = lnIdPais
 AND sc.oid_soli_cabe = sp.soca_oid_soli_cabe
 AND sp.prod_oid_prod = p.oid_prod
 AND sc.tspa_oid_tipo_soli_pais IN ( SELECT oid_tipo_soli_pais FROM ped_tipo_solic_pais
 WHERE tsol_oid_tipo_soli IN (SELECT oid_tipo_soli FROM ped_tipo_solic
 WHERE cod_tipo_soli IN ('SPV1','SPV2')) )
 AND sc.fec_fact IS NOT NULL
 AND sp.num_unid_aten <> 0 -- que existan unidades --
 AND sc.grpr_oid_grup_proc = 5
 AND sc.soca_oid_soli_cabe = sc2.oid_soli_cabe
 AND sc2.esso_oid_esta_soli <> 4
 AND sc.fec_fact >= TO_DATE(psFechaInicioFacturacion, 'DD/MM/YYYY')
 AND sc.fec_fact < TO_DATE(psFechaFinFacturacion, 'DD/MM/YYYY') + 1
 AND (lsCodigoSAP IS NULL OR p.cod_sap = lsCodigoSAP)
 GROUP BY
 sc.perd_oid_peri,
 sc.fec_fact,
 sp.prod_oid_prod,
 p.cod_sap
 ) tabla
 GROUP BY
 tabla.perd_oid_peri,
 tabla.prod_oid_prod,
 tabla.fec_fact,
 tabla.cod_sap,
 tabla.des_producto;

 END IF;

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := SUBSTR(SQLERRM,1,150);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_OBTIE_PREMI_ENTRE: '||ls_sqlerrm);

END INC_PR_OBTIE_PREMI_ENTRE;

/***************************************************************************
Descripcion : Obtiene las cantidades de devoluciones, anulaciones,
 trueques, canjes, que se han generados por los premios
 entregados a las consultoras
Fecha Creacion : 13/04/2008
Autor : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_OBTIE_INDIC_GESTI_INCEN
 (psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodCanal VARCHAR2,
 psCampoFiltro VARCHAR2,
 psCodConcursos VARCHAR2,
 psCodPeriodoInicio VARCHAR2,
 psCodPeriodoFin VARCHAR2)
IS
 lnIdPais SEG_PAIS.OID_PAIS%TYPE;
 lnIdMarca SEG_MARCA.OID_MARC%TYPE;
 lnIdCanal SEG_CANAL.OID_CANA%TYPE;

 lsSentenciaSQL VARCHAR2(2000);
BEGIN

 --RECUPERAMOS EL OID DEL PAIS, MARCA Y CANAL
 lnIdPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);

 --BORRAMOS LAS TABLAS TEMPORALES
 EXECUTE IMMEDIATE 'TRUNCATE TABLE INC_TMP_INDIC_GESTI_INCEN';
 EXECUTE IMMEDIATE 'TRUNCATE TABLE INC_TMP_CONCU_VIGEN';

 --OBTENEMOS LOS CONCURSOS A MOSTRARSE
 lsSentenciaSQL := 'INSERT INTO INC_TMP_CONCU_VIGEN ';
 lsSentenciaSQL := lsSentenciaSQL || 'SELECT cpg.oid_para_gral ';
 lsSentenciaSQL := lsSentenciaSQL || 'FROM inc_concu_param_gener cpg, ';
 lsSentenciaSQL := lsSentenciaSQL || ' inc_versi_concu vig, ';
 lsSentenciaSQL := lsSentenciaSQL || ' cra_perio crai, ';
 lsSentenciaSQL := lsSentenciaSQL || ' cra_perio craf, ';
 lsSentenciaSQL := lsSentenciaSQL || ' seg_perio_corpo peri, ';
 lsSentenciaSQL := lsSentenciaSQL || ' seg_perio_corpo perf ';
 lsSentenciaSQL := lsSentenciaSQL || 'WHERE cpg.pais_oid_pais = ' || TO_CHAR(lnIdPais);
 lsSentenciaSQL := lsSentenciaSQL || ' AND cpg.marc_oid_marc = ' || TO_CHAR(lnIdMarca);
 lsSentenciaSQL := lsSentenciaSQL || ' AND cpg.cana_oid_cana = ' || TO_CHAR(lnIdCanal);
 lsSentenciaSQL := lsSentenciaSQL || ' AND cpg.oid_para_gral = vig.copa_oid_para_gral ';
 lsSentenciaSQL := lsSentenciaSQL || ' AND crai.oid_peri = cpg.perd_oid_peri_desd ';
 lsSentenciaSQL := lsSentenciaSQL || ' AND peri.oid_peri = crai.peri_oid_peri ';
 lsSentenciaSQL := lsSentenciaSQL || ' AND craf.oid_peri = cpg.perd_oid_peri_hast ';
 lsSentenciaSQL := lsSentenciaSQL || ' AND perf.oid_peri = craf.peri_oid_peri ';
 lsSentenciaSQL := lsSentenciaSQL || ' AND ( (cpg.ind_acti = 1 AND vig.vico_oid_vige_conc = 1) OR ';
 lsSentenciaSQL := lsSentenciaSQL || ' (cpg.ind_acti = 0 AND vig.vico_oid_vige_conc = 6) ) ';

 --SE FILTRA LOS CONCURSOS POR NUMERO DE COONCURSOS o POR RANGO DE PERIODOS
 IF(psCampoFiltro = CAMPO_FILTRO_CONCURSO) THEN
 lsSentenciaSQL := lsSentenciaSQL || ' AND cpg.oid_para_gral ' || psCodConcursos;
 ELSE
 lsSentenciaSQL := lsSentenciaSQL || ' AND peri.cod_peri >= ' || psCodPeriodoInicio;
 lsSentenciaSQL := lsSentenciaSQL || ' AND perf.cod_peri <= ' || psCodPeriodoFin;
 END IF;

 --OBTENEMOS LOS CONCURSOS A BUSCAR
 EXECUTE IMMEDIATE lsSentenciaSQL;

 --RECUPERAMOS LOS CAMBIOS, TRUEQUES, DEVOLUCIONES y ANULACIONES
 INSERT INTO INC_TMP_INDIC_GESTI_INCEN
 (COD_PAIS, COD_PERI_INIC, COD_PERI_FINA, COD_CONC, NOM_CONC,
 COD_SAP, NOM_PREM, NUM_GANA, NUM_PREM,
 NUM_CAMB, NUM_DEVO, NUM_TRUE,
 NUM_FALT_PREM, NUM_MERC_PERD, NUM_SERV_PREM,
 NUM_CONS_ATEN, NUM_CONS_ATEN_FUER, VAL_PORC_CAMB, VAL_PORC_DEVO,
 VAL_PORC_TRUE, VAL_PORC_TOTA_CDRS, VAL_PORC_ENVI_DESP, VAL_PORC_ENVI_DESP_FUER,
 VAL_PORC_FALT_PREM, VAL_PORC_MERC_PERD, VAL_PORC_SERV_PREM)
 SELECT psCodPais,
 Gen_Pkg_Gener.gen_fn_devuelve_des_perio(con.perd_oid_peri_desd) campIni,
 Gen_Pkg_Gener.gen_fn_devuelve_des_perio(con.perd_oid_peri_hast) campFin,
 con.num_conc,
 con.val_nomb,
 con.cod_sap,
 con.des_producto,
 NVL(SUM(gan.ganadoras),0) AS ganadoras,
 NVL(SUM(prem.numeroPremios),0) AS numpremios,
 NVL(SUM(cdrs.canjes),0) AS cambios,
 NVL(SUM(cdrs.devoluciones),0) AS devoluciones,
 NVL(SUM(cdrs.trueques),0) AS trueques,
 NVL(SUM(cdrs.faltantes),0) AS faltantes,
 NVL(SUM(cdrs.nota_mercaderia_perdida),0) AS nota_mercaderia_perdida,
 NVL(SUM(cdrs.servicio_premio),0) AS servicio_premio,
 NVL(SUM(consdesp.ConsAtendCampDesp),0) AS consatencampdesp,
 NVL(SUM(consdesp.ConsFueraCampDesp),0) AS consfuercampdesp,
 0,0,0,0,0,0,0,0,0
 FROM
 ( -- Relacion de concursos, niveles, premios, productos --
 SELECT cpg.oid_para_gral,
 cpg.num_conc,
 cpg.val_nomb,
 cpg.ind_acti,
 cpg.perd_oid_peri_desd,
 cpg.perd_oid_peri_hast,
 pgp.perd_oid_peri,
 pnp.num_nive,
 lpa.num_prem,
 al.prod_oid_prod,
 p.cod_sap,
 pq_apl_aux.Valor_Gen_I18n_Sicc( 1, p.oid_prod, 'MAE_PRODU') AS des_producto
 FROM
 INC_CONCU_PARAM_GENER cpg,
 INC_TMP_CONCU_VIGEN vig,
 INC_PARAM_GENER_PREMI pgp,
 INC_PARAM_NIVEL_PREMI pnp,
 INC_PREMI_ARTIC pa,
 INC_LOTE_PREMI_ARTIC lpa,
 INC_ARTIC_LOTE al,
 MAE_PRODU p
 WHERE
 cpg.oid_para_gral = vig.oid_para_gral
 AND cpg.oid_para_gral = pgp.copa_oid_para_gral
 AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
 AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
 AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
 AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
 AND p.oid_prod = al.prod_oid_prod
 ) con,
 ( -- Relacion de ganadoras por concurso y nivel --
 SELECT
 cpg.oid_para_gral,
 cpg.num_conc,
 pnp.num_nive,
 sc.num_prem,
 sp.prod_oid_prod,
 COUNT(DISTINCT g.clie_oid_clie) AS ganadoras
 FROM
 INC_CONCU_PARAM_GENER cpg,
 INC_TMP_CONCU_VIGEN vig,
 INC_PARAM_GENER_PREMI pgp,
 INC_PARAM_NIVEL_PREMI pnp,
 INC_GANAD g,
 ped_solic_cabec sc,
 ped_solic_posic sp
 WHERE
 cpg.oid_para_gral = vig.oid_para_gral
 AND cpg.oid_para_gral = pgp.copa_oid_para_gral
 AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
 AND pnp.oid_para_nive_prem = g.panp_oid_para_nive_prem
 AND g.ind_desp = 1
 AND g.soca_oid_soli_cabe = sc.oid_soli_cabe
 AND cpg.oid_para_gral = sc.copa_oid_para_gene
 AND cpg.pais_oid_pais = sc.pais_oid_pais
 AND sc.oid_soli_cabe = sp.soca_oid_soli_cabe
 AND sc.fec_fact IS NOT NULL
 GROUP BY
 cpg.oid_para_gral,
 cpg.num_conc,
 pnp.num_nive,
 sc.num_prem,
 sp.prod_oid_prod
 ) gan ,
 ( -- Numero de Premios Despachados por concurso --
 SELECT x.oid_para_gral,
 -- Nivel --
 (SELECT pnp.num_nive
 FROM inc_param_gener_premi pgp,
 inc_param_nivel_premi pnp,
 inc_premi_artic pa,
 inc_lote_premi_artic lpa,
 inc_artic_lote al
 WHERE pgp.copa_oid_para_gral = x.oid_para_gral
 AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
 AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
 AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
 AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
 AND al.prod_oid_prod = x.prod_oid_prod
 AND lpa.num_prem = x.num_prem) num_nive,
 x.num_prem,
 x.prod_oid_prod,
 x.cod_sap,
 NVL(SUM (x.num_unid_aten),0) AS numeroPremios
 FROM (SELECT cpgx.oid_para_gral,
 cpgx.num_conc,
 scx.tspa_oid_tipo_soli_pais,
 scx.num_prem,
 spx.prod_oid_prod,
 mp.cod_sap,
 spx.num_unid_aten,
 scx.perd_oid_peri,
 scx.clie_oid_clie
 FROM
 inc_concu_param_gener cpgx,
 INC_TMP_CONCU_VIGEN vig,
 inc_param_gener_premi pgpx,
 ped_solic_cabec scx,
 ped_solic_posic spx,
 mae_produ mp
 WHERE
 cpgx.oid_para_gral = vig.OID_PARA_GRAL
 AND cpgx.oid_para_gral = pgpx.copa_oid_para_gral
 AND cpgx.oid_para_gral = scx.copa_oid_para_gene
 AND cpgx.pais_oid_pais = scx.pais_oid_pais
 AND scx.fec_fact IS NOT NULL
 AND scx.oid_soli_cabe = spx.soca_oid_soli_cabe
 AND mp.oid_prod = spx.prod_oid_prod
 ) x
 GROUP BY
 x.oid_para_gral,
 x.num_prem,
 x.prod_oid_prod,
 x.cod_sap
 ) prem ,
 ( -- Numero de consultoras atendidas dentro y fuera de la campaña de despacho
 SELECT x.oid_para_gral,
 x.num_nive,
 x.num_prem,
 x.prod_oid_prod,
 SUM(DECODE(x.perio_a_tiempo,1, 1,0)) AS ConsAtendCampDesp,
 SUM(DECODE(x.perio_a_tiempo,0, 1,0)) AS ConsFueraCampDesp
 FROM
 (
 SELECT cpgx.oid_para_gral,
 pnpx.num_nive,
 scx.num_prem,
 spx.prod_oid_prod,
 scx.clie_oid_clie,
 DECODE( scx.perd_oid_peri, pgpx.perd_oid_peri, 1, 0) AS perio_a_tiempo
 FROM
 INC_CONCU_PARAM_GENER cpgx,
 INC_TMP_CONCU_VIGEN vig,
 INC_PARAM_GENER_PREMI pgpx,
 INC_PARAM_NIVEL_PREMI pnpx,
 PED_SOLIC_CABEC scx,
 PED_SOLIC_POSIC spx
 WHERE
 cpgx.oid_para_gral = vig.oid_para_gral
 AND cpgx.oid_para_gral = pgpx.copa_oid_para_gral(+)
 AND cpgx.oid_para_gral = scx.copa_oid_para_gene
 AND cpgx.pais_oid_pais = scx.pais_oid_pais
 AND scx.fec_Fact IS NOT NULL
 AND scx.oid_soli_cabe = spx.soca_oid_soli_cabe
 AND pnpx.pagp_oid_para_gene_prem = pgpx.oid_para_gene_prem
 AND spx.num_unid_aten > 0 -- debe haber despachado una unidad por lo menos --
 AND scx.tspa_oid_tipo_soli_pais IN ( SELECT oid_tipo_soli_pais FROM ped_tipo_solic_pais
 WHERE tsol_oid_tipo_soli IN (SELECT oid_tipo_soli FROM ped_tipo_solic
 WHERE cod_tipo_soli IN ('SINC','SING','SIPC','SICP','SIFC','SIFG')) )
 GROUP BY
 cpgx.oid_para_gral,
 pnpx.num_nive,
 scx.num_prem,
 spx.prod_oid_prod,
 scx.perd_oid_peri,
 pgpx.perd_oid_peri,
 scx.clie_oid_clie
 ) x
 GROUP BY x.oid_para_gral, x.num_nive, x.num_prem, x.prod_oid_prod
 ) consdesp ,
 ( -- Numero de Cambios (canjes) - Devoluciones - Trueques - Faltantes - NMP y Servicio de Premios
 SELECT x.oid_para_gral,
 -- Nivel --
 (SELECT pnp.num_nive
 FROM inc_param_gener_premi pgp,
 inc_param_nivel_premi pnp,
 inc_premi_artic pa,
 inc_lote_premi_artic lpa,
 inc_artic_lote al
 WHERE pgp.copa_oid_para_gral = x.oid_para_gral
 AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
 AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
 AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
 AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
 AND al.prod_oid_prod = x.prod_oid_prod
 AND lpa.num_prem = x.num_prem) num_nive,
 x.num_prem,
 x.prod_oid_prod,
 ABS(SUM( DECODE(x.trueque, 1, x.num_unid_aten, 0 ))) AS trueques,
 ABS(SUM( DECODE(x.canje, 1, x.num_unid_aten, 0 ))) AS canjes,
 ABS(SUM( DECODE(x.devolucion, 1, x.num_unid_aten, 0 ))) AS devoluciones,
 ABS(SUM( DECODE(x.faltante, 1, x.num_unid_aten, 0 ))) AS faltantes,
 ABS(SUM( DECODE(x.nota_merca_perdi, 1, x.num_unid_aten, 0 ))) AS nota_mercaderia_perdida,
 ABS(SUM( DECODE(x.servicio_premio, 1, x.num_unid_aten, 0 ))) AS servicio_premio
 FROM (SELECT cpgx.oid_para_gral,
 cpgx.num_conc,
 scx.tspa_oid_tipo_soli_pais,
 scx.num_prem,
 spx.prod_oid_prod,
 spx.num_unid_aten,
 scx.perd_oid_peri,
 scx.clie_oid_clie,
 DECODE( scx.tspa_oid_tipo_soli_pais,
 (SELECT tspa_oid_soli_pais_gene FROM rec_opera WHERE cod_oper IN ('TP')), 1,
 (SELECT tspa_oid_soli_pais_gene FROM rec_opera WHERE cod_oper IN ('PT')), 1, 0 ) AS trueque,
 DECODE( scx.tspa_oid_tipo_soli_pais,
 (SELECT tspa_oid_soli_pais_gene FROM rec_opera WHERE cod_oper IN ('CP')), 1,
 (SELECT tspa_oid_soli_pais_gene FROM rec_opera WHERE cod_oper IN ('PC')), 1, 0 ) AS canje,
 DECODE( scx.tspa_oid_tipo_soli_pais,
 (SELECT tspa_oid_soli_pais_gene FROM rec_opera WHERE cod_oper IN ('DP')), 1,
 (SELECT tspa_oid_soli_pais_gene FROM rec_opera WHERE cod_oper IN ('DZ')), 1, 0 ) AS devolucion,
 DECODE( scx.tspa_oid_tipo_soli_pais,
 (SELECT tspa_oid_soli_pais_gene FROM rec_opera WHERE cod_oper IN ('FP')), 1,
 (SELECT tspa_oid_soli_pais_gene FROM rec_opera WHERE cod_oper IN ('PF')), 1, 0 ) AS faltante,
 DECODE( scx.tspa_oid_tipo_soli_pais,
 (SELECT tspa_oid_soli_pais_gene FROM rec_opera WHERE cod_oper IN ('XP')), 1, 0 ) AS nota_merca_perdi,
 DECODE( scx.tspa_oid_tipo_soli_pais,
 (SELECT tspa_oid_soli_con_stoc FROM rec_opera WHERE cod_oper IN ('SP')), 1,
 (SELECT tspa_oid_soli_con_stoc FROM rec_opera WHERE cod_oper IN ('PS')), 1, 0 ) AS servicio_premio
 FROM
 inc_concu_param_gener cpgx,
 INC_TMP_CONCU_VIGEN vig,
 inc_param_gener_premi pgpx,
 ped_solic_cabec scx,
 ped_solic_posic spx
 WHERE
 cpgx.oid_para_gral = vig.OID_PARA_GRAL
 AND cpgx.oid_para_gral = pgpx.copa_oid_para_gral
 AND cpgx.oid_para_gral = scx.copa_oid_para_gene
 AND cpgx.pais_oid_pais = scx.pais_oid_pais
 AND scx.fec_Fact IS NOT NULL
 AND scx.oid_soli_cabe = spx.soca_oid_soli_cabe
 AND scx.tspa_oid_tipo_soli_pais IN ( SELECT tspa_oid_soli_pais_gene
 FROM rec_opera WHERE cod_oper IN ('CP','PC','DP','DZ','TP','PT','FP','PF','XP')
 AND tspa_oid_soli_pais_gene IS NOT NULL
 UNION
 SELECT tspa_oid_soli_con_stoc
 FROM rec_opera WHERE cod_oper IN ('SP','PS')
 AND tspa_oid_soli_con_stoc IS NOT NULL )
 ) x
 GROUP BY
 x.oid_para_gral,
 x.num_prem,
 x.prod_oid_prod
 ) cdrs
 WHERE
 con.oid_para_gral = gan.oid_para_gral(+)
 AND con.num_nive = gan.num_nive(+)
 AND con.num_prem = gan.num_prem(+)
 AND con.prod_oid_prod = gan.prod_oid_prod(+)
 AND con.oid_para_gral = prem.oid_para_gral(+)
 AND con.num_nive = prem.num_nive(+)
 AND con.num_prem = prem.num_prem(+)
 AND con.prod_oid_prod = prem.prod_oid_prod(+)
 AND con.oid_para_gral = consdesp.oid_para_gral(+)
 AND con.num_nive = consdesp.num_nive(+)
 AND con.num_prem = consdesp.num_prem(+)
 AND con.prod_oid_prod = consdesp.prod_oid_prod(+)
 AND con.oid_para_gral = cdrs.oid_para_gral(+)
 AND con.num_nive = cdrs.num_nive(+)
 AND con.num_prem = cdrs.num_prem(+)
 AND con.prod_oid_prod = cdrs.prod_oid_prod(+)
 GROUP BY
 con.perd_oid_peri_desd,
 con.perd_oid_peri_hast,
 con.num_conc,
 con.val_nomb,
 con.cod_sap,
 con.des_producto;

 --Actualizamos Total CDRs. = Cambios + Devoluciones + Trueques + Faltantes + Nota Merc.Perd. + Servicio de Premio
 UPDATE INC_TMP_INDIC_GESTI_INCEN
 SET NUM_TOTA_CDRS = NUM_CAMB + NUM_DEVO + NUM_TRUE + NUM_FALT_PREM + NUM_MERC_PERD + NUM_SERV_PREM;

 --Calculamos los porcentajes de cambios, anulaciones, devoluciones, trueques
 UPDATE INC_TMP_INDIC_GESTI_INCEN
 SET VAL_PORC_CAMB = ROUND((NUM_CAMB * 100) / NUM_PREM, 2),
 VAL_PORC_DEVO = ROUND((NUM_DEVO * 100) / NUM_PREM, 2),
 VAL_PORC_TRUE = ROUND((NUM_TRUE * 100) / NUM_PREM, 2),
 VAL_PORC_FALT_PREM = ROUND((NUM_FALT_PREM * 100) / NUM_PREM, 2),
 VAL_PORC_MERC_PERD = ROUND((NUM_MERC_PERD * 100) / NUM_PREM, 2),
 VAL_PORC_SERV_PREM = ROUND((NUM_SERV_PREM * 100) / NUM_PREM, 2),
 VAL_PORC_TOTA_CDRS = ROUND((NUM_TOTA_CDRS * 100) / NUM_PREM, 2)
 WHERE NUM_PREM > 0;

 --Calculamos los porcentajes de Envio Campaña Despacho
 UPDATE INC_TMP_INDIC_GESTI_INCEN
 SET VAL_PORC_ENVI_DESP = ROUND((NUM_CONS_ATEN * 100) / (NUM_CONS_ATEN + NUM_CONS_ATEN_FUER), 2),
 VAL_PORC_ENVI_DESP_FUER = ROUND((NUM_CONS_ATEN_FUER * 100) / (NUM_CONS_ATEN + NUM_CONS_ATEN_FUER), 2)
 WHERE (NUM_CONS_ATEN + NUM_CONS_ATEN_FUER) > 0;

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := SUBSTR(SQLERRM,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_OBTIE_INDIC_GESTI_INCEN: '||ls_sqlerrm);

END INC_PR_OBTIE_INDIC_GESTI_INCEN;

/***************************************************************************
Descripcion : Genera la data delos premios despachadaoos para luego ser mostrada
 por el reporte
Fecha Creacion : 23/06/2009
Parametros :
 psCodPais Codigo Pais
 psCodMarca Codigo Marca
 psCodCanal Codigo Canal
 psTipoCierre Tipo Cierre (D:diario,R:region Z:zona, P:campanha)
 psCodPeriodo Codigo Periodo
 psFechaProceso Fecha Proceso
 psUsuario Usuario
 psNumLote Numero de Lote que se genera
Autor : Sergio Buchelli
***************************************************************************/
PROCEDURE INC_PR_GENER_REPOR_INCEN
 (psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodCanal VARCHAR2,
 psTipoCierre VARCHAR2,
 psCodPeriodo VARCHAR2,
 psFechaProceso VARCHAR2,
 psUsuario VARCHAR2,
 psNumLote OUT VARCHAR2)
IS
 lnIdPais SEG_PAIS.OID_PAIS%TYPE;
 lnIdMarca SEG_MARCA.OID_MARC%TYPE;
 lnIdCanal SEG_CANAL.OID_CANA%TYPE;
 lnIdPeriodo CRA_PERIO.OID_PERI%TYPE;

BEGIN

 --RECUPERAMOS EL OID DEL PAIS, MARCA Y CANAL
 lnIdPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
 lnIdMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
 lnIdCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);
 lnIdPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO( psCodPeriodo,lnIdMarca,lnIdCanal);

 EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
 EXECUTE IMMEDIATE 'alter session set NLS_DATE_FORMAT = "DD/MM/YYYY"';

 /* Obteniendo el numero de lote*/
 SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') ||
 LPAD(NVL(MAX(SUBSTR(A.NUM_LOTE, 9, 4)) + 1, 1), 4, '0')
 INTO psNumLote
 FROM INC_REPOR_ESTAD_PREMI_DESPA A;

 INSERT INTO INC_REPOR_ESTAD_PREMI_DESPA (
 NUM_LOTE, TIP_CIERR, COD_PERI,
 FEC_PROC,
 NUM_PEDI_DIA,
 NUM_PEDI_CAMP,
 NUM_PEDI_FALT,
 NUM_PEDI_PROY,
 NUM_CONC,
 DES_CONC,
 COD_SAP,
 DES_PROD,
 NUM_STOC,
 NUM_PUPP_DIA,
 NUM_PUPP_CAMP,
 NUM_UNID_DIA,
 NUM_UNID_CAMP,
 NUM_UNID_PROY,
 NUM_UNID_POR_DESP,
 NUM_UNID_FALT,
 VAL_OBSE, FEC_ULTI_ACTU,
 COD_USUR)
 SELECT
 psNumLote,psTipoCierre,psCodPeriodo
 ,TO_CHAR(pedidos.fec_fact,'dd/MM/yyyy') fec_fact
 ,pedidos.num_pedi_fec_fact
 ,pedidos.num_pedi_camp
 ,CASE WHEN ( pedidos.num_pedi_camp > pedidos.num_pedi_proy_camp ) THEN 0
 ELSE pedidos.num_pedi_proy_camp - pedidos.num_pedi_camp
 END num_pedi_falt
 ,pedidos.num_pedi_proy_camp
 ,premios_info.num_conc
 ,premios_info.val_nomb
 ,premios_info.cod_sap
 ,premios_info.des_producto
 ,NVL(stocks.unid_stock,0) unid_stock
 ,ROUND(premios_despa.unid_aten_fec_fact/pedidos.num_pedi_fec_fact,2) pup_dia
 ,ROUND(premios_despa.unid_aten_camp/pedidos.num_pedi_camp,2) pup_camp
 ,premios_despa.unid_aten_fec_fact unid_dia
 ,premios_despa.unid_aten_camp unid_acum
 ,ROUND((premios_despa.unid_aten_camp/pedidos.num_pedi_camp)*pedidos.num_pedi_proy_camp) unid_proy
 ,ROUND((premios_despa.unid_aten_camp/pedidos.num_pedi_camp)*pedidos.num_pedi_proy_camp)-premios_despa.unid_aten_camp unid_por_desp
 ,NVL(stocks.unid_stock,0)-(ROUND((premios_despa.unid_aten_camp/pedidos.num_pedi_camp)*pedidos.num_pedi_proy_camp)-premios_despa.unid_aten_camp) faltantes
 ,CASE
 WHEN ( (NVL(stocks.unid_stock,0)-(ROUND((premios_despa.unid_aten_camp/pedidos.num_pedi_camp)*pedidos.num_pedi_proy_camp)-premios_despa.unid_aten_camp))<0 )
 THEN 'FALTA STOCK'
 ELSE 'EXISTE STOCK'
 END observacion,
 SYSDATE,
 psUsuario
 FROM
 ( -- 1- PREMIOS DESPACHADOS EN EL DIA --
 SELECT
 t1.perd_oid_peri
 ,t1.copa_oid_para_gene
 ,t1.prod_oid_prod
 ,NVL(t1.unid_aten_camp,0) unid_aten_camp
 ,NVL(t2.unid_aten_fec_fact,0) unid_aten_fec_fact
 FROM
 ( -- Numero de Premios Despachados en la Campaña
 SELECT
 sc.perd_oid_peri
 ,sc.copa_oid_para_gene
 ,sp.prod_oid_prod
 ,SUM(sp.num_unid_aten) unid_aten_camp
 FROM
 ped_solic_cabec sc
 ,ped_tipo_solic ts
 ,ped_tipo_solic_pais tsp
 ,ped_solic_posic sp
 ,ped_clase_solic cs
 WHERE
 sc.perd_oid_peri = lnIdPeriodo
 AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
 AND cs.oid_clas_soli = ts.clso_oid_clas_soli
 AND cs.cod_clas_soli = 'I1'
 AND ts.ind_soli_nega = 0
 AND sc.grpr_oid_grup_proc = 5
 AND sc.oid_soli_cabe = sp.soca_oid_soli_cabe
 AND sc.soca_oid_soli_cabe IS NOT NULL
 AND sc.copa_oid_para_gene IS NOT NULL
 GROUP BY
 sc.perd_oid_peri
 ,sc.copa_oid_para_gene
 ,sp.prod_oid_prod
 ) t1,
 ( -- Numero de Premios Despachados al dia de facturacion
 SELECT
 sc.copa_oid_para_gene
 ,sp.prod_oid_prod
 ,SUM(sp.num_unid_aten) unid_aten_fec_fact
 FROM
 ped_solic_cabec sc
 ,ped_tipo_solic ts
 ,ped_tipo_solic_pais tsp
 ,ped_solic_posic sp
 ,ped_clase_solic cs
 WHERE
 sc.perd_oid_peri = lnIdPeriodo
 AND TO_CHAR(sc.fec_fact,'dd/MM/yyyy') = psFechaProceso
 AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
 AND cs.oid_clas_soli = ts.clso_oid_clas_soli
 AND cs.cod_clas_soli = 'I1'
 AND ts.ind_soli_nega = 0
 AND sc.grpr_oid_grup_proc = 5
 AND sc.oid_soli_cabe = sp.soca_oid_soli_cabe
 AND sc.soca_oid_soli_cabe IS NOT NULL
 AND sc.copa_oid_para_gene IS NOT NULL
 GROUP BY
 sc.copa_oid_para_gene
 ,sp.prod_oid_prod
 ) t2
 WHERE
 t1.copa_oid_para_gene = t2.copa_oid_para_gene
 AND t1.prod_oid_prod = t2.prod_oid_prod
 ) premios_despa,
 ( -- 2 - PEDIDOS EN CAMPAÑA Y ESTIMADOS --
 SELECT
 t1.perd_oid_peri
 ,t1.fec_fact
 ,NVL(t1.num_pedi_fec_fact,0) num_pedi_fec_fact
 ,NVL(t2.num_pedi_camp,0) num_pedi_camp
 ,NVL(t3.num_pedi_proy_camp,0) num_pedi_proy_camp
 FROM
 ( -- Numero de Pedidos Facturados del Día --
 SELECT
 sc.perd_oid_peri
 ,sc.fec_fact
 , COUNT(*) num_pedi_fec_fact
 FROM ped_solic_cabec sc,
 ped_tipo_solic ts,
 ped_tipo_solic_pais tsp,
 ped_solic_cabec sc2
 WHERE sc.perd_oid_peri = lnIdPeriodo
 AND TO_CHAR(sc.fec_fact,'dd/MM/yyyy') = psFechaProceso
 AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
 AND ts.cod_tipo_soli = 'SOC'
 AND sc.grpr_oid_grup_proc = 5
 AND sc.soca_oid_soli_cabe = sc2.oid_soli_cabe
 AND sc2.esso_oid_esta_soli <> 4
 GROUP BY
 sc.perd_oid_peri
 ,sc.fec_fact
 ) t1,
 ( -- Numero de Pedidos Facturados en la Campaña --
 SELECT
 sc.perd_oid_peri
 ,COUNT(*) num_pedi_camp
 FROM ped_solic_cabec sc,
 ped_tipo_solic ts,
 ped_tipo_solic_pais tsp,
 ped_solic_cabec sc2
 WHERE sc.perd_oid_peri = lnIdPeriodo
 AND sc.fec_fact IS NOT NULL
 AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
 AND ts.cod_tipo_soli = 'SOC'
 AND sc.grpr_oid_grup_proc = 5
 AND sc.soca_oid_soli_cabe = sc2.oid_soli_cabe
 AND sc2.esso_oid_esta_soli <> 4
 GROUP BY
 sc.perd_oid_peri
 ) t2,
 ( -- Numero de Pedidos Proyectados para la Campaña --
 SELECT perd_oid_peri, SUM(num_pedi) num_pedi_proy_camp
 FROM int_fuent_venta_previ_sap
 WHERE perd_oid_peri = lnIdPeriodo
 GROUP BY perd_oid_peri
 ) t3
 WHERE
 t1.perd_oid_peri = t2.perd_oid_peri
 AND t1.perd_oid_peri = t3.perd_oid_peri(+)
 ) pedidos,
 ( -- 3 - PREMIOS DESCRIPCION Y DATOS DEL CONCURSO --
 SELECT
 cpg.oid_para_gral
 ,cpg.num_conc
 ,cpg.val_nomb
 ,al.prod_oid_prod
 ,p.cod_sap
 , pq_apl_aux.Valor_Gen_I18n_Sicc( 1, al.prod_oid_prod, 'MAE_PRODU') AS des_producto
 FROM
 inc_concu_param_gener cpg
 ,inc_param_gener_premi pgp
 ,inc_param_nivel_premi pnp
 ,inc_premi_artic pa
 ,inc_lote_premi_artic lpa
 ,inc_artic_lote al
 ,mae_produ p
 WHERE
 cpg.oid_para_gral = pgp.copa_oid_para_gral
 AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
 AND pnp.tpre_oid_tipo_prem = 2
 AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
 AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
 AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
 AND al.prod_oid_prod = p.oid_prod
 GROUP BY
 cpg.oid_para_gral
 ,cpg.num_conc
 ,cpg.val_nomb
 ,al.prod_oid_prod
 ,p.cod_sap
 UNION
 SELECT
 cpg.oid_para_gral
 ,cpg.num_conc
 ,cpg.val_nomb
 ,ral.prod_oid_prod
 ,p.cod_sap
 , pq_apl_aux.Valor_Gen_I18n_Sicc( 1, ral.prod_oid_prod, 'MAE_PRODU') AS des_producto
 FROM
 inc_concu_param_gener cpg
 ,inc_param_gener_premi pgp
 ,inc_param_nivel_premi pnp
 ,inc_premi_artic pa
 ,inc_lote_premi_artic lpa
 ,inc_artic_lote al
 ,inc_reemp_artic_lote ral
 ,mae_produ p
 WHERE
 cpg.oid_para_gral = pgp.copa_oid_para_gral
 AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
 AND pnp.tpre_oid_tipo_prem = 2
 AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
 AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
 AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
 AND al.oid_arti_lote = ral.arlo_oid_arti_lote
 AND ral.prod_oid_prod = p.oid_prod
 GROUP BY
 cpg.oid_para_gral
 ,cpg.num_conc
 ,cpg.val_nomb
 ,ral.prod_oid_prod
 ,p.cod_sap
 ) premios_info,
 ( -- 4- STOCKS --
 SELECT s.prod_oid_prod, s.val_sald unid_stock
 FROM
 bel_stock s
 ,bel_estad_merca em
 ,(SELECT almc_oid_alma
 FROM (SELECT almc_oid_alma
 FROM ped_tipo_solic_pais
 WHERE oid_tipo_soli_pais IN (SELECT tspa_oid_tipo_soli_pais
 FROM ped_tipo_solic_proce
 WHERE oper_oid_oper IN (SELECT oid_oper FROM bel_opera WHERE cod_oper = 'INC020'))
 GROUP BY almc_oid_alma
 ) WHERE ROWNUM = 1) alm
 WHERE
 s.esme_oid_esta_merc = em.oid_esta_merc
 AND em.cod_esta = 'LD'
 AND em.pais_oid_pais = lnIdPais
 AND s.almc_oid_alma = alm.almc_oid_alma
 ) stocks
 WHERE
 premios_despa.perd_oid_peri = pedidos.perd_oid_peri(+)
 AND premios_despa.copa_oid_para_gene = premios_info.oid_para_gral(+)
 AND premios_despa.prod_oid_prod = premios_info.prod_oid_prod(+)
 AND premios_despa.prod_oid_prod = stocks.prod_oid_prod(+)
 ORDER BY
 premios_info.num_conc,premios_info.cod_sap;

EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := SUBSTR(SQLERRM,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_GENER_REPOR_INCEN: '||ls_sqlerrm);

END INC_PR_GENER_REPOR_INCEN;


/***************************************************************************
Descripcion : Genera el calculo de metas(venta base), clasificacion y creacion de mensajes
 de concursos Dupl Cyzone
Fecha Creacion    : 19/01/2010
Fecha modificacion: 13/07/2010
Fecha Ult.Modificacion : 20/07/2010
Parametros :
 p_cod_pais Codigo Pais
 p_cod_concurso Numero Concurso
 p_cod_peri Periodo
Autor       : Alexander Villavicencio
Modificacion: David Ramos
***************************************************************************/

PROCEDURE INC_PR_CALCU_VENTA(
        p_cod_pais     VARCHAR2,
        p_cod_concurso VARCHAR2,
        p_cod_peri     VARCHAR2,
        p_ind_Eliminar VARCHAR2

    )IS


 TYPE tmptablaClien IS RECORD
 (
 OID_CLIE           MAE_CLIEN.oid_clie %TYPE,
 VAL_NOM1           MAE_CLIEN.val_nom1 %TYPE,
 VAL_APE1           MAE_CLIEN.val_ape1 %TYPE,
 COD_CLIE           MAE_CLIEN.cod_clie %TYPE,
 COD_ZONA           ZON_ZONA.cod_zona  %TYPE,
 COD_TERR           ZON_TERRI.cod_terr %TYPE,
 SI_VINCULO_DCYZONE VARCHAR2(1)             ,
 VTA_ACUM           INC_METAS_TIPO_VENTA.imp_monto_venta %TYPE
 );
 TYPE tablaRegClientes IS TABLE OF tmptablaClien;
 tablaRegClientesrecord tablaRegClientes;

 CURSOR clientes(lnOidPais NUMBER,lnOidGral NUMBER, indicador NUMBER) IS
 SELECT mc.oid_clie,mc.val_nom1,mc.val_ape1, mc.cod_clie,
        zon.cod_zona cod_zona  ,zt.cod_terr cod_terr,
        NVL( ( SELECT 'S'  FROM MAE_CLIEN_VINCU  mcv, MAE_CLIEN ma
                WHERE mcv.clie_oid_clie_vnte =  mc.oid_clie
                  AND mcv.tivc_oid_tipo_vinc = 1
                  AND mcv.clie_oid_clie_vnte =  ma.oid_clie
                  AND mcv.fec_desd          <= SYSDATE
                  AND mcv.FEC_HAST IS NULL
                  AND ROWNUM=1
            ),'N') SI_Vinculo_DCyzone
            ,
   0 vta_acum
FROM
      INC_PARAM_CALIF B      ,
   ( SELECT DISTINCT perd_oid_peri,zorg_oid_regi
         FROM FAC_CONTR_CIERR
        WHERE
              tcie_oid_tipo_cier= 1         AND
              pais_oid_pais     = lnOidPais AND
              val_resu_proc     ='OK'
      )  fcc,
      ZON_ZONA zon               , ZON_SECCI sec       ,
      MAE_CLIEN_DATOS_ADICI mcda , MAE_CLIEN mc        ,
      MAE_CLIEN_UNIDA_ADMIN ua   , ZON_TERRI_ADMIN za  ,
      ZON_TERRI zt
WHERE
       fcc.perd_oid_peri     = B.perd_oid_peri_hast
   AND B.copa_oid_para_gral  = lnOidGral
   AND fcc.zorg_oid_regi     = zon.zorg_oid_regi
   AND zon.ind_acti          = 1 AND zon.ind_borr = 0
   AND sec.ind_acti          = 1 AND sec.ind_borr = 0
   AND mcda.ind_acti         = 1
   AND mc.oid_clie           = mcda.clie_oid_clie
   AND ua.ind_acti           = 1
   AND ua.clie_oid_clie      = mc.oid_clie
   AND ua.ztad_oid_terr_admi = za.oid_terr_admi
   AND za.terr_oid_terr      = zt.oid_terr
   AND zt.ind_borr           = 0
   AND za.zscc_oid_secc      = sec.oid_secc
   AND sec.zzon_oid_zona     = zon.oid_zona
   AND ((indicador = 1 AND NOT EXISTS
                        (SELECT 1
                           FROM MAE_CLIEN_TIPO_SUBTI G1, MAE_CLIEN_CLASI G2, MAE_TIPO_CLASI_CLIEN G3
                          WHERE G1.CLIE_OID_CLIE = mc.oid_clie
                            AND G1.OID_CLIE_TIPO_SUBT = G2.CTSU_OID_CLIE_TIPO_SUBT
                            AND G2.TCCL_OID_TIPO_CLASI = G3.OID_TIPO_CLAS
                            AND G3.COD_TIPO_CLAS = '37'))
        OR (indicador = 0));

  /* Declaracion de Variables */
    lnOidPais                 seg_pais.oid_pais%TYPE;
    lnOidPeriodo              CRA_PERIO.OID_PERI%TYPE;
    lnOidParaGral             INC_CONCU_PARAM_GENER.oid_para_gral%TYPE;
    lnIndConcPorRango         NUMBER;
    lnOidClie                 MAE_CLIEN.OID_CLIE%TYPE;
    lnIndAnulado              inc_param_calif.ind_anul%TYPE;
    lnIndDevuelto             inc_param_calif.ind_devo%TYPE;
    lnIndFalta                inc_param_calif.ind_falt_no_anun%TYPE;
    lnOidTipoClas             MAE_TIPO_CLASI_CLIEN.oid_tipo_clas%TYPE;
    lnOidClas                 MAE_CLASI.oid_clas%TYPE;

    V_meta_minima             INC_METAS_TIPO_VENTA.IMP_MONTO_VENTA%TYPE;
    V_vta_acum                INC_METAS_TIPO_VENTA.IMP_MONTO_VENTA%TYPE;
    V_ValorInicial            INC_RANGO_MONTO_BASE.VAL_INIC_RANG%TYPE;
    V_ValorFinal              INC_RANGO_MONTO_BASE.VAL_INIC_RANG%TYPE;
    V_Oid_Meta                INC_METAS_TIPO_VENTA.OID_META_TIPO_VENTA%TYPE;
    V_SI_Vinculo_DCyzone      VARCHAR2(1);
    V_cod_tipo_clas           INC_RANGO_MONTO_BASE.COD_TIPO_CLAS%TYPE;
    V_cod_clas                INC_RANGO_MONTO_BASE.COD_CLAS%TYPE;
    V_oid_clie_tipo_subt      MAE_CLIEN_TIPO_SUBTI.oid_clie_tipo_subt%TYPE;
    V_oid_clie_clas           MAE_CLIEN_CLASI.OID_CLIE_CLAS%TYPE;
    V_mens_oid_mens           INC_PARAM_CALIF.mens_oid_mens%TYPE;
    V_val_nomb                INC_CONCU_PARAM_GENER.val_nomb%TYPE;

    V_perd_oid_peri_desd1  INC_CONCU_PARAM_GENER.perd_oid_peri_desd%TYPE;
    V_perd_oid_peri_hast1  INC_CONCU_PARAM_GENER.perd_oid_peri_hast%TYPE;
    V_perd_oid_peri_desd2  INC_PARAM_CALIF.perd_oid_peri_desd%TYPE;
    V_perd_oid_peri_hast2  INC_PARAM_CALIF.perd_oid_peri_hast%TYPE;
    V_val_nomb_peri1       CRA_PERIO.Val_Nomb_Peri%TYPE;
    V_val_nomb_peri2       CRA_PERIO.Val_Nomb_Peri%TYPE;
    V_val_nomb_peri3       CRA_PERIO.Val_Nomb_Peri%TYPE;
    V_val_nomb_peri4       CRA_PERIO.Val_Nomb_Peri%TYPE;
    W_FILAS NUMBER := 4000;

    V_graba_meta   VARCHAR2(1):='N';
    V_graba_msg    VARCHAR2(1):='N';
    V_graba_clasif VARCHAR2(1):='N';

    lnOcurrencias           NUMBER;
BEGIN

  IF(p_ind_Eliminar = '1') THEN
    DELETE FROM MAE_CLIEN_CLASI
     WHERE TCCL_OID_TIPO_CLASI IN (SELECT OID_TIPO_CLAS
                                     FROM MAE_TIPO_CLASI_CLIEN WHERE COD_TIPO_CLAS = '37');
  END IF;

 --RECUPERAMOS EL OID DEL PAIS Y DEL PERIODO
  lnOidPeriodo   := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(p_cod_peri);
  lnOidPais      := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(p_cod_pais);

  SELECT A.oid_para_gral      ,B.mens_oid_mens      ,A.val_nomb ,
         A.perd_oid_peri_desd ,A.perd_oid_peri_hast ,
         B.perd_oid_peri_desd ,B.perd_oid_peri_hast
    INTO lnOidParaGral        ,V_mens_oid_mens      ,V_val_nomb ,
         V_perd_oid_peri_desd1,V_perd_oid_peri_hast1,
         V_perd_oid_peri_desd2,V_perd_oid_peri_hast2
   FROM INC_CONCU_PARAM_GENER A ,INC_PARAM_CALIF B
  WHERE A.pais_oid_pais      = lnOidPais         AND
        A.num_conc           = p_cod_concurso    AND
        B.copa_oid_para_gral = a.oid_para_gral ;

  BEGIN
    SELECT val_nomb_peri INTO V_val_nomb_peri1
     FROM CRA_PERIO WHERE oid_peri=V_perd_oid_peri_desd1;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
          V_val_nomb_peri1:=' ';
  END;
  BEGIN
    SELECT val_nomb_peri INTO V_val_nomb_peri2
     FROM CRA_PERIO WHERE oid_peri=V_perd_oid_peri_hast1;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
          V_val_nomb_peri2:=' ';
  END;
  BEGIN
    SELECT val_nomb_peri INTO V_val_nomb_peri3
     FROM CRA_PERIO WHERE oid_peri=V_perd_oid_peri_desd2;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
          V_val_nomb_peri3:=' ';
  END;
  BEGIN
    SELECT val_nomb_peri INTO V_val_nomb_peri4
     FROM CRA_PERIO WHERE oid_peri=V_perd_oid_peri_hast2;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
          V_val_nomb_peri4:=' ';
  END;

  SELECT NVL(max(imrc.imp_mont_mini),0)  INTO V_meta_minima
    FROM INC_CLASI_PARTI_CALIF       icpc ,
         INC_MONTO_MINIM_RANGO_CONSU imrc
   WHERE icpc.copa_oid_para_gral = lnOidParaGral
     AND icpc.oid_clas_part_cali = imrc.clpc_oid_clas_part_cali ;

   SELECT
       CASE WHEN NVL(COUNT(cod_rang  )  ,0)<>0 THEN 1
       ELSE
            0
       END,
       NVL(MAX(val_inic_rang),0), NVL(MAX(val_fina_rang),0),
       MAX(cod_tipo_clas) , MAX(cod_clas)
    INTO lnIndConcPorRango, V_ValorInicial, V_ValorFinal,
         V_cod_tipo_clas , V_cod_clas
   FROM INC_RANGO_MONTO_BASE
   WHERE copa_oid_para_gral = lnOidParaGral;

  SELECT ind_devo     , ind_anul      , ind_falt_no_anun
     INTO lnIndDevuelto, lnIndAnulado  ,lnIndFalta
    FROM INC_PARAM_CALIF
  WHERE copa_oid_para_gral = lnOidParaGral ;

OPEN CLIENTES(lnOidPais,lnOidParaGral,lnIndConcPorRango);
LOOP
   FETCH CLIENTES BULK COLLECT INTO tablaRegClientesrecord LIMIT W_FILAS;
   IF tablaRegClientesrecord.COUNT > 0 THEN
   FOR x IN tablaRegClientesrecord.FIRST .. tablaRegClientesrecord.LAST LOOP
        lnOidClie            := tablaRegClientesrecord(x).oid_clie;
        V_SI_Vinculo_DCyzone := tablaRegClientesrecord(x).si_vinculo_dcyzone;

        SELECT  NVL(SUM(
                CASE WHEN ipc.ind_falt_no_anun = 0 THEN
                      nvl(psp.val_prec_cata_unit_loca,0) * nvl(psp.num_unid_aten,0)
                     WHEN ipc.ind_falt_no_anun = 1 THEN
                       nvl(psp.val_prec_cata_unit_loca,0) * nvl(psp.num_unid_dema_real,0)
                ELSE
                       0
                END
                ),0) INTO V_vta_acum
        FROM  PED_TIPO_SOLIC_PAIS AAA,PED_TIPO_SOLIC BBB,
              INC_PRODU_CALIF ipr    ,PED_SOLIC_CABEC psc,INC_PARAM_CALIF ipc,
              PED_SOLIC_CABEC pscb   ,PED_SOLIC_POSIC psp,
              PRE_OFERT_DETAL pod    ,MAE_PRODU  mp,
              (SELECT OID_TIPO_OFER FROM PRE_TIPO_OFERT WHERE  OID_TIPO_OFER NOT IN (SELECT  ipex.tofe_oid_tipo_ofer
                 FROM inc_produ_exclu_calif ipex WHERE ipex.copa_oid_para_gral = lnOidParaGral)) pto,
              (SELECT OID_PERI FROM CRA_PERIO WHERE OID_PERI BETWEEN V_perd_oid_peri_desd2 AND V_perd_oid_peri_hast2) cra
        WHERE psc.clie_oid_clie      = lnOidClie
          AND psc.perd_oid_peri = cra.oid_peri
          AND ipc.copa_oid_para_gral = lnOidParaGral
          AND AAA.pais_oid_pais      = lnOidPais
          AND AAA.tsol_oid_tipo_soli = BBB.oid_tipo_soli
          AND BBB.cod_tipo_soli      ='SOC'
          AND AAA.oid_tipo_soli_pais = psc.tspa_oid_tipo_soli_pais
          AND psc.OID_SOLI_CABE      = psp.SOCA_OID_SOLI_CABE
          AND pscB.perd_oid_peri      = psc.perd_oid_peri
          AND pscB.esso_oid_esta_soli <> 4
          AND psc.soca_oid_soli_cabe  = pscB.oid_soli_cabe
          AND mp.oid_prod             = psp.prod_oid_prod
          AND ipr.copa_oid_para_gral = ipc.copa_oid_para_gral
          AND mp.mapr_oid_marc_prod  = ipr.mapr_oid_marc_prod
          AND pod.oid_deta_ofer = psp.ofde_oid_deta_ofer
          AND pto.oid_tipo_ofer = pod.tofe_oid_tipo_ofer
          AND psp.espo_oid_esta_posi <> 2;

        IF V_vta_acum < V_meta_minima THEN
           V_vta_acum :=V_meta_minima;
        END IF;

        V_graba_meta   :='N';  V_graba_msg :='N'; V_graba_clasif :='N';
        IF V_SI_Vinculo_DCyzone = 'S' AND  lnIndConcPorRango =  0  THEN
           V_graba_meta := 'S'; V_graba_msg := 'S'; V_graba_clasif := 'N';
        ELSIF V_SI_Vinculo_DCyzone = 'S' AND  lnIndConcPorRango =  1  THEN
              IF V_vta_acum BETWEEN V_ValorInicial AND V_ValorFinal THEN
                 V_graba_meta := 'S'; V_graba_msg := 'S'; V_graba_clasif := 'S';
              ELSE
                 V_graba_meta := 'N'; V_graba_msg := 'N'; V_graba_clasif := 'N';
              END IF;
        ELSIF V_SI_Vinculo_DCyzone = 'N' AND  lnIndConcPorRango =  0  THEN
              V_graba_meta := 'S'; V_graba_msg := 'S'; V_graba_clasif := 'N';
        ELSIF V_SI_Vinculo_DCyzone = 'N' AND  lnIndConcPorRango =  1  AND
              V_ValorInicial = 0          THEN
              V_graba_meta := 'S'; V_graba_msg := 'S'; V_graba_clasif := 'N';
        END IF;

        IF V_graba_meta = 'S' AND (lnIndDevuelto = 0) AND (lnIndAnulado  = 0) THEN
           BEGIN
             SELECT OID_META_TIPO_VENTA INTO V_Oid_Meta
               FROM INC_METAS_TIPO_VENTA
             WHERE clie_oid_clie      = lnOidClie
               AND copa_oid_para_gral = lnOidParaGral;

             UPDATE INC_METAS_TIPO_VENTA
                SET IMP_MONTO_VENTA=V_vta_acum,VAL_META       =V_vta_acum
              WHERE OID_META_TIPO_VENTA = V_Oid_Meta ;
           EXCEPTION
              WHEN NO_DATA_FOUND THEN
                   INSERT INTO INC_METAS_TIPO_VENTA
                   ( OID_META_TIPO_VENTA,  IMP_MONTO_VENTA,  NUM_UNID_VEND,  VAL_INCR,
                     VAL_META           ,  CLIE_OID_CLIE  ,COPA_OID_PARA_GRAL)
                   VALUES
                   ( INC_METV_SEQ.NEXTVAL, V_vta_acum      ,              0,         0,
                     V_vta_acum          ,  lnOidClie      ,lnOidParaGral     );
           END;
        END IF;

        IF V_graba_clasif = 'S' AND (lnIndDevuelto = 0) AND (lnIndAnulado  = 0) THEN
           BEGIN
             SELECT AA.oid_clie_tipo_subt,  mtcc.oid_tipo_clas   ,  mcl.oid_clas
               INTO V_oid_clie_tipo_subt , lnOidTipoClas ,  lnOidClas
               FROM MAE_CLIEN_TIPO_SUBTI AA,
               ( SELECT DISTINCT T2.sbti_oid_subt_clie,T2.OID_TIPO_CLAS
                    FROM INC_RANGO_MONTO_BASE T1,MAE_TIPO_CLASI_CLIEN T2
                  WHERE T1.COPA_OID_PARA_GRAL = lnOidParaGral    AND
                        T1.cod_tipo_clas      = T2.cod_tipo_clas
               ) mtcc,
               ( SELECT DISTINCT T2.TCCL_OID_TIPO_CLAS,T2.oid_clas
                   FROM  INC_RANGO_MONTO_BASE T1,MAE_CLASI T2
                  WHERE T1.COPA_OID_PARA_GRAL = lnOidParaGral AND
                        T1.cod_clas      = T2.cod_clas
               ) mcl
             WHERE AA.clie_oid_clie        = lnOidClie
               AND mtcc.sbti_oid_subt_clie = AA.sbti_oid_subt_clie
               AND mcl.tccl_oid_tipo_clas  = MTCC.Oid_Tipo_Clas;

               SELECT COUNT(1) INTO lnOcurrencias
                 FROM MAE_CLIEN_CLASI mcc, MAE_TIPO_CLASI_CLIEN tcc
                WHERE mcc.CTSU_OID_CLIE_TIPO_SUBT = V_oid_clie_tipo_subt
                  AND mcc.TCCL_OID_TIPO_CLASI = tcc.OID_TIPO_CLAS
                  AND tcc.COD_TIPO_CLAS = '37';

               --Pasamos a la Siguiente Consultora
               IF(lnOcurrencias > 0) THEN
                 V_graba_msg := 'N';
               ELSE
                  INSERT INTO MAE_CLIEN_CLASI
                     ( OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT,  CLAS_OID_CLAS  ,
                       PERD_OID_PERI, TCCL_OID_TIPO_CLASI    ,  FEC_CLAS       ,
                       IND_PPAL     , FEC_ULTI_ACTU)
                  VALUES
                     ( MAE_CLCL_SEQ.NEXTVAL,    V_oid_clie_tipo_subt ,  lnOidClas ,
                       lnOidPeriodo        ,    lnOidTipoClas        ,  TRUNC(SYSDATE,'DD') ,
                        0                   ,    TRUNC(SYSDATE,'DD'));
               END IF;

           EXCEPTION
             WHEN NO_DATA_FOUND THEN
                NULL;
           END;
        END IF;

        IF V_graba_msg = 'S' AND (lnIndDevuelto = 0) AND (lnIndAnulado  = 0) THEN
           DELETE FROM MSG_BUZON_MENSA
           WHERE CLIE_OID_CLIE       =lnOidClie AND
                 MODU_OID_MODU_ORIG  =13        AND
                 DATO_VARI_13=p_cod_concurso   ;

           INSERT INTO MSG_BUZON_MENSA
            ( OID_BUZO_MENS      ,NUM_SECU       ,
              IND_ESTA_MENS      ,CLIE_OID_CLIE  , MENS_OID_MENS      ,
              MODU_OID_MODU_ORIG ,VAL_NOM1_CLIE  , VAL_NOM2_CLIE      ,
              VAL_APE1_CLIE      ,VAL_APE2_CLIE  , VAL_APEL_CASA_CLIE , NUM_LOTE_IMPR  ,
              FEC_GRAB           ,FEC_IMPR       , IND_LIST_CONS      , PERI_OID_PERI  ,
              IND_ACTI           ,DATO_VARI_01   , DATO_VARI_02 ,--
              DATO_VARI_03                       , DATO_VARI_04       ,
              DATO_VARI_05                       , DATO_VARI_06       ,
              DATO_VARI_07       ,DATO_VARI_08   , DATO_VARI_09       , DATO_VARI_10   ,
              DATO_VARI_11       ,DATO_VARI_12   , DATO_VARI_13       , DATO_VARI_14   ,
              DATO_VARI_15                       , DATO_VARI_16       ,
              DATO_VARI_17       ,DATO_VARI_18   , DATO_VARI_19       , DATO_VARI_20  )
            SELECT
               MSG_BUME_SEQ.NEXTVAL,   MSG_BUM2_SEQ.NEXTVAL,
               NULL               ,lnOidClie      , V_mens_oid_mens   ,
               13                 ,' '            , ' '               ,
               ' '                ,' '            , ' '               , NULL           ,
               TRUNC(SYSDATE,'DD'),NULL           , 0                 , NULL           ,
               1                  ,TO_CHAR(SYSDATE,'DD/MM/YYYY'), tablaRegClientesrecord(x).val_nom1 ,
               tablaRegClientesrecord(x).val_ape1  , tablaRegClientesrecord(x).cod_clie ,
               tablaRegClientesrecord(x).cod_zona  , TO_CHAR(tablaRegClientesrecord(x).cod_terr),
               V_val_nomb_peri1   ,V_val_nomb_peri2,V_val_nomb_peri3   ,V_val_nomb_peri4,
               TO_CHAR(V_vta_acum),'0'             , p_cod_concurso     , V_val_nomb    ,
               TO_CHAR(ipnp.num_cant_inic_punt)       , TO_CHAR(V_vta_acum + ipnp.num_cant_inic_punt)  ,
               TO_CHAR(ipnp.num_nive),' '             , ' '                , ' '
            FROM INC_PARAM_GENER_PREMI ipgp,  INC_PARAM_NIVEL_PREMI ipnp
            WHERE ipgp.copa_oid_para_gral = lnOidParaGral
              AND ipgp.oid_para_gene_prem = ipnp.pagp_oid_para_gene_prem ;

        END IF;

     END LOOP;
     END IF;
EXIT WHEN clientes%NOTFOUND;
END LOOP;
CLOSE clientes;


EXCEPTION
  WHEN NO_DATA_FOUND THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_CALCU_VENTA: '||ls_sqlerrm);
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_CALCU_VENTA: '||to_char(lnOidClie)||'   '||ls_sqlerrm);
END INC_PR_CALCU_VENTA;


/**************************************************************************
Descripcion        : Recupera la Cantidad de Campañas sobre un rango de Periodos
Fecha Creacion     :  01/03/2011
Parametros Entrada :
           pnOidPeriodoDesde : oid Periodo Desde
           pnOidPeriodoHasta : oid Periodo Hasta

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INC_FN_CALCU_CANTI_PERIO(pnOidPeriodoDesde    NUMBER,
                                  pnOidPeriodoHasta    NUMBER)
RETURN NUMBER IS
  lnCantidad    NUMBER;
BEGIN

  SELECT COUNT(1)
    INTO lnCantidad
    FROM CRA_PERIO desde,
         CRA_PERIO hasta,
         CRA_PERIO periodos
   WHERE desde.oid_peri = pnOidPeriodoDesde
     AND hasta.oid_peri = pnOidPeriodoHasta
     AND periodos.cana_oid_cana = desde.cana_oid_cana
     AND periodos.marc_oid_marc = desde.marc_oid_marc
     AND periodos.pais_oid_pais = desde.pais_oid_pais
     AND periodos.fec_inic >= desde.fec_inic
     AND periodos.fec_fina <= hasta.fec_fina;

  RETURN lnCantidad;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := SUBSTR(SQLERRM,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_CALCU_CANTI_PERIO: '||ls_sqlerrm);

END INC_FN_CALCU_CANTI_PERIO;

/***************************************************************************
Descripcion       : Funcion que la utilizaremos para obtenes la campaña
                    siguiente
Fecha Creacion    : 13/09/2013
   pdOidPeriodo    : Oid del Periodo
   psOidPais       : Oid del Pais
   psNumCampana    : Numero Campaña Siguiente

Autor              : Aurelio Oviedo
***************************************************************************/
FUNCTION INC_FN_OBTIE_CAMPA_SIGUI (pdOidPeriodo NUMBER,
                                   psOidPais NUMBER,
                                   psNumCampanas NUMBER)

RETURN NUMBER IS
    lnCampanaSiguiente      NUMBER(6);

    lnCodPeriodo            SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnIdMarca               SEG_MARCA.OID_MARC%TYPE;
    lnIdCanal               SEG_CANAL.OID_CANA%TYPE;

BEGIN
    lnIdMarca      := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnIdCanal      := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
    lnCodPeriodo   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO3(pdOidPeriodo);

    lnCampanaSiguiente := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lnCodPeriodo, psOidPais, lnIdMarca, lnIdCanal, psNumCampanas);

    RETURN lnCampanaSiguiente;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := SUBSTR(SQLERRM,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_FN_OBTIE_CAMPA_SIGUI: '||ls_sqlerrm);

END INC_FN_OBTIE_CAMPA_SIGUI;

/***************************************************************************
Descripcion       : Genera la data para el Reporte del Programa de
                    Reconocimiento
Fecha Creacion    : 02/10/2013
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE  INC_PR_CARGA_HISTO_PROGR_RECON(
    psNumConcurso   VARCHAR2,
    psOidPeriodo    VARCHAR2,
    psPuntMinimo    NUMBER,
    psPuntMaximo    NUMBER)
 IS

BEGIN
    --Borrando la Tabla Temporal
    DELETE FROM INC_TMP_REPOR_PROGR_RECON;

    --Insertando en la Tabla Temporal
    INSERT INTO INC_TMP_REPOR_PROGR_RECON (
        NUM_CONC,
        COD_REGI,
        DES_REGI,
        COD_ZONA,
        DES_ZONA,
        OID_CLIE,
        COD_CLIE,
        VAL_APE1,
        VAL_APE2,
        VAL_NOM1,
        VAL_NOM2,
        VAL_PUNT,
        VAL_NIVE,
        VAL_RANK)
    select    ac1.*,
              rownum
    from      ( SELECT pc.num_conc,
                       zr.COD_REGI,
                       zr.DES_REGI,
                       zon.cod_zona,
                       zon.DES_ZONA,
                       cli.oid_clie,
                       cli.cod_clie,
                       cli.VAL_APE1,
                       cli.VAL_APE2,
                       cli.VAL_NOM1,
                       cli.VAL_NOM2,
                       pc.PUNTAJE,
                       (SELECT LPA.VAL_DESC_LOTE_PREM_ARTI
                          FROM inc_param_gener_premi ipg,
                               inc_param_nivel_premi ipn,
                               INC_PREMI_ARTIC PAR,
                               INC_LOTE_PREMI_ARTIC LPA
                         where ipn.PAGP_OID_PARA_GENE_PREM = ipg.OID_PARA_GENE_PREM
                           AND PAR.PANP_OID_PARA_NIVE_PREM(+) = iPN.OID_PARA_NIVE_PREM
                           AND LPA.PRAR_OID_PREM_ARTI(+) = PAR.OID_PREM_ARTI
                           and ipg.COPA_OID_PARA_GRAL = pc.oid_para_gral
                           and ipn.num_cant_inic_punt <= pc.PUNTAJE
                           and ipn.num_cant_FINA_punt >= pc.PUNTAJE) Nivel
                  FROM mae_clien cli,
                       mae_clien_datos_adici clia,
                       mae_clien_unida_admin ua,
                       zon_terri_admin za,
                       zon_secci sec,
                       zon_zona zon,
                       zon_terri zt,
                       zon_regio zr,
                           (
                           select    icp.num_conc,
                                     icp.oid_para_gral,
                                     icc.clie_oid_clie,
                                     sum(icc.num_punt) Puntaje
                           from      inc_cuent_corri_punto icc,
                                         inc_concu_param_gener ICP
                           where     icp.num_conc = psNumConcurso
                                 and     icc.COPA_OID_PARA_GRAL = ICP.OID_PARA_GRAL
                             and     icc.perd_oid_peri <= TO_NUMBER(psOidPeriodo)
                             and     exists (
                                       select    *
                                       from      inc_candi_ganad icg
                                       where     icc.clie_oid_clie = icg.clie_oid_clie
                                       and       icg.copa_oid_para_gral = icp.oid_para_gral
                                       and       icg.BINC_OID_BASE_INCU is null
                                     )
                             and     not exists (
                                       select    *
                                       from      inc_candi_ganad icg
                                       where     icc.clie_oid_clie = icg.clie_oid_clie
                                       and       icg.copa_oid_para_gral = icp.oid_para_gral
                                       and       icg.BINC_OID_BASE_INCU is not null
                                     )
                             and     not exists	(
                                       select    *
                                       from      inc_desca ids
                                       where     icc.clie_oid_clie = ids.clie_oid_clie
                                       and       ids.copa_oid_para_gral = icp.oid_para_gral
                                     )
                           group by  icp.num_conc, icp.oid_para_gral, clie_oid_clie
                           having    sum(icc.num_punt) >= psPuntMinimo
                              and    sum(icc.num_punt) <= psPuntMaximo
                           ) pc
                WHERE    cli.oid_clie = pc.clie_oid_clie
                  AND    cli.oid_clie = clia.clie_oid_clie
                  and    clia.ind_acti = 1
                  AND    cli.oid_clie = ua.clie_oid_clie
                  AND    ua.ztad_oid_terr_admi = za.oid_terr_admi
                  AND    za.terr_oid_terr = zt.oid_terr
                  AND    za.zscc_oid_secc = sec.oid_secc
                  AND    sec.zzon_oid_zona = zon.oid_zona
                  and    zon.ZORG_OID_REGI = zr.OID_REGI
                  AND    ua.ind_acti = 1
                  AND    ZON.IND_ACTI = 1
                  AND    ZON.IND_BORR =0
                  AND    SEC.IND_ACTI = 1
                  AND    SEC.IND_BORR =0
                  AND    ZT.IND_BORR = 0
                  AND    ZR.IND_ACTI = 1
                  AND    ZR.IND_BORR = 0
                order by pc.puntaje desc
              ) ac1;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_CARGA_HISTO_PROGR_RECON: '||ls_sqlerrm);

END INC_PR_CARGA_HISTO_PROGR_RECON;

/***************************************************************************
Descripcion       : Genera la data para el Reporte del Programa de
                    Reconocimiento COLOMBIA
Fecha Creacion    : 03/10/2013
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE  INC_PR_HISTO_PROGR_RECON_COLOM(
    psNumConcurso   VARCHAR2,
    psOidPeriodo    VARCHAR2,
    psPuntMinimo    NUMBER,
    psPuntMaximo    NUMBER)
 IS

BEGIN
    --Borrando la Tabla Temporal
    DELETE FROM INC_TMP_REPOR_PROGR_RECON;

    --Insertando en la Tabla Temporal
    INSERT INTO INC_TMP_REPOR_PROGR_RECON (
        NUM_CONC,
        COD_REGI,
        DES_REGI,
        COD_ZONA,
        DES_ZONA,
        OID_CLIE,
        COD_CLIE,
        VAL_APE1,
        VAL_APE2,
        VAL_NOM1,
        VAL_NOM2,
        NRO_DOCUMENTO,
        VAL_PUNT,
        VAL_NIVE,
        VAL_RANK)
    select    ac1.*,
              rownum
    from      ( SELECT pc.num_conc,
                       zr.COD_REGI,
                       zr.DES_REGI,
                       zon.cod_zona,
                       zon.DES_ZONA,
                       cli.oid_clie,
                       cli.cod_clie,
                       cli.VAL_APE1,
                       cli.VAL_APE2,
                       cli.VAL_NOM1,
                       cli.VAL_NOM2,
                       (SELECT num_docu_iden FROM MAE_CLIEN_IDENT  WHERE clie_oid_clie = cli.oid_clie AND val_iden_docu_prin = 1
                       AND  rownum =1) NRO_DOCUMENTO,
                       pc.PUNTAJE,
                       (SELECT LPA.VAL_DESC_LOTE_PREM_ARTI
                          FROM inc_param_gener_premi ipg,
                               inc_param_nivel_premi ipn,
                               INC_PREMI_ARTIC PAR,
                               INC_LOTE_PREMI_ARTIC LPA
                         where ipn.PAGP_OID_PARA_GENE_PREM = ipg.OID_PARA_GENE_PREM
                           AND PAR.PANP_OID_PARA_NIVE_PREM(+) = iPN.OID_PARA_NIVE_PREM
                           AND LPA.PRAR_OID_PREM_ARTI(+) = PAR.OID_PREM_ARTI
                           and ipg.COPA_OID_PARA_GRAL = pc.oid_para_gral
                           and ipn.num_cant_inic_punt <= pc.PUNTAJE
                           and ipn.num_cant_FINA_punt >= pc.PUNTAJE) Nivel
                  FROM mae_clien cli,
                       mae_clien_datos_adici clia,
                       mae_clien_unida_admin ua,
                       zon_terri_admin za,
                       zon_secci sec,
                       zon_zona zon,
                       zon_terri zt,
                       zon_regio zr,
                           (SELECT ganad.clie_oid_clie , MIN(perd_oid_peri_desd) per_ini
                              FROM inc_concu_param_gener icpg,
                                   inc_candi_ganad ganad
                             WHERE ganad.COPA_OID_PARA_GRAL = icpg.OID_PARA_GRAL
                               AND icpg.num_conc =  psNumConcurso
                             GROUP BY ganad.clie_oid_clie) cini,
                           (
                           select    icp.num_conc,
                                     icp.oid_para_gral,
                                     icc.clie_oid_clie,
                                     icp.perd_oid_peri_desd,
                                     icp.perd_oid_peri_hast,
                                     sum(icc.num_punt) Puntaje
                           from      inc_cuent_corri_punto icc,
                                     inc_concu_param_gener ICP
                           where     icp.num_conc = psNumConcurso
                             and     icc.COPA_OID_PARA_GRAL = ICP.OID_PARA_GRAL
                             and     icc.perd_oid_peri <= TO_NUMBER(psOidPeriodo)
                             and     exists (
                                       select    *
                                       from      inc_candi_ganad icg
                                       where     icc.clie_oid_clie = icg.clie_oid_clie
                                       and       icg.copa_oid_para_gral = icp.oid_para_gral
                                       and       icg.BINC_OID_BASE_INCU is null
                                     )
                             and     not exists (
                                       select    *
                                       from      inc_candi_ganad icg
                                       where     icc.clie_oid_clie = icg.clie_oid_clie
                                       and       icg.copa_oid_para_gral = icp.oid_para_gral
                                       and       icg.BINC_OID_BASE_INCU is not null
                                     )
                             and     not exists	(
                                       select    *
                                       from      inc_desca ids
                                       where     icc.clie_oid_clie = ids.clie_oid_clie
                                       and       ids.copa_oid_para_gral = icp.oid_para_gral
                                     )
                           group by  icp.num_conc, icp.oid_para_gral, clie_oid_clie, icp.perd_oid_peri_desd, icp.perd_oid_peri_hast
                           having    sum(icc.num_punt) >= psPuntMinimo
                              and    sum(icc.num_punt) <= psPuntMaximo
                           ) pc
                WHERE    cli.oid_clie = pc.clie_oid_clie
                  AND    cli.oid_clie = clia.clie_oid_clie
                  and    clia.ind_acti = 1
                  AND    cli.oid_clie = ua.clie_oid_clie
                  AND    ua.ztad_oid_terr_admi = za.oid_terr_admi
                  AND    za.terr_oid_terr = zt.oid_terr
                  AND    za.zscc_oid_secc = sec.oid_secc
                  AND    sec.zzon_oid_zona = zon.oid_zona
                  AND    zon.ZORG_OID_REGI = zr.OID_REGI
                  AND    cini.clie_oid_clie = pc.clie_oid_clie
                  AND    ((SELECT count(*)
                             FROM ped_solic_cabec_acum2 sca2
                            WHERE sca2.clie_oid_clie = pc.clie_oid_clie
                              AND sca2.perd_oid_peri between cini.per_ini
                              AND (CASE WHEN TO_NUMBER(psOidPeriodo) <= pc.PERD_OID_PERI_HAST THEN
                                        TO_NUMBER(psOidPeriodo)
                                    ELSE
                                        perd_oid_peri_hast END))  -
                              (SELECT COUNT(*) FROM cra_perio cra where cra.oid_peri
                               BETWEEN cini.per_ini AND (CASE
                                                            WHEN TO_NUMBER(psOidPeriodo) <= pc.PERD_OID_PERI_HAST THEN
                                                                 TO_NUMBER(psOidPeriodo)
                                                            ELSE pc.perd_oid_peri_hast
                                                            END )) = 0)
                  AND    ua.ind_acti = 1
                  AND    ZON.IND_ACTI = 1
                  AND    ZON.IND_BORR =0
                  AND    SEC.IND_ACTI = 1
                  AND    SEC.IND_BORR =0
                  AND    ZT.IND_BORR = 0
                  AND    ZR.IND_ACTI = 1
                  AND    ZR.IND_BORR = 0
                order by pc.puntaje desc
              ) ac1;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_HISTO_PROGR_RECON_COLOM: '||ls_sqlerrm);

END INC_PR_HISTO_PROGR_RECON_COLOM;

/***************************************************************************
Descripcion       : Genera la data para el Reporte de cupones electronicos

Fecha Creacion    : 06/11/2013
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE INC_PR_REPOR_CUPON_ELECT(
    psNumeroConcurso VARCHAR2,
    psUsuario VARCHAR2
) IS
    lsFlagRegionesZonas VARCHAR2(1);
BEGIN

    --Verificamos si existen zonas/Regiones
    SELECT DECODE(COUNT(*), 0, '0', '1')
    INTO lsFlagRegionesZonas
    FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R';

    --Borrando la Tabla
    DELETE FROM INC_TEMPO_REPOR_CUPON_ELECT WHERE USU_REGI = psUsuario;

    --Insertando en la Tabla
        INSERT INTO INC_TEMPO_REPOR_CUPON_ELECT(
            DES_REGI,
            COD_ZONA,
            VAL_CAMP,
            NUM_DOCU_IDEN,
            COD_CLIE,
            NOM_APEL,
            NUM_TELE_FIJO,
            NUM_CUPO,
            USU_REGI
        )
        SELECT
                         zr.des_regi REGION,
                         zon.cod_zona ZONA,
                         pcup.cod_peri_proc CAMPANIA,
                         mi.num_docu_iden DOC_IDENTIDAD,
                         pcup.cod_clie CODIGO_CLIENTE,
                            cli.val_nom1
                         || ' '
                         || cli.val_nom2
                         || ' '
                         || cli.val_ape1
                         || ' '
                         || cli.val_ape2
                            NOMBRES_APELLIDOS,
                         (SELECT com.val_text_comu
                            FROM MAE_CLIEN_COMUN com, MAE_TIPO_COMUN tip
                           WHERE     com.ticm_oid_tipo_comu = tip.oid_tipo_comu
                                 AND com.clie_oid_clie = cli.oid_clie
                                 AND tip.cod_tipo_comu = 'TF')
                            TELEFONO_FIJO,
                         pcup.num_cupo NUM_CUPON,
                         psUsuario
                    FROM inc_cupon_elect pcup,
                         mae_clien cli,
                         mae_clien_datos_adici mcda,
                         mae_clien_unida_admin ua,
                         zon_terri_admin za,
                         zon_secci sec,
                         zon_zona zon,
                         zon_terri zt,
                         zon_regio zr,
                         mae_clien_ident mi
                   WHERE     1 = 1
                         AND pcup.num_conc = psNumeroConcurso
                         AND pcup.cod_clie = cli.cod_clie
                         AND cli.oid_clie = ua.clie_oid_clie
                         AND cli.oid_clie = mcda.clie_oid_clie
                         AND mcda.ind_acti = 1
                         AND ua.ztad_oid_terr_admi = za.oid_terr_admi
                         AND za.terr_oid_terr = zt.oid_terr
                         AND za.zscc_oid_secc = sec.oid_secc
                         AND sec.zzon_oid_zona = zon.oid_zona
                         AND zon.ZORG_OID_REGI = zr.OID_REGI
                         AND ua.ind_acti = 1
                         AND ZON.IND_ACTI = 1
                         AND ZON.IND_BORR = 0
                         AND SEC.IND_ACTI = 1
                         AND SEC.IND_BORR = 0
                         AND ZT.IND_BORR = 0
                         AND ZR.IND_ACTI = 1
                         AND ZR.IND_BORR = 0
                         AND cli.oid_clie = mi.clie_oid_clie
                         AND mi.val_iden_docu_prin = 1
                         AND ((lsFlagRegionesZonas = '1' AND ZON.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0')
                ORDER BY zr.des_regi,
                         zon.cod_zona,
                         pcup.cod_peri_proc,
                         pcup.cod_clie,
                         pcup.num_cupo;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_REPOR_CUPON_ELECT: '||ls_sqlerrm);

END INC_PR_REPOR_CUPON_ELECT;

/***************************************************************************
Descripcion       : Genera el Reporte de cupones electronicos

Fecha Creacion    : 06/11/2013
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE INC_PR_GENER_REPOR_CUPON_ELECT(
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psUsuario VARCHAR2,
    psdirectorio             OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas           number := 5000;
    v_handle       utl_file.file_type;
    lslinea            varchar2(4000);

    CURSOR C_REPOR_CUPON_ELECT IS
    SELECT
        DES_REGI,
        COD_ZONA,
        VAL_CAMP,
        NUM_DOCU_IDEN,
        COD_CLIE,
        NOM_APEL,
        NUM_TELE_FIJO,
        NUM_CUPO
        FROM INC_TEMPO_REPOR_CUPON_ELECT
        WHERE USU_REGI = psUsuario;

        TYPE cuponElectReg IS RECORD(
            nombreRegion INC_TEMPO_REPOR_CUPON_ELECT.DES_REGI%TYPE,
            codigoZona INC_TEMPO_REPOR_CUPON_ELECT.COD_ZONA%TYPE,
            campania INC_TEMPO_REPOR_CUPON_ELECT.VAL_CAMP%TYPE,
            numeroDocumento INC_TEMPO_REPOR_CUPON_ELECT.NUM_DOCU_IDEN%TYPE,
            codigoCliente INC_TEMPO_REPOR_CUPON_ELECT.COD_CLIE%TYPE,
            nombre INC_TEMPO_REPOR_CUPON_ELECT.NOM_APEL%TYPE,
            telefonoFijo INC_TEMPO_REPOR_CUPON_ELECT.NUM_TELE_FIJO%TYPE,
            cupon INC_TEMPO_REPOR_CUPON_ELECT.NUM_CUPO%TYPE
        );

        TYPE cuponElectTab IS TABLE OF cuponElectReg;
        cuponElectRecord cuponElectTab;

   lbAbrirUtlFile  BOOLEAN;

BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_CUPON_ELECT;
      LOOP
       FETCH C_REPOR_CUPON_ELECT BULK COLLECT INTO cuponElectRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF cuponElectRecord.COUNT > 0 THEN
          FOR x IN cuponElectRecord.FIRST .. cuponElectRecord.LAST LOOP
                lslinea :=
                                '"'|| cuponElectRecord(x).nombreRegion || '"' || ',' ||
                                '=T("'|| cuponElectRecord(x).codigoZona || '")' || ',' ||
                                '=T("'|| cuponElectRecord(x).campania || '")' || ',' ||
                                '=T("'|| cuponElectRecord(x).numeroDocumento || '")' || ',' ||
                                '=T("'|| cuponElectRecord(x).codigoCliente || '")' || ',' ||
                                '"'|| replace(cuponElectRecord(x).nombre,',',' ') || '"' || ',' ||
                                '=T("'|| cuponElectRecord(x).telefonoFijo || '")' || ',' ||
                                '=T("'|| cuponElectRecord(x).cupon || '")';

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_CUPON_ELECT%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_CUPON_ELECT;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_GENER_REPOR_CUPON_ELECT: '||ls_sqlerrm);

END INC_PR_GENER_REPOR_CUPON_ELECT;

/***************************************************************************
Descripcion       : Genera el Reporte Puntos Obtenidos de Bolsa Faltantes
Fecha Creacion    : 28/01/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_REPOR_PUNTO_BOLSA_FALTA(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);
    lnPosicion      number;

    CURSOR C_REPOR_BOLSA_FALTA IS
    SELECT
        COD_REGI,
        DES_REGI,
        COD_ZONA,
        DES_ZONA,
        COD_TERR,
        COD_SECC,
        NUM_CONC,
        VAL_NOMB,
        TIP_DOCU,
        NUM_DOCU,
        COD_CLIE,
        NOM_CLIE,
        NUM_TELE,
        NUM_CELU,
        VAL_DIRE,
        COD_SAP,
        DES_PROD,
        VAL_TOTA,
        FEC_ASIG,
        FEC_SOLU,
        VAL_OBSE,
        VAL_NUME_SOLI,
        COD_VTA,
        NUM_UNID,
        VAL_PREC_UNIT,
        VAL_PREC_TOTA,
        VAL_ESTA,
        COD_CAMP,
        FEC_FACT,
        TIP_SOLI
     FROM INC_TMP_PUNTO_BOLSA_FALTA
    ORDER BY 6,7,1,3,10,14,16;

     TYPE detalleUnidadesReg IS RECORD(
         codRegion     INC_TMP_PUNTO_BOLSA_FALTA.COD_REGI%TYPE,
         region        INC_TMP_PUNTO_BOLSA_FALTA.DES_REGI%TYPE,
         codZona       INC_TMP_PUNTO_BOLSA_FALTA.COD_ZONA%TYPE,
         zona          INC_TMP_PUNTO_BOLSA_FALTA.DES_ZONA%TYPE,
         territorio    INC_TMP_PUNTO_BOLSA_FALTA.COD_TERR%TYPE,
         seccion       INC_TMP_PUNTO_BOLSA_FALTA.COD_SECC%TYPE,
         numConcurso   INC_TMP_PUNTO_BOLSA_FALTA.NUM_CONC%TYPE,
         nombre        INC_TMP_PUNTO_BOLSA_FALTA.VAL_NOMB%TYPE,
         tipoDocu      INC_TMP_PUNTO_BOLSA_FALTA.TIP_DOCU%TYPE,
         numDocu       INC_TMP_PUNTO_BOLSA_FALTA.NUM_DOCU%TYPE,
         codCliente    INC_TMP_PUNTO_BOLSA_FALTA.COD_CLIE%TYPE,
         nomCliente    INC_TMP_PUNTO_BOLSA_FALTA.NOM_CLIE%TYPE,
         telefono      INC_TMP_PUNTO_BOLSA_FALTA.NUM_TELE%TYPE,
         celular       INC_TMP_PUNTO_BOLSA_FALTA.NUM_CELU%TYPE,
         direccion     INC_TMP_PUNTO_BOLSA_FALTA.VAL_DIRE%TYPE,
         codigoSAP     INC_TMP_PUNTO_BOLSA_FALTA.COD_SAP%TYPE,
         producto      INC_TMP_PUNTO_BOLSA_FALTA.DES_PROD%TYPE,
         total         INC_TMP_PUNTO_BOLSA_FALTA.VAL_TOTA%TYPE,
         fecAsig       INC_TMP_PUNTO_BOLSA_FALTA.FEC_ASIG%TYPE,
         fecSolu       INC_TMP_PUNTO_BOLSA_FALTA.FEC_SOLU%TYPE,
         observaciones INC_TMP_PUNTO_BOLSA_FALTA.VAL_OBSE%TYPE,
         pedido        INC_TMP_PUNTO_BOLSA_FALTA.VAL_NUME_SOLI%TYPE,
         codVenta      INC_TMP_PUNTO_BOLSA_FALTA.COD_VTA%TYPE,
         unidades      INC_TMP_PUNTO_BOLSA_FALTA.NUM_UNID%TYPE,
         precioUnitario INC_TMP_PUNTO_BOLSA_FALTA.VAL_PREC_UNIT%TYPE,
         precioTotal   INC_TMP_PUNTO_BOLSA_FALTA.VAL_PREC_TOTA%TYPE,
         estatus       INC_TMP_PUNTO_BOLSA_FALTA.VAL_ESTA%TYPE,
         campana       INC_TMP_PUNTO_BOLSA_FALTA.COD_CAMP%TYPE,
         fechaFacturacion INC_TMP_PUNTO_BOLSA_FALTA.FEC_FACT%TYPE,
         tipoSolicitud INC_TMP_PUNTO_BOLSA_FALTA.TIP_SOLI%TYPE
      );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lnPosicion :=1;
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_BOLSA_FALTA;
      LOOP
       FETCH C_REPOR_BOLSA_FALTA BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                lslinea :=     '"'|| lnPosicion || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codRegion || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).region || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codZona || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).zona || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).territorio || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).seccion || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).numConcurso || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).nombre || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).tipoDocu || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).numDocu || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codCliente || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).nomCliente || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).telefono || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).celular || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).direccion || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codigoSAP || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).producto || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).total || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).fecAsig || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).fecSolu || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).observaciones || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).pedido || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).codVenta || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).unidades || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).precioUnitario || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).precioTotal || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).estatus || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).campana || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).fechaFacturacion || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).tipoSolicitud || '"';

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                 lnPosicion := lnPosicion + 1;
          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_BOLSA_FALTA%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_BOLSA_FALTA;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_REPOR_PUNTO_BOLSA_FALTA: '||ls_sqlerrm);

END INC_PR_REPOR_PUNTO_BOLSA_FALTA;

/***************************************************************************
Descripcion       : Genera el Reporte Puntaje Obtenidos y Puntahe Faltantes
Fecha Creacion    : 10/04/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INC_PR_REPOR_POBTE_PFALT_CSV(
    pscodigopais       VARCHAR2,
    pstipoReporte      VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);
    lnPosicion      number;

    CURSOR CFALTA IS
    SELECT
      COD_REGI,
      DES_REGI,
      COD_ZONA,
      DES_ZONA,
      COD_SECC,
      COD_TERR,
      NUM_CONC,
      VAL_NOMB_CONC,
      COD_CLIE,
      VAL_NOMB_CLIE,
      VAL_TIPO_DOCU,
      NUM_DOCU_IDEN,
      VAL_NUME_CELU,
      VAL_DIRE,
      VAL_PUNT,
      NUM_NIVE,
      VAL_PUNT_FALT,
      VAL_ESTA
    FROM INC_TMP_REPOR_POBTE_PFALT
    ORDER BY
      COD_REGI,
      COD_ZONA,
      COD_TERR,
      COD_CLIE,
      NUM_NIVE  ;

     TYPE detalleUnidadesReg IS RECORD(
         codRegion                    INC_TMP_REPOR_POBTE_PFALT.COD_REGI%TYPE,
         region                       INC_TMP_REPOR_POBTE_PFALT.DES_REGI%TYPE,
         codZona                      INC_TMP_REPOR_POBTE_PFALT.COD_ZONA%TYPE,
         zona                         INC_TMP_REPOR_POBTE_PFALT.DES_ZONA%TYPE,
         codSecc                      INC_TMP_REPOR_POBTE_PFALT.COD_SECC%TYPE,
         territorio                   INC_TMP_REPOR_POBTE_PFALT.COD_TERR%TYPE,
         numConcurso                  INC_TMP_REPOR_POBTE_PFALT.NUM_CONC%TYPE,
         nombre                       INC_TMP_REPOR_POBTE_PFALT.VAL_NOMB_CONC%TYPE,
         codCliente                   INC_TMP_REPOR_POBTE_PFALT.COD_CLIE%TYPE,
         nomCliente                   INC_TMP_REPOR_POBTE_PFALT.VAL_NOMB_CLIE%TYPE,
         tipoDocu                     INC_TMP_REPOR_POBTE_PFALT.VAL_TIPO_DOCU%TYPE,
         numDocu                      INC_TMP_REPOR_POBTE_PFALT.NUM_DOCU_IDEN%TYPE,
         telefono                     INC_TMP_REPOR_POBTE_PFALT.VAL_NUME_CELU%TYPE,
         direccion                    INC_TMP_REPOR_POBTE_PFALT.VAL_DIRE%TYPE,
         puntaje                      INC_TMP_REPOR_POBTE_PFALT.VAL_PUNT%TYPE,
         nivel                        INC_TMP_REPOR_POBTE_PFALT.NUM_NIVE%TYPE,
         puntajeFaltante              INC_TMP_REPOR_POBTE_PFALT.VAL_PUNT_FALT%TYPE,
         estado                       INC_TMP_REPOR_POBTE_PFALT.VAL_ESTA%TYPE
      );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

    CURSOR CEXIGIDO IS
    SELECT
      COD_REGI,
      DES_REGI,
      COD_ZONA,
      DES_ZONA,
      COD_SECC,
      COD_TERR,
      NUM_CONC,
      VAL_NOMB_CONC,
      COD_CLIE,
      VAL_NOMB_CLIE,
      VAL_TIPO_DOCU,
      NUM_DOCU_IDEN,
      VAL_NUME_CELU,
      VAL_DIRE,
      VAL_PUNT,
      VAL_PUNT_EXIG,
      NUM_NIVE,
      VAL_PUNT_FALT,
      VAL_PUNT_FALT_EXIG,
      VAL_ESTA
    FROM INC_TMP_REPOR_POBTE_PFALT
    ORDER BY
      COD_REGI,
      COD_ZONA,
      COD_TERR,
      COD_CLIE,
      NUM_NIVE  ;

     TYPE detalleExigido IS RECORD(
         codRegion                    INC_TMP_REPOR_POBTE_PFALT.COD_REGI%TYPE,
         region                       INC_TMP_REPOR_POBTE_PFALT.DES_REGI%TYPE,
         codZona                      INC_TMP_REPOR_POBTE_PFALT.COD_ZONA%TYPE,
         zona                         INC_TMP_REPOR_POBTE_PFALT.DES_ZONA%TYPE,
         codSecc                      INC_TMP_REPOR_POBTE_PFALT.COD_SECC%TYPE,
         territorio                   INC_TMP_REPOR_POBTE_PFALT.COD_TERR%TYPE,
         numConcurso                  INC_TMP_REPOR_POBTE_PFALT.NUM_CONC%TYPE,
         nombre                       INC_TMP_REPOR_POBTE_PFALT.VAL_NOMB_CONC%TYPE,
         codCliente                   INC_TMP_REPOR_POBTE_PFALT.COD_CLIE%TYPE,
         nomCliente                   INC_TMP_REPOR_POBTE_PFALT.VAL_NOMB_CLIE%TYPE,
         telefono                     INC_TMP_REPOR_POBTE_PFALT.VAL_NUME_CELU%TYPE,
         tipoDocu                     INC_TMP_REPOR_POBTE_PFALT.VAL_TIPO_DOCU%TYPE,
         numDocu                      INC_TMP_REPOR_POBTE_PFALT.NUM_DOCU_IDEN%TYPE,
         direccion                    INC_TMP_REPOR_POBTE_PFALT.VAL_DIRE%TYPE,
         puntaje                      INC_TMP_REPOR_POBTE_PFALT.VAL_PUNT%TYPE,
         puntajeExigido               INC_TMP_REPOR_POBTE_PFALT.VAL_PUNT_EXIG%TYPE,
         nivel                        INC_TMP_REPOR_POBTE_PFALT.NUM_NIVE%TYPE,
         puntajeFaltante              INC_TMP_REPOR_POBTE_PFALT.VAL_PUNT_FALT%TYPE,
         puntajeFaltanteExigido       INC_TMP_REPOR_POBTE_PFALT.VAL_PUNT_FALT_EXIG%TYPE,
         estado                       INC_TMP_REPOR_POBTE_PFALT.VAL_ESTA%TYPE
      );

     TYPE detalleExigidoTab IS TABLE OF detalleExigido;
     detalleExigidoRecord detalleExigidoTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lnPosicion :=1;
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    IF pstipoReporte = '1' THEN
       OPEN CFALTA;
          LOOP
           FETCH CFALTA BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                    lslinea :=      '=T("'|| detalleUnidadesRegRecord(x).codRegion || '")' || ',' ||
                                    '"'|| detalleUnidadesRegRecord(x).region || '"' || ',' ||
                                    '=T("'|| detalleUnidadesRegRecord(x).codZona || '")' || ',' ||
                                    '"'|| detalleUnidadesRegRecord(x).zona || '"' || ',' ||
                                    '"'|| detalleUnidadesRegRecord(x).codSecc || '"' || ',' ||
                                    '=T("'|| detalleUnidadesRegRecord(x).territorio || '")' || ',' ||
                                    '=T("'|| detalleUnidadesRegRecord(x).numConcurso || '")' || ',' ||
                                    '"'|| detalleUnidadesRegRecord(x).nombre || '"' || ',' ||
                                    '=T("'|| detalleUnidadesRegRecord(x).codCliente || '")' || ',' ||
                                    '"'|| detalleUnidadesRegRecord(x).nomCliente || '"' || ',' ||
                                     '"'|| detalleUnidadesRegRecord(x).tipoDocu || '"' || ',' ||
                                    '=T("'|| detalleUnidadesRegRecord(x).numDocu || '")' || ',' ||
                                    '=T("'|| detalleUnidadesRegRecord(x).telefono || '")' || ',' ||
                                    '=T("'|| detalleUnidadesRegRecord(x).direccion || '")' || ',' ||
                                    '"'|| detalleUnidadesRegRecord(x).puntaje || '"' || ',' ||
                                    '"'|| detalleUnidadesRegRecord(x).nivel || '"' || ',' ||
                                    '"'|| detalleUnidadesRegRecord(x).puntajeFaltante || '"' || ',' ||
                                    '"'|| detalleUnidadesRegRecord(x).estado || '"';

                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                     lnPosicion := lnPosicion + 1;
              END LOOP;
            END IF;
            EXIT WHEN CFALTA%NOTFOUND;
         END LOOP;
        CLOSE CFALTA;
    ELSE

        OPEN CEXIGIDO;
          LOOP
           FETCH CEXIGIDO BULK COLLECT INTO detalleExigidoRecord LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleExigidoRecord.COUNT > 0 THEN
              FOR x IN detalleExigidoRecord.FIRST .. detalleExigidoRecord.LAST LOOP
                    lslinea :=      '=T("'|| detalleExigidoRecord(x).codRegion || '")' || ',' ||
                                    '"'|| detalleExigidoRecord(x).region || '"' || ',' ||
                                    '=T("'|| detalleExigidoRecord(x).codZona || '")' || ',' ||
                                    '"'|| detalleExigidoRecord(x).zona || '"' || ',' ||
                                    '"'|| detalleExigidoRecord(x).codSecc || '"' || ',' ||
                                    '=T("'|| detalleExigidoRecord(x).territorio || '")' || ',' ||
                                    '=T("'|| detalleExigidoRecord(x).numConcurso || '")' || ',' ||
                                    '"'|| detalleExigidoRecord(x).nombre || '"' || ',' ||
                                    '=T("'|| detalleExigidoRecord(x).codCliente || '")' || ',' ||
                                    '"'|| detalleExigidoRecord(x).nomCliente || '"' || ',' ||
                                     '"'|| detalleExigidoRecord(x).tipoDocu || '"' || ',' ||
                                    '=T("'|| detalleExigidoRecord(x).numDocu || '")' || ',' ||
                                    '=T("'|| detalleExigidoRecord(x).telefono || '")' || ',' ||
                                    '=T("'|| detalleExigidoRecord(x).direccion || '")' || ',' ||
                                    '"'|| detalleExigidoRecord(x).puntaje || '"' || ',' ||
                                    '"'|| detalleExigidoRecord(x).puntajeExigido || '"' || ',' ||
                                    '"'|| detalleExigidoRecord(x).nivel || '"' || ',' ||
                                    '"'|| detalleExigidoRecord(x).puntajeFaltante || '"' || ',' ||
                                    '"'|| detalleExigidoRecord(x).puntajeFaltanteExigido || '"' || ',' ||
                                    '"'|| detalleExigidoRecord(x).estado || '"';

                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                     lnPosicion := lnPosicion + 1;
              END LOOP;
            END IF;
            EXIT WHEN CEXIGIDO%NOTFOUND;
         END LOOP;
        CLOSE CEXIGIDO;
     END IF;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_REPOR_POBTE_PFALT_CSV: '||ls_sqlerrm);

END INC_PR_REPOR_POBTE_PFALT_CSV;

/***************************************************************************
Descripcion       : Genera el Reporte Puntos Consultora
Fecha Creacion    : 28/05/2014
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_REPOR_PUNTO_CONSU_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_PUNTA_CONSU IS
    SELECT
        DES_REGI,
        COD_ZONA,
        COD_SECC,
        COD_TERR,
        COD_CLIE,
        VAL_CEDU,
        NOM_CONS,
        VAL_CIUD,
        VAL_TELE,
        VAL_MOVI,
        NUM_PUNT_VD,
        NUM_PUNT_RETA,
        NUM_PUNT_BONI,
        NUM_DESC_CDRS,
        NUM_ABON_CARG,
        NUM_TOTA_PUNT
     FROM INC_TMP_REPOR_PUNTO_CONSU
    ORDER BY 1,2,3;

     TYPE detalleUnidadesReg IS RECORD(
         region        INC_TMP_REPOR_PUNTO_CONSU.DES_REGI%TYPE,
         codZona       INC_TMP_REPOR_PUNTO_CONSU.COD_ZONA%TYPE,
         seccion       INC_TMP_REPOR_PUNTO_CONSU.COD_SECC%TYPE,
         territorio    INC_TMP_REPOR_PUNTO_CONSU.COD_TERR%TYPE,
         codCliente    INC_TMP_REPOR_PUNTO_CONSU.COD_CLIE%TYPE,
         cedula        INC_TMP_REPOR_PUNTO_CONSU.VAL_CEDU%TYPE,
         nombres       INC_TMP_REPOR_PUNTO_CONSU.NOM_CONS%TYPE,
         ciudad        INC_TMP_REPOR_PUNTO_CONSU.VAL_CIUD%TYPE,
         telefono      INC_TMP_REPOR_PUNTO_CONSU.VAL_TELE%TYPE,
         celular       INC_TMP_REPOR_PUNTO_CONSU.VAL_MOVI%TYPE,
         puntajeVD     INC_TMP_REPOR_PUNTO_CONSU.NUM_PUNT_VD%TYPE,
         puntajeRetail INC_TMP_REPOR_PUNTO_CONSU.NUM_PUNT_RETA%TYPE,
         puntajeBoni   INC_TMP_REPOR_PUNTO_CONSU.NUM_PUNT_BONI%TYPE,
         descuentoCDRS INC_TMP_REPOR_PUNTO_CONSU.NUM_DESC_CDRS%TYPE,
         abonoCargos   INC_TMP_REPOR_PUNTO_CONSU.NUM_ABON_CARG%TYPE,
         totalPuntaje  INC_TMP_REPOR_PUNTO_CONSU.NUM_TOTA_PUNT%TYPE
      );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_PUNTA_CONSU;
      LOOP
       FETCH C_REPOR_PUNTA_CONSU BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                lslinea :=     '"'|| detalleUnidadesRegRecord(x).region || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codZona || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).seccion || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).territorio || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codCliente || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).cedula || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).nombres || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).ciudad || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).telefono || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).celular || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).puntajeVD || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).puntajeRetail || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).puntajeBoni || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).descuentoCDRS || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).abonoCargos || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).totalPuntaje || '")';

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_PUNTA_CONSU%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_PUNTA_CONSU;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_REPOR_PUNTO_CONSU_CSV: '||ls_sqlerrm);

END INC_PR_REPOR_PUNTO_CONSU_CSV;

/***************************************************************************
Descripcion       : Genera el Reporte Puntos Campania
Fecha Creacion    : 09/06/2014
Autor             : Sebastian Guerra
***************************************************************************/
PROCEDURE INC_PR_REPOR_PUNTO_CAMPA_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_PUNTA_CAMPA IS
    SELECT
        DES_REGI,
        COD_ZONA,
        COD_SECC,
        COD_TERR,
        COD_CLIE,
        VAL_CEDU,
        NOM_CONS,
        VAL_CIUD,
        VAL_TELE,
        VAL_MOVI,
        COD_PERI,
        NUM_PUNT_VD,
        NUM_PUNT_RETA,
        NUM_PUNT_BONI,
        NUM_DESC_CDRS,
        NUM_ABON_CARG,
        NUM_TOTA_PUNT,
        NUM_TOTA
     FROM INC_TMP_REPOR_PUNTO_CAMPA
    ORDER BY 1,2,3;

     TYPE detalleUnidadesReg IS RECORD(
         region        INC_TMP_REPOR_PUNTO_CAMPA.DES_REGI%TYPE,
         codZona       INC_TMP_REPOR_PUNTO_CAMPA.COD_ZONA%TYPE,
         seccion       INC_TMP_REPOR_PUNTO_CAMPA.COD_SECC%TYPE,
         territorio    INC_TMP_REPOR_PUNTO_CAMPA.COD_TERR%TYPE,
         codCliente    INC_TMP_REPOR_PUNTO_CAMPA.COD_CLIE%TYPE,
         cedula        INC_TMP_REPOR_PUNTO_CAMPA.VAL_CEDU%TYPE,
         nombres       INC_TMP_REPOR_PUNTO_CAMPA.NOM_CONS%TYPE,
         ciudad        INC_TMP_REPOR_PUNTO_CAMPA.VAL_CIUD%TYPE,
         telefono      INC_TMP_REPOR_PUNTO_CAMPA.VAL_TELE%TYPE,
         celular       INC_TMP_REPOR_PUNTO_CAMPA.VAL_MOVI%TYPE,
         campania     INC_TMP_REPOR_PUNTO_CAMPA.COD_PERI%TYPE,
         puntajeVD     INC_TMP_REPOR_PUNTO_CAMPA.NUM_PUNT_VD%TYPE,
         puntajeRetail INC_TMP_REPOR_PUNTO_CAMPA.NUM_PUNT_RETA%TYPE,
         puntajeBoni   INC_TMP_REPOR_PUNTO_CAMPA.NUM_PUNT_BONI%TYPE,
         descuentoCDRS INC_TMP_REPOR_PUNTO_CAMPA.NUM_DESC_CDRS%TYPE,
         abonoCargos   INC_TMP_REPOR_PUNTO_CAMPA.NUM_ABON_CARG%TYPE,
         totalPuntaje  INC_TMP_REPOR_PUNTO_CAMPA.NUM_TOTA_PUNT%TYPE,
         total  INC_TMP_REPOR_PUNTO_CAMPA.NUM_TOTA%TYPE
      );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_PUNTA_CAMPA;
      LOOP
       FETCH C_REPOR_PUNTA_CAMPA BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                lslinea :=     '"'|| detalleUnidadesRegRecord(x).region || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codZona || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).seccion || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).territorio || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codCliente || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).cedula || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).nombres || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).ciudad || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).telefono || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).celular || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).campania || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).puntajeVD || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).puntajeRetail || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).puntajeBoni || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).descuentoCDRS || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).abonoCargos || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).totalPuntaje || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).total || '")';

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_PUNTA_CAMPA%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_PUNTA_CAMPA;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := sqlcode;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_REPOR_PUNTO_CAMPA_CSV: '||ls_sqlerrm);

END INC_PR_REPOR_PUNTO_CAMPA_CSV;


/***************************************************************************
Descripcion       : Genera el Reporte Consultoras con Puntajes
Fecha Creacion    : 28/09/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INC_PR_REPOR_CONSU_PUNTA(
    pnOidConcurso      NUMBER
) IS
    w_filas         number := 10000;

    CURSOR C_REPOR_PUNTA_CAMPA IS
    SELECT
       X.CLIE_OID_CLIE
     FROM INC_REPOR_CONSU_PUNTA X;

     TYPE detalleReporte IS RECORD(
         oidCliente INC_REPOR_CONSU_PUNTA.CLIE_OID_CLIE%TYPE

      );

     TYPE detalleReporteTab IS TABLE OF detalleReporte;
     detalleReporteRecord detalleReporteTab;

     lsNombreConcurso     INC_REPOR_CONSU_PUNTA.VAL_CONC%TYPE;
     lnOidCliente         INC_REPOR_CONSU_PUNTA.CLIE_OID_CLIE%TYPE;
     lsDesRegion          INC_REPOR_CONSU_PUNTA.DES_REGI%TYPE;
     lsDesZona            INC_REPOR_CONSU_PUNTA.Des_Zona%TYPE;
     lnTerritorio         INC_REPOR_CONSU_PUNTA.COD_TERR%TYPE;
     lsDireccion          INC_REPOR_CONSU_PUNTA.VAL_DIRE%TYPE;
     lsTelefonoFijo       INC_REPOR_CONSU_PUNTA.VAL_TELE_FIJO%TYPE;
     lsCelular            INC_REPOR_CONSU_PUNTA.Val_Tele_Celu%TYPE;

     lsPrimerPedido       INC_REPOR_CONSU_PUNTA.VAL_PRIM_PEDI%TYPE;
     lsUltimoPedido       INC_REPOR_CONSU_PUNTA.Val_Ulti_Pedi%TYPE;
     lsEstatus            INC_REPOR_CONSU_PUNTA.Val_Esta%TYPE;
     lnPuntajeTotal       INC_REPOR_CONSU_PUNTA.MON_PUNT_TOTA%TYPE;
     lnPuntajeCanjeado    INC_REPOR_CONSU_PUNTA.MON_PUNT_CANJE%TYPE;
     lnPuntajeDisponible  INC_REPOR_CONSU_PUNTA.Mon_Punt_Disp%TYPE;
     lnPuntajeComprometido INC_REPOR_CONSU_PUNTA.MON_PUNT_COMP%TYPE;
     lnOidPeri             NUMBER;
     lncontador            NUMBER;

BEGIN
    EXECUTE IMMEDIATE 'truncate table INC_REPOR_CONSU_PUNTA';
    DELETE FROM INC_GTT_CONSU_PUNTA;
    DELETE FROM INC_GTT_CONSU_PUNTA_02;
    
    /* Obteniendo nombre del Concurso */
    SELECT n.num_conc || ' '||n.VAL_NOMB
     INTO lsNombreConcurso
    FROM  inc_concu_param_gener N
    WHERE n.oid_para_gral = pnOidConcurso;

    /* Insertando data en tabla INC_GTT_CONSU_PUNTA */
    INSERT INTO INC_GTT_CONSU_PUNTA(
      CLIE_OID_CLIE,
      COD_CLIE,
      NOM_CLIE,
      num_punt,
      VAL_DESC,
      IND_CANJ)
    SELECT 
           A.CLIE_OID_CLIE,
           b.COD_CLIE,
           TRIM(b.VAL_NOM1)||' '||TRIM(b.VAL_NOM2)||' '||TRIM(b.VAL_APE1)||' '||TRIM(b.VAL_APE2),
           A.NUM_PUNT,
           A.VAL_DESC,
           CASE 
             WHEN a.num_punt LIKE 'Entrega de Premio%' 
                THEN 1
           ELSE 
             0
           END IND_CANJ
      FROM INC_CUENT_CORRI_PUNTO A,
          MAE_CLIEN B
      WHERE A.COPA_OID_PARA_GRAL = pnOidConcurso
        AND a.num_punt <> 0
        AND A.CLIE_OID_CLIE = B.OID_CLIE;
    
    COMMIT;
    
    SELECT COUNT(1)
    INTO lnContador
    FROM INC_GTT_CONSU_PUNTA;
    
    INSERT INTO INC_GTT_CONSU_PUNTA_02(
      CLIE_OID_CLIE,
      num_cant_fija_punt)
    SELECT  E.CLIE_OID_CLIE,
            n.num_cant_fija_punt
    FROM  inc_premi_elegi e,  
          inc_param_nivel_premi n
     WHERE e.copa_oid_para_gral = pnOidConcurso
       AND e.ind_pend = 1
       AND e.panp_oid_para_nive_prem = n.oid_para_nive_prem;
    
    COMMIT;
    
    SELECT COUNT(1)
    INTO lnContador
    FROM INC_GTT_CONSU_PUNTA_02;
    
    /* Insertando data en tabla INC_REPOR_CONSU_PUNTA */
    INSERT INTO INC_REPOR_CONSU_PUNTA(
      CLIE_OID_CLIE,
      COD_CLIE,
      NOM_CLIE)
    SELECT DISTINCT
           CLIE_OID_CLIE,
           COD_CLIE,
           NOM_CLIE
      FROM INC_GTT_CONSU_PUNTA;
    COMMIT;

    OPEN C_REPOR_PUNTA_CAMPA;
    LOOP
      FETCH C_REPOR_PUNTA_CAMPA BULK COLLECT INTO detalleReporteRecord LIMIT W_FILAS;
      IF detalleReporteRecord.COUNT > 0 THEN
         FOR x IN detalleReporteRecord.FIRST .. detalleReporteRecord.LAST LOOP
             lnOidCliente :=   detalleReporteRecord(x).oidCliente;

             -- región --
            Select n.des_REGI
            INTO lsDesRegion
            from
             MAE_CLIEN_UNIDA_ADMIN j,
             ZON_TERRI_ADMIN k,
             ZON_SECCI l,
             ZON_ZONA m,
             ZON_REGIO n
            where j.CLIE_OID_CLIE = lnOidCliente
            and j.IND_ACTI = 1
            and j.ZTAD_OID_TERR_ADMI = k.OID_TERR_ADMI
            and k.ZSCC_OID_SECC = l.OID_SECC
            and l.ZZON_OID_ZONA = m.OID_ZONA
            and m.ZORG_OID_REGI = n.OID_REGI
            AND rownum = 1;


             -- zona --
            Select  M.des_ZONA
            INTO lsDesZona
            from
             MAE_CLIEN_UNIDA_ADMIN j,
             ZON_TERRI_ADMIN k,
             ZON_SECCI l,
             ZON_ZONA m,
             ZON_REGIO n
            where j.CLIE_OID_CLIE = lnOidCliente
            and j.IND_ACTI = 1
            and j.ZTAD_OID_TERR_ADMI = k.OID_TERR_ADMI
            and k.ZSCC_OID_SECC = l.OID_SECC
            and l.ZZON_OID_ZONA = m.OID_ZONA
            and m.ZORG_OID_REGI = n.OID_REGI
            AND rownum = 1;

            ---territorio---
             Select t.COD_TERR
             INTO lnTerritorio
             from
              MAE_CLIEN_UNIDA_ADMIN j,
              ZON_TERRI_ADMIN k,
              zon_terri t
             where j.CLIE_OID_CLIE = lnOidCliente
             and j.IND_ACTI = 1
             and j.ZTAD_OID_TERR_ADMI = k.OID_TERR_ADMI
             and k.TERR_OID_TERR =t.OID_TERR
             and t.IND_BORR =0
             AND rownum = 1;

            --- Direccón---
            BEGIN
              select TRIM(mcd.VAL_NOMB_VIA)||' '||TRIM(mcd.NUM_PPAL)||' '||TRIM(mcd.VAL_OBSE)
              INTO lsDireccion
              from
                MAE_CLIEN_DIREC mcd
              where mcd.CLIE_OID_CLIE = lnOidCliente
                and mcd.IND_DIRE_PPAL = 1
                and mcd.IND_ELIM = 0;
            EXCEPTION
            WHEN no_data_found THEN
                 lsDireccion := '';
            END;

            ---- Teléfono ---
            BEGIN
              select tc1.VAL_TEXT_COMU
              INTO lsTelefonoFijo
              from
                   MAE_CLIEN_COMUN tc1
              WHERE tc1.TICM_OID_TIPO_COMU = 1
                and tc1.clie_oid_clie = lnOidCliente;
            EXCEPTION
            WHEN no_data_found THEN
              lsTelefonoFijo := '';
            END;

            -- Teléfono Celular ----
            BEGIN
              select tc1.VAL_TEXT_COMU
              INTO lsCelular
              from  MAE_CLIEN_COMUN tc1
              WHERE tc1.TICM_OID_TIPO_COMU = 6
                and tc1.clie_oid_clie = lnOidCliente;
            EXCEPTION
            WHEN no_data_found THEN
                 lsCelular := '';
            END;

            -- Primer pedido --
            BEGIN
              lnOidPeri := GEN_PKG_GENER.GEN_FN_OID_PERIO_MAXIM_CLIEN(lnOidCliente,0);
              select cr.VAL_NOMB_PERI
              INTO lsPrimerPedido
              from  cra_perio cr
              where cr.OID_PERI = lnOidPeri;
		        EXCEPTION
            WHEN no_data_found THEN
                 lsPrimerPedido := '';
            END;

            -- Ultimo Pedido ------
            BEGIN
              lnOidPeri := GEN_PKG_GENER.GEN_FN_OID_PERIO_MAXIM_CLIEN(lnOidCliente,1);
              SELECT cr.val_nomb_peri
              INTO lsUltimoPedido
              from  cra_perio cr
              where cr.oid_peri = lnOidPeri;
			      EXCEPTION
            WHEN no_data_found THEN
                 lsUltimoPedido := '';
            END;

            ---- Estatus ---
            BEGIN
              SELECT gc.VAL_I18N
              INTO lsEstatus
              FROM  gen_i18n_sicc_comun gc
               WHERE gc.attr_enti = 'MAE_ESTAT_CLIEN'
                 AND gc.val_oid = (SELECT ma.esta_oid_esta_clie
                                    FROM  mae_clien_datos_adici ma
                                    WHERE ma.clie_oid_clie = lnOidCliente);
            EXCEPTION
            WHEN no_data_found THEN
                 lsEstatus := '';
            END;

            --- Puntaje Total ---
            BEGIN
              SELECT SUM (p.num_punt)
              INTO lnPuntajeTotal
              FROM  INC_GTT_CONSU_PUNTA P
              WHERE p.CLIE_OID_CLIE = lnOidCliente
                AND p.IND_CANJ = '0';
		        EXCEPTION
            WHEN no_data_found THEN
                 lnPuntajeTotal := 0;
            END;

            --- Puntaje Canjeado ---
            BEGIN
              SELECT SUM (p.num_punt)
              INTO lnPuntajeCanjeado
              FROM  INC_GTT_CONSU_PUNTA P
              WHERE p.CLIE_OID_CLIE = lnOidCliente
                AND p.IND_CANJ = '1';
		        EXCEPTION
            WHEN no_data_found THEN 
                 lnPuntajeCanjeado := 0;
            END;

            ---  Puntaje disponible ----
            BEGIN
               SELECT sum(a.num_punt)
               INTO lnPuntajeDisponible
               from  INC_GTT_CONSU_PUNTA A
              WHERE A.CLIE_OID_CLIE = lnOidCliente  ;
            EXCEPTION
            WHEN no_data_found THEN
                 lnPuntajeDisponible := 0;
            END;


            ---  Puntaje Comprometido ----
            BEGIN
               SELECT SUM (E.num_cant_fija_punt)
               INTO lnPuntajeComprometido
                  FROM  INC_GTT_CONSU_PUNTA_02 E
                 WHERE e.clie_oid_clie = lnOidCliente;
            EXCEPTION
            WHEN no_data_found THEN
                 lnPuntajeComprometido := 0;
            END;

            -- Actualizando Tabla 
            UPDATE INC_REPOR_CONSU_PUNTA x
            SET X.DES_REGI = lsDesRegion,
                X.DES_ZONA = lsDesZona,
                X.COD_TERR = lnTerritorio,
                X.VAL_DIRE = lsDireccion,
                X.VAL_TELE_FIJO = lsTelefonoFijo,
                X.VAL_TELE_CELU = lsCelular,
                X.VAL_PRIM_PEDI = lsPrimerPedido,
                X.VAL_ULTI_PEDI = lsUltimoPedido,
                X.VAL_ESTA = lsEstatus,
                X.VAL_CONC = lsNombreConcurso,
                X.MON_PUNT_TOTA = lnPuntajeTotal,
                X.MON_PUNT_CANJE = lnPuntajeCanjeado,
                X.MON_PUNT_DISP = lnPuntajeDisponible,
                X.MON_PUNT_COMP = lnPuntajeComprometido
            WHERE X.CLIE_OID_CLIE = lnOidCliente;

         END LOOP;
      END IF;
      COMMIT;
      EXIT WHEN C_REPOR_PUNTA_CAMPA%NOTFOUND;
      
    END LOOP;
    CLOSE C_REPOR_PUNTA_CAMPA;


EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := sqlcode;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_REPOR_CONSU_PUNTA: '||ls_sqlerrm);

END INC_PR_REPOR_CONSU_PUNTA;

/***************************************************************************
Descripcion       : Genera el Reporte Programa Puntos Nacional
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROGR_PUNTO_NACI_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    pscondicionPeriodo VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_PROGR_PUNTO_NACI IS
    select sum((CASE WHEN (upper(des_moti) = 'VENTA' or  des_moti IS NULL) THEN num_punt ELSE 0 END))  ptos_ventas, -- Puntaje Ventas
                           sum((CASE WHEN (upper(des_moti)  LIKE '%PEDIDOS CONSECUTIVOS' ) THEN num_punt ELSE 0 END))  ptos_pedcon,   --  Puntaje Constancia                           
                          sum((CASE WHEN (upper(des_moti)  LIKE 'RECOMENDACIONES%' ) THEN num_punt ELSE 0 END))  ptos_recom,  -- Puntaje Recomend.                           
                           sum((CASE WHEN (upper(des_moti)  LIKE 'LANZAMIENTO%' ) THEN num_punt ELSE 0 END))  ptos_promo, -- Puntaje Lanzam. Estrat.
                           sum((CASE WHEN (upper(des_moti)  LIKE '%NUEVAS%' ) THEN num_punt ELSE 0 END))  ptos_nueva,  -- Puntaje Constancia Nuevas
                           sum((CASE WHEN (upper(des_moti)  LIKE '%RETAIL%' ) THEN num_punt ELSE 0 END))  ptos_retail, --  Puntaje Belcenter
                           sum((CASE WHEN (upper(des_moti)  LIKE '%SIN PEDIDO%' ) THEN num_punt ELSE 0 END))  ptos_sinpedido, --  Puntos Canc.Puntos Constancia
                           sum((CASE WHEN (upper(des_moti)  LIKE '%VENCIMIENTO%' ) THEN num_punt ELSE 0 END))  ptos_vencidos, --  Puntos Canc.Puntos Vencmto.
                           sum((CASE WHEN (upper(des_moti)  LIKE 'PREMIA%' )  THEN num_punt ELSE 0 END))  ptos_canjeados, -- Puntos Canjeados
                           sum((CASE WHEN (upper(des_moti)  LIKE '%ANUL%'  OR upper(des_moti)  LIKE '%DEVOL%') THEN num_punt ELSE 0 END))  ptos_cdr, -- Devol/Anul.
                           sum(num_punt) ptos_total -- Puntaje total  
                           from inc_cuent_corri_punto   
                           where copa_oid_para_gral = psnumeroConcurso 
                           and (pscondicionPeriodo is null or PERD_OID_PERI in (pscondicionPeriodo));

     TYPE detalleProgPuntos IS RECORD(
         ptos_ventas      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_pedcon      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_recom       inc_cuent_corri_punto.num_punt%TYPE,
         ptos_promo      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_nueva      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_retail      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_sinpedido      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_vencidos      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_canjeados      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_cdr      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_total      inc_cuent_corri_punto.num_punt%TYPE
      );

     TYPE detalleProgPuntosTab IS TABLE OF detalleProgPuntos;
     detalleProgPuntosRecord detalleProgPuntosTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_PROGR_PUNTO_NACI;
      LOOP
       FETCH C_REPOR_PROGR_PUNTO_NACI BULK COLLECT INTO detalleProgPuntosRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleProgPuntosRecord.COUNT > 0 THEN
          FOR x IN detalleProgPuntosRecord.FIRST .. detalleProgPuntosRecord.LAST LOOP
                lslinea :=      '=T("'|| detalleProgPuntosRecord(x).ptos_ventas || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_pedcon || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_recom || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_promo || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_nueva || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_retail || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_sinpedido || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_vencidos || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_canjeados || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_cdr || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_total || '")';

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_PROGR_PUNTO_NACI%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_PROGR_PUNTO_NACI;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := sqlcode;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_PROGR_PUNTO_NACI_CSV: '||ls_sqlerrm);

END INC_PR_PROGR_PUNTO_NACI_CSV;

/***************************************************************************
Descripcion       : Genera el Reporte Programa Puntos por Region
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROGR_PUNTO_REGI_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    pscondicionPeriodo VARCHAR2,
    pscondicionRegion  VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_PROGR_PUNTO_REGI IS
    select 
zr.des_regi Des_regi,
        (CASE WHEN (upper(des_moti) = 'VENTA' or  des_moti IS NULL) THEN num_punt ELSE 0 END) Ptos_ventas, -- Puntaje Ventas
                           (CASE WHEN (upper(des_moti)  LIKE '%PEDIDOS CONSECUTIVOS' ) THEN num_punt ELSE 0 END) Ptos_pedcon,   --  Puntaje Constancia                           
                          (CASE WHEN (upper(des_moti)  LIKE 'RECOMENDACIONES%' ) THEN num_punt ELSE 0 END)  Ptos_recom,  -- Puntaje Recomend.                           
                           (CASE WHEN (upper(des_moti)  LIKE 'LANZAMIENTO%' ) THEN num_punt ELSE 0 END)  Ptos_promo, -- Puntaje Lanzam. Estrat.
                           (CASE WHEN (upper(des_moti)  LIKE '%NUEVAS%' ) THEN num_punt ELSE 0 END)  Ptos_nueva,  -- Puntaje Constancia Nuevas
                           (CASE WHEN (upper(des_moti)  LIKE '%RETAIL%' ) THEN num_punt ELSE 0 END)  Ptos_retail, --  Puntaje Belcenter
                           (CASE WHEN (upper(des_moti)  LIKE '%SIN PEDIDO%' ) THEN num_punt ELSE 0 END)  Ptos_sinpedido, --  Puntos Canc.Puntos Constancia
                           (CASE WHEN (upper(des_moti)  LIKE '%VENCIMIENTO%' ) THEN num_punt ELSE 0 END) Ptos_vencidos, --  Puntos Canc.Puntos Vencmto.
                          (CASE WHEN (upper(des_moti)  LIKE 'PREMIA%' )  THEN num_punt ELSE 0 END) Ptos_canjeados, -- Puntos Canjeados
                           (CASE WHEN (upper(des_moti)  LIKE '%ANUL%'  OR upper(des_moti)  LIKE '%DEVOL%') THEN num_punt ELSE 0 END)  Ptos_cdr, -- Devol/Anul.
                           num_punt Ptos_Total -- Puntaje total  
                           from inc_cuent_corri_punto iccp,
                           mae_clien_unida_admin mcua,
                           zon_terri_admin za, 
                           zon_secci sec, zon_zona zon,
                           zon_regio zr
                           where mcua.clie_oid_clie=iccp.clie_oid_clie
                           and mcua.ZTAD_OID_TERR_ADMI = za.oid_terr_admi
                           and za.ZSCC_OID_SECC = sec.oid_secc
                           and sec.ZZON_OID_ZONA = zon.oid_zona
                           and zon.zorg_oid_regi = zr.oid_regi
                           and copa_oid_para_gral = psnumeroConcurso 
                           and (pscondicionPeriodo is null or PERD_OID_PERI in (pscondicionPeriodo))
                           and (pscondicionRegion is null or zr.cod_regi in (pscondicionRegion));

     TYPE detalleProgPuntos IS RECORD(
         des_regi        zon_regio.des_regi%TYPE,
         ptos_ventas      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_pedcon      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_recom       inc_cuent_corri_punto.num_punt%TYPE,
         ptos_promo      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_nueva      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_retail      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_sinpedido      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_vencidos      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_canjeados      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_cdr      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_total      inc_cuent_corri_punto.num_punt%TYPE
      );

     TYPE detalleProgPuntosTab IS TABLE OF detalleProgPuntos;
     detalleProgPuntosRecord detalleProgPuntosTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_PROGR_PUNTO_REGI;
      LOOP
       FETCH C_REPOR_PROGR_PUNTO_REGI BULK COLLECT INTO detalleProgPuntosRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleProgPuntosRecord.COUNT > 0 THEN
          FOR x IN detalleProgPuntosRecord.FIRST .. detalleProgPuntosRecord.LAST LOOP
                lslinea :=     '"'|| detalleProgPuntosRecord(x).des_regi || '"' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_ventas || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_pedcon || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_recom || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_promo || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_nueva || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_retail || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_sinpedido || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_vencidos || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_canjeados || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_cdr || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_total || '")';

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_PROGR_PUNTO_REGI%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_PROGR_PUNTO_REGI;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := sqlcode;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_PROGR_PUNTO_REGI_CSV: '||ls_sqlerrm);

END INC_PR_PROGR_PUNTO_REGI_CSV;

/***************************************************************************
Descripcion       : Genera el Reporte Programa Puntos por Zona
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROGR_PUNTO_ZONA_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    pscondicionPeriodo VARCHAR2,
    pscondicionRegion  VARCHAR2,
    pscondicionZona    VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_PROGR_PUNTO_ZONA IS
    select 
    zr.des_regi des_regi,
    zon.cod_zona cod_zona,
        (CASE WHEN (upper(des_moti) = 'VENTA' or  des_moti IS NULL) THEN num_punt ELSE 0 END) ptos_ventas, -- Puntaje Ventas
                           (CASE WHEN (upper(des_moti)  LIKE '%PEDIDOS CONSECUTIVOS' ) THEN num_punt ELSE 0 END) ptos_pedcon,   --  Puntaje Constancia                           
                          (CASE WHEN (upper(des_moti)  LIKE 'RECOMENDACIONES%' ) THEN num_punt ELSE 0 END)  ptos_recom,  -- Puntaje Recomend.                           
                           (CASE WHEN (upper(des_moti)  LIKE 'LANZAMIENTO%' ) THEN num_punt ELSE 0 END)  ptos_promo, -- Puntaje Lanzam. Estrat.
                           (CASE WHEN (upper(des_moti)  LIKE '%NUEVAS%' ) THEN num_punt ELSE 0 END)  ptos_nueva,  -- Puntaje Constancia Nuevas
                           (CASE WHEN (upper(des_moti)  LIKE '%RETAIL%' ) THEN num_punt ELSE 0 END)  ptos_retail, --  Puntaje Belcenter
                           (CASE WHEN (upper(des_moti)  LIKE '%SIN PEDIDO%' ) THEN num_punt ELSE 0 END)  ptos_sinpedido, --  Puntos Canc.Puntos Constancia
                           (CASE WHEN (upper(des_moti)  LIKE '%VENCIMIENTO%' ) THEN num_punt ELSE 0 END) ptos_vencidos, --  Puntos Canc.Puntos Vencmto.
                          (CASE WHEN (upper(des_moti)  LIKE 'PREMIA%' )  THEN num_punt ELSE 0 END) ptos_canjeados, -- Puntos Canjeados
                           (CASE WHEN (upper(des_moti)  LIKE '%ANUL%'  OR upper(des_moti)  LIKE '%DEVOL%') THEN num_punt ELSE 0 END)  ptos_cdr, -- Devol/Anul.
                           num_punt ptos_total -- Puntaje total  
                           from inc_cuent_corri_punto iccp,
                           mae_clien_unida_admin mcua,
                           zon_terri_admin za, 
                           zon_secci sec, zon_zona zon,
                           zon_regio zr
                           where mcua.clie_oid_clie=iccp.clie_oid_clie
                           and mcua.ZTAD_OID_TERR_ADMI = za.oid_terr_admi
                           and za.ZSCC_OID_SECC = sec.oid_secc
                           and sec.ZZON_OID_ZONA = zon.oid_zona
                           and zon.zorg_oid_regi = zr.oid_regi
                           and copa_oid_para_gral = psnumeroConcurso 
                           and (pscondicionPeriodo is null or PERD_OID_PERI in (pscondicionPeriodo))
                           and (pscondicionRegion is null or zr.cod_regi in (pscondicionRegion))
                           and (pscondicionZona is null or zon.cod_zona in (pscondicionZona));

     TYPE detalleProgPuntos IS RECORD(
         des_regi        zon_regio.des_regi%TYPE,
         cod_zona       zon_zona.cod_zona%TYPE,
         ptos_ventas      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_pedcon      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_recom       inc_cuent_corri_punto.num_punt%TYPE,
         ptos_promo      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_nueva      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_retail      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_sinpedido      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_vencidos      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_canjeados      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_cdr      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_total      inc_cuent_corri_punto.num_punt%TYPE
      );

     TYPE detalleProgPuntosTab IS TABLE OF detalleProgPuntos;
     detalleProgPuntosRecord detalleProgPuntosTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_PROGR_PUNTO_ZONA;
      LOOP
       FETCH C_REPOR_PROGR_PUNTO_ZONA BULK COLLECT INTO detalleProgPuntosRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleProgPuntosRecord.COUNT > 0 THEN
          FOR x IN detalleProgPuntosRecord.FIRST .. detalleProgPuntosRecord.LAST LOOP
                lslinea :=     '"'|| detalleProgPuntosRecord(x).des_regi || '"' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).cod_zona || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_ventas || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_pedcon || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_recom || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_promo || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_nueva || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_retail || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_sinpedido || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_vencidos || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_canjeados || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_cdr || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_total || '")';

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_PROGR_PUNTO_ZONA%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_PROGR_PUNTO_ZONA;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := sqlcode;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_PROGR_PUNTO_ZONA_CSV: '||ls_sqlerrm);

END INC_PR_PROGR_PUNTO_ZONA_CSV;

/***************************************************************************
Descripcion       : Genera el Reporte Programa Puntos por Consultora
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROGR_PUNTO_CONS_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    pscondicionPeriodo VARCHAR2,
    pscondicionRegion  VARCHAR2,
    pscondicionZona    VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_PROGR_PUNTO_CONS IS
    select 
    zr.des_regi des_regi,
    zon.cod_zona cod_zona,
    sec.cod_secc seccion,
    cli.cod_clie cod_clie,
    cli.val_nom1 || cli.val_nom2 || cli.val_ape1 || cli.val_ape2 nombre_consultora,
        (CASE WHEN (upper(des_moti) = 'VENTA' or  des_moti IS NULL) THEN num_punt ELSE 0 END) ptos_ventas, -- Puntaje Ventas
                            (CASE WHEN (upper(des_moti)  LIKE '%PEDIDOS CONSECUTIVOS' ) THEN num_punt ELSE 0 END) ptos_pedcon,   --  Puntaje Constancia                           
                          (CASE WHEN (upper(des_moti)  LIKE 'RECOMENDACIONES%' ) THEN num_punt ELSE 0 END)  ptos_recom,  -- Puntaje Recomend.                           
                           (CASE WHEN (upper(des_moti)  LIKE 'LANZAMIENTO%' ) THEN num_punt ELSE 0 END)  ptos_promo, -- Puntaje Lanzam. Estrat.
                           (CASE WHEN (upper(des_moti)  LIKE '%NUEVAS%' ) THEN num_punt ELSE 0 END)  ptos_nueva,  -- Puntaje Constancia Nuevas
                           (CASE WHEN (upper(des_moti)  LIKE '%RETAIL%' ) THEN num_punt ELSE 0 END)  ptos_retail, --  Puntaje Belcenter
                           (CASE WHEN (upper(des_moti)  LIKE '%SIN PEDIDO%' ) THEN num_punt ELSE 0 END)  ptos_sinpedido, --  Puntos Canc.Puntos Constancia
                           (CASE WHEN (upper(des_moti)  LIKE '%VENCIMIENTO%' ) THEN num_punt ELSE 0 END) ptos_vencidos, --  Puntos Canc.Puntos Vencmto.
                          (CASE WHEN (upper(des_moti)  LIKE 'PREMIA%' )  THEN num_punt ELSE 0 END) ptos_canjeados, -- Puntos Canjeados
                           (CASE WHEN (upper(des_moti)  LIKE '%ANUL%'  OR upper(des_moti)  LIKE '%DEVOL%') THEN num_punt ELSE 0 END)  ptos_cdr, -- Devol/Anul.
                           num_punt ptos_total -- Puntaje total  
                           from inc_cuent_corri_punto iccp,
                           mae_clien_unida_admin mcua,
                           zon_terri_admin za, 
                           zon_secci sec, zon_zona zon,
                           zon_regio zr,
                           mae_clien cli
                           where mcua.clie_oid_clie=iccp.clie_oid_clie
                           and cli.oid_clie=mcua.clie_oid_clie
                           and mcua.ZTAD_OID_TERR_ADMI = za.oid_terr_admi
                           and za.ZSCC_OID_SECC = sec.oid_secc
                           and sec.ZZON_OID_ZONA = zon.oid_zona
                           and zon.zorg_oid_regi = zr.oid_regi 
                           and copa_oid_para_gral = psnumeroConcurso 
                           and (pscondicionPeriodo is null or PERD_OID_PERI in (pscondicionPeriodo))
                           and (pscondicionRegion is null or zr.cod_regi in (pscondicionRegion))
                           and (pscondicionZona is null or zon.cod_zona in (pscondicionZona));

     TYPE detalleProgPuntos IS RECORD(
         des_regi        zon_regio.des_regi%TYPE,
         cod_zona       zon_zona.cod_zona%TYPE,
         seccion       zon_secci.cod_secc%TYPE,
         cod_clie    mae_clien.cod_clie%TYPE,
         nombre_consultora  VARCHAR2(100),
         ptos_ventas      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_pedcon      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_recom       inc_cuent_corri_punto.num_punt%TYPE,
         ptos_promo      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_nueva      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_retail      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_sinpedido      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_vencidos      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_canjeados      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_cdr      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_total      inc_cuent_corri_punto.num_punt%TYPE
      );

     TYPE detalleProgPuntosTab IS TABLE OF detalleProgPuntos;
     detalleProgPuntosRecord detalleProgPuntosTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_PROGR_PUNTO_CONS;
      LOOP
       FETCH C_REPOR_PROGR_PUNTO_CONS BULK COLLECT INTO detalleProgPuntosRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleProgPuntosRecord.COUNT > 0 THEN
          FOR x IN detalleProgPuntosRecord.FIRST .. detalleProgPuntosRecord.LAST LOOP
                lslinea :=     '"'|| detalleProgPuntosRecord(x).des_regi || '"' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).cod_zona || '")' || ',' ||
                                '"'|| detalleProgPuntosRecord(x).seccion || '"' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).cod_clie || '")' || ',' ||
                                '"'|| detalleProgPuntosRecord(x).nombre_consultora || '"' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_ventas || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_pedcon || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_recom || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_promo || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_nueva || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_retail || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_sinpedido || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_vencidos || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_canjeados || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_cdr || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_total || '")';

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_PROGR_PUNTO_CONS%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_PROGR_PUNTO_CONS;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := sqlcode;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_PROGR_PUNTO_CONS_CSV: '||ls_sqlerrm);

END INC_PR_PROGR_PUNTO_CONS_CSV;

/***************************************************************************
Descripcion       : Genera el Reporte Programa Puntos por Campania
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROGR_PUNTO_CAMP_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    pscondicionPeriodo VARCHAR2,
    pscondicionRegion  VARCHAR2,
    pscondicionZona    VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_PROGR_PUNTO_CAMP IS
    select 
     zr.des_regi des_regi,
     zon.cod_zona cod_zona,
     sec.cod_secc seccion,
     cli.cod_clie cod_clie,
     cli.val_nom1 || cli.val_nom2 || cli.val_ape1 || cli.val_ape2 nombre_consultora,
     gen_pkg_gener.gen_fn_devuelve_id_cra_perio3(iccp.perd_oid_peri) campania,
     (CASE WHEN (upper(des_moti) = 'VENTA' or  des_moti IS NULL) THEN num_punt ELSE 0 END) ptos_ventas, -- Puntaje Ventas
                           (CASE WHEN (upper(des_moti)  LIKE '%PEDIDOS CONSECUTIVOS' ) THEN num_punt ELSE 0 END) ptos_pedcon,   --  Puntaje Constancia                           
                          (CASE WHEN (upper(des_moti)  LIKE 'RECOMENDACIONES%' ) THEN num_punt ELSE 0 END)  ptos_recom,  -- Puntaje Recomend.                           
                           (CASE WHEN (upper(des_moti)  LIKE 'LANZAMIENTO%' ) THEN num_punt ELSE 0 END)  ptos_promo, -- Puntaje Lanzam. Estrat.
                           (CASE WHEN (upper(des_moti)  LIKE '%NUEVAS%' ) THEN num_punt ELSE 0 END)  ptos_nueva,  -- Puntaje Constancia Nuevas
                           (CASE WHEN (upper(des_moti)  LIKE '%RETAIL%' ) THEN num_punt ELSE 0 END)  ptos_retail, --  Puntaje Belcenter
                           (CASE WHEN (upper(des_moti)  LIKE '%SIN PEDIDO%' ) THEN num_punt ELSE 0 END)  ptos_sinpedido, --  Puntos Canc.Puntos Constancia
                           (CASE WHEN (upper(des_moti)  LIKE '%VENCIMIENTO%' ) THEN num_punt ELSE 0 END) ptos_vencidos, --  Puntos Canc.Puntos Vencmto.
                          (CASE WHEN (upper(des_moti)  LIKE 'PREMIA%' )  THEN num_punt ELSE 0 END) ptos_canjeados, -- Puntos Canjeados
                           (CASE WHEN (upper(des_moti)  LIKE '%ANUL%'  OR upper(des_moti)  LIKE '%DEVOL%') THEN num_punt ELSE 0 END)  ptos_cdr, -- Devol/Anul.
                           num_punt ptos_total -- Puntaje total  
                           from inc_cuent_corri_punto iccp,
                           mae_clien_unida_admin mcua,
                           zon_terri_admin za, 
                           zon_secci sec, zon_zona zon,
                           zon_regio zr,
                           mae_clien cli
                           where mcua.clie_oid_clie=iccp.clie_oid_clie
                           and cli.oid_clie=mcua.clie_oid_clie
                           and mcua.ZTAD_OID_TERR_ADMI = za.oid_terr_admi
                           and za.ZSCC_OID_SECC = sec.oid_secc
                           and sec.ZZON_OID_ZONA = zon.oid_zona
                           and zon.zorg_oid_regi = zr.oid_regi
                           and copa_oid_para_gral = psnumeroConcurso 
                           and (pscondicionPeriodo is null or PERD_OID_PERI in (pscondicionPeriodo))
                           and (pscondicionRegion is null or zr.cod_regi in (pscondicionRegion))
                           and (pscondicionZona is null or zon.cod_zona in (pscondicionZona));


     TYPE detalleProgPuntos IS RECORD(
         des_regi        zon_regio.des_regi%TYPE,
         cod_zona       zon_zona.cod_zona%TYPE,
         seccion       zon_secci.cod_secc%TYPE,
         cod_clie    mae_clien.cod_clie%TYPE,
         nombre_consultora  VARCHAR2(100),
         campania        VARCHAR2(6),
         ptos_ventas      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_pedcon      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_recom       inc_cuent_corri_punto.num_punt%TYPE,
         ptos_promo      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_nueva      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_retail      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_sinpedido      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_vencidos      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_canjeados      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_cdr      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_total      inc_cuent_corri_punto.num_punt%TYPE
      );

     TYPE detalleProgPuntosTab IS TABLE OF detalleProgPuntos;
     detalleProgPuntosRecord detalleProgPuntosTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_PROGR_PUNTO_CAMP;
      LOOP
       FETCH C_REPOR_PROGR_PUNTO_CAMP BULK COLLECT INTO detalleProgPuntosRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleProgPuntosRecord.COUNT > 0 THEN
          FOR x IN detalleProgPuntosRecord.FIRST .. detalleProgPuntosRecord.LAST LOOP
                lslinea :=     '"'|| detalleProgPuntosRecord(x).des_regi || '"' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).cod_zona || '")' || ',' ||
                                '"'|| detalleProgPuntosRecord(x).seccion || '"' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).cod_clie || '")' || ',' ||
                                '"'|| detalleProgPuntosRecord(x).nombre_consultora || '"' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).campania || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_ventas || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_pedcon || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_recom || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_promo || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_nueva || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_retail || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_sinpedido || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_vencidos || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_canjeados || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_cdr || '")' || ',' ||
                                '=T("'|| detalleProgPuntosRecord(x).ptos_total || '")';

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_PROGR_PUNTO_CAMP%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_PROGR_PUNTO_CAMP;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := sqlcode;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_PROGR_PUNTO_CAMP_CSV: '||ls_sqlerrm);

END INC_PR_PROGR_PUNTO_CAMP_CSV;

/***************************************************************************
Descripcion       : Genera el Reporte para Provisión Contable Ingresos
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROVI_CONTA_INGRE_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    psfechaInicio      VARCHAR2,
    psfechaFin         VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_PROVI_CONTA_INGRE IS
    select
    to_char(FEC_MOVI, 'DD/MM/YYYY') fecha,
                           SUM( (CASE WHEN (upper(des_moti) = 'VENTA' or  des_moti IS NULL) THEN num_punt ELSE 0 END)) ptos_ventas, -- Puntaje Ventas
                          SUM((CASE WHEN (upper(des_moti)  LIKE '%PEDIDOS CONSECUTIVOS' ) THEN num_punt ELSE 0 END)) ptos_pedcon,   --  Puntaje Constancia                          
                          SUM((CASE WHEN (upper(des_moti)  LIKE 'RECOMENDACION%' ) THEN num_punt ELSE 0 END))  ptos_recom,  -- Puntaje Recomend.                          
                           SUM((CASE WHEN (upper(des_moti)  LIKE 'LANZAMIENTO%' ) THEN num_punt ELSE 0 END))  ptos_promo, -- Puntaje Lanzam. Estrat.
                           SUM((CASE WHEN (upper(des_moti)  LIKE '%NUEVAS%' ) THEN num_punt ELSE 0 END))  ptos_nueva,  -- Puntaje Constancia Nuevas
                           SUM((CASE WHEN (upper(des_moti)  LIKE '%RETAIL%' ) THEN num_punt ELSE 0 END))  ptos_retail, --  Puntaje Belcenter
                           SUM(((CASE WHEN (upper(des_moti) = 'VENTA' or  des_moti IS NULL) THEN num_punt ELSE 0 END)+
                           (CASE WHEN (upper(des_moti)  LIKE '%PEDIDOS CONSECUTIVOS' ) THEN num_punt ELSE 0 END)+
                           (CASE WHEN (upper(des_moti)  LIKE 'RECOMENDACION%' ) THEN num_punt ELSE 0 END)+
                           (CASE WHEN (upper(des_moti)  LIKE 'LANZAMIENTO%' ) THEN num_punt ELSE 0 END)+
                           (CASE WHEN (upper(des_moti)  LIKE '%NUEVAS%' ) THEN num_punt ELSE 0 END)+
                           (CASE WHEN (upper(des_moti)  LIKE '%RETAIL%' ) THEN num_punt ELSE 0 END))) ptos_total -- Puntaje total 
                           from inc_cuent_corri_punto
                           where copa_oid_para_gral = psnumeroConcurso
                           and fec_movi between TO_DATE(psfechaInicio, 'DD/MM/YYYY') 
                           and TO_DATE(psfechaFin, 'DD/MM/YYYY')
                          group by FEC_MOVI;
                           


     TYPE detalleIngresos IS RECORD(
         fecha      VARCHAR2(10),
         ptos_ventas      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_pedcon      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_recom       inc_cuent_corri_punto.num_punt%TYPE,
         ptos_promo      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_nueva      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_retail      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_total      inc_cuent_corri_punto.num_punt%TYPE
      );

     TYPE detalleIngresosTab IS TABLE OF detalleIngresos;
     detalleIngresosRecord detalleIngresosTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_PROVI_CONTA_INGRE;
      LOOP
       FETCH C_REPOR_PROVI_CONTA_INGRE BULK COLLECT INTO detalleIngresosRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleIngresosRecord.COUNT > 0 THEN
          FOR x IN detalleIngresosRecord.FIRST .. detalleIngresosRecord.LAST LOOP
                lslinea :=      '=T("'|| detalleIngresosRecord(x).fecha || '")' || ',' ||
                                '=T("'|| detalleIngresosRecord(x).ptos_ventas || '")' || ',' ||
                                '=T("'|| detalleIngresosRecord(x).ptos_pedcon || '")' || ',' ||
                                '=T("'|| detalleIngresosRecord(x).ptos_recom || '")' || ',' ||
                                '=T("'|| detalleIngresosRecord(x).ptos_promo || '")' || ',' ||
                                '=T("'|| detalleIngresosRecord(x).ptos_nueva || '")' || ',' ||
                                '=T("'|| detalleIngresosRecord(x).ptos_retail || '")' || ',' ||
                                '=T("'|| detalleIngresosRecord(x).ptos_total || '")';

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_PROVI_CONTA_INGRE%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_PROVI_CONTA_INGRE;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := sqlcode;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_PROVI_CONTA_INGRE_CSV: '||ls_sqlerrm);

END INC_PR_PROVI_CONTA_INGRE_CSV;

/***************************************************************************
Descripcion       : Genera el Reporte para Provisión Contable Gastos
Fecha Creacion    : 25/01/2016
Autor             : Segundo Leiva
***************************************************************************/
PROCEDURE INC_PR_PROVI_CONTA_GASTO_CSV(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psnumeroConcurso   VARCHAR2,
    psfechaInicio      VARCHAR2,
    psfechaFin         VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_PROVI_CONTA_GASTO IS
    select
       to_char(FEC_MOVI, 'dd/mm/YYYY') fecha,
       SUM((CASE WHEN (upper(des_moti)  LIKE 'PREMIA%' )  THEN num_punt ELSE 0 END)) ptos_canjeados, -- Puntos Canjeados
       SUM((CASE WHEN (upper(des_moti)  LIKE '%SIN PEDIDO%' ) THEN num_punt ELSE 0 END))  ptos_sinpedido, --  Puntos Canc.Puntos Constancia
       SUM((CASE WHEN (upper(des_moti)  LIKE '%VENCIMIENTO%' ) THEN num_punt ELSE 0 END)) ptos_vencidos, --  Puntos Canc.Puntos Vencmto.
       SUM( ((CASE WHEN (upper(des_moti)  LIKE '%SIN PEDIDO%' ) THEN num_punt ELSE 0 END) +
        (CASE WHEN (upper(des_moti)  LIKE '%VENCIMIENTO%' ) THEN num_punt ELSE 0 END) +
        (CASE WHEN (upper(des_moti)  LIKE 'PREMIA%' )  THEN num_punt ELSE 0 END))) ptos_total -- Puntaje total 
        from inc_cuent_corri_punto
        where copa_oid_para_gral = psnumeroConcurso
                           and fec_movi between TO_DATE(psfechaInicio, 'DD/MM/YYYY') 
                           and TO_DATE(psfechaFin, 'DD/MM/YYYY')
                          group by FEC_MOVI;

     TYPE detalleGastos IS RECORD(
         fecha      VARCHAR2(10),
         ptos_canjeados      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_sinpedido      inc_cuent_corri_punto.num_punt%TYPE,
         ptos_vencidos       inc_cuent_corri_punto.num_punt%TYPE,
         ptos_total      inc_cuent_corri_punto.num_punt%TYPE
      );

     TYPE detalleGastosTab IS TABLE OF detalleGastos;
     detalleGastosRecord detalleGastosTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_PROVI_CONTA_GASTO;
      LOOP
       FETCH C_REPOR_PROVI_CONTA_GASTO BULK COLLECT INTO detalleGastosRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleGastosRecord.COUNT > 0 THEN
          FOR x IN detalleGastosRecord.FIRST .. detalleGastosRecord.LAST LOOP
                lslinea :=      '=T("'|| detalleGastosRecord(x).fecha || '")' || ',' ||
                                '=T("'|| detalleGastosRecord(x).ptos_canjeados || '")' || ',' ||
                                '=T("'|| detalleGastosRecord(x).ptos_sinpedido || '")' || ',' ||
                                '=T("'|| detalleGastosRecord(x).ptos_vencidos || '")' || ',' ||
                                '=T("'|| detalleGastosRecord(x).ptos_total || '")';

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_PROVI_CONTA_GASTO%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_PROVI_CONTA_GASTO;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := sqlcode;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_PROVI_CONTA_GASTO_CSV: '||ls_sqlerrm);

END INC_PR_PROVI_CONTA_GASTO_CSV;


END Inc_Pkg_Repor_Incen;
/
