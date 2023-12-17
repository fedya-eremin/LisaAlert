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
            var url = "https://sber.skomarov.com/api/v1/locator/missing?latitude=43.404851&longitude=39.959444";
            $session.people = $session.people || [];
            $client.lastQuery = $client.lastQuery || [];
            $response.replies = $response.replies || [];
            if ($session.people.length === 0) {
                var newPeople = JSON.parse($http.get(url).data).map(function(e) {
                    return e.photo_url;
                });
                $session.notUpdated = newPeople.slice(0).sort().join() == $client.lastQuery.slice(0).sort().join();
                $response.replies.push({
                    type: 'text',
                    text: notUpdated.toString()
                })
                if ($session.notUpdated) {
                    return;
                }
                $client.lastQuery = newPeople;
                $session.people = newPeople;
                //$client.lastRequest = $session.people;
            }
            $response.replies.push({
              type: 'text',
              text: JSON.stringify($session.people.splice(0, 1)[0])
            });
        if: $session.notUpdated
            a: Пока новых пропавших без вести людей рядом с вами нет. Заходите позже!
            go!: /ShowMore/LocalCatchAll
        a: Вы видели этого человека?

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
            a: До новых встреч!
            go!: /
