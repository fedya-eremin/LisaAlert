require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: util/references.js  

theme: /

    state: Start
        eg!: start
        a: Вот, смотрите!
        script:
            var url = "https://cataas.com/cat";
            $response.replies = $response.replies || [];
            $response.replies.push({
              type: 'text',
              messageToUser: '123456'
            });
            var res = $fetch.get(url);
            if (res.isOk) {
                $jsapi.log(res);
            }

