1.2. Создайте приложение, которое:
- устанавливает соединение с созданной базой данных;
- выполняет запросы к базе данных:
· получить список всех сотрудников;
· получить список всех заданий;
· получить список сотрудников указанного отдела;
· добавить задание для некоторого сотрудника
· получить для указанного сотрудника список его заданий;
· удалить сотрудника;
- выводит на консоль запрашиваемые данные.

Взаимодействие с базой данных реализовано с использованием шаблона DAO (пакет db.dao).
Интерфейс CoreDao содержит общие методы для всех сущностей.
    - findAll()
    - findById(key)
Абстрактный класс CoreDaoImpl реализует обобщенные методы, вызывая абстрактные методы рабора ответа
из дочерних классов DAO.

Класс ConnectionHandler реализует шаблон Одиночка. Он получает информацию о СУБД и возвращает соединение.