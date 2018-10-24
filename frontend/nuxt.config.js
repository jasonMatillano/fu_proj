const pkg = require('./package')

module.exports = {
  mode: 'universal',

  /*
  ** Headers of the page
  */
  head: {
    title: pkg.name,
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: pkg.description }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },

  /*
  ** Customize the progress-bar color
  */
  loading: { color: '#FFFFFF' },

  /*
  ** Global CSS
  */
  css: [
    '@/assets/scss/global.scss',
    '@/assets/scss/common.scss',
  ],

  /*
  ** Plugins to load before mounting the App
  */
  plugins: [
    { src: '~/plugins/global.js', ssr: true },
    { src: '~/plugins/particles.js', ssr: false },
    { src: '~/plugins/i18n.js', ssr: true },
    { src: '~/plugins/axios.js', ssr: true },
    {src: '~/plugins/Vuelidate', ssr: true}
  ],

  router: {
    middleware: ['i18n', 'auth'],
  },

  /*
  ** Nuxt.js modules
  */
  modules: [
    // Doc: https://bootstrap-vue.js.org/docs/
    'bootstrap-vue/nuxt',
    // Doc: https://github.com/nuxt-community/axios-module#usage
    // '@nuxtjs/axios',
    ['@nuxtjs/axios', {
      baseURL: 'http://192.168.99.100:8080/fujiko',
      proxyHeaders: false,
      credentials: false 
    }], 
  ],
  /*
  ** Axios module configuration
  */
  axios: {
    // See https://github.com/nuxt-community/axios-module#options
    // proxy: true // Can be also an object with default options

  },

  // proxy: {
  //   '/api/': 'http://localhost:8080',
  // },
  
  watchers: {
    webpack: {
      aggregateTimeout: 300,
      poll: 1000
    }
  },

  /*
  ** Build configuration
  */
  build: {
    /*
    ** You can extend webpack config here
    */

    extend(config, ctx) {
      vendor : [
        'axios',
        'vuelidate'
      ]

      config.module.rules.push({
        test: /\.ts$/,
        loader: 'ts-loader'
      })

      for (rule of config.module.rules) {
        if (rule.loader === 'vue-loader') {
          rule.options.loaders.ts = 'ts-loader?{"appendTsSuffixTo":["\\\\.vue$"]}'
        }
      }
    }
  }
}
