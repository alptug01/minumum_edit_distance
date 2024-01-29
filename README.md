Algorithm
The function creating_first_matrix(String w1, String w2) is responsible for generating the initial matrix that we will use for minimum edit distance. The letters of the words are placed in the first row and first column of the matrix. Then, in the second row and second column, numbers starting from 1 are written up to the length of the words. This function returns this matrix. 
Another private function, calculating_med(String word1, String word2), initially sends the basic matrix to the creating_first_matrix function to obtain it. It then fills in the remaining empty spaces in the matrix. If the corresponding letters of two words are the same, whatever is in the diagonal is written. If they are different, the function looks to the right, left, and diagonally of the matrix element, and the smallest value is placed in the matrix with 1 added. This function returns the completed matrix.

The function where the Part 1 operations are performed is alternative_correct_words(String word1, JTextArea resultArea). In this function, two array lists, alternative_words, and distances, are opened. While reading from the text file, the input taken from the user is sent to the calculating_med() function for distance calculation. If alternative_words has fewer than 5 elements or the calculated distance d is smaller than the current maximum distance, then the following steps are taken: First, the relevant values are added to alternative_words and words_distances. If the list of alternative words has more than 5 elements, it determines the index of the one with the highest edit distance, removes that word, and its distance from the lists. Then, printing operations are performed.

The function where Part 2 is performed is first_to_sec(String word1, String word2, JTextArea resultAreaPart2). It is sent to creating_first_matrix to create the basic form of the matrix. A path_matrix of the same length as this matrix is created. The process of filling the matrix is done in a similar way, additionally, if the letters are equal, the path_matrix is filled with the value "Same". In cases where they are not equal, again, the elements around the matrix element are examined. If the smallest value is in the diagonal, the path_matrix is filled with "Replace", if above it's "Delete", if to the left it's "Insert". Then, a while loop is opened, and the values of i and j are defined to look at the path_matrix. Starting from the bottom-right corner of the matrix, values are decreased. According to the values of insert, delete, and replace, they are printed to the screen.




Screenshots of Test Results
Running Time:
 ![image](https://github.com/alptug01/minumum_edit_distance/assets/102905961/36a15f56-1130-4d2a-bafc-4e749a06e004)

Part 1:
 ![image](https://github.com/alptug01/minumum_edit_distance/assets/102905961/86b17824-2150-46a3-9b7e-e0deb512bacc)

![image](https://github.com/alptug01/minumum_edit_distance/assets/102905961/321bda54-cd3f-4260-9cca-ddb6a01c717b)

 

 

 

 

Part 2:
 ![image](https://github.com/alptug01/minumum_edit_distance/assets/102905961/ef056c87-a5a4-4cd7-b018-f1571a5a6b30)

 ![image](https://github.com/alptug01/minumum_edit_distance/assets/102905961/d80483ba-9c97-42e0-b26c-ad243bf52667)


 



