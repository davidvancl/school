# Nastavení cesty
base_path <- "/workspaces/Projects/DM/R-project"

# Import utilit z adresáře
source(paste(base_path, "/R/Utils/DeploymentUtils.R", sep = ""))
source(paste(base_path, "/R/Utils/PreparationUtils.R", sep = ""))

# Definice cest pro soubory
data_path <- paste(base_path, "/Data/deploydata.txt", sep = "")

#-------------- Začátek hlavního programu------------------------
# 1. Načtu DATA
data <- load_text_data(data_path)

# 1.1 Odstraním ID a No.phone.lines
user_ids <- as.data.frame(data$ID)
data <- data[, !(names(data) %in% c("ID", "No.phone.lines"))]
View(data)

# 2. Vytvořím rozdělení pomocí clusterů (Alternativa TwoStep)
res <- NbClust(data[, c("LOCAL", "International", "LONGDIST")],
    diss = NULL, distance = "euclidean", min.nc = 2, max.nc = 5,
    method = "ward.D2", index = "kl"
)
# 2.1 Přiřadím výsledek k datům
data$TwoStep <- res$Best.partition
View(data)

# 3. Provedu predikci na základě modelu C5.0
predictions <- predict(model_c5, data)
# 3.1 Přiřadím k datům původní tabulky
data$C_CHURNED <- predictions
View(data)

# 4. Do tabulky uložím i procentuální hodnotu
percentages <- predict(model_c5, data, type = "prob")
percentages <- as.data.frame(percentages)
# 4.1 Dva sloupce pro Vol i pro Current a zajímá mě ta predikovaná, max
data$CC_CHURNED <- pmax(percentages$Current, percentages$Vol)
View(data)

# 5. Do tabulky přidám vypočítanou hodnotu skóre
data$Skore <- ifelse(data$C_CHURNED == "Vol",
    0.5 + data$CC_CHURNED / 2,
    0.5 - data$CC_CHURNED / 2
)
View(data)

# 6. Vykreslím graf pro rozložení skóre
graph_skore <- ggplot(data, aes(
    x = round(Skore, digits = 2),
    fill = C_CHURNED
)) +
    geom_bar()
show(graph_skore)

# 7. Vyfiltruji řádky, které mají skóre větší než 0.5
data <- data[data$Skore > 0.5, ]

# 8. Doplní pole řádků zpátky ID, abych věděl čí je co
data$row_id <- rownames(data)
user_ids$row_id <- rownames(user_ids)
data <- merge(data, user_ids, by = "row_id", all.x = TRUE)
data <- data[, !(names(data) %in% c("row_id"))]
names(data)[ncol(data)] <- "ID"

# 9. Seřadím data vzestupně podle clusteru a sestupně podle Skore
data <- data[order(data$TwoStep, -data$Skore), ]

# 10. Vypíšu vyhodnocená data
View(data)
