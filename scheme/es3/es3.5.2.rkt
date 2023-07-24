;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es3.5.2) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "teachpack.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "teachpack.ss" "installed-teachpacks")) #f)))
;;;; PARTE II

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

(define chr-dec
  (lambda(chr seq)                          ; chr (carattere), seq (sequenza di ricerca) -> dopo quanto comapre quel carattere per la 1a volta
    (chr-dec-rec chr seq (string-length seq))
    )
  )

(define chr-dec-rec
  (lambda ( chr seq sql)                             ; chr (carattere), seq (sequenza di ricerca), sql (lunghezza sequenza)
    (if (string=? seq "")                            ; se la sequenza è vuota
        0                                          
        (if (char=? chr (string-ref seq 0))          ; se il carattere è uguale al primo simbolo della sequenza
            (- sql (string-length seq))              ; lungehzza sequenza (all'inizio) - lunghezza sequenza (attuale)
            (chr-dec-rec chr (substring seq 1) sql)) ; ricorsione togliendo il primo char dalla sequenza
      )
    )
  )

(define int-dec
  (lambda(s seq)       ; s (stringa parte intera), seq (sequenza di riferimento) -> converte la parte intera in decimale
    (if (string=? s "")
        0
        (+ (* (expt (string-length seq) (- (string-length s) 1))
              (chr-dec (string-ref s 0) seq))
           (int-dec (substring s 1) seq))
      )
    )
  )

(define rep-int
  (lambda ( s)
    (rep-int-rec s s)
    )
  )

(define rep-int-rec
  (lambda (s int)                                                        ; s (stringa originale) int (stringa con solo parte intera) -> "101.1" → "101"
    (if (string=? int "")                                                ; se la stringa con la parte intera è vuota
        s                                                                ; restituisce la stringa originale
        (if (char=? (string-ref int (- (string-length int) 1)) #\.)      ; se il carattere in ultima posizione di int == .
            (substring int 0 (- (string-length int) 1))                  ; restituisce la stringa int dalla prima alla penultima cifra
            (rep-int-rec s (substring int 0 (- (string-length int) 1)))) ; ricorsione con s e int dalla prima alla penultima cifra
      )
    )
  )

(define rat-dec
  (lambda ( s seq)         ; s (stringa parte non intera), seq (sequenza di riferimento)
    (rat-dec-rec (list->string (reverse (string->list s))) seq)
    )
  )

(define rat-dec-rec
  (lambda (s seq)     ; s (stringa parte non intera), seq (sequenza di riferimento) -> converte la parte non intera in decimale
    (if (string=? s "")
        0
        (+ (* (expt (string-length seq) (- (string-length s)))
              (chr-dec (string-ref s 0) seq))
           (rat-dec-rec (substring s 1) seq))
      )
    )
  )

(define rep-rat
  (lambda( s)                                            ; stringa di 1/0 -> "101.1" → "1"
    (if (string=? s "")                                  ; se la stringa con la parte intera è vuota
        ""                                               ; restituisce la stringa originale
        (if (char=? (string-ref s 0) #\.)                ; se il primo carattere è un punto
            (substring s 1 (string-length s))            ; restituisce tutto tranne la prima cifra
            (rep-rat (substring s 1 (string-length s)))) ; ricorsione con s senza la prima cifra            
      )
    )
  )

(define rep->number; conversione si una stringa in base alla seq data
  (lambda (seq s);; string, string
    (* (segno s)                   
       (+ (int-dec (rep-int s) seq)   ; parte intera
          (rat-dec (rep-rat s) seq))  ; parte non intera
       )
    )
  )

(rep->number "zu" "-uuzz") ;→ -12
(rep->number "0123" "+21.1") ;→ 9.25
(rep->number "01234" "-10.02") ;→ -5.08
(rep->number "0123456789ABCDEF" "0.A") ;→ 0.625
(rep->number "0123456789ABCDEF" "1CF.0") ;→ 463

;; --------- ;;

;;  NON UTILIZZATE
;; converte in decimale la parte intera data sequenza
(define pdec2
  (lambda (int-dec s seq); s (stringa parte intera), seq (sequenza di riferimento) -> converte la parte intera in decimale
  (if (string=? s "")
      0
      (+ (* (expt (string-length seq) (- (string-length s) 1))
            (chr-dec (string-ref s 0) seq))
         (int-dec (substring s 1) seq))
      )))