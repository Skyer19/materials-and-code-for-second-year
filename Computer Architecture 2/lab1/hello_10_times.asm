      .MODEL   medium
      .STACK
      .DATA
msgl   BYTE "Hello world",13,10,"$"
      .CODE
      .STARTUP
       mov cx,10  ; circle times
lable:
      mov bx,OFFSET msgl
back: mov dx,[bx]    ; dl=letters
      cmp dl,'$'
      jz done
      mov ah,02h
      int 021h
      inc bx
      jmp back
done: nop
 loop lable
      .EXIT
END

