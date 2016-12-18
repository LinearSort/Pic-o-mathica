import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

public class PixMatrix {

	public Cell[][] cells;
	public int height;
	public int width;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public PixMatrix() {
		try {
			BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir") + "/src/main/resources/images/1.jpg"));

			this.width = image.getWidth();
			this.height = image.getHeight();

			cells = new Cell[height][width];

			for (int i = 0; i < height; i++) {

				for (int j = 0; j < width; j++) {
					int color = image.getRGB(j, i);
					
					int red = (color & 0x00ff0000) >> 16;
					int green = (color & 0x0000ff00) >> 8;
					int blue = color & 0x000000ff;

					Cell newCell = new Cell(red, green, blue);
					addToMatrix(i, j, newCell);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addToMatrix(int x, int y, Cell cell) {
		this.cells[x][y] = cell;
	}

	@SuppressWarnings("resource")
	public void printMatrixToFile() {
		try {
			PrintWriter writer = new PrintWriter("MATRIXTOFILE.txt", "UTF-8");

			for (int i = 0; i < height; i++) {
				writer.println();
				for (int j = 0; j < width; j++) {
					Cell curCell = cells[i][j];
					writer.print(curCell.getRed() + " " + curCell.getGreen() + " " + curCell.getBlue() + " | ");
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
