;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname zk-priprava-02) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define (f a b c)
  (/ (sqrt (+ (* a a) (* b b) (* c c))) (+ a b c))
)
(f 2 3 6)
;--------------------------------------------------
(define (obsahuje N L)
  (cond
    [(empty? L) #false]
    [(= N (car L)) #true]
    [else (obsahuje N (cdr L))]
   )
)
(define (odkonce N L)
  (cond
    [(not (obsahuje N L)) 0]
    [else
      (cond
        [(empty? L) 0]
        [(= N (car (reverse L))) 1]
        [else (+ (odkonce N (reverse(cdr (reverse L)))) 1)]
      )
    ]
   )
)
(odkonce 8 '(15 8 0 0 8 15))
;--------------------------------------------------
(define-struct autosalon (nazev znacky))

(define (ma? nazev L)
  (cond
    [(empty? L) #false]
    [(string=? nazev (car L)) #true]
    [else (ma? nazev (cdr L))]
  )
)
(define (najdiznacku znacka L)
  (cond
    [(empty? L) '()]
    [(ma? znacka (autosalon-znacky (car L))) (cons (autosalon-nazev (car L)) (najdiznacku znacka (cdr L)))]
    [else (najdiznacku znacka (cdr L))]
  )
)
(najdiznacku "Škoda"
  (list
   (make-autosalon "Novák-Cars" '("Toyota" "Škoda"))
   (make-autosalon "Auto bílý" '("Ford" "Volvo"))
   (make-autosalon "Vaše auto" '("Škoda" "Honda" "Kia"))
   (make-autosalon "Auto plus" '("Volvo" "Škoda"))
   (make-autosalon "Auto Černý" '("Honda" "Audi"))
  )
)
;--------------------------------------------------