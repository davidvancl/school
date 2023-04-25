;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname struktury) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define-struct zbozi (nazev cena kusu))

(define sklad (list
 (make-zbozi "Samsung Galaxy A14 4GB/128GB černá" 11999 8)
 (make-zbozi "iPhone 13 128GB bílá" 19490 4)
 (make-zbozi "iPhone 14 Pro Max 256GB černá" 36490 1)
 (make-zbozi "Savo" 5 10)
))

(define (najdi-cenu jmeno seznam)
  (cond
    [(empty? seznam) 0]
    [(string=? jmeno (zbozi-nazev (car seznam))) (zbozi-cena (car seznam))]
    [else (najdi-cenu jmeno (cdr seznam))]
    ))

(define (celkova-cena seznam)
  (cond
  [(empty? seznam) 0]
  [else (+ (* (zbozi-cena (car seznam)) (zbozi-kusu (car seznam))) (celkova-cena (cdr seznam))) ]
  ))

(define (cena-objednavky objednavka sklad)
  (if (empty? objednavka) 0
      (+ (cena-polozky (car objednavka) sklad)
         (cena-objednavky (cdr objednavka) sklad))))

(define (cena-polozky polozka seznam)
  (* (najdi-cenu (car polozka) seznam) (second polozka)))


(define (vydej seznam nazev kusu)
  (cond
    [(empty? seznam) '()]
    [(string=? nazev (zbozi-nazev (car seznam))) (odeber-kusy seznam kusu)]
    [else (cons (car seznam)
                (vydej (cdr seznam) nazev kusu))]
    )
  )

(define (odeber-kusy seznam kusy)
  (if (>= kusy (zbozi-kusu (car seznam))) (cdr seznam)
      (cons (make-zbozi (zbozi-nazev (car seznam))
                        (zbozi-cena (car seznam))
                        (- (zbozi-kusu (car seznam)) kusy))
                    (cdr seznam))))

(vydej sklad "Savo" 2)