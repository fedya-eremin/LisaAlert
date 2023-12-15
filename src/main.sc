require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: Приветствие
        q!: $regex</start>
        intent!: /привет
        a: Здравствуйте! Это - навык Лиза Алерт!

    state: Prepare
        q: *
        intent: /SearchIntent
        a: Давайте посмотрим!
        go!: /Start

    state: Start || modal = true
        script:
            $jsapi.log(123);
            $response.replies = $response.replies || [];
            $response.replies.push({
              type: 'image',
              imageUrl: 'https://cataas.com/cat/says/Hello' 
            });
        a: Вот ваш кот. Видели этого человека?

        state: Seen
            eg: matched
            a: Хорошо. Сообщю, что Вы видели этого человека.
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
            a: Хорошо, до свидания!
            go!: /Приветствие
        