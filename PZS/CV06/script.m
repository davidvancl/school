clear all;
close all;
clc;

N = 256;
n = 0:N-1;
f_s = 16000;
x = cos(pi/4 * n);
X = fft(x); %spectrum

k = n;
w_ax = 2 * pi * k/N;
f_ax = f_s * k/N;
stem(f_ax, abs(X));

