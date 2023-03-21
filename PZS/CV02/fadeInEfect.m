clear all;
close all;
clc;

% fade in efekt
r_on = 5;

[x, x_fs] = audioread('samba_short.wav');
x_len = length(x);

r_samp = r_on * x_fs;

t = (0:x_len - 1)/x_fs;
figure;
plot(t, x);

% soundsc(x, x_fs)
% clear sound

r = ones(size(x));
r(1:r_samp + 1) = 0:1/r_samp:1;

y = x .* r;

figure;
plot(t, y);