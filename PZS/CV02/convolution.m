clear all;
close all;
clc;

[x, x_fs] = audioread('samba_short.wav');

% DP - dolní prospust, HP - horní propust
dp = load('DP.mat');
hp = load('HP.mat');

z = conv(x, hp.b_hp);

y = conv(x, dp.b_dp); % 1. použití conv
y = filter(dp.b_dp, 1, x); % 2. funkce filter, 1 z druhé strany rovnice

% pro rekurzivní fce vždy filter