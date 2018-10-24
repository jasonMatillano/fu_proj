import Vue from 'vue'

import { library, config } from '@fortawesome/fontawesome-svg-core'
import { far } from '@fortawesome/free-regular-svg-icons'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import fteHlper from '@try-parser/fte-helper'

import 'vue-event-calendar/dist/style.css'
import  '@try-parser/fte-helper/dist/fte-helper.css'

//import Vuelidate from 'vuelidate'

import vueEventCalendar from 'vue-event-calendar'

config.replacementClass = 'svg-inline--fa'
config.showMissingIcons = true

library.add(far, fas)

Vue.use(fteHlper)
//Vue.use(Vuelidate)
Vue.use(vueEventCalendar, { locale: 'ja' }) 

Vue.component('fa', FontAwesomeIcon)