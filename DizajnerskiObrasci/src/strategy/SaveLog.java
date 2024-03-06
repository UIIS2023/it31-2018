package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import mvc.DrawingFrame;

public class SaveLog implements Save {

	
	
	
	@Override
	public void save(Object o, File f) {
		DrawingFrame frame = (DrawingFrame) o;
		
		
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f,false));
			
			for (Object logElement : frame.getLogListModel().toArray()) {
				String element = (String)logElement+"\n";
				bufferedWriter.append(element);
			}
			bufferedWriter.flush();
			bufferedWriter.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
