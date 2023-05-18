# Nastavení cesty
base_path <- "/workspaces/Projects/DM/R-project"

# Import utilit z adresáře
source(paste(base_path, "/R/Utils/ModelingUtils.R", sep = ""))

# Definice cest pro soubory
export_data_path <- paste(base_path, "/Data/export_data_r.sav", sep = "")

#-------------- Začátek hlavního programu------------------------
# 1. Načtu si vyexportovaná data
data <- load_data(export_data_path)
data <- data[, !(names(data) %in% c("ID"))]

# 2. Vytvořím rozdělení pomocí clusterů (Alternativa TwoStep) a přiřadím k datům
res <- NbClust(data[, c("LOCAL", "International", "LONGDIST")],
    diss = NULL, distance = "euclidean", min.nc = 2, max.nc = 5,
    method = "ward.D2", index = "kl"
)
data$TwoStep <- res$Best.partition
# View(data)

# 3. Vykreslím 3D graf pro zařazení do clusterů
fig <- plot_ly(data,
    x = ~LOCAL, y = ~LONGDIST, z = ~International, color = ~TwoStep,
    colors = c("#BF382A", "#0C4B8E", "#12C409", "#0BECE9")
)
fig <- fig %>% add_markers()
fig <- fig %>% layout(scene = list(
    xaxis = list(title = "LOCAL"),
    yaxis = list(title = "LONGDIST"),
    zaxis = list(title = "International")
))
# show(fig)

# 4. Vykreslím histogram pro clusterované hodnoty
vol_frame <- data[data$CHURNED == "Vol", ]
# hist(vol_frame$TwoStep, col = rgb(1, 0, 0, 0.5))
current_frame <- data[data$CHURNED == "Current", ]
# hist(current_frame$TwoStep, col = rgb(0, 0, 1, 0.5), add = TRUE)

# 5. PARTITION - Rozdělím data na trénovací a testovací
# 5.1 Náhodně zamícháme řádky v dataframe
prepare_data <- data[sample(nrow(data)), ]

# 5.2 Rozdělení 90% trénovací data a 10%
train_percent <- 0.9
train_size <- round(train_percent * nrow(data))
train_data <- prepare_factors(prepare_data[1:train_size, ])
test_data <- prepare_factors(prepare_data[(train_size + 1):nrow(data), ])
# View(train_data)
# View(test_data)

# 6 Predikce algoritmem C5.0
# 6.1 Připravým trénovací data a cílový sloupec
train_x <- train_data[, !(names(train_data) %in% c("CHURNED"))]
train_y <- train_data$CHURNED

# 6.2 Provedu natrénování modelu
model_c5 <- C5.0(train_x, train_y)
print(model_c5)

# 6.1 Připravým testovací data a cílový sloupec
test_x <- test_data[, !(names(test_data) %in% c("CHURNED"))]
test_y <- test_data$CHURNED
# View(test_x)

# 6.4 Predikce na testovacích datech
predictions_c5 <- predict(model_c5, test_x)

# 6.5 Vyhodnocení výsledků v podobě koláčového grafu
accuracy_c5 <- (sum(predictions_c5 == test_y) / length(test_y)) * 100
print_success_graph(accuracy_c5, "Úspěšnost predikce modelem C5.0")

# 7 Predikce algoritmem C&R Tree
# 7.1 Provedu natrénování modelu
model_cr <- rpart(CHURNED ~ ., data = train_data, method = "class")

# 7.2 Predikce na testovacích datech
predictions_cr <- predict(model_cr, test_x, type = "class")

# 7.3 Výpis výsledků predikce
accuracy_cr <- (sum(predictions_cr == test_y) / length(test_y)) * 100
print_success_graph(accuracy_cr, "Úspěšnost predikce modelem C&R Tree")

# 8 Predikce algoritmem Logistic
# 8.1 Převod na numerická data
train_data <- map_data_to_numbers(train_data)
test_data <- map_data_to_numbers(test_data)

# 8.2 Rozložení testovacích dat a slouspe s výsledkem
test_x <- test_data[, !(names(test_data) %in% c("CHURNED"))]
test_y <- test_data$CHURNED

# 8.3 Provedu natrénování modelu
model_glm <- glm(CHURNED ~ ., data = train_data, family = binomial)

# 8.4 Predikce na testovacích datech a převod výsledků do 0 a 1
predicted_glm <- predict(model_glm, newdata = test_x, type = "response")
predicted_glm <- ifelse(predicted_glm > 0.5, 1, 0)

# 8.5 Výpis výsledků predikce
accuracy_glm <- (sum(predicted_glm == test_y) / length(test_y)) * 100
print_success_graph(accuracy_glm, "Úspěšnost predikce modelem Logistic")

# 9 Predikce algoritmem Neuronová síť
# 9.1 Provedu natrénování modelu
model_neural <- nnet(CHURNED ~ .,
    data = train_data, size = 5, linout = TRUE, decay = 0.1
)

# 9.2 Predikce na testovacích datech a převod výsledků do 0 a 1
predicted_neural <- predict(model_neural, newdata = test_x)
predicted_neural <- ifelse(predicted_neural > 0.5, 1, 0)

# 9.3 Výpis výsledků predikce
accuracy_neural <- (sum(predicted_neural == test_y) / length(test_y)) * 100
print_success_graph(accuracy_neural, "Úspěšnost predikce modelem Neuronová sít")
#-------------- Konec hlavního programu------------------------
