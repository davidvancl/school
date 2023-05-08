function [y] = load_note(y, noteFlute, note, type)
    if note == 0
        if type == "quarter"                        % Podle typu načtu délku mezery
            y = [y zeros(1, 4000)];                    % quarter - 4000
        elseif type == "half"
            y = [y zeros(1, 8000)];                    % half - 8000
        elseif type == "whole"
            y = [y zeros(1, 16000)];                   % whole - celá
        end
    else
        x = noteFlute{note};                         % Načtu z dat danou notu
        if type == "quarter"                         % Podle typu načtu délku noty
            y = [y fade_in_out_effect(x(1:4000))];   % quarter - 4000
        elseif type == "half"
            y = [y fade_in_out_effect(x(1:8000))];   % half - 8000
        elseif type == "whole"
            y = [y fade_in_out_effect(x)];           % whole - celá
        end
    end
end