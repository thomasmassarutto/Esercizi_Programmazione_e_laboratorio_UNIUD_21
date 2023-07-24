;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es3.5) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "teachpack.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "teachpack.ss" "installed-teachpacks")) #f)))
; determina il segno di una stringa (+1, -1)
(define segno; int: (+1, -1)
  (lambda (string);
    (if (char=? (string-ref string 0) #\-)
        -1
        ; else
        +1
    ) 
  )
)

;; determina la posizione del punto in una stringa[0,1,2,...]
;; la stringa non deve contenere il segno
(define indicePunto; int
  (lambda (string)
    (cond
      ;; se non ce un punto
      ((string=? string "")
       0)
      ;; se il . si trova in prima posizione
      ((char=? (string-ref string 0) #\.)
       0)
      (else
      (+ 1 (indicePunto (substring string 1)))
      )
    ))
  )

;; converte una stringa come se fosse la parte intera di un numero
;; la stringa nn deve contenere il segno
;; utilizza il metodo della sommatoria elevata alla base: (x_1*2^n)+(x_1*2^n-1)
(define conversioneParteIntera
  (lambda (string base)
    (cond
      ;; se la stringa è vuota
      ((= (string-length string) 0)
       0)

      ;;se ci sono caratteri "normali"
      (else
       (+ (* (- (char->integer (string-ref string 0)) 48)
             (expt base (- (string-length string) 1)))
          (conversioneParteIntera (substring string 1) base)) 
       )
      )
    )
  )

;; converte una stringa come se fosse la parte frazionaria di un numero
;; la stringa nn deve contenere il segno
;; utilizza il metodo della sommatoria elevata alla base: (x_1*2^n)+(x_1*2^n-1)
(define conversioneParteFrazionaria
  (lambda (string base)
    (cond
      
      ;; se la stringa è vuota
      ((= (string-length string) 0)
       0)

      ;; se la stringa comincia con punto
      ((char=? (string-ref string 0) #\.)
       (conversioneParteFrazionaria (substring string 1) base))

      ;;se ci sono caratteri "normali"
      (else
       (+ (* (- (char->integer (string-ref string (- (string-length string) 1))) 48)
             (expt base (-(string-length string))))
          
          (conversioneParteFrazionaria (substring string 0 (- (string-length string) 1) ) base))
       )
      )
    )
  )

;; procedura principale: determina il segno e delega
;; https://www.rapidtables.com/convert/number/binary-to-decimal.html
(define bin-rep->number
  (lambda (binString); stringa binaria "+-01."
      (cond
        ;; se il segno è negativo
        (
         (char=? (string-ref binString 0) #\-)
         (baseConverter -1 (substring binString 1))
         )
        
        ;;se il segno è positivo
        (
         (char=? (string-ref binString 0) #\+)
         (baseConverter +1 (substring binString 1))
         )
        
        ;; se non cè il segno
        (else
         (baseConverter +1 binString)
         )
       )
    )
  )

;; converte una stringa nella base
;; la stringa non deve avere il segno
;; (segno * (inte + fraz))
(define baseConverter
  (lambda (segno string); int[+1, -1], string
    (let (
          (parteIntera (substring string 0 (indicePunto string)) ); trova solo la parte intera (1010)
          (parteFrazionaria (substring string (indicePunto string)) ); trova solo la parte frazionaria (.1010)
          )
      
      (* segno; segno
         (+ (conversioneParteIntera parteIntera 2); parte intera
            (conversioneParteFrazionaria parteFrazionaria 2)); parte frazionaria
         )
      )
    )
  )

(bin-rep->number "+1101") ;→ 13
(bin-rep->number "0") ;→ 0
(bin-rep->number "10110.011") ;→ 22.375
(bin-rep->number "-0.1101001") ;→ -0.8203125


