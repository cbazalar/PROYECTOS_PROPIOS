CREATE OR REPLACE PACKAGE int_pkg_flex IS
/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=5000;

/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de Consultoras Objetadas (FLX-3)
Fecha Creacion    : 17/05/2012
Autor: Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
****************************************************************************************/
PROCEDURE INT_PR_FLX_ENVIO_CONSU_OBJET
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Resultados Programas (FLX-4)
Fecha Creacion    : 17/05/2012
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
***************************************************************************/
PROCEDURE INT_PR_FLX_ENVIO_RESUL_PROGR
  (psCodigoPais            VARCHAR2,
   psCodigoSistema         VARCHAR2,
   psCodigoInterfaz        VARCHAR2,
   psNombreArchivo         VARCHAR2,
   psCodigoUsuario         VARCHAR2,
   psnumerolote            VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Recepcion de Consultoras Habiles(FLX-1)
Fecha Creacion    : 28/05/2012
Autor             : Jorge Velásquez
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
PROCEDURE INT_PR_FLX_RECEP_CONSU_HABIL( pscodigopais     VARCHAR2,
                                        pscodigosistema  VARCHAR2,
                                        pscodigointerfaz VARCHAR2,
                                        psnombrearchivo  VARCHAR2,
                                        pscodigousuario  VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Recepcion de Consultoras Comunicación(FLX-2)
Fecha Creacion    : 29/05/2012
Autor             : Jorge Velásquez
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_CONSU_COMU
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario VARCHAR2
  );

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Resultados Programas (FLX-4)
Fecha Creacion    : 09/10/2012
Autor             : Dennys Oliva Iriarte
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoPeriodo  : Codigo de Periodo
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_FLX_ENVIO_RESUL_PROGR2
  (psCodigoPais           VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2);
   
/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Consultoras Objetadas (FLX-3)
Fecha Creacion    : 09/10/2012
Autor             : Dennys Oliva Iriarte
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoPeriodo  : Codigo de Periodo
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_FLX_ENVIO_CONSU_OBJET
  (psCodigoPais           VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2);   

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Informacion de Habiles (FLX-5)
Fecha Creacion    : 09/10/2012
Autor             : Dennys Oliva Iriarte
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoPeriodo  : Codigo de Periodo
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_FLX_ENVIO_INFOR_HABIL2
  (psCodigoPais           VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2);
   
 
/***************************************************************************
Descripcion       : Recepcion de Consultoras Rechazadas desde WEB (FLX-6)
Fecha Creacion    : 20/06/2013
Autor             : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_CONSU_RECHW
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario VARCHAR2
  );

/***************************************************************************
Descripcion       : Recepcion de Consultoras Recomendadas desde WEB (FLX-7)
Fecha Creacion    : 20/06/2013
Autor             : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_CONSU_RECOW
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario VARCHAR2
  );

/***************************************************************************
Descripcion       : Envia la informacion para los procesos comerciales WEB (FLX-78
Fecha Creacion    : 20/06/2013
Autor             : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema    : Codigo del Sistema
 psCodigoPeriodo : Codigo de la campaña
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_ENVIO_PROCE_COMER
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigousuario VARCHAR2
  );
  
/***************************************************************************
Descripcion       : Envia la informacion de los resusltados del programa
Fecha Creacion    : 20/06/2013
Autor             : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema    : Codigo del Sistema
 psCodigoPeriodo    : Campaña de cierre
 psCodigoPeriodo : Codigo de la campaña
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_ENVIO_REPRO
  (psCodigoPais            VARCHAR2,
   psCodigoSistema         VARCHAR2,
   psCodigoInterfaz        VARCHAR2,
   psNombreArchivo         VARCHAR2,
   psCodigoPeriodo         VARCHAR2,
   psCodigoUsuario         VARCHAR2
  );  
 
/***************************************************************************
Descripcion       : Recepcion de variables del modelo
Fecha Creacion    : 20/06/2013
Autor             : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_VARIA_MODEL
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psCodigoPeriodo  VARCHAR2,
    pscodigousuario VARCHAR2
  );
   
-----------------------------------------
-- reemplaza a la FLX-4
-----------------------------------------
PROCEDURE INT_PR_FLX_ENVIO_RESUL_PROGR
  (psCodigoPais           VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2);
   
-----------------------------------------
-- reemplaza a la FLX-5
-----------------------------------------
PROCEDURE INT_PR_FLX_ENVIO_INFOR_HABIL
  (psCodigoPais           VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2);  

/***************************************************************************
Descripcion               : Envia la informacion de las variables calculadas en cuentas corrientes a Datamart
Fecha Creacion        : 26/11/2013
Autor                        : Sebastian Guerra
Parametros:
 psCodigoPais           : Codigo de Pais
 psCodigoSistema    : Codigo del Sistema
 psCodigoPeriodo    : Codigo de la campaña
 psCodigoUsuario    : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_ENVIO_VARIA_CTACTE
  (psCodigoPais           VARCHAR2,
   psCodigoSistema      VARCHAR2,
   psCodigoInterfaz     VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psCodigoPeriodo       VARCHAR2,
   psCodigoUsuario       VARCHAR2);

/***************************************************************************
Descripcion         : Recepcion de variables de datamart
Fecha Creacion  : 05/12/2013
Autor                  : Sebastian Guerra
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Archivo
 psCodigoPeriodo  : Codigo de Periodo
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_VARIA_DATAM
  (pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigousuario VARCHAR2);

END INT_PKG_FLEX;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_FLEX IS

/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de Consultoras Objetadas (FLX-3)
Fecha Creacion    : 17/05/2012
Autor: Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
****************************************************************************************/
PROCEDURE INT_PR_FLX_ENVIO_CONSU_OBJET
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2)
IS
   CURSOR c_interfaz IS
      select f.cod_pais,
             f.cod_peri_fact,
             f.cod_clie,
             '0',
             (select m.cod_clie
                from zon_zona zz, mae_clien m
               where zz.oid_zona = gen_pkg_gener.GEN_FN_CLIEN_DATOS(f.cod_clie, 'OID_ZONA')
                 and m.oid_clie = zz.clie_oid_clie) cod_gz,
             to_char(sysdate, 'DD/MM/YYYY') fec_carga
        from flx_objec_comun_flexi f
       where f.cod_pais = psCodigoPais
         and f.ind_envi = '0';
      /*select h.cod_pais,
               h.cod_peri_fact,
               h.cod_clie,
               h.ind_acti,
               (select m.cod_clie
                  from zon_zona zz, mae_clien m
                 where zz.oid_zona = gen_pkg_gener.GEN_FN_CLIEN_DATOS(h.cod_clie,
                                                                      'OID_ZONA')
                   and m.oid_clie = zz.clie_oid_clie) cod_gz,
               to_char(sysdate,'DD/MM/YYYY') fec_carga
        from flx_consu_habil_flexi h
        where h.cod_pais = psCodigoPais
        and h.ind_envi = '0';*/

   TYPE interfazRec IS RECORD
       (
        cod_pais       SEG_PAIS.COD_PAIS%TYPE,
        cod_peri_fact  flx_consu_habil_flexi.cod_peri_fact%TYPE,
        cod_clie       flx_consu_habil_flexi.cod_clie%TYPE,
        ind_acti       flx_consu_habil_flexi.ind_acti%TYPE,
        cod_gz         MAE_CLIEN.COD_CLIE%TYPE,
        fec_carga      VARCHAR2(10)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
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
                          lsLinea :=  interfazRecord(x).cod_pais        ||';'||
                                      interfazRecord(x).cod_peri_fact   ||';'||
                                      interfazRecord(x).cod_clie        ||';'||
                                      interfazRecord(x).ind_acti        ||';'||
                                      interfazRecord(x).cod_gz          ||';'||
                                      interfazRecord(x).fec_carga;

                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                       --SE ACTUALIZA EL INDICADOR DE ENVIADO y el lote de recepcion
                       update flx_objec_comun_flexi
                       set ind_envi = '1',
                           num_lote = psnumerolote,
                           USU_DIGI = psCodigoUsuario,
                           FEC_DIGI = SYSDATE
                       where COD_PAIS = interfazRecord(x).cod_pais
                          AND COD_CLIE = interfazRecord(x).cod_clie
                          AND COD_PERI_FACT = interfazRecord(x).cod_peri_fact;

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FLX_ENVIO_CONSU_OBJET: '||ls_sqlerrm);

END INT_PR_FLX_ENVIO_CONSU_OBJET;


/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Resultados Programas (FLX-4)
Fecha Creacion    : 17/05/2012
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
***************************************************************************/
PROCEDURE INT_PR_FLX_ENVIO_RESUL_PROGR
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2)
IS

   CURSOR c_interfaz IS
        select fl.cod_pais,
               fl.cod_peri_comu,
               fl.cod_peri_fact,
               fl.cod_clie,
               -- Para que la carta este remitida, debe existir mensaje de flexipago en el buzon,
               -- para la consultora, con fecha de impresion diferente de null
               (select count(1)
                  from msg_buzon_mensa bm
                 where bm.fec_impr is not null -- impreso
                   and bm.mens_oid_mens in (select ms.oid_mens
                                              from msg_mensa ms
                                             where ms.cod_mens like 'FPG0%') -- mensajes de flexipago
                   and bm.clie_oid_clie = m.oid_clie),
               -- Que la carta haya sido recepcionada, implica
               -- que la consultora haya sido activada por SAC
               (select count(1)
                  from flx_consu_habil_flexi h
                 where h.cod_clie = fl.cod_clie
                   and h.cod_peri_comu = fl.cod_peri_comu
                   and h.ind_acti = '1') car_recep,
               '0' lev_pedido,
               0 mon_fp_solicitado,
               0 mon_fp_otorgado,
               0 mon_pedido_normal,
               to_char(sysdate,'DD/MM/YYYY') fec_carga
        from flx_consu_comun_flexi fl, mae_clien m
        where fl.cod_pais = psCodigoPais
          and m.cod_clie(+) = fl.cod_clie
                and fl.ind_envi = '0';

   TYPE interfazRec IS RECORD
       (
        cod_pais       SEG_PAIS.COD_PAIS%TYPE,
        cod_peri_comu  flx_consu_comun_flexi.cod_peri_comu%TYPE,
        cod_peri_fact  flx_consu_comun_flexi.cod_peri_fact%TYPE,
        cod_clie       flx_consu_comun_flexi.cod_clie%TYPE,
        car_remit            VARCHAR2(1),
        car_recep            VARCHAR2(1),
        lev_pedido           VARCHAR2(1),
        mon_fp_solicitado    NUMBER(12,2),
        mon_fp_otorgado      NUMBER(12,2),
        mon_pedido_normal    NUMBER(12,2),
        fec_carga            VARCHAR2(10)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
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
                          lsLinea :=  interfazRecord(x).cod_pais              ||';'||
                                      interfazRecord(x).cod_peri_comu         ||';'||
                                      interfazRecord(x).cod_peri_fact         ||';'||
                                      interfazRecord(x).cod_clie              ||';'||
                                      interfazRecord(x).car_remit             ||';'||
                                      interfazRecord(x).car_recep             ||';'||
                                      interfazRecord(x).lev_pedido            ||';'||
                                      interfazRecord(x).mon_fp_solicitado     ||';'||
                                      interfazRecord(x).mon_fp_otorgado       ||';'||
                                      interfazRecord(x).mon_pedido_normal     ||';'||
                                      interfazRecord(x).fec_carga;

                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                       --SE ACTUALIZA EL INDICADOR DE ENVIADO y el lote de recepcion
                       update flx_consu_comun_flexi
                       set ind_envi = '1',
                           num_lote = psnumerolote,
                           USU_DIGI = psCodigoUsuario,
                           FEC_DIGI = SYSDATE
                       where COD_PAIS = interfazRecord(x).cod_pais
                          AND COD_CLIE = interfazRecord(x).cod_clie
                          AND COD_PERI_FACT = interfazRecord(x).cod_peri_fact;

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FLX_ENVIO_RESUL_PROGR: '||ls_sqlerrm);

END INT_PR_FLX_ENVIO_RESUL_PROGR;
/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Consultoras Habiles
  Fecha Creacion    : 28/05/2012
  Autor             : Jorge Luis Velásquez Sánchez
  ***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_CONSU_HABIL
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario VARCHAR2
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

    TYPE t_cod_pais IS TABLE OF FLX_CONSU_HABIL_FLEXI.COD_PAIS%TYPE;
    TYPE t_cod_camp_comu IS TABLE OF FLX_CONSU_HABIL_FLEXI.COD_PERI_COMU%TYPE;
    TYPE t_cod_camp_fact IS TABLE OF FLX_CONSU_HABIL_FLEXI.COD_PERI_FACT%TYPE;
    TYPE t_cod_clie IS TABLE OF FLX_CONSU_HABIL_FLEXI.COD_CLIE%TYPE;
    TYPE t_line_cred IS TABLE OF FLX_CONSU_HABIL_FLEXI.VAL_LINE_CRED%TYPE;
    TYPE t_pedi_base IS TABLE OF FLX_CONSU_HABIL_FLEXI.VAL_PEDI_BASE%TYPE;
    TYPE t_cali_comp IS TABLE OF FLX_CONSU_HABIL_FLEXI.IND_CALI_COMP%TYPE;
    TYPE t_cali_expe IS TABLE OF FLX_CONSU_HABIL_FLEXI.IND_CALI_EXPE_FLEX%TYPE;

    TYPE t_ind_acti IS TABLE OF FLX_CONSU_HABIL_FLEXI.Ind_Acti%TYPE;

    v_cod_pais        t_cod_pais      := t_cod_pais();
    v_cod_camp_comu   t_cod_camp_comu := t_cod_camp_comu();
    v_cod_camp_fact   t_cod_camp_fact := t_cod_camp_fact();
    v_cod_clie        t_cod_clie      := t_cod_clie();
    v_line_cred       t_line_cred     := t_line_cred();
    v_pedi_base       t_pedi_base     := t_pedi_base();
    v_cali_comp       t_cali_comp     := t_cali_comp();
    v_cali_expe       t_cali_expe     := t_cali_expe();
    v_ind_acti        t_ind_acti      := t_ind_acti();

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;

    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

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
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_camp_comu.extend;
                  v_cod_camp_comu(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 3) THEN
                  v_cod_camp_fact.extend;
                  v_cod_camp_fact(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 4) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));

                  -- Ver si la consultora tiene un registro anterior y verificar su estado [Activa/Inactiva]
                  v_ind_acti.extend;
                  begin
                       select f.ind_acti
                         into v_ind_acti(i)
                         from flx_consu_habil_flexi f
                        where f.cod_pais = v_cod_pais(i)
                          and f.cod_clie = v_cod_clie(i)
                          AND f.cod_peri_fact = (select max(ff.cod_peri_fact)
                                                   from flx_consu_habil_flexi ff
                                                  where ff.cod_pais = f.cod_pais
                                                    and ff.cod_clie = f.cod_clie);
                  exception
                      when no_data_found then
                        v_ind_acti(i) := '0';
                  end;

                ELSIF (posicion = 5) THEN
                  v_line_cred.extend;
                  v_line_cred(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 6) THEN
                  v_pedi_base.extend;
                  v_pedi_base(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 7) THEN
                  v_cali_comp.extend;
                  v_cali_comp(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 8) THEN
                  v_cali_expe.extend;
                  v_cali_expe(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
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
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO FLX_CONSU_HABIL_FLEXI (
			COD_PAIS,
			COD_CLIE,
			COD_PERI_FACT,
			COD_PERI_COMU,
			VAL_LINE_CRED,
			VAL_PEDI_BASE,
			IND_CALI_COMP,
			IND_CALI_EXPE_FLEX,
      ind_acti,
			USU_DIGI,
			FEC_DIGI)
		VALUES (
			v_cod_pais(i),
			v_cod_clie(i),
			v_cod_camp_fact(i),
			v_cod_camp_comu(i),
			v_line_cred(i),
			v_pedi_base(i),
			v_cali_comp(i),
			v_cali_expe(i),
      v_ind_acti(i),
			pscodigousuario,
			SYSDATE);

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_FLX_RECEP_CONSU_HABIL: ' || ls_sqlerrm);

  END INT_PR_FLX_RECEP_CONSU_HABIL;

/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Consultoras Comunicación
  Fecha Creacion    : 29/05/2012
  Autor             : Jorge Luis Velásquez Sánchez
  ***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_CONSU_COMU
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario VARCHAR2
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

    v_cod_pais          FLX_CONSU_HABIL_FLEXI.COD_PAIS%TYPE;
    v_cod_camp_comu     FLX_CONSU_HABIL_FLEXI.COD_PERI_COMU%TYPE;
    v_cod_camp_fact     FLX_CONSU_HABIL_FLEXI.COD_PERI_FACT%TYPE;
    v_cod_clie          FLX_CONSU_HABIL_FLEXI.COD_CLIE%TYPE;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;

    --habilitado NUMBER :=0;

    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

    vaux    number := 0;
    auxcont number := 0; 

    lsnumerodocident      mae_clien_ident.num_docu_iden%TYPE;
    lsdireccion           VARCHAR2(200);
    lsunidadgeo           VARCHAR2(200);

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
                  v_cod_pais := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_camp_comu := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 3) THEN
                 v_cod_camp_fact := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 4) THEN
                  v_cod_clie := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                END IF;

                inicio := inicio + longitud;

              END LOOP;
           
           /* Evalua si la consultora se encuentra activa en la campaña actual */
           select count(1) 
             into auxcont
             from flx_consu_habil_flexi fc
            where fc.cod_peri_fact in (select bf.cod_peri 
                                         from bas_ctrl_fact bf 
                                        where bf.ind_camp_act = '1' 
                                          and bf.sta_camp = '0')
              and fc.cod_clie = v_cod_clie
              and fc.ind_habi = '1'
              and fc.ind_acti = '1';
          
           /* Solo se registran si no estan activas en la campaña actual */
           IF auxcont = 0 THEN
          
           /* Se inserta el archivo a la tabla*/
           INSERT INTO FLX_CONSU_COMUN_FLEXI(
                COD_PAIS,
                COD_CLIE,
                COD_PERI_FACT,
                COD_PERI_COMU,
                USU_DIGI,
                FEC_DIGI)
                 VALUES(
                v_cod_pais,
                v_cod_clie,
                v_cod_camp_fact,
                v_cod_camp_comu,
                pscodigousuario,
                SYSDATE);

            select count(1)
            into vaux
            from mae_clien m
            where m.cod_clie = v_cod_clie;

            IF vaux != 0 THEN

                /* Si el codigo de consultora es valido, se inserta los mensajes en el buzon*/

                /* Elimino los mensajes de flexipago para la consultora, en caso tuviera */
                delete from msg_buzon_mensa m
                 where m.clie_oid_clie = (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_cod_clie)
                   and mens_oid_mens in (SELECT oid_mens FROM msg_mensa WHERE cod_mens like 'FPG0%');

                /* Obtiene el DNI de la consultora */
                begin
                  select ci.num_docu_iden
                    into lsnumerodocident
                    from mae_clien_ident ci , mae_clien m
                   where ci.val_iden_docu_prin = '1'
                     and ci.clie_oid_clie = m.oid_clie
                     and m.cod_clie = v_cod_clie
                     and rownum = 1 ;
                exception
                  when no_data_found then
                    lsnumerodocident := null;
                end;


                /* Obtiene la direccion y la unidad geografica */

                begin
                  SELECT substr(DES_ABRV_TIPO_VIA || ' ' || VAL_NOMB_VIA || ' ' || NUM_PPAL || ' ' || VAL_OBSE,0,200),
                       substr(nivel_1
                         || DECODE (nivel_2, NULL, '', '/' || nivel_2)
                         || DECODE (nivel_3, NULL, '', '/' || nivel_3)
                         || DECODE (nivel_4, NULL, '', '/' || nivel_4)
                         || DECODE (nivel_5, NULL, '', '/' || nivel_5)
                         || DECODE (nivel_6, NULL, '', '/' || nivel_6)
                         || DECODE (nivel_7, NULL, '', '/' || nivel_7)
                         || DECODE (nivel_8, NULL, '', '/' || nivel_8)
                         || DECODE (nivel_9, NULL, '', '/' || nivel_9),0,200) AS DESC_UNI
                  INTO lsdireccion, lsunidadgeo
                    FROM (SELECT   a.OID_CLIE_DIRE OID,
                                   c.DES_ABRV_TIPO_VIA,
                                   a.VAL_NOMB_VIA,
                                   a.NUM_PPAL,
                                   a.VAL_OBSE,
                                   a.VAL_BARR,
                                   t.COD_TERR,
                                   (SELECT des_geog
                                      FROM zon_valor_estru_geopo
                                     WHERE pais_oid_pais = d.pais_oid_pais
                                       AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                       AND orde_2 IS NULL) AS nivel_1,
                                   (SELECT des_geog
                                      FROM zon_valor_estru_geopo
                                     WHERE pais_oid_pais = d.pais_oid_pais
                                       AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                       AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                       AND orde_3 IS NULL) AS nivel_2,
                                   (SELECT des_geog
                                      FROM zon_valor_estru_geopo
                                     WHERE pais_oid_pais = d.pais_oid_pais
                                       AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                       AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                       AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                       AND orde_4 IS NULL) AS nivel_3,
                                   CASE
                                      WHEN LENGTH (a.cod_unid_geog) > 18
                                         THEN (SELECT des_geog
                                                 FROM zon_valor_estru_geopo
                                                WHERE pais_oid_pais =
                                                                     d.pais_oid_pais
                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                  AND orde_5 IS NULL)
                                      ELSE NULL
                                   END AS nivel_4,
                                   CASE
                                      WHEN LENGTH (a.cod_unid_geog) > 24
                                         THEN (SELECT des_geog
                                                 FROM zon_valor_estru_geopo
                                                WHERE pais_oid_pais =
                                                                     d.pais_oid_pais
                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                  AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                                  AND orde_6 IS NULL)
                                      ELSE NULL
                                   END AS nivel_5,
                                   CASE
                                      WHEN LENGTH (a.cod_unid_geog) > 30
                                         THEN (SELECT des_geog
                                                 FROM zon_valor_estru_geopo
                                                WHERE pais_oid_pais =
                                                                     d.pais_oid_pais
                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                  AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                                  AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                                  AND orde_7 IS NULL)
                                      ELSE NULL
                                   END AS nivel_6,
                                   CASE
                                      WHEN LENGTH (a.cod_unid_geog) > 36
                                         THEN (SELECT des_geog
                                                 FROM zon_valor_estru_geopo
                                                WHERE pais_oid_pais =
                                                                     d.pais_oid_pais
                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                  AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                                  AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                                  AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
                                                  AND orde_8 IS NULL)
                                      ELSE NULL
                                   END AS nivel_7,
                                   CASE
                                      WHEN LENGTH (a.cod_unid_geog) > 42
                                         THEN (SELECT des_geog
                                                 FROM zon_valor_estru_geopo
                                                WHERE pais_oid_pais =
                                                                     d.pais_oid_pais
                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                  AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                                  AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                                  AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
                                                  AND orde_8 = SUBSTR (a.cod_unid_geog, 43, 6)
                                                  AND orde_9 IS NULL)
                                      ELSE NULL
                                   END AS nivel_8,
                                   CASE
                                      WHEN LENGTH (a.cod_unid_geog) > 48
                                         THEN (SELECT des_geog
                                                 FROM zon_valor_estru_geopo
                                                WHERE pais_oid_pais =
                                                                     d.pais_oid_pais
                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                  AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                                  AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                                  AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
                                                  AND orde_8 = SUBSTR (a.cod_unid_geog, 43, 6)
                                                  AND orde_9 = SUBSTR (a.cod_unid_geog, 49, 6))
                                      ELSE NULL
                                   END AS nivel_9
                              FROM MAE_CLIEN_DIREC a,
                                   MAE_TIPO_DIREC b,
                                   SEG_TIPO_VIA c,
                                   MAE_CLIEN d,
                                   ZON_TERRI t,
                                   mae_clien m
                             WHERE d.OID_CLIE = m.oid_clie
                               and m.cod_clie = v_cod_clie
                               AND d.OID_CLIE = a.CLIE_OID_CLIE
                               AND a.IND_ELIM = 0
                               AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                               AND c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
                               AND a.IND_DIRE_PPAL  = 1
                               AND a.TERR_OID_TERR = t.OID_TERR (+)
                          ORDER BY a.OID_CLIE_DIRE DESC)
                   WHERE ROWNUM = 1;
                exception
                  when no_data_found then
                    lsdireccion := null;
                    lsunidadgeo := null;
                end;

                -- Solo se inserta si esta parametrizado en el BAS_PARAM_PAIS
                IF nvl(gen_pkg_gener.gen_fn_param_pais(pscodigopais,pscodigosistema,'002'),'0') = '1' THEN

                 -- Mensaje flexipago 2
                INSERT INTO msg_buzon_mensa
                  (oid_buzo_mens,
                   num_secu,
                   ind_esta_mens,
                   clie_oid_clie,
                   DATO_VARI_02,
                     DATO_VARI_03,
                     DATO_VARI_04,
                     DATO_VARI_05,
                   mens_oid_mens,
                   modu_oid_modu_orig,
                   fec_grab,
                   ind_list_cons,
                   ind_acti)
                VALUES
                  (msg_bume_seq.nextval,
                   msg_bum2_seq.nextval,
                   1,
                   (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_cod_clie),
                     (select substr(RTRIM(m.val_nom1 || ' ' || m.val_nom2 /*|| ' ' || m.val_ape1 || ' ' || m.val_ape2*/),1,200)
                        from mae_clien m
                       where m.cod_clie = v_cod_clie),
                     lsnumerodocident,
                     lsdireccion     ,
                     lsunidadgeo     ,
                   (SELECT oid_mens FROM msg_mensa WHERE cod_mens = 'FPG02'),
                   1,
                   SYSDATE,
                   0,
                   1);

                END IF;

            END IF;

            END IF;

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

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_FLX_RECEP_CONSU_COMU: ' || ls_sqlerrm);

  END INT_PR_FLX_RECEP_CONSU_COMU;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Resultados Programas (FLX-4)
Fecha Creacion    : 09/10/2012
Autor             : Dennys Oliva Iriarte
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoPeriodo  : Codigo de Periodo
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_FLX_ENVIO_RESUL_PROGR2
  (psCodigoPais           VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2)
IS
   CURSOR c_interfaz IS
        select habil.cod_pais,
               habil.cod_peri_fact,
               fac.val_nume_soli,
               habil.cod_clie,
               nvl(fac.ind_admi_cart,'0'),                         
               fac.val_mnto_flex,                                  
               (select cf.val_mont_fina from flx_cuota_flexi_factu_cabec cf
                 where cf.cod_peri = habil.cod_peri_fact
                   and cf.cod_clie = habil.cod_clie ) val_mont_fina,
               fac.val_tota_paga_loca,  
               (select cf.val_fact_conv from flx_cuota_flexi_factu_cabec cf
                 where cf.cod_peri = habil.cod_peri_fact
                   and cf.cod_clie = habil.cod_clie ) val_fact_conv,
               fac.IND_UTIL_FLEX,
               fac.IND_ACEP_FLEX, 
               fac.IND_ACEP_FLEX  
        from flx_consu_habil_flexi habil ,  
               (select scc.val_nume_soli,               
                       hc.ind_admi_cart,
                       scc.val_tota_paga_loca,
                       hc.cod_clie,
                       hc.val_mnto_flex,
                       hc.IND_UTIL_FLEX,
                       hc.IND_ACEP_FLEX
                  from ped_solic_cabec sc,
                       ped_solic_cabec scc,
                       int_solic_conso_cabec hc 
                 where sc.oid_soli_cabe = hc.soca_oid_soli_cabe_refe
                   and scc.oid_soli_cabe = sc.soca_oid_soli_cabe  
                   and hc.cod_peri = psCodigoPeriodo
                   and hc.ind_proc_gp2 = '1') fac
        where habil.cod_peri_fact = psCodigoPeriodo
        and habil.cod_clie = fac.cod_clie(+)
        and exists (select null 
                      from int_solic_conso_cabec cab 
                     where cab.cod_peri = psCodigoPeriodo 
                       and cab.ind_proc_gp2 = '1')
        union

        select habil.cod_pais,
               habil.cod_peri_fact,
               fac.val_nume_soli,
               habil.cod_clie,
               nvl(fac.ind_admi_cart,'0'),                         
               fac.val_mnto_flex,                                  
               (select cf.val_mont_fina from flx_cuota_flexi_factu_cabec cf
                 where cf.cod_peri = habil.cod_peri_fact
                   and cf.cod_clie = habil.cod_clie ) val_mont_fina,
               fac.val_tota_paga_loca,  
               (select cf.val_fact_conv from flx_cuota_flexi_factu_cabec cf
                 where cf.cod_peri = habil.cod_peri_fact
                   and cf.cod_clie = habil.cod_clie ) val_fact_conv,
               fac.IND_UTIL_FLEX,
               fac.IND_ACEP_FLEX, 
               fac.IND_ACEP_FLEX  
        from flx_consu_habil_flexi habil ,  
               (select scc.val_nume_soli,               
                       hc.ind_admi_cart,
                       scc.val_tota_paga_loca,
                       hc.cod_clie,
                       hc.val_mnto_flex,
                       hc.IND_UTIL_FLEX,
                       hc.IND_ACEP_FLEX
                  from ped_solic_cabec sc,
                       ped_solic_cabec scc,
                       ped_histo_solic_conso_cabec hc 
                 where sc.oid_soli_cabe = hc.soca_oid_soli_cabe_refe
                   and scc.oid_soli_cabe = sc.soca_oid_soli_cabe  
                   and hc.cod_peri =  psCodigoPeriodo
                   and hc.ind_proc_gp2 = '1') fac
        where habil.cod_peri_fact = psCodigoPeriodo 
        and habil.cod_clie = fac.cod_clie(+)
        and exists (select null 
                      from ped_histo_solic_conso_cabec cab 
                     where cab.cod_peri = psCodigoPeriodo 
                       and cab.ind_proc_gp2 = '1') ;           

   TYPE interfazRec IS RECORD
       (
        cod_pais            flx_consu_habil_flexi.COD_PAIS%TYPE,
        cod_peri_fact       flx_consu_habil_flexi.cod_peri_fact%TYPE,   
        val_nume_soli       ped_solic_cabec.val_nume_soli%type,     
        cod_clie            flx_consu_comun_flexi.cod_clie%TYPE,
        ind_admi_cart       int_solic_conso_cabec.ind_admi_cart%TYPE,
        val_mnto_flex       int_solic_conso_cabec.val_mnto_flex%TYPE,
        val_mont_fina       flx_cuota_flexi_factu_cabec.val_mont_fina%TYPE,
        val_tota_paga_loca  ped_solic_cabec.val_tota_paga_loca%TYPE,
        val_fact_conv       flx_cuota_flexi_factu_cabec.val_fact_conv%TYPE,
        ind_util_flex       int_solic_conso_cabec.ind_util_flex%TYPE,
        ind_acep_flex       int_solic_conso_cabec.ind_acep_flex%TYPE, 
        ind_firm_flex       int_solic_conso_cabec.ind_acep_flex%TYPE          
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
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
                          lsLinea :=  interfazRecord(x).cod_pais            ||';'||
                                      interfazRecord(x).cod_peri_fact       ||';'||
                                      interfazRecord(x).val_nume_soli       ||';'||
                                      interfazRecord(x).cod_clie            ||';'||
                                      interfazRecord(x).ind_admi_cart       ||';'||
                                      interfazRecord(x).val_mnto_flex       ||';'||
                                      interfazRecord(x).val_mont_fina       ||';'||
                                      interfazRecord(x).val_tota_paga_loca  ||';'||
                                      interfazRecord(x).val_fact_conv       ||';'||
                                      interfazRecord(x).ind_util_flex       ||';'||
                                      interfazRecord(x).ind_acep_flex       ||';'||
                                      interfazRecord(x).ind_firm_flex     ;

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FLX_ENVIO_RESUL_PROGR: '||ls_sqlerrm);

END INT_PR_FLX_ENVIO_RESUL_PROGR2;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Consultoras Objetadas (FLX-3)
Fecha Creacion    : 09/10/2012
Autor             : Dennys Oliva Iriarte
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoPeriodo  : Codigo de Periodo
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_FLX_ENVIO_CONSU_OBJET
  (psCodigoPais           VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2)

IS
   CURSOR c_interfaz IS
       select f.cod_pais,
              f.cod_peri_fact,
              f.cod_clie,
              '0',
              (select m.cod_clie
                 from zon_zona zz, mae_clien m
                where zz.oid_zona = gen_pkg_gener.GEN_FN_CLIEN_DATOS(f.cod_clie, 'OID_ZONA')
                  and m.oid_clie = zz.clie_oid_clie) cod_gz,
              to_char(sysdate, 'DD/MM/YYYY') fec_carga
         from flx_objec_comun_flexi f
        where f.cod_pais = psCodigoPais
          and f.cod_peri_fact = psCodigoPeriodo;

   TYPE interfazRec IS RECORD
       (
        cod_pais            flx_objec_comun_flexi.COD_PAIS%TYPE,
        cod_peri_fact       flx_objec_comun_flexi.cod_peri_fact%TYPE,                  
        cod_clie            flx_objec_comun_flexi.cod_clie%TYPE,
        status              varchar2(1),
        cod_clie_gz         mae_clien.cod_clie%TYPE,
        fecha               varchar2(10)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
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
                          lsLinea :=  interfazRecord(x).cod_pais      ||';'||   
                                      interfazRecord(x).cod_peri_fact ||';'||   
                                      interfazRecord(x).cod_clie      ||';'||   
                                      interfazRecord(x).status        ||';'||   
                                      interfazRecord(x).cod_clie_gz   ||';'||   
                                      interfazRecord(x).fecha;

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FLX_ENVIO_CONSU_OBJET: '||ls_sqlerrm);

END INT_PR_FLX_ENVIO_CONSU_OBJET;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Informacion de Habiles (FLX-5)
Fecha Creacion    : 09/10/2012
Autor             : Dennys Oliva Iriarte
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoPeriodo  : Codigo de Periodo
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_FLX_ENVIO_INFOR_HABIL2
  (psCodigoPais           VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2)
IS
   CURSOR c_interfaz IS
        select h.cod_pais,
               h.cod_peri_comu,
               h.cod_peri_fact,
               h.cod_clie,
               '1',
               (select to_char(a.fec_acci, 'dd/mm/yyyy')
                  from flx_audit_consu_habil a
                 where a.cod_clie = h.cod_clie
                   and a.cod_acci = '02'
                   and a.cod_peri_fact = h.cod_peri_fact
                   and rownum = 1),
               
               (select count(1)
                  from flx_audit_consu_habil a
                 where a.cod_clie = h.cod_clie
                   and a.cod_acci = '02'
                   and a.cod_peri_fact = h.cod_peri_fact
                   and rownum = 1),
               
               (SELECT NVL(SUM(ROUND(c.imp_pend * a.val_fact_conv, 2)), 0)
                  FROM flx_cuota_flexi_factu_cabec a,
                       flx_cuota_flexi_factu_detal b,
                       ccc_movim_cuent_corri       c
                 WHERE a.cod_peri = b.cod_peri
                   AND a.fec_fact = b.fec_fact
                   AND a.cod_clie = b.cod_clie
                   AND b.oid_movi_carg_flex = c.oid_movi_cc
                   AND a.cod_clie = h.cod_clie)
                   
          from flx_consu_habil_flexi h, 
               flx_cuota_flexi_factu_cabec f
         where h.cod_clie = f.cod_clie(+)
           and h.cod_peri_fact = f.cod_peri(+)
           and h.cod_peri_fact = psCodigoPeriodo;

   TYPE interfazRec IS RECORD
       (
        cod_pais            flx_consu_habil_flexi.COD_PAIS%TYPE,
        cod_peri_comu       flx_consu_habil_flexi.cod_peri_comu%TYPE, 
        cod_peri_fact       flx_consu_habil_flexi.cod_peri_fact%TYPE,
        cod_clie            flx_consu_habil_flexi.cod_clie%TYPE,
        ind_remi            varchar2(1),
        fec_acti            varchar2(10),
        ind_acti            varchar2(1),
        imp_pend            ccc_movim_cuent_corri.imp_pend%TYPE       
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
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
                          lsLinea :=  interfazRecord(x).cod_pais       ||';'|| 
                                      interfazRecord(x).cod_peri_comu  ||';'|| 
                                      interfazRecord(x).cod_peri_fact  ||';'|| 
                                      interfazRecord(x).cod_clie       ||';'|| 
                                      interfazRecord(x).ind_remi       ||';'|| 
                                      interfazRecord(x).fec_acti       ||';'|| 
                                      interfazRecord(x).ind_acti       ||';'||  
                                      interfazRecord(x).imp_pend;

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FLX_ENVIO_INFOR_HABIL: '||ls_sqlerrm);

END INT_PR_FLX_ENVIO_INFOR_HABIL2;


/***************************************************************************
Descripcion       : Recepcion de Consultoras Rechazadas desde WEB (FLX-6)
Fecha Creacion    : 20/06/2013
Autor             : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_CONSU_RECHW
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario VARCHAR2
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

    v_cod_pais      varchar2(3);
    v_cod_camp      varchar2(6);
    v_cod_cons      varchar2(15);
    v_cod_moti_rech varchar2(2);
    v_cod_orig      varchar2(1);
    v_cod_stat_rech varchar2(2);

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;
    lnCantidad  NUMBER := 0;

    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

  BEGIN

    -- tomamos comp campaña la maxima que existe en la tabla de hábiles
    -- para no considerar la campaña que viene en el archivo
    select max(cod_peri_comu)
    into  v_cod_camp
    from FLX_CONSU_HABIL_FLEXI where cod_pais = pscodigopais; 
    -- -- 

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
            i := i + 1;
            
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
                                v_cod_pais := nvl(TRIM(substr(lslinea, inicio, longitud)), pscodigopais);
                            ELSIF (posicion = 3) THEN
                                v_cod_cons := TRIM(translate(substr(lslinea, inicio, longitud), lscadena, lsreplace));
                            ELSIF (posicion = 4) THEN
                                v_cod_moti_rech := TRIM(translate(substr(lslinea, inicio, longitud), lscadena, lsreplace));
                            ELSIF (posicion = 5) THEN
                                v_cod_orig := TRIM(translate(substr(lslinea, inicio, longitud), lscadena, lsreplace));
                            ELSIF (posicion = 6) THEN
                                v_cod_stat_rech := TRIM(translate(substr(lslinea, inicio, longitud), lscadena, lsreplace));
                            END IF;

                            inicio := inicio + longitud;
                        END LOOP;

                        SELECT COUNT (*)
                            INTO lnCantidad
                          FROM flx_consu_habil_flexi
                         WHERE cod_pais = pscodigopais
                              AND cod_clie = v_cod_cons
                              AND cod_peri_fact >= v_cod_camp
                              AND ind_habi = 1
                              and ind_acti = 0;
                        
                        IF lnCantidad > 0 THEN
                        -- Actualizamos la data en la tabla --
                        -- RECHAZADAS
                            UPDATE flx_varia_calcu_model
                               SET  cod_more = v_cod_moti_rech,
                                       cod_esre = v_cod_stat_rech,
                                       vcc_indi_regz = '1',         -- RECHAZADA
                                       vcc_indi_rcgz = '0',         -- NO RECOMENDADA
                                       vcc_indi_apro = 'N',        -- NO APROBADA
                                       vcc_esta_proc = '5',       -- REHAZADA POR EL MODELO
                                       usu_modi = pscodigousuario,
                                       fec_modi = SYSDATE
                             WHERE vcc_camp_eval >= v_cod_camp 
                                  AND cod_clie = v_cod_cons;
                        
                            DELETE flx_consu_habil_flexi                            
                            WHERE cod_pais = pscodigopais
                                 AND cod_clie = v_cod_cons
                                 AND cod_peri_fact >= v_cod_camp
                                 AND ind_habi = 1;
                        END IF;
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

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_FLX_RECEP_CONSU_RECHW: ' || ls_sqlerrm);

  END INT_PR_FLX_RECEP_CONSU_RECHW;

/***************************************************************************
Descripcion       : Recepcion de Consultoras Recomendadas desde WEB (FLX-7)
Fecha Creacion    : 20/06/2013
Autor             : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_CONSU_RECOW
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigousuario VARCHAR2
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

    v_cod_pais      varchar2(3);
    v_cod_camp      varchar2(6);
    v_cod_cons      varchar2(15);
    v_cod_moti_reco varchar2(2);
    v_cod_orig      varchar2(1);
    v_cod_stat_reco varchar2(2);

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;
    lnCantidad  NUMBER := 0;

    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

  BEGIN

    -- tomamos comp campaña la maxima que existe en la tabla de hábiles
    -- para no considerar la campaña que viene en el archivo
    select max(cod_peri_comu)
    into  v_cod_camp
    from FLX_CONSU_HABIL_FLEXI where cod_pais = pscodigopais; 
    -- -- 
    
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
            i := i + 1;
            
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
                                v_cod_pais := nvl(TRIM(substr(lslinea, inicio, longitud)), pscodigopais);
                            ELSIF (posicion = 3) THEN
                                v_cod_cons := TRIM(translate(substr(lslinea, inicio, longitud), lscadena, lsreplace));
                            ELSIF (posicion = 4) THEN
                                v_cod_moti_reco := TRIM(translate(substr(lslinea, inicio, longitud), lscadena, lsreplace));
                            ELSIF (posicion = 5) THEN
                                v_cod_orig := TRIM(translate(substr(lslinea, inicio, longitud), lscadena, lsreplace));
                            ELSIF (posicion = 6) THEN
                                v_cod_stat_reco := TRIM(translate(substr(lslinea, inicio, longitud), lscadena, lsreplace));
                            END IF;

                            inicio := inicio + longitud;

                        END LOOP;
                        
                        SELECT COUNT (*)
                            INTO lnCantidad
                          FROM flx_consu_habil_flexi
                         WHERE cod_pais = pscodigopais
                              AND cod_clie  = v_cod_cons
                              AND cod_peri_fact >= v_cod_camp
                              AND ind_habi = 1;
                        
                        IF lnCantidad =  0 THEN
                            -- Validamos si la consultora viene de la campaña anterior y que en esta campaña haya salido con estado NO_PARTICIPA
                            select count(*) 
                            into lnCantidad
                            from flx_varia_calcu_model                            
                            where cod_clie = v_cod_cons
                            and CAM_CERR =  gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, v_cod_camp,-1)
                            and vcc_esta_proc = '0'; --NO PARTICIPA
                            
                            IF lnCantidad > 0 THEN
                                -- tomamos los datos de la campaña anterior, ya que en esa debió de haber estado prerechazada
                                 
                                -- Eliminamos el registro de la campaña actual
                                delete from flx_varia_calcu_model                            
                                where cod_clie = v_cod_cons
                                and CAM_CERR = gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, v_cod_camp,-1)
                                and vcc_esta_proc = '0'; --NO PARTICIPA
                                
                                -- Insertamos el registro de la ampaña anterior con la campaña actual
                                INSERT INTO FLX_VARIA_CALCU_MODEL(
                                    CAM_CERR,
                                    COD_CLIE,
                                    VPI_EDAD_CLIE,
                                    VPI_CODI_REGI,
                                    VPI_NUAN_BELC,
                                    VPI_STAR,
                                    VPI_PROM_VND_ULT6C,
                                    VPI_FREC_ULT18_CAMP,
                                    VPI_SALD_VENC,
                                    VPI_CARG_TOTA_CAMP,
                                    VPI_CONS_ULT6_CAMP,
                                    VPI_MAXP_ULT3_CAMP,
                                    VPI_PRSV_ULT18_CAMP,
                                    VPI_MAXP_ULT18_CAMP,
                                    VPB_PRVD_VCMN_18CA,
                                    VPB_PRVD_VCMN_ANPC,
                                    VPB_PROM_VDCP_1ERP,
                                    VPB_PROM_VDCP_2DOP,
                                    VPB_PROM_VDCP_3ERP,
                                    VLC_FREC_COP1_ANOA,
                                    VLC_FREC_COP2_ANOA,
                                    VLC_FREC_COP3_ANOA,
                                    VLC_PROM_VDVC_UL18_CAZO,
                                    VPB_PRVD_VCMN_APCZ,
                                    VPB_PVDC_P1ZO,
                                    VPB_PVDC_P2ZO,
                                    VPB_PVDC_P3ZO,
                                    VCP_COMP_PAGO,
                                    VCC_CAMP_CIER,
                                    VCC_PROB_INCU,
                                    VCC_CAMP_COMU,
                                    VCC_CAMP_EVAL,
                                    VCC_CAMP_PBLC,
                                    VCC_PEDI_BASE,
                                    VCC_LINE_CRED,
                                    VCC_ESTA_PROC,
                                    VCC_CAMP_FACT,
                                    IND_EWEB,
                                    COD_MORE,
                                    COD_MORC,
                                    COD_ESRE,
                                    COD_ESRC,
                                    USU_REGI,
                                    FEC_REGI,
                                    VCC_INDI_REGZ,
                                    VCC_INDI_RCGZ,
                                    VCC_INDI_APRO,
                                    VCC_ESTA_PREA,
                                    VCE_CODI_CALI,
                                    VPI_INDI_SEGM_NUEV,
                                    VPI_INDI_SEGM_CON1,
                                    VPI_INDI_SEGM_CON2,
                                    VPI_INDI_INCO,
                                    VPI_INDI_SEGM_TOPS,
                                    VPI_FREC_ULT9_CAMP,
                                    VPI_PROM_VNTA_ULT3_CAMP,
                                    VPI_PROM_VNTA_ULT9_CAMP,
                                    VPI_NUM_UNID_VEND,
                                    VPB_PRVD_VCMN_09CA,
                                    VPI_IND_DIAS_ATRA_0021,
                                    VPI_NUM_CAMP_DA42_ULT9_CAMP,
                                    VPI_FLG_DA168_ULT9_CAMP,
                                    VPI_VAL_ABON_ADIA,
                                    VPI_PRI_DA00_ULT3_CAMP,
                                    VPI_ULT_DA21_ULT9_CAMP,
                                    VPI_PRI_DA00_ULT9_CAMP,
                                    VPI_PDA_ULT6_CAMP,
                                    VPI_MAX_PUL3_CAMP,
                                    VPI_PDP_ULT3_CAMP,
                                    VPI_PRO_DUL3_CAMP,
                                    VPI_ULT_DA00_ULT6_CAMP,
                                    VPI_NUM_CAMP_D168_ULT9_CAMP,
                                    VPI_ULT_DA00_ULT9_CAMP)
                                SELECT
                                    gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, CAM_CERR,+1),
                                    COD_CLIE,
                                    VPI_EDAD_CLIE,
                                    VPI_CODI_REGI,
                                    VPI_NUAN_BELC,
                                    VPI_STAR,
                                    VPI_PROM_VND_ULT6C,
                                    VPI_FREC_ULT18_CAMP,
                                    VPI_SALD_VENC,
                                    VPI_CARG_TOTA_CAMP,
                                    VPI_CONS_ULT6_CAMP,
                                    VPI_MAXP_ULT3_CAMP,
                                    VPI_PRSV_ULT18_CAMP,
                                    VPI_MAXP_ULT18_CAMP,
                                    VPB_PRVD_VCMN_18CA,
                                    VPB_PRVD_VCMN_ANPC,
                                    VPB_PROM_VDCP_1ERP,
                                    VPB_PROM_VDCP_2DOP,
                                    VPB_PROM_VDCP_3ERP,
                                    VLC_FREC_COP1_ANOA,
                                    VLC_FREC_COP2_ANOA,
                                    VLC_FREC_COP3_ANOA,
                                    VLC_PROM_VDVC_UL18_CAZO,
                                    VPB_PRVD_VCMN_APCZ,
                                    VPB_PVDC_P1ZO,
                                    VPB_PVDC_P2ZO,
                                    VPB_PVDC_P3ZO,
                                    VCP_COMP_PAGO,
                                    gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, VCC_CAMP_CIER,+1),
                                    VCC_PROB_INCU,
                                    gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, VCC_CAMP_COMU,+1),
                                    gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, VCC_CAMP_EVAL,+1),
                                    gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, VCC_CAMP_PBLC,+1),
                                    VCC_PEDI_BASE,
                                    VCC_LINE_CRED,
                                    VCC_ESTA_PROC,
                                    gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, VCC_CAMP_FACT,+1),
                                    IND_EWEB,
                                    COD_MORE,
                                    COD_MORC,
                                    COD_ESRE,
                                    COD_ESRC,
                                    pscodigousuario USU_REGI,
                                    sysdate FEC_REGI,
                                    VCC_INDI_REGZ,
                                    VCC_INDI_RCGZ,
                                    VCC_INDI_APRO,
                                    VCC_ESTA_PREA,
                                    VCE_CODI_CALI,
                                    VPI_INDI_SEGM_NUEV,
                                    VPI_INDI_SEGM_CON1,
                                    VPI_INDI_SEGM_CON2,
                                    VPI_INDI_INCO,
                                    VPI_INDI_SEGM_TOPS,
                                    VPI_FREC_ULT9_CAMP,
                                    VPI_PROM_VNTA_ULT3_CAMP,
                                    VPI_PROM_VNTA_ULT9_CAMP,
                                    VPI_NUM_UNID_VEND,
                                    VPB_PRVD_VCMN_09CA,
                                    VPI_IND_DIAS_ATRA_0021,
                                    VPI_NUM_CAMP_DA42_ULT9_CAMP,
                                    VPI_FLG_DA168_ULT9_CAMP,
                                    VPI_VAL_ABON_ADIA,
                                    VPI_PRI_DA00_ULT3_CAMP,
                                    VPI_ULT_DA21_ULT9_CAMP,
                                    VPI_PRI_DA00_ULT9_CAMP,
                                    VPI_PDA_ULT6_CAMP,
                                    VPI_MAX_PUL3_CAMP,
                                    VPI_PDP_ULT3_CAMP,
                                    VPI_PRO_DUL3_CAMP,
                                    VPI_ULT_DA00_ULT6_CAMP,
                                    VPI_NUM_CAMP_D168_ULT9_CAMP,
                                    VPI_ULT_DA00_ULT9_CAMP                                
                                FROM FLX_VARIA_CALCU_MODEL
                                where cod_clie = v_cod_cons
                                and vcc_camp_eval = gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, v_cod_camp,-1);
                                                                
                            END IF;                            
                            -- -- 
                            
                        -- Actualizamos la data en la tabla --
                            UPDATE flx_varia_calcu_model
                               SET cod_morc = v_cod_moti_reco,
                                   cod_esrc = v_cod_stat_reco,
                                   vcc_indi_regz = '0',    -- NO RECHAZADA
                                   vcc_indi_rcgz = '1',     -- RECOMENDADA
                                   vcc_indi_apro = 'A',    -- APROBADA
                                   usu_modi = pscodigousuario,
                                   fec_modi = SYSDATE,
                                   cod_more = '01',
                                   cod_esre = '01'
                             WHERE vcc_camp_eval = v_cod_camp
                                  AND cod_clie = v_cod_cons;
                        
                                INSERT INTO FLX_CONSU_HABIL_FLEXI (
                                COD_PAIS,
                                COD_CLIE,
                                COD_PERI_FACT,
                                COD_PERI_COMU,
                                    VAL_LINE_CRED,
                                    VAL_PEDI_BASE,
                                    IND_CALI_COMP,
                                IND_HABI,
                                IND_ACTI,
                                USU_DIGI,
                                    FEC_DIGI,
                                    IND_CALI_EXPE_FLEX)                                
                                SELECT
                                pscodigopais,
                                    COD_CLIE,
                                    VCC_CAMP_FACT,
                                    VCC_CAMP_COMU,
                                    VCC_LINE_CRED,
                                    VCC_PEDI_BASE,
                                    VCP_COMP_PAGO,
                                    '1',
                                    '0',
                                pscodigousuario,
                                    SYSDATE,
                                    VCE_CODI_CALI                                    
                                    FROM FLX_VARIA_CALCU_MODEL
                                WHERE vcc_camp_eval = v_cod_camp
                                AND cod_clie = v_cod_cons;
                                    
                        END IF;
                        
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

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_FLX_RECEP_CONSU_RECOW: ' || ls_sqlerrm);

  END INT_PR_FLX_RECEP_CONSU_RECOW;

/***************************************************************************
Descripcion       : Envia la informacion para los procesos comerciales WEB (FLX-8
Fecha Creacion    : 20/06/2013
Autor             : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoPeriodo : Codigo de la campaña
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_ENVIO_PROCE_COMER
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigousuario VARCHAR2
  ) IS
    
    -- Recomendadas por WEB (APROBADAS) (Listado de Habiles)
    CURSOR c_recomendadas IS
        SELECT
        COD_CLIE,
        VCC_CAMP_FACT,
        VCC_CAMP_COMU,
        VCC_LINE_CRED,
        VCC_PEDI_BASE,
        VCP_COMP_PAGO,
        VCE_CODI_CALI
        FROM FLX_VARIA_CALCU_MODEL
        WHERE VCC_CAMP_EVAL = pscodigoperiodo
        AND VCC_INDI_APRO = 'A'; --APROBADAS/HABILES
    -- --
    
    --Lista de consultoras a comunicar
    CURSOR c_comunicar IS    
    SELECT 
    COD_CLIE,
    VCC_CAMP_FACT,
    VCC_CAMP_COMU
    FROM FLX_VARIA_CALCU_MODEL t
    WHERE  VCC_CAMP_EVAL = pscodigoperiodo
    AND VCC_INDI_APRO = 'A'
    AND COD_CLIE IN(
        SELECT COD_CLIE FROM FLX_CONSU_HABIL_FLEXI
            GROUP BY COD_CLIE
            HAVING COUNT(*) = 1 --solo los  que fueron registrados por primera vez
    );    
        
    TYPE recomendada IS RECORD(
      codigoCliente         FLX_VARIA_CALCU_MODEL.COD_CLIE%TYPE,
      campaniaFacturacion   FLX_VARIA_CALCU_MODEL.VCC_CAMP_FACT%TYPE,
      campaniaComunicacion  FLX_VARIA_CALCU_MODEL.VCC_CAMP_COMU%TYPE,
      lineaCredito          FLX_VARIA_CALCU_MODEL.VCC_LINE_CRED%TYPE,
      pedidoBase            FLX_VARIA_CALCU_MODEL.VCC_PEDI_BASE%TYPE,
      compromisoPago        FLX_VARIA_CALCU_MODEL.VCP_COMP_PAGO%TYPE,
      calificacionFlexipago FLX_VARIA_CALCU_MODEL.VCE_CODI_CALI%TYPE
      );

    TYPE comunicar IS RECORD(
      codigoCliente         FLX_VARIA_CALCU_MODEL.COD_CLIE%TYPE,
      campaniaFacturacion   FLX_VARIA_CALCU_MODEL.VCC_CAMP_FACT%TYPE,
      campaniaComunicacion  FLX_VARIA_CALCU_MODEL.VCC_CAMP_COMU%TYPE
      );

    TYPE recomendadaTab IS TABLE OF recomendada;
    TYPE comunicarTab IS TABLE OF comunicar;

    recomendadaRecord recomendadaTab;
    comunicarRecord comunicarTab;
    
    v_ind_acti VARCHAR2(1);
    auxcont NUMBER;
    lsnumerodocident      mae_clien_ident.num_docu_iden%TYPE;
    lsdireccion           VARCHAR2(200);
    lsunidadgeo           VARCHAR2(200);
        
    lnCantidad NUMBER;
        
  BEGIN
  
        -- TODAS LAS QUE FUERON ENVIADAS COMO PREAPROBADAS Y RETORNARON
        -- COMO CODIGOS POR DEFECTO(NO MODIFICADAS POR LA GZ), SE CONSIDERAN COMO ACEPTADAS
        UPDATE FLX_VARIA_CALCU_MODEL
        SET
        VCC_INDI_REGZ = '0', -- NO RECHAZADA
        VCC_INDI_RCGZ = '0', -- NO RECOMENDADA
        VCC_INDI_APRO = 'A', -- APROBADA POR LA GZ
        VCC_ESTA_PROC = '4', --APROBADA POR EL MODELO
        USU_MODI = pscodigousuario,
        FEC_MODI = SYSDATE
        WHERE VCC_CAMP_EVAL = pscodigoperiodo
        AND VCC_ESTA_PROC = '2'
        AND COD_MORE = '01'
        AND COD_ESRE = '01';

        -- TODAS LAS QUE FUERON ENVIADAS COMO RECOMENDADAS Y RETORNARON
        -- COMO CODIGOS POR DEFECTO(NO MODIFICADAS POR LA GZ), SE CONSIDERAN COMO RECHAZADAS

        UPDATE FLX_VARIA_CALCU_MODEL
        SET
        VCC_INDI_REGZ = '1', -- RECHAZADA
        VCC_INDI_RCGZ = '0', -- NO RECOMENDADA
        VCC_INDI_APRO = 'N', -- NO APROBADA
        VCC_ESTA_PROC = '5', -- RECHAZADA POR EL MODELO
        USU_MODI = pscodigousuario,
        FEC_MODI = SYSDATE
        WHERE VCC_CAMP_EVAL = pscodigoperiodo
        AND VCC_ESTA_PROC = '3'
        AND COD_MORC = '01'
        AND COD_ESRC = '01';
                            
        -- PROCESO DE RECOMENDADAS/HABILES --
        OPEN c_recomendadas;
        LOOP
            FETCH c_recomendadas BULK COLLECT
            INTO recomendadaRecord LIMIT w_filas;
                
                IF recomendadaRecord.count > 0 THEN
                
                    FOR x IN recomendadaRecord.FIRST .. recomendadaRecord.LAST 
                    LOOP

                        -- Generamos la info para los procesos comerciales --
                            
                        -- Ver si la consultora tiene un registro anterior y verificar su estado [Activa/Inactiva]
                        begin
                            select f.ind_acti
                            into v_ind_acti
                            from flx_consu_habil_flexi f
                            where f.cod_pais = pscodigopais
                            and f.cod_clie = recomendadaRecord(x).codigoCliente
                            AND f.cod_peri_fact = (select max(ff.cod_peri_fact)
                                                        from flx_consu_habil_flexi ff
                                                        where ff.cod_pais = f.cod_pais
                                                        and ff.cod_clie = f.cod_clie);
                        exception
                                when no_data_found then
                                    v_ind_acti := '0';
                        end;
                            
                        INSERT INTO FLX_CONSU_HABIL_FLEXI (
                            COD_PAIS,
                            COD_CLIE,
                            COD_PERI_FACT,
                            COD_PERI_COMU,
                            VAL_LINE_CRED,
                            VAL_PEDI_BASE,
                            IND_CALI_COMP,
                            IND_ACTI,
                            USU_DIGI,
                                FEC_DIGI,
                                IND_CALI_EXPE_FLEX)
                        VALUES (
                            pscodigopais,
                            recomendadaRecord(x).codigoCliente,
                            recomendadaRecord(x).campaniaFacturacion,
                            recomendadaRecord(x).campaniaComunicacion,
                            recomendadaRecord(x).lineaCredito,
                            recomendadaRecord(x).pedidoBase,
                            recomendadaRecord(x).compromisoPago,
                            v_ind_acti,
                            pscodigousuario,
                                SYSDATE,
                                recomendadaRecord(x).calificacionFlexipago);
                            -- -- 
                    END LOOP;
                
                END IF;           
            
            EXIT WHEN c_recomendadas%NOTFOUND;
        END LOOP;
        CLOSE c_recomendadas;
        -- --
  
        -- PROCESO DE RECOMENDADAS/HABILES --
        OPEN c_comunicar;
        LOOP
            FETCH c_comunicar BULK COLLECT
            INTO comunicarRecord LIMIT w_filas;
                
                IF comunicarRecord.count > 0 THEN
                
                    FOR x IN comunicarRecord.FIRST .. comunicarRecord.LAST 
                    LOOP

                        /* Evalua si la consultora se encuentra activa en la campaña actual */
                        select count(1) 
                        into auxcont
                        from flx_consu_habil_flexi fc
                        where fc.cod_peri_fact in (select bf.cod_peri 
                                                        from bas_ctrl_fact bf 
                                                        where bf.ind_camp_act = '1' 
                                                        and bf.sta_camp = '0')
                        and fc.cod_clie = comunicarRecord(x).codigoCliente
                        and fc.ind_habi = '1'
                        and fc.ind_acti = '1';
                    
                        /* Solo se registran si no estan activas en la campaña actual */
                        IF auxcont = 0 THEN
                            
                           /* Se inserta el archivo a la tabla */
                           INSERT INTO FLX_CONSU_COMUN_FLEXI(
                                COD_PAIS,
                                COD_CLIE,
                                COD_PERI_FACT,
                                COD_PERI_COMU,
                                USU_DIGI,
                                FEC_DIGI)
                                 VALUES(
                                pscodigopais,
                                comunicarRecord(x).codigoCliente,
                                comunicarRecord(x).campaniaFacturacion,
                                comunicarRecord(x).campaniaComunicacion,
                                pscodigousuario,
                                SYSDATE);

                            select count(1)
                            into auxcont
                            from mae_clien m
                            where m.cod_clie = comunicarRecord(x).codigoCliente;

                            IF auxcont != 0 THEN
                                                        
                                /* Si el codigo de consultora es valido, se inserta los mensajes en el buzon*/

                                /* Elimino los mensajes de flexipago para la consultora, en caso tuviera */
                                delete from msg_buzon_mensa m
                                where m.clie_oid_clie = (SELECT oid_clie FROM mae_clien WHERE cod_clie = comunicarRecord(x).codigoCliente)
                                and mens_oid_mens in (SELECT oid_mens FROM msg_mensa WHERE cod_mens like 'FPG0%');

                                /* Obtiene el DNI de la consultora */
                                begin
                                    select ci.num_docu_iden
                                    into lsnumerodocident
                                    from mae_clien_ident ci , mae_clien m
                                    where ci.val_iden_docu_prin = '1'
                                    and ci.clie_oid_clie = m.oid_clie
                                    and m.cod_clie = comunicarRecord(x).codigoCliente
                                    and rownum = 1 ;
                                exception
                                    when no_data_found then
                                        lsnumerodocident := null;
                                end;
                                
                                /* Obtiene la direccion y la unidad geografica */
                                begin
                                  SELECT substr(DES_ABRV_TIPO_VIA || ' ' || VAL_NOMB_VIA || ' ' || NUM_PPAL || ' ' || VAL_OBSE,0,200),
                                       substr(nivel_1
                                         || DECODE (nivel_2, NULL, '', '/' || nivel_2)
                                         || DECODE (nivel_3, NULL, '', '/' || nivel_3)
                                         || DECODE (nivel_4, NULL, '', '/' || nivel_4)
                                         || DECODE (nivel_5, NULL, '', '/' || nivel_5)
                                         || DECODE (nivel_6, NULL, '', '/' || nivel_6)
                                         || DECODE (nivel_7, NULL, '', '/' || nivel_7)
                                         || DECODE (nivel_8, NULL, '', '/' || nivel_8)
                                         || DECODE (nivel_9, NULL, '', '/' || nivel_9),0,200) AS DESC_UNI
                                  INTO lsdireccion, lsunidadgeo
                                    FROM (SELECT   a.OID_CLIE_DIRE OID,
                                                   c.DES_ABRV_TIPO_VIA,
                                                   a.VAL_NOMB_VIA,
                                                   a.NUM_PPAL,
                                                   a.VAL_OBSE,
                                                   a.VAL_BARR,
                                                   t.COD_TERR,
                                                   (SELECT des_geog
                                                      FROM zon_valor_estru_geopo
                                                     WHERE pais_oid_pais = d.pais_oid_pais
                                                       AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                       AND orde_2 IS NULL) AS nivel_1,
                                                   (SELECT des_geog
                                                      FROM zon_valor_estru_geopo
                                                     WHERE pais_oid_pais = d.pais_oid_pais
                                                       AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                       AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                       AND orde_3 IS NULL) AS nivel_2,
                                                   (SELECT des_geog
                                                      FROM zon_valor_estru_geopo
                                                     WHERE pais_oid_pais = d.pais_oid_pais
                                                       AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                       AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                       AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                       AND orde_4 IS NULL) AS nivel_3,
                                                   CASE
                                                      WHEN LENGTH (a.cod_unid_geog) > 18
                                                         THEN (SELECT des_geog
                                                                 FROM zon_valor_estru_geopo
                                                                WHERE pais_oid_pais =
                                                                                     d.pais_oid_pais
                                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                                  AND orde_5 IS NULL)
                                                      ELSE NULL
                                                   END AS nivel_4,
                                                   CASE
                                                      WHEN LENGTH (a.cod_unid_geog) > 24
                                                         THEN (SELECT des_geog
                                                                 FROM zon_valor_estru_geopo
                                                                WHERE pais_oid_pais =
                                                                                     d.pais_oid_pais
                                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                                  AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                                                  AND orde_6 IS NULL)
                                                      ELSE NULL
                                                   END AS nivel_5,
                                                   CASE
                                                      WHEN LENGTH (a.cod_unid_geog) > 30
                                                         THEN (SELECT des_geog
                                                                 FROM zon_valor_estru_geopo
                                                                WHERE pais_oid_pais =
                                                                                     d.pais_oid_pais
                                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                                  AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                                                  AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                                                  AND orde_7 IS NULL)
                                                      ELSE NULL
                                                   END AS nivel_6,
                                                   CASE
                                                      WHEN LENGTH (a.cod_unid_geog) > 36
                                                         THEN (SELECT des_geog
                                                                 FROM zon_valor_estru_geopo
                                                                WHERE pais_oid_pais =
                                                                                     d.pais_oid_pais
                                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                                  AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                                                  AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                                                  AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
                                                                  AND orde_8 IS NULL)
                                                      ELSE NULL
                                                   END AS nivel_7,
                                                   CASE
                                                      WHEN LENGTH (a.cod_unid_geog) > 42
                                                         THEN (SELECT des_geog
                                                                 FROM zon_valor_estru_geopo
                                                                WHERE pais_oid_pais =
                                                                                     d.pais_oid_pais
                                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                                  AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                                                  AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                                                  AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
                                                                  AND orde_8 = SUBSTR (a.cod_unid_geog, 43, 6)
                                                                  AND orde_9 IS NULL)
                                                      ELSE NULL
                                                   END AS nivel_8,
                                                   CASE
                                                      WHEN LENGTH (a.cod_unid_geog) > 48
                                                         THEN (SELECT des_geog
                                                                 FROM zon_valor_estru_geopo
                                                                WHERE pais_oid_pais =
                                                                                     d.pais_oid_pais
                                                                  AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                                                  AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                                                  AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                                                  AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                                                  AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                                                  AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                                                  AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
                                                                  AND orde_8 = SUBSTR (a.cod_unid_geog, 43, 6)
                                                                  AND orde_9 = SUBSTR (a.cod_unid_geog, 49, 6))
                                                      ELSE NULL
                                                   END AS nivel_9
                                              FROM MAE_CLIEN_DIREC a,
                                                   MAE_TIPO_DIREC b,
                                                   SEG_TIPO_VIA c,
                                                   MAE_CLIEN d,
                                                   ZON_TERRI t,
                                                   mae_clien m
                                             WHERE d.OID_CLIE = m.oid_clie
                                               and m.cod_clie = comunicarRecord(x).codigoCliente
                                               AND d.OID_CLIE = a.CLIE_OID_CLIE
                                               AND a.IND_ELIM = 0
                                               AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                                               AND c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
                                               AND a.IND_DIRE_PPAL  = 1
                                               AND a.TERR_OID_TERR = t.OID_TERR (+)
                                          ORDER BY a.OID_CLIE_DIRE DESC)
                                   WHERE ROWNUM = 1;
                                exception
                                  when no_data_found then
                                    lsdireccion := null;
                                    lsunidadgeo := null;
                                end;
                                
                                
                                IF nvl(gen_pkg_gener.gen_fn_param_pais(pscodigopais,pscodigosistema,'002'),'0') = '1' THEN
                                                                
                                    -- Mensaje flexipago 2
                                    INSERT INTO msg_buzon_mensa
                                        (oid_buzo_mens,
                                        num_secu,
                                        ind_esta_mens,
                                        clie_oid_clie,
                                        DATO_VARI_02,
                                        DATO_VARI_03,
                                        DATO_VARI_04,
                                        DATO_VARI_05,
                                        mens_oid_mens,
                                        modu_oid_modu_orig,
                                        fec_grab,
                                        ind_list_cons,
                                        ind_acti)
                                    VALUES
                                        (msg_bume_seq.nextval,
                                        msg_bum2_seq.nextval,
                                        1,
                                        (SELECT oid_clie FROM mae_clien WHERE cod_clie = comunicarRecord(x).codigoCliente),
                                        (select substr(RTRIM(m.val_nom1 || ' ' || m.val_nom2 /*|| ' ' || m.val_ape1 || ' ' || m.val_ape2*/),1,200)
                                            from mae_clien m
                                            where m.cod_clie = comunicarRecord(x).codigoCliente),
                                        lsnumerodocident,
                                        lsdireccion     ,
                                        lsunidadgeo     ,
                                        (SELECT oid_mens FROM msg_mensa WHERE cod_mens = 'FPG02'),
                                        1,
                                        SYSDATE,
                                        0,
                                        1);
                                
                                END IF;
                            
                            END IF;
                        
                        END IF;
                        
                    END LOOP;
                
                END IF;           
            
            EXIT WHEN c_comunicar%NOTFOUND;
        END LOOP;
        CLOSE c_comunicar;
        -- --
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_FLX_ENVIO_PROCE_COMER: ' || ls_sqlerrm);

  END INT_PR_FLX_ENVIO_PROCE_COMER;
 
/***************************************************************************
Descripcion       : Envia la informacion de los resusltados del programa
Fecha Creacion    : 20/06/2013
Autor             : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema    : Codigo del Sistema
 psCodigoPeriodo : Codigo de la campaña
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_ENVIO_REPRO
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoUsuario        VARCHAR2)
IS
   CURSOR c_interfaz(cvVersion VARCHAR2) IS
   SELECT 
   CAM_CERR,
   COD_CLIE,
   VCC_CAMP_COMU,
   VCC_CAMP_FACT,
   TRIM(TO_CHAR(VCC_LINE_CRED, '999999999999999999.999999')) LC ,
   TRIM(TO_CHAR(VCC_PEDI_BASE, '999999999999999999.999999')) PB ,
   TRIM(TO_CHAR(VCC_PROB_INCU, '999999999999999999.999999')) PI ,
   DECODE(VCC_ESTA_PREA, '4', '1', '0') VCC_ESTA_PROC, --PREAPROBACION
   cvVersion,
   NVL(VCC_INDI_REGZ, '0') VCC_INDI_REGZ, --INDICADORRECHAZO GZ
   NVL(VCC_INDI_RCGZ, '0') VCC_INDI_RCGZ, --INDICADOR RECOMENDACION GZ
   VCC_INDI_APRO  --INDICADOR DE APROBACION      
   FROM FLX_VARIA_CALCU_MODEL
   WHERE CAM_CERR =  gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, psCodigoPeriodo,-1)
   AND VCC_ESTA_PROC IN ('3', '4', '5');

   TYPE interfazRec IS RECORD
    (
       campania             FLX_VARIA_CALCU_MODEL.CAM_CERR%type,
       codigoConsultora     FLX_VARIA_CALCU_MODEL.COD_CLIE%type,
       campaniaComunicacion FLX_VARIA_CALCU_MODEL.VCC_CAMP_COMU%type,
       campaniaFacturacion  FLX_VARIA_CALCU_MODEL.VCC_CAMP_FACT%type,
       lineaCredito         VARCHAR2(25),
       pedidoBase           VARCHAR2(25),
       probabilidadInc      VARCHAR2(25),
       indicadorPreAprobada FLX_VARIA_CALCU_MODEL.VCC_ESTA_PROC%type, --PREAPROBACION
       versionModelo        FLX_PARAM.VAL_PARA%type,
       indicadorRechazoGZ   FLX_VARIA_CALCU_MODEL.VCC_INDI_REGZ%type, --INDICADORRECHAZO GZ
       indicadorRecomenGZ   FLX_VARIA_CALCU_MODEL.VCC_INDI_RCGZ%type, --INDICADOR RECOMENDACION GZ
       indicadorAprobada    FLX_VARIA_CALCU_MODEL.VCC_INDI_APRO%type  --INDICADOR DE APROBACION      
    );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;
   
   lsVersion    FLX_PARAM.VAL_PARA%TYPE;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    SELECT VAL_PARA
    INTO  lsVersion
    FROM FLX_PARAM WHERE COD_GRUP = '06' AND COD_PARA = '001';
    
    OPEN c_interfaz(lsVersion);
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
                          lsLinea :=  interfazRecord(x).campania                ||';'||
                                      interfazRecord(x).codigoConsultora        ||';'||
                                      interfazRecord(x).campaniaComunicacion    ||';'||
                                      interfazRecord(x).campaniaFacturacion     ||';'||
                                      interfazRecord(x).lineaCredito            ||';'||
                                      interfazRecord(x).pedidoBase              ||';'||
                                      interfazRecord(x).probabilidadInc         ||';'||
                                      interfazRecord(x).indicadorPreAprobada    ||';'||
                                      interfazRecord(x).versionModelo           ||';'||
                                      interfazRecord(x).indicadorRechazoGZ      ||';'||
                                      interfazRecord(x).indicadorRecomenGZ      ||';'||
                                      interfazRecord(x).indicadorAprobada;

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FLX_ENVIO_REPRO: '||ls_sqlerrm);

END INT_PR_FLX_ENVIO_REPRO;
 
/***************************************************************************
Descripcion       : Recepcion de variables del modelo
Fecha Creacion    : 20/06/2013
Autor             : Ivan Tocto
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_VARIA_MODEL
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psCodigoPeriodo  VARCHAR2,
    pscodigousuario VARCHAR2
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

    TYPE	t_CAM_CERR	IS TABLE OF	FLX_VARIA_CALCU_MODEL.CAM_CERR%TYPE;
    TYPE	t_COD_CLIE	IS TABLE OF	FLX_VARIA_CALCU_MODEL.COD_CLIE%TYPE;
    TYPE	t_VPI_EDAD_CLIE	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_EDAD_CLIE%TYPE;
    TYPE	t_VPI_CODI_REGI	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_CODI_REGI%TYPE;
    TYPE	t_VPI_NUAN_BELC	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_NUAN_BELC%TYPE;
    TYPE	t_VPI_STAR	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_STAR%TYPE;
    TYPE	t_VPI_PROM_VND_ULT6C	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_PROM_VND_ULT6C%TYPE;
    TYPE	t_VPI_FREC_ULT18_CAMP	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_FREC_ULT18_CAMP%TYPE;
    TYPE	t_VPI_SALD_VENC	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_SALD_VENC%TYPE;
    TYPE	t_VPI_CARG_TOTA_CAMP	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_CARG_TOTA_CAMP%TYPE;
    TYPE	t_VPI_CONS_ULT6_CAMP	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_CONS_ULT6_CAMP%TYPE;
    TYPE	t_VPI_MAXP_ULT3_CAMP	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_MAXP_ULT3_CAMP%TYPE;
    TYPE	t_VPI_PRSV_ULT18_CAMP	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_PRSV_ULT18_CAMP%TYPE;
    TYPE	t_VPI_MAXP_ULT18_CAMP	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPI_MAXP_ULT18_CAMP%TYPE;
    TYPE	t_VPB_PRVD_VCMN_18CA	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPB_PRVD_VCMN_18CA%TYPE;
    TYPE	t_VPB_PRVD_VCMN_ANPC	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPB_PRVD_VCMN_ANPC%TYPE;
    TYPE	t_VPB_PROM_VDCP_1ERP	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPB_PROM_VDCP_1ERP%TYPE;
    TYPE	t_VPB_PROM_VDCP_2DOP	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPB_PROM_VDCP_2DOP%TYPE;
    TYPE	t_VPB_PROM_VDCP_3ERP	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPB_PROM_VDCP_3ERP%TYPE;
    TYPE	t_VLC_FREC_COP1_ANOA	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VLC_FREC_COP1_ANOA%TYPE;
    TYPE	t_VLC_FREC_COP2_ANOA	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VLC_FREC_COP2_ANOA%TYPE;
    TYPE	t_VLC_FREC_COP3_ANOA	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VLC_FREC_COP3_ANOA%TYPE;
    TYPE	t_VLC_PROM_VDVC_UL18_CAZO	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VLC_PROM_VDVC_UL18_CAZO%TYPE;
    TYPE	t_VPB_PRVD_VCMN_APCZ	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPB_PRVD_VCMN_APCZ%TYPE;
    TYPE	t_VPB_PVDC_P1ZO	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPB_PVDC_P1ZO%TYPE;
    TYPE	t_VPB_PVDC_P2ZO	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPB_PVDC_P2ZO%TYPE;
    TYPE	t_VPB_PVDC_P3ZO	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VPB_PVDC_P3ZO%TYPE;
    TYPE	t_VCP_COMP_PAGO	IS TABLE OF	FLX_VARIA_CALCU_MODEL.VCP_COMP_PAGO%TYPE;
    TYPE    t_VCE_CODI_CALI    IS TABLE OF    FLX_VARIA_CALCU_MODEL.VCE_CODI_CALI%TYPE;

    v_CAM_CERR	t_CAM_CERR	 :=  t_CAM_CERR();
    v_COD_CLIE	t_COD_CLIE	 :=  t_COD_CLIE();
    v_VPI_EDAD_CLIE	t_VPI_EDAD_CLIE	 :=  t_VPI_EDAD_CLIE();
    v_VPI_CODI_REGI	t_VPI_CODI_REGI	 :=  t_VPI_CODI_REGI();
    v_VPI_NUAN_BELC	t_VPI_NUAN_BELC	 :=  t_VPI_NUAN_BELC();
    v_VPI_STAR	t_VPI_STAR	 :=  t_VPI_STAR();
    v_VPI_PROM_VND_ULT6C	t_VPI_PROM_VND_ULT6C	 :=  t_VPI_PROM_VND_ULT6C();
    v_VPI_FREC_ULT18_CAMP	t_VPI_FREC_ULT18_CAMP	 :=  t_VPI_FREC_ULT18_CAMP();
    v_VPI_SALD_VENC	t_VPI_SALD_VENC	 :=  t_VPI_SALD_VENC();
    v_VPI_CARG_TOTA_CAMP	t_VPI_CARG_TOTA_CAMP	 :=  t_VPI_CARG_TOTA_CAMP();
    v_VPI_CONS_ULT6_CAMP	t_VPI_CONS_ULT6_CAMP	 :=  t_VPI_CONS_ULT6_CAMP();
    v_VPI_MAXP_ULT3_CAMP	t_VPI_MAXP_ULT3_CAMP	 :=  t_VPI_MAXP_ULT3_CAMP();
    v_VPI_PRSV_ULT18_CAMP	t_VPI_PRSV_ULT18_CAMP	 :=  t_VPI_PRSV_ULT18_CAMP();
    v_VPI_MAXP_ULT18_CAMP	t_VPI_MAXP_ULT18_CAMP	 :=  t_VPI_MAXP_ULT18_CAMP();
    v_VPB_PRVD_VCMN_18CA	t_VPB_PRVD_VCMN_18CA	 :=  t_VPB_PRVD_VCMN_18CA();
    v_VPB_PRVD_VCMN_ANPC	t_VPB_PRVD_VCMN_ANPC	 :=  t_VPB_PRVD_VCMN_ANPC();
    v_VPB_PROM_VDCP_1ERP	t_VPB_PROM_VDCP_1ERP	 :=  t_VPB_PROM_VDCP_1ERP();
    v_VPB_PROM_VDCP_2DOP	t_VPB_PROM_VDCP_2DOP	 :=  t_VPB_PROM_VDCP_2DOP();
    v_VPB_PROM_VDCP_3ERP	t_VPB_PROM_VDCP_3ERP	 :=  t_VPB_PROM_VDCP_3ERP();
    v_VLC_FREC_COP1_ANOA	t_VLC_FREC_COP1_ANOA	 :=  t_VLC_FREC_COP1_ANOA();
    v_VLC_FREC_COP2_ANOA	t_VLC_FREC_COP2_ANOA	 :=  t_VLC_FREC_COP2_ANOA();
    v_VLC_FREC_COP3_ANOA	t_VLC_FREC_COP3_ANOA	 :=  t_VLC_FREC_COP3_ANOA();
    v_VLC_PROM_VDVC_UL18_CAZO	t_VLC_PROM_VDVC_UL18_CAZO	 :=  t_VLC_PROM_VDVC_UL18_CAZO();
    v_VPB_PRVD_VCMN_APCZ	t_VPB_PRVD_VCMN_APCZ	 :=  t_VPB_PRVD_VCMN_APCZ();
    v_VPB_PVDC_P1ZO	t_VPB_PVDC_P1ZO	 :=  t_VPB_PVDC_P1ZO();
    v_VPB_PVDC_P2ZO	t_VPB_PVDC_P2ZO	 :=  t_VPB_PVDC_P2ZO();
    v_VPB_PVDC_P3ZO	t_VPB_PVDC_P3ZO	 :=  t_VPB_PVDC_P3ZO();
    v_VCP_COMP_PAGO	t_VCP_COMP_PAGO	 :=  t_VCP_COMP_PAGO();
    v_VCE_CODI_CALI    t_VCE_CODI_CALI     :=  t_VCE_CODI_CALI();
    
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;

    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

    lnCantidad NUMBER;

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
                    v_CAM_CERR.extend;
                    v_CAM_CERR(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                    v_COD_CLIE.extend;
                    v_COD_CLIE(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 3) THEN
                  v_VPI_EDAD_CLIE.extend;
                  v_VPI_EDAD_CLIE(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)));
                ELSIF (posicion = 4) THEN
                  v_VPI_CODI_REGI.extend;
                  v_VPI_CODI_REGI(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 5) THEN
                  v_VPI_NUAN_BELC.extend;
                  v_VPI_NUAN_BELC(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)));
                ELSIF (posicion = 6) THEN
                  v_VPI_STAR.extend;
                  v_VPI_STAR(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 7) THEN
                  v_VPI_PROM_VND_ULT6C.extend;
                  v_VPI_PROM_VND_ULT6C(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '999999999999.999999');
                ELSIF (posicion = 8) THEN
                  v_VPI_FREC_ULT18_CAMP.extend;
                  v_VPI_FREC_ULT18_CAMP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)));
                ELSIF (posicion = 9) THEN
                  v_VPI_SALD_VENC.extend;
                  v_VPI_SALD_VENC(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');
                ELSIF (posicion = 10) THEN
                  v_VPI_CARG_TOTA_CAMP.extend;
                  v_VPI_CARG_TOTA_CAMP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '999999999999.999999');
                ELSIF (posicion = 11) THEN
                  v_VPI_CONS_ULT6_CAMP.extend;
                  v_VPI_CONS_ULT6_CAMP(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 12) THEN
                  v_VPI_MAXP_ULT3_CAMP.extend;
                  v_VPI_MAXP_ULT3_CAMP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)));
                ELSIF (posicion = 13) THEN
                  v_VPI_PRSV_ULT18_CAMP.extend;
                  v_VPI_PRSV_ULT18_CAMP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');
                ELSIF (posicion = 14) THEN
                  v_VPI_MAXP_ULT18_CAMP.extend;
                  v_VPI_MAXP_ULT18_CAMP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)));
                ELSIF (posicion = 15) THEN
                  v_VPB_PRVD_VCMN_18CA.extend;
                  v_VPB_PRVD_VCMN_18CA(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');

                ELSIF (posicion = 16) THEN
                  v_VPB_PRVD_VCMN_ANPC.extend;
                  v_VPB_PRVD_VCMN_ANPC(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');

                ELSIF (posicion = 17) THEN
                  v_VPB_PROM_VDCP_1ERP.extend;
                  v_VPB_PROM_VDCP_1ERP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');
                ELSIF (posicion = 18) THEN
                  v_VPB_PROM_VDCP_2DOP.extend;
                  v_VPB_PROM_VDCP_2DOP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');
                ELSIF (posicion = 19) THEN
                  v_VPB_PROM_VDCP_3ERP.extend;
                  v_VPB_PROM_VDCP_3ERP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');

                ELSIF (posicion = 20) THEN
                  v_VLC_FREC_COP1_ANOA.extend;
                  v_VLC_FREC_COP1_ANOA(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)));

                ELSIF (posicion = 21) THEN
                  v_VLC_FREC_COP2_ANOA.extend;
                  v_VLC_FREC_COP2_ANOA(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)));

                ELSIF (posicion = 22) THEN
                  v_VLC_FREC_COP3_ANOA.extend;
                  v_VLC_FREC_COP3_ANOA(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)));

                ELSIF (posicion = 23) THEN
                  v_VLC_PROM_VDVC_UL18_CAZO.extend;
                  v_VLC_PROM_VDVC_UL18_CAZO(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');

                ELSIF (posicion = 24) THEN
                  v_VPB_PRVD_VCMN_APCZ.extend;
                  v_VPB_PRVD_VCMN_APCZ(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');

                ELSIF (posicion = 25) THEN
                  v_VPB_PVDC_P1ZO.extend;
                  v_VPB_PVDC_P1ZO(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');

                ELSIF (posicion = 26) THEN
                  v_VPB_PVDC_P2ZO.extend;
                  v_VPB_PVDC_P2ZO(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');

                ELSIF (posicion = 27) THEN
                  v_VPB_PVDC_P3ZO.extend;
                  v_VPB_PVDC_P3ZO(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999999999999.999999');

                ELSIF (posicion = 28) THEN
                  v_VCP_COMP_PAGO.extend;
                  v_VCP_COMP_PAGO(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 29) THEN
                  v_VCE_CODI_CALI.extend;
                  v_VCE_CODI_CALI(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                                                       
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

    DELETE FROM INT_FLX_VARIA_CALCU_MODEL 
    WHERE CAM_CERR = psCodigoPeriodo 
    AND USU_REGI = pscodigousuario;
    
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_CAM_CERR.count
    
      INSERT INTO INT_FLX_VARIA_CALCU_MODEL(
        USU_REGI,
        CAM_CERR,
        COD_CLIE,
        VPI_EDAD_CLIE,
        VPI_CODI_REGI,
        VPI_NUAN_BELC,
        VPI_STAR,
        VPI_PROM_VND_ULT6C,
        VPI_FREC_ULT18_CAMP,
        VPI_SALD_VENC,
        VPI_CARG_TOTA_CAMP,
        VPI_CONS_ULT6_CAMP,
        VPI_MAXP_ULT3_CAMP,
        VPI_PRSV_ULT18_CAMP,
        VPI_MAXP_ULT18_CAMP,
        VPB_PRVD_VCMN_18CA,
        VPB_PRVD_VCMN_ANPC,
        VPB_PROM_VDCP_1ERP,
        VPB_PROM_VDCP_2DOP,
        VPB_PROM_VDCP_3ERP,
        VLC_FREC_COP1_ANOA,
        VLC_FREC_COP2_ANOA,
        VLC_FREC_COP3_ANOA,
        VLC_PROM_VDVC_UL18_CAZO,
        VPB_PRVD_VCMN_APCZ,
        VPB_PVDC_P1ZO,
        VPB_PVDC_P2ZO,
        VPB_PVDC_P3ZO,
        VCP_COMP_PAGO,
        VCE_CODI_CALI)
      VALUES
        (
        pscodigousuario,
        v_CAM_CERR(i),
        v_COD_CLIE(i),
        v_VPI_EDAD_CLIE(i),
        v_VPI_CODI_REGI(i),
        v_VPI_NUAN_BELC(i),
        v_VPI_STAR(i),
        v_VPI_PROM_VND_ULT6C(i),
        v_VPI_FREC_ULT18_CAMP(i),
        v_VPI_SALD_VENC(i),
        v_VPI_CARG_TOTA_CAMP(i),
        v_VPI_CONS_ULT6_CAMP(i),
        v_VPI_MAXP_ULT3_CAMP(i),
        v_VPI_PRSV_ULT18_CAMP(i),
        v_VPI_MAXP_ULT18_CAMP(i),
        v_VPB_PRVD_VCMN_18CA(i),
        v_VPB_PRVD_VCMN_ANPC(i),
        v_VPB_PROM_VDCP_1ERP(i),
        v_VPB_PROM_VDCP_2DOP(i),
        v_VPB_PROM_VDCP_3ERP(i),
        v_VLC_FREC_COP1_ANOA(i),
        v_VLC_FREC_COP2_ANOA(i),
        v_VLC_FREC_COP3_ANOA(i),
        v_VLC_PROM_VDVC_UL18_CAZO(i),
        v_VPB_PRVD_VCMN_APCZ(i),
        v_VPB_PVDC_P1ZO(i),
        v_VPB_PVDC_P2ZO(i),
        v_VPB_PVDC_P3ZO(i),
        v_VCP_COMP_PAGO(i),
        v_VCE_CODI_CALI(i));
        
      -- ENVIAMOS DE LA TABLA TEMPORAL A LA TABLA FINAL
      -- COMO NO HAY PROCESO DE NADA SOLO ENVIAMOS TODA LA DATA
      DELETE FROM FLX_VARIA_CALCU_MODEL WHERE CAM_CERR = psCodigoPeriodo;
        
      -- BORRAMOS DUPLICIDAD DE DATOS EN BASE A CAMPAÑA(CAM_CERR) Y CODIGO CLIENTE(COD_CLIE)
      DELETE FROM INT_FLX_VARIA_CALCU_MODEL a
           WHERE a.ROWID >
                    ANY (SELECT b.ROWID
                           FROM INT_FLX_VARIA_CALCU_MODEL b
                          WHERE  A.CAM_CERR = B.CAM_CERR
                                AND A.COD_CLIE = B.COD_CLIE
                                AND A.CAM_CERR = psCodigoPeriodo
                                AND A.USU_REGI = pscodigousuario);
                          
        INSERT INTO FLX_VARIA_CALCU_MODEL(
        CAM_CERR,
        COD_CLIE,
        VPI_EDAD_CLIE,
        VPI_CODI_REGI,
        VPI_NUAN_BELC,
        VPI_STAR,
        VPI_PROM_VND_ULT6C,
        VPI_FREC_ULT18_CAMP,
        VPI_SALD_VENC,
        VPI_CARG_TOTA_CAMP,
        VPI_CONS_ULT6_CAMP,
        VPI_MAXP_ULT3_CAMP,
        VPI_PRSV_ULT18_CAMP,
        VPI_MAXP_ULT18_CAMP,
        VPB_PRVD_VCMN_18CA,
        VPB_PRVD_VCMN_ANPC,
        VPB_PROM_VDCP_1ERP,
        VPB_PROM_VDCP_2DOP,
        VPB_PROM_VDCP_3ERP,
        VLC_FREC_COP1_ANOA,
        VLC_FREC_COP2_ANOA,
        VLC_FREC_COP3_ANOA,
        VLC_PROM_VDVC_UL18_CAZO,
        VPB_PRVD_VCMN_APCZ,
        VPB_PVDC_P1ZO,
        VPB_PVDC_P2ZO,
        VPB_PVDC_P3ZO,
        VCP_COMP_PAGO,
          IND_EWEB,
          USU_REGI,
          FEC_REGI,
          VCE_CODI_CALI)
        SELECT
        CAM_CERR,
        COD_CLIE,
        VPI_EDAD_CLIE,
        VPI_CODI_REGI,
        VPI_NUAN_BELC,
        VPI_STAR,
        VPI_PROM_VND_ULT6C,
        VPI_FREC_ULT18_CAMP,
        VPI_SALD_VENC,
        VPI_CARG_TOTA_CAMP,
        VPI_CONS_ULT6_CAMP,
        VPI_MAXP_ULT3_CAMP,
        VPI_PRSV_ULT18_CAMP,
        VPI_MAXP_ULT18_CAMP,
        VPB_PRVD_VCMN_18CA,
        VPB_PRVD_VCMN_ANPC,
        VPB_PROM_VDCP_1ERP,
        VPB_PROM_VDCP_2DOP,
        VPB_PROM_VDCP_3ERP,
        VLC_FREC_COP1_ANOA,
        VLC_FREC_COP2_ANOA,
        VLC_FREC_COP3_ANOA,
        VLC_PROM_VDVC_UL18_CAZO,
        VPB_PRVD_VCMN_APCZ,
        VPB_PVDC_P1ZO,
        VPB_PVDC_P2ZO,
        VPB_PVDC_P3ZO,
        VCP_COMP_PAGO,
          '0' IND_EWEB,
          pscodigousuario USU_REGI,
          SYSDATE FEC_REGI,
          VCE_CODI_CALI
        FROM INT_FLX_VARIA_CALCU_MODEL
        WHERE CAM_CERR = psCodigoPeriodo
             AND USU_REGI = pscodigousuario;
        
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_FLX_RECEP_VARIA_MODEL: ' || ls_sqlerrm);

  END INT_PR_FLX_RECEP_VARIA_MODEL;

-----------------------------------------
-- reemplaza a la FLX-4
-----------------------------------------
PROCEDURE INT_PR_FLX_ENVIO_RESUL_PROGR
  (psCodigoPais           VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2)
IS
   CURSOR c_interfaz IS
   
   select h.cod_peri_fact,
       cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact, -1) peri_ref1,
       cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact, -2) peri_ref2,
           h.cod_clie,
       nvl( flx_pkg_proce.FLX_FN_FLAG_USO_FLEXI_CAMPA(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                            -1),
                                                         h.cod_clie),
               '0') flag_uso_flx, -- Flag Uso Flexipago                                                                                                                                                                    
       nvl( flx_pkg_proce.FLX_FN_MONTO_FINAN_CATAL_CAMPA(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                               -1),
                                                            h.cod_clie),
               '0') monto_venta_catal_finan, -- Monto Venta Catalogo Financiado                                                                                                                                         
       nvl( flx_pkg_proce.FLX_FN_MONTO_FINAN_FACTU_CAMPA(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                               -1),
                                                            h.cod_clie),
               '0') monto_venta_factu_finan, -- Monto Venta Factura Financiado                                                                                                                                          
       (SELECT SUM(NVL(mcc.imp_movi, 0))
          FROM ccc_movim_cuent_corri mcc
         WHERE mcc.clie_oid_clie =
               FIN_PKG_GENER.FIN_FN_OBTIE_OID_CLIEN(h.cod_clie)
           AND mcc.perd_oid_peri =
               FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(h.cod_pais,
                                                    FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(h.cod_peri_fact,
                                                                                           -1))
           AND mcc.subp_oid_subp_crea NOT IN (203, 204, 2023)
           AND mcc.imp_movi > 0) -
       (select SUM(NVL(de.val_mont_cuot_flex, 0))
          from flx_cuota_flexi_factu_detal de
         where de.cod_peri =
               FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(h.cod_peri_fact, -1)
           and de.cod_clie = h.cod_clie) pedido_menos_financiado, --Total Pedido menos Monto Financiado
       nvl( flx_pkg_proce.FLX_FN_MONTO_CUOTA_FLEXI(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                 -1),
                                                              h.cod_peri_fact,
                                                      h.cod_clie),
               '0') cuota_ref1, --Cuota Flexipago a pagar en la campaña en proceso sobre el pedido de la campaña de referencia 1                                                                                                  
       nvl( flx_pkg_proce.FLX_FN_MONTO_INTER_FLEXI(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                 -1),
                                                              h.cod_peri_fact,
                                                      h.cod_clie),
               '0') interes_ref1, --Interés por el financiamiento de la campaña de referencia 1                                                                                                                                   
       nvl( flx_pkg_proce.FLX_FN_MONTO_CUOTA_FLEXI(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                         -1),
                                                              cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                         1),
                                                      h.cod_clie),
               '0') cuota_ref2, --Cuota Flexipago a pagar en la campaña en proceso sobre el pedido de la campaña de referencia 2                                                                                                  
       /* nvl( flx_pkg_proce.FLX_FN_MONTO_INTER_FLEXI(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                         -1),
                                              cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                         1),
                                                      h.cod_clie),
       '0') */
       (select NVL(ff.val_mont_inte_fle2_proy, 0)
          from flx_gener_finan_consu_flexi ff
         where ff.cod_clie = h.cod_clie
           and ff.cod_peri = h.cod_peri_fact
           and ff.cod_moti_rech is null) interes_ref2, --Interés por el financiamiento de la campaña de referencia 2
       /* nvl( flx_pkg_proce.FLX_FN_SALDO_CUOTA_FLEXI(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                 -2),
                                              h.cod_peri_fact,
                                                      h.cod_clie),
       '0') saldo_cuota_flx_ref2,*/ --Cuota Flexipago pendiente de pago, que se debe pagar en la campaña de proceso sobre el pedido de la campaña de referencia 2
       (SELECT nvl(fd.val_sald_cuot_flex, 0)
          FROM flx_cuota_flexi_factu_detal fd
         WHERE fd.cod_peri =
               cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact, -2)
           AND fd.oid_peri_cuot_flex =
               FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(h.cod_peri_fact)
           AND fd.cod_clie = h.cod_clie
           AND to_date(fd.fec_fact, 'dd/mm/yyyy') =
               (SELECT to_date(min(fd.fec_fact), 'dd/mm/yyyy')
                  FROM flx_cuota_flexi_factu_detal fd
                 WHERE fd.cod_peri =
               cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                          -2)
           AND fd.oid_peri_cuot_flex =
               FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(h.cod_peri_fact)
                   AND fd.cod_clie = h.cod_clie)
        ) saldo_cuota_flx_ref2,
       nvl( flx_pkg_proce.FLX_FN_MONTO_INTER_FLEXI(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                 -2),
                                                              h.cod_peri_fact,
                                                      h.cod_clie),
               '0') interes_finan_flx_ref2, --Interés por el Financiamiento de la campaña de referencia 2                                                                                                                         
           nvl((select max(cc.ind_admi_cart)
                 from ped_histo_solic_conso_cabec cc
                where cc.cod_peri = h.cod_peri_fact
                  and cc.cod_clie = h.cod_clie),
               '0') levan_deuda, -- indicador de levantamiento                                                                                                                                                                                               
       /* flx_pkg_proce.FLX_FN_FACTO_CONVE_CAMPA(h.cod_peri_fact,
       h.cod_clie)*/
       (select ff.val_porc_conv_pedi
          from flx_gener_finan_consu_flexi ff
         where ff.cod_clie = h.cod_clie
           and ff.cod_peri = h.cod_peri_fact
           and ff.cod_moti_rech is null) factor_pedido, --Factor Pedido
       --flx_pkg_proce.FLX_FN_FLAG_PREPA_CAMPA(psCodigoPeriodo, h.cod_clie) flag_prepago -- Flag Prepago
       case
         when (nvl( flx_pkg_proce.FLX_FN_MONTO_CUOTA_FLEXI(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                                 -2),
                                                                      h.cod_peri_fact,
                                                                      h.cod_clie),
                   '0') > nvl( flx_pkg_proce.FLX_FN_SALDO_CUOTA_FLEXI(cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(h.cod_peri_fact,
                                                                                                                             -2),
                                                                                  h.cod_peri_fact,
                                                                                  h.cod_clie),
                               '0')) then
          1
         else
          0
       end flag_prepago -- Flag Prepago
      from flx_consu_habil_flexi h
 where h.cod_peri_fact = psCodigoPeriodo
   and exists
 (select *
          from flx_gener_finan_consu_flexi ff
         where ff.cod_moti_rech is null
           and ff.cod_clie = h.cod_clie
           and ff.cod_peri between
               FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(h.cod_peri_fact, -4) and
               h.cod_peri_fact);

   TYPE interfazRec IS RECORD
       (
        codigoPeriodo            VARCHAR2(6),
        periodoRef1              VARCHAR2(6),
        periodoRef2              VARCHAR2(6),
        codigoCliente            flx_consu_habil_flexi.cod_clie%type,
        flagUsoFlx               VARCHAR2(1),
        montoVentaCatalFinan     NUMBER,
        montoVentaFactuFinan     VARCHAR2(50),
        pedidoMenosFinanciado    NUMBER,
        cuotaRef1                NUMBER,
        interesRef1              NUMBER,
        cuotaRef2                NUMBER,
        interesRef2              NUMBER,                                                                                                                              
        saldoCuotaFlxRef2        NUMBER,
        interesFinanFlxRef2      NUMBER,                                                                                                
        levanDeuda               ped_histo_solic_conso_cabec.ind_admi_cart%type,
        factorPedido             NUMBER,   
        flagPrepago              VARCHAR2(10)          
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
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
                          lsLinea :=  interfazRecord(x).codigoPeriodo        ||';'||
                                      interfazRecord(x).periodoRef1          ||';'||
                                      interfazRecord(x).periodoRef2          ||';'||
                                      interfazRecord(x).codigoCliente        ||';'||
                                      interfazRecord(x).flagUsoFlx           ||';'||
                                      interfazRecord(x).montoVentaCatalFinan ||';'||
                                      interfazRecord(x).montoVentaFactuFinan ||';'||
                                      interfazRecord(x).pedidoMenosFinanciado||';'||
                                      interfazRecord(x).cuotaRef1            ||';'||
                                      interfazRecord(x).interesRef1          ||';'||
                                      interfazRecord(x).cuotaRef2            ||';'||
                                      interfazRecord(x).interesRef2          ||';'||
                                      interfazRecord(x).saldoCuotaFlxRef2    ||';'||
                                      interfazRecord(x).interesFinanFlxRef2  ||';'||
                                      interfazRecord(x).levanDeuda           ||';'||
                                      interfazRecord(x).factorPedido         ||';'||
                                      interfazRecord(x).flagPrepago          ;

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FLX_ENVIO_RESUL_PROGR: '||ls_sqlerrm);

END INT_PR_FLX_ENVIO_RESUL_PROGR;
  
-----------------------------------------
-- reemplaza a la FLX-5
-----------------------------------------
PROCEDURE INT_PR_FLX_ENVIO_INFOR_HABIL
  (psCodigoPais           VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2)
IS
   CURSOR c_interfaz IS
        select psCodigoPeriodo ,
               h.cod_clie ,
               decode(h.ind_acti,
                      '1',
                      'N',
                      '0',
                      (decode((select count(1)
                                from flx_audit_consu_habil a
                               where a.cod_acci = '01'
                                 and a. cod_clie = h.cod_clie),
                              0,
                              'I',
                              'C'))),
               cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa((select min(f.cod_peri_comu)
                                                            from flx_consu_habil_flexi f
                                                           where f.cod_clie =
                                                                 h.cod_clie),
                                                          '-1') ,
               (select min(f.cod_peri_fact)
                  from flx_consu_habil_flexi f
                 where f.cod_clie = h.cod_clie
                   and f.ind_acti = '1') ,
               (select max(ch.cod_peri_fact)
                  from Flx_Audit_Consu_Habil ch
                 where ch.cod_acci = '01'
                   and ch.cod_clie = h.cod_clie
                  and not exists (select null 
                                     from flx_consu_habil_flexi fff 
                                    where fff.cod_clie = h.cod_clie 
                                      and fff.cod_peri_fact >= h.cod_peri_fact 
                                      and fff.ind_acti = '1')) camp_cancela
          from flx_consu_habil_flexi h
         where h.cod_peri_fact = psCodigoPeriodo;

   TYPE interfazRec IS RECORD
       (
        codigoPeriodo        VARCHAR2(6),
        codigoConsultora     flx_consu_habil_flexi.cod_clie%TYPE, 
        estatus              VARCHAR2(1),
        campAprobacion       VARCHAR2(6),
        campInscripcion      VARCHAR2(6),
        campCancela          VARCHAR2(6)       
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
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
                          lsLinea :=  interfazRecord(x).codigoPeriodo     ||';'|| 
                                      interfazRecord(x).codigoConsultora  ||';'|| 
                                      interfazRecord(x).estatus           ||';'|| 
                                      interfazRecord(x).campAprobacion    ||';'|| 
                                      interfazRecord(x).campInscripcion   ||';'|| 
                                      interfazRecord(x).campCancela       ;

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FLX_ENVIO_INFOR_HABIL: '||ls_sqlerrm);

END INT_PR_FLX_ENVIO_INFOR_HABIL;

/***********************************************************************************
Descripcion               : Envia la informacion de las variables calculadas en cuentas corrientes a Datamart
Fecha Creacion        : 26/11/2013
Autor                        : Sebastian Guerra
Parametros:
 psCodigoPais           : Codigo de Pais
 psCodigoSistema    : Codigo del Sistema
 psCodigoPeriodo    : Codigo de la campaña
 psCodigoUsuario    : Nombre de Usuario
***********************************************************************************/
  PROCEDURE INT_PR_FLX_ENVIO_VARIA_CTACTE
  (psCodigoPais           VARCHAR2,
   psCodigoSistema      VARCHAR2,
   psCodigoInterfaz     VARCHAR2,
   psNombreArchivo     VARCHAR2,
   psCodigoPeriodo       VARCHAR2,
   psCodigoUsuario       VARCHAR2)
IS
   CURSOR c_interfaz IS   
   select 
       gen_pkg_gener.gen_fn_devuelve_cod_clie(oid_clie) cod_clie,
       fec_carg,
       fec_venc,
       decode(val_carg, 0, null, val_sald_venc) val_sald_venc,
       val_sald_xven,
       decode(val_carg, 0, null, val_sald) val_sald,
       decode(val_carg, 0, null, val_carg) val_carg,
       decode(val_carg, 0, null, val_abon_venc) val_abon_venc,
       decode(val_carg, 0, null, val_abon_adia) val_abon_adia,
       decode(val_carg, 0, null, val_abon) val_abon,
       fec_pago,
       decode(val_carg, 0, null, ind_marc_carg) ind_marc_carg,
       decode(val_carg, 0, null, num_dias_atra) num_dias_atra,
       ind_dias_atra_0001,
       ind_dias_atra_0021,
       ind_dias_atra_0042,
       ind_dias_atra_0063,
       ind_dias_atra_0105,
       ind_dias_atra_0168,
       ult_da01_ult9_camp,
       ult_da21_ult9_camp,
       ult_da42_ult9_camp,
       ult_da63_ult9_camp,
       ult_da105_ult9_camp,
       ult_da168_ult9_camp,
       flg_da01_ult9_camp,
       flg_da21_ult9_camp,
       flg_da42_ult9_camp,
       flg_da63_ult9_camp,
       flg_da105_ult9_camp,
       flg_da168_ult9_camp,
       decode(val_carg, 0, null, num_dias_pago) num_dias_pago,
       pdp_ult3_camp,
       pdp_ult6_camp,
       pdp_ult9_camp,
       pda_ult3_camp,
       pda_ult6_camp,
       pda_ult9_camp,
       max_pul3_camp,
       max_pul6_camp,
       max_pul9_camp,
       max_aul3_camp,
       max_aul6_camp,
       max_aul9_camp,
       pro_dul3_camp,
       pro_dul6_camp,
       pro_dul9_camp,
       (select a.cod_peri
          from seg_perio_corpo a, cra_perio b
         where b.oid_peri = pri_da00_ult3_camp and a.oid_peri = b.peri_oid_peri) pri_da00_ult3_camp,
       (select a.cod_peri
          from seg_perio_corpo a, cra_perio b
         where b.oid_peri = pri_da00_ult6_camp and a.oid_peri = b.peri_oid_peri) pri_da00_ult6_camp,
       (select a.cod_peri
          from seg_perio_corpo a, cra_perio b
         where b.oid_peri = pri_da00_ult9_camp and a.oid_peri = b.peri_oid_peri) pri_da00_ult9_camp,
       (select a.cod_peri
          from seg_perio_corpo a, cra_perio b
         where b.oid_peri = ult_da00_ult3_camp and a.oid_peri = b.peri_oid_peri) ult_da00_ult3_camp,
       (select a.cod_peri
          from seg_perio_corpo a, cra_perio b
         where b.oid_peri = ult_da00_ult6_camp and a.oid_peri = b.peri_oid_peri) ult_da00_ult6_camp,
       (select a.cod_peri
          from seg_perio_corpo a, cra_perio b
         where b.oid_peri = ult_da00_ult9_camp and a.oid_peri = b.peri_oid_peri) ult_da00_ult9_camp,
       num_camp_da01_ult9_camp,
       num_camp_da21_ult9_camp,
       num_camp_da42_ult9_camp,
       num_camp_da63_ult9_camp,
       num_camp_d105_ult9_camp,
       num_camp_d168_ult9_camp,
       cam_cier
   from flx_varia_cuent_corri;

   TYPE interfazVCC IS RECORD
   (
    cod_clie                                               mae_clien.cod_clie%type, 
    fechaEmision                                      flx_varia_cuent_corri.fec_carg%type,
    fechaVencimiento                                  flx_varia_cuent_corri.fec_venc%type,
    saldoVencidoCampania                              flx_varia_cuent_corri.val_sald_venc%type,
    saldoPorVencer                                    flx_varia_cuent_corri.val_sald_xven%type,
    saldoTotal                                        flx_varia_cuent_corri.val_sald%type,
    cargoTotalCampania                                flx_varia_cuent_corri.val_carg%type,
    abonoVencido                                      flx_varia_cuent_corri.val_abon_venc%type,
    abonoDia                                          flx_varia_cuent_corri.val_abon_adia%type,
    abonoTotal                                        flx_varia_cuent_corri.val_abon%type,
    fechaCancelacionDeuda                             flx_varia_cuent_corri.fec_pago%type,
    indicadorCanceloDeuda                             flx_varia_cuent_corri.ind_marc_carg%type,
    diasAtraso                                        flx_varia_cuent_corri.num_dias_atra%type,
    indicadorDiasAtraso1                              flx_varia_cuent_corri.ind_dias_atra_0001%type,
    indicadorDiasAtraso21                             flx_varia_cuent_corri.ind_dias_atra_0021%type,
    indicadorDiasAtraso42                             flx_varia_cuent_corri.ind_dias_atra_0042%type,
    indicadorDiasAtraso63                             flx_varia_cuent_corri.ind_dias_atra_0063%type,
    indicadorDiasAtraso105                            flx_varia_cuent_corri.ind_dias_atra_0105%type,
    indicadorDiasAtraso168                            flx_varia_cuent_corri.ind_dias_atra_0168%type,
    numCampaniaRecienDA1Ultima9                         flx_varia_cuent_corri.ult_da01_ult9_camp%type,
    numCampaniaRecienDA21Ultima9                       flx_varia_cuent_corri.ult_da21_ult9_camp%type,
    numCampaniaRecienDA42Ultima9                       flx_varia_cuent_corri.ult_da42_ult9_camp%type,
    numCampaniaRecienDA63Ultima9                       flx_varia_cuent_corri.ult_da63_ult9_camp%type,
    numCampaniaRecienDA105Ultima9                     flx_varia_cuent_corri.ult_da105_ult9_camp%type,
    numCampaniaRecienDA168Ultima9                     flx_varia_cuent_corri.ult_da168_ult9_camp%type,
    flagCampaniaRecienDA1Ultima9               flx_varia_cuent_corri.flg_da01_ult9_camp%type,
    flagCampaniaRecienDA21Ultima9              flx_varia_cuent_corri.flg_da21_ult9_camp%type,
    flagCampaniaRecienDA42Ultima9              flx_varia_cuent_corri.flg_da42_ult9_camp%type,
    flagCampaniaRecienDA63Ultima9              flx_varia_cuent_corri.flg_da63_ult9_camp%type,
    flagCampaniaRecienDA105Ultima9             flx_varia_cuent_corri.flg_da105_ult9_camp%type,
    flagCampaniaRecienDA168Ultima9             flx_varia_cuent_corri.flg_da168_ult9_camp%type,
    diasPago                                          flx_varia_cuent_corri.num_dias_pago%type,
    promedioDiaPagoUltima3                            flx_varia_cuent_corri.pdp_ult3_camp%type,
    promedioDiaPagoUltima6                            flx_varia_cuent_corri.pdp_ult6_camp%type,
    promedioDiaPagoUltima9                            flx_varia_cuent_corri.pdp_ult9_camp%type,
    promedioDiaAtrasoUltima3                          flx_varia_cuent_corri.pda_ult3_camp%type,
    promedioDiaAtrasoUltima6                          flx_varia_cuent_corri.pda_ult6_camp%type,
    promedioDiaAtrasoUltima9                          flx_varia_cuent_corri.pda_ult9_camp%type,
    maximoDiaPagoUltima3                              flx_varia_cuent_corri.max_pul3_camp%type,
    maximoDiaPagoUltima6                              flx_varia_cuent_corri.max_pul6_camp%type,
    maximoDiaPagoUltima9                              flx_varia_cuent_corri.max_pul9_camp%type,
    maximoDiaAtraso3                                  flx_varia_cuent_corri.max_aul3_camp%type,
    maximoDiaAtraso6                                  flx_varia_cuent_corri.max_aul6_camp%type,
    maximoDiaAtraso9                                  flx_varia_cuent_corri.max_aul9_camp%type,
    promedioSaldoVencidoUltima3                       flx_varia_cuent_corri.pro_dul3_camp%type,
    promedioSaldoVencidoUltima6                       flx_varia_cuent_corri.pro_dul6_camp%type,
    promedioSaldoVencidoUltima9                       flx_varia_cuent_corri.pro_dul9_camp%type,
    campaniaMasAntiguaUltima3                         varchar2(6),
    campaniaMasAntiguaUltima6                         varchar2(6),
    campaniaMasAntiguaUltima9                         varchar2(6),
    campaniaMasRecienteUltima3                        varchar2(6),
    campaniaMasRecienteUltima6                        varchar2(6),
    campaniaMasRecienteUltima9                        varchar2(6),
    numeroCampaniaDAMenor0                    flx_varia_cuent_corri.num_camp_da01_ult9_camp%type,
    numeroCampaniaDAMenor21                   flx_varia_cuent_corri.num_camp_da21_ult9_camp%type,
    numeroCampaniaDAMenor42                   flx_varia_cuent_corri.num_camp_da42_ult9_camp%type,
    numeroCampaniaDAMenor63                   flx_varia_cuent_corri.num_camp_da63_ult9_camp%type,
    numeroCampaniaDAMenor105                  flx_varia_cuent_corri.num_camp_d105_ult9_camp%type,
    numeroCampaniaDAMenor168                  flx_varia_cuent_corri.num_camp_d168_ult9_camp%type,
    cam_cier                                                flx_varia_cuent_corri.cam_cier%type
   );

   TYPE interfazVCCTab  IS TABLE OF interfazVCC ;
   interfazRecord interfazVCCTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo              bas_inter.dir_temp%type;
   v_handle                 utl_file.file_type;
   
   lsLinea                     varchar2(4000);
   lsNombreArchivo     varchar2(50);
   lbAbrirUtlFile           boolean;
   lsCampaniaEvaluacion     varchar(6);

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz;
        LOOP
             FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, v_handle);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                        lsLinea :=  substr(pscodigopais, 0, 2) ||';'|| interfazRecord(x).cam_cier ||';'||interfazRecord(x).cod_clie 
                        ||';'||to_char(interfazRecord(x).fechaEmision,'YYYYMMDD')
                        ||';'||to_char(interfazRecord(x).fechaVencimiento,'YYYYMMDD')
                        ||';'||trim(to_char(interfazRecord(x).saldoVencidoCampania,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).saldoPorVencer,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).saldoTotal,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).cargoTotalCampania,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).abonoVencido,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).abonoDia,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).abonoTotal,'999999999999.999'))
                        ||';'||to_char(interfazRecord(x).fechaCancelacionDeuda,'YYYYMMDD')
                        ||';'||trim(to_char(interfazRecord(x).indicadorCanceloDeuda,'9'))
                        ||';'||trim(to_char(interfazRecord(x).diasAtraso,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).indicadorDiasAtraso1,'9'))
                        ||';'||trim(to_char(interfazRecord(x).indicadorDiasAtraso21,'9'))
                        ||';'||trim(to_char(interfazRecord(x).indicadorDiasAtraso42,'9'))
                        ||';'||trim(to_char(interfazRecord(x).indicadorDiasAtraso63,'9'))
                        ||';'||trim(to_char(interfazRecord(x).indicadorDiasAtraso105,'9'))
                        ||';'||trim(to_char(interfazRecord(x).indicadorDiasAtraso168,'9'))
                        ||';'||trim(to_char(interfazRecord(x).numCampaniaRecienDA1Ultima9,'9999'))
                        ||';'||trim(to_char(interfazRecord(x).numCampaniaRecienDA21Ultima9,'9999'))
                        ||';'||trim(to_char(interfazRecord(x).numCampaniaRecienDA42Ultima9,'9999'))
                        ||';'||trim(to_char(interfazRecord(x).numCampaniaRecienDA63Ultima9,'9999'))
                        ||';'||trim(to_char(interfazRecord(x).numCampaniaRecienDA105Ultima9,'9999'))
                        ||';'||trim(to_char(interfazRecord(x).numCampaniaRecienDA168Ultima9,'9999'))
                        ||';'||trim(to_char(interfazRecord(x).flagCampaniaRecienDA1Ultima9,'9'))
                        ||';'||trim(to_char(interfazRecord(x).flagCampaniaRecienDA21Ultima9,'9'))
                        ||';'||trim(to_char(interfazRecord(x).flagCampaniaRecienDA42Ultima9,'9'))
                        ||';'||trim(to_char(interfazRecord(x).flagCampaniaRecienDA63Ultima9,'9'))
                        ||';'||trim(to_char(interfazRecord(x).flagCampaniaRecienDA105Ultima9,'9'))
                        ||';'||trim(to_char(interfazRecord(x).flagCampaniaRecienDA168Ultima9,'9'))
                        ||';'||trim(to_char(interfazRecord(x).diasPago,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).promedioDiaPagoUltima3,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).promedioDiaPagoUltima6,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).promedioDiaPagoUltima9,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).promedioDiaAtrasoUltima3,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).promedioDiaAtrasoUltima6,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).promedioDiaAtrasoUltima9,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).maximoDiaPagoUltima3,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).maximoDiaPagoUltima6,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).maximoDiaPagoUltima9,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).maximoDiaAtraso3,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).maximoDiaAtraso6,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).maximoDiaAtraso9,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).promedioSaldoVencidoUltima3,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).promedioSaldoVencidoUltima6,'999999999999.999'))
                        ||';'||trim(to_char(interfazRecord(x).promedioSaldoVencidoUltima9,'999999999999.999'))
                        ||';'||interfazRecord(x).campaniaMasAntiguaUltima3
                        ||';'||interfazRecord(x).campaniaMasAntiguaUltima6
                        ||';'||interfazRecord(x).campaniaMasAntiguaUltima9
                        ||';'||interfazRecord(x).campaniaMasRecienteUltima3
                        ||';'||interfazRecord(x).campaniaMasRecienteUltima6
                        ||';'||interfazRecord(x).campaniaMasRecienteUltima9
                        ||';'||trim(to_char(interfazRecord(x).numeroCampaniaDAMenor0,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).numeroCampaniaDAMenor21,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).numeroCampaniaDAMenor42,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).numeroCampaniaDAMenor63,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).numeroCampaniaDAMenor105,'999999999999'))
                        ||';'||trim(to_char(interfazRecord(x).numeroCampaniaDAMenor168,'999999999999'));

                       utl_file.put_line (v_handle, lslinea );
                       
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
       ln_sqlcode := sqlcode;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_FLX_ENVIO_VARIA_CTACTE: '||ls_sqlerrm);

END INT_PR_FLX_ENVIO_VARIA_CTACTE;

/***************************************************************************
Descripcion         : Recepcion de variables de datamart
Fecha Creacion  : 05/12/2013
Autor                  : Sebastian Guerra
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Archivo
 psCodigoPeriodo  : Codigo de Periodo
 psCodigoUsuario : Nombre de Usuario
***************************************************************************/
  PROCEDURE INT_PR_FLX_RECEP_VARIA_DATAM
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigousuario VARCHAR2
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
      idcampo      bas_estru_archi.ide_camp%TYPE,
      sigla            bas_tipo_dato.sig_tdat%TYPE);

    TYPE interfazcabtab IS TABLE OF interfazcab;
    interfazrecord interfazcabtab;

    TYPE   t_CAM_PAGO  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.CAM_PAGO%TYPE;
    TYPE   t_COD_CLIE  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.COD_CLIE%TYPE;
    TYPE   t_VPI_EDAD_CLIE  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_EDAD_CLIE%TYPE;
    TYPE   t_VPI_CODI_REGI  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_CODI_REGI%TYPE;
    TYPE   t_VPI_NUAN_BELC  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_NUAN_BELC%TYPE;
    TYPE   t_VPI_INDI_SEGM_NUEV  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_INDI_SEGM_NUEV%TYPE;
    TYPE   t_VPI_INDI_SEGM_CON1  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_INDI_SEGM_CON1%TYPE;
    TYPE   t_VPI_INDI_SEGM_CON2  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_INDI_SEGM_CON2%TYPE;
    TYPE   t_VPI_INDI_INCO  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_INDI_INCO%TYPE;
    TYPE   t_VPI_INDI_SEGM_TOPS  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_INDI_SEGM_TOPS%TYPE;
    TYPE   t_VPI_FREC_ULT9_CAMP  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_FREC_ULT9_CAMP%TYPE;
    TYPE   t_VPI_PROM_VNTA_ULT3_CAMP  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_PROM_VNTA_ULT3_CAMP%TYPE;
    TYPE   t_VPI_PROM_VNTA_ULT9_CAMP  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_PROM_VNTA_ULT9_CAMP%TYPE;
    TYPE   t_VPI_PROM_VNTA_ULT6_CAMP  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_PROM_VNTA_ULT6_CAMP%TYPE;
    TYPE   t_VPI_CONS_ULT6_CAMP  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_CONS_ULT6_CAMP%TYPE;
    TYPE   t_VPI_NUM_UNID_VEND  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_NUM_UNID_VEND%TYPE;
    TYPE   t_VPB_PRVD_VCMN_18CA  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPB_PRVD_VCMN_18CA%TYPE;
    TYPE   t_VPB_PRVD_VCMN_09CA  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPB_PRVD_VCMN_09CA%TYPE;
    TYPE   t_VPB_PRVD_VCMN_ANPC  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPB_PRVD_VCMN_ANPC%TYPE;
    TYPE   t_VPB_PROM_VDCP_1ERP  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPB_PROM_VDCP_1ERP%TYPE;
    TYPE   t_VPB_PROM_VDCP_2DOP  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPB_PROM_VDCP_2DOP%TYPE;
    TYPE   t_VPB_PROM_VDCP_3ERP  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPB_PROM_VDCP_3ERP%TYPE;
    TYPE   t_VLC_FREC_COP1_ANOA  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VLC_FREC_COP1_ANOA%TYPE;
    TYPE   t_VLC_FREC_COP2_ANOA  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VLC_FREC_COP2_ANOA%TYPE;
    TYPE   t_VLC_FREC_COP3_ANOA  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VLC_FREC_COP3_ANOA%TYPE;
    TYPE   t_VPB_PRVD_VCMN_APCZ  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPB_PRVD_VCMN_APCZ%TYPE;
    TYPE   t_VPB_PVDC_P1ZO  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPB_PVDC_P1ZO%TYPE;
    TYPE   t_VPB_PVDC_P2ZO  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPB_PVDC_P2ZO%TYPE;
    TYPE   t_VPB_PVDC_P3ZO  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPB_PVDC_P3ZO%TYPE;
    TYPE   t_VCP_COMP_PAGO  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VCP_COMP_PAGO%TYPE;
    TYPE   t_VCE_CODI_CALI  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VCE_CODI_CALI%TYPE;
    TYPE   t_VPI_CONS_3ULT_CAMP  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_CONS_3ULT_CAMP%TYPE;
    TYPE   t_VPI_INDI_SEGU  IS TABLE OF  INT_FLX_TEMPO_CALCU_MODEL.VPI_INDI_SEGU%TYPE;

    v_CAM_PAGO  t_CAM_PAGO  :=    t_CAM_PAGO();
    v_COD_CLIE  t_COD_CLIE  :=    t_COD_CLIE();
    v_VPI_EDAD_CLIE  t_VPI_EDAD_CLIE  :=    t_VPI_EDAD_CLIE();
    v_VPI_CODI_REGI  t_VPI_CODI_REGI  :=    t_VPI_CODI_REGI();
    v_VPI_NUAN_BELC  t_VPI_NUAN_BELC  :=    t_VPI_NUAN_BELC();
    v_VPI_INDI_SEGM_NUEV  t_VPI_INDI_SEGM_NUEV  :=    t_VPI_INDI_SEGM_NUEV();
    v_VPI_INDI_SEGM_CON1  t_VPI_INDI_SEGM_CON1  :=    t_VPI_INDI_SEGM_CON1();
    v_VPI_INDI_SEGM_CON2  t_VPI_INDI_SEGM_CON2  :=    t_VPI_INDI_SEGM_CON2();
    v_VPI_INDI_INCO  t_VPI_INDI_INCO  :=    t_VPI_INDI_INCO();
    v_VPI_INDI_SEGM_TOPS  t_VPI_INDI_SEGM_TOPS  :=    t_VPI_INDI_SEGM_TOPS();
    v_VPI_FREC_ULT9_CAMP  t_VPI_FREC_ULT9_CAMP  :=    t_VPI_FREC_ULT9_CAMP();
    v_VPI_PROM_VNTA_ULT3_CAMP  t_VPI_PROM_VNTA_ULT3_CAMP  :=    t_VPI_PROM_VNTA_ULT3_CAMP();
    v_VPI_PROM_VNTA_ULT9_CAMP  t_VPI_PROM_VNTA_ULT9_CAMP  :=    t_VPI_PROM_VNTA_ULT9_CAMP();
    v_VPI_PROM_VNTA_ULT6_CAMP  t_VPI_PROM_VNTA_ULT6_CAMP  :=    t_VPI_PROM_VNTA_ULT6_CAMP();
    v_VPI_CONS_ULT6_CAMP  t_VPI_CONS_ULT6_CAMP  :=    t_VPI_CONS_ULT6_CAMP();
    v_VPI_NUM_UNID_VEND  t_VPI_NUM_UNID_VEND  :=    t_VPI_NUM_UNID_VEND();
    v_VPB_PRVD_VCMN_18CA  t_VPB_PRVD_VCMN_18CA  :=    t_VPB_PRVD_VCMN_18CA();
    v_VPB_PRVD_VCMN_09CA  t_VPB_PRVD_VCMN_09CA  :=    t_VPB_PRVD_VCMN_09CA();
    v_VPB_PRVD_VCMN_ANPC  t_VPB_PRVD_VCMN_ANPC  :=    t_VPB_PRVD_VCMN_ANPC();
    v_VPB_PROM_VDCP_1ERP  t_VPB_PROM_VDCP_1ERP  :=    t_VPB_PROM_VDCP_1ERP();
    v_VPB_PROM_VDCP_2DOP  t_VPB_PROM_VDCP_2DOP  :=    t_VPB_PROM_VDCP_2DOP();
    v_VPB_PROM_VDCP_3ERP  t_VPB_PROM_VDCP_3ERP  :=    t_VPB_PROM_VDCP_3ERP();
    v_VLC_FREC_COP1_ANOA  t_VLC_FREC_COP1_ANOA  :=    t_VLC_FREC_COP1_ANOA();
    v_VLC_FREC_COP2_ANOA  t_VLC_FREC_COP2_ANOA  :=    t_VLC_FREC_COP2_ANOA();
    v_VLC_FREC_COP3_ANOA  t_VLC_FREC_COP3_ANOA  :=    t_VLC_FREC_COP3_ANOA();
    v_VPB_PRVD_VCMN_APCZ  t_VPB_PRVD_VCMN_APCZ  :=    t_VPB_PRVD_VCMN_APCZ();
    v_VPB_PVDC_P1ZO  t_VPB_PVDC_P1ZO  :=    t_VPB_PVDC_P1ZO();
    v_VPB_PVDC_P2ZO  t_VPB_PVDC_P2ZO  :=    t_VPB_PVDC_P2ZO();
    v_VPB_PVDC_P3ZO  t_VPB_PVDC_P3ZO  :=    t_VPB_PVDC_P3ZO();
    v_VCP_COMP_PAGO  t_VCP_COMP_PAGO  :=    t_VCP_COMP_PAGO();
    v_VCE_CODI_CALI  t_VCE_CODI_CALI  :=    t_VCE_CODI_CALI();
    v_VPI_CONS_3ULT_CAMP  t_VPI_CONS_3ULT_CAMP  :=    t_VPI_CONS_3ULT_CAMP();
    v_VPI_INDI_SEGU  t_VPI_INDI_SEGU  :=    t_VPI_INDI_SEGU();

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i  BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;

    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

    lnCantidad NUMBER;
    sqlQuery    VARCHAR2(100);

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
                    v_CAM_PAGO.extend;
                    v_CAM_PAGO(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 2) THEN
                    v_COD_CLIE.extend;
                    v_COD_CLIE(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 3) THEN
                  v_VPI_EDAD_CLIE.extend;
                  v_VPI_EDAD_CLIE(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99');
                ELSIF (posicion = 4) THEN
                  v_VPI_CODI_REGI.extend;
                  v_VPI_CODI_REGI(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 5) THEN
                  v_VPI_NUAN_BELC.extend;
                  v_VPI_NUAN_BELC(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '999');
                ELSIF (posicion = 6) THEN
                  v_VPI_INDI_SEGM_NUEV.extend;
                  v_VPI_INDI_SEGM_NUEV(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '9');
                ELSIF (posicion = 7) THEN
                  v_VPI_INDI_SEGM_CON1.extend;
                  v_VPI_INDI_SEGM_CON1(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '9');
                ELSIF (posicion = 8) THEN
                  v_VPI_INDI_SEGM_CON2.extend;
                  v_VPI_INDI_SEGM_CON2(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '9');
                ELSIF (posicion = 9) THEN
                  v_VPI_INDI_INCO.extend;
                  v_VPI_INDI_INCO(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '9');
                ELSIF (posicion = 10) THEN
                  v_VPI_INDI_SEGM_TOPS.extend;
                  v_VPI_INDI_SEGM_TOPS(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '9');
                ELSIF (posicion = 11) THEN
                  v_VPI_FREC_ULT9_CAMP.extend;
                  v_VPI_FREC_ULT9_CAMP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99');
                ELSIF (posicion = 12) THEN
                  v_VPI_PROM_VNTA_ULT3_CAMP.extend;
                  v_VPI_PROM_VNTA_ULT3_CAMP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 13) THEN
                  v_VPI_PROM_VNTA_ULT9_CAMP.extend;
                  v_VPI_PROM_VNTA_ULT9_CAMP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                                                  
                ELSIF (posicion = 14) THEN
                  v_VPI_PROM_VNTA_ULT6_CAMP.extend;
                  v_VPI_PROM_VNTA_ULT6_CAMP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 15) THEN
                  v_VPI_CONS_ULT6_CAMP.extend;
                  v_VPI_CONS_ULT6_CAMP(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 16) THEN
                  v_VPI_NUM_UNID_VEND.extend;
                  v_VPI_NUM_UNID_VEND(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '99999');
                      
                ELSIF (posicion = 17) THEN
                  v_VPB_PRVD_VCMN_18CA.extend;
                  v_VPB_PRVD_VCMN_18CA(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 18) THEN
                  v_VPB_PRVD_VCMN_09CA.extend;
                  v_VPB_PRVD_VCMN_09CA(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 19) THEN
                  v_VPB_PRVD_VCMN_ANPC.extend;
                  v_VPB_PRVD_VCMN_ANPC(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 20) THEN
                  v_VPB_PROM_VDCP_1ERP.extend;
                  v_VPB_PROM_VDCP_1ERP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 21) THEN
                  v_VPB_PROM_VDCP_2DOP.extend;
                  v_VPB_PROM_VDCP_2DOP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 22) THEN
                  v_VPB_PROM_VDCP_3ERP.extend;
                  v_VPB_PROM_VDCP_3ERP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 23) THEN
                  v_VLC_FREC_COP1_ANOA.extend;
                  v_VLC_FREC_COP1_ANOA(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '99');
                ELSIF (posicion = 24) THEN
                  v_VLC_FREC_COP2_ANOA.extend;
                  v_VLC_FREC_COP2_ANOA(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '99');
                ELSIF (posicion = 25) THEN
                  v_VLC_FREC_COP3_ANOA.extend;
                  v_VLC_FREC_COP3_ANOA(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '99');
                ELSIF (posicion = 26) THEN
                  v_VPB_PRVD_VCMN_APCZ.extend;
                  v_VPB_PRVD_VCMN_APCZ(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 27) THEN
                  v_VPB_PVDC_P1ZO.extend;
                  v_VPB_PVDC_P1ZO(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 28) THEN
                  v_VPB_PVDC_P2ZO.extend;
                  v_VPB_PVDC_P2ZO(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 29) THEN
                  v_VPB_PVDC_P3ZO.extend;
                  v_VPB_PVDC_P3ZO(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999.999999');
                ELSIF (posicion = 30) THEN
                  v_VCP_COMP_PAGO.extend;
                  v_VCP_COMP_PAGO(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 31) THEN
                  v_VCE_CODI_CALI.extend;
                  v_VCE_CODI_CALI(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 32) THEN
                  v_VPI_CONS_3ULT_CAMP.extend;
                  v_VPI_CONS_3ULT_CAMP(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '9');
                ELSIF (posicion = 33) THEN
                  v_VPI_INDI_SEGU.extend;
                  v_VPI_INDI_SEGU(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace)), '9');
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

    DELETE FROM INT_FLX_TEMPO_CALCU_MODEL
    WHERE USU_REGI = pscodigousuario;
    
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_COD_CLIE.count
        INSERT INTO INT_FLX_TEMPO_CALCU_MODEL (
            cam_pago,
            cod_clie,
            vpi_edad_clie,
            vpi_codi_regi,
            vpi_nuan_belc,
            vpi_indi_segm_nuev,
            vpi_indi_segm_con1,
            vpi_indi_segm_con2,
            vpi_indi_inco,
            vpi_indi_segm_tops,
            vpi_frec_ult9_camp,
            vpi_prom_vnta_ult3_camp,
            vpi_prom_vnta_ult9_camp,
            vpi_prom_vnta_ult6_camp,
            vpi_cons_ult6_camp,
            vpi_num_unid_vend,
            vpb_prvd_vcmn_18ca,
            vpb_prvd_vcmn_09ca,
            vpb_prvd_vcmn_anpc,
            vpb_prom_vdcp_1erp,
            vpb_prom_vdcp_2dop,
            vpb_prom_vdcp_3erp,
            vlc_frec_cop1_anoa,
            vlc_frec_cop2_anoa,
            vlc_frec_cop3_anoa,
            vpb_prvd_vcmn_apcz,
            vpb_pvdc_p1zo,
            vpb_pvdc_p2zo,
            vpb_pvdc_p3zo,
            vcp_comp_pago,
            vce_codi_cali,
            usu_regi,
            vpi_cons_3ult_camp,
            vpi_indi_segu)
        VALUES(
            v_CAM_PAGO(i),
            v_COD_CLIE(i),
            v_VPI_EDAD_CLIE(i),
            v_VPI_CODI_REGI(i),
            v_VPI_NUAN_BELC(i),
            v_VPI_INDI_SEGM_NUEV(i),
            v_VPI_INDI_SEGM_CON1(i),
            v_VPI_INDI_SEGM_CON2(i),
            v_VPI_INDI_INCO(i),
            v_VPI_INDI_SEGM_TOPS(i),
            v_VPI_FREC_ULT9_CAMP(i),
            v_VPI_PROM_VNTA_ULT3_CAMP(i),
            v_VPI_PROM_VNTA_ULT9_CAMP(i),
            v_VPI_PROM_VNTA_ULT6_CAMP(i),
            v_VPI_CONS_ULT6_CAMP(i),
            v_VPI_NUM_UNID_VEND(i),
            v_VPB_PRVD_VCMN_18CA(i),
            v_VPB_PRVD_VCMN_09CA(i),
            v_VPB_PRVD_VCMN_ANPC(i),
            v_VPB_PROM_VDCP_1ERP(i),
            v_VPB_PROM_VDCP_2DOP(i),
            v_VPB_PROM_VDCP_3ERP(i),
            v_VLC_FREC_COP1_ANOA(i),
            v_VLC_FREC_COP2_ANOA(i),
            v_VLC_FREC_COP3_ANOA(i),
            v_VPB_PRVD_VCMN_APCZ(i),
            v_VPB_PVDC_P1ZO(i),
            v_VPB_PVDC_P2ZO(i),
            v_VPB_PVDC_P3ZO(i),
            NVL(v_VCP_COMP_PAGO(i), 'B'),
            v_VCE_CODI_CALI(i),
            pscodigousuario,
            v_VPI_CONS_3ULT_CAMP(i),
            v_VPI_INDI_SEGU(i)
        );

        sqlQuery := 'analyze table flx_varia_calcu_model compute statistics';
        execute immediate (sqlQuery);

        DELETE FROM FLX_VARIA_CALCU_MODEL WHERE CAM_CERR = psCodigoPeriodo;

        INSERT INTO FLX_VARIA_CALCU_MODEL(
            cam_cerr,
            cod_clie,
            vpi_codi_regi,
            vpi_edad_clie,
            vpi_nuan_belc,
            vpi_indi_segm_nuev,
            vpi_indi_segm_con1,
            vpi_indi_segm_con2,
            vpi_indi_inco,
            vpi_indi_segm_tops,
            vpi_frec_ult9_camp,
            vpi_prom_vnta_ult3_camp,
            vpi_prom_vnta_ult9_camp,
            vpi_prom_vnd_ult6c,
            vpi_cons_ult6_camp,
            vpi_num_unid_vend,
            vpi_ind_dias_atra_0021,
            vpi_num_camp_da42_ult9_camp,
            vpi_flg_da168_ult9_camp,
            vpi_val_abon_adia,
            vpi_pri_da00_ult3_camp,
            vpi_ult_da21_ult9_camp,
            vpi_pri_da00_ult9_camp,
            vpi_pda_ult6_camp,
            vpi_max_pul3_camp,
            vpi_pdp_ult3_camp,
            vpi_pro_dul3_camp,
            vpi_ult_da00_ult6_camp,
            vpi_num_camp_d168_ult9_camp,
            vpb_prvd_vcmn_18ca,
            vpb_prvd_vcmn_09ca,
            vpb_prvd_vcmn_anpc,
            vpb_prom_vdcp_1erp,
            vpb_prom_vdcp_2dop,
            vpb_prom_vdcp_3erp,
            vlc_frec_cop1_anoa,
            vlc_frec_cop2_anoa,
            vlc_frec_cop3_anoa,
            vpb_prvd_vcmn_apcz,
            vpb_pvdc_p1zo,
            vpb_pvdc_p2zo,
            vpb_pvdc_p3zo,
            vcp_comp_pago,
            vce_codi_cali,
            usu_regi,
            fec_regi,
            vpi_ult_da00_ult9_camp,
            vpi_cons_3ult_camp,
            vpi_indi_segu,
            VPI_NUM_DIAS_PAGO,
            VPI_NUM_CAMP_DA21_ULT9_CAMP,
            VPI_VAL_SALD_VENC,
            VPI_PRO_DUL9_CAMP,
            VPI_FLG_DA21_ULT9_CAMP
        )
        SELECT 
            a.cam_pago,
            a.cod_clie,
            a.vpi_codi_regi,
            a.vpi_edad_clie,
            a.vpi_nuan_belc,
            a.vpi_indi_segm_nuev,
            a.vpi_indi_segm_con1,
            a.vpi_indi_segm_con2,
            a.vpi_indi_inco,
            a.vpi_indi_segm_tops,
            a.vpi_frec_ult9_camp,
            a.vpi_prom_vnta_ult3_camp,
            a.vpi_prom_vnta_ult9_camp,
            a.vpi_prom_vnta_ult6_camp,
            a.vpi_cons_ult6_camp,
            a.vpi_num_unid_vend,
            (SELECT ind_dias_atra_0021 FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT num_camp_da42_ult9_camp FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT flg_da168_ult9_camp FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT val_abon_adia FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT 
              (
                SELECT to_number(spc.cod_peri)
                  FROM seg_perio_corpo spc, cra_perio cp, seg_canal sc, seg_marca sm
                 WHERE cp.oid_peri = pri_da00_ult3_camp
                   AND spc.oid_peri = cp.peri_oid_peri
                   AND cp.cana_oid_cana = sc.oid_cana
                   AND cp.marc_oid_marc = sm.oid_marc
                   AND sc.cod_cana = 'VD'
                   AND sm.cod_marc = 'T'
              )
              FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT ult_da21_ult9_camp FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT 
              (
                SELECT to_number(spc.cod_peri)
                  FROM seg_perio_corpo spc, cra_perio cp, seg_canal sc, seg_marca sm
                 WHERE cp.oid_peri = pri_da00_ult9_camp
                   AND spc.oid_peri = cp.peri_oid_peri
                   AND cp.cana_oid_cana = sc.oid_cana
                   AND cp.marc_oid_marc = sm.oid_marc
                   AND sc.cod_cana = 'VD'
                   AND sm.cod_marc = 'T'
              )
             FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT pda_ult6_camp FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT max_pul3_camp FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT pdp_ult3_camp FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT pro_dul3_camp FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT 
              (
                SELECT to_number(spc.cod_peri)
                  FROM seg_perio_corpo spc, cra_perio cp, seg_canal sc, seg_marca sm
                 WHERE cp.oid_peri = ult_da00_ult6_camp
                   AND spc.oid_peri = cp.peri_oid_peri
                   AND cp.cana_oid_cana = sc.oid_cana
                   AND cp.marc_oid_marc = sm.oid_marc
                   AND sc.cod_cana = 'VD'
                   AND sm.cod_marc = 'T'
              )
            FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT num_camp_d168_ult9_camp FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            a.vpb_prvd_vcmn_18ca,
            a.vpb_prvd_vcmn_09ca,
            a.vpb_prvd_vcmn_anpc,
            a.vpb_prom_vdcp_1erp,
            a.vpb_prom_vdcp_2dop,
            a.vpb_prom_vdcp_3erp,
            a.vlc_frec_cop1_anoa,
            a.vlc_frec_cop2_anoa,
            a.vlc_frec_cop3_anoa,
            a.vpb_prvd_vcmn_apcz,
            a.vpb_pvdc_p1zo,
            a.vpb_pvdc_p2zo,
            a.vpb_pvdc_p3zo,
            a.vcp_comp_pago,
            a.vce_codi_cali,
            a.usu_regi,
            sysdate fec_regi,
            (SELECT
              (
                SELECT to_number(spc.cod_peri)
                  FROM seg_perio_corpo spc, cra_perio cp, seg_canal sc, seg_marca sm
                 WHERE cp.oid_peri = ult_da00_ult9_camp
                   AND spc.oid_peri = cp.peri_oid_peri
                   AND cp.cana_oid_cana = sc.oid_cana
                   AND cp.marc_oid_marc = sm.oid_marc
                   AND sc.cod_cana = 'VD'
                   AND sm.cod_marc = 'T'
              )
             FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            a.vpi_cons_3ult_camp,
            a.vpi_indi_segu,
            (SELECT NVL(NUM_DIAS_PAGO, 0) FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT NVL(NUM_CAMP_DA21_ULT9_CAMP, 0) FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT NVL(VAL_SALD_VENC, 0) FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT NVL(PRO_DUL9_CAMP, 0) FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie)),
            (SELECT NVL(FLG_DA21_ULT9_CAMP, 0) FROM FLX_VARIA_CUENT_CORRI WHERE cam_cier = a.cam_pago AND oid_clie = (select mcl.oid_clie from mae_clien mcl where mcl.cod_clie = a.cod_clie))
        FROM INT_FLX_TEMPO_CALCU_MODEL a
        WHERE a.cam_pago = pscodigoperiodo 
        AND a.usu_regi = pscodigousuario;
        
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_FLX_RECEP_VARIA_DATAM: ' || ls_sqlerrm);

  END INT_PR_FLX_RECEP_VARIA_DATAM;

END INT_PKG_FLEX;
/
