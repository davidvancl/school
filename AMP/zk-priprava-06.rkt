;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname zk-priprava-06) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define (najdix x L index)
  (cond
    [(empty? L) 0]
    [(= x (car (reverse L))) index]
    [else (najdix x (reverse (cdr (reverse L))) (+ index 1))]
  )
)

(define (hledej x L) (najdix x L 1))

(hledej 12 '(12 5 8 9 78 52 5 23 35))