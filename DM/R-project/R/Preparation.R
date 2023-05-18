# Nastavení cesty
base_path <- "/workspaces/Projects/DM/R-project"

# Import utilit z adresáře
source(paste(base_path, "/R/Utils/PreparationUtils.R", sep = ""))

# Definice cest pro soubory
raw_data_path <- paste(base_path, "/Data/rawdata.txt", sep = "")
export_data_path <- paste(base_path, "/Data/export_data_r.sav", sep = "")

#-------------- Začátek hlavního programu------------------------
# 1. Načtu DATA a zobrazím view
raw_data <- load_text_data(raw_data_path)
View(raw_data)

# 2. Provedu FILTER nad sloupcem No.phone.lines a vykreslím view
raw_data <- raw_data[, !names(raw_data) %in% c("No.phone.lines")]
View(raw_data)

# 3. Provedu SELECT na CHURN a vyberu pouze neprázdné hodnoty
raw_data <- raw_data[
    (trimws(raw_data$CHURNED) != "" &
        !is.na(trimws(raw_data$CHURNED))),
]
View(raw_data)

# 4. Provedu SELECT na CHURN a odeberu neplatící zákazníky
raw_data <- raw_data[trimws(raw_data$CHURNED) != "InVol", ]
View(raw_data)

# 5. Provedu BALANCE pro sloupec CHURN
value_counts <- table(raw_data$CHURNED)
# 5.1 Určení maximálního počtu výskytů mezi hodnotami
max_count <- max(value_counts)
# 5.2 Inicializace prázdné tabulky pro vyvážená data
balanced_data <- data.frame()
# 5.3 Pro každou hodnotu ve sloupci
for (value in unique(raw_data$CHURNED)) {
    # 5.4 Získání počtu výskytů této hodnoty
    count <- value_counts[value]
    # 5.5 Výpočet počtu potřebných nových řádků
    num_new_rows <- max_count - count
    # 5.6 Výběr náhodných řádků s touto hodnotou pro duplikaci
    duplicated_rows <- raw_data[raw_data$CHURNED == value, ]
    # 5.7 Přidání nových řádků do vyvážené tabulky
    for (i in 1:num_new_rows) {
        balanced_data <- rbind(balanced_data, duplicated_rows[i, ])
    }
}
# 5.8 Sloučení původní tabulky s vyváženou tabulkou
raw_data <- rbind(raw_data, balanced_data)
# 5.9 Seřadím podle sloupce ID
raw_data <- raw_data[order(raw_data$ID), ]
# 5.10 Zobrazím graf vyváženosti a tabulku s daty
graph_data <- ggplot(raw_data, aes(x = CHURNED, fill = CHURNED)) +
    geom_bar()
View(raw_data)
show(graph_data)

# 6. Převedu faktory na string a uložím výsledek do .sav souboru
export_frame <- prepare_data_export(raw_data)
write_sav(export_frame, export_data_path)
#-------------- Konec hlavního programu------------------------
