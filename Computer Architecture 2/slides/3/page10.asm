.STARTUP

nextc: mov ah, 8
int 021h
mov dl,al

mov ah,02h
int o21h

cmp dl,'q'
jnz nextc

.EXIT
