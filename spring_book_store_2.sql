-- phpMyAdmin SQL Dump
-- version 4.4.15.7
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1:3306
-- Время создания: Сен 30 2016 г., 15:41
-- Версия сервера: 5.5.50
-- Версия PHP: 5.4.45

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `spring_book_market`
--

-- --------------------------------------------------------

--
-- Структура таблицы `author`
--

CREATE TABLE IF NOT EXISTS `author` (
  `id` bigint(20) NOT NULL,
  `fio` varchar(300) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `author`
--

INSERT INTO `author` (`id`, `fio`) VALUES
(1, 'Yakov Fain'),
(2, 'Крейг Уоллс'),
(3, 'Vishal Layka'),
(4, 'Дебу Панда'),
(5, 'Энтони Гонсалвес'),
(6, 'Kishori Sharan'),
(7, 'Тимур Машнин'),
(8, 'Девид Хеффельфингер');

-- --------------------------------------------------------

--
-- Структура таблицы `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `content` longblob,
  `page_count` int(11) NOT NULL,
  `isbn` varchar(100) DEFAULT NULL,
  `genre_id` bigint(20) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `publish_year` int(11) NOT NULL,
  `publisher_id` bigint(20) NOT NULL,
  `image` longblob,
  `descr` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `book`
--

INSERT INTO `book` (`id`, `name`, `quantity`, `price`, `content`, `page_count`, `isbn`, `genre_id`, `author_id`, `publish_year`, `publisher_id`, `image`, `descr`) VALUES
(1, 'Java® Programming 24-Hour Trainer', 100, '36.00', NULL, 506, '978-0-470-88964-0', 1, 1, 2011, 3, NULL, NULL),
(2, 'Spring в действии', 50, '39.99', NULL, 752, '978-5-94074-568-6', 1, 2, 2013, 1, NULL, NULL),
(3, 'Learn Java For Web Development', 150, '37.50', NULL, 461, NULL, 1, 3, 2014, 2, NULL, NULL),
(4, 'EJB 3 в действии', 200, '38.99', NULL, 618, '78-5-97060-135-8', 1, 4, 2015, 1, NULL, NULL),
(5, 'Изучаем Java EE 7', 75, '32.00', NULL, 640, NULL, 1, 4, 2014, 5, NULL, NULL),
(6, 'Learn JavaFX 8: Building User Experience and Interfaces with Java 8', 30, '35.40', NULL, 1210, '978-1-4842-1143-4', 1, 6, 2015, 1, NULL, NULL),
(7, 'JavaFX 2.0: разработка RIA-приложений', 50, '29.99', NULL, 723, '978-5-9775-0820-9', 1, 7, 2012, 4, NULL, NULL),
(8, 'Java EE 6 и сервер приложений GlassFish 3', 23, '26.75', NULL, 416, NULL, 1, 8, 2013, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `genre`
--

CREATE TABLE IF NOT EXISTS `genre` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `parent` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `genre`
--

INSERT INTO `genre` (`id`, `name`, `parent`) VALUES
(1, 'Programming', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `publisher`
--

CREATE TABLE IF NOT EXISTS `publisher` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `publisher`
--

INSERT INTO `publisher` (`id`, `name`) VALUES
(1, 'ДМК'),
(2, 'Apress'),
(3, 'Wrox'),
(4, 'БХВ-Петербург'),
(5, 'Питер');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD UNIQUE KEY `isbn_UNIQUE` (`isbn`),
  ADD KEY `fk_author_idx` (`author_id`),
  ADD KEY `fk_genre_idx` (`genre_id`),
  ADD KEY `fk_publiher_idx` (`publisher_id`);

--
-- Индексы таблицы `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_parent_idx` (`parent`);

--
-- Индексы таблицы `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `author`
--
ALTER TABLE `author`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT для таблицы `book`
--
ALTER TABLE `book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT для таблицы `genre`
--
ALTER TABLE `genre`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `publisher`
--
ALTER TABLE `publisher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `fk_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_genre` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_publisher` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `genre`
--
ALTER TABLE `genre`
  ADD CONSTRAINT `fk_parent` FOREIGN KEY (`parent`) REFERENCES `genre` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
