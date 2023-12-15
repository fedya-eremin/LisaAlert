require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: Приветствие
        a: Здравствуйте! Это - навык Лиза Алерт!
        go!: /Prepare
        
    state: Prepare
        intent: /SearchIntent
        

    state: Start || modal = true
        script:
            $smartProfile.getProfileData();
            $jsapi.log(123);
            $response.replies = [];
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
            
    state: ShowMore
        a: Показать еще объявления?
        
        state: YesShowMore
            q: да
            go!: /Start
        
        state: LocalCatchAll
            event: noMatch
            a: Хорошо, до свидания!
            go!: /Приветствие
        