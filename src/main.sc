require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /

    state: Start
        eg!: start
        script:
            $jsapi.log(123);
        a: Вот: {{ $request.query }}

    $session.codes = ["XeZ4","o09E","sadL"];
    state: GetPromoCode
        q!: хочу получить промокод
        a: Твой промокод: {{ $session.codes.splice(0,1) }}
        script:
            $jsapi.log("Осталось промокодов: " + $session.codes.length);