require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: util/references.js  

theme: /

    state: Start
        eg!: start
        script:
            var url = "https://cataas.com/cat";
        a: Вот: {{ $url }}

