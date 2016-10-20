CREATE OR REPLACE PACKAGE int_pkg_ape AS

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Cabecera
                      de documentos (DYTCAB)
  Fecha Creacion    : 24/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_cabec_docum
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Detalle
                      de documentos (DYTDET)
  Fecha Creacion    : 24/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_detal_docum
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Maestro
                      de destinos (DYTCLI)
  Fecha Creacion    : 26/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_maest_desti
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Maestro
                      de cuentas (DYTCTA)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_maest_cuent
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Maestro
                      de territorios (DYTSCT)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_maest_terri
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2,
    pslongitudorden1 VARCHAR2,
    pslongitudorden2 VARCHAR2,
    pslongitudorden3 VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Secuencia
                      de cuentas (DYTSCX)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_secue_cuent
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Cronograma
                      de operaciones (DYTCRO)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_crono_opera
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Control
                      de proceso (DYTCTR)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_contr_proce
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2,
    pstipoproceso    VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Matriz
                      de campa;a (ANQPRD0)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_matri_campa
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Matriz con DA
                      de campa;a (ANQPRD0)
  Fecha Creacion    : 06/06/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_matri_campa_da
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de informacion
                      de Anaqueles (ANQPRD0)
  Fecha Creacion    : 26/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_recep_anaqu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de informacion
                      de Chequeo (DYTCHK)
  Fecha Creacion    : 26/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_recep_chequ
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /**************************************************************************
   Descripcion        : APE_PR_CARGA_RUTAS_CLIEN
                        Carga la tabla APP_RUTAS_CLIEN
   Fecha Creacion     : 28/05/2009
   Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ape_pr_carga_rutas_clien
  (
    psoidpais      VARCHAR2,
    pscodigopais   VARCHAR2,
    psfechaproceso VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio Costo por Caja a SAT
  Fecha Creacion    : 01/09/2010
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_costo_caja
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfechafactura   VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Maestro de Productos para WCS
  Fecha Creacion    : 26/08/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_produ_wcs
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigocentro   VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Mapa de Anaqueles para WCS
  Fecha Creacion    : 26/08/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_anaqu_wcs
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigocentro   VARCHAR2,
    pscodigomapacd   VARCHAR2,
    pscodigoversion  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Olas a WCS (WCS-3)
  Fecha Creacion    : 31/08/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_olas_wcs3
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    psfechafactura   VARCHAR2,
    pscodigocentro   VARCHAR2,
    pscodigolinea    VARCHAR2,
    psnumola         VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Devuelve el Texto Variable por Cliente
  Autor             : Nicolás López
  Fecha Creacion    : 31/08/2010
  ***************************************************************************/
  FUNCTION int_fn_ape_devue_texto_varia(p_oid_cliente IN NUMBER) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Datos para Etiquetado a WCS (WCS-8)
  Fecha Creacion    : 02/09/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_etiqu_wcs8
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    psfechafactura   VARCHAR2,
    psnumola         VARCHAR2,
    pscodigocentro   VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Confirmar Envio de Olas a WCS (WCS-3)
  Fecha Creacion    : 02/09/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_olas_wcs3c
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    psnumola         VARCHAR2,
    pscodigocentro   VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Confirmar Envio de Datos para Etiquetado a WCS (WCS-8)
  Fecha Creacion    : 02/09/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_etiqu_wcs8c
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    psnumola         VARCHAR2,
    pscodigocentro   VARCHAR2
  );

  /**************************************************************************
       Descripcion       : Generar numeración de asignaciones por Producto en versión Balanceada
       Autor             : Nicolás López
       Fecha Creacion    : 20/09/2010
       Parametros Entrada :
              psCodigoMarca
              psCodigoCanal
              psCodigoPeriodo
              psCodigoCentro
              psVal_version
  ***************************************************************************/
  PROCEDURE ape_pr_gener_numer_asgpr_verbl
  (
    pscodigopais    VARCHAR2,
    pscodigomarca   VARCHAR2,
    pscodigocanal   VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocentro  VARCHAR2,
    psval_version   VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Facturas Anuladas a WCS
  Fecha Creacion    : 22/09/2010
  Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE int_pr_ape_factu_anula_wcs
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2
  );

  /**************************************************************************
      Descripcion : Devuelve la cadena de texto sin caracteres especiales
      Fecha Creacion : 17/11/2010
      Autor: Nicolas Lopez
  ***************************************************************************/
  FUNCTION int_fn_conve_caden_texto(vscadena VARCHAR2) RETURN VARCHAR2;

FUNCTION int_fn_obtie_estim_mav(pscodigopais VARCHAR2, psoidprod NUMBER, psoidtipofer NUMBER, psoidperi NUMBER)
    RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Productos Material Gerente
                      de Zona y Regional
  Fecha Creacion    : 23/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_produ_mater
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Cabecera de Boletas
                      de Recojo
  Fecha Creacion    : 23/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_cabec_bolet
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Detalle de Boletas de
                      Recojo
  Fecha Creacion    : 23/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_detal_bolet
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Cabecera de Boletas
                      de Recojo Facturacion
  Fecha Creacion    : 06/01/2015
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_cabec_bofac
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Detalle de Boletas de
                      Recojo Facturacion
  Fecha Creacion    : 06/01/2015
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_detal_bofac
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  );
  
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepción de Parametrización para
                      Picado Cabecera
  Fecha Creacion    : 19/01/2016
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_ape_recep_picad_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario  VARCHAR2
  );
  
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepción de Parametrización para
                      Picado Detalle
  Fecha Creacion    : 19/01/2016
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_ape_recep_picad_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario  VARCHAR2
  );  
  
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepción de Parametrización para
                      Orden de Impresión
  Fecha Creacion    : 19/01/2016
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_ape_recep_orden_impre
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario  VARCHAR2
  );    

END int_pkg_ape;
/
CREATE OR REPLACE PACKAGE BODY int_pkg_ape IS

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  --CLASIFICACION_ESTRELLA CONSTANT VARCHAR2(50)      := 'APE_CLAS_ESTR';
  --CLASIFICACION_CIRCULO_HONOR CONSTANT VARCHAR2(50) := 'APE_CLAS_CIRC_HONO';
  --FLAG_ESTRELLA                      CONSTANT VARCHAR2(1) := 'E';
  --FLAG_CIRCULO_DE_HONOR              CONSTANT VARCHAR2(1) := 'H';
  --FLAG_PRIMER_PEDIDO                 CONSTANT VARCHAR2(1) := 'P';
  --FLAG_ESTRELLA_REINCIDENTE          CONSTANT VARCHAR2(1) := 'Y';
  --FLAG_CIRCULO_DE_HONOR_REINCI       CONSTANT VARCHAR2(1) := 'X';
  --FLAG_REINCIDENTE                   CONSTANT VARCHAR2(1) := 'R';
  --FLAG_TODAS_LAS_DEMAS               CONSTANT VARCHAR2(1) := ' ';
  w_filas CONSTANT NUMBER := 5000;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Cabecera
                      de documentos (DYTCAB)
  Fecha Creacion    : 24/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_cabec_docum
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT decode((SELECT (SUM(nvl(soc.ind_oc,
                                    0)))
                      FROM ped_solic_cabec soc
                     WHERE soc.soca_oid_soli_cabe = soc2.oid_soli_cabe),
                    0,
                    '02',
                    '01') ped_serv,
             soc2.val_nume_soli numeropedido,
             cli.cod_clie codigocliente,
             to_char(soc2.fec_fact,
                     'YYMMDD') fechafact,
             soc2.num_unid_aten_tota unidadesatendidas,
             decode((select count(*) nroexpr
                from ped_solic_cabec psc1, ped_tipo_solic_pais a, ped_tipo_solic b, gen_i18n_sicc_comun c
                where PSC1.TSPA_OID_TIPO_SOLI_PAIS = A.OID_TIPO_SOLI_PAIS
                and A.TSOL_OID_TIPO_SOLI =  B.OID_TIPO_SOLI
                and b.OID_TIPO_SOLI = c.VAL_OID
                and c.ATTR_ENTI='PED_TIPO_SOLIC'
                and UPPER(c.VAL_I18N) like '%XPRES%'
                and psc1.soca_OID_SOLI_CABE =  SOC2.OID_SOLI_CABE ),0,0,1) indexpr,
                --soc2.fec_fact-(select x.fec_inic from cra_crono x, cra_activ y where x.zzon_oid_zona=soc2.zzon_oid_zona and x.perd_oid_peri=soc2.perd_oid_peri and x.cact_oid_acti=y.oid_acti and y.cod_acti='FA')+1 ind_fac,
                b.ind_comp,
                to_char(nvl(b.fec,soc2.fec_fact),'DDMMYYYY') FECHA_ENTREGA
        FROM ped_solic_cabec       soc2,
             mae_clien             cli,
             ped_solic_cabec_secue a,
             ped_segui_pedid       b
       WHERE soc2.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais FROM int_lar_tipo_solici_pedido_dis)
         AND soc2.clie_oid_clie = cli.oid_clie
         AND nvl(soc2.ind_inte_lari_gene,
                 0) = 0
         AND a.soca_oid_soli_cabe = soc2.oid_soli_cabe
         AND b.soca_oid_soli_cabe(+) = soc2.oid_soli_cabe
         AND soc2.perd_oid_peri = gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psperiodo)
         AND nvl(soc2.ind_inte_lari_gene,
                 0) = 0
         AND soc2.fec_fact = to_date(psfecha,
                                     'dd/MM/YYYY')
         AND soc2.num_unid_aten_tota > 0
         and exists (
             select 1 from ped_solic_cabec x, ped_solic_posic y, mae_produ z
             where x.soca_oid_soli_cabe=soc2.oid_soli_cabe and x.oid_soli_cabe=y.soca_oid_soli_cabe
             and y.prod_oid_prod=z.oid_prod and nvl(z.val_atri_3,0)<>1
         )
       ORDER BY a.num_secu_fact_diar;

    TYPE interfazrec IS RECORD(
      ped_serv          VARCHAR2(2),
      numeropedido      ped_solic_cabec.val_nume_soli%TYPE,
      codigocliente     mae_clien.cod_clie%TYPE,
      fechafact         VARCHAR2(6),
      unidadesatendidas ped_solic_cabec.num_unid_aten_tota%TYPE,
      ind_express       VARCHAR2(1),
      ind_fac           VARCHAR2(3),
      fecha_entrega     VARCHAR2(15)
      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .ped_serv || ';' || interfazrecord(x).numeropedido || ';' || interfazrecord(x)
                     .codigocliente || ';' || lpad(interfazrecord(x).fechafact,
                                                   7,
                                                   ' ') || ';' ||
                      lpad(interfazrecord(x).unidadesatendidas,3,'0')
                      || ';' || interfazrecord(x).ind_express
                      || ';' || interfazrecord(x).ind_fac
                      || ';' || interfazrecord(x).fecha_entrega
                      ;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_cabec_docum: ' || ls_sqlerrm);
  END int_pr_ape_envi_cabec_docum;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Detalle
                      de documentos (DYTDET)
  Fecha Creacion    : 24/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_detal_docum
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT ped_serv,
             numeropedido,
             cod_sap,
             decode(val_atri_2,
                    '1',
                    'P',
                    ' '),
             SUM(num_unid_aten)
        FROM (SELECT decode((SELECT SUM(nvl(soc.ind_oc,
                                           0))
                              FROM ped_solic_cabec soc
                             WHERE soc.soca_oid_soli_cabe = soc2.oid_soli_cabe),
                            0,
                            '02',
                            '01') ped_serv,
                     soc2.val_nume_soli numeropedido,
                     prod.cod_sap,
                     sop.num_unid_aten,
                     prod.val_atri_2
                FROM ped_solic_cabec       soc2,
                     ped_solic_cabec       soc,
                     ped_solic_posic       sop,
                     mae_clien             cli,
                     mae_produ             prod,
                     ped_solic_cabec_secue a
               WHERE soc2.tspa_oid_tipo_soli_pais IN
                     (SELECT tspa_oid_tipo_soli_pais FROM int_lar_tipo_solici_pedido_dis)
                 AND soc.soca_oid_soli_cabe = soc2.oid_soli_cabe
                 AND sop.soca_oid_soli_cabe = soc.oid_soli_cabe
                 AND sop.num_unid_aten > 0
                 AND prod.oid_prod = sop.prod_oid_prod
                 AND sop.espo_oid_esta_posi <> 2
                 AND soc2.clie_oid_clie = cli.oid_clie
                 AND a.soca_oid_soli_cabe = soc2.oid_soli_cabe
                 AND soc2.perd_oid_peri = gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psperiodo)
                 AND soc2.ind_inte_lari_gene = 0
                 AND soc2.fec_fact = to_date(psfecha,
                                             'dd/MM/YYYY')
                 AND soc2.num_unid_aten_tota > 0
                 and nvl(prod.val_atri_3,0)<>1
               ORDER BY a.num_secu_fact_diar)
       GROUP BY ped_serv,
                numeropedido,
                cod_sap,
                val_atri_2;

    TYPE interfazrec IS RECORD(
      pedserv           VARCHAR2(2),
      numeropedido      ped_solic_cabec.val_nume_soli%TYPE,
      codigosap         mae_produ.cod_sap%TYPE,
      valatri2          mae_produ.val_atri_2%TYPE,
      unidadesatendidas ped_solic_posic.num_unid_aten%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).pedserv || ';' || interfazrecord(x).numeropedido || ';' || interfazrecord(x)
                     .codigosap || ';' || interfazrecord(x).unidadesatendidas || ';' || interfazrecord(x)
                     .valatri2;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_detal_docum: ' || ls_sqlerrm);
  END int_pr_ape_envi_detal_docum;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Maestro
                      de destinos (DYTCLI)
  Fecha Creacion    : 26/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_maest_desti
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

    l_textoObs             VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','textoObservaciones');

    l_textoCP             VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','textoCP');


    CURSOR c_interfaz
    (
      vnidperiodo NUMBER,
      vscadena    VARCHAR2,
      vsreplace   VARCHAR2
    ) IS
      SELECT distinct con.clie_oid_clie AS oidcliente,
             cli.cod_clie AS codigocliente,
             TRIM(cli.val_ape1) || ' ' || TRIM(cli.val_ape2) || ' ' || TRIM(val_nom1) || ' ' ||
             TRIM(val_nom2) AS nombre,
             replace(replace(imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,cli.oid_clie),''),l_textoCP,''), l_textoObs, '') AS direccion,
             (SELECT zon.cod_zona FROM zon_zona zon WHERE zon.oid_zona = con.zzon_oid_zona) codigozona,
             (SELECT terr.cod_terr FROM zon_terri terr WHERE terr.oid_terr = con.terr_oid_terr) codigoterritorio,
             (SELECT secci.cod_secc
                FROM zon_terri_admin zta,
                     zon_secci       secci
               WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                 AND secci.oid_secc = zta.zscc_oid_secc) codigoseccion,
             ident.num_docu_iden numerodocumentoidentidad,
             translate((SELECT val_text_comu
                          FROM mae_clien_comun
                         WHERE ticm_oid_tipo_comu = 1
                           AND clie_oid_clie = cli.oid_clie) || ' ' ||
                       (SELECT val_text_comu
                          FROM mae_clien_comun
                         WHERE ticm_oid_tipo_comu = 6
                           AND clie_oid_clie = cli.oid_clie),
                       vscadena,
                       vsreplace) telefono,
             imp_pkg_proce_laser.imp_fn_es_clie_vaca(pscodigopais,
                                                     cli.oid_clie) vaca,
          case when
          (SELECT COUNT(1)
            FROM ped_pedid_chequ
           WHERE cod_clie = cli.cod_clie
             AND cod_peri = (SELECT cod_peri
                               FROM bas_ctrl_fact
                              WHERE sta_camp = 0
                                AND ind_camp_act = 1))>0 then '1'
           else '0'
           end distribucion,
           /*case mcda.niri_oid_nive_ries
           when 4 then 1
           when 5 then 2
           when 6 then 3
           when 7 then 4
           else 0
           end ciclo,*/
           /*(select z.val_barr from mae_clien_direc z where z.oid_clie_dire=imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                         cli.oid_clie)) barrio,*/
             imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie('COE',
                                                                                                         cli.oid_clie),
                                                           '') AS barrio,               nvl((select VAL_LAR
           from LAR_TIPO_CLIEN_VIP
           where OID_CLAS = (select cc.CLAS_OID_CLAS
                             from mae_clien_tipo_subti cts,
                                  mae_clien_clasi cc
                            where cts.OID_CLIE_TIPO_SUBT = cc.CTSU_OID_CLIE_TIPO_SUBT
                              and cts.CLIE_OID_CLIE = cli.oid_clie
                              and cc.TCCL_OID_TIPO_CLASI in (select OID_TIPO_CLAS
                                                              from LAR_TIPO_CLIEN_VIP
                                                             )
                              and rownum=1)),0) indicadorvip,
           (SELECT nvl(val_text,COD_ORIG_CHEQ)
            FROM ped_pedid_chequ
           WHERE cod_clie = cli.cod_clie
             AND cod_peri = (SELECT cod_peri
                               FROM bas_ctrl_fact
                              WHERE sta_camp = 0
                                AND ind_camp_act = 1)
             and val_nume_soli=con.val_nume_soli
             and rownum=1) tipo_cheq
        FROM ped_solic_cabec con,
             mae_clien       cli,
             --mae_clien_direc dir,
             mae_clien_ident ident,
             mae_clien_datos_adici mcda
       WHERE con.perd_oid_peri = vnidperiodo
         AND con.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais FROM int_lar_tipo_solici_pedido_dis)
         AND con.fec_fact = to_date(psfecha,
                                    'dd/MM/YYYY')
         AND nvl(con.ind_inte_lari_gene,
                 0) = 0
         AND con.num_unid_aten_tota > 0
         AND con.clie_oid_clie = cli.oid_clie
         AND con.clie_oid_clie = ident.clie_oid_clie
         AND con.clie_oid_clie = mcda.clie_oid_clie
         AND ident.val_iden_docu_prin = 1
         ;
    --AND con.clie_oid_clie = dir.clie_oid_clie
    --and dir.oid_clie_dire = CLDI_OID_CLIE_DIRE;

    TYPE interfazrec IS RECORD(
      oidcliente               ped_solic_cabec.clie_oid_clie%TYPE,
      codigocliente            mae_clien.cod_clie%TYPE,
      nombre                   VARCHAR2(100),
      direccion                VARCHAR2(300),
      codigozona               zon_zona.cod_zona%TYPE,
      codigoterritorio         zon_terri.cod_terr%TYPE,
      codigoseccion            zon_secci.cod_secc%TYPE,
      numerodocumentoidentidad mae_clien_ident.num_docu_iden%TYPE,
      telefono                 VARCHAR2(201),
      vaca                     NUMBER(1),
      distribucion             VARCHAR2(1),
      barrio                   VARCHAR2(500),
      vip                      VARCHAR2(1),
      tipo_cheq                VARCHAR2(2)
      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo          bas_inter.dir_temp%TYPE;
    w_filas             NUMBER := 1000;
    v_handle            utl_file.file_type;
    lslinea             VARCHAR2(1000);
    lsnombrearchivo     VARCHAR2(50);
    lstemporal          NUMBER := 0;
    lbabrirutlfile      BOOLEAN;
    lnidperiodo         NUMBER;
  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    lnidperiodo    := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psperiodo);
    OPEN c_interfaz(lnidperiodo,
                    lscadena,
                    lsreplace);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          --lsCanalDistribucion := FLAG_TODAS_LAS_DEMAS;
          /*lscanaldistribucion := '0';
          --Evaluo si es es pedido a chequear
          SELECT COUNT(1)
            INTO lstemporal
            FROM ped_pedid_chequ
           WHERE cod_clie =
                 (SELECT cod_clie FROM mae_clien WHERE oid_clie = interfazrecord(x).oidcliente)
             AND cod_peri = (SELECT cod_peri
                               FROM bas_ctrl_fact
                              WHERE sta_camp = 0
                                AND ind_camp_act = 1);
          IF lstemporal > 0 THEN
            lscanaldistribucion := '1';
          END IF;*/
          lslinea := interfazrecord(x)
                     .codigocliente || ';' || interfazrecord(x).nombre || ';' || interfazrecord(x)
                     .direccion || ';' || interfazrecord(x).codigozona || '00;' || interfazrecord(x)
                     .codigoseccion || ';' || interfazrecord(x).distribucion || ';' || interfazrecord(x)
                     .numerodocumentoidentidad || ';' || interfazrecord(x).numerodocumentoidentidad || ';' || interfazrecord(x)
                     .telefono || ';' || interfazrecord(x).codigoterritorio
                      || ';' || interfazrecord(x).vaca || ';' || interfazrecord(x).barrio || ';' || interfazrecord(x).vip || ';' || interfazrecord(x).tipo_cheq;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_maest_desti: ' || ls_sqlerrm);
  END int_pr_ape_envi_maest_desti;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Maestro
                      de cuentas (DYTCTA)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_maest_cuent
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    CURSOR c_interfaz IS
select cod_regi, cod_zona,des_zona,
                      cli.val_nom1 || ' ' || cli.val_nom2 || ' ' || cli.val_ape1 || ' ' || cli.val_ape2 NOMBRE,
                      (select dir.val_nomb_via || ' ' || dir.num_ppal || ' ' || dir.val_obse from mae_clien_direc dir where clie_oid_clie=cli.oid_clie and dir.ind_dire_ppal=1 and dir.ind_elim=0) DIRECCION,
                      (select com.val_text_comu from mae_clien_comun com where clie_oid_clie=cli.oid_clie and com.ticm_oid_tipo_comu=1) tel
,(select iden.num_docu_iden from mae_clien_ident iden where clie_oid_clie=cli.oid_clie and iden.val_iden_docu_prin=1 and rownum=1) iden
, (select des_geog from zon_valor_estru_geopo x, mae_clien_direc y where orde_1=substr(y.cod_unid_geog,1,6) and orde_2=substr(y.cod_unid_geog,7,6) and orde_3=substr(y.cod_unid_geog,13,6)
 and y.clie_oid_clie=cli.oid_clie and y.ind_dire_ppal=1 and y.ind_elim=0
and rownum=1) CIUDAD
from
(
      SELECT DISTINCT regi.cod_regi, zona.cod_zona,
                      zona.des_zona,
nvl(zona.clie_oid_clie,
         (SELECT mc.oid_clie
--DE.COD_CLIE,DE.COD_REGI, DE.COD_ZONA, CG.COD_TIPO_CARG
  FROM ZON_DIREC_VENTA_CABEC CA,
       ZON_DIREC_VENTA_DETAL DE,
       ZON_TIPO_CARGO        CG,
       MAE_CLIEN MC
 WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
   AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
   AND CA.COD_CLIE = DE.COD_CLIE
   and de.cod_clie=mc.cod_clie
   AND CA.FEC_REGI = DE.DICA_FEC_REGI
   AND CA.CAM_PROC = DE.DICA_CAM_PROC
   AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
   AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
   AND CA.EST_REGI = '1'
   AND DE.EST_REGI = '1'
   AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
   AND CG.EST_REGI = '1'
   AND CG.COD_TIPO_CARG IN ('MVZ')-- EE: INSTRUCTORAS--MVR: ALTERNO MAV DE GERENTES DE REGION-- MVZ:ALTERNO MAV DE GERENTES DE ZONA
   AND CA.ESCA_COD_ESTA_CARG = 'A'
   AND DE.COD_SUBG = '01'
   and de.cod_zona=zona.cod_zona
   )
   ) oid_clie
FROM zon_zona zona, zon_regio regi
       WHERE zona.ind_acti = 1
         and zona.zorg_oid_regi=regi.oid_regi
) tmp, mae_clien cli
where tmp.oid_clie=cli.oid_clie(+)
/*      SELECT DISTINCT regi.cod_regi, zona.cod_zona,
                      zona.des_zona,
                      cli.val_nom1 || ' ' || cli.val_nom2 || ' ' || cli.val_ape1 || ' ' || cli.val_ape2 NOMBRE,
                      (select dir.val_nomb_via || ' ' || dir.num_ppal || ' ' || dir.val_obse from mae_clien_direc dir where clie_oid_clie=cli.oid_clie and dir.ind_dire_ppal=1 and dir.ind_elim=0) DIRECCION,
                      (select com.val_text_comu from mae_clien_comun com where clie_oid_clie=cli.oid_clie and com.ticm_oid_tipo_comu=1) tel
,(select iden.num_docu_iden from mae_clien_ident iden where clie_oid_clie=cli.oid_clie and iden.val_iden_docu_prin=1 and rownum=1) iden
, (select des_geog from zon_valor_estru_geopo x, mae_clien_direc y where orde_1=substr(y.cod_unid_geog,1,6) and orde_2=substr(y.cod_unid_geog,7,6) and orde_3=substr(y.cod_unid_geog,13,6)
 and y.clie_oid_clie=cli.oid_clie and y.ind_dire_ppal=1 and y.ind_elim=0
and rownum=1) CIUDAD
        FROM zon_zona zona, zon_regio regi, mae_clien cli
       WHERE zona.ind_acti = 1
         and zona.zorg_oid_regi=regi.oid_regi
         and nvl(zona.clie_oid_clie,
         (SELECT mc.oid_clie
--DE.COD_CLIE,DE.COD_REGI, DE.COD_ZONA, CG.COD_TIPO_CARG
  FROM ZON_DIREC_VENTA_CABEC CA,
       ZON_DIREC_VENTA_DETAL DE,
       ZON_TIPO_CARGO        CG,
       MAE_CLIEN MC
 WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
   AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
   AND CA.COD_CLIE = DE.COD_CLIE
   and de.cod_clie=mc.cod_clie
   AND CA.FEC_REGI = DE.DICA_FEC_REGI
   AND CA.CAM_PROC = DE.DICA_CAM_PROC
   AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
   AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
   AND CA.EST_REGI = '1'
   AND DE.EST_REGI = '1'
   AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
   AND CG.EST_REGI = '1'
   AND CG.COD_TIPO_CARG IN ('MVZ')-- EE: INSTRUCTORAS--MVR: ALTERNO MAV DE GERENTES DE REGION-- MVZ:ALTERNO MAV DE GERENTES DE ZONA
   AND CA.ESCA_COD_ESTA_CARG = 'A'
   AND DE.COD_SUBG = '01'
   and de.cod_zona=zona.cod_zona
   )
   )
         =cli.oid_clie
         AND zona.ind_borr = 0*/
      union

      SELECT DISTINCT cod_regi, dato,
                      des_regi,
                      cli.val_nom1 || ' ' || cli.val_nom2 || ' ' || cli.val_ape1 || ' ' || cli.val_ape2 NOMBRE,
                      (select dir.val_nomb_via || ' ' || dir.num_ppal || ' ' || dir.val_obse from mae_clien_direc dir where clie_oid_clie=cli.oid_clie and dir.ind_dire_ppal=1 and dir.ind_elim=0) DIRECCION,
                      (select com.val_text_comu from mae_clien_comun com where clie_oid_clie=cli.oid_clie and com.ticm_oid_tipo_comu=1) tel
                      ,(select iden.num_docu_iden from mae_clien_ident iden where clie_oid_clie=cli.oid_clie and iden.val_iden_docu_prin=1 and rownum=1) iden
, (select des_geog from zon_valor_estru_geopo x, mae_clien_direc y where orde_1=substr(y.cod_unid_geog,1,6) and orde_2=substr(y.cod_unid_geog,7,6) and orde_3=substr(y.cod_unid_geog,13,6)
 and y.clie_oid_clie=cli.oid_clie and y.ind_dire_ppal=1 and y.ind_elim=0
and rownum=1) CIUDAD
      from
      (
      SELECT DISTINCT regi.cod_regi, 'XXXX' dato,
                      regi.des_regi,
nvl(regi.clie_oid_clie,
         (SELECT mc.oid_clie
--DE.COD_CLIE,DE.COD_REGI, DE.COD_ZONA, CG.COD_TIPO_CARG
  FROM ZON_DIREC_VENTA_CABEC CA,
       ZON_DIREC_VENTA_DETAL DE,
       ZON_TIPO_CARGO        CG,
       MAE_CLIEN MC
 WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
   AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
   AND CA.COD_CLIE = DE.COD_CLIE
   and de.cod_clie=mc.cod_clie
   AND CA.FEC_REGI = DE.DICA_FEC_REGI
   AND CA.CAM_PROC = DE.DICA_CAM_PROC
   AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
   AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
   AND CA.EST_REGI = '1'
   AND DE.EST_REGI = '1'
   AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
   AND CG.EST_REGI = '1'
   AND CG.COD_TIPO_CARG IN ('MVR')-- EE: INSTRUCTORAS--MVR: ALTERNO MAV DE GERENTES DE REGION-- MVZ:ALTERNO MAV DE GERENTES DE ZONA
   AND CA.ESCA_COD_ESTA_CARG = 'A'
   AND DE.COD_SUBG = '01'
   and de.cod_regi=regi.cod_regi
   )
   ) oid_clie
from zon_regio regi
) datos, mae_clien cli
where datos.oid_clie=cli.oid_clie(+)

/*      SELECT DISTINCT regi.cod_regi, 'XXXX',
                      regi.des_regi,
                      cli.val_nom1 || ' ' || cli.val_nom2 || ' ' || cli.val_ape1 || ' ' || cli.val_ape2 NOMBRE,
                      (select dir.val_nomb_via || ' ' || dir.num_ppal || ' ' || dir.val_obse from mae_clien_direc dir where clie_oid_clie=cli.oid_clie and dir.ind_dire_ppal=1 and dir.ind_elim=0) DIRECCION,
                      (select com.val_text_comu from mae_clien_comun com where clie_oid_clie=cli.oid_clie and com.ticm_oid_tipo_comu=1) tel
                      ,(select iden.num_docu_iden from mae_clien_ident iden where clie_oid_clie=cli.oid_clie and iden.val_iden_docu_prin=1 and rownum=1) iden
, (select des_geog from zon_valor_estru_geopo x, mae_clien_direc y where orde_1=substr(y.cod_unid_geog,1,6) and orde_2=substr(y.cod_unid_geog,7,6) and orde_3=substr(y.cod_unid_geog,13,6)
 and y.clie_oid_clie=cli.oid_clie and y.ind_dire_ppal=1 and y.ind_elim=0
and rownum=1) CIUDAD
        FROM zon_regio regi, mae_clien cli
       WHERE regi.ind_acti = 1
         and nvl(regi.clie_oid_clie,
         (SELECT mc.oid_clie
--DE.COD_CLIE,DE.COD_REGI, DE.COD_ZONA, CG.COD_TIPO_CARG
  FROM ZON_DIREC_VENTA_CABEC CA,
       ZON_DIREC_VENTA_DETAL DE,
       ZON_TIPO_CARGO        CG,
       MAE_CLIEN MC
 WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
   AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
   AND CA.COD_CLIE = DE.COD_CLIE
   and de.cod_clie=mc.cod_clie
   AND CA.FEC_REGI = DE.DICA_FEC_REGI
   AND CA.CAM_PROC = DE.DICA_CAM_PROC
   AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
   AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
   AND CA.EST_REGI = '1'
   AND DE.EST_REGI = '1'
   AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
   AND CG.EST_REGI = '1'
   AND CG.COD_TIPO_CARG IN ('MVR')-- EE: INSTRUCTORAS--MVR: ALTERNO MAV DE GERENTES DE REGION-- MVZ:ALTERNO MAV DE GERENTES DE ZONA
   AND CA.ESCA_COD_ESTA_CARG = 'A'
   AND DE.COD_SUBG = '01'
   and de.cod_regi=regi.cod_regi
   )
   )
         =cli.oid_clie*/

union

SELECT DE.COD_REGI, 'XXX1', zr.des_regi
,mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 NOMBRE
,(select dir.val_nomb_via || ' ' || dir.num_ppal || ' ' || dir.val_obse from mae_clien_direc dir where clie_oid_clie=mc.oid_clie and dir.ind_dire_ppal=1 and dir.ind_elim=0) DIRECCION
,(select com.val_text_comu from mae_clien_comun com where clie_oid_clie=mc.oid_clie and com.ticm_oid_tipo_comu=1) tel
,(select iden.num_docu_iden from mae_clien_ident iden where clie_oid_clie=mc.oid_clie and iden.val_iden_docu_prin=1 and rownum=1) iden
, (select des_geog from zon_valor_estru_geopo x, mae_clien_direc y where orde_1=substr(y.cod_unid_geog,1,6) and orde_2=substr(y.cod_unid_geog,7,6) and orde_3=substr(y.cod_unid_geog,13,6)
 and y.clie_oid_clie=mc.oid_clie and y.ind_dire_ppal=1 and y.ind_elim=0
and rownum=1) CIUDAD
  FROM ZON_DIREC_VENTA_CABEC CA,
       ZON_DIREC_VENTA_DETAL DE,
       ZON_TIPO_CARGO        CG,
       mae_clien mc, zon_regio zr
 WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
   AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
   AND CA.COD_CLIE = DE.COD_CLIE
   and de.cod_regi=zr.cod_regi
   and de.cod_clie=mc.cod_clie
   AND CA.FEC_REGI = DE.DICA_FEC_REGI
   AND CA.CAM_PROC = DE.DICA_CAM_PROC
   AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
   AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
   AND CA.EST_REGI = '1'
   AND DE.EST_REGI = '1'
   AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
   AND CG.EST_REGI = '1'
   AND CG.COD_TIPO_CARG IN ('EE')-- EE: INSTRUCTORAS--MVR: ALTERNO MAV DE GERENTES DE REGION-- MVZ:ALTERNO MAV DE GERENTES DE ZONA
   AND CA.ESCA_COD_ESTA_CARG = 'A'
   AND DE.COD_SUBG = '01'

         ;





    TYPE interfazrec IS RECORD(
      codigoregi      zon_regio.cod_regi%TYPE,
      codigozona      zon_zona.cod_zona%TYPE,
      deszona         zon_zona.des_zona%TYPE,
      nombre          VARCHAR2(200),
      direccion       VARCHAR2(200),
      telefono        VARCHAR2(200),
      iden            VARCHAR2(200),
      ciudad            VARCHAR2(200)
      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigoregi || ';' || interfazrecord(x).codigozona || ';'
          || interfazrecord(x).deszona || ';'
          || interfazrecord(x).nombre || ';'
          || interfazrecord(x).direccion || ';' || interfazrecord(x).telefono || ';' || interfazrecord(x).iden || ';' || interfazrecord(x).ciudad;

          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_maest_cuent: ' || ls_sqlerrm);
  END int_pr_ape_envi_maest_cuent;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Maestro
                      de territorios (DYTSCT)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_maest_terri
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2,
    pslongitudorden1 VARCHAR2,
    pslongitudorden2 VARCHAR2,
    pslongitudorden3 VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT DISTINCT zona.cod_zona,
                      secci.cod_secc,
                      terri.cod_terr,
                      terri.cod_terr descripcion,
                      (SELECT substr(orde_1,
                                     length(orde_1) - pslongitudorden1 + 1,
                                     pslongitudorden1)
                         FROM zon_valor_estru_geopo
                        WHERE oid_valo_estr_geop = terri.vepo_oid_valo_estr_geop) ||
                      (SELECT substr(orde_2,
                                     length(orde_1) - pslongitudorden2 + 1,
                                     pslongitudorden2)
                         FROM zon_valor_estru_geopo
                        WHERE oid_valo_estr_geop = terri.vepo_oid_valo_estr_geop) ||
                      (SELECT substr(orde_3,
                                     length(orde_1) - pslongitudorden3 + 1,
                                     pslongitudorden3)
                         FROM zon_valor_estru_geopo
                        WHERE oid_valo_estr_geop = terri.vepo_oid_valo_estr_geop) ubigeo
        FROM zon_terri_admin terri_admin,
             zon_terri       terri,
             zon_secci       secci,
             zon_zona        zona
       WHERE terri_admin.terr_oid_terr = terri.oid_terr
         AND terri_admin.zscc_oid_secc = secci.oid_secc
         AND secci.zzon_oid_zona = zona.oid_zona
         AND terri.ind_borr = 0
         AND terri_Admin.ind_borr = 0
         and zona.ind_Acti = 1;

    TYPE interfazrec IS RECORD(
      codigozona            zon_zona.cod_zona%TYPE,
      codigoseccion         zon_secci.cod_secc%TYPE,
      codigoterritorio      zon_terri.cod_terr%TYPE,
      descripcionterritorio zon_terri.cod_terr%TYPE,
      ubigeo                VARCHAR2(18));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigozona || '00;' || interfazrecord(x).codigoseccion || ';' || interfazrecord(x)
                     .descripcionterritorio || ';' || interfazrecord(x).ubigeo || ';' || interfazrecord(x)
                     .codigoterritorio;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_maest_terri: ' || ls_sqlerrm);
  END int_pr_ape_envi_maest_terri;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Secuencia
                      de cuentas (DYTSCX)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_secue_cuent
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT DISTINCT zona.cod_zona,
                      trans.num_secu,
                      '01' linea
        FROM ped_solic_cabec con,
             zon_zona        zona,
             app_rutas_trans trans
       WHERE con.perd_oid_peri = gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psperiodo)
         AND con.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais FROM int_lar_tipo_solici_pedido_dis)
         AND con.fec_fact = to_date(psfecha,
                                    'dd/MM/YYYY')
         AND con.num_unid_aten_tota > 0
         AND con.zzon_oid_zona = zona.oid_zona
         AND zona.cod_zona = trans.cod_ruta(+);

    TYPE interfazrec IS RECORD(
      codigozona      zon_zona.cod_zona%TYPE,
      numerosecuencia app_rutas_trans.num_secu%TYPE,
      linea           VARCHAR2(2));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigozona || '00;' || interfazrecord(x).numerosecuencia || ';' || interfazrecord(x)
                     .linea;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_secue_cuent: ' || ls_sqlerrm);
  END int_pr_ape_envi_secue_cuent;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Cronograma
                      de operaciones (DYTCRO)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_crono_opera
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    CURSOR c_interfaz
    (
      vnidperiodo       NUMBER,
      vnidperiodonuevas NUMBER
      , p_actividadConf VARCHAR2
      , p_actividadDesp VARCHAR2
      , p_actividadRepa VARCHAR2
    ) IS
      SELECT DISTINCT b.cod_peri,
                      zona.cod_zona,
                      (SELECT fec_inic
                         FROM cra_crono
                        WHERE perd_oid_peri = a.oid_peri
                          AND zzon_oid_zona = zona.oid_zona
                          AND cact_oid_acti = (SELECT oid_acti
                                                 FROM cra_activ
                                                WHERE pais_oid_pais =
                                                      gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais)
                                                  AND cod_acti = 'FA')) fecha_facturacion,
                      (SELECT fec_inic
                         FROM cra_crono
                        WHERE perd_oid_peri = a.oid_peri
                          AND zzon_oid_zona = zona.oid_zona
                          AND cact_oid_acti = (SELECT oid_acti
                                                 FROM cra_activ
                                                WHERE pais_oid_pais =
                                                      gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais)
                                                  AND cod_acti = p_actividadConf)) fecha_reunion,
                      (SELECT fec_inic
                         FROM cra_crono
                        WHERE perd_oid_peri = a.oid_peri
                          AND zzon_oid_zona = zona.oid_zona
                          AND cact_oid_acti = (SELECT oid_acti
                                                 FROM cra_activ
                                                WHERE pais_oid_pais =
                                                      gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais)
                                                  AND cod_acti = p_actividadDesp)) fecha_despacho,
                      (SELECT fec_inic
                         FROM cra_crono
                        WHERE perd_oid_peri = a.oid_peri
                          AND zzon_oid_zona = zona.oid_zona
                          AND cact_oid_acti = (SELECT oid_acti
                                                 FROM cra_activ
                                                WHERE pais_oid_pais =
                                                      gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais)
                                                  AND cod_acti = p_actividadRepa)) fecha_reparto
        FROM cra_perio       a,
             seg_perio_corpo b,
             ped_solic_cabec con,
             zon_zona        zona
       WHERE con.zzon_oid_zona = zona.oid_zona
         AND con.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais FROM int_lar_tipo_solici_pedido_dis)
         AND con.fec_fact = to_date(psfecha,
                                    'dd/MM/YYYY')
         AND nvl(con.ind_inte_lari_gene,
                 0) = 0
         AND con.num_unid_aten_tota > 0
         AND a.oid_peri IN (vnidperiodo,
                            vnidperiodonuevas)
         AND a.peri_oid_peri = b.oid_peri
       ORDER BY b.cod_peri,
                zona.cod_zona;

    TYPE interfazrec IS RECORD(
      codigoperiodo    seg_perio_corpo.cod_peri%TYPE,
      codigozona       zon_zona.cod_zona%TYPE,
      fechafacturacion cra_crono.fec_inic%TYPE,
      fechareunion     cra_crono.fec_inic%TYPE,
      fecha_despacho   cra_crono.fec_inic%TYPE,
      fecha_reparto    cra_crono.fec_inic%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo        bas_inter.dir_temp%TYPE;
    w_filas           NUMBER := 1000;
    v_handle          utl_file.file_type;
    lslinea           VARCHAR2(1000);
    lsnombrearchivo   VARCHAR2(50);
    lbabrirutlfile    BOOLEAN;
    lnidperiodo       NUMBER;
    lnidperiodonuevas NUMBER;

lv_actividadConf VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadConf'),'CV');

lv_actividadDesp VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadDesp'),'DP');

lv_actividadRepa VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadRepa'),'RP');


  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile    := TRUE;
    lnidperiodo       := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psperiodo);
    lnidperiodonuevas := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psperiodo,
                                                                                                                1));
    OPEN c_interfaz(lnidperiodo,
                    lnidperiodonuevas, lv_actividadConf, lv_actividadDesp, lv_actividadRepa);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigozona || '00' || ';' || interfazrecord(x).codigoperiodo || ';' ||
                      to_char(to_date(interfazrecord(x).fechafacturacion,
                                      'DD/MM/YYYY'),
                              'YYMMDD') || ';' || to_char(to_date(interfazrecord(x).fechareunion,
                                                                  'DD/MM/YYYY'),
                                                          'YYMMDD') || ';' ||
                      to_char(to_date(interfazrecord(x).fecha_despacho,
                                      'DD/MM/YYYY'),
                              'YYMMDD') || ';' || to_char(to_date(interfazrecord(x).fecha_reparto,
                                                                  'DD/MM/YYYY'),
                                                          'YYMMDD');
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_crono_opera: ' || ls_sqlerrm);
  END int_pr_ape_envi_crono_opera;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Control
                      de proceso (DYTCTR)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_contr_proce
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfecha          VARCHAR2,
    psperiodo        VARCHAR2,
    pstipoproceso    VARCHAR2
  ) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    /* Procedimiento inicial para generar interfaz */
    IF lbabrirutlfile THEN
      gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                             pscodigosistema,
                                             pscodigointerfaz,
                                             psnombrearchivo,
                                             lsdirtempo,
                                             lsnombrearchivo,
                                             v_handle);
      lbabrirutlfile := FALSE;
    END IF;
    lslinea := to_char(to_date(psfecha,
                               'DD/MM/YYYY'),
                       'YYYYMMDD') || ';' || psperiodo || ';' || pstipoproceso;
    utl_file.put_line(v_handle,
                      lslinea);

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_contr_proce: ' || ls_sqlerrm);
  END int_pr_ape_envi_contr_proce;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Matriz
                      de campa;a (ANQPRD0)
  Fecha Creacion    : 25/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_matri_campa
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT e.cod_peri,
             decode(substr(b.cod_sap,
                           0,
                           2),
                    'P0',
                    b.cod_sap,
                    b.cod_sap),
             c.val_i18n,
             SUM(nvl(a.num_unid_esti,
                     0))
        FROM ape_estim_produ    a,
             mae_produ          b,
             gen_i18n_sicc_pais c,
             cra_perio          d,
             seg_perio_corpo    e
       WHERE a.prod_oid_prod = b.oid_prod
         AND b.oid_prod = c.val_oid
         AND c.attr_enti = 'MAE_PRODU'
         AND a.perd_oid_peri = d.oid_peri
         AND d.peri_oid_peri = e.oid_peri
         AND e.cod_peri = psperiodo
       GROUP BY e.cod_peri,
                b.cod_sap,
                c.val_i18n
      UNION
      SELECT j.cod_peri,
             decode(substr(b.cod_sap,
                           0,
                           2),
                    'P0',
                    b.cod_sap,
                    b.cod_sap),
             c.val_i18n,
             SUM(decode(g.cod_orig,
                        'MAV',
                        nvl(int_fn_obtie_estim_mav(pscodigopais,g.prod_oid_prod,g.tofe_oid_tipo_ofer,h.perd_oid_peri)/*(SELECT SUM(val_base_esti_dest * num_unid_clie)
                              FROM mav_detal_mav
                             WHERE tepr_oid_tipo_esta_proc <> 5
                               AND prod_oid_prod = g.prod_oid_prod
                               AND tofe_oid_tipo_ofer = g.tofe_oid_tipo_ofer
                               AND cod_orig = 'MAV'
                               AND perd_oid_peri = h.perd_oid_peri)*/,
                            0),
                        nvl(g.num_unid_esti,
                            0))) xx
        FROM pre_ofert             f,
             pre_ofert_detal       g,
             pre_matri_factu_cabec h,
             cra_perio             i,
             seg_perio_corpo       j,
             mae_produ             b,
             gen_i18n_sicc_pais    c
       WHERE f.oid_ofer = g.ofer_oid_ofer
         AND f.mfca_oid_cabe = h.oid_cabe
         AND h.perd_oid_peri = i.oid_peri
         AND i.peri_oid_peri = j.oid_peri
         AND g.prod_oid_prod = b.oid_prod
         AND b.oid_prod = c.val_oid
         AND c.attr_enti = 'MAE_PRODU'
         AND j.cod_peri = psperiodo
         AND g.prod_oid_prod NOT IN (SELECT b.oid_prod
                                       FROM ape_estim_produ a,
                                            mae_produ       b,
                                            cra_perio       d,
                                            seg_perio_corpo e
                                      WHERE a.prod_oid_prod = b.oid_prod
                                        AND a.perd_oid_peri = d.oid_peri
                                        AND d.peri_oid_peri = e.oid_peri
                                        AND e.cod_peri = psperiodo)
         and not exists (select 1 from mav_param_confi x1
         where x1.cam_para_mav=j.cod_peri and x1.val_codi_vent=g.val_codi_vent and x1.ticl_oid_tipo_clie=4
         )
         AND g.oid_deta_ofer IN (SELECT OID
                                   FROM (SELECT g.prod_oid_prod,
                                                g.tofe_oid_tipo_ofer,
                                                MAX(g.oid_deta_ofer) OID
                                           FROM pre_ofert             f,
                                                pre_ofert_detal       g,
                                                pre_matri_factu_cabec h,
                                                cra_perio             i,
                                                seg_perio_corpo       j,
                                                mae_produ             b,
                                                gen_i18n_sicc_pais    c
                                          WHERE f.oid_ofer = g.ofer_oid_ofer
                                            AND f.mfca_oid_cabe = h.oid_cabe
                                            AND h.perd_oid_peri = i.oid_peri
                                            AND i.peri_oid_peri = j.oid_peri
                                            AND g.prod_oid_prod = b.oid_prod
                                            AND b.oid_prod = c.val_oid
                                            AND c.attr_enti = 'MAE_PRODU'
                                            AND j.cod_peri = psperiodo
                                            AND g.prod_oid_prod NOT IN
                                                (SELECT b.oid_prod
                                                   FROM ape_estim_produ a,
                                                        mae_produ       b,
                                                        cra_perio       d,
                                                        seg_perio_corpo e
                                                  WHERE a.prod_oid_prod = b.oid_prod
                                                    AND a.perd_oid_peri = d.oid_peri
                                                    AND d.peri_oid_peri = e.oid_peri
                                                    AND e.cod_peri = psperiodo)
                                          GROUP BY g.prod_oid_prod,
                                                   g.tofe_oid_tipo_ofer))
       GROUP BY j.cod_peri,
                b.cod_sap,
                c.val_i18n /*select j.COD_PERI,
                       decode(substr(b.COD_SAP,0,2),'P0', b.COD_SAP,b.COD_SAP),
                       c.VAL_I18N,sum(decode(g.COD_ORIG,'MAV',nvl((select sum(VAL_BASE_ESTI_DEST * NUM_UNID_CLIE) from mav_detal_mav where prod_oid_prod=g.prod_oid_prod and tofe_oid_tipo_ofer=g.tofe_oid_tipo_ofer and cod_orig='MAV' and perd_oid_peri=h.perd_oid_peri),0),nvl(g.NUM_UNID_ESTI,0)))
                from pre_ofert f, pre_ofert_detal g, pre_matri_factu_cabec h, cra_perio i,
                seg_perio_corpo j, mae_produ b, gen_i18n_sicc_pais c
                where f.OID_OFER=g.OFER_OID_OFER and f.MFCA_OID_CABE=h.OID_CABE
                and h.PERD_OID_PERI=i.OID_PERI and i.PERI_OID_PERI=j.OID_PERI
                and g.PROD_OID_PROD=b.OID_PROD and b.OID_PROD=c.VAL_OID and
                c.ATTR_ENTI='MAE_PRODU'
                and j.COD_PERI=psperiodo
                and g.PROD_OID_PROD not in (select b.OID_PROD
                                            from ape_estim_produ a, mae_produ b, cra_perio d, seg_perio_corpo e
                                            where a.PROD_OID_PROD=b.OID_PROD
                                            and a.PERD_OID_PERI=d.OID_PERI
                                            and d.PERI_OID_PERI=e.OID_PERI
                                            and e.COD_PERI=psperiodo
                                            )
                group by j.COD_PERI,b.COD_SAP,c.VAL_I18N*/

      --- Se incluye los productos de la matriz anterior
      UNION
      SELECT psperiodo,
             decode(substr(b.cod_sap,
                           0,
                           2),
                    'P0',
                    b.cod_sap,
                    b.cod_sap),
             c.val_i18n,
             SUM(0) xx
        FROM pre_ofert             f,
             pre_ofert_detal       g,
             pre_matri_factu_cabec h,
             cra_perio             i,
             seg_perio_corpo       j,
             mae_produ             b,
             gen_i18n_sicc_pais    c
       WHERE f.oid_ofer = g.ofer_oid_ofer
         AND f.mfca_oid_cabe = h.oid_cabe
         AND h.perd_oid_peri = i.oid_peri
         AND i.peri_oid_peri = j.oid_peri
         AND g.prod_oid_prod = b.oid_prod
         AND b.oid_prod = c.val_oid
         AND c.attr_enti = 'MAE_PRODU'
         AND j.cod_peri = gen_fn_calcu_perio(psperiodo,
                                             -1)
         AND EXISTS
       (SELECT 1
                FROM sto_param_gener_occrr
               WHERE cod_para = 'APE_MATRI_ANTER'
                 AND cod_pais = pscodigopais
                 AND val_param = 'S')
         AND g.prod_oid_prod NOT IN
             (SELECT b.oid_prod
                FROM ape_estim_produ a,
                     mae_produ       b,
                     cra_perio       d,
                     seg_perio_corpo e
               WHERE a.prod_oid_prod = b.oid_prod
                 AND a.perd_oid_peri = d.oid_peri
                 AND d.peri_oid_peri = e.oid_peri
                 AND e.cod_peri = gen_fn_calcu_perio(psperiodo,
                                                     -1))
         AND g.prod_oid_prod NOT IN
             (SELECT DISTINCT g.prod_oid_prod
                FROM pre_ofert             f,
                     pre_ofert_detal       g,
                     pre_matri_factu_cabec h,
                     cra_perio             i,
                     seg_perio_corpo       j,
                     mae_produ             b,
                     gen_i18n_sicc_pais    c
               WHERE f.oid_ofer = g.ofer_oid_ofer
                 AND f.mfca_oid_cabe = h.oid_cabe
                 AND h.perd_oid_peri = i.oid_peri
                 AND i.peri_oid_peri = j.oid_peri
                 AND g.prod_oid_prod = b.oid_prod
                 AND b.oid_prod = c.val_oid
                 AND c.attr_enti = 'MAE_PRODU'
                 AND j.cod_peri = psperiodo
                 AND g.prod_oid_prod NOT IN (SELECT b.oid_prod
                                               FROM ape_estim_produ a,
                                                    mae_produ       b,
                                                    cra_perio       d,
                                                    seg_perio_corpo e
                                              WHERE a.prod_oid_prod = b.oid_prod
                                                AND a.perd_oid_peri = d.oid_peri
                                                AND d.peri_oid_peri = e.oid_peri
                                                AND e.cod_peri = psperiodo))
         AND g.oid_deta_ofer IN
             (SELECT OID
                FROM (SELECT g.prod_oid_prod,
                             g.tofe_oid_tipo_ofer,
                             MAX(g.oid_deta_ofer) OID
                        FROM pre_ofert             f,
                             pre_ofert_detal       g,
                             pre_matri_factu_cabec h,
                             cra_perio             i,
                             seg_perio_corpo       j,
                             mae_produ             b,
                             gen_i18n_sicc_pais    c
                       WHERE f.oid_ofer = g.ofer_oid_ofer
                         AND f.mfca_oid_cabe = h.oid_cabe
                         AND h.perd_oid_peri = i.oid_peri
                         AND i.peri_oid_peri = j.oid_peri
                         AND g.prod_oid_prod = b.oid_prod
                         AND b.oid_prod = c.val_oid
                         AND c.attr_enti = 'MAE_PRODU'
                         AND j.cod_peri = gen_fn_calcu_perio(psperiodo,
                                                             -1)
                         AND g.prod_oid_prod NOT IN
                             (SELECT b.oid_prod
                                FROM ape_estim_produ a,
                                     mae_produ       b,
                                     cra_perio       d,
                                     seg_perio_corpo e
                               WHERE a.prod_oid_prod = b.oid_prod
                                 AND a.perd_oid_peri = d.oid_peri
                                 AND d.peri_oid_peri = e.oid_peri
                                 AND e.cod_peri = gen_fn_calcu_perio(psperiodo,
                                                                     -1))
                       GROUP BY g.prod_oid_prod,
                                g.tofe_oid_tipo_ofer))
       GROUP BY j.cod_peri,
                b.cod_sap,
                c.val_i18n
      -----
      UNION
SELECT DISTINCT psperiodo, -- Periodo de proceso --
                             decode(substr(p.cod_sap,
                                    0,
                                    2),
                             'P0',
                             p.cod_sap,
                             p.cod_sap),
                      (SELECT i.val_i18n
                         FROM gen_i18n_sicc i
                        WHERE i.idio_oid_idio = 1
                          AND i.attr_enti = 'MAE_PRODU'
                          AND i.val_oid = p.oid_prod) AS des_producto,
                      0 num_unid_estim
        FROM inc_concu_param_gener cpg,
             inc_versi_concu       vc,
             inc_param_gener_premi pgp,
             inc_param_nivel_premi pnp,
             inc_premi_artic       pa,
             inc_lote_premi_artic  lpa,
             inc_artic_lote        al,
             mae_produ             p,
             cra_perio             cp1,
             seg_perio_corpo       spc1,
             cra_perio             cp2,
             seg_perio_corpo       spc2
       WHERE cpg.oid_para_gral = pgp.copa_oid_para_gral
         AND cpg.oid_para_gral = vc.copa_oid_para_gral
         AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem(+)
         AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem(+)
         AND pa.oid_prem_arti = lpa.prar_oid_prem_arti(+)
         AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti(+)
         AND al.prod_oid_prod = p.oid_prod(+)
         AND pgp.perd_oid_peri = cp1.oid_peri(+)
         AND cp1.peri_oid_peri = spc1.oid_peri(+)
         AND pgp.perd_oid_peri_inic = cp2.oid_peri(+)
         AND cp2.peri_oid_peri = spc2.oid_peri(+)
         AND ((pgp.perd_oid_peri IS NOT NULL AND psperiodo BETWEEN spc2.cod_peri AND LEAST(spc1.cod_peri, psperiodo)) OR
                 (pgp.perd_oid_peri IS NULL AND cpg.ind_acti = 1))                     
         AND ((cpg.ind_acti = 1 
                            ) OR
             (cpg.ind_acti = 0 AND vc.vico_oid_vige_conc = 6))

         UNION

         SELECT DISTINCT 
                      psperiodo, -- Periodo de proceso --
                      decode(substr(p.cod_sap,
                                    0,
                                    2),
                             'P0',
                             p.cod_sap,
                             p.cod_sap),
                      (SELECT i.val_i18n
                         FROM gen_i18n_sicc i
                        WHERE i.idio_oid_idio = 1
                          AND i.attr_enti = 'MAE_PRODU'
                          AND i.val_oid = p.oid_prod) AS des_producto,
                      0 num_unid_estim
        FROM inc_concu_param_gener cpg,
             inc_versi_concu       vc,
             inc_param_gener_premi pgp,
             inc_param_nivel_premi pnp,
             inc_premi_artic       pa,
             inc_lote_premi_artic  lpa,
             inc_artic_lote        al,
             mae_produ             p,
             cra_perio             cp1,
             seg_perio_corpo       spc1,
             cra_perio             cp2,
             seg_perio_corpo       spc2,
             inc_reemp_artic_lote ral
       WHERE cpg.oid_para_gral = pgp.copa_oid_para_gral
         AND cpg.oid_para_gral = vc.copa_oid_para_gral
         AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem(+)
         AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem(+)
         AND pa.oid_prem_arti = lpa.prar_oid_prem_arti(+)
         AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti(+)
         AND al.oid_arti_lote = ral.arlo_oid_arti_lote
         AND ral.prod_oid_prod = p.oid_prod(+)
         AND pgp.perd_oid_peri = cp1.oid_peri(+)
         AND cp1.peri_oid_peri = spc1.oid_peri(+)
         AND pgp.perd_oid_peri_inic = cp2.oid_peri(+)
         AND cp2.peri_oid_peri = spc2.oid_peri(+)
         AND ((pgp.perd_oid_peri IS NOT NULL AND psperiodo BETWEEN spc2.cod_peri AND LEAST(spc1.cod_peri, psperiodo)) OR
                 (pgp.perd_oid_peri IS NULL AND cpg.ind_acti = 1))             
         AND ((cpg.ind_acti = 1 
             ) OR
             (cpg.ind_acti = 0 AND vc.vico_oid_vige_conc = 6));



    TYPE interfazrec IS RECORD(
      codigoperiodo       seg_perio_corpo.cod_peri%TYPE,
      codigosap           mae_produ.cod_sap%TYPE,
      descripcionproducto gen_i18n_sicc_pais.val_i18n%TYPE,
      unidadesestimadas   ape_estim_produ.num_unid_esti%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := substr(interfazrecord(x).codigoperiodo,
                            3,
                            4) || ';' || interfazrecord(x).codigosap || ';' || interfazrecord(x)
                    .descripcionproducto || ';' || interfazrecord(x).unidadesestimadas || ';0;E';
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_matri_campa: ' || ls_sqlerrm);
  END int_pr_ape_envi_matri_campa;


  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de informacion de Matriz con DA
                      de campa;a (ANQPRD0)
  Fecha Creacion    : 06/06/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_matri_campa_da
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    CURSOR c_interfaz IS

    select cod_peri, decode(substr(cod_sap,
                           0,
                           2),
                    'P0',
                    cod_sap,
                    cod_sap), des_sap, sum(num_unid)
    from ped_deman_antic where cod_peri=psperiodo
    group by cod_peri, decode(substr(cod_sap,
                                    0,
                                    2),
                             'P0',
                    cod_sap,
                    cod_sap), des_sap;



    TYPE interfazrec IS RECORD(
      codigoperiodo       seg_perio_corpo.cod_peri%TYPE,
      codigosap           mae_produ.cod_sap%TYPE,
      descripcionproducto gen_i18n_sicc_pais.val_i18n%TYPE,
      unidadesestimadas   ape_estim_produ.num_unid_esti%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := substr(interfazrecord(x).codigoperiodo,
                            3,
                            4) || ';' || interfazrecord(x).codigosap || ';' || interfazrecord(x)
                    .descripcionproducto || ';' || interfazrecord(x).unidadesestimadas || ';0;E';
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_matri_campa_da: ' || ls_sqlerrm);
  END int_pr_ape_envi_matri_campa_da;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de informacion de
                      de Anaqueles (ANQPRD0)
  Fecha Creacion    : 26/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_recep_anaqu
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

    TYPE t_cod_peri IS TABLE OF int_ape_matri_campa_anaqu.cod_peri%TYPE;
    TYPE t_cod_item IS TABLE OF int_ape_matri_campa_anaqu.cod_item%TYPE;
    TYPE t_des_item IS TABLE OF int_ape_matri_campa_anaqu.des_item%TYPE;
    TYPE t_tot_unid IS TABLE OF int_ape_matri_campa_anaqu.tot_unid%TYPE;
    TYPE t_val_anaq IS TABLE OF int_ape_matri_campa_anaqu.val_anaq%TYPE;
    TYPE t_ind_styl IS TABLE OF int_ape_matri_campa_anaqu.ind_styl%TYPE;

    v_cod_peri t_cod_peri := t_cod_peri();
    v_cod_item t_cod_item := t_cod_item();
    v_des_item t_des_item := t_des_item();
    v_tot_unid t_tot_unid := t_tot_unid();
    v_val_anaq t_val_anaq := t_val_anaq();
    v_ind_styl t_ind_styl := t_ind_styl();

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    /* Variables de parametros */

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;

  BEGIN

    --EXECUTE IMMEDIATE 'TRUNCATE TABLE int_solic_cabec';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_APE_MATRI_CAMPA_ANAQU';

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
          utl_file.get_line(v_handle,
                            lslinea);
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
                  v_cod_peri.extend;
                  v_cod_peri(i) := TRIM(substr(lslinea,
                                               inicio,
                                               longitud));
                ELSIF (posicion = 2) THEN
                  v_cod_item.extend;
                  v_cod_item(i) := TRIM(substr(lslinea,
                                               inicio,
                                               longitud));
                ELSIF (posicion = 3) THEN
                  v_des_item.extend;
                  v_des_item(i) := TRIM(substr(lslinea,
                                               inicio,
                                               longitud));
                ELSIF (posicion = 4) THEN
                  v_tot_unid.extend;
                  v_tot_unid(i) := TRIM(substr(lslinea,
                                               inicio,
                                               longitud));
                ELSIF (posicion = 5) THEN
                  v_val_anaq.extend;
                  v_val_anaq(i) := TRIM(substr(lslinea,
                                               inicio,
                                               longitud));
                ELSIF (posicion = 6) THEN
                  v_ind_styl.extend;
                  v_ind_styl(i) := TRIM(substr(lslinea,
                                               inicio,
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
    FORALL i IN 1 .. v_cod_peri.count
      INSERT INTO int_ape_matri_campa_anaqu
        (cod_peri,
         cod_item,
         des_item,
         tot_unid,
         val_anaq,
         ind_styl)
      VALUES
        (v_cod_peri(i),
         v_cod_item(i),
         v_des_item(i),
         v_tot_unid(i),
         v_val_anaq(i),
         v_ind_styl(i));

    -- Bulk bind of data in memory table...

    UPDATE mae_produ
       SET cod_ind_dent_caja = 'F',
           val_atri_1        = 'F'
     WHERE val_atri_1 IS NULL;

    FORALL i IN 1 .. v_cod_peri.count
      UPDATE mae_produ
         SET cod_ind_dent_caja = decode(nvl(substr(v_val_anaq(i),
                                                   0,
                                                   1),
                                            'X'),
                                        'X',
                                        'F',
                                        'C'),
             val_atri_1        = decode(nvl(substr(v_val_anaq(i),
                                                   0,
                                                   1),
                                            'X'),
                                        'X',
                                        'F',
                                        'C')
       WHERE cod_sap = v_cod_item(i);

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_recep_anaqu: ' || psnombrearchivo || '**' ||
                              ls_sqlerrm);

  END int_pr_ape_recep_anaqu;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de informacion
                      de Chequeo (DYTCHK)
  Fecha Creacion    : 26/03/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ape_recep_chequ
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

    TYPE t_cod_peri IS TABLE OF int_ape_pedid_revis_chequ.cod_peri%TYPE;
    TYPE t_cod_item IS TABLE OF int_ape_pedid_revis_chequ.cod_item%TYPE;
    TYPE t_num_pedi IS TABLE OF int_ape_pedid_revis_chequ.num_pedi%TYPE;
    TYPE t_cod_clie IS TABLE OF int_ape_pedid_revis_chequ.cod_clie%TYPE;

    v_cod_peri t_cod_peri := t_cod_peri();
    v_cod_item t_cod_item := t_cod_item();
    v_num_pedi t_num_pedi := t_num_pedi();
    v_cod_clie t_cod_clie := t_cod_clie();

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    /* Variables de parametros */

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;

  BEGIN

    --EXECUTE IMMEDIATE 'TRUNCATE TABLE int_solic_cabec';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_APE_PEDID_REVIS_CHEQU';

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
          utl_file.get_line(v_handle,
                            lslinea);
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
                  v_cod_peri.extend;
                  v_cod_peri(i) := TRIM(substr(lslinea,
                                               inicio,
                                               longitud));
                ELSIF (posicion = 2) THEN
                  v_cod_item.extend;
                  v_cod_item(i) := TRIM(substr(lslinea,
                                               inicio,
                                               longitud));
                ELSIF (posicion = 3) THEN
                  v_num_pedi.extend;
                  v_num_pedi(i) := TRIM(substr(lslinea,
                                               inicio,
                                               longitud));
                ELSIF (posicion = 4) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(substr(lslinea,
                                               inicio,
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
    FORALL i IN 1 .. v_cod_peri.count
      INSERT INTO int_ape_pedid_revis_chequ
        (cod_peri,
         cod_item,
         num_pedi,
         cod_clie)
      VALUES
        (v_cod_peri(i),
         v_cod_item(i),
         v_num_pedi(i),
         v_cod_clie(i));

    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_peri.count
      UPDATE ped_solic_cabec
         SET recq_oid_resu_cheq =
             (SELECT oid_resu_cheq FROM rec_resul_chequ WHERE cod_resu_cheq = 'OK'),
             inre_oid_indi_revi = 2
       WHERE val_nume_soli = v_num_pedi(i);

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_recep_chequ: ' || psnombrearchivo || '**' ||
                              ls_sqlerrm);

  END int_pr_ape_recep_chequ;

  /**************************************************************************
  Descripcion        :       APE_PR_CARGA_RUTAS_CLIEN
                     Carga la tabla APP_RUTAS_CLIEN
  Fecha Creacion     : 28/04/2009
  Parametros Entrada:
           psOidPais: Codigo Programa
           psCodigoPais: Codigo Pais
           psFechaProceso: Fecha de proceso
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ape_pr_carga_rutas_clien
  (
    psoidpais      VARCHAR2,
    pscodigopais   VARCHAR2,
    psfechaproceso VARCHAR2
  ) IS
  BEGIN

    --EXECUTE IMMEDIATE('TRUNCATE TABLE app_rutas_clien');
    DELETE FROM app_rutas_clien;

    INSERT INTO app_rutas_clien
      (oid_app_ruta_clie,
       val_nume_secu,
       clie_oid_clie,
       pais_oid_pais,
       rutr_oid_ruta_tran)
      (SELECT app_aprc_seq.nextval,
              rownum,
              x.oidcliente,
              psoidpais,
              x.oid_ruta
         FROM (SELECT rownum                  nume_secu,
                      cli.oid_clie            AS oidcliente,
                      rut_trans.oid_ruta_tran oid_ruta
                 FROM mae_clien cli,
                      mae_clien_datos_adici adici,
                      mae_clien_unida_admin uad,
                      zon_terri_admin zta,
                      zon_secci sec,
                      zon_terri terr,
                      zon_zona zon,
                      app_rutas_trans rut_trans,
                      (SELECT DISTINCT clie_oid_clie
                         FROM ped_solic_cabec
                        WHERE perd_oid_peri =
                              (SELECT x.oid_peri
                                 FROM cra_perio       x,
                                      seg_perio_corpo y
                                WHERE x.peri_oid_peri = y.oid_peri
                                  AND y.cod_peri = (SELECT cod_peri
                                                      FROM bas_ctrl_fact z
                                                     WHERE z.ind_camp_act = 1
                                                       AND z.sta_camp = 0))
                          AND grpr_oid_grup_proc = 4) clien
                WHERE cli.oid_clie = adici.clie_oid_clie
                  AND cli.oid_clie = uad.clie_oid_clie
                  AND uad.ztad_oid_terr_admi = zta.oid_terr_admi
                  AND zta.terr_oid_terr = terr.oid_terr
                  AND zta.zscc_oid_secc = sec.oid_secc
                  AND sec.zzon_oid_zona = zon.oid_zona
                  AND rut_trans.cod_ruta = zon.cod_zona
                  AND 1 = adici.ind_acti
                  AND 1 = uad.ind_acti
                  AND uad.perd_oid_peri_fin IS NULL
                  AND adici.clie_oid_clie = uad.clie_oid_clie
                  AND adici.ind_acti = uad.ind_acti
                  AND cli.oid_clie = clien.clie_oid_clie
                ORDER BY rut_trans.num_secu,
                         sec.cod_secc,
                         cod_terr,
                         ape_pkg_gener.ape_fn_obten_tipo_consu(pscodigopais,
                                                               to_date(psfechaproceso,
                                                                       'DD/MM/YYYY'),
                                                               cli.oid_clie), --tipo_consultora,
                         cli.cod_clie /*codigocliente*/
               ) x);

    UPDATE mae_produ SET cod_ind_dent_caja = val_atri_1;

    UPDATE mae_produ
       SET cod_ind_dent_caja = 'F'
     WHERE oid_prod IN (SELECT DISTINCT b.prod_oid_prod
                          FROM ped_solic_cabec     a,
                               ped_solic_posic     b,
                               ped_tipo_solic_pais e,
                               ped_tipo_solic      f,
                               mae_produ           g
                         WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                           AND a.perd_oid_peri =
                               (SELECT c.oid_peri
                                  FROM cra_perio       c,
                                       seg_perio_corpo d
                                 WHERE c.peri_oid_peri = d.oid_peri
                                   AND d.cod_peri = (SELECT cod_peri
                                                       FROM bas_ctrl_fact b
                                                      WHERE b.sta_camp = 0
                                                        AND b.ind_camp_act = 1))
                           AND a.tspa_oid_tipo_soli_pais = e.oid_tipo_soli_pais
                           AND e.tsol_oid_tipo_soli = f.oid_tipo_soli
                           AND nvl(f.ind_soli_nega,
                                   0) = 0
                           AND b.prod_oid_prod = g.oid_prod
                           AND a.grpr_oid_grup_proc = 4
                           AND g.cod_ind_dent_caja IS NULL);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR APE_PR_CARGA_RUTAS_CLIEN: ' || ls_sqlerrm);
  END ape_pr_carga_rutas_clien;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio Costo por Caja a SAT
  Fecha Creacion    : 01/09/2010
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_costo_caja
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfechafactura   VARCHAR2
  ) IS
    CURSOR c_interfaz
    (
      vscodmarca VARCHAR2,
      vscodpais  VARCHAR2
    ) IS
      SELECT vscodpais codigopais,
             vscodmarca codigomarca,
             CASE
               WHEN (SELECT COUNT(1)
                       FROM ped_solic_cabec con
                      WHERE con.perd_oid_peri = psc.perd_oid_peri
                        AND con.fec_fact = psc.fec_fact
                        AND EXISTS
                      (SELECT NULL
                               FROM int_lar_tipo_solici_pedido_dis l
                              WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)
                        AND NOT EXISTS (SELECT NULL
                               FROM ped_solic_cabec psc
                              WHERE psc.soca_oid_soli_cabe = con.oid_soli_cabe
                                AND psc.ind_oc = 1)) = 0 THEN
                '01'
               ELSE
                '02'
             END codigoboleta,
             psc.val_nume_soli numconsolidado,
             lpd.num_caja numcaja,
             tce.val_tipo_caja valtipocaja,
             SUM(lpd.num_unid_prod * nvl(mpd.val_cost_estd,
                                         0)) valcaja,
             SUM(lpd.num_unid_prod * nvl(mpd.val_peso,
                                         0)) valpesocaja
        FROM ped_solic_cabec                psc,
             int_lar_tipo_solici_pedido_dis lts,
             ape_lista_picad_cabec          lpc,
             ape_lista_picad_detal          lpd,
             ape_etiqu                      aet,
             mae_produ                      mpd,
             ape_tipo_caja_embal            tce
       WHERE psc.ind_inte_lari_gene = 0
         AND psc.tspa_oid_tipo_soli_pais = lts.tspa_oid_tipo_soli_pais
         AND psc.oid_soli_cabe = lpc.soca_oid_soli_cabe
         AND lpc.oid_list_pica_cabe = lpd.lpca_oid_list_pica_cabe
         AND lpc.oid_list_pica_cabe = aet.lpca_oid_list_pica_cabe
         AND lpd.prod_oid_prod = mpd.oid_prod
         AND trunc(psc.fec_fact) = to_date(psfechafactura,
                                           'dd/MM/yyyy')
       GROUP BY psc.val_nume_soli,
                lpd.num_caja,
                tce.val_tipo_caja,
                psc.perd_oid_peri,
                psc.fec_fact;

    TYPE interfazrec IS RECORD(
      codigopais     bas_pais.cod_pais%TYPE,
      codigomarca    seg_marca.cod_marc%TYPE,
      codigoboleta   VARCHAR2(2),
      numconsolidado ped_solic_cabec.val_nume_soli%TYPE,
      numcaja        ape_lista_picad_detal.num_caja%TYPE,
      valtipocaja    ape_tipo_caja_embal.val_tipo_caja%TYPE,
      valcaja        NUMBER,
      valpesocaja    NUMBER);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    vscodmarca VARCHAR2(2);
    vscodpais  VARCHAR2(2);

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;

  BEGIN
    -- Obteniendo el codigo de Pais y de Marca para enviar a SAT
    SELECT p.cod_comp,
           p.cod_pais_ocr
      INTO vscodmarca,
           vscodpais
      FROM bas_pais_compa p
     WHERE p.cod_pais = pscodigopais;

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz(vscodmarca,
                    vscodpais);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigopais || ';' || interfazrecord(x).codigomarca || ';' || interfazrecord(x)
                     .codigoboleta || ';' || interfazrecord(x).numconsolidado || ';' || interfazrecord(x)
                     .numcaja || ';' || interfazrecord(x).valtipocaja || ';' || interfazrecord(x)
                     .valcaja || ';' || interfazrecord(x).valpesocaja;

          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;

      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_PR_APE_ENVIO_COSTO_CAJA: ' || ls_sqlerrm);
  END int_pr_ape_envio_costo_caja;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Maestro de Productos para WCS
  Fecha Creacion    : 26/08/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_produ_wcs
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigocentro   VARCHAR2
  ) IS

    CURSOR c_interfaz(vnidperiodo NUMBER) IS
      SELECT substr(psnombrearchivo,
                    14,
                    4) idconfiguracion,
             pscodigopais pais,
             pscodigomarca marca,
             pscodigocentro cd,
             mep.cod_sap codigoproducto,
             s.val_i18n descripcionproducto,
             mep.val_peso peso,
             prd.num_afra_larg largo,
             prd.num_afra_anch ancho,
             prd.num_afra_alto alto,
             CASE
               WHEN prd.tidi_oid_tipo_dispe = 2 THEN
                '1'
               ELSE
                '0'
             END indaf,
             vda.val_unit_numb afunit,
             prd.num_afra_pack_size afpack_size,
             vda.val_stat afstatus,
             vda.val_strg_loca afstoragelocation,
             vda.val_case_quty afcase_quantity,
             prd.num_afra_scre_quty afscreen_of_quantity,
             prd.num_lane_capa_60 aflane_capacity,
             vda.val_reor_poin afreorder_point,
             vda.val_reor_quty afreorder_quantity,
             prd.num_afra_pick_sped afpick_speed,
             NULL aflane_type,
             vda.val_pick_sequ afpick_sequence,
             vda.val_pick afpicked,
             vda.pred_repl afpredicted_replenishment,
             vda.val_vari afvariance,
             vda.val_val1 afvalue1,
             vda.val_val2 afvalue2,
             vda.val_val3 afvalue3,
             to_char(SYSDATE,
                     'DD/MM/YYYY') afdatelastmodified,
             nvl(prd.num_unid_caja_gran,
                 0) unidadescajagrande,
             nvl(prd.num_unid_caja_pequ,
                 0) unidadescajapequena,
             mep.oid_prod oidprodu
        FROM ape_produ_xcamp       pxc,
             mae_produ             mep,
             ape_produ             prd,
             ape_valor_defau_afram vda,
             gen_i18n_sicc_pais    s
       WHERE pxc.prod_oid_prod = mep.oid_prod
         AND mep.oid_prod = prd.prod_oid_prod
         AND mep.oid_prod = s.val_oid
         AND s.attr_enti = 'MAE_PRODU'
         AND s.idio_oid_idio = 1
         AND perd_oid_peri = vnidperiodo;

    TYPE interfazrec IS RECORD(
      idconfiguracion           VARCHAR2(20),
      pais                      VARCHAR2(20),
      marca                     VARCHAR2(20),
      cd                        VARCHAR2(20),
      codigoproducto            mae_produ.cod_sap%TYPE,
      descripcionproducto       gen_i18n_sicc_pais.val_i18n%TYPE,
      peso                      mae_produ.val_peso%TYPE,
      largo                     mae_produ.val_dime_larg%TYPE,
      ancho                     mae_produ.val_dime_anch%TYPE,
      alto                      mae_produ.val_dime_alto%TYPE,
      indaf                     VARCHAR2(20),
      afunit                    ape_valor_defau_afram.val_unit_numb%TYPE,
      afpack_size               ape_valor_defau_afram.val_pack_size%TYPE,
      afstatus                  ape_valor_defau_afram.val_stat%TYPE,
      afstoragelocation         ape_valor_defau_afram.val_strg_loca%TYPE,
      afcase_quantity           ape_valor_defau_afram.val_case_quty%TYPE,
      afscreen_of_quantity      ape_produ.num_afra_scre_quty%TYPE,
      aflane_capacity           ape_produ.num_lane_capa_60%TYPE,
      afreorder_point           ape_valor_defau_afram.val_reor_poin%TYPE,
      afreorder_quantity        ape_valor_defau_afram.val_reor_quty%TYPE,
      afpick_speed              ape_produ.num_afra_pick_sped%TYPE,
      aflane_type               VARCHAR2(20),
      afpick_sequence           ape_valor_defau_afram.val_pick_sequ%TYPE,
      afpicked                  ape_valor_defau_afram.val_pick%TYPE,
      afpredicted_replenishment ape_valor_defau_afram.pred_repl%TYPE,
      afvariance                ape_valor_defau_afram.val_vari%TYPE,
      afvalue1                  ape_valor_defau_afram.val_val1%TYPE,
      afvalue2                  ape_valor_defau_afram.val_val2%TYPE,
      afvalue3                  ape_valor_defau_afram.val_val3%TYPE,
      afdatelastmodified        VARCHAR2(20),
      unidadescajagrande        ape_produ.num_unid_caja_gran%TYPE,
      unidadescajapequena       ape_produ.num_unid_caja_pequ%TYPE,
      oidprodu                  mae_produ.oid_prod%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo       bas_inter.dir_temp%TYPE;
    v_handle         utl_file.file_type;
    lslinea          VARCHAR2(1000);
    lsnombrearchivo  VARCHAR2(50);
    lnidpais         NUMBER;
    lnidmarca        NUMBER;
    lnidcanal        NUMBER;
    lnidperiodo      NUMBER;
    lbabrirutlfile   BOOLEAN;
    lnnumpriominima  ape_produ_anaqu.num_nume_prio%TYPE;
    lscodigotipoanaq ape_tipo_anaqu.cod_tipo_anaq%TYPE;

  BEGIN

    /* obteniendo id's */
    lnidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante
    lnidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca); -- id del marca consultante
    lnidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal); -- id del canal consultante
    lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                              lnidmarca,
                                                              lnidcanal); -- id del periodo consultante

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz(lnidperiodo);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */

      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          BEGIN

            -- Se obtiene la Minima Prioridad del Tipo de Anaquel del Producto
            SELECT MIN(approdanaq.num_nume_prio)
              INTO lnnumpriominima
              FROM ape_produ_anaqu approdanaq,
                   ape_tipo_anaqu  aptipanaqu
             WHERE approdanaq.tian_oid_tipo_anaq = aptipanaqu.oid_tipo_anaq
               AND approdanaq.prod_oid_prod = interfazrecord(x).oidprodu;

          EXCEPTION
            WHEN no_data_found THEN
              lnnumpriominima := 0;
          END;

          IF (lnnumpriominima > 0) THEN
            -- Se captura el Código de Tipo de Anaquel del Producto,
            -- con la condición que sea de la mínima Prioridad
            BEGIN

              SELECT aptipanaqu.cod_tipo_anaq
                INTO lscodigotipoanaq
                FROM ape_produ_anaqu approdanaq,
                     ape_tipo_anaqu  aptipanaqu
               WHERE approdanaq.tian_oid_tipo_anaq = aptipanaqu.oid_tipo_anaq
                 AND approdanaq.prod_oid_prod = interfazrecord(x).oidprodu
                 AND aptipanaqu.ind_tipo_afrm = 1
                 AND approdanaq.num_nume_prio = lnnumpriominima;

            EXCEPTION
              WHEN no_data_found THEN
                lscodigotipoanaq := NULL;
            END;
          ELSE
            lscodigotipoanaq := NULL;
          END IF;

          lslinea := interfazrecord(x)
                     .idconfiguracion || ';' || interfazrecord(x).pais || ';' || interfazrecord(x)
                     .marca || ';' || interfazrecord(x).cd || ';' || interfazrecord(x)
                     .codigoproducto || ';' ||
                      int_fn_conve_caden_texto(interfazrecord(x).descripcionproducto) || ';' || interfazrecord(x).peso || ';' || interfazrecord(x)
                     .largo || ';' || interfazrecord(x).ancho || ';' || interfazrecord(x).alto || ';' || interfazrecord(x)
                     .indaf || ';' || interfazrecord(x).afunit || ';' || interfazrecord(x)
                     .afpack_size || ';' || interfazrecord(x).afstatus || ';' || interfazrecord(x)
                     .afstoragelocation || ';' || interfazrecord(x).afcase_quantity || ';' || interfazrecord(x)
                     .afscreen_of_quantity || ';' || interfazrecord(x).aflane_capacity || ';' || interfazrecord(x)
                     .afreorder_point || ';' || interfazrecord(x).afreorder_quantity || ';' || interfazrecord(x)
                     .afpick_speed || ';' || lscodigotipoanaq || ';' || interfazrecord(x)
                     .afpick_sequence || ';' || interfazrecord(x).afpicked || ';' || interfazrecord(x)
                     .afpredicted_replenishment || ';' || interfazrecord(x).afvariance || ';' || interfazrecord(x)
                     .afvalue1 || ';' || interfazrecord(x).afvalue2 || ';' || interfazrecord(x)
                     .afvalue3 || ';' || interfazrecord(x).afdatelastmodified || ';' || interfazrecord(x)
                     .unidadescajagrande || ';' || interfazrecord(x).unidadescajapequena;

          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;

      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;

    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);

    END IF;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_APE_ENVI_PRODU_WCS: ' || ls_sqlerrm);
  END int_pr_ape_envio_produ_wcs;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Mapa de Anaqueles para WCS
  Fecha Creacion    : 26/08/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_anaqu_wcs
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigocentro   VARCHAR2,
    pscodigomapacd   VARCHAR2,
    pscodigoversion  VARCHAR2
  ) IS

    CURSOR c_interfaz
    (
      vnidperiodo NUMBER,
      vnidcentro  NUMBER,
      vnidmapacd  NUMBER
    ) IS
      SELECT substr(psnombrearchivo,
                    14,
                    4) idconfiguracion,
             pscodigopais pais,
             pscodigomarca marca,
             pscodigocentro cd,
             liar.cod_line_wcs linea,
             subli.cod_subl_wcs sublinea,
             subli.cod_subl_arma subsistema,
             NULL zona,
             mpdet.num_anaq anaquel,
             prod.cod_sap codigoproducto,
             mpdet.num_afra_chan_addr afchanneladdress,
             mpdet.val_side afside,
             mpdet.num_afra_fram_numb afframenumber,
             mpdet.num_afra_leve_numb aflevelnumber,
             mpdet.num_afra_mach_addr afmachineaddress,
             '0' afcard,
             '00' afport,
             asdet.num_afra_lane_numb aflanenumber,
             asdet.num_afra_lane_qtty aflanequantity,
             '000' afreplanismentqty,
             NULL aflanetype,
             mpdet.num_afra_heig afheight,
             mpdet.num_afra_widt afwidth,
             '000000' afpicked,
             '0000' afvarianza,
             '00000' afreset,
             '00000' afemptycount,
             '00000' afjamcount,
             '00000' afmotorfailcount,
             '00000' aftimeoutcount,
             '00000' afimproperdispensecount,
             '00000' afnodispensecount,
             '00000' afnocogcount,
             '00000' afovercurrentcount,
             '00000' afwrongmodecount,
             '00000' afinternalerrorcount,
             '00000' aftotalerrorcount,
             '00000' affailedpickcount,
             '00000' afvalue1,
             '00000' afvalue2,
             '00000' afvalue3,
             '' aflotnumber,
             '00000' afrepeinishmentrequest,
             subli.oid_subl_arma oidsublinea,
             mpdet.tian_oid_tipo_anaq oidtipoanaquel,
             to_number(nvl(substr(mpdet.num_anaq,
                                  2,
                                  2),
                           '0')) numanaquel,
             ascab.mzca_oid_mapa_zona_cabe oidmapazona
        FROM ape_asign_produ_anaqu_cabec ascab,
             ape_asign_produ_anaqu_detal asdet,
             ape_mapa_centr_distr_detal  mpdet,
             ape_mapa_centr_distr_cabec  mpcab,
             ape_linea_armad             liar,
             ape_subli_armad             subli,
             mae_produ                   prod
       WHERE asdet.apac_oid_asig_prod_anaq_cabe = ascab.oid_asig_prod_anaq_cabe
         AND liar.oid_line_arma = subli.liar_oid_line_arma
         AND subli.oid_subl_arma = mpdet.sbar_oid_subl_arma
         AND prod.oid_prod(+) = asdet.prod_oid_prod
         AND mpdet.oid_mapa_cent_dist_deta = asdet.mcdd_oid_mapa_cent_dist_deta
         AND mpdet.mcdc_oid_mapa_cent_dist_cabe = mpcab.oid_mapa_cent_dist_cabe
         AND mpcab.ccdi_oid_conf_cent_dist = vnidcentro
         AND ascab.mcdc_oid_mapa_cent_dist_cabe = vnidmapacd
         AND ascab.perd_oid_peri = vnidperiodo
         AND ascab.val_vers = pscodigoversion
       ORDER BY mpdet.num_anaq;

    TYPE interfazrec IS RECORD(
      idconfiguracion         VARCHAR2(20),
      pais                    VARCHAR2(20),
      marca                   VARCHAR2(20),
      cd                      VARCHAR2(20),
      linea                   ape_linea_armad.cod_line_wcs%TYPE,
      sublinea                ape_subli_armad.cod_subl_wcs%TYPE,
      subsistema              ape_subli_armad.cod_subl_arma%TYPE,
      zona                    ape_mapa_zona_detal.num_idzo%TYPE,
      anaquel                 ape_mapa_centr_distr_detal.num_anaq%TYPE,
      codigoproducto          mae_produ.cod_sap%TYPE,
      afchanneladdress        ape_mapa_centr_distr_detal.num_afra_chan_addr%TYPE,
      afside                  ape_mapa_centr_distr_detal.val_side%TYPE,
      afframenumber           ape_mapa_centr_distr_detal.num_afra_fram_numb%TYPE,
      aflevelnumber           ape_mapa_centr_distr_detal.num_afra_leve_numb%TYPE,
      afmachineaddress        ape_mapa_centr_distr_detal.num_afra_mach_addr%TYPE,
      afcard                  VARCHAR2(20),
      afport                  VARCHAR2(20),
      aflanenumber            ape_asign_produ_anaqu_detal.num_afra_lane_numb%TYPE,
      aflanequantity          ape_asign_produ_anaqu_detal.num_afra_lane_qtty%TYPE,
      afreplanismentqty       VARCHAR2(20),
      aflanetype              ape_tipo_chane.cod_tipo_chan%TYPE,
      afheight                ape_mapa_centr_distr_detal.num_afra_heig%TYPE,
      afwidth                 ape_mapa_centr_distr_detal.num_afra_widt%TYPE,
      afpicked                VARCHAR2(20),
      afvarianza              VARCHAR2(20),
      afreset                 VARCHAR2(20),
      afemptycount            VARCHAR2(20),
      afjamcount              VARCHAR2(20),
      afmotorfailcount        VARCHAR2(20),
      aftimeoutcount          VARCHAR2(20),
      afimproperdispensecount VARCHAR2(20),
      afnodispensecount       VARCHAR2(20),
      afnocogcount            VARCHAR2(20),
      afovercurrentcount      VARCHAR2(20),
      afwrongmodecount        VARCHAR2(20),
      afinternalerrorcount    VARCHAR2(20),
      aftotalerrorcount       VARCHAR2(20),
      affailedpickcount       VARCHAR2(20),
      afvalue1                VARCHAR2(20),
      afvalue2                VARCHAR2(20),
      afvalue3                VARCHAR2(20),
      aflotnumber             VARCHAR2(20),
      afrepeinishmentrequest  VARCHAR2(20),
      oidsublinea             ape_subli_armad.oid_subl_arma%TYPE,
      oidtipoanaquel          ape_tipo_anaqu.oid_tipo_anaq%TYPE,
      numanaquel              NUMBER(6),
      oidmapazona             ape_asign_produ_anaqu_cabec.mzca_oid_mapa_zona_cabe%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo     VARCHAR2(50);
    lnidpais            NUMBER;
    lnidmarca           NUMBER;
    lnidcanal           NUMBER;
    lnidperiodo         NUMBER;
    lbabrirutlfile      BOOLEAN;
    lnoidcentro         app_confi_centr_distr.oid_conf_cent_dist%TYPE;
    lnoidmapacd         ape_mapa_centr_distr_cabec.oid_mapa_cent_dist_cabe%TYPE;
    lscodigomapazona    VARCHAR(20);
    lscodigotipoanaquel VARCHAR(20);

  BEGIN

    /* obteniendo id's */
    lnidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante
    lnidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca); -- id del marca consultante
    lnidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal); -- id del canal consultante
    lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                              lnidmarca,
                                                              lnidcanal); -- id del periodo consultante

    /* Obteniendo id Centro de Distribución */

    SELECT a.oid_conf_cent_dist
      INTO lnoidcentro
      FROM app_confi_centr_distr a
     WHERE a.cod_cent_dist = pscodigocentro;

    /* Obteniendo Id Mapa Centro de Distribución  */

    SELECT oid_mapa_cent_dist_cabe
      INTO lnoidmapacd
      FROM ape_mapa_centr_distr_cabec
     WHERE num_codi_mapa = pscodigomapacd
       AND ccdi_oid_conf_cent_dist = lnoidcentro;

    /* Llamada al caso de uso APS - 122 (Numerar Asignaciones x Producto en Versión Balanceada) */

    int_pkg_ape.ape_pr_gener_numer_asgpr_verbl(pscodigopais,
                                               pscodigomarca,
                                               pscodigocanal,
                                               pscodigoperiodo,
                                               pscodigocentro,
                                               pscodigoversion);

    /* Fin de Llamada al caso de uso APS - 122 */

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz(lnidperiodo,
                    lnoidcentro,
                    lnoidmapacd);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */

      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          BEGIN
            -- Obtenemos la Zona del Anaquel
            SELECT to_char(mzdet.num_idzo)
              INTO lscodigomapazona
              FROM ape_mapa_zona_detal mzdet,
                   ape_mapa_zona_cabec mzcab
             WHERE mzdet.mzca_oid_mapa_zona_cabe = mzcab.oid_mapa_zona_cabe
               AND interfazrecord(x).numanaquel >= num_bahi_desd
               AND interfazrecord(x).numanaquel <= num_bahi_hast
               AND mzcab.mcdc_oid_mapa_cent_dist_cabe = lnoidmapacd
               AND mzdet.sbar_oid_subl_arma = interfazrecord(x).oidsublinea
               AND mzcab.oid_mapa_zona_cabe = interfazrecord(x).oidmapazona;

          EXCEPTION
            WHEN no_data_found THEN
              lscodigomapazona := '1';
          END;

          BEGIN
            -- Obtenemos el campo AfLaneType (Código de Tipo Channel)
            SELECT tipchan.cod_tipo_chan
              INTO lscodigotipoanaquel
              FROM ape_tipo_anaqu tipanaq,
                   ape_tipo_chane tipchan
             WHERE tipanaq.tich_oid_tipo_chan = tipchan.oid_tipo_chan
               AND tipanaq.oid_tipo_anaq = interfazrecord(x).oidtipoanaquel;

          EXCEPTION
            WHEN no_data_found THEN
              lscodigotipoanaquel := NULL;
          END;

          lslinea := interfazrecord(x)
                     .idconfiguracion || ';' || interfazrecord(x).pais || ';' || interfazrecord(x)
                     .marca || ';' || interfazrecord(x).cd || ';' || interfazrecord(x).linea || ';' || interfazrecord(x)
                     .sublinea || ';' || interfazrecord(x).subsistema || ';' || lscodigomapazona || ';' || interfazrecord(x)
                     .anaquel || ';' || interfazrecord(x).codigoproducto || ';' || interfazrecord(x)
                     .afchanneladdress || ';' || interfazrecord(x).afside || ';' || interfazrecord(x)
                     .afframenumber || ';' || interfazrecord(x).aflevelnumber || ';' || interfazrecord(x)
                     .afmachineaddress || ';' || interfazrecord(x).afcard || ';' || interfazrecord(x)
                     .afport || ';' || interfazrecord(x).aflanenumber || ';' || interfazrecord(x)
                     .aflanequantity || ';' || interfazrecord(x).afreplanismentqty || ';' ||
                      lscodigotipoanaquel || ';' || interfazrecord(x).afheight || ';' || interfazrecord(x)
                     .afwidth || ';' || interfazrecord(x).afpicked || ';' || interfazrecord(x)
                     .afvarianza || ';' || interfazrecord(x).afreset || ';' || interfazrecord(x)
                     .afemptycount || ';' || interfazrecord(x).afjamcount || ';' || interfazrecord(x)
                     .afmotorfailcount || ';' || interfazrecord(x).aftimeoutcount || ';' || interfazrecord(x)
                     .afimproperdispensecount || ';' || interfazrecord(x).afnodispensecount || ';' || interfazrecord(x)
                     .afnocogcount || ';' || interfazrecord(x).afovercurrentcount || ';' || interfazrecord(x)
                     .afwrongmodecount || ';' || interfazrecord(x).afinternalerrorcount || ';' || interfazrecord(x)
                     .aftotalerrorcount || ';' || interfazrecord(x).affailedpickcount || ';' || interfazrecord(x)
                     .afvalue1 || ';' || interfazrecord(x).afvalue2 || ';' || interfazrecord(x)
                     .afvalue3 || ';' || interfazrecord(x).aflotnumber || ';' || interfazrecord(x)
                     .afrepeinishmentrequest;

          utl_file.put_line(v_handle,
                            lslinea);

        END LOOP;

      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;

    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);

    END IF;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_APE_ENVI_ANAQU_WCS: ' || ls_sqlerrm);
  END int_pr_ape_envio_anaqu_wcs;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Olas a WCS (WCS-3)
  Fecha Creacion    : 31/08/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_olas_wcs3
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    psfechafactura   VARCHAR2,
    pscodigocentro   VARCHAR2,
    pscodigolinea    VARCHAR2,
    psnumola         VARCHAR2
  ) IS

    CURSOR c_interfaz
    (
      vnidlinea  NUMBER,
      vnidcentro NUMBER
    ) IS
      SELECT pscodigopais pais,
             pscodigomarca marca,
             pscodigocentro centrodistribucion,
             NULL regioncomercial,
             NULL zonacomercial,
             linar.cod_line_wcs linea,
             psnumola lote,
             to_char(apicab.fec_factu,
                     'MM/DD/YYYY') fechafacturacion,
             rtrim(ltrim(to_char(psol.val_nume_soli))) ||
             rtrim(ltrim(to_char(apidt.num_caja,
                                 '000'))) consolidado,
             psol.val_nume_soli orden,
             apidt.num_caja numerocaja,
             etqu.num_secu numerounicocaja,
             mprd.cod_sap codigoproducto,
             s1.val_i18n descripcionproducto,
             apidt.num_unid_pica unidadespicar,
             ltrim(rtrim(to_char(etqu.num_caja,
                                 '000'))) || '/' ||
             ltrim(rtrim(to_char(etqu.num_tota_caja,
                                 '000'))) ptlcartondescription,
             '1' ptlnumberofcarton,
             NULL afunitnumber,
             '1' afstatus,
             '0' afstorenumber,
             NULL afstoreid,
             psol.val_nume_soli afinvoicenumber,
             '' afaddress1,
             '' afaddress2,
             '' afaddress3,
             '0' afaddress4,
             '' afcity,
             '' afstate,
             '' afzip,
             '0' afsize,
             '' afdescription,
             '' afcodes,
             '' afroute,
             '' aftoteid,
             NULL afpacksize,
             '0000' aflinenumber,
             '00000000' afmpbline,
             '00000' afquantitypicked,
             '0' afspecialcode,
             '00' aferrorcode,
             '00' aferrorlane,
             '0' aflotnumber,
             NULL idcampana,
             CASE
               WHEN apicab.val_text_cheq IS NULL THEN
                '0'
               WHEN apicab.val_text_cheq = '' THEN
                '0'
               ELSE
                '1'
             END chequeocomercial,
             apicab.zorg_oid_regi oidregion,
             apicab.zzon_oid_zona oidzona
        FROM ape_lista_picad_detal apidt,
             ape_lista_picad_cabec apicab,
             mae_produ             mprd,
             ape_produ             prd,
             ped_solic_cabec       psol,
             ape_linea_armad       linar,
             ape_etiqu             etqu,
             gen_i18n_sicc_pais    s1
       WHERE apidt.lpca_oid_list_pica_cabe = apicab.oid_list_pica_cabe
         AND apidt.prod_oid_prod = mprd.oid_prod
         AND mprd.oid_prod = prd.prod_oid_prod
         AND apicab.soca_oid_soli_cabe = psol.oid_soli_cabe
         AND linar.oid_line_arma = apicab.liar_oid_line_arma
         AND (etqu.soca_oid_soli_cabe(+) = apicab.soca_oid_soli_cabe AND
             etqu.num_caja = apidt.num_caja)
         AND (s1.attr_enti(+) = 'MAE_PRODU' AND s1.val_oid(+) = mprd.oid_prod)
         AND apicab.num_ola = to_number(psnumola)
         AND trunc(apicab.fec_factu) = to_date(psfechafactura,
                                               'dd/MM/yyyy')
         AND apicab.ccdi_oid_conf_cent_dist = vnidcentro
         AND apicab.liar_oid_line_arma = vnidlinea
       ORDER BY psol.val_nume_soli,
                apidt.num_caja,
                mprd.cod_sap;

    TYPE interfazrec IS RECORD(
      pais                 VARCHAR2(20),
      marca                VARCHAR2(20),
      centrodistribucion   VARCHAR2(20),
      regioncomercial      VARCHAR2(20),
      zonacomercial        VARCHAR2(20),
      linea                ape_linea_armad.cod_line_wcs%TYPE,
      lote                 VARCHAR2(20),
      fechafacturacion     VARCHAR2(10),
      consolidado          VARCHAR2(20),
      orden                ped_solic_cabec.val_nume_soli%TYPE,
      numerocaja           ape_lista_picad_detal.num_caja%TYPE,
      numerounicocaja      ape_etiqu.num_secu%TYPE,
      codigoproducto       mae_produ.cod_sap%TYPE,
      descripcionproducto  gen_i18n_sicc_pais.val_i18n%TYPE,
      unidadespicar        ape_lista_picad_detal.num_unid_prod%TYPE,
      ptlcartondescription VARCHAR2(20),
      ptlnumberofcarton    VARCHAR2(10),
      afunitnumber         ape_valor_defau_afram.val_unit_numb%TYPE,
      afstatus             VARCHAR2(10),
      afstorenumber        VARCHAR2(10),
      afstoreid            VARCHAR2(20),
      afinvoicenumber      ped_solic_cabec.val_nume_soli%TYPE,
      afaddress1           VARCHAR2(10),
      afaddress2           VARCHAR2(10),
      afaddress3           VARCHAR2(10),
      afaddress4           VARCHAR2(10),
      afcity               VARCHAR2(10),
      afstate              VARCHAR2(10),
      afzip                VARCHAR2(10),
      afsize               VARCHAR2(10),
      afdescription        VARCHAR2(10),
      afcodes              VARCHAR2(10),
      afroute              VARCHAR2(10),
      aftoteid             VARCHAR2(10),
      afpacksize           ape_valor_defau_afram.val_pack_size%TYPE,
      aflinenumber         VARCHAR2(10),
      afmpbline            VARCHAR2(10),
      afquantitypicked     VARCHAR2(10),
      afspecialcode        VARCHAR2(10),
      aferrorcode          VARCHAR2(10),
      aferrorlane          VARCHAR2(10),
      aflotnumber          VARCHAR2(10),
      idcampana            VARCHAR2(20),
      chequeocomercial     VARCHAR2(10),
      oidregion            zon_regio.oid_regi%TYPE,
      oidzona              zon_zona.oid_zona%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);
    lnidpais        NUMBER;
    lnidcentro      NUMBER;
    lnidlinea       NUMBER;
    lbabrirutlfile  BOOLEAN;
    lnconsecutivo   NUMBER := 0;
    p_nro_caja_ante ape_lista_picad_detal.num_caja%TYPE := NULL;
    p_nro_caja      ape_lista_picad_detal.num_caja%TYPE;
    ln_valunitnumb  ape_valor_defau_afram.val_unit_numb%TYPE;
    ln_valpacksize  ape_valor_defau_afram.val_pack_size%TYPE;
    ls_codregion    zon_regio.cod_regi%TYPE;
    ls_codzona      zon_zona.cod_zona%TYPE;
    ls_codperiodo   VARCHAR2(100);

  BEGIN

    /* obteniendo id's */
    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante

    /* Obtenemos el Oid Centro de Distribución */
    SELECT app.oid_conf_cent_dist
      INTO lnidcentro
      FROM app_confi_centr_distr app
     WHERE app.cod_cent_dist = pscodigocentro
       AND app.pais_oid_pais = lnidpais;

    /* Obtenemos el Oid Linea de Armado */
    SELECT lia.oid_line_arma
      INTO lnidlinea
      FROM ape_linea_armad lia
     WHERE lia.num_codi_line = pscodigolinea;

    /* Obtenemos los valores de Val_Unit_Numb y Val_Pack_Size de la tabla APE_VALOR_DEFAU_AFRAM*/

    SELECT val_unit_numb,
           val_pack_size
      INTO ln_valunitnumb,
           ln_valpacksize
      FROM ape_valor_defau_afram;

    /* Obtenemos el Código de la Campaña */

    BEGIN
      ls_codperiodo := substr(gen_pkg_gener.gen_fn_devue_descr_campa_fecha(psfechafactura),
                              15,
                              6);

    EXCEPTION
      WHEN no_data_found THEN
        ls_codperiodo := NULL;
    END;

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz(lnidlinea,
                    lnidcentro);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */

      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          p_nro_caja := interfazrecord(x).numerocaja;

          IF (p_nro_caja_ante IS NULL OR p_nro_caja_ante = p_nro_caja) THEN
            lnconsecutivo := lnconsecutivo + 1;
          ELSE
            lnconsecutivo := 1;
          END IF;

          /* Obtenemos el Codigo de Región Comercial */

          BEGIN
            SELECT reg.cod_regi
              INTO ls_codregion
              FROM zon_regio reg
             WHERE reg.oid_regi = interfazrecord(x).oidregion;
          EXCEPTION
            WHEN no_data_found THEN
              ls_codregion := '';
          END;

          /* Obtenemos el Código de Zona Comercial */

          BEGIN
            SELECT zn.cod_zona
              INTO ls_codzona
              FROM zon_zona zn
             WHERE zn.zorg_oid_regi = interfazrecord(x).oidregion
               AND zn.oid_zona = interfazrecord(x).oidzona;
          EXCEPTION
            WHEN no_data_found THEN
              ls_codzona := '';
          END;

          lslinea := interfazrecord(x)
                     .pais || ';' || interfazrecord(x).marca || ';' || interfazrecord(x)
                     .centrodistribucion || ';' || ls_codregion || ';' || ls_codzona || ';' || interfazrecord(x)
                     .linea || ';' || interfazrecord(x).lote || ';' || interfazrecord(x)
                     .fechafacturacion || ';' || interfazrecord(x).consolidado || ';' || interfazrecord(x)
                     .orden || ';' || interfazrecord(x).numerocaja || ';' || interfazrecord(x)
                     .numerounicocaja || ';' || interfazrecord(x).codigoproducto || ';' ||
                      int_fn_conve_caden_texto(interfazrecord(x).descripcionproducto) || ';' || interfazrecord(x)
                     .unidadespicar || ';' || interfazrecord(x).ptlcartondescription || ';' || interfazrecord(x)
                     .ptlnumberofcarton || ';' || ln_valunitnumb || ';' || interfazrecord(x)
                     .afstatus || ';' || interfazrecord(x).afstorenumber || ';' || ls_codperiodo || ';' || interfazrecord(x)
                     .afinvoicenumber || ';' || interfazrecord(x).afaddress1 || ';' || interfazrecord(x)
                     .afaddress2 || ';' || interfazrecord(x).afaddress3 || ';' || interfazrecord(x)
                     .afaddress4 || ';' || interfazrecord(x).afcity || ';' || interfazrecord(x)
                     .afstate || ';' || interfazrecord(x).afzip || ';' || interfazrecord(x).afsize || ';' || interfazrecord(x)
                     .afdescription || ';' || interfazrecord(x).afcodes || ';' || interfazrecord(x)
                     .afroute || ';' || interfazrecord(x).aftoteid || ';' || ln_valpacksize || ';' ||
                      lnconsecutivo || ';' || interfazrecord(x).afmpbline || ';' || interfazrecord(x)
                     .afquantitypicked || ';' || interfazrecord(x).afspecialcode || ';' || interfazrecord(x)
                     .aferrorcode || ';' || interfazrecord(x).aferrorlane || ';' || interfazrecord(x)
                     .aflotnumber || ';' || ls_codperiodo || ';' || interfazrecord(x)
                     .chequeocomercial;

          utl_file.put_line(v_handle,
                            lslinea);

          p_nro_caja_ante := p_nro_caja;

        END LOOP;

      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;

    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);

    END IF;

    /* Al retornar satisfactoriamente del caso de uso actualiza el estado de la ola y fecha de liberación*/

    UPDATE ape_olas_xdia
       SET val_esta_ola  = 1,
           fec_hora_libe = SYSDATE
     WHERE num_ola = psnumola
       AND ccdi_oid_conf_cent_dist = lnidcentro;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_APE_ENVI_OLAS_WCS3: ' || ls_sqlerrm);
  END int_pr_ape_envio_olas_wcs3;

  /**************************************************************************
  Descripcion       : Devuelve el Texto Variable por Cliente
  Autor             : Nicolás López
  Fecha Creacion    : 31/08/2010
  ***************************************************************************/
  FUNCTION int_fn_ape_devue_texto_varia(p_oid_cliente IN NUMBER) RETURN VARCHAR2 IS
    vs_texto_varia ape_confi_texto_varia.val_text_vari%TYPE;
  BEGIN

    SELECT textovariable.val_text_vari
      INTO vs_texto_varia
      FROM (SELECT ctext.val_text_vari,
                   tclas.oid_tipo_clas,
                   stcl.oid_subt_clie,
                   clasi.oid_clas,
                   mcli.oid_clie,
                   ctext.tccl_oid_tipo_clas tipoclas,
                   ctext.sbti_oid_subt_clie subtipo_cliente,
                   ctext.clas_oid_clas      clase
              FROM mae_clien             mcli,
                   mae_tipo_clien        tcli,
                   mae_subti_clien       stcl,
                   mae_tipo_clasi_clien  tclas,
                   mae_clasi             clasi,
                   ape_confi_texto_varia ctext
             WHERE mcli.oid_clie = tcli.oid_tipo_clie
               AND tcli.oid_tipo_clie = stcl.ticl_oid_tipo_clie
               AND stcl.oid_subt_clie = tclas.sbti_oid_subt_clie
               AND tclas.oid_tipo_clas = clasi.tccl_oid_tipo_clas
               AND (nvl(ctext.sbti_oid_subt_clie,
                        0) = nvl(stcl.oid_subt_clie,
                                  0) OR
                   nvl(ctext.clas_oid_clas,
                        0) = nvl(clasi.oid_clas,
                                  0) OR
                   nvl(ctext.tccl_oid_tipo_clas,
                        0) = nvl(tclas.oid_tipo_clas,
                                  0))) textovariable
     WHERE textovariable.oid_clie = p_oid_cliente
       AND textovariable.subtipo_cliente IS NOT NULL
       AND textovariable.tipoclas IS NOT NULL
       AND textovariable.clase IS NOT NULL
       AND rownum = 1;

    RETURN vs_texto_varia;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN '';
  END int_fn_ape_devue_texto_varia;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Datos para Etiquetado a WCS (WCS-8)
  Fecha Creacion    : 02/09/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_etiqu_wcs8
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    psfechafactura   VARCHAR2,
    psnumola         VARCHAR2,
    pscodigocentro   VARCHAR2
  ) IS

    CURSOR c_interfaz(vnidcentro NUMBER) IS
      SELECT DISTINCT pscodigopais pais,
                      pscodigomarca marca,
                      pscodigocentro centrodistribucion,
                      linar.cod_line_wcs linea,
                      to_char(apicab.fec_factu,
                              'MM/DD/YYYY') fechafacturacion,
                      etqu.num_secu numerounicocaja,
                      psol.val_nume_soli consolidado,
                      to_char(etqu.num_tota_caja,
                              '000') totalcajas,
                      to_char(etqu.num_caja,
                              '000') numerocaja,
                      s1.val_i18n descriplinea,
                      NULL tipocaja,
                      NULL textovariable,
                      NULL codigoterritorial,
                      mcli.cod_clie codigo,
                      rtrim(mcli.val_ape1) || ' ' || rtrim(mcli.val_ape2) || ', ' ||
                      rtrim(mcli.val_nom1) nombre,
                      NULL direccionp1,
                      NULL direccionp2,
                      NULL direccionp3,
                      NULL direccionp4,
                      '0' chequeocomercial,
                      psol.val_nume_soli boletaentrega,
                      NULL codigoproducto,
                      NULL nombreproducto,
                      NULL indcajamaestra,
                      mcli.oid_clie oidcliente,
                      psol.sbti_oid_subt_clie oidsubtipocliente,
                      psol.tccl_oid_tccl_flet oidtipoclasifica,
                      psol.clas_oid_clas_flet oidclasifica,
                      apidt.tcem_oid_tipo_caja_emba oidtipocajaembalaje,
                      apicab.zorg_oid_regi oidregion,
                      apicab.zzon_oid_zona oidzona,
                      apicab.zscc_oid_secc oidseccion
        FROM ape_lista_picad_detal apidt,
             ape_lista_picad_cabec apicab,
             ape_linea_armad       linar,
             ape_etiqu             etqu,
             gen_i18n_sicc_pais    s1,
             ped_solic_cabec       psol,
             mae_clien             mcli
       WHERE apidt.lpca_oid_list_pica_cabe = apicab.oid_list_pica_cabe
         AND apicab.liar_oid_line_arma = linar.oid_line_arma
         AND apicab.oid_list_pica_cabe = etqu.lpca_oid_list_pica_cabe
         AND apidt.tcem_oid_tipo_caja_emba = etqu.tcem_oid_tipo_caja_emba
         AND s1.val_oid = apicab.liar_oid_line_arma
         AND s1.attr_enti = 'APE_LINEA_ARMAD'
         AND apicab.soca_oid_soli_cabe = psol.oid_soli_cabe
         AND apicab.clie_oid_clie = mcli.oid_clie
         AND apicab.num_ola = psnumola
         AND apicab.ccdi_oid_conf_cent_dist = vnidcentro
         AND trunc(apicab.fec_factu) = to_date(psfechafactura,
                                               'DD/MM/YYYY')
       ORDER BY psol.val_nume_soli;

    TYPE interfazrec IS RECORD(
      pais                VARCHAR2(20),
      marca               VARCHAR2(20),
      centrodistribucion  VARCHAR2(20),
      linea               ape_linea_armad.cod_line_wcs%TYPE,
      fechafacturacion    VARCHAR2(10),
      numerounicocaja     ape_etiqu.num_secu%TYPE,
      consolidado         ped_solic_cabec.val_nume_soli%TYPE,
      totalcajas          VARCHAR2(20),
      numerocaja          VARCHAR2(20),
      descriplinea        gen_i18n_sicc_pais.val_i18n%TYPE,
      tipocaja            app_tipo_caja_produ.cod_caja%TYPE,
      textovariable       ape_confi_texto_varia.val_text_vari%TYPE,
      codigoterritorial   VARCHAR2(20),
      codigo              mae_clien.cod_clie%TYPE,
      nombre              VARCHAR2(200),
      direccionp1         VARCHAR2(100),
      direccionp2         mae_clien_direc.val_barr%TYPE,
      direccionp3         zon_valor_estru_geopo.des_geog%TYPE,
      direccionp4         zon_valor_estru_geopo.des_geog%TYPE,
      chequeocomercial    VARCHAR2(20),
      boletaentrega       ped_solic_cabec.val_nume_soli%TYPE,
      codigoproducto      mae_produ.cod_sap%TYPE,
      nombreproducto      gen_i18n_sicc_pais.val_i18n%TYPE,
      indcajamaestra      VARCHAR2(1),
      oidcliente          mae_clien.oid_clie %TYPE,
      oidsubtipocliente   ped_solic_cabec.sbti_oid_subt_clie%TYPE,
      oidtipoclasifica    ped_solic_cabec.tccl_oid_tccl_flet%TYPE,
      oidclasifica        ped_solic_cabec.clas_oid_clas_flet%TYPE,
      oidtipocajaembalaje ape_tipo_caja_embal.oid_tipo_caja_emba%TYPE,
      oidregion           zon_regio.oid_regi%TYPE,
      oidzona             zon_zona.oid_zona%TYPE,
      oidseccion          zon_secci.oid_secc%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo        bas_inter.dir_temp%TYPE;
    v_handle          utl_file.file_type;
    lslinea           VARCHAR2(1000);
    lsnombrearchivo   VARCHAR2(50);
    lscadena1         VARCHAR2(100);
    lscadena2         VARCHAR2(100);
    lsdireccionp1     VARCHAR2(100);
    lsdireccionp2     VARCHAR2(100);
    lsdireccionp3     zon_valor_estru_geopo.des_geog%TYPE;
    lsdireccionp4     zon_valor_estru_geopo.des_geog%TYPE;
    ls_orde_1         zon_valor_estru_geopo.orde_1%TYPE;
    ls_orde_2         zon_valor_estru_geopo.orde_2%TYPE;
    ls_orde_3         zon_valor_estru_geopo.orde_3%TYPE;
    ls_orde_4         zon_valor_estru_geopo.orde_4%TYPE;
    ls_codigoproducto mae_produ.cod_sap%TYPE;
    ls_descproducto   gen_i18n_sicc_pais.val_i18n%TYPE;
    ls_textovariable  ape_confi_texto_varia.val_text_vari%TYPE;
    ln_ind_dire_sepa  seg_param_inter_pais.ind_dire_sepa%TYPE;
    lnidpais          NUMBER;
    lnidcentro        NUMBER;
    lnidlinea         NUMBER;
    lbabrirutlfile    BOOLEAN;
    lscodtipocaja     app_tipo_caja_produ.cod_caja%TYPE;
    lsindcajamaestra  VARCHAR2(10);
    lncontador        NUMBER;
    lscodregion       zon_regio.cod_regi%TYPE;
    lscodzona         zon_zona.cod_zona%TYPE;
    lscodseccion      zon_secci.cod_secc%TYPE;

  BEGIN

    /* obteniendo id's */
    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    /* Obtenemos el Oid Centro de Distribución */
    SELECT app.oid_conf_cent_dist
      INTO lnidcentro
      FROM app_confi_centr_distr app
     WHERE app.cod_cent_dist = pscodigocentro
       AND app.pais_oid_pais = lnidpais;

    -- Se captura el valor de Indicador dirección separada
    SELECT segparam.ind_dire_sepa
      INTO ln_ind_dire_sepa
      FROM seg_param_inter_pais segparam
     WHERE segparam.pais_oid_pais = lnidpais;

    OPEN c_interfaz(lnidcentro);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */

      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          BEGIN
            -- Se obtiene el Tipo de Caja
            SELECT tpcp.cod_caja,
                   to_char(tpce.ind_caja_prod_term)
              INTO lscodtipocaja,
                   lsindcajamaestra
              FROM ape_tipo_caja_embal tpce,
                   app_tipo_caja_produ tpcp
             WHERE tpce.ticp_oid_tipo_caja_prod = tpcp.oid_tipo_caja_prod
               AND tpce.ind_caja_prod_term = 1
               AND tpce.oid_tipo_caja_emba = interfazrecord(x).oidtipocajaembalaje;

          EXCEPTION
            WHEN no_data_found THEN
              lscodtipocaja    := NULL;
              lsindcajamaestra := NULL;
          END;

          IF (lsindcajamaestra IS NOT NULL) THEN
            IF (lsindcajamaestra = '0') THEN
              lsindcajamaestra := ' ';
            ELSE
              lsindcajamaestra := 'M';
            END IF;
          ELSE
            lsindcajamaestra := ' ';
          END IF;

          IF (lsindcajamaestra = 'M') THEN

            -- Verificamos Cantidad de Productos y validamos si es caja maestra

            SELECT COUNT(1)
              INTO lncontador
              FROM ape_lista_picad_detal apidet,
                   ape_lista_picad_cabec apicabe,
                   ped_solic_cabec       psol
             WHERE apidet.lpca_oid_list_pica_cabe = apicabe.oid_list_pica_cabe
               AND apicabe.soca_oid_soli_cabe = psol.oid_soli_cabe
               AND psol.val_nume_soli = interfazrecord(x).consolidado
               AND apidet.num_caja = to_number(interfazrecord(x).numerocaja);

            -- Se valida que tenga un producto al menos

            IF (lncontador = 1) THEN

              -- Si es una caja Maestra, se almacena el Código y la descripción internacionalizable.

              SELECT mpro.cod_sap,
                     int_fn_conve_caden_texto(s1.val_i18n)
                INTO ls_codigoproducto,
                     ls_descproducto
                FROM ape_lista_picad_detal apidet,
                     ape_lista_picad_cabec apicabe,
                     ped_solic_cabec       psol,
                     mae_produ             mpro,
                     gen_i18n_sicc_pais    s1
               WHERE apidet.lpca_oid_list_pica_cabe = apicabe.oid_list_pica_cabe
                 AND apicabe.soca_oid_soli_cabe = psol.oid_soli_cabe
                 AND apidet.prod_oid_prod = mpro.oid_prod
                 AND s1.attr_enti = 'MAE_PRODU'
                 AND s1.val_oid = mpro.oid_prod
                 AND psol.val_nume_soli = interfazrecord(x).consolidado
                 AND apidet.num_caja = to_number(interfazrecord(x).numerocaja);

            ELSE
              ls_codigoproducto := '';
              ls_descproducto   := '';
            END IF;

          ELSE
            ls_codigoproducto := '';
            ls_descproducto   := '';
          END IF;

          -- Se obtiene el texto variable
          ls_textovariable := '';

          IF ((interfazrecord(x)
             .oidsubtipocliente IS NOT NULL OR interfazrecord(x).oidsubtipocliente <> '') AND
             (interfazrecord(x)
             .oidtipoclasifica IS NOT NULL OR interfazrecord(x).oidtipoclasifica <> '') AND
             (interfazrecord(x).oidclasifica IS NOT NULL OR interfazrecord(x).oidclasifica <> '')) THEN

            BEGIN
              SELECT actv.val_text_vari
                INTO ls_textovariable
                FROM ape_confi_texto_varia actv
               WHERE actv.sbti_oid_subt_clie = interfazrecord(x).oidsubtipocliente
                 AND actv.tccl_oid_tipo_clas = interfazrecord(x).oidtipoclasifica
                 AND actv.clas_oid_clas = interfazrecord(x).oidclasifica
                 AND actv.pais_oid_pais = lnidpais
                 AND rownum = 1
               ORDER BY actv.oid_conf_text_vari DESC;
            EXCEPTION
              WHEN no_data_found THEN
                ls_textovariable := '';
            END;

          ELSE

            IF ((interfazrecord(x)
               .oidsubtipocliente IS NOT NULL OR interfazrecord(x).oidsubtipocliente <> '') AND
               (interfazrecord(x)
               .oidtipoclasifica IS NULL OR interfazrecord(x).oidtipoclasifica = '') AND
               (interfazrecord(x).oidclasifica IS NULL OR interfazrecord(x).oidclasifica = '')) THEN

              BEGIN
                SELECT actv.val_text_vari
                  INTO ls_textovariable
                  FROM ape_confi_texto_varia actv
                 WHERE actv.sbti_oid_subt_clie = interfazrecord(x).oidsubtipocliente
                   AND actv.tccl_oid_tipo_clas IS NULL
                   AND actv.clas_oid_clas IS NULL
                   AND actv.pais_oid_pais = lnidpais
                   AND rownum = 1
                 ORDER BY actv.oid_conf_text_vari DESC;
              EXCEPTION
                WHEN no_data_found THEN
                  ls_textovariable := '';
              END;

            END IF;

            IF ((interfazrecord(x)
               .oidsubtipocliente IS NOT NULL OR interfazrecord(x).oidsubtipocliente <> '') AND
               (interfazrecord(x)
               .oidtipoclasifica IS NOT NULL OR interfazrecord(x).oidtipoclasifica <> '') AND
               (interfazrecord(x).oidclasifica IS NULL OR interfazrecord(x).oidclasifica = '')) THEN

              BEGIN
                SELECT actv.val_text_vari
                  INTO ls_textovariable
                  FROM ape_confi_texto_varia actv
                 WHERE actv.sbti_oid_subt_clie = interfazrecord(x).oidsubtipocliente
                   AND actv.tccl_oid_tipo_clas = interfazrecord(x).oidtipoclasifica
                   AND actv.clas_oid_clas IS NULL
                   AND actv.pais_oid_pais = lnidpais
                   AND rownum = 1
                 ORDER BY actv.oid_conf_text_vari DESC;
              EXCEPTION
                WHEN no_data_found THEN
                  ls_textovariable := '';
              END;

            END IF;

            IF ((interfazrecord(x)
               .oidsubtipocliente IS NULL OR interfazrecord(x).oidsubtipocliente = '') AND
               (interfazrecord(x)
               .oidtipoclasifica IS NOT NULL OR interfazrecord(x).oidtipoclasifica <> '') AND
               (interfazrecord(x).oidclasifica IS NOT NULL OR interfazrecord(x).oidclasifica <> '')) THEN

              BEGIN
                SELECT actv.val_text_vari
                  INTO ls_textovariable
                  FROM ape_confi_texto_varia actv
                 WHERE actv.sbti_oid_subt_clie IS NULL
                   AND actv.tccl_oid_tipo_clas = interfazrecord(x).oidtipoclasifica
                   AND actv.clas_oid_clas = interfazrecord(x).oidclasifica
                   AND actv.pais_oid_pais = lnidpais
                   AND rownum = 1
                 ORDER BY actv.oid_conf_text_vari DESC;
              EXCEPTION
                WHEN no_data_found THEN
                  ls_textovariable := '';
              END;

            END IF;

          END IF;

          -- Obtenemos la Region
          BEGIN
            SELECT r.cod_regi
              INTO lscodregion
              FROM zon_regio r
             WHERE r.oid_regi = interfazrecord(x).oidregion;
          EXCEPTION
            WHEN no_data_found THEN
              lscodregion := '  ';
          END;

          -- Obtenemos la Zona
          BEGIN
            SELECT z.cod_zona
              INTO lscodzona
              FROM zon_zona z
             WHERE z.oid_zona = interfazrecord(x).oidzona
               AND z.zorg_oid_regi = interfazrecord(x).oidregion;
          EXCEPTION
            WHEN no_data_found THEN
              lscodzona := '  ';
          END;

          -- Obtenemos la Sección
          BEGIN
            SELECT c.cod_secc
              INTO lscodseccion
              FROM zon_secci c
             WHERE c.zzon_oid_zona = interfazrecord(x).oidzona
               AND c.oid_secc = interfazrecord(x).oidseccion;
          EXCEPTION
            WHEN no_data_found THEN
              lscodseccion := '  ';
          END;

          --Se obtiene la Dirección P1
          SELECT substr(ltrim(rtrim(stv.des_abrv_tipo_via)) || ' ' ||
                        ltrim(rtrim(mcld.val_nomb_via)) || ' ' || ltrim(rtrim(mcld.zvia_oid_via)) || ' ' ||
                        ltrim(rtrim(zv.nom_via)) || ' ' || ltrim(rtrim(mcld.num_ppal)) || ' ' ||
                        ltrim(rtrim(mcld.val_obse)),
                        1,
                        100),
                 substr(ltrim(rtrim(mcld.val_obse)) || ' ' || ltrim(rtrim(mcld.val_barr)),
                        1,
                        100),
                 substr(ltrim(rtrim(mcld.val_barr)),
                        1,
                        100)
            INTO lscadena1,
                 lscadena2,
                 lsdireccionp2
            FROM mae_clien_direc mcld,
                 seg_tipo_via    stv,
                 zon_via         zv
           WHERE mcld.tivi_oid_tipo_via = stv.oid_tipo_via(+)
             AND mcld.zvia_oid_via = zv.oid_via(+)
             AND mcld.clie_oid_clie = interfazrecord(x).oidcliente
             AND mcld.ind_elim = 0
             AND mcld.ind_dire_ppal = 1;

          IF (ln_ind_dire_sepa IS NOT NULL) THEN
            lsdireccionp1 := lscadena1;
          ELSE
            lsdireccionp1 := lscadena2;
          END IF;

          -- Se obtiene el nombre de la Ciudad (Provincia)
          BEGIN
            SELECT zge.orde_1,
                   zge.orde_2,
                   zge.orde_3,
                   zge.orde_4
              INTO ls_orde_1,
                   ls_orde_2,
                   ls_orde_3,
                   ls_orde_4
              FROM mae_clien_direc       mcdi,
                   zon_terri             zte,
                   zon_valor_estru_geopo zge
             WHERE mcdi.terr_oid_terr = zte.oid_terr
               AND zte.vepo_oid_valo_estr_geop = zge.oid_valo_estr_geop
               AND mcdi.clie_oid_clie = interfazrecord(x).oidcliente
               AND mcdi.ind_elim = 0
               AND mcdi.ind_dire_ppal = 1;
          EXCEPTION
            WHEN no_data_found THEN
              ls_orde_1 := NULL;
              ls_orde_2 := NULL;
              ls_orde_3 := NULL;
              ls_orde_4 := NULL;
          END;

          BEGIN
            SELECT zvgp.des_geog
              INTO lsdireccionp3
              FROM zon_valor_estru_geopo zvgp
             WHERE zvgp.orde_1 = ls_orde_1
               AND zvgp.orde_2 = ls_orde_2
               AND zvgp.orde_3 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              lsdireccionp3 := '';
          END;

          -- Se obtiene el nombre del Departamento
          BEGIN
            SELECT zvgp.des_geog
              INTO lsdireccionp4
              FROM zon_valor_estru_geopo zvgp
             WHERE zvgp.orde_1 = ls_orde_1
               AND zvgp.orde_2 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              lsdireccionp4 := '';
          END;

          lslinea := interfazrecord(x)
                     .pais || ';' || interfazrecord(x).marca || ';' || interfazrecord(x)
                     .centrodistribucion || ';' || interfazrecord(x).linea || ';' || interfazrecord(x)
                     .fechafacturacion || ';' || interfazrecord(x).numerounicocaja || ';' || interfazrecord(x)
                     .consolidado || ';' || interfazrecord(x).totalcajas || ';' || interfazrecord(x)
                     .numerocaja || ';' || interfazrecord(x).descriplinea || ';' || lscodtipocaja || ';' ||
                      ls_textovariable || ';' || lscodregion || lscodzona || lscodseccion || ';' || interfazrecord(x)
                     .codigo || ';' || interfazrecord(x).nombre || ';' || lsdireccionp1 || ';' ||
                      lsdireccionp2 || ';' || lsdireccionp3 || ';' || lsdireccionp4 || ';' || interfazrecord(x)
                     .chequeocomercial || ';' || interfazrecord(x).boletaentrega || ';' ||
                      ls_codigoproducto || ';' || ls_descproducto || ';' || lsindcajamaestra;

          utl_file.put_line(v_handle,
                            lslinea);

        END LOOP;

      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;

    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);

    END IF;

    /* Al retornar satisfactoriamente del caso de uso actualiza el estado de la ola y fecha de liberación*/

    UPDATE ape_olas_xdia
       SET val_esta_ola  = 1,
           fec_hora_libe = SYSDATE
     WHERE num_ola = psnumola
       AND ccdi_oid_conf_cent_dist = lnidcentro;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_APE_ENVI_ETIQU_WCS8: ' || ls_sqlerrm);
  END int_pr_ape_envio_etiqu_wcs8;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Confirmar Envio de Olas a WCS (WCS-3)
  Fecha Creacion    : 02/09/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_olas_wcs3c
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    psnumola         VARCHAR2,
    pscodigocentro   VARCHAR2
  ) IS

    CURSOR c_interfaz
    (
      vnidlinea  NUMBER,
      vnidcentro NUMBER
    ) IS
      SELECT pscodigopais pais,
             pscodigomarca marca,
             pscodigocentro centrodistribucion,
             NULL regioncomercial,
             NULL zonacomercial,
             linar.cod_line_wcs linea,
             psnumola lote,
             to_char(apicab.fec_factu,
                     'MM/DD/YYYY') fechafacturacion,
             rtrim(ltrim(to_char(psol.val_nume_soli))) ||
             rtrim(ltrim(to_char(apidt.num_caja,
                                 '000'))) consolidado,
             psol.val_nume_soli orden,
             apidt.num_caja numerocaja,
             etqu.num_secu numerounicocaja,
             mprd.cod_sap codigoproducto,
             s1.val_i18n descripcionproducto,
             apidt.num_unid_pica unidadespicar,
             ltrim(rtrim(to_char(etqu.num_caja,
                                 '000'))) || '/' ||
             ltrim(rtrim(to_char(etqu.num_tota_caja,
                                 '000'))) ptlcartondescription,
             '1' ptlnumberofcarton,
             NULL afunitnumber,
             '1' afstatus,
             '0' afstorenumber,
             NULL afstoreid,
             psol.val_nume_soli afinvoicenumber,
             '' afaddress1,
             '' afaddress2,
             '' afaddress3,
             '0' afaddress4,
             '' afcity,
             '' afstate,
             '' afzip,
             '0' afsize,
             '' afdescription,
             '' afcodes,
             '' afroute,
             '' aftoteid,
             NULL afpacksize,
             '0000' aflinenumber,
             '00000000' afmpbline,
             '00000' afquantitypicked,
             '0' afspecialcode,
             '00' aferrorcode,
             '00' aferrorlane,
             '0' aflotnumber,
             NULL idcampana,
             CASE
               WHEN apicab.val_text_cheq IS NULL THEN
                '0'
               WHEN apicab.val_text_cheq = '' THEN
                '0'
               ELSE
                '1'
             END chequeocomercial,
             apicab.zorg_oid_regi oidregion,
             apicab.zzon_oid_zona oidzona,
             apicab.perd_oid_peri oidperiodo
        FROM ape_lista_picad_detal apidt,
             ape_lista_picad_cabec apicab,
             mae_produ             mprd,
             ape_produ             prd,
             ped_solic_cabec       psol,
             ape_linea_armad       linar,
             ape_etiqu             etqu,
             gen_i18n_sicc_pais    s1
       WHERE apidt.lpca_oid_list_pica_cabe = apicab.oid_list_pica_cabe
         AND apidt.prod_oid_prod = mprd.oid_prod
         AND mprd.oid_prod = prd.prod_oid_prod
         AND apicab.soca_oid_soli_cabe = psol.oid_soli_cabe
         AND linar.oid_line_arma = apicab.liar_oid_line_arma
         AND (etqu.soca_oid_soli_cabe(+) = apicab.soca_oid_soli_cabe AND
             etqu.num_caja = apidt.num_caja)
         AND (s1.attr_enti(+) = 'MAE_PRODU' AND s1.val_oid(+) = mprd.oid_prod)
         AND apicab.num_ola = to_number(psnumola)
         AND apicab.ccdi_oid_conf_cent_dist = vnidcentro
       ORDER BY psol.val_nume_soli,
                apidt.num_caja,
                mprd.cod_sap;

    TYPE interfazrec IS RECORD(
      pais                 VARCHAR2(20),
      marca                VARCHAR2(20),
      centrodistribucion   VARCHAR2(20),
      regioncomercial      VARCHAR2(20),
      zonacomercial        VARCHAR2(20),
      linea                ape_linea_armad.cod_line_wcs%TYPE,
      lote                 VARCHAR2(20),
      fechafacturacion     VARCHAR2(10),
      consolidado          VARCHAR2(20),
      orden                ped_solic_cabec.val_nume_soli%TYPE,
      numerocaja           ape_lista_picad_detal.num_caja%TYPE,
      numerounicocaja      ape_etiqu.num_secu%TYPE,
      codigoproducto       mae_produ.cod_sap%TYPE,
      descripcionproducto  gen_i18n_sicc_pais.val_i18n%TYPE,
      unidadespicar        ape_lista_picad_detal.num_unid_prod%TYPE,
      ptlcartondescription VARCHAR2(20),
      ptlnumberofcarton    VARCHAR2(10),
      afunitnumber         ape_valor_defau_afram.val_unit_numb%TYPE,
      afstatus             VARCHAR2(10),
      afstorenumber        VARCHAR2(10),
      afstoreid            VARCHAR2(20),
      afinvoicenumber      ped_solic_cabec.val_nume_soli%TYPE,
      afaddress1           VARCHAR2(10),
      afaddress2           VARCHAR2(10),
      afaddress3           VARCHAR2(10),
      afaddress4           VARCHAR2(10),
      afcity               VARCHAR2(10),
      afstate              VARCHAR2(10),
      afzip                VARCHAR2(10),
      afsize               VARCHAR2(10),
      afdescription        VARCHAR2(10),
      afcodes              VARCHAR2(10),
      afroute              VARCHAR2(10),
      aftoteid             VARCHAR2(10),
      afpacksize           ape_valor_defau_afram.val_pack_size%TYPE,
      aflinenumber         VARCHAR2(10),
      afmpbline            VARCHAR2(10),
      afquantitypicked     VARCHAR2(10),
      afspecialcode        VARCHAR2(10),
      aferrorcode          VARCHAR2(10),
      aferrorlane          VARCHAR2(10),
      aflotnumber          VARCHAR2(10),
      idcampana            VARCHAR2(20),
      chequeocomercial     VARCHAR2(10),
      oidregion            zon_regio.oid_regi%TYPE,
      oidzona              zon_zona.oid_zona%TYPE,
      oidperiodo           seg_perio_corpo.oid_peri%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);
    lnidpais        NUMBER;
    lnidcentro      NUMBER;
    lnidlinea       NUMBER;
    lbabrirutlfile  BOOLEAN;
    lnconsecutivo   NUMBER := 0;
    p_nro_caja_ante ape_lista_picad_detal.num_caja%TYPE := NULL;
    p_nro_caja      ape_lista_picad_detal.num_caja%TYPE;
    ln_valunitnumb  ape_valor_defau_afram.val_unit_numb%TYPE;
    ln_valpacksize  ape_valor_defau_afram.val_pack_size%TYPE;
    ls_codregion    zon_regio.cod_regi%TYPE;
    ls_codzona      zon_zona.cod_zona%TYPE;
    ls_codperiodo   VARCHAR2(100);

  BEGIN

    /* obteniendo id's */
    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante

    /* Obtenemos el Oid Centro de Distribución */
    SELECT app.oid_conf_cent_dist
      INTO lnidcentro
      FROM app_confi_centr_distr app
     WHERE app.cod_cent_dist = pscodigocentro
       AND app.pais_oid_pais = lnidpais;

    /* Obtenemos los valores de Val_Unit_Numb y Val_Pack_Size de la tabla APE_VALOR_DEFAU_AFRAM*/

    SELECT val_unit_numb,
           val_pack_size
      INTO ln_valunitnumb,
           ln_valpacksize
      FROM ape_valor_defau_afram;

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz(lnidlinea,
                    lnidcentro);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */

      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          p_nro_caja := interfazrecord(x).numerocaja;

          IF (p_nro_caja_ante IS NULL OR p_nro_caja_ante = p_nro_caja) THEN
            lnconsecutivo := lnconsecutivo + 1;
          ELSE
            lnconsecutivo := 1;
          END IF;

          /* Obtenemos el Codigo de Región Comercial */

          BEGIN
            SELECT reg.cod_regi
              INTO ls_codregion
              FROM zon_regio reg
             WHERE reg.oid_regi = interfazrecord(x).oidregion;
          EXCEPTION
            WHEN no_data_found THEN
              ls_codregion := '';
          END;

          /* Obtenemos el Código de Zona Comercial */

          BEGIN
            SELECT zn.cod_zona
              INTO ls_codzona
              FROM zon_zona zn
             WHERE zn.zorg_oid_regi = interfazrecord(x).oidregion
               AND zn.oid_zona = interfazrecord(x).oidzona;
          EXCEPTION
            WHEN no_data_found THEN
              ls_codzona := '';
          END;

          /* Obtenemos el Codigo de Campaña*/
          BEGIN
            SELECT b.cod_peri
              INTO ls_codperiodo
              FROM cra_perio       a,
                   seg_perio_corpo b
             WHERE a.peri_oid_peri = b.oid_peri
               AND a.oid_peri = interfazrecord(x).oidperiodo;
          EXCEPTION
            WHEN no_data_found THEN
              ls_codperiodo := NULL;
          END;

          lslinea := interfazrecord(x)
                     .pais || ';' || interfazrecord(x).marca || ';' || interfazrecord(x)
                     .centrodistribucion || ';' || ls_codregion || ';' || ls_codzona || ';' || interfazrecord(x)
                     .linea || ';' || interfazrecord(x).lote || ';' || interfazrecord(x)
                     .fechafacturacion || ';' || interfazrecord(x).consolidado || ';' || interfazrecord(x)
                     .orden || ';' || interfazrecord(x).numerocaja || ';' || interfazrecord(x)
                     .numerounicocaja || ';' || interfazrecord(x).codigoproducto || ';' ||
                      int_fn_conve_caden_texto(interfazrecord(x).descripcionproducto) || ';' || interfazrecord(x)
                     .unidadespicar || ';' || interfazrecord(x).ptlcartondescription || ';' || interfazrecord(x)
                     .ptlnumberofcarton || ';' || ln_valunitnumb || ';' || interfazrecord(x)
                     .afstatus || ';' || interfazrecord(x).afstorenumber || ';' || ls_codperiodo || ';' || interfazrecord(x)
                     .afinvoicenumber || ';' || interfazrecord(x).afaddress1 || ';' || interfazrecord(x)
                     .afaddress2 || ';' || interfazrecord(x).afaddress3 || ';' || interfazrecord(x)
                     .afaddress4 || ';' || interfazrecord(x).afcity || ';' || interfazrecord(x)
                     .afstate || ';' || interfazrecord(x).afzip || ';' || interfazrecord(x).afsize || ';' || interfazrecord(x)
                     .afdescription || ';' || interfazrecord(x).afcodes || ';' || interfazrecord(x)
                     .afroute || ';' || interfazrecord(x).aftoteid || ';' || ln_valpacksize || ';' ||
                      lnconsecutivo || ';' || interfazrecord(x).afmpbline || ';' || interfazrecord(x)
                     .afquantitypicked || ';' || interfazrecord(x).afspecialcode || ';' || interfazrecord(x)
                     .aferrorcode || ';' || interfazrecord(x).aferrorlane || ';' || interfazrecord(x)
                     .aflotnumber || ';' || ls_codperiodo || ';' || interfazrecord(x)
                     .chequeocomercial;

          utl_file.put_line(v_handle,
                            lslinea);

          p_nro_caja_ante := p_nro_caja;

        END LOOP;

      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;

    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);

    END IF;

    /* Al retornar satisfactoriamente del caso de uso actualiza el estado de la ola y fecha de liberación*/

    UPDATE ape_olas_xdia
       SET val_esta_ola  = 1,
           fec_hora_libe = SYSDATE
     WHERE num_ola = psnumola
       AND ccdi_oid_conf_cent_dist = lnidcentro;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_APE_ENVI_OLAS_WCS3C: ' || ls_sqlerrm);
  END int_pr_ape_envio_olas_wcs3c;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Confirmar Envio de Datos para Etiquetado a WCS (WCS-8)
  Fecha Creacion    : 02/09/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_envio_etiqu_wcs8c
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    psnumola         VARCHAR2,
    pscodigocentro   VARCHAR2
  ) IS

    CURSOR c_interfaz(vnidcentro NUMBER) IS
      SELECT DISTINCT pscodigopais pais,
                      pscodigomarca marca,
                      pscodigocentro centrodistribucion,
                      linar.cod_line_wcs linea,
                      to_char(apicab.fec_factu,
                              'MM/DD/YYYY') fechafacturacion,
                      etqu.num_secu numerounicocaja,
                      psol.val_nume_soli consolidado,
                      to_char(etqu.num_tota_caja,
                              '000') totalcajas,
                      to_char(etqu.num_caja,
                              '000') numerocaja,
                      s1.val_i18n descriplinea,
                      NULL tipocaja,
                      NULL textovariable,
                      NULL codigoterritorial,
                      mcli.cod_clie codigo,
                      rtrim(mcli.val_ape1) || ' ' || rtrim(mcli.val_ape2) || ', ' ||
                      rtrim(mcli.val_nom1) nombre,
                      NULL direccionp1,
                      NULL direccionp2,
                      NULL direccionp3,
                      NULL direccionp4,
                      '0' chequeocomercial,
                      psol.val_nume_soli boletaentrega,
                      NULL codigoproducto,
                      NULL nombreproducto,
                      NULL indcajamaestra,
                      mcli.oid_clie oidcliente,
                      psol.sbti_oid_subt_clie oidsubtipocliente,
                      psol.tccl_oid_tccl_flet oidtipoclasifica,
                      psol.clas_oid_clas_flet oidclasifica,
                      apidt.tcem_oid_tipo_caja_emba oidtipocajaembalaje,
                      apicab.zorg_oid_regi oidregion,
                      apicab.zzon_oid_zona oidzona,
                      apicab.zscc_oid_secc oidseccion
        FROM ape_lista_picad_detal apidt,
             ape_lista_picad_cabec apicab,
             ape_linea_armad       linar,
             ape_etiqu             etqu,
             gen_i18n_sicc_pais    s1,
             ped_solic_cabec       psol,
             mae_clien             mcli
       WHERE apidt.lpca_oid_list_pica_cabe = apicab.oid_list_pica_cabe
         AND apicab.liar_oid_line_arma = linar.oid_line_arma
         AND apicab.oid_list_pica_cabe = etqu.lpca_oid_list_pica_cabe
         AND apidt.tcem_oid_tipo_caja_emba = etqu.tcem_oid_tipo_caja_emba
         AND s1.val_oid = apicab.liar_oid_line_arma
         AND s1.attr_enti = 'APE_LINEA_ARMAD'
         AND apicab.soca_oid_soli_cabe = psol.oid_soli_cabe
         AND apicab.clie_oid_clie = mcli.oid_clie
         AND apicab.num_ola = to_number(psnumola)
         AND apicab.ccdi_oid_conf_cent_dist = vnidcentro
       ORDER BY psol.val_nume_soli;

    TYPE interfazrec IS RECORD(
      pais                VARCHAR2(20),
      marca               VARCHAR2(20),
      centrodistribucion  VARCHAR2(20),
      linea               ape_linea_armad.cod_line_wcs%TYPE,
      fechafacturacion    VARCHAR2(10),
      numerounicocaja     ape_etiqu.num_secu%TYPE,
      consolidado         ped_solic_cabec.val_nume_soli%TYPE,
      totalcajas          VARCHAR2(20),
      numerocaja          VARCHAR2(20),
      descriplinea        gen_i18n_sicc_pais.val_i18n%TYPE,
      tipocaja            app_tipo_caja_produ.cod_caja%TYPE,
      textovariable       ape_confi_texto_varia.val_text_vari%TYPE,
      codigoterritorial   VARCHAR2(20),
      codigo              mae_clien.cod_clie%TYPE,
      nombre              VARCHAR2(200),
      direccionp1         VARCHAR2(100),
      direccionp2         mae_clien_direc.val_barr%TYPE,
      direccionp3         zon_valor_estru_geopo.des_geog%TYPE,
      direccionp4         zon_valor_estru_geopo.des_geog%TYPE,
      chequeocomercial    VARCHAR2(20),
      boletaentrega       ped_solic_cabec.val_nume_soli%TYPE,
      codigoproducto      mae_produ.cod_sap%TYPE,
      nombreproducto      gen_i18n_sicc_pais.val_i18n%TYPE,
      indcajamaestra      VARCHAR2(1),
      oidcliente          mae_clien.oid_clie %TYPE,
      oidsubtipocliente   ped_solic_cabec.sbti_oid_subt_clie%TYPE,
      oidtipoclasifica    ped_solic_cabec.tccl_oid_tccl_flet%TYPE,
      oidclasifica        ped_solic_cabec.clas_oid_clas_flet%TYPE,
      oidtipocajaembalaje ape_tipo_caja_embal.oid_tipo_caja_emba%TYPE,
      oidregion           zon_regio.oid_regi%TYPE,
      oidzona             zon_zona.oid_zona%TYPE,
      oidseccion          zon_secci.oid_secc%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo        bas_inter.dir_temp%TYPE;
    v_handle          utl_file.file_type;
    lslinea           VARCHAR2(1000);
    lsnombrearchivo   VARCHAR2(50);
    lscadena1         VARCHAR2(100);
    lscadena2         VARCHAR2(100);
    lsdireccionp1     VARCHAR2(100);
    lsdireccionp2     VARCHAR2(100);
    lsdireccionp3     zon_valor_estru_geopo.des_geog%TYPE;
    lsdireccionp4     zon_valor_estru_geopo.des_geog%TYPE;
    ls_orde_1         zon_valor_estru_geopo.orde_1%TYPE;
    ls_orde_2         zon_valor_estru_geopo.orde_2%TYPE;
    ls_orde_3         zon_valor_estru_geopo.orde_3%TYPE;
    ls_orde_4         zon_valor_estru_geopo.orde_4%TYPE;
    ls_codigoproducto mae_produ.cod_sap%TYPE;
    ls_descproducto   gen_i18n_sicc_pais.val_i18n%TYPE;
    ls_textovariable  ape_confi_texto_varia.val_text_vari%TYPE;
    ln_ind_dire_sepa  seg_param_inter_pais.ind_dire_sepa%TYPE;
    lnidpais          NUMBER;
    lnidcentro        NUMBER;
    lnidlinea         NUMBER;
    lbabrirutlfile    BOOLEAN;
    lscodtipocaja     app_tipo_caja_produ.cod_caja%TYPE;
    lsindcajamaestra  VARCHAR2(10);
    lncontador        NUMBER;
    lscodregion       zon_regio.cod_regi%TYPE;
    lscodzona         zon_zona.cod_zona%TYPE;
    lscodseccion      zon_secci.cod_secc%TYPE;

  BEGIN

    /* obteniendo id's */
    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    /* Obtenemos el Oid Centro de Distribución */
    SELECT app.oid_conf_cent_dist
      INTO lnidcentro
      FROM app_confi_centr_distr app
     WHERE app.cod_cent_dist = pscodigocentro
       AND app.pais_oid_pais = lnidpais;

    -- Se captura el valor de Indicador dirección separada
    SELECT segparam.ind_dire_sepa
      INTO ln_ind_dire_sepa
      FROM seg_param_inter_pais segparam
     WHERE segparam.pais_oid_pais = lnidpais;

    OPEN c_interfaz(lnidcentro);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */

      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          BEGIN

            -- Se obtiene el Tipo de Caja
            SELECT tpcp.cod_caja,
                   to_char(tpce.ind_caja_prod_term)
              INTO lscodtipocaja,
                   lsindcajamaestra
              FROM ape_tipo_caja_embal tpce,
                   app_tipo_caja_produ tpcp
             WHERE tpce.ticp_oid_tipo_caja_prod = tpcp.oid_tipo_caja_prod
               AND tpce.ind_caja_prod_term = 1
               AND tpce.oid_tipo_caja_emba = interfazrecord(x).oidtipocajaembalaje;

          EXCEPTION
            WHEN no_data_found THEN
              lscodtipocaja    := NULL;
              lsindcajamaestra := NULL;
          END;

          IF (lsindcajamaestra IS NOT NULL) THEN
            IF (lsindcajamaestra = '0') THEN
              lsindcajamaestra := ' ';
            ELSE
              lsindcajamaestra := 'M';
            END IF;
          ELSE
            lsindcajamaestra := ' ';
          END IF;

          IF (lsindcajamaestra = 'M') THEN

            -- Verificamos Cantidad de Productos y validamos si es caja maestra

            SELECT COUNT(1)
              INTO lncontador
              FROM ape_lista_picad_detal apidet,
                   ape_lista_picad_cabec apicabe,
                   ped_solic_cabec       psol
             WHERE apidet.lpca_oid_list_pica_cabe = apicabe.oid_list_pica_cabe
               AND apicabe.soca_oid_soli_cabe = psol.oid_soli_cabe
               AND psol.val_nume_soli = interfazrecord(x).consolidado
               AND apidet.num_caja = to_number(interfazrecord(x).numerocaja);

            -- Se valida que tenga un producto al menos

            IF (lncontador = 1) THEN

              -- Si es una caja Maestra, se almacena el Código y la descripción internacionalizable.

              SELECT mpro.cod_sap,
                     int_fn_conve_caden_texto(s1.val_i18n)
                INTO ls_codigoproducto,
                     ls_descproducto
                FROM ape_lista_picad_detal apidet,
                     ape_lista_picad_cabec apicabe,
                     ped_solic_cabec       psol,
                     mae_produ             mpro,
                     gen_i18n_sicc_pais    s1
               WHERE apidet.lpca_oid_list_pica_cabe = apicabe.oid_list_pica_cabe
                 AND apicabe.soca_oid_soli_cabe = psol.oid_soli_cabe
                 AND apidet.prod_oid_prod = mpro.oid_prod
                 AND s1.attr_enti = 'MAE_PRODU'
                 AND s1.val_oid = mpro.oid_prod
                 AND psol.val_nume_soli = interfazrecord(x).consolidado
                 AND apidet.num_caja = to_number(interfazrecord(x).numerocaja);

            ELSE
              ls_codigoproducto := '';
              ls_descproducto   := '';
            END IF;

          ELSE
            ls_codigoproducto := '';
            ls_descproducto   := '';
          END IF;

          -- Se obtiene el texto variable
          ls_textovariable := '';

          IF ((interfazrecord(x)
             .oidsubtipocliente IS NOT NULL OR interfazrecord(x).oidsubtipocliente <> '') AND
             (interfazrecord(x)
             .oidtipoclasifica IS NOT NULL OR interfazrecord(x).oidtipoclasifica <> '') AND
             (interfazrecord(x).oidclasifica IS NOT NULL OR interfazrecord(x).oidclasifica <> '')) THEN

            BEGIN
              SELECT actv.val_text_vari
                INTO ls_textovariable
                FROM ape_confi_texto_varia actv
               WHERE actv.sbti_oid_subt_clie = interfazrecord(x).oidsubtipocliente
                 AND actv.tccl_oid_tipo_clas = interfazrecord(x).oidtipoclasifica
                 AND actv.clas_oid_clas = interfazrecord(x).oidclasifica
                 AND actv.pais_oid_pais = lnidpais
                 AND rownum = 1
               ORDER BY actv.oid_conf_text_vari DESC;
            EXCEPTION
              WHEN no_data_found THEN
                ls_textovariable := '';
            END;

          ELSE

            IF ((interfazrecord(x)
               .oidsubtipocliente IS NOT NULL OR interfazrecord(x).oidsubtipocliente <> '') AND
               (interfazrecord(x)
               .oidtipoclasifica IS NULL OR interfazrecord(x).oidtipoclasifica = '') AND
               (interfazrecord(x).oidclasifica IS NULL OR interfazrecord(x).oidclasifica = '')) THEN

              BEGIN
                SELECT actv.val_text_vari
                  INTO ls_textovariable
                  FROM ape_confi_texto_varia actv
                 WHERE actv.sbti_oid_subt_clie = interfazrecord(x).oidsubtipocliente
                   AND actv.tccl_oid_tipo_clas IS NULL
                   AND actv.clas_oid_clas IS NULL
                   AND actv.pais_oid_pais = lnidpais
                   AND rownum = 1
                 ORDER BY actv.oid_conf_text_vari DESC;
              EXCEPTION
                WHEN no_data_found THEN
                  ls_textovariable := '';
              END;

            END IF;

            IF ((interfazrecord(x)
               .oidsubtipocliente IS NOT NULL OR interfazrecord(x).oidsubtipocliente <> '') AND
               (interfazrecord(x)
               .oidtipoclasifica IS NOT NULL OR interfazrecord(x).oidtipoclasifica <> '') AND
               (interfazrecord(x).oidclasifica IS NULL OR interfazrecord(x).oidclasifica = '')) THEN

              BEGIN
                SELECT actv.val_text_vari
                  INTO ls_textovariable
                  FROM ape_confi_texto_varia actv
                 WHERE actv.sbti_oid_subt_clie = interfazrecord(x).oidsubtipocliente
                   AND actv.tccl_oid_tipo_clas = interfazrecord(x).oidtipoclasifica
                   AND actv.clas_oid_clas IS NULL
                   AND actv.pais_oid_pais = lnidpais
                   AND rownum = 1
                 ORDER BY actv.oid_conf_text_vari DESC;
              EXCEPTION
                WHEN no_data_found THEN
                  ls_textovariable := '';
              END;

            END IF;

            IF ((interfazrecord(x)
               .oidsubtipocliente IS NULL OR interfazrecord(x).oidsubtipocliente = '') AND
               (interfazrecord(x)
               .oidtipoclasifica IS NOT NULL OR interfazrecord(x).oidtipoclasifica <> '') AND
               (interfazrecord(x).oidclasifica IS NOT NULL OR interfazrecord(x).oidclasifica <> '')) THEN

              BEGIN
                SELECT actv.val_text_vari
                  INTO ls_textovariable
                  FROM ape_confi_texto_varia actv
                 WHERE actv.sbti_oid_subt_clie IS NULL
                   AND actv.tccl_oid_tipo_clas = interfazrecord(x).oidtipoclasifica
                   AND actv.clas_oid_clas = interfazrecord(x).oidclasifica
                   AND actv.pais_oid_pais = lnidpais
                   AND rownum = 1
                 ORDER BY actv.oid_conf_text_vari DESC;
              EXCEPTION
                WHEN no_data_found THEN
                  ls_textovariable := '';
              END;

            END IF;

          END IF;

          -- Obtenemos la Region
          BEGIN
            SELECT r.cod_regi
              INTO lscodregion
              FROM zon_regio r
             WHERE r.oid_regi = interfazrecord(x).oidregion;
          EXCEPTION
            WHEN no_data_found THEN
              lscodregion := '  ';
          END;

          -- Obtenemos la Zona
          BEGIN
            SELECT z.cod_zona
              INTO lscodzona
              FROM zon_zona z
             WHERE z.oid_zona = interfazrecord(x).oidzona
               AND z.zorg_oid_regi = interfazrecord(x).oidregion;
          EXCEPTION
            WHEN no_data_found THEN
              lscodzona := '  ';
          END;

          -- Obtenemos la Sección
          BEGIN
            SELECT c.cod_secc
              INTO lscodseccion
              FROM zon_secci c
             WHERE c.zzon_oid_zona = interfazrecord(x).oidzona
               AND c.oid_secc = interfazrecord(x).oidseccion;
          EXCEPTION
            WHEN no_data_found THEN
              lscodseccion := '  ';
          END;

          --Se obtiene la Dirección P1
          SELECT substr(ltrim(rtrim(stv.des_abrv_tipo_via)) || ' ' ||
                        ltrim(rtrim(mcld.val_nomb_via)) || ' ' || ltrim(rtrim(mcld.zvia_oid_via)) || ' ' ||
                        ltrim(rtrim(zv.nom_via)) || ' ' || ltrim(rtrim(mcld.num_ppal)) || ' ' ||
                        ltrim(rtrim(mcld.val_obse)),
                        1,
                        100),
                 substr(ltrim(rtrim(mcld.val_obse)) || ' ' || ltrim(rtrim(mcld.val_barr)),
                        1,
                        100),
                 substr(ltrim(rtrim(mcld.val_barr)),
                        1,
                        100)
            INTO lscadena1,
                 lscadena2,
                 lsdireccionp2
            FROM mae_clien_direc mcld,
                 seg_tipo_via    stv,
                 zon_via         zv
           WHERE mcld.tivi_oid_tipo_via = stv.oid_tipo_via(+)
             AND mcld.zvia_oid_via = zv.oid_via(+)
             AND mcld.clie_oid_clie = interfazrecord(x).oidcliente
             AND mcld.ind_elim = 0
             AND mcld.ind_dire_ppal = 1;

          IF (ln_ind_dire_sepa IS NOT NULL) THEN
            lsdireccionp1 := lscadena1;
          ELSE
            lsdireccionp1 := lscadena2;
          END IF;

          -- Se obtiene el nombre de la Ciudad (Provincia)
          BEGIN
            SELECT zge.orde_1,
                   zge.orde_2,
                   zge.orde_3,
                   zge.orde_4
              INTO ls_orde_1,
                   ls_orde_2,
                   ls_orde_3,
                   ls_orde_4
              FROM mae_clien_direc       mcdi,
                   zon_terri             zte,
                   zon_valor_estru_geopo zge
             WHERE mcdi.terr_oid_terr = zte.oid_terr
               AND zte.vepo_oid_valo_estr_geop = zge.oid_valo_estr_geop
               AND mcdi.clie_oid_clie = interfazrecord(x).oidcliente
               AND mcdi.ind_elim = 0
               AND mcdi.ind_dire_ppal = 1;
          EXCEPTION
            WHEN no_data_found THEN
              ls_orde_1 := NULL;
              ls_orde_2 := NULL;
              ls_orde_3 := NULL;
              ls_orde_4 := NULL;
          END;

          BEGIN
            SELECT zvgp.des_geog
              INTO lsdireccionp3
              FROM zon_valor_estru_geopo zvgp
             WHERE zvgp.orde_1 = ls_orde_1
               AND zvgp.orde_2 = ls_orde_2
               AND zvgp.orde_3 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              lsdireccionp3 := '';
          END;

          -- Se obtiene el nombre del Departamento
          BEGIN
            SELECT zvgp.des_geog
              INTO lsdireccionp4
              FROM zon_valor_estru_geopo zvgp
             WHERE zvgp.orde_1 = ls_orde_1
               AND zvgp.orde_2 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              lsdireccionp4 := '';
          END;

          lslinea := interfazrecord(x)
                     .pais || ';' || interfazrecord(x).marca || ';' || interfazrecord(x)
                     .centrodistribucion || ';' || interfazrecord(x).linea || ';' || interfazrecord(x)
                     .fechafacturacion || ';' || interfazrecord(x).numerounicocaja || ';' || interfazrecord(x)
                     .consolidado || ';' || interfazrecord(x).totalcajas || ';' || interfazrecord(x)
                     .numerocaja || ';' || interfazrecord(x).descriplinea || ';' || lscodtipocaja || ';' ||
                      ls_textovariable || ';' || lscodregion || lscodzona || lscodseccion || ';' || interfazrecord(x)
                     .codigo || ';' || interfazrecord(x).nombre || ';' || lsdireccionp1 || ';' ||
                      lsdireccionp2 || ';' || lsdireccionp3 || ';' || lsdireccionp4 || ';' || interfazrecord(x)
                     .chequeocomercial || ';' || interfazrecord(x).boletaentrega || ';' ||
                      ls_codigoproducto || ';' || ls_descproducto || ';' || lsindcajamaestra;

          utl_file.put_line(v_handle,
                            lslinea);

        END LOOP;

      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;

    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);

    END IF;

    /* Al retornar satisfactoriamente del caso de uso actualiza el estado de la ola y fecha de liberación*/

    UPDATE ape_olas_xdia
       SET val_esta_ola  = 1,
           fec_hora_libe = SYSDATE
     WHERE num_ola = psnumola
       AND ccdi_oid_conf_cent_dist = lnidcentro;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_APE_ENVI_ETIQU_WCS8C: ' || ls_sqlerrm);
  END int_pr_ape_envio_etiqu_wcs8c;

  /**************************************************************************
       Descripcion       : Generar numeración de asignaciones por Producto en versión Balanceada
       Autor             : Nicolás López
       Fecha Creacion    : 20/09/2010
       Parametros Entrada :
              psCodigoMarca
              psCodigoCanal
              psCodigoPeriodo
              psCodigoCentro
              psVal_version
  ***************************************************************************/
  PROCEDURE ape_pr_gener_numer_asgpr_verbl
  (
    pscodigopais    VARCHAR2,
    pscodigomarca   VARCHAR2,
    pscodigocanal   VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocentro  VARCHAR2,
    psval_version   VARCHAR2
  ) IS
    p_nuevo        ape_asign_produ_anaqu_cabec.oid_asig_prod_anaq_cabe %TYPE;
    w_filas        NUMBER := 1000;
    p_cod_sap_ante mae_produ.cod_sap %TYPE := NULL;
    p_cod_sap      mae_produ.cod_sap %TYPE;
    p_correlativo  ape_asign_produ_anaqu_detal.num_afra_lane_numb %TYPE;

    lnidpais    NUMBER;
    lnidmarca   NUMBER;
    lnidcanal   NUMBER;
    lnidcentro  NUMBER;
    lnidperiodo NUMBER;

    TYPE tmptablaasignarproductoanaquel IS RECORD(
      oid_asig_prod_anaq ape_asign_produ_anaqu_detal.oid_asig_prod_anaq %TYPE,
      cod_sap            mae_produ.cod_sap %TYPE,
      num_anaq           ape_mapa_centr_distr_detal.num_anaq %TYPE);

    TYPE tablaregasignarprodanaquel IS TABLE OF tmptablaasignarproductoanaquel;
    tablaregasignarprodanaqrecord tablaregasignarprodanaquel;

    -- Se obtienen los datos de Asignación Productos Anaqueles
    CURSOR numer_asign_produ
    (
      vn_oid_centro  NUMBER,
      vn_oid_periodo NUMBER,
      vs_val_version VARCHAR2
    ) IS
      SELECT ascdpr.oid_asig_prod_anaq,
             mprod.cod_sap,
             mpcddt.num_anaq
        FROM ape_asign_produ_anaqu_cabec ascbpr,
             ape_asign_produ_anaqu_detal ascdpr,
             ape_mapa_centr_distr_cabec  mpcdcb,
             ape_mapa_centr_distr_detal  mpcddt,
             mae_produ                   mprod
       WHERE ascbpr.oid_asig_prod_anaq_cabe = ascdpr.apac_oid_asig_prod_anaq_cabe
         AND ascbpr.mcdc_oid_mapa_cent_dist_cabe = mpcdcb.oid_mapa_cent_dist_cabe
         AND mpcdcb.oid_mapa_cent_dist_cabe = mpcddt.mcdc_oid_mapa_cent_dist_cabe
         AND ascdpr.mcdd_oid_mapa_cent_dist_deta = mpcddt.oid_mapa_cent_dist_deta
         AND ascdpr.prod_oid_prod = mprod.oid_prod
         AND mpcdcb.ccdi_oid_conf_cent_dist = vn_oid_centro
         AND ascbpr.perd_oid_peri = vn_oid_periodo
         AND ascbpr.val_vers = vs_val_version
       ORDER BY mprod.cod_sap || mpcddt.num_anaq;

  BEGIN

    /* obteniendo id's */
    lnidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante
    lnidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca); -- id del marca consultante
    lnidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal); -- id del canal consultante
    lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                              lnidmarca,
                                                              lnidcanal); -- id del periodo consultante

    /* Obtenemos el Oid Centro de Distribución */
    SELECT app.oid_conf_cent_dist
      INTO lnidcentro
      FROM app_confi_centr_distr app
     WHERE app.cod_cent_dist = pscodigocentro
       AND app.pais_oid_pais = lnidpais;

    OPEN numer_asign_produ(lnidcentro,
                           lnidperiodo,
                           psval_version);
    LOOP
      FETCH numer_asign_produ BULK COLLECT
        INTO tablaregasignarprodanaqrecord LIMIT w_filas;
      IF tablaregasignarprodanaqrecord.count > 0 THEN
        FOR x IN tablaregasignarprodanaqrecord.first .. tablaregasignarprodanaqrecord.last
        LOOP

          p_cod_sap := tablaregasignarprodanaqrecord(x).cod_sap;

          IF (p_cod_sap_ante IS NULL OR p_cod_sap_ante <> p_cod_sap) THEN

            p_correlativo  := 0;
            p_correlativo  := p_correlativo + 1;
            p_cod_sap_ante := p_cod_sap;
          ELSE
            p_correlativo  := p_correlativo + 1;
            p_cod_sap_ante := p_cod_sap;
          END IF;

          --Se numera el atributo Num_Afra_Lane_Numb

          UPDATE ape_asign_produ_anaqu_detal
             SET num_afra_lane_numb = p_correlativo
           WHERE oid_asig_prod_anaq = tablaregasignarprodanaqrecord(x).oid_asig_prod_anaq;

        END LOOP;
      END IF;
      EXIT WHEN numer_asign_produ%NOTFOUND;
    END LOOP;
    CLOSE numer_asign_produ;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR APE_PR_GENER_NUMER_ASGPR_VERBL: ' || ls_sqlerrm);
  END ape_pr_gener_numer_asgpr_verbl;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Facturas Anuladas a WCS
  Fecha Creacion    : 22/09/2010
  Autor             : Nicolás López
  ***************************************************************************/
  PROCEDURE int_pr_ape_factu_anula_wcs
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2
  ) IS

    CURSOR c_interfaz IS
      SELECT pscodigopais        pais,
             pscodigomarca       marca,
             apcd.cod_cent_dist  centrodistribucion,
             linar.cod_line_wcs  linea,
             psolc.val_nume_soli orden
        FROM ped_solic_cabec       psolc,
             app_confi_centr_distr apcd,
             ape_lista_picad_cabec apicad,
             bel_almac             bealm,
             ape_linea_armad       linar,
             ape_gtt_cons_anula    cons
       WHERE psolc.almc_oid_alma = bealm.oid_alma
         AND bealm.ccdi_oid_confi_centr_distr = apcd.oid_conf_cent_dist
         AND psolc.oid_soli_cabe = apicad.soca_oid_soli_cabe
         AND apicad.liar_oid_line_arma = linar.oid_line_arma
         AND cons.num_recl = psolc.val_nume_soli
         AND cons.cod_erro = '00';

    TYPE interfazrec IS RECORD(
      pais               VARCHAR2(20),
      marca              VARCHAR2(20),
      centrodistribucion VARCHAR2(20),
      linea              ape_linea_armad.cod_line_wcs %TYPE,
      orden              ped_solic_cabec.val_nume_soli %TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;

  BEGIN

    /* Generando Archivo XML (Detalle) */
    lbabrirutlfile := TRUE;

    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x).pais || ';' || interfazrecord(x).marca || ';' || interfazrecord(x)
                     .centrodistribucion || ';' || interfazrecord(x).linea || ';' || interfazrecord(x)
                     .orden;

          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;

      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;

    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);

    END IF;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_APE_FACTU_ANULA_WCS: ' || ls_sqlerrm);
  END int_pr_ape_factu_anula_wcs;

  /**************************************************************************
      Descripcion : Devuelve la cadena de texto sin caracteres especiales
      Fecha Creacion : 17/11/2010
      Autor: Nicolás López
  ***************************************************************************/
  FUNCTION int_fn_conve_caden_texto(vscadena VARCHAR2) RETURN VARCHAR2 IS
    CURSOR c_caracteres IS
      SELECT car_espec,
             car_reemp
        FROM ape_carac_espec;

    ls_cadenatexto gen_i18n_sicc_pais.val_i18n%TYPE;

    searchstr  VARCHAR2(200) := 'a';
    replacestr VARCHAR2(200) := 'a';
    car_espe   VARCHAR2(1);
    car_reem   VARCHAR2(1);

  BEGIN
    OPEN c_caracteres;
    LOOP
      FETCH c_caracteres
        INTO car_espe,
             car_reem;
      EXIT WHEN(c_caracteres%NOTFOUND);

      searchstr  := searchstr || car_espe;
      replacestr := replacestr || car_reem;

    END LOOP;
    CLOSE c_caracteres;

    searchstr  := searchstr || chr(10) || chr(13) || chr(64) || chr(47) || chr(92) || chr(40) ||
                  chr(41) || chr(123) || chr(125) || chr(58) || chr(36) || chr(91) || chr(93);
    replacestr := replacestr || '             ';

    ls_cadenatexto := TRIM(translate(vscadena,
                                     searchstr,
                                     replacestr));

    RETURN ls_cadenatexto;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN '';
  END int_fn_conve_caden_texto;
  FUNCTION int_fn_obtie_estim_mav(pscodigopais VARCHAR2, psoidprod NUMBER, psoidtipofer NUMBER, psoidperi NUMBER) RETURN NUMBER IS


    lsnuevmav VARCHAR2(1) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'IND_NUEV_MAV'),'N');
    lnresult NUMBER:=0;

  BEGIN

    if lsnuevmav='S' then

           SELECT SUM(val_base_esti_dest * num_unid_clie) into lnresult
            FROM mav_detal_mav
           WHERE tepr_oid_tipo_esta_proc <> 5
             AND prod_oid_prod = psoidprod
             AND tofe_oid_tipo_ofer = psoidtipofer
             --AND cod_orig = 'MAV'
             AND perd_oid_peri = psoidperi;
    else
           SELECT sum(M.Val_Base_Esti_Dest*nvl(m.num_unid_prod,1)) into lnresult
               FROM MAV_PARAM_CONFI M,
                     own_comun.PRE_TIPO_OFERT tp,
                     cra_perio cr
                   WHERE M.COD_TIPO_OFER = TP.COD_TIPO_OFER
                   AND cr.val_nomb_peri = M.Cam_Para_Mav
                     AND m.cod_esta_mav <> 5
                     AND m.prod_oid_prod = psoidprod
                     AND TP.OID_TIPO_OFER = psoidtipofer
                     AND cr.oid_peri = psoidperi;
    end if;

    RETURN lnresult;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
  END int_fn_obtie_estim_mav;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Productos Material Gerente
                      de Zona y Regional
  Fecha Creacion    : 23/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_produ_mater
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    CURSOR c_interfaz(p_fecha DATE) IS
            select  COD_REGI,
                       COD_ZONA,
                        to_char(FEC_FACT,'YYYYMMDD'),
                        GEN_FN_CALCU_PERIO(COD_PERI, 2) COD_PERI,
                        COD_SAP,
                        SUM(num_unid_aten),
                        SUM(faltante),
                        tipo,
                        SUBTIPO_MATERIAL,
                        OBS_USO
            from
            (
            select distinct c.oid_soli_posi, me.cod_regi
            , nvl(me.COD_ZONA,decode(me.tip_cons_desp,'IN','XXX1','XXXX')) COD_ZONA
            , a.fec_fact, M.CAM_PARA_MAV cod_peri, e.cod_sap, c.num_unid_aten, c.num_unid_dema_real-c.num_unid_aten faltante,  k.zorg_oid_regi, DECODE(ME.TIP_CONS_DESP, 'CA' ,'IN','AZ','GZ','AR','GR',ME.TIP_CONS_DESP) tipo
            ,        M.VAL_OBSE OBS_USO,
       M.COD_ACTI SUBTIPO_MATERIAL,
       (SELECT GEN.VAL_I18N
          FROM GEN_I18N_SICC GEN
         WHERE GEN.ATTR_ENTI = 'MAV_ACTIV'
           AND GEN.VAL_OID = M.COD_ACTI AND GEN.IDIO_OID_IDIO =1 ) TIPO_MATERIAL
            from ped_solic_cabec a, ped_solic_cabec b, ped_solic_posic c, mae_produ e, cra_perio f, seg_perio_corpo g
            , ped_tipo_solic_pais h, ped_tipo_solic i, gen_i18n_sicc_comun j, zon_zona k, zon_regio l
            , MAV_SOLIC_ENVIO_CONFI MSE
            , MAV_ENVIO_CONFI ME
            , MAV_PARAM_CONFI M
            , MAV_ACTIV AC
            , mae_clien y
            where a.oid_soli_cabe=b.soca_oid_soli_cabe and b.oid_soli_cabe=c.soca_oid_soli_cabe and c.prod_oid_prod=e.oid_prod
            and a.perd_oid_peri=f.oid_peri and f.peri_oid_peri=g.oid_peri and g.cod_peri=psperiodo and a.feC_fact=p_fecha
            and a.tspa_oid_tipo_soli_pais=h.oid_tipo_soli_pais and a.clie_oid_clie=y.oid_clie
            and h.tsol_oid_tipo_soli=i.oid_tipo_soli and i.oid_tipo_soli=j.val_oid and j.attr_enti='PED_TIPO_SOLIC' and j.val_i18n like '%MAV%'
            and a.clie_oid_clie=k.clie_oid_clie(+)
            and m.cod_sap=e.cod_sap
            and m.val_codi_vent=c.val_codi_vent
            and a.clie_oid_clie=l.clie_oid_clie(+)
            and M.ATOF_ACTI_OID_ACTI = AC.OID_ACTI
            AND AC.IND_ACTI=1 AND AC.EST_REGI=1
            and M.TICL_OID_TIPO_CLIE =4
            and M.PAIS_COD_PAIS = ME.PAIS_COD_PAIS AND M.COR_PARA_CONF = ME.COR_PARA_CONF
            AND M.IND_ACTI=1 AND M.EST_REGI=1
            and ME.OID_ENVI = MSE.MENV_OID_ENVI
            AND ME.IND_ACTI=1 AND ME.EST_REGI=1
            and MSE.SOCA_OID_SOLI_CABE =b.oid_soli_cabe            )
            group by COD_REGI,
                       COD_ZONA,
                        FEC_FACT,
                        COD_PERI,
                        COD_SAP,
                        tipo,
                        SUBTIPO_MATERIAL,
                        OBS_USO
                        ;

    TYPE interfazrec IS RECORD(
      codigoregion         VARCHAR2(2),
      codigozona           VARCHAR2(4),
      fecha                VARCHAR2(8),
      campanya             VARCHAR2(6),
      codigosap            VARCHAR2(9),
      unidades             VARCHAR2(10),
      faltante             VARCHAR2(10),
      tipomaterial         VARCHAR2(2),
      subtipomaterial      VARCHAR2(100),
      observacion          VARCHAR2(100)
      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    ldfecha         date;
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN

    select fec_proc into ldfecha
    from bas_ctrl_fact a where a.sta_camp='0' and a.ind_camp_act='1';
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz(ldfecha);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigoregion || ';' || interfazrecord(x).codigozona || ';' || interfazrecord(x)
                    .fecha || ';' || interfazrecord(x).campanya || ';' || interfazrecord(x)
                    .codigosap || ';' || interfazrecord(x).unidades
                    || ';' || interfazrecord(x).tipomaterial
                    || ';' || interfazrecord(x).subtipomaterial
                    || ';' || interfazrecord(x).faltante
                    || ';' || interfazrecord(x).observacion
                    ;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_produ_mater: ' || ls_sqlerrm);
  END int_pr_ape_envi_produ_mater;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Cabecera de Boletas
                      de Recojo
  Fecha Creacion    : 23/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_cabec_bolet
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  ) IS

    lsFecProc      bas_Ctrl_Fact.Fec_Proc%TYPE;
    lsTipoSol      ped_solic_cabec.tspa_oid_tipo_soli_pais%TYPE;

    CURSOR c_interfaz IS
      ----Select '0000000000' as Consultora, '00000000' as Boleta, '00' as Estado, '0' as indicador from dual;

      /*select b.cod_clie,b.COD_CABE_BORE,decode(B.ESBO_OID_ESTA_BOR1,1,'G','N') estado,
          decode((select count(*) from ped_solic_Cabec psc
                    where PSC.CLIE_OID_CLIE = B.CLIE_OID_CLIE
                    and PSC.TSPA_OID_TIPO_SOLI_PAIS = lsTipoSol
                    and PSC.FEC_FACT = lsFecProc
          ),0,'N','S') indicador
      from (select distinct COD_CABE_BORE nro_boleta
               from INT_BOLET_RECOJ_XML) a,
          INT_REC_CABEC_BOREC b
      where A.nro_boleta = B.COD_CABE_BORE;*/
      /*
          decode((select count(*) from ped_solic_Cabec psc
                    where PSC.CLIE_OID_CLIE = B.CLIE_OID_CLIE
                    and PSC.TSPA_OID_TIPO_SOLI_PAIS = lsTipoSol
                    and trunc(PSC.FEC_FACT) = lsFecProc --- psfecha --- lsFecProc
          ),0,'N','S') indicador
      */

      select b.cod_clie,b.COD_CABE_BORE,decode(B.ESBO_OID_ESTA_BOR2,5,'S',6,'N','G') estado,
          decode( B.num_reco,2, decode( b.ind_envi_con_ped2,1,'S','N') ,
                                decode( b.ind_envi_con_ped1,1,'S','N')) indicador
      from (select distinct rab.cod_Cabe_bore
                from rec_audit_borec rab
                where nvl(RAB.Ind_Envi,0) = 0
                and RAB.COD_ESTA_BORE in('NX','EX')
                and RAB.FLAG_PROC = '1') a,
                ----where trunc(RAB.FEC_INGR) = lsFecProc ---- psfecha --- lsFecProc
          INT_REC_CABEC_BOREC b
      where A.cod_Cabe_bore   = B.COD_CABE_BORE
          and B.ESBO_OID_ESTA_BOR1 = 4 ;


    TYPE interfazrec IS RECORD(
      codigoconsultora       VARCHAR2(10),
      numeroboleta           VARCHAR2(8),
      estadoboleta           VARCHAR2(2),
      indicadorpedido        VARCHAR2(1));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;


  BEGIN

    /*
    --- Fecha que se esta facturando
    select fec_proc into lsFecProc
    from bas_Ctrl_Fact bcf
    where BCF.STA_CAMP = '0' and BCF.IND_CAMP_ACT = '1';
    */

    --- Tipo de Solicitud de consolidado
    select tsp.oid_tipo_soli_pais into lsTipoSol
    from ped_tipo_solic_pais tsp, ped_tipo_solic ts
    where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
    and TS.COD_TIPO_SOLI = 'C1';

    lsFecProc := to_Date(psfecha,'DD/MM/YYYY');

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigoconsultora || ';' || interfazrecord(x).numeroboleta || ';' || interfazrecord(x)
                    .estadoboleta || ';' || interfazrecord(x).indicadorpedido;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_cabec_bolet: ' || ls_sqlerrm);
  END int_pr_ape_envi_cabec_bolet;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Detalle de Boletas de
                      Recojo
  Fecha Creacion    : 23/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_detal_bolet
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  ) IS

    lsFecProc      bas_Ctrl_Fact.Fec_Proc%TYPE;

    CURSOR c_interfaz IS
      ----Select '0000000000' as Consultora, '00000000' as Boleta, '00' as Estado, '0' as indicador from dual;
      /*select b.cod_clie,b.COD_CABE_BORE,c.cod_prod,C.NUM_UNID_RECL
      from (select distinct COD_CABE_BORE nro_boleta
         from INT_BOLET_RECOJ_XML) a,
         INT_REC_CABEC_BOREC b, INT_REC_LINEA_BOREC c
      where A.nro_boleta = B.COD_CABE_BORE
      and B.COD_CABE_BORE = C.COD_CABE_BORE;*/

      select b.cod_clie,b.COD_CABE_BORE,c.cod_prod,C.NUM_UNID_RECL
      from (select distinct rab.cod_Cabe_bore
                from rec_audit_borec rab
                where nvl(RAB.Ind_Envi,0) = 0
                and RAB.COD_ESTA_BORE in('NX','EX')
                and RAB.FLAG_PROC = '1') a,
                ----where trunc(RAB.FEC_INGR) = lsFecProc  --- psfecha
          INT_REC_CABEC_BOREC b , INT_REC_LINEA_BOREC c
      where A.cod_Cabe_bore   = B.COD_CABE_BORE
          and B.COD_CABE_BORE = C.COD_CABE_BORE
          and B.ESBO_OID_ESTA_BOR1 = 4 ;

    TYPE interfazrec IS RECORD(
      codigoconsultora       VARCHAR2(10),
      numeroboleta           VARCHAR2(8),
      codigosap              VARCHAR2(9),
      unidadesproducto       VARCHAR2(5));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN

    lsFecProc := to_Date(psfecha,'DD/MM/YYYY');


    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigoconsultora || ';' || interfazrecord(x).numeroboleta || ';' || interfazrecord(x)
                    .codigosap || ';' || interfazrecord(x).unidadesproducto;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    --- Actualiza archivo
    update rec_audit_borec rab
    set Ind_Envi = '1'
    where nvl(Ind_Envi,0) = 0
    and COD_ESTA_BORE in('NX','EX')
    and FLAG_PROC = '1';

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_detal_bolet: ' || ls_sqlerrm);
  END int_pr_ape_envi_detal_bolet;

 /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Cabecera de Boletas
                      de Recojo Facturacion
  Fecha Creacion    : 06/01/2015
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_cabec_bofac
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  ) IS

    lsFecProc                 VARCHAR2(8);
    lsCodigoCompania          BAS_PAIS_COMPA.COD_COMP%TYPE;
    lsCodPeriodoFact          BAS_CTRL_FACT.COD_PERI%TYPE;
    lsFecha                   date;

    CURSOR c_interfaz IS
      select b.COD_CABE_BORE nrobolreco,
             b.cod_clie,
             B.num_reco nroreco,
             decode(B.num_reco,
                    2,
                    decode(b.ind_envi_con_ped2, 1, 'S', 'N'),
                    decode(b.ind_envi_con_ped1, 1, 'S', 'N')) indicador,
             decode(B.num_reco,
                    2,
                    decode(b.ind_envi_con_ped2, 1, b.val_nume_soli_ped2, ''),
                    decode(b.ind_envi_con_ped1, 1, b.val_nume_soli_ped1, '')) pedido,
             to_char((select fec
                       from ped_segui_pedid a, ped_solic_cabec b
                      where a.soca_oid_soli_cabe = b.oid_soli_cabe
                        and b.val_nume_soli =
                            decode(B.num_reco,
                                   2,
                                   decode(b.ind_envi_con_ped2,
                                          1,
                                          b.val_nume_soli_ped2,
                                          ''),
                                   decode(b.ind_envi_con_ped1,
                                          1,
                                          b.val_nume_soli_ped1,
                                          ''))),
                     'yyyyMMdd') fecentrega
        from (select distinct rab.cod_Cabe_bore from INT_BOLET_RECOJ_XML rab) a,
             INT_REC_CABEC_BOREC b
       where A.cod_Cabe_bore = B.COD_CABE_BORE
         and B.COD_PAIS = pscodigopais;


    TYPE interfazrec IS RECORD(
      nrobolreco        number(12),
      codigoConsultora  VARCHAR2(15),
      nroreco           number(4),
      indicador         VARCHAR2(1),
      pedido            number(10),
      fecentrega        VARCHAR2(8));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;


  BEGIN
    --- Codigo de Compañia
 select cod_comp
   into lsCodigoCompania
   from BAS_PAIS_COMPA bpc, bas_ctrl_fact bcf
  where BPC.COD_PAIS = BCF.COD_PAIS
    and BPC.COD_PAIS = pscodigopais
    and rownum = 1;

    --Recupermoa el periodo de Facturacion
  SELECT COD_PERI
    INTO lsCodPeriodoFact
    FROM BAS_CTRL_FACT
   WHERE COD_PAIS = psCodigoPais
     AND STA_CAMP = '0'
     AND IND_CAMP_ACT = '1';

    lsFecProc := substr(psfecha,7,4)||substr(psfecha,4,2)||substr(psfecha,1,2);

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := pscodigopais || ';' ||
                     lsCodigoCompania || ';' ||
                     interfazrecord(x).nrobolreco || ';' ||
                     interfazrecord(x).codigoConsultora || ';' ||
                     lsCodPeriodoFact || ';' ||
                     lsFecProc || ';' ||
                     interfazrecord(x).nroreco || ';' ||
                     interfazrecord(x).indicador || ';' ||
                     interfazrecord(x).pedido || ';' ||
                     interfazrecord(x).fecentrega;

          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_cabec_bofac: ' || ls_sqlerrm);
  END int_pr_ape_envi_cabec_bofac;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Detalle de Boletas de
                      Recojo Facturacion
  Fecha Creacion    : 06/01/2015
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_ape_envi_detal_bofac
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  ) IS

    lsCodigoCompania          BAS_PAIS_COMPA.COD_COMP%TYPE;

    CURSOR c_interfaz IS
 select b.cod_clie,
        b.COD_CABE_BORE,
        c.cod_prod,
        C.NUM_UNID_RECL,
        c.val_prec,
        c.val_prec_cont,
        d.val_cost_estd,
        gen_pkg_gener.gen_fn_devuelve_des_perio(e.PERD_OID_PERI_RECL) periodopv
   from (select distinct rab.cod_Cabe_bore from INT_BOLET_RECOJ_XML rab) a,
        INT_REC_CABEC_BOREC b,
        INT_REC_LINEA_BOREC c,
        mae_produ d,
        rec_cabec_recla e
  where A.cod_Cabe_bore = B.COD_CABE_BORE
    and B.COD_CABE_BORE = C.COD_CABE_BORE
    and c.care_oid_Cabe_Recl = e.oid_Cabe_recl
    and c.cod_prod = d.cod_sap(+)
    and c.cod_pais = pscodigopais;

    TYPE interfazrec IS RECORD(
      codigoconsultora       VARCHAR2(15),
      numeroboleta           number(12),
      codigosap              VARCHAR2(20),
      unidadesproducto       number(4),
      precio                 number(12,2),
      precioContable         number(12,2),
      costoStandard          number(12,2),
      campanaEmision         varchar2(6));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN

   --- Codigo de Compañia
 select cod_comp
   into lsCodigoCompania
   from BAS_PAIS_COMPA bpc, bas_ctrl_fact bcf
  where BPC.COD_PAIS = BCF.COD_PAIS
    and BPC.COD_PAIS = pscodigopais
    and rownum = 1;

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := pscodigopais || ';' ||
                     lsCodigoCompania || ';' ||
                     interfazrecord(x).numeroboleta || ';' ||
                     interfazrecord(x).codigosap || ';' ||
                     interfazrecord(x).campanaEmision || ';' ||
                     interfazrecord(x).unidadesproducto || ';' ||
                     interfazrecord(x).precio || ';' ||
                     interfazrecord(x).precioContable || ';' ||
                     interfazrecord(x).costoStandard;

          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_envi_detal_bofac: ' || ls_sqlerrm);
  END int_pr_ape_envi_detal_bofac;
  
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepción de Parametrización para
                      Picado Cabecera
  Fecha Creacion    : 19/01/2016
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_ape_recep_picad_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario  VARCHAR2
  ) IS 
  
    TYPE t_COD_CLIE IS TABLE OF IMP_TMP_PRINT_PICAD.COD_CLIE%TYPE;
    TYPE t_COD_REGI IS TABLE OF IMP_TMP_PRINT_PICAD.COD_REGI%TYPE;
    TYPE t_COD_ZONA IS TABLE OF IMP_TMP_PRINT_PICAD.COD_ZONA%TYPE;
    TYPE t_LIN_ARMA IS TABLE OF IMP_TMP_PRINT_PICAD.LIN_ARMA%TYPE;
    TYPE t_TOT_CAJA IS TABLE OF IMP_TMP_PRINT_PICAD.TOT_CAJA%TYPE;
    TYPE t_TIP_SERV IS TABLE OF IMP_TMP_PRINT_PICAD.TIP_SERV%TYPE;
    TYPE t_NUM_PEDI IS TABLE OF IMP_TMP_PRINT_PICAD.NUM_PEDI%TYPE;
    TYPE t_FEC_FACT IS TABLE OF IMP_TMP_PRINT_PICAD.FEC_FACT%TYPE;
    TYPE t_CAM_FACT IS TABLE OF IMP_TMP_PRINT_PICAD.CAM_FACT%TYPE;
    TYPE t_IND_CHEQ IS TABLE OF IMP_TMP_PRINT_PICAD.IND_CHEQ%TYPE;
    TYPE t_CEN_DIST IS TABLE OF IMP_TMP_PRINT_PICAD.CEN_DIST%TYPE;    
  
    v_COD_CLIE t_COD_CLIE := t_COD_CLIE();
    v_COD_REGI t_COD_REGI := t_COD_REGI();
    v_COD_ZONA t_COD_ZONA := t_COD_ZONA();
    v_LIN_ARMA t_LIN_ARMA := t_LIN_ARMA();
    v_TOT_CAJA t_TOT_CAJA := t_TOT_CAJA();
    v_TIP_SERV t_TIP_SERV := t_TIP_SERV();
    v_NUM_PEDI t_NUM_PEDI := t_NUM_PEDI();
    v_FEC_FACT t_FEC_FACT := t_FEC_FACT();
    v_CAM_FACT t_CAM_FACT := t_CAM_FACT();
    v_IND_CHEQ t_IND_CHEQ := t_IND_CHEQ();
    v_CEN_DIST t_CEN_DIST := t_CEN_DIST();    
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
  
    v_handle utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i BINARY_INTEGER := 0;
  
    t_estructura t_all_estru_archi;
  
  BEGIN
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle,
                                                 t_estructura);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i := i + 1;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
          FOR j IN t_estructura.first .. t_estructura.last
          LOOP
            --dbms_output.put_line(t_estructura(j).pos_camp);
          
            IF j = 1 THEN
              v_COD_CLIE.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_COD_CLIE(i));
            ELSIF j = 2 THEN
              v_COD_REGI.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_COD_REGI(i));
            
            ELSIF j = 3 THEN
              v_COD_ZONA.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_COD_ZONA(i));
            
            ELSIF j = 4 THEN
              v_LIN_ARMA.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_LIN_ARMA(i));
            
            ELSIF j = 5 THEN
              v_TOT_CAJA.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_TOT_CAJA(i));
            
            ELSIF j = 6 THEN
              v_TIP_SERV.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_TIP_SERV(i));
            
            ELSIF j = 7 THEN
              v_NUM_PEDI.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_NUM_PEDI(i));
            
            ELSIF j = 8 THEN
              v_FEC_FACT.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_FEC_FACT(i));
                                                    
            ELSIF j = 9 THEN
              v_CAM_FACT.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_CAM_FACT(i));

            ELSIF j = 10 THEN
              v_IND_CHEQ.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_IND_CHEQ(i));
                                                    
            ELSIF j = 11 THEN
              v_CEN_DIST.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_CEN_DIST(i));                                                                                                        
            
            END IF;
          END LOOP;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    DELETE IMP_TMP_PRINT_PICAD where USU_CREA = pscodigousuario;
  
    FORALL i IN 1 .. v_cod_regi.count    
      INSERT INTO IMP_TMP_PRINT_PICAD (
        COD_CLIE,
        COD_REGI,
        COD_ZONA,
        LIN_ARMA,
        TOT_CAJA,
        TIP_SERV,
        NUM_PEDI,
        FEC_FACT,
        CAM_FACT,
        IND_CHEQ,
        CEN_DIST,
        USU_CREA,
        FEC_CREA)
    	VALUES(
        trim(v_COD_CLIE(i)),
        v_COD_REGI(i),
        v_COD_ZONA(i),
        v_LIN_ARMA(i),
        v_TOT_CAJA(i),
        v_TIP_SERV(i),
        v_NUM_PEDI(i),
        v_FEC_FACT(i),
        v_CAM_FACT(i),
        v_IND_CHEQ(i),
        v_CEN_DIST(i),
        pscodigousuario,
        SYSDATE);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_recep_picad_cabec: ' ||
                              ls_sqlerrm || lslinea);
    
  END int_pr_ape_recep_picad_cabec;
  
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepción de Parametrización para
                      Picado Detalle
  Fecha Creacion    : 19/01/2016
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_ape_recep_picad_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario  VARCHAR2
  ) IS 
  

    TYPE t_TIP_SERV IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.TIP_SERV%TYPE;
    TYPE t_NUM_PEDI IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.NUM_PEDI%TYPE;
    TYPE t_VAL_ANAQ IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.VAL_ANAQ%TYPE;
    TYPE t_NOM_PROD IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.NOM_PROD%TYPE;
    TYPE t_UNI_EMBA IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.UNI_EMBA%TYPE;
    TYPE t_NUM_CAJA IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.NUM_CAJA%TYPE;
    TYPE t_TIP_CAJA IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.TIP_CAJA%TYPE;
    TYPE t_ORD_LIPI IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.ORD_LIPI%TYPE;
    TYPE t_LIN_EMBA IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.LIN_EMBA%TYPE;
    TYPE t_EST_TRAB IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.EST_TRAB%TYPE;
    TYPE t_COD_SAP  IS TABLE OF IMP_TMP_PRINT_PICAD_DETAL.COD_SAP%TYPE;    
  
    v_TIP_SERV t_TIP_SERV := t_TIP_SERV();
    v_NUM_PEDI t_NUM_PEDI := t_NUM_PEDI();
    v_VAL_ANAQ t_VAL_ANAQ := t_VAL_ANAQ();
    v_NOM_PROD t_NOM_PROD := t_NOM_PROD();
    v_UNI_EMBA t_UNI_EMBA := t_UNI_EMBA();
    v_NUM_CAJA t_NUM_CAJA := t_NUM_CAJA();
    v_TIP_CAJA t_TIP_CAJA := t_TIP_CAJA();
    v_ORD_LIPI t_ORD_LIPI := t_ORD_LIPI();
    v_LIN_EMBA t_LIN_EMBA := t_LIN_EMBA();
    v_EST_TRAB t_EST_TRAB := t_EST_TRAB();
    v_COD_SAP  t_COD_SAP  := t_COD_SAP();    
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
  
    v_handle utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i BINARY_INTEGER := 0;
  
    t_estructura t_all_estru_archi;
  
  BEGIN
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle,
                                                 t_estructura);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i := i + 1;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
          FOR j IN t_estructura.first .. t_estructura.last
          LOOP
            --dbms_output.put_line(t_estructura(j).pos_camp);
          
            IF j = 1 THEN
              v_TIP_SERV.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_TIP_SERV(i));
            ELSIF j = 2 THEN
              v_NUM_PEDI.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_NUM_PEDI(i));
            
            ELSIF j = 3 THEN
              v_VAL_ANAQ.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_VAL_ANAQ(i));
            
            ELSIF j = 4 THEN
              v_NOM_PROD.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_NOM_PROD(i));
            
            ELSIF j = 5 THEN
              v_UNI_EMBA.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_UNI_EMBA(i));
            
            ELSIF j = 6 THEN
              v_NUM_CAJA.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_NUM_CAJA(i));
            
            ELSIF j = 7 THEN
              v_TIP_CAJA.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_TIP_CAJA(i));
            
            ELSIF j = 8 THEN
              v_ORD_LIPI.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_ORD_LIPI(i));
                                                    
            ELSIF j = 9 THEN
              v_LIN_EMBA.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_LIN_EMBA(i));

            ELSIF j = 10 THEN
              v_EST_TRAB.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_EST_TRAB(i));
                                                    
            ELSIF j = 11 THEN
              v_COD_SAP.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_COD_SAP(i));                                                                                                        
            
            END IF;
          END LOOP;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    DELETE IMP_TMP_PRINT_PICAD_DETAL where USU_CREA = pscodigousuario;
  
    FORALL i IN 1 .. v_TIP_SERV.count    
      INSERT INTO IMP_TMP_PRINT_PICAD_DETAL (
        TIP_SERV,
        NUM_PEDI,
        VAL_ANAQ,
        NOM_PROD,
        UNI_EMBA,
        NUM_CAJA,
        TIP_CAJA,
        ORD_LIPI,
        LIN_EMBA,
        EST_TRAB,
        COD_SAP,
        USU_CREA,
        FEC_CREA)    	
    	VALUES(
        v_TIP_SERV(i),
        v_NUM_PEDI(i),
        v_VAL_ANAQ(i),
        trim(v_NOM_PROD(i)),
        v_UNI_EMBA(i),
        v_NUM_CAJA(i),
        v_TIP_CAJA(i),
        v_ORD_LIPI(i),
        v_LIN_EMBA(i),
        v_EST_TRAB(i),
        v_COD_SAP(i),
        pscodigousuario,
        SYSDATE);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_recep_picad_detal: ' ||
                              ls_sqlerrm || lslinea);
    
  END int_pr_ape_recep_picad_detal;  
  
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepción de Parametrización para
                      Orden de Impresión
  Fecha Creacion    : 19/01/2016
  Autor             : Gonzalo Huertas
  ***************************************************************************/
  PROCEDURE int_pr_ape_recep_orden_impre
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario  VARCHAR2
  ) IS 
  
    TYPE t_TIP_ORDE IS TABLE OF IMP_PRINT_APE.TIP_ORDE%TYPE;
    TYPE t_COD_REGI IS TABLE OF IMP_PRINT_APE.COD_REGI%TYPE;
    TYPE t_COD_ZONA IS TABLE OF IMP_PRINT_APE.COD_ZONA%TYPE;
    TYPE t_COD_SECC IS TABLE OF IMP_PRINT_APE.COD_SECC%TYPE;
    TYPE t_COD_TERR IS TABLE OF IMP_PRINT_APE.COD_TERR%TYPE;
    TYPE t_COD_CLIE IS TABLE OF IMP_PRINT_APE.COD_CLIE%TYPE;
    TYPE t_VAL_PRIO IS TABLE OF IMP_PRINT_APE.VAL_PRIO%TYPE;
    TYPE t_VAL_REPA IS TABLE OF IMP_PRINT_APE.VAL_REPA%TYPE;
    TYPE t_VAL_PISO IS TABLE OF IMP_PRINT_APE.VAL_PISO%TYPE;
    TYPE t_VAL_CDP  IS TABLE OF IMP_PRINT_APE.VAL_CDP%TYPE;
    TYPE t_VAL_MODU IS TABLE OF IMP_PRINT_APE.VAL_MODU%TYPE;
    TYPE t_VAL_LINE IS TABLE OF IMP_PRINT_APE.VAL_LINE%TYPE;    
  
    v_TIP_ORDE t_TIP_ORDE := t_TIP_ORDE();
    v_COD_REGI t_COD_REGI := t_COD_REGI();
    v_COD_ZONA t_COD_ZONA := t_COD_ZONA();
    v_COD_SECC t_COD_SECC := t_COD_SECC();
    v_COD_TERR t_COD_TERR := t_COD_TERR();
    v_COD_CLIE t_COD_CLIE := t_COD_CLIE();
    v_VAL_PRIO t_VAL_PRIO := t_VAL_PRIO();
    v_VAL_REPA t_VAL_REPA := t_VAL_REPA();
    v_VAL_PISO t_VAL_PISO := t_VAL_PISO();
    v_VAL_CDP  t_VAL_CDP  := t_VAL_CDP();
    v_VAL_MODU t_VAL_MODU := t_VAL_MODU();
    v_VAL_LINE t_VAL_LINE := t_VAL_LINE(); 
    
    lsCodigoProceso varchar2(32);
    
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
  
    v_handle utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i BINARY_INTEGER := 0;
  
    t_estructura t_all_estru_archi;
  
  BEGIN
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle,
                                                 t_estructura);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i := i + 1;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
          FOR j IN t_estructura.first .. t_estructura.last
          LOOP
            --dbms_output.put_line(t_estructura(j).pos_camp);
          
            IF j = 1 THEN
              v_TIP_ORDE.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_TIP_ORDE(i));
            
            ELSIF j = 2 THEN
              v_COD_REGI.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_COD_REGI(i));
            
            ELSIF j = 3 THEN
              v_COD_ZONA.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_COD_ZONA(i));
            
            ELSIF j = 4 THEN
              v_COD_SECC.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_COD_SECC(i));
            
            ELSIF j = 5 THEN
              v_COD_TERR.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_COD_TERR(i));
            
            ELSIF j = 6 THEN
              v_COD_CLIE.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_COD_CLIE(i));
            
            ELSIF j = 7 THEN
              v_VAL_PRIO.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_VAL_PRIO(i));
                                                    
            ELSIF j = 8 THEN
              v_VAL_REPA.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_VAL_REPA(i));

            ELSIF j = 9 THEN
              v_VAL_PISO.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_VAL_PISO(i));
                                                    
            ELSIF j = 10 THEN
              v_VAL_CDP.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_VAL_CDP(i));
                                                    
            ELSIF j = 11 THEN
              v_VAL_MODU.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_VAL_MODU(i));
                                                    
            ELSIF j = 12 THEN
              v_VAL_LINE.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_VAL_LINE(i));                                                                                                                                                            
            
            END IF;
          END LOOP;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    DELETE IMP_PRINT_APE where USU_CREA = pscodigousuario;
    
    select max(cod_proc)
      into lsCodigoProceso 
      from imp_print_spool;
  
    FORALL i IN 1 .. v_COD_CLIE.count    
      INSERT INTO IMP_PRINT_APE (
        OID_APE,
        COD_PROC,
        TIP_ORDE,
        COD_REGI,
        COD_ZONA,
        COD_SECC,
        COD_TERR,
        COD_CLIE,
        VAL_PRIO,
        VAL_REPA,
        VAL_PISO,
        VAL_CDP,
        VAL_MODU,
        VAL_LINE,
        USU_CREA,
        FEC_CREA,
        IND_ACTI)
    	VALUES(
        IMP_PAPE_SEQ.NEXTVAL,
        lsCodigoProceso,
        v_TIP_ORDE(i),
        v_COD_REGI(i),
        v_COD_ZONA(i),
        v_COD_SECC(i),
        v_COD_TERR(i),
        v_COD_CLIE(i),
        v_VAL_PRIO(i),
        v_VAL_REPA(i),
        v_VAL_PISO(i),
        v_VAL_CDP(i),
        v_VAL_MODU(i),
        v_VAL_LINE(i),
        pscodigousuario,
        SYSDATE,
        '1');
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ape_recep_orden_impre: ' ||
                              ls_sqlerrm || lslinea);
    
  END int_pr_ape_recep_orden_impre;  

END int_pkg_ape;
/
