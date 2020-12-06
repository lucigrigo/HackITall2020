import pandas as pd
import numpy as np
import tensorflow as tf
import cv2
import os
import matplotlib.pyplot as plt
import seaborn as sns
from keras.preprocessing.image import ImageDataGenerator
from keras.callbacks import ReduceLROnPlateau
from sklearn.metrics import confusion_matrix
from keras.preprocessing import image

new_model = tf.keras.models.load_model('XRaysDetector/')
new_model.summary()

for i in range (1, 43):
	test_image = image.load_img('PneumoniaXRays/' + str(i) + '.jpeg',
	                            target_size = (150, 150), color_mode = 'grayscale')
	test_image = image.img_to_array(test_image)
	test_image = np.expand_dims(test_image, axis = 0)
	result = new_model.predict(test_image)
	if result[0][0] == 1:
		prediction = 'Pneumonia'
	else:
		prediction = 'Normal'
	print('Health state of XRay #' + str(i) + '--' + prediction)
