CREATE OR REPLACE PACKAGE CRA_PKG_PROCE is

 procedure CRA_PR_PROCE_CARGA_MATRI_DIAS (psCodigoPais    varchar2);
  
 procedure CRA_PR_PROCE_ACTUA_MATRI_DIAS (psOidMatri             cra_matri_dias.oid_matr_dias%type,
                                          psDiasDesplazamiento   number, --respecto a la actividad origen                                          
                                          psUsuario              cra_crono.usu_regi%type);
                                    
  PROCEDURE CRA_PR_DELET_DIA_NO_LABOR(psCodigoPais      VARCHAR2,
                                      psAnhio                   VARCHAR2,
                                      psCodigoActividad      VARCHAR2,
                                      psDia                     VARCHAR2,
                                      psUsuario                 VARCHAR2);                                    
  
  PROCEDURE CRA_PR_PROCE_DIA_NO_LABOR(psCodigoPais              VARCHAR2,
                                      psAnhio                   VARCHAR2,
                                      psCodigoActividad      VARCHAR2,
                                      psDia                     VARCHAR2,
                                      psUsuario                 VARCHAR2);
  
  PROCEDURE CRA_PR_PROCE_DIA_FERIA(psCodigoPais              VARCHAR2,
                                      psAnhio                VARCHAR2,
                                      psCodigoActividad      VARCHAR2,
                                      psFecha                VARCHAR2,                                      
                                      psUsuario              VARCHAR2);                                                                                                               
                                      
 PROCEDURE CRA_PR_COPIA_CALEN_POR_ACTIV (psAnhio                cra_calen.num_anio%type,  
                                        psOidActiRefe          cra_activ.oid_acti%type,
                                        psOidActiRege          cra_activ.oid_acti%type,                                               
                                        psUsuario              cra_crono.usu_regi%type);    
                                        
  PROCEDURE  CRA_PR_INSER_GRUPO_ZONA(psCodigoPais   seg_pais.cod_pais%type,
                                    psMarca        VARCHAR2,
                                    psCanal        VARCHAR2,
                                    psAcceso       VARCHAR2,
                                    psNombreGrupo  cra_cabec_grupo_zona.nom_grup%type,
                                    psUsuario       cra_crono.usu_regi%type,
                                    psAnhio         cra_calen.num_anio%type);
                                    
                                    
  PROCEDURE CRA_PR_DELETE_GRUPO_ZONA(psOidGrupoZona        cra_feria.cgzo_oid_cabe_grup_zona%type,
                                    psUsuario             cra_feria.usu_regi%type);                                                                                                               
                                      
  PROCEDURE CRA_PR_PROCE_CARGA_PERIO_CORPO(psCodigoPais              VARCHAR2,
                                           psAnhio                   VARCHAR2);   

  FUNCTION  CRA_FN_CALCU_DESPL_VALID(psCodigoPais       seg_pais.cod_pais%type,
                                     psOidgrupoZona     cra_cabec_grupo_zona.oid_cabe_grup_zona%type,
                                     psOidActiv         cra_activ.oid_acti%type,
                                     psFechaInicio      date,
                                     psDesplazamiento   cra_crono_grupo_zona.val_fec0%type)  RETURN NUMBER;
                                     
procedure CRA_PR_RECUR_GENER_CRONO_FASE1 (psOidGrupoZona        cra_cabec_grupo_zona.oid_cabe_grup_zona%type,
                                          psOidActiOri          cra_activ.oid_acti%type,
                                          psOidPeriodo          seg_perio_corpo.oid_peri%type,
                                          psCodigoPais          seg_pais.cod_pais%type,
                                          psFecIni              date,
                                          psUsuario             cra_crono.usu_regi%type);                                     
                                     
procedure CRA_PR_PROCE_GENER_CRONO_FASE1 (psCodigoPeriodo varchar2,
                                          psCodigoPais    varchar2,
                                          psCantidadColumnas out varchar2,
                                          psFechaInicio out varchar2,
                                          psUsuario     varchar2);
                                          
procedure CRA_PR_PROCE_CARGA_CRONO_FASE1 (psCodigoPeriodo varchar2,
                                          psCodigoPais    varchar2,
                                          psCantidadColumnas out varchar2,
                                          psFechaInicio out varchar2);
                                          
procedure CRA_PR_RECUR_ACTUA_CRONO_FASE1 (psOidActividad        cra_crono.cact_oid_acti%type,
                                          psOidPeriodo          cra_crono.perd_oid_peri%type,
                                          psFechaPapa           date,
                                          psCodigoPais          seg_pais.cod_pais%type,
                                          psUsuario             cra_crono.usu_regi%type,
                                          psOidGrupoZona        cra_crono_grupo_zona.oid_cron_grup_zona%type);
                                          
procedure CRA_PR_PROCE_ACTUA_CRONO_FASE1 (psCodigoPais           seg_pais.cod_pais%type,
                                          psOidCrono             cra_crono.oid_cron%type,
                                          psOidGrupoZona         cra_cabec_grupo_zona.oid_cabe_grup_zona%type,
                                          psDiasDesplazamiento   number, --respecto a la actividad origen                                          
                                          psUsuario              cra_crono.usu_regi%type);                                          
                                          
procedure CRA_PR_PROCE_CARGA_CRONO_FASE2 (psCodigoPeriodo varchar2,
                                          psCodigoPais    varchar2,
                                          psOidZona       varchar2,
                                          psCantidadColumnas out varchar2,
                                          psFechaInicio out varchar2);                                          
                                          
procedure CRA_PR_PROCE_GENER_CRONO_FASE2 (psCodigoPeriodo varchar2,
                                          psCodigoPais    varchar2,
                                          psOidZona       varchar2,
                                          psCantidadColumnas out varchar2,
                                          psFechaInicio out varchar2,
                                          psUsuario     varchar2);
                                          
procedure CRA_PR_PROCE_DELET_CRONO_FASE2(psCodigoPeriodo   seg_perio_corpo.cod_peri%type, 
                                         psOidZona cra_crono.zzon_oid_zona%type);   
                                         
procedure CRA_PR_RECUR_ACTUA_CRONO_FASE2 (psOidActividad        cra_crono.cact_oid_acti%type,
                                    psOidPeriodo          cra_crono.perd_oid_peri%type,
                                    psFechaPapa           date,
                                    psCodigoPais          seg_pais.cod_pais%type,
                                    psUsuario             cra_crono.usu_regi%type,
                                    psOidZona             cra_crono.zzon_oid_zona%type,
                                    psOidGrupoZona        cra_crono_grupo_zona.cgzo_oid_cabe_grup_zona%type);                                                                                                                           
                                          
procedure CRA_PR_PROCE_ACTUA_CRONO_FASE2 (psCodigoPais           seg_pais.cod_pais%type,
                                          psOidCrono             cra_crono.oid_cron%type,
                                          psOidGrupoZona         cra_cabec_grupo_zona.oid_cabe_grup_zona%type,
                                          psDiasDesplazamiento   number,
                                          psUsuario              cra_crono.usu_regi%type);
                                          
procedure CRA_PR_COPIA_CRONO_ZONA_FASE2 (psCodigoPais                 seg_pais.cod_pais%type,
                                               psCodigoPeriodo        seg_perio_corpo.cod_peri%type,
                                               PsOidZonaRege          zon_zona.oid_zona%type,
                                               PsOidZonaRefe          zon_zona.oid_zona%type,
                                               psOidActividad         cra_crono.cact_oid_acti%type,
                                         psUsuario              cra_crono.usu_regi%type);                                          
                                                                                                                                                                                                                                                                                                                                  
FUNCTION  CRA_FN_GET_FECHA_CRONO_FASE2(psCodigoPais       seg_pais.cod_pais%type,
                                     psCodigoPeriodo       seg_perio_corpo.cod_peri%type,
                                     psCodigoActiv         cra_activ.cod_acti%type,
                                     psCodigoZona          zon_zona.cod_zona%type)  RETURN VARCHAR2;                                         

END CRA_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY CRA_PKG_PROCE is
  /* Declaracion de Variables */
  ln_sqlcode        NUMBER(10);
  ls_sqlerrm        VARCHAR2(1000);

/******************************************************************************
 Descripcion         : CRA_PR_PROCE_CARGA_MATRI_TEMPO
                       Proceso que carga una tabla temporal con la informacion
                       necesaria para mostrar la matriz de dias en SSiCC
 ------------------------------------------------------------------------------
 Fecha Creacion      : 13/12/2012
 ------------------------------------------------------------------------------
 Parametros Entrada:
     psCodigoPais : Codigo Pais
 ------------------------------------------------------------------------------
 Caso de Uso : Matriz de Dias
 ------------------------------------------------------------------------------
 Autor               : Dennys Oliva Iriate
********************************************************************************/
procedure CRA_PR_PROCE_CARGA_MATRI_DIAS (psCodigoPais    varchar2) is

 cursor c_mat is

    select mat.cgzo_oid_cabe_grup_zona,
           ccg.nom_grup,
           mat.cact_oid_acti,
           ca.nom_acti,
           mat.num_dias_refe,
           cra_dias(ca.oid_acti,ccg.cod_grup, pa.cod_pais)
      from cra_matri_dias mat,
           cra_cabec_grupo_zona ccg,
           cra_activ            ca,
           seg_pais             pa
     where mat.cgzo_oid_cabe_grup_zona = ccg.oid_cabe_grup_zona
       and mat.cact_oid_acti = ca.oid_acti
       and ccg.pais_oid_pais = ca.pais_oid_pais
       and ca.pais_oid_pais = pa.oid_pais
       and pa.cod_pais = psCodigoPais
       and ca.ind_acti = '1'
       and ccg.ind_esta = '1'
     order by 1, 6;
   
TYPE t_oid_grup_zona    IS TABLE OF  cra_cabec_grupo_zona.oid_cabe_grup_zona%TYPE;
TYPE t_nom_grup_zona    IS TABLE OF  cra_cabec_grupo_zona.nom_grup%TYPE;       
TYPE t_cact_oid_acti    IS TABLE OF  cra_activ.oid_acti%TYPE;       
TYPE t_nom_acti         IS TABLE OF  cra_activ.nom_acti%TYPE;                  
TYPE t_num_dias         IS TABLE OF  cra_matri_dias.num_dias_refe%TYPE;     
TYPE t_orden            IS TABLE OF  cra_matri_dias.num_dias_refe%TYPE;

v_oid_grup_zona         t_oid_grup_zona;
v_nom_grup_zona         t_nom_grup_zona;
v_cact_oid_acti          t_cact_oid_acti;
v_nom_acti               t_nom_acti     ;
v_num_dias               t_num_dias     ;
v_orden                  t_orden        ;

l_oid                   cra_cabec_grupo_zona.oid_cabe_grup_zona%type;
l_nom                   cra_cabec_grupo_zona.nom_grup%type;
l_orden                 cra_matri_dias.num_dias_refe%TYPE;
lv_aux                  varchar2(4000) := '';
begin
    
     delete cra_tempo_matri_dias;             
  
  OPEN c_mat;
    LOOP
      FETCH c_mat BULK COLLECT
         INTO v_oid_grup_zona ,     
              v_nom_grup_zona ,        
              v_cact_oid_acti  ,        
              v_nom_acti       ,        
              v_num_dias       ,         
              v_orden                  
              LIMIT 1000;

      IF v_oid_grup_zona.count > 0 THEN

        FOR i IN v_oid_grup_zona.first .. v_oid_grup_zona.last loop                                  

            if i=1 then
              l_oid := v_oid_grup_zona(i);
              l_orden := v_orden(i);
            end if;
            
            if ( l_oid = v_oid_grup_zona(i) and  l_orden = v_orden(i) ) then
               lv_aux :=  lv_aux ||'|'|| v_nom_acti(i);                  

            else         
              
              insert into cra_tempo_matri_dias
                 (oid_grup_zona,
                  val_grup_zona,
                  val_acti,
                  val_dia)
               values
                 (l_oid,
                  l_nom,
                  lv_aux,
                  l_orden);                                               
            
               lv_aux :=  '|'||v_nom_acti(i);
               
            end if;
            l_oid := v_oid_grup_zona(i);
            l_nom := v_nom_grup_zona(i);         
            l_orden := v_orden(i);           
                  
        END LOOP;
        --para el ultimo        
        if ( (v_oid_grup_zona(v_oid_grup_zona.count-1) <> v_oid_grup_zona(v_oid_grup_zona.count) )
             or ( v_orden(v_oid_grup_zona.count-1) <> v_orden(v_oid_grup_zona.count)  ) ) then
                        
              insert into cra_tempo_matri_dias
                 (oid_grup_zona,
                  val_grup_zona,
                  val_acti,
                  val_dia)
               values
                 (l_oid,
                  l_nom,
                  lv_aux,
                  l_orden); 
        end if;
        
      END IF;

      EXIT WHEN c_mat%NOTFOUND;
      END LOOP;
    CLOSE c_mat;

EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_CARGA_MATRI_DIAS ' || ls_sqlerrm);    
    
end CRA_PR_PROCE_CARGA_MATRI_DIAS;

procedure CRA_PR_PROCE_ACTUA_MATRI_DIAS ( psOidMatri             cra_matri_dias.oid_matr_dias%type,
                                          psDiasDesplazamiento   number, --respecto a la actividad origen                                          
                                          psUsuario              cra_crono.usu_regi%type)  IS
    
    /* ln_oid_acti          number(8);
     ln_oid_peri          number(8);
     dt_fec_prev          date;*/
                                                
BEGIN
     
    /* select cgz.cact_oid_acti, cgz.perd_oid_peri, cgzPa.Fec_Prev
       into ln_oid_acti, ln_oid_peri, dt_fec_prev --Fecha del Padre            
       from cra_crono_grupo_zona cgz,
            cra_crono_grupo_zona cgzPa,
            cra_activ            ca,
            seg_pais             pa
      where cgz.oid_cron_grup_zona = psOidCrono
        and cgz.cact_oid_acti = ca.oid_acti
        and ca.pais_oid_pais = pa.oid_pais
        and pa.cod_pais = psCodigoPais
        and cgzPa.Cgzo_Oid_Cabe_Grup_Zona = cgz.cgzo_oid_cabe_grup_zona
        and cgzPa.Perd_Oid_Peri = cgz.perd_oid_peri
        and cgzPa.Cact_Oid_Acti =
            nvl(ca.cact_oid_acti,
                (select cca.oid_acti
                   from cra_activ cca
                  where upper(cca.nom_acti) like '%INICIO DE PERIODO%'
                    and cca.pais_oid_pais = pa.oid_pais));
           */
    --Primera vez (solo actividad padre) se actualiza el desplazamiento
    update cra_matri_dias mat
    set mat.num_dias_refe = psDiasDesplazamiento,
        mat.usu_modi = psUsuario,
        mat.fec_modi = sysdate
    where mat.oid_matr_dias = psOidMatri;
    
    --Para el resto se realiza la recursividad
    --CRA_PKG_PROCE.CRA_PR_RECUR_ACTUA_MATRI_DIAS(ln_oid_acti,ln_oid_peri,dt_fec_prev,psCodigoPais, psUsuario, psOidGrupoZona);
    
EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_ACTUA_MATRI_DIAS ' || ls_sqlerrm);              
End CRA_PR_PROCE_ACTUA_MATRI_DIAS;
  
PROCEDURE CRA_PR_DELET_DIA_NO_LABOR(psCodigoPais     VARCHAR2,
                                      psAnhio                   VARCHAR2,
                                      psCodigoActividad         VARCHAR2,
                                      psDia                     VARCHAR2,
                                      psUsuario                 VARCHAR2) as    
   
    fecha                        DATE;
    fecFin                       DATE;
    flag                         boolean:= true;           
    
  BEGIN
      SELECT CP.FEC_INIC INTO fecha
        FROM CRA_PERIO CP
       WHERE CP.VAL_NOMB_PERI LIKE '%'||psAnhio||'01%';

       SELECT CP.FEC_FINA INTO fecFin
         FROM CRA_PERIO CP
        WHERE CP.VAL_NOMB_PERI LIKE '%'||psAnhio||'18%';
              
       --select next_day(fecInicio,psDia) into fecha from  dual;

      while (flag) loop
        if TRIM(TO_CHAR(fecha, 'DAY', 'NLS_DATE_LANGUAGE = SPANISH')) = psDia then
          flag := false;
        end if;
        IF (flag) THEN
           fecha := fecha + 1;
        END IF;
      end loop;
      
      LOOP
  
          DELETE CRA_FERIA CFD
           WHERE exists
           (SELECT CF.OID_FERI
                    FROM CRA_FERIA            CF,
                         CRA_ACTIV            CA,
                         CRA_CABEC_GRUPO_ZONA GZ,
                         SEG_PAIS             SP
                   WHERE CF.CGZO_OID_CABE_GRUP_ZONA = GZ.OID_CABE_GRUP_ZONA
                     AND CF.CACT_OID_ACTI = CA.OID_ACTI
                     AND GZ.PAIS_OID_PAIS = CA.PAIS_OID_PAIS
                     AND CA.PAIS_OID_PAIS = SP.OID_PAIS
                     AND SP.COD_PAIS = psCodigoPais
                     AND CF.NUM_ANIO = psAnhio
                     AND CA.OID_ACTI = psCodigoActividad
                     AND CF.IND_FEST = '0'
                     --AND CF.IND_NO_LABO = '1'
                     AND TRUNC(CF.FEC_FERI) = TRUNC(fecha)
                     AND CF.OID_FERI = CFD.OID_FERI);
              
            fecha := fecha+7;
          IF (fecha > fecFin ) THEN
             EXIT;
          END IF;
       END LOOP;
  EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_DELET_DIA_NO_LABOR ' || ls_sqlerrm);
  END  CRA_PR_DELET_DIA_NO_LABOR;      
  
  PROCEDURE CRA_PR_PROCE_DIA_NO_LABOR(psCodigoPais              VARCHAR2,
                                      psAnhio                   VARCHAR2,
                                      psCodigoActividad         VARCHAR2,
                                      psDia                     VARCHAR2,
                                      psUsuario                 VARCHAR2) as
    
   CURSOR c_grupoActiv IS
   
       SELECT GZ.OID_CABE_GRUP_ZONA , 
              CA.OID_ACTI
         FROM CRA_CABEC_GRUPO_ZONA GZ, CRA_ACTIV CA, SEG_PAIS SP
        WHERE GZ.PAIS_OID_PAIS = CA.PAIS_OID_PAIS
          AND CA.PAIS_OID_PAIS = SP.Oid_Pais
          AND SP.COD_PAIS = psCodigoPais
          AND CA.OID_ACTI = psCodigoActividad
          AND CA.IND_ACTI = '1'
          AND GZ.IND_ESTA = '1'
         ORDER BY 1, 2;
     
    TYPE t_oid_cabe_grup_zona    IS TABLE OF CRA_CABEC_GRUPO_ZONA.Oid_Cabe_Grup_Zona%TYPE;
    TYPE t_oid_acti              IS TABLE OF CRA_ACTIV.Oid_Acti%TYPE;

    v_oid_cabe_grup_zona         t_oid_cabe_grup_zona;
    v_oid_acti                   t_oid_acti ;

    w_filas                      NUMBER := 5000; -- Numero de filas a procesar cada vez
    i                            BINARY_INTEGER := 0;
    fecha                        DATE;
    fecFin                       DATE;
    ncont                        NUMBER; 
    flag                         boolean:= true;           
    
  BEGIN
      SELECT CP.FEC_INIC INTO fecha
        FROM CRA_PERIO CP
       WHERE CP.VAL_NOMB_PERI LIKE '%'||psAnhio||'01%';

       SELECT CP.FEC_FINA INTO fecFin
         FROM CRA_PERIO CP
        WHERE CP.VAL_NOMB_PERI LIKE '%'||psAnhio||'18%';
              
       --select next_day(fecInicio,psDia) into fecha from  dual;

      while (flag) loop
        if TRIM(TO_CHAR(fecha, 'DAY', 'NLS_DATE_LANGUAGE = SPANISH')) = psDia then
          flag := false;
        end if;
        IF (flag) THEN
           fecha := fecha + 1;
        END IF;
      end loop;
       
      LOOP
          OPEN c_grupoActiv;
                LOOP
                  FETCH c_grupoActiv BULK COLLECT
                     INTO v_oid_cabe_grup_zona,
                          v_oid_acti               
                          LIMIT w_filas;

                  IF v_oid_cabe_grup_zona.count > 0 THEN

                    FOR i IN v_oid_cabe_grup_zona.first .. v_oid_cabe_grup_zona.last loop
                      
                      SELECT COUNT(*) INTO ncont
                        FROM CRA_FERIA CA
                       WHERE CA.CGZO_OID_CABE_GRUP_ZONA = v_oid_cabe_grup_zona(i)
                         AND CA.CACT_OID_ACTI =  v_oid_acti(i)
                         AND CA.NUM_ANIO = psAnhio
                         AND TRUNC(CA.FEC_FERI) = TRUNC(fecha);
                         
                      IF (ncont = 0) THEN
                    
                       INSERT INTO 
                              CRA_FERIA (  OID_FERI                ,
                                          CGZO_OID_CABE_GRUP_ZONA ,
                                          CACT_OID_ACTI           ,
                                          NUM_ANIO                ,
                                          FEC_FERI                ,
                                          IND_FEST                ,
                                          IND_NO_LABO             ,
                                          IND_TRAP                ,
                                          USU_REGI                ,
                                          FEC_REGI                ,
                                          USU_MODI                ,
                                          FEC_MODI                
                                         ) VALUES
                                         (
                                           CRA_FERI_SEQ.Nextval ,
                                           v_oid_cabe_grup_zona(i),
                                           v_oid_acti(i),
                                           psAnhio,
                                           fecha,
                                           '0',
                                           gen_pkg_gener.gen_fn_param_pais(psCodigoPais,'CRA','004'), --'1',
                                           gen_pkg_gener.gen_fn_param_pais(psCodigoPais,'CRA','005'),--'0',
                                           psUsuario,
                                           sysdate,
                                           null,
                                           null
                                         );     
                          END IF;                                  
            
                    END LOOP;       

                   END IF;      
      
                 EXIT WHEN c_grupoActiv%NOTFOUND;
                 
                 END LOOP;
           
            CLOSE c_grupoActiv;
            fecha := fecha+7;
          IF (fecha > fecFin) THEN
             EXIT;
          END IF;
       END LOOP;
  EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_DIA_NO_LABOR ' || ls_sqlerrm);
  END  CRA_PR_PROCE_DIA_NO_LABOR;              

  PROCEDURE CRA_PR_PROCE_DIA_FERIA(psCodigoPais              VARCHAR2,
                                      psAnhio                VARCHAR2,
                                      psCodigoActividad      VARCHAR2,
                                      psFecha                VARCHAR2,                                      
                                      psUsuario              VARCHAR2) as
    
   CURSOR c_grupoActiv IS
   
       SELECT GZ.OID_CABE_GRUP_ZONA ,CA.OID_ACTI
         FROM CRA_CABEC_GRUPO_ZONA GZ, CRA_ACTIV CA, SEG_PAIS SP
        WHERE GZ.PAIS_OID_PAIS = CA.PAIS_OID_PAIS
          AND CA.PAIS_OID_PAIS = SP.Oid_Pais
          AND SP.COD_PAIS = psCodigoPais
          AND CA.OID_ACTI = psCodigoActividad
          AND CA.IND_ACTI = '1'
          AND GZ.IND_ESTA = '1'
         ORDER BY 1, 2;
     
    TYPE t_oid_cabe_grup_zona    IS TABLE OF CRA_CABEC_GRUPO_ZONA.Oid_Cabe_Grup_Zona%TYPE;
    TYPE t_oid_acti              IS TABLE OF CRA_ACTIV.Oid_Acti%TYPE;

    v_oid_cabe_grup_zona         t_oid_cabe_grup_zona;
    v_oid_acti                   t_oid_acti ;

    w_filas                      NUMBER := 5000; -- Numero de filas a procesar cada vez
    i                            BINARY_INTEGER := 0;
    
  BEGIN     
          OPEN c_grupoActiv;
                LOOP
                  FETCH c_grupoActiv BULK COLLECT
                     INTO v_oid_cabe_grup_zona,
                          v_oid_acti                
                          LIMIT w_filas;

                  IF v_oid_cabe_grup_zona.count > 0 THEN

                    FOR i IN v_oid_cabe_grup_zona.first .. v_oid_cabe_grup_zona.last loop
                       INSERT INTO 
                              CRA_FERIA (  OID_FERI                ,
                                          CGZO_OID_CABE_GRUP_ZONA ,
                                          CACT_OID_ACTI           ,
                                          NUM_ANIO                ,
                                          FEC_FERI                ,
                                          IND_FEST                ,
                                          IND_NO_LABO             ,
                                          IND_TRAP                ,
                                          USU_REGI                ,
                                          FEC_REGI                ,
                                          USU_MODI                ,
                                          FEC_MODI                
                                         ) VALUES
                                         (
                                           CRA_FERI_SEQ.Nextval ,
                                           v_oid_cabe_grup_zona(i),
                                           v_oid_acti(i),
                                           psAnhio,
                                           TO_DATE(psFecha,'dd/mm/yyyy'),
                                           '1',
                                           '0',
                                           '0',
                                           psUsuario,
                                           sysdate,
                                           null,
                                           null
                                         );                                        
            
                    END LOOP;       

                   END IF;      
                 EXIT WHEN c_grupoActiv%NOTFOUND;
                 
                 END LOOP;
           
            CLOSE c_grupoActiv;
            
  EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_DIA_FERIA ' || ls_sqlerrm);
  END  CRA_PR_PROCE_DIA_FERIA;  
  
PROCEDURE CRA_PR_COPIA_CALEN_POR_ACTIV (psAnhio                cra_calen.num_anio%type,  
                                        psOidActiRefe          cra_activ.oid_acti%type,
                                        psOidActiRege          cra_activ.oid_acti%type,                                               
                                        psUsuario              cra_crono.usu_regi%type)  IS
       
cursor c_calen_acti is

   SELECT CF.CGZO_OID_CABE_GRUP_ZONA,
       CF.FEC_FERI,
       CF.IND_FEST,
       CF.IND_NO_LABO,
       CF.IND_TRAP
  FROM CRA_FERIA CF
 WHERE CF.NUM_ANIO = psAnhio
   AND CF.CACT_OID_ACTI = psOidActiRefe;
     
TYPE t_cgzo_oid_cabe_grup_zona    IS TABLE OF  cra_feria.cgzo_oid_cabe_grup_zona%TYPE;        
TYPE t_fec_feri                   IS TABLE OF  cra_feria.fec_feri%TYPE;            
TYPE t_ind_fest                   IS TABLE OF  cra_feria.ind_fest%TYPE;     
TYPE t_ind_no_labo                IS TABLE OF  cra_feria.ind_no_labo%TYPE;     
TYPE t_ind_trap                   IS TABLE OF  cra_feria.ind_trap%TYPE;      

v_cgzo_oid_cabe_grup_zona				t_cgzo_oid_cabe_grup_zona;
v_fec_feri               				t_fec_feri               ;
v_ind_fest               				t_ind_fest               ;
v_ind_no_labo            				t_ind_no_labo            ;
v_ind_trap               				t_ind_trap               ;

begin      
  
  DELETE FROM CRA_FERIA CF
   WHERE CF.NUM_ANIO = psAnhio
     AND CF.CACT_OID_ACTI = psOidActiRege;

  OPEN c_calen_acti;
    LOOP
      FETCH c_calen_acti BULK COLLECT
         INTO v_cgzo_oid_cabe_grup_zona,
              v_fec_feri               ,
              v_ind_fest               ,
              v_ind_no_labo            ,
              v_ind_trap                    
              LIMIT 1000;

      IF v_cgzo_oid_cabe_grup_zona.count > 0 THEN

        FOR i IN v_cgzo_oid_cabe_grup_zona.first .. v_cgzo_oid_cabe_grup_zona.last loop
                             
            INSERT INTO 
            CRA_FERIA (  OID_FERI                ,
                        CGZO_OID_CABE_GRUP_ZONA ,
                        CACT_OID_ACTI           ,
                        NUM_ANIO                ,
                        FEC_FERI                ,
                        IND_FEST                ,
                        IND_NO_LABO             ,
                        IND_TRAP                ,
                        USU_REGI                ,
                        FEC_REGI                ,
                        USU_MODI                ,
                        FEC_MODI                
                       ) VALUES
                       (
                         CRA_FERI_SEQ.Nextval ,
                         v_cgzo_oid_cabe_grup_zona(i),
                         psOidActiRege,
                         psAnhio,
                         v_fec_feri(i),
                         v_ind_fest(i),
                         v_ind_no_labo(i),
                         v_ind_trap(i),
                         psUsuario,
                         sysdate,
                         null,
                         null
                       );               
        END LOOP;

      END IF;

      EXIT WHEN c_calen_acti%NOTFOUND;
      END LOOP;
    CLOSE c_calen_acti;

EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_COPIA_CALEN_POR_ACTIV ' || ls_sqlerrm);    
          
END CRA_PR_COPIA_CALEN_POR_ACTIV;

 PROCEDURE  CRA_PR_INSER_GRUPO_ZONA(psCodigoPais   seg_pais.cod_pais%type,
                                    psMarca        VARCHAR2,
                                    psCanal        VARCHAR2,
                                    psAcceso       VARCHAR2,
                                    psNombreGrupo  cra_cabec_grupo_zona.nom_grup%type,
                                    psUsuario       cra_crono.usu_regi%type,
                                    psAnhio         cra_calen.num_anio%type)  IS
                                    
     ln_oid         NUMBER(12);
     ln_oid_grupo   NUMBER(12);
 
 BEGIN
       ln_oid:=CRA_CGZO_SEQ.nextval;
        
        INSERT INTO CRA_CABEC_GRUPO_ZONA
		         (OID_CABE_GRUP_ZONA,
		          PAIS_OID_PAIS,
		          MARC_OID_MARC,
		          CANA_OID_CANA,
		          ACCE_OID_ACCE,
		          COD_GRUP,
		          IND_ESTA,
		          TIFA_OID_TIPO_FACT,
		          NOM_GRUP,
		          USU_REGI,
		          FEC_REGI)
		       VALUES
		         (ln_oid,
		          GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais),
		          GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psMarca),
		          GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCanal),
		          (SELECT AC.OID_ACCE
		             FROM SEG_ACCES AC
		            WHERE AC.COD_ACCE = psAcceso
		              AND AC.CANA_OID_CANA =
		                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCanal)),
		          CRA_CCGZ_SEQ.NEXTVAL,
		          1,
		          1,
		          psNombreGrupo,		          
		          psUsuario,
		          SYSDATE);
              
       BEGIN  
        select cf.cgzo_oid_cabe_grup_zona into ln_oid_grupo
          from cra_feria cf
         where cf.num_anio = psAnhio
           and rownum = 1
         group by cf.cgzo_oid_cabe_grup_zona;
        EXCEPTION
           WHEN no_data_found THEN
             ln_oid_grupo := 0;
        END;
        
        IF (ln_oid_grupo <> 0) THEN
            insert into cra_feria
            select CRA_FERI_SEQ.Nextval,ln_oid ,cf.cact_oid_acti, cf.num_anio, cf.fec_feri, cf.ind_fest, cf.ind_no_labo,psUsuario ,sysdate,null,null,cf.ind_trap
              from cra_feria cf
             where cf.num_anio = psAnhio
               and cf.cgzo_oid_cabe_grup_zona = ln_oid_grupo;
        END IF;          
              
 EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_INSER_GRUPO_ZONA ' || ls_sqlerrm);    
          
 END CRA_PR_INSER_GRUPO_ZONA;
 
 PROCEDURE CRA_PR_DELETE_GRUPO_ZONA(psOidGrupoZona        cra_feria.cgzo_oid_cabe_grup_zona%type,
                                    psUsuario             cra_feria.usu_regi%type) IS
 BEGIN
  
  UPDATE CRA_CABEC_GRUPO_ZONA CAB
		 SET CAB.IND_ESTA = '0', CAB.USU_MODI =psUsuario, CAB.FEC_MODI = sysdate
	 WHERE CAB.OID_CABE_GRUP_ZONA = psOidGrupoZona;
     
   delete from cra_matri_dias cmd where cmd.cgzo_oid_cabe_grup_zona = psOidGrupoZona;
       
 EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_DELETE_GRUPO_ZONA ' || ls_sqlerrm);    
          
 END CRA_PR_DELETE_GRUPO_ZONA;                
  
  PROCEDURE CRA_PR_PROCE_CARGA_PERIO_CORPO(psCodigoPais              VARCHAR2,
                                           psAnhio                   VARCHAR2) as   

    ln_oid_tipo_peri             NUMBER;
    ln_durac                     NUMBER;
    ln_peris                     NUMBER;
    i                            BINARY_INTEGER:= 0;
    lv_fec_ini                   VARCHAR2(10);
    
  BEGIN  
        SELECT STP.OID_TIPO_PERI,  
               STP.NUM_DIAS,  
               STP.NUM_PERI_ANIO
               INTO ln_oid_tipo_peri, ln_durac, ln_peris
          FROM SEG_TIPO_PERIO STP
         WHERE STP.COD_TIPO_PERI = 'CM';
         
        BEGIN
          SELECT TO_CHAR(CP.FEC_FINA + 1,'dd/mm/yyyy') INTO lv_fec_ini
            FROM CRA_PERIO CP, SEG_PERIO_CORPO SPC, SEG_PAIS SP   
            WHERE SPC.OID_PERI = CP.PERI_OID_PERI
            AND SP.OID_PAIS = CP.PAIS_OID_PAIS
            AND SP.COD_PAIS = psCodigoPais
            AND SPC.COD_PERI =  cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psAnhio||'01',-1); 
        EXCEPTION 
            WHEN no_data_found THEN
              lv_fec_ini := '01/01/'||psAnhio;
            WHEN OTHERS THEN
              lv_fec_ini := '01/01/'||psAnhio;
        END;          
         
         WHILE i < ln_peris LOOP
           
            INSERT INTO SEG_PERIO_CORPO 
              (OID_PERI, 
              TIPE_OID_TIPO_PERI,
              COD_PERI,
              VAL_ANIO)
            VALUES (
              SEG_PERI_SEQ.NEXTVAL,
              ln_oid_tipo_peri,
              cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psAnhio||'01',i),
              psAnhio);
              
            INSERT INTO GTT_CRA_PERIO_NUEVO_CORPO
               (COD_PERI,
                FEC_INIC,
                FEC_FIN,
                DURAC
                )
            VALUES(
                cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(psAnhio||'01',i),
                TO_DATE(lv_fec_ini,'DD/MM/YYYY'),
                 TO_DATE(lv_fec_ini,'DD/MM/YYYY') + ln_durac,
                ln_durac);
                
                lv_fec_ini := TO_DATE(lv_fec_ini,'DD/MM/YYYY') + ln_durac + 1;
                i:=i+1;                 
           
         END LOOP;                 
            
  EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_CARGA_PERIO_CORPO ' || ls_sqlerrm);
  END  CRA_PR_PROCE_CARGA_PERIO_CORPO;   
  
  FUNCTION  CRA_FN_CALCU_DESPL_VALID(psCodigoPais       seg_pais.cod_pais%type,
                                     psOidgrupoZona     cra_cabec_grupo_zona.oid_cabe_grup_zona%type,
                                     psOidActiv         cra_activ.oid_acti%type,
                                     psFechaInicio      date,
                                     psDesplazamiento   cra_crono_grupo_zona.val_fec0%type)  RETURN NUMBER 
  IS 

 ln_desplazamiento   number(8):=0;
 ln_cont             number(8):=0;
 ln_tot_feri_NoLabo  number(8):=0;
 ln_ind_labo         number(1);
 delta               number(1);                                                                       
 
    BEGIN  
      select ca.ind_labo into ln_ind_labo from cra_activ ca, seg_pais pa where ca.oid_acti = psOidActiv
      and ca.pais_oid_pais=pa.oid_pais
      and pa.cod_pais=psCodigoPais;
      
    if (psDesplazamiento < 0) then
        delta := -1;
      else
        delta := 1;
      end if;

    if (ln_ind_labo = 0) then --Continuo
      ln_cont := psDesplazamiento*delta+1;
    else --Laborable
         
          while (ln_desplazamiento <= psDesplazamiento*delta) loop

              select count(*) into ln_tot_feri_NoLabo
            from cra_feria cf
           where cf.cgzo_oid_cabe_grup_zona = psOidgrupoZona
             and cf.cact_oid_acti = psOidActiv
             and ( (cf.ind_fest = 1 and cf.ind_no_labo= 0 ) or (cf.ind_fest = 0 and cf.ind_no_labo= 1 )
                 )--feriado o no laborable
             and cf.fec_feri = psFechaInicio + ln_cont*delta;
           
             if (ln_tot_feri_NoLabo = 0) then --si no es feriado ni laborable
                ln_desplazamiento := ln_desplazamiento+1;
              end if;                       
        
          ln_cont := ln_cont + 1;
     end loop;

    end if;
      RETURN (ln_cont - 1)*delta;                                                                       
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_FN_CALCU_DESPL_VALID: '||ls_sqlerrm);
     return 0;
      
 END CRA_FN_CALCU_DESPL_VALID; 
 
procedure CRA_PR_RECUR_GENER_CRONO_FASE1 (psOidGrupoZona        cra_cabec_grupo_zona.oid_cabe_grup_zona%type,
                                          psOidActiOri          cra_activ.oid_acti%type,
                                          psOidPeriodo          seg_perio_corpo.oid_peri%type,
                                          psCodigoPais          seg_pais.cod_pais%type,
                                          psFecIni              date,
                                          psUsuario             cra_crono.usu_regi%type)is
                                                                                               
  cursor c_fase1Recur is --los hijos
    select md.cact_oid_acti, md.num_dias_refe, ca.ind_labo
      from cra_matri_dias       md,
           cra_cabec_grupo_zona ccgz,
           cra_activ            ca,
           seg_pais             pa
     where md.cgzo_oid_cabe_grup_zona = ccgz.oid_cabe_grup_zona
       and ca.oid_acti = md.cact_oid_acti
       and ccgz.pais_oid_pais = ca.pais_oid_pais
       and ca.pais_oid_pais = pa.oid_pais
       and pa.cod_pais = psCodigoPais
       and ccgz.oid_cabe_grup_zona = psOidGrupoZona
       and ca.ind_acti = '1'
       and ccgz.ind_esta = '1'
       and nvl(ca.cact_oid_acti,
               (select cca.oid_acti
                  from cra_activ cca
                 where upper(cca.nom_acti) like '%INICIO DE PERIODO%'
                   and cca.pais_oid_pais = pa.oid_pais)) = psOidActiOri
       and ca.oid_acti <>
           (select cca.oid_acti
              from cra_activ cca
             where upper(cca.nom_acti) like '%INICIO DE PERIODO%'
               and cca.pais_oid_pais = pa.oid_pais)
       --and nvl(ca.cact_oid_acti, 1) = psOidActiOri
       --and ca.oid_acti <> 1
     order by ca.oid_acti;

  TYPE t_oid_acti      IS TABLE OF cra_activ.oid_acti%TYPE;
  TYPE t_num_dias_refe IS TABLE OF cra_matri_dias.num_dias_refe%TYPE;
  TYPE t_ind_labo      IS TABLE OF cra_activ.ind_labo%TYPE;
  
  v_oid_acti           t_oid_acti;
  v_num_dias_refe      t_num_dias_refe;
  v_ind_labo           t_ind_labo;
  dt_fec_prev          date;
  cont                 number;
  oidActiIni           number;
  
   rows NATURAL := 1000; -- Number of rows to process at a time
   i    BINARY_INTEGER := 0;
  
BEGIN

    OPEN c_fase1Recur;
    LOOP

      FETCH c_fase1Recur BULK COLLECT
         INTO v_oid_acti,
              v_num_dias_refe,
              v_ind_labo
              LIMIT rows;

      IF v_oid_acti.count > 0 THEN
        FOR i IN v_oid_acti.first .. v_oid_acti.last loop
           
            --Calcula fecha en base al desplazamiento y respetando la configuración de feriados, dias no laborables o continuos
            dt_fec_prev:= psFecIni + cra_pkg_proce.cra_fn_calcu_despl_valid(psCodigoPais,psOidGrupoZona,v_oid_acti(i),psFecIni, v_num_dias_refe(i)); --fec_prev

           insert into cra_crono_grupo_zona
            (perd_oid_peri,
             oid_cron_grup_zona,
             cgzo_oid_cabe_grup_zona,
             cact_oid_acti,
             val_fec0,  
             COD_VIST,           
             fec_prev,
             cod_tipo_dias,
             tifa_oid_tipo_fact,
             usu_regi,
             fec_regi)
          values
            (psOidPeriodo,
             CRA_CRGZ_SEQ.Nextval,
             psOidGrupoZona,
             v_oid_acti(i),
             v_num_dias_refe(i),  --val_fec0,    
             1,     
             dt_fec_prev,
             v_ind_labo(i),   --COD_VIST
             '1',
             psUsuario,
             sysdate); 
             
             CRA_PKG_PROCE.CRA_PR_RECUR_GENER_CRONO_FASE1(psOidGrupoZona,v_oid_acti(i),psOidPeriodo,psCodigoPais,dt_fec_prev,psUsuario);        
       
        END LOOP;
       END IF;
      EXIT WHEN c_fase1Recur%NOTFOUND;
      
      begin
        select cca.oid_acti into oidActiIni      
          from cra_activ cca, seg_pais pa
         where upper(cca.nom_acti) like '%INICIO DE PERIODO%'
           and cca.pais_oid_pais = pa.oid_pais
           and pa.cod_pais = psCodigoPais;
       exception
              when no_data_found then
                oidActiIni := 0;
              when others then
                oidActiIni := 0;
          end;

      if ( psOidActiOri = oidActiIni )then
        
      select count(*)
        into cont
        from cra_matri_dias       md,
             cra_cabec_grupo_zona ccgz,
             cra_activ            ca,
             seg_pais             pa
       where md.cgzo_oid_cabe_grup_zona = ccgz.oid_cabe_grup_zona
         and ca.oid_acti = md.cact_oid_acti
         and ccgz.pais_oid_pais = ca.pais_oid_pais
         and ca.pais_oid_pais = pa.oid_pais
         and pa.cod_pais = psCodigoPais
         and ccgz.oid_cabe_grup_zona = psOidGrupoZona
         and ca.ind_acti = '1'
         and nvl(ca.cact_oid_acti,
                 (select cca.oid_acti
                    from cra_activ cca
                   where upper(cca.nom_acti) like '%INICIO DE PERIODO%'
                     and cca.pais_oid_pais = pa.oid_pais)) = psOidActiOri
         and ca.oid_acti <>
             (select cca.oid_acti
                from cra_activ cca
               where upper(cca.nom_acti) like '%INICIO DE PERIODO%'
                 and cca.pais_oid_pais = pa.oid_pais)
      -- and nvl(ca.cact_oid_acti, 1) = 1
      -- and ca.oid_acti <> 1 
       order by ca.oid_acti;
     
        EXIT WHEN c_fase1Recur%rowcount = cont;
      end if;
        
      END LOOP; 
      
    CLOSE c_fase1Recur;

EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_RECUR_GENER_CRONO_FASE1 ' || ls_sqlerrm);    
          
End CRA_PR_RECUR_GENER_CRONO_FASE1;        

procedure CRA_PR_PROCE_GENER_CRONO_FASE1 (psCodigoPeriodo varchar2,
                                          psCodigoPais    varchar2,
                                          psCantidadColumnas out varchar2,
                                          psFechaInicio out varchar2,
                                          psUsuario     varchar2) is
  
 cursor c_fase1 is

    select  ccgz.oid_cabe_grup_zona, ca.oid_acti, md.num_dias_refe, ca.ind_labo 
      from cra_matri_dias md, cra_cabec_grupo_zona ccgz, cra_activ ca, seg_pais pa
     where md.cgzo_oid_cabe_grup_zona = ccgz.oid_cabe_grup_zona
       and ca.oid_acti = md.cact_oid_acti
       and ccgz.pais_oid_pais = ca.pais_oid_pais
       and ca.pais_oid_pais = pa.oid_pais
       and pa.cod_pais=psCodigoPais
       and ca.ind_acti = '1'
       and ccgz.ind_esta ='1'
       and UPPER(ca.nom_acti) like '%INICIO DE PERIODO%'
     order by 2;

TYPE t_oid_grupo_zona  IS TABLE OF cra_cabec_grupo_zona.oid_cabe_grup_zona%TYPE;
TYPE t_oid_acti_ini    IS TABLE OF cra_activ.cact_oid_acti%TYPE;
TYPE t_num_dias_refe   IS TABLE OF cra_activ.num_camp_refe%TYPE;
TYPE t_ind_labo        IS TABLE OF cra_activ.ind_labo%TYPE;

v_oid_grupo_zona   t_oid_grupo_zona ;
v_oid_acti_ini     t_oid_acti_ini ;
v_num_dias_refe    t_num_dias_refe ;
v_ind_labo         t_ind_labo ;
   rows NATURAL := 1000; -- Number of rows to process at a time
   i    BINARY_INTEGER := 0;

lv_oid_peri   cra_perio.oid_peri%type;
lv_fec_inic   cra_perio.fec_inic%type;
lv_fec_fina   cra_perio.fec_fina%type;
dt_fec_prev   date;

begin

     select cp.oid_peri,
            cp.fec_inic,
            cp.fec_fina,
            (cp.fec_fina - cp.fec_inic)+1
      into lv_oid_peri,
           lv_fec_inic,
           lv_fec_fina,
           psCantidadColumnas
      from cra_perio cp,
           seg_perio_corpo sp
    where cp.peri_oid_peri = sp.oid_peri
    and sp.cod_peri = psCodigoPeriodo;

  psFechaInicio :=  to_char(lv_fec_inic,'dd/mm/yyyy');

  delete cra_tempo_crono_fase1;
  delete from cra_crono_grupo_zona gz where gz.perd_oid_peri = lv_oid_peri;
  
  OPEN c_fase1;
    LOOP
      FETCH c_fase1 BULK COLLECT
         INTO v_oid_grupo_zona ,
              v_oid_acti_ini   ,
              v_num_dias_refe  ,
              v_ind_labo 
              LIMIT rows;

      IF v_oid_grupo_zona.count > 0 THEN

        FOR i IN v_oid_grupo_zona.first .. v_oid_grupo_zona.last loop    
          
            --inserta padre
            --Calcula fecha en base al desplazamiento y respetando la configuración de feriados, dias no laborables o continuos
            dt_fec_prev:= lv_fec_inic + cra_pkg_proce.cra_fn_calcu_despl_valid(psCodigoPais,v_oid_grupo_zona(i),v_oid_acti_ini(i),lv_fec_inic, v_num_dias_refe(i)); --fec_prev

           insert into cra_crono_grupo_zona
            (perd_oid_peri,
             oid_cron_grup_zona,
             cgzo_oid_cabe_grup_zona,
             cact_oid_acti,
             val_fec0,  
             COD_VIST,           
             fec_prev,
             cod_tipo_dias,
             tifa_oid_tipo_fact,
             usu_regi,
             fec_regi)
          values
            (lv_oid_peri,
             CRA_CRGZ_SEQ.Nextval,
             v_oid_grupo_zona(i),
             v_oid_acti_ini(i),
             v_num_dias_refe(i),  --val_fec0,    
             1,     
             dt_fec_prev,
             v_ind_labo(i),   --COD_VIST
             '1',
             psUsuario,
             sysdate); 
              
             --evalua hijos                               
             CRA_PKG_PROCE.CRA_PR_RECUR_GENER_CRONO_FASE1(v_oid_grupo_zona(i),v_oid_acti_ini(i),lv_oid_peri,psCodigoPais,dt_fec_prev, psUsuario);
        END LOOP;

      END IF;

      EXIT WHEN c_fase1%NOTFOUND;
      END LOOP;
    CLOSE c_fase1;
EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_GENER_CRONO_FASE1 ' || ls_sqlerrm);    
    
end CRA_PR_PROCE_GENER_CRONO_FASE1;

procedure CRA_PR_PROCE_CARGA_CRONO_FASE1 (psCodigoPeriodo varchar2,
                                          psCodigoPais    varchar2,
                                          psCantidadColumnas out varchar2,
                                          psFechaInicio out varchar2) is

 cursor c_fase1(lv_oid_peri number,lv_fec_inic date ) is

      select cgz.cgzo_oid_cabe_grup_zona,
           ccg.nom_grup,
           cgz.cact_oid_acti,
           ca.nom_acti,
           cgz.val_fec0,
           cgz.fec_prev,
           cgz.fec_prev - lv_fec_inic
      from cra_crono_grupo_zona cgz,
           cra_cabec_grupo_zona ccg,
           cra_activ            ca,
           seg_pais             pa
     where cgz.cgzo_oid_cabe_grup_zona = ccg.oid_cabe_grup_zona
       and cgz.cact_oid_acti = ca.oid_acti
       and cgz.perd_oid_peri = lv_oid_peri
       and ccg.pais_oid_pais = ca.pais_oid_pais
       and ca.pais_oid_pais = pa.oid_pais
       and pa.cod_pais = psCodigoPais
       and ca.ind_acti = '1'
       and ccg.ind_esta = '1'
     order by 1, 6;
   
TYPE t_oid_grup_zona    IS TABLE OF  cra_cabec_grupo_zona.oid_cabe_grup_zona%TYPE;
TYPE t_nom_grup_zona    IS TABLE OF  cra_cabec_grupo_zona.nom_grup%TYPE;       
TYPE t_cact_oid_acti    IS TABLE OF  cra_activ.oid_acti%TYPE;       
TYPE t_nom_acti         IS TABLE OF  cra_activ.nom_acti%TYPE;                  
TYPE t_val_fec0         IS TABLE OF  cra_crono.val_fec0%TYPE;      
TYPE t_fec_prev         IS TABLE OF  cra_crono.fec_inic%TYPE;     
TYPE t_orden            IS TABLE OF  cra_crono.val_fec0%TYPE;

v_oid_grup_zona         t_oid_grup_zona;
v_nom_grup_zona         t_nom_grup_zona;
v_cact_oid_acti          t_cact_oid_acti;
v_nom_acti               t_nom_acti     ;
v_val_fec0               t_val_fec0     ;
v_fec_prev               t_fec_prev     ;
v_orden                  t_orden        ;

l_oid                   cra_cabec_grupo_zona.oid_cabe_grup_zona%type;
l_nom                   cra_cabec_grupo_zona.nom_grup%type;
dt_fec                  date;
l_orden                 cra_matri_dias.num_dias_refe%TYPE;
lv_aux                  varchar2(4000) := '';
lv_oid_peri             number;
lv_fec_inic             date;
begin
    
     delete cra_tempo_crono_fase1;   

     select cp.oid_peri,
            cp.fec_inic,
            (cp.fec_fina - cp.fec_inic)+1
      into lv_oid_peri,
           lv_fec_inic,
           psCantidadColumnas
      from cra_perio cp,
           seg_perio_corpo sp
    where cp.peri_oid_peri = sp.oid_peri
    and sp.cod_peri = psCodigoPeriodo;

  psFechaInicio :=  to_char(lv_fec_inic,'dd/mm/yyyy');             
  
  OPEN c_fase1(lv_oid_peri,lv_fec_inic);
    LOOP
      FETCH c_fase1 BULK COLLECT
         INTO v_oid_grup_zona ,     
              v_nom_grup_zona ,        
              v_cact_oid_acti  ,        
              v_nom_acti       ,        
              v_val_fec0       ,        
              v_fec_prev       ,        
              v_orden                  
              LIMIT 1000;

      IF v_oid_grup_zona.count > 0 THEN

        FOR i IN v_oid_grup_zona.first .. v_oid_grup_zona.last loop                                  

            if i=1 then
              l_oid := v_oid_grup_zona(i);
              l_orden := v_orden(i);
            end if;
            
            if ( l_oid = v_oid_grup_zona(i) and  l_orden = v_orden(i) ) then
               lv_aux :=  lv_aux ||'|'|| v_nom_acti(i);                  

            else         
              
              insert into cra_tempo_crono_fase1
                 (oid_grup_zona,
                  val_grup_zona,
                  val_fech,
                  val_acti,
                  val_dia)
               values
                 (l_oid,
                  l_nom,
                  dt_fec,
                  lv_aux,
                  l_orden);                                               
            
               lv_aux :=  '|'||v_nom_acti(i);
               
            end if;
            l_oid := v_oid_grup_zona(i);
            l_nom := v_nom_grup_zona(i);
            dt_fec := v_fec_prev(i);          
            l_orden := v_orden(i);           
                  
        END LOOP;
        --para el ultimo        
              insert into cra_tempo_crono_fase1
                 (oid_grup_zona,
                  val_grup_zona,
                  val_fech,
                  val_acti,
                  val_dia)
               values
                 (l_oid,
                  l_nom,
                  dt_fec,
                  lv_aux,
                  l_orden); 
        
      END IF;

      EXIT WHEN c_fase1%NOTFOUND;
      END LOOP;
    CLOSE c_fase1;

EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_CARGA_CRONO_FASE1 ' || ls_sqlerrm);    
    
end CRA_PR_PROCE_CARGA_CRONO_FASE1;

procedure CRA_PR_RECUR_ACTUA_CRONO_FASE1 (psOidActividad        cra_crono.cact_oid_acti%type,
                                          psOidPeriodo          cra_crono.perd_oid_peri%type,
                                          psFechaPapa           date,
                                          psCodigoPais          seg_pais.cod_pais%type,
                                          psUsuario             cra_crono.usu_regi%type,
                                          psOidGrupoZona        cra_crono_grupo_zona.oid_cron_grup_zona%type)is
                                                                                                     
  cursor c_actis is
  select cr.oid_cron_grup_zona, cr.cact_oid_acti, cr.val_fec0
    from cra_crono_grupo_zona cr, cra_perio cp, cra_activ ca, seg_pais sp
   where cr.perd_oid_peri = cp.oid_peri
     and cp.pais_oid_pais = ca.pais_oid_pais
     and ca.pais_oid_pais = sp.oid_pais
     and sp.cod_pais = psCodigoPais
     and cp.oid_peri = psOidPeriodo
     and cr.cgzo_oid_cabe_grup_zona = psOidGrupoZona
     and cr.cact_oid_acti = ca.oid_acti
     and ca.cact_oid_acti = psOidActividad
  order by cr.cact_oid_acti;

  TYPE t_oid_cron IS TABLE OF cra_crono.oid_cron%TYPE;
  TYPE t_oid_acti IS TABLE OF cra_activ.oid_acti%TYPE;
  TYPE t_val_fec0 IS TABLE OF cra_crono.val_fec0%TYPE;
  
  v_oid_cron   t_oid_cron;
  v_oid_acti   t_oid_acti;
  v_val_fec0   t_val_fec0;
  dt_fec_prev  date;
  
   rows NATURAL := 1000; -- Number of rows to process at a time
   i    BINARY_INTEGER := 0;
   cont number;
  
BEGIN  
    
    OPEN c_actis;
    LOOP
      FETCH c_actis BULK COLLECT
         INTO v_oid_cron,
              v_oid_acti,
              v_val_fec0
              LIMIT rows;

      IF v_oid_acti.count > 0 THEN
        FOR i IN v_oid_acti.first .. v_oid_acti.last loop
          
         --Calcula fecha en base al desplazamiento y respetando la configuración de feriados, dias no laborables o continuos
            dt_fec_prev:= psFechaPapa + cra_pkg_proce.cra_fn_calcu_despl_valid(psCodigoPais,psOidGrupoZona,v_oid_acti(i),psFechaPapa, v_val_fec0(i));
           
             update cra_crono_grupo_zona  cr
             set cr.fec_prev = dt_fec_prev,
                 cr.usu_modi = psUsuario,
                 cr.fec_modi = sysdate
             where  cr.oid_cron_grup_zona = v_oid_cron(i);
             cra_pkg_proce.CRA_PR_RECUR_ACTUA_CRONO_FASE1(v_oid_acti(i),psOidPeriodo,dt_fec_prev,psCodigoPais, psUsuario, psOidGrupoZona);        
       
        END LOOP;
       END IF;
       EXIT WHEN c_actis%NOTFOUND;
      
      --if (psOidActiOri = 1)then
        
          select count(*) into cont
    from cra_crono_grupo_zona cr, cra_perio cp, cra_activ ca, seg_pais sp
   where cr.perd_oid_peri = cp.oid_peri
     and cp.pais_oid_pais = ca.pais_oid_pais
     and ca.pais_oid_pais = sp.oid_pais
     and sp.cod_pais = psCodigoPais
     and cp.oid_peri = psOidPeriodo
     and cr.cgzo_oid_cabe_grup_zona = psOidGrupoZona
     and cr.cact_oid_acti = ca.oid_acti
     and ca.cact_oid_acti = psOidActividad;
     
        EXIT WHEN c_actis%rowcount = cont;
      --end if;
      
      END LOOP;
    CLOSE c_actis; 

EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_RECUR_ACTUA_CRONO_FASE1 ' || ls_sqlerrm);    
          
End CRA_PR_RECUR_ACTUA_CRONO_FASE1; 

procedure CRA_PR_PROCE_ACTUA_CRONO_FASE1 (psCodigoPais           seg_pais.cod_pais%type,
                                          psOidCrono             cra_crono.oid_cron%type,
                                          psOidGrupoZona         cra_cabec_grupo_zona.oid_cabe_grup_zona%type,
                                          psDiasDesplazamiento   number, --respecto a la actividad origen                                          
                                          psUsuario              cra_crono.usu_regi%type)  IS
    
     ln_oid_acti          number(8);
     ln_oid_peri          number(8);
     dt_fec_prev          date;
                                                
BEGIN
     
     select cgz.cact_oid_acti, cgz.perd_oid_peri, cgzPa.Fec_Prev
       into ln_oid_acti, ln_oid_peri, dt_fec_prev --Fecha del Padre            
       from cra_crono_grupo_zona cgz,
            cra_crono_grupo_zona cgzPa,
            cra_activ            ca,
            seg_pais             pa
      where cgz.oid_cron_grup_zona = psOidCrono
        and cgz.cact_oid_acti = ca.oid_acti
        and ca.pais_oid_pais = pa.oid_pais
        and pa.cod_pais = psCodigoPais
        and cgzPa.Cgzo_Oid_Cabe_Grup_Zona = cgz.cgzo_oid_cabe_grup_zona
        and cgzPa.Perd_Oid_Peri = cgz.perd_oid_peri
        and cgzPa.Cact_Oid_Acti =
            nvl(ca.cact_oid_acti,
                (select cca.oid_acti
                   from cra_activ cca
                  where upper(cca.nom_acti) like '%INICIO DE PERIODO%'
                    and cca.pais_oid_pais = pa.oid_pais));
     
     --Calcula fecha en base al desplazamiento y respetando la configuración de feriados, dias no laborables o continuos
     dt_fec_prev:= dt_fec_prev + cra_pkg_proce.cra_fn_calcu_despl_valid(psCodigoPais,psOidGrupoZona,ln_oid_acti,dt_fec_prev, psDiasDesplazamiento);
     
    --Primera vez (solo actividad padre) se actualiza el desplazamiento y fecha
    update cra_crono_grupo_zona cgz
    set cgz.val_fec0 = psDiasDesplazamiento,
        cgz.fec_prev = dt_fec_prev,
        cgz.usu_modi = psUsuario,
        cgz.fec_modi = sysdate
    where cgz.oid_cron_grup_zona = psOidCrono;
    
    --Para el resto se realiza la recursividad
    CRA_PKG_PROCE.CRA_PR_RECUR_ACTUA_CRONO_FASE1(ln_oid_acti,ln_oid_peri,dt_fec_prev,psCodigoPais, psUsuario, psOidGrupoZona);
    
EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_ACTUA_CRONO_FASE1 ' || ls_sqlerrm);    
          
End CRA_PR_PROCE_ACTUA_CRONO_FASE1; 

procedure CRA_PR_PROCE_CARGA_CRONO_FASE2 (psCodigoPeriodo varchar2,
                                          psCodigoPais    varchar2,
                                          psOidZona       varchar2,
                                          psCantidadColumnas out varchar2,
                                          psFechaInicio out varchar2) is
  
 cursor c_fase2(lv_oid_peri number,lv_fec_inic date ) is

  select ccg.oid_cabe_grup_zona,
         zz.oid_zona,
         zz.des_zona,
         ca.nom_acti,
         cr.val_fec0,
         cr.fec_inic,
         cr.fec_inic - lv_fec_inic orden
    from cra_crono cr,
         cra_cabec_grupo_zona ccg,
         cra_detal_grupo_zona cdg,
         cra_activ            ca,
         zon_zona             zz,
         seg_pais             pa
   where  cdg.cgzo_oid_cabe_grup_zona = ccg.oid_cabe_grup_zona
     and cdg.zzon_oid_zona = cr.zzon_oid_zona
     and cdg.zzon_oid_zona = zz.oid_zona    
     and cr.cact_oid_acti = ca.oid_acti
     and cr.perd_oid_peri = lv_oid_peri
     and ccg.pais_oid_pais = zz.pais_oid_pais
     and  zz.pais_oid_pais = pa.oid_pais
     and pa.cod_pais = psCodigoPais
     and cdg.zzon_oid_zona = nvl( psOidZona, cdg.zzon_oid_zona) 
     and ccg.ind_esta = 1
     and ca.ind_acti = 1
   order by 1,2,6;
     
TYPE t_oid_grup_zona    IS TABLE OF  cra_cabec_grupo_zona.oid_cabe_grup_zona%TYPE;
TYPE t_oid_zona         IS TABLE OF  cra_crono.zzon_oid_zona%TYPE;       
TYPE t_des_zona         IS TABLE OF  zon_zona.des_zona%TYPE;           
TYPE t_nom_acti         IS TABLE OF  cra_activ.nom_acti%TYPE;            
TYPE t_val_fec0         IS TABLE OF  cra_crono.val_fec0%TYPE;      
TYPE t_fec_prev         IS TABLE OF  cra_crono.fec_inic%TYPE;     
TYPE t_orden            IS TABLE OF  cra_crono.val_fec0%TYPE;

v_oid_grup_zona         t_oid_grup_zona;
v_oid_zona               t_oid_zona     ;
v_des_zona               t_des_zona     ;
v_nom_acti               t_nom_acti     ;
v_val_fec0               t_val_fec0     ;
v_fec_prev               t_fec_prev     ;
v_orden                  t_orden        ;

l_oid                   zon_zona.oid_zona%type;
l_oidGrupoZona          cra_cabec_grupo_zona.oid_cabe_grup_zona%type;
l_desZona               zon_zona.des_zona%type;
dt_fec                  date;
l_orden                 cra_matri_dias.num_dias_refe%TYPE;
lv_aux                  varchar2(4000) := '';
lv_oid_peri             number;
lv_fec_inic             date;

begin
   
     select cp.oid_peri,
            cp.fec_inic,
            (cp.fec_fina - cp.fec_inic)+1
      into lv_oid_peri,
           lv_fec_inic,
           psCantidadColumnas
      from cra_perio cp,
           seg_perio_corpo sp
    where cp.peri_oid_peri = sp.oid_peri
    and sp.cod_peri = psCodigoPeriodo;

  psFechaInicio :=  to_char(lv_fec_inic,'dd/mm/yyyy');             
  
  OPEN c_fase2(lv_oid_peri,lv_fec_inic);
    LOOP
      FETCH c_fase2 BULK COLLECT
         INTO v_oid_grup_zona,
              v_oid_zona     ,
              v_des_zona     ,
              v_nom_acti     ,
              v_val_fec0     ,
              v_fec_prev     ,
              v_orden        
              LIMIT 1000;

      IF v_oid_grup_zona.count > 0 THEN

        FOR i IN v_oid_grup_zona.first .. v_oid_grup_zona.last loop
                                   
            if i=1 then
              l_oid := v_oid_zona(i);
              l_orden := v_orden(i);
            end if;
          
            if ((l_oid = v_oid_zona(i)) and (l_orden = v_orden(i))) then

               lv_aux :=  lv_aux ||'|'|| v_nom_acti(i);                  

            else
          
              insert into cra_tempo_crono_fase2
                 (oid_grup_zona,
                  oid_zona,
                  val_zona,
                  val_fech,
                  val_acti,
                  val_dia)
               values
                 (l_oidGrupoZona,
                  l_oid,
                  l_desZona,
                  dt_fec,
                  lv_aux,
                  l_orden);
               
               lv_aux := '|'||v_nom_acti(i);                  

            
            end if;

            l_oid := v_oid_zona(i);
            l_orden := v_orden(i);
            l_oidGrupoZona := v_oid_grup_zona(i);
            l_desZona := v_des_zona(i);
            dt_fec :=v_fec_prev(i);      

        END LOOP;

        --para el ultimo        
             insert into cra_tempo_crono_fase2
                 (oid_grup_zona,
                  oid_zona,
                  val_zona,
                  val_fech,
                  val_acti,
                  val_dia)
               values
                 (l_oidGrupoZona,
                  l_oid,
                  l_desZona,
                  dt_fec,
                  lv_aux,
                  l_orden);
        
      END IF;

      EXIT WHEN c_fase2%NOTFOUND;
      END LOOP;
    CLOSE c_fase2;

  EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_CARGA_CRONO_FASE2 ' || ls_sqlerrm);    
    
end CRA_PR_PROCE_CARGA_CRONO_FASE2;

procedure CRA_PR_PROCE_GENER_CRONO_FASE2 (psCodigoPeriodo varchar2,
                                          psCodigoPais    varchar2,
                                          psOidZona       varchar2,
                                          psCantidadColumnas out varchar2,
                                          psFechaInicio out varchar2,
                                          psUsuario     varchar2) is
  
 cursor c_fase2(lv_oid_peri number,lv_fec_inic date ) is

      select cgz.perd_oid_peri,
        ccg.oid_cabe_grup_zona,
         zz.oid_zona,
         zz.des_zona,
         cgz.cact_oid_acti,
         ca.nom_acti,
         cgz.cod_tipo_dias,
         cgz.val_fec0,
         cgz.fec_prev,
         cgz.fec_prev -lv_fec_inic orden
    from cra_crono_grupo_zona cgz,
         cra_cabec_grupo_zona ccg,
         cra_detal_grupo_zona cdg,
         cra_activ            ca,
         zon_zona             zz,
         seg_pais             pa
   where cgz.cgzo_oid_cabe_grup_zona = ccg.oid_cabe_grup_zona
     and cdg.cgzo_oid_cabe_grup_zona = ccg.oid_cabe_grup_zona
     and cdg.zzon_oid_zona = zz.oid_zona
     and cgz.cact_oid_acti = ca.oid_acti
     and cgz.perd_oid_peri = lv_oid_peri
     and ccg.pais_oid_pais = zz.pais_oid_pais
     and  zz.pais_oid_pais = pa.oid_pais
     and pa.cod_pais = psCodigoPais
     and cdg.zzon_oid_zona = nvl( psOidZona, cdg.zzon_oid_zona) 
   order by 2,3,9;

TYPE t_perd_oid_peri    IS TABLE OF  cra_crono.perd_oid_peri%TYPE;  
TYPE t_oid_grup_zona    IS TABLE OF  cra_cabec_grupo_zona.oid_cabe_grup_zona%TYPE;
TYPE t_oid_zona         IS TABLE OF  cra_crono.zzon_oid_zona%TYPE;       
TYPE t_des_zona         IS TABLE OF  zon_zona.des_zona%TYPE;       
TYPE t_cact_oid_acti    IS TABLE OF  cra_crono.cact_oid_acti%TYPE;      
TYPE t_nom_acti         IS TABLE OF  cra_activ.nom_acti%TYPE;       
TYPE t_cod_tipo_dias    IS TABLE OF  cra_crono.cod_tipo_dias%TYPE;     
TYPE t_val_fec0         IS TABLE OF  cra_crono.val_fec0%TYPE;      
TYPE t_fec_prev         IS TABLE OF  cra_crono.fec_inic%TYPE;     
TYPE t_orden            IS TABLE OF  cra_crono.val_fec0%TYPE;

v_perd_oid_peri          t_perd_oid_peri;
v_oid_grup_zona         t_oid_grup_zona;
v_oid_zona               t_oid_zona     ;
v_des_zona               t_des_zona     ;
v_cact_oid_acti          t_cact_oid_acti;
v_nom_acti               t_nom_acti     ;
v_cod_tipo_dias          t_cod_tipo_dias;
v_val_fec0               t_val_fec0     ;
v_fec_prev               t_fec_prev     ;
v_orden                  t_orden        ;

l_zona                  zon_zona.oid_zona%type;
l_num_dias              cra_matri_dias.num_dias_refe%TYPE;
lv_aux                  varchar2(4000) := '';
lv_oid_peri             number;
lv_fec_inic             date;

begin

   CRA_PR_PROCE_DELET_CRONO_FASE2(psCodigoPeriodo,psOidZona);
   
     select cp.oid_peri,
            cp.fec_inic,
            (cp.fec_fina - cp.fec_inic)+1
      into lv_oid_peri,
           lv_fec_inic,
           psCantidadColumnas
      from cra_perio cp,
           seg_perio_corpo sp
    where cp.peri_oid_peri = sp.oid_peri
    and sp.cod_peri = psCodigoPeriodo;

  psFechaInicio :=  to_char(lv_fec_inic,'dd/mm/yyyy');             
  
  OPEN c_fase2(lv_oid_peri,lv_fec_inic);
    LOOP
      FETCH c_fase2 BULK COLLECT
         INTO v_perd_oid_peri,
              v_oid_grup_zona,
              v_oid_zona     ,
              v_des_zona     ,
              v_cact_oid_acti,
              v_nom_acti     ,
              v_cod_tipo_dias,
              v_val_fec0     ,
              v_fec_prev     ,
              v_orden        
              LIMIT 1000;

      IF v_perd_oid_peri.count > 0 THEN

        FOR i IN v_perd_oid_peri.first .. v_perd_oid_peri.last loop
                             
          insert into cra_crono
            (OID_CRON          , 
              PERD_OID_PERI     , 
              ZSCC_OID_SECC     , 
              CACT_OID_ACTI     , 
              COD_TIPO_DIAS     , 
              FEC_INIC          , 
              FEC_MEDI          , 
              FEC_FINA          , 
              ZZON_OID_ZONA     , 
              TIFA_OID_TIPO_FACT, 
              VAL_FEC0          , 
              IND_FIJA          , 
              COD_VIST          , 
              COD_VIST_ORIG     , 
              NUM_DIAS_DESP     , 
              IND_PEND_ORIG     , 
              PERD_OID_PERI_REFE,
              USU_REGI          ,
              FEC_REGI  
              )
          values
            (CRA_CRON_SEQ.Nextval,
             v_perd_oid_peri(i),
             null,
             v_cact_oid_acti(i),
             v_cod_tipo_dias(i),      
             v_fec_prev(i),
             null,
             null,
             v_oid_zona(i),     
             1,
             v_val_fec0(i),
             0,
             v_cact_oid_acti(i),
             v_cact_oid_acti(i),
             null,
             null,
             null,            
             psUsuario,
             sysdate);
        
            if i=1 then
              l_zona := v_oid_zona(i);
              l_num_dias := v_orden(i);
            end if;
          
            if ((l_zona = v_oid_zona(i)) and (l_num_dias = v_orden(i))) then

               lv_aux :=  lv_aux ||'|'|| v_nom_acti(i);                  

            else
          
              insert into cra_tempo_crono_fase2
                 (oid_grup_zona,
                  oid_zona,
                  val_zona,
                  val_fech,
                  val_acti,
                  val_dia)
               values
                 (v_oid_grup_zona(i),
                  v_oid_zona(i),
                  v_des_zona(i),
                  v_fec_prev(i),
                  lv_aux,
                  l_num_dias);
               
               lv_aux := '|'||v_nom_acti(i);                  

            
            end if;

            l_zona := v_oid_zona(i);
            l_num_dias := v_orden(i);

        END LOOP;

      END IF;

      EXIT WHEN c_fase2%NOTFOUND;
      END LOOP;
    CLOSE c_fase2;

EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_GENER_CRONO_FASE2 ' || ls_sqlerrm);    
    
end CRA_PR_PROCE_GENER_CRONO_FASE2;

procedure CRA_PR_PROCE_DELET_CRONO_FASE2(psCodigoPeriodo   seg_perio_corpo.cod_peri%type, 
                                         psOidZona cra_crono.zzon_oid_zona%type) is

BEGIN
  delete from cra_crono crono
   where crono.perd_oid_peri =
         (select cp.oid_peri
            from cra_perio cp, seg_perio_corpo sp
           where cp.peri_oid_peri = sp.oid_peri
             and sp.cod_peri = psCodigoPeriodo)
           and crono.zzon_oid_zona= nvl(psOidZona,crono.zzon_oid_zona);

EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_DELET_CRONO_FASE2 ' || ls_sqlerrm);    
    
end CRA_PR_PROCE_DELET_CRONO_FASE2;

procedure CRA_PR_RECUR_ACTUA_CRONO_FASE2 (psOidActividad        cra_crono.cact_oid_acti%type,
                                    psOidPeriodo          cra_crono.perd_oid_peri%type,
                                    psFechaPapa           date,
                                    psCodigoPais          seg_pais.cod_pais%type,
                                    psUsuario             cra_crono.usu_regi%type,
                                    psOidZona             cra_crono.zzon_oid_zona%type,
                                    psOidGrupoZona        cra_crono_grupo_zona.cgzo_oid_cabe_grup_zona%type)is
                                                                                               
  cursor c_actis is
  select cr.oid_cron, cr.cact_oid_acti, cr.val_fec0
    from cra_crono cr, cra_perio cp, cra_activ ca, seg_pais sp
   where cr.perd_oid_peri = cp.oid_peri
     and cp.pais_oid_pais = ca.pais_oid_pais
     and ca.pais_oid_pais = sp.oid_pais
     and sp.cod_pais = psCodigoPais
     and cp.oid_peri = psOidPeriodo
     and cr.zzon_oid_zona = psOidZona
     and cr.cact_oid_acti = ca.oid_acti
     and ca.cact_oid_acti = psOidActividad
     and ca.ind_acti='1';

  TYPE t_oid_cron IS TABLE OF cra_crono.oid_cron%TYPE;
  TYPE t_oid_acti IS TABLE OF cra_activ.oid_acti%TYPE;
  TYPE t_val_fec0 IS TABLE OF cra_crono.val_fec0%TYPE;
  
  v_oid_cron   t_oid_cron;
  v_oid_acti   t_oid_acti;
  v_val_fec0   t_val_fec0;
  
  cont         number;
  psFecha      date;
  
BEGIN

    OPEN c_actis;
    LOOP
      FETCH c_actis BULK COLLECT
         INTO v_oid_cron,
              v_oid_acti,
              v_val_fec0
              LIMIT 1000;

      IF v_oid_acti.count > 0 THEN
        FOR i IN v_oid_acti.first .. v_oid_acti.last loop
          
             psFecha := psFechaPapa + cra_pkg_proce.cra_fn_calcu_despl_valid(psCodigoPais,psOidGrupoZona,v_oid_acti(i),psFechaPapa, v_val_fec0(i));
             
             update cra_crono  cr
             set cr.fec_inic = psFecha,
                 cr.usu_modi = psUsuario,
                 cr.fec_modi = sysdate
             where  cr.oid_cron = v_oid_cron(i); 
             
             cra_pkg_proce.CRA_PR_RECUR_ACTUA_CRONO_FASE2(v_oid_acti(i),psOidPeriodo,psFecha,psCodigoPais, psUsuario, psOidZona,psOidGrupoZona);        
       
        END LOOP;
       END IF;
      EXIT WHEN c_actis%NOTFOUND;
      
       select count(*) into cont
        from cra_crono cr, cra_perio cp, cra_activ ca, seg_pais sp
       where cr.perd_oid_peri = cp.oid_peri
         and cp.pais_oid_pais = ca.pais_oid_pais
         and ca.pais_oid_pais = sp.oid_pais
         and sp.cod_pais = psCodigoPais
         and cp.oid_peri = psOidPeriodo
         and cr.zzon_oid_zona = psOidZona
         and cr.cact_oid_acti = ca.oid_acti
         and ca.cact_oid_acti = psOidActividad;
     
        EXIT WHEN c_actis%rowcount = cont;
        
      END LOOP;
    CLOSE c_actis; 

EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_RECUR_ACTUA_CRONO_FASE2 ' || ls_sqlerrm);    
          
End CRA_PR_RECUR_ACTUA_CRONO_FASE2; 

procedure CRA_PR_PROCE_ACTUA_CRONO_FASE2 (psCodigoPais           seg_pais.cod_pais%type,
                                          psOidCrono             cra_crono.oid_cron%type,
                                          psOidGrupoZona         cra_cabec_grupo_zona.oid_cabe_grup_zona%type,
                                          psDiasDesplazamiento   number, --respecto a la actividad origen                                          
                                          psUsuario              cra_crono.usu_regi%type)  IS
    
     ln_oid_acti          number(8);
     ln_oid_peri          number(8);
     ln_oid_zona          number(8);
     dt_fec_prev          date;
                                                
BEGIN
     
    select cr.cact_oid_acti,
           cr.perd_oid_peri,
           crPa.Fec_Inic,
           cr.zzon_oid_zona
      into ln_oid_acti,
           ln_oid_peri,
           dt_fec_prev, --Fecha del Padre
           ln_oid_zona
      from cra_crono cr, cra_crono crPa, cra_activ ca, seg_pais pa
     where cr.oid_cron = psOidCrono
       and cr.cact_oid_acti = ca.oid_acti
       and ca.pais_oid_pais = pa.oid_pais
       and pa.cod_pais = psCodigoPais
       and crPa.Zzon_Oid_Zona = cr.zzon_oid_zona
       and crPa.Perd_Oid_Peri = cr.perd_oid_peri
       and crPa.Cact_Oid_Acti =
           nvl(ca.cact_oid_acti,
               (select cca.oid_acti
                  from cra_activ cca
                 where upper(cca.nom_acti) like '%INICIO DE PERIODO%'
                   and cca.pais_oid_pais = pa.oid_pais));
     
     --Calcula fecha en base al desplazamiento y respetando la configuración de feriados, dias no laborables o continuos
     dt_fec_prev:= dt_fec_prev + --psDiasDesplazamiento;
     cra_pkg_proce.cra_fn_calcu_despl_valid(psCodigoPais,psOidGrupoZona,ln_oid_acti,dt_fec_prev, psDiasDesplazamiento);
     
    --Primera vez (solo actividad padre) se actualiza el desplazamiento y fecha
    update cra_crono
    set val_fec0 = psDiasDesplazamiento,
        fec_inic = dt_fec_prev,
        usu_modi = psUsuario,
        fec_modi = sysdate
    where oid_cron = psOidCrono; 
    
    --Para el resto se realiza la recursividad
    CRA_PKG_PROCE.CRA_PR_RECUR_ACTUA_CRONO_FASE2(ln_oid_acti,ln_oid_peri,dt_fec_prev,psCodigoPais, psUsuario, ln_oid_zona,psOidGrupoZona);
    
EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_PROCE_ACTUA_CRONO_FASE2 ' || ls_sqlerrm);    
          
End CRA_PR_PROCE_ACTUA_CRONO_FASE2; 

procedure CRA_PR_COPIA_CRONO_ZONA_FASE2 (psCodigoPais                 seg_pais.cod_pais%type,
                                               psCodigoPeriodo        seg_perio_corpo.cod_peri%type,
                                               PsOidZonaRege          zon_zona.oid_zona%type,
                                               PsOidZonaRefe          zon_zona.oid_zona%type,                                               
                                               psOidActividad         cra_crono.cact_oid_acti%type,
                                               psUsuario              cra_crono.usu_regi%type)  IS
       

ln_desplazamiento                NUMBER(8);
ln_oidCron                       NUMBER(8);
ln_oidGrupoZona                  NUMBER(8);


begin      
  
BEGIN
 select cr.oid_cron ,ccg.oid_cabe_grup_zona
 into ln_oidCron, ln_oidGrupoZona
 from cra_crono cr,  cra_perio cp, cra_cabec_grupo_zona ccg, cra_detal_grupo_zona ccd
 where cr.perd_oid_peri = cp.oid_peri
 and cp.val_nomb_peri = psCodigoPeriodo
 and cr.cact_oid_acti = psOidActividad
 and cr.zzon_oid_zona = ccd.zzon_oid_zona
 and ccd.cgzo_oid_cabe_grup_zona = ccg.oid_cabe_grup_zona
 and ccg.ind_esta = 1
 and ccd.zzon_oid_zona = PsOidZonaRege;
EXCEPTION
  WHEN no_data_found THEN
    ln_oidCron:=0;
END;

BEGIN
select decode(ca.ind_labo,
              1,
               (abs(cr.fec_inic - crap.fec_inic) -
              (select count(*) 
                 from cra_feria cf
                where (  (cf.fec_feri between cr.fec_inic and crap.fec_inic) or
                         (cf.fec_feri between crap.fec_inic and cr.fec_inic)   
              )
                and ((cf.ind_no_labo = 1) or (cf.ind_fest = 1))
                and cf.cgzo_oid_cabe_grup_zona = ln_oidGrupoZona
                and cf.cact_oid_acti = psOidActividad))*(case 
                                                            when cr.fec_inic < crap.fec_inic then -1
                                                              else 1
                                                         end) ,
              cr.fec_inic -crap.fec_inic)
 into ln_desplazamiento
  from cra_crono cr,
       cra_activ ca,
       seg_pais  sp,
       cra_perio cp,
       cra_crono crap
 where sp.oid_pais = ca.pais_oid_pais
   and cp.oid_peri = cr.perd_oid_peri
   and ca.oid_acti = cr.cact_oid_acti
   and cr.zzon_oid_zona = PsOidZonaRefe
   and ca.oid_acti = psOidActividad
   and sp.cod_pais = psCodigoPais
   and cp.val_nomb_peri = psCodigoPeriodo
   and crap.perd_oid_peri = cr.perd_oid_peri
   and crap.zzon_oid_zona = cr.zzon_oid_zona
   and crap.cact_oid_acti =
       nvl(ca.cact_oid_acti,
           (select cca.oid_acti
              from cra_activ cca
             where upper(cca.nom_acti) like '%INICIO DE PERIODO%'
               and cca.pais_oid_pais = sp.oid_pais));
EXCEPTION
  WHEN no_data_found THEN
    ln_desplazamiento:=0;
END;               

 IF ln_oidCron <> 0 THEN 
  CRA_PKG_PROCE.CRA_PR_PROCE_ACTUA_CRONO_FASE2(psCodigoPais,ln_oidCron,ln_oidGrupoZona,ln_desplazamiento,psUsuario );
      END IF;

EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_COPIA_CRONO_ZONA_FASE2 ' || ls_sqlerrm);    
          
End CRA_PR_COPIA_CRONO_ZONA_FASE2;

FUNCTION  CRA_FN_GET_FECHA_CRONO_FASE2(psCodigoPais       seg_pais.cod_pais%type,
                                     psCodigoPeriodo       seg_perio_corpo.cod_peri%type,
                                     psCodigoActiv         cra_activ.cod_acti%type,
                                     psCodigoZona          zon_zona.cod_zona%type)  RETURN VARCHAR2 IS
                                     
   lv_fech  VARCHAR2(20);                                     
 
BEGIN                                          

 BEGIN
  SELECT NVL(TO_CHAR(cr.fec_inic,'dd/mm/yyyy'),'')
  INTO lv_fech
    FROM cra_crono       cr,
         cra_perio       cp,
         cra_activ       ca,
         seg_pais        sp,
         zon_zona        zz,
         seg_perio_corpo ps
   WHERE cr.perd_oid_peri = cp.oid_peri
     AND cp.peri_oid_peri = ps.oid_peri
     AND ps.cod_peri = psCodigoPeriodo
     AND cr.cact_oid_acti = ca.oid_acti
     AND ca.ind_acti = '1'
     AND ca.cod_acti = psCodigoActiv
     AND ca.pais_oid_pais = sp.oid_pais
     AND sp.cod_pais = psCodigoPais
     AND cr.zzon_oid_zona = zz.oid_zona
     and zz.cod_zona = psCodigoZona;
  EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lv_fech := '';
   END;
        
   RETURN lv_fech;
     
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_FN_GET_FECHA_CRONO_FASE2: '||ls_sqlerrm);
     return '';     
   
END CRA_FN_GET_FECHA_CRONO_FASE2;  

End CRA_PKG_PROCE;
/
