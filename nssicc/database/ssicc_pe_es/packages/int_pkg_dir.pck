CREATE OR REPLACE PACKAGE INT_PKG_DIR IS

   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(1500);
   W_FILAS      NUMBER:=999;

/***************************************************************************
  Descripcion               :  Procedimiento que recepciona el archivo de Clientes
  Fecha Creacion        : 24/07/2013
  Autor                     : Ivan Tocto
***************************************************************************/
 PROCEDURE INT_PR_DIR_RECEP_CLIEN
  (
    psusuario   VARCHAR2
  );

END INT_PKG_DIR;
/

CREATE OR REPLACE PACKAGE BODY INT_PKG_DIR IS

/***************************************************************************
  Descripcion               :  Procedimiento que recepciona el archivo de Clientes
  Fecha Creacion        : 24/07/2013
  Autor                     : Ivan Tocto
***************************************************************************/
 PROCEDURE INT_PR_DIR_RECEP_CLIEN
  (
    psusuario   VARCHAR2
  ) IS
  
  BEGIN

    INSERT INTO zon_direc_clien(
        pais_cod_pais,
        cod_clie,
        --cod_regi,
        --cod_zona,
        --cod_secc,
        --cod_terr,
        cam_ingr,
        ape_pate,
        ape_mate,
        --ape_casa,
        --pri_nomb,
        --seg_nomb,
        num_docu,
        fec_naci,
        --num_tele_part,
        --num_celu,
        --ind_moro,
        --sal_clie,
        --ape_nomb_comp,
        --ind_bloq,
        --niv_cons,
        --cod_depa,
        --cod_muni,
        --des_pobl,
        --cam_ingr_come,
        ---sta_clie,
        est_regi,
        usu_crea,
        fec_crea)
    SELECT
        pais_cod_pais,
        cod_clie,
        --cod_regi,
       -- cod_zona,
       -- cod_secc,
        --cod_terr,
        cam_ingr,
        ape_pate,
        ape_mate,
        --ape_casa,
       -- pri_nomb,
        --seg_nomb,
        num_docu,
        fec_naci,
        --num_tele_part,
        --num_celu,
        --ind_moro,
        --sal_clie,
        --ape_nomb_comp,
        --ind_bloq,
        --niv_cons,
        --cod_depa,
        --cod_muni,
        --des_pobl,
        --cam_ingr_come,
        --sta_clie,
        '1',
        psusuario,
        sysdate
    FROM INT_TMP_zon_direc_clien
    WHERE ( pais_cod_pais, cod_clie) NOT IN( SELECT pais_cod_pais, cod_clie FROM zon_direc_clien );
    
    
    for reg in (
        SELECT
            pais_cod_pais,
            cod_clie,
            cod_regi,
            cod_zona,
            cod_secc,
            cod_terr,
            cam_ingr,
            ape_pate,
            ape_mate,
            ape_casa,
            pri_nomb,
            seg_nomb,
            num_docu,
            fec_naci,
            num_tele_part,
            num_celu,
            ind_moro,
            sal_clie,
            ape_nomb_comp,
            ind_bloq,
            niv_cons,
            cod_depa,
            cod_muni,
            des_pobl,
            cam_ingr_come,
            sta_clie
        FROM INT_TMP_zon_direc_clien
        WHERE ( pais_cod_pais, cod_clie) IN( SELECT pais_cod_pais, cod_clie FROM zon_direc_clien )) 
    LOOP

        UPDATE zon_direc_clien SET
        --cod_regi = reg.cod_regi,
        --cod_zona = reg.cod_zona,
        --cod_secc = reg.cod_secc,
        --cod_terr = reg.cod_terr,
        cam_ingr = reg.cam_ingr,
      --  ape_pate = reg.ape_pate,
       -- ape_mate = reg.ape_mate,
       -- ape_casa = reg.ape_casa,
        --pri_nomb = reg.pri_nomb,
        --seg_nomb = reg.seg_nomb,
        num_docu = reg.num_docu,
        fec_naci = reg.fec_naci,
       -- num_tele_part = reg.num_tele_part,
      --  num_celu = reg.num_celu,
       -- ind_moro = reg.ind_moro,
       -- sal_clie = reg.sal_clie,
        --ape_nomb_comp = reg.ape_nomb_comp,
        --ind_bloq = reg.ind_bloq,
        --niv_cons = reg.niv_cons,
        --cod_depa = reg.cod_depa,
        --cod_muni = reg.cod_muni,
        --des_pobl = reg.des_pobl,
        --cam_ingr_come = reg.cam_ingr_come,
        --sta_clie = reg.sta_clie,
        usu_modi = psusuario,
        fec_modi = sysdate
        WHERE pais_cod_pais = reg.pais_cod_pais
        AND cod_clie = reg.cod_clie;
    
    END LOOP;
    
  
 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DIR_RECEP_CLIEN: '||ls_sqlerrm);
  
  END INT_PR_DIR_RECEP_CLIEN;

END INT_PKG_DIR;
/