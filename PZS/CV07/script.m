clear all;
close all;
clc;

[x, x_fs] = audioread('DTMF.wav');

n = 0:length(x) - 1;
plot(n, x);

win_len = 400;
% spectrogram(x,win_len, 0, 2 * win_len, x_fs, 'yaxis');colormap jet;

spectrogram(x,win_len, 0, 2 * win_len, x_fs, 'yaxis');colormap jet;