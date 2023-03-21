rodic(alfons, vera).
rodic(alfons, emil).
rodic(ema, emil).
rodic(ema, vera).
rodic(jiri, ivana).
rodic(jitka, ivana).
rodic(emil, cyril).
rodic(ivana, cyril).
rodic(bohumil, lenka).
rodic(cyril, zuzana).
rodic(cyril, otakar).
rodic(lenka, zuzana).
rodic(lenka, otakar).

muz(alfons).
muz(jiri).
muz(emil).
muz(bohumil).
muz(cyril).
muz(otakar).

zena(vera).
zena(ivana).
zena(jitka).
zena(lenka).
zena(zuzana).
zena(ema).

prarodic(X, Y) :- rodic(X, Z), rodic(Z, Y).
vnouce(X, Y) :- rodic(Y,Z), rodic(Z,X).
pravnouce(X, Y) :- rodic(Y,Q), rodic(Q, Z), rodic(Z,X).
sourozenec(X, Y) :- rodic(Z, X), rodic(Z, Y), X\==Y.
bratr(X, Y) :- muz(Y), sourozenec(Y, X).
teta(X, Y) :- zena(Y), sourozenec(Y, Q), rodic(Q, X).