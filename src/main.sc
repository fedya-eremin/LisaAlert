require: slotfilling/slotFilling.sc
  module = sys.zb-common

require: util.js

theme: /
    state: Greeting
        q!: $regex</start>
        intent: /привет
        a: Здравствуйте! Это - навык Лиза Алерт!
        a: Вы можете помочь нам найти пропавших без вести людей.
        a: Вам будут показаны их ориентировки, после каждой вы сможете сообщить детали
        a: Чтобы начать, скажите "Покажи пропавших людей"!

    state: Prepare
        intent: /SearchIntent
        a: Давайте посмотрим!
        go!: /Start

    state: Start || modal = true
        script:
            $session.people = $session.people || [];
            $response.replies = $response.replies || [];
            if ($session.people.length === 100) {
                $session.people = [];
            }
            var coord = 1;

            $session.people.push(coord)
            $response.replies.push({
                type: 'text',
                text: JSON.stringify(typeof $session.people)
            });
            $response.replies.push({
              type: 'image',
              imageUrl: 'https://cataas.com/cat/says/Hello' + coord
            });
        a: Вот ваш кот. Видели этого человека?

        state: Seen
            q: *
            a: Хорошо. Сообщю, что Вы видели этого человека. [{{ $request.query }}]
            script:
                $mail.sendMessage("henry_morgan06@mail.ru", "message subject", "message body");
            go!: /ShowMore
        
        state: NotSeen
            q: * нет *
            go!: /ShowMore
            
        state: LocalCatchAll
            event: noMatch
            a: Не понимаю вас. Попробуйте еще раз, пожалуйста
            
    state: ShowMore || modal = true
        a: Показать еще объявления?
        
        state: YesShowMore
            q: да
            go!: /Start
        
        state: LocalCatchAll
            event: noMatch
            intent: /пока || toState = /Greeting
            a: Хорошо, до свидания!
            go!: /
