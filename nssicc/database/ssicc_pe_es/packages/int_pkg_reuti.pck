CREATE OR REPLACE PACKAGE "INT_PKG_REUTI" IS
/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;
   /* Declaracion de procedures */
   /*
    * Identifica los OID de clientes que ha sido actualizados a partir
 * de una fecha la cual es pasado como parametro.
    */
   PROCEDURE INT_PR_REU_CARGA_CONSU_ACTUA(fechaUltimoProceso IN DATE);
   /*
    * Elimina todas las tablas (la de calculos parciales y la que contiene
 * la informacion a enviar por la interfaz) y regenera la informacion pero
 * solo para aquellas consultoras que fueron identificadas por el proceso
 * anterior.
 */
   PROCEDURE INT_PR_REU_CARGA_CONSU(codigoPais IN VARCHAR2,
                                    codigoMarca IN VARCHAR2,
         codigoCanal IN VARCHAR2,
                                    codigoTipoCliente IN VARCHAR2,
                                    valorPorcentajeDcto IN VARCHAR2,
         montoMaximoDeuda IN NUMBER);
   /*
    * Elimina todas las tablas (la de calculos parciales y la que contiene
 * la informacion a enviar por la interfaz) y regenera la informacion de
 * todas las consultoras.
 */
   PROCEDURE INT_PR_REU_CARGA_CONSU_HISTO(codigoPais IN VARCHAR2,
                                          codigoMarca IN VARCHAR2,
            codigoCanal IN VARCHAR2,
                                          codigoTipoCliente IN VARCHAR2,
                                          valorPorcentajeDcto IN VARCHAR2,
            montoMaximoDeuda IN NUMBER);

   PROCEDURE INT_PR_REU_CONSO_CONSU(codigoPais IN VARCHAR2,
                                    codigoMarca IN VARCHAR2,
         codigoCanal IN VARCHAR2,
                                          codigoTipoCliente IN VARCHAR2,
                                    valorPorcentajeDcto IN VARCHAR2,
         montoMaximoDeuda IN NUMBER);

   PROCEDURE INT_PR_REU_CONSO_CONSU_HISTO(codigoPais IN VARCHAR2,
                                          codigoMarca IN VARCHAR2,
            codigoCanal IN VARCHAR2,
                                                codigoTipoCliente IN VARCHAR2,
                                          valorPorcentajeDcto IN VARCHAR2,
            montoMaximoDeuda IN NUMBER);
   /*
    * Genera la informacion a partir de las tablas parciales y las tablas
 * basicas de SiCC para generar la tabla que contendra la informacion a
 * enviar.
 */
   PROCEDURE INT_PR_REU_ARCHI_CONSU(pscodigoPais IN VARCHAR2,
                                    psCodigosistema IN VARCHAR2,
                                    psCodigoInterfaz IN VARCHAR2,
                                    psnombreArchivo IN VARCHAR2);

/***************************************************************************
Descripcion       : Genera el archivo para la Interfaz Enviar MAtriz Facturacion REU-2
Fecha Creacion    : 13/06/2008
Autor             : Marco Silva
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodigoMarca    : Codigo Marca
            psCodigoCanal    : Codigo Canal
            psCodigoAcceso   : Codigo Acceso
            psPeriodoInicio  : Periodo Inicio
            psPeriodoFin     : Periodoo Fin

***************************************************************************/
PROCEDURE INT_PR_REU_ENVIA_MATRI
   (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psCodigoAcceso VARCHAR2,
   psPeriodoInicio VARCHAR2,
   psPeriodoFin VARCHAR2
   );
/**************************************************************************
Descripcion        : Obtiene Indicador de Comision Adicional
Fecha Creacion     : 03/03/2007
Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION INT_FN_REU_INDIC_COMIS_ADICI(
  psValCodiVenta VARCHAR2,
  pnIdPeri NUMBER)
RETURN VARCHAR2;
/**************************************************************************
Descripcion        : Devuelve Indicador Comisionable
Fecha Creacion     : 03/03/2007
Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION INT_FN_REU_INDIC_COMIS(
    psCodigoPeriodo            VARCHAR2,
    psCodigoTipoCliente        VARCHAR2,
    psCodigoSubtipoCliente     VARCHAR2,
    psCodigoTipoOferta         VARCHAR2,
    psCodigoNegocio            VARCHAR2,
    psCodigoUnidadNegocio      VARCHAR2,
    psCodigoMarcaProducto      VARCHAR2,
    pnComisionable             NUMBER
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Llena tabla temporal (DAT-86)
Fecha Creacion    : 08/09/2008
Autor             : RRG
***************************************************************************/
PROCEDURE INT_PR_REU_CONSS_UTMPR(
   psCodigoPais       VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2);

/***************************************************************************
Descripcion       : Determina el valor del indicador de autorizacion para pasar
                    pedido, el cual es enviado a la web de SomosEsika / MyBEL.
Fecha Creacion    : 22/01/2009
Autor             : Carlos Hurtado
Parametros        pnOidCliente: OID del Cliente
***************************************************************************/
FUNCTION INT_FN_REU_INDIC_PEDID(pnOidCliente  NUMBER,
                                psCodEstaClie VARCHAR2) RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Determina el status de la consultora a enviar a Calypso.
Fecha Creacion    : 11/02/2009
Autor             : Carlos Hurtado
Parametros        pnOidCliente: OID del Cliente
                  pnMontoMaximoDeuda Monto de deuda maximo el cual sobrepasado
                                     considera a la consultora como morosa.
***************************************************************************/
FUNCTION INT_FN_REU_EVALU_ESTAT_CONSU(pnOidCliente       NUMBER,
                                      psCodEstaClie      VARCHAR2,
                                      psCodZona          VARCHAR2,
                                      pnMontoMaximoDeuda NUMBER) RETURN VARCHAR2;

END INT_PKG_REUTI;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_REUTI" IS
/****************************************************************************
Proyecto Optimizaci�n  c1

Descripcion       :   Carga en los datos del movimiento de cuenta y datos
                      adicionales del cliente y se cargan en la tabla
					  INT_REU_OID_CLIEN_ACTUA.

Fecha Modicicacion: 04/05/2011  15:10
Autor             : Jorge Angulo   JANGULO    
***************************************************************************/
   PROCEDURE INT_PR_REU_CARGA_CONSU_ACTUA(fechaUltimoProceso IN DATE) IS
   CURSOR clie_actu_cur IS
      SELECT CLIE_OID_CLIE
      FROM CCC_MOVIM_CUENT_CORRI
      WHERE FEC_ULTI_ACTU > fechaUltimoProceso
      UNION
      SELECT OID_CLIE
      FROM MAE_CLIEN
      WHERE FEC_ULTI_ACTU > fechaUltimoProceso
      UNION
      SELECT CLIE_OID_CLIE
      FROM MAE_CLIEN_DATOS_ADICI
      WHERE FEC_ULTI_ACTU > fechaUltimoProceso
      UNION
      SELECT CLIE_OID_CLIE
      FROM MAE_CLIEN_DIREC
      WHERE FEC_ULTI_ACTU > fechaUltimoProceso
      UNION
      SELECT CLIE_OID_CLIE
      FROM MAE_CLIEN_COMUN
      WHERE FEC_ULTI_ACTU > fechaUltimoProceso
      UNION
      SELECT CLIE_OID_CLIE
      FROM MAE_CLIEN_UNIDA_ADMIN
      WHERE FEC_ULTI_ACTU > fechaUltimoProceso
      UNION
      SELECT CLIE_OID_CLIE
      FROM MAE_CLIEN_TIPO_SUBTI
      WHERE FEC_ULTI_ACTU > fechaUltimoProceso
      UNION
      SELECT X.CLIE_OID_CLIE
      FROM MAE_CLIEN_TIPO_SUBTI X, MAE_CLIEN_CLASI Y
      WHERE X.OID_CLIE_TIPO_SUBT = Y.CTSU_OID_CLIE_TIPO_SUBT
      AND Y.FEC_ULTI_ACTU > fechaUltimoProceso
      UNION
      SELECT CLIE_OID_CLIE
      FROM MAE_CLIEN_PRIME_CONTA
      WHERE FEC_ULTI_ACTU >= fechaUltimoProceso
      UNION
      SELECT CLIE_OID_CLIE
      FROM MAE_CLIEN_BLOQU
      WHERE FEC_BLOQ >= TRUNC(fechaUltimoProceso)
      UNION
      SELECT CLIE_OID_CLIE
      FROM MAE_CLIEN_BLOQU
      WHERE FEC_DESB >= TRUNC(fechaUltimoProceso);

   TYPE clie_actu_oid_type IS TABLE OF INT_REU_OID_CLIEN_ACTUA.OID_CLIE%TYPE;
   clie_actu_oid clie_actu_oid_type;
   i NUMBER;
--c1   rowLimit             NUMBER := 1000 ;
   rowLimit             NUMBER := 10000 ; --c1
   BEGIN
      -- Truncamos la tabla que contiene los clientes a enviar
      EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_OID_CLIEN_ACTUA';
       -- Abrimos el cursor usando un BULK COLLECT
    OPEN clie_actu_cur;
    LOOP
         FETCH clie_actu_cur BULK COLLECT INTO clie_actu_oid LIMIT rowLimit;
         EXIT WHEN clie_actu_oid.COUNT = 0;
         FORALL i in clie_actu_oid.FIRST..clie_actu_oid.LAST
   INSERT INTO INT_REU_OID_CLIEN_ACTUA(OID_CLIE, FEC_ULTI_ACTU)
   VALUES (clie_actu_oid(i), SYSDATE);
   IF MOD(i, rowLimit) = 0 THEN
   COMMIT;
   END IF;
    END LOOP;
    CLOSE clie_actu_cur;
    COMMIT;
   END INT_PR_REU_CARGA_CONSU_ACTUA;

   PROCEDURE INT_PR_REU_CARGA_CONSU(codigoPais IN VARCHAR2,
                                    codigoMarca IN VARCHAR2,
         codigoCanal IN VARCHAR2,
                                    codigoTipoCliente IN VARCHAR2,
                                    valorPorcentajeDcto IN VARCHAR2,
         montoMaximoDeuda IN NUMBER) IS
   oidPais SEG_PAIS.OID_PAIS%TYPE;
   oidCanal SEG_CANAL.OID_CANA%TYPE;

   -- Cursor para la obtencion de la cuenta corriente
   CURSOR clie_cuen_corr_cur(codigoPeriodo VARCHAR2,
                             codigoMarca VARCHAR2,
        codigoCanal VARCHAR2) IS
   SELECT
   OID_CLIE,
   '',
   GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALDO_VENCI(OID_CLIE,
                  GEN_PKG_GENER.GEN_FN_OBTIE_FECHA_VENCI(codigoPais,
                                             codigoMarca,
               codigoCanal,
               codigoPeriodo,
               GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(OID_CLIE, 'COD_REGI'),
               GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(OID_CLIE, 'COD_ZONA'),
               OID_CLIE)
            )
   -- GEN_PKG_GENER.GEN_FN_CLIEN_SALDO_DEUDA_TOTAL(OID_CLIE)
   FROM INT_REU_OID_CLIEN_ACTUA;
   TYPE clie_cuen_corr_type IS TABLE OF INT_REU_SALDO_CUENT_CORRI%ROWTYPE;
   clie_cuen_corr clie_cuen_corr_type;

   -- Cursor para la obtencion de los datos de comunicacion y geograficos
   CURSOR clie_comu_cur IS
   SELECT
   OID_CLIE,
   GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(OID_CLIE, 'TF'),
   GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(OID_CLIE, 'TM'),
   GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(OID_CLIE, 'TT'),
   GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(OID_CLIE, 'ML'),
   GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, OID_CLIE, 1),
   GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, OID_CLIE, 2),
   GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, OID_CLIE, 3),
   GEN_PKG_GENER.GEN_FN_CLIEN_LIDER(OID_CLIE, codigoTipoCliente)
   FROM INT_REU_OID_CLIEN_ACTUA;
   TYPE clie_comu_type IS TABLE OF INT_REU_CLIEN_COMUN%ROWTYPE;
   clie_comu clie_comu_type;

   -- Cursor para la obtencion de datos adicionales
   CURSOR clie_adic_cur IS
   SELECT
   OID_CLIE,
   GEN_PKG_GENER.GEN_FN_CLIEN_FECHA_EGRES(OID_CLIE),
   GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_INGRE(OID_CLIE),
   GEN_PKG_GENER.GEN_FN_CLIEN_PEDID_MONTO_MINIM(OID_CLIE, codigoTipoCliente),
   GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_CIERR(OID_CLIE)
   FROM INT_REU_OID_CLIEN_ACTUA;
   TYPE clie_adic_type IS TABLE OF INT_REU_CLIEN_ADICI%ROWTYPE;
   clie_adic clie_adic_type;

   -- Cursor para la obtencion de los porcentajes de dscto
   CURSOR valo_desc_cur IS
   SELECT
   OID_CLIE,
   GEN_PKG_INTER_ARCHI.GEN_FN_OBTIE_PORC_DCTO(OID_CLIE, valorPorcentajeDcto)
   FROM INT_REU_OID_CLIEN_ACTUA;
   TYPE valo_desc_type IS TABLE OF INT_REU_VALOR_DESCU%ROWTYPE;
   valo_desc valo_desc_type;

   -- Cursor para la obtencion de las consultoras que pasaron pedido
   -- en la ultima campa�a, la cual es pasada como parametro
   CURSOR ulti_camp_cur(ps_cod_peri VARCHAR2) IS
      SELECT
   INT_REU_OID_CLIEN_ACTUA.OID_CLIE,
   ps_cod_peri,
   GEN_PKG_GENER.GEN_FN_CLIEN_MONTO_ULTIM_PEDID(INT_REU_OID_CLIEN_ACTUA.OID_CLIE, ps_cod_peri) VAL_MNTO_PEDI,
   PEDIDOS.FEC_FACT FEC_ULTI_PEDI
   FROM INT_REU_OID_CLIEN_ACTUA, (
   SELECT
   PED_SOLIC_CABEC.CLIE_OID_CLIE,
   MAX(PED_SOLIC_CABEC.FEC_FACT) FEC_FACT
   FROM PED_SOLIC_CABEC,
        CRA_PERIO,
  SEG_PERIO_CORPO,
  PED_TIPO_SOLIC,
  PED_TIPO_SOLIC_PAIS
  WHERE PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI
    AND CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI
    AND PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS = PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS
    AND PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI = PED_TIPO_SOLIC.OID_TIPO_SOLI
    AND SEG_PERIO_CORPO.COD_PERI = ps_cod_peri
    AND FEC_FACT IS NOT NULL
    AND PED_TIPO_SOLIC.IND_ANUL <> 1
    AND PED_TIPO_SOLIC.IND_DEVO <> 1
    AND PED_SOLIC_CABEC.IND_OC = 1
 AND PED_TIPO_SOLIC.COD_TIPO_SOLI = 'SOC'
 GROUP BY PED_SOLIC_CABEC.CLIE_OID_CLIE) PEDIDOS
 WHERE INT_REU_OID_CLIEN_ACTUA.OID_CLIE = PEDIDOS.CLIE_OID_CLIE;
   TYPE ulti_camp_type IS TABLE OF INT_REU_ULTIM_CAMPA%ROWTYPE;

   ulti_camp ulti_camp_type;
   i        NUMBER := 0;
   rowLimit NUMBER := 1000 ;
   cod_peri_vige VARCHAR2(6);

   BEGIN
   -- Obtenemos el oid del pais
   SELECT OID_PAIS
   INTO oidPais
   FROM SEG_PAIS
   WHERE COD_PAIS = codigoPais;
   -- Nos aseguramos que la BD no tome en cuenta los indices en estado UNUSABLE
   EXECUTE IMMEDIATE 'ALTER SESSION SET skip_unusable_indexes=true';
   -- Deshabilitamos los indices
   EXECUTE IMMEDIATE 'ALTER INDEX REU_SACC_IDX UNUSABLE';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_CLCO_IDX UNUSABLE';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_VADE_IDX UNUSABLE';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_CLAD_IDX UNUSABLE';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_ULCA_IDX UNUSABLE';
   -- truncamos las tablas
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_SALDO_CUENT_CORRI';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_CLIEN_COMUN';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_CLIEN_ADICI';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_VALOR_DESCU';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_ULTIM_CAMPA';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_CONSU';

   -- Obtenemos el valor de la campa�a vigente
   oidCanal     := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(codigoCanal);
   cod_peri_vige := GEN_PKG_GENER.GEN_FN_DEVUE_PERIO_ACTUA( oidPais, oidCanal, NULL);

   -- Calculamos los saldos de los clientes
   -- Abrimos el cursor usando un BULK COLLECT
   OPEN clie_cuen_corr_cur(cod_peri_vige, codigoMarca, codigoCanal);
   LOOP
         FETCH clie_cuen_corr_cur BULK COLLECT INTO clie_cuen_corr LIMIT rowLimit;
         EXIT WHEN clie_cuen_corr.COUNT = 0;
         FORALL i in clie_cuen_corr.FIRST..clie_cuen_corr.LAST
   INSERT INTO INT_REU_SALDO_CUENT_CORRI
   VALUES clie_cuen_corr(i);
   IF MOD(i, rowLimit) = 0 THEN
   COMMIT;
   END IF;
   END LOOP;
   CLOSE clie_cuen_corr_cur;
   COMMIT;

   -- Obtenemos sus datos basicos
   -- Abrimos el cursor usando un BULK COLLECT
   OPEN clie_comu_cur;
   LOOP
         FETCH clie_comu_cur BULK COLLECT INTO clie_comu LIMIT rowLimit;
         EXIT WHEN clie_comu.COUNT = 0;
         FORALL i in clie_comu.FIRST..clie_comu.LAST
   INSERT INTO INT_REU_CLIEN_COMUN
   VALUES clie_comu(i);
   IF MOD(i, rowLimit) = 0 THEN
   COMMIT;
   END IF;
   END LOOP;
   CLOSE clie_comu_cur;
   COMMIT;

   -- Calculamos los datos adicionales
   OPEN clie_adic_cur;
   LOOP
         FETCH clie_adic_cur BULK COLLECT INTO clie_adic LIMIT rowLimit;
         EXIT WHEN clie_adic.COUNT = 0;
         FORALL i in clie_adic.FIRST..clie_adic.LAST
         INSERT INTO INT_REU_CLIEN_ADICI
         VALUES clie_adic(i);
         IF MOD(i, rowLimit) = 0 THEN
            COMMIT;
         END IF;
   END LOOP;
   CLOSE clie_adic_cur;

   COMMIT;
   -- Calculamos el porcentaje de descuento
   -- Abrimos el cursor usando un BULK COLLECT
   OPEN valo_desc_cur;
   LOOP
         FETCH valo_desc_cur BULK COLLECT INTO valo_desc LIMIT rowLimit;
         EXIT WHEN valo_desc.COUNT = 0;
         FORALL i in valo_desc.FIRST..valo_desc.LAST
   INSERT INTO INT_REU_VALOR_DESCU
   VALUES valo_desc(i);
   IF MOD(i, rowLimit) = 0 THEN
   COMMIT;
   END IF;
   END LOOP;
   CLOSE valo_desc_cur;
   COMMIT;

   -- Calculamos los datos de la ultima campa�a
   OPEN ulti_camp_cur(cod_peri_vige);
   LOOP
         FETCH ulti_camp_cur BULK COLLECT INTO ulti_camp LIMIT rowLimit;
         EXIT WHEN ulti_camp.COUNT = 0;
         FORALL i in ulti_camp.FIRST..ulti_camp.LAST
         INSERT INTO INT_REU_ULTIM_CAMPA
         VALUES ulti_camp(i);
         IF MOD(i, rowLimit) = 0 THEN
             COMMIT;
         END IF;
   END LOOP;
   CLOSE ulti_camp_cur;
   COMMIT;
   -- Reconstruimos los indices
   EXECUTE IMMEDIATE 'ALTER INDEX REU_SACC_IDX REBUILD';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_CLCO_IDX REBUILD';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_VADE_IDX REBUILD';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_CLAD_IDX REBUILD';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_ULCA_IDX REBUILD';

   -- Consolidamos toda la informacion en la tabla de novedades
   INT_PR_REU_CONSO_CONSU(codigoPais,
                          codigoMarca,
        codigoCanal,
        codigoTipoCliente,
        valorPorcentajeDcto,
        montoMaximoDeuda);
   COMMIT;
   END INT_PR_REU_CARGA_CONSU;
   PROCEDURE INT_PR_REU_CARGA_CONSU_HISTO(codigoPais IN VARCHAR2,
                                          codigoMarca IN VARCHAR2,
            codigoCanal IN VARCHAR2,
                                    codigoTipoCliente IN VARCHAR2,
                                          valorPorcentajeDcto IN VARCHAR2,
            montoMaximoDeuda IN NUMBER) IS
   BEGIN
   -- Nos aseguramos que la BD no tome en cuenta los indices en estado UNUSABLE
   EXECUTE IMMEDIATE 'ALTER SESSION SET skip_unusable_indexes=true';
   -- Deshabilitamos los indices
   EXECUTE IMMEDIATE 'ALTER INDEX REU_SACC_IDX UNUSABLE';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_CLCO_IDX UNUSABLE';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_VADE_IDX UNUSABLE';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_CLCA_IDX UNUSABLE';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_DATOS_SOLIC_IDX UNUSABLE';
   -- truncamos las tablas
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_SALDO_CUENT_CORRI';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_CLIEN_COMUN';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_VALOR_DESCU';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_DATOS_CAMPA';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_DATOS_SOLIC';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_CONSU';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_REU_CONSU_HISTO';
   -- Calculamos los saldos de los clientes
   INSERT INTO INT_REU_SALDO_CUENT_CORRI
   SELECT
   MAE_CLIEN.OID_CLIE,
   MAE_CLIEN.COD_CLIE,
   GEN_PKG_GENER.GEN_FN_CLIEN_SALDO_DEUDA_TOTAL(MAE_CLIEN.OID_CLIE)
   FROM MAE_CLIEN;
   COMMIT;
   -- Obtenemos sus datos basicos
   INSERT INTO INT_REU_CLIEN_COMUN
   SELECT
   MAE_CLIEN.OID_CLIE,
   GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'TF'),
   GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'TM'),
   GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'TT'),
   GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'ML'),
   GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(MAE_CLIEN.PAIS_OID_PAIS, MAE_CLIEN.OID_CLIE, 1),
   GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(MAE_CLIEN.PAIS_OID_PAIS, MAE_CLIEN.OID_CLIE, 2),
   GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(MAE_CLIEN.PAIS_OID_PAIS, MAE_CLIEN.OID_CLIE, 3),
   GEN_PKG_GENER.GEN_FN_CLIEN_LIDER(MAE_CLIEN.OID_CLIE, codigoTipoCliente)
   FROM MAE_CLIEN;
   COMMIT;
   -- Calculamos el porcentaje de descuento
   INSERT INTO INT_REU_VALOR_DESCU
   SELECT
   MAE_CLIEN.OID_CLIE,
   GEN_PKG_INTER_ARCHI.GEN_FN_OBTIE_PORC_DCTO(MAE_CLIEN.OID_CLIE, valorPorcentajeDcto)
   FROM MAE_CLIEN;
   COMMIT;
   COMMIT;
   -- Reconstruimos los indices
   EXECUTE IMMEDIATE 'ALTER INDEX REU_SACC_IDX REBUILD';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_CLCO_IDX REBUILD';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_VADE_IDX REBUILD';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_CLCA_IDX REBUILD';
   EXECUTE IMMEDIATE 'ALTER INDEX REU_DATOS_SOLIC_IDX REBUILD';

   -- Consolidamos toda la informacion en la tabla
   INT_PR_REU_CONSO_CONSU_HISTO(codigoPais,
                                codigoMarca,
        codigoCanal,
        codigoTipoCliente,
        valorPorcentajeDcto,
        montoMaximoDeuda);
   COMMIT;
   END INT_PR_REU_CARGA_CONSU_HISTO;

/****************************************************************************
Proyecto Optimizaci�n  c1

Descripcion       :   Carga en los datos del movimiento de cuenta y datos
                      adicionales del cliente y se cargan en la tabla
					  INT_REU_OID_CLIEN_ACTUA.

Fecha Modicicacion: 05/05/2011  09:20
Autor             : Jorge Angulo   JANGULO
***************************************************************************/

   PROCEDURE INT_PR_REU_CONSO_CONSU(codigoPais IN VARCHAR2,
                                    codigoMarca IN VARCHAR2,
                                    codigoCanal IN VARCHAR2,
                                    codigoTipoCliente IN VARCHAR2,
                                    valorPorcentajeDcto IN VARCHAR2,
                                    montoMaximoDeuda IN NUMBER) IS

   -- Definimos los caracteres a filtrar y reemplazar por blancos
   searchStr  VARCHAR2(100) := 'a"'',;|' || CHR(10) || CHR(13) || CHR(20);
   replaceStr VARCHAR2(100) := 'a        ';

   CURSOR consu_cur IS
       SELECT
       SEG_PAIS.COD_PAIS,
       MAE_CLIEN.COD_CLIE,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_APE1, searchStr, replaceStr)) VAL_APE1,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_APE2, searchStr, replaceStr)) VAL_APE2,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_NOM1, searchStr, replaceStr)) VAL_NOM1,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_NOM2, searchStr, replaceStr)) VAL_NOM2,
       MAE_CLIEN.COD_SEXO,
       TO_CHAR(MAE_CLIEN_DATOS_ADICI.FEC_NACI, 'YYYYMMDD') AS FEC_NACI,
       NVL(MAE_ESTAD_CIVIL.COD_ESTA_CIVI, '06') COD_ESTA_CIVI, -- 06 - Otros
       MAE_ESTAT_CLIEN.COD_ESTA_CLIE,
       MAE_CLIEN_IDENT.NUM_DOCU_IDEN,
       TRIM(TRANSLATE(SEG_TIPO_VIA.COD_TIPO_VIA, searchStr, replaceStr)) COD_TIPO_VIA,
       TRIM(TRANSLATE(MAE_CLIEN_DIREC.VAL_NOMB_VIA, searchStr, replaceStr)) VAL_NOMB_VIA,
       TRIM(TRANSLATE(MAE_CLIEN_DIREC.NUM_PPAL, searchStr, replaceStr)) NUM_PPAL,
       TRIM(TRANSLATE(MAE_CLIEN_DIREC.VAL_OBSE, searchStr, replaceStr)) VAL_OBSE,
       TRIM(TRANSLATE(MAE_CLIEN_DIREC.VAL_COD_POST, searchStr, replaceStr)) VAL_COD_POST,
       TRIM(TRANSLATE(INT_REU_CLIEN_COMUN.VAL_TELE_FIJO, searchStr, replaceStr)) VAL_TELE_FIJO,
       TRIM(TRANSLATE(INT_REU_CLIEN_COMUN.VAL_TELE_CELU, searchStr, replaceStr)) VAL_TELE_CELU,
       TRIM(TRANSLATE(INT_REU_CLIEN_COMUN.VAL_TELE_OFIC, searchStr, replaceStr)) VAL_TELE_OFIC,
       TRIM(TRANSLATE(INT_REU_CLIEN_COMUN.VAL_CORR_ELEC, searchStr, replaceStr)) VAL_CORR_ELEC,
       MAE_CLIEN.ind_PROL,       -----ZON_SUB_GEREN_VENTA.COD_SUBG_VENT,
       ZON_REGIO.COD_REGI,
       ZON_ZONA.COD_ZONA,
       ZON_SECCI.COD_SECC,
       NVL(ZON_TERRI.COD_TERR, 0) AS COD_TERR,
       (CASE ZON_TERRI.COD_NSE2
        WHEN NULL THEN NVL (ZON_TERRI.COD_NSE1, ' ')
        ELSE NVL (ZON_TERRI.COD_NSE2, ' ')
        END
       ) AS EST_SOCI_ECON,
       TRIM (SUBSTR (MAE_CLIEN_DIREC.COD_UNID_GEOG, 1, 6)) AS ORDE_1,
       TRIM (SUBSTR (MAE_CLIEN_DIREC.COD_UNID_GEOG, 7, 6)) AS ORDE_2,
       INT_REU_CLIEN_COMUN.NOM_DEPA,
       INT_REU_CLIEN_COMUN.NOM_PROV,
       INT_REU_CLIEN_COMUN.NOM_DIST,
       INT_REU_SALDO_CUENT_CORRI.SAL_CUEN_CORR,
       INT_PKG_REUTI.INT_FN_REU_INDIC_PEDID(MAE_CLIEN.OID_CLIE,
                                            MAE_ESTAT_CLIEN.COD_ESTA_CLIE) IND_PEDI, -- CHR 22/01/2009
       (CASE MAE_ESTAT_CLIEN.COD_ESTA_CLIE
        WHEN '01' THEN '1'
        ELSE
            INT_REU_CLIEN_COMUN.COD_TIPO_CONS
        END
       ) AS COD_TIPO_CONS,
       INT_PKG_REUTI.INT_FN_REU_EVALU_ESTAT_CONSU(MAE_CLIEN.OID_CLIE,
                                                  MAE_ESTAT_CLIEN.COD_ESTA_CLIE,
                                                  ZON_ZONA.COD_ZONA,
                                                  montoMaximoDeuda) AS EST_CONS,
       MAE_TIPO_DOCUM.COD_TIPO_DOCU,
       (CASE MAE_TIPO_DOCUM.COD_TIPO_DOCU
        WHEN codigoTipoCliente THEN NVL (MAE_CLIEN_IDENT.NUM_DOCU_IDEN, ' ')
        ELSE ' '
        END
       ) AS NUM_RUC,
       (CASE MAE_TIPO_DOCUM.COD_TIPO_DOCU
        WHEN '01' THEN '0'
        ELSE '1'
        END
       ) AS FLA_EMIT_FACT,
       INT_REU_VALOR_DESCU.VAL_PORC_DESC,
       TO_CHAR(INT_REU_CLIEN_ADICI.FEC_EGRE, 'YYYYMMDD') FEC_EGRE,
       INT_REU_CLIEN_ADICI.CAM_INGR,
       INT_REU_CLIEN_ADICI.VAL_MNTO_MINI,
       0 IND_CLIE_TRAN,
       MAE_CLIEN.OID_CLIE,
       MAE_CLIEN.FEC_CREA,
       MAE_CLIEN_DATOS_ADICI.IND_ACTI,
       INT_REU_CLIEN_ADICI.COD_PERI_CIER,
       (CASE WHEN EXISTS (
            SELECT NULL
            FROM MAE_CLIEN_TIPO_SUBTI,
                 MAE_CLIEN_CLASI,
                 MAE_TIPO_CLIEN,
                 MAE_SUBTI_CLIEN,
                 MAE_TIPO_CLASI_CLIEN,
                 MAE_CLASI
            WHERE MAE_CLIEN.OID_CLIE = MAE_CLIEN_TIPO_SUBTI.CLIE_OID_CLIE
              AND MAE_CLIEN_TIPO_SUBTI.TICL_OID_TIPO_CLIE = MAE_TIPO_CLIEN.OID_TIPO_CLIE
              AND MAE_CLIEN_TIPO_SUBTI.SBTI_OID_SUBT_CLIE = MAE_SUBTI_CLIEN.OID_SUBT_CLIE
              AND MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT = MAE_CLIEN_CLASI.CTSU_OID_CLIE_TIPO_SUBT(+)
              AND MAE_CLIEN_CLASI.TCCL_OID_TIPO_CLASI = MAE_TIPO_CLASI_CLIEN.OID_TIPO_CLAS(+)
              AND MAE_CLIEN_CLASI.CLAS_OID_CLAS = MAE_CLASI.OID_CLAS(+)
              AND MAE_TIPO_CLASI_CLIEN.COD_TIPO_CLAS = '41'
              AND MAE_CLASI.COD_CLAS = '01')
        THEN 1
        ELSE 0
        END
       ) AS IND_MARC_LBEL
       FROM
       MAE_CLIEN,
       SEG_PAIS,
       MAE_CLIEN_DATOS_ADICI,
       MAE_CLIEN_IDENT,
       MAE_TIPO_DOCUM,
       MAE_ESTAD_CIVIL,
       MAE_TIPO_CLIEN,
       MAE_CLIEN_TIPO_SUBTI,
       MAE_CLIEN_DIREC,
       SEG_TIPO_VIA,
       MAE_CLIEN_UNIDA_ADMIN,
       ZON_SUB_GEREN_VENTA,
       ZON_REGIO,
       ZON_ZONA,
       ZON_SECCI,
       ZON_TERRI_ADMIN,
       ZON_TERRI,
       MAE_ESTAT_CLIEN,
       INT_REU_SALDO_CUENT_CORRI,
       INT_REU_CLIEN_COMUN,
       INT_REU_VALOR_DESCU,
       INT_REU_CLIEN_ADICI
       WHERE ((SEG_PAIS.OID_PAIS = MAE_CLIEN.PAIS_OID_PAIS)
        AND (SEG_PAIS.COD_PAIS = codigoPais)
        AND (MAE_ESTAD_CIVIL.OID_ESTA_CIVI(+) = MAE_CLIEN_DATOS_ADICI.ESCV_OID_ESTA_CIVI)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_TIPO_SUBTI.CLIE_OID_CLIE)
        AND (MAE_TIPO_CLIEN.OID_TIPO_CLIE = MAE_CLIEN_TIPO_SUBTI.TICL_OID_TIPO_CLIE)
        AND (MAE_TIPO_CLIEN.OID_TIPO_CLIE = MAE_CLIEN_TIPO_SUBTI.TICL_OID_TIPO_CLIE)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE)
        AND (MAE_ESTAT_CLIEN.OID_ESTA_CLIE = MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_IDENT.CLIE_OID_CLIE)
        AND (MAE_TIPO_DOCUM.OID_TIPO_DOCU = MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU)
        AND (MAE_CLIEN_IDENT.VAL_IDEN_DOCU_PRIN = 1)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_DIREC.CLIE_OID_CLIE)
        AND (SEG_TIPO_VIA.OID_TIPO_VIA(+) = MAE_CLIEN_DIREC.TIVI_OID_TIPO_VIA)
        AND (MAE_CLIEN_DIREC.IND_DIRE_PPAL =  1)
        AND (MAE_CLIEN_DIREC.IND_ELIM =  0)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE)
        AND (ZON_SUB_GEREN_VENTA.OID_SUBG_VENT = ZON_REGIO.ZSGV_OID_SUBG_VENT)
        AND (ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI)
        AND (ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA)
        AND (ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC)
        AND (MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI = ZON_TERRI_ADMIN.OID_TERR_ADMI)
        AND (ZON_TERRI.OID_TERR = ZON_TERRI_ADMIN.TERR_OID_TERR)
        AND (MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = 1)
        AND (MAE_TIPO_CLIEN.COD_TIPO_CLIE = NVL(codigoTipoCliente, MAE_TIPO_CLIEN.COD_TIPO_CLIE))
        AND (MAE_CLIEN.OID_CLIE = INT_REU_SALDO_CUENT_CORRI.OID_CLIE)
        AND (MAE_CLIEN.OID_CLIE = INT_REU_CLIEN_COMUN.OID_CLIE)
        AND (MAE_CLIEN.OID_CLIE = INT_REU_VALOR_DESCU.OID_CLIE)
        AND (MAE_CLIEN.OID_CLIE = INT_REU_CLIEN_ADICI.OID_CLIE)
        );
   TYPE int_reu_consu_tab_type IS TABLE OF INT_REU_CONSU%ROWTYPE;
   int_reu_consu_tab int_reu_consu_tab_type;

   CURSOR upd_camp_ulti_pedi_cur IS
   SELECT
   INT_REU_ULTIM_CAMPA.OID_CLIE,
   DECODE(INT_REU_CLIEN_ADICI.CAM_INGR, NULL, INT_REU_ULTIM_CAMPA.CAM_ULTI_PEDI,INT_REU_CLIEN_ADICI.CAM_INGR) CAM_INGR,
   INT_REU_ULTIM_CAMPA.CAM_ULTI_PEDI,
   INT_REU_ULTIM_CAMPA.VAL_MNTO_PEDI
   FROM INT_REU_ULTIM_CAMPA, INT_REU_CLIEN_ADICI
   WHERE INT_REU_ULTIM_CAMPA.OID_CLIE = INT_REU_CLIEN_ADICI.OID_CLIE;
   TYPE oid_clie_type IS TABLE OF INT_REU_ULTIM_CAMPA.OID_CLIE%TYPE;
   TYPE cam_ingr_type IS TABLE OF INT_REU_ULTIM_CAMPA.CAM_ULTI_PEDI%TYPE;
   TYPE cam_ulti_pedi_type IS TABLE OF INT_REU_ULTIM_CAMPA.CAM_ULTI_PEDI%TYPE;
   TYPE val_mnto_pedi_type IS TABLE OF INT_REU_ULTIM_CAMPA.VAL_MNTO_PEDI%TYPE;
   v_oid_clie oid_clie_type;
   v_cam_ingr cam_ingr_type;
   v_cam_ulti_pedi cam_ulti_pedi_type;
   v_val_mnto_pedi val_mnto_pedi_type;
--c1   rowLimit         NUMBER := 1000 ;
   rowLimit         NUMBER := 10000 ;  --c1
   v_row_count      NUMBER := 0;
   i                BINARY_INTEGER := 0;
   cod_peri_vige VARCHAR2(6);
   BEGIN
       -- Abrimos el cursor usando un BULK COLLECT
       OPEN consu_cur;
       LOOP
       FETCH consu_cur BULK COLLECT INTO int_reu_consu_tab LIMIT rowLimit;
       EXIT WHEN int_reu_consu_tab.COUNT = 0;
           FORALL i in int_reu_consu_tab.FIRST..int_reu_consu_tab.LAST
           INSERT INTO int_reu_consu VALUES int_reu_consu_tab(i);
       END LOOP;
       CLOSE consu_cur;
       COMMIT;
    -- Insertamos y/o actualizamos el historico segun sea el caso
    MERGE INTO INT_REU_CONSU_HISTO
    USING INT_REU_CONSU ON (INT_REU_CONSU_HISTO.OID_CLIE = INT_REU_CONSU.OID_CLIE)
    WHEN MATCHED THEN
    UPDATE SET
       INT_REU_CONSU_HISTO.VAL_APE1      = INT_REU_CONSU.VAL_APE1,
       INT_REU_CONSU_HISTO.VAL_APE2      = INT_REU_CONSU.VAL_APE2,
       INT_REU_CONSU_HISTO.VAL_NOM1      = INT_REU_CONSU.VAL_NOM1,
       INT_REU_CONSU_HISTO.VAL_NOM2      = INT_REU_CONSU.VAL_NOM2,
       INT_REU_CONSU_HISTO.COD_SEXO      = INT_REU_CONSU.COD_SEXO,
       INT_REU_CONSU_HISTO.FEC_NACI      = INT_REU_CONSU.FEC_NACI,
       INT_REU_CONSU_HISTO.COD_ESTA_CIVI = INT_REU_CONSU.COD_ESTA_CIVI,
       INT_REU_CONSU_HISTO.COD_ESTA_CLIE = INT_REU_CONSU.COD_ESTA_CLIE,
       INT_REU_CONSU_HISTO.NUM_DOCU_IDEN = INT_REU_CONSU.NUM_DOCU_IDEN,
       INT_REU_CONSU_HISTO.COD_TIPO_VIA  = INT_REU_CONSU.COD_TIPO_VIA,
       INT_REU_CONSU_HISTO.VAL_NOMB_VIA  = INT_REU_CONSU.VAL_NOMB_VIA,
       INT_REU_CONSU_HISTO.NUM_PPAL      = INT_REU_CONSU.NUM_PPAL,
       INT_REU_CONSU_HISTO.VAL_OBSE      = INT_REU_CONSU.VAL_OBSE,
       INT_REU_CONSU_HISTO.VAL_COD_POST  = INT_REU_CONSU.VAL_COD_POST,
       INT_REU_CONSU_HISTO.VAL_TELE_FIJO = INT_REU_CONSU.VAL_TELE_FIJO,
       INT_REU_CONSU_HISTO.VAL_TELE_CELU = INT_REU_CONSU.VAL_TELE_CELU,
       INT_REU_CONSU_HISTO.VAL_TELE_OFIC = INT_REU_CONSU.VAL_TELE_OFIC,
       INT_REU_CONSU_HISTO.VAL_CORR_ELEC = INT_REU_CONSU.VAL_CORR_ELEC,
       INT_REU_CONSU_HISTO.COD_SUBG_VENT = INT_REU_CONSU.COD_SUBG_VENT,
       INT_REU_CONSU_HISTO.COD_REGI      = INT_REU_CONSU.COD_REGI,
       INT_REU_CONSU_HISTO.COD_ZONA      = INT_REU_CONSU.COD_ZONA,
       INT_REU_CONSU_HISTO.COD_SECC      = INT_REU_CONSU.COD_SECC,
       INT_REU_CONSU_HISTO.COD_TERR      = INT_REU_CONSU.COD_TERR,
       INT_REU_CONSU_HISTO.EST_SOCI_ECON = INT_REU_CONSU.EST_SOCI_ECON,
       INT_REU_CONSU_HISTO.ORDE_1        = INT_REU_CONSU.ORDE_1,
       INT_REU_CONSU_HISTO.ORDE_2        = INT_REU_CONSU.ORDE_2,
       INT_REU_CONSU_HISTO.NOM_DEPA      = INT_REU_CONSU.NOM_DEPA,
       INT_REU_CONSU_HISTO.NOM_PROV      = INT_REU_CONSU.NOM_PROV,
       INT_REU_CONSU_HISTO.NOM_DIST      = INT_REU_CONSU.NOM_DIST,
       INT_REU_CONSU_HISTO.SAL_CUEN_CORR = INT_REU_CONSU.SAL_CUEN_CORR,
       INT_REU_CONSU_HISTO.IND_PEDI      = INT_REU_CONSU.IND_PEDI,
       INT_REU_CONSU_HISTO.COD_TIPO_CONS = INT_REU_CONSU.COD_TIPO_CONS,
       INT_REU_CONSU_HISTO.EST_CONS      = INT_REU_CONSU.EST_CONS,
       INT_REU_CONSU_HISTO.COD_TIPO_DOCU = INT_REU_CONSU.COD_TIPO_DOCU,
       INT_REU_CONSU_HISTO.NUM_RUC       = INT_REU_CONSU.NUM_RUC,
       INT_REU_CONSU_HISTO.FLA_EMIT_FACT = INT_REU_CONSU.FLA_EMIT_FACT,
       INT_REU_CONSU_HISTO.VAL_PORC_DESC = INT_REU_CONSU.VAL_PORC_DESC,
       INT_REU_CONSU_HISTO.FEC_EGRE      = INT_REU_CONSU.FEC_EGRE,
       INT_REU_CONSU_HISTO.CAM_INGR      = INT_REU_CONSU.CAM_INGR,
       INT_REU_CONSU_HISTO.VAL_MNTO_MINI = INT_REU_CONSU.VAL_MNTO_MINI,
       INT_REU_CONSU_HISTO.IND_CLIE_TRAN = INT_REU_CONSU.IND_CLIE_TRAN,
       INT_REU_CONSU_HISTO.IND_ACTI      = INT_REU_CONSU.IND_ACTI,
       INT_REU_CONSU_HISTO.COD_PERI_CIER = INT_REU_CONSU.COD_PERI_CIER,
       INT_REU_CONSU_HISTO.IND_MARC_LBEL = INT_REU_CONSU.IND_MARC_LBEL,
    INT_REU_CONSU_HISTO.FEC_ULTI_ACTU = SYSDATE
    WHEN NOT MATCHED THEN
    INSERT (
       INT_REU_CONSU_HISTO.COD_PAIS,
       INT_REU_CONSU_HISTO.COD_CLIE,
       INT_REU_CONSU_HISTO.VAL_APE1,
       INT_REU_CONSU_HISTO.VAL_APE2,
       INT_REU_CONSU_HISTO.VAL_NOM1,
       INT_REU_CONSU_HISTO.VAL_NOM2,
       INT_REU_CONSU_HISTO.COD_SEXO,
       INT_REU_CONSU_HISTO.FEC_NACI,
       INT_REU_CONSU_HISTO.COD_ESTA_CIVI,
       INT_REU_CONSU_HISTO.COD_ESTA_CLIE,
       INT_REU_CONSU_HISTO.NUM_DOCU_IDEN,
       INT_REU_CONSU_HISTO.COD_TIPO_VIA,
       INT_REU_CONSU_HISTO.VAL_NOMB_VIA,
       INT_REU_CONSU_HISTO.NUM_PPAL,
       INT_REU_CONSU_HISTO.VAL_OBSE,
       INT_REU_CONSU_HISTO.VAL_COD_POST,
       INT_REU_CONSU_HISTO.VAL_TELE_FIJO,
       INT_REU_CONSU_HISTO.VAL_TELE_CELU,
       INT_REU_CONSU_HISTO.VAL_TELE_OFIC,
       INT_REU_CONSU_HISTO.VAL_CORR_ELEC,
       INT_REU_CONSU_HISTO.COD_SUBG_VENT,
       INT_REU_CONSU_HISTO.COD_REGI,
       INT_REU_CONSU_HISTO.COD_ZONA,
       INT_REU_CONSU_HISTO.COD_SECC,
       INT_REU_CONSU_HISTO.COD_TERR,
       INT_REU_CONSU_HISTO.EST_SOCI_ECON,
       INT_REU_CONSU_HISTO.ORDE_1,
       INT_REU_CONSU_HISTO.ORDE_2,
       INT_REU_CONSU_HISTO.NOM_DEPA,
       INT_REU_CONSU_HISTO.NOM_PROV,
       INT_REU_CONSU_HISTO.NOM_DIST,
       INT_REU_CONSU_HISTO.SAL_CUEN_CORR,
       INT_REU_CONSU_HISTO.IND_PEDI,
       INT_REU_CONSU_HISTO.COD_TIPO_CONS,
       INT_REU_CONSU_HISTO.EST_CONS,
       INT_REU_CONSU_HISTO.COD_TIPO_DOCU,
       INT_REU_CONSU_HISTO.NUM_RUC,
       INT_REU_CONSU_HISTO.FLA_EMIT_FACT,
       INT_REU_CONSU_HISTO.VAL_PORC_DESC,
       INT_REU_CONSU_HISTO.FEC_EGRE,
       INT_REU_CONSU_HISTO.CAM_INGR,
       INT_REU_CONSU_HISTO.VAL_MNTO_MINI,
       INT_REU_CONSU_HISTO.IND_CLIE_TRAN,
       INT_REU_CONSU_HISTO.OID_CLIE,
       INT_REU_CONSU_HISTO.FEC_CREA,
       INT_REU_CONSU_HISTO.IND_ACTI,
       INT_REU_CONSU_HISTO.COD_PERI_CIER,
       INT_REU_CONSU_HISTO.IND_MARC_LBEL,
    INT_REU_CONSU_HISTO.FEC_ULTI_ACTU)
    VALUES (
       INT_REU_CONSU.COD_PAIS,
       INT_REU_CONSU.COD_CLIE,
       INT_REU_CONSU.VAL_APE1,
       INT_REU_CONSU.VAL_APE2,
       INT_REU_CONSU.VAL_NOM1,
       INT_REU_CONSU.VAL_NOM2,
       INT_REU_CONSU.COD_SEXO,
       INT_REU_CONSU.FEC_NACI,
       INT_REU_CONSU.COD_ESTA_CIVI,
       INT_REU_CONSU.COD_ESTA_CLIE,
       INT_REU_CONSU.NUM_DOCU_IDEN,
       INT_REU_CONSU.COD_TIPO_VIA,
       INT_REU_CONSU.VAL_NOMB_VIA,
       INT_REU_CONSU.NUM_PPAL,
       INT_REU_CONSU.VAL_OBSE,
       INT_REU_CONSU.VAL_COD_POST,
       INT_REU_CONSU.VAL_TELE_FIJO,
       INT_REU_CONSU.VAL_TELE_CELU,
       INT_REU_CONSU.VAL_TELE_OFIC,
       INT_REU_CONSU.VAL_CORR_ELEC,
       INT_REU_CONSU.COD_SUBG_VENT,
       INT_REU_CONSU.COD_REGI,
       INT_REU_CONSU.COD_ZONA,
       INT_REU_CONSU.COD_SECC,
       INT_REU_CONSU.COD_TERR,
       INT_REU_CONSU.EST_SOCI_ECON,
       INT_REU_CONSU.ORDE_1,
       INT_REU_CONSU.ORDE_2,
       INT_REU_CONSU.NOM_DEPA,
       INT_REU_CONSU.NOM_PROV,
       INT_REU_CONSU.NOM_DIST,
       INT_REU_CONSU.SAL_CUEN_CORR,
       INT_REU_CONSU.IND_PEDI,
       INT_REU_CONSU.COD_TIPO_CONS,
       INT_REU_CONSU.EST_CONS,
       INT_REU_CONSU.COD_TIPO_DOCU,
       INT_REU_CONSU.NUM_RUC,
       INT_REU_CONSU.FLA_EMIT_FACT,
       INT_REU_CONSU.VAL_PORC_DESC,
       INT_REU_CONSU.FEC_EGRE,
       INT_REU_CONSU.CAM_INGR,
       INT_REU_CONSU.VAL_MNTO_MINI,
       INT_REU_CONSU.IND_CLIE_TRAN,
       INT_REU_CONSU.OID_CLIE,
       INT_REU_CONSU.FEC_CREA,
       INT_REU_CONSU.IND_ACTI,
       INT_REU_CONSU.COD_PERI_CIER,
       INT_REU_CONSU.IND_MARC_LBEL,
       SYSDATE);
       COMMIT;
    -- Actualizamos la campa?a de ultimo pedido
    OPEN upd_camp_ulti_pedi_cur;
    LOOP
    FETCH upd_camp_ulti_pedi_cur BULK COLLECT INTO v_oid_clie,
                                                      v_cam_ingr,
                                                      v_cam_ulti_pedi,
                                                      v_val_mnto_pedi
               LIMIT rowLimit;
    EXIT WHEN v_row_count = upd_camp_ulti_pedi_cur%ROWCOUNT;
           v_row_count := upd_camp_ulti_pedi_cur%ROWCOUNT;
--c1     FOR i IN v_oid_clie.FIRST .. v_oid_clie.LAST LOOP
     FORALL i IN v_oid_clie.FIRST .. v_oid_clie.LAST  --c1
         UPDATE INT_REU_CONSU_HISTO SET
         CAM_INGR = v_cam_ingr(i),
         CAM_ULTI_PEDI = v_cam_ulti_pedi(i),
         VAL_MNTO_PEDI = v_val_mnto_pedi(i),
         IND_CLIE_TRAN = 0,
         FEC_ULTI_ACTU = SYSDATE
         WHERE OID_CLIE = v_oid_clie(i);
--c1     END LOOP;
    END LOOP;
       CLOSE upd_camp_ulti_pedi_cur;
 COMMIT;

 -- Actualizamos el status de consultora de Calypso para las egresadas
 UPDATE INT_REU_CONSU_HISTO A
 SET A.EST_CONS = '01', -- Inactivo
 A.IND_CLIE_TRAN = 0
 WHERE EXISTS (
 SELECT B.COD_PAIS, B.COD_CLIE
 FROM INT_REU_CONSU_HISTO B
 WHERE B.COD_ESTA_CLIE = '05' -- Egresada
 AND B.EST_CONS = '00' -- Activa
 AND B.COD_PERI_CIER IS NOT NULL
 AND B.CAM_ULTI_PEDI < GEN_FN_CALCU_PERIO(B.COD_PERI_CIER, -1) -- Diferencia entre ultima campa�a activa (cierre + 1) y la vigente es mayor a 2
 AND B.COD_PAIS = A.COD_PAIS
 AND B.COD_CLIE = A.COD_CLIE);

END INT_PR_REU_CONSO_CONSU;

PROCEDURE INT_PR_REU_CONSO_CONSU_HISTO(codigoPais IN VARCHAR2,
                                       codigoMarca IN VARCHAR2,
                                       codigoCanal IN VARCHAR2,
                                       codigoTipoCliente IN VARCHAR2,
                                       valorPorcentajeDcto IN VARCHAR2,
                                       montoMaximoDeuda IN NUMBER) IS

   -- Definimos los caracteres a filtrar y reemplazar por blancos
   searchStr  VARCHAR2(100) := 'a"'',;|' || CHR(10) || CHR(13) || CHR(20);
   replaceStr VARCHAR2(100) := 'a        ';

   CURSOR consu_cur IS
       SELECT
       SEG_PAIS.COD_PAIS,
       MAE_CLIEN.COD_CLIE,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_APE1, searchStr, replaceStr)) VAL_APE1,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_APE2, searchStr, replaceStr)) VAL_APE2,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_NOM1, searchStr, replaceStr)) VAL_NOM1,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_NOM2, searchStr, replaceStr)) VAL_NOM2,
       MAE_CLIEN.COD_SEXO,
       TO_CHAR(MAE_CLIEN_DATOS_ADICI.FEC_NACI, 'YYYYMMDD') AS FEC_NACI,
       NVL(MAE_ESTAD_CIVIL.COD_ESTA_CIVI, '06') COD_ESTA_CIVI, -- 06 - Otros
       MAE_CLIEN_IDENT.NUM_DOCU_IDEN,
       TRIM(TRANSLATE(SEG_TIPO_VIA.COD_TIPO_VIA, searchStr, replaceStr)) COD_TIPO_VIA,
       TRIM(TRANSLATE(MAE_CLIEN_DIREC.VAL_NOMB_VIA, searchStr, replaceStr)) VAL_NOMB_VIA,
       TRIM(TRANSLATE(MAE_CLIEN_DIREC.NUM_PPAL, searchStr, replaceStr)) NUM_PPAL,
       TRIM(TRANSLATE(MAE_CLIEN_DIREC.VAL_OBSE, searchStr, replaceStr)) VAL_OBSE,
       TRIM(TRANSLATE(MAE_CLIEN_DIREC.VAL_COD_POST, searchStr, replaceStr)) VAL_COD_POST,
       TRIM(TRANSLATE(INT_REU_CLIEN_COMUN.VAL_TELE_FIJO, searchStr, replaceStr)) VAL_TELE_FIJO,
       TRIM(TRANSLATE(INT_REU_CLIEN_COMUN.VAL_TELE_CELU, searchStr, replaceStr)) VAL_TELE_CELU,
       TRIM(TRANSLATE(INT_REU_CLIEN_COMUN.VAL_TELE_OFIC, searchStr, replaceStr)) VAL_TELE_OFIC,
       TRIM(TRANSLATE(INT_REU_CLIEN_COMUN.VAL_CORR_ELEC, searchStr, replaceStr)) VAL_CORR_ELEC,
       ZON_SUB_GEREN_VENTA.COD_SUBG_VENT,
       ZON_REGIO.COD_REGI,
       ZON_ZONA.COD_ZONA,
       ZON_SECCI.COD_SECC,
       NVL(ZON_TERRI.COD_TERR, 0) AS COD_TERR,
       (CASE ZON_TERRI.COD_NSE2
        WHEN NULL THEN NVL (ZON_TERRI.COD_NSE1, ' ')
        ELSE NVL (ZON_TERRI.COD_NSE2, ' ')
        END
       ) AS EST_SOCI_ECON,
       TRIM (SUBSTR (MAE_CLIEN_DIREC.COD_UNID_GEOG, 1, 6)) AS ORDE_1,
       TRIM (SUBSTR (MAE_CLIEN_DIREC.COD_UNID_GEOG, 7, 6)) AS ORDE_2,
       INT_REU_CLIEN_COMUN.NOM_DEPA,
       INT_REU_CLIEN_COMUN.NOM_PROV,
       INT_REU_CLIEN_COMUN.NOM_DIST,
       INT_REU_SALDO_CUENT_CORRI.SAL_CUEN_CORR,
       INT_PKG_REUTI.INT_FN_REU_INDIC_PEDID(MAE_CLIEN.OID_CLIE,
                                            MAE_ESTAT_CLIEN.COD_ESTA_CLIE) IND_PEDI, -- CHR 22/01/2009
       (CASE MAE_ESTAT_CLIEN.COD_ESTA_CLIE
        WHEN '01' THEN '1'
        ELSE
            INT_REU_CLIEN_COMUN.COD_TIPO_CONS
        END
       ) AS COD_TIPO_CONS,
       INT_PKG_REUTI.INT_FN_REU_EVALU_ESTAT_CONSU(MAE_CLIEN.OID_CLIE,
                                                  MAE_ESTAT_CLIEN.COD_ESTA_CLIE,
                                                  ZON_ZONA.COD_ZONA,
                                                  montoMaximoDeuda) AS EST_CONS,
       MAE_TIPO_DOCUM.COD_TIPO_DOCU,
       (CASE MAE_TIPO_DOCUM.COD_TIPO_DOCU
        WHEN codigoTipoCliente THEN NVL (MAE_CLIEN_IDENT.NUM_DOCU_IDEN, ' ')
        ELSE ' '
        END
       ) AS NUM_RUC,
       (CASE MAE_TIPO_DOCUM.COD_TIPO_DOCU
        WHEN '01' THEN '0'
        ELSE '1'
        END
       ) AS FLA_EMIT_FACT,
       INT_REU_VALOR_DESCU.VAL_PORC_DESC,
       TO_CHAR(INT_REU_CLIEN_ADICI.FEC_EGRE, 'YYYYMMDD') FEC_EGRE,
       INT_REU_CLIEN_ADICI.CAM_INGR,
       INT_REU_CLIEN_ADICI.VAL_MNTO_MINI,
       0 IND_CLIE_TRAN
       FROM
       MAE_CLIEN,
       SEG_PAIS,
       MAE_CLIEN_DATOS_ADICI,
       MAE_CLIEN_IDENT,
       MAE_TIPO_DOCUM,
       MAE_ESTAD_CIVIL,
       MAE_TIPO_CLIEN,
       MAE_CLIEN_TIPO_SUBTI,
       MAE_CLIEN_DIREC,
       SEG_TIPO_VIA,
       MAE_CLIEN_UNIDA_ADMIN,
       ZON_SUB_GEREN_VENTA,
       ZON_REGIO,
       ZON_ZONA,
       ZON_SECCI,
       ZON_TERRI_ADMIN,
       ZON_TERRI,
       MAE_ESTAT_CLIEN,
       INT_REU_SALDO_CUENT_CORRI,
       INT_REU_CLIEN_COMUN,
       INT_REU_VALOR_DESCU,
       INT_REU_CLIEN_ADICI
       WHERE ((SEG_PAIS.OID_PAIS = MAE_CLIEN.PAIS_OID_PAIS)
        AND (SEG_PAIS.COD_PAIS = codigoPais)
        AND (MAE_ESTAD_CIVIL.OID_ESTA_CIVI(+) = MAE_CLIEN_DATOS_ADICI.ESCV_OID_ESTA_CIVI)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_TIPO_SUBTI.CLIE_OID_CLIE)
        AND (MAE_TIPO_CLIEN.OID_TIPO_CLIE = MAE_CLIEN_TIPO_SUBTI.TICL_OID_TIPO_CLIE)
        AND (MAE_TIPO_CLIEN.OID_TIPO_CLIE = MAE_CLIEN_TIPO_SUBTI.TICL_OID_TIPO_CLIE)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE)
        AND (MAE_ESTAT_CLIEN.OID_ESTA_CLIE = MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_IDENT.CLIE_OID_CLIE)
        AND (MAE_TIPO_DOCUM.OID_TIPO_DOCU = MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU)
        AND (MAE_CLIEN_IDENT.VAL_IDEN_DOCU_PRIN = 1)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_DIREC.CLIE_OID_CLIE)
        AND (SEG_TIPO_VIA.OID_TIPO_VIA(+) = MAE_CLIEN_DIREC.TIVI_OID_TIPO_VIA)
        AND (MAE_CLIEN_DIREC.IND_DIRE_PPAL =  1)
        AND (MAE_CLIEN_DIREC.IND_ELIM =  0)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE)
        AND (ZON_SUB_GEREN_VENTA.OID_SUBG_VENT = ZON_REGIO.ZSGV_OID_SUBG_VENT)
        AND (ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI)
        AND (ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA)
        AND (ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC)
        AND (MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI = ZON_TERRI_ADMIN.OID_TERR_ADMI)
        AND (ZON_TERRI.OID_TERR = ZON_TERRI_ADMIN.TERR_OID_TERR)
        AND (MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = 1)
        AND (MAE_TIPO_CLIEN.COD_TIPO_CLIE = NVL(codigoTipoCliente, MAE_TIPO_CLIEN.COD_TIPO_CLIE))
        AND (MAE_CLIEN.OID_CLIE = INT_REU_SALDO_CUENT_CORRI.OID_CLIE)
        AND (MAE_CLIEN.OID_CLIE = INT_REU_CLIEN_COMUN.OID_CLIE)
        AND (MAE_CLIEN.OID_CLIE = INT_REU_VALOR_DESCU.OID_CLIE)
        AND (MAE_CLIEN.OID_CLIE = INT_REU_CLIEN_ADICI.OID_CLIE)
        );
   TYPE int_reu_consu_tab_type IS TABLE OF INT_REU_CONSU%ROWTYPE;
   int_reu_consu_tab int_reu_consu_tab_type;
   rowLimit             NUMBER := 1000 ;
   BEGIN
       -- Abrimos el cursor usando un BULK COLLECT
    rowLimit := 0;
       /*OPEN consu_cur;
    LOOP
         FETCH consu_cur BULK COLLECT INTO int_reu_consu_tab LIMIT rowLimit;
   EXIT WHEN int_reu_consu_tab.COUNT = 0;
   FORALL i in int_reu_consu_tab.FIRST..int_reu_consu_tab.LAST
            INSERT INTO INT_REU_CONSU_HISTO VALUES int_reu_consu_tab(i);
    END LOOP;
    CLOSE consu_cur;
       COMMIT;    */
   END INT_PR_REU_CONSO_CONSU_HISTO;


   PROCEDURE INT_PR_REU_ARCHI_CONSU(pscodigoPais IN VARCHAR2,
                                    psCodigosistema IN VARCHAR2,
                                    psCodigoInterfaz IN VARCHAR2,
                                    psnombreArchivo IN VARCHAR2) IS
   CURSOR c_interfaz IS
   SELECT COD_PAIS,
          COD_CLIE,
          VAL_APE1,
          VAL_APE2,
          VAL_NOM1,
          VAL_NOM2,
          COD_SEXO,
          FEC_NACI,
          COD_ESTA_CIVI,
          COD_ESTA_CLIE,
          NUM_DOCU_IDEN,
          COD_TIPO_VIA,
          VAL_NOMB_VIA,
          TRIM(LEADING '0' FROM NUM_PPAL) NUM_PPAL,
          VAL_OBSE,
          VAL_COD_POST,
          VAL_TELE_FIJO,
          VAL_TELE_OFIC,
          VAL_TELE_CELU,
          VAL_CORR_ELEC,
          COD_SUBG_VENT,
          COD_REGI,
          COD_ZONA,
          COD_SECC,
          COD_TERR,
          EST_SOCI_ECON,
          ORDE_1,
          ORDE_2,
          NOM_DEPA,
          NOM_PROV,
          NOM_DIST,
          CAM_ULTI_PEDI,
          SAL_CUEN_CORR,
          IND_PEDI,
          COD_TIPO_CONS,
          EST_CONS,
          COD_TIPO_DOCU,
          NUM_RUC,
          FLA_EMIT_FACT,
          VAL_PORC_DESC,
          FEC_EGRE,
          CAM_INGR,
          VAL_MNTO_PEDI,
          VAL_MNTO_MINI,
          IND_ACTI,
          IND_MARC_LBEL
          --CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_TOTAL( COD_CLIE ) SAL_DEUD_TOTA 
   FROM INT_REU_CONSU_HISTO
   WHERE IND_CLIE_TRAN = 0;
 TYPE interfazRec IS RECORD
 (
  codigoPais               INT_REU_CONSU_HISTO.COD_PAIS%TYPE,
  codigoCliente            INT_REU_CONSU_HISTO.COD_CLIE%TYPE,
  apellidoPaterno          INT_REU_CONSU_HISTO.VAL_APE1%TYPE,
  apellidoMaterno          INT_REU_CONSU_HISTO.VAL_APE2%TYPE,
  nombre1                  INT_REU_CONSU_HISTO.VAL_NOM1%TYPE,
  nombre2                  INT_REU_CONSU_HISTO.VAL_NOM2%TYPE,
  sexo                     INT_REU_CONSU_HISTO.COD_SEXO%TYPE,
  fechaNacimiento          INT_REU_CONSU_HISTO.FEC_NACI%TYPE,
  estadoCivil              INT_REU_CONSU_HISTO.COD_ESTA_CIVI%TYPE,
  estadoCliente            INT_REU_CONSU_HISTO.COD_ESTA_CLIE%TYPE,
  numeroDocumento          INT_REU_CONSU_HISTO.NUM_DOCU_IDEN%TYPE,
  tipoVia                  INT_REU_CONSU_HISTO.COD_TIPO_VIA%TYPE,
  nombreVia                INT_REU_CONSU_HISTO.VAL_NOMB_VIA%TYPE,
  numeroPrincipal          INT_REU_CONSU_HISTO.NUM_PPAL%TYPE,
  observaciones            INT_REU_CONSU_HISTO.VAL_OBSE%TYPE,
  codigoPostal             INT_REU_CONSU_HISTO.VAL_COD_POST%TYPE,
  telefonoCasa             INT_REU_CONSU_HISTO.VAL_TELE_FIJO%TYPE,
  telefonoTrabajo          INT_REU_CONSU_HISTO.VAL_TELE_OFIC%TYPE,
  celular                  INT_REU_CONSU_HISTO.VAL_TELE_CELU%TYPE,
  correo                   INT_REU_CONSU_HISTO.VAL_CORR_ELEC%TYPE,
  codigoSGV                INT_REU_CONSU_HISTO.COD_SUBG_VENT%TYPE,
  codigoRegion             INT_REU_CONSU_HISTO.COD_REGI%TYPE,
  codigoZona               INT_REU_CONSU_HISTO.COD_ZONA%TYPE,
  codigoSeccion            INT_REU_CONSU_HISTO.COD_SECC%TYPE,
  codigoTerreno            INT_REU_CONSU_HISTO.COD_TERR%TYPE,
  estatusSE                INT_REU_CONSU_HISTO.EST_SOCI_ECON%TYPE,
  orden1                   INT_REU_CONSU_HISTO.ORDE_1%TYPE,
  orden2                   INT_REU_CONSU_HISTO.ORDE_2%TYPE,
  nombreUnidadGeografica1  INT_REU_CONSU_HISTO.NOM_DEPA%TYPE,
  nombreUnidadGeografica2  INT_REU_CONSU_HISTO.NOM_PROV%TYPE,
  nombreUnidadGeografica3  INT_REU_CONSU_HISTO.NOM_DIST%TYPE,
  anyoCampanyaUltimoPedido INT_REU_CONSU_HISTO.CAM_ULTI_PEDI%TYPE,
  saldoCtaCte              INT_REU_CONSU_HISTO.SAL_CUEN_CORR%TYPE,
  indicadorPedido          INT_REU_CONSU_HISTO.IND_PEDI%TYPE,
  tipoConsultora           INT_REU_CONSU_HISTO.COD_TIPO_CONS%TYPE,
  estadoConsultora         INT_REU_CONSU_HISTO.EST_CONS%TYPE,
  tipoDocumento            INT_REU_CONSU_HISTO.COD_TIPO_DOCU%TYPE,
  numeroRUC                INT_REU_CONSU_HISTO.NUM_RUC%TYPE,
  flagEmitirFactura        INT_REU_CONSU_HISTO.FLA_EMIT_FACT%TYPE,
  valorPorcentajeDcto      INT_REU_CONSU_HISTO.VAL_PORC_DESC%TYPE,
  fechaEgreso              INT_REU_CONSU_HISTO.FEC_EGRE%TYPE,
  campanyaIngreso          INT_REU_CONSU_HISTO.CAM_INGR%TYPE,
  montoFacturado           INT_REU_CONSU_HISTO.VAL_MNTO_PEDI%TYPE,
  montoMinimo              INT_REU_CONSU_HISTO.VAL_MNTO_MINI%TYPE,
  indicadorActivo          INT_REU_CONSU_HISTO.IND_ACTI%TYPE,
  indicadorMarcaLBel       INT_REU_CONSU_HISTO.IND_MARC_LBEL%TYPE
  --saldototal               INT_REU_SALDO_CUENT_CORRI.SAL_CUEN_CORR%TYPE
   );
  TYPE interfazRecTab  IS TABLE OF interfazRec;
  interfazRecord interfazRecTab;
  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
--c1  W_FILAS             NUMBER := 1000 ;
  W_FILAS             NUMBER := 10000 ; --c1
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(4000);
  lsLinea             VARCHAR2(4000);
  lsLineaCabecera     VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  lnCount             NUMBER := 0;
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=  interfazRecord(x).codigoPais             ||';'||
                          interfazRecord(x).codigoCliente            ||';'||
                          interfazRecord(x).apellidoPaterno          ||';'||
                          interfazRecord(x).apellidoMaterno          ||';'||
                          interfazRecord(x).nombre1                  ||';'||
                          interfazRecord(x).nombre2                  ||';'||
                          interfazRecord(x).sexo                     ||';'||
                          interfazRecord(x).fechaNacimiento          ||';'||
                          interfazRecord(x).estadoCivil              ||';'||
                          interfazRecord(x).estadoCliente            ||';'||
                          interfazRecord(x).numeroDocumento          ||';'||
                          interfazRecord(x).tipoVia                  ||';'||
                          interfazRecord(x).nombreVia                ||';'||
                          interfazRecord(x).numeroPrincipal          ||';'||
                          interfazRecord(x).observaciones            ||';'||
                          interfazRecord(x).codigoPostal             ||';'||
                          interfazRecord(x).telefonoCasa             ||';'||
                          interfazRecord(x).telefonoTrabajo          ||';'||
                          interfazRecord(x).celular                  ||';'||
                          interfazRecord(x).correo                   ||';'||
                          interfazRecord(x).codigoSGV                ||';'||
                          interfazRecord(x).codigoRegion             ||';'||
                          interfazRecord(x).codigoZona               ||';'||
                          interfazRecord(x).codigoSeccion            ||';'||
                          interfazRecord(x).codigoTerreno            ||';'||
                          interfazRecord(x).estatusSE                ||';'||
                          interfazRecord(x).orden1                   ||';'||
                          interfazRecord(x).orden2                   ||';'||
                          interfazRecord(x).nombreUnidadGeografica1  ||';'||
                          interfazRecord(x).nombreUnidadGeografica2  ||';'||
                          interfazRecord(x).nombreUnidadGeografica3  ||';'||
                          interfazRecord(x).anyoCampanyaUltimoPedido ||';'||
                          to_char(interfazRecord(x).saldoCtaCte,'999999990.99')   ||';'||
                          interfazRecord(x).indicadorPedido          ||';'||
                          interfazRecord(x).tipoConsultora           ||';'||
                          interfazRecord(x).estadoConsultora         ||';'||
                          interfazRecord(x).tipoDocumento            ||';'||
                          interfazRecord(x).numeroRUC                ||';'||
                          interfazRecord(x).flagEmitirFactura        ||';'||
                          interfazRecord(x).valorPorcentajeDcto      ||';'||
                          interfazRecord(x).fechaEgreso              ||';'||
                          interfazRecord(x).campanyaIngreso          ||';'||
                          to_char(interfazRecord(x).montoFacturado,'999999990.99') ||';'||
                          to_char(interfazRecord(x).montoMinimo,'999999990.99') ||';'||
                          interfazRecord(x).indicadorActivo          ||';'||
                          interfazRecord(x).indicadorMarcaLBel;
                          --to_char(interfazRecord(x).saldototal,'999999990.99');
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
              lnCount := lnCount + 1;
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
 	GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);


 -- Actualizamos el indicador de transferencia
 UPDATE INT_REU_CONSU_HISTO
 SET IND_CLIE_TRAN = 1
 WHERE IND_CLIE_TRAN = 0;
   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123, 'ERROR REU_PR_INTER_CONSU: '||ls_sqlerrm);
     END IF;
   END INT_PR_REU_ARCHI_CONSU;

/***************************************************************************
Descripcion       : Genera el archivo para la Interfaz Enviar Matriz Facturacion REU-2
Fecha Creacion    : 13/06/2008
  Autor              : Marco Silva
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodigoMarca    : Codigo Marca
            psCodigoCanal    : Codigo Canal
            psCodigoAcceso   : Codigo Acceso
            psPeriodoInicio  : Periodo Inicio
            psPeriodoFin     : Periodoo Fin

 ***************************************************************************/

PROCEDURE INT_PR_REU_ENVIA_MATRI
   (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psCodigoAcceso VARCHAR2,
   psPeriodoInicio VARCHAR2,
   psPeriodoFin VARCHAR2
   )
IS
   CURSOR c_interfaz IS
  select pais.cod_pais      as codpais,
         peri.cod_peri      as codperi,
         det.val_codi_vent  as codvent,
         case when pof.coes_oid_estr in (2007,2006,2005,2004, 2019, 2020) then '01'
         else '  '
         end
           as codgrup,
         prod.cod_sap       as codprod,
         ofer.cod_tipo_ofer as codtofe,
         case when pof.coes_oid_estr in (2002,2006,2020) then (select sum(imp_prec_cata / val_fact_repe) from pre_ofert_detal where ofer_oid_ofer=pof.oid_ofer and nvl(gofe_oid_grup_ofer,0)=nvl(det.gofe_oid_grup_ofer,0)) else imp_prec_cata / val_fact_repe end as precprod,
         --round(det.imp_prec_cata / det.val_fact_repe, 2) as precprod,
         prod.val_prec_cont                              as preccont,
         det.num_pagi_cata                               as pagcata,
         det.val_fact_repe                               as factrep,
         (select ptsp.num_unid_alar
            from ped_tipo_solic_pais ptsp, ped_tipo_solic pts
           where ptsp.pais_oid_pais = pais.oid_pais
             and ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
             and pts.cod_tipo_soli = 'SOC')                 as unidmax,
         ofer.ind_esta                                      as indestad,
         (select grup.cod_grup_desc
            from mae_grupo_descu grup,
                 mae_negoc       nego,
                 mae_unida_negoc unid,
                 seg_marca_produ mpro
           where grup.pais_cod_pais = pais.cod_pais
             and grup.cod_grup_arti = trim(prod.val_grup_arti)
             and nego.pais_oid_pais = pais.oid_pais
             and nego.oid_nego = prod.nego_oid_nego
             and unid.pais_oid_pais = pais.oid_pais
             and grup.cod_unid_nego = unid.cod_unid_nego
             and unid.oid_unid_nego = prod.uneg_oid_unid_nego
             and grup.cod_marc_prod = mpro.cod_marc_prod
             and mpro.oid_marc_prod = prod.mapr_oid_marc_prod) as grupodesc,
         ofer.ind_comi                                         as indcomi,
         (select nvl((select (tof.ind_comi - 1)
                       from pre_tipo_ofert tof
                      where tof.oid_tipo_ofer = ofer.oid_tipo_ofer
                        and tof.cod_tipo_ofer in ('021', '023', '033', '053') -- Nuevo
                        and tof.ind_comi = 1), 1) from dual)        as indcomiadic,
         ocr_solic_pedidos.gen_fn_desc_prod(det.prod_oid_prod)      as desprod,
         (select cata.cod_cata
            from pre_catal cata, pre_ofert ofe
           where cata.oid_cata = ofe.ocat_oid_cata
             and cata.pais_oid_pais = pais.oid_pais
             and det.ofer_oid_ofer = ofe.oid_ofer) as codcata,
         det.ind_digi as indigi,
         (select count(distinct pre_venta_exclu.ofer_oid_ofer)
            from pre_venta_exclu, mae_tipo_clasi_clien, mae_clasi
           where det.ofer_oid_ofer = pre_venta_exclu.ofer_oid_ofer
             and pre_venta_exclu.clas_oid_clas = mae_clasi.oid_clas
             and pre_venta_exclu.tccl_oid_tipo_clas = mae_tipo_clasi_clien.oid_tipo_clas
             and mae_tipo_clasi_clien.oid_tipo_clas = mae_clasi.tccl_oid_tipo_clas
             and mae_clasi.cod_clas = '01'
             and mae_tipo_clasi_clien.cod_tipo_clas = '41')        as indmarclbel,
         mprod.cod_marc_prod,
         sac.cod_acce --modi
    from seg_pais        pais,
         pre_matri_factu matriz,
         pre_ofert       pof,
         pre_ofert_detal det,
         pre_tipo_ofert  ofer,
         mae_produ       prod,
         seg_marca_produ mprod,
         seg_perio_corpo peri,
         seg_acces       sac
   where pais.cod_pais = psCodigoPais
     and pais.oid_pais = prod.pais_oid_pais
     and matriz.ofde_oid_deta_ofer = det.oid_deta_ofer
     and det.tofe_oid_tipo_ofer = ofer.oid_tipo_ofer
     and det.ofer_oid_ofer = pof.oid_ofer
     and pof.acce_oid_acce = sac.oid_acce (+)
     and det.prod_oid_prod = prod.oid_prod
     and prod.mapr_oid_marc_prod = mprod.oid_marc_prod
     and exists
   (select cab.oid_cabe
            from pre_matri_factu_cabec cab
           where exists (select null
                    from cra_perio       a,
                         seg_perio_corpo b,
                         seg_marca       marc,
                         seg_canal       cana,
                         seg_acces       acce
                   where b.oid_peri = peri.oid_peri
                     and a.peri_oid_peri = b.oid_peri
                     and (b.cod_peri between psPeriodoInicio and psPeriodoFin)
                     and a.pais_oid_pais = pais.oid_pais
                     and a.marc_oid_marc = marc.oid_marc
                     and marc.cod_marc = psCodigoMarca
                     and a.cana_oid_cana = cana.oid_cana
                     and cana.cod_cana = psCodigoCanal
                     and a.acce_oid_acce = acce.oid_acce
                     and acce.cod_acce = psCodigoAcceso
                     and acce.cana_oid_cana = cana.oid_cana
                     and cab.perd_oid_peri = a.oid_peri)
             and matriz.mfca_oid_cabe = cab.oid_cabe)
         and det.val_codi_vent is not null

  union all

-- Agrega los Codigos de Cupones Programa Nuevas
  select pais.cod_pais      as codpais,
         peri.cod_peri      as codperi,
         (select cupo.cod_cupon from cup_equiv_matr cupo
                        where cupo.cod_pais = pais.cod_pais and
                              cupo.cod_peri = peri.cod_peri and
                              cupo.cod_venta = det.val_codi_vent ) as codcupon,
         '  '               as codgrup,
         prod.cod_sap       as codprod,
         ofer.cod_tipo_ofer as codtofe,
         --round(det.imp_prec_cata / det.val_fact_repe, 2) as precprod,
         case when pof.coes_oid_estr in (2002,2006,2020) then (select sum(imp_prec_cata / val_fact_repe) from pre_ofert_detal where ofer_oid_ofer=pof.oid_ofer and nvl(gofe_oid_grup_ofer,0)=nvl(det.gofe_oid_grup_ofer,0)) else imp_prec_cata / val_fact_repe end as precprod,
         prod.val_prec_cont                              as preccont,
         det.num_pagi_cata                               as pagcata,
         det.val_fact_repe                               as factrep,
         (select ptsp.num_unid_alar
            from ped_tipo_solic_pais ptsp, ped_tipo_solic pts
           where ptsp.pais_oid_pais = pais.oid_pais
             and ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
             and pts.cod_tipo_soli = 'SOC')                 as unidmax,
         ofer.ind_esta                                      as indestad,
         (select grup.cod_grup_desc
            from mae_grupo_descu grup,
                 mae_negoc       nego,
                 mae_unida_negoc unid,
                 seg_marca_produ mpro
           where grup.pais_cod_pais = pais.cod_pais
             and grup.cod_grup_arti = trim(prod.val_grup_arti)
             and nego.pais_oid_pais = pais.oid_pais
             and nego.oid_nego = prod.nego_oid_nego
             and unid.pais_oid_pais = pais.oid_pais
             and grup.cod_unid_nego = unid.cod_unid_nego
             and unid.oid_unid_nego = prod.uneg_oid_unid_nego
             and grup.cod_marc_prod = mpro.cod_marc_prod
             and mpro.oid_marc_prod = prod.mapr_oid_marc_prod) as grupodesc,
         ofer.ind_comi                                         as indcomi,
         (select nvl((select (tof.ind_comi - 1)
                       from pre_tipo_ofert tof
                      where tof.oid_tipo_ofer = ofer.oid_tipo_ofer
                        and tof.cod_tipo_ofer in ('021', '023', '033', '053') -- Nuevo
                        and tof.ind_comi = 1), 1) from dual)        as indcomiadic,
         --ocr_solic_pedidos.gen_fn_desc_prod(det.prod_oid_prod)      as desprod,
         cupo.des_prod      as desprod,
         (select cata.cod_cata
            from pre_catal cata, pre_ofert ofe
           where cata.oid_cata = ofe.ocat_oid_cata
             and cata.pais_oid_pais = pais.oid_pais
             and det.ofer_oid_ofer = ofe.oid_ofer) as codcata,
        1    as indigi,  -- Para que sean considerados por BizTalk y sean enviados a MyEBEL - PRE_OFERT_DETAL.IND_DIGI
        0    as indmarclbel, -- Estos productos no son considerados para LBel - IND_MARC_LBEL
        mprod.cod_marc_prod,
        sac.cod_acce
    from seg_pais        pais,
         pre_matri_factu matriz,
         pre_ofert       pof,
         pre_ofert_detal det,
         pre_tipo_ofert  ofer,
         mae_produ       prod,
         seg_marca_produ mprod,
         seg_perio_corpo peri,
         seg_acces       sac,
         cup_equiv_matr cupo
   where pais.cod_pais = psCodigoPais
     and pais.oid_pais = prod.pais_oid_pais
     and matriz.ofde_oid_deta_ofer = det.oid_deta_ofer
     and det.tofe_oid_tipo_ofer = ofer.oid_tipo_ofer
     and det.ofer_oid_ofer = pof.oid_ofer
     and pof.acce_oid_acce = sac.oid_acce (+)
     and det.prod_oid_prod = prod.oid_prod
     and prod.mapr_oid_marc_prod = mprod.oid_marc_prod
     AND cupo.cod_pais = pais.cod_pais
     AND cupo.cod_peri = peri.cod_peri
     AND cupo.cod_venta = det.val_codi_vent
     AND cupo.cod_prog = (SELECT MIN(x.cod_prog) FROM cup_equiv_matr x
                                              WHERE x.cod_pais = cupo.cod_pais and
                                                    x.cod_peri = cupo.cod_peri and
                                                    x.cod_cupon = cupo.cod_cupon)
   /*  and exists (select null from cup_equiv_matr cupo
                        where cupo.cod_pais = pais.cod_pais and
                              cupo.cod_peri = peri.cod_peri and
                              cupo.cod_venta = det.val_codi_vent )*/
     and exists
   (select cab.oid_cabe
            from pre_matri_factu_cabec cab
           where exists (select null
                    from cra_perio       a,
                         seg_perio_corpo b,
                         seg_marca       marc,
                         seg_canal       cana,
                         seg_acces       acce
                   where b.oid_peri = peri.oid_peri
                     and a.peri_oid_peri = b.oid_peri
                     and (b.cod_peri between psPeriodoInicio and psPeriodoFin)
                     and a.pais_oid_pais = pais.oid_pais
                     and a.marc_oid_marc = marc.oid_marc
                     and marc.cod_marc = psCodigoMarca
                     and a.cana_oid_cana = cana.oid_cana
                     and cana.cod_cana = psCodigoCanal
                     and a.acce_oid_acce = acce.oid_acce
                     and acce.cod_acce = psCodigoAcceso
                     and acce.cana_oid_cana = cana.oid_cana
                     and cab.perd_oid_peri = a.oid_peri)
                 and matriz.mfca_oid_cabe = cab.oid_cabe)
         and det.val_codi_vent is not null


         union all

               SELECT pais.cod_pais,
             perio.cod_peri,
             a.cod_vent_fict,
             null COD_GRUP,
             prod.cod_sap,
            '00' CODTOFE,
             0 PRECPROD,
             a.IMP_PREC_PUBL PRECCONT,
             0 PAGCATA,
             1 FACTREP,
                          (select ptsp.num_unid_alar
                from ped_tipo_solic_pais ptsp, ped_tipo_solic pts
               where ptsp.pais_oid_pais = pais.oid_pais
                 and ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                 and pts.cod_tipo_soli = 'SOC')                 as unidmax,
                 0 INDESTAD,
                 '00' GRUPODESC,
                 0 INDCOMI,
                 0 INDCOMIADIC,
                 (select val_i18n from gen_i18n_sicc_pais where attr_enti='MAE_PRODU' and val_oid=prod.oid_prod) DESPROD,
                 0 COD_CATA,
                 1 IND_DIGI,
                 0 INDMARCLBEL,
                 smp.cod_marc_prod,
                 null COD_ACCE
        FROM inc_artic_lote a,
             inc_lote_premi_artic b,
             inc_premi_artic c,
             inc_param_nivel_premi d,
             inc_param_gener_premi e,
             inc_concu_param_gener f,
             SEG_PAIS pais,
             mae_produ prod,
             seg_marca_produ smp,
             (SELECT a.cod_peri, b.oid_peri
                FROM seg_perio_corpo a,
                     cra_perio       b
               WHERE (a.cod_peri >= psPeriodoInicio AND a.cod_peri <= psPeriodoFin)
                 --AND b.marc_oid_marc = oidmarca
                 --AND b.cana_oid_cana = oidcanal
                 AND a.oid_peri = b.peri_oid_peri) perio
       WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
       and a.prod_oid_prod=prod.oid_prod
       and prod.pais_oid_pais = pais.oid_pais
       and prod.mapr_oid_marc_prod=smp.oid_marc_prod
         AND b.prar_oid_prem_arti = c.oid_prem_arti
         AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
         AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
         AND e.copa_oid_para_gral = f.oid_para_gral
         AND f.ind_acti = 1
         --AND f.bcal_oid_base_calc in (1,2)
         AND ( f.bcal_oid_base_calc in (1,2) or (f.bcal_oid_base_calc = 4 and e.tprm_oid_tipo_pion = 1 ))
         AND length(a.cod_vent_fict) = 5
         --and e.ind_prem_elec=1
         And d.val_nive_sele=1     -- Nuevo
         and f.perd_oid_peri_desd<=perio.oid_peri
         and e.perd_oid_peri>=perio.oid_peri
;

   TYPE interfazRec IS RECORD
   (
        codigoPais                           SEG_PAIS.COD_PAIS%TYPE,
        codigoPeriodo                        SEG_PERIO_CORPO.COD_PERI%TYPE,
        codigoVenta                          PRE_OFERT_DETAL.VAL_CODI_VENT%TYPE,
        codigoGrupo                          VARCHAR2(2),
        codigoProducto                       MAE_PRODU.COD_SAP%TYPE,
        codigoTipoOferta                     PRE_TIPO_OFERT.COD_TIPO_OFER%TYPE,
        precioProducto                       PRE_OFERT_DETAL.IMP_PREC_CATA%TYPE,
        precioContable                       MAE_PRODU.VAL_PREC_CONT%TYPE,
        numeroPaginaCatalogo                 PRE_OFERT_DETAL.NUM_PAGI_CATA%TYPE,
        factorRepeticionProducto             PRE_OFERT_DETAL.VAL_FACT_REPE%TYPE,
        numeroUnidadesMaximas                PED_TIPO_SOLIC_PAIS.NUM_UNID_ALAR%TYPE,
        indicadorProdEstadist                PRE_TIPO_OFERT.IND_ESTA%TYPE,
        grupoDescuento                       MAE_GRUPO_DESCU.COD_GRUP_DESC%TYPE,
        indicadorComisionable                PRE_TIPO_OFERT.IND_COMI%TYPE,
        indicadorComisionAdicional           VARCHAR2(3),
        descripcion                          GEN_I18N_SICC_PAIS.VAL_I18N%TYPE,
        codigoCatalogo                       PRE_CATAL.COD_CATA%TYPE,
        indicadorDigitable                   PRE_OFERT_DETAL.IND_DIGI%TYPE,
        indicadorMarcaLBel                   VARCHAR2(1),
        codigoMarcaProducto                  SEG_MARCA_PRODU.COD_MARC_PROD%TYPE,
        codigoAcceso                         SEG_ACCES.COD_ACCE%TYPE
   );
   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;
  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(4000);
  lsLineaCabecera     VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  lsNombreDirectorio  VARCHAR2(100) := 'INT_DIR_REU_MATRI';
  lbAbrirUtlFile  BOOLEAN;
BEGIN
    lbAbrirUtlFile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz ;
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
              lsLinea :=
                          interfazRecord(x).codigoPais                     ||';'||
                          interfazRecord(x).codigoPeriodo                  ||';'||
                          interfazRecord(x).codigoVenta                    ||';'||
                          interfazRecord(x).codigoGrupo                    ||';'||
                          interfazRecord(x).codigoProducto                 ||';'||
                          interfazRecord(x).codigoTipoOferta               ||';'||
                          interfazRecord(x).precioProducto                 ||';'||
                          interfazRecord(x).precioContable                 ||';'||
                          interfazRecord(x).numeroPaginaCatalogo           ||';'||
                          interfazRecord(x).factorRepeticionProducto       ||';'||
                          interfazRecord(x).numeroUnidadesMaximas          ||';'||
                          interfazRecord(x).indicadorProdEstadist          ||';'||
                          interfazRecord(x).grupoDescuento                 ||';'||
                          interfazRecord(x).indicadorComisionable          ||';'||
                          interfazRecord(x).indicadorComisionAdicional     ||';'||
                          interfazRecord(x).descripcion                    ||';'||
                          interfazRecord(x).codigoCatalogo                 ||';'||
                          interfazRecord(x).indicadorDigitable             ||';'||
                          interfazRecord(x).indicadorMarcaLBel             ||';'||
                          interfazRecord(x).codigoMarcaProducto            ||';'||
                          interfazRecord(x).codigoAcceso;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
 		GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_REU_ENVIA_MATRI: '||ls_sqlerrm);
END INT_PR_REU_ENVIA_MATRI;
/**************************************************************************
Descripcion        : Obtiene Indicador de Comision Adicional
Fecha Creacion     : 03/03/2007
Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION INT_FN_REU_INDIC_COMIS_ADICI(
  psValCodiVenta VARCHAR2,
  pnIdPeri NUMBER)
RETURN VARCHAR2
IS
  lsDevuelve   PRE_OFERT_DETAL.VAL_CODI_VENT%TYPE;
BEGIN
  SELECT D.VAL_CODI_VENT
  INTO lsDevuelve
  FROM
    PRE_OFERT_DETAL D,
    MAE_PRODU M,
    MAE_NEGOC N,
    MAE_UNIDA_NEGOC UN,
    SEG_MARCA_PRODU MP,
    PRE_TIPO_OFERT TOF,
    GEN_I18N_SICC_PAIS GN,
    GEN_I18N_SICC_PAIS GUN
  WHERE D.VAL_CODI_VENT = psValCodiVenta
  AND EXISTS (
        SELECT OFDE_OID_DETA_OFER
        FROM
            PRE_MATRI_FACTU MF,
            PRE_MATRI_FACTU_CABEC MFC
        WHERE  MF.MFCA_OID_CABE = MFC.OID_CABE
           AND MFC.PERD_OID_PERI = pnIdPeri
           AND MF.OFDE_OID_DETA_OFER = D.OID_DETA_OFER)
  AND D.PROD_OID_PROD = M.OID_PROD
  AND M.NEGO_OID_NEGO = N.OID_NEGO
  AND M.UNEG_OID_UNID_NEGO = UN.OID_UNID_NEGO
  AND M.MAPR_OID_MARC_PROD = MP.OID_MARC_PROD
  AND D.TOFE_OID_TIPO_OFER = TOF.OID_TIPO_OFER
  AND GN.VAL_OID = N.OID_NEGO
  AND GN.ATTR_ENTI LIKE 'MAE_NEGOC%'
  AND GUN.VAL_OID = UN.OID_UNID_NEGO
  AND GUN.ATTR_ENTI LIKE 'MAE_UNIDA_NEGOC%'
  AND TOF.COD_TIPO_OFER IN (21,23,33, 53)
  AND TOF.IND_COMI = 1;
  RETURN '0';
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN '1';
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    IF ln_sqlcode < 0 THEN
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_FN_REU_INDIC_COMIS_ADICI: ' || ls_sqlerrm);
    END IF;
END INT_FN_REU_INDIC_COMIS_ADICI;
/**************************************************************************
Descripcion        : Devuelve Indicador Comisionable
Fecha Creacion     : 03/03/2007
Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION INT_FN_REU_INDIC_COMIS(
    psCodigoPeriodo            VARCHAR2,
    psCodigoTipoCliente        VARCHAR2,
    psCodigoSubtipoCliente     VARCHAR2,
    psCodigoTipoOferta         VARCHAR2,
    psCodigoNegocio            VARCHAR2,
    psCodigoUnidadNegocio      VARCHAR2,
    psCodigoMarcaProducto      VARCHAR2,
    pnComisionable             NUMBER
)
RETURN VARCHAR2
IS
  regRegistro    GEN_PKG_GENER.tRegObtenerDsctoVarios;
BEGIN
  regRegistro := GEN_PKG_GENER.GEN_FN_DEVUE_DSCTO_VARIO(
    psCodigoPeriodo           ,
    psCodigoTipoCliente       ,
    psCodigoSubtipoCliente    ,
    psCodigoTipoOferta        ,
    psCodigoNegocio           ,
    psCodigoUnidadNegocio     ,
    psCodigoMarcaProducto     ,
    pnComisionable            );
  RETURN regRegistro.indicadorComisionable;
END INT_FN_REU_INDIC_COMIS;


/***************************************************************************
Descripcion       : Llena tabla temporal (DAT-86)
Fecha Creacion    : 08/09/2008
Autor             : RRG
***************************************************************************/
PROCEDURE INT_PR_REU_CONSS_UTMPR(
   psCodigoPais    VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2) IS

   lsfechUltmProceso   DATE;

   CURSOR clie_ultm_proce IS
      SELECT cli.oid_clie as oid_clie,
             cli.cod_clie as cod_clie,
             cli.pais_oid_pais as oid_pais,
             cli.fec_ulti_actu as fec_ulti_actu
        FROM mae_clien cli
       WHERE NOT TRUNC(cli.fec_ulti_actu) > TRUNC(cli.fec_crea)
       AND ((cli.fec_ulti_actu > lsfechUltmProceso) OR (lsfechUltmProceso is null))
      UNION
      SELECT cli.oid_clie as oid_clie,
             cli.cod_clie as cod_clie,
             cli.pais_oid_pais as oid_pais,
             cli.fec_ulti_actu as fec_ulti_actu
        FROM mae_clien             cli,
             mae_clien_vincu       vin,
             mae_clien_datos_adici adi,
             mae_clien_unida_admin adm,
             mae_estat_clien       est,
             mae_clien_direc       dir
       WHERE cli.oid_clie = adi.clie_oid_clie
         AND cli.oid_clie = adm.clie_oid_clie
         AND adi.esta_oid_esta_clie = est.oid_esta_clie(+)
         AND cli.oid_clie = vin.clie_oid_clie_vndo(+)
         AND cli.oid_clie = dir.clie_oid_clie
         AND TRUNC(cli.fec_ulti_actu) > TRUNC(cli.fec_crea)
         AND (   (cli.fec_ulti_actu > lsfechUltmProceso)
                 OR (adi.fec_ulti_actu > lsfechUltmProceso)
                 OR (adm.fec_ulti_actu > lsfechUltmProceso)
                 OR (vin.fec_ulti_actu > lsfechUltmProceso)
                 OR (est.fec_ulti_actu > lsfechUltmProceso)
                 OR (dir.fec_ulti_actu > lsfechUltmProceso)
                 OR (lsfechUltmProceso is null)
            );

   TYPE t_oid_clie IS TABLE OF INT_PR_REU_CONS_PROCE.OID_CLIE%TYPE;
   TYPE t_cod_clie IS TABLE OF INT_PR_REU_CONS_PROCE.COD_CLIE%TYPE;
   TYPE t_pais_oid_pais IS TABLE OF INT_PR_REU_CONS_PROCE.PAIS_OID_PAIS%TYPE;
   TYPE t_fec_ulti_actu IS TABLE OF INT_PR_REU_CONS_PROCE.FEC_ULTI_ACTU%TYPE;

   v_oid_clie           t_oid_clie;
   v_cod_clie           t_cod_clie;
   v_pais_oid_pais      t_pais_oid_pais;
   v_fec_ulti_actu      t_fec_ulti_actu;

    rows NATURAL        := 1000;   -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
    v_row_count      NUMBER := 0;

   BEGIN

      SELECT MAX(HL.FEC_FPRO)
        into lsfechUltmProceso
    FROM BAS_HISTO_LOTES HL
       WHERE HL.PAIS_COD_PAIS = psCodigoPais
         AND HL.SIST_COD_SIST = psCodigoSistema
      AND HL.INTE_COD_INTE = psCodigoInterfaz;

      -- Truncamos la tabla que contiene los clientes a enviar
      EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_PR_REU_CONS_PROCE';
       -- Abrimos el cursor usando un BULK COLLECT
    OPEN clie_ultm_proce;
    LOOP
         FETCH clie_ultm_proce BULK COLLECT INTO
                               v_oid_clie,
                               v_cod_clie,
                               v_pais_oid_pais,
                               v_fec_ulti_actu
                               LIMIT rows;

        EXIT WHEN v_row_count = clie_ultm_proce%ROWCOUNT;
        v_row_count := clie_ultm_proce%ROWCOUNT;

        -- Bulk bind of data in memory table...
        FORALL i IN 1..v_oid_clie.count
           insert into INT_PR_REU_CONS_PROCE(OID_CLIE, COD_CLIE, PAIS_OID_PAIS, FEC_ULTI_ACTU)
           values (v_oid_clie(i),v_cod_clie(i),v_pais_oid_pais(i), v_fec_ulti_actu(i));
    END LOOP;
    CLOSE clie_ultm_proce;

END INT_PR_REU_CONSS_UTMPR;

/***************************************************************************
Descripcion       : Determina el valor del indicador de autorizacion para pasar
                    pedido, el cual es enviado a la web de SomosEsika / MyBEL.
Fecha Creacion    : 22/01/2009
Autor             : Carlos Hurtado
Parametros        pnOidCliente: OID del Cliente
***************************************************************************/
FUNCTION INT_FN_REU_INDIC_PEDID(pnOidCliente  NUMBER,
                                psCodEstaClie VARCHAR2) RETURN VARCHAR2 IS

lnCount  NUMBER := 0;
lsResult VARCHAR2(1);

BEGIN

    -- Validamos si la consultora tiene algun bloqueo
    SELECT COUNT(*)
    INTO lnCount
    FROM (
      SELECT A.CLIE_OID_CLIE,
             NVL(D.COD_PROC_BLOQ, '999') COD_PROC_BLOQ,
             NVL(C.COD_ACCI_BLOQ, '999') COD_ACCI_BLOQ
      FROM MAE_CLIEN_BLOQU A,
           MAE_ACCIO_PROCE_BLOQU B,
           MAE_ACCIO_BLOQU C,
           MAE_PROCE_BLOQU D
      WHERE A.FEC_DESB IS NULL
        AND A.CLIE_OID_CLIE = pnOidCliente
        AND A.TIBQ_OID_TIPO_BLOQ = B.TIBQ_OID_TIPO_BLOQ (+)
        AND B.MABL_OID_ACCI_BLOQ = C.OID_ACCI_BLOQ (+)
        AND B.MAPB_OID_PROC_BLOQ = D.OID_PROC_BLOQ (+)
    ) BLOQUEO
    WHERE BLOQUEO.COD_PROC_BLOQ NOT IN ('FA', 'FR')
    AND BLOQUEO.COD_ACCI_BLOQ != 'FS';

    IF lnCount > 0 THEN
        lsResult := 'N'; -- No Autorizado a pasar pedido
    ELSE
        BEGIN
            SELECT
            CASE WHEN MCDA.IND_ACTI = 1 AND
                 (psCodEstaClie = '01' OR -- Registrada
                  psCodEstaClie = '02' OR -- Ingreso / Nueva
                  psCodEstaClie = '03' OR -- Constante / Normal
                  psCodEstaClie = '04' OR -- Posible Egreso /Egresante
                  psCodEstaClie = '05' OR -- Egreso / Egresada
                  psCodEstaClie = '06' OR -- Reingreso
                  psCodEstaClie = '07' OR -- Retirada
                  psCodEstaClie = '08')   -- Reactivada
            THEN 'S' -- Autorizado a pasar pedido
            ELSE 'N' -- No Autorizado a pasar pedido
            END
            INTO lsResult
            FROM MAE_CLIEN_DATOS_ADICI MCDA
            WHERE MCDA.CLIE_OID_CLIE = pnOidCliente;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN lsResult := 'N';
        END;
    END IF;

    RETURN lsResult;

END INT_FN_REU_INDIC_PEDID;

/***************************************************************************
Descripcion       : Determina el status de la consultora a enviar a Calypso.
Fecha Creacion    : 11/02/2009
Autor             : Carlos Hurtado
Parametros        pnOidCliente: OID del Cliente
                  pnMontoMaximoDeuda Monto de deuda maximo el cual sobrepasado
                                     considera a la consultora como morosa.
***************************************************************************/
FUNCTION INT_FN_REU_EVALU_ESTAT_CONSU(pnOidCliente       NUMBER,
                                      psCodEstaClie      VARCHAR2,
                                      psCodZona          VARCHAR2,
                                      pnMontoMaximoDeuda NUMBER) RETURN VARCHAR2 IS

lnCount     NUMBER := 0;
lnCountZona NUMBER := 0;
lsResult    VARCHAR2(2);

BEGIN

    SELECT COUNT(*)
    INTO lnCountZona
    FROM INT_PED_ZONA_OFICI
    WHERE EST_ZOOF = 1
    AND COD_ZONA = psCodZona;

    -- Primero validamos el status de morosidad
    IF  (psCodEstaClie != '07' -- Se aplica a las no retiradas
         AND lnCountZona = 0 -- No se toman en cuenta las zonas de oficina
         AND GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALDO_VENCI(pnOidCliente, SYSDATE - 63) > pnMontoMaximoDeuda
        ) THEN
        lsResult := '02'; -- Morosa
    ELSE
        -- Validamos si la consultora tiene algun bloqueo
        SELECT COUNT(*)
        INTO lnCount
        FROM (
          SELECT A.CLIE_OID_CLIE,
                 NVL(D.COD_PROC_BLOQ, '999') COD_PROC_BLOQ,
                 NVL(C.COD_ACCI_BLOQ, '999') COD_ACCI_BLOQ
          FROM MAE_CLIEN_BLOQU A,
               MAE_ACCIO_PROCE_BLOQU B,
               MAE_ACCIO_BLOQU C,
               MAE_PROCE_BLOQU D
          WHERE A.FEC_DESB IS NULL
            AND A.CLIE_OID_CLIE = pnOidCliente
            AND A.TIBQ_OID_TIPO_BLOQ = B.TIBQ_OID_TIPO_BLOQ (+)
            AND B.MABL_OID_ACCI_BLOQ = C.OID_ACCI_BLOQ (+)
            AND B.MAPB_OID_PROC_BLOQ = D.OID_PROC_BLOQ (+)
        ) BLOQUEO
        WHERE BLOQUEO.COD_PROC_BLOQ NOT IN ('FA', 'FR')
        AND BLOQUEO.COD_ACCI_BLOQ != 'FS';

        IF lnCount > 0 THEN
            lsResult := '03'; -- Bloqueo
        ELSE
            BEGIN
                SELECT
                CASE WHEN MCDA.IND_ACTI = 1 AND
                (
                     (psCodEstaClie = '01' AND 
                          (SELECT count(*)
                                FROM ped_solic_cabec soca
                           WHERE 1=1
                           AND soca.perd_oid_peri = (SELECT p.oid_peri 
                                                    FROM cra_perio p,
                                                         seg_perio_corpo spc
                                                     WHERE p.peri_oid_peri = spc.oid_peri
                                                     AND spc.cod_peri = ( SELECT bcf.cod_peri
                                                                           FROM bas_ctrl_fact bcf
                                                                           WHERE bcf.ind_camp_act = 1
                                                                           AND bcf.sta_camp = 0
                                                                         )
                                                    ) 
                             AND soca.clie_oid_clie=pnOidCliente
                             AND soca.tspa_oid_tipo_soli_pais =(SELECT ptsp.oid_tipo_soli_pais 
                                                                FROM ped_tipo_solic pts, 
                                                                     ped_tipo_solic_pais ptsp 
                                                                WHERE pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli 
                                                                AND pts.cod_tipo_soli = 'SOC')
                             and fec_fact is not null)>0
                      ) OR   -- Registrada 
                      psCodEstaClie = '02' OR -- Ingreso / Nueva
                      psCodEstaClie = '03' OR -- Constante / Normal
                      psCodEstaClie = '04' OR -- Posible Egreso /Egresante
                      psCodEstaClie = '06' OR -- Reingreso
                      psCodEstaClie = '08')   -- Reactivada
                THEN '00' -- Activo
                ELSE '01' -- Inactivo
                END
                INTO lsResult
                FROM MAE_CLIEN_DATOS_ADICI MCDA
                WHERE MCDA.CLIE_OID_CLIE = pnOidCliente;
            EXCEPTION
            WHEN NO_DATA_FOUND THEN lsResult := '01';
            END;
        END IF;

    END IF;

    RETURN lsResult;

END INT_FN_REU_EVALU_ESTAT_CONSU;

END INT_PKG_REUTI;
/
