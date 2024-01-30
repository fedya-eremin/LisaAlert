# Salute Lisa Alert
> чат-бот в виде навыка Салют, который позволяет людям без усилий помогать в поиске пропавших без вести

## Взаимодействие:
- Нажать на зеленую кнопку в SberStudio

## Описание сценария:
- При первом знакомстве (ивент /start) бот предлагает участвовать в поиске пропавших без вести. Далее при при помощи классификатора и заранее подготовленных фраз (в файле /src/dictionaries/examples.json) начинается взаимодействие.
- Бот отправляет фотографию ориентировки на пропавшего без вести человека (при запуске на реальном устройстве приложение должно получить доступ к геоданным, сейчас в сценарии установлены координаты Сочи). 
    - Если на фото не обнаружено пропавшего, бот предлагает продолжить попытку.
    - Если пользователь видел пропавшего без вести, то боту следует уведомить Лиза Алерт об этом (запрос к API, SMTP на зарезервированный email и т.п.)
- Чтобы не повторяться, бот запоминает каждые 10 ориентировок (сохраняя их в пользовательском кэше), полученных от ручки к API Лиза Алерт. Если эти 10 ориентировок просмотрены, то отправляется запрос на получение новых. Если старые и новые ориентировки идентичны, то бот отправляет пользователю предупреждение о том, что новых ориентировок нет и следует зайти в другой раз.
