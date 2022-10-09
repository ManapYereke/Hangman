import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class HangmanFrame extends JFrame implements ActionListener {// Класс игрового окна

	private final int weight = 600;// ширина в пикселях
	private final int height = 600;// высота
	private final int xLocation = 350;// х-координата окна в пикселях
	private final int yLocation = 100;// у-координата
	private JTextField textField = new JTextField(1);// Текстовое поле
	private JButton exitButton = new JButton("Выйти");// кнопка выхода
	private JButton playAgain = new JButton("Начать заново");;
	private JButton check = new JButton("Ввести");
	private JButton authors = new JButton("Авторы");
	private JButton rules = new JButton("Правила игры");
	private JPanel centerPanel = new JPanel();// Главная панель
	private GamePanel gamePanel;// игровая панель
	private JPanel southPanel = new JPanel();// Панель кнопок
	private String instruction = "Вам дается 6 шансов, чтобы отгадать слово по определенной\n тематике. Если не угадаете, вы проиграли";// Правила
																																		// игры
	private int numLive = 6;// количество шансов
	private HangmanAction h = new HangmanAction();// Объект класса HangmanAction
	private String word = h.generateWord();// Сгенерированное слово
	private String hint = h.hint();// Подсказка
	private String findedWord = String.valueOf(h.letter);// Найденные буквы
	private String message = "";// Сообщения
	private String usedLetters = "";// Использованные символы

	public HangmanFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Для выхода при нажатий на х
		setSize(weight, height);// установка размеров
		setLocation(xLocation, yLocation);// установка координат
		setTitle("Игра Виселица");// Название окна
		setBackground(Color.white);// Белый фон
		setLayout(new BorderLayout());
		gamePanel = new GamePanel(numLive, findedWord, hint, message);
		centerPanel.setSize(weight, height);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.add(gamePanel);// добаляем игровую панель в центральную
		add(centerPanel, BorderLayout.CENTER);
		textField.addActionListener(this);// активируем текстовое поле при определенных действиях
		exitButton.addActionListener(this);// то же самое
		playAgain.addActionListener(this);
		check.addActionListener(this);
		authors.addActionListener(this);
		rules.addActionListener(this);
		southPanel.setBackground(Color.BLACK);
		southPanel.setLayout(new GridLayout(0, 3));
		southPanel.add(playAgain);// добавляем кнопки в панель кнопок
		southPanel.add(textField);
		southPanel.add(check);
		southPanel.add(exitButton);
		southPanel.add(authors);
		southPanel.add(rules);
		add(southPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		int k = 4;// для вывода сообщения
		String str = textField.getText();// Полученный текст из текстового поля
		if (textField.getText().length() > 1) {
			textField.setText(String.valueOf(str.charAt(0)));// если количество символов больше 1, оставляем только один
																// символ
		}
		if (evt.getSource() == check || str.length() == 1) {// при нажатии на check или enter, если длина строки равна 1
			numLive = h.getChance(word, str);// получение значения количества попыток
			textField.setText("");// очистка текстового поля
			findedWord = String.valueOf(h.seeWord(word, str));// найденные символы
			String finalWord = "";// для проверки победы
			for (int i = 0; i < findedWord.length(); i++)
				if (findedWord.charAt(i) != '_' && findedWord.charAt(i) != ' ')
					finalWord += findedWord.charAt(i);
			if (usedLetters.contains(str))// для вывода сообщения, если 1 символ вести повторно
				k = 2;
			if (!h.checkInput(str))// для вывода сообщения при вводе латиницы или других символов
				k = 3;
			if (finalWord.toLowerCase().equals(word.toLowerCase()))// для объявления победы
				k = 1;
			if (numLive <= 0)// для объявления проигрыша
				k = 0;
			message = h.getMessage(k);// сообщение в зависимости от k
			gamePanel = new GamePanel(numLive, findedWord, hint, message);// обновляем игровое поле
			centerPanel.removeAll();// очистка центральной панели
			centerPanel.add(gamePanel);// добавление игрового поля
			centerPanel.revalidate();// перерисовка
			usedLetters += str;// обновляем использованные символы
			if (k == 1 || k == 0) {
				check.setEnabled(false);// если выиграл или проиграл, отключаем кнопку ввода
				textField.setEnabled(false);// если выиграл или проиграл, отключаем текстовое поле
			}
		}
		if (evt.getSource() == exitButton) {
			System.exit(0);// для выхода при нажатий на exit
		}
		if (evt.getSource() == playAgain) {// для повторной игры, здесь происходит обнуление
			centerPanel.removeAll();
			h = new HangmanAction();// новый объект
			word = h.generateWord();// новое слово
			hint = h.hint();
			findedWord = String.valueOf(h.letter);
			numLive = 6;
			message = "";
			usedLetters = "";
			gamePanel = new GamePanel(numLive, findedWord, hint, message);
			centerPanel.add(gamePanel);
			centerPanel.revalidate();
			check.setEnabled(true);
			textField.setEnabled(true);
		}
		if (evt.getSource() == authors) {
			JOptionPane.showMessageDialog(null, "Манап Ербұлан", "Авторы", JOptionPane.PLAIN_MESSAGE);// вывод окна при нажатий
																								// на authors
		}
		if (evt.getSource() == rules) {
			JOptionPane.showMessageDialog(null, instruction, "Правила игры", JOptionPane.PLAIN_MESSAGE);// вывод окна
																										// при нажатий
																										// на rules
		}
	}
}
