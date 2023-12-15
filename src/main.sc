require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: Приветствие
        q!: $regex</start>
        intent!: /привет || toState=/Start
        a: Здравствуйте! Это - навык Лиза Алерт
        
    state: Start
        intent!: /SearchIntent 
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
                go!: /AskContinue

            state: No
                q: * нет *
                go: /Start
            # TODO сделать это нормально
                
    state: AskContinue
        a: Хорошо. Сообщю, что Вы видели этого человека. Идём дальше?
        go!: Matched
        state: Matched
            state: Yes
                eg!: matched
                go!: /Start

            state: No
                q: * нет *
                a: Жаль...
                go: /Start
        
        