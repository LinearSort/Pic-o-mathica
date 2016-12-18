package fromPictureToLaTeX;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static int c = 80;
	public static int d = 2;
	public static int e = d+1; 

	public static boolean isInsideMatrix(int x, int y, boolean[][] matrix) {
		return x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length && matrix[x][y] == true;
	}

	public static boolean isBlack(int i, int j, PixMatrix matrix) {
		return matrix.cells[i][j].getRed() < c && matrix.cells[i][j].getGreen() < c && matrix.cells[i][j].getBlue() < c;
	}

	private static void dfs(boolean[][] matrix, int x, int y, PrintWriter writer) {

		for (int i = -d; i <= d; i++) {
			for (int j = -d; j <= d; j++) {
				matrix[x + i][y + j] = false;
			}
		}

		for (int i = -e; i <= e; i++) {
			for (int j = -e; j <= e; j++) {
				if (isInsideMatrix(x + i, y + j, matrix)) {
					dfs(matrix, x + i, y + j, writer);
				}
			}
		}

		writer.print(y + " " + x + ", ");
	}

	public static void bfs(boolean[][] matrix, int x, int y, PrintWriter writer) {

		Queue<Pair> q = new LinkedList<>();

		q.add(new Pair(x, y));
		writer.print(y + " " + x);
		matrix[x][y] = false;

		// for (int i = -1; i <= 1; i++) {
		// for (int j = -1; j <= 1; j++) {
		// matrix[x + i][y + j] = false;
		// }
		// }
		//
		// while (!q.isEmpty()) {
		// Pair p = q.poll();
		// x = p.x;
		// y = p.y;
		//
		// for (int i = -2; i <= 2; i++) {
		// for (int j = -2; j <= 2; j++) {
		//
		// if (isInsideMatrix((x + i), (y + j), matrix)) {
		// q.add(new Pair(x + i, (y + j)));
		// writer.print(", " + (y + j) + " " + (x + i));
		//
		// for (int k = -1; k <= 1; k++) {
		// for (int l = -1; l <= 1; l++) {
		// matrix[x + i + k][y + j + l] = false;
		// }
		// }
		//
		// }
		// }
		// }
		// }
		while (!q.isEmpty()) {
			Pair p = q.poll();
			x = p.x;
			y = p.y;

			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {

					if (isInsideMatrix(x + i, y + j, matrix)) {
						q.add(new Pair(x + i, y + j));
						writer.print(", " + (y + j) + " " + (x + i));
						matrix[x + i][y + j] = false;
					}

				}
			}

			// if (isInsideMatrix(x, y - 1, matrix)) {
			// q.add(new Pair(x, y - 1));
			// writer.print(", " + (y - 1) + " " + x);
			// matrix[x][y - 1] = false;
			// }
			// if (isInsideMatrix(x, y + 1, matrix)) {
			// q.add(new Pair(x, y + 1));
			// writer.print(", " + (y + 1) + " " + x);
			// matrix[x][y + 1] = false;
			// }
			// if (isInsideMatrix(x - 1, y, matrix)) {
			// q.add(new Pair(x - 1, y));
			// writer.print(", " + y + " " + (x - 1));
			// matrix[x - 1][y] = false;
			// }
			// if (isInsideMatrix(x + 1, y, matrix)) {
			// q.add(new Pair(x + 1, y));
			// writer.print(", " + y + " " + (x + 1));
			// matrix[x + 1][y] = false;
			// }
		}
		writer.println();
		writer.flush();

	}

	public static void main(String[] args) {

		PixMatrix matrix = new PixMatrix();
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("/home/geshh/code/seshat/SampleMathExps/boolMatrix.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		matrix.printMatrixToFile();
		boolean[][] boolMatrix = new boolean[matrix.getHeight()/2+1][matrix.getWidth()/2+1];

		for (int i = 0; i < matrix.getHeight(); i += 2) {

			for (int j = 0; j < matrix.getWidth(); j += 2) {

				if (isBlack(i, j, matrix)) {

					boolMatrix[i/2][j/2] = true;
				}
				if (boolMatrix[i/2][j/2] == true) {
					writer.print(1);
					writer.flush();
				} else {
					writer.print(0);
					writer.flush();
				}
			}
			writer.println();
		}

		try {
			writer = new PrintWriter("/home/geshh/code/seshat/SampleMathExps/strokeMatrix.inkml", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writer.println("<ink>\n");
		writer.flush();

		for (int i = 0; i < boolMatrix[0].length; i++) {
			for (int j = 0; j < boolMatrix.length; j++) {
				if (boolMatrix[j][i] == true) {
					writer.println("<trace>");
					writer.flush();
					dfs(boolMatrix, j, i, writer);
					// bfs(boolMatrix, j, i, writer);
					writer.println("</trace>\n");
					writer.flush();
				}
			}
		}
		writer.println("</ink>");
		writer.flush();
		writer.close();

	}
}
