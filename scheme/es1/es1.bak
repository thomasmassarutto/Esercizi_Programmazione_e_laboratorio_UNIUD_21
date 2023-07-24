;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es1) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;; dato soggetto, verbo all'infinito, soggetto
;; il programma forma una frase


(define frase ;stringa
  (lambda (sogg verb cogg);stringhe
    ;corpo
    ;aggiunge articolo a soggetto, coniuga il verbo in base al soggetto e aggiunge articolo a oggetto
    (string-append (articolo sogg) " " (coniuga verb sogg) " " (articolo cogg) )
   )
  )

;aggiunge l'articolo al sostantivo
(define articolo ;;return string
  (lambda (sost)
    ;4 condizioni maschile singolare, maschile plurale, femminile singolare, femminile plurale
    (cond((char=? (lastletter sost) #\o);maschile singolare
          (string-append "il " sost))
         ((char=? (lastletter sost) #\i);maschile plurale
          (string-append "i " sost))
         ((char=? (lastletter sost) #\a);femminile singolare
          (string-append "la " sost))
         ((char=? (lastletter sost) #\e);femminile plurale
          (string-append "le " sost))
         (else "err sost")
     )
      )
  )


;coniuga un verbo in base al sostantivo
(define coniuga ;;ritorna string
  (lambda (verb sost)
        (cond((char=? (lastletter sost) #\o);maschile singolare
          (string-append (radice verb) "a")
          )
         ((char=? (lastletter sost) #\i);maschile plurale
          (string-append (radice verb) "ano")
          )
         ((char=? (lastletter sost) #\a);femminile singolare
          (string-append (radice verb) "a")
          )
         ((char=? (lastletter sost) #\e);femminile plurale
          (string-append (radice verb) "ano" )
          )
         (else "err verb")
     )
    )
  )
;;

;controllo ultima lettera di una parola
(define lastletter ;char 
  (lambda (string) 
    (string-ref string (-(string-length string) 1)); char in posizione finale 
    )
  )

;radice verbo: toglie ultime 3 lettere
(define radice ;return string
  (lambda (verbo); verbo all'infinito
    (substring verbo 0 (- (string-length verbo) 3))
    )
  )

;;
(frase "gatto" "mangiare" "topo")
(frase "mucca" "mangiare" "fieno")
(frase "sorelle" "leggere" "novella")
(frase "bambini" "amare" "favole")
(frase "musicisti" "suonare" "pianoforti")
(frase "cuoco" "friggere" "patate")
(frase "camerieri" "servire" "clienti")
(frase "mamma" "chiamare" "figlie") 