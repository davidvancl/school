# Import knihoven
library(stringr)
library(haven)
library(ggplot2)
library(dplyr)

# Pomocné funkce pro hlavní program
load_text_data <- function(file) {
    data <- read.table(
        file,
        sep = ",",
        header = TRUE,
        stringsAsFactors = TRUE,
        dec = "."
    )
    return(data)
}

# Pomocné funkce pro hlavní program
prepare_data_export <- function(frame) {
    export_frame <- frame
    export_frame$PAY_MTHD <- as.character(export_frame$PAY_MTHD)
    export_frame$LocalBillType <- as.character(export_frame$LocalBillType)
    export_frame$LongDistanceBillType <-
        as.character(export_frame$LongDistanceBillType)
    export_frame$SEX <- as.character(export_frame$SEX)
    export_frame$STATUS <- as.character(export_frame$STATUS)
    export_frame$Est_Income <- as.character(export_frame$Est_Income)
    export_frame$Car_Owner <- as.character(export_frame$Car_Owner)
    export_frame$CHURNED <- as.character(export_frame$CHURNED)
    return(export_frame)
}
