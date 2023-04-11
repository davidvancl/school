clear all;
close all;
clc;

load("filter_1.mat");
[H, w] = freqz(B, A,1000);

freqz(B, A,1000);
figure;plot(w/pi, abs(H));
figure;phasedelay(B, A,1000);
figure;stem(B);

% load("filter_2.mat");
% [H, w] = freqz(BB, AA,1000);
% 
% freqz(BB, AA,1000);
% figure;plot(w/pi, abs(H));
% figure;phasedelay(BB, AA,1000);
% figure;stem(BB);

N = 1000;
n = 0:N - 1;
x = zeros(size(n));
w_b = 0.02*pi;
fmr = 1:200;
for k = 1:5
    x = x + 4/pi*sin((2*k-1)*w_b*n) / (2*k-1);
end

figure;plot(n,x);
hold on;
y = filter(B,A, x);
plot(n,y);