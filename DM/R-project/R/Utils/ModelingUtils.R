# Import knihoven
library(haven)
library(NbClust)
library(plotly)
library(C50)
library(rpart)
library(nnet)

# ===========Pomocné funkce pro hlavní program===========
# Načtení dat ze sav souboru
load_data <- function(file) {
    return(read_sav(file))
}

# Připravení dat na faktory pro následné zpracování
prepare_factors <- function(frame) {
    export_frame <- frame
    export_frame$Est_Income[export_frame$Est_Income == "$null$"] <- "0"
    export_frame$PAY_MTHD <- as.factor(export_frame$PAY_MTHD)
    export_frame$LocalBillType <- as.factor(export_frame$LocalBillType)
    export_frame$LongDistanceBillType <-
        as.factor(export_frame$LongDistanceBillType)
    export_frame$SEX <- as.factor(export_frame$SEX)
    export_frame$STATUS <- as.factor(export_frame$STATUS)
    export_frame$Est_Income <- as.double(export_frame$Est_Income)
    export_frame$Car_Owner <- as.factor(export_frame$Car_Owner)
    export_frame$CHURNED <- as.factor(export_frame$CHURNED)
    return(export_frame)
}

# Převedení dat do oblasti čísel
map_data_to_numbers <- function(data) {
    data$LongDistanceBillType <- as.double(factor(data$LongDistanceBillType))
    data$LocalBillType <- as.double((data$LocalBillType))
    data$PAY_MTHD <- as.double(factor(data$PAY_MTHD))
    data$STATUS <- as.double(factor(data$STATUS))
    data$SEX <- as.double(factor(data$SEX))
    data$Car_Owner <- as.double(factor(data$Car_Owner))
    data$CHURNED <- as.double(factor(data$CHURNED))
    data$Est_Income <- as.double(factor(data$Est_Income))
    data$CHURNED <- ifelse(data$CHURNED == 1, 0, 1)

    return(data)
}

# Vykreslení koláčového grafu
print_success_graph <- function(percentage, title) {
    lbls <- c(
        paste("Neúspěšne", "\n", 100 - percentage),
        paste("Úspěšně", "\n", percentage)
    )
    pie(c(100 - percentage, percentage),
        labels = lbls,
        col = rainbow(length(lbls)),
        main = title
    )
}
