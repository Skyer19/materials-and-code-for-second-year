.MODEL medium 
 .STACK ; Stack default size 1024 bytes 
 .DATA ; Data segment (for variables) 
 .CODE ; Run-able code goes in code segment 
.STARTUP ; Handover code from OS call to typer.exe 
nextc: mov ah,8 ; Call int21 with ah=8 returns with 
 int 021h ; al equal to the ASCII character pressed 
 mov dl,al ; dl is assigned value in al, dl=al 
 
  cmp dl,123;
  jnc ex1;
  cmp dl,97;
  jc  ex1;
  sub dl,32;
ex1:
  cmp dl,27 ; compare dl with ASCII ¡®q¡¯= 
  jz ex ; if key pressed was not a ¡®q¡¯ go back
  mov ah,02h ; Call int21 with ah=2 prints ASCII 
  int 021h ; character represented by value in dl
  jmp nextc;
ex:
  
 .EXIT ; Terminate and return control to OS 
 END ; End of file (for compiler)