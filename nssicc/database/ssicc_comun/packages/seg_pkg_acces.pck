CREATE OR REPLACE PACKAGE "SEG_PKG_ACCES" is

 TYPE micursor IS REF CURSOR;
 type split_tbl IS table of varchar2(4000);
/* TYPE split_reg IS RECORD (
     acceso varchar2(4000)
   );*/

/***************************************************************************
Descripcion       : Funcion que devuelve la transpuesta de una columna
Fecha Creacion    : 09/07/2007
Autor             : Daniel Hinostroza vintes
***************************************************************************/
FUNCTION SEG_FN_DEVUE_CADEN_COLUM_TRANS(
     psConsulta     VARCHAR2 )
RETURN VARCHAR2 ;


/************************************************************************/
/* Descripcion    :Funcion q lista los paises por su repectivo diario  */
/* Creado         : Daniel Hinostroza vintes                                               */
/* Fecha      : 19/10/2005                                             */
/************************************************************************/
function SEG_FN_CADEN_COLUM_TRANS_CONCA(p_key_name in varchar2,
                            p_key_name2 in varchar2,
                            p_key_name3 in varchar2,
                     p_key_val  in varchar2,
                     p_keyval2  in varchar2,
                     p_keyval3  in varchar2,
                     p_other_col_name in varchar2,
                     p_tname     in varchar2 )
 return varchar2 ;




/***************************************************************************
Descripcion       : Eliminando opciones deshasignados
Fecha Creacion    : 23/10/2007
Fecha Modificado  : 24/10/2007
Autor             : Daniel HInostroza Vintes
***************************************************************************/

PROCEDURE INT_PR_ELI_DESH_OPCI_UPD
  (psCodMenu                  VARCHAR2,
   psCodPais                  VARCHAR2,
   psCodOpc                   VARCHAR2);

/***************************************************************************
Descripcion       : Asignando botones - opciones
Fecha Creacion    : 23/10/2007
Fecha Modificado  : 24/10/2007
Autor             : Daniel HInostroza Vintes
***************************************************************************/

PROCEDURE INT_PR_ASI_OPCI_UPD
  (psCodMenu                  VARCHAR2,
   psCodOpc                   VARCHAR2);


/************************************************************************
 Descripcion : Funcion que obtiene los accesos por pantalla
 Creado     : Carlos Bazalar
 Fecha      : 19/10/2010
   Parametros:
     psUseUsuario  Login del usuario
     psCampoMenu   Campo de Tabla del Menu
     psCampoPais   Campo de Tabla del Pais
     psCampoRol    Campo de Tabla del Rol
     psValorMenu   Valor del Menu
     psValorPais   Valor del Pais
     psCampoRolOpcion     Campo Rol Opcion
     psCampoOpcionMenuRol Campo Opcion Menu Rol
************************************************************************/
FUNCTION SEG_FN_OBTIE_ACCES_OPCIO(
  psUseUsuario VARCHAR2,
  psCampoMenu  VARCHAR2,
  psCampoPais  VARCHAR2,
  psCampoRol   VARCHAR2,
  psValorMenu  VARCHAR2,
  psValorPais  VARCHAR2,
  psCampoRolOpcion VARCHAR2,
  psCampoOpcionMenuRol VARCHAR2
)
RETURN VARCHAR2;


/************************************************************************
 Descripcion : Funcion que convierte Cadena a Lista
 Creado     : Carlos Bazalar
 Fecha      : 19/10/2010
   Parametros:
     p_list  Cadena que sera convertida a Lista
     p_del   Delimitador a usar, por defecto es ,
************************************************************************/
FUNCTION SEG_FN_SPLIT
(
    p_list varchar2,
    p_del varchar2 := ','
)
RETURN split_tbl;

end SEG_PKG_ACCES;
/

CREATE OR REPLACE PACKAGE BODY "SEG_PKG_ACCES" is

/***************************************************************************
Descripcion       : Funcion que devuelve la transpuesta de una columna
Fecha Creacion    : 09/07/2007
Autor             : Daniel Hinostroza vintes
Parametros        :
            psSentencia : enviando select field from table
***************************************************************************/
FUNCTION SEG_FN_DEVUE_CADEN_COLUM_TRANS(
     psConsulta     VARCHAR2 )
RETURN VARCHAR2
IS

ret  VARCHAR2(4000);
hold VARCHAR2(4000);
cur  micursor ;

BEGIN
OPEN cur FOR psConsulta;
LOOP
FETCH cur INTO hold;
EXIT WHEN cur%NOTFOUND;
IF ret IS NULL THEN
ret := hold;
ELSE
ret := ret || ',' || hold;
END IF;
END LOOP;
RETURN ret;

END;

/************************************************************************/
/* Descripcion    :Funcion q lista los paises por su repectivo diario  */
/* Creado         : Daniel Hinostroza vintes                                               */
/* Fecha      : 19/10/2005
            ps : enviando select field from table
            psP_key_name       : campo1
            psP_key_name2      : campo2
            psP_key_name3      : campo3
            psp_key_val        : valor1
            psp_keyval2        : valor2
            psP_keyval3        : valor3
            psP_other_col_name : columna a mostrar
            psP_tname          : nombre de la tabla

*/
/************************************************************************/

 function SEG_FN_CADEN_COLUM_TRANS_CONCA(p_key_name in varchar2,
                            p_key_name2 in varchar2,
                            p_key_name3 in varchar2,
                     p_key_val  in varchar2,
                     p_keyval2  in varchar2,
                     p_keyval3  in varchar2,
                     p_other_col_name in varchar2,
                     p_tname     in varchar2 )
 return varchar2
 as
     type rc is ref cursor;
     l_str    varchar2(4000);
     l_sep    varchar2(1);
     l_val    varchar2(4000);

     l_cur    rc;
 begin

     open l_cur for 'select '||p_other_col_name||'
                       from '|| p_tname || ' t
                      where   ' || p_key_name || ' = :x ' ||
                             'and   ' || p_key_name2 || ' = :y ' ||
                             'and   ' || p_key_name3 || ' = :z '

                 using p_key_val, p_keyval2, p_keyval3 ;
     loop
         fetch l_cur into l_val;
         exit when l_cur%notfound;
         l_str := l_str || l_sep || l_val;
         l_sep := ',';
     end loop;
     close l_cur;

     return l_str;
 end;

/***************************************************************************
Descripcion       : Eliminando opciones deshasignados
Fecha Creacion    : 23/10/2007
Fecha Modificado  : 24/10/2007
Autor             : Daniel HInostroza Vintes
***************************************************************************/

PROCEDURE INT_PR_ELI_DESH_OPCI_UPD
  (psCodMenu                  VARCHAR2,
   psCodPais                  VARCHAR2,
   psCodOpc                   VARCHAR2)

IS
existe NUMBER;
BEGIN

	SELECT COUNT(*) INTO existe FROM  seg_opcio_menu WHERE menu_cod_menu=psCodMenu and opci_cod_opci=psCodOpc;

	IF existe = 1 THEN
		begin
        delete from seg_opcio_menu_rol
        where menu_cod_menu = psCodMenu
        and pais_cod_pais = psCodPais
        and opci_cod_opci = psCodOpc;

        DELETE FROM SEG_OPCIO_MENU
		    WHERE  MENU_COD_MENU = psCodMenu and opci_cod_opci=psCodOpc;
        end;
   END IF;

END INT_PR_ELI_DESH_OPCI_UPD;


/***************************************************************************
Descripcion       : Asignando botones -opciones
Fecha Creacion    : 23/10/2007
Fecha Modificado  : 24/10/2007
Autor             : Daniel HInostroza Vintes
***************************************************************************/

PROCEDURE INT_PR_ASI_OPCI_UPD
  (psCodMenu                  VARCHAR2,
   psCodOpc                   VARCHAR2)

IS
existe NUMBER;
BEGIN

	SELECT COUNT(*) INTO existe FROM  seg_opcio_menu WHERE menu_cod_menu=psCodMenu and opci_cod_opci=psCodOpc;

	IF existe = 0 THEN
		BEGIN
      insert into seg_opcio_menu
      (menu_cod_menu, opci_cod_opci)
      values
      (psCodMenu, psCodOpc);
        end;
   END IF;

END INT_PR_ASI_OPCI_UPD;

/************************************************************************
 Descripcion : Funcion que obtiene los accesos por pantalla
 Creado     : Carlos Bazalar
 Fecha      : 19/10/2010
   Parametros:
     psUseUsuario  Login del usuario
     psCampoMenu   Campo de Tabla del Menu
     psCampoPais   Campo de Tabla del Pais
     psCampoRol    Campo de Tabla del Rol
     psValorMenu   Valor del Menu
     psValorPais   Valor del Pais
     psCampoRolOpcion     Campo Rol Opcion
     psCampoOpcionMenuRol Campo Opcion Menu Rol
************************************************************************/
FUNCTION SEG_FN_OBTIE_ACCES_OPCIO(
  psUseUsuario VARCHAR2,
  psCampoMenu  VARCHAR2,
  psCampoPais  VARCHAR2,
  psCampoRol   VARCHAR2,
  psValorMenu  VARCHAR2,
  psValorPais  VARCHAR2,
  psCampoRolOpcion VARCHAR2,
  psCampoOpcionMenuRol VARCHAR2
)
RETURN VARCHAR2
IS
  lsActivos     VARCHAR2(4000);
  listaActivos  split_tbl;
  lsRetorno     VARCHAR2(4000);
  CARACTER_SEPARACION VARCHAR2(1) := ',';
  lnPosicion    NUMBER(5);
  CURSOR cRolUsuario(psUsuario VARCHAR2)
  IS
    SELECT UR.ROL_COD_ROL
    FROM SEG_USUAR U, SEG_USUAR_ROL UR
    WHERE U.USE_USUA = psUsuario
      AND U.COD_USUA = UR.USUA_COD_USUA
      AND U.PAIS_COD_PAIS = UR.PAIS_COD_PAIS
      AND UR.EST_UROL = '1' ;
BEGIN
  lsRetorno := '';
  FOR cRol IN cRolUsuario(psUseUsuario) LOOP
      lsActivos := SEG_PKG_ACCES.SEG_FN_CADEN_COLUM_TRANS_CONCA(
           psCampoMenu,
           psCampoPais,
           psCampoRol,
           psValorMenu,
           psValorPais,
           cRol.Rol_Cod_Rol,
           psCampoRolOpcion,
           psCampoOpcionMenuRol);
      listaActivos := SEG_PKG_ACCES.SEG_FN_SPLIT(lsActivos);

      FOR i in 1..listaActivos.COUNT
      LOOP
         IF listaActivos(i) IS NOT NULL THEN
            lnPosicion := Instr(lsRetorno,listaActivos(i));
            IF lnPosicion IS NULL OR lnPosicion <= 0 THEN
               lsRetorno := lsRetorno || CARACTER_SEPARACION ||listaActivos(i);
            END IF;
         END IF;
      END LOOP;
      IF substr(lsRetorno,1,1) = CARACTER_SEPARACION THEN
         lsRetorno := substr(lsRetorno,2);
      END IF;
  END LOOP;
  RETURN lsRetorno;
END SEG_FN_OBTIE_ACCES_OPCIO;

/************************************************************************
 Descripcion : Funcion que convierte Cadena a Lista
 Creado     : Carlos Bazalar
 Fecha      : 19/10/2010
   Parametros:
     p_list  Cadena que sera convertida a Lista
     p_del   Delimitador a usar, por defecto es ,
************************************************************************/
FUNCTION SEG_FN_SPLIT
(
    p_list varchar2,
    p_del varchar2 := ','
)
RETURN split_tbl
IS
    l_idx      pls_integer;
    indice     NUMBER(1);
    l_list     varchar2(4000) := p_list;
    l_value    varchar2(4000);
    listaSplit split_tbl;
BEGIN
    listaSplit := split_tbl();
    indice := 0;
    loop
        l_idx := instr(l_list,p_del);
        indice:= indice + 1;
        listaSplit.EXTEND(indice);
        if l_idx > 0 then
            l_value:= substr(l_list,1,l_idx-1);
            l_list := substr(l_list,l_idx+length(p_del));
            listaSplit(indice):= l_value;
        else
            l_value := l_list;
            listaSplit(indice):= l_value;
            exit;
        end if;
    end loop;
    RETURN listaSplit;
end SEG_FN_SPLIT;


end SEG_PKG_ACCES;
/

