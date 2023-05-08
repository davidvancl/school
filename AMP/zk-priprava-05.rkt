;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname zk-priprava-05) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define-struct uzel (otec matka jmeno))

(define jan (make-uzel '() '() "Jan"))
(define petra (make-uzel '() '() "Petra"))
(define karel (make-uzel jan petra "Karel"))
(define ivo (make-uzel jan petra "Ivo"))
(define roman (make-uzel '() '() "Roman"))
(define zuzana (make-uzel '() '() "Zuzana"))
(define tereza (make-uzel roman zuzana "Tereza"))
(define alena (make-uzel ivo tereza "Alena"))
(define ivana (make-uzel ivo tereza "Ivana"))
(define martin (make-uzel ivo tereza "Martin"))

(define (predek? jmeno uz)
  (cond
    [(empty? uz) #false]
    [(string=? jmeno (uzel-jmeno uz)) #true]
    [else (or (predek? jmeno (uzel-matka uz)) (predek? jmeno (uzel-otec uz)))]
  )
)
(predek? "Karel" martin)

(define (vsichni-predci uz)
  (cond
    [(or (empty? uz) (empty? (uzel-matka uz)) (empty? (uzel-otec uz))) '()]
    [else (append (list (uzel-jmeno (uzel-matka uz)) (uzel-jmeno (uzel-otec uz))) (vsichni-predci (uzel-matka uz)) (vsichni-predci (uzel-otec uz)))]
  )
)
(vsichni-predci karel)

