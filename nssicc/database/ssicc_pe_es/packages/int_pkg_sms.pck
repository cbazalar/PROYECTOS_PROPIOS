CREATE OR REPLACE PACKAGE INT_PKG_SMS IS

  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  w_filas    NUMBER := 1000;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Consultoras del programa SMS
  Fecha Creacion    : 15/12/2009
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE int_pr_sms_envio_consu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoPeriodo  VARCHAR2,
    psfechaFacturacion VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Pedidos de las Consultoras del
                      programa SMS
  Fecha Creacion    : 15/12/2009
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE int_pr_sms_envio_pedid_consu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psfechafacturacion VARCHAR2
  );
  
  /**********************************************************************************
  Descripcion       : Genera Interfaz de Envio de Mensajes de Texto a las Consultoras
  Fecha Creacion    : 04/02/2013
  Autor             : Danny Amaro
  ***********************************************************************************/
  PROCEDURE int_pr_sms_envio_mensa_consu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psfechafacturacion VARCHAR2
  );
  
/*****************************************************************************************
  Descripcion          : Genera Interfaz de Envio a Consultoras de pago a cuentas corrientes por recaudo bancario
  Fecha Creacion   : 13/10/2014
  Autor                  : Sebastian Guerra
******************************************************************************************/
 PROCEDURE int_pr_sms_envio_consu_pagos(
    pscodigopais            VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz      VARCHAR2,
    psnombrearchivo     VARCHAR2,
    psfechaproceso       VARCHAR2,
    psflagnovedades     VARCHAR2
 );
  
END INT_PKG_SMS;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_SMS IS

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Consultoras del programa SMS
  Fecha Creacion    : 15/12/2009
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE int_pr_sms_envio_consu
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    psfechafacturacion VARCHAR2
  )
  IS
  CURSOR c_interfaz IS
    SELECT DISTINCT
           pscodigopais codigoPais,
           TO_CHAR(to_date(psfechaFacturacion,'DD/MM/YYYY'),'DDMMYYYY') fechaFacturacion,
           MCLTE.COD_CLIE codigoConsultora,
           TELEMOV.FONO   numeroCelular,
           TO_CHAR(DA.FEC_NACI, 'DDMMYYYY') fechaNacimiento
     FROM  MAE_CLIEN_CLASI MCC,
           MAE_CLIEN_TIPO_SUBTI MCTS,
           MAE_CLASI MC,
           MAE_TIPO_CLASI_CLIEN MTCC,
           MAE_SUBTI_CLIEN MSC,
           MAE_TIPO_CLIEN MTC,
           MAE_CLIEN MCLTE,
           MAE_CLIEN_DATOS_ADICI DA,
           (SELECT MID.CLIE_OID_CLIE, SUBSTR(MID.VAL_TEXT_COMU, 1, 15) FONO
              FROM MAE_CLIEN_COMUN MID,  MAE_TIPO_COMUN TC
             WHERE MID.TICM_OID_TIPO_COMU = TC.OID_TIPO_COMU
               AND TC.COD_TIPO_COMU = 'TM') TELEMOV,
           (SELECT *
              FROM MAE_CLASI_PROGR MCP
             WHERE MCP.COD_PAIS = pscodigopais
               AND MCP.COD_PROG = 'SMS'
               AND MCP.IND_ACTI = '1') TABPARA
    WHERE  MCTS.OID_CLIE_TIPO_SUBT = MCC.CTSU_OID_CLIE_TIPO_SUBT(+)
      AND  MCC.CLAS_OID_CLAS = MC.OID_CLAS(+)
      AND  MCC.TCCL_OID_TIPO_CLASI = MTCC.OID_TIPO_CLAS(+)
      AND  MCTS.TICL_OID_TIPO_CLIE = MTC.OID_TIPO_CLIE(+)
      AND  MCTS.SBTI_OID_SUBT_CLIE = MSC.OID_SUBT_CLIE(+)
      AND  MCTS.CLIE_OID_CLIE = MCLTE.OID_CLIE
      AND  MCLTE.OID_CLIE = DA.CLIE_OID_CLIE
      AND  MCLTE.OID_CLIE = TELEMOV.CLIE_OID_CLIE(+)
      AND  MC.COD_CLAS = TABPARA.COD_CLAS(+)
      AND  MTCC.COD_TIPO_CLAS = TABPARA.COD_TIPO_CLAS
      AND  MSC.COD_SUBT_CLIE = TABPARA.COD_SUBT_CLIE
      AND  MTC.COD_TIPO_CLIE = TABPARA.COD_TIPO_CLIE;

    TYPE interfazrec IS RECORD(
      codigopais        seg_pais.cod_pais%TYPE,
      fechafacturacion  VARCHAR2(10),
      codigoconsultora  mae_clien.cod_clie%TYPE,
      numerocelular     mae_clien_comun.val_text_comu%TYPE,
      fechaNacimiento   VARCHAR2(10)
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
            lslinea := interfazrecord(x).codigopais       || ';' ||
                       interfazrecord(x).fechafacturacion || ';' ||
                       interfazrecord(x).codigoconsultora || ';' ||
                       interfazrecord(x).numerocelular    || ';' ||
                       interfazrecord(x).fechaNacimiento;

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
      ls_sqlerrm := substr(SQLERRM,1,1000);
      raise_application_error(-20123,'ERROR INT_PR_SMS_ENVIO_CONSU: ' || ls_sqlerrm);

  END int_pr_sms_envio_consu;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Pedidos de las Consultoras del
                      programa SMS
  Fecha Creacion    : 15/12/2009
  Autor             : José Luis Rodríguez
  ***************************************************************************/
  PROCEDURE int_pr_sms_envio_pedid_consu
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pscodigoPeriodo    VARCHAR2,
    psfechaFacturacion VARCHAR2
  )
  IS
    CURSOR c_interfaz (oidperiodo VARCHAR2, oidtiposolicitud VARCHAR2)  IS
      SELECT DISTINCT
             pscodigopais  codigoPais ,
             SPC.COD_PERI campanaFacturacion,
             TO_CHAR(SC.FEC_FACT, 'ddmmyyyy') fechaFacturacion,
             BDCLIENTE.COD_CLIE codigoConsultora,
             LTRIM(TO_CHAR(NVL(SC.VAL_TOTA_PAGA_LOCA, 0),'999999999990.99')) importe
        FROM (SELECT DISTINCT mclte.oid_clie, mclte.cod_clie
                FROM mae_clien_clasi      mcc,
                     mae_clien_tipo_subti mcts,
                     mae_clasi            mc,
                     mae_tipo_clasi_clien mtcc,
                     mae_subti_clien      msc,
                     mae_tipo_clien       mtc,
                     mae_clien            mclte,
                     MAE_CLASI_PROGR       mcp
               WHERE mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt(+)
                 AND mcc.clas_oid_clas = mc.oid_clas(+)
                 AND mcc.tccl_oid_tipo_clasi = mtcc.oid_tipo_clas(+)
                 AND mcts.ticl_oid_tipo_clie = mtc.oid_tipo_clie(+)
                 AND mcts.sbti_oid_subt_clie = msc.oid_subt_clie(+)
                 AND mcts.clie_oid_clie = mclte.oid_clie
                 AND mcp.cod_pais = pscodigopais
                 AND mcp.cod_prog = 'SMS'
                 AND mcp.ind_acti = '1'
                 AND mc.cod_clas = mcp.cod_clas(+)
                 AND mtcc.cod_tipo_clas = mcp.cod_tipo_clas
                 AND msc.cod_subt_clie = mcp.cod_subt_clie
                 AND mtc.cod_tipo_clie = mcp.cod_tipo_clie) bdcliente,
             ped_solic_cabec sc,
             ped_tipo_solic_pais tsp,
             ped_tipo_solic ts,
             seg_perio_corpo spc,
             cra_perio cp
       WHERE bdcliente.oid_clie = sc.clie_oid_clie
         AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
         AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
         AND ts.cod_tipo_soli = 'SOC'
         AND sc.perd_oid_peri = cp.oid_peri
         AND cp.peri_oid_peri = spc.oid_peri
         AND spc.cod_peri = pscodigoPeriodo
         AND sc.fec_fact = TO_DATE(psfechafacturacion, 'dd/mm/yyyy') ;

    TYPE interfazrec IS RECORD(
      codigopais         seg_pais.cod_pais%TYPE,
      campanaFacturacion seg_perio_corpo.cod_peri%TYPE,
      fechafacturacion   VARCHAR2(10),
      codigoconsultora   mae_clien.cod_clie%TYPE,
      importe            VARCHAR2(15)
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

    ls_oidperiodo        seg_perio_corpo.oid_peri%TYPE;
    ls_oidtiposolicitud  NUMBER;

  BEGIN

    ls_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoPeriodo);
    ls_oidtiposolicitud := int_pkg_recla.gen_fn_devue_oid_tipo_solpa('SOC');

    lbabrirutlfile := TRUE;

    OPEN c_interfaz(ls_oidperiodo, ls_oidtiposolicitud);
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
              lslinea := interfazrecord(x).codigopais         || ';' ||
                         interfazrecord(x).campanaFacturacion || ';' ||
                         interfazrecord(x).fechafacturacion   || ';' ||
                         interfazrecord(x).codigoconsultora   || ';' ||
                         interfazrecord(x).importe;

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
      ls_sqlerrm := substr(SQLERRM,1,1000);
      raise_application_error(-20123,'ERROR INT_PR_SMS_ENVIO_PEDID_CONSU: ' || ls_sqlerrm);

  END int_pr_sms_envio_pedid_consu;
  
  
  /**********************************************************************************
  Descripcion       : Genera Interfaz de Envio de Mensajes de Texto a las Consultoras
  Fecha Creacion    : 04/02/2013
  Autor             : Danny Amaro
  ***********************************************************************************/
  PROCEDURE int_pr_sms_envio_mensa_consu
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    psfechafacturacion VARCHAR2
  )
  IS
  CURSOR c_interfaz IS
SELECT TRANSLATE ( TRIM(nombre),'áéíóúÁÉÍÓÚÑñ','aeiouAEIOUNn' ) nombre,
       TRIM(celular) celular,
       TRANSLATE ( TRIM(to_char(TO_DATE ( fechaentre, 'DD/MM/YYYY' ),'DAY','NLS_DATE_LANGUAGE = SPANISH')) || ' ' || to_char(TO_DATE ( TO_DATE ( fechaentre, 'DD/MM/YYYY' ), 'DD/MM/YYYY' ),'DD') || ' DE ' || TRIM(to_char(TO_DATE ( fechaentre, 'DD/MM/YYYY' ),'MONTH','NLS_DATE_LANGUAGE = SPANISH')) || ' DEL ' || to_char(TO_DATE ( fechaentre, 'DD/MM/YYYY' ),'YYYY'),'áéíóúÁÉÍÓÚÑñ','aeiouAEIOUNn' )  fechaentre,
       valorfactura,
       cod_clie
FROM
(        
select * from
(
SELECT NVL(m.val_nom1,m.val_nom2) AS nombre,
       SUBSTR(FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN (sms.CLIE_OID_CLIE, 'TM'),1,10) AS celular,
       fec AS fechaentre,
       ( select NVL(A.VAL_TOTA_PAGA_LOCA,0 )
         from ped_solic_cabec a
        where A.oid_soli_cabe=sms.soca_oid_soli_cabe
        ) AS valorFactura,
        m.cod_clie
       --GEN_PKG_GENER.gen_fn_clien_monto_ultim_pedid (sms.CLIE_OID_CLIE) AS valorFactura
  FROM MAE_CLIEN M,
  (
  SELECT DISTINCT PED.CLIE_OID_CLIE, seg.fec, ped.soca_oid_soli_cabe
          FROM ped_solic_cabec ped,
                 mae_clien_unida_admin c,
                 zon_terri_admin       d,
                 zon_terri             e,
                 zon_secci             f,
                 zon_zona              g,
                 zon_regio             h,
                 mae_clien_tipo_subti  i,
                 mae_clien_clasi       j,
                 SMS_CLIEN_ENVIO_MENSA a,
                 ped_segui_pedid seg
         WHERE ped.TSPA_OID_TIPO_SOLI_PAIS = FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS ('SOC')
               AND ped.GRPR_OID_GRUP_PROC = 5
               AND ped.FEC_FACT = TO_DATE (psfechafacturacion, 'DD/MM/YYYY') 
               AND c.ztad_oid_terr_admi = d.oid_terr_admi
                 AND d.zscc_oid_secc = f.oid_secc
                 AND d.terr_oid_terr = e.oid_terr
                 AND f.zzon_oid_zona = g.oid_zona
                 AND g.zorg_oid_regi = h.oid_regi
                 AND c.clie_oid_clie = i.clie_oid_clie
                 AND c.ind_acti = 1
                 AND d.ind_borr = 0
                 AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
               AND c.clie_oid_clie = ped.clie_oid_clie
               AND A.IND_ESTA = 1
               AND ped.perd_oid_peri = NVL (a.oid_peri,FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO (pscodigoperiodo)) --periodo
               AND i.ticl_oid_tipo_clie = NVL (a.oid_tipo_clie, i.ticl_oid_tipo_clie)          --tipo
               AND i.sbti_oid_subt_clie = NVL (a.oid_subt_clie, i.sbti_oid_subt_clie)          --subtipo
               AND j.tccl_oid_tipo_clasi = NVL (a.oid_tipo_clas_clie, j.tccl_oid_tipo_clasi)   --tipo clasif
               AND j.clas_oid_clas = NVL (a.oid_clas_clie, j.clas_oid_clas)                    --clasif
               AND h.oid_regi = NVL (a.oid_regi, h.oid_regi)                                   --region
               AND g.oid_zona = NVL (a.oid_zona, g.oid_zona)                                   --zona
               and ped.soca_oid_soli_cabe=seg.soca_oid_soli_cabe
) SMS
WHERE sms.CLIE_OID_CLIE = m.oid_clie
)
WHERE trim(NOMBRE) IS NOT NULL 
AND trim(fechaentre) IS NOT NULL 
AND trim(CELULAR) IS NOT NULL
AND VALORFACTURA > 0
) LISTA_SMS;
    
	TYPE interfazrec IS RECORD(
		nombre      	VARCHAR2(100),
		celular      	MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
    fechaEntre    VARCHAR2(200),
		valorFactura  VARCHAR2(20),
    cod_clie      VARCHAR2(50)
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
	
	lstexto0		VARCHAR2(132);
	lstexto1		VARCHAR2(32);
	lstexto2		VARCHAR2(35);
	lstexto3		VARCHAR2(50);
	lstexto4		VARCHAR2(30);
	
	lsfechaentrega	VARCHAR2(100);
	-- Pendiente de confirmar
	lsevento		VARCHAR2(15);
	lscanal			VARCHAR2(1);
	
  lsw         VARCHAR2(1);

  BEGIN

  lbabrirutlfile := TRUE;
	
 	lsevento:= '1';
	lscanal := '1';
 
  lsw     := '1';

	SELECT B.VAL_PAIN INTO lstexto0
		FROM BAS_PARAM_INTER B
		WHERE inte_cod_inte = pscodigointerfaz
		AND B.NOM_PAIN = 'texto0';

	SELECT B.VAL_PAIN INTO lstexto1
		FROM BAS_PARAM_INTER B
		WHERE inte_cod_inte = pscodigointerfaz
		AND B.NOM_PAIN = 'texto1';
	
	SELECT B.VAL_PAIN INTO lstexto2
		FROM BAS_PARAM_INTER B
		WHERE inte_cod_inte = pscodigointerfaz
		AND B.NOM_PAIN = 'texto2';
	
	SELECT B.VAL_PAIN INTO lstexto3
		FROM BAS_PARAM_INTER B
		WHERE inte_cod_inte = pscodigointerfaz
		AND B.NOM_PAIN = 'texto3';
	
	SELECT B.VAL_PAIN INTO lstexto4
		FROM BAS_PARAM_INTER B
		WHERE inte_cod_inte = pscodigointerfaz
		AND B.NOM_PAIN = 'texto4';
	
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

        IF interfazrecord.COUNT > 0 and lsw = '1' THEN
            lsw := '0';
            lslinea := 'Number;Message;Autogenerado';
            utl_file.put_line(v_handle,lslinea);          
        END IF;

        IF interfazrecord.COUNT > 0 THEN
          FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
            lslinea := SUBSTR(TRIM(interfazrecord(x).celular),1,10) || ';' ||
                       lstexto0						             ||
                       TRIM(interfazrecord(x).nombre)		     ||
					             lstexto1						             ||
                       TRIM(interfazrecord(x).valorFactura)  ||
                       lstexto2                        ||
                       TRIM(interfazrecord(x).fechaEntre)    ||
                       lstexto3                        ||
                       lstexto4 || ';' || interfazrecord(x).cod_clie;
            utl_file.put_line(v_handle,lslinea);
          END LOOP;

        END IF;
        EXIT WHEN c_interfaz%NOTFOUND;

      END LOOP;

    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      -- Procedimiento final para generar interfaz 
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

    END IF;
    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,1,1000);
      raise_application_error(-20123,'ERROR INT_PR_SMS_ENVIO_MENSA_CONSU: ' || ls_sqlerrm);

  END int_pr_sms_envio_mensa_consu;  

/*****************************************************************************************
  Descripcion          : Genera Interfaz de Envio a Consultoras de pago a cuentas corrientes por recaudo bancario
  Fecha Creacion   : 13/10/2014
  Autor                  : Sebastian Guerra
******************************************************************************************/
  PROCEDURE int_pr_sms_envio_consu_pagos
  (
    pscodigopais            VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz      VARCHAR2,
    psnombrearchivo     VARCHAR2,
    psfechaproceso       VARCHAR2,
    psflagnovedades     VARCHAR2
  )
  IS
    CURSOR c_interfaz IS
        SELECT a.oid_movi_banc,
                     f.val_text_comu,
                     'BELCORP. ' || b.val_nom1
                    || ' se ha realizado el abono a su cuenta por $'
                    || replace(a.imp_pago, ',', '.')
                    || ' con fecha '
                    || TO_CHAR (a.fec_proc, 'dd/mm/yyyy') || ' Mayor informacion en www.somosbelcorp.com' val_text,
                    b.cod_clie
          FROM ccc_movim_banca a,
               mae_clien b,
               mae_clien_tipo_subti c,
               lar_tipo_clien_vip d,
               mae_clien_clasi e,
               mae_clien_comun f
         WHERE a.clie_oid_clie = b.oid_clie
           AND b.oid_clie = c.clie_oid_clie
           AND c.oid_clie_tipo_subt = e.ctsu_oid_clie_tipo_subt
           AND e.tccl_oid_tipo_clasi = oid_tipo_clas
           AND clas_oid_clas = oid_clas
           AND a.clie_oid_clie = f.clie_oid_clie
           AND f.ticm_oid_tipo_comu = 6 
           AND TO_CHAR (a.fec_proc, 'dd/mm/yyyy') = psfechaproceso;

    CURSOR c_interfaz_novedades IS
        SELECT a.oid_movi_banc,
                     f.val_text_comu,
                     'BELCORP. ' || b.val_nom1
                    || ' se ha realizado el abono a su cuenta por $'
                    || replace(a.imp_pago, ',', '.')
                    || ' con fecha '
                    || TO_CHAR (a.fec_proc, 'dd/mm/yyyy') || ' Mayor informacion en www.somosbelcorp.com' val_text,
                    b.cod_clie
          FROM ccc_movim_banca a,
               mae_clien b,
               mae_clien_tipo_subti c,
               lar_tipo_clien_vip d,
               mae_clien_clasi e,
               mae_clien_comun f
         WHERE a.clie_oid_clie = b.oid_clie
           AND b.oid_clie = c.clie_oid_clie
           AND c.oid_clie_tipo_subt = e.ctsu_oid_clie_tipo_subt
           AND e.tccl_oid_tipo_clasi = oid_tipo_clas
           AND clas_oid_clas = oid_clas
           AND a.clie_oid_clie = f.clie_oid_clie
           AND f.ticm_oid_tipo_comu = 6 
           AND TO_CHAR (a.fec_proc, 'dd/mm/yyyy') = psfechaproceso 
           AND a.oid_movi_banc NOT IN(SELECT DISTINCT oid_movi_banc FROM ccc_banco_sms);

    TYPE interfazrec IS RECORD(
      oid_movi_banc                 ccc_movim_banca.oid_movi_banc%TYPE, 
      val_text_comu                 mae_clien_comun.val_text_comu%TYPE,
      val_text                           VARCHAR2(150),
      cod_clie                      mae_clien.cod_clie%TYPE
    );
    
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo              bas_inter.dir_temp%TYPE;
    w_filas                    NUMBER := 1000;
    v_handle                utl_file.file_type;
    lslinea                     VARCHAR2(1000);
    lsnombrearchivo     VARCHAR2(50);
    lbabrirutlfile            BOOLEAN;
    lsnumerolote          VARCHAR2(12);

  BEGIN

    lbabrirutlfile := TRUE;
    lsnumerolote := substr(psnombrearchivo, 4);

    CASE psflagnovedades
    WHEN '0' THEN
    OPEN c_interfaz;
      LOOP
        FETCH c_interfaz BULK COLLECT
          INTO interfazrecord LIMIT w_filas;

          /* Procedimiento inicial para generar interfaz */
          IF lbabrirutlfile THEN
            gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais, pscodigosistema, pscodigointerfaz, psnombrearchivo, lsdirtempo, lsnombrearchivo, v_handle);
            lbabrirutlfile := FALSE;
          END IF;

          IF interfazrecord.COUNT > 0 THEN

                   lsLinea := 'Number;Message;Autogenerado';

                   utl_file.put_line(v_handle, lslinea);


            FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
               lsLinea := interfazrecord(x).val_text_comu||';'||
                               interfazrecord(x).val_text||';'||
                               interfazrecord(x).cod_clie;
                               
              utl_file.put_line(v_handle, lslinea);
                   
                   insert into ccc_banco_sms
                     (oid_movi_banc, num_lote_sms)
                   values
                     (interfazrecord(x).oid_movi_banc, lsnumerolote);
            END LOOP;
          END IF;
        EXIT WHEN c_interfaz%NOTFOUND;
      END LOOP;
    CLOSE c_interfaz;

    WHEN '1' THEN
        OPEN c_interfaz_novedades;
          LOOP
            FETCH c_interfaz_novedades BULK COLLECT
              INTO interfazrecord LIMIT w_filas;

              /* Procedimiento inicial para generar interfaz */
              IF lbabrirutlfile THEN
                gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais, pscodigosistema, pscodigointerfaz, psnombrearchivo, lsdirtempo, lsnombrearchivo, v_handle);
                lbabrirutlfile := FALSE;
              END IF;

              IF interfazrecord.COUNT > 0 THEN
                
                   lsLinea := 'Number;Message';

                   utl_file.put_line(v_handle, lslinea);                
                
                FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
                   lsLinea := interfazrecord(x).val_text_comu||';'||
                                   interfazrecord(x).val_text;

                   utl_file.put_line(v_handle, lslinea);
                   
                   insert into ccc_banco_sms
                     (oid_movi_banc, num_lote_sms)
                   values
                     (interfazrecord(x).oid_movi_banc, lsnumerolote);
                END LOOP;
              END IF;
            EXIT WHEN c_interfaz_novedades%NOTFOUND;
          END LOOP;
        CLOSE c_interfaz_novedades;
    END CASE;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo, lsnombrearchivo);
    END IF;
    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,1,1000);
      raise_application_error(-20123, 'ERROR INT_PR_SMS_ENVIO_CONSU_PAGOS: ' || ls_sqlerrm);

  END int_pr_sms_envio_consu_pagos;
  
END INT_PKG_SMS;
/
