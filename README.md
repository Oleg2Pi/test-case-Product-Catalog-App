# Тестовое задание
При запуске приложения у вас открывается каталог продуктов (в данном случае инструментов стоматолога),
при нажатии на которые вы переходите дальше, пока не дайдете до самого продукта.

### Настройка
На данный момент проект настроен на json фаел из папки assets в ресурсах, в нем находятся тестовые данные, чтобы 
можно было посмотреть приложение.

Чтобы получать json данные из удаленного сервера, вам нужно зайти в класс <code>ApiClient</code>
и заменить базовый url на свой. Затем перейти в интерфейс <code>ApiServer</code> и в нем заменить
api на свой, чтобы получить json файл. Полный путь составляется из <code>BASE_URL + Get(api)</code>.

И в конце в <code>MainActivity</code> закомментировать строчки и раскомментировать по подсказке в коде