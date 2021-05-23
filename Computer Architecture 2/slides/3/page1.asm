.MODEL MEDIUM
.STACK
.DATA
.CODE
.STARTUP

mov dl,'0'
again:
mov ah,02h
int 021h

inc dl

cmp dl,'9'
jnz again
 .EXIT
 END