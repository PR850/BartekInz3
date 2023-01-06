from PIL import Image
from pathlib import Path

array = []
mapSize = 0

file = open("mapPrep.txt", "r")
for line in file:
    mapSize += 1
    tempArray = []
    for char in line:
        tempArray.append(char)
    array.append(tempArray)

file.close()

img = Image.new( 'RGB', (mapSize,mapSize), "black")
pixels = img.load()

"""
for i in range(img.size[0]):
    for j in range(img.size[1]):
        pixels[i,j] = (i, j, 100)
"""

for i in range(len(array)):
    for j in range(len(array)):
        if int(array[i][j]) == 0:
            pixels[i,j] = (0, 255, 0)
        elif int(array[i][j]) == 1:
            pixels[i,j] = (140, 140, 140)
        elif int(array[i][j]) == 2:
            pixels[i, j] = (17, 77, 15)
        elif int(array[i][j]) == 3:
            pixels[i, j] = (0, 0, 255)
        elif int(array[i][j]) == 4:
            pixels[i, j] = (164, 115, 65)
        elif int(array[i][j]) == 6:
            pixels[i, j] = (226, 234, 27)


iterator = 0
mapName = "generatedMap"
newMapName = "generatedMap"

while True:
    path = Path(newMapName + str(".png"))

    if path.is_file():
        iterator += 1
        newMapName = mapName + str(iterator)
    else:
        img.save(newMapName + str(".png"))
        break