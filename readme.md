# Задание: Автоматизация Meowle. Kaspresso, Compose, WireMock

## Описание

Приложение реализует сервис Meowle.

Исходник проекта находится по ссылке: https://github.com/gunrus/Meeowle.

Функционал приложения:
- Пользователи могут подобрать кличку своему питомцу. 
- Также можно добавить новую кличку, добавить туда фотографии и описание.
- Клички котиков можно добавлять в избранное, которое сохраняется локально на устройстве.
- Клички можно лайкать и дизлайкать, на основе чего сервис составляет рейтинг котоимён.

# Тест-кейсы
### Тест 1. Успешный поиск котиков  - (test1SearchCat)


_**Шаги:**_
- Запустить приложения
- На экране поиска ввести имя котика
- Нажать кнопку поиска

_**Ожидаемый результат:**_
- Выводится замоканный список котов, у самого первого в имени есть то имя, по которому производился поиск, имя котика нужно проверить в тесте

### Кейс 2. Переход из поиска котиков в детали  (test2FromSearchCatToDetailCat)

Примечание: только на UIAutomator

_**Шаги:**_
- Запустить приложение
- На экране поиска ввести имя котика
- Нажать кнопку поиска
- Нажать на первого найденного котика

_**Ожидаемый результат:**_
- Открылся экран деталей кота с данными которые были замоканы в тесте

### Кейс 3. Лайк топового котика  (test3LikeTopCat)

_**Шаги:**_
- Запустить приложение
- Перейти на экран рейтинга (Топ по дизлайкам)
- Проверить что вывелись замоканные данные
- Кликнуть на самого первого котика по дизлайкам
- На экране деталей котика поставить ему лайк (нажать на кнопку с лайком)

_**Ожидаемый результат:**_
- Проверить что вызвался корректный метод с запросом в котором передался лайк
  
### Кейс 4. Добавление котика  (test4AddCat) 

_**Шаги:**_
- Запустить приложение
- Перейти на экран добавление кота
- Ввести данные кота
- Кликнуть на кнопку добавить
- Проверить успешное добавление

_**Ожидаемый результат:**_
- Проверить что вызвался корректный метод с запросом в котором передаются данные кота из теста
  
### Кейс 5. Редактирование котика  (test5EditCat)

_**Шаги:**_
- Запустить приложение
- Перейти на рейтинга
- Кликнуть на 5 кота в списке
- На открывшемся экране деталей изменить описание кота
- Проверить что описание изменилось

_**Ожидаемый результат:**_
- Проверить что вызвался корректный метод с запросом в котором передается описание из теста


## Мобильное устройство:
- Pixel 5 API 27 (эмулятор в Android Studio)

## Запуск проекта
- Открыть проект в Android Studio
- Для запуска демонстрации проекта:
  - Нажать правой кнопкой мыши на файл `TestsMeowleNoCompose` и выбрать запустить тест
  - Затем нажать правой кнопкой мыши на файл `TestsMeowleCompose` и выбрать запустить тест

##  Стэк
- Kaspresso, Compose, WireMock, Junit, Kotlin, Android Studio