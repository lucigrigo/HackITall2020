import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

dataset = pd.read_csv('Market_Basket_Optimisation.csv', header = None)
transactions = []
for i in range(0, 7501):
  transactions.append([str(dataset.values[i,j]) for j in range(0, 20)])

from apyori import apriori
rules = apriori(transactions = transactions, min_support = 0.003, min_confidence = 0.2, min_lift = 3, min_length = 2, max_length = 2)

results = list(rules)

def inspect(results):
    lhs         = [tuple(result[2][0][0])[0] for result in results]
    rhs         = [tuple(result[2][0][1])[0] for result in results]
    supports    = [result[1] for result in results]
    confidences = [result[2][0][2] for result in results]
    lifts       = [result[2][0][3] for result in results]
    return list(zip(lhs, rhs, supports, confidences, lifts))
resultsinDataFrame = pd.DataFrame(inspect(results), columns = ['Left Hand Side', 'Right Hand Side', 'Support', 'Confidence', 'Lift'])

n = resultsinDataFrame.size // 5

asc = []
for i in range(n):
    asc.append([(resultsinDataFrame.nlargest(n = 10, columns = 'Lift')).iloc[i]['Left Hand Side'], (resultsinDataFrame.nlargest(n = 10, columns = 'Lift')).iloc[i]['Right Hand Side']])

to_buy = []
for tr in transactions[-4:]:
    for prod in tr:
        if prod == 'nan':
            continue
        if prod in to_buy:
            to_boy.remove(prod)
        for asoc in asc:
            if prod in asoc:
                asoc.remove(prod)
                to_buy.append(asoc)

print(str(to_buy))
