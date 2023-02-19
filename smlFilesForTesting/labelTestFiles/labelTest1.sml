    mov EAX 0
f1: mov EBX 5
    jnz EAX f1
    add EAX EBX
f1: out EAX