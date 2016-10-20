CREATE OR REPLACE PACKAGE LOV_PKG_PROCE IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  W_FILAS    NUMBER := 1000;

  /* Procesos de Programas de Puntajes LOVE */
  PROCESO_GP4 CONSTANT VARCHAR2(3) := 'G';
  PROCESO_CIERRE_ZONA CONSTANT VARCHAR2(3) := 'Z';
  PROCESO_CIERRE_REGION CONSTANT VARCHAR2(3) := 'R';
  PROCESO_CIERRE_CAMPANA CONSTANT VARCHAR2(3) := 'P';

/**************************************************************************
Descripcion       : Generar Puntaje por Compras para el programa LOVE
Fecha Creacion    : 06/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                       GP4: GP4
  psCodigoRegion     : Codigo de Region
  psCodigoZona       : Codigo de Zona
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_GENER_PUNTA_COMPR
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psIndicadorProceso         VARCHAR2,
   psCodigoRegion             VARCHAR2,
   psCodigoZona               VARCHAR2,
   psFechaFacturacion         VARCHAR2);

/**************************************************************************
Descripcion        : Recupera el Oid Concurso vigente del programa LOVE
Fecha Creacion     : 06/10/2009
Parametros Entrada :
           psCodigoPais : Codigo Pais
           psCodigoPeriodo : Codigo Perido

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_DEVUE_CONCU_VIGEN(psCodigoPais      VARCHAR2,
                                  psCodigoPeriodo   VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion        : Valida las clasificaciones del cliente en las clasificaciones del concurso
Fecha Creacion     :  06/10/2009
Parametros Entrada :
           psOidConcurso : oidConcurso
           psOidCliente :oidCliente
Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_VALID_CLASI_CONCU(pnOidConcurso        NUMBER,
                                  pnOidCliente         NUMBER)
RETURN VARCHAR2;

/**************************************************************************
Descripcion        : Obtiene la Categoria de acuerdo a la clasificacion LOVE
                     que tenga el Cliente
Fecha Creacion     :  06/10/2009
Parametros Entrada :
           psOidCliente :  oidCliente
Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_CATEG_CLASI_CONCU(pnOidCliente         NUMBER)
RETURN VARCHAR2;

/**************************************************************************
Descripcion        : Valida si la campaña de la solicitu de referencia
                     se encuentra dentro del rango de periodos
Fecha Creacion     :  09/10/2009
Parametros Entrada :
           pnOidSoliCabeRefe : oid Solicitud Referencia
           psCodigoPeriodoIni :  codigo Periodo Inicio
           psCodigoPeriodoFin :  codigo Periodo Fin

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_VALID_PERIO_REFER(pnOidSoliCabeRefe       NUMBER,
                                  psCodigoPeriodoIni      VARCHAR2,
                                  psCodigoPeriodoFin      VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion        : Recupera  la campaña de la solicitu de referencia
Fecha Creacion     :  12/10/2009
Parametros Entrada :
           pnOidSoliCabeRefe : oid Solicitud Referencia

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_DEVUE_PERIO_REFER(pnOidSoliCabeRefe       NUMBER)
RETURN VARCHAR2;

/**************************************************************************
Descripcion       : Generar Puntaje por Constancia para el programa LOVE
Fecha Creacion    : 12/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                       GP4: GP4
  psCodigoRegion     : Codigo de Region
  psCodigoZona       : Codigo de Zona
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_GENER_PUNTA_CONST
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psIndicadorProceso         VARCHAR2,
   psCodigoRegion             VARCHAR2,
   psCodigoZona               VARCHAR2,
   psFechaFacturacion         VARCHAR2);

/**************************************************************************
Descripcion        : Valida el puntaje relacionado al total Pedidos de un
                     determinado concurso
Fecha Creacion     :  12/10/2009
Parametros Entrada :
           psNumeroConcurso : Numero Concurso
           pnTotalPedidos :  Total Pedidos para el concurso

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_DEVUE_PUNTA_CONST(psNumeroConcurso     VARCHAR2,
                                  pnTotalPedidos       NUMBER)
RETURN NUMBER;

/**************************************************************************
Descripcion        : Se calcula el numero de pedidos consecutivos para el cliente
                     y un determinado concurso
Fecha Creacion     :  12/10/2009
Parametros Entrada :
           psCodigoCliente  : Codigo de Cliente
           psNumeroConcurso : Numero Concurso
           psCodigoPeriodoIni : Codigo Periodo Inicio del Concurso
           psCodigoPais     :  Codigo Pais
           psCodigoMarca    :  Codigo Marca
           psCodigoCanal    :  Codigo Canal
           psCodigoPeriodo  :  Codigo Periodo Proceso

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_DEVUE_PEDID_CONSE(psCodigoCliente      VARCHAR2,
                                  psNumeroConcurso     VARCHAR2,
                                  psCodigoPeriodoIni   VARCHAR2,
                                  psCodigoPais         VARCHAR2,
                                  psCodigoMarca        VARCHAR2,
                                  psCodigoCanal        VARCHAR2,
                                  psCodigoPeriodo      VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion       : Registra una incidencia producida de alguna validacion que no
                    paso una determinada solicitud para cualquier de los procesos de LOVE
Fecha Creacion    : 15/10/2009
Parametros Entrada:
  psNumeroConcurso :  Numero de Concurso
  psCodigoCliente  :  Codigo de Cliente
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoProceso  :  Codigo de Proceso
  psMotivoIndicencia  :  Motivo de la Incidencia
  pnOidSolicitud     :  Oid Solicitud
  pnNumeroPremio     : Numero de Premio
  psCodigoVenta      : Codigo de Venta
  psDescripcionPremio : Descripcion Premio
  pnNumeroUnidades : Numero de Unidades del Premio

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_REGIS_INCID(psNumeroConcurso           VARCHAR2,
                             psCodigoCliente            VARCHAR2,
                             psCodigoPeriodo            VARCHAR2,
                             psCodigoProceso            VARCHAR2,
                             psMotivoIndicencia         VARCHAR2,
                             pnOidSolicitud             NUMBER,
                             pnNumeroPremio             NUMBER := NULL,
                             psCodigoVenta              VARCHAR2 := NULL,
                             psDescripcionPremio        VARCHAR2 := NULL,
                             pnNumeroUnidades           NUMBER := NULL);

/**************************************************************************
Descripcion       : Genera las solicitudes de premiación del Programa Love
Fecha Creacion    : 23/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                       GP4: GP4
  psCodigoRegion     : Codigo de Region
  psCodigoZona       : Codigo de Zona
  psFechaFacturacion  :  Fecha de Facturacion
  psCodigoUsuario     : Usuario que ejecuta el Proceso

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_ATENC_PREMI
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psIndicadorProceso         VARCHAR2,
   psCodigoRegion             VARCHAR2,
   psCodigoZona               VARCHAR2,
   psFechaFacturacion         VARCHAR2,
   psCodigoUsuario            VARCHAR2);

/**************************************************************************
Descripcion        : Recupera el Oid Concurso vigente del programa LOVE para
                     Despacho
Fecha Creacion     : 28/10/2009
Parametros Entrada :
           psCodigoPais : Codigo Pais
           psCodigoPeriodo : Codigo Perido
           psCodigoCanal : Codigo Canal
           psCodigoPeriodo : Codigo Periodo
           pnNumeroPeriodos : Numero Periodos

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_DEVUE_CONCU_DESPA(psCodigoPais      VARCHAR2,
                                  psCodigoMarca     VARCHAR2,
                                  psCodigoCanal     VARCHAR2,
                                  psCodigoPeriodo   VARCHAR2,
                                  pnNumeroPeriodos  NUMBER)
RETURN NUMBER;

/**************************************************************************
Descripcion       : Elimina Premios Elegidos No Atentidos
Fecha Creacion    : 29/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                       GP4: GP4
  psCodigoRegion     : Codigo de Region
  psCodigoZona       : Codigo de Zona

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_ELIMI_PREMI_NOATE
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psIndicadorProceso         VARCHAR2,
   psCodigoRegion             VARCHAR2,
   psCodigoZona               VARCHAR2);

/**************************************************************************
Descripcion       : Elimina Puntaje por incumplir con el numero maximo de
                    campañas sin pasar pedido
Fecha Creacion    : 29/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                       GP4: GP4

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_ELIMI_PUNTA
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psIndicadorProceso         VARCHAR2);


/**************************************************************************
Descripcion       : Registra una incidencia producida de alguna validacion que no
                    paso una determinada solicitud para cualquier de los procesos de LOVE
Fecha Creacion    : 06/11/2009
Parametros Entrada:
  psNumeroConcurso :  Numero de Concurso
  psCodigoCliente  :  Codigo de Cliente
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoProceso  :  Codigo de Proceso
  psMotivoIndicencia  :  Motivo de la Incidencia
  pnOidSolicitud     :  Oid Solicitud
  pnNumeroPremio     : Numero de Premio
  psCodigoVenta      : Codigo de Venta
  psDescripcionPremio : Descripcion Premio
  pnNumeroUnidades : Numero de Unidades del Premio

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_REGIS_INCID_INDEP(psNumeroConcurso           VARCHAR2,
                                   psCodigoCliente            VARCHAR2,
                                   psCodigoPeriodo            VARCHAR2,
                                   psCodigoProceso            VARCHAR2,
                                   psMotivoIndicencia         VARCHAR2,
                                   pnOidSolicitud             NUMBER,
                                   pnNumeroPremio             NUMBER := NULL,
                                   psCodigoVenta              VARCHAR2 := NULL,
                                   psDescripcionPremio        VARCHAR2 := NULL,
                                   pnNumeroUnidades           NUMBER := NULL);


END LOV_PKG_PROCE;
/

CREATE OR REPLACE PACKAGE BODY LOV_PKG_PROCE IS

/**************************************************************************
Descripcion       : Generar Puntaje por Compras para el programa LOVE
Fecha Creacion    : 06/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                       GP4: GP4
  psCodigoRegion     : Codigo de Region
  psCodigoZona       : Codigo de Zona
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_GENER_PUNTA_COMPR
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psIndicadorProceso         VARCHAR2,
   psCodigoRegion             VARCHAR2,
   psCodigoZona               VARCHAR2,
   psFechaFacturacion         VARCHAR2)
IS
  lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo                CRA_PERIO.OID_PERI%TYPE;

  lnOidConcurso               INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  lsNumeroConcurso            INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
  lsCodigoPeriodoIni          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodigoPeriodoFin          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidDirigido               INC_CONCU_PARAM_GENER.DIRI_OID_DIRI%TYPE;
  lnIndParticipacion          INC_PLANT_CONCU.VAL_PART%TYPE;

  lsCodTipoCliente            MAE_TIPO_CLIEN.COD_TIPO_CLIE%TYPE;

  lbParticipante              BOOLEAN;
  lnTotal                     NUMBER(12);
  lnTotalSolic                NUMBER(12);
  lnTotalUnidades             NUMBER(12);
  lnMontoVenta                NUMBER(12,2);
  lsResult                    VARCHAR2(100);
  lnValidacion                NUMBER(1);

  lsCodigoCategoria           LOV_FACTO_PUNTA_CATEG.COD_CATE%TYPE;
  lnPuntaje                   INC_SOLIC_CONCU_PUNTA.NUM_PUNT%TYPE;
  lnFactorMultiplicador       LOV_FACTO_PUNTA_CATEG.VAL_FACT_MULT%TYPE;
  lnOidCuenta                 INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;

  lnPedido                    NUMBER;
  lnTotalAmbitoGeografico     NUMBER;

CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER, oidConcurso NUMBER, indAmbitoGeografico NUMBER) IS
  SELECT psc.Oid_Soli_Cabe, psc.perd_oid_peri, psc.clie_oid_clie,
         ts.COD_TIPO_SOLI, psc.FEC_PROG_FACT, psc.Soca_Oid_Docu_Refe, cli.Cod_Clie
   FROM PED_SOLIC_CABEC psc,
        PED_TIPO_SOLIC_PAIS tsp,
        (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI IN ('SOC','SDDN','SDAN','SDAA')) ts,
        ZON_ZONA zon,
        ZON_REGIO reg,
        MAE_CLIEN cli
    WHERE psc.PAIS_OID_PAIS = oidPais
      AND psc.PERD_OID_PERI = oidPeriodo
      AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
      AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
      AND psc.zzon_oid_zona = zon.oid_zona
      AND zon.zorg_oid_regi = reg.oid_regi
      AND psc.CLIE_OID_CLIE = cli.OID_CLIE
      AND (( (psIndicadorProceso = PROCESO_GP4) AND
                       (
                         ((psc.FEC_FACT IS NULL) AND (psc.GRPR_OID_GRUP_PROC = 4) AND (ts.COD_TIPO_SOLI = 'SOC'))
                           OR
                         ((psc.FEC_FACT = TO_DATE(psFechaFacturacion,'DD/MM/YYYY')) AND (psc.GRPR_OID_GRUP_PROC = 5)
                                                                      AND (ts.COD_TIPO_SOLI <> 'SOC'))
                       )
            ) OR
            ((psIndicadorProceso = PROCESO_CIERRE_ZONA) AND
                       (
                         (psc.FEC_FACT IS NULL) AND (psc.GRPR_OID_GRUP_PROC = 4) AND (zon.cod_zona = psCodigoZona)
                                                                                 AND (zon.ind_acti = 1)
                       )
            ) OR
            ((psIndicadorProceso = PROCESO_CIERRE_REGION) AND
                       (
                         (psc.FEC_FACT IS NOT NULL) AND (psc.GRPR_OID_GRUP_PROC = 5) AND (reg.cod_regi = psCodigoRegion)
                                                                                     AND (reg.ind_acti = 1)
                       )
            ) OR
            ((psIndicadorProceso = PROCESO_CIERRE_CAMPANA) AND
                       (
                         (psc.FEC_FACT IS NOT NULL) AND (psc.GRPR_OID_GRUP_PROC = 5)
                       )
            )
          )
      AND NOT EXISTS (SELECT SOCA_OID_SOLI_CABE
                        FROM LOV_SOLIC_COMPR con
                        WHERE con.soca_oid_soli_cabe = psc.oid_soli_cabe
                          AND con.copa_oid_para_gral = oidConcurso)
      AND ((indAmbitoGeografico=0) OR
           (indAmbitoGeografico>0 AND (zon.zorg_oid_regi, zon.oid_zona) IN
                                    (SELECT z.zorg_oid_regi, z.oid_zona
                                       FROM inc_concu_param_gener c,
                                            inc_ambit_geogr a,
                                            zon_zona z, zon_regio r, zon_sub_geren_venta v
                                      WHERE c.oid_para_gral = oidConcurso
                                        AND c.oid_para_gral = a.copa_oid_para_gral
                                        AND a.zzon_oid_zona IS NOT NULL
                                        AND a.zzon_oid_zona = z.oid_zona
                                        AND r.oid_regi = z.zorg_oid_regi
                                        AND r.zsgv_oid_subg_vent = v.oid_subg_vent
                                        AND v.cod_subg_vent = '01'
                                      UNION
                                      SELECT z.zorg_oid_regi, z.oid_zona
                                        FROM inc_concu_param_gener c,
                                             inc_ambit_geogr a,
                                             zon_zona z, zon_regio r, zon_sub_geren_venta v
                                       WHERE c.oid_para_gral = oidConcurso
                                         AND c.oid_para_gral = a.copa_oid_para_gral
                                         AND a.zorg_oid_regi IS NOT NULL
                                         AND a.zzon_oid_zona IS NULL
                                         AND a.zorg_oid_regi = z.zorg_oid_regi
                                         AND r.oid_regi = z.zorg_oid_regi
                                         AND r.zsgv_oid_subg_vent = v.oid_subg_vent
                                         AND v.cod_subg_vent = '01')));

  TYPE interfazPedidos IS RECORD
  (
   oidSolicitud              PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
   oidPeriodo                PED_SOLIC_CABEC.PERD_OID_PERI%TYPE,
   oidCliente                MAE_CLIEN.OID_CLIE%TYPE,
   codTipoSolicitud          PED_TIPO_SOLIC.COD_TIPO_SOLI%TYPE,
   fecProgFacturacion        PED_SOLIC_CABEC.FEC_PROG_FACT%TYPE,
   oidSolicitudRefer         PED_SOLIC_CABEC.SOCA_OID_DOCU_REFE%TYPE,
   codCliente                MAE_CLIEN.COD_CLIE%TYPE
  );

  TYPE interfazPedidosTab  IS TABLE OF interfazPedidos;
  interfazRecordN interfazPedidosTab;

BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  --Recuperamos el oid y Concurso del Programa LOVE vigente para el periodo de Proceso
  lnOidConcurso := LOV_FN_DEVUE_CONCU_VIGEN(psCodigoPais, psCodigoPeriodo);

  IF (lnOidConcurso IS NULL) THEN --No existe concurso, finaliza el Proceso
    RETURN;
  END IF;

  --Obtenemos el numero de Concurso
  SELECT gen.Num_Conc, gen.diri_oid_diri, cord.cod_peri, corh.cod_peri,
         (SELECT COUNT(geo.oid_ambito_geografico)
          FROM INC_AMBIT_GEOGR geo WHERE geo.copa_oid_para_gral = gen.oid_para_gral)
    INTO lsNumeroConcurso, lnOidDirigido, lsCodigoPeriodoIni, lsCodigoPeriodoFin, lnTotalAmbitoGeografico
    FROM INC_CONCU_PARAM_GENER gen,
         CRA_PERIO crad, SEG_PERIO_CORPO cord,
         CRA_PERIO crah, SEG_PERIO_CORPO corh
   WHERE oid_para_gral = lnOidConcurso
     AND gen.perd_oid_peri_desd = crad.oid_peri
     AND crad.peri_oid_peri = cord.oid_peri
     AND gen.perd_oid_peri_hast = crah.oid_peri
     AND crah.peri_oid_peri = corh.oid_peri;

  --Recuperamos el Indicador Participantes del Concurso
   SELECT VAL_PART
     INTO lnIndParticipacion
     FROM INC_PLANT_CONCU pla, INC_CONCU_PARAM_GENER gen
    WHERE pla.OID_PLAN_CONC = gen.PLC2_OID_PLAN_CONC
      AND gen.OID_PARA_GRAL = lnOidConcurso;

   --(1) PROCESAMOS A LOS PEDIDOS
    OPEN c_Pedidos(lnOidPais, lnOidPeriodo, lnOidConcurso, lnTotalAmbitoGeografico);
    LOOP
       FETCH c_Pedidos BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
       IF interfazRecordN.COUNT > 0 THEN

          FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
            lnPedido := interfazRecordN(x).oidSolicitud;

            --Para el caso de las Anulaciones y Devoluciones, se valida
            --que el periodo de su respectiva solicitud de referencia debe
            --pertenecer al rango de periodos de vigencia del concurso
            IF((interfazRecordN(x).codTipoSolicitud = 'SDDN') OR
               (interfazRecordN(x).codTipoSolicitud = 'SDAN') OR
               (interfazRecordN(x).codTipoSolicitud = 'SDAA')) THEN
              lnValidacion := LOV_FN_VALID_PERIO_REFER(interfazRecordN(x).oidSolicitudRefer,
                                                     lsCodigoPeriodoIni, lsCodigoPeriodoFin);
            ELSE
              lnValidacion := 1;
            END IF;

            IF(lnValidacion = 1) THEN
              --Para el caso de Filtro de Participantes ES ACTIVO
              lbParticipante := TRUE;
              IF(lnIndParticipacion = 1)THEN
                BEGIN
                  SELECT Y.COD_TIPO_CLIE
                    INTO lsCodTipoCliente
                    FROM MAE_CLIEN_TIPO_SUBTI X, MAE_TIPO_CLIEN Y
                   WHERE X.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                     AND Y.OID_TIPO_CLIE=X.TICL_OID_TIPO_CLIE
                     AND X.IND_PPAL ='1';

                  IF(lnOidDirigido = 1)THEN --Consultoras
                    IF(lsCodTipoCliente <> '02')THEN --NO ES CONSULTORA
                      lbParticipante := FALSE;
                    END IF;
                  END IF;

                  IF(lnOidDirigido = 2)THEN --Gerentes
                    IF(lsCodTipoCliente <> '04')THEN --NO ES GERENTE
                      lbParticipante := FALSE;
                    END IF;
                  END IF;

                EXCEPTION
                  WHEN OTHERS THEN
                    lbParticipante := FALSE;
                    LOV_PR_REGIS_INCID(lsNumeroConcurso, interfazRecordN(x).codCliente,
                      psCodigoPeriodo,'LOV-1','Hubo problemas al recuperar el tipo de cliente principal',
                      interfazRecordN(x).oidSolicitud);
                END;

              ELSE
                --Indicador Participantes desactivado
                lsResult := LOV_FN_VALID_CLASI_CONCU(lnOidConcurso, interfazRecordN(x).oidCliente);

                IF(lsResult <> '1') THEN
                  lbParticipante := FALSE;
                END IF;
              END IF;


              IF(lbParticipante) THEN
                --Calculamos el monto Venta de la Solicitud
                SELECT SUM((CASE WHEN ((pos.Ind_Ctrl_Liqu = 1) OR (pos.ind_limi_vent = 1))
                                     THEN pos.num_unid_compr
                                 WHEN ((pos.Ind_Ctrl_Liqu IS NULL) OR (pos.ind_limi_vent IS NULL))
                                     THEN pos.num_unid_dema_real
                             ELSE 0 END)),
                        SUM((CASE WHEN ((pos.Ind_Ctrl_Liqu = 1) OR (pos.ind_limi_vent = 1))
                                    THEN (pos.num_unid_compr * pos.val_prec_cata_unit_loca)
                                  WHEN ((pos.Ind_Ctrl_Liqu IS NULL) OR (pos.ind_limi_vent IS NULL))
                                    THEN (pos.num_unid_dema_real * pos.val_prec_cata_unit_loca)
                             ELSE 0 END))
                  INTO lnTotalUnidades, lnMontoVenta
                  FROM PED_SOLIC_POSIC pos
                 WHERE pos.soca_oid_soli_cabe = interfazRecordN(x).oidSolicitud;

                --Se recupera la categoria del Programa Love que tiene el cliente de la Solicitud
                lsCodigoCategoria := LOV_FN_CATEG_CLASI_CONCU(interfazRecordN(x).oidCliente);

                IF (lsCodigoCategoria IS NOT NULL) THEN
                  lnFactorMultiplicador := NULL;

                  --Se recupera el factor puntaje a utilizar
                  BEGIN
                    SELECT fac.val_fact_mult
                      INTO lnFactorMultiplicador
                      FROM LOV_FACTO_PUNTA_CATEG fac
                     WHERE fac.cod_conc = lsNumeroConcurso
                       AND fac.cod_cate = lsCodigoCategoria
                       AND fac.ind_acti = '1';
                  EXCEPTION
                    WHEN OTHERS THEN
                      --No se recupera categoria LOVE para la solicitud
                      LOV_PR_REGIS_INCID(lsNumeroConcurso, interfazRecordN(x).codCliente,
                        psCodigoPeriodo,'LOV-1','Falta factor multiplicador para la categoría',
                        interfazRecordN(x).oidSolicitud);
                  END;

                  IF (lnFactorMultiplicador IS NOT NULL) THEN
                    --Se calcula el puntaje a asignar
                    lnPuntaje := TRUNC(lnMontoVenta * lnFactorMultiplicador);

                    --Insertamos en la tabla LOV_SOLIC_COMPR las solicitudes procesadas
                    INSERT INTO LOV_SOLIC_COMPR
                      (COPA_OID_PARA_GRAL, SOCA_OID_SOLI_CABE, FEC_ULTI_ACTU)
                    VALUES
                      (lnOidConcurso, interfazRecordN(x).oidSolicitud, SYSDATE);

                    --Se verifica si para el concurso, cliente y periodo, existe un registro en INC_SOLIC_CONCU_PUNTA
                    SELECT COUNT(*)
                      INTO lnTotalSolic
                      FROM INC_SOLIC_CONCU_PUNTA soc
                     WHERE soc.copa_oid_para_gral = lnOidConcurso
                       AND soc.soca_oid_soli_cabe = interfazRecordN(x).oidSolicitud
                       AND soc.perd_oid_peri = interfazRecordN(x).oidPeriodo;

                    IF(lnTotalSolic = 0) THEN
                      --Se inserta un registro en la tabla INC_SOLIC_CONCU_PUNTA
                      INSERT INTO INC_SOLIC_CONCU_PUNTA
                        (OID_SOLI_CONC_PUNT, NUM_PUNT, VAL_PUNT_BONI,
                         VAL_PUNT_FALT_NANU, FEC_DOCU,
                         IND_ANUL,
                         COPA_OID_PARA_GRAL, SOCA_OID_SOLI_CABE, PERD_OID_PERI,
                         CLIE_OID_CLIE, IMP_MONT, CLIE_OID_CLIE_GERE, NUM_UNID)
                      VALUES
                        (INC_SOCP_SEQ.NEXTVAL, lnPuntaje, 0,
                        0, interfazRecordN(x).fecProgFacturacion,
                        DECODE(interfazRecordN(x).codTipoSolicitud,'SDAN',1,'SDAA',1,0),
                        lnOidConcurso, interfazRecordN(x).oidSolicitud, interfazRecordN(x).oidPeriodo,
                        interfazRecordN(x).oidCliente, lnMontoVenta, NULL, lnTotalUnidades);
                    END IF;

                    --Se inserta el puntaje respectivo en la tabla INC_CUENT_CORRI_PUNTO
                    SELECT INC_CUCP_SEQ.NEXTVAL INTO lnOidCuenta FROM DUAL;

                    INSERT INTO INC_CUENT_CORRI_PUNTO
                      (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
                       NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
                       CLIE_OID_CLIE, PERD_OID_PERI,
                       TMOV_OID_TIPO_MOVI,
                       FEC_ULTI_ACTU, VAL_DESC)
                    VALUES
                      (lnOidCuenta, lnOidCuenta, lnPuntaje,
                       0, TRUNC(SYSDATE), lnOidConcurso,
                       interfazRecordN(x).oidCliente, interfazRecordN(x).oidPeriodo,
                       DECODE(interfazRecordN(x).codTipoSolicitud,'SOC',1,2),
                       SYSDATE, DECODE(interfazRecordN(x).codTipoSolicitud,
                                      'SOC','Puntaje por Compras Campaña ' || psCodigoPeriodo,
                                      'SDDN','Puntaje por Devolución en Campaña ' || psCodigoPeriodo,
                                      'SDAN','Puntaje por Anulación en Campaña ' || psCodigoPeriodo,
                                      'SDAA','Puntaje por Anulación en Campaña ' || psCodigoPeriodo
                                      ));

                    --Se verifica si para el concurso, cliente y periodo, existe un registro en
                    --Candidata Ganadora (INC_CANDI_GANAD)
                    SELECT COUNT(*)
                      INTO lnTotal
                      FROM INC_CANDI_GANAD gan
                     WHERE gan.copa_oid_para_gral = lnOidConcurso
                       AND gan.clie_oid_clie = interfazRecordN(x).oidCliente
                       AND gan.perd_oid_peri = interfazRecordN(x).oidPeriodo;

                    IF(lnTotal = 0) THEN
                      SELECT COUNT(*)
                        INTO lnTotal
                        FROM INC_CANDI_GANAD gan
                       WHERE gan.copa_oid_para_gral = lnOidConcurso
                         AND gan.clie_oid_clie = interfazRecordN(x).oidCliente;

                      INSERT INTO INC_CANDI_GANAD
                        (OID_CAND_GANA, IND_META_SUPE, VAL_REQU_PREM_SUPE,
                         PERD_OID_PERI, COPA_OID_PARA_GRAL, BINC_OID_BASE_INCU,
                         PERD_OID_PERI_EVAL, CLIE_OID_CLIE, FEC_ULTI_ACTU, NUM_PERI_EVAL)
                      VALUES
                        (INC_CAGA_SEQ.NEXTVAL, 0, 0,
                         interfazRecordN(x).oidPeriodo, lnOidConcurso, NULL,
                         NULL, interfazRecordN(x).oidCliente, SYSDATE, lnTotal + 1);
                    END IF;
                  END IF;
                ELSE
                  --No se recupera categoria LOVE para la solicitud
                  LOV_PR_REGIS_INCID(lsNumeroConcurso, interfazRecordN(x).codCliente,
                    psCodigoPeriodo,'LOV-1','Falta la clasificación asociada al programa Love para la consultora',
                    interfazRecordN(x).oidSolicitud);

                END IF;

              ELSE --No paso la validacion de Participacion
                  LOV_PR_REGIS_INCID(lsNumeroConcurso, interfazRecordN(x).codCliente,
                    psCodigoPeriodo,'LOV-1','No paso el filtro de Participantes del Concurso',
                    interfazRecordN(x).oidSolicitud);

              END IF;

            END IF;

          END LOOP;
       END IF;
       EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_PR_GENER_PUNTA_COMPR: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END LOV_PR_GENER_PUNTA_COMPR;

/**************************************************************************
Descripcion        : Recupera el Oid Concurso vigente del programa LOVE
Fecha Creacion     : 06/10/2009
Parametros Entrada :
           psCodigoPais : Codigo Pais
           psCodigoPeriodo : Codigo Perido

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_DEVUE_CONCU_VIGEN(psCodigoPais      VARCHAR2,
                                  psCodigoPeriodo   VARCHAR2)
RETURN NUMBER IS
  lnOidConcurso       INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
BEGIN
  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  SELECT gen.Oid_Para_Gral
    INTO lnOidConcurso
    FROM INC_CONCU_PARAM_GENER gen, INC_CLASI_CONCU cla,
         CRA_PERIO crad, SEG_PERIO_CORPO cord,
         CRA_PERIO crah, SEG_PERIO_CORPO corh
   WHERE gen.CCON_OID_CLAS_CONC = cla.Oid_Clas_Conc
     AND gen.pais_oid_pais = lnOidPais
     AND cla.cod_clas_conc = 'A'
     AND gen.ind_acti = 1
     AND gen.perd_oid_peri_desd = crad.oid_peri
     AND crad.peri_oid_peri = cord.oid_peri
     AND gen.perd_oid_peri_hast = crah.oid_peri
     AND crah.peri_oid_peri = corh.oid_peri
     AND cord.cod_peri <= psCodigoPeriodo
     AND corh.cod_peri >= psCodigoPeriodo;

  RETURN lnOidConcurso;

EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;

END LOV_FN_DEVUE_CONCU_VIGEN;

/**************************************************************************
Descripcion        : Valida las clasificaciones del cliente en las clasificaciones del concurso
Fecha Creacion     :  06/10/2009
Parametros Entrada :
           psOidConcurso : oidConcurso
           psOidCliente :oidCliente
Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_VALID_CLASI_CONCU(pnOidConcurso        NUMBER,
                                  pnOidCliente         NUMBER)
  RETURN VARCHAR2 IS

   CURSOR cursorClasifCliente(lnOidClasificacion     NUMBER,
                              lnOidTipoClasificacion NUMBER,
                              lnOidTipoCliente       NUMBER,
                              lnOidSubTipoCliente    NUMBER)
   IS
     SELECT COUNT(1) AS lnCont
     FROM MAE_CLIEN_CLASI X,MAE_CLIEN_TIPO_SUBTI Y
      WHERE X.CTSU_OID_CLIE_TIPO_SUBT = Y.OID_CLIE_TIPO_SUBT
          AND Y.CLIE_OID_CLIE =pnOidCliente
          AND Y.IND_PPAL='1'
          AND (lnOidClasificacion IS NULL OR X.CLAS_OID_CLAS = lnOidClasificacion)
          AND (lnOidTipoClasificacion IS NULL OR X.TCCL_OID_TIPO_CLASI = lnOidTipoClasificacion)
          AND (lnOidTipoCliente IS NULL OR Y.TICL_OID_TIPO_CLIE = lnOidTipoCliente)
          AND (lnOidSubTipoCliente IS NULL OR Y.SBTI_OID_SUBT_CLIE = lnOidSubTipoCliente);

   CURSOR cursorClasifConcurso
   IS
      SELECT C.*
      FROM INC_CLASI_PARTI_CONCU A,
           INC_PARTI_CONCU_CABEC B,
           INC_PARTI_CONCU_DETAL C
      WHERE A.COPA_OID_PARA_GRAL = pnOidConcurso
       AND B.OID_PART_CONC_CABE = A.PACI_OID_PART_CONC_CABE
       AND C.PACI_OID_PART_CONC_CABE=B.OID_PART_CONC_CABE;

  lnOidClasificacion       NUMBER;
  lsResult                 VARCHAR2(1);
BEGIN

  lsResult := '1';
  FOR cClasifConcurso IN cursorClasifConcurso LOOP
    lsResult := '0';
    --recorremos las clasificaciones del concurso para encontralas en las
    --clasificaciones del cliente la validacion puede ser viceversa
    FOR cClasifCliente IN cursorClasifCliente(cClasifConcurso.CLAS_OID_CLAS,
                                              cClasifConcurso.TCCL_OID_TIPO_CLAS,
                                              cClasifConcurso.TICL_OID_TIPO_CLIE,
                                              cClasifConcurso.SBTI_OID_SUBT_CLIE ) LOOP
      IF(cClasifCliente.lnCont > 0) THEN
        RETURN '1';
      END IF;
    END LOOP;

  END LOOP;

  RETURN lsResult;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_FN_VALID_CLASI_CONCU: ' || ls_sqlerrm);

END LOV_FN_VALID_CLASI_CONCU;

/**************************************************************************
Descripcion        : Obtiene la Categoria de acuerdo a la clasificacion LOVE
                     que tenga el Cliente
Fecha Creacion     :  06/10/2009
Parametros Entrada :
           psOidCliente :  oidCliente
Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_CATEG_CLASI_CONCU(pnOidCliente         NUMBER)
RETURN VARCHAR2 IS

   CURSOR cursorClasifCliente(lnOidClasificacion     NUMBER,
                              lnOidTipoClasificacion NUMBER,
                              lnOidTipoCliente       NUMBER,
                              lnOidSubTipoCliente    NUMBER)
   IS
     SELECT COUNT(1) AS lnCont
     FROM MAE_CLIEN_CLASI X,MAE_CLIEN_TIPO_SUBTI Y
      WHERE X.CTSU_OID_CLIE_TIPO_SUBT = Y.OID_CLIE_TIPO_SUBT
          AND Y.CLIE_OID_CLIE =pnOidCliente
          AND Y.IND_PPAL='1'
          AND (lnOidClasificacion IS NULL OR X.CLAS_OID_CLAS = lnOidClasificacion)
          AND (lnOidTipoClasificacion IS NULL OR X.TCCL_OID_TIPO_CLASI = lnOidTipoClasificacion)
          AND (Y.TICL_OID_TIPO_CLIE = lnOidTipoCliente)
          AND (lnOidSubTipoCliente IS NULL OR Y.SBTI_OID_SUBT_CLIE = lnOidSubTipoCliente);

   CURSOR cursorClasifConcurso
   IS
      SELECT CLAS_OID_CLAS, TCCL_OID_TIPO_CLAS, SBTI_OID_SUBT_CLIE, TICL_OID_TIPO_CLIE, COD_CATE
       FROM LOV_CATEG
       ORDER BY CLAS_OID_CLAS, TCCL_OID_TIPO_CLAS, SBTI_OID_SUBT_CLIE, TICL_OID_TIPO_CLIE;

BEGIN

  FOR cClasifConcurso IN cursorClasifConcurso LOOP
    --recorremos las clasificaciones del concurso para encontralas en las
    --clasificaciones del cliente la validacion puede ser viceversa
    FOR cClasifCliente IN cursorClasifCliente(cClasifConcurso.CLAS_OID_CLAS,
                                              cClasifConcurso.TCCL_OID_TIPO_CLAS,
                                              cClasifConcurso.TICL_OID_TIPO_CLIE,
                                              cClasifConcurso.SBTI_OID_SUBT_CLIE ) LOOP
      IF(cClasifCliente.lnCont > 0) THEN
        RETURN cClasifConcurso.COD_CATE;
      END IF;
    END LOOP;

  END LOOP;

  RETURN NULL;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_FN_CATEG_CLASI_CONCU: ' || ls_sqlerrm);

END LOV_FN_CATEG_CLASI_CONCU;


/**************************************************************************
Descripcion        : Valida si la campaña de la solicitu de referencia
                     se encuentra dentro del rango de periodos
Fecha Creacion     :  09/10/2009
Parametros Entrada :
           pnOidSoliCabeRefe : oid Solicitud Referencia
           psCodigoPeriodoIni :  codigo Periodo Inicio
           psCodigoPeriodoFin :  codigo Periodo Fin

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_VALID_PERIO_REFER(pnOidSoliCabeRefe       NUMBER,
                                  psCodigoPeriodoIni      VARCHAR2,
                                  psCodigoPeriodoFin      VARCHAR2)
RETURN NUMBER IS
  lnOcurrencia   NUMBER;
BEGIN
  SELECT COUNT(*)
    INTO lnOcurrencia
    FROM PED_SOLIC_CABEC ped, CRA_PERIO cra, SEG_PERIO_CORPO cor
          ,ped_solic_cabec ped_orig
          ,ped_tipo_solic_pais tsp
          ,ped_tipo_solic ts
   WHERE ped.oid_soli_cabe = pnOidSoliCabeRefe
     AND cra.oid_peri = ped.perd_oid_peri
     AND cor.oid_peri = cra.peri_oid_peri
     AND cor.cod_peri >= psCodigoPeriodoIni
     AND cor.cod_peri <= psCodigoPeriodoFin
       and ped.oid_soli_cabe = ped_orig.soca_oid_soli_cabe
       and ped_orig.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       and ts.cod_tipo_soli = 'SOC';

  IF(lnOcurrencia>0) THEN
    RETURN 1;
  ELSE
    RETURN 0;
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_FN_VALID_PERIO_REFER: ' || ls_sqlerrm);

END LOV_FN_VALID_PERIO_REFER;


/**************************************************************************
Descripcion        : Recupera  la campaña de la solicitu de referencia
Fecha Creacion     :  12/10/2009
Parametros Entrada :
           pnOidSoliCabeRefe : oid Solicitud Referencia

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_DEVUE_PERIO_REFER(pnOidSoliCabeRefe       NUMBER)
RETURN VARCHAR2 IS
  lsCodigoPeriodo   SEG_PERIO_CORPO.COD_PERI%TYPE;
BEGIN
  SELECT cor.Cod_Peri
    INTO lsCodigoPeriodo
    FROM PED_SOLIC_CABEC ped, CRA_PERIO cra, SEG_PERIO_CORPO cor
   WHERE ped.oid_soli_cabe = pnOidSoliCabeRefe
     AND cra.oid_peri = ped.perd_oid_peri
     AND cor.oid_peri = cra.peri_oid_peri;

  RETURN lsCodigoPeriodo;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_FN_DEVUE_PERIO_REFER: ' || ls_sqlerrm);

END LOV_FN_DEVUE_PERIO_REFER;


/**************************************************************************
Descripcion       : Generar Puntaje por Constancia para el programa LOVE
Fecha Creacion    : 12/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                       GP4: GP4
  psCodigoRegion     : Codigo de Region
  psCodigoZona       : Codigo de Zona
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_GENER_PUNTA_CONST
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psIndicadorProceso         VARCHAR2,
   psCodigoRegion             VARCHAR2,
   psCodigoZona               VARCHAR2,
   psFechaFacturacion         VARCHAR2)
IS
  lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo                CRA_PERIO.OID_PERI%TYPE;
  lnOidPeriodoRefe            CRA_PERIO.OID_PERI%TYPE;

  lnOidConcurso               INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  lsNumeroConcurso            INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
  lsCodigoPeriodoIni          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodigoPeriodoFin          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidDirigido               INC_CONCU_PARAM_GENER.DIRI_OID_DIRI%TYPE;
  lnIndParticipacion          INC_PLANT_CONCU.VAL_PART%TYPE;

  lsCodTipoCliente            MAE_TIPO_CLIEN.COD_TIPO_CLIE%TYPE;

  lbParticipante              BOOLEAN;
  lnTotal                     NUMBER(12);
  lsResult                    VARCHAR2(100);
  lnValidacion                NUMBER(1);

  lnOidCuenta                 INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;
  lnPedido                    NUMBER;
  lnTotalAmbitoGeografico     NUMBER;
  lsIndicadorPedido           LOV_PEDID_CONST.IND_PEDI%TYPE;
  lsCodigoPeriodoRefe         SEG_PERIO_CORPO.COD_PERI%TYPE;

  lnTotalPedidosCons          LOV_CLIEN_CONST.NUM_PEDI_CONS%TYPE;
  lnTotalPedidos              LOV_CLIEN_CONST.NUM_PEDI_TOTA%TYPE;
  lnPuntaje                   LOV_RANGO_PUNTA_CONST.VAL_PUNT%TYPE;

CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER, oidConcurso NUMBER, indAmbitoGeografico NUMBER) IS
  SELECT psc.OID_SOLI_CABE,
         psc.PERD_OID_PERI,
         psc.CLIE_OID_CLIE,
         ts.COD_TIPO_SOLI,
         psc.FEC_PROG_FACT,
         cli.COD_CLIE,
         psc.SOCA_OID_DOCU_REFE
   FROM PED_SOLIC_CABEC psc,
        PED_TIPO_SOLIC_PAIS tsp,
        (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI IN ('SOC','SDAN','SDAA')) ts,
        ZON_ZONA zon,
        ZON_REGIO reg,
        MAE_CLIEN cli
    WHERE psc.PAIS_OID_PAIS = oidPais
      AND psc.PERD_OID_PERI = oidPeriodo
      AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
      AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
      AND psc.ZZON_OID_ZONA = zon.OID_ZONA
      AND zon.zorg_oid_regi = reg.oid_regi
      AND psc.CLIE_OID_CLIE = cli.OID_CLIE
      AND (( (psIndicadorProceso = PROCESO_GP4) AND
                       (
                         ((psc.FEC_FACT IS NULL) AND (psc.GRPR_OID_GRUP_PROC = 4) AND (ts.COD_TIPO_SOLI = 'SOC'))
                           OR
                         ((psc.FEC_FACT = TO_DATE(psFechaFacturacion,'DD/MM/YYYY')) AND (psc.GRPR_OID_GRUP_PROC = 5)
                                                                      AND (ts.COD_TIPO_SOLI <> 'SOC'))
                       )
            ) OR
            ((psIndicadorProceso = PROCESO_CIERRE_ZONA) AND
                       (
                         (psc.FEC_FACT IS NULL) AND (psc.GRPR_OID_GRUP_PROC = 4) AND (zon.cod_zona = psCodigoZona)
                                                                                 AND (zon.ind_acti = 1)
                       )
            ) OR
            ((psIndicadorProceso = PROCESO_CIERRE_REGION) AND
                       (
                         (psc.FEC_FACT IS NOT NULL) AND (psc.GRPR_OID_GRUP_PROC = 5) AND (reg.cod_regi = psCodigoRegion)
                                                                                     AND (reg.ind_acti = 1)
                       )
            ) OR
            ((psIndicadorProceso = PROCESO_CIERRE_CAMPANA) AND
                       (
                         (psc.FEC_FACT IS NOT NULL) AND (psc.GRPR_OID_GRUP_PROC = 5)
                       )
            )
          )
      AND NOT EXISTS (SELECT SOCA_OID_SOLI_CABE
                        FROM LOV_SOLIC_CONST con
                        WHERE con.soca_oid_soli_cabe = psc.oid_soli_cabe
                          AND con.copa_oid_para_gral = oidConcurso)
      AND ((indAmbitoGeografico=0) OR
           (indAmbitoGeografico>0 AND (zon.zorg_oid_regi, zon.oid_zona) IN
                                    (SELECT z.zorg_oid_regi, z.oid_zona
                                       FROM inc_concu_param_gener c,
                                            inc_ambit_geogr a,
                                            zon_zona z, zon_regio r, zon_sub_geren_venta v
                                      WHERE c.oid_para_gral = oidConcurso
                                        AND c.oid_para_gral = a.copa_oid_para_gral
                                        AND a.zzon_oid_zona IS NOT NULL
                                        AND a.zzon_oid_zona = z.oid_zona
                                        AND r.oid_regi = z.zorg_oid_regi
                                        AND r.zsgv_oid_subg_vent = v.oid_subg_vent
                                        AND v.cod_subg_vent = '01'
                                      UNION
                                      SELECT z.zorg_oid_regi, z.oid_zona
                                        FROM inc_concu_param_gener c,
                                             inc_ambit_geogr a,
                                             zon_zona z, zon_regio r, zon_sub_geren_venta v
                                       WHERE c.oid_para_gral = oidConcurso
                                         AND c.oid_para_gral = a.copa_oid_para_gral
                                         AND a.zorg_oid_regi IS NOT NULL
                                         AND a.zzon_oid_zona IS NULL
                                         AND a.zorg_oid_regi = z.zorg_oid_regi
                                         AND r.oid_regi = z.zorg_oid_regi
                                         AND r.zsgv_oid_subg_vent = v.oid_subg_vent
                                         AND v.cod_subg_vent = '01')))
      ORDER BY ts.COD_TIPO_SOLI;

  TYPE interfazPedidos IS RECORD
  (
   oidSolicitud              PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
   oidPeriodo                PED_SOLIC_CABEC.PERD_OID_PERI%TYPE,
   oidCliente                MAE_CLIEN.OID_CLIE%TYPE,
   codTipoSolicitud          PED_TIPO_SOLIC.COD_TIPO_SOLI%TYPE,
   fecProgFacturacion        PED_SOLIC_CABEC.FEC_PROG_FACT%TYPE,
   codCliente                MAE_CLIEN.COD_CLIE%TYPE,
   oidSolicitudRefer         PED_SOLIC_CABEC.SOCA_OID_DOCU_REFE%TYPE
  );

  TYPE interfazPedidosTab  IS TABLE OF interfazPedidos;
  interfazRecordN interfazPedidosTab;

BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  --Recuperamos el oid y Concurso del Programa LOVE vigente para el periodo de Proceso
  lnOidConcurso := LOV_FN_DEVUE_CONCU_VIGEN(psCodigoPais, psCodigoPeriodo);

  IF (lnOidConcurso IS NULL) THEN --No existe concurso, finaliza el Proceso
    RETURN;
  END IF;

  --Obtenemos el numero de Concurso
  SELECT gen.Num_Conc, gen.diri_oid_diri, cord.cod_peri, corh.cod_peri,
         (SELECT COUNT(geo.oid_ambito_geografico)
          FROM INC_AMBIT_GEOGR geo WHERE geo.copa_oid_para_gral = gen.oid_para_gral)
    INTO lsNumeroConcurso, lnOidDirigido, lsCodigoPeriodoIni, lsCodigoPeriodoFin, lnTotalAmbitoGeografico
    FROM INC_CONCU_PARAM_GENER gen,
         CRA_PERIO crad, SEG_PERIO_CORPO cord,
         CRA_PERIO crah, SEG_PERIO_CORPO corh
   WHERE oid_para_gral = lnOidConcurso
     AND gen.perd_oid_peri_desd = crad.oid_peri
     AND crad.peri_oid_peri = cord.oid_peri
     AND gen.perd_oid_peri_hast = crah.oid_peri
     AND crah.peri_oid_peri = corh.oid_peri;

  --Recuperamos el Indicador Participantes del Concurso
   SELECT VAL_PART
     INTO lnIndParticipacion
     FROM INC_PLANT_CONCU pla, INC_CONCU_PARAM_GENER gen
    WHERE pla.OID_PLAN_CONC = gen.PLC2_OID_PLAN_CONC
      AND gen.OID_PARA_GRAL = lnOidConcurso;

   --(1) PROCESAMOS A LOS PEDIDOS
    OPEN c_Pedidos(lnOidPais, lnOidPeriodo, lnOidConcurso, lnTotalAmbitoGeografico);
    LOOP
       FETCH c_Pedidos BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
       IF interfazRecordN.COUNT > 0 THEN

          FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
            lnPedido := interfazRecordN(x).oidSolicitud;

            --Para el caso de las Anulaciones, se valida
            --que el periodo de su respectiva solicitud de referencia debe
            --pertenecer al rango de periodos de vigencia del concurso
            IF((interfazRecordN(x).codTipoSolicitud = 'SDAN') OR
               (interfazRecordN(x).codTipoSolicitud = 'SDAA')) THEN
              lnValidacion := LOV_FN_VALID_PERIO_REFER(interfazRecordN(x).oidSolicitudRefer,
                                                     lsCodigoPeriodoIni, lsCodigoPeriodoFin);
            ELSE
              lnValidacion := 1;
            END IF;

            IF(lnValidacion = 1) THEN
              --Si es Anulacion, Recuperamos el periodo de la Solicitud de Referencia
              IF((interfazRecordN(x).codTipoSolicitud = 'SDAN') OR
                 (interfazRecordN(x).codTipoSolicitud = 'SDAA')) THEN
                lsCodigoPeriodoRefe := LOV_FN_DEVUE_PERIO_REFER(interfazRecordN(x).oidSolicitudRefer);

                SELECT PERD_OID_PERI INTO lnOidPeriodoRefe
                FROM PED_SOLIC_CABEC WHERE OID_SOLI_CABE = interfazRecordN(x).oidSolicitudRefer;
              END IF;

              --Para el caso de Filtro de Participantes ES ACTIVO
              lbParticipante := TRUE;
              IF(lnIndParticipacion = 1)THEN
                BEGIN
                  SELECT Y.COD_TIPO_CLIE
                    INTO lsCodTipoCliente
                    FROM MAE_CLIEN_TIPO_SUBTI X, MAE_TIPO_CLIEN Y
                   WHERE X.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                     AND Y.OID_TIPO_CLIE=X.TICL_OID_TIPO_CLIE
                     AND X.IND_PPAL ='1';

                  IF(lnOidDirigido = 1)THEN --Consultoras
                    IF(lsCodTipoCliente <> '02')THEN --NO ES CONSULTORA
                      lbParticipante := FALSE;
                    END IF;
                  END IF;

                  IF(lnOidDirigido = 2)THEN --Gerentes
                    IF(lsCodTipoCliente <> '04')THEN --NO ES GERENTE
                      lbParticipante := FALSE;
                    END IF;
                  END IF;

                EXCEPTION
                  WHEN OTHERS THEN
                    lbParticipante := FALSE;
                    LOV_PR_REGIS_INCID(lsNumeroConcurso, interfazRecordN(x).codCliente,
                      psCodigoPeriodo,'LOV-2','Hubo problemas al recuperar el tipo de cliente principal',
                      interfazRecordN(x).oidSolicitud);
                END;
              ELSE
                --Indicador Participantes desactivado
                lsResult := LOV_FN_VALID_CLASI_CONCU(lnOidConcurso, interfazRecordN(x).oidCliente);

                IF(lsResult <> '1') THEN
                  lbParticipante := FALSE;
                END IF;
              END IF;


              IF(lbParticipante) THEN
                --Se valida si ya existe un registro en la tabla LOV_CLIEN_CONST
                SELECT COUNT(1)
                  INTO lnTotal
                  FROM LOV_CLIEN_CONST
                 WHERE COD_CONC = lsNumeroConcurso
                   AND COD_CLIE = interfazRecordN(x).codCliente;

                 IF(lnTotal = 0) THEN
                   INSERT INTO LOV_CLIEN_CONST
                     (COD_CONC, COD_CLIE, NUM_PEDI_CONS,
                      NUM_PEDI_TOTA, FEC_ULTI_ACTU, COD_PERI_INIC_CONC, COD_PERI_INIC_CLIE)
                   VALUES
                     (lsNumeroConcurso, interfazRecordN(x).codCliente, 0,
                      0, SYSDATE, lsCodigoPeriodoIni, psCodigoPeriodo);
                 END IF;

                --Si el tipo de solicitud es un pedido O/C, el indicador Pedido = 1
                --Si el tipo de solicitud es Anulacion, el indicador Pedido = 0
                IF(interfazRecordN(x).codTipoSolicitud = 'SOC') THEN
                  lsIndicadorPedido := '1';

                   --Si el tipo de solicitud es un pedido O/C
                  SELECT COUNT(1)
                    INTO lnTotal
                    FROM LOV_PEDID_CONST
                   WHERE COD_CONC = lsNumeroConcurso
                     AND COD_CLIE = interfazRecordN(x).codCliente
                     AND COD_PERI = psCodigoPeriodo;

                  IF(lnTotal = 0) THEN
                    INSERT INTO LOV_PEDID_CONST
                      (COD_CONC, COD_CLIE, COD_PERI,
                       IND_PEDI, FEC_ULTI_ACTU)
                    VALUES
                      (lsNumeroConcurso, interfazRecordN(x).codCliente, psCodigoPeriodo,
                       lsIndicadorPedido, SYSDATE);
                  ELSE
                    UPDATE LOV_PEDID_CONST
                       SET IND_PEDI = lsIndicadorPedido,
                           FEC_ULTI_ACTU = SYSDATE
                     WHERE COD_CONC = lsNumeroConcurso
                       AND COD_CLIE = interfazRecordN(x).codCliente
                       AND COD_PERI = psCodigoPeriodo;
                  END IF;

                ELSE
                  lsIndicadorPedido := '0';

                   --Si el tipo de solicitud es Anulacion
                  SELECT COUNT(1)
                    INTO lnTotal
                    FROM LOV_PEDID_CONST
                   WHERE COD_CONC = lsNumeroConcurso
                     AND COD_CLIE = interfazRecordN(x).codCliente
                     AND COD_PERI = lsCodigoPeriodoRefe;

                  IF(lnTotal = 0) THEN
                    INSERT INTO LOV_PEDID_CONST
                      (COD_CONC, COD_CLIE, COD_PERI,
                       IND_PEDI, FEC_ULTI_ACTU)
                    VALUES
                      (lsNumeroConcurso, interfazRecordN(x).codCliente, lsCodigoPeriodoRefe,
                       lsIndicadorPedido, SYSDATE);
                  ELSE
                    UPDATE LOV_PEDID_CONST
                       SET IND_PEDI = lsIndicadorPedido,
                           FEC_ULTI_ACTU = SYSDATE
                     WHERE COD_CONC = lsNumeroConcurso
                       AND COD_CLIE = interfazRecordN(x).codCliente
                       AND COD_PERI = lsCodigoPeriodoRefe;
                  END IF;
                END IF;

                --Se calcula el número de pedidos consecutivos y el número total de pedidos pasados
                --para proceder a calcular y abonar puntaje si le corresponde
                lnTotalPedidosCons := LOV_FN_DEVUE_PEDID_CONSE(interfazRecordN(x).codCliente,
                                          lsNumeroConcurso, lsCodigoPeriodoIni, psCodigoPais,
                                          psCodigoMarca, psCodigoCanal, psCodigoPeriodo);

                SELECT COUNT(1)
                  INTO lnTotalPedidos
                  FROM LOV_PEDID_CONST
                 WHERE COD_CONC = lsNumeroConcurso
                   AND COD_CLIE = interfazRecordN(x).codCliente
                   AND IND_PEDI = '1';

                --Se actualiza en la tabla LOV_CLIEN_CONST
                UPDATE LOV_CLIEN_CONST
                   SET NUM_PEDI_CONS = lnTotalPedidosCons,
                       NUM_PEDI_TOTA = lnTotalPedidos
                 WHERE COD_CONC = lsNumeroConcurso
                   AND COD_CLIE = interfazRecordN(x).codCliente;

                --Si el tipo de Solicitud es Pedido O/C, se evalua si el numero de pedidos consecutivos
                --se encuentra en alguno de los rangos del puntaje
                IF(interfazRecordN(x).codTipoSolicitud = 'SOC') THEN
                  lnPuntaje := LOV_FN_DEVUE_PUNTA_CONST(lsNumeroConcurso, lnTotalPedidosCons);

                  IF(lnPuntaje IS NOT NULL) THEN
                    --Se inserta el puntaje respectivo en la tabla INC_CUENT_CORRI_PUNTO
                    SELECT INC_CUCP_SEQ.NEXTVAL INTO lnOidCuenta FROM DUAL;

                    INSERT INTO INC_CUENT_CORRI_PUNTO
                      (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
                       NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
                       CLIE_OID_CLIE, PERD_OID_PERI,
                       TMOV_OID_TIPO_MOVI,
                       FEC_ULTI_ACTU, VAL_DESC)
                    VALUES
                      (lnOidCuenta, lnOidCuenta, lnPuntaje,
                       0, TRUNC(SYSDATE), lnOidConcurso,
                       interfazRecordN(x).oidCliente, interfazRecordN(x).oidPeriodo,
                       1, SYSDATE, 'Puntaje por Constancia Campaña ' || psCodigoPeriodo);

                    --Se verifica si para el concurso, cliente y periodo, existe un registro en
                    --Candidata Ganadora (INC_CANDI_GANAD)
                    SELECT COUNT(*)
                      INTO lnTotal
                      FROM INC_CANDI_GANAD gan
                     WHERE gan.copa_oid_para_gral = lnOidConcurso
                       AND gan.clie_oid_clie = interfazRecordN(x).oidCliente
                       AND gan.perd_oid_peri = interfazRecordN(x).oidPeriodo;

                    IF(lnTotal = 0) THEN
                      SELECT COUNT(*)
                        INTO lnTotal
                        FROM INC_CANDI_GANAD gan
                       WHERE gan.copa_oid_para_gral = lnOidConcurso
                         AND gan.clie_oid_clie = interfazRecordN(x).oidCliente;

                      INSERT INTO INC_CANDI_GANAD
                        (OID_CAND_GANA, IND_META_SUPE, VAL_REQU_PREM_SUPE,
                         PERD_OID_PERI, COPA_OID_PARA_GRAL, BINC_OID_BASE_INCU,
                         PERD_OID_PERI_EVAL, CLIE_OID_CLIE, FEC_ULTI_ACTU, NUM_PERI_EVAL)
                      VALUES
                        (INC_CAGA_SEQ.NEXTVAL, 0, 0,
                         interfazRecordN(x).oidPeriodo, lnOidConcurso, NULL,
                         NULL, interfazRecordN(x).oidCliente, SYSDATE, lnTotal + 1);
                    END IF;

                  ELSE --No paso la validacion de Participacion
                      LOV_PR_REGIS_INCID(lsNumeroConcurso, interfazRecordN(x).codCliente,
                        psCodigoPeriodo,'LOV-2','No existe rango activo para el total pedidos consecutivos del cliente ',
                        interfazRecordN(x).oidSolicitud);

                  END IF;
                END IF;

                --Si el tipo es Anulacion (SDAN o SDAA), se verifica si para el periodo de la solicitud
                --de referencia de la anulacion hubo un abono de puntaje en cuenta corriente
                IF((interfazRecordN(x).codTipoSolicitud = 'SDAN') OR
                   (interfazRecordN(x).codTipoSolicitud = 'SDAA')) THEN

                  BEGIN
                    SELECT pun.NUM_PUNT
                      INTO lnPuntaje
                      FROM INC_CUENT_CORRI_PUNTO pun
                     WHERE pun.copa_oid_para_gral = lnOidConcurso
                       AND pun.clie_oid_clie = interfazRecordN(x).oidCliente
                       AND pun.perd_oid_peri = lnOidPeriodoRefe
                       AND pun.tmov_oid_tipo_movi = 1
                       AND pun.val_desc LIKE '%Puntaje por Constancia%';
                  EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                      LOV_PR_REGIS_INCID(lsNumeroConcurso, interfazRecordN(x).codCliente,
                        psCodigoPeriodo,'LOV-2','No existe registro en Cuenta Corriente Puntos para la anulacion',
                        interfazRecordN(x).oidSolicitud);

                      lnPuntaje := 0;
                    WHEN TOO_MANY_ROWS THEN
                      LOV_PR_REGIS_INCID(lsNumeroConcurso, interfazRecordN(x).codCliente,
                        psCodigoPeriodo,'LOV-2','Existe mas de un registro en Cuenta Corriente Puntos para la anulacion',
                        interfazRecordN(x).oidSolicitud);

                      lnPuntaje := 0;
                    WHEN OTHERS THEN
                      lnPuntaje := 0;
                  END;

                  IF(lnPuntaje > 0) THEN
                    --Se inserta el puntaje respectivo en la tabla INC_CUENT_CORRI_PUNTO
                    SELECT INC_CUCP_SEQ.NEXTVAL INTO lnOidCuenta FROM DUAL;

                    INSERT INTO INC_CUENT_CORRI_PUNTO
                      (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
                       NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
                       CLIE_OID_CLIE, PERD_OID_PERI,
                       TMOV_OID_TIPO_MOVI,
                       FEC_ULTI_ACTU, VAL_DESC)
                    VALUES
                      (lnOidCuenta, lnOidCuenta, lnPuntaje*(-1),
                       0, TRUNC(SYSDATE), lnOidConcurso,
                       interfazRecordN(x).oidCliente, interfazRecordN(x).oidPeriodo,
                       2, SYSDATE, 'Anulación de Puntaje por Constancia');

                  END IF;

                END IF;

                --Insertamos en la tabla LOV_SOLIC_CONST las solicitudes procesadas
                INSERT INTO LOV_SOLIC_CONST
                  (COPA_OID_PARA_GRAL, SOCA_OID_SOLI_CABE, FEC_ULTI_ACTU)
                VALUES
                  (lnOidConcurso, interfazRecordN(x).oidSolicitud, SYSDATE);

              ELSE --No paso la validacion de Participacion
                  LOV_PR_REGIS_INCID(lsNumeroConcurso, interfazRecordN(x).codCliente,
                    psCodigoPeriodo,'LOV-2','No paso el filtro de Participantes del Concurso',
                    interfazRecordN(x).oidSolicitud);

              END IF;

            END IF;

          END LOOP;
       END IF;
       EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_PR_GENER_PUNTA_CONST: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END LOV_PR_GENER_PUNTA_CONST;


/**************************************************************************
Descripcion        : Valida el puntaje relacionado al total Pedidos de un
                     determinado concurso
Fecha Creacion     :  12/10/2009
Parametros Entrada :
           psNumeroConcurso : Numero Concurso
           pnTotalPedidos :  Total Pedidos para el concurso

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_DEVUE_PUNTA_CONST(psNumeroConcurso     VARCHAR2,
                                  pnTotalPedidos       NUMBER)
RETURN NUMBER IS
  lnPuntaje   NUMBER;
BEGIN

  SELECT ran.val_punt
    INTO lnPuntaje
    FROM LOV_RANGO_PUNTA_CONST ran
   WHERE ran.cod_conc = psNumeroConcurso
     AND pnTotalPedidos >= ran.num_pedi_desd
     AND pnTotalPedidos <= ran.num_pedi_hast
     AND ran.ind_acti = '1';

  RETURN lnPuntaje;

EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;

END LOV_FN_DEVUE_PUNTA_CONST;


/**************************************************************************
Descripcion        : Se calcula el numero de pedidos consecutivos para el cliente
                     y un determinado concurso
Fecha Creacion     :  12/10/2009
Parametros Entrada :
           psCodigoCliente  : Codigo de Cliente
           psNumeroConcurso : Numero Concurso
           psCodigoPeriodoIni : Codigo Periodo Inicio del Concurso
           psCodigoPais     :  Codigo Pais
           psCodigoMarca    :  Codigo Marca
           psCodigoCanal    :  Codigo Canal
           psCodigoPeriodo  :  Codigo Periodo Proceso

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_DEVUE_PEDID_CONSE(psCodigoCliente      VARCHAR2,
                                  psNumeroConcurso     VARCHAR2,
                                  psCodigoPeriodoIni   VARCHAR2,
                                  psCodigoPais         VARCHAR2,
                                  psCodigoMarca        VARCHAR2,
                                  psCodigoCanal        VARCHAR2,
                                  psCodigoPeriodo      VARCHAR2)
RETURN NUMBER IS
  lsIndicadorPedido     LOV_PEDID_CONST.IND_PEDI%TYPE;
  lnTotalPedidos        NUMBER;

  lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
BEGIN

  --Recuperamos el oid Pais,Marca,Canal
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

  --Verificamos si para el periodo de proceso existe registro efectivo en la tabla LOV_PEDID_CONST
  --para el cliente y concurso
  BEGIN
    SELECT IND_PEDI
      INTO lsIndicadorPedido
      FROM LOV_PEDID_CONST con
     WHERE con.cod_clie = psCodigoCliente
       and con.cod_conc = psNumeroConcurso
       and con.cod_peri = psCodigoPeriodo;
  EXCEPTION
    WHEN OTHERS THEN
      lsIndicadorPedido := '0';
  END;

  IF(lsIndicadorPedido = '0') THEN
    lnTotalPedidos := 0;
  ELSE
    lnTotalPedidos := 0;

    --Recorremos desde el periodo de proceso, hasta No exista registros en LOV_PEDID_CONST
    --para el período, o IND_PEDI = 0, o hasta alcanzar al Período de inicio del Concurso
    FOR x IN (SELECT cor.cod_peri
                FROM SEG_PERIO_CORPO cor, CRA_PERIO cra
               WHERE cor.oid_peri = cra.peri_oid_peri
                 AND cra.pais_oid_pais = lnOidPais
                 AND cra.marc_oid_marc = lnOidMarca
                 AND cra.cana_oid_cana = lnOidCanal
                 AND cor.cod_peri <= psCodigoPeriodo
                 AND cor.cod_peri >= psCodigoPeriodoIni
               ORDER BY cor.cod_peri DESC) LOOP

      BEGIN
        SELECT IND_PEDI
          INTO lsIndicadorPedido
          FROM LOV_PEDID_CONST con
         WHERE con.cod_clie = psCodigoCliente
           and con.cod_conc = psNumeroConcurso
           and con.cod_peri = x.cod_peri;
      EXCEPTION
        WHEN OTHERS THEN
          lsIndicadorPedido := '0';
      END;

      IF(lsIndicadorPedido = '1') THEN
        lnTotalPedidos := lnTotalPedidos + 1;
      ELSE
        RETURN lnTotalPedidos;
      END IF;

    END LOOP;

  END IF;

  RETURN lnTotalPedidos;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_FN_DEVUE_PEDID_CONSE: ' || ls_sqlerrm);

END LOV_FN_DEVUE_PEDID_CONSE;


/**************************************************************************
Descripcion       : Registra una incidencia producida de alguna validacion que no
                    paso una determinada solicitud para cualquier de los procesos de LOVE
Fecha Creacion    : 15/10/2009
Parametros Entrada:
  psNumeroConcurso :  Numero de Concurso
  psCodigoCliente  :  Codigo de Cliente
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoProceso  :  Codigo de Proceso
  psMotivoIndicencia  :  Motivo de la Incidencia
  pnOidSolicitud     :  Oid Solicitud
  pnNumeroPremio     : Numero de Premio
  psCodigoVenta      : Codigo de Venta
  psDescripcionPremio : Descripcion Premio
  pnNumeroUnidades : Numero de Unidades del Premio

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_REGIS_INCID(psNumeroConcurso           VARCHAR2,
                             psCodigoCliente            VARCHAR2,
                             psCodigoPeriodo            VARCHAR2,
                             psCodigoProceso            VARCHAR2,
                             psMotivoIndicencia         VARCHAR2,
                             pnOidSolicitud             NUMBER,
                             pnNumeroPremio             NUMBER := NULL,
                             psCodigoVenta              VARCHAR2 := NULL,
                             psDescripcionPremio        VARCHAR2 := NULL,
                             pnNumeroUnidades           NUMBER := NULL)
IS
BEGIN

  INSERT INTO LOV_INCID
    (NUM_INCI, COD_CONC, COD_CLIE,
     COD_PERI, COD_PROC, DES_MOTI,
     SOCA_OID_SOLI_CABE, FEC_ULTI_ACTU,
     NUM_PREM, COD_VENT_FICT, DES_PREM, NUM_UNID_PREM)
  VALUES
    (LOV_SEQ_INCID.NEXTVAL, psNumeroConcurso, psCodigoCliente,
     psCodigoPeriodo, psCodigoProceso, psMotivoIndicencia,
     pnOidSolicitud, SYSDATE,
     pnNumeroPremio, psCodigoVenta, psDescripcionPremio, pnNumeroUnidades);

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_PR_REGIS_INCID: ' || ls_sqlerrm);

END LOV_PR_REGIS_INCID;


/**************************************************************************
Descripcion       : Genera las solicitudes de premiación del Programa Love
Fecha Creacion    : 23/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                       GP4: GP4
  psCodigoRegion     : Codigo de Region
  psCodigoZona       : Codigo de Zona
  psFechaFacturacion  :  Fecha de Facturacion
  psCodigoUsuario     : Usuario que ejecuta el Proceso

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_ATENC_PREMI
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psIndicadorProceso         VARCHAR2,
   psCodigoRegion             VARCHAR2,
   psCodigoZona               VARCHAR2,
   psFechaFacturacion         VARCHAR2,
   psCodigoUsuario            VARCHAR2)
IS
  lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo                CRA_PERIO.OID_PERI%TYPE;

  lnOidConcurso               INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  lsNumeroConcurso            INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
  lsCodigoPeriodoIni          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodigoPeriodoFin          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidDirigido               INC_CONCU_PARAM_GENER.DIRI_OID_DIRI%TYPE;
  lnOidTipoPrograma           INC_CONCU_PARAM_GENER.ICTP_OID_TIPO_PROG%TYPE;
  lnOidPeriodoPremio          INC_PARAM_GENER_PREMI.PERD_OID_PERI%TYPE;

  lnOidCuenta                 INC_CUENT_CORRI_PUNTO.OID_CUEN_CORR_PUNT%TYPE;
  lnOidOperacion              BEL_OPERA.OID_OPER%TYPE;
  lnOidClaseSolicitud         PED_CLASE_SOLIC.OID_CLAS_SOLI%TYPE;
  lnIndicadorOC               PED_CLASE_SOLIC.IND_ORDE_COMP%TYPE;

  lnOidTipoSoliPais           PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE;
  lnOidTipoPosi               PED_TIPO_SOLIC_PROCE.TPOS_OID_TIPO_POSI%TYPE;
  lnOidSubTipoPosi            PED_TIPO_SOLIC_PROCE.STPO_OID_SUBT_POSI%TYPE;
  lnOidAcceso                 PED_TIPO_SOLIC.ACCE_OID_ACCE%TYPE;
  lnOidSubAcceso              PED_TIPO_SOLIC.SBAC_OID_SBAC%TYPE;
  lnOidTipoCliente            PED_TIPO_SOLIC.TICL_OID_TIPO_CLIE%TYPE;
  lnOidActividad              PED_TIPO_SOLIC_PAIS.CACT_OID_ACTI%TYPE;
  lnOidFormaPago              PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO%TYPE;
  lnIndPedidoPrueba           PED_TIPO_SOLIC_PAIS.IND_PEDI_PRUE%TYPE;
  lnIndPermitirUnion          PED_TIPO_SOLIC_PAIS.IND_PERM_UNIO%TYPE;
  lnOidTipoCons               PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS%TYPE;
  lnOidTipoDocumentoLegal     PED_TIPO_SOLIC_PAIS.TIDO_OID_TIPO_DOCU%TYPE;
  lnOidSociedad               PED_TIPO_SOLIC_PAIS.SOCI_OID_SOCI%TYPE;
  lnOidMoneda                 PED_TIPO_SOLIC_PAIS.MONE_OID_MONE%TYPE;

  lnOidEstadoSolicitud        PED_ESTAD_SOLIC.OID_ESTA_SOLI%TYPE;
  lnOidEstadoPosicion         PED_ESTAD_POSIC.OID_ESTA_POSI%TYPE;
  lnOidProceso                PED_SECUE_PROCE.PROC_OID_PROC%TYPE;
  lnTipoCambio                PRE_MATRI_FACTU_CABEC.VAL_TIPO_CAMB%TYPE;
  lnOidTipoDespacho           PED_TIPO_DESPA.OID_TIPO_DESP%TYPE;
  lnIndCronograma             PED_TIPO_DESPA.IND_CRON%TYPE;
  lnOidAlmacen                BEL_ALMAC.OID_ALMA%TYPE;
  lsCodigoPeriodoSig          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidPeriodoDespacho        CRA_PERIO.OID_PERI%TYPE;

  lnOidSubTipoCliente         MAE_CLIEN_TIPO_SUBTI.SBTI_OID_SUBT_CLIE%TYPE;
  lnOidClienteDir             MAE_CLIEN_DIREC.OID_CLIE_DIRE%TYPE;
  lnOidValEstruGeopo          ZON_VALOR_ESTRU_GEOPO.OID_VALO_ESTR_GEOP%TYPE;

  lnOidTipoDocumento          MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU%TYPE;
  lnOidTerriAdmin             ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE;
  lnOidTerritorio             ZON_TERRI.OID_TERR%TYPE;
  lnOidSeccion                ZON_SECCI.OID_SECC%TYPE;
  lnOidZona                   ZON_ZONA.OID_ZONA%TYPE;
  lnOidRegion                 ZON_REGIO.OID_REGI%TYPE;
  lnOidSolicitud              PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;

  lbPrimeraVez                BOOLEAN;
  lnContadorPosiciones        NUMBER;
  lsConsulta                  VARCHAR2(100);
  ldFechaProgFacturacion      DATE;
  lnPuntaje                   INC_CUENT_CORRI_PUNTO.NUM_PUNT%TYPE;

  lsCodigoSubAcceso           SEG_SUBAC.COD_SBAC%TYPE;
  lnNumeroSolicitud           PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
  lnSolicitudFormato          PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;

CURSOR c_Pedidos(oidPais NUMBER, oidPeriodo NUMBER, oidConcurso NUMBER) IS
  SELECT DISTINCT psc.CLIE_OID_CLIE,
         cli.COD_CLIE,
         psc.PERD_OID_PERI
   FROM PED_SOLIC_CABEC psc,
        PED_TIPO_SOLIC_PAIS tsp,
        (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC') ts,
        ZON_ZONA zon,
        ZON_REGIO reg,
        MAE_CLIEN cli
    WHERE psc.PAIS_OID_PAIS = oidPais
      AND psc.PERD_OID_PERI = oidPeriodo
      AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
      AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
      AND psc.ZZON_OID_ZONA = zon.OID_ZONA
      AND psc.CLIE_OID_CLIE = cli.OID_CLIE
      AND (( (psIndicadorProceso = PROCESO_GP4) AND
                       ((psc.FEC_FACT IS NULL) AND (psc.GRPR_OID_GRUP_PROC = 4) )
            ) OR
            ((psIndicadorProceso = PROCESO_CIERRE_ZONA) AND
                       (
                         (psc.FEC_FACT IS NULL) AND (psc.GRPR_OID_GRUP_PROC = 4) AND (zon.cod_zona = psCodigoZona)
                                                                                 AND (zon.ind_acti = 1)
                       )
            ) OR
            ((psIndicadorProceso = PROCESO_CIERRE_REGION) AND
                       (
                         (psc.FEC_FACT IS NOT NULL) AND (psc.GRPR_OID_GRUP_PROC = 5) AND (reg.cod_regi = psCodigoRegion)
                                                                                     AND (reg.ind_acti = 1)
                       )
            ) OR
            ((psIndicadorProceso = PROCESO_CIERRE_CAMPANA) AND
                       (
                         (psc.FEC_FACT IS NOT NULL) AND (psc.GRPR_OID_GRUP_PROC = 5)
                       )
            )
          )
      AND EXISTS (SELECT SOCA_OID_SOLI_CABE
                    FROM INC_PREMI_ELEGI pre
                    WHERE pre.clie_oid_clie = psc.clie_oid_clie
                      AND pre.copa_oid_para_gral = oidConcurso
                      AND pre.ind_pend = 1);

  TYPE interfazPedidos IS RECORD
  (
   oidCliente                MAE_CLIEN.OID_CLIE%TYPE,
   codCliente                MAE_CLIEN.COD_CLIE%TYPE,
   oidPeriodo                PED_SOLIC_CABEC.PERD_OID_PERI%TYPE
  );

  TYPE interfazPedidosTab  IS TABLE OF interfazPedidos;
  interfazRecordN interfazPedidosTab;

BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  --Recuperamos el oid y Concurso del Programa LOVE vigente de Despacho para el periodo de Proceso
  lnOidConcurso := LOV_FN_DEVUE_CONCU_DESPA(psCodigoPais, psCodigoMarca, psCodigoCanal, psCodigoPeriodo, 1);

  IF (lnOidConcurso IS NULL) THEN --No existe concurso, finaliza el Proceso
    RETURN;
  END IF;

  --Obtenemos el numero de Concurso
  SELECT gen.Num_Conc, gen.diri_oid_diri, cord.cod_peri,
         corh.cod_peri, gen.ICTP_OID_TIPO_PROG, pre.perd_oid_peri
    INTO lsNumeroConcurso, lnOidDirigido, lsCodigoPeriodoIni,
         lsCodigoPeriodoFin, lnOidTipoPrograma, lnOidPeriodoPremio
    FROM INC_CONCU_PARAM_GENER gen,
         CRA_PERIO crad, SEG_PERIO_CORPO cord,
         CRA_PERIO crah, SEG_PERIO_CORPO corh,
         INC_PARAM_GENER_PREMI pre
   WHERE oid_para_gral = lnOidConcurso
     AND gen.perd_oid_peri_desd = crad.oid_peri
     AND crad.peri_oid_peri = cord.oid_peri
     AND gen.perd_oid_peri_hast = crah.oid_peri
     AND crah.peri_oid_peri = corh.oid_peri
     AND gen.oid_para_gral = pre.copa_oid_para_gral;

  lbPrimeraVez := TRUE;

   --(1) PROCESAMOS A LOS CLIENTES
    OPEN c_Pedidos(lnOidPais, lnOidPeriodo, lnOidConcurso);
    LOOP
       FETCH c_Pedidos BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
       IF interfazRecordN.COUNT > 0 THEN

          FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

            --SI ES PRIMERA VEZ, RECUPERAMOS INFORMACION DE LAS TABLAS NECESARIAS
            --PARA LA GENERACION DE LAS SOLICITUDES DE INCENTIVOS
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
              SELECT PROC_OID_PROC
                INTO lnOidProceso
                FROM PED_SECUE_PROCE
               WHERE TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSoliPais
                 AND GRPR_OID_GRUP_PROC = 1;

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

            BEGIN
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
                       ORDER BY VAL_IDEN_DOCU_PRIN)
               WHERE ROWNUM = 1;

              --Recuperamos los datos de la unidad administrativa del Cliente
              lsConsulta := 'TerritorioCliente';
              SELECT ter.OID_TERR_ADMI, ter.TERR_OID_TERR, sec.OID_SECC,
                     zon.OID_ZONA, zon.ZORG_OID_REGI
                INTO lnOidTerriAdmin, lnOidTerritorio, lnOidSeccion,
                     lnOidZona, lnOidRegion
                FROM MAE_CLIEN_UNIDA_ADMIN adm, ZON_TERRI_ADMIN ter, ZON_SECCI sec, ZON_ZONA zon
               WHERE adm.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                 AND adm.IND_ACTI = 1
                 AND adm.ZTAD_OID_TERR_ADMI = ter.OID_TERR_ADMI
                 AND ter.ZSCC_OID_SECC = sec.OID_SECC
                 AND sec.ZZON_OID_ZONA = zon.OID_ZONA;


              --Por cada cliente, se recupera los premios elegidos
              FOR y IN (SELECT PANP_OID_PARA_NIVE_PREM, NUM_PREM, COUNT(1) TOT_ATEN
                          FROM INC_PREMI_ELEGI
                         WHERE COPA_OID_PARA_GRAL = lnOidConcurso
                           AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                           AND IND_PEND = 1
                         GROUP BY PANP_OID_PARA_NIVE_PREM, NUM_PREM) LOOP

                SELECT PED_SOCA_SEQ.NEXTVAL INTO lnOidSolicitud FROM DUAL;

                --Calculamos la Fecha Programada de Facturacion
                lsConsulta := 'Obteniendo FechaProgFacturacion';
                IF(lnIndCronograma = 1) THEN
                  SELECT FEC_INIC
                    INTO ldFechaProgFacturacion
                    FROM CRA_CRONO
                   WHERE PERD_OID_PERI = lnOidPeriodo
                     AND ZZON_OID_ZONA = lnOidZona
                     AND CACT_OID_ACTI = lnOidActividad
                     AND TRUNC(FEC_INIC) >= TRUNC(SYSDATE);
                ELSE
                  ldFechaProgFacturacion := TRUNC(SYSDATE);
                END IF;

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
                   1, lnIndicadorOC, lnIndPedidoPrueba,
                   lnIndPermitirUnion, 1, 13,
                   lnOidMoneda, NULL, NULL,
                   y.NUM_PREM, lnOidOperacion, lnOidPais,
                   lnOidPeriodoDespacho, lnOidProceso, lnOidSubAcceso,
                   lnOidSubTipoCliente, NULL, lnOidSociedad,
                   lnOidTipoDocumento, lnOidTerritorio, lnOidTipoCliente,
                   lnOidTipoDocumentoLegal, lnOidTipoDespacho, lnOidTipoSoliPais,
                   lnOidTipoCons, 'TRANSFERENCIA GRATUITA', lnSolicitudFormato,
                   psCodigoUsuario, lnOidValEstruGeopo, DECODE(lnOidMoneda, NULL, 1, lnTipoCambio),
                   lnOidZona, lnOidTerriAdmin, lnOidTipoPrograma);

                --Inicializamos el contador de Posiciones
                lnContadorPosiciones := 0;

                --Por cada premio elegido, se recupera los productos a despachar
                FOR z IN (SELECT al.PROD_OID_PROD,
                                 al.IMP_PREC_PUBL,
                                 pa.NUM_UNID,
                                 al.COD_VENT_FICT,
                                 DECODE(pnp.NUM_CANT_FIJA_PUNT, null, pnp.NUM_CANT_INIC_PUNT, pnp.NUM_CANT_FIJA_PUNT) PUNT_NIVE,
                                 lpa.VAL_DESC_LOTE_PREM_ARTI des_lote
                          FROM INC_PARAM_NIVEL_PREMI pnp,
                               INC_PREMI_ARTIC pa,
                               INC_LOTE_PREMI_ARTIC lpa,
                               INC_ARTIC_LOTE al
                          WHERE pnp.OID_PARA_NIVE_PREM = y.PANP_OID_PARA_NIVE_PREM
                            AND lpa.NUM_PREM = y.NUM_PREM
                            AND pnp.OID_PARA_NIVE_PREM = pa.PANP_OID_PARA_NIVE_PREM
                            AND pa.OID_PREM_ARTI = lpa.PRAR_OID_PREM_ARTI
                            AND lpa.OID_LOTE_PREM_ARTI = al.LOPA_OID_LOTE_PREM_ARTI ) LOOP

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
                     y.TOT_ATEN, y.TOT_ATEN, lnOidTipoPosi,
                     z.PROD_OID_PROD, NULL, NULL,
                     lnOidEstadoPosicion, lnOidSubTipoPosi, z.COD_VENT_FICT,
                     y.TOT_ATEN, 0, z.IMP_PREC_PUBL,
                     0, 0, NULL,
                     NULL, NULL, NULL,
                     NULL, NULL, NULL, NULL);

                  lnContadorPosiciones := lnContadorPosiciones + 1;
                  lnPuntaje := z.PUNT_NIVE;

                END LOOP;

                --Se actualiza los premios elegidos como Atendidos
                lsConsulta := 'Actualizando PremioElegido';
                UPDATE INC_PREMI_ELEGI
                   SET IND_PEND = 0
                 WHERE COPA_OID_PARA_GRAL = lnOidConcurso
                   AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                   AND PANP_OID_PARA_NIVE_PREM = y.PANP_OID_PARA_NIVE_PREM
                   AND NUM_PREM = y.NUM_PREM;

                --Se actualiza la cuenta corriente de puntos con el cargo por el despacho de premios
                SELECT INC_CUCP_SEQ.NEXTVAL INTO lnOidCuenta FROM DUAL;

                --Calculamos el puntaje, que es el puntos en premio * numero de unidades atendidas
                lnPuntaje := lnPuntaje * y.TOT_ATEN;

                lsConsulta := 'Insertando CuentaCorriente';
                INSERT INTO INC_CUENT_CORRI_PUNTO
                  (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
                   NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
                   CLIE_OID_CLIE, PERD_OID_PERI,
                   TMOV_OID_TIPO_MOVI,
                   FEC_ULTI_ACTU, VAL_DESC)
                VALUES
                  (lnOidCuenta, lnOidCuenta, lnPuntaje*(-1),
                   0, TRUNC(SYSDATE), lnOidConcurso,
                   interfazRecordN(x).oidCliente, interfazRecordN(x).oidPeriodo,
                   2, SYSDATE, 'Entrega de Premio');

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

              END LOOP;

            EXCEPTION
              WHEN OTHERS THEN
                ln_sqlcode := SQLCODE;
                ls_sqlerrm := substr(SQLERRM,1,250);

                LOV_PR_REGIS_INCID_INDEP(lsNumeroConcurso, interfazRecordN(x).codCliente,
                      psCodigoPeriodo,'LOV-3', SUBSTR(lsConsulta || ' ,' || ls_sqlerrm, 1, 100),
                      NULL);

                RAISE_APPLICATION_ERROR(-20123, lsConsulta || ' - ' || ls_sqlerrm);
            END;

          END LOOP;
       END IF;
       EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_PR_ATENC_PREMI: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END LOV_PR_ATENC_PREMI;


/**************************************************************************
Descripcion        : Recupera el Oid Concurso vigente del programa LOVE para
                     Despacho
Fecha Creacion     : 28/10/2009
Parametros Entrada :
           psCodigoPais : Codigo Pais
           psCodigoPeriodo : Codigo Perido
           psCodigoCanal : Codigo Canal
           psCodigoPeriodo : Codigo Periodo
           pnNumeroPeriodos : Numero Periodos

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION LOV_FN_DEVUE_CONCU_DESPA(psCodigoPais      VARCHAR2,
                                  psCodigoMarca     VARCHAR2,
                                  psCodigoCanal     VARCHAR2,
                                  psCodigoPeriodo   VARCHAR2,
                                  pnNumeroPeriodos  NUMBER)
RETURN NUMBER IS
  lnOidConcurso       INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca          SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal          SEG_CANAL.OID_CANA%TYPE;

BEGIN
  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

  SELECT Oid_Para_Gral
    INTO lnOidConcurso
    FROM (SELECT gen.Oid_Para_Gral,
                 DECODE(pnNumeroPeriodos,0,cord.cod_peri,per_pkg_repor_perce.per_fn_obtie_perio(cord.cod_peri,
                           lnOidPais, lnOidMarca, lnOidCanal, pnNumeroPeriodos)) cod_peri_inic,
                 corh.cod_peri cod_peri_fin
            FROM INC_CONCU_PARAM_GENER gen, INC_CLASI_CONCU cla, INC_PARAM_GENER_PREMI pre,
                 CRA_PERIO crad, SEG_PERIO_CORPO cord,
                 CRA_PERIO crah, SEG_PERIO_CORPO corh
           WHERE gen.CCON_OID_CLAS_CONC = cla.Oid_Clas_Conc
             AND pre.copa_oid_para_gral = gen.oid_para_gral
             AND gen.pais_oid_pais = lnOidPais
             AND cla.cod_clas_conc = 'A'
             AND gen.ind_acti = 1
             AND gen.perd_oid_peri_desd = crad.oid_peri
             AND crad.peri_oid_peri = cord.oid_peri
             AND pre.perd_oid_peri = crah.oid_peri
             AND crah.peri_oid_peri = corh.oid_peri) x
    WHERE
         x.cod_peri_inic<= psCodigoPeriodo
     AND x.cod_peri_fin >= psCodigoPeriodo;

  RETURN lnOidConcurso;

EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;

END LOV_FN_DEVUE_CONCU_DESPA;


/**************************************************************************
Descripcion       : Elimina Premios Elegidos No Atentidos
Fecha Creacion    : 29/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                       GP4: GP4
  psCodigoRegion     : Codigo de Region
  psCodigoZona       : Codigo de Zona

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_ELIMI_PREMI_NOATE
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psIndicadorProceso         VARCHAR2,
   psCodigoRegion             VARCHAR2,
   psCodigoZona               VARCHAR2)
IS
  lnOidConcurso               INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  lsNumeroConcurso            INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
  lsMotivoRechazo             VARCHAR2(500);

  lnIdPais       seg_pais.oid_pais%TYPE;
  lnIdCanal      seg_canal.oid_cana%TYPE;
  lnIdMarca      seg_marca.oid_marc%TYPE;
  lnIdPeriodo    seg_perio_corpo.oid_peri%TYPE;

  lnCont         NUMBER;
  continuarSig   Boolean;

CURSOR c_Premios(oidConcurso NUMBER) IS
  SELECT pre.CLIE_OID_CLIE, cli.COD_CLIE,
         pre.PANP_OID_PARA_NIVE_PREM, pre.NUM_PREM,
         COUNT(1) TOT_ATEN
    FROM INC_PREMI_ELEGI pre, MAE_CLIEN cli, MAE_CLIEN_UNIDA_ADMIN adm,
         ZON_TERRI_ADMIN ter, ZON_SECCI sec, ZON_ZONA zon, ZON_REGIO reg
   WHERE pre.COPA_OID_PARA_GRAL = oidConcurso
     AND pre.CLIE_OID_CLIE = cli.OID_CLIE
     AND pre.IND_PEND = 1
     AND adm.CLIE_OID_CLIE = cli.OID_CLIE
     AND adm.IND_ACTI = 1
     AND adm.ZTAD_OID_TERR_ADMI = ter.OID_TERR_ADMI
     AND ter.ZSCC_OID_SECC = sec.OID_SECC
     AND sec.ZZON_OID_ZONA = zon.OID_ZONA
     AND zon.ZORG_OID_REGI = reg.OID_REGI
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
          )
   GROUP BY pre.CLIE_OID_CLIE, cli.COD_CLIE, pre.PANP_OID_PARA_NIVE_PREM, pre.NUM_PREM;

  TYPE interfazPremios IS RECORD
  (
   oidCliente                MAE_CLIEN.OID_CLIE%TYPE,
   codCliente                MAE_CLIEN.COD_CLIE%TYPE,
   oidPremio                 INC_PARAM_NIVEL_PREMI.OID_PARA_NIVE_PREM%TYPE,
   numeroPremio              INC_PREMI_ELEGI.NUM_PREM%TYPE,
   totalAtender              NUMBER
  );

  TYPE interfazPremiosTab  IS TABLE OF interfazPremios;
  interfazRecordN interfazPremiosTab;

BEGIN

  --Recuperamos el oid y Concurso del Programa LOVE vigente de Despacho para el periodo de Proceso
  lnOidConcurso := LOV_FN_DEVUE_CONCU_DESPA(psCodigoPais, psCodigoMarca, psCodigoCanal, psCodigoPeriodo, 0);

  /* obteniendos ids */
        lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
        lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal);
        lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca);

       /* obteniendo el oid del periodo actual con la funcion */
        lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO( psCodigoPeriodo,lnIdMarca,lnIdCanal);

  IF (lnOidConcurso IS NULL) THEN --No existe concurso, finaliza el Proceso
    RETURN;
  END IF;

  --Obtenemos el numero de Concurso
  SELECT gen.Num_Conc
    INTO lsNumeroConcurso
    FROM INC_CONCU_PARAM_GENER gen
   WHERE gen.oid_para_gral = lnOidConcurso;

  --(1) PROCESAMOS A LOS PREMIOS
  OPEN c_Premios(lnOidConcurso);
  LOOP
    FETCH c_Premios BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

        continuarSig:=false;
        --REcuperamos el motivo del Rechazo para la solicitud de Premio
        lsMotivoRechazo := NULL;
        FOR y IN (SELECT SP.DES_VALI
                 		FROM STO_DETAL_DOCUM_EXCEP DE,
                         STO_DOCUM_DIGIT DG,
                      	 STO_PARAM_VALID SP
                 		WHERE DE.COD_PAIS = psCodigoPais
              			  AND DG.COD_PAIS=DE.COD_PAIS
                     	AND DG.SEC_NUME_DOCU = DE.SEC_NUME_DOCU
                    	AND DG.COD_TIPO_DOCU = DE.COD_TIPO_DOCU
                      AND DG.NUM_LOTE = DE.NUM_LOTE
              			  AND SP.COD_PAIS = DE.COD_PAIS
                      AND SP.COD_VALI = DE.COD_VALI
                    	AND SP.COD_TIPO_DOCU = DE.COD_TIPO_DOCU
                 		 	AND DE.COD_TIPO_DOCU = 'OCC'
                      AND DG.COD_PERI = psCodigoPeriodo
                      AND DG.COD_CLIE = interfazRecordN(x).codCliente
                      AND DE.IND_APRO = '0'
                      AND NOT EXISTS (SELECT 1 FROM STO_DOCUM_DIGIT TT
                                      WHERE TT.COD_PAIS = DG.COD_PAIS
                                        AND TT.COD_PERI = DG.COD_PERI
                                        AND TT.COD_TIPO_DOCU = DG.COD_TIPO_DOCU
                                        AND TT.COD_CLIE = DG.COD_CLIE
                                        AND TT.IND_ENVI = '1')) LOOP

          IF(lsMotivoRechazo IS NULL) THEN
            lsMotivoRechazo := y.DES_VALI;
          ELSE
            lsMotivoRechazo := lsMotivoRechazo || ', ' || y.DES_VALI;
          END IF;
        END LOOP;

        IF(lsMotivoRechazo IS NULL) THEN

          --Si el motivo es nulo, entonces se verifica si el cliente paso pedido en el período de proceso
             SELECT COUNT(1) INTO lnCont
             FROM PED_SOLIC_CABEC A ,
                  PED_TIPO_SOLIC_PAIS B ,
                  PED_TIPO_SOLIC C,
                  MAE_CLIEN D
                WHERE  A.PAIS_OID_PAIS = lnIdPais
                    AND A.PERD_OID_PERI = lnIdPeriodo
                    AND D.PAIS_OID_PAIS = A.PAIS_OID_PAIS
                    AND D.OID_CLIE =  A.CLIE_OID_CLIE
                    AND D.COD_CLIE =interfazRecordN(x).codCliente
                    AND A.FEC_FACT IS NOT NULL
                    AND A.GRPR_OID_GRUP_PROC = 5
                    AND B.PAIS_OID_PAIS = A.PAIS_OID_PAIS
                    AND B.OID_TIPO_SOLI_PAIS = A.TSPA_OID_TIPO_SOLI_PAIS
                    AND C.OID_TIPO_SOLI = B.TSOL_OID_TIPO_SOLI
                    AND C.COD_TIPO_SOLI = 'SOC';

                IF(lnCont > 0) THEN
                  --SE CONTINUA CON EL SIGUIENTE CLIENTE
                  continuarSig:=TRUE;
                ELSE
          lsMotivoRechazo := 'Cliente no paso pedido en la campaña';
                  continuarSig:=FALSE;
                END IF;

        ELSE
          lsMotivoRechazo := 'Pedido rechazado por ' || lsMotivoRechazo;
        END IF;


         IF(continuarSig=FALSE) THEN--Si no hay cliente que paso pedido se procede a borrar

        --Por cada premio elegido, se recupera los productos a despachar
        FOR z IN (SELECT al.PROD_OID_PROD,
                         al.COD_VENT_FICT,
                         pq_apl_aux.Valor_Gen_I18n_Sicc(1, al.PROD_OID_PROD, 'MAE_PRODU') DES_PROD
                  FROM INC_PARAM_NIVEL_PREMI pnp,
                       INC_PREMI_ARTIC pa,
                       INC_LOTE_PREMI_ARTIC lpa,
                       INC_ARTIC_LOTE al
                  WHERE pnp.OID_PARA_NIVE_PREM = interfazRecordN(x).oidPremio
                    AND lpa.NUM_PREM = interfazRecordN(x).numeroPremio
                    AND pnp.OID_PARA_NIVE_PREM = pa.PANP_OID_PARA_NIVE_PREM
                    AND pa.OID_PREM_ARTI = lpa.PRAR_OID_PREM_ARTI
                    AND lpa.OID_LOTE_PREM_ARTI = al.LOPA_OID_LOTE_PREM_ARTI ) LOOP

          LOV_PR_REGIS_INCID(lsNumeroConcurso, interfazRecordN(x).codCliente,
                  psCodigoPeriodo,'LOV-4', SUBSTR(lsMotivoRechazo,1,100), NULL,
                  interfazRecordN(x).numeroPremio, z.COD_VENT_FICT,
                  z.DES_PROD, interfazRecordN(x).totalAtender);


        END LOOP;

        --Se elimina el premio Elegido del Cliente
        DELETE FROM INC_PREMI_ELEGI
        WHERE COPA_OID_PARA_GRAL = lnOidConcurso
          AND CLIE_OID_CLIE = interfazRecordN(x).oidCliente
          AND PANP_OID_PARA_NIVE_PREM = interfazRecordN(x).oidPremio
          AND NUM_PREM = interfazRecordN(x).numeroPremio
          AND IND_PEND = 1;
          END IF;

      END LOOP;
    END IF;
    EXIT WHEN c_Premios%NOTFOUND;
  END LOOP;
  CLOSE c_Premios;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_PR_ELIMI_PREMI_NOATE: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END LOV_PR_ELIMI_PREMI_NOATE;


/**************************************************************************
Descripcion       : Elimina Puntaje por incumplir con el numero maximo de
                    campañas sin pasar pedido
Fecha Creacion    : 29/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psIndicadorProceso : P: Cierre de Campana, R: Cierre de Region, Z: Cierre de Zona,
                       GP4: GP4

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_ELIMI_PUNTA
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psIndicadorProceso         VARCHAR2)
IS
  lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo                CRA_PERIO.OID_PERI%TYPE;

  lsCodigoPeriodoIni          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidConcurso               INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  lsNumeroConcurso            INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
  lnTotalPedidos              NUMBER;
  lnOidCuenta                 NUMBER;
  lnNumeroCampanas            LOV_PARAM.NUM_CAMP_SIN_PEDI%TYPE;

CURSOR c_Premios(oidConcurso NUMBER) IS
  SELECT CLIE_OID_CLIE, SUM(NUM_PUNT)
    FROM INC_CUENT_CORRI_PUNTO
   WHERE COPA_OID_PARA_GRAL = oidConcurso
   GROUP BY CLIE_OID_CLIE
   HAVING SUM(NUM_PUNT)>0;

  TYPE interfazPremios IS RECORD
  (
   oidCliente                MAE_CLIEN.OID_CLIE%TYPE,
   totalPuntaje              INC_CUENT_CORRI_PUNTO.NUM_PUNT%TYPE
  );

  TYPE interfazPremiosTab  IS TABLE OF interfazPremios;
  interfazRecordN interfazPremiosTab;

BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  --Recuperamos el oid y Concurso del Programa LOVE vigente para el periodo de Proceso
  lnOidConcurso := LOV_FN_DEVUE_CONCU_VIGEN(psCodigoPais, psCodigoPeriodo);

  IF (lnOidConcurso IS NULL) THEN --No existe concurso, finaliza el Proceso
    RETURN;
  END IF;

  --Obtenemos el numero de Concurso
  SELECT gen.Num_Conc
    INTO lsNumeroConcurso
    FROM INC_CONCU_PARAM_GENER gen
   WHERE gen.oid_para_gral = lnOidConcurso;

  --Recuperamos el numero de campañas sin Pedido para LOVE
  BEGIN
    SELECT NUM_CAMP_SIN_PEDI
      INTO lnNumeroCampanas
      FROM LOV_PARAM
     WHERE COD_CONC = lsNumeroConcurso;
  EXCEPTION
    WHEN OTHERS THEN
      lnNumeroCampanas := NULL;
  END;

  IF (lnNumeroCampanas IS NULL) THEN
    RETURN;
  ELSE
    IF(lnNumeroCampanas = 1) THEN
      lsCodigoPeriodoIni := psCodigoPeriodo;
    ELSE
      lsCodigoPeriodoIni := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                           lnOidPais, lnOidMarca, lnOidCanal, (-1)*(lnNumeroCampanas-1));
    END IF;
  END IF;

  --(1) PROCESAMOS A LOS PREMIOS
  OPEN c_Premios(lnOidConcurso);
  LOOP
    FETCH c_Premios BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

        SELECT COUNT(1)
          INTO lnTotalPedidos
          FROM PED_SOLIC_CABEC psc,
               PED_TIPO_SOLIC_PAIS tsp,
               (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC') ts,
               CRA_PERIO cra,
               SEG_PERIO_CORPO cor,
               PED_SOLIC_CABEC con
         WHERE psc.PAIS_OID_PAIS = lnOidPais
           AND psc.PERD_OID_PERI = cra.oid_peri
           AND cra.peri_oid_peri = cor.oid_peri
           AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
           AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
           AND psc.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
           AND psc.GRPR_OID_GRUP_PROC = 5
           AND psc.FEC_FACT IS NOT NULL
           AND con.OID_SOLI_CABE = psc.SOCA_OID_SOLI_CABE
           AND con.ESSO_OID_ESTA_SOLI <> '4'
           AND cor.cod_peri >= lsCodigoPeriodoIni
           AND cor.cod_peri <= psCodigoPeriodo;

        --Si no paso Pedidos, se procede a generar un cargo de eliminacion de puntaje
        IF(lnTotalPedidos = 0) THEN
          --Se actualiza la cuenta corriente de puntos con el cargo por el despacho de premios
          SELECT INC_CUCP_SEQ.NEXTVAL INTO lnOidCuenta FROM DUAL;

          INSERT INTO INC_CUENT_CORRI_PUNTO
            (OID_CUEN_CORR_PUNT, NUM_MOVI, NUM_PUNT,
             NUM_PUNT_EXIG, FEC_MOVI, COPA_OID_PARA_GRAL,
             CLIE_OID_CLIE, PERD_OID_PERI,
             TMOV_OID_TIPO_MOVI,
             FEC_ULTI_ACTU, VAL_DESC)
          VALUES
            (lnOidCuenta, lnOidCuenta, interfazRecordN(x).totalPuntaje*(-1),
             0, TRUNC(SYSDATE), lnOidConcurso,
             interfazRecordN(x).oidCliente, lnOidPeriodo,
             2, SYSDATE, 'Por incumplimiento de las Bases del Programa');
        END IF;

      END LOOP;
    END IF;
    EXIT WHEN c_Premios%NOTFOUND;
  END LOOP;
  CLOSE c_Premios;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_PR_ELIMI_PUNTA: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END LOV_PR_ELIMI_PUNTA;


/**************************************************************************
Descripcion       : Registra una incidencia producida de alguna validacion que no
                    paso una determinada solicitud para cualquier de los procesos de LOVE
Fecha Creacion    : 06/11/2009
Parametros Entrada:
  psNumeroConcurso :  Numero de Concurso
  psCodigoCliente  :  Codigo de Cliente
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoProceso  :  Codigo de Proceso
  psMotivoIndicencia  :  Motivo de la Incidencia
  pnOidSolicitud     :  Oid Solicitud
  pnNumeroPremio     : Numero de Premio
  psCodigoVenta      : Codigo de Venta
  psDescripcionPremio : Descripcion Premio
  pnNumeroUnidades : Numero de Unidades del Premio

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE LOV_PR_REGIS_INCID_INDEP(psNumeroConcurso           VARCHAR2,
                                   psCodigoCliente            VARCHAR2,
                                   psCodigoPeriodo            VARCHAR2,
                                   psCodigoProceso            VARCHAR2,
                                   psMotivoIndicencia         VARCHAR2,
                                   pnOidSolicitud             NUMBER,
                                   pnNumeroPremio             NUMBER := NULL,
                                   psCodigoVenta              VARCHAR2 := NULL,
                                   psDescripcionPremio        VARCHAR2 := NULL,
                                   pnNumeroUnidades           NUMBER := NULL)
IS PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN

  INSERT INTO LOV_INCID
    (NUM_INCI, COD_CONC, COD_CLIE,
     COD_PERI, COD_PROC, DES_MOTI,
     SOCA_OID_SOLI_CABE, FEC_ULTI_ACTU,
     NUM_PREM, COD_VENT_FICT, DES_PREM, NUM_UNID_PREM)
  VALUES
    (LOV_SEQ_INCID.NEXTVAL, psNumeroConcurso, psCodigoCliente,
     psCodigoPeriodo, psCodigoProceso, psMotivoIndicencia,
     pnOidSolicitud, SYSDATE,
     pnNumeroPremio, psCodigoVenta, psDescripcionPremio, pnNumeroUnidades);

   COMMIT;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR LOV_PR_REGIS_INCID_INDEP: ' || ls_sqlerrm);

END LOV_PR_REGIS_INCID_INDEP;


END LOV_PKG_PROCE;
/

