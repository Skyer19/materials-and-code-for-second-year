      .MODEL   medium
      .STACK
      .DATA
msgl   BYTE "Hello worlds"
	  .CODE
	  .STARTUP
	  mov bx,OFFSET msgl
back: mov dx,[bx]    ; dl=letters
	  cmp dl,'$'
	  jz done
	  mov ah,02h
	  int 021h
	  inc bx
	  jmp back
done: nop
	  .EXIT
END

