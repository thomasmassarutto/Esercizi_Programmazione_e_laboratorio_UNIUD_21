;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es2) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "teachpack.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "teachpack.ss" "installed-teachpacks")) #f)))
;;LEGGIMI
;;Aggiungi teachpack.ss dal menu Language >  Add Teachpack
;;Deve essere presente drawings.ss

(set-tessellation-shift-step!)
;larger-tile
;smaller-tile

;croce
(glue-tiles
 ;prima meta
(glue-tiles (shift-right smaller-tile 3.2) larger-tile )
; stessa forma di sopra solo messa ok
(shift-down (shift-right (half-turn (glue-tiles (shift-right smaller-tile 3.2) larger-tile )) 3.2) 1.4)
)


;seconda forma
(glue-tiles
 ;prima metà
  (half-turn(glue-tiles larger-tile(shift-down smaller-tile 6.4)))
  ;specchiata
  (shift-down (shift-right (glue-tiles larger-tile(shift-down smaller-tile 6.4 )) 3.2) 1.6)
)

  



