;Zaklad pro psani vlastnich programu
    list	p=16F1508
    #include    "p16f1508.inc"

    #define BT2	    PORTA,5
    #define LED	    PORTA,2
    
    __CONFIG _CONFIG1, _FOSC_INTOSC & _WDTE_OFF & _PWRTE_OFF & _MCLRE_ON & _CP_OFF & _BOREN_OFF & _CLKOUTEN_OFF & _IESO_OFF & _FCMEN_OFF

    __CONFIG _CONFIG2, _WRT_OFF & _STVREN_ON & _BORV_LO & _LPBOR_OFF & _LVP_ON




;VARIABLE DEFINITIONS
;COMMON RAM 0x70 to 0x7F
    CBLOCK	0x70
	tmp
	cnt1
        cnt2
    ENDC
    
;**********************************************************************
	ORG     0x00
  	goto    Start
	
	ORG     0x04
	nop			;pripraveno pro obsluhu preruseni
  	retfie
	
	
	
Start	movlb	.1		;Bank1
	movlw	b'01101000'	;4MHz Medium
	movwf	OSCCON		;nastaveni hodin

	call	Config_IOs	;vola nastaveni pinu
	movlb	.0		;Bank0

Main	;hlavni smycka...
	movlw	.100
	btfss	BT2
	movlw	.200
	call	Delay_ms
	bsf	LED
	
	movlw	.100
	btfss	BT2
	movlw	.200
	call	Delay_ms
	bsf	LED

	goto	Main		;zacykleni
	

Delay_ms    movwf cnt2 ; napln?ní ?íta?e vn?j?í smy?ky z W
OutLp	    movlw .249 ; trvání smy?ky 249*4+4=1000 cykl?
	    movwf cnt1 ; napln?ní ?íta?e vnit?ní smy?ky
InLp	    nop ; t?lo vnit?ní smy?ky
	    decfsz cnt1,F
	    goto InLp ; skok na opakování vnit?ní smy?ky
	    decfsz cnt2,F
	    goto OutLp ; skok na opakování vn?j?í smy?ky
	    return
	    
    #include	"Config_IOs.inc"	;zde "#include" funguje tak, ze proste jen vlozi svuj obsah tam kam ho napisete
		
	END






		
		
		
		
		


