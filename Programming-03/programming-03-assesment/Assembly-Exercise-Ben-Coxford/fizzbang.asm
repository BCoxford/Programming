global _start ; Tell kernel where program executes.

section .text ; Section for storing code.

_start: ; Tells linker (ld) the entry point.
xor rcx, 1 ; Resets value of rcx to 0
mov r14, 0 ; Sets register r14 to 0
mov r15, 0 ; Sets register r15 to 0
mov r13, 0 ; Sets register r13 to 0
mov r12, 0 ; Sets register r12 to 0

loopMain: ; Divide by 15 (FizzBang)
push rcx ; Writes rcx to the stack.
xor rdx, rdx ; Resets the value of rdx to 0
mov rax, rcx ; Moves contents of rcx to register rax (dividend)
mov rbx, 15 ; Moves value 15 to contents of rbx (divisor)
div rbx ; Divides the contents of rax by rbx.
cmp rdx, 0 ; Compares if result is zero
jz loop35 ; Jump if equal or zero to loop35

; Divide by 5 (Bang)
xor rdx, rdx ; Resets the value of rdx to 0
mov rax, rcx ; Moves contents of rcx to register rax (dividend)
mov rbx, 5 ; Moves value 5 to contents of rbx (divisor)
div rbx ; Divides the contents of rax by rbx.
cmp rdx, 0 ; Compares if result is zero
jz loop5 ; Jump if equal or zero to loop5

; Divide by 3 (Fizz)
xor rdx, rdx ; Resets the value of rdx to 0
mov rax, rcx ; Moves contents of rcx to register rax (dividend)
mov rbx, 3 ; Moves value 5 to contents of rbx (divisor)
div rbx ; Divides the contents of rax by rbx.
cmp rdx, 0 ; Compares if result is zero
jz loop3 ; Jump if equal or zero to loop3

jmp loopNum ; If no condition is met, jump to loopNum and print number.

increment:
pop rcx ; Restore the contents of the rcx from the stack
inc rcx ; Increment the rcx by 1
cmp rcx, 21 ; Compare if equal to 21
jne loopMain ; Jump if not equal to or zero.

jmp printFizzNumber ; If equal to 21, print the number of fizz and bangs.

printFizzNumber:
mov r13, r15 ; Move contents r15 to register r13 for outputting
jmp loopPrintFinal ; Jump to printFinal to print the number of fizzbangs.

printBangNumber:
mov r13, r14 ; Move contents r14 to register r13 for outputting
mov r12, 1 ; Set r12 flag to 1
jmp loopPrintFinal ; Jump to printFinal to print the number of fizzbangs.

loopPrintFinal:
    push rax ; Push contents rax to stack
    push rcx ; Push contents rcx to stack
    mov rax, r13 ; Move value to be printed r13 to rax.

    mov rdi, 1 ; Sets accumulator to 1 (system call number)
    mov rdx, 1 ; Sets base register to 1 (where to write to)
    mov rcx, 64
	; Each 4 bits should be output as one hexadecimal digit
	; Use shift and bitwise AND to isolate them
	; the result is the offset in 'codes' array
.loop:
    push rax ; Push contents rax to stack.
    sub rcx, 4 ; Subtract 4 from rcx to get next hex value.
	; cl is a register, smallest part of rcx
	; rax -- eax -- ax -- ah + al
	; rcx -- ecx -- cx -- ch + cl
    sar rax, cl
    and rax, 0xf

    lea rsi, [codes + rax] ; Uses relative addressing to get hex value
    mov rax, 1 ; Resets rax to 1

    ; syscall leaves rcx and r11 changed
    push rcx
    syscall ; Invokes system call
    pop rcx

    pop rax ; Retrieves the rax value from stack.
    test rcx, rcx
    jnz .loop

; Prints a new line
mov rax, 1
mov rdi, 1
mov rsi, newline 
mov rdx, 1
syscall

cmp r12, 1 ; If flag is 1
jne printBangNumber ; Print bang number

jmp finish ; Jump to finish

finish: ; On finish, jump to exit.
jmp exit

loopNum:
    push rax ; Push contents rax to stack
    push rcx ; Push contents rcx to stack
    mov rax, rcx ; Move value to be printed rcx to rax.

    mov rdi, 1 ; Sets accumulator to 1 (system call number)
    mov rdx, 1 ; Sets base register to 1 (where to write to)
    mov rcx, 64
	; Each 4 bits should be output as one hexadecimal digit
	; Use shift and bitwise AND to isolate them
	; the result is the offset in 'codes' array
.loop:
    push rax ; Push contents rax to stack.
    sub rcx, 4 ; Subtract 4 from rcx to get next hex value.
	; cl is a register, smallest part of rcx
	; rax -- eax -- ax -- ah + al
	; rcx -- ecx -- cx -- ch + cl
    sar rax, cl
    and rax, 0xf

    lea rsi, [codes + rax] ; Uses relative addressing to get hex value
    mov rax, 1 ; Resets rax to 1

    ; syscall leaves rcx and r11 changed
    push rcx
    syscall
    pop rcx

    pop rax
    test rcx, rcx
    jnz .loop

; Prints new line
mov rax, 1
mov rdi, 1
mov rsi, newline
mov rdx, 1
syscall

jmp increment ; Jumps to increment to increment rcx

loop35: ; Divisible by 3 and 5
inc r14 ; Increment r14 by one (fizz counter)
inc r15 ; Increment r15 by one (buzz counter)
mov rax, 1 ; Sets accumulator to 1 (system call number)
mov rbx, 1 ; Sets base register to 1 (where to write to)
mov rsi, fizzbang ; String starts
mov rdx, 9 ; String length in bytes
syscall ; Invokes system call
jmp increment ; Jump to increment rcx and continue to next number.

loop3: ; Divisible by 3
inc r14 ; Increment r14 by one (fizz counter)
mov rax, 1 ; Sets accumulator to 1 (system call number)
mov rbx, 1 ; Sets base register to 1 (where to write to)
mov rsi, fizz ; String starts
mov rdx, 5 ; String length in bytes
syscall ; Invokes system call
jmp increment ; Jump to increment rcx and continue to next number.

loop5: ; Divisible by 3
inc r15 ; Increment r15 by one (bang counter)
mov rax, 1 ; Sets accumulator to 1 (system call number)
mov rbx, 1 ; Sets base register to 1 (where to write to)
mov rsi, bang ; String starts
mov rdx, 5 ; String length in bytes
syscall ; Invokes system call
jmp increment ; Jump to increment rcx and continue to next number.

exit: ; Exit program
mov eax, 60 ; Interrupt code
mov ebx, 0 ; Return value
xor rdi, rdi ; Reset register
syscall ; Invoke system call

section .data ; Data section declaring initialised data.
fizz db "Fizz", 10 ; Stores string Fizz
bang db "Bang", 10 ; Stores string Bang
fizzbang db "Fizzbang", 10 ; Stores string Fizzbang 
codes:
    db      '0123456789ABCDEF' ; Stores codes for decimal to hex
newline db 10 ; Stores string for a new line
