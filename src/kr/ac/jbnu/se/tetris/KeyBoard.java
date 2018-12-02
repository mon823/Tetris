package kr.ac.jbnu.se.tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Key implements KeyListener {
	private Board board;
	private Board board2;
	private boolean playFlag = false;

	public Key(Board board) {
		this.board = board;
		board2 = new Board();
	}

	public Key(Board board, Board board2) {
		this.board = board;
		this.board2 = board2;
		playFlag = true;
	}

	public void keyPressed(KeyEvent e) {

		if (!board.isStarted || board.curPiece.getShape() == Tetrominoes.NoShape) {
			if(!board2.isStarted || board2.curPiece.getShape() == Tetrominoes.NoShape)
				return;
		}

		int keycode = e.getKeyCode();

		if (keycode == 'p' || keycode == 'P') {
			board.pause();
			board2.pause();
			
			return;
		}

		if (board.isPaused)
			return;

		if (playFlag == true) {

			switch (keycode) {
			case KeyEvent.VK_A:
				board2.tryMove(board2.curPiece, board2.curX - 1, board2.curY);
				break;
			case KeyEvent.VK_D:
				board2.tryMove(board2.curPiece, board2.curX + 1, board2.curY);
				break;
			case KeyEvent.VK_S:
				board2.tryMove(board2.curPiece.rotateRight(), board2.curX, board2.curY);
				board2.guideMove(board2.guidePiece.rotateRight(), board2.curX, board2.curY);
				board2.guideDown();
				break;
			case KeyEvent.VK_W:
				board2.tryMove(board2.curPiece.rotateLeft(), board2.curX, board2.curY);
				board2.guideMove(board2.guidePiece.rotateLeft(), board2.curX, board2.curY);
				board2.guideDown();
				break;
			case KeyEvent.VK_SHIFT:
				board2.dropDown();
				break;
			case KeyEvent.VK_0:
				board2.start();
				board2.backgroundMusic.Stop();
				board2.timer.start();
				break;
			case 'q':
				board2.oneLineDown();
				break;
			case 'Q':
				board2.oneLineDown();
				break;
			case KeyEvent.VK_LEFT:
				board.tryMove(board.curPiece, board.curX - 1, board.curY);
				break;
			case KeyEvent.VK_RIGHT:
				board.tryMove(board.curPiece, board.curX + 1, board.curY);
				break;
			case KeyEvent.VK_DOWN:
				board.tryMove(board.curPiece.rotateRight(), board.curX, board.curY);
				board.guideMove(board.guidePiece.rotateRight(), board.curX, board.curY);
				board.guideDown();
				break;
			case KeyEvent.VK_UP:
				board.tryMove(board.curPiece.rotateLeft(), board.curX, board.curY);
				board.guideMove(board.guidePiece.rotateLeft(), board.curX, board.curY);
				board.guideDown();
				break;
			case KeyEvent.VK_SPACE:
				board.dropDown();
				break;
			case KeyEvent.VK_9:
				board.start();
				board.backgroundMusic.Stop();
				board.timer.start();
				break;
			case 'm':
				board.oneLineDown();
				break;
			case 'M':
				board.oneLineDown();
				break;

			}

		} 
		else {
			switch (keycode) {
			case KeyEvent.VK_LEFT:
				board.tryMove(board.curPiece, board.curX - 1, board.curY);
				break;
			case KeyEvent.VK_RIGHT:
				board.tryMove(board.curPiece, board.curX + 1, board.curY);
				break;
			case KeyEvent.VK_DOWN:
				board.tryMove(board.curPiece.rotateRight(), board.curX, board.curY);
				board.guideMove(board.guidePiece.rotateRight(), board.curX, board.curY);
				board.guideDown();
				break;
			case KeyEvent.VK_UP:
				board.tryMove(board.curPiece.rotateLeft(), board.curX, board.curY);
				board.guideMove(board.guidePiece.rotateLeft(), board.curX, board.curY);
				board.guideDown();
				break;
			case KeyEvent.VK_SPACE:
				board.dropDown();
				break;
			case KeyEvent.VK_0:
				board.start();
				board.backgroundMusic.Stop();
				board.timer.start();
				break;
			case 'm':
				board.oneLineDown();
				break;
			case 'M':
				board.oneLineDown();
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
}