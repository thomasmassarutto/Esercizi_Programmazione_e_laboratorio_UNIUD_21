;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es9) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "teachpack.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "teachpack.ss" "installed-teachpacks")) #f)))
;Parte I
;definisci procedura con valori procedurali che, data una chiave compresa nell’intervallo [0, 19], restituisce
;la corrispondente funzione di crittazione, da lettera maiuscola a lettera maiuscola, per l’alfabeto Latino dell’epoca
;Repubblicana: A B C D E F G H I L M N O P Q R S T V X

;ES: (crittazione "MESSAGGIO" (regola 3)):
;crittazione: funzione principale
;"MESSAGGIO": stringa da crittare
;(regola 3): funzione che implemanta una crittazione a scorrimento di 3 caratteri

;definizione dell'alfabeto che verrà usato
(define alfabetoLatino; lista che comprende i char dell'alfabeto latino; 20 char
  (list #\A #\B #\C #\D #\E #\F #\G #\H #\I #\L #\M #\N #\O #\P #\Q #\R #\S #\T #\V #\X)
  )


; questa funzione ritorna una procedura che a sua volta ritorna un char
; dato un parametro shift definisce un carattere distante shift posizioni dal carattere char in un alfabeto alphabet
;
;verrà usata nella funzione principale in quanto ritorna una funzione con input un alfabeto (list) e un carattere (char)
;non ha senso usarla da sola: vedi (define scorrimento) come prototipo
(define customShiftCipher
  (lambda (shift);parametro: int posizioni da shiftare
    
  ;espressione che ritorna un char
  (lambda (alphabet char); alphabet: lista di char; char: char da crittare
    (let ((charindex (indexOfChar alphabet char)); indice del carattere in chiaro nella lista alfabeto
          )
    (if (<= charindex (- (length alphabet)shift) ); se il carattere precede di var= 3 posizioni l'ultima letter: caso normale
        (list-ref alphabet (+ (- shift 1) charindex)); ritorno il riferimeto alla lista piu shift posizioni; (- shift 1): correttivo
        ;else; se il carattere ricade nelle ultime posizioni (precede di max var posizioni l ultimo carattere)
        (list-ref alphabet (- charindex (- (length alphabet) (- shift 1) ) ))); faccio il giro (indice - (lunghezzaalfabeto-var))
      )
    ))
  )
;...

;dato un char ne ritorna l'indice del char corrispondente all'interno di una lista "alfabeto"
;NB: IL CARATTERE DEVE CORRISPONDERE ESATTAMENTE AD UN ELEMENTO DELLA LISTA!
;in poche parole conta quante volte va in ricorsione
(define indexOfChar; int: indice di posizione della lettera nell'alfabeto
  (lambda (alphabet Char); alphabet: lista alfabeto, 
    (if (char=? Char (car alphabet)); se il carattera è uguale al primo elemento della lista
        1 ;ritorna 1: 
        ;else
        (+ 1 (indexOfChar (cdr alphabet) Char )); in ricorsone incrementativa (+ 1 ogni volta) con la coda dell'alfabeto fino a quando trova una corrsipondenza
        )
    )
  )
;(indexOfChar alfabetoLatino #\A); 1
;(indexOfChar alfabetoLatino #\X); 20


; data una stringa e una regola di crittazioen a scorrimento, critta la stringa
; NB: USA CARATTERI DI alfabetolatino
(define crittazione; stringa
  (lambda (msg shiftCipher); msg: stringa da crittare, shiftCipher: funzione con in entrata un parametro int=x : [0, 19]es:(nome 1); int definisce lo shift
    (cond ((string=? msg ""); quando la stringa del messaggio è vuota 
           ""); ritorna un messaggio vuoto
          ((string=? (substring msg 0 1) " "); se la stringa ha la testa vuota
            (crittazione (substring msg 1 (string-length msg)) shiftCipher)); ignora lo spazio
        ;else
          (else  
           (string-append; critta una lettera e la attacca alla crittazione della successiva in ricorsione
            (string (shiftCipher alfabetoLatino (string-ref msg 0)));ritorna la prima lettera crittata secondo la regola slidingCipher; string-ref->char
            (crittazione (substring msg 1 (string-length msg)) shiftCipher)); in ricorsione la coda del messaggio
           )
          )
    )
  )

(crittazione "ALEA IACTA EST IVLIVS CAESAR DIXIT" (customShiftCipher 3))

;; VARIE PROVE

(define scorrimento; dato un char ti ritorna il su scorrimento; return lambda expression
  (lambda (alphabet char); alphabet: lista di char; char: char da crittare
    (let ((charindex (indexOfChar alphabet char)); indice del carattere nella lista alfabeto
          )
    (if (<= charindex (- (length alphabet)3) ); se il carattere precede di var= 3 posizioni l'ultima letter: caso normale
        (list-ref alphabet (+ (- 3 1) charindex)); aggiungo var-1 posizioni alla lettera da ritornare
        ;else; se il carattere ricade nell insieme [var, ultimo char] (precede di max var posizioni lultimo carattere)
        (list-ref alphabet (- charindex (- (length alphabet) (- 3 1) ) ))); faccio fare il giro allindice sottraendo la lunghezza della lista alfabeto
      )
    )
  )
;(indexOfChar alfabetoLatino #\X); 20
;(scorrimento alfabetoLatino #\A)
;(scorrimento alfabetoLatino #\B)
;(scorrimento alfabetoLatino #\R)
;(scorrimento alfabetoLatino #\S)
;(scorrimento alfabetoLatino #\T)
;(scorrimento alfabetoLatino #\V)
;(scorrimento alfabetoLatino #\X)

;Parte II
"parte2"
(define H
  (lambda (f g);; due funzioni (add, mul pow)
    (lambda (m n)
      (if (= n 0)
          (f m);; caso base
          ;; else
          (g m ((H f g) m (- n 1)))
          )
      )
    )
  )


(define add (H (lambda (x) x)
               (lambda (m prev) (+ 1 prev))))

(define mul (H (lambda (x) 0) add))

(define pow (H (lambda (x) 1) mul))

(add 2 3) ; Output: 5
(add 10 7) ; Output: 17
(add 0 0) ; Output: 0

(mul 4 5) ; Output: 20
(mul 3 6) ; Output: 18
(mul 2 0) ; Output: 0

(pow 2 4) ; Output: 16
(pow 5 3) ; Output: 125
(pow 0 5) ; Output: 0


