#coding=utf-8
import cv2  
import numpy as np
from sys import argv

sourceImage = argv[1]
subImage = argv[2]

class ImageMatcher:

    def __init__(self,sourceimg):
        self.sourceimg = sourceimg

    def find(self,templateimg,threshold=0.8):
        image = cv2.imread(self.sourceimg)  
        template = cv2.imread(templateimg)  
        result = cv2.matchTemplate(image,template,cv2.cv.CV_TM_CCOEFF_NORMED) 
        similarity = cv2.minMaxLoc(result)[1]
        if similarity < threshold:
            return "fail"
        else:
            return "ok"

if __name__=="__main__":
    matcher = ImageMatcher(sourceImage)
    print matcher.find(subImage)