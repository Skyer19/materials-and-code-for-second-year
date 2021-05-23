;/* Direct write to screen memory             */
;/* Attribute byte changed to set colour      */
;/* Flash,RGB(Background), B(Bright)RGB(Text) */
;/* Starts printing at location x=38,y=10     */

; By  C.Markham
; Revised March 2016

	.MODEL    small
	.STACK
	.DATA			; DS will may change when TSR operates
	.CODE

	.STARTUP

	mov	ax,0b800h 		 ; Base address of screen memory
	mov	es,ax            ; Use extra segment register to access 0x800...
    mov     bx,0         ; Screen position x=0, y=0, (2*((80*y)+x))=0

	mov     al,'C'          ; Print 'Hello' on the screen
    mov     es:[bx],al     
    inc     bx
    mov     al,12           ; Bright red on a black background
    mov     es:[bx],al

    inc     bx              ; Black on a red background
    mov     al,'M'
    mov	es:[bx],al
    inc     bx
    mov     al,64
    mov     es:[bx],al
quit:   .EXIT

END