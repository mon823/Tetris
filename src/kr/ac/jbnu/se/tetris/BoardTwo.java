package kr.ac.jbnu.se.tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JPanel;

public class BoardTwo extends JPanel {//실제로 그려주는 패널
	// 여기 패널에다가 그려줌

	final protected int BoardWidth = 10; // 가로 10칸
	final protected int BoardHeight = 22; // 세로 10칸
	public Shape curPiece;
	public Shape guidePiece;
	protected Tetrominoes[] board;
	protected Tetrominoes[] cpBoard = new Tetrominoes[BoardWidth * BoardHeight];
	private boolean isFallingFinished = false;
	protected boolean isStarted = false;
	static protected boolean isPaused = false;
	static protected boolean isReverse = false;
	public int curX = 0;
	public int curY = 0;
	public int guideX = 0;
	public int guideY = 0;
	static protected int top = 0;
	protected Tetris tetris;
	
	//컬러
	private Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), new Color(102, 204, 102),
			new Color(102, 102, 204), new Color(204, 204, 102), new Color(204, 102, 204), new Color(102, 204, 204),
			new Color(218, 170, 0), new Color(192, 192, 192) };

	private Color colorsguides[] = { new Color(0, 0, 0, 122), new Color(204, 102, 102, 122),
			new Color(102, 204, 102, 122), new Color(102, 102, 204, 122), new Color(204, 204, 102, 122),
			new Color(204, 102, 204, 122), new Color(102, 204, 204, 122), new Color(218, 170, 0, 122),
			new Color(192, 192, 192, 122) };

	public BoardTwo(Tetris tetris) {
		this.tetris = tetris;
		tetris.add(this);

	}

	protected void drawSquare(Graphics g, int x, int y, Tetrominoes shape, boolean k) {

		Color color = colors[shape.ordinal()];
		if (k == true) {
			color = colorsguides[shape.ordinal()];
		}
		g.setColor(color);
		g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

		g.setColor(color.brighter());
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);

	}

	// 페인트
	public void paint(Graphics g) {
		super.paint(g);

		Dimension size = getSize();

		int backGroundColor;
		int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();

		backGroundColor = (top * 10) + 20;// 백그라운드 컬러바꾸기
		g.setColor(new Color(backGroundColor, 0, 0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		System.arraycopy(board, 0, cpBoard, 0, board.length);// 배열복사
		reverse(cpBoard);

		for (int i = 0; i < BoardHeight; ++i) {

			for (int j = 0; j < BoardWidth; ++j) {

				if (isReverse == true) {

					Tetrominoes shape = shapeAtReverse(j, BoardHeight - i - 1);

					if (shape != Tetrominoes.NoShape)
						drawSquare(g, 0 + j * squareWidth(), i * squareHeight() + boardTop, shape);
				} else {
					Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
					if (shape != Tetrominoes.NoShape)
						drawSquare(g, 0 + j * squareWidth(), i * squareHeight() + boardTop, shape);
				} // <- 여기도 중복 줄일 수 있지 않을까

			}
		}

		if (curPiece.getShape() != Tetrominoes.NoShape) {

			int x;
			int y;

			for (int i = 0; i < 4; ++i) {

				if (isReverse == true) {
					x = BoardWidth - 1 - (curX + curPiece.x(i));
					y = BoardHeight - 1 - (curY - curPiece.y(i));
				} else {
					x = curX + curPiece.x(i);
					y = curY - curPiece.y(i);
				}

				drawSquare(g, 0 + x * squareWidth(), boardTop + (BoardHeight - y - 1) * squareHeight(),
						curPiece.getShape());
			}
		}

		// 가이드 좌표 찍어주는 거일꺼야 아마...
		if (guidePiece.getShape() != Tetrominoes.NoShape) {

			int x;
			int y;

			for (int i = 0; i < 4; ++i) {

				if (isReverse == true) {
					x = BoardWidth - 1 - (guideX + guidePiece.x(i));
					y = BoardHeight - 1 - (guideY - guidePiece.y(i));
				} else {
					x = guideX + guidePiece.x(i);
					y = guideY - guidePiece.y(i);// 가이드좌표 찍어주는거
				}

				drawSquare(g, 0 + x * squareWidth(), boardTop + (BoardHeight - y - 1) * squareHeight(),
						guidePiece.getShape(), true);

			}
		}
	}

	protected void drawSquare(Graphics g, int x, int y, Tetrominoes shape) {
		this.drawSquare(g, x, y, shape, false);
	}

	protected int squareWidth() {
		return (int) getSize().getWidth() / BoardWidth;

	}

	protected int squareHeight() {
		return (int) getSize().getHeight() / BoardHeight;
	}

	protected static <T> void reverse(Tetrominoes[] cpBoard) {
		Collections.reverse(Arrays.asList(cpBoard));
	}

	protected Tetrominoes shapeAt(int x, int y) {

		return board[(y * BoardWidth) + x]; // board 가 순서대로 X 축으로 늘어나면서 세로축값이 2차로 증가

	}

	protected Tetrominoes shapeAtReverse(int x, int y) {
		return cpBoard[(y * BoardWidth) + x]; // board 가 순서대로 X 축으로 늘어나면서 세로축값이 2차로 증가

	}

	protected void findTop() {
		for (int i = BoardHeight - 1; i >= 0; --i) {
			for (int j = 0; j < BoardWidth; ++j) {
				if (shapeAt(j, i) != Tetrominoes.NoShape) {
					top = i;
					i = 0;
					break;
				}
			}
		}
	}

}
