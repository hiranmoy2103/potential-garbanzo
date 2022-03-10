## I have taken below assumptions while in this assignment
1. This word search has been tested only using .txt files in different directories, other file format may not be supported.

2. Upon successful search , only list of file names where searched word is found will be returned by the API.
e.g. Only list of file names will be returned by the API. Full path can also be returned but I have only kept file names.
   [
   "h1_test.txt",
   "U12_11.txt"
   ]
3. Multiple words will be separated by space in the url. In the below example 2 words are being passed as path variable.

**e.g. http://localhost:8091/search/Tuhin Hiranmoy**

4. The search is case-insensitive , i.e. 'inferior' , 'Inferior' ,'InfErIor' will be treated as same.

5. Only full word can be searched , e.g. if I search "cell" word , the api will look for exact "cell" word
in the files , even if any file contains word "excellent" (has "cell" in it) the search will fail.

6. In the controller I have used base path as my local pc path. Please change this path according to your computer while testing,
**private static String basDirectoryPath = "C:\\Users\\HiranmoyBiswas\\Desktop\\HB_Profile\\Test";**

7. **End Point :  http://localhost:8091/search/{word1 word2 word3 ...}**

## END , Thanks