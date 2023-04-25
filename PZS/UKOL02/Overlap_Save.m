close all;
clear;
clc;

maxNumCompThreads(1);           %Nastavení výpočetního výkonu na jedno vlákno
%% Krátký signál na krokování
% x_orig = [3 -1 0 1 3 2 0 1 2 1];  %Signál x
% L = 3;                            %Délka odezvy
% h = [1 1 1];                      %Impulzní odezva

%% Generovaný signál
% N = 1e8;                           %Délka signálu
% x_orig = randn(1, N);              %Signál x
% L = 2048;                          %Délka odezvy
% h = randn(1, L);                   %Impulzní odezva

%% Replikovaná audionahrávka
[x,Fs]=audioread('waltz.wav');  %Načtu signál
x=repmat(x(:,1),20,1);          %Vezmu pouze jeden kanál 20x ho zreplikuju
x_orig=x(:,1)';                 %Načtu pouze jeden kanál ze signálu

L=1024;                         %Délka impulzní odezvy
h=fir1(L-1,0.2);                %vygenerování impulzní odezvy jako low-pass filter

%% algoritmus
M=(2^nextpow2(2*L)-L)+1;        %Možná délka posouvaného bloku, na druhou kvůli fft

dfth=fft([h zeros(1,M-1)]);     %Předvygeneruji, doplním 0 a vypočítám fft pro h

x = [zeros(1,L - 1) x_orig];    %Doplním počátek L - 1 - 0 tak abych mohl začít od začátku
x = [x zeros(1,M - L + 1)];     %Doplním 0 na konec kvůli přetečení pro konvoluci

tic;                            %Zaínám počítat
nr=ceil(length(x)/L);           %Vypočítám počet kroků potřebných k projití signálu

for k=1:nr
    start = (k-1) * L + 1;      %Index pro počátek bloku
    stop = k * L + M - 1;       %Index pro konec bloku

    if stop > length(x)         %Pokud přesáhnu přes konec doplním nuly a spočítám fft
        dftx = fft([x(start:end) zeros(1, size(dfth,2) - size(x(start:end), 2))]);
    else
        dftx = fft(x(start:stop)); %Jinak spočítám fft pro daný blok
    end

    y = ifft(dftx.*dfth);       % Provedu inverzi abych dostal výsledný y
    y_res(start:stop - L + 1) = y(L:end);   % z výsledku bloku uložím pouze relevantí informace tedy od L do konce
end
y_res = y_res(1:(length(x) - M + L - 1)); % Odeberu koncové přebytečné 0 doplněné kvůli jednoduchosti výpočtu
disp('Čas pro overlap save:');
y_save_time=toc                 % Ukončení a výpis času

%% Použití funkce conv
tic;                            %Zaínám počítat
y_b=conv(x_orig,h);             %Změřím jak dlouho poběží build-in funkce
disp('Čas pro conv funkci:');
y_b_time=toc                    % Ukončení a výpis času

%% Porovnání výsledku
disp('Rozdíl signálů:');
sum((y_b-y_res).^2)             % Vypíšu rozdíl mezi mojí a conv funkcí