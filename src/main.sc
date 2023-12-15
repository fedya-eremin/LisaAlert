require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: FlightNumber || modal = true
        q!: * (когда|прибывает*) * ~рейс *
        a: Здравствуйте! Какой у вас номер рейса?

        state: GetNumber
            q: * $Number *
            a: Ожидается прибытие рейса
            go!: /WhatElse

        state: LocalCatchAll
            event: noMatch
            a: Это не похоже на номер рейса. Попробуйте еще раз.

    state: WhatElse
        a: Чем еще я могу помочь?

    state: City
        q: * $City *
        a: Вас интересуют рейсы из города {{ $parseTree._City }}?