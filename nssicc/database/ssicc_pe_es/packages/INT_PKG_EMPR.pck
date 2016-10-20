CREATE OR REPLACE PACKAGE INT_PKG_EMPR IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=5000;

/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de Maestro de socias
Fecha Creacion    : 27/02/2013
Autor: Dennys Oliva Iriarte
***************************************************************************************
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
 psNumeroLote     : Numero de lote
****************************************************************************************/
PROCEDURE INT_PR_EMP_ENVIO_MAEST_SOCIA(psCodigoPais           VARCHAR2,
                                       psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psNumeroLote           VARCHAR2);

/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de Vinculos de socias
Fecha Creacion    : 27/02/2013
Autor: Dennys Oliva Iriarte
***************************************************************************************
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
 psNumeroLote     : Numero de lote
****************************************************************************************/
PROCEDURE INT_PR_EMP_ENVIO_VINCU_SOCIA(psCodigoPais           VARCHAR2,
                                       psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psNumeroLote           VARCHAR2);

/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de Bajas de socias
Fecha Creacion    : 27/02/2013
Autor: Dennys Oliva Iriarte
***************************************************************************************
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
 psNumeroLote     : Numero de lote
****************************************************************************************/
PROCEDURE INT_PR_EMP_ENVIO_BAJAS_SOCIA(psCodigoPais           VARCHAR2,
                                       psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psNumeroLote           VARCHAR2);

END INT_PKG_EMPR;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_EMPR IS
/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de Maestro de socias
Fecha Creacion    : 27/02/2013
Autor: Dennys Oliva Iriarte
***************************************************************************************
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
 psNumeroLote     : Numero de lote
****************************************************************************************/
PROCEDURE INT_PR_EMP_ENVIO_MAEST_SOCIA
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psNumeroLote           VARCHAR2)
IS
   CURSOR c_interfaz IS
      select emp.cod_clie,
             decode(emp.ind_empr,'1',emp.pre_camp_inic_part_pre,emp.cam_inic_empr),
             decode(emp.ind_empr,'2',(select niv.nom_nive from emp_nivel niv
                                        where niv.cod_prog = emp.cod_prog 
                                          and niv.cod_nive = emp.cod_nive),
                                       (select clas.nom_clasi from emp_clasi clas
                                         where clas.cod_prog = emp.cod_prog
                                           and clas.cod_clasi = emp.PRE_CODI_CLASI)   
                                      ),
             ' ',
             decode(emp.ind_empr,'1','PS','SE' ),
             decode(emp.cod_tipo_empre,'R','C','F','FS','PS'),
             gen_pkg_gener.gen_fn_clien_datos(emp.cod_clie,'COD_REGI'),
             gen_pkg_gener.gen_fn_clien_datos(emp.cod_clie,'COD_ZONA'),
             (select mcm.cod_clie 
                from mae_clien_vincu cv, 
                     mae_clien mc,
                     mae_clien mcm
                where cv.tivc_oid_tipo_vinc in (select tv.oid_tipo_vinc 
                                                  from mae_tipo_vincu tv 
                                                 where tv.cod_tipo_vinc = '07')
                and mc.oid_clie = cv.clie_oid_clie_vndo
                and mcm.oid_clie = cv.clie_oid_clie_vnte
                and cv.fec_hast is null
                and mc.cod_clie = emp.cod_clie
             ), -- Madre 
             decode(emp.ind_baja,'1','0','1')
        from emp_empre emp
       where not exists(select null from EMP_MAEST_DATAM_HISTO hd where hd.cod_clie = emp.cod_clie);

   TYPE interfazRec IS RECORD
       (
         cod_clie       emp_empre.cod_clie%type,
         cam_ingr       emp_empre.pre_camp_inic_part_pre%type,
         nom_nive       emp_nivel.nom_nive%type,
         nom_nive_postu emp_nivel.nom_nive%type,
         tip_part       varchar2(2),
         origen         varchar2(2),         
         cod_regi       zon_regio.cod_regi%type,
         cod_zona       zon_zona.cod_zona%type,
         cod_clie_madr  emp_empre.cod_clie%type,
         ind_acti       varchar2(1)
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
                          lsLinea :=   interfazRecord(x).cod_clie        ||';'||
                                       interfazRecord(x).cam_ingr        ||';'||
                                       interfazRecord(x).nom_nive        ||';'||
                                       interfazRecord(x).nom_nive_postu  ||';'||
                                       interfazRecord(x).tip_part        ||';'||
                                       interfazRecord(x).origen          ||';'||                                       
                                       interfazRecord(x).cod_regi        ||';'||
                                       interfazRecord(x).cod_zona        ||';'||
                                       interfazRecord(x).cod_clie_madr   ||';'||
                                       interfazRecord(x).ind_acti      ;

                          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );                 
                          
                          /* Se inserta en la tabla para que ya no sea enviado para el caso de novedades */
                          insert into EMP_MAEST_DATAM_HISTO values(interfazRecord(x).cod_clie, sysdate,psNumeroLote);                       

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_EMP_ENVIO_MAEST_SOCIA: '||ls_sqlerrm);

END INT_PR_EMP_ENVIO_MAEST_SOCIA;

/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de Vinculos de socias
Fecha Creacion    : 27/02/2013
Autor: Dennys Oliva Iriarte
***************************************************************************************
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
 psNumeroLote     : Numero de lote
****************************************************************************************/
PROCEDURE INT_PR_EMP_ENVIO_VINCU_SOCIA
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psNumeroLote           VARCHAR2)
IS
   CURSOR c_interfaz IS
      select cf.cod_peri, 
             mh.cod_clie hija ,
             mm.cod_clie madre
      from mae_clien_vincu mv,
           mae_clien mm,
           mae_clien mh,
           bas_ctrl_fact cf
      where mv.tivc_oid_tipo_vinc = 13
      and nvl(mv.fec_hast,sysdate) >= sysdate
      and mm.oid_clie = mv.clie_oid_clie_vnte
      and mh.oid_clie = mv.clie_oid_clie_vndo
      and cf.sta_camp = '0'
      and cf.ind_camp_act = '1';
      
      /*select (select sp.cod_peri 
                from cra_perio cp, seg_perio_corpo sp 
               where cv.fec_desd between cp.fec_inic and cp.fec_fina
                 and sp.oid_peri = cp.peri_oid_peri
                 and rownum = 1),
             mh.cod_clie,
             m.cod_clie,
             '1',
             decode(
             (select count(1)
                from mae_clien_vincu mv 
                where mv.clie_oid_clie_vndo = cv.clie_oid_clie_vndo
                and mv.tivc_oid_tipo_vinc = 13 
             ),1,'0','1'
             ),
             ' '
      from mae_clien_vincu cv,
           mae_clien m,
           mae_clien mh,
           emp_empre emp 
      where cv.tivc_oid_tipo_vinc = 13
        and m.oid_clie = cv.clie_oid_clie_vnte
        and mh.oid_clie = cv.clie_oid_clie_vndo
        and emp.cod_clie = m.cod_clie
        and nvl(emp.ind_baja,'0') != '1'
        and cv.fec_hast is null -- vinculos activos
        and not exists(select null 
                         from EMP_ENVIO_VINCU_DATAM hd 
                        where hd.cod_clie_vnte = m.cod_clie
                          and hd.cod_clie_vndo = mh.cod_clie);*/

   TYPE interfazRec IS RECORD( cod_peri       seg_perio_corpo.cod_peri%type,
         cod_clie_hija  mae_clien.cod_clie%type,
                               cod_clie_madr  mae_clien.cod_clie%type );

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
                          lsLinea :=   interfazRecord(x).cod_peri       ||';'||
                                       interfazRecord(x).cod_clie_hija  ||';'||
                                       interfazRecord(x).cod_clie_madr  ;

                          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );                 
                          
                          /* Se inserta en la tabla para que ya no sea enviado para el caso de novedades */
                          /*insert into EMP_ENVIO_VINCU_DATAM 
                                values(interfazRecord(x).cod_clie_madr,
                                       interfazRecord(x).cod_clie_hija, 
                                       sysdate,
                                       psNumeroLote);*/                       

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_EMP_ENVIO_VINCU_SOCIA: '||ls_sqlerrm);

END INT_PR_EMP_ENVIO_VINCU_SOCIA;

/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de Bajas de socias
Fecha Creacion    : 27/02/2013
Autor: Dennys Oliva Iriarte
***************************************************************************************
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoSistema  : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo  : Nombre Archivo
 psNumeroLote     : Numero de lote
****************************************************************************************/
PROCEDURE INT_PR_EMP_ENVIO_BAJAS_SOCIA
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psNumeroLote           VARCHAR2)
IS
   CURSOR c_interfaz IS
      select ee.cod_peri_baja, 
             ee.pre_codi_zona,
             ee.cod_clie,
             mb.des_moti_baja
        from emp_empre ee,
             emp_motiv_baja mb
       where ee.ind_baja = '1'
         and mb.cod_moti_baja = ee.cod_moti_baja
         and mb.ind_pre_empr = ee.ind_empr
         and not exists(select null 
                          from EMP_ENVIO_BAJAS_DATAM bd 
                         where bd.cod_peri = ee.cod_peri_baja
                           and bd.cod_clie = ee.cod_clie);

   TYPE interfazRec IS RECORD
       (
         cod_peri       emp_empre.cod_peri_baja%type,
         cod_zona       emp_empre.pre_codi_zona%type,
         cod_clie       emp_empre.cod_clie%type,
         des_baja       emp_motiv_baja.des_moti_baja%type
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
                          lsLinea :=   interfazRecord(x).cod_peri  ||';'||
                                       interfazRecord(x).cod_zona  ||';'||
                                       interfazRecord(x).cod_clie  ||';'||
                                       interfazRecord(x).des_baja  ;

                          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );                 
                          
                          /* Se inserta en la tabla para que ya no sea enviado para el caso de novedades */
                          insert into EMP_ENVIO_BAJAS_DATAM 
                                values(interfazRecord(x).cod_peri,
                                       interfazRecord(x).cod_clie, 
                                       sysdate,
                                       psNumeroLote);                       

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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_EMP_ENVIO_BAJAS_SOCIA: '||ls_sqlerrm);

END INT_PR_EMP_ENVIO_BAJAS_SOCIA;

END INT_PKG_EMPR; 
/
