CREATE OR REPLACE PACKAGE "INT_PKG_MYEBE" IS
  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  w_filas    NUMBER := 1000;
  /* Declaracion de procedures */
  /**************************************************************************
  Descripcion        : Genera el archivo para la interfaz MYE Movimientos de
                       Cuenta Corriente
  Fecha Creacion     : 13/02/2007
  Autor              : Jose Escalante / Carlos Bazalar
  ***************************************************************************/
  PROCEDURE int_pr_mye_movim_cuent_corri
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pdfechaultimo    DATE
  );

  /**************************************************************************
  Descripcion        : Retorna la descripcion de la transaccion de reclamos
                       concatenando los valores para cuando el query retorna
             mas de un fila.
  Fecha Creacion     : 16/11/2007
  Autor              : Carlos Hurtado
  ***************************************************************************/
  FUNCTION int_fn_mye_descr_trans_recla(pnnumerosolicitud NUMBER)
    RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Interfaces Diarias
  Fecha Creacion    : 04/06/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_mye_envio_diari
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psperiodo          VARCHAR2,
    pscodigomarca      VARCHAR2,
    pscodigocanal      VARCHAR2,
    psfechafacturacion VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de cabeceras de pedidos
  Fecha Creacion    : 05/06/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_mye_cabec_pedid
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psperiodo          VARCHAR2,
    pscodigomarca      VARCHAR2,
    pscodigocanal      VARCHAR2,
    pscodigotipoclien  VARCHAR2,
    psfechafacturacion VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de archivos de premios
  Fecha Creacion    : 05/06/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_mye_archi_premi
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Tipo de producto catalogo
  Fecha Creacion    : 06/11/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_mye_produ_catal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodcanal       VARCHAR2,
    pscodmarca       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Faltantes anunciados
  Fecha Creacion    : 06/11/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_mye_falta_anunc
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2
  );

  /***************************************************************************
Descripcion       : Genera Interfaz de Faltantes anunciados - Limite de venta
Fecha Creacion    : 22/08/2012
Autor             : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE INT_PR_MYE_FALAN_LIVTA(psCodigoPais       VARCHAR2,
                                 psCodigoSistema    VARCHAR2,
                                 psCodigoInterfaz   VARCHAR2,
                                 psNombreArchivo    VARCHAR2,
                                 psCodigoPeriodo	  VARCHAR2);

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo Control envio OCR
                      a SICC
  Fecha Creacion    : 01/04/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_mye_actua_datos_consu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo Control envio OCR
                      a SICC
  Fecha Creacion    : 01/04/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_mye_proce_actua_datos;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Historicos de posibles egresos
  Fecha Creacion    : 08/11/2010
  Parametros
    pscodigopais       : codigo pais,
    pscodigosistema    : codigo sistema,
    pscodigointerfaz   : codigo interfaz,
    psnombrearchivo    : nombre archivo,
    psperiodo          : periodo,
    pscodigomarca      : marca,
    pscodigocanal      :canal
  Autor             : Sergio Buchelli Silva
  ***************************************************************************/
  PROCEDURE INT_PR_MYE_ENVIO_HISTO_PEGS
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psperiodo          VARCHAR2,
    pscodigomarca      VARCHAR2,
    pscodigocanal      VARCHAR2
  );

/***********************************************************************************
  Descripcion        : Retorna el monto factura de la consultora en el periodo dado
                        si no facturo en el periodo retorna cero
  Fecha Creacion     : 08/11/2010
  Parametros Entrada :
    psCodigoPais       Codigo Pais
    psCodigoPeriodo    Codigo Periodo
    psOidCliente       Oid Cliente

  Autor              : Sergio Buchelli
  ***************************************************************************/
  FUNCTION INT_FN_DEVUE_MONTO_FACTU_NCAMP(
    pnOidPeriodo  NUMBER,
    psOidCliente  NUMBER ) RETURN NUMBER;


/***********************************************************************************
  Descripcion        : Proceso para crear nueva interfaz de clientes para web
  Fecha Creacion     : 23/08/2012
   Parametros Entrada :
     psCodigoPais       Codigo Pais
     psCodigoSistema    Codigo Sistema
     psCodigoInterfaz   Codigo Interfaz
     psNombreArchivo    Nombre Archivo
    psCodigoPeriodo    Codigo Periodo

  Autor             : Sergio Buchelli
  ***************************************************************************/
PROCEDURE INT_PR_MYE_ENVIA_CLIEN_WEB(psCodigoPais   VARCHAR2,
                                 psCodigoSistema    VARCHAR2,
                                 psCodigoInterfaz   VARCHAR2,
                                 psNombreArchivo    VARCHAR2,
                                 psCodigoPeriodo	VARCHAR2);

/***********************************************************************************
  Descripcion        : Proceso para crear nueva interfaz de maestros de incobrables
  Fecha Creacion     : 30/01/2013
   Parametros Entrada :
     psCodigoPais       Codigo Pais
     psCodigoSistema    Codigo Sistema
     psCodigoInterfaz   Codigo Interfaz
     psNombreArchivo    Nombre Archivo

  Autor             : Ivan Tocto
  ***************************************************************************/
PROCEDURE INT_PR_MYE_ENVIA_MAEST_INCOB(psCodigoPais VARCHAR2,
                                 psCodigoSistema    VARCHAR2,
                                 psCodigoInterfaz   VARCHAR2,
                                 psNombreArchivo    VARCHAR2,
                                 psNumeroLote       VARCHAR2);

/***************************************************************************
  Descripcion       : Genera Interfaz de RECEPCIONAR ACTIVACION FLEXIPAGO WEB.
  Fecha Creacion    : 26/05/2014
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_mye_activ_flexi_web
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  );
  
  /***************************************************************************
  Descripcion       : Genera Interfaz de RECEPCIONAR PREMIOS WEB.
  Fecha Creacion    : 08/02/2016
  Autor             : Segundo Leiva
  ***************************************************************************/
  PROCEDURE INT_PR_MYR_RECEP_PREMI_WEB
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psusuario     VARCHAR2
  );

END int_pkg_myebe;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_MYEBE" IS

/**************************************************************************
Descripcion        : Genera el archivo para la interfaz MYE Movimientos de
                     Cuenta Corriente
Fecha Creacion     : 13/02/2007
Autor              : Jose Escalante / Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_MYE_MOVIM_CUENT_CORRI
(  psCodigoPais     VARCHAR2,
   psCodigoSistema  VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo  VARCHAR2,
   pdFechaUltimo    DATE
)
IS
  lnOidInicial  NUMBER;
  lnOidFinal    NUMBER;

  CURSOR c_interfaz(psNumEjecucion VARCHAR2, pnOidInicial NUMBER, pnOidFinal NUMBER) IS
  SELECT  DISTINCT
		   NVL(pais.COD_PAIS, ' ')  AS CODPAIS,
       NVL(cliente.COD_CLIE, ' ') AS CODCON,
       psNumEjecucion AS NUMSEC,
      (CASE subproceso.val_indi_cons
          WHEN 'D' THEN
             (CASE
                 WHEN movctacte.imp_movi >= 0 THEN 'C'
              ELSE 'A'
              END)
           WHEN 'H' THEN
              (CASE
                 WHEN movctacte.imp_movi*-1 >= 0 THEN 'C'
              ELSE 'A'
              END)
       ELSE ' '
       END) AS TIPOPE,

       (CASE
          WHEN cargodetalle.fec_pago_banc IS NULL THEN TO_CHAR(cargodetalle.fec_movi, 'YYYYMMDD')
          ELSE TO_CHAR(cargodetalle.fec_pago_banc, 'YYYYMMDD')
        END) AS FECOPE,

        TO_CHAR(movctacte.FEC_VENC, 'YYYYMMDD') AS FECVEN,

		    periodocorporativo.cod_peri as ANYCAM,

        (CASE proceso.cod_proc

              WHEN 'CCC001' THEN
                (CASE
                   WHEN movctacte.imp_movi >= 0 THEN 'PED. C-' || periodocorporativo.cod_peri || ' B.DESPACHO ' ||  movctacte.VAL_EJER_CUOT || LPAD(movctacte.NUM_IDEN_CUOT, 8, '0')
                   ELSE nvl(INT_FN_MYE_DESCR_TRANS_RECLA(TO_NUMBER(movctacte.VAL_EJER_CUOT || LPAD (movctacte.NUM_IDEN_CUOT, 8, '0'))), 'Reclamo C-' || periodocorporativo.cod_peri)
                   END)
              WHEN 'CCCP01' THEN 'Percepcion Facturacion PED. C-' || periodocorporativo.cod_peri
              WHEN 'CCCA01' THEN 'Descuento a Personal'
              WHEN 'TES001' THEN (SELECT 'Pago ' || CCC_BANCO.DES_BANC
                                    FROM CCC_CUENT_CORRI_BANCA,
                                         CCC_SUCUR,
                                         CCC_BANCO
                                    WHERE (cargodetalle.CCBA_OID_CUEN_CORR_BANC = CCC_CUENT_CORRI_BANCA.OID_CUEN_CORR_BANC)
                                    AND   (CCC_CUENT_CORRI_BANCA.SUCU_OID_SUCU = CCC_SUCUR.OID_SUCU)
                                    AND   (CCC_SUCUR.CBAN_OID_BANC = CCC_BANCO.OID_BANC)
                                 )
              ELSE
                (CASE WHEN (proceso.COD_PROC <> 'TES001') AND (proceso.COD_PROC <> 'CCC001') THEN
                   (SELECT GEN_I18N_SICC_PAIS.VAL_I18N
                      FROM CCC_TIPO_CARGO_ABONO,
                           GEN_I18N_SICC_PAIS,
                           SEG_IDIOM
                      WHERE (tipoabono.TCAB_OID_TCAB = CCC_TIPO_CARGO_ABONO.OID_TIPO_CARG_ABON)
                      AND   (GEN_I18N_SICC_PAIS.ATTR_ENTI = 'CCC_TIPO_CARGO_ABONO')
                      AND   (SEG_IDIOM.COD_IDIO = 'ES')
                      AND   (SEG_IDIOM.OID_IDIO = GEN_I18N_SICC_PAIS.IDIO_OID_IDIO)
                      AND   (GEN_I18N_SICC_PAIS.VAL_OID = CCC_TIPO_CARGO_ABONO.OID_TIPO_CARG_ABON)
                   )
                 END)
            END) AS DESTRA,

         nvl(abs(movctacte.imp_movi),cargodetalle.imp) AS MONOPE
  FROM
  		 ccc_detal_cargo_abono_direc cargodetalle,
       ccc_cabec_carga_abono_direc cargocabecera,
       ccc_tipo_abono_subpr tipoabono,
       ccc_subpr subproceso,
  		 ccc_proce proceso,
  		 mae_clien cliente,
  		 seg_pais pais,
  		 cra_perio periodocronograma,
       seg_perio_corpo periodocorporativo,
       ccc_movim_cuent_corri movctacte
  WHERE
  		 cargodetalle.ccad_oid_cabe_carg = cargocabecera.oid_cabe_carg
       AND cargodetalle.tasp_oid_tipo_abon_subp = tipoabono.oid_tipo_abon_subp
       AND tipoabono.subp_oid_subp = subproceso.oid_subp
       AND subproceso.val_indi_cons IN ('A', 'D', 'H')
       AND cargodetalle.mvcc_oid_movi_cc = movctacte.oid_movi_cc(+)
  	   AND pais.oid_pais = cliente.pais_oid_pais
  	   AND pais.cod_pais = psCodigoPais
  	   AND cargocabecera.pais_oid_pais = pais.oid_pais
  	   AND cliente.oid_clie = cargodetalle.clie_oid_clie
  	   AND subproceso.ccpr_oid_proc = proceso.oid_proc
  	   AND periodocronograma.peri_oid_peri = periodocorporativo.oid_peri (+)
       AND movctacte.perd_oid_peri = periodocronograma.oid_peri (+)
       AND cargodetalle.oid_deta_carg_abon_dire > pnOidInicial
       AND cargodetalle.oid_deta_carg_abon_dire <= pnOidFinal
   ORDER BY 5 DESC;

  TYPE interfazRec IS RECORD   (
    codigoPais               SEG_PAIS.COD_PAIS%TYPE,
    codigoConsultora         MAE_CLIEN.COD_CLIE%TYPE,
    numeroLote               VARCHAR2(100),
    tipoOperacion            VARCHAR2(100),
    fechaOperacion           VARCHAR2(100),
    fechaVencimiento         VARCHAR2(100),
    anyoCampanya             SEG_PERIO_CORPO.COD_PERI%TYPE,
    descripcionTransaccion   VARCHAR2(100),
    montoOperacion           CCC_DETAL_CARGO_ABONO_DIREC.IMP%TYPE
   );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lsNombreArchivoZip  VARCHAR2(50);
  lsNumEjecucion      VARCHAR2(10);
  lnNumEjecucion      NUMBER;
  ldFechaAnteriorEjecucion   DATE;
  lbAbrirUtlFile  BOOLEAN;
BEGIN

    /* Obteniendo Numero de Ejecucion */
    BEGIN
       SELECT NUM_EJIN
       INTO lnNumEjecucion
       FROM BAS_INTER B
					 WHERE B.PAIS_COD_PAIS = psCodigoPais
					 AND   B.SIST_COD_SIST = psCodigoSistema
					 AND   B.COD_INTE = psCodigoInterfaz;
       lnNumEjecucion := lnNumEjecucion + 1;
       lsNumEjecucion := TRIM(to_char(lnNumEjecucion));
    EXCEPTION
    WHEN no_data_found THEN
         lsNumEjecucion := '1';
    END;

    /* Obteniendo oid final de la tabla CCC_DETAL_CARGO_ABONO_DIREC */
    SELECT MAX(A.OID_DETA_CARG_ABON_DIRE)
    INTO lnOidFinal
    FROM CCC_DETAL_CARGO_ABONO_DIREC A;

    /* Obteniendo oid inicial de la tabla BAS_INTER_CONTR_REGIS_PROCE */
    BEGIN
      SELECT A.OID_ULTI_REGI_PROC, A.FEC_ULTI_EJEC
      INTO lnOidInicial, ldFechaAnteriorEjecucion
      FROM BAS_INTER_CONTR_REGIS_PROCE A
      WHERE A.PAIS_COD_PAIS = psCodigoPais
        AND A.SIST_COD_SIST = psCodigoSistema
        AND A.INTE_COD_INTE = psCodigoInterfaz
        AND A.COD_CTRL_REGI_PROC= '01';

      UPDATE BAS_INTER_CONTR_REGIS_PROCE A
      SET
           A.OID_ANTE_REGI_PROC = lnOidInicial,
           A.OID_ULTI_REGI_PROC = lnOidFinal,
           A.FEC_ANTE_EJEC = ldFechaAnteriorEjecucion,
           A.FEC_ULTI_EJEC = SYSDATE
      WHERE A.PAIS_COD_PAIS = psCodigoPais
        AND A.SIST_COD_SIST = psCodigoSistema
        AND A.INTE_COD_INTE = psCodigoInterfaz
        AND A.COD_CTRL_REGI_PROC= '01';

    EXCEPTION
    WHEN no_Data_found THEN
         lnOidInicial := 0;
    END;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsNumEjecucion, lnOidInicial, lnOidFinal);
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
           lsLinea :=  interfazRecord(x).codigoPais   ||';'||
                 interfazRecord(x).codigoConsultora  ||';'||
                 interfazRecord(x).numeroLote   ||';'||
                 interfazRecord(x).tipoOperacion   ||';'||
                 interfazRecord(x).fechaOperacion  ||';'||
                 interfazRecord(x).fechaVencimiento  ||';'||
                 interfazRecord(x).anyoCampanya   ||';'||
                 interfazRecord(x).descripcionTransaccion ||';'||
                 interfazRecord(x).montoOperacion;
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;
    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MYE_MOVIM_CUENT_CORRI: '||ls_sqlerrm);
END INT_PR_MYE_MOVIM_CUENT_CORRI;

/**************************************************************************
Descripcion        : Retorna la descripcion de la transaccion de reclamos
                     concatenando los valores para cuando el query retorna
					 mas de un fila.
Fecha Creacion     : 16/11/2007
Autor              : Carlos Hurtado
***************************************************************************/
FUNCTION INT_FN_MYE_DESCR_TRANS_RECLA
( pnNumeroSolicitud NUMBER
) RETURN VARCHAR2 IS

CURSOR c_descTrans IS
SELECT DISTINCT
GEN_I18N_SICC_COMUN.VAL_I18N,
SEG_PERIO_CORPO.COD_PERI,
REC_CABEC_RECLA.NUM_RECL
FROM
PED_SOLIC_CABEC SOLI,
REC_CABEC_RECLA,
REC_OPERA_RECLA,
REC_LINEA_OPERA_RECLA,
PED_SOLIC_CABEC CONS,
PED_SOLIC_POSIC,
REC_SOLIC_OPERA,
CRA_PERIO,
SEG_PERIO_CORPO,
PED_TIPO_SOLIC_PAIS,
PED_TIPO_SOLIC,
GEN_I18N_SICC_COMUN
WHERE (SOLI.VAL_NUME_SOLI = pnNumeroSolicitud)
AND (REC_CABEC_RECLA.OID_CABE_RECL = REC_OPERA_RECLA.CARE_OID_CABE_RECL)
AND (REC_LINEA_OPERA_RECLA.OPRE_OID_OPER_RECL = REC_OPERA_RECLA.OID_OPER_RECL)
AND (REC_LINEA_OPERA_RECLA.SOPO_OID_SOLI_POSI = PED_SOLIC_POSIC.OID_SOLI_POSI)
AND (REC_SOLIC_OPERA.OPRE_OID_OPER_RECL = REC_LINEA_OPERA_RECLA.OPRE_OID_OPER_RECL)
AND (REC_SOLIC_OPERA.SOCA_OID_SOLI_CABE = CONS.OID_SOLI_CABE)
AND (CONS.SOCA_OID_SOLI_CABE = SOLI.OID_SOLI_CABE)
AND (PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI = PED_TIPO_SOLIC.OID_TIPO_SOLI)
AND (SOLI.PERD_OID_PERI = CRA_PERIO.OID_PERI)
AND (SOLI.TSPA_OID_TIPO_SOLI_PAIS=PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS)
AND (CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI)
AND (GEN_I18N_SICC_COMUN.ATTR_ENTI = 'PED_TIPO_SOLIC')
AND (GEN_I18N_SICC_COMUN.VAL_OID = PED_TIPO_SOLIC.OID_TIPO_SOLI);

TYPE descRec IS RECORD   (
  descripcionTipoSolicitud GEN_I18N_SICC_COMUN.VAL_I18N%TYPE,
  codigoPeriodo            SEG_PERIO_CORPO.COD_PERI%TYPE,
  numeroReclamo            REC_CABEC_RECLA.NUM_RECL%TYPE
);

TYPE descRecTab  IS TABLE OF descRec ;
descRecord descRecTab;

lsDescripcion VARCHAR2(100);
lnContador    NUMBER := 0;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_descTrans;
    LOOP
       FETCH c_descTrans BULK COLLECT INTO descRecord LIMIT W_FILAS;
       IF descRecord.COUNT > 0 THEN
          FOR x IN descRecord.FIRST .. descRecord.LAST LOOP
		    IF x = 1 THEN
			    lsDescripcion :=
			    substr(descRecord(x).descripcionTipoSolicitud || ' C-' ||
                descRecord(x).codigoPeriodo || ' NRO. ' ||
                descRecord(x).numeroReclamo,1,100);
			ELSE
			    lsDescripcion := substr(lsDescripcion || ' ' || descRecord(x).numeroReclamo,1,100);
			END IF;
          END LOOP;
       END IF;
       EXIT WHEN c_descTrans%NOTFOUND;
    END LOOP;
    CLOSE c_descTrans;
	RETURN lsDescripcion;
END INT_FN_MYE_DESCR_TRANS_RECLA;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Interfaces Diarias
Fecha Creacion    : 04/06/2008
Autor             : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE INT_PR_MYE_ENVIO_DIARI
  (psCodigoPais       VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psPeriodo 		  VARCHAR2,
   psCodigoMarca 	  VARCHAR2,
   psCodigoCanal 	  VARCHAR2,
   psFechaFacturacion VARCHAR2)
IS
	CURSOR c_interfaz IS
		      SELECT SEG_PAIS.COD_PAIS AS CODPAIS,
	                 PED_SOLIC_CABEC.VAL_NUME_SOLI AS NUMBOL,
	                 MAE_CLIEN.COD_CLIE AS CODCLIENTE,
	                 to_char(REC_CABEC_RECLA.FEC_INGR,'yyyyMMdd') AS FECHAANULACION,
	                 SEG_PERIO_CORPO.COD_PERI AS CODPERIODO
	            FROM REC_OPERA_RECLA,
	                 REC_CABEC_RECLA,
	                 REC_OPERA,
	                 REC_TIPOS_OPERA,
	                 MAE_CLIEN,
	                 PED_SOLIC_CABEC,
	                 CRA_PERIO,
	                 SEG_PERIO_CORPO,
	                 REC_ESTAD_OPERA,
	                 SEG_PERIO_CORPO SEG_PERIO_CORPO_RECLA,
	                 CRA_PERIO CRA_PERIO_RECLA,
	                 REC_ESTAD_RECLA,
	                 SEG_PAIS,
	                 SEG_CANAL,
	                 SEG_MARCA
	           WHERE      (REC_CABEC_RECLA.OID_CABE_RECL = REC_OPERA_RECLA.CARE_OID_CABE_RECL)
	                  AND (REC_OPERA.OID_OPER = REC_TIPOS_OPERA.ROPE_OID_OPER)
	                  AND (REC_TIPOS_OPERA.OID_TIPO_OPER = REC_OPERA_RECLA.TIOP_OID_TIPO_OPER)
	                  AND (MAE_CLIEN.OID_CLIE = REC_CABEC_RECLA.CLIE_OID_CLIE)
	                  AND (PED_SOLIC_CABEC.OID_SOLI_CABE = REC_CABEC_RECLA.SOCA_OID_SOLI_CABE)
	                  AND (CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI)
	                  AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
	                  AND (REC_ESTAD_OPERA.OID_ESTA_OPER = REC_OPERA_RECLA.ESOP_OID_ESTA_OPER)
	                  AND (CRA_PERIO_RECLA.OID_PERI = REC_CABEC_RECLA.PERD_OID_PERI_RECL)
	                  AND (CRA_PERIO_RECLA.PERI_OID_PERI = SEG_PERIO_CORPO_RECLA.OID_PERI)
	                  AND (REC_ESTAD_RECLA.OID_ESTA_RECL = REC_CABEC_RECLA.ESRE_OID_ESTA_RECL)
	                  AND (SEG_PAIS.OID_PAIS = MAE_CLIEN.PAIS_OID_PAIS)
	                  AND (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
	                  AND (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
	                  AND (REC_OPERA.COD_OPER = 'A')
	                  AND (REC_ESTAD_OPERA.COD_ESTA_OPER = 'F')
	                  AND (REC_ESTAD_RECLA.COD_ESTA = 'F')
	                  AND (SEG_PERIO_CORPO_RECLA.COD_PERI = psPeriodo)
	                  AND (SEG_PAIS.COD_PAIS = psCodigoPais)
	                  AND (SEG_CANAL.COD_CANA = psCodigoCanal)
	                  AND (SEG_MARCA.COD_MARC = psCodigoMarca)
	                  AND (REC_CABEC_RECLA.FEC_INGR <= TO_DATE(psFechaFacturacion,'dd/MM/yyyy'))
	        ORDER BY CODPAIS, CODCLIENTE, NUMBOL;
	TYPE interfazRec IS RECORD
	(
		codigoPais         SEG_PAIS.COD_PAIS%TYPE,
		numeroSolicitud    PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE,
		codigoCliente      MAE_CLIEN.COD_CLIE%TYPE,
		fechaIngreso       varchar2(8),--REC_CABEC_RECLA.FEC_INGR%TYPE,
		codigoPeriodo      SEG_PERIO_CORPO.COD_PERI%TYPE
    );
   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;
	/* Variables usadas para la generacion del archivo de texto */
	  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
	  W_FILAS             NUMBER := 1000 ;
	  v_handle            UTL_FILE.FILE_TYPE;
	  W_DESC              VARCHAR2(200);
	  lsLinea             VARCHAR2(1000);
	  lsLineaCabecera     VARCHAR2(1000);
	  lsNombreArchivo     VARCHAR2(50);
    lbAbrirUtlFile  BOOLEAN;
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
	                  lsLinea :=  interfazRecord(x).codigoPais      ||';'||
					              interfazRecord(x).codigoCliente   ||';'||
								  interfazRecord(x).codigoPeriodo   ||';'||
					              interfazRecord(x).numeroSolicitud ||';'||
					              interfazRecord(x).fechaIngreso;
	                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
	              END LOOP;
	           END IF;
	           EXIT WHEN c_interfaz%NOTFOUND;
	        END LOOP;
	        CLOSE c_interfaz;

	  IF NOT lbAbrirUtlFile THEN
	    utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

	    RETURN;
	EXCEPTION
	WHEN OTHERS THEN
		     ln_sqlcode := SQLCODE;
		     ls_sqlerrm := substr(sqlerrm,1,250);
		     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MYE_ENVIO_DIARI: '||ls_sqlerrm);

	END INT_PR_MYE_ENVIO_DIARI;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de cabeceras de pedidos
Fecha Creacion    : 05/06/2008
Autor             : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE INT_PR_MYE_CABEC_PEDID
  (psCodigoPais       VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psPeriodo 		  VARCHAR2,
   psCodigoMarca 	  VARCHAR2,
   psCodigoCanal 	  VARCHAR2,
   psCodigoTipoClien  VARCHAR2,
   psFechaFacturacion VARCHAR2)
IS
	CURSOR c_interfaz IS
		      SELECT S.COD_PAIS,
					 M.COD_CLIE,
					 SP.COD_PERI,
					 ZT.COD_TERR,
					 P.VAL_NUME_SOLI,
					 P.NUM_UNID_ATEN_TOTA,
					 P.VAL_TOTA_PAGA_LOCA
				FROM PED_SOLIC_CABEC P,
					 MAE_CLIEN M,
					 MAE_CLIEN_UNIDA_ADMIN MAD,
					 ZON_TERRI_ADMIN ZTA,
					 ZON_TERRI ZT,
					 SEG_PAIS S,
					 CRA_PERIO C,
					 SEG_PERIO_CORPO SP,
					 PED_TIPO_SOLIC_PAIS TSP,
					 PED_TIPO_SOLIC TS,
					 SEG_CANAL SC,
					 SEG_MARCA SM,
					 SEG_ACCES SA,
					 mae_tipo_clien mtc
			  WHERE 1=1
				AND P.IND_OC = 0
				AND P.IND_TS_NO_CONSO = 0
				AND P.SOCA_OID_SOLI_CABE IS NULL
				AND P.PAIS_OID_PAIS = S.OID_PAIS
				AND MAD.CLIE_OID_CLIE = M.OID_CLIE
				AND MAD.PERD_OID_PERI_FIN IS NULL
				AND MAD.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
				AND ZTA.IND_BORR = 0
				AND ZTA.TERR_OID_TERR = ZT.OID_TERR
				AND P.CLIE_OID_CLIE = M.OID_CLIE
				AND P.PERD_OID_PERI = C.OID_PERI
				AND C.PERI_OID_PERI = SP.OID_PERI
				AND P.GRPR_OID_GRUP_PROC=5
				AND P.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
				AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
				AND C.CANA_OID_CANA = SC.OID_CANA
				AND C.MARC_OID_MARC = SM.OID_MARC
				AND C.ACCE_OID_ACCE = SA.OID_ACCE
				and p.TICL_OID_TIPO_CLIE = mtc.OID_TIPO_CLIE
				AND TS.COD_TIPO_SOLI = 'C1'
				AND SP.COD_PERI = psPeriodo
				AND S.COD_PAIS = psCodigoPais
				AND SC.COD_CANA = psCodigoCanal
				AND SM.COD_MARC = psCodigoMarca
				AND MTC.COD_TIPO_CLIE = psCodigoTipoClien
				AND EXISTS (SELECT OID_SOLI_CABE
										    FROM PED_SOLIC_CABEC P1,
											 	 PED_TIPO_SOLIC_PAIS TP1,
									 			 PED_TIPO_SOLIC T1
											WHERE P1.TSPA_OID_TIPO_SOLI_PAIS = TP1.OID_TIPO_SOLI_PAIS
											AND TP1.TSOL_OID_TIPO_SOLI = T1.OID_TIPO_SOLI
											AND T1.COD_TIPO_SOLI = 'SOC'
											AND P1.SOCA_OID_SOLI_CABE = P.OID_SOLI_CABE
											)
				AND P.FEC_FACT = to_date(psFechaFacturacion,'dd/MM/yyyy');

				TYPE interfazRec IS RECORD
					(
						codigoPais         SEG_PAIS.COD_PAIS%TYPE,
						codigoCliente      MAE_CLIEN.COD_CLIE%TYPE,
						codigoPeriodo      SEG_PERIO_CORPO.COD_PERI%TYPE,
						codigoTerritorio   ZON_TERRI.COD_TERR%TYPE,
						--fechaIngreso       REC_CABEC_RECLA.FEC_INGR%TYPE,
						numeroSolicitud    PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE,
						unidadesAten       PED_SOLIC_CABEC.NUM_UNID_ATEN_TOTA%TYPE,
						unidadesPaga	   PED_SOLIC_CABEC.VAL_TOTA_PAGA_LOCA%TYPE
				    );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;
	/* Variables usadas para la generacion del archivo de texto */
	  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
	  W_FILAS             NUMBER := 1000 ;
	  v_handle            UTL_FILE.FILE_TYPE;
	  W_DESC              VARCHAR2(200);
	  lsLinea             VARCHAR2(1000);
	  lsLineaCabecera     VARCHAR2(1000);
	  lsNombreArchivo     VARCHAR2(50);
    lbAbrirUtlFile  BOOLEAN;
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
	                  lsLinea :=  interfazRecord(x).codigoPais        ||';'||
					              interfazRecord(x).codigoCliente     ||';'||
					              interfazRecord(x).codigoPeriodo     ||';'||
					             -- interfazRecord(x).fechaIngreso      ||';'||
					              interfazRecord(x).codigoTerritorio  ||';'||
								  interfazRecord(x).numeroSolicitud   ||';'||
								  interfazRecord(x).unidadesAten      ||';'||
								  interfazRecord(x).unidadesPaga;
	                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
	              END LOOP;
	           END IF;
	           EXIT WHEN c_interfaz%NOTFOUND;
	        END LOOP;
	        CLOSE c_interfaz;

	    IF NOT lbAbrirUtlFile THEN
	    utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
      END IF;
	    RETURN;
	EXCEPTION
	WHEN OTHERS THEN
		     ln_sqlcode := SQLCODE;
		     ls_sqlerrm := substr(sqlerrm,1,250);
		     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MYE_CABEC_PEDID: '||ls_sqlerrm);

	END INT_PR_MYE_CABEC_PEDID;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de archivos de premios
Fecha Creacion    : 05/06/2008
Autor             : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE INT_PR_MYE_ARCHI_PREMI
  (psCodigoPais       VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2)
IS
	CURSOR c_interfaz IS
		SELECT  sp.cod_pais,
				al.cod_vent_fict codigoventa,
				mp.cod_sap AS codigoproducto,
				(SELECT VAL_I18N
				 FROM GEN_I18N_SICC_PAIS
				 WHERE ATTR_ENTI = 'MAE_PRODU'
				 AND VAL_OID = mp.oid_prod ) PROD
		   FROM seg_pais sp,
		        mae_produ mp,
				inc_artic_lote al
		   WHERE 1=1
			 AND al.prod_oid_prod = mp.oid_prod
			 and sp.oid_pais=mp.pais_oid_pais
	    ORDER BY al.cod_vent_fict desc;

	TYPE interfazRec IS RECORD
					(
						codigoPais         SEG_PAIS.COD_PAIS%TYPE,
					    codigoVenta        inc_artic_lote.cod_vent_fict%TYPE,
						codigoProducto     mae_produ.cod_sap%TYPE,
						producto           GEN_I18N_SICC_PAIS.VAL_I18N%TYPE
				    );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;
	/* Variables usadas para la generacion del archivo de texto */
	  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
	  W_FILAS             NUMBER := 1000 ;
	  v_handle            UTL_FILE.FILE_TYPE;
	  W_DESC              VARCHAR2(200);
	  lsLinea             VARCHAR2(1000);
	  lsLineaCabecera     VARCHAR2(1000);
	  lsNombreArchivo     VARCHAR2(50);
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
	                  lsLinea :=  interfazRecord(x).codigoPais          ||';'||
					              interfazRecord(x).codigoVenta         ||';'||
					              interfazRecord(x).codigoProducto      ||';'||
					              interfazRecord(x).producto;
	                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
	              END LOOP;
	           END IF;
	           EXIT WHEN c_interfaz%NOTFOUND;
	        END LOOP;
	        CLOSE c_interfaz;

	    utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
	    RETURN;
	EXCEPTION
	WHEN OTHERS THEN
		     ln_sqlcode := SQLCODE;
		     ls_sqlerrm := substr(sqlerrm,1,250);
		     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MYE_ARCHI_PREMI: '||ls_sqlerrm);

	END INT_PR_MYE_ARCHI_PREMI;
/***************************************************************************
Descripcion       : Genera Interfaz de Tipo de producto catalogo
Fecha Creacion    : 06/11/2008
Autor             : Cristhian Roman
***************************************************************************/
PROCEDURE INT_PR_MYE_PRODU_CATAL
  (psCodigoPais       VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodCanal        VARCHAR2,
   psCodMarca        VARCHAR2)

IS
	CURSOR c_interfaz (psCodigoPeriodo VARCHAR2, psIndicadorActivo VARCHAR2) IS
		SELECT  b.COD_PAIS codigoPais,
				psCodigoPeriodo codigoPeriodo,
				a.COD_CATA codigoCatalogo,
				a.DES_CATA desCatalogo,
				psIndicadorActivo indActividad
		   FROM pre_catal a,
		   		seg_pais b
		   WHERE a.PAIS_OID_PAIS = b.OID_PAIS
			 	  AND b.COD_PAIS = psCodigoPais;
	TYPE interfazRec IS RECORD
					(
						codigoPais         SEG_PAIS.COD_PAIS%TYPE,
					    codigoPeriodo      seg_perio_corpo.COD_PERI%TYPE,
						codigoCatalogo     pre_catal.COD_CATA%TYPE,
						desCatalogo		   pre_catal.DES_CATA%TYPE,
						indActividad         VARCHAR2(1)
				    );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;
	/* Variables usadas para la generacion del archivo de texto */
	  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
	  W_FILAS             NUMBER := 1000 ;
	  v_handle            UTL_FILE.FILE_TYPE;
	  W_DESC              VARCHAR2(200);
	  lsLinea             VARCHAR2(1000);
	  lsLineaCabecera     VARCHAR2(1000);
	  lsNombreArchivo     VARCHAR2(50);
	  psCodigoPeriodo 	  SEG_PERIO_CORPO.COD_PERI%TYPE;
	  psIndicadorActivo VARCHAR2(1);
	  ln_idpais          seg_pais.OID_PAIS%TYPE;
      ln_id_canal        seg_canal.oid_cana%TYPE;
	  lbAbrirUtlFile      BOOLEAN;

	BEGIN
	 psIndicadorActivo:='1';
	 /* obteniendo id's */
     ln_idpais:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
     ln_id_canal:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);
     psCodigoPeriodo:=GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_ACTU(ln_idpais,psCodMarca , ln_id_canal);

		   lbAbrirUtlFile := TRUE;
	        OPEN c_interfaz(psCodigoPeriodo, psIndicadorActivo);
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
	                  lsLinea :=  interfazRecord(x).codigoPais          ||';'||
					              interfazRecord(x).codigoPeriodo       ||';'||
					              interfazRecord(x).codigoCatalogo      ||';'||
					              interfazRecord(x).desCatalogo			||';'||
								  interfazRecord(x).indActividad;

	                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
	              END LOOP;
	           END IF;
	           EXIT WHEN c_interfaz%NOTFOUND;
	        END LOOP;
	        CLOSE c_interfaz;

	IF NOT lbAbrirUtlFile THEN
	utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
	END IF;
    RETURN;
	EXCEPTION
	WHEN OTHERS THEN
		     ln_sqlcode := SQLCODE;
		     ls_sqlerrm := substr(sqlerrm,1,250);
		     RAISE_APPLICATION_ERROR(-20123, 'INT_PR_MYE_PRODU_CATAL: '||ls_sqlerrm);
END INT_PR_MYE_PRODU_CATAL;
/***************************************************************************
Descripcion       : Genera Interfaz de Faltantes anunciados
Fecha Creacion    : 06/11/2008
Autor             : Cristhian Roman
***************************************************************************/
PROCEDURE INT_PR_MYE_FALTA_ANUNC
  (psCodigoPais       VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoPeriodo	  VARCHAR2)

   IS
	 CURSOR c_interfaz (psCodigoPeriodo VARCHAR2, psIndicadorActivo VARCHAR2)IS
      SELECT    d.cod_pais AS codigopais,
                c.cod_peri AS codigoperiodo,
                e.val_codi_vent AS codigoventa,
                f.cod_zona AS codigozona,
       '1' AS indactividad
		   FROM ped_gesti_stock a,
				cra_perio b,
				seg_perio_corpo c,
				seg_pais d,
				pre_ofert_detal e,
				zon_zona f,
      				  PRE_TIPO_OFERT g
   WHERE a.perd_oid_peri = b.oid_peri
   AND b.peri_oid_peri = c.oid_peri
       AND      c.cod_peri in (psCodigoPeriodo,GEN_FN_CALCU_PERIO(psCodigoPeriodo, 1))
   AND b.pais_oid_pais = d.oid_pais
   AND d.cod_pais = psCodigoPais
   AND a.ofde_oid_deta_ofer = e.oid_deta_ofer
   AND a.zzon_oid_zona = f.oid_zona
   AND a.val_limi_ctrl_vent = 0
   and g.OID_TIPO_OFER = e.TOFE_OID_TIPO_OFER
   and g.COD_TIPO_OFER not in (21,23)
   UNION
      SELECT    d.cod_pais AS codigopais,
                c.cod_peri AS codigoperiodo,
                e.val_codi_vent AS codigoventa,
                f.cod_zona AS codigozona,
            '1' AS indactividad
		   FROM ped_gesti_stock a,
				cra_perio b,
				seg_perio_corpo c,
				seg_pais d,
				pre_ofert_detal e,
        zon_zona f,
      				  PRE_TIPO_OFERT g
    WHERE a.perd_oid_peri = b.oid_peri
    AND b.peri_oid_peri = c.oid_peri
        AND     c.cod_peri in (psCodigoPeriodo,GEN_FN_CALCU_PERIO(psCodigoPeriodo, 1))
    AND b.pais_oid_pais = d.oid_pais
    AND d.cod_pais = psCodigoPais
    AND a.ofde_oid_deta_ofer = e.oid_deta_ofer
    AND a.zzon_oid_zona IS NULL
    AND a.zorg_oid_regi IS NULL
    AND f.ind_acti = 1
    AND a.val_limi_ctrl_vent = 0
    and g.OID_TIPO_OFER = e.TOFE_OID_TIPO_OFER
    and g.COD_TIPO_OFER not in (21,23)
    UNION
      SELECT    d.cod_pais AS codigopais,
                c.cod_peri AS codigoperiodo,
                e.val_codi_vent AS codigoventa,
                f.cod_zona AS codigozona,
             '1' AS indactividad
    FROM ped_gesti_stock a,
             cra_perio b,
             seg_perio_corpo c,
             seg_pais d,
             pre_ofert_detal e,
             zon_zona f,
             zon_regio g,
      			    PRE_TIPO_OFERT h
     WHERE a.perd_oid_peri = b.oid_peri
     AND b.peri_oid_peri = c.oid_peri
        AND     c.cod_peri in (psCodigoPeriodo,GEN_FN_CALCU_PERIO(psCodigoPeriodo, 1))
     AND b.pais_oid_pais = d.oid_pais
     AND d.cod_pais = psCodigoPais
     AND a.ofde_oid_deta_ofer = e.oid_deta_ofer
     AND a.zzon_oid_zona IS NULL
     AND a.zorg_oid_regi IS NOT NULL
     AND f.ind_acti = 1
     AND g.oid_regi = a.zorg_oid_regi
     AND g.oid_regi = f.zorg_oid_regi
     AND g.ind_acti = 1
     AND a.val_limi_ctrl_vent = 0
     and h.OID_TIPO_OFER = e.TOFE_OID_TIPO_OFER
     and h.COD_TIPO_OFER not in (21,23);

	TYPE interfazRec IS RECORD
					(
						codigoPais         SEG_PAIS.COD_PAIS%TYPE,
					    codigoPeriodo      seg_perio_corpo.COD_PERI%TYPE,
						codigoVenta        pre_ofert_detal.VAL_CODI_VENT%TYPE,
						codigoZona		   zon_zona.COD_ZONA%TYPE,
						indActividad		   VARCHAR2(1)
				    );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;
	/* Variables usadas para la generacion del archivo de texto */
	  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
	  W_FILAS             NUMBER := 1000 ;
	  v_handle            UTL_FILE.FILE_TYPE;
	  W_DESC              VARCHAR2(200);
	  lsLinea             VARCHAR2(1000);
	  lsLineaCabecera     VARCHAR2(1000);
	  lsNombreArchivo     VARCHAR2(50);
	  psIndicadorActivo	  VARCHAR2(1);
	  lbAbrirUtlFile      BOOLEAN;

	BEGIN
	psIndicadorActivo:='1';

	lbAbrirUtlFile := TRUE;
	        OPEN c_interfaz(psCodigoPeriodo,psIndicadorActivo);
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
	                  lsLinea :=  interfazRecord(x).codigoPais       ||';'||
					              interfazRecord(x).codigoPeriodo    ||';'||
					              interfazRecord(x).codigoVenta      ||';'||
								  interfazRecord(x).codigoZona		 ||';'||
								  interfazRecord(x).indActividad;

	                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
	              END LOOP;
	           END IF;
	           EXIT WHEN c_interfaz%NOTFOUND;
	        END LOOP;
	        CLOSE c_interfaz;


	   IF NOT lbAbrirUtlFile THEN
	   utl_file.fclose(V_HANDLE);

        /* Procedimiento final para generar interfaz */
        GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
	   END IF;
	   RETURN;
	EXCEPTION
	WHEN OTHERS THEN
		     ln_sqlcode := SQLCODE;
		     ls_sqlerrm := substr(sqlerrm,1,250);
		     RAISE_APPLICATION_ERROR(-20123, 'INT_PR_MYE_FALTA_ANUNC: '||ls_sqlerrm);
END INT_PR_MYE_FALTA_ANUNC;

/***************************************************************************
Descripcion       : Genera Interfaz de Faltantes anunciados - Limite de venta
Fecha Creacion    : 22/08/2012
Autor             : Dennys Oliva Iriarte

- Limite venta [0]   => Faltante anunciado
               [!=0] => Limite de venta del producto

- Si no esta en el archivo, es porque el producto no tiene limite de venta
***************************************************************************/
PROCEDURE INT_PR_MYE_FALAN_LIVTA(psCodigoPais       VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoPeriodo	  VARCHAR2)

   IS
	 CURSOR c_interfaz (psCodigoPeriodo VARCHAR2)IS
      SELECT    d.cod_pais AS codigopais,
                c.cod_peri AS codigoperiodo,
                e.val_codi_vent AS codigoventa,
                f.cod_zona AS codigozona,
             decode(a.val_limi_ctrl_vent,0,'1',null) AS indactividad,
             a.val_limi_ctrl_vent AS limiteventa
      FROM      ped_gesti_stock a,
      				  cra_perio b,
      				  seg_perio_corpo c,
      			    seg_pais d,
      				  pre_ofert_detal e,
      				  zon_zona f,
      				  PRE_TIPO_OFERT g
      WHERE     a.perd_oid_peri = b.oid_peri
       AND      b.peri_oid_peri = c.oid_peri
       AND      c.cod_peri in (psCodigoPeriodo,GEN_FN_CALCU_PERIO(psCodigoPeriodo, 1))
       AND      b.pais_oid_pais = d.oid_pais
       AND      d.cod_pais = psCodigoPais
       AND      a.ofde_oid_deta_ofer = e.oid_deta_ofer
       AND      a.zzon_oid_zona = f.oid_zona
         AND a.val_limi_ctrl_vent is not null
       and      g.OID_TIPO_OFER = e.TOFE_OID_TIPO_OFER
       and      g.COD_TIPO_OFER not in (21,23)

      UNION

      SELECT    d.cod_pais AS codigopais,
                c.cod_peri AS codigoperiodo,
                e.val_codi_vent AS codigoventa,
                f.cod_zona AS codigozona,
             decode(a.val_limi_ctrl_vent,0,'1',null) AS indactividad,
             a.val_limi_ctrl_vent AS limiteventa
      FROM      ped_gesti_stock a,
      				  cra_perio b,
      				  seg_perio_corpo c,
      				  seg_pais d,
      				  pre_ofert_detal e,
      				  zon_zona f,
      				  PRE_TIPO_OFERT g
      WHERE     a.perd_oid_peri = b.oid_peri
        AND     b.peri_oid_peri = c.oid_peri
        AND     c.cod_peri in (psCodigoPeriodo,GEN_FN_CALCU_PERIO(psCodigoPeriodo, 1))
        AND     b.pais_oid_pais = d.oid_pais
        AND     d.cod_pais = psCodigoPais
        AND     a.ofde_oid_deta_ofer = e.oid_deta_ofer
        AND     a.zzon_oid_zona IS NULL
        AND     a.zorg_oid_regi IS NULL
        AND     f.ind_acti = 1
         AND a.val_limi_ctrl_vent is not null
        and     g.OID_TIPO_OFER = e.TOFE_OID_TIPO_OFER
        and     g.COD_TIPO_OFER not in (21,23)

      UNION

      SELECT    d.cod_pais AS codigopais,
                c.cod_peri AS codigoperiodo,
                e.val_codi_vent AS codigoventa,
                f.cod_zona AS codigozona,
             decode(a.val_limi_ctrl_vent,0,'1',null) AS indactividad,
             a.val_limi_ctrl_vent AS limiteventa
      FROM      ped_gesti_stock a,
                cra_perio b,
                seg_perio_corpo c,
                seg_pais d,
                pre_ofert_detal e,
                zon_zona f,
                zon_regio g,
      			    PRE_TIPO_OFERT h
      WHERE     a.perd_oid_peri = b.oid_peri
        AND     b.peri_oid_peri = c.oid_peri
        AND     c.cod_peri in (psCodigoPeriodo,GEN_FN_CALCU_PERIO(psCodigoPeriodo, 1))
        AND     b.pais_oid_pais = d.oid_pais
        AND     d.cod_pais = psCodigoPais
        AND     a.ofde_oid_deta_ofer = e.oid_deta_ofer
        AND     a.zzon_oid_zona IS NULL
        AND     a.zorg_oid_regi IS NOT NULL
        AND     f.ind_acti = 1
        AND     g.oid_regi = a.zorg_oid_regi
        AND     g.oid_regi = f.zorg_oid_regi
        AND     g.ind_acti = 1
         AND a.val_limi_ctrl_vent is not null
        and     h.OID_TIPO_OFER = e.TOFE_OID_TIPO_OFER
        and     h.COD_TIPO_OFER not in (21,23);

	TYPE interfazRec IS RECORD(codigoPais      SEG_PAIS.COD_PAIS%TYPE,
					    codigoPeriodo      seg_perio_corpo.COD_PERI%TYPE,
						codigoVenta        pre_ofert_detal.VAL_CODI_VENT%TYPE,
						codigoZona		   zon_zona.COD_ZONA%TYPE,
                             indactividad    varchar2(1),
						                 limiteventa		 ped_gesti_stock.val_limi_ctrl_vent%type
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

	lbAbrirUtlFile := TRUE;
	        OPEN c_interfaz(psCodigoPeriodo);
	        LOOP
	           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
			    /* Procedimiento inicial para generar interfaz */
			   IF lbAbrirUtlFile THEN
                 GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais,
                                                        psCodigosistema,
                                                        psCodigoInterfaz,
                                                        psNombreArchivo,
                                                        lsDirTempo,
                                                        lsNombreArchivo,
                                                        V_HANDLE);
               lbAbrirUtlFile := FALSE;
               END IF;

	           IF interfazRecord.COUNT > 0 THEN
	              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
	                  lsLinea :=  interfazRecord(x).codigoPais       ||';'||
					              interfazRecord(x).codigoPeriodo    ||';'||
					              interfazRecord(x).codigoVenta      ||';'||
								  interfazRecord(x).codigoZona		 ||';'||
                                interfazRecord(x).indactividad     ||';'||
                                interfazRecord(x).limiteventa;

	                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
	              END LOOP;
	           END IF;
	           EXIT WHEN c_interfaz%NOTFOUND;
	        END LOOP;
	        CLOSE c_interfaz;


	   IF NOT lbAbrirUtlFile THEN
	   utl_file.fclose(V_HANDLE);
        /* Procedimiento final para generar interfaz */
        GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
	   END IF;

	   RETURN;
	EXCEPTION
	WHEN OTHERS THEN
		     ln_sqlcode := SQLCODE;
		     ls_sqlerrm := substr(sqlerrm,1,250);
		     RAISE_APPLICATION_ERROR(-20123, 'INT_PR_MYE_FALAN_LIVTA: '||ls_sqlerrm);
END INT_PR_MYE_FALAN_LIVTA;


/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo Control envio OCR
                      a SICC
  Fecha Creacion    : 01/04/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_mye_actua_datos_consu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;

    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);

    TYPE interfazcabtab IS TABLE OF interfazcab;

    interfazrecord interfazcabtab;

    TYPE t_cod_pais IS TABLE OF int_mye_actua_datos_consu.cod_pais%TYPE;
    TYPE t_cod_clie IS TABLE OF int_mye_actua_datos_consu.cod_clie%TYPE;
    TYPE t_val_tele_fijo IS TABLE OF int_mye_actua_datos_consu.val_tele_fijo%TYPE;
    TYPE t_val_tele_celu IS TABLE OF int_mye_actua_datos_consu.val_tele_celu%TYPE;
    TYPE t_val_corr_elec IS TABLE OF int_mye_actua_datos_consu.val_corr_elec%TYPE;
    TYPE t_val_ind_acti_emai IS TABLE OF int_mye_actua_datos_consu.val_ind_acti_emai%TYPE;
    TYPE t_val_cam_acti_emai IS TABLE OF int_mye_actua_datos_consu.val_cam_acti_emai%TYPE;

    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_clie      t_cod_clie := t_cod_clie();
    v_val_tele_fijo t_val_tele_fijo := t_val_tele_fijo();
    v_val_tele_celu t_val_tele_celu := t_val_tele_celu();
    v_val_corr_elec t_val_corr_elec := t_val_corr_elec();
    v_val_ind_acti_emai t_val_ind_acti_emai := t_val_ind_acti_emai();
    v_val_cam_acti_emai t_val_cam_acti_emai := t_val_cam_acti_emai();

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);

    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN

          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;

          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.COUNT > 0 THEN
              FOR x IN interfazrecord.FIRST .. interfazrecord.LAST
              LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                  v_cod_pais.EXTEND;
                  v_cod_pais(i) := TRIM(nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais));
                ELSIF (posicion = 2) THEN
                  v_cod_clie.EXTEND;
                  v_cod_clie(i) := TRIM(substr(lslinea, inicio,
                                          longitud));
                ELSIF (posicion = 3) THEN
                  v_val_tele_fijo.EXTEND;
                  v_val_tele_fijo(i) := TRIM(substr(lslinea, inicio,
                                               longitud));
                ELSIF (posicion = 4) THEN
                  v_val_tele_celu.EXTEND;
                  v_val_tele_celu(i) := TRIM(substr(lslinea, inicio,
                                               longitud));
                ELSIF (posicion = 5) THEN
                  v_val_corr_elec.EXTEND;
                  v_val_corr_elec(i) := TRIM(substr(lslinea, inicio,
                                               longitud));
                ELSIF (posicion = 6) THEN
                  v_val_ind_acti_emai.EXTEND;
                  v_val_ind_acti_emai(i) := TRIM(substr(lslinea, inicio,
                                               longitud));
                ELSIF (posicion = 7) THEN
                  v_val_cam_acti_emai.EXTEND;
                  v_val_cam_acti_emai(i) := TRIM(substr(lslinea, inicio,
                                               longitud));

                END IF;

                inicio := inicio + longitud;

              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;

        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;

    utl_file.fclose(v_handle);

    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.COUNT
      INSERT INTO int_mye_actua_datos_consu
        (cod_pais,
         cod_clie,
         val_tele_fijo,
         val_tele_celu,
         val_corr_elec,
         oid_clie,
         val_ind_acti_emai,
         val_cam_acti_emai)
      VALUES
        (v_cod_pais(i),
         v_cod_clie(i),
         v_val_tele_fijo(i),
         v_val_tele_celu(i),
         v_val_corr_elec(i),
         (SELECT oid_clie
            FROM mae_clien
           WHERE cod_clie = v_cod_clie(i)),
         v_val_ind_acti_emai(i),
         v_val_cam_acti_emai(i));

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_MYE_ACTUA_DATOS_CONSU: ' ||
                               ls_sqlerrm);

  END int_pr_mye_actua_datos_consu;


/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo Control envio OCR
                      a SICC
  Fecha Creacion    : 01/04/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
PROCEDURE int_pr_mye_proce_actua_datos IS
  CURSOR c_upddatos IS
    SELECT cod_pais,
           cod_clie,
           val_tele_fijo,
           val_tele_celu,
           val_corr_elec,
           oid_clie,
           val_ind_acti_emai,
           val_cam_acti_emai
      FROM int_mye_actua_datos_consu temp
     WHERE temp.oid_clie IS NOT NULL
       AND EXISTS
     (SELECT 1
              FROM mae_clien_comun com
             WHERE com.clie_oid_clie = temp.oid_clie);

  TYPE t_cod_pais IS TABLE OF int_mye_actua_datos_consu.cod_pais%TYPE;
  TYPE t_cod_clie IS TABLE OF int_mye_actua_datos_consu.cod_clie%TYPE;
  TYPE t_val_tele_fijo IS TABLE OF int_mye_actua_datos_consu.val_tele_fijo%TYPE;
  TYPE t_val_tele_celu IS TABLE OF int_mye_actua_datos_consu.val_tele_celu%TYPE;
  TYPE t_val_corr_elec IS TABLE OF int_mye_actua_datos_consu.val_corr_elec%TYPE;
  TYPE t_oid_clie IS TABLE OF int_mye_actua_datos_consu.oid_clie%TYPE;
  TYPE t_val_ind_acti_emai IS TABLE OF int_mye_actua_datos_consu.val_ind_acti_emai%TYPE;
  TYPE t_val_cam_acti_emai IS TABLE OF int_mye_actua_datos_consu.val_cam_acti_emai%TYPE;

  v_cod_pais      t_cod_pais;
  v_cod_clie      t_cod_clie;
  v_val_tele_fijo t_val_tele_fijo;
  v_val_tele_celu t_val_tele_celu;
  v_val_corr_elec t_val_corr_elec;
  v_oid_clie      t_oid_clie;
  v_val_ind_acti_emai t_val_ind_acti_emai;
  v_val_cam_acti_emai t_val_cam_acti_emai;

  CURSOR c_insdatostf(oidtipocomun NUMBER) IS
    SELECT MIN(val_tele_fijo),
           oid_clie
      FROM int_mye_actua_datos_consu temp
     WHERE temp.oid_clie IS NOT NULL
       AND val_tele_fijo IS NOT NULL
       AND NOT EXISTS
     (SELECT 1
              FROM mae_clien_comun com
             WHERE com.clie_oid_clie = temp.oid_clie
               AND com.ticm_oid_tipo_comu = oidtipocomun)
     GROUP BY oid_clie;

  CURSOR c_insdatostm(oidtipocomun NUMBER) IS
    SELECT MIN(val_tele_celu),
           oid_clie
      FROM int_mye_actua_datos_consu temp
     WHERE temp.oid_clie IS NOT NULL
       AND val_tele_celu IS NOT NULL
       AND NOT EXISTS
     (SELECT 1
              FROM mae_clien_comun com
             WHERE com.clie_oid_clie = temp.oid_clie
               AND com.ticm_oid_tipo_comu = oidtipocomun)
     GROUP BY oid_clie;

  CURSOR c_insdatosml(oidtipocomun NUMBER) IS
    SELECT MIN(val_corr_elec),
           oid_clie,
           VAL_IND_ACTI_EMAI,
           VAL_CAM_ACTI_EMAI
      FROM int_mye_actua_datos_consu temp
     WHERE temp.oid_clie IS NOT NULL
       AND val_corr_elec IS NOT NULL
       AND NOT EXISTS
     (SELECT 1
              FROM mae_clien_comun com
             WHERE com.clie_oid_clie = temp.oid_clie
               AND com.ticm_oid_tipo_comu = oidtipocomun)
     GROUP BY oid_clie, VAL_IND_ACTI_EMAI, VAL_CAM_ACTI_EMAI;

  i BINARY_INTEGER := 0;

  lnoidtipocomutelefijo    mae_tipo_comun.oid_tipo_comu%TYPE;
  lnoidtipocomutelecelular mae_tipo_comun.oid_tipo_comu%TYPE;
  lnoidtipocomuemail       mae_tipo_comun.oid_tipo_comu%TYPE;
  rows                     NATURAL := 1000; -- Number of rows to process at a time

BEGIN

  SELECT oid_tipo_comu
    INTO lnoidtipocomutelefijo
    FROM mae_tipo_comun t
   WHERE t.cod_tipo_comu = 'TF';

  SELECT oid_tipo_comu
    INTO lnoidtipocomutelecelular
    FROM mae_tipo_comun t
   WHERE t.cod_tipo_comu = 'TM';

  SELECT oid_tipo_comu
    INTO lnoidtipocomuemail
    FROM mae_tipo_comun t
   WHERE t.cod_tipo_comu = 'ML';

  /*Inserta datos Telefono fijo*/
  OPEN c_insdatostf(lnoidtipocomutelefijo);
  LOOP
    FETCH c_insdatostf BULK COLLECT
      INTO v_val_tele_fijo, v_oid_clie LIMIT rows;

    IF v_oid_clie.COUNT > 0 THEN
      /*TELEFONO FIJO*/
      FORALL i IN 1 .. v_oid_clie.COUNT

        INSERT INTO mae_clien_comun
          (oid_clie_comu,
           clie_oid_clie,
           ticm_oid_tipo_comu,
           val_dia_comu,
           val_text_comu,
           fec_hora_desd,
           fec_hora_hast,
           val_inte_comu,
           ind_comu_ppal,
           fec_ulti_actu)
        VALUES
          (mae_clco_seq.NEXTVAL,
           v_oid_clie(i),
           lnoidtipocomutelefijo,
           'L',
           v_val_tele_fijo(i),
           NULL,
           NULL,
           0,
           0,
           SYSDATE);

    END IF;
    EXIT WHEN c_insdatostf%NOTFOUND;
  END LOOP;
  CLOSE c_insdatostf;

  /*Inserta datos Telefono Movil*/
  OPEN c_insdatostm(lnoidtipocomutelecelular);
  LOOP
    FETCH c_insdatostm BULK COLLECT
      INTO v_val_tele_celu, v_oid_clie LIMIT rows;

    IF v_oid_clie.COUNT > 0 THEN

      FORALL i IN 1 .. v_oid_clie.COUNT

        INSERT INTO mae_clien_comun
          (oid_clie_comu,
           clie_oid_clie,
           ticm_oid_tipo_comu,
           val_dia_comu,
           val_text_comu,
           fec_hora_desd,
           fec_hora_hast,
           val_inte_comu,
           ind_comu_ppal,
           fec_ulti_actu)
        VALUES
          (mae_clco_seq.NEXTVAL,
           v_oid_clie(i),
           lnoidtipocomutelecelular,
           'L',
           v_val_tele_celu(i),
           NULL,
           NULL,
           0,
           0,
           SYSDATE);

    END IF;
    EXIT WHEN c_insdatostm%NOTFOUND;
  END LOOP;
  CLOSE c_insdatostm;

  /*Inserta datos email*/
  OPEN c_insdatosml(lnoidtipocomuemail);
  LOOP
    FETCH c_insdatosml BULK COLLECT
      INTO v_val_corr_elec, v_oid_clie, v_val_ind_acti_emai, v_val_cam_acti_emai LIMIT rows;

    IF v_oid_clie.COUNT > 0 THEN

      FORALL i IN 1 .. v_oid_clie.COUNT

        INSERT INTO mae_clien_comun
          (oid_clie_comu,
           clie_oid_clie,
           ticm_oid_tipo_comu,
           val_dia_comu,
           val_text_comu,
           fec_hora_desd,
           fec_hora_hast,
           val_inte_comu,
           ind_comu_ppal,
           fec_ulti_actu)
        VALUES
          (mae_clco_seq.NEXTVAL,
           v_oid_clie(i),
           lnoidtipocomuemail,
           'L',
           v_val_corr_elec(i),
           NULL,
           NULL,
           0,
           0,
           SYSDATE);
           
      FORALL i IN 1 .. v_oid_clie.COUNT
        UPDATE mae_clien_datos_adici adi
           SET adi.IND_ACTI_EMAI = v_val_ind_acti_emai(i), 
               adi.CAM_ACTI_EMAI = v_val_cam_acti_emai(i)
         WHERE adi.CLIE_OID_CLIE = v_oid_clie(i);

    END IF;
    EXIT WHEN c_insdatosml%NOTFOUND;
  END LOOP;
  CLOSE c_insdatosml;

  OPEN c_upddatos;
  LOOP
    FETCH c_upddatos BULK COLLECT
      INTO v_cod_pais, v_cod_clie, v_val_tele_fijo, v_val_tele_celu, 
           v_val_corr_elec, v_oid_clie, v_val_ind_acti_emai, v_val_cam_acti_emai LIMIT rows;

    IF v_cod_pais.COUNT > 0 THEN
      /*TELEFONO FIJO*/
      FORALL i IN 1 .. v_cod_pais.COUNT
        UPDATE mae_clien_comun com
           SET com.val_text_comu = v_val_tele_fijo(i)
         WHERE com.ticm_oid_tipo_comu = lnoidtipocomutelefijo
           AND v_val_tele_fijo(i) IS NOT NULL
           AND com.clie_oid_clie = v_oid_clie(i) ;

      /*TELEFONO MOVIL*/
      FORALL i IN 1 .. v_cod_pais.COUNT
        UPDATE mae_clien_comun com
           SET com.val_text_comu = v_val_tele_celu(i)
         WHERE com.ticm_oid_tipo_comu =
               lnoidtipocomutelecelular
           AND v_val_tele_celu(i) IS NOT NULL
            AND com.clie_oid_clie = v_oid_clie(i);

      /*TEMAIL*/
      FORALL i IN 1 .. v_cod_pais.COUNT
        UPDATE mae_clien_comun com
           SET com.val_text_comu = v_val_corr_elec(i)
         WHERE com.ticm_oid_tipo_comu = lnoidtipocomuemail
           AND v_val_corr_elec(i) IS NOT NULL
            AND com.clie_oid_clie = v_oid_clie(i);
            
      /*INDICADOR ACTIVACION EMAIL*/
      FORALL i IN 1 .. v_cod_pais.COUNT
        UPDATE mae_clien_datos_adici adi
           SET adi.IND_ACTI_EMAI = v_val_ind_acti_emai(i)
         WHERE v_val_ind_acti_emai(i) IS NOT NULL
           AND adi.clie_oid_clie = v_oid_clie(i);
            
      /*CAMPAÑA ACTIVACION EMAIL*/
      FORALL i IN 1 .. v_cod_pais.COUNT
        UPDATE mae_clien_datos_adici adi
           SET adi.CAM_ACTI_EMAI = v_val_cam_acti_emai(i)
         WHERE v_val_cam_acti_emai(i) IS NOT NULL
           AND adi.clie_oid_clie = v_oid_clie(i);

    END IF;
    EXIT WHEN c_upddatos%NOTFOUND;
  END LOOP;
  CLOSE c_upddatos;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_MYE_PROCE_ACTUA_DATOS: ' ||
                             ls_sqlerrm);

END int_pr_mye_proce_actua_datos;



  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Historicos de posibles egresos
  Fecha Creacion    : 08/11/2010
  Parametros
    pscodigopais       : codigo pais,
    pscodigosistema    : codigo sistema,
    pscodigointerfaz   : codigo interfaz,
    psnombrearchivo    : nombre archivo,
    psperiodo          : periodo,
    pscodigomarca      : marca,
    pscodigocanal      :canal
  Autor             : Sergio Buchelli Silva
  ***************************************************************************/
  PROCEDURE INT_PR_MYE_ENVIO_HISTO_PEGS
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psperiodo          VARCHAR2,
    pscodigomarca      VARCHAR2,
    pscodigocanal      VARCHAR2
  )IS

  Cursor c_interfaz(oidperiodo18 number,
                    oidperiodo17 number,
                    oidperiodo16 number,
                    oidperiodo15 number,
                    oidperiodo14 number,
                    oidperiodo13 number,
                    oidperiodo12 number,
                    oidperiodo11 number,
                    oidperiodo10 number,
                    oidperiodo9 number,
                    oidperiodo8 number,
                    oidperiodo7 number,
                    oidperiodo6 number,
                    oidperiodo5 number,
                    oidperiodo4 number,
                    oidperiodo3 number,
                    oidperiodo2 number,
                    oidperiodo1 number
                 ) IS
         SELECT zorg.cod_regi,
                zzon.cod_zona,
                clie.oid_clie,
                clie.cod_clie,
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo18,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo17,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo16,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo15,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo14,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo13,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo12,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo11,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo10,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo9,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo8,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo7,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo6,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo5,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo4,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo3,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo2,clie.oid_clie),
                INT_PKG_MYEBE.INT_FN_DEVUE_MONTO_FACTU_NCAMP(oidperiodo1,clie.oid_clie)
          FROM mae_clien_unida_admin cuad,
               zon_terri_admin ztad,
               zon_secci zscc,
               zon_terri terr,
               zon_zona zzon,
               zon_regio zorg,
               mae_clien_datos_adici clda,
               mae_clien clie
         WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
           AND cuad.clie_oid_clie = clda.clie_oid_clie
           AND clda.clie_oid_clie = clie.oid_clie
           AND ztad.zscc_oid_secc = zscc.oid_secc
           AND ztad.terr_oid_terr = terr.oid_terr
           AND zscc.zzon_oid_zona = zzon.oid_zona
           AND zzon.zorg_oid_regi = zorg.oid_regi
           AND cuad.ind_acti = 1
           AND clda.esta_oid_esta_clie = 4;
           --AND clie.cod_clie='007659202';


    /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;



   TYPE interfazTipo IS RECORD
   (  codigoRegion          ZON_REGIO.COD_REGI%TYPE,
      codigoZona            ZON_ZONA.COD_ZONA%TYPE,
      oidCliente            MAE_CLIEN.OID_CLIE%TYPE,
      codigoCliente         MAE_CLIEN.COD_CLIE%TYPE,
      monto18               FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto17               FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto16               FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto15               FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto14               FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto13               FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto12               FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto11               FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto10               FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto9                FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto8                FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto7                FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto6                FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto5                FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto4                FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto3                FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto2                FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE,
      monto1                FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_DOCU%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;



  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  oidPais SEG_PAIS.OID_PAIS%TYPE;
  lbAbrirUtlFile      BOOLEAN;
  oidmarca SEG_MARCA.OID_MARC%TYPE;
  oidcanal SEG_CANAL.OID_CANA%TYPE;
  periodo18 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo17 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo16 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo15 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo14 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo13 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo12 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo11 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo10 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo9 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo8 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo7 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo6 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo5 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo4 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo3 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo2 SEG_PERIO_CORPO.COD_PERI%TYPE ;
  periodo1 SEG_PERIO_CORPO.COD_PERI%TYPE ;

  oidperiodo18 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo17 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo16 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo15 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo14 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo13 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo12 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo11 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo10 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo9 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo8 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo7 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo6 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo5 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo4 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo3 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo2 SEG_PERIO_CORPO.OID_PERI%TYPE ;
  oidperiodo1 SEG_PERIO_CORPO.OID_PERI%TYPE ;

 -- w_filas    NUMBER := 5000;

BEGIN
     oidPais    := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais);
     oidMarca   := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(pscodigomarca);
     oidCanal   := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(pscodigocanal);


	periodo18:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -18);
	periodo17:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -17);
	periodo16:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -16);
	periodo15:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -15);
	periodo14:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -14);
	periodo13:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -13);
	periodo12:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -12);
	periodo11:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -11);
	periodo10:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -10);
	periodo9:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -9);
	periodo8:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -8);
	periodo7:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -7);
	periodo6:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -6);
	periodo5:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -5);
	periodo4:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -4);
	periodo3:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -3);
	periodo2:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -2);
	periodo1:=  Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(psperiodo,oidPais,
                                                                 oidMarca,
                                                                 oidCanal,
                                                                 -1);

    --calculamos los oids
    oidperiodo18 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo18);
    oidperiodo17 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo17);
    oidperiodo16 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo16);
    oidperiodo15 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo15);
    oidperiodo14 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo14);
    oidperiodo13 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo13);
    oidperiodo12 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo12);
    oidperiodo11 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo11);
    oidperiodo10 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo10);
    oidperiodo9 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo9);
    oidperiodo8 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo8);
    oidperiodo7 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo7);
    oidperiodo6 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo6);
    oidperiodo5 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo5);
    oidperiodo4 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo4);
    oidperiodo3 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo3);
    oidperiodo2 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo2);
    oidperiodo1 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(periodo1);


    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz( oidperiodo18 ,
                    oidperiodo17 ,
                    oidperiodo16 ,
                    oidperiodo15 ,
                    oidperiodo14 ,
                    oidperiodo13 ,
                    oidperiodo12 ,
                    oidperiodo11 ,
                    oidperiodo10 ,
                    oidperiodo9 ,
                    oidperiodo8 ,
                    oidperiodo7 ,
                    oidperiodo6 ,
                    oidperiodo5 ,
                    oidperiodo4 ,
                    oidperiodo3 ,
                    oidperiodo2 ,
                    oidperiodo1);

        LOOP

                  FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT w_filas;
                     /* Procedimiento inicial para generar interfaz */
                               IF lbAbrirUtlFile THEN
                                    /* Procedimiento inicial para generar interfaz */
                                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(pscodigopais, pscodigosistema, pscodigointerfaz,
                                        psnombrearchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                                   lbAbrirUtlFile := FALSE;
                               END IF;

                    IF interfazRecord.COUNT > 0 THEN


                        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

                        lsLinea :=  interfazRecord(x).codigoCliente ||';'||
                                    interfazRecord(x).codigoRegion  ||';'||
                                    interfazRecord(x).codigoZona    ||';'||
                                    interfazRecord(x).monto18 ||';'||
                                    interfazRecord(x).monto17 ||';'||
                                    interfazRecord(x).monto16 ||';'||
                                    interfazRecord(x).monto15 ||';'||
                                    interfazRecord(x).monto14 ||';'||
                                    interfazRecord(x).monto13 ||';'||
                                    interfazRecord(x).monto12 ||';'||
                                    interfazRecord(x).monto11 ||';'||
                                    interfazRecord(x).monto10 ||';'||
                                    interfazRecord(x).monto9  ||';'||
                                    interfazRecord(x).monto8  ||';'||
                                    interfazRecord(x).monto7  ||';'||
                                    interfazRecord(x).monto6  ||';'||
                                    interfazRecord(x).monto5  ||';'||
                                    interfazRecord(x).monto4  ||';'||
                                    interfazRecord(x).monto3  ||';'||
                                    interfazRecord(x).monto2  ||';'||
                                    interfazRecord(x).monto1 ;

                            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                        END LOOP;
                    END IF;

                 EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
       CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);
       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_MYE_ENVIO_HISTO_PEGS: ' ||
                             ls_sqlerrm);

END INT_PR_MYE_ENVIO_HISTO_PEGS;


/***********************************************************************************
  Descripcion        : Retorna el monto factura de la consultora en el periodo dado
                        si no facturo en el periodo retorna cero
  Fecha Creacion     : 08/11/2010
  Parametros Entrada :
    psCodigoPais       Codigo Pais
    psCodigoPeriodo    Codigo Periodo
    psOidCliente       Oid Cliente

  Autor              : Sergio Buchelli
  ***************************************************************************/
  FUNCTION INT_FN_DEVUE_MONTO_FACTU_NCAMP(
    pnOidPeriodo  NUMBER,
    psOidCliente  NUMBER ) RETURN NUMBER
  IS
   lnMonto number(12,2);
   lnOidPeriodo fac_docum_conta_cabec.PERD_OID_PERI%TYPE;
  BEGIN

     --lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

     SELECT  NVL(SUM(dcca.val_tota_paga_docu),0) INTO lnMonto
          FROM ped_solic_cabec soca,
               ( SELECT soca_oid_soli_cabe,
                        SUM(val_tota_paga_docu) AS val_tota_paga_docu
                   FROM  fac_docum_conta_cabec
                   where PERD_OID_PERI = pnOidPeriodo
                  GROUP BY soca_oid_soli_cabe ) dcca,
               ped_tipo_solic_pais,
               ped_tipo_solic
              -- cra_perio perd,
               --seg_perio_corpo peri
         WHERE soca.soca_oid_soli_cabe = dcca.soca_oid_soli_cabe
           AND soca.tspa_oid_tipo_soli_pais = ped_tipo_solic_pais.oid_tipo_soli_pais
           AND ped_tipo_solic_pais.tsol_oid_tipo_soli = ped_tipo_solic.oid_tipo_soli
           AND soca.perd_oid_peri = pnOidPeriodo--perd.oid_peri
           --AND perd.peri_oid_peri = peri.oid_peri
           AND ped_tipo_solic.cod_tipo_soli = ('SOC')
         --  AND peri.cod_peri = psCodigoPeriodo
           AND clie_oid_clie = psOidCliente
     GROUP BY clie_oid_clie ;

         RETURN lnMonto;

  EXCEPTION
   WHEN OTHERS THEN
     RETURN 0;
  END INT_FN_DEVUE_MONTO_FACTU_NCAMP;

  /***********************************************************************************
  Descripcion        : Proceso para crear nueva interfaz de clientes para web
  Fecha Creacion     : 23/08/2012
   Parametros Entrada :
     psCodigoPais       Codigo Pais
     psCodigoSistema    Codigo Sistema
     psCodigoInterfaz   Codigo Interfaz
     psNombreArchivo    Nombre Archivo
    psCodigoPeriodo    Codigo Periodo

  Autor             : Sergio Buchelli
  ***************************************************************************/
PROCEDURE INT_PR_MYE_ENVIA_CLIEN_WEB(psCodigoPais       VARCHAR2,
                                 psCodigoSistema    VARCHAR2,
                                 psCodigoInterfaz   VARCHAR2,
                                 psNombreArchivo    VARCHAR2,
                                 psCodigoPeriodo	  VARCHAR2)

   IS

    CURSOR c_interfaz(pn_monto_minimo1 NUMBER, pn_monto_minimo2 NUMBER, pn_monto_maximo1 NUMBER) IS
      SELECT
       SEG_PAIS.COD_PAIS codPais,
       MAE_CLIEN.COD_CLIE codClie,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_NOM1 || ' ' || MAE_CLIEN.VAL_NOM2 || ' ' || MAE_CLIEN.VAL_APE1 || ' ' || MAE_CLIEN.VAL_APE2,  'a"'',;|' || CHR(10) || CHR(13) || CHR(20), 'a        ')) VAL_NOMBRE,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_NOM1, 'a"'',;|' || CHR(10) || CHR(13) || CHR(20), 'a        ')) VAL_NOM1,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_APE1, 'a"'',;|' || CHR(10) || CHR(13) || CHR(20), 'a        ')) VAL_NOM2,
       TRIM(TRANSLATE(MAE_CLIEN.VAL_APE2, 'a"'',;|' || CHR(10) || CHR(13) || CHR(20), 'a        ')) VAL_APE2,
       TRIM(TRANSLATE(SEG_TIPO_VIA.COD_TIPO_VIA || ' ' || MAE_CLIEN_DIREC.VAL_NOMB_VIA || ' ' || MAE_CLIEN_DIREC.NUM_PPAL || ' ' || MAE_CLIEN_DIREC.VAL_OBSE || ' ' || MAE_CLIEN_DIREC.VAL_COD_POST, 'a"'',;|' || CHR(10) || CHR(13) || CHR(20), 'a        ')) DIR1,
       TRIM (GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(SEG_PAIS.OID_PAIS, MAE_CLIEN.OID_CLIE, 1) || ' ' || GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(SEG_PAIS.OID_PAIS, MAE_CLIEN.OID_CLIE, 2) || ' ' || GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(SEG_PAIS.OID_PAIS, MAE_CLIEN.OID_CLIE, 3)) AS DIR2,
       MAE_CLIEN.ind_PROL indProl,
       ' ' LIBRE,
       ZON_REGIO.COD_REGI codRegi,
       ZON_ZONA.COD_ZONA codZona,
       ZON_SECCI.COD_SECC codSecc,
       NVL(ZON_TERRI.COD_TERR, 0) AS COD_TERR,
       MAE_CLIEN.SAL_DEUD_ANTE SALDO,
       MAE_CLIEN_IDENT.NUM_DOCU_IDEN numDoc,
       MAE_ESTAT_CLIEN.COD_ESTA_CLIE ESTADO,
       MAE_CLIEN_ESTAT.CAMP_ULTI_PEDI ULTIMO_PEDIDO,
       GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'TF') FIJO,
       GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'TM') CELULAR,
       GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'TT') TRABAJO,
       GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MAE_CLIEN.OID_CLIE, 'ML') CORREO,
       1 AUTORIZADA,
       MAE_CLIEN_ESTAT.Fec_Egre FECHA_EGRESO,
       MAE_CLIEN.Fec_Ingr FECHA_INGRESO,
       MAE_CLIEN_ESTAT.VAL_MONT_FACT MONTO_FACTURADO,
       case when ZON_ZONA.COD_ZONA like '90%' then pn_monto_minimo1 else pn_monto_minimo2 end MONTO_MINIMO,
       MAE_TIPO_DOCUM.COD_TIPO_DOCU tipoDoc,
       MAE_CLIEN_DATOS_ADICI.IND_ACTI indActi,
       '0' IND_MARC_LBEL,
       pn_monto_maximo1 MONTO_MAXIMO
       FROM
       MAE_CLIEN,
       SEG_PAIS,
       MAE_CLIEN_DATOS_ADICI,
       MAE_CLIEN_IDENT,
       MAE_TIPO_DOCUM,
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
       MAE_CLIEN_ESTAT
       WHERE ((SEG_PAIS.OID_PAIS = MAE_CLIEN.PAIS_OID_PAIS)
        AND (SEG_PAIS.COD_PAIS = pscodigopais)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE)
        AND (MAE_CLIEN.OID_CLIE = MAE_CLIEN_ESTAT.OID_CLIE(+))
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
        AND (MAE_CLIEN_DATOS_ADICI.IND_ACTI = 1)
        );

	TYPE interfazRec IS RECORD( codigoPais      SEG_PAIS.COD_PAIS%TYPE,
                              codigoCliente   MAE_CLIEN.COD_CLIE%TYPE,
                              nombreCompleto    varchar2(500),
                              nombre          varchar2(100),
                              apepate					varchar2(100),
                              apemate      		varchar2(100),
                              dir1  					varchar2(1000),
                              dir2  					varchar2(1000),
                              indProl     		MAE_CLIEN.ind_PROL%TYPE,
                              libre  					varchar2(1),
                              codRegi 				ZON_REGIO.COD_REGI%TYPE,
                              codZona     		ZON_ZONA.COD_ZONA%TYPE,
                              codSecc     		ZON_SECCI.COD_SECC%TYPE,
                              codTerr    			varchar2(6),
                              saldo  					varchar2(15),
                              numDoc     			MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE,
                              estado  				varchar2(2),
                              ultimoPedido 	  varchar2(6),
                              fijo 						varchar2(30),
                              celular 				varchar2(30),
                              trabajo 				varchar2(30),
                              correo 					varchar2(100),
                              autorizada 			varchar2(1),
                              fechaEgreso 		varchar2(8) ,
                              fechaIngreso 		varchar2(6),
                              montoFacturado 	varchar2(15),
                              montoMinimo 		varchar2(15),
                              tipodoc 				MAE_TIPO_DOCUM.COD_TIPO_DOCU%TYPE,
                              indacti 				MAE_CLIEN_DATOS_ADICI.IND_ACTI%TYPE,
                              indMarcLbel 		varchar2(1),
                              montoMaximo 		varchar2(15));

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

	/* Variables usadas para la generacion del archivo de texto */
	  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
	  W_FILAS             NUMBER := 1000 ;
	  v_handle            UTL_FILE.FILE_TYPE;
	  lsLinea             VARCHAR2(1000);
	  lsNombreArchivo     VARCHAR2(50);
	  lbAbrirUtlFile      BOOLEAN;
	  ln_monto_minimo1    NUMBER(10,2) := 0 ;
	  ln_monto_minimo2    NUMBER(10,2) := 0 ;
	  ln_monto_maximo1    NUMBER(10,2) := 0 ;

BEGIN

    select max(val_niv1) into ln_monto_minimo1 from ped_monto_minim where rownum=1;
    select min(val_niv1) into ln_monto_minimo2 from ped_monto_minim where rownum=1;
    select max(VAL_MONT_MAXI_PERM) into ln_monto_maximo1 from car_param_carte where IND_MONT_MAXI=1 and rownum=1;


   lbAbrirUtlFile := TRUE;
	        OPEN c_interfaz(ln_monto_minimo1,ln_monto_minimo2,ln_monto_maximo1);
	        LOOP
	           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
			       /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
                 GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais,
                                                        psCodigosistema,
                                                        psCodigoInterfaz,
                                                        psNombreArchivo,
                                                        lsDirTempo,
                                                        lsNombreArchivo,
                                                        V_HANDLE);
                 lbAbrirUtlFile := FALSE;
             END IF;

	           IF interfazRecord.COUNT > 0 THEN
	              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
	                  lsLinea :=  interfazRecord(x).codigoPais      ||';' ||
                                interfazRecord(x).codigoCliente   ||';' ||
                                interfazRecord(x).nombreCompleto  ||';' ||
                                interfazRecord(x).nombre          ||';' ||
                                interfazRecord(x).apepate					||';' ||
                                interfazRecord(x).apemate      		||';' ||
                                interfazRecord(x).dir1  					||';' ||
                                interfazRecord(x).dir2  					||';' ||
                                interfazRecord(x).indProl     		||';' ||
                                interfazRecord(x).libre  					||';' ||
                                interfazRecord(x).codRegi 				||';' ||
                                interfazRecord(x).codZona     		||';' ||
                                interfazRecord(x).codSecc     		||';' ||
                                interfazRecord(x).codTerr    			||';' ||
                                interfazRecord(x).saldo  					||';' ||
                                interfazRecord(x).numDoc     			||';' ||
                                interfazRecord(x).estado  				||';' ||
                                interfazRecord(x).ultimoPedido 	  ||';' ||
                                interfazRecord(x).fijo 						||';' ||
                                interfazRecord(x).celular 				||';' ||
                                interfazRecord(x).trabajo 				||';' ||
                                interfazRecord(x).correo 					||';' ||
                                interfazRecord(x).autorizada 			||';' ||
                                interfazRecord(x).fechaEgreso 		||';' ||
                                interfazRecord(x).fechaIngreso 		||';' ||
                                interfazRecord(x).montoFacturado 	||';' ||
                                interfazRecord(x).montoMinimo 		||';' ||
                                interfazRecord(x).indacti 				||';' ||
                                interfazRecord(x).indMarcLbel     ||';' ||
                                interfazRecord(x).montoMaximo ;

	                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
	              END LOOP;
	           END IF;
	           EXIT WHEN c_interfaz%NOTFOUND;
	        END LOOP;
	        CLOSE c_interfaz;


	   IF NOT lbAbrirUtlFile THEN
	      utl_file.fclose(V_HANDLE);
        /* Procedimiento final para generar interfaz */
        GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
	   END IF;

	   RETURN;
	EXCEPTION
	WHEN OTHERS THEN
		     ln_sqlcode := SQLCODE;
		     ls_sqlerrm := substr(sqlerrm,1,250);
		     RAISE_APPLICATION_ERROR(-20123, 'INT_PR_MYE_ENVIA_CLIEN_WEB: '||ls_sqlerrm);
  end INT_PR_MYE_ENVIA_CLIEN_WEB;

/***********************************************************************************
  Descripcion        : Proceso para crear nueva interfaz de maestros de incobrables
  Fecha Creacion     : 30/01/2013
   Parametros Entrada :
     psCodigoPais       Codigo Pais
     psCodigoSistema    Codigo Sistema
     psCodigoInterfaz   Codigo Interfaz
     psNombreArchivo    Nombre Archivo

  Autor             : Ivan Tocto
  ***************************************************************************/
PROCEDURE INT_PR_MYE_ENVIA_MAEST_INCOB(psCodigoPais VARCHAR2,
                                 psCodigoSistema    VARCHAR2,
                                 psCodigoInterfaz   VARCHAR2,
                                 psNombreArchivo    VARCHAR2,
                                 psNumeroLote       VARCHAR2)
IS
    CURSOR c_interfaz(codigoInterfaz VARCHAR2) IS
        SELECT a.cod_clie,
            TRIM(TRANSLATE(a.VAL_NOM1 || ' ' || a.VAL_NOM2 || ' ' || a.VAL_APE1 || ' ' || a.VAL_APE2,  'a"'',;|' || CHR(10) || CHR(13) || CHR(20), 'a        ')) nombre,
           'FS' codffvv
        FROM ccc_clien_casti a
        WHERE a.fec_modi >=
              (SELECT MAX (fec_ipro)
                 FROM bas_histo_lotes
                WHERE pais_cod_pais = psCodigoPais
                  AND sist_cod_sist = psCodigoSistema
                  AND inte_cod_inte = codigoInterfaz
                  AND num_lote != psNumeroLote);

	TYPE interfazRec IS RECORD(
                                codigoCliente     MAE_CLIEN.COD_CLIE%TYPE,
                                nombreCompleto    varchar2(500),
                                codigoFFVV        varchar2(2));

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

	/* Variables usadas para la generacion del archivo de texto */
	  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
	  W_FILAS             NUMBER := 1000 ;
	  v_handle            UTL_FILE.FILE_TYPE;
	  lsLinea             VARCHAR2(1000);
	  lsNombreArchivo     VARCHAR2(50);
	  lbAbrirUtlFile      BOOLEAN;
      lsCodigoInterfazPaquete   BAS_COMPO_PAQUE.INTE_COD_INPA%TYPE;
BEGIN

    /**/
    SELECT INTE_COD_INPA
    INTO lsCodigoInterfazPaquete
    FROM BAS_COMPO_PAQUE
    WHERE PAIS_COD_PAIS = psCodigoPais
    AND SIST_COD_SIST = psCodigoSistema
    AND INTE_COD_INTE = psCodigoInterfaz;

    lbAbrirUtlFile := TRUE;

	OPEN c_interfaz(lsCodigoInterfazPaquete);
    LOOP
	    FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
            GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais,
                                                        psCodigosistema,
                                                        psCodigoInterfaz,
                                                        psNombreArchivo,
                                                        lsDirTempo,
                                                        lsNombreArchivo,
                                                        V_HANDLE);
            lbAbrirUtlFile := FALSE;
        END IF;

        IF interfazRecord.COUNT > 0 THEN
	        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
	            lsLinea :=  interfazRecord(x).codigoCliente   ||';' ||
                                interfazRecord(x).nombreCompleto    ||';' ||
                                interfazRecord(x).codigoFFVV ;

	            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END LOOP;
        END IF;

        EXIT WHEN c_interfaz%NOTFOUND;

    END LOOP;
	CLOSE c_interfaz;


    IF NOT lbAbrirUtlFile THEN
	    utl_file.fclose(V_HANDLE);
            /* Procedimiento final para generar interfaz */
        GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

	RETURN;

EXCEPTION
	WHEN OTHERS THEN
		     ln_sqlcode := SQLCODE;
		     ls_sqlerrm := substr(sqlerrm,1,250);
		     RAISE_APPLICATION_ERROR(-20123, 'INT_PR_MYE_ENVIA_MAEST_INCOB: '||ls_sqlerrm);
END INT_PR_MYE_ENVIA_MAEST_INCOB;

/***************************************************************************
  Descripcion       : Genera Interfaz de RECEPCIONAR ACTIVACION FLEXIPAGO WEB.
  Fecha Creacion    : 26/05/2014
  Autor             : Gonzalo Huertas
  ***************************************************************************/
PROCEDURE int_pr_mye_activ_flexi_web
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9 --anulado = 9; activo =1
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;

    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);

    TYPE interfazcabtab IS TABLE OF interfazcab;

    interfazrecord interfazcabtab;

    TYPE t_cod_clie IS TABLE OF FLX_ACTIV_FLEXI_WEB.Cod_Clie %TYPE;
    TYPE t_cod_peri IS TABLE OF FLX_ACTIV_FLEXI_WEB.Cod_Peri %TYPE;
    TYPE t_indicado IS TABLE OF FLX_ACTIV_FLEXI_WEB.Ind_Acti %TYPE;

    ----------------------
    v_codclie t_cod_clie := t_cod_clie();
    v_codperi t_cod_peri := t_cod_peri();
    v_indicad t_indicado := t_indicado();

    ----------------------

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);

    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;
    -------------------------
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);

    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;

          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
                IF (posicion = 1) THEN
                  v_codclie.extend;
                  v_codclie(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_codperi.extend;
                  v_codperi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 3) THEN
                  v_indicad.extend;
                  v_indicad(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;

                inicio := inicio + longitud;

              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;

        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;

    utl_file.fclose(v_handle);

    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codclie.count
      INSERT INTO FLX_ACTIV_FLEXI_WEB
        (OID_ACTI_FLEX_WEB,
         NUM_LOTE,
         COD_CLIE,
         COD_PERI,
         IND_ACTI)
      VALUES
        (FLX_ACTIV_FLEXI_WEB_SEQ.NEXTVAL,
         psnumerolote,
         v_codclie(i),
         v_codperi(i),
         v_indicad(i));

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_mye_activ_flexi_web: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
  END int_pr_mye_activ_flexi_web;
  
  /***************************************************************************
  Descripcion       : Genera Interfaz de RECEPCIONAR PREMIOS WEB.
  Fecha Creacion    : 08/02/2016
  Autor             : Segundo Leiva
  ***************************************************************************/
  PROCEDURE INT_PR_MYR_RECEP_PREMI_WEB
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psusuario     VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
      
    TYPE interfazcabtab IS TABLE OF interfazcab;
    interfazrecord interfazcabtab;

    CURSOR c_historial(cuvArchivo VARCHAR2) IS
      SELECT
          oid_para_gral   copa_oid_para_gral,
          lpa.num_prem    num_prem, 
          pa.panp_oid_para_nive_prem    panp_oid_para_nive_prem
      from
          inc_concu_param_gener cpg, 
          inc_param_gener_premi pgp, 
          inc_param_nivel_premi pnp, 
          inc_premi_artic pa, 
          inc_lote_premi_artic lpa, 
          inc_artic_lote al
      where
      cpg.oid_para_gral = pgp.copa_oid_para_gral
      and pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
      and pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
      and pa.oid_prem_arti = lpa.prar_oid_prem_arti
      and lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
      and pgp.tprm_oid_tipo_pion=1
      and cpg.ind_acti = 1
      and lpa.ind_premi_elweb=1
      and cpg.ind_prog_punt = 1
      and al.cod_vent_fict= cuvArchivo;
  
      TYPE historialcab IS RECORD(
        copa_oid_para_gral  inc_concu_param_gener.oid_para_gral%TYPE,
        num_prem   inc_lote_premi_artic.num_prem%TYPE,
        panp_oid_para_nive_prem inc_premi_artic.panp_oid_para_nive_prem%TYPE);
      
      TYPE historialcabtab IS TABLE OF historialcab;
      historialrecord historialcabtab;  
      
     CURSOR c_historialdistinct IS
          select distinct
        CLIE_OID_CLIE   oidcliente,
        PERD_OID_PERI    oidperiodo, 
        COPA_OID_PARA_GRAL  copaoidparagral
          from
              inc_histo_carga_premi_elweb;
        
      TYPE historialdistinctcab IS RECORD(
        oidcliente  inc_histo_carga_premi_elweb.clie_oid_clie%TYPE,
        oidperiodo   inc_histo_carga_premi_elweb.PERD_OID_PERI%TYPE,
        copaoidparagral inc_histo_carga_premi_elweb.COPA_OID_PARA_GRAL%TYPE);
        
        TYPE historialdistinctcabtab IS TABLE OF historialdistinctcab;
        historialdistinctrecord historialdistinctcabtab;
      
      
        CURSOR c_historialinsert(psnumerolote NUMERIC) IS     
            Select copa_oid_para_gral, 
                   clie_oid_clie, 
                   num_prem, 
                   panp_oid_para_nive_prem, 
                   perd_oid_peri, 
                   max(unids) unids 
       from(Select copa_oid_para_gral, 
            clie_oid_clie, num_prem, panp_oid_para_nive_prem, 
            perd_oid_peri, cuv_prem, sum(num_unid) unids
            
            From inc_histo_carga_premi_elweb
            where ind_apli is null and num_lote= psnumerolote
            Group by copa_oid_para_gral, clie_oid_clie, num_prem,
            panp_oid_para_nive_prem, perd_oid_peri, cuv_prem)

        Group by copa_oid_para_gral, clie_oid_clie, num_prem,
        panp_oid_para_nive_prem, perd_oid_peri;
        
        TYPE historialinsertcab IS RECORD(
          copa_oid_para_gral  inc_histo_carga_premi_elweb.clie_oid_clie%TYPE,
          clie_oid_clie   inc_histo_carga_premi_elweb.PERD_OID_PERI%TYPE,
          num_prem inc_histo_carga_premi_elweb.num_prem%TYPE,
          panp_oid_para_nive_prem inc_histo_carga_premi_elweb.panp_oid_para_nive_prem%TYPE,
          perd_oid_peri inc_histo_carga_premi_elweb.perd_oid_peri%TYPE,
          unids inc_histo_carga_premi_elweb.num_unid%TYPE);
      
        TYPE historialinsertcabtab IS TABLE OF historialinsertcab;
        historialinsertrecord historialinsertcabtab;
   
        TYPE t_numlote IS TABLE OF inc_histo_carga_premi_elweb.NUM_LOTE%TYPE;
        TYPE t_codpais IS TABLE OF inc_histo_carga_premi_elweb.COD_PAIS%TYPE;
        TYPE t_camenv IS TABLE OF inc_histo_carga_premi_elweb.CAM_ENV%TYPE;
        TYPE t_codcli IS TABLE OF inc_histo_carga_premi_elweb.COD_CLIE%TYPE;
        TYPE t_cuvprem IS TABLE OF inc_histo_carga_premi_elweb.CUV_PREM%TYPE;
        TYPE t_numunid IS TABLE OF inc_histo_carga_premi_elweb.NUM_UNID%TYPE;
        TYPE t_indcarg IS TABLE OF inc_histo_carga_premi_elweb.IND_CARG%TYPE;
        TYPE t_peroidperi IS TABLE OF inc_histo_carga_premi_elweb.PERD_OID_PERI%TYPE;
        TYPE t_clieoidclie IS TABLE OF inc_histo_carga_premi_elweb.CLIE_OID_CLIE%TYPE;
        TYPE t_copaoidparagral IS TABLE OF inc_histo_carga_premi_elweb.COPA_OID_PARA_GRAL%TYPE;
        TYPE t_numprem IS TABLE OF inc_histo_carga_premi_elweb.NUM_PREM%TYPE;
        TYPE t_panpoidparaniveprem IS TABLE OF inc_histo_carga_premi_elweb.PANP_OID_PARA_NIVE_PREM%TYPE;
        TYPE t_indapli IS TABLE OF inc_histo_carga_premi_elweb.IND_APLI%TYPE;
        TYPE t_msgmesaerro IS TABLE OF inc_histo_carga_premi_elweb.MSG_MESA_ERRO%TYPE;
        TYPE t_usucrea IS TABLE OF inc_histo_carga_premi_elweb.USU_CREA%TYPE;
        TYPE t_feccrea IS TABLE OF inc_histo_carga_premi_elweb.FEC_CREA%TYPE;

        ----------------------
        v_numlote          t_numlote := t_numlote();
        v_codpais           t_codpais := t_codpais();
        v_camenv      t_camenv := t_camenv();
        v_codcli          t_codcli := t_codcli();
        v_cuvprem       t_cuvprem := t_cuvprem();
        v_numunid        t_numunid := t_numunid();
        v_indcarg          t_indcarg := t_indcarg();
        v_peroidperi        t_peroidperi := t_peroidperi();
        v_clieoidclie       t_clieoidclie := t_clieoidclie();
        v_copaoidparagral        t_copaoidparagral := t_copaoidparagral();
        v_numprem       t_numprem := t_numprem();
        v_panpoidparaniveprem t_panpoidparaniveprem := t_panpoidparaniveprem();
        v_indapli     t_indapli := t_indapli();
        v_msgmesaerro      t_msgmesaerro := t_msgmesaerro();
        v_usucrea      t_usucrea := t_usucrea();
        v_feccrea      t_feccrea := t_feccrea();
        ----------------------
  
        /* Variables usadas para la generacion del archivo de texto */
        lsdirtempo bas_inter.dir_temp%TYPE;
        v_handle   utl_file.file_type;
      
        lslinea         VARCHAR2(4000);
        lsnombrearchivo VARCHAR2(50);
      
        /* Variables de parametros */
        i        BINARY_INTEGER := 0;
        posicion NUMBER := 0;
        longitud NUMBER := 0;
      
        inicio NUMBER := 0;
    
        l_oidclie inc_histo_carga_premi_elweb.clie_oid_clie%TYPE;
        l_oidperiodo inc_histo_carga_premi_elweb.perd_oid_peri%TYPE;
        l_indapli NUMBER := NULL;
        l_ocurrencia NUMBER := 0;
        l_ocurrenciaCliente NUMBER := 0;
        l_ocurrenciaPeriodo NUMBER := 0;
        l_numlote inc_histo_carga_premi_elweb.num_lote%TYPE;
     
        -------------------------
    
  BEGIN
    
      BEGIN
          select max(num_lote) into l_numlote 
          from inc_histo_carga_premi_elweb;    
          l_numlote:= l_numlote + 1;       
          IF(l_numlote IS NULL)THEN
               l_numlote:='1';   
          END IF;     
      END;

    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle,
                                                 32000);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
                --PAIS
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := TRIM(substr(lslinea, inicio, longitud));
                  
                --  CAMPAÑA
                ELSIF (posicion = 2) THEN
                  v_camenv.extend;
                  v_camenv(i) := TRIM(substr(lslinea, inicio, longitud));
                  
                  SELECT COUNT(1) INTO l_ocurrenciaPeriodo 
                  FROM seg_perio_corpo a 
                  WHERE a.cod_peri = v_camenv(i);
                  
                  IF(l_ocurrenciaPeriodo>0)THEN                         
                      v_peroidperi.extend;
                      v_peroidperi(i) := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(v_camenv(i));
                  ELSE                   
                      v_indapli.extend;
                      v_indapli(i) := 0;
                        
                      v_msgmesaerro.extend;
                      v_msgmesaerro(i) := 'Campaña no existe';
                      
                      v_peroidperi.extend;
                      v_peroidperi(i) := NULL;
                       
                  END IF;
                  
                --CLIENTE  
                ELSIF (posicion = 3) THEN
                  v_codcli.extend;
                  v_codcli(i) :=  TRIM(substr(lslinea, inicio, longitud));
                  
                  SELECT COUNT(1) INTO l_ocurrenciaCliente FROM MAE_CLIEN 
                  WHERE COD_CLIE = v_codcli(i);
                  
                  IF(l_ocurrenciaCliente>0)THEN                         
                      v_clieoidclie.extend;
                      v_clieoidclie(i) := gen_pkg_gener.gen_fn_devuelve_id_cliente(v_codcli(i));
                  ELSE                   
                      v_indapli.extend;
                      v_indapli(i) := 0;
                        
                      v_msgmesaerro.extend;
                      v_msgmesaerro(i) := 'Consultora no existe';
                      
                      v_clieoidclie.extend;
                      v_clieoidclie(i) := NULL;
                       
                  END IF;
                
                --CUV  
                ELSIF (posicion = 4) THEN
                      v_cuvprem.extend;
                      v_cuvprem(i) := TRIM(substr(lslinea, inicio, longitud));
                      IF(v_peroidperi(i) IS NOT NULL AND v_clieoidclie(i) IS NOT NULL)THEN
                         
                          OPEN c_historial(v_cuvprem(i));
                            LOOP
                               FETCH c_historial BULK COLLECT INTO historialrecord LIMIT W_FILAS;
                               IF historialrecord.COUNT > 0 THEN
                                  FOR x IN historialrecord.FIRST .. historialrecord.LAST LOOP
                                  v_copaoidparagral.extend;
                                  v_copaoidparagral(i) := historialrecord(x).copa_oid_para_gral;
                                  
                                  IF(v_copaoidparagral(i) IS NULL) THEN
                                      v_indapli.extend;
                                      v_indapli(i) := 0;
                                      v_msgmesaerro.extend;
                                      v_msgmesaerro(i) := 'CUV no existe o no es de WEB';
                                  END IF;
                      
                                  v_numprem.extend;
                                  v_numprem(i) := historialrecord(x).num_prem;
                                  
                                  v_panpoidparaniveprem.extend;
                                  v_panpoidparaniveprem(i) := historialrecord(x).panp_oid_para_nive_prem;
                                      
                                  END LOOP;
                              
                           ELSE
                              v_copaoidparagral.extend;
                              v_copaoidparagral(i) := NULL;
                              
                              v_indapli.extend;
                              v_indapli(i) := 0;
                              
                              v_msgmesaerro.extend;
                              v_msgmesaerro(i) := 'CUV no existe o no es de WEB';
                  
                              v_numprem.extend;
                              v_numprem(i) := NULL;
                              
                              v_panpoidparaniveprem.extend;
                              v_panpoidparaniveprem(i) := NULL;
                           END IF;
                           EXIT WHEN c_historial%NOTFOUND;
                        END LOOP;
                      CLOSE c_historial;   
                      
                      ELSE
                            v_copaoidparagral.extend;
                            v_copaoidparagral(i) := NULL;
                              
                            v_indapli.extend;
                            v_indapli(i) := 0;
                                                
                            v_numprem.extend;
                            v_numprem(i) := NULL;
                              
                            v_panpoidparaniveprem.extend;
                            v_panpoidparaniveprem(i) := NULL;            
                      END IF;
                                
                ELSIF (posicion = 5) THEN
                  v_numunid.extend;
                  v_numunid(i) := TO_NUMBER(substr(lslinea, inicio, longitud));               
                END IF;
                
                
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...      
    
    FOR i IN 1 .. v_codpais.count LOOP
         
     select count(1) into l_ocurrencia from inc_premi_elegi ipe 
           where ipe.clie_oid_clie = v_clieoidclie(i)
           and ipe.perd_oid_peri = v_peroidperi(i)
           and ipe.copa_oid_para_gral = v_copaoidparagral(i)
           and ipe.ind_pend = 0
           and ipe.cana_orig = 'SOMOS B.';
           
      IF(l_ocurrencia>0)THEN
           v_indapli.extend;
           v_indapli(i) := 0;
           v_msgmesaerro.extend;
           v_msgmesaerro(i) := 'Consultora ya facturó premios WEB para la campaña';
      END IF;
      
      INSERT INTO inc_histo_carga_premi_elweb
        (NUM_LOTE,
         COD_PAIS,
         CAM_ENV,
         COD_CLIE,
         CUV_PREM,
         NUM_UNID,
         IND_CARG,
         PERD_OID_PERI,
         CLIE_OID_CLIE,
         COPA_OID_PARA_GRAL,
         NUM_PREM,
         PANP_OID_PARA_NIVE_PREM,
         IND_APLI,
         MSG_MESA_ERRO,
         USU_CREA,
         FEC_CREA)
      VALUES
        (
         l_numlote,
         v_codpais(i),
         v_camenv(i),
         v_codcli(i),
         v_cuvprem(i),
         v_numunid(i),
         0,
         v_peroidperi(i),
         v_clieoidclie(i),
         v_copaoidparagral(i),
         v_numprem(i),
         v_panpoidparaniveprem(i),
         v_indapli(i),
         v_msgmesaerro(i),
         psusuario,
         SYSDATE);   
         
         COMMIT;
         
     END LOOP; 
    
     OPEN c_historialdistinct;
            LOOP
               FETCH c_historialdistinct BULK COLLECT INTO historialdistinctrecord LIMIT W_FILAS;
               IF historialdistinctrecord.COUNT > 0 THEN
                  FOR x IN historialdistinctrecord.FIRST .. historialdistinctrecord.LAST LOOP
                      
                  delete from inc_premi_elegi ipe
                  where ipe.clie_oid_clie = historialdistinctrecord(x).oidcliente
                  and ipe.perd_oid_peri = historialdistinctrecord(x).oidperiodo
                  and ipe.copa_oid_para_gral = historialdistinctrecord(x).copaoidparagral
                  and ipe.ind_pend=1
                  and ipe.cana_orig='SOMOS B.';
                                  
                  END LOOP;
               END IF;
               EXIT WHEN c_historialdistinct%NOTFOUND;
            END LOOP;
     CLOSE c_historialdistinct;
  
  
    OPEN c_historialinsert(l_numlote);
      LOOP
        FETCH c_historialinsert BULK COLLECT
          INTO historialinsertrecord LIMIT W_FILAS;
        IF historialinsertrecord.COUNT > 0 THEN
        
          FOR i IN historialinsertrecord.FIRST .. historialinsertrecord.LAST LOOP
             FOR x IN 1 .. historialinsertrecord(i).unids LOOP
          
              INSERT INTO inc_premi_elegi 
                 (OID_PREM_ELEG,          
                  NUM_PREM,
                  CLIE_OID_CLIE,
                  COPA_OID_PARA_GRAL, 
                  PANP_OID_PARA_NIVE_PREM,
                  FEC_SIST,
                  IND_PENDI,
                  IND_PEND,
                  USU_CREA,
                  USU_MODI,
                  FEC_MODI,
                  PERD_OID_PERI,
                  TIP_RECE,
                  COD_MOTI_INVA,
                  PERD_OID_PERI_IATN,
                  COD_TIPO_PREM,
                  VAL_DSTO_REAL,
                  NUM_PUNT_REAL,
                  SOCA_OID_SOLI_CABE,
                  CANA_ORIG)
                VALUES
                  (INC_PREL_SEQ.NEXTVAL,
                   historialinsertrecord(i).num_prem,
                   historialinsertrecord(i).clie_oid_clie,
                   historialinsertrecord(i).copa_oid_para_gral,
                   historialinsertrecord(i).panp_oid_para_nive_prem,
                   SYSDATE,
                   1,
                   1,
                   psusuario,
                   NULL,
                   NULL,
                   historialinsertrecord(i).perd_oid_peri,
                   'A',
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   'SOMOS B.');
               END LOOP;                
          END LOOP;
        
        END IF;
        EXIT WHEN c_historialinsert%NOTFOUND;
      END LOOP;
   CLOSE c_historialinsert;

   update inc_histo_carga_premi_elweb set ind_apli=1 where ind_apli=NULL;
   
   RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_MYR_RECEP_PREMI_WEB: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END INT_PR_MYR_RECEP_PREMI_WEB;
 
END INT_PKG_MYEBE;
/
