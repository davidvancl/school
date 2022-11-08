    list p=16F1508
    #include "p16f1508.inc"

    #define SETBT   PORTA,4
    #define RESETBT PORTA,5
    #define OUT	    PORTC,5
    
    ; CONFIG1
    ; __config 0x3FE4
     __CONFIG _CONFIG1, _FOSC_INTOSC & _WDTE_OFF & _PWRTE_OFF & _MCLRE_ON & _CP_OFF & _BOREN_ON & _CLKOUTEN_OFF & _IESO_ON & _FCMEN_ON
    ; CONFIG2
    ; __config 0x3FFF
     __CONFIG _CONFIG2, _WRT_OFF & _STVREN_ON & _BORV_LO & _LPBOR_OFF & _LVP_ON
     
     ORG 0x00
     goto   Start
     
     ORG 0x04
     nop
     retfie
     
Start:
    movlb   .1
    movlw   0b01101000
    movwf   OSCCON
    call    Config_IOs
    movlb   .0
    
Loop:
    btfsc   SETBT
    bsf	    OUT
    btfsc   RESETBT
    bcf	    OUT
    goto    Loop
    
    #include "Config_IOs.inc"
    
    END