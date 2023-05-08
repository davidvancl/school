;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname zk-priprava-04) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define-struct zbozi (nazev cena kusy))

(define sklad (list
  (make-zbozi "iPhone 14 Plus 128GB černá" 24990 5)
  (make-zbozi "Niceboy X-fit Watch 2" 1490 2)
  (make-zbozi "Apple AirPods Pro 2022" 6690 5)
))

(define (je-na-skladu? nazev L)
  (cond
    [(empty? L) #false]
    [(string=? nazev (zbozi-nazev (car L))) #true]
    [else (je-na-skladu? nazev (cdr L))]
  )
)

(define (odeber-zbozi nazev kusu L)
  (cond
    [(empty? L) '()]
    [(and (string=? nazev (zbozi-nazev (car L))) (> kusu (zbozi-kusy (car L)))) (error 'odeber-zbozi "Na skladu není dostatek kusů.")]
    [(and (string=? nazev (zbozi-nazev (car L))) (= kusu (zbozi-kusy (car L)))) (cdr L)]
    [(and (string=? nazev (zbozi-nazev (car L))) (< kusu (zbozi-kusy (car L)))) (cons (make-zbozi (zbozi-nazev (car L)) (zbozi-cena (car L)) (- (zbozi-kusy (car L)) kusu)) (cdr L))]
    [else (cons (car L) (odeber-zbozi nazev kusu (cdr L)))]
  )
)

(define (najdi-cenu nazev L)
  (cond
    [(empty? L) #false]
    [(string=? nazev (zbozi-nazev (car L))) (zbozi-cena (car L))]
    [else (najdi-cenu nazev (cdr L))]
  )
)

(define (celkova-cena seznam)
  (cond
    [(empty? seznam) 0]
    [else (+ (* (najdi-cenu (car (car seznam)) sklad) (car (cdr (car seznam)))) (celkova-cena (cdr seznam)))]
  )
)

;(celkova-cena '(("iPhone 14 Plus 128GB černá" 1) ("Niceboy X-fit Watch 2" 2)))

(define (proved-objednavku seznam sklad)
  (cond
    [(empty? seznam) sklad]
    [(je-na-skladu? (car (car seznam)) sklad) (proved-objednavku (cdr seznam) (odeber-zbozi (car (car seznam)) (car (cdr (car seznam))) sklad))]
    [else  (error 'proved-objednavku "Na skladu není požadované zboží.")]
  )
)

(define (vystup-objednavka objednavka)
  (list (celkova-cena objednavka) (proved-objednavku objednavka sklad))
)

(define objednavka '(("iPhone 14 Plus 128GB černá" 2) ("Niceboy X-fit Watch 2" 2)))
(vystup-objednavka objednavka)