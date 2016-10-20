CREATE OR REPLACE PACKAGE INT_PKG_MYLBE IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  W_FILAS    NUMBER := 1000;


/**************************************************************************
Descripcion       : Envia la informacion de los concursos que han
                    sido seleccionados para enviar a MyLBEL
Fecha Creacion    : 01/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_CONCU(psCodigoPais            VARCHAR2,
                                 psCodigoSistema         VARCHAR2,
                                 psCodigoInterfaz        VARCHAR2,
                                 psNombreArchivo         VARCHAR2,
                                 psCodigoPeriodo         VARCHAR2,
                                 psFechaFacturacion      VARCHAR2);

/**************************************************************************
Descripcion       : Envia la informacion de los Niveles de los concursos que han
                    sifo seleccionados para enviar
Fecha Creacion    : 30/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_NIVEL(psCodigoPais            VARCHAR2,
                                 psCodigoSistema         VARCHAR2,
                                 psCodigoInterfaz        VARCHAR2,
                                 psNombreArchivo         VARCHAR2,
                                 psCodigoPeriodo         VARCHAR2,
                                 psFechaFacturacion      VARCHAR2);

/**************************************************************************
Descripcion       : Envia la informacion de los Premios de los concursos que han
                    sifo seleccionados para enviar
Fecha Creacion    : 30/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_PREMI(psCodigoPais            VARCHAR2,
                                 psCodigoSistema         VARCHAR2,
                                 psCodigoInterfaz        VARCHAR2,
                                 psNombreArchivo         VARCHAR2,
                                 psCodigoPeriodo         VARCHAR2,
                                 psFechaFacturacion      VARCHAR2);

/**************************************************************************
Descripcion       : Envia la informacion de las descripciones de los Premios
                    de los concursos que han sido seleccionados para enviar
Fecha Creacion    : 30/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_DESCR_PREMI(psCodigoPais            VARCHAR2,
                                       psCodigoSistema         VARCHAR2,
                                       psCodigoInterfaz        VARCHAR2,
                                       psNombreArchivo         VARCHAR2,
                                       psCodigoPeriodo         VARCHAR2,
                                       psFechaFacturacion      VARCHAR2);

/**************************************************************************
Descripcion        : Devuelve el codigo de Periodo
Fecha Creacion     : 30/09/2009
Parametros Entrada :
           pnOidPeriodo : Oid Periodo
           pnNumPeriodo : Numero de Periodos a Avanzar o Retroceder

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_DEVUE_CODIG_PERIO(pnOidPeriodo        NUMBER,
                                      pnNumPeriodo        NUMBER := NULL)
RETURN NUMBER;

/**************************************************************************
Descripcion       : Envia la informacion de los Zonas de los concursos que han
                    sifo seleccionados para enviar
Fecha Creacion    : 29/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_ZONAS (psCodigoPais            VARCHAR2,
                                  psCodigoSistema         VARCHAR2,
                                  psCodigoInterfaz        VARCHAR2,
                                  psNombreArchivo         VARCHAR2,
                                  psCodigoPeriodo         VARCHAR2,
                                  psFechaFacturacion      VARCHAR2);

/**************************************************************************
Descripcion       : Envia la informacion de Premios Asignados
Fecha Creacion    : 29/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_PREMI_ASIGN (psCodigoPais            VARCHAR2,
                                        psCodigoSistema         VARCHAR2,
                                        psCodigoInterfaz        VARCHAR2,
                                        psNombreArchivo         VARCHAR2,
                                        psCodigoPeriodo         VARCHAR2,
                                        psFechaFacturacion      VARCHAR2);

/**************************************************************************
Descripcion       : Envia la informacion de Premios Despachados
Fecha Creacion    : 29/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_PREMI_DESPA (psCodigoPais            VARCHAR2,
                                        psCodigoSistema         VARCHAR2,
                                        psCodigoInterfaz        VARCHAR2,
                                        psNombreArchivo         VARCHAR2,
                                        psCodigoPeriodo         VARCHAR2,
                                        psFechaFacturacion      VARCHAR2);

/**************************************************************************
Descripcion       : Envia la informacion de Puntajes
Fecha Creacion    : 01/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_PUNTA(psCodigoPais            VARCHAR2,
                                 psCodigoSistema         VARCHAR2,
                                 psCodigoInterfaz        VARCHAR2,
                                 psNombreArchivo         VARCHAR2,
                                 psCodigoPeriodo         VARCHAR2,
                                 psFechaFacturacion      VARCHAR2);

/**************************************************************************
Descripcion       : Envia la informacion de Recomendaciones
Fecha Creacion    : 29/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_RECOM (psCodigoPais            VARCHAR2,
                                  psCodigoSistema         VARCHAR2,
                                  psCodigoInterfaz        VARCHAR2,
                                  psNombreArchivo         VARCHAR2,
                                  psCodigoPeriodo         VARCHAR2,
                                  psFechaFacturacion      VARCHAR2);

/**************************************************************************
Descripcion        : Calcula el Total de Pedidos realizado por un cliente en un
                     determinado rango de periodos, para enviarlo en la interfaz
                     de Recomendaciones para My LBEL
                     periodo
Fecha Creacion     : 30/09/2009
Parametros Entrada :
           pnOidCliente : Oid Cliente
           pnOidPeriodo : Oid Periodo
           pnValPeriEval : Valor Periodo Evaluacion

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_TOTAL_PEDID_RECOM(pnOidCliente        NUMBER,
                                      pnOidPeriodo        NUMBER,
                                      pnValPeriEval       NUMBER)
RETURN NUMBER;

/**************************************************************************
Descripcion        : Calcula el nivel alcanzado de acuerdo al puntaje en el
                     concurso seleccionado
Fecha Creacion     : 01/10/2009
Parametros Entrada :
           pnOidConcurso : Oid Concurso
           pnPuntaje : Puntaje del Cliente

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_NIVEL_ALCAN_CAMPA(pnOidConcurso       NUMBER,
                                      pnPuntaje           NUMBER)
RETURN NUMBER;

/**************************************************************************
Descripcion        : Calcula el nivel x Alcanzar de acuerdo al nivel actual
                     que tenga y el concurso seleccionado
Fecha Creacion     : 01/10/2009
Parametros Entrada :
           pnOidConcurso : Oid Concurso
           pnNivel : Numero de Nivel

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_NIVEL_POR_ALCAN(pnOidConcurso       NUMBER,
                                    pnNivel             NUMBER)
RETURN NUMBER;

/**************************************************************************
Descripcion        : Calcula el puntaje minimo para un determinado concurso
                     y nivel alcanzando
Fecha Creacion     : 01/10/2009
Parametros Entrada :
           pnOidConcurso : Oid Concurso
           pnNivel : Numero de Nivel

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_DEVUE_PUNTA_MINIM(pnOidConcurso       NUMBER,
                                      pnNivel             NUMBER)
RETURN NUMBER;

/**************************************************************************
Descripcion        : Obtiene el Factor de Conversion del Concurso
Fecha Creacion     : 14/10/2009
Parametros Entrada :
           pnOidConcurso : Oid Concurso

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_DEVUE_FACTO_CONVE(pnOidConcurso       NUMBER)
RETURN NUMBER;
/**************************************************************************
Descripcion       : Envia la informacion de control de las interfaces
Fecha Creacion    : 15/10/2009
Autor             : Jose Cairampoma
***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_CNTRL (psCodigoPais            VARCHAR2,
                                        psCodigoSistema         VARCHAR2,
                                        psCodigoInterfaz        VARCHAR2,
                                        psNombreArchivo         VARCHAR2,
                                        psNumeroLote            VARCHAR2,
                                        psCodigoPaquete         VARCHAR2);
END INT_PKG_MYLBE;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_MYLBE IS

/**************************************************************************
Descripcion       : Envia la informacion de los concursos que han
                    sido seleccionados para enviar a MyLBEL
Fecha Creacion    : 01/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_CONCU(psCodigoPais            VARCHAR2,
                                 psCodigoSistema         VARCHAR2,
                                 psCodigoInterfaz        VARCHAR2,
                                 psNombreArchivo         VARCHAR2,
                                 psCodigoPeriodo         VARCHAR2,
                                 psFechaFacturacion      VARCHAR2)
IS
  CURSOR c_interfaz IS
    SELECT psCodigoPais,
           gen.num_conc,
           gen.val_nomb,
           (CASE
                 WHEN (gen.BCAL_OID_BASE_CALC = 4) THEN 'R'
                 WHEN (gen.VAL_OBSE like 'PROGRAMA%') THEN 'P'
                 ELSE 'V'
            END) agrupacion1,
           NVL((SELECT clc.cod_clas_conc
              FROM INC_CLASI_CONCU clc
             WHERE clc.oid_clas_conc = gen.ccon_oid_clas_conc),'R') agrupacion2,
           DECODE(ver.vico_oid_vige_conc, 6, 'C', 'P') estado,
           pre.num_nive,
           INT_PKG_DATAM.INT_FN_DEVUE_CONCU_UNIDA_MEDID(gen.oid_para_gral) base,
           INT_FN_MLB_DEVUE_CODIG_PERIO(gen.perd_oid_peri_desd) periodoInicio,
           INT_FN_MLB_DEVUE_CODIG_PERIO(gen.perd_oid_peri_hast) periodoFin,
           INT_FN_MLB_DEVUE_CODIG_PERIO(cal.perd_oid_peri_desd) periodoPuntajeInicio,
           INT_FN_MLB_DEVUE_CODIG_PERIO(cal.perd_oid_peri_hast) periodoPuntajeFin,
           DECODE(ivr.oid_conc_ivr, NULL, '0', '1') indicador
    FROM  INC_CONCU_PARAM_GENER gen,
          INC_VERSI_CONCU ver,
          INC_PARAM_GENER_PREMI pre,
          CRA_PERIO cra,
          SEG_PERIO_CORPO cor,
          INC_PARAM_CALIF cal,
          INC_CONCU_IVR ivr,
          INC_CONCU_PARAM_CONSU con
    WHERE gen.OID_PARA_GRAL = pre.COPA_OID_PARA_GRAL
      AND gen.OID_PARA_GRAL = ver.COPA_OID_PARA_GRAL
      AND ((gen.IND_ACTI = 1 AND ver.VICO_OID_VIGE_CONC = 1) OR
           (gen.IND_ACTI = 0 AND ver.VICO_OID_VIGE_CONC = 6))
      AND gen.perd_oid_peri_desd = cra.oid_peri
      AND cra.peri_oid_peri = cor.oid_peri
      AND cor.cod_peri <= psCodigoPeriodo
      AND cal.copa_oid_para_gral(+) = gen.oid_para_gral
      AND ivr.oid_conc_ivr(+) = gen.coiv_oid_conc_ivr
      AND gen.oid_para_gral = con.copa_oid_para_gral(+)
      AND NOT ((pre.perd_oid_peri IS NOT NULL) AND (INT_FN_MLB_DEVUE_CODIG_PERIO(pre.perd_oid_peri,1) < psCodigoPeriodo))
      AND NOT ((pre.perd_oid_peri IS NULL) AND (gen.bcal_oid_base_calc=4) AND
              (INT_FN_MLB_DEVUE_CODIG_PERIO(gen.perd_oid_peri_hast, con.val_peri_eval+1) < psCodigoPeriodo))
    ORDER BY gen.num_conc;


  TYPE interfazCon IS RECORD
  (
    codigoPais             SEG_PAIS.COD_PAIS%TYPE,
    numeroConcurso         INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
    descripcionConcurso    INC_CONCU_PARAM_GENER.VAL_NOMB%TYPE,
    codigoAgrupacion1      VARCHAR2(1),
    codigoAgrupacion2      VARCHAR2(1),
    estadoConcurso         VARCHAR2(1),
    numeroNivel            INC_PARAM_GENER_PREMI.NUM_NIVE%TYPE,
    baseCalculo            VARCHAR2(1),
    campanaInicioConcurso  SEG_PERIO_CORPO.COD_PERI%TYPE,
    campanaFinConcurso     SEG_PERIO_CORPO.COD_PERI%TYPE,
    campanaInicioCalculo   SEG_PERIO_CORPO.COD_PERI%TYPE,
    campanaFinCalculo      SEG_PERIO_CORPO.COD_PERI%TYPE,
    indicadorIVR           VARCHAR2(1)
  );

  TYPE interfazConTab  IS TABLE OF interfazCon ;
  interfazRecord interfazConTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  --Limpiamos la tabla de Concursos
  EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_MLB_CONCU';

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz;
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            INSERT INTO INT_MLB_CONCU(COD_PAIS, NUM_CONC, COD_PERI, FEC_FACT)
              VALUES(psCodigoPais, interfazRecord(x).numeroConcurso, psCodigoPeriodo, psFechaFacturacion);

            lsLinea :=  interfazRecord(x).codigoPais                ||';'||
                        interfazRecord(x).numeroConcurso            ||';'||
                        interfazRecord(x).descripcionConcurso       ||';'||
                        interfazRecord(x).codigoAgrupacion1         ||';'||
                        interfazRecord(x).codigoAgrupacion2         ||';'||
                        interfazRecord(x).estadoConcurso            ||';'||
                        interfazRecord(x).numeroNivel               ||';'||
                        interfazRecord(x).baseCalculo               ||';'||
                        interfazRecord(x).campanaInicioConcurso     ||';'||
                        interfazRecord(x).campanaFinConcurso        ||';'||
                        interfazRecord(x).campanaInicioCalculo      ||';'||
                        interfazRecord(x).campanaFinCalculo         ||';'||
                        interfazRecord(x).indicadorIVR;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MLB_ENVIA_CONCU: '||ls_sqlerrm);

END INT_PR_MLB_ENVIA_CONCU;


/**************************************************************************
Descripcion       : Envia la informacion de los Niveles de los concursos que han
                    sifo seleccionados para enviar
Fecha Creacion    : 30/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_NIVEL(psCodigoPais            VARCHAR2,
                                 psCodigoSistema         VARCHAR2,
                                 psCodigoInterfaz        VARCHAR2,
                                 psNombreArchivo         VARCHAR2,
                                 psCodigoPeriodo         VARCHAR2,
                                 psFechaFacturacion      VARCHAR2)
IS
  CURSOR c_interfaz IS
    SELECT psCodigoPais,
           g.num_conc,
           n.num_nive,
           ' ' blancos30,
           CASE
              WHEN n.num_cant_inic_punt IS NOT NULL
                 THEN n.num_cant_inic_punt
              WHEN n.num_cant_inic_punt IS NULL
                 THEN n.num_cant_fija_punt
           END puntaje_minimo,
           n.num_cant_fina_punt
      FROM inc_concu_param_gener g,
           inc_param_gener_premi p,
           inc_param_nivel_premi n,
           int_mlb_concu c
     WHERE g.num_conc = c.num_conc
       AND g.oid_para_gral = p.copa_oid_para_gral
       AND p.oid_para_gene_prem = n.pagp_oid_para_gene_prem
     ORDER BY num_conc, num_nive;

  TYPE interfazNiv IS RECORD
  (
    codigoPais        SEG_PAIS.COD_PAIS%TYPE,
    numeroConcurso    INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
    numeroNivel       INC_PARAM_GENER_PREMI.NUM_NIVE%TYPE,
    descripcionNivel  VARCHAR2(30),
    puntajeMinimo     NUMBER(7),
    puntajeMaximo     NUMBER(7)
  );

  TYPE interfazNivTab  IS TABLE OF interfazNiv ;
  interfazRecord interfazNivTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz;
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).numeroConcurso        ||';'||
                        interfazRecord(x).numeroNivel           ||';'||
                        interfazRecord(x).descripcionNivel      ||';'||
                        interfazRecord(x).puntajeMinimo         ||';'||
                        interfazRecord(x).puntajeMaximo;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MLB_ENVIA_NIVEL: '||ls_sqlerrm);

END INT_PR_MLB_ENVIA_NIVEL;


/**************************************************************************
Descripcion       : Envia la informacion de los Premios de los concursos que han
                    sifo seleccionados para enviar
Fecha Creacion    : 30/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_PREMI(psCodigoPais            VARCHAR2,
                                 psCodigoSistema         VARCHAR2,
                                 psCodigoInterfaz        VARCHAR2,
                                 psNombreArchivo         VARCHAR2,
                                 psCodigoPeriodo         VARCHAR2,
                                 psFechaFacturacion      VARCHAR2)
IS
  CURSOR c_interfaz IS
    SELECT psCodigoPais,
           g.num_conc,
           n.num_nive,
           l.num_prem,
           al.cod_vent_fict
      FROM inc_concu_param_gener g,
           inc_param_gener_premi p,
           inc_param_nivel_premi n,
           inc_premi_artic a,
           inc_lote_premi_artic l,
           inc_artic_lote al,
           int_mlb_concu c
     WHERE g.num_conc = c.num_conc
       AND g.oid_para_gral = p.copa_oid_para_gral
       AND p.oid_para_gene_prem = n.pagp_oid_para_gene_prem
       AND n.oid_para_nive_prem = a.panp_oid_para_nive_prem
       AND a.oid_prem_arti = l.prar_oid_prem_arti
       AND l.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
     ORDER BY g.num_conc, n.num_nive, l.num_prem;

  TYPE interfazPre IS RECORD
  (
    codigoPais           SEG_PAIS.COD_PAIS%TYPE,
    numeroConcurso       INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
    numeroNivel          INC_PARAM_GENER_PREMI.NUM_NIVE%TYPE,
    numeroPremio         INC_LOTE_PREMI_ARTIC.NUM_PREM%TYPE,
    codigoVentaFicticio  INC_ARTIC_LOTE.COD_VENT_FICT%TYPE
  );

  TYPE interfazPreTab  IS TABLE OF interfazPre ;
  interfazRecord interfazPreTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz;
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).numeroConcurso        ||';'||
                        interfazRecord(x).numeroNivel           ||';'||
                        interfazRecord(x).numeroPremio          ||';'||
                        interfazRecord(x).codigoVentaFicticio;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MLB_ENVIA_PREMI: '||ls_sqlerrm);

END INT_PR_MLB_ENVIA_PREMI;


/**************************************************************************
Descripcion       : Envia la informacion de las descripciones de los Premios
                    de los concursos que han sido seleccionados para enviar
Fecha Creacion    : 30/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_DESCR_PREMI(psCodigoPais            VARCHAR2,
                                       psCodigoSistema         VARCHAR2,
                                       psCodigoInterfaz        VARCHAR2,
                                       psNombreArchivo         VARCHAR2,
                                       psCodigoPeriodo         VARCHAR2,
                                       psFechaFacturacion      VARCHAR2)
IS
  CURSOR c_interfaz IS
    SELECT DISTINCT psCodigoPais,
           al.cod_vent_fict,
           m.COD_SAP codigo_sap,
           SUBSTR(pq_apl_aux.Valor_Gen_I18n_Sicc(1, al.PROD_OID_PROD, 'MAE_PRODU'),1, 200) descripcion
      FROM inc_concu_param_gener g,
           inc_param_gener_premi p,
           inc_param_nivel_premi n,
           inc_premi_artic a,
           inc_lote_premi_artic l,
           inc_artic_lote al,
           int_mlb_concu c,
           mae_produ m
     WHERE g.num_conc = c.num_conc
       AND g.oid_para_gral = p.copa_oid_para_gral
       AND p.oid_para_gene_prem = n.pagp_oid_para_gene_prem
       AND n.oid_para_nive_prem = a.panp_oid_para_nive_prem
       AND a.oid_prem_arti = l.prar_oid_prem_arti
       AND l.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
       AND m.OID_PROD(+) = al.PROD_OID_PROD;

  TYPE interfazPre IS RECORD
  (
    codigoPais           SEG_PAIS.COD_PAIS%TYPE,
    codigoVentaFicticio  INC_ARTIC_LOTE.COD_VENT_FICT%TYPE,
    codigoSAP            MAE_PRODU.COD_SAP%TYPE,
    descripcionPremio    VARCHAR2(200)
  );

  TYPE interfazPreTab  IS TABLE OF interfazPre ;
  interfazRecord interfazPreTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz;
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).codigoVentaFicticio   ||';'||
                        interfazRecord(x).codigoSAP             ||';'||
                        interfazRecord(x).descripcionPremio;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MLB_ENVIA_DESCR_PREMI: '||ls_sqlerrm);

END INT_PR_MLB_ENVIA_DESCR_PREMI;

/**************************************************************************
Descripcion        : Devuelve el codigo de Periodo
Fecha Creacion     : 30/09/2009
Parametros Entrada :
           pnOidPeriodo : Oid Periodo
           pnNumPeriodo : Numero de Periodos a Avanzar o Retroceder

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_DEVUE_CODIG_PERIO(pnOidPeriodo        NUMBER,
                                      pnNumPeriodo        NUMBER := NULL)
RETURN NUMBER IS
  lsCodigoPeriodo   SEG_PERIO_CORPO.COD_PERI%TYPE;

  lnIdPais          SEG_PAIS.OID_PAIS%TYPE;
  lnIdMarca         SEG_MARCA.OID_MARC%TYPE;
  lnIdCanal         SEG_CANAL.OID_CANA%TYPE;

BEGIN
  IF(pnOidPeriodo IS NULL) THEN
    RETURN NULL;
  END IF;

  /* Obteniendo id de periodo */
   SELECT cor.cod_peri, cra.pais_oid_pais, cra.marc_oid_marc, cra.cana_oid_cana
     INTO lsCodigoPeriodo, lnIdPais, lnIdMarca, lnIdCanal
    FROM CRA_PERIO cra,
         SEG_PERIO_CORPO cor
   WHERE cra.peri_oid_peri = cor.oid_peri
     AND cra.oid_peri = pnOidPeriodo;

  IF(pnNumPeriodo IS NOT NULL) THEN
    lsCodigoPeriodo:= per_pkg_repor_perce.per_fn_obtie_perio(lsCodigoPeriodo,
                                              lnIdPais,
                                              lnIdMarca,
                                              lnIdCanal,
                                              pnNumPeriodo);
  END IF;

  RETURN lsCodigoPeriodo;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_FN_MLB_DEVUE_CODIG_PERIO: '||ls_sqlerrm);

END INT_FN_MLB_DEVUE_CODIG_PERIO;


/**************************************************************************
Descripcion       : Envia la informacion de los Zonas de los concursos que han
                    sifo seleccionados para enviar
Fecha Creacion    : 29/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_ZONAS (psCodigoPais            VARCHAR2,
                                  psCodigoSistema         VARCHAR2,
                                  psCodigoInterfaz        VARCHAR2,
                                  psNombreArchivo         VARCHAR2,
                                  psCodigoPeriodo         VARCHAR2,
                                  psFechaFacturacion      VARCHAR2)
IS
  CURSOR c_interfaz IS
    SELECT psCodigoPais,
           c.num_conc,
           z.cod_zona
     FROM inc_concu_param_gener c,
          inc_ambit_geogr a,
          zon_zona z,
          int_mlb_concu m
    WHERE c.num_conc = m.num_conc
      AND c.oid_para_gral = a.copa_oid_para_gral
      AND a.zzon_oid_zona IS NOT NULL
      AND a.zzon_oid_zona = z.oid_zona
    UNION
    SELECT psCodigoPais,
           c.num_conc,
           z.cod_zona
     FROM inc_concu_param_gener c,
          inc_ambit_geogr a,
          zon_zona z,
          int_mlb_concu m
    WHERE c.num_conc = m.num_conc
      AND c.oid_para_gral = a.copa_oid_para_gral
      AND a.zorg_oid_regi IS NOT NULL
      AND a.zzon_oid_zona IS NULL
      AND a.zorg_oid_regi = z.zorg_oid_regi
      AND z.ind_acti = 1;

  TYPE interfazZon IS RECORD
  (
    codigoPais        SEG_PAIS.COD_PAIS%TYPE,
    numeroConcurso    INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
    codigoZona        ZON_ZONA.COD_ZONA%TYPE
  );

  TYPE interfazZonTab  IS TABLE OF interfazZon ;
  interfazRecord interfazZonTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz;
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).numeroConcurso        ||';'||
                        interfazRecord(x).codigoZona;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MLB_ENVIA_ZONAS: '||ls_sqlerrm);

END INT_PR_MLB_ENVIA_ZONAS;


/**************************************************************************
Descripcion       : Envia la informacion de Premios Asignados
Fecha Creacion    : 29/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_PREMI_ASIGN (psCodigoPais            VARCHAR2,
                                        psCodigoSistema         VARCHAR2,
                                        psCodigoInterfaz        VARCHAR2,
                                        psNombreArchivo         VARCHAR2,
                                        psCodigoPeriodo         VARCHAR2,
                                        psFechaFacturacion      VARCHAR2)
IS
  CURSOR c_interfaz IS
    SELECT psCodigoPais,
           gen.num_conc concurso,
          (SELECT n.NUM_NIVE
             from INC_PARAM_GENER_PREMI g,
                  INC_PARAM_NIVEL_PREMI n,
                  INC_PREMI_ARTIC a,
                  INC_LOTE_PREMI_ARTIC l
          	where g.COPA_OID_PARA_GRAL = pc.COPA_OID_PARA_GENE
           	  and g.OID_PARA_GENE_PREM = n.PAGP_OID_PARA_GENE_PREM
            	and n.OID_PARA_NIVE_PREM = a.PANP_OID_PARA_NIVE_PREM
            	and a.OID_PREM_ARTI = l.PRAR_OID_PREM_ARTI
         	    and l.NUM_PREM = pc.NUM_PREM) nivel,
           pc.num_prem numero_premio,
           cli.cod_clie,
           pd.val_codi_vent_fict codigo_venta,
           pd.num_unid_dema_real unidades_premio,
           cor.cod_peri periodo,
           TO_CHAR(pc.Fec_fact,'YYYYMMDD') fecha
      FROM PED_SOLIC_CABEC pc, PED_SOLIC_POSIC pd, INC_CONCU_PARAM_GENER gen, MAE_CLIEN cli,
           PED_TIPO_SOLIC_PAIS tip, PED_TIPO_SOLIC sol, CRA_PERIO cra, SEG_PERIO_CORPO cor
     WHERE pc.fec_fact = TO_DATE(psFechaFacturacion, 'DD/MM/YYYY')
       AND pc.copa_oid_para_gene IS NOT NULL
       AND pc.tspa_oid_tipo_soli_pais = tip.oid_tipo_soli_pais
       AND tip.tsol_oid_tipo_soli = sol.oid_tipo_soli
       AND sol.cod_tipo_soli = 'SINC'
       AND pc.oid_soli_cabe = pd.soca_oid_soli_cabe
       AND gen.oid_para_gral = pc.copa_oid_para_gene
       AND cli.oid_clie = pc.clie_oid_clie
       AND cra.oid_peri = pc.perd_oid_peri
       AND cor.oid_peri = cra.peri_oid_peri;

  TYPE interfazPre IS RECORD
  (
    codigoPais            SEG_PAIS.COD_PAIS%TYPE,
    numeroConcurso        INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
    numeroNivelConcurso   VARCHAR2(2),
    numeroPremio          VARCHAR2(3),
    codigoConsultora      VARCHAR2(15),
    codigoVentaFicticio   VARCHAR2(6),
    unidadesPremios       NUMBER(2),
    campanaAsignacion     VARCHAR2(6),
    fechaAsignacion       VARCHAR2(8)

  );

  TYPE interfazPreTab  IS TABLE OF interfazPre ;
  interfazRecord interfazPreTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz;
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).numeroConcurso        ||';'||
                        interfazRecord(x).numeroNivelConcurso   ||';'||
                        interfazRecord(x).numeroPremio          ||';'||
                        interfazRecord(x).codigoConsultora      ||';'||
                        interfazRecord(x).codigoVentaFicticio   ||';'||
                        interfazRecord(x).unidadesPremios       ||';'||
                        interfazRecord(x).campanaAsignacion     ||';'||
                        interfazRecord(x).fechaAsignacion;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MLB_ENVIA_PREMI_ASIGN: '||ls_sqlerrm);

END INT_PR_MLB_ENVIA_PREMI_ASIGN;


/**************************************************************************
Descripcion       : Envia la informacion de Premios Despachados
Fecha Creacion    : 29/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_PREMI_DESPA (psCodigoPais            VARCHAR2,
                                        psCodigoSistema         VARCHAR2,
                                        psCodigoInterfaz        VARCHAR2,
                                        psNombreArchivo         VARCHAR2,
                                        psCodigoPeriodo         VARCHAR2,
                                        psFechaFacturacion      VARCHAR2)
IS
  CURSOR c_interfaz IS
    SELECT psCodigoPais,
           gen.num_conc concurso,
           (SELECT n.NUM_NIVE
             from INC_PARAM_GENER_PREMI g,
                  INC_PARAM_NIVEL_PREMI n,
                  INC_PREMI_ARTIC a,
                  INC_LOTE_PREMI_ARTIC l
          	where g.COPA_OID_PARA_GRAL = pc.COPA_OID_PARA_GENE
           	  and g.OID_PARA_GENE_PREM = n.PAGP_OID_PARA_GENE_PREM
            	and n.OID_PARA_NIVE_PREM = a.PANP_OID_PARA_NIVE_PREM
            	and a.OID_PREM_ARTI = l.PRAR_OID_PREM_ARTI
         	    and l.NUM_PREM = pc.NUM_PREM) nivel,
           pc.num_prem numero_premio,
           cli.cod_clie,
           pd.val_codi_vent_fict codigo_venta,
           pd.val_codi_vent_fict codigo_venta1,
           pd.num_unid_aten  unidades_atendidas,
           cor.cod_peri periodo,
           (SELECT VAL_NUME_SOLI FROM PED_SOLIC_CABEC pcon
            WHERE pcon.OID_SOLI_CABE = pc.SOCA_OID_SOLI_CABE) Factura,
           TO_CHAR(pc.Fec_fact,'YYYYMMDD') fecha
      FROM PED_SOLIC_CABEC pc, PED_SOLIC_POSIC pd, INC_CONCU_PARAM_GENER gen, MAE_CLIEN cli,
           PED_TIPO_SOLIC_PAIS tip, PED_TIPO_SOLIC sol, CRA_PERIO cra, SEG_PERIO_CORPO cor
     WHERE pc.fec_fact = TO_DATE(psFechaFacturacion,'DD/MM/YYYY')
       AND pc.copa_oid_para_gene IS NOT NULL
       AND pc.tspa_oid_tipo_soli_pais = tip.oid_tipo_soli_pais
       AND tip.tsol_oid_tipo_soli = sol.oid_tipo_soli
       AND sol.cod_tipo_soli IN ('SINC', 'SIFC')
       AND pc.oid_soli_cabe = pd.soca_oid_soli_cabe
       AND gen.oid_para_gral = pc.copa_oid_para_gene
       AND cli.oid_clie = pc.clie_oid_clie
       AND cra.oid_peri = pc.perd_oid_peri
       AND cor.oid_peri = cra.peri_oid_peri
       AND pd.num_unid_aten > 0;

  TYPE interfazPre IS RECORD
  (
    codigoPais            SEG_PAIS.COD_PAIS%TYPE,
    numeroConcurso        INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
    numeroNivelConcurso   VARCHAR2(2),
    numeroPremio          VARCHAR2(3),
    codigoConsultora      VARCHAR2(15),
    codigoVentaFicticio   VARCHAR2(6),
    codigoVentaFicticio1  VARCHAR2(6),
    unidadesPremios       NUMBER(2),
    campanaDespacho       VARCHAR2(6),
    numeroFactura         VARCHAR2(10),
    fechaDespacho         VARCHAR2(8)

  );

  TYPE interfazPreTab  IS TABLE OF interfazPre ;
  interfazRecord interfazPreTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz;
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).numeroConcurso        ||';'||
                        interfazRecord(x).numeroNivelConcurso   ||';'||
                        interfazRecord(x).numeroPremio          ||';'||
                        interfazRecord(x).codigoConsultora      ||';'||
                        interfazRecord(x).codigoVentaFicticio   ||';'||
                        interfazRecord(x).codigoVentaFicticio1   ||';'||
                        interfazRecord(x).unidadesPremios       ||';'||
                        interfazRecord(x).campanaDespacho       ||';'||
                        interfazRecord(x).numeroFactura         ||';'||
                        interfazRecord(x).fechaDespacho;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MLB_ENVIA_PREMI_DESPA: '||ls_sqlerrm);

END INT_PR_MLB_ENVIA_PREMI_DESPA;

/**************************************************************************
Descripcion       : Envia la informacion de Puntajes
Fecha Creacion    : 01/10/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_PUNTA(psCodigoPais            VARCHAR2,
                                 psCodigoSistema         VARCHAR2,
                                 psCodigoInterfaz        VARCHAR2,
                                 psNombreArchivo         VARCHAR2,
                                 psCodigoPeriodo         VARCHAR2,
                                 psFechaFacturacion      VARCHAR2)
IS
  CURSOR c_interfaz(oidPeriodo NUMBER) IS
    SELECT psCodigoPais,
           x.oid_para_gral,
           num_conc,
           x.clie_oid_clie,
           GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(clie_oid_clie),
           psCodigoPeriodo,
          (SELECT SUM(ccp.num_punt)
                   FROM inc_cuent_corri_punto ccp
                  WHERE ccp.copa_oid_para_gral = x.oid_para_gral
                    AND ccp.perd_oid_peri = oidPeriodo
                    AND NOT (ccp.val_desc like 'Entrega de Premio%')
                    AND ccp.clie_oid_clie = x.clie_oid_clie) puntajeCampana,
          (SELECT SUM(ccp.num_punt)
                   FROM inc_cuent_corri_punto ccp
                  WHERE ccp.copa_oid_para_gral = x.oid_para_gral
                    AND ccp.val_desc like 'Entrega de Premio%'
                    AND ccp.clie_oid_clie = x.clie_oid_clie) * (-1) puntajeUtilizado,
          (SELECT SUM(ccp.num_punt)
                   FROM inc_cuent_corri_punto ccp
                  WHERE ccp.copa_oid_para_gral = x.oid_para_gral
                    AND ccp.clie_oid_clie = x.clie_oid_clie) puntajeAcumulado,
          (SELECT SUM(sol.val_punt_boni)
                  FROM inc_solic_concu_punta sol
                 WHERE sol.copa_oid_para_gral = x.oid_para_gral
                   AND sol.clie_oid_clie = x.clie_oid_clie
                   AND sol.perd_oid_peri = oidPeriodo) puntajeBonificadoCampana,
          (SELECT SUM(sol.val_punt_boni)
                  FROM inc_solic_concu_punta sol
                 WHERE sol.copa_oid_para_gral = x.oid_para_gral
                   AND sol.clie_oid_clie = x.clie_oid_clie ) puntajeBonificadoAcumulado,
           NVL((SELECT ven.val_meta
                  FROM inc_metas_tipo_venta ven
                 WHERE ven.copa_oid_para_gral = x.oid_para_gral
                    AND ven.clie_oid_clie = x.clie_oid_Clie),0) puntajeBase,
           NULL, NULL, 0, 0, x.puntaje
    FROM
          (SELECT gen.oid_para_gral,gen.num_conc, pun.clie_oid_clie, SUM(pun.num_punt) puntaje
           FROM  INC_CONCU_PARAM_GENER gen,
                 INT_MLB_CONCU con,
                 INC_CUENT_CORRI_PUNTO pun
          WHERE gen.num_conc = con.num_conc
            AND pun.copa_oid_para_gral = gen.oid_para_gral
            AND NOT (pun.val_desc LIKE 'Entrega de Premio%')
            AND pun.num_punt <> 0
          GROUP BY gen.oid_para_gral, gen.num_conc, pun.clie_oid_clie) x;

  TYPE interfazPun IS RECORD
  (
    codigoPais                 SEG_PAIS.COD_PAIS%TYPE,
    oidConcurso                INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE,
    numeroConcurso             INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
    oidConsultora              MAE_CLIEN.OID_CLIE%TYPE,
    codigoConsultora           MAE_CLIEN.COD_CLIE%TYPE,
    campanaFacturacion         SEG_PERIO_CORPO.COD_PERI%TYPE,
    puntajeAlcanzadoCampana    NUMBER(12),
    puntajeUtilizado           NUMBER(12),
    puntateAcumulado           NUMBER(12),
    puntajeBonificadoCampana   NUMBER(12),
    puntajeBonificadoAcumu     NUMBER(12),
    puntajeBase                INC_METAS_TIPO_VENTA.VAL_META%TYPE,
    nivelAlcanzadoCampana      INC_PARAM_NIVEL_PREMI.NUM_NIVE%TYPE,
    nivelPorAlcanzar           INC_PARAM_NIVEL_PREMI.NUM_NIVE%TYPE,
    puntajeMinimoSigNivel      NUMBER(12),
    puntajeNecesarioSigNivel   NUMBER(12),
    puntajeObtenido            NUMBER(12)
  );

  TYPE interfazPunTab  IS TABLE OF interfazPun ;
  interfazRecord interfazPunTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;

  lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo                CRA_PERIO.OID_PERI%TYPE;
  lnFactor                    INC_OBTEN_PUNTO.VAL_FACT_CONV%TYPE;
BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz(lnOidPeriodo);
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

            --Recalculamos el puntajeBase
            IF(interfazRecord(x).puntajeBase > 0) THEN
              lnFactor := INT_FN_MLB_DEVUE_FACTO_CONVE(interfazRecord(x).oidConcurso);

              IF(lnFactor = 0.0) THEN
                interfazRecord(x).puntajeBase := 0;
              ELSE
                interfazRecord(x).puntajeBase := ROUND(interfazRecord(x).puntajeBase/lnFactor);
              END IF;
            END IF;

            --Si puntaje Alcanzado en Campana es Negativo, se envia 0
            IF((interfazRecord(x).puntajeAlcanzadoCampana IS NOT NULL) AND
               (interfazRecord(x).puntajeAlcanzadoCampana < 0)) THEN
               interfazRecord(x).puntajeAlcanzadoCampana := 0;
            END IF;

            --Si puntaje Acumulado es Negativo, se envia 0
            IF((interfazRecord(x).puntateAcumulado IS NOT NULL) AND
               (interfazRecord(x).puntateAcumulado < 0)) THEN
               interfazRecord(x).puntateAcumulado := 0;
            END IF;

            --Recuperamos el nivel alcanzado en campaña por la consultora
            interfazRecord(x).nivelAlcanzadoCampana := INT_FN_MLB_NIVEL_ALCAN_CAMPA(interfazRecord(x).oidConcurso,
                                                             interfazRecord(x).puntajeObtenido - interfazRecord(x).puntajeBase);

            --Recuperamos el siguiente nivel a alcanzar
            interfazRecord(x).nivelPorAlcanzar :=  INT_FN_MLB_NIVEL_POR_ALCAN(interfazRecord(x).oidConcurso,
                                                                          interfazRecord(x).nivelAlcanzadoCampana);

            --Se recupera puntaje minimo del siguiente nivel a alcanzar y el puntaje que necesita la consultora
            --para pasar a dicho nivel
            IF(interfazRecord(x).nivelPorAlcanzar IS NOT NULL) THEN
              interfazRecord(x).puntajeMinimoSigNivel := INT_FN_MLB_DEVUE_PUNTA_MINIM(interfazRecord(x).oidConcurso,
                                                                                      interfazRecord(x).nivelPorAlcanzar);

              interfazRecord(x).puntajeNecesarioSigNivel := interfazRecord(x).puntajeMinimoSigNivel
                                                            + interfazRecord(x).puntajeBase
                                                            - interfazRecord(x).puntajeObtenido;

            END IF;

            lsLinea :=  interfazRecord(x).codigoPais                  ||';'||
                        interfazRecord(x).numeroConcurso              ||';'||
                        interfazRecord(x).codigoConsultora            ||';'||
                        interfazRecord(x).campanaFacturacion          ||';'||
                        interfazRecord(x).puntajeAlcanzadoCampana     ||';'||
                        interfazRecord(x).puntajeUtilizado            ||';'||
                        interfazRecord(x).puntateAcumulado            ||';'||
                        interfazRecord(x).puntajeBonificadoCampana    ||';'||
                        interfazRecord(x).puntajeBonificadoAcumu      ||';'||
                        interfazRecord(x).puntajeBase                 ||';'||
                        interfazRecord(x).nivelAlcanzadoCampana       ||';'||
                        interfazRecord(x).nivelPorAlcanzar            ||';'||
                        interfazRecord(x).puntajeMinimoSigNivel       ||';'||
                        interfazRecord(x).puntajeNecesarioSigNivel;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MLB_ENVIA_PUNTA: '||ls_sqlerrm);

END INT_PR_MLB_ENVIA_PUNTA;


/**************************************************************************
Descripcion       : Envia la informacion de Recomendaciones
Fecha Creacion    : 29/09/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion  :  Fecha de Facturacion

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_RECOM (psCodigoPais            VARCHAR2,
                                  psCodigoSistema         VARCHAR2,
                                  psCodigoInterfaz        VARCHAR2,
                                  psNombreArchivo         VARCHAR2,
                                  psCodigoPeriodo         VARCHAR2,
                                  psFechaFacturacion      VARCHAR2)
IS
  CURSOR c_interfaz IS
    SELECT psCodigoPais,
           c.num_conc,
           (SELECT COD_CLIE FROM MAE_CLIEN WHERE OID_CLIE = rt.clie_oid_clie) recomendante,
           (SELECT COD_CLIE FROM MAE_CLIEN WHERE OID_CLIE = rd.clie_oid_clie) recomendada,
           cor.cod_peri,
           INT_PKG_MYLBE.INT_FN_MLB_TOTAL_PEDID_RECOM(rt.clie_oid_clie, rd.perd_oid_peri, s.val_peri_eval) pedidos_recomendante,
           INT_PKG_MYLBE.INT_FN_MLB_TOTAL_PEDID_RECOM(rd.clie_oid_clie, rd.perd_oid_peri, s.val_peri_eval) pedidos_recomendada,
           NVL(rd.ind_efec,0) ind_efec
      FROM INC_CONCU_PARAM_GENER c,
           INC_CONCU_PARAM_CONSU s,
           INC_CLIEN_RECTE rt,
           INC_CLIEN_RECDO rd,
           INT_MLB_CONCU con,
           CRA_PERIO cra,
           SEG_PERIO_CORPO cor
     WHERE c.num_conc = con.num_conc
       AND c.bcal_oid_base_calc = 4
       AND c.oid_para_gral = s.copa_oid_para_gral
       AND c.oid_para_gral = rt.copa_oid_para_gral
       AND rt.oid_clie_rete = rd.clr3_oid_clie_rete
       AND rd.perd_oid_peri = cra.oid_peri
       AND cra.peri_oid_peri = cor.oid_peri;

  TYPE interfazRec IS RECORD
  (
    codigoPais             SEG_PAIS.COD_PAIS%TYPE,
    numeroConcurso         INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
    codigoConsultoraRecte  MAE_CLIEN.COD_CLIE%TYPE,
    codigoConsultoraRecda  MAE_CLIEN.COD_CLIE%TYPE,
    campanaRecomendacion   SEG_PERIO_CORPO.COD_PERI%TYPE,
    numeroPedidosRecte     NUMBER(4),
    numeroPedidosRecda     NUMBER(4),
    indicadorRecomEfectiva VARCHAR2(1)
  );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz;
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).numeroConcurso        ||';'||
                        interfazRecord(x).codigoConsultoraRecte ||';'||
                        interfazRecord(x).codigoConsultoraRecda ||';'||
                        interfazRecord(x).campanaRecomendacion  ||';'||
                        interfazRecord(x).numeroPedidosRecte    ||';'||
                        interfazRecord(x).numeroPedidosRecda    ||';'||
                        interfazRecord(x).indicadorRecomEfectiva;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MLB_ENVIA_RECOM: '||ls_sqlerrm);

END INT_PR_MLB_ENVIA_RECOM;

/**************************************************************************
Descripcion        : Calcula el Total de Pedidos realizado por un cliente en un
                     determinado rango de periodos, para enviarlo en la interfaz
                     de Recomendaciones para My LBEL
                     periodo
Fecha Creacion     : 30/09/2009
Parametros Entrada :
           pnOidCliente : Oid Cliente
           pnOidPeriodo : Oid Periodo
           pnValPeriEval : Valor Periodo Evaluacion

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_TOTAL_PEDID_RECOM(pnOidCliente        NUMBER,
                                      pnOidPeriodo        NUMBER,
                                      pnValPeriEval       NUMBER)
RETURN NUMBER IS
  lnTotal NUMBER;

  lsPeriodoInicio   SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodoFin   SEG_PERIO_CORPO.COD_PERI%TYPE;
BEGIN
   lsPeriodoInicio := int_pkg_mylbe.INT_FN_MLB_DEVUE_CODIG_PERIO(pnOidPeriodo);
   lsPeriodoFin := int_pkg_mylbe.INT_FN_MLB_DEVUE_CODIG_PERIO(pnOidPeriodo, pnValPeriEval - 1);

  /* Obteniendo id de periodo */
   SELECT COUNT(*)
     INTO lnTotal
    FROM (SELECT * FROM PED_SOLIC_CABEC PED_SOLIC_CABEC WHERE clie_oid_clie = pnOidCliente) x,
         (SELECT * FROM PED_SOLIC_CABEC PED_SOLIC_CABEC WHERE clie_oid_clie = pnOidCliente) y,
         PED_TIPO_SOLIC_PAIS z1,
         (SELECT * FROM PED_TIPO_SOLIC where cod_tipo_soli = 'SOC') z2,
         CRA_PERIO cra,
         SEG_PERIO_CORPO cor
   WHERE x.tspa_oid_tipo_soli_pais = z1.oid_tipo_soli_pais
     AND z1.tsol_oid_tipo_soli = z2.oid_tipo_soli
     AND x.grpr_oid_grup_proc = 5
     AND x.fec_fact IS NOT NULL
     AND x.soca_oid_soli_cabe = y.oid_soli_cabe
     AND y.esso_oid_esta_soli <> 4
     AND (cor.cod_peri >= lsPeriodoInicio)
     AND (cor.cod_peri <= lsPeriodoFin)
     AND cra.oid_peri = x.perd_oid_peri
     and cor.oid_peri = cra.peri_oid_peri;

  RETURN lnTotal;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_FN_MLB_TOTAL_PEDID_RECOM: '||ls_sqlerrm);

END INT_FN_MLB_TOTAL_PEDID_RECOM;


/**************************************************************************
Descripcion        : Calcula el nivel alcanzado de acuerdo al puntaje en el
                     concurso seleccionado
Fecha Creacion     : 01/10/2009
Parametros Entrada :
           pnOidConcurso : Oid Concurso
           pnPuntaje : Puntaje del Cliente

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_NIVEL_ALCAN_CAMPA(pnOidConcurso       NUMBER,
                                      pnPuntaje           NUMBER)
RETURN NUMBER IS
  lnNivel INC_PARAM_NIVEL_PREMI.NUM_NIVE%TYPE;
BEGIN
  /* Obteniendo Nivel Alcanzado */
  SELECT num_nive
    INTO lnNivel
    FROM
         (SELECT x.num_nive
            FROM
                 (SELECT n.num_nive,
                         CASE
                            WHEN n.num_cant_inic_punt IS NOT NULL
                               THEN n.num_cant_inic_punt
                            WHEN n.num_cant_inic_punt IS NULL
                               THEN n.num_cant_fija_punt
                         END puntaje_minimo,
                         n.num_cant_fina_punt puntaje_maximo
                    FROM inc_concu_param_gener g,
                         inc_param_gener_premi p,
                         inc_param_nivel_premi n
                   WHERE g.oid_para_gral = pnOidConcurso
                     AND g.oid_para_gral = p.copa_oid_para_gral
                     AND p.oid_para_gene_prem = n.pagp_oid_para_gene_prem) x
           WHERE pnPuntaje > x.puntaje_minimo
           ORDER BY x.puntaje_minimo DESC)
   WHERE ROWNUM = 1;

  RETURN lnNivel;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN NULL;

END INT_FN_MLB_NIVEL_ALCAN_CAMPA;


/**************************************************************************
Descripcion        : Calcula el nivel x Alcanzar de acuerdo al nivel actual
                     que tenga y el concurso seleccionado
Fecha Creacion     : 01/10/2009
Parametros Entrada :
           pnOidConcurso : Oid Concurso
           pnNivel : Numero de Nivel

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_NIVEL_POR_ALCAN(pnOidConcurso       NUMBER,
                                    pnNivel             NUMBER)
RETURN NUMBER IS
  lnNivel INC_PARAM_NIVEL_PREMI.NUM_NIVE%TYPE;
BEGIN
  IF(pnNivel IS NULL) THEN --Si es Vacio el nivel Actual, se devuelve 1
    lnNivel := 1;
  ELSE
    SELECT num_nive
      INTO lnNivel
      FROM
           (SELECT x.num_nive
              FROM
                   (SELECT n.num_nive
                      FROM inc_concu_param_gener g,
                           inc_param_gener_premi p,
                           inc_param_nivel_premi n
                     WHERE g.oid_para_gral = pnOidConcurso
                       AND g.oid_para_gral = p.copa_oid_para_gral
                       AND p.oid_para_gene_prem = n.pagp_oid_para_gene_prem) x
             WHERE pnNivel < x.num_nive
             ORDER BY x.num_nive)
    WHERE ROWNUM = 1;
  END IF;

  RETURN lnNivel;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN NULL;

END INT_FN_MLB_NIVEL_POR_ALCAN;


/**************************************************************************
Descripcion        : Calcula el puntaje minimo para un determinado concurso
                     y nivel alcanzando
Fecha Creacion     : 01/10/2009
Parametros Entrada :
           pnOidConcurso : Oid Concurso
           pnNivel : Numero de Nivel

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_DEVUE_PUNTA_MINIM(pnOidConcurso       NUMBER,
                                      pnNivel             NUMBER)
RETURN NUMBER IS
  lnPuntaje   INC_PARAM_NIVEL_PREMI.NUM_CANT_INIC_PUNT%TYPE;
BEGIN
  SELECT x.puntaje_minimo
    INTO lnPuntaje
    FROM
         (SELECT n.num_nive,
                 CASE
                    WHEN n.num_cant_inic_punt IS NOT NULL
                       THEN n.num_cant_inic_punt
                    WHEN n.num_cant_inic_punt IS NULL
                       THEN n.num_cant_fija_punt
                 END puntaje_minimo
            FROM inc_concu_param_gener g,
                 inc_param_gener_premi p,
                 inc_param_nivel_premi n
           WHERE g.oid_para_gral = pnOidConcurso
             AND g.oid_para_gral = p.copa_oid_para_gral
             AND p.oid_para_gene_prem = n.pagp_oid_para_gene_prem) x
   WHERE x.num_nive = pnNivel;

  RETURN lnPuntaje;

EXCEPTION
  
  WHEN OTHERS THEN
    RETURN 0;  
  /*  
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_FN_MLB_NIVEL_POR_ALCAN: '||ls_sqlerrm);
*/
END INT_FN_MLB_DEVUE_PUNTA_MINIM;


/**************************************************************************
Descripcion        : Obtiene el Factor de Conversion del Concurso
Fecha Creacion     : 14/10/2009
Parametros Entrada :
           pnOidConcurso : Oid Concurso

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION INT_FN_MLB_DEVUE_FACTO_CONVE(pnOidConcurso       NUMBER)
RETURN NUMBER IS
  lnFactor    INC_OBTEN_PUNTO.VAL_FACT_CONV%TYPE;
BEGIN
  SELECT VAL_FACT_CONV
    INTO lnFactor
    FROM INC_OBTEN_PUNTO
   WHERE COPA_OID_PARA_GRAL = pnOidConcurso;

  RETURN lnFactor;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN 0.0;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_FN_MLB_DEVUE_FACTO_CONVE: '||ls_sqlerrm);

END INT_FN_MLB_DEVUE_FACTO_CONVE;

/**************************************************************************
Descripcion       : Envia la informacion de control de las interfaces
Fecha Creacion    : 15/10/2009
Autor             : Jose Cairampoma
***************************************************************************/
PROCEDURE INT_PR_MLB_ENVIA_CNTRL (psCodigoPais            VARCHAR2,
                                        psCodigoSistema         VARCHAR2,
                                        psCodigoInterfaz        VARCHAR2,
                                        psNombreArchivo         VARCHAR2,
                                        psNumeroLote            VARCHAR2,
                                        psCodigoPaquete         VARCHAR2)
IS
  CURSOR c_interfaz (vscadena VARCHAR2,vsreplace VARCHAR2)IS
 SELECT translate(inte_cod_inte || '-' || des_erro,
                  vscadena,
                  vsreplace)
  FROM bas_histo_lotes
  WHERE num_lote = psnumerolote
    AND (inpa_cod_inte = pscodigopaquete OR inte_cod_inte = pscodigopaquete)
   AND fec_fpro IS NOT NULL
   AND ind_loer = 'S'
    AND inte_cod_inte != pscodigointerfaz;

  TYPE interfazPre IS RECORD
  (
    error           BAS_HISTO_LOTES.DES_ERRO%TYPE

  );

  TYPE interfazPreTab  IS TABLE OF interfazPre ;
  interfazRecord interfazPreTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;

   lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20) || '+';
   lsreplace VARCHAR2(100) := 'a          ';
BEGIN


  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz(lscadena,lsreplace);
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).error;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MLB_ENVIA_CNTRL: '||ls_sqlerrm);

END INT_PR_MLB_ENVIA_CNTRL;


END INT_PKG_MYLBE;
/

