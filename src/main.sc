require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    state: Приветствие
        q!: $regex</start>
        intent: /привет
        a: Здравствуйте! Это - навык Лиза Алерт!

    state: Prepare
        q: *
        intent: /SearchIntent
        a: Давайте посмотрим!
