CREATE OR REPLACE PACKAGE "MAE_PKG_CLASI" IS
/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(150);
W_FILAS      NUMBER:=3000;

TYPE TIPOCURSOR IS  REF CURSOR;

/***************************************************************************
Descripcion       : Actualiza Clasificacion de Clientes
Fecha Creacion    : 14/02/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CLASI_CLIEN
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psCodigoTipoCliente        VARCHAR2,
   psNumeroCampanias          NUMBER);

/***************************************************************************
Descripcion       : Verifica si el codigo de Cliente existe en el esquema de
                    LBEL relacionado al mismo pais
Fecha Creacion    : 14/02/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_VERIF_CLIEN_LBEL
  (psOidCliente    NUMBER) RETURN NUMBER;

/***************************************************************************
Descripcion       : Actualiza Clasificacion LOVE de Clientes
Fecha Creacion    : 14/09/2009
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CLASI_LOVE
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoPeriodo            VARCHAR2);

/***************************************************************************
Descripcion       : Valida Carga de Clasificacion de Clientes
Fecha Creacion    : 24/05/2010
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_VALID_CARGA_CLASI
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER);

/***************************************************************************
Descripcion       : Actualiza Carga de Clasificacion de Clientes
Fecha Creacion    : 25/05/2010
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CARGA_CLASI
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   psNumeroLote               VARCHAR2,
   psUsuMod                   VARCHAR2);

/***************************************************************************
Descripcion       : Actualiza  Clasificacion de Nuevas por Zonas.
Fecha Creacion    : 02/09/2014
Autor             : Juan Gutiérrez
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CLASI_NUEVA
 (
   psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psCodigoUsuario            VARCHAR2);

/**************************************************************************
Descripcion       : Inserta las correspondiente registros en las tablas de
                    incentivos para la recomendante y la recomendad
Fecha Creacion    : 12/08/2015
Parametros Entrada:
  psCodigoPais         : Codigo Pais

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_CARGA_MASIV_CLASI_SEGME
  (psCodigoPais           VARCHAR2);

/***************************************************************************
Descripcion       : Valida Carga Masiva de Direcciones
Fecha Creacion    : 31/08/2015
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_VALID_CARGA_MASIV_DIREC
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER);

/**************************************************************************
Descripcion       : Envio de correo electronico en Carga Automatica Masiva de Clasificaciones de Clientes
Fecha Creacion    : 02/09/2015
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE MAE_PR_ENCOR_CARGA_AUTOM_MASCC
  (psCodigoPais           VARCHAR2);
  
END MAE_PKG_CLASI;
/
CREATE OR REPLACE PACKAGE BODY "MAE_PKG_CLASI" IS
/***************************************************************************
Descripcion       : Actualiza Clasificacion de Clientes
Fecha Creacion    : 14/03/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CLASI_CLIEN
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psCodigoTipoCliente        VARCHAR2,
   psNumeroCampanias          NUMBER)
IS

CURSOR c_clientesNuevos(oidPais NUMBER, codigoTipoCliente VARCHAR2) IS
SELECT cli.oid_clie, sub.SBTI_OID_SUBT_CLIE, sub.OID_CLIE_TIPO_SUBT, est.cod_esta_clie
  FROM MAE_CLIEN cli,
       MAE_CLIEN_DATOS_ADICI adi,
       MAE_CLIEN_TIPO_SUBTI sub,
       MAE_TIPO_CLIEN mtc,
       MAE_ESTAT_CLIEN est,
       MAE_CLASI_ACTUA cla
 WHERE mtc.cod_tipo_clie = codigoTipoCliente
   AND sub.ticl_oid_tipo_clie = mtc.oid_tipo_clie
   AND sub.clie_oid_clie = cli.oid_clie
   AND cli.pais_oid_pais = oidPais
   AND adi.clie_oid_clie = cli.oid_clie
   AND adi.esta_oid_esta_clie = est.oid_esta_clie
   AND est.cod_esta_clie IN ('01', '02', '07', '08')
   AND sub.sbti_oid_subt_clie = cla.oid_subt_clie
   AND cla.cla_cod = '1'
   AND NOT EXISTS (
          SELECT mcl.oid_clie_clas
            FROM mae_clien_clasi mcl, mae_clasi_actua pca
           WHERE mcl.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
             AND mcl.clas_oid_clas = pca.oid_clas
             AND pca.cla_cod = '1')
  AND NOT EXISTS (
          SELECT mcb.CLIE_OID_CLIE
            FROM MAE_CLIEN_BLOQU mcb
           WHERE (mcb.CLIE_OID_CLIE = cli.OID_CLIE)
           and mcb.fec_desb is null
      );

CURSOR c_clientesAntiguos(oidPais NUMBER, codigoTipoCliente VARCHAR2, oidPeriodo NUMBER) IS
SELECT cli.oid_clie, cls.OID_CLIE_CLAS, sub.SBTI_OID_SUBT_CLIE
  FROM MAE_CLIEN cli,
       MAE_CLIEN_PRIME_CONTA pri,
       MAE_CLIEN_TIPO_SUBTI sub,
       MAE_TIPO_CLIEN mtc,
       MAE_CLIEN_CLASI cls,
       MAE_CLIEN_DATOS_ADICI adi,
       MAE_ESTAT_CLIEN est,
       MAE_CLASI_ACTUA cla
 WHERE mtc.cod_tipo_clie = codigoTipoCliente
   AND sub.ticl_oid_tipo_clie = mtc.oid_tipo_clie
   AND sub.clie_oid_clie = cli.oid_clie
   AND cls.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
   AND cli.pais_oid_pais = oidPais
   AND adi.clie_oid_clie = cli.oid_clie
   AND adi.esta_oid_esta_clie = est.oid_esta_clie
   AND est.cod_esta_clie IN ('03', '04', '05', '06')
   AND pri.clie_oid_clie = cli.oid_clie
   AND pri.perd_oid_peri = oidPeriodo
   AND sub.sbti_oid_subt_clie = cla.oid_subt_clie
   AND cls.clas_oid_clas = cla.oid_clas
   AND cla.cla_cod = '1'
   AND NOT EXISTS (
          SELECT mcb.CLIE_OID_CLIE
            FROM MAE_CLIEN_BLOQU mcb
           WHERE (mcb.CLIE_OID_CLIE = cli.OID_CLIE)
           and mcb.fec_desb is null
      );

CURSOR c_clientesLBELxEliminar(oidPais NUMBER, codigoTipoCliente VARCHAR2, periodoLEbel VARCHAR2) IS
SELECT cli.oid_clie, cls.oid_clie_clas
  FROM MAE_CLIEN cli,
       MAE_CLIEN_TIPO_SUBTI sub,
       MAE_TIPO_CLIEN mtc,
       MAE_CLIEN_CLASI cls,
       MAE_CLIEN_PRIME_CONTA pri,
       SEG_PERIO_CORPO spc,
       CRA_PERIO cra,
       MAE_CLIEN_DATOS_ADICI adi,
       MAE_CLIEN_UNIDA_ADMIN uni,
       ZON_TERRI_ADMIN ter,
       ZON_SECCI sec,
       ZON_ZONA zon,
       MAE_CLASI_ACTUA cla
 WHERE mtc.cod_tipo_clie = codigoTipoCliente
   AND sub.ticl_oid_tipo_clie = mtc.oid_tipo_clie
   AND sub.clie_oid_clie = cli.oid_clie
   AND cls.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
   AND cli.pais_oid_pais = oidPais
   AND sub.sbti_oid_subt_clie = cla.oid_subt_clie
   AND cls.clas_oid_clas = cla.oid_clas
   AND cla.cla_cod = '3'
   AND pri.clie_oid_clie = cli.oid_clie
   AND pri.perd_oid_peri = cra.oid_peri
   AND cra.peri_oid_peri = spc.oid_peri
   AND adi.clie_oid_clie = cli.oid_clie
   AND uni.clie_oid_clie = cli.oid_clie
   AND uni.ind_acti = '1'
   AND uni.ztad_oid_terr_admi = ter.oid_terr_admi
   AND ter.zscc_oid_secc = sec.oid_secc
   AND sec.zzon_oid_zona = zon.oid_zona
   AND zon.oid_zona IN (SELECT oid_zona from MAE_CLASI_ZONA)
   AND ((spc.cod_peri >= periodoLEbel) OR
        (adi.esta_oid_esta_clie IN (SELECT OID_ESTA_CLIE FROM MAE_CLASI_ESTAT_LBEL WHERE COD_OPER='3')) OR
        MAE_FN_VERIF_CLIEN_LBEL(cli.oid_clie) > 0
       )
   AND NOT EXISTS (
      SELECT mcb.CLIE_OID_CLIE
      FROM MAE_CLIEN_BLOQU mcb
      WHERE (mcb.CLIE_OID_CLIE = cli.OID_CLIE)
      and mcb.fec_desb is null);

CURSOR c_clientesLBELxInsertar(oidPais NUMBER, codigoTipoCliente VARCHAR2, oidSubTipo NUMBER) IS
SELECT cli.oid_clie, sub.SBTI_OID_SUBT_CLIE, sub.OID_CLIE_TIPO_SUBT
  FROM MAE_CLIEN cli,
       MAE_CLIEN_TIPO_SUBTI sub,
       MAE_TIPO_CLIEN mtc,
       MAE_CLASI_ACTUA cla,
       MAE_CLIEN_UNIDA_ADMIN uni,
       ZON_TERRI_ADMIN ter,
       ZON_SECCI sec,
       ZON_ZONA zon
 WHERE mtc.cod_tipo_clie = codigoTipoCliente
   AND sub.ticl_oid_tipo_clie = mtc.oid_tipo_clie
   AND sub.clie_oid_clie = cli.oid_clie
   AND cli.pais_oid_pais = oidPais
   AND sub.sbti_oid_subt_clie = cla.oid_subt_clie
   AND cla.cla_cod = '3'
   AND uni.clie_oid_clie = cli.oid_clie
   AND uni.ind_acti = '1'
   AND uni.ztad_oid_terr_admi = ter.oid_terr_admi
   AND ter.zscc_oid_secc = sec.oid_secc
   AND sec.zzon_oid_zona = zon.oid_zona
   AND NOT EXISTS (
          SELECT mcl.oid_clie_clas
            FROM mae_clien_clasi mcl, mae_clasi_actua pca
           WHERE mcl.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
             AND mcl.clas_oid_clas = pca.oid_clas
             AND pca.cla_cod IN ('3','4'))
   AND NOT EXISTS (
          SELECT mcb.CLIE_OID_CLIE
            FROM MAE_CLIEN_BLOQU mcb
           WHERE mcb.CLIE_OID_CLIE = cli.OID_CLIE
             and mcb.fec_desb is null)
   AND (sub.sbti_oid_subt_clie = oidSubTipo OR
        zon.oid_zona NOT IN (SELECT oid_zona FROM MAE_CLASI_ZONA));

CURSOR c_clientesLBELInhabxInsertar(oidPais NUMBER, codigoTipoCliente VARCHAR2) IS
SELECT cli.oid_clie, sub.SBTI_OID_SUBT_CLIE, sub.OID_CLIE_TIPO_SUBT
  FROM MAE_CLIEN cli,
       MAE_CLIEN_TIPO_SUBTI sub,
       MAE_TIPO_CLIEN mtc
 WHERE mtc.cod_tipo_clie = codigoTipoCliente
   AND sub.ticl_oid_tipo_clie = mtc.oid_tipo_clie
   AND sub.clie_oid_clie = cli.oid_clie
   AND cli.pais_oid_pais = oidPais
   AND sub.sbti_oid_subt_clie IN (SELECT DISTINCT OID_SUBT_CLIE FROM MAE_CLASI_ACTUA WHERE CLA_COD IN ('3'))
   AND NOT EXISTS (
          SELECT mcl.oid_clie_clas
            FROM mae_clien_clasi mcl, mae_clasi_actua pca
           WHERE mcl.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
             AND mcl.clas_oid_clas = pca.oid_clas
             AND pca.cla_cod IN ('3','4'))
   AND NOT EXISTS (
          SELECT mcb.CLIE_OID_CLIE
            FROM MAE_CLIEN_BLOQU mcb
           WHERE mcb.CLIE_OID_CLIE = cli.OID_CLIE
           and mcb.fec_desb is null);

CURSOR c_clientesSuscripcionCatalogos(oidPais NUMBER, codigoTipoCliente VARCHAR2, oidPeriodo NUMBER) IS
SELECT cli.oid_clie, sub.SBTI_OID_SUBT_CLIE, sub.OID_CLIE_TIPO_SUBT, adi.esta_oid_esta_clie
  FROM MAE_CLIEN cli,
       MAE_CLIEN_PRIME_CONTA pri,
       MAE_CLIEN_DATOS_ADICI adi,
       MAE_CLIEN_TIPO_SUBTI sub,
       MAE_TIPO_CLIEN mtc,
       MAE_CLASI_ACTUA cla
 WHERE mtc.cod_tipo_clie = codigoTipoCliente
   AND sub.ticl_oid_tipo_clie = mtc.oid_tipo_clie
   AND sub.clie_oid_clie = cli.oid_clie
   AND cli.pais_oid_pais = oidPais
   AND adi.clie_oid_clie = cli.oid_clie
   AND adi.esta_oid_esta_clie = 3 --Activas
   AND pri.clie_oid_clie = cli.oid_clie
   AND pri.perd_oid_peri = oidPeriodo
   AND sub.sbti_oid_subt_clie = cla.oid_subt_clie
   AND cla.cla_cod = '5'
   AND NOT EXISTS (
          SELECT mcl.oid_clie_clas
            FROM mae_clien_clasi mcl, mae_clasi_actua pca
           WHERE mcl.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
             AND mcl.tccl_oid_tipo_clasi = pca.oid_tipo_clas --Lo busca por Tipo Clasificacion
             AND pca.cla_cod = '5')
  AND NOT EXISTS (
          SELECT mcb.CLIE_OID_CLIE
            FROM MAE_CLIEN_BLOQU mcb
           WHERE (mcb.CLIE_OID_CLIE = cli.OID_CLIE)
           and mcb.fec_desb is null
      );

-------------------------------------------------------------
CURSOR c_clientesSegmentacion(oidPais NUMBER, codigoTipoCliente VARCHAR2) IS
SELECT DISTINCT mc.OID_CLIE,
       mcts.OID_CLIE_TIPO_SUBT,
       mcaa.OID_TIPO_CLAS,
       mcaa.OID_CLAS
FROM
mae_clien_vincu mcv,
MAE_TIPO_VINCU  mtv,
mae_clien mc,
MAE_TIPO_CLIEN mtc,
mae_clien_tipo_subti mcts,
MAE_CLIEN_CLASI mcc,
MAE_CLASI_ACTUA mcaa,
mae_clien_datos_adici da
WHERE
mcv.TIVC_OID_TIPO_VINC = mtv.OID_TIPO_VINC
AND mtv.COD_TIPO_VINC  = '01' -- Dupla Cyzone
AND mc.pais_oid_pais   = oidPais
AND mcv.fec_ulti_actu >= NVL(mcv.fec_desd,SYSDATE)
AND ( mcv.fec_ulti_actu <=  NVL(mcv.fec_hast,SYSDATE) OR mcv.FEC_HAST IS NULL )
AND mcv.CLIE_OID_CLIE_VNTE = mc.OID_CLIE
AND mc.OID_CLIE = mcts.CLIE_OID_CLIE
AND mcts.TICL_OID_TIPO_CLIE = mtc.OID_TIPO_CLIE
AND mtc.COD_TIPO_CLIE = codigoTipoCliente
AND mcts.OID_CLIE_TIPO_SUBT =  mcc.CTSU_OID_CLIE_TIPO_SUBT
AND mc.OID_CLIE = da.CLIE_OID_CLIE
AND da.IND_ACTI = 1
AND mcaa.IND_DEFA = 1
AND mcaa.CLA_COD= '6'
AND mcaa.OID_SUBT_CLIE = mcts.sbti_oid_subt_clie
AND NOT EXISTS (SELECT *
                        FROM MAE_CLIEN_BLOQU MCB
                       WHERE MCB.CLIE_OID_CLIE =  mc.OID_CLIE  --50749 --
                         AND MCB.FEC_DESB IS NULL)
AND mc.oid_clie NOT IN (SELECT DISTINCT mcts.CLIE_OID_CLIE
					   		FROM
							MAE_CLIEN_CLASI mcc1,
							MAE_CLASI_ACTUA mca,
							mae_clien_tipo_subti mcts
							WHERE
							      mcc1.CLAS_OID_CLAS = mca.OID_CLAS
							  AND mcc1.TCCL_OID_TIPO_CLASI = mca.OID_TIPO_CLAS
							  AND mcc1.CTSU_OID_CLIE_TIPO_SUBT = mcts.OID_CLIE_TIPO_SUBT
							  AND mcts.SBTI_OID_SUBT_CLIE = mca.OID_SUBT_CLIE
							  AND mca.CLA_COD ='6');



/*CURSOR c_clientesSegmentacion(oidPais NUMBER, codigoTipoCliente VARCHAR2) IS
  SELECT CLI.OID_CLIE,
         SUB.OID_CLIE_TIPO_SUBT,
         cla.oid_tipo_clas,
         cla.oid_clas
    FROM MAE_CLIEN             CLI,
         MAE_CLIEN_TIPO_SUBTI  SUB,
         MAE_TIPO_CLIEN        MTC,
         MAE_CLASI_ACTUA       CLA,
         mae_clien_vincu       a ,
         MAE_TIPO_VINCU        b
   WHERE MTC.COD_TIPO_CLIE = codigoTipoCliente
     AND SUB.TICL_OID_TIPO_CLIE = MTC.OID_TIPO_CLIE
     AND SUB.CLIE_OID_CLIE = CLI.OID_CLIE
     AND CLI.PAIS_OID_PAIS = oidPais
     AND SUB.SBTI_OID_SUBT_CLIE = CLA.OID_SUBT_CLIE
     AND CLA.CLA_COD = '6'
     -- Dupla Cyzone
     AND a.oid_clie_vinc = cli.oid_clie
     AND a.tivc_oid_tipo_vinc = b.oid_tipo_vinc
     AND b.cod_tipo_vinc = '01'
     AND a.fec_ulti_actu BETWEEN nvl(a.fec_desd,SYSDATE) AND nvl(a.fec_hast,SYSDATE)
      AND NOT EXISTS (SELECT NULL
                        FROM MAE_CLIEN_CLASI MCL,
                             MAE_CLASI_ACTUA PCA
                       WHERE MCL.CTSU_OID_CLIE_TIPO_SUBT = SUB.OID_CLIE_TIPO_SUBT
                         AND MCL.Tccl_Oid_Tipo_Clasi = cla.oid_tipo_clas
                         AND MCL.CLAS_OID_CLAS = PCA.OID_CLAS
                         AND PCA.CLA_COD = '6')

      AND NOT EXISTS (SELECT NULL
                        FROM MAE_CLIEN_BLOQU MCB
                       WHERE (MCB.CLIE_OID_CLIE = CLI.OID_CLIE)
                         AND MCB.FEC_DESB IS NULL);*/

TYPE interfazClientesSegmentacion IS RECORD
   (
    oidCliente                mae_clien.OID_CLIE%TYPE,
    oidSubTipoCliente         mae_clien_tipo_subti.OID_CLIE_TIPO_SUBT%TYPE,
    oidTipoClasi              MAE_CLASI_ACTUA.oid_tipo_clas%TYPE,
    codClasi                  MAE_CLASI_ACTUA.oid_clas%TYPE
   );

   TYPE interfazClientesSegmenTab  IS TABLE OF interfazClientesSegmentacion;
   interfazRecordSeg interfazClientesSegmenTab;
-------------------------------------------------------------

   TYPE interfazClientesNuevos IS RECORD
   (
    oidCliente                mae_clien.OID_CLIE%TYPE,
    oidSubTipoCliente         mae_clien_tipo_subti.SBTI_OID_SUBT_CLIE%TYPE,
    oidClienteSubTipo         mae_clien_tipo_subti.OID_CLIE_TIPO_SUBT%TYPE,
    codEstadoCliente          mae_estat_clien.COD_ESTA_CLIE%TYPE
   );

   TYPE interfazClientesNuevosTab  IS TABLE OF interfazClientesNuevos;
   interfazRecordN interfazClientesNuevosTab;

   TYPE interfazClientesAntiguos IS RECORD
   (
    oidCliente                mae_clien.OID_CLIE%TYPE,
    oidClienteClasi           mae_clien_clasi.oid_clie_clas%TYPE,
    oidSubTipoCliente         mae_clien_tipo_subti.SBTI_OID_SUBT_CLIE%TYPE
   );

   TYPE interfazClientesAntiguosTab  IS TABLE OF interfazClientesAntiguos;
   interfazRecordA interfazClientesAntiguosTab;

   TYPE interfazClientesEliminar IS RECORD
   (
    oidCliente                mae_clien.oid_clie%TYPE,
    oidClienteClasi           mae_clien_clasi.oid_clie_clas%TYPE
   );

   TYPE interfazClientesEliminarTab  IS TABLE OF interfazClientesEliminar;
   interfazRecordE interfazClientesEliminarTab;

   TYPE interfazClientesNuevosLBel IS RECORD
   (
    oidCliente                mae_clien.OID_CLIE%TYPE,
    oidSubTipoCliente         mae_clien_tipo_subti.SBTI_OID_SUBT_CLIE%TYPE,
    oidClienteSubTipo         mae_clien_tipo_subti.OID_CLIE_TIPO_SUBT%TYPE
   );

   TYPE interfazClientesNuevosLBelTab  IS TABLE OF interfazClientesNuevosLBel;
   interfazRecordLBelN interfazClientesNuevosLBelTab;

   TYPE interfazClientesSuscripcion IS RECORD
   (
    oidCliente                mae_clien.OID_CLIE%TYPE,
    oidSubTipoCliente         mae_clien_tipo_subti.SBTI_OID_SUBT_CLIE%TYPE,
    oidClienteSubTipo         mae_clien_tipo_subti.OID_CLIE_TIPO_SUBT%TYPE,
    oidEstadoCliente          mae_estat_clien.OID_ESTA_CLIE%TYPE
   );

   TYPE interfazClientesSuscripcionTab  IS TABLE OF interfazClientesSuscripcion;
   interfazRecordS interfazClientesSuscripcionTab;

   ls_oidPais                 seg_pais.oid_pais%TYPE;
   ls_oidMarca                seg_marca.oid_marc%TYPE;
   ls_oidCanal                seg_canal.oid_cana%TYPE;
   ls_oidPeriodo              cra_perio.oid_peri%TYPE;
   ls_codigoPeriodoEvaluar    seg_perio_Corpo.COD_PERI%TYPE;
   ls_oidPeriodoEvaluar       cra_perio.oid_peri%TYPE;
   ls_PeriodoLEbel            cra_perio.oid_peri%TYPE;

   ls_oidCliente              mae_clien.OID_CLIE%TYPE;
   ls_oidClienteSubTipo       mae_clien_tipo_subti.OID_CLIE_TIPO_SUBT%TYPE;
   ls_oidSubTipoCliente       mae_clien_tipo_subti.SBTI_OID_SUBT_CLIE%TYPE;
   ls_oidClienteClasi         mae_clien_clasi.OID_CLIE_CLAS%TYPE;
   ls_codEstadoCliente        mae_estat_clien.COD_ESTA_CLIE%TYPE;
   ls_oidEstadoCliente        mae_estat_clien.OID_ESTA_CLIE%TYPE;

   ls_oidClas                 mae_clasi.OID_CLAS%TYPE;
   ls_oidTipoClas             mae_tipo_clasi_clien.OID_TIPO_CLAS%TYPE;
   ls_indFlagClas             mae_clien_clasi.IND_PPAL%TYPE;
   ls_tipoPais                VARCHAR2(1);
   ls_Proceso                 VARCHAR2(100);

   TYPE t_oidClasiCli         IS TABLE OF MAE_CLIEN_CLASI.OID_CLIE_CLAS%TYPE ;
   TYPE t_oidsubTipoCli       IS TABLE OF MAE_CLIEN_CLASI.CTSU_OID_CLIE_TIPO_SUBT%TYPE ;
   TYPE t_clasificacion       IS TABLE OF MAE_CLIEN_CLASI.CLAS_OID_CLAS%TYPE ;
   TYPE t_periodo             IS TABLE OF MAE_CLIEN_CLASI.PERD_OID_PERI%TYPE ;
   TYPE t_tipoClasi           IS TABLE OF MAE_CLIEN_CLASI.TCCL_OID_TIPO_CLASI%TYPE ;
   TYPE t_fechaClasi          IS TABLE OF MAE_CLIEN_CLASI.FEC_CLAS%TYPE ;
   TYPE t_indPrincipal        IS TABLE OF MAE_CLIEN_CLASI.IND_PPAL%TYPE ;

   v_oidClasiCli              t_oidClasiCli  := t_oidClasiCli() ;
   v_oidsubTipoCli            t_oidsubTipoCli  := t_oidsubTipoCli() ;
   v_clasificacion            t_clasificacion  := t_clasificacion() ;
   v_periodo                  t_periodo  := t_periodo() ;
   v_tipoClasi                t_tipoClasi  := t_tipoClasi() ;
   v_fechaClasi               t_fechaClasi  := t_fechaClasi() ;
   v_indPrincipal             t_indPrincipal  := t_indPrincipal() ;

	 ls_oidSubTipo              MAE_SUBTI_CLIEN.oid_subt_clie%TYPE;

   ls_log VARCHAR2(1000);
   lsCodPeriodoAux            SEG_PERIO_CORPO.COD_PERI%TYPE;
   lnOidPeriodoAux            CRA_PERIO.OID_PERI%TYPE;
BEGIN

    --Recuperamos el oid Pais,Marca,Canal,Periodo
    ls_oidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    ls_oidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    ls_oidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    ls_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, ls_oidMarca, ls_oidCanal);

   --(1) PROCESAMOS A LOS CLIENTES NUEVOS
    ls_Proceso := 'Procesando Clientes Nuevos..';
    OPEN c_clientesNuevos(ls_oidPais, psCodigoTipoCliente);
    LOOP
       FETCH c_clientesNuevos BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
       IF interfazRecordN.COUNT > 0 THEN

          FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
              ls_oidCliente :=  interfazRecordN(x).oidCliente;
              ls_oidSubTipoCliente :=  interfazRecordN(x).oidSubTipoCliente;
              ls_oidClienteSubTipo :=  interfazRecordN(x).oidClienteSubTipo;
              ls_codEstadoCliente :=  interfazRecordN(x).codEstadoCliente;

              --Obtenemos el tipo de clasificiacion y Clasificacion relacionado al subtipo del cliente
              --la cual se le agregara como otra clasificacion para el cliente
              SELECT cla.OID_TIPO_CLAS, cla.OID_CLAS
               INTO  ls_oidTipoClas, ls_oidClas
                FROM mae_clasi_actua cla
               WHERE cla.OID_SUBT_CLIE = ls_oidSubTipoCliente
                 AND cla.CLA_COD = '1';

              --Se debe eliminar cualquier registro anterior que tenga la consultora para las clasificaciones
              --de consultoras antiguas para lo cual se debe buscar el registro en MAE_CLIEN_CLASI
              --que corresponda con el tipo de clasificacion inscrito en la tabla MAE_CLASI_ACTUA con codigo '2'
              DELETE FROM MAE_CLIEN_CLASI
              WHERE  OID_CLIE_CLAS IN
                     (SELECT OID_CLIE_CLAS
                      FROM   MAE_CLIEN_CLASI cls, MAE_CLIEN_TIPO_SUBTI sub, MAE_CLASI_ACTUA cla
                      WHERE  cls.CTSU_OID_CLIE_TIPO_SUBT = sub.OID_CLIE_TIPO_SUBT
                        AND  sub.CLIE_OID_CLIE = ls_oidCliente
                        AND  cla.OID_TIPO_CLAS = cls.TCCL_OID_TIPO_CLASI
                        AND  cla.CLA_COD = '2');

              --Verificamos si para el cliente ya tiene asignado una clasificacion principal
              SELECT DECODE(COUNT(cla.oid_clie_clas), 0, 1, 0)
              INTO   ls_indFlagClas
              FROM   MAE_CLIEN_CLASI cla, MAE_CLIEN_TIPO_SUBTI sub
              WHERE  sub.clie_oid_clie = ls_oidCliente
                AND  cla.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
                AND  cla.ind_ppal = '1';

              --Insertamos la clasificacion de Consultoras Nuevas para el cliente
              v_oidsubTipoCli.EXTEND(1);
              v_clasificacion.EXTEND(1);
              v_periodo.EXTEND(1);
              v_tipoClasi.EXTEND(1);
              v_fechaClasi.EXTEND(1);
              v_indPrincipal.EXTEND(1);

              v_oidsubTipoCli(v_oidsubTipoCli.COUNT) := ls_oidClienteSubTipo;
              v_clasificacion(v_clasificacion.COUNT) := ls_oidClas;
              v_periodo(v_periodo.COUNT) := ls_oidPeriodo;
              v_tipoClasi(v_tipoClasi.COUNT) := ls_oidTipoClas;
              v_fechaClasi(v_fechaClasi.COUNT) := trunc(SYSDATE);
              v_indPrincipal(v_indPrincipal.COUNT) := ls_indFlagClas;

          END LOOP;
       END IF;
       EXIT WHEN c_clientesNuevos%NOTFOUND;
    END LOOP;
    CLOSE c_clientesNuevos;

    --Insertamos la clasificacion de Consultoras Nuevas para el cliente
    FORALL i IN 1..v_oidsubTipoCli.COUNT
      INSERT INTO MAE_CLIEN_CLASI
        (OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT, CLAS_OID_CLAS,
         PERD_OID_PERI, TCCL_OID_TIPO_CLASI, FEC_CLAS, IND_PPAL)
      VALUES
         (MAE_CLCL_SEQ.nextval, v_oidsubTipoCli(i), v_clasificacion(i),
          v_periodo(i), v_tipoClasi(i), v_fechaClasi(i), v_indPrincipal(i));


    --Recuperamos el periodo a evaluar
    BEGIN
        SELECT a.periodo
        INTO ls_codigoPeriodoEvaluar
        FROM (SELECT ROWNUM ID, periodo
              FROM (SELECT   cod_peri periodo
                        FROM SEG_PERIO_CORPO cor,
                              CRA_PERIO cra
                       WHERE cor.oid_peri = cra.peri_oid_peri
                         AND cor.cod_peri <= psCodigoPeriodo
                    ORDER BY cod_peri DESC)) a
        WHERE a.ID = psNumeroCampanias + 1;

    EXCEPTION
      WHEN OTHERS THEN
       RAISE_APPLICATION_ERROR(-20123, 'El periodo a evaluar no existe para el periodo y el numero de campa?as seleccionados.');
    END;

    ls_oidPeriodoEvaluar := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(ls_codigoPeriodoEvaluar, ls_oidMarca, ls_oidCanal);

    --(2) PROCESAMOS A LOS CLIENTES ANTIGUOS
    ls_Proceso := 'Procesando Clientes Antiguos..';
    v_oidClasiCli.DELETE;
    v_oidsubTipoCli.DELETE;
    v_clasificacion.DELETE;
    v_periodo.DELETE;
    v_tipoClasi.DELETE;
    v_fechaClasi.DELETE;
    v_indPrincipal.DELETE;

    OPEN c_clientesAntiguos(ls_oidPais, psCodigoTipoCliente, ls_oidPeriodoEvaluar);
    LOOP
       FETCH c_clientesAntiguos BULK COLLECT INTO interfazRecordA LIMIT W_FILAS;
       IF interfazRecordA.COUNT > 0 THEN
          FOR x IN interfazRecordA.FIRST .. interfazRecordA.LAST LOOP
              ls_oidCliente :=  interfazRecordA(x).oidCliente;
              ls_oidClienteClasi :=   interfazRecordA(x).oidClienteClasi;
              ls_oidSubTipoCliente :=  interfazRecordA(x).oidSubTipoCliente;

             --Obtenemos el tipo de clasificiacion y Clasificacion relacionado al subtipo del cliente
             --la cual se le actualizara la clasificacion para el cliente
             SELECT cla.OID_TIPO_CLAS, cla.OID_CLAS
             INTO  ls_oidTipoClas, ls_oidClas
             FROM  MAE_CLASI_ACTUA cla
             WHERE cla.OID_SUBT_CLIE = ls_oidSubTipoCliente
               AND cla.CLA_COD = '2';

             --Actualizamos la clasificacion de Consultoras Antiguas para el cliente
              v_oidClasiCli.EXTEND(1);
              v_clasificacion.EXTEND(1);
              v_tipoClasi.EXTEND(1);
              v_periodo.EXTEND(1);

              v_oidClasiCli(v_oidClasiCli.COUNT) := ls_oidClienteClasi;
              v_clasificacion(v_clasificacion.COUNT) := ls_oidClas;
              v_tipoClasi(v_periodo.COUNT) := ls_oidTipoClas;
              v_periodo(v_tipoClasi.COUNT) := ls_oidPeriodo;

          END LOOP;
       END IF;
       EXIT WHEN c_clientesAntiguos%NOTFOUND;
    END LOOP;
    CLOSE c_clientesAntiguos;

    --Actualizamos la clasificacion de Consultoras Antiguas para el cliente
    FORALL i IN 1..v_oidClasiCli.COUNT
      UPDATE MAE_CLIEN_CLASI
        SET CLAS_OID_CLAS = v_clasificacion(i),
            TCCL_OID_TIPO_CLASI = v_tipoClasi(i),
            PERD_OID_PERI = v_periodo(i)
      WHERE OID_CLIE_CLAS = v_oidClasiCli(i);


    --Validamos si el pais es de tipo ESIKA o LBEL
    SELECT DECODE (SUBSTR (psCodigoPais, 3, 1),NULL, 'E',SUBSTR (psCodigoPais, 3, 1))
  	INTO   ls_tipoPais
  	FROM DUAL;

    --SOLO PARA EL CASO QUE EL PAIS SEA 'ESIKA'
    IF (ls_tipoPais = 'E') THEN
      --RECUPERAMOS EL PERIODO LBEL
      SELECT COD_PERI
      INTO   ls_PeriodoLEbel
      FROM   MAE_CLASI_PERIO_LBEL;

      IF(ls_PeriodoLEBel IS NULL) THEN
          RAISE_APPLICATION_ERROR(-20123, 'El periodo LBEL no esta definido.');
      END IF;

      --(3) ELIMINAMOS LOS TIPOS DE CLASIFICACION A LAS CONSULTORAS L'EBEL
      ls_Proceso := 'Eliminado Consultoras LBel..';
      OPEN c_clientesLBELxEliminar(ls_oidPais, psCodigoTipoCliente, ls_PeriodoLEbel);
      LOOP
         FETCH c_clientesLBELxEliminar BULK COLLECT INTO interfazRecordE LIMIT W_FILAS;
         IF interfazRecordE.COUNT > 0 THEN
            FOR x IN interfazRecordE.FIRST .. interfazRecordE.LAST LOOP
                ls_oidClienteClasi :=   interfazRecordE(x).oidClienteClasi;
                ls_oidCliente :=  interfazRecordE(x).oidCliente;

                --Eliminamos la clasificacion del Cliente
                DELETE FROM MAE_CLIEN_CLASI
                WHERE  OID_CLIE_CLAS = ls_oidClienteClasi;

                --Verificamos si para el cliente ya tiene asignado una clasificacion principal
                SELECT DECODE(COUNT(cla.oid_clie_clas), 0, 0, 1)
                INTO   ls_indFlagClas
                FROM   MAE_CLIEN_CLASI cla, MAE_CLIEN_TIPO_SUBTI sub
                WHERE  sub.clie_oid_clie = ls_oidCliente
                  AND  cla.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
                  AND  cla.ind_ppal = '1';

                --Si no tiene clasificacion principal para el cliente, se actualiza el indicador
                --para el registro mas antiguo de la consultora
                IF (ls_indFlagClas = 0 ) THEN
                  BEGIN
                    SELECT oid_clie_clas
                    INTO   ls_oidClienteClasi
                    FROM   (SELECT cla.oid_clie_clas
                            FROM   MAE_CLIEN_CLASI cla, MAE_CLIEN_TIPO_SUBTI sub
                            WHERE  sub.clie_oid_clie = ls_oidCliente
                              AND  cla.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
                            ORDER  BY oid_clie_clas ASC)
                    WHERE  ROWNUM = 1;
                	EXCEPTION
                	  WHEN NO_DATA_FOUND THEN
                   	  ls_oidClienteClasi := null;
                	END;

                  IF (ls_oidClienteClasi IS NOT NULL) THEN
                    UPDATE MAE_CLIEN_CLASI
                       SET IND_PPAL = 1
                     WHERE oid_clie_clas = ls_oidClienteClasi;
                  END IF;
                END IF;

            END LOOP;
         END IF;
         EXIT WHEN c_clientesLBELxEliminar%NOTFOUND;
      END LOOP;
      CLOSE c_clientesLBELxEliminar;


      --(4) AGREGAMOS TIPOS DE CLASIFICACION A LAS CONSULTORAS L'EBEL
      ls_Proceso := 'Insertando Consultoras LBel..';
      v_oidClasiCli.DELETE;
      v_oidsubTipoCli.DELETE;
      v_clasificacion.DELETE;
      v_periodo.DELETE;
      v_tipoClasi.DELETE;
      v_fechaClasi.DELETE;
      v_indPrincipal.DELETE;

			-- Obtiene el oid en base al codigo
			select b.oid_subt_clie
        into ls_oidSubTipo
        from MAE_SUBTI_CLIEN b
       where b.cod_subt_clie = '06';

      OPEN c_clientesLBELxInsertar(ls_oidPais, psCodigoTipoCliente, ls_oidSubTipo);
      LOOP
         FETCH c_clientesLBELxInsertar BULK COLLECT INTO interfazRecordLBelN LIMIT W_FILAS;
         IF interfazRecordLBelN.COUNT > 0 THEN
            FOR x IN interfazRecordLBelN.FIRST .. interfazRecordLBelN.LAST LOOP
                ls_oidCliente :=  interfazRecordLBelN(x).oidCliente;
                ls_oidSubTipoCliente :=  interfazRecordLBelN(x).oidSubTipoCliente;
                ls_oidClienteSubTipo :=  interfazRecordLBelN(x).oidClienteSubTipo;

                --Obtenemos el tipo de clasificiacion y Clasificacion relacionado al subtipo del cliente
                --la cual se le agregara como otra clasificacion para el cliente
                SELECT cla.OID_TIPO_CLAS, cla.OID_CLAS
                 INTO  ls_oidTipoClas, ls_oidClas
                 FROM  MAE_CLASI_ACTUA cla
                WHERE cla.OID_SUBT_CLIE = ls_oidSubTipoCliente
                  AND cla.CLA_COD = '3';

                --Verificamos si para el cliente ya tiene asignado una clasificacion principal
                SELECT DECODE(COUNT(cla.oid_clie_clas), 0, 1, 0)
                INTO   ls_indFlagClas
                FROM   mae_clien_clasi cla, mae_clien_tipo_subti sub
                WHERE  sub.clie_oid_clie = ls_oidCliente
                  AND  cla.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
                  AND  cla.ind_ppal = '1';

                --Insertamos la clasificacion de Consultoras Nuevas LBEL para el cliente
                v_oidsubTipoCli.EXTEND(1);
                v_clasificacion.EXTEND(1);
                v_periodo.EXTEND(1);
                v_tipoClasi.EXTEND(1);
                v_fechaClasi.EXTEND(1);
                v_indPrincipal.EXTEND(1);

                v_oidsubTipoCli(v_oidsubTipoCli.COUNT) := ls_oidClienteSubTipo;
                v_clasificacion(v_clasificacion.COUNT) := ls_oidClas;
                v_periodo(v_periodo.COUNT) := ls_oidPeriodo;
                v_tipoClasi(v_tipoClasi.COUNT) := ls_oidTipoClas;
                v_fechaClasi(v_fechaClasi.COUNT) := trunc(SYSDATE);
                v_indPrincipal(v_indPrincipal.COUNT) := ls_indFlagClas;

            END LOOP;
         END IF;
         EXIT WHEN c_clientesLBELxInsertar%NOTFOUND;
      END LOOP;
      CLOSE c_clientesLBELxInsertar;

      --Insertamos la clasificacion de Consultoras Nuevas LBEL para el cliente
      FORALL i IN 1..v_oidsubTipoCli.COUNT
        INSERT INTO MAE_CLIEN_CLASI
          (OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT, CLAS_OID_CLAS,
           PERD_OID_PERI, TCCL_OID_TIPO_CLASI, FEC_CLAS, IND_PPAL)
        VALUES
           (MAE_CLCL_SEQ.nextval, v_oidsubTipoCli(i), v_clasificacion(i),
            v_periodo(i), v_tipoClasi(i), v_fechaClasi(i), v_indPrincipal(i));


      --(5) SE INSERTA EL TIPO DE CLASIFICACION A TODAS LAS CONSULTORAS INHABILITADAS PARA VENDER LBEL
      ls_Proceso := 'Insertando Consultoras Inhabilitadas LBel..';
      v_oidClasiCli.DELETE;
      v_oidsubTipoCli.DELETE;
      v_clasificacion.DELETE;
      v_periodo.DELETE;
      v_tipoClasi.DELETE;
      v_fechaClasi.DELETE;
      v_indPrincipal.DELETE;

      OPEN c_clientesLBELInhabxInsertar(ls_oidPais, psCodigoTipoCliente);
      LOOP
         FETCH c_clientesLBELInhabxInsertar BULK COLLECT INTO interfazRecordLBelN LIMIT W_FILAS;
         IF interfazRecordLBelN.COUNT > 0 THEN
            FOR x IN interfazRecordLBelN.FIRST .. interfazRecordLBelN.LAST LOOP
                ls_oidCliente :=  interfazRecordLBelN(x).oidCliente;
                ls_oidSubTipoCliente :=  interfazRecordLBelN(x).oidSubTipoCliente;
                ls_oidClienteSubTipo :=  interfazRecordLBelN(x).oidClienteSubTipo;

                --Obtenemos el tipo de clasificiacion y Clasificacion relacionado al subtipo del cliente
                --la cual se le agregara como otra clasificacion para el cliente
                SELECT cla.OID_TIPO_CLAS, cla.OID_CLAS
                 INTO  ls_oidTipoClas, ls_oidClas
                 FROM  MAE_CLASI_ACTUA cla
                WHERE cla.OID_SUBT_CLIE = ls_oidSubTipoCliente
                  AND cla.CLA_COD = '4';

                --Verificamos si para el cliente ya tiene asignado una clasificacion principal
                SELECT DECODE(COUNT(cla.oid_clie_clas), 0, 1, 0)
                INTO   ls_indFlagClas
                FROM   mae_clien_clasi cla, mae_clien_tipo_subti sub
                WHERE  sub.clie_oid_clie = ls_oidCliente
                  AND  cla.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
                  AND  cla.ind_ppal = '1';

                --Insertamos la clasificacion de Consultoras Nuevas LBEL Inhabilitadas
                v_oidsubTipoCli.EXTEND(1);
                v_clasificacion.EXTEND(1);
                v_periodo.EXTEND(1);
                v_tipoClasi.EXTEND(1);
                v_fechaClasi.EXTEND(1);
                v_indPrincipal.EXTEND(1);

                v_oidsubTipoCli(v_oidsubTipoCli.COUNT) := ls_oidClienteSubTipo;
                v_clasificacion(v_clasificacion.COUNT) := ls_oidClas;
                v_periodo(v_periodo.COUNT) := ls_oidPeriodo;
                v_tipoClasi(v_tipoClasi.COUNT) := ls_oidTipoClas;
                v_fechaClasi(v_fechaClasi.COUNT) := trunc(SYSDATE);
                v_indPrincipal(v_indPrincipal.COUNT) := ls_indFlagClas;

            END LOOP;
         END IF;
         EXIT WHEN c_clientesLBELInhabxInsertar%NOTFOUND;
      END LOOP;
      CLOSE c_clientesLBELInhabxInsertar;

      --Insertamos la clasificacion de Consultoras Nuevas LBEL Inhabilitadas
      FORALL i IN 1..v_oidsubTipoCli.COUNT
        INSERT INTO MAE_CLIEN_CLASI
          (OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT, CLAS_OID_CLAS,
           PERD_OID_PERI, TCCL_OID_TIPO_CLASI, FEC_CLAS, IND_PPAL)
        VALUES
           (MAE_CLCL_SEQ.nextval, v_oidsubTipoCli(i), v_clasificacion(i),
            v_periodo(i), v_tipoClasi(i), v_fechaClasi(i), v_indPrincipal(i));

    END IF;

   --(6) PROCESAMOS A LOS CLIENTES PROGRAMA SUSCRIPCION CATALOGOS
    ls_Proceso := 'Procesando Clientes Suscripcion Catalogos..';
    v_oidClasiCli.DELETE;
    v_oidsubTipoCli.DELETE;
    v_clasificacion.DELETE;
    v_periodo.DELETE;
    v_tipoClasi.DELETE;
    v_fechaClasi.DELETE;
    v_indPrincipal.DELETE;

    --Recuperamos el Periodo de aca a 3 campa?as anteriores
    lsCodPeriodoAux := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                               ls_oidPais, ls_oidMarca, ls_oidCanal,-3);
    lnOidPeriodoAux := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoAux,
                               ls_oidMarca, ls_oidCanal);

    OPEN c_clientesSuscripcionCatalogos(ls_oidPais, psCodigoTipoCliente, lnOidPeriodoAux);
    LOOP
       FETCH c_clientesSuscripcionCatalogos BULK COLLECT INTO interfazRecordS LIMIT W_FILAS;
       IF interfazRecordS.COUNT > 0 THEN

          FOR x IN interfazRecordS.FIRST .. interfazRecordS.LAST LOOP
              ls_oidCliente :=  interfazRecordS(x).oidCliente;
              ls_oidSubTipoCliente :=  interfazRecordS(x).oidSubTipoCliente;
              ls_oidClienteSubTipo :=  interfazRecordS(x).oidClienteSubTipo;
              ls_oidEstadoCliente :=  interfazRecordS(x).oidEstadoCliente;

              --Obtenemos el tipo de clasificiacion y Clasificacion relacionado al subtipo del cliente
              --la cual se le agregara como otra clasificacion para el cliente
              SELECT cla.OID_TIPO_CLAS, cla.OID_CLAS
               INTO  ls_oidTipoClas, ls_oidClas
                FROM mae_clasi_actua cla
               WHERE cla.OID_SUBT_CLIE = ls_oidSubTipoCliente
                 AND cla.CLA_COD = '5';

              --Verificamos si para el cliente ya tiene asignado una clasificacion principal
              SELECT DECODE(COUNT(cla.oid_clie_clas), 0, 1, 0)
              INTO   ls_indFlagClas
              FROM   MAE_CLIEN_CLASI cla, MAE_CLIEN_TIPO_SUBTI sub
              WHERE  sub.clie_oid_clie = ls_oidCliente
                AND  cla.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
                AND  cla.ind_ppal = '1';

              --Insertamos la clasificacion de Consultoras Nuevas para el cliente
              v_oidsubTipoCli.EXTEND(1);
              v_clasificacion.EXTEND(1);
              v_periodo.EXTEND(1);
              v_tipoClasi.EXTEND(1);
              v_fechaClasi.EXTEND(1);
              v_indPrincipal.EXTEND(1);

              v_oidsubTipoCli(v_oidsubTipoCli.COUNT) := ls_oidClienteSubTipo;
              v_clasificacion(v_clasificacion.COUNT) := ls_oidClas;
              v_periodo(v_periodo.COUNT) := ls_oidPeriodo;
              v_tipoClasi(v_tipoClasi.COUNT) := ls_oidTipoClas;
              v_fechaClasi(v_fechaClasi.COUNT) := trunc(SYSDATE);
              v_indPrincipal(v_indPrincipal.COUNT) := ls_indFlagClas;

          END LOOP;
       END IF;
       EXIT WHEN c_clientesSuscripcionCatalogos%NOTFOUND;
    END LOOP;
    CLOSE c_clientesSuscripcionCatalogos;

    --Insertamos la clasificacion de Consultoras Nuevas para el cliente
    FORALL i IN 1..v_oidsubTipoCli.COUNT
      INSERT INTO MAE_CLIEN_CLASI
        (OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT, CLAS_OID_CLAS,
         PERD_OID_PERI, TCCL_OID_TIPO_CLASI, FEC_CLAS, IND_PPAL)
      VALUES
         (MAE_CLCL_SEQ.nextval, v_oidsubTipoCli(i), v_clasificacion(i),
          v_periodo(i), v_tipoClasi(i), v_fechaClasi(i), v_indPrincipal(i));


    --(7) ELIMINAMOS A LAS CONSULTORAS RETIRADAS
    ls_Proceso := 'Eliminando Clientes Retiradas con Suscripcion Catalogos..';

    --Para el caso de los cliente retirados (estatus 7) se puede contar
    --con un registro anterior correspondiente a la clasificacion de Suscripcion de Catalogos,
    --el cual se debe eliminar para lo cual se debe buscar el registro en MAE_CLIEN_CLASI
    --que corresponda con el tipo de clasificacion inscrito en la tabla MAE_CLASI_ACTUA con codigo '5'
    DELETE FROM MAE_CLIEN_CLASI
    WHERE  OID_CLIE_CLAS IN (
                            SELECT cls.OID_CLIE_CLAS
                              FROM MAE_CLIEN cli,
                                   MAE_CLIEN_DATOS_ADICI adi,
                                   MAE_ESTAT_CLIEN est,
                                   MAE_CLIEN_TIPO_SUBTI sub,
                                   MAE_TIPO_CLIEN mtc,
                                   MAE_CLASI_ACTUA cla,
                            	   MAE_CLIEN_CLASI cls
                             WHERE mtc.cod_tipo_clie = psCodigoTipoCliente
                               AND cls.ctsu_oid_clie_tipo_subt = sub.oid_clie_tipo_subt
                               AND sub.ticl_oid_tipo_clie = mtc.oid_tipo_clie
                               AND sub.clie_oid_clie = cli.oid_clie
                               AND cli.pais_oid_pais = ls_oidPais
                               AND adi.clie_oid_clie = cli.oid_clie
                               AND adi.esta_oid_esta_clie = est.oid_esta_clie
                               AND est.cod_esta_clie = '07'
                               AND sub.sbti_oid_subt_clie = cla.oid_subt_clie
                               AND cla.cla_cod = '5'
                               AND cls.tccl_oid_tipo_clasi = cla.oid_tipo_clas
                               and cls.clas_oid_clas <> 2169); --No elimina a la suscripcion de desactivacion

    ---------------------------------------------------------------------------------------------------
    --(8) PROCESAMOS A LOS CLIENTES SEGMENTACION A - CYZONE
    ls_Proceso := 'Procesando Clientes Segmentacion A - Cyzone..';
    OPEN c_clientesSegmentacion(ls_oidPais, psCodigoTipoCliente);
    LOOP
       FETCH c_clientesSegmentacion BULK COLLECT INTO interfazRecordSeg LIMIT W_FILAS;
       IF interfazRecordSeg.COUNT > 0 THEN

          FOR x IN interfazRecordSeg.FIRST .. interfazRecordSeg.LAST LOOP
              ls_log := interfazRecordSeg(x).oidSubTipoCliente ||' '||
                        interfazRecordSeg(x).codClasi ||' '||
                        ls_oidPeriodo ||' '||
                        interfazRecordSeg(x).oidTipoClasi;

              INSERT INTO MAE_CLIEN_CLASI(OID_CLIE_CLAS,
                                          CTSU_OID_CLIE_TIPO_SUBT,
                                          CLAS_OID_CLAS,
                                          PERD_OID_PERI,
                                          TCCL_OID_TIPO_CLASI,
                                          FEC_CLAS,
                                          IND_PPAL)
              VALUES(MAE_CLCL_SEQ.nextval,
                     interfazRecordSeg(x).oidSubTipoCliente,
                     interfazRecordSeg(x).codClasi,
                     ls_oidPeriodo,
                     interfazRecordSeg(x).oidTipoClasi,
                     trunc(SYSDATE),
                     (decode((SELECT COUNT(1)
                               FROM MAE_CLIEN_CLASI cls,
                                    MAE_CLIEN_TIPO_SUBTI sub
                              WHERE cls.CTSU_OID_CLIE_TIPO_SUBT = sub.OID_CLIE_TIPO_SUBT
                                AND sub.CLIE_OID_CLIE = interfazRecordSeg(x).oidCliente
                                AND cls.Ind_Ppal = 1),0,1,0
                     )));

          END LOOP;
       END IF;
       EXIT WHEN c_clientesSegmentacion%NOTFOUND;
    END LOOP;
    CLOSE c_clientesSegmentacion;
    ---------------------------------------------------------------------------------------------------

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_CLASI_CLIEN: ('|| ls_Proceso || ' -- SEGMENTACION :' || ls_log || ',' || TO_CHAR(ls_oidCliente) || ')' || ls_sqlerrm );
END MAE_PR_ACTUA_CLASI_CLIEN;

/***************************************************************************
Descripcion       : Verifica si el codigo de Cliente existe en el esquema de
                    LBEL relacionado al mismo pais
Fecha Creacion    : 14/03/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION MAE_FN_VERIF_CLIEN_LBEL
  (psOidCliente    NUMBER) RETURN NUMBER
IS
  ls_FlagEncontrado NUMBER:=0;
  ls_tipoDocumento  MAE_TIPO_DOCUM.COD_TIPO_DOCU%TYPE;
  ls_NroDocumento   MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE;
BEGIN
  --Recuperamos el tipo y numero de documento de la consultora
  SELECT doc.cod_tipo_docu, ide.num_docu_iden
  INTO   ls_TipoDocumento, ls_NroDocumento
  FROM   MAE_CLIEN_IDENT ide, MAE_TIPO_DOCUM doc
  WHERE  ide.clie_oid_clie = psOidCliente
    AND  ide.tdoc_oid_tipo_docu = doc.oid_tipo_docu
    and  ide.val_iden_docu_prin = 1;


  --Buscamos si se encuentra registrado en el esquema de LBEL del mismo pais
  SELECT DECODE(count(*), 0, 0, 1)
  INTO   ls_FlagEncontrado
  FROM   MAE_CLIEN_IDENT_LBEL idel, MAE_TIPO_DOCUM docl
  WHERE  idel.tdoc_oid_tipo_docu = docl.oid_tipo_docu
    AND  docl.cod_tipo_docu = ls_TipoDocumento
    AND  idel.num_docu_iden = ls_NroDocumento;

  RETURN ls_FlagEncontrado;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_VERIF_CLIEN_LBEL('|| psOidCliente || '):'||ls_sqlerrm);
END MAE_FN_VERIF_CLIEN_LBEL;

/***************************************************************************
Descripcion       : Actualiza Clasificacion LOVE de Clientes
Fecha Creacion    : 14/09/2009
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CLASI_LOVE
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoPeriodo            VARCHAR2)
IS
  lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo                CRA_PERIO.OID_PERI%TYPE;
BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  --identifica a las consultoras que no tienen las clasificaciones de LOVE y que pertenecen a las regiones del programas
  --e inserta su respectiva clasificacion
  INSERT INTO MAE_CLIEN_CLASI
    (OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT,
     CLAS_OID_CLAS, TCCL_OID_TIPO_CLASI,
     PERD_OID_PERI, FEC_CLAS, IND_PPAL)
  SELECT MAE_CLCL_SEQ.nextval, A.OID_CLIE_TIPO_SUBT,
         (SELECT OID_CLAS FROM MAE_LOVE_CLASI
           WHERE OID_SUBT_CLIE = A.SBTI_OID_SUBT_CLIE
             AND VAL_DEFE = 1),
         (SELECT OID_TIPO_CLAS FROM MAE_LOVE_CLASI
           WHERE OID_SUBT_CLIE = A.SBTI_OID_SUBT_CLIE
             AND VAL_DEFE = 1),
         lnOidPeriodo,  trunc(SYSDATE), '0'
    FROM MAE_CLIEN_TIPO_SUBTI A,
         MAE_CLIEN_UNIDA_ADMIN B,
         ZON_TERRI_ADMIN C,
         ZON_SECCI D,
         ZON_ZONA E,
         ZON_REGIO F,
         MAE_LOVE_REGIO G,
         MAE_TIPO_CLIEN H,
         (SELECT DISTINCT OID_SUBT_CLIE FROM MAE_LOVE_CLASI) I
   WHERE A.CLIE_OID_CLIE = B.CLIE_OID_CLIE
     AND A.TICL_OID_TIPO_CLIE = H.OID_TIPO_CLIE
     AND H.COD_TIPO_CLIE = '02'
     AND B.IND_ACTI = 1
     AND B.ZTAD_OID_TERR_ADMI = C.OID_TERR_ADMI
     AND C.ZSCC_OID_SECC = D.OID_SECC
     AND D.ZZON_OID_ZONA = E.OID_ZONA
     AND E.ZORG_OID_REGI = F.OID_REGI
     AND F.OID_REGI = G.OID_REGI
     AND A.SBTI_OID_SUBT_CLIE = I.OID_SUBT_CLIE
     AND A.CLIE_OID_CLIE NOT IN
                              (SELECT M.CLIE_OID_CLIE
                                 FROM MAE_CLIEN_TIPO_SUBTI M,
                                      MAE_CLIEN_CLASI N,
                                      MAE_LOVE_CLASI O
                                WHERE M.CLIE_OID_CLIE = A.CLIE_OID_CLIE
                                  AND M.OID_CLIE_TIPO_SUBT = N.CTSU_OID_CLIE_TIPO_SUBT
                                  AND N.TCCL_OID_TIPO_CLASI = O.OID_TIPO_CLAS);

  --Se eliminan las consultoras que no estan dentro de las regiones del programa y que tienen la clasificacion LOVE
  DELETE MAE_CLIEN_CLASI O
   WHERE O.OID_CLIE_CLAS IN
        (SELECT Y.OID_CLIE_CLAS
           FROM MAE_CLIEN_TIPO_SUBTI X,
                MAE_CLIEN_CLASI Y,
                MAE_LOVE_CLASI Z,
                MAE_TIPO_CLIEN H,
                (SELECT B.CLIE_OID_CLIE
                       FROM MAE_CLIEN_UNIDA_ADMIN B,
                            ZON_TERRI_ADMIN C,
                            ZON_SECCI D,
                            ZON_ZONA E,
                            (SELECT OID_REGI FROM ZON_REGIO WHERE OID_REGI NOT IN (SELECT OID_REGI FROM MAE_LOVE_REGIO)) F
                      WHERE B.IND_ACTI = 1
                        AND B.ZTAD_OID_TERR_ADMI = C.OID_TERR_ADMI
                        AND C.ZSCC_OID_SECC = D.OID_SECC
                        AND D.ZZON_OID_ZONA = E.OID_ZONA
                        AND E.ZORG_OID_REGI = F.OID_REGI) T
          WHERE X.OID_CLIE_TIPO_SUBT = Y.CTSU_OID_CLIE_TIPO_SUBT
            AND Y.CLAS_OID_CLAS = Z.OID_CLAS
            AND X.CLIE_OID_CLIE = T.CLIE_OID_CLIE
            AND X.TICL_OID_TIPO_CLIE = H.OID_TIPO_CLIE
            AND H.COD_TIPO_CLIE = '02');


  --Eliminamos a las Consultoras que tengan estatus configurado en MAE_LOVE_ESTAT_PRINT y
  --cuya clasificacion este asociada el programa LOV y que NO pertenezca a las regiones de LOVE
  DELETE FROM MAE_CLIEN_CLASI
   WHERE OID_CLIE_CLAS IN (
                  SELECT M.OID_CLIE_CLAS
                    FROM MAE_CLIEN_TIPO_SUBTI A,
                         MAE_CLIEN_UNIDA_ADMIN B,
                         ZON_TERRI_ADMIN C,
                         ZON_SECCI D,
                         ZON_ZONA E,
                         ZON_REGIO F,
                         (SELECT OID_REGI FROM ZON_REGIO WHERE OID_REGI NOT IN (SELECT OID_REGI FROM MAE_LOVE_REGIO)) G,
                         MAE_TIPO_CLIEN H,
                         MAE_LOVE_CLASI_PRINT I,
                         MAE_CLIEN_DATOS_ADICI J,
                         MAE_LOVE_ESTAT_PRINT K,
                         MAE_CLIEN_CLASI M
                   WHERE A.CLIE_OID_CLIE = B.CLIE_OID_CLIE
                     AND A.TICL_OID_TIPO_CLIE = H.OID_TIPO_CLIE
                     AND H.COD_TIPO_CLIE = '02'
                     AND B.IND_ACTI = 1
                     AND B.ZTAD_OID_TERR_ADMI = C.OID_TERR_ADMI
                     AND C.ZSCC_OID_SECC = D.OID_SECC
                     AND D.ZZON_OID_ZONA = E.OID_ZONA
                     AND E.ZORG_OID_REGI = F.OID_REGI
                     AND F.OID_REGI = G.OID_REGI
                     AND A.SBTI_OID_SUBT_CLIE = I.OID_SUBT_CLIE
                     AND A.CLIE_OID_CLIE = J.CLIE_OID_CLIE
                     AND J.ESTA_OID_ESTA_CLIE = K.OID_ESTA
                     AND A.OID_CLIE_TIPO_SUBT = M.CTSU_OID_CLIE_TIPO_SUBT
                     AND M.CLAS_OID_CLAS = I.OID_CLAS
                     AND I.TIP_PROG = 'LOV');

  --Eliminamos a las Consultoras que tengan estatus configurado en MAE_LOVE_ESTAT_PRINT y
  --cuya clasificacion este asociada el programa REG y que pertenezca a las regiones de LOVE
  DELETE FROM MAE_CLIEN_CLASI
   WHERE OID_CLIE_CLAS IN (
                  SELECT M.OID_CLIE_CLAS
                    FROM MAE_CLIEN_TIPO_SUBTI A,
                         MAE_CLIEN_UNIDA_ADMIN B,
                         ZON_TERRI_ADMIN C,
                         ZON_SECCI D,
                         ZON_ZONA E,
                         ZON_REGIO F,
                         MAE_LOVE_REGIO G,
                         MAE_TIPO_CLIEN H,
                         MAE_LOVE_CLASI_PRINT I,
                         MAE_CLIEN_DATOS_ADICI J,
                         MAE_LOVE_ESTAT_PRINT K,
                         MAE_CLIEN_CLASI M
                   WHERE A.CLIE_OID_CLIE = B.CLIE_OID_CLIE
                     AND A.TICL_OID_TIPO_CLIE = H.OID_TIPO_CLIE
                     AND H.COD_TIPO_CLIE = '02'
                     AND B.IND_ACTI = 1
                     AND B.ZTAD_OID_TERR_ADMI = C.OID_TERR_ADMI
                     AND C.ZSCC_OID_SECC = D.OID_SECC
                     AND D.ZZON_OID_ZONA = E.OID_ZONA
                     AND E.ZORG_OID_REGI = F.OID_REGI
                     AND F.OID_REGI = G.OID_REGI
                     AND A.SBTI_OID_SUBT_CLIE = I.OID_SUBT_CLIE
                     AND A.CLIE_OID_CLIE = J.CLIE_OID_CLIE
                     AND J.ESTA_OID_ESTA_CLIE = K.OID_ESTA
                     AND A.OID_CLIE_TIPO_SUBT = M.CTSU_OID_CLIE_TIPO_SUBT
                     AND M.CLAS_OID_CLAS = I.OID_CLAS
                     AND I.TIP_PROG = 'REG');

  --identifica a las consultoras nuevas para actualizar en el programa de nuevas LOVE
  --e inserta su respectiva clasificacion
  INSERT INTO MAE_CLIEN_CLASI
    (OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT,
     CLAS_OID_CLAS, TCCL_OID_TIPO_CLASI,
     PERD_OID_PERI, FEC_CLAS, IND_PPAL)
  SELECT MAE_CLCL_SEQ.nextval, A.OID_CLIE_TIPO_SUBT,
         (SELECT OID_CLAS FROM MAE_LOVE_CLASI_PRINT
           WHERE OID_SUBT_CLIE = A.SBTI_OID_SUBT_CLIE
             AND TIP_PROG = 'LOV'),
         (SELECT OID_TIPO_CLAS FROM MAE_LOVE_CLASI_PRINT
           WHERE OID_SUBT_CLIE = A.SBTI_OID_SUBT_CLIE
             AND TIP_PROG = 'LOV'),
         lnOidPeriodo,  trunc(SYSDATE), '0'
    FROM MAE_CLIEN_TIPO_SUBTI A,
         MAE_CLIEN_UNIDA_ADMIN B,
         ZON_TERRI_ADMIN C,
         ZON_SECCI D,
         ZON_ZONA E,
         ZON_REGIO F,
         MAE_LOVE_REGIO G,
         MAE_TIPO_CLIEN H,
         (SELECT DISTINCT OID_SUBT_CLIE FROM MAE_LOVE_CLASI_PRINT) I,
         MAE_CLIEN_DATOS_ADICI J,
         MAE_LOVE_ESTAT_PRINT K
   WHERE A.CLIE_OID_CLIE = B.CLIE_OID_CLIE
     AND A.TICL_OID_TIPO_CLIE = H.OID_TIPO_CLIE
     AND H.COD_TIPO_CLIE = '02'
     AND B.IND_ACTI = 1
     AND B.ZTAD_OID_TERR_ADMI = C.OID_TERR_ADMI
     AND C.ZSCC_OID_SECC = D.OID_SECC
     AND D.ZZON_OID_ZONA = E.OID_ZONA
     AND E.ZORG_OID_REGI = F.OID_REGI
     AND F.OID_REGI = G.OID_REGI
     AND A.SBTI_OID_SUBT_CLIE = I.OID_SUBT_CLIE
     AND A.CLIE_OID_CLIE = J.CLIE_OID_CLIE
     AND J.ESTA_OID_ESTA_CLIE = K.OID_ESTA
     AND A.CLIE_OID_CLIE NOT IN
                              (SELECT M.CLIE_OID_CLIE
                                 FROM MAE_CLIEN_TIPO_SUBTI M,
                                      MAE_CLIEN_CLASI N,
                                      MAE_LOVE_CLASI_PRINT O
                                WHERE M.CLIE_OID_CLIE = A.CLIE_OID_CLIE
                                  AND M.OID_CLIE_TIPO_SUBT = N.CTSU_OID_CLIE_TIPO_SUBT
                                  AND N.TCCL_OID_TIPO_CLASI = O.OID_TIPO_CLAS
                                  AND O.TIP_PROG = 'LOV');

  --identifica a las consultoras nuevas para actualizar en el programa de nuevas REGULAR
  --e inserta su respectiva clasificacion
  INSERT INTO MAE_CLIEN_CLASI
    (OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT,
     CLAS_OID_CLAS, TCCL_OID_TIPO_CLASI,
     PERD_OID_PERI, FEC_CLAS, IND_PPAL)
  SELECT MAE_CLCL_SEQ.nextval, A.OID_CLIE_TIPO_SUBT,
         (SELECT OID_CLAS FROM MAE_LOVE_CLASI_PRINT
           WHERE OID_SUBT_CLIE = A.SBTI_OID_SUBT_CLIE
             AND TIP_PROG = 'REG'),
         (SELECT OID_TIPO_CLAS FROM MAE_LOVE_CLASI_PRINT
           WHERE OID_SUBT_CLIE = A.SBTI_OID_SUBT_CLIE
             AND TIP_PROG = 'REG'),
         lnOidPeriodo,  trunc(SYSDATE), '0'
    FROM MAE_CLIEN_TIPO_SUBTI A,
         MAE_CLIEN_UNIDA_ADMIN B,
         ZON_TERRI_ADMIN C,
         ZON_SECCI D,
         ZON_ZONA E,
         ZON_REGIO F,
         (SELECT OID_REGI FROM ZON_REGIO WHERE OID_REGI NOT IN (SELECT OID_REGI FROM MAE_LOVE_REGIO)) G,
         MAE_TIPO_CLIEN H,
         (SELECT DISTINCT OID_SUBT_CLIE FROM MAE_LOVE_CLASI_PRINT) I,
         MAE_CLIEN_DATOS_ADICI J,
         MAE_LOVE_ESTAT_PRINT K
   WHERE A.CLIE_OID_CLIE = B.CLIE_OID_CLIE
     AND A.TICL_OID_TIPO_CLIE = H.OID_TIPO_CLIE
     AND H.COD_TIPO_CLIE = '02'
     AND B.IND_ACTI = 1
     AND B.ZTAD_OID_TERR_ADMI = C.OID_TERR_ADMI
     AND C.ZSCC_OID_SECC = D.OID_SECC
     AND D.ZZON_OID_ZONA = E.OID_ZONA
     AND E.ZORG_OID_REGI = F.OID_REGI
     AND F.OID_REGI = G.OID_REGI
     AND A.SBTI_OID_SUBT_CLIE = I.OID_SUBT_CLIE
     AND A.CLIE_OID_CLIE = J.CLIE_OID_CLIE
     AND J.ESTA_OID_ESTA_CLIE = K.OID_ESTA
     AND A.CLIE_OID_CLIE NOT IN
                              (SELECT M.CLIE_OID_CLIE
                                 FROM MAE_CLIEN_TIPO_SUBTI M,
                                      MAE_CLIEN_CLASI N,
                                      MAE_LOVE_CLASI_PRINT O
                                WHERE M.CLIE_OID_CLIE = A.CLIE_OID_CLIE
                                  AND M.OID_CLIE_TIPO_SUBT = N.CTSU_OID_CLIE_TIPO_SUBT
                                  AND N.TCCL_OID_TIPO_CLASI = O.OID_TIPO_CLAS
                                  AND O.TIP_PROG = 'REG');

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_CLASI_LOVE: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_ACTUA_CLASI_LOVE;


/***************************************************************************
Descripcion       : Valida Carga de Clasificacion de Clientes
Fecha Creacion    : 24/05/2010
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_VALID_CARGA_CLASI
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER)
IS

CURSOR c_clientes IS
SELECT NUM_FILA, COD_CLIE, OID_SUBT_CLIE, OID_CLAS
  FROM MAE_CARGA_CLASI_CLIEN
 WHERE NUM_CARG = pnNumeroCarga;

  TYPE interfazClientes IS RECORD
  (
    numeroFila         MAE_CARGA_CLASI_CLIEN.NUM_FILA%TYPE,
    codigoCliente      MAE_CARGA_CLASI_CLIEN.COD_CLIE%TYPE,
    oidSubTipoCliente  MAE_CARGA_CLASI_CLIEN.OID_SUBT_CLIE%TYPE,
    oidClasificacion   MAE_CARGA_CLASI_CLIEN.OID_CLAS%TYPE
  );

  TYPE interfazClientesTab  IS TABLE OF interfazClientes;
  interfazRecordN interfazClientesTab;

  lnOidPais                  SEG_PAIS.OID_PAIS%TYPE;
  lnNumeroFila               MAE_CARGA_CLASI_CLIEN.NUM_FILA%TYPE;
  lsCodigoCliente            MAE_CARGA_CLASI_CLIEN.COD_CLIE%TYPE;
  lsCodigoMotivo             MAE_MOTIV_CARGA_CLASI.COD_MOTI%TYPE;
  lnOidCliente               MAE_CLIEN.OID_CLIE%TYPE;
  lnOcurrencias              NUMBER(12);

BEGIN

  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

 --(1) PROCESAMOS A LOS CLIENTES
  OPEN c_clientes;
  LOOP
    FETCH c_clientes BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        lnNumeroFila :=  interfazRecordN(x).numeroFila;
        lsCodigoCliente :=  interfazRecordN(x).codigoCliente;

        lsCodigoMotivo := NULL;
        lnOidCliente := NULL;

        --(1), Validamos si existe el Codigo de Cliente
        BEGIN
          SELECT OID_CLIE
            INTO lnOidCliente
            FROM MAE_CLIEN
           WHERE PAIS_OID_PAIS = lnOidPais
             AND COD_CLIE = lsCodigoCliente;
        EXCEPTION
          WHEN OTHERS THEN NULL;
        END;

        IF(lnOidCliente IS NULL) THEN
          lsCodigoMotivo := '01';

        ELSE
          --(2), Validamos Existencia de Clasificacion para Cliente
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM MAE_CLIEN_TIPO_SUBTI sub, MAE_CLIEN_CLASI cla
           WHERE sub.CLIE_OID_CLIE = lnOidCliente
             AND sub.OID_CLIE_TIPO_SUBT = cla.CTSU_OID_CLIE_TIPO_SUBT
             AND cla.CLAS_OID_CLAS = interfazRecordN(x).oidClasificacion;

          IF(lnOcurrencias > 0) THEN
            lsCodigoMotivo := '02';

          ELSE
            --(3), Tipologia ingresada no coincide con Tipologia de Consultora Asociadas
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM MAE_CLIEN_TIPO_SUBTI sub
             WHERE sub.CLIE_OID_CLIE = lnOidCliente
               AND sub.SBTI_OID_SUBT_CLIE = interfazRecordN(x).oidSubTipoCliente;

            IF(lnOcurrencias = 0) THEN
              lsCodigoMotivo := '03';

            ELSE

              --(4), Region de Consultora no coincide con tipologia ingresada
              SELECT COUNT(1)
                INTO lnOcurrencias
                FROM MAE_SUBTI_CLIEN sub
               WHERE sub.OID_SUBT_CLIE = interfazRecordN(x).oidSubTipoCliente
                 AND sub.IND_EMPL = 1;

              IF(lnOcurrencias > 0) THEN
                SELECT COUNT(1)
                  INTO lnOcurrencias
                  FROM MAE_CLIEN_UNIDA_ADMIN adm, ZON_TERRI_ADMIN ter, ZON_SECCI sec,
                       ZON_ZONA zon, ZON_REGIO reg
                 WHERE adm.CLIE_OID_CLIE = lnOidCliente
                   AND adm.IND_ACTI = 1
                   AND ter.OID_TERR_ADMI = adm.ZTAD_OID_TERR_ADMI
                   AND sec.OID_SECC = ter.ZSCC_OID_SECC
                   AND zon.OID_ZONA = sec.ZZON_OID_ZONA
                   AND reg.OID_REGI = zon.ZORG_OID_REGI
                   AND reg.IND_OFIC = 1;

                IF(lnOcurrencias = 0) THEN
                  lsCodigoMotivo := '04';
                END IF;
              END IF;
            END IF;
          END IF;
        END IF;

        IF(interfazRecordN(x).oidClasificacion=2008) THEN
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM MAE_CLIEN_TIPO_SUBTI sub, MAE_CLIEN_CLASI cla
           WHERE sub.CLIE_OID_CLIE = lnOidCliente
             AND sub.OID_CLIE_TIPO_SUBT = cla.CTSU_OID_CLIE_TIPO_SUBT
             AND cla.CLAS_OID_CLAS = 2009;

          IF(lnOcurrencias > 0) THEN
            lsCodigoMotivo := '05';
          END IF;
                END IF;

        IF(interfazRecordN(x).oidClasificacion=2009) THEN
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM MAE_CLIEN_TIPO_SUBTI sub, MAE_CLIEN_CLASI cla
           WHERE sub.CLIE_OID_CLIE = lnOidCliente
             AND sub.OID_CLIE_TIPO_SUBT = cla.CTSU_OID_CLIE_TIPO_SUBT
             AND cla.CLAS_OID_CLAS = 2008;

          IF(lnOcurrencias > 0) THEN
            lsCodigoMotivo := '06';
          END IF;
        END IF;

        UPDATE MAE_CARGA_CLASI_CLIEN
           SET IND_VALI = DECODE(lsCodigoMotivo, NULL, 1, 0),
               COD_MOTI = lsCodigoMotivo
         WHERE NUM_CARG = pnNumeroCarga
           AND NUM_FILA = lnNumeroFila;

        END LOOP;
     END IF;
     EXIT WHEN c_clientes%NOTFOUND;
  END LOOP;
  CLOSE c_clientes;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_VALID_CARGA_CLASI: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_VALID_CARGA_CLASI;

/***************************************************************************
Descripcion       : Actualiza Carga de Clasificacion de Clientes
Fecha Creacion    : 25/05/2010
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CARGA_CLASI
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   psNumeroLote               VARCHAR2,
   psUsuMod                   VARCHAR2)
IS

CURSOR c_clientes IS
SELECT NUM_FILA, COD_CLIE, OID_SUBT_CLIE, OID_TIPO_CLAS, OID_CLAS
  FROM MAE_CARGA_CLASI_CLIEN
 WHERE NUM_CARG = pnNumeroCarga
   AND IND_VALI = 1;

  TYPE interfazClientes IS RECORD
  (
    numeroFila             MAE_CARGA_CLASI_CLIEN.NUM_FILA%TYPE,
    codigoCliente          MAE_CARGA_CLASI_CLIEN.COD_CLIE%TYPE,
    oidSubTipoCliente      MAE_CARGA_CLASI_CLIEN.OID_SUBT_CLIE%TYPE,
    oidTipoClasificiacion  MAE_CARGA_CLASI_CLIEN.OID_TIPO_CLAS%TYPE,
    oidClasificacion       MAE_CARGA_CLASI_CLIEN.OID_CLAS%TYPE
  );

  TYPE interfazClientesTab  IS TABLE OF interfazClientes;
  interfazRecordN interfazClientesTab;

  lnOidPais                  SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                 SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                 SEG_CANAL.OID_CANA%TYPE;

  lnOidPeriodo               CRA_PERIO.OID_PERI%TYPE;
  lnNumeroFila               MAE_CARGA_CLASI_CLIEN.NUM_FILA%TYPE;
  lsCodigoCliente            MAE_CARGA_CLASI_CLIEN.COD_CLIE%TYPE;
  lnOidCliente               MAE_CLIEN.OID_CLIE%TYPE;

  lnOidClienteSubTipo        MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;
  lnOidClienteClasificacion  MAE_CLIEN_CLASI.OID_CLIE_CLAS%TYPE;

BEGIN

  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

  --Recuperamos el Oid Periodo Actual
  SELECT OID_PERI
    INTO lnOidPeriodo
    FROM (SELECT OID_PERI
            FROM CRA_PERIO
           WHERE PAIS_OID_PAIS = lnOidPais
             AND MARC_OID_MARC = lnOidMarca
             AND CANA_OID_CANA = lnOidCanal
           	 AND TRUNC(SYSDATE) >= TRUNC(FEC_INIC)
             AND TRUNC(SYSDATE) <= TRUNC(FEC_FINA)
           ORDER BY FEC_INIC)
   WHERE ROWNUM=1;

 --(1) PROCESAMOS A LOS CLIENTES
  OPEN c_clientes;
  LOOP
    FETCH c_clientes BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        lnNumeroFila :=  interfazRecordN(x).numeroFila;
        lsCodigoCliente :=  interfazRecordN(x).codigoCliente;
        lnOidCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(interfazRecordN(x).codigoCliente);

        --Recuperamos Tipologia ingresada al OidSubtipoCliente
        SELECT sub.OID_CLIE_TIPO_SUBT
          INTO lnOidClienteSubTipo
          FROM MAE_CLIEN_TIPO_SUBTI sub
         WHERE sub.CLIE_OID_CLIE = lnOidCliente
           AND sub.SBTI_OID_SUBT_CLIE = interfazRecordN(x).oidSubTipoCliente;

        --Insertamos la clasificacion al Cliente correspondiente
        SELECT MAE_CLCL_SEQ.NEXTVAL INTO lnOidClienteClasificacion FROM DUAL;

        INSERT INTO MAE_CLIEN_CLASI
          (OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT, CLAS_OID_CLAS,
           PERD_OID_PERI, TCCL_OID_TIPO_CLASI, FEC_CLAS, IND_PPAL,USU_MODI)
        VALUES
           (lnOidClienteClasificacion, lnOidClienteSubTipo, interfazRecordN(x).oidClasificacion,
            lnOidPeriodo, interfazRecordN(x).oidTipoClasificiacion, to_date(to_char(sysdate,'dd/mm/yyyy'),'dd/mm/yyyy'), '0',psUsuMod);

        --Actualiza la fila procesada
        UPDATE MAE_CARGA_CLASI_CLIEN
           SET NUM_LOTE = psNumeroLote,
               FEC_PROC = SYSDATE,
               OID_CLIE_CLAS = lnOidClienteClasificacion
         WHERE NUM_CARG = pnNumeroCarga
           AND NUM_FILA = lnNumeroFila;

        END LOOP;
     END IF;
     EXIT WHEN c_clientes%NOTFOUND;
  END LOOP;
  CLOSE c_clientes;

  --Actualiza el numero de Lote, para toda la Carga, indicando que ha sido procesada
  UPDATE MAE_CARGA_CLASI_CLIEN
     SET NUM_LOTE = psNumeroLote
   WHERE NUM_CARG = pnNumeroCarga;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ACTUA_CARGA_CLASI: ('|| lsCodigoCliente || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_ACTUA_CARGA_CLASI;



/***************************************************************************
Descripcion       : Actualiza  Clasificacion de Nuevas por Zonas.
Fecha Creacion    : 02/09/2014
Autor             : Juan Gutiérrez
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_CLASI_NUEVA
 (
   psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psCodigoUsuario            VARCHAR2)
 IS

 CURSOR c_eliminar(vnOidTNueva NUMBER) IS
       SELECT   mae.cod_clie,
                mts.oid_clie_tipo_subt
         FROM
                MAE_CLIEN             mae,
                MAE_CLIEN_DATOS_ADICI mcda,
                MAE_CLIEN_TIPO_SUBTI  mts,
                MAE_CLIEN_CLASI       mcl,
                mae_clien_unida_admin cuad,
                zon_terri_admin       ztad,
                zon_secci             zscc,
                zon_terri             terr,
                zon_zona              zzon,
                zon_regio             zorg
         WHERE  mcda.ind_acti = 1
         AND    mcda.esta_oid_esta_clie  NOT IN (5,7)
         AND    MAE.OID_CLIE = mcda.clie_oid_clie
         AND    mts.clie_oid_clie = mae.oid_clie
         AND    mts.oid_clie_tipo_subt = mcl.ctsu_oid_clie_tipo_subt
         AND    mcl.tccl_oid_tipo_clasi = vnOidTNueva
         AND    mae.oid_clie = cuad.clie_oid_clie
         AND    cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND    ztad.zscc_oid_secc = zscc.oid_secc
         AND    ztad.terr_oid_terr = terr.oid_terr
         AND    zscc.zzon_oid_zona = zzon.oid_zona
         AND    zzon.zorg_oid_regi = zorg.oid_regi
         AND   (((SELECT count(1) FROM nvs_param_descu_unadm npua
                WHERE npua.cod_regi = zorg.cod_regi
                AND  (npua.cod_zona is null or npua.cod_zona = zzon.cod_zona)
                AND  npua.ind_excl = 0 ) = 0)
         OR    (SELECT COUNT(1)
                FROM MAE_CLIEN_PRIME_CONTA mp
                WHERE mp.clie_oid_clie = mae.oid_clie
                AND
                FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(mp.perd_oid_peri),3) < psCodigoPeriodo )>0);

 CURSOR c_nuevas(lnOidPeriodo NUMBER, vnCodPeriEval VARCHAR2, vnOidTNueva VARCHAR2) IS
    WITH TEMP AS (
                SELECT clie.cod_clie,
                       --clie.oid_clie,
                       mts.oid_clie_tipo_subt
                  FROM mae_clien_histo_estat clhe,
                       mae_clien clie,
                       cra_perio perd,
                       seg_perio_corpo peri,
                       (
                          SELECT soca.clie_oid_clie, cons.val_nume_soli, cons.val_tota_paga_loca, cons.esso_oid_esta_soli
                            FROM ped_solic_cabec soca,
                                 ped_solic_cabec cons,
                                 ped_tipo_solic_pais tspa,
                                 ped_tipo_solic tsol
                           WHERE 1=1
                             AND soca.soca_oid_soli_cabe = cons.oid_soli_cabe(+)
                             AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                             AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                             AND soca.perd_oid_peri = lnOidPeriodo
                         --    AND soca.grpr_oid_grup_proc = 3 -- Esto debe ser 3 ( para evaluar pedidos en gp3 )
                             AND tsol.cod_tipo_soli = 'SOC'
                       ) pedi,
                       MAE_CLIEN_TIPO_SUBTI  mts,
                       ped_solic_cabec_acum2 sca2,
                       cra_perio perd2,
                       seg_perio_corpo peri2,
                       nvs_param_descu_unadm npua,
                       mae_clien_unida_admin cuad,
                       zon_terri_admin       ztad,
                       zon_secci             zscc,
                       zon_terri             terr,
                       zon_zona              zzon,
                       zon_regio             zorg
                 WHERE clhe.clie_oid_clie = sca2.clie_oid_clie(+)
                   AND sca2.perd_oid_peri = perd2.oid_peri(+)
                   AND perd2.peri_oid_peri = peri2.oid_peri(+)
                   AND clhe.clie_oid_clie = pedi.clie_oid_clie
                   AND clhe.clie_oid_clie = clie.oid_clie
                   AND clhe.perd_oid_peri = perd.oid_peri
                   AND perd.peri_oid_peri = peri.oid_peri
                   AND clhe.esta_oid_esta_clie IN (2,8)
                   AND peri.cod_peri >= vnCodPeriEval
                   AND peri.cod_peri >= GEN_FN_CALCU_PERIO (psCodigoPeriodo, -3)
                   AND peri2.cod_peri BETWEEN GEN_FN_CALCU_PERIO (psCodigoPeriodo, -3) AND psCodigoPeriodo
                   AND npua.cod_regi =  zorg.cod_regi
                   AND npua.cod_zona =  zzon.cod_zona
                   AND npua.ind_excl =  '0'
                   AND clie.oid_clie = cuad.clie_oid_clie
                   AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                   AND ztad.zscc_oid_secc = zscc.oid_secc
                   AND ztad.terr_oid_terr = terr.oid_terr
                   AND zscc.zzon_oid_zona = zzon.oid_zona
                   AND zzon.zorg_oid_regi = zorg.oid_regi
                   AND cuad.ind_acti = 1
                   AND mts.clie_oid_clie = clie.oid_clie
                   AND (SELECT count(1) FROM MAE_CLIEN_CLASI MCC
                        WHERE MTS.oid_clie_tipo_subt = MCC.CTSU_OID_CLIE_TIPO_SUBT
                        AND MCC.TCCL_OID_TIPO_CLASI = vnOidTNueva) = 0
              GROUP BY clie.cod_clie,
                       clie.oid_clie,
                       peri.cod_peri
             )
  SELECT cod_clie,
         oid_clie_tipo_subt
    FROM temp ;

      vnOidClasNueva        MAE_CLASI.OID_CLAS%TYPE;
      vnOidTNueva           MAE_TIPO_CLASI_CLIEN.OID_TIPO_CLAS%TYPE;
      vnCodPeriEval         VARCHAR2(6);
      vnOidPeriodo          NUMBER;
      vsCodClie             MAE_CLIEN.COD_CLIE%TYPE;
      vsOidCliTipoSTipo     MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;

BEGIN
      vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

      SELECT tcc.oid_tipo_clas
        INTO vnOidTNueva
        FROM mae_tipo_clasi_clien tcc
             ,gen_i18n_sicc_comun des_tip_cla
       WHERE tcc.oid_tipo_clas     =  des_tip_cla.val_oid
         AND des_tip_cla.attr_enti = 'MAE_TIPO_CLASI_CLIEN'
         AND des_tip_cla.val_i18n  = 'Nuevas Piloto 2014';

      SELECT c.oid_clas
        INTO vnOidClasNueva
        FROM mae_clasi c
             ,gen_i18n_sicc_comun des_cla
       WHERE c.tccl_oid_tipo_clas   =  vnOidTNueva
         AND c.oid_clas             = des_cla.val_oid
         AND des_cla.attr_enti      = 'MAE_CLASI'
         AND des_cla.val_i18n       = 'Piloto 2014';

      SELECT VAL_PARA
        INTO vnCodPeriEval
        FROM Bas_Param_PAIS
       WHERE cod_sist = 'NVS'
         AND NOM_PARA = 'campEvalNueva';

      OPEN c_eliminar(vnOidTNueva );
        LOOP FETCH c_eliminar INTO vsCodClie, vsOidCliTipoSTipo;

            EXIT WHEN c_eliminar%NOTFOUND;

            DELETE FROM MAE_CLIEN_CLASI
            WHERE CTSU_OID_CLIE_TIPO_SUBT = vsOidCliTipoSTipo
            AND TCCL_OID_TIPO_CLASI = vnOidTNueva;

        END LOOP;
      CLOSE c_eliminar;

      OPEN c_nuevas(vnOidPeriodo,vnCodPeriEval,vnOidTNueva );
        LOOP FETCH c_nuevas INTO vsCodClie, vsOidCliTipoSTipo;

            EXIT WHEN c_nuevas%NOTFOUND;

            INSERT INTO MAE_CLIEN_CLASI CL(
               CL.OID_CLIE_CLAS , CL.CTSU_OID_CLIE_TIPO_SUBT , CL.CLAS_OID_CLAS, CL.PERD_OID_PERI , CL.TCCL_OID_TIPO_CLASI , CL.FEC_CLAS , CL.IND_PPAL , CL.FEC_ULTI_ACTU)
            VALUES
               (mae_clcl_seq.nextval , vsOidCliTipoSTipo, vnOidClasNueva , vnOidPeriodo ,vnOidTNueva , TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY')  ,0,SYSDATE);

        END LOOP;
      CLOSE c_nuevas;

END MAE_PR_ACTUA_CLASI_NUEVA;

/**************************************************************************
Descripcion       : Inserta las correspondiente registros en las tablas de
                    incentivos para la recomendante y la recomendad
Fecha Creacion    : 12/08/2015
Parametros Entrada:
  psCodigoPais         : Codigo Pais

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAE_PR_CARGA_MASIV_CLASI_SEGME
  (psCodigoPais           VARCHAR2)
IS

CURSOR c_clientes IS
  SELECT DISTINCT SG.COD_CLIE, SG.COD_TIPO_CLAS, SG.COD_CLAS
    FROM MAE_CARGA_CLASI_CLIEN_SEGME SG
   WHERE SG.COD_TIPO_CLAS IN (SELECT TC.COD_TIPO_CLAS
                                FROM MAE_TIPO_CLASI_CLIEN TC
                               WHERE TC.IND_SEGM ='1')
     AND EST_REGI IS NULL;

  TYPE interfazClientes IS RECORD
  (
    codigoCliente      MAE_CARGA_CLASI_CLIEN.COD_CLIE%TYPE,
    tipoClasificacion  MAE_CARGA_CLASI_CLIEN_SEGME.COD_TIPO_CLAS%TYPE,
    clasificacion      MAE_CARGA_CLASI_CLIEN_SEGME.COD_CLAS%TYPE
  );

  TYPE interfazClientesTab  IS TABLE OF interfazClientes;
  interfazRecordN interfazClientesTab;

  lsCodigoCliente            MAE_CARGA_CLASI_CLIEN.COD_CLIE%TYPE;
  lnOidTipoClasi             MAE_TIPO_CLASI_CLIEN.OID_TIPO_CLAS%TYPE;
  lnOidClasi                 MAE_CLASI.OID_CLAS%TYPE;

  lsCodigoUsuario            VARCHAR2(20);
  lnTotalOCurrencias         NUMBER;
  lnNumeroFila               NUMBER;
  lnNumeroCarga              NUMBER;
  lnNumeroLote               NUMBER;

BEGIN
  lsCodigoUsuario := 'BDIAUTO';


  -- LO QUE SE ELIMINARA SE GUARDA EN EL HISTORICO EL TOTAL
INSERT INTO MAE_CLASI_ELIMI_HISTO
  (COR_CLAS_ELIM,
   PAIS_COD_PAIS,
   COD_SUBT_CLIE,
   COD_TIPO_CLIE,
   COD_TIPO_CLAS,
   COD_CLAS,
   NUM_REGI_ELIM,
   FEC_ELIM,
   USU_ELIM)
VALUES
  ((SELECT NVL(MAX(COR_CLAS_ELIM), 0) + 1 FROM MAE_CLASI_ELIMI_HISTO),
   psCodigoPais,
   (SELECT COD_SUBT_CLIE FROM MAE_SUBTI_CLIEN WHERE OID_SUBT_CLIE = 1),
   (SELECT COD_TIPO_CLIE FROM MAE_TIPO_CLIEN WHERE OID_TIPO_CLIE = 2),
   'MV',--MASIVO
   'T',--TODAS
   (select count(*) from mae_clien_clasi x
WHERE x.tccl_oid_tipo_clasi in
(SELECT  tc.oid_tipo_clas
    FROM MAE_TIPO_CLASI_CLIEN TC
   WHERE TC.IND_SEGM ='1')),--total
   SYSDATE,
   lsCodigoUsuario);


 --SE ELIMINA TODAS LAS CLASIFICACIONES DE SEGMETACION CARGADAS ANTERIORMENTE C
    DELETE mae_clien_clasi x
    WHERE x.tccl_oid_tipo_clasi in
    (SELECT  tc.oid_tipo_clas
        FROM MAE_TIPO_CLASI_CLIEN TC
       WHERE TC.IND_SEGM ='1');


  --SE ELIMINA TODO LO PROCESADO ANTERIORMENTE
  DELETE FROM MAE_CARGA_CLASI_CLIEN_SEGME WHERE EST_REGI IS NOT NULL;

  --Obtenemos el Numero de Carga
  SELECT NVL(MAX(NUM_CARG) + 1, 1)
    INTO lnNumeroCarga
    FROM MAE_CARGA_CLASI_CLIEN c;

  lnNumeroFila := 0;
  --(1) PROCESAMOS A LOS CLIENTES
  OPEN c_clientes;
  LOOP
    FETCH c_clientes BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        lnNumeroFila :=  lnNumeroFila + 1;
        lsCodigoCliente :=  interfazRecordN(x).codigoCliente;

        --Obtenemos el Oid TipoClasificacion
        SELECT OID_TIPO_CLAS
          INTO lnOidTipoClasi
          FROM MAE_TIPO_CLASI_CLIEN
         WHERE SBTI_OID_SUBT_CLIE = 1
           AND COD_TIPO_CLAS = interfazRecordN(x).tipoClasificacion;

        --Obtenemos el Oid Clasificacion
        SELECT OID_CLAS
          INTO lnOidClasi
          FROM MAE_CLASI
         WHERE TCCL_OID_TIPO_CLAS = lnOidTipoClasi
           AND COD_CLAS = interfazRecordN(x).clasificacion;

        SELECT COUNT(1)
          INTO lnTotalOCurrencias
          FROM MAE_CARGA_CLASI_CLIEN
         WHERE NUM_CARG = lnNumeroCarga
           AND COD_CLIE = lsCodigoCliente
           AND OID_TIPO_CLAS = lnOidTipoClasi;

        IF(lnTotalOcurrencias = 0) THEN
          --INSERTAMOS EN LA TABLA DE CARGA DEL PROCESO DE CLASIFICACIONES DE MAE
          INSERT INTO MAE_CARGA_CLASI_CLIEN
            (NUM_CARG,
             NUM_FILA,
             COD_CLIE,
             FEC_CARG,
             OID_TIPO_CLIE,
             OID_SUBT_CLIE,
             OID_TIPO_CLAS,
             OID_CLAS,
             IND_VALI,
             COD_MOTI,
             NUM_LOTE,
             FEC_PROC,
             COD_USUA,
             OID_CLIE_CLAS)
          VALUES
            (lnNumeroCarga,
             lnNumeroFila,
             lsCodigoCliente,
             SYSDATE,
             2,
             1,
             lnOidTipoClasi,
             lnOidClasi,
             NULL,
             NULL,
             NULL,
             NULL,
             lsCodigoUsuario,
             NULL);
          END IF;

        END LOOP;
     END IF;
     EXIT WHEN c_clientes%NOTFOUND;
  END LOOP;
  CLOSE c_clientes;

  --Actualizamos en la tabla temporal marcando que ya fueron procesadas
  UPDATE MAE_CARGA_CLASI_CLIEN_SEGME
     SET EST_REGI = '1'
   WHERE EST_REGI IS NULL;

  --VALIDACIONES
  MAE_PKG_CLASI.MAE_PR_VALID_CARGA_CLASI(psCodigoPais, lnNumeroCarga) ;

  --Obtenemos el Numero de Lote
  SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') ||
         LPAD(NVL(MAX(SUBSTR(A.NUM_LOTE, 9, 4)) + 1, 1), 4, '0') AS NUM_LOTE
    INTO lnNumeroLote
    FROM MAE_CARGA_CLASI_CLIEN A;

  --INSERCION EN LA TABLA DE CLASIFICACION
  MAE_PKG_CLASI.MAE_PR_ACTUA_CARGA_CLASI(psCodigoPais, lnNumeroCarga, lnNumeroLote, lsCodigoUsuario);

  --ENVIO DE CORREO
  MAE_PKG_CLASI.MAE_PR_ENCOR_CARGA_AUTOM_MASCC(psCodigoPais);

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_CARGA_MASIV_CLASI_SEGME: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_CARGA_MASIV_CLASI_SEGME;

/***************************************************************************
Descripcion       : Valida Carga Masiva de Direcciones
Fecha Creacion    : 31/08/2015
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAE_PR_VALID_CARGA_MASIV_DIREC
  (psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER)
IS

CURSOR c_clientes IS
SELECT NUM_FILA,
       COD_CLIE,
       VAL_REFE,
       VAL_BARR,
       COD_ZONA,
       COD_TERR,
       VAL_TIPO_VIA,
       VAL_DIRE,
       VAL_NUME_PRIN,
       COD_TIPO_DIRE,
       VAL_MANZ,
       VAL_ETA_CONJ,
       VAL_CAL_PRIN,
       VAL_CAL_SEC
  FROM MAE_CARGA_MASIV_DIREC
 WHERE NUM_CARG = pnNumeroCarga;

  TYPE interfazClientes IS RECORD
  (
    numeroFila         MAE_CARGA_MASIV_DIREC.NUM_FILA%TYPE,
    codigoCliente      MAE_CARGA_MASIV_DIREC.COD_CLIE%TYPE,
    referencia         MAE_CARGA_MASIV_DIREC.VAL_REFE%TYPE,
    barrio             MAE_CARGA_MASIV_DIREC.VAL_BARR%TYPE,
    zona               MAE_CARGA_MASIV_DIREC.COD_ZONA%TYPE,
    territorio         MAE_CARGA_MASIV_DIREC.COD_TERR%TYPE,
    tipoVia            MAE_CARGA_MASIV_DIREC.VAL_TIPO_VIA%TYPE,
    direccion          MAE_CARGA_MASIV_DIREC.VAL_DIRE%TYPE,
    numeroPrincipal    MAE_CARGA_MASIV_DIREC.VAL_NUME_PRIN%TYPE,
    tipoDireccion      MAE_CARGA_MASIV_DIREC.COD_TIPO_DIRE%TYPE,
    manzanaLetra       MAE_CARGA_MASIV_DIREC.VAL_MANZ%TYPE,
    etapaConjunto      MAE_CARGA_MASIV_DIREC.VAL_ETA_CONJ%TYPE,
    callePrincipal     MAE_CARGA_MASIV_DIREC.VAL_CAL_PRIN%TYPE,
    calleSecundaria    MAE_CARGA_MASIV_DIREC.VAL_CAL_SEC%TYPE
  );

  TYPE interfazClientesTab  IS TABLE OF interfazClientes;
  interfazRecordN interfazClientesTab;

  lnOidZona                  NUMBER;
  lnOidTerritorio            NUMBER;
  lnOidTipoDirec             NUMBER;
  lnOidTerrAdmi              NUMBER;
  lsUbigeo                   VARCHAR2(30);
  lsEstatus                  VARCHAR2(2);
  lsCaracteresNV             VARCHAR2(200);
  lsAuxiliar                 VARCHAR2(10);
  lsZonaActual               VARCHAR2(4);
  lsTerritorioActual         VARCHAR2(6);
  lnLongitud                 NUMBER;
  lnPosicion                 NUMBER;
  lbValido                   BOOLEAN;

  lnOidPais                  SEG_PAIS.OID_PAIS%TYPE;
  lnNumeroFila               MAE_CARGA_CLASI_CLIEN.NUM_FILA%TYPE;
  lsCodigoCliente            MAE_CARGA_CLASI_CLIEN.COD_CLIE%TYPE;
  lsCodigoMotivo             MAE_MOTIV_CARGA_CLASI.COD_MOTI%TYPE;
  lnOidCliente               MAE_CLIEN.OID_CLIE%TYPE;
  lnOcurrencias              NUMBER(12);
  lnValorPara                VARCHAR2(1);
  lnRegistroLLeno            NUMBER(12);

BEGIN

  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  --Valor del Parametro
  BEGIN
    select val_para into lnValorPara
    from bas_param_pais
   where cod_sist = 'MAE'
     and cod_pais= psCodigoPais
     AND nom_para = 'indCamposAdicionales';
   EXCEPTION
     WHEN OTHERS THEN
       lnValorPara:='0';
  END;
   

  --RECUPERAMOS LOS CARACTERES NO VALIDOS PARA REFERENCIA y DIRECCION
  FOR x IN (SELECT CRT_VALI
              FROM MAE_CONTR_CARAC
             WHERE COD_MODU_VALI = 'VAL_CRT_NV1'
               AND CRT_INDI='N') LOOP
    /*IF(lsCaracteresNV IS NULL) THEN
      lsCaracteresNV := ASCII(x.CRT_VALI);
    ELSE
      lsCaracteresNV := lsCaracteresNV || '__' || ASCII(x.CRT_VALI);
    END IF;*/

    lsCaracteresNV := lsCaracteresNV || x.CRT_VALI || '__';
  END LOOP;
  lsCaracteresNV := '__' || lsCaracteresNV;

  --PROCESAMOS A LOS CLIENTES
  OPEN c_clientes;
  LOOP
    FETCH c_clientes BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        lnNumeroFila :=  interfazRecordN(x).numeroFila;
        lsCodigoCliente :=  interfazRecordN(x).codigoCliente;

        lsCodigoMotivo := NULL;
        lnOidCliente := NULL;
        lnOidTerritorio := NULL;
        lnOidTerrAdmi := NULL;
        lsUbigeo := NULL;
        lsEstatus := NULL;
        lnRegistroLLeno:=0;

        --(1), Validamos si existe el Codigo de Cliente
        BEGIN
          SELECT OID_CLIE
            INTO lnOidCliente
            FROM MAE_CLIEN
           WHERE PAIS_OID_PAIS = lnOidPais
             AND COD_CLIE = interfazRecordN(x).codigoCliente;
        EXCEPTION
          WHEN OTHERS THEN
            lsCodigoMotivo := '01';
        END;


        --(2), Validamos si existe el Tipo Via
        IF(lsCodigoMotivo IS NULL) THEN
          IF(interfazRecordN(x).tipoVia IS NOT NULL) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM SEG_TIPO_VIA
             WHERE COD_TIPO_VIA = interfazRecordN(x).tipoVia;

            IF(lnOcurrencias = 0) THEN
              lsCodigoMotivo := '02';
            END IF;
          END IF;
        END IF;

        --(3), Validamos si existe el Codigo de Zona
        IF(lsCodigoMotivo IS NULL) THEN
          BEGIN
            SELECT OID_ZONA
              INTO lnOidZona
              FROM ZON_ZONA
             WHERE COD_ZONA = interfazRecordN(x).zona
               AND IND_ACTI = 1;
          EXCEPTION
            WHEN OTHERS THEN
              lsCodigoMotivo := '03';
          END;
        END IF;

        --(4), Validamos si existe el Codigo de Territorio
        IF(lsCodigoMotivo IS NULL) THEN
          BEGIN
            SELECT OID_TERR
              INTO lnOidTerritorio
              FROM ZON_TERRI
             WHERE COD_TERR = interfazRecordN(x).territorio
               AND IND_BORR = 0;
          EXCEPTION
            WHEN OTHERS THEN
              lsCodigoMotivo := '04';
          END;
        END IF;

        --(5), Validamos si existe el Codigo de Tipo de Direccion
        IF(lsCodigoMotivo IS NULL) THEN
          BEGIN
            SELECT OID_TIPO_DIRE
              INTO lnOidTipoDirec
              FROM MAE_TIPO_DIREC
             WHERE COD_TIPO_DIRE = interfazRecordN(x).tipoDireccion;
          EXCEPTION
            WHEN OTHERS THEN
              lsCodigoMotivo := '05';
          END;
        END IF;

        --(6), Validamos Caracteres No Validos para el campo Referencia
        IF(lsCodigoMotivo IS NULL) THEN
          IF(interfazRecordN(x).referencia IS NOT NULL) THEN
            lnLongitud := LENGTH(interfazRecordN(x).referencia);
            lbValido := TRUE;
            lnPosicion := 1;

            WHILE ((lnPosicion <= lnLongitud) AND lbValido)
            LOOP
              lsAuxiliar := '__' || substr(interfazRecordN(x).referencia,lnPosicion,1) || '__';

              IF(INSTR(lsCaracteresNV, lsAuxiliar)>0) THEN
                lbValido := FALSE;
              ELSE
                lnPosicion := lnPosicion + 1;
              END IF;

            END LOOP;

            IF(NOT lbValido) THEN
              lsCodigoMotivo := '06';
            END IF;
          END IF;
        END IF;

        --(7), Validamos Caracteres No Validos para el campo Direccion
        IF(lsCodigoMotivo IS NULL) THEN
          IF(interfazRecordN(x).direccion IS NOT NULL) THEN
            lnLongitud := LENGTH(interfazRecordN(x).direccion);
            lbValido := TRUE;
            lnPosicion := 1;

            WHILE ((lnPosicion <= lnLongitud) AND lbValido)
            LOOP
              lsAuxiliar := '__' || substr(interfazRecordN(x).direccion,lnPosicion,1) || '__';

              IF(INSTR(lsCaracteresNV, lsAuxiliar)>0) THEN
                lbValido := FALSE;
              ELSE
                lnPosicion := lnPosicion + 1;
              END IF;

            END LOOP;

            IF(NOT lbValido) THEN
              lsCodigoMotivo := '07';
            END IF;
          END IF;
        END IF;
        
        --(7), Validamos Caracteres No Validos para el campo Referencia
        IF(lsCodigoMotivo IS NULL AND (lnValorPara IS NULL OR lnValorPara <> '1') )THEN
          IF(interfazRecordN(x).referencia IS NULL AND interfazRecordN(x).direccion IS NULL) THEN
            lsCodigoMotivo := '08';
          END IF;
        END IF;

        --(12)Valida Campo Referencia-direccion-calle principal indCamposAdicionales
         IF(lsCodigoMotivo IS NULL AND lnValorPara = '1') THEN
          IF(interfazRecordN(x).referencia IS NOT NULL) THEN
              lnRegistroLLeno := lnRegistroLLeno+1;
           END IF;
          IF(interfazRecordN(x).callePrincipal IS NOT NULL)THEN
              lnRegistroLLeno := lnRegistroLLeno+1;              
          END IF;
          IF(interfazRecordN(x).calleSecundaria IS NOT NULL)THEN
              lnRegistroLLeno := lnRegistroLLeno+1;              
          END IF;
          IF(interfazRecordN(x).etapaConjunto IS NOT NULL)THEN
              lnRegistroLLeno := lnRegistroLLeno+1;              
          END IF;
          IF(interfazRecordN(x).barrio IS NOT NULL)THEN
              lnRegistroLLeno := lnRegistroLLeno+1;              
          END IF;
          
          IF(lnRegistroLLeno < 2)THEN
              lsCodigoMotivo := '12';              
          END IF;
        END IF;

        IF(lsCodigoMotivo IS NULL) THEN
          --OBTENEMOS EL CODIGO ZONA y CODIGO TERRITORIO ACTUAL DE LA CONSULTORA
          SELECT COD_ZONA,
                 COD_TERR
            INTO lsZonaActual,
                 lsTerritorioActual
            FROM MAE_CLIEN_UNIDA_ADMIN uni,
                 ZON_TERRI_ADMIN adm,
                 ZON_TERRI ter,
                 ZON_SECCI sec,
                 ZON_ZONA zon
           WHERE uni.Clie_Oid_Clie = lnOidCliente
             AND uni.perd_oid_peri_fin IS NULL
             AND adm.oid_terr_admi = uni.ztad_oid_terr_admi
             AND ter.oid_terr = adm.terr_oid_terr
             AND adm.zscc_oid_secc = sec.oid_secc
             AND sec.zzon_oid_zona = zon.oid_zona
             AND rownum = 1;

          --EXISTE CAMBIO DE ZONA, VALIDAMOS LA NUEVA UA
          IF((interfazRecordN(x).zona <> lsZonaActual) OR
             (interfazRecordN(x).territorio <> lsTerritorioActual)) THEN

            BEGIN
              SELECT ADM.OID_TERR_ADMI
                INTO lnOidTerrAdmi
                FROM ZON_TERRI_ADMIN ADM, ZON_TERRI TER, ZON_SECCI SEC
                WHERE ADM.TERR_OID_TERR = TER.OID_TERR
                  AND ADM.PERD_OID_PERI_FINA IS NULL
                  AND ADM.PAIS_OID_PAIS = lnOidPais
                  AND TER.OID_TERR = lnOidTerritorio
                  AND SEC.OID_SECC = ADM.ZSCC_OID_SECC
                  AND SEC.ZZON_OID_ZONA = lnOidZona;

              --Obtenemos el ubigeo de la Nueva UA
              SELECT VAL.ORDE_1 || VAL.ORDE_2 || VAL.ORDE_3 || VAL.ORDE_4
                INTO lsUbigeo
                FROM ZON_VALOR_ESTRU_GEOPO VAL,
                     ZON_TERRI TER
               WHERE VAL.OID_VALO_ESTR_GEOP = TER.VEPO_OID_VALO_ESTR_GEOP
                 AND TER.OID_TERR = lnOidTerritorio;

              --Obtenemos el codigo Estatus de la Consultora
              SELECT est.COD_ESTA_CLIE
                INTO lsEstatus
                FROM MAE_CLIEN_DATOS_ADICI adi,
                     MAE_ESTAT_CLIEN est
               WHERE adi.CLIE_OID_CLIE = lnOidCliente
                 AND adi.ESTA_OID_ESTA_CLIE = est.OID_ESTA_CLIE;

            EXCEPTION
              WHEN OTHERS THEN
                lsCodigoMotivo := '09';
            END;

          END IF;
        END IF;

        UPDATE MAE_CARGA_MASIV_DIREC
           SET IND_VALI = DECODE(lsCodigoMotivo, NULL, 1, 0),
               COD_MOTI = lsCodigoMotivo,
               OID_CLIE = lnOidCliente,
               OID_TERR = lnOidTerritorio,
               OID_TERR_ADMI = lnOidTerrAdmi,
               COD_UNID_GEOG = lsUbigeo,
               COD_ESTA = lsEstatus,
               COD_ZONA_ACTU = lsZonaActual
         WHERE NUM_CARG = pnNumeroCarga
           AND NUM_FILA = lnNumeroFila;

        END LOOP;
     END IF;
     EXIT WHEN c_clientes%NOTFOUND;
  END LOOP;
  CLOSE c_clientes;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_VALID_CARGA_MASIV_DIREC: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END MAE_PR_VALID_CARGA_MASIV_DIREC;

/***************************************************************************
Descripcion       : Envio de correo electronico en Carga Automatica Masiva de Clasificaciones de Clientes
Fecha Creacion    : 02/09/2015
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE MAE_PR_ENCOR_CARGA_AUTOM_MASCC
  (
    pscodigopais     VARCHAR2
  ) IS

    regreporte bas_repor_param%ROWTYPE;

    l_mail_conn utl_smtp.connection;

    lssubject       VARCHAR2(500);
    lslinea         VARCHAR2(4000);
    lnregistro     NUMBER;
    lnEntorno       BAS_PAIS.VAL_URL%TYPE;
    lnAsuntoFinal       VARCHAR2(100);
    lnDescPais       BAS_PAIS.DES_PAIS%TYPE;

    CURSOR cursorResultadoCarga IS
       select tclas.COD_TIPO_CLAS COD_TIPO_CLAS,
                gtclas.VAL_I18N TIPO_CLASIFICACION,
                clas.COD_CLAS COD_CLAS,
                GCLAS.VAL_I18N CLASIFICACION,
                count(*) CANTIDAD
        from mae_clien cl,
                MAE_CLIEN_TIPO_SUBTI st,
                mae_tipo_clien tp,
                mae_subti_clien subt,
                MAE_CLIEN_CLASI cla,
                MAE_TIPO_CLASI_CLIEN TCLAS,
                mae_clasi clas,
                GEN_I18N_SICC_COMUN GTIPO,
                GEN_I18N_SICC_COMUN GSUBT,
                GEN_I18N_SICC_COMUN GTCLAS,
                GEN_I18N_SICC_COMUN GCLAS
        WHERE cl.OID_CLIE = st.CLIE_OID_CLIE
            and st.TICL_OID_TIPO_CLIE = tp.OID_TIPO_CLIE
            and st.TICL_OID_TIPO_CLIE = gtipo.VAL_OID AND GTIPO.ATTR_ENTI = 'MAE_TIPO_CLIEN'
            and st.SBTI_OID_SUBT_CLIE = subt.OID_SUBT_CLIE
            and st.SBTI_OID_SUBT_CLIE = gsubt.VAL_OID AND GSUBT.ATTR_ENTI = 'MAE_SUBTI_CLIEN'
            and st.OID_CLIE_TIPO_SUBT = cla.CTSU_OID_CLIE_TIPO_SUBT
            and cla.TCCL_OID_TIPO_CLASI = tclas.OID_TIPO_CLAS
            and tclas.OID_TIPO_CLAS = gtclas.VAL_OID AND GTCLAS.ATTR_ENTI = 'MAE_TIPO_CLASI_CLIEN'
            and cla.CLAS_OID_CLAS = clas.OID_CLAS
            and clas.OID_CLAS = gclas.VAL_OID AND Gclas.ATTR_ENTI = 'MAE_CLASI'
            and tclas.COD_TIPO_CLAS in
            (
                SELECT TC.Cod_Tipo_Clas
                                    FROM MAE_TIPO_CLASI_CLIEN TC
                                   WHERE TC.IND_SEGM ='1'
            )
        group by tclas.COD_TIPO_CLAS, gtclas.VAL_I18N, clas.COD_CLAS,GCLAS.VAL_I18N
        ORDER BY 1,3;

    CURSOR cursorErrorCarga IS
        SELECT car.NUM_FILA NUM_FILA,
                    car.COD_CLIE COD_CLIE,
                    car.COD_MOTI COD_MOTI,
                    mot.DES_MOTI DES_MOTI
          FROM MAE_CARGA_CLASI_CLIEN car,
                    MAE_MOTIV_CARGA_CLASI mot
         WHERE car.NUM_CARG = (SELECT MAX(C.NUM_CARG) FROM MAE_CARGA_CLASI_CLIEN c)
             AND car.COD_MOTI = mot.COD_MOTI
           order by 1;

  BEGIN
    IF(NULL IS NULL) THEN
      SELECT *
        INTO regreporte
        FROM bas_repor_param
       WHERE pais_cod_pais = pscodigopais
         AND nom_repo = 'procesoMAEsegmentacion';

        SELECT UPPER(SUBSTR(VAL_URL, -3, 3))
           INTO lnEntorno
          FROM BAS_PAIS
        WHERE COD_PAIS = pscodigopais;

        SELECT DES_PAIS
           INTO lnDescPais
          FROM BAS_PAIS
        WHERE COD_PAIS = pscodigopais;

        SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') || ' - ' || BP.DES_PAIS || ' - ' || SUBSTR(BCF.COD_PERI, 0, 4) || '-' || SUBSTR(BCF.COD_PERI, 5, 6)
            INTO lnAsuntoFinal
          FROM BAS_PAIS BP, BAS_CTRL_FACT BCF
        WHERE BP.COD_PAIS = BCF.COD_PAIS
             AND BP.COD_PAIS = pscodigopais
             AND BCF.STA_CAMP = 0
             AND BCF.IND_CAMP_ACT = 1;

        lssubject := lnEntorno || ' - ' ||regreporte.val_subj|| ' - ' || lnAsuntoFinal;
        l_mail_conn := log_email.begin_mail(sender     => regreporte.ema_orig,
                                            recipients => regreporte.ema_copi,
                                            subject    => lssubject,
                                            mime_type  => 'text/html');

        lslinea := lslinea ||
                   '<html><head><meta content="text/html charset=ISO-8859-1" http-equiv="content-type"><title></title></head><body>';
        lslinea := lslinea ||
                   '<table border="0" cellpadding="0" cellspacing="0">';
        lslinea := lslinea ||
                   '<tbody><tr><td><font face="Arial" size="2">Se ha realizado la carga de la SEGMENTACION correspondiente al pais ' || lnDescPais || '</font></td></tr><tr><td></td></tr>';
        lslinea := lslinea ||
                   '<tr><td width="95%"><table border="1" cellpadding="0" cellspacing="0"><tr>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Resultados de Carga</b></font></td></tr><tr>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Código Tipo Clasificación</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Tipo Clasificación</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Código Clasificación</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Clasificación</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Cantidad</b></font></td></tr>';

        log_email.write_text(l_mail_conn, lslinea);


        FOR cResultado IN cursorResultadoCarga
        LOOP
          lslinea := '<tr>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     cResultado.COD_TIPO_CLAS || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     cResultado.TIPO_CLASIFICACION || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     cResultado.COD_CLAS || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     cResultado.CLASIFICACION || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     cResultado.CANTIDAD || '</font></td>';
          lslinea := lslinea ||
                     '</tr>';

          log_email.write_text(l_mail_conn, lslinea);

        END LOOP;

        lslinea := '</table></td></tr><tr><td></td></tr>';

        lslinea := lslinea ||'<tr><td></td></tr>';

        ---------------TABLA ERRORES INICIO
        lslinea := lslinea ||
                   '<table border="0" cellpadding="0" cellspacing="0">';
        lslinea := lslinea ||
                   '<tbody><tr><td><font face="Arial" size="2"></font></td></tr><tr><td></td></tr>';
        lslinea := lslinea ||
                   '<tr><td width="95%"><table border="1" cellpadding="0" cellspacing="0"><tr>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Errores de Carga</b></font></td></tr><tr>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Fila</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Código Consultora</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Código Motivo</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#496a9a" align="center" bgcolor="#496a9a"><font face="Arial" size="2" color="#ffffff"><b>Motivo Rechazo</b></font></td></tr>';

        log_email.write_text(l_mail_conn, lslinea);

        FOR cError IN cursorErrorCarga
        LOOP
          lslinea := '<tr>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     cError.NUM_FILA || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     cError.COD_CLIE || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     cError.COD_MOTI || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     cError.DES_MOTI || '</font></td>';
          lslinea := lslinea ||
                     '</tr>';

          log_email.write_text(l_mail_conn, lslinea);

        END LOOP;

        lslinea := '</table></td></tr><tr><td></td></tr>';

        lslinea := lslinea ||'<tr><td></td></tr>';

        -------------------TABLA ERRORES FIN

        lslinea := lslinea ||
                 '<tr><td><br/><br/><br/><br/><font face="Arial" size="2"><strong>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada. </strong></font><br/><br/><br/><br/></td></tr>';
        lslinea := lslinea || '</tbody></table></body></html>';

        log_email.write_text(l_mail_conn, lslinea);

        log_email.end_mail(conn => l_mail_conn);

       /*dbms_output.put_line(lslinea);*/
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR MAE_PR_ENCOR_CARGA_AUTOM_MASCC: **' || ls_sqlerrm);
  END MAE_PR_ENCOR_CARGA_AUTOM_MASCC;

END MAE_PKG_CLASI;
/
