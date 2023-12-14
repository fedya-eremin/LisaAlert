require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: util/references.js  

theme: /

    state: Start
        eg!: start
        script:
            var url = "https://cataas.com/cat?json=true";
            var res = $fetch.get(url);
            if (res.isOk) {
                $ans = res.data.size;
            }
        a: Вот: {{ $ans }}

