CREATE OR REPLACE PACKAGE APP_PKG_PROCE IS
/* Declaracion de variables */
ln_sqlcode NUMBER(10);
ls_sqlerrm VARCHAR2(1500);
ls_dato VARCHAR2(100);
W_FILAS NUMBER:=1000;


/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 psCodigoPais        :  Codigo de Pais
 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion
 psUsuario           :  Usuario

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE
 (psCodigoPais       VARCHAR2,
  psPeriodoProceso   VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psUsuario          VARCHAR2);


/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION ZONA
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 psCodigoPais        :  Codigo de Pais
 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion
 psUsuario           :  Usuario

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_ZONA
 (  lnOidPeriodo   NUMBER,
  psFechaFacturacion VARCHAR2,
    psRegion NUMBER
  );

/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION TERRI
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_TERRI
 (  lnOidPeriodo   NUMBER,
  psFechaFacturacion VARCHAR2,
    psRegion NUMBER
  );

/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION CLASI
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_CLASI
 (  lnOidPeriodo   NUMBER,
  psFechaFacturacion VARCHAR2,
    psRegion NUMBER
  );


/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION REINCIDENCIA
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:

 lnOidPeriodo    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_REINC_FNE
 (  lnOidPeriodo   NUMBER,
  psFechaFacturacion VARCHAR2,
    psRegion NUMBER
  );

/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION ESTATUS
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:

 lnOidPeriodo    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_ESTAT
 (  lnOidPeriodo   NUMBER,
    psFechaFacturacion VARCHAR2,
    psRegion NUMBER
    );

/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION NIVL RIESGO
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 lnOidPeriodo    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion

***************************************************************************/
PROCEDURE APP_PR_PROCE_NIVEL_RIESG
 (  lnOidPeriodo   NUMBER,
    psFechaFacturacion VARCHAR2,
    psRegion NUMBER
    );


/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION DINAMICO
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 psCodigoPais        :  Codigo de Pais
 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion
 psUsuario           :  Usuario

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_DINAM
 (psCodigoPais       VARCHAR2,
  psPeriodoProceso   VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psUsuario          VARCHAR2,
  psRegion           NUMBER)
  ;

/***************************************************************************
Descripcion : PROCESO DE ACTUALIZACION DE SECUENCIA EN RELACION ALA ZONA
Fecha Creacion : 29/09/2010
Autor : Sergio Buchelli
Parametros:
***************************************************************************/
PROCEDURE APP_PR_ACTUA_SECUE_ZONA(psRegion NUMBER);


/***************************************************************************
Descripcion : PROCESO DE ACTUALIZACION DE SECUENCIA DEBAJO EN RELACION ALA ZONA
Fecha Creacion : 29/09/2010
Autor : Sergio Buchelli
Parametros:
***************************************************************************/
PROCEDURE APP_PR_ACTUA_SECUE_NO_ZONA(psRegion NUMBER);

/*******************************************************************************
  Descripcion         : Recalcula la secuencia de los pedidos que aun no han sido
                        enviados a despacho de tal manera que permita la ejecucion
                        de mas de un lote de GP4 a GP5, esta actualizacion incluye
                        ademas la regeneracion de los documentos internos y la
                        actualizacion del numero de lote de facturacion al valor
                        maximo. Este proceso ha sido copiado del paquete
                        PED_PKG_PROCE.PED_PR_ACTUA_SECUE_PEDID para que este nuevo paquete
                        se encarge completamente de la secunciacion de pedidos
  Fecha Creacion      : 07/10/2010
  Parametros Entrada:
      p_codigoPais       : Codigo del pais a procesar
      p_codigoPeriodo    : Codigo de periodo
      p_fechaFacturacion : Fecha de Facturacion
  Autor               : Sergio Buchelli Silva
***************************************************************************/
PROCEDURE PED_PR_ACTUA_SECUE_PEDID(p_codigoPais IN VARCHAR2,
                                   p_codigoPeriodo IN VARCHAR2,
                                   p_fechaFacturacion IN VARCHAR2);

/**************************************************************************
  Descripcion       : APE_PR_INSER_HOMOL_YOBEL

  Fecha Creacion    : 11/10/2010
  Parametros Entrada:
      psCodigoZona        : Codigo de zona
      psCodigoSeccion     : Codigo de seccion
      psCodigoTerritorio  : Codigo de territorio
      psNumeroSecuencia   : Numero de secuencia
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
 PROCEDURE APP_PR_INSER_HOMOL_YOBEL(psCodigoZona         VARCHAR2,
                                    psCodigoSeccion      VARCHAR2,
                                    psCodigoTerritorio   VARCHAR2,
                                    psNumeroSecuencia    VARCHAR2 );

/**************************************************************************
  Descripcion       : APP_PR_INSER_TEMPO_HOMOL_YOBEL

  Fecha Creacion    : 06/10/2011
  Parametros Entrada:
      psCodigoZona        : Codigo de zona
      psCodigoSeccion     : Codigo de seccion
      psCodigoTerritorio  : Codigo de territorio
      psNumeroSecuencia   : Numero de secuencia
  Autor             : Francesco Rodriguez
  ***************************************************************************/
 PROCEDURE APP_PR_INSER_TEMPO_HOMOL_YOBEL(psCodigoZona   VARCHAR2,
                                    psCodigoSeccion      VARCHAR2,
                                    psCodigoTerritorio   VARCHAR2,
                                    psNumeroSecuencia    VARCHAR2 );

/***************************************************************************
Descripcion    : APP_PR_PROCE_SECUE_ZONA
Fecha Creacion : 21/10/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   psOidPais       :  OID de Pais
   pscodigoZona    :  Codigo de Zona
   psoidrutatrans  :  OID Ruta transporte
   pssecuencia     :  Secuencia
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_ZONA(psOidPais       NUMBER,
                                  pscodigoZona    VARCHAR2,
                                  psoidrutatrans  NUMBER,
                                  psUsuario       VARCHAR2,
                                  psmensajeerror  OUT VARCHAR2);

/***************************************************************************
Descripcion    : APP_PR_INSER_SECUE_ZONAS
Fecha Creacion : 04/11/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   psOidPais       :  OID de Pais
   pscodigoZona    :  Codigo de Zona
   psoidrutatrans  :  OID Ruta transporte
   pssecuencia     :  Secuencia
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_INSER_SECUE_ZONAS(psOidPais       NUMBER,
                                   pscodigoZona    VARCHAR2,
                                   psoidrutatrans  NUMBER,
                                   pssecuencia     NUMBER,
                                   psUsuario       VARCHAR2);

/***************************************************************************
Descripcion    : APP_PR_PROCE_SECUE_TERRI
Fecha Creacion : 21/10/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   pscodigoZona      : Codigo zona
   pscodigoSeccion   : Codigo Seccion
   pscodigoTerritorio: Codigo Territorio
   psoidterri        : Oid Teritorio
   psoidrutaterri    : Oid Ruta Territorio
   psoidrutatrans    : Oid Ruta Transporte
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_TERRI(pscodigoZona        VARCHAR2,
                                   pscodigoSeccion     VARCHAR2,
                                   pscodigoTerritorio  VARCHAR2,
                                   psoidterri          NUMBER,
                                   psoidrutaterri      NUMBER,
                                   psoidrutatrans      NUMBER);

/***************************************************************************
Descripcion    : APP_PR_PROCE_SECUE_TERRI2
Fecha Creacion : 21/10/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   pscodigoZona      : Codigo zona
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_TERRI2(pscodigoZona  VARCHAR2,
                                    psUsuario     VARCHAR2);


/***************************************************************************
Descripcion    : APP_PR_PROCE_SECUE_TERRIT
Fecha Creacion : 21/10/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   pscodigoZona      : Codigo zona
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_TERRIT(psUsuario     VARCHAR2);

/***************************************************************************
Descripcion    : APP_PR_PROCE_SECUE_TERRIF
Fecha Creacion : 21/10/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   pscodigoZona      : Codigo zona
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_TERRIZ(pscodigoZona  VARCHAR2,
                                    psUsuario     VARCHAR2);
/***************************************************************************
Descripcion    : APP_PR_INSER_SECUE_TERRI
Fecha Creacion : 04/11/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   psOidPais       :  OID de Pais
   pscodigoZona    :  Codigo de Zona
   psoidrutatrans  :  OID Ruta transporte
   pssecuencia     :  Secuencia
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_INSER_SECUE_TERRI(psoidrutatrans     NUMBER,
                                   psoidrutaterri     NUMBER,
                                   psoidterri         NUMBER,
                                   pssecuencia        NUMBER,
                                   pscodigoZona       VARCHAR2,
                                   pscodigoTerritorio  VARCHAR2,
                                   psOidPais           NUMBER,
                                   psUsuario           VARCHAR2);

/***************************************************************************
Descripcion : Proceso De Secunciacion De Zonas Desde La Tabla APP_HOMOL_YOBEL
Fecha Creacion : 20/01/2011
Autor : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_ZONAS (psUsuario  VARCHAR2);

/***************************************************************************
Descripcion    : APP_PR_VALID_CARGA_HOMOL
Fecha Creacion : 21/09/2011
Autor          : Francesco Rodriguez
Parametros:
   psListaErrores        :  Códigos de Zona, Sección, Territorio y/o Número de
                            Secuencia obtenidos como errados
   psMensajeInicioError  :  Mensaje cabecera de Error según la validación
   psMensajeFinalError   :  Mensaje final de Error según la validación
***************************************************************************/
PROCEDURE APP_PR_VALID_CARGA_HOMOL(psListaErrores       OUT VARCHAR2,
                                   psMensajeInicioError OUT VARCHAR2,
                                   psMensajeFinalError  OUT VARCHAR2);

/***************************************************************************
Descripcion : Proceso De CArga de Secuencias Zonas
Fecha Creacion : 11/04/2012
Autor : Jose Luis Rodriguez
***************************************************************************/
PROCEDURE APP_PR_CARGA_SECUE_ZONAS (psCodigoPais  VARCHAR2,
                                    psError   OUT VARCHAR2);

END APP_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY APP_PKG_PROCE IS
/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 psCodigoPais        :  Codigo de Pais
 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion
 psUsuario           :  Usuario

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE
 (psCodigoPais       VARCHAR2,
  psPeriodoProceso   VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psUsuario          VARCHAR2) IS

  lnOidPeriodo NUMBER;
  plsql_block VARCHAR2(4000);

  CURSOR cursorquery
        IS
             SELECT  a.EXE_PROC
             FROM APP_TIPO_SECUE_PEDID a
            WHERE a.IND_ACTI = '1'
   ORDER BY NUM_SECU;

  CURSOR regiones
        IS
             select 0 as zorg_oid_regi from
             (
             SELECT  a.zorg_oid_regi, max(b.num_secu) n
             FROM APP_SOLIC_CABEC_SECUE a, app_rutas_trans b
            WHERE a.cod_zona=b.cod_ruta
            group by a.zorg_oid_regi
            ) where rownum=1 order by n asc
            ;

BEGIN

    --SE OBTIEN EL OID DEL PROCESO
    lnOidPeriodo:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psPeriodoProceso);

   --Al inicio del proceso, se eliminara la información de la tabla APP_SOLIC_CABEC_SECUE
   EXECUTE IMMEDIATE 'TRUNCATE TABLE APP_SOLIC_CABEC_SECUE';

   --
           INSERT INTO APP_SOLIC_CABEC_SECUE (
                NUM_LOTE,
                CLIE_OID_CLIE,
                COD_CLIE,
                SOCA_OID_SOLI_CABE,
                PERD_OID_PERI,
                 FEC_FACT,
                VAL_NUME_SOLI,
                NUM_LOTE_FACT,
                ZZON_OID_ZONA,
               COD_ZONA,
               TERR_OID_TERR,
               COD_TERR,
               ZORG_OID_REGI
               )
             select
                 ( SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') ||
                   LPAD(NVL(MAX(SUBSTR(A.NUM_LOTE, 9, 4)) + 1, 1), 4, '0') AS NUM_LOTE
                   FROM APP_SOLIC_CABEC_SECUE_HISTO A
                   WHERE  SUBSTR(A.NUM_LOTE, 1, 8) = TO_CHAR(SYSDATE, 'YYYYMMDD')
                  ) NUM_LOTE,
                   mc.oid_clie,
                   mc.cod_clie,
                   con.oid_soli_cabe,
                   lnOidPeriodo,
                   con.fec_fact,
                   con.val_nume_soli,
                   con.num_lote_fact,
                   zon.oid_zona,
                   zon.cod_zona,
                   ter.oid_terr,
                   ter.cod_terr,
                   0--zon.zorg_oid_regi
            from ped_solic_cabec con,
                 mae_clien mc,
                 zon_zona zon,
                 zon_terri ter
            where con.terr_oid_terr = ter.oid_terr
              and con.clie_oid_clie = mc.oid_clie
              and con.zzon_oid_zona = zon.oid_zona
              and con.perd_oid_peri = lnOidPeriodo
              and con.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
              and con.num_unid_aten_tota > 0 -- Pedidos con unidades atendidas
              and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
              and exists (
                select null
                from int_lar_tipo_solici_pedido_dis l
                where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
              );

      FOR vregiones IN regiones  LOOP
      FOR vcursorquery IN cursorquery  LOOP
             plsql_block:= 'BEGIN app_pkg_proce.'|| vcursorquery.EXE_PROC ||'(:lnOidPeriodo,:psFechaFacturacion,:psRegion); END;';
             EXECUTE IMMEDIATE plsql_block USING lnOidPeriodo, psFechaFacturacion, vregiones.zorg_oid_regi;

      END LOOP;

      --EJEUCTAMOS EL PROCESO DE EJECUCION DINAMICA
      APP_PR_PROCE_SECUE_DINAM(psCodigoPais,
                               psPeriodoProceso,
                               psFechaFacturacion,
                               psUsuario,
                               vregiones.zorg_oid_regi
                               );
       --Se ejecuta los procesos de actualizacion de secuencia para lo demas pasos
       --2: ACTUALIZACION DEL NUMERO DE DOCUMENTO INTERNO DE LOS DOCUMENTOS CONTABLES
       --3: ACTUALIZACION DEL LOTE DE FACTURACION
       --4: ACTUALIZACION DE LAS SEMILLAS DE LOS DOCUMENTOS CONTABLES
       --5: ACTUALIZACION RE SECUENCIACIÓN DEL NÚMERO DE BOLETA DE DESPACHO
       --6: finalmente ACTUALIZACION EN CUENTA CORRIENTE
      END LOOP;
       PED_PR_ACTUA_SECUE_PEDID(psCodigoPais,
                               psPeriodoProceso,
                               psFechaFacturacion);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR APP_PR_PROCE_SECUE: ' ||
                              ls_sqlerrm);

END APP_PR_PROCE_SECUE;




/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION ZONA
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 psCodigoPais        :  Codigo de Pais
 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion
 psUsuario           :  Usuario

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_ZONA
 (  lnOidPeriodo   NUMBER,
    psFechaFacturacion VARCHAR2,
    psRegion NUMBER
    )
  IS
    CURSOR cursorSecue IS
         select con.oid_soli_cabe,
               tra.num_secu
        from ped_solic_cabec con,
             zon_zona zon,
             app_rutas_trans tra
        where con.zzon_oid_zona = zon.oid_zona
          and zon.cod_zona = tra.cod_ruta
          and con.perd_oid_peri = lnOidPeriodo
          and con.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
          and con.num_unid_aten_tota > 0 -- Pedidos con unidades atendidas
          and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
          --and zon.zorg_oid_regi=psRegion
          and exists (
            select null
            from int_lar_tipo_solici_pedido_dis l
            where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
          );


  TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE ;
  TYPE t_num_secu      IS TABLE OF app_rutas_trans.num_secu%TYPE ;


 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;

 v_row_count NUMBER := 0;

 v_oid_soli_cabe t_oid_soli_cabe;
 v_num_secu t_num_secu;

  BEGIN

             OPEN cursorSecue;
                 LOOP
                 -- Bulk collect data into memory table - X rows at a time
                 FETCH cursorSecue BULK COLLECT INTO
                                            v_oid_soli_cabe,
                                            v_num_secu LIMIT rows;

                 EXIT WHEN v_row_count = cursorSecue%ROWCOUNT;
                 v_row_count := cursorSecue%ROWCOUNT;

                 -- Bulk bind of data in memory table...
                     FORALL i IN 1..v_num_secu.count
                     UPDATE APP_SOLIC_CABEC_SECUE x
                     SET    X.NUM_SECU_ZONA = v_num_secu(i)
                     WHERE X.SOCA_OID_SOLI_CABE = v_oid_soli_cabe(i);


                 END LOOP;
             CLOSE cursorSecue;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR APP_PR_PROCE_SECUE_ZONA: '||ls_sqlerrm);

  END APP_PR_PROCE_SECUE_ZONA;

/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION TERRI
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_TERRI
 (  lnOidPeriodo   NUMBER,
    psFechaFacturacion VARCHAR2,
    psRegion NUMBER
    )
  IS
    CURSOR cursorSecue IS
        select con.oid_soli_cabe,
               ter.num_secu
        from ped_solic_cabec con,
             zon_zona zon,
             app_rutas_terri ter
        where con.terr_oid_terr = ter.terr_oid_terr
          and con.perd_oid_peri = lnOidPeriodo
          and con.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
          and con.num_unid_aten_tota > 0 -- Pedidos con unidades atendidas
          and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
          and con.zzon_oid_zona=zon.oid_zona
          --and zon.zorg_oid_regi=psRegion
          and exists (
            select null
            from int_lar_tipo_solici_pedido_dis l
            where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
          );

  TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE ;
  TYPE t_num_secu      IS TABLE OF app_rutas_trans.num_secu%TYPE ;


 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;

 v_row_count NUMBER := 0;

 v_oid_soli_cabe t_oid_soli_cabe;
 v_num_secu t_num_secu;

  BEGIN
             OPEN cursorSecue;
                 LOOP
                 -- Bulk collect data into memory table - X rows at a time
                 FETCH cursorSecue BULK COLLECT INTO
                                            v_oid_soli_cabe,
                                            v_num_secu LIMIT rows;

                 EXIT WHEN v_row_count = cursorSecue%ROWCOUNT;
                 v_row_count := cursorSecue%ROWCOUNT;

                 -- Bulk bind of data in memory table...
                     FORALL i IN 1..v_num_secu.count
                     UPDATE APP_SOLIC_CABEC_SECUE x
                     SET    X.NUM_SECU_TERR = v_num_secu(i)
                     WHERE X.SOCA_OID_SOLI_CABE = v_oid_soli_cabe(i);


                 END LOOP;
             CLOSE cursorSecue;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR APP_PR_PROCE_SECUE_TERRI: '||ls_sqlerrm);
  END APP_PR_PROCE_SECUE_TERRI;

/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION CLASI
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_CLASI
 (  lnOidPeriodo   NUMBER,
    psFechaFacturacion VARCHAR2,
    psRegion NUMBER
    )IS

    CURSOR cursorSecue IS
        select con.oid_soli_cabe,
               cla.num_secu
        from ped_solic_cabec con,
             zon_zona zon,
             mae_clien_clasi mcc,
             mae_clien_tipo_subti cts,
             app_rutas_clasi cla
        where con.clie_oid_clie = cts.clie_oid_clie
          and mcc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
          and mcc.clas_oid_clas = cla.clas_oid_clas
          and mcc.tccl_oid_tipo_clasi = cla.tccl_oid_tipo_clas
          and cts.ticl_oid_tipo_clie = cla.ticl_oid_tipo_clie
          and cts.sbti_oid_subt_clie = cla.sbti_oid_subt_clie
          and con.perd_oid_peri = lnOidPeriodo
          and con.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
          and con.num_unid_aten_tota > 0 -- Pedidos con unidades atendidas
          and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
          and con.zzon_oid_zona=zon.oid_zona
          --and zon.zorg_oid_regi=psRegion
          and exists (
            select null
            from int_lar_tipo_solici_pedido_dis l
            where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
          )
        order by cla.num_secu desc;


  TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE ;
  TYPE t_num_secu      IS TABLE OF app_rutas_trans.num_secu%TYPE ;


 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;

 v_row_count NUMBER := 0;

 v_oid_soli_cabe t_oid_soli_cabe;
 v_num_secu t_num_secu;

  BEGIN

             OPEN cursorSecue;
                 LOOP
                 -- Bulk collect data into memory table - X rows at a time
                 FETCH cursorSecue BULK COLLECT INTO
                                            v_oid_soli_cabe,
                                            v_num_secu LIMIT rows;

                 EXIT WHEN v_row_count = cursorSecue%ROWCOUNT;
                 v_row_count := cursorSecue%ROWCOUNT;

                 -- Bulk bind of data in memory table...
                     FORALL i IN 1..v_num_secu.count
                     UPDATE APP_SOLIC_CABEC_SECUE x
                     SET    X.NUM_SECU_CLAS_CLIE = v_num_secu(i)
                     WHERE X.SOCA_OID_SOLI_CABE = v_oid_soli_cabe(i);


                 END LOOP;
             CLOSE cursorSecue;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR APP_PR_PROCE_SECUE_CLASI: '||ls_sqlerrm);
   END APP_PR_PROCE_SECUE_CLASI;



/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION REINCIDENCIA
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:

 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_REINC_FNE
 (  lnOidPeriodo   NUMBER,
    psFechaFacturacion VARCHAR2,
    psRegion NUMBER
    )
    IS
    CURSOR cursorSecue(lnOidPeriodoInicio NUMBER, lnOidPeriodo NUMBER) IS
          select rcr.clie_oid_clie oid_clie,
                 count(1) cont
            from rec_cabec_recla rcr,
                 rec_opera_recla ror,
                 rec_linea_opera_recla lor,
                 rec_tipos_opera rto,
                 rec_opera ro
            where rcr.oid_cabe_recl = ror.care_oid_cabe_recl
              and ror.oid_oper_recl = lor.opre_oid_oper_recl
              and ror.tiop_oid_tipo_oper = rto.oid_tipo_oper
              and rto.rope_oid_oper = ro.oid_oper
              and lor.timo_oid_tipo_movi = 2 -- retornos
              and ro.cod_oper in (select cod_opera from app_opera_fne)
              and rcr.perd_oid_peri_recl >= lnOidPeriodoInicio
              and rcr.perd_oid_peri_recl <= lnOidPeriodo
              --and rcr.clie_oid_clie = oidCliente
              and exists (
                  select null
                  from rec_solic_opera rso,
                       ped_solic_cabec psc
                  where rso.soca_oid_soli_cabe = psc.oid_soli_cabe
                    and rso.tspa_oid_tipo_soli_pais = psc.tspa_oid_tipo_soli_pais
                    and psc.fec_fact is not null
                    and rso.opre_oid_oper_recl = ror.oid_oper_recl
              )
            group by rcr.clie_oid_clie;


   CURSOR cursorSecuetipoD(lnOidPeriodo NUMBER, lnNumeroPeriodos NUMBER ) IS
          select rcr.clie_oid_clie oid_clie,
                 count(1) cont
            from rec_cabec_recla rcr,
                 rec_opera_recla ror,
                 rec_linea_opera_recla lor,
                 rec_tipos_opera rto,
                 rec_opera ro
            where rcr.oid_cabe_recl = ror.care_oid_cabe_recl
              and ror.oid_oper_recl = lor.opre_oid_oper_recl
              and ror.tiop_oid_tipo_oper = rto.oid_tipo_oper
              and rto.rope_oid_oper = ro.oid_oper
              and lor.timo_oid_tipo_movi = 2 -- retornos
              and ro.cod_oper in (select cod_opera from app_opera_fne)
              and rcr.fec_ingr >= (trunc(to_date(psFechaFacturacion,'dd/MM/yyyy')) - lnNumeroPeriodos)
              --and rcr.clie_oid_clie = oidCliente
              and exists (
                  select null
                  from rec_solic_opera rso,
                       ped_solic_cabec psc
                  where rso.soca_oid_soli_cabe = psc.oid_soli_cabe
                    and rso.tspa_oid_tipo_soli_pais = psc.tspa_oid_tipo_soli_pais
                    and psc.fec_fact is not null
                    and rso.opre_oid_oper_recl = ror.oid_oper_recl
              )
            group by rcr.clie_oid_clie;

  TYPE t_oid_clie IS TABLE OF ped_solic_cabec.CLIE_OID_CLIE%TYPE ;
  TYPE t_cont     IS TABLE OF app_rutas_trans.num_secu%TYPE ;


 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;

 v_row_count NUMBER := 0;

 v_oid_clie t_oid_clie;
 v_cont t_cont;


  regParam APP_PARAM_FNE%ROWTYPE;
  lnOidPeriodoInicio NUMBER;
  lsPeriodo VARCHAR2(6);
  lnNumeroPeriodos NUMBER;
  BEGIN

        SELECT *
        INTO regParam
        FROM APP_PARAM_FNE;

        --periodo de incio

        SELECT A.COD_PERI
        INTO lsPeriodo
        FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
       WHERE A.OID_PERI = B.PERI_OID_PERI
         AND B.OID_PERI = lnOidPeriodo
         AND B.CANA_OID_CANA = C.OID_CANA
         AND B.MARC_OID_MARC = D.OID_MARC
         AND C.COD_CANA = 'VD'
         AND D.COD_MARC = 'T';

        lnNumeroPeriodos := regParam.NUM_UNID_TIEM;

        IF(regParam.IND_TIPO_CALC = 'C')THEN
          --Si el parámetro IND_TIPO_CALC es C (Campañas) debemos calcular el rango de periodos

          SELECT oid_peri --gen_pkg_gener.gen_fn_devuelve_des_perio(oid_peri)
              INTO lnOidPeriodoInicio
              FROM (SELECT rownum fila,
                           t.*
                      FROM (SELECT cra_perio.*
                              FROM cra_perio
                             WHERE fec_fina <
                                   (SELECT p.fec_fina
                                      FROM cra_perio p
                                     WHERE p.val_nomb_peri LIKE '%' || lsPeriodo || '%')
                             ORDER BY fec_fina DESC) t) tt
             WHERE tt.fila = lnNumeroPeriodos;


              OPEN cursorSecue(lnOidPeriodoInicio,lnOidPeriodo);
                 LOOP

                  FETCH cursorSecue BULK COLLECT INTO
                                            v_oid_clie,
                                            v_cont LIMIT rows;

                    IF v_oid_clie.COUNT > 0 THEN

                        FOR x IN v_oid_clie.FIRST .. v_oid_clie.LAST LOOP

                          IF(v_cont(x) > regParam.NUM_MINI_FNE ) THEN

                             UPDATE APP_SOLIC_CABEC_SECUE x
                             SET    X.NUM_SECU_FNE = 1
                             WHERE X.CLIE_OID_CLIE = v_oid_clie(x)
                             and x.zorg_oid_regi=psRegion
                             ;

                          END IF;


                        END LOOP;
                    END IF;

                 EXIT WHEN cursorSecue%NOTFOUND;
                 END LOOP;
              CLOSE cursorSecue;

        ELSE --SI PARAMETRO ES D

              OPEN cursorSecuetipoD(lnOidPeriodo,lnNumeroPeriodos);
                 LOOP

                  FETCH cursorSecuetipoD BULK COLLECT INTO
                                            v_oid_clie,
                                            v_cont LIMIT rows;

                    IF v_oid_clie.COUNT > 0 THEN

                        FOR x IN v_oid_clie.FIRST .. v_oid_clie.LAST LOOP

                          IF(v_cont(x) > regParam.NUM_MINI_FNE ) THEN

                             UPDATE APP_SOLIC_CABEC_SECUE x
                             SET    X.NUM_SECU_FNE = 1
                             WHERE X.CLIE_OID_CLIE = v_oid_clie(x);

                          END IF;


                        END LOOP;
                    END IF;

                 EXIT WHEN cursorSecuetipoD%NOTFOUND;
                 END LOOP;
              CLOSE cursorSecuetipoD;




        END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR APP_PR_PROCE_SECUE_REINC_FNE: '||ls_sqlerrm);
    END APP_PR_PROCE_SECUE_REINC_FNE;

/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION ESTATUS
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:

 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_ESTAT
 (  lnOidPeriodo   NUMBER,
    psFechaFacturacion VARCHAR2,
    psRegion NUMBER
    )IS

    CURSOR cursorSecue IS
        select con.oid_soli_cabe,
               est.num_secu
        from ped_solic_cabec con,
             zon_zona zon,
             mae_clien_datos_adici cda ,
             app_rutas_estat_clien est
        where con.clie_oid_clie = cda.clie_oid_clie
          and cda.esta_oid_esta_clie = est.esta_oid_esta_clie
          and con.perd_oid_peri = lnOidPeriodo
          and con.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
          and con.num_unid_aten_tota > 0 -- Pedidos con unidades atendidas
          and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
          and con.zzon_oid_zona=zon.oid_zona
          --and zon.zorg_oid_regi=psRegion
          and exists (
            select null
            from int_lar_tipo_solici_pedido_dis l
            where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
          )
        order by est.num_secu;



  TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE ;
  TYPE t_num_secu      IS TABLE OF app_rutas_trans.num_secu%TYPE ;


 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;

 v_row_count NUMBER := 0;

 v_oid_soli_cabe t_oid_soli_cabe;
 v_num_secu t_num_secu;

  BEGIN

             OPEN cursorSecue;
                 LOOP
                 -- Bulk collect data into memory table - X rows at a time
                 FETCH cursorSecue BULK COLLECT INTO
                                            v_oid_soli_cabe,
                                            v_num_secu LIMIT rows;

                 EXIT WHEN v_row_count = cursorSecue%ROWCOUNT;
                 v_row_count := cursorSecue%ROWCOUNT;

                 -- Bulk bind of data in memory table...
                     FORALL i IN 1..v_num_secu.count
                     UPDATE APP_SOLIC_CABEC_SECUE x
                     SET    X.NUM_SECU_ESTA_CLIE = v_num_secu(i)
                     WHERE X.SOCA_OID_SOLI_CABE = v_oid_soli_cabe(i);


                 END LOOP;
             CLOSE cursorSecue;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR APP_PR_PROCE_SECUE_ESTAT: '||ls_sqlerrm);

  END APP_PR_PROCE_SECUE_ESTAT;

/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION NIVL RIESGO
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:

 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion

***************************************************************************/
PROCEDURE APP_PR_PROCE_NIVEL_RIESG
 (  lnOidPeriodo   NUMBER,
    psFechaFacturacion VARCHAR2,
    psRegion NUMBER
    )IS

    CURSOR cursorSecue IS
        select con.oid_soli_cabe,
               niv.num_secu
        from ped_solic_cabec con,
             zon_zona zon,
             mae_clien_datos_adici cda ,
             app_rutas_nivel_riesg niv
        where con.clie_oid_clie = cda.clie_oid_clie
          and cda.niri_oid_nive_ries = niv.niri_oid_nive_ries
          and con.perd_oid_peri = lnOidPeriodo
          and con.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
          and con.num_unid_aten_tota > 0 -- Pedidos con unidades atendidas
          and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
          and con.zzon_oid_zona=zon.oid_zona
          --and zon.zorg_oid_regi=psRegion
          and exists (
            select null
            from int_lar_tipo_solici_pedido_dis l
            where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
          )
        order by niv.num_secu;



  TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE ;
  TYPE t_num_secu      IS TABLE OF app_rutas_trans.num_secu%TYPE ;


 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;

 v_row_count NUMBER := 0;

 v_oid_soli_cabe t_oid_soli_cabe;
 v_num_secu t_num_secu;

  BEGIN

             OPEN cursorSecue;
                 LOOP
                 -- Bulk collect data into memory table - X rows at a time
                 FETCH cursorSecue BULK COLLECT INTO
                                            v_oid_soli_cabe,
                                            v_num_secu LIMIT rows;

                 EXIT WHEN v_row_count = cursorSecue%ROWCOUNT;
                 v_row_count := cursorSecue%ROWCOUNT;

                 -- Bulk bind of data in memory table...
                     FORALL i IN 1..v_num_secu.count
                     UPDATE APP_SOLIC_CABEC_SECUE x
                     SET    X.NUM_SECU_NIVE_RIES = v_num_secu(i)
                     WHERE X.SOCA_OID_SOLI_CABE = v_oid_soli_cabe(i);


                 END LOOP;
             CLOSE cursorSecue;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR APP_PR_PROCE_NIVEL_RIESG: '||ls_sqlerrm);

END APP_PR_PROCE_NIVEL_RIESG;



/***************************************************************************
Descripcion : PROCESO DE SECUNCIACION
Fecha Creacion : 27/09/2010
Autor : Sergio Buchelli
Parametros:
 psCodigoPais        :  Codigo de Pais
 psPeriodoProceso    :   Peridodo Proceso
 psFechaFacturacion  :  Fecha Facturacion
 psUsuario           :  Usuario

***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_DINAM
 (psCodigoPais       VARCHAR2,
  psPeriodoProceso   VARCHAR2,
  psFechaFacturacion VARCHAR2,
  psUsuario          VARCHAR2,
  psRegion           NUMBER
  ) IS

  lnOidPeriodo NUMBER;
  plsql_block VARCHAR2(4000);
  valorSecuencia NUMBER;
  CURSOR cursorquery
        IS
             SELECT  A.COD_TIPO_SECU_PEDI,
                     A.NUM_SECU,
                     A.EXE_PROC,
                     A.IND_ORDE_EXCE,
                     A.NOM_COLU_ORDE,
                     A.IND_ORDE_ZONA
             FROM APP_TIPO_SECUE_PEDID a
            WHERE a.IND_ACTI = '1' and a.zorg_oid_regi=case when exists (select * from app_tipo_secue_pedid where zorg_oid_regi=psRegion) then psRegion else 0 end
   ORDER BY NUM_SECU;


 CURSOR cursorUPDPedidosSecuencia(oidPeriodo NUMBER)
   IS
     select consolidados.soca_oid_soli_cabe,
            consolidados.zzon_oid_zona,
            consolidados.num_secu_zona_ruta,
            consolidados.val_secu_ruta_terr,
            (nvl(histo.num_secu_inic, 0) + consolidados.num_secu_fact_diar) num_secu_fact_diar
        from (
            -- Subquery que obtiene los numeros de secuencia iniciales de
            -- lotes anteriores pero para la misma fecha y periodo
            select scsh.zzon_oid_zona, max(scsh.num_secu_fact_diar) num_secu_inic
            from app_solic_cabec_secue_histo scsh
            where scsh.perd_oid_peri = oidPeriodo
              and scsh.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
              and not exists (
                  select null
                  from app_solic_cabec_secue x
                  where x.soca_oid_soli_cabe = scsh.soca_oid_soli_cabe
              )
            group by scsh.zzon_oid_zona
            ) histo,
            (
            select scs.soca_oid_soli_cabe,
                   scs.cod_clie,
                   scs.zzon_oid_zona,
                   scs.num_secu_zona_ruta,
                   scs.val_secu_ruta_terr,
                   scs.num_secu_fact_diar
            from app_solic_cabec_secue scs
            order by scs.num_secu_zona_ruta,
                     scs.num_secu_fact_diar,
                     scs.cod_clie
            ) consolidados
        where histo.zzon_oid_zona (+) = consolidados.zzon_oid_zona
        order by consolidados.num_secu_zona_ruta,
                 consolidados.num_secu_fact_diar,
                 consolidados.cod_clie;

  TYPE t_soca_oid_soli_cabe IS TABLE OF app_solic_cabec_secue.soca_oid_soli_cabe%TYPE ;
  TYPE t_zzon_oid_zona      IS TABLE OF app_solic_cabec_secue.zzon_oid_zona%TYPE ;
  TYPE t_num_secu_zona_ruta IS TABLE OF app_solic_cabec_secue.num_secu_zona_ruta%TYPE ;
  TYPE t_val_secu_ruta_terr IS TABLE OF app_solic_cabec_secue.val_secu_ruta_terr%TYPE ;
  TYPE t_num_secu_fact_diar IS TABLE OF app_solic_cabec_secue.num_secu_fact_diar%TYPE ;


 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;

 v_row_count NUMBER := 0;
 v_soca_oid_soli_cabe t_soca_oid_soli_cabe;
 v_zzon_oid_zona      t_zzon_oid_zona;
 v_num_secu_zona_ruta t_num_secu_zona_ruta;
 v_val_secu_ruta_terr t_val_secu_ruta_terr;
 v_num_secu_fact_diar t_num_secu_fact_diar;

BEGIN
    --SE OBTIEN EL OID DEL PROCESO
      lnOidPeriodo:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psPeriodoProceso);


      FOR vcursorquery IN cursorquery  LOOP
         --actualizando los campos que quedaron nullos
         IF( vcursorquery.IND_ORDE_EXCE = 'I' ) THEN
            valorSecuencia:=0;
         ELSE
            valorSecuencia:=999999;
         END IF;


         IF(vcursorquery.NOM_COLU_ORDE IS NOT NULL )THEN

             plsql_block:= ' UPDATE  APP_SOLIC_CABEC_SECUE  SET '|| vcursorquery.NOM_COLU_ORDE  ||' = :1'||
                           ' WHERE ' || vcursorquery.NOM_COLU_ORDE  ||' IS NULL';

             EXECUTE IMMEDIATE plsql_block USING valorSecuencia;
         END IF;

      END LOOP;


    --ACTUALIZACION DE SECUANCIA EN RELACION A ZONA

     APP_PR_ACTUA_SECUE_ZONA(psRegion);

    --ACTUALIZACION DE SECUANCIA  NO TOMANDO CMO CRITERIO LA ZONA

    APP_PR_ACTUA_SECUE_NO_ZONA(psRegion);


    --Hasta este punto el proceso ya tiene calculados los campos que han de ser utilizados
    --para actualizar la tabla PED_SOLIC_CABEC_SECUE
        OPEN cursorUPDPedidosSecuencia(lnOidPeriodo);
                 LOOP
                 -- Bulk collect data into memory table - X rows at a time
                 FETCH cursorUPDPedidosSecuencia BULK COLLECT INTO
                                             v_soca_oid_soli_cabe,
                                             v_zzon_oid_zona,
                                             v_num_secu_zona_ruta,
                                             v_val_secu_ruta_terr,
                                             v_num_secu_fact_diar
                                             LIMIT rows;

                 EXIT WHEN v_row_count = cursorUPDPedidosSecuencia%ROWCOUNT;
                 v_row_count := cursorUPDPedidosSecuencia%ROWCOUNT;

                 -- Bulk bind of data in memory table...
                     FORALL i IN 1..v_soca_oid_soli_cabe.count
                     UPDATE PED_SOLIC_CABEC_SECUE sec
                     SET sec.val_secu_ruta_terr = v_val_secu_ruta_terr(i),
                         sec.num_secu_fact_diar = v_num_secu_fact_diar(i),
                         sec.num_secu_zona_ruta = v_num_secu_zona_ruta(i)
                     WHERE sec.soca_oid_soli_cabe = v_soca_oid_soli_cabe(i);


                 END LOOP;
        CLOSE cursorUPDPedidosSecuencia;

    --Finalmente pasamos los datos de la tabla APP_SOLIC_CABEC_SECUE a la tabla histórica APP_SOLIC_CABEC_SECUE_HISTO
           INSERT INTO app_solic_cabec_secue_histo
            (num_lote, clie_oid_clie, cod_clie, soca_oid_soli_cabe,
             perd_oid_peri, fec_fact, val_nume_soli, num_lote_fact,
             zzon_oid_zona, cod_zona, terr_oid_terr, cod_terr, num_secu_zona,
             num_secu_terr, num_secu_clas_clie, num_secu_fne,
             num_secu_esta_clie, num_secu_nive_ries, num_secu_zona_ruta,
             val_secu_ruta_terr, num_secu_fact_diar)
               SELECT
                      NUM_LOTE,
                      clie_oid_clie, cod_clie, soca_oid_soli_cabe,
                      perd_oid_peri, fec_fact, val_nume_soli, num_lote_fact,
                      zzon_oid_zona, cod_zona, terr_oid_terr, cod_terr, num_secu_zona,
                      num_secu_terr, num_secu_clas_clie, num_secu_fne, num_secu_esta_clie,
                      num_secu_nive_ries, num_secu_zona_ruta, val_secu_ruta_terr,
                      num_secu_fact_diar
                 FROM app_solic_cabec_secue
                 where zorg_oid_regi=psRegion
                 ;


  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR APP_PR_PROCE_SECUE_DINAM: ' ||
                              ls_sqlerrm);

END APP_PR_PROCE_SECUE_DINAM;



/***************************************************************************
Descripcion : PROCESO DE ACTUALIZACION DE SECUENCIA EN RELACION ALA ZONA
Fecha Creacion : 29/09/2010
Autor : Sergio Buchelli
Parametros:
***************************************************************************/
PROCEDURE APP_PR_ACTUA_SECUE_ZONA(psRegion NUMBER)
IS
  CURSOR cursorquery
        IS
            select tsp.num_secu,
                   tsp.nom_colu_orde
            from app_tipo_secue_pedid tsp
            where num_secu <= (select x.num_secu
                               from app_tipo_secue_pedid x
                               where x.ind_orde_zona = '1'
                               and x.zorg_oid_regi=case when exists (select * from app_tipo_secue_pedid where zorg_oid_regi=psRegion) then psRegion else 0 end
                               )
              and tsp.ind_acti = '1'
              and tsp.zorg_oid_regi=case when exists (select * from app_tipo_secue_pedid where zorg_oid_regi=psRegion) then psRegion else 0 end
            order by tsp.num_secu;

  lnOidPeriodo NUMBER;
  plsql_block VARCHAR2(4000);
  nombreColumna  VARCHAR2(200);

   TYPE tcursorDinamico IS REF CURSOR;
   vcursorDinamico         tcursorDinamico;

  TYPE t_soca_oid_soli_cabe IS TABLE OF app_solic_cabec_secue.soca_oid_soli_cabe%TYPE ;
  TYPE t_num_secu_zona_ruta IS TABLE OF app_solic_cabec_secue.num_secu_zona_ruta%TYPE ;


 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;

 v_row_count NUMBER := 0;
 v_row_max NUMBER := 0;
 v_soca_oid_soli_cabe t_soca_oid_soli_cabe;
 v_num_secu_zona_ruta t_num_secu_zona_ruta;


BEGIN
nombreColumna:='';

       FOR vcursorquery IN cursorquery  LOOP
         --obteniendo losnombres de la columna
        IF(vcursorquery.nom_colu_orde IS NOT NULL) THEN
          nombreColumna:=nombreColumna || vcursorquery.nom_colu_orde|| ',';
         END IF;

      END LOOP;
       --QUITANDO LA ULTIMA ',' SI nombreColuma es no null
      IF(nombreColumna IS NOT NULL) THEN
           nombreColumna:=substr(nombreColumna,1,length(nombreColumna)-1);

           plsql_block:= ' select soca_oid_soli_cabe, '||
                         ' dense_rank() over (partition by num_lote order by  ' || nombreColumna || ') num_secu_zona_ruta ' ||
                         ' from app_solic_cabec_secue where zorg_oid_regi=' || psRegion;


          select max(num_secu_zona_ruta) into v_row_max from app_solic_cabec_secue;

          OPEN vcursorDinamico FOR plsql_block;
           LOOP
                FETCH vcursorDinamico BULK COLLECT INTO
                                                 v_soca_oid_soli_cabe,
                                                 v_num_secu_zona_ruta
                                                 LIMIT rows;

                     EXIT WHEN v_row_count = vcursorDinamico%ROWCOUNT;
                     v_row_count := vcursorDinamico%ROWCOUNT;

                     -- Bulk bind of data in memory table...
                         FORALL i IN 1..v_soca_oid_soli_cabe.count
                         UPDATE APP_SOLIC_CABEC_SECUE sec
                         SET   sec.num_secu_zona_ruta = v_num_secu_zona_ruta(i)+nvl(v_row_max,0)
                         WHERE sec.soca_oid_soli_cabe = v_soca_oid_soli_cabe(i);


           END LOOP;
          CLOSE vcursorDinamico;
       END IF;--FIN DEL NOMBRE COLUMNA

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR APP_PR_ACTUA_SECUE_ZONA: ' ||
                              ls_sqlerrm);
END APP_PR_ACTUA_SECUE_ZONA;


/***************************************************************************
Descripcion : PROCESO DE ACTUALIZACION DE SECUENCIA DEBAJO EN RELACION ALA ZONA
Fecha Creacion : 29/09/2010
Autor : Sergio Buchelli
Parametros:
***************************************************************************/
PROCEDURE APP_PR_ACTUA_SECUE_NO_ZONA(psRegion NUMBER) IS
  CURSOR cursorquery
      IS
       select tsp.num_secu,
              tsp.nom_colu_orde
        from app_tipo_secue_pedid tsp
        where tsp.ind_acti = '1'
        and tsp.zorg_oid_regi=case when exists (select * from app_tipo_secue_pedid where zorg_oid_regi=psRegion) then psRegion else 0 end
         order by tsp.num_secu;


  lnOidPeriodo NUMBER;
  plsql_block VARCHAR2(4000);
  line1 VARCHAR2(500);

  nombreColumna  VARCHAR2(200);

   TYPE tcursorDinamico IS REF CURSOR;
   vcursorDinamico         tcursorDinamico;



  TYPE t_soca_oid_soli_cabe IS TABLE OF app_solic_cabec_secue.soca_oid_soli_cabe%TYPE ;
  TYPE t_num_secu_zona_ruta IS TABLE OF app_solic_cabec_secue.num_secu_zona_ruta%TYPE ;
  TYPE t_val_secu_ruta_terr IS TABLE OF app_solic_cabec_secue.val_secu_ruta_terr%TYPE ;
  TYPE t_num_secu_fact_diar IS TABLE OF app_solic_cabec_secue.num_secu_fact_diar%TYPE ;


 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;

 v_row_count NUMBER := 0;
 v_soca_oid_soli_cabe t_soca_oid_soli_cabe;
 v_num_secu_zona_ruta t_num_secu_zona_ruta;
 v_val_secu_ruta_terr t_val_secu_ruta_terr;
 v_num_secu_fact_diar t_num_secu_fact_diar;

  lsFormato VARCHAR2(10);
BEGIN
nombreColumna:='';

       FOR vcursorquery IN cursorquery  LOOP
         --obteniendo losnombres de la columna
        IF(vcursorquery.nom_colu_orde IS NOT NULL) THEN
          nombreColumna:=nombreColumna || vcursorquery.nom_colu_orde|| ',';
        END IF;

      END LOOP;
       --QUITANDO LA ULTIMA ',' SI ES NOT NULL

     IF(nombreColumna IS NOT NULL) THEN
           nombreColumna:=substr(nombreColumna,1,length(nombreColumna)-1);

           lsFormato:='''000000''';

           plsql_block:= ' select soca_oid_soli_cabe, '||
                         ' num_secu_zona_ruta, '||
                         ' trim(to_char(num_secu_zona_ruta, '|| lsFormato  ||')) || ' ||
                         ' trim(to_char(num_secu_fact_diar, '|| lsFormato  ||')) val_secu_ruta_terr, '||
                         ' num_secu_fact_diar '||
                         ' from ( '||
                         '     select soca_oid_soli_cabe, ' ||
                         '            num_secu_zona_ruta, ' ||
                         '   row_number() over (partition by num_secu_zona order by   ' || nombreColumna || ',cod_clie) num_secu_fact_diar ' ||
                         ' from app_solic_cabec_secue where zorg_oid_regi='|| psRegion ||
                         ' ) '||
                         ' order by num_secu_zona_ruta, '||
                         '          num_secu_fact_diar ';

          -- DBMS_OUTPUT.PUT_LINE('plsql_block    ' || plsql_block);
          OPEN vcursorDinamico FOR plsql_block;
           LOOP
                FETCH vcursorDinamico BULK COLLECT INTO
                                                 v_soca_oid_soli_cabe,
                                                 v_num_secu_zona_ruta,
                                                 v_val_secu_ruta_terr,
                                                 v_num_secu_fact_diar
                                                 LIMIT rows;

                     EXIT WHEN v_row_count = vcursorDinamico%ROWCOUNT;
                     v_row_count := vcursorDinamico%ROWCOUNT;

                     -- Bulk bind of data in memory table...
                         FORALL i IN 1..v_soca_oid_soli_cabe.count
                          UPDATE APP_SOLIC_CABEC_SECUE sec
                          SET   sec.val_secu_ruta_terr =  v_val_secu_ruta_terr(i),
                                sec.num_secu_fact_diar =v_num_secu_fact_diar(i)
                         WHERE sec.soca_oid_soli_cabe = v_soca_oid_soli_cabe(i);


           END LOOP;
          CLOSE vcursorDinamico;
      END IF;--FIN DE NOMBRE COLUMNA

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR APP_PR_ACTUA_SECUE_NO_ZONA: ' ||
                              ls_sqlerrm);
END APP_PR_ACTUA_SECUE_NO_ZONA;




/*******************************************************************************
  Descripcion         : Recalcula la secuencia de los pedidos que aun no han sido
                        enviados a despacho de tal manera que permita la ejecucion
                        de mas de un lote de GP4 a GP5, esta actualizacion incluye
                        ademas la regeneracion de los documentos internos y la
                        actualizacion del numero de lote de facturacion al valor
                        maximo. Este proceso ha sido copiado del paquete
                        PED_PKG_PROCE.PED_PR_ACTUA_SECUE_PEDID para que este nuevo paquete
                        se encarge completamente de la secunciacion de pedidos
  Fecha Creacion      : 07/10/2010
  Parametros Entrada:
      p_codigoPais       : Codigo del pais a procesar
      p_codigoPeriodo    : Codigo de periodo
      p_fechaFacturacion : Fecha de Facturacion
  Autor               : Sergio Buchelli Silva
***************************************************************************/
PROCEDURE PED_PR_ACTUA_SECUE_PEDID(p_codigoPais IN VARCHAR2,
                                   p_codigoPeriodo IN VARCHAR2,
                                   p_fechaFacturacion IN VARCHAR2) IS

    TYPE t_oid_clie             IS TABLE OF mae_clien.oid_clie%TYPE;
    TYPE t_cod_clie             IS TABLE OF mae_clien.cod_clie%TYPE;
    TYPE t_cod_zona             IS TABLE OF zon_zona.cod_zona%TYPE;
    TYPE t_oid_soli_cabe        IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
    TYPE t_num_secu_ruta_tran   IS TABLE OF app_rutas_trans.num_secu%TYPE;
    TYPE t_num_secu_ruta_terr   IS TABLE OF app_rutas_terri.num_secu%TYPE;
    TYPE t_num_secu_ruta_clie   IS TABLE OF app_rutas_clien.val_nume_secu%TYPE;
    TYPE t_val_secu_ruta_terr   IS TABLE OF ped_solic_cabec_secue.val_secu_ruta_terr%TYPE;
    TYPE t_num_secu             IS TABLE OF ped_solic_cabec_secue.num_secu_fact_diar%TYPE;

    v_oid_clie              t_oid_clie;
    v_cod_clie              t_cod_clie;
    v_cod_zona              t_cod_zona;
    v_oid_soli_cabe         t_oid_soli_cabe;
    v_num_secu_ruta_tran    t_num_secu_ruta_tran;
    v_num_secu_ruta_terr    t_num_secu_ruta_terr;
    v_num_secu_ruta_clie    t_num_secu_ruta_clie;
    v_val_secu_ruta_terr    t_val_secu_ruta_terr;
    v_num_secu              t_num_secu;

    -- A diferencia de los cursores anteriores, no consideramos la condicion
    -- NUM_UNID_ATEN_TOTA > 0 ya que a nivel de documentos contables, sí se
    -- llegan a generar registros en FAC_DOCUM_CONTA_CABEC a pesar que el
    -- consolidado no tenga unidades atendidas
    CURSOR c_documentos(p_oidPeriodo NUMBER) IS
    select documentos.oid_cabe,
           documentos.oid_regi,
           documentos.val_ejer_docu_inte,
           (tipos.num_docu_cont_inte_inic + documentos.num_secu - 1) num_docu_cont_inte_calc
    from (
        -- select que obtiene los numeros de documento iniciales
        select doc.tido_oid_tipo_docu,
               min(doc.num_docu_cont_inte) num_docu_cont_inte_inic
        from ped_solic_cabec psc,
             fac_docum_conta_cabec doc
        where psc.oid_soli_cabe = doc.soca_oid_soli_cabe
          and psc.perd_oid_peri = p_oidPeriodo
          and psc.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
          and nvl(psc.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
          and exists (
            select null
            from int_lar_tipo_solici_pedido_dis lar
            where lar.tspa_oid_tipo_soli_pais = psc.tspa_oid_tipo_soli_pais
          )
        group by doc.tido_oid_tipo_docu
        ) tipos,
        (
        -- select que obtiene los datos de los documentos matriciales y su secuencia
        select con.oid_soli_cabe,
               con.clie_oid_clie,
               con.fec_fact,
               con.num_lote_fact,
               row_number() over (partition by cab.tido_oid_tipo_docu order by sec.val_secu_ruta_terr, mc.cod_clie, cab.oid_cabe) num_secu,
               cab.oid_cabe,
               cab.tido_oid_tipo_docu,
               cab.num_docu_cont_inte,
               nvl(cab.val_ejer_docu_inte, to_char(cab.fec_fact, 'yy')) val_ejer_docu_inte,
               ven.oid_regi
        from ped_solic_cabec con,
             mae_clien mc,
             ped_solic_cabec_secue sec,
             fac_docum_conta_cabec cab,
             fac_regis_unico_venta ven
        where con.oid_soli_cabe = sec.soca_oid_soli_cabe
          and con.clie_oid_clie = mc.oid_clie
          and con.perd_oid_peri = p_oidPeriodo
          and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
          and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
          and exists (
            select null
            from int_lar_tipo_solici_pedido_dis l
            where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
          )
          and cab.soca_oid_soli_cabe = con.oid_soli_cabe
          and cab.oid_cabe = ven.dcca_oid_cabe
        order by cab.tido_oid_tipo_docu,
                 sec.val_secu_ruta_terr,
                 mc.cod_clie,
                 cab.oid_cabe
        ) documentos
    where documentos.tido_oid_tipo_docu = tipos.tido_oid_tipo_docu;

    CURSOR c_numerosInternos(p_oidPeriodo NUMBER) IS
    select cab.tido_oid_tipo_docu,
           cab.sbac_oid_sbac,
           max(cab.num_docu_cont_inte) val_ulti_nume_docu_cont_inte
    from ped_solic_cabec con,
         ped_solic_cabec_secue sec,
         fac_docum_conta_cabec cab
    where con.oid_soli_cabe = sec.soca_oid_soli_cabe
      and con.perd_oid_peri = p_oidPeriodo
      and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
      and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
      and exists (
        select null
        from int_lar_tipo_solici_pedido_dis l
        where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
      )
      and cab.soca_oid_soli_cabe = con.oid_soli_cabe
    group by cab.tido_oid_tipo_docu,
             cab.sbac_oid_sbac
    order by cab.tido_oid_tipo_docu,
             cab.sbac_oid_sbac;


    TYPE t_oid_cabe             IS TABLE OF fac_docum_conta_cabec.oid_cabe%TYPE;
    TYPE t_oid_regi             IS TABLE OF fac_regis_unico_venta.oid_regi%TYPE;
    TYPE t_val_ejer_docu_inte   IS TABLE OF fac_docum_conta_cabec.val_ejer_docu_inte%TYPE;
    TYPE t_num_docu_cont_inte   IS TABLE OF fac_docum_conta_cabec.num_docu_cont_inte%TYPE;
    TYPE t_tido_oid_tipo_docu   IS TABLE OF fac_docum_conta_cabec.tido_oid_tipo_docu%TYPE;
    TYPE t_sbac_oid_sbac        IS TABLE OF fac_docum_conta_cabec.sbac_oid_sbac%TYPE;

    v_oid_cabe              t_oid_cabe;
    v_oid_regi              t_oid_regi;
    v_val_ejer_docu_inte    t_val_ejer_docu_inte;
    v_num_docu_cont_inte    t_num_docu_cont_inte;
    v_tido_oid_tipo_docu    t_tido_oid_tipo_docu;
    v_sbac_oid_sbac         t_sbac_oid_sbac;


    rows NATURAL        := 500;   -- Numero de filas a procesar a la vez
    i    BINARY_INTEGER := 0;

    l_oidPais               NUMBER;
    l_oidPeriodo            NUMBER;
    l_tipoSecuenciacion     seg_pais.ind_secu%TYPE;
    l_numeroLoteFacturacion ped_solic_cabec.num_lote_fact%TYPE;

    l_oid_docu_suba             fac_docum_subac.oid_docu_suba%TYPE;
    l_val_seri_docu_lega        fac_docum_subac.val_seri_docu_lega%TYPE;
    l_val_ulti_nume_docu_inte   fac_docum_subac.val_ulti_nume_docu_inte%TYPE;


    CURSOR c_rboletadespacho(lnNumeroSoliIni NUMBER, lnOidPeriodo NUMBER) IS
        select rownum val_nume_soli_ficti,
               oid_soli_cabe,
               lnNumeroSoliIni + rownum - 1 val_nume_soli
        from (
            select con.oid_soli_cabe,
                   con.clie_oid_clie,
                   con.fec_fact,
                   con.num_lote_fact
            from ped_solic_cabec con,
                 mae_clien mc,
                 ped_solic_cabec_secue sec
            where con.oid_soli_cabe = sec.soca_oid_soli_cabe
              and con.clie_oid_clie = mc.oid_clie
             -- and con.perd_oid_peri = lnOidPeriodo
              and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
              and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
              and exists (
                select null
                from int_lar_tipo_solici_pedido_dis l
                where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
              )
            order by sec.val_secu_ruta_terr,
                     mc.cod_clie
        );

   lnNumeroSoliIni NUMBER;

   TYPE t_val_nume_soli  IS TABLE OF ped_solic_cabec.val_nume_soli%TYPE;
   TYPE t_val_nume_soli_ficti  IS TABLE OF ped_solic_cabec.val_nume_soli%TYPE;

   v_val_nume_soli t_val_nume_soli;
   v_val_nume_soli_ficti t_val_nume_soli_ficti;


 CURSOR c_ctacorriente(lnOidPeriodo NUMBER) IS
        select rownum val_nume_soli_ficti,
               oid_soli_cabe,
               to_char(trunc(val_nume_soli/100000000)) val_ejer_cuot,
               to_number(substr(to_char(val_nume_soli), -8, 8)) num_iden_cuot
        from ped_solic_cabec con
        where --con.perd_oid_peri =  lnOidPeriodo
         con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
        and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
        and exists (
            select null
            from int_lar_tipo_solici_pedido_dis l
            where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
        )
        and con.val_tota_paga_loca > 0;

    TYPE t_val_ejer_cuot  IS TABLE OF ccc_movim_cuent_corri.val_ejer_cuot%TYPE;
    TYPE t_num_iden_cuot  IS TABLE OF ccc_movim_cuent_corri.num_iden_cuot%TYPE;

     v_val_ejer_cuot t_val_ejer_cuot;
     v_num_iden_cuot t_num_iden_cuot;

     lnpos NUMBER :=1;
BEGIN

    -- Obtenemos el OID del pais y del periodo
    l_oidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(p_codigoPeriodo);

    -- Obtenemos el tipo de secuenciacion
    select nvl(ind_secu, 'T')
    into l_tipoSecuenciacion
    from seg_pais
    where cod_pais = p_codigoPais;

    -- 2 - ACTUALIZACION DEL NUMERO DE DOCUMENTO INTERNO DE LOS DOCUMENTOS CONTABLES
    -- Actualizamos los numeros de codigo interno de los documentos
    -- matriciales en base a la secuencia actualizada
    OPEN c_documentos(l_oidPeriodo);
    LOOP
        FETCH c_documentos
        BULK COLLECT INTO v_oid_cabe,
                          v_oid_regi,
                          v_val_ejer_docu_inte,
                          v_num_docu_cont_inte  LIMIT rows;
        EXIT WHEN v_oid_cabe.count = 0;

        -- Actualizamos el documento interno en FAC_DOCUM_CONTA_CABEC
        FORALL j IN 1..v_oid_cabe.count
        UPDATE fac_docum_conta_cabec cab
           SET cab.num_docu_cont_inte = v_num_docu_cont_inte(j),
               cab.val_ejer_docu_inte = v_val_ejer_docu_inte(j)
         WHERE cab.oid_cabe = v_oid_cabe(j);

        -- Actualizamos el documento interno en FAC_REGIS_UNICO_VENTA
        FORALL k IN 1..v_oid_regi.count
        UPDATE fac_regis_unico_venta ven
           SET ven.num_docu_cont_inte = v_num_docu_cont_inte(k),
               ven.val_ejer_docu_inte = v_val_ejer_docu_inte(k)
         WHERE ven.oid_regi = v_oid_regi(k);

    END LOOP;
    CLOSE c_documentos;

    -- 3 - ACTUALIZACION DEL LOTE DE FACTURACION
    -- Primero obtenemos el numero de lote mayor de aquellos consolidados
    -- que aun no hayan sido enviados a ser despachados (indicador LAR en 0)
    BEGIN

        select max(con.num_lote_fact)
        into l_numeroLoteFacturacion
        from ped_solic_cabec con
        where con.perd_oid_peri = l_oidPeriodo
          and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
          and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
          and con.num_lote_fact is not null
          and exists (
            select null
            from int_lar_tipo_solici_pedido_dis l
            where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
          );

    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        l_numeroLoteFacturacion := NULL;
    END;

    -- Hacemos la actualizacion correspondiente
    IF l_numeroLoteFacturacion IS NOT NULL THEN
        update ped_solic_cabec con
        set con.num_lote_fact = l_numeroLoteFacturacion
        where con.perd_oid_peri = l_oidPeriodo
          and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
          and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
          and con.num_lote_fact is not null
          and exists (
            select null
            from int_lar_tipo_solici_pedido_dis l
            where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
          );
    END IF;


    -- 4 - ACTUALIZACION DE LAS SEMILLAS DE LOS DOCUMENTOS CONTABLES
    OPEN c_numerosInternos(l_oidPeriodo);
    LOOP
        FETCH c_numerosInternos
        BULK COLLECT INTO v_tido_oid_tipo_docu,
                          v_sbac_oid_sbac,
                          v_num_docu_cont_inte  LIMIT rows;
        EXIT WHEN v_tido_oid_tipo_docu.count = 0;

        FOR i IN 1..v_tido_oid_tipo_docu.count LOOP

            -- Obtenemos el valor actual de la semilla en base al tipo de documento
            select fds.oid_docu_suba,
                   fds.val_seri_docu_lega,
                   fds.val_ulti_nume_docu_inte
            into l_oid_docu_suba,
                 l_val_seri_docu_lega,
                 l_val_ulti_nume_docu_inte
            from fac_docum_subac fds
            where fds.sbac_oid_sbac = v_sbac_oid_sbac(i)
            and fds.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
            and fds.pais_oid_pais = l_oidPais
            and fds.val_ulti_ejer_docu_inte = (select max(x.val_ulti_ejer_docu_inte)
                                               from fac_docum_subac x
                                               where x.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
                                                 and x.sbac_oid_sbac = fds.sbac_oid_sbac
                                              );

            -- Si el valor de la semilla es diferente al ultimo numero
            -- de documento interno hacemos la actualizacion correspondiente
            IF l_val_ulti_nume_docu_inte != v_num_docu_cont_inte(i) THEN

                update fac_docum_subac fds
                set fds.val_ulti_nume_docu_inte = v_num_docu_cont_inte(i)
                where fds.oid_docu_suba = l_oid_docu_suba;

            END IF;

            -- Finalmente actualizamos aquellos registros que tengan
            -- el valor de la serie en null con el valor correcto
            -- primero en el registro de ventas ...
            update fac_regis_unico_venta x
            set x.val_seri_docu_lega = l_val_seri_docu_lega
            where exists (
                select ven.oid_regi
                from fac_docum_conta_cabec cab,
                     fac_regis_unico_venta ven
                where cab.oid_cabe = ven.dcca_oid_cabe
                  and cab.perd_oid_peri = l_oidPeriodo
                  and cab.pais_oid_pais = l_oidPais
                  and cab.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
                  and cab.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
                  and cab.val_seri_docu_lega is null
                  and ven.oid_regi = x.oid_regi
            );

            -- y luego en los documentos contables
            update fac_docum_conta_cabec y
            set y.val_seri_docu_lega = l_val_seri_docu_lega
            where exists (
                select cab.oid_cabe
                from fac_docum_conta_cabec cab
                where cab.perd_oid_peri = l_oidPeriodo
                  and cab.pais_oid_pais = l_oidPais
                  and cab.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
                  and cab.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
                  and cab.val_seri_docu_lega is null
                  and cab.oid_cabe = y.oid_cabe
            );

        END LOOP;
    END LOOP;
    CLOSE c_numerosInternos;


    EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'PED_PR_ACTUA_SECUE_PEDID: '||ls_sqlerrm);

END PED_PR_ACTUA_SECUE_PEDID;


/**************************************************************************
  Descripcion       : APP_PR_INSER_HOMOL_YOBEL

  Fecha Creacion    : 11/10/2010
  Parametros Entrada:
      psCodigoZona        : Codigo de zona
      psCodigoSeccion     : Codigo de seccion
      psCodigoTerritorio  : Codigo de territorio
      psNumeroSecuencia   : Numero de secuencia
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
 PROCEDURE APP_PR_INSER_HOMOL_YOBEL(psCodigoZona         VARCHAR2,
                                    psCodigoSeccion      VARCHAR2,
                                    psCodigoTerritorio   VARCHAR2,
                                    psNumeroSecuencia    VARCHAR2 ) IS

 BEGIN
    begin
      insert into app_homol_yobel(cod_zona,
                                  cod_secc,
                                  cod_terr,
                                  num_secu)
        values(psCodigoZona,
               psCodigoSeccion,
               psCodigoTerritorio,
               psNumeroSecuencia);
    exception
      when dup_val_on_index then
           update app_homol_yobel
              set num_secu = psNumeroSecuencia
            where cod_zona = psCodigoZona
              and cod_secc = psCodigoSeccion
              and cod_terr = psCodigoTerritorio;
    end;




 EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'APP_PR_INSER_HOMOL_YOBEL: '||ls_sqlerrm);

 END APP_PR_INSER_HOMOL_YOBEL;

/**************************************************************************
  Descripcion       : APP_PR_INSER_TEMPO_HOMOL_YOBEL

  Fecha Creacion    : 06/10/2011
  Parametros Entrada:
      psCodigoZona        : Codigo de zona
      psCodigoSeccion     : Codigo de seccion
      psCodigoTerritorio  : Codigo de territorio
      psNumeroSecuencia   : Numero de secuencia
  Autor             : Francesco Rodriguez
  ***************************************************************************/
 PROCEDURE APP_PR_INSER_TEMPO_HOMOL_YOBEL(psCodigoZona         VARCHAR2,
                                    psCodigoSeccion      VARCHAR2,
                                    psCodigoTerritorio   VARCHAR2,
                                    psNumeroSecuencia    VARCHAR2 ) IS

 BEGIN
    begin

      insert into app_tempo_homol_yobel(cod_zona,
                                  cod_secc,
                                  cod_terr,
                                  num_secu)
        values(psCodigoZona,
               psCodigoSeccion,
               psCodigoTerritorio,
               psNumeroSecuencia);
    exception
      when dup_val_on_index then
           update app_tempo_homol_yobel
              set num_secu = psNumeroSecuencia
            where cod_zona = psCodigoZona
              and cod_secc = psCodigoSeccion
              and cod_terr = psCodigoTerritorio;
    end;




 EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'APP_PR_INSER_HOMOL_YOBEL: '||ls_sqlerrm);

 END APP_PR_INSER_TEMPO_HOMOL_YOBEL;

/***************************************************************************
Descripcion    : APP_PR_PROCE_SECUE_ZONA
Fecha Creacion : 21/10/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   psOidPais       :  OID de Pais
   pscodigoZona    :  Codigo de Zona
   psoidrutatrans  :  OID Ruta transporte
   pssecuencia     :  Secuencia
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_ZONA(psOidPais       NUMBER,
                                  pscodigoZona    VARCHAR2,
                                  psoidrutatrans  NUMBER,
                                  psUsuario       VARCHAR2,
                                  psmensajeerror  OUT VARCHAR2)
IS

  cont NUMBER := 0;
  vsDataAuditoria  app_rutas_audit_secue_terri.val_data_audi%TYPE;
  vsNumSecuOrig    app_rutas_trans.num_secu%TYPE;
  vsOidRutaTrans   app_rutas_trans.OID_RUTA_TRAN%TYPE;

BEGIN

  SELECT COUNT(1)
    INTO cont
    FROM app_rutas_trans
   WHERE oid_ruta_tran = psoidrutatrans;

      psmensajeerror := '-1';

  BEGIN

        IF cont = 0 THEN

      vsOidRutaTrans := app_rutr_seq.nextval;

      INSERT INTO app_rutas_trans
      VALUES (
        vsOidRutaTrans,
                      psOidPais,
                      pscodigoZona,
                      null,
        pscodigoZona
      );

    vsDataAuditoria := '/2=' || psOidPais     ||
                       '/3=' || pscodigoZona  ||
                       '/5=' || pscodigoZona  ||
                       '/*';

    -- Se inserta en la tabla de auditoria
    INSERT INTO app_rutas_audit_secue_terri
    VALUES (
      'APPTRA',
      vsOidRutaTrans,
      psUsuario,
      'RSTINS',
      SYSDATE,
      'APP_PR_PROCE_SECUE_ZONA',
      vsDataAuditoria
    );

           ELSE

    -- Se obtiene el registro antes de actualizarlo para insertarlo en la tabla de auditoria
    SELECT num_secu
      INTO vsNumSecuOrig
      FROM app_rutas_trans
     WHERE oid_ruta_tran = psoidrutatrans;

      UPDATE app_rutas_trans
         SET num_secu = cod_ruta
       WHERE oid_ruta_tran = psoidrutatrans;

    -- Se inserta en la tabla de auditoria el valor antiguo y el valor nuevo
    vsDataAuditoria := '/2=' || psOidPais      ||
                       '/3=' || pscodigoZona   ||
                       '/5=' || vsNumSecuOrig  ||
                       '/*';

    INSERT INTO app_rutas_audit_secue_terri
    VALUES (
      'APPTRA',
      psoidrutatrans,
      psUsuario,
      'RSTUPO',
      SYSDATE,
      'APP_PR_PROCE_SECUE_ZONA',
      vsDataAuditoria
    );

    -- Se obtiene el registro actualizado para insertarlo en la tabla de auditoria
    SELECT num_secu
      INTO vsNumSecuOrig
      FROM app_rutas_trans
     WHERE oid_ruta_tran = psoidrutatrans;

    vsDataAuditoria := '/2=' || psOidPais      ||
                       '/3=' || pscodigoZona   ||
                       '/5=' || vsNumSecuOrig  ||
                       '/*';

    INSERT INTO app_rutas_audit_secue_terri
    VALUES (
      'APPTRA',
      psoidrutatrans,
      psUsuario,
      'RSTUPM',
      SYSDATE,
      'APP_PR_PROCE_SECUE_ZONA',
      vsDataAuditoria
    );

           END IF;

  EXCEPTION
    WHEN dup_val_on_index THEN
          psmensajeerror := pscodigoZona;

  END;

EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'APP_PR_PROCE_SECUE_ZONA: '||ls_sqlerrm);

END APP_PR_PROCE_SECUE_ZONA;

/***************************************************************************
Descripcion    : APP_PR_INSER_SECUE_ZONAS
Fecha Creacion : 04/11/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   psOidPais       :  OID de Pais
   pscodigoZona    :  Codigo de Zona
   psoidrutatrans  :  OID Ruta transporte
   pssecuencia     :  Secuencia
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_INSER_SECUE_ZONAS(psOidPais       NUMBER,
                                   pscodigoZona    VARCHAR2,
                                   psoidrutatrans  NUMBER,
                                   pssecuencia     NUMBER,
                                   psUsuario       VARCHAR2)
IS

  cont             NUMBER := 0;
  vsDataAuditoria  app_rutas_audit_secue_terri.val_data_audi%TYPE;
  vsNumSecuOrig    app_rutas_trans.num_secu%TYPE;
  vsOidRutaTrans   app_rutas_trans.OID_RUTA_TRAN%TYPE;

BEGIN

  SELECT COUNT(1)
    INTO cont
    FROM app_rutas_trans
   WHERE oid_ruta_tran = psoidrutatrans;

  IF cont = 0 THEN

    vsOidRutaTrans := app_rutr_seq.nextval;

    INSERT INTO app_rutas_trans
    VALUES (
      vsOidRutaTrans,
                psOidPais,
                pscodigoZona,
                null,
      pssecuencia
    );

    vsDataAuditoria := '/2=' || psOidPais     ||
                       '/3=' || pscodigoZona  ||
                       '/5=' || pssecuencia   ||
                       '/*';

    -- Se inserta en la tabla de auditoria
    INSERT INTO app_rutas_audit_secue_terri
    VALUES (
      'APPTRA',
      vsOidRutaTrans,
      psUsuario,
      'GDAINS',
      SYSDATE,
      'APP_PR_INSER_SECUE_ZONAS',
      vsDataAuditoria
    );

  ELSE
    -- Se obtiene el registro antes de actualizarlo para insertarlo en la tabla de auditoria
    SELECT num_secu
      INTO vsNumSecuOrig
      FROM app_rutas_trans
     WHERE oid_ruta_tran = psoidrutatrans;

    UPDATE app_rutas_trans
       SET num_secu = pssecuencia
     WHERE oid_ruta_tran = psoidrutatrans;

    -- Se inserta en la tabla de auditoria el valor antiguo y el valor nuevo
    vsDataAuditoria := '/2=' || psOidPais      ||
                       '/3=' || pscodigoZona   ||
                       '/5=' || vsNumSecuOrig  ||
                       '/*';

    INSERT INTO app_rutas_audit_secue_terri
    VALUES (
      'APPTRA',
      psoidrutatrans,
      psUsuario,
      'GDAUPO',
      SYSDATE,
      'APP_PR_INSER_SECUE_ZONAS',
      vsDataAuditoria
    );

    vsDataAuditoria := '/2=' || psOidPais     ||
                       '/3=' || pscodigoZona  ||
                       '/5=' || pssecuencia   ||
                       '/*';

    INSERT INTO app_rutas_audit_secue_terri
    VALUES (
      'APPTRA',
      psoidrutatrans,
      psUsuario,
      'GDAUPM',
      SYSDATE,
      'APP_PR_INSER_SECUE_ZONAS',
      vsDataAuditoria
    );

  END IF;

EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'APP_PR_INSER_SECUE_ZONAS: '||ls_sqlerrm);

END APP_PR_INSER_SECUE_ZONAS;

/***************************************************************************
Descripcion    : APP_PR_PROCE_SECUE_TERRI
Fecha Creacion : 21/10/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   pscodigoZona      : Codigo zona
   pscodigoSeccion   : Codigo Seccion
   pscodigoTerritorio: Codigo Territorio
   psoidterri        : Oid Teritorio
   psoidrutaterri    : Oid Ruta Territorio
   psoidrutatrans    : Oid Ruta Transporte
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_TERRI(pscodigoZona        VARCHAR2,
                                   pscodigoSeccion     VARCHAR2,
                                   pscodigoTerritorio  VARCHAR2,
                                   psoidterri          NUMBER,
                                   psoidrutaterri      NUMBER,
                                   psoidrutatrans      NUMBER) IS
 cont number :=0;
 contHomol number :=0;

BEGIN
     -- Verifica si la tabla APP_HOMOL_YOBEL tiene registros
     select count(1)
       into contHomol
       from APP_HOMOL_YOBEL
      where COD_ZONA = pscodigoZona; -- Ajustes V2

     select count(1)
           into cont
           from app_rutas_terri
          where OID_RUTA_TERR = psoidrutaterri;

     if contHomol = 0 then
        -- No tiene registros
        IF cont = 0 THEN
           Insert into app_rutas_terri
            values (app_rute_seq.nextval,
                    pscodigoTerritorio,
                    (select rt.oid_ruta_tran
                       from app_rutas_trans rt
                      where rt.cod_ruta = pscodigoZona) /*psoidrutatrans*/,
                    (select zt.oid_terr
                       from zon_terri zt
                      where zt.cod_terr = pscodigoTerritorio
                     )/*psoidterri*/);
        ELSE
            Update app_rutas_terri
               set num_secu=pscodigoTerritorio
             Where oid_ruta_terr=psoidrutaterri;

        END IF;

     else
         -- Si tiene registros
         IF cont = 0 THEN
            Insert into app_rutas_terri
            values(app_rute_seq.nextval,
                   (select num_secu
                      from app_homol_yobel
                     where cod_zona=pscodigoZona
                       and cod_secc=pscodigoSeccion
                       and cod_terr=pscodigoTerritorio),
                   (select rt.oid_ruta_tran
                       from app_rutas_trans rt
                      where rt.cod_ruta = pscodigoZona) /*psoidrutatrans*/,
                    (select zt.oid_terr
                       from zon_terri zt
                      where zt.cod_terr = pscodigoTerritorio
                     )/*psoidterri*/);
         ELSE
             Update app_rutas_terri
                set num_secu = (select num_secu
                                  from app_homol_yobel
                                 where cod_zona=pscodigoZona
                                   and cod_secc=pscodigoSeccion
                                   and cod_terr=pscodigoTerritorio)
           	  Where oid_ruta_terr = psoidrutaterri;

         END IF;
     end if;

EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'APP_PR_PROCE_SECUE_TERRI: '||ls_sqlerrm);

END APP_PR_PROCE_SECUE_TERRI;

/***************************************************************************
Descripcion    : APP_PR_PROCE_SECUE_TERRI2
Fecha Creacion : 21/10/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   pscodigoZona    : Codigo zona
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_TERRI2(pscodigoZona  VARCHAR2,
                                    psUsuario     VARCHAR2)
IS

  CURSOR curDel IS
    SELECT oid_ruta_terr,
           num_secu,
           rutr_oid_ruta_tran,
           terr_oid_terr
      FROM app_rutas_terri
     WHERE rutr_oid_ruta_tran IN (SELECT oid_ruta_tran
                                    FROM app_rutas_trans
                                   WHERE cod_ruta = pscodigoZona);

  TYPE rutaDelRecord IS RECORD(
    oidRutaTerr      app_rutas_terri.oid_ruta_terr%TYPE,
    numSecu          app_rutas_terri.num_secu%TYPE,
    rutrOidRutaTran  app_rutas_terri.rutr_oid_ruta_tran%TYPE,
    terrOidTerr      app_rutas_terri.terr_oid_terr%TYPE
  );

  TYPE rutaDelRec IS TABLE OF rutaDelRecord;
  rutasDelRecord rutaDelRec;

  CURSOR curIns IS
    SELECT orden,
           (SELECT rt.oid_ruta_tran
              FROM app_rutas_trans rt
             WHERE rt.cod_ruta = pscodigoZona) oidRutaTran,
           oid_terr
      FROM (SELECT oid_terr,
                   cod_terr,
                   nvl( ( SELECT num_secu
                            FROM app_homol_yobel
                           WHERE cod_terr = relok.cod_terr
                             AND cod_secc = relok.cod_secc
                             AND cod_zona = relok.cod_zona), ordenx ) orden
              FROM (SELECT rel.*,
                           rownum  ordenx
                      FROM (SELECT a.cod_zona,
                                   b.cod_secc,
                                   d.oid_terr,
                                   d.cod_terr
                              FROM zon_zona a,
                                   zon_secci b,
                                   zon_terri_admin c,
                                   zon_terri d
                             WHERE a.oid_zona = b.zzon_oid_zona
                               AND b.oid_secc = c.zscc_oid_secc
                               AND c.terr_oid_terr = d.oid_terr
                               AND a.cod_zona = pscodigoZona
                               AND a.ind_acti = 1
                               AND a.ind_borr = 0
                               AND b.ind_acti = 1
                               AND b.ind_borr = 0
                               AND c.ind_borr = 0
                               AND d.ind_borr = 0
                          ORDER BY 1, 2, 4 )  rel
                    ) relok
             );

  TYPE rutaInsRecord IS RECORD(
    orden        app_rutas_terri.num_secu%TYPE,
    oidRutaTran  app_rutas_terri.rutr_oid_ruta_tran%TYPE,
    oidTerr      app_rutas_terri.terr_oid_terr%TYPE
  );

  TYPE rutaInsRec IS TABLE OF rutaInsRecord;
  rutasInsRecord rutaInsRec;

  W_FILAS    NUMBER := 10000;
  vsDataAuditoria  app_rutas_audit_secue_terri.val_data_audi%TYPE;
  vnOidRuta  NUMBER;

BEGIN

  OPEN curDel;
    LOOP
      FETCH curDel BULK COLLECT INTO rutasDelRecord LIMIT W_FILAS;

        IF rutasDelRecord.COUNT > 0 THEN

          FOR x IN rutasDelRecord.FIRST .. rutasDelRecord.LAST LOOP

            vsDataAuditoria := '/2=' || rutasDelRecord(x).numSecu          ||
                               '/3=' || rutasDelRecord(x).rutrOidRutaTran  ||
                               '/4=' || rutasDelRecord(x).terrOidTerr      ||
                               '/*';

            -- Se inserta en la tabla de auditoria
            INSERT INTO app_rutas_audit_secue_terri
            VALUES (
              'APPTER',
              rutasDelRecord(x).oidRutaTerr,
              psUsuario,
              'RSTDEL',
              SYSDATE,
              'APP_PR_PROCE_SECUE_TERRI2',
              vsDataAuditoria
            );

            DELETE FROM app_rutas_terri
             WHERE rutr_oid_ruta_tran = rutasDelRecord(x).rutrOidRutaTran;

          END LOOP;

        END IF;

      EXIT WHEN curDel%NOTFOUND;
    END LOOP;
  CLOSE curDel;

  OPEN curIns;
    LOOP
      FETCH curIns BULK COLLECT INTO rutasInsRecord LIMIT W_FILAS;

        IF rutasInsRecord.COUNT > 0 THEN

          FOR x IN rutasInsRecord.FIRST .. rutasInsRecord.LAST LOOP

            SELECT app_rute_seq.NEXTVAL
              INTO vnOidRuta
              FROM dual;

            INSERT INTO app_rutas_terri(
              oid_ruta_terr,
              num_secu,
              rutr_oid_ruta_tran,
              terr_oid_terr
            )
            VALUES(
              vnOidRuta,
              rutasInsRecord(x).orden,
              rutasInsRecord(x).oidRutaTran,
              rutasInsRecord(x).oidTerr
);

            -- Se inserta en la tabla de auditoria
            vsDataAuditoria := '/2=' || rutasInsRecord(x).orden        ||
                               '/3=' || rutasInsRecord(x).oidRutaTran  ||
                               '/4=' || rutasInsRecord(x).oidTerr      ||
                               '/*';

            -- Se inserta en la tabla de auditoria
            INSERT INTO app_rutas_audit_secue_terri
            VALUES (
              'APPTER',
              vnOidRuta,
              psUsuario,
              'RSTINS',
              SYSDATE,
              'APP_PR_PROCE_SECUE_TERRI2',
              vsDataAuditoria
);

          END LOOP;

        END IF;

      EXIT WHEN curIns%NOTFOUND;
    END LOOP;
  CLOSE curIns;

EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'APP_PR_PROCE_SECUE_TERRI2: '||ls_sqlerrm);

END APP_PR_PROCE_SECUE_TERRI2;

/***************************************************************************
Descripcion    : APP_PR_PROCE_SECUE_TERRIT
Fecha Creacion : 21/10/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   pscodigoZona      : Codigo zona
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_TERRIT(psUsuario     VARCHAR2)
IS

  CURSOR curDel IS
    select DISTINCT cod_zona
      from zon_zona, zon_secci, zon_terri_admin, zon_terri
     where zzon_oid_zona = oid_zona
       and zscc_oid_secc = oid_secc
       and zon_terri_admin.terr_oid_terr = oid_terr
       and zon_zona.ind_borr = 0
       and zon_secci.ind_borr = 0
       and zon_terri.ind_borr = 0
       and zon_terri_admin.ind_borr = 0
       and zon_zona.ind_acti = 1
       and zon_secci.ind_acti = 1
       and zon_terri.oid_terr not in
           (select terr_oid_terr from app_rutas_terri);

  TYPE rutaDelRecord IS RECORD(
    codzona      APP_HOMOL_YOBEL.Cod_Zona%TYPE
  );

  TYPE rutaDelRec IS TABLE OF rutaDelRecord;
  rutasDelRecord rutaDelRec;


  W_FILAS    NUMBER := 10000;
  vsDataAuditoria  app_rutas_audit_secue_terri.val_data_audi%TYPE;
  vnOidRuta  NUMBER;

BEGIN

  OPEN curDel;
    LOOP
      FETCH curDel BULK COLLECT INTO rutasDelRecord LIMIT W_FILAS;

        IF rutasDelRecord.COUNT > 0 THEN

          FOR x IN rutasDelRecord.FIRST .. rutasDelRecord.LAST LOOP

            --- borra APP_HOMOL_YOBEL
            delete from APP_HOMOL_YOBEL
            where cod_zona = rutasDelRecord(x).codzona;

            --- borra app_rutas_terri
            delete from APP_RUTAS_TERRI x
            where X.RUTR_OID_RUTA_TRAN in (select oid_ruta_Tran from app_rutas_trans
            where cod_ruta = rutasDelRecord(x).codzona );


            --- inserta en APP_HOMOL_YOBEL 
            insert into APP_HOMOL_YOBEL
            select cod_zona,cod_secc,cod_terr ,RANK() OVER
            (PARTITION BY cod_zona   ORDER BY cod_zona,cod_secc,cod_terr) num_secu 
            from zon_zona a, zon_secci b, app_rutas_trans c, zon_terri_admin d, zon_terri e 
            where a.oid_zona=b.zzon_oid_zona and a.cod_zona=c.cod_ruta 
            and b.oid_secc=d.zscc_oid_secc and d.terr_oid_terr=e.oid_terr 
            and d.ind_borr=0 
            and cod_zona = rutasDelRecord(x).codzona
            order by a.cod_zona, b.cod_secc ,cod_terr;

            --- inserta en app_rutas_terri 
            insert into app_rutas_terri 
            select app_rute_seq.nextval, a.num_secu , B.OID_RUTA_TRAN, T.OID_TERR 
            from APP_HOMOL_YOBEL a, zon_terri t, app_rutas_trans b
            where a.cod_terr = T.COD_TERR
            and A.COD_ZONA = B.COD_RUTA 
            and a.cod_zona = rutasDelRecord(x).codzona;


          END LOOP;

        END IF;

      EXIT WHEN curDel%NOTFOUND;
    END LOOP;
  CLOSE curDel;


EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'APP_PR_PROCE_SECUE_TERRIT: '||ls_sqlerrm);

END APP_PR_PROCE_SECUE_TERRIT;

/***************************************************************************
Descripcion    : APP_PR_PROCE_SECUE_TERRIF
Fecha Creacion : 21/10/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   pscodigoZona      : Codigo zona
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_TERRIZ(pscodigoZona  VARCHAR2,
                                    psUsuario     VARCHAR2)
IS

  W_FILAS    NUMBER := 10000;
  vsDataAuditoria  app_rutas_audit_secue_terri.val_data_audi%TYPE;
  vnOidRuta  NUMBER;

BEGIN


    --- borra APP_HOMOL_YOBEL
    delete from APP_HOMOL_YOBEL
    where cod_zona = pscodigoZona;

    --- borra app_rutas_terri
    delete from APP_RUTAS_TERRI x
    where X.RUTR_OID_RUTA_TRAN in (select oid_ruta_Tran from app_rutas_trans
    where cod_ruta = pscodigoZona);


    --- inserta en APP_HOMOL_YOBEL 
    insert into APP_HOMOL_YOBEL
    select cod_zona,cod_secc,cod_terr ,RANK() OVER
    (PARTITION BY cod_zona   ORDER BY cod_zona,cod_secc,cod_terr) num_secu 
    from zon_zona a, zon_secci b, app_rutas_trans c, zon_terri_admin d, zon_terri e 
    where a.oid_zona=b.zzon_oid_zona and a.cod_zona=c.cod_ruta 
    and b.oid_secc=d.zscc_oid_secc and d.terr_oid_terr=e.oid_terr 
    and d.ind_borr=0 
    and cod_zona = pscodigoZona
    order by a.cod_zona, b.cod_secc ,cod_terr;

    --- inserta en app_rutas_terri 
    insert into app_rutas_terri 
    select app_rute_seq.nextval, a.num_secu , B.OID_RUTA_TRAN, T.OID_TERR 
    from APP_HOMOL_YOBEL a, zon_terri t, app_rutas_trans b
    where a.cod_terr = T.COD_TERR
    and A.COD_ZONA = B.COD_RUTA 
    and a.cod_zona = pscodigoZona;

EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'APP_PR_PROCE_SECUE_TERRIZ: '||ls_sqlerrm);

END APP_PR_PROCE_SECUE_TERRIZ;


/***************************************************************************
Descripcion    : APP_PR_INSER_SECUE_TERRI
Fecha Creacion : 04/11/2010
Autor          : Dennys Oliva Iriarte
Parametros:
   psOidPais       :  OID de Pais
   pscodigoZona    :  Codigo de Zona
   psoidrutatrans  :  OID Ruta transporte
   pssecuencia     :  Secuencia
   psUsuario       :  Usuario
***************************************************************************/
PROCEDURE APP_PR_INSER_SECUE_TERRI(psoidrutatrans     NUMBER,
                                   psoidrutaterri     NUMBER,
                                   psoidterri         NUMBER,
                                   pssecuencia        NUMBER,
                                   pscodigoZona       VARCHAR2,
                                   pscodigoTerritorio VARCHAR2,
                                   psOidPais          NUMBER,
                                   psUsuario          VARCHAR2)
IS

  cont             NUMBER := 0;
  vsDataAuditoria  app_rutas_audit_secue_terri.val_data_audi%TYPE;
  vnOidRuta        NUMBER;
  vnOidRutaTrans   NUMBER;
  vnOidTerri       NUMBER;
  vnNumSecuencia   NUMBER;

BEGIN

  SELECT COUNT(1)
    INTO cont
    FROM app_rutas_terri
   WHERE oid_ruta_terr = psoidrutaterri;

  IF cont = 0 THEN

    SELECT app_rute_seq.NEXTVAL
      INTO vnOidRuta
      FROM dual;

    SELECT rt.oid_ruta_tran
      INTO vnOidRutaTrans
      FROM app_rutas_trans rt
     WHERE rt.pais_oid_pais = psOidPais
       AND rt.cod_ruta = pscodigoZona;

    SELECT zt.oid_terr
      INTO vnOidTerri
      FROM zon_terri zt
     WHERE zt.pais_oid_pais = psOidPais
       AND zt.cod_terr = pscodigoTerritorio;

    INSERT INTO app_rutas_terri
    VALUES (
      vnOidRuta,
                pssecuencia,
      vnoidrutatrans,
      vnOidTerri
    );

    vsDataAuditoria := '/2=' || pssecuencia     ||
                       '/3=' || vnOidRutaTrans  ||
                       '/4=' || vnOidTerri      ||
                       '/*';

    -- Se inserta en la tabla de auditoria
    INSERT INTO app_rutas_audit_secue_terri
    VALUES (
      'APPTER',
      vnOidRuta,
      psUsuario,
      'GDAINS',
      SYSDATE,
      'APP_PR_INSER_SECUE_TERRI',
      vsDataAuditoria
    );

  ELSE

    SELECT num_secu,
           rutr_oid_ruta_tran,
           terr_oid_terr
      INTO vnNumSecuencia,
           vnOidRutaTrans,
           vnOidTerri
      FROM app_rutas_terri
     WHERE oid_ruta_terr = psoidrutaterri;

    vsDataAuditoria := '/2=' || vnNumSecuencia  ||
                       '/3=' || vnOidRutaTrans  ||
                       '/4=' || vnOidTerri      ||
                       '/*';

    -- Se inserta en la tabla de auditoria
    INSERT INTO app_rutas_audit_secue_terri
    VALUES (
      'APPTER',
      psoidrutaterri,
      psUsuario,
      'GDAUPO',
      SYSDATE,
      'APP_PR_INSER_SECUE_TERRI',
      vsDataAuditoria
    );

    UPDATE app_rutas_terri
       SET num_secu = pssecuencia
     WHERE oid_ruta_terr = psoidrutaterri;

    SELECT num_secu,
           rutr_oid_ruta_tran,
           terr_oid_terr
      INTO vnNumSecuencia,
           vnOidRutaTrans,
           vnOidTerri
      FROM app_rutas_terri
     WHERE oid_ruta_terr = psoidrutaterri;

    vsDataAuditoria := '/2=' || vnNumSecuencia  ||
                       '/3=' || vnOidRutaTrans  ||
                       '/4=' || vnOidTerri      ||
                       '/*';

    -- Se inserta en la tabla de auditoria
    INSERT INTO app_rutas_audit_secue_terri
    VALUES (
      'APPTER',
      psoidrutaterri,
      psUsuario,
      'GDAUPM',
      SYSDATE,
      'APP_PR_INSER_SECUE_TERRI',
      vsDataAuditoria
    );

  END IF;

EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'APP_PR_INSER_SECUE_TERRI: '||ls_sqlerrm);

END APP_PR_INSER_SECUE_TERRI;

/***************************************************************************
Descripcion : Proceso De Secunciacion De Zonas Desde La Tabla APP_HOMOL_YOBEL
Fecha Creacion : 20/01/2011
Autor : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE APP_PR_PROCE_SECUE_ZONAS (psUsuario  VARCHAR2)
IS

  CURSOR cursorSecue IS
    SELECT a.cod_zona,
           b.cod_secc,
           d.cod_terr,
           e.num_secu,
           e.oid_ruta_terr,
           d.oid_terr,
           e.rutr_oid_ruta_tran       --- PER-SiCC-2011-0302
      FROM zon_zona        a,
           zon_secci       b,
           zon_terri_admin c,
           zon_terri       d,
           app_rutas_terri e
     WHERE b.zzon_oid_zona = a.oid_zona
       AND c.zscc_oid_secc = b.oid_secc
       AND c.terr_oid_terr = d.oid_terr
       AND a.ind_borr = 0
       AND b.ind_borr = 0
       AND d.ind_borr = 0
       AND c.ind_borr = 0
       AND a.ind_acti = 1
       AND b.ind_acti = 1
       AND d.oid_terr = e.terr_oid_terr(+)
       AND a.cod_zona IN (SELECT DISTINCT cod_zona
                            FROM app_homol_yobel) -- PER-SiCC-2011-0302 < lista de zonas en app_homol_yobel >
     ORDER BY 1, 4 ;
 --    ORDER BY 1, 2, 3;

  TYPE t_cod_zona            IS TABLE OF zon_zona.cod_zona%TYPE ;
  TYPE t_cod_secc            IS TABLE OF zon_secci.cod_secc%TYPE ;
  TYPE t_cod_terr            IS TABLE OF zon_terri.cod_terr%TYPE ;
  TYPE t_num_secu            IS TABLE OF app_rutas_terri.NUM_SECU%TYPE ;
  TYPE t_oid_ruta_terr       IS TABLE OF app_rutas_terri.OID_RUTA_TERR%TYPE ;
  TYPE t_oid_terr            IS TABLE OF zon_terri.OID_TERR%TYPE ;
  TYPE t_rutr_oid_ruta_tran  IS TABLE OF app_rutas_terri.rutr_oid_ruta_tran%TYPE ;    --- PER-SiCC-2011-0302

  v_cod_zona             t_cod_zona      ;
  v_cod_secc             t_cod_secc      ;
  v_cod_terr             t_cod_terr      ;
  v_num_secu             t_num_secu      ;
  v_oid_ruta_terr        t_oid_ruta_terr ;
  v_oid_terr             t_oid_terr      ;
    v_rutr_oid_ruta_tran   t_rutr_oid_ruta_tran ;    --- PER-SiCC-2011-0302

  rows         NATURAL := 1000; -- Number of rows to process at a time
  i            BINARY_INTEGER := 0;
  v_row_count  NUMBER := 0;
  cont         NUMBER := 0;

  vsDataAuditoria  app_rutas_audit_secue_terri.val_data_audi%TYPE;
  vsNumSecuOrig    app_rutas_terri.num_secu%TYPE;
  vnOidTerrOrig    app_rutas_terri.terr_oid_terr%TYPE;
  vnOidRutaTerOrig app_rutas_terri.oid_ruta_terr%TYPE;
  vnOidRutaTraOrig app_rutas_terri.rutr_oid_ruta_tran%TYPE;
  vnOidRuta        NUMBER;

  BEGIN
      ---- Actualiza zona y seccion del app_homol_yobel por rezonificaion

      update app_homol_yobel ahy
      set cod_zona = (  SELECT a.cod_zona
            FROM zon_zona        a,zon_secci       b,zon_terri_admin c,zon_terri       d
            WHERE b.zzon_oid_zona = a.oid_zona
             AND c.zscc_oid_secc = b.oid_secc
             AND c.terr_oid_terr = d.oid_terr
             AND a.ind_borr = 0
             AND b.ind_borr = 0
             AND d.ind_borr = 0
             AND c.ind_borr = 0
             AND a.ind_acti = 1
             AND b.ind_acti = 1
             and D.COD_TERR = AHY.COD_TERR
      ),
       cod_SECC = (  SELECT B.COD_SECC 
            FROM zon_zona        a,zon_secci       b,zon_terri_admin c,zon_terri       d
            WHERE b.zzon_oid_zona = a.oid_zona
             AND c.zscc_oid_secc = b.oid_secc
             AND c.terr_oid_terr = d.oid_terr
             AND a.ind_borr = 0
             AND b.ind_borr = 0
             AND d.ind_borr = 0
             AND c.ind_borr = 0
             AND a.ind_acti = 1
             AND b.ind_acti = 1
             and D.COD_TERR = AHY.COD_TERR
      ) ;



       OPEN cursorSecue;
          LOOP
      --Bulk collect data into memory table - X rows at a time
             FETCH cursorSecue BULK COLLECT INTO  v_cod_zona     ,
                                                  v_cod_secc     ,
                                                  v_cod_terr     ,
                                           v_num_secu     ,
                                           v_oid_ruta_terr,
                                           v_oid_terr,
                                                  v_rutr_oid_ruta_tran     -- PER-SiCC-2011-0302
                                            LIMIT rows;

                 EXIT WHEN v_row_count = cursorSecue%ROWCOUNT;
                 v_row_count := cursorSecue%ROWCOUNT;

      FOR i IN 1..v_cod_zona.COUNT LOOP

        --Se inserta en la tabla de auditoria antes de eliminar
        --Se obtienen los datos antes de eliminarlos
        BEGIN
            SELECT num_secu,
                   oid_ruta_terr,
                   terr_oid_terr
              INTO vsNumSecuOrig,
                   vnOidRutaTerOrig,
                   vnOidTerrOrig
              FROM app_rutas_terri
             WHERE rutr_oid_ruta_tran = v_rutr_oid_ruta_tran(i)
               AND num_secu = (SELECT num_secu
                                 FROM app_homol_yobel
                                WHERE cod_zona = v_cod_zona(i)
                                  AND cod_secc = v_cod_secc(i)
                                  AND cod_terr = v_cod_terr(i))
               AND  oid_ruta_terr  <> v_oid_ruta_terr(i) ;
        EXCEPTION WHEN NO_DATA_FOUND
               THEN
                  vnOidTerrOrig := NULL;
        END;
        IF  vnOidTerrOrig IS NOT NULL THEN

            vsDataAuditoria := '/2=' || vsNumSecuOrig            ||
                               '/3=' || v_rutr_oid_ruta_tran(i)  ||
                               '/4=' || vnOidTerrOrig            ||
                               '/*';

            INSERT INTO app_rutas_audit_secue_terri
            VALUES (
              'APPTER',
              vnOidRutaTerOrig,
              psUsuario,
              'HOMDEL',
              SYSDATE,
              'APP_PR_PROCE_SECUE_ZONAS',
              vsDataAuditoria
            );
        END IF;

                        /* PER-SiCC-2011-0302  */

        -- Eliminar casos que  tienen una secuencia  que se usara  para  otro territorio
                        DELETE FROM app_rutas_terri ter
                         WHERE ter.rutr_oid_ruta_tran  =  v_rutr_oid_ruta_tran(i)
           AND ter.num_secu = (SELECT num_secu
                                                  FROM app_homol_yobel
                                WHERE cod_zona = v_cod_zona(i)
                                  AND cod_secc = v_cod_secc(i)
                                  AND cod_terr = v_cod_terr(i))
           AND ter.oid_ruta_terr  <> v_oid_ruta_terr(i);

                        /* fin PER-SiCC-2011-0302  */

        SELECT COUNT(1)
          INTO cont
          FROM app_rutas_terri
         WHERE oid_ruta_terr = v_oid_ruta_terr(i);

        IF cont != 0 THEN
          -- Se inserta en la tabla de auditoria antes de realizar la actualizacion
          SELECT num_secu,
                 rutr_oid_ruta_tran,
                 terr_oid_terr
            INTO vsNumSecuOrig,
                 vnOidRutaTraOrig,
                 vnOidTerrOrig
            FROM app_rutas_terri
           WHERE oid_ruta_terr = v_oid_ruta_terr(i);

          vsDataAuditoria := '/2=' || vsNumSecuOrig     ||
                             '/3=' || vnOidRutaTraOrig  ||
                             '/4=' || vnOidTerrOrig     ||
                             '/*';

          INSERT INTO app_rutas_audit_secue_terri
          VALUES (
            'APPTER',
            v_oid_ruta_terr(i),
            psUsuario,
            'HOMUPO',
            SYSDATE,
            'APP_PR_PROCE_SECUE_ZONAS',
            vsDataAuditoria
          );
                           -- Si tiene secuencia
         ls_dato := ' Falta ZONA: ' || v_cod_zona(i) ||
                     ' SEC: '  || v_cod_secc(i) ||
                     ' TERR: ' || v_cod_terr(i) || 
                     ' OIDT '  || v_oid_ruta_terr(i);

          UPDATE app_rutas_terri
             SET num_secu = (SELECT num_secu
                               FROM app_homol_yobel
                              WHERE cod_zona = v_cod_zona(i)
                                and cod_secc = v_cod_secc(i)
                                AND cod_terr = v_cod_terr(i))
           WHERE oid_ruta_terr = v_oid_ruta_terr(i);

         ls_dato := '';

          SELECT num_secu,
                 rutr_oid_ruta_tran,
                 terr_oid_terr
            INTO vsNumSecuOrig,
                 vnOidRutaTraOrig,
                 vnOidTerrOrig
            FROM app_rutas_terri
           WHERE oid_ruta_terr = v_oid_ruta_terr(i);

          vsDataAuditoria := '/2=' || vsNumSecuOrig     ||
                             '/3=' || vnOidRutaTraOrig  ||
                             '/4=' || vnOidTerrOrig     ||
                             '/*';

          -- Se inserta en la tabla de auditoria despues de realizar la actualizacion
          INSERT INTO app_rutas_audit_secue_terri
          VALUES (
            'APPTER',
            v_oid_ruta_terr(i),
            psUsuario,
            'HOMUPM',
            SYSDATE,
            'APP_PR_PROCE_SECUE_ZONAS',
            vsDataAuditoria
          );

        ELSE
          SELECT app_rute_seq.NEXTVAL
            INTO vnOidRuta
            FROM dual;

          INSERT INTO app_rutas_terri
          VALUES (
            vnOidRuta,
            (SELECT num_secu
               FROM app_homol_yobel
              WHERE cod_zona = v_cod_zona(i)
                AND cod_secc = v_cod_secc(i)
                AND cod_terr = v_cod_terr(i)),
            (SELECT t.oid_ruta_tran
               FROM app_rutas_trans t
              WHERE t.cod_ruta = v_cod_zona(i)),
            v_oid_terr(i)
          );

          SELECT num_secu,
                 rutr_oid_ruta_tran,
                 terr_oid_terr
            INTO vsNumSecuOrig,
                 vnOidRutaTraOrig,
                 vnOidTerrOrig
            FROM app_rutas_terri
           WHERE oid_ruta_terr = vnOidRuta;

          vsDataAuditoria := '/2=' || vsNumSecuOrig     ||
                             '/3=' || vnOidRutaTraOrig  ||
                             '/4=' || vnOidTerrOrig     ||
                             '/*';

          -- Se inserta en la tabla de auditoria
          INSERT INTO app_rutas_audit_secue_terri
          VALUES (
            'APPTER',
            vnOidRuta,
            psUsuario,
            'HOMINS',
            SYSDATE,
            'APP_PR_PROCE_SECUE_ZONAS',
            vsDataAuditoria
          );

        END IF;

                    END LOOP;

                 END LOOP;
             CLOSE cursorSecue;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR APP_PR_PROCE_SECUE_ZONAS: ' || ls_dato ||ls_sqlerrm);

  END APP_PR_PROCE_SECUE_ZONAS;


/***************************************************************************
Descripcion    : APP_PR_VALID_CARGA_HOMOL
Fecha Creacion : 21/09/2011
Autor          : Francesco Rodriguez
Parametros:
   psListaErrores        :  Códigos de Zona, Sección, Territorio y/o Número de
                            Secuencia obtenidos como errados
   psMensajeInicioError  :  Mensaje cabecera de Error según la validación
   psMensajeFinalError   :  Mensaje final de Error según la validación
***************************************************************************/
PROCEDURE APP_PR_VALID_CARGA_HOMOL(psListaErrores       OUT VARCHAR2,
                                   psMensajeInicioError OUT VARCHAR2,
                                   psMensajeFinalError  OUT VARCHAR2) IS

   TYPE codZonaRec IS RECORD ( cod_zona app_homol_yobel.cod_zona%TYPE );
   TYPE codZonaRecTab IS TABLE OF codZonaRec;
   codZonaRecord codZonaRecTab;

   TYPE codSeccZonaRec IS RECORD ( cod_zona app_homol_yobel.cod_zona%TYPE,
                                   cod_secc app_homol_yobel.cod_secc%TYPE);
   TYPE codSeccZonaRecTab IS TABLE OF codSeccZonaRec;
   codSeccZonaRecord codSeccZonaRecTab;

   TYPE codTerrSeccZonaRec IS RECORD ( cod_zona app_homol_yobel.cod_zona%TYPE,
                                   cod_secc app_homol_yobel.cod_secc%TYPE,
                                   cod_terr app_homol_yobel.cod_terr%TYPE);
   TYPE codTerrSeccZonaRecTab IS TABLE OF codTerrSeccZonaRec;
   codTerrSeccZonaRecord codTerrSeccZonaRecTab;

   TYPE codSecTerrSeccZonaRec IS RECORD ( cod_zona app_homol_yobel.cod_zona%TYPE,
                                   cod_secc app_homol_yobel.cod_secc%TYPE,
                                   cod_terr app_homol_yobel.cod_terr%TYPE,
                                   num_secu app_homol_yobel.num_secu%TYPE);
   TYPE codSecTerrSeccZonaRecTab IS TABLE OF codSecTerrSeccZonaRec;
   codSecTerrSeccZonaRecord codSecTerrSeccZonaRecTab;

 BEGIN
   psListaErrores := '';
   psMensajeInicioError := '';
   psMensajeFinalError  := '';

   select distinct yob.cod_zona
     bulk collect into codZonaRecord
     from app_tempo_homol_yobel yob,
          ( select cod_zona
              from zon_zona
             where ind_acti = 1
               and ind_borr = 0 ) zon
    where zon.cod_zona(+) = yob.cod_zona
      and zon.cod_zona is null;

   if codZonaRecord.count > 0 then
     -- 1.1 Códigos de Zona no existen en el maestro de zonas.
     psMensajeInicioError := psMensajeInicioError || 'procesoAPPCargarHomolYobel.error.zonas' || ',';
     psMensajeFinalError  := psMensajeFinalError || 'procesoAPPCargarHomolYobel.error.maestroZonas' || ',';
     for i in codZonaRecord.first .. codZonaRecord.last
       loop
         psListaErrores := psListaErrores || codZonaRecord(i).cod_zona || ', ';
     end loop;
     psListaErrores := substr(psListaErrores, 0, length(psListaErrores)-2) || ' ';
     psListaErrores := psListaErrores || ';';
   end if;

     select distinct  csx.cod_zona,  csx.cod_secc
       bulk collect into codSeccZonaRecord
       from ( select distinct zon.cod_zona, zon.oid_zona, yob.cod_secc
              from app_tempo_homol_yobel yob, zon_zona zon
               where zon.cod_zona = yob.cod_zona
                 and zon.ind_acti = 1
                 and zon.ind_borr = 0 ) csx, zon_secci zsec
      where zsec.zzon_oid_zona(+) = csx.oid_zona
        and zsec.cod_secc(+) = csx.cod_secc
        and zsec.cod_secc is null ;

     if codSeccZonaRecord.count > 0 then
       -- 1.2 Código de Sección no existe en el maestro de secciones.
       psMensajeInicioError := psMensajeInicioError || 'procesoAPPCargarHomolYobel.error.seccion' || ',';
       psMensajeFinalError  := psMensajeFinalError || 'procesoAPPCargarHomolYobel.error.maestroSeccion' || ',';
       for i in codSeccZonaRecord.first .. codSeccZonaRecord.last
         loop
           psListaErrores := psListaErrores || codSeccZonaRecord(i).cod_zona || ' : '
                || codSeccZonaRecord(i).cod_secc || ' , ';
       end loop;
     psListaErrores := substr(psListaErrores, 0, length(psListaErrores)-2) || ' ';
       psListaErrores := psListaErrores || ';';
   end if;

       select yob.cod_zona, yob.cod_secc, yob.cod_terr
         bulk collect into codTerrSeccZonaRecord
     from app_tempo_homol_yobel yob,
              ( select cod_terr
                  from zon_terri
                 where ind_borr = 0 ) ter
        where ter.cod_terr(+) = yob.cod_terr
          and ter.cod_terr is null;

       if codTerrSeccZonaRecord.count > 0 then
         -- 1.3 Código(s) de terreno(s) no existe en el maestro de territorios.
         psMensajeInicioError := psMensajeInicioError || 'procesoAPPCargarHomolYobel.error.territorios' || ',';
         psMensajeFinalError  := psMensajeFinalError || 'procesoAPPCargarHomolYobel.error.maestroTerritorios' || ',';
         for i in codTerrSeccZonaRecord.first .. codTerrSeccZonaRecord.last
           loop
             psListaErrores := psListaErrores || codTerrSeccZonaRecord(i).cod_zona || ' : '
                 || codTerrSeccZonaRecord(i).cod_secc || ' : ' || codTerrSeccZonaRecord(i).cod_terr || ' , ';
         end loop;
     psListaErrores := substr(psListaErrores, 0, length(psListaErrores)-2) || ' ';
         psListaErrores := psListaErrores || ';';
   end if;

         codTerrSeccZonaRecord := null;
         select yobv.cod_zona, yobv.cod_secc, yobv.cod_terr
           bulk collect into codTerrSeccZonaRecord
           from ( select zscc_oid_secc, terr_oid_terr
                    from zon_terri_admin
                   where ind_borr = 0  )   zta,
                ( select yob.cod_zona, yob.cod_secc, yob.cod_terr, zt.oid_terr, sec.oid_secc
              from  app_tempo_homol_yobel yob,
                          zon_zona  zon,
                          zon_terri zt,
                          zon_secci sec
                   where  zon.cod_zona = yob.cod_zona
                     and  zon.ind_acti = 1
                     and  zon.ind_borr = 0
                     and  zt.cod_terr  =  yob.cod_terr
                     and  zt.ind_borr  =  0
                     and  sec.zzon_oid_zona = zon.oid_zona
                     and  sec.cod_secc =  yob.cod_secc
                     and  sec.ind_acti =  1
                      and  sec.ind_borr =  0 )  yobv
          where yobv.oid_secc  =  zta.zscc_oid_secc(+)
            and yobv.oid_terr  = zta.terr_oid_terr(+)
            and zta.zscc_oid_secc  is null;

          if codTerrSeccZonaRecord.count > 0 then
            -- 1.4 Código de Territorio no pertenece a la Sección referida.
            psMensajeInicioError := psMensajeInicioError || 'procesoAPPCargarHomolYobel.error.territorios' || ',';
            psMensajeFinalError  := psMensajeFinalError || 'procesoAPPCargarHomolYobel.error.zonaSeccion' || ',';
            for i in codTerrSeccZonaRecord.first .. codTerrSeccZonaRecord.last
              loop
                psListaErrores := psListaErrores || codTerrSeccZonaRecord(i).cod_zona || ' : '
                   || codTerrSeccZonaRecord(i).cod_secc || ' : ' || codTerrSeccZonaRecord(i).cod_terr || ' , ';
            end loop;
      psListaErrores := substr(psListaErrores, 0, length(psListaErrores)-2) || ' ';
            psListaErrores := psListaErrores || ';';
    end if;

            select yob.cod_zona, yob.cod_secc,
                   yob.cod_terr, yob.num_secu
              bulk collect into codSecTerrSeccZonaRecord
      from app_tempo_homol_yobel yob,
                   ( select hom.cod_zona, hom.num_secu, count(*) cta_sec
               from app_tempo_homol_yobel hom
                      group by hom.cod_zona, hom.num_secu
                     having count(*)  >  1  )  val
             where yob.cod_zona  =  val.cod_zona
               and yob.num_secu  =  val.num_secu
             order by yob.cod_zona, yob.num_secu;

             if codSecTerrSeccZonaRecord.count > 0 then
               --1.5 Valida  secuencia duplicada
               psMensajeInicioError := psMensajeInicioError || 'procesoAPPCargarHomolYobel.error.numSecu';
               psMensajeFinalError  := psMensajeFinalError || 'procesoAPPCargarHomolYobel.error.secuenciaDuplicada';
               for i in codSecTerrSeccZonaRecord.first .. codSecTerrSeccZonaRecord.last
                 loop
                   psListaErrores := psListaErrores || codSecTerrSeccZonaRecord(i).cod_zona || ' : '
                       || codSecTerrSeccZonaRecord(i).cod_secc || ' : ' || codSecTerrSeccZonaRecord(i).cod_terr || ' : '
                       || codSecTerrSeccZonaRecord(i).num_secu || ' , ';
               end loop;
             end if;

   if length(psListaErrores) > 0 then
     psListaErrores := substr(psListaErrores, 0, length(psListaErrores)-2) || ' ';
   end if;

 EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR APP_PR_VALID_CARGA_HOMOL: '||ls_sqlerrm);

END APP_PR_VALID_CARGA_HOMOL;

/***************************************************************************
Descripcion : Proceso De CArga de Secuencias Zonas
Fecha Creacion : 11/04/2012
Autor : Jose Luis Rodriguez
***************************************************************************/
PROCEDURE APP_PR_CARGA_SECUE_ZONAS (psCodigoPais  VARCHAR2,
                                    psError   OUT VARCHAR2)
IS

  CURSOR cursorSecue IS
    SELECT a.cod_zona  COD_ZONA,
           a.num_secu  NUM_SECU
      FROM app_tempo_rutas_trans a;

  TYPE t_cod_zona  IS TABLE OF zon_zona.cod_zona%TYPE;
  TYPE t_num_secu  IS TABLE OF app_tempo_rutas_trans.num_secu%TYPE;

  v_cod_zona  t_cod_zona;
  v_num_secu  t_num_secu;

  CURSOR cursorSecue2 IS
    SELECT a.oid_ruta_tran  OID_RUTA,
           a.cod_ruta       COD_RUTA
      FROM app_rutas_trans a
     WHERE a.num_secu >= 10000
   ORDER BY a.num_secu;

  TYPE t_oid_ruta  IS TABLE OF app_rutas_trans.oid_ruta_tran%TYPE;
  TYPE t_cod_ruta  IS TABLE OF app_rutas_trans.cod_ruta%TYPE;

  v_oid_ruta  t_oid_ruta;
  v_cod_ruta  t_cod_ruta;

  rows         NATURAL := 10000; -- Number of rows to process at a time
  i            BINARY_INTEGER := 0;
  v_row_count  NUMBER := 0;

  vsResultados VARCHAR2(1):= '0';
  vnContador   NUMBER := 0;
  vnoidPais    NUMBER;
  vnNumber     NUMBER;
  vnIndice     NUMBER;
  vnMaximo     NUMBER;

BEGIN

  vnoidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  OPEN cursorSecue;
    LOOP
    --Bulk collect data into memory table - X rows at a time
      FETCH cursorSecue BULK COLLECT INTO  v_cod_zona,
                                           v_num_secu
      LIMIT rows;

      EXIT WHEN v_row_count = cursorSecue%ROWCOUNT;
      v_row_count := cursorSecue%ROWCOUNT;

      -- Se valida las zonas
      FOR i IN v_cod_zona.FIRST .. v_cod_zona.LAST
      LOOP

      IF (vsResultados = '0') THEN

        SELECT COUNT(1)
          INTO vnContador
          FROM zon_zona z
         WHERE z.cod_zona = v_cod_zona (i)
           AND z.ind_acti = 1;

        IF (vnContador = 0) THEN
          vsResultados := '1';
        END IF;

      END IF;

      END LOOP;

      -- Se valida q las secuencias sean numericas
      IF (vsResultados = '0') THEN

        FOR l IN v_num_secu.FIRST .. v_num_secu.LAST
        LOOP

          IF (vsResultados = '0') THEN
            BEGIN
              vnNumber := TO_NUMBER(v_num_secu(l));
            EXCEPTION
            WHEN OTHERS THEN
              vnNumber := -1;
            END;

            IF (vnNumber = -1) THEN
              vsResultados := '2';
            END IF;

          END IF;

        END LOOP;

      END IF;

      -- Se valida las secuencias
      IF (vsResultados = '0') THEN

         vnContador := 0;

         FOR k IN v_num_secu.FIRST .. v_num_secu.LAST
         LOOP

           SELECT COUNT(1)
             INTO vnContador
             FROM app_tempo_rutas_trans a
            WHERE a.num_secu = v_num_secu(k);

           IF (vnContador > 1) THEN
             vsResultados := '3';
           END IF;

         END LOOP;

      END IF;

      IF (vsResultados = '0') THEN

        UPDATE app_rutas_trans
           SET num_secu = num_secu * 10000;

        FOR j IN v_cod_zona.FIRST .. v_cod_zona.LAST
        LOOP

          BEGIN
            INSERT INTO app_rutas_trans(
              oid_ruta_tran,
              pais_oid_pais,
              cod_ruta,
              num_secu
            )
            VALUES(
              app_rutr_seq.NEXTVAL,
              vnoidPais,
              v_cod_zona(j),
              v_num_secu(j)
            );
          EXCEPTION
            WHEN dup_val_on_index THEN
              UPDATE app_rutas_trans
                 SET num_secu = v_num_secu(j)
               WHERE cod_ruta = v_cod_zona(j);
          END;

        END LOOP;

      END IF;

    END LOOP;
  CLOSE cursorSecue;

  IF (vsResultados = '0') THEN

    vnIndice := 1;

    SELECT MAX(a.num_secu)
      INTO vnMaximo
      FROM app_rutas_trans a
     WHERE a.num_secu < 10000;

    OPEN cursorSecue2;
    LOOP
      --Bulk collect data into memory table - X rows at a time
      FETCH cursorSecue2 BULK COLLECT INTO  v_oid_ruta,
                                            v_cod_ruta
      LIMIT rows;

      EXIT WHEN v_row_count = cursorSecue2%ROWCOUNT;
      v_row_count := cursorSecue2%ROWCOUNT;

      FOR m IN v_oid_ruta.FIRST .. v_oid_ruta.LAST
      LOOP

        vnNumber := vnMaximo + vnIndice;

        UPDATE app_rutas_trans
           SET num_secu = vnNumber
         WHERE oid_ruta_tran = v_oid_ruta(m);

        vnIndice := vnIndice + 1;

        dbms_output.put_line('oid_ruta_tran: ' || v_oid_ruta(m) || '  ' || 'vnIndice: ' || vnIndice || '  ' || 'num_secu: ' || vnNumber);

      END LOOP;

    END LOOP;
    CLOSE cursorSecue2;

  END IF;

  psError := vsResultados;

EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR APP_PR_CARGA_SECUE_ZONAS: '||ls_sqlerrm);

END APP_PR_CARGA_SECUE_ZONAS;

END APP_PKG_PROCE;
/

