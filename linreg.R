data1 = read.csv("flipped.csv")
data2 = read.csv("2020flipped.csv")
data1$BDPRP<-5.46*(data1$BDPRP)
data1$ENPRP<-3.192*(data1$ENPRP)
data2$BDPRP<-5.46*(data2$BDPRP)
data2$ENPRP<-3.192*(data2$ENPRP)
lmt = lm(assistance~BDPRP+CLPRB+ENPRP+GETCB+HYTCB+NCPRB+NGMPB+NUETB+PAPRB+REPRB+SOTCB+TEPRB+TETCB+WDEXB+WDPRB+WDTCB+WSTCB+WWPRB+WYTCB+emissions+numInvestments, data = data1)
data2rel = data2[1:50, 3:23]
error = 0
for (x in 1:50) {
  print(data2[x, 1])
  diff = predict(lmt, data2rel[x,]) - data2[x, 24]
  print(diff)
  error = error + diff^2
}
error = sqrt(error)
print(sum(data2[,24]))
