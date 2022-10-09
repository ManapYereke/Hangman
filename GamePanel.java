import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private String hint;
	private Font font = new Font("Times New Roman", Font.BOLD, 25);// шрифт
	private Font font2 = new Font("Times New Roman", Font.BOLD, 14);
	private int numLive;
	private String word;
	private String message;

	public GamePanel(int numLive, String word, String hint, String message) {// конструктор
		super();
		this.numLive = numLive;
		this.word = word;
		this.hint = hint;
		this.message = message;
		setPreferredSize(new Dimension(500, 500));// размер игровой панели
	}

	public void paintComponent(Graphics g) {// метод рисования
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5.0f));// толщина
		g2.setColor(Color.black);// цвет
		g2.drawLine(10, 400, 90, 400);// нижняя опора
		g2.drawLine(50, 400, 50, 100);// столб
		g2.drawLine(50, 100, 170, 100);// виселица
		g2.drawLine(150, 100, 150, 150);// веревка
		g2.setFont(font);
		g2.drawString(hint, 100, 50);// подсказка
		g2.drawString(word, 20, 450);// слово
		g2.setFont(font2);
		if (message != null)
			g2.drawString(message, 20, 480);// сообщение
		if (numLive < 6) {
			g2.drawOval(130, 150, 40, 40);// рисуем голову
			g2.drawLine(140, 165, 145, 165);// левый глаз
			g2.drawLine(155, 165, 160, 165);// правый глаз
			g2.drawLine(145, 180, 155, 180);// рот
		}
		if (numLive < 5) {
			g2.drawLine(150, 190, 150, 200);// шея
			g2.drawOval(130, 200, 40, 100);// корпус
		}
		if (numLive < 4) {
			g2.drawLine(163, 210, 200, 260);// правая рука
		}
		if (numLive < 3) {
			g2.drawLine(137, 210, 100, 260);// левая рука
		}
		if (numLive < 2) {
			g2.drawLine(155, 297, 190, 370);// правая нога
			g2.drawLine(190, 370, 200, 360);
		}
		if (numLive < 1) {
			g2.drawLine(145, 297, 110, 370);// левая нога
			g2.drawLine(110, 370, 100, 360);
		}
	}
}