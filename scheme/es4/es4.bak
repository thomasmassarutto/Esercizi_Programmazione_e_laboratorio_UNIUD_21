;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es4) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;data una rappresentazione BTR (stringa), restituisce la cifra meno significativa
;oppure zero (#\.) se l’argomento è la stringa vuota. NB ritorna Char
(define lsd; char
  (lambda (string); stringa BTR
    (cond ((= (string-length string) 0) #\. ); prima di tutto controlla che non sia stringa vuota
          ;poi controlla che non sia stringa composta da uno spazio in testa
          ((string=? (substring string 0 1) " ") (lsd (substring string 1 (string-length string))) );
          ;poi controlla sottostringa composta da ultimo carattere e ritorna il char corrispondente
          ((string=? (substring string (-(string-length string)1 ) (string-length string)) "+") #\+ )
          ((string=? (substring string (-(string-length string)1 ) (string-length string)) "-") #\- )
          ((string=? (substring string (-(string-length string)1 ) (string-length string)) ".") #\. )
          ;ritorna errore
          (else "err non BTR string (+-.)")
     )
   )
  )
;(lsd "     ")


;data una rappresentazione BTR (stringa), restituisce la parte che precede l’ultima
;cifra (stringa) oppure la stringa vuota ("") se l’argomento è la stringa vuota. NB Ritorna string
(define head; string
  (lambda (string);stringa
    (cond ( (= (string-length string) 0) " " );se la stringa ha lunghezza 0, ritorna la stringa composta da uno spazio
          ; se ha uno spazio in posizione meno significativa (dx) lo toglie e va in ricorsione finchè non trova un qualcosa che non sia spazio 
          ((string=? (substring string (- (string-length string) 1) (string-length string)) " ") (head (substring string 0 (- (string-length string) 1))) )
          (else (substring string 0 (- (string-length string) 1)))
     )
      )
  )
;(head "      ")


;data una rappresentazione BTR (stringa), restituisce la rappresentazione non vuota
;equivalente in cui le eventuali cifre zero "#\." in testa, ininfluenti, sono rimosse
(define normalized-btr; string
  (lambda (string);string
    (cond ((= (string-length string) 0);se la stringa ha lunghezza 0, ritorna zero in strings
           "." )
          ((string=? (substring string 0 1) ".");se la prima cifra è ".", taglia il primo carattere e va in ricorsione con la coda:
          (normalized-btr (substring string 1 (string-length string)))); continua a togliere il primo carattere fino a lunghezza 0: primo caso
          (else string);ritorna la stringa quando non ci sono "." in testa
     )
    )
 )
;(normalized-btr ".+++")


;dati 2 char ne calcola la somma ESCLUSO il riporto
;in un Sistema numerico ternario bilanciato
(define twoDigitSum; char
  (lambda (Cbtr1 Cbtr2);char
    (cond ( (and (char=? Cbtr1 #\+) (char=? Cbtr2 #\+)); quando sono entrambi +
            #\-)
          ( (and (char=? Cbtr1 #\-) (char=? Cbtr2 #\-)); quando sono entrambi -
            #\+)
          ( ( or (and (char=? Cbtr1 #\+) (char=? Cbtr2 #\-)) (and (char=? Cbtr1 #\-) (char=? Cbtr2 #\+))); quando sono + - OPPURE - +
             #\.)
          ((char=? Cbtr1 #\.); quando Cbtr1 è . ritorna Cbtr2
           Cbtr2)
          ((char=? Cbtr2 #\.); quando Cbtr2 è . ritorna Cbtr1
           Cbtr1)
            (else "err no btr char")
      )
    )
  )
;(twoDigitSum #\- #\-)

;date due cifre BTR “incolonnate” e il relativo riporto BTR in entrata (caratteri),
;restituisce la cifra BTR corrispondente (carattere) della rappresentazione della somma
;DIPENDE DA : twoDigitSum
(define btr-digit-sum;char
  (lambda (Cbtr1 Cbtr2 Crip); char
    (twoDigitSum (twoDigitSum Cbtr1 Cbtr2) Crip)
  )
)

;(btr-digit-sum #\+ #\. #\-)


;dati 2 char ne calcola il riporto ESCLUSO la somma
;in un Sistema numerico ternario bilanciato
(define twoDigitCarry; char
  (lambda (Cbtr1 Cbtr2);char
    (cond ( (and (char=? Cbtr1 #\+) (char=? Cbtr2 #\+)); quando sono entrambi +
            #\+)
          ( (and (char=? Cbtr1 #\-) (char=? Cbtr2 #\-)); quando sono entrambi -
            #\-)
          ( ( or (and (char=? Cbtr1 #\+) (char=? Cbtr2 #\-)) (and (char=? Cbtr1 #\-) (char=? Cbtr2 #\+))); quando sono + - OPPURE - +
             #\.)
          ((or (char=? Cbtr1 #\.) (char=? Cbtr2 #\.)); quando una cifra è . ritorna .
             #\.
                )
            (else "err no btr char")
      )
    )
  )
;(twoDigitCarry #\. #\+)


;date due cifre BTR “incolonnate” e il relativo riporto BTR in entrata (caratteri),
;restituisce il riporto BTR in uscita (carattere) conseguente alla somma delle cifre
;DIPENDE DA : twoDigitCarry
(define btr-digit-carry; char
  (lambda (Cbtr1 Cbtr2 Crip); char
    ;prima fa il carry delle prime 2 cifre, poi le somma e trova il carry fra la somma e il riporto in entrata.
    ;Alla fine somma i 2 carry 
    (twoDigitSum (twoDigitCarry Cbtr1 Cbtr2) (twoDigitCarry (twoDigitSum Cbtr1 Cbtr2) Crip) )
    )
  )


;date le rappresentazioni BTR di due interi (stringhe) e il riporto in entrata (carattere),
;restituisce la rappresentazione BTR della somma inclusiva del riporto
(define btr-carry-sum
  (lambda (Sbtr1 Sbtr2 Crip); String, String, Char
    (if (and (= (string-length Sbtr1) 1) (= (string-length Sbtr2) 1)); caso basico: le stringhe btr hanno lunghezza 1
        (string ; creo una stringa composta da
         (btr-digit-carry (lsd Sbtr1) (lsd Sbtr2) Crip); il riporto in posizione piu significativa nella stringa
         (btr-digit-sum (lsd Sbtr1) (lsd Sbtr2) Crip); la somma in posizione meno significativa nella stringa
         )
     ;else ;ricorsione
        (string-append ;sommo due stringhe
         ;ricorsione fra testa di Sbtr1, testa Sbtr2, riporto fra cifre meno significative (dx) 
         (btr-carry-sum (head Sbtr1) (head Sbtr2) (btr-digit-carry (lsd Sbtr1) (lsd Sbtr2) Crip))
         ;faccio diventare stringa la somma delle cifre meno significative e il loro riporto
         (string (btr-digit-sum (lsd Sbtr1) (lsd Sbtr2) Crip));cifra meno significativa
         )
     )
    )
  )
;(btr-carry-sum ".++" "+" #\+)


;funzione principale, date due stringhe in Rappresentazione Ternaria Bilanciata (+-.)
;ne calcola la somma 
(define btr-sum; string
  (lambda (Btr1 Btr2); string
    (normalized-btr (btr-carry-sum Btr1 Btr2 #\.))
    )
  )

(btr-sum "-+--" "+")
(btr-sum "-+--" "-")
(btr-sum "+-.+" "-+.-")
(btr-sum "-+--+" "-.--")
(btr-sum "-+-+." "-.-+")
(btr-sum "+-+-." "+.+-") 