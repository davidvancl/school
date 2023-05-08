;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname zk) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define (f a b)
  (+ (/ (+ a b) a) (/ (+ a b) b) (/ (* a b) (+ a b)))
)

(f 2 3)

(define (kolikrat N L)
  (cond
    [(empty? L) 0]
    [(= N (car L)) (+ 1 (kolikrat N (cdr L)))]
    [else (kolikrat N (cdr L))]
  )
)

(kolikrat 8 '(10 8 15 8 22 9 8))

(define-struct bod (x y))

(define (delka-cary L)
  (cond
    [(empty? (cdr L)) 0]
    [else (+ (sqrt (+ (* (- (bod-x (car (cdr L))) (bod-x (car L))) (- (bod-x (car (cdr L))) (bod-x (car L)))) (* (- (bod-y (car (cdr L))) (bod-y (car L))) (- (bod-y (car (cdr L))) (bod-y (car L)))))) (delka-cary (cdr L)))]
  )
)

(define (nejdelsi L)
  (cond
    [(empty? L) 0]
    [else (max (delka-cary (car L)) (nejdelsi (cdr L)))]
  )
)

(nejdelsi
 (list
  (list (make-bod 1 1) (make-bod 4 5) (make-bod 8 8 ))
  (list (make-bod 1 -1) (make-bod 10 11))
  (list (make-bod 5 1) (make-bod 8 3) (make-bod 9 7))
  (list (make-bod 0 2) (make-bod 3 5) (make-bod 4 7))
 )
)