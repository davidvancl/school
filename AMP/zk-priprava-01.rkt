;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname zk-priprava-01) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define (funkce a b)
  (/ (+ (/ a b) (/ b a)) (* a b)))

(funkce 1 2)


(define (vyber n L)
  (cond
    [(empty? L) (list)]
    [(= 1 n) (car L)]
    [else (vyber (- n 1) (cdr L))]
    ))

(vyber 6 '(10 20 30 40 50))


(define-struct oddeleni (nazev zamestnanci))
(define podnik (list
  (make-oddeleni "Prodej" '("Anna Bílá" "Lída Žlutá" "Petr Modrý"))
  (make-oddeleni "Vývoj" '("Josef Zelený" "Lenka Růžová" "Alena Černá"))
))

(define (hledej-zamestnance nazev L)
  (cond
    [(empty? L) #false]
    [(string=? nazev (car L)) #true]
    [else (hledej-zamestnance nazev (cdr L))]
  )
)

(define (hledej-oddeleni nazev podnik)
  (cond
    [(empty? podnik) (list)]
    [(boolean=? (hledej-zamestnance nazev (oddeleni-zamestnanci (car podnik))) #true) (oddeleni-nazev (car podnik))]
    [else (hledej-oddeleni nazev (cdr podnik))]
  )
 )

(hledej-oddeleni "Petr Modrý" podnik)
(hledej-oddeleni "Lenka Zelená" podnik)
(hledej-oddeleni "Lenka Růžová" podnik)