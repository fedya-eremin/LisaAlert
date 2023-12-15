require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: Приветствие
        q!: $regex</start>
        intent!: /привет || toState=/Start
        a: Здравствуйте! Это - навык Лиза Алерт
        
    state: Start
        intent!: /SearchIntent || toState = Matched
        script:
            $smartProfile.getProfileData();
            log(123);
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
                go: /Start

            state: No
                q: * нет *
                a: Жаль...
                go: /Start
            # TODO сделать это нормально
                
    state: AskContinue
        a: Дальше?
        go!: /Start