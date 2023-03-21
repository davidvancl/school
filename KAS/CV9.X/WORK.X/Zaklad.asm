;blikani pomoci TMR2
    list	p=16F1508
    #include    "p16F1508.inc"
    
    #define	BT1	PORTA,4
    #define	BT2	PORTA,5
   
    __CONFIG _CONFIG1, _FOSC_INTOSC & _WDTE_OFF & _PWRTE_OFF & _MCLRE_ON & _CP_OFF & _BOREN_OFF & _CLKOUTEN_OFF & _IESO_OFF & _FCMEN_OFF
    __CONFIG _CONFIG2, _WRT_OFF & _STVREN_ON & _BORV_LO & _LPBOR_OFF & _LVP_ON
    

;VARIABLE DEFINITIONS
;COMMON RAM 0x70 to 0x7F
    CBLOCK	0x70
    	cnt1
        cnt2
	value1
	value2
	value3
	dir
	position		;pozice displaye
	counter			;aktualne zobrazovane cislo
	num7S			;cislo pro zobrazeni, dalsi 3B budou displeje!
	dispL			;levy 7seg
	dispM			;prostredni 7seg
	dispR			;pravy 7seg
    ENDC
    
;**********************************************************************
	ORG     0x00
  	goto    Start
	
	ORG     0x04
	movlb	.7		;Banka7 s IOC
	btfss	IOCAF,4		;preruseni od BT1(RA4)?
	goto	BT2Int		;je to tedy od BT2...
	bcf	IOCAF,4		;vynulovat priznak od BT1(RA4)
	movlb	.0		;Banka0 s PORT
	
	movf	dir,W
	xorlw	b'00000001'
	movwf	dir
	
  	retfie

BT2Int	bcf	IOCAF,5		;vynulovat priznak od BT2(RA5)
	
	incf    counter,F
	
	movlw	0x0A
	subwf	counter,W
	
	btfsc	STATUS, 2
	clrf	counter
	
	retfie	
	
Start	clrf	counter		;vynulovani registru counter
	clrf	value1		;vynulovani registru counter
	clrf	value2		;vynulovani registru counter
	clrf	value3		;vynulovani registru counter
	
	movlw	0x01
	movwf	position	;nastavení pozice 1
	
	movlw	0x00
	movwf	dir
	
	movlb	.1		;Banka1
	movlw	b'01101000'	;4MHz Medium
	movwf	OSCCON		;nastaveni hodin

	call	Config_IOs
	call	Config_SPI
	
	;nastaveni preruseni
	movlb	.7		;Banka7 s IOC
	bsf	IOCAP,4		;BT1(RA4) nastavena detekce pozitivni hrany
	bsf	IOCAN,5		;BT2(RA5) nastavena detekce negativni hrany
	clrf	IOCAF		;smazat priznak doted detekovanych hran
	
	bsf	INTCON,IOCIE	;povolit preruseni od IOC
	bsf	INTCON,GIE	;povolit preruseni jako takove
	
	;config TMR2
	movlb	.0		;Banka0 s TMR2
	movlw	b'01111011'	;1:64 pre, 1:16 post
	movwf	T2CON
	clrf	TMR2		;vynulovat citac
	movlw	0xFF
	movwf	PR2		;nastavit na max. hodnotu
	bsf	T2CON,TMR2ON	;po nastaveni vseho zapnout TMR2
	
	movlb	.0		;Banka0 s PORT
	
	movlw	b'11111100'
	call    SendByte7S
	
	movlw	b'00000000'
	call    SendByte7S
	
	movlw	b'00000000'
	call    SendByte7S
	
	
Loop	movf    counter,W	;nacte nejvyssich 8 bit? vysledku
	
	movwf	num7S		;zapsani cisla pro zobrazeni
        call    Bin2Bcd		;z num7S udela BCD cisla v dispL-dispM-dispR
	
	movf	dispL,W
        call    Byte2Seg	;4bit. cislo ve W zmeni na segment pro zobrazeni
	movwf	dispL
	
	movf	dispM,W
        call    Byte2Seg	;4bit. cislo ve W zmeni na segment pro zobrazeni
	movwf	dispM
	
	movf	dispR,W
        call    Byte2Seg	;4bit. cislo ve W zmeni na segment pro zobrazeni
	movwf	dispR
	
	;1
	movlw	b'00000000'
	movwf	value1
	
	movf	position,W
	sublw	0x01
	btfsc	STATUS, 2
	call	Write1
	
	;2
	movlw	b'00000000'
	movwf	value2
	
	movf	position,W
	sublw	0x02
	btfsc	STATUS, 2
	call	Write2
	
	;3
	movlw	b'00000000'
	movwf	value3
	
	movf	position,W
	sublw	0x03
	btfsc	STATUS, 2
	call	Write3
	
	call	Out

	;ceka se na priznak casovace
	btfss	PIR1,TMR2IF	;ceka az dobehne TMR2
	goto	$-1
	bcf	PIR1,TMR2IF	;vynuluje jeho priznak
	
	movf	dir,W
	sublw	0x01
	btfsc	STATUS, 2
	goto	Incr
	goto	Decr
	
Finish	movf	position,W
	sublw	0x04
	btfsc	STATUS, 2
	call	Set_1
	
	movf	position,W
	sublw	0x00
	btfsc	STATUS, 2
	call	Set_4
	
	call	Out
	
        goto    Loop

Incr	incf	position,F
	goto	Finish
	
Decr	decf    position,F
	goto	Finish
	
Out	movf	value1, W
	call    SendByte7S
	
	movf	value2, W
	call    SendByte7S
	
	movf	value3, W
	call    SendByte7S
	return
	
Set_1	movlw	0x01
	movwf	position
	return
	
Set_4	movlw	0x03
	movwf	position
	return
	
Write1	movf	dispR, W
	movwf	value1
	return
	
Write2	movf	dispR, W
	movwf	value2
	return
	
Write3	movf	dispR, W
	movwf	value3
	return
	
    #include	"Config_IOs.inc"
    
    #include	"Display.inc"
		
	END

		
		
		


