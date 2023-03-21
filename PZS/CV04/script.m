clear all;
close all;
clc;

N = 100;
n = 0:N-1;
x = cos(pi/2*n);

figure;
stem(n, x)

y = filter([1/3 1/3 1/3], [1], x);

hold on;
stem(n, y);

residuez([1 -1], [1 -3/4 1/8])