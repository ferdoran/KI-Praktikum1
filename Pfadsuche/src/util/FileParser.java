/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import data.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roland
 */
public class FileParser {
    
    FileReader fr;
    BufferedReader br;
    
    public FileParser(String filepath){
        try {
            fr = new FileReader(filepath);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String readLine() throws IOException {
        return br.readLine();
    }
    
    public Point returnPoint() {
        
        try {
            String line = readLine();
            if(!line.contains(",")) {
                return null;
            }
            int comma = line.indexOf(",");
            double x = Double.parseDouble(line.substring(1, comma-1));
            double y = Double.parseDouble(line.substring(comma+1, line.length()-1));
            return new Point(x,y);
        } catch (IOException ex) {
            Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }
    
}
