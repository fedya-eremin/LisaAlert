require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: FlightNumber || modal = true
        q!: * (когда|прибывает*) * ~рейс *
        a: Здравствуйте! Какой у вас номер рейса?

        state: GetNumber
            q: * сто *
            a: Ожидается прибытие рейса
            go!: /WhatElse

        state: LocalCatchAll
            event: noMatch
            a: Это не похоже на номер рейса. Попробуйте еще раз.

    state: WhatElse
        a: Чем еще я могу помочь?

    state: City
        q: * москва *
        a: Вас интересуют рейсы из города {{ $request.query }}?