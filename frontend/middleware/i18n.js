export default function ({ isHMR, app, store, error, query, req }) {
  const defaultLocale = app.i18n.fallbackLocale
  // If middleware is called from hot module replacement, ignore it
  if (isHMR) return
  // Get locale from params  
  let locale = app.i18n.locale || defaultLocale
  if (store.state.locales.indexOf(locale) === -1) {
    return error({ message: 'This page could not be found.', statusCode: 404 })
  }

  let h = "", getCookie = (e) => {
    locale = e.split("=")[1] || locale
  }

  if(process.server) {
    if(req) {
      h = req.headers.cookie
    } 
  } else {
    h = document.cookie
  }

  if(h) {
    h.split(";").filter(e => e.match("_locale_")).forEach(getCookie)
  }
  
  // Set locale
  store.commit('SET_LANG', locale)
  app.i18n.locale = store.state.locale
}