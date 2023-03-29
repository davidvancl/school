grammar Gramatika;
r : expression EOF;
//expressions
expression: (operand | operand operation ( operand | expression));
operand: (data_type | convert);
convert: 'Convert('data_type','INT')';
//operations
operation: (MULT|PLUS|XOR);
MULT: '*';
PLUS: '+';
XOR: '^';
//data types
data_type: (INT|BINARY|BOOL);
BINARY : 'b'[01]+;
INT: NONZERODIGIT(DIGIT)*|DIGIT;
NONZERODIGIT: ('1'..'9');
DIGIT: ('0'..'9');
BOOL: (TRUE | FALSE);
TRUE : 'true';
FALSE : 'false';
WHITESPACE : ' ' -> skip ;
