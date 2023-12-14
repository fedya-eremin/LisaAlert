require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /

    state: Start
        eg!: start
        script:
            $jsapi.log(123);
        a: Вот: {{ $request.query }}

