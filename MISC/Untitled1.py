#!/usr/bin/env python
# coding: utf-8

# In[1]:


get_ipython().system('pip install apyori')


# In[61]:


import numpy as np
import matplotlib.pyplot as plt
import pandas as pd


# In[62]:


dataset = pd.read_csv('Market_Basket_Optimisation.csv', header = None)
transactions = []
for i in range(0, 7501):
  transactions.append([str(dataset.values[i,j]) for j in range(0, 20)])


# In[63]:


from apyori import apriori
rules = apriori(transactions = transactions, min_support = 0.003, min_confidence = 0.2, min_lift = 3, min_length = 2, max_length = 2)


# In[64]:


results = list(rules)


# In[65]:


def inspect(results):
    lhs         = [tuple(result[2][0][0])[0] for result in results]
    rhs         = [tuple(result[2][0][1])[0] for result in results]
    supports    = [result[1] for result in results]
    confidences = [result[2][0][2] for result in results]
    lifts       = [result[2][0][3] for result in results]
    return list(zip(lhs, rhs, supports, confidences, lifts))
resultsinDataFrame = pd.DataFrame(inspect(results), columns = ['Left Hand Side', 'Right Hand Side', 'Support', 'Confidence', 'Lift'])


# In[66]:


#resultsinDataFrame


# In[67]:


#resultsinDataFrame.nlargest(n = 10, columns = 'Lift')


# In[68]:


n = resultsinDataFrame.size // 5


# In[69]:


asc = []
for i in range(n):
    asc.append([(resultsinDataFrame.nlargest(n = 10, columns = 'Lift')).iloc[i]['Left Hand Side'], (resultsinDataFrame.nlargest(n = 10, columns = 'Lift')).iloc[i]['Right Hand Side']])


# In[70]:


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
print(to_buy)

