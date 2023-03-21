objemkvadru(A, B, C, O) :- O is A * B * C.
povrchkvadru(A, B, C, P) :- P is 2 * A * B + 2 * B * C + 2 * C * A.

nsd(X, X, X).
nsd(X, Y, Delitel) :- X > Y, NewX is X - Y, nsd(NewX, Y, Delitel).
nsd(X, Y, Delitel) :- Y > X, NewY is Y - X, nsd(X, NewY, Delitel).

druhy(N, [_, N | _]).
druhy(N, L) :- nth0(1, L, N).

delka([], 0).
delka([_|Ocas], Delka) :- delka(Ocas, DelkaOcasu), Delka is DelkaOcasu + 1.

odrizni(0, Seznam, Seznam).
odrizni(_, [], []).
odrizni(N, [_|Ocas], Odriznuto) :- N > 0, NewN is N - 1, odrizni(NewN, Ocas, Odriznuto).