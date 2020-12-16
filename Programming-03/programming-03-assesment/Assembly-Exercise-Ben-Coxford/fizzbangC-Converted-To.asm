	.file	"fizzbang.c"
# GNU C17 (Ubuntu 8.3.0-6ubuntu1) version 8.3.0 (x86_64-linux-gnu)
#	compiled by GNU C version 8.3.0, GMP version 6.1.2, MPFR version 4.0.2, MPC version 1.1.0, isl version isl-0.20-GMP

# GGC heuristics: --param ggc-min-expand=100 --param ggc-min-heapsize=131072
# options passed:  -imultiarch x86_64-linux-gnu fizzbang.c -mtune=generic
# -march=x86-64 -auxbase-strip fizzbangC.asm -fverbose-asm
# -fstack-protector-strong -Wformat -Wformat-security
# options enabled:  -fPIC -fPIE -faggressive-loop-optimizations
# -fasynchronous-unwind-tables -fauto-inc-dec -fchkp-check-incomplete-type
# -fchkp-check-read -fchkp-check-write -fchkp-instrument-calls
# -fchkp-narrow-bounds -fchkp-optimize -fchkp-store-bounds
# -fchkp-use-static-bounds -fchkp-use-static-const-bounds
# -fchkp-use-wrappers -fcommon -fdelete-null-pointer-checks
# -fdwarf2-cfi-asm -fearly-inlining -feliminate-unused-debug-types
# -ffp-int-builtin-inexact -ffunction-cse -fgcse-lm -fgnu-runtime
# -fgnu-unique -fident -finline-atomics -fira-hoist-pressure
# -fira-share-save-slots -fira-share-spill-slots -fivopts
# -fkeep-static-consts -fleading-underscore -flifetime-dse
# -flto-odr-type-merging -fmath-errno -fmerge-debug-strings -fpeephole
# -fplt -fprefetch-loop-arrays -freg-struct-return
# -fsched-critical-path-heuristic -fsched-dep-count-heuristic
# -fsched-group-heuristic -fsched-interblock -fsched-last-insn-heuristic
# -fsched-rank-heuristic -fsched-spec -fsched-spec-insn-heuristic
# -fsched-stalled-insns-dep -fschedule-fusion -fsemantic-interposition
# -fshow-column -fshrink-wrap-separate -fsigned-zeros
# -fsplit-ivs-in-unroller -fssa-backprop -fstack-protector-strong
# -fstdarg-opt -fstrict-volatile-bitfields -fsync-libcalls -ftrapping-math
# -ftree-cselim -ftree-forwprop -ftree-loop-if-convert -ftree-loop-im
# -ftree-loop-ivcanon -ftree-loop-optimize -ftree-parallelize-loops=
# -ftree-phiprop -ftree-reassoc -ftree-scev-cprop -funit-at-a-time
# -funwind-tables -fverbose-asm -fzero-initialized-in-bss
# -m128bit-long-double -m64 -m80387 -malign-stringops
# -mavx256-split-unaligned-load -mavx256-split-unaligned-store
# -mfancy-math-387 -mfp-ret-in-387 -mfxsr -mglibc -mieee-fp
# -mlong-double-80 -mmmx -mno-sse4 -mpush-args -mred-zone -msse -msse2
# -mstv -mtls-direct-seg-refs -mvzeroupper

	.text
	.section	.rodata
.LC0:
	.string	"FizzBang"
.LC1:
	.string	"Fizz"
.LC2:
	.string	"Bang"
.LC3:
	.string	"%d\n"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	pushq	%rbp	#
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp	#,
	.cfi_def_cfa_register 6
	subq	$16, %rsp	#,
# fizzbang.c:4: 	int bang=0;
	movl	$0, -12(%rbp)	#, bang
# fizzbang.c:5: 	int fizz=0;
	movl	$0, -8(%rbp)	#, fizz
# fizzbang.c:6: 	for (int i=1; i<=20; i++) {
	movl	$1, -4(%rbp)	#, i
# fizzbang.c:6: 	for (int i=1; i<=20; i++) {
	jmp	.L2	#
.L7:
# fizzbang.c:7: 		if(i%15==0) {
	movl	-4(%rbp), %ecx	# i, tmp92
	movl	$-2004318071, %edx	#, tmp94
	movl	%ecx, %eax	# tmp92, tmp115
	imull	%edx	# tmp94
	leal	(%rdx,%rcx), %eax	#, tmp95
	sarl	$3, %eax	#, tmp95
	movl	%eax, %edx	# tmp95, tmp96
	movl	%ecx, %eax	# tmp92, tmp97
	sarl	$31, %eax	#, tmp97
	subl	%eax, %edx	# tmp97, tmp96
	movl	%edx, %eax	# tmp96, _1
	movl	%eax, %edx	# _1, tmp98
	sall	$4, %edx	#, tmp99
	subl	%eax, %edx	# _1, tmp98
	movl	%ecx, %eax	# tmp92, tmp92
	subl	%edx, %eax	# tmp98, tmp92
# fizzbang.c:7: 		if(i%15==0) {
	testl	%eax, %eax	# _1
	jne	.L3	#,
# fizzbang.c:8: 			printf("FizzBang\n");
	leaq	.LC0(%rip), %rdi	#,
	call	puts@PLT	#
# fizzbang.c:9: 			bang++;
	addl	$1, -12(%rbp)	#, bang
# fizzbang.c:10: 			fizz++;
	addl	$1, -8(%rbp)	#, fizz
	jmp	.L4	#
.L3:
# fizzbang.c:12: 		else if(i%3==0) {
	movl	-4(%rbp), %ecx	# i, tmp100
	movl	$1431655766, %edx	#, tmp102
	movl	%ecx, %eax	# tmp100, tmp116
	imull	%edx	# tmp102
	movl	%ecx, %eax	# tmp100, tmp103
	sarl	$31, %eax	#, tmp103
	subl	%eax, %edx	# tmp103, tmp101
	movl	%edx, %eax	# tmp101, _2
	movl	%eax, %edx	# _2, tmp104
	addl	%edx, %edx	# tmp104
	addl	%eax, %edx	# _2, tmp104
	movl	%ecx, %eax	# tmp100, tmp100
	subl	%edx, %eax	# tmp104, tmp100
# fizzbang.c:12: 		else if(i%3==0) {
	testl	%eax, %eax	# _2
	jne	.L5	#,
# fizzbang.c:13: 			printf("Fizz\n");
	leaq	.LC1(%rip), %rdi	#,
	call	puts@PLT	#
# fizzbang.c:14: 			fizz++;
	addl	$1, -8(%rbp)	#, fizz
	jmp	.L4	#
.L5:
# fizzbang.c:16: 		else if(i%5==0) {
	movl	-4(%rbp), %ecx	# i, tmp105
	movl	$1717986919, %edx	#, tmp107
	movl	%ecx, %eax	# tmp105, tmp117
	imull	%edx	# tmp107
	sarl	%edx	# tmp108
	movl	%ecx, %eax	# tmp105, tmp109
	sarl	$31, %eax	#, tmp109
	subl	%eax, %edx	# tmp109, tmp108
	movl	%edx, %eax	# tmp108, _3
	movl	%eax, %edx	# _3, tmp110
	sall	$2, %edx	#, tmp110
	addl	%eax, %edx	# _3, tmp110
	movl	%ecx, %eax	# tmp105, tmp105
	subl	%edx, %eax	# tmp110, tmp105
# fizzbang.c:16: 		else if(i%5==0) {
	testl	%eax, %eax	# _3
	jne	.L6	#,
# fizzbang.c:17: 			printf("Bang\n");
	leaq	.LC2(%rip), %rdi	#,
	call	puts@PLT	#
# fizzbang.c:18: 			bang++;
	addl	$1, -12(%rbp)	#, bang
	jmp	.L4	#
.L6:
# fizzbang.c:21: 			printf("%d\n", i);
	movl	-4(%rbp), %eax	# i, tmp111
	movl	%eax, %esi	# tmp111,
	leaq	.LC3(%rip), %rdi	#,
	movl	$0, %eax	#,
	call	printf@PLT	#
.L4:
# fizzbang.c:6: 	for (int i=1; i<=20; i++) {
	addl	$1, -4(%rbp)	#, i
.L2:
# fizzbang.c:6: 	for (int i=1; i<=20; i++) {
	cmpl	$20, -4(%rbp)	#, i
	jle	.L7	#,
# fizzbang.c:24: 	printf("%d\n", bang);
	movl	-12(%rbp), %eax	# bang, tmp112
	movl	%eax, %esi	# tmp112,
	leaq	.LC3(%rip), %rdi	#,
	movl	$0, %eax	#,
	call	printf@PLT	#
# fizzbang.c:25: 	printf("%d\n", fizz);
	movl	-8(%rbp), %eax	# fizz, tmp113
	movl	%eax, %esi	# tmp113,
	leaq	.LC3(%rip), %rdi	#,
	movl	$0, %eax	#,
	call	printf@PLT	#
# fizzbang.c:26: 	return 0;
	movl	$0, %eax	#, _26
# fizzbang.c:27: }
	leave	
	.cfi_def_cfa 7, 8
	ret	
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 8.3.0-6ubuntu1) 8.3.0"
	.section	.note.GNU-stack,"",@progbits
