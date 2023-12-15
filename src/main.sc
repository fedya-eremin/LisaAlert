require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: Приветствие || noContext = true
        q!: *
        intent!: /привет || toState=/Prepare
        a: Здравствуйте! Это - навык Лиза Алерт
        
    state: Prepare
        intent: /SearchIntent
        go: /Start

    state: Start || modal = true
        script:
            $smartProfile.getProfileData();
            $jsapi.log(123);
            $response.replies = [];
            $response.replies.push({
              type: 'image',
              imageUrl: 'https://cataas.com/cat/says/Hello' + $jsapi.random(100)
            });
        a: Вот ваш кот. Видели этого человека?

        state: Matched
            eg: matched
            a: Хорошо. Сообщю, что Вы видели этого человека.
            go!: /ShowMore
            
        state: LocalCatchAll
            event: noMatch
            a: Не понимаю вас. Попробуйте еще раз, пожалуйста
            
    state: ShowMore
        a: Показать еще объявления?
        
        state: YesShowMore
            q: да
            go!: /Start
        
        state: LocalCatchAll
            event: noMatch
            a: Хорошо, до свидания!
        