#suppressWarnings(library(dplyr))

# make it not depends on dplyr, but less functional

options <- commandArgs(trailingOnly = TRUE)

#data.frame(rawname = suppressWarnings(readLines(options[1])), stringsAsFactors = FALSE) %>% mutate(rawname = ifelse(nchar(rawname) ==4, substr(rawname, 2,4), rawname)) %>% mutate(first = substr(rawname, 1,1), second = substr(rawname, 2,2), third = substr(rawname, 3,3)) -> rawname

rawname <- data.frame(rawname = suppressWarnings(readLines(options[1])), stringsAsFactors = FALSE)
rawname$rawname <- ifelse(nchar(rawname$rawname)==4, substr(rawname$rawname, 2,4), rawname$rawname)
rawname$first <- substr(rawname$rawname, 1,1)
rawname$second <- substr(rawname$rawname, 2,2)
rawname$third <- substr(rawname$rawname, 3,3)


for (i in 1:as.numeric(options[2])) {
    cat(paste0(sample(rawname$first,1), sample(c(rawname$second, rawname$third),1), sample(c(rawname$second, rawname$third),1), "\n"))
}
