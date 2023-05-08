	.file	"suboptima.c"
	.text
	.section	.text.startup,"ax",@progbits
	.p2align 4
	.globl	main
	.type	main, @function
main:
.LFB11:
	.cfi_startproc
	xorl	%eax, %eax
	ret
	.cfi_endproc
.LFE11:
	.size	main, .-main
	.ident	"GCC: (GNU) 13.0.1 20230421 (Red Hat 13.0.1-0)"
	.section	.note.GNU-stack,"",@progbits
