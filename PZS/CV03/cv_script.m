clear all;
close all;
clc;

[x,x_fs] = audioread('TwoChannelMicrophoneArrayRecording.wav');
rxy = xcorr(x(:,1), x(:,2));

x_l = size(x, 1);

lag_ax = -x_l+1:x_l-1;

figure;
plot(lag_ax, rxy);

[mx, arg_mx] = max(rxy);
lmax = lag_ax(arg_mx);

TDOA = lmax * (1/x_fs);
DOA = asin(TDOA * 343/0.055)/ (2*pi)*360;