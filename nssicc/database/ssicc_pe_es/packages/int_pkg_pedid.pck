CREATE OR REPLACE PACKAGE "INT_PKG_PEDID" IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Recalcula la deuda de las consultoras de INC_SOLIC_CONSO_CABEC
Fecha Creacion    : 16/03/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE PED_CALC_DEUD_CAMPA
  (psCodPais VARCHAR2,
   psCodPeriodo VARCHAR2,
   psError OUT VARCHAR2);
/***************************************************************************
Descripcion       : Genera Interfase de Enviar Inscritas de Eccomerce
Fecha Creacion    : 14/11/2006
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE INT_PR_PED_ENVI_MATR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psNumeroLote               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodoDesde            VARCHAR2,
   psCodigoPeriodoHasta            VARCHAR2)
;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Tipo de Chequeo
  Fecha Creacion    : 22/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_ped_envio_tipo_chequ
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Origen de Chequeo
  Fecha Creacion    : 22/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_ped_envio_orige_chequ
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Resultado de Chequeo
  Fecha Creacion    : 22/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_ped_envio_resul_chequ
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Pedidos a Chequear
  Fecha Creacion    : 22/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_ped_envio_pedid_chequ
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  );

/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Resultado de Chequeo
                      Cabecera
  Fecha Creacion    : 28/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_recep_resul_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Resultado de Chequeo
                      Detalle
  Fecha Creacion    : 29/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_recep_resul_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Valida si un pedido esta registrad en la tabla PED_PEDI_CHEQU
                      si no se encuebtra se registra
  Fecha Creacion    : 29/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_valid_pedid_chequ
  (
    pscodpais     VARCHAR2,
    pscodtipochq  VARCHAR2,
    psnumpedido   NUMBER
  );

/***************************************************************************
  Descripcion       : Inserta en las tablas finales los resultados de chequeo
  Fecha Creacion    : 29/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_resul_chequ
  (
    pscodigopais      VARCHAR2
  );

/***************************************************************************
  Descripcion       : Inserta en la tabla final cabecera
                      los resultados de chequeo
  Fecha Creacion    : 29/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_resul_chequ_cabec
  (
    pscodigopais      VARCHAR2
  );

/***************************************************************************
  Descripcion       : Inserta en la tabla final cabecera
                      los resultados de chequeo
  Fecha Creacion    : 29/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_resul_chequ_detal
  (
    pscodigopais      VARCHAR2
  );

    /***************************************************************************
  Descripcion       : Interfaz que envía matriz de facturacion
  Fecha Creacion    : 24/09/2013
  Autor             : Gonzalo Javier Huertas Agurto
  Parametros:
            psCodigoPais   : Codigo de Pais
            psCodigoPeriodo : Codigo Periodo
            psCodigoSistema   : Codigo de Sistema
            psCodigoInterfaz  : Codigo Interfaz
            psNombreArchivo   : Nombre Archivo
            psCentro          : Parametro por pais
 ***************************************************************************/
PROCEDURE int_pr_ped_envio_matri_factu
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psCentro varchar2);

END INT_PKG_PEDID;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_PEDID" IS
/***************************************************************************
Descripcion       : Recalcula la deuda de las consultoras de INC_SOLIC_CONSO_CABEC
Fecha Creacion    : 16/03/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE PED_CALC_DEUD_CAMPA
  (psCodPais VARCHAR2,
   psCodPeriodo VARCHAR2,
   psError OUT VARCHAR2)
IS
 CURSOR c_rechazados IS
      SELECT R.COD_PAIS, R.COD_PERI, R.COD_CLIE, R.FEC_SOLI
        FROM INT_SOLIC_CONSO_CABEC R
       WHERE
    (  ( (R.IND_ERROR_SGPE = 0)
         AND (R.IND_OCS_PROC = 0)
         AND (R.IND_ERRO_REMP = 0)
         AND (R.IND_ERRO_RECH = 0))
         OR R.IND_ERRO_DEUD = 2    )
         AND (R.COD_PERI IN
             (SELECT T.COD_PERI FROM BAS_CTRL_FACT T WHERE T.IND_CAMP_ACT = 1))
         AND (R.COD_PAIS  = psCodPais)
         AND (R.COD_PERI  = psCodPeriodo);
    r_rechazados  c_rechazados%ROWTYPE;
    saldo_rechazo NUMBER;
    saldo_deudor  NUMBER;
  BEGIN
    OPEN c_rechazados;
    LOOP
      FETCH c_rechazados
        INTO r_rechazados;
      EXIT WHEN c_rechazados%NOTFOUND;
      BEGIN
        SELECT B.VAL_MNT_MIN_DEUD
          INTO saldo_rechazo
          FROM BAS_CTRL_FACT B
         WHERE B.COD_PAIS = psCodPais
           AND B.COD_PERI = r_rechazados.cod_peri;
        saldo_deudor := GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALDO_DEUDO(r_rechazados.cod_clie);

  -- Actualizamos el saldo de deuda y el indicador
        UPDATE INT_SOLIC_CONSO_CABEC R
           SET R.IND_ERRO_DEUD = DECODE(SIGN(saldo_deudor - saldo_rechazo),
                                        1,
                                        2,
                                        1),
               R.VAL_SALD_DEUD = saldo_deudor
         WHERE R.COD_CLIE = r_rechazados.cod_clie
           AND R.FEC_SOLI = r_rechazados.fec_soli
     AND R.COD_ZONA NOT IN (SELECT COD_ZONA
                            FROM INT_PED_ZONA_OFICI
          WHERE EST_ZOOF = '1');

  -- Para los pedidos de oficina no hacemos rechazo por deuda
        UPDATE INT_SOLIC_CONSO_CABEC R
           SET R.IND_ERRO_DEUD = 1,
         R.VAL_SALD_DEUD = saldo_deudor
         WHERE R.COD_CLIE = r_rechazados.cod_clie
           AND R.FEC_SOLI = r_rechazados.fec_soli
     AND R.COD_ZONA IN (SELECT COD_ZONA
                        FROM INT_PED_ZONA_OFICI
         WHERE EST_ZOOF = '1');

      END;
    END LOOP;
    CLOSE c_rechazados;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     psError    := ls_sqlerrm;
     RETURN;
END PED_CALC_DEUD_CAMPA;


/***************************************************************************
Descripcion       : Genera Interfase de Enviar Matriz de Facturacion
Fecha Creacion    : 20/03/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE INT_PR_PED_ENVI_MATR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psNumeroLote               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodoDesde       VARCHAR2,
   psCodigoPeriodoHasta       VARCHAR2)
IS
   CURSOR c_interfaz( oidPeriodoDesde VARCHAR2, oidPeriodoHasta VARCHAR2, oidPais VARCHAR2, oidMarca VARCHAR2 , oidCanal VARCHAR2) IS
   SELECT DISTINCT pais.cod_pais AS cod_pais,
                   perio_corpo.cod_peri AS cod_peri,
                   ofert_detal.val_codi_vent AS val_codi_vent
           FROM cra_perio perio,
                cra_perio ini,
                cra_perio fin,
                pre_matri_factu_cabec factu_cabec,
                pre_ofert ofert,
                pre_ofert_detal ofert_detal,
                seg_pais pais,
                seg_perio_corpo perio_corpo
          WHERE ini.pais_oid_pais = oidPais
            AND ini.marc_oid_marc = oidMarca
            AND ini.cana_oid_cana = oidCanal
            AND fin.pais_oid_pais = oidPais
            AND fin.marc_oid_marc = oidMarca
            AND fin.cana_oid_cana = oidCanal
            AND perio.pais_oid_pais = oidPais
            AND perio.marc_oid_marc = oidMarca
            AND perio.cana_oid_cana = oidCanal
            AND pais.oid_pais = perio.pais_oid_pais
            AND perio_corpo.oid_peri = perio.peri_oid_peri
            AND ini.fec_inic <= perio.fec_inic
            AND fin.fec_fina >= perio.fec_fina
            AND factu_cabec.perd_oid_peri = perio.oid_peri
            AND ofert.mfca_oid_cabe = factu_cabec.oid_cabe
            AND ofert_detal.ofer_oid_ofer = ofert.oid_ofer
            AND ofert_detal.ind_digi = 1
            AND ini.oid_peri = oidPeriodoDesde
            AND fin.oid_peri = oidPeriodoHasta
            AND ofert_detal.val_codi_vent IS NOT NULL
    UNION
    SELECT cup_equiv_matr.cod_pais AS cod_pais,
           cup_equiv_matr.cod_peri AS cod_peri,
           cup_equiv_matr.cod_cupon AS cod_venta
    FROM   cup_equiv_matr
    WHERE  cup_equiv_matr.cod_pais = cod_pais
          AND cup_equiv_matr.cod_peri <= psCodigoPeriodoHasta
          AND cup_equiv_matr.cod_peri >= psCodigoPeriodoDesde
                ;
   ls_oidPeriodoDesde         seg_perio_corpo.oid_peri%TYPE;
   ls_oidPeriodoHasta         seg_perio_corpo.oid_peri%TYPE;
   ls_oidPais                 seg_pais.oid_pais%TYPE;
   ls_oidMarca                seg_marca.oid_marc%TYPE;
   ls_oidCanal                seg_canal.oid_cana%TYPE;
   TYPE interfazRec IS RECORD
   (
    codigoPais                seg_pais.cod_pais%TYPE,
    codigoPeriodo             seg_perio_corpo.cod_peri%TYPE,
    codigoVenta               pre_ofert_detal.val_codi_vent%TYPE

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

    /* Generando Archivo de Texto (Detalle) */
    ls_oidPais:= gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    ls_oidMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    ls_oidCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    ls_oidPeriodoDesde := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodoDesde, ls_oidMarca, ls_oidCanal);
    ls_oidPeriodoHasta := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodoHasta, ls_oidMarca, ls_oidCanal);

    /* Procedimiento inicial para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);


    OPEN c_interfaz(ls_oidPeriodoDesde,ls_oidPeriodoHasta, ls_oidPais , ls_oidMarca, ls_oidCanal);
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=  interfazRecord(x).codigoPais                    ||';'||
                          interfazRecord(x).codigoPeriodo                 ||';'||
                          interfazRecord(x).codigoVenta
                          ;
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
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_PER_SOLIC_MONET_CABEC: '||ls_sqlerrm);
END INT_PR_PED_ENVI_MATR;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Tipo de Chequeo
  Fecha Creacion    : 22/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_ped_envio_tipo_chequ
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  )
  IS
    CURSOR c_interfaz IS
      SELECT pscodigopais,ptc.cod_tipo_cheq,
             ptc.des_tipo_cheq
        FROM ped_tipo_chequ ptc
       WHERE ptc.cod_pais = pscodigopais;

     TYPE interfazrec IS RECORD(
      codPais         bas_pais.cod_pais%TYPE,
      codTipoChequeo  ped_tipo_chequ.cod_tipo_cheq%TYPE,
      desTipoChequeo  ped_tipo_chequ.des_tipo_cheq%TYPE
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

    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
      LOOP
        FETCH c_interfaz BULK COLLECT
          INTO interfazrecord LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbabrirutlfile THEN
          gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,pscodigosistema,pscodigointerfaz,
                                           psnombrearchivo,lsdirtempo,lsnombrearchivo,v_handle);
          lbabrirutlfile := FALSE;
        END IF;

        IF interfazrecord.COUNT > 0 THEN
          FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
            lslinea := interfazrecord(x).codPais       || ';' ||
                       interfazrecord(x).codTipoChequeo       || ';' ||
                       interfazrecord(x).desTipoChequeo;

            utl_file.put_line(v_handle,lslinea);
          END LOOP;

        END IF;
        EXIT WHEN c_interfaz%NOTFOUND;

      END LOOP;

    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

    END IF;
    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_ped_envio_tipo_chequ: '||ls_sqlerrm);
  END int_pr_ped_envio_tipo_chequ;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Origen de Chequeo
  Fecha Creacion    : 22/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_ped_envio_orige_chequ
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  )
  IS

    CURSOR c_interfaz IS
      SELECT pscodigopais,poc.cod_orig_cheq,
             poc.des_orig_cheq
        FROM ped_orige_chequ poc
       WHERE poc.cod_pais = pscodigopais
         AND poc.sec_eval != 0
    ORDER BY poc.sec_eval;

    TYPE interfazrec IS RECORD(
      codPais         bas_pais.cod_pais%TYPE,
      codOrigChequeo  ped_orige_chequ.cod_orig_cheq%TYPE,
      desOrigChequeo  ped_orige_chequ.des_orig_cheq%TYPE
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

    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
      LOOP
        FETCH c_interfaz BULK COLLECT
          INTO interfazrecord LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbabrirutlfile THEN
          gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,pscodigosistema,pscodigointerfaz,
                                           psnombrearchivo,lsdirtempo,lsnombrearchivo,v_handle);
          lbabrirutlfile := FALSE;
        END IF;

        IF interfazrecord.COUNT > 0 THEN
          FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
            lslinea := interfazrecord(x).codPais       || ';' ||
                       interfazrecord(x).codOrigChequeo       || ';' ||
                       interfazrecord(x).desOrigChequeo;

            utl_file.put_line(v_handle,lslinea);
          END LOOP;

        END IF;
        EXIT WHEN c_interfaz%NOTFOUND;

      END LOOP;

    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

    END IF;
    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_ped_envio_orige_chequ: '||ls_sqlerrm);
  END int_pr_ped_envio_orige_chequ;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Resultado de Chequeo
  Fecha Creacion    : 22/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_ped_envio_resul_chequ
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  )
  IS

    CURSOR c_interfaz IS
      SELECT pscodigopais,prc.cod_resu_cheq,
             prc.des_resu_cheq
        FROM ped_resul_chequ prc
       WHERE prc.cod_pais = pscodigopais;

    TYPE interfazrec IS RECORD(
      codPais         bas_pais.cod_pais%TYPE,
      codResuChequeo  ped_resul_chequ.cod_resu_cheq%TYPE,
      desResuChequeo  ped_resul_chequ.des_resu_cheq%TYPE
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

    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
      LOOP
        FETCH c_interfaz BULK COLLECT
          INTO interfazrecord LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbabrirutlfile THEN
          gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,pscodigosistema,pscodigointerfaz,
                                           psnombrearchivo,lsdirtempo,lsnombrearchivo,v_handle);
          lbabrirutlfile := FALSE;
        END IF;

        IF interfazrecord.COUNT > 0 THEN
          FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
            lslinea := interfazrecord(x).codPais       || ';' ||
                       interfazrecord(x).codResuChequeo       || ';' ||
                       interfazrecord(x).desResuChequeo;

            utl_file.put_line(v_handle,lslinea);
          END LOOP;

        END IF;
        EXIT WHEN c_interfaz%NOTFOUND;

      END LOOP;

    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

    END IF;
    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_ped_envio_resul_chequ: '||ls_sqlerrm);
  END int_pr_ped_envio_resul_chequ;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Pedidos a Chequear
  Fecha Creacion    : 22/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_ped_envio_pedid_chequ
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  )
  IS

    CURSOR c_interfaz IS
      SELECT pscodigopais,b.cod_tipo_cheq,
             a.val_nume_soli,
             b.cod_orig_cheq,
             e.cod_peri,
             to_char(a.fec_fact, 'DDMMYYYY'),
             c.cod_zona,
             a.oid_soli_cabe
        FROM ped_solic_cabec a,
             ped_pedid_chequ b,
             zon_zona c,
             cra_perio d,
             seg_perio_corpo e
       WHERE b.cod_pais = pscodigopais
         AND b.ind_envi_yobe = 0
         AND a.oid_soli_cabe = b.oid_pedi_cheq
         AND a.zzon_oid_zona = c.oid_zona
         AND a.perd_oid_peri = d.oid_peri
         AND d.peri_oid_peri = e.oid_peri ;

    TYPE interfazrec IS RECORD(
      codPais         bas_pais.cod_pais%TYPE,
      codTipoChequeo  ped_pedid_chequ.cod_tipo_cheq%TYPE,
      numeSolicitud   ped_solic_cabec.val_nume_soli%TYPE,
      codOrigChequeo  ped_pedid_chequ.cod_orig_cheq%TYPE,
      codPeriodo      seg_perio_corpo.cod_peri%TYPE,
      fecFacturacion  VARCHAR2(8),
      codZona         zon_zona.cod_zona%TYPE ,
      oidPedido       ped_solic_cabec.oid_soli_cabe%TYPE
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

    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
      LOOP
        FETCH c_interfaz BULK COLLECT
          INTO interfazrecord LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbabrirutlfile THEN
          gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,pscodigosistema,pscodigointerfaz,
                                           psnombrearchivo,lsdirtempo,lsnombrearchivo,v_handle);
          lbabrirutlfile := FALSE;
        END IF;

        IF interfazrecord.COUNT > 0 THEN
          FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
            lslinea := interfazrecord(x).codPais       || ';' ||
                       interfazrecord(x).codTipoChequeo   || ';' ||
                       interfazrecord(x).numeSolicitud    || ';' ||
                       interfazrecord(x).codOrigChequeo   || ';' ||
                       interfazrecord(x).codPeriodo       || ';' ||
                       interfazrecord(x).fecFacturacion   || ';' ||
                       interfazrecord(x).codZona;

            utl_file.put_line(v_handle,lslinea);

            UPDATE ped_pedid_chequ
               SET num_lote = psnumerolote
             WHERE cod_pais = pscodigopais
               AND cod_tipo_cheq = interfazrecord(x).codTipoChequeo
               AND oid_pedi_cheq = interfazrecord(x).oidPedido
               AND ind_envi_yobe = 0;

          END LOOP;

        END IF;
        EXIT WHEN c_interfaz%NOTFOUND;

      END LOOP;

    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

    END IF;
    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_ped_envio_pedid_chequ: '||ls_sqlerrm);
  END int_pr_ped_envio_pedid_chequ;

/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Resultado de Chequeo
                      Cabecera
  Fecha Creacion    : 28/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_recep_resul_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  )
  IS

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

    TYPE t_codtipocheq IS TABLE OF int_ped_resul_chequ_cabec.cod_tipo_cheq%TYPE;
    TYPE t_numpedido IS TABLE OF int_ped_resul_chequ_cabec.num_pedi_cheq%TYPE;
    TYPE t_resultado IS TABLE OF int_ped_resul_chequ_cabec.cod_resu_cheq%TYPE;

    v_codtipocheq  t_codtipocheq := t_codtipocheq();
    v_numpedido    t_numpedido   := t_numpedido();
    v_resultado    t_resultado   := t_resultado();

    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    i               BINARY_INTEGER := 0;
    posicion        NUMBER := 0;
    longitud        NUMBER := 0;
    inicio          NUMBER := 0;

  BEGIN

    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais, pscodigosistema, pscodigointerfaz,
                                                 psnombrearchivo, 'TXT', lsdirtempo, lsnombrearchivo,
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
              FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
                  
                IF (posicion = 1) THEN
                  v_codtipocheq.EXTEND;
                  v_codtipocheq(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 2) THEN
                  v_numpedido.EXTEND;
                  v_numpedido(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 3) THEN
                  v_resultado.EXTEND;
                  v_resultado(i) := TRIM(substr(lslinea, inicio, longitud));
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
    FORALL i IN 1 .. v_codtipocheq.COUNT
      INSERT INTO int_ped_resul_chequ_cabec
        (cod_pais,
         cod_tipo_cheq,
         num_pedi_cheq,
         cod_resu_cheq  )
      VALUES
        (pscodigopais,
         v_codtipocheq(i),
         v_numpedido(i),
         v_resultado(i));

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_recep_resul_cabec: '||ls_sqlerrm);
  END int_pr_recep_resul_cabec;

/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Resultado de Chequeo
                      Detalle
  Fecha Creacion    : 29/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
PROCEDURE int_pr_recep_resul_detal
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

  TYPE t_codtipocheq IS TABLE OF int_ped_resul_chequ_detal.cod_tipo_cheq%TYPE;
  TYPE t_numpedido IS TABLE OF int_ped_resul_chequ_detal.num_pedi_cheq%TYPE;
  TYPE t_resultado IS TABLE OF int_ped_resul_chequ_detal.cod_resu_cheq%TYPE;
  TYPE t_codproducto IS TABLE OF int_ped_resul_chequ_detal.cod_prod%TYPE;
  TYPE t_desproducto IS TABLE OF int_ped_resul_chequ_detal.des_prod%TYPE;
  TYPE t_unireclamadas IS TABLE OF int_ped_resul_chequ_detal.uni_recl%TYPE;

  v_codtipocheq   t_codtipocheq := t_codtipocheq();
  v_numpedido     t_numpedido := t_numpedido();
  v_resultado     t_resultado := t_resultado();
  v_codproducto   t_codproducto := t_codproducto();
  v_desproducto   t_desproducto := t_desproducto();
  v_unireclamadas t_unireclamadas := t_unireclamadas();

  lsdirtempo      bas_inter.dir_temp%TYPE;
  w_filas         NUMBER := 1000;
  v_handle        utl_file.file_type;
  lslinea         VARCHAR2(1000);
  lsnombrearchivo VARCHAR2(50);
  i               BINARY_INTEGER := 0;
  posicion        NUMBER := 0;
  longitud        NUMBER := 0;
  inicio          NUMBER := 0;

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
                v_numpedido.extend;
                v_numpedido(i) := TRIM(substr(lslinea,
                                              inicio,
                                              longitud));
              
              ELSIF (posicion = 2) THEN
                v_codtipocheq.extend;
                v_codtipocheq(i) := TRIM(substr(lslinea,
                                                inicio,
                                                longitud));
              
              ELSIF (posicion = 3) THEN
                v_resultado.extend;
                v_resultado(i) := TRIM(substr(lslinea,
                                              inicio,
                                              longitud));
              
              ELSIF (posicion = 4) THEN
                v_codproducto.extend;
                v_codproducto(i) := TRIM(substr(lslinea,
                                                inicio,
                                                longitud));
              
              ELSIF (posicion = 5) THEN
                v_desproducto.extend;
                v_desproducto(i) := TRIM(substr(lslinea,
                                                inicio,
                                                longitud));
              
              ELSIF (posicion = 6) THEN
                v_unireclamadas.extend;
                v_unireclamadas(i) := to_number(TRIM(substr(lslinea,
                                                            inicio,
                                                            longitud)));
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

    FORALL i IN 1 .. v_codtipocheq.COUNT
     INSERT INTO int_ped_resul_chequ_detal
      (cod_pais,
       cod_tipo_cheq,
       num_pedi_cheq,
       cod_resu_cheq,
       cod_prod,
       des_prod,
       uni_recl)
      SELECT pscodigopais,
             c.cod_tipo_cheq,
             c.num_pedi_cheq,
             c.cod_resu_cheq,
             v_codproducto(i),
             v_desproducto(i),
             v_unireclamadas(i)
        FROM int_ped_resul_chequ_cabec c
       WHERE c.cod_pais = pscodigopais
         AND c.cod_tipo_cheq = v_codtipocheq(i)
         AND c.num_pedi_cheq = v_numpedido(i)
         AND c.cod_resu_cheq = v_resultado(i)
         AND rownum = 1;

 

 for i in 1 .. v_codtipocheq.count loop
    -- Se valida si el pedido esta insertado en la tabla PED_PEDI_CHEQU
    int_pr_valid_pedid_chequ(pscodigopais,
                             v_codtipocheq(i),
                             v_numpedido(i));
  end loop;

 

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         1000);
    raise_application_error(-20123,
                            'ERROR int_pr_recep_resul_detal: ' || ls_sqlerrm);
END int_pr_recep_resul_detal;

/***************************************************************************
  Descripcion       : Valida si un pedido esta registrad en la tabla PED_PEDI_CHEQU
                      si no se encuebtra se registra
  Fecha Creacion    : 29/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_valid_pedid_chequ
  (
    pscodpais     VARCHAR2,
    pscodtipochq  VARCHAR2,
    psnumpedido   NUMBER
  )
  IS
    v_contpedid  NUMBER := 0;
    v_oidpedido  ped_pedid_chequ.oid_pedi_cheq%TYPE;
    v_codorigen  ped_orige_chequ.cod_orig_cheq%TYPE;
    v_campana    ped_pedid_chequ.cod_peri%TYPE;
    v_fecha      ped_pedid_chequ.fec_fact%TYPE;
    v_codregion  ped_pedid_chequ.cod_regi%TYPE;
    v_codzona    ped_pedid_chequ.cod_zona%TYPE;
    v_codseccion ped_pedid_chequ.cod_secc%TYPE;
    v_codterri   ped_pedid_chequ.cod_terr%TYPE;
    v_codcliente mae_clien.cod_clie%TYPE;

  BEGIN

    -- Los pedidos que no se encuentran registrados se insertan
    -- con codigo de origen de Analista Yobel
    v_codorigen := 'AY';

    -- se obtiene los datos del pedido del pedido
    SELECT z.oid_soli_cabe,
           gen_pkg_gener.gen_fn_devuelve_des_perio(z.perd_oid_peri),
           z.fec_fact,
           (SELECT zr.cod_regi
              FROM zon_zona zz, zon_regio zr
             WHERE zz.oid_zona = z.zzon_oid_zona
               AND zr.oid_regi = zz.zorg_oid_regi),
           (SELECT zz.cod_zona
              FROM zon_zona zz
             WHERE zz.oid_zona = z.zzon_oid_zona),
           (SELECT nvl(zs.cod_secc,'')
              FROM zon_terri_admin zta,
                   zon_terri zt,
                   zon_secci zs,
                   zon_zona zz,
                   zon_regio zr
             WHERE zta.oid_terr_admi = z.ztad_oid_terr_admi
               AND zs.oid_secc = zta.zscc_oid_secc
               AND zz.oid_zona = zs.zzon_oid_zona
               AND zr.oid_regi = zz.zorg_oid_regi
               AND zta.terr_oid_terr = zt.oid_terr),
           (SELECT nvl(zt.cod_terr,'')
              FROM zon_terri_admin zta,
                   zon_terri zt
             WHERE zta.oid_terr_admi = z.ztad_oid_terr_admi
               AND zta.terr_oid_terr = zt.oid_terr),
           (SELECT m.cod_clie
              FROM mae_clien m
             WHERE m.oid_clie = z.clie_oid_clie
           )
      INTO v_oidpedido,
           v_campana,
           v_fecha,
           v_codregion,
           v_codzona,
           v_codseccion,
           v_codterri,
           v_codcliente
      FROM ped_solic_cabec z
     WHERE z.val_nume_soli = psnumpedido;
       --AND z.tspa_oid_tipo_soli_pais = gen_pkg_gener.gen_fn_devuelve_id_pais(pscodpais);

    SELECT COUNT(1)
      INTO v_contpedid
      FROM ped_pedid_chequ p
     WHERE p.cod_pais = pscodpais
       AND p.cod_tipo_cheq = pscodtipochq
       AND p.oid_pedi_cheq = v_oidpedido;

    IF v_contpedid = 0 THEN
      INSERT INTO ped_pedid_chequ
      ( cod_pais,
        cod_tipo_cheq,
        oid_pedi_cheq,
        cod_orig_cheq,
        cod_peri,
        fec_fact,
        cod_regi,
        cod_zona,
        cod_secc,
        cod_terr,
        ind_envi_yobe,
        cod_clie,
        val_nume_soli  )
      VALUES(
        pscodpais,
        pscodtipochq,
        v_oidpedido,
        v_codorigen,
        v_campana,
        v_fecha,
        v_codregion,
        v_codzona,
        v_codseccion,
        v_codterri,
        1,
        v_codcliente,
        psnumpedido
        );

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_valid_pedid_chequ: '||ls_sqlerrm);
  END int_pr_valid_pedid_chequ;

/***************************************************************************
  Descripcion       : Inserta en las tablas finales los resultados de chequeo
  Fecha Creacion    : 29/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_resul_chequ
  (
    pscodigopais      VARCHAR2
  )
  IS
  BEGIN

    int_pkg_pedid.int_pr_resul_chequ_cabec(pscodigopais);
    int_pkg_pedid.int_pr_resul_chequ_detal(pscodigopais);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_resul_chequ: '||ls_sqlerrm);

  END int_pr_resul_chequ;

/***************************************************************************
  Descripcion       : Inserta en la tabla final cabecera
                      los resultados de chequeo
  Fecha Creacion    : 29/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_resul_chequ_cabec
  (
    pscodigopais      VARCHAR2
  )
  IS

    CURSOR curinscabecera IS

      SELECT pscodigopais,
             c.cod_tipo_cheq,
             z.oid_soli_cabe,
             decode(cod_resu_cheq, null, 'KO', cod_resu_cheq) cod_resu_cheq
        FROM ped_solic_cabec z, 
             ped_pedid_chequ p,
             int_ped_resul_chequ_cabec c
       WHERE c.cod_pais = pscodigopais
         AND z.val_nume_soli = c.num_pedi_cheq
         AND z.oid_soli_cabe = p.oid_pedi_cheq
         AND p.cod_pais = c.cod_pais
         AND p.cod_tipo_cheq = c.cod_tipo_cheq;

    TYPE ped_resul_chequ_cabec_tab_t IS TABLE OF ped_resul_chequ_cabec%ROWTYPE INDEX BY BINARY_INTEGER;

    ped_resul_chequ_cabec_tab ped_resul_chequ_cabec_tab_t; -- In-memory table

    rows NATURAL := 1000; -- Number of rows to process at a time
    j    BINARY_INTEGER := 0;

  BEGIN

    OPEN curinscabecera;
      LOOP
        FETCH curinscabecera BULK COLLECT
          INTO ped_resul_chequ_cabec_tab LIMIT rows;

        EXIT WHEN ped_resul_chequ_cabec_tab.COUNT = 0;

        FORALL j IN ped_resul_chequ_cabec_tab.FIRST .. ped_resul_chequ_cabec_tab.LAST
          INSERT INTO ped_resul_chequ_cabec VALUES ped_resul_chequ_cabec_tab (j);

        -- Bulk bind of data in memory table...
        FORALL j IN ped_resul_chequ_cabec_tab.FIRST .. ped_resul_chequ_cabec_tab.LAST
          UPDATE ped_solic_cabec
             SET recq_oid_resu_cheq =
                 (SELECT oid_resu_cheq FROM rec_resul_chequ WHERE cod_resu_cheq = 'OK'),
                 inre_oid_indi_revi = 2
           WHERE oid_soli_cabe = ped_resul_chequ_cabec_tab(j).Oid_Pedi_Cheq;

      END LOOP;
    CLOSE curinscabecera;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_resul_chequ_cabec: '||ls_sqlerrm);

  END int_pr_resul_chequ_cabec;

/***************************************************************************
  Descripcion       : Inserta en la tabla final cabecera
                      los resultados de chequeo
  Fecha Creacion    : 29/12/2009
  Autor             : José Luis Rodríguez
***************************************************************************/
  PROCEDURE int_pr_resul_chequ_detal
  (
    pscodigopais      VARCHAR2
  )
  IS

    CURSOR curinsdetalle IS

      SELECT pscodigopais,
             b.cod_tipo_cheq,
             z.oid_soli_cabe,
             b.cod_resu_cheq,
             b.cod_prod,
             b.uni_recl
        FROM ped_solic_cabec z, 
             ped_pedid_chequ p,
             ped_resul_chequ_cabec a,
             int_ped_resul_chequ_detal  b
       WHERE b.cod_pais = pscodigopais
         AND z.oid_soli_cabe = p.oid_pedi_cheq
         AND z.val_nume_soli = b.num_pedi_cheq
         AND p.cod_pais = a.cod_pais
         AND p.cod_tipo_cheq = a.cod_tipo_cheq
         AND p.oid_pedi_cheq = a.oid_pedi_cheq
         AND a.cod_pais = b.cod_pais
         AND a.cod_tipo_cheq = b.cod_tipo_cheq
         and A.COD_RESU_CHEQ=B.COD_RESU_CHEQ;
         
         
    TYPE ped_resul_chequ_detal_tab_t IS TABLE OF ped_resul_chequ_detal%ROWTYPE INDEX BY BINARY_INTEGER;

    ped_resul_chequ_detal_tab ped_resul_chequ_detal_tab_t; -- In-memory table

    rows NATURAL := 1000; -- Number of rows to process at a time
    j    BINARY_INTEGER := 0;

  BEGIN

    OPEN curinsdetalle;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsdetalle BULK COLLECT
        INTO ped_resul_chequ_detal_tab LIMIT rows;
      EXIT WHEN ped_resul_chequ_detal_tab.COUNT = 0;

      FORALL j IN ped_resul_chequ_detal_tab.FIRST .. ped_resul_chequ_detal_tab.LAST
        INSERT INTO ped_resul_chequ_detal VALUES ped_resul_chequ_detal_tab (j);

    END LOOP;
    CLOSE curinsdetalle;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_resul_chequ_detal: '||ls_sqlerrm);

  END int_pr_resul_chequ_detal;

    /***************************************************************************
  Descripcion       : Interfaz que envía matriz de facturacion
  Fecha Creacion    : 24/09/2013
  Autor             : Gonzalo Javier Huertas Agurto
  Parametros:
            psCodigoPais   : Codigo de Pais
            psCodigoPeriodo : Codigo Periodo
            psCodigoSistema   : Codigo de Sistema
            psCodigoInterfaz  : Codigo Interfaz
            psNombreArchivo   : Nombre Archivo
            psCentro          : Parametro por pais
 ***************************************************************************/
PROCEDURE int_pr_ped_envio_matri_factu
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psCentro varchar2)
   
 is
 CURSOR c_interfaz IS
    select psCentro CENTRO 
          , c.cod_peri	
          , to_char(lpad(f.cod_cata,2,'0'))
          , g.cod_sap
          , g.codi_anti BPCS
          , e.val_codi_vent CUV
          , trim(to_char(h.cod_tipo_ofer,'099'))
          , trim(to_char(g.VAL_COST_ESTD,'9999999999V999'))
          , trim(to_char(e.precio_unitario,'9999999999V999'))
          , to_char(e.num_pagi_cata)
          , to_char(e.ind_digi) DIGITABLE
          , to_char(e.ind_prod_prin)
          , to_char(e.num_unid_esti)  
      from pre_matri_factu_cabec a, cra_perio b, seg_perio_corpo c, pre_ofert d, pre_ofert_detal e, pre_catal f, mae_produ g, pre_tipo_ofert h
      where a.perd_oid_peri=b.oid_peri and b.peri_oid_peri=c.oid_peri and c.cod_peri= psCodigoPeriodo and e.tofe_oid_tipo_ofer=h.oid_tipo_ofer
        and a.oid_cabe=d.mfca_oid_cabe and d.oid_ofer=e.ofer_oid_ofer and d.ocat_oid_cata=f.oid_cata
        and e.prod_oid_prod=g.oid_prod
      order by 1,2,3;
   TYPE interfazTipo IS RECORD
   (  CENTRO          VARCHAR2(20),
      cod_peri        varchar2(20),
      cod_cata        varchar2(20),
      cod_sap         varchar2(20),
      BPCS            varchar2(20),
      CUV             varchar2(20),
      cod_tipo_ofer   varchar2(20),
      VAL_COST_ESTD   varchar2(20),
      precio_unitario varchar2(20),
      num_pagi_cata   varchar2(20),
      DIGITABLE       varchar2(1),
      ind_prod_prin   varchar2(1),
      num_unid_esti   varchar2(10)
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsFlag       VARCHAR2(1):=' ';
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
                  lsLinea :=  pscentro      ||';'||
                              interfazRecord(x).cod_peri      ||';'||
                              interfazRecord(x).cod_cata      ||';'||
                              interfazRecord(x).cod_sap      ||';'||
                              interfazRecord(x).BPCS      ||';'||
                              interfazRecord(x).CUV      ||';'||
                              interfazRecord(x).cod_tipo_ofer      ||';'||
                              interfazRecord(x).VAL_COST_ESTD      ||';'||
                              interfazRecord(x).precio_unitario      ||';'||
                              interfazRecord(x).num_pagi_cata      ||';'||
                              interfazRecord(x).DIGITABLE      ||';'||
                              interfazRecord(x).ind_prod_prin      ||';'||
                              interfazRecord(x).num_unid_esti;
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
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_ped_envio_matri_factu: '||ls_sqlerrm);
END int_pr_ped_envio_matri_factu;

END INT_PKG_PEDID;
/

