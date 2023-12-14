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
            $response.replies = [];
            $response.replies.push({
              type: 'image',
              imageUrl: 'https://cataas.com/cat/says/Hello' + $jsapi.random(100)
            });
        a: Вот ваш кот. Видели этого человека?

            state: Matched
                state: Yes
                    eg!: matched
                    a: Хорошо. Сообщю, что Вы видели этого человека
                state: No
                    q: * нет *
                    a: Жаль... Далее
                    go!: /Start

    
    state: GetPromoCode
        q!: хочу получить промокод
        script:
            $session.codes = ["XeZ4","o09E","sadL"];
            $jsapi.log("Осталось промокодов: " + $session.codes.length);
        a: Твой промокод: {{ $session.codes.splice(0,1) }}