import os

for file in os.listdir('C:\\Users\\Imad\\Desktop\\S 4\\POO 2\\TP\\TP3\\src'):
	if file.endswith('.class'):
		os.remove(os.path.join('C:\\Users\\Imad\\Desktop\\S 4\\POO 2\\TP\\TP3\\src', file))