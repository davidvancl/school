;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname zk-priprava-03) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define (sudepozice L)
  (cond
    [(empty? L) '()]
    [(empty? (cdr L)) '()]
    [else (cons (car (cdr L)) (sudepozice (cdr (cdr L))))]
  )
)
(sudepozice '(12 45 78 96 32 45 62 75 66))
;----------------------------------------------------------
(define (lichepozice L)
  (cond
    [(empty? L) '()]
    [(empty? (cdr L)) (list (car L))]
    [else (cons (car L) (lichepozice (cdr (cdr L))))]
  )
)
(lichepozice '(12 45 78 96 32 45 62 75 66))
;----------------------------------------------------------
(define (odeberpozici x L)
  (cond
   [(empty? L) '()]
   [(= x 1) (odeberpozici (- x 1) (cdr L))]
   [else (cons (car L) (odeberpozici (- x 1) (cdr L)))]
  )
)
(odeberpozici 3 '(12 45 78 96 32 45 62 75 66))
;----------------------------------------------------------
(define (oznacsuda L)
  (cond
    [(empty? L) '()]
    [(even? (car L)) (cons #true (oznacsuda (cdr L)))]
    [else (cons #false (oznacsuda (cdr L)))]
  )
)
(oznacsuda '(12 45 78 96 32 45 62 75 66))
;----------------------------------------------------------
(define (nejmensi L)
  (cond
    [(empty? L) '()]
    [(empty? (cdr L)) (car L)]
    [else (min (car L) (nejmensi (cdr L)))]
  )
)
(define (odeber x L)
  (cond
    [(empty? L) '()]
    [(= x (car L)) (cdr L)]
    [else (cons (car L) (odeber x (cdr L)))]
  )
)
(define (serad L)
  (cond
    [(empty? L) '()]
    [else (cons (nejmensi L) (serad (odeber (nejmensi L) L)))]
  )
)
(serad '(12 45 78 96 32 5 62 75 66))
(define (spojaserad L1 L2)
  (serad (append L1 L2))
)
(spojaserad '(12 45 78 96 32 5 62 75 66) '(1 2 3 4 5 6 7 8 9))
;----------------------------------------------------------
