require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: Приветствие
        q!: $regex</start>
        intent!: /привет || toState=/Prepare
        a: Здравствуйте! Это - навык Лиза Алерт
        
    state: Prepare
        intent!: /SearchIntent
        go!: /Start
        
    state: Start 
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
            eg!: matched
            a: ok
            go: /Start
        
            # TODO сделать это нормально
                
    state: AskContinue
        a: Хорошо. Сообщю, что Вы видели этого человека. Идём дальше!
        go: /Start
        state: Matched
            state: Yes
                eg!: matched
                go!: /Start

            state: No
                q: * нет *
                a: Жаль...
                go: /Start
        
        