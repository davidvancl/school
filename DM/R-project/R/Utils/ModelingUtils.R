# Import knihoven
library(haven)
library(NbClust)
library(plotly)
library(C50)
library(rpart)

# Pomocné funkce pro hlavní program
load_data <- function(file) {
    return(read_sav(file))
}

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

map_data_to_numbers <- function(data) {
    mapovani <- data %>%
    distinct(
        LONGDIST, International, LOCAL, DROPPED, AGE, CHURNED,
        Est_Income, PAY_MTHD, LocalBillType, LongDistanceBillType,
        SEX, STATUS, Car_Owner, CHURNED
    ) %>%
    mutate(
        LongDistanceBillType =
            as.double(factor(LongDistanceBillType))
    ) %>%
    mutate(LocalBillType = as.double(factor(LocalBillType))) %>%
    mutate(PAY_MTHD = as.double(factor(PAY_MTHD))) %>%
    mutate(STATUS = as.double(factor(STATUS))) %>%
    mutate(SEX = as.double(factor(SEX))) %>%
    mutate(Car_Owner = as.double(factor(Car_Owner))) %>%
    mutate(CHURNED = as.double(factor(CHURNED)))

    mapovani$Est_Income <- as.double(factor(mapovani$Est_Income))
    mapovani$CHURNED <- ifelse(mapovani$CHURNED == 1, 0, 1)

    return(mapovani)
}

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
