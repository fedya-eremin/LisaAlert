require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /

    state: Start
        eg!: start
        script:
            $jsapi.log(123);
            $fetch.get("https://cataas.com/cat")
                .then(function (res) {$temp.res = res})
            $response.replies = $response.replies || [];
            $response.replies.push({
              type: 'image',
              imageUrl: 'https://cataas.com/cat'
            });

        a: Вот: {{ $temp }}

    
    state: GetPromoCode
        q!: хочу получить промокод
        script:
            $session.codes = ["XeZ4","o09E","sadL"];
            $jsapi.log("Осталось промокодов: " + $session.codes.length);
        a: Твой промокод: {{ $session.codes.splice(0,1) }}