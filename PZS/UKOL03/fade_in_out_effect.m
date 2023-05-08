function [y] = fade_in_out_effect(x)
    x_len = length(x);                                      % délka signálu
    r_samp = (x_len / 100) * 15;                            % 15% vzorků efektu (z leva, z prava)
    signal_effect = ones(size(x));                          % vygenerování vektoru jedniček
    signal_effect(1:r_samp + 1) = 0:1/r_samp:1;             % vložím do effektu fade in 15% vzorků
    signal_effect(x_len - r_samp:x_len) = 1:(-1/r_samp):0;  % vložím do effektu fade out 15% vzorků
    y = x .* signal_effect;                                 % vynásobím signál effektem a vrátím
end

