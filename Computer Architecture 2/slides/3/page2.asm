.MODEL MEDIUM
.STACK
.DATA
.CODE
.STARTUP

mov bl, 4
nextline: mov dl, '0'

nextchar: mov ah, 02h
          int 021h

          inc dl
          cmp dl,'4'
          jnz nextchar

          push dx
          mov ah, 02h
          mov dl,10
          int 021h
          mov dl,13
          int 021h
          pop dx

          dec bl
          jnz nextline

 .EXIT
 END