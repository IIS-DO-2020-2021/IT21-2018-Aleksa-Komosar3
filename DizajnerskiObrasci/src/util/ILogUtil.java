package util;

import java.util.Scanner;

import geometry.Shape;

public interface ILogUtil {
	public void loadFileByLoadingType(int type, Scanner file);
	public void executeLineLog(String line);
	public Shape getShapesFromLog(String line, String stringShape, Boolean second);
}
