CREATE OR REPLACE PACKAGE "APP_PKG_REPOR" IS

   /* Declaracion de Tipos */
   TYPE TIPOCURSOR IS  REF CURSOR;
   TYPE tRegPedidosServicio IS RECORD (
     TOTAL_PSERVICIO  NUMBER,
     TOTAL_FSERVICIO  NUMBER,
     COD_SAP          MAE_PRODU.COD_SAP%TYPE,
     DES_CORT         MAE_PRODU.DES_CORT%TYPE,
     TOTAL_PRODUCTO   NUMBER
   );
   TYPE tablaPedidosServicio IS TABLE OF tRegPedidosServicio;


   TYPE tRegPedidosBolsa IS RECORD (
     FEC_FACT       DATE,
     TOTAL_BOLSA    NUMBER,
     TOTAL_PEDIDOS  NUMBER,
     PORCENTAJE     NUMBER
    );
   TYPE tablaPedidosBolsa IS TABLE OF tRegPedidosBolsa;


   /* Declaracion de Variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(150);
   W_FILAS      NUMBER:=5000;

/***************************************************************************
Descripcion    	  : Devuelve Lista de Reporte de Informacion de Pedidos de
                    Servicio
Fecha Creacion 	  : 04/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PEDID_SERVI(
    psCodPais VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodPeriodo VARCHAR2)
RETURN  tablaPedidosServicio PIPELINED;


/***************************************************************************
Descripcion    	  : Devuelve Lista de Reporte de Informacion de Pedidos de
                    Servicio
Fecha Creacion 	  : 04/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PEDID_BOLSA(
    psCodPais VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodPeriodo VARCHAR2)
RETURN  tablaPedidosBolsa PIPELINED;


END APP_PKG_REPOR;
/

CREATE OR REPLACE PACKAGE BODY "APP_PKG_REPOR" IS

/***************************************************************************
Descripcion    	  : Devuelve Lista de Reporte de Informacion de Pedidos de
                    Servicio
Fecha Creacion 	  : 04/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PEDID_SERVI(
    psCodPais VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodPeriodo VARCHAR2)
RETURN  tablaPedidosServicio PIPELINED
IS
  lnTotalPedidosServicio      NUMBER;
  lnTotalFueraServicio        NUMBER;
  lnIdPais        seg_pais.oid_pais%TYPE;
  lnIdCanal       seg_canal.oid_cana%TYPE;
  lnIdMarca       seg_marca.oid_marc%TYPE;
  lnIdPeriodo     seg_perio_corpo.oid_peri%TYPE;

  tablaRegistro   tablaPedidosServicio;

  CURSOR cursorRegistro(PnTotalPedidosServicio NUMBER, PnTotalFueraServicio NUMBER) IS
  SELECT
  PnTotalPedidosServicio,
  PnTotalFueraServicio,
	MAE_PRODU.COD_SAP,
	MAE_PRODU.DES_CORT,
	COUNT (1) TOTAL_PRODUCTO
  FROM
  	(
  		SELECT
  			SOCA_OID_SOLI_CABE,
  			SUM(OC) AS TOTAL_OC,
  			SUM(NO_OC) AS TOTAL_NO_OC,
  			SUM(NUM_SOLIC) AS TOTAL_SOLIC
  		FROM
  			(
  				SELECT
  					DISTINCT SOCA_OID_SOLI_CABE,
  					CASE
  						WHEN IND_OC = 1 THEN COUNT(1)
  						ELSE 0
  					END AS OC,
  					CASE
  						WHEN IND_OC = 0 THEN COUNT(1)
  						ELSE 0
  					END AS NO_OC,
  					COUNT(1) AS NUM_SOLIC
  				FROM
  					PED_SOLIC_CABEC
  				WHERE
  					PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1
  					AND PED_SOLIC_CABEC.FEC_FACT IS NOT NULL
  					AND PED_SOLIC_CABEC.PERD_OID_PERI = lnIdPeriodo
  				GROUP BY
  					SOCA_OID_SOLI_CABE,
  					IND_OC
  			)
  		GROUP BY
  			SOCA_OID_SOLI_CABE
  	) SOLIC,
  	CRA_PERIO B,
  	SEG_PERIO_CORPO C,
    INT_LAR_TIPO_SOLICI_PEDIDO_DIS,
  	MAE_PRODU,
  	PED_SOLIC_POSIC,
  	PED_SOLIC_CABEC,
  	PED_SOLIC_CABEC CONSO
  WHERE CONSO.PERD_OID_PERI = B.OID_PERI
  	AND B.PERI_OID_PERI = C.OID_PERI
  	AND B.MARC_OID_MARC = lnIdMarca
  	AND B.CANA_OID_CANA = lnIdCanal
  	AND B.PAIS_OID_PAIS = lnIdPais
  	AND B.OID_PERI = lnIdPeriodo
  	AND INT_LAR_TIPO_SOLICI_PEDIDO_DIS.TSPA_OID_TIPO_SOLI_PAIS = CONSO.TSPA_OID_TIPO_SOLI_PAIS
  	AND CONSO.IND_INTE_LARI_GENE = 1
  	AND CONSO.OID_SOLI_CABE = SOLIC.SOCA_OID_SOLI_CABE
  	AND SOLIC.TOTAL_OC = 0
  	AND PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE = CONSO.OID_SOLI_CABE
  	AND PED_SOLIC_CABEC.OID_SOLI_CABE = PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE
  	AND PED_SOLIC_POSIC.PROD_OID_PROD = MAE_PRODU.OID_PROD
  	AND (IND_DENT_FUER_CAJA_BOLS = 'B' OR IND_DENT_FUER_CAJA_BOLS IS NULL)
  GROUP BY
  	MAE_PRODU.COD_SAP,
  	MAE_PRODU.DES_CORT;


BEGIN
  -- Obteniendo ID's
  lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lnIdPeriodo   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);
  -- Obteniendo Cantidad de Pedidos directos
  SELECT COUNT (CONSO.OID_SOLI_CABE)
  INTO
     lnTotalPedidosServicio
  FROM
  	(
  		SELECT
  			SOCA_OID_SOLI_CABE,
  			SUM(OC) AS TOTAL_OC,
  			SUM(NO_OC) AS TOTAL_NO_OC,
  			SUM(NUM_SOLIC) AS TOTAL_SOLIC
  		FROM
  			(
  				SELECT
  					DISTINCT SOCA_OID_SOLI_CABE,
  					CASE
  						WHEN IND_OC = 1 THEN COUNT(1)
  						ELSE 0
  					END AS OC,
  					CASE
  						WHEN IND_OC = 0 THEN COUNT(1)
  						ELSE 0
  					END AS NO_OC,
  					COUNT(1) AS NUM_SOLIC
  				FROM
  					PED_SOLIC_CABEC
  				WHERE
  					PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1
  					AND PED_SOLIC_CABEC.FEC_FACT IS NOT NULL
  					AND PED_SOLIC_CABEC.PERD_OID_PERI = lnIdPeriodo
  				GROUP BY
  					SOCA_OID_SOLI_CABE,
  					IND_OC
  			)
  		GROUP BY
  			SOCA_OID_SOLI_CABE
  	) SOLIC,
  	CRA_PERIO B,
  	SEG_PERIO_CORPO C,
  	INT_LAR_TIPO_SOLICI_PEDIDO_DIS,
  	PED_SOLIC_CABEC CONSO
  WHERE CONSO.PERD_OID_PERI = B.OID_PERI
  	AND B.PERI_OID_PERI = C.OID_PERI
  	AND B.MARC_OID_MARC = lnIdMarca
  	AND B.CANA_OID_CANA = lnIdCanal
  	AND B.PAIS_OID_PAIS = lnIdPais
  	AND B.OID_PERI = lnIdPeriodo
  	AND INT_LAR_TIPO_SOLICI_PEDIDO_DIS.TSPA_OID_TIPO_SOLI_PAIS = CONSO.TSPA_OID_TIPO_SOLI_PAIS
  	AND CONSO.IND_INTE_LARI_GENE = 1
  	AND CONSO.OID_SOLI_CABE = SOLIC.SOCA_OID_SOLI_CABE
  	AND SOLIC.TOTAL_OC = 0;

  -- Obteniendo Pedidos de Servicio con solo fuera de pedido
  SELECT
  	COUNT (CONSO.OID_SOLI_CABE)
  INTO
    lnTotalFueraServicio
  FROM
  	(
  		SELECT
  			SOCA_OID_SOLI_CABE,
  			SUM(OC) AS TOTAL_OC,
  			SUM(NO_OC) AS TOTAL_NO_OC,
  			SUM(CAJA) AS TOTAL_CAJA,
  			SUM(FUERA_PEDIDO) AS TOTAL_FUERA_PEDIDO,
  			SUM(POSIC) AS TOTAL_POSIC,
  			SUM(NUM_SOLIC) AS TOTAL_SOLIC
  		FROM
  			(
  				SELECT
  					DISTINCT PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE,
  					CASE
  						WHEN IND_OC = 1 THEN COUNT(1)
  						ELSE 0
  					END AS OC,
  					CASE
  						WHEN IND_OC = 0 THEN COUNT(1)
  						ELSE 0
  					END AS NO_OC,
  					SUM(CAJA) AS CAJA,
  					SUM(BOLSA) + SUM(FUERA) AS FUERA_PEDIDO,
  					SUM(NUM_POSIC) AS POSIC,
  					COUNT(1) AS NUM_SOLIC
  				FROM
  					PED_SOLIC_CABEC,
  					(
  						SELECT
  							PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE,
  							CASE
  								WHEN IND_DENT_FUER_CAJA_BOLS = 'C' THEN COUNT (1)
  								ELSE 0
  							END AS CAJA,
  							CASE
  								WHEN IND_DENT_FUER_CAJA_BOLS = 'B' THEN COUNT (1)
  								ELSE 0
  							END AS BOLSA,
  							CASE
  								WHEN IND_DENT_FUER_CAJA_BOLS IS NULL THEN COUNT (1)
  								ELSE 0
  							END AS FUERA,
  							COUNT(1) AS NUM_POSIC
  						FROM
  							PED_SOLIC_POSIC,
  							PED_SOLIC_CABEC A
  						WHERE
  							A.OID_SOLI_CABE = PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE
  							AND A.PERD_OID_PERI = lnIdPeriodo
  						GROUP BY
  							PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE,
  							IND_DENT_FUER_CAJA_BOLS
  					) POSIC
  				WHERE
  					PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1
  					AND PED_SOLIC_CABEC.FEC_FACT IS NOT NULL
  					AND PED_SOLIC_CABEC.OID_SOLI_CABE = POSIC.SOCA_OID_SOLI_CABE
  					AND PED_SOLIC_CABEC.PERD_OID_PERI = lnIdPeriodo
  				GROUP BY
  					PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE,
  					IND_OC
  			)
  		GROUP BY
  			SOCA_OID_SOLI_CABE
  	) SOLIC,
  	CRA_PERIO B,
  	SEG_PERIO_CORPO C,
  	INT_LAR_TIPO_SOLICI_PEDIDO_DIS,
  	PED_SOLIC_CABEC CONSO
  WHERE CONSO.PERD_OID_PERI = B.OID_PERI
  	AND B.PERI_OID_PERI = C.OID_PERI
  	AND B.MARC_OID_MARC = lnIdMarca
  	AND B.CANA_OID_CANA = lnIdCanal
  	AND B.PAIS_OID_PAIS = lnIdPais
  	AND B.OID_PERI = lnIdPeriodo
  	AND INT_LAR_TIPO_SOLICI_PEDIDO_DIS.TSPA_OID_TIPO_SOLI_PAIS = CONSO.TSPA_OID_TIPO_SOLI_PAIS
  	AND CONSO.IND_INTE_LARI_GENE = 1
  	AND CONSO.OID_SOLI_CABE = SOLIC.SOCA_OID_SOLI_CABE
  	AND SOLIC.TOTAL_OC = 0
  	AND SOLIC.TOTAL_CAJA = 0;

  OPEN cursorRegistro(lnTotalPedidosServicio, lnTotalFueraServicio) ;
  LOOP
       FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
       IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             PIPE ROW(tablaRegistro(x));
         END LOOP;
       END IF;
       EXIT WHEN cursorRegistro%NOTFOUND ;
  END LOOP ;
  CLOSE cursorRegistro;
  RETURN ;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_PEDID_SERVI: '||ls_sqlerrm);

END PER_FN_OBTIE_PEDID_SERVI;


/***************************************************************************
Descripcion    	  : Devuelve Lista de Reporte de Informacion de Pedidos de
                    bolsa
Fecha Creacion 	  : 04/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PEDID_BOLSA(
    psCodPais VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodPeriodo VARCHAR2)
RETURN  tablaPedidosBolsa PIPELINED
IS
  lnIdPais        seg_pais.oid_pais%TYPE;
  lnIdCanal       seg_canal.oid_cana%TYPE;
  lnIdMarca       seg_marca.oid_marc%TYPE;
  lnIdPeriodo     seg_perio_corpo.oid_peri%TYPE;

  TYPE tRegPedidosIni IS RECORD (
     FEC_FACT       DATE,
     TOTAL_BOLSA  NUMBER
   );
  TYPE tablaPedidosIni IS TABLE OF tRegPedidosIni;

  tablaRegistroIni   tablaPedidosIni;
  tablaRegistro      tablaPedidosBolsa;
  lnTotalPedidos     NUMBER;

  CURSOR cursorRegistro IS
  SELECT
  	CONSO.FEC_FACT,
  	COUNT (CONSO.OID_SOLI_CABE) TOTAL_BOLSA
  FROM
  	(
  		SELECT
  			SOCA_OID_SOLI_CABE,
  			SUM(OC) AS TOTAL_OC,
  			SUM(NO_OC) AS TOTAL_NO_OC,
  			SUM(CAJA) AS TOTAL_CAJA,
  			SUM(BOLSA) AS TOTAL_BOLSA,
  			SUM(POSIC) AS TOTAL_POSIC,
  			SUM(NUM_SOLIC) AS TOTAL_SOLIC
  		FROM
  			(
  				SELECT
  					DISTINCT PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE,
  					CASE
  						WHEN IND_OC = 1 THEN COUNT(1)
  						ELSE 0
  					END AS OC,
  					CASE
  						WHEN IND_OC = 0 THEN COUNT(1)
  						ELSE 0
  					END AS NO_OC,
  					SUM(CAJA) AS CAJA,
  					SUM(BOLSA) AS BOLSA,
  					SUM(NUM_POSIC) AS POSIC,
  					COUNT(1) AS NUM_SOLIC
  				FROM
  					PED_SOLIC_CABEC,
  					(
  						SELECT
  							PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE,
  							CASE
  								WHEN IND_DENT_FUER_CAJA_BOLS = 'C' THEN COUNT (1)
  								ELSE 0
  							END AS CAJA,
  							CASE
  								WHEN IND_DENT_FUER_CAJA_BOLS = 'B' THEN COUNT (1)
  								ELSE 0
  							END AS BOLSA,
  							CASE
  								WHEN IND_DENT_FUER_CAJA_BOLS IS NULL THEN COUNT (1)
  								ELSE 0
  							END AS FUERA,
  							COUNT(1) AS NUM_POSIC
  						FROM
  							PED_SOLIC_POSIC,
  							PED_SOLIC_CABEC A
  						WHERE
  							A.OID_SOLI_CABE = PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE
  							AND A.PERD_OID_PERI = lnIdPeriodo
  						GROUP BY
  							PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE,
  							IND_DENT_FUER_CAJA_BOLS
  					) POSIC
  				WHERE
  					PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1
  					AND PED_SOLIC_CABEC.FEC_FACT IS NOT NULL
  					AND PED_SOLIC_CABEC.PERD_OID_PERI = lnIdPeriodo
  					AND PED_SOLIC_CABEC.OID_SOLI_CABE = POSIC.SOCA_OID_SOLI_CABE
  				GROUP BY
  					PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE,
  					IND_OC
  			)
  		GROUP BY
  			SOCA_OID_SOLI_CABE
  	) SOLIC,
  	CRA_PERIO B,
  	SEG_PERIO_CORPO C,
  	INT_LAR_TIPO_SOLICI_PEDIDO_DIS,
  	PED_SOLIC_CABEC CONSO
  WHERE CONSO.PERD_OID_PERI = B.OID_PERI
  	AND B.PERI_OID_PERI = C.OID_PERI
  	AND B.MARC_OID_MARC = lnIdMarca
  	AND B.CANA_OID_CANA = lnIdCanal
  	AND B.PAIS_OID_PAIS = lnIdPais
  	AND B.OID_PERI = lnIdPeriodo
  	AND INT_LAR_TIPO_SOLICI_PEDIDO_DIS.TSPA_OID_TIPO_SOLI_PAIS = CONSO.TSPA_OID_TIPO_SOLI_PAIS
  	AND CONSO.IND_INTE_LARI_GENE = 1
  	AND CONSO.OID_SOLI_CABE = SOLIC.SOCA_OID_SOLI_CABE
  	AND SOLIC.TOTAL_BOLSA >= 1
  GROUP BY
  	CONSO.FEC_FACT;

BEGIN

  -- Obteniendo ID's
  lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lnIdPeriodo   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);
  /* Recorriendo Lista */
  OPEN cursorRegistro ;
  LOOP
       FETCH cursorRegistro BULK COLLECT INTO tablaRegistroIni LIMIT W_FILAS;
       IF tablaRegistroIni.COUNT > 0 THEN
         FOR x IN tablaRegistroIni.FIRST .. tablaRegistroIni.LAST LOOP
             BEGIN
              -- Obteniendo Pedidos totales
              SELECT
              	count (conso.oid_soli_cabe)
              INTO
                lnTotalPedidos
              FROM
              	cra_perio b,
              	seg_perio_corpo c,
              	INT_LAR_TIPO_SOLICI_PEDIDO_DIS,
              	ped_solic_cabec conso
              WHERE conso.perd_oid_peri = b.oid_peri
              	AND b.peri_oid_peri = c.oid_peri
              	and b.MARC_OID_MARC = lnIdMarca
              	and b.CANA_OID_CANA = lnIdCanal
              	and b.PAIS_OID_PAIS = lnIdPais
              	and b.oid_peri = lnIdPeriodo
              	and INT_LAR_TIPO_SOLICI_PEDIDO_DIS.TSPA_OID_TIPO_SOLI_PAIS = conso.tspa_oid_tipo_soli_pais
              	and conso.IND_INTE_LARI_GENE = 1
              	and conso.IND_TS_NO_CONSO = 0
              	and conso.FEC_FACT is not NULL;
            EXCEPTION
            WHEN no_data_found THEN
                 lnTotalPedidos := 0.0;
            END;
            tablaRegistro(x).FEC_FACT := tablaRegistroIni(x).FEC_FACT;
            tablaRegistro(x).TOTAL_BOLSA := tablaRegistroIni(x).TOTAL_BOLSA;
            tablaRegistro(x).TOTAL_PEDIDOS :=lnTotalPedidos;
            IF lnTotalPedidos = 0 THEN
               tablaRegistro(x).porcentaje := 0;
            ELSE
               tablaRegistro(x).porcentaje :=  tablaRegistro(x).TOTAL_BOLSA / lnTotalPedidos ;
            END IF;
            PIPE ROW(tablaRegistro(x));
         END LOOP;
       END IF;
       EXIT WHEN cursorRegistro%NOTFOUND ;
  END LOOP ;
  CLOSE cursorRegistro;
  RETURN ;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_PEDID_BOLSA: '||ls_sqlerrm);

END PER_FN_OBTIE_PEDID_BOLSA;





END APP_PKG_REPOR;
/

