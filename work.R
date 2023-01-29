data = read.csv("LinearRegPredictions.csv")
data1 = read.csv("2020flipped.csv")


for (i in 1:50) {
  data[i, 2] = round(data[i, 2], 0)
}

data2 = cbind(data1[,1], data[,2])

write.csv(data2, "output.csv")
