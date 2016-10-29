/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonread;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdul Qadir
 */
public class wLadder {
     String tStart;
    List<String> mPath=new ArrayList<>();

    public wLadder(String tStart) {
        this.tStart = tStart;
    }
    public wLadder(String tStart,wLadder bigPath)
    {
        this.tStart = tStart;
        mPath.add(bigPath.tStart);
        mPath.addAll(bigPath.mPath);
    }
    @Override
    public String toString() {
        return "\n" + tStart +"    "  + mPath+ "";
    }
    
    
    
    
    
}
