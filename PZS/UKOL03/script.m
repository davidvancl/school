clear all;
close all;
clc;

load("Tones.mat");  % Načtení základních tonů

y = [];             % Příprava proměnné
% --------------PRVNÍ PART------------------------ (opakuje se)
for i = 1:2
    % ------------------TAKT 1-------------------- dohromady jeden oktet
    y = load_note(y, noteFlute, 1, "quarter");
    y = load_note(y, noteFlute, 6, "quarter");
    y = load_note(y, noteFlute, 5, "quarter");
    y = load_note(y, noteFlute, 4, "quarter");
    y = load_note(y, noteFlute, 1, "whole");
    % ------------------TAKT 2--------------------
    y = load_note(y, noteFlute, 1, "quarter");
    y = load_note(y, noteFlute, 6, "quarter");
    y = load_note(y, noteFlute, 5, "quarter");
    y = load_note(y, noteFlute, 4, "quarter");
    y = load_note(y, noteFlute, 2, "whole");
    % ------------------TAKT 3--------------------
    y = load_note(y, noteFlute, 2, "quarter");
    y = load_note(y, noteFlute, 7, "quarter");
    y = load_note(y, noteFlute, 6, "quarter");
    y = load_note(y, noteFlute, 5, "quarter");
    y = load_note(y, noteFlute, 8, "quarter");
    y = load_note(y, noteFlute, 8, "quarter");
    y = load_note(y, noteFlute, 8, "half");
    % ------------------TAKT 4--------------------
    y = load_note(y, noteFlute, 9, "quarter");
    y = load_note(y, noteFlute, 8, "quarter");
    y = load_note(y, noteFlute, 7, "quarter");
    y = load_note(y, noteFlute, 5, "quarter");
    y = load_note(y, noteFlute, 6, "whole");
end
% --------------DRUHÝ PART--------------------
% ------------------TAKT 1--------------------
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 6, "half");
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 6, "half");
% ------------------TAKT 2--------------------
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 8, "quarter");
y = load_note(y, noteFlute, 4, "quarter");
y = load_note(y, noteFlute, 5, "quarter");
y = load_note(y, noteFlute, 6, "half");
y = load_note(y, noteFlute, 0, "half");
% ------------------TAKT 3--------------------
y = load_note(y, noteFlute, 7, "quarter");
y = load_note(y, noteFlute, 7, "quarter");
y = load_note(y, noteFlute, 7, "half");
y = load_note(y, noteFlute, 7, "quarter");
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 6, "half");
% ------------------TAKT 4--------------------
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 5, "quarter");
y = load_note(y, noteFlute, 5, "quarter");
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 5, "whole");
% --------------TŘETÍ PART-------------------- (kvuli taktu 4 nejde cyklus)
% ------------------TAKT 1--------------------
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 6, "half");
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 6, "half");
% ------------------TAKT 2--------------------
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 8, "quarter");
y = load_note(y, noteFlute, 4, "quarter");
y = load_note(y, noteFlute, 5, "quarter");
y = load_note(y, noteFlute, 6, "half");
y = load_note(y, noteFlute, 0, "half");
% ------------------TAKT 3--------------------
y = load_note(y, noteFlute, 7, "quarter");
y = load_note(y, noteFlute, 7, "quarter");
y = load_note(y, noteFlute, 7, "half");
y = load_note(y, noteFlute, 7, "quarter");
y = load_note(y, noteFlute, 6, "quarter");
y = load_note(y, noteFlute, 6, "half");
% ------------------TAKT 4--------------------
y = load_note(y, noteFlute, 8, "quarter");
y = load_note(y, noteFlute, 8, "quarter");
y = load_note(y, noteFlute, 7, "quarter");
y = load_note(y, noteFlute, 5, "quarter");
y = load_note(y, noteFlute, 4, "half");
y = load_note(y, noteFlute, 0, "half");

[h, Fs_h] = audioread('carpark_balloon _441k.wav');  % Načtu si nahranou IR
h_resampled = resample(h(:,1), fsNew, Fs_h);         % Převzorkuji na 16kHZ
y = conv(y, h_resampled);                            % Aplikuji konvolucí

soundsc(y, fsNew);                                   % přehraji výsledek