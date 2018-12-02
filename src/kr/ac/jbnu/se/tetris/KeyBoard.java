package kr.ac.jbnu.se.tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Key extends SettingKey implements KeyListener {

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

		KeyEvent key = e;

		if (!board.isStarted || board.curPiece.getShape() == Tetrominoes.NoShape) {
			if (!board2.isStarted || board2.curPiece.getShape() == Tetrominoes.NoShape)
				return;
		}

		int keycode = e.getKeyCode();

		if (keycode == pauseKey || keycode == pauseKey+30) {//대문자와 소문자의 차이는 아스키 코드로 30!
			board.pause();
			board2.pause();

			return;
		}

		if (board.isPaused)
			return;

		if (playFlag == true) {

			play2KeyCode(keycode);
			play1KeyCode(keycode);

		}

		else {

			play1KeyCode(keycode);

		}
	}

	private void play1KeyCode(int keycode) {
		if (keycode == leftKey) {
			board.tryMove(board.curPiece, board.curX - 1, board.curY);

		}

		if (keycode == rightKey) {
			board.tryMove(board.curPiece, board.curX + 1, board.curY);
		}
		if (keycode == downKey) {
			board.tryMove(board.curPiece.rotateRight(), board.curX, board.curY);
			board.guideMove(board.guidePiece.rotateRight(), board.curX, board.curY);
			board.guideDown();
		}

		if (keycode == upKey) {
			board.tryMove(board.curPiece.rotateLeft(), board.curX, board.curY);
			board.guideMove(board.guidePiece.rotateLeft(), board.curX, board.curY);
			board.guideDown();
		}
		if (keycode == lineDownKey) {
			board.dropDown();
		}

		if (keycode == KeyEvent.VK_9) {
			board.start();
			board.backgroundMusic.Stop();
			board.timer.start();
		}

		if (keycode == oneLineDownKey||keycode == oneLineDownKey+32) {
			board.oneLineDown();
		}
	}

	private void play2KeyCode(int keycode) {
		if (keycode == leftKeyTwo) {
			board2.tryMove(board2.curPiece, board2.curX - 1, board2.curY);
		}
		if (keycode == rightKeyTwo) {
			board2.tryMove(board2.curPiece, board2.curX + 1, board2.curY);
		}

		if (keycode == downKeyTwo) {
			board2.tryMove(board2.curPiece.rotateRight(), board2.curX, board2.curY);
			board2.guideMove(board2.guidePiece.rotateRight(), board2.curX, board2.curY);
			board2.guideDown();
		}

		if (keycode == upKeyTwo) {
			board2.tryMove(board2.curPiece.rotateLeft(), board2.curX, board2.curY);
			board2.guideMove(board2.guidePiece.rotateLeft(), board2.curX, board2.curY);
			board2.guideDown();
		}

		if (keycode == lineDownKeyTwo) {
			board2.dropDown();
		}

		if (keycode == KeyEvent.VK_0) {
			board2.start();
			board2.backgroundMusic.Stop();
			board2.timer.start();
		}

		if (keycode == oneLineDownKeyTwo||keycode == oneLineDownKeyTwo+32) {
			board2.oneLineDown();
		}
	}

}