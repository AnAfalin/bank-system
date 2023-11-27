# Bank System - Technical Specification
Разработать высоконагруженную банковскую систему. Архитектура системы представлена независимыми микросервисами.
Каждый микросервис имеет свою собственную базу данных и взаимодействует с другими микросервисами черезопределенные API (REST или очередь обмена сообщениями).
Такое разделение обеспечивает гибкость, масштабируемость и упрощает обслуживание системы.

Система должна решать следующие задачи:
•	Регистрация и авторизация клиентов в системе.
•	Управление счетами.
•	Выполнение транзакций (пополнение, снятие, оплата, перевод).
•	Генерация отчетов по счетам: файл, предоставляющий информацию по пополнениям, снятиям, оплатам, переводам за указанный период (возможны дополнительные параметры).


Микросервисы:
•	Customer Service: сервис по работе с клиентами и их данными.
•	Account Service: сервис по работе с банковскими счетами клиентов.
•	Payment Service: сервис по работе с транзакциями.
•	Report service: сервис генерации отчетов по счетам клиентов.
•	Authorization Service: сервис аутентификации и авторизации клиентов в системе.