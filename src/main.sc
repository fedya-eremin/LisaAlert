require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: Приветствие
        q!: $regex</start>
        intent!: /привет || toState=/Start
        a: Здравствуйте! Это - навык Лиза Алерт
        
    state: Start
        eg!: start
        script:
            $smartProfile.getProfileData();
            log(123);
            $response.replies = [];
            $response.replies.push({
              type: 'image',
              imageUrl: 'https://cataas.com/cat/says/Hello' + $jsapi.random(100)
            });
        a: Вот ваш кот. Видели этого человека?
        go!: Matched

        state: Matched
            state: Yes
                eg!: matched
                a: Хорошо. Сообщю, что Вы видели этого человека
                go: /Start  
            state: No
                q: * нет *
                a: Жаль...
                go: /Start 
            # TODO сделать это нормально
                
    state: AskContinue
        a: Дальше?
        go!: Continue
    

    
    state: GetPromoCode
        q!: хочу получить промокод
        script:
            $session.codes = ["XeZ4","o09E","sadL"];
            $jsapi.log("Осталось промокодов: " + $session.codes.length);
        a: Твой промокод: {{ $session.codes.splice(0,1) }}