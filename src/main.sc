require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: util/references.js  

theme: /

    state: Start
        eg!: start
        a: Вот, смотрите!
        script:
            var url = "https://cataas.com/cat";
            var res = $fetch.get(url);
            if (res.isOk) {
                $log(res);
            }

