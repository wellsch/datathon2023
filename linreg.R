require(glmnet)

data1 = read.csv("flipped.csv")
data2 = read.csv("2020flipped.csv")
data2rel = data2[1:50, 3:23]

data1$BDPRP<-5.46*(data1$BDPRP)
data1$ENPRP<-3.192*(data1$ENPRP)
data2$BDPRP<-5.46*(data2$BDPRP)
data2$ENPRP<-3.192*(data2$ENPRP)

for(i in 1:250) {
  for (j in 3:21) {
    if (data1[i, j] == 0) {
      data1[i, j] = mean(data1[,j])
    }
  }
}

x <- as.matrix(data1[,3:23])
y <- as.matrix(data1[,24])

set.seed(999)
cv.lasso <- cv.glmnet(x, y, family="gaussian", alpha=1, parallel=TRUE, standardize=TRUE, type.measure='auc')

# Results
plot(cv.lasso)
plot(cv.lasso$glmnet.fit, xvar="lambda", label=TRUE)
cv.lasso$lambda.min
cv.lasso$lambda.1se
coef(cv.lasso, s=cv.lasso$lambda.min)
error = 0
for (y in 1:50) {
  print(data2[y, 1])
  diff = predict(cv.lasso, as.matrix(data2rel[y,])) - data2[y, 24]
  print(diff)
  error = error + diff^2
}
error = sqrt(error)
print(error)


lmt = lm(assistance~BDPRP+CLPRB+ENPRP+GETCB+HYTCB+NCPRB+NGMPB+NUETB+PAPRB+REPRB+SOTCB+TEPRB+TETCB+WDEXB+WDPRB+WDTCB+WSTCB+WWPRB+WYTCB+emissions+numInvestments, data = data1)

error = 0
for (y in 1:50) {
  print(data2[y, 1])
  diff = predict(lmt, data2rel[y,]) - data2[y, 24]
  print(diff)
  error = error + diff^2
}
error = sqrt(error)
print(error)


