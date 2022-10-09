
public class HangmanAction {
	private int numLives = 6;// Количество попыток
	private String[][] words = new String[][] { { "Компьютер", "Ноутбук", "Планшет", "Смартфон", "Моноблок" }, // Слова
																												// для
																												// загадки
			{ "Инженер", "Программист", "Архитектор", "Электрик", "Журналист" },
			{ "Футбол", "Волейбол", "Баскетбол", "Плавание", "Бокс" },
			{ "Казахстан", "Россия", "Бангладеш", "Палестина", "Турция" },
			{ "Рубашка", "Брюки", "Футболка", "Свитер", "Джемпер" } };
	private int theme = (int) (Math.random() * words.length);// тематика
	private int word = (int) (Math.random() * words[theme].length);// слово
	char[] letter = new char[words[theme][word].length() * 2];// для вывода отгаданных букв
	private String usedChars = "";// использованные символы

	public String generateWord() {// генерация слова
		for (int i = 0; i < letter.length; i++) {
			if (i % 2 == 0)
				letter[i] = '_';
			else
				letter[i] = ' ';
		}
		return words[theme][word];
	}

	public String hint() {// Возвращает подсказку
		if (theme == 0) {
			return "Девайс:";
		}
		if (theme == 1) {
			return "Профессия:";
		}
		if (theme == 2) {
			return "Спорт:";
		}
		if (theme == 3) {
			return "Страна:";
		} else {
			return "Одежда:";
		}
	}

	public boolean checkInput(String c) {// проверка правильности ввода
		String alphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
		int k = 0;
		for (int i = 0; i < alphabet.length(); i++) {
			if (String.valueOf(alphabet.charAt(i)).equals(c.toLowerCase())) {
				k = 1;
			}
		}
		if (k == 1) {
			return true;
		} else {
			return false;
		}
	}

	public int getChance(String word, String c) {// Получение значения количества попыток
		if (checkInput(c) && !word.toLowerCase().contains(c.toLowerCase())
				&& !usedChars.toLowerCase().contains(c.toLowerCase())) {
			numLives--;
		}
		usedChars += c;
		return numLives;
	}

	public char[] seeWord(String word, String c) {// возвращает отгаданные символы
		for (int i = 0; i < word.length(); i++) {
			if (c.toLowerCase().charAt(0) == word.toLowerCase().charAt(i)) {
				letter[i * 2] = c.toLowerCase().charAt(0);
			}
		}
		return letter;
	}

	public String getMessage(int k) {// возвращает сообщение
		if (k == 2) {
			return "Вы уже вводили эту букву";
		}
		if (k == 0) {
			return "Игра окончена, вы проиграли! Это слово: " + generateWord();
		}
		if (k == 1) {
			return "Вы выиграли!";
		}
		if (k == 3) {
			return "Можно вводить только русские буквы!";
		} else {
			return null;
		}
	}
}
