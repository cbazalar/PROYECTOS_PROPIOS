CREATE OR REPLACE package ZON_PKG_REPOR is

  -- Author  : Luis Sebastian Guerra Chacaltana
  -- Created : 28/04/2014 11:46:30 a.m.
  -- Purpose : paquete que contiene los reportes del modulo de zonificacion

   w_filas                      NUMBER := 5000;
   ln_sqlcode                NUMBER(10);
   ls_sqlerrm                 VARCHAR2(1000);

  /*********************************************************************************
  Descripcion          : Inserta resultados de consulta de movimientos de territorio en temporal
  Fecha Creacion   : 27/05/2013
  Autor                  : Guerra Chacaltana Luis Sebastian
  Parametros        :
    pscodigopais                      codigo de pais
    pscodigocampania             codigo de campania
    pscodigomarca                  codigo de marca
    pscodigocanal                   codigo de canal
    pscodigocliente                 codigo de consultora
    pscondicionfechaactua     condicion de fecha de actualizacion
    psoidproceso                    oid de proceso
    cadena01                         cadena que contiene primera parte de sentencia sql
    cadena02                         cadena que contiene segunda parte de sentencia sql
  **********************************************************************************/
  PROCEDURE zon_pr_tempo_movim_terri_csv
  (
    pscodigopais                      VARCHAR2,
    pscodigocampania             VARCHAR2,
    pscodigomarca                  VARCHAR2,
    pscodigocanal                    VARCHAR2,
    pscodigocliente                  VARCHAR2,
    pscondicionfechaactua      VARCHAR2,
    psoidproceso             OUT NUMBER
  );

  /******************************************************************************
  Descripcion            : Genera archivo CSV correspondiente al Reporte de Movimiento de Territorios
  Fecha Creacion     : 28/05/2013
  Autor                     : Sebastian Guerra
  Parametros           :
    pscodigopais           codigo de pais
    psnombrearchivo    nombre de archivo
    pstitulo                   titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
  ******************************************************************************/
  PROCEDURE zon_pr_repor_movim_terri_csv
  (
    pscodigopais              VARCHAR2,
    psoidproceso             NUMBER,
    psnombrearchivo       VARCHAR2,
    pstitulo                       VARCHAR2,
    psdirectorio                OUT VARCHAR2
  );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte zon Terrotorio 
                    Unidades Geograficas
Fecha Creacion    : 21/01/2015
Autor             : Ivan Tocto
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psTitulo : Cabecera del archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE ZON_PR_GENER_REPOR_TUNGE_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

end ZON_PKG_REPOR;
/

CREATE OR REPLACE package body ZON_PKG_REPOR is

  /*********************************************************************************
  Descripcion          : Inserta resultados de consulta de movimientos de territorio en temporal
  Fecha Creacion   : 27/05/2013
  Autor                  : Guerra Chacaltana Luis Sebastian
  Parametros        :
    pscodigopais                      codigo de pais
    pscodigocampania             codigo de campania
    pscodigomarca                  codigo de marca
    pscodigocanal                   codigo de canal
    pscodigocliente                 codigo de consultora
    pscondicionfechaactua     condicion de fecha de actualizacion
    psoidproceso                    oid de proceso
    cadena01                         cadena que contiene primera parte de sentencia sql
    cadena02                         cadena que contiene segunda parte de sentencia sql
  **********************************************************************************/
  PROCEDURE zon_pr_tempo_movim_terri_csv
  (
    pscodigopais                      VARCHAR2,
    pscodigocampania             VARCHAR2,
    pscodigomarca                  VARCHAR2,
    pscodigocanal                    VARCHAR2,
    pscodigocliente                  VARCHAR2,
    pscondicionfechaactua      VARCHAR2,
    psoidproceso                  OUT NUMBER
  ) IS

    cadena01 VARCHAR2(4000);
    cadena02 VARCHAR2(4000);

    BEGIN

        cadena01:='';
        cadena02:='';

        select zon_seq_movim_terri.nextval into psoidproceso from dual;

        cadena01 :=
        'insert into zon_tempo_movim_terri(oid_proc_movt, cod_clie, num_docu_clie, nom_clie, esta_clie,
        cod_regi_ante, cod_zona_ante, cod_secc_ante, cod_terr_ante,
        cod_regi_actu, cod_zona_actu, cod_secc_actu, cod_terr_actu, val_dire,
        val_barrio, cod_camp_01er_pedi, fec_ingr, val_tele, val_celu,
        cod_camp_ulti_pedi, fec_modi, usu_modi, cod_depa_clie, cod_prov_clie, cod_dist_clie,
        nom_depa, nom_prov, nom_dist)
        SELECT '''||psoidproceso||''' oid_proc_movt, 
                    gen_pkg_gener.gen_fn_devuelve_cod_clie(UA_ANTERIOR.clie_oid_clie) CODIGO,
                    mci.num_docu_iden DOCIDENT,
                    cl.val_nom1 || '' '' || cl.val_nom2 || '' '' || cl.val_ape1 || '' '' || cl.val_ape2 NOMBRES,
                    i18.val_i18n ESTATUS,
                    UA_ANTERIOR.cod_regi REGANT,
                    UA_ANTERIOR.cod_zona ZONANT,
                    UA_ANTERIOR.cod_secc SECANT,
                    UA_ANTERIOR.cod_terr TERRANT,
                    UA_ACTUAL.cod_regi REGACT,
                    UA_ACTUAL.cod_zona ZONACT,
                    UA_ACTUAL.cod_secc SECACT,
                    UA_ACTUAL.cod_terr TERRACT,
                    sv.des_abrv_tipo_via || '' '' || dir.val_nomb_via || '' '' || dir.num_ppal ||  dir.val_obse DIRECC,
                    dir.val_barr BARRIO,
                    spc.cod_peri CAMPAN,
                    mpc.fec_cont FECING,
                    tc.val_text_comu TELEF,
                    tm.val_text_comu CELULAR,
                    (select max(SS.cod_peri)
                       from ped_solic_cabec_acum2 AC,
                            cra_perio RR,
                            seg_perio_corpo SS
                      where AC.clie_oid_clie = UA_ACTUAL.clie_oid_clie
                            and AC.perd_oid_peri = RR.oid_peri
                            and RR.peri_oid_peri = SS.oid_peri) ULTPEDI,
                    UA_ACTUAL.fec_ulti_actu FECACT,
                    UA_ACTUAL.usu_modi   USU_MODI,
                    ze.orde_1 ORDEN1,
                    ze.orde_2 ORDEN2,
                    ze.orde_3 ORDEN3,
                    ze1.des_geog DESCORD1,
                    ze2.des_geog DESCORD2,
                    ze3.des_geog DESCORD3
                    FROM
                       (select adm.clie_oid_clie,
                               adm.perd_oid_peri_ini,
                               ter.cod_terr,
                               sec.cod_secc,
                               reg.cod_regi,
                               zon.cod_zona,
                               adm.fec_ulti_actu fec_ulti_actu
                          from MAE_CLIEN_UNIDA_ADMIN ADM,
                               ZON_TERRI_ADMIN TERRI,
                               ZON_TERRI TER,
                               ZON_REGIO REG,
                               ZON_SECCI SEC,
                               ZON_ZONA ZON
                         where adm.perd_oid_peri_fin = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(
                                                                             GEN_PKG_GENER.GEN_FN_PERIO_NSIGU('''||pscodigopais||''','''||pscodigocampania||''', -1),
                                                                             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('''||pscodigomarca||'''), 
                                                                             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('''||pscodigocanal||''')) 
                           AND terri.pais_oid_pais = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS('''||pscodigopais||''')
                           AND terri.marc_oid_marc = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('''||pscodigomarca||''') 
                           AND terri.cana_oid_cana = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('''||pscodigocanal||''') 
                           AND terri.oid_terr_admi = adm.ztad_oid_terr_admi
                           AND ter.oid_terr = terri.terr_oid_terr
                           AND terri.ZSCC_OID_SECC = sec.OID_SECC
                           AND sec.ZZON_OID_ZONA = zon.OID_ZONA ';
                           
        cadena02 := 
                           'AND ZON.ZORG_OID_REGI = reg.OID_REGI) UA_ANTERIOR,
                        (select adm.clie_oid_clie,
                                adm.perd_oid_peri_ini,
                                ter.cod_terr,
                                sec.cod_secc,
                                reg.cod_regi,
                                zon.cod_zona,
                                adm.fec_ulti_actu fec_ulti_actu,
                                adm.usu_modi usu_modi 
                   from mae_clien_unida_admin adm,
                        zon_terri_admin terri,
                        zon_terri ter,
                        zon_regio reg,
                        zon_secci sec,
                        zon_zona zon
              where adm.perd_oid_peri_ini =  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO('''||pscodigocampania||''',
                                                                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('''||pscodigomarca||'''), 
                                                                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('''||pscodigocanal||''')) 
                AND terri.pais_oid_pais = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS('''||pscodigopais||''') 
                AND terri.marc_oid_marc = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('''||pscodigomarca||''') 
                AND terri.cana_oid_cana = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('''||pscodigocanal||''') 
                AND terri.oid_terr_admi = adm.ztad_oid_terr_admi
                AND ter.oid_terr = terri.terr_oid_terr
                AND terri.ZSCC_OID_SECC = sec.OID_SECC
                AND sec.ZZON_OID_ZONA = zon.OID_ZONA
                AND ZON.ZORG_OID_REGI = reg.OID_REGI) UA_ACTUAL,
                mae_clien cl,
                mae_clien_ident mci,
                mae_clien_direc dir,
                mae_clien_datos_adici ad,
                seg_tipo_via sv,
                mae_clien_prime_conta mpc,
                cra_perio cp,
                seg_perio_corpo spc,
                mae_clien_comun tc,
                mae_clien_comun tm,
                gen_i18n_sicc_comun i18,
                zon_terri trr,
                zon_valor_estru_geopo ze,
                zon_valor_estru_geopo ze1,
                zon_valor_estru_geopo ze2,
                zon_valor_estru_geopo ze3
            WHERE UA_ACTUAL.clie_oid_clie = UA_ANTERIOR.clie_oid_clie
            and  ( UA_ANTERIOR.cod_regi <> UA_ACTUAL.cod_regi or
                   UA_ANTERIOR.cod_zona <> UA_ACTUAL.cod_zona  or
                   UA_ANTERIOR.cod_secc <> UA_ACTUAL.cod_secc or
                   UA_ANTERIOR.cod_terr  <> UA_ACTUAL.cod_terr )
            and ua_actual.clie_oid_clie = mci.clie_oid_clie
            and ua_actual.clie_oid_clie = cl.oid_clie
            and mci.val_iden_docu_prin = 1
            and ua_actual.clie_oid_clie = dir.clie_oid_clie
            and dir.ind_elim = 0
            and dir.ind_dire_ppal = 1
            and ua_actual.clie_oid_clie = ad.clie_oid_clie
            and dir.tivi_oid_tipo_via = sv.oid_tipo_via
            and ua_actual.clie_oid_clie = mpc.clie_oid_clie
            and mpc.perd_oid_peri = cp.oid_peri
            and cp.peri_oid_peri = spc.oid_peri
            and ua_actual.clie_oid_clie = tc.clie_oid_clie (+)
            and 1 = tc.ticm_oid_tipo_comu (+)
            and ua_actual.clie_oid_clie = tm.clie_oid_clie (+)
            and 6 = tm.ticm_oid_tipo_comu (+)
            and ad.esta_oid_esta_clie = i18.val_oid
            and i18.attr_enti = ''MAE_ESTAT_CLIEN''
            and ua_actual.cod_terr = trr.cod_terr
            and trr.vepo_oid_valo_estr_geop = ze.oid_valo_estr_geop
            and ze.orde_1 = ze1.orde_1 and ze1.orde_2 is null
            and ze.orde_2 = ze2.orde_2 and  ze.orde_1 = ze2.orde_1 and ze2.orde_3 is null
            and ze.orde_1 = ze3.orde_1  and ze.orde_2 = ze3.orde_2 and  ze.orde_3 = ze3.orde_3 and ze3.orde_4 is null 
            and ('''||pscodigocliente||''' is null or cl.cod_clie ='''||pscodigocliente||''')';
            
        if (pscondicionfechaactua is not null) then
            cadena02 := cadena02 || ' ' || pscondicionfechaactua;
        end if;

        EXECUTE IMMEDIATE cadena01 || ' ' || cadena02;

    EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR ZON_PR_TEMPO_MOVIM_TERRI_CSV' || ls_sqlerrm);
    END zon_pr_tempo_movim_terri_csv;

  /******************************************************************************
  Descripcion            : Genera archivo CSV correspondiente al Reporte de Movimiento de Territorios
  Fecha Creacion     : 28/05/2013
  Autor                     : Sebastian Guerra
  Parametros           :
    pscodigopais           codigo de pais
    psnombrearchivo    nombre de archivo
    pstitulo                   titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
  ******************************************************************************/
  PROCEDURE zon_pr_repor_movim_terri_csv
  (
    pscodigopais              VARCHAR2,
    psoidproceso             NUMBER,
    psnombrearchivo       VARCHAR2,
    pstitulo                       VARCHAR2,
    psdirectorio                OUT VARCHAR2
  ) IS

    lsdirtempo       bas_inter.dir_temp%TYPE;
    w_filas            NUMBER := 5000;
    v_handle        utl_file.file_type;
    lslinea             VARCHAR2(4000);

    CURSOR c_movim_territorio IS
    SELECT cod_clie, num_docu_clie, nom_clie, esta_clie,
           cod_regi_ante, cod_zona_ante, cod_secc_ante, cod_terr_ante,
           cod_regi_actu, cod_zona_actu, cod_secc_actu, cod_terr_actu, val_dire,
           val_barrio, cod_camp_01er_pedi,
           TO_CHAR (fec_ingr, 'DD/MM/YYYY') fec_ingr, val_tele, val_celu,
           cod_camp_ulti_pedi, cod_depa_clie, cod_prov_clie, cod_dist_clie,
           nom_depa, nom_prov, nom_dist, usu_modi,
           TO_CHAR (fec_modi, 'DD/MM/YYYY')||' '||'00:00:00' fec_modi
      FROM zon_tempo_movim_terri
     WHERE oid_proc_movt = psoidproceso
     ORDER BY cod_clie;

     TYPE movimterritorioreg IS RECORD(
        cod_clie                      zon_tempo_movim_terri.cod_clie%type,
        num_docu_clie                 zon_tempo_movim_terri.num_docu_clie%type,
        nom_clie                      zon_tempo_movim_terri.nom_clie%type,
        esta_clie                     zon_tempo_movim_terri.esta_clie%type,
        cod_regi_ante                 zon_tempo_movim_terri.cod_regi_ante%type,
        cod_zona_ante                 zon_tempo_movim_terri.cod_zona_ante%type,
        cod_secc_ante                 zon_tempo_movim_terri.cod_secc_ante%type,
        cod_terr_ante                 zon_tempo_movim_terri.cod_terr_ante%type,
        cod_regi_actu                 zon_tempo_movim_terri.cod_regi_actu%type,
        cod_zona_actu                 zon_tempo_movim_terri.cod_zona_actu%type,
        cod_secc_actu                 zon_tempo_movim_terri.cod_secc_actu%type,
        cod_terr_actu                 zon_tempo_movim_terri.cod_terr_actu%type,
        val_dire                      zon_tempo_movim_terri.val_dire%type,
        val_barrio                    zon_tempo_movim_terri.val_barrio%type,
        cod_camp_01er_pedi            zon_tempo_movim_terri.cod_camp_01er_pedi%type,
        fec_ingr                      varchar2(10),
        val_tele                      zon_tempo_movim_terri.val_tele%type,
        val_celu                      zon_tempo_movim_terri.val_celu%type,
        cod_camp_ulti_pedi            zon_tempo_movim_terri.cod_camp_ulti_pedi%type,
        cod_depa_clie                 zon_tempo_movim_terri.cod_depa_clie%type,
        cod_prov_clie                 zon_tempo_movim_terri.cod_prov_clie%type,
        cod_dist_clie                 zon_tempo_movim_terri.cod_dist_clie%type,
        nom_depa                      zon_tempo_movim_terri.nom_depa%type,
        nom_prov                      zon_tempo_movim_terri.nom_prov%type,
        nom_dist                      zon_tempo_movim_terri.nom_dist%type,
        usu_modi                      zon_tempo_movim_terri.usu_modi%type,
        fec_modi                      varchar2(30)
      );

     TYPE movimterritoriotab IS TABLE OF movimterritorioreg;
    movimterritoriorecord movimterritoriotab;

    lbCabecera BOOLEAN;
    
  BEGIN

    lbCabecera := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

    gen_pkg_inter_archi.gen_pr_inici_repor_oracl(  pscodigopais,
                                                                                 psnombrearchivo,
                                                                                 '.csv',
                                                                                 pstitulo,
                                                                                 lsdirtempo,
                                                                                 v_handle );
    
    psdirectorio   := lsdirtempo;

    OPEN c_movim_territorio;
    LOOP
      FETCH c_movim_territorio BULK COLLECT
        INTO movimterritoriorecord LIMIT w_filas;
          IF movimterritoriorecord.count >= 0 THEN
            FOR x IN movimterritoriorecord.first .. movimterritoriorecord.last
            LOOP
                IF lbCabecera THEN
                   lslinea :=  
                                '"Codigo de consultora",' ||
                                '"Documento identidad",' ||
                                '"Nombres",' ||
                                '"Estatus consultora",' ||
                                '"Region anterior",' ||
                                '"Zona anterior",' ||
                                '"Seccion anterior",' ||
                                '"Territorio anterior",' ||
                                '"Region actual",' ||
                                '"Zona actual",' ||
                                '"Seccion actual",' ||
                                '"Territorio actual",' ||
                                '"Direccion",' ||
                                '"Barrio",' ||
                                '"Campaña 1er pedido",' ||
                                '"Fecha de ingreso",' ||
                                '"Telefono",' ||
                                '"Celular",' ||
                                '"Ultimo pedido",' ||
                                '"Departamento",' ||
                                '"Provincia",' ||
                                '"Distrito",' ||
                                '"Descripcion departamento",' ||
                                '"Descripcion provincia",' ||
                                '"Descripcion distrito",' ||
                                '"Usuario actualizacion",' ||
                                '"Fecha actualizacion"';
                                

                   lbCabecera := false;
                   utl_file.put_line(v_handle, lslinea);
                END IF;

                lslinea :=  
                            '=T("'|| movimterritoriorecord(x).cod_clie || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).num_docu_clie || '")' || ',' ||
                            '"'|| movimterritoriorecord(x).nom_clie || '"' || ',' ||
                            '=T("'|| movimterritoriorecord(x).esta_clie || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_regi_ante || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_zona_ante || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_secc_ante || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_terr_ante || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_regi_actu || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_zona_actu || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_secc_actu || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_terr_actu || '")' || ',' ||
                            '"'|| replace(movimterritoriorecord(x).val_dire,'-',' ') || '"' || ',' ||
                            '"'|| replace(movimterritoriorecord(x).val_barrio,'-',' ') || '"' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_camp_01er_pedi || '")' || ',' ||
                            '"'|| movimterritoriorecord(x).fec_ingr || '"' || ',' ||
                            '=T("'|| movimterritoriorecord(x).val_tele || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).val_celu || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_camp_ulti_pedi || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_depa_clie || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_prov_clie || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).cod_dist_clie || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).nom_depa || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).nom_prov || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).nom_dist || '")' || ',' ||
                            '=T("'|| movimterritoriorecord(x).usu_modi || '")' || ',' ||
                            '"'|| movimterritoriorecord(x).fec_modi || '"';

                utl_file.put_line(v_handle,lslinea);
            END LOOP;
          END IF;
      EXIT WHEN c_movim_territorio%NOTFOUND;
    END LOOP;
    CLOSE c_movim_territorio;

    utl_file.fclose(v_handle);

    delete from zon_tempo_movim_terri WHERE oid_proc_movt = psoidproceso;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ZON_PR_REPOR_MOVIM_TERRI_CSV' || ls_sqlerrm);
  END zon_pr_repor_movim_terri_csv;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte zon Terrotorio 
                    Unidades Geograficas
Fecha Creacion    : 21/01/2015
Autor             : Ivan Tocto
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psTitulo : Cabecera del archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE ZON_PR_GENER_REPOR_TUNGE_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  lsCodigoPais        VARCHAR2(3);
  
  CURSOR c_interfaz IS  
    SELECT
            ZT.COD_TERR,
            ZEG.ORDE_1 ||
            ZEG.ORDE_2 ||
            ZEG.ORDE_3 ||
            ZEG.ORDE_4 COD_GEOR,
            (
                SELECT VEG.DES_GEOG
                FROM ZON_VALOR_ESTRU_GEOPO VEG
                WHERE VEG.ORDE_1 = ZEG.ORDE_1
                AND VEG.ORDE_2 IS NULL
            ) DESC_1,
            (
                SELECT VEG.DES_GEOG
                FROM ZON_VALOR_ESTRU_GEOPO VEG
                WHERE VEG.ORDE_1 = ZEG.ORDE_1
                AND VEG.ORDE_2 = ZEG.ORDE_2
                AND VEG.ORDE_3 IS NULL
            ) DESC_2,
            (
                SELECT VEG.DES_GEOG
                FROM ZON_VALOR_ESTRU_GEOPO VEG
                WHERE VEG.ORDE_1 = ZEG.ORDE_1
                AND VEG.ORDE_2 = ZEG.ORDE_2
                AND VEG.ORDE_3 = ZEG.ORDE_3
                AND VEG.ORDE_4 IS NULL
            ) DESC_3,
            (
                SELECT VEG.DES_GEOG
                FROM ZON_VALOR_ESTRU_GEOPO VEG
                WHERE VEG.ORDE_1 = ZEG.ORDE_1
                AND VEG.ORDE_2 = ZEG.ORDE_2
                AND VEG.ORDE_3 = ZEG.ORDE_3
                AND VEG.ORDE_4 = ZEG.ORDE_4
            ) DESC_4
            FROM ZON_TERRI_GEOPO ZG, ZON_TERRI ZT, ZON_VALOR_ESTRU_GEOPO ZEG
            WHERE ZG.TERR_OID_TERR = ZT.OID_TERR
            AND ZG.VEPO_OID_VEPO = ZEG.OID_VALO_ESTR_GEOP
            ORDER BY ZT.COD_TERR, ZEG.ORDE_1, ZEG.ORDE_2, ZEG.ORDE_3, ZEG.ORDE_4;
            
TYPE interfazTipo IS RECORD (
 codigoTerritorio   NUMBER(6),
 codigoGeografia    VARCHAR2(24),
 desc1              VARCHAR2(100),
 desc2              VARCHAR2(100),
 desc3              VARCHAR2(100),
 desc4              VARCHAR2(100)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := '=T("'||interfazRecord(x).codigoTerritorio||'")' ||','||
                     '=T("'||interfazRecord(x).codigoGeografia ||'")' ||','||
                             interfazRecord(x).desc1 ||','||
                             interfazRecord(x).desc2 ||','||
                             interfazRecord(x).desc3 ||','||
                             interfazRecord(x).desc4 ;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_GENER_REPOR_TUNGE_CSV: '||ls_sqlerrm);
END ZON_PR_GENER_REPOR_TUNGE_CSV;

end ZON_PKG_REPOR;
/
