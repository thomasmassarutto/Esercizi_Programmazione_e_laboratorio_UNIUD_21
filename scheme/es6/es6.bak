;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es6) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))

; crea un pattern di tiles a partire da un tile base
; per capire il funzionamento testa con:
; (piastrellista L-tile 0.5 1) -> ritorna lo stesso risultato della funzione "two"
; funziona grazie alle proporzioni dei pezzi a L "L-tiles"
(define piastrellista; tile
  ; tile di base
  ; q: coefficenti seconda riga (1/4 della misurao riginale)
  ; h: coefficenti prima/terza riga (1/2 della misuraoriginale);
  (lambda (tile q h)
    ;genearazione pattern:
  (glue-tiles;incollo prima riga
   (glue-tiles;incollo prime 2 righe
    (glue-tiles;incollo tutto per avere un nuovo oggetto tile
     ;genero riga per riga come un funzione two, ma con dei parametri per mantenere le proporzioni
     tile (shift-right(quarter-turn-right tile )h)); prima riga
    (shift-right (shift-down tile q) q));seconda riga
   (shift-down (quarter-turn-left tile) h)); terza riga
    )
  )

; dato un intero (potenza di due) e un tile "multiplo" di L-tile (in teachpack)
; genera una superficie dalle corrette dimensioni
; int deve essere potenza di due altrimenti non incontre la condizione necessaria per caso base
(define myTassellation;tile
(lambda (int tile); intero potenza 2, tile
  (if (= int 2);caso base int= 2
      ; creo un pattern con L-tile base affidato alla procedura piastrellista,
      ; in pratica esegue la funzione two
      (piastrellista L-tile (* int 0.25) (* int 0.5))  
      ;else: procedura ricorsiva:
      ; affido a piastrellista:
      ; tile generato dalla myTassellation di una potenza inferiore di 2 (int / 2)
      ; e i coefficienti scalano secondo le regole classiche: 1/4 per la rigacentrale, 1/2  per la prima e la terza
      (piastrellista (myTassellation (* int 0.5) tile) (* int 0.25) (* int 0.5))
      )
  )
  )
;(myTassellation 4 L-tile)


;funzione principale
;data la lunghezza del lato più corto della regione da coprire
;(potenza di due) restituisce l’ “immagine” della regione tassellata.
; n deve essere potenza di due altrimenti non incontre la condizione necessaria per caso base in funzione myTassellation
(define l-tassellation; tassellation: regione coperta dalle piastrelle
  (lambda (n); int potenza di 2
    (cond ((= n 1); caso base: genero singola piastrella
           (L-tile)); stampa il blocco singolo
        ;else: delega a myTassellation tutti gli altri casi in cui n!=2, le potenze di 2 verranno risolte da myTassellation
        (else (myTassellation n L-tile))
     )
    )
  )


(l-tassellation 8)

