clear all;
close all;
clc;

% a = [1;2;3];
% b = zeros(3, 2);
% c = ones(3, 2);
% d = rand(3, 2);
% e = randn(1000, 1);
% % histogram(e);
% f = 1:1:10;
% g = [1 2 3;4 5 6;7 8 9];
% % g(2,2)
% % g(2,:)
% % g(:)
% %for lp = 1:2:10
% %     lp
% % end
% h = [1 2 3];
% i = [4 5 6]'; % h + i divočina součet slopcového vektoru a řádkového

n = 0:100;
x = sin(pi/20 * n);

figure;
plot(n, x);

% Domácí úloha
% zopakovat si soustavu linearnich rovnic na papír, kolik má řešení a na
% čem závisí + kvadratická rovnice


ra = cos(pi/20*n)
rb = sin(pi/20*n);
plot3(ra, rb, n)






