require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /

    state: Start
        eg!: start
        script:
            $jsapi.log(123);
        a: Вот: {{ $fetch.get("https://cataas.com/cat?json=true").isOk || true  }}

    
    state: GetPromoCode
        q!: хочу получить промокод
        script:
            $session.codes = ["XeZ4","o09E","sadL"];
            $jsapi.log("Осталось промокодов: " + $session.codes.length);
        a: Твой промокод: {{ $session.codes.splice(0,1) }}