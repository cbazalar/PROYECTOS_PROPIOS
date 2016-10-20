CREATE OR REPLACE PACKAGE EMP_PKG_PROCE is

/******************************************************************************
 Descripcion         : EMP_PR_PROCE_VINCU_PREEM_CONSU
                       Proceso que crea los vinculos a las pre-emprendedoras
                       con las consultoras de su seccion
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 13/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psCodigoPrograma : Codigo Programa
     psUsuario        : Usuario
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
  PROCEDURE EMP_PR_PROCE_VINCU_PREEM_CONSU(psCodigoPrograma  VARCHAR2,
                                           psUsuario         VARCHAR2);
  /******************************************************************************
 Descripcion         : EMP_PR_ELIMI_CARGA_PRE_EMPRE
                       Proceso elimina las tablas de carga.
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 13/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramírez
********************************************************************************/                                         
 PROCEDURE EMP_PR_ELIMI_CARGA_PRE_EMPRE;  
 /******************************************************************************
 Descripcion         : EMP_PR_VALID_CARGA_PRE_EMPRE
                       Proceso que valida la carga.
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 13/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada: 
            p_cod_prog
            p_cant_regi_carg
            p_cod_error
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramírez
********************************************************************************/
 PROCEDURE EMP_PR_VALID_CARGA_PRE_EMPRE ( 
   p_cod_prog											IN   VARCHAR2,
   p_cant_regi_carg               OUT      VARCHAR2,
   p_cod_error                    OUT     VARCHAR2);
/******************************************************************************
 Descripcion         : EMP_PR_PROCE_PRE_EMPRE
                       Proceso que realiza la carga de pre emprendedoras validadas
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 26/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:

     p_cod_peri        : Código de Periodo
     p_cod_prog        : Código de Programa
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramírez
********************************************************************************/   
 PROCEDURE EMP_PR_PROCE_PRE_EMPRE(
    p_cod_peri											IN   VARCHAR2,
    p_cod_prog											IN   VARCHAR2,
    p_cod_usuario                   IN   VARCHAR2
    );   
/******************************************************************************
 Descripcion         : EMP_FN_CALCU_CLASI
                       Proceso que calcula  la clasificación de la pre empresaria
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 26/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:

     p_cod_prog        : Código de Programa
     p_num_pedi_base   : Pedido base     
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramírez
********************************************************************************/  
  FUNCTION EMP_FN_CALCU_CLASI(
    p_cod_prog                    IN   emp_progr.cod_prog%TYPE,
    p_num_pedi_base               IN   emp_carga_pre_empre.num_pedi_base%TYPE
    )
    RETURN NUMBER;                                           
    
/******************************************************************************
 Descripcion         : EMP_PR_PROCE_BAJAS_EMPRE
                       Proceso que da de baja a las empresarias
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 13/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psCodigoPrograma : Codigo Programa
     
     psUsuario        : Usuario
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
  PROCEDURE EMP_PR_PROCE_BAJAS_EMPRE(psCodigoCliente   VARCHAR2,
                                     psMotivoBaja      VARCHAR2,
                                     psUsuario         VARCHAR2);    

/******************************************************************************
 Descripcion         : EMP_PR_PROCE_REASI_EMPRE
                       Proceso que reasigna la empresaria a una consultora
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 26/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:

     psUsuario        : Usuario
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
  PROCEDURE EMP_PR_PROCE_REASI_EMPRE(psCodigoConsultora         VARCHAR2,
                                     psCodigoNuevaEmprendedora  VARCHAR2,
                                     psMotivoReasignacion       VARCHAR2,
                                     psUsuario                  VARCHAR2);

/******************************************************************************
 Descripcion         : EMP_PR_PROCE_VINCU_NUEVA_REACT
                       Proceso que crea los vinculos a las nuevas y reactivadas
                       con su socia o pre-socia correspondiente
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 29/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psCodigoPrograma : Codigo Programa
     psUsuario        : Usuario
 ------------------------------------------------------------------------------    
 Caso de Uso : 2.1 Creacion de consultoras 
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
  PROCEDURE EMP_PR_PROCE_VINCU_NUEVA_REACT(psCodigoPrograma  VARCHAR2,
                                           psUsuario         VARCHAR2);                                     
 
/******************************************************************************
 Descripcion         : EMP_PR_VALID_REASI_MASIV
                       Proceso que crea los vinculos a las nuevas y reactivadas
                       con su socia o pre-socia correspondiente
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 29/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psCodigoPrograma : Codigo Programa
     psUsuario        : Usuario
 ------------------------------------------------------------------------------    
 Caso de Uso : 2.1 Creacion de consultoras 
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
  PROCEDURE EMP_PR_VALID_REASI_MASIV(psUsuario VARCHAR2,
                                     p_cant_erro OUT VARCHAR2);

  /******************************************************************************
 Descripcion         : EMP_PR_PROCE_UPDAT_DATOS_CLIEN
                       Proceso que actualiza los datos de telefono celular, fijo
                       y email de la consultora.
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 16/01/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psOidCliente     : Oid Cliente
     psUsuario        : Usuario
     psDato           : Valor a modificar
     psCampo          : Campo modificar
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
********************************************************************************/
  PROCEDURE EMP_PR_PROCE_UPDAT_DATOS_CLIEN(psOidCliente VARCHAR2,
                                           psDato VARCHAR2,
                                           psCampo VARCHAR2,                                           
                                           psUsuario VARCHAR2,
                                           psIndOrigen VARCHAR2);
                                          
 FUNCTION EMP_FN_CUENT_PEDID(
          psCliente  emp_empre.cod_clie%type,
          psPeriodo  bas_ctrl_fact.cod_peri%type) 
          RETURN NUMBER; 

 FUNCTION EMP_FN_VALID_RECO_EFEC(psClienteRecomendante  emp_empre.cod_clie%type,
                                 psClienteRecomendada  emp_empre.cod_clie%type,
                                 psPrimerPedido  bas_ctrl_fact.cod_peri%type) 
          RETURN NUMBER;
          
 FUNCTION EMP_FN_CUENT_RECO_EFEC(psPrograma               emp_empre.cod_prog%type,
                                 psPeriodo                bas_ctrl_fact.cod_peri%type,                                 
                                 psCliente                emp_empre.cod_clie%type,
                                 psPeriIni                bas_ctrl_fact.cod_peri%type,
                                 psPeriFin                bas_ctrl_fact.cod_peri%type,
                                 psUsuario                emp_empre.usu_regi%type,
                                 lnOid                    NUMBER) RETURN NUMBER;

 PROCEDURE EMP_PR_PROCE_GUARD_ERROR_EMPRE( psPeriodo                emp_empre.cod_peri_baja%type,      
                                           psPrograma               emp_empre.cod_prog%type,
                                           psCliente                emp_empre.cod_clie%type,
                                           psTipoEmprendedora       emp_empre.cod_tipo_empre%type,
                                           psUsuario                emp_empre.usu_regi%type);   
                                           
                                                 
                                            
  PROCEDURE EMP_PR_PROCE_REASI_RECOM(psPrograma     emp_empre.cod_prog%type,
                                        psCliente      emp_empre.cod_clie%type,
                                        psPeriodo      bas_ctrl_fact.cod_peri%type,
                                        psUsuario      emp_empre.usu_regi%type,
                                        psNumRecoEfec  NUMBER,
                                        lnOid          NUMBER);
                                        
/******************************************************************************
 Descripcion         : EMP_PR_PROCE_REASI_NUEVA
                       Proceso que reasgina las recomendadas efectivas y e identifica
                       a las recomendadas normales de la campaña anterio y las nuevas
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 17/01/2012
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
 /********************************************************************************/  
  PROCEDURE EMP_PR_PROCE_REASI_NUEVA(psPrograma     emp_empre.cod_prog%type,
                                        psCliente      emp_empre.cod_clie%type,
                                        psPeriodo      bas_ctrl_fact.cod_peri%type,
                                        psPrimePedi     bas_ctrl_fact.cod_peri%type,
                                        psUsuario      emp_empre.usu_regi%type,
                                        lnOid          NUMBER);                                        
                                        
 /******************************************************************************
 Descripcion         : EMP_PR_PROCE_VALID_EMPRE_REGUL
                       Proceso que valida la empresaria regular
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 17/01/2012
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
********************************************************************************/                                           
 PROCEDURE EMP_PR_PROCE_VALID_EMPRE_REGUL(psPeriodo    IN     bas_ctrl_fact.cod_peri%type,
                                          psPrograma   IN     emp_empre.cod_prog%type,
                                          psUsuario    IN     emp_empre.usu_regi%type,
                                          psCliente    IN     emp_empre.cod_clie%type,
                                          psError      OUT    NUMBER,
                                          psOidReco    OUT    NUMBER);        
                                          
  /******************************************************************************
 Descripcion         : EMP_FN_CUENT_RECO_NUEVA
                       Proceso que cuenta las recomendaciones efectivas en un rango
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 04/04/2013
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
********************************************************************************/                     
 FUNCTION EMP_FN_CUENT_RECO_NUEVA(psPrograma               emp_empre.cod_prog%type,
                                 psPeriOri                bas_ctrl_fact.cod_peri%type,
                                 psPeriodo                bas_ctrl_fact.cod_peri%type,                                 
                                 psCliente                emp_empre.cod_clie%type,
                                 psUsuario                emp_empre.usu_regi%type,
                                 lnOid                    NUMBER) RETURN NUMBER;                                          
                                          
 /******************************************************************************
 Descripcion         : EMP_PR_PROCE_VALID_EMPRE_FAST
                       Proceso que valida la empresaria fastruck
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 04/04/2013
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
********************************************************************************/                                           
 PROCEDURE EMP_PR_PROCE_VALID_EMPRE_FAST(psPeriodo    IN     bas_ctrl_fact.cod_peri%type,
                                          psPrograma   IN     emp_empre.cod_prog%type,
                                          psUsuario    IN     emp_empre.usu_regi%type,
                                          psCliente    IN     emp_empre.cod_clie%type,
                                          psError      OUT    NUMBER,
                                          psNivel      OUT    NUMBER,
                                          psOidReco    OUT    NUMBER);                                                                                                                      
                                         
                                    
END EMP_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY EMP_PKG_PROCE is
  /* Declaracion de Variables */
  ln_sqlcode        NUMBER(10);
  ls_sqlerrm        VARCHAR2(1000);

/******************************************************************************
 Descripcion         : EMP_PR_PROCE_VINCU_PREEM_CONSU
                       Proceso que crea los vinculos a las pre-emprendedoras
                       con las consultoras de su seccion
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 13/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psCodigoPrograma : Codigo Programa
     psUsuario        : Usuario
 ------------------------------------------------------------------------------
 Caso de Uso : 3.2 Vinculacion de consultoras a Pre-socias    
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
  PROCEDURE EMP_PR_PROCE_VINCU_PREEM_CONSU(psCodigoPrograma  VARCHAR2,
                                           psUsuario         VARCHAR2) as

  cursor curvinculos is

    select r.oid_regi,
           z.oid_zona,
           s.oid_secc,
           ua.clie_oid_clie,
           (select m.oid_clie --ee.cod_clie,                   
              from emp_empre ee, mae_clien m
             where ee.pre_codi_zona = z.cod_zona
               and ee.pre_codi_secc = s.cod_secc
               and ee.cod_prog = ep.cod_prog
               and m.cod_clie = ee.cod_clie
               and ee.ind_empr = '1')
      from mae_clien_unida_admin ua,
           MAE_CLIEN_DATOS_ADICI da,
           MAE_ESTAT_CLIEN       ec,
           zon_terri_admin       ta,
           zon_terri             t,
           zon_secci             s,
           zon_zona              z,
           zon_regio             r,
           mae_clien             c,
           emp_progr             ep,
           emp_progr_unida_admin ue,
           zon_zona              zz,
           zon_secci             zs,
           mae_clien             mm
           ---
     where ua.ind_acti = 1
       and ua.perd_oid_peri_fin is null
       and ua.ztad_oid_terr_admi = ta.oid_terr_admi
       and ta.terr_oid_terr = t.oid_terr
       and ta.zscc_oid_secc = s.oid_secc
       and s.zzon_oid_zona = z.oid_zona
       and z.zorg_oid_regi = r.oid_regi
       and c.oid_clie = ua.clie_oid_clie
       and da.clie_oid_clie = ua.clie_oid_clie
       and ec.oid_esta_clie = da.esta_oid_esta_clie
       -- No puede ser hija si es madre - Pre socias --
       and mm.oid_clie = ua.clie_oid_clie
       and not exists (select null from Emp_Empre pp where pp.cod_clie = mm.cod_clie) 
       -- join con tablas de EMP
       and ep.cod_prog = psCodigoPrograma --'001'
       and ep.cod_prog = ue.cod_prog
       and zz.cod_zona = ue.cod_zona
       and zz.oid_zona = zs.zzon_oid_zona
       and zs.ind_acti = '1'
       and zs.ind_borr = '0'
       and zz.oid_zona = z.oid_zona
       and zz.zorg_oid_regi = r.oid_regi
       and zs.oid_secc = s.oid_secc
       -- que no sea retirada
       and ec.cod_esta_clie != '07'
       -- Q no sea ella misma
       and (select oid_clie
              from mae_clien where cod_clie = (select ee.cod_clie
                                                  from emp_empre ee
                                                 where ee.pre_codi_zona = z.cod_zona
                                                   and ee.pre_codi_secc = s.cod_secc
                                                   and ee.cod_prog = ep.cod_prog
                                                   and ee.ind_empr = '1')
           ) != ua.clie_oid_clie
       /*
       -- Que no se haya registrado ya el vinculo de madre/hija
       and not exists (select null from mae_clien_vincu cv
                        where cv.clie_oid_clie_vnte = ua.clie_oid_clie
                          and cv.oid_clie_vinc = (select ee.cod_clie
                                                    from emp_empre ee
                                                   where ee.pre_codi_zona = z.cod_zona
                                                     and ee.pre_codi_secc = s.cod_secc
                                                     and ee.cod_prog = ep.cod_prog
                                                     and ee.ind_empr = '1')
                          and cv.tivc_oid_tipo_vinc = (select tv.oid_tipo_vinc
                                                         from mae_tipo_vincu tv
                                                        where tv.cod_tipo_vinc = '07'))
       */
       ;

       TYPE t_oid_regi      IS TABLE OF zon_regio.oid_regi%TYPE;
       TYPE t_oid_zona      IS TABLE OF zon_zona.oid_zona%TYPE;
       TYPE t_oid_secc      IS TABLE OF zon_secci.oid_secc%TYPE;
       TYPE t_oid_clie_vndo IS TABLE OF mae_clien.oid_clie%TYPE;
       TYPE t_oid_clie_vnte IS TABLE OF mae_clien.oid_clie%TYPE;

       v_oid_regi        t_oid_regi;
       v_oid_zona        t_oid_zona;
       v_oid_secc        t_oid_secc;
       v_oid_clie_vndo   t_oid_clie_vndo;
       v_oid_clie_vnte   t_oid_clie_vnte;

       rows NATURAL := 1000; -- Number of rows to process at a time
       i    BINARY_INTEGER := 0;

  BEGIN

     -- Se borra todos los vinculos existentes
     delete from mae_clien_vincu cv
      where cv.tivc_oid_tipo_vinc = (select tv.oid_tipo_vinc
                                       from mae_tipo_vincu tv
                                      where tv.cod_tipo_vinc = '07');

     OPEN curvinculos;
      LOOP
        FETCH curvinculos BULK COLLECT
          INTO v_oid_regi,
               v_oid_zona,
               v_oid_secc,
               v_oid_clie_vndo,
               v_oid_clie_vnte LIMIT rows;

          IF v_oid_regi.count > 0 THEN
             FOR i IN v_oid_clie_vndo.first .. v_oid_clie_vndo.last
          LOOP

             IF v_oid_clie_vnte IS NOT NULL THEN

                -- Se inserta el vinculo en la tabla de MAE
                insert into mae_clien_vincu
                  (oid_clie_vinc,
                   fec_desd,
                   fec_hast,
                   clie_oid_clie_vnte,
                   clie_oid_clie_vndo,
                   tivc_oid_tipo_vinc,
                   ind_vinc_ppal,
                   fec_ulti_actu,
                   usu_modi)
                values
                  (MAE_CVIN_SEQ.NEXTVAL, -- sequence
                   trunc(SYSDATE),
                   NULL,
                   v_oid_clie_vnte(i),
                   v_oid_clie_vndo(i),
                   (select tv.oid_tipo_vinc
                      from mae_tipo_vincu tv
                     where tv.cod_tipo_vinc = '07'),
                   '0',
                   sysdate,
                   psUsuario);

             END IF;

          END LOOP;
          END IF;

         EXIT WHEN curvinculos%NOTFOUND;
    END LOOP;
    CLOSE curvinculos;

  EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_VINCU_PREEM_CONSU ' || ls_sqlerrm);

  end EMP_PR_PROCE_VINCU_PREEM_CONSU;
  
  /******************************************************************************
 Descripcion         : EMP_PR_ELIMI_CARGA_PRE_EMPRE
                       Proceso elimina las tablas de carga.
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 13/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramírez
********************************************************************************/
  PROCEDURE EMP_PR_ELIMI_CARGA_PRE_EMPRE
   IS
   BEGIN

    DELETE FROM EMP_CARGA_PRE_EMPRE;
    DELETE FROM EMP_ERROR_PRE_EMPRE;

   EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_ELIMI_CARGA_PRE_EMPRE: '||ls_sqlerrm);

  END EMP_PR_ELIMI_CARGA_PRE_EMPRE;
  
 /******************************************************************************
 Descripcion         : EMP_PR_VALID_CARGA_PRE_EMPRE
                       Proceso que valida la carga.
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 13/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada: 
            p_cod_prog
            p_cant_regi_carg
            p_cod_error
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramírez
********************************************************************************/

 PROCEDURE EMP_PR_VALID_CARGA_PRE_EMPRE (
   p_cod_prog                      IN   VARCHAR2,
   p_cant_regi_carg               OUT      VARCHAR2,
   p_cod_error                    OUT     VARCHAR2)
   
   IS
       CURSOR c_carga_pre_empre IS
       SELECT * 
       FROM EMP_CARGA_PRE_EMPRE
       ORDER  BY VAL_FILA;              
       
       TYPE t_tab_emp_carga_pre_empre                IS TABLE OF EMP_CARGA_PRE_EMPRE%ROWTYPE;
      lv_tab_emp_carga_pre_empre                         t_tab_emp_carga_pre_empre;
      lv_reg_emp_carga_pre_empre                         emp_carga_pre_empre%ROWTYPE;
      lv_reg_emp_error_pre_empre                         emp_error_pre_empre%ROWTYPE;
            
      lv_oid_clie                                    NUMBER;
      lv_cod_zona                                    VARCHAR2(4);
      lv_cont_pre_empre                              NUMBER;
      lv_cont_carga_pre_empre                        NUMBER;
      lv_cant_Erro                                  NUMBER;
      
      rows NATURAL := 1000; -- Number of rows to process at a time
      x    BINARY_INTEGER := 0;
           
   BEGIN
       OPEN c_carga_pre_empre;
       LOOP
           FETCH c_carga_pre_empre BULK COLLECT INTO lv_tab_emp_carga_pre_empre LIMIT rows;
             IF lv_tab_emp_carga_pre_empre.COUNT > 0 THEN
               
                 FOR x IN lv_tab_emp_carga_pre_empre.FIRST .. lv_tab_emp_carga_pre_empre.LAST LOOP
                   lv_reg_emp_carga_pre_empre := lv_tab_emp_carga_pre_empre(x);
                  lv_reg_emp_error_pre_empre.val_fila := lv_reg_emp_carga_pre_empre.Val_Fila;
                 
                   --01) Validando los datos Obligatorios
                  IF (lv_reg_emp_carga_pre_empre.cod_clie IS NULL) THEN
                     lv_reg_emp_error_pre_empre.cod_erro := '01';
                     lv_reg_emp_error_pre_empre.des_erro := 'Dato oblligatorio en nulo - Codigo Consultora';

                     INSERT INTO emp_error_pre_empre VALUES lv_reg_emp_error_pre_empre;

                  ELSE

                     --02) El codigo consultora no existe
                     BEGIN
                        SELECT mc.oid_clie
                        INTO lv_oid_clie
                        FROM mae_clien mc
                        WHERE mc.cod_clie=lv_reg_emp_carga_pre_empre.cod_clie;
                        
                        --03) Zona no esta dentro del alcance geografico
                        BEGIN
                          SELECT un.cod_zona
                          INTO lv_cod_zona
                          FROM emp_progr_unida_admin un
                          WHERE un.cod_prog = p_cod_prog
                          AND un.cod_zona =  lv_reg_emp_carga_pre_empre.cod_zona;
                            BEGIN                                
                                --04) Una pre emprendedora por seccion  
                                  --Cuenta las ya cargada por seccion                              
                                  SELECT COUNT(*)
                                  INTO lv_cont_pre_empre
                                  FROM emp_empre ee
                                  WHERE ee.pre_codi_zona = lv_reg_emp_carga_pre_empre.cod_zona
                                  AND ee.pre_codi_secc = lv_reg_emp_carga_pre_empre.cod_secci;
                                  --Cuenta las que figuran en el excel por seccion
                                  SELECT count (*) 
                                  INTO lv_cont_carga_pre_empre
                                  FROM emp_carga_pre_empre cpe
                                  WHERE cpe.cod_zona = lv_reg_emp_carga_pre_empre.cod_zona
                                  AND cpe.cod_secci = lv_reg_emp_carga_pre_empre.cod_secci;
                                  
                                  IF (NVL(lv_cont_pre_empre,0) + NVL(lv_cont_carga_pre_empre,0) > 1) THEN
                                      lv_reg_emp_error_pre_empre.cod_erro := '04';
                                       lv_reg_emp_error_pre_empre.des_erro := 'Debe de haber una pre emprendedora por seccion';
      
                                      INSERT INTO emp_error_pre_empre VALUES lv_reg_emp_error_pre_empre;
                                 END IF;
                               END;                                                                              
                        EXCEPTION
                          WHEN NO_DATA_FOUND THEN
                             lv_reg_emp_error_pre_empre.cod_erro := '03';
                             lv_reg_emp_error_pre_empre.des_erro := 'Zona no esta definido dentro del alcance geografico';

                             INSERT INTO emp_error_pre_empre VALUES lv_reg_emp_error_pre_empre;
                        END;  

                        EXCEPTION
                           WHEN NO_DATA_FOUND THEN
                               lv_reg_emp_error_pre_empre.cod_erro := '02';
                               lv_reg_emp_error_pre_empre.des_erro := 'Codigo de Consultora No Existe';

                            INSERT INTO emp_error_pre_empre VALUES lv_reg_emp_error_pre_empre;
                       END;
                  END IF;
                END LOOP;
             END IF;
      EXIT WHEN c_carga_pre_empre%NOTFOUND;
         END LOOP;
      CLOSE c_carga_pre_empre;
      
      SELECT COUNT(*)
      INTO  lv_cant_Erro
      FROM  emp_error_pre_empre;

       p_cod_error := lv_cant_Erro;

       SELECT
          TO_CHAR(COUNT(*))
       INTO
          p_cant_regi_carg 
       FROM emp_carga_pre_empre;
       
   EXCEPTION
      WHEN OTHERS THEN
          ln_sqlcode := SQLCODE;
          ls_sqlerrm := substr(sqlerrm,1,250);
          RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_VALID_CARGA_PRE_EMPRE: '||ls_sqlerrm);
 END EMP_PR_VALID_CARGA_PRE_EMPRE;
/******************************************************************************
 Descripcion         : EMP_PR_PROCE_PRE_EMPRE
                       Proceso que realiza la carga de pre emprendedoras validadas
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 26/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:

     p_cod_peri        : Código de Periodo
     p_cod_prog        : Código de Programa
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramírez
********************************************************************************/  
 PROCEDURE EMP_PR_PROCE_PRE_EMPRE(
    p_cod_peri                      IN   VARCHAR2,
    p_cod_prog                      IN   VARCHAR2,
    p_cod_usuario                   IN   VARCHAR2
   )
    
 IS

  CURSOR cur_carg_masi_pre_empre
  IS
      SELECT
        cod_clie               COD_CLIE,                                                 
        p_cod_prog             COD_PROG,                                                 
        NULL                   PRE_CODI_CLASI,                                           
        cod_peri               PRE_CAMP_INIC_MEDI,                                       
        cod_zona               PRE_CODI_ZONA,                                            
        cod_secci               PRE_CODI_SECC,                                            
        num_pedi_base          PRE_NUME_PEDI_BASE,                                       
        val_vent_base          PRE_VALO_VENT_BASE,                                       
        num_acti_base          PRE_NUME_ACTI_BASE,                                       
        val_nive_ejec          PRE_VALO_NIVE_EJEC,                                       
        val_porc_comi_nive     PRE_VALO_PORC_COMI_NIVE,                                  
        val_psp                 PRE_VALO_PSP,                                             
        val_vent_base_ince     PRE_VALO_VENT_BASE_INCE,                                  
        NULL                   IND_BAJA,                                                 
        1                       IND_EMPR, --1: pre emprendedora                         
        p_cod_peri             PRE_CAMP_INIC_PART_PRE, --campaña de inicio de la pre  
        NULL                    CAM_INIC_EMPR,                                            
        NULL                    VAL_NUME_DOCU_LEGA,                                       
        NULL                    COD_NIVE,                                                 
        NULL                    VAL_CUEN_BANC,                                            
        NULL                    COD_MOTI_BAJA,                                            
        NULL                    VAL_CODI_CCI,                                             
        NULL                    COD_TIPO_REGI,                                            
        NULL                    COD_TIPO_EMPRE,                                           
        NULL                    USU_MODI_BAJA,                                            
        NULL                    FEC_MODI_BAJA,                                            
        p_cod_usuario           USU_REGI,                                                 
        sysdate                 FEC_REGI,                                                 
        NULL                    USU_MODI,                                                 
        NULL                    FEC_MODI,                                                 
        NULL                    COD_PERI_BAJA                                                         
     FROM EMP_CARGA_PRE_EMPRE
     ORDER  BY VAL_FILA;      
     
      TYPE t_tab_emp_empre                      IS TABLE OF emp_empre%ROWTYPE;

      lv_tab_emp_empre                               t_tab_emp_empre;

      rows NATURAL := 1000; -- Number of rows to process at a time
      i    BINARY_INTEGER := 0;

   BEGIN   
     
   OPEN cur_carg_masi_pre_empre;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH cur_carg_masi_pre_empre BULK COLLECT
        INTO lv_tab_emp_empre LIMIT rows;
      EXIT WHEN lv_tab_emp_empre.count = 0;    

      FOR i IN lv_tab_emp_empre.FIRST .. lv_tab_emp_empre.LAST LOOP

        lv_tab_emp_empre(i).pre_codi_clasi := EMP_FN_CALCU_CLASI(p_cod_prog, lv_tab_emp_empre(i).pre_nume_pedi_base);
        INSERT INTO emp_empre VALUES lv_tab_emp_empre(i);
      END LOOP;

    END LOOP;
    CLOSE cur_carg_masi_pre_empre;

   EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
          ls_sqlerrm := substr(sqlerrm,1,250);
          RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_PRE_EMPRE: '||ls_sqlerrm);
   END EMP_PR_PROCE_PRE_EMPRE; 
/******************************************************************************
 Descripcion         : EMP_FN_CALCU_CLASI
                       Proceso que calcula  la clasificación de la pre empresaria
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 26/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:

     p_cod_prog        : Código de Programa
     p_num_pedi_base   : Pedido base     
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramírez
********************************************************************************/ 
  FUNCTION EMP_FN_CALCU_CLASI(
    p_cod_prog                    IN   emp_progr.cod_prog%TYPE,
    p_num_pedi_base               IN   emp_carga_pre_empre.num_pedi_base%TYPE
    )
  RETURN NUMBER
  IS
      lv_cod_clasi      NUMBER;
  BEGIN
      SELECT ec.cod_clasi  into lv_cod_clasi FROM emp_clasi ec
      WHERE ec.cod_prog = p_cod_prog
      AND ec.num_pedi_desd <= p_num_pedi_base
      AND ec.num_pedi_hast >= p_num_pedi_base;
      
      return lv_cod_clasi;      
  EXCEPTION
    WHEN OTHERS THEN
       RETURN NULL;      
  END EMP_FN_CALCU_CLASI;

/******************************************************************************
 Descripcion         : EMP_PR_PROCE_BAJAS_EMPRE
                       Proceso que da de baja a las empresarias
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 13/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psCodigoPrograma : Codigo Programa
     
     psUsuario        : Usuario
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
  PROCEDURE EMP_PR_PROCE_BAJAS_EMPRE(psCodigoCliente   VARCHAR2,
                                     psMotivoBaja      VARCHAR2,
                                     psUsuario         VARCHAR2) IS

   lv_camp_actual bas_ctrl_fact.cod_peri%type;                              
                                          
  BEGIN                                         
     -- Obtiene la campaña de proceso
      begin
        select cf.cod_peri 
          into lv_camp_actual
          from bas_ctrl_fact cf
         where cf.sta_camp = '0'
           and cf.ind_camp_act = '1';
      exception
        when no_data_found then
          lv_camp_actual := null;
        when others then
          lv_camp_actual := null;
      end;  
  
      IF lv_camp_actual is not null THEN
           
         lv_camp_actual := cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(lv_camp_actual,-1);   
       
         -- Se da de baja en la tabla de empresarias 
         update emp_empre ee
            set ee.ind_baja = '1',
                ee.cod_moti_baja = psMotivoBaja,
                ee.usu_modi_baja = psUsuario,
                ee.fec_modi_baja = sysdate,
                ee.cod_peri_baja = lv_camp_actual
            where ee.cod_clie = psCodigoCliente ;    
          
          -- Se rompe el vinculo a partir del ultimo dia de la campaña activa         
          update mae_clien_vincu mv
             set mv.fec_hast = (select cp.fec_fina from cra_perio cp where cp.val_nomb_peri like '%' || lv_camp_actual || '%'),
                 mv.fec_ulti_actu = sysdate,
                 mv.usu_modi = psUsuario
           where mv.tivc_oid_tipo_vinc in (select tv.oid_tipo_vinc 
                                             from mae_tipo_vincu tv 
                                             where tv.cod_tipo_vinc = '07')
             and mv.clie_oid_clie_vnte = gen_pkg_gener.gen_fn_devuelve_id_cliente(psCodigoCliente);
           
           ----
           update emp_clien_vincu ev
              set ev.fec_hast = (select cp.fec_fina from cra_perio cp where cp.val_nomb_peri like '%' || lv_camp_actual || '%'),
                  ev.cod_peri_fin = lv_camp_actual,
                  ev.fec_ulti_actu = sysdate,
                  ev.usu_modi = psUsuario
            where ev.cod_clie_vnte = psCodigoCliente
              and ev.cod_peri_fin is null;
      
      END IF;
       
  EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_BAJAS_EMPRE ' || ls_sqlerrm);

  end EMP_PR_PROCE_BAJAS_EMPRE;

/******************************************************************************
 Descripcion         : EMP_PR_PROCE_REASI_EMPRE
                       Proceso que reasigna la empresaria a una consultora
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 26/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:

     psUsuario        : Usuario
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
   PROCEDURE EMP_PR_PROCE_REASI_EMPRE(psCodigoConsultora         VARCHAR2,
                                     psCodigoNuevaEmprendedora  VARCHAR2,
                                     psMotivoReasignacion       VARCHAR2,
                                     psUsuario                  VARCHAR2) IS

   lv_camp_actual bas_ctrl_fact.cod_peri%type;

  BEGIN

      -- Obtiene la campaña de proceso
      begin
        select cf.cod_peri
          into lv_camp_actual
          from bas_ctrl_fact cf
         where cf.sta_camp = '0'
           and cf.ind_camp_act = '1';
      exception
        when no_data_found then
          lv_camp_actual := null;
        when others then
          lv_camp_actual := null;
      end;

      IF lv_camp_actual is not null THEN

         -- En caso tenga otro vinculo de madre/hija, se finaliza en
         -- el ultimo dia de la campaña anterior
         update mae_clien_vincu mv
           set mv.fec_hast = (select cp.fec_fina
                               from cra_perio cp
                              where cp.val_nomb_peri like '%' ||
                                           (CUP_PKG_PROG_NUEVAS.gen_fn_dev_nsgte_campa(lv_camp_actual,-1))|| '%')
         where mv.clie_oid_clie_vndo = (select m.oid_clie from mae_clien m where m.cod_clie = psCodigoConsultora)
           and mv.tivc_oid_tipo_vinc in (select tv.oid_tipo_vinc
                                           from mae_tipo_vincu tv
                                          where tv.cod_tipo_vinc = '07');
                                          
         update emp_clien_vincu ev
            set ev.fec_hast = (select cp.fec_fina
                               from cra_perio cp
                              where cp.val_nomb_peri like '%' ||
                                           (CUP_PKG_PROG_NUEVAS.gen_fn_dev_nsgte_campa(lv_camp_actual,-1))|| '%'),
                 cod_peri_fin = (CUP_PKG_PROG_NUEVAS.gen_fn_dev_nsgte_campa(lv_camp_actual,-1)),
                 usu_modi = psUsuario,
                 val_moti_reas = psMotivoReasignacion
           where ev.cod_clie_vndo = psCodigoConsultora
             and ev.cod_peri_fin is null;
           
         -- por integridad, se borra el vinculo en caso exista para los mismos 
         -- vinculante y vinculado, para el tipo de vinculo 07
         delete mae_clien_vincu mv
         where mv.CLIE_OID_CLIE_VNTE = (select m.oid_clie from mae_clien m where m.cod_clie = psCodigoNuevaEmprendedora)
           and mv.CLIE_OID_CLIE_VNDO = (select m.oid_clie from mae_clien m where m.cod_clie = psCodigoConsultora)
           and mv.TIVC_OID_TIPO_VINC = (select tv.oid_tipo_vinc
                                          from mae_tipo_vincu tv
                                         where tv.cod_tipo_vinc = '07');
         
         -- Se crea el nuevo vinculo
         -- Se inserta el vinculo en la tabla de MAE
           insert into mae_clien_vincu
                  (oid_clie_vinc,
                   fec_desd,
                   fec_hast,
                   clie_oid_clie_vnte,
                   clie_oid_clie_vndo,
                   tivc_oid_tipo_vinc,
                   ind_vinc_ppal,
                   fec_ulti_actu,
                   usu_modi)
                values
                  (MAE_CVIN_SEQ.NEXTVAL, -- sequence
                   (select cp.fec_inic
                               from cra_perio cp
                              where cp.val_nomb_peri like '%' || lv_camp_actual || '%'),
                   NULL,
                   (select m.oid_clie from mae_clien m where m.cod_clie = psCodigoNuevaEmprendedora),
                   (select m.oid_clie from mae_clien m where m.cod_clie = psCodigoConsultora),
                   (select tv.oid_tipo_vinc
                      from mae_tipo_vincu tv
                     where tv.cod_tipo_vinc = '07'),
                   '0',
                   sysdate,
                   psUsuario);

           -- Se inserta el vinculo en la tabla de Empresarias
               insert into emp_clien_vincu(fec_desd,
                                           fec_hast,
                                           cod_clie_vnte,
                                           cod_clie_vndo,
                                           fec_ulti_actu,
                                           usu_modi,
                                           cod_peri_ini,
                                           cod_peri_fin,
                                           val_moti_reas)
                   values ((select cp.fec_inic
                               from cra_perio cp
                              where cp.val_nomb_peri like '%' || lv_camp_actual || '%'),
                           null,
                           psCodigoNuevaEmprendedora,
                           psCodigoConsultora,
                           sysdate,
                           psUsuario,
                           lv_camp_actual,
                           null,
                           psMotivoReasignacion);

      END IF;


  EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_BAJAS_EMPRE ' || ls_sqlerrm);

  end EMP_PR_PROCE_REASI_EMPRE;

/******************************************************************************
 Descripcion         : EMP_PR_PROCE_VINCU_NUEVA_REACT
                       Proceso que crea los vinculos a las nuevas y reactivadas
                       con su socia o pre-socia correspondiente
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 29/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psCodigoPrograma : Codigo Programa
     psUsuario        : Usuario
 ------------------------------------------------------------------------------    
 Caso de Uso : 2.1 Creacion de consultoras 
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
  PROCEDURE EMP_PR_PROCE_VINCU_NUEVA_REACT(psCodigoPrograma  VARCHAR2,
                                           psUsuario         VARCHAR2) as
  
  cursor curvinculos(psoidperiodo number) is
    -----------------------------------------------------------
    -- Obtiene todas las consultoras nuevas y reactivadas de 
    -- las zonas que pertenecen a un programa en particular
    -----------------------------------------------------------
    SELECT m.cod_clie, --HIP_PKG_CONSU.HIP_FN_DEVUELVE_TIPO_CONSUL('PE',m.oid_clie),
           m.oid_clie,
           r.cod_regi, 
           z.cod_zona 
      FROM mae_clien m,
           MAE_CLIEN_HISTO_ESTAT mh,
           mae_clien_unida_admin ua,
           zon_terri_admin       ta,
           zon_terri             t,
           zon_secci             s,
           zon_zona              z,
           zon_regio             r,
           emp_progr_unida_admin uae
      where ua.ind_acti = 1
      and ua.perd_oid_peri_fin is null
      and ua.ztad_oid_terr_admi = ta.oid_terr_admi
      and ta.terr_oid_terr = t.oid_terr
      and ta.zscc_oid_secc = s.oid_secc
      and s.zzon_oid_zona = z.oid_zona
      and z.zorg_oid_regi = r.oid_regi
      and m.oid_clie = ua.clie_oid_clie
      and uae.cod_prog = psCodigoPrograma --'001'
      and uae.cod_regi = r.cod_regi
      and uae.cod_zona = z.cod_zona
      -- nueva - primer pedido para la campaña actual
      and m.oid_clie = mh.clie_oid_clie    
      and mh.esta_oid_esta_clie in (2,8)
      and mh.perd_oid_peri = psoidperiodo --(select cp.oid_peri from cra_perio cp where cp.val_nomb_peri like '%201306%') 
      -- si pertenece a una region cerrada
      and r.cod_regi in (select distinct zr.cod_regi
                           from fac_contr_cierr cc,
                                FAC_TIPOS_CIERR tc,
                               -- cra_perio cp,
                                zon_regio zr
                          where cc.tcie_oid_tipo_cier = tc.oid_tipo_cier
                            and tc.cod_tipo_cier = 'R' 
                            and cc.val_resu_proc = 'OK'    
                            and zr.oid_regi = cc.zorg_oid_regi
                            and cc.perd_oid_peri = psoidperiodo-- cp.oid_peri
                            /*and cp.val_nomb_peri like '%201306%'*/)
      -- Si tiene algun vinculo madre/hija vigente, no de modifica
      and not exists (select null
                        from mae_clien_vincu mv 
                       where mv.tivc_oid_tipo_vinc in (select tv.oid_tipo_vinc 
                                                         from mae_tipo_vincu tv 
                                                        where tv.cod_tipo_vinc = '07')
                         and mv.clie_oid_clie_vndo = m.oid_clie
                         and nvl(mv.fec_hast,sysdate) >= sysdate);
   /* SELECT m.cod_clie, 
           m.oid_clie,
           r.cod_regi, 
           z.cod_zona 
      FROM MAE_CLIEN_DATOS_ADICI da,
           MAE_ESTAT_CLIEN       ec,
           mae_clien m,
           mae_clien_unida_admin ua,
           zon_terri_admin       ta,
           zon_terri             t,
           zon_secci             s,
           zon_zona              z,
           zon_regio             r,
           emp_progr_unida_admin uae
      where da.esta_oid_esta_clie = ec.oid_esta_clie
      and m.oid_clie = da.clie_oid_clie
      and ec.cod_esta_clie in ('01','07')
      and da.ind_acti = '1' -- para que solo tome las reactivadas 
      and ua.ind_acti = 1
      and ua.perd_oid_peri_fin is null
      and ua.ztad_oid_terr_admi = ta.oid_terr_admi
      and ta.terr_oid_terr = t.oid_terr
      and ta.zscc_oid_secc = s.oid_secc
      and s.zzon_oid_zona = z.oid_zona
      and z.zorg_oid_regi = r.oid_regi
      and m.oid_clie = ua.clie_oid_clie
      and da.clie_oid_clie = ua.clie_oid_clie
      and uae.cod_prog = psCodigoPrograma --'001'
      and uae.cod_regi = r.cod_regi
      and uae.cod_zona = z.cod_zona
      -- Si tiene algun vinculo madre/hija vigente, no de modifica
      and not exists (select null
                        from mae_clien_vincu mv 
                       where mv.tivc_oid_tipo_vinc in (select tv.oid_tipo_vinc 
                                                         from mae_tipo_vincu tv 
                                                        where tv.cod_tipo_vinc = '07')
                         and mv.clie_oid_clie_vndo = m.oid_clie
                         and nvl(mv.fec_hast,sysdate) >= sysdate);*/
  
       TYPE t_cod_clie      IS TABLE OF mae_clien.cod_clie%TYPE;
       TYPE t_oid_clie      IS TABLE OF mae_clien.oid_clie%TYPE;
       TYPE t_cod_regi      IS TABLE OF zon_regio.cod_regi%TYPE; 
       TYPE t_cod_zona      IS TABLE OF zon_zona.cod_zona%TYPE;
      
       v_cod_clie        t_cod_clie;
       v_oid_clie        t_oid_clie;
       v_cod_regi        t_cod_regi;
       v_cod_zona        t_cod_zona;
       
       rows NATURAL := 1000; -- Number of rows to process at a time
       i    BINARY_INTEGER := 0;
       
       ls_oid_recomendante        mae_clien_vincu.clie_oid_clie_vnte%type;
       lv_oid_madre_recomendante  mae_clien_vincu.clie_oid_clie_vnte%type;

       lv_camp_actual bas_ctrl_fact.cod_peri%type; 
       lv_oid_peri cra_perio.oid_peri%type;
  BEGIN
     
     -- Obtiene la campaña de proceso
      begin
        select cf.cod_peri , cp.oid_peri
          into lv_camp_actual,
               lv_oid_peri
          from bas_ctrl_fact cf,
               seg_perio_corpo pc,
               cra_perio cp
         where cf.sta_camp = '0'
           and cf.ind_camp_act = '1'                         
           and pc.cod_peri = cf.cod_peri
           and cp.peri_oid_peri = pc.oid_peri;
      exception
        when no_data_found then
          lv_camp_actual := null;
          lv_oid_peri := null;
        when others then
          lv_camp_actual := null;
          lv_oid_peri := null;
      end;  
  
     OPEN curvinculos(lv_oid_peri);
      LOOP
        FETCH curvinculos BULK COLLECT INTO v_cod_clie,
                                            v_oid_clie,
                                            v_cod_regi,
                                            v_cod_zona          
                                       LIMIT rows;
                                      
          IF v_cod_clie.count > 0 THEN  
             FOR i IN v_cod_clie.first .. v_cod_clie.last
          LOOP

             IF v_cod_clie IS NOT NULL THEN
               ---------------------------- 
               -- Obtiene su recomendante  
               ----------------------------                             
               begin                                
                 select mv.clie_oid_clie_vnte --, rdo.cod_clie, rte.cod_clie, gen_pkg_gener.gen_fn_clien_datos(rdo.cod_clie,'COD_ZONA') , gen_pkg_gener.gen_fn_clien_datos(rte.cod_clie,'COD_ZONA')
                  into ls_oid_recomendante 
                  from mae_clien_vincu mv,
                       mae_clien rdo,
                       mae_clien rte 
                  where mv.clie_oid_clie_vndo = v_oid_clie(i)
                  and mv.tivc_oid_tipo_vinc in (select tv.oid_tipo_vinc 
                                                                 from mae_tipo_vincu tv 
                                                                where tv.cod_tipo_vinc = '03')
                  and rdo.oid_clie = mv.clie_oid_clie_vndo
                  and rte.oid_clie = mv.clie_oid_clie_vnte
                  -- que sean de la misma zona de la recomendada
                  and gen_pkg_gener.gen_fn_clien_datos(rdo.cod_clie,'COD_ZONA') = gen_pkg_gener.gen_fn_clien_datos(rte.cod_clie,'COD_ZONA')
                  -- que sea socia o pre-socia
                  and exists (select null
                                from emp_empre emp
                                where emp.ind_empr in ('1','2')
                                and emp.cod_clie = rte.cod_clie
                                and nvl(emp.ind_baja,'0') != '1');
                                
               exception
                 when no_data_found then
                   ls_oid_recomendante := null;
               end;
                 
               if ls_oid_recomendante is not null then
                 ---------------------------------------------------------------- 
                 -- existe recomendante en la misma zona y es socia o pre-socia
                 ----------------------------------------------------------------
                begin
                insert into mae_clien_vincu
                  (oid_clie_vinc,
                   fec_desd,
                   fec_hast,
                   clie_oid_clie_vnte,
                   clie_oid_clie_vndo,
                   tivc_oid_tipo_vinc,
                   ind_vinc_ppal,
                   fec_ulti_actu,
                   usu_modi)
                values
                  (MAE_CVIN_SEQ.NEXTVAL, -- sequence
                   trunc(SYSDATE),
                   NULL,
                   ls_oid_recomendante,
                   v_oid_clie(i),
                   (select tv.oid_tipo_vinc 
                      from mae_tipo_vincu tv 
                     where tv.cod_tipo_vinc = '07'), 
                   '0', 
                   sysdate,
                   psUsuario);
                   
                   -- si inserta bien, se inserta en la tabla
                   -- de vinculos de empresarias
                    insert into emp_clien_vincu(fec_desd,
                                           fec_hast,
                                           cod_clie_vnte,
                                           cod_clie_vndo,
                                           fec_ulti_actu,
                                           usu_modi,
                                           cod_peri_ini,
                                           cod_peri_fin,
                                           val_moti_reas)
                   values (sysdate,
                           null,
                           (select cod_clie from mae_clien where oid_clie = ls_oid_recomendante),
                           (select cod_clie from mae_clien where oid_clie = v_oid_clie(i)),
                           sysdate,
                           psUsuario,
                           lv_camp_actual,
                           null,
                           null);                   
                     
                exception
                   when others then
                     null;
                 end;
                 
               else
                  ---------------------------------------------------------
                  -- en caso el recomendante no pertenece a la misma zona  
                  -- o si no es socia o pre-socia
                  ---------------------------------------------------------
                  -- Obtiene a su recomendante
                  ------------------------------
                  begin
                    select mv.clie_oid_clie_vnte --, rdo.cod_clie, rte.cod_clie, gen_pkg_gener.gen_fn_clien_datos(rdo.cod_clie,'COD_ZONA') , gen_pkg_gener.gen_fn_clien_datos(rte.cod_clie,'COD_ZONA')
                      into ls_oid_recomendante 
                      from mae_clien_vincu mv,
                           mae_clien rdo,
                           mae_clien rte 
                      where mv.clie_oid_clie_vndo = v_oid_clie(i)
                      and mv.tivc_oid_tipo_vinc in (select tv.oid_tipo_vinc 
                                                                     from mae_tipo_vincu tv 
                                                                    where tv.cod_tipo_vinc = '03')
                      and rdo.oid_clie = mv.clie_oid_clie_vndo
                      and rte.oid_clie = mv.clie_oid_clie_vnte;
                      
                    -------------------------------------------------------------------  
                    -- se evalua si la recomendante tiene una madre socia o pre-socia
                    -- y pertenece a la misma zona de la nueva/reactivada
                    -------------------------------------------------------------------
                    select mv.clie_oid_clie_vnte--, rdo.cod_clie, rte.cod_clie, gen_pkg_gener.gen_fn_clien_datos(rdo.cod_clie,'COD_ZONA') , gen_pkg_gener.gen_fn_clien_datos(rte.cod_clie,'COD_ZONA')
                      into lv_oid_madre_recomendante -- oid madre de la recomendante
                      from mae_clien_vincu mv,
                           mae_clien rdo,
                           mae_clien rte ,
                           emp_empre ee
                      where mv.clie_oid_clie_vndo = ls_oid_recomendante
                      and mv.tivc_oid_tipo_vinc in (select tv.oid_tipo_vinc 
                                                                     from mae_tipo_vincu tv 
                                                                    where tv.cod_tipo_vinc = '07')
                      and rdo.oid_clie = mv.clie_oid_clie_vndo
                      and rte.oid_clie = mv.clie_oid_clie_vnte
                      and ee.cod_clie = rte.cod_clie
                      and mv.fec_hast is null
                      and nvl(ee.ind_baja,'0') != '1'                      
                      -- que sean de la misma zona de la recomendada
                      and gen_pkg_gener.gen_fn_clien_datos(v_cod_clie(i),'COD_ZONA') = gen_pkg_gener.gen_fn_clien_datos(rte.cod_clie,'COD_ZONA');

                      ------------------------------------------------------------------
                      -- si existe, se crea el vinculo con la madre de la recomendante
                      ------------------------------------------------------------------
                      begin
                      insert into mae_clien_vincu
                        (oid_clie_vinc,
                         fec_desd,
                         fec_hast,
                         clie_oid_clie_vnte,
                         clie_oid_clie_vndo,
                         tivc_oid_tipo_vinc,
                         ind_vinc_ppal,
                         fec_ulti_actu,
                         usu_modi)
                      values
                        (MAE_CVIN_SEQ.NEXTVAL, -- sequence
                         trunc(SYSDATE),
                         NULL,
                         lv_oid_madre_recomendante,
                         v_oid_clie(i),
                         (select tv.oid_tipo_vinc 
                            from mae_tipo_vincu tv 
                           where tv.cod_tipo_vinc = '07'), 
                         '0', 
                         sysdate,
                         psUsuario);
                         
                   -- si inserta bien, se inserta en la tabla
                   -- de vinculos de empresarias
                    insert into emp_clien_vincu(fec_desd,
                                           fec_hast,
                                           cod_clie_vnte,
                                           cod_clie_vndo,
                                           fec_ulti_actu,
                                           usu_modi,
                                           cod_peri_ini,
                                           cod_peri_fin,
                                           val_moti_reas)
                   values (sysdate,
                           null,
                           (select cod_clie from mae_clien where oid_clie = lv_oid_madre_recomendante),
                           (select cod_clie from mae_clien where oid_clie = v_oid_clie(i)),
                           sysdate,
                           psUsuario,
                           lv_camp_actual,
                           null,
                           null);

                         
                      exception
                       when others then
                         null;
                     end;
                  exception
                    when no_data_found then
                      ----------------------------------------------------
                      -- no se hace nada, queda para reasignacion manual
                      ----------------------------------------------------
                      null;
                  end;
                                    
               end if;
                                        
             END IF;
             
          END LOOP; 
          END IF;
         
         EXIT WHEN curvinculos%NOTFOUND;
    END LOOP;
    CLOSE curvinculos;

  EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_VINCU_NUEVA_REACT ' || ls_sqlerrm);

  end EMP_PR_PROCE_VINCU_NUEVA_REACT;

/******************************************************************************
 Descripcion         : EMP_PR_VALID_REASI_MASIV
                       Proceso que crea los vinculos a las nuevas y reactivadas
                       con su socia o pre-socia correspondiente
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 29/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psCodigoPrograma : Codigo Programa
     psUsuario        : Usuario
 ------------------------------------------------------------------------------    
 Caso de Uso : 2.1 Creacion de consultoras 
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
  PROCEDURE EMP_PR_VALID_REASI_MASIV (psUsuario   VARCHAR2,
                                      p_cant_erro OUT VARCHAR2) IS
    
  cursor curreasignar is

    select rm.val_fila,
           rm.cod_clie,
           rm.cod_nuev_empr
      from EMP_TEMP_REAS_MASI rm;

       TYPE t_val_fila      IS TABLE OF EMP_TEMP_REAS_MASI.Val_Fila%TYPE;
       TYPE t_cod_clie      IS TABLE OF EMP_TEMP_REAS_MASI.Cod_Clie%TYPE;
       TYPE t_cod_nuev_empr IS TABLE OF EMP_TEMP_REAS_MASI.Cod_Nuev_Empr%TYPE;
    
       v_val_fila        t_val_fila;
       v_cod_clie        t_cod_clie;
       v_cod_nuev_empr   t_cod_nuev_empr;
       
       rows NATURAL := 1000; -- Number of rows to process at a time
       i    BINARY_INTEGER := 0;
  
       lv_cod_zona_cliente   zon_zona.cod_zona%type;
       lv_cod_zona           zon_zona.cod_zona%type;
       
   BEGIN
     
   OPEN curreasignar;
      LOOP
        FETCH curreasignar BULK COLLECT INTO  v_val_fila     ,
                                              v_cod_clie     ,
                                              v_cod_nuev_empr
                                        LIMIT rows;

          IF v_val_fila.count > 0 THEN
             FOR i IN v_val_fila.first .. v_val_fila.last
          LOOP
             -- Verifica que el codigo de la consultora exista
             begin
                select gen_pkg_gener.gen_fn_clien_datos(m.cod_clie,'COD_ZONA')
                  into lv_cod_zona_cliente
                  from mae_clien m                  
                  where m.cod_clie = v_cod_clie(i); 
                  
                 -- verifica que el codigo de la empresaria exista y
                 -- que pertenezca al programa de socias
                 begin
                   select gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,'COD_ZONA')                        
                     into lv_cod_zona
                     from mae_clien mc
                    where mc.cod_clie = v_cod_nuev_empr(i)
                      and (select ee.ind_empr
                             from emp_empre ee 
                            where ee.cod_clie = mc.cod_clie) is not null;
                     -- Valida que sean de la misma zona
                     if lv_cod_zona != lv_cod_zona_cliente then
                       update EMP_TEMP_REAS_MASI rm 
                          set rm.des_erro = 'Las zonas de las consultoras no coinciden'
                        where rm.val_fila = v_val_fila(i);
                     else
                       /*update EMP_TEMP_REAS_MASI rm 
                          set rm.des_erro = '-'
                        where rm.val_fila = v_val_fila(i);*/
                       EMP_PR_PROCE_REASI_EMPRE(v_cod_clie(i),
                                                v_cod_nuev_empr(i),
                                                '007', -- revisar
                                                psUsuario);
                     
                     end if;
                            
                 exception
                   when no_data_found then
                     -- Si no devuelve datos, no existe el codigo de la consultora
                     -- en el programa de socias
                     update EMP_TEMP_REAS_MASI rm 
                        set rm.des_erro = 'El codigo ' || v_cod_nuev_empr(i) || ' no existe en el programa de socias empresarias'
                      where rm.val_fila = v_val_fila(i); 
                        
                 end; 
             exception
               when no_data_found then
                 -- Si no devuelve datos, no existe el codigo de la consultora
                 update EMP_TEMP_REAS_MASI rm 
                    set rm.des_erro = 'El codigo ' || v_cod_clie(i) || ' no existe en el maestro de consultoras'
                  where rm.val_fila = v_val_fila(i); 
                    
             end;
              

          END LOOP;
          END IF;

          -- devuelve la cantidad de errores
          SELECT TO_CHAR(COUNT(*))
            INTO p_cant_erro 
            FROM EMP_TEMP_REAS_MASI t
           where t.des_erro is not null;

         EXIT WHEN curreasignar%NOTFOUND;
    END LOOP;
    CLOSE curreasignar;

   EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_VALID_REASI_MASIV: '||ls_sqlerrm);

  END EMP_PR_VALID_REASI_MASIV;
  
  /******************************************************************************
 Descripcion         : EMP_PR_PROCE_UPDAT_DATOS_CLIEN
                       Proceso que actualiza los datos de telefono celular, fijo
                       y email de la consultora.
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 16/01/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psOidCliente     : Oid Cliente
     psUsuario        : Usuario
     psDato           : Valor a modificar
     psCampo          : Campo modificar
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
********************************************************************************/
  PROCEDURE EMP_PR_PROCE_UPDAT_DATOS_CLIEN(psOidCliente VARCHAR2,
                                           psDato VARCHAR2,
                                           psCampo VARCHAR2,                                           
                                           psUsuario VARCHAR2,
                                           psIndOrigen VARCHAR2) IS
                                           
  BEGIN
  
    UPDATE MAE_CLIEN_COMUN CC
       SET CC.VAL_TEXT_COMU = psDato,
           CC.USU_MODI = psUsuario,
           CC.FEC_ULTI_ACTU = sysdate,
           CC.Ind_Orig_Regi = psIndOrigen
     WHERE CC.TICM_OID_TIPO_COMU =
           (SELECT TC.OID_TIPO_COMU
              FROM MAE_TIPO_COMUN TC
             WHERE TC.COD_TIPO_COMU = psCampo)
       AND CC.CLIE_OID_CLIE = psOidCliente;
       
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_UPDAT_DATOS_CLIEN: '||ls_sqlerrm);
                                           
  END EMP_PR_PROCE_UPDAT_DATOS_CLIEN;
  
 /******************************************************************************
 Descripcion         : EMP_FN_CUENT_PEDID
                       Proceso que cuenta los pedidos en un periodo
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 17/01/2012
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
 /********************************************************************************/ 
 FUNCTION EMP_FN_CUENT_PEDID(psCliente  emp_empre.cod_clie%type,
                             psPeriodo  bas_ctrl_fact.cod_peri%type) RETURN NUMBER
 IS
   ln_cuent_pedid            NUMBER := 0;
   BEGIN
  
        SELECT count(*) INTO ln_cuent_pedid     
        FROM ped_tipo_solic_pais tsp,
             ped_tipo_solic      ts,
             ped_solic_cabec     ca,
             cra_perio           cra,
             seg_perio_corpo     spc,
             mae_clien           mc
       WHERE ca.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
         AND ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
         AND cra.oid_peri = ca.perd_oid_peri
         AND cra.peri_oid_peri = spc.oid_peri
         AND spc.cod_peri = psPeriodo
         AND mc.oid_clie = ca.clie_oid_clie
         AND mc.cod_clie = psCliente
         and ca.grpr_oid_grup_proc = 5
         AND ts.cod_tipo_soli = 'SOC';
         
         return ln_cuent_pedid;
         
   EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_FN_CUENT_PEDID: '||ls_sqlerrm);
     return 0;          
         
 END EMP_FN_CUENT_PEDID;

 /******************************************************************************
 Descripcion         : EMP_FN_VALID_RECO_EFEC
                       Proceso que valida si es recomendacion efectiva en el periodo
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 17/01/2012
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
 /********************************************************************************/   
 FUNCTION EMP_FN_VALID_RECO_EFEC(psClienteRecomendante  emp_empre.cod_clie%type,
                                 psClienteRecomendada  emp_empre.cod_clie%type,
                                 psPrimerPedido  bas_ctrl_fact.cod_peri%type) RETURN NUMBER
 IS
    BEGIN     
      --Si la recomendante y la recomendada paso pedido en la campaña anterior (primer pedido) y la actual (segundo pedido)
      IF ( (EMP_FN_CUENT_PEDID(psClienteRecomendante,psPrimerPedido) > 0)
            AND (EMP_FN_CUENT_PEDID(psClienteRecomendada,psPrimerPedido) > 0)    ) THEN
            
                IF (   (EMP_FN_CUENT_PEDID(psClienteRecomendante,cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPrimerPedido,1)) > 0)
                     AND  (EMP_FN_CUENT_PEDID(psClienteRecomendada,cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPrimerPedido,1)) >0)   ) THEN
                  RETURN 1;
                END IF;
      END IF;
      
      RETURN 0;                                                                       
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_FN_VALID_RECO_EFEC: '||ls_sqlerrm);
     return 0;
      
 END EMP_FN_VALID_RECO_EFEC;                                 
                       
 /******************************************************************************
 Descripcion         : EMP_FN_CUENT_RECO_EFEC
                       Proceso que cuenta las recomendaciones efectivas en un rango
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 17/01/2012
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
********************************************************************************/                     
 FUNCTION EMP_FN_CUENT_RECO_EFEC(psPrograma               emp_empre.cod_prog%type,
                                 psPeriodo                bas_ctrl_fact.cod_peri%type,                                 
                                 psCliente                emp_empre.cod_clie%type,
                                 psPeriIni                bas_ctrl_fact.cod_peri%type,
                                 psPeriFin                bas_ctrl_fact.cod_peri%type,
                                 psUsuario                emp_empre.usu_regi%type,
                                 lnOid                    NUMBER) RETURN NUMBER
   IS
   CURSOR curRecdoPrimerPedido IS
  
   --Lista todas las recomendadas que hayan pasado segundo pedido en el rango 
   --Cuando la nueva pasa primer pedido y segundo pedido es una recomendacion efectiva en el Segundo Pedido.
    SELECT mc.cod_clie, sp.cod_peri --Reocomendada, periodo de primer pedido
      FROM mae_clien_vincu       mcv,
           mae_clien             mc,
           mae_clien_prime_conta mcp,
           cra_perio             cp,
           seg_perio_corpo       sp
     WHERE mc.oid_clie = mcp.clie_oid_clie
       AND sp.oid_peri = cp.peri_oid_peri
       AND cp.oid_peri = mcp.perd_oid_peri
       AND mc.oid_clie = mcv.clie_oid_clie_vndo 
       AND mcv.tivc_oid_tipo_vinc = 9 --Recomendada/Recomendante
       AND mcv.fec_desd < sysdate AND mcv.fec_hast > sysdate 
       AND mcv.clie_oid_clie_vnte = (select mc1.oid_clie from mae_clien mc1 where mc1.cod_clie = psCliente) 
       AND cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(sp.cod_peri,1) between psPeriIni and psPeriFin;
       
   TYPE t_cod_clie                  IS TABLE OF mae_clien.cod_clie%TYPE;      
   TYPE t_cod_peri                  IS TABLE OF bas_ctrl_fact.cod_peri%type; 
     
   v_cod_clie                       t_cod_clie;
   v_cod_peri                       t_cod_peri;

   lv_ind_empre                     emp_empre.ind_empr%type;
   ln_cont_reco_efec                NUMBER :=0;
       
   rows NATURAL := 1000; -- Number of rows to process at a time
   i    BINARY_INTEGER := 0;
     
   BEGIN

      OPEN curRecdoPrimerPedido;
      LOOP
        FETCH curRecdoPrimerPedido BULK COLLECT INTO v_cod_clie,
                                                     v_cod_peri          
                                                     LIMIT rows;
                                      
         IF v_cod_clie.count > 0 THEN 
            
           FOR i IN v_cod_clie.first .. v_cod_clie.last
           LOOP
             IF (EMP_FN_VALID_RECO_EFEC(psCliente,v_cod_clie(i),v_cod_peri(i)) = '1') THEN
                 --Si es recomendacion efectiva la cuento y guardo en una tabla temporal
                 --el campo ind_empre indica si la recomendad es emprendedora
                 ln_cont_reco_efec := ln_cont_reco_efec + 1;
                                
                 SELECT decode(count(*),0,0,1) into lv_ind_empre  -- 0:no es emprendedora,1: si es emprendedora
                 FROM emp_empre ee
                 WHERE ee.cod_clie = v_cod_clie(i)
                 AND ee.ind_empr = '2'; --emprendedora
                 
                 BEGIN
                        
                     INSERT INTO EMP_RECOM 
                   (oid_reco,
                     cod_prog,
                     cod_peri,
                     cod_clie_rcte,
                     cod_clie_rcda,
                     ind_empre,
                     cod_peri_pped,
                     tip_empre,
                     fec_proc,
                     usu_regi,
                     ind_esta,
                     ind_reco_efec)
                     VALUES(
                     lnOid,
                     psPrograma,
                     psPeriodo,
                     psCliente,
                     v_cod_clie(i),
                     lv_ind_empre,
                     v_cod_peri(i),                     
                     'R', --Regular
                     sysdate,
                     psUsuario,
                     'C', --Al inicio todas se cobran, a medida q se reasignen se cambia el indicador
                     '1' --es efectiva
                     );                
                 EXCEPTION
                    WHEN OTHERS THEN
                      --Si se cae por PK es un recomendacion ya contada no debe caer
                      dbms_output.put_line('codigo repetido');
                 END;
               
             END IF;
          
           END LOOP;                        
         END IF;
         EXIT WHEN curRecdoPrimerPedido%NOTFOUND;
      END LOOP;
      CLOSE curRecdoPrimerPedido;
      
      return ln_cont_reco_efec;
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_FN_CUENT_RECO_EFEC: '||ls_sqlerrm);
     return 0;
      
  END EMP_FN_CUENT_RECO_EFEC;
  
 /******************************************************************************
 Descripcion         : EMP_PR_PROCE_GUARD_ERROR_EMPRE
                       Proceso que guarda los errores de la tabla temporal a la 
                       tabla final.
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 17/01/2012
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
 /********************************************************************************/  
 PROCEDURE EMP_PR_PROCE_GUARD_ERROR_EMPRE( psPeriodo                emp_empre.cod_peri_baja%type,
                                           psPrograma               emp_empre.cod_prog%type,
                                           psCliente                emp_empre.cod_clie%type,
                                           psTipoEmprendedora       emp_empre.cod_tipo_empre%type,
                                           psUsuario                emp_empre.usu_regi%type)
  IS

      CURSOR curErroEmprendedora IS  
    SELECT gtt.cod_erro, gtt.des_erro
      FROM gtt_emp_error_requi_empre gtt;
       
   TYPE t_cod_erro                  IS TABLE OF gtt_emp_error_requi_empre.cod_erro%TYPE;      
   TYPE t_des_erro                  IS TABLE OF gtt_emp_error_requi_empre.des_erro%type; 
     
   v_cod_erro                       t_cod_erro;
   v_des_erro                       t_des_erro;   
       
   rows NATURAL := 1000; -- Number of rows to process at a time
   i    BINARY_INTEGER := 0;
     
   BEGIN
  
      OPEN curErroEmprendedora;
      LOOP
        FETCH curErroEmprendedora BULK COLLECT INTO v_cod_erro,
                                                    v_des_erro          
                                                    LIMIT rows;
                                      
         IF v_cod_erro.count > 0 THEN  
           FOR i IN v_cod_erro.first .. v_cod_erro.last
           LOOP
             INSERT INTO emp_error_empre 
             (cod_progr,
             cod_peri,
             cod_clie,
             cod_tipo_empre,
             cod_erro,
             des_erro,
             fec_proc,
             usu_regi)
             VALUES
             (psPrograma,
             psPeriodo,
             psCliente,
             psTipoEmprendedora,
             v_cod_erro(i),
             v_des_erro(i),
             sysdate,
             psUsuario
             );
          
           END LOOP; 
         END IF;
         EXIT WHEN curErroEmprendedora%NOTFOUND;
      END LOOP;
      CLOSE curErroEmprendedora; 
   EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_GUARD_ERROR_EMPRE: '||ls_sqlerrm);
                                                        
  END EMP_PR_PROCE_GUARD_ERROR_EMPRE;

 /******************************************************************************
 Descripcion         : EMP_PR_PROCE_REASI_RECOM
                       Proceso que reasgina las recomendadas efectivas y e identifica
                       a las recomendadas normales de la campaña anterio y las nuevas
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 17/01/2012
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
 /********************************************************************************/  
  PROCEDURE EMP_PR_PROCE_REASI_RECOM(psPrograma     emp_empre.cod_prog%type,
                                        psCliente      emp_empre.cod_clie%type,
                                        psPeriodo      bas_ctrl_fact.cod_peri%type,
                                        psUsuario      emp_empre.usu_regi%type,
                                        psNumRecoEfec  NUMBER,
                                        lnOid          NUMBER)
    IS
    
 --Recomendaciones efectivas en el rango
 CURSOR curRecoEfectivas IS
    SELECT rc.cod_clie_rcda
    FROM emp_recom rc
    WHERE rc.oid_reco = lnOid
    and rc.ind_reco_efec='1'
    and rc.cod_prog=psPrograma
    and rc.cod_peri=psPeriodo
    and rc.cod_clie_rcte=psCliente
    ORDER BY rc.ind_empre desc;    --Priorizando las recomendaciones efectivas que sean emprendedoras
    
  --Recomendaciones de periodo anterior y nuevas de periodo actual    
  CURSOR curRecoPeriAnte IS      
  SELECT mc.cod_clie, sp.cod_peri --Reocomendada, periodo de primer pedido
      FROM mae_clien_vincu       mcv,
           mae_clien             mc,
           mae_clien_prime_conta mcp,
           cra_perio             cp,
           seg_perio_corpo       sp
     WHERE mc.oid_clie = mcp.clie_oid_clie
       AND sp.oid_peri = cp.peri_oid_peri
       AND cp.oid_peri = mcp.perd_oid_peri
       AND mc.oid_clie = mcv.clie_oid_clie_vndo
       AND mcv.tivc_oid_tipo_vinc = 9 --Recomendada/Recomendante
       AND mcv.fec_desd < sysdate AND mcv.fec_hast > sysdate
       AND mcv.clie_oid_clie_vnte = (select mc1.oid_clie from mae_clien mc1 where mc1.cod_clie = psCliente)
       AND sp.cod_peri between cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-1) and psPeriodo;
       
   TYPE t_cod_clie                  IS TABLE OF emp_empre.cod_clie%TYPE;  
    TYPE t_cod_peri                 IS TABLE OF emp_empre.cod_peri_baja%TYPE; 
     
   v_cod_clie                       t_cod_clie; 
   v_cod_peri                       t_cod_peri;
     
   ln_cont_reco_efec                NUMBER:=0;    
   rows NATURAL := 1000; 
   i    BINARY_INTEGER := 0;
    
    BEGIN
      
        SELECT COUNT(*) INTO ln_cont_reco_efec 
          FROM emp_recom rc
          WHERE rc.oid_reco = lnOid
          and rc.ind_reco_efec='1'
          and rc.cod_prog=psPrograma
          and rc.cod_peri=psPeriodo
          and rc.cod_clie_rcte=psCliente
         ORDER BY rc.ind_empre desc;    
       
       --Cobra las recomendaciones efectivas segun parametro y reasigna las adicionales
       IF (ln_cont_reco_efec > psNumRecoEfec) THEN
          OPEN curRecoEfectivas;
          LOOP
            FETCH curRecoEfectivas BULK COLLECT INTO v_cod_clie         
                                                     LIMIT rows;
                                          
             IF v_cod_clie.count > 0 THEN  
               FOR i IN v_cod_clie.first .. v_cod_clie.last
               LOOP
                    EMP_PR_PROCE_REASI_EMPRE(v_cod_clie(i), psCliente,'RECO_EFEC_ADICI',psUsuario);                                    
                    ln_cont_reco_efec:=ln_cont_reco_efec-1;
                    
                    --Indicamos recomendaciones efectivas reasignadas
                    UPDATE emp_recom rc 
                      SET rc.ind_esta = 'R' --Reasignar
                      WHERE rc.oid_reco = lnOid
                        and rc.ind_reco_efec='1'
                        and rc.cod_prog=psPrograma
                        and rc.cod_peri=psPeriodo
                        and rc.cod_clie_rcte=psCliente
                        and rc.cod_clie_rcda = v_cod_clie(i);
                    
               EXIT WHEN psNumRecoEfec=ln_cont_reco_efec ;
               END LOOP; 
             END IF;
             EXIT WHEN curRecoEfectivas%NOTFOUND;
          END LOOP;
          CLOSE curRecoEfectivas;         
        END IF; 
        
        --Reasgina todas las recomendades del periodo anterior y las actuales
        OPEN curRecoPeriAnte;
          LOOP
            FETCH curRecoPeriAnte BULK COLLECT INTO v_cod_clie,
                                                    v_cod_peri
                                                     LIMIT rows;

             IF v_cod_clie.count > 0 THEN
               FOR i IN v_cod_clie.first .. v_cod_clie.last
               LOOP
                 --Si la recomendada paso pedido se reasigna
                 IF (EMP_FN_CUENT_PEDID(v_cod_clie(i), v_cod_peri(i)) = 1 ) THEN

                   INSERT INTO EMP_RECOM
                   (oid_reco,
                     cod_prog,
                     cod_peri,
                     cod_clie_rcte,
                     cod_clie_rcda,
                     ind_empre,
                     cod_peri_pped,
                     tip_empre,
                     fec_proc,
                     usu_regi,
                     ind_esta,
                     ind_reco_efec)
                     VALUES(
                     lnOid,
                     psPrograma,
                     psPeriodo,
                     psCliente,
                     v_cod_clie(i),
                     '0', --No es emprendedora
                     v_cod_peri(i),
                     'R', --Regular
                     sysdate,
                     psUsuario,
                     'R', --Reasignada
                     '0' --No es efectiva
                     );

                    EMP_PR_PROCE_REASI_EMPRE(v_cod_clie(i), psCliente,'RECO_NO_EFEC_ADICI',psUsuario);
                  END IF;
               END LOOP;
             END IF;
             EXIT WHEN curRecoPeriAnte%NOTFOUND;
          END LOOP;
          CLOSE curRecoPeriAnte;

     EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_REASI_RECOM: '||ls_sqlerrm);

  END EMP_PR_PROCE_REASI_RECOM;

/******************************************************************************
 Descripcion         : EMP_PR_PROCE_REASI_NUEVA
                       Proceso que reasgina las recomendadas efectivas y e identifica
                       a las recomendadas normales de la campaña anterio y las nuevas
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 17/01/2012
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
 /********************************************************************************/  
  PROCEDURE EMP_PR_PROCE_REASI_NUEVA(psPrograma     emp_empre.cod_prog%type,
                                        psCliente      emp_empre.cod_clie%type,
                                        psPeriodo      bas_ctrl_fact.cod_peri%type,
                                        psPrimePedi     bas_ctrl_fact.cod_peri%type,
                                        psUsuario      emp_empre.usu_regi%type,
                                        lnOid          NUMBER)
    IS
      
 --Recomendaciones 
 CURSOR curReco IS
    SELECT rc.cod_clie_rcda, rc.cod_peri_pped
    FROM emp_recom rc
    WHERE rc.oid_reco = lnOid
    and rc.cod_prog=psPrograma
    and rc.cod_peri=psPeriodo
    and rc.cod_peri_pped between psPrimePedi and psPeriodo
    and rc.tip_empre='F' 
    and rc.cod_clie_rcte=psCliente;
   
   TYPE t_cod_clie                  IS TABLE OF emp_empre.cod_clie%TYPE;  
    TYPE t_cod_peri                 IS TABLE OF emp_empre.cod_peri_baja%TYPE; 
     
   v_cod_clie                       t_cod_clie; 
   v_cod_peri                       t_cod_peri;
       
   rows NATURAL := 1000; 
   i    BINARY_INTEGER := 0;
    
    BEGIN
       
          OPEN curReco;
          LOOP
            FETCH curReco BULK COLLECT INTO v_cod_clie  ,
                                            v_cod_peri       
                                                     LIMIT rows;
                                          
             IF v_cod_clie.count > 0 THEN  
               FOR i IN v_cod_clie.first .. v_cod_clie.last
               LOOP
                    EMP_PR_PROCE_REASI_EMPRE(v_cod_clie(i), psCliente,'RECO_NUEVA_FAST',psUsuario);                                                        
                    
                    --Indicamos recomendaciones efectivas reasignadas
                    UPDATE emp_recom rc 
                      SET rc.ind_esta = 'R' --Reasignar
                      WHERE rc.oid_reco = lnOid
                        and rc.cod_prog=psPrograma
                        and rc.cod_peri=psPeriodo
                        and rc.cod_clie_rcte=psCliente
                         and rc.cod_peri_pped = v_cod_peri(i)
                        and rc.tip_empre='F' 
                        and rc.cod_clie_rcda = v_cod_clie(i);
                 
               END LOOP;                        
         END IF;
         EXIT WHEN curReco%NOTFOUND;
      END LOOP;
      CLOSE curReco;           
      
    EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_REASI_NUEVA: '||ls_sqlerrm);

  END EMP_PR_PROCE_REASI_NUEVA;
  
 /******************************************************************************
 Descripcion         : EMP_PR_PROCE_VALID_EMPRE_REGUL
                       Proceso que valida la empresaria regular
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 17/01/2012
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
********************************************************************************/                                           
 PROCEDURE EMP_PR_PROCE_VALID_EMPRE_REGUL(psPeriodo    IN     bas_ctrl_fact.cod_peri%type,
                                          psPrograma   IN     emp_empre.cod_prog%type,
                                          psUsuario    IN     emp_empre.usu_regi%type,
                                          psCliente    IN     emp_empre.cod_clie%type,
                                          psError      OUT    NUMBER,
                                          psOidReco    OUT    NUMBER) IS 
                                          
     lv_peri_baja                         bas_ctrl_fact.cod_peri%type;
     lv_cod_empre                         emp_empre.cod_clie%type;
     lv_ind_baja                          emp_empre.ind_baja%type;
     lv_cod_zona                          emp_progr_unida_admin.cod_zona%type;
     lv_peri_prime_conta                  bas_ctrl_fact.cod_peri%type;
     lv_peri_ini                          bas_ctrl_fact.cod_peri%type;
     lv_peri_fin                          bas_ctrl_fact.cod_peri%type;
     ln_reco_efec_rang1                   NUMBER;
     ln_reco_efec_rang2                   NUMBER;
     ln_reco_efec_max                     NUMBER;
     ln_cont_error                        NUMBER:=0;
     lv_codi                              emp_param_inscr_empre.cod_para_inscr%type;
     lv_codi_camp_reco                    emp_param_inscr_empre.cod_para_inscr%type;
     lv_desc                              emp_param_inscr_empre.des_para_inscr%type;     
     ln_valo                              emp_param_inscr_empre.val_para_inscr%type;
     lv_ind_acti                          emp_param_inscr_empre.ind_acti%type; 
     ln_oid                               NUMBER;    
                                           
 BEGIN
      --01) Unidad Administrativa del programa
      BEGIN
        SELECT uae.cod_zona into lv_cod_zona
          FROM mae_clien_unida_admin ua,
               mae_clien             mc,
               zon_terri_admin       ta,
               zon_secci             zs,
               zon_zona              zz,
               emp_progr_unida_admin uae
         WHERE ua.ztad_oid_terr_admi = ta.oid_terr_admi
           AND ta.zscc_oid_secc = zs.oid_secc
           AND zs.zzon_oid_zona = zz.oid_zona
           AND zz.cod_zona = uae.cod_zona
           AND ua.clie_oid_clie = mc.oid_clie
           AND ua.ind_acti = '1'
           AND mc.cod_clie = pscliente
           AND uae.cod_prog = psprograma;
        EXCEPTION    
            WHEN no_data_found THEN
              lv_cod_zona := NULL;
            WHEN OTHERS THEN
              lv_cod_zona := NULL;
        END;
    
        BEGIN             
       SELECT para.cod_para_inscr, para.des_para_inscr, para.ind_acti
         INTO lv_codi, lv_desc, lv_ind_acti
         FROM emp_param_inscr_empre para
        WHERE para.cod_prog = psprograma
          AND para.nom_para_inscr = 'VAL_UNID_ADMI_PROG';
    EXCEPTION    
        WHEN no_data_found THEN
          lv_ind_acti := '0';
        WHEN OTHERS THEN
          lv_ind_acti := '0';
        END;     
    
        IF (lv_ind_acti = '1' ) THEN
            IF ( (lv_cod_zona IS NULL) ) THEN 
                 INSERT INTO gtt_emp_error_requi_empre(cod_erro, des_erro)  VALUES (lv_codi, lv_desc);     
            END IF; 
        END IF;
        
        --02)Consultora registrada como pre o emprendedora
         BEGIN
            SELECT ee.ind_baja, ee.cod_clie
              INTO lv_ind_baja , lv_cod_empre
              FROM emp_empre ee
             WHERE ee.cod_clie = psCliente  
              AND ee.cod_prog = psPrograma;
          EXCEPTION    
              WHEN no_data_found THEN
                lv_ind_baja := NULL;
              WHEN OTHERS THEN
                lv_ind_baja := NULL;
          END;
    
            BEGIN             
           SELECT para.cod_para_inscr, para.des_para_inscr, para.ind_acti
             INTO lv_codi, lv_desc, lv_ind_acti
             FROM emp_param_inscr_empre para
            WHERE para.cod_prog = psprograma
              AND para.nom_para_inscr = 'VAL_CONS_REGI';
        EXCEPTION    
            WHEN no_data_found THEN
              lv_ind_acti := '0';
            WHEN OTHERS THEN
              lv_ind_acti := '0';
            END;
                 
           IF (lv_ind_acti = '1' ) THEN
                IF ( (lv_ind_baja IS NULL) AND (lv_cod_empre IS NOT NULL ) )THEN --existe como emprendeodra y no esta de baja
                     INSERT INTO gtt_emp_error_requi_empre(cod_erro, des_erro)  VALUES (lv_codi, lv_desc);     
                ELSE 
                BEGIN
                          --03)Campañas sin participar en el programa
                          BEGIN   
                          SELECT ee.cod_peri_baja
                            INTO lv_peri_baja
                            FROM emp_empre ee
                           WHERE ee.cod_clie = psCliente  
                            AND ee.ind_baja = '1'; 
                        EXCEPTION    
                            WHEN no_data_found THEN
                              lv_peri_baja := NULL;
                            WHEN OTHERS THEN
                              lv_peri_baja := NULL;
                        END;
                         
                        BEGIN             
                           SELECT para.cod_para_inscr, para.des_para_inscr, para.val_para_inscr, para.ind_acti
                             INTO lv_codi, lv_desc, ln_valo, lv_ind_acti
                             FROM emp_param_inscr_empre para
                            WHERE para.cod_prog = psprograma
                              AND para.nom_para_inscr = 'NUM_CAMP_SIN_PART';
                        EXCEPTION    
                            WHEN no_data_found THEN
                              lv_ind_acti := '0';
                            WHEN OTHERS THEN
                              lv_ind_acti := '0';
                        END;     
                        
                        IF (lv_ind_acti = '1' ) THEN
                              IF ( lv_peri_baja IS NOT NULL) THEN
                                IF (psPeriodo <= cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(lv_peri_baja,ln_valo) ) THEN

                                   INSERT INTO gtt_emp_error_requi_empre(cod_erro, des_erro)  VALUES (lv_codi, lv_desc);
                                 END IF;
                              END IF;
                        END IF;
                END;
                END IF;   
            END IF;     
    
    BEGIN
        --04)Antiguedad como consultora
        SELECT sp.cod_peri INTO lv_peri_prime_conta
          FROM mae_clien_prime_conta mcp,
               mae_clien             mc,
               cra_perio             cp,
               seg_perio_corpo       sp
         WHERE mc.oid_clie = mcp.clie_oid_clie
           AND sp.oid_peri = cp.peri_oid_peri
           AND cp.oid_peri = mcp.perd_oid_peri
           AND mc.cod_clie = psCliente;
     EXCEPTION    
        WHEN no_data_found THEN
          lv_peri_prime_conta := NULL;
        WHEN OTHERS THEN
          lv_peri_prime_conta := NULL;
     END;
     
     BEGIN     
         SELECT para.cod_para_inscr, para.des_para_inscr, para.val_para_inscr, para.ind_acti
           INTO lv_codi, lv_desc, ln_valo, lv_ind_acti
           FROM emp_param_inscr_empre para
          WHERE para.cod_prog = psprograma   
            AND para.nom_para_inscr = 'VAL_ANTI_CONSU';
     EXCEPTION    
        WHEN no_data_found THEN
          lv_ind_acti := '0';
        WHEN OTHERS THEN
          lv_ind_acti := '0';
     END;

     IF (lv_ind_acti = '1' ) THEN
            IF (lv_peri_prime_conta IS NOT NULL)  THEN
              IF (psPeriodo <= cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(lv_peri_prime_conta,ln_valo-1) ) THEN

                 INSERT INTO gtt_emp_error_requi_empre(cod_erro, des_erro)  VALUES (lv_codi, lv_desc);
               END IF;
            END IF;
     END IF;
    
    --05)Numero de campañas de actividad  
       BEGIN    
       SELECT para.cod_para_inscr, para.des_para_inscr, para.val_para_inscr, para.ind_acti
         INTO lv_codi, lv_desc, ln_valo, lv_ind_acti
         FROM emp_param_inscr_empre para
        WHERE para.cod_prog = psprograma   
          AND para.nom_para_inscr = 'NUM_CAMP_ACTI';
   EXCEPTION    
        WHEN no_data_found THEN
          lv_ind_acti := '0';
        WHEN OTHERS THEN
          lv_ind_acti := '0';
     END;
     
       BEGIN  
          SELECT count(*) into  ln_cont_error
          FROM mae_clien_histo_estat mche,
               mae_clien             mc,
               cra_perio             cra,
               seg_perio_corpo       sp,
               cra_perio             cra2,
               seg_perio_corpo       sp2
         WHERE mche.clie_oid_clie = mc.oid_clie
           AND mche.perd_oid_peri = cra.oid_peri
           AND cra.peri_oid_peri = sp.oid_peri
           AND mche.perd_oid_peri_peri_fin = cra2.oid_peri
           AND cra2.peri_oid_peri = sp2.oid_peri
           AND ( (sp.cod_peri >= cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-ln_valo)) OR
                 (sp2.cod_peri >= cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-ln_valo)) )
           AND mc.cod_clie =  psCliente
           AND mche.esta_oid_esta_clie IN (5, 7); --egreso o retirada        
     EXCEPTION    
        WHEN no_data_found THEN
          ln_cont_error := NULL;
        WHEN OTHERS THEN
          ln_cont_error := NULL;
     END;     
        
     IF (lv_ind_acti = '1' ) THEN
          IF (ln_cont_error >= 1)  THEN
               INSERT INTO gtt_emp_error_requi_empre(cod_erro, des_erro)  VALUES (lv_codi, lv_desc);
          END IF;
    END IF;
   
    BEGIN
        --06)REcomendacion efectiva
       SELECT para.val_para_inscr
         INTO lv_codi_camp_reco
         FROM emp_param_inscr_empre para
        WHERE para.cod_prog = psprograma  
          AND para.nom_para_inscr = 'NUM_CAMP_RECO_EFEC';
    EXCEPTION    
        WHEN no_data_found THEN
          lv_codi_camp_reco := NULL;
        WHEN OTHERS THEN
          lv_codi_camp_reco := NULL;
    END;
    
    BEGIN
       SELECT para.cod_para_inscr, para.des_para_inscr, para.val_para_inscr, para.ind_acti
         INTO lv_codi, lv_desc, ln_valo, lv_ind_acti
         FROM emp_param_inscr_empre para
        WHERE para.cod_prog = psprograma  
          AND para.nom_para_inscr = 'NUM_RECO_EFEC';
    EXCEPTION    
        WHEN no_data_found THEN
          lv_ind_acti := '0';
        WHEN OTHERS THEN
          lv_ind_acti := '0';
    END;
    
    IF ( (lv_codi_camp_reco IS NOT NULL) AND (lv_ind_acti = '1')) THEN
      
          --Genera Secuencial
          ln_oid := EMP_RECO_SEQ.Nextval;
          
          --Calcula periodo de inicio y fin del rango1      
          lv_peri_ini := cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-lv_codi_camp_reco); 
          lv_peri_fin  := cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-1);
          
          --Cuenta las recomendaciones efectivas en el rango1    
          ln_reco_efec_rang1 := EMP_FN_CUENT_RECO_EFEC(psPrograma,psPeriodo ,psCliente,lv_peri_ini, lv_peri_fin, psUsuario,ln_oid);
             
          --Calcula periodo de inicio y fin del rango2
          lv_peri_ini := cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(lv_peri_ini,-1);
          lv_peri_fin := cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-2);
          
          --Cuenta las recomendaciones efectivas en el rango2
          ln_reco_efec_rang2 := EMP_FN_CUENT_RECO_EFEC(psPrograma,psPeriodo ,psCliente,lv_peri_ini, lv_peri_fin, psUsuario, ln_oid);
          
          --Se considera el rango que tiene mayor cantidad de recomendaciones efectivas
          IF (ln_reco_efec_rang1 > ln_reco_efec_rang2) THEN
              ln_reco_efec_max := ln_reco_efec_rang1;
          ELSE 
              ln_reco_efec_max := ln_reco_efec_rang2;
          END IF;
          
          --Si las recomendaciones efectivas es menor q parametro inserta en tabla temporal de errores    
          IF (ln_reco_efec_max < ln_valo) THEN      
            INSERT INTO gtt_emp_error_requi_empre(cod_erro, des_erro)  VALUES (lv_codi, lv_desc);
          END IF;
              
          SELECT COUNT(*) INTO ln_cont_error FROM gtt_emp_error_requi_empre;
          
          IF (ln_cont_error > 0 ) THEN
             --Si hay error se guarda en la tabla de errores
             EMP_PR_PROCE_GUARD_ERROR_EMPRE(psPeriodo,psPrograma, psCliente,'R', psUsuario);
          ELSE
             BEGIN              
               --Si no hay error se reasigna las recomendadas               
               EMP_PR_PROCE_REASI_RECOM(psPrograma,psCliente, psPeriodo,psUsuario, ln_valo, ln_oid);
             END;
          END IF;
     END IF;
     psError := ln_cont_error;  
     psOidReco := ln_oid;         
 EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_VALID_EMPRE_REGUL: '||ls_sqlerrm);                                      
 END EMP_PR_PROCE_VALID_EMPRE_REGUL;    
    
  /******************************************************************************
 Descripcion         : EMP_FN_CUENT_RECO_NUEVA
                       Proceso que cuenta las recomendaciones efectivas en un rango
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 04/04/2013
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
********************************************************************************/                     
 FUNCTION EMP_FN_CUENT_RECO_NUEVA(psPrograma               emp_empre.cod_prog%type,
                                 psPeriOri                bas_ctrl_fact.cod_peri%type,
                                 psPeriodo                bas_ctrl_fact.cod_peri%type,                                 
                                 psCliente                emp_empre.cod_clie%type,
                                 psUsuario                emp_empre.usu_regi%type,
                                 lnOid                    NUMBER) RETURN NUMBER
   IS
  --Recomendaciones de nuevas en el periodo de parametro
  CURSOR curRecoNueva IS      
  SELECT mc.cod_clie, sp.cod_peri --Reocomendada, periodo de primer pedido
      FROM mae_clien_vincu       mcv,
           mae_clien             mc,
           mae_clien_prime_conta mcp,
           cra_perio             cp,
           seg_perio_corpo       sp
     WHERE mc.oid_clie = mcp.clie_oid_clie
       AND sp.oid_peri = cp.peri_oid_peri
       AND cp.oid_peri = mcp.perd_oid_peri
       AND mc.oid_clie = mcv.clie_oid_clie_vndo
       AND mcv.tivc_oid_tipo_vinc = 9 --Recomendada/Recomendante
       AND mcv.fec_desd < sysdate AND mcv.fec_hast > sysdate
       AND mcv.clie_oid_clie_vnte = (select mc1.oid_clie from mae_clien mc1 where mc1.cod_clie = psCliente)
       AND sp.cod_peri = psPeriodo;
       
   TYPE t_cod_clie                  IS TABLE OF mae_clien.cod_clie%TYPE;      
   TYPE t_cod_peri                  IS TABLE OF bas_ctrl_fact.cod_peri%type; 
     
   v_cod_clie                       t_cod_clie;
   v_cod_peri                       t_cod_peri;
    
   rows                             NATURAL := 1000; -- Number of rows to process at a time
   i                                BINARY_INTEGER := 0;
   ln_cont_reco                     NUMBER := 0;
     
   BEGIN

      OPEN curRecoNueva;
      LOOP
        FETCH curRecoNueva BULK COLLECT INTO v_cod_clie,
                                                     v_cod_peri          
                                                     LIMIT rows;
                                      
         IF v_cod_clie.count > 0 THEN 
            
           FOR i IN v_cod_clie.first .. v_cod_clie.last
           LOOP
             IF (  (EMP_FN_CUENT_PEDID(v_cod_clie(i),v_cod_peri(i)) > 0 ) AND
                   (EMP_FN_CUENT_PEDID(psCliente,v_cod_peri(i)) > 0) ) THEN                            
                 
                 BEGIN
                   
                 ln_cont_reco := ln_cont_reco + 1;
                        
                 INSERT INTO EMP_RECOM 
                   (oid_reco,
                     cod_prog,
                     cod_peri,
                     cod_clie_rcte,
                     cod_clie_rcda,
                     ind_empre,
                     cod_peri_pped,
                     tip_empre,
                     fec_proc,
                     usu_regi,
                     ind_esta,
                     ind_reco_efec)
                     
                     VALUES(
                     lnOid,
                     psPrograma,
                     psPeriOri,
                     psCliente,
                     v_cod_clie(i),
                     '',
                     v_cod_peri(i),                     
                     'F', --Fastruck
                     sysdate,
                     psUsuario,
                     'C', --Al inicio todas se cobran, a medida q se reasignen se cambia el indicador
                     '0' --no es efectiva
                     );                
                 EXCEPTION
                    WHEN OTHERS THEN
                      --Si se cae por PK es un recomendacion ya contada no debe caer
                      dbms_output.put_line('codigo repetido');
                 END;
               
             END IF;
          
           END LOOP;                        
         END IF;
         EXIT WHEN curRecoNueva%NOTFOUND;
      END LOOP;
      CLOSE curRecoNueva;
      
      return ln_cont_reco;
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_FN_CUENT_RECO_NUEVA: '||ls_sqlerrm);
     return 0;
      
  END EMP_FN_CUENT_RECO_NUEVA;                                       
                                          
 /******************************************************************************
 Descripcion         : EMP_PR_PROCE_VALID_EMPRE_FAST
                       Proceso que valida la empresaria fastruck
 ------------------------------------------------------------------------------                       
 Fecha Creacion      : 04/04/2013
 ------------------------------------------------------------------------------    
 Caso de Uso : 4.1 Creacion de empresaria 
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramirez Guardia
********************************************************************************/                                           
 PROCEDURE EMP_PR_PROCE_VALID_EMPRE_FAST(psPeriodo    IN     bas_ctrl_fact.cod_peri%type,
                                          psPrograma   IN     emp_empre.cod_prog%type,
                                          psUsuario    IN     emp_empre.usu_regi%type,
                                          psCliente    IN     emp_empre.cod_clie%type,
                                          psError      OUT    NUMBER,
                                          psNivel      OUT    NUMBER,
                                          psOidReco    OUT    NUMBER) IS 
                                           
     lv_peri_baja                         bas_ctrl_fact.cod_peri%type;
     lv_cod_empre                         emp_empre.cod_clie%type;
     lv_ind_baja                          emp_empre.ind_baja%type;
     lv_cod_zona                          emp_progr_unida_admin.cod_zona%type;
     lv_periodo                           bas_ctrl_fact.cod_peri%type;
     ln_reco_nueva                        NUMBER;
     ln_reco_per_ante1                    NUMBER;
     ln_reco_per_ante2                    NUMBER;
     ln_cont_error                        NUMBER:=0;
     lv_codi                              emp_param_inscr_empre.cod_para_inscr%type;
     lv_desc                              emp_param_inscr_empre.des_para_inscr%type;
     ln_valo                              emp_param_inscr_empre.val_para_inscr%type; 
     lv_ind_acti                          emp_param_inscr_empre.ind_acti%type;     
     ln_oid                               NUMBER;  
     ln_num_reco_nive1                    NUMBER;
     ln_num_reco_nive2                    NUMBER;  
                                           
 BEGIN
      --01) Unidad Administrativa del programa
      BEGIN
        SELECT uae.cod_zona into lv_cod_zona
          FROM mae_clien_unida_admin ua,
               mae_clien             mc,
               zon_terri_admin       ta,
               zon_secci             zs,
               zon_zona              zz,
               emp_progr_unida_admin uae
         WHERE ua.ztad_oid_terr_admi = ta.oid_terr_admi
           AND ta.zscc_oid_secc = zs.oid_secc
           AND zs.zzon_oid_zona = zz.oid_zona
           AND zz.cod_zona = uae.cod_zona
           AND ua.clie_oid_clie = mc.oid_clie
           AND ua.ind_acti = '1'
           AND mc.cod_clie = pscliente
           AND uae.cod_prog = psprograma;
        EXCEPTION    
            WHEN no_data_found THEN
              lv_cod_zona := NULL;
            WHEN OTHERS THEN
              lv_cod_zona := NULL;
     END;
    
     BEGIN             
       SELECT para.cod_para_inscr, para.des_para_inscr, para.ind_acti
         INTO lv_codi, lv_desc, lv_ind_acti
         FROM emp_param_inscr_empre para
        WHERE para.cod_prog = psprograma
          AND para.nom_para_inscr = 'VAL_UNID_ADMI_PROG';
    EXCEPTION    
        WHEN no_data_found THEN
          lv_ind_acti := '0';
        WHEN OTHERS THEN
          lv_ind_acti := '0';
     END;     
    
      IF (lv_ind_acti = '1' ) THEN
            IF ( (lv_cod_zona IS NULL) ) THEN 
                 INSERT INTO gtt_emp_error_requi_empre(cod_erro, des_erro)  VALUES (lv_codi, lv_desc);     
            END IF; 
      END IF;
     
        --02)Consultora registrada como pre o emprendedora
         BEGIN
            SELECT ee.ind_baja, ee.cod_clie
              INTO lv_ind_baja , lv_cod_empre
              FROM emp_empre ee
             WHERE ee.cod_clie = psCliente  
              AND ee.cod_prog = psPrograma;
          EXCEPTION    
              WHEN no_data_found THEN
                lv_ind_baja := NULL;
              WHEN OTHERS THEN
                lv_ind_baja := NULL;
          END;
    
            BEGIN             
           SELECT para.cod_para_inscr, para.des_para_inscr, para.ind_acti
             INTO lv_codi, lv_desc, lv_ind_acti
             FROM emp_param_inscr_empre para
            WHERE para.cod_prog = psprograma
              AND para.nom_para_inscr = 'VAL_CONS_REGI';
        EXCEPTION    
            WHEN no_data_found THEN
              lv_ind_acti := '0';
            WHEN OTHERS THEN
              lv_ind_acti := '0';
            END;     
    
          IF (lv_ind_acti = '1' ) THEN
              IF ( (lv_ind_baja IS NULL) AND (lv_cod_empre IS NOT NULL ) )THEN --existe como emprendeodra y no esta de baja
                   INSERT INTO gtt_emp_error_requi_empre(cod_erro, des_erro)  VALUES (lv_codi, lv_desc);     
              ELSE 
              BEGIN
                        --03)Campañas sin participar en el programa
                        BEGIN   
                        SELECT ee.cod_peri_baja
                          INTO lv_peri_baja
                          FROM emp_empre ee
                         WHERE ee.cod_clie = psCliente  
                          AND ee.ind_baja = '1'; 
                      EXCEPTION    
                          WHEN no_data_found THEN
                            lv_peri_baja := NULL;
                          WHEN OTHERS THEN
                            lv_peri_baja := NULL;
                      END;
                       
                      BEGIN             
                         SELECT para.cod_para_inscr, para.des_para_inscr, para.val_para_inscr, para.ind_acti
                           INTO lv_codi, lv_desc, ln_valo, lv_ind_acti
                           FROM emp_param_inscr_empre para
                          WHERE para.cod_prog = psprograma
                            AND para.nom_para_inscr = 'NUM_CAMP_SIN_PART_F';
                      EXCEPTION    
                          WHEN no_data_found THEN
                            lv_ind_acti := '0';
                          WHEN OTHERS THEN
                            lv_ind_acti := '0';
                      END;     
                      
                      IF (lv_ind_acti='1') THEN
                            IF (lv_peri_baja IS NOT NULL)  THEN
                              IF (psPeriodo <= cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(lv_peri_baja,ln_valo) ) THEN

                                 INSERT INTO gtt_emp_error_requi_empre(cod_erro, des_erro)  VALUES (lv_codi, lv_desc);
                               END IF;
                            END IF;
                     END IF;
                     
              END;
              END IF; 
          END IF;                 
      
    BEGIN
         SELECT para.val_para_inscr
           INTO ln_num_reco_nive1
           FROM emp_param_inscr_empre para
          WHERE para.cod_prog = psprograma  
            AND para.nom_para_inscr = 'NUM_RECO_NIV1';  
    EXCEPTION    
        WHEN no_data_found THEN
          ln_num_reco_nive1 := 0;
        WHEN OTHERS THEN
          ln_num_reco_nive1 := 0;
    END;
     
    BEGIN
         SELECT para.val_para_inscr
           INTO ln_num_reco_nive2
           FROM emp_param_inscr_empre para
          WHERE para.cod_prog = psprograma  
            AND para.nom_para_inscr = 'NUM_RECO_NIV2'; 
    EXCEPTION    
        WHEN no_data_found THEN
          ln_num_reco_nive2 := 0;
        WHEN OTHERS THEN
          ln_num_reco_nive2 := 0;
    END;
    
        BEGIN
       SELECT para.cod_para_inscr, para.des_para_inscr, para.ind_acti
         INTO lv_codi, lv_desc, lv_ind_acti
         FROM emp_param_inscr_empre para
        WHERE para.cod_prog = psprograma  
          AND para.nom_para_inscr = 'NUM_RECO_NIVE_F';
    EXCEPTION    
        WHEN no_data_found THEN
          lv_ind_acti := '0';
        WHEN OTHERS THEN
          lv_ind_acti := '0';
    END;
    
    --Genera Secuencial
          ln_oid := EMP_RECO_SEQ.Nextval;

          IF (ln_num_reco_nive1 <> 0 AND ln_num_reco_nive2 <>0 AND lv_ind_acti<>0) THEN
            
              ln_reco_nueva := EMP_FN_CUENT_RECO_NUEVA(psPrograma,psPeriodo,psPeriodo,psCliente,psUsuario, ln_oid);
              ln_reco_per_ante1 := EMP_FN_CUENT_RECO_NUEVA(psPrograma,psPeriodo,cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-1),psCliente,psUsuario, ln_oid);
              ln_reco_per_ante2 := EMP_FN_CUENT_RECO_NUEVA(psPrograma,psPeriodo,cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-2),psCliente,psUsuario, ln_oid);
              
              if (ln_reco_per_ante1 >= ln_num_reco_nive2) then
                psNivel := 2;
                lv_periodo := cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-1);
              else
                if (ln_reco_per_ante1 >= ln_num_reco_nive1) and (ln_reco_per_ante1 < ln_num_reco_nive2) then
                   psNivel := 1;
                   lv_periodo := cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-1);
                else 
                    if (ln_reco_per_ante2 >= ln_num_reco_nive2) then
                         psNivel := 2;
                         lv_periodo := cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-2);
                     else
                       if (ln_reco_per_ante2 >= ln_num_reco_nive1) and (ln_reco_per_ante2 < ln_num_reco_nive2) then
                           psNivel := 1;
                           lv_periodo := cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psPeriodo,-2);
                       else
                          if (ln_reco_per_ante2 < ln_num_reco_nive1) then
                             psNivel := 0;
                             INSERT INTO gtt_emp_error_requi_empre(cod_erro, des_erro)  VALUES (lv_codi, lv_desc);
                          end if;
                        end if;
                     end if;
                   end if;      
                end if;
          
          END IF;
          
          SELECT COUNT(*) INTO ln_cont_error FROM gtt_emp_error_requi_empre;
          
          IF (ln_cont_error > 0 ) THEN
             --Si hay error se guarda en la tabla de errores
             EMP_PR_PROCE_GUARD_ERROR_EMPRE(psPeriodo,psPrograma, psCliente,'F', psUsuario);
          ELSE
               --Si no hay error se reasigna las recomendadas               
               EMP_PR_PROCE_REASI_NUEVA(psPrograma,psCliente, psPeriodo, lv_periodo ,psUsuario,  ln_oid);

          END IF;

     psError := ln_cont_error; 
     psOidReco := ln_oid;
                                  
 EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EMP_PR_PROCE_VALID_EMPRE_FAST: '||ls_sqlerrm);                                      
 END EMP_PR_PROCE_VALID_EMPRE_FAST;           

End EMP_Pkg_Proce;
/
