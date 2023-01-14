# Import dataset from local download file
aged_25_54_labour_force_participation_rate_percent <- read.csv("~/Downloads/aged_25_54_labour_force_participation_rate_percent.csv")
library(fpp)
library(fpp2)
df <- aged_25_54_labour_force_participation_rate_percent
df[names(df) %in% c("United States", "China", "United Kingdom", "India", "Canada")]
df1 <- subset(df, country%in%c("United States", "China", "United Kingdom", "India", "Canada"), select = c("country", "X2006", "X2007", "X2008", "X2009", "X2010"))
# Perform mean and Holt Winters Forecasting on five countries
Canada <- ts( as.vector(t(df1[1,2:6])), start = c(2006, 1), frequency = 1)
fc <- meanf(Canada, h = 5)
plot(fc, xlab = "Year", ylab = "Participation Rate", main = "Canada")
fc$mean 
accuracy(fc)
hist(fc$residuals, xlab = "mean residual", main = "Histogram of mean residual of Canada")
library(stats)
holttrend = holt(Canada, h = 5)
holttrend
accuracy(holttrend)
plot(holttrend)
hist(holttrend$residuals, xlab = "Holt Winters residual", main = "Histogram of Holt Winters residual of Canada")

China <- ts( as.vector(t(df1[2,2:6])), start = c(2006, 1), frequency = 1)
fc <- meanf(China, h = 5)
plot(fc, xlab = "Year", ylab = "Participation Rate", main = "China")
fc$mean 
accuracy(fc)
hist(fc$residuals, xlab = "mean residual", main = "Histogram of mean residual of China")
library(stats)
holttrend = holt(China, h = 5)
holttrend
accuracy(holttrend)
plot(holttrend)
hist(holttrend$residuals, xlab = "Holt Winters residual", main = "Histogram of Holt Winters residual of China")
 
UK <- ts( as.vector(t(df1[3,2:6])), start = c(2006, 1), frequency = 1)
fc <- meanf(UK, h = 5)
plot(fc, xlab = "Year", ylab = "Participation Rate", main = "United Kingdom")
fc$mean 
accuracy(fc)
hist(fc$residuals, xlab = "mean residual", main = "Histogram of mean residual of United Kingdom")
library(stats)
holttrend = holt(UK, h = 5)
holttrend
accuracy(holttrend)
plot(holttrend)
hist(holttrend$residuals, xlab = "Holt Winters residual", main = "Histogram of Holt Winters residual of United Kingdom")

India <- ts( as.vector(t(df1[4,2:6])), start = c(2006, 1), frequency = 1)
fc <- meanf(India, h = 5)
plot(fc, xlab = "Year", ylab = "Participation Rate", main = "India")
fc$mean 
accuracy(fc)
hist(fc$residuals, xlab = "mean residual", main = "Histogram of mean residual of India")
library(stats)
holttrend = holt(India, h = 5)
holttrend
accuracy(holttrend)
plot(holttrend)
hist(holttrend$residuals, xlab = "Holt Winters residual", main = "Histogram of Holt Winters residual of India")

US <- ts( as.vector(t(df1[5,2:6])), start = c(2006, 1), frequency = 1)
fc <- meanf(US, h = 5)
plot(fc, xlab = "Year", ylab = "Participation Rate", main = "United States")
fc$mean 
accuracy(fc)
hist(fc$residuals, xlab = "mean residual", main = "Histogram of mean residual of United States")
library(stats)
holttrend = holt(US, h = 5)
holttrend
accuracy(holttrend)
plot(holttrend)
hist(holttrend$residuals, xlab = "Holt Winters residual", main = "Histogram of Holt Winters residual of United States")

# 3-year Moving averages of five countires
autoplot(Canada, series="Data") +autolayer(ma(Canada,3), series="3-MA") +xlab("Year") + ylab("Partipation") +ggtitle("Canda moving average") +scale_colour_manual(values=c("Data"="grey50","3-MA"="red"),breaks=c("Data","3-MA"))
ma(Canada,3)
autoplot(US, series="Data") +autolayer(ma(US,3), series="3-MA") +xlab("Year") + ylab("Partipation") +ggtitle("US moving average") +scale_colour_manual(values=c("Data"="grey50","3-MA"="red"),breaks=c("Data","3-MA"))
ma(US,3)
autoplot(UK, series="Data") +autolayer(ma(UK,3), series="3-MA") +xlab("Year") + ylab("Partipation") +ggtitle("UK moving average") +scale_colour_manual(values=c("Data"="grey50","3-MA"="red"),breaks=c("Data","3-MA"))
ma(UK,3)
autoplot(China, series="Data") +autolayer(ma(China,3), series="3-MA") +xlab("Year") + ylab("Partipation") +ggtitle("China moving average") +scale_colour_manual(values=c("Data"="grey50","3-MA"="red"),breaks=c("Data","3-MA"))
ma(China,3)
autoplot(India, series="Data") +autolayer(ma(India,3), series="3-MA") +xlab("Year") + ylab("Partipation") +ggtitle("India moving average") +scale_colour_manual(values=c("Data"="grey50","3-MA"="red"),breaks=c("Data","3-MA"))
ma(India,3)
# Now updates the actual data of five countries from 2011-2015 to determine which method is the best in this analysis.
df2 <- subset(df, country%in%c("United States", "China", "United Kingdom", "India", "Canada"), select = c("country", "X2011", "X2012", "X2013", "X2014", "X2015"))
Canada2011 <- ts( as.vector(t(df2[1,2:6])), start = c(2011, 1), frequency = 1)
China2011 <- ts( as.vector(t(df2[2,2:6])), start = c(2011, 1), frequency = 1)
UK2011 <- ts( as.vector(t(df2[3,2:6])), start = c(2011, 1), frequency = 1)
India2011 <- ts( as.vector(t(df2[4,2:6])), start = c(2011, 1), frequency = 1)
US2011 <- ts( as.vector(t(df2[5,2:6])), start = c(2011, 1), frequency = 1)
autoplot(Canada2011) + xlab("Year") + ylab("participation Rate") + ggtitle("Actual Participation Rate from 2011 to 2015")
autoplot(Canada2011) + xlab("Year") + ylab("participation Rate") + ggtitle("Actual Canada's Participation Rate from 2011 to 2015")
autoplot(UK2011) + xlab("Year") + ylab("participation Rate") + ggtitle("Actual UK's Participation Rate from 2011 to 2015")
autoplot(US2011) + xlab("Year") + ylab("participation Rate") + ggtitle("Actual US's Participation Rate from 2011 to 2015")
autoplot(China2011) + xlab("Year") + ylab("participation Rate") + ggtitle("Actual China's Participation Rate from 2011 to 2015")
autoplot(India2011) + xlab("Year") + ylab("participation Rate") + ggtitle("Actual India's Participation Rate from 2011 to 2015")
