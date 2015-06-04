#!/usr/bin/env racket
#lang racket
(require 2htdp/batch-io)
(require racket/cmdline)
(define args (current-command-line-arguments))

(define rawnames (map (λ (name) (if (= (string-length name) 4) (substring name 1 4) name)) (read-lines (vector-ref args 0))))
;; (for ([oname processnames]) (printf (string-append oname "\n"))

(define pick-item (λ (l) (list-ref l (random (length l)))))

(define firstchar (map (λ (name) (substring name 0 1)) rawnames))
(define otherchar (map (λ (name) (substring name 1 2)) rawnames))
(define otherchar2 (map (λ (name) (if (= (string-length name) 2) "" (substring name 2 3))) rawnames))
(define printrandomname (λ (firstchar otherchar otherchar2)
(printf (string-append (pick-item firstchar) (pick-item (append otherchar otherchar2)) (pick-item (append otherchar otherchar2)) "\n"))))


(define (repeater f count) (for ((i (in-range count))) (f)))
(repeater (λ () (printrandomname firstchar otherchar otherchar2)) (string->number (vector-ref args 1)))
