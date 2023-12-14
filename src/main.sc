require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: Start
        eg!: start
        script:
            $smartProfile.getProfileData();
            $response.replies = [];
            $response.replies.push({
              type: 'image',
              imageUrl: 'https://cataas.com/cat/says/Hello' + $jsapi.random(100)
            });
        a: Вот ваш кот
        
    state: Matched
        eg!: matched
        intent: /MatchedIntent || fromState=/Start, onlyThisState = true
        a: Хорошо. Сообщю, что Вы видели этого человека

    
    state: GetPromoCode
        q!: хочу получить промокод
        script:
            $session.codes = ["XeZ4","o09E","sadL"];
            $jsapi.log("Осталось промокодов: " + $session.codes.length);
        a: Твой промокод: {{ $session.codes.splice(0,1) }}