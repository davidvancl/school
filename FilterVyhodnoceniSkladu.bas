Attribute VB_Name = "Module3"
Sub FilterVyhodnoceniSkladu()
Attribute FilterVyhodnoceniSkladu.VB_Description = "Smaže øádky, které mají množství menší než nula."
Attribute FilterVyhodnoceniSkladu.VB_ProcData.VB_Invoke_Func = "d\n14"
'
' FilterVyhodnoceniSkladu Macro
' Smaže øádky, které mají množství menší než nula.
'
' Keyboard Shortcut: Ctrl+d
'
    Rows("1:1").Select
    Selection.Insert Shift:=xlDown, CopyOrigin:=xlFormatFromLeftOrAbove
    Rows("1:1").Select
    Selection.AutoFilter
    ActiveSheet.Range("$A$1:$K$3640").AutoFilter Field:=3, Criteria1:="<=0", _
        Operator:=xlAnd
    ActiveSheet.Range("$A$1:$K$3640").AutoFilter Field:=1, Criteria1:= _
        "<>*Suma*", Operator:=xlAnd
    Cells.Select
    Selection.Delete Shift:=xlUp
    Rows("1:1").Select
    Selection.Insert Shift:=xlDown, CopyOrigin:=xlFormatFromLeftOrAbove
    Rows("1:1").Select
    Selection.AutoFilter
    ActiveSheet.Range("$A$1:$K$1155").AutoFilter Field:=1, Criteria1:="=*Suma*" _
        , Operator:=xlAnd
    ActiveSheet.Range("$A$1:$K$1155").AutoFilter Field:=2, Criteria1:="<=0", _
        Operator:=xlAnd
    Cells.Select
    Selection.Delete Shift:=xlUp
End Sub
