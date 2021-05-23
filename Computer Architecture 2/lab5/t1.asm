; Test code to redirect Timer Interrupt to our
; own Service routine, old service routine is
; run after ours.  The program terminates and
; stay resident.  The demo service routine is
; a four digit counter.

; By C.Markham
; March 2016
; Requires Win95/DOS (not NT), 8086+


        .MODEL	  small
        .STACK
        .DATA			; DS will may change when TSR operates
        .CODE

        .STARTUP
        jmp ov1

dseg    db      '0','0','0','0' ; Store variables in code segment (TSR)

;The actual location of these variables from part of a long jump
;to the existing service routine.  
;vtabip dw	 0		; Vector table,	IP
;vtabcs dw	 0		; Vector table,	CS

ov1:    push    es		; Save existing	value of ES.
        mov     ax,0000h        ; Set ES (extra segment) to page 0 
        mov     es,ax		; Note page 0 is vector	address table
        mov     bx,020h	        ; Vector address for timer INT 8, IRQ0
        mov     ax,es:[bx]      ; ax=[0000:0020], CS to jump to
        mov     vtabip,ax       ; Store the CS, 1st two bytes of table
        inc     bx
        inc     bx
        mov     ax,es:[bx]      ; ax=[0000:0020], IP to jump to
        mov     vtabcs,ax       ; Store the IP, 2nd two bytes
        pop     es		; Restore the value of es   

        push    es		; Change vector	address to ISR routine
        mov     ax,0000h        ; Vector address table segment
        mov     es,ax
        mov     bx,020h	    
        mov     ax,OFFSET isr   ; Set IP of vector table to new ISR
        mov     es:[bx],ax	        
        inc     bx
        inc     bx
        mov     ax,cs		; The ISR is in	the same segment as the program.
        mov     es:[bx],ax      ; New CS in vector table is the code segment of
        pop     es		; this program. 


;*********************


; TSR Code keeps program memory resident
        mov     ah,031h	        ; TSR Entry code ah=31h,al=0h
        mov     al,00h
        mov     dx,1024	        ; DX=no of bytes to remain resident
        int     021h		; Terminate and	stay resident.
        jmp     quit		; Historic code, never reached


; The New Interrupt Service Routine

isr:    push    ax		; ISR is called	18.2 times per second (Apx 55mS). 
        push    bx		; Interrupts don't store general registers, you	must.
        push    cx
        push    dx
        push    di
        push    si
        push    bp
        push    es
        push    ds


;+++++++++++++++++++++++++++++++
; Core code of the service routine (what you want it to do each
; time the timer interrupt is called)

; Four digit counter, advance +1 on each call of the routine

        mov     cx,4 
        mov     bx,OFFSET dseg
back:   mov     al,cs:[bx]      ; Load al with character code in [dseg]
        inc     al		; Increase al by 1, next character.
        mov     cs:[bx],al      ; Store the increased value
        cmp     al,':'		; If al	equals character after 9
        jnz     skip		; If it	is not 10 do nothing
        mov     al,'0'		; Otherwise reset character to'0'
        mov     cs:[bx],al      ;
        inc     bx		; Move onto next digit
        loop    back		; Repeat on the	successive digits

skip:   mov     ax,0b800h       ; Base address of screen memory
        mov     es,ax
        mov     bx,OFFSET dseg  ; First digit
        mov     dx,140		; Screen position (10,40)=2*(10*80+40)
        mov     cx,4
   

nextc:  mov     al,cs:[bx]      ; Read character from our store  

        mov     di,bx		; Exchange dx,bx
        mov     bx,dx
        mov     dx,di

        mov     es:[bx],al      ; Character changed on screen to ASCII al

        mov     di,bx		; Exchange dx,bx
        mov     bx,dx
        mov     dx,di

        inc     bx
        dec     dx
        dec     dx  
        loop    nextc
;++++++++++++++++++++++++++++++


ovr:    mov     dx,020h	        ; Non Specific end of interrupt command
        mov     al,020h	        ; to sent 8259 #1 controller.
        out     dx,al
        pop     ds		; Restore all registers	previously save on
        pop     es		; the stack.
        pop     bp
        pop     si
        pop     di
        pop     dx
        pop     cx
        pop     bx
        pop     ax

        db      0EAh		; jump far to the existing Timer routine
vtabip  dw 0			; IP offset
vtabcs  dw 0			; CS segment

quit:   .EXIT			; In TSR mode this is not reached

END