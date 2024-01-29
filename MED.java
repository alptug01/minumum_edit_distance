import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextArea;

public class MED {
	private int calculating_med(String word1,String word2) {
		Object[][] matrix=creating_first_matrix(word1,word2);
		for(int i=2;i<matrix.length;i++) {
			for(int j=2;j<matrix[i].length;j++) {
				if(matrix[i][0].equals(matrix[0][j])) {
					matrix[i][j]=matrix[i-1][j-1];
				}else {
					int min_value=Math.min((int)matrix[i-1][j-1], Math.min((int)matrix[i-1][j],(int) matrix[i][j-1]))+1;
					matrix[i][j]=min_value;
				}
			}
		}
		return (int) matrix[matrix.length-1][matrix[0].length-1];
	}
	public void alternative_correct_words(String word1, JTextArea resultArea) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("vocabulary_tr.txt"), Charset.forName("ISO-8859-9")));
			String line;
			ArrayList<String> alternative_words = new ArrayList<>();
			ArrayList<Integer> words_distances = new ArrayList<>();
			int max=Integer.MAX_VALUE;
	        while ((line = bufferedReader.readLine()) != null) {
	        	int distance=calculating_med(word1,line);	        	
	        	if(alternative_words.size()<5 || distance<Collections.max(words_distances)) {
	        		alternative_words.add(line);
	        		words_distances.add(distance);
	        		if(alternative_words.size()>5) {
	        			int remove_index=words_distances.indexOf(Collections.max(words_distances));
	        			alternative_words.remove(remove_index);
		        		words_distances.remove(remove_index);
	        		}
	        	}
	        }
	        for (int i = 0; i < alternative_words.size(); i++) {
	        	resultArea.append("Word: " + alternative_words.get(i) + ", Distance: " + words_distances.get(i) + "\n");	            
	        }
	        long endTime = System.currentTimeMillis();
	        long elapsedTime = endTime - startTime;
	        //System.out.println("Part1 execution time: " + elapsedTime + " milliseconds");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}
	public void first_to_sec(String word1,String word2, JTextArea resultAreaPart2) {
		long startTime = System.currentTimeMillis();
		Object[][] matrix=creating_first_matrix(word1,word2);
		Object[][] path_matrix = new Object[matrix.length][matrix[0].length];
		for(int i=2;i<matrix.length;i++) {
			for(int j=2;j<matrix[i].length;j++) {
				if(matrix[i][0].equals(matrix[0][j])) {
					matrix[i][j]=matrix[i-1][j-1];
					path_matrix[i][j]="Same";
				}else {
					int min_value=Math.min((int)matrix[i-1][j-1], Math.min((int)matrix[i-1][j],(int) matrix[i][j-1]));
					matrix[i][j]=min_value+1;
					if(min_value==(int)matrix[i-1][j-1]) {
						path_matrix[i][j]="Replace";
					}else if(min_value==(int)matrix[i-1][j]) {
						path_matrix[i][j]="Delete";
					}else {
						path_matrix[i][j]="Insert";
					}
				}
			}
		}
		print_matrix(matrix,resultAreaPart2);
		int i=path_matrix.length-1,j=path_matrix[0].length-1;
		while (i > 0 && j > 0) {
			Object value=path_matrix[i][j];
			if(value!=null) {
				if(value.equals("Same")) {
					i--;
					j--;
				}else if(value.equals("Replace")) {
					resultAreaPart2.append("Replace: "+matrix[i][0]+"->"+matrix[0][j]+"\n");
					i--;j--;
					
				}else if(value.equals("Delete")) {
					resultAreaPart2.append("Delete: "+matrix[i][0]+"\n");
					i--;
					
				}else if(value.equals("Insert")) {
					resultAreaPart2.append("Insert: "+matrix[0][j]+"\n");
					j--;
				}
			}else {
				if(j==1 && !matrix[i][0].equals("#")) {
					resultAreaPart2.append("Delete: "+matrix[i][0]+"\n");
					i--;
				}
				else if(i==1) {
					if(!matrix[0][j].equals("#")) {
						resultAreaPart2.append("Insert: "+matrix[0][j]+"\n");
						j--;
					}
					}
				break;
				
			}
			
		}
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        //System.out.println("Part 2 execution time: " + elapsedTime + " milliseconds");

	}
	private Object[][] creating_first_matrix(String word1,String word2) {
				//creating first matrix
				int len1=word1.length();int len2=word2.length();
				Object[][] matrix = new Object[len1+2][len2+2];
				matrix[0][0]=" ";matrix[0][1]="#";matrix[1][0]="#"; matrix[1][1]=0;
				for(int i=0;i<len2;i++) {
					char ch1=word2.charAt(i);
					matrix[0][2+i]=word2.charAt(i);
					matrix[1][2+i]=i+1;
				}
				for(int i=0;i<len1;i++) {
					char ch2=word1.charAt(i);
					matrix[2+i][0]=word1.charAt(i);
					matrix[2+i][1]=i+1;
				}
				return matrix;
		
	}
	
	private void print_matrix(Object[][] matrix, JTextArea resultAreaPart2) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == null) {
                    resultAreaPart2.append(" ");
                } else {
                    resultAreaPart2.append(matrix[i][j] + " ");
                }
            }
            resultAreaPart2.append("\n");
        }
        resultAreaPart2.append("\n\n");
    }
}
