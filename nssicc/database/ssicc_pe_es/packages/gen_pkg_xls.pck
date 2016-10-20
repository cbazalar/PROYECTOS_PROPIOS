create or replace package GEN_PKG_XLS is

/**
||@copyright   (c) public domain
||@Name         xml_spreadsheet
||@Description  create Excel files in XML format
||                      can be read by Open Office and MS Office XP/2002
||                      to import into older MS Office versions
||                      see http://support.microsoft.com/kb/923505
||                      For further information on the XML format see
||                      http://msdn2.microsoft.com/en-us/library/aa140066.aspx
||@history <pre>
|| +Table Änderungshistorie
|| +-----------+-------------------+-------+----------------------------------------
||  Date        Who                 Version What's new
|| +-----------+-------------------+-------+----------------------------------------
||  25.10.2007  Matzberger Marcus   0.1     New
||  31.10.2007  Matzberger Marcus   0.2     Comments for Column title
||  05.01.2008  Matzberger Marcus   0.6     close and open worksheet as separate procedures
||  26.01.2008  Matzberger Marcus   0.7     allow more than one queries in abfrage
||                                              allow custom cell formats and cell protection
||  09.02.2008  Matzberger Marcus   1.0     provide all features of this XML format
||                                              except named ranges
||  19.02.2008  Matzberger Marcus   1.01    Changed format for comments for automatic documentation
||  09.05.2008  Matzberger Marcus   1.1.3   changed color definition because OO ignored named colors
||  23.06.2008  Matzberger Marcus   1.1.4   Increase Size for caption comments,
||                                              change definition for c_nf2decimalGroupSep
||                                              allow writeData without format
||  25.07.2008  Matzberger Marcus   1.1.5   newWorksheet and newDataRow with new parameter
||                                              getRelativeCellReference
||  13.09.2008  Matzberger Marcus   1.1.6   build_cursor public
||                                              CHR(38) instead of ampersand to avoid SET DEFINE OFF on installation
||  20.09.2008  Matzberger Marcus   2.0     Write into CLOB
||  12.11.2008  Matzberger Marcus   2.1     Wordwrap as default for headers
||                                          createNewFile: new parameter for default font
||                                          closeWorksheet: print setup (margins and text)
||  30.03.2009  Matzberger Marcus   2.2     createNewFile: Character Encoding as parameter
||                                          repeat titles when printing as parameter in t_rec_caption
||  19.08.2010  Matzberger Marcus   2.3     New predefined format: percent with 2 decimals
||                                          Doubled length of header text
|| +-----------+-------------------+-------+----------------------------------------
</pre>
*/
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- number default (2 decimals)
    c_numberDefault         CONSTANT VARCHAR2(3) := 's22';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- number integer
    c_integer               CONSTANT VARCHAR2(3) := 's23';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- number 2 decimals
    c_number2decimal        CONSTANT VARCHAR2(3) := 's24';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- number percent
    c_prozent               CONSTANT VARCHAR2(3) := 's25';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- number percent
    c_percent2decimal        CONSTANT VARCHAR2(4) := 's25a';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- number currency
    c_currency              CONSTANT VARCHAR2(3) := 's26';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- number accounting
    c_accounting            CONSTANT VARCHAR2(3) := 's27';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- number unformatted
    c_unformatted           CONSTANT VARCHAR2(3) := 's28';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- number unformatted
    c_unformattedSum        CONSTANT VARCHAR2(3) := 's29';

    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- Date
    c_date                  CONSTANT VARCHAR2(3) := 's30';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- Date with time
    c_dateTime              CONSTANT VARCHAR2(3) := 's31';

    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- Text default
    c_textDefault           CONSTANT VARCHAR2(3) := 's40';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- Text Bold
    c_textBold              CONSTANT VARCHAR2(3) := 's41';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- Text underlined
    c_textUnderline         CONSTANT VARCHAR2(3) := 's42';
    -- Predefined format for p_format in writeData, newDataRow or t_tab_columns.format
    -- Text bold and underlined
    c_textBoldUnderline     CONSTANT VARCHAR2(3) := 's43';

    -- Predefined Color
    -- can be used for text and background color
    c_red                   CONSTANT VARCHAR2(10) := '#FF0000';
    -- Predefined Color
    c_yellow                CONSTANT VARCHAR2(10) := '#FFFF00';
    -- Predefined Color
    c_blue                  CONSTANT VARCHAR2(10) := '#0000FF';
    -- Predefined Color
    c_green                 CONSTANT VARCHAR2(10) := '#008000';
    -- Predefined Color
    c_brightGreen           CONSTANT VARCHAR2(10) := '#00FF00';
    -- Predefined Color
    c_brightBlue            CONSTANT VARCHAR2(10) := '#80FFFF';
    -- Predefined Color
    c_lilac                 CONSTANT VARCHAR2(10) := '#FF00FF';
    -- Predefined Color
    c_brown                 CONSTANT VARCHAR2(10) := '#800000';
    -- Predefined Color
    c_white                 CONSTANT VARCHAR2(10) := '#FFFFFF';
    -- Predefined Color
    c_silver                CONSTANT VARCHAR2(10) := '#C0C0C0';
    -- Predefined Color
    c_lavender              CONSTANT VARCHAR2(10) := '#CC99FF';
    -- Predefined Color
    c_brightYellow          CONSTANT VARCHAR2(10) := '#FFFF99';
    -- Predefined Color
    c_brightOrange          CONSTANT VARCHAR2(10) := '#FFCC99';
    -- Predefined Color
    c_brightPink            CONSTANT VARCHAR2(10) := '#FF99CC';

    -- predefined format for conditional formatting.
    -- Bold
    c_emphasisBold          CONSTANT VARCHAR2(20) := 'font-weight:700;';
    -- predefined format for conditional formatting.
    -- Italic
    c_emphasisItalic        CONSTANT VARCHAR2(20) := 'font-style:italic;';
    -- predefined format for conditional formatting.
    -- Bold an Italic
    c_emphasisBoldItalic    CONSTANT VARCHAR2(40) := 'font-style:italic;font-weight:700;';

    -- predefined format for conditional formatting.
    -- Underlined
    c_markupUnderlined      CONSTANT VARCHAR2(30) := 'text-underline-style:single;';
    -- predefined format for conditional formatting.
    -- Double underlined
    c_markupUnderlinedD     CONSTANT VARCHAR2(30) := 'text-underline-style:double;';

    -- Background Pattern Solid
    c_bgpSolid              CONSTANT VARCHAR2(30) := 'Solid';
    -- Background Pattern 75% Gray
    c_bgpGray75             CONSTANT VARCHAR2(30) := 'Gray75';
    -- Background Pattern 50% Gray
    c_bgpGray50             CONSTANT VARCHAR2(30) := 'Gray50';
    -- Background Patterns 25% Gray
    c_bgpGray25             CONSTANT VARCHAR2(30) := 'Gray25';
    -- Background Patterns 12.5% Gray
    c_bgpGray125            CONSTANT VARCHAR2(30) := 'Gray125';
    -- Background Patterns 6.5% Gray
    c_bgpGray625            CONSTANT VARCHAR2(30) := 'Gray0625';
    -- Background Patterns Thin Horizontal Stripes
    c_bgpThinHorzStripe     CONSTANT VARCHAR2(30) := 'ThinHorzStripe';
    -- Background Patterns Horizontal Stripes
    c_bgpHorzStripe         CONSTANT VARCHAR2(30) := 'HorzStripe';
    -- Background Patterns Thin Vertical Stripes
    c_bgpThinVertStripe     CONSTANT VARCHAR2(30) := 'ThinVertStripe';
    -- Background Patterns Vertical Stripes
    c_bgpVertStripe         CONSTANT VARCHAR2(30) := 'VertStripe';
    -- Background Patterns Thin Diagonal Stripes
    c_bgpThinDiagStripe     CONSTANT VARCHAR2(30) := 'ThinDiagStripe';
    -- Background Patterns Thin ReverseDiagonal Stripes
    c_bgpThinRevDiagStripe  CONSTANT VARCHAR2(30) := 'ThinReverseDiagStripe';
    -- Background Patterns Thin Diagonal Cross
    c_bgpThinDiagCross      CONSTANT VARCHAR2(30) := 'ThinDiagCross';
    -- Background Patterns Diagonal Cross
    c_bgpDiagCross          CONSTANT VARCHAR2(30) := 'DiagCross';
    -- Background Patterns ThickDiagonal Cross
    c_bgpThickDiagCross     CONSTANT VARCHAR2(30) := 'ThickDiagCross';
    -- Background Patterns Thin Horizontal Cross
    c_bgpThinHorzCross      CONSTANT VARCHAR2(30) := 'ThinHorzCross';

    -- Predefined number format for custom cell formats
    -- Unformatted
    c_nfUnformatted         CONSTANT VARCHAR2(30) := '';
    -- Predefined number format for custom cell formats
    -- Integer
    c_nfInteger             CONSTANT VARCHAR2(30) := '0';
    -- Predefined number format for custom cell formats
    -- 2 decimals places
    c_nf2decimal            CONSTANT VARCHAR2(30) := 'Fixed';
    -- Predefined number format for custom cell formats
    -- 3 decimals places
    c_nf3decimal            CONSTANT VARCHAR2(30) := '0.000';
    -- Predefined number format for custom cell formats
    -- 2 decimals places and group separator
    c_nf2decimalGroupSep    CONSTANT VARCHAR2(30) := '#,##0.00';
    -- Predefined number format for custom cell formats
    -- 3 decimals places and group separator
    c_nf3decimalGroupSep    CONSTANT VARCHAR2(30) := '#,##0.000';
    -- Predefined number format for custom cell formats
    -- Currency with red negative numbers and local currency symbol
    c_nfCurrency            CONSTANT VARCHAR2(30) := 'Currency';
    -- Predefined number format for custom cell formats
    -- Currency with black negative numbers and Euro currency symbol
    c_nfCurrencyEur         CONSTANT VARCHAR2(30 CHAR) := '#,##0.00\ '||CHR(38)||'quot;¿'||CHR(38)||'quot;';
    -- Predefined number format for custom cell formats
    -- Currency with red negative numbers and Euro currency symbol
    c_nfCurrencyEurR        CONSTANT VARCHAR2(55 CHAR) := '#,##0.00\ '||CHR(38)||'quot;¿'||CHR(38)||'quot;;[Red]#,##0.00\ '||CHR(38)||'quot;¿'||CHR(38)||'quot;';
    -- Predefined number format for custom cell formats
    -- accounting, aligned decimals, minus sign aligned left
    c_nfAccounting          CONSTANT VARCHAR2(70 CHAR) := '_-* #,##0.00\ _¿_-;\-* #,##0.00\ _¿_-;_-* '||CHR(38)||'quot;-'||CHR(38)||'quot;??\ _¿_-;_-@_-';
    -- Predefined number format for custom cell formats
    -- accounting with Euro sign, aligned decimals, minus sign aligned left
    c_nfAccountingEur       CONSTANT VARCHAR2(110 CHAR) := '_-* #,##0.00\ '||CHR(38)||'quot;¿'||CHR(38)||'quot;_-;\-* #,##0.00\ '||CHR(38)||'quot;¿'||CHR(38)||'quot;_-;_-* '||CHR(38)||'quot;-'||CHR(38)||'quot;??\ '||CHR(38)||'quot;¿'||CHR(38)||'quot;_-;_-@_-';
    -- Predefined number format for custom cell formats
    -- accounting with Euro ISO, aligned decimals, minus sign aligned left
    c_nfAccountingEurIso    CONSTANT VARCHAR2(90 CHAR) := '_-* #,##0.00\ [$EUR]_-;\-* #,##0.00\ [$EUR]_-;_-* '||CHR(38)||'quot;-'||CHR(38)||'quot;??\ [$EUR]_-;_-@_-';
    -- Predefined number format for custom cell formats
    -- accounting with USD sign at the left side of the number, aligned decimals, negative numbers red
    c_nfAccountingUsd       CONSTANT VARCHAR2(50 CHAR) := '[$$-409]#,##0.00_ ;[Red]\-[$$-409]#,##0.00\ ';
    -- Predefined number format for custom cell formats
    -- scientific 2 decimal places
    c_nfScientific2decimals CONSTANT VARCHAR2(30) := '0.00E+00';
    -- Predefined number format for custom cell formats
    -- scientific 3 decimal places
    c_nfScientific3decimals CONSTANT VARCHAR2(30) := '0.000E+00';
    -- Predefined number format for custom cell formats
    -- Percent no decimal places
    c_nfPercent             CONSTANT VARCHAR2(30) := '0%';
    -- Predefined number format for custom cell formats
    -- Percent 1 decimal place
    c_nfPercent1            CONSTANT VARCHAR2(30) := '0.0%';
    -- Predefined number format for custom cell formats
    -- Percent 2 decimal places
    c_nfPercent2            CONSTANT VARCHAR2(30) := 'Percent';
    -- Predefined number format for custom cell formats
    -- Percent 3 decimal places
    c_nfPercent3            CONSTANT VARCHAR2(30) := '0.000%';

    -- Predefined date format for custom cell formats
    -- dd.mm.yyyy
    c_dfDDMMYYYY            CONSTANT VARCHAR2(30) := 'Short Date';
    -- Predefined date format for custom cell formats
    -- dd.mm.yyyy hh:mi
    c_dfDDMMYYYYHHMI        CONSTANT VARCHAR2(30) := 'General Date';
    -- Predefined date format for custom cell formats
    -- dd.mon yy
    c_dfDDMONYY             CONSTANT VARCHAR2(30) := 'Medium Date';
    -- Predefined date format for custom cell formats
    -- dd.Month yyyy
    c_dfDDMONTHYYYY         CONSTANT VARCHAR2(30) := '[$-407]d/\ mmmm\ yyyy;@';
    -- Predefined date format for custom cell formats
    --Weekday, dd Month yyyy
    c_dfDayDDMONTHYYYY      CONSTANT VARCHAR2(40) := '[$-F800]dddd\,\ mmmm\ dd\,\ yyyy';
    -- Predefined date format for custom cell formats
    -- dd.mm.yyyy hh:mm:ss
    c_dfDDMMYYYYHHMISS      CONSTANT VARCHAR2(30) := 'dd/mm/yyyy\ hh:mm:ss';
    -- Predefined date format for custom cell formats
    -- hh:mi
    c_dfHHMI                CONSTANT VARCHAR2(30) := 'Short Time';
    -- Predefined date format for custom cell formats
    -- hh:mi:ss AM/PM
    c_dfHHMISS              CONSTANT VARCHAR2(30) := 'Long Time';
    -- Predefined date format for custom cell formats
    -- mi:ss,s
    c_dfMISSS               CONSTANT VARCHAR2(30) := 'mm:ss.0;@';
    -- Predefined date format for custom cell formats
    -- d.m.yy h:mi
    c_dfDMYYHMI             CONSTANT VARCHAR2(30) := 'd/m/yy\ h:mm;@';

    -- Predefined text to include page number in header or footer text
    c_mtPage                CONSTANT VARCHAR2(10) := '\[page]';
    -- Predefined text to include total page number in header or footer text
    c_mtPageTotal           CONSTANT VARCHAR2(10) := '\[pageT]';
    -- Predefined text to include time in header or footer text
    c_mtTime                CONSTANT VARCHAR2(10) := '\[time]';
    -- Predefined text to include date in header or footer text
    c_mtDate                CONSTANT VARCHAR2(10) := '\[date]';
    -- Predefined text to include file path in header or footer text
    c_mtPath                CONSTANT VARCHAR2(10) := '\[path]';
    -- Predefined text to include file name in header or footer text
    c_mtName                CONSTANT VARCHAR2(10) := '\[name]';
    -- Predefined text to include tab name in header or footer text
    c_mtTab                 CONSTANT VARCHAR2(10) := '\[tab]';
    -- Predefined text set font size 8 in header or footer text
    c_mtFontSize8           CONSTANT VARCHAR2(10) := '\[size8]';
    -- Predefined text set font size 10 in header or footer text
    c_mtFontSize10          CONSTANT VARCHAR2(10) := '\[size10]';
    -- Predefined text set font size 12 in header or footer text
    c_mtFontSize12          CONSTANT VARCHAR2(10) := '\[size12]';
    -- Predefined text set font size 14 in header or footer text
    c_mtFontSize14          CONSTANT VARCHAR2(10) := '\[size14]';
    -- Predefined text set font Arial bold in header or footer text
    c_mtFontBold            CONSTANT VARCHAR2(10) := '\[bold]';

/**
||@Name         t_rec_columns
||@Description  Record type for column definitions
||@Param        cWidth      PLS_INTEGER  column width. Between 0 and 600 or NULL.
||@Param        cFormat     VARCHAR2(50) ID of a user defined cell format. Set as default for cells in this column
||@Param        cAutowidth  BOOLEAN      set the width of a column automatically. If a width is defined then this value will be the minimal width of the column. TRUE = Yes, FALSE = No, NULL = Default. Works only for date and number values.
||@param        cHidden     BOOLEAN      Hide column.  TRUE = Yes, FALSE = No, NULL = Default
||@param        cIndex      PLS_INTEGER  Specifies the position of this column within the table. If this tag is not specified, the first instance has an assumed Index="1". Each additional Column element has an assumed Index that is one higher.
||                                       Indices must appear in strictly increasing order. Failure to do so will result in an XML Spreadsheet document that is invalid. Indices do not need to be sequential, however. Omitted indices are formatted with the default style's format.
*/
    TYPE t_rec_columns  IS RECORD(
         cWidth              PLS_INTEGER
        ,cFormat             VARCHAR2(50)
        ,cAutowidth          BOOLEAN
        ,cHidden             BOOLEAN
        ,cIndex              PLS_INTEGER
        );
    TYPE t_tab_columns IS TABLE OF t_rec_columns
        INDEX BY BINARY_INTEGER;

/**
||@Name         t_rec_caption
||@Description  Record type for caption definitions
||              -- When there is at least one top caption then every caption without a top caption needs an blank (' ') as topTitle
||              -- Indices of t_tab_caption need to be sequential from 1 to n
||@Param        title              VARCHAR2(100) -- column title
||@Param        topTitle           VARCHAR2(100) -- first row caption (topCaption) when 2 caption rows. Default ''
||@Param        span               PLS_INTEGER  -- topCaption spans x columns. Default 1
||@param        comment            VARCHAR2(1000) -- comment on title. Default ''
||@param        repeatTitle        BOOLEAN -- Repeat titles on every page when printed. TRUE = Yes. Needs to be set for first title only.
*/
    TYPE t_rec_caption  IS RECORD(
         title              VARCHAR2(100)
        ,topTitle           VARCHAR2(100)
        ,span               PLS_INTEGER
        ,comment            VARCHAR2(1000)
        ,repeatTitle        BOOLEAN
        );
    TYPE t_tab_caption IS TABLE OF t_rec_caption
        INDEX BY BINARY_INTEGER;

/**
||@Name         t_tab_abfrage
||@Description  TABLE OF VARCHAR2(2000)
||              List of strings for column headings and definition sum columns in Function / Procedure abfrage
*/
    TYPE t_tab_abfrage IS TABLE OF VARCHAR2(2000);

/**
||@Name         t_rec_border
||@Description  Record type for border definitions
||@Param        bPosition           VARCHAR2(60) -- Top, Bottom, Left, Right, DiagonalRight, DiagonalLeft. Can be combined in a comma separated list, i.e 'Top,Bottom'
||@Param        bLineStyle          VARCHAR2(50) -- Dot, DashDot, DashDotDot, Dash, SlantDashDot (weight 2), Double (weight 3). Default Continuous
||@Param        bWeight             PLS_INTEGER  -- Width of the Border 1,2 or 3. Default 0: 0¿Hairline 1¿Thin 2¿Medium 3¿Thick
||@param        bColor              VARCHAR2(50) -- Border color like the predefined (c_red...) or RGB value '#FF99CC'
*/
    TYPE t_rec_border IS RECORD(
         bPosition           VARCHAR2(60)
        ,bLineStyle          VARCHAR2(50)
        ,bWeight             PLS_INTEGER
        ,bColor              VARCHAR2(50)
        );
    TYPE t_tab_border IS TABLE OF t_rec_border
        INDEX BY BINARY_INTEGER;

/**
||@Name         t_rec_font
||@Description  Record type for font definitions
||@Param        fName              VARCHAR2(50) -- Font Name "Times New Roman","Courier New","Wingdings",... Default "Arial"
||@Param        fFamily            VARCHAR2(50) -- Font family - Automatic, Decorative, Modern, Roman, Script, Swiss
||@Param        fSize              PLS_INTEGER  -- Font size in pt. Default 10, Min 5
||@param        fColor             VARCHAR2(50) -- Font color like the predefined (c_red...) or RGB value '#FF99CC'. Default black
||@param        fBold              BOOLEAN      -- bold text TRUE = Yes, FALSE = No, NULL = Default (inherit from parent)
||@param        fItalic            BOOLEAN      -- italic text TRUE = Yes, FALSE = No, NULL = Default (inherit from parent)
||@param        fStrikeThrough     BOOLEAN      -- strike through text TRUE = Yes, FALSE = No, NULL = Default (inherit from parent)
||@param        fUnderline         VARCHAR2(50) -- underline text s = single, d = double, as = accounting single, ad = accounting double
||@param        fPosition          VARCHAR2(50) -- Extra Markup 'Subscript' or 'Superscript'
*/
    TYPE t_rec_font IS RECORD(
         fName              VARCHAR2(50)
        ,fFamily            VARCHAR2(50)
        ,fSize              PLS_INTEGER
        ,fColor             VARCHAR2(50)
        ,fBold              BOOLEAN
        ,fItalic            BOOLEAN
        ,fStrikeThrough     BOOLEAN
        ,fUnderline         VARCHAR2(50)
        ,fPosition          VARCHAR2(50)
        );

/**
||@Name         t_rec_background
||@Description  Record type for background definitions
||@Param        bColor             VARCHAR2(50) -- background color like the predefined (c_red...) or RGB value '#FF99CC'
||@Param        bPattern           VARCHAR2(50) -- background pattern: see predefined Background Patterns. Default Solid
||@Param        bPatternColor      VARCHAR2(50) -- background pattern color like the predefined (c_red...) or RGB value '#FF99CC'
*/
    TYPE t_rec_background IS RECORD(
         bColor             VARCHAR2(50)
        ,bPattern           VARCHAR2(50)
        ,bPatternColor      VARCHAR2(50)
        );

/**
||@Name         t_rec_alignment
||@Description  Record type for text behavior definitions
||@Param        vertical           VARCHAR2(50) -- vertical alignment: Top, Bottom, Center. DEFAULT Top
||@Param        horizontal         VARCHAR2(50) -- horizontal alignment: Left, Center, Right, Justify, Fill
||@Param        textRotate         PLS_INTEGER  -- Degrees to rotate text (-90 to 90)
||@param        readingOrder       VARCHAR2(30) -- Specifies the default right-to-left text entry mode for a cell. 'RightToLeft', 'LeftToRight' and 'Context'
||@Param        shrinkToFit        BOOLEAN      -- True means that the text size should be shrunk so that all of the text fits within the cell.
||@Param        verticalText       BOOLEAN      -- Specifies whether the text is drawn "downwards", whereby each letter is drawn horizontally, one above the other. TRUE = Yes, FALSE = No, NULL = Default (inherit from parent)
||@param        wrapText           BOOLEAN      -- Specifies whether the text in this cell should wrap at the cell boundary. TRUE = Yes, FALSE = No, NULL = Default (inherit from parent)
*/
    TYPE t_rec_alignment IS RECORD(
         vertical           VARCHAR2(50)
        ,horizontal         VARCHAR2(50)
        ,textRotate         PLS_INTEGER
        ,readingOrder       VARCHAR2(30)
        ,shrinkToFit        BOOLEAN
        ,verticalText       BOOLEAN
        ,wrapText           BOOLEAN
        );

/**
||@Name         t_rec_customStyles
||@Description  Record type for custom style definitions
||@Param        id                 VARCHAR2(50) -- ID if the custom format. To be used for p_format when calling writeData. Has to be unique within the spreadsheet. s22 to s43 are predefined by the package
||@Param        type               VARCHAR2(50) -- Data type Text, Date, Number, Percent
||@Param        format             VARCHAR2(200) -- Number or date format. One of the predefined formats (c_nfX for numbers or c_dfX for dates) or self defined as in Excel
||@param        font               t_rec_font
||@Param        background         t_rec_background
||@Param        border             t_tab_border -- Borders, defined in a record
||@Param        alignment          t_rec_alignment
||@param        protection         BOOLEAN      -- Protect cell from changes by user text TRUE = Yes, FALSE = No, NULL = Default (inherit from parent)
*/
    TYPE t_rec_customStyles  IS RECORD(
         id                 VARCHAR2(50)
        ,type               VARCHAR2(50)
        ,format             VARCHAR2(200)
        ,font               t_rec_font
        ,background         t_rec_background
        ,border             t_tab_border
        ,alignment          t_rec_alignment
        ,protection         BOOLEAN
        );
    TYPE t_tab_customStyles IS TABLE OF t_rec_customStyles
        INDEX BY BINARY_INTEGER;

    -- Multiple contitions can be defined for the same range with a maximum of 3.
    -- They are applied in order of apperance
/**
||@Name         t_rec_conditionalFormats
||@Description  Record type for conditional format definitions
||              Multiple contitions can be defined for the same range with a maximum of 3.
||              They are applied in order of apperance
||@Param        range              VARCHAR2(50) -- In R3C2 notation e.g.R4C4, R4C4:R5C4 or C4 for entire column,
||@Param        qualifier          VARCHAR2(50) -- Qualifier for comparison (Less, Greater, Equal, NotEqual, LessOrEqual, GreaterOrEqual, Between, NotBetween
||@Param        value1             VARCHAR2(50) -- first value to be compared with
||@param        value2             VARCHAR2(50) -- second value to be compared with (for Between, NotBetween)
||@Param        formatColor        VARCHAR2(50) -- format for text color
||@Param        formatEmphasis     VARCHAR2(50) -- format for text bold/italic
||@Param        formatMarkup       VARCHAR2(50) -- format for text underlined singe/double
||@param        formatBgColor      VARCHAR2(50) -- format for background color
*/
    TYPE t_rec_conditionalFormats  IS RECORD(
         range              VARCHAR2(50)
        ,qualifier          VARCHAR2(50)
        ,value1             VARCHAR2(50)
        ,value2             VARCHAR2(50)
        ,formatColor        VARCHAR2(50)
        ,formatEmphasis     VARCHAR2(50)
        ,formatMarkup       VARCHAR2(50)
        ,formatBgColor      VARCHAR2(50)
        );
    TYPE t_tab_conditionalFormats IS TABLE OF t_rec_conditionalFormats
        INDEX BY BINARY_INTEGER;


/**
||@Name         t_rec_printSetup
||@Description  Record type for print setup
||              Margins expect values in centimeters
||@Param        landscape          BOOLEAN -- TRUE = landscape FALSE or NULL = portrait
||@Param        headerLeft         VARCHAR2(500) -- left aligned header text
||@param        headerCenter       VARCHAR2(500) -- centered header text
||@Param        headerRight        VARCHAR2(500) -- right aligned header text
||@Param        headerMargin       NUMBER        --
||@Param        footerLeft         VARCHAR2(500) --
||@param        footerCenter       VARCHAR2(500) --
||@Param        footerRight        VARCHAR2(500) --
||@Param        footerMargin       NUMBER        --
||@param        pageMarginTop      NUMBER        --
||@Param        pageMarginBottom   NUMBER        --
||@Param        pageMarginLeft     NUMBER        --
||@Param        pageMarginRight    NUMBER        --
*/
    TYPE t_rec_printSetup  IS RECORD(
         landscape          BOOLEAN
        ,headerLeft         VARCHAR2(500)
        ,headerCenter       VARCHAR2(500)
        ,headerRight        VARCHAR2(500)
        ,headerMargin       NUMBER
        ,footerLeft         VARCHAR2(500)
        ,footerCenter       VARCHAR2(500)
        ,footerRight        VARCHAR2(500)
        ,footerMargin       NUMBER
        ,pageMarginTop      NUMBER
        ,pageMarginBottom   NUMBER
        ,pageMarginLeft     NUMBER
        ,pageMarginRight    NUMBER
        );

    g_tab_columns               t_tab_columns;
    g_tab_caption               t_tab_caption;
    g_tab_conditionalFormats    t_tab_conditionalFormats;
    g_rec_customStyles          t_rec_customStyles;
    g_tab_customStyles          t_tab_customStyles;
    g_rec_printSetup            t_rec_printSetup;

/**
||  @Name         getExcelColFromColNumber
||  @Description  gets the Excel column identifier from the current column number
||                e.g. 1 -> A, 10 -> J
||  @param        p_number - current column number with 1 <= p_number <= 256
*/
FUNCTION getExcelColFromColNumber
    (
     p_number               IN PLS_INTEGER
    )
    RETURN VARCHAR2;

/**
||  @Name         createNewFile
||  @Description  create a new Excel file
||  @param        p_path - valid DIRECTORY_NAME from database
||  @param        p_filename - file name
||  @param        p_tab_customStyles - user defined cell formats
||  @param        p_globalCustomStyle - Set a global default cell format
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
*/
FUNCTION createNewFile
    (
     p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2
    ,p_tab_customStyles     IN t_tab_customStyles := g_tab_customStyles
    ,p_globalCustomStyle    IN t_rec_customStyles := g_rec_customStyles
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
    RETURN utl_file.FILE_TYPE;

/**
||  @Name         createNewFile
||  @Description  create a new Excel file
||  @param        p_file - CLOB
||  @param        p_tab_customStyles - user defined cell formats
||  @param        p_globalCustomStyle - Set a global default cell format
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
*/
PROCEDURE createNewFile
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_tab_customStyles     IN t_tab_customStyles := g_tab_customStyles
    ,p_globalCustomStyle    IN t_rec_customStyles := g_rec_customStyles
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    );

/**
||  @Name         newDataRow
||  @Description  close previous row and open a new one
||  @param        p_fileHandle - file handle from 'createNewFile'
||  @param        p_format - Formatting of the cells according to constants
||                  in package header or user defines formats.
||  @param        p_index - Row index: Specifies the position of this row within
||                  the table. If this tag is not specified, the first instance
||                  has an assumed Index="1". Each additional Row element has an
||                  assumed Index that is one higher. Indices must appear in
||                  strictly increasing order. Failure to do so will result in
||                  an XML Spreadsheet document that is invalid. Indices do not
||                  need to be sequential, however. Omitted indices are formatted
||                  with the default style's format.
||  @param        p_closeRow - Close previous data row before opening the new one.
||                  Set to FALSE for the first row when the worksheet is opend
||                  with p_beginNewRow = FALSE
*/
PROCEDURE newDataRow
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_format               IN VARCHAR2 := NULL
    ,p_height               IN PLS_INTEGER := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_closeRow             IN BOOLEAN := TRUE
    );

/**
||  @Name         newDataRow
||  @Description  close previous row and open a new one
||  @param        p_file - CLOB
||  @param        p_format - Formatting of the cells according to constants
||                  in package header or user defines formats.
||  @param        p_index - Row index: Specifies the position of this row within
||                  the table. If this tag is not specified, the first instance
||                  has an assumed Index="1". Each additional Row element has an
||                  assumed Index that is one higher. Indices must appear in
||                  strictly increasing order. Failure to do so will result in
||                  an XML Spreadsheet document that is invalid. Indices do not
||                  need to be sequential, however. Omitted indices are formatted
||                  with the default style's format.
||  @param        p_closeRow - Close previous data row before opening the new one.
||                  Set to FALSE for the first row when the worksheet is opend
||                  with p_beginNewRow = FALSE
*/
PROCEDURE newDataRow
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_format               IN VARCHAR2 := NULL
    ,p_height               IN PLS_INTEGER := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_closeRow             IN BOOLEAN := TRUE
    );

/**
||  @Name         writeData
||  @Description  write text
||  @param        p_fileHandle - file handle from 'createNewFile'
||  @param        p_text - string to be written
||  @param        p_format - Formatting of the text according to constants
||                  in package header.
||  @param        p_comment - comment.
||  @param        p_formula - The cells are referenced relative to the formula cell
||                  The syntax is e.g.
||                       =SUM(R[-2]C,R[-1]C)
||                       =IF(B6>0;"T";"U")
||                       =AVERAGE(R[-2]C:R[-1]C)
||                       =COUNT(R[-2]C:R[-1]C)
||  @param        p_colspan - Merge cells horizontally.
||  @param        p_href - URL to which to link this cell.
||  @param        p_index - column index within containing row.
||                  Indices must appear in strictly increasing order
||                  Indices must not overlap
||  @param        p_protected - indicates whether or not this cell is protected.
||                  When the worksheet is unprotected, cell-level protection has
||                  no effect. When a cell is protected, it will not allow the
||                  user to enter information into it.
*/
PROCEDURE writeData
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_text                 IN VARCHAR2
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    );

/**
||  @Name         writeData
||  @Description  write text
||  @param        p_file - CLOB
||  @param        p_text - string to be written
||  @param        p_format - Formatting of the text according to constants
||                  in package header.
||  @param        p_comment - comment.
||  @param        p_formula - The cells are referenced relative to the formula cell
||                  The syntax is e.g.
||                       =SUM(R[-2]C,R[-1]C)
||                       =IF(B6>0;"T";"U")
||                       =AVERAGE(R[-2]C:R[-1]C)
||                       =COUNT(R[-2]C:R[-1]C)
||  @param        p_colspan - Merge cells horizontally.
||  @param        p_href - URL to which to link this cell.
||  @param        p_index - column index within containing row.
||                  Indices must appear in strictly increasing order
||                  Indices must not overlap
||  @param        p_protected - indicates whether or not this cell is protected.
||                  When the worksheet is unprotected, cell-level protection has
||                  no effect. When a cell is protected, it will not allow the
||                  user to enter information into it.
*/
PROCEDURE writeData
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_text                 IN VARCHAR2
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    );

/**
||  @Name         writeData
||  @Description  write number
||  @param        p_fileHandle - file handle from 'createNewFile'
||  @param        p_number - number to be written
||  @param        p_format - Formatting of the number Values according to constants
||                  in package header.
||  @param        p_comment - comment.
||  @param        p_formula - The cells are referenced relative to the formula cell
||                  The syntax is e.g.
||                       =SUM(R[-2]C,R[-1]C)
||                       =IF(B6>0;"T";"U")
||                       =AVERAGE(R[-2]C:R[-1]C)
||                       =COUNT(R[-2]C:R[-1]C)
||  @param        p_colspan - Merge cells horizontally.
||  @param        p_href - URL to which to link this cell.
||  @param        p_index - column index within containing row.
||                  Indices must appear in strictly increasing order
||                  Indices must not overlap
||  @param        p_protected - indicates whether or not this cell is protected.
||                  When the worksheet is unprotected, cell-level protection has
||                  no effect. When a cell is protected, it will not allow the
||                  user to enter information into it.
*/
PROCEDURE writeData
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_number               IN NUMBER
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    );

/**
||  @Name         writeData
||  @Description  write number
||  @param        p_file - CLOB
||  @param        p_number - number to be written
||  @param        p_format - Formatting of the number Values according to constants
||                  in package header.
||  @param        p_comment - comment.
||  @param        p_formula - The cells are referenced relative to the formula cell
||                  The syntax is e.g.
||                       =SUM(R[-2]C,R[-1]C)
||                       =IF(B6>0;"T";"U")
||                       =AVERAGE(R[-2]C:R[-1]C)
||                       =COUNT(R[-2]C:R[-1]C)
||  @param        p_colspan - Merge cells horizontally.
||  @param        p_href - URL to which to link this cell.
||  @param        p_index - column index within containing row.
||                  Indices must appear in strictly increasing order
||                  Indices must not overlap
||  @param        p_protected - indicates whether or not this cell is protected.
||                  When the worksheet is unprotected, cell-level protection has
||                  no effect. When a cell is protected, it will not allow the
||                  user to enter information into it.
*/
PROCEDURE writeData
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_number               IN NUMBER
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    );

/**
||  @Name         writeData
||  @Description  write date
||  @param        p_fileHandle - file handle from 'createNewFile'
||  @param        p_date - date to be written
||  @param        p_format - Formatting of the date values according to constants
||                  in package header.
||  @param        p_comment - comment.
||  @param        p_formula - The cells are referenced relative to the formula cell
||                  The syntax is e.g.
||                       =SUM(R[-2]C,R[-1]C)
||                       =IF(B6>0;"T";"U")
||                       =AVERAGE(R[-2]C:R[-1]C)
||                       =COUNT(R[-2]C:R[-1]C)
||  @param        p_colspan - Merge cells horizontally.
||  @param        p_href - URL to which to link this cell.
||  @param        p_index - column index within containing row.
||                  Indices must appear in strictly increasing order
||                  Indices must not overlap
||  @param        p_protected - indicates whether or not this cell is protected.
||                  When the worksheet is unprotected, cell-level protection has
||                  no effect. When a cell is protected, it will not allow the
||                  user to enter information into it.
*/
PROCEDURE writeData
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_date                 IN DATE
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    );

/**
||  @Name         writeData
||  @Description  write date
||  @param        p_file - CLOB
||  @param        p_date - date to be written
||  @param        p_format - Formatting of the date values according to constants
||                  in package header.
||  @param        p_comment - comment.
||  @param        p_formula - The cells are referenced relative to the formula cell
||                  The syntax is e.g.
||                       =SUM(R[-2]C,R[-1]C)
||                       =IF(B6>0;"T";"U")
||                       =AVERAGE(R[-2]C:R[-1]C)
||                       =COUNT(R[-2]C:R[-1]C)
||  @param        p_colspan - Merge cells horizontally.
||  @param        p_href - URL to which to link this cell.
||  @param        p_index - column index within containing row.
||                  Indices must appear in strictly increasing order
||                  Indices must not overlap
||  @param        p_protected - indicates whether or not this cell is protected.
||                  When the worksheet is unprotected, cell-level protection has
||                  no effect. When a cell is protected, it will not allow the
||                  user to enter information into it.
*/
PROCEDURE writeData
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_date                 IN DATE
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    );

/**
||  @Name         closeWorksheet
||  @Description  close last data row and close worksheet
||  @param        p_fileHandle - file handle from 'createNewFile'
||  @param        p_lineFixed - is the n-th line to be fixed when scrolling
||                  within the last worksheet
||                  0 = not fixed
||  @param        p_colFixed - is the n-th column to be fixed when scrolling
||                  within the last worksheet
||                  0 = not fixed
||  @param        p_tab_caption - collection with caption information
||  @param        p_autofilter - Set autofilter TRUE = Yes
||  @param        p_tab_conditionalFormats - collection with formatting information
||  @param        p_rec_printSetup - format for printing
*/
PROCEDURE closeWorksheet
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_lineFixed            IN PLS_INTEGER := 1
    ,p_colFixed             IN PLS_INTEGER := 0
    ,p_tab_caption          IN t_tab_caption := g_tab_caption
    ,p_autofilter           IN BOOLEAN := TRUE
    ,p_tab_conditionalFormats IN t_tab_conditionalFormats := g_tab_conditionalFormats
    ,p_rec_printSetup       IN t_rec_printSetup := g_rec_printSetup
    );

/**
||  @Name         closeWorksheet
||  @Description  close last data row and close worksheet
||  @param        p_file - CLOB
||  @param        p_lineFixed - is the n-th line to be fixed when scrolling
||                  within the last worksheet
||                  0 = not fixed
||  @param        p_colFixed - is the n-th column to be fixed when scrolling
||                  within the last worksheet
||                  0 = not fixed
||  @param        p_tab_caption - collection with caption information
||  @param        p_autofilter - Set autofilter TRUE = Yes
||  @param        p_tab_conditionalFormats - collection with formatting information
||  @param        p_rec_printSetup - format for printing
*/
PROCEDURE closeWorksheet
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_lineFixed            IN PLS_INTEGER := 1
    ,p_colFixed             IN PLS_INTEGER := 0
    ,p_tab_caption          IN t_tab_caption := g_tab_caption
    ,p_autofilter           IN BOOLEAN := TRUE
    ,p_tab_conditionalFormats IN t_tab_conditionalFormats := g_tab_conditionalFormats
    ,p_rec_printSetup       IN t_rec_printSetup := g_rec_printSetup
    );

/**
||  @Name         newWorksheet
||  @Description  open a new worksheet and open first data row
||  @param        p_fileHandle - file handle from 'createNewFile'
||  @param        p_sheetname - name of the first worksheet.
||                  This name has to be unique within the file
||  @param        p_tab_caption - collection with caption information
||  @param        p_tab_columns - collection with column settings
||  @param        p_format - Formatting of the cells in new row according to constants
||                  in package header or user defines formats.
||  @param        p_height - Row height in pt
||  @param        p_protected - indicates whether or not cell protection is in effect.
||                  When the worksheet is unprotected, cell-level protection has no effect
||  @param        p_beginNewRow - Begin new data row after opening the worksheet
*/
PROCEDURE newWorksheet
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_sheetname            IN VARCHAR2
    ,p_tab_caption          IN t_tab_caption := g_tab_caption
    ,p_tab_columns          IN t_tab_columns := g_tab_columns
    ,p_format               IN VARCHAR2 := NULL
    ,p_height               IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    ,p_beginNewRow          IN BOOLEAN  := TRUE
    );

/**
||  @Name         newWorksheet
||  @Description  open a new worksheet and open first data row
||  @param        p_file - CLOB
||  @param        p_sheetname - name of the first worksheet.
||                  This name has to be unique within the file
||  @param        p_tab_caption - collection with caption information
||  @param        p_tab_columns - collection with column settings
||  @param        p_format - Formatting of the cells in new row according to constants
||                  in package header or user defines formats.
||  @param        p_height - Row height in pt
||  @param        p_protected - indicates whether or not cell protection is in effect.
||                  When the worksheet is unprotected, cell-level protection has no effect
||  @param        p_beginNewRow - Begin new data row after opening the worksheet
*/
PROCEDURE newWorksheet
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_sheetname            IN VARCHAR2
    ,p_tab_caption          IN t_tab_caption := g_tab_caption
    ,p_tab_columns          IN t_tab_columns := g_tab_columns
    ,p_format               IN VARCHAR2 := NULL
    ,p_height               IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    ,p_beginNewRow          IN BOOLEAN  := TRUE
    );

/**
||  @Name         closeFile
||  @Description  close last data row and close file
||  @param        p_fileHandle - file handle from 'createNewFile'
*/
PROCEDURE closeFile
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    );

/**
||  @Name         closeFile
||  @Description  close last data row and close file
||  @param        p_file - CLOB
*/
PROCEDURE closeFile
    (
     p_file                 IN OUT NOCOPY CLOB
    );

/**
||  @Name         build_cursor
||  @Description  build a cursor from a SELECT
||  @param        p_query - SELECT string
||  @param        p_parm_names - list of parameter names
||  @param        p_parm_values - list of parameter values
||  @return       cursor number as INTEGER
*/
FUNCTION build_cursor
    (
     p_query                IN VARCHAR2
    ,p_parm_names           IN t_tab_abfrage
    ,p_parm_values          IN t_tab_abfrage
    )
    RETURN INTEGER;

/**
||  @Name         abfrage
||  @Description  write the output of the cursor  into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||  @param        p_cursor - cursor
||  @param        p_path - directory path - DIRECTORY_NAME from DB
||  @param        p_filename - file name
||  @param        p_sumColumns - list of columns to be summed
||  @param        p_maxRows - maximum rows to be written on one worksheet Default 64000
||  @param        p_showNullAs - how to disply NULLS
||  @param        p_titles - list of column captions
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
*/
PROCEDURE abfrage
    (
     p_cursor               IN INTEGER
    ,p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2     DEFAULT NULL
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    );

/**
||  @Name         abfrage
||  @Description  write the output of the query into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||  @param        p_query - SELECT
||  @param        p_path - DIRECTORY_NAME from DB
||  @param        p_filename - file name
||  @param        p_parm_names - list of parameter names
||  @param        p_parm_values - list of parameter values
||  @param        p_sumColumns - list of columns to be summed
||  @param        p_maxRows - maximum rows to be written on one worksheet Default 64000
||  @param        p_showNullAs - how to disply NULLS
||  @param        p_titles - list of column captions
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
*/
PROCEDURE abfrage
    (
     p_query                IN VARCHAR2
    ,p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2     DEFAULT NULL
    ,p_parm_names           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_parm_values          IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    );

/**
||  @Name         abfrage
||  @Description  write the output of the cursor  into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||                  next query into a file opend with FUNCTION abfrage
||  @param        p_cursor - cursor
||  @param        p_fileHandle - file handle from FUNCTION 'abfrage'
||  @param        p_sheetname - name of the current worksheet.
||                  This name has to be unique within the file
||  @param        p_sumColumns - list of columns to be summed
||  @param        p_maxRows - maximum rows to be written on one worksheet Default 64000
||  @param        p_showNullAs - how to disply NULLS
||  @param        p_titles - list of column captions
||  @param        p_close - should the file be closed after the query
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
*/
FUNCTION abfrage
    (
     p_cursor               IN INTEGER
    ,p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2     DEFAULT NULL
    ,p_sheetname            IN VARCHAR2
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
    RETURN utl_file.FILE_TYPE;

/**
||  @Name         abfrage
||  @Description  write the output of the query into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||  @param        p_query - SELECT
||  @param        p_path - DIRECTORY_NAME from DB
||  @param        p_filename - file name
||  @param        p_sheetname - name of the first worksheet.
||                  This name has to be unique within the file
||  @param        p_parm_names - list of parameter names
||  @param        p_parm_values - list of parameter values
||  @param        p_sumColumns - list of columns to be summed
||  @param        p_maxRows - maximum rows to be written on one worksheet Default 64000
||  @param        p_showNullAs - how to disply NULLS
||  @param        p_titles - list of column captions
||  @param        p_close - should the file be closed after the query
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
*/
FUNCTION abfrage
    (
     p_query                IN VARCHAR2
    ,p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2     DEFAULT NULL
    ,p_sheetname            IN VARCHAR2
    ,p_parm_names           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_parm_values          IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
    RETURN utl_file.FILE_TYPE;

/**
||  @Name         abfrage
||  @Description  write the output of the cursor  into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||                  next query into a file opend with FUNCTION abfrage
||  @param        p_cursor - cursor
||  @param        p_fileHandle - file handle from FUNCTION 'abfrage'
||  @param        p_sheetname - name of the current worksheet.
||                  This name has to be unique within the file
||  @param        p_sumColumns - list of columns to be summed
||  @param        p_maxRows - maximum rows to be written on one worksheet Default 64000
||  @param        p_showNullAs - how to disply NULLS
||  @param        p_titles - list of column captions
||  @param        p_close - should the file be closed after the query
*/
PROCEDURE abfrage
    (
     p_cursor               IN INTEGER
    ,p_fileHandle           IN utl_file.FILE_TYPE
    ,p_sheetname            IN VARCHAR2
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    );

/**
||  @Name         abfrage
||  @Description  write the output of the query into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||                  next query into a file opend with FUNCTION abfrage
||  @param        p_query - SELECT
||  @param        p_fileHandle - file handle from FUNCTION 'abfrage'
||  @param        p_sheetname - name of the current worksheet.
||                  This name has to be unique within the file
||  @param        p_parm_names - list of parameter names
||  @param        p_parm_values - list of parameter values
||  @param        p_sumColumns - list of columns to be summed
||  @param        p_maxRows - maximum rows to be written on one worksheet Default 64000
||  @param        p_showNullAs - how to disply NULLS
||  @param        p_titles - list of column captions
||  @param        p_close - should the file be closed after the query
*/
PROCEDURE abfrage
    (
     p_query                IN VARCHAR2
    ,p_fileHandle           IN utl_file.FILE_TYPE
    ,p_sheetname            IN VARCHAR2
    ,p_parm_names           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_parm_values          IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    );

/**
||  @Name         abfrage
||  @Description  write the output of the cursor  into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||  @param        p_cursor - cursor
||  @param        p_file - CLOB
||  @param        p_sheetname - name of the current worksheet.
||                  This name has to be unique within the file
||  @param        p_sumColumns - list of columns to be summed
||  @param        p_maxRows - maximum rows to be written on one worksheet Default 64000
||  @param        p_showNullAs - how to disply NULLS
||  @param        p_titles - list of column captions
||  @param        p_close - should the file be closed after the query
||  @param        p_first - Is this the first query for this file.
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
*/
PROCEDURE abfrage
    (
     p_cursor               IN INTEGER
    ,p_file                 IN OUT NOCOPY CLOB
    ,p_sheetname            IN VARCHAR2
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    ,p_first                IN BOOLEAN      DEFAULT TRUE
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    );

/**
||  @Name         abfrage
||  @Description  write the output of the query into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||  @param        p_query - SELECT
||  @param        p_file - CLOB
||  @param        p_sheetname - name of the current worksheet.
||                  This name has to be unique within the file
||  @param        p_parm_names - list of parameter names
||  @param        p_parm_values - list of parameter values
||  @param        p_sumColumns - list of columns to be summed
||  @param        p_maxRows - maximum rows to be written on one worksheet Default 64000
||  @param        p_showNullAs - how to disply NULLS
||  @param        p_titles - list of column captions
||  @param        p_close - should the file be closed after the query
||  @param        p_first - Is this the first query for this file.
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
*/
PROCEDURE abfrage
    (
     p_query                IN VARCHAR2
    ,p_file                 IN OUT NOCOPY CLOB
    ,p_sheetname            IN VARCHAR2
    ,p_parm_names           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_parm_values          IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    ,p_first                IN BOOLEAN      DEFAULT TRUE
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    );

/**
||  @Name         getRelativeCellReference
||  @Description  The reference in formulas regarding other cells has to be
||                  given relative to the current cell. This function gives
||                  the reference in the correct format R[x]C[y]
||  @param        p_rowFrom - Row of current cell
||  @param        p_colFrom - Column of current cell
||  @param        p_rowTo   - Row of target cell
||  @param        p_colTo   - Column of target cell
||  @return       Reference to target cell, format R[x]C[y]
*/
FUNCTION getRelativeCellReference
    (
     p_rowFrom              IN PLS_INTEGER
    ,p_colFrom              IN PLS_INTEGER
    ,p_rowTo                IN PLS_INTEGER
    ,p_colTo                IN PLS_INTEGER
    )
    RETURN VARCHAR2;

 PROCEDURE gen_pr_gener_file_excel(
  p_val_sql                         IN   VARCHAR2,
  p_file_name                       IN   VARCHAR2,
  p_max_rows                        IN   NUMBER);

 PROCEDURE gen_pr_gener_file_excel_tclob(
  p_val_sql                         IN   VARCHAR2,
  p_file_name                       IN   VARCHAR2,
  p_max_rows                        IN   NUMBER,
  p_clob_exce                       OUT  CLOB);
  
 PROCEDURE gen_pr_gener_clob_excel(
  p_val_sql                         IN   VARCHAR2,
  p_max_rows                        IN   NUMBER,
  p_clob                            OUT  CLOB);

END GEN_PKG_XLS;
/
create or replace package body GEN_PKG_XLS is

/*----------------------------------------------------------------
||
||                 (c) public domain
-----------------------------------------------------------------*/
/*-----------------Module----------------------------------------
  Name             :    xml_spreadsheet.pcb
  Description      :    create Excel files in XML format
-----------------------------------------------------------------*/

/*-----------------History---------------------------------------------------------------
||
||  Date        Who                 Version What's new
||
||  25.10.2007  Matzberger Marcus   0.1     New
||  31.10.2007  Matzberger Marcus   0.2     Comments for Column title
||                                          Escape for german Umlaute and line break
||                                          write headers for new worksheet
||  06.11.2007  Matzberger Marcus   0.5     some minor bugs
||  05.01.2008  Matzberger Marcus   0.6     close and open worksheet as separate procedures
||  26.01.2008  Matzberger Marcus   0.7     allow more than one queries in abfrage
||                                              allow custom cell formats and cell protection
||  09.02.2008  Matzberger Marcus   1.0     provide all features of this XML format
||                                              except named ranges
||  19.02.2008  Matzberger Marcus   1.1     write no <data> element when date is NULL
||  14.03.2008  Matzberger Marcus   1.1.1   don't escape characters in writeAbfrage as it is done in writeData
||                                              open new file with 32767. Metalink Note:358781.1
||  03.04.2008  Matzberger Marcus   1.1.2   fixed bug that prevented scrolling when first lines were fixed
||  09.05.2008  Matzberger Marcus   1.1.3   fixed typo in allowed values for vertical alignment
||  23.06.2008  Matzberger Marcus   1.1.4   ss:Index in uppercase
||                                              when a query returns more than p_maxRows in abfrage then set columns on second/further sheet/s like on the first
||                                              wrap text is no longer default when no format is set,
||                                              but is set with any prefdefined text formats
||  25.07.2008  Matzberger Marcus   1.1.5   Write Formulas in date and text cells
||                                              newWorksheet and newDataRow with new parameter
||                                              getRelativeCellReference
||  13.09.2008  Matzberger Marcus   1.1.6   CHR(38) instead of ampersand to avoid SET DEFINE OFF on installation
||  20.09.2008  Matzberger Marcus   2.0     Write into CLOB
||  12.11.2008  Matzberger Marcus   2.1     Wordwrap as default for headers
||                                          createNewFile: new parameter for default font
||                                          closeWorksheet: print setup (margins and text)
||  30.03.2009  Matzberger Marcus   2.2     createNewFile: Character Encoding as parameter
||                                          repeat titles when printing as parameter in t_rec_caption
||  19.08.2010  Matzberger Marcus   2.3     Bugfix: Error when the full list of sumColumn was not supplying (Found and repaired by Ian Allen)
||                                          New predefined format: percent with 2 decimals
-----------------------------------------------------------------*/

    c_packageName           CONSTANT VARCHAR2(40) := 'xml_spreadsheet';
 gc_dire_name_repo                 CONSTANT VARCHAR2(15) := 'XLS_REPORTS';
 gc_path_name_repo                 CONSTANT VARCHAR2(30) := '/sicc/int/temp/';
 gc_val_name_sheet                 CONSTANT VARCHAR2(15) := 'datos';

    g_debug                 BOOLEAN := FALSE;

    g_desc_t                dbms_sql.desc_tab;

    g_clob                  CLOB;
    g_setClob               BOOLEAN := FALSE;

    e_invalidPath           EXCEPTION;

PROCEDURE pld
    (
     p_text                 IN VARCHAR2
    )
IS
BEGIN
    IF g_debug THEN
        dbms_output.put_line('% '||SUBSTR(p_text,1,253));
    END IF;

END pld;

/*-----------------------------------------------------------------------------
||  Name:         checkPath
||  Description:  check whether p_path exists in all_directories
||  Parameter:    p_path - directory path
-----------------------------------------------------------------------------*/
PROCEDURE checkPath
    (
     p_path                 IN VARCHAR2
    )
IS
    v_count                 PLS_INTEGER;
BEGIN
    pld('checkPath p_path '||p_path);

    SELECT  COUNT(*)
    INTO    v_count
    FROM    all_directories
    WHERE   directory_name = p_path;

    IF v_count = 0 THEN
        RAISE e_invalidPath;
    END IF;

END checkPath;

/*-----------------------------------------------------------------------------
||  Name:         fopen
||  Description:  Open new file
||  Parameter:    p_path - valid directory_path
||                p_filename - file name
-----------------------------------------------------------------------------*/
FUNCTION fopen
    (
     p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2
    )
    RETURN utl_file.FILE_TYPE
IS

    v_fileHandle            utl_file.FILE_TYPE;

    v_argsstr               VARCHAR2(200)   := SUBSTR(
                                                'p_path '||p_path||','||
                                                'p_filename '||p_filename
                                                ,1,500);

BEGIN
    pld('fopen p_filename '||p_filename);

    v_fileHandle := utl_file.fopen (p_path, p_filename, 'W', 32767);

    RETURN v_fileHandle;

EXCEPTION
    WHEN utl_file.INVALID_PATH THEN
        RAISE_APPLICATION_ERROR(
             -20001
            ,SUBSTR(
                 'Error in '||c_packageName||'.fopen: ' ||
                 v_argsstr ||' invalid_path',1,255)
                );
    WHEN utl_file.INVALID_MODE THEN
        RAISE_APPLICATION_ERROR(
             -20001
            ,SUBSTR(
                 'Error in '||c_packageName||'.fopen: ' ||
                 v_argsstr ||' invalid_mode',1,255)
                );
    WHEN utl_file.INVALID_FILEHANDLE THEN
        RAISE_APPLICATION_ERROR(
             -20001
            ,SUBSTR(
                 'Error in '||c_packageName||'.fopen: ' ||
                 v_argsstr ||' invalid_filehandle',1,255)
                );
    WHEN utl_file.INVALID_OPERATION THEN
        RAISE_APPLICATION_ERROR(
             -20001
            ,SUBSTR(
                 'Error in '||c_packageName||'.fopen: ' ||
                 v_argsstr ||' invalid_operation',1,255)
                );
    WHEN utl_file.READ_ERROR THEN
        RAISE_APPLICATION_ERROR(
             -20001
            ,SUBSTR(
                 'Error in '||c_packageName||'.fopen: ' ||
                 v_argsstr ||' read_error',1,255)
                );
    WHEN utl_file.WRITE_ERROR THEN
        RAISE_APPLICATION_ERROR(
             -20001
            ,SUBSTR(
                 'Error in '||c_packageName||'.fopen: ' ||
                 v_argsstr ||' write_error',1,255)
                );
    WHEN utl_file.INTERNAL_ERROR THEN
        RAISE_APPLICATION_ERROR(
             -20001
            ,SUBSTR(
                 'Error in '||c_packageName||'.fopen: ' ||
                 v_argsstr ||' internal_error',1,255)
                );
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(
             -20001
            ,SUBSTR(
                 'Error in '||c_packageName||'.fopen: ' ||
                 v_argsstr ||' ' ||
                 SQLERRM,1,255)
                );
END fopen;

/*-----------------------------------------------------------------------------
||  Name:         fclose
||  Description:  close a file
||  Parameter:    p_fileHandle - file handle from 'fopen'
-----------------------------------------------------------------------------*/
PROCEDURE fclose
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    )
IS

    v_fileHandle            utl_file.FILE_TYPE := p_fileHandle;

BEGIN
    pld('fclose');

    IF utl_file.is_open (v_fileHandle) THEN
        utl_file.fclose (v_fileHandle);
    END IF;

END fclose;


/*-----------------------------------------------------------------------------
||  Name:         put_line
||  Description:  write a line into an open file
||  Parameter:    p_fileHandle - file handle from 'fopen'
||                p_line - string to be written
-----------------------------------------------------------------------------*/
PROCEDURE put_line
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_line                 IN VARCHAR2
    )
IS
--für CLOBs vor dem Aufruf von put_line: g_setClob := TRUE
--dann g_clob an übergebenen CLOB anhängen
--dbms_lob.TRIM(g_clob,0);
--am Schluss g_setClob := FALSE
BEGIN
    IF g_setClob THEN
        g_clob := g_clob||p_line||CHR(10);
        --dbms_lob.writeappend(g_clob, LENGTH(p_line), p_line);
    ELSE
        utl_file.put_line (p_fileHandle, p_line);
    END IF;

END put_line;


/*-----------------------------------------------------------------------------
||  Name:         getExcelColFromColNumber
||  Description:  gets the Excel column identifier from the current column number
||                e.g. 1 -> A, 10 -> J
||  Parameter:    p_number - current column number with 1 <= p_number <= 256
-----------------------------------------------------------------------------*/
FUNCTION getExcelColFromColNumber
    (
     p_number               IN PLS_INTEGER
    )
    RETURN VARCHAR2
IS
    v_ascii                 VARCHAR2(2);

    e_outOfRange            EXCEPTION;

    v_argsstr               VARCHAR2(500)   := SUBSTR(
                                                'p_number '||TO_CHAR(p_number)
                                                ,1,500);
BEGIN
    pld('getExcelColFromColNumber p_number '||p_number);

    IF p_number NOT BETWEEN 1 AND 256 THEN
        RAISE e_outOfRange;
    END IF;

    v_ascii := CHR(MOD(p_number,26) + 64);
    IF p_number > 26 THEN
        v_ascii := CHR(FLOOR(p_number / 26) + 64)||v_ascii;
    END IF;

    pld('.  v_ascii '||v_ascii);
    RETURN v_ascii;

EXCEPTION
    WHEN e_outOfRange THEN
        RAISE_APPLICATION_ERROR(
             -20001
            ,'Error in '||c_packageName||'.getExcelColFromColNumber: ' ||
             v_argsstr ||' ' ||
             'Value out of range 1 - 256'
             );
END getExcelColFromColNumber;

/*-----------------------------------------------------------------------------
||  Name:         convertNumberIntoExcelFormat
||  Description:  convert a numeric value into character with '.' as
||                decimal separator and max. 12 decimal places
||  Parameter:    p_number - number to be converted
-----------------------------------------------------------------------------*/
FUNCTION convertNumberIntoExcelFormat
    (
     p_number                 IN NUMBER
    )
    RETURN VARCHAR2
IS
    v_argsstr               VARCHAR2(500)   := SUBSTR(
                                                'p_number '||TO_CHAR(p_number)
                                                ,1,500);
BEGIN
    pld('convertNumberIntoExcelFormat p_number '||p_number);

    IF p_number IS NULL THEN
        RETURN NULL;
    ELSE
        RETURN TO_CHAR(p_number,'FM99999999999999999D999999999999','NLS_NUMERIC_CHARACTERS = ''.,''');
    END IF;

END convertNumberIntoExcelFormat;

/*-----------------------------------------------------------------------------
||  Name:         convertDateIntoExcelFormat
||  Description:  convert a date value into character
||  Parameter:    p_date - date to be converted
-----------------------------------------------------------------------------*/
FUNCTION convertDateIntoExcelFormat
    (
     p_date                 IN DATE
    )
    RETURN VARCHAR2
IS
    v_argsstr               VARCHAR2(500)   := SUBSTR(
                                                'p_date '||TO_CHAR(p_date)
                                                ,1,500);
BEGIN
    pld('convertDateIntoExcelFormat p_date '||p_date);

    RETURN TO_CHAR(p_date,'yyyy-mm-dd')||'T'||TO_CHAR(p_date,'hh24:mi:ss')||'Z';

END convertDateIntoExcelFormat;

/*-----------------------------------------------------------------------------
||  Name:         escapeSpecialCharacters
||  Description:  Masking of 'dangerous' letters, because of output as XML
||  Parameter:    p_text - string to be searched
-----------------------------------------------------------------------------*/
FUNCTION escapeSpecialCharacters
    (
     p_text                 IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN
    pld('escapeSpecialCharacters');

    RETURN  REPLACE(
             REPLACE(
              REPLACE(
               REPLACE(
                REPLACE(
                 REPLACE(
                  REPLACE(
                   REPLACE(
                    REPLACE(
                     REPLACE(
                      REPLACE(
                       REPLACE( p_text            -- use CHR(38) for ampersand so I don't have to set define off
                        ,CHR(38), CHR(38)||'amp;')-- Has to be the first to be replaced
                       ,CHR(10),CHR(38)||'#10;')
                      ,'ü', CHR(38)||'uuml;')
                     ,'ö', CHR(38)||'ouml;')
                    ,'ä', CHR(38)||'auml;')
                   ,'Ü', CHR(38)||'Uuml;')
                  ,'Ö', CHR(38)||'Ouml;')
                 ,'Ä', CHR(38)||'Auml;')
                ,'ß', CHR(38)||'szlig;')
               ,'"', CHR(38)||'quot;')
              ,'<', CHR(38)||'lt;')
             ,'>', CHR(38)||'gt;');

END escapeSpecialCharacters;

/*-----------------------------------------------------------------------------
||  Name:         setStyleColor
||  Description:  formatting the string for text color
||  Parameter:    p_color - color
-----------------------------------------------------------------------------*/
FUNCTION setStyleColor
    (
     p_color                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN
    IF p_color IS NULL THEN
        RETURN NULL;
    ELSE
        RETURN 'color:'||p_color||';';
    END IF;

END setStyleColor;

/*-----------------------------------------------------------------------------
||  Name:         setBgColor
||  Description:  formatting the string for background color
||  Parameter:    p_color - color
-----------------------------------------------------------------------------*/
FUNCTION setBgColor
    (
     p_color                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN
    IF p_color IS NULL THEN
        RETURN NULL;
    ELSE
        RETURN 'background:'||p_color||';';
    END IF;

END setBgColor;

/*-----------------------------------------------------------------------------
||  Name:         setColspan
||  Description:  formatting the string for merging cells
||  Parameter:    p_colspan - Number of cells to be merged
-----------------------------------------------------------------------------*/
FUNCTION setColspan
    (
     p_colspan              IN PLS_INTEGER
    )
    RETURN VARCHAR2
IS
BEGIN
    IF p_colspan IS NULL THEN
        RETURN NULL;
    ELSE
        RETURN ' ss:MergeAcross="'||p_colspan||'" ' ;
    END IF;

END setColspan;

/*-----------------------------------------------------------------------------
||  Name:         writeConditionalFormatting
||  Description:  write information for formatting on given conditions
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_tab_conditionalFormats - collection with formatting information
-----------------------------------------------------------------------------*/
PROCEDURE writeConditionalFormatting
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_tab_conditionalFormats IN t_tab_conditionalFormats
   )
IS
    v_range                 VARCHAR2(50);
BEGIN
    pld('writeConditionalFormatting');

    IF p_tab_conditionalFormats.COUNT > 0 THEN
        NULL;

        put_line(p_fileHandle,'    <ConditionalFormatting xmlns="urn:schemas-microsoft-com:office:excel">');

        FOR j IN 1..p_tab_conditionalFormats.COUNT LOOP
            IF p_tab_conditionalFormats(j).range <> NVL(v_range,'X') THEN
                v_range := p_tab_conditionalFormats(j).range;
                put_line(p_fileHandle,'   <Range>'||v_range||'</Range>');
            END IF;
            put_line(p_fileHandle,'   <Condition>');
            put_line(p_fileHandle,'    <Qualifier>'||p_tab_conditionalFormats(j).qualifier||'</Qualifier>');
            put_line(p_fileHandle,'    <Value1>'||p_tab_conditionalFormats(j).value1||'</Value1>');

            IF p_tab_conditionalFormats(j).qualifier IN ('Between', 'NotBetween') THEN
                put_line(p_fileHandle,'    <Value2>'||p_tab_conditionalFormats(j).value2||'</Value2>');
            END IF;

            put_line(p_fileHandle
                    ,'    <Format Style='''||
                     setStyleColor(p_tab_conditionalFormats(j).formatColor)||
                     p_tab_conditionalFormats(j).formatEmphasis||
                     p_tab_conditionalFormats(j).formatMarkup||
                     setBgColor(p_tab_conditionalFormats(j).formatBgColor)||
                     '''/>');
            put_line(p_fileHandle,'   </Condition>');

        END LOOP;

        put_line(p_fileHandle,'    </ConditionalFormatting>');

    END IF;

END writeConditionalFormatting;

/*-----------------------------------------------------------------------------
||  Name:         writeAutofilter
||  Description:  write information for autofiltering columns
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_tab_caption - collection with caption information
||                p_autofilter - Set autofilter TRUE = Yes
-----------------------------------------------------------------------------*/
PROCEDURE writeAutofilter
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_tab_caption          IN t_tab_caption
    ,p_autofilter           IN BOOLEAN
    )
IS
    v_autoFilterLine        PLS_INTEGER := 1;
    v_lastColumn            PLS_INTEGER := 1;

BEGIN
    pld('writeAutofilter');

    IF    p_tab_caption.COUNT > 0
      AND p_autofilter
    THEN
        <<loop_testCaption>>
        FOR j IN 1..p_tab_caption.COUNT LOOP
            -- test whether super title (twoline caption) required
            IF p_tab_caption(j).topTitle IS NOT NULL THEN
                v_autoFilterLine := 2;
                EXIT loop_testCaption;
            END IF;
        END LOOP loop_testCaption;

        v_lastColumn    := p_tab_caption.COUNT;

        IF v_lastColumn = 0 THEN
            v_lastColumn := 1;
        END IF;
        put_line(p_fileHandle,'  <AutoFilter '||
                                'x:Range="R'||TO_CHAR(v_autoFilterLine)||'C1:R'||TO_CHAR(v_autoFilterLine)||'C'||TO_CHAR(v_lastColumn)||'" '||
                                'xmlns="urn:schemas-microsoft-com:office:excel">');
        put_line(p_fileHandle,'  </AutoFilter>');
    END IF;


END writeAutofilter;

/*-----------------------------------------------------------------------------
||  Name:         setURL
||  Description:  formatting the string for URL
||  Parameter:    p_url - url
-----------------------------------------------------------------------------*/
FUNCTION setURL
    (
     p_url                  IN VARCHAR2
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_url IS NULL THEN
        RETURN NULL;
    ELSE
        RETURN ' ss:HRef="'||p_url||'" ';
    END IF;

END setURL;

/*-----------------------------------------------------------------------------
||  Name:         setIndex
||  Description:  formatting the string for cell index
||  Parameter:    p_index - index
-----------------------------------------------------------------------------*/
FUNCTION setIndex
    (
     p_index                IN VARCHAR2
    )
    RETURN VARCHAR2
IS

BEGIN
    pld('setIndex');
    pld('. p_index '||p_index);

    IF p_index IS NULL THEN
        RETURN NULL;
    ELSE
        RETURN ' ss:Index="'||p_index||'" ';
    END IF;

END setIndex;

/*-----------------------------------------------------------------------------
||  Name:         setProtected
||  Description:  formatting the string for cell protection
||  Parameter:    p_value - protect cell True/False
-----------------------------------------------------------------------------*/
FUNCTION setProtected
    (
     p_value                IN BOOLEAN
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_value THEN
        RETURN ' ss:Protected="1" ';
    ELSIF NOT p_value THEN
        RETURN ' ss:Protected="0" ';
    ELSE
        RETURN NULL;
    END IF;

END setProtected;

/*-----------------------------------------------------------------------------
||  Name:         setHidden
||  Description:  formatting the string for hiding columns
||  Parameter:    p_value - hide column True/False
-----------------------------------------------------------------------------*/
FUNCTION setHidden
    (
     p_value                IN BOOLEAN
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_value THEN
        RETURN ' ss:Hidden="1" ';
    ELSIF NOT p_value THEN
        RETURN ' ss:Hidden="0" ';
    ELSE
        RETURN NULL;
    END IF;

END setHidden;

/*-----------------------------------------------------------------------------
||  Name:         setAutoFitWidth
||  Description:  formatting the string for automatic column width
||  Parameter:    p_value - set column width automatically True/False
-----------------------------------------------------------------------------*/
FUNCTION setAutoFitWidth
    (
     p_value                IN BOOLEAN
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_value THEN
        RETURN ' ss:AutoFitWidth="1" ';
    ELSIF NOT p_value THEN
        RETURN ' ss:AutoFitWidth="0" ';
    ELSE
        RETURN NULL;
    END IF;

END setAutoFitWidth;

/*-----------------------------------------------------------------------------
||  Name:         setHeight
||  Description:  formatting the string for row height
||  Parameter:    p_value - height
-----------------------------------------------------------------------------*/
FUNCTION setHeight
    (
     p_value                IN PLS_INTEGER
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_value IS NOT NULL THEN

        IF p_value NOT BETWEEN 0 AND 255 THEN
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for row height');
        END IF;

        RETURN ' ss:Height="'||TO_CHAR(p_value)||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setHeight;

/*-----------------------------------------------------------------------------
||  Name:         setWidth
||  Description:  formatting the string for column width
||  Parameter:    p_value - width
-----------------------------------------------------------------------------*/
FUNCTION setWidth
    (
     p_value                IN PLS_INTEGER
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_value IS NOT NULL THEN

        IF p_value NOT BETWEEN 0 AND 600 THEN
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for column width');
        END IF;

        RETURN ' ss:Width="'||TO_CHAR(p_value)||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setWidth;

/*-----------------------------------------------------------------------------
||  Name:         setFormat
||  Description:  formatting the string for Style ID
||  Parameter:    p_value - valid Style ID
-----------------------------------------------------------------------------*/
FUNCTION setFormat
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_value IS NOT NULL THEN
        RETURN ' ss:StyleID="'||p_value ||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setFormat;

/*-----------------------------------------------------------------------------
||  Name:         setRepeatTitle
||  Description:  formatting the string for printing titles on each page
||  Parameter:    p_value - TRUE = Yes
-----------------------------------------------------------------------------*/
FUNCTION setRepeatTitle
    (
     p_value                IN BOOLEAN
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_value THEN
        RETURN ' <NamedCell ss:Name="Print_Titles"/> ';
    ELSE
        RETURN NULL;
    END IF;

END setRepeatTitle;

/*-----------------------------------------------------------------------------
||  Name:         formatFormula
||  Description:  formatting the string for formula
||  Parameter:    p_formula - formula
-----------------------------------------------------------------------------*/
FUNCTION formatFormula
    (
     p_formula              IN VARCHAR2
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_formula IS NULL THEN
        RETURN NULL;
    ELSE
        RETURN ' ss:Formula="'||escapeSpecialCharacters(p_formula)||'" ';
    END IF;

END formatFormula;

/*-----------------------------------------------------------------------------
||  Name:         formatComment
||  Description:  formatting the string for cell comments
||  Parameter:    p_comment - comment
-----------------------------------------------------------------------------*/
FUNCTION formatComment
    (
     p_comment              IN VARCHAR2
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_comment IS NULL THEN
        RETURN NULL;
    ELSE
        RETURN '<Comment ss:Author="'||SYS_CONTEXT ('USERENV', 'OS_USER')||'">'||
               '<ss:Data xmlns="http://www.w3.org/TR/REC-html40">'||
               '<B><Font html:Face="Tahoma" html:Size="8" html:Color="#000000">'||SYS_CONTEXT ('USERENV', 'OS_USER')||':</Font></B>'||
               '<Font html:Face="Tahoma" html:Size="8" html:Color="#000000"> '||escapeSpecialCharacters(p_comment)||'</Font>'||
               '</ss:Data>'||
               '</Comment>';
    END IF;

END formatComment;

/*-----------------------------------------------------------------------------
||  Name:         writeColumns
||  Description:  Write column settings
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_tab_columns - collection with column settings
-----------------------------------------------------------------------------*/
PROCEDURE writeColumns
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_tab_columns          IN t_tab_columns
    )
IS

BEGIN
    pld('writeColumns');
    pld('.  p_tab_columns.COUNT '||p_tab_columns.COUNT);

    FOR j IN 1..p_tab_columns.COUNT LOOP

        put_line(
             p_fileHandle
            ,'   <Column '||
             setFormat(p_tab_columns(j).cFormat)||
             setAutoFitWidth(p_tab_columns(j).cAutowidth)||
             setHidden(p_tab_columns(j).cHidden)||
             setWidth(p_tab_columns(j).cWidth)||
             setIndex(p_tab_columns(j).cIndex)||
             ' />');

    END LOOP;

END writeColumns;

/*-----------------------------------------------------------------------------
||  Name:         writeCaption
||  Description:  Write caption and open first data row
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_tab_caption - collection with caption information
-----------------------------------------------------------------------------*/
PROCEDURE writeCaption
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_tab_caption          IN t_tab_caption
    )
IS
    v_topTitleExists        BOOLEAN := FALSE;
    v_colspan               VARCHAR2(20) := '';
    v_style                 VARCHAR2(3) := 'sh1';
    v_repeatTitle           BOOLEAN;
BEGIN
    pld('writeCaption');
    pld('. p_tab_caption.COUNT '||p_tab_caption.COUNT);

    FOR j IN 1..p_tab_caption.COUNT LOOP

        -- test whether super title (twoline caption) required
        IF p_tab_caption(j).topTitle IS NOT NULL THEN
            v_topTitleExists := TRUE;
            EXIT;
        END IF;

    END LOOP;

    put_line(p_fileHandle,'   <Row>');

    IF v_topTitleExists THEN
        -- when super title exist, then write them
        FOR j IN 1..p_tab_caption.COUNT LOOP
            IF p_tab_caption(j).topTitle IS NOT NULL THEN
                v_colspan   := TO_CHAR(NVL(p_tab_caption(j).span,1) - 1);
               put_line(
                     p_fileHandle
                    ,'    <Cell ss:MergeAcross="'||v_colspan||'" ss:StyleID="'||v_style||'">'||
                     '<Data ss:Type="String">'||p_tab_caption(j).topTitle||'</Data>'||
                     '</Cell>');
                IF j = 1 THEN
                    v_style := 'sh2';
                END IF;
            END IF;
        END LOOP;
        put_line(p_fileHandle,'   </Row>');
        put_line(p_fileHandle,'   <Row>');

    END IF;

    v_style := 'sh1';

    FOR j IN 1..p_tab_caption.COUNT LOOP

        IF j = 1 THEN
            v_repeatTitle := p_tab_caption(j).repeatTitle;
        END IF;

        put_line(p_fileHandle
                ,'    <Cell ss:StyleID="'||v_style||'">'||
                 '<Data ss:Type="String">'||escapeSpecialCharacters(p_tab_caption(j).title)||'</Data>'||
                 formatComment(p_tab_caption(j).comment)||setRepeatTitle(v_repeatTitle)||
                 '</Cell>');
        IF j = 1 THEN
            v_style := 'sh2';
        END IF;

    END LOOP;

    put_line(p_fileHandle,'   </Row>');

END writeCaption;

/*-----------------------------------------------------------------------------
||  Name:         writeNamedRange
||  Description:  Write Named Range for repeating titles
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_tab_caption - collection with caption information
-----------------------------------------------------------------------------*/
PROCEDURE writeNamedRange
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_tab_caption          IN t_tab_caption
    ,p_sheetname            IN VARCHAR2
    )
IS
    v_row                   PLS_INTEGER := 1;
BEGIN
    IF p_tab_caption(1).repeatTitle THEN
        FOR j IN 1..p_tab_caption.COUNT LOOP

            -- test whether super title (twoline caption) required
            IF p_tab_caption(j).topTitle IS NOT NULL THEN
                v_row := 2;
                EXIT;
            END IF;

        END LOOP;
        put_line(p_fileHandle,'  <Names>');
        put_line(p_fileHandle,'   <NamedRange ss:Name="Print_Titles" ss:RefersTo="='''||p_sheetname||'''!R'||TO_CHAR(v_row)||'"/>');
        put_line(p_fileHandle,'  </Names>');

    END IF;

END writeNamedRange;

FUNCTION setParent
    (
     p_type                 IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN
    IF UPPER(p_type) = 'PERCENT' THEN
        RETURN ' ss:Parent="''||c_prozent||''';
    ELSE
        RETURN NULL;
    END IF;
END setParent;

FUNCTION setHAlign
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN

        IF INITCAP(p_value) NOT IN ('Left', 'Center', 'Right', 'Justify', 'Fill') THEN
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for hAlign');
        END IF;

        RETURN ' ss:Horizontal="'||INITCAP(p_value)||'" ';
    ELSE
        RETURN NULL;
    END IF;
END setHAlign;

FUNCTION setVAlign
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN

        IF INITCAP(p_value) NOT IN ('Top', 'Bottom', 'Center') THEN
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for vAlign');
        END IF;

        RETURN ' ss:Vertical="'||INITCAP(p_value)||'" ';
    ELSE
        RETURN NULL;
    END IF;
END setVAlign;

FUNCTION setTextRotate
    (
     p_value                IN PLS_INTEGER
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN

        IF p_value NOT BETWEEN -90 AND 90 THEN
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for textRotate');
        END IF;

        RETURN ' ss:Rotate="'||TO_CHAR(p_value)||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setTextRotate;

FUNCTION setReadingOrder
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN

        IF p_value NOT IN ('RightToLeft','LeftToRight','Context') THEN
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for Reading Order');
        END IF;

        RETURN ' ss:ReadingOrder="'||p_value||'" ';
    ELSE
        RETURN NULL;
    END IF;
END setReadingOrder;

FUNCTION setShrinkToFit
    (
     p_value                IN BOOLEAN
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_value THEN
        RETURN ' ss:ShrinkToFit="1" ';
    ELSE
        RETURN NULL;
    END IF;

END setShrinkToFit;

FUNCTION setVerticalText
    (
     p_value                IN BOOLEAN
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_value THEN
        RETURN ' ss:VerticalText="1" ';
    ELSIF NOT p_value THEN
        RETURN ' ss:VerticalText="0" ';
    ELSE
        RETURN NULL;
    END IF;

END setVerticalText;

FUNCTION setWrapText
    (
     p_value                IN BOOLEAN
    )
    RETURN VARCHAR2
IS

BEGIN

    IF p_value THEN
        RETURN ' ss:WrapText="1" ';
    ELSIF NOT p_value THEN
        RETURN ' ss:WrapText="0" ';
    ELSE
        RETURN NULL;
    END IF;

END setWrapText;

PROCEDURE writeAlign
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_rec_alignement       IN t_rec_alignment
    )
IS

BEGIN
    IF   p_rec_alignement.vertical IS NOT NULL
      OR p_rec_alignement.horizontal IS NOT NULL
      OR p_rec_alignement.textRotate IS NOT NULL
      OR p_rec_alignement.readingOrder IS NOT NULL
      OR p_rec_alignement.shrinkToFit IS NOT NULL
      OR p_rec_alignement.verticalText IS NOT NULL
      OR p_rec_alignement.wrapText IS NOT NULL
    THEN
        put_line(
             p_fileHandle
            ,'   <Alignment '||
            setHAlign(p_rec_alignement.horizontal)||
            setVAlign(p_rec_alignement.vertical)||
            setTextRotate(p_rec_alignement.textRotate)||
            setReadingOrder(p_rec_alignement.readingOrder)||
            setShrinkToFit(p_rec_alignement.shrinkToFit)||
            setVerticalText(p_rec_alignement.verticalText)||
            setWrapText(p_rec_alignement.wrapText)||
            ' />'
            );
    END IF;

END writeAlign;

FUNCTION setLineStyle
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN

        IF p_value NOT IN ('Continuous','Dot', 'DashDot', 'DashDotDot', 'Dash', 'SlantDashDot', 'Double') THEN
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for line style');
        END IF;

        RETURN ' ss:LineStyle="'||p_value||'" ';
    ELSE
        RETURN ' ss:LineStyle="Continuous" ';
    END IF;

END setLineStyle;

FUNCTION setWeight
    (
     p_value                IN PLS_INTEGER
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN

        IF p_value NOT BETWEEN 0 AND 3 THEN
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for border weight');
        END IF;

        RETURN ' ss:Weight="'||TO_CHAR(p_value)||'" ';
    ELSE
        RETURN ' ss:Weight="0" ';
    END IF;

END setWeight;

FUNCTION setColor
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN
        RETURN ' ss:Color="'||p_value||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setColor;

PROCEDURE writeBorder
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_position             IN VARCHAR2
    ,p_rec_border           IN t_rec_border
    )
IS
BEGIN
        put_line(
             p_fileHandle
            ,'    <Border '||
            ' ss:Position="'||p_position||'" '||
            setLineStyle(p_rec_border.bLineStyle)||
            setWeight(p_rec_border.bWeight)||
            setColor(NVL(p_rec_border.bColor,'#000000'))||
            '/>'
            );

END writeBorder;

PROCEDURE writeBorders
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_tab_border           IN t_tab_border
    )
IS
    v_position              VARCHAR2(100);
    v_countTop              PLS_INTEGER := 0;
    v_countBottom           PLS_INTEGER := 0;
    v_countLeft             PLS_INTEGER := 0;
    v_countRight            PLS_INTEGER := 0;
    v_countDiagonalRight    PLS_INTEGER := 0;
    v_countDiagonalLeft     PLS_INTEGER := 0;
BEGIN
    pld('writeBorders');
    pld('. p_tab_border.COUNT '||p_tab_border.COUNT);

    IF p_tab_border.COUNT > 0 THEN
        put_line(p_fileHandle,'   <Borders>');

        FOR j IN p_tab_border.FIRST .. p_tab_border.LAST LOOP

            v_position  := REPLACE(','||UPPER(p_tab_border(j).bPosition)||',',' ','');

            IF v_position LIKE '%,BOTTOM,%' THEN
                writeBorder(p_fileHandle,'Bottom',p_tab_border(j));
                v_countBottom   := v_countBottom + 1;
            END IF;

           IF v_position LIKE '%,LEFT,%' THEN
                writeBorder(p_fileHandle,'Left',p_tab_border(j));
                v_countLeft := v_countLeft + 1;
            END IF;

            IF v_position LIKE '%,RIGHT,%' THEN
                writeBorder(p_fileHandle,'Right',p_tab_border(j));
                v_countRight    := v_countRight + 1;
            END IF;

            IF v_position LIKE '%,TOP,%' THEN
                writeBorder(p_fileHandle,'Top',p_tab_border(j));
                v_countTop  := v_countTop + 1;
            END IF;

            IF v_position LIKE '%,DIAGONALRIGHT,%' THEN
                writeBorder(p_fileHandle,'DiagonalRight',p_tab_border(j));
                v_countDiagonalRight    := v_countDiagonalRight + 1;
            END IF;

            IF v_position LIKE '%,DIAGONALLEFT,%' THEN
                writeBorder(p_fileHandle,'DiagonalLeft',p_tab_border(j));
                v_countDiagonalLeft := v_countDiagonalLeft + 1;
            END IF;

        END LOOP;

        IF   v_countTop           > 1
          OR v_countBottom        > 1
          OR v_countLeft          > 1
          OR v_countRight         > 1
          OR v_countDiagonalRight > 1
          OR v_countDiagonalLeft  > 1
        THEN
            RAISE_APPLICATION_ERROR(-20001,'Multiple definitions of border position');
        END IF;

        put_line(p_fileHandle,'   </Borders>');

    END IF;

END writeBorders;

FUNCTION setName
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN
        RETURN ' ss:FontName="'||p_value||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setName;

FUNCTION setFamily
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN
    IF p_value NOT IN ('Automatic', 'Decorative', 'Modern', 'Roman', 'Script', 'Swiss') THEN
        RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for font family');
    END IF;

    IF p_value IS NOT NULL THEN
        RETURN ' x:Family="'||p_value||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setFamily;

FUNCTION setSize
    (
     p_value                IN PLS_INTEGER
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN

        IF p_value < 5 THEN
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for text size');
        END IF;

        RETURN ' ss:Size="'||TO_CHAR(p_value)||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setSize;

FUNCTION setEmphasis
    (
     p_bold                 IN BOOLEAN
    ,p_italic               IN BOOLEAN
    ,p_strike               IN BOOLEAN
    )
    RETURN VARCHAR2
IS
    v_emphasis              VARCHAR2(200);
BEGIN


    IF p_bold THEN
        v_emphasis  := ' ss:Bold="1" ';
    ELSIF NOT p_bold THEN
        v_emphasis  := ' ss:Bold="0" ';
    END IF;

    IF p_italic THEN
        v_emphasis  := v_emphasis||' ss:Italic="1" ';
    ELSIF NOT p_italic THEN
        v_emphasis  := ' ss:Italic="0" ';
    END IF;

    IF p_strike THEN
        v_emphasis  := v_emphasis||' ss:StrikeThrough="1" ';
    ELSIF NOT p_strike THEN
        v_emphasis  := ' ss:StrikeThrough="0" ';
    END IF;

    RETURN v_emphasis;

END setEmphasis;

FUNCTION setVerticalAlign
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN

        IF p_value NOT IN ('Superscript','Subscript') THEN
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for text size');
        END IF;

        RETURN ' ss:VerticalAlign="'||p_value||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setVerticalAlign;

FUNCTION setUnderline
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
    v_value                 VARCHAR2(30);
BEGIN

    IF p_value IS NOT NULL THEN

        IF LOWER(p_value) = 's' THEN
            v_value := 'Single';
        ELSIF LOWER(p_value) = 'd' THEN
            v_value := 'Double';
        ELSIF LOWER(p_value) = 'as' THEN
            v_value := 'SingleAccounting';
        ELSIF LOWER(p_value) = 'ad' THEN
            v_value := 'DoubleAccounting';
        ELSE
            RAISE_APPLICATION_ERROR(-20001,'Value '||p_value||' not allowed for text underline');
        END IF;

        RETURN ' ss:Underline="'||v_value||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setUnderline;

PROCEDURE writeFont
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_rec_font             IN t_rec_font
    )
IS

BEGIN

    IF   p_rec_font.fName IS NOT NULL
      OR p_rec_font.fFamily IS NOT NULL
      OR p_rec_font.fSize IS NOT NULL
      OR p_rec_font.fColor IS NOT NULL
      OR p_rec_font.fBold IS NOT NULL
      OR p_rec_font.fItalic IS NOT NULL
      OR p_rec_font.fStrikeThrough IS NOT NULL
      OR p_rec_font.fPosition IS NOT NULL
      OR p_rec_font.fUnderline IS NOT NULL
    THEN
        put_line(
             p_fileHandle
            ,'   <Font '||
            setName(p_rec_font.fName)||
            setFamily(p_rec_font.fFamily)||
            setSize(p_rec_font.fSize)||
            setColor(p_rec_font.fColor)||
            setEmphasis(p_rec_font.fBold,p_rec_font.fItalic,p_rec_font.fStrikeThrough)||
            setVerticalAlign(p_rec_font.fPosition)||
            setUnderline(p_rec_font.fUnderline)||
            ' />'
            );

    END IF;

END writeFont;

FUNCTION setBgPatternColor
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN
        RETURN ' ss:PatternColor="'||p_value||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setBgPatternColor;

FUNCTION setBgPattern
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN
        RETURN ' ss:Pattern="'||p_value||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setBgPattern;

PROCEDURE writeBackground
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_rec_background       IN t_rec_background
    )
IS

BEGIN

    IF   p_rec_background.bColor IS NOT NULL
      OR p_rec_background.bPattern IS NOT NULL
      OR p_rec_background.bPatternColor IS NOT NULL
    THEN
        put_line(
             p_fileHandle
            ,'   <Interior '||
            setColor(p_rec_background.bColor)||
            setBgPattern(p_rec_background.bPattern)||
            setBgPatternColor(p_rec_background.bPatternColor)||
            ' />'
            );
    END IF;
END writeBackground;

PROCEDURE writeNumberFormat
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_type                 IN VARCHAR2
    ,p_format               IN VARCHAR2
    )
IS

BEGIN

    IF UPPER(p_format) <> 'TEXT' THEN
        put_line(p_fileHandle,'   <NumberFormat ss:Format="'||p_format||'"/>');
    END IF;

END writeNumberFormat;

PROCEDURE writeProtection
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_value                IN BOOLEAN
    )
IS

BEGIN

    put_line(p_fileHandle,'   <Protection'||setProtected(p_value)||'/>');

END writeProtection;

FUNCTION setStyleName
    (
     p_value                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN

    IF p_value IS NOT NULL THEN
        RETURN ' ss:Name="'||p_value||'" ';
    ELSE
        RETURN NULL;
    END IF;

END setStyleName;

PROCEDURE writeStyles
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_rec_style            IN t_rec_customStyles
    ,p_name                 IN VARCHAR2 := NULL
    )
IS
BEGIN
    pld('writeStyles');

        put_line(p_fileHandle,'  <Style ss:ID="'||p_rec_style.id||'"'||setParent(p_rec_style.type)||setStyleName(p_name)||'>');
        writeAlign(p_fileHandle,p_rec_style.alignment);
        writeBorders(p_fileHandle,p_rec_style.border);
        writeFont(p_fileHandle,p_rec_style.font);
        writeBackground(p_fileHandle,p_rec_style.background);
        writeNumberFormat(p_fileHandle,p_rec_style.type,p_rec_style.format);
        writeProtection(p_fileHandle,p_rec_style.protection);
        put_line(p_fileHandle,'  </Style>');

END writeStyles;

/*-----------------------------------------------------------------------------
||  Name:         writeCustomStyles
||  Description:  write the user defined styles part of the file header
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_tab_customStyles - user defined cell formats
-----------------------------------------------------------------------------*/
PROCEDURE writeCustomStyles
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_tab_customStyles     IN t_tab_customStyles
    )
IS

BEGIN
    pld('writeCustomStyles');
    pld('. p_tab_customStyles.COUNT '||p_tab_customStyles.COUNT);

    FOR i IN p_tab_customStyles.FIRST .. p_tab_customStyles.LAST LOOP
        -- TODO prüfe ID auf Eindeutigkeit - interne PLSQL Tabelle
        writeStyles(p_fileHandle,p_tab_customStyles(i));
    END LOOP;

END writeCustomStyles;

/*-----------------------------------------------------------------------------
||  Name:         writePredefinedStyles
||  Description:  write the styles part of the file header
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_tab_customStyles - user defined cell formats
-----------------------------------------------------------------------------*/
PROCEDURE writePredefinedStyles
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_tab_customStyles     IN t_tab_customStyles
    ,p_globalCustomStyle    IN t_rec_customStyles
    )
IS
    r_style                 t_rec_customStyles;
BEGIN
    put_line(p_fileHandle,' <Styles>');
    -- Default style
    r_style                     := p_globalCustomStyle;
    r_style.id                  := 'Default';
    r_style.type                := 'Text';
    r_style.protection          := p_globalCustomStyle.protection;
    r_style.alignment.vertical  := 'Top';
    r_style.alignment.horizontal := p_globalCustomStyle.alignment.horizontal;
    r_style.alignment.textRotate := p_globalCustomStyle.alignment.textRotate;
    r_style.alignment.readingOrder  := p_globalCustomStyle.alignment.readingOrder;
    r_style.alignment.shrinkToFit   := p_globalCustomStyle.alignment.shrinkToFit;
    r_style.alignment.verticalText  := p_globalCustomStyle.alignment.verticalText;
    r_style.alignment.wrapText  := p_globalCustomStyle.alignment.wrapText;
    r_style.background          := p_globalCustomStyle.background;
    r_style.border              := p_globalCustomStyle.border;
    r_style.font                := p_globalCustomStyle.font;
    r_style.font.fFamily        := NVL(p_globalCustomStyle.font.fFamily,'Swiss');
    writeStyles(p_fileHandle,r_style,'Normal');

    -- Text
    r_style.id                  := c_textDefault;
    r_style.alignment.wrapText  := NVL(p_globalCustomStyle.alignment.wrapText,TRUE);
    writeStyles(p_fileHandle,r_style);

    -- Bold
    r_style.id                  := c_textBold;
    r_style.font.fBold          := TRUE;
    writeStyles(p_fileHandle,r_style);

    -- Underlined
    r_style.id                  := c_textUnderline;
    r_style.font.fBold          := FALSE;
    r_style.font.fUnderline     := 's';
    writeStyles(p_fileHandle,r_style);

    --Bold Underlined
    r_style.id                  := c_textBoldUnderline;
    r_style.font.fBold          := TRUE;
    writeStyles(p_fileHandle,r_style);

    -- Caption
    r_style.id                  := 'sh1';
    r_style.font.fUnderline     := NULL;
    r_style.border(1).bPosition := 'Top, Right, Bottom';
    r_style.border(1).bLineStyle := 'Continuous';
    r_style.border(1).bWeight   := 1;
    r_style.background.bColor   := c_silver;
    r_style.background.bPattern := c_bgpSolid;
    writeStyles(p_fileHandle,r_style);
    --
    r_style.id                  := 'sh2';
    r_style.border(1).bPosition := 'Top, Right, Bottom, Left';
    writeStyles(p_fileHandle,r_style);

    -- cell with number unformated
    put_line(p_fileHandle,'  <Style ss:ID="'||c_unformatted||'">');
    --put_line(p_fileHandle,'   <NumberFormat ss:Format="'||c_nfUnformatted||'"/>');
    put_line(p_fileHandle,'  </Style>');
    -- cell with number unformated and Border top
    put_line(p_fileHandle,'  <Style ss:ID="'||c_unformattedSum||'">');
    put_line(p_fileHandle,'   <Borders>');
    put_line(p_fileHandle,'    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>');
    put_line(p_fileHandle,'   </Borders>');
    put_line(p_fileHandle,'  </Style>');
    -- cell with number, 2 decimal places and group separator
    put_line(p_fileHandle,'  <Style ss:ID="'||c_numberDefault||'">');
    put_line(p_fileHandle,'   <NumberFormat ss:Format="'||c_nf2decimalGroupSep||'"/>');
    put_line(p_fileHandle,'  </Style>');
    -- cell with integer
    put_line(p_fileHandle,'  <Style ss:ID="'||c_integer||'">');
   put_line(p_fileHandle,'   <NumberFormat ss:Format="'||c_nfInteger||'"/>');
    put_line(p_fileHandle,'  </Style>');
   -- cell with number, 2 decimal places
    put_line(p_fileHandle,'  <Style ss:ID="'||c_number2decimal||'">');
    put_line(p_fileHandle,'   <NumberFormat ss:Format="'||c_nf2decimal||'"/>');
    put_line(p_fileHandle,'  </Style>');
    --cell with currency format with two decimal places and red text with parenthesis for negative values
    put_line(p_fileHandle,'  <Style ss:ID="'||c_currency||'">');
    put_line(p_fileHandle,'   <NumberFormat ss:Format="'||c_nfCurrency||'"/>');
    put_line(p_fileHandle,'  </Style>');
    -- same as c_currency using the Euro currency symbol instead
    put_line(p_fileHandle,'  <Style ss:ID="'||c_accounting||'">');
    put_line(p_fileHandle,'   <NumberFormat ss:Format="'||c_nfAccountingEur||'"/>');
    put_line(p_fileHandle,'  </Style>');
    -- cell with number as percent
    put_line(p_fileHandle,'  <Style ss:ID="'||c_prozent||'" ss:Name="Prozent">');
    put_line(p_fileHandle,'   <NumberFormat ss:Format="'||c_nfPercent||'"/>');
    put_line(p_fileHandle,'  </Style>');
    -- cell with number as percent and 2 decimal numbers
    put_line(p_fileHandle,'  <Style ss:ID="'||c_percent2decimal||'" ss:Parent="'||c_prozent||'">');
    put_line(p_fileHandle,'   <NumberFormat ss:Format="Percent"/>');
    put_line(p_fileHandle,'  </Style>');
    -- cell with date
    put_line(p_fileHandle,'  <Style ss:ID="'||c_date||'">');
    put_line(p_fileHandle,'   <NumberFormat ss:Format="'||c_dfDDMMYYYY||'"/>');
    put_line(p_fileHandle,'  </Style>');
    -- cell with datetime
    put_line(p_fileHandle,'  <Style ss:ID="'||c_dateTime||'">');
    put_line(p_fileHandle,'   <NumberFormat ss:Format="'||c_dfDDMMYYYYHHMI||'"/>');
    put_line(p_fileHandle,'  </Style>');

    IF p_tab_customStyles.COUNT > 0 THEN
        writeCustomStyles(p_fileHandle,p_tab_customStyles);
    END IF;
    put_line(p_fileHandle,' </Styles>');
END writePredefinedStyles;

PROCEDURE writeFileHeader
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_encoding             IN VARCHAR2
    )
IS

BEGIN
    -- Header
    put_line(p_fileHandle,'<?xml version="1.0" encoding="'||p_encoding||'"?>');
    put_line(p_fileHandle,'<?mso-application progid="Excel.Sheet"?>');
    put_line(p_fileHandle,'<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"');
    put_line(p_fileHandle,' xmlns:o="urn:schemas-microsoft-com:office:office"');
    put_line(p_fileHandle,' xmlns:x="urn:schemas-microsoft-com:office:excel"');
    put_line(p_fileHandle,' xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"');
    put_line(p_fileHandle,' xmlns:html="http://www.w3.org/TR/REC-html40">');
    put_line(p_fileHandle,' <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">');
    put_line(p_fileHandle,'  <Author>'||SYS_CONTEXT ('USERENV', 'OS_USER')||'</Author>');
    put_line(p_fileHandle,'  <LastAuthor>'||SYS_CONTEXT ('USERENV', 'OS_USER')||'</LastAuthor>');
    put_line(p_fileHandle,'  <Created>'||convertDateIntoExcelFormat(SYSDATE)||'</Created>');
--  put_line(p_fileHandle,'  <Company></Company>');
    put_line(p_fileHandle,' </DocumentProperties>');
END writeFileHeader;

/*-----------------------------------------------------------------------------
||  Name:         createNewFile
||  Description:  create a new Excel file
||  Parameter:    p_path - valid DIRECTORY_NAME from database
||                p_filename - file name
||                p_tab_customStyles - user defined cell formats
||                p_globalCustomStyle - Set a global default cell format
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
-----------------------------------------------------------------------------*/
FUNCTION createNewFile
    (
     p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2
    ,p_tab_customStyles     IN t_tab_customStyles := g_tab_customStyles
    ,p_globalCustomStyle    IN t_rec_customStyles := g_rec_customStyles
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
    RETURN utl_file.FILE_TYPE
IS

    v_fileHandle            utl_file.FILE_TYPE;
    v_argsstr               VARCHAR2(500)   := SUBSTR(
                                                'p_path '||p_path||','||
                                                'p_filename '||p_filename
                                                ,1,500);

BEGIN
    pld('createNewFile File');

    checkPath(p_path);

    -- open file
    v_fileHandle    := fopen(p_path, p_filename);
    writeFileHeader(v_fileHandle,p_encoding );
    writePredefinedStyles(v_fileHandle,p_tab_customStyles,p_globalCustomStyle);

    RETURN v_fileHandle;

EXCEPTION
    WHEN e_invalidPath THEN
        RAISE_APPLICATION_ERROR(
             -20001
            ,'Error in '||c_packageName||'.createNewFile: ' ||
             v_argsstr ||' ' ||
             'invalid path'
             );
END createNewFile;

PROCEDURE createNewFile
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_tab_customStyles     IN t_tab_customStyles := g_tab_customStyles
    ,p_globalCustomStyle    IN t_rec_customStyles := g_rec_customStyles
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
IS
BEGIN
    pld('createNewFile CLOB');

    g_setClob := TRUE;
    writeFileHeader(NULL,p_encoding );
    writePredefinedStyles(NULL,p_tab_customStyles,p_globalCustomStyle);
    p_file  := p_file||g_clob;
    --dbms_lob.writeappend(p_file, LENGTH(g_clob), g_clob);
    dbms_lob.TRIM(g_clob,0);
    g_setClob := TRUE;

END createNewFile;

PROCEDURE writeNewDataRow
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_format               IN VARCHAR2
    ,p_height               IN PLS_INTEGER
    ,p_index                IN PLS_INTEGER
    ,p_closeRow             IN BOOLEAN
    )
IS

BEGIN
    pld('writeNewDataRow');
    pld('. p_index '||p_index);
    IF p_closeRow THEN
        put_line(p_fileHandle,'   </Row>');
    END IF;

    put_line(p_fileHandle,'   <Row ss:AutoFitHeight="1"'||setIndex(p_index)||setFormat(p_format)||setHeight(p_height)||'>');

END writeNewDataRow;

/*-----------------------------------------------------------------------------
||  Name:         newDataRow
||  Description:  close previous row and open a new one
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_format - Formatting of the cells according to constants
||                  in package header or user defines formats.
||                p_height - Row height in pt
||                p_index - Row index: Specifies the position of this row within
||                  the table. If this tag is not specified, the first instance
||                  has an assumed Index="1". Each additional Row element has an
||                  assumed Index that is one higher. Indices must appear in
||                  strictly increasing order. Failure to do so will result in
||                  an XML Spreadsheet document that is invalid. Indices do not
||                  need to be sequential, however. Omitted indices are formatted
||                  with the default style's format.
||                p_closeRow - Close previous data row before opening the new one.
||                  Set to FALSE for the first row when the worksheet is opend
||                  with p_beginNewRow = FALSE
-----------------------------------------------------------------------------*/
PROCEDURE newDataRow
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_format               IN VARCHAR2 := NULL
    ,p_height               IN PLS_INTEGER := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_closeRow             IN BOOLEAN := TRUE
    )
IS

BEGIN
    writeNewDataRow(
         p_fileHandle
        ,p_format
        ,p_height
        ,p_index
        ,p_closeRow
        );
END newDataRow;

PROCEDURE newDataRow
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_format               IN VARCHAR2 := NULL
    ,p_height               IN PLS_INTEGER := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_closeRow             IN BOOLEAN := TRUE
    )
IS

BEGIN
    g_setClob := TRUE;
    writeNewDataRow(
         NULL
        ,p_format
        ,p_height
        ,p_index
        ,p_closeRow
        );
    p_file  := p_file||g_clob;
    --dbms_lob.writeappend(p_file, LENGTH(g_clob), g_clob);
    dbms_lob.TRIM(g_clob,0);
    g_setClob := TRUE;

END newDataRow;

/*-----------------------------------------------------------------------------
||  Name:         setStyle
||  Description:  Set cell style
||  Parameter:    p_format - format
-----------------------------------------------------------------------------*/
FUNCTION setStyle
    (
     p_format                IN VARCHAR2
    )
    RETURN VARCHAR2
IS
BEGIN
    IF p_format IS NULL THEN
        RETURN NULL;
    ELSE
        RETURN 'ss:StyleID="'||p_format||'" ';
    END IF;

END setStyle;

PROCEDURE writeVarcharData
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_text                 IN VARCHAR2
    ,p_format               IN VARCHAR2
    ,p_comment              IN VARCHAR2
    ,p_formula              IN VARCHAR2
    ,p_colspan              IN PLS_INTEGER
    ,p_href                 IN VARCHAR2
    ,p_index                IN PLS_INTEGER
    ,p_protected            IN BOOLEAN
    )
IS
BEGIN

    put_line(
             p_fileHandle
            ,'    <Cell '||setProtected(p_protected)||setIndex(p_index)||setURL(p_href)||setColspan(p_colspan)||setStyle(p_format)||formatFormula(p_formula)||'>'||
             '<Data ss:Type="String">'||escapeSpecialCharacters(p_text)||'</Data>'||
             formatComment(p_comment)||
             '</Cell>'
            );

END writeVarcharData;

/*-----------------------------------------------------------------------------
||  Name:         writeData
||  Description:  write text
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_text - string to be written
||                p_format - Formatting of the text according to constants
||                  in package header.
||                p_comment - comment.
||                p_formula - The cells are referenced relative to the formula cell
||                  The syntax is e.g.
||                       =SUM(R[-2]C,R[-1]C)
||                       =IF(B6>0;"T";"U")
||                       =AVERAGE(R[-2]C:R[-1]C)
||                       =COUNT(R[-2]C:R[-1]C)
||                p_colspan - Merge cells horizontally.
||                p_href - URL to which to link this cell.
||                p_index - column index within containing row.
||                  Indices must appear in strictly increasing order
||                  Indices must not overlap
||                p_protected - indicates whether or not this cell is protected.
||                  When the worksheet is unprotected, cell-level protection has
||                  no effect. When a cell is protected, it will not allow the
||                  user to enter information into it.
-----------------------------------------------------------------------------*/
PROCEDURE writeData
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_text                 IN VARCHAR2
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    )
IS

BEGIN

    writeVarcharData(
         p_fileHandle
        ,p_text
        ,p_format
        ,p_comment
        ,p_formula
        ,p_colspan
        ,p_href
        ,p_index
        ,p_protected
        );
END writeData;

PROCEDURE writeData
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_text                 IN VARCHAR2
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    )
IS

BEGIN

    g_setClob := TRUE;
    writeVarcharData(
         NULL
        ,p_text
        ,p_format
        ,p_comment
        ,p_formula
        ,p_colspan
        ,p_href
        ,p_index
        ,p_protected
        );
    p_file  := p_file||g_clob;
    --dbms_lob.writeappend(p_file, LENGTH(g_clob), g_clob);
    dbms_lob.TRIM(g_clob,0);
    g_setClob := TRUE;

END writeData;

PROCEDURE writeNumberData
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_number               IN NUMBER
    ,p_format               IN VARCHAR2
    ,p_comment              IN VARCHAR2
    ,p_formula              IN VARCHAR2
    ,p_colspan              IN PLS_INTEGER
    ,p_href                 IN VARCHAR2
    ,p_index                IN PLS_INTEGER
    ,p_protected            IN BOOLEAN
    )
IS

BEGIN

    put_line(
             p_fileHandle
            ,'    <Cell '||setProtected(p_protected)||setIndex(p_index)||setURL(p_href)||setColspan(p_colspan)||setStyle(p_format)||formatFormula(p_formula)||'>'||
             '<Data ss:Type="Number">'||convertNumberIntoExcelFormat(p_number)||'</Data>'||
             formatComment(p_comment)||
             '</Cell>'
            );

END writeNumberData;

/*-----------------------------------------------------------------------------
||  Name:         writeData
||  Description:  write number
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_number - number to be written
||                p_format - Formatting of the number Values according to constants
||                  in package header.
||                p_comment - comment.
||                p_formula - The cells are referenced relative to the formula cell
||                  The syntax is e.g.
||                       =SUM(R[-2]C,R[-1]C)
||                       =IF(B6>0;"T";"U")
||                       =AVERAGE(R[-2]C:R[-1]C)
||                       =COUNT(R[-2]C:R[-1]C)
||                p_colspan - Merge cells horizontally.
||                p_href - URL to which to link this cell.
||                p_index - column index within containing row.
||                  Indices must appear in strictly increasing order
||                  Indices must not overlap
||                p_protected - indicates whether or not this cell is protected.
||                  When the worksheet is unprotected, cell-level protection has
||                  no effect. When a cell is protected, it will not allow the
||                  user to enter information into it.
-----------------------------------------------------------------------------*/
PROCEDURE writeData
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_number               IN NUMBER
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    )
IS

BEGIN

    writeNumberData(
         p_fileHandle
        ,p_number
        ,p_format
        ,p_comment
        ,p_formula
        ,p_colspan
        ,p_href
        ,p_index
        ,p_protected
        );
END writeData;

PROCEDURE writeData
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_number               IN NUMBER
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    )
IS

BEGIN

    g_setClob := TRUE;
    writeNumberData(
         NULL
        ,p_number
        ,p_format
        ,p_comment
        ,p_formula
        ,p_colspan
        ,p_href
        ,p_index
        ,p_protected
        );
    p_file  := p_file||g_clob;
    --dbms_lob.writeappend(p_file, LENGTH(g_clob), g_clob);
    dbms_lob.TRIM(g_clob,0);
    g_setClob := TRUE;

END writeData;

PROCEDURE writeDateData
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_date                 IN DATE
    ,p_format               IN VARCHAR2
    ,p_comment              IN VARCHAR2
    ,p_formula              IN VARCHAR2
    ,p_colspan              IN PLS_INTEGER
    ,p_href                 IN VARCHAR2
    ,p_index                IN PLS_INTEGER
    ,p_protected            IN BOOLEAN
    )
IS

BEGIN
    IF p_date IS NULL THEN
        put_line(
                 p_fileHandle
                ,'    <Cell '||setProtected(p_protected)||setIndex(p_index)||setURL(p_href)||setColspan(p_colspan)||setStyle(NVL(p_format,c_date))||formatFormula(p_formula)||'>'||
                 formatComment(p_comment)||
                 '</Cell>'
                );
    -- Dates before 01.01.1900 cannot be displayed by Excel therefore they are written as Text
    -- System nls_date_format is used
    ELSIF p_date < TO_DATE('01.01.1900','dd.mm.yyyy') THEN
        writeVarcharData(
             p_fileHandle
            ,TO_CHAR(p_date)
            ,c_textDefault
            ,p_comment
            ,NULL
            ,p_colspan
            ,p_href
            ,p_index
            ,p_protected
            );
    ELSE
        put_line(
                 p_fileHandle
                ,'    <Cell '||setProtected(p_protected)||setIndex(p_index)||setURL(p_href)||setColspan(p_colspan)||setStyle(NVL(p_format,c_date))||formatFormula(p_formula)||'>'||
                 '<Data ss:Type="DateTime">'||convertDateIntoExcelFormat(p_date)||'</Data>'||
                 formatComment(p_comment)||
                 '</Cell>'
                );
    END IF;

END writeDateData;

/*-----------------------------------------------------------------------------
||  Name:         writeData
||  Description:  write date
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_date - date to be written
||                p_format - Formatting of the date values according to constants
||                  in package header.
||                p_comment - comment.
||                p_formula - The cells are referenced relative to the formula cell
||                  The syntax is e.g.
||                       =SUM(R[-2]C,R[-1]C)
||                       =IF(B6>0;"T";"U")
||                       =AVERAGE(R[-2]C:R[-1]C)
||                       =COUNT(R[-2]C:R[-1]C)
||                p_colspan - Merge cells horizontally.
||                p_href - URL to which to link this cell.
||                p_index - column index within containing row.
||                  Indices must appear in strictly increasing order
||                  Indices must not overlap
||                p_protected - indicates whether or not this cell is protected.
||                  When the worksheet is unprotected, cell-level protection has
||                  no effect. When a cell is protected, it will not allow the
||                  user to enter information into it.
-----------------------------------------------------------------------------*/
PROCEDURE writeData
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_date                 IN DATE
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    )
IS

BEGIN
    writeDateData(
         p_fileHandle
        ,p_date
        ,p_format
        ,p_comment
        ,p_formula
        ,p_colspan
        ,p_href
        ,p_index
        ,p_protected
        );
END writeData;

PROCEDURE writeData
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_date                 IN DATE
    ,p_format               IN VARCHAR2 := NULL
    ,p_comment              IN VARCHAR2 := NULL
    ,p_formula              IN VARCHAR2 := NULL
    ,p_colspan              IN PLS_INTEGER := NULL
    ,p_href                 IN VARCHAR2 := NULL
    ,p_index                IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    )
IS

BEGIN
    g_setClob := TRUE;
    writeDateData(
         NULL
        ,p_date
        ,p_format
        ,p_comment
        ,p_formula
        ,p_colspan
        ,p_href
        ,p_index
        ,p_protected
        );
    p_file  := p_file||g_clob;
    --dbms_lob.writeappend(p_file, LENGTH(g_clob), g_clob);
    dbms_lob.TRIM(g_clob,0);
    g_setClob := TRUE;

END writeData;

FUNCTION centi2inch
    (
     p_centimeter           IN NUMBER
    )
    RETURN VARCHAR2
IS

BEGIN
    RETURN convertNumberIntoExcelFormat(p_centimeter / 254 * 100);
END centi2inch;

FUNCTION replaceMarginText
    (
     p_text                 IN VARCHAR2
    )
    RETURN VARCHAR2
IS

BEGIN
    pld('replaceMarginText');

    RETURN  REPLACE(
             REPLACE(
              REPLACE(
               REPLACE(
                REPLACE(
                 REPLACE(
                  REPLACE(
                   REPLACE(
                    REPLACE(
                     REPLACE(
                      REPLACE(
                       REPLACE(
                        escapeSpecialCharacters( p_text)
                        ,c_mtFontSize8,CHR(38)||'amp;8')
                       ,c_mtFontSize10,CHR(38)||'amp;10')
                      ,c_mtFontSize12,CHR(38)||'amp;12')
                     ,c_mtFontSize14,CHR(38)||'amp;14')
                    ,c_mtFontBold,CHR(38)||'amp;'||CHR(38)||'quot;Arial,Fett'||CHR(38)||'quot;')
                   ,c_mtPage,CHR(38)||'amp;S')
                  ,c_mtPageTotal, CHR(38)||'amp;A')
                 ,c_mtTime, CHR(38)||'amp;U')
                ,c_mtDate, CHR(38)||'amp;D')
               ,c_mtPath, CHR(38)||'amp;P')
              ,c_mtName, CHR(38)||'amp;N')
             ,c_mtTab, CHR(38)||'amp;B');

END replaceMarginText;

PROCEDURE writePrintSetup
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_rec_printSetup       IN t_rec_printSetup
    )
IS
BEGIN
    put_line(p_fileHandle,'   <PageSetup>');

    IF p_rec_printSetup.landscape THEN
        put_line(p_fileHandle,'    <Layout x:Orientation="Landscape"/>');
    END IF;

    IF p_rec_printSetup.headerLeft IS NOT NULL
      OR p_rec_printSetup.headerCenter IS NOT NULL
      OR p_rec_printSetup.headerRight IS NOT NULL
    THEN
        put_line(
             p_fileHandle
            ,'    <Header x:Margin="'||centi2inch(NVL(p_rec_printSetup.headerMargin,13/10))||'" '||
             'x:Data="'||CHR(38)||'amp;L'||replaceMarginText(p_rec_printSetup.headerLeft)||' '||
             ''||CHR(38)||'amp;Z'||replaceMarginText(p_rec_printSetup.headerCenter)||' '||
             ''||CHR(38)||'amp;R'||replaceMarginText(p_rec_printSetup.headerRight)||' "/>'
             );
    END IF;

    IF p_rec_printSetup.footerLeft IS NOT NULL
      OR p_rec_printSetup.footerCenter IS NOT NULL
      OR p_rec_printSetup.footerRight IS NOT NULL
    THEN
        put_line(
             p_fileHandle
            ,'    <Footer x:Margin="'||centi2inch(NVL(p_rec_printSetup.footerMargin,13/10))||'" '||
             'x:Data="'||CHR(38)||'amp;L'||replaceMarginText(p_rec_printSetup.footerLeft)||' '||
             ''||CHR(38)||'amp;Z'||replaceMarginText(p_rec_printSetup.footerCenter)||' '||
             ''||CHR(38)||'amp;R'||replaceMarginText(p_rec_printSetup.footerRight)||' "/>'
             );
    END IF;

    put_line(
         p_fileHandle
        ,'    <PageMargins x:Bottom="'||centi2inch(NVL(p_rec_printSetup.pageMarginBottom,25/10))||'" '||
         'x:Left="'||centi2inch(NVL(p_rec_printSetup.pageMarginLeft,25/10))||'" '||
         'x:Right="'||centi2inch(NVL(p_rec_printSetup.pageMarginRight,25/10))||'" '||
         'x:Top="'||centi2inch(NVL(p_rec_printSetup.pageMarginTop,25/10))||'"/>'
         );

    put_line(p_fileHandle,'   </PageSetup>');

END writePrintSetup;

PROCEDURE writeCloseWorksheet
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_lineFixed            IN PLS_INTEGER
    ,p_colFixed             IN PLS_INTEGER
    ,p_tab_caption          IN t_tab_caption
    ,p_autofilter           IN BOOLEAN
    ,p_tab_conditionalFormats IN t_tab_conditionalFormats
    ,p_rec_printSetup       IN t_rec_printSetup
    )
IS

BEGIN
    put_line(p_fileHandle,'   </Row>');
    put_line(p_fileHandle,'  </Table>');
    -- Worksheet to be closed
    put_line(p_fileHandle,'  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">');
    put_line(p_fileHandle,'   <Selected/>');
    -- Print output in landscape format
    writePrintSetup(p_fileHandle,p_rec_printSetup);
    -- Fix first n lines when scrolling
    IF   p_lineFixed > 0
      AND p_colFixed = 0
    THEN
        put_line(p_fileHandle,'   <FreezePanes/>');
        put_line(p_fileHandle,'   <FrozenNoSplit/>');
        put_line(p_fileHandle,'   <SplitHorizontal>'||p_lineFixed||'</SplitHorizontal>');
        put_line(p_fileHandle,'   <TopRowBottomPane>'||p_lineFixed||'</TopRowBottomPane>');
        put_line(p_fileHandle,'   <ActivePane>2</ActivePane>');
        put_line(p_fileHandle,'   <Panes>');
        put_line(p_fileHandle,'    <Pane>');
        put_line(p_fileHandle,'     <Number>3</Number>');
        put_line(p_fileHandle,'    </Pane>');
        put_line(p_fileHandle,'    <Pane>');
        put_line(p_fileHandle,'     <Number>2</Number>');
        put_line(p_fileHandle,'    </Pane>');
        put_line(p_fileHandle,'   </Panes>');
    ELSIF p_lineFixed = 0
      AND p_colFixed > 0
    THEN
        put_line(p_fileHandle,'   <FreezePanes/>');
        put_line(p_fileHandle,'   <FrozenNoSplit/>');
        put_line(p_fileHandle,'   <SplitVertical>'||p_colFixed||'</SplitVertical>');
        put_line(p_fileHandle,'   <LeftColumnRightPane>'||p_colFixed||'</LeftColumnRightPane>');
        put_line(p_fileHandle,'   <ActivePane>1</ActivePane>');
        put_line(p_fileHandle,'   <Panes>');
        put_line(p_fileHandle,'    <Pane>');
        put_line(p_fileHandle,'     <Number>3</Number>');
        put_line(p_fileHandle,'    </Pane>');
        put_line(p_fileHandle,'    <Pane>');
        put_line(p_fileHandle,'     <Number>1</Number>');
        put_line(p_fileHandle,'     <ActiveRow>1</ActiveRow>');
        put_line(p_fileHandle,'     <ActiveCol>0</ActiveCol>');
        put_line(p_fileHandle,'    </Pane>');
        put_line(p_fileHandle,'   </Panes>');
    ELSIF p_lineFixed > 0
      AND p_colFixed > 0
    THEN
        put_line(p_fileHandle,'   <FreezePanes/>');
        put_line(p_fileHandle,'   <FrozenNoSplit/>');
        put_line(p_fileHandle,'   <SplitHorizontal>'||p_lineFixed||'</SplitHorizontal>');
        put_line(p_fileHandle,'   <TopRowBottomPane>'||p_lineFixed||'</TopRowBottomPane>');
        put_line(p_fileHandle,'   <SplitVertical>'||p_colFixed||'</SplitVertical>');
        put_line(p_fileHandle,'   <LeftColumnRightPane>'||p_colFixed||'</LeftColumnRightPane>');
        put_line(p_fileHandle,'   <ActivePane>0</ActivePane>');
        put_line(p_fileHandle,'   <Panes>');
        put_line(p_fileHandle,'    <Pane>');
        put_line(p_fileHandle,'     <Number>3</Number>');
        put_line(p_fileHandle,'    </Pane>');
        put_line(p_fileHandle,'    <Pane>');
        put_line(p_fileHandle,'     <Number>1</Number>');
        put_line(p_fileHandle,'    </Pane>');
        put_line(p_fileHandle,'    <Pane>');
        put_line(p_fileHandle,'     <Number>2</Number>');
        put_line(p_fileHandle,'    </Pane>');
        put_line(p_fileHandle,'    <Pane>');
        put_line(p_fileHandle,'     <Number>0</Number>');
        put_line(p_fileHandle,'    </Pane>');
        put_line(p_fileHandle,'   </Panes>');
    ELSE
        put_line(p_fileHandle,'   <Panes>');
        put_line(p_fileHandle,'    <Pane>');
        put_line(p_fileHandle,'     <Number>3</Number>');
        put_line(p_fileHandle,'     <ActiveRow>1</ActiveRow>');
        put_line(p_fileHandle,'    </Pane>');
        put_line(p_fileHandle,'   </Panes>');

    END IF;
    put_line(p_fileHandle,'   <ProtectObjects>False</ProtectObjects>');
    put_line(p_fileHandle,'   <ProtectScenarios>False</ProtectScenarios>');
    put_line(p_fileHandle,'  </WorksheetOptions>');

    writeAutofilter(p_fileHandle,p_tab_caption,p_autofilter);
    writeConditionalFormatting(p_fileHandle,p_tab_conditionalFormats);

    put_line(p_fileHandle,' </Worksheet>');

END writeCloseWorksheet;
/*-----------------------------------------------------------------------------
||  Name:         closeWorksheet
||  Description:  close last data row and close worksheet
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_lineFixed - is the n-th line to be fixed when scrolling
||                  within the last worksheet
||                  0 = not fixed
||                p_colFixed - is the n-th column to be fixed when scrolling
||                  within the last worksheet
||                  0 = not fixed
||                p_tab_caption - collection with caption information
||                p_autofilter - Set autofilter TRUE = Yes
||                p_tab_conditionalFormats - collection with formatting information
||                p_landscape - Landscape format for printing
-----------------------------------------------------------------------------*/
PROCEDURE closeWorksheet
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_lineFixed            IN PLS_INTEGER := 1
    ,p_colFixed             IN PLS_INTEGER := 0
    ,p_tab_caption          IN t_tab_caption := g_tab_caption
    ,p_autofilter           IN BOOLEAN := TRUE
    ,p_tab_conditionalFormats IN t_tab_conditionalFormats := g_tab_conditionalFormats
    ,p_rec_printSetup       IN t_rec_printSetup := g_rec_printSetup
    )
IS

BEGIN
    pld('closeWorksheet File');

    writeCloseWorksheet(
         p_fileHandle
        ,p_lineFixed
        ,p_colFixed
        ,p_tab_caption
        ,p_autofilter
        ,p_tab_conditionalFormats
        ,p_rec_printSetup
        );

END closeWorksheet;

PROCEDURE closeWorksheet
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_lineFixed            IN PLS_INTEGER := 1
    ,p_colFixed             IN PLS_INTEGER := 0
    ,p_tab_caption          IN t_tab_caption := g_tab_caption
    ,p_autofilter           IN BOOLEAN := TRUE
    ,p_tab_conditionalFormats IN t_tab_conditionalFormats := g_tab_conditionalFormats
    ,p_rec_printSetup       IN t_rec_printSetup := g_rec_printSetup
    )
IS

BEGIN
    pld('closeWorksheet CLOB');

    g_setClob := TRUE;
    writeCloseWorksheet(
         NULL
        ,p_lineFixed
        ,p_colFixed
        ,p_tab_caption
        ,p_autofilter
        ,p_tab_conditionalFormats
        ,p_rec_printSetup
        );
    p_file  := p_file||g_clob;
    --dbms_lob.writeappend(p_file, LENGTH(g_clob), g_clob);
    dbms_lob.TRIM(g_clob,0);
    g_setClob := TRUE;

END closeWorksheet;

PROCEDURE writeNewWorksheet
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_sheetname            IN VARCHAR2
    ,p_tab_caption          IN t_tab_caption
    ,p_tab_columns          IN t_tab_columns
    ,p_format               IN VARCHAR2
    ,p_height               IN PLS_INTEGER
    ,p_protected            IN BOOLEAN
    ,p_beginNewRow          IN BOOLEAN
    )
IS

BEGIN

    --New Worksheet
    put_line(p_fileHandle,' <Worksheet ss:Name="'||escapeSpecialCharacters(p_sheetname)||'"'||setProtected(p_protected)||'>');

    IF p_tab_caption.COUNT > 0 THEN
        writeNamedRange(p_fileHandle,p_tab_caption,p_sheetname);
    END IF;

    put_line(p_fileHandle,'  <Table ss:DefaultColumnWidth="60">');

    IF p_tab_columns.COUNT > 0 THEN
        writeColumns(p_fileHandle,p_tab_columns);
    END IF;

    IF p_tab_caption.COUNT > 0 THEN
        writeCaption(p_fileHandle,p_tab_caption);
    END IF;

    IF p_beginNewRow THEN
        put_line(p_fileHandle,'   <Row ss:AutoFitHeight="1"'||setFormat(p_format)||setHeight(p_height)||'>');
    END IF;

END writeNewWorksheet;

/*-----------------------------------------------------------------------------
||  Name:         newWorksheet
||  Description:  open a new worksheet and open first data row
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
||                p_sheetname - name of the first worksheet.
||                  This name has to be unique within the file
||                p_tab_caption - collection with caption information
||                p_tab_columns - collection with column settings
||                p_format - Formatting of the cells in first row according to constants
||                  in package header or user defines formats.
||                p_height - Height of first row in pt
||                p_protected - indicates whether or not cell protection is in effect.
||                  When the worksheet is unprotected, cell-level protection has no effect
||                p_beginNewRow - Begin new data row after opening the worksheet
-----------------------------------------------------------------------------*/
PROCEDURE newWorksheet
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_sheetname            IN VARCHAR2
    ,p_tab_caption          IN t_tab_caption := g_tab_caption
    ,p_tab_columns          IN t_tab_columns := g_tab_columns
    ,p_format               IN VARCHAR2 := NULL
    ,p_height               IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    ,p_beginNewRow          IN BOOLEAN  := TRUE
    )
IS

BEGIN
    pld('newWorksheet File');

    writeNewWorksheet(
         p_fileHandle
        ,p_sheetname
        ,p_tab_caption
        ,p_tab_columns
        ,p_format
        ,p_height
        ,p_protected
        ,p_beginNewRow
        );
END newWorksheet;

PROCEDURE newWorksheet
    (
     p_file                 IN OUT NOCOPY CLOB
    ,p_sheetname            IN VARCHAR2
    ,p_tab_caption          IN t_tab_caption := g_tab_caption
    ,p_tab_columns          IN t_tab_columns := g_tab_columns
    ,p_format               IN VARCHAR2 := NULL
    ,p_height               IN PLS_INTEGER := NULL
    ,p_protected            IN BOOLEAN  := NULL
    ,p_beginNewRow          IN BOOLEAN  := TRUE
    )
IS

BEGIN
    pld('newWorksheet CLOB');

    g_setClob := TRUE;
    writeNewWorksheet(
         NULL
        ,p_sheetname
        ,p_tab_caption
        ,p_tab_columns
        ,p_format
        ,p_height
        ,p_protected
        ,p_beginNewRow
        );
    p_file  := p_file||g_clob;
    --dbms_lob.writeappend(p_file, LENGTH(g_clob), g_clob);
    dbms_lob.TRIM(g_clob,0);
    g_setClob := TRUE;

END newWorksheet;
/*-----------------------------------------------------------------------------
||  Name:         closeFile
||  Description:  close last data row and close file
||  Parameter:    p_fileHandle - file handle from 'createNewFile'
-----------------------------------------------------------------------------*/
PROCEDURE closeFile
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    )
IS

BEGIN
    pld('closeFile File');

    put_line(p_fileHandle,'</Workbook>');

    fclose(p_fileHandle);

END closeFile;

PROCEDURE closeFile
    (
     p_file                 IN OUT NOCOPY CLOB
    )
IS

BEGIN
    pld('closeFile CLOB');

    g_setClob := TRUE;
    put_line(NULL,'</Workbook>');
    p_file  := p_file||g_clob;
    --dbms_lob.writeappend(p_file, LENGTH(g_clob), g_clob);
    dbms_lob.TRIM(g_clob,0);
    g_setClob := TRUE;

END closeFile;

PROCEDURE writeSumCols
    (
     p_fileHandle           IN utl_file.FILE_TYPE
    ,p_sumColumns           IN t_tab_abfrage
    ,p_row_cnt              IN PLS_INTEGER
    )
IS
BEGIN
    pld('writeSumCols File');
    pld('. p_sumColumns.COUNT '||p_sumColumns.COUNT);

    IF p_sumColumns.COUNT > 0 THEN
        FOR i IN 1 .. g_desc_t.COUNT LOOP
            IF p_sumColumns.EXISTS(i)
              AND UPPER(p_sumColumns(i)) = 'Y'
            THEN
                writeData(
                         p_fileHandle
                        ,0
                        ,c_unformattedSum
                        ,'Col Sum'
                        ,'=SUM(R[-'||TO_CHAR(p_row_cnt + 1)||']C:R[-1]C)'
                        );
            ELSE
                writeData(p_fileHandle,' ');
            END IF;
        END LOOP;
    END IF;
END writeSumCols;

PROCEDURE writeAbfrage
    (
     p_cursor               IN INTEGER
    ,p_fileHandle           IN utl_file.FILE_TYPE
    ,p_sheetname            IN VARCHAR2
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    )
IS
    tab_columns             t_tab_columns;
    tab_caption             t_tab_caption;
    tab_conditionalFormats  t_tab_conditionalFormats;

    v_page                  PLS_INTEGER := 2;

    l_row_cnt               PLS_INTEGER := 0;
    l_col_cnt               PLS_INTEGER;
    l_status                PLS_INTEGER;
    g_cvalue                VARCHAR2(32767);

    v_nls_date_format       VARCHAR2(50);
    v_my_date_format        VARCHAR2(30) := 'dd.mm.yyyy hh24:mi:ss';

BEGIN
    -- Set the Date format. Otherwise the result depends on initial parameter settings
    -- Errors might occure when i.e. date format is set to dd.mm.rr

    SELECT  value
    INTO    v_nls_date_format
    FROM    nls_session_parameters
    WHERE   UPPER(parameter) = 'NLS_DATE_FORMAT';

    EXECUTE IMMEDIATE 'ALTER SESSION SET NLS_DATE_FORMAT = '''||v_my_date_format||'''';


    -- read column properties
    dbms_sql.describe_columns( p_cursor, l_col_cnt, g_desc_t );
   --
    FOR i IN 1 .. g_desc_t.COUNT LOOP
        dbms_sql.define_column( p_cursor, i, g_cvalue, 32765);
    END LOOP;

    -- write captions and column width
    FOR i IN 1 .. g_desc_t.COUNT LOOP
        tab_columns(i).cWidth         := g_desc_t(i).col_name_len * 8;--
        tab_columns(i).cAutowidth     := TRUE;

        BEGIN
            tab_caption(i).title     := p_titles(i);
        EXCEPTION
            WHEN OTHERS THEN
                tab_caption(i).title := g_desc_t(i).col_name;
        END;
    END LOOP;

    -- open first worksheet
    newWorksheet(
             p_fileHandle
            ,p_sheetname
            ,tab_caption
            ,tab_columns
            );

    l_status := dbms_sql.execute( p_cursor );

    LOOP
        EXIT WHEN dbms_sql.fetch_rows( p_cursor ) <= 0;
        l_row_cnt := l_row_cnt + 1;

        FOR i IN 1 .. g_desc_t.COUNT LOOP
            dbms_sql.column_value( p_cursor, i, g_cvalue );
            g_cvalue := REPLACE( g_cvalue,CHR(10), '<br>' );
            IF g_desc_t(i).col_type = 2
            THEN
                writeData(p_fileHandle,TO_NUMBER(g_cvalue),c_unformatted);
            ELSIF g_desc_t(i).col_type = 12
            THEN
                writeData(p_fileHandle,TO_DATE(g_cvalue),c_date);
            ELSE
                writeData(p_fileHandle,NVL(g_cvalue,p_showNullAs));
            END IF;
        END LOOP;

        IF MOD(l_row_cnt,p_maxRows) = 0 THEN
            writeSumCols(
                 p_fileHandle
                ,p_sumColumns
                ,l_row_cnt
                );
            closeWorksheet(
                 p_fileHandle
                ,1
                ,0
                ,tab_caption
                --,TRUE
                --,tab_conditionalFormats
                );
            newWorksheet(
                 p_fileHandle
                ,p_sheetname||'-'||TO_CHAR(v_page)
                ,tab_caption
                ,tab_columns
                );
            v_page      := v_page + 1;
            l_row_cnt   := 0;
        ELSE
            newDataRow(p_fileHandle);
        END IF;
    END LOOP;

    writeSumCols(
         p_fileHandle
        ,p_sumColumns
        ,l_row_cnt
        );
    closeWorksheet(
         p_fileHandle
        ,1
        ,0
        ,tab_caption
        --,TRUE
        --,tab_conditionalFormats
        );

    EXECUTE IMMEDIATE 'ALTER SESSION SET NLS_DATE_FORMAT = '''||v_nls_date_format||'''';

END writeAbfrage;

/*-----------------------------------------------------------------------------
||  Name:         abfrage
||  Description:  write the output of the cursor  into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||  Parameter:    p_cursor - cursor
||                p_path - directory path - DIRECTORY_NAME from DB
||                p_filename - file name
||                p_sumColumns - list of columns to be summed
||                p_maxRows - maximum rows to be written on one worksheet Default 64000
||                p_showNullAs - how to disply NULLS
||                p_titles - list of column captions
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
-----------------------------------------------------------------------------*/
PROCEDURE abfrage
    (
     p_cursor               IN INTEGER
    ,p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2     DEFAULT NULL
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
IS
--
    v_fileHandle            utl_file.FILE_TYPE;
    v_filename              VARCHAR2(200);

BEGIN
    -- Open file
    v_filename     := NVL(      p_filename
                                ,'query_'||
                                 LOWER(SYS_CONTEXT ('USERENV', 'OS_USER'))||'_'||
                                 TO_CHAR(SYSDATE,'YYYYMMDD_HH24MISS')||
                                 '.xls'
                                 );

    v_fileHandle   := createNewFile(p_path,v_filename,g_tab_customStyles,g_rec_customStyles,p_encoding);
    writeAbfrage(
         p_cursor
        ,v_fileHandle
        ,'query'
        ,p_sumColumns
        ,p_maxRows
        ,p_showNullAs
        ,p_titles
        );
    closeFile(v_fileHandle);

END abfrage;

/*-----------------------------------------------------------------------------
||  Name:         build_cursor
||  Description:  build a cursor from a SELECT
||  Parameter:    p_query - SELECT string
||                p_parm_names - list of parameter names
||                p_parm_values - list of parameter values
-----------------------------------------------------------------------------*/
FUNCTION build_cursor
    (
     p_query                IN VARCHAR2
    ,p_parm_names           IN t_tab_abfrage
    ,p_parm_values          IN t_tab_abfrage
    )
    RETURN INTEGER
IS
    cur_c                   INTEGER := dbms_sql.open_cursor;
    i                       PLS_INTEGER := 1;
BEGIN
    pld('build_cursor');

    dbms_sql.parse (cur_c, p_query, dbms_sql.native);
    LOOP
        dbms_sql.bind_variable( cur_c, p_parm_names(i), p_parm_values(i) );
        i := i + 1;
    END LOOP;

    RETURN cur_c;

EXCEPTION
    WHEN OTHERS THEN
        RETURN cur_c;
END build_cursor;

/*-----------------------------------------------------------------------------
||  Name:         abfrage
||  Description:  write the output of the query into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||  Parameter:    p_query - SELECT
||                p_path - DIRECTORY_NAME from DB
||                p_filename - file name
||                p_parm_names - list of parameter names
||                p_parm_values - list of parameter values
||                p_sumColumns - list of columns to be summed
||                p_maxRows - maximum rows to be written on one worksheet Default 64000
||                p_showNullAs - how to disply NULLS
||                p_titles - list of column captions
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
-----------------------------------------------------------------------------*/
PROCEDURE abfrage
    (
     p_query                IN VARCHAR2
    ,p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2     DEFAULT NULL
    ,p_parm_names           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_parm_values          IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
IS
BEGIN
    abfrage( build_cursor(
                     p_query
                    ,p_parm_names
                    ,p_parm_values
                    )
            ,p_path
            ,p_filename
            ,p_sumColumns
            ,p_maxRows
            ,p_showNullAs
            ,p_titles
            ,p_encoding
            );
END abfrage;

/*-----------------------------------------------------------------------------
||  Name:         abfrage
||  Description:  write the output of the cursor  into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||                  next query into a file opend with FUNCTION abfrage
||  Parameter:    p_cursor - cursor
||                p_fileHandle - file handle from FUNCTION 'abfrage'
||                p_sheetname - name of the current worksheet.
||                  This name has to be unique within the file
||                p_sumColumns - list of columns to be summed
||                p_maxRows - maximum rows to be written on one worksheet Default 64000
||                p_showNullAs - how to disply NULLS
||                p_titles - list of column captions
||                p_close - should the file be closed after the query
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
-----------------------------------------------------------------------------*/
FUNCTION abfrage
    (
     p_cursor               IN INTEGER
    ,p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2     DEFAULT NULL
    ,p_sheetname            IN VARCHAR2
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
    RETURN utl_file.FILE_TYPE
IS
    v_fileHandle            utl_file.FILE_TYPE;
    v_filename              VARCHAR2(200);
BEGIN
        -- Open file
    v_filename     := NVL(      p_filename
                                ,'query_'||
                                 LOWER(SYS_CONTEXT ('USERENV', 'OS_USER'))||'_'||
                                 TO_CHAR(SYSDATE,'YYYYMMDD_HH24MISS')||
                                 '.xls'
                                 );

    v_fileHandle   := createNewFile(p_path,v_filename,g_tab_customStyles,g_rec_customStyles,p_encoding);

    writeAbfrage(
         p_cursor
        ,v_fileHandle
        ,p_sheetname
        ,p_sumColumns
        ,p_maxRows
        ,p_showNullAs
        ,p_titles
        );

    IF p_close THEN
        closeFile(v_fileHandle);
    END IF;

    RETURN v_fileHandle;

END abfrage;

/*-----------------------------------------------------------------------------
||  Name:         abfrage
||  Description:  write the output of the query into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||  Parameter:    p_query - SELECT
||                p_path - DIRECTORY_NAME from DB
||                p_filename - file name
||                p_sheetname - name of the first worksheet.
||                  This name has to be unique within the file
||                p_parm_names - list of parameter names
||                p_parm_values - list of parameter values
||                p_sumColumns - list of columns to be summed
||                p_maxRows - maximum rows to be written on one worksheet Default 64000
||                p_showNullAs - how to disply NULLS
||                p_titles - list of column captions
||                p_close - should the file be closed after the query
||  @param        p_encoding - Character encoding like UTF-8, UTF-16. DEFAULT windows-1252
-----------------------------------------------------------------------------*/
FUNCTION abfrage
    (
     p_query                IN VARCHAR2
    ,p_path                 IN VARCHAR2
    ,p_filename             IN VARCHAR2     DEFAULT NULL
    ,p_sheetname            IN VARCHAR2
    ,p_parm_names           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_parm_values          IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
    RETURN utl_file.FILE_TYPE
IS
BEGIN
    RETURN abfrage(
                 build_cursor(
                     p_query
                    ,p_parm_names
                    ,p_parm_values
                    )
                ,p_path
                ,p_filename
                ,p_sheetname
                ,p_sumColumns
                ,p_maxRows
                ,p_showNullAs
                ,p_titles
                ,p_close
                ,p_encoding
                );
END abfrage;

/*-----------------------------------------------------------------------------
||  Name:         abfrage
||  Description:  write the output of the cursor  into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||                  next query into a file opend with FUNCTION abfrage
||  Parameter:    p_cursor - cursor
||                p_fileHandle - file handle from FUNCTION 'abfrage'
||                p_sheetname - name of the current worksheet.
||                  This name has to be unique within the file
||                p_sumColumns - list of columns to be summed
||                p_maxRows - maximum rows to be written on one worksheet Default 64000
||                p_showNullAs - how to disply NULLS
||                p_titles - list of column captions
||                p_close - should the file be closed after the query
-----------------------------------------------------------------------------*/
PROCEDURE abfrage
    (
     p_cursor               IN INTEGER
    ,p_fileHandle           IN utl_file.FILE_TYPE
    ,p_sheetname            IN VARCHAR2
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    )
IS
BEGIN
    writeAbfrage(
         p_cursor
        ,p_fileHandle
        ,p_sheetname
        ,p_sumColumns
        ,p_maxRows
        ,p_showNullAs
        ,p_titles
        );

    IF p_close THEN
        closeFile(p_fileHandle);
    END IF;

END abfrage;

/*-----------------------------------------------------------------------------
||  Name:         abfrage
||  Description:  write the output of the query into an excelformat file
||                idea taken from package owa_silk from Tom Kyte
||                  next query into a file opend with FUNCTION abfrage
||  Parameter:    p_query - SELECT
||                p_fileHandle - file handle from FUNCTION 'abfrage'
||                p_sheetname - name of the current worksheet.
||                  This name has to be unique within the file
||                p_parm_names - list of parameter names
||                p_parm_values - list of parameter values
||                p_sumColumns - list of columns to be summed
||                p_maxRows - maximum rows to be written on one worksheet Default 64000
||                p_showNullAs - how to disply NULLS
||                p_titles - list of column captions
||                p_close - should the file be closed after the query
-----------------------------------------------------------------------------*/
PROCEDURE abfrage
    (
     p_query                IN VARCHAR2
    ,p_fileHandle           IN utl_file.FILE_TYPE
    ,p_sheetname            IN VARCHAR2
    ,p_parm_names           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_parm_values          IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    )
IS
BEGIN
    abfrage( build_cursor(
                     p_query
                    ,p_parm_names
                    ,p_parm_values
                    )
            ,p_fileHandle
            ,p_sheetname
            ,p_sumColumns
            ,p_maxRows
            ,p_showNullAs
            ,p_titles
            ,p_close
            );
END abfrage;

PROCEDURE abfrage
    (
     p_cursor               IN INTEGER
    ,p_file                 IN OUT NOCOPY CLOB
    ,p_sheetname            IN VARCHAR2
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    ,p_first                IN BOOLEAN      DEFAULT TRUE
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
IS
BEGIN
    g_setClob := TRUE;

    IF p_first THEN
        createNewFile(p_file,g_tab_customStyles,g_rec_customStyles,p_encoding);
    END IF;

    writeAbfrage(
         p_cursor
        ,NULL
        ,p_sheetname
        ,p_sumColumns
        ,p_maxRows
        ,p_showNullAs
        ,p_titles
        );
    p_file  := p_file||g_clob;
    --dbms_lob.writeappend(p_file, LENGTH(g_clob), g_clob);
    dbms_lob.TRIM(g_clob,0);
    g_setClob := TRUE;


    IF p_close THEN
        closeFile(p_file);
    END IF;

END abfrage;

PROCEDURE abfrage
    (
     p_query                IN VARCHAR2
    ,p_file                 IN OUT NOCOPY CLOB
    ,p_sheetname            IN VARCHAR2
    ,p_parm_names           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_parm_values          IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_sumColumns           IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_maxRows              IN NUMBER       DEFAULT 64000
    ,p_showNullAs           IN VARCHAR2     DEFAULT NULL
    ,p_titles               IN t_tab_abfrage DEFAULT t_tab_abfrage()
    ,p_close                IN BOOLEAN      DEFAULT TRUE
    ,p_first                IN BOOLEAN      DEFAULT TRUE
    ,p_encoding             IN VARCHAR2     DEFAULT 'windows-1252'
    )
IS
BEGIN
    abfrage( build_cursor(
                     p_query
                    ,p_parm_names
                    ,p_parm_values
                    )
            ,p_file
            ,p_sheetname
            ,p_sumColumns
            ,p_maxRows
            ,p_showNullAs
            ,p_titles
            ,p_close
            ,p_first
            ,p_encoding
            );
END abfrage;

/**
||  @Name         getRelativeCellReference
||  @Description  The reference in formulas regarding other cells has to be
||                  given relative to the current cell. This function gives
||                  the reference in the correct format R[x]C[y]
||  @param        p_rowFrom - Row of current cell
||  @param        p_colFrom - Column of current cell
||  @param        p_rowTo   - Row of target cell
||  @param        p_colTo   - Column of target cell
||  @return       Reference to target cell, format R[x]C[y]
*/
FUNCTION getRelativeCellReference
    (
     p_rowFrom              IN PLS_INTEGER
    ,p_colFrom              IN PLS_INTEGER
    ,p_rowTo                IN PLS_INTEGER
    ,p_colTo                IN PLS_INTEGER
    )
    RETURN VARCHAR2
IS
    v_reference             VARCHAR2(100);
    v_diff                  INTEGER;

BEGIN
    pld('getRelativeCellReference');
    pld('. p_rowFrom '||p_rowFrom);
    pld('. p_colFrom '||p_colFrom);
    pld('. p_rowTo '||p_rowTo);
    pld('. p_colTo '||p_colTo);

    IF   p_colFrom IS NULL
      OR p_rowFrom IS NULL
      OR p_colTo IS NULL
      OR p_rowTo IS NULL
    THEN
        RAISE_APPLICATION_ERROR(-20001,'Cell references must not be NULL');
    ELSIF p_colFrom < 1
      OR p_rowFrom < 1
      OR p_colTo < 1
      OR p_rowTo < 1
    THEN
        RAISE_APPLICATION_ERROR(-20001,'Cell references must not be less than 1');
    END IF;

    v_diff  := p_rowTo - p_rowFrom;

    IF v_diff = 0 THEN
        v_reference := 'R';
    ELSE
        v_reference := 'R['||v_diff||']';
    END IF;

    v_diff  := p_colTo - p_colFrom;

    IF v_diff = 0 THEN
        v_reference := v_reference||'C';
    ELSE
        v_reference := v_reference||'C['||v_diff||']';
    END IF;

    pld('. v_reference '||v_reference);
    RETURN v_reference;

END getRelativeCellReference;

 PROCEDURE gen_pr_gener_file_excel(
  p_val_sql                         IN   VARCHAR2,
  p_file_name                       IN   VARCHAR2,
  p_max_rows                        IN   NUMBER)
 IS

 l_cursor number := dbms_sql.open_cursor;

 BEGIN

  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY ' ||  gc_dire_name_repo || ' AS ''' || gc_path_name_repo || '''';

  dbms_sql.parse( l_cursor,p_val_sql,dbms_sql.native );

  gen_pkg_xls.abfrage(
   p_cursor =>  l_cursor,
   p_path => gc_dire_name_repo,
   p_filename => p_file_name,
   p_maxRows =>p_max_rows);

 END gen_pr_gener_file_excel;

 PROCEDURE gen_pr_gener_file_excel_tclob(
  p_val_sql                         IN   VARCHAR2,
  p_file_name                       IN   VARCHAR2,
  p_max_rows                        IN   NUMBER,
  p_clob_exce                       OUT  CLOB)
 IS
 
 l_cursor number := dbms_sql.open_cursor;
 
 filehandler UTL_FILE.FILE_TYPE;
 lv_clob_exce CLOB;
 vLine VARCHAR2(4000);


 BEGIN
  
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY ' ||  gc_dire_name_repo || ' AS ''' || gc_path_name_repo || '''';

  dbms_sql.parse( l_cursor,p_val_sql,dbms_sql.native );
  
  gen_pkg_xls.abfrage(
   p_cursor =>  l_cursor,
   p_path => gc_dire_name_repo,
   p_filename => p_file_name,
   p_maxRows =>p_max_rows);
   
  filehandler := utl_file.fopen(gc_dire_name_repo, p_file_name, 'R');

  IF utl_file.is_open(filehandler) THEN
   LOOP
    BEGIN
     utl_file.get_line(filehandler, vLine);
     lv_clob_exce := lv_clob_exce || vLine || UTL_TCP.CRLF;
    EXCEPTION WHEN no_data_found THEN
     EXIT;
    END;
   END LOOP;
  END IF;
 
  p_clob_exce := lv_clob_exce;
  
 END gen_pr_gener_file_excel_tclob;

 PROCEDURE gen_pr_gener_clob_excel(
  p_val_sql                        IN   VARCHAR2,
  p_max_rows                       IN   NUMBER,
  p_clob                           OUT  CLOB)
 IS

  l_cursor NUMBER := dbms_sql.open_cursor;

 BEGIN

  dbms_sql.parse(l_cursor,p_val_sql,dbms_sql.native);

  gen_pkg_xls.abfrage(
     p_cursor => l_cursor
    ,p_file => p_clob
    ,p_sheetname => gc_val_name_sheet,
    p_maxRows =>p_max_rows
    );

END gen_pr_gener_clob_excel;

END GEN_PKG_XLS;
/
