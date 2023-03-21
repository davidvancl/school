clear all;
close all;
clc;

figure("Name", "Zarovnání signálu", 'NumberTitle','off'); % figura 1
hold on; % zápis do jednoho okna

[x,x_fs] = audioread('DSVowels.wav'); % Načtu signál do x a vzorkovací frekvenci x_fs
x_len = length(x); % délka signálu x

t = (0:x_len - 1)/x_fs; % časová osa navzorkovaná
plot(t, x) % vykreslení originálního signálu

xx = x - mean(x); % odečtu střední hodnoty od signálu
plot(t, xx) % vykreslení zarovnaný signál

xx_2 = xx .^ 2; % počítám okamžitý výkon, tedy N = 1 a proto můžu dát pouze na 2

figure("Name", "Energie signálu", 'NumberTitle','off');
hold on;

plot(t, xx_2); % vykreslení spočítané energie energie

L = 1000; % definuji konstantu L
u = ones([x_len 1]); % vytvořím vektor 1 což je rovno jednotkovému skoku u[n]
u_l = u; % zkopíruju vektor jedniček
u_l(1:999) = 0; % vytvořím vektor 1 posunutý o 1k vzorků to je rovno u[n - L]
h_n = 1/L .* (u - u_l); % vygeneruju h_n, tedy dosadím h[n]=(1/L)·(u[n]-u[n-L])

xx_2_c = filter(h_n, 1, xx_2); % provedu aplikaci filteru
plot(t, xx_2_c); % vykreslení ofiltrované energie

min_constant = sum(xx_2) * 10^-6; % vezmu prahovou hodnotu
y = xx_2_c; % y nastavím jako přefiltrovaný signál
y(y >= min_constant) = max(xx); % pod dobu detekovaného signálu nastavím max signálu
y(y < min_constant) = 0; % ostatní nedetekované hodnoty vynuluji

figure("Name", "Označení detekovaných úseků", 'NumberTitle','off');
hold on;

plot(t, xx); % vykreslím původní signál (zarovnaný)
plot(t, y); % vykreslím hanice detekované řeči
