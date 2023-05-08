	.file	"suboptima.c"
	.text
	.section	.text.startup,"ax",@progbits
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	xorl	%eax, %eax
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (GNU) 13.0.1 20230421 (Red Hat 13.0.1-0)"
	.section	.note.GNU-stack,"",@progbits
